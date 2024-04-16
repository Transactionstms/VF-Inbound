/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.dao;

import com.google.gson.Gson;
import com.tacts.model.CustomModel;
import com.tacts.model.RegistrosModel;
import com.tacts.sql.Conexion;
import com.tacts.sql.Configuracion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo Tacts
 */
public class TmsCustom {
    private Conexion cnBaseDeDatos;
    
    public TmsCustom() {
        Configuracion cfg = new Configuracion();
        this.cnBaseDeDatos = new Conexion(cfg.getSid(), cfg.getUsuario(), cfg.getPassword());
    }
    
    public String registrosCustoms(CustomModel objeto, String agenteId)throws Exception{
        
        String StringCustom = null;
        CallableStatement sp = null;
        RegistrosModel oRespuesta = null;
        List<RegistrosModel> listaRegistros = new ArrayList<RegistrosModel>();
        
        try {
            
           this.cnBaseDeDatos.Iniciar();
           
           if(agenteId.equals("4003")){
              sp = CustomsSql.consultarCustomRADAR(this.cnBaseDeDatos, objeto, agenteId);
           }else if(agenteId.equals("4001")){
              sp = CustomsSql.consultarCustomLOGIX(this.cnBaseDeDatos, objeto, agenteId); 
           }
           
           sp.execute();
           
           ResultSet rs = (ResultSet)sp.getObject("resultado");
           while (rs.next()) {
               
               RegistrosModel listar = new RegistrosModel();
               
               listar.setEvento(rs.getString("ID_EVENTO"));
               listar.setResponsable(rs.getString("RESPONSABLE"));
               listar.setFinalDestination(rs.getString("FINAL_DESTINATION"));
               listar.setBrandDivision(rs.getString("NOMBRE_BD"));
               listar.setDivision(rs.getString("CBDIV_ID"));
               listar.setShipmentId(rs.getString("SHIPMENT_ID"));
               listar.setContainer(rs.getString("CONTAINER1"));
               listar.setBlAwbPro(rs.getString("BL_AWB_PRO"));
               listar.setLoadType(rs.getString("LOAD_TYPE"));
               listar.setQuantity(rs.getString("QUANTITY"));
               listar.setPod(rs.getString("NOMBRE_POD"));
               listar.setDeparturePol(rs.getString("EST_DEPARTURE_POL"));
               listar.setPortDischarge(rs.getString("ETA_PORT_DISCHARGE"));
               listar.setEstEtaDc(rs.getString("EST_ETA_DC"));
               listar.setFechaCaptura(rs.getString("FECHA_CAPTURA"));
               listar.setNombrePol(rs.getString("NOMBRE_POL"));
               listar.setOperacionRegimen(rs.getString("OPERACION_REGIMEN"));
               listar.setOperacionCliente(rs.getString("OPERACION_CLIENTE"));
               listar.setOperacionTipoTransporte(rs.getString("OPERACION_TIPO_TRANSPORTE"));
               listar.setAduanaId(rs.getString("ADUANA_ID"));
               listar.setAduanaClave(rs.getString("ADUANA_CLAVE"));
               listar.setAduanaNombre(rs.getString("ADUANA_NOMBRE"));
              
               listaRegistros.add(listar);
           }
           
            this.cnBaseDeDatos.Cerrar(sp);
            this.cnBaseDeDatos.Finalizar();
           
        } catch (SQLException exception) {
            System.out.println("--" + exception);
            return null;
        }
        
         if (listaRegistros != null) {
            Gson gson = new Gson();
            StringCustom = gson.toJson((Object)listaRegistros);
        }else{
            StringCustom = "{}";
        }
        
       
        return StringCustom;
    }
    
}
