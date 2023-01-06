/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.eliminar.facturacion;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.consultas.ConsultasQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class EliminarConceptos extends HttpServlet {
    
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
        
        String codigo_concepto = request.getParameter("codigo_concepto"); 
        String folio_concepto = request.getParameter("folio_concepto"); 
        String salida = "";
         
        /*ELIMINAR FOLIOS DE LOS IMPUESTOS ITERADOS POR EL CONCEPTO-FACTURA*/
        if (db.doDB(fac.consultarFoliosImpuestosFactura(codigo_concepto, folio_concepto, cve))) {
            for (String[] rowF : db.getResultado()) {
                String deleteImpuestos = "DELETE FROM TRA_REL_IMPUESTOS WHERE REL_IMPUESTO_ID =" + rowF[0] + " AND CBDIV_ID ='" + cve + "' AND ESTADO_ID = 0";
                boolean oraOut2 = oraDB.execute(deleteImpuestos);
            }
        }
            
        String deleteConceptos = "DELETE FROM TRA_REL_CONCEPTOS WHERE CONCEPTO_ID='" + codigo_concepto + "' AND REL_CONCEPTO_ID = '" + folio_concepto + "' AND CBDIV_ID='" + cve + "' AND ESTADO_ID = 0";
        boolean oraOut1 = oraDB.execute(deleteConceptos);    
          
        if(oraOut1){    
            salida = "1";
        }else{
            salida = "2";
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
            Logger.getLogger(EliminarProductos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EliminarProductos.class.getName()).log(Level.SEVERE, null, ex);
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