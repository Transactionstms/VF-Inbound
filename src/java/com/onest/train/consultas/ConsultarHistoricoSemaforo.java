/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
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
public class ConsultarHistoricoSemaforo extends HttpServlet {
    
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
        String idShipment = request.getParameter("idShipment");       
        String prioridad = "";
        String salida = "";
        int sal = 0;
        
        String consulta = " SELECT DISTINCT TO_CHAR(FECHA_ACTIVACION,'MM/DD/YYYY'), ESTATUS_SEMAFORO, LOAD_TYPE_FINAL, TO_CHAR(FECHA_TERMINO,'MM/DD/YYYY') FROM TRA_HIST_INB_SEMAFORO WHERE SHIPMENT_ID = '" + idShipment + "' ";
        if (db.doDB(consulta)) {
            for (String[] row : db.getResultado()) {
                
                 if(row[1].equals("1")||row[1].equals("")){
                     prioridad = "BAJA";
                 }else if(row[1].equals("2")){
                     prioridad = "MEDIA";
                 }else if(row[1].equals("3")){
                     prioridad = "ALTA";
                 }
                
               salida+= " <tr id=\"tr" + sal + "\"> "
                     + "    <td>" + row[0] + "</td> "
                     + "    <td>" + prioridad + "</td> "
                     + "    <td>" + row[2] + "</td> "
                     + "    <td>" + row[3] + "</td> "
                     + " </tr> ";
                     
                     sal++; 
            }
        }
     
         out.print(salida);
         System.out.println("Esqueleto tabla:"+salida);
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
            Logger.getLogger(ConsultarHistoricoSemaforo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarHistoricoSemaforo.class.getName()).log(Level.SEVERE, null, ex);
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