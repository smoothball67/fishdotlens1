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

public class ProfilService {
    private DatabaseConnection db;

    public ProfilService() {
        db = new DatabaseConnection();
    }

   public java.sql.ResultSet getProfilUser() {
        int myId = service.UserSession.getUserId(); 
        db.openConnection();
        
        String query = "SELECT nama, email, role, tingkat_keanggotaan, tanggal_daftar FROM tabel_user WHERE id = " + myId;
        
        return db.getData(query);
    }
    
    public boolean updateNama(String namaBaru) {
        int myId = service.UserSession.getUserId(); 
        
        try {
            db.openConnection();

            String query = "UPDATE tabel_user SET nama = '" + namaBaru + "' WHERE id = " + myId;
            
            String status = db.executeStatement(query);
            if (status.equals("Success")) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error update nama profil: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return false;
    }
}
