/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;

import com.conexion.GenericJdbc;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author grecendiz
 */
public class ConsultarRepartoCustodias extends HttpServlet {

    
      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String transportista=request.getParameter("transporte");
      
         GenericJdbc generico = new GenericJdbc();
         generico.openConection();
         Statement statement = generico.getConnection().createStatement();
                    
      
         
         String sql = " "
                 + " "
                 + " select "
                 + " DISTINCT tie.EMBARQUE_ID,"
                 + " tie.EMBARQUE_AGRUPADOR,"
                 + " TO_CHAR(tie.EMBARQUE_FEC_ENRAMPE, 'MM/DD/YY'),"
                 + " TO_CHAR(tie.EMBARQUE_FEC_INICIO, 'MM/DD/YY'),"
                 + " tc.TC_DESCRIPCION "
                 + " from TRA_INB_EMBARQUE  tie "
                 + " inner join tra_inb_tipo_custodia  tc on tc.ID_TC=tie.EMBARQUE_TCUSTODIA"
                 + " where STATUS_CUSTODIA is null and tie.EMBARQUE_TCUSTODIA="+transportista
                 + "";
          System.out.println(sql); 
    
   
        
        try (PrintWriter out = response.getWriter()) {
         
            int sal = 0;
               ResultSet rs = statement.executeQuery(sql);

            String salida = ""
                    + " <table id=\"myTable\" class=\"display nowrap\" cellspacing=\"0\" width=\"100%\">\n" +
"                                                                    <thead class=\"thead-dark\">\n" +
"                                                                        <tr>" +
"                                                                            <th>Embarque                 </th>\n" +
"                                                                            <th>Fecha de enrampe         </th>\n" +
"                                                                            <th>Fecha inicio de entrega  </th>\n" +
                    
 "                                                                           <th>Modificar      </th>\n" +  
                    
"                                                                        </tr>" + 
"                                                                    </thead> " +
"                                                                    <tbody>"
                    + "";
            
             
            while (rs.next()) {  
                 System.out.println("---**"+rs.getString(1));
              
                
                 salida += ""
                        + " <tr>\n" +
                          "     <td class=\"\"><b>"+rs.getString(1)+"</b></td> \n" +
                          "     <td class=\"\"><b>"+rs.getString(3)+"</b></td> \n" +
                          "     <td class=\"\"><b>"+rs.getString(4)+"</b></td>\n" +
                          "     <td class=\"\"> <button type=\"button\" class=\"btn btn-primary\" onclick=\"muestraRepartos('"+rs.getString(1)+"','"+rs.getString(2)+"','"+rs.getString(5)+"')\">Modificar</button> </td>\n" +
                          "      " +
                          " </tr>"
                        + ""; 
                 
                sal++;
            }
            
             salida += "</tbody></table>";
           // dao.CerrarConexion();
         //  dbConnection.
           
             
            if (sal > 0) {
                out.print(salida);

            } else {
                out.print("No se encontraron datos");

            }
            statement.close();
          
             rs.close();
         
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

