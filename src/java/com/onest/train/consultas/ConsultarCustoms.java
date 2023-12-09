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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
            
            String selected_referenciaAA = request.getParameter("selected_referenciaAA").trim();
            String selected_evento = request.getParameter("selected_evento").trim();
            String selected_responsable = request.getParameter("selected_responsable").trim();
            String selected_final_destination = request.getParameter("selected_final_destination").trim();
            String selected_brand_division = request.getParameter("selected_brand_division").trim();
            String selected_division = request.getParameter("selected_division").trim();
            String selected_shipmentId = request.getParameter("selected_shipmentId").trim();
            String selected_containerId = request.getParameter("selected_containerId").trim();
            String selected_blAwbPro = request.getParameter("selected_blAwbPro").trim();
            String selected_loadTypeFinal = request.getParameter("selected_loadTypeFinal").trim();
            String selected_quantity = request.getParameter("selected_quantity").trim();
            String selected_pod = request.getParameter("selected_pod").trim();
            String selected_estDepartFromPol = request.getParameter("selected_estDepartFromPol").trim();
            String selected_etaRealPortOfDischarge = request.getParameter("selected_etaRealPortOfDischarge").trim();
            String selected_estEtaDc = request.getParameter("selected_estEtaDc").trim();
            String selected_inboundNotification = request.getParameter("selected_inboundNotification").trim();
            String selected_pol = request.getParameter("selected_pol").trim();
            String selected_aa = request.getParameter("selected_aa").trim();
            String selected_fechaMesVenta = request.getParameter("selected_fechaMesVenta").trim();
            String selected_prioridad = request.getParameter("selected_prioridad").trim();
            String selected_pais_origen = request.getParameter("selected_pais_origen").trim();
            String selected_size_container = request.getParameter("selected_size_container").trim();
            String selected_valor_usd = request.getParameter("selected_valor_usd").trim();
            String selected_eta_port_discharge = request.getParameter("selected_eta_port_discharge").trim();
            String selected_agente_aduanal = request.getParameter("selected_agente_aduanal").trim();
            String selected_pedimento_a1 = request.getParameter("selected_pedimento_a1").trim();
            String selected_pedimento_r1_1er = request.getParameter("selected_pedimento_r1_1er").trim();
            String selected_motivo_rectificacion_1er = request.getParameter("selected_motivo_rectificacion_1er").trim();
            String selected_pedimento_r1_2do = request.getParameter("selected_pedimento_r1_2do").trim();
            String selected_motivo_rectificacion_2do = request.getParameter("selected_motivo_rectificacion_2do").trim();
            String selected_fecha_recepcion_doc = request.getParameter("selected_fecha_recepcion_doc").trim();
            String selected_recinto = request.getParameter("selected_recinto").trim();
            String selected_naviera = request.getParameter("selected_naviera").trim();
            String selected_buque = request.getParameter("selected_buque").trim();
            String selected_fecha_revalidacion = request.getParameter("selected_fecha_revalidacion").trim();
            String selected_fecha_previo_origen = request.getParameter("selected_fecha_previo_origen").trim();
            String selected_fecha_previo_destino = request.getParameter("selected_fecha_previo_destino").trim();
            String selected_fecha_resultado_previo = request.getParameter("selected_fecha_resultado_previo").trim();
            String selected_proforma_final = request.getParameter("selected_proforma_final").trim();
            String selected_permiso = request.getParameter("selected_permiso").trim();
            String selected_fecha_envio = request.getParameter("selected_fecha_envio").trim();
            String selected_fecha_recepcion_perm = request.getParameter("selected_fecha_recepcion_perm").trim();
            String selected_fecha_activacion_perm = request.getParameter("selected_fecha_activacion_perm").trim();
            String selected_fecha_permisos_aut = request.getParameter("selected_fecha_permisos_aut").trim();
            String selected_co_pref_arancelaria = request.getParameter("selected_co_pref_arancelaria").trim();
            String selected_aplic_pref_arancelaria = request.getParameter("selected_aplic_pref_arancelaria").trim();
            String selected_req_uva = request.getParameter("selected_req_uva").trim();
            String selected_req_ca = request.getParameter("selected_req_ca").trim();
            String selected_fecha_recepcion_ca = request.getParameter("selected_fecha_recepcion_ca").trim();
            String selected_num_constancia_ca = request.getParameter("selected_num_constancia_ca").trim();
            String selected_monto_ca = request.getParameter("selected_monto_ca").trim();
            String selected_fecha_doc_completos = request.getParameter("selected_fecha_doc_completos").trim();
            String selected_fecha_pago_pedimento = request.getParameter("selected_fecha_pago_pedimento").trim();
            String selected_fecha_solicitud_transporte = request.getParameter("selected_fecha_solicitud_transporte").trim();
            String selected_fecha_modulacion = request.getParameter("selected_fecha_modulacion").trim();
            String selected_modalidad = request.getParameter("selected_modalidad").trim();
            String selected_resultado_modulacion = request.getParameter("selected_resultado_modulacion").trim();
            String selected_fecha_reconocimiento = request.getParameter("selected_fecha_reconocimiento").trim();
            String selected_fecha_liberacion = request.getParameter("selected_fecha_liberacion").trim();
            String selected_sello_origen = request.getParameter("selected_sello_origen").trim();
            String selected_sello_final = request.getParameter("selected_sello_final").trim();
            String selected_fecha_retencion_aut = request.getParameter("selected_fecha_retencion_aut").trim();
            String selected_fecha_liberacion_aut = request.getParameter("selected_fecha_liberacion_aut").trim();
            String selected_estatus_operacion = request.getParameter("selected_estatus_operacion").trim();
            String selected_motivo_atraso = request.getParameter("selected_motivo_atraso").trim();
            String selected_observaciones = request.getParameter("selected_observaciones").trim();
            String selected_llegada_a_nova = request.getParameter("selected_llegada_a_nova").trim();
            String selected_llegada_a_globe_trade_sd = request.getParameter("selected_llegada_a_globe_trade_sd").trim();
            String selected_archivo_m = request.getParameter("selected_archivo_m").trim();
            String selected_fecha_archivo_m = request.getParameter("selected_fecha_archivo_m").trim();
            String selected_fecha_solicit_manip = request.getParameter("selected_fecha_solicit_manip").trim();
            String selected_fecha_vencim_manip = request.getParameter("selected_fecha_vencim_manip").trim();
            String selected_fecha_confirm_clave_pedim = request.getParameter("selected_fecha_confirm_clave_pedim").trim();
            String selected_fecha_recep_increment = request.getParameter("selected_fecha_recep_increment").trim();
            String selected_t_e = request.getParameter("selected_t_e").trim();
            String selected_fecha_vencim_inbound = request.getParameter("selected_fecha_vencim_inbound").trim();
            String selected_no_bultos = request.getParameter("selected_no_bultos").trim();
            String selected_peso_kg = request.getParameter("selected_peso_kg").trim();
            String selected_transferencia = request.getParameter("selected_transferencia").trim();
            String selected_fecha_inicio_etiquetado = request.getParameter("selected_fecha_inicio_etiquetado").trim();
            String selected_fecha_termino_etiquetado = request.getParameter("selected_fecha_termino_etiquetado").trim();
            String selected_hora_termino_etiquetado = request.getParameter("selected_hora_termino_etiquetado").trim();
            String selected_proveedor = request.getParameter("selected_proveedor").trim();
            String selected_proveedor_carga = request.getParameter("selected_proveedor_carga").trim();
            String selected_fy = request.getParameter("selected_fy").trim();


            String caramelo_referenciaAA ="";
            String caramelo_evento ="";
            String caramelo_responsable ="";
            String caramelo_final_destination ="";
            String caramelo_brand_division ="";
            String caramelo_division ="";
            String caramelo_shipmentId ="";
            String caramelo_containerId ="";
            String caramelo_blAwbPro ="";
            String caramelo_loadTypeFinal ="";
            String caramelo_quantity ="";
            String caramelo_pod ="";
            String caramelo_estDepartFromPol ="";
            String caramelo_etaRealPortOfDischarge ="";
            String caramelo_estEtaDc ="";
            String caramelo_inboundNotification ="";
            String caramelo_pol ="";
            String caramelo_aa ="";
            String caramelo_fechaMesVenta ="";
            String caramelo_prioridad ="";
            String caramelo_pais_origen ="";
            String caramelo_size_container ="";
            String caramelo_valor_usd ="";
            String caramelo_eta_port_discharge ="";
            String caramelo_agente_aduanal ="";
            String caramelo_pedimento_a1 ="";
            String caramelo_pedimento_r1_1er ="";
            String caramelo_motivo_rectificacion_1er ="";
            String caramelo_pedimento_r1_2do ="";
            String caramelo_motivo_rectificacion_2do ="";
            String caramelo_fecha_recepcion_doc ="";
            String caramelo_recinto ="";
            String caramelo_naviera ="";
            String caramelo_buque ="";
            String caramelo_fecha_revalidacion ="";
            String caramelo_fecha_previo_origen ="";
            String caramelo_fecha_previo_destino ="";
            String caramelo_fecha_resultado_previo ="";
            String caramelo_proforma_final ="";
            String caramelo_permiso ="";
            String caramelo_fecha_envio ="";
            String caramelo_fecha_recepcion_perm ="";
            String caramelo_fecha_activacion_perm ="";
            String caramelo_fecha_permisos_aut ="";
            String caramelo_co_pref_arancelaria ="";
            String caramelo_aplic_pref_arancelaria ="";
            String caramelo_req_uva ="";
            String caramelo_req_ca ="";
            String caramelo_fecha_recepcion_ca ="";
            String caramelo_num_constancia_ca ="";
            String caramelo_monto_ca ="";
            String caramelo_fecha_doc_completos ="";
            String caramelo_fecha_pago_pedimento ="";
            String caramelo_fecha_solicitud_transporte ="";
            String caramelo_fecha_modulacion ="";
            String caramelo_modalidad ="";
            String caramelo_resultado_modulacion ="";
            String caramelo_fecha_reconocimiento ="";
            String caramelo_fecha_liberacion ="";
            String caramelo_sello_origen ="";
            String caramelo_sello_final ="";
            String caramelo_fecha_retencion_aut ="";
            String caramelo_fecha_liberacion_aut ="";
            String caramelo_estatus_operacion ="";
            String caramelo_motivo_atraso ="";
            String caramelo_observaciones ="";
            String caramelo_llegada_a_nova ="";
            String caramelo_llegada_a_globe_trade_sd ="";
            String caramelo_archivo_m ="";
            String caramelo_fecha_archivo_m ="";
            String caramelo_fecha_solicit_manip ="";
            String caramelo_fecha_vencim_manip ="";
            String caramelo_fecha_confirm_clave_pedim ="";
            String caramelo_fecha_recep_increment ="";
            String caramelo_t_e ="";
            String caramelo_fecha_vencim_inbound ="";
            String caramelo_no_bultos ="";
            String caramelo_peso_kg ="";
            String caramelo_transferencia ="";
            String caramelo_fecha_inicio_etiquetado ="";
            String caramelo_fecha_termino_etiquetado ="";
            String caramelo_hora_termino_etiquetado ="";
            String caramelo_proveedor ="";
            String caramelo_proveedor_carga ="";
            String caramelo_fy ="";
            String sql = "";
            
            //Generar caramelo: Opciones del multiselect
            if (!selected_referenciaAA.equals("")) { 
                String[] selected = selected_referenciaAA.split(",");
                for (String a : selected) {
                    caramelo_referenciaAA += "'" + a + "',";
                }
            }
            if (!selected_evento.equals("")) {
                String[] selected = selected_evento.split(",");
                for (String a : selected) {
                    caramelo_evento += a + ",";
                }
            }
            if (!selected_responsable.equals("")) {
                String[] selected = selected_responsable.split(",");
                for (String a : selected) {
                    caramelo_responsable += "'" + a + "',";
                }
            }
            if (!selected_final_destination.equals("")) {
                String[] selected = selected_final_destination.split(",");
                for (String a : selected) {
                    caramelo_final_destination += "'" + a + "',";
                }
            }
            if (!selected_brand_division.equals("")) {
                String[] selected = selected_brand_division.split(",");
                for (String a : selected) {
                    caramelo_brand_division += "'" + a + "',";
                }
            }
            if (!selected_division.equals("")) {
                String[] selected = selected_division.split(",");
                for (String a : selected) {
                    caramelo_division += "'" + a + "',";
                }
            }
            if (!selected_shipmentId.equals("")) {
                String[] selected = selected_shipmentId.split(",");
                for (String a : selected) {
                    caramelo_shipmentId += "'" + a + "',";
                }
            }
            if (!selected_containerId.equals("")) {
                String[] selected = selected_containerId.split(",");
                for (String a : selected) {
                    caramelo_containerId += "'" + a + "',";
                }
            }
            if (!selected_blAwbPro.equals("")) {
                String[] selected = selected_blAwbPro.split(",");
                for (String a : selected) {
                    caramelo_blAwbPro += "'" + a + "',";
                }
            }
            if (!selected_loadTypeFinal.equals("")) {
                String[] selected = selected_loadTypeFinal.split(",");
                for (String a : selected) {
                    caramelo_loadTypeFinal += "'" + a + "',";
                }
            }
            if (!selected_quantity.equals("")) {
                String[] selected = selected_quantity.split(",");
                for (String a : selected) {
                    caramelo_quantity += a + ",";
                }
            }
            if (!selected_pod.equals("")) {
                String[] selected = selected_pod.split(",");
                for (String a : selected) {
                    caramelo_pod += "'" + a + "',";
                }
            }
            if (!selected_estDepartFromPol.equals("")) {
                String[] selected = selected_estDepartFromPol.split(",");
                for (String a : selected) {
                    caramelo_estDepartFromPol += "'" + a + "',";
                }
            }
            if (!selected_etaRealPortOfDischarge.equals("")) {
                String[] selected = selected_etaRealPortOfDischarge.split(",");
                for (String a : selected) {
                    caramelo_etaRealPortOfDischarge += "'" + a + "',";
                }
            }
            if (!selected_estEtaDc.equals("")) {
                String[] selected = selected_estEtaDc.split(",");
                for (String a : selected) {
                    caramelo_estEtaDc += "'" + a + "',";
                }
            }
            if (!selected_inboundNotification.equals("")) {
                String[] selected = selected_inboundNotification.split(",");
                for (String a : selected) {
                    caramelo_inboundNotification += "'" + a + "',";
                }
            }
            if (!selected_pol.equals("")) {
                String[] selected = selected_pol.split(",");
                for (String a : selected) {
                    caramelo_pol += "'" + a + "',";
                }
            }
            if (!selected_aa.equals("")) {
                String[] selected = selected_aa.split(",");
                for (String a : selected) {
                    caramelo_aa += "'" + a + "',";
                }
            }
            if (!selected_fechaMesVenta.equals("")) {
                String[] selected = selected_fechaMesVenta.split(",");
                for (String a : selected) {
                    caramelo_fechaMesVenta += "'" + a + "',";
                }
            }
            if (!selected_prioridad.equals("")) {
                String[] selected = selected_prioridad.split(",");
                for (String a : selected) {
                    caramelo_prioridad += "'" + a + "',";
                }
            }
            if (!selected_pais_origen.equals("")) {
                String[] selected = selected_pais_origen.split(",");
                for (String a : selected) {
                    caramelo_pais_origen += "'" + a + "',";
                }
            }
            if (!selected_size_container.equals("")) {
                String[] selected = selected_size_container.split(",");
                for (String a : selected) {
                    caramelo_size_container += "'" + a + "',";
                }
            }
            if (!selected_valor_usd.equals("")) {
                String[] selected = selected_valor_usd.split(",");
                for (String a : selected) {
                    caramelo_valor_usd += "'" + a + "',";
                }
            }
            if (!selected_eta_port_discharge.equals("")) {
                String[] selected = selected_eta_port_discharge.split(",");
                for (String a : selected) {
                    caramelo_eta_port_discharge += "'" + a + "',";
                }
            }
            if (!selected_agente_aduanal.equals("")) {
                String[] selected = selected_agente_aduanal.split(",");
                for (String a : selected) {
                    caramelo_agente_aduanal += "'" + a + "',";
                }
            }
            if (!selected_pedimento_a1.equals("")) {
                String[] selected = selected_pedimento_a1.split(",");
                for (String a : selected) {
                    caramelo_pedimento_a1 += "'" + a + "',";
                }
            }
            if (!selected_pedimento_r1_1er.equals("")) {
                String[] selected = selected_pedimento_r1_1er.split(",");
                for (String a : selected) {
                    caramelo_pedimento_r1_1er += "'" + a + "',";
                }
            }
            if (!selected_motivo_rectificacion_1er.equals("")) {
                String[] selected = selected_motivo_rectificacion_1er.split(",");
                for (String a : selected) {
                    caramelo_motivo_rectificacion_1er += "'" + a + "',";
                }
            }
            if (!selected_pedimento_r1_2do.equals("")) {
                String[] selected = selected_pedimento_r1_2do.split(",");
                for (String a : selected) {
                    caramelo_pedimento_r1_2do += "'" + a + "',";
                }
            }
            if (!selected_motivo_rectificacion_2do.equals("")) {
                String[] selected = selected_motivo_rectificacion_2do.split(",");
                for (String a : selected) {
                    caramelo_motivo_rectificacion_2do += "'" + a + "',";
                }
            }
            if (!selected_fecha_recepcion_doc.equals("")) {
                String[] selected = selected_fecha_recepcion_doc.split(",");
                for (String a : selected) {
                    caramelo_fecha_recepcion_doc += "'" + a + "',";
                }
            }
            if (!selected_recinto.equals("")) {
                String[] selected = selected_recinto.split(",");
                for (String a : selected) {
                    caramelo_recinto += "'" + a + "',";
                }
            }
            if (!selected_naviera.equals("")) {
                String[] selected = selected_naviera.split(",");
                for (String a : selected) {
                    caramelo_naviera += "'" + a + "',";
                }
            }
            if (!selected_buque.equals("")) {
                String[] selected = selected_buque.split(",");
                for (String a : selected) {
                    caramelo_buque += "'" + a + "',";
                }
            }
            if (!selected_fecha_revalidacion.equals("")) {
                String[] selected = selected_fecha_revalidacion.split(",");
                for (String a : selected) {
                    caramelo_fecha_revalidacion += "'" + a + "',";
                }
            }
            if (!selected_fecha_previo_origen.equals("")) {
                String[] selected = selected_fecha_previo_origen.split(",");
                for (String a : selected) {
                    caramelo_fecha_previo_origen += "'" + a + "',";
                }
            }
            if (!selected_fecha_previo_destino.equals("")) {
                String[] selected = selected_fecha_previo_destino.split(",");
                for (String a : selected) {
                    caramelo_fecha_previo_destino += "'" + a + "',";
                }
            }
            if (!selected_fecha_resultado_previo.equals("")) {
                String[] selected = selected_fecha_resultado_previo.split(",");
                for (String a : selected) {
                    caramelo_fecha_resultado_previo += "'" + a + "',";
                }
            }
            if (!selected_proforma_final.equals("")) {
                String[] selected = selected_proforma_final.split(",");
                for (String a : selected) {
                    caramelo_proforma_final += "'" + a + "',";
                }
            }
            if (!selected_permiso.equals("")) {
                String[] selected = selected_permiso.split(",");
                for (String a : selected) {
                    caramelo_permiso += "'" + a + "',";
                }
            }
            if (!selected_fecha_envio.equals("")) {
                String[] selected = selected_fecha_envio.split(",");
                for (String a : selected) {
                    caramelo_fecha_envio += "'" + a + "',";
                }
            }
            if (!selected_fecha_recepcion_perm.equals("")) {
                String[] selected = selected_fecha_recepcion_perm.split(",");
                for (String a : selected) {
                    caramelo_fecha_recepcion_perm += "'" + a + "',";
                }
            }
            if (!selected_fecha_activacion_perm.equals("")) {
                String[] selected = selected_fecha_activacion_perm.split(",");
                for (String a : selected) {
                    caramelo_fecha_activacion_perm += "'" + a + "',";
                }
            }
            if (!selected_fecha_permisos_aut.equals("")) {
                String[] selected = selected_fecha_permisos_aut.split(",");
                for (String a : selected) {
                    caramelo_fecha_permisos_aut += "'" + a + "',";
                }
            }
            if (!selected_co_pref_arancelaria.equals("")) {
                String[] selected = selected_co_pref_arancelaria.split(",");
                for (String a : selected) {
                    caramelo_co_pref_arancelaria += "'" + a + "',";
                }
            }
            if (!selected_aplic_pref_arancelaria.equals("")) {
                String[] selected = selected_aplic_pref_arancelaria.split(",");
                for (String a : selected) {
                    caramelo_aplic_pref_arancelaria += "'" + a + "',";
                }
            }
            if (!selected_req_uva.equals("")) {
                String[] selected = selected_req_uva.split(",");
                for (String a : selected) {
                    caramelo_req_uva += "'" + a + "',";
                }
            }
            if (!selected_req_ca.equals("")) {
                String[] selected = selected_req_ca.split(",");
                for (String a : selected) {
                    caramelo_req_ca += "'" + a + "',";
                }
            }
            if (!selected_fecha_recepcion_ca.equals("")) {
                String[] selected = selected_fecha_recepcion_ca.split(",");
                for (String a : selected) {
                    caramelo_fecha_recepcion_ca += "'" + a + "',";
                }
            }
            if (!selected_num_constancia_ca.equals("")) {
                String[] selected = selected_num_constancia_ca.split(",");
                for (String a : selected) {
                    caramelo_num_constancia_ca += a + ",";
                }
            }
            if (!selected_monto_ca.equals("")) {
                String[] selected = selected_monto_ca.split(",");
                for (String a : selected) {
                    caramelo_monto_ca += a + ",";
                }
            }
            if (!selected_fecha_doc_completos.equals("")) {
                String[] selected = selected_fecha_doc_completos.split(",");
                for (String a : selected) {
                    caramelo_fecha_doc_completos += "'" + a + "',";
                }
            }
            if (!selected_fecha_pago_pedimento.equals("")) {
                String[] selected = selected_fecha_pago_pedimento.split(",");
                for (String a : selected) {
                    caramelo_fecha_pago_pedimento += "'" + a + "',";
                }
            }
            if (!selected_fecha_solicitud_transporte.equals("")) {
                String[] selected = selected_fecha_solicitud_transporte.split(",");
                for (String a : selected) {
                    caramelo_fecha_solicitud_transporte += "'" + a + "',";
                }
            }
            if (!selected_fecha_modulacion.equals("")) {
                String[] selected = selected_fecha_modulacion.split(",");
                for (String a : selected) {
                    caramelo_fecha_modulacion += "'" + a + "',";
                }
            }
            if (!selected_modalidad.equals("")) {
                String[] selected = selected_modalidad.split(",");
                for (String a : selected) {
                    caramelo_modalidad += "'" + a + "',";
                }
            }
            if (!selected_resultado_modulacion.equals("")) {
                String[] selected = selected_resultado_modulacion.split(",");
                for (String a : selected) {
                    caramelo_resultado_modulacion += "'" + a + "',";
                }
            }
            if (!selected_fecha_reconocimiento.equals("")) {
                String[] selected = selected_fecha_reconocimiento.split(",");
                for (String a : selected) {
                    caramelo_fecha_reconocimiento += "'" + a + "',";
                }
            }
            if (!selected_fecha_liberacion.equals("")) {
                String[] selected = selected_fecha_liberacion.split(",");
                for (String a : selected) {
                    caramelo_fecha_liberacion += "'" + a + "',";
                }
            }
            if (!selected_sello_origen.equals("")) {
                String[] selected = selected_sello_origen.split(",");
                for (String a : selected) {
                    caramelo_sello_origen += "'" + a + "',";
                }
            }
            if (!selected_sello_final.equals("")) {
                String[] selected = selected_sello_final.split(",");
                for (String a : selected) {
                    caramelo_sello_final += "'" + a + "',";
                }
            }
            if (!selected_fecha_retencion_aut.equals("")) {
                String[] selected = selected_fecha_retencion_aut.split(",");
                for (String a : selected) {
                    caramelo_fecha_retencion_aut += "'" + a + "',";
                }
            }
            if (!selected_fecha_liberacion_aut.equals("")) {
                String[] selected = selected_fecha_liberacion_aut.split(",");
                for (String a : selected) {
                    caramelo_fecha_liberacion_aut += "'" + a + "',";
                }
            }
            if (!selected_estatus_operacion.equals("")) {
                String[] selected = selected_estatus_operacion.split(",");
                for (String a : selected) {
                    caramelo_estatus_operacion += "'" + a + "',";
                }
            }
            if (!selected_motivo_atraso.equals("")) {
                String[] selected = selected_motivo_atraso.split(",");
                for (String a : selected) {
                    caramelo_motivo_atraso += "'" + a + "',";
                }
            }
            if (!selected_observaciones.equals("")) {
                String[] selected = selected_observaciones.split(",");
                for (String a : selected) {
                    caramelo_observaciones += "'" + a + "',";
                }
            }
            
        if (AgentType.equals("4001") || AgentType.equals("4006")) { //LOGIX Y VF    
            
            if (!selected_llegada_a_nova.equals("")) {
                String[] selected = selected_llegada_a_nova.split(",");
                for (String a : selected) {
                    caramelo_llegada_a_nova += "'" + a + "',";
                }
            }
            if (!selected_llegada_a_globe_trade_sd.equals("")) {
                String[] selected = selected_llegada_a_globe_trade_sd.split(",");
                for (String a : selected) {
                    caramelo_llegada_a_globe_trade_sd += "'" + a + "',";
                }
            }
            if (!selected_archivo_m.equals("")) {
                String[] selected = selected_archivo_m.split(",");
                for (String a : selected) {
                    caramelo_archivo_m += "'" + a + "',";
                }
            }
            if (!selected_fecha_archivo_m.equals("")) {
                String[] selected = selected_fecha_archivo_m.split(",");
                for (String a : selected) {
                    caramelo_fecha_archivo_m += "'" + a + "',";
                }
            }
            if (!selected_fecha_solicit_manip.equals("")) {
                String[] selected = selected_fecha_solicit_manip.split(",");
                for (String a : selected) {
                    caramelo_fecha_solicit_manip += "'" + a + "',";
                }
            }
            if (!selected_fecha_vencim_manip.equals("")) {
                String[] selected = selected_fecha_vencim_manip.split(",");
                for (String a : selected) {
                    caramelo_fecha_vencim_manip += "'" + a + "',";
                }
            }
            if (!selected_fecha_confirm_clave_pedim.equals("")) {
                String[] selected = selected_fecha_confirm_clave_pedim.split(",");
                for (String a : selected) {
                    caramelo_fecha_vencim_manip += "'" + a + "',";
                }
            }
            if (!selected_fecha_recep_increment.equals("")) {
                String[] selected = selected_fecha_recep_increment.split(",");
                for (String a : selected) {
                    caramelo_fecha_recep_increment += "'" + a + "',";
                }
            }
            if (!selected_t_e.equals("")) {
                String[] selected = selected_t_e.split(",");
                for (String a : selected) {
                    caramelo_t_e += "'" + a + "',";
                }
            }
            if (!selected_fecha_vencim_inbound.equals("")) {
                String[] selected = selected_fecha_vencim_inbound.split(",");
                for (String a : selected) {
                    caramelo_fecha_vencim_inbound += "'" + a + "',";
                }
            }
            
        }    
            
        if (AgentType.equals("4002") || AgentType.equals("4006")) {  //CUSA Y VF

            if (!selected_no_bultos.equals("")) {
                String[] selected = selected_no_bultos.split(",");
                for (String a : selected) {
                    caramelo_no_bultos += "'" + a + "',";
                }
            }
            if (!selected_peso_kg.equals("")) {
                String[] selected = selected_peso_kg.split(",");
                for (String a : selected) {
                    caramelo_peso_kg += "'" + a + "',";
                }
            }
            if (!selected_transferencia.equals("")) {
                String[] selected = selected_transferencia.split(",");
                for (String a : selected) {
                    caramelo_transferencia += "'" + a + "',";
                }
            }
            if (!selected_fecha_inicio_etiquetado.equals("")) {
                String[] selected = selected_fecha_inicio_etiquetado.split(",");
                for (String a : selected) {
                    caramelo_fecha_inicio_etiquetado += "'" + a + "',";
                }
            }
            if (!selected_fecha_termino_etiquetado.equals("")) {
                String[] selected = selected_fecha_termino_etiquetado.split(",");
                for (String a : selected) {
                    caramelo_fecha_termino_etiquetado += "'" + a + "',";
                }
            }
            if (!selected_hora_termino_etiquetado.equals("")) {
                String[] selected = selected_hora_termino_etiquetado.split(",");
                for (String a : selected) {
                    caramelo_hora_termino_etiquetado += "'" + a + "',";
                }
            }
            if (!selected_proveedor.equals("")) {
                String[] selected = selected_proveedor.split(",");
                for (String a : selected) {
                    caramelo_proveedor += "'" + a + "',";
                }
            }
            if (!selected_proveedor_carga.equals("")) {
                String[] selected = selected_proveedor_carga.split(",");
                for (String a : selected) {
                    caramelo_proveedor_carga += "'" + a + "',";
                }
            }
            
        }    
            if (!selected_fy.equals("")) {
                String[] selected = selected_fy.split(",");
                for (String a : selected) {
                    caramelo_fy += "'" + a + "',";
                }
            }

            if (!selected_referenciaAA.equals("")) {
                caramelo_referenciaAA = caramelo_referenciaAA.replaceAll(",$", "");
            }
            if (!selected_evento.equals("")) {
                caramelo_evento = caramelo_evento.replaceAll(",$", "");
            }
            if (!selected_responsable.equals("")) {
                caramelo_responsable = caramelo_responsable.replaceAll(",$", "");
            }
            if (!selected_final_destination.equals("")) {
                caramelo_final_destination = caramelo_final_destination.replaceAll(",$", "");
            }
            if (!selected_brand_division.equals("")) {
                caramelo_brand_division = caramelo_brand_division.replaceAll(",$", "");
            }
            if (!selected_division.equals("")) {
                caramelo_division = caramelo_division.replaceAll(",$", "");
            }
            if (!selected_shipmentId.equals("")) {
                caramelo_shipmentId = caramelo_shipmentId.replaceAll(",$", "");
            }
            if (!selected_containerId.equals("")) {
                caramelo_containerId = caramelo_containerId.replaceAll(",$", "");
            }
            if (!selected_blAwbPro.equals("")) {
                caramelo_blAwbPro = caramelo_blAwbPro.replaceAll(",$", "");
            }
            if (!selected_loadTypeFinal.equals("")) {
                caramelo_loadTypeFinal = caramelo_loadTypeFinal.replaceAll(",$", "");
            }
            if (!selected_quantity.equals("")) {
                caramelo_quantity = caramelo_quantity.replaceAll(",$", "");
            }
            if (!selected_pod.equals("")) {
                caramelo_pod = caramelo_pod.replaceAll(",$", "");
            }
            if (!selected_estDepartFromPol.equals("")) {
                caramelo_estDepartFromPol = caramelo_estDepartFromPol.replaceAll(",$", "");
            }
            if (!selected_etaRealPortOfDischarge.equals("")) {
                caramelo_etaRealPortOfDischarge = caramelo_etaRealPortOfDischarge.replaceAll(",$", "");
            }
            if (!selected_estEtaDc.equals("")) {
                caramelo_estEtaDc = caramelo_estEtaDc.replaceAll(",$", "");
            }
            if (!selected_inboundNotification.equals("")) {
                caramelo_inboundNotification = caramelo_inboundNotification.replaceAll(",$", "");
            }
            if (!selected_pol.equals("")) {
                caramelo_pol = caramelo_pol.replaceAll(",$", "");
            }
            if (!selected_aa.equals("")) {
                caramelo_aa = caramelo_aa.replaceAll(",$", "");
            }
            if (!selected_fechaMesVenta.equals("")) {
                caramelo_fechaMesVenta = caramelo_fechaMesVenta.replaceAll(",$", "");
            }
            if (!selected_prioridad.equals("")) {
                caramelo_prioridad = caramelo_prioridad.replaceAll(",$", "");
            }
            if (!selected_pais_origen.equals("")) {
                caramelo_pais_origen = caramelo_pais_origen.replaceAll(",$", "");
            }
            if (!selected_size_container.equals("")) {
                caramelo_size_container = caramelo_size_container.replaceAll(",$", "");
            }
            if (!selected_valor_usd.equals("")) {
                caramelo_valor_usd = caramelo_valor_usd.replaceAll(",$", "");
            }
            if (!selected_eta_port_discharge.equals("")) {
                caramelo_eta_port_discharge = caramelo_eta_port_discharge.replaceAll(",$", "");
            }
            if (!selected_agente_aduanal.equals("")) {
                caramelo_agente_aduanal = caramelo_agente_aduanal.replaceAll(",$", "");
            }
            if (!selected_pedimento_a1.equals("")) {
                caramelo_pedimento_a1 = caramelo_pedimento_a1.replaceAll(",$", "");
            }
            if (!selected_pedimento_r1_1er.equals("")) {
                caramelo_pedimento_r1_1er = caramelo_pedimento_r1_1er.replaceAll(",$", "");
            }
            if (!selected_motivo_rectificacion_1er.equals("")) {
                caramelo_motivo_rectificacion_1er = caramelo_motivo_rectificacion_1er.replaceAll(",$", "");
            }
            if (!selected_pedimento_r1_2do.equals("")) {
                caramelo_pedimento_r1_2do = caramelo_pedimento_r1_2do.replaceAll(",$", "");
            }
            if (!selected_motivo_rectificacion_2do.equals("")) {
                caramelo_motivo_rectificacion_2do = caramelo_motivo_rectificacion_2do.replaceAll(",$", "");
            }
            if (!selected_fecha_recepcion_doc.equals("")) {
                caramelo_fecha_recepcion_doc = caramelo_fecha_recepcion_doc.replaceAll(",$", "");
            }
            if (!selected_recinto.equals("")) {
                caramelo_recinto = caramelo_recinto.replaceAll(",$", "");
            }
            if (!selected_naviera.equals("")) {
                caramelo_naviera = caramelo_naviera.replaceAll(",$", "");
            }
            if (!selected_buque.equals("")) {
                caramelo_buque = caramelo_buque.replaceAll(",$", "");
            }
            if (!selected_fecha_revalidacion.equals("")) {
                caramelo_fecha_revalidacion = caramelo_fecha_revalidacion.replaceAll(",$", "");
            }
            if (!selected_fecha_previo_origen.equals("")) {
                caramelo_fecha_previo_origen = caramelo_fecha_previo_origen.replaceAll(",$", "");
            }
            if (!selected_fecha_previo_destino.equals("")) {
                caramelo_fecha_previo_destino = caramelo_fecha_previo_destino.replaceAll(",$", "");
            }
            if (!selected_fecha_resultado_previo.equals("")) {
                caramelo_fecha_resultado_previo = caramelo_fecha_resultado_previo.replaceAll(",$", "");
            }
            if (!selected_proforma_final.equals("")) {
                caramelo_proforma_final = caramelo_proforma_final.replaceAll(",$", "");
            }
            if (!selected_permiso.equals("")) {
                caramelo_permiso = caramelo_permiso.replaceAll(",$", "");
            }
            if (!selected_fecha_envio.equals("")) {
                caramelo_fecha_envio = caramelo_fecha_envio.replaceAll(",$", "");
            }
            if (!selected_fecha_recepcion_perm.equals("")) {
                caramelo_fecha_recepcion_perm = caramelo_fecha_recepcion_perm.replaceAll(",$", "");
            }
            if (!selected_fecha_activacion_perm.equals("")) {
                caramelo_fecha_activacion_perm = caramelo_fecha_activacion_perm.replaceAll(",$", "");
            }
            if (!selected_fecha_permisos_aut.equals("")) {
                caramelo_fecha_permisos_aut = caramelo_fecha_permisos_aut.replaceAll(",$", "");
            }
            if (!selected_co_pref_arancelaria.equals("")) {
                caramelo_co_pref_arancelaria = caramelo_co_pref_arancelaria.replaceAll(",$", "");
            }
            if (!selected_aplic_pref_arancelaria.equals("")) {
                caramelo_aplic_pref_arancelaria = caramelo_aplic_pref_arancelaria.replaceAll(",$", "");
            }
            if (!selected_req_uva.equals("")) {
                caramelo_req_uva = caramelo_req_uva.replaceAll(",$", "");
            }
            if (!selected_req_ca.equals("")) {
                caramelo_req_ca = caramelo_req_ca.replaceAll(",$", "");
            }
            if (!selected_fecha_recepcion_ca.equals("")) {
                caramelo_fecha_recepcion_ca = caramelo_fecha_recepcion_ca.replaceAll(",$", "");
            }
            if (!selected_num_constancia_ca.equals("")) {
                caramelo_num_constancia_ca = caramelo_num_constancia_ca.replaceAll(",$", "");
            }
            if (!selected_monto_ca.equals("")) {
                caramelo_monto_ca = caramelo_monto_ca.replaceAll(",$", "");
            }
            if (!selected_fecha_doc_completos.equals("")) {
                caramelo_fecha_doc_completos = caramelo_fecha_doc_completos.replaceAll(",$", "");
            }
            if (!selected_fecha_pago_pedimento.equals("")) {
                caramelo_fecha_pago_pedimento = caramelo_fecha_pago_pedimento.replaceAll(",$", "");
            }
            if (!selected_fecha_solicitud_transporte.equals("")) {
                caramelo_fecha_solicitud_transporte = caramelo_fecha_solicitud_transporte.replaceAll(",$", "");
            }
            if (!selected_fecha_modulacion.equals("")) {
                caramelo_fecha_modulacion = caramelo_fecha_modulacion.replaceAll(",$", "");
            }
            if (!selected_modalidad.equals("")) {
                caramelo_modalidad = caramelo_modalidad.replaceAll(",$", "");
            }
            if (!selected_resultado_modulacion.equals("")) {
                caramelo_resultado_modulacion = caramelo_resultado_modulacion.replaceAll(",$", "");
            }
            if (!selected_fecha_reconocimiento.equals("")) {
                caramelo_fecha_reconocimiento = caramelo_fecha_reconocimiento.replaceAll(",$", "");
            }
            if (!selected_fecha_liberacion.equals("")) {
                caramelo_fecha_liberacion = caramelo_fecha_liberacion.replaceAll(",$", "");
            }
            if (!selected_sello_origen.equals("")) {
                caramelo_sello_origen = caramelo_sello_origen.replaceAll(",$", "");
            }
            if (!selected_sello_final.equals("")) {
                caramelo_sello_final = caramelo_sello_final.replaceAll(",$", "");
            }
            if (!selected_fecha_retencion_aut.equals("")) {
                caramelo_fecha_retencion_aut = caramelo_fecha_retencion_aut.replaceAll(",$", "");
            }
            if (!selected_fecha_liberacion_aut.equals("")) {
                caramelo_fecha_liberacion_aut = caramelo_fecha_liberacion_aut.replaceAll(",$", "");
            }
            if (!selected_estatus_operacion.equals("")) {
                caramelo_estatus_operacion = caramelo_estatus_operacion.replaceAll(",$", "");
            }
            if (!selected_motivo_atraso.equals("")) {
                caramelo_motivo_atraso = caramelo_motivo_atraso.replaceAll(",$", "");
            }
            if (!selected_observaciones.equals("")) {
                caramelo_observaciones = caramelo_observaciones.replaceAll(",$", "");
            }
            
        if (AgentType.equals("4001") || AgentType.equals("4006")) { //LOGIX Y VF
            
            if (!selected_llegada_a_nova.equals("")) {
                caramelo_llegada_a_nova = caramelo_llegada_a_nova.replaceAll(",$", "");
            }
            if (!selected_llegada_a_globe_trade_sd.equals("")) {
                caramelo_llegada_a_globe_trade_sd = caramelo_llegada_a_globe_trade_sd.replaceAll(",$", "");
            }
            if (!selected_archivo_m.equals("")) {
                caramelo_archivo_m = caramelo_archivo_m.replaceAll(",$", "");
            }
            if (!selected_fecha_archivo_m.equals("")) {
                caramelo_fecha_archivo_m = caramelo_fecha_archivo_m.replaceAll(",$", "");
            }
            if (!selected_fecha_solicit_manip.equals("")) {
                caramelo_fecha_solicit_manip = caramelo_fecha_solicit_manip.replaceAll(",$", "");
            }
            if (!selected_fecha_vencim_manip.equals("")) {
                caramelo_fecha_vencim_manip = caramelo_fecha_vencim_manip.replaceAll(",$", "");
            }
            if (!selected_fecha_confirm_clave_pedim.equals("")) {
                caramelo_fecha_confirm_clave_pedim = caramelo_fecha_confirm_clave_pedim.replaceAll(",$", "");
            }
            if (!selected_fecha_recep_increment.equals("")) {
                caramelo_fecha_recep_increment = caramelo_fecha_recep_increment.replaceAll(",$", "");
            }
            if (!selected_t_e.equals("")) {
                caramelo_t_e = caramelo_t_e.replaceAll(",$", "");
            }
            if (!selected_fecha_vencim_inbound.equals("")) {
                caramelo_fecha_vencim_inbound = caramelo_fecha_vencim_inbound.replaceAll(",$", "");
            }
            if (!selected_no_bultos.equals("")) {
                caramelo_no_bultos = caramelo_no_bultos.replaceAll(",$", "");
            }
            
        }
        
        if (AgentType.equals("4002") || AgentType.equals("4006")) {  //CUSA Y VF
            
            if (!selected_peso_kg.equals("")) {
                caramelo_peso_kg = caramelo_peso_kg.replaceAll(",$", "");
            }
            if (!selected_transferencia.equals("")) {
                caramelo_transferencia = caramelo_transferencia.replaceAll(",$", "");
            }
            if (!selected_fecha_inicio_etiquetado.equals("")) {
                caramelo_fecha_inicio_etiquetado = caramelo_fecha_inicio_etiquetado.replaceAll(",$", "");
            }
            if (!selected_fecha_termino_etiquetado.equals("")) {
                caramelo_fecha_termino_etiquetado = caramelo_fecha_termino_etiquetado.replaceAll(",$", "");
            }
            if (!selected_hora_termino_etiquetado.equals("")) {
                caramelo_hora_termino_etiquetado = caramelo_hora_termino_etiquetado.replaceAll(",$", "");
            }
            if (!selected_proveedor.equals("")) {
                caramelo_proveedor = caramelo_proveedor.replaceAll(",$", "");
            }
            if (!selected_proveedor_carga.equals("")) {
                caramelo_proveedor_carga = caramelo_proveedor_carga.replaceAll(",$", "");
            }
            
        }
        
            if (!selected_fy.equals("")) {
                caramelo_fy = caramelo_fy.replaceAll(",$", "");
            }

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
                    /*15*/ + " TO_CHAR(GTN.FECHA_CAPTURA, 'MM/DD/YYYY') AS FECHA_CAPTURA, "//INBOUND NOTIFICATION
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

            if (!AgentType.equals("4006")) { //VF GENERAL
                sql += " AND TIP1.AGENTE_ADUANAL_ID IN ('" + AgentType + "') ";
            }
            if (!caramelo_referenciaAA.equals("")) {  //Referencia AA
                sql += " AND TIC.REFERENCIA_AA IN (" + caramelo_referenciaAA + ") ";
            }
            if (!caramelo_evento.equals("")) {  //Evento
                sql += " AND TIE.ID_EVENTO IN (" + caramelo_evento + ") ";
            }
            if (!caramelo_responsable.equals("")) {  //Responsable
                sql += " AND BP.RESPONSABLE IN (" + caramelo_responsable + ") ";
            }
            if (!caramelo_final_destination.equals("")) {  //Final Destination
                sql += " AND GTN.FINAL_DESTINATION IN (" + caramelo_final_destination + ") ";
            }
            if (!caramelo_brand_division.equals("")) {  //Brand-Division
                sql += " AND TIBD.NOMBRE_BD IN (" + caramelo_brand_division + ") ";
            }
            if (!caramelo_division.equals("")) {  //División
                sql += " AND TID.DIVISION_NOMBRE IN (" + caramelo_division + ") ";
            }
            if (!caramelo_shipmentId.equals("")) {  //Shipment Id
                sql += " AND GTN.SHIPMENT_ID IN (" + caramelo_shipmentId + ") ";
            }
            if (!caramelo_containerId.equals("")) {  //Container
                sql += " AND GTN.CONTAINER1 IN (" + caramelo_containerId + ") ";
            }
            if (!caramelo_blAwbPro.equals("")) {  //BL/AWB/PRO
                sql += " AND GTN.BL_AWB_PRO IN (" + caramelo_blAwbPro + ") ";
            }
            if (!caramelo_loadTypeFinal.equals("")) { //Load Type
                sql += " AND GTN.LOAD_TYPE IN (" + caramelo_loadTypeFinal + ") ";
            }
            if (!caramelo_quantity.equals("")) { //Quantity
                sql += " AND SQ.SUMA IN (" + caramelo_quantity + ") ";
            }
            if (!caramelo_pod.equals("")) { //Pod
                sql += " AND TIP1.NOMBRE_POD IN (" + caramelo_pod + ") ";
            }
            if (!caramelo_estDepartFromPol.equals("")) { //Departure Pol 
                sql += " AND GTN.EST_DEPARTURE_POL IN (" + caramelo_estDepartFromPol + ") ";
            }
            if (!caramelo_etaRealPortOfDischarge.equals("")) { //ETA REAL Port of Discharge
                sql += " AND TO_DATE(trunc(GTN.ETA_PORT_DISCHARGE),'DD/MM/YY') = TO_DATE(" + caramelo_etaRealPortOfDischarge + ",'MM/DD/YY') ";
            }
            if (!caramelo_estEtaDc.equals("")) { //Est. Eta DC
                sql += " AND GTN.ETA_PLUS2 IN (" + caramelo_estEtaDc + ") ";
            }
            if (!caramelo_inboundNotification.equals("")) { //Inbound notification
                sql += " AND TO_DATE(trunc(GTN.FECHA_CAPTURA),'DD/MM/YYYY') = TO_DATE(" + caramelo_inboundNotification + ",'MM/DD/YYYY') ";
            }
            if (!caramelo_pol.equals("")) { //Pol
                sql += " AND TIP2.NOMBRE_POL IN (" + caramelo_pol + ") ";
            }
            if (!caramelo_aa.equals("")) { //Agente Aduanal
                sql += " AND TAA.AGENTE_ADUANAL_NOMBRE IN (" + caramelo_aa + ") ";
            }
            if (!caramelo_fechaMesVenta.equals("")) { //Fecha Mes de Venta
                sql += " AND TO_DATE(trunc(TIE.FECHA_CAPTURA),'DD/MM/YY') = TO_DATE(" + caramelo_fechaMesVenta + ",'MM/DD/YY') ";
            }
            if (!caramelo_prioridad.equals("")) { //Prioridad
                sql += " AND TIC.PRIORIDAD IN (" + caramelo_prioridad + ") ";
            }
            if (!caramelo_pais_origen.equals("")) { // País Origen 
                sql += " AND TIC.PAIS_ORIGEN IN (" + caramelo_pais_origen + ") ";
            }
            if (!caramelo_size_container.equals("")) { // Size Container
                sql += " AND TIC.SIZE_CONTAINER IN (" + caramelo_size_container + ") ";
            }
            if (!caramelo_valor_usd.equals("")) { // Valor USD
                sql += " AND TIC.VALOR_USD IN (" + caramelo_valor_usd + ") ";
            }
            if (!caramelo_eta_port_discharge.equals("")) { // ETA Port Of Discharge
                sql += " AND TO_DATE(trunc(TIC.ETA_PORT_OF_DISCHARGE),'DD/MM/YY') = TO_DATE(" + caramelo_eta_port_discharge + ",'MM/DD/YY') ";
            }
            if (!caramelo_agente_aduanal.equals("")) { // Agente Aduanal 
                sql += " AND TIC.AGENTE_ADUANAL IN (" + caramelo_agente_aduanal + ") ";
            }
            if (!caramelo_pedimento_a1.equals("")) { // Pedimento A1 
                sql += " AND TIC.PEDIMENTO_A1 IN (" + caramelo_pedimento_a1 + ") ";
            }
            if (!caramelo_pedimento_r1_1er.equals("")) { // Pedimento R1
                sql += " AND TIC.PEDIMENTO_R1 IN (" + caramelo_pedimento_r1_1er + ") ";
            }
            if (!caramelo_motivo_rectificacion_1er.equals("")) { // Motivo Rectificación 1
                sql += " AND TIC.MOTIVO_RECTIFICACION_1 IN (" + caramelo_motivo_rectificacion_1er + ") ";
            }
            if (!caramelo_pedimento_r1_2do.equals("")) { // Pedimento R1 (2DO)
                sql += " AND TIC.PEDIMENTO_R1_2DO IN (" + caramelo_pedimento_r1_2do + ") ";
            }
            if (!caramelo_motivo_rectificacion_2do.equals("")) { // Motivo Rectificación 2
                sql += " AND TIC.MOTIVO_RECTIFICACION_2 IN (" + caramelo_motivo_rectificacion_2do + ") ";
            }
            if (!caramelo_fecha_recepcion_doc.equals("")) { // Fecha Recepción Documentos
                sql += " AND TO_DATE(trunc(TIC.FECHA_RECEPCION_DOCUMENTOS),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_recepcion_doc + ",'MM/DD/YY') ";
            }
            if (!caramelo_recinto.equals("")) { // Recinto
                sql += " AND TIC.RECINTO IN (" + caramelo_recinto + ") ";
            }
            if (!caramelo_naviera.equals("")) { // Naviera/Forwarder
                sql += " AND TIC.NAVIERA_FORWARDER IN (" + caramelo_naviera + ") ";
            }
            if (!caramelo_buque.equals("")) { // Buque
                sql += " AND TIC.BUQUE IN (" + caramelo_buque + ") ";
            }
            if (!caramelo_fecha_revalidacion.equals("")) { // Fecha Revalidación/Liberación de BL
                sql += " AND TO_DATE(trunc(TIC.FECHA_REVALID_LIBE_BL),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_revalidacion + ",'MM/DD/YY') ";
            }
            if (!caramelo_fecha_previo_origen.equals("")) { // Fecha Previo Origen
                sql += " AND TO_DATE(trunc(TIC.FECHA_PREVIO_ORIGEN),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_previo_origen + ",'MM/DD/YY') ";
            }
            if (!caramelo_fecha_previo_destino.equals("")) { // Fecha Previo en destino
                sql += " AND TO_DATE(trunc(TIC.FECHA_PREVIO_DESTINO),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_previo_destino + ",'MM/DD/YY') ";
            }
            if (!caramelo_fecha_resultado_previo.equals("")) { // Fecha Resultado Previo
                sql += " AND TO_DATE(trunc(TIC.FECHA_RESULTADO_PREVIO),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_resultado_previo + ",'MM/DD/YY') ";
            }
            if (!caramelo_proforma_final.equals("")) { // Proforma Final 
                sql += " AND TIC.PROFORMA_FINAL IN (" + caramelo_proforma_final + ") ";
            }
            if (!caramelo_permiso.equals("")) { // Requiere permiso
                sql += " AND TIC.REQUIERE_PERMISO IN (" + caramelo_permiso + ") ";
            }
            if (!caramelo_fecha_envio.equals("")) { // Fecha envío Fichas/notas
                sql += " AND TO_DATE(trunc(TIC.FECHA_ENVIO_FICHAS_NOTAS),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_envio + ",'MM/DD/YY') ";
            }
            if (!caramelo_fecha_recepcion_perm.equals("")) { // Fec. Recepción de permisos tramit.
                sql += " AND TO_DATE(trunc(TIC.FEC_RECEPCION_PERMISOS_TRAMIT),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_recepcion_perm + ",'MM/DD/YY') ";
            }
            if (!caramelo_fecha_activacion_perm.equals("")) { // Fec. Act Permisos (Inic Vigencia)
                sql += " AND TO_DATE(trunc(TIC.FEC_ACT_PERMISOS),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_activacion_perm + ",'MM/DD/YY') ";
            }
            if (!caramelo_fecha_permisos_aut.equals("")) { // Fec. Perm. Aut. (Fin de Vigencia) 
                sql += " AND TO_DATE(trunc(TIC.FEC_PERM_AUT),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_permisos_aut + ",'MM/DD/YY') ";
            }
            if (!caramelo_co_pref_arancelaria.equals("")) { // Cuenta con CO para aplicar preferencia Arancelaria
                sql += " AND TIC.CO_APLIC_PREF_ARANCELARIA IN (" + caramelo_co_pref_arancelaria + ") ";
            }
            if (!caramelo_aplic_pref_arancelaria.equals("")) { // Aplico Preferencia Arancelaria 
                sql += " AND TIC.APLIC_PREF_ARANCELARIA_CO IN (" + caramelo_aplic_pref_arancelaria + ") ";
            }
            if (!caramelo_req_uva.equals("")) { // Requiere UVA
                sql += " AND TIC.REQUIERE_UVA IN (" + caramelo_req_uva + ") ";
            }
            if (!caramelo_req_ca.equals("")) { // Requiere CA
                sql += " AND TIC.REQUIERE_CA IN (" + caramelo_req_ca + ") ";
            }
            if (!caramelo_fecha_recepcion_ca.equals("")) { // Fecha Recepción CA
                sql += " AND TO_DATE(trunc(TIC.FECHA_RECEPCION_CA),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_recepcion_ca + ",'MM/DD/YY') ";
            }
            if (!caramelo_num_constancia_ca.equals("")) { // Número de Constancia CA 
                sql += " AND IC.NÚMERO_CONSTANCIA_CA IN (" + caramelo_num_constancia_ca + ") ";
            }
            if (!caramelo_monto_ca.equals("")) { // Monto CA
                sql += " AND TIC.MONTO_CA IN (" + caramelo_monto_ca + ") ";
            }
            if (!caramelo_fecha_doc_completos.equals("")) { // Fecha Documentos Completos
                sql += " AND TO_DATE(trunc(TIC.FECHA_DOCUMENTOS_COMPLETOS),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_doc_completos + ",'MM/DD/YY') ";
            }
            if (!caramelo_fecha_pago_pedimento.equals("")) { // Fecha Pago Pedimento
                sql += " AND TO_DATE(trunc(TIC.FECHA_PAGO_PEDIMENTO),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_pago_pedimento + ",'MM/DD/YY') ";
            }
            if (!caramelo_fecha_solicitud_transporte.equals("")) { // Fecha Solicitud de transporte
                sql += " AND TO_DATE(trunc(TIC.FECHA_SOLICITUD_TRANSPORTE),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_solicitud_transporte + ",'MM/DD/YY') ";
            }
            if (!caramelo_fecha_modulacion.equals("")) { // Fecha Modulacion
                sql += " AND TO_DATE(trunc(TIC.FECHA_MODULACION),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_modulacion + ",'MM/DD/YY') ";
            }
            if (!caramelo_modalidad.equals("")) { // Modalidad
                sql += " AND TIC.MODALIDAD_CAMION_TREN IN (" + caramelo_modalidad + ") ";
            }
            if (!caramelo_resultado_modulacion.equals("")) { // Resultado Modulacion
                sql += " AND TIC.RESULT_MODULACION_VERDE_ROJO IN (" + caramelo_resultado_modulacion + ") ";
            }
            if (!caramelo_fecha_reconocimiento.equals("")) { // Fecha Reconocimiento
                sql += " AND TO_DATE(trunc(TIC.FECHA_RECONOCIMIENTO),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_reconocimiento + ",'MM/DD/YY') ";
            }
            if (!caramelo_fecha_liberacion.equals("")) { // Fecha Liberacion
                sql += " AND TO_DATE(trunc(TIC.FECHA_LIBERACION),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_liberacion + ",'MM/DD/YY') ";
            }
            if (!caramelo_sello_origen.equals("")) { // Sello Origen 
                sql += " AND TIC.SELLO_ORIGEN IN (" + caramelo_sello_origen + ") ";
            }
            if (!caramelo_sello_final.equals("")) { // Sello Final
                sql += " AND TIC.SELLO_FINAL IN (" + caramelo_sello_final + ") ";
            }
            if (!caramelo_fecha_retencion_aut.equals("")) { // Fecha de retencion por la autoridad
                sql += " AND TO_DATE(trunc(TIC.FECHA_RETENCION_AUTORIDAD),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_retencion_aut + ",'MM/DD/YY') ";
            }
            if (!caramelo_fecha_liberacion_aut.equals("")) { // Fec. de liberacion por ret. de la aut.
                sql += " AND TO_DATE(trunc(TIC.FECHA_LIB_POR_RET_AUT),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_liberacion_aut + ",'MM/DD/YY') ";
            }
            if (!caramelo_estatus_operacion.equals("")) { // Estatus de la operación
                sql += " AND TEC.DESCRIPCION_ESTADO IN (" + caramelo_estatus_operacion + ") ";
            }
            if (!caramelo_motivo_atraso.equals("")) { // Motivo Atraso
                sql += " AND TIC.MOTIVO_ATRASO IN (" + caramelo_motivo_atraso + ") ";
            }
            if (!caramelo_observaciones.equals("")) { // Observaciones
                sql += " AND TIC.OBSERVACIONES IN (" + caramelo_observaciones + ") ";
            }
            
        if (AgentType.equals("4001") || AgentType.equals("4006")) { //LOGIX Y VF    
            
            if (!caramelo_llegada_a_nova.equals("")) { // Llegada a NOVA
                sql += " AND TIC.LLEGADA_A_NOVA IN (" + caramelo_llegada_a_nova + ") ";
            }
            if (!caramelo_llegada_a_globe_trade_sd.equals("")) { // Llegada a Globe trade SD  
                sql += " AND TIC.LLEGADA_A_GLOBE_TRADE_SD IN (" + caramelo_llegada_a_globe_trade_sd + ") ";
            }
            if (!caramelo_archivo_m.equals("")) { // Archivo M 
                sql += " AND TIC.ARCHIVO_M IN (" + caramelo_archivo_m + ") ";
            }
            if (!caramelo_fecha_archivo_m.equals("")) { // Fecha de Archivo M 
                sql += " AND TO_DATE(trunc(TIC.FECHA_ARCHIVO_M),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_archivo_m + ",'MM/DD/YY') ";
            }
            if (!caramelo_fecha_solicit_manip.equals("")) { // Fecha Solicitud de Manipulacion
                sql += " AND TO_DATE(trunc(TIC.FECHA_SOLICIT_MANIP),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_solicit_manip + ",'MM/DD/YY') ";
            }
            if (!caramelo_fecha_vencim_manip.equals("")) { // Fecha de vencimiento de Manipulacion
                sql += " AND TO_DATE(trunc(TIC.FECHA_VENCIM_MANIP),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_vencim_manip + ",'MM/DD/YY') ";
            }
            if (!caramelo_fecha_confirm_clave_pedim.equals("")) { // Fecha confirmacion Clave de Pedimento
                sql += " AND TO_DATE(trunc(TIC.FECHA_CONFIRM_CLAVE_PEDIM),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_confirm_clave_pedim + ",'MM/DD/YY') ";
            }
            if (!caramelo_fecha_recep_increment.equals("")) { // Fecha de Recepcion de Incrementables
                sql += " AND TO_DATE(trunc(TIC.FECHA_RECEP_INCREMENT),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_recep_increment + ",'MM/DD/YY') ";
            }
            if (!caramelo_t_e.equals("")) { // T&E 
                sql += " AND TIC.T_E IN (" + caramelo_t_e + ") ";
            }
            if (!caramelo_fecha_vencim_inbound.equals("")) { // Fecha de Vencimiento del Inbound  
                sql += " AND TO_DATE(trunc(TIC.FECHA_VENCIM_INBOUND),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_vencim_inbound + ",'MM/DD/YY') ";
            }
            
        }
        
        if (AgentType.equals("4002") || AgentType.equals("4006")) {  //CUSA Y VF
        
            if (!caramelo_no_bultos.equals("")) { // No. BULTOS
                sql += " AND TIC.NO_BULTOS IN (" + caramelo_no_bultos + ") ";
            }
            if (!caramelo_peso_kg.equals("")) { // Peso (KG)
                sql += " AND TIC.PESO_KG IN (" + caramelo_peso_kg + ") ";
            }
            if (!caramelo_transferencia.equals("")) { // Transferencia 
                sql += " AND TIC.TRANSFERENCIA IN (" + caramelo_transferencia + ") ";
            }
            if (!caramelo_fecha_inicio_etiquetado.equals("")) { // Fecha Inicio Etiquetado
                sql += " AND TO_DATE(trunc(TIC.FECHA_INICIO_ETIQUETADO),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_inicio_etiquetado + ",'MM/DD/YY') ";
            }
            if (!caramelo_fecha_termino_etiquetado.equals("")) { // Fecha Termino Etiquetado 
                sql += " AND TO_DATE(trunc(TIC.FECHA_TERMINO_ETIQUETADO),'DD/MM/YY') = TO_DATE(" + caramelo_fecha_termino_etiquetado + ",'MM/DD/YY') ";
            }
            if (!caramelo_hora_termino_etiquetado.equals("")) { // Hora de termino Etiquetado
                sql += " AND TIC.HORA_TERMINO_ETIQUETADO IN (" + caramelo_hora_termino_etiquetado + ") ";
            }
            if (!caramelo_proveedor.equals("")) { // Proveedor
                sql += " AND TIC.PROVEEDOR IN (" + caramelo_proveedor + ") ";
            }
            if (!caramelo_proveedor_carga.equals("")) { // Proveedor de Carga
                sql += " AND TIC.PROVEEDOR_CARGA IN (" + caramelo_proveedor_carga + ") ";
            }
            
        }    
            if (!caramelo_fy.equals("")) { // FY
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

                    salida += "<tr id=\"tr<" + cont + "\">"
                            + " <th id=\"columna\"><center><img id=\"imgSemaforo" + cont + "\" src=\"" + colorSemaforo + "\" width=\"" + sizeSemaforo + "\"></center></th> "
                            + " <th contenteditable=\"true\" id=\"referenciaAA[" + cont + "]\" oninput=\"validarInput(this);\">" + row[30] + "<pre id=\"rfcOK\" style=\"font-family: Arial; font-weight: bold; color:#4d73d1;\" size=\"1\"></pre></th> "
                            + " <th class=\"font-numero first-column\" id=\"elemento" + cont + "\">" + row[0] + ""
                            + "   <input type=\"hidden\" id=\"evento[" + cont + "]\" name=\"evento[" + cont + "]\" value=\"" + row[0] + "\"> "
                            + "   <div id=\"popup" + cont + "\" style=\"display: none;\"> "
                            + "     <div id=\"mSgError" + cont + "\"></div> "
                            + "   </div> "
                            + " </th> "
                            + " <td id=\"Responsable[" + cont + "]\">" + row[1] + "</td> "
                            + " <td id=\"FinalDestination[" + cont + "]\">" + row[2] + "</td> "
                            + " <td id=\"BrandDivision[" + cont + "]\">" + row[21] + "</td> "
                            + " <td id=\"Division[" + cont + "]\">" + row[4] + "</td> "
                            + " <td id=\"shipmentId[" + cont + "]\">" + row[5] + "</td> "
                            + " <td id=\"containerId[" + cont + "]\">" + row[6] + "</td> "
                            + " <td id=\"blAwbPro[" + cont + "]\">" + row[7] + "</td> "
                            + " <td id=\"loadTypeFinal[" + cont + "]\">" + row[8] + "</td> "
                            + " <td id=\"Quantity[" + cont + "]\">" + row[9] + "</td> "
                            + " <td id=\"Pod[" + cont + "]\">" + row[19] + "</td> "
                            + " <td id=\"EstDepartureFromPol[" + cont + "]\">" + row[11] + "</td> "
                            + " <td id=\"EtaRealPortOfDischarge[" + cont + "]\">" + row[12] + "</td> "
                            + " <td id=\"EstEtaDc[" + cont + "]\">" + row[22] + "</td> "
                            + " <td id=\"InboundNotification[" + cont + "]\">" + row[14] + "</td> "
                            + " <td id=\"Pol[" + cont + "]\">" + row[20] + "</td> "
                            + " <td id=\"aa[" + cont + "]\">" + row[16] + "</td> "
                            + " <td id=\"FechaMesVenta[" + cont + "]\">" + row[28] + "</td> " 
                            + " <td id=\"prioridad[" + cont + "]\">" + row[97] + "</td> "
                            + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'pais_origen'," + cont + ")\" id=\"pais_origen[" + cont + "]\">" + row[31] + "</td> "
                            + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'size_container'," + cont + ")\" id=\"size_container[" + cont + "]\">" + row[32] + "</td> "
                            + " <td contenteditable=\"true\" oninput=\"validarNumero(event)\" onkeydown=\"tabuladorVertical(event,'valor_usd'," + cont + ")\" id=\"valor_usd[" + cont + "]\">" + row[33] + "</td> "
                            + " <td id=\"eta_port_discharge[" + cont + "]\" onclick=\"show_eta_port_discharge('" + row[34] + "'," + cont + ")\">" + row[34] + "</td> "
                            + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'agente_aduanal'," + cont + ")\" id=\"agente_aduanal[" + cont + "]\">" + row[35] + "</td> "
                            + " <td contenteditable=\"true\" onkeypress=\"formatoNumero(event)\" onkeydown=\"tabuladorVertical(event,'pedimento_a1'," + cont + ")\" id=\"pedimento_a1[" + cont + "]\">" + row[36] + "</td> "
                            + " <td contenteditable=\"true\" onkeypress=\"formatoNumero(event)\" onkeydown=\"tabuladorVertical(event,'pedimento_r1_1er'," + cont + ")\" id=\"pedimento_r1_1er[" + cont + "]\" onclick=\"cleanPedimento_r1_1er(''," + cont + ")\">" + row[37] + "</td> "
                            + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'motivo_rectificacion_1er'," + cont + ")\" id=\"motivo_rectificacion_1er[" + cont + "]\">" + row[38] + "</td> "
                            + " <td contenteditable=\"true\" onkeypress=\"formatoNumero(event)\" onkeydown=\"tabuladorVertical(event,'pedimento_r1_2do'," + cont + ")\" id=\"pedimento_r1_2do[" + cont + "]\" onclick=\"cleanPedimento_r1_2do('" + row[39] + "'," + cont + ")\">" + row[39] + "</td> "
                            + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'motivo_rectificacion_2do'," + cont + ")\" id=\"motivo_rectificacion_2do[" + cont + "]\">" + row[40] + "</td> "
                            + " <td id=\"fecha_recepcion_doc[" + cont + "]\" onclick=\"show_fecha_recepcion_doc('" + row[41] + "'," + cont + ")\">" + row[41] + "</td> "
                            + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'recinto'," + cont + ")\" id=\"recinto[" + cont + "]\">" + row[42] + "</td> "
                            + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'naviera'," + cont + ")\" id=\"naviera[" + cont + "]\">" + row[43] + "</td> "
                            + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'buque'," + cont + ")\" id=\"buque[" + cont + "]\">" + row[44] + "</td> "
                            + " <td id=\"fecha_revalidacion[" + cont + "]\" onclick=\"show_fecha_revalidacion('" + row[45] + "'," + cont + ")\">" + row[45] + "</td> "
                            + " <td id=\"fecha_previo_origen[" + cont + "]\" onclick=\"show_fecha_previo_origen('" + row[46] + "'," + cont + ")\">" + row[46] + "</td> "
                            + " <td id=\"fecha_previo_destino[" + cont + "]\" onclick=\"show_fecha_previo_destino('" + row[47] + "'," + cont + ")\">" + row[47] + "</td> "
                            + " <td id=\"fecha_resultado_previo[" + cont + "]\" onclick=\"show_fecha_resultado_previo('" + row[48] + "'," + cont + ")\">" + row[48] + "</td> "
                            + " <td id=\"proforma_final[" + cont + "]\" onclick=\"show_proforma_final('" + row[49] + "'," + cont + ")\">" + row[49] + "</td> "
                            + " <td id=\"permiso[" + cont + "]\" onclick=\"show_permiso(" + cont + ")\">" + row[50] + "</td> "
                            + " <td id=\"fecha_envio[" + cont + "]\" onclick=\"show_fecha_envio('" + row[51] + "'," + cont + ")\">" + row[51] + "</td> "
                            + " <td id=\"fecha_recepcion_perm[" + cont + "]\" onclick=\"show_fecha_recepcion_perm('" + row[52] + "'," + cont + ")\">" + row[52] + "</td> "
                            + " <td id=\"fecha_activacion_perm[" + cont + "]\" onclick=\"show_fecha_activacion_perm('" + row[53] + "'," + cont + ")\">" + row[53] + "</td> "
                            + " <td id=\"fecha_permisos_aut[" + cont + "]\" onclick=\"show_fecha_permisos_aut('" + row[54] + "'," + cont + ")\">" + row[54] + "</td> "
                            + " <td id=\"co_pref_arancelaria[" + cont + "]\" onclick=\"show_co_pref_arancelaria(" + cont + ")\">" + row[55] + "</td> "
                            + " <td id=\"aplic_pref_arancelaria[" + cont + "]\" onclick=\"show_aplic_pref_arancelaria(" + cont + ")\">" + row[56] + "</td> "
                            + " <td id=\"req_uva[" + cont + "]\" onclick=\"show_req_uva(" + cont + ")\">" + row[57] + "</td> "
                            + " <td id=\"req_ca[" + cont + "]\" onclick=\"show_req_ca(" + cont + ")\">" + row[58] + "</td> "
                            + " <td id=\"fecha_recepcion_ca[" + cont + "]\" onclick=\"show_fecha_recepcion_ca('" + row[59] + "'," + cont + ")\">" + row[59] + "</td> "
                            + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'num_constancia_ca'," + cont + ")\" id=\"num_constancia_ca[" + cont + "]\">" + row[60] + "</td> "
                            + " <td contenteditable=\"true\" oninput=\"validarNumero(event)\" onkeydown=\"tabuladorVertical(event,'monto_ca'," + cont + ")\" contenteditable=\"true\" id=\"monto_ca[" + cont + "]\">" + row[61] + "</td> "
                            + " <td id=\"fecha_doc_completos[" + cont + "]\" onclick=\"show_fecha_doc_completos('" + row[63] + "'," + cont + ")\">" + row[62] + "</td> "
                            + " <td id=\"fecha_pago_pedimento[" + cont + "]\" onclick=\"show_fecha_pago_pedimento(" + cont + ")\">" + row[63] + "</td> "
                            + " <td id=\"fecha_solicitud_transporte[" + cont + "]\" onclick=\"show_fecha_solicitud_transporte('" + row[64] + "'," + cont + ")\">" + row[64] + "</td> "
                            + " <td id=\"fecha_modulacion[" + cont + "]\" onclick=\"show_fecha_modulacion(" + cont + ")\">" + row[65] + "</td> "
                            + " <td id=\"modalidad[" + cont + "]\" onclick=\"show_modalidad(" + cont + ")\">" + row[66] + "</td> "
                            + " <td id=\"resultado_modulacion[" + cont + "]\" onclick=\"show_resultado_modulacion(" + cont + "," + AgentType + ")\">" + row[67] + "</td> "
                            + " <td id=\"fecha_reconocimiento[" + cont + "]\" onclick=\"show_fecha_reconocimiento('" + row[68] + "'," + cont + ")\">" + row[68] + "</td> "
                            + " <td id=\"fecha_liberacion[" + cont + "]\" onclick=\"show_fecha_liberacion('" + row[69] + "'," + cont + ")\">" + row[69] + "</td> "
                            + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'sello_origen'," + cont + ")\" id=\"sello_origen[" + cont + "]\">" + row[70] + "</td> "
                            + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'sello_final'," + cont + ")\" id=\"sello_final[" + cont + "]\">" + row[71] + "</td> "
                            + " <td id=\"fecha_retencion_aut[" + cont + "]\" onclick=\"show_fecha_retencion_aut('" + row[72] + "'," + cont + ")\">" + row[72] + "</td> "
                            + " <td id=\"fecha_liberacion_aut[" + cont + "]\" onclick=\"show_fecha_liberacion_aut('" + row[73] + "'," + cont + ")\">" + row[73] + "</td> "
                            + " <td onmouseover=\"formComplet('" + AgentType + "'," + cont + ")\"><select class=\"form-control\" style=\"border: none; outline: none;\" id=\"estatus_operacion[" + cont + "]\" name=\"estatus_operacion[" + cont + "]\" value=\"" + row[74] + "\"> <option value=\"" + row[98] + "\">" + row[74] + "</option>" + listStatusOperationEvent + "</select></td> "
                            + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'motivo_atraso'," + cont + ")\" id=\"motivo_atraso[" + cont + "]\">" + row[75] + "</td> "
                            + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'observaciones'," + cont + ")\" id=\"observaciones[" + cont + "]\">" + row[76] + "</td> ";

                    if (AgentType.equals("4001") || AgentType.equals("4006")) { //LOGIX Y VF

                        salida += " <td id=\"llegada_a_nova[" + cont + "]\" onclick=\"show_llegada_a_nova('" + row[77] + "'," + cont + ")\">" + row[77] + "</td> "
                                + " <td id=\"llegada_a_globe_trade_sd[" + cont + "]\" onclick=\"show_llegada_a_globe_trade_sd('" + row[78] + "'," + cont + ")\">" + row[78] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'archivo_m'," + cont + ")\" id=\"archivo_m[" + cont + "]\">" + row[79] + "</td> "
                                + " <td id=\"fecha_archivo_m[" + cont + "]\" onclick=\"show_fecha_archivo_m('" + row[80] + "'," + cont + ")\">" + row[80] + "</td> "
                                + " <td id=\"fecha_solicit_manip[" + cont + "]\" onclick=\"show_fecha_solicit_manip('" + row[81] + "'," + cont + ")\">" + row[81] + "</td> "
                                + " <td id=\"fecha_vencim_manip[" + cont + "]\" onclick=\"show_fecha_vencim_manip('" + row[82] + "'," + cont + ")\">" + row[82] + "</td> "
                                + " <td id=\"fecha_confirm_clave_pedim[" + cont + "]\" onclick=\"show_fecha_confirm_clave_pedim('" + row[83] + "'," + cont + ")\">" + row[83] + "</td> "
                                + " <td id=\"fecha_recep_increment[" + cont + "]\" onclick=\"show_fecha_recep_increment('" + row[84] + "'," + cont + ")\">" + row[84] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'t_e'," + cont + ")\" id=\"t_e[" + cont + "]\">" + row[85] + "</td> "
                                + " <td id=\"fecha_vencim_inbound[" + cont + "]\" onclick=\"show_fecha_vencim_inbound('" + row[86] + "'," + cont + ")\">" + row[86] + "</td> ";
                    }

                    if (AgentType.equals("4002") || AgentType.equals("4006")) {  //CUSA Y VF

                        salida += " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'no_bultos'," + cont + ")\" id=\"no_bultos[" + cont + "]\">" + row[87] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'peso_kg'," + cont + ")\" id=\"peso_kg[" + cont + "]\">" + row[88] + "</td> "
                                + " <td id=\"transferencia[" + cont + "]\" onclick=\"show_transferencia(" + cont + ")\">" + row[89] + "</td> "
                                + " <td id=\"fecha_inicio_etiquetado[" + cont + "]\" onclick=\"show_fecha_inicio_etiquetado('" + row[90] + "'," + cont + ")\">" + row[90] + "</td> "
                                + " <td id=\"fecha_termino_etiquetado[" + cont + "]\" onclick=\"show_fecha_termino_etiquetado('" + row[91] + "'," + cont + ")\">" + row[91] + "</td> "
                                + " <td><input class=\"form-control\" style=\"border: none; outline: none;\" id=\"hora_termino_etiquetado[" + cont + "]\" name=\"hora_termino_etiquetado[" + cont + "]\" type=\"time\" value=\"" + row[92] + "\" autocomplete=\"off\"></td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'proveedor'," + cont + ")\" id=\"proveedor[" + cont + "]\">" + row[93] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'proveedor_carga'," + cont + ")\" id=\"proveedor_carga[" + cont + "]\">" + row[94] + "</td> ";
                    }

                    salida += " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'fy'," + cont + ")\" id=\"fy[" + cont + "]\">" + row[95] + "</td> "
                            + " <td><a class=\"btn btn-primary text-uppercase\" onclick=\"AddLineCustoms(" + cont + ")\"><i class=\"fa fa-save\"></i></a></td> "
                            + "</tr>";

                    cont++;
                }
            }

            salida += "<input type=\"hidden\" id=\"numCustoms\" name=\"numCustoms\" value=\"" + cont + "\">";

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
