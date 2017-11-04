package Model;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author septiandrd
 */
public class Pembayaran extends Transaksi {

    private String jnsPembayaran;
    private double jmlbayar;

    public Pembayaran(String jnsPembayaran, double jmlbayar, String idtrx, Date tgl) {
        super(idtrx, tgl);
        this.jnsPembayaran = jnsPembayaran;
        this.jmlbayar = jmlbayar;
    }

    public Pembayaran() {
    }

    public String getJnsPembayaran() {
        return jnsPembayaran;
    }

    public double getJmlbayar() {
        return jmlbayar;
    }

    @Override
    public String getIdtrx() {
        return super.getIdtrx();
    }

}
