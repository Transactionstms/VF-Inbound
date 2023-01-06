/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.insertar.facturacion;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.consultas.ConsultasQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
 * @author luis_
 */
public class InsertarFacturacionPOS extends HttpServlet {

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
            ConsultasQuery fac = new ConsultasQuery();
            NumberFormat formatter = new DecimalFormat("#0.00");    
            //Parametros - cliente
            String num_ticket = request.getParameter("num_ticket");
            String carameloTicket = request.getParameter("carameloTicket");
            String salida = "";        
            
            System.out.println(carameloTicket);
            try {

                //Parametros generales:
                String tipoEmision = request.getParameter("tipoEmision"); 
                String usocfdi_id = request.getParameter("usocfdi_id");
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
                String codigo_postal = request.getParameter("codigo_postal");
                String condiciones_pago = request.getParameter("condiciones_pago");
                String moneda_id = request.getParameter("moneda_id");
                String cambio = request.getParameter("tipo_cambio");
                String existenciaCliente = request.getParameter("existenciaCliente");
                
                
                String rfc = request.getParameter("rfc");
                String razon_social = request.getParameter("razon_social");
                String correo = request.getParameter("correo");
                
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

                //Contadores Facturación:            
                String contConcept = request.getParameter("numConceptos");
                int numConcept = Integer.parseInt(contConcept);

                String clvFactID = "";
                String tipo_cambio = "";
                String comprobante_id = "";
                String folio_concepto = "";
                String folio_relimpuesto = "";
                String folio_relpedimento = "";
                String folio_relpartes = "";
                String folio_relcfdi = "";
                String folio_relcom = "";
                String addCliente = "";
                String numCuenta = "";
                String cliente_id = "";
                String regimen_desc = "";
                
                //Exepciones temporales:
                if (cambio == null) {
                    tipo_cambio = "500";
                }

                if (comprobante.equals("3")) {
                    comprobante_id = "1";
                } else {
                    comprobante_id = comprobante;
                }

                //CONSULTA DE SECUENCIAS: (Num Factura | Num Concepto | Num impuesto | Num pedimento | Num Partes | Num relación cfdi | Num relación comercial | Num Carta Porte)
                if (db.doDB(fac.consultarFolioConcepto(cve))) { 
                    for (String[] rowC : db.getResultado()) {
                        folio_concepto = rowC[0];
                    }
                }

                if (db.doDB(fac.consultarFolioImpuesto(cve))) {
                    for (String[] rowI : db.getResultado()) {
                        folio_relimpuesto = rowI[0];
                    }
                }
                
                if (db.doDB(fac.consultarFolioPedimento(cve))) {
                    for (String[] rowP : db.getResultado()) {
                         folio_relpedimento = rowP[0];
                    }
                }

                if (db.doDB(fac.consultarFolioParte(cve))) {
                    for (String[] rowPR : db.getResultado()) {
                         folio_relpartes = rowPR[0];
                    }
                }

                if (db.doDB(fac.consultarFolioRelcfdi(cve))) {
                    for (String[] rowRf : db.getResultado()) {
                         folio_relcfdi = rowRf[0];
                    }
                }

                if (db.doDB(fac.consultarFolioRelComercial(cve))) {
                    for (String[] rowRC : db.getResultado()) {
                         folio_relcom = rowRC[0];
                    }
                }

                if (db.doDB(fac.consultarCuenta(cve))) {
                    for (String[] rowCP : db.getResultado()) {
                        numCuenta = rowCP[0];
                    }
                }
                
                //SECCIÓN: ACTUALIZACIÓN DE SECUENCIAS
                String numFactura = "UPDATE TRA_SERIES_FOLIOS SET SECUENCIA_FOLIO = '" + folio + "' WHERE PREFIJO_SERIE = '" + serie + "' AND CBDIV_ID = '" + cve + "'";
                boolean oraOut0 = oraDB.execute(numFactura);

                String numConcepto = "UPDATE BROKER_CUENTA SET SECUENCIA_CONCEPTO = '" + folio_concepto + "' WHERE CUENTA_ID = '" + numCuenta + "'";
                boolean oraOut1 = oraDB.execute(numConcepto);

                String numImpuesto = "UPDATE BROKER_CUENTA SET SECUENCIA_REL_IMPUESTO = '" + folio_relimpuesto + "' WHERE CUENTA_ID = '" + numCuenta + "'";
                boolean oraOut2 = oraDB.execute(numImpuesto);
                
                
                //Extraer descripción del regimen fiscal del cliente
                if (db.doDB(fac.consultarDescRegimenFiscalSusCliente(regimen_id))) {
                    for (String[] rowRC : db.getResultado()) {
                         regimen_desc = rowRC[0];
                    }
                }

                if(existenciaCliente.equals("0")){
                    addCliente = " INSERT INTO TRA_SUSCLIENTES "
                               + " (CLIENTE_ID, "
                               + " CLIENTE_DESCRIPCION, "
                               + " RFC, "
                               + " CORREO_CONTACTO1, "
                               + " ESTATUS_ID, "
                               + " CBDIV_ID, "
                               + " EMAIL_XML, "
                               + " EMAIL_PDF,"
                               + " USOCFDI_ID, "
                               + " METODO_ID, "
                               + " FORMA_ID, "
                               + " REGIMENFISCALRECEPTOR,"
                               + " DOMICILIOFISCALRECEPTOR, "
                               + " CODIGO_POSTAL) "
                               + " VALUES "
                               + " (NULL, "
                               + " '" + razon_social + "', "
                               + " '" + rfc + "', "
                               + " '" + correo + "', "
                               + " 1, "
                               + " '" + cve + "', "
                               + " 1, "
                               + " 1,"
                               + " '" + usocfdi_id + "', "
                               + " '" + metodo_id + "', "
                               + " '" + forma_id + "', "
                               + " '" + regimen_desc + "', "
                               + " '" + codigo_postal + "', "
                               + " '" + codigo_postal + "') ";
                }else{
                    addCliente = " UPDATE TRA_SUSCLIENTES "
                               + " SET "
                               + " CLIENTE_DESCRIPCION = '" + razon_social + "', "
                               + " CORREO_CONTACTO1 = '" + correo + "', "
                               + " USOCFDI_ID = '" + usocfdi_id + "',"
                               + " METODO_ID = '" + metodo_id + "', "
                               + " FORMA_ID = '" + forma_id + "', "
                               + " REGIMENFISCALRECEPTOR = '" + regimen_desc + "', "
                               + " DOMICILIOFISCALRECEPTOR = '" + codigo_postal + "', "
                               + " CODIGO_POSTAL = '" + codigo_postal + "' "
                               + " WHERE RFC = '" + rfc + "' "
                               + " AND CBDIV_ID = '" + cve + "'";
                }
                
                boolean oraOut7_1 = oraDB.execute(addCliente);
                
                if (oraOut7_1) {
                    if (db.doDB(fac.consultaridCliente(rfc, cve))) {
                        for (String[] rowCl : db.getResultado()) {
                            cliente_id = rowCl[0];
                        }
                    }
                }

                //SECCIÓN: FACTURACIÓN 
              String insertarFE = " INSERT INTO TRA_FACTURACION "
                                + " (CLVFACT_ID, "
                                + " CLIENTE_ID, "
                                + " RFC, "
                                + " USOCFDI_ID, "
                                + " TIPO_DESCRIPCION, "
                                + " FECHA_EMISION, "
                                + " HORA_EMISION, "
                                + " SERIE, "
                                + " FOLIO, "
                                + " COMPROBANTE_ID, "
                                + " DOCUMENTO_ID, "
                                + " REGIMEN_ID, "
                                + " METODO_ID, "
                                + " FORMA_ID, "
                                + " MONEDA_ID, "
                                + " TIPO_CAMBIO, "
                                + " REL_CONCEPTO_ID, " //--->SECUENCIA
                                + " REL_CLVCFDI_ID, "
                                + " REL_CLVCOM_ID, "
                                + " SUBTOTAL, " //Subtotal
                                + " TOTAL_GLOBAL, " //Total
                                + " ESTADO_ID, "
                                + " CBDIV_ID, "
                                + " NUM_TICKET, "
                                + " VERSION_XML) "
                                + "VALUES "
                                + " (NULL, "
                                + " '" + cliente_id + "', "
                                + " '" + rfc + "', "
                                + " '" + usocfdi_id + "', "
                                + " '" + tipo_descripcion + "', "
                                + " TO_DATE('" + fecha_emision + "', 'dd/mm/yyyy HH24:MI:SS'), "
                                + " '" + hora_emision + "', "
                                + " '" + serie + "', "
                                + " '" + folio + "', "
                                + " '" + comprobante_id + "', "
                                + " '" + documento_id + "', "
                                + " '" + regimen_id + "', "
                                + " '" + metodo_id + "', "
                                + " '" + forma_id + "', "
                                + " '" + moneda_id + "', "
                                + " 1, "
                                + " '" + folio_concepto + "', " //--->SECUENCIA
                                + " '" + folio_relcfdi + "', "
                                + " '" + folio_relcom + "', "
                                + " '" + subtotal_global + "', " //vacio
                                + " '" + total_global + "', " //vacio           
                                + " '" + tipoEmision + "', "
                                + " '" + cve + "', "
                                + " '" + num_ticket + "', "
                                + " '3.3') ";
                boolean oraOut7 = oraDB.execute(insertarFE);
                
                //SECCIÓN: RELACIÓN CONCEPTOS 
                if (numConcept > 0) {

                    for (int i = 0; i < numConcept; i++) {

                        //String comentarios = request.getParameter("comentarios[" + i + "]");
                        String rel_clvsat_id = request.getParameter("rel_clvsat_id[" + i + "]");
                        String unidad_sat_id = request.getParameter("unidad_sat_id[" + i + "]");
                        String concepto_id = request.getParameter("concepto_id[" + i + "]");
                        String concepto_desc = request.getParameter("concepto_desc[" + i + "]");
                        String cantidad = request.getParameter("cantidad[" + i + "]");
                        String precio_unitario = request.getParameter("precio_unitario[" + i + "]");
                        String importe_descuento = request.getParameter("importe_descuento[" + i + "]");
                        String importe = request.getParameter("importe[" + i + "]");

                        String insertarRC = " INSERT INTO TRA_REL_CONCEPTOS "
                                        + " (REL_CONCEP_ID, "
                                        + " REL_CONCEPTO_ID, " //--->SECUENCIA
                                        + " CONCEPTO_ID, "
                                        + " DESCRIPCION_CONCEPTO, "
                                        + " CANTIDAD, "
                                        + " REL_CLVSAT_ID, "
                                        + " UNIDAD_SAT_ID, "
                                        + " PRECIO_UNITARIO, "
                                        + " IMPORTE_DESCUENTO, "
                                        + " IMPORTE, "
                                        + " NUM_IDENTIFICADOR, "
                                        + " REL_IMPUESTO_ID, " //--->SECUENCIA
                                        + " REL_PEDIMENTO_ID, " //--->SECUENCIA
                                        + " REL_PARTE_ID, " //--->SECUENCIA
                                        + " ESTADO_ID, "
                                        + " CBDIV_ID) "
                                        + " VALUES "
                                        + " (NULL, "
                                        + " '" + folio_concepto + "', " //--->SECUENCIA
                                        + " '" + concepto_id + "', "
                                        + " '" + concepto_desc + "', "
                                        + " '" + cantidad + "', "
                                        + " '" + rel_clvsat_id + "', "
                                        + " '" + unidad_sat_id + "', "
                                        + " '" + precio_unitario + "', "
                                        + " '" + importe_descuento + "', "
                                        + " '" + importe + "', "
                                        + " 'PRD', "
                                        + " '" + folio_relimpuesto + "', " //--->SECUENCIA
                                        + " '" + folio_relpedimento + "', " //--->SECUENCIA
                                        + " '" + folio_relpartes + "', " //--->SECUENCIA
                                        + " 1,"
                                        + " '" + cve + "') ";
                        boolean oraOut8 = oraDB.execute(insertarRC);
                    }
                }

                //SECCIÓN: IMPUESTOS   
                String up_tipoImpuesto = "1"; 
                String up_impuesto = "2";
                String up_tipoFactor = "1";
                String up_tasa = "5";
                String up_cuota = "0.160000";
                         
                float up_subtotal = 0;
                float up_iva = 0;    
                float up_total = 0;  
                
                up_subtotal = Float.parseFloat(total_global) / 1.16f;
                up_iva =      Float.parseFloat(total_global) - up_subtotal; 
                up_total = up_subtotal + up_iva;
                        
                String insertarImpuesto = "INSERT INTO TRA_REL_IMPUESTOS "
                                        + " (REL_IMP_ID, "
                                        + " TIPO_IMPUESTO, "
                                        + " BASE, " //Tvalor unitario X cantidad
                                        + " IMPUESTO_ID, " //Traslados
                                        + " TIPO_FACTOR, " //tra_tipo_tasa = 1
                                        + " TASA_ID, "
                                        + " CUOTA, " //0.160000
                                        + " IMPORTE, "
                                        + " REL_IMPUESTO_ID, " //--->SECUENCIA
                                        + " CBDIV_ID) "
                                        + "VALUES "
                                        + " (NULL,"
                                        + " '" + up_tipoImpuesto + "', "
                                        + " '" + formatter.format(up_subtotal) + "', "       //Subtotal
                                        + " '" + up_impuesto + "', "
                                        + " '" + up_tipoFactor + "', "
                                        + " '" + up_tasa + "', "
                                        + " '" + up_cuota + "', "
                                        + " '" + formatter.format(up_iva) + "', "            //Iva
                                        + " '" + folio_relimpuesto + "', " //--->SECUENCIA
                                        + " '" + cve + "') ";
               boolean oraOut9 = oraDB.execute(insertarImpuesto);

                if (oraOut7) { //Inserción: Facturación
                    if (db.doDB(fac.FolioFactura(serie, folio, cve))) {
                        for (String[] rowF : db.getResultado()) {
                            clvFactID = rowF[0];
                        }
                    }
                    salida = clvFactID; //idRegistro|tipo timbrado
                } else {
                    salida = "0";                         //idRegistro|tipo timbrado
                }

                out.print(salida);
                System.out.println("ID DE REGISTRO: " + salida);
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
            Logger.getLogger(InsertarFacturacionPOS.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InsertarFacturacionPOS.class.getName()).log(Level.SEVERE, null, ex);
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
    }
}
