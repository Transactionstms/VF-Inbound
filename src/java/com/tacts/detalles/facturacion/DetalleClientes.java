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
public class DetalleClientes extends HttpServlet {
    
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

          String detalleClientes ="SELECT DISTINCT       "
                                + " CLIENTE_ID,          "
                                + " CLIENTE_DESCRIPCION, "
                                + " RFC,                 "
                                + " NOMBRE_COMERCIAL,    "
                                + " CODIGO_CLIENTE,      "
                                + " NUM_CLIENTE,         "
                                + " NACIONALIDAD_ID,     "
                                + " CALLE,               "
                                + " NUM_EXT,             "
                                + " NUM_INT,             "
                                + " COLONIA,             "
                                + " LOCALIDAD,           "
                                + " REFERENCIA,          "
                                + " NUM_REGID_TRIB,      "
                                + " CODIGO_POSTAL,       "
                                + " PAIS_ID,             "
                                + " PROVINCIA_ID,        "
                                + " ESTADO_ID,           "
                                + " MUNICIPIO_ID,        "
                                + " NOMBRE_COMPRADOR,    "
                                + " METODO_ID,           "
                                + " FORMA_ID,            "
                                + " USOCFDI_ID,          "
                                + " TIPOENVIO_ID,        "
                                + " REL_CONTAC_ID,       "
                                + " TELEFONO,            "
                                + " PAGINA_WEB,          "
                                + " COMENT_EMAIL_CFDI,   "
                                + " REL_DATBANC_ID,      "
                                + " FECHA_REGISTRO,      "
                                + " ESTATUS_ID,          "
                                + " CBDIV_ID             "
                                + " FROM TRA_CLIENTE "
                                + " AND CBDIV_ID = '" + cve + "' ";

        ServiceDAO dao = new ServiceDAO();
        ResultSet rs = dao.consulta(detalleClientes);
        int sal = 0;
       

            out.print("<div class=\"card card-table\">  "
                    + "  <div class=\"preload-wrapper\">"
                    + "    <table class=\"table table-hover table-borderless align-middle mb-0\" id=\"productsDatatable\"> "
                    + "    <thead> "
                    + "      <tr>"
                    + "          <th>Código de Cliente</th> "
                    + "          <th>RFC</th> "
                    + "          <th>Nombre del Cliente</th> "
                    + "          <th>Contacto</th> "
                    + "          <th>Correo</th> "
                    + "          <th>Teléfono</th> "
                    + "          <th style=\"min-width: 50px;\"> </th>"
                    + "      </tr>"
                    + "    </thead> "
                    + "  <tbody>");

            String salida = " ";
            while (rs.next()) {

                String codigo_cliente = rs.getString(1);
                String rfc = rs.getString(2);
                String nombre_cliente = rs.getString(3);
                String contacto = rs.getString(4);
                String correo = rs.getString(5);
                String telefono = rs.getString(6);
                
                salida += " <tr class=\"align-middle\"> "
                        + "    <td>" + codigo_cliente + "</td> "
                        + "    <td>" + rfc + "</td> "
                        + "    <td>" + nombre_cliente + "</td> "
                        + "    <td>" + contacto + "</td> "
                        + "    <td>" + correo + "</td> "
                        + "    <td>" + telefono + "</td> "
                        + "    <td><a class=\"me-3 text-lg text-success\" href=\"../Bancos/List.jsp\"><i class=\"far fa-edit\"></i></a><a class=\"text-lg text-success\" href=\"Details.jsp\"><i class=\"far fa-edit\"></i></a>  <a class=\"me-3 text-lg text-success\" href=\"Edit.jsp\"><i class=\"far fa-edit\"></i></a><a class=\"text-lg text-danger\" href=\"\"><i class=\"far fa-trash-alt\"></i></a></td> "
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
            Logger.getLogger(DetalleClientes.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DetalleClientes.class.getName()).log(Level.SEVERE, null, ex);
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