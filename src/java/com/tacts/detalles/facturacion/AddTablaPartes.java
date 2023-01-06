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
public class AddTablaPartes extends HttpServlet {


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
        
        String concepto_id = request.getParameter("parte_concepto_id");  
        String upParte_desc = request.getParameter("upParte_desc");
        String upParte_Cantidad = request.getParameter("upParte_Cantidad");
        String upParte_precioUnitario = request.getParameter("upParte_precioUnitario");
        String upParte_ClaveProdServ = request.getParameter("upParte_ClaveProdServ");
        String upParte_unidad = request.getParameter("upParte_unidad");
        String upParte_NoIdentificacion= request.getParameter("upParte_NoIdentificacion");	  
        String importe = "0.0";
        
        int contador = Integer.parseInt(request.getParameter("contadorPartes"));
        
        String salida = "";
        int sal = contador;
        
        //Armado de tabla
        salida += "<tr id=\"part" + sal + "\"> "
                + "     <td><center>" + upParte_desc + "</center><input value=\"" + upParte_desc + "\" name=\"tqParte_desc[" + sal + "]\"  id=\"tqParte_desc[" + sal + "]\" type=\"hidden\" /><input value=\"" + concepto_id + "\" name=\"tqconcepto_id[" + sal + "]\"  id=\"tqconcepto_id[" + sal + "]\" type=\"hidden\" /></td>"
                + "     <td><center>" + upParte_Cantidad + "</center><input value=\"" + upParte_Cantidad + "\" name=\"tqParte_Cantidad[" + sal + "]\"  id=\"tqParte_Cantidad[" + sal + "]\" type=\"hidden\" /></td>"
                + "     <td><center>" + upParte_ClaveProdServ + "</center><input value=\"" + upParte_ClaveProdServ + "\" name=\"tqParte_ClaveProdServ[" + sal + "]\"  id=\"tqParte_ClaveProdServ[" + sal + "]\" type=\"hidden\" /></td>"
                + "     <td><center>" + upParte_unidad + "</center><input value=\"" + upParte_unidad + "\" name=\"tqParte_unidad[" + sal + "]\"  id=\"tqParte_unidad[" + sal + "]\" type=\"hidden\" /></td>"
                + "     <td><center>" + upParte_precioUnitario + "</center><input value=\"" + upParte_precioUnitario + "\" name=\"tqParte_precioUnitario[" + sal + "]\"  id=\"tqParte_precioUnitario[" + sal + "]\" type=\"hidden\" /></td>"
                + "     <td>" + importe + "</td>"
                + "     <td><center>" + upParte_NoIdentificacion + "</center><input value=\"" + upParte_NoIdentificacion + "\" name=\"tqParte_NoIdentificacion[" + sal + "]\"  id=\"tqParte_NoIdentificacion[" + sal + "]\" type=\"hidden\" /></td>"
                + "  <td><a class=\"text-lg text-info\" onclick=\"delete_partes('" + sal + "')\"><i class=\"far fa-trash-alt\"></i></a></td>"
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
            Logger.getLogger(AddTablaConceptos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddTablaConceptos.class.getName()).log(Level.SEVERE, null, ex);
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
