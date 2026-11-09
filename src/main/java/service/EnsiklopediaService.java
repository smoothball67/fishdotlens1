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

public class EnsiklopediaService {
    private DatabaseConnection db;

    public EnsiklopediaService() {
        db = new DatabaseConnection();
    }

    public ResultSet getIkanPopuler() {
        db.openConnection();
        String query = "SELECT nama_spesies, habitat FROM tabel_ikan LIMIT 5";
        return db.getData(query);
    }
    
    public ResultSet getAllIkan() {
        db.openConnection();
        
        String query = "SELECT id, nama_spesies, habitat, pakan, penyakit FROM tabel_ikan";
        
        return db.getData(query);
    }
    
    public java.sql.ResultSet cariIkan(String keyword) {
        db.openConnection();
        
        String query = "SELECT id, nama_spesies, habitat, pakan, penyakit FROM tabel_ikan " +
                       "WHERE nama_spesies LIKE '%" + keyword + "%' " +
                       "OR habitat LIKE '%" + keyword + "%' " +
                       "OR penyakit LIKE '%" + keyword + "%'";
                       
        return db.getData(query);
    }
    
    public boolean tambahIkan(String nama, String habitat, String pakan, String penyakit) {
        try {
            db.openConnection();
            String query = "INSERT INTO tabel_ikan (nama_spesies, habitat, pakan, penyakit) " +
                           "VALUES ('" + nama + "', '" + habitat + "', '" + pakan + "', '" + penyakit + "')";
            
            if (db.executeStatement(query).equals("Success")) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error tambah ikan: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return false;
    }

    public boolean hapusIkanPermanen(String idIkan) {
        try {
            db.openConnection();
            String bersihkanFavorit = "DELETE FROM tabel_ikan_favorit WHERE ikan_id = " + idIkan;
            db.executeStatement(bersihkanFavorit);
            
            String hapusUtama = "DELETE FROM tabel_ikan WHERE id = " + idIkan;
            if (db.executeStatement(hapusUtama).equals("Success")) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error hapus ikan permanen: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return false;
    }
}
