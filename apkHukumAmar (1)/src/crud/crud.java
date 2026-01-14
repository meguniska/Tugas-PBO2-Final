/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.sql.ResultSetMetaData;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author User
 */
    public class crud {
    private Connection Koneksidb;
    private String username="root";
    private String password="";
    private String dbname="db_hukum_perkara"; 
    private String urlKoneksi="jdbc:mysql://localhost/"+dbname;
    public boolean duplikasi=false;

    public String CEK_NAMA_KLIEN, CEK_TEMPAT_LAHIR, CEK_TANGGAL_LAHIR, CEK_KTP, CEK_JENIS_KELAMIN, CEK_PEKERJAAN, CEK_ALAMAT_KLIEN, CEK_TELEPON_KLIEN, CEK_EMAIL_KLIEN = null;
    public String CEK_NAMA_PENGACARA, CEK_ALAMAT_PENGACARA, CEK_TELEPON_PENGACARA, CEK_INSTANSI = null;
    public String CEK_NAMA_PERKARA, CEK_JENIS_PERKARA_KAT, CEK_DESKRIPSI_PERKARA = null;
    public String CEK_JENIS_PERKARA, CEK_NO_PERKARA, CEK_KATEGORI_ID, CEK_TGL_MULAI, CEK_TGL_SELESAI, CEK_STATUS_PERKARA, CEK_KLIEN_ID, CEK_NAMA_TERDAKWA, CEK_PENGACARA_ID, CEK_KETERANGAN, CEK_DOKUMEN, CEK_SURAT_KUASA = null;

    
    public crud(){
        try {
            Driver dbdriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(dbdriver);
            Koneksidb=DriverManager.getConnection(urlKoneksi,username,password);
            System.out.print("Database Terkoneksi");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }

    public void simpanKlien01(String id, String nama_klien, String tempat_lahir, String tanggal_lahir, String ktp, String jenis_kelamin, String pekerjaan, String alamat, String telepon, String email){
        try {
            String sqlsimpan="insert into klien(id, nama_klien, tempat_lahir, tanggal_lahir, ktp, jenis_kelamin, pekerjaan, alamat, telepon, email) value"
                    + " ('"+id+"', '"+nama_klien+"', '"+tempat_lahir+"', '"+tanggal_lahir+"', '"+ktp+"', '"+jenis_kelamin+"', '"+pekerjaan+"', '"+alamat+"', '"+telepon+"', '"+email+"')";
            String sqlcari="select*from klien where id='"+id+"'";
            
            Statement cari=Koneksidb.createStatement();
            ResultSet data=cari.executeQuery(sqlcari);
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Klien sudah terdaftar");
            } else {
                Statement perintah=Koneksidb.createStatement();
                perintah.execute(sqlsimpan);
                JOptionPane.showMessageDialog(null, "Data Klien berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void simpanKlien02(String id, String nama_klien, String tempat_lahir, String tanggal_lahir, String ktp, String jenis_kelamin, String pekerjaan, String alamat, String telepon, String email){
        try {
            String sqlsimpan="INSERT INTO klien (id, nama_klien, tempat_lahir, tanggal_lahir, ktp, jenis_kelamin, pekerjaan, alamat, telepon, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sqlcari= "SELECT*FROM klien WHERE id = ?";
            
            PreparedStatement cari = Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, id);
            ResultSet data = cari.executeQuery();
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Klien sudah terdaftar");
                this.duplikasi = true;
                this.CEK_NAMA_KLIEN = data.getString("nama_klien");
                this.CEK_TEMPAT_LAHIR = data.getString("tempat_lahir");
                this.CEK_TANGGAL_LAHIR = data.getString("tanggal_lahir");
                this.CEK_KTP = data.getString("ktp");
                this.CEK_JENIS_KELAMIN = data.getString("jenis_kelamin");
                this.CEK_PEKERJAAN = data.getString("pekerjaan");
                this.CEK_ALAMAT_KLIEN = data.getString("alamat");
                this.CEK_TELEPON_KLIEN = data.getString("telepon");
                this.CEK_EMAIL_KLIEN = data.getString("email");
            } else {
                this.duplikasi = false;
                this.CEK_NAMA_KLIEN = null;
                this.CEK_TEMPAT_LAHIR = null;
                this.CEK_TANGGAL_LAHIR = null;
                this.CEK_KTP = null;
                this.CEK_JENIS_KELAMIN = null;
                this.CEK_PEKERJAAN = null;
                this.CEK_ALAMAT_KLIEN = null;
                this.CEK_TELEPON_KLIEN = null;
                this.CEK_EMAIL_KLIEN = null;
                
                PreparedStatement perintah = Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, id);
                perintah.setString(2, nama_klien);
                perintah.setString(3, tempat_lahir);
                perintah.setString(4, tanggal_lahir);
                perintah.setString(5, ktp);
                perintah.setString(6, jenis_kelamin);
                perintah.setString(7, pekerjaan);
                perintah.setString(8, alamat);
                perintah.setString(9, telepon);
                perintah.setString(10, email);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Klien berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahKlien(String id, String nama_klien, String tempat_lahir, String tanggal_lahir, String ktp, String jenis_kelamin, String pekerjaan, String alamat, String telepon, String email){
        try {
            String sqlubah="UPDATE klien SET nama_klien = ?, tempat_lahir = ?, tanggal_lahir = ?, ktp = ?, jenis_kelamin = ?, pekerjaan = ?, alamat = ?, telepon = ?, email = ? WHERE id = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlubah);
            perintah.setString(1, nama_klien);
            perintah.setString(2, tempat_lahir);
            perintah.setString(3, tanggal_lahir);
            perintah.setString(4, ktp);
            perintah.setString(5, jenis_kelamin);
            perintah.setString(6, pekerjaan);
            perintah.setString(7, alamat);
            perintah.setString(8, telepon);
            perintah.setString(9, email);
            perintah.setString(10, id); 
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Klien berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusKlien(String id){
        try {
            String sqlhapus="DELETE FROM klien WHERE id = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlhapus);
            perintah.setString(1, id);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Klien berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void tampilDataKlien(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            
            modeltabel.addColumn("ID");
            modeltabel.addColumn("Nama Klien");
            modeltabel.addColumn("Tempat Lahir");
            modeltabel.addColumn("Tanggal Lahir");
            modeltabel.addColumn("KTP");
            modeltabel.addColumn("Jenis Kelamin");
            modeltabel.addColumn("Pekerjaan");
            modeltabel.addColumn("Alamat");
            modeltabel.addColumn("Telepon");
            modeltabel.addColumn("Email");
            
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
        }
    }

    public void simpanPengacara01(String id, String nama_pengacara, String alamat, String telepon, String instansi){
        try {
            String sqlsimpan="insert into pengacara(id, nama_pengacara, alamat, telepon, instansi) value"
                    + " ('"+id+"', '"+nama_pengacara+"', '"+alamat+"', '"+telepon+"', '"+instansi+"')";
            String sqlcari="select*from pengacara where id='"+id+"'";
            
            Statement cari=Koneksidb.createStatement();
            ResultSet data=cari.executeQuery(sqlcari);
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Pengacara sudah terdaftar");
            } else {
                Statement perintah=Koneksidb.createStatement();
                perintah.execute(sqlsimpan);
                JOptionPane.showMessageDialog(null, "Data Pengacara berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void simpanPengacara02(String id, String nama_pengacara, String alamat, String telepon, String instansi){
        try {
            String sqlsimpan="INSERT INTO pengacara (id, nama_pengacara, alamat, telepon, instansi) VALUES (?, ?, ?, ?, ?)";
            String sqlcari= "SELECT*FROM pengacara WHERE id = ?";
            
            PreparedStatement cari = Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, id);
            ResultSet data = cari.executeQuery();
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Pengacara sudah terdaftar");
                this.duplikasi = true;
                this.CEK_NAMA_PENGACARA = data.getString("nama_pengacara");
                this.CEK_ALAMAT_PENGACARA = data.getString("alamat");
                this.CEK_TELEPON_PENGACARA = data.getString("telepon");
                this.CEK_INSTANSI = data.getString("instansi");
            } else {
                this.duplikasi = false;
                this.CEK_NAMA_PENGACARA = null;
                this.CEK_ALAMAT_PENGACARA = null;
                this.CEK_TELEPON_PENGACARA = null;
                this.CEK_INSTANSI = null;
                
                PreparedStatement perintah = Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, id);
                perintah.setString(2, nama_pengacara);
                perintah.setString(3, alamat);
                perintah.setString(4, telepon);
                perintah.setString(5, instansi);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Pengacara berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahPengacara(String id, String nama_pengacara, String alamat, String telepon, String instansi){
        try {
            String sqlubah="UPDATE pengacara SET nama_pengacara = ?, alamat = ?, telepon = ?, instansi = ? WHERE id = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlubah);
            perintah.setString(1, nama_pengacara);
            perintah.setString(2, alamat);
            perintah.setString(3, telepon);
            perintah.setString(4, instansi);
            perintah.setString(5, id); 
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pengacara berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusPengacara(String id){
        try {
            String sqlhapus="DELETE FROM pengacara WHERE id = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlhapus);
            perintah.setString(1, id);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pengacara berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void tampilDataPengacara(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            
            modeltabel.addColumn("ID");
            modeltabel.addColumn("Nama Pengacara");
            modeltabel.addColumn("Alamat");
            modeltabel.addColumn("Telepon");
            modeltabel.addColumn("Instansi");
            
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
        }
    }

    public void simpanKategoriPerkara01(String id, String nama_perkara, String jenis_perkara, String deskripsi){
        try {
            String sqlsimpan="insert into kategori_perkara(id, nama_perkara, jenis_perkara, deskripsi) value"
                    + " ('"+id+"', '"+nama_perkara+"', '"+jenis_perkara+"', '"+deskripsi+"')";
            String sqlcari="select*from kategori_perkara where id='"+id+"'";
            
            Statement cari=Koneksidb.createStatement();
            ResultSet data=cari.executeQuery(sqlcari);
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Kategori Perkara sudah terdaftar");
            } else {
                Statement perintah=Koneksidb.createStatement();
                perintah.execute(sqlsimpan);
                JOptionPane.showMessageDialog(null, "Data Kategori Perkara berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void simpanKategoriPerkara02(String id, String nama_perkara, String jenis_perkara, String deskripsi){
        try {
            String sqlsimpan="INSERT INTO kategori_perkara (id, nama_perkara, jenis_perkara, deskripsi) VALUES (?, ?, ?, ?)";
            String sqlcari= "SELECT*FROM kategori_perkara WHERE id = ?";
            
            PreparedStatement cari = Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, id);
            ResultSet data = cari.executeQuery();
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Kategori Perkara sudah terdaftar");
                this.duplikasi = true;
                this.CEK_NAMA_PERKARA = data.getString("nama_perkara");
                this.CEK_JENIS_PERKARA_KAT = data.getString("jenis_perkara");
                this.CEK_DESKRIPSI_PERKARA = data.getString("deskripsi");
            } else {
                this.duplikasi = false;
                this.CEK_NAMA_PERKARA = null;
                this.CEK_JENIS_PERKARA_KAT = null;
                this.CEK_DESKRIPSI_PERKARA = null;
                
                PreparedStatement perintah = Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, id);
                perintah.setString(2, nama_perkara);
                perintah.setString(3, jenis_perkara);
                perintah.setString(4, deskripsi);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Kategori Perkara berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahKategoriPerkara(String id, String nama_perkara, String jenis_perkara, String deskripsi){
        try {
            String sqlubah="UPDATE kategori_perkara SET nama_perkara = ?, jenis_perkara = ?, deskripsi = ? WHERE id = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlubah);
            perintah.setString(1, nama_perkara);
            perintah.setString(2, jenis_perkara);
            perintah.setString(3, deskripsi);
            perintah.setString(4, id);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Kategori Perkara berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusKategoriPerkara(String id){
        try {
            String sqlhapus="DELETE FROM kategori_perkara WHERE id = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlhapus);
            perintah.setString(1, id);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Kategori Perkara berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void tampilDataKategoriPerkara(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            
            modeltabel.addColumn("ID");
            modeltabel.addColumn("Nama Perkara");
            modeltabel.addColumn("Jenis Perkara");
            modeltabel.addColumn("Deskripsi");
            
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
        }
    }

    public void simpanPerkara01(String id, String jenis_perkara, String no_perkara, String kategori_perkara_id_kategori, String tgl_mulai, String tgl_selesai, String status, String klien_id_klien, String nama_terdakwa, String pengacara_id_pengacara, String keterangan, String dokumen, String surat_kuasa){
        try {
            String sqlsimpan="insert into perkara(id, jenis_perkara, no_perkara, kategori_perkara_id_kategori, tgl_mulai, tgl_selesai, status, klien_id_klien, nama_terdakwa, pengacara_id_pengacara, keterangan, dokumen, surat_kuasa) value"
                    + " ('"+id+"', '"+jenis_perkara+"', '"+no_perkara+"', '"+kategori_perkara_id_kategori+"', '"+tgl_mulai+"', '"+tgl_selesai+"', '"+status+"', '"+klien_id_klien+"', '"+nama_terdakwa+"', '"+pengacara_id_pengacara+"', '"+keterangan+"', '"+dokumen+"', '"+surat_kuasa+"')";
            String sqlcari="select*from perkara where id='"+id+"'";
            
            Statement cari=Koneksidb.createStatement();
            ResultSet data=cari.executeQuery(sqlcari);
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Perkara sudah terdaftar");
            } else {
                Statement perintah=Koneksidb.createStatement();
                perintah.execute(sqlsimpan);
                JOptionPane.showMessageDialog(null, "Data Perkara berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void simpanPerkara02(String id, String jenis_perkara, String no_perkara, String kategori_perkara_id_kategori, String tgl_mulai, String tgl_selesai, String status, String klien_id_klien, String nama_terdakwa, String pengacara_id_pengacara, String keterangan, String dokumen, String surat_kuasa){
        try {
            String sqlsimpan="INSERT INTO perkara (id, jenis_perkara, no_perkara, kategori_perkara_id_kategori, tgl_mulai, tgl_selesai, status, klien_id_klien, nama_terdakwa, pengacara_id_pengacara, keterangan, dokumen, surat_kuasa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sqlcari= "SELECT*FROM perkara WHERE id = ?";
            
            PreparedStatement cari = Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, id);
            ResultSet data = cari.executeQuery();
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Perkara sudah terdaftar");
                this.duplikasi = true;
                this.CEK_JENIS_PERKARA = data.getString("jenis_perkara");
                this.CEK_NO_PERKARA = data.getString("no_perkara");
                this.CEK_KATEGORI_ID = data.getString("kategori_perkara_id_kategori");
                this.CEK_TGL_MULAI = data.getString("tgl_mulai");
                this.CEK_TGL_SELESAI = data.getString("tgl_selesai");
                this.CEK_STATUS_PERKARA = data.getString("status");
                this.CEK_KLIEN_ID = data.getString("klien_id_klien");
                this.CEK_NAMA_TERDAKWA = data.getString("nama_terdakwa");
                this.CEK_PENGACARA_ID = data.getString("pengacara_id_pengacara");
                this.CEK_KETERANGAN = data.getString("keterangan");
                this.CEK_DOKUMEN = data.getString("dokumen");
                this.CEK_SURAT_KUASA = data.getString("surat_kuasa");
            } else {
                this.duplikasi = false;
                this.CEK_JENIS_PERKARA = null;
                this.CEK_NO_PERKARA = null;
                this.CEK_KATEGORI_ID = null;
                this.CEK_TGL_MULAI = null;
                this.CEK_TGL_SELESAI = null;
                this.CEK_STATUS_PERKARA = null;
                this.CEK_KLIEN_ID = null;
                this.CEK_NAMA_TERDAKWA = null;
                this.CEK_PENGACARA_ID = null;
                this.CEK_KETERANGAN = null;
                this.CEK_DOKUMEN = null;
                this.CEK_SURAT_KUASA = null;
                
                PreparedStatement perintah = Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, id);
                perintah.setString(2, jenis_perkara);
                perintah.setString(3, no_perkara);
                perintah.setString(4, kategori_perkara_id_kategori);
                perintah.setString(5, tgl_mulai);
                perintah.setString(6, tgl_selesai);
                perintah.setString(7, status);
                perintah.setString(8, klien_id_klien);
                perintah.setString(9, nama_terdakwa);
                perintah.setString(10, pengacara_id_pengacara);
                perintah.setString(11, keterangan);
                perintah.setString(12, dokumen);
                perintah.setString(13, surat_kuasa);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Perkara berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahPerkara(String id, String jenis_perkara, String no_perkara, String kategori_perkara_id_kategori, String tgl_mulai, String tgl_selesai, String status, String klien_id_klien, String nama_terdakwa, String pengacara_id_pengacara, String keterangan, String dokumen, String surat_kuasa){
        try {
            String sqlubah="UPDATE perkara SET jenis_perkara = ?, no_perkara = ?, kategori_perkara_id_kategori = ?, tgl_mulai = ?, tgl_selesai = ?, status = ?, klien_id_klien = ?, nama_terdakwa = ?, pengacara_id_pengacara = ?, keterangan = ?, dokumen = ?, surat_kuasa = ? WHERE id = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlubah);
            perintah.setString(1, jenis_perkara);
            perintah.setString(2, no_perkara);
            perintah.setString(3, kategori_perkara_id_kategori);
            perintah.setString(4, tgl_mulai);
            perintah.setString(5, tgl_selesai);
            perintah.setString(6, status);
            perintah.setString(7, klien_id_klien);
            perintah.setString(8, nama_terdakwa);
            perintah.setString(9, pengacara_id_pengacara);
            perintah.setString(10, keterangan);
            perintah.setString(11, dokumen);
            perintah.setString(12, surat_kuasa);
            perintah.setString(13, id); 
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Perkara berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusPerkara(String id){
        try {
            String sqlhapus="DELETE FROM perkara WHERE id = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlhapus);
            perintah.setString(1, id);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Perkara berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void tampilDataPerkara(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            
            modeltabel.addColumn("ID");
            modeltabel.addColumn("Jenis Perkara");
            modeltabel.addColumn("No Perkara");
            modeltabel.addColumn("ID Kategori");
            modeltabel.addColumn("Tgl Mulai");
            modeltabel.addColumn("Tgl Selesai");
            modeltabel.addColumn("Status");
            modeltabel.addColumn("ID Klien");
            modeltabel.addColumn("Nama Terdakwa");
            modeltabel.addColumn("ID Pengacara");
            modeltabel.addColumn("Keterangan");
            modeltabel.addColumn("Dokumen");
            modeltabel.addColumn("Surat Kuasa");
            
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
        }
    } 
    
        public void cetaklaporan(String filelaporan, String sql){
        try {
            File file = new File(filelaporan);
            JasperDesign jasdes = JRXmlLoader.load(file);
            JRDesignQuery query = new JRDesignQuery();
            query.setText(sql);
            jasdes.setQuery(query);
            JasperReport jr = JasperCompileManager.compileReport(jasdes);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, this.Koneksidb);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
} 
    
