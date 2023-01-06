/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;

import com.dao.ServiceDAO;
import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
 * @author DesarrolloI
 */
public class ConsultaTiendas extends HttpServlet {

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
            throws ServletException, IOException, SQLException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession ownsession = request.getSession(false);
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            String cveCuenta = (String) ownsession.getAttribute("cbdivcuenta");

            String tipo_destrito = request.getParameter("tipo");
            String country = request.getParameter("country");
            String sin_tienda = "0";
            
            String res = "";

            if (country.equals("0")) {

                res += "<input type=\"hidden\" value=" + sin_tienda + " id=\"teams\" name=\"teams\">";
                out.println(res);

            }else{
                
                String sql = "SELECT DISTINCT DDH.DESTINO_SHIP_TO, UPPER(DDH.DESTINO_NOMBRE) FROM TRA_DISTRITOS_TIENDAS TDT INNER JOIN DILOG_DESTINOS_HILTI DDH ON DDH.DESTINO_SHIP_TO = TDT.DESTINO_SHIP_TO WHERE TDT.ID_DT='" + country + "' AND DDH.DESTINO_SALES_ORG='1' ORDER BY UPPER(DDH.DESTINO_NOMBRE) ASC";

                ServiceDAO dao = new ServiceDAO();
                ResultSet rs = dao.consulta(sql);
                int j = 0;
                while (rs.next()) {
                    if (j == 0) {
                        res += "<option> </option>";
                        j++;
                    }
                    String id = rs.getString(1);
                    String empresa_desc = rs.getString(2);

                    res += "<option value=" + id + ">" + empresa_desc + "</option>";
                }
                out.println("<center><label>TIENDAS</label></center>");
                out.println("<select class=\"chosen-select\" name=\"teams\" id=\"teams\">");
                out.println(res);
                out.println("</select>");
                
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
            Logger.getLogger(ConsultaTiendas.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultaTiendas.class.getName()).log(Level.SEVERE, null, ex);
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
