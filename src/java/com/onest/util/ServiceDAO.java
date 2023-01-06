/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SergioCastro
 */
public class ServiceDAO extends  Conexion {
    
     java.sql.Statement stmt;
     Connection con ;
    
    public ResultSet consulta( String sql ) throws SQLException{
        con = this.conectar();
        java.sql.Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);
             
        return res;
    }
    public boolean save( String sql ) {
        
        boolean bandera = false;
              
        try {
             con = this.conectar();
           java.sql.Statement stmt = con.createStatement();
        stmt.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
    
    public boolean update( String sql ) {
        
        boolean bandera = false;
              
        try {
            con = this.conectar();
           java.sql.Statement stmt = con.createStatement();
        stmt.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
    
    
    
    
    
    
    
    
    
    
    
    public void desconectarService(ResultSet res){
        this.desconectar(con, stmt, res);
    }
   
}
