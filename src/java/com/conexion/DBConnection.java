/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author User_Windows10
 */
public class DBConnection {
    private static final String URL = "jdbc:oracle:thin:@74.208.140.125:1521:tacts125";
    private static final String USER = "VANS39TEST";
    private static final String PASSWORD = "XUKidn49N875RBH54Cq2";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
