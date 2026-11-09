/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author 7320
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseConnection {
    // Sesuaikan URL dengan nama database kita
    private final String URL = "jdbc:mysql://localhost:3306/db_fishlens";
    // Default username bawaan XAMPP adalah "root"
    private final String USERNAME = "root"; 
    // Default password bawaan XAMPP adalah kosong (tidak ada spasi)
    private final String PASSWORD = "";     
    
    private Connection connection;

    // Method untuk membuka koneksi ke database
    public void openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Koneksi Database Berhasil!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Koneksi Database Gagal: " + e.getMessage());
        }
    }

    // Method untuk menutup koneksi
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Koneksi Ditutup.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menutup koneksi: " + e.getMessage());
        }
    }

    // Method untuk mengambil data (SELECT)
    public ResultSet getData(String query) {
        ResultSet rs = null;
        try {
            if (connection == null || connection.isClosed()) {
                openConnection(); 
            }
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data: " + e.getMessage());
        }
        return rs;
    }

    // Method untuk mengeksekusi manipulasi data (INSERT, UPDATE, DELETE)
    public String executeStatement(String query) {
        try {
            if (connection == null || connection.isClosed()) {
                openConnection();
            }
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            return "Success";
        } catch (SQLException e) {
            System.out.println("Gagal eksekusi query: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }

    // Method Main HANYA untuk testing koneksi
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        db.openConnection();
        db.closeConnection();
    }
}