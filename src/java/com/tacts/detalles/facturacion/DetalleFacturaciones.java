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
public class DetalleFacturaciones extends HttpServlet {
    
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

             String detalleFact = "SELECT DISTINCT "
                                + " CLVFACT_ID,        "
                                + " CLIENTE_ID,        "
                                + " RFC,               "
                                + " USOCFDI_ID,        "
                                + " CORREOS,           "
                                + " TIPOENVIO_ID,      "
                                + " TIPO_DESCRIPCION,  "
                                + " FECHA_EMISION,     "
                                + " HORA_EMISION,      "
                                + " SERIE,             "
                                + " FOLIO,             "
                                + " COMPROBANTE_ID,    "
                                + " DOCUMENTO_ID,      "
                                + " REGIMEN_ID,        "
                                + " COMENTARIOS,       "
                                + " CONCEPTO_ID,       "
                                + " CANTIDAD,          "
                                + " REL_CLVSAT_ID,     "
                                + " UNIDAD_SAT_ID,     "
                                + " PRECIO_UNITARIO,   "
                                + " METODO_ID,         "
                                + " FORMA_ID,          "
                                + " CONDICIONES_PAGO,  "
                                + " MONEDA_ID,         "
                                + " TIPO_CAMBIO,       "
                                + " REL_CLVCFDI_ID,    "
                                + " REL_CLVCOM_ID      "
                                + " FROM TRA_FACTURACION "
                                + " AND CBDIV_ID = '" + cve + "' ";

        ServiceDAO dao = new ServiceDAO();
        ResultSet rs = dao.consulta(detalleFact);
        int sal = 0;
       

            out.print("<div class=\"card card-table\">  "
                    + "  <div class=\"preload-wrapper\">"
                    + "    <table class=\"table table-hover table-borderless align-middle mb-0\" id=\"productsDatatable\"> "
                    + "    <thead> "
                    + "      <tr>"
                    + "          <th>Serie</th> "
                    + "          <th>Folio</th> "
                    + "          <th>UUID</th> "
                    + "          <th>Cliente</th> "
                    + "          <th>Fecha de Emisión</th> "
                    + "          <th>Total</th> "
                    + "          <th>Estatus</th> "
                    + "          <th>Estatus de Pago</th> "
                    + "          <th style=\"min-width: 50px;\"> </th>"
                    + "      </tr>"
                    + "    </thead> "
                    + "  <tbody>");

            String salida = " ";
            while (rs.next()) {

                String serie = rs.getString(1);
                String folio = rs.getString(2);
                String uuid = rs.getString(3);
                String cliente = rs.getString(4);
                String fechaEmision = rs.getString(5);
                String total = rs.getString(6);
                String estatus = rs.getString(7);
                String estatus_pago = rs.getString(8);
                
                salida += " <tr class=\"align-middle\"> "
                        + "    <td>" + serie + "</td> "
                        + "    <td>" + folio + "</td> "
                        + "    <td>" + uuid + "</td> "
                        + "    <td>" + cliente + "</td> "
                        + "    <td>" + fechaEmision + "</td> "
                        + "    <td>" + total + "</td> "
                        + "    <td>" + estatus + "</td> "
                        + "    <td>" + estatus_pago + "</td> "
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
            Logger.getLogger(DetalleFacturaciones.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DetalleFacturaciones.class.getName()).log(Level.SEVERE, null, ex);
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
