/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.Date;
import Model.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author septiandrd
 */
public class HandlerGUI implements ActionListener {
    
    private final AppGUI app;
    private final ConfirmTransfer confirmTransfer;
    private final ConfirmBayar confirmBayar;
    private Nasabah n;
    Database db = new Database();
    ActionEvent ae;
    
    public HandlerGUI() {        
        app = new AppGUI();
        app.AddActionListener(this);
        confirmBayar = new ConfirmBayar();
        confirmBayar.AddActionListener(this);        
        confirmTransfer = new ConfirmTransfer();
        confirmTransfer.AddActionListener(this);        
        app.setVisible(true);
        app.Login();
        db.connect();        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object act = ae.getSource();
        try {
            if (act.equals(app.getBtnLogin())) {                
                login();
            }
            if (act.equals(app.getBtnAkun())) {
                app.InfoAkun(n);
            }
            if (act.equals(app.getBtnRiwayat())) {
                app.Riwayat();
                riwayat(n);
            }
            if (act.equals(app.getBtnTransfer())) {
                app.Transfer();
            }
            if (act.equals(app.getBtnBayar())) {
                app.Pembayaran();
            }
            if (act.equals(app.getBtnLogout())) {   
                int a = JOptionPane.showConfirmDialog(app, "Confirm Logout ?", "Logout", 0);
                if (a==0) {
                    app.Login();
                }               
            }     
            if (act.equals(app.getBtnOkInfo())) {
                app.Menu();
            }
            if (act.equals(app.getBtnOkRiwayat())) {
                app.Menu();
            }                
            if (act.equals(app.getBtnProsesTrf())) {
                checkTransfer();
            }
            if (act.equals(app.getBtnBackTrf())) {
                app.Menu();
                app.resetTransfer();
            }
            if (act.equals(confirmTransfer.getBtnConfirm())) {
                transfer(n);                
            }
            if (act.equals(confirmTransfer.getBtnBack())) {
                confirmTransfer.dispose();
                confirmTransfer.reset();
            }
            if (act.equals(app.getBtnProsesBayar())) {
                checkBayar();
            }
            if (act.equals(app.getBtnBackBayar())) {
                app.Menu();
                app.resetPembayaran();
            }       
            if (act.equals(confirmBayar.getBtnConfirm())) {
                bayar(n);
            }
            if (act.equals(confirmBayar.getBtnBack())) {
                confirmBayar.dispose();
                confirmBayar.reset();
            }
        } catch (ParseException e) {
            
        }         
    }
    
    public void login() {        
         if (app.getJtxPass().equals(db.getPassword(app.getJtxUsername()))){
            JOptionPane.showMessageDialog(app, "Login Berhasil!");
            Laporan l = db.loadLaporan(db.getNorek(app.getJtxUsername()));
            n = db.loadNasabah(app.getJtxUsername());
            n.setRiwayatTransaksi(new Laporan());
            n.setRiwayatTransaksi(l);             
            app.Menu();
            app.setHeader(n.getNama());
            app.resetLogin();
        } else {        
                if(db.getPassword(app.getJtxUsername())==null){
                    JOptionPane.showMessageDialog(app, "Username tidak terdaftar");
                } else {
                    JOptionPane.showMessageDialog(app, "Password salah!");
                }                        
        }     
    }                
    
    public void checkTransfer() {
        if (!app.getRekTujuan().isEmpty()) {
            if (!app.getJumlahTrf().equals(0.0)) {
                if (db.getNama(app.getRekTujuan())!=null && !n.getNorek().equals(app.getRekTujuan())) {
                    if (db.getSaldo(n.getNorek())>app.getJumlahTrf()) {
                        confirmTransfer.setVisible(true);  
                        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
                        Date date = new Date();                
                        String idtrx = "trf" + n.getJmlTrx() + app.getRekTujuan() + dateFormat.format(date);
                        Transfer t = new Transfer(app.getRekTujuan(),app.getJumlahTrf(),idtrx,date);   
                        String text = getTransferConfirm(n,t);
                        confirmTransfer.setTextConfirm(text);
                    } else {
                        JOptionPane.showMessageDialog(app, "Maaf, saldo anda tidak mencukupi");
                    }                    
                } else {
                    JOptionPane.showMessageDialog(app, "No. Rekening tujuan salah");
                }                        
            } else {
                JOptionPane.showMessageDialog(app, "Harap masukkan jumlah transfer");
            }                    
        } else {
            JOptionPane.showMessageDialog(app, "Harap masukkan Rekening Tujuan");
        }  
    }
    
    public void transfer(Nasabah n) throws ParseException {
        if (confirmTransfer.getPIN().equals(n.getPIN())) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
            Date date = new Date();                
            String idtrx = "trf" + n.getJmlTrx() + app.getRekTujuan() + dateFormat.format(date);
            Transfer t = new Transfer(app.getRekTujuan(),app.getJumlahTrf(),idtrx,date);               
            n.transfer(app.getRekTujuan(),app.getJumlahTrf(),idtrx,date);
            db.saveTransfer(n,t);
            db.updateSaldo(n.getNorek(), n.getSaldo()); 
            db.updateJmlTrx(n.getNorek(), n.getJmlTrx());
            db.updateSaldo(app.getRekTujuan(),(db.getSaldo(app.getRekTujuan()) + app.getJumlahTrf()));
            JOptionPane.showMessageDialog(app, "Transfer Berhasil");
            confirmTransfer.dispose();
            confirmTransfer.reset();                        
            app.resetTransfer();
            app.Menu();
        } else if (confirmTransfer.getPIN().equals(null) || confirmTransfer.getPIN().equals("") ) {
            JOptionPane.showMessageDialog(confirmTransfer, "Harap masukkan PIN");
        } else {
            JOptionPane.showMessageDialog(confirmTransfer, "PIN salah!");
        }
    }
    
    public String getTransferConfirm(Nasabah n, Transfer t) {
        String text = "\n \n Anda akan melakukan Transfer ke " + t.getRektujuan() + "\na.n " 
                + db.getNama(t.getRektujuan()) + " sebesar Rp. " + t.getJmltransfer() + ",- ";
        return text;
    }
    
    public void checkBayar() {
        if (!"<Pilih Pembayaran>".equals(app.getPembayaran())) {
            if (!app.getJumlahBayar().equals(0.0)) { 
                if (db.getSaldo(n.getNorek())>app.getJumlahBayar()) {
                    confirmBayar.setVisible(true);  
                    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
                    Date date = new Date();
                    String idtrx = "pay" + n.getJmlTrx() + app.getPembayaran().substring(0, 3) + n.getNorek().substring(6,10) + dateFormat.format(date);
                    Pembayaran p = new Pembayaran(app.getPembayaran(),app.getJumlahBayar(),idtrx,date);
                    String text = getBayarConfirm(n,p);
                    confirmBayar.setTextConfirm(text);                    
                } else {
                    JOptionPane.showMessageDialog(app, "Maaf, saldo anda tidak mencukupi.");
                }
            } else {
                JOptionPane.showMessageDialog(app, "Harap masukkan jumlah pembayaran");
            }
        } else {
            JOptionPane.showMessageDialog(app,"Harap pilih pembayaran");
        }  
    }
    
    public void bayar(Nasabah n) {
        if (confirmBayar.getPIN().equals(n.getPIN())) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
            Date date = new Date();
            String idtrx = "pay" + n.getJmlTrx() + app.getPembayaran().substring(0, 3) + n.getNorek().substring(6,10) + dateFormat.format(date);
            Pembayaran p = new Pembayaran(app.getPembayaran(),app.getJumlahBayar(),idtrx,date);
            n.bayar(app.getPembayaran(), app.getJumlahBayar(), idtrx, date);
            db.savePembayaran(n, p);
            db.updateJmlTrx(n.getNorek(), n.getJmlTrx());
            db.updateSaldo(n.getNorek(), n.getSaldo());
            JOptionPane.showMessageDialog(app, "Pembayaran Berhasil");
            confirmBayar.dispose();
            confirmBayar.reset();            
            app.resetPembayaran();
            app.Menu();
        } else if (confirmBayar.getPIN() == null || confirmBayar.getPIN().equals("") ) {
            JOptionPane.showMessageDialog(confirmBayar, "Harap masukkan PIN");
        } else {
            JOptionPane.showMessageDialog(confirmBayar, "PIN salah!");
        }
    }
    
    public String getBayarConfirm(Nasabah n, Pembayaran p) {
        String text = "\n \n Anda akan melakukan Pembayaran " + p.getJnsPembayaran()  
                 + " sebesar Rp. " + p.getJmlbayar()+ ",- ";
        return text;
    }
    
    public void riwayat(Nasabah n) {
        String s1 = n.getRiwayatTransaksi().getLaporanTrf();
        String s2 = n.getRiwayatTransaksi().getLaporanBayar();
//        String s3 = n.getRiwayatTransaksi().getLaporanBeli();
        app.setTextRiwayatTrf(s1);
        app.setTextRiwayatBayar(s2);
//        app.setTextRiwayatBeli(s3);
    }
    
}
