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
 * @author Sergio Castro
 */
@WebServlet(name = "ActionsUsuarios", urlPatterns = {"/ActionsUsuarios"})
public class ActionsUsuarios extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        Class.forName("oracle.jdbc.OracleDriver");
        /*Establecemos conexion*/
        ServiceDAO con = new ServiceDAO(); 
        /*Tomamos parametros*/
        String dml_type = request.getParameter("dmlType");
        String emp_code = request.getParameter("empCode");
        String emp_name = request.getParameter("empName");
        String sql="";
        /*Valiamos la accion y ejecutamos query */
        if("Ins".equals(dml_type)) /*Insertar*/
          sql = "insert into tra_angulartest (NOMBRE, APELLIDO) values('" + emp_code + "','" + emp_name + "')";
        else if("Del".equals(dml_type))/*Borrar*/
          sql = "update tra_angulartest set bandera = '0' where nombre = '" + emp_code + "' and apellido = '" + emp_name + "'";
        else if("Upd".equals(dml_type))/*Actualizar*/
          sql = "update tra_angulartest set apellido = '" + emp_name + "' where nombre = '" + emp_code + "'" ;
        try
        {
          Statement stmt = con.conectar().prepareStatement(sql);
          ResultSet rs = stmt.executeQuery(sql);
          ResultSetMetaData rsMetaData = rs.getMetaData();
        }
        catch(SQLException e)
        {
         //e.printStackTrace();
           out.print("SQL Error encountered " + dml_type  + "," + e.getMessage());
        }
        con=null;
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionsUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActionsUsuarios.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionsUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActionsUsuarios.class.getName()).log(Level.SEVERE, null, ex);
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
