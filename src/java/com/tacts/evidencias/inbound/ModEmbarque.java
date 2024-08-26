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
public class ModEmbarque extends HttpServlet {

  
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
           // String sqlGtn="update tra_inc_gtn_test set STATUS_EMBARQUE=2 where EMBARQUE_AGRUPADOR='"+fol+"' ";
            String sqlEmb="update  TRA_INB_EMBARQUE set"
                    + ""
                    + " EMBARQUE_TRANSPORTISTA='"+tran+"',"
                    + " EMBARQUE_FEC_ENRAMPE=TO_DATE('"+f1+"', 'MM/DD/YYYY HH24:MI'),"
                    + " EMBARQUE_FEC_INICIO=TO_DATE('"+f2+"', 'MM/DD/YYYY HH24:MI'),"
                    + " EMBARQUE_TCUSTODIA='"+cus+"',"
                    + " CAMION_ID='"+camionesValue+"',"
                    + " UTRANSPORTE_ID="+tipoUnidadValue+","
                    + " CHOFER_ID='"+choferValue+"',"
                    + " DSPMOV_ID="+dispositivosValue+","
                    + " EMBARQUE_FEC_REVISION= to_date('"+fechaRevisionValue+"','MM/DD/YYYY HH24:MI'),"
                    + " EMBARQUE_FEC_FIN=to_date('"+fechaFinEntregaValue+"','MM/DD/YYYY HH24:MI'),"
                    + " EMBARQUE_SELLO_CAJA='"+selloCajaValue+"',"
                    + " EMBARQUE_RELACION_ENT='"+relacionEntregaValue+"',"
                    + " EMBARQUE_PACKING_LIST='"+packingListValue+"',"
                    + " EMBARQUE_AUDITOR='"+autorValue+"',"
                    + " EMBARQUE_OBSERVACIONES='"+observacionesValue+"',"
                    + " ORIGEN_ID='"+origen+"'"
                    + " where  EMBARQUE_ID in ('"+fol+"')";
         
            System.out.println("*****"+sqlEmb);
            boolean update2=db.doDB(sqlEmb);
            
            
            
            
            
            
             if( update2){ 
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



