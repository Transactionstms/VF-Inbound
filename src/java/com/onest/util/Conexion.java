/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.util;

import com.management.util.Loggin;
import java.sql.*;

/**
 *
 * @author SergioCastro
 */
public class Conexion {

    Connection conn = null;
    private String user = "";
    private String password = "";

    public Connection conectar() {
         try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            conn = DriverManager.getConnection(PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_URL),
                    PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_USER), PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_PASSWORD));
        } catch (Exception exc) {
            exc.printStackTrace(System.err);
            Loggin.error("Error al tratar de abrir la base de Datos", exc);
        }
           /*
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "HILTI", "HILTIXXX");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@transactions.mx:1521:orcl", "HILTI", "HILTIXXX");
        } catch (Exception exc) {
            System.out.println("Error al tratar de abrir la base de Datos");
        }
        */

        return conn;
    }

    public Connection CerrarConexion() throws SQLException {
        conn.close();
        conn = null;
        return conn;
    }
    
    
    public   void desconectar(Connection con , Statement stm, ResultSet res){
		if(res != null){
			try{
				res.close();
				res = null;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(stm != null){
			try{
				stm.close();
				stm = null;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(con != null){
			try{
				con.close();
				con = null;
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
    }

}
