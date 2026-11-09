/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author 7320
 */

import database.DatabaseConnection;
import java.sql.ResultSet;

public class ForumService {
    private DatabaseConnection db;

    public ForumService() {
        db = new DatabaseConnection();
    }

    public ResultSet getPostinganTerbaru() {
        db.openConnection();
        
        String query = "SELECT tabel_postingan.judul, tabel_user.nama " +
                       "FROM tabel_postingan " +
                       "JOIN tabel_user ON tabel_postingan.user_id = tabel_user.id " +
                       "ORDER BY tabel_postingan.id DESC LIMIT 5";
                       
        return db.getData(query);
    }
    
    public java.sql.ResultSet getAllPostinganAdmin() {
        db.openConnection();
        
        String query = "SELECT p.id, p.judul, u.nama AS pengguna, p.tanggal " +
                       "FROM tabel_postingan p " +
                       "LEFT JOIN tabel_user u ON p.user_id = u.id " +
                       "ORDER BY p.tanggal DESC"; 
                       
        return db.getData(query);
    }
    
    public java.sql.ResultSet getAllPostingan() {
        db.openConnection();
        String query = "SELECT tabel_postingan.id, tabel_postingan.judul, tabel_user.nama, tabel_postingan.tanggal " +
                       "FROM tabel_postingan " +
                       "JOIN tabel_user ON tabel_postingan.user_id = tabel_user.id " +
                       "ORDER BY tabel_postingan.id DESC";
        return db.getData(query);
    }

    public boolean buatPostingan(String judul, String isi) {
        int userId = service.UserSession.getUserId(); 
        
        String tanggal = java.time.LocalDate.now().toString(); 
        
        db.openConnection();
        String query = "INSERT INTO tabel_postingan (judul, isi, user_id, tanggal) " +
                       "VALUES ('" + judul + "', '" + isi + "', " + userId + ", '" + tanggal + "')";
                       
        String status = db.executeStatement(query);
        db.closeConnection();
        
        return status.equals("Success");
    }

    public boolean hapusPostinganPermanen(String idPostingan) {
        db.openConnection();
        try {
            String bersihkanFavorit = "DELETE FROM tabel_postingan_disimpan WHERE postingan_id = " + idPostingan;
            db.executeStatement(bersihkanFavorit);
            
            String queryHapusUtama = "DELETE FROM tabel_postingan WHERE id = " + idPostingan;
            
            if (db.executeStatement(queryHapusUtama).equals("Success")) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error hapus postingan permanen: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return false;
    }

    public boolean cekApakahPostinganDisimpan(String idPostingan) {
        boolean sudahAda = false;
        try {
            db.openConnection();
            int userId = service.UserSession.getUserId();
            
            String query = "SELECT * FROM tabel_postingan_disimpan WHERE postingan_id = " + idPostingan + " AND user_id = " + userId;
            java.sql.ResultSet rs = db.getData(query);
            
            if (rs != null && rs.next()) {
                sudahAda = true;
            }
        } catch (Exception e) {
            System.out.println("Error cek favorit forum: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return sudahAda;
    }
}
