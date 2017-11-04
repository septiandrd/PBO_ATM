package Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class Laporan {

    private ArrayList<Transfer> transfer = new ArrayList();
    private ArrayList<Pembayaran> pembayaran = new ArrayList();
    private ArrayList<Pembelian> pembelian = new ArrayList();
    private String idtrx;

    public void addTransfer(String rektujuan, double jumlah, String idtrx, Date tgl) {
        Transfer t = new Transfer(rektujuan, jumlah, idtrx, tgl);
        transfer.add(t);
    }

    public void addPembayaran(String jnsPembayaran, double jumlah, String idtrx, Date tgl) {
        Pembayaran p = new Pembayaran(jnsPembayaran, jumlah, idtrx, tgl);
        pembayaran.add(p);
    }
    
    public void addPembelian(String jnsPembelian, double jumlah, String idtrx, Date tgl) {
        Pembelian p = new Pembelian(jnsPembelian, jumlah, idtrx, tgl);
        pembelian.add(p);
    }

//    public Transfer getTransfer(String rektujuan) {
//        for (Transfer t : transfer) {
//            if (t.getRektujuan().equals(rektujuan)) {
//                return t;
//            }
//        }
//        return null;
//    }        
    public String getLaporan() {
        String s = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        for (Transfer t : transfer) {
            s += dateFormat.format(t.getTanggal()) + " Transfer to " + t.getRektujuan() + " Rp. " + String.valueOf(t.getJmltransfer()) + ",- \n";
        }
        for (Pembayaran p : pembayaran) {
            s += dateFormat.format(p.getTanggal()) + " Pembayaran " + p.getJnsPembayaran() + " Rp. " + String.valueOf(p.getJmlbayar()) + ".- \n";
        }
        return s;
    }

    public String getLaporanTrf() {
        String s = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        for (Transfer t : transfer) {
            s += dateFormat.format(t.getTanggal()) + " Transfer to " + t.getRektujuan() + " Rp. " + String.valueOf(t.getJmltransfer()) + ",- \n";
        }
        return s;
    }

    public String getLaporanBayar() {
        String s = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        for (Pembayaran p : pembayaran) {
            s += dateFormat.format(p.getTanggal()) + " Pembayaran " + p.getJnsPembayaran() + " Rp. " + String.valueOf(p.getJmlbayar()) + ".- \n";
        }
        return s;
    }

}
