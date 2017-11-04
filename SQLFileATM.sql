/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  septiandrd
 * Created: Apr 28, 2017
 */

CREATE TABLE TBayar (idTransaksi VARCHAR(20) NOT NULL, jumlah FLOAT NOT NULL, tanggal DATE NOT NULL, jenisBayar VARCHAR(20) NOT NULL, rekPengirim VARCHAR(20) NOT NULL, PRIMARY KEY (idTransaksi));

CREATE TABLE TNasabah (username VARCHAR(20) NOT NULL, password VARCHAR(20) NOT NULL, nama VARCHAR(20) NOT NULL, ID VARCHAR(20) NOT NULL, noRek VARCHAR(20) NOT NULL, alamat VARCHAR(100) NOT NULL, email VARCHAR(20), saldo FLOAT NOT NULL, pin VARCHAR(4) NOT NULL, jmlTrx INT NOT NULL, PRIMARY KEY (ID));

CREATE TABLE TTransfer (jumlah FLOAT NOT NULL, tanggal DATE NOT NULL, idTransaksi VARCHAR(30) NOT NULL, rekTujuan VARCHAR(20) NOT NULL, rekPengirim VARCHAR(20) NOT NULL, PRIMARY KEY (idTransaksi));

INSERT INTO atm_db.TNasabah (username, password, nama, ID, noRek, alamat, email, saldo, pin, jmlTrx) 
	VALUES ('septiandrd', '1234', 'Septian D Indradi', 'nsb001', '1301154164', 'Bandung', 'septiandrd@gmail.com', 7500000.0, '0122', 0);
INSERT INTO atm_db.TNasabah (username, password, nama, ID, noRek, alamat, email, saldo, pin, jmlTrx) 
	VALUES ('fiqqihms', '1212', 'Fiqqih Maulana', 'nsb002', '1301154178', 'Jakarta', 'fiqqihms@gmail.com', 867000.0, '1212', 0);
INSERT INTO atm_db.TNasabah (username, password, nama, ID, noRek, alamat, email, saldo, pin, jmlTrx) 
	VALUES ('fatihmalisie', 'fatih', 'Fatih Malisie', 'nsb003', '1301154262', 'Palembang', 'fatihmalisie', 3000000.0, '1919', 0);
INSERT INTO atm_db.TNasabah (username, password, nama, ID, noRek, alamat, email, saldo, pin, jmlTrx) 
	VALUES ('mauricefk', 'maurice', 'Maurice Fikry', 'nsb004', '1301154206', 'Banjarmasin', 'maurice@gmail.com', 5000000.0, '4206', 0);
INSERT INTO atm_db.TNasabah (username, password, nama, ID, noRek, alamat, email, saldo, pin, jmlTrx) 
	VALUES ('adityaramadan', 'ara', 'M. Aditya Ramadhan', 'nsb005', '1301154388', 'Jakarta', 'adityarmdn@gmail.com', 2150000.0, '4388', 0);
INSERT INTO atm_db.TNasabah (username, password, nama, ID, noRek, alamat, email, saldo, pin, jmlTrx) 
	VALUES ('adityaarisandi', 'adit', 'Aditya Arisandi', 'nsb006', '1301154122', 'Subang', 'adiyaars@gmail.com', 3270000.0, '4122', 0);
INSERT INTO atm_db.TNasabah (username, password, nama, ID, noRek, alamat, email, saldo, pin, jmlTrx) 
	VALUES ('alivadilla', 'aliva', 'Aliva Dilla Agustin', 'nsb007', '1301154486', 'Cilegon', 'alivadilla@gmail.com', 1000000.0, '4486', 0);
INSERT INTO atm_db.TNasabah (username, password, nama, ID, noRek, alamat, email, saldo, pin, jmlTrx) 
	VALUES ('arfiniainiyyah', 'arfini', 'Arfini Ainiyyah', 'nsb008', '1301154542', 'Bogor', 'arfinia@gmail.com', 2260000.0, '4542', 0);
