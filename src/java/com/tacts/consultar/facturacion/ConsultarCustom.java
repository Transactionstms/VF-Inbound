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

            //Objetos Multiselect:
            HashSet<String> list_evento = new HashSet<String>();
            HashSet<String> list_referenciaAA = new HashSet<String>();
            HashSet<String> list_responsable = new HashSet<String>();
            HashSet<String> list_finalDestination = new HashSet<String>();
            HashSet<String> list_brandDivision = new HashSet<String>();
            HashSet<String> list_division = new HashSet<String>();
            HashSet<String> list_shipmentId = new HashSet<String>();
            HashSet<String> list_containerId = new HashSet<String>();
            HashSet<String> list_blAwbPro = new HashSet<String>();
            HashSet<String> list_loadType = new HashSet<String>();
            HashSet<String> list_quantity = new HashSet<String>();
            HashSet<String> list_pod = new HashSet<String>();
            HashSet<String> list_estDepartFromPol = new HashSet<String>();
            HashSet<String> list_etaRealPortOfDischarge = new HashSet<String>();
            HashSet<String> list_estEtaDc = new HashSet<String>();
            HashSet<String> list_inboundNotification = new HashSet<String>();
            HashSet<String> list_pol = new HashSet<String>();
            HashSet<String> list_aa = new HashSet<String>();
            HashSet<String> list_fechaMesVenta = new HashSet<String>();
            HashSet<String> list_prioridad = new HashSet<String>();
            HashSet<String> list_pais_origen= new HashSet<String>();
            HashSet<String> list_size_container = new HashSet<String>();    
            HashSet<String> list_valor_usd = new HashSet<String>();                 
            HashSet<String> list_eta_port_discharge = new HashSet<String>();         
            HashSet<String> list_agente_aduanal = new HashSet<String>();              
            HashSet<String> list_pedimento_a1 = new HashSet<String>();              
            HashSet<String> list_pedimento_r1_1er = new HashSet<String>();          
            HashSet<String> list_motivo_rectificacion_1er = new HashSet<String>();    
            HashSet<String> list_pedimento_r1_2do = new HashSet<String>();         
            HashSet<String> list_motivo_rectificacion_2do = new HashSet<String>();    
            HashSet<String> list_fecha_recepcion_doc = new HashSet<String>();    
            HashSet<String> list_recinto = new HashSet<String>();
            HashSet<String> list_naviera = new HashSet<String>();
            HashSet<String> list_buque = new HashSet<String>();
            HashSet<String> list_fecha_revalidacion = new HashSet<String>();      
            HashSet<String> list_fecha_previo_origen = new HashSet<String>();     
            HashSet<String> list_fecha_previo_destino = new HashSet<String>();   
            HashSet<String> list_fecha_resultado_previo = new HashSet<String>();   
            HashSet<String> list_proforma_final = new HashSet<String>();        
            HashSet<String> list_permiso = new HashSet<String>();               
            HashSet<String> list_fecha_envio = new HashSet<String>();             
            HashSet<String> list_fecha_recepcion_perm = new HashSet<String>();
            HashSet<String> list_fecha_activacion_perm = new HashSet<String>();   
            HashSet<String> list_fecha_permisos_aut = new HashSet<String>();     
            HashSet<String> list_co_pref_arancelaria = new HashSet<String>();    
            HashSet<String> list_aplic_pref_arancelaria = new HashSet<String>();  
            HashSet<String> list_req_uva = new HashSet<String>();  
            HashSet<String> list_req_ca = new HashSet<String>();    
            HashSet<String> list_fecha_recepcion_ca = new HashSet<String>(); 
            HashSet<String> list_num_constancia_ca = new HashSet<String>();   
            HashSet<String> list_monto_ca = new HashSet<String>();
            HashSet<String> list_fecha_doc_completos = new HashSet<String>();  
            HashSet<String> list_fecha_pago_pedimento = new HashSet<String>();     
            HashSet<String> list_fecha_solicitud_transporte = new HashSet<String>();
            HashSet<String> list_fecha_modulacion = new HashSet<String>();   
            HashSet<String> list_modalidad = new HashSet<String>();          
            HashSet<String> list_resultado_modulacion = new HashSet<String>();    
            HashSet<String> list_fecha_reconocimiento = new HashSet<String>();     
            HashSet<String> list_fecha_liberacion = new HashSet<String>();       
            HashSet<String> list_sello_origen = new HashSet<String>();            
            HashSet<String> list_sello_final = new HashSet<String>();      
            HashSet<String> list_fecha_retencion_aut = new HashSet<String>();     
            HashSet<String> list_fecha_liberacion_aut = new HashSet<String>();   
            HashSet<String> list_estatus_operacion = new HashSet<String>();      
            HashSet<String> list_motivo_atraso = new HashSet<String>();          
            HashSet<String> list_observaciones = new HashSet<String>(); 
            HashSet<String> list_llegada_a_nova = new HashSet<String>();
            HashSet<String> list_llegada_a_globe_trade_sd = new HashSet<String>();
            HashSet<String> list_archivo_m = new HashSet<String>();
            HashSet<String> list_fecha_archivo_m = new HashSet<String>();
            HashSet<String> list_fecha_solicit_manip = new HashSet<String>();
            HashSet<String> list_fecha_vencim_manip = new HashSet<String>();
            HashSet<String> list_fecha_confirm_clave_pedim = new HashSet<String>();
            HashSet<String> list_fecha_recep_increment = new HashSet<String>();
            HashSet<String> list_t_e = new HashSet<String>();
            HashSet<String> list_fecha_vencim_inbound = new HashSet<String>();  
            HashSet<String> list_no_bultos = new HashSet<String>();
            HashSet<String> list_peso_kg = new HashSet<String>();
            HashSet<String> list_transferencia = new HashSet<String>();
            HashSet<String> list_fecha_inicio_etiquetado = new HashSet<String>();
            HashSet<String> list_fecha_termino_etiquetado = new HashSet<String>();
            HashSet<String> list_hora_termino_etiquetado = new HashSet<String>();
            HashSet<String> list_proveedor = new HashSet<String>();
            HashSet<String> list_proveedor_carga = new HashSet<String>();     
            HashSet<String> list_fy = new HashSet<String>();

            //Parametros Multiselect:
            String multiselect_evento = "";
            String multiselect_referenciaAA = "";
            String multiselect_responsable = "";
            String multiselect_finalDestination = "";
            String multiselect_brandDivision = "";
            String multiselect_division = "";
            String multiselect_shipmentId = "";
            String multiselect_containerId = "";
            String multiselect_blAwbPro = "";
            String multiselect_loadType = "";
            String multiselect_quantity = "";
            String multiselect_pod = "";
            String multiselect_estDepartFromPol = "";
            String multiselect_etaRealPortOfDischarge = "";
            String multiselect_estEtaDc = "";
            String multiselect_inboundNotification = "";
            String multiselect_pol = "";
            String multiselect_aa = "";
            String multiselect_fechaMesVenta = "";
            String multiselect_prioridad = "";
            String multiselect_pais_origen = "";    
            String multiselect_size_container = "";    
            String multiselect_valor_usd = "";                 
            String multiselect_eta_port_discharge = "";         
            String multiselect_agente_aduanal = "";              
            String multiselect_pedimento_a1 = "";              
            String multiselect_pedimento_r1_1er = "";          
            String multiselect_motivo_rectificacion_1er = "";    
            String multiselect_pedimento_r1_2do = "";         
            String multiselect_motivo_rectificacion_2do = "";    
            String multiselect_fecha_recepcion_doc = "";    
            String multiselect_recinto = "";
            String multiselect_naviera = "";
            String multiselect_buque = "";
            String multiselect_fecha_revalidacion = "";      
            String multiselect_fecha_previo_origen = "";     
            String multiselect_fecha_previo_destino = "";   
            String multiselect_fecha_resultado_previo = "";   
            String multiselect_proforma_final = "";        
            String multiselect_permiso = "";               
            String multiselect_fecha_envio = "";             
            String multiselect_fecha_recepcion_perm = "";
            String multiselect_fecha_activacion_perm = "";    
            String multiselect_fecha_permisos_aut = "";     
            String multiselect_co_pref_arancelaria = "";    
            String multiselect_aplic_pref_arancelaria = "";  
            String multiselect_req_uva = "";   
            String multiselect_req_ca = "";    
            String multiselect_fecha_recepcion_ca = ""; 
            String multiselect_num_constancia_ca = "";   
            String multiselect_monto_ca = "";
            String multiselect_fecha_doc_completos = "";  
            String multiselect_fecha_pago_pedimento = "";     
            String multiselect_fecha_solicitud_transporte = "";
            String multiselect_fecha_modulacion = "";   
            String multiselect_modalidad = "";          
            String multiselect_resultado_modulacion = "";    
            String multiselect_fecha_reconocimiento = "";     
            String multiselect_fecha_liberacion = "";       
            String multiselect_sello_origen = "";            
            String multiselect_sello_final = "";      
            String multiselect_fecha_retencion_aut = "";     
            String multiselect_fecha_liberacion_aut = "";   
            String multiselect_estatus_operacion = "";      
            String multiselect_motivo_atraso = "";          
            String multiselect_observaciones = ""; 
            String multiselect_llegada_a_nova = "";
            String multiselect_llegada_a_globe_trade_sd = "";
            String multiselect_archivo_m = "";
            String multiselect_fecha_archivo_m = "";
            String multiselect_fecha_solicit_manip = "";
            String multiselect_fecha_vencim_manip = "";
            String multiselect_fecha_confirm_clave_pedim = "";
            String multiselect_fecha_recep_increment = "";
            String multiselect_t_e = "";
            String multiselect_fecha_vencim_inbound = ""; 
            String multiselect_no_bultos = "";
            String multiselect_peso_kg = "";
            String multiselect_transferencia = "";
            String multiselect_fecha_inicio_etiquetado = "";
            String multiselect_fecha_termino_etiquetado = "";
            String multiselect_hora_termino_etiquetado = "";
            String multiselect_proveedor = "";
            String multiselect_proveedor_carga = "";     
            String multiselect_fy = "";

            //Parametros Generales:
            String charsToRemove = "[_,_]";
            String tabla = "";
            String body = "";
            String contador = "";
            int sal = 0;
            
                    ResultSet rs = dao.consulta(fac.consultarEventosCustoms(tipoAgente));
                    while (rs.next()) {
                        
                                list_evento.add("<option value=\""+rs.getString(1)+"\">"+rs.getString(1)+"</option>");
                                list_referenciaAA.add("<option value=\""+rs.getString(31)+"\">"+rs.getString(31)+"</option>");
                                list_responsable.add("<option value=\""+rs.getString(2)+"\">"+rs.getString(2)+"</option>");
                                list_finalDestination.add("<option value=\""+rs.getString(3)+"\">"+rs.getString(3)+"</option>");
                                list_brandDivision.add("<option value=\""+rs.getString(22)+"\">"+rs.getString(22)+"</option>");
                                list_division.add("<option value=\""+rs.getString(5)+"\">"+rs.getString(5)+"</option>");
                                list_shipmentId.add("<option value=\""+rs.getString(6)+"\">"+rs.getString(6)+"</option>");
                                list_containerId.add("<option value=\""+rs.getString(7)+"\">"+rs.getString(7)+"</option>");
                                list_blAwbPro.add("<option value=\""+rs.getString(8)+"\">"+rs.getString(8)+"</option>");
                                list_loadType.add("<option value=\""+rs.getString(23)+"\">"+rs.getString(23)+"</option>");
                                list_quantity.add("<option value=\""+rs.getString(10)+"\">"+rs.getString(10)+"</option>");
                                list_pod.add("<option value=\""+rs.getString(20)+"\">"+rs.getString(20)+"</option>");
                                list_estDepartFromPol.add("<option value=\""+rs.getString(12)+"\">"+rs.getString(12)+"</option>");
                                list_etaRealPortOfDischarge.add("<option value=\""+rs.getString(13)+"\">"+rs.getString(13)+"</option>");
                                list_estEtaDc.add("<option value=\""+rs.getString(24)+"\">"+rs.getString(24)+"</option>");
                                list_inboundNotification.add("<option value=\""+rs.getString(15)+"\">"+rs.getString(15)+"</option>");
                                list_pol.add("<option value=\""+rs.getString(21)+"\">"+rs.getString(21)+"</option>");
                                list_aa.add("<option value=\""+rs.getString(17)+"\">"+rs.getString(17)+"</option>");
                                list_fechaMesVenta.add("<option value=\""+rs.getString(29)+"\">"+rs.getString(29)+"</option>");
                                list_prioridad.add("<option value=\""+rs.getString(98)+"\">"+rs.getString(98)+"</option>");
                                list_pais_origen.add("<option value=\""+rs.getString(32)+"\">"+rs.getString(32)+"</option>");   
                                list_size_container.add("<option value=\""+rs.getString(33)+"\">"+rs.getString(33)+"</option>");    
                                list_valor_usd.add("<option value=\""+rs.getString(34)+"\">"+rs.getString(34)+"</option>");                 
                                list_eta_port_discharge.add("<option value=\""+rs.getString(35)+"\">"+rs.getString(35)+"</option>");         
                                list_agente_aduanal.add("<option value=\""+rs.getString(36)+"\">"+rs.getString(36)+"</option>");              
                                list_pedimento_a1.add("<option value=\""+rs.getString(37)+"\">"+rs.getString(37)+"</option>");              
                                list_pedimento_r1_1er.add("<option value=\""+rs.getString(38)+"\">"+rs.getString(38)+"</option>");          
                                list_motivo_rectificacion_1er.add("<option value=\""+rs.getString(39)+"\">"+rs.getString(39)+"</option>");    
                                list_pedimento_r1_2do.add("<option value=\""+rs.getString(40)+"\">"+rs.getString(40)+"</option>");         
                                list_motivo_rectificacion_2do.add("<option value=\""+rs.getString(41)+"\">"+rs.getString(41)+"</option>");    
                                list_fecha_recepcion_doc.add("<option value=\""+rs.getString(42)+"\">"+rs.getString(42)+"</option>");    
                                list_recinto.add("<option value=\""+rs.getString(43)+"\">"+rs.getString(43)+"</option>");
                                list_naviera.add("<option value=\""+rs.getString(44)+"\">"+rs.getString(44)+"</option>");
                                list_buque.add("<option value=\""+rs.getString(45)+"\">"+rs.getString(45)+"</option>");
                                list_fecha_revalidacion.add("<option value=\""+rs.getString(46)+"\">"+rs.getString(46)+"</option>");      
                                list_fecha_previo_origen.add("<option value=\""+rs.getString(47)+"\">"+rs.getString(47)+"</option>");     
                                list_fecha_previo_destino.add("<option value=\""+rs.getString(48)+"\">"+rs.getString(48)+"</option>");   
                                list_fecha_resultado_previo.add("<option value=\""+rs.getString(49)+"\">"+rs.getString(49)+"</option>");   
                                list_proforma_final.add("<option value=\""+rs.getString(50)+"\">"+rs.getString(50)+"</option>");        
                                list_permiso.add("<option value=\""+rs.getString(51)+"\">"+rs.getString(51)+"</option>");               
                                list_fecha_envio.add("<option value=\""+rs.getString(52)+"\">"+rs.getString(52)+"</option>");             
                                list_fecha_recepcion_perm.add("<option value=\""+rs.getString(53)+"\">"+rs.getString(53)+"</option>");
                                list_fecha_activacion_perm.add("<option value=\""+rs.getString(54)+"\">"+rs.getString(54)+"</option>");    
                                list_fecha_permisos_aut.add("<option value=\""+rs.getString(55)+"\">"+rs.getString(55)+"</option>");     
                                list_co_pref_arancelaria.add("<option value=\""+rs.getString(56)+"\">"+rs.getString(56)+"</option>");    
                                list_aplic_pref_arancelaria.add("<option value=\""+rs.getString(57)+"\">"+rs.getString(57)+"</option>");  
                                list_req_uva.add("<option value=\""+rs.getString(58)+"\">"+rs.getString(58)+"</option>");   
                                list_req_ca.add("<option value=\""+rs.getString(59)+"\">"+rs.getString(59)+"</option>");    
                                list_fecha_recepcion_ca.add("<option value=\""+rs.getString(60)+"\">"+rs.getString(60)+"</option>"); 
                                list_num_constancia_ca.add("<option value=\""+rs.getString(61)+"\">"+rs.getString(61)+"</option>");   
                                list_monto_ca.add("<option value=\""+rs.getString(62)+"\">"+rs.getString(62)+"</option>");
                                list_fecha_doc_completos.add("<option value=\""+rs.getString(63)+"\">"+rs.getString(63)+"</option>");  
                                list_fecha_pago_pedimento.add("<option value=\""+rs.getString(64)+"\">"+rs.getString(64)+"</option>");     
                                list_fecha_solicitud_transporte.add("<option value=\""+rs.getString(65)+"\">"+rs.getString(65)+"</option>");
                                list_fecha_modulacion.add("<option value=\""+rs.getString(66)+"\">"+rs.getString(66)+"</option>");   
                                list_modalidad.add("<option value=\""+rs.getString(67)+"\">"+rs.getString(67)+"</option>");          
                                list_resultado_modulacion.add("<option value=\""+rs.getString(68)+"\">"+rs.getString(68)+"</option>");    
                                list_fecha_reconocimiento.add("<option value=\""+rs.getString(69)+"\">"+rs.getString(69)+"</option>");     
                                list_fecha_liberacion.add("<option value=\""+rs.getString(70)+"\">"+rs.getString(70)+"</option>");       
                                list_sello_origen.add("<option value=\""+rs.getString(71)+"\">"+rs.getString(71)+"</option>");            
                                list_sello_final.add("<option value=\""+rs.getString(72)+"\">"+rs.getString(72)+"</option>");      
                                list_fecha_retencion_aut.add("<option value=\""+rs.getString(73)+"\">"+rs.getString(73)+"</option>");     
                                list_fecha_liberacion_aut.add("<option value=\""+rs.getString(74)+"\">"+rs.getString(74)+"</option>");   
                                list_estatus_operacion.add("<option value=\""+rs.getString(75)+"\">"+rs.getString(75)+"</option>");      
                                list_motivo_atraso.add("<option value=\""+rs.getString(76)+"\">"+rs.getString(76)+"</option>");          
                                list_observaciones.add("<option value=\""+rs.getString(77)+"\">"+rs.getString(77)+"</option>"); 
                                
                    if(tipoAgente.equals("4001")||tipoAgente.equals("4006")){ //Logix ó VF            
                                list_llegada_a_nova.add("<option value=\""+rs.getString(78)+"\">"+rs.getString(78)+"</option>");
                                list_llegada_a_globe_trade_sd.add("<option value=\""+rs.getString(79)+"\">"+rs.getString(79)+"</option>");
                                list_archivo_m.add("<option value=\""+rs.getString(80)+"\">"+rs.getString(80)+"</option>");
                                list_fecha_archivo_m.add("<option value=\""+rs.getString(81)+"\">"+rs.getString(81)+"</option>");
                                list_fecha_solicit_manip.add("<option value=\""+rs.getString(82)+"\">"+rs.getString(82)+"</option>");
                                list_fecha_vencim_manip.add("<option value=\""+rs.getString(83)+"\">"+rs.getString(83)+"</option>");
                                list_fecha_confirm_clave_pedim.add("<option value=\""+rs.getString(84)+"\">"+rs.getString(84)+"</option>");
                                list_fecha_recep_increment.add("<option value=\""+rs.getString(85)+"\">"+rs.getString(85)+"</option>");
                                list_t_e.add("<option value=\""+rs.getString(86)+"\">"+rs.getString(86)+"</option>");
                                list_fecha_vencim_inbound.add("<option value=\""+rs.getString(87)+"\">"+rs.getString(87)+"</option>");
                    }
                    
                    if(tipoAgente.equals("4002")||tipoAgente.equals("4006")){  //Cusa ó VF
                                list_no_bultos.add("<option value=\""+rs.getString(88)+"\">"+rs.getString(88)+"</option>");
                                list_peso_kg.add("<option value=\""+rs.getString(89)+"\">"+rs.getString(89)+"</option>");
                                list_transferencia.add("<option value=\""+rs.getString(90)+"\">"+rs.getString(90)+"</option>");
                                list_fecha_inicio_etiquetado.add("<option value=\""+rs.getString(91)+"\">"+rs.getString(91)+"</option>");
                                list_fecha_termino_etiquetado.add("<option value=\""+rs.getString(92)+"\">"+rs.getString(92)+"</option>");
                                list_hora_termino_etiquetado.add("<option value=\""+rs.getString(93)+"\">"+rs.getString(93)+"</option>");
                                list_proveedor.add("<option value=\""+rs.getString(94)+"\">"+rs.getString(94)+"</option>");
                                list_proveedor_carga.add("<option value=\""+rs.getString(95)+"\">"+rs.getString(95)+"</option>");
                                list_fy.add("<option value=\""+rs.getString(96)+"\">"+rs.getString(96)+"</option>");
                    }            

                          body += " <tr id=\"tr" + sal + "\"> "
                                + "    <th class=\"font-numero\"><center><img src=\"../img/circle-green.png\" width=\"100%\"/></center></th> "  //Semaforo
                                + "    <th class=\"font-numero\">"+rs.getString(1)+"<input type=\"hidden\" id=\"evento[" + sal + "]\" name=\"evento[" + sal + "]\" value=\""+rs.getString(1)+"\"></th> "  // Número de Evento
                                + "    <th class=\"font-numero\"> "                           // Referencia Aduanal
                                + "        <input type=\"text\" class=\"form-control\" id=\"referenciaAA[" + sal + "]\" name=\"referenciaAA[" + sal + "]\" value=\""+rs.getString(31)+"\" autocomplete=\"off\"> "
                                + "    </th>"
                                + "    <td class=\"font-numero\">"+rs.getString(2)+"</td> "   // Responsable
                                + "    <td class=\"font-numero\">"+rs.getString(3)+"</td> "   // Final Destination
                                + "    <td class=\"font-numero\">"+rs.getString(22)+"</td> "  // Brand-Division
                                + "    <td class=\"font-numero\">"+rs.getString(5)+"</td> "   // Division
                                + "    <td class=\"font-numero\">"+rs.getString(6)+"<input type=\"hidden\" id=\"shipmentId[" + sal + "]\" name=\"shipmentId[" + sal + "]\" value=\""+rs.getString(6)+"\"></td> "  // Shipment ID
                                + "    <td class=\"font-numero\">"+rs.getString(7)+"<input type=\"hidden\" id=\"containerId[" + sal + "]\" name=\"containerId[" + sal + "]\" value=\""+rs.getString(7)+"\"></td> "  // Container
                                + "    <td class=\"font-numero\">"+rs.getString(8)+"</td> "   // BL/AWB/PRO
                                + "    <td class=\"font-numero\">"+rs.getString(23)+"</td> "  // LoadType
                                + "    <td class=\"font-numero\">"+rs.getString(10)+"</td> "  // Quantity
                                + "    <td class=\"font-numero\">"+rs.getString(20)+"</td> "  // POD
                                + "    <td class=\"font-numero\">"+rs.getString(12)+"</td> "  // Est. Departure from POL
                                + "    <td class=\"font-numero\">"+rs.getString(13)+"</td> "  // ETA REAL Port of Discharge
                                + "    <td class=\"font-numero\">"+rs.getString(24)+"</td> "  // Est. Eta DC
                                + "    <td class=\"font-numero\">"+rs.getString(15)+"</td> "  // Inbound notification
                                + "    <td class=\"font-numero\">"+rs.getString(21)+"</td> "  // POL
                                + "    <td class=\"font-numero\">"+rs.getString(17)+"</td> "  // A.A.
                                + "    <td class=\"font-numero\">"+rs.getString(29)+"</td> "  // Fecha Mes de Venta 
                                + "    <td class=\"font-numero\"> "                           // Prioridad Si/No
                                + "      <select class=\"form-control\" id=\"prioridad[" + sal + "]\" name=\"prioridad[" + sal + "]\" value=\""+rs.getString(98)+"\">" 
                                + "         <option value=\"Si\">SI</option>" 
                                + "         <option value=\"No\" disabled selected>NO</option>" 
                                + "      </select>" 
                                + "    </td> "                                                // Campo en blanco
                                + "    <td class=\"font-numero\"> "
                                + "        <input class=\"form-control\" id=\"pais_origen[" + sal + "]\" name=\"pais_origen[" + sal + "]\" type=\"text\" value=\""+rs.getString(32)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "        <input class=\"form-control\" id=\"size_container[" + sal + "]\" name=\"size_container[" + sal + "]\" type=\"text\" value=\""+rs.getString(33)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "        <input class=\"form-control\" id=\"valor_usd[" + sal + "]\" name=\"valor_usd[" + sal + "]\" type=\"text\" value=\""+rs.getString(34)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"eta_port_discharge[" + sal + "]\" name=\"eta_port_discharge[" + sal + "]\" type=\"date\" value=\""+rs.getString(35)+"\" onchange=\"onSemaforo()\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"agente_aduanal[" + sal + "]\" name=\"agente_aduanal[" + sal + "]\" type=\"text\" value=\""+rs.getString(36)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"pedimento_a1[" + sal + "]\" name=\"pedimento_a1[" + sal + "]\" type=\"text\" value=\""+rs.getString(37)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"pedimento_r1_1er[" + sal + "]\" name=\"pedimento_r1_1er[" + sal + "]\" type=\"text\" value=\""+rs.getString(38)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"motivo_rectificacion_1er[" + sal + "]\" name=\"motivo_rectificacion_1er[" + sal + "]\" type=\"text\" value=\""+rs.getString(39)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"pedimento_r1_2do[" + sal + "]\" name=\"pedimento_r1_2do[" + sal + "]\" type=\"text\" value=\""+rs.getString(40)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"motivo_rectificacion_2do[" + sal + "]\" name=\"motivo_rectificacion_2do[" + sal + "]\" type=\"text\" value=\""+rs.getString(41)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_recepcion_doc[" + sal + "]\" name=\"fecha_recepcion_doc[" + sal + "]\" type=\"date\" value=\""+rs.getString(42)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"recinto[" + sal + "]\" name=\"recinto[" + sal + "]\" type=\"text\" value=\""+rs.getString(43)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"naviera[" + sal + "]\" name=\"naviera[" + sal + "]\" type=\"text\" value=\""+rs.getString(44)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"buque[" + sal + "]\" name=\"buque[" + sal + "]\" type=\"text\" value=\""+rs.getString(45)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_revalidacion[" + sal + "]\" name=\"fecha_revalidacion[" + sal + "]\" type=\"date\" value=\""+rs.getString(46)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_previo_origen[" + sal + "]\" name=\"fecha_previo_origen[" + sal + "]\" type=\"date\" value=\""+rs.getString(47)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_previo_destino[" + sal + "]\" name=\"fecha_previo_destino[" + sal + "]\" type=\"date\" value=\""+rs.getString(48)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_resultado_previo[" + sal + "]\" name=\"fecha_resultado_previo[" + sal + "]\" type=\"date\" value=\""+rs.getString(49)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"proforma_final[" + sal + "]\" name=\"proforma_final[" + sal + "]\" type=\"date\" value=\""+rs.getString(50)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"permiso[" + sal + "]\" name=\"permiso[" + sal + "]\"  value=\""+rs.getString(51)+"\" value=\"\">" 
                                + "         <option value=\"Si\">SI</option>" 
                                + "         <option value=\"No\" disabled selected>NO</option>" 
                                + "      </select>" 
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_envio[" + sal + "]\" name=\"fecha_envio[" + sal + "]\" type=\"date\" value=\""+rs.getString(52)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_recepcion_perm[" + sal + "]\" name=\"fecha_recepcion_perm[" + sal + "]\" type=\"date\" value=\""+rs.getString(53)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_activacion_perm[" + sal + "]\" name=\"fecha_activacion_perm[" + sal + "]\" type=\"date\" value=\""+rs.getString(54)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_permisos_aut[" + sal + "]\" name=\"fecha_permisos_aut[" + sal + "]\" type=\"date\" value=\""+rs.getString(55)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"co_pref_arancelaria[" + sal + "]\" name=\"co_pref_arancelaria[" + sal + "]\" value=\""+rs.getString(56)+"\" value=\"\">" 
                                + "         <option value=\"Si\">SI</option>" 
                                + "         <option value=\"No\" disabled selected>NO</option>" 
                                + "      </select>" 
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"aplic_pref_arancelaria[" + sal + "]\" name=\"aplic_pref_arancelaria[" + sal + "]\" value=\""+rs.getString(57)+"\" value=\"\">" 
                                + "         <option value=\"Si\">SI</option>" 
                                + "         <option value=\"No\" disabled selected>NO</option>" 
                                + "      </select>" 
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"req_uva[" + sal + "]\" name=\"req_uva[" + sal + "]\" value=\""+rs.getString(58)+"\" value=\"\">" 
                                + "         <option value=\"Si\">SI</option>" 
                                + "         <option value=\"No\" disabled selected>NO</option>" 
                                + "      </select>" 
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"req_ca[" + sal + "]\" name=\"req_ca[" + sal + "]\"  value=\""+rs.getString(59)+"\">" 
                                + "         <option value=\"Si\">SI</option>" 
                                + "         <option value=\"No\" disabled selected>NO</option>" 
                                + "      </select>" 
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_recepcion_ca[" + sal + "]\" name=\"fecha_recepcion_ca[" + sal + "]\" type=\"date\" value=\""+rs.getString(60)+"\" autocomplete=\"off\"> "
                                + "    </td> " 
                                + "    <td class=\"font-numero\"> " 
                                + "      <input class=\"form-control\" id=\"num_constancia_ca[" + sal + "]\" name=\"num_constancia_ca[" + sal + "]\" type=\"text\" value=\""+rs.getString(61)+"\" autocomplete=\"off\"> "
                                + "    </td> " 
                                + "    <td class=\"font-numero\"> " 
                                + "      <input class=\"form-control\" id=\"monto_ca[" + sal + "]\" name=\"monto_ca[" + sal + "]\" type=\"text\" value=\""+rs.getString(62)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_doc_completos[" + sal + "]\" name=\"fecha_doc_completos[" + sal + "]\" type=\"date\" value=\""+rs.getString(63)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_pago_pedimento[" + sal + "]\" name=\"fecha_pago_pedimento[" + sal + "]\" type=\"date\" value=\""+rs.getString(64)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_solicitud_transporte[" + sal + "]\" name=\"fecha_solicitud_transporte[" + sal + "]\" type=\"date\" value=\""+rs.getString(65)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_modulacion[" + sal + "]\" name=\"fecha_modulacion[" + sal + "]\" type=\"date\" value=\""+rs.getString(66)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"modalidad[" + sal + "]\" name=\"modalidad[" + sal + "]\" value=\""+rs.getString(67)+"\">" 
                                + "         <option value=\"Camión\">Camión</option>" 
                                + "         <option value=\"Tren\" disabled selected>Tren</option>" 
                                + "      </select>" 
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"resultado_modulacion[" + sal + "]\" name=\"resultado_modulacion[" + sal + "]\"  value=\""+rs.getString(68)+"\">" 
                                + "         <option value=\"Verde\">Verde</option>" 
                                + "         <option value=\"Rojo\" disabled selected>Rojo</option>" 
                                + "      </select>" 
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_reconocimiento[" + sal + "]\" name=\"fecha_reconocimiento[" + sal + "]\" type=\"date\"  value=\""+rs.getString(69)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_liberacion[" + sal + "]\" name=\"fecha_liberacion[" + sal + "]\" type=\"date\"  value=\""+rs.getString(70)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"sello_origen[" + sal + "]\" name=\"sello_origen[" + sal + "]\" type=\"text\"  value=\""+rs.getString(71)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"sello_final[" + sal + "]\" name=\"sello_final[" + sal + "]\" type=\"text\"  value=\""+rs.getString(72)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_retencion_aut[" + sal + "]\" name=\"fecha_retencion_aut[" + sal + "]\" type=\"date\" value=\""+rs.getString(73)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"fecha_liberacion_aut[" + sal + "]\" name=\"fecha_liberacion_aut[" + sal + "]\" type=\"date\" value=\""+rs.getString(74)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"estatus_operacion[" + sal + "]\" name=\"estatus_operacion[" + sal + "]\"  value=\""+rs.getString(75)+"\"> "
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
                                + "      <input class=\"form-control\" id=\"motivo_atraso[" + sal + "]\" name=\"motivo_atraso[" + sal + "]\" type=\"text\"  value=\""+rs.getString(76)+"\" autocomplete=\"off\"> "
                                + "    </td> "
                                + "    <td class=\"font-numero\"> "
                                + "      <input class=\"form-control\" id=\"observaciones[" + sal + "]\" name=\"observaciones[" + sal + "]\" type=\"text\" value=\""+rs.getString(77)+"\" autocomplete=\"off\"> "
                                + "    </td> ";

                    if(tipoAgente.equals("4001")||tipoAgente.equals("4006")){ //Logix ó VF
                          body += " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"llegada_a_nova[" + sal + "]\" name=\"llegada_a_nova[" + sal + "]\" type=\"text\" value=\""+rs.getString(78)+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"llegada_a_globe_trade_sd[" + sal + "]\" name=\"llegada_a_globe_trade_sd[" + sal + "]\" type=\"text\" value=\""+rs.getString(79)+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"archivo_m[" + sal + "]\" name=\"archivo_m[" + sal + "]\" type=\"text\" value=\""+rs.getString(80)+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"fecha_archivo_m[" + sal + "]\" name=\"fecha_archivo_m[" + sal + "]\" type=\"text\" value=\""+rs.getString(81)+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"fecha_solicit_manip[" + sal + "]\" name=\"fecha_solicit_manip[" + sal + "]\" type=\"text\" value=\""+rs.getString(82)+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"fecha_vencim_manip[" + sal + "]\" name=\"fecha_vencim_manip[" + sal + "]\" type=\"text\" value=\""+rs.getString(83)+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"fecha_confirm_clave_pedim[" + sal + "]\" name=\"fecha_confirm_clave_pedim[" + sal + "]\" type=\"text\" value=\""+rs.getString(84)+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"fecha_recep_increment[" + sal + "]\" name=\"fecha_recep_increment[" + sal + "]\" type=\"text\" value=\""+rs.getString(85)+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"t_e[" + sal + "]\" name=\"t_e[" + sal + "]\" type=\"text\" value=\""+rs.getString(86)+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"fecha_vencim_inbound[" + sal + "]\" name=\"fecha_vencim_inbound[" + sal + "]\" type=\"text\" value=\""+rs.getString(87)+"\" autocomplete=\"off\"> "
                                + " </td> ";

                    }

                    if(tipoAgente.equals("4002")||tipoAgente.equals("4006")){  //Cusa ó VF
                          body += " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"no_bultos[" + sal + "]\" name=\"no_bultos[" + sal + "]\" type=\"text\" value=\""+rs.getString(88)+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"peso_kg[" + sal + "]\" name=\"peso_kg[" + sal + "]\" type=\"text\" value=\""+rs.getString(89)+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "      <select class=\"form-control\" id=\"transferencia[" + sal + "]\" name=\"transferencia[" + sal + "]\" value=\""+rs.getString(90)+"\">" 
                                + "         <option value=\"Si\">SI</option>" 
                                + "         <option value=\"No\" disabled selected>NO</option>" 
                                + "      </select>" 
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"fecha_inicio_etiquetado[" + sal + "]\" name=\"fecha_inicio_etiquetado[" + sal + "]\" type=\"text\" value=\""+rs.getString(91)+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"fecha_termino_etiquetado[" + sal + "]\" name=\"fecha_termino_etiquetado[" + sal + "]\" type=\"text\" value=\""+rs.getString(92)+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"hora_termino_etiquetado[" + sal + "]\" name=\"hora_termino_etiquetado[" + sal + "]\" type=\"text\" value=\""+rs.getString(93)+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"proveedor[" + sal + "]\" name=\"proveedor[" + sal + "]\" type=\"text\" value=\""+rs.getString(94)+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"proveedor_carga[" + sal + "]\" name=\"proveedor_carga[" + sal + "]\" type=\"text\" value=\""+rs.getString(95)+"\" autocomplete=\"off\"> "
                                + " </td> ";
                    }
                          body += " <td class=\"font-numero\"> "
                                + "     <input class=\"form-control\" id=\"fy[" + sal + "]\" name=\"fy[" + sal + "]\" type=\"text\" value=\""+rs.getString(96)+"\" autocomplete=\"off\"> "
                                + " </td> "
                                + "</tr>";
                        sal++;
                    }
            /*
                String array1 = list_evento.toString();
                for (char c : charsToRemove.toCharArray()) {
                    array1 = array1.replace(String.valueOf(c),"");
                }     
                System.out.println("listar combo evento :" + array1);
            */
             multiselect_evento = " <div id=\"buscador\"> "
                                + "     <div id=\"primero\"> " 
                                //+ "         <select class=\"selectpicker\" id=\"col_evento\" name=\"col_evento\" multiple aria-label=\"Seleccione\"> "
                                + "         <select class=\"form-control\" id=\"col_evento\" name=\"col_evento\"> "
                                + "           <option value=\"0\"></option>"
                                + list_evento
                                + "         </select> "
                                + "     </div> "
                                + "     <div id=\"segundo\"> "
                                + "         <a class=\"text-lg text-info\" onclick=\"customForm('1')\"><i class=\"fa fa-search\"></i></a> "
                                + "     </div> "
                                + " </div> ";  
             
       multiselect_referenciaAA = " <div id=\"buscador\"> "
                                + "     <div id=\"primero\"> " 
                                + "         <select class=\"form-control\" id=\"col_referenciaAA\" name=\"col_referenciaAA\"> "
                                + "           <option value=\"0\"></option>"
                                + list_referenciaAA
                                + "         </select> "
                                + "     </div> "
                                + "     <div id=\"segundo\"> "
                                + "         <a class=\"text-lg text-info\" onclick=\"customForm('2')\"><i class=\"fa fa-search\"></i></a> "
                                + "     </div> "
                                + " </div> ";  
       
        multiselect_responsable = " <div id=\"buscador\"> "
                                + "     <div id=\"primero\"> " 
                                + "         <select class=\"form-control\" id=\"col_responsable\" name=\"col_responsable\"> "
                                + "           <option value=\"0\"></option>"
                                + list_responsable
                                + "         </select> "
                                + "     </div> "
                                + "     <div id=\"segundo\"> "
                                + "         <a class=\"text-lg text-info\" onclick=\"customForm('3')\"><i class=\"fa fa-search\"></i></a> "
                                + "     </div> "
                                + " </div> ";  
        
   multiselect_finalDestination = " <div id=\"buscador\"> "
                                + "     <div id=\"primero\"> " 
                                + "         <select class=\"form-control\" id=\"col_finalDestination\" name=\"col_finalDestination\"> "
                                + "           <option value=\"0\"></option>"
                                + list_finalDestination
                                + "         </select> "
                                + "     </div> "
                                + "     <div id=\"segundo\"> "
                                + "         <a class=\"text-lg text-info\" onclick=\"customForm('4')\"><i class=\"fa fa-search\"></i></a> "
                                + "     </div> "
                                + " </div> ";  
   
      multiselect_brandDivision = " <div id=\"buscador\"> "
                                + "     <div id=\"primero\"> " 
                                + "         <select class=\"form-control\" id=\"col_brandDivision\" name=\"col_brandDivision\"> "
                                + "           <option value=\"0\"></option>"
                                + list_brandDivision
                                + "         </select> "
                                + "     </div> "
                                + "     <div id=\"segundo\"> "
                                + "         <a class=\"text-lg text-info\" onclick=\"customForm('5')\"><i class=\"fa fa-search\"></i></a> "
                                + "     </div> "
                                + " </div> ";  
      
      multiselect_division = " <div id=\"buscador\"> "
                                + "     <div id=\"primero\"> " 
                                + "         <select class=\"form-control\" id=\"col_division\" name=\"col_division\"> "
                                + "           <option value=\"0\"></option>"
                                + list_division
                                + "         </select> "
                                + "     </div> "
                                + "     <div id=\"segundo\"> "
                                + "         <a class=\"text-lg text-info\" onclick=\"customForm('6')\"><i class=\"fa fa-search\"></i></a> "
                                + "     </div> "
                                + " </div> "; 
         
         multiselect_shipmentId = " <div id=\"buscador\"> "
                                + "     <div id=\"primero\"> " 
                                + "         <select class=\"form-control\" id=\"col_shipmentId\" name=\"col_shipmentId\"> "
                                + "           <option value=\"0\"></option>"
                                + list_shipmentId
                                + "         </select> "
                                + "     </div> "
                                + "     <div id=\"segundo\"> "
                                + "         <a class=\"text-lg text-info\" onclick=\"customForm('7')\"><i class=\"fa fa-search\"></i></a> "
                                + "     </div> "
                                + " </div> ";  
         
        multiselect_containerId = " <div id=\"buscador\"> "
                                + "     <div id=\"primero\"> " 
                                + "         <select class=\"form-control\" id=\"col_container\" name=\"col_container\"> "
                                + "           <option value=\"0\"></option>"
                                + list_containerId
                                + "         </select> "
                                + "     </div> "
                                + "     <div id=\"segundo\"> "
                                + "         <a class=\"text-lg text-info\" onclick=\"customForm('8')\"><i class=\"fa fa-search\"></i></a> "
                                + "     </div> "
                                + " </div> ";   

        multiselect_blAwbPro = " <div id=\"buscador\"> "
                                + "     <div id=\"primero\"> " 
                                + "         <select class=\"form-control\" id=\"col_blAwbPro\" name=\"col_blAwbPro\"> "
                                + "           <option value=\"0\"></option>"
                                + list_blAwbPro
                                + "         </select> "
                                + "     </div> "
                                + "     <div id=\"segundo\"> "
                                + "         <a class=\"text-lg text-info\" onclick=\"customForm('9')\"><i class=\"fa fa-search\"></i></a> "
                                + "     </div> "
                                + " </div> ";
        
           multiselect_loadType = " <div id=\"buscador\"> "
                                + "     <div id=\"primero\"> " 
                                + "         <select class=\"form-control\" id=\"col_loadType\" name=\"col_loadType\"> "
                                + "           <option value=\"0\"></option>"
                                + list_loadType
                                + "         </select> "
                                + "     </div> "
                                + "     <div id=\"segundo\"> "
                                + "         <a class=\"text-lg text-info\" onclick=\"customForm('10')\"><i class=\"fa fa-search\"></i></a> "
                                + "     </div> "
                                + " </div> ";  
                
                       contador = " <input type=\"hidden\" id=\"numCustoms\" name=\"numCustoms\" value=\"" + sal + "\"> "; 

                          tabla = " <table id=\"main-table\" class=\"main-table\" style=\"table-layout:fixed; width:1000%;\"> "
                                + "     <thead align=\"center\"> "
                                + "         <tr> "
                                + "             <th class=\"col-sm-1\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"></th> "  
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"text-align:left;\"><font size=\"1\">Número de evento <strong style=\"color:red\">*</strong></font>"+multiselect_evento+"</th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Referencia AA</font>"+multiselect_referenciaAA+"</th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Responsable</font>"+multiselect_responsable+"</th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"1\">Final Destination</font>"+multiselect_finalDestination+"</th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Brand-Division</font>"+multiselect_brandDivision+"</th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Division</font>"+multiselect_division+"</th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Shipment ID</font>"+multiselect_shipmentId+"</th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Container</font>"+multiselect_containerId+"</th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">BL/AWB/PRO</font>"+multiselect_blAwbPro+"</th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">LoadType</font>"+multiselect_loadType+"</th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Quantity</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">POD</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"1\">Est. Departure from POL</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"1\">ETA REAL Port of Discharge</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Est. Eta DC</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"1\">Inbound notification</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">POL</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">A.A.</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Fecha Mes de Venta</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Prioridad Si/No</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">País Origen</font></th> "	
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Size Container</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Valor USD</font></th> "	
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">ETA Port Of Discharge</font></th> "	
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Agente Aduanal</font></th> "	
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Pedimento A1</font></th> "	
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Pedimento R1</font></th> "	
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Motivo rectificación 1</font></th> "	
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Pedimento R1 (2do)</font></th> "	
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Motivo rectificación 2</font></th> "	
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fecha Recepción Documentos</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#FF4040;\"><font size=\"2\">Recinto</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#FF4040;\"><font size=\"2\">Naviera / Forwarder</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#FF4040;\"><font size=\"2\">Buque</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fecha Revalidación/Liberación de BL</font></th> "	
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fecha Previo Origen</font></th> "	
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fecha Previo en destino</font></th> "	
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fecha Resultado Previo</font></th> "	
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Proforma Final</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Requiere permiso</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fecha envío Fichas/notas</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fec. Recepción de permisos tramit.</font></th> "	
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fec. Act Permisos (Inic Vigencia)</font></th> "	
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fec. Perm. Aut. (Fin de Vigencia)</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Cuenta con CO para aplicar preferencia Arancelaria</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Aplico Preferencia Arancelaria</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Requiere UVA</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#626567;\"><font size=\"2\">Requiere CA</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#626567;\"><font size=\"2\">Fecha Recepción CA</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#626567;\"><font size=\"2\">Número de Constancia CA</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#626567;\"><font size=\"2\">Monto CA</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fecha Documentos Completos</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fecha Pago Pedimento</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fecha Solicitud de transporte</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fecha Modulacion</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Modalidad</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Resultado Modulacion</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fecha Reconocimiento</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fecha Liberacion</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Sello Origen</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Sello Final</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fecha de retencion por la autoridad</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Fec. de liberacion por ret. de la aut.</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Estatus de la operación</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Motivo Atraso</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#00BFBF;\"><font size=\"2\">Observaciones</font></th> ";

                    if(tipoAgente.equals("4001")||tipoAgente.equals("4006")){ //Logix ó VF

                         tabla += "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Llegada a NOVA</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Llegada a Globe trade SD</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Archivo M</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Fecha de Archivo M</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Fecha Solicitud de Manipulacion</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Fecha de vencimiento de Manipulacion</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Fecha confirmacion Clave de Pedimento</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Fecha de Recepcion de Incrementables</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">T&E</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Fecha de Vencimiento del Inbound</font></th> ";

                    }

                    if(tipoAgente.equals("4002")||tipoAgente.equals("4006")){  //Cusa ó VF

                         tabla += "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">No. BULTOS</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Peso (KG)</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Transferencia (SI / NO)</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Fecha Inicio Etiquetado</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Fecha Termino Etiquetado</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Hora de termino Etiquetado</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Proveedor</font></th> "
                                + "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">Proveedor de Carga</font></th> ";

                    }

                         tabla += "             <th class=\"col-sm-3\" class=\"font-titulo\" style=\"background-color:#8BC4C4;\"><font size=\"2\">FY</font></th> "
                                + "         </tr> "
                                + "     </thead> "
                                + "     <tbody>"
                                + body        
                                + "    </tbody> "
                                + " </table> "
                                + contador; 

            dao.CerrarConexion();
            oraDB.close();

            if (sal > 0) {
                out.print(tabla);
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
