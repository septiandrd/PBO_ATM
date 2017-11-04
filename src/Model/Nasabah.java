package Model;

import java.util.Date;

public class Nasabah {

    private String nama;
    private String alamat;
    private String email;
    private String username;
    private String pass;
    private String ID;
    private String norek;
    private double saldo;
    private String pin;
    private int jmlTrx;
    private Laporan riwayatTransaksi;

    public Nasabah(String nama, String alamat, String email, String username, String pass, String ID, String norek, double saldo, String pin, Laporan riwayatTransaksi) {
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.username = username;
        this.pass = pass;
        this.ID = ID;
        this.norek = norek;
        this.saldo = saldo;
        this.pin = pin;
        this.riwayatTransaksi = riwayatTransaksi;
    }

    public Nasabah(String nama, String alamat, String email, String username, String pass, String ID, String norek, double saldo, String pin, int jmlTrx) {
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.username = username;
        this.pass = pass;
        this.ID = ID;
        this.norek = norek;
        this.saldo = saldo;
        this.pin = pin;
        this.jmlTrx = jmlTrx;
    }

    public void transfer(String rektujuan, double jumlah, String idtrx, Date tgl) {
        if (saldo > jumlah) {
            riwayatTransaksi.addTransfer(rektujuan, jumlah, idtrx, tgl);
            jmlTrx++;
            saldo -= jumlah;
        }
    }

    public void bayar(String jnsPembayaran, double jumlah, String idtrx, Date tgl) {
        if (saldo > jumlah) {
            riwayatTransaksi.addPembayaran(jnsPembayaran, jumlah, idtrx, tgl);
            jmlTrx++;
            saldo -= jumlah;
        }
    }

    public void setRiwayatTransaksi(Laporan riwayatTransaksi) {
        this.riwayatTransaksi = riwayatTransaksi;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNorek() {
        return norek;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getJmlTrx() {
        return jmlTrx;
    }

    public String getPIN() {
        return pin;
    }

    public Laporan getRiwayatTransaksi() {
        return riwayatTransaksi;
    }
}
