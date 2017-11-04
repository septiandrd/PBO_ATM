package Model;

import java.util.Date;

public abstract class Transaksi {

    private String idtrx;
    private Date tanggal;

    public Transaksi() {
    }

    public Transaksi(String idtrx, Date tanggal) {
        this.idtrx = idtrx;
        this.tanggal = tanggal;
    }

    public String getIdtrx() {
        return idtrx;
    }

    public Date getTanggal() {
        return tanggal;
    }

}
