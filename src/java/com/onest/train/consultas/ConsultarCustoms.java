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
                        +" <th id=\"columna\"><center><img id=\"imgSemaforo"+cont+"\" src=\""+colorSemaforo+"\" width=\""+sizeSemaforo+"\"></center></th>"
                        +" <th id=\"ReferenciaAduanal\"><input type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" class=\"form-control\" id=\"referenciaAA["+cont+"]\" name=\"referenciaAA["+cont+"]\" value=\""+row[30].trim()+"\" autocomplete=\"off\"></th>"
                        +" <th class=\"font-numero first-column\" id=\"elemento"+cont+"\">"+row[0]+"<input type=\"hidden\" id=\"evento["+cont+"]\" name=\"evento["+cont+"]\" value=\""+row[0]+"\"><div id=\"popup"+cont+"\" style=\"display: none;\"><div id=\"mSgError"+cont+"\"></div></div></th>"
                        +" <td id=\"Responsable["+cont+"]\">"+row[1]+"</td>"
                        +" <td id=\"FinalDestination["+cont+"]\">"+row[2]+"</td>"
                        +" <td id=\"BrandDivision["+cont+"]\">"+row[21]+"</td>"
                        +" <td id=\"Division["+cont+"]\">"+row[4]+"</td>"
                        +" <td>"+row[5]+"<input type=\"hidden\" id=\"shipmentId["+cont+"]\" name=\"shipmentId["+cont+"]\" value=\""+row[5]+"\"></td>"
                        +" <td> "+row[6]+"<input type=\"hidden\" id=\"containerId["+cont+"]\" name=\"containerId["+cont+"]\" value=\""+row[6]+"\"></td>"
                        +" <td id=\"blAwbPro["+cont+"]\">"+row[7]+"</td>"
                        +" <td>"+row[8]+"<input type=\"hidden\" id=\"loadTypeFinal["+cont+"]\" name=\"loadTypeFinal["+cont+"]\" value=\""+row[8]+"\"><input type=\"hidden\" id=\"plantillaId["+cont+"]\" name=\"plantillaId["+cont+"]\" value=\""+row[17]+"\"></td>"
                        +" <td id=\"Quantity["+cont+"]\">"+row[9]+"</td>"
                        +" <td id=\"Pod["+cont+"]\">"+row[19]+"</td>"
                        +" <td id=\"EstDepartureFromPol["+cont+"]\">"+row[11]+"</td>"
                        +" <td id=\"EtaRealPortOfDischarge["+cont+"]\">"+row[12]+"</td>"
                        +" <td id=\"EstEtaDc["+cont+"]\">"+row[22]+"</td>"
                        +" <td id=\"InboundNotification["+cont+"]\">"+row[14]+"</td>"
                        +" <td id=\"Pol["+cont+"]\">"+row[20]+"</td>"
                        +" <td id=\"aa["+cont+"]\">"+row[16]+"</td>"
                        +" <td id=\"FechaMesVenta["+cont+"]\">"+row[28]+"</td>"
                        +" <td>"+row[97]+"<input type=\"hidden\" id=\"prioridad["+cont+"]\" name=\"prioridad["+cont+"]\" value=\""+row[97]+"\"></td>"
                        +" <td><input class=\"form-control\" id=\"pais_origen["+cont+"]\" name=\"pais_origen["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[31].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"size_container["+cont+"]\" name=\"size_container["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[32].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"valor_usd["+cont+"]\" name=\"valor_usd["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[33].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"eta_port_discharge["+cont+"]\" name=\"eta_port_discharge["+cont+"]\" type=\"text\" value=\""+row[34].trim()+"\" onchange=\"pedimento(this.value,"+cont+")\" autocomplete=\"off\"><a class=\"text-lg text-blue\" onclick=\"historicoSemaforo("+row[5]+")\"><i class=\"fa fa-folder-open\"></i></a></td>"
                        +" <td><input class=\"form-control\" id=\"agente_aduanal["+cont+"]\" name=\"agente_aduanal["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[35].trim()+"\" onkeyup=\"this.value = this.value.toUpperCase()\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"pedimento_a1["+cont+"]\" name=\"pedimento_a1["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[36].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"pedimento_r1_1er["+cont+"]\" name=\"pedimento_r1_1er["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[37].trim()+"\" autocomplete=\"off\" onblur=\"cleanPedimento_r1_1er(this.value,"+cont+")\"></td>"
                        +" <td><input class=\"form-control\" id=\"motivo_rectificacion_1er["+cont+"]\" name=\"motivo_rectificacion_1er["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[38].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"pedimento_r1_2do["+cont+"]\" name=\"pedimento_r1_2do["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[39].trim()+"\" autocomplete=\"off\" onblur=\"cleanPedimento_r1_2do(this.value,"+cont+")\"></td>"
                        +" <td><input class=\"form-control\" id=\"motivo_rectificacion_2do["+cont+"]\" name=\"motivo_rectificacion_2do["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[40].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_recepcion_doc["+cont+"]\" name=\"fecha_recepcion_doc["+cont+"]\" type=\"text\" value=\""+row[41].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"recinto["+cont+"]\" name=\"recinto["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[42].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"naviera["+cont+"]\" name=\"naviera["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[43].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"buque["+cont+"]\" name=\"buque["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[44].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_revalidacion["+cont+"]\" name=\"fecha_revalidacion["+cont+"]\" type=\"text\" value=\""+row[45].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_previo_origen["+cont+"]\" name=\"fecha_previo_origen["+cont+"]\" type=\"text\" value=\""+row[46].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_previo_destino["+cont+"]\" name=\"fecha_previo_destino["+cont+"]\" type=\"text\" value=\""+row[47].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_resultado_previo["+cont+"]\" name=\"fecha_resultado_previo["+cont+"]\" type=\"text\" value=\""+row[48].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"proforma_final["+cont+"]\" name=\"proforma_final["+cont+"]\" type=\"text\" value=\""+row[49].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><select class=\"form-control\" id=\"permiso["+cont+"]\" name=\"permiso["+cont+"]\" value=\""+row[50]+"\" onchange=\"cleanPermiso(this.value,"+cont+")\"><option value=\""+row[50]+"\">"+row[50]+"</option><option value=\"Si\">Si</option><option value=\"No\">No</option></select></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_envio["+cont+"]\" name=\"fecha_envio["+cont+"]\" type=\"text\" value=\""+row[51].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_recepcion_perm["+cont+"]\" name=\"fecha_recepcion_perm["+cont+"]\" type=\"text\" value=\""+row[52].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_activacion_perm["+cont+"]\" name=\"fecha_activacion_perm["+cont+"]\" type=\"text\" value=\""+row[53].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_permisos_aut["+cont+"]\" name=\"fecha_permisos_aut["+cont+"]\" type=\"text\" value=\""+row[54].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><select class=\"form-control\" id=\"co_pref_arancelaria["+cont+"]\" name=\"co_pref_arancelaria["+cont+"]\" value=\""+row[55]+"\"><option value=\""+row[55]+"\">"+row[55]+"</option><option value=\"Si\">Si</option><option value=\"No\">No</option></select></td>"
                        +" <td><select class=\"form-control\" id=\"aplic_pref_arancelaria["+cont+"]\" name=\"aplic_pref_arancelaria["+cont+"]\" value=\""+row[56]+"\"><option value=\""+row[56]+"\">"+row[56]+"</option><option value=\"Si\">Si</option><option value=\"No\">No</option></select></td>"
                        +" <td><select class=\"form-control\" id=\"req_uva["+cont+"]\" name=\"req_uva["+cont+"]\" value=\""+row[57]+"\"><option value=\""+row[57]+"\">"+row[57]+"</option><option value=\"Si\">Si</option><option value=\"No\">No</option></select></td>"
                        +" <td><select class=\"form-control\" id=\"req_ca["+cont+"]\" name=\"req_ca["+cont+"]\"value=\""+row[58]+"\" onchange=\"cleanRequerimientoCA(this.value,"+cont+")\"><option value=\""+row[58]+"\">"+row[58]+"</option><option value=\"Si\">Si</option><option value=\"No\">No</option></select></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_recepcion_ca["+cont+"]\" name=\"fecha_recepcion_ca["+cont+"]\" type=\"text\" value=\""+row[59].trim()+"\" autocomplete=\"off\" disabled=\""+blockedDate+"\"></td>"
                        +" <td><input class=\"form-control\" id=\"num_constancia_ca["+cont+"]\" name=\"num_constancia_ca["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[60].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"monto_ca["+cont+"]\" name=\"monto_ca["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[61].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_doc_completos["+cont+"]\" name=\"fecha_doc_completos["+cont+"]\" value=\""+row[62].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker-pedimento"+cont+"\" id=\"fecha_pago_pedimento["+cont+"]\" name=\"fecha_pago_pedimento["+cont+"]\" type=\"text\" value=\""+row[63].trim()+"\" onchange=\"modulacion("+cont+")\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_solicitud_transporte["+cont+"]\" name=\"fecha_solicitud_transporte["+cont+"]\" type=\"text\" value=\""+row[64].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker-modulacion"+cont+"\" id=\"fecha_modulacion["+cont+"]\" name=\"fecha_modulacion["+cont+"]\" type=\"text\" value=\""+row[65].trim()+"\"></td>"
                        +" <td><select class=\"form-control\" id=\"modalidad["+cont+"]\" name=\"modalidad["+cont+"]\" value=\""+row[66]+"\"><option value=\""+row[66]+"\">"+row[66]+"</option><option value=\"Camión\">Camión</option><option value=\"Tren\">Tren</option></select></td>"
                        +" <td><select class=\"form-control\" id=\"resultado_modulacion["+cont+"]\" name=\"resultado_modulacion["+cont+"]\"value=\""+row[67]+"\" onchange=\"cleanResultadoModulacion(this.value,"+cont+","+AgentType+")\"><option value=\""+row[67]+"\">"+row[67]+"</option><option value=\"Verde\">Verde</option><option value=\"Rojo\">Rojo</option></select></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_reconocimiento["+cont+"]\" name=\"fecha_reconocimiento["+cont+"]\" type=\"text\" value=\""+row[68].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_liberacion["+cont+"]\" name=\"fecha_liberacion["+cont+"]\" type=\"text\" value=\""+row[69].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"sello_origen["+cont+"]\" name=\"sello_origen["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\"value=\""+row[70].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"sello_final["+cont+"]\" name=\"sello_final["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\"value=\""+row[71].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_retencion_aut["+cont+"]\" name=\"fecha_retencion_aut["+cont+"]\" type=\"text\" value=\""+row[72].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_liberacion_aut["+cont+"]\" name=\"fecha_liberacion_aut["+cont+"]\" type=\"text\" value=\""+row[73].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td onmouseover=\"formComplet("+AgentType+","+cont+")\"><select class=\"form-control\" id=\"estatus_operacion["+cont+"]\" name=\"estatus_operacion["+cont+"]\"value=\""+row[74]+"\"> <option value=\""+row[98]+"\">"+row[74]+"</option>"+listStatusOperationEvent+"</select></td>"
                        +" <td><input class=\"form-control\" id=\"motivo_atraso["+cont+"]\" name=\"motivo_atraso["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[75].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"observaciones["+cont+"]\" name=\"observaciones["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[75].trim()+"\" autocomplete=\"off\"></td>";
                 
                if(AgentType.equals("4001")||AgentType.equals("4006")){ //LOGIX Y VF

                 salida+=" <td><input class=\"form-control datepicker\" id=\"llegada_a_nova["+cont+"]\" name=\"llegada_a_nova["+cont+"]\" type=\"text\" value=\""+row[77].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"llegada_a_globe_trade_sd["+cont+"]\" name=\"llegada_a_globe_trade_sd["+cont+"]\" type=\"text\" value=\""+row[78].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"archivo_m["+cont+"]\" name=\"archivo_m["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[79].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_archivo_m["+cont+"]\" name=\"fecha_archivo_m["+cont+"]\" type=\"text\" value=\""+row[80].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_solicit_manip["+cont+"]\" name=\"fecha_solicit_manip["+cont+"]\" type=\"text\" value=\""+row[81].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_vencim_manip["+cont+"]\" name=\"fecha_vencim_manip["+cont+"]\" type=\"text\" value=\""+row[82].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_confirm_clave_pedim["+cont+"]\" name=\"fecha_confirm_clave_pedim["+cont+"]\" type=\"text\" value=\""+row[83].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_recep_increment["+cont+"]\" name=\"fecha_recep_increment["+cont+"]\" type=\"text\" value=\""+row[84].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"t_e["+cont+"]\" name=\"t_e["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[85].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_vencim_inbound["+cont+"]\" name=\"fecha_vencim_inbound["+cont+"]\" type=\"text\" value=\""+row[86].trim()+"\" autocomplete=\"off\"></td>";
                } 

                if(AgentType.equals("4002")||AgentType.equals("4006")){  //CUSA Y VF

                 salida+=" <td><input class=\"form-control\" id=\"no_bultos["+cont+"]\" name=\"no_bultos["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[87].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"peso_kg["+cont+"]\" name=\"peso_kg["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[88].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td> <select class=\"form-control\" id=\"transferencia["+cont+"]\" name=\"transferencia["+cont+"]\" value=\""+row[89]+"\"><option value=\""+row[89]+"\">"+row[89]+"</option><option value=\"Si\">Si</option><option value=\"No\">No</option> </select></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_inicio_etiquetado["+cont+"]\" name=\"fecha_inicio_etiquetado["+cont+"]\" type=\"text\" value=\""+row[90].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control datepicker\" id=\"fecha_termino_etiquetado["+cont+"]\" name=\"fecha_termino_etiquetado["+cont+"]\" type=\"text\" value=\""+row[91].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"hora_termino_etiquetado["+cont+"]\" name=\"hora_termino_etiquetado["+cont+"]\" type=\"time\" value=\""+row[92].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"proveedor["+cont+"]\" name=\"proveedor["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[93].trim()+"\" autocomplete=\"off\"></td>"
                        +" <td><input class=\"form-control\" id=\"proveedor_carga["+cont+"]\" name=\"proveedor_carga["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[94].trim()+"\" autocomplete=\"off\"></td>";
                }
                
                salida+=" <td><input class=\"form-control\" id=\"fy["+cont+"]\" name=\"fy["+cont+"]\" type=\"text\" onkeyup=\"this.value = this.value.toUpperCase()\" value=\""+row[95].trim()+"\" autocomplete=\"off\"></td>"
                       +" <td><a class=\"btn btn-primary text-uppercase\" onclick=\"AddLineCustoms("+cont+")\"><i class=\"fa fa-save\"></i></a></td>"
                       +"</tr>"; 
                          
                  cont++; 
                }     
            }
      
         out.print(salida);
         System.out.println("Esqueleto tabla:"+salida);
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