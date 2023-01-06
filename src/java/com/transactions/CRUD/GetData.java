/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transactions.CRUD;

import com.dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author OVALOIS
 */
@WebServlet(name = "GetData", urlPatterns = {"/GetData"})
public class GetData extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* Establecemos Conexion */
           ServiceDAO conn = new ServiceDAO();  
           String consulta = request.getParameter("consulta");

            try {
                Statement stmt = conn.conectar().prepareStatement(consulta);
                ResultSet rs = stmt.executeQuery(consulta);
                ResultSetMetaData rsMetaData = rs.getMetaData();

                int numberOfColumns = rsMetaData.getColumnCount();
                /* Creamos JSON */
                out.print("[");
                rs.next();
                /* Iteramos JSON */
                while (true) {
                    /* Comienza ROW */
                    out.print("{");
                    /*  Creamos Inner en columnas */
                    for (int i = 1; i <= numberOfColumns; i++) {
                        out.print("\"" + rsMetaData.getColumnName(i) + "\":\""
                                + rs.getString(rsMetaData.getColumnName(i)) + "\"");
                        if (i < numberOfColumns) {
                            out.print(",");
                        }
                    }
                    out.print("}");
                    if (rs.next()) {
                        out.print(",\n");
                    } else {
                        out.print("]");
                        break;
                    }
                }
                stmt.close();
                rs.close();
                stmt = null;
                rs = null;

            } catch (SQLException e) {
            }
            conn = null;
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
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
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
