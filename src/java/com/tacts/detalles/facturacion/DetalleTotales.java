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
 * @author Desarrollo Tacts
 */
public class DetalleTotales extends HttpServlet {

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
        
        String serie = request.getParameter("serie"); 
        String folio = request.getParameter("folio"); 
        String salida = "";
        
        //Totales
        float subtotalP=0;
        float trasladoP=0;
        float retencionesP=0;
        float descuentosP=0;
        float subtotalGral = 0;
        float importe_descuentoGral = 0;
        float trasladoGral = 0;
        float retencionesGral = 0;
        float totalGral = 0;
        float ivaProducto = 1.16f;
        float ivaServicio = 0.16f;
        
        /*ARMADO DE TABLA*/
        if (db.doDB(fac.consultarConceptosIngresados(serie, folio, cve))) {
            for (String[] rowF : db.getResultado()) {
                
                if (db.doDB(fac.consultarTipoConcepto(cve,rowF[1]))) {
                    for (String[] rowT : db.getResultado()) {
                        
                        if(rowT[0].equals("1")){  //(1=producto)
                            subtotalP = (Float.parseFloat(rowF[3])*Float.parseFloat(rowT[1]))/ivaProducto; 
                            trasladoP = (Float.parseFloat(rowF[3])*Float.parseFloat(rowT[1]))-subtotalP;
                        }else{                  //(2=servicio)
                            subtotalP = Float.parseFloat(rowT[1]);
                            trasladoP = subtotalP * ivaServicio;
                        }
                    }
                }
                
                //Calcular Subtotal Gral
                subtotalGral += subtotalP;
                //Calcular Traslado Gral
                trasladoGral += trasladoP;
                //Calcular Retenciones Gral
                retencionesGral += retencionesP;
                //Calcular Descuento Gral
                importe_descuentoGral += descuentosP;
                
            }
                //Calcular Total Gral
                totalGral = subtotalGral + trasladoGral + retencionesGral - importe_descuentoGral;
        }
        
                salida += "<table class=\"table\" id=\"datatable1\"> "
                        + "    <thead> "
                        + "        <tr><th class=\"\" scope=\"col\">Subtotal:</th> "
                        + "            <td>$ " + formatter.format(subtotalGral) + "</td></tr> "
                        + "        <tr><th class=\"\">Importe Descuentos:</th> "
                        + "            <td>$ " + formatter.format(importe_descuentoGral) + "</td></tr> "
                        + "        <tr><th class=\"\">Traslados:</th> "
                        + "            <td>$ " + formatter.format(trasladoGral) + "</td></tr> "
                        + "        <tr><th class=\"\">Retenciones:</th> "
                        + "            <td>$ " + formatter.format(retencionesGral) + "</td></tr> "
                        + "        <tr><th class=\"\">Total:</th> "
                        + "            <td>$ " + formatter.format(totalGral) + "</td></tr> "
                        + "    </thead> "
                        + "    <tbody> "
                        + "    </tbody> "
                        + "</table>";
        
                salida += "<input type=\"hidden\" id=\"cant_subtotal_gral\" name=\"cant_subtotal_gral\" value=\"" + formatter.format(subtotalGral) + "\">";   
                salida += "<input type=\"hidden\" id=\"cant_descuento_gral\" name=\"cant_descuento_gral\" value=\"" + formatter.format(importe_descuentoGral) + "\">";  
                salida += "<input type=\"hidden\" id=\"cant_traslados_gral\" name=\"cant_traslados_gral\" value=\"" + formatter.format(trasladoGral) + "\">"; 
                salida += "<input type=\"hidden\" id=\"cant_retenciones_gral\" name=\"cant_retenciones_gral\"  value=\"" + formatter.format(retencionesGral) + "\">";  
                salida += "<input type=\"hidden\" id=\"cant_total_gral\" name=\"cant_total_gral\"  value=\"" + formatter.format(totalGral) + "\">";
            
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
            Logger.getLogger(DetalleTotales.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DetalleTotales.class.getName()).log(Level.SEVERE, null, ex);
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
