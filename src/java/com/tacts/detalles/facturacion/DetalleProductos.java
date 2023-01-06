/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.detalles.facturacion;

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
public class DetalleProductos extends HttpServlet {
    
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

          String detalleProducto =" SELECT DISTINCT "
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
                                + " FROM  "
                                + " TRA_PRODUCTOS_SERVICIOS"
                                + " AND CBDIV_ID = '" + cve + "' ";

        ServiceDAO dao = new ServiceDAO();
        ResultSet rs = dao.consulta(detalleProducto);
        int sal = 0;
       

            out.print("<div class=\"card card-table\">  "
                    + "  <div class=\"preload-wrapper\">"
                    + "    <table class=\"table table-hover table-borderless align-middle mb-0\" id=\"productsDatatable\"> "
                    + "    <thead> "
                    + "      <tr>"
                    + "          <th>Código</th> "
                    + "          <th>Clave de Producto SAT</th> "
                    + "          <th>Descripción</th> "
                    + "          <th>Valor Unitario</th> "
                    + "          <th style=\"min-width: 50px;\"> </th>"
                    + "      </tr>"
                    + "    </thead> "
                    + "  <tbody>");

            String salida = " ";
            while (rs.next()) {

                String codigo = rs.getString(1);
                String clave_producto_sat = rs.getString(2);
                String descripcion = rs.getString(3);
                String valorUnitario = rs.getString(4);
                
                salida += " <tr class=\"align-middle\"> "
                        + "    <td>" + codigo + "</td> "
                        + "    <td>" + clave_producto_sat + "</td> "
                        + "    <td>" + descripcion + "</td> "
                        + "    <td>" + valorUnitario + "</td> "
                        + "    <td><a class=\"me-3 text-lg text-success\" href=\"Details.jsp\"><i class=\"far fa-edit\"></i></a><a class=\"text-lg text-success\" href=\"Upd.jsp\"><i class=\"far fa-edit\"></i></a></td>"
                        + " </tr>";
                sal++;

            }
            dao.CerrarConexion();

            if (sal > 0) {
                out.print(salida);

            } else {
                out.print("No se encontraron datos");

            }
            out.print("</tbody></table></div></div>");
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
            Logger.getLogger(DetalleProductos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DetalleProductos.class.getName()).log(Level.SEVERE, null, ex);
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