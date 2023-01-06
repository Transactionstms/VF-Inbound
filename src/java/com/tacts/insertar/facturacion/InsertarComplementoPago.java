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
import com.tacts.evidencias.facturacion.Email;
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
 * @author luis_
 */
public class InsertarComplementoPago extends HttpServlet {

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
      
        try {    
            
            //Parametros generales:
            String serie = request.getParameter("serie");
            String folio = request.getParameter("folio");
            String tipoEmision = request.getParameter("tipoEmision");
            
            String cliente_id = request.getParameter("cliente_id");
            String montoPago = request.getParameter("montoPago");
            String moneda_id = request.getParameter("moneda_id");
            String fecha_emision = request.getParameter("fecha_emision");
            String hora_emision = request.getParameter("hora_emision");
            String rfcCtaOrdenante = request.getParameter("ctaOrdenante");
            String formapago = request.getParameter("formapago");
            String relcfdiPag = request.getParameter("relcfdiPag");
            String fecha = request.getParameter("fechaPago");
            String rfcCtaBeneficiarias = request.getParameter("ctaBeneficiarias");
            String numOperacion = request.getParameter("numOperacion");
            String regimenFiscal = request.getParameter("regimenFiscal");
            String horaEmision = request.getParameter("horaEmision");
            String totalGlobal = request.getParameter("totalGlobal");   
            String fechaPago = "";
            String moneda_desc = "";
            
            // create SimpleDateFormat object with source string date format: Tratamiento 1
            SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd"); 

            // create SimpleDateFormat object with desired date format:       Tratamiento 2
            SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy, hh:mm");
            //SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy");
                    
            // parse the string into Date object
            Date date = sdfSource.parse(fecha);  
            
            // parse the date into another format
            fechaPago = sdfDestination.format(date);
            
            //Parametros - bancarios:regimenFiscal
            String nom_banco_ord_ext = "Banamex";       
            String cta_ordenante = "2367147337";                
            String cta_beneficiario = "062180113410080795"; 
            
            String folio_complemento = "";
            String clvFactID = "";
            
            //Parametros - cliente:
            String rfc = "";
            String metodo_id = "";
            String forma_id = "";
            String uso_cfdi = "";
            String regimen_id = "";
            String numCuenta = "";
            String salida = "";
            boolean oraOut3 = true;
            
            String contadorFacturasPorPagar = request.getParameter("contadorFacturasPorPagar");
            int numFacturasPorPagar = Integer.parseInt(contadorFacturasPorPagar);
            
            if (db.doDB(fac.consultarFolioComplemento(cve))) {
                for (String[] rowCP : db.getResultado()) {
                     folio_complemento = rowCP[0];
                }
            }
            
            if (db.doDB(fac.consultarCuenta(cve))) {
                for (String[] rowCP : db.getResultado()) {
                     numCuenta = rowCP[0];
                }
            }
            
            String numFolioComplemento = "UPDATE BROKER_CUENTA SET SECUENCIA_COMPLEMENTO = '" + folio_complemento + "' WHERE CUENTA_ID = '" + numCuenta + "'";
            boolean oraOut = oraDB.execute(numFolioComplemento);
                                
            //Extraer datos clientes:
            if (db.doDB(fac.dataSusCliente(cliente_id, cve))) {
                for (String[] rowCP : db.getResultado()) {
                     rfc = rowCP[0];
                     metodo_id = rowCP[1];
                     forma_id = rowCP[2];
                     uso_cfdi = rowCP[3];
                     regimen_id = rowCP[4];
                }
            }
            
            //Extraer descripción de moneda:
            if (db.doDB(fac.consultarMonedaDescripcion(moneda_id))) {
                for (String[] rowm : db.getResultado()) {
                     moneda_desc = rowm[0];
                }
            }
            
              String insertarFE = " INSERT INTO TRA_FACTURACION "
                                + " (CLVFACT_ID, "
                                + " CLIENTE_ID, "
                                + " RFC, "
                                + " USOCFDI_ID, "
                                + " TIPOENVIO_ID, "
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
                                + " REL_CONCEPTO_ID, "        //--->SECUENCIA FIJA PARA OBTENER CONCEPTO DE DPP
                                + " REL_COMPLEMENTO_ID, "     //--->SECUENCIA COMPLEMENTO
                                + " SUBTOTAL, "               //Subtotal
                                + " TOTAL_GLOBAL, "           //Total
                                + " ESTADO_ID, "
                                + " CBDIV_ID) "
                                + "VALUES "
                                + " (NULL, "
                                + " '" + cliente_id + "', "
                                + " '" + rfc + "', "
                                + " '" + uso_cfdi + "', "
                                + " 1, "
                                + " 1, "
                                + " TO_DATE('" + fecha_emision + "', 'dd/mm/yyyy HH24:MI:SS'), "
                                + " '" + hora_emision + "', "
                                + " '" + serie + "', "
                                + " '" + folio + "', "
                                + " '1', "
                                + " '1', "
                                + " '" + regimen_id + "', "
                                + " '" + metodo_id + "', "
                                + " '" + forma_id + "', "
                                + " '" + moneda_id + "', "
                                + " 1, "
                                + " 0, "                                    //--->SECUENCIA FIJA PARA OBTENER CONCEPTO DE DPP
                                + " '" + folio_complemento + "', "          //--->SECUENCIA COMPLEMENTO DE PAGO
                                + " '" + totalGlobal + "', "                //Importe total del pago a facturar (pago parcial)
                                + " '" + totalGlobal + "', "                //Importe total del pago a facturar (pago parcial)              
                                + " '" + tipoEmision + "', "                //Estatus 1: No Timbrado -- Estatus 2: Si Timbrado
                                + " '" + cve + "') ";
            boolean oraOut1 = oraDB.execute(insertarFE);
            
            
            String pago = " INSERT INTO TRA_PAGO "
                        + " (REGP_ID, "                  
                        + " FECHA_PAGO, "        
                        + " FORMA_DE_PAGOP, "    
                        + " MONEDAP, "           
                        + " MONTO, "             
                        + " RFC_EMISOR_CTA_ORD, "
                        + " NOM_BANCO_ORD_EXT, " 
                        + " CTA_ORDENANTE, "     
                        + " RFC_EMISOR_CTA_BEN, "
                        + " CTA_BENEFICIARIO, "   
                        + " ESTADO_ID, "         
                        + " CBDIV_ID, "          
                        + " REL_COMPLEMENTO_ID, "
                        + " NUM_OPERACION, "
                        + " TIPOCAMBIOP) "                        
                        + " VALUES "
                        + " (NULL, "
                        + " TO_DATE('" + fechaPago + "', 'dd/mm/yyyy HH24:MI:SS'), "
                        + " '" + formapago + "', "
                        + " '" + moneda_desc + "', "
                        + " '" + montoPago + "', "                              //Importe total de la(s) factura(s) a relacionar
                        + " '" + rfcCtaOrdenante + "', "
                        + " '" + nom_banco_ord_ext + "', "
                        + " '" + cta_ordenante + "', "
                        + " '" + rfcCtaBeneficiarias + "', "
                        + " '" + cta_beneficiario + "', "
                        + " '" + tipoEmision + "', "                            //Estatus 1: No Timbrado -- Estatus 2: Si Timbrado
                        + " '" + cve + "', "                                               //cve
                        + " '" + folio_complemento + "', "
                        + " '" + numOperacion + "', "                           //--->SECUENCIA COMPLEMENTO DE PAGO
                        + " 1) ";
            boolean oraOut2 = oraDB.execute(pago); 
            
            for (int i = 0; i <numFacturasPorPagar; i++) {
                
                String id_documento = request.getParameter("id_documento[" + i + "]");      
                String serieFactOld = request.getParameter("serieFactOld[" + i + "]");            
                String folioFactOld = request.getParameter("folioFactOld[" + i + "]");            
                String moneda_dr = request.getParameter("moneda_dr[" + i + "]");       
                String metodo_de_pago_dr = request.getParameter("metodo_de_pago_dr[" + i + "]");
                String numParcialidad = request.getParameter("numParcialidad[" + i + "]");  
                String saldoAnterior = request.getParameter("saldoAnterior[" + i + "]");    
                String saldoPagado = request.getParameter("saldoPagado[" + i + "]");   
                String saldoPendiente = request.getParameter("saldoPendiente[" + i + "]"); 
                String importeSaldoInsoluto = request.getParameter("importeSaldoInsoluto[" + i + "]");
                String equivalenciaDr = request.getParameter("equivalenciaDr[" + i + "]");     
                String ObjetoImpDr = request.getParameter("ObjetoImpDr[" + i + "]");     
                
                 String docRelacionados = " INSERT INTO TRA_DOCUMENTOS_RELACIONADOS "
                                        + " (REGD_ID, "
                                        + " ID_DOCUMENTO, "
                                        + " SERIE, "
                                        + " FOLIO, "
                                        + " MONEDA_DR, "
                                        + " METODO_DE_PAGO_DR, "
                                        + " NUM_PARCIALIDAD, "
                                        + " IMP_SALDO_ANT, "
                                        + " IMP_PAGADO, "
                                        + " IMP_SALDO_INSOLUTO, "
                                        + " ESTADO_ID, "
                                        + " CBDIV_ID, "
                                        + " REL_COMPLEMENTO_ID,"
                                        + " EQUIVALENCIADR, "
                                        + " OBJETOIMPDR) "
                                        + " VALUES "
                                        + " (NULL, "
                                        + " '" + id_documento + "', "
                                        + " '" + serieFactOld + "', "
                                        + " '" + folioFactOld + "', "
                                        + " '" + moneda_dr + "', "
                                        + " '" + metodo_de_pago_dr + "', "
                                        + " '" + numParcialidad + "', "
                                        + " '" + saldoAnterior + "', "
                                        + " '" + saldoPagado + "', "
                                        + " '" + importeSaldoInsoluto + "', "
                                        +  " '" + tipoEmision + "', "           //Estatus 1: No Timbrado -- Estatus 2: Si Timbrado
                                        + " '" + cve + "', "                               //cve
                                        + " '" + folio_complemento + "', "
                                        + " '" + equivalenciaDr + "', "
                                        + " '" + ObjetoImpDr + "')";
                 oraOut3 = oraDB.execute(docRelacionados); 
            }
            
            if(oraOut && oraOut1 && oraOut2 && oraOut3){ //Inserciones: ---> Consecutivo / Facturación / Tra pago / Relación de documentos
                if (db.doDB(fac.FolioFactura(serie, folio, cve))) {  
                    for (String[] rowF : db.getResultado()) {
                        clvFactID = rowF[0];
                    }
                }
                salida = clvFactID + "*" + tipoEmision;
            }else{
                salida = "0*0";
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
