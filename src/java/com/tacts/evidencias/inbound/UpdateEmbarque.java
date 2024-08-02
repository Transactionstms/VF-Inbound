/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.inbound;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author grecendiz
 */
public class UpdateEmbarque extends HttpServlet {

     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
              HttpSession ownsession = request.getSession();
              DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            
    
             String origen   = request.getParameter("op");                           //, clave='"+reg1+"',DOCTOS_ADUANEROS='"+reg2+"',TIPO_MATERIA='"+reg3+"'    
            String sqlGtn="update tra_inc_gtn_test set STATUS_EMBARQUE=0,EMBARQUE_AGRUPADOR=null where EMBARQUE_AGRUPADOR=( select EMBARQUE_AGRUPADOR from TRA_INB_EMBARQUE  WHERE EMBARQUE_ID = '"+origen+"') ";
          
                                        //, clave='"+reg1+"',DOCTOS_ADUANEROS='"+reg2+"',TIPO_MATERIA='"+reg3+"'    
           
            
            boolean update1=db.doDB(sqlGtn); 
            
            
            
            
 // Verificar si el string no está vacío
        if (!origen.isEmpty()) {
            // Obtener el primer carácter
            char firstChar = origen.charAt(0);

            // Imprimir el primer carácter
            System.out.println("El string empieza con: " + firstChar);

            // Comparar con una letra específica
            if (firstChar == 'M') {
               String sqlGtn2="update tra_inb_embarque_traslado set EMBARQUE_ESTADO_ID=8 where EMBARQUE_AGRUPADOR=( select EMBARQUE_AGRUPADOR from tra_inb_embarque_traslado  WHERE EMBARQUE_T_ID = '"+origen+"') ";
           boolean update2=db.doDB(sqlGtn2);
                  } else {
                 String sqlGtn2="update TRA_INB_EMBARQUE set EMBARQUE_ESTADO_ID=8 where EMBARQUE_AGRUPADOR=( select EMBARQUE_AGRUPADOR from TRA_INB_EMBARQUE  WHERE EMBARQUE_ID = '"+origen+"') ";
           boolean update2=db.doDB(sqlGtn2);
            }
        } else {
            System.out.println("El string está vacío.");
        }
        
            
            
            
            
             if(update1 ){ 
               
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
