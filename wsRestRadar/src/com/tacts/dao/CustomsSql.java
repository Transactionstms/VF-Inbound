/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.dao;

import com.tacts.sql.Conexion;
import java.sql.CallableStatement;

/**
 *
 * @author Desarrollo Tacts
 */
public class CustomsSql {

    public static CallableStatement consultarCustomRADAR(Conexion conexion,String agenteId)throws Exception{
        try {
            CallableStatement sp = conexion.getConexion().prepareCall("{ call SP_INB_CUSTOMS_WSCONSUM_RADAR(?,?) }");
            sp.setString("pAgenteAduanal", agenteId);
            sp.registerOutParameter("resultado", -10);
            return sp; 
        } catch (Exception exception) {
            System.out.println("--"+exception);
            return null; 
        }
    }
    
    public static CallableStatement actualizarCustomsRadar(Conexion conexion, 
                String referenciaAA,
                String shipmentId,
                String pais_origen,
                String size_container,
                String valor_usd,
                String agente_aduanal,
                String pedimento_a1,
                String pedimento_r1_1er,
                String motivo_rectificacion_1er,
                String pedimento_r1_2do,
                String motivo_rectificacion_2do,
                String fecha_recepcion_doc,
                String recinto,
                String naviera,
                String buque,
                String fecha_revalidacion,
                String fecha_previo_origen,
                String fecha_previo_destino,
                String fecha_eta_port_discharge,
                String fecha_resultado_previo,
                String proforma_final,
                String permiso,
                String fecha_envio,
                String fecha_recepcion_perm,
                String fecha_activacion_perm,
                String fecha_permisos_aut,
                String co_pref_arancelaria,
                String aplic_pref_arancelaria,
                String req_uva,
                String req_ca,
                String fecha_recepcion_ca,
                String num_constancia_ca,
                String monto_ca,
                String fecha_doc_completos,
                String fecha_pago_pedimento,
                String fecha_solicitud_transporte,
                String fecha_modulacion,
                String modalidad,
                String resultado_modulacion,
                String fecha_reconocimiento,
                String fecha_liberacion,
                String sello_origen,
                String sello_final,
                String fecha_retencion_aut,
                String fecha_liberacion_aut,
                String estatus_operacion,
                String motivo_atraso,
                String observaciones,
                String containerId) {
        try {
           
            CallableStatement sp = conexion.getConexion().prepareCall("{call SP_INB_WS_CONSUMO_UPLOAD_RADAR_CUSTOMS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            sp.setString("referenciaAA", referenciaAA);
            sp.setString("shipmentId", shipmentId);
            sp.setString("pais_origen", pais_origen);
            sp.setString("size_container", size_container);
            sp.setString("valor_usd", valor_usd);
            sp.setString("agente_aduanal", agente_aduanal);
            sp.setString("pedimento_a1", pedimento_a1);
            sp.setString("pedimento_r1_1er", pedimento_r1_1er);
            sp.setString("motivo_rectificacion_1er", motivo_rectificacion_1er);
            sp.setString("pedimento_r1_2do", pedimento_r1_2do);
            sp.setString("motivo_rectificacion_2do", motivo_rectificacion_2do);
            sp.setString("fecha_recepcion_doc", fecha_recepcion_doc);
            sp.setString("recinto", recinto);
            sp.setString("naviera", naviera);
            sp.setString("buque", buque);
            sp.setString("fecha_revalidacion", fecha_revalidacion);
            sp.setString("fecha_previo_origen", fecha_previo_origen);
            sp.setString("fecha_eta_port_discharge", fecha_eta_port_discharge);
            sp.setString("fecha_previo_destino", fecha_previo_destino);
            sp.setString("fecha_resultado_previo", fecha_resultado_previo);
            sp.setString("proforma_final", proforma_final);
            sp.setString("permiso", permiso);
            sp.setString("fecha_envio", fecha_envio);
            sp.setString("fecha_recepcion_perm", fecha_recepcion_perm);
            sp.setString("fecha_activacion_perm", fecha_activacion_perm);
            sp.setString("fecha_permisos_aut", fecha_permisos_aut);
            sp.setString("co_pref_arancelaria", co_pref_arancelaria);
            sp.setString("aplic_pref_arancelaria", aplic_pref_arancelaria);
            sp.setString("req_uva", req_uva);
            sp.setString("req_ca", req_ca);
            sp.setString("fecha_recepcion_ca", fecha_recepcion_ca);
            sp.setString("num_constancia_ca", num_constancia_ca);
            sp.setString("monto_ca", monto_ca);
            sp.setString("fecha_doc_completos", fecha_doc_completos);
            sp.setString("fecha_pago_pedimento", fecha_pago_pedimento);
            sp.setString("fecha_solicitud_transporte", fecha_solicitud_transporte);
            sp.setString("fecha_modulacion", fecha_modulacion);
            sp.setString("modalidad", modalidad);
            sp.setString("resultado_modulacion", resultado_modulacion);
            sp.setString("fecha_reconocimiento", fecha_reconocimiento);
            sp.setString("fecha_liberacion", fecha_liberacion);
            sp.setString("sello_origen", sello_origen);
            sp.setString("sello_final", sello_final);
            sp.setString("fecha_retencion_aut", fecha_retencion_aut);
            sp.setString("fecha_liberacion_aut", fecha_liberacion_aut);
            sp.setString("estatus_operacion", estatus_operacion);
            sp.setString("motivo_atraso", motivo_atraso);
            sp.setString("observaciones", observaciones);
            sp.setString("containerId", containerId);
            sp.registerOutParameter("resultado", -10);
            return sp;
            
        } catch (Exception ex) {
            System.out.println("Error al reproducir store procedure (SP_INB_WS_CONSUMO_UPLOAD_RADAR_CUSTOMS): " + ex.toString());
        }
        return null;
    } 
    
}
