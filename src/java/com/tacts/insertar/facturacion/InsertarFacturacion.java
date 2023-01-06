/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.insertar.facturacion;

import com.dao.ServiceDAO;
import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.consultas.ConsultasQuery;
import com.tacts.evidencias.facturacion.Email;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis_
 */
public class InsertarFacturacion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
            String UserId = (String) ownsession.getAttribute("login.user_id_number");
            String cve = (String) ownsession.getAttribute("cbdivcuenta");

            OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
            oraDB.connect(dbData.getUser(), dbData.getPassword());
      
        try {    
            
            Date date = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fecha_registro = formato.format(date);
            
            Email correo = new Email();
            ConsultasQuery fac = new ConsultasQuery();
            
            //Parametros generales:
            String tipoEmision = request.getParameter("tipoEmision");
            String cliente_id = request.getParameter("cliente_id");
            String rfc = request.getParameter("rfc");
            String usocfdi_id = request.getParameter("usocfdi_id");
            String correos = request.getParameter("correos");
            String tipo_descripcion = request.getParameter("tipo_descripcion");
            String fecha_emision = request.getParameter("fecha_emision");
            String hora_emision = request.getParameter("hora_emision");
            String serie = request.getParameter("serie");
            String folio = request.getParameter("folio");
            String comprobante = request.getParameter("comprobante_id");
            String documento_id = request.getParameter("documento_id");
            String regimen_id = request.getParameter("regimen_id");
            String metodo_id = request.getParameter("metodo_id");
            String forma_id = request.getParameter("forma_id");
            String condiciones_pago = request.getParameter("condiciones_pago");
            String moneda_id = request.getParameter("moneda_id");
            String cambio = request.getParameter("tipo_cambio");
            
            String num_orden = request.getParameter("num_orden1");
            String num_proveedor = request.getParameter("num_proveedor1");
            String info_observaciones = request.getParameter("info_observaciones1");

            //Parametros nuevos (TRA_FACTURACION):                  
            String subtotal_global = request.getParameter("cant_subtotal_gral");   
            String importe_descuento_global = request.getParameter("cant_descuento_gral"); 
            String traslados_global = request.getParameter("cant_traslados_gral");  
            String retenciones_global = request.getParameter("cant_retenciones_gral");                
            String total_global = request.getParameter("cant_total_gral");                         
              
            String impuesto_global = request.getParameter("cant_impuesto_gral"); 
            String tipo_factor_global = request.getParameter("cant_factor_gral");                             
            String tasa_global = request.getParameter("cant_tasa_gral");      
            String cuota_global = request.getParameter("cant_cuota_gral");  
            String tipo_impuesto_global = request.getParameter("tipo_impueto_gral"); 
  
            //Controles
            String xml_sts = request.getParameter("xml_sts");
            String pdf_sts = request.getParameter("pdf_sts");
            String addFact_sts = request.getParameter("addFact_sts");

            //Contadores Facturación:            
            
            //String contImpuestos = request.getParameter("numImpuestos");
            //String contPedimentos = request.getParameter("numPedimentos");
            //String contPartes = request.getParameter("numPartes");
            //String contUUID = request.getParameter("numUUID");
            //String contCartaPorte = request.getParameter("numCartaPorte");
            int numConcept = 0; 
            //int numImpuestos = Integer.parseInt(contImpuestos);
            //int numPedimentos = Integer.parseInt(contPedimentos);
            //int numPartes = Integer.parseInt(contPartes);
            //int numUUID = Integer.parseInt(contUUID);
            int numCartaPorte = 0;
            
           
            String clvFactID = "";
            String tipo_cambio = "";
            String comprobante_id = "";
            String folioFactura = serie + "" + folio;
            String folio_concepto = "";
            String folio_relpedimento = "";
            String folio_relpartes = "";
            String folio_relcfdi = "";
            String folio_relcom = "";
            String folio_cartaporte = "";
            String numCuenta = "";
            String cpFederalEmisor = "";
            String estadoFederalEmisor = "";
            
            //Variables Globales: Impuestos
            int folioImpuestoContador = 0;
            String up_tipoImpuesto = "0";
            String up_base = "0";
            String up_impuesto = "0";
            String up_tipoFactor = "0";
            String up_tasa = "0";
            String up_cuota = "0";
            String up_importe = "0";
            String insertarImpuesto = "";
            
            boolean oraOut9 = true;
            String salida = "";
            
            //Exepciones temporales:
            if(cambio == null){
                tipo_cambio = "500";
            }
            
            if(comprobante.equals("3")){
                comprobante_id = "1";
            }else{
                comprobante_id = comprobante;
            }
            
            
            /*EXTRAER FOLIO-FINAL: CONCEPTO*/
            if (db.doDB(fac.consultarFolioConcepto(cve))) {
                for (String[] rowC : db.getResultado()) {
                     folio_concepto = rowC[0];
                }
            }
            /*EXTRAER FOLIO-FINAL: PEDIMENTO*/
            if (db.doDB(fac.consultarFolioPedimento(cve))) {
                for (String[] rowP : db.getResultado()) {
                     folio_relpedimento = rowP[0];
                }
            }
            /*EXTRAER FOLIO-FINAL: PARTES*/
            if (db.doDB(fac.consultarFolioParte(cve))) {
                for (String[] rowPR : db.getResultado()) {
                     folio_relpartes = rowPR[0];
                }
            }
            /*EXTRAER FOLIO-FINAL: RELACION DE CFDI*/
            if (db.doDB(fac.consultarFolioRelcfdi(cve))) {
                for (String[] rowRf : db.getResultado()) {
                     folio_relcfdi = rowRf[0];
                }
            }
            /*EXTRAER FOLIO-FINAL: INFORMACION COMERCIAL*/
            if (db.doDB(fac.consultarFolioRelComercial(cve))) {
                for (String[] rowRC : db.getResultado()) {
                     folio_relcom = rowRC[0];
                }
            }
            /*EXTRAER FOLIO-FINAL: CARTA PORTE*/
            if (db.doDB(fac.consultarFolioCartaPorte(cve))) {
                for (String[] rowCP : db.getResultado()) {
                     folio_cartaporte = rowCP[0];
                }
            }
            /*EXTRAER CUENTA LIGA A LA DIVISION*/
            if (db.doDB(fac.consultarCuenta(cve))) {
                for (String[] rowCP : db.getResultado()) {
                     numCuenta = rowCP[0];
                }
            }
            
            /*ACTUALIZAR PREFIJO Y SERIE DE LA FACTURACIÓN*/
            String numFactura = "UPDATE TRA_SERIES_FOLIOS SET SECUENCIA_FOLIO = '" + folio + "' WHERE PREFIJO_SERIE = '" + serie + "' AND CBDIV_ID = '" + cve + "'";
            boolean oraOut0 = oraDB.execute(numFactura);
            /*ACTUALIZAR FOLIO-FINAL: CONCEPTO*/
            String numConcepto = "UPDATE BROKER_CUENTA SET SECUENCIA_CONCEPTO = '" + folio_concepto + "' WHERE CUENTA_ID = '" + numCuenta + "'";
            boolean oraOut1 = oraDB.execute(numConcepto);
            /*ACTUALIZAR FOLIO-FINAL: PEDIMENTO*/
            String numPedimento = "UPDATE BROKER_CUENTA SET SECUENCIA_REL_PEDIMENTO = '" + folio_relpedimento + "' WHERE CUENTA_ID = '" + numCuenta + "'";
            boolean oraOut3 = oraDB.execute(numPedimento);
            /*ACTUALIZAR FOLIO-FINAL: PARTES*/
            String numParte = "UPDATE BROKER_CUENTA SET SECUENCIA_REL_PARTES = '" + folio_relpartes + "' WHERE CUENTA_ID = '" + numCuenta + "'";
            boolean oraOut4 = oraDB.execute(numParte);
            /*ACTUALIZAR FOLIO-FINAL: RELACIÓN CFDI*/
            String numRelCfdi = "UPDATE BROKER_CUENTA SET SECUENCIA_REL_CFDI = '" + folio_relcfdi + "' WHERE CUENTA_ID = '" + numCuenta + "'";
            boolean oraOut5 = oraDB.execute(numRelCfdi);
            /*ACTUALIZAR FOLIO-FINAL: INFORMACIÓN COMERCIAL*/
            String numRelComercial = "UPDATE BROKER_CUENTA SET SECUENCIA_REL_COMERCIAL = '" + folio_relcom + "' WHERE CUENTA_ID = '" + numCuenta + "'";
            boolean oraOut6 = oraDB.execute(numRelComercial);
            /*ACTUALIZAR FOLIO-FINAL: CARTA PORTE*/
            String numFolioCartaPorte = "UPDATE BROKER_CUENTA SET SECUENCIA_CARTA_PORTE = '" + folio_cartaporte + "' WHERE CUENTA_ID = '" + numCuenta + "'";
            boolean oraOut6_1 = oraDB.execute(numFolioCartaPorte);
            
            
            /*OBTENER CÓDIGO POSTAL/ESTADO FEDERATIVO POR MEDIO DEL CLIENTE ID*/
            if (db.doDB(fac.consultarEstadoCp(cve))) {
                for (String[] rowE : db.getResultado()) {
                     cpFederalEmisor = rowE[0];     //CÓDIGO POSTAL (ENTIDAD FEDERATIVA)
                     estadoFederalEmisor = rowE[1]; //ESTADO        (ENTIDAD FEDERATIVA)
                }
            }
            String hrActual = timeZone(estadoFederalEmisor, hora_emision); 
            System.out.println("Zona Horaria Actual (Estado): " + estadoFederalEmisor+ " (Hora): " + hrActual);
            
            
            //SECCIÓN: FACTURACIÓN 
              String insertarFE = " UPDATE TRA_FACTURACION SET"
                                + " CLIENTE_ID = '" + cliente_id + "', "
                                + " RFC = '" + rfc + "', "
                                + " USOCFDI_ID = '" + usocfdi_id + "', "
                                + " TIPOENVIO_ID = '" + xml_sts + "', "
                                + " TIPO_DESCRIPCION = '" + tipo_descripcion + "', "
                                + " FECHA_EMISION = TO_DATE('" + fecha_emision + "', 'dd/mm/yyyy HH24:MI:SS'), "
                                + " HORA_EMISION = '" + hrActual + "', "
                                + " SERIE = '" + serie + "', "
                                + " FOLIO = '" + folio + "', "
                                + " COMPROBANTE_ID = '" + comprobante_id + "', "
                                + " DOCUMENTO_ID = '" + documento_id + "', "
                                + " REGIMEN_ID = '" + regimen_id + "', "
                                + " METODO_ID = '" + metodo_id + "', "
                                + " FORMA_ID = '" + forma_id + "', "
                                + " CONDICIONES_PAGO = '" + condiciones_pago + "', "
                                + " MONEDA_ID = '" + moneda_id + "', "
                                + " TIPO_CAMBIO = 1, "
                                + " REL_CONCEPTO_ID = '" + folio_concepto + "', " //--->SECUENCIA
                                + " REL_CLVCFDI_ID = '" + folio_relcfdi + "', "
                                + " REL_CLVCOM_ID = '" + folio_relcom + "', "
                                + " SUBTOTAL = '" + subtotal_global + "', "           //Subtotal
                                + " TOTAL_GLOBAL = '" + total_global + "', ";       //Total
                if (numCartaPorte > 0) {
                    insertarFE += " REL_CARTAPORTE_ID = '" + folio_cartaporte + "', ";
                }
                    insertarFE += " VERSION_XML = 3.3, "
                                + " ESTADO_ID = '" + tipoEmision + "' "
                                + " WHERE SERIE='" + serie + "' "
                                + " AND FOLIO = '" + folio + "' "
                                + " AND CBDIV_ID ='" + cve + "' ";
            boolean oraOut7 = oraDB.execute(insertarFE);
            
            
            /*ACTUALIZAR LOS ESTATUS DE LOS CONCEPTOS, QUE TIENE LA FACTURA EMITIDA (TABLA: APP_ESTADO)*/
            String estatusConcepto = "UPDATE TRA_REL_CONCEPTOS SET ESTADO_ID = '" + tipoEmision + "' WHERE REL_CONCEPTO_ID = '" + folio_concepto + "' AND CBDIV_ID = '" + cve + "' ";
            boolean oraOutCon = oraDB.execute(estatusConcepto);
 
            
            /*ITERAR CONSECUTIVO DEL IMPUESTO POR CONCEPTO, PARA ACTUALIZAR EN LA D.B */
            if (db.doDB(fac.consultarFolioImpuesto3_3(cve))) {  //Consultar folio actual
                for (String[] rowI : db.getResultado()) {
                    folioImpuestoContador = Integer.parseInt(rowI[0]);
                }
            }
            
            if (db.doDB(fac.consultarTotalConceptos(folio_concepto, cve))) {
                for (String[] rowF : db.getResultado()) {
                    numConcept = Integer.parseInt(rowF[0]); 
                }
            }
            
            if(numConcept>0){
                for (int i = 0; i < numConcept; i++) {
                    
                    folioImpuestoContador++;
                    
                    /*ACTUALIZAR EL CONSECUTIVO DEL IMPUESTO*/
                    String numImpuesto = "UPDATE BROKER_CUENTA SET SECUENCIA_REL_IMPUESTO = '" + folioImpuestoContador + "' WHERE CUENTA_ID = '" + numCuenta + "'";
                    boolean oraOut2 = oraDB.execute(numImpuesto);    
                    
                    /*ACTUALIZAR LOS ESTATUS DE LOS IMPUESTOS, QUE TIENE LA FACTURA EMITIDA (TABLA: APP_ESTADO)*/
                    String estatusImpuesto = "UPDATE TRA_REL_IMPUESTOS SET ESTADO_ID = '" + tipoEmision + "' WHERE REL_IMPUESTO_ID = '" + folioImpuestoContador + "' AND CBDIV_ID = '" + cve + "' ";
                    boolean oraOutImp = oraDB.execute(estatusImpuesto);
                    
                }
            }
            
    
    //SECCIÓN: PEDIMENTOS/OTROS 
            /*if(numPedimentos>0){
                
                for (int i = 0; i < numPedimentos; i++) {
                    String up_cuentaPredial = request.getParameter("up_cuentaPredial[" + i + "]");
                    String up_numeroPedimento = request.getParameter("up_numeroPedimento[" + i + "]"); 

                    //Tabla TRA_REL_PEDIMENTOS
                    String insertarPedimento = " INSERT INTO TRA_REL_PEDIMENTOS "
                                             + " (REL_PED_ID, "
                                             + " CUENTA_PREDIAL, "
                                             + " NUM_PEDIMENTO,  "
                                             + " REL_PEDIMENTO_ID, " //---> SECUENCIA
                                             + " CBDIV_ID) "
                                             + " VALUES "
                                             + " (NULL,"
                                             + " '" + up_cuentaPredial + "', "
                                             + " '" + up_numeroPedimento + "', "
                                             + " '" + folio_relpedimento + "', "  //--->SECUENCIA
                                             + " '" + cve + "') ";
                    boolean oraOut10 = oraDB.execute(insertarPedimento);
                }
            }*/
            
     //SECCIÓN: PARTES  
            /*if(numPartes>0){
                
                for (int i = 0; i < numPartes; i++) {
                    String part_concepto_id = request.getParameter("parte_concepto_id[" + i + "]");  
                    String upParte_desc = request.getParameter("upParte_desc[" + i + "]");
                    String upParte_Cantidad = request.getParameter("upParte_Cantidad[" + i + "]");
                    String upParte_precioUnitario = request.getParameter("upParte_precioUnitario[" + i + "]");
                    String upParte_ClaveProdServ = request.getParameter("upParte_ClaveProdServ[" + i + "]");
                    String upParte_unidad = request.getParameter("upParte_unidad[" + i + "]");
                    String upParte_NoIdentificacion= request.getParameter("upParte_NoIdentificacion[" + i + "]");	

                    //Tabla TRA_REL_PARTES
                    String insertarPartes= "INSERT INTO TRA_REL_PARTES " 
                                         + "(REL_PAR_ID, "
                                         + " CANTIDAD, "
                                         + " PRECIO_UNITARIO, "
                                         + " REL_CLVSAT_ID, "
                                         + " UNIDAD_SAT_ID, "
                                         + " NUM_IDENTIFICADOR, "
                                         + " REL_PARTE_ID, "            //--->SECUENCIA
                                         + " CBDIV_ID) "
                                         + "VALUES "
                                         + " (NULL,"
                                         + " '" + upParte_Cantidad + "', "
                                         + " '" + upParte_precioUnitario + "', "
                                         + " '" + upParte_ClaveProdServ + "', "
                                         + " '" + upParte_unidad + "', "
                                         + " '" + upParte_NoIdentificacion + "', "
                                         + " '" + folio_relpartes + "', "     //--->SECUENCIA
                                         + " '" + cve + "')";
                    boolean oraOut11 = oraDB.execute(insertarPartes);
                }
            }*/
            
    //SECCIÓN: RELACIÓN CFDI    
            /*if(numUUID>0){
                 
                for (int i = 0; i < numUUID; i++) {
                    String tiporelacion_id = request.getParameter("tiporelacion_id[" + i + "]");
                    String uuid_id = request.getParameter("uuid_id[" + i + "]");
                    String total_cfdi = request.getParameter("total_cfdi[" + i + "]");

                    //Tabla TRA_REL_CFDI
                    String insertarRCF = "INSERT INTO TRA_REL_CFDI "
                                        + " (REL_CLV_ID, "
                                        + " REL_CLVCFDI_ID, "                    //--->SECUENCIA
                                        + " TIPO_REL_ID, "
                                        + " UUID_ID, "
                                        + " TOTAL_CFDI, "
                                        + " ESTADO_ID, "
                                        + " CBDIV_ID) "
                                        + " VALUES "
                                        + " (NULL, "
                                        + " '" + folio_relcfdi + "', "     //--->SECUENCIA
                                        + " '" + tiporelacion_id + "', "
                                        + " '" + uuid_id + "', "
                                        + " '" + total_cfdi + "', "
                                        + " 1, "
                                        + " '"+ cve +"') ";
                    boolean oraOut12 = oraDB.execute(insertarRCF);
                }
            }*/
            
    //SECCIÓN: RELACIÓN COMERCIAL
           /*if(num_orden != null){ 
            
                String insertarRCM = " INSERT INTO TRA_REL_COMERCIAL "
                                    + " (REL_COM_ID, "
                                    + " REL_CLVCOM_ID, "
                                    + " NUM_ORDEN, "
                                    + " NUM_PROVEEDOR, "
                                    + " OBSERVACIONES, "
                                    + " ESTADO_ID, "
                                    + " CBDIV_ID) "
                                    + " VALUES "
                                    + " (NULL, "
                                    + " '" + folio_relcom + "', "
                                    + " '" + num_orden + "', "
                                    + " '" + num_proveedor + "', "
                                    + " '" + info_observaciones + "', "
                                    + " 1,"
                                    + " '"+ cve +"') ";
                boolean oraOut13 = oraDB.execute(insertarRCM);
           }*/
        
    //SECCIÓN: CARTA PORTE 
        /*if(numCartaPorte>0){
            
            float distancia2 =0;
            float distancia3 =0;
            float distanciaTotalRecorrida = 0;
            
            //Parametros: Lugar de Embarque
            String check_lugarEmbarque = request.getParameter("check_lugarEmbarque");
            String lugarDeEmbarque = request.getParameter("lugarDeEmbarque");
            
            String lugEmb_nombre = request.getParameter("lugEmb_nombre");
            String lugEmb_rfc = request.getParameter("lugEmb_rfc");
            String lugEmb_calle = request.getParameter("lugEmb_calle");	
            String lugEmb_noInterior = request.getParameter("lugEmb_noInterior");	
            String lugEmb_noExterior = request.getParameter("lugEmb_noExterior");	
            String lugEmb_codigoPostal = request.getParameter("lugEmb_codigoPostal");	
            String lugEmb_estado = request.getParameter("lugEmb_estado");                
            String lugEmb_municipio = request.getParameter("lugEmb_municipio");	        
            String lugEmb_colonia = request.getParameter("lugEmb_colonia");	        
            String lugEmb_referencia = request.getParameter("lugEmb_referencia");	
            String fechaOrigen = request.getParameter("lugEmb_fechaHoraSalidaLlegada");
            String lugEmb_localidad = request.getParameter("lugEmb_localidad");   
            String lugEmb_idUbicacion = request.getParameter("lugEmb_idUbicacion");        
      
      
            
            //Parametros: Lugar de Destino
            String check_lugarDestinatario = request.getParameter("check_lugarDestinatario");
            String lugarDestinatario = request.getParameter("lugarDestinatario");
            
            String dest_nombre = request.getParameter("dest_nombre");	
            String dest_rfc = request.getParameter("dest_rfc");	
            String dest_calle = request.getParameter("dest_calle");	
            String dest_noInterior = request.getParameter("dest_noInterior");	
            String dest_noExterior = request.getParameter("dest_noExterior");	
            String dest_codigoPostal = request.getParameter("dest_codigoPostal");	
            String dest_estado = request.getParameter("dest_estado");	                 
            String dest_municipio = request.getParameter("dest_municipio");	        
            String dest_colonia = request.getParameter("dest_colonia");	                
            String dest_referencia = request.getParameter("dest_referencia");	 
            String fechaDestino = request.getParameter("dest_fechaHoraSalidaLlegada");		
            String dest_distancia = request.getParameter("dest_distancia");
            String dest_localidad = request.getParameter("dest_localidad");   
            String dest_idUbicacion = request.getParameter("dest_idUbicacion");  
            
            
            
            //Parametros: Lugar de Entrega
            String check_lugarEntrega = request.getParameter("check_lugarEntrega");
            String lugarEntrega = request.getParameter("lugarEntrega");
            
            String lugEnt_contacto = request.getParameter("lugEnt_contacto");	
            String lugEnt_nombre = request.getParameter("lugEnt_nombre");
            String lugEnt_rfc = request.getParameter("lugEnt_rfc");	
            String lugEnt_calle = request.getParameter("lugEnt_calle");	
            String lugEnt_noInterior = request.getParameter("lugEnt_noInterior");	
            String lugEnt_noExterior = request.getParameter("lugEnt_noExterior");	
            String lugEnt_codigoPostal = request.getParameter("lugEnt_codigoPostal");	
            String lugEnt_estado = request.getParameter("lugEnt_estado");	          
            String lugEnt_municipio = request.getParameter("lugEnt_municipio");	           
            String lugEnt_colonia = request.getParameter("lugEnt_colonia");	          
            String lugEnt_referencia = request.getParameter("lugEnt_referencia");
            String lugEnt_pais = request.getParameter("lugEnt_pais");	         // ------> revisar nomeclatura - pais          
            String lugEnt_tipoTransporte = request.getParameter("lugEnt_tipoTransporte");	
            String fechaEntrega = request.getParameter("lugEnt_fechaHoraSalidaLlegada");	
            String lugEnt_distancia = request.getParameter("lugEnt_distancia");
            String lugEnt_localidad = request.getParameter("lugEnt_localidad");  
            String lugEnt_idUbicacion = request.getParameter("lugEnt_idUbicacion");       
            
            //Casteo de fechas  ********
            String lugEmb_fechaHoraSalidaLlegada = "";
            String dest_fechaHoraSalidaLlegada = "";
            String lugEnt_fechaHoraSalidaLlegada = "";
            
            // create SimpleDateFormat object with source string date format: Tratamiento 1
            SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd"); 

            // create SimpleDateFormat object with desired date format:       Tratamiento 2
            SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy, hh:mm");
            //SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy");
                    
            // parse the string into Date object
            Date date1 = sdfSource.parse(fechaOrigen);     
            Date date2 = sdfSource.parse(fechaDestino);  
            Date date3 = sdfSource.parse(fechaEntrega);  
            
            // parse the date into another format
            lugEmb_fechaHoraSalidaLlegada = sdfDestination.format(date1);
            dest_fechaHoraSalidaLlegada = sdfDestination.format(date2);
            lugEnt_fechaHoraSalidaLlegada = sdfDestination.format(date3);
            
            //Casteo de Distancias ********
            if(dest_distancia == null){
                distancia2 = 0;
            }else{
                distancia2 = Float.parseFloat(dest_distancia);
            }
            
            if(lugEnt_distancia == null){
                distancia3 = 0;
            }else{
                distancia3 = Float.parseFloat(lugEnt_distancia);
            }
            
            distanciaTotalRecorrida = distancia2 + distancia3;
            
            //Parametros: Mercancía
            String unidad_peso_cp = request.getParameter("unidad_peso_cp");
            //String mercancia_peso_cp = request.getParameter("mercancia_peso_cp");
            String mercancia_es_peligroso_cp = request.getParameter("mercancia_es_peligroso_cp");
            String numpedimento_cp = request.getParameter("numpedimento_cp");
            String moneda_cp = request.getParameter("moneda_cp");                        
            
            
            //Parametros: Autotransporte
            String perm_sct = request.getParameter("perm_sct");                  
            String no_permismo_sct = request.getParameter("no_permismo_sct");    
            String config_vehicular = request.getParameter("config_vehicular");  
            String placa_vm = request.getParameter("placa_vm");
            String anio_modelovm = request.getParameter("anio_modelovm");
            String aseguradora_civil = request.getParameter("aseguradora_civil");
            String poliza_civil = request.getParameter("poliza_civil");
            String tipo_figura = request.getParameter("tipo_figura");            
            String rfc_figura = request.getParameter("rfc_figura");
            String num_licencia = request.getParameter("num_licencia");
            String nombre_figura = request.getParameter("nombre_figura");
            String placa_remolque = request.getParameter("placa_remolque");
            String subtipo_remolque = request.getParameter("tipo_remolque");  
           
            String autChofer_calle = request.getParameter("autChofer_calle");  
            String autChofer_numero_exterior = request.getParameter("autChofer_noInterior"); 
            String autChofer_numero_interior = request.getParameter("autChofer_noExterior"); 
            String autChofer_colonia = request.getParameter("autChofer_colonia"); 
            String autChofer_localidad = request.getParameter("autChofer_localidad");       
            String autChofer_municipio = request.getParameter("autChofer_municipio");       
            String autChofer_referencia = request.getParameter("autChofer_referencia");      
            String autChofer_estado = request.getParameter("autChofer_estado");         
            String autChofer_codigo_postal = request.getParameter("autChofer_codigoPostal");   
            
            String tra_carta_porte = " INSERT INTO TRA_CARTA_PORTE"
                                   + " (CARTAP_ID, "
                                   + " TOTAL_DISTREC, "
                                   + " VERSION, "
                                   + " TRANSP_INTERNAC, "
                                   + " ENTRADA_SALIDAMERC, "
                                   + " PAIS_ORIGENDESTINO, "
                                   + " VIA_ENTRADA_SALIDA, "
                                   + " REL_CARTAPORTE_ID, "
                                   + " REL_UBIC_ID, "
                                   + " CBDIV_ID, "
                                   + " REL_MERCANCIAS, "
                                   + " REL_TFIG_ID)" 
                                   + " values"
                                   + " (NULL, "
                                   + " '" + distanciaTotalRecorrida + "', "            //Total distancia recorrdida
                                   + " '2.0', "                                 //verión  
                                   + " '" + lugEnt_tipoTransporte + "', "       //Envío internacional
                                   + " 'Salida', "                              //Entrada o Salida
                                   + " '" + lugEnt_pais + "', "                 //Pais origen
                                   + " '01', "                                  //Catálogo = c_CveTransporte
                                   + " '" + folio_cartaporte + "', "
                                   + " '" + folio_cartaporte + "', "
                                   + " '" + cve + "', "
                                   + " '" + folio_cartaporte + "', "
                                   + " '" + folio_cartaporte + "') ";
            boolean oraOut14 = oraDB.execute(tra_carta_porte);
            
        //Ubicación: 1    
        if(lugEmb_nombre != null){
          
            String tra_ubicacion1 = " INSERT INTO TRA_UBICACION"
                                  + " (UBIC_ID, "
                                  + " FECHA_HORA_SALIDA_LLEGADA, "
                                  + " NOMBRE_REMITENTE_DESTINATARIO, "
                                  + " RFC_REMITENTE_DESTINATARIO, "
                                  + " ID_UBICACION, "
                                  + " TIPO_UBICACION, "
                                  + " NUM_REGIDTRIB, "
                                  + " RESIDENCIA_FISCAL,  "
                                  + " DISTANCIA_RECORRIDA, "
                                  + " REL_UBIC_ID, "
                                  + " CBDIV_ID, "
                                  + " REL_DOM_ID) " 
                                  + " values "
                                  + " (NULL, "
                                  + " TO_DATE('" + lugEmb_fechaHoraSalidaLlegada + "', 'dd/mm/yyyy HH24:MI:SS'), "
                                  + " '" + lugEmb_nombre + "', "
                                  + " '" + lugEmb_rfc + "', "
                                  + " '" + lugEmb_idUbicacion + "', "           //id Ubicación
                                  + " 'Origen', "                               //Tipo Ubicación
                                  + " '" + regimen_id + "', "                   //Num Regimen Tributario
                                  + " '" + lugEnt_pais + "', "                  //Residencia Fiscal
                                  + " 0, "                                      //Distancia recorrida
                                  + " '" + folio_cartaporte + "', "             //consecutivo
                                  + " '" + cve + "', "                                     //cve
                                  + " '" + folio_cartaporte + "') ";            //consecutivo
            boolean oraOut15 = oraDB.execute(tra_ubicacion1);
            
            String tra_domicilio1 = " INSERT INTO TRA_DOMICILIO "
                                  + " (DOM_ID, "
                                  + " CODIGO_POSTAL, "
                                  + " PAIS, "
                                  + " ESTADO, "
                                  + " MUNICIPIO, "
                                  + " REFERENCIA, "
                                  + " LOCALIDAD, "
                                  + " COLONIA, "
                                  + " NUMERO_INTERIOR, "
                                  + " NUMERO_EXTERIOR, "
                                  + " CALLE, "
                                  + " REL_DOM_ID, "
                                  + " CBDIV_ID) " 
                                  + " values "
                                  + " (NULL, "
                                  + " '" + lugEmb_codigoPostal + "', "
                                  + " '" + lugEnt_pais + "', "
                                  + " '" + lugEmb_estado + "', "
                                  + " '" + lugEmb_municipio + "', "              
                                  + " '" + lugEmb_referencia + "', "             
                                  + " '" + lugEmb_localidad + "', "              
                                  + " '" + lugEmb_colonia + "', "
                                  + " '" + lugEmb_noInterior + "', "
                                  + " '" + lugEmb_noExterior + "', "
                                  + " '" + lugEmb_calle + "', "
                                  + " '" + folio_cartaporte + "', "
                                  + " '" + cve + "') ";
            boolean oraOut16 = oraDB.execute(tra_domicilio1);
        }    
            
        //Ubicación: 2   
        if(dest_nombre != null){
          
            String tra_ubicacion2 = " INSERT INTO TRA_UBICACION"
                                  + " (UBIC_ID, "
                                  + " FECHA_HORA_SALIDA_LLEGADA, "
                                  + " NOMBRE_REMITENTE_DESTINATARIO, "
                                  + " RFC_REMITENTE_DESTINATARIO, "
                                  + " ID_UBICACION, "
                                  + " TIPO_UBICACION, "
                                  + " NUM_REGIDTRIB, "
                                  + " RESIDENCIA_FISCAL,  "
                                  + " DISTANCIA_RECORRIDA, "
                                  + " REL_UBIC_ID, "
                                  + " CBDIV_ID, "
                                  + " REL_DOM_ID) " 
                                  + " values "
                                  + " (NULL, "
                                  + " TO_DATE('" + dest_fechaHoraSalidaLlegada + "', 'dd/mm/yyyy HH24:MI:SS'), "
                                  + " '" + dest_nombre + "', "
                                  + " '" + dest_rfc + "', "
                                  + " '" + dest_idUbicacion + "', "             //id Ubicación  
                                  + " 'Destino', "                              //Tipo Ubicación
                                  + " '" + regimen_id + "', "                   //Num Regimen Tributario
                                  + " '" + lugEnt_pais + "', "                  //Residencia Fiscal
                                  + " '" + distancia2 + "', "                   //Distancia recorrida
                                  + " '" + folio_cartaporte + "', "             //consecutivo
                                  + " '" + cve + "', "                                     //cve
                                  + " '" + folio_cartaporte + "') ";            //consecutivo
            boolean oraOut17 = oraDB.execute(tra_ubicacion2);
            
            String tra_domicilio2 = " INSERT INTO TRA_DOMICILIO "
                                  + " (DOM_ID, "
                                  + " CODIGO_POSTAL, "
                                  + " PAIS, "
                                  + " ESTADO, "
                                  + " MUNICIPIO, "
                                  + " REFERENCIA, "
                                  + " LOCALIDAD, "
                                  + " COLONIA, "
                                  + " NUMERO_INTERIOR, "
                                  + " NUMERO_EXTERIOR, "
                                  + " CALLE, "
                                  + " REL_DOM_ID, "
                                  + " CBDIV_ID) " 
                                  + " values "
                                  + " (NULL, "
                                  + " '" + dest_codigoPostal + "', "
                                  + " '" + lugEnt_pais + "', "
                                  + " '" + dest_estado + "', "
                                  + " '" + dest_municipio + "', "               //municipio
                                  + " '" + dest_referencia + "', "              //referencia
                                  + " '" + dest_localidad + "', "               //localidad
                                  + " '" + dest_colonia + "', "
                                  + " '" + dest_noInterior + "', "
                                  + " '" + dest_noExterior + "', "
                                  + " '" + dest_calle + "', "
                                  + " '" + folio_cartaporte + "', "
                                  + " '" + cve + "') ";
            boolean oraOut18 = oraDB.execute(tra_domicilio2);
        }  
        
        //Ubicación: 3   
        if(lugEnt_nombre != null){
          
            String tra_ubicacion3 = " INSERT INTO TRA_UBICACION"
                                  + " (UBIC_ID, "
                                  + " FECHA_HORA_SALIDA_LLEGADA, "
                                  + " NOMBRE_REMITENTE_DESTINATARIO, "
                                  + " RFC_REMITENTE_DESTINATARIO, "
                                  + " ID_UBICACION, "
                                  + " TIPO_UBICACION, "
                                  + " NUM_REGIDTRIB, "
                                  + " RESIDENCIA_FISCAL,  "
                                  + " DISTANCIA_RECORRIDA, "
                                  + " REL_UBIC_ID, "
                                  + " CBDIV_ID, "
                                  + " REL_DOM_ID) " 
                                  + " values "
                                  + " (NULL, "
                                  + " TO_DATE('" + lugEnt_fechaHoraSalidaLlegada + "', 'dd/mm/yyyy HH24:MI:SS'), "
                                  + " '" + lugEnt_nombre + "', "
                                  + " '" + lugEnt_rfc + "', "
                                  + " '" + lugEnt_idUbicacion + "', "           //id Ubicación
                                  + " 'Destino', "                              //Tipo Ubicación
                                  + " '" + regimen_id + "', "                   //Num Regimen Tributario
                                  + " '" + lugEnt_pais + "', "                  //Residencia Fiscal
                                  + " '" + distancia3 + "', "                   //Distancia recorrida
                                  + " '" + folio_cartaporte + "', "             //consecutivo
                                  + " '" + cve + "', "                                     //cve
                                  + " '" + folio_cartaporte + "') ";            //consecutivo
            boolean oraOut19 = oraDB.execute(tra_ubicacion3);
            
            String tra_domicilio3 = " INSERT INTO TRA_DOMICILIO "
                                  + " (DOM_ID, "
                                  + " CODIGO_POSTAL, "
                                  + " PAIS, "
                                  + " ESTADO, "
                                  + " MUNICIPIO, "
                                  + " REFERENCIA, "
                                  + " LOCALIDAD, "
                                  + " COLONIA, "
                                  + " NUMERO_INTERIOR, "
                                  + " NUMERO_EXTERIOR, "
                                  + " CALLE, "
                                  + " REL_DOM_ID, "
                                  + " CBDIV_ID) " 
                                  + " values "
                                  + " (NULL, "
                                  + " '" + lugEnt_codigoPostal + "', "
                                  + " '" + lugEnt_pais + "', "
                                  + " '" + lugEnt_estado + "', "
                                  + " '" + lugEnt_municipio + "', "              
                                  + " '" + lugEnt_referencia + "', "             
                                  + " '" + lugEnt_localidad + "', "             
                                  + " '" + lugEnt_colonia + "', "
                                  + " '" + lugEnt_noInterior + "', "
                                  + " '" + lugEnt_noExterior + "', "
                                  + " '" + lugEnt_calle + "', "
                                  + " '" + folio_cartaporte + "', "
                                  + " '" + cve + "') ";
            boolean oraOut20 = oraDB.execute(tra_domicilio3);
        }
         
        float pesoBrutoTotal = 0;
        int numTotalMercancias = 0;
        
            for (int i = 0; i < numCartaPorte; i++) {
                    
                String rel_clvsat_id_cp = request.getParameter("rel_clvsat_id_cp[" + i + "]");
                String unidad_sat_id_cp = request.getParameter("unidad_sat_id_cp[" + i + "]");
                String unidad_sat_desc_cp = request.getParameter("unidad_sat_desc_cp[" + i + "]");
                String concepto_id_cp = request.getParameter("concepto_id_cp[" + i + "]");
                String concepto_desc_cp = request.getParameter("concepto_desc_cp[" + i + "]");
                String cantidad_cp = request.getParameter("cantidad_cp[" + i + "]");
                String precio_unitario_cp = request.getParameter("precio_unitario_cp[" + i + "]");
                String importe_descuento_cp = request.getParameter("importe_descuento_cp[" + i + "]");
                String importe_cp = request.getParameter("importe_cp[" + i + "]"); 
                String no_pedimento_cp = request.getParameter("no_pedimento_cp[" + i + "]"); 
                String unidadMedidaValor = request.getParameter("unidadMedidaValor[" + i + "]"); 

                pesoBrutoTotal += Float.parseFloat(unidadMedidaValor);
                numTotalMercancias ++;
                        
                String tra_mercancia = " INSERT INTO TRA_MERCANCIA "
                                     + " (MERC_ID, "
                                     + " MONEDA, "
                                     + " VALOR_MERCANCIA, "
                                     + " PESOENKG, "
                                     + " UNIDAD, "
                                     + " CLAVE_UNIDAD, "
                                     + " CANTIDAD, "
                                     + " DESCRIPCION, "
                                     + " BIENES_TRANSP, "
                                     + " REL_MERC_ID, "
                                     + " CBDIV_ID) " 
                                     + " values "
                                     + " (NULL, "
                                     + " '" + moneda_cp + "', "                 //moneda
                                     + " '" + precio_unitario_cp + "', "        //valor mercancia
                                     + " '" + unidadMedidaValor + "', "         //Kg, Litros, Metros cubicos
                                     + " '" + unidad_sat_desc_cp + "', "        //CLAVE_UNIDAD_SAT DESCRIPCIÓN
                                     + " '" + unidad_sat_id_cp + "', "          //CLAVE_UNIDAD_SAT       
                                     + " '" + cantidad_cp + "', "
                                     + " '" + concepto_desc_cp + "', "
                                     + " '" + rel_clvsat_id_cp + "', "
                                     + " '" + folio_cartaporte + "', "
                                     + " '" + cve + "') ";
                boolean oraOut22 = oraDB.execute(tra_mercancia); 
            }
            
            String tra_rel_mercancias = " INSERT INTO TRA_REL_MERCANCIAS "
                                      + " (MER_ID, "
                                      + " NUM_TOTAL_MERCANCIAS, "
                                      + " PESO_NETOTOTAL, "
                                      + " UNIDAD_PESO, "
                                      + " PESO_BRUTOTOTAL, "
                                      + " REL_MERC_ID, "
                                      + " REL_PEDP_ID, "
                                      + " REL_CTR_ID, "
                                      + " REL_AUT_ID, "
                                      + " REL_TFIG_ID, "
                                      + " CBDIV_ID, "
                                      + " REL_MERCANCIAS) " 
                                      + " values "
                                      + " (NULL, "
                                      + " '" + numTotalMercancias + "', "               //num total de mercancias
                                      + " '" + pesoBrutoTotal + "', "           //peso neto total
                                      + " '" + unidad_peso_cp + "', "              //unidad peso
                                      + " '" + pesoBrutoTotal + "', "           //peso bruto total
                                      + " '" + folio_cartaporte + "', "
                                      + " '" + folio_cartaporte + "', "
                                      + " '" + folio_cartaporte + "', "
                                      + " '" + folio_cartaporte + "', "
                                      + " '" + folio_cartaporte + "', "
                                      + " '" + cve + "', "
                                      + " '" + folio_cartaporte + "') ";
            boolean oraOut21 = oraDB.execute(tra_rel_mercancias);   
            
            String tra_autotransporte = "INSERT INTO TRA_AUTOTRANSPORTE"
                                      + " (TAU_ID, "
                                      + " NUM_PERMISOSCT, "
                                      + " PERM_SCT, "
                                      + " REL_AUT_ID, "
                                      + " CBDIV_ID, "
                                      + " REL_IDENTV_ID, "
                                      + " REL_SEG_ID, "
                                      + " REL_REM_ID) " 
                                      + " values "
                                      + " (NULL, "
                                      + " '" + no_permismo_sct + "', "            
                                      + " '" + perm_sct + "', "                   
                                      + " '" + folio_cartaporte + "', "
                                      + " '" + cve + "', "
                                      + " '" + folio_cartaporte + "', "
                                      + " '" + folio_cartaporte + "', "
                                      + " '" + folio_cartaporte + "') ";
            boolean oraOut25 = oraDB.execute(tra_autotransporte);
            
            String tra_identificacionVehicular = " INSERT INTO TRA_IDENTIFICACION_VEHICULAR"
                                               + " (TIV_ID, "
                                               + " ANIO_MODELOVM, "
                                               + " PLACAVM, "
                                               + " CONFIG_VEHICULAR, "
                                               + " REL_IDENTV_ID, "
                                               + " CBDIV_ID) " 
                                               + " values "
                                               + " (NULL, "
                                               + " '" + anio_modelovm + "', "
                                               + " '" + placa_vm + "', "
                                               + " '" + config_vehicular + "', "  
                                               + " '" + folio_cartaporte + "', "
                                               + " '" + cve + "') ";
            boolean oraOut26 = oraDB.execute(tra_identificacionVehicular);
            
            String tra_seguros = " INSERT INTO TRA_SEGUROS"
                               + " (SEG_ID, "
                               + " POLIZA_RESPCIVIL, "
                               + " ASEGURA_RESPCIVIL, "
                               + " REL_SEG_ID, "
                               + " CBDIV_ID) " 
                               + " values"
                               + " (NULL, "
                               + " '" + poliza_civil + "', "     //Considerar '-'
                               + " '" + aseguradora_civil + "', "
                               + " '" + folio_cartaporte + "', "
                               + " '" + cve + "') ";
            boolean oraOut27 = oraDB.execute(tra_seguros);
            
            String tra_remolque = " INSERT INTO TRA_REMOLQUE"
                                + " (REM_ID, "
                                + " PLACA, "
                                + " SUB_TIPOREM, "
                                + " REL_REM_ID, "
                                + " CBDIV_ID) " 
                                + " values "
                                + " (NULL, "
                                + " '" + placa_remolque + "', "
                                + " '" + subtipo_remolque + "', "    
                                + " '" + folio_cartaporte + "', "
                                + " '" + cve + "') ";
            boolean oraOut28 = oraDB.execute(tra_remolque);
            
            String tra_tipos_figuras = " INSERT INTO TRA_TIPOS_FIGURA"
                                     + " (TTF_ID, "
                                     + " NOMBRE_FIGURA, "
                                     + " NUM_LICENCIA, "
                                     + " RFC_FIGURA, "
                                     + " TIPO_FIGURA, "
                                     + " REL_TFIG_ID, "
                                     + "CALLE,           "
                                     + "NUMERO_EXTERIOR, "
                                     + "NUMERO_INTERIOR, "
                                     + "COLONIA,         "
                                     + "LOCALIDAD,       "
                                     + "MUNICIPIO,       "
                                     + "REFERENCIA,      "
                                     + "ESTADO,          "
                                     + "CODIGO_POSTAL,   "
                                     + " CBDIV_ID) " 
                                     + " values "
                                     + " (NULL, "
                                     + " '" + nombre_figura + "', "
                                     + " '" + num_licencia + "', "
                                     + " '" + rfc_figura + "', "
                                     + " '" + tipo_figura + "', "       
                                     + " '" + folio_cartaporte + "', "
                                     
                                     + " '" + autChofer_calle + "', "
                                     + " '" + autChofer_numero_exterior + "', "
                                     + " '" + autChofer_numero_interior + "', "
                                     + " '" + autChofer_colonia + "', "
                                     + " '" + autChofer_localidad + "', "
                                     + " '" + autChofer_municipio + "', "
                                     + " '" + autChofer_referencia + "', "
                                     + " '" + autChofer_estado + "', "
                                     + " '" + autChofer_codigo_postal + "', "
                    
                                     + " '" + cve + "') ";
            boolean oraOut29 = oraDB.execute(tra_tipos_figuras);
             
        }*/
        
            if (oraOut7){ //Inserción: Facturación
                if (db.doDB(fac.FolioFactura(serie, folio, cve))) {  
                    for (String[] rowF : db.getResultado()) {
                        clvFactID = rowF[0];
                    }
                }
                salida = clvFactID + "*" + tipoEmision; //idRegistro|tipo timbrado
            }else{
                salida = "0*0";                         //idRegistro|tipo timbrado
            }

            out.print(salida);
            oraDB.close();
            
          } catch (Exception e) {
                out.println("Error " + e.toString());
            } finally {
                oraDB.close();
            }
            
        } catch (NullPointerException e) {
            out.println(e.toString());
            out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            out.println("<script>window.close();</script>");
        }
    }
    
    private String timeZone(String estado, String horarEmision) {
        
        String difTime="";
        int hr =0;
        int hrs=0;
        
        if (estado.equals("AGU")) { //Aguascalientes
            hrs = 0;
        } else if (estado.equals("BCN")) { //Baja California Norte 
            hrs = 2; 
        } else if (estado.equals("BCS")) { //Baja California Sur
            hrs = 1;
        } else if (estado.equals("CAM")) { //Campeche
            hrs = 0;
        } else if (estado.equals("CHH")) { //Chiapas
            hrs = 0;
        } else if (estado.equals("CHP")) { //Chihuahua
            hrs = 1;
        } else if (estado.equals("COA")) { //Coahuila
            hrs = 0;
        } else if (estado.equals("COL")) { //Colima
            hrs = 0;
        } else if (estado.equals("DIF")) { //Ciudad de México
            hrs = 0;
        } else if (estado.equals("DUR")) { //Durango
            hrs = 0;
        } else if (estado.equals("GRO")) { //Guerrero
            hrs = 0;
        } else if (estado.equals("GUA")) { //Guanajuato
            hrs = 0;
        } else if (estado.equals("HID")) { //Hidalgo
            hrs = 0;
        } else if (estado.equals("JAL")) { //Jalisco
            hrs = 0;
        } else if (estado.equals("MEX")) { //México
            hrs = 0;
        } else if (estado.equals("MIC")) { //Michoacán
            hrs = 0;
        } else if (estado.equals("MOR")) { //Morelos
            hrs = 0;
        } else if (estado.equals("NAY")) { //Nayarit
            hrs = 1;
        } else if (estado.equals("NLE")) { //Nuevo León
            hrs = 0;
        } else if (estado.equals("OAX")) { //Oaxaca
            hrs = 0;
        } else if (estado.equals("PUE")) { //Puebla
            hrs = 0;
        } else if (estado.equals("QUE")) { //Querétaro
            hrs = 0;
        } else if (estado.equals("ROO")) { //Quintana Roo
            hrs = 0;
        } else if (estado.equals("SIN")) { //Sinaloa
            hrs = 1;
        } else if (estado.equals("SLP")) { //San Luis Potosí
            hrs = 0;
        } else if (estado.equals("SON")) { //Sonora
            hrs = 1;
        } else if (estado.equals("TAB")) { //Tabasco
            hrs = 0;  
        } else if (estado.equals("TAM")) { //Tamaulipas
            hrs = 0;
        } else if (estado.equals("TLA")) { //Tlaxcala
            hrs = 0;
        } else if (estado.equals("VER")) { //Veracruz
            hrs = 0;
        } else if (estado.equals("YUC")) { //Yucatán
            hrs = 0;
        } else if (estado.equals("ZAC")) { //Zacatecas
            hrs = 0;
        }
        
        String[] par = horarEmision.split(":");
        int hh = Integer.parseInt(par[0]); //Hora
        String mm = par[1];                //Minutos
        String ss = par[2];                //Segundos
        
        hr = hh-hrs; //Extracción de hr (zona horaria)
        
        if(hr>=1 & hr<=9){
            difTime = String.valueOf("0"+hr) + ":" + mm + ":" + ss;
        }else{
            difTime = String.valueOf(hr) + ":" + mm + ":" + ss;
        }
        
        return difTime;
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
            Logger.getLogger(InsertarFacturacion.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InsertarFacturacion.class.getName()).log(Level.SEVERE, null, ex);
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
