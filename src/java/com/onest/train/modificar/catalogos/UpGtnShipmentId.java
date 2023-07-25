/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.modificar.catalogos;

import com.google.gson.Gson;
import com.onest.oracle.Conexion;
import com.onest.oracle.Configuracion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Desarrollo Tacts
 */
public class UpGtnShipmentId {
    private Conexion cnBaseDeDatos;
    
    public UpGtnShipmentId() {
        Configuracion cfg = new Configuracion();
        this.cnBaseDeDatos = new Conexion(cfg.getSid(), cfg.getUsuario(), cfg.getPassword());
    }
    
    public String updateGtnShipmentId(String shipmentId)throws Exception{
        
        String res = null;
        CallableStatement sp1 = null;
        
        try {
            
           this.cnBaseDeDatos.Iniciar();
           sp1 = Upltd_gtnConShipmentId.ltdGtnShipmentId(this.cnBaseDeDatos,shipmentId);
           sp1.execute();
    
            this.cnBaseDeDatos.Cerrar(sp1);
            this.cnBaseDeDatos.Finalizar();
           
        } catch (SQLException exception) {
            System.out.println("--" + exception);
            return null;
        }
        
        return res;
    }
    
}
