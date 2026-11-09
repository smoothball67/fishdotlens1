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
import java.sql.SQLException;

public class DashboardService {
    private DatabaseConnection db;

    public DashboardService() {
        db = new DatabaseConnection();
    }

    public String getTotalIkan() {
        String total = "0";
        int myId = UserSession.getUserId(); 
        
        try {
            db.openConnection();
            ResultSet rs = db.getData("SELECT COUNT(*) AS total FROM tabel_ikan_favorit WHERE user_id = " + myId);
            if (rs.next()) {
                total = rs.getString("total");
            }
        } catch (SQLException e) {
            System.out.println("Error hitung ikan: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return total;
    }

    public String getTotalArtikelFavorit() {
        String total = "0";
        int myId = service.UserSession.getUserId(); 
        try {
            db.openConnection();
            String query = "SELECT COUNT(*) AS total FROM tabel_artikel_disimpan WHERE user_id = " + myId;
            java.sql.ResultSet rs = db.getData(query);
            if (rs != null && rs.next()) {
                total = rs.getString("total");
            }
        } catch (Exception e) {
            System.out.println("Error hitung artikel favorit: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return total;
    }

    public String getTotalPostinganFavorit() {
        String total = "0";
        int myId = service.UserSession.getUserId(); 
        try {
            db.openConnection();

            String query = "SELECT COUNT(*) AS total FROM tabel_postingan_disimpan WHERE user_id = " + myId;
            java.sql.ResultSet rs = db.getData(query);
            
            if (rs != null && rs.next()) {
                total = rs.getString("total");
            }
        } catch (Exception e) {
            System.out.println("Error hitung postingan favorit: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return total;
    }
    
    // 1. Menghitung Total Pengguna
    public String getTotalPengguna() {
        String total = "0";
        try {
            db.openConnection();
            java.sql.ResultSet rs = db.getData("SELECT COUNT(*) AS total FROM tabel_user");
            if (rs != null && rs.next()) total = rs.getString("total");
        } catch (Exception e) { System.out.println("Error total pengguna: " + e.getMessage()); } 
        finally { db.closeConnection(); }
        return total;
    }

    public String getArtikelPending() {
        String total = "0";
        try {
            db.openConnection();
            java.sql.ResultSet rs = db.getData("SELECT COUNT(*) AS total FROM tabel_artikel WHERE status = 'Pending'");
            if (rs != null && rs.next()) total = rs.getString("total");
        } catch (Exception e) { System.out.println("Error artikel pending: " + e.getMessage()); } 
        finally { db.closeConnection(); }
        return total;
    }


    public String getTotalIkanSemua() {
        String total = "0";
        try {
            db.openConnection();

            java.sql.ResultSet rs = db.getData("SELECT COUNT(*) AS total FROM tabel_ikan");
            
            if (rs != null && rs.next()) {
                total = rs.getString("total");
            }
        } catch (Exception e) { 
            System.out.println("Error total ikan: " + e.getMessage()); 
        } finally { 
            db.closeConnection(); 
        }
        return total;
    }

    public java.sql.ResultSet getPendaftarTerbaru() {
        db.openConnection();
        
        return db.getData("SELECT nama, email, role, tingkat_keanggotaan, tanggal_daftar FROM tabel_user ORDER BY id DESC LIMIT 5");
    }
}