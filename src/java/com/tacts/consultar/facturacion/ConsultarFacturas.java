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
public class ConsultarFacturas extends HttpServlet {

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

            String idCliente = request.getParameter("idCliente");
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
            
            String clienteEmisor = "SELECT DISTINCT RFC FROM TRA_CLIENTE WHERE CBDIV_ID = '" + cve + "'";
            
            if (db.doDB(clienteEmisor)) {
                for (String[] rowE : db.getResultado()) {
                    rfc_emisor = rowE[0];
                }
            }

            String listarP = " SELECT DISTINCT "
                           + " TF.SERIE, "
                           + " TF.FOLIO, "
                           + " TF.COM_UUID, "
                           + " TS.CLIENTE_DESCRIPCION, "
                           + " TO_CHAR(TF.FECHA_EMISION,'DD/MM/YYYY'), "
                           + " TF.TOTAL_GLOBAL, "
                           + " TF.CLVFACT_ID, "
                           + " TS.RFC, "
                           + " TF.SELLO, "
                           + " TF.COM_FECHATIMBRADO, "
                           + " TS.CORREO_CONTACTO1, "
                           + " TS.CORREO_CONTACTO2, "
                           + " TS.CORREO_CONTACTO3 "
                           + " FROM TRA_FACTURACION TF "
                           + " INNER JOIN TRA_SUSCLIENTES TS ON TF.CLIENTE_ID = TS.CLIENTE_ID "
                           + " INNER JOIN TRA_METODO_PAGO TMP ON TF.METODO_ID = TMP.METODO_ID "
                           + " INNER JOIN TRA_TIPO_MONEDA TTM ON TF.MONEDA_ID = TTM.MONEDA_ID "
                           + " WHERE TF.CLIENTE_ID = '" + idCliente + "' "
                           + " AND TF.CBDIV_ID = '" + cve + "' "
                           + " AND TF.COM_NOCERTIFICADOSAT IS NOT NULL ";

            ServiceDAO dao = new ServiceDAO();
            ResultSet rs = dao.consulta(listarP);
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
                
                if(rs.getString(11) != null){
                    email_1 = rs.getString(11) + " ";
                }else{
                    email_1 = "" + coma; 
                }
                
                if(rs.getString(12) != null){
                    email_2 = rs.getString(12) + " ";
                }else{
                    email_2 = "" + coma;
                }
                
                if(rs.getString(13) != null){
                    email_3 = rs.getString(13);
                }else{
                    email_3 = "" + coma;
                }
                
                //Se quitan correos vacíos(',')
                emails = email_1.replaceAll(",","") + email_2.replaceAll(",","") + email_3.replaceAll(",","");
                
                System.out.println("Consulta: " + listarP);
                System.out.println("https://www.rtms.mx/consultar/consultarSat.jsp?re=" + rfc_emisor + "&rr=" + rfc_receptor + "&tt=" + formatter.format(totalGloblal) + "&id=" + uuid + "&fe=" + folio_fiscal);

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