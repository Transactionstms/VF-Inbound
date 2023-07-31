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
import java.sql.SQLException;
import com.onest.train.consultas.actualCRDModel;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Desarrollo Tacts
 */
public class Rule2_UpGtnLt2 {
    private Conexion cnBaseDeDatos;
    
    public Rule2_UpGtnLt2() {
        Configuracion cfg = new Configuracion();
        this.cnBaseDeDatos = new Conexion(cfg.getSid(), cfg.getUsuario(), cfg.getPassword());
    }
    
    public String updateLt2LoadTypeNot(String fecha_actualCrd)throws Exception{
        
        String res = "";
        CallableStatement sp1 = null;
        actualCRDModel oRespuesta = null;
        List<actualCRDModel> listaRegistros = new ArrayList<actualCRDModel>();
        
        // create SimpleDateFormat object with desired date format:       Tratamiento 3
        SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd"); 
        
        try {
            
           this.cnBaseDeDatos.Iniciar();
           
           sp1 = Rule2_UpLt2_EtaDcAndPutAwayLoadTipeNot.lt2ActualCrd(this.cnBaseDeDatos, fecha_actualCrd);
           sp1.execute();
           java.sql.Date p_salida = sp1.getDate(2);

           res = sdfDestination.format(p_salida);
        
            this.cnBaseDeDatos.Cerrar(sp1);
            this.cnBaseDeDatos.Finalizar();
           
        } catch (SQLException exception) {
            System.out.println("--" + exception);
            return null;
        }
        
        return res;
    }
    
}
