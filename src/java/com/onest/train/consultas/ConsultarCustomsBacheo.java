/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.onest.train.consultas;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import static com.tacts.evidencias.inbound.CrearSemaforoCustoms.contarDiasHabilesTranscurridos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User_Windows10
 */
public class ConsultarCustomsBacheo extends HttpServlet {

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
            
            Date date = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
            String dateDay = formato.format(date);

            String columna_referenciaAA = request.getParameter("columna_referenciaAA").trim();
            String columna_evento = request.getParameter("columna_evento").trim();
            String columna_responsable = request.getParameter("columna_responsable").trim();
            String columna_final_destination = request.getParameter("columna_final_destination").trim();
            String columna_brand_division = request.getParameter("columna_brand_division").trim();
            String columna_division = request.getParameter("columna_division").trim();
            String columna_shipmentId = request.getParameter("columna_shipmentId").trim();
            String columna_containerId = request.getParameter("columna_containerId").trim();
            String columna_blAwbPro = request.getParameter("columna_blAwbPro").trim();
            String columna_loadTypeFinal = request.getParameter("columna_loadTypeFinal").trim();
            String columna_quantity = request.getParameter("columna_quantity").trim();
            String columna_pod = request.getParameter("columna_pod").trim();
            String columna_estDepartFromPol = request.getParameter("columna_estDepartFromPol").trim();
            String columna_etaRealPortOfDischarge = request.getParameter("columna_etaRealPortOfDischarge").trim();
            String columna_estEtaDc = request.getParameter("columna_estEtaDc").trim();
            String columna_inboundNotification = request.getParameter("columna_inboundNotification").trim();
            String columna_pol = request.getParameter("columna_pol").trim();
            String columna_aa = request.getParameter("columna_aa").trim();
            String columna_fechaMesVenta = request.getParameter("columna_fechaMesVenta").trim();
            String columna_prioridad = request.getParameter("columna_prioridad").trim();
            String columna_pais_origen = request.getParameter("columna_pais_origen").trim();
            String columna_size_container = request.getParameter("columna_size_container").trim();
            String columna_valor_usd = request.getParameter("columna_valor_usd").trim();
            String columna_eta_port_discharge = request.getParameter("columna_eta_port_discharge").trim();
            String columna_agente_aduanal = request.getParameter("columna_agente_aduanal").trim();
            String columna_pedimento_a1 = request.getParameter("columna_pedimento_a1").trim();
            String columna_pedimento_r1_1er = request.getParameter("columna_pedimento_r1_1er").trim();
            String columna_motivo_rectificacion_1er = request.getParameter("columna_motivo_rectificacion_1er").trim();
            String columna_pedimento_r1_2do = request.getParameter("columna_pedimento_r1_2do").trim();
            String columna_motivo_rectificacion_2do = request.getParameter("columna_motivo_rectificacion_2do").trim();
            String columna_fecha_recepcion_doc = request.getParameter("columna_fecha_recepcion_doc").trim();
            String columna_recinto = request.getParameter("columna_recinto").trim();
            String columna_naviera = request.getParameter("columna_naviera").trim();
            String columna_buque = request.getParameter("columna_buque").trim();
            String columna_fecha_revalidacion = request.getParameter("columna_fecha_revalidacion").trim();
            String columna_fecha_previo_origen = request.getParameter("columna_fecha_previo_origen").trim();
            String columna_fecha_previo_destino = request.getParameter("columna_fecha_previo_destino").trim();
            String columna_fecha_resultado_previo = request.getParameter("columna_fecha_resultado_previo").trim();
            String columna_proforma_final = request.getParameter("columna_proforma_final").trim();
            String columna_permiso = request.getParameter("columna_permiso").trim();
            String columna_fecha_envio = request.getParameter("columna_fecha_envio").trim();
            String columna_fecha_recepcion_perm = request.getParameter("columna_fecha_recepcion_perm").trim();
            String columna_fecha_activacion_perm = request.getParameter("columna_fecha_activacion_perm").trim();
            String columna_fecha_permisos_aut = request.getParameter("columna_fecha_permisos_aut").trim();
            String columna_co_pref_arancelaria = request.getParameter("columna_co_pref_arancelaria").trim();
            String columna_aplic_pref_arancelaria = request.getParameter("columna_aplic_pref_arancelaria").trim();
            String columna_req_uva = request.getParameter("columna_req_uva").trim();
            String columna_req_ca = request.getParameter("columna_req_ca").trim();
            String columna_fecha_recepcion_ca = request.getParameter("columna_fecha_recepcion_ca").trim();
            String columna_num_constancia_ca = request.getParameter("columna_num_constancia_ca").trim();
            String columna_monto_ca = request.getParameter("columna_monto_ca").trim();
            String columna_fecha_doc_completos = request.getParameter("columna_fecha_doc_completos").trim();
            String columna_fecha_pago_pedimento = request.getParameter("columna_fecha_pago_pedimento").trim();
            String columna_fecha_solicitud_transporte = request.getParameter("columna_fecha_solicitud_transporte").trim();
            String columna_fecha_modulacion = request.getParameter("columna_fecha_modulacion").trim();
            String columna_modalidad = request.getParameter("columna_modalidad").trim();
            String columna_resultado_modulacion = request.getParameter("columna_resultado_modulacion").trim();
            String columna_fecha_reconocimiento = request.getParameter("columna_fecha_reconocimiento").trim();
            String columna_fecha_liberacion = request.getParameter("columna_fecha_liberacion").trim();
            String columna_sello_origen = request.getParameter("columna_sello_origen").trim();
            String columna_sello_final = request.getParameter("columna_sello_final").trim();
            String columna_fecha_retencion_aut = request.getParameter("columna_fecha_retencion_aut").trim();
            String columna_fecha_liberacion_aut = request.getParameter("columna_fecha_liberacion_aut").trim();
            String columna_estatus_operacion = request.getParameter("columna_estatus_operacion").trim();
            String columna_motivo_atraso = request.getParameter("columna_motivo_atraso").trim();
            String columna_observaciones = request.getParameter("columna_observaciones").trim();
            String columna_llegada_a_nova = request.getParameter("columna_llegada_a_nova").trim();
            String columna_llegada_a_globe_trade_sd = request.getParameter("columna_llegada_a_globe_trade_sd").trim();
            String columna_archivo_m = request.getParameter("columna_archivo_m").trim();
            String columna_fecha_archivo_m = request.getParameter("columna_fecha_archivo_m").trim();
            String columna_fecha_solicit_manip = request.getParameter("columna_fecha_solicit_manip").trim();
            String columna_fecha_vencim_manip = request.getParameter("columna_fecha_vencim_manip").trim();
            String columna_fecha_confirm_clave_pedim = request.getParameter("columna_fecha_confirm_clave_pedim").trim();
            String columna_fecha_recep_increment = request.getParameter("columna_fecha_recep_increment").trim();
            String columna_t_e = request.getParameter("columna_t_e").trim();
            String columna_fecha_vencim_inbound = request.getParameter("columna_fecha_vencim_inbound").trim();
            String columna_no_bultos = request.getParameter("columna_no_bultos").trim();
            String columna_peso_kg = request.getParameter("columna_peso_kg").trim();
            String columna_transferencia = request.getParameter("columna_transferencia").trim();
            String columna_fecha_inicio_etiquetado = request.getParameter("columna_fecha_inicio_etiquetado").trim();
            String columna_fecha_termino_etiquetado = request.getParameter("columna_fecha_termino_etiquetado").trim();
            String columna_hora_termino_etiquetado = request.getParameter("columna_hora_termino_etiquetado").trim();
            String columna_proveedor = request.getParameter("columna_proveedor").trim();
            String columna_proveedor_carga = request.getParameter("columna_proveedor_carga").trim();
            String columna_fy = request.getParameter("columna_fy").trim();
            int contSubfiltros = Integer.parseInt(request.getParameter("contSubfiltros"))-1;
            int offset = Integer.parseInt(request.getParameter("offset"));
            int next = Integer.parseInt(request.getParameter("next"));

            System.out.println("Contador SubFiltros:" + contSubfiltros);   
            
            //Lista de valores - filtros consulta sql
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
            String fecha_importacion_registrada = "";
            String sql = "";
            int diasHabiles = 0;
            boolean excluirShipment = false;
            String AgentType = "";
            
            //Parametros Generales
            String colorSemaforo = "";
            String sizeSemaforo = "";
            String listStatusOperationEvent = "";
            String salida = "";
            int cont = 1;
           
            //Consultar Información de Agente Aduanal
            if (db.doDB(fac.consultarAgenteAduanalCustoms(UserId))) {
                for (String[] rowA : db.getResultado()) {
                    AgentType = rowA[0];
                }
            }
            
            /*Columna: Estatus Operación (listado)*/
            if (db.doDB(fac.consultarEstatusOperacionCustoms())) {
                for (String[] rowO : db.getResultado()) {
                    listStatusOperationEvent += "<option value=\"" + rowO[0] + "\">" + rowO[1] + "</option>";
                }
            }
           
            
    /*  ----------------------------- FILTROS MULTISELECT ENCABEZADOS  -----------------------------  */  
    
            if (!columna_referenciaAA.equals("")) { 
                String[] selected = columna_referenciaAA.split("@");
                for (String a : selected) {
                    caramelo_referenciaAA += "'" + a + "',";
                }
            }
            if (!columna_evento.equals("")) {
                String[] selected = columna_evento.split("@");
                for (String a : selected) {
                    caramelo_evento += a + ",";
                }
            }
            if (!columna_responsable.equals("")) {
                String[] selected = columna_responsable.split("@");
                for (String a : selected) {
                    caramelo_responsable += "'" + a + "',";
                }
            }
            if (!columna_final_destination.equals("")) {
                String[] selected = columna_final_destination.split("@");
                for (String a : selected) {
                    caramelo_final_destination += "'" + a + "',";
                }
            }
            if (!columna_brand_division.equals("")) {
                String[] selected = columna_brand_division.split("@");
                for (String a : selected) {
                    caramelo_brand_division += "'" + a + "',";
                }
            }
            if (!columna_division.equals("")) {
                String[] selected = columna_division.split("@");
                for (String a : selected) {
                    caramelo_division += "'" + a + "',";
                }
            }
            if (!columna_shipmentId.equals("")) {
                String[] selected = columna_shipmentId.split("@");
                for (String a : selected) {
                    caramelo_shipmentId += "'" + a + "',";
                }
            }
            if (!columna_containerId.equals("")) {
                String[] selected = columna_containerId.split("@");
                for (String a : selected) {
                    caramelo_containerId += "'" + a + "',";
                }
            }
            if (!columna_blAwbPro.equals("")) {
                String[] selected = columna_blAwbPro.split("@");
                for (String a : selected) {
                    caramelo_blAwbPro += "'" + a + "',";
                }
            }
            if (!columna_loadTypeFinal.equals("")) {
                String[] selected = columna_loadTypeFinal.split("@");
                for (String a : selected) {
                    caramelo_loadTypeFinal += "'" + a + "',";
                }
            }
            if (!columna_quantity.equals("")) {
                String[] selected = columna_quantity.split("@");
                for (String a : selected) {
                    caramelo_quantity += a + ",";
                }
            }
            if (!columna_pod.equals("")) {
                String[] selected = columna_pod.split("@");
                for (String a : selected) {
                    caramelo_pod += "'" + a + "',";
                }
            }
            if (!columna_estDepartFromPol.equals("")) {
                String[] selected = columna_estDepartFromPol.split("@");
                for (String a : selected) {
                    caramelo_estDepartFromPol += "'" + a + "',";
                }
            }
            if (!columna_etaRealPortOfDischarge.equals("")) {
                String[] selected = columna_etaRealPortOfDischarge.split("@");
                for (String a : selected) {
                    caramelo_etaRealPortOfDischarge += "'" + a + "',";
                }
            }
            if (!columna_estEtaDc.equals("")) {
                String[] selected = columna_estEtaDc.split("@");
                for (String a : selected) {
                    caramelo_estEtaDc += "'" + a + "',";
                }
            }
            if (!columna_inboundNotification.equals("")) {
                String[] selected = columna_inboundNotification.split("@");
                for (String a : selected) {
                    caramelo_inboundNotification += "'" + a + "',";
                }
            }
            if (!columna_pol.equals("")) {
                String[] selected = columna_pol.split("@");
                for (String a : selected) {
                    caramelo_pol += "'" + a + "',";
                }
            }
            if (!columna_aa.equals("")) {
                String[] selected = columna_aa.split("@");
                for (String a : selected) {
                    caramelo_aa += "'" + a + "',";
                }
            }
            if (!columna_fechaMesVenta.equals("")) {
                String[] selected = columna_fechaMesVenta.split("@");
                for (String a : selected) {
                    caramelo_fechaMesVenta += "'" + a + "',";
                }
            }
            if (!columna_prioridad.equals("")) {
                String[] selected = columna_prioridad.split("@");
                for (String a : selected) {
                    caramelo_prioridad += "'" + a + "',";
                }
            }
            if (!columna_pais_origen.equals("")) {
                String[] selected = columna_pais_origen.split("@");
                for (String a : selected) {
                    caramelo_pais_origen += "'" + a + "',";
                }
            }
            if (!columna_size_container.equals("")) {
                String[] selected = columna_size_container.split("@");
                for (String a : selected) {
                    caramelo_size_container += "'" + a + "',";
                }
            }
            if (!columna_valor_usd.equals("")) {
                String[] selected = columna_valor_usd.split("@");
                for (String a : selected) {
                    caramelo_valor_usd += "'" + a + "',";
                }
            }
            if (!columna_eta_port_discharge.equals("")) {
                String[] selected = columna_eta_port_discharge.split("@");
                for (String a : selected) {
                    caramelo_eta_port_discharge += "'" + a + "',";
                }
            }
            if (!columna_agente_aduanal.equals("")) {
                String[] selected = columna_agente_aduanal.split("@");
                for (String a : selected) {
                    caramelo_agente_aduanal += "'" + a + "',";
                }
            }
            if (!columna_pedimento_a1.equals("")) {
                String[] selected = columna_pedimento_a1.split("@");
                for (String a : selected) {
                    caramelo_pedimento_a1 += "'" + a + "',";
                }
            }
            if (!columna_pedimento_r1_1er.equals("")) {
                String[] selected = columna_pedimento_r1_1er.split("@");
                for (String a : selected) {
                    caramelo_pedimento_r1_1er += "'" + a + "',";
                }
            }
            if (!columna_motivo_rectificacion_1er.equals("")) {
                String[] selected = columna_motivo_rectificacion_1er.split("@");
                for (String a : selected) {
                    caramelo_motivo_rectificacion_1er += "'" + a + "',";
                }
            }
            if (!columna_pedimento_r1_2do.equals("")) {
                String[] selected = columna_pedimento_r1_2do.split("@");
                for (String a : selected) {
                    caramelo_pedimento_r1_2do += "'" + a + "',";
                }
            }
            if (!columna_motivo_rectificacion_2do.equals("")) {
                String[] selected = columna_motivo_rectificacion_2do.split("@");
                for (String a : selected) {
                    caramelo_motivo_rectificacion_2do += "'" + a + "',";
                }
            }
            if (!columna_fecha_recepcion_doc.equals("")) {
                String[] selected = columna_fecha_recepcion_doc.split("@");
                for (String a : selected) {
                    caramelo_fecha_recepcion_doc += "'" + a + "',";
                }
            }
            if (!columna_recinto.equals("")) {
                String[] selected = columna_recinto.split("@");
                for (String a : selected) {
                    caramelo_recinto += "'" + a + "',";
                }
            }
            if (!columna_naviera.equals("")) {
                String[] selected = columna_naviera.split("@");
                for (String a : selected) {
                    caramelo_naviera += "'" + a + "',";
                }
            }
            if (!columna_buque.equals("")) {
                String[] selected = columna_buque.split("@");
                for (String a : selected) {
                    caramelo_buque += "'" + a + "',";
                }
            }
            if (!columna_fecha_revalidacion.equals("")) {
                String[] selected = columna_fecha_revalidacion.split("@");
                for (String a : selected) {
                    caramelo_fecha_revalidacion += "'" + a + "',";
                }
            }
            if (!columna_fecha_previo_origen.equals("")) {
                String[] selected = columna_fecha_previo_origen.split("@");
                for (String a : selected) {
                    caramelo_fecha_previo_origen += "'" + a + "',";
                }
            }
            if (!columna_fecha_previo_destino.equals("")) {
                String[] selected = columna_fecha_previo_destino.split("@");
                for (String a : selected) {
                    caramelo_fecha_previo_destino += "'" + a + "',";
                }
            }
            if (!columna_fecha_resultado_previo.equals("")) {
                String[] selected = columna_fecha_resultado_previo.split("@");
                for (String a : selected) {
                    caramelo_fecha_resultado_previo += "'" + a + "',";
                }
            }
            if (!columna_proforma_final.equals("")) {
                String[] selected = columna_proforma_final.split("@");
                for (String a : selected) {
                    caramelo_proforma_final += "'" + a + "',";
                }
            }
            if (!columna_permiso.equals("")) {
                String[] selected = columna_permiso.split("@");
                for (String a : selected) {
                    caramelo_permiso += "'" + a + "',";
                }
            }
            if (!columna_fecha_envio.equals("")) {
                String[] selected = columna_fecha_envio.split("@");
                for (String a : selected) {
                    caramelo_fecha_envio += "'" + a + "',";
                }
            }
            if (!columna_fecha_recepcion_perm.equals("")) {
                String[] selected = columna_fecha_recepcion_perm.split("@");
                for (String a : selected) {
                    caramelo_fecha_recepcion_perm += "'" + a + "',";
                }
            }
            if (!columna_fecha_activacion_perm.equals("")) {
                String[] selected = columna_fecha_activacion_perm.split("@");
                for (String a : selected) {
                    caramelo_fecha_activacion_perm += "'" + a + "',";
                }
            }
            if (!columna_fecha_permisos_aut.equals("")) {
                String[] selected = columna_fecha_permisos_aut.split("@");
                for (String a : selected) {
                    caramelo_fecha_permisos_aut += "'" + a + "',";
                }
            }
            if (!columna_co_pref_arancelaria.equals("")) {
                String[] selected = columna_co_pref_arancelaria.split("@");
                for (String a : selected) {
                    caramelo_co_pref_arancelaria += "'" + a + "',";
                }
            }
            if (!columna_aplic_pref_arancelaria.equals("")) {
                String[] selected = columna_aplic_pref_arancelaria.split("@");
                for (String a : selected) {
                    caramelo_aplic_pref_arancelaria += "'" + a + "',";
                }
            }
            if (!columna_req_uva.equals("")) {
                String[] selected = columna_req_uva.split("@");
                for (String a : selected) {
                    caramelo_req_uva += "'" + a + "',";
                }
            }
            if (!columna_req_ca.equals("")) {
                String[] selected = columna_req_ca.split("@");
                for (String a : selected) {
                    caramelo_req_ca += "'" + a + "',";
                }
            }
            if (!columna_fecha_recepcion_ca.equals("")) {
                String[] selected = columna_fecha_recepcion_ca.split("@");
                for (String a : selected) {
                    caramelo_fecha_recepcion_ca += "'" + a + "',";
                }
            }
            if (!columna_num_constancia_ca.equals("")) {
                String[] selected = columna_num_constancia_ca.split("@");
                for (String a : selected) {
                    caramelo_num_constancia_ca += a + ",";
                }
            }
            if (!columna_monto_ca.equals("")) {
                String[] selected = columna_monto_ca.split("@");
                for (String a : selected) {
                    caramelo_monto_ca += a + ",";
                }
            }
            if (!columna_fecha_doc_completos.equals("")) {
                String[] selected = columna_fecha_doc_completos.split("@");
                for (String a : selected) {
                    caramelo_fecha_doc_completos += "'" + a + "',";
                }
            }
            if (!columna_fecha_pago_pedimento.equals("")) {
                String[] selected = columna_fecha_pago_pedimento.split("@");
                for (String a : selected) {
                    caramelo_fecha_pago_pedimento += "'" + a + "',";
                }
            }
            if (!columna_fecha_solicitud_transporte.equals("")) {
                String[] selected = columna_fecha_solicitud_transporte.split("@");
                for (String a : selected) {
                    caramelo_fecha_solicitud_transporte += "'" + a + "',";
                }
            }
            if (!columna_fecha_modulacion.equals("")) {
                String[] selected = columna_fecha_modulacion.split("@");
                for (String a : selected) {
                    caramelo_fecha_modulacion += "'" + a + "',";
                }
            }
            if (!columna_modalidad.equals("")) {
                String[] selected = columna_modalidad.split("@");
                for (String a : selected) {
                    caramelo_modalidad += "'" + a + "',";
                }
            }
            if (!columna_resultado_modulacion.equals("")) {
                String[] selected = columna_resultado_modulacion.split("@");
                for (String a : selected) {
                    caramelo_resultado_modulacion += "'" + a + "',";
                }
            }
            if (!columna_fecha_reconocimiento.equals("")) {
                String[] selected = columna_fecha_reconocimiento.split("@");
                for (String a : selected) {
                    caramelo_fecha_reconocimiento += "'" + a + "',";
                }
            }
            if (!columna_fecha_liberacion.equals("")) {
                String[] selected = columna_fecha_liberacion.split("@");
                for (String a : selected) {
                    caramelo_fecha_liberacion += "'" + a + "',";
                }
            }
            if (!columna_sello_origen.equals("")) {
                String[] selected = columna_sello_origen.split("@");
                for (String a : selected) {
                    caramelo_sello_origen += "'" + a + "',";
                }
            }
            if (!columna_sello_final.equals("")) {
                String[] selected = columna_sello_final.split("@");
                for (String a : selected) {
                    caramelo_sello_final += "'" + a + "',";
                }
            }
            if (!columna_fecha_retencion_aut.equals("")) {
                String[] selected = columna_fecha_retencion_aut.split("@");
                for (String a : selected) {
                    caramelo_fecha_retencion_aut += "'" + a + "',";
                }
            }
            if (!columna_fecha_liberacion_aut.equals("")) {
                String[] selected = columna_fecha_liberacion_aut.split("@");
                for (String a : selected) {
                    caramelo_fecha_liberacion_aut += "'" + a + "',";
                }
            }
            if (!columna_estatus_operacion.equals("")) {
                String[] selected = columna_estatus_operacion.split("@");
                for (String a : selected) {
                    caramelo_estatus_operacion += "'" + a + "',";
                }
            }
            if (!columna_motivo_atraso.equals("")) {
                String[] selected = columna_motivo_atraso.split("@");
                for (String a : selected) {
                    caramelo_motivo_atraso += "'" + a + "',";
                }
            }
            if (!columna_observaciones.equals("")) {
                String[] selected = columna_observaciones.split("@");
                for (String a : selected) {
                    caramelo_observaciones += "'" + a + "',";
                }
            }
            
        if (AgentType.equals("4001") || AgentType.equals("4006")) { //LOGIX Y VF    
            
            if (!columna_llegada_a_nova.equals("")) {
                String[] selected = columna_llegada_a_nova.split("@");
                for (String a : selected) {
                    caramelo_llegada_a_nova += "'" + a + "',";
                }
            }
            if (!columna_llegada_a_globe_trade_sd.equals("")) {
                String[] selected = columna_llegada_a_globe_trade_sd.split("@");
                for (String a : selected) {
                    caramelo_llegada_a_globe_trade_sd += "'" + a + "',";
                }
            }
            if (!columna_archivo_m.equals("")) {
                String[] selected = columna_archivo_m.split("@");
                for (String a : selected) {
                    caramelo_archivo_m += "'" + a + "',";
                }
            }
            if (!columna_fecha_archivo_m.equals("")) {
                String[] selected = columna_fecha_archivo_m.split("@");
                for (String a : selected) {
                    caramelo_fecha_archivo_m += "'" + a + "',";
                }
            }
            if (!columna_fecha_solicit_manip.equals("")) {
                String[] selected = columna_fecha_solicit_manip.split("@");
                for (String a : selected) {
                    caramelo_fecha_solicit_manip += "'" + a + "',";
                }
            }
            if (!columna_fecha_vencim_manip.equals("")) {
                String[] selected = columna_fecha_vencim_manip.split("@");
                for (String a : selected) {
                    caramelo_fecha_vencim_manip += "'" + a + "',";
                }
            }
            if (!columna_fecha_confirm_clave_pedim.equals("")) {
                String[] selected = columna_fecha_confirm_clave_pedim.split("@");
                for (String a : selected) {
                    caramelo_fecha_vencim_manip += "'" + a + "',";
                }
            }
            if (!columna_fecha_recep_increment.equals("")) {
                String[] selected = columna_fecha_recep_increment.split("@");
                for (String a : selected) {
                    caramelo_fecha_recep_increment += "'" + a + "',";
                }
            }
            if (!columna_t_e.equals("")) {
                String[] selected = columna_t_e.split("@");
                for (String a : selected) {
                    caramelo_t_e += "'" + a + "',";
                }
            }
            if (!columna_fecha_vencim_inbound.equals("")) {
                String[] selected = columna_fecha_vencim_inbound.split("@");
                for (String a : selected) {
                    caramelo_fecha_vencim_inbound += "'" + a + "',";
                }
            }
            
        }    
            
        if (AgentType.equals("4002") || AgentType.equals("4006")) {  //CUSA Y VF

            if (!columna_no_bultos.equals("")) {
                String[] selected = columna_no_bultos.split("@");
                for (String a : selected) {
                    caramelo_no_bultos += "'" + a + "',";
                }
            }
            if (!columna_peso_kg.equals("")) {
                String[] selected = columna_peso_kg.split("@");
                for (String a : selected) {
                    caramelo_peso_kg += "'" + a + "',";
                }
            }
            if (!columna_transferencia.equals("")) {
                String[] selected = columna_transferencia.split("@");
                for (String a : selected) {
                    caramelo_transferencia += "'" + a + "',";
                }
            }
            if (!columna_fecha_inicio_etiquetado.equals("")) {
                String[] selected = columna_fecha_inicio_etiquetado.split("@");
                for (String a : selected) {
                    caramelo_fecha_inicio_etiquetado += "'" + a + "',";
                }
            }
            if (!columna_fecha_termino_etiquetado.equals("")) {
                String[] selected = columna_fecha_termino_etiquetado.split("@");
                for (String a : selected) {
                    caramelo_fecha_termino_etiquetado += "'" + a + "',";
                }
            }
            if (!columna_hora_termino_etiquetado.equals("")) {
                String[] selected = columna_hora_termino_etiquetado.split("@");
                for (String a : selected) {
                    caramelo_hora_termino_etiquetado += "'" + a + "',";
                }
            }
            if (!columna_proveedor.equals("")) {
                String[] selected = columna_proveedor.split("@");
                for (String a : selected) {
                    caramelo_proveedor += "'" + a + "',";
                }
            }
            if (!columna_proveedor_carga.equals("")) {
                String[] selected = columna_proveedor_carga.split("@");
                for (String a : selected) {
                    caramelo_proveedor_carga += "'" + a + "',";
                }
            }
            
        }    
            if (!columna_fy.equals("")) {
                String[] selected = columna_fy.split("@");
                for (String a : selected) {
                    caramelo_fy += "'" + a + "',";
                }
            }

    
            if (!columna_referenciaAA.equals("")) {
                caramelo_referenciaAA = caramelo_referenciaAA.replaceAll(",$", "");
            }
            if (!columna_evento.equals("")) {
                caramelo_evento = caramelo_evento.replaceAll(",$", "");
            }
            if (!columna_responsable.equals("")) {
                caramelo_responsable = caramelo_responsable.replaceAll(",$", "");
            }
            if (!columna_final_destination.equals("")) {
                caramelo_final_destination = caramelo_final_destination.replaceAll(",$", "");
            }
            if (!columna_brand_division.equals("")) {
                caramelo_brand_division = caramelo_brand_division.replaceAll(",$", "");
            }
            if (!columna_division.equals("")) {
                caramelo_division = caramelo_division.replaceAll(",$", "");
            }
            if (!columna_shipmentId.equals("")) {
                caramelo_shipmentId = caramelo_shipmentId.replaceAll(",$", "");
            }
            if (!columna_containerId.equals("")) {
                caramelo_containerId = caramelo_containerId.replaceAll(",$", "");
            }
            if (!columna_blAwbPro.equals("")) {
                caramelo_blAwbPro = caramelo_blAwbPro.replaceAll(",$", "");
            }
            if (!columna_loadTypeFinal.equals("")) {
                caramelo_loadTypeFinal = caramelo_loadTypeFinal.replaceAll(",$", "");
            }
            if (!columna_quantity.equals("")) {
                caramelo_quantity = caramelo_quantity.replaceAll(",$", "");
            }
            if (!columna_pod.equals("")) {
                caramelo_pod = caramelo_pod.replaceAll(",$", "");
            }
            if (!columna_estDepartFromPol.equals("")) {
                caramelo_estDepartFromPol = caramelo_estDepartFromPol.replaceAll(",$", "");
            }
            if (!columna_etaRealPortOfDischarge.equals("")) {
                caramelo_etaRealPortOfDischarge = caramelo_etaRealPortOfDischarge.replaceAll(",$", "");
            }
            if (!columna_estEtaDc.equals("")) {
                caramelo_estEtaDc = caramelo_estEtaDc.replaceAll(",$", "");
            }
            if (!columna_inboundNotification.equals("")) {
                caramelo_inboundNotification = caramelo_inboundNotification.replaceAll(",$", "");
            }
            if (!columna_pol.equals("")) {
                caramelo_pol = caramelo_pol.replaceAll(",$", "");
            }
            if (!columna_aa.equals("")) {
                caramelo_aa = caramelo_aa.replaceAll(",$", "");
            }
            if (!columna_fechaMesVenta.equals("")) {
                caramelo_fechaMesVenta = caramelo_fechaMesVenta.replaceAll(",$", "");
            }
            if (!columna_prioridad.equals("")) {
                caramelo_prioridad = caramelo_prioridad.replaceAll(",$", "");
            }
            if (!columna_pais_origen.equals("")) {
                caramelo_pais_origen = caramelo_pais_origen.replaceAll(",$", "");
            }
            if (!columna_size_container.equals("")) {
                caramelo_size_container = caramelo_size_container.replaceAll(",$", "");
            }
            if (!columna_valor_usd.equals("")) {
                caramelo_valor_usd = caramelo_valor_usd.replaceAll(",$", "");
            }
            if (!columna_eta_port_discharge.equals("")) {
                caramelo_eta_port_discharge = caramelo_eta_port_discharge.replaceAll(",$", "");
            }
            if (!columna_agente_aduanal.equals("")) {
                caramelo_agente_aduanal = caramelo_agente_aduanal.replaceAll(",$", "");
            }
            if (!columna_pedimento_a1.equals("")) {
                caramelo_pedimento_a1 = caramelo_pedimento_a1.replaceAll(",$", "");
            }
            if (!columna_pedimento_r1_1er.equals("")) {
                caramelo_pedimento_r1_1er = caramelo_pedimento_r1_1er.replaceAll(",$", "");
            }
            if (!columna_motivo_rectificacion_1er.equals("")) {
                caramelo_motivo_rectificacion_1er = caramelo_motivo_rectificacion_1er.replaceAll(",$", "");
            }
            if (!columna_pedimento_r1_2do.equals("")) {
                caramelo_pedimento_r1_2do = caramelo_pedimento_r1_2do.replaceAll(",$", "");
            }
            if (!columna_motivo_rectificacion_2do.equals("")) {
                caramelo_motivo_rectificacion_2do = caramelo_motivo_rectificacion_2do.replaceAll(",$", "");
            }
            if (!columna_fecha_recepcion_doc.equals("")) {
                caramelo_fecha_recepcion_doc = caramelo_fecha_recepcion_doc.replaceAll(",$", "");
            }
            if (!columna_recinto.equals("")) {
                caramelo_recinto = caramelo_recinto.replaceAll(",$", "");
            }
            if (!columna_naviera.equals("")) {
                caramelo_naviera = caramelo_naviera.replaceAll(",$", "");
            }
            if (!columna_buque.equals("")) {
                caramelo_buque = caramelo_buque.replaceAll(",$", "");
            }
            if (!columna_fecha_revalidacion.equals("")) {
                caramelo_fecha_revalidacion = caramelo_fecha_revalidacion.replaceAll(",$", "");
            }
            if (!columna_fecha_previo_origen.equals("")) {
                caramelo_fecha_previo_origen = caramelo_fecha_previo_origen.replaceAll(",$", "");
            }
            if (!columna_fecha_previo_destino.equals("")) {
                caramelo_fecha_previo_destino = caramelo_fecha_previo_destino.replaceAll(",$", "");
            }
            if (!columna_fecha_resultado_previo.equals("")) {
                caramelo_fecha_resultado_previo = caramelo_fecha_resultado_previo.replaceAll(",$", "");
            }
            if (!columna_proforma_final.equals("")) {
                caramelo_proforma_final = caramelo_proforma_final.replaceAll(",$", "");
            }
            if (!columna_permiso.equals("")) {
                caramelo_permiso = caramelo_permiso.replaceAll(",$", "");
            }
            if (!columna_fecha_envio.equals("")) {
                caramelo_fecha_envio = caramelo_fecha_envio.replaceAll(",$", "");
            }
            if (!columna_fecha_recepcion_perm.equals("")) {
                caramelo_fecha_recepcion_perm = caramelo_fecha_recepcion_perm.replaceAll(",$", "");
            }
            if (!columna_fecha_activacion_perm.equals("")) {
                caramelo_fecha_activacion_perm = caramelo_fecha_activacion_perm.replaceAll(",$", "");
            }
            if (!columna_fecha_permisos_aut.equals("")) {
                caramelo_fecha_permisos_aut = caramelo_fecha_permisos_aut.replaceAll(",$", "");
            }
            if (!columna_co_pref_arancelaria.equals("")) {
                caramelo_co_pref_arancelaria = caramelo_co_pref_arancelaria.replaceAll(",$", "");
            }
            if (!columna_aplic_pref_arancelaria.equals("")) {
                caramelo_aplic_pref_arancelaria = caramelo_aplic_pref_arancelaria.replaceAll(",$", "");
            }
            if (!columna_req_uva.equals("")) {
                caramelo_req_uva = caramelo_req_uva.replaceAll(",$", "");
            }
            if (!columna_req_ca.equals("")) {
                caramelo_req_ca = caramelo_req_ca.replaceAll(",$", "");
            }
            if (!columna_fecha_recepcion_ca.equals("")) {
                caramelo_fecha_recepcion_ca = caramelo_fecha_recepcion_ca.replaceAll(",$", "");
            }
            if (!columna_num_constancia_ca.equals("")) {
                caramelo_num_constancia_ca = caramelo_num_constancia_ca.replaceAll(",$", "");
            }
            if (!columna_monto_ca.equals("")) {
                caramelo_monto_ca = caramelo_monto_ca.replaceAll(",$", "");
            }
            if (!columna_fecha_doc_completos.equals("")) {
                caramelo_fecha_doc_completos = caramelo_fecha_doc_completos.replaceAll(",$", "");
            }
            if (!columna_fecha_pago_pedimento.equals("")) {
                caramelo_fecha_pago_pedimento = caramelo_fecha_pago_pedimento.replaceAll(",$", "");
            }
            if (!columna_fecha_solicitud_transporte.equals("")) {
                caramelo_fecha_solicitud_transporte = caramelo_fecha_solicitud_transporte.replaceAll(",$", "");
            }
            if (!columna_fecha_modulacion.equals("")) {
                caramelo_fecha_modulacion = caramelo_fecha_modulacion.replaceAll(",$", "");
            }
            if (!columna_modalidad.equals("")) {
                caramelo_modalidad = caramelo_modalidad.replaceAll(",$", "");
            }
            if (!columna_resultado_modulacion.equals("")) {
                caramelo_resultado_modulacion = caramelo_resultado_modulacion.replaceAll(",$", "");
            }
            if (!columna_fecha_reconocimiento.equals("")) {
                caramelo_fecha_reconocimiento = caramelo_fecha_reconocimiento.replaceAll(",$", "");
            }
            if (!columna_fecha_liberacion.equals("")) {
                caramelo_fecha_liberacion = caramelo_fecha_liberacion.replaceAll(",$", "");
            }
            if (!columna_sello_origen.equals("")) {
                caramelo_sello_origen = caramelo_sello_origen.replaceAll(",$", "");
            }
            if (!columna_sello_final.equals("")) {
                caramelo_sello_final = caramelo_sello_final.replaceAll(",$", "");
            }
            if (!columna_fecha_retencion_aut.equals("")) {
                caramelo_fecha_retencion_aut = caramelo_fecha_retencion_aut.replaceAll(",$", "");
            }
            if (!columna_fecha_liberacion_aut.equals("")) {
                caramelo_fecha_liberacion_aut = caramelo_fecha_liberacion_aut.replaceAll(",$", "");
            }
            if (!columna_estatus_operacion.equals("")) {
                caramelo_estatus_operacion = caramelo_estatus_operacion.replaceAll(",$", "");
            }
            if (!columna_motivo_atraso.equals("")) {
                caramelo_motivo_atraso = caramelo_motivo_atraso.replaceAll(",$", "");
            }
            if (!columna_observaciones.equals("")) {
                caramelo_observaciones = caramelo_observaciones.replaceAll(",$", "");
            }
            
        if (AgentType.equals("4001") || AgentType.equals("4006")) { //LOGIX Y VF
            
            if (!columna_llegada_a_nova.equals("")) {
                caramelo_llegada_a_nova = caramelo_llegada_a_nova.replaceAll(",$", "");
            }
            if (!columna_llegada_a_globe_trade_sd.equals("")) {
                caramelo_llegada_a_globe_trade_sd = caramelo_llegada_a_globe_trade_sd.replaceAll(",$", "");
            }
            if (!columna_archivo_m.equals("")) {
                caramelo_archivo_m = caramelo_archivo_m.replaceAll(",$", "");
            }
            if (!columna_fecha_archivo_m.equals("")) {
                caramelo_fecha_archivo_m = caramelo_fecha_archivo_m.replaceAll(",$", "");
            }
            if (!columna_fecha_solicit_manip.equals("")) {
                caramelo_fecha_solicit_manip = caramelo_fecha_solicit_manip.replaceAll(",$", "");
            }
            if (!columna_fecha_vencim_manip.equals("")) {
                caramelo_fecha_vencim_manip = caramelo_fecha_vencim_manip.replaceAll(",$", "");
            }
            if (!columna_fecha_confirm_clave_pedim.equals("")) {
                caramelo_fecha_confirm_clave_pedim = caramelo_fecha_confirm_clave_pedim.replaceAll(",$", "");
            }
            if (!columna_fecha_recep_increment.equals("")) {
                caramelo_fecha_recep_increment = caramelo_fecha_recep_increment.replaceAll(",$", "");
            }
            if (!columna_t_e.equals("")) {
                caramelo_t_e = caramelo_t_e.replaceAll(",$", "");
            }
            if (!columna_fecha_vencim_inbound.equals("")) {
                caramelo_fecha_vencim_inbound = caramelo_fecha_vencim_inbound.replaceAll(",$", "");
            }
            if (!columna_no_bultos.equals("")) {
                caramelo_no_bultos = caramelo_no_bultos.replaceAll(",$", "");
            }
            
        }
        
        if (AgentType.equals("4002") || AgentType.equals("4006")) {  //CUSA Y VF
            
            if (!columna_peso_kg.equals("")) {
                caramelo_peso_kg = caramelo_peso_kg.replaceAll(",$", "");
            }
            if (!columna_transferencia.equals("")) {
                caramelo_transferencia = caramelo_transferencia.replaceAll(",$", "");
            }
            if (!columna_fecha_inicio_etiquetado.equals("")) {
                caramelo_fecha_inicio_etiquetado = caramelo_fecha_inicio_etiquetado.replaceAll(",$", "");
            }
            if (!columna_fecha_termino_etiquetado.equals("")) {
                caramelo_fecha_termino_etiquetado = caramelo_fecha_termino_etiquetado.replaceAll(",$", "");
            }
            if (!columna_hora_termino_etiquetado.equals("")) {
                caramelo_hora_termino_etiquetado = caramelo_hora_termino_etiquetado.replaceAll(",$", "");
            }
            if (!columna_proveedor.equals("")) {
                caramelo_proveedor = caramelo_proveedor.replaceAll(",$", "");
            }
            if (!columna_proveedor_carga.equals("")) {
                caramelo_proveedor_carga = caramelo_proveedor_carga.replaceAll(",$", "");
            }
            
        }
        
            if (!columna_fy.equals("")) {
                caramelo_fy = caramelo_fy.replaceAll(",$", "");
            }

        /*  ----------------------------- DATA/CONSULTA DE TABLA  -----------------------------  */    
            
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
        /*12*/ + " CASE WHEN nvl(to_char(GTN.EST_DEPARTURE_POL, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(GTN.EST_DEPARTURE_POL, 'Mon/DD/YYYY'),  ' ') END, "
        /*13*/ + " CASE WHEN nvl(to_char(GTN.ETA_PORT_DISCHARGE, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(GTN.ETA_PORT_DISCHARGE, 'Mon/DD/YYYY'),  ' ') END, "
        /*14*/ + " NVL(GTN.MAX_FLETE, 0) AS EST_ETA_DC, "
        /*15*/ + " CASE WHEN nvl(to_char(GTN.FECHA_CAPTURA, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(GTN.FECHA_CAPTURA, 'Mon/DD/YYYY'),  ' ') END, "
        /*16*/ + " TIP2.NOMBRE_POL, "
        /*17*/ + " NVL(TAA.AGENTE_ADUANAL_NOMBRE, ' ') AS AGENTE_ADUANAL, "
        /*18*/ + " GTN.PLANTILLA_ID, "
        /*19*/ + " SYSDATE AS FECHA_CAPTURAOLD, "
        /*20*/ + " TIP1.NOMBRE_POD, "
        /*21*/ + " TIP2.NOMBRE_POL, "
        /*22*/ + " TIBD.NOMBRE_BD, "
        /*23*/ + " CASE WHEN nvl(to_char(GTN.ETA_PLUS2, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(tic.eta_port_of_discharge, 'Mon/DD/YYYY'),  ' ') END, "
        /*24*/ + " CASE WHEN nvl(to_char(TIE.EST_ETA_DC, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(tic.eta_port_of_discharge, 'Mon/DD/YYYY'),  ' ') END , "
        /*25*/ + " CASE WHEN nvl(to_char(GTN.ETA_PLUS, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(tic.eta_port_of_discharge, 'Mon/DD/YYYY'),  ' ') END, "
        /*26*/ + " NVL(TIE.OBSERVACIONES, ' ') AS OBSERVACIONES, "
        /*27*/ + " TIE.ESTATUS_EVENTO, "
        /*28*/ + " NVL(TIE.REFERENCIA_AA,' '), "
        /*29*/ + " CASE WHEN nvl(to_char(TIE.FECHA_CAPTURA, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIE.FECHA_CAPTURA, 'Mon/DD/YYYY'),  ' ') END, "
        /*30*/ + " NVL(TIE.PRIORIDAD,' '), "
        /*31*/ + " NVL(TIC.REFERENCIA_AA,' '), "
        /*32*/ + " NVL(TIC.PAIS_ORIGEN,' '), "
        /*33*/ + " NVL(TIC.SIZE_CONTAINER,' '), "
        /*34*/ + " NVL(TIC.VALOR_USD,' '), "
        /*35*/ + " CASE WHEN nvl(to_char(TIC.ETA_PORT_OF_DISCHARGE, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.ETA_PORT_OF_DISCHARGE, 'Mon/DD/YYYY'),  ' ') END, "
        /*36*/ + " NVL(TIC.AGENTE_ADUANAL,' '), "
        /*37*/ + " NVL(TIC.PEDIMENTO_A1,' '), "
        /*38*/ + " NVL(TIC.PEDIMENTO_R1,' '), "
        /*39*/ + " NVL(TIC.MOTIVO_RECTIFICACION_1,' '), "
        /*40*/ + " NVL(TIC.PEDIMENTO_R1_2DO,' '), "
        /*41*/ + " NVL(TIC.MOTIVO_RECTIFICACION_2,' '), "
        /*42*/ + " CASE WHEN nvl(to_char(TIC.FECHA_RECEPCION_DOCUMENTOS, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_RECEPCION_DOCUMENTOS, 'Mon/DD/YYYY'),  ' ') END, "
        /*43*/ + " NVL(TIC.RECINTO,' '), "
        /*44*/ + " NVL(TIC.NAVIERA_FORWARDER,' '), "
        /*45*/ + " NVL(TIC.BUQUE,' '), "
        /*46*/ + " CASE WHEN nvl(to_char(TIC.FECHA_REVALID_LIBE_BL, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_REVALID_LIBE_BL, 'Mon/DD/YYYY'),  ' ') END, "
        /*47*/ + " CASE WHEN nvl(to_char(TIC.FECHA_PREVIO_ORIGEN, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_PREVIO_ORIGEN, 'Mon/DD/YYYY'),  ' ') END, "
        /*48*/ + " CASE WHEN nvl(to_char(TIC.FECHA_PREVIO_DESTINO, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_PREVIO_DESTINO, 'Mon/DD/YYYY'),  ' ') END, "
        /*49*/ + " CASE WHEN nvl(to_char(TIC.FECHA_RESULTADO_PREVIO, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_RESULTADO_PREVIO, 'Mon/DD/YYYY'),  ' ') END, "
        /*50*/ + " CASE WHEN nvl(to_char(TIC.PROFORMA_FINAL, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.PROFORMA_FINAL, 'Mon/DD/YYYY'),  ' ') END, "
        /*51*/ + " NVL(TIC.REQUIERE_PERMISO,' '), "
        /*52*/ + " CASE WHEN nvl(to_char(TIC.FECHA_ENVIO_FICHAS_NOTAS, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_ENVIO_FICHAS_NOTAS, 'Mon/DD/YYYY'),  ' ') END, "
        /*53*/ + " CASE WHEN nvl(to_char(TIC.FEC_RECEPCION_PERMISOS_TRAMIT, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FEC_RECEPCION_PERMISOS_TRAMIT, 'Mon/DD/YYYY'),  ' ') END, "
        /*54*/ + " CASE WHEN nvl(to_char(TIC.FEC_ACT_PERMISOS, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FEC_ACT_PERMISOS, 'Mon/DD/YYYY'),  ' ') END, "
        /*55*/ + " CASE WHEN nvl(to_char(TIC.FEC_PERM_AUT, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FEC_PERM_AUT, 'Mon/DD/YYYY'),  ' ') END, "
        /*56*/ + " NVL(TIC.CO_APLIC_PREF_ARANCELARIA,' '), "
        /*57*/ + " NVL(TIC.APLIC_PREF_ARANCELARIA_CO,' '), "
        /*58*/ + " NVL(TIC.REQUIERE_UVA,' '), "
        /*59*/ + " NVL(TIC.REQUIERE_CA,' '), "
        /*60*/ + " CASE WHEN nvl(to_char(TIC.FECHA_RECEPCION_CA, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_RECEPCION_CA, 'Mon/DD/YYYY'),  ' ') END, "
        /*61*/ + " NVL(TIC.NÚMERO_CONSTANCIA_CA,' '), "
        /*62*/ + " NVL(TIC.MONTO_CA,' '), "
        /*63*/ + " CASE WHEN nvl(to_char(TIC.FECHA_DOCUMENTOS_COMPLETOS, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_DOCUMENTOS_COMPLETOS, 'Mon/DD/YYYY'),  ' ') END, "
        /*64*/ + " CASE WHEN nvl(to_char(TIC.FECHA_PAGO_PEDIMENTO, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_PAGO_PEDIMENTO, 'Mon/DD/YYYY'),  ' ') END,"
        /*65*/ + " CASE WHEN nvl(to_char(TIC.FECHA_SOLICITUD_TRANSPORTE, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_SOLICITUD_TRANSPORTE, 'Mon/DD/YYYY'),  ' ') END,"
        /*66*/ + " CASE WHEN nvl(to_char(TIC.FECHA_MODULACION, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_MODULACION, 'Mon/DD/YYYY'),  ' ') END,"
        /*67*/ + " NVL(TIC.MODALIDAD_CAMION_TREN,' '), "
        /*68*/ + " NVL(TIC.RESULT_MODULACION_VERDE_ROJO,' '), "
        /*69*/ + " CASE WHEN nvl(to_char(TIC.FECHA_RECONOCIMIENTO, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_RECONOCIMIENTO, 'Mon/DD/YYYY'),  ' ') END, "
        /*70*/ + " CASE WHEN nvl(to_char(TIC.FECHA_LIBERACION, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_LIBERACION, 'Mon/DD/YYYY'),  ' ') END, "
        /*71*/ + " NVL(TIC.SELLO_ORIGEN,' '), "
        /*72*/ + " NVL(TIC.SELLO_FINAL,' '), "
        /*73*/ + " CASE WHEN nvl(to_char(TIC.FECHA_RETENCION_AUTORIDAD, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_RETENCION_AUTORIDAD, 'Mon/DD/YYYY'),  ' ') END, "
        /*74*/ + " CASE WHEN nvl(to_char(TIC.FECHA_LIB_POR_RET_AUT, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_LIB_POR_RET_AUT, 'Mon/DD/YYYY'),  ' ') END, "
        /*75*/ + " NVL(TEC.DESCRIPCION_ESTADO,' '), "
        /*76*/ + " NVL(TIC.MOTIVO_ATRASO,' '), "
        /*77*/ + " NVL(TIC.OBSERVACIONES,' '), "
        /*78*/ + " CASE WHEN nvl(to_char(TIC.LLEGADA_A_NOVA, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.LLEGADA_A_NOVA, 'Mon/DD/YYYY'),  ' ') END, "
        /*79*/ + " CASE WHEN nvl(to_char(TIC.LLEGADA_A_GLOBE_TRADE_SD, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.LLEGADA_A_GLOBE_TRADE_SD, 'Mon/DD/YYYY'),  ' ') END, "
        /*80*/ + " NVL(TIC.ARCHIVO_M,' '), "
        /*81*/ + " CASE WHEN nvl(to_char(TIC.FECHA_ARCHIVO_M, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_ARCHIVO_M, 'Mon/DD/YYYY'),  ' ') END, "
        /*82*/ + " CASE WHEN nvl(to_char(TIC.FECHA_SOLICIT_MANIP, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_SOLICIT_MANIP, 'Mon/DD/YYYY'),  ' ') END, "
        /*83*/ + " CASE WHEN nvl(to_char(TIC.FECHA_VENCIM_MANIP, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_VENCIM_MANIP, 'Mon/DD/YYYY'),  ' ') END, "
        /*84*/ + " CASE WHEN nvl(to_char(TIC.FECHA_CONFIRM_CLAVE_PEDIM, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_CONFIRM_CLAVE_PEDIM, 'Mon/DD/YYYY'),  ' ') END, "
        /*85*/ + " CASE WHEN nvl(to_char(TIC.FECHA_RECEP_INCREMENT, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_RECEP_INCREMENT, 'Mon/DD/YYYY'),  ' ') END, "
        /*86*/ + " NVL(TIC.T_E,' '), "
        /*87*/ + " CASE WHEN nvl(to_char(TIC.FECHA_VENCIM_INBOUND, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_VENCIM_INBOUND, 'Mon/DD/YYYY'),  ' ') END, "
        /*88*/ + " NVL(TIC.NO_BULTOS,' '), "
        /*89*/ + " NVL(TIC.PESO_KG,' '), "
        /*90*/ + " NVL(TIC.TRANSFERENCIA,' '), "
        /*91*/ + " CASE WHEN nvl(to_char(TIC.FECHA_INICIO_ETIQUETADO, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_INICIO_ETIQUETADO, 'Mon/DD/YYYY'),  ' ') END, "
        /*92*/ + " CASE WHEN nvl(to_char(TIC.FECHA_TERMINO_ETIQUETADO, 'MM/DD/YYYY'), ' ') =  '12/30/1899' THEN NULL ELSE nvl(to_char(TIC.FECHA_TERMINO_ETIQUETADO, 'Mon/DD/YYYY'),  ' ') END, "
         /*93*/ + " NVL(TIC.HORA_TERMINO_ETIQUETADO,' '), "
        /*94*/ + " NVL(TIC.PROVEEDOR,' '), "
        /*95*/ + " NVL(TIC.PROVEEDOR_CARGA,' '), "
        /*96*/ + " NVL(TIC.FY,' '), "
        /*97*/ + " NVL(TIC.AGENTE_ADUANAL_ID,0), "
        /*98*/ + " NVL(TIC.PRIORIDAD,'No'), "
        /*99*/ + " NVL(GTN.ESTATUS,1), "
       /*100*/ + " NVL(TIC.ESTATUS_SEMAFORO,'0'), "
       /*101*/ + " NVL(TIP1.ADUANA_NUMERO,0), "
       /*102*/ + " NVL(TAA.PATENTE_AGENTE_ADUANERO,'0000'), "
       /*103*/ + " NVL(TO_CHAR(GTN.FECHA_IMPORTACION, 'DD/MM/YY'),'-') "
               + " FROM TRA_INB_EVENTO TIE "
               + " INNER JOIN TRA_DESTINO_RESPONSABLE BP ON BP.USER_NID = TIE.USER_NID "
               + " INNER JOIN TRA_INC_GTN_TEST GTN ON GTN.PLANTILLA_ID = TIE.PLANTILLA_ID "
               + " INNER JOIN TRA_INB_POD TIP1 ON TIP1.ID_POD = GTN.POD "
               + " INNER JOIN TRA_INB_POL TIP2 ON TIP2.ID_POL = GTN.POL "
               + " INNER JOIN TRA_INB_BRAND_DIVISION TIBD ON TIBD.ID_BD = GTN.BRAND_DIVISION "
               + " INNER JOIN TRA_INB_AGENTE_ADUANAL TAA ON TAA.AGENTE_ADUANAL_ID = TIP1.AGENTE_ADUANAL_ID "
               + " INNER JOIN TRA_INB_DIVISION TID ON TID.ID_DIVISION = GTN.SBU_NAME "
               + " INNER JOIN SUM_QUANTITY SQ ON SQ.SHIPMENT_ID = GTN.SHIPMENT_ID AND SQ.CONTAINER1 = GTN.CONTAINER1 "
               + " LEFT JOIN TRA_INB_CUSTOMS TIC ON GTN.SHIPMENT_ID = TIC.SHIPMENT_ID "
               + " LEFT JOIN TRA_ESTADOS_CUSTOMS TEC ON GTN.ESTATUS = TEC.ID_ESTADO "
               + " LEFT JOIN TRA_INB_SEMAFORO TISE ON TIC.SHIPMENT_ID = TISE.SHIPMENT_ID "
               + " WHERE TIE.ESTADO = 1 "
               //+ " AND to_date(trunc(tie.FECHA_CAPTURA),'dd/MM/yy') >= to_date((SELECT MIN(TO_DATE(FECHA_CAPTURA, 'DD/MM/YYYY')) FROM TRA_INB_EVENTO WHERE ESTADO = 1),'dd/MM/yy') "
               //+ " AND to_date(trunc(tie.FECHA_CAPTURA),'dd/MM/yy') <= to_date((SELECT MAX(TO_DATE(FECHA_CAPTURA, 'DD/MM/YYYY')) FROM TRA_INB_EVENTO WHERE ESTADO = 1),'dd/MM/yy') "
               + " AND tid.division_nombre <> 'No/DSN' "
               + " AND gtn.load_type_final IS NOT NULL "
               + " AND gtn.FINAL_DESTINATION IS NOT NULL "    
               + " AND tie.id_evento >= 240000 ";

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
                sql += " AND TO_DATE(trunc(GTN.EST_DEPARTURE_POL),'DD/MM/YY') = TO_DATE(" + caramelo_estDepartFromPol + ",'MM/DD/YY') ";
            }
            if (!caramelo_etaRealPortOfDischarge.equals("")) { //ETA REAL Port of Discharge
                sql += " AND TO_DATE(trunc(GTN.ETA_PORT_DISCHARGE),'DD/MM/YY') = TO_DATE(" + caramelo_etaRealPortOfDischarge + ",'MM/DD/YY') ";
            }
            if (!caramelo_estEtaDc.equals("")) { //Est. Eta DC
                sql += " AND TO_DATE(trunc(GTN.ETA_PLUS2),'DD/MM/YY') = TO_DATE(" + caramelo_estEtaDc + ",'MM/DD/YY') ";
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
            sql += " ORDER BY tie.id_evento, "
                 + " tibd.nombre_bd, "
                 + " GTN.SHIPMENT_ID ASC ";
        
        if(offset>=1){ //Bacheo por pasos de 1000 registros
            sql += " OFFSET "+offset+" ROWS FETCH NEXT "+next+" ROWS ONLY ";
        }    

         if (db.doDB(sql)) {
                for (String[] row : db.getResultado()) {
                    
                    fecha_importacion_registrada = row[102].replaceAll("-", "");
                            
                    if (row[99].equals("1")) {
                        colorSemaforo = "../img/circle-green.webp";
                        sizeSemaforo = "55%";
                    } else if (row[99].equals("2")) {
                        colorSemaforo = "../img/circle-yellow.webp";
                        sizeSemaforo = "50%";
                    } else if (row[99].equals("3")) {
                        colorSemaforo = "../img/circle-red.webp";
                        sizeSemaforo = "30%";
                    } else {
                        colorSemaforo = "../img/circle-gray.webp";
                        sizeSemaforo = "60%";
                    }

                    /*Fecha importación <-- { .... contar los días transcurridos .... } --> Fecha actual*/
                    if(!fecha_importacion_registrada.equals("")){    
                        
                        String[] fImp = fecha_importacion_registrada.split("/"); /*Fecha Importación*/
                        String[] fAct = dateDay.split("/");  /*Fecha Actual*/

                        /*Obtener los días habiles del rango de fechas*/
                        LocalDate fechaImportacion = LocalDate.of(Integer.parseInt(fImp[2]), Integer.parseInt(fImp[1]), Integer.parseInt(fImp[0]));                      
                        LocalDate fechaActual = LocalDate.of(Integer.parseInt(fAct[2]), Integer.parseInt(fAct[1]), Integer.parseInt(fAct[0]));   
                        
                        diasHabiles = contarDiasHabilesTranscurridos(fechaImportacion, fechaActual);
                        
                        /*Excluir iteración del shipment (++ 5 días habiles)*/
                        if(diasHabiles>5){
                            excluirShipment = true;
                        }else{
                            excluirShipment = false;
                        }
                        
                    }else{
                       excluirShipment = false;
                    }

            /*  ----------------------------- CUERPO/DATA DE TABLA  -----------------------------  */
            
                    if (!excluirShipment) {
                    salida += "<tr id=\"tr<" + cont + "\">"
                             // + " <th id=\"columna\"></th> "
                                + " <th id=\"columna\"><center><img id=\"imgSemaforo" + cont + "\" src=\"" + colorSemaforo + "\" width=\"" + sizeSemaforo + "\"></center></th> "
                                + " <th contenteditable=\"true\" oninput=\"validarTextoAlfanumericoReferenciaAA(this,'referenciaAA'," + cont + ")\" onkeydown=\"tabuladorVertical(event,'referenciaAA'," + cont + ")\"  id=\"referenciaAA[" + cont + "]\">" + row[30] + "</th> "
                                + " <th class=\"font-numero first-column\" id=\"elemento" + cont + "\">" + row[0] + "<input type=\"hidden\" id=\"evento[" + cont + "]\" name=\"evento[" + cont + "]\" value=\"" + row[0] + "\"></th> "
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
                                + " <td contenteditable=\"true\" oninput=\"validarTextoPais(this," + cont + ")\" onkeydown=\"tabuladorVertical(event,'pais_origen'," + cont + ")\" id=\"pais_origen[" + cont + "]\" onpaste=\"handlePasteText(event)\">" + row[31] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTextoAlfanumerico(this,'size_container'," + cont + ")\" onkeydown=\"tabuladorVertical(event,'size_container'," + cont + ")\" onpaste=\"handlePasteAlfanumerico(event)\" id=\"size_container[" + cont + "]\">" + row[32] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarMonto$(event)\" onkeydown=\"tabuladorVertical(event,'valor_usd'," + cont + ")\" onpaste=\"handlePasteMonto$(event)\" id=\"valor_usd[" + cont + "]\">" + row[33] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"eta_port_discharge[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'eta_port_discharge'," + cont + ")\" ondblclick=\"show_eta_port_discharge('" + row[34] + "'," + cont + ")\" onpaste=\"handlePasteFechaEtaPortDischarge(event," + cont + ")\">" + row[34] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTextoParametrizacion(this,'agente_aduanal'," + cont + ")\" onkeydown=\"tabuladorVertical(event,'agente_aduanal'," + cont + ")\" onpaste=\"handlePasteText(event)\" id=\"agente_aduanal[" + cont + "]\">" + row[35] + "</td> "
                                + " <td contenteditable=\"true\" onkeypress=\"formatoNumero(event,'" + cont + "','pedimento_a1')\" onkeydown=\"tabuladorVertical(event,'pedimento_a1'," + cont + ")\" onpaste=\"handlePasteNumberPedimento(event,'" + cont + "','pedimento_a1')\" id=\"pedimento_a1[" + cont + "]\">" + row[36] + "</td> "
                                + " <td contenteditable=\"true\" onkeypress=\"formatoNumero(event,'" + cont + "','pedimento_r1_1er')\" onkeydown=\"tabuladorVertical(event,'pedimento_r1_1er'," + cont + ")\" onpaste=\"handlePasteNumberPedimento(event,'" + cont + "','pedimento_r1_1er')\" id=\"pedimento_r1_1er[" + cont + "]\">" + row[37] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTextoAlfanumericoMotivoRectificacion(this,'motivo_rectificacion_1er'," + cont + ")\" onkeydown=\"tabuladorVertical(event,'motivo_rectificacion_1er'," + cont + ")\" onpaste=\"handlePasteAlfanumericoMotivoRectificacion(event)\" id=\"motivo_rectificacion_1er[" + cont + "]\">" + row[38] + "</td> "
                                + " <td contenteditable=\"true\" onkeypress=\"formatoNumero(event,'" + cont + "','pedimento_r1_2do')\" onkeydown=\"tabuladorVertical(event,'pedimento_r1_2do'," + cont + ")\" onpaste=\"handlePasteNumberPedimento(event,'" + cont + "','pedimento_r1_2do')\" id=\"pedimento_r1_2do[" + cont + "]\">" + row[39] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTextoAlfanumericoMotivoRectificacion(this,'motivo_rectificacion_2do'," + cont + ")\" onkeydown=\"tabuladorVertical(event,'motivo_rectificacion_2do'," + cont + ")\" onpaste=\"handlePasteAlfanumericoMotivoRectificacion(event)\" id=\"motivo_rectificacion_2do[" + cont + "]\">" + row[40] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_recepcion_doc[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_recepcion_doc'," + cont + ")\" ondblclick=\"show_fecha_recepcion_doc('" + row[41] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[41] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTextoAlfanumerico(this,'recinto'," + cont + ")\" onkeydown=\"tabuladorVertical(event,'recinto'," + cont + ")\" onpaste=\"handlePasteAlfanumerico(event)\" id=\"recinto[" + cont + "]\">" + row[42] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTextoParametrizacion(this,'naviera'," + cont + ")\" onkeydown=\"tabuladorVertical(event,'naviera'," + cont + ")\" onpaste=\"handlePasteText(event)\" id=\"naviera[" + cont + "]\">" + row[43] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTextoParametrizacion(this,'buque'," + cont + ")\" onkeydown=\"tabuladorVertical(event,'buque'," + cont + ")\" onpaste=\"handlePasteText(event)\" id=\"buque[" + cont + "]\">" + row[44] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_revalidacion[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_revalidacion'," + cont + ")\" ondblclick=\"show_fecha_revalidacion('" + row[45] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[45] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_previo_origen[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_previo_origen'," + cont + ")\" ondblclick=\"show_fecha_previo_origen('" + row[46] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[46] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_previo_destino[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_previo_destino'," + cont + ")\" ondblclick=\"show_fecha_previo_destino('" + row[47] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[47] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_resultado_previo[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_resultado_previo'," + cont + ")\" ondblclick=\"show_fecha_resultado_previo('" + row[48] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[48] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"proforma_final[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'proforma_final'," + cont + ")\" ondblclick=\"show_proforma_final('" + row[49] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[49] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTextoCheckbox(this)\" id=\"permiso[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'permiso'," + cont + ")\" ondblclick=\"show_permiso(" + cont + ")\" onpaste=\"handlePasteTextCheckBox(event)\">" + row[50] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_envio[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_envio'," + cont + ")\" ondblclick=\"show_fecha_envio('" + row[51] + "','fecha_envio'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[51] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_recepcion_perm[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_recepcion_perm'," + cont + ")\" ondblclick=\"show_fecha_recepcion_perm('" + row[52] + "','fecha_recepcion_perm'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[52] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_activacion_perm[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_activacion_perm'," + cont + ")\" ondblclick=\"show_fecha_activacion_perm('" + row[53] + "','fecha_activacion_perm'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[53] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_permisos_aut[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_permisos_aut'," + cont + ")\" ondblclick=\"show_fecha_permisos_aut('" + row[54] + "','fecha_permisos_aut'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[54] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTextoCheckbox(this)\" id=\"co_pref_arancelaria[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'co_pref_arancelaria'," + cont + ")\" ondblclick=\"show_co_pref_arancelaria(" + cont + ")\" onpaste=\"handlePasteTextCheckBox(event)\">" + row[55] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTextoCheckbox(this)\" id=\"aplic_pref_arancelaria[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'aplic_pref_arancelaria'," + cont + ")\" ondblclick=\"show_aplic_pref_arancelaria(" + cont + ")\" onpaste=\"handlePasteTextCheckBox(event)\">" + row[56] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTextoCheckbox(this)\" id=\"req_uva[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'req_uva'," + cont + ")\" ondblclick=\"show_req_uva(" + cont + ")\" onpaste=\"handlePasteTextCheckBox(event)\">" + row[57] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTextoCheckbox(this)\" id=\"req_ca[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'req_ca'," + cont + ")\" ondblclick=\"show_req_ca(" + cont + ")\" onpaste=\"handlePasteTextCheckBox(event)\">" + row[58] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_recepcion_ca[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_recepcion_ca'," + cont + ")\" ondblclick=\"show_fecha_recepcion_ca('" + row[59] + "','fecha_recepcion_ca'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[59] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarNumero(event)\" onkeydown=\"tabuladorVertical(event,'num_constancia_ca'," + cont + ")\" onpaste=\"handlePasteNumber(event)\" onclick=\"show_num_constancia_ca('num_constancia_ca'," + cont + ")\" id=\"num_constancia_ca[" + cont + "]\">" + row[60] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarMonto$(event)\" onkeydown=\"tabuladorVertical(event,'monto_ca'," + cont + ")\" onpaste=\"handlePasteMonto$(event)\" contenteditable=\"true\" onclick=\"show_monto_ca('monto_ca'," + cont + ")\" id=\"monto_ca[" + cont + "]\">" + row[61] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_doc_completos[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_doc_completos'," + cont + ")\" ondblclick=\"show_fecha_doc_completos('" + row[63] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[62] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_pago_pedimento[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_pago_pedimento'," + cont + ")\" ondblclick=\"show_fecha_pago_pedimento(" + cont + ")\" onpaste=\"handlePasteFechaPagoPedimento(event," + cont + ")\">" + row[63] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_solicitud_transporte[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_solicitud_transporte'," + cont + ")\" ondblclick=\"show_fecha_solicitud_transporte('" + row[64] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[64] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_modulacion[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_modulacion'," + cont + ")\" ondblclick=\"show_fecha_modulacion(" + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[65] + "</td> "
                                + " <td contenteditable=\"true\" id=\"modalidad[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'modalidad'," + cont + ")\" ondblclick=\"show_modalidad(" + cont + ")\" onpaste=\"handlePasteText(event)\">" + row[66] + "</td> "
                                + " <td contenteditable=\"true\" id=\"resultado_modulacion[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'resultado_modulacion'," + cont + ")\" ondblclick=\"show_resultado_modulacion(" + cont + "," + AgentType + ")\" onpaste=\"handlePasteText(event)\">" + row[67] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_reconocimiento[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_reconocimiento'," + cont + ")\" ondblclick=\"show_fecha_reconocimiento('" + row[68] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[68] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_liberacion[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_liberacion'," + cont + ")\" ondblclick=\"show_fecha_liberacion('" + row[69] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[69] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTextoAlfanumerico(this,'sello_origen'," + cont + ")\" onkeydown=\"tabuladorVertical(event,'sello_origen'," + cont + ")\" onpaste=\"handlePasteAlfanumerico(event)\" id=\"sello_origen[" + cont + "]\">" + row[70] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTextoAlfanumerico(this,'sello_final'," + cont + ")\" onkeydown=\"tabuladorVertical(event,'sello_final'," + cont + ")\" onpaste=\"handlePasteAlfanumerico(event)\" id=\"sello_final[" + cont + "]\">" + row[71] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_retencion_aut[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_retencion_aut'," + cont + ")\" ondblclick=\"show_fecha_retencion_aut('" + row[72] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[72] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_liberacion_aut[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_liberacion_aut'," + cont + ")\" ondblclick=\"show_fecha_liberacion_aut('" + row[73] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[73] + "</td> "
                                + " <td onmouseover=\"formComplet('" + AgentType + "'," + cont + ")\"><select class=\"form-control\" style=\"border: none; outline: none;\" id=\"estatus_operacion[" + cont + "]\" name=\"estatus_operacion[" + cont + "]\" onchange=\"parametrizacionValoresEstatusOperacion("+cont+")\" value=\"" + row[74] + "\"> <option value=\"" + row[98] + "\">" + row[74] + "</option>" + listStatusOperationEvent + "</select></td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTextoAlfanumericoMotivoAtraso(this,'motivo_atraso'," + cont + ")\" onkeydown=\"tabuladorVertical(event,'motivo_atraso'," + cont + ")\" id=\"motivo_atraso[" + cont + "]\" onpaste=\"handlePasteAlfanumericoMotivoAtraso(event)\">" + row[75] + "</td> "
                                + " <td contenteditable=\"true\" oninput=\"validarTextoAlfanumericoObservaciones(this,'observaciones'," + cont + ")\" onkeydown=\"tabuladorVertical(event,'observaciones'," + cont + ")\" onpaste=\"handlePasteAlfanumericoObservaciones(event)\" id=\"observaciones[" + cont + "]\">" + row[76] + "</td> ";

                        if (AgentType.equals("4001") || AgentType.equals("4006")) { //LOGIX Y VF

                        salida += " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"llegada_a_nova[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'llegada_a_nova'," + cont + ")\" ondblclick=\"show_llegada_a_nova('" + row[77] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[77] + "</td> "
                                    + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"llegada_a_globe_trade_sd[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'llegada_a_globe_trade_sd'," + cont + ")\" ondblclick=\"show_llegada_a_globe_trade_sd('" + row[78] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[78] + "</td> "
                                    + " <td contenteditable=\"true\" oninput=\"validarTextoAlfanumericoSnParametrizacion(this)\" onkeydown=\"tabuladorVertical(event,'archivo_m'," + cont + ")\" onpaste=\"handlePasteAlfanumerico(event)\" id=\"archivo_m[" + cont + "]\">" + row[79] + "</td> "
                                    + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_archivo_m[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_archivo_m'," + cont + ")\" ondblclick=\"show_fecha_archivo_m('" + row[80] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[80] + "</td> "
                                    + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_solicit_manip[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_solicit_manip'," + cont + ")\" ondblclick=\"show_fecha_solicit_manip('" + row[81] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[81] + "</td> "
                                    + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_vencim_manip[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_vencim_manip'," + cont + ")\" ondblclick=\"show_fecha_vencim_manip('" + row[82] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[82] + "</td> "
                                    + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_confirm_clave_pedim[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_confirm_clave_pedim'," + cont + ")\" ondblclick=\"show_fecha_confirm_clave_pedim('" + row[83] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[83] + "</td> "
                                    + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_recep_increment[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_recep_increment'," + cont + ")\" ondblclick=\"show_fecha_recep_increment('" + row[84] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[84] + "</td> "
                                    + " <td contenteditable=\"true\" oninput=\"validarNumeroT_E(event)\" onkeydown=\"tabuladorVertical(event,'t_e'," + cont + ")\" onpaste=\"handlePasteNumberT_E(event)\" id=\"t_e[" + cont + "]\">" + row[85] + "</td> "
                                    + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_vencim_inbound[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_vencim_inbound'," + cont + ")\" ondblclick=\"show_fecha_vencim_inbound('" + row[86] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[86] + "</td> ";
                        }

                        if (AgentType.equals("4002") || AgentType.equals("4006")) {  //CUSA Y VF

                        salida += " <td contenteditable=\"true\" oninput=\"validarNumero(event)\" onkeydown=\"tabuladorVertical(event,'no_bultos'," + cont + ")\" onpaste=\"handlePasteNumber(event)\" id=\"no_bultos[" + cont + "]\">" + row[87] + "</td> "
                                    + " <td contenteditable=\"true\" oninput=\"validarNumero(event)\" onkeydown=\"tabuladorVertical(event,'peso_kg'," + cont + ")\" onpaste=\"handlePasteNumber(event)\" id=\"peso_kg[" + cont + "]\">" + row[88] + "</td> "
                                    + " <td contenteditable=\"true\" oninput=\"validarTextoCheckbox(this)\" id=\"transferencia[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'transferencia'," + cont + ")\" ondblclick=\"show_transferencia(" + cont + ")\" onpaste=\"handlePasteTextCheckBox(event)\">" + row[89] + "</td> "
                                    + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_inicio_etiquetado[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_inicio_etiquetado'," + cont + ")\" ondblclick=\"show_fecha_inicio_etiquetado('" + row[90] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[90] + "</td> "
                                    + " <td contenteditable=\"true\" oninput=\"formatoFecha(event)\" id=\"fecha_termino_etiquetado[" + cont + "]\" onkeydown=\"tabuladorVertical(event,'fecha_termino_etiquetado'," + cont + ")\" ondblclick=\"show_fecha_termino_etiquetado('" + row[91] + "'," + cont + ")\" onpaste=\"handlePasteFecha(event)\">" + row[91] + "</td> "
                                    + " <td contenteditable=\"true\" id=\"time_termino_etiquetado[" + cont + "]\" onclick=\"onclickTime(" + cont + ")\">" + row[92] + "</td> "
                                    + " <td contenteditable=\"true\" oninput=\"validarTexto(this)\" onkeydown=\"tabuladorVertical(event,'proveedor'," + cont + ")\" onpaste=\"handlePasteText(event)\" id=\"proveedor[" + cont + "]\">" + row[93] + "</td> "
                                    + " <td contenteditable=\"true\" oninput=\"validarTextoParametrizacion(this,'proveedor_carga'," + cont + ")\" onkeydown=\"tabuladorVertical(event,'proveedor_carga'," + cont + ")\" onpaste=\"handlePasteText(event)\" id=\"proveedor_carga[" + cont + "]\">" + row[94] + "</td> ";
                        }

                        salida += " <td contenteditable=\"true\" oninput=\"validarTextoAlfanumericoSnParametrizacion(this)\" onkeydown=\"tabuladorVertical(event,'fy'," + cont + ")\" onpaste=\"handlePasteAlfanumerico(event)\" id=\"fy[" + cont + "]\">" + row[95] + "</td> "
                                + " <td><a class=\"btn btn-primary text-uppercase\" onclick=\"AddLineCustoms(" + cont + ")\"><i class=\"fa fa-save\"></i></a></td> "
                                + "</tr>";

                         cont++;  //Contabilizar número de registros/batch
                    }
                    
                }
         }
            
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
            Logger.getLogger(ConsultarCustomsBacheo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarCustomsBacheo.class.getName()).log(Level.SEVERE, null, ex);
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
