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

            String tipoAgente = request.getParameter("tipoAgente");
            String tipoConsulta = request.getParameter("tipoConsulta"); //(evento/shipment/container)
            String id = request.getParameter("id"); //id
            String salida = "";
            int sal = 0;
            
            System.out.println("tipoAgente:" + tipoAgente);
            System.out.println("tipoConsulta:" + tipoConsulta);
            System.out.println("id:" + id);
             
            ServiceDAO dao = new ServiceDAO();
            ResultSet rs = dao.consulta(fac.consultarEventosCustoms(tipoConsulta,id));
            while (rs.next()) {

                salida += " <tr> "
                        + "    <td class=\"font-numero\"><center><img src=\"../img/circle-green.png\" width=\"40%\"/></center></td> "  //Semaforo            // Semaforo
                        + "    <th class=\"font-numero\">"+rs.getString(1)+"</th> "  // Número de Evento
                        + "    <td class=\"font-numero\"> "                          // Referencia Aduanal
                        + "        <input class=\"form-control\" id=\"referenciaAA\" name=\"referenciaAA\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\">"+rs.getString(2)+"</td> "  // Responsable
                        + "    <td class=\"font-numero\">"+rs.getString(3)+"</td> "  // Final Destination
                        + "    <td class=\"font-numero\">"+rs.getString(4)+"</td> "  // Brand-Division
                        + "    <td class=\"font-numero\">"+rs.getString(5)+"</td> "  // Division
                        + "    <td class=\"font-numero\">"+rs.getString(6)+"</td> "  // Shipment ID
                        + "    <td class=\"font-numero\">"+rs.getString(7)+"</td> "  // Container
                        + "    <td class=\"font-numero\">"+rs.getString(8)+"</td> "  // BL/AWB/PRO
                        + "    <td class=\"font-numero\">"+rs.getString(9)+"</td> "  // LoadType
                        + "    <td class=\"font-numero\">"+rs.getString(10)+"</td> " // Quantity
                        + "    <td class=\"font-numero\">"+rs.getString(11)+"</td> " // POD
                        + "    <td class=\"font-numero\">"+rs.getString(12)+"</td> " // Est. Departure from POL
                        + "    <td class=\"font-numero\">"+rs.getString(13)+"</td> " // ETA REAL Port of Discharge
                        + "    <td class=\"font-numero\">"+rs.getString(14)+"</td> " // Est. Eta DC
                        + "    <td class=\"font-numero\"></td> "                     // Inbound notification
                        + "    <td class=\"font-numero\">"+rs.getString(16)+"</td> " // POL
                        + "    <td class=\"font-numero\">"+rs.getString(17)+"</td> " // A.A.
                        + "    <td class=\"font-numero\"></td> "                     // Fecha Mes de Venta 
                        + "    <td class=\"font-numero\"> "                          // Prioridad Si/No
                        + "      <select class=\"form-control\" id=\"shipment_id\" name=\"shipment_id\" required=\"true\">" 
                        + "         <option value=\"Si\">SI</option>" 
                        + "         <option value=\"No\" disabled selected>NO</option>" 
                        + "      </select>" 
                        + "    </td> "
                        + "    <td class=\"font-numero\"></td> "                     // Campo en blanco
                        + "    <td class=\"font-numero\"> "
                        + "        <input class=\"form-control\" id=\"pais_origen\" name=\"pais_origen\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "        <input class=\"form-control\" id=\"size_container\" name=\"size_container\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "        <input class=\"form-control\" id=\"valor_usd\" name=\"valor_usd\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"eta_port_discharge\" name=\"eta_port_discharge\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"agente_aduanal\" name=\"agente_aduanal\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"pedimento_a1\" name=\"pedimento_a1\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"pedimento_r1_1er\" name=\"pedimento_r1_1er\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"motivo_rectificacion_1er\" name=\"motivo_rectificacion_1er\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"pedimento_r1_2do\" name=\"pedimento_r1_2do\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"motivo_rectificacion_2do\" name=\"motivo_rectificacion_2do\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_recepcion_doc\" name=\"fecha_recepcion_doc\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"recinto\" name=\"recinto\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"naviera\" name=\"naviera\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"buque\" name=\"buque\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_revalidacion\" name=\"fecha_revalidacion\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_previo_origen\" name=\"fecha_previo_origen\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_previo_destino\" name=\"fecha_previo_destino\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_resultado_previo\" name=\"fecha_resultado_previo\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"proforma_final\" name=\"proforma_final\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"permiso\" name=\"permiso\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_envio\" name=\"fecha_envio\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_recepcion_perm\" name=\"fecha_recepcion_perm\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_activacion_perm\" name=\"fecha_activacion_perm\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_permisos_aut\" name=\"fecha_permisos_aut\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"co_pref_arancelaria\" name=\"co_pref_arancelaria\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"aplic_pref_arancelaria\" name=\"aplic_pref_arancelaria\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"req_uva\" name=\"req_uva\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"req_ca\" name=\"req_ca\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_recepcion_ca\" name=\"fecha_recepcion_ca\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> " 
                        + "    <td class=\"font-numero\"> " 
                        + "      <input class=\"form-control\" id=\"num_constancia_ca\" name=\"num_constancia_ca\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> " 
                        + "    <td class=\"font-numero\"> " 
                        + "      <input class=\"form-control\" id=\"monto_ca\" name=\"monto_ca\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_doc_completos\" name=\"fecha_doc_completos\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_pago_pedimento\" name=\"fecha_pago_pedimento\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_solicitud_transporte\" name=\"fecha_solicitud_transporte\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_modulacion\" name=\"fecha_modulacion\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"modalidad\" name=\"modalidad\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"resultado_modulacion\" name=\"resultado_modulacion\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_reconocimiento\" name=\"fecha_reconocimiento\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_liberacion\" name=\"fecha_liberacion\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"sello_origen\" name=\"sello_origen\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"sello_final\" name=\"sello_final\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_retencion_aut\" name=\"fecha_retencion_aut\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_liberacion_aut\" name=\"fecha_liberacion_aut\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <select class=\"form-control\" id=\"estatus_operacion\" name=\"estatus_operacion\" required=\"true\"> "
                        + "          <option value=\"0\" disabled selected> SELECCIONE </option> "
                        + "          <option value=\"1\">EN TRANSITO</option> "
                        + "          <option value=\"2\">EN TRANSITO - PENDIENTE REVALIDACION</option> "
                        + "          <option value=\"3\">EN TRANSITO - REVALIDADO</option> "
                        + "          <option value=\"4\">EN PROCESO DE ARRIBO</option> "
                        + "          <option value=\"5\">EN PROCESO DE RECOLECCION</option> "
                        + "          <option value=\"6\">ARRIBADO</option> "
                        + "          <option value=\"7\">ARRIBADO - PENDIENTE REVALIDACION</option> "
                        + "          <option value=\"8\">REVALIDADO</option> "
                        + "          <option value=\"9\">EN ESPERA DE PREVIO</option> "
                        + "          <option value=\"10\">RETENIDO POR LA AUTORIDAD</option> "
                        + "          <option value=\"11\">EN PREVIO</option> "
                        + "          <option value=\"12\">EN GLOSA</option> "
                        + "          <option value=\"13\">EN ESPERA DE DOCUMENTOS</option> "
                        + "          <option value=\"14\">EN PROCESO DE PAGO DE PEDIMENTO</option> "
                        + "          <option value=\"15\">PEDIMENTO PAGADO</option> "
                        + "          <option value=\"16\">EN ESPERA DE INSTRUCCIONES PARA DESPACHO</option> "
                        + "          <option value=\"17\">EN PROGRAMACION DE DESPACHO</option> "
                        + "          <option value=\"18\">EN DESPACHO</option> "
                        + "      </select> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"motivo_atraso\" name=\"motivo_atraso\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"observaciones\" name=\"observaciones\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> ";
                
            if(tipoAgente.equals("1")){        //Logix
                salida += " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> ";
                    
            }else if(tipoAgente.equals("2")){  //Cusa
                salida += " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"\" name=\"\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> ";
            }
                salida += "</tr>";
                sal++;
            }
            System.out.println("Tabla:"+salida);
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
