/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documento;

import com.conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Omar
 */
public class ScriptDocumento {
    public static CallableStatement getDocumentoEncabezado(Conexion conexion, String embarque, int idLenguaje) throws SQLException {
        try {
            CallableStatement sp = null;
            sp = (CallableStatement) conexion.getConexion().prepareCall("{ call SPCNDOCUMENTO_EMBARQUEENC(?,?,?) }");
            sp.setString(1, embarque);
            sp.setInt(2, idLenguaje);
            sp.registerOutParameter(3, OracleTypes.CURSOR);
            return sp;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
    
    public static CallableStatement getDocumentoDetalle(Conexion conexion, String agrupador, int idLenguaje) throws SQLException {
        try {
            CallableStatement sp = null;
            sp = (CallableStatement) conexion.getConexion().prepareCall("{ call SPCNDOCUMENTO_EMBARQUEDET(?,?,?) }");
            sp.setString(1, agrupador);
            sp.setInt(2, idLenguaje);
            sp.registerOutParameter(3, OracleTypes.CURSOR);
            return sp;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
    
     public static CallableStatement getDocumentoPie(Conexion conexion, String agrupador, int idLenguaje) throws SQLException {
        try {
            CallableStatement sp = null;
            sp = (CallableStatement) conexion.getConexion().prepareCall("{ call SPCNDOCUMENTO_EMBARQUEPIE(?,?,?) }");
            sp.setString(1, agrupador);
            sp.setInt(2, idLenguaje);
            sp.registerOutParameter(3, OracleTypes.CURSOR);
            return sp;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
