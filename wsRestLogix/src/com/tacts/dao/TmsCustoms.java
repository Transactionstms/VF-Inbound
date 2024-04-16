/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.dao;

import com.tacts.sql.Conexion;
import com.tacts.sql.Configuracion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import wsRestLogix.ConsumowsLogix;

/**
 *
 * @author Desarrollo Tacts
 */
public class TmsCustoms {
    
    private Conexion cnBaseDeDatos;
    
    public TmsCustoms() {
        Configuracion cfg = new Configuracion();
        this.cnBaseDeDatos = new Conexion(cfg.getSid(), cfg.getUsuario(), cfg.getPassword());
    }
    
    public String consultarCustomsLOGIX(String agenteId)throws Exception{
        
        String StringCustomLOGIX = null;
        CallableStatement sp1 = null;
        ConsumowsLogix Consumows=new ConsumowsLogix();
        String contenidoLOGIX = "";
        String shipmentId = "";
        
        try {
            
           this.cnBaseDeDatos.Iniciar();
           sp1 = CustomsSql.consultarCustomLOGIX(this.cnBaseDeDatos,agenteId);
           sp1.execute();
           
           ResultSet rs = (ResultSet)sp1.getObject("resultado");
           while (rs.next()) {
               shipmentId = rs.getString(6);
               contenidoLOGIX = Consumows.ConsumoLOGIX(shipmentId); 
           }
           
            this.cnBaseDeDatos.Cerrar(sp1);
            this.cnBaseDeDatos.Finalizar();
            
            StringCustomLOGIX = "ok";
           
        } catch (SQLException exception) {
            System.out.println("--" + exception);
            return null;
        }
        
        return StringCustomLOGIX;
    }
    
}