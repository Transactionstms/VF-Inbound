/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.dao;

import com.tacts.model.CustomModel;
import com.tacts.sql.Conexion;
import java.sql.CallableStatement;

/**
 *
 * @author Desarrollo Tacts
 */
public class CustomsSql {

    public static CallableStatement consultarCustomRADAR(Conexion conexion, CustomModel objeto, String agenteId)throws Exception{
        try {
            CallableStatement sp = conexion.getConexion().prepareCall("{ call SP_INB_CUSTOMS_WSEXP_RADAR(?,?) }");
            sp.setString("pAgenteAduanal", agenteId);
            sp.registerOutParameter("resultado", -10);
            return sp; 
        } catch (Exception exception) {
            System.out.println("--"+exception);
            return null; 
        }
    }
    
    public static CallableStatement consultarCustomLOGIX(Conexion conexion, CustomModel objeto, String agenteId)throws Exception{
        try {
            CallableStatement sp = conexion.getConexion().prepareCall("{ call SP_INB_CUSTOMS_WSEXP_LOGIX(?,?) }");
            sp.setString("pAgenteAduanal", agenteId);
            sp.registerOutParameter("resultado", -10);
            return sp; 
        } catch (Exception exception) {
            System.out.println("--"+exception);
            return null; 
        }
    }
    
}
