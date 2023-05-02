/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.insertar.catalogos;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.consultas.ConsultasQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class InsertarCustomsForms extends HttpServlet {
    
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
        
        try{
            
        Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha_registro = formato.format(date);
        ConsultasQuery fac = new ConsultasQuery();
    
          //Parametros Indicadores
          String evento = request.getParameter("evento");
          String shipmentId = request.getParameter("shipmentId");
          String containerId = request.getParameter("containerId");
          String referenciaAA = request.getParameter("referenciaAA"); 
          String prioridad = request.getParameter("prioridad"); 
          String idAgenteAduanal = request.getParameter("idAgenteAduanal"); 
          
          //Parametros Generales
          String pais_origen = request.getParameter("pais_origen"); 
          String size_container = request.getParameter("size_container"); 
          String valor_usd = request.getParameter("valor_usd");                
          String eta_port_discharge = request.getParameter("eta_port_discharge");        
          String agente_aduanal = request.getParameter("agente_aduanal");            
          String pedimento_a1 = request.getParameter("pedimento_a1");             
          String pedimento_r1_1er = request.getParameter("pedimento_r1_1er");          
          String motivo_rectificacion_1er = request.getParameter("motivo_rectificacion_1er");  
          String pedimento_r1_2do = request.getParameter("pedimento_r1_2do");          
          String motivo_rectificacion_2do = request.getParameter("motivo_rectificacion_2do");  
          String fecha_recepcion_doc = request.getParameter("fecha_recepcion_doc");  
          String recinto = request.getParameter("recinto");
          String naviera = request.getParameter("naviera");
          String buque = request.getParameter("buque");
          String fecha_revalidacion = request.getParameter("fecha_revalidacion");        
          String fecha_previo_origen = request.getParameter("fecha_previo_origen");       
          String fecha_previo_destino = request.getParameter("fecha_previo_destino");      
          String fecha_resultado_previo = request.getParameter("fecha_resultado_previo");    
          String proforma_final = request.getParameter("proforma_final");            
          String permiso = request.getParameter("permiso");                   
          String fecha_envio = request.getParameter("fecha_envio");               
          String fecha_recepcion_perm = request.getParameter("fecha_recepcion_perm");    
          String fecha_activacion_perm = request.getParameter("fecha_activacion_perm");     
          String fecha_permisos_aut = request.getParameter("fecha_permisos_aut");        
          String co_pref_arancelaria = request.getParameter("co_pref_arancelaria");       
          String aplic_pref_arancelaria = request.getParameter("aplic_pref_arancelaria");    
          String req_uva = request.getParameter("req_uva");   
          String req_ca = request.getParameter("req_ca");   
          String fecha_recepcion_ca = request.getParameter("fecha_recepcion_ca");   
          String num_constancia_ca = request.getParameter("num_constancia_ca");   
          String monto_ca = request.getParameter("monto_ca");   
          String fecha_doc_completos = request.getParameter("fecha_doc_completos");       
          String fecha_pago_pedimento = request.getParameter("fecha_pago_pedimento");      
          String fecha_solicitud_transporte = request.getParameter("fecha_solicitud_transporte");
          String fecha_modulacion = request.getParameter("fecha_modulacion");          
          String modalidad = request.getParameter("modalidad");                 
          String resultado_modulacion = request.getParameter("resultado_modulacion");      
          String fecha_reconocimiento = request.getParameter("fecha_reconocimiento");      
          String fecha_liberacion = request.getParameter("fecha_liberacion");         
          String sello_origen = request.getParameter("sello_origen");              
          String sello_final = request.getParameter("sello_final");               
          String fecha_retencion_aut = request.getParameter("fecha_retencion_aut");       
          String fecha_liberacion_aut = request.getParameter("fecha_liberacion_aut");     
          String estatus_operacion = request.getParameter("estatus_operacion");         
          String motivo_atraso = request.getParameter("motivo_atraso");             
          String observaciones = request.getParameter("observaciones"); 
          String fy = request.getParameter("fy"); 
          
          //Parametros Logix
          String llegada_a_nova = request.getParameter("llegada_a_nova"); 
          String llegada_a_globe_trade_sd = request.getParameter("llegada_a_globe_trade_sd"); 
          String archivo_m = request.getParameter("archivo_m"); 
          String fecha_archivo_m = request.getParameter("fecha_archivo_m"); 
          String fecha_solicit_manip = request.getParameter("fecha_solicit_manip"); 
          String fecha_vencim_manip = request.getParameter("fecha_vencim_manip"); 
          String fecha_confirm_clave_pedim = request.getParameter("fecha_confirm_clave_pedim"); 
          String fecha_recep_increment = request.getParameter("fecha_recep_increment"); 
          String t_e = request.getParameter("t_e"); 
          String fecha_vencim_inbound = request.getParameter("fecha_vencim_inbound"); 
          
          //Parametros Cusa
          String no_bultos = request.getParameter("no_bultos"); 
          String peso_kg = request.getParameter("peso_kg"); 
          String transferencia = request.getParameter("transferencia"); 
          String fecha_inicio_etiquetado= request.getParameter("fecha_inicio_etiquetado"); 
          String fecha_termino_etiquetado = request.getParameter("fecha_termino_etiquetado"); 
          String hora_termino_etiquetado = request.getParameter("hora_termino_etiquetado"); 
          String proveedor = request.getParameter("proveedor"); 
          String proveedor_carga = request.getParameter("proveedor_carga"); 
          
      String salida = "";
        
      String insertarCustoms =  " INSERT INTO TRA_INB_CUSTOMS " 
                              + " (CUSTREG_ID, "  
                              + " NUMERO_DE_EVENTO, "  
                              + " SHIPMENT_ID, "  
                              + " CONTAINER_ID, "  
                              + " REFERENCIA_AA, "
                              + " PAIS_ORIGEN, "  
                              + " SIZE_CONTAINER, "  
                              + " VALOR_USD, "                
                              + " ETA_PORT_DISCHARGE, "        
                              + " AGENTE_ADUANAL, "            
                              + " PEDIMENTO_A1, "              
                              + " PEDIMENTO_R1_1ER, "          
                              + " MOTIVO_RECTIFICACION_1ER, "  
                              + " PEDIMENTO_R1_2DO, "          
                              + " MOTIVO_RECTIFICACION_2DO, "  
                              + " FECHA_RECEPCION_DOC, "    
                              + " RECINTO, "    
                              + " NAVIERA, "    
                              + " BUQUE, "   
                              + " FECHA_REVALIDACION, "        
                              + " FECHA_PREVIO_ORIGEN, "       
                              + " FECHA_PREVIO_DESTINO, "      
                              + " FECHA_RESULTADO_PREVIO, "    
                              + " PROFORMA_FINAL, "            
                              + " PERMISO, "                   
                              + " FECHA_ENVIO, "               
                              + " FECHA_RECEPCION_PERM, "      
                              + " FECHA_ACTIVACION_PERM, "     
                              + " FECHA_PERMISOS_AUT, "        
                              + " CO_PREF_ARANCELARIA, "       
                              + " APLIC_PREF_ARANCELARIA, "    
                              + " REQ_UVA, "    
                              + " REQ_CA, "  
                              + " FECHA_RECEPCION_CA, "  
                              + " NUM_CONSTANCIA_CA, "  
                              + " MONTO_CA, "  
                              + " FECHA_DOC_COMPLETOS, "       
                              + " FECHA_PAGO_PEDIMENTO, "      
                              + " FECHA_SOLICITUD_TRANSPORTE, "
                              + " FECHA_MODULACION, "          
                              + " MODALIDAD, "                 
                              + " RESULTADO_MODULACION, "      
                              + " FECHA_RECONOCIMIENTO, "      
                              + " FECHA_LIBERACION, "         
                              + " SELLO_ORIGEN, "              
                              + " SELLO_FINAL, "               
                              + " FECHA_RETENCION_AUT, "       
                              + " FECHA_LIBERACION_AUT, "      
                              + " ESTATUS_OPERACION, "         
                              + " MOTIVO_ATRASO, "             
                              + " OBSERVACIONES, ";        
        if(idAgenteAduanal.equals("4001")){ //LOGIX
              insertarCustoms  += " LLEGADA_A_NOVA, "
                             + " LLEGADA_A_GLOBE_TRADE_SD, " 
                             + " ARCHIVO_M, " 
                             + " FECHA_ARCHIVO_M, " 
                             + " FECHA_SOLICIT_MANIP, " 
                             + " FECHA_VENCIM_MANIP, " 
                             + " FECHA_CONFIRM_CLAVE_PEDIM, " 
                             + " FECHA_RECEP_INCREMENT, " 
                             + " T_E, " 
                             + " FECHA_VENCIM_INBOUND, ";
        }           
        if(idAgenteAduanal.equals("4002")){ //CUSA
              insertarCustoms  += " NO_BULTOS, "
                             + " PESO_KG, " 
                             + " TRANSFERENCIA, " 
                             + " FECHA_INICIO_ETIQUETADO, " 
                             + " FECHA_TERMINO_ETIQUETADO, " 
                             + " HORA_TERMINO_ETIQUETADO, " 
                             + " PROVEEDOR, " 
                             + " PROVEEDOR_CARGA, ";
        } 
               insertarCustoms  += " FY, "
                              + " AGENTE_ADUANAL_ID, "
                              + " PRIORIDAD, "
                              + " FECHA_REGISTRO, "            
                              + " ESTATUS_ID, "                
                              + " CBDIV_ID, "
                              + " USER_NID) "
                              + " VALUES " 
                              + " (NULL, "  
                              + " '" + evento + "', "  
                              + " '" + shipmentId + "', "  
                              + " '" + containerId + "', "  
                              + " '" + referenciaAA + "', "
                              + " '" + pais_origen + "', "  
                              + " '" + size_container + "', "  
                              + " '" + valor_usd + "', "                
                              + " TO_DATE('" + eta_port_discharge + "', 'mm/dd/yy HH24:MI:SS'), "        
                              + " '" + agente_aduanal + "', "            
                              + " '" + pedimento_a1 + "', "              
                              + " '" + pedimento_r1_1er + "', "          
                              + " '" + motivo_rectificacion_1er + "', "
                              + " '" + pedimento_r1_2do + "', "          
                              + " '" + motivo_rectificacion_2do + "', "  
                              + " TO_DATE('" + fecha_recepcion_doc + "', 'mm/dd/yy HH24:MI:SS'), "       
                              + " '" + recinto + "', " 
                              + " '" + naviera + "', " 
                              + " '" + buque + "', " 
                              + " TO_DATE('" + fecha_revalidacion + "', 'mm/dd/yy HH24:MI:SS'), "         
                              + " TO_DATE('" + fecha_previo_origen + "', 'mm/dd/yy HH24:MI:SS'), "           
                              + " TO_DATE('" + fecha_previo_destino + "', 'mm/dd/yy HH24:MI:SS'), "          
                              + " TO_DATE('" + fecha_resultado_previo + "', 'mm/dd/yy HH24:MI:SS'), "        
                              + " TO_DATE('" + proforma_final + "', 'mm/dd/yy HH24:MI:SS'), "                
                              + " '" + permiso + "', "                   
                              + " TO_DATE('" + fecha_envio + "', 'mm/dd/yy HH24:MI:SS'), "                   
                              + " TO_DATE('" + fecha_recepcion_perm + "', 'mm/dd/yy HH24:MI:SS'), "          
                              + " TO_DATE('" + fecha_activacion_perm + "', 'mm/dd/yy HH24:MI:SS'), "         
                              + " TO_DATE('" + fecha_permisos_aut + "', 'mm/dd/yy HH24:MI:SS'), "            
                              + " '" + co_pref_arancelaria + "', "       
                              + " '" + aplic_pref_arancelaria + "', "    
                              + " '" + req_uva + "', "   
                              + " '" + req_ca + "', "   
                              + " TO_DATE('" + fecha_recepcion_ca + "', 'mm/dd/yy HH24:MI:SS'), "       
                              + " '" + num_constancia_ca + "', "   
                              + " '" + monto_ca + "', "  
                              + " TO_DATE('" + fecha_doc_completos + "', 'mm/dd/yy HH24:MI:SS'), "           
                              + " TO_DATE('" + fecha_pago_pedimento + "', 'mm/dd/yy HH24:MI:SS'), "          
                              + " TO_DATE('" + fecha_solicitud_transporte + "', 'mm/dd/yy HH24:MI:SS'), "    
                              + " TO_DATE('" + fecha_modulacion + "', 'mm/dd/yy HH24:MI:SS'), "              
                              + " '" + modalidad + "', "                 
                              + " '" + resultado_modulacion + "', "      
                              + " TO_DATE('" + fecha_reconocimiento + "', 'mm/dd/yy HH24:MI:SS'), "          
                              + " TO_DATE('" + fecha_liberacion + "', 'mm/dd/yy HH24:MI:SS'), "             
                              + " '" + sello_origen + "', "              
                              + " '" + sello_final + "', "               
                              + " TO_DATE('" + fecha_retencion_aut + "', 'mm/dd/yy HH24:MI:SS'), "           
                              + " TO_DATE('" + fecha_liberacion_aut + "', 'mm/dd/yy HH24:MI:SS'), "          
                              + " '" + estatus_operacion + "', "         
                              + " '" + motivo_atraso + "', "             
                              + " '" + observaciones + "', "; 
        if(idAgenteAduanal.equals("4001")){ //LOGIX
              insertarCustoms  +=  " TO_DATE('" + llegada_a_nova + "', 'mm/dd/yy HH24:MI:SS'), "    
                              + " TO_DATE('" + llegada_a_globe_trade_sd + "', 'mm/dd/yy HH24:MI:SS'), "    
                              + " '" + archivo_m + "', "
                              + " TO_DATE('" + fecha_archivo_m + "', 'mm/dd/yy HH24:MI:SS'), "    
                              + " TO_DATE('" + fecha_solicit_manip + "', 'mm/dd/yy HH24:MI:SS'), "    
                              + " TO_DATE('" + fecha_vencim_manip + "', 'mm/dd/yy HH24:MI:SS'), "    
                              + " TO_DATE('" + fecha_confirm_clave_pedim + "', 'mm/dd/yy HH24:MI:SS'), "    
                              + " TO_DATE('" + fecha_recep_increment + "', 'mm/dd/yy HH24:MI:SS'), "    
                              + " '" + t_e + "', "
                              + " TO_DATE('" + fecha_vencim_inbound + "', 'mm/dd/yy HH24:MI:SS'), ";     
        }   
        if(idAgenteAduanal.equals("4002")){ //CUSA
              insertarCustoms  +=  " '" + no_bultos + "', "
                              + " '" + peso_kg + "', "
                              + " '" + transferencia + "', "
                              + " TO_DATE('" + fecha_inicio_etiquetado + "', 'mm/dd/yy HH24:MI:SS'), "    
                              + " TO_DATE('" + fecha_termino_etiquetado + "', 'mm/dd/yy HH24:MI:SS'), "    
                              + " '" + hora_termino_etiquetado + "', "
                              + " '" + proveedor + "', "
                              + " '" + proveedor_carga + "', "; 
        }        
              insertarCustoms  +=  " '" + fy + "', "
                              + " '" + idAgenteAduanal + "', "
                              + " '" + prioridad + "', "
                              + " TO_DATE(SYSDATE, 'mm/dd/yy HH24:MI:SS'), "               
                              + " 1, "                
                              + " '" + cve + "', "
                              + " '" + UserId + "') ";  
        boolean oraOut1 = oraDB.execute(insertarCustoms);
        
       
         String historicoCustoms =  " INSERT INTO TRA_HIST_INB_CUSTOMS " 
                                  + " (CUSTREG_ID, "  
                                  + " NUMERO_DE_EVENTO, "  
                                  + " SHIPMENT_ID, "  
                                  + " CONTAINER_ID, "  
                                  + " REFERENCIA_AA, "
                                  + " PAIS_ORIGEN, "  
                                  + " SIZE_CONTAINER, "  
                                  + " VALOR_USD, "                
                                  + " ETA_PORT_DISCHARGE, "        
                                  + " AGENTE_ADUANAL, "            
                                  + " PEDIMENTO_A1, "              
                                  + " PEDIMENTO_R1_1ER, "          
                                  + " MOTIVO_RECTIFICACION_1ER, "  
                                  + " PEDIMENTO_R1_2DO, "          
                                  + " MOTIVO_RECTIFICACION_2DO, "  
                                  + " FECHA_RECEPCION_DOC, "    
                                  + " RECINTO, "    
                                  + " NAVIERA, "    
                                  + " BUQUE, "   
                                  + " FECHA_REVALIDACION, "        
                                  + " FECHA_PREVIO_ORIGEN, "       
                                  + " FECHA_PREVIO_DESTINO, "      
                                  + " FECHA_RESULTADO_PREVIO, "    
                                  + " PROFORMA_FINAL, "            
                                  + " PERMISO, "                   
                                  + " FECHA_ENVIO, "               
                                  + " FECHA_RECEPCION_PERM, "      
                                  + " FECHA_ACTIVACION_PERM, "     
                                  + " FECHA_PERMISOS_AUT, "        
                                  + " CO_PREF_ARANCELARIA, "       
                                  + " APLIC_PREF_ARANCELARIA, "    
                                  + " REQ_UVA, "    
                                  + " REQ_CA, "  
                                  + " FECHA_RECEPCION_CA, "  
                                  + " NUM_CONSTANCIA_CA, "  
                                  + " MONTO_CA, "  
                                  + " FECHA_DOC_COMPLETOS, "       
                                  + " FECHA_PAGO_PEDIMENTO, "      
                                  + " FECHA_SOLICITUD_TRANSPORTE, "
                                  + " FECHA_MODULACION, "          
                                  + " MODALIDAD, "                 
                                  + " RESULTADO_MODULACION, "      
                                  + " FECHA_RECONOCIMIENTO, "      
                                  + " FECHA_LIBERACION, "         
                                  + " SELLO_ORIGEN, "              
                                  + " SELLO_FINAL, "               
                                  + " FECHA_RETENCION_AUT, "       
                                  + " FECHA_LIBERACION_AUT, "      
                                  + " ESTATUS_OPERACION, "         
                                  + " MOTIVO_ATRASO, "             
                                  + " OBSERVACIONES, ";
            if(idAgenteAduanal.equals("4001")){ //LOGIX
                  insertarCustoms  += " LLEGADA_A_NOVA, "
                                 + " LLEGADA_A_GLOBE_TRADE_SD, " 
                                 + " ARCHIVO_M, " 
                                 + " FECHA_ARCHIVO_M, " 
                                 + " FECHA_SOLICIT_MANIP, " 
                                 + " FECHA_VENCIM_MANIP, " 
                                 + " FECHA_CONFIRM_CLAVE_PEDIM, " 
                                 + " FECHA_RECEP_INCREMENT, " 
                                 + " T_E, " 
                                 + " FECHA_VENCIM_INBOUND, ";
            } 
            if(idAgenteAduanal.equals("4002")){ //CUSA
                  insertarCustoms  += " NO_BULTOS, "
                                 + " PESO_KG, " 
                                 + " TRANSFERENCIA, " 
                                 + " FECHA_INICIO_ETIQUETADO, " 
                                 + " FECHA_TERMINO_ETIQUETADO, " 
                                 + " HORA_TERMINO_ETIQUETADO, " 
                                 + " PROVEEDOR, " 
                                 + " PROVEEDOR_CARGA, ";
            } 
                   insertarCustoms  += " FY, "
                                  + " AGENTE_ADUANAL_ID, "
                                  + " PRIORIDAD, "
                                  + " FECHA_REGISTRO, "            
                                  + " ESTATUS_ID, "                
                                  + " CBDIV_ID, "
                                  + " USER_NID) "
                                  + " VALUES " 
                                  + " (NULL, "  
                                  + " '" + evento + "', "  
                                  + " '" + shipmentId + "', "  
                                  + " '" + containerId + "', "  
                                  + " '" + referenciaAA + "', "
                                  + " '" + pais_origen + "', "  
                                  + " '" + size_container + "', "  
                                  + " '" + valor_usd + "', "                
                                  + " TO_DATE('" + eta_port_discharge + "', 'mm/dd/yy HH24:MI:SS'), "        
                                  + " '" + agente_aduanal + "', "            
                                  + " '" + pedimento_a1 + "', "              
                                  + " '" + pedimento_r1_1er + "', "          
                                  + " '" + motivo_rectificacion_1er + "', "
                                  + " '" + pedimento_r1_2do + "', "          
                                  + " '" + motivo_rectificacion_2do + "', "  
                                  + " TO_DATE('" + fecha_recepcion_doc + "', 'mm/dd/yy HH24:MI:SS'), "       
                                  + " '" + recinto + "', " 
                                  + " '" + naviera + "', " 
                                  + " '" + buque + "', " 
                                  + " TO_DATE('" + fecha_revalidacion + "', 'mm/dd/yy HH24:MI:SS'), "         
                                  + " TO_DATE('" + fecha_previo_origen + "', 'mm/dd/yy HH24:MI:SS'), "           
                                  + " TO_DATE('" + fecha_previo_destino + "', 'mm/dd/yy HH24:MI:SS'), "          
                                  + " TO_DATE('" + fecha_resultado_previo + "', 'mm/dd/yy HH24:MI:SS'), "        
                                  + " TO_DATE('" + proforma_final + "', 'mm/dd/yy HH24:MI:SS'), "                
                                  + " '" + permiso + "', "                   
                                  + " TO_DATE('" + fecha_envio + "', 'mm/dd/yy HH24:MI:SS'), "                   
                                  + " TO_DATE('" + fecha_recepcion_perm + "', 'mm/dd/yy HH24:MI:SS'), "          
                                  + " TO_DATE('" + fecha_activacion_perm + "', 'mm/dd/yy HH24:MI:SS'), "         
                                  + " TO_DATE('" + fecha_permisos_aut + "', 'mm/dd/yy HH24:MI:SS'), "            
                                  + " '" + co_pref_arancelaria + "', "       
                                  + " '" + aplic_pref_arancelaria + "', "    
                                  + " '" + req_uva + "', "   
                                  + " '" + req_ca + "', "   
                                  + " TO_DATE('" + fecha_recepcion_ca + "', 'mm/dd/yy HH24:MI:SS'), "       
                                  + " '" + num_constancia_ca + "', "   
                                  + " '" + monto_ca + "', "  
                                  + " TO_DATE('" + fecha_doc_completos + "', 'mm/dd/yy HH24:MI:SS'), "           
                                  + " TO_DATE('" + fecha_pago_pedimento + "', 'mm/dd/yy HH24:MI:SS'), "          
                                  + " TO_DATE('" + fecha_solicitud_transporte + "', 'mm/dd/yy HH24:MI:SS'), "    
                                  + " TO_DATE('" + fecha_modulacion + "', 'mm/dd/yy HH24:MI:SS'), "              
                                  + " '" + modalidad + "', "                 
                                  + " '" + resultado_modulacion + "', "      
                                  + " TO_DATE('" + fecha_reconocimiento + "', 'mm/dd/yy HH24:MI:SS'), "          
                                  + " TO_DATE('" + fecha_liberacion + "', 'mm/dd/yy HH24:MI:SS'), "             
                                  + " '" + sello_origen + "', "              
                                  + " '" + sello_final + "', "               
                                  + " TO_DATE('" + fecha_retencion_aut + "', 'mm/dd/yy HH24:MI:SS'), "           
                                  + " TO_DATE('" + fecha_liberacion_aut + "', 'mm/dd/yy HH24:MI:SS'), "          
                                  + " '" + estatus_operacion + "', "         
                                  + " '" + motivo_atraso + "', "             
                                  + " '" + observaciones + "', "; 
            if(idAgenteAduanal.equals("4001")){ //LOGIX
                  insertarCustoms  +=  " TO_DATE('" + llegada_a_nova + "', 'mm/dd/yy HH24:MI:SS'), "    
                                  + " TO_DATE('" + llegada_a_globe_trade_sd + "', 'mm/dd/yy HH24:MI:SS'), "    
                                  + " '" + archivo_m + "', "
                                  + " TO_DATE('" + fecha_archivo_m + "', 'mm/dd/yy HH24:MI:SS'), "    
                                  + " TO_DATE('" + fecha_solicit_manip + "', 'mm/dd/yy HH24:MI:SS'), "    
                                  + " TO_DATE('" + fecha_vencim_manip + "', 'mm/dd/yy HH24:MI:SS'), "    
                                  + " TO_DATE('" + fecha_confirm_clave_pedim + "', 'mm/dd/yy HH24:MI:SS'), "    
                                  + " TO_DATE('" + fecha_recep_increment + "', 'mm/dd/yy HH24:MI:SS'), "    
                                  + " '" + t_e + "', "
                                  + " TO_DATE('" + fecha_vencim_inbound + "', 'mm/dd/yy HH24:MI:SS'), ";     
            }   
            if(idAgenteAduanal.equals("4002")){ //CUSA
                  insertarCustoms  +=  " '" + no_bultos + "', "
                                  + " '" + peso_kg + "', "
                                  + " '" + transferencia + "', "
                                  + " TO_DATE('" + fecha_inicio_etiquetado + "', 'mm/dd/yy HH24:MI:SS'), "    
                                  + " TO_DATE('" + fecha_termino_etiquetado + "', 'mm/dd/yy HH24:MI:SS'), "    
                                  + " '" + hora_termino_etiquetado + "', "
                                  + " '" + proveedor + "', "
                                  + " '" + proveedor_carga + "', "; 
            }        
                  insertarCustoms  +=  " '" + fy + "', "
                                  + " '" + idAgenteAduanal + "', "
                                  + " '" + prioridad + "', "
                                  + " TO_DATE(SYSDATE, 'mm/dd/yy HH24:MI:SS'), "               
                                  + " 1, "                
                                  + " '" + cve + "', "
                                  + " '" + UserId + "') ";  
            boolean oraOut2 = oraDB.execute(historicoCustoms);
        
        System.out.println("Insert Customs: " + insertarCustoms);
        System.out.println("Insert Historico: " + historicoCustoms);
             
            if(oraOut1&&oraOut2){
                salida = "1";
            }else{
                salida = "0";
            }
            
         out.print(salida);
         oraDB.close(); //cerrar conexión
         
         } catch (Exception e) {
                out.println("Error " + e.toString());
            } finally {
                oraDB.close();
            }
            
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
            Logger.getLogger(InsertarCustomsForms.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InsertarCustomsForms.class.getName()).log(Level.SEVERE, null, ex);
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
