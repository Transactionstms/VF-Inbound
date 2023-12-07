/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.onest.train.consultas;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

/**
 *
 * @author Desarrollo Tacts
 */
public class ConsultarCustoms extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        
        HttpSession ownsession = request.getSession();
        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
        DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
        String UserId = (String) ownsession.getAttribute("login.user_id_number");
        String cve = (String) ownsession.getAttribute("cbdivcuenta");
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        oraDB.connect(dbData.getUser(), dbData.getPassword());    
        ConsultasQuery fac = new ConsultasQuery();
         
        String AgentType = request.getParameter("AgentType");    
        String filterType = request.getParameter("filterType");   
    
        String [] selected_referenciaAA = request.getParameter("selected_referenciaAA").split(","); 
        String [] selected_evento = request.getParameter("selected_evento").split(","); 
        String [] selected_responsable = request.getParameter("selected_responsable").split(","); 
        String [] selected_final_destination = request.getParameter("selected_final_destination").split(","); 
        String [] selected_brand_division = request.getParameter("selected_brand_division").split(","); 
        String [] selected_division = request.getParameter("selected_division").split(","); 
        String [] selected_shipmentId = request.getParameter("selected_shipmentId").split(","); 
        String [] selected_containerId = request.getParameter("selected_containerId").split(","); 
        String [] selected_blAwbPro = request.getParameter("selected_blAwbPro").split(","); 
        String [] selected_loadTypeFinal = request.getParameter("selected_loadTypeFinal").split(","); 
        String [] selected_quantity = request.getParameter("selected_quantity").split(","); 
        String [] selected_pod = request.getParameter("selected_pod").split(","); 
        String [] selected_estDepartFromPol = request.getParameter("selected_estDepartFromPol").split(","); 
        String [] selected_etaRealPortOfDischarge = request.getParameter("selected_etaRealPortOfDischarge").split(","); 
        String [] selected_estEtaDc = request.getParameter("selected_estEtaDc").split(","); 
        String [] selected_inboundNotification = request.getParameter("selected_inboundNotification").split(","); 
        String [] selected_pol = request.getParameter("selected_pol").split(","); 
        String [] selected_aa = request.getParameter("selected_aa").split(","); 
        String [] selected_fechaMesVenta = request.getParameter("selected_fechaMesVenta").split(","); 
        String [] selected_prioridad = request.getParameter("selected_prioridad").split(","); 
        String [] selected_pais_origen = request.getParameter("selected_pais_origen").split(","); 
        String [] selected_size_container = request.getParameter("selected_size_container").split(","); 
        String [] selected_valor_usd = request.getParameter("selected_valor_usd").split(","); 
        String [] selected_eta_port_discharge = request.getParameter("selected_eta_port_discharge").split(","); 
        String [] selected_agente_aduanal = request.getParameter("selected_agente_aduanal").split(","); 
        String [] selected_pedimento_a1 = request.getParameter("selected_pedimento_a1").split(","); 
        String [] selected_pedimento_r1_1er = request.getParameter("selected_pedimento_r1_1er").split(","); 
        String [] selected_motivo_rectificacion_1er = request.getParameter("selected_motivo_rectificacion_1er").split(","); 
        String [] selected_pedimento_r1_2do = request.getParameter("selected_pedimento_r1_2do").split(","); 
        String [] selected_motivo_rectificacion_2do = request.getParameter("selected_motivo_rectificacion_2do").split(","); 
        String [] selected_fecha_recepcion_doc = request.getParameter("selected_fecha_recepcion_doc").split(","); 
        String [] selected_recinto = request.getParameter("selected_recinto").split(","); 
        String [] selected_naviera = request.getParameter("selected_naviera").split(","); 
        String [] selected_buque = request.getParameter("selected_buque").split(","); 
        String [] selected_fecha_revalidacion = request.getParameter("selected_fecha_revalidacion").split(","); 
        String [] selected_fecha_previo_origen = request.getParameter("selected_fecha_previo_origen").split(","); 
        String [] selected_fecha_previo_destino = request.getParameter("selected_fecha_previo_destino").split(","); 
        String [] selected_fecha_resultado_previo = request.getParameter("selected_fecha_resultado_previo").split(","); 
        String [] selected_proforma_final = request.getParameter("selected_proforma_final").split(","); 
        String [] selected_permiso = request.getParameter("selected_permiso").split(","); 
        String [] selected_fecha_envio = request.getParameter("selected_fecha_envio").split(","); 
        String [] selected_fecha_recepcion_perm = request.getParameter("selected_fecha_recepcion_perm").split(","); 
        String [] selected_fecha_activacion_perm = request.getParameter("selected_fecha_activacion_perm").split(","); 
        String [] selected_fecha_permisos_aut = request.getParameter("selected_fecha_permisos_aut").split(","); 
        String [] selected_co_pref_arancelaria = request.getParameter("selected_co_pref_arancelaria").split(","); 
        String [] selected_aplic_pref_arancelaria = request.getParameter("selected_aplic_pref_arancelaria").split(","); 
        String [] selected_req_uva = request.getParameter("selected_req_uva").split(","); 
        String [] selected_req_ca = request.getParameter("selected_req_ca").split(","); 
        String [] selected_fecha_recepcion_ca = request.getParameter("selected_fecha_recepcion_ca").split(","); 
        String [] selected_num_constancia_ca = request.getParameter("selected_num_constancia_ca").split(","); 
        String [] selected_monto_ca = request.getParameter("selected_monto_ca").split(","); 
        String [] selected_fecha_doc_completos = request.getParameter("selected_fecha_doc_completos").split(","); 
        String [] selected_fecha_pago_pedimento = request.getParameter("selected_fecha_pago_pedimento").split(","); 
        String [] selected_fecha_solicitud_transporte = request.getParameter("selected_fecha_solicitud_transporte").split(","); 
        String [] selected_fecha_modulacion = request.getParameter("selected_fecha_modulacion").split(","); 
        String [] selected_modalidad = request.getParameter("selected_modalidad").split(","); 
        String [] selected_resultado_modulacion = request.getParameter("selected_resultado_modulacion").split(","); 
        String [] selected_fecha_reconocimiento = request.getParameter("selected_fecha_reconocimiento").split(","); 
        String [] selected_fecha_liberacion = request.getParameter("selected_fecha_liberacion").split(","); 
        String [] selected_sello_origen = request.getParameter("selected_sello_origen").split(","); 
        String [] selected_sello_final = request.getParameter("selected_sello_final").split(","); 
        String [] selected_fecha_retencion_aut = request.getParameter("selected_fecha_retencion_aut").split(","); 
        String [] selected_fecha_liberacion_aut = request.getParameter("selected_fecha_liberacion_aut").split(","); 
        String [] selected_estatus_operacion = request.getParameter("selected_estatus_operacion").split(","); 
        String [] selected_motivo_atraso = request.getParameter("selected_motivo_atraso").split(","); 
        String [] selected_observaciones = request.getParameter("selected_observaciones").split(","); 
        String [] selected_llegada_a_nova = request.getParameter("selected_llegada_a_nova").split(","); 
        String [] selected_llegada_a_globe_trade_sd = request.getParameter("selected_llegada_a_globe_trade_sd").split(","); 
        String [] selected_archivo_m = request.getParameter("selected_archivo_m").split(","); 
        String [] selected_fecha_archivo_m = request.getParameter("selected_fecha_archivo_m").split(","); 
        String [] selected_fecha_solicit_manip = request.getParameter("selected_fecha_solicit_manip").split(","); 
        String [] selected_fecha_vencim_manip = request.getParameter("selected_fecha_vencim_manip").split(","); 
        String [] selected_fecha_confirm_clave_pedim = request.getParameter("selected_fecha_confirm_clave_pedim").split(","); 
        String [] selected_fecha_recep_increment = request.getParameter("selected_fecha_recep_increment").split(","); 
        String [] selected_t_e = request.getParameter("selected_t_e").split(","); 
        String [] selected_fecha_vencim_inbound = request.getParameter("selected_fecha_vencim_inbound").split(","); 
        String [] selected_no_bultos = request.getParameter("selected_no_bultos").split(","); 
        String [] selected_peso_kg = request.getParameter("selected_peso_kg").split(","); 
        String [] selected_transferencia = request.getParameter("selected_transferencia").split(","); 
        String [] selected_fecha_inicio_etiquetado = request.getParameter("selected_fecha_inicio_etiquetado").split(","); 
        String [] selected_fecha_termino_etiquetado = request.getParameter("selected_fecha_termino_etiquetado").split(","); 
        String [] selected_hora_termino_etiquetado = request.getParameter("selected_hora_termino_etiquetado").split(","); 
        String [] selected_proveedor = request.getParameter("selected_proveedor").split(","); 
        String [] selected_proveedor_carga = request.getParameter("selected_proveedor_carga").split(","); 
        String [] selected_fy = request.getParameter("selected_fy").split(",");
        
        String caramelo_referenciaAA = "";
        String caramelo_evento = "";
        String caramelo_responsable = "";
        String caramelo_final_destination = "";
        String caramelo_brand_division = "";
        String caramelo_division = "";
        String caramelo_shipmentId = "";
        String caramelo_containerId = "";
        String caramelo_blAwbPro = "";
        String caramelo_loadTypeFinal = "";
        String caramelo_quantity = "";
        String caramelo_pod = "";
        String caramelo_estDepartFromPol = "";
        String caramelo_etaRealPortOfDischarge = "";
        String caramelo_estEtaDc = "";
        String caramelo_inboundNotification = "";
        String caramelo_pol = "";
        String caramelo_aa = "";
        String caramelo_fechaMesVenta = "";
        String caramelo_prioridad = "";
        String caramelo_pais_origen = "";
        String caramelo_size_container = "";
        String caramelo_valor_usd = "";
        String caramelo_eta_port_discharge = "";
        String caramelo_agente_aduanal = "";
        String caramelo_pedimento_a1 = "";
        String caramelo_pedimento_r1_1er = "";
        String caramelo_motivo_rectificacion_1er = "";
        String caramelo_pedimento_r1_2do = "";
        String caramelo_motivo_rectificacion_2do = "";
        String caramelo_fecha_recepcion_doc = "";
        String caramelo_recinto = "";
        String caramelo_naviera = "";
        String caramelo_buque = "";
        String caramelo_fecha_revalidacion = "";
        String caramelo_fecha_previo_origen = "";
        String caramelo_fecha_previo_destino = "";
        String caramelo_fecha_resultado_previo = "";
        String caramelo_proforma_final = "";
        String caramelo_permiso = "";
        String caramelo_fecha_envio = "";
        String caramelo_fecha_recepcion_perm = "";
        String caramelo_fecha_activacion_perm = "";
        String caramelo_fecha_permisos_aut = "";
        String caramelo_co_pref_arancelaria = "";
        String caramelo_aplic_pref_arancelaria = "";
        String caramelo_req_uva = "";
        String caramelo_req_ca = "";
        String caramelo_fecha_recepcion_ca = "";
        String caramelo_num_constancia_ca = "";
        String caramelo_monto_ca = "";
        String caramelo_fecha_doc_completos = "";
        String caramelo_fecha_pago_pedimento = "";
        String caramelo_fecha_solicitud_transporte = "";
        String caramelo_fecha_modulacion = "";
        String caramelo_modalidad = "";
        String caramelo_resultado_modulacion = "";
        String caramelo_fecha_reconocimiento = "";
        String caramelo_fecha_liberacion = "";
        String caramelo_sello_origen = "";
        String caramelo_sello_final = "";
        String caramelo_fecha_retencion_aut = "";
        String caramelo_fecha_liberacion_aut = "";
        String caramelo_estatus_operacion = "";
        String caramelo_motivo_atraso = "";
        String caramelo_observaciones = "";
        String caramelo_llegada_a_nova = "";
        String caramelo_llegada_a_globe_trade_sd = "";
        String caramelo_archivo_m = "";
        String caramelo_fecha_archivo_m = "";
        String caramelo_fecha_solicit_manip = "";
        String caramelo_fecha_vencim_manip = "";
        String caramelo_fecha_confirm_clave_pedim = "";
        String caramelo_fecha_recep_increment = "";
        String caramelo_t_e = "";
        String caramelo_fecha_vencim_inbound = "";
        String caramelo_no_bultos = "";
        String caramelo_peso_kg = "";
        String caramelo_transferencia = "";
        String caramelo_fecha_inicio_etiquetado = "";
        String caramelo_fecha_termino_etiquetado = "";
        String caramelo_hora_termino_etiquetado = "";
        String caramelo_proveedor = "";
        String caramelo_proveedor_carga = "";
        String caramelo_fy = "";
        String sql = "";
        
        //Generar caramelo: Opciones del multiselect
        for (String a : selected_referenciaAA) {
            caramelo_referenciaAA += "'" + a + "',";
        }
        for (String a : selected_evento) {
            caramelo_evento += a + ",";
        }
        for (String a : selected_responsable) {
            caramelo_responsable += "'" + a + "',";
        }
        for (String a : selected_final_destination) {
            caramelo_final_destination += "'" + a + "',";
        }
        for (String a : selected_brand_division) {
            caramelo_brand_division += "'" + a + "',";
        }
        for (String a : selected_division) {
            caramelo_division += "'" + a + "',";
        }
        for (String a : selected_shipmentId) {
            caramelo_shipmentId += "'" + a + "',";
        }
        for (String a : selected_containerId) {
            caramelo_containerId += "'" + a + "',";
        }
        for (String a : selected_blAwbPro) {
            caramelo_blAwbPro += "'" + a + "',";
        }
        for (String a : selected_loadTypeFinal) {
            caramelo_loadTypeFinal += "'" + a + "',";
        }
        for (String a : selected_quantity) {
            caramelo_quantity += a + ",";
        }
        for (String a : selected_pod) {
            caramelo_pod += "'" + a + "',";
        }
        for (String a : selected_estDepartFromPol) {
            caramelo_estDepartFromPol += "'" + a + "',";
        }
        for (String a : selected_etaRealPortOfDischarge) {
            caramelo_etaRealPortOfDischarge += "'" + a + "',";
        }
        for (String a : selected_estEtaDc) {
            caramelo_estEtaDc += "'" + a + "',";
        }
        for (String a : selected_inboundNotification) {
            caramelo_inboundNotification += "'" + a + "',";
        }
        for (String a : selected_pol) {
            caramelo_pol += "'" + a + "',";
        }
        for (String a : selected_aa) {
            caramelo_aa += "'" + a + "',";
        }
        for (String a : selected_fechaMesVenta) {
            caramelo_fechaMesVenta += "'" + a + "',";
        }
        for (String a : selected_prioridad) {
            caramelo_prioridad += "'" + a + "',";
        }
        for (String a : selected_pais_origen) {
            caramelo_pais_origen += "'" + a + "',";
        }
        for (String a : selected_size_container) {
            caramelo_size_container += "'" + a + "',";
        }
        for (String a : selected_valor_usd) {
            caramelo_valor_usd += "'" + a + "',";
        }
        for (String a : selected_eta_port_discharge) {
            caramelo_eta_port_discharge += "'" + a + "',";
        }
        for (String a : selected_agente_aduanal) {
            caramelo_agente_aduanal += "'" + a + "',";
        }
        for (String a : selected_pedimento_a1) {
            caramelo_pedimento_a1 += "'" + a + "',";
        }
        for (String a : selected_pedimento_r1_1er) {
            caramelo_pedimento_r1_1er += "'" + a + "',";
        }
        for (String a : selected_motivo_rectificacion_1er) {
            caramelo_motivo_rectificacion_1er += "'" + a + "',";
        }
        for (String a : selected_pedimento_r1_2do) {
            caramelo_pedimento_r1_2do += "'" + a + "',";
        }
        for (String a : selected_motivo_rectificacion_2do) {
            caramelo_motivo_rectificacion_2do += "'" + a + "',";
        }
        for (String a : selected_fecha_recepcion_doc) {
            caramelo_fecha_recepcion_doc += "'" + a + "',";
        }
        for (String a : selected_recinto) {
            caramelo_recinto += "'" + a + "',";
        }
        for (String a : selected_naviera) {
            caramelo_naviera += "'" + a + "',";
        }
        for (String a : selected_buque) {
            caramelo_buque += "'" + a + "',";
        }
        for (String a : selected_fecha_revalidacion) {
            caramelo_fecha_revalidacion += "'" + a + "',";
        }
        for (String a : selected_fecha_previo_origen) {
            caramelo_fecha_previo_origen += "'" + a + "',";
        }
        for (String a : selected_fecha_previo_destino) {
            caramelo_fecha_previo_destino += "'" + a + "',";
        }
        for (String a : selected_fecha_resultado_previo) {
            caramelo_fecha_resultado_previo += "'" + a + "',";
        }
        for (String a : selected_proforma_final) {
            caramelo_proforma_final += "'" + a + "',";
        }
        for (String a : selected_permiso) {
            caramelo_permiso += "'" + a + "',";
        }
        for (String a : selected_fecha_envio) {
            caramelo_fecha_envio += "'" + a + "',";
        }
        for (String a : selected_fecha_recepcion_perm) {
            caramelo_fecha_recepcion_perm += "'" + a + "',";
        }
        for (String a : selected_fecha_activacion_perm) {
            caramelo_fecha_activacion_perm += "'" + a + "',";
        }
        for (String a : selected_fecha_permisos_aut) {
            caramelo_fecha_permisos_aut += "'" + a + "',";
        }
        for (String a : selected_co_pref_arancelaria) {
            caramelo_co_pref_arancelaria += "'" + a + "',";
        }
        for (String a : selected_aplic_pref_arancelaria) {
            caramelo_aplic_pref_arancelaria += "'" + a + "',";
        }
        for (String a : selected_req_uva) {
            caramelo_req_uva += "'" + a + "',";
        }
        for (String a : selected_req_ca) {
            caramelo_req_ca += "'" + a + "',";
        }
        for (String a : selected_fecha_recepcion_ca) {
            caramelo_fecha_recepcion_ca += "'" + a + "',";
        }
        for (String a : selected_num_constancia_ca) {
            caramelo_num_constancia_ca += a + ",";
        }
        for (String a : selected_monto_ca) {
            caramelo_monto_ca += a + ",";
        }
        for (String a : selected_fecha_doc_completos) {
            caramelo_fecha_doc_completos += "'" + a + "',";
        }
        for (String a : selected_fecha_pago_pedimento) {
            caramelo_fecha_pago_pedimento += "'" + a + "',";
        }
        for (String a : selected_fecha_solicitud_transporte) {
            caramelo_fecha_solicitud_transporte += "'" + a + "',";
        }
        for (String a : selected_fecha_modulacion) {
            caramelo_fecha_modulacion += "'" + a + "',";
        }
        for (String a : selected_modalidad) {
            caramelo_modalidad += "'" + a + "',";
        }
        for (String a : selected_resultado_modulacion) {
            caramelo_resultado_modulacion += "'" + a + "',";
        }
        for (String a : selected_fecha_reconocimiento) {
            caramelo_fecha_reconocimiento += "'" + a + "',";
        }
        for (String a : selected_fecha_liberacion) {
            caramelo_fecha_liberacion += "'" + a + "',";
        }
        for (String a : selected_sello_origen) {
            caramelo_sello_origen += "'" + a + "',";
        }
        for (String a : selected_sello_final) {
            caramelo_sello_final += "'" + a + "',";
        }
        for (String a : selected_fecha_retencion_aut) {
            caramelo_fecha_retencion_aut += "'" + a + "',";
        }
        for (String a : selected_fecha_liberacion_aut) {
            caramelo_fecha_liberacion_aut += "'" + a + "',";
        }
        for (String a : selected_estatus_operacion) {
            caramelo_estatus_operacion += "'" + a + "',";
        }
        for (String a : selected_motivo_atraso) {
            caramelo_motivo_atraso += "'" + a + "',";
        }
        for (String a : selected_observaciones) {
            caramelo_observaciones += "'" + a + "',";
        }
        for (String a : selected_llegada_a_nova) {
            caramelo_llegada_a_nova += "'" + a + "',";
        }
        for (String a : selected_llegada_a_globe_trade_sd) {
            caramelo_llegada_a_globe_trade_sd += "'" + a + "',";
        }
        for (String a : selected_archivo_m) {
            caramelo_archivo_m += "'" + a + "',";
        }
        for (String a : selected_fecha_archivo_m) {
            caramelo_fecha_archivo_m += "'" + a + "',";
        }
        for (String a : selected_fecha_solicit_manip) {
            caramelo_fecha_solicit_manip += "'" + a + "',";
        }
        for (String a : selected_fecha_vencim_manip) {
            caramelo_fecha_vencim_manip += "'" + a + "',";
        }
        for (String a : selected_fecha_confirm_clave_pedim) {
            caramelo_fecha_vencim_manip += "'" + a + "',";
        }
        for (String a : selected_fecha_recep_increment) {
            caramelo_fecha_recep_increment += "'" + a + "',";
        }
        for (String a : selected_t_e) {
            caramelo_t_e += "'" + a + "',";
        }
        for (String a : selected_fecha_vencim_inbound) {
            caramelo_fecha_vencim_inbound += "'" + a + "',";
        }
        for (String a : selected_no_bultos) {
            caramelo_no_bultos += "'" + a + "',";
        }
        for (String a : selected_peso_kg) {
            caramelo_peso_kg += "'" + a + "',";
        }
        for (String a : selected_transferencia) {
            caramelo_transferencia += "'" + a + "',";
        }
        for (String a : selected_fecha_inicio_etiquetado) {
            caramelo_fecha_inicio_etiquetado += "'" + a + "',";
        }
        for (String a : selected_fecha_termino_etiquetado) {
            caramelo_fecha_termino_etiquetado += "'" + a + "',";
        }
        for (String a : selected_hora_termino_etiquetado) {
            caramelo_hora_termino_etiquetado += "'" + a + "',";
        }
        for (String a : selected_proveedor) {
            caramelo_proveedor += "'" + a + "',";
        }
        for (String a : selected_proveedor_carga) {
            caramelo_proveedor_carga += "'" + a + "',";
        }
        for (String a : selected_fy) {
            caramelo_fy += "'" + a + "',";
        }
        
        caramelo_referenciaAA=caramelo_referenciaAA.replaceAll(",$","");
        caramelo_evento=caramelo_evento.replaceAll(",$","");
        caramelo_responsable=caramelo_responsable.replaceAll(",$","");
        caramelo_final_destination=caramelo_final_destination.replaceAll(",$","");
        caramelo_brand_division=caramelo_brand_division.replaceAll(",$","");
        caramelo_division=caramelo_division.replaceAll(",$","");
        caramelo_shipmentId=caramelo_shipmentId.replaceAll(",$","");
        caramelo_containerId=caramelo_containerId.replaceAll(",$","");
        caramelo_blAwbPro=caramelo_blAwbPro.replaceAll(",$","");
        caramelo_loadTypeFinal=caramelo_loadTypeFinal.replaceAll(",$","");
        caramelo_quantity=caramelo_quantity.replaceAll(",$","");
        caramelo_pod=caramelo_pod.replaceAll(",$","");
        caramelo_estDepartFromPol=caramelo_estDepartFromPol.replaceAll(",$","");
        caramelo_etaRealPortOfDischarge=caramelo_etaRealPortOfDischarge.replaceAll(",$","");
        caramelo_estEtaDc=caramelo_estEtaDc.replaceAll(",$","");
        caramelo_inboundNotification=caramelo_inboundNotification.replaceAll(",$","");
        caramelo_pol=caramelo_pol.replaceAll(",$","");
        caramelo_aa=caramelo_aa.replaceAll(",$","");
        caramelo_fechaMesVenta=caramelo_fechaMesVenta.replaceAll(",$","");
        caramelo_prioridad=caramelo_prioridad.replaceAll(",$","");
        caramelo_pais_origen=caramelo_pais_origen.replaceAll(",$","");
        caramelo_size_container=caramelo_size_container.replaceAll(",$","");
        caramelo_valor_usd=caramelo_valor_usd.replaceAll(",$","");
        caramelo_eta_port_discharge=caramelo_eta_port_discharge.replaceAll(",$","");
        caramelo_agente_aduanal=caramelo_agente_aduanal.replaceAll(",$","");
        caramelo_pedimento_a1=caramelo_pedimento_a1.replaceAll(",$","");
        caramelo_pedimento_r1_1er=caramelo_pedimento_r1_1er.replaceAll(",$","");
        caramelo_motivo_rectificacion_1er=caramelo_motivo_rectificacion_1er.replaceAll(",$","");
        caramelo_pedimento_r1_2do=caramelo_pedimento_r1_2do.replaceAll(",$","");
        caramelo_motivo_rectificacion_2do=caramelo_motivo_rectificacion_2do.replaceAll(",$","");
        caramelo_fecha_recepcion_doc=caramelo_fecha_recepcion_doc.replaceAll(",$","");
        caramelo_recinto=caramelo_recinto.replaceAll(",$","");
        caramelo_naviera=caramelo_naviera.replaceAll(",$","");
        caramelo_buque=caramelo_buque.replaceAll(",$","");
        caramelo_fecha_revalidacion=caramelo_fecha_revalidacion.replaceAll(",$","");
        caramelo_fecha_previo_origen=caramelo_fecha_previo_origen.replaceAll(",$","");
        caramelo_fecha_previo_destino=caramelo_fecha_previo_destino.replaceAll(",$","");
        caramelo_fecha_resultado_previo=caramelo_fecha_resultado_previo.replaceAll(",$","");
        caramelo_proforma_final=caramelo_proforma_final.replaceAll(",$","");
        caramelo_permiso=caramelo_permiso.replaceAll(",$","");
        caramelo_fecha_envio=caramelo_fecha_envio.replaceAll(",$","");
        caramelo_fecha_recepcion_perm=caramelo_fecha_recepcion_perm.replaceAll(",$","");
        caramelo_fecha_activacion_perm=caramelo_fecha_activacion_perm.replaceAll(",$","");
        caramelo_fecha_permisos_aut=caramelo_fecha_permisos_aut.replaceAll(",$","");
        caramelo_co_pref_arancelaria=caramelo_co_pref_arancelaria.replaceAll(",$","");
        caramelo_aplic_pref_arancelaria=caramelo_aplic_pref_arancelaria.replaceAll(",$","");
        caramelo_req_uva=caramelo_req_uva.replaceAll(",$","");
        caramelo_req_ca=caramelo_req_ca.replaceAll(",$","");
        caramelo_fecha_recepcion_ca=caramelo_fecha_recepcion_ca.replaceAll(",$","");
        caramelo_num_constancia_ca=caramelo_num_constancia_ca.replaceAll(",$","");
        caramelo_monto_ca=caramelo_monto_ca.replaceAll(",$","");
        caramelo_fecha_doc_completos=caramelo_fecha_doc_completos.replaceAll(",$","");
        caramelo_fecha_pago_pedimento=caramelo_fecha_pago_pedimento.replaceAll(",$","");
        caramelo_fecha_solicitud_transporte=caramelo_fecha_solicitud_transporte.replaceAll(",$","");
        caramelo_fecha_modulacion=caramelo_fecha_modulacion.replaceAll(",$","");
        caramelo_modalidad=caramelo_modalidad.replaceAll(",$","");
        caramelo_resultado_modulacion=caramelo_resultado_modulacion.replaceAll(",$","");
        caramelo_fecha_reconocimiento=caramelo_fecha_reconocimiento.replaceAll(",$","");
        caramelo_fecha_liberacion=caramelo_fecha_liberacion.replaceAll(",$","");
        caramelo_sello_origen=caramelo_sello_origen.replaceAll(",$","");
        caramelo_sello_final=caramelo_sello_final.replaceAll(",$","");
        caramelo_fecha_retencion_aut=caramelo_fecha_retencion_aut.replaceAll(",$","");
        caramelo_fecha_liberacion_aut=caramelo_fecha_liberacion_aut.replaceAll(",$","");
        caramelo_estatus_operacion=caramelo_estatus_operacion.replaceAll(",$","");
        caramelo_motivo_atraso=caramelo_motivo_atraso.replaceAll(",$","");
        caramelo_observaciones=caramelo_observaciones.replaceAll(",$","");
        caramelo_llegada_a_nova=caramelo_llegada_a_nova.replaceAll(",$","");
        caramelo_llegada_a_globe_trade_sd=caramelo_llegada_a_globe_trade_sd.replaceAll(",$","");
        caramelo_archivo_m=caramelo_archivo_m.replaceAll(",$","");
        caramelo_fecha_archivo_m=caramelo_fecha_archivo_m.replaceAll(",$","");
        caramelo_fecha_solicit_manip=caramelo_fecha_solicit_manip.replaceAll(",$","");
        caramelo_fecha_vencim_manip=caramelo_fecha_vencim_manip.replaceAll(",$","");
        caramelo_fecha_confirm_clave_pedim=caramelo_fecha_confirm_clave_pedim.replaceAll(",$","");
        caramelo_fecha_recep_increment=caramelo_fecha_recep_increment.replaceAll(",$","");
        caramelo_t_e=caramelo_t_e.replaceAll(",$","");
        caramelo_fecha_vencim_inbound=caramelo_fecha_vencim_inbound.replaceAll(",$","");
        caramelo_no_bultos=caramelo_no_bultos.replaceAll(",$","");
        caramelo_peso_kg=caramelo_peso_kg.replaceAll(",$","");
        caramelo_transferencia=caramelo_transferencia.replaceAll(",$","");
        caramelo_fecha_inicio_etiquetado=caramelo_fecha_inicio_etiquetado.replaceAll(",$","");
        caramelo_fecha_termino_etiquetado=caramelo_fecha_termino_etiquetado.replaceAll(",$","");
        caramelo_hora_termino_etiquetado=caramelo_hora_termino_etiquetado.replaceAll(",$","");
        caramelo_proveedor=caramelo_proveedor.replaceAll(",$","");
        caramelo_proveedor_carga=caramelo_proveedor_carga.replaceAll(",$","");
        caramelo_fy=caramelo_fy.replaceAll(",$","");

        //Parametros Generales
        String colorSemaforo = "";
        String sizeSemaforo = "";
        String listStatusOperationEvent = "";
        String blockedDate = "";
        String salida = "";
        int cont = 1; 

       /*Columna: Estatus Operación (listado)*/
        if (db.doDB(fac.consultarEstatusOperacionCustoms())) {
            for (String[] rowO : db.getResultado()) {
                listStatusOperationEvent += "<option value=\"" + rowO[0] + "\">" + rowO[1] + "</option>"; 
            }
        }
              
           sql = " WITH SUM_QUANTITY AS (SELECT SHIPMENT_ID, CONTAINER1, SUM(QUANTITY) AS SUMA FROM TRA_INC_GTN_TEST GROUP BY SHIPMENT_ID, CONTAINER1) "
               + " SELECT DISTINCT "
         /*1*/ + " TIE.ID_EVENTO, "
         /*2*/ + " NVL(BP.RESPONSABLE, ' ') AS RESPONSABLE, "
         /*3*/ + " GTN.FINAL_DESTINATION, "
         /*4*/ + " GTN.BRAND_DIVISION, "
         /*5*/ + " NVL(TID.DIVISION_NOMBRE,' '), "
         /*6*/ + " GTN.SHIPMENT_ID, "
         /*7*/ + " GTN.CONTAINER1, "
         /*8*/ + " GTN.BL_AWB_PRO, "
         /*9*/ + " GTN.LOAD_TYPE_FINAL, "
        /*10*/ + " SQ.SUMA, "
        /*11*/ + " TIP1.NOMBRE_POD, "
        /*12*/ + " TO_CHAR(GTN.EST_DEPARTURE_POL, 'MM/DD/YYYY') AS EST_DEPARTURE_POL, "
        /*13*/ + " TO_CHAR(GTN.ETA_PORT_DISCHARGE, 'MM/DD/YYYY') AS ETA_REAL_PORT, "
        /*14*/ + " NVL(GTN.MAX_FLETE, 0) AS EST_ETA_DC, "
         /*15*/+ " TO_CHAR(GTN.FECHA_CAPTURA, 'MM/DD/YYYY') AS FECHA_CAPTURA, "//INBOUND NOTIFICATION
        /*16*/ + " TIP2.NOMBRE_POL, "
        /*17*/ + " NVL(TAA.AGENTE_ADUANAL_NOMBRE, ' ') AS AGENTE_ADUANAL, "
        /*18*/ + " GTN.PLANTILLA_ID, "
        /*19*/ + " SYSDATE AS FECHA_CAPTURAOLD, "
        /*20*/ + " TIP1.NOMBRE_POD, "
        /*21*/ + " TIP2.NOMBRE_POL, "
        /*22*/ + " TIBD.NOMBRE_BD, "
        /*23*/ + " NVL(TO_CHAR(GTN.ETA_PLUS2, 'MM/DD/YYYY'), ' ') AS ETA_DC, "
        /*24*/ + " NVL(TO_CHAR(TIE.EST_ETA_DC, 'MM/DD/YYYY'),' '), "
        /*25*/ + " NVL(TO_CHAR(GTN.ETA_PLUS, 'MM/DD/YYYY'), ' ') AS ETA_DC1, "
        /*26*/ + " NVL(TIE.OBSERVACIONES, ' ') AS OBSERVACIONES, "
        /*27*/ + " TIE.ESTATUS_EVENTO, "
        /*28*/ + " NVL(TIE.REFERENCIA_AA,' '), "
        /*29*/ + " NVL(TO_CHAR(TIE.FECHA_CAPTURA, 'MM/DD/YYYY'),' '), "    
        /*30*/ + " NVL(TIE.PRIORIDAD,' '), "  
        /*31*/ + " NVL(TIC.REFERENCIA_AA,' '), " 
        /*32*/ + " NVL(TIC.PAIS_ORIGEN,' '), "   
        /*33*/ + " NVL(TIC.SIZE_CONTAINER,' '), "   
        /*34*/ + " NVL(TIC.VALOR_USD,' '), "               
        /*35*/ + " NVL(TO_CHAR(TIC.ETA_PORT_OF_DISCHARGE, 'MM/DD/YYYY'),' '), "       
        /*36*/ + " NVL(TIC.AGENTE_ADUANAL,' '), "             
        /*37*/ + " NVL(TIC.PEDIMENTO_A1,' '), "               
        /*38*/ + " NVL(TIC.PEDIMENTO_R1,' '), "           
        /*39*/ + " NVL(TIC.MOTIVO_RECTIFICACION_1,' '), "   
        /*40*/ + " NVL(TIC.PEDIMENTO_R1_2DO,' '), "           
        /*41*/ + " NVL(TIC.MOTIVO_RECTIFICACION_2,' '), "   
        /*42*/ + " NVL(TO_CHAR(TIC.FECHA_RECEPCION_DOCUMENTOS, 'MM/DD/YYYY'),' '), "       
        /*43*/ + " NVL(TIC.RECINTO,' '), "     
        /*44*/ + " NVL(TIC.NAVIERA_FORWARDER,' '), "     
        /*45*/ + " NVL(TIC.BUQUE,' '), "    
        /*46*/ + " NVL(TO_CHAR(TIC.FECHA_REVALID_LIBE_BL, 'MM/DD/YYYY'),' '), "   
        /*47*/ + " NVL(TO_CHAR(TIC.FECHA_PREVIO_ORIGEN, 'MM/DD/YYYY'),' '), "   
        /*48*/ + " NVL(TO_CHAR(TIC.FECHA_PREVIO_DESTINO, 'MM/DD/YYYY'),' '), "   
        /*49*/ + " NVL(TO_CHAR(TIC.FECHA_RESULTADO_PREVIO, 'MM/DD/YYYY'),' '), "   
        /*50*/ + " NVL(TO_CHAR(TIC.PROFORMA_FINAL, 'MM/DD/YYYY'),' '), "           
        /*51*/ + " NVL(TIC.REQUIERE_PERMISO,' '), "    
        /*52*/ + " NVL(TO_CHAR(TIC.FECHA_ENVIO_FICHAS_NOTAS, 'MM/DD/YYYY'),' '), " 
        /*53*/ + " NVL(TO_CHAR(TIC.FEC_RECEPCION_PERMISOS_TRAMIT, 'MM/DD/YYYY'),' '), " 
        /*54*/ + " NVL(TO_CHAR(TIC.FEC_ACT_PERMISOS, 'MM/DD/YYYY'),' '), " 
        /*55*/ + " NVL(TO_CHAR(TIC.FEC_PERM_AUT, 'MM/DD/YYYY'),' '), " 
        /*56*/ + " NVL(TIC.CO_APLIC_PREF_ARANCELARIA,' '), "                     
        /*57*/ + " NVL(TIC.APLIC_PREF_ARANCELARIA_CO,' '), "     
        /*58*/ + " NVL(TIC.REQUIERE_UVA,' '), "                                  
        /*59*/ + " NVL(TIC.REQUIERE_CA,' '), "    
        /*60*/ + " NVL(TO_CHAR(TIC.FECHA_RECEPCION_CA, 'MM/DD/YYYY'),' '), " 
        /*61*/ + " NVL(TIC.NÚMERO_CONSTANCIA_CA,' '), "                         
        /*62*/ + " NVL(TIC.MONTO_CA,' '), "   
        /*63*/ + " NVL(TO_CHAR(TIC.FECHA_DOCUMENTOS_COMPLETOS, 'MM/DD/YYYY'),' '), " 
        /*64*/ + " NVL(TO_CHAR(TIC.FECHA_PAGO_PEDIMENTO, 'MM/DD/YYYY'),' '), " 
        /*65*/ + " NVL(TO_CHAR(TIC.FECHA_SOLICITUD_TRANSPORTE, 'MM/DD/YYYY'),' '), " 
        /*66*/ + " NVL(TO_CHAR(TIC.FECHA_MODULACION, 'MM/DD/YYYY'),' '), " 
        /*67*/ + " NVL(TIC.MODALIDAD_CAMION_TREN,' '), "                  
        /*68*/ + " NVL(TIC.RESULT_MODULACION_VERDE_ROJO,' '), "       
        /*69*/ + " NVL(TO_CHAR(TIC.FECHA_RECONOCIMIENTO, 'MM/DD/YYYY'),' '), " 
        /*70*/ + " NVL(TO_CHAR(TIC.FECHA_LIBERACION, 'MM/DD/YYYY'),' '), "    
        /*71*/ + " NVL(TIC.SELLO_ORIGEN,' '), "                                         
        /*72*/ + " NVL(TIC.SELLO_FINAL,' '), "                
        /*73*/ + " NVL(TO_CHAR(TIC.FECHA_RETENCION_AUTORIDAD, 'MM/DD/YYYY'),' '), "    
        /*74*/ + " NVL(TO_CHAR(TIC.FECHA_LIB_POR_RET_AUT, 'MM/DD/YYYY'),' '), "    
        /*75*/ + " NVL(TEC.DESCRIPCION_ESTADO,' '), "           
        /*76*/ + " NVL(TIC.MOTIVO_ATRASO,' '), "              
        /*77*/ + " NVL(TIC.OBSERVACIONES,' '), "         
        /*78*/ + " NVL(TO_CHAR(TIC.LLEGADA_A_NOVA, 'MM/DD/YYYY'),' '), " 
        /*79*/ + " NVL(TO_CHAR(TIC.LLEGADA_A_GLOBE_TRADE_SD, 'MM/DD/YYYY'),' '), " 
        /*80*/ + " NVL(TIC.ARCHIVO_M,' '), "  
        /*81*/ + " NVL(TO_CHAR(TIC.FECHA_ARCHIVO_M, 'MM/DD/YYYY'),' '), "    
        /*82*/ + " NVL(TO_CHAR(TIC.FECHA_SOLICIT_MANIP, 'MM/DD/YYYY'),' '), "    
        /*83*/ + " NVL(TO_CHAR(TIC.FECHA_VENCIM_MANIP, 'MM/DD/YYYY'),' '), "    
        /*84*/ + " NVL(TO_CHAR(TIC.FECHA_CONFIRM_CLAVE_PEDIM, 'MM/DD/YYYY'),' '), "    
        /*85*/ + " NVL(TO_CHAR(TIC.FECHA_RECEP_INCREMENT, 'MM/DD/YYYY'),' '), "    
        /*86*/ + " NVL(TIC.T_E,' '), "  
        /*87*/ + " NVL(TO_CHAR(TIC.FECHA_VENCIM_INBOUND, 'MM/DD/YYYY'),' '), "                  
        /*88*/ + " NVL(TIC.NO_BULTOS,' '), " 
        /*89*/ + " NVL(TIC.PESO_KG,' '), "  
        /*90*/ + " NVL(TIC.TRANSFERENCIA,' '), "  
        /*91*/ + " NVL(TO_CHAR(TIC.FECHA_INICIO_ETIQUETADO, 'MM/DD/YYYY'),' '), "    
        /*92*/ + " NVL(TO_CHAR(TIC.FECHA_TERMINO_ETIQUETADO, 'MM/DD/YYYY'),' '), "    
        /*93*/ + " NVL(TIC.HORA_TERMINO_ETIQUETADO,' '), "  
        /*94*/ + " NVL(TIC.PROVEEDOR,' '), "  
        /*95*/ + " NVL(TIC.PROVEEDOR_CARGA,' '), " 						 
        /*96*/ + " NVL(TIC.FY,' '), "                                                
        /*97*/ + " NVL(TIC.AGENTE_ADUANAL_ID,0), "                                   
        /*98*/ + " NVL(TIC.PRIORIDAD,'No'), "
        /*99*/ + " NVL(GTN.ESTATUS,1), "
       /*100*/ + " NVL(TIC.ESTATUS_SEMAFORO,'0') "                                     
               + " FROM TRA_INB_EVENTO TIE "
               + " LEFT JOIN TRA_DESTINO_RESPONSABLE BP ON BP.USER_NID = TIE.USER_NID "
               + " INNER JOIN TRA_INC_GTN_TEST GTN ON GTN.PLANTILLA_ID = TIE.PLANTILLA_ID "
               + " LEFT JOIN TRA_INB_POD TIP1 ON TIP1.ID_POD = GTN.POD "
               + " LEFT JOIN TRA_INB_POL TIP2 ON TIP2.ID_POL = GTN.POL "
               + " LEFT JOIN TRA_INB_BRAND_DIVISION TIBD ON TIBD.ID_BD = GTN.BRAND_DIVISION "
               + " LEFT JOIN TRA_INB_AGENTE_ADUANAL TAA ON TAA.AGENTE_ADUANAL_ID = TIP1.AGENTE_ADUANAL_ID "
               + " LEFT JOIN TRA_INB_DIVISION TID ON TID.ID_DIVISION = GTN.SBU_NAME "
               + " LEFT JOIN SUM_QUANTITY SQ ON SQ.SHIPMENT_ID = GTN.SHIPMENT_ID AND SQ.CONTAINER1 = GTN.CONTAINER1 "
               + " LEFT JOIN TRA_INB_CUSTOMS TIC ON GTN.SHIPMENT_ID = TIC.SHIPMENT_ID "
               + " LEFT JOIN TRA_ESTADOS_CUSTOMS TEC ON GTN.ESTATUS = TEC.ID_ESTADO "
               + " LEFT JOIN TRA_INB_SEMAFORO TISE ON TIC.SHIPMENT_ID = TISE.SHIPMENT_ID "
               + " WHERE TIE.ESTADO = 1 "
               + " AND to_date(trunc(tie.FECHA_CAPTURA),'dd/mm/yy') >= to_date('01/10/2023','dd/mm/yyyy') " 
               + " AND to_date(trunc(tie.FECHA_CAPTURA),'dd/mm/yy') <= to_date('30/11/2023','dd/mm/yyyy') "
               + " AND tid.division_nombre <> 'No/DSN' "
               + " AND gtn.load_type_final IS NOT NULL "   
               + " AND GTN.ESTATUS <> 19 ";  
         
        if(!AgentType.equals("4006")){ //VF GENERAL
          sql += " AND TIP1.AGENTE_ADUANAL_ID IN ('" + AgentType + "') ";  
        }
        
        if(filterType.equals("1")){  //Referencia AA
          sql += " AND TIC.REFERENCIA_AA IN (" + caramelo_referenciaAA + ") ";
        }else if(filterType.equals("2")){  //Evento
          sql += " AND TIE.ID_EVENTO IN (" + caramelo_evento + ") ";
        }else if(filterType.equals("3")){  //Responsable
          sql += " AND BP.RESPONSABLE IN (" + caramelo_responsable + ") ";
        }else if(filterType.equals("4")){  //Final Destination
          sql += " AND GTN.FINAL_DESTINATION IN (" + caramelo_final_destination + ") ";
        }else if(filterType.equals("5")){  //Brand-Division
          sql += " AND TIBD.NOMBRE_BD IN (" + caramelo_brand_division + ") ";
        }else if(filterType.equals("6")){  //División
          sql += " AND TID.DIVISION_NOMBRE IN (" + caramelo_division + ") ";
        }else if(filterType.equals("7")){  //Shipment Id
          sql += " AND GTN.SHIPMENT_ID IN (" + caramelo_shipmentId + ") ";
        }else if(filterType.equals("8")){  //Container
          sql += " AND GTN.CONTAINER1 IN (" + caramelo_containerId + ") ";
        }else if(filterType.equals("9")){  //BL/AWB/PRO
          sql += " AND GTN.BL_AWB_PRO IN (" + caramelo_blAwbPro + ") ";
        }else if(filterType.equals("10")){ //Load Type
          sql += " AND GTN.LOAD_TYPE IN (" + caramelo_loadTypeFinal + ") ";
        }else if(filterType.equals("11")){ //Quantity
          sql += " AND SQ.SUMA IN (" + caramelo_quantity + ") ";
        }else if(filterType.equals("12")){ //Pod
          sql += " AND TIP1.NOMBRE_POD IN (" + caramelo_pod + ") ";
        }else if(filterType.equals("13")){ //Departure Pol 
          sql += " AND GTN.EST_DEPARTURE_POL IN (" + caramelo_estDepartFromPol + ") ";
        }else if(filterType.equals("14")){ //ETA REAL Port of Discharge
          sql += " AND TO_DATE(trunc(GTN.ETA_PORT_DISCHARGE),'DD/MM/YY') = TO_DATE(" + caramelo_etaRealPortOfDischarge + ",'MM/DD/YY') ";
        }else if(filterType.equals("15")){ //Est. Eta DC
          sql += " AND GTN.ETA_PLUS2 IN (" + caramelo_estEtaDc + ") ";
        }else if(filterType.equals("16")){ //Inbound notification
          sql += "" + caramelo_inboundNotification;
        }else if(filterType.equals("17")){ //Pol
          sql += " AND TIP2.NOMBRE_POL IN (" + caramelo_pol + ") ";
        }else if(filterType.equals("18")){ //Agente Aduanal
          sql += " AND TAA.AGENTE_ADUANAL_NOMBRE IN (" + caramelo_aa + ") ";
        }else if(filterType.equals("19")){ //Fecha Mes de Venta
          sql += " AND TO_DATE(trunc(TIE.FECHA_CAPTURA),'DD/MM/YY') = TO_DATE(" + caramelo_fechaMesVenta + ",'MM/DD/YY') ";
        }else if(filterType.equals("20")){ //Prioridad
          sql += " AND TIC.PRIORIDAD IN (" + caramelo_prioridad + ") ";
        }else if(filterType.equals("21")){ // País Origen 
          sql += " AND TIC.PAIS_ORIGEN IN (" + caramelo_pais_origen + ") ";
        }else if(filterType.equals("22")){ // Size Container
          sql += " AND TIC.SIZE_CONTAINER IN (" + caramelo_size_container + ") ";
        }else if(filterType.equals("23")){ // Valor USD
          sql += " AND TIC.VALOR_USD IN (" + caramelo_valor_usd + ") ";
        }else if(filterType.equals("24")){ // ETA Port Of Discharge
          sql += " AND TO_DATE(trunc(TIC.ETA_PORT_OF_DISCHARGE),'DD/MM/YY') = TO_DATE(" + caramelo_eta_port_discharge + ",'MM/DD/YY') ";
        }else if(filterType.equals("25")){ // Agente Aduanal 
          sql += " AND TIC.AGENTE_ADUANAL IN (" + caramelo_agente_aduanal + ") ";
        }else if(filterType.equals("26")){ // Pedimento A1 
          sql += " AND TIC.PEDIMENTO_A1 IN (" + caramelo_pedimento_a1 + ") ";
        }else if(filterType.equals("27")){ // Pedimento R1
          sql += " AND TIC.PEDIMENTO_R1 IN (" + caramelo_pedimento_r1_1er + ") ";
        }else if(filterType.equals("28")){ // Motivo Rectificación 1
          sql += " AND TIC.MOTIVO_RECTIFICACION_1 IN (" + caramelo_motivo_rectificacion_1er + ") ";
        }else if(filterType.equals("29")){ // Pedimento R1 (2DO)
          sql += " AND TIC.PEDIMENTO_R1_2DO IN (" + caramelo_pedimento_r1_2do + ") ";
        }else if(filterType.equals("30")){ // Motivo Rectificación 2
          sql += " AND TIC.MOTIVO_RECTIFICACION_2 IN (" + caramelo_motivo_rectificacion_2do + ") ";
        }else if(filterType.equals("31")){ // Fecha Recepción Documentos
          sql += " AND TO_DATE(trunc(TIC.FECHA_RECEPCION_DOCUMENTOS),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_recepcion_doc + ",'MM/DD/YY') ";
        }else if(filterType.equals("32")){ // Recinto
          sql += " AND TIC.RECINTO IN (" + caramelo_recinto + ") ";
        }else if(filterType.equals("33")){ // Naviera/Forwarder
          sql += " AND TIC.NAVIERA_FORWARDER IN (" + caramelo_naviera + ") ";
        }else if(filterType.equals("34")){ // Buque
          sql += " AND TIC.BUQUE IN (" + caramelo_buque + ") ";
        }else if(filterType.equals("35")){ // Fecha Revalidación/Liberación de BL
          sql += " AND TO_DATE(trunc(TIC.FECHA_REVALID_LIBE_BL),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_revalidacion + ",'MM/DD/YY') ";
        }else if(filterType.equals("36")){ // Fecha Previo Origen
          sql += " AND TO_DATE(trunc(TIC.FECHA_PREVIO_ORIGEN),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_previo_origen + ",'MM/DD/YY') ";
        }else if(filterType.equals("37")){ // Fecha Previo en destino
          sql += " AND TO_DATE(trunc(TIC.FECHA_PREVIO_DESTINO),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_previo_destino + ",'MM/DD/YY') ";
        }else if(filterType.equals("38")){ // Fecha Resultado Previo
          sql += " AND TO_DATE(trunc(TIC.FECHA_RESULTADO_PREVIO),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_resultado_previo + ",'MM/DD/YY') ";
        }else if(filterType.equals("39")){ // Proforma Final 
          sql += " AND TIC.PROFORMA_FINAL IN (" + caramelo_proforma_final + ") ";
        }else if(filterType.equals("40")){ // Requiere permiso
          sql += " AND TIC.REQUIERE_PERMISO IN (" + caramelo_permiso + ") ";
        }else if(filterType.equals("41")){ // Fecha envío Fichas/notas
          sql += " AND TO_DATE(trunc(TIC.FECHA_ENVIO_FICHAS_NOTAS),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_envio + ",'MM/DD/YY') ";
        }else if(filterType.equals("42")){ // Fec. Recepción de permisos tramit.
          sql += " AND TO_DATE(trunc(TIC.FEC_RECEPCION_PERMISOS_TRAMIT),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_recepcion_perm + ",'MM/DD/YY') ";
        }else if(filterType.equals("43")){ // Fec. Act Permisos (Inic Vigencia)
          sql += " AND TO_DATE(trunc(TIC.FEC_ACT_PERMISOS),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_activacion_perm + ",'MM/DD/YY') ";
        }else if(filterType.equals("44")){ // Fec. Perm. Aut. (Fin de Vigencia) 
          sql += " AND TO_DATE(trunc(TIC.FEC_PERM_AUT),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_permisos_aut + ",'MM/DD/YY') ";
        }else if(filterType.equals("45")){ // Cuenta con CO para aplicar preferencia Arancelaria
          sql += " AND TIC.CO_APLIC_PREF_ARANCELARIA IN (" + caramelo_co_pref_arancelaria + ") ";
        }else if(filterType.equals("46")){ // Aplico Preferencia Arancelaria 
          sql += " AND TIC.APLIC_PREF_ARANCELARIA_CO IN (" + caramelo_aplic_pref_arancelaria + ") ";
        }else if(filterType.equals("47")){ // Requiere UVA
          sql += " AND TIC.REQUIERE_UVA IN (" + caramelo_req_uva + ") ";
        }else if(filterType.equals("48")){ // Requiere CA
          sql += " AND TIC.REQUIERE_CA IN (" + caramelo_req_ca + ") ";
        }else if(filterType.equals("49")){ // Fecha Recepción CA
          sql += " AND TO_DATE(trunc(TIC.FECHA_RECEPCION_CA),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_recepcion_ca + ",'MM/DD/YY') ";
        }else if(filterType.equals("50")){ // Número de Constancia CA 
          sql += " AND IC.NÚMERO_CONSTANCIA_CA IN (" + caramelo_num_constancia_ca + ") ";
        }else if(filterType.equals("51")){ // Monto CA
          sql += " AND TIC.MONTO_CA IN (" + caramelo_monto_ca + ") ";
        }else if(filterType.equals("52")){ // Fecha Documentos Completos
          sql += " AND TO_DATE(trunc(TIC.FECHA_DOCUMENTOS_COMPLETOS),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_doc_completos + ",'MM/DD/YY') ";
        }else if(filterType.equals("53")){ // Fecha Pago Pedimento
          sql += " AND TO_DATE(trunc(TIC.FECHA_PAGO_PEDIMENTO),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_pago_pedimento + ",'MM/DD/YY') ";
        }else if(filterType.equals("54")){ // Fecha Solicitud de transporte
          sql += " AND TO_DATE(trunc(TIC.FECHA_SOLICITUD_TRANSPORTE),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_solicitud_transporte + ",'MM/DD/YY') ";
        }else if(filterType.equals("55")){ // Fecha Modulacion
          sql += " AND TO_DATE(trunc(TIC.FECHA_MODULACION),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_modulacion + ",'MM/DD/YY') ";
        }else if(filterType.equals("56")){ // Modalidad
          sql += " AND TIC.MODALIDAD_CAMION_TREN IN (" + caramelo_modalidad + ") ";
        }else if(filterType.equals("57")){ // Resultado Modulacion
          sql += " AND TIC.RESULT_MODULACION_VERDE_ROJO IN (" + caramelo_resultado_modulacion + ") ";
        }else if(filterType.equals("58")){ // Fecha Reconocimiento
          sql += " AND TO_DATE(trunc(TIC.FECHA_RECONOCIMIENTO),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_reconocimiento + ",'MM/DD/YY') ";
        }else if(filterType.equals("59")){ // Fecha Liberacion
          sql += " AND TO_DATE(trunc(TIC.FECHA_LIBERACION),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_liberacion + ",'MM/DD/YY') ";
        }else if(filterType.equals("60")){ // Sello Origen 
          sql += " AND TIC.SELLO_ORIGEN IN (" + caramelo_sello_origen + ") ";
        }else if(filterType.equals("61")){ // Sello Final
          sql += " AND TIC.SELLO_FINAL IN (" + caramelo_sello_final + ") ";
        }else if(filterType.equals("62")){ // Fecha de retencion por la autoridad
          sql += " AND TO_DATE(trunc(TIC.FECHA_RETENCION_AUTORIDAD),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_retencion_aut + ",'MM/DD/YY') ";
        }else if(filterType.equals("63")){ // Fec. de liberacion por ret. de la aut.
          sql += " AND TO_DATE(trunc(TIC.FECHA_LIB_POR_RET_AUT),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_liberacion_aut + ",'MM/DD/YY') ";
        }else if(filterType.equals("64")){ // Estatus de la operación
          sql += " AND TEC.DESCRIPCION_ESTADO IN (" + caramelo_estatus_operacion + ") ";
        }else if(filterType.equals("65")){ // Motivo Atraso
          sql += " AND TIC.MOTIVO_ATRASO IN (" + caramelo_motivo_atraso + ") ";
        }else if(filterType.equals("66")){ // Observaciones
          sql += " AND TIC.OBSERVACIONES IN (" + caramelo_observaciones + ") ";
        }else if(filterType.equals("67")){ // Llegada a NOVA
          sql += " AND TIC.LLEGADA_A_NOVA IN (" + caramelo_llegada_a_nova + ") ";
        }else if(filterType.equals("68")){ // Llegada a Globe trade SD  
          sql += " AND TIC.LLEGADA_A_GLOBE_TRADE_SD IN (" + caramelo_llegada_a_globe_trade_sd + ") ";
        }else if(filterType.equals("69")){ // Archivo M 
          sql += " AND TIC.ARCHIVO_M IN (" + caramelo_archivo_m + ") ";
        }else if(filterType.equals("70")){ // Fecha de Archivo M 
          sql += " AND TO_DATE(trunc(TIC.FECHA_ARCHIVO_M),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_archivo_m + ",'MM/DD/YY') ";
        }else if(filterType.equals("71")){ // Fecha Solicitud de Manipulacion
          sql += " AND TO_DATE(trunc(TIC.FECHA_SOLICIT_MANIP),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_solicit_manip + ",'MM/DD/YY') ";
        }else if(filterType.equals("72")){ // Fecha de vencimiento de Manipulacion
          sql += " AND TO_DATE(trunc(TIC.FECHA_VENCIM_MANIP),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_vencim_manip + ",'MM/DD/YY') ";
        }else if(filterType.equals("73")){ // Fecha confirmacion Clave de Pedimento
          sql += " AND TO_DATE(trunc(TIC.FECHA_CONFIRM_CLAVE_PEDIM),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_confirm_clave_pedim + ",'MM/DD/YY') ";
        }else if(filterType.equals("74")){ // Fecha de Recepcion de Incrementables
          sql += " AND TO_DATE(trunc(TIC.FECHA_RECEP_INCREMENT),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_recep_increment + ",'MM/DD/YY') ";
        }else if(filterType.equals("75")){ // T&E 
          sql += " AND TIC.T_E IN (" + caramelo_t_e + ") ";
        }else if(filterType.equals("76")){ // Fecha de Vencimiento del Inbound  
          sql += " AND TO_DATE(trunc(TIC.FECHA_VENCIM_INBOUND),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_vencim_inbound + ",'MM/DD/YY') ";
        }else if(filterType.equals("77")){ // No. BULTOS
          sql += " AND TIC.NO_BULTOS IN (" + caramelo_no_bultos + ") ";
        }else if(filterType.equals("78")){ // Peso (KG)
          sql += " AND TIC.PESO_KG IN (" + caramelo_peso_kg + ") ";
        }else if(filterType.equals("79")){ // Transferencia 
          sql += " AND TIC.TRANSFERENCIA IN (" + caramelo_transferencia + ") ";
        }else if(filterType.equals("80")){ // Fecha Inicio Etiquetado
          sql += " AND TO_DATE(trunc(TIC.FECHA_INICIO_ETIQUETADO),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_inicio_etiquetado + ",'MM/DD/YY') ";
        }else if(filterType.equals("81")){ // Fecha Termino Etiquetado 
          sql += " AND TO_DATE(trunc(TIC.FECHA_TERMINO_ETIQUETADO),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_termino_etiquetado + ",'MM/DD/YY') ";
        }else if(filterType.equals("82")){ // Hora de termino Etiquetado
          sql += " AND TIC.HORA_TERMINO_ETIQUETADO IN (" + caramelo_hora_termino_etiquetado + ") ";
        }else if(filterType.equals("83")){ // Proveedor
          sql += " AND TIC.PROVEEDOR IN (" + caramelo_proveedor + ") ";
        }else if(filterType.equals("84")){ // Proveedor de Carga
          sql += " AND TIC.PROVEEDOR_CARGA IN (" + caramelo_proveedor_carga + ") ";
        }else if(filterType.equals("85")){ // FY
          sql += " AND TIC.FY IN (" + caramelo_fy + ") ";
        }
          sql += " ORDER BY tie.id_evento, tibd.nombre_bd ASC ";  
           
        if (db.doDB(sql)) {
            for (String[] row : db.getResultado()) {
                       
                    if (row[99].equals("1")) {
                        colorSemaforo = "../img/circle-green.webp";
                        sizeSemaforo = "100%";
                    } else if (row[99].equals("2")) {
                        colorSemaforo = "../img/circle-yellow.webp";
                        sizeSemaforo = "80%";
                    } else if (row[99].equals("3")) {
                        colorSemaforo = "../img/circle-red.webp";
                        sizeSemaforo = "60%";
                    } else {
                        colorSemaforo = "../img/circle-gray.webp";
                        sizeSemaforo = "100%";
                    }

                    if (row[58] == "No") {
                        blockedDate = "false";
                    }

                 salida +="<tr id=\"tr<"+cont+"\">"
                        + " <th id=\"columna\"><center><img id=\"imgSemaforo"+cont+"\" src=\""+colorSemaforo+"\" width=\""+sizeSemaforo+"\"></center></th> "
                        + " <th contenteditable=\"true\" id=\"referenciaAA["+cont+"]\">"+row[30].trim()+"</th> "
                        + " <th class=\"font-numero first-column\" id=\"elemento"+cont+"\">"+row[0]+""
                        + "   <input type=\"hidden\" id=\"evento["+cont+"]\" name=\"evento["+cont+"]\" value=\""+row[0]+"\"> "                   
                        + "   <div id=\"popup"+cont+"\" style=\"display: none;\"> "
                        + "     <div id=\"mSgError"+cont+"\"></div> "
                        + "   </div> "
                        + " </th> "
                        + " <td id=\"Responsable["+cont+"]\">"+row[1].trim()+"</td> "
                        + " <td id=\"FinalDestination["+cont+"]\">"+row[2].trim()+"</td> "
                        + " <td id=\"BrandDivision["+cont+"]\">"+row[21].trim()+"</td> "
                        + " <td id=\"Division["+cont+"]\">"+row[4].trim()+"</td> "
                        + " <td id=\"shipmentId["+cont+"]\">"+row[5].trim()+"</td> "
                        + " <td id=\"containerId["+cont+"]\">"+row[6].trim()+"</td> "
                        + " <td id=\"blAwbPro["+cont+"]\">"+row[7].trim()+"</td> "
                        + " <td id=\"loadTypeFinal["+cont+"]\">"+row[8].trim()+"</td> "
                        + " <td id=\"Quantity["+cont+"]\">"+row[9].trim()+"</td> "
                        + " <td id=\"Pod["+cont+"]\">"+row[19].trim()+"</td> "
                        + " <td id=\"EstDepartureFromPol["+cont+"]\">"+row[11].trim()+"</td> "
                        + " <td id=\"EtaRealPortOfDischarge["+cont+"]\">"+row[12].trim()+"</td> "
                        + " <td id=\"EstEtaDc["+cont+"]\">"+row[22].trim()+"</td> "
                        + " <td id=\"InboundNotification["+cont+"]\">"+row[14].trim()+"</td> "
                        + " <td id=\"Pol["+cont+"]\">"+row[20].trim()+"</td> "
                        + " <td id=\"aa["+cont+"]\">"+row[16].trim()+"</td> "
                        + " <td id=\"FechaMesVenta["+cont+"]\">"+row[28].trim()+"</td> "
                        + " <td id=\"prioridad["+cont+"]\">"+row[97].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"pais_origen["+cont+"]\">"+row[31].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"size_container["+cont+"]\">"+row[32].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"valor_usd["+cont+"]\">"+row[33].trim()+"</td> "
                        + " <td id=\"eta_port_discharge["+cont+"]\" onclick=\"show_eta_port_discharge('"+row[34].trim()+"',"+cont+")\">"+row[34].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"agente_aduanal["+cont+"]\">"+row[35].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"pedimento_a1["+cont+"]\">"+row[36].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"pedimento_r1_1er["+cont+"]\" onclick=\"cleanPedimento_r1_1er('',"+cont+")\">"+row[37].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"motivo_rectificacion_1er["+cont+"]\">"+row[38].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"pedimento_r1_2do["+cont+"]\" onclick=\"cleanPedimento_r1_2do('"+row[39].trim()+"',"+cont+")\">"+row[39].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"motivo_rectificacion_2do["+cont+"]\">"+row[40].trim()+"</td> "
                        + " <td id=\"fecha_recepcion_doc["+cont+"]\" onclick=\"show_fecha_recepcion_doc('"+row[41].trim()+"',"+cont+")\">"+row[41].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"recinto["+cont+"]\">"+row[42].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"naviera["+cont+"]\">"+row[43].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"buque["+cont+"]\">"+row[44].trim()+"</td> "
                        + " <td id=\"fecha_revalidacion["+cont+"]\" onclick=\"show_fecha_revalidacion('"+row[45].trim()+"',"+cont+")\">"+row[45].trim()+"</td> "
                        + " <td id=\"fecha_previo_origen["+cont+"]\" onclick=\"show_fecha_previo_origen('"+row[46].trim()+"',"+cont+")\">"+row[46].trim()+"</td> "
                        + " <td id=\"fecha_previo_destino["+cont+"]\" onclick=\"show_fecha_previo_destino('"+row[47].trim()+"',"+cont+")\">"+row[47].trim()+"</td> "
                        + " <td id=\"fecha_resultado_previo["+cont+"]\" onclick=\"show_fecha_resultado_previo('"+row[48].trim()+"',"+cont+")\">"+row[48].trim()+"</td> "
                        + " <td id=\"proforma_final["+cont+"]\" onclick=\"show_proforma_final('"+row[49].trim()+"',"+cont+")\">"+row[49].trim()+"</td> "
                        + " <td id=\"permiso["+cont+"]\" onclick=\"show_permiso("+cont+")\">"+row[50].trim()+"</td> "
                        + " <td id=\"fecha_envio["+cont+"]\" onclick=\"show_fecha_envio('"+row[51].trim()+"',"+cont+")\">"+row[51].trim()+"</td> "
                        + " <td id=\"fecha_recepcion_perm["+cont+"]\" onclick=\"show_fecha_recepcion_perm('"+row[52].trim()+"',"+cont+")\">"+row[52].trim()+"</td> "
                        + " <td id=\"fecha_activacion_perm["+cont+"]\" onclick=\"show_fecha_activacion_perm('"+row[53].trim()+"',"+cont+")\">"+row[53].trim()+"</td> "
                        + " <td id=\"fecha_permisos_aut["+cont+"]\" onclick=\"show_fecha_permisos_aut('"+row[54].trim()+"',"+cont+")\">"+row[54].trim()+"</td> " 
                        + " <td id=\"co_pref_arancelaria["+cont+"]\" onclick=\"show_co_pref_arancelaria("+cont+")\">"+row[55].trim()+"</td> "
                        + " <td id=\"aplic_pref_arancelaria["+cont+"]\" onclick=\"show_aplic_pref_arancelaria("+cont+")\">"+row[56].trim()+"</td> "
                        + " <td id=\"req_uva["+cont+"]\" onclick=\"show_req_uva("+cont+")\">"+row[57].trim()+"</td> "
                        + " <td id=\"req_ca["+cont+"]\" onclick=\"show_req_ca("+cont+")\">"+row[58].trim()+"</td> "
                        + " <td id=\"fecha_recepcion_ca["+cont+"]\" onclick=\"show_fecha_recepcion_ca('"+row[59].trim()+"',"+cont+")\">"+row[59].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"num_constancia_ca["+cont+"]\">"+row[60].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"monto_ca["+cont+"]\">"+row[61].trim()+"</td> "
                        + " <td id=\"fecha_doc_completos["+cont+"]\" onclick=\"show_fecha_doc_completos('"+row[63].trim()+"',"+cont+")\">"+row[62].trim()+"</td> "
                        + " <td id=\"fecha_pago_pedimento["+cont+"]\" onclick=\"show_fecha_pago_pedimento("+cont+")\">"+row[63].trim()+"</td> "
                        + " <td id=\"fecha_solicitud_transporte["+cont+"]\" onclick=\"show_fecha_solicitud_transporte('"+row[64].trim()+"',"+cont+")\">"+row[64].trim()+"</td> "
                        + " <td id=\"fecha_modulacion["+cont+"]\" onclick=\"show_fecha_modulacion("+cont+")\">"+row[65].trim()+"</td> "
                        + " <td id=\"modalidad["+cont+"]\" onclick=\"show_modalidad("+cont+")\">"+row[66].trim()+"</td> "
                        + " <td id=\"resultado_modulacion["+cont+"]\" onclick=\"show_resultado_modulacion("+cont+","+AgentType+")\">"+row[67].trim()+"</td> "
                        + " <td id=\"fecha_reconocimiento["+cont+"]\" onclick=\"show_fecha_reconocimiento('"+row[68].trim()+"',"+cont+")\">"+row[68].trim()+"</td> "
                        + " <td id=\"fecha_liberacion["+cont+"]\" onclick=\"show_fecha_liberacion('"+row[69].trim()+"',"+cont+")\">"+row[69].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"sello_origen["+cont+"]\">"+row[70].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"sello_final["+cont+"]\">"+row[71].trim()+"</td> "
                        + " <td id=\"fecha_retencion_aut["+cont+"]\" onclick=\"show_fecha_retencion_aut('"+row[72].trim()+"',"+cont+")\">"+row[72].trim()+"</td> "
                        + " <td id=\"fecha_liberacion_aut["+cont+"]\" onclick=\"show_fecha_liberacion_aut('"+row[73].trim()+"',"+cont+")\">"+row[73].trim()+"</td> "
                        + " <td onmouseover=\"formComplet('"+AgentType+"',"+cont+")\"><select class=\"form-control\" style=\"border: none; outline: none;\" id=\"estatus_operacion["+cont+"]\" name=\"estatus_operacion["+cont+"]\" value=\""+row[74]+"\"> <option value=\""+row[98]+"\">"+row[74]+"</option>"+listStatusOperationEvent+"</select></td> "
                        + " <td contenteditable=\"true\" id=\"motivo_atraso["+cont+"]\">"+row[75].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"observaciones["+cont+"]\">"+row[76].trim()+"</td> ";
                 
                if(AgentType.equals("4001")||AgentType.equals("4006")){ //LOGIX Y VF

                 salida+= " <td id=\"llegada_a_nova["+cont+"]\" onclick=\"show_llegada_a_nova('"+row[77].trim()+"',"+cont+")\">"+row[77].trim()+"</td> "
                        + " <td id=\"llegada_a_globe_trade_sd["+cont+"]\" onclick=\"show_llegada_a_globe_trade_sd('"+row[78].trim()+"',"+cont+")\">"+row[78].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"archivo_m["+cont+"]\">"+row[79].trim()+"</td> "
                        + " <td id=\"fecha_archivo_m["+cont+"]\" onclick=\"show_fecha_archivo_m('"+row[80].trim()+"',"+cont+")\">"+row[80].trim()+"</td> "
                        + " <td id=\"fecha_solicit_manip["+cont+"]\" onclick=\"show_fecha_solicit_manip('"+row[81].trim()+"',"+cont+")\">"+row[81].trim()+"</td> "
                        + " <td id=\"fecha_vencim_manip["+cont+"]\" onclick=\"show_fecha_vencim_manip('"+row[82].trim()+"',"+cont+")\">"+row[82].trim()+"</td> "
                        + " <td id=\"fecha_confirm_clave_pedim["+cont+"]\" onclick=\"show_fecha_confirm_clave_pedim('"+row[83].trim()+"',"+cont+")\">"+row[83].trim()+"</td> "
                        + " <td id=\"fecha_recep_increment["+cont+"]\" onclick=\"show_fecha_recep_increment('"+row[84].trim()+"',"+cont+")\">"+row[84].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"t_e["+cont+"]\">"+row[85].trim()+"</td> "
                        + " <td id=\"fecha_vencim_inbound["+cont+"]\" onclick=\"show_fecha_vencim_inbound('"+row[86].trim()+"',"+cont+")\">"+row[86].trim()+"</td> ";
                } 

                if(AgentType.equals("4002")||AgentType.equals("4006")){  //CUSA Y VF

                 salida+=" <td contenteditable=\"true\" id=\"no_bultos["+cont+"]\">"+row[87].trim()+"</td> "
                       + " <td contenteditable=\"true\" id=\"peso_kg["+cont+"]\">"+row[88].trim()+"</td> "
                       + " <td id=\"transferencia["+cont+"]\" onclick=\"show_transferencia("+cont+")\">"+row[89].trim()+"</td> "
                       + " <td id=\"fecha_inicio_etiquetado["+cont+"]\" onclick=\"show_fecha_inicio_etiquetado('"+row[90].trim()+"',"+cont+")\">"+row[90].trim()+"</td> "
                       + " <td id=\"fecha_termino_etiquetado["+cont+"]\" onclick=\"show_fecha_termino_etiquetado('"+row[91].trim()+"',"+cont+")\">"+row[91].trim()+"</td> "
                       + " <td><input class=\"form-control\" style=\"border: none; outline: none;\" id=\"hora_termino_etiquetado["+cont+"]\" name=\"hora_termino_etiquetado["+cont+"]\" type=\"time\" value=\""+row[92].trim()+"\" autocomplete=\"off\"></td> "
                       + " <td contenteditable=\"true\" id=\"proveedor["+cont+"]\">"+row[93].trim()+"</td> "
                       + " <td contenteditable=\"true\" id=\"proveedor_carga["+cont+"]\">"+row[94].trim()+"</td> ";
                }
                
                salida+=" <td contenteditable=\"true\" id=\"fy["+cont+"]\">"+row[95].trim()+"</td> "
                      + " <td><a class=\"btn btn-primary text-uppercase\" onclick=\"AddLineCustoms("+cont+")\"><i class=\"fa fa-save\"></i></a></td> "
                       +"</tr>"; 
                          
                  cont++; 
                }     
            }
      
                salida+="<input type=\"hidden\" id=\"numCustoms\" name=\"numCustoms\" value=\""+cont+"\">";
                
         out.print(salida);
         oraDB.close(); //cerrar conexión
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarCustoms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarCustoms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}