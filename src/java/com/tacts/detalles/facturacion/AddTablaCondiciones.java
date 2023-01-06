/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.detalles.facturacion;

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
public class AddTablaCondiciones extends HttpServlet {


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");


        HttpSession ownsession = request.getSession();
        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
        DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        String cve = (String) ownsession.getAttribute("cbdivcuenta");
        String UserId = (String) ownsession.getAttribute("login.user_id_number");
        
        try (PrintWriter out = response.getWriter()) {
        
        String tiporelacion_id = request.getParameter("tiporelacion_id"); 
        String uuid_id = request.getParameter("uuid_id"); 
        String total_cfdi = request.getParameter("total_cfdi"); 
        
        int contador = Integer.parseInt(request.getParameter("contadorUUID"));
        
        String salida = "";
        int sal = contador;
        
        //Armado de tabla
        salida += "<tr id=\"uuid" + sal + "\"> "
                + " <td>" + uuid_id + "<input value=\"" + tiporelacion_id + "\" name=\"tiporelacion_id[" + sal + "]\"  id=\"tiporelacion_id[" + sal + "]\" type=\"hidden\" /><input value=\"" + uuid_id + "\" name=\"uuid_id[" + sal + "]\"  id=\"uuid_id[" + sal + "]\" type=\"hidden\" /></td> "
                + " <td>" + total_cfdi + "<input value=\"" + total_cfdi + "\" name=\"total_cfdi[" + sal + "]\"  id=\"total_cfdi[" + sal + "]\" type=\"hidden\" /></td> "
                + " <td><a class=\"text-lg text-info\" onclick=\"delete_uuid('" + sal + "')\"><i class=\"far fa-trash-alt\"></i></a></td>"
                + "</tr> ";

                sal++;
                
            if (sal > 0) {
                 out.print(salida);
            } else {
                out.print("No se encontraron datos");

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
            Logger.getLogger(AddTablaCondiciones.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddTablaCondiciones.class.getName()).log(Level.SEVERE, null, ex);
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