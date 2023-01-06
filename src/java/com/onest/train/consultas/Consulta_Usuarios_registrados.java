/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;

import com.dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TACTS
 */
public class Consulta_Usuarios_registrados extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession ownsession = request.getSession();
        String Div = (String) ownsession.getAttribute("cbdivcuenta");
        String bo = (String) ownsession.getAttribute("bodega.id");

        String shipto = request.getParameter("shipto");
        
            String MostrarDatos = " select distinct "
                    + " bp.user_name, "
                    + " bp.user_id, "
                    + " tp.descripcion, "
                    + " bp.user_nid "
                    + " from "
                    + " broker_passwd bp "
                    + " inner join tra_perfil_usuario tpu "
                    + " on "
                    + " bp.user_nid = tpu.id_usuario "
                    + " inner join tra_perfil tp "
                    + " on  "
                    + " tp.id_perfil = tpu.id_perfil "
                    + " left join tra_tienda_usuario ttu "
                    + " on "
                    + " bp.user_nid = ttu.nuser_id "
                    + " left join dilog_destinos_hilti ddh "
                    + " on "
                    + " ttu.destino_ship_to = ddh.destino_ship_to "
                    + " WHERE "
                    + " ttu.destino_ship_to='" + shipto + "' "
                    + "  and bp.user_nid <> '5428' "
                    + "  and bp.user_nid <> '5867' "
                    + "  and bp.user_nid <> '5719' "
                    + "  and bp.user_nid <> '5870' "
                    + "  and bp.user_nid <> '5639' "
                    + "  and bp.user_nid <> '5638' "
                    + "  and bp.user_nid <> '5636' "
                    + "  and bp.user_nid <> '5635' "
                    + "  and bp.user_nid <> '5871' "
                    + " order by "
                    + " bp.user_name "
                    + " ASC ";

        ServiceDAO dao = new ServiceDAO();
        ResultSet rs = dao.consulta(MostrarDatos);
        int sal = 0;
        try (PrintWriter out = response.getWriter()) {
            String salida = "\n";
            while (rs.next()) {

                // Parametros generados mediante la consulta
                String user_name = rs.getString(1);
                String user_id = rs.getString(2);
                String descripcion = rs.getString(3);
                String user_nid = rs.getString(4);
                
                salida += " <tr id=\"tr\" + sal + \"\"> "
                        + " <td name=\"user_name\" id=\"user_name\" align=\"justify\"><a href=\"#\" data-toggle=\"modal\" onclick=\"Editar('" + user_nid + "')\" ><font style=\"color:#797979\">" + user_name  + "</font></a></td>"
                        + " <td name=\"user_id\" id=\"user_id\" align=\"left\">" + user_id + "</td> "
                        + " <td name=\"descripcion\" id=\"descripcion\" align=\"center\">" + descripcion + "</td> "
                        + " <td><center><a class=\"btn btn-danger\" onclick=\"confirmar('" + user_nid + "')\"  id=\"El0\"><i id=\"iconE0\"  class=\"glyphicon glyphicon-trash\"></i></a></center></td> "
                        + " </tr> ";
                ;
                sal++;

            }
            dao.CerrarConexion();
            //salida+="                                </tbody>";
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
            Logger.getLogger(ConsultaPrioridadAsignaciones.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultaPrioridadAsignaciones.class.getName()).log(Level.SEVERE, null, ex);
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
