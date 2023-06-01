/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;

import com.conexion.GenericJdbc;
import com.tacts.evidencias.facturacion.Email;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author grecendiz
 */
public class ConsultarRepartoTransportistaEdit extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
             String evento=request.getParameter("evento");
            
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
             
                
             GenericJdbc generico = new GenericJdbc();
         generico.openConection();
                    

         String sql=" update tra_inb_embarque set"
                + " CAMION_ID='"+camionesValue+"', "
                + " UTRANSPORTE_ID="+tipoUnidadValue+","
                + " CHOFER_ID='"+choferValue+"',"
                + " DSPMOV_ID="+dispositivosValue+", "
                +"  EMBARQUE_FEC_REVISION =to_date('"+fechaRevisionValue+"','MM/DD/YY'), " 
                +"  EMBARQUE_FEC_FIN =to_date('"+fechaFinEntregaValue+"','MM/DD/YY'), "
                + " EMBARQUE_SELLO_CAJA='"+selloCajaValue+"',"
                + " EMBARQUE_RELACION_ENT='"+relacionEntregaValue+"',"
                + " EMBARQUE_PACKING_LIST='"+packingListValue+"',"
                + " EMBARQUE_AUDITOR='"+autorValue+"',"
                + " EMBARQUE_OBSERVACIONES='"+observacionesValue+"',"
                + " EMBARQUE_ESTADO_ID=1 " 
                + " where EMBARQUE_AGRUPADOR='"+evento+"'";
            System.out.println("sql"+sql);
            
            String sql2=" update tra_inc_gtn_test set STATUS_EMBARQUE=3 where EMBARQUE_AGRUPADOR='"+evento+"'";
  PreparedStatement statement = generico.getConnection().prepareStatement(sql);
  PreparedStatement statement2 = generico.getConnection().prepareStatement(sql2);
              // Ejecutar la consulta
            int rowsUpdated = statement.executeUpdate();
             int rowsUpdated2 = statement2.executeUpdate();
            // Verificar el número de filas actualizadas
            System.out.println("Filas actualizadas: " + rowsUpdated);
              System.out.println("Filas actualizadas: " + rowsUpdated2);
            
            if(rowsUpdated>0 && rowsUpdated2>0){
                
                //enviar correo con pdf
                    Email correot = new Email(); 
                  correot.alertaConfirmacionTransporte(evento,evento);
   
            out.print("Datos guardados");
            }else{
            out.print("Error todos los datos son obligatorios");
            }
             statement.close(); 
           generico.closeConnection();
         
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
            Logger.getLogger(ConsultarRepartoTransportistaEdit.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarRepartoTransportistaEdit.class.getName()).log(Level.SEVERE, null, ex);
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
