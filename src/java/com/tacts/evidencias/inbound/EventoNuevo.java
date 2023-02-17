/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.inbound;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.consultas.ConsultasQuery;
import com.tacts.evidencias.facturacion.Email;
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
 * @author grecendiz
 */
public class EventoNuevo extends HttpServlet {

    
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        
        HttpSession ownsession = request.getSession();
        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));

        String gtnId = request.getParameter("gtnId");
        String id    = request.getParameter("id");
        boolean update=false;
        boolean insert=false;
        
        
         if (db.doDB("select ID_EVENTO,USER_NID,SHIPMENT_ID,CONTAINER1,BL_AWB_PRO,ESTADO,FECHA_CAPTURA,PLANTILLA_ID,EST_ETA_DC from TRA_INB_EVENTO where ID_EVENTO="+id)) {
            for (String[] row : db.getResultado()) {
                 String sql2=" Insert into TRA_INB_EVENTO("
                         + "ID_EVENTO,USER_NID,SHIPMENT_ID,CONTAINER1,BL_AWB_PRO,PLANTILLA_ID"
                         + ")values("
                         + "null,"+row[1]+",'"+row[2]+"','"+row[3]+"','"+row[4]+"','"+gtnId+"')";

                 
                String sqlUpdate="update TRA_INC_GTN_TEST set PLANTILLA_ID='"+gtnId+"' where ID_GTN in ("+gtnId+")";
                  update=db.doDB(sqlUpdate);
                  insert=db.doDB(sql2);
                  System.out.println(sqlUpdate+"--"+sql2);
                 
                 
             }
            }
        
        
       
        
        /*
          
        */

        if(update && insert){
            
            out.print("correcto");
        
        }
      
        
             
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
            Logger.getLogger(AlertaInbound.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AlertaInbound.class.getName()).log(Level.SEVERE, null, ex);
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
