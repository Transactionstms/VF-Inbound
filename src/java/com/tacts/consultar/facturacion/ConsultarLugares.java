/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.consultar.facturacion;

import com.dao.ServiceDAO;
import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.consultas.ConsultasQuery;
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
 * @author luis_
 */
public class ConsultarLugares extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
            String UserId = (String) ownsession.getAttribute("login.user_id_number");
            OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
            String cve = (String) ownsession.getAttribute("cbdivcuenta");

            ConsultasQuery fac = new ConsultasQuery();
            ServiceDAO dao = new ServiceDAO();

            String idTipo = request.getParameter("idTipo");
            String idLugarEmbarque = request.getParameter("idLugarEmbarque");
            String idLugarDestinatario = request.getParameter("idLugarDestinatario");
            String idLugarEntrega = request.getParameter("idLugarEntrega");
            String salida = "";
            String idLugar = "";
            
            if(idTipo.equals("1")){
                idLugar = idLugarEmbarque;
            }else if(idTipo.equals("2")){
                idLugar = idLugarDestinatario;
            }else if(idTipo.equals("3")){
                idLugar = idLugarEntrega;
            }
            
                ResultSet rs = dao.consulta(fac.ubicacionesGral(idLugar, cve));
                while (rs.next()) {
                    salida = rs.getString(1) + "*" + rs.getString(2) + "*" + rs.getString(3) + "*" + rs.getString(4) + "*" + rs.getString(5) + "*" + rs.getString(6) + "*" + rs.getString(7) + "*" + rs.getString(8) + "*" + rs.getString(9) + "*" + rs.getString(10)+ "*" + rs.getString(11)+ "*" + rs.getString(12)+ "*" + rs.getString(13)+ "*" + rs.getString(14) + "*" + rs.getString(15);
                }
                dao.CerrarConexion();
            
            out.print(salida);
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
            Logger.getLogger(ConsultarClaveSAT.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarClaveSAT.class.getName()).log(Level.SEVERE, null, ex);
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