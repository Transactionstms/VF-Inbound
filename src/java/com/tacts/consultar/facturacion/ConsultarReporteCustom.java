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
            String salida = "";
            int sal = 0;
             
            ServiceDAO dao = new ServiceDAO();
            ResultSet rs = dao.consulta(fac.consultarReporteCustoms(tipoAgente));
            while (rs.next()) {

                salida += " <tr id=\"tr" + sal + "\"> "
                        + "    <th class=\"font-numero\"><center><img src=\"../../img/circle-green.png\" width=\"100%\"/></center></th> "  //Semaforo            // Semaforo
                        + "    <th class=\"font-numero\">"+rs.getString(1)+"</th> "  // Número de Evento
                        + "    <th class=\"font-numero\">"+rs.getString(31)+"</th> "  // Referencia Aduanal
                        + "    <td class=\"font-numero\">"+rs.getString(2)+"</td> "   // Responsable
                        + "    <td class=\"font-numero\">"+rs.getString(3)+"</td> "   // Final Destination
                        + "    <td class=\"font-numero\">"+rs.getString(22)+"</td> "  // Brand-Division
                        + "    <td class=\"font-numero\">"+rs.getString(5)+"</td> "   // Division
                        + "    <td class=\"font-numero\">"+rs.getString(6)+"</td> "  // Shipment ID
                        + "    <td class=\"font-numero\">"+rs.getString(7)+"</td> "  // Container
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
                        + "    <td class=\"font-numero\">"+rs.getString(98)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(32)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(33)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(34)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(35)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(36)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(37)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(38)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(39)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(40)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(41)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(42)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(43)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(44)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(45)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(46)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(47)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(48)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(49)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(50)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(51)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(52)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(53)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(54)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(55)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(56)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(57)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(58)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(59)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(60)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(61)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(62)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(63)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(64)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(65)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(66)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(67)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(68)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(69)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(70)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(71)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(72)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(73)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(74)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(75)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(76)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(77)+"</td> ";
                
            if(tipoAgente.equals("4001")||tipoAgente.equals("4006")){ //Logix ó VF
                
                salida += "    <td class=\"font-numero\">"+rs.getString(78)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(79)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(80)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(81)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(82)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(83)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(84)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(85)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(86)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(87)+"</td> ";
                    
            }
            
            if(tipoAgente.equals("4002")||tipoAgente.equals("4006")){  //Cusa ó VF
                
                salida += "    <td class=\"font-numero\">"+rs.getString(88)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(89)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(90)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(91)+"td> "
                        + "    <td class=\"font-numero\">"+rs.getString(92)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(93)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(94)+"</td> "
                        + "    <td class=\"font-numero\">"+rs.getString(95)+"</td> ";
            }
                salida += "    <td class=\"font-numero\">"+rs.getString(96)+"</td> "
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
