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
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
public class ConsultarCustom extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
            String UserId = (String) ownsession.getAttribute("login.user_id_number");
            String cve = (String) ownsession.getAttribute("cbdivcuenta");
            //String cve = "10";

            OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
            oraDB.connect(dbData.getUser(), dbData.getPassword());
            ConsultasQuery fac = new ConsultasQuery();
            NumberFormat formatter = new DecimalFormat("#0.00");

            String regId = request.getParameter("regId");
            String[] parts = regId.split(",");
            String id = parts[0];   //id principal 
            String tipo = parts[1]; //Tipo Consulta (evento/shipment/container)
            
           
            String serie = "";
            String folio = "";
            String uuid = "";
            String cliente_descripcion = "";
            String fecha_emision = "";
            String fecha_timbrado = "";
            String id_factura = "";
            String rfc_emisor = "";
            String rfc_receptor = "";
            String folio_fiscal = "";
            String email_1 = "";
            String email_2 = "";
            String email_3 = "";
            String coma = ",";
            String emails = "";
            float totalGloblal = 0;

            String sql = " SELECT DISTINCT "
                       + " NVL(TIE.ID_EVENTO,0), "
                       + " NVL(UPPER(TDR.RESPONSABLE),' '), "
                       + " NVL(TIGT.FINAL_DESTINATION,' '), "
                       + " NVL(TIBD.NOMBRE_BD,' '), "
                       + " NVL(TIGT.CBDIV_ID,0), "
                       + " NVL(TIE.SHIPMENT_ID,' '), "
                       + " NVL(TIE.CONTAINER1,' '), "
                       + " NVL(TIGT.BL_AWB_PRO,' '), "
                       + " NVL(TIGT.LOAD_TYPE,' '), "
                       + " NVL(TIGT.QUANTITY,0), "
                       + " NVL(TIP.NOMBRE_POD,' '), "
                       + " NVL(TO_CHAR(TIGT.EST_DEPARTURE_POL, 'dd/mm/yyyy'),' '), "
                       + " NVL(TO_CHAR(TIGT.ETA_PORT_DISCHARGE, 'dd/mm/yyyy'),' '), "
                       + " NVL(TO_CHAR(TIE.EST_ETA_DC, 'dd/mm/yyyy'),' '), "
                       + " NVL(TO_CHAR(TIE.FECHA_CAPTURA, 'dd/mm/yyyy'),' '), "
                       + " NVL(TIPL.NOMBRE_POL,' ') "
                       + " FROM TRA_INB_EVENTO TIE "
                       + " INNER JOIN TRA_INC_GTN_TEST TIGT ON TIE.PLANTILLA_ID = TIGT.PLANTILLA_ID "
                       + " INNER JOIN TRA_INB_BRAND_DIVISION TIBD ON TIGT.BRAND_DIVISION = TIBD.ID_BD "
                       + " INNER JOIN TRA_DESTINO_RESPONSABLE TDR ON TIE.USER_NID = TDR.USER_NID "
                       + " INNER JOIN TRA_INB_POD TIP ON TIGT.POD = TIP.ID_POD "
                       + " INNER JOIN TRA_INB_POL TIPL ON TIGT.POL = TIPL.ID_POL "
                       + " WHERE ";
            
                if(tipo.equals("1")){
                  sql += "TIE.ID_EVENTO = '" + id + "'";
                }else if(tipo.equals("2")){
                  sql += "TIE.SHIPMENT_ID = '" + id + "'";
                }else if(tipo.equals("3")){
                  sql += "TIE.CONTAINER1 = '" + id + "'";
                }
                
                 sql += " AND TIGT.ESTATUS = 1 "
                      + " ORDER BY NVL(TO_CHAR(TIE.FECHA_CAPTURA, 'dd/mm/yyyy'),' ') DESC ";

            ServiceDAO dao = new ServiceDAO();
            ResultSet rs = dao.consulta(sql);
            int sal = 0;

            String salida = " ";
            while (rs.next()) {

                serie = rs.getString(1);
                folio = rs.getString(2);
                uuid = rs.getString(3);
                cliente_descripcion = rs.getString(4);
                fecha_emision = rs.getString(5);
                totalGloblal = Float.parseFloat(rs.getString(6));
                id_factura = rs.getString(7);
                rfc_receptor = rs.getString(8);
                folio_fiscal = rs.getString(9).substring(Math.max(0, rs.getString(9).length() - 8));
                fecha_timbrado = rs.getString(10);

                salida += " <tr> "
                        + "    <td>" + serie + "</td> "
                        + "    <td>" + folio + "</td> "
                        + "    <td>" + uuid + "</td> "
                        + "    <td>" + cliente_descripcion + "</td> "
                        + "    <td>" + fecha_emision + "</td> "
                        + "    <td>$ " + formatter.format(totalGloblal) + "</td> "
                        + "    <td>ACTIVO</td> "
                        + "    <td>No Pagada</td> "
                        + "    <td>"
                        + "        <a class=\"text-lg text-info\" onclick=\"detalle_factura('" + id_factura + "')\"><i class=\"fa fa-info-circle\"></i></a>"
                        + "        <a class=\"text-lg text-info\" onclick=\"visor_factura('" + id_factura + "')\"><i class=\"fa fa-file\"></i></a>"
                        + "        <a class=\"text-lg text-info\" onclick=\"consultarEstatusSATCFDI('" + id_factura + "', '" + serie + "', '" + folio + "', '" + rfc_emisor + "', '" + rfc_receptor + "', '" + formatter.format(totalGloblal) + "', '" + uuid + "', '" + folio_fiscal + "', '" + fecha_emision + "', '" + fecha_timbrado + "', '" + emails + "')\"><i class=\"far fa-trash-alt\"></i></a>"
                        + "    </td>"
                        + " </tr>";
                sal++;
            }
            
            dao.CerrarConexion();
            oraDB.close();

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
            Logger.getLogger(ConsultarFacturas.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarFacturas.class.getName()).log(Level.SEVERE, null, ex);
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
