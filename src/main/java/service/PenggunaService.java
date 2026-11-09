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

public class PenggunaService {
    private DatabaseConnection db;

    public PenggunaService() {
        db = new DatabaseConnection();
    }

    public ResultSet getAllPengguna() {
        db.openConnection();
        return db.getData("SELECT id, nama, email, role, keahlian, tingkat_keanggotaan, tanggal_daftar FROM tabel_user");
    }

    public ResultSet cariPengguna(String keyword) {
        db.openConnection();
        
        String query = "SELECT id, nama, email, role, keahlian, tingkat_keanggotaan, tanggal_daftar " +
                       "FROM tabel_user " +
                       "WHERE LOWER(nama) LIKE LOWER('%" + keyword + "%')";
                       
        return db.getData(query);
    }

    public boolean ubahTingkat(String idUser, String tingkatBaru) {
        try {
            db.openConnection();
            String query = "UPDATE tabel_user SET tingkat_keanggotaan = '" + tingkatBaru + "' WHERE id = " + idUser;
            if (db.executeStatement(query).equals("Success")) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error ubah tingkat: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return false;
    }
}
