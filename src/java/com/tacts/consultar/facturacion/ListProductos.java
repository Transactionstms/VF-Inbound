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
public class ListProductos extends HttpServlet {
    
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

          String listarProductos = " SELECT DISTINCT "
                                + " CLVREG_ID,      "
                                + " TIPO_REGISTRO, "
                                + " CODIGO,         "
                                + " DESCRIPCION,    "
                                + " VALOR_UNITARIO, "
                                + " UNIDAD_MEDIDA,  "
                                + " IVA_ID,         "
                                + " IVA_RET,        "
                                + " IEPSTASA_ID,    "
                                + " IEPS_CUOTA,     "
                                + " ISR,            "
                                + " EPSRET_ID,      "
                                + " IEPS_RET_CUOTA, "
                                + " REL_CLVSAT_ID,  "
                                + " UNIDAD_SAT_ID,  "
                                + " FECHA_REGISTRO, "
                                + " ESTADO_ID,      "
                                + " CBDIV_ID       "
                                + " FROM TRA_PRODUCTOS_SERVICIOS "
                                + " WHERE ESTADO_ID = 1 "
                                + " AND CBDIV_ID = '" + cve + "'  "
                                + " ORDER BY TIPO_REGISTRO DESC ";

        ServiceDAO dao = new ServiceDAO();
        ResultSet rs = dao.consulta(listarProductos);
        int sal = 0;
       
            String salida = " ";
            while (rs.next()) {
                String clvreg_id = rs.getString(1); 
                String codigo = rs.getString(3);
                String clave_producto_sat = rs.getString(14);
                String descripcion = rs.getString(4);
                String valorUnitario = rs.getString(5);
                
                salida += " <tr class=\"align-middle\"> "
                        + "    <td>" + codigo + "</td> "
                        + "    <td>" + clave_producto_sat + "</td> "
                        + "    <td>" + descripcion + "</td> "
                        + "    <td>$ " + valorUnitario + "</td> "
                        + "    <td>"
                        //+ "        <a class=\"me-1 text-lg text-info\" href=\"Details.jsp?clvreg_id=" + clvreg_id + "\"><i class=\"fas fa-info-circle\"></i></a> "
                        + "        <a class=\"me-1 text-lg text-info\" href=\"Upd.jsp?clvreg_id=" + clvreg_id + "\"><i class=\"far fa-edit\"></i></a>"
                        + "        <a class=\"text-lg text-info\" onclick=\"eliminar(" + clvreg_id + ");\"><i class=\"far fa-trash-alt\"></i></a>"
                        + "    </td>"
                        + " </tr>";
                sal++;

            }
            dao.CerrarConexion();

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
            Logger.getLogger(ListProductos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ListProductos.class.getName()).log(Level.SEVERE, null, ex);
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