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
                
               //   String evento=request.getParameter("evento");
            
             String camionesValue        = request.getParameter("camionesValue");
             String tipoUnidadValue      = request.getParameter("tipoUnidadValue");
             String choferValue          = request.getParameter("choferValue");
             String dispositivosValue    = request.getParameter("dispositivosValue");
             String fechaRevisionValue   = request.getParameter("fechaRevisionValue");
             String selloCajaValue       = request.getParameter("selloCajaValue");
             String relacionEntregaValue = request.getParameter("relacionEntregaValue");
             String fechaFinEntregaValue = request.getParameter("fechaFinEntregaValue");
             String packingListValue     = request.getParameter("packingListValue");
             String autorValue           = request.getParameter("autorValue");
             String observacionesValue   = request.getParameter("observacionesValue");
             
String reg1   = request.getParameter("reg1");
String reg2   = request.getParameter("reg2");
String origen   = request.getParameter("origen");
                                          //, clave='"+reg1+"',DOCTOS_ADUANEROS='"+reg2+"',TIPO_MATERIA='"+reg3+"'    
            String sqlGtn="update tra_inc_gtn_test set STATUS_EMBARQUE=2 where EMBARQUE_AGRUPADOR='"+fol+"' ";
            String sqlEmb="insert into TRA_INB_EMBARQUE "
                    + "(EMBARQUE_AGRUPADOR,EMBARQUE_TRANSPORTISTA,EMBARQUE_FEC_ENRAMPE,EMBARQUE_FEC_INICIO,EMBARQUE_TCUSTODIA,"
                    + " CAMION_ID,UTRANSPORTE_ID,CHOFER_ID,DSPMOV_ID,EMBARQUE_FEC_REVISION,EMBARQUE_FEC_FIN,EMBARQUE_SELLO_CAJA,EMBARQUE_RELACION_ENT,EMBARQUE_PACKING_LIST,EMBARQUE_AUDITOR,EMBARQUE_OBSERVACIONES,ORIGEN_ID)"//EMBARQUE_ESTADO_ID
                    + "values"
                    + "('"+fol+"','"+tran+"',TO_DATE('"+f1+"', 'MM/DD/YYYY HH24:MI'),TO_DATE('"+f2+"', 'MM/DD/YYYY HH24:MI'),'"+cus+"',"
                    + "'"+camionesValue+"', "+tipoUnidadValue+",'"+choferValue+"',"+dispositivosValue+", to_date('"+fechaRevisionValue+"','MM/DD/YYYY HH24:MI'), to_date('"+fechaFinEntregaValue+"','MM/DD/YYYY HH24:MI'), '"+selloCajaValue+"','"+relacionEntregaValue+"','"+packingListValue+"','"+autorValue+"','"+observacionesValue+"','"+origen+"'  ) ";
         
            System.out.println(sqlGtn);
            boolean update1=db.doDB(sqlGtn);
            boolean update2=db.doDB(sqlEmb);
            
            
            
            
            
            
             if(update1 && update2){ 
                 //  Email correo = new Email();
                 // try {
                 //      correo.alertaLiberacionV2(bytes, embarque_id, idLTransporte, nameLTransporte);
                 //      String transportista = "";  
                 //      String sbuSQL = "select  LTRANSPORTE_NOMBRE from tra_inb_linea_transporte where LTRANSPORTE_ID="+tran;
                 //       if (db.doDB(sbuSQL)) {
                 //            for (String[] row : db.getResultado()) {
                 //               transportista =   row[0]  ;
                 //         }
                 //       }
                 //     
                 //     correo.alertaLiberacionV2(null, fol, tran, transportista);
                 //     
                 // } catch (SQLException ex) {
                 //     Logger.getLogger(CrearEmbarque.class.getName()).log(Level.SEVERE, null, ex);
                 // }
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
