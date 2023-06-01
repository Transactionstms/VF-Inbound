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
import java.sql.SQLException;
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
public class ConsultarRepartoCustodiasEdit extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
             String evento=request.getParameter("evento");
            
             String ncustodio        = request.getParameter("ncustodio");
             String ncustodio2      = request.getParameter("ncustodio2");
             String placa          = request.getParameter("placa");
             String celcustodio    = request.getParameter("celcustodio");
             String EMBARQUE   = request.getParameter("EMBARQUE"); 
             
         
             GenericJdbc generico = new GenericJdbc();
         generico.openConection();
                    

         String sql=" update tra_inb_embarque set"
                + " NOMBRE_CUSTODIO  ='"+ncustodio+"', "
                + " NOMBRE_CUSTODIO2 ='"+ncustodio2+"',"
                + " PLACAS_CUSTODIA='"+placa+"',"
                + " DSP_MOV_CUSTODIA="+celcustodio+", "
                +"  STATUS_CUSTODIA =1 " 
                + " where EMBARQUE_ID='"+EMBARQUE+"'";
            System.out.println("sql"+sql);
            
   PreparedStatement statement = generico.getConnection().prepareStatement(sql);
               // Ejecutar la consulta
            int rowsUpdated = statement.executeUpdate();
             // Verificar el número de filas actualizadas
            System.out.println("Filas actualizadas: " + rowsUpdated);
             
            if(rowsUpdated>0  ){
                
                //enviar correo con pdf
            out.print("Datos guardados ");
              Email correot = new Email(); 
                  correot.alertaConfirmacionCustodia(EMBARQUE,EMBARQUE);
            }else{
            out.print("Error porfavor verifica tus datos");
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
