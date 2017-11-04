/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

// author @septiandrd
import java.util.Date;
// author @septiandrd

public class Pembelian extends Transaksi {

    private String jnsPembelian;
    private double jmlbayar;

    public Pembelian(String jnsPembelian, double jmlbayar, String idtrx, Date tanggal) {
        super(idtrx, tanggal);
        this.jnsPembelian = jnsPembelian;
        this.jmlbayar = jmlbayar;
    }

    public Pembelian(String idtrx, Date tanggal) {
        super(idtrx, tanggal);
    }

    public String getJnsPembelian() {
        return jnsPembelian;
    }

    public double getJmlbayar() {
        return jmlbayar;
    }

    @Override
    public String getIdtrx() {
        return super.getIdtrx();
    }

}
