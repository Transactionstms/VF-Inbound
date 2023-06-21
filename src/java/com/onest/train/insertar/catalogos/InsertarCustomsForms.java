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
import com.tacts.evidencias.inbound.CrearSemaforoCustoms;
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
        ConsultasQuery fac = new ConsultasQuery();                     //Objeto para consultas 
        SimpleDateFormat formato = new SimpleDateFormat("mm/dd/yyyy"); //Objeto para calcular días de despacho (SEMAFORO)
        CrearSemaforoCustoms obj = new CrearSemaforoCustoms();
        
        try{
                    
            String idAgenteAduanal = request.getParameter("idAgenteAduanal"); 
          //Contadores
            String contCustoms = request.getParameter("numCustoms");
            int numCustoms = Integer.parseInt(contCustoms);
            
          //Casteo Fechas Inbound Gral
            String eta_port_discharge = "";
            String fecha_recepcion_doc = "";
            String fecha_revalidacion = "";
            String fecha_previo_origen = "";
            String fecha_previo_destino = "";
            String fecha_resultado_previo = "";
            String proforma_final = "";
            String fecha_envio = "";
            String fecha_recepcion_perm = "";
            String fecha_activacion_perm = ""; 
            String fecha_permisos_aut = "";
            String fecha_recepcion_ca = "";
            String fecha_doc_completos = "";
            String fecha_pago_pedimento = "";
            String fecha_solicitud_transporte = "";
            String fecha_modulacion = "";
            String fecha_reconocimiento = ""; 
            String fecha_liberacion = ""; 
            String fecha_retencion_aut = "";
            String fecha_liberacion_aut = "";
            
          //Parametros VF Y OTROS 
            String pais_origen = "";
            String size_container = "";
            String valor_usd = "";
            String agente_aduanal = "";
            String pedimento_a1 = "";
            String pedimento_r1_1er = "";
            String motivo_rectificacion_1er = "";
            String pedimento_r1_2do = "";
            String motivo_rectificacion_2do = "";
            String recinto = "";
            String naviera = "";
            String buque = "";
            String permiso = "";
            String co_pref_arancelaria = "";
            String aplic_pref_arancelaria = "";
            String req_uva = "";
            String req_ca = "";
            String num_constancia_ca = "";
            String monto_ca = "";
            String modalidad = "";
            String resultado_modulacion = "";
            String sello_origen = "";
            String sello_final = "";
            String estatus_operacion = "";
            String motivo_atraso = "";
            String observaciones = "";
            String fy = "";
          
          
          //Parametros Logix
            String llegada_a_nova = "";
            String llegada_a_globe_trade_sd = "";
            String archivo_m = "";
            String fecha_archivo_m = "";
            String fecha_solicit_manip = "";
            String fecha_vencim_manip = "";
            String fecha_confirm_clave_pedim = "";
            String fecha_recep_increment = "";
            String t_e = "";
            String fecha_vencim_inbound = ""; 
        
           //Parametros Cusa
            String no_bultos = "";
            String peso_kg = "";
            String transferencia = "";
            String fecha_inicio_etiquetado = "";
            String fecha_termino_etiquetado = "";
            String hora_termino_etiquetado = "";
            String proveedor = "";
            String proveedor_carga = "";   
          
            String insertarCustoms = "";
            String regPrioridad = "";

            //Parametros Semaforo:
            String semaforo = "";
            String eta_port_discharge_old = "";
            int diasTranscurridos = 0;
            
            String salida = "";
            
            
            // create SimpleDateFormat object with source string date format: Tratamiento 1
            SimpleDateFormat sdfSource = new SimpleDateFormat("MM/dd/yyyy"); 
            
            // create SimpleDateFormat object with desired date format:       Tratamiento 2
            SimpleDateFormat sdfDestination = new SimpleDateFormat("MM/dd/yyyy");
              
              for (int i=numCustoms; i<=numCustoms; i++){
                  
                  //Parametros Indicadores
                  String referenciaAA = request.getParameter("referenciaAA[" + i + "]").trim(); 
                  String evento = request.getParameter("evento[" + i + "]").trim();
                  String shipmentId = request.getParameter("shipmentId[" + i + "]").trim();
                  String containerId = request.getParameter("containerId[" + i + "]").trim();
                  String prioridad = request.getParameter("prioridad[" + i + "]").trim(); 
                  String loadTypeFinal = request.getParameter("loadTypeFinal[" + i + "]").trim(); 
                  String plantillaId = request.getParameter("plantillaId[" + i + "]").trim(); 
                  
                if(idAgenteAduanal.equals("4001")||idAgenteAduanal.equals("4002")||idAgenteAduanal.equals("4003")||idAgenteAduanal.equals("4004")||idAgenteAduanal.equals("4005")||idAgenteAduanal.equals("4006")){ //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF 
                  //Parametros Generales
                  pais_origen = request.getParameter("pais_origen[" + i + "]").trim(); 
                  size_container = request.getParameter("size_container[" + i + "]").trim(); 
                  valor_usd = request.getParameter("valor_usd[" + i + "]").trim();                
                 
                  eta_port_discharge = request.getParameter("eta_port_discharge[" + i + "]");                                     //fecha   
                  if(!eta_port_discharge.trim().equals("")){
                      Date date1 = sdfSource.parse(eta_port_discharge);                                                           //parse the string into Date object
                      eta_port_discharge = sdfDestination.format(date1);                                                          //parse the date into another format
                  } 
                  
                  agente_aduanal = request.getParameter("agente_aduanal[" + i + "]").trim();            
                  pedimento_a1 = request.getParameter("pedimento_a1[" + i + "]").trim();             
                  pedimento_r1_1er = request.getParameter("pedimento_r1_1er[" + i + "]").trim();          
                  motivo_rectificacion_1er = request.getParameter("motivo_rectificacion_1er[" + i + "]").trim();  
                  pedimento_r1_2do = request.getParameter("pedimento_r1_2do[" + i + "]").trim();          
                  motivo_rectificacion_2do = request.getParameter("motivo_rectificacion_2do[" + i + "]").trim();  
                  
                  fecha_recepcion_doc = request.getParameter("fecha_recepcion_doc[" + i + "]");               //fecha  
                  if(!fecha_recepcion_doc.trim().equals("")){
                      Date date2 = sdfSource.parse(fecha_recepcion_doc);                                                           //parse the string into Date object
                      fecha_recepcion_doc = sdfDestination.format(date2);                                                          //parse the date into another format
                  }
                  
                  recinto = request.getParameter("recinto[" + i + "]").trim();
                  naviera = request.getParameter("naviera[" + i + "]").trim();
                  buque = request.getParameter("buque[" + i + "]").trim();
                  
                  fecha_revalidacion = request.getParameter("fecha_revalidacion[" + i + "]");                 //fecha 
                  if(!fecha_revalidacion.trim().equals("")){
                      Date date3 = sdfSource.parse(fecha_revalidacion);                                                            //parse the string into Date object
                      fecha_revalidacion = sdfDestination.format(date3);                                                           //parse the date into another format
                  }
                  
                  fecha_previo_origen = request.getParameter("fecha_previo_origen[" + i + "]");               //fecha 
                  if(!fecha_previo_origen.trim().equals("")){
                      Date date4 = sdfSource.parse(fecha_previo_origen);                                                           //parse the string into Date object
                      fecha_previo_origen = sdfDestination.format(date4);                                                          //parse the date into another format
                  }
                  
                  fecha_previo_destino = request.getParameter("fecha_previo_destino[" + i + "]");             //fecha 
                  if(!fecha_previo_destino.trim().equals("")){
                      Date date5 = sdfSource.parse(fecha_previo_destino);                                                          //parse the string into Date object
                      fecha_previo_destino = sdfDestination.format(date5);                                                         //parse the date into another format
                  }
                  
                  fecha_resultado_previo = request.getParameter("fecha_resultado_previo[" + i + "]");         //fecha 
                  if(!fecha_resultado_previo.trim().equals("")){
                      Date date6 = sdfSource.parse(fecha_resultado_previo);                                                        //parse the string into Date object
                      fecha_resultado_previo = sdfDestination.format(date6);                                                       //parse the date into another format
                  }
                  
                  proforma_final = request.getParameter("proforma_final[" + i + "]");                  //fecha  
                  if(!proforma_final.trim().equals("")){
                      Date date20 = sdfSource.parse(proforma_final);                                                               //parse the string into Date object
                      proforma_final = sdfDestination.format(date20);                                                              //parse the date into another format
                  }
                  
                  permiso = request.getParameter("permiso[" + i + "]").trim();                   
                  
                  fecha_envio = request.getParameter("fecha_envio[" + i + "]");                               //fecha  
                  if(!fecha_envio.trim().equals("")){
                      Date date7 = sdfSource.parse(fecha_envio);                                                                   //parse the string into Date object
                      fecha_envio = sdfDestination.format(date7);                                                                  //parse the date into another format
                  }
                  
                  fecha_recepcion_perm = request.getParameter("fecha_recepcion_perm[" + i + "]");             //fecha 
                  if(!fecha_recepcion_perm.trim().equals("")){
                      Date date8 = sdfSource.parse(fecha_recepcion_perm);                                                          //parse the string into Date object
                      fecha_recepcion_perm = sdfDestination.format(date8);                                                         //parse the date into another format
                  }
                  
                  fecha_activacion_perm = request.getParameter("fecha_activacion_perm[" + i + "]");           //fecha 
                  if(!fecha_activacion_perm.trim().equals("")){
                      Date date9 = sdfSource.parse(fecha_activacion_perm);                                                         //parse the string into Date object
                      fecha_activacion_perm = sdfDestination.format(date9);                                                        //parse the date into another format
                  }
                  
                  fecha_permisos_aut = request.getParameter("fecha_permisos_aut[" + i + "]");                 //fecha 
                  if(!fecha_permisos_aut.trim().equals("")){
                      Date date10 = sdfSource.parse(fecha_permisos_aut);                                                           //parse the string into Date object
                      fecha_permisos_aut = sdfDestination.format(date10);                                                          //parse the date into another format
                  }
                  
                  co_pref_arancelaria = request.getParameter("co_pref_arancelaria[" + i + "]").trim();       
                  aplic_pref_arancelaria = request.getParameter("aplic_pref_arancelaria[" + i + "]").trim();    
                  req_uva = request.getParameter("req_uva[" + i + "]").trim();   
                  req_ca = request.getParameter("req_ca[" + i + "]").trim();   
                  
                  fecha_recepcion_ca = request.getParameter("fecha_recepcion_ca[" + i + "]");                  //fecha 
                  if(!fecha_recepcion_ca.trim().equals("")){
                      Date date11 = sdfSource.parse(fecha_recepcion_ca);                                                            //parse the string into Date object
                      fecha_recepcion_ca = sdfDestination.format(date11);                                                           //parse the date into another format
                  }
                  
                  num_constancia_ca = request.getParameter("num_constancia_ca[" + i + "]").trim();   
                  monto_ca = request.getParameter("monto_ca[" + i + "]").trim();   
                  
                  fecha_doc_completos = request.getParameter("fecha_doc_completos[" + i + "]");                 //fecha 
                  if(!fecha_doc_completos.trim().equals("")){
                      Date date12 = sdfSource.parse(fecha_doc_completos);                                                            //parse the string into Date object
                      fecha_doc_completos = sdfDestination.format(date12);                                                           //parse the date into another format
                  }
                  
                  fecha_pago_pedimento = request.getParameter("fecha_pago_pedimento[" + i + "]");               //fecha 
                  if(!fecha_pago_pedimento.trim().equals("")){
                      Date date13 = sdfSource.parse(fecha_pago_pedimento);                                                           //parse the string into Date object
                      fecha_pago_pedimento = sdfDestination.format(date13);                                                          //parse the date into another format
                  }
                  
                  fecha_solicitud_transporte = request.getParameter("fecha_solicitud_transporte[" + i + "]");   //fecha 
                  if(!fecha_solicitud_transporte.trim().equals("")){
                      Date date14 = sdfSource.parse(fecha_solicitud_transporte);                                                     //parse the string into Date object
                      fecha_solicitud_transporte = sdfDestination.format(date14);                                                    //parse the date into another format
                  }
                  
                  fecha_modulacion = request.getParameter("fecha_modulacion[" + i + "]");                       //fecha 
                  if(!fecha_modulacion.trim().equals("")){
                      Date date15 = sdfSource.parse(fecha_modulacion);                                                               //parse the string into Date object
                      fecha_modulacion = sdfDestination.format(date15);                                                              //parse the date into another format
                  }
                  
                  modalidad = request.getParameter("modalidad[" + i + "]").trim();                 
                  resultado_modulacion = request.getParameter("resultado_modulacion[" + i + "]").trim();      
                  
                  fecha_reconocimiento = request.getParameter("fecha_reconocimiento[" + i + "]");               //fecha 
                  if(!fecha_reconocimiento.trim().equals("")){
                      Date date16 = sdfSource.parse(fecha_reconocimiento);                                                           //parse the string into Date object
                      fecha_reconocimiento = sdfDestination.format(date16);                                                          //parse the date into another format
                  }
                  
                  fecha_liberacion = request.getParameter("fecha_liberacion[" + i + "]");                       //fecha 
                  if(!fecha_liberacion.trim().equals("")){                  
                      Date date17 = sdfSource.parse(fecha_liberacion);                                                               //parse the string into Date object
                      fecha_liberacion = sdfDestination.format(date17);                                                              //parse the date into another format
                  }
                  
                  sello_origen = request.getParameter("sello_origen[" + i + "]").trim();              
                  sello_final = request.getParameter("sello_final[" + i + "]").trim();               
                  
                  fecha_retencion_aut = request.getParameter("fecha_retencion_aut[" + i + "]");                 //fecha 
                  if(!fecha_retencion_aut.trim().equals("")){
                      Date date18 = sdfSource.parse(fecha_retencion_aut);                                                            //parse the string into Date object
                      fecha_retencion_aut = sdfDestination.format(date18);                                                           //parse the date into another format
                  }
                  
                  fecha_liberacion_aut = request.getParameter("fecha_liberacion_aut[" + i + "]");               //fecha 
                  if(!fecha_liberacion_aut.trim().equals("")){                  
                      Date date19 = sdfSource.parse(fecha_liberacion_aut);                                                           //parse the string into Date object
                      fecha_liberacion_aut = sdfDestination.format(date19);                                                          //parse the date into another format
                  }
                  
                  estatus_operacion = request.getParameter("estatus_operacion[" + i + "]").trim();         
                  motivo_atraso = request.getParameter("motivo_atraso[" + i + "]").trim();             
                  observaciones = request.getParameter("observaciones[" + i + "]").trim(); 
                  fy = request.getParameter("fy[" + i + "]").trim();
            }
              
            if(idAgenteAduanal.equals("4001")||idAgenteAduanal.equals("4006")){ //LOGIX Y VF
               
                llegada_a_nova = request.getParameter("llegada_a_nova[" + i + "]");                                 //fecha 
                if(!llegada_a_nova.trim().equals("")){                  
                  Date date21 = sdfSource.parse(llegada_a_nova);                                                           //parse the string into Date object
                  llegada_a_nova = sdfDestination.format(date21);                                                          //parse the date into another format
                }
                
                llegada_a_globe_trade_sd = request.getParameter("llegada_a_globe_trade_sd[" + i + "]");             //fecha 
                if(!llegada_a_globe_trade_sd.trim().equals("")){                  
                  Date date22 = sdfSource.parse(llegada_a_globe_trade_sd);                                                           //parse the string into Date object
                  llegada_a_globe_trade_sd = sdfDestination.format(date22);                                                          //parse the date into another format
                }
                                
                archivo_m = request.getParameter("archivo_m[" + i + "]").trim(); 
                
                fecha_archivo_m = request.getParameter("fecha_archivo_m[" + i + "]");                               //fecha  
                if(!fecha_archivo_m.trim().equals("")){                  
                  Date date23 = sdfSource.parse(fecha_archivo_m);                                                           //parse the string into Date object
                  fecha_archivo_m = sdfDestination.format(date23);                                                          //parse the date into another format
                }
                                
                fecha_solicit_manip = request.getParameter("fecha_solicit_manip[" + i + "]");                       //fecha 
                if(!fecha_solicit_manip.trim().equals("")){                  
                  Date date24 = sdfSource.parse(fecha_solicit_manip);                                                           //parse the string into Date object
                  fecha_solicit_manip = sdfDestination.format(date24);                                                          //parse the date into another format
                }
                                
                fecha_vencim_manip = request.getParameter("fecha_vencim_manip[" + i + "]");                         //fecha 
                if(!fecha_vencim_manip.trim().equals("")){                  
                  Date date25 = sdfSource.parse(fecha_vencim_manip);                                                           //parse the string into Date object
                  fecha_vencim_manip = sdfDestination.format(date25);                                                          //parse the date into another format
                }
                                
                fecha_confirm_clave_pedim = request.getParameter("fecha_confirm_clave_pedim[" + i + "]");           //fecha 
                if(!fecha_confirm_clave_pedim.trim().equals("")){                  
                  Date date26 = sdfSource.parse(fecha_confirm_clave_pedim);                                                           //parse the string into Date object
                  fecha_confirm_clave_pedim = sdfDestination.format(date26);                                                          //parse the date into another format
                }
                                
                fecha_recep_increment = request.getParameter("fecha_recep_increment[" + i + "]");                   //fecha 
                if(!fecha_recep_increment.trim().equals("")){                  
                  Date date27 = sdfSource.parse(fecha_recep_increment);                                                           //parse the string into Date object
                  fecha_recep_increment = sdfDestination.format(date27);                                                          //parse the date into another format
                }
                                
                t_e = request.getParameter("t_e[" + i + "]").trim(); 
                
                fecha_vencim_inbound = request.getParameter("fecha_vencim_inbound[" + i + "]");                     //fecha 
                if(!fecha_vencim_inbound.trim().equals("")){                  
                  Date date28 = sdfSource.parse(fecha_vencim_inbound);                                                           //parse the string into Date object
                  fecha_vencim_inbound = sdfDestination.format(date28);                                                          //parse the date into another format
                }
                                
            }
            
            if(idAgenteAduanal.equals("4002")||idAgenteAduanal.equals("4006")){ //CUSA Y VF
                 
                no_bultos = request.getParameter("no_bultos[" + i + "]").trim(); 
                peso_kg = request.getParameter("peso_kg[" + i + "]").trim(); 
                transferencia = request.getParameter("transferencia[" + i + "]").trim();
                
                fecha_inicio_etiquetado= request.getParameter("fecha_inicio_etiquetado[" + i + "]");                //fecha 
                if(!fecha_inicio_etiquetado.trim().equals("")){                  
                  Date date29 = sdfSource.parse(fecha_inicio_etiquetado);                                                           //parse the string into Date object
                  fecha_inicio_etiquetado = sdfDestination.format(date29);                                                          //parse the date into another format
                }
                                
                fecha_termino_etiquetado = request.getParameter("fecha_termino_etiquetado[" + i + "]");             //fecha 
                if(!fecha_termino_etiquetado.trim().equals("")){                  
                  Date date30 = sdfSource.parse(fecha_termino_etiquetado);                                                           //parse the string into Date object
                  fecha_termino_etiquetado = sdfDestination.format(date30);                                                          //parse the date into another format
                }
                                
                hora_termino_etiquetado = request.getParameter("hora_termino_etiquetado[" + i + "]").trim(); 
                proveedor = request.getParameter("proveedor[" + i + "]").trim(); 
                proveedor_carga = request.getParameter("proveedor_carga[" + i + "]").trim(); 
            
            }
            
            //Consultar existencia de Shipmentd para el tipo de registro:
            String valExist = "SELECT DISTINCT CUSTREG_ID FROM TRA_INB_CUSTOMS WHERE SHIPMENT_ID = '" + shipmentId + "' AND CBDIV_ID = '" + cve + "'";
            boolean oraOut = oraDB.execute(valExist);
            
            if(oraOut){
                
                oraDB.connect(dbData.getUser(), dbData.getPassword()); /* CONEXIÓN */

                                 insertarCustoms = " UPDATE TRA_INB_CUSTOMS SET "                              
                                                 + " REFERENCIA_AA = '" + referenciaAA + "', ";
                                
                if(idAgenteAduanal.equals("4001")||idAgenteAduanal.equals("4002")||idAgenteAduanal.equals("4003")||idAgenteAduanal.equals("4004")||idAgenteAduanal.equals("4005")||idAgenteAduanal.equals("4006")){ //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF                       
                                insertarCustoms += " PAIS_ORIGEN = '" + pais_origen + "', "   
                                                 + " SIZE_CONTAINER = '" + size_container + "', "   
                                                 + " VALOR_USD = '" + valor_usd + "', ";       
                        if(!eta_port_discharge.trim().equals("")){
                                insertarCustoms += " ETA_PORT_OF_DISCHARGE = TO_DATE('" + eta_port_discharge + "', 'MM/DD/YYYY'), ";
                        }         
                                insertarCustoms += " AGENTE_ADUANAL = '" + agente_aduanal + "', "             
                                                 + " PEDIMENTO_A1 = '" + pedimento_a1 + "', "               
                                                 + " PEDIMENTO_R1 = '" + pedimento_r1_1er + "', "           
                                                 + " MOTIVO_RECTIFICACION_1 = '" + motivo_rectificacion_1er + "', "   
                                                 + " PEDIMENTO_R1_2DO = '" + pedimento_r1_2do + "', "           
                                                 + " MOTIVO_RECTIFICACION_2 = '" + motivo_rectificacion_2do + "', ";   
                        if(!fecha_recepcion_doc.trim().equals("")){        
                                insertarCustoms += " FECHA_RECEPCION_DOCUMENTOS = TO_DATE('" + fecha_recepcion_doc + "', 'MM/DD/YYYY'), ";
                        }        
                                insertarCustoms += " RECINTO = '" + recinto + "', "     
                                                 + " NAVIERA_FORWARDER = '" + naviera + "', "     
                                                 + " BUQUE = '" + buque + "', "; 
                        if(!fecha_revalidacion.trim().equals("")){        
                                insertarCustoms += " FECHA_REVALID_LIBE_BL = TO_DATE('" + fecha_revalidacion + "', 'MM/DD/YYYY'), "; 
                        }  
                        if(!fecha_previo_origen.trim().equals("")){
                                insertarCustoms += " FECHA_PREVIO_ORIGEN = TO_DATE('" + fecha_previo_origen + "', 'MM/DD/YYYY'), "; 
                        } 
                        if(!fecha_previo_destino.trim().equals("")){
                                insertarCustoms += " FECHA_PREVIO_DESTINO = TO_DATE('" + fecha_previo_destino + "', 'MM/DD/YYYY'), ";
                        }        
                        if(!fecha_resultado_previo.trim().equals("")){
                                insertarCustoms += " FECHA_RESULTADO_PREVIO = TO_DATE('" + fecha_resultado_previo + "', 'MM/DD/YYYY'), ";    
                        }       
                        if(!proforma_final.trim().equals("")){
                                insertarCustoms += " PROFORMA_FINAL = TO_DATE('" + proforma_final + "', 'MM/DD/YYYY'), ";  
                        }        
                                insertarCustoms += " REQUIERE_PERMISO = '" + permiso + "', ";
                        if(!fecha_envio.trim().equals("")){        
                                insertarCustoms += " FECHA_ENVIO_FICHAS_NOTAS = TO_DATE('" + fecha_envio + "', 'MM/DD/YYYY'), "; 
                        }        
                        if(!fecha_recepcion_perm.trim().equals("")){
                                insertarCustoms += " FEC_RECEPCION_PERMISOS_TRAMIT = TO_DATE('" + fecha_recepcion_perm + "', 'MM/DD/YYYY'), ";  
                        }
                        if(!fecha_activacion_perm.trim().equals("")){
                                insertarCustoms += " FEC_ACT_PERMISOS = TO_DATE('" + fecha_activacion_perm + "', 'MM/DD/YYYY'), ";
                        }    
                        if(!fecha_permisos_aut.trim().equals("")){
                                insertarCustoms += " FEC_PERM_AUT = TO_DATE('" + fecha_permisos_aut + "', 'MM/DD/YYYY'), "; 
                        }        
                                insertarCustoms += " CO_APLIC_PREF_ARANCELARIA = '" + co_pref_arancelaria + "', "        
                                                 + " APLIC_PREF_ARANCELARIA_CO = '" + aplic_pref_arancelaria + "', "     
                                                 + " REQUIERE_UVA = '" + req_uva + "', "     
                                                 + " REQUIERE_CA = '" + req_ca + "', ";   
                        if(!fecha_recepcion_ca.trim().equals("")){        
                                insertarCustoms += " FECHA_RECEPCION_CA = TO_DATE('" + fecha_recepcion_ca + "', 'MM/DD/YYYY'), ";  
                        }        
                                insertarCustoms += " NÚMERO_CONSTANCIA_CA = '" + num_constancia_ca + "', "   
                                                 + " MONTO_CA = '" + monto_ca + "', ";   
                        if(!fecha_doc_completos.trim().equals("")){        
                                insertarCustoms += " FECHA_DOCUMENTOS_COMPLETOS = TO_DATE('" + fecha_doc_completos + "', 'MM/DD/YYYY'), "; 
                        }  
                        if(!fecha_pago_pedimento.trim().equals("")){
                                insertarCustoms += " FECHA_PAGO_PEDIMENTO = TO_DATE('" + fecha_pago_pedimento + "', 'MM/DD/YYYY'), "; 
                        }
                        if(!fecha_solicitud_transporte.trim().equals("")){
                                insertarCustoms += " FECHA_SOLICITUD_TRANSPORTE = TO_DATE('" + fecha_solicitud_transporte + "', 'MM/DD/YYYY'), "; 
                        }
                        if(!fecha_modulacion.trim().equals("")){
                                insertarCustoms += " FECHA_MODULACION = TO_DATE('" + fecha_modulacion + "', 'MM/DD/YYYY'), ";
                        }        
                                insertarCustoms += " MODALIDAD_CAMION_TREN = '" + modalidad + "', "                  
                                                 + " RESULT_MODULACION_VERDE_ROJO = '" + resultado_modulacion + "', ";   
                        if(!fecha_reconocimiento.trim().equals("")){        
                                insertarCustoms += " FECHA_RECONOCIMIENTO = TO_DATE('" + fecha_reconocimiento + "', 'MM/DD/YYYY'), ";  
                        } 
                        if(!fecha_liberacion.trim().equals("")){
                                insertarCustoms += " FECHA_LIBERACION = TO_DATE('" + fecha_liberacion + "', 'MM/DD/YYYY'), ";    
                        }                
                                insertarCustoms += " SELLO_ORIGEN = '" + sello_origen + "', "               
                                                 + " SELLO_FINAL = '" + sello_final + "', ";    
                        if(!fecha_retencion_aut.trim().equals("")){        
                                insertarCustoms += " FECHA_RETENCION_AUTORIDAD = TO_DATE('" + fecha_retencion_aut + "', 'MM/DD/YYYY'), ";
                        }   
                        if(!fecha_liberacion_aut.trim().equals("")){
                                insertarCustoms += " FECHA_LIB_POR_RET_AUT = TO_DATE('" + fecha_liberacion_aut + "', 'MM/DD/YYYY'), ";   
                        }        
                                insertarCustoms += " ESTATUS_OPERACION = '" + estatus_operacion + "', "          
                                                 + " MOTIVO_ATRASO = '" + motivo_atraso + "', "              
                                                 + " OBSERVACIONES = '" + observaciones + "', ";   
                }
            
                if(idAgenteAduanal.equals("4001")||idAgenteAduanal.equals("4006")){ //LOGIX Y VF
                    
                        if(!llegada_a_nova.trim().equals("")){        
                                insertarCustoms += " LLEGADA_A_NOVA = TO_DATE('" + llegada_a_nova + "', 'MM/DD/YYYY'), "; 
                        }        
                        if(!llegada_a_globe_trade_sd.trim().equals("")){        
                                insertarCustoms += " LLEGADA_A_GLOBE_TRADE_SD = TO_DATE('" + llegada_a_globe_trade_sd + "', 'MM/DD/YYYY'), "; 
                        }       
                                insertarCustoms += " ARCHIVO_M = '" + archivo_m + "', "; 

                        if(!fecha_archivo_m.trim().equals("")){        
                                insertarCustoms += " FECHA_ARCHIVO_M = TO_DATE('" + fecha_archivo_m + "', 'MM/DD/YYYY'), ";  
                        }        
                        if(!fecha_solicit_manip.trim().equals("")){        
                                insertarCustoms += " FECHA_SOLICIT_MANIP = TO_DATE('" + fecha_solicit_manip + "', 'MM/DD/YYYY'), ";  
                        }        
                        if(!fecha_vencim_manip.trim().equals("")){        
                                insertarCustoms += " FECHA_VENCIM_MANIP = TO_DATE('" + fecha_vencim_manip + "', 'MM/DD/YYYY'), ";  
                        }        
                        if(!fecha_confirm_clave_pedim.trim().equals("")){        
                                insertarCustoms += " FECHA_CONFIRM_CLAVE_PEDIM = TO_DATE('" + fecha_confirm_clave_pedim + "', 'MM/DD/YYYY'), ";  
                        }        
                        if(!fecha_recep_increment.trim().equals("")){        
                                insertarCustoms += " FECHA_RECEP_INCREMENT = TO_DATE('" + fecha_recep_increment + "', 'MM/DD/YYYY'), ";   
                        }        
                                insertarCustoms += " T_E = '" + t_e + "', "; 
                        if(!fecha_vencim_inbound.trim().equals("")){        
                                insertarCustoms += " FECHA_VENCIM_INBOUND = TO_DATE('" + fecha_vencim_inbound + "', 'MM/DD/YYYY'), ";
                        }        
                }
                
                if(idAgenteAduanal.equals("4002")||idAgenteAduanal.equals("4006")){  //CUSA Y VF
                    
                                insertarCustoms += " NO_BULTOS = '" + no_bultos + "', " 
                                                 + " PESO_KG = '" + peso_kg + "', "  
                                                 + " TRANSFERENCIA = '" + transferencia + "', ";  
                        if(!fecha_inicio_etiquetado.trim().equals("")){        

                                insertarCustoms += " FECHA_INICIO_ETIQUETADO = TO_DATE('" + fecha_inicio_etiquetado + "', 'MM/DD/YYYY'), ";
                        }        
                        if(!fecha_termino_etiquetado.trim().equals("")){        
                                insertarCustoms += " FECHA_TERMINO_ETIQUETADO = TO_DATE('" + fecha_termino_etiquetado + "', 'MM/DD/YYYY'), ";
                        }        
                                insertarCustoms += " HORA_TERMINO_ETIQUETADO = '" + hora_termino_etiquetado + "', "  
                                                 + " PROVEEDOR = '" + proveedor + "', "  
                                                 + " PROVEEDOR_CARGA = '" + proveedor_carga + "', "; 
                }
                                insertarCustoms += " FY = '" + fy + "', " 
                                                 + " AGENTE_ADUANAL_ID = '" + idAgenteAduanal + "', " 
                                                 + " PRIORIDAD = '" + prioridad + "', "         
                                                 + " CBDIV_ID = '" + cve + "', " 
                                                 + " USER_NID = '" + UserId + "' "
                                                 + " WHERE SHIPMENT_ID = '" + shipmentId + "' ";
                    
            }else{   
                
                oraDB.connect(dbData.getUser(), dbData.getPassword()); /* CONEXIÓN */
                
                                  insertarCustoms = " INSERT INTO TRA_INB_CUSTOMS "
                                                  + " (CUSTREG_ID, "
                                                  + " NUMERO_DE_EVENTO, "  
                                                  + " SHIPMENT_ID, "  
                                                  + " CONTAINER_ID, "  
                                                  + " REFERENCIA_AA, ";
                                
                if(idAgenteAduanal.equals("4001")||idAgenteAduanal.equals("4002")||idAgenteAduanal.equals("4003")||idAgenteAduanal.equals("4004")||idAgenteAduanal.equals("4005")||idAgenteAduanal.equals("4006")){ //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF             
                                 insertarCustoms += " PAIS_ORIGEN, "  
                                                  + " SIZE_CONTAINER, "  
                                                  + " VALOR_USD, ";       
                        if(!eta_port_discharge.trim().equals("")){         
                                 insertarCustoms += " ETA_PORT_OF_DISCHARGE, ";
                        }         
                                 insertarCustoms += " AGENTE_ADUANAL, "            
                                                  + " PEDIMENTO_A1, "              
                                                  + " PEDIMENTO_R1, "          
                                                  + " MOTIVO_RECTIFICACION_1, "  
                                                  + " PEDIMENTO_R1_2DO, "          
                                                  + " MOTIVO_RECTIFICACION_2, ";  
                        if(!fecha_recepcion_doc.trim().equals("")){         
                                 insertarCustoms += " FECHA_RECEPCION_DOCUMENTOS, ";   
                        }         
                                 insertarCustoms += " RECINTO, "    
                                                  + " NAVIERA_FORWARDER, "    
                                                  + " BUQUE, ";  
                        if(!fecha_revalidacion.trim().equals("")){         
                                 insertarCustoms += " FECHA_REVALID_LIBE_BL, ";
                        }
                        if(!fecha_previo_origen.trim().equals("")){
                                 insertarCustoms += " FECHA_PREVIO_ORIGEN, "; 
                        } 
                        if(!fecha_previo_destino.trim().equals("")){
                                 insertarCustoms += " FECHA_PREVIO_DESTINO, ";  
                        }     
                        if(!fecha_resultado_previo.trim().equals("")){
                                 insertarCustoms += " FECHA_RESULTADO_PREVIO, "; 
                        }
                        if(!proforma_final.trim().equals("")){
                                 insertarCustoms += " PROFORMA_FINAL, "; 
                        }        
                                 insertarCustoms += " REQUIERE_PERMISO, ";     
                        if(!fecha_envio.trim().equals("")){         
                                 insertarCustoms += " FECHA_ENVIO_FICHAS_NOTAS, ";   
                        }
                        if(!fecha_recepcion_perm.trim().equals("")){
                                 insertarCustoms += " FEC_RECEPCION_PERMISOS_TRAMIT, ";     
                        }         
                        if(!fecha_activacion_perm.trim().equals("")){
                                 insertarCustoms += " FEC_ACT_PERMISOS, ";     
                        }     
                        if(!fecha_permisos_aut.trim().equals("")){
                                 insertarCustoms += " FEC_PERM_AUT, ";  
                        }         
                                 insertarCustoms += " CO_APLIC_PREF_ARANCELARIA, "       
                                                  + " APLIC_PREF_ARANCELARIA_CO, "    
                                                  + " REQUIERE_UVA, "    
                                                  + " REQUIERE_CA, ";  
                        if(!fecha_recepcion_ca.trim().equals("")){         
                                 insertarCustoms += " FECHA_RECEPCION_CA, "; 
                        }         
                                 insertarCustoms += " NÚMERO_CONSTANCIA_CA, "  
                                                  + " MONTO_CA, ";  
                        if(!fecha_doc_completos.trim().equals("")){         
                                 insertarCustoms += " FECHA_DOCUMENTOS_COMPLETOS, ";     
                        }
                        if(!fecha_pago_pedimento.trim().equals("")){
                                 insertarCustoms += " FECHA_PAGO_PEDIMENTO, ";     
                        }     
                        if(!fecha_solicitud_transporte.trim().equals("")){
                                 insertarCustoms += " FECHA_SOLICITUD_TRANSPORTE, ";
                        }         
                        if(!fecha_modulacion.trim().equals("")){
                                 insertarCustoms += " FECHA_MODULACION, "; 
                        }         
                                 insertarCustoms += " MODALIDAD_CAMION_TREN, "                 
                                                  + " RESULT_MODULACION_VERDE_ROJO, "; 
                        if(!fecha_reconocimiento.trim().equals("")){         
                                 insertarCustoms += " FECHA_RECONOCIMIENTO, "; 
                        }     
                        if(!fecha_liberacion.trim().equals("")){
                                 insertarCustoms += " FECHA_LIBERACION, ";  
                        }                 
                                 insertarCustoms += " SELLO_ORIGEN, "              
                                                  + " SELLO_FINAL, "; 
                        if(!fecha_retencion_aut.trim().equals("")){         
                                 insertarCustoms += " FECHA_RETENCION_AUTORIDAD, ";    
                        }         
                        if(!fecha_liberacion_aut.trim().equals("")){
                                 insertarCustoms += " FECHA_LIB_POR_RET_AUT, ";  
                        }         
                                 insertarCustoms += " ESTATUS_OPERACION, "         
                                                  + " MOTIVO_ATRASO, "             
                                                  + " OBSERVACIONES, ";        
               }
            
               if(idAgenteAduanal.equals("4001")||idAgenteAduanal.equals("4006")){ //LOGIX Y VF
                        if(!llegada_a_nova.trim().equals("")){        
                                insertarCustoms += " LLEGADA_A_NOVA, ";
                        }   
                        if(!llegada_a_globe_trade_sd.trim().equals("")){
                                insertarCustoms += " LLEGADA_A_GLOBE_TRADE_SD, "; 
                        }       
                                insertarCustoms += " ARCHIVO_M, "; 
                        if(!fecha_archivo_m.trim().equals("")){       
                                insertarCustoms += " FECHA_ARCHIVO_M, "; 
                        }
                        if(!fecha_solicit_manip.trim().equals("")){
                                insertarCustoms += " FECHA_SOLICIT_MANIP, "; 
                        }    
                        if(!fecha_vencim_manip.trim().equals("")){
                                insertarCustoms += " FECHA_VENCIM_MANIP, "; 
                        }
                        if(!fecha_confirm_clave_pedim.trim().equals("")){
                                insertarCustoms += " FECHA_CONFIRM_CLAVE_PEDIM, "; 
                        }   
                        if(!fecha_recep_increment.trim().equals("")){
                                insertarCustoms += " FECHA_RECEP_INCREMENT, ";
                        }        
                                insertarCustoms += " T_E, "; 
                        if(!fecha_vencim_inbound.trim().equals("")){        
                                insertarCustoms += " FECHA_VENCIM_INBOUND, ";
                        }        
                }
                
                if(idAgenteAduanal.equals("4002")||idAgenteAduanal.equals("4006")){  //CUSA Y VF
                                insertarCustoms += " NO_BULTOS, "
                                                 + " PESO_KG, " 
                                                 + " TRANSFERENCIA, "; 
                        if(!fecha_inicio_etiquetado.trim().equals("")){        
                                insertarCustoms += " FECHA_INICIO_ETIQUETADO, "; 
                        }   
                        if(!fecha_termino_etiquetado.trim().equals("")){
                                insertarCustoms += " FECHA_TERMINO_ETIQUETADO, "; 
                        }        
                                insertarCustoms += " HORA_TERMINO_ETIQUETADO, " 
                                                 + " PROVEEDOR, " 
                                                 + " PROVEEDOR_CARGA, ";
                } 
                                 insertarCustoms += " FY, "
                                                  + " AGENTE_ADUANAL_ID, "
                                                  + " PRIORIDAD, "
                                                  + " FECHA_REGISTRO, "        
                                                  + " CBDIV_ID, "
                                                  + " USER_NID) "
                                                  + " VALUES " 
                                                  + " (NULL, "
                                                  + " '" + evento + "', "  
                                                  + " '" + shipmentId + "', "  
                                                  + " '" + containerId + "', "  
                                                  + " '" + referenciaAA + "', ";
                                 
                if(idAgenteAduanal.equals("4001")||idAgenteAduanal.equals("4002")||idAgenteAduanal.equals("4003")||idAgenteAduanal.equals("4004")||idAgenteAduanal.equals("4005")||idAgenteAduanal.equals("4006")){ //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF                            
                                                 
                                 insertarCustoms += " '" + pais_origen + "', "  
                                                  + " '" + size_container + "', "  
                                                  + " '" + valor_usd + "', ";  
                        if(!eta_port_discharge.trim().equals("")){         
                                 insertarCustoms += " TO_DATE('" + eta_port_discharge + "', 'MM/DD/YYYY'), ";     
                        }         
                                 insertarCustoms += " '" + agente_aduanal + "', "            
                                                  + " '" + pedimento_a1 + "', "              
                                                  + " '" + pedimento_r1_1er + "', "          
                                                  + " '" + motivo_rectificacion_1er + "', "
                                                  + " '" + pedimento_r1_2do + "', "          
                                                  + " '" + motivo_rectificacion_2do + "', ";  
                        if(!fecha_recepcion_doc.trim().equals("")){         
                                 insertarCustoms += " TO_DATE('" + fecha_recepcion_doc + "', 'MM/DD/YYYY'), ";     
                        }         
                                 insertarCustoms += " '" + recinto + "', " 
                                                  + " '" + naviera + "', " 
                                                  + " '" + buque + "', "; 
                        if(!fecha_revalidacion.trim().equals("")){         
                                 insertarCustoms += " TO_DATE('" + fecha_revalidacion + "', 'MM/DD/YYYY'), ";    
                        }     
                        if(!fecha_previo_origen.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + fecha_previo_origen + "', 'MM/DD/YYYY'), ";   
                        }  
                        if(!fecha_previo_destino.trim().equals("")){
                                insertarCustoms += " TO_DATE('" + fecha_previo_destino + "', 'MM/DD/YYYY'), ";    
                        } 
                        if(!fecha_resultado_previo.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + fecha_resultado_previo + "', 'MM/DD/YYYY'), ";
                        }      
                        if(!proforma_final.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + proforma_final + "', 'MM/DD/YYYY'), "; 
                        }         
                                 insertarCustoms += " '" + permiso + "', ";   
                        if(!fecha_envio.trim().equals("")){         
                                 insertarCustoms += " TO_DATE('" + fecha_envio + "', 'MM/DD/YYYY'), "; 
                        }         
                        if(!fecha_recepcion_perm.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + fecha_recepcion_perm + "', 'MM/DD/YYYY'), "; 
                        }    
                        if(!fecha_activacion_perm.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + fecha_activacion_perm + "', 'MM/DD/YYYY'), ";
                        }   
                        if(!fecha_permisos_aut.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + fecha_permisos_aut + "', 'MM/DD/YYYY'), ";   
                        }         
                                 insertarCustoms += " '" + co_pref_arancelaria + "', "       
                                                  + " '" + aplic_pref_arancelaria + "', "    
                                                  + " '" + req_uva + "', "   
                                                  + " '" + req_ca + "', ";   
                        if(!fecha_recepcion_ca.trim().equals("")){         
                                 insertarCustoms += " TO_DATE('" + fecha_recepcion_ca + "', 'MM/DD/YYYY'), ";   
                        }         
                                 insertarCustoms += " '" + num_constancia_ca + "', "   
                                                  + " '" + monto_ca + "', ";  
                        if(!fecha_doc_completos.trim().equals("")){         
                                 insertarCustoms += " TO_DATE('" + fecha_doc_completos + "', 'MM/DD/YYYY'), ";    
                        }         
                        if(!fecha_pago_pedimento.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + fecha_pago_pedimento + "', 'MM/DD/YYYY'), ";
                        }         
                        if(!fecha_solicitud_transporte.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + fecha_solicitud_transporte + "', 'MM/DD/YYYY'), ";  
                        }
                        if(!fecha_modulacion.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + fecha_modulacion + "', 'MM/DD/YYYY'), ";   
                        }         
                                 insertarCustoms += " '" + modalidad + "', "                 
                                                  + " '" + resultado_modulacion + "', ";   
                        if(!fecha_reconocimiento.trim().equals("")){         
                                 insertarCustoms += " TO_DATE('" + fecha_reconocimiento + "', 'MM/DD/YYYY'), ";    
                        }         
                        if(!fecha_liberacion.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + fecha_liberacion + "', 'MM/DD/YYYY'), "; 
                        }         
                                 insertarCustoms += " '" + sello_origen + "', "              
                                                  + " '" + sello_final + "', "; 
                        if(!fecha_retencion_aut.trim().equals("")){         
                                 insertarCustoms += " TO_DATE('" + fecha_retencion_aut + "', 'MM/DD/YYYY'), ";  
                        }         
                        if(!fecha_liberacion_aut.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + fecha_liberacion_aut + "', 'MM/DD/YYYY'), "; 
                        }                 
                                 insertarCustoms +=  " '" + estatus_operacion + "', "         
                                                   + " '" + motivo_atraso + "', "             
                                                   + " '" + observaciones + "', "; 
                }  
                
                if(idAgenteAduanal.equals("4001")||idAgenteAduanal.equals("4006")){ //LOGIX Y VF

                        if(!llegada_a_nova.trim().equals("")){        
                                 insertarCustoms += " TO_DATE('" + llegada_a_nova + "', 'MM/DD/YYYY'), ";    
                        } 
                        if(!llegada_a_globe_trade_sd.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + llegada_a_globe_trade_sd + "', 'MM/DD/YYYY'), ";  
                        }         
                                 insertarCustoms += " '" + archivo_m + "', ";
                        if(!fecha_archivo_m.trim().equals("")){         
                                 insertarCustoms += " TO_DATE('" + fecha_archivo_m + "', 'MM/DD/YYYY'), ";   
                        }         
                        if(!fecha_solicit_manip.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + fecha_solicit_manip + "', 'MM/DD/YYYY'), ";   
                        } 
                        if(!fecha_vencim_manip.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + fecha_vencim_manip + "', 'MM/DD/YYYY'), ";    
                        }       
                        if(!fecha_confirm_clave_pedim.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + fecha_confirm_clave_pedim + "', 'MM/DD/YYYY'), ";
                        }     
                        if(!fecha_recep_increment.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + fecha_recep_increment + "', 'MM/DD/YYYY'), "; 
                        }         
                                 insertarCustoms += " '" + t_e + "', ";
                        if(!fecha_vencim_inbound.trim().equals("")){         
                                 insertarCustoms += " TO_DATE('" + fecha_vencim_inbound + "', 'MM/DD/YYYY'), ";    
                        }         
                }
                
                if(idAgenteAduanal.equals("4002")||idAgenteAduanal.equals("4006")){  //Cusa ó VF
                                 insertarCustoms += " '" + no_bultos + "', "
                                                  + " '" + peso_kg + "', "
                                                  + " '" + transferencia + "', ";
                        if(!fecha_inicio_etiquetado.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + fecha_inicio_etiquetado + "', 'MM/DD/YYYY'), ";  
                        }
                        if(!fecha_termino_etiquetado.trim().equals("")){
                                 insertarCustoms += " TO_DATE('" + fecha_termino_etiquetado + "', 'MM/DD/YYYY'), ";    
                        }
                                 insertarCustoms += " '" + hora_termino_etiquetado + "', "
                                                  + " '" + proveedor + "', "
                                                  + " '" + proveedor_carga + "', "; 
                }        
                                insertarCustoms  += " '" + fy + "', "
                                                  + " '" + idAgenteAduanal + "', "
                                                  + " '" + prioridad + "', "
                                                  + " TO_DATE(SYSDATE, 'DD/MM/YYYY'), "    //INSERTAR DEFAULT        
                                                  + " '" + cve + "', "
                                                  + " '" + UserId + "') ";  
            }      
                    boolean oraOut1 = oraDB.execute(insertarCustoms); 
                    
                    System.out.println("Registro:" + insertarCustoms);    
                    
                    regPrioridad = " UPDATE TRA_INC_GTN_TEST SET ESTATUS='" + estatus_operacion + "', PRIORIDAD = '"+ prioridad +"' WHERE SHIPMENT_ID = '" + shipmentId + "'";
                    boolean oraOut2 = oraDB.execute(regPrioridad);
                
                    
        /************************************* Proceso para activar semaforo /*************************************/
       
        if(!eta_port_discharge.trim().equals("")){ 

            /*Consultar si existe el SHIPMENT_ID en la tabla de tra_inb_semaforo*/
            String valShipmentSemaforo = "SELECT DISTINCT SHIPMENT_ID FROM TRA_INB_SEMAFORO WHERE SHIPMENT_ID = '" + shipmentId + "'";
            boolean oraOut3 = oraDB.execute(valShipmentSemaforo); 

            if(oraOut3){

                /*Extrar las fechas del semaforo*/
                if (db.doDB(fac.consultarFechaSemaforo(shipmentId))) {
                    for (String[] rowF : db.getResultado()) {
                        eta_port_discharge_old = rowF[0];                /*fecha ya registrada en sistema*/
                        diasTranscurridos = Integer.parseInt(rowF[1]);   /*dias transcurridos ya en sistema*/
                    }
                }

                /*fecha ya registrada en sistema vs fecha recibida del front-ed*/
                if(!eta_port_discharge_old.equals(eta_port_discharge)){ 
                    
                    String[] par = eta_port_discharge.split("/");
                    int month = Integer.parseInt(par[0]);
                    int day = Integer.parseInt(par[1]);
                    int year = Integer.parseInt(par[2]);    

                    /* CONSULTAR (FECHA DISCHARGE/LOAD TYPE/DÍA CONTADOR) */
                    String data = obj.dataSemaforo(month, day, year, loadTypeFinal, diasTranscurridos);

                    /* SPLIT VARIABLE DATA */
                    String[] parts = data.split("-");
                    String dia_contador = parts[0];   
                    String color_semaforo = parts[1];  
                    String fecha_inicial = parts[2];  
                    String fecha_final = parts[3];
                    String dias_total_despacho = parts[4];      

                            semaforo = " UPDATE TRA_INB_SEMAFORO SET "
                                     + " DIAS_TRANSCURRIDOS = '" + dia_contador + "', "
                                     + " DIAS_CALCULADOS = '" + dias_total_despacho + "', "
                                     + " FECHA_ACTIVACION = TO_DATE('" + eta_port_discharge + "', 'MM/DD/YYYY'), "    
                                     //+ " FECHA_TERMINO = TO_DATE('" + fecha_final + "', 'MM/DD/YYYY'), "
                                     + " ESTATUS_SEMAFORO = '" + color_semaforo + "' " 
                                     + " WHERE SHIPMENT_ID = '" + shipmentId + "' "
                                     + " AND AGENTE_ID = '" + idAgenteAduanal + "' "; //idAgenteAduanal
                }

            }else{
                
                    String[] par = eta_port_discharge.split("/");
                    int month = Integer.parseInt(par[0]);
                    int day = Integer.parseInt(par[1]);
                    int year = Integer.parseInt(par[2]);

                    /* CONSULTAR (FECHA DISCHARGE/LOAD TYPE/DÍA CONTADOR) */
                    String data = obj.dataSemaforo(month, day, year, loadTypeFinal, 1);
                                                 //3/Verde/6/10/2023/6/21/2023/11
                    /* SPLIT VARIABLE DATA */
                    String[] parts = data.split("-");
                    String dia_contador = parts[0];   
                    String color_semaforo = parts[1];  
                    String fecha_inicial = parts[2];  
                    String fecha_final = parts[3];
                    String dias_total_despacho = parts[4];  


                           /*Registro por 1era. vez */
                            semaforo = " INSERT INTO TRA_INB_SEMAFORO "
                                     + " (REG_ID, "
                                     + " EVENTO_ID, "
                                     + " SHIPMENT_ID, "
                                     + " CONTAINER_ID, "
                                     + " PLANTILLA_ID, "
                                     + " AGENTE_ID, "
                                     + " LOAD_TYPE_FINAL, "
                                     + " DIAS_TRANSCURRIDOS, "
                                     + " ESTATUS_SEMAFORO, "
                                     + " FECHA_ACTIVACION, "
                                     //+ " FECHA_TERMINO, "
                                     + " DIAS_CALCULADOS) "
                                     + " VALUES "
                                     + "(NULL, "
                                     + " '" + evento + "', "
                                     + " '" + shipmentId + "', "
                                     + " '" + containerId + "', "
                                     + " '" + plantillaId +  "', "
                                     + " '" + idAgenteAduanal + "', "
                                     + " '" + loadTypeFinal + "', "
                                     + " '" + dia_contador + "', "
                                     + " '" + color_semaforo + "', "
                                     + " TO_DATE('" + eta_port_discharge + "', 'MM/DD/YYYY'), "
                                     //+ " TO_DATE('" + fecha_final + "', 'MM/DD/YYYY'), "
                                     + " '" + dias_total_despacho + "')";
            }             
        }            
               boolean oraOut4 = oraDB.execute(semaforo); System.out.println("semaforo:"+semaforo);
               
                if(oraOut1&&oraOut2&&oraOut4){
                    salida = "1";
                }else{
                    salida = "2";
                }
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
