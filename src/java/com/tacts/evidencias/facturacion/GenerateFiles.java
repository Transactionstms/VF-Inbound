/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template fileCliente, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.facturacion;

import com.dao.ServiceDAO;
import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
public class GenerateFiles extends HttpServlet {
    
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
        
        String tipo = request.getParameter("tipo");
        String serie = request.getParameter("serie");
        String folio = request.getParameter("folio");
        String numFactura = serie + "" + folio; 
        String fileCliente = "cvd" + cve;
        String saveUrl = "";
        String salida = "";
        
        /*
          1 = CFDI
          2 = Carta Porte
          3 = Recibo de Pago
        */
        
        if(tipo.equals("1")){
            
            saveUrl = "UPDATE TRA_FACTURACION SET URL_PDF_CFDI = 'D:\\Servicios\\Facturacion\\Cfdi\\" + fileCliente + "\\" + numFactura + ".pdf' WHERE SERIE = '" + serie + "' AND FOLIO = '" + folio + "' AND CBDIV_ID = '" + cve + "'";
       
        }else if(tipo.equals("2")){
            
            saveUrl = "UPDATE TRA_FACTURACION SET URL_PDF_CFDI = 'D:\\Servicios\\Facturacion\\Cfdi\\" + fileCliente + "\\" + numFactura + ".pdf', URL_PDF_CP = 'D:\\Servicios\\Facturacion\\CartaPorte\\" + fileCliente + "\\" + numFactura + ".pdf' WHERE SERIE = '" + serie + "' AND FOLIO = '" + folio + "' AND CBDIV_ID = '" + cve + "'";
        
        }else if(tipo.equals("3")){
            
            saveUrl = "UPDATE TRA_FACTURACION SET URL_PDF_RDP = 'D:\\Servicios\\Facturacion\\ReciboDePago\\" + fileCliente + "\\" + numFactura + ".pdf' WHERE SERIE = '" + serie + "' AND FOLIO = '" + folio + "' AND CBDIV_ID = '" + cve + "'";
        
        }else if(tipo.equals("4")){
            
            saveUrl = "UPDATE TRA_FACTURACION SET URL_PDF_TICKET = 'D:\\Servicios\\Facturacion\\Tickets\\" + fileCliente + "\\" + numFactura + ".pdf' WHERE SERIE = '" + serie + "' AND FOLIO = '" + folio + "' AND CBDIV_ID = '" + cve + "'";
        }
        
            boolean oraOut = oraDB.execute(saveUrl);
            
        oraDB.close(); //cerrar conexión
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
            Logger.getLogger(GenerateFiles.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GenerateFiles.class.getName()).log(Level.SEVERE, null, ex);
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