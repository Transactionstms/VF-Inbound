/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.modificar.catalogos;

import com.onest.oracle.Conexion;
import com.onest.oracle.Configuracion;
import java.sql.CallableStatement;
import java.sql.SQLException;

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
        
        String res = null;
        CallableStatement sp1 = null;
        
        try {
            
           this.cnBaseDeDatos.Iniciar();
           sp1 = Rule2_UpLt2_EtaDcAndPutAwayLoadTipeNot.lt2ActualCrd(this.cnBaseDeDatos, fecha_actualCrd);
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
