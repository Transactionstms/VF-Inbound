/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.onest.train.consultas;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * @author Desarrollo Tacts
 */
public class ConsultarCustoms extends HttpServlet {
    
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
         
        String AgentType = request.getParameter("AgentType");    
        String filterType = request.getParameter("filterType");    
        String id = request.getParameter("id");                       //Inicializar con 0
        String[] arrOfStr = id.split(",");
        
        //Parametros Generales
        String colorSemaforo = "";
        String sizeSemaforo = "";
        String listStatusOperationEvent = "";
        String blockedDate = "";
        String caramelo = "";  
        String salida = "";
        int cont = 1; 
        
        //Generar caramelo: Opciones del multiselect
        for (String a : arrOfStr) {
            caramelo += a+",";
        }
        caramelo = caramelo.replaceAll(",$", "");

       /*Columna: Estatus Operación (listado)*/
        if (db.doDB(fac.consultarEstatusOperacionCustoms())) {
            for (String[] rowO : db.getResultado()) {
                listStatusOperationEvent += "<option value=\"" + rowO[0] + "\">" + rowO[1] + "</option>"; 
            }
        }
      
        if (db.doDB(fac.consultarEventosCustoms(AgentType,filterType,caramelo))) {
            for (String[] row : db.getResultado()) {
                       
                    if (row[99].equals("1")) {
                        colorSemaforo = "../img/circle-green.webp";
                        sizeSemaforo = "100%";
                    } else if (row[99].equals("2")) {
                        colorSemaforo = "../img/circle-yellow.webp";
                        sizeSemaforo = "80%";
                    } else if (row[99].equals("3")) {
                        colorSemaforo = "../img/circle-red.webp";
                        sizeSemaforo = "60%";
                    } else {
                        colorSemaforo = "../img/circle-gray.webp";
                        sizeSemaforo = "100%";
                    }

                    if (row[58] == "No") {
                        blockedDate = "false";
                    }

                 salida +="<tr id=\"tr<"+cont+"\">"
                        + " <th id=\"columna\"><center><img id=\"imgSemaforo"+cont+"\" src=\""+colorSemaforo+"\" width=\""+sizeSemaforo+"\"></center></th> "
                        + " <th contenteditable=\"true\" id=\"referenciaAA["+cont+"]\">"+row[30].trim()+"</th> "
                        + " <th class=\"font-numero first-column\" id=\"elemento"+cont+"\">"+row[0]+""
                        + "   <input type=\"hidden\" id=\"evento["+cont+"]\" name=\"evento["+cont+"]\" value=\""+row[0]+"\"> "                   
                        + "   <div id=\"popup"+cont+"\" style=\"display: none;\"> "
                        + "     <div id=\"mSgError"+cont+"\"></div> "
                        + "   </div> "
                        + " </th> "
                        + " <td id=\"Responsable["+cont+"]\">"+row[1].trim()+"</td> "
                        + " <td id=\"FinalDestination["+cont+"]\">"+row[2].trim()+"</td> "
                        + " <td id=\"BrandDivision["+cont+"]\">"+row[21].trim()+"</td> "
                        + " <td id=\"Division["+cont+"]\">"+row[4].trim()+"</td> "
                        + " <td id=\"shipmentId["+cont+"]\">"+row[5].trim()+"</td> "
                        + " <td id=\"containerId["+cont+"]\">"+row[6].trim()+"</td> "
                        + " <td id=\"blAwbPro["+cont+"]\">"+row[7].trim()+"</td> "
                        + " <td id=\"loadTypeFinal["+cont+"]\">"+row[8].trim()+"</td> "
                        + " <td id=\"Quantity["+cont+"]\">"+row[9].trim()+"</td> "
                        + " <td id=\"Pod["+cont+"]\">"+row[19].trim()+"</td> "
                        + " <td id=\"EstDepartureFromPol["+cont+"]\">"+row[11].trim()+"</td> "
                        + " <td id=\"EtaRealPortOfDischarge["+cont+"]\">"+row[12].trim()+"</td> "
                        + " <td id=\"EstEtaDc["+cont+"]\">"+row[22].trim()+"</td> "
                        + " <td id=\"InboundNotification["+cont+"]\">"+row[14].trim()+"</td> "
                        + " <td id=\"Pol["+cont+"]\">"+row[20].trim()+"</td> "
                        + " <td id=\"aa["+cont+"]\">"+row[16].trim()+"</td> "
                        + " <td id=\"FechaMesVenta["+cont+"]\">"+row[28].trim()+"</td> "
                        + " <td id=\"prioridad["+cont+"]\">"+row[97].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"pais_origen["+cont+"]\">"+row[31].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"size_container["+cont+"]\">"+row[32].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"valor_usd["+cont+"]\">"+row[33].trim()+"</td> "
                        + " <td id=\"eta_port_discharge["+cont+"]\" onclick=\"show_eta_port_discharge('"+row[34].trim()+"',"+cont+")\">"+row[34].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"agente_aduanal["+cont+"]\">"+row[35].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"pedimento_a1["+cont+"]\">"+row[36].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"pedimento_r1_1er["+cont+"]\" onclick=\"cleanPedimento_r1_1er('"+row[37].trim()+"',"+cont+")\">"+row[37].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"motivo_rectificacion_1er["+cont+"]\">"+row[38].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"pedimento_r1_2do["+cont+"]\" onclick=\"cleanPedimento_r1_2do('"+row[39].trim()+"',"+cont+")\">"+row[39].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"motivo_rectificacion_2do["+cont+"]\">"+row[40].trim()+"</td> "
                        + " <td id=\"fecha_recepcion_doc["+cont+"]\" onclick=\"show_fecha_recepcion_doc('"+row[41].trim()+"',"+cont+")\">"+row[41].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"recinto["+cont+"]\">"+row[42].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"naviera["+cont+"]\">"+row[43].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"buque["+cont+"]\">"+row[44].trim()+"</td> "
                        + " <td id=\"fecha_revalidacion["+cont+"]\" onclick=\"show_fecha_revalidacion('"+row[45].trim()+"',"+cont+")\">"+row[45].trim()+"</td> "
                        + " <td id=\"fecha_previo_origen["+cont+"]\" onclick=\"show_fecha_previo_origen('"+row[46].trim()+"',"+cont+")\">"+row[46].trim()+"</td> "
                        + " <td id=\"fecha_previo_destino["+cont+"]\" onclick=\"show_fecha_previo_destino('"+row[47].trim()+"',"+cont+")\">"+row[47].trim()+"</td> "
                        + " <td id=\"fecha_resultado_previo["+cont+"]\" onclick=\"show_fecha_resultado_previo('"+row[48].trim()+"',"+cont+")\">"+row[48].trim()+"</td> "
                        + " <td id=\"proforma_final["+cont+"]\" onclick=\"show_proforma_final('"+row[49].trim()+"',"+cont+")\">"+row[49].trim()+"</td> "
                        + " <td id=\"permiso["+cont+"]\" onclick=\"show_permiso("+cont+")\">"+row[50].trim()+"</td> "
                        + " <td id=\"fecha_envio["+cont+"]\" onclick=\"show_fecha_envio('"+row[51].trim()+"',"+cont+")\">"+row[51].trim()+"</td> "
                        + " <td id=\"fecha_recepcion_perm["+cont+"]\" onclick=\"show_fecha_recepcion_perm('"+row[52].trim()+"',"+cont+")\">"+row[52].trim()+"</td> "
                        + " <td id=\"fecha_activacion_perm["+cont+"]\" onclick=\"show_fecha_activacion_perm('"+row[53].trim()+"',"+cont+")\">"+row[53].trim()+"</td> "
                        + " <td id=\"fecha_permisos_aut["+cont+"]\" onclick=\"show_fecha_permisos_aut('"+row[54].trim()+"',"+cont+")\">"+row[54].trim()+"</td> " 
                        + " <td id=\"co_pref_arancelaria["+cont+"]\" onclick=\"show_co_pref_arancelaria("+cont+")\">"+row[55].trim()+"</td> "
                        + " <td id=\"aplic_pref_arancelaria["+cont+"]\" onclick=\"show_aplic_pref_arancelaria("+cont+")\">"+row[56].trim()+"</td> "
                        + " <td id=\"req_uva["+cont+"]\" onclick=\"show_req_uva("+cont+")\">"+row[57].trim()+"</td> "
                        + " <td id=\"req_ca["+cont+"]\" onclick=\"show_req_ca("+cont+")\">"+row[58].trim()+"</td> "
                        + " <td id=\"fecha_recepcion_ca["+cont+"]\" onclick=\"show_fecha_recepcion_ca('"+row[59].trim()+"',"+cont+")\">"+row[59].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"num_constancia_ca["+cont+"]\">"+row[60].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"monto_ca["+cont+"]\">"+row[61].trim()+"</td> "
                        + " <td id=\"fecha_doc_completos["+cont+"]\" onclick=\"show_fecha_doc_completos('"+row[63].trim()+"',"+cont+")\">"+row[62].trim()+"</td> "
                        + " <td id=\"fecha_pago_pedimento["+cont+"]\" onclick=\"show_fecha_pago_pedimento("+cont+")\">"+row[63].trim()+"</td> "
                        + " <td id=\"fecha_solicitud_transporte["+cont+"]\" onclick=\"show_fecha_solicitud_transporte('"+row[64].trim()+"',"+cont+")\">"+row[64].trim()+"</td> "
                        + " <td id=\"fecha_modulacion["+cont+"]\" onclick=\"show_fecha_modulacion("+cont+")\">"+row[65].trim()+"</td> "
                        + " <td id=\"modalidad["+cont+"]\" onclick=\"show_modalidad("+cont+")\">"+row[66].trim()+"</td> "
                        + " <td id=\"resultado_modulacion["+cont+"]\" onclick=\"show_resultado_modulacion("+cont+","+AgentType+")\">"+row[67].trim()+"</td> "
                        + " <td id=\"fecha_reconocimiento["+cont+"]\" onclick=\"show_fecha_reconocimiento('"+row[68].trim()+"',"+cont+")\">"+row[68].trim()+"</td> "
                        + " <td id=\"fecha_liberacion["+cont+"]\" onclick=\"show_fecha_liberacion('"+row[69].trim()+"',"+cont+")\">"+row[69].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"sello_origen["+cont+"]\">"+row[70].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"sello_final["+cont+"]\">"+row[71].trim()+"</td> "
                        + " <td id=\"fecha_retencion_aut["+cont+"]\" onclick=\"show_fecha_retencion_aut('"+row[72].trim()+"',"+cont+")\">"+row[72].trim()+"</td> "
                        + " <td id=\"fecha_liberacion_aut["+cont+"]\" onclick=\"show_fecha_liberacion_aut('"+row[73].trim()+"',"+cont+")\">"+row[73].trim()+"</td> "
                        + " <td onmouseover=\"formComplet('"+AgentType+"',"+cont+")\"><select class=\"form-control\" style=\"border: none; outline: none;\" id=\"estatus_operacion["+cont+"]\" name=\"estatus_operacion["+cont+"]\" value=\""+row[74]+"\"> <option value=\""+row[98]+"\">"+row[74]+"</option>"+listStatusOperationEvent+"</select></td> "
                        + " <td contenteditable=\"true\" id=\"motivo_atraso["+cont+"]\">"+row[75].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"observaciones["+cont+"]\">"+row[76].trim()+"</td> ";
                 
                if(AgentType.equals("4001")||AgentType.equals("4006")){ //LOGIX Y VF

                 salida+= " <td id=\"llegada_a_nova["+cont+"]\" onclick=\"show_llegada_a_nova('"+row[77].trim()+"',"+cont+")\">"+row[77].trim()+"</td> "
                        + " <td id=\"llegada_a_globe_trade_sd["+cont+"]\" onclick=\"show_llegada_a_globe_trade_sd('"+row[78].trim()+"',"+cont+")\">"+row[78].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"archivo_m["+cont+"]\">"+row[79].trim()+"</td> "
                        + " <td id=\"fecha_archivo_m["+cont+"]\" onclick=\"show_fecha_archivo_m('"+row[80].trim()+"',"+cont+")\">"+row[80].trim()+"</td> "
                        + " <td id=\"fecha_solicit_manip["+cont+"]\" onclick=\"show_fecha_solicit_manip('"+row[81].trim()+"',"+cont+")\">"+row[81].trim()+"</td> "
                        + " <td id=\"fecha_vencim_manip["+cont+"]\" onclick=\"show_fecha_vencim_manip('"+row[82].trim()+"',"+cont+")\">"+row[82].trim()+"</td> "
                        + " <td id=\"fecha_confirm_clave_pedim["+cont+"]\" onclick=\"show_fecha_confirm_clave_pedim('"+row[83].trim()+"',"+cont+")\">"+row[83].trim()+"</td> "
                        + " <td id=\"fecha_recep_increment["+cont+"]\" onclick=\"show_fecha_recep_increment('"+row[84].trim()+"',"+cont+")\">"+row[84].trim()+"</td> "
                        + " <td contenteditable=\"true\" id=\"t_e["+cont+"]\">"+row[85].trim()+"</td> "
                        + " <td id=\"fecha_vencim_inbound["+cont+"]\" onclick=\"show_fecha_vencim_inbound('"+row[86].trim()+"',"+cont+")\">"+row[86].trim()+"</td> ";
                } 

                if(AgentType.equals("4002")||AgentType.equals("4006")){  //CUSA Y VF

                 salida+=" <td contenteditable=\"true\" id=\"no_bultos["+cont+"]\">"+row[87].trim()+"</td> "
                       + " <td contenteditable=\"true\" id=\"peso_kg["+cont+"]\">"+row[88].trim()+"</td> "
                       + " <td id=\"transferencia["+cont+"]\" onclick=\"show_transferencia("+cont+")\">"+row[89].trim()+"</td> "
                       + " <td id=\"fecha_inicio_etiquetado["+cont+"]\" onclick=\"show_fecha_inicio_etiquetado('"+row[90].trim()+"',"+cont+")\">"+row[90].trim()+"</td> "
                       + " <td id=\"fecha_termino_etiquetado["+cont+"]\" onclick=\"show_fecha_termino_etiquetado('"+row[91].trim()+"',"+cont+")\">"+row[91].trim()+"</td> "
                       + " <td><input class=\"form-control\" style=\"border: none; outline: none;\" id=\"hora_termino_etiquetado["+cont+"]\" name=\"hora_termino_etiquetado["+cont+"]\" type=\"time\" value=\""+row[92].trim()+"\" autocomplete=\"off\"></td> "
                       + " <td contenteditable=\"true\" id=\"proveedor["+cont+"]\">"+row[93].trim()+"</td> "
                       + " <td contenteditable=\"true\" id=\"proveedor_carga["+cont+"]\">"+row[94].trim()+"</td> ";
                }
                
                salida+=" <td contenteditable=\"true\" id=\"fy["+cont+"]\">"+row[95].trim()+"</td> "
                      + " <td><a class=\"btn btn-primary text-uppercase\" onclick=\"AddLineCustoms("+cont+")\"><i class=\"fa fa-save\"></i></a></td> "
                       +"</tr>"; 
                          
                  cont++; 
                }     
            }
      
                salida+="<input type=\"hidden\" id=\"numCustoms\" name=\"numCustoms\" value=\""+cont+"\">";
                
         out.print(salida);
         oraDB.close(); //cerrar conexión
            
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
            Logger.getLogger(ConsultarCustoms.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarCustoms.class.getName()).log(Level.SEVERE, null, ex);
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