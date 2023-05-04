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
             
            ServiceDAO dao = new ServiceDAO();
            ResultSet rs = dao.consulta(fac.consultarEventosCustoms(tipoConsulta,id));
            while (rs.next()) {

                salida += " <tr id=\"tr" + sal + "\"> "
                        + "    <td class=\"font-numero\"><center><img src=\"../img/circle-green.png\" width=\"40%\"/></center></td> "  //Semaforo            // Semaforo
                        + "    <th class=\"font-numero\">"+rs.getString(1)+"<input type=\"hidden\" id=\"evento[" + sal + "]\" name=\"evento[" + sal + "]\" value=\""+rs.getString(1)+"\"></th> "  // Número de Evento
                        + "    <td class=\"font-numero\"> "                          // Referencia Aduanal
                        + "        <input type=\"text\" class=\"form-control\" id=\"referenciaAA[" + sal + "]\" name=\"referenciaAA[" + sal + "]\" value=\""+rs.getString(19)+"\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\">"+rs.getString(2)+"</td> "  // Responsable
                        + "    <td class=\"font-numero\">"+rs.getString(3)+"</td> "  // Final Destination
                        + "    <td class=\"font-numero\">"+rs.getString(4)+"</td> "  // Brand-Division
                        + "    <td class=\"font-numero\">"+rs.getString(5)+"</td> "  // Division
                        + "    <td class=\"font-numero\">"+rs.getString(6)+"<input type=\"hidden\" id=\"shipmentId[" + sal + "]\" name=\"shipmentId[" + sal + "]\" value=\""+rs.getString(6)+"\"></td> "  // Shipment ID
                        + "    <td class=\"font-numero\">"+rs.getString(7)+"<input type=\"hidden\" id=\"containerId[" + sal + "]\" name=\"containerId[" + sal + "]\" value=\""+rs.getString(7)+"\"></td> "  // Container
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
                        + "      <select class=\"form-control\" id=\"prioridad[" + sal + "]\" name=\"prioridad[" + sal + "]\" value=\""+rs.getString(20)+"\">" 
                        + "         <option value=\"Si\">SI</option>" 
                        + "         <option value=\"No\" disabled selected>NO</option>" 
                        + "      </select>" 
                        + "    </td> "
                        + "    <td class=\"font-numero\"></td> "                     // Campo en blanco
                        + "    <td class=\"font-numero\"> "
                        + "        <input class=\"form-control\" id=\"pais_origen[" + sal + "]\" name=\"pais_origen[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "        <input class=\"form-control\" id=\"size_container[" + sal + "]\" name=\"size_container[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "        <input class=\"form-control\" id=\"valor_usd[" + sal + "]\" name=\"valor_usd[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"eta_port_discharge[" + sal + "]\" name=\"eta_port_discharge[" + sal + "]\" type=\"date\" onchange=\"onSemaforo()\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"agente_aduanal[" + sal + "]\" name=\"agente_aduanal[" + sal + "]\" type=\"text\" value=\""+rs.getString(17)+"\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"pedimento_a1[" + sal + "]\" name=\"pedimento_a1[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"pedimento_r1_1er[" + sal + "]\" name=\"pedimento_r1_1er[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"motivo_rectificacion_1er[" + sal + "]\" name=\"motivo_rectificacion_1er[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"pedimento_r1_2do[" + sal + "]\" name=\"pedimento_r1_2do[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"motivo_rectificacion_2do[" + sal + "]\" name=\"motivo_rectificacion_2do[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_recepcion_doc[" + sal + "]\" name=\"fecha_recepcion_doc[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"recinto[" + sal + "]\" name=\"recinto[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"naviera[" + sal + "]\" name=\"naviera[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"buque[" + sal + "]\" name=\"buque[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_revalidacion[" + sal + "]\" name=\"fecha_revalidacion[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_previo_origen[" + sal + "]\" name=\"fecha_previo_origen[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_previo_destino[" + sal + "]\" name=\"fecha_previo_destino[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_resultado_previo[" + sal + "]\" name=\"fecha_resultado_previo[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"proforma_final[" + sal + "]\" name=\"proforma_final[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <select class=\"form-control\" id=\"permiso[" + sal + "]\" name=\"permiso[" + sal + "]\" value=\"\">" 
                        + "         <option value=\"Si\">SI</option>" 
                        + "         <option value=\"No\" disabled selected>NO</option>" 
                        + "      </select>" 
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_envio[" + sal + "]\" name=\"fecha_envio[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_recepcion_perm[" + sal + "]\" name=\"fecha_recepcion_perm[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_activacion_perm[" + sal + "]\" name=\"fecha_activacion_perm[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_permisos_aut[" + sal + "]\" name=\"fecha_permisos_aut[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <select class=\"form-control\" id=\"co_pref_arancelaria[" + sal + "]\" name=\"co_pref_arancelaria[" + sal + "]\" value=\"\">" 
                        + "         <option value=\"Si\">SI</option>" 
                        + "         <option value=\"No\" disabled selected>NO</option>" 
                        + "      </select>" 
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <select class=\"form-control\" id=\"aplic_pref_arancelaria[" + sal + "]\" name=\"aplic_pref_arancelaria[" + sal + "]\" value=\"\">" 
                        + "         <option value=\"Si\">SI</option>" 
                        + "         <option value=\"No\" disabled selected>NO</option>" 
                        + "      </select>" 
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <select class=\"form-control\" id=\"req_uva[" + sal + "]\" name=\"req_uva[" + sal + "]\" value=\"\">" 
                        + "         <option value=\"Si\">SI</option>" 
                        + "         <option value=\"No\" disabled selected>NO</option>" 
                        + "      </select>" 
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <select class=\"form-control\" id=\"req_ca[" + sal + "]\" name=\"req_ca[" + sal + "]\" value=\"\">" 
                        + "         <option value=\"Si\">SI</option>" 
                        + "         <option value=\"No\" disabled selected>NO</option>" 
                        + "      </select>" 
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_recepcion_ca[" + sal + "]\" name=\"fecha_recepcion_ca[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> " 
                        + "    <td class=\"font-numero\"> " 
                        + "      <input class=\"form-control\" id=\"num_constancia_ca[" + sal + "]\" name=\"num_constancia_ca[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> " 
                        + "    <td class=\"font-numero\"> " 
                        + "      <input class=\"form-control\" id=\"monto_ca[" + sal + "]\" name=\"monto_ca[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_doc_completos[" + sal + "]\" name=\"fecha_doc_completos[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_pago_pedimento[" + sal + "]\" name=\"fecha_pago_pedimento[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_solicitud_transporte[" + sal + "]\" name=\"fecha_solicitud_transporte[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_modulacion[" + sal + "]\" name=\"fecha_modulacion[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <select class=\"form-control\" id=\"modalidad[" + sal + "]\" name=\"modalidad[" + sal + "]\" value=\"\">" 
                        + "         <option value=\"Camión\">Camión</option>" 
                        + "         <option value=\"Tren\" disabled selected>Tren</option>" 
                        + "      </select>" 
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <select class=\"form-control\" id=\"resultado_modulacion[" + sal + "]\" name=\"resultado_modulacion[" + sal + "]\" value=\"\">" 
                        + "         <option value=\"Verde\">Verde</option>" 
                        + "         <option value=\"Rojo\" disabled selected>Rojo</option>" 
                        + "      </select>" 
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_reconocimiento[" + sal + "]\" name=\"fecha_reconocimiento[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_liberacion[" + sal + "]\" name=\"fecha_liberacion[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"sello_origen[" + sal + "]\" name=\"sello_origen[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"sello_final[" + sal + "]\" name=\"sello_final[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_retencion_aut[" + sal + "]\" name=\"fecha_retencion_aut[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"fecha_liberacion_aut[" + sal + "]\" name=\"fecha_liberacion_aut[" + sal + "]\" type=\"date\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <select class=\"form-control\" id=\"estatus_operacion[" + sal + "]\" name=\"estatus_operacion[" + sal + "]\"> "
                        + "          <option value=\"1\" disabled selected>EN TRANSITO</option> "
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
                        + "      <input class=\"form-control\" id=\"motivo_atraso[" + sal + "]\" name=\"motivo_atraso[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> "
                        + "    <td class=\"font-numero\"> "
                        + "      <input class=\"form-control\" id=\"observaciones[" + sal + "]\" name=\"observaciones[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + "    </td> ";
                
            if(tipoAgente.equals("4001")){        //Logix
                salida += " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"llegada_a_nova[" + sal + "]\" name=\"llegada_a_nova[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"llegada_a_globe_trade_sd[" + sal + "]\" name=\"llegada_a_globe_trade_sd[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"archivo_m[" + sal + "]\" name=\"archivo_m[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"fecha_archivo_m[" + sal + "]\" name=\"fecha_archivo_m[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"fecha_solicit_manip[" + sal + "]\" name=\"fecha_solicit_manip[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"fecha_vencim_manip[" + sal + "]\" name=\"fecha_vencim_manip[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"fecha_confirm_clave_pedim[" + sal + "]\" name=\"fecha_confirm_clave_pedim[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"fecha_recep_increment[" + sal + "]\" name=\"fecha_recep_increment[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"t_e[" + sal + "]\" name=\"t_e[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"fecha_vencim_inbound[" + sal + "]\" name=\"fecha_vencim_inbound[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> ";
                    
            }else if(tipoAgente.equals("4002")){  //Cusa
                salida += " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"no_bultos[" + sal + "]\" name=\"no_bultos[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"peso_kg[" + sal + "]\" name=\"peso_kg[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"transferencia[" + sal + "]\" name=\"transferencia[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"fecha_inicio_etiquetado[" + sal + "]\" name=\"fecha_inicio_etiquetado[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"fecha_termino_etiquetado[" + sal + "]\" name=\"fecha_termino_etiquetado[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"hora_termino_etiquetado[" + sal + "]\" name=\"hora_termino_etiquetado[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"proveedor[" + sal + "]\" name=\"proveedor[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"proveedor_carga[" + sal + "]\" name=\"proveedor_carga[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> ";
            }
            
                salida += " <td class=\"font-numero\"> "
                        + "     <input class=\"form-control\" id=\"fy[" + sal + "]\" name=\"fy[" + sal + "]\" type=\"text\" autocomplete=\"off\"> "
                        + " </td> "
                        + "</tr>";
                sal++;
            }
                salida += " <input type=\"hidden\" id=\"numCustoms\" name=\"numCustoms\" value=\"" + sal + "\"> ";
            
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
