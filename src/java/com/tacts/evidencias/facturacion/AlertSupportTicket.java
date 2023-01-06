/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.facturacion;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.consultas.ConsultasQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
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
public class AlertSupportTicket extends HttpServlet {
    
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

        Email correo = new Email();
        ConsultasQuery fac = new ConsultasQuery();
        
        //Extracción de Fecha
        Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formato.format(date);

        //Extracción de Hora   
        Date hr = new Date();
        DateFormat hours = new SimpleDateFormat("HH:mm:ss");
        String hour = hours.format(hr);
        
        String num_ticket = request.getParameter("num_ticketSupport");
        String rfc = request.getParameter("rfcSupport");
        String codigo_postal = request.getParameter("codigo_postalSupport");
        String razon_social = request.getParameter("razon_socialSupport");
        String correoCliente = request.getParameter("correoSupport");
        String usocfdi_id = request.getParameter("usocfdi_idSupport");
        String regimen_id = request.getParameter("regimen_idSupport");
        String forma_id = request.getParameter("forma_idSupport");
        String com = request.getParameter("comentarios");
        String fechaEmsion = fecha + "  " + hour; 
        String correosInternos = ""; 
        String comentarios = "";
        String desc_UsoCfdi = "";
        String desc_RegimenFiscal = "";
        String desc_FormaPago = "";
        
        if(!com.equals("")){
            comentarios = com;
        }else{
            comentarios = "Sin Comentarios";
        }
        
        if (db.doDB(fac.consultarEmailSupport())) {  
            for (String[] rowx : db.getResultado()) {
                 correosInternos = rowx[0];
            }
        }
        
        if (db.doDB(fac.consultarDescUsoCfdi(usocfdi_id))) {  
            for (String[] rowx : db.getResultado()) {
                 desc_UsoCfdi = rowx[0];
            }
        }
        
        if (db.doDB(fac.consultarDescRegimenFiscal(regimen_id))) {  
            for (String[] rowx : db.getResultado()) {
                 desc_RegimenFiscal = rowx[0];
            }
        }
        
        if (db.doDB(fac.consultarDescFormaPago(forma_id))) {   
            for (String[] rowx : db.getResultado()) {
                 desc_FormaPago = rowx[0]; 
            }
        }
        
        
        correo.alertaSupport(fechaEmsion, correosInternos, num_ticket, rfc, codigo_postal, razon_social, correoCliente, desc_UsoCfdi, desc_RegimenFiscal, desc_FormaPago, comentarios);
        
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
            Logger.getLogger(AlertaFactutacion.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AlertaFactutacion.class.getName()).log(Level.SEVERE, null, ex);
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