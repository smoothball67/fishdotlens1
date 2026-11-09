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

public class LoginService {
    private DatabaseConnection db;

    public LoginService() {
        db = new DatabaseConnection();
    }

    public String prosesLogin(String email, String password) {
        String role = null;
        try {
            db.openConnection();
            String query = "SELECT id, role FROM tabel_user WHERE email = '" + email + "' AND password = '" + password + "'";
            java.sql.ResultSet rs = db.getData(query);
            
            if (rs.next()) {
                role = rs.getString("role");
                
                int idYangLogin = rs.getInt("id");
                UserSession.setUserId(idYangLogin); 
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return role;
    }
}
