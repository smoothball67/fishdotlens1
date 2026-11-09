/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author 7320
 */

public class UserSession {
    // Variabel statis untuk menyimpan ID user yang sedang login
    private static int userId;

    // Method untuk mengambil ID
    public static int getUserId() {
        return userId;
    }

    // Method untuk menyimpan ID saat login berhasil
    public static void setUserId(int id) {
        userId = id;
    }
}
