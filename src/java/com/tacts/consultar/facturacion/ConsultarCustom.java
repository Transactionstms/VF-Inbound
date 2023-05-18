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
import java.util.HashSet;

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
            OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
            oraDB.connect(dbData.getUser(), dbData.getPassword());
            ConsultasQuery fac = new ConsultasQuery();
            ServiceDAO dao = new ServiceDAO();
            
            String tipoAgente = request.getParameter("tipoAgente");
            String tipoFiltro = request.getParameter("tipoFiltro");
            String id = request.getParameter("id");

            //Parametros Generales:
            String body = "";
            String caramelo = "";
            int sal = 1;
            
            //Generar caramelo: Opciones del multiselect
            String[] arrOfStr = id.split(",");
            
            for (String a : arrOfStr) {
                caramelo += "'" + a + "',";
            }
            caramelo = caramelo.replaceAll(",$", "");
            
            HashSet<String> list_estatus = new HashSet<String>();
            
            if (db.doDB(fac.consultarEstatusCustoms())) {
                for (String[] row : db.getResultado()) {
                    list_estatus.add("<option value=\"" + row[0].trim() + "\">" + row[1] + "</option>");
                }
            }
                    ResultSet rs = dao.consulta(fac.consultarEventosCustoms(tipoAgente,tipoFiltro,caramelo)); 
                    while (rs.next()) {

                          body += " <tr id=\"tr" + sal + "\"> "
                                + "    <th class=\"font-numero\"><center><img src=\"../img/circle-green.png\" width=\"100%\"/></center></th> "  //Semaforo
                                + "    <th class=\"font-numero\"> "                           // Referencia Aduanal
                                + "        <input type=\"text\" class=\"form-control\" id=\"referenciaAA[" + sal + "]\" name=\"referenciaAA[" + sal + "]\" value=\""+rs.getString(31).trim()+"\" autocomplete=\"off\"> "
                                + "    </th>"
                                + "    <th class=\"font-numero\">"+rs.getString(1)+"<input type=\"hidden\" id=\"evento[" + sal + "]\" name=\"evento[" + sal + "]\" value=\""+rs.getString(1).trim()+"\"></th> "  // Número de Evento
                                + "    <td class=\"font-numero\">"+rs.getString(2).trim()+"</td> "   // Responsable
                                + "    <td class=\"font-numero\">"+rs.getString(3).trim()+"</td> "   // Final Destination
                                + "    <td class=\"font-numero\">"+rs.getString(22).trim()+"</td> "  // Brand-Division
                                + "    <td class=\"font-numero\">"+rs.getString(5).trim()+"</td> "   // Division
                                + "    <td class=\"font-numero\">"+rs.getString(6).trim()+"<input type=\"hidden\" id=\"shipmentId[" + sal + "]\" name=\"shipmentId[" + sal + "]\" value=\""+rs.getString(6).trim()+"\"></td> "  // Shipment ID
                                + "    <td class=\"font-numero\">"+rs.getString(7).trim()+"<input type=\"hidden\" id=\"containerId[" + sal + "]\" name=\"containerId[" + sal + "]\" value=\""+rs.getString(7).trim()+"\"></td> "  // Container
                                + "    <td class=\"font-numero\">"+rs.getString(8).trim()+"</td> "   // BL/AWB/PRO
                                + "    <td class=\"font-numero\">"+rs.getString(23).trim()+"</td> "  // LoadType
                                + "    <td class=\"font-numero\">"+rs.getString(10).trim()+"</td> "  // Quantity
                                + "    <td class=\"font-numero\">"+rs.getString(20).trim()+"</td> "  // POD
                                + "    <td class=\"font-numero\">"+rs.getString(12).trim()+"</td> "  // Est. Departure from POL
                                + "    <td class=\"font-numero\">"+rs.getString(13).trim()+"</td> "  // ETA REAL Port of Discharge
                                + "    <td class=\"font-numero\">"+rs.getString(24).trim()+"</td> "  // Est. Eta DC
                                + "    <td class=\"font-numero\">"+rs.getString(15).trim()+"</td> "  // Inbound notification
                                + "    <td class=\"font-numero\">"+rs.getString(21).trim()+"</td> "  // POL
                                + "    <td class=\"font-numero\">"+rs.getString(17).trim()+"</td> "  // A.A.
                                + "    <td class=\"font-numero\">"+rs.getString(29).trim()+"</td> "  // Fecha Mes de Venta 
                                + "    <td class=\"font-numero\"> "                           // Prioridad Si/No
                                + "      <select class=\"form-control\" id=\"prioridad[" + sal + "]\" name=\"prioridad[" + sal + "]\" value=\""+rs.getString(98).trim()+"\">" 
                                + "         <option value=\"\"></option>" 
                                + "         <option value=\"Si\">SI</option>" 
                                + "         <option value=\"No\">NO</option>" 
                                + "      </select>" 
                                + "    </td> "                                                // Campo en blanco
                                + "    <td class=\"font-numero\"> "
                                + "        <input class=\"form-control\" id=\"pais_origen[" + sal + "]\" name=\"pais_origen[" + sal + "]\" type=\"text\" value=\""+rs.getString(32).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "        <input class=\"form-control\" id=\"size_container[" + sal + "]\" name=\"size_container[" + sal + "]\" type=\"text\" value=\""+rs.getString(33).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "        <input class=\"form-control\" id=\"valor_usd[" + sal + "]\" name=\"valor_usd[" + sal + "]\" type=\"text\" value=\""+rs.getString(34).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"eta_port_discharge[" + sal + "]\" name=\"eta_port_discharge[" + sal + "]\" type=\"text\" value=\""+rs.getString(35).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"agente_aduanal[" + sal + "]\" name=\"agente_aduanal[" + sal + "]\" type=\"text\" value=\""+rs.getString(36).trim()+"\" onkeyup=\"this.value = this.value.toUpperCase()\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"pedimento_a1[" + sal + "]\" name=\"pedimento_a1[" + sal + "]\" type=\"text\" value=\""+rs.getString(37).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"pedimento_r1_1er[" + sal + "]\" name=\"pedimento_r1_1er[" + sal + "]\" type=\"text\" value=\""+rs.getString(38).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"motivo_rectificacion_1er[" + sal + "]\" name=\"motivo_rectificacion_1er[" + sal + "]\" type=\"text\" value=\""+rs.getString(39).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"pedimento_r1_2do[" + sal + "]\" name=\"pedimento_r1_2do[" + sal + "]\" type=\"text\" value=\""+rs.getString(40).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"motivo_rectificacion_2do[" + sal + "]\" name=\"motivo_rectificacion_2do[" + sal + "]\" type=\"text\" value=\""+rs.getString(41).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_recepcion_doc[" + sal + "]\" name=\"fecha_recepcion_doc[" + sal + "]\" type=\"text\" value=\""+rs.getString(42).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"recinto[" + sal + "]\" name=\"recinto[" + sal + "]\" type=\"text\" value=\""+rs.getString(43).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"naviera[" + sal + "]\" name=\"naviera[" + sal + "]\" type=\"text\" value=\""+rs.getString(44).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"buque[" + sal + "]\" name=\"buque[" + sal + "]\" type=\"text\" value=\""+rs.getString(45).trim().trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_revalidacion[" + sal + "]\" name=\"fecha_revalidacion[" + sal + "]\" type=\"text\" value=\""+rs.getString(46).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_previo_origen[" + sal + "]\" name=\"fecha_previo_origen[" + sal + "]\" type=\"text\" value=\""+rs.getString(47).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_previo_destino[" + sal + "]\" name=\"fecha_previo_destino[" + sal + "]\" type=\"text\" value=\""+rs.getString(48).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_resultado_previo[" + sal + "]\" name=\"fecha_resultado_previo[" + sal + "]\" type=\"text\" value=\""+rs.getString(49).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"proforma_final[" + sal + "]\" name=\"proforma_final[" + sal + "]\" type=\"text\" value=\""+rs.getString(50).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"permiso[" + sal + "]\" name=\"permiso[" + sal + "]\"  value=\""+rs.getString(51).trim()+"\" value=\"\">" 
                                + "         <option value=\"\"></option>" 
                                + "         <option value=\"Si\">SI</option>" 
                                + "         <option value=\"No\">NO</option>" 
                                + "      </select>" 
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_envio[" + sal + "]\" name=\"fecha_envio[" + sal + "]\" type=\"text\" value=\""+rs.getString(52).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_recepcion_perm[" + sal + "]\" name=\"fecha_recepcion_perm[" + sal + "]\" type=\"text\" value=\""+rs.getString(53).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_activacion_perm[" + sal + "]\" name=\"fecha_activacion_perm[" + sal + "]\" type=\"text\" value=\""+rs.getString(54).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_permisos_aut[" + sal + "]\" name=\"fecha_permisos_aut[" + sal + "]\" type=\"text\" value=\""+rs.getString(55).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"co_pref_arancelaria[" + sal + "]\" name=\"co_pref_arancelaria[" + sal + "]\" value=\""+rs.getString(56).trim()+"\" value=\"\">" 
                                + "         <option value=\"\"></option>" 
                                + "         <option value=\"Si\">SI</option>" 
                                + "         <option value=\"No\">NO</option>" 
                                + "      </select>" 
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"aplic_pref_arancelaria[" + sal + "]\" name=\"aplic_pref_arancelaria[" + sal + "]\" value=\""+rs.getString(57).trim()+"\" value=\"\">" 
                                + "         <option value=\"\"></option>" 
                                + "         <option value=\"Si\">SI</option>" 
                                + "         <option value=\"No\">NO</option>" 
                                + "      </select>" 
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"req_uva[" + sal + "]\" name=\"req_uva[" + sal + "]\" value=\""+rs.getString(58).trim()+"\" value=\"\">" 
                                + "         <option value=\"\"></option>" 
                                + "         <option value=\"Si\">SI</option>" 
                                + "         <option value=\"No\">NO</option>" 
                                + "      </select>" 
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"req_ca[" + sal + "]\" name=\"req_ca[" + sal + "]\"  value=\""+rs.getString(59).trim()+"\">" 
                                + "         <option value=\"\"></option>" 
                                + "         <option value=\"Si\">SI</option>" 
                                + "         <option value=\"No\">NO</option>" 
                                + "      </select>" 
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_recepcion_ca[" + sal + "]\" name=\"fecha_recepcion_ca[" + sal + "]\" type=\"text\" value=\""+rs.getString(60).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> " 
                                + "    <td class=\"font-numero\"> " 
                                + "      <input class=\"form-control\" id=\"num_constancia_ca[" + sal + "]\" name=\"num_constancia_ca[" + sal + "]\" type=\"text\" value=\""+rs.getString(61).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> " 
                                + "    <td class=\"font-numero\"> " 
                                + "      <input class=\"form-control\" id=\"monto_ca[" + sal + "]\" name=\"monto_ca[" + sal + "]\" type=\"text\" value=\""+rs.getString(62).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_doc_completos[" + sal + "]\" name=\"fecha_doc_completos[" + sal + "]\" type=\"text\" value=\""+rs.getString(63).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_pago_pedimento[" + sal + "]\" name=\"fecha_pago_pedimento[" + sal + "]\" type=\"text\" value=\""+rs.getString(64).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_solicitud_transporte[" + sal + "]\" name=\"fecha_solicitud_transporte[" + sal + "]\" type=\"text\" value=\""+rs.getString(65).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_modulacion[" + sal + "]\" name=\"fecha_modulacion[" + sal + "]\" type=\"text\" value=\""+rs.getString(66).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"modalidad[" + sal + "]\" name=\"modalidad[" + sal + "]\" value=\""+rs.getString(67).trim()+"\">" 
                                + "         <option value=\"\"></option>"   
                                + "         <option value=\"Camión\">Camión</option>" 
                                + "         <option value=\"Tren\">Tren</option>" 
                                + "      </select>" 
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"resultado_modulacion[" + sal + "]\" name=\"resultado_modulacion[" + sal + "]\"  value=\""+rs.getString(68).trim()+"\">" 
                                + "         <option value=\"\"></option>" 
                                + "         <option value=\"Verde\">Verde</option>" 
                                + "         <option value=\"Rojo\">Rojo</option>" 
                                + "      </select>" 
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_reconocimiento[" + sal + "]\" name=\"fecha_reconocimiento[" + sal + "]\" type=\"text\"  value=\""+rs.getString(69).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_liberacion[" + sal + "]\" name=\"fecha_liberacion[" + sal + "]\" type=\"text\"  value=\""+rs.getString(70).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"sello_origen[" + sal + "]\" name=\"sello_origen[" + sal + "]\" type=\"text\"  value=\""+rs.getString(71).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"sello_final[" + sal + "]\" name=\"sello_final[" + sal + "]\" type=\"text\"  value=\""+rs.getString(72).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_retencion_aut[" + sal + "]\" name=\"fecha_retencion_aut[" + sal + "]\" type=\"text\" value=\""+rs.getString(73).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control datepicker\" id=\"fecha_liberacion_aut[" + sal + "]\" name=\"fecha_liberacion_aut[" + sal + "]\" type=\"text\" value=\""+rs.getString(74).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"estatus_operacion[" + sal + "]\" name=\"estatus_operacion[" + sal + "]\"  value=\""+rs.getString(75).trim()+"\"> "
                                + "         <option value=\"\"></option>" 
                                + list_estatus
                                + "      </select> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"motivo_atraso[" + sal + "]\" name=\"motivo_atraso[" + sal + "]\" type=\"text\"  value=\""+rs.getString(76).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"observaciones[" + sal + "]\" name=\"observaciones[" + sal + "]\" type=\"text\" value=\""+rs.getString(77).trim()+"\" autocomplete=\"off\"> "
                                + "    </td> ";

                    if(tipoAgente.equals("4001")||tipoAgente.equals("4006")){ //Logix ó VF
                          body += " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control datepicker\" id=\"llegada_a_nova[" + sal + "]\" name=\"llegada_a_nova[" + sal + "]\" type=\"text\" value=\""+rs.getString(78).trim()+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control datepicker\" id=\"llegada_a_globe_trade_sd[" + sal + "]\" name=\"llegada_a_globe_trade_sd[" + sal + "]\" type=\"text\" value=\""+rs.getString(79).trim()+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"archivo_m[" + sal + "]\" name=\"archivo_m[" + sal + "]\" type=\"text\" value=\""+rs.getString(80).trim()+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control datepicker\" id=\"fecha_archivo_m[" + sal + "]\" name=\"fecha_archivo_m[" + sal + "]\" type=\"text\" value=\""+rs.getString(81).trim()+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control datepicker\" id=\"fecha_solicit_manip[" + sal + "]\" name=\"fecha_solicit_manip[" + sal + "]\" type=\"text\" value=\""+rs.getString(82).trim()+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control datepicker\" id=\"fecha_vencim_manip[" + sal + "]\" name=\"fecha_vencim_manip[" + sal + "]\" type=\"text\" value=\""+rs.getString(83).trim()+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control datepicker\" id=\"fecha_confirm_clave_pedim[" + sal + "]\" name=\"fecha_confirm_clave_pedim[" + sal + "]\" type=\"text\" value=\""+rs.getString(84).trim()+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control datepicker\" id=\"fecha_recep_increment[" + sal + "]\" name=\"fecha_recep_increment[" + sal + "]\" type=\"text\" value=\""+rs.getString(85).trim()+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"t_e[" + sal + "]\" name=\"t_e[" + sal + "]\" type=\"text\" value=\""+rs.getString(86).trim()+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control datepicker\" id=\"fecha_vencim_inbound[" + sal + "]\" name=\"fecha_vencim_inbound[" + sal + "]\" type=\"text\" value=\""+rs.getString(87).trim()+"\" autocomplete=\"off\"> "
                                + " </td> ";

                    }

                    if(tipoAgente.equals("4002")||tipoAgente.equals("4006")){  //Cusa ó VF
                          body += " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"no_bultos[" + sal + "]\" name=\"no_bultos[" + sal + "]\" type=\"text\" value=\""+rs.getString(88).trim()+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"peso_kg[" + sal + "]\" name=\"peso_kg[" + sal + "]\" type=\"text\" value=\""+rs.getString(89).trim()+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"transferencia[" + sal + "]\" name=\"transferencia[" + sal + "]\" value=\""+rs.getString(90).trim()+"\">" 
                                + "         <option value=\"\"></option>" 
                                + "         <option value=\"Si\">SI</option>" 
                                + "         <option value=\"No\">NO</option>" 
                                + "      </select>" 
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control datepicker\" id=\"fecha_inicio_etiquetado[" + sal + "]\" name=\"fecha_inicio_etiquetado[" + sal + "]\" text value=\""+rs.getString(91).trim()+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control datepicker\" id=\"fecha_termino_etiquetado[" + sal + "]\" name=\"fecha_termino_etiquetado[" + sal + "]\" type=\"text\" value=\""+rs.getString(92).trim()+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"hora_termino_etiquetado[" + sal + "]\" name=\"hora_termino_etiquetado[" + sal + "]\" type=\"time\" value=\""+rs.getString(93).trim()+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"proveedor[" + sal + "]\" name=\"proveedor[" + sal + "]\" type=\"text\" value=\""+rs.getString(94).trim()+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"proveedor_carga[" + sal + "]\" name=\"proveedor_carga[" + sal + "]\" type=\"text\" value=\""+rs.getString(95).trim()+"\" autocomplete=\"off\"> "
                                + " </td> ";
                    }
                          body += " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"fy[" + sal + "]\" name=\"fy[" + sal + "]\" type=\"text\" value=\""+rs.getString(96).trim()+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <center><a class=\"btn btn-primary text-uppercase\" onclick=\"AddCustoms()\"><i class=\"fa fa-save\"></i></a></center> "  
                                + " </td> " 
                                + "</tr>";
                        sal++;
                    }
                    
                          body += " <input type=\"hidden\" id=\"numCustoms\" name=\"numCustoms\" value=\"" + sal + "\"> ";
            /*
                String array1 = list_evento.toString();
                for (char c : charsToRemove.toCharArray()) {
                    array1 = array1.replace(String.valueOf(c),"");
                }     
                System.out.println("listar combo evento :" + array1);
            */
            
            dao.CerrarConexion();
            oraDB.close();
            
            if (sal > 0) {
                out.print(body);
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
