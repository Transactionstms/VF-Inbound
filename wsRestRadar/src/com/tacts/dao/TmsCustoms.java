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
import wsRadar.ConsumowsRadar;

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
    
    public String consultarCustomsRADAR(String agenteId)throws Exception{
        
        String StringCustomRADAR = null;
        CallableStatement sp1 = null;
        ConsumowsRadar Consumows=new ConsumowsRadar();
        String contenidoRADAR = "";
        String id = "";
        String data = "";
        
        try {
            
           this.cnBaseDeDatos.Iniciar();
           sp1 = CustomsSql.consultarCustomRADAR(this.cnBaseDeDatos,agenteId);
           sp1.execute();
           
           ResultSet rs = (ResultSet)sp1.getObject("resultado");
           while (rs.next()) {
               
               if(!rs.getString(6).equals("")){
                  id = "&shipmentId="+rs.getString(6);
                  data = rs.getString(6);
               }else{
                  id = "&container="+rs.getString(7); 
                  data = rs.getString(7); 
               }
               
               contenidoRADAR = Consumows.ConsumoRADAR("asignaciones?idCliente=489"+id, data);  
           }
           
            this.cnBaseDeDatos.Cerrar(sp1);
            this.cnBaseDeDatos.Finalizar();
            
            StringCustomRADAR = "ok";
           
        } catch (SQLException exception) {
            System.out.println("--" + exception);
            return null;
        }
        
        return StringCustomRADAR;
    }

}