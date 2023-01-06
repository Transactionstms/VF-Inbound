/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import com.management.util.Loggin;
import com.onest.util.PropertiesUtil;
import java.sql.*;

/**
 *
 * @author SergioCastro
 */
public class Conexion {
    Connection conn = null;

    public Connection conectar() {

        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            conn = DriverManager.getConnection(PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_URL),
                    PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_USER), PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_PASSWORD));
        } catch (Exception exc) {
            exc.printStackTrace(System.err);
            Loggin.error("Error al tratar de abrir la base de Datos", exc);
        }

        return conn;
    }

    
    public Connection getConexion(){
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
                Loggin.error(e);
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
