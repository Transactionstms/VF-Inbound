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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        oraDB.connect(dbData.getUser(), dbData.getPassword());
        ConsultasQuery fac = new ConsultasQuery();                     //Objeto para consultas 
        SimpleDateFormat formato = new SimpleDateFormat("mm/dd/yyyy"); //Objeto para calcular días de despacho (SEMAFORO)
        CrearSemaforoCustoms obj = new CrearSemaforoCustoms();
        System.out.println("*********************************************************************************************************************************************");
        try{
                    
            String idAgenteAduanal = request.getParameter("idAgenteAduanal"); 
          //Contadores
            String contCustomsI = request.getParameter("numCustomsInicial");
            int numCustomsInicial = Integer.parseInt(contCustomsI);

            String contCustomsF = request.getParameter("numCustomsFinal");
            int numCustomsFinal = Integer.parseInt(contCustomsF);

            
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
            String estatus_operacion_actual = "";
            String motivo_atraso = "";
            String observaciones = "";
            String fy = "";
            String estatus_importado = "19";
            boolean addFechaImportacion = false;
          
          
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
            String eta_port_discharge_old = "";
            String insertarSemaforo = "";
            int diasTranscurridos = 0;
            String customs = "";
            String salida = "0";
            
            // Crear un mapa de nombres de meses a números de mes
            Map<String, Integer> mapaMeses = new HashMap<>();
            
            //Mapping Lenguages Spanish
            mapaMeses.put("Ene", 1);
            mapaMeses.put("Abr", 4);
            mapaMeses.put("Ago", 8);
            mapaMeses.put("Dic", 12);
            
             //Mapping Lenguages  English
            mapaMeses.put("Jan", 1);
            mapaMeses.put("Feb", 2);
            mapaMeses.put("Mar", 3);
            mapaMeses.put("Apr", 4);
            mapaMeses.put("May", 5);
            mapaMeses.put("Jun", 6);
            mapaMeses.put("Jul", 7);
            mapaMeses.put("Aug", 8);
            mapaMeses.put("Sep", 9);
            mapaMeses.put("Oct", 10);
            mapaMeses.put("Nov", 11);
            mapaMeses.put("Dec", 12);

             System.out.println("numCustomsInicial"+numCustomsInicial);
             System.out.println("numCustomsFinal"+numCustomsFinal);
             
              for (int i=numCustomsInicial; i<=numCustomsFinal; i++){
                  System.out.println("referenciaAA[" + i + "]");
                  //Parametros Indicadores
                  String referenciaAA = request.getParameter("referenciaAA[" + i + "]").trim(); 
                  String evento = request.getParameter("evento[" + i + "]").trim();
                  String shipmentId = request.getParameter("shipmentId[" + i + "]").trim();
                  String containerId = request.getParameter("containerId[" + i + "]").trim();
                  String loadTypeFinal = request.getParameter("loadTypeFinal[" + i + "]").trim();
                  String prioridad = request.getParameter("prioridad[" + i + "]").trim(); 
                  
                  //Parametros Generales
                  pais_origen = request.getParameter("pais_origen[" + i + "]").trim(); 
                  size_container = request.getParameter("size_container[" + i + "]").trim(); 
                  valor_usd = request.getParameter("valor_usd[" + i + "]").trim();                
                 
                  eta_port_discharge = request.getParameter("eta_port_discharge[" + i + "]").trim();                //fecha   
                //  if(!eta_port_discharge.equals("")){
                 if (eta_port_discharge != null && !eta_port_discharge.equals("") && !eta_port_discharge.equalsIgnoreCase("null")) {
                      System.out.println(" if eta_port_discharge");
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = eta_port_discharge.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      eta_port_discharge = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;
                  } 
                  
                  agente_aduanal = request.getParameter("agente_aduanal[" + i + "]").trim();            
                  pedimento_a1 = request.getParameter("pedimento_a1[" + i + "]").trim();             
                  pedimento_r1_1er = request.getParameter("pedimento_r1_1er[" + i + "]").trim();          
                  motivo_rectificacion_1er = request.getParameter("motivo_rectificacion_1er[" + i + "]").trim();  
                  pedimento_r1_2do = request.getParameter("pedimento_r1_2do[" + i + "]").trim();          
                  motivo_rectificacion_2do = request.getParameter("motivo_rectificacion_2do[" + i + "]").trim();  
                  
                  fecha_recepcion_doc = request.getParameter("fecha_recepcion_doc[" + i + "]").trim();               //fecha  
                  System.out.println("antes  fecha_recepcion_doc.equals"+fecha_recepcion_doc);
                  //if(!fecha_recepcion_doc.equals("") || !fecha_recepcion_doc.equals("null") || !fecha_recepcion_doc is null ){
                  if (fecha_recepcion_doc != null && !fecha_recepcion_doc.equals("") && !fecha_recepcion_doc.equalsIgnoreCase("null")) {
    
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_recepcion_doc.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_recepcion_doc = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;
                  }
                  
                  recinto = request.getParameter("recinto[" + i + "]").trim();
                  naviera = request.getParameter("naviera[" + i + "]").trim();
                  buque = request.getParameter("buque[" + i + "]").trim();
                  
                  fecha_revalidacion = request.getParameter("fecha_revalidacion[" + i + "]").trim();                 //fecha 
                  System.out.println("antes fecha_revalidacion"+fecha_revalidacion);
                 // if(!fecha_revalidacion.equals("")){
                   if (fecha_revalidacion != null && !fecha_revalidacion.equals("") && !fecha_revalidacion.equalsIgnoreCase("null")) {    
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_revalidacion.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_revalidacion = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;                                                          //parse the date into another format
                  }
                  
                  fecha_previo_origen = request.getParameter("fecha_previo_origen[" + i + "]").trim();               //fecha 
                 // if(!fecha_previo_origen.equals("")){
                       if (fecha_previo_origen != null && !fecha_previo_origen.equals("") && !fecha_previo_origen.equalsIgnoreCase("null")) { 
                                      
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_previo_origen.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_previo_origen = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  fecha_previo_destino = request.getParameter("fecha_previo_destino[" + i + "]").trim();             //fecha 
                 // if(!fecha_previo_destino.equals("")){
                      if (fecha_previo_destino != null && !fecha_previo_destino.equals("") && !fecha_previo_destino.equalsIgnoreCase("null")) { 
                                      
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_previo_destino.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_previo_destino = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  fecha_resultado_previo = request.getParameter("fecha_resultado_previo[" + i + "]").trim();         //fecha 
                  
                  //if(!fecha_resultado_previo.equals("")){
                   if (fecha_resultado_previo != null && !fecha_resultado_previo.equals("") && !fecha_resultado_previo.equalsIgnoreCase("null")) {                     
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_resultado_previo.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_resultado_previo = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  proforma_final = request.getParameter("proforma_final[" + i + "]").trim();                  //fecha  
                 // if(!proforma_final.equals("")){
                   if (proforma_final != null && !proforma_final.equals("") && !proforma_final.equalsIgnoreCase("null")) {                     
                                        
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = proforma_final.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      proforma_final = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  permiso = request.getParameter("permiso[" + i + "]").trim();                   
                  
                  fecha_envio = request.getParameter("fecha_envio[" + i + "]").trim();                               //fecha  
                //  if(!fecha_envio.equals("")){
                   if (fecha_envio != null && !fecha_envio.equals("") && !fecha_envio.equalsIgnoreCase("null")) {                     
                                         
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_envio.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_envio = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  fecha_recepcion_perm = request.getParameter("fecha_recepcion_perm[" + i + "]").trim();             //fecha 
                 // if(!fecha_recepcion_perm.equals("")){
                   if (fecha_recepcion_perm != null && !fecha_recepcion_perm.equals("") && !fecha_recepcion_perm.equalsIgnoreCase("null")) {                     
                                            
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_recepcion_perm.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_recepcion_perm = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  fecha_activacion_perm = request.getParameter("fecha_activacion_perm[" + i + "]").trim();           //fecha 
                 // if(!fecha_activacion_perm.equals("")){
                     if (fecha_activacion_perm != null && !fecha_activacion_perm.equals("") && !fecha_activacion_perm.equalsIgnoreCase("null")) {                     
                                        
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_activacion_perm.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_activacion_perm = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  fecha_permisos_aut = request.getParameter("fecha_permisos_aut[" + i + "]").trim();                 //fecha 
                  //if(!fecha_permisos_aut.equals("")){
                     if (fecha_permisos_aut != null && !fecha_permisos_aut.equals("") && !fecha_permisos_aut.equalsIgnoreCase("null")) {                     
                                           
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_permisos_aut.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_permisos_aut = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  co_pref_arancelaria = request.getParameter("co_pref_arancelaria[" + i + "]").trim();       
                  aplic_pref_arancelaria = request.getParameter("aplic_pref_arancelaria[" + i + "]").trim();    
                  req_uva = request.getParameter("req_uva[" + i + "]").trim();   
                  req_ca = request.getParameter("req_ca[" + i + "]").trim();   
                  
                  fecha_recepcion_ca = request.getParameter("fecha_recepcion_ca[" + i + "]").trim();                  //fecha 
                 // if(!fecha_recepcion_ca.equals("")){
                     if (fecha_recepcion_ca != null && !fecha_recepcion_ca.equals("") && !fecha_recepcion_ca.equalsIgnoreCase("null")) {                     
                                         
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_recepcion_ca.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_recepcion_ca = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  num_constancia_ca = request.getParameter("num_constancia_ca[" + i + "]").trim();   
                  monto_ca = request.getParameter("monto_ca[" + i + "]").trim();   
                  
                  fecha_doc_completos = request.getParameter("fecha_doc_completos[" + i + "]").trim();                 //fecha 
                  //if(!fecha_doc_completos.equals("")){
                     if (fecha_doc_completos != null && !fecha_doc_completos.equals("") && !fecha_doc_completos.equalsIgnoreCase("null")) {                     
                                         
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_doc_completos.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_doc_completos = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  fecha_pago_pedimento = request.getParameter("fecha_pago_pedimento[" + i + "]").trim();               //fecha 
                //  if(!fecha_pago_pedimento.equals("")){
                      if (fecha_pago_pedimento != null && !fecha_pago_pedimento.equals("") && !fecha_pago_pedimento.equalsIgnoreCase("null")) {                     
                                         
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_pago_pedimento.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_pago_pedimento = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  fecha_solicitud_transporte = request.getParameter("fecha_solicitud_transporte[" + i + "]").trim();   //fecha 
                  //if(!fecha_solicitud_transporte.equals("")){
                    if (fecha_solicitud_transporte != null && !fecha_solicitud_transporte.equals("") && !fecha_solicitud_transporte.equalsIgnoreCase("null")) {                     
                                            
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_solicitud_transporte.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_solicitud_transporte = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  fecha_modulacion = request.getParameter("fecha_modulacion[" + i + "]").trim();                       //fecha 
                 // if(!fecha_modulacion.equals("")){
                       if (fecha_modulacion != null && !fecha_modulacion.equals("") && !fecha_modulacion.equalsIgnoreCase("null")) {                     
                      
                           
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_modulacion.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_modulacion = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  modalidad = request.getParameter("modalidad[" + i + "]").trim();                 
                  resultado_modulacion = request.getParameter("resultado_modulacion[" + i + "]").trim();      
                  
                  fecha_reconocimiento = request.getParameter("fecha_reconocimiento[" + i + "]").trim();               //fecha 
                 // if(!fecha_reconocimiento.equals("")){
                     if (fecha_reconocimiento != null && !fecha_reconocimiento.equals("") && !fecha_reconocimiento.equalsIgnoreCase("null")) {                     
                                         
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_reconocimiento.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_reconocimiento = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  fecha_liberacion = request.getParameter("fecha_liberacion[" + i + "]").trim();                       //fecha 
                 // if(!fecha_liberacion.equals("")){   
                       if (fecha_liberacion != null && !fecha_liberacion.equals("") && !fecha_liberacion.equalsIgnoreCase("null")) {                     
                      
                                  System.out.println("fecha_liberacion"+fecha_liberacion);     
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_liberacion.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_liberacion =mes+"/"+dia+"/"+anio ;// String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  sello_origen = request.getParameter("sello_origen[" + i + "]").trim();              
                  sello_final = request.getParameter("sello_final[" + i + "]").trim();               
                  
                  fecha_retencion_aut = request.getParameter("fecha_retencion_aut[" + i + "]").trim();                 //fecha 
                //  if(!fecha_retencion_aut.equals("")){
                    if (fecha_retencion_aut != null && !fecha_retencion_aut.equals("") && !fecha_retencion_aut.equalsIgnoreCase("null")) {                     
                                          
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_retencion_aut.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_retencion_aut = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  fecha_liberacion_aut = request.getParameter("fecha_liberacion_aut[" + i + "]").trim();               //fecha 
                //  if(!fecha_liberacion_aut.equals("")){                  
                   if (fecha_liberacion_aut != null && !fecha_liberacion_aut.equals("") && !fecha_liberacion_aut.equalsIgnoreCase("null")) {                     
                                           
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_liberacion_aut.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_liberacion_aut = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                  }
                  
                  estatus_operacion_actual = request.getParameter("estatus_operacion[" + i + "]").trim();         
                  motivo_atraso = request.getParameter("motivo_atraso[" + i + "]").trim();             
                  observaciones = request.getParameter("observaciones[" + i + "]").trim(); 
                  fy = request.getParameter("fy[" + i + "]").trim();
 
              
            if(idAgenteAduanal.equals("4001")||idAgenteAduanal.equals("4006")){ //LOGIX Y VF
               
                llegada_a_nova = request.getParameter("llegada_a_nova[" + i + "]").trim();                                 //fecha 
              //  if(!llegada_a_nova.equals("")){                  
                   if (llegada_a_nova != null && !llegada_a_nova.equals("") && !llegada_a_nova.equalsIgnoreCase("null")) {                     
                                            
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = llegada_a_nova.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      llegada_a_nova = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                }
                
                llegada_a_globe_trade_sd = request.getParameter("llegada_a_globe_trade_sd[" + i + "]").trim();             //fecha 
               // if(!llegada_a_globe_trade_sd.equals("")){                  
                  if (llegada_a_globe_trade_sd != null && !llegada_a_globe_trade_sd.equals("") && !llegada_a_globe_trade_sd.equalsIgnoreCase("null")) {                     
                                          
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = llegada_a_globe_trade_sd.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      llegada_a_globe_trade_sd = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                }
                                
                archivo_m = request.getParameter("archivo_m[" + i + "]").trim(); 
                
                fecha_archivo_m = request.getParameter("fecha_archivo_m[" + i + "]").trim();                               //fecha  
               // if(!fecha_archivo_m.equals("")){                  
                   if (fecha_archivo_m != null && !fecha_archivo_m.equals("") && !fecha_archivo_m.equalsIgnoreCase("null")) {                     
                                         
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_archivo_m.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_archivo_m = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                }
                                
                fecha_solicit_manip = request.getParameter("fecha_solicit_manip[" + i + "]").trim();                       //fecha 
              //  if(!fecha_solicit_manip.equals("")){                  
                    if (fecha_solicit_manip != null && !fecha_solicit_manip.equals("") && !fecha_solicit_manip.equalsIgnoreCase("null")) {                     
                                        
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_solicit_manip.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_solicit_manip = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                }
                                
                fecha_vencim_manip = request.getParameter("fecha_vencim_manip[" + i + "]").trim();                         //fecha 
               // if(!fecha_vencim_manip.equals("")){                  
                  if (fecha_vencim_manip != null && !fecha_vencim_manip.equals("") && !fecha_vencim_manip.equalsIgnoreCase("null")) {                     
                                          
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_vencim_manip.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_vencim_manip = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                }
                                
                fecha_confirm_clave_pedim = request.getParameter("fecha_confirm_clave_pedim[" + i + "]").trim();           //fecha 
             //   if(!fecha_confirm_clave_pedim.equals("")){                  
                     if (fecha_confirm_clave_pedim != null && !fecha_confirm_clave_pedim.equals("") && !fecha_confirm_clave_pedim.equalsIgnoreCase("null")) {                     
                                     
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_confirm_clave_pedim.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_confirm_clave_pedim = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                }
                                
                fecha_recep_increment = request.getParameter("fecha_recep_increment[" + i + "]").trim();                   //fecha 
               // if(!fecha_recep_increment.equals("")){                  
                     if (fecha_recep_increment != null && !fecha_recep_increment.equals("") && !fecha_recep_increment.equalsIgnoreCase("null")) {                     
                                           
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_recep_increment.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_recep_increment = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                }
                                
                t_e = request.getParameter("t_e[" + i + "]").trim(); 
                
                fecha_vencim_inbound = request.getParameter("fecha_vencim_inbound[" + i + "]").trim();                     //fecha 
                //if(!fecha_vencim_inbound.equals("")){                  
                      if (fecha_vencim_inbound != null && !fecha_vencim_inbound.equals("") && !fecha_vencim_inbound.equalsIgnoreCase("null")) {                     
                                         
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_vencim_inbound.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_vencim_inbound = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                }
                                
            }
                  System.out.println(" antes idAgenteAduanal"+idAgenteAduanal);
            if(idAgenteAduanal.equals("4002")||idAgenteAduanal.equals("4006")){ //CUSA Y VF
                 
                no_bultos = request.getParameter("no_bultos[" + i + "]").trim(); 
                peso_kg = request.getParameter("peso_kg[" + i + "]").trim(); 
                transferencia = request.getParameter("transferencia[" + i + "]").trim();
                
                fecha_inicio_etiquetado= request.getParameter("fecha_inicio_etiquetado[" + i + "]").trim();                //fecha 
                if(!fecha_inicio_etiquetado.equals("")){                  
                                      
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_inicio_etiquetado.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_inicio_etiquetado = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                }
                                
                fecha_termino_etiquetado = request.getParameter("fecha_termino_etiquetado[" + i + "]").trim();             //fecha 
               // if(!fecha_termino_etiquetado.equals("")){                  
                      if (fecha_termino_etiquetado != null && !fecha_termino_etiquetado.equals("") && !fecha_termino_etiquetado.equalsIgnoreCase("null")) {                     
                                         
                      // Dividir la cadena de entrada en partes
                      String[] partesFecha = fecha_termino_etiquetado.split("/");
                      String mes = partesFecha[0];
                      String dia = partesFecha[1];
                      String anio = partesFecha[2];

                      // Obtener el número de mes a partir del nombre del mes
                      int numeroMes = mapaMeses.get(mes);

                      // Construir la fecha en formato "MM/dd/yyyy"
                      fecha_termino_etiquetado = String.format("%02d", numeroMes) + "/" + dia + "/" + anio;     
                }
                                
                hora_termino_etiquetado = request.getParameter("hora_termino_etiquetado[" + i + "]").trim(); 
                proveedor = request.getParameter("proveedor[" + i + "]").trim(); 
                proveedor_carga = request.getParameter("proveedor_carga[" + i + "]").trim(); 
            
            }
           
              //Consultar Estatus de Operación del shipment

                      System.out.println("estatus_operacion_actual"+estatus_operacion_actual);
                      if (estatus_operacion_actual.equals(estatus_importado)) { 
                      
                          if (db.doDB(fac.consultarEstatusImportacion(shipmentId))) {
                              for (String[] estatus_shipment : db.getResultado()) { 
                                  
                                  if(estatus_operacion_actual.equals(estatus_shipment[0])){
                                     addFechaImportacion = false;  //shipment con fecha de importación.
                                  }else{
                                     addFechaImportacion = true;   //shipment sin fecha de importación.
                                  }
                                  
                              }
                          }  
                          
                      } //false: sin registro de fecha/cualquier estatus
                      
            
            //Consultar existencia de Shipmentd para el tipo de registro:
            String valExist = "SELECT DISTINCT CUSTREG_ID FROM TRA_INB_CUSTOMS WHERE SHIPMENT_ID = '" + shipmentId + "'"; //";
                  System.out.println("valExist"+valExist);
            boolean oraOut = oraDB.execute(valExist);
            
            if(oraOut){

                                 insertarCustoms = " UPDATE TRA_INB_CUSTOMS SET "                              
                                                 + " REFERENCIA_AA = '" + referenciaAA + "', "
                                                 + " PAIS_ORIGEN = '" + pais_origen + "', "   
                                                 + " SIZE_CONTAINER = '" + size_container + "', "   
                                                 + " VALOR_USD = '" + valor_usd + "', "
                                                 + " AGENTE_ADUANAL = '" + agente_aduanal + "', "             
                                                 + " PEDIMENTO_A1 = '" + pedimento_a1 + "', "               
                                                 + " PEDIMENTO_R1 = '" + pedimento_r1_1er + "', "           
                                                 + " MOTIVO_RECTIFICACION_1 = '" + motivo_rectificacion_1er + "', "   
                                                 + " PEDIMENTO_R1_2DO = '" + pedimento_r1_2do + "', "           
                                                 + " MOTIVO_RECTIFICACION_2 = '" + motivo_rectificacion_2do + "', ";   
                        //if(!fecha_recepcion_doc.trim().equals("null")){ 
                            if (fecha_recepcion_doc != null && !fecha_recepcion_doc.equals("") && !fecha_recepcion_doc.equalsIgnoreCase("null")) {

                                insertarCustoms += " FECHA_RECEPCION_DOCUMENTOS = TO_DATE('" + fecha_recepcion_doc + "', 'MM/DD/YYYY'), ";
                        }        
                                insertarCustoms += " RECINTO = '" + recinto + "', "     
                                                 + " NAVIERA_FORWARDER = '" + naviera + "', "     
                                                 + " BUQUE = '" + buque + "', "; 
                        //if(!fecha_revalidacion.trim().equals("null")){  
                          if (fecha_revalidacion != null && !fecha_revalidacion.equals("") && !fecha_revalidacion.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_REVALID_LIBE_BL = TO_DATE('" + fecha_revalidacion + "', 'MM/DD/YYYY'), "; 
                         }  
                        //if(!fecha_previo_origen.trim().equals("null")){
                          if (fecha_previo_origen != null && !fecha_previo_origen.equals("") && !fecha_previo_origen.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_PREVIO_ORIGEN = TO_DATE('" + fecha_previo_origen + "', 'MM/DD/YYYY'), "; 
                        } 
                        //if(!fecha_previo_destino.trim().equals("null")){
                          if (fecha_previo_destino != null && !fecha_previo_destino.equals("") && !fecha_previo_destino.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_PREVIO_DESTINO = TO_DATE('" + fecha_previo_destino + "', 'MM/DD/YYYY'), ";
                        }        
                       // if(!fecha_resultado_previo.trim().equals("null")){
                         if (fecha_resultado_previo != null && !fecha_resultado_previo.equals("") && !fecha_resultado_previo.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_RESULTADO_PREVIO = TO_DATE('" + fecha_resultado_previo + "', 'MM/DD/YYYY'), ";    
                        }       
                        //if(!proforma_final.trim().equals("null")){
                          if (proforma_final != null && !proforma_final.equals("") && !proforma_final.equalsIgnoreCase("null")) {
                                insertarCustoms += " PROFORMA_FINAL = TO_DATE('" + proforma_final + "', 'MM/DD/YYYY'), ";  
                        }        
                                insertarCustoms += " REQUIERE_PERMISO = '" + permiso + "', ";
                        //if(!fecha_envio.trim().equals("null")){     
                          if (fecha_envio != null && !fecha_envio.equals("") && !fecha_envio.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_ENVIO_FICHAS_NOTAS = TO_DATE('" + fecha_envio + "', 'MM/DD/YYYY'), "; 
                        }        
                       // if(!fecha_recepcion_perm.trim().equals("null")){
                         if (fecha_recepcion_perm != null && !fecha_recepcion_perm.equals("") && !fecha_recepcion_perm.equalsIgnoreCase("null")) {
                                insertarCustoms += " FEC_RECEPCION_PERMISOS_TRAMIT = TO_DATE('" + fecha_recepcion_perm + "', 'MM/DD/YYYY'), ";  
                       }
                        //if(!fecha_activacion_perm.trim().equals("null")){
                          if (fecha_activacion_perm != null && !fecha_activacion_perm.equals("") && !fecha_activacion_perm.equalsIgnoreCase("null")) {
                                insertarCustoms += " FEC_ACT_PERMISOS = TO_DATE('" + fecha_activacion_perm + "', 'MM/DD/YYYY'), ";
                        }    
                       // if(!fecha_permisos_aut.trim().equals("null")){
                         if (fecha_permisos_aut != null && !fecha_permisos_aut.equals("") && !fecha_permisos_aut.equalsIgnoreCase("null")) {
                                insertarCustoms += " FEC_PERM_AUT = TO_DATE('" + fecha_permisos_aut + "', 'MM/DD/YYYY'), "; 
                       }        
                                insertarCustoms += " CO_APLIC_PREF_ARANCELARIA = '" + co_pref_arancelaria + "', "        
                                                 + " APLIC_PREF_ARANCELARIA_CO = '" + aplic_pref_arancelaria + "', "     
                                                 + " REQUIERE_UVA = '" + req_uva + "', "     
                                                 + " REQUIERE_CA = '" + req_ca + "', ";   
                        //if(!fecha_recepcion_ca.trim().equals("null")){  
                          if (fecha_recepcion_ca != null && !fecha_recepcion_ca.equals("") && !fecha_recepcion_ca.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_RECEPCION_CA = TO_DATE('" + fecha_recepcion_ca + "', 'MM/DD/YYYY'), ";  
                        }        
                                insertarCustoms += " NÚMERO_CONSTANCIA_CA = '" + num_constancia_ca + "', "   
                                                 + " MONTO_CA = '" + monto_ca + "', ";   
                        //if(!fecha_doc_completos.trim().equals("null")){ 
                          if (fecha_doc_completos != null && !fecha_doc_completos.equals("") && !fecha_doc_completos.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_DOCUMENTOS_COMPLETOS = TO_DATE('" + fecha_doc_completos + "', 'MM/DD/YYYY'), "; 
                       }  
                       // if(!fecha_pago_pedimento.trim().equals("null")){
                         if (fecha_pago_pedimento != null && !fecha_pago_pedimento.equals("") && !fecha_pago_pedimento.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_PAGO_PEDIMENTO = TO_DATE('" + fecha_pago_pedimento + "', 'MM/DD/YYYY'), "; 
                        }
                       // if(!fecha_solicitud_transporte.trim().equals("null")){
                         if (fecha_solicitud_transporte != null && !fecha_solicitud_transporte.equals("") && !fecha_solicitud_transporte.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_SOLICITUD_TRANSPORTE = TO_DATE('" + fecha_solicitud_transporte + "', 'MM/DD/YYYY'), "; 
                        }
                        //if(!fecha_modulacion.trim().equals("null")){
                          if (fecha_modulacion != null && !fecha_modulacion.equals("") && !fecha_modulacion.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_MODULACION = TO_DATE('" + fecha_modulacion + "', 'MM/DD/YYYY'), ";
                        }        
                                insertarCustoms += " MODALIDAD_CAMION_TREN = '" + modalidad + "', "                  
                                                 + " RESULT_MODULACION_VERDE_ROJO = '" + resultado_modulacion + "', ";   
                       // if(!fecha_reconocimiento.trim().equals("null")){  
                         if (fecha_reconocimiento != null && !fecha_reconocimiento.equals("") && !fecha_reconocimiento.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_RECONOCIMIENTO = TO_DATE('" + fecha_reconocimiento + "', 'MM/DD/YYYY'), ";  
                        } 
                        //if(!fecha_liberacion.trim().equals("null")){
                          if (fecha_liberacion != null && !fecha_liberacion.equals("") && !fecha_liberacion.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_LIBERACION = TO_DATE('" + fecha_liberacion + "', 'MM/DD/YYYY'), ";    ///**********
                        }         
                        System.out.println(" FECHA_LIBERACION = TO_DATE('" + fecha_liberacion + "', 'MM/DD/YYYY'), ");
                        
                                insertarCustoms += " SELLO_ORIGEN = '" + sello_origen + "', "               
                                                 + " SELLO_FINAL = '" + sello_final + "', ";    
                        //if(!fecha_retencion_aut.trim().equals("null")){ 
                          if (fecha_retencion_aut != null && !fecha_retencion_aut.equals("") && !fecha_retencion_aut.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_RETENCION_AUTORIDAD = TO_DATE('" + fecha_retencion_aut + "', 'MM/DD/YYYY'), ";
                        }   
                       // if(!fecha_liberacion_aut.trim().equals("null")){
                         if (fecha_liberacion_aut != null && !fecha_liberacion_aut.equals("") && !fecha_liberacion_aut.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_LIB_POR_RET_AUT = TO_DATE('" + fecha_liberacion_aut + "', 'MM/DD/YYYY'), ";   
                         }        
                                insertarCustoms += " ESTATUS_OPERACION = '" + estatus_operacion_actual + "', "          
                                                 + " MOTIVO_ATRASO = '" + motivo_atraso + "', "              
                                                 + " OBSERVACIONES = '" + observaciones + "', ";   
            
                if(idAgenteAduanal.equals("4001")||idAgenteAduanal.equals("4006")){ //LOGIX Y VF
                    
                        //if(!llegada_a_nova.trim().equals("null")){        
                                insertarCustoms += " LLEGADA_A_NOVA = TO_DATE('" + llegada_a_nova + "', 'MM/DD/YYYY'), "; 
                        //}        
                        //if(!llegada_a_globe_trade_sd.trim().equals("null")){ 
                          if (llegada_a_globe_trade_sd != null && !llegada_a_globe_trade_sd.equals("") && !llegada_a_globe_trade_sd.equalsIgnoreCase("null")) {
                                insertarCustoms += " LLEGADA_A_GLOBE_TRADE_SD = TO_DATE('" + llegada_a_globe_trade_sd + "', 'MM/DD/YYYY'), "; 
                        }       
                                insertarCustoms += " ARCHIVO_M = '" + archivo_m + "', "; 

                        //if(!fecha_archivo_m.trim().equals("null")){    
                          if (fecha_archivo_m != null && !fecha_archivo_m.equals("") && !fecha_archivo_m.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_ARCHIVO_M = TO_DATE('" + fecha_archivo_m + "', 'MM/DD/YYYY'), ";  
                        }        
                        //if(!fecha_solicit_manip.trim().equals("null")){  
                          if (fecha_solicit_manip != null && !fecha_solicit_manip.equals("") && !fecha_solicit_manip.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_SOLICIT_MANIP = TO_DATE('" + fecha_solicit_manip + "', 'MM/DD/YYYY'), ";  
                        }        
                        //if(!fecha_vencim_manip.trim().equals("null")){   
                          if (fecha_vencim_manip != null && !fecha_vencim_manip.equals("") && !fecha_vencim_manip.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_VENCIM_MANIP = TO_DATE('" + fecha_vencim_manip + "', 'MM/DD/YYYY'), ";  
                        }        
                        //if(!fecha_confirm_clave_pedim.trim().equals("null")){ 
                          if (fecha_confirm_clave_pedim != null && !fecha_confirm_clave_pedim.equals("") && !fecha_confirm_clave_pedim.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_CONFIRM_CLAVE_PEDIM = TO_DATE('" + fecha_confirm_clave_pedim + "', 'MM/DD/YYYY'), ";  
                       }        
                       // if(!fecha_recep_increment.trim().equals("null")){  
                         if (fecha_recep_increment != null && !fecha_recep_increment.equals("") && !fecha_recep_increment.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_RECEP_INCREMENT = TO_DATE('" + fecha_recep_increment + "', 'MM/DD/YYYY'), ";   
                        }        
                                insertarCustoms += " T_E = '" + t_e + "', "; 
                        //if(!fecha_vencim_inbound.trim().equals("null")){   
                          if (fecha_vencim_inbound != null && !fecha_vencim_inbound.equals("") && !fecha_vencim_inbound.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_VENCIM_INBOUND = TO_DATE('" + fecha_vencim_inbound + "', 'MM/DD/YYYY'), ";
                        }        
                }
                
                if(idAgenteAduanal.equals("4002")||idAgenteAduanal.equals("4006")){  //CUSA Y VF
                    
                                insertarCustoms += " NO_BULTOS = '" + no_bultos + "', " 
                                                 + " PESO_KG = '" + peso_kg + "', "  
                                                 + " TRANSFERENCIA = '" + transferencia + "', ";  
                       // if(!fecha_inicio_etiquetado.trim().equals("null")){        
                        if (fecha_inicio_etiquetado != null && !fecha_inicio_etiquetado.equals("") && !fecha_inicio_etiquetado.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_INICIO_ETIQUETADO = TO_DATE('" + fecha_inicio_etiquetado + "', 'MM/DD/YYYY'), ";
                        }        
                       // if(!fecha_termino_etiquetado.trim().equals("null")){ 
                         if (fecha_termino_etiquetado != null && !fecha_termino_etiquetado.equals("") && !fecha_termino_etiquetado.equalsIgnoreCase("null")) {
                                insertarCustoms += " FECHA_TERMINO_ETIQUETADO = TO_DATE('" + fecha_termino_etiquetado + "', 'MM/DD/YYYY'), ";
                       }        
                                insertarCustoms += " HORA_TERMINO_ETIQUETADO = '" + hora_termino_etiquetado + "', "  
                                                 + " PROVEEDOR = '" + proveedor + "', "  
                                                 + " PROVEEDOR_CARGA = '" + proveedor_carga + "', "; 
                }
                
                if(addFechaImportacion){
                                insertarCustoms += " FECHA_IMPORTACION = TO_DATE(SYSDATE, 'DD/MM/YYYY'), ";
                }               
                                insertarCustoms += " FY = '" + fy + "', " 
                                                 + " AGENTE_ADUANAL_ID = '" + idAgenteAduanal + "', " 
                                                 + " PRIORIDAD = '" + prioridad + "', "
                                                 + " USER_NID = '" + UserId + "' "
                                                 + " WHERE SHIPMENT_ID = '" + shipmentId + "' ";
                 System.out.println("if"+insertarCustoms);   
            }else{
                System.out.println("else"+insertarCustoms);
                
                //oraDB.connect(dbData.getUser(), dbData.getPassword()); /* CONEXIÓN */
                
                                  insertarCustoms = " INSERT INTO TRA_INB_CUSTOMS "
                                                  + " (CUSTREG_ID, "
                                                  + " NUMERO_DE_EVENTO, "  
                                                  + " SHIPMENT_ID, "  
                                                  + " CONTAINER_ID, "  
                                                  + " REFERENCIA_AA, "
                                                  + " PAIS_ORIGEN, "  
                                                  + " SIZE_CONTAINER, "  
                                                  + " VALOR_USD, ";          
                                 insertarCustoms += " AGENTE_ADUANAL, "            
                                                  + " PEDIMENTO_A1, "              
                                                  + " PEDIMENTO_R1, "          
                                                  + " MOTIVO_RECTIFICACION_1, "  
                                                  + " PEDIMENTO_R1_2DO, "          
                                                  + " MOTIVO_RECTIFICACION_2, "; 
                                   System.out.println("else1 "+insertarCustoms);
                     //   if(!fecha_recepcion_doc.trim().equals("null")){         
                                 insertarCustoms += " FECHA_RECEPCION_DOCUMENTOS, ";   
                    //    }         
                                 insertarCustoms += " RECINTO, "    
                                                  + " NAVIERA_FORWARDER, "    
                                                  + " BUQUE, ";  
                    //    if(!fecha_revalidacion.trim().equals("null")){         
                                 insertarCustoms += " FECHA_REVALID_LIBE_BL, ";
                   //     }
                    //    if(!fecha_previo_origen.trim().equals("null")){
                                 insertarCustoms += " FECHA_PREVIO_ORIGEN, "; 
                  //      } 
                  //      if(!fecha_previo_destino.trim().equals("null")){
                                 insertarCustoms += " FECHA_PREVIO_DESTINO, ";  
                  //      }     
                       // if(!fecha_resultado_previo.trim().equals("null")){
                                 insertarCustoms += " FECHA_RESULTADO_PREVIO, "; 
                       // }
                        //if(!proforma_final.trim().equals("null")){
                                 insertarCustoms += " PROFORMA_FINAL, "; 
                        //}        
                                 insertarCustoms += " REQUIERE_PERMISO, ";     
                        //if(!fecha_envio.trim().equals("null")){         
                                 insertarCustoms += " FECHA_ENVIO_FICHAS_NOTAS, ";   
                        //}
                       // if(!fecha_recepcion_perm.trim().equals("null")){
                                 insertarCustoms += " FEC_RECEPCION_PERMISOS_TRAMIT, ";     
                       // }         
                       //// if(!fecha_activacion_perm.trim().equals("null")){
                                 insertarCustoms += " FEC_ACT_PERMISOS, ";     
                       // }     
                       // if(!fecha_permisos_aut.trim().equals("null")){
                                 insertarCustoms += " FEC_PERM_AUT, ";  
                       // }         
                                 insertarCustoms += " CO_APLIC_PREF_ARANCELARIA, "       
                                                  + " APLIC_PREF_ARANCELARIA_CO, "    
                                                  + " REQUIERE_UVA, "    
                                                  + " REQUIERE_CA, ";  
                       // if(!fecha_recepcion_ca.trim().equals("null")){         
                                 insertarCustoms += " FECHA_RECEPCION_CA, "; 
                       // }         
                                 insertarCustoms += " NÚMERO_CONSTANCIA_CA, "  
                                                  + " MONTO_CA, ";  
                       // if(!fecha_doc_completos.trim().equals("null")){         
                                 insertarCustoms += " FECHA_DOCUMENTOS_COMPLETOS, ";     
                       // }
                       // if(!fecha_pago_pedimento.trim().equals("null")){
                                 insertarCustoms += " FECHA_PAGO_PEDIMENTO, ";     
                       // }     
                        //if(!fecha_solicitud_transporte.trim().equals("null")){
                                 insertarCustoms += " FECHA_SOLICITUD_TRANSPORTE, ";
                       // }         
                       // if(!fecha_modulacion.trim().equals("null")){
                                 insertarCustoms += " FECHA_MODULACION, "; 
                      //  }         
                                 insertarCustoms += " MODALIDAD_CAMION_TREN, "                 
                                                  + " RESULT_MODULACION_VERDE_ROJO, "; 
                        //if(!fecha_reconocimiento.trim().equals("null")){         
                                 insertarCustoms += " FECHA_RECONOCIMIENTO, "; 
                        //}     
                        //if(!fecha_liberacion.trim().equals("null")){
                                 insertarCustoms += " FECHA_LIBERACION, ";  
                        //}                 
                                 insertarCustoms += " SELLO_ORIGEN, "              
                                                  + " SELLO_FINAL, "; 
                       // if(!fecha_retencion_aut.trim().equals("null")){         
                                 insertarCustoms += " FECHA_RETENCION_AUTORIDAD, ";    
                       // }         
                       // if(!fecha_liberacion_aut.trim().equals("null")){
                                 insertarCustoms += " FECHA_LIB_POR_RET_AUT, ";  
                       // }         
                                 insertarCustoms += " ESTATUS_OPERACION, "         
                                                  + " MOTIVO_ATRASO, "             
                                                  + " OBSERVACIONES, ";        

               if(idAgenteAduanal.equals("4001")||idAgenteAduanal.equals("4006")){ //LOGIX Y VF
                       // if(!llegada_a_nova.trim().equals("null")){        
                                insertarCustoms += " LLEGADA_A_NOVA, ";
                       // }   
                       // if(!llegada_a_globe_trade_sd.trim().equals("null")){
                                insertarCustoms += " LLEGADA_A_GLOBE_TRADE_SD, "; 
                      //  }       
                                insertarCustoms += " ARCHIVO_M, "; 
                        //if(!fecha_archivo_m.trim().equals("null")){       
                                insertarCustoms += " FECHA_ARCHIVO_M, "; 
                       // }
                       // if(!fecha_solicit_manip.trim().equals("null")){
                                insertarCustoms += " FECHA_SOLICIT_MANIP, "; 
                       // }    
                       // if(!fecha_vencim_manip.trim().equals("null")){
                                insertarCustoms += " FECHA_VENCIM_MANIP, "; 
                       // }
                       // if(!fecha_confirm_clave_pedim.trim().equals("null")){
                                insertarCustoms += " FECHA_CONFIRM_CLAVE_PEDIM, "; 
                       // }   
                       // if(!fecha_recep_increment.trim().equals("null")){
                                insertarCustoms += " FECHA_RECEP_INCREMENT, ";
                       // }        
                                insertarCustoms += " T_E, "; 
                        //if(!fecha_vencim_inbound.trim().equals("null")){        
                                insertarCustoms += " FECHA_VENCIM_INBOUND, ";
                        //}        
                }
                  System.out.println("else2"+insertarCustoms);
                if(idAgenteAduanal.equals("4002")||idAgenteAduanal.equals("4006")){  //CUSA Y VF
                                insertarCustoms += " NO_BULTOS, "
                                                 + " PESO_KG, " 
                                                 + " TRANSFERENCIA, "; 
                        //if(!fecha_inicio_etiquetado.trim().equals("null")){        
                                insertarCustoms += " FECHA_INICIO_ETIQUETADO, "; 
                        //}   
                        //if(!fecha_termino_etiquetado.trim().equals("null")){
                                insertarCustoms += " FECHA_TERMINO_ETIQUETADO, "; 
                        //}        
                                insertarCustoms += " HORA_TERMINO_ETIQUETADO, " 
                                                 + " PROVEEDOR, " 
                                                 + " PROVEEDOR_CARGA, ";
                } 
                
                if(addFechaImportacion){
                                 insertarCustoms += " FECHA_IMPORTACION, ";
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
                                                  + " '" + referenciaAA + "', "
                                                  + " '" + pais_origen + "', "  
                                                  + " '" + size_container + "', "  
                                                  + " '" + valor_usd + "', ";  
     
                                 insertarCustoms += " '" + agente_aduanal + "', "            
                                                  + " '" + pedimento_a1 + "', "              
                                                  + " '" + pedimento_r1_1er + "', "          
                                                  + " '" + motivo_rectificacion_1er + "', "
                                                  + " '" + pedimento_r1_2do + "', "          
                                                  + " '" + motivo_rectificacion_2do + "', ";  
                      //  if(!fecha_recepcion_doc.trim().equals("null")){  
                             if (fecha_recepcion_doc != null && !fecha_recepcion_doc.equals("") && !fecha_recepcion_doc.equalsIgnoreCase("null")) {
                                 insertarCustoms += " TO_DATE('" + fecha_recepcion_doc + "', 'MM/DD/YYYY'), ";     
                               }  else{
                             insertarCustoms +="null,";
                             }       
                                 insertarCustoms += " '" + recinto + "', " 
                                                  + " '" + naviera + "', " 
                                                  + " '" + buque + "', "; 
                       // if(!fecha_revalidacion.trim().equals("null")){   
                              if (fecha_revalidacion != null && !fecha_revalidacion.equals("") && !fecha_revalidacion.equalsIgnoreCase("null")) {
                                 insertarCustoms += " TO_DATE('" + fecha_revalidacion + "', 'MM/DD/YYYY'), ";    
                        }   else{
                             insertarCustoms +="null,";
                             }    
                      //  if(!fecha_previo_origen.trim().equals("null")){
                             if (fecha_previo_origen != null && !fecha_previo_origen.equals("") && !fecha_previo_origen.equalsIgnoreCase("null")) {
                                 insertarCustoms += " TO_DATE('" + fecha_previo_origen + "', 'MM/DD/YYYY'), ";   
                        }   else{
                             insertarCustoms +="null,";
                             } 
                       // if(!fecha_previo_destino.trim().equals("null")){
                             if (fecha_previo_destino != null && !fecha_previo_destino.equals("") && !fecha_previo_destino.equalsIgnoreCase("null")) {
                        
                                insertarCustoms += " TO_DATE('" + fecha_previo_destino + "', 'MM/DD/YYYY'), ";    
                        }  else{
                             insertarCustoms +="null,";
                             } 
                       // if(!fecha_resultado_previo.trim().equals("null")){
                             if (fecha_resultado_previo != null && !fecha_resultado_previo.equals("") && !fecha_resultado_previo.equalsIgnoreCase("null")) {
                        
                                 insertarCustoms += " TO_DATE('" + fecha_resultado_previo + "', 'MM/DD/YYYY'), ";
                        }  else{
                             insertarCustoms +="null,";
                             }      
                       // if(!proforma_final.trim().equals("null")){
                             if (proforma_final != null && !proforma_final.equals("") && !proforma_final.equalsIgnoreCase("null")) {
                        
                                 insertarCustoms += " TO_DATE('" + proforma_final + "', 'MM/DD/YYYY'), "; 
                        }   else{
                             insertarCustoms +="null,";
                             }        
                                 insertarCustoms += " '" + permiso + "', ";   
                       // if(!fecha_envio.trim().equals("null")){ 
                             if (fecha_envio != null && !fecha_envio.equals("") && !fecha_envio.equalsIgnoreCase("null")) {
                                
                                 insertarCustoms += " TO_DATE('" + fecha_envio + "', 'MM/DD/YYYY'), "; 
                        }  else{
                             insertarCustoms +="null,";
                             }         
                      //  if(!fecha_recepcion_perm.trim().equals("null")){
                             if (fecha_recepcion_perm != null && !fecha_recepcion_perm.equals("") && !fecha_recepcion_perm.equalsIgnoreCase("null")) {
                      
                                 insertarCustoms += " TO_DATE('" + fecha_recepcion_perm + "', 'MM/DD/YYYY'), "; 
                        }  else{
                             insertarCustoms +="null,";
                             }    
                        //if(!fecha_activacion_perm.trim().equals("null")){
                             if (fecha_activacion_perm != null && !fecha_activacion_perm.equals("") && !fecha_activacion_perm.equalsIgnoreCase("null")) {
                         
                                 insertarCustoms += " TO_DATE('" + fecha_activacion_perm + "', 'MM/DD/YYYY'), ";
                        }   else{
                             insertarCustoms +="null,";
                             }  
                        //if(!fecha_permisos_aut.trim().equals("null")){
                             if (fecha_permisos_aut != null && !fecha_permisos_aut.equals("") && !fecha_permisos_aut.equalsIgnoreCase("null")) {
                        
                                 insertarCustoms += " TO_DATE('" + fecha_permisos_aut + "', 'MM/DD/YYYY'), ";   
                        }  else{
                             insertarCustoms +="null,";
                             }         
                                 insertarCustoms += " '" + co_pref_arancelaria + "', "       
                                                  + " '" + aplic_pref_arancelaria + "', "    
                                                  + " '" + req_uva + "', "   
                                                  + " '" + req_ca + "', ";   
                      //  if(!fecha_recepcion_ca.trim().equals("null")){ 
                             if (fecha_recepcion_ca != null && !fecha_recepcion_ca.equals("") && !fecha_recepcion_ca.equalsIgnoreCase("null")) {
                                
                                 insertarCustoms += " TO_DATE('" + fecha_recepcion_ca + "', 'MM/DD/YYYY'), ";   
                        }   else{
                             insertarCustoms +="null,";
                             }        
                                 insertarCustoms += " '" + num_constancia_ca + "', "   
                                                  + " '" + monto_ca + "', ";  
                       // if(!fecha_doc_completos.trim().equals("null")){  
                             if (fecha_doc_completos != null && !fecha_doc_completos.equals("") && !fecha_doc_completos.equalsIgnoreCase("null")) {
                             
                                 insertarCustoms += " TO_DATE('" + fecha_doc_completos + "', 'MM/DD/YYYY'), ";    
                        }  else{
                             insertarCustoms +="null,";
                             }         
                       // if(!fecha_pago_pedimento.trim().equals("null")){
                             if (fecha_pago_pedimento != null && !fecha_pago_pedimento.equals("") && !fecha_pago_pedimento.equalsIgnoreCase("null")) {
                       
                                 insertarCustoms += " TO_DATE('" + fecha_pago_pedimento + "', 'MM/DD/YYYY'), ";
                        }  else{
                             insertarCustoms +="null,";
                             }         
                       // if(!fecha_solicitud_transporte.trim().equals("null")){
                             if (fecha_solicitud_transporte != null && !fecha_solicitud_transporte.equals("") && !fecha_solicitud_transporte.equalsIgnoreCase("null")) {
                        
                                 insertarCustoms += " TO_DATE('" + fecha_solicitud_transporte + "', 'MM/DD/YYYY'), ";  
                        } else{
                             insertarCustoms +="null,";
                             } 
                        //if(!fecha_modulacion.trim().equals("null")){
                             if (fecha_modulacion != null && !fecha_modulacion.equals("") && !fecha_modulacion.equalsIgnoreCase("null")) {
                         
                                 insertarCustoms += " TO_DATE('" + fecha_modulacion + "', 'MM/DD/YYYY'), ";   
                        } else{
                             insertarCustoms +="null,";
                             }          
                                 insertarCustoms += " '" + modalidad + "', "                 
                                                  + " '" + resultado_modulacion + "', ";   
                       // if(!fecha_reconocimiento.trim().equals("null")){  
                             if (fecha_reconocimiento != null && !fecha_reconocimiento.equals("") && !fecha_reconocimiento.equalsIgnoreCase("null")) {
                               
                                 insertarCustoms += " TO_DATE('" + fecha_reconocimiento + "', 'MM/DD/YYYY'), ";    
                        } else{
                             insertarCustoms +="null,";
                             }          
                       // if(!fecha_liberacion.trim().equals("null")){
                             if (fecha_liberacion != null && !fecha_liberacion.equals("") && !fecha_liberacion.equalsIgnoreCase("null")) {
                        
                                 insertarCustoms += " TO_DATE('" + fecha_liberacion + "', 'MM/DD/YYYY'), "; 
                        }  else{
                             insertarCustoms +="null,";
                             }         
                                 insertarCustoms += " '" + sello_origen + "', "              
                                                  + " '" + sello_final + "', "; 
                       // if(!fecha_retencion_aut.trim().equals("null")){   
                             if (fecha_retencion_aut != null && !fecha_retencion_aut.equals("") && !fecha_retencion_aut.equalsIgnoreCase("null")) {
                              
                                 insertarCustoms += " TO_DATE('" + fecha_retencion_aut + "', 'MM/DD/YYYY'), ";  
                        }  else{
                             insertarCustoms +="null,";
                             }         
                       // if(!fecha_liberacion_aut.trim().equals("null")){
                             if (fecha_liberacion_aut != null && !fecha_liberacion_aut.equals("") && !fecha_liberacion_aut.equalsIgnoreCase("null")) {
                        
                                 insertarCustoms += " TO_DATE('" + fecha_liberacion_aut + "', 'MM/DD/YYYY'), "; 
                        }  else{
                             insertarCustoms +="null,";
                             }                 
                                 insertarCustoms +=  " '" + estatus_operacion_actual + "', "         
                                                   + " '" + motivo_atraso + "', "             
                                                   + " '" + observaciones + "', "; 
                
                if(idAgenteAduanal.equals("4001")||idAgenteAduanal.equals("4006")){ //LOGIX Y VF

                       // if(!llegada_a_nova.trim().equals("null")){  
                             if (llegada_a_nova != null && !llegada_a_nova.equals("") && !llegada_a_nova.equalsIgnoreCase("null")) {
                              
                                 insertarCustoms += " TO_DATE('" + llegada_a_nova + "', 'MM/DD/YYYY'), ";    
                        }  else{
                             insertarCustoms +="null,";
                             } 
                       // if(!llegada_a_globe_trade_sd.trim().equals("null")){
                             if (llegada_a_globe_trade_sd != null && !llegada_a_globe_trade_sd.equals("") && !llegada_a_globe_trade_sd.equalsIgnoreCase("null")) {
                        
                                 insertarCustoms += " TO_DATE('" + llegada_a_globe_trade_sd + "', 'MM/DD/YYYY'), ";  
                        }   else{
                             insertarCustoms +="null,";
                             }        
                                 insertarCustoms += " '" + archivo_m + "', ";
                       // if(!fecha_archivo_m.trim().equals("null")){   
                             if (fecha_archivo_m != null && !fecha_archivo_m.equals("") && !fecha_archivo_m.equalsIgnoreCase("null")) {
                              
                                 insertarCustoms += " TO_DATE('" + fecha_archivo_m + "', 'MM/DD/YYYY'), ";   
                        }  else{
                             insertarCustoms +="null,";
                             }         
                       // if(!fecha_solicit_manip.trim().equals("null")){
                             if (fecha_solicit_manip != null && !fecha_solicit_manip.equals("") && !fecha_solicit_manip.equalsIgnoreCase("null")) {
                        
                                 insertarCustoms += " TO_DATE('" + fecha_solicit_manip + "', 'MM/DD/YYYY'), ";   
                        }  else{
                             insertarCustoms +="null,";
                             } 
                      //  if(!fecha_vencim_manip.trim().equals("null")){
                             if (fecha_vencim_manip != null && !fecha_vencim_manip.equals("") && !fecha_vencim_manip.equalsIgnoreCase("null")) {
                         
                                 insertarCustoms += " TO_DATE('" + fecha_vencim_manip + "', 'MM/DD/YYYY'), ";    
                        } else{
                             insertarCustoms +="null,";
                             }        
                       // if(!fecha_confirm_clave_pedim.trim().equals("null")){
                             if (fecha_confirm_clave_pedim != null && !fecha_confirm_clave_pedim.equals("") && !fecha_confirm_clave_pedim.equalsIgnoreCase("null")) {
                         
                                 insertarCustoms += " TO_DATE('" + fecha_confirm_clave_pedim + "', 'MM/DD/YYYY'), ";
                        }  else{
                             insertarCustoms +="null,";
                             }     
                      //  if(!fecha_recep_increment.trim().equals("null")){
                             if (fecha_recep_increment != null && !fecha_recep_increment.equals("") && !fecha_recep_increment.equalsIgnoreCase("null")) {
                        
                                 insertarCustoms += " TO_DATE('" + fecha_recep_increment + "', 'MM/DD/YYYY'), "; 
                        }  else{
                             insertarCustoms +="null,";
                             }         
                                 insertarCustoms += " '" + t_e + "', ";
                       // if(!fecha_vencim_inbound.trim().equals("null")){ 
                             if (fecha_vencim_inbound != null && !fecha_vencim_inbound.equals("") && !fecha_vencim_inbound.equalsIgnoreCase("null")) {
                                
                                 insertarCustoms += " TO_DATE('" + fecha_vencim_inbound + "', 'MM/DD/YYYY'), ";    
                        }  else{
                             insertarCustoms +="null,";
                             }         
                }
                
                if(idAgenteAduanal.equals("4002")||idAgenteAduanal.equals("4006")){  //Cusa ó VF
                                 insertarCustoms += " '" + no_bultos + "', "
                                                  + " '" + peso_kg + "', "
                                                  + " '" + transferencia + "', ";
                      //  if(!fecha_inicio_etiquetado.trim().equals("null")){
                             if (fecha_inicio_etiquetado != null && !fecha_inicio_etiquetado.equals("") && !fecha_inicio_etiquetado.equalsIgnoreCase("null")) {
                        
                                 insertarCustoms += " TO_DATE('" + fecha_inicio_etiquetado + "', 'MM/DD/YYYY'), ";  
                        } else{
                             insertarCustoms +="null,";
                             } 
                       // if(!fecha_termino_etiquetado.trim().equals("null")){
                             if (fecha_termino_etiquetado != null && !fecha_termino_etiquetado.equals("") && !fecha_termino_etiquetado.equalsIgnoreCase("null")) {
                        
                                 insertarCustoms += " TO_DATE('" + fecha_termino_etiquetado + "', 'MM/DD/YYYY'), ";    
                        } else{
                             insertarCustoms +="null,";
                             } 
                                 insertarCustoms += " '" + hora_termino_etiquetado + "', "
                                                  + " '" + proveedor + "', "
                                                  + " '" + proveedor_carga + "', "; 
                }        
                
                if(addFechaImportacion){
                                insertarCustoms  += " TO_DATE(SYSDATE, 'DD/MM/YYYY'), ";
                }                
                                insertarCustoms  += " '" + fy + "', "
                                                  + " '" + idAgenteAduanal + "', "
                                                  + " '" + prioridad + "', "
                                                  + " TO_DATE(SYSDATE, 'DD/MM/YYYY'), "    //INSERTAR DEFAULT        
                                                  + " '20', "
                                                  + " '" + UserId + "') ";  
                                  System.out.println("else3"+insertarCustoms);
            }      
            
                  System.out.println("insertarCustoms"+insertarCustoms);
                    boolean oraOut1 = oraDB.execute(insertarCustoms);  
                
                
                    //Actualización de estatus operación y fecha:
                    regPrioridad = " UPDATE TRA_INC_GTN_TEST SET "
                                 + " ESTATUS='" + estatus_operacion_actual + "', ";
                    
                if(addFechaImportacion){    
                   regPrioridad += " FECHA_IMPORTACION = TO_DATE(SYSDATE, 'DD/MM/YYYY'), ";
                }             
                
                   regPrioridad += " PRIORIDAD = '"+ prioridad +"' "
                                 + " WHERE SHIPMENT_ID = '" + shipmentId + "'";
                   
                   boolean oraOut2 = oraDB.execute(regPrioridad);
                
                    
        /************************************* Proceso para activar semaforo /*************************************/
       
              //  if(!eta_port_discharge.trim().equals("null")){ 
                      if (eta_port_discharge != null && !eta_port_discharge.equals("") && !eta_port_discharge.equalsIgnoreCase("null")) {
                    
                    // create SimpleDateFormat object with source string date format: Tratamiento 1
                    SimpleDateFormat sdfSource2 = new SimpleDateFormat("yyyy-MM-dd"); 
            
                    // create SimpleDateFormat object with desired date format:       Tratamiento 2
                    SimpleDateFormat sdfDestination2 = new SimpleDateFormat("MM/dd/yyyy");

                    String[] par = eta_port_discharge.split("/");
                    int month = Integer.parseInt(par[0]);
                    int day = Integer.parseInt(par[1]);
                    int year = Integer.parseInt(par[2]);
                    String eta_port_dischargeSystem = "";

                    /* CONSULTAR (FECHA INICIAL/FECHA FINAL/LOAD TYPE/ESTATUS SEMAFORO) */
                    String data = obj.dataSemaforo(month, day, year, loadTypeFinal, prioridad);

                    /* SPLIT VARIABLE DATA */
                    String[] parts = data.split("@");
                    //2023-10-24  @  2023-11-08       @11 @3 @8 @10 @11
                    String f1 = parts[0];  
                    String f2 = parts[1];
                    String dias_total_despacho = parts[2]; 
                    String estatus_semaforo = parts[3]; 
                    String diasLimitePrioridadBaja = parts[4];     /*Días Limite Semaforo (Verde)*/    
                    String diasLimitePrioridadMedia = parts[5];    /*Días Limite Semaforo (Amarillo)*/  
                    String diasLimitePrioridadAlta = parts[6];     /*Días Limite Semaforo (Rojo)*/  
                 
                    Date dateFec1 = sdfSource2.parse(f1);                                                           //parse the string into Date object
                    String fecha_inicial = sdfDestination2.format(dateFec1); 
                    
                    Date dateFec2 = sdfSource2.parse(f2);                                                           //parse the string into Date object
                    String fecha_final = sdfDestination2.format(dateFec2); 

                    /* Consultar Fecha Eta Port Discharge */
                    if (db.doDB(fac.consultarFechaSemaforo(shipmentId))) {
                        for (String[] rowF : db.getResultado()) {
                           eta_port_dischargeSystem =  rowF[0];
                        }
                    }

                    if (!eta_port_discharge.equals(eta_port_dischargeSystem)) {

                        insertarSemaforo = " UPDATE TRA_INB_SEMAFORO SET "
                                         + " DIAS_TRANSCURRIDOS = 1, "
                                         + " LOAD_TYPE_FINAL = '" + loadTypeFinal + "', "
                                         + " ESTATUS_SEMAFORO = '" + estatus_semaforo + "', " 
                                         + " FECHA_ACTIVACION = TO_DATE('" + eta_port_discharge + "', 'MM/DD/YYYY'), " 
                                         + " FECHA_TERMINO = TO_DATE('" + fecha_final + "', 'MM/DD/YYYY'), "
                                         + " DIAS_CALCULADOS = '" + dias_total_despacho + "', "
                                         + " DAY_LIMIT_GREEN = '" + diasLimitePrioridadBaja + "', "
                                         + " DAY_LIMIT_YELLOW = '" + diasLimitePrioridadMedia + "', "
                                         + " DAY_LIMIT_RED = '" + diasLimitePrioridadAlta + "' "
                                         + " WHERE SHIPMENT_ID = '" + shipmentId + "' "
                                         + " AND AGENTE_ID = '" + idAgenteAduanal + "' "; //idAgenteAduanal
                    }else{

                        insertarSemaforo = " INSERT INTO TRA_INB_SEMAFORO "
                                         + " (REG_ID, "
                                         + " EVENTO_ID, "
                                         + " SHIPMENT_ID, "
                                         + " CONTAINER_ID, "
                                         + " AGENTE_ID, "
                                         + " LOAD_TYPE_FINAL, "
                                         + " ESTATUS_SEMAFORO, "
                                         + " FECHA_ACTIVACION, "
                                         + " FECHA_TERMINO, "
                                         + " DIAS_CALCULADOS, "
                                         + " DAY_LIMIT_GREEN, "
                                         + " DAY_LIMIT_YELLOW, "
                                         + " DAY_LIMIT_RED, "
                                         + " DIAS_TRANSCURRIDOS) "
                                         + " VALUES "
                                         + "(NULL, "
                                         + " '" + evento + "', "
                                         + " '" + shipmentId + "', "
                                         + " '" + containerId + "', "
                                         + " '" + idAgenteAduanal + "', "
                                         + " '" + loadTypeFinal + "', "
                                         + " '" + estatus_semaforo + "', "
                                         + " TO_DATE('" + fecha_inicial + "', 'MM/DD/YYYY'), "
                                         + " TO_DATE('" + fecha_final + "', 'MM/DD/YYYY'), "
                                         + " '" + dias_total_despacho + "', "
                                         + " '" + diasLimitePrioridadBaja + "', "
                                         + " '" + diasLimitePrioridadMedia + "', "
                                         + " '" + diasLimitePrioridadAlta + "', "
                                         + " 0) ";
                    }

                    boolean oraOut4 = oraDB.execute(insertarSemaforo); 
                    
                    if(oraOut4){
                        customs = " UPDATE TRA_INB_CUSTOMS SET "
                                + " ETA_PORT_OF_DISCHARGE = TO_DATE('" + eta_port_discharge + "', 'MM/DD/YYYY'), "
                                + " ESTATUS_SEMAFORO = '" + estatus_semaforo + "', "
                                + " ESTADO_ID = 1 "
                                + " WHERE SHIPMENT_ID = '" + shipmentId + "' "
                                + " AND AGENTE_ADUANAL_ID = '" + idAgenteAduanal + "' ";
                        boolean ouraOut5 = oraDB.execute(customs);
                        
                        System.out.println("Actualización de Shipment Id (customs/semaforo): " + shipmentId + ":" + ouraOut5);
                    }
                    salida = estatus_semaforo; //Registro Semaforo
                    
                 }else{
                    
                    if (db.doDB(fac.consultarColorSemaforo(shipmentId))) {
                        for (String[] rowO : db.getResultado()) {
                            salida = rowO[0];  
                        }
                    } 
                   
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
