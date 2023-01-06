/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pantalla;

import com.conexion.Conexion;
import com.excel.pojos.Celda;
import com.excel.pojos.Plantilla;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Omar
 */
public class ScriptPantalla {

    public static CallableStatement getPantalla(Conexion conexion, int idPantalla, int idLenguaje) throws SQLException {
        try {
            CallableStatement sp = null;
            sp = (CallableStatement) conexion.getConexion().prepareCall("{ call SPCNPANTALLA_ETIQUETAS(?,?,?) }");
            sp.setInt(1, idPantalla);
            sp.setInt(2, idLenguaje);
            sp.registerOutParameter(3, OracleTypes.CURSOR);
            return sp;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
