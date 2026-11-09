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

public class FavoritService {
    private DatabaseConnection db;

    public FavoritService() {
        db = new DatabaseConnection();
    }

    // Method untuk menyimpan ikan ke tabel favorit
    public String simpanIkan(String idIkan) {
        int myId = UserSession.getUserId(); 
        
        try {
            db.openConnection();
            
            String cekQuery = "SELECT id FROM tabel_ikan_favorit WHERE user_id = " + myId + " AND ikan_id = " + idIkan;
            java.sql.ResultSet rs = db.getData(cekQuery);
            
            if (rs != null && rs.next()) {
                return "SUDAH_ADA"; 
            }
            
            String insertQuery = "INSERT INTO tabel_ikan_favorit (user_id, ikan_id) VALUES (" + myId + ", " + idIkan + ")";
            String status = db.executeStatement(insertQuery);
            
            if (status.equals("Success")) {
                return "SUKSES";
            }
            
        } catch (Exception e) {
            System.out.println("Error simpan ikan: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        
        return "GAGAL";
    }
    
    public String simpanPostingan(String idPostingan) {
        int myId = service.UserSession.getUserId(); 
        try {
            db.openConnection();
            
            // 1. Cek apakah postingan sudah pernah disimpan
            String cekQuery = "SELECT id FROM tabel_postingan_disimpan WHERE user_id = " + myId + " AND postingan_id = " + idPostingan;
            java.sql.ResultSet rs = db.getData(cekQuery);
            if (rs != null && rs.next()) {
                return "SUDAH_ADA"; 
            }
            
            // 2. Simpan jika belum ada
            String insertQuery = "INSERT INTO tabel_postingan_disimpan (user_id, postingan_id) VALUES (" + myId + ", " + idPostingan + ")";
            if (db.executeStatement(insertQuery).equals("Success")) {
                return "SUKSES";
            }
        } catch (Exception e) {
            System.out.println("Error simpan postingan: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return "GAGAL";
    }
    
    public String simpanArtikel(String idArtikel) {
        int myId = UserSession.getUserId(); 
        try {
            db.openConnection();
            String cekQuery = "SELECT id FROM tabel_artikel_disimpan WHERE user_id = " + myId + " AND artikel_id = " + idArtikel;
            java.sql.ResultSet rs = db.getData(cekQuery);
            if (rs != null && rs.next()) {
                return "SUDAH_ADA"; 
            }
            
            String insertQuery = "INSERT INTO tabel_artikel_disimpan (user_id, artikel_id) VALUES (" + myId + ", " + idArtikel + ")";
            if (db.executeStatement(insertQuery).equals("Success")) {
                return "SUKSES";
            }
        } catch (Exception e) {
            System.out.println("Error simpan artikel: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return "GAGAL";
    }
    
    public String hapusArtikel(String idArtikel) {
        int myId = service.UserSession.getUserId();
        try {
            db.openConnection();
            String query = "DELETE FROM tabel_artikel_disimpan WHERE user_id = " + myId + " AND artikel_id = " + idArtikel;
            
            if (db.executeStatement(query).equals("Success")) {
                return "SUKSES";
            }
        } catch (Exception e) {
            System.out.println("Error hapus artikel: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return "GAGAL";
    }
    
    // 1. MESIN UNTUK MENGHAPUS IKAN FAVORIT
    public String hapusIkan(String idIkan) {
        int myId = service.UserSession.getUserId();
        try {
            db.openConnection();
            // Targetnya adalah tabel_ikan_favorit
            String query = "DELETE FROM tabel_ikan_favorit WHERE user_id = " + myId + " AND ikan_id = " + idIkan;
            
            if (db.executeStatement(query).equals("Success")) {
                return "SUKSES";
            }
        } catch (Exception e) {
            System.out.println("Error hapus ikan favorit: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return "GAGAL";
    }


    public String hapusPostingan(String idPostingan) {
        int myId = service.UserSession.getUserId();
        try {
            db.openConnection();

            String query = "DELETE FROM tabel_postingan_disimpan WHERE user_id = " + myId + " AND postingan_id = " + idPostingan;
            
            if (db.executeStatement(query).equals("Success")) {
                return "SUKSES";
            }
        } catch (Exception e) {
            System.out.println("Error hapus postingan disimpan: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return "GAGAL";
    }
    
    public boolean cekApakahSudahDisimpan(String idArtikel) {
        boolean sudahAda = false;
        try {
            db.openConnection();

            int userId = service.UserSession.getUserId(); 
            
            String query = "SELECT * FROM tabel_artikel_disimpan WHERE artikel_id = " + idArtikel + " AND user_id = " + userId;
            java.sql.ResultSet rs = db.getData(query);
            
            if (rs != null && rs.next()) {
                sudahAda = true; 
            }
        } catch (Exception e) {
            System.out.println("Error cek favorit: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return sudahAda;
    }
}
