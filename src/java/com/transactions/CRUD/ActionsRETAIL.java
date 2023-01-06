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
 * @author OVALOIS
 */
@WebServlet(name = "ActionsRETAIL", urlPatterns = {"/ActionsRETAIL"})
public class ActionsRETAIL extends HttpServlet {

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
            String cantidad = request.getParameter("cantidad");
            String carton = request.getParameter("carton");
            String sql = "";
            String insert = "";
            int bandera = 0;
            /*Valiamos la accion y ejecutamos query */
            if ("close".equals(dml_type)) /*ASISTENCIA*/ {
                sql = "update tra_cajas set ESTADO_ID=2 "
                        + "        where TRA_CAJA in (SELECT tc.tra_caja FROM tra_cajas tc INNER JOIN ontms_docto_sal ods "
                        + "        ON tc.tra_caja_pedido = ods.docto_referencia INNER JOIN ontms_embarque oe "
                        + "        ON ods.docto_sal_agrupador = oe.embarque_agrupador INNER JOIN tra_tienda_usuario "
                        + "        ttu ON ttu.destino_ship_to = ods.destino_id WHERE ttu.nuser_id = '" + emp_code + "' AND  "
                        + "        ods.DOCTO_PROGRAMACION = '" + emp_name + "' AND   tc.estado_id = '1')";
                bandera = 2;

            } else if ("Del".equals(dml_type))/*Inasistencias*/ {
                sql = "update ontms_camion set CAMION_STS = 0 where CAMION_ID = '" + emp_code + "'";
                insert = "insert into TRA_HIST_ASISTENCIA_CAMION (ID_ASISTENCIA,FECHA_ASISTENCIA,ID_CAMION,OBS_ASISTENCIA,STATUS)values(null,SYSDATE," + emp_code + ",'" + emp_name + "',2)";
                bandera = 2;

            } else if ("Upd".equals(dml_type))/*Asistencia*/ {
                sql = "update tra_cajas set estado_id = 1, FECHA_INGRESO_TIENDA = sysdate where tra_caja = '" + emp_name + "'";
                bandera = 1;

            }else if ("ErrorC".equals(dml_type))/*Asistencia*/ {
                sql = "update tra_cajas set tra_caja_cantidad = '"+cantidad+"' where tra_caja_pedido in  ('" + carton + "')";
                bandera = 1;

            }
            try {
                Statement stmt = con.conectar().prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql);

                if (bandera == 1 || bandera == 2) {
                    Statement stmt1 = con.conectar().prepareStatement(insert);
                    ResultSet rs1 = stmt1.executeQuery(insert);
                    ResultSetMetaData rsMetaData1 = rs1.getMetaData();
                }
                ResultSetMetaData rsMetaData = rs.getMetaData();
            } catch (SQLException e) {
                //e.printStackTrace();
                out.print("SQL Error encountered " + dml_type + "," + e.getMessage());
            }
            con = null;
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
