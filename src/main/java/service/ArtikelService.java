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

public class ArtikelService {
    private DatabaseConnection db;

    public ArtikelService() {
        db = new DatabaseConnection();
    }

    public ResultSet getAllArtikel() {
        db.openConnection();
       
        String query = "SELECT a.id, a.judul, IFNULL(u.nama, 'Admin/Anonim') AS penulis, a.status, a.isi " +
                       "FROM tabel_artikel a " +
                       "LEFT JOIN tabel_user u ON a.mentor_id = u.id " +
                       "WHERE a.status = 'Approved'";
        return db.getData(query);
    }

    
    public ResultSet cariArtikel(String keyword) {
        db.openConnection();
        String query = "SELECT a.id, a.judul, IFNULL(u.nama, 'Admin/Anonim') AS penulis, a.status, a.isi " +
                       "FROM tabel_artikel a " +
                       "LEFT JOIN tabel_user u ON a.mentor_id = u.id " +
                       "WHERE a.status = 'Approved' AND a.judul LIKE '%" + keyword + "%'";
        return db.getData(query);
    }
    
    public java.sql.ResultSet getArtikelPending() {
        db.openConnection();
        String query = "SELECT a.id, a.judul, u.nama AS penulis, a.isi " +
                       "FROM tabel_artikel a " +
                       "LEFT JOIN tabel_user u ON a.mentor_id = u.id " +
                       "WHERE a.status = 'Pending'";
        return db.getData(query);
    }

    public boolean approveArtikel(String idArtikel) {
        try {
            db.openConnection();
            String query = "UPDATE tabel_artikel SET status = 'Approved' WHERE id = " + idArtikel;
            
            if (db.executeStatement(query).equals("Success")) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error approve artikel: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return false;
    }
}