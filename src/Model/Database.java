package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class Database {

    private final String server = "jdbc:mysql://localhost:3306/atm_db";
    private final String dbuser = "root";
    private final String dbpasswd = "";
    private Statement statement = null;
    private Connection connection = null;

    public void connect() {
        try {
            connection = DriverManager.getConnection(server, dbuser, dbpasswd);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Terjadi kesalahan saat koneksi database");
        }
    }

    public String getPassword(String username) {
        String pass = null;
        try {
            String query = "select password from TNasabah where username='"
                    + username + "';";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                pass = rs.getString("password");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat login!");
        }
        return pass;
    }

    public String getNorek(String username) {
        String pass = null;
        try {
            String query = "select noRek from TNasabah where username='"
                    + username + "';";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                pass = rs.getString("noRek");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat login!");
        }
        return pass;
    }

    public Double getSaldo(String norek) {
        Double saldo = null;
        try {
            String query = "select saldo from TNasabah where noRek='"
                    + norek + "';";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                saldo = rs.getDouble("saldo");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan");
        }
        return saldo;
    }

    public String getNama(String norek) {
        String nama = null;
        try {
            String query = "select nama from TNasabah where noRek='"
                    + norek + "';";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                nama = rs.getString("nama");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan");
        }
        return nama;
    }

    public Laporan loadLaporan(String norek) {
        Laporan lp = new Laporan();
        try {
            String query = "select * from TTransfer where rekPengirim='"
                    + norek + "' order by 2;";
            ResultSet rs = statement.executeQuery(query);
//            System.out.println("q1 executed");
            while (rs.next()) {
//                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                lp.addTransfer(rs.getString("rekTujuan"), Double.parseDouble(rs.getString("jumlah")), rs.getString("idTransaksi"), rs.getDate("tanggal"));
//                System.out.println("q1 add");
            }

            query = "select * from TBayar where rekPengirim='"
                    + norek + "'order by 3;";
            rs = statement.executeQuery(query);
//            System.out.println("q2 executed");
            while (rs.next()) {
//                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");                
                lp.addPembayaran(rs.getString("jenisBayar"), Double.parseDouble(rs.getString("jumlah")), rs.getString("idTransaksi"), rs.getDate("tanggal"));
//                System.out.println("q2 add");
            }
            
            query = "select * from TBeli where rekPengirim='"
                    + norek + "'order by 3;";
            rs = statement.executeQuery(query);
//            System.out.println("q2 executed");
            while (rs.next()) {
//                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");                
                lp.addPembelian(rs.getString("jenisBeli"), Double.parseDouble(rs.getString("jumlah")), rs.getString("idTransaksi"), rs.getDate("tanggal"));
//                System.out.println("q2 add");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat load data");
        }
        return lp;
    }

    public Nasabah loadNasabah(String username) {
        Nasabah n = null;
        try {
            String query = "select * from TNasabah where username='"
                    + username + "';";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                n = new Nasabah(rs.getString("nama"), rs.getString("alamat"), rs.getString("email"), rs.getString("username"), rs.getString("password"), rs.getString("ID"), rs.getString("noRek"), rs.getDouble("saldo"), rs.getString("pin"), rs.getInt("jmlTrx"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        return n;
    }

    public void saveTransfer(Nasabah n, Transfer t) {
        try {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String query = "insert into TTransfer(idTransaksi,rekPengirim,rekTujuan,jumlah,tanggal) values ("
                    + "'" + t.getIdtrx() + "',"
                    + "'" + n.getNorek() + "',"
                    + "'" + t.getRektujuan() + "',"
                    + t.getJmltransfer() + ","
                    + "'" + date.format(t.getTanggal()) + "');";
//            System.out.println(query);
            statement.execute(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Transfer");
        }
    }

    public void savePembayaran(Nasabah n, Pembayaran p) {
        try {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String query = "insert into TBayar(idTransaksi,jenisBayar,rekPengirim,jumlah,tanggal) values "
                    + "('" + p.getIdtrx() + "',"
                    + "'" + p.getJnsPembayaran() + "',"
                    + "'" + n.getNorek() + "',"
                    + p.getJmlbayar()
                    + ",'" + date.format(p.getTanggal()) + "');";
//            System.out.println(query);
            statement.execute(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Pembayaran");
        }
    }
    
    public void savePembelian(Nasabah n, Pembelian p) {
        try {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String query = "insert into TBeli(idTransaksi,jenisBeli,rekPengirim,jumlah,tanggal) values "
                    + "('" + p.getIdtrx() + "',"
                    + "'" + p.getJnsPembelian() + "',"
                    + "'" + n.getNorek() + "',"
                    + p.getJmlbayar()
                    + ",'" + date.format(p.getTanggal()) + "');";
//            System.out.println(query);
            statement.execute(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Pembelian");
        }
    }

    public void updateSaldo(String norek, Double saldo) {
        try {
            String query = "update TNasabah set saldo="
                    + saldo + " where norek="
                    + "'" + norek + "';";
//            System.out.println(query);
            statement.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error update saldo");
        }
    }

    public void updateJmlTrx(String norek, int jmlTrx) {
        try {
            String query = "update TNasabah set jmlTrx="
                    + jmlTrx + " where norek="
                    + "'" + norek + "';";
//            System.out.println(query);
            statement.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error update saldo");
        }
    }

}
