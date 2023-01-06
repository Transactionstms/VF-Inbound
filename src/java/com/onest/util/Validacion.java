/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.util;

import com.onest.train.consultas.CapturaDocumento;
import com.onest.train.consultas.ConsultasQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergiocastros
 */
public class Validacion extends ServiceDAO{
    private CapturaDocumento capturaDocumento = new CapturaDocumento();
    private ConsultasQuery consultasQuery = new ConsultasQuery();
    public String validaciontrakingGeneralLista2(String documento, String cveCuenta, String e) {

        String salida = "";
        try {
            //      if (cveCuenta.equals(cuentaMArti)) {
           
                salida = consultasQuery.trakingGeneralLista2(documento, cveCuenta);
            

        } catch (Exception ex) {
            ex.getMessage();
        }

        return salida;
    }
    
    public String validaciondetalleDocumentosEmbarcados(String agrupador, String division, String e, String cve,String idCuentaBroker) {

        String salida = "";
        try {
           
                salida = capturaDocumento.detalleDocumentosEmbarcados(agrupador, division, e);
            

        } catch (Exception ex) {
            ex.getMessage();
        }

        return salida;
    }
    
     public String validaciondetalleDocumentosIngresados(String documento, String division, String e, String cve,String idCuentaBroker) {

        String salida = "";
        try {
           
                salida = capturaDocumento.documentosEstadoIngresadoTracking(division,documento);
            

        } catch (Exception ex) {
            ex.getMessage();
        }

        return salida;
    }
    
    
    
     public String validacionservicioClienteLista(String embarque, String cve) {

        String salida = "";
        try {
            //   if (cve.equals(cuentaMArti)) {
          
                salida = capturaDocumento.servicioClienteLista(embarque, cve);
            

        } catch (Exception ex) {
            ex.getMessage();
        }

        return salida;
    }
    
    public boolean validaUsuarioCuenta(String idCuentaBroker)  {
        String salida = "";

            String sql = " SELECT * FROM app_constantes WHERE constante_valor ='" + idCuentaBroker + "' ";

        ResultSet res = null;
        try {
            res = this.consulta(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Validacion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        try {
            while (res.next()) {
               return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Validacion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        this.desconectarService(res);
        return false;
    }
    
    
}
