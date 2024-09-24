/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacts.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author User_Windows10
 */

public class ConnectionPool {

    private static final int MAX_CONNECTIONS = 5; // Número máximo de conexiones en el pool
    private static final String DB_URL = "jdbc:oracle:thin:@74.208.140.125:1521:tacts125"; // Cambia a la URL de tu base de datos
    private static final String DB_USER = "VANS39TEST";
    private static final String DB_PASSWORD = "XUKidn49N875RBH54Cq2";

    private Queue<Connection> connectionPool = new LinkedList<>();
    private int currentConnections = 0;

    // Registro estático del driver
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // Cambia esto si usas otro driver
            System.out.println("Driver registrado exitosamente");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Obtiene una conexión del pool
    public synchronized Connection getConnection() throws SQLException {
        if (!connectionPool.isEmpty()) {
            return connectionPool.poll(); // Retira la primera conexión disponible
        } else if (currentConnections < MAX_CONNECTIONS) {
            // Si no hay conexiones disponibles y no se ha alcanzado el límite, crea una nueva conexión
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            currentConnections++;
            return conn;
        } else {
            throw new SQLException("El pool de conexiones ha alcanzado su límite máximo.");
        }
    }

    // Devuelve una conexión al pool
    public synchronized void releaseConnection(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    connectionPool.offer(conn); // Añade la conexión al final del pool
                } else {
                    currentConnections--; // Si la conexión está cerrada, decrementa el contador
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Cierra todas las conexiones del pool
    public synchronized void closeAllConnections() throws SQLException {
        while (!connectionPool.isEmpty()) {
            Connection conn = connectionPool.poll();
            if (conn != null && !conn.isClosed()) {
                conn.close(); // Cierra cada conexión
            }
        }
    }

}
