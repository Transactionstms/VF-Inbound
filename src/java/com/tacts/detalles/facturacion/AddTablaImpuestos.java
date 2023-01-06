/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.detalles.facturacion;

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
 * @author luis_
 */
public class AddTablaImpuestos extends HttpServlet {

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
        
        String codigo_concepto = request.getParameter("codigo_concepto");
        String up_tipoImpuesto = request.getParameter("up_tipoImpuesto");
        String up_base = request.getParameter("up_base");
        String up_impuesto = request.getParameter("up_impuesto");
        String up_tipoFactor = request.getParameter("up_tipoFactor");
        String desc_tasa = request.getParameter("up_tasa"); //tasa-porcentaje valor 
        String up_Cuota = request.getParameter("up_Cuota"); //cuota
        String importe = request.getParameter("up_importe"); //importe
        String tipoConcepto = request.getParameter("tipoConcepto"); //tipo concepto
        String id_tasa = "";
        String desc_tipoImpuesto = "";
        String desc_impuesto = "";
        String desc_tipoFactor = "";
        String insertarImpuesto = "";
        
        String salida = "";
        float upBase = 0;
        float subTotal =0;
        float ivaTotal =0;
        float iva16 = 1.16f;
        
        int folioImpuestoContador = Integer.parseInt(request.getParameter("contImpuestoConcepto"));    //contador por concepto
        int contador = Integer.parseInt(request.getParameter("contadorImpuestos"));                    //Contador de registros total
        
        int sal = contador;
        
            /*EXTRAER DESCRIPCION DEL TIPO IMPUESTO POR MEDIO DEL ID*/
            if (db.doDB(fac.desc_tipoImpuesto(up_tipoImpuesto))) {
                for (String[] rowT : db.getResultado()) {
                    desc_tipoImpuesto = rowT[0];
                }
            }
            
            /*EXTRAER DESCRIPCION DEL IMPUESTO POR MEDIO DEL ID*/
            if (db.doDB(fac.desc_impuesto(up_impuesto))) {
                for (String[] rowI : db.getResultado()) {
                    desc_impuesto = rowI[0];
                }
            }
            
            /*EXTRAER DESCRIPCION DEL TIPO FACTOR POR MEDIO DEL ID*/
            if (db.doDB(fac.desc_tipoFactor(up_tipoFactor))) {
                for (String[] rowF : db.getResultado()) {
                    desc_tipoFactor = rowF[0];
                }
            }
            
            /*EXTRAER ID TASA POR MEDIO DE LA DESCRIPCION*/
            if (db.doDB(fac.consultarIdTasa(desc_tasa))) {
                for (String[] rowT : db.getResultado()) {
                    id_tasa = rowT[0];
                }
            }
        
            //CASTEAR (IMPORTE BASE);
            upBase = Float.parseFloat(up_base); 

            /*IDENTIFICAR TIPO DE CONCEPTO PARA APLICAR EL IVA CORRESPONDIENTE DEPENDIENDO DE SU TASA ID*/
            if(tipoConcepto.equals("1")){ //(1=PRODUCTO)
                subTotal = upBase/iva16; 
                ivaTotal = upBase - subTotal;
            }else{                        //(2=SERVICIO)
                subTotal = upBase;
                ivaTotal = upBase*Float.parseFloat(desc_tasa);
                
            }   
            
            /*CONSULTAR EXISTENCIA DEL IMPUESTO LIGADO AL NUMERO DE FACTURA*/
            String existConcepto = "SELECT REL_IMP_ID FROM TRA_REL_IMPUESTOS WHERE CONCEPTO_ID ='" + codigo_concepto + "' AND CBDIV_ID = '" + cve + "' AND ESTADO_ID =0";
            boolean oraOutC = oraDB.execute(existConcepto); 
            
            if(oraOutC){              
                insertarImpuesto = " UPDATE TRA_REL_IMPUESTOS SET "
                                 + " BASE = '" + formatter.format(subTotal) + "', "
                                 + " IMPORTE = '" + formatter.format(ivaTotal) + "' "
                                 + " WHERE CONCEPTO_ID = '" + codigo_concepto + "' "
                                 + " AND CBDIV_ID = '" + cve + "' "
                                 + " AND ESTADO_ID =0 ";
            }else{
                insertarImpuesto = " INSERT INTO TRA_REL_IMPUESTOS "
                                 + " (REL_IMP_ID, "
                                 + " CONCEPTO_ID, "
                                 + " TIPO_IMPUESTO, "
                                 + " BASE, "                                /*Tvalor unitario X cantidad*/
                                 + " IMPUESTO_ID, "                         /*Traslados*/
                                 + " TIPO_FACTOR, "                         /*tra_tipo_tasa = 1*/
                                 + " TASA_ID, "
                                 + " CUOTA, "                               /*0.160000*/
                                 + " IMPORTE, "
                                 + " REL_IMPUESTO_ID, "                     /*SECUENCIA*/
                                 + " ESTADO_ID, "
                                 + " CBDIV_ID) "
                                 + " VALUES "
                                 + " (NULL,"
                                 + " '" + codigo_concepto + "', "
                                 + " '" + up_tipoImpuesto + "', "
                                 + " '" + formatter.format(subTotal) + "', "
                                 + " '" + up_impuesto + "', "
                                 + " '" + up_tipoFactor + "', "
                                 + " '" + id_tasa + "', "
                                 + " '" + desc_tasa + "', "
                                 + " '" + formatter.format(ivaTotal) + "', "
                                 + " '" + folioImpuestoContador + "', "     /*SECUENCIA*/
                                 + " 0, "                                   /*0 CONCEPTO PRE-INGRESADO (TABLA: APP_ESTADO)*/
                                 + " '" + cve + "') ";
            }     
             boolean oraOut9 = oraDB.execute(insertarImpuesto);
             
            //ARMADO DE TABLA
            salida += "<tr id=\"imp" + sal + "\"> "
                    + "  <td><center>" + desc_tipoImpuesto + "</center><input value=\"" + up_tipoImpuesto + "\" name=\"ti_tipoImpuesto[" + sal + "]\"  id=\"ti_tipoImpuesto[" + sal + "]\" type=\"hidden\" /></td>"
                    + "  <td><center>" + formatter.format(subTotal) + "</center><input value=\"" + formatter.format(subTotal) + "\" name=\"ti_base[" + sal + "]\"  id=\"ti_base[" + sal + "]\" type=\"hidden\" /></td>"
                    + "  <td><center>" + desc_impuesto + "</center><input value=\"" + up_impuesto + "\" name=\"ti_impuesto[" + sal + "]\"  id=\"ti_impuesto[" + sal + "]\" type=\"hidden\" /></td>"
                    + "  <td><center>" + desc_tipoFactor + "</center><input value=\"" + up_tipoFactor + "\" name=\"ti_tipoFactor[" + sal + "]\"  id=\"ti_tipoFactor[" + sal + "]\" type=\"hidden\" /></td>"
                    + "  <td><center>" + desc_tasa + " %</center><input value=\"" + id_tasa + "\" name=\"ti_up_tasa[" + sal + "]\"  id=\"ti_up_tasa[" + sal + "]\" type=\"hidden\" /></td>"
                    + "  <td><center>" + up_Cuota + "</center><input value=\"" + desc_tasa + "\" name=\"ti_up_Cuota[" + sal + "]\"  id=\"ti_up_Cuota[" + sal + "]\" type=\"hidden\" /></td>"
                    + "  <td><center>$ " + formatter.format(ivaTotal) + "</center><input value=\"" + formatter.format(ivaTotal) + "\" name=\"ti_importe[" + sal + "]\"  id=\"ti_importe[" + sal + "]\" type=\"hidden\" /></td>"
                    + "  <td><a class=\"text-lg text-info\" onclick=\"delete_impuesto('" + sal + "')\"><i class=\"far fa-trash-alt\"></i></a></td>"
                    + "</tr> ";
             
             sal++;
             
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
            Logger.getLogger(AddTablaConceptos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddTablaConceptos.class.getName()).log(Level.SEVERE, null, ex);
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