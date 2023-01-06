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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
public class InsertarConceptos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession ownsession = request.getSession();
        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
        DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        String cve = (String) ownsession.getAttribute("cbdivcuenta");
        String UserId = (String) ownsession.getAttribute("login.user_id_number");
        oraDB.connect(dbData.getUser(), dbData.getPassword());
        ConsultasQuery fac = new ConsultasQuery();
        NumberFormat formatter = new DecimalFormat("#0.00"); 
        
        try (PrintWriter out = response.getWriter()) {
        
        String tipoArmado = request.getParameter("tipoArmado"); 
        String serie = request.getParameter("serie"); 
        String folio = request.getParameter("folio"); 
        
        //Sección: General    
        String comentarios = request.getParameter("comentarios"); 
        String rel_clvsat_id = request.getParameter("rel_clvsat_id"); 
        String unidad_sat_clave = request.getParameter("unidad_sat_id");  //enviar id a consulta para extraer id del regisgro
        String codigo_concepto = request.getParameter("codigo_concepto");  
        float cantidad = Float.parseFloat(request.getParameter("cantidad")); 
        String precio_unitario = request.getParameter("precio_unitario"); 
        String importe_descuento = request.getParameter("importe_descuento"); 
        String importe = request.getParameter("importe"); 
        String tipoConcepto = "";
        String concepto_desc = "";
        String unidad_sat_id = "";
        String folio_concepto = "";
        String folio_relpedimento = "";
        String folio_relpartes = "";
        String check = "";
        String caramelo = "";
        String salida = "";
        String insertarRC = "";
        
        float iva16 = 1.16f;
        float subtotalUnitario = 0;
        float subtotalGral = 0;
        
        float cantidadExist = 0;
        float cantidadTotal = 0;
        
        int folioImpuestoContador = Integer.parseInt(request.getParameter("contImpuestoConcepto"));   //contador por concepto                  //Contador de registros total
        
        /*******************************************************************************************************************************************/ 
        
            /*EXTRAER PRE-FOLIO: CONCEPTO*/
            if (db.doDB(fac.consultarFolioConcepto(cve))) {
                for (String[] rowC : db.getResultado()) {
                     folio_concepto = rowC[0];
                }
            }

            /*EXTRAER PRE-FOLIO: PEDIMENTO*/
            if (db.doDB(fac.consultarFolioPedimento(cve))) {
                for (String[] rowP : db.getResultado()) {
                     folio_relpedimento = rowP[0];
                }
            }

            /*EXTRAER PRE-FOLIO: PARTES*/
            if (db.doDB(fac.consultarFolioParte(cve))) {
                for (String[] rowPR : db.getResultado()) {
                     folio_relpartes = rowPR[0];
                }
            }

        /*******************************************************************************************************************************************/

            /*EXTRAER ID DE LA CLAVE UNIDAD DE MEDIDA SAT*/
            if (db.doDB(fac.idClaveUnidadSatFact(unidad_sat_clave))) {
                for (String[] rowC : db.getResultado()) {
                    unidad_sat_id = rowC[0];
                }
            }

            /*EXTRAER DESCRIPCION DEL PRODUCTO POR MEDIO DEL ID*/
            if (db.doDB(fac.consultarConceptoDesc(codigo_concepto, cve))) {
                for (String[] rowC : db.getResultado()) {
                    concepto_desc = rowC[0];
                }
            }

            /*EXTRAER TIPO DE CONCEPTO (PRODUCTO-SERVICIO)*/
            if (db.doDB(fac.consultarTipoConcepto(cve,codigo_concepto))) {
                for (String[] rowC : db.getResultado()) {
                    tipoConcepto = rowC[0];
                }
            }

            /*IDENTIFICAR TIPO DE CONCEPTO PARA APLICAR EL IVA CORRESPONDIENTE DEPENDIENDO DE SU TASA ID*/
            if(tipoConcepto.equals("1")){   //(1=PRODUCTO)
                subtotalUnitario = Float.parseFloat(precio_unitario)/iva16; 
            }else{                          //(2=SERVICIO)
                subtotalUnitario = Float.parseFloat(precio_unitario);
            }

            if(comentarios == null){ check = "✔"; }

            /*RELACIONAR FOLIO DEL CONCEPTO CON EL NUMERO DE FACTURA*/
            String actFolioRegFact = "UPDATE TRA_FACTURACION SET REL_CONCEPTO_ID = '" + folio_concepto + "' WHERE SERIE = '" + serie + "' AND FOLIO = '" + folio + "' AND CBDIV_ID = '" + cve + "' AND ESTADO_ID =0";
            boolean oraOutFolio = oraDB.execute(actFolioRegFact);  
              
            /*CONSULTAR EXISTENCIA DEL CONCEPTO LIGADO AL NUMERO DE FACTURA*/
            String existConcepto = "SELECT DISTINCT REL_CONCEP_ID FROM TRA_REL_CONCEPTOS WHERE CONCEPTO_ID = '" + codigo_concepto + "' AND REL_CONCEPTO_ID ='" + folio_concepto + "' AND CBDIV_ID = '" + cve + "' AND ESTADO_ID =0";
            boolean oraOutC = oraDB.execute(existConcepto);  
            
            if(oraOutC){
                
                if (db.doDB(fac.consultarCantidadImporteConcepto(codigo_concepto, folio_concepto, cve))) {
                    for (String[] rowy : db.getResultado()) {
                        cantidadExist = Float.parseFloat(rowy[0]);
                    }
                }
                
                cantidadTotal = cantidad+cantidadExist;
                subtotalGral = (cantidadTotal)*subtotalUnitario; /*SE OBTIENE EL SUBTOTAL GRAL POR LA CANTIDAD DE CONCEPTOS INGRESADOS*/
                
                 insertarRC = " UPDATE TRA_REL_CONCEPTOS SET "
                            + " CANTIDAD = '" + cantidadTotal + "', " 
                            + " IMPORTE = '" + formatter.format(subtotalGral) + "' "
                            + " WHERE CONCEPTO_ID = '" + codigo_concepto + "' "
                            + " AND REL_CONCEPTO_ID ='" + folio_concepto + "' "
                            + " AND CBDIV_ID = '" + cve + "' "
                            + " AND ESTADO_ID =0 ";
            }else{
                
                cantidadTotal = cantidad;
                subtotalGral = (cantidadTotal)*subtotalUnitario; /*SE OBTIENE EL SUBTOTAL GRAL POR LA CANTIDAD DE CONCEPTOS INGRESADOS*/
                
                 insertarRC = " INSERT INTO TRA_REL_CONCEPTOS "
                            + " (REL_CONCEP_ID, "
                            + " REL_CONCEPTO_ID, "                          /*SECUENCIA*/
                            + " CONCEPTO_ID, "
                            + " DESCRIPCION_CONCEPTO, "
                            + " CANTIDAD, "
                            + " REL_CLVSAT_ID, "
                            + " UNIDAD_SAT_ID, "
                            + " PRECIO_UNITARIO, "
                            + " IMPORTE_DESCUENTO, "
                            + " IMPORTE, "
                            + " COMENTARIOS, "
                            + " NUM_IDENTIFICADOR, "
                            + " REL_IMPUESTO_ID, "                          /*SECUENCIA*/
                            + " REL_PEDIMENTO_ID, "                         /*SECUENCIA*/
                            + " REL_PARTE_ID, "                             /*SECUENCIA*/
                            + " ESTADO_ID, "
                            + " CBDIV_ID) "
                            + " VALUES "
                            + " (NULL, "
                            + " '" + folio_concepto + "', "                 /*SECUENCIA*/
                            + " '" + codigo_concepto + "', "
                            + " '" + concepto_desc + "', "
                            + " '" + cantidadTotal + "', "
                            + " '" + rel_clvsat_id + "', "
                            + " '" + unidad_sat_id + "', "
                            + " '" + formatter.format(subtotalUnitario) + "', "
                            + " '" + importe_descuento + "', "
                            + " '" + formatter.format(subtotalGral) + "', "
                            + " '" + comentarios + "', "
                            + " 'PRD', "
                            + " '" + folioImpuestoContador + "', "          /*SECUENCIA*/
                            + " '" + folio_relpedimento + "', "             /*SECUENCIA*/
                            + " '" + folio_relpartes + "', "                /*SECUENCIA*/
                            + " 0, "                                        /*0 CONCEPTO PRE-INGRESADO (TABLA: APP_ESTADO)*/
                            + " '"+ cve +"') ";
            }

            boolean oraOut8 = oraDB.execute(insertarRC);       
            
            oraDB.close();
            out.print(salida);   
            
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
            Logger.getLogger(InsertarConceptos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InsertarConceptos.class.getName()).log(Level.SEVERE, null, ex);
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
