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
public class ModificarEvento extends HttpServlet {

     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          
            
            
            
        HttpSession ownsession = request.getSession();
        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));

        String responsable = request.getParameter("responsable");
        String finaldes    = request.getParameter("finaldes");
        String Brand       = request.getParameter("Brand");
        
        String sbu_name = request.getParameter("sbu_name");
        String Shipment = request.getParameter("Shipment");
        String Load1    = request.getParameter("Load1");
        
        String quantity          = request.getParameter("quantity");
        String pod               = request.getParameter("pod");
        String est_departure_pol = request.getParameter("est_departure_pol");
        
        String eta_port_discharge = request.getParameter("eta_port_discharge");
        String max_flete          = request.getParameter("max_flete");
        String eta_plus2          = request.getParameter("eta_plus2");
        
        String eta_plus         = request.getParameter("eta_plus");
        String pol              = request.getParameter("pol");
        String observaciones    = request.getParameter("observaciones");
        
        String evento    = request.getParameter("evento");
        String ship      = request.getParameter("ship");
        String con       = request.getParameter("con");
        
         String bl       = request.getParameter("bl");
         
         String sqlEve=" update tra_inb_evento\n" +
                        " set \n" +
                        " USER_NID ="+responsable+",\n" +
                        " observaciones='"+observaciones+"'\n" +
                        " where ID_EVENTO="+evento;
         String sqlGtn="update tra_inc_gtn_test\n" +
" set \n" +
" final_destination = '"+finaldes+"',\n" +
" brand_division='"+Brand+"',\n" +
" sbu_name='"+sbu_name+"',\n" +
" shipment_id='"+Shipment+"',\n" +
" load_type='"+Load1+"',\n" +
" LOAD_TYPE_FINAL='"+Load1+"',\n" +
" quantity='"+quantity+"',\n" +
" pod='"+pod+"',\n" +
" est_departure_pol =to_date('"+est_departure_pol+"','MM/DD/YY'), " +
" eta_port_discharge=to_date('"+eta_port_discharge+"','MM/DD/YY'),\n" +
" max_flete='"+max_flete+"',\n" +
" eta_plus2=to_date('"+eta_plus2+"', 'MM/DD/YY'),\n" +
" eta_plus=to_date('"+eta_plus+"','MM/DD/YY'),\n" +
" pol='"+pol+"',"+
" bl_awb_pro='"+bl+"'\n" +
" where container1='"+con+"' and shipment_id='"+ship+"'";
        
            System.out.println(sqlEve);
            System.out.println(sqlGtn);
        boolean update=db.doDB(sqlEve);
        boolean insert=db.doDB(sqlGtn);
        
        
      
            
        
        
       
        
        /*

        
        */

        if(update && insert){
            
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
