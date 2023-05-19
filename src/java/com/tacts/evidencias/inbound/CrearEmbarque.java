/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.inbound;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.transactions.mailsender.jdbc.Email;
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
 * @author grecendiz
 */
public class CrearEmbarque extends HttpServlet {
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
              HttpSession ownsession = request.getSession();
              DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            
                String tran = request.getParameter("tran"); 
                String cus  = request.getParameter("cus"); 
                String f1   = request.getParameter("f1"); 
                String f2   = request.getParameter("f2"); 
                String fol  = request.getParameter("fol");  

                                                  
            String sqlGtn="update tra_inc_gtn_test set STATUS_EMBARQUE=2 where EMBARQUE_AGRUPADOR='"+fol+"' ";
            String sqlEmb="insert into TRA_INB_EMBARQUE (EMBARQUE_AGRUPADOR,EMBARQUE_TRANSPORTISTA,EMBARQUE_FEC_ENRAMPE,EMBARQUE_FEC_INICIO,EMBARQUE_TCUSTODIA)"
                    + "values"
                    + "('"+fol+"','"+tran+"',TO_DATE('"+f1+"', 'MM/DD/YYYY HH24:MI'),TO_DATE('"+f2+"', 'MM/DD/YYYY HH24:MI'),'"+cus+"') ";
         
            System.out.println(sqlGtn);
            boolean update1=db.doDB(sqlGtn);
            boolean update2=db.doDB(sqlEmb);
            
            
            
            
            
            
             if(update1 && update2){ 
                   Email correo = new Email();
                  try {
                       //correo.alertaLiberacionV2(bytes, embarque_id, idLTransporte, nameLTransporte);
                       String transportista = "";  
                       String sbuSQL = "select  LTRANSPORTE_NOMBRE from tra_inb_linea_transporte where LTRANSPORTE_ID="+tran;
                        if (db.doDB(sbuSQL)) {
                             for (String[] row : db.getResultado()) {
                                transportista =   row[0]  ;
                          }
                        }
                      
                      correo.alertaLiberacionV2(null, fol, tran, transportista);
                      
                  } catch (SQLException ex) {
                      Logger.getLogger(CrearEmbarque.class.getName()).log(Level.SEVERE, null, ex);
                  }
                    out.print("correcto"); 
                }else{ 
                    out.print("error"); 
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
        processRequest(request, response);
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
        processRequest(request, response);
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
