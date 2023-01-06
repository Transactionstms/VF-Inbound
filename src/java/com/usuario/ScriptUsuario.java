/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuario;

import com.conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Omar
 */
public class ScriptUsuario {
    
    public static CallableStatement getUsuario(Conexion conexion,String usuario,String password,int idLenguaje) throws SQLException{
        try {
            CallableStatement sp = null;
            sp = (CallableStatement) conexion.getConexion().prepareCall("{ call SPCNUSUARIOMENU(?,?,?,?) }");
            sp.setString(1, usuario);
            sp.setString(2, password);             
            sp.setInt(3, idLenguaje);
            sp.registerOutParameter(4, OracleTypes.CURSOR);            
            return sp;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
    
    public static CallableStatement getBodegas(Conexion conexion,Usuario usuario) throws SQLException{
        try {
            CallableStatement sp = null;
            sp = (CallableStatement) conexion.getConexion().prepareCall("{ call SPCNCUENTAS(?,?) }");
            sp.setInt(1, usuario.getId());
            sp.registerOutParameter(2, OracleTypes.CURSOR);            
            return sp;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
    
    public static CallableStatement getLenguajes(Conexion conexion) throws SQLException{
        try {
            CallableStatement sp = null;
            sp = (CallableStatement) conexion.getConexion().prepareCall("{ call SPCNLENGUAJES(?) }");
            sp.registerOutParameter(1, OracleTypes.CURSOR);            
            return sp;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
    
}
