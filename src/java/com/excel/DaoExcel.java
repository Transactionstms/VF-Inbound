/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excel;

import com.base.Respuesta;
import com.conexion.Conexion;
import com.excel.pojos.Celda;
import com.excel.pojos.Plantilla;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sergiocastros
 */
public class DaoExcel {

    private final Conexion conexion;

    public DaoExcel() {
        conexion = new Conexion();
    }

    public Plantilla getPlantilla(int idPlantilla, int idLenguaje) throws SQLException {
        CallableStatement cS = null;
        ResultSet rs = null;
        Plantilla plantilla = new Plantilla("", "");
        try {
            conexion.conectar();
            cS = ScriptExcel.getPlantilla(conexion, idPlantilla, idLenguaje);
            cS.execute();
            rs = (ResultSet) cS.getObject(3);
            List<Celda> celdas = new ArrayList<Celda>();
            while (rs.next()) {
                plantilla.setNombre(rs.getString("NOMBRE"));
                plantilla.setStored(rs.getString("STORED"));
                plantilla.setCeldas(new Celda(rs.getString("CELDA"), rs.getString("DESCRIPCION"), rs.getInt("ID_TIPO"), rs.getString("TIPO"), rs.getInt("REQUERIDO")));
            }
            if (cS != null) {
                cS.close();
                rs.close();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            if (cS != null) {
                cS.close();
                rs.close();
            }
            conexion.CerrarConexion();

        } finally {
            if (cS != null) {
                cS.close();
                rs.close();
            }
            conexion.CerrarConexion();

        }
        if (plantilla.getNombre().equals("")) {
            plantilla = null;
            
        }
        return plantilla;
    }

    public Respuesta setPlantilla(Plantilla plantilla, List<Celda> celdas,int idLenguaje,int idDivision,int idBodega) throws SQLException {
        CallableStatement cS = null;
        ResultSet rs = null;
        Respuesta respuesta = new Respuesta();
        try {
            conexion.conectar();
            cS = ScriptExcel.setPlantilla(conexion, plantilla, celdas,idLenguaje,idDivision,idBodega);
            cS.execute();
            rs = (ResultSet) cS.getObject(celdas.size()+4);
            while (rs.next()) {
                respuesta.setErrorSql(rs.getString("ErrorSql"));
                respuesta.setMensaje(rs.getString("Mensaje"));
                respuesta.setProceso(rs.getInt("Proceso"));
            }
            if (cS != null) {
                cS.close();
                rs.close();
            }

        } catch (Exception ex) {
                respuesta.setErrorSql("<B style=\"color: red;\">[ERROR]</B>");
                respuesta.setMensaje(ex.getMessage());
                respuesta.setProceso(0);
        } finally {
            if(conexion!=null)
                conexion.CerrarConexion();                
                if (cS != null) {
                cS.close();
                rs.close();
            }

        }

        return respuesta;
    }
}
