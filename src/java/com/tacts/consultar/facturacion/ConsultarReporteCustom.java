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
 * @author Desarrollo Tacts
 */
public class ConsultarReporteCustom extends HttpServlet {

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
            String tipoFiltro = request.getParameter("tipoFiltro");
            String id = request.getParameter("id");
            
            //Parametros Generales:
            String salida = "";
            String caramelo = "";
            int sal = 0;
            
            //Generar caramelo: Opciones del multiselect
            String[] arrOfStr = id.split(",");
            
            for (String a : arrOfStr) {
                caramelo += "'" + a + "',";
            }
            caramelo = caramelo.replaceAll(",$", "");
             
            ServiceDAO dao = new ServiceDAO();
            ResultSet rs = dao.consulta(fac.consultarReporteCustoms(tipoAgente,tipoFiltro,caramelo));
            while (rs.next()) {

                salida += " <tr id=\"tr" + sal + "\"> "
                        + "    <th class=\"font-numero\"><center><img src=\"../../img/circle-green.png\" width=\"100%\"/></center></th> "  //Semaforo 
                        + "    <th class=\"font-numero\">"+rs.getString(31).trim()+"</th> "  // Referencia Aduanal
                        + "    <th class=\"font-numero\">"+rs.getString(1).trim()+"</th> "   // Evento
                        + "    <td class=\"font-numero\">"+rs.getString(2).trim()+"</td> "   // Responsable
                        + "    <td class=\"font-numero\">"+rs.getString(3).trim()+"</td> "   // Final Destination
                        + "    <td class=\"font-numero\">"+rs.getString(22).trim()+"</td> "  // Brand-Division
                        + "    <td class=\"font-numero\">"+rs.getString(5).trim()+"</td> "   // Division
                        + "    <td class=\"font-numero\">"+rs.getString(6).trim()+"</td> "   // Shipment ID
                        + "    <td class=\"font-numero\">"+rs.getString(7).trim()+"</td> "   // Container
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
                        + "    <td class=\"font-numero\">"+rs.getString(98).trim()+"</td> "  // Prioridad
                        + "    <td class=\"font-numero\">"+rs.getString(32).trim()+"</td> "  // País Origen    
                        + "    <td class=\"font-numero\">"+rs.getString(33).trim()+"</td> "  // Size Container 
                        + "    <td class=\"font-numero\">"+rs.getString(34).trim()+"</td> "  // Valor USD
                        + "    <td class=\"font-numero\">"+rs.getString(35).trim()+"</td> "  // ETA Port Of Discharge
                        + "    <td class=\"font-numero\">"+rs.getString(36).trim()+"</td> "  // Agente Aduanal 
                        + "    <td class=\"font-numero\">"+rs.getString(37).trim()+"</td> "  // Pedimento A1   
                        + "    <td class=\"font-numero\">"+rs.getString(38).trim()+"</td> "  // Pedimento R1
                        + "    <td class=\"font-numero\">"+rs.getString(39).trim()+"</td> "  // Motivo Rectificación 1
                        + "    <td class=\"font-numero\">"+rs.getString(40).trim()+"</td> "  // Pedimento R1 (2DO)
                        + "    <td class=\"font-numero\">"+rs.getString(41).trim()+"</td> "  // Motivo Rectificación 2
                        + "    <td class=\"font-numero\">"+rs.getString(42).trim()+"</td> "  // Fecha Recepción Documentos
                        + "    <td class=\"font-numero\">"+rs.getString(43).trim()+"</td> "  // Recinto
                        + "    <td class=\"font-numero\">"+rs.getString(44).trim()+"</td> "  // Naviera/Forwarder
                        + "    <td class=\"font-numero\">"+rs.getString(45).trim()+"</td> "  // Buque
                        + "    <td class=\"font-numero\">"+rs.getString(46).trim()+"</td> "  // Fecha Revalidación/Liberación de BL
                        + "    <td class=\"font-numero\">"+rs.getString(47).trim()+"</td> "  // Fecha Previo Origen
                        + "    <td class=\"font-numero\">"+rs.getString(48).trim()+"</td> "  // Fecha Previo en destino
                        + "    <td class=\"font-numero\">"+rs.getString(49).trim()+"</td> "  // Fecha Resultado Previo
                        + "    <td class=\"font-numero\">"+rs.getString(50).trim()+"</td> "  // Proforma Final 
                        + "    <td class=\"font-numero\">"+rs.getString(51).trim()+"</td> "  // Requiere permiso
                        + "    <td class=\"font-numero\">"+rs.getString(52).trim()+"</td> "  // Fecha envío Fichas/notas
                        + "    <td class=\"font-numero\">"+rs.getString(53).trim()+"</td> "  // Fec. Recepción de permisos tramit.
                        + "    <td class=\"font-numero\">"+rs.getString(54).trim()+"</td> "  // Fec. Act Permisos (Inic Vigencia)
                        + "    <td class=\"font-numero\">"+rs.getString(55).trim()+"</td> "  // Fec. Perm. Aut. (Fin de Vigencia) 
                        + "    <td class=\"font-numero\">"+rs.getString(56).trim()+"</td> "  // Cuenta con CO para aplicar preferencia Arancelaria
                        + "    <td class=\"font-numero\">"+rs.getString(57).trim()+"</td> "  // Aplico Preferencia Arancelaria 
                        + "    <td class=\"font-numero\">"+rs.getString(58).trim()+"</td> "  // Requiere UVA
                        + "    <td class=\"font-numero\">"+rs.getString(59).trim()+"</td> "  // Requiere CA
                        + "    <td class=\"font-numero\">"+rs.getString(60).trim()+"</td> "  // Fecha Recepción CA
                        + "    <td class=\"font-numero\">"+rs.getString(61).trim()+"</td> "  // Número de Constancia CA 
                        + "    <td class=\"font-numero\">"+rs.getString(62).trim()+"</td> "  // Monto CA
                        + "    <td class=\"font-numero\">"+rs.getString(63).trim()+"</td> "  // Fecha Documentos Completos
                        + "    <td class=\"font-numero\">"+rs.getString(64).trim()+"</td> "  // Fecha Pago Pedimento
                        + "    <td class=\"font-numero\">"+rs.getString(65).trim()+"</td> "  // Fecha Solicitud de transporte
                        + "    <td class=\"font-numero\">"+rs.getString(66).trim()+"</td> "  // Fecha Modulacion
                        + "    <td class=\"font-numero\">"+rs.getString(67).trim()+"</td> "  // Modalidad
                        + "    <td class=\"font-numero\">"+rs.getString(68).trim()+"</td> "  // Resultado Modulacion
                        + "    <td class=\"font-numero\">"+rs.getString(69).trim()+"</td> "  // Fecha Reconocimiento
                        + "    <td class=\"font-numero\">"+rs.getString(70).trim()+"</td> "  // Fecha Liberacion 
                        + "    <td class=\"font-numero\">"+rs.getString(71).trim()+"</td> "  // Sello Origen 
                        + "    <td class=\"font-numero\">"+rs.getString(72).trim()+"</td> "  // Sello Final
                        + "    <td class=\"font-numero\">"+rs.getString(73).trim()+"</td> "  // Fecha de retencion por la autoridad 
                        + "    <td class=\"font-numero\">"+rs.getString(74).trim()+"</td> "  // Fec. de liberacion por ret. de la aut.
                        + "    <td class=\"font-numero\">"+rs.getString(75).trim()+"</td> "  // Estatus de la operación
                        + "    <td class=\"font-numero\">"+rs.getString(76).trim()+"</td> "  // Motivo Atraso
                        + "    <td class=\"font-numero\">"+rs.getString(77).trim()+"</td> "; // Observaciones
                
            if(tipoAgente.equals("4001")||tipoAgente.equals("4006")){ //Logix ó VF
                
                salida += "    <td class=\"font-numero\">"+rs.getString(78).trim()+"</td> "  // Llegada a NOVA
                        + "    <td class=\"font-numero\">"+rs.getString(79).trim()+"</td> "  // Llegada a Globe trade SD  
                        + "    <td class=\"font-numero\">"+rs.getString(80).trim()+"</td> "  // Archivo M 
                        + "    <td class=\"font-numero\">"+rs.getString(81).trim()+"</td> "  // Fecha de Archivo M 
                        + "    <td class=\"font-numero\">"+rs.getString(82).trim()+"</td> "  // Fecha Solicitud de Manipulacion
                        + "    <td class=\"font-numero\">"+rs.getString(83).trim()+"</td> "  // Fecha de vencimiento de Manipulacion
                        + "    <td class=\"font-numero\">"+rs.getString(84).trim()+"</td> "  // Fecha confirmacion Clave de Pedimento
                        + "    <td class=\"font-numero\">"+rs.getString(85).trim()+"</td> "  // Fecha de Recepcion de Incrementables
                        + "    <td class=\"font-numero\">"+rs.getString(86).trim()+"</td> "  // T&E 
                        + "    <td class=\"font-numero\">"+rs.getString(87).trim()+"</td> "; // Fecha de Vencimiento del Inbound  
                    
            }
            
            if(tipoAgente.equals("4002")||tipoAgente.equals("4006")){  //Cusa ó VF
                
                salida += "    <td class=\"font-numero\">"+rs.getString(88).trim()+"</td> "  // No. BULTOS
                        + "    <td class=\"font-numero\">"+rs.getString(89).trim()+"</td> "  // Peso (KG)
                        + "    <td class=\"font-numero\">"+rs.getString(90).trim()+"</td> "  // Transferencia 
                        + "    <td class=\"font-numero\">"+rs.getString(91).trim()+"</td> "  // Fecha Inicio Etiquetado
                        + "    <td class=\"font-numero\">"+rs.getString(92).trim()+"</td> "  // Fecha Termino Etiquetado 
                        + "    <td class=\"font-numero\">"+rs.getString(93).trim()+"</td> "  // Hora de termino Etiquetado
                        + "    <td class=\"font-numero\">"+rs.getString(94).trim()+"</td> "  // Proveedor
                        + "    <td class=\"font-numero\">"+rs.getString(95).trim()+"</td> "; // Proveedor de Carga 
            }
                salida += "    <td class=\"font-numero\">"+rs.getString(96).trim()+"</td> "  // FY
                        + "</tr>";
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
            Logger.getLogger(ConsultarReporteCustom.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarReporteCustom.class.getName()).log(Level.SEVERE, null, ex);
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
