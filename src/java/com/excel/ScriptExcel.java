/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excel;

import com.conexion.Conexion;
import com.excel.pojos.Celda;
import com.excel.pojos.Plantilla;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Omar
 */
public class ScriptExcel {

    public static CallableStatement getPlantilla(Conexion conexion, int idPlantilla, int idLenguaje) throws SQLException {
        try {
            CallableStatement sp = null;
            sp = (CallableStatement) conexion.getConexion().prepareCall("{ call SPCNXLS_PLANTILLAS(?,?,?) }");
            sp.setInt(1, idPlantilla);
            sp.setInt(2, idLenguaje);
            sp.registerOutParameter(3, OracleTypes.CURSOR);
            return sp;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static CallableStatement setPlantilla(Conexion conexion, Plantilla plantilla, List<Celda> celdas, int idLenguaje, int idDivision, int idBodega) throws SQLException {
        try {
            CallableStatement sp = null;
            String script = "{ call " + plantilla.getStored();
            String parametros = "(";
            for (int i = 0; i < plantilla.getCeldas().size(); i++) {
                parametros += "?";
                if (i + 1 < plantilla.getCeldas().size()) {
                    parametros += ",";
                } else {
                    parametros += ",?,?,?,?)}";
                }
            }
            sp = (CallableStatement) conexion.getConexion().prepareCall(script += parametros);
            int contador = 1;
            for (int i = 0; i < celdas.size(); i++) {
                switch (celdas.get(i).getIdTipoDato()) {
                    case 4://Double
                        sp.setDouble(contador, Double.parseDouble(celdas.get(i).getValor()));
                        break;
                    case 2://int
                        sp.setInt(contador, Integer.parseInt(celdas.get(i).getValor()));
                        break;
                    case 1://String
                        sp.setString(contador, celdas.get(i).getValor());
                        break;                    
                }
                contador++;
            }
            sp.setInt(contador, idLenguaje);
            contador++;
            sp.setInt(contador, idDivision);
            contador++;
            sp.setInt(contador, idBodega);
            contador++;
            sp.registerOutParameter(contador, OracleTypes.CURSOR);
            return sp;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

}
