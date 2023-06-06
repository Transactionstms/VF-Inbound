/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.inbound;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.tacts.evidencias.facturacion.Email;
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
public class AlertaCustoms extends HttpServlet {
    
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

        String agenteAduanal = request.getParameter("agenteAduanal");
        String rutaFicheroRojos = "";
        String rutaFicheroAmarillos = "";
        String agentes = "";
        String emails = "";
        String salida = "";
         
        String consulta = "SELECT DISTINCT AGENTE_ADUANAL_ID, CORREO FROM TRA_INB_AGENTE_ADUANAL WHERE AGENTE_ADUANAL_ID IN (" + agenteAduanal + ") AND ESTATUS = 1 AND CBDIV_ID = 20";
        if (db.doDB(consulta)) {
            for (String[] rowE : db.getResultado()) {
                agentes = rowE[0];
                emails = rowE[1];
            }
        }
        
        emails = emails.replaceFirst(" ", "/");
        
        String semaforoRojo = "SELECT DISTINCT ESTATUS_SEMAFORO FROM TRA_INB_CUSTOMS WHERE AGENTE_ADUANAL_ID IN ('" + agentes + "') AND ESTATUS_SEMAFORO = 1";
        if (db.doDB(semaforoRojo)) {
            for (String[] rowR : db.getResultado()) {
                rutaFicheroRojos = CreatExcelCustoms.crearAPartirDeArrayListCustoms(agentes, rowR[0], "PRIORIDAD ALTA");
                correo.alertaSemaforoCustoms(emails, rutaFicheroRojos.trim(), agentes, "PRIORIDAD ALTA");
                System.out.println("Estatus Correo Rojos: "+correo);
            }
        }
        
        String semaforoAmarillo = "SELECT DISTINCT ESTATUS_SEMAFORO FROM TRA_INB_CUSTOMS WHERE AGENTE_ADUANAL_ID IN ('" + agentes + "') AND ESTATUS_SEMAFORO = 2";
        if (db.doDB(semaforoAmarillo)) {
            for (String[] rowA : db.getResultado()) {
                rutaFicheroAmarillos = CreatExcelCustoms.crearAPartirDeArrayListCustoms(agentes, rowA[0], "PRIORIDAD MEDIA");
                correo.alertaSemaforoCustoms(emails, rutaFicheroAmarillos.trim(), agentes, "PRIORIDAD MEDIA");
                System.out.println("Estatus Correo Amarillos: "+correo);
            }
        }    
        
         salida = "true";
     
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
            Logger.getLogger(AlertaCustoms.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AlertaCustoms.class.getName()).log(Level.SEVERE, null, ex);
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