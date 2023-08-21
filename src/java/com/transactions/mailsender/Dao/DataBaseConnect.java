/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transactions.mailsender.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author luis_
 */
public class DataBaseConnect {

    Connection conn = null;
    String url = "jdbc:oracle:thin:@74.208.140.125:1521:tacts125";
    String user = "VANS39TEST";
    String pass = "UKidn49N875RBH54Cq2";


    public Connection conectar() {

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn = DriverManager.getConnection(url,
                    user, pass);
        } catch (Exception exc) {
            exc.printStackTrace(System.err);
        }

        return conn;
    }

    public Connection getConexion() {
        return this.conn;
    }

    public Connection CerrarConexion() throws SQLException {
        conn.close();
        conn = null;
        return conn;
    }

    public void desconectar(Connection con, Statement stm, ResultSet res) {
        if (res != null) {
            try {
                res.close();
                res = null;
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        if (stm != null) {
            try {
                stm.close();
                stm = null;
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }

        }
    }
}
