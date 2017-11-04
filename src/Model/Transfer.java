package Model;

import java.util.Date;

/**
 * @author septiandrd
 */
public class Transfer extends Transaksi {

    private String rektujuan;
    private double jmltransfer;

    public Transfer(String rektujuan, double jmltransfer, String idtrx, Date tgl) {
        super(idtrx, tgl);
        this.rektujuan = rektujuan;
        this.jmltransfer = jmltransfer;
    }

    public String getRektujuan() {
        return rektujuan;
    }

    public double getJmltransfer() {
        return jmltransfer;
    }
}
