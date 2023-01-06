/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pantalla;

import com.conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author sergiocastros
 */
public class DaoPantalla {

    private final Conexion conexion;

    public DaoPantalla() {
        conexion = new Conexion();
    }

    public Pantalla getPantalla(int idPantalla, int idLenguaje) throws SQLException {
        CallableStatement cS = null;
        ResultSet rs = null;
        Pantalla pantalla = null;
        try {
            conexion.conectar();
            cS = ScriptPantalla.getPantalla(conexion,idPantalla, idLenguaje);
            cS.execute();
            rs = (ResultSet) cS.getObject(3);
            HashMap<Integer,Leyenda> etiquetas =  new HashMap<Integer,Leyenda>();
            int identificador=0;
            String descripcion="";
            String url="";
            while (rs.next()) {
                identificador=rs.getInt("ID");
                descripcion=rs.getString("DESCRIPCION");
                url=rs.getString("URL");
                etiquetas.put(rs.getInt("ID_LEYENDA"), new Leyenda(rs.getInt("ID_LEYENDA"), rs.getString("LEYENDA")));
            }
            if(identificador>0){
                pantalla = new Pantalla(identificador, descripcion, url, etiquetas);
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
        return pantalla;
    }

    
}
