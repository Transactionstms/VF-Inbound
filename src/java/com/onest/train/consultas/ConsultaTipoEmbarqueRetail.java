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
import java.util.HashMap;
import java.util.Map;
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
public class ConsultaTipoEmbarqueRetail extends HttpServlet {

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

        String MostrarDatos = " select                 "
                + " c.camion_id,                       " 
                + " c.camion_placas,                   " 
                + " ut.utransporte_desc                "
                + " from                               " 
                + " ontms_camion c,                    "
                + " ontms_linea_transporte lt,         "
                + " ontms_unidad_transporte ut         "
                + " where                              "
                + " c.ltransporte_id=lt.ltransporte_id "
                + " and                                "
                + " c.utransporte_id=ut.utransporte_id "
                + " order by                           "
                + " c.camion_placas                    ";
        
        ServiceDAO dao = new ServiceDAO();
        ResultSet rs = dao.consulta(MostrarDatos);
        int sal = 0;
        try (PrintWriter out = response.getWriter()) {
            String salida = "\n";
            while (rs.next()) {

                // Parametros generados mediante la consulta
                String camion_id = rs.getString(1);
                String camion_placas = rs.getString(2);
                String transporte_desc = rs.getString(3);
              
                salida += " <div class=\"modal fade\" id=\"squarespaceModalchofer\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"modalLabel\" aria-hidden=\"true\">\n" +
"                                        <div class=\"modal-dialog\" id=\"mdialTamanio\">\n" +
"                                            <div class=\"modal-content\">\n" +
"                                                <div class=\"modal-header\">\n" +
"                                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">×</button>\n" +
"                                                    <div class=\"col-md-8 col-md-offset-4\">\n" +
"                                                        <h4 class=\"modal-title\"><i class=\"fa fa-truck\"></i>&nbsp; Tipo de Embarque</h4>\n" +
"                                                    </div>\n" +
"                                                </div>\n" +
"                                                <div class=\"modal-body\">\n" +
"                                                     <div class=\"tab-pane\" id=\"new-account\">\n" +
"                                                            <div class=\"container\">\n" +
"                                                                <div class=\"row\">\n" +
"                                                                    <div class=\"col-md-12 col-sm-12\">\n" +
"                                                                        <label>Ingrese su tipo de embarque:</label>\n" +
"                                                                        <div class=\"col-md-12 col-sm-6\">\n" +
"                                                                            <ol>\n" +
"                                                                                <li><input name=\"dir\" id=\"dir\" type=\"checkbox\" onclick=\"showMe('directo', this)\"> Directo </li>\n" +
"                                                                                <li><input name=\"paq\" id=\"paq\" type=\"checkbox\" onclick=\"showMe('paqueteria', this)\"> Paqueteria </li>\n" +
"                                                                                 \n" +
"                                                                            </ol>\n" +
"                                                                        </div>\n" +
"                                                                    </div>    \n" +
"                                                                </div>\n" +
"                                                                <div style=\"display: none;\" id=\"directo\">\n" +
"                                                                    <div class=\"row\">\n" +
"                                                                         <div class=\"col-md-12 col-sm-12\">\n" +
"                                                                             <br>\n" +
"                                                                           <div  class=\"col-md-6 col-sm-6\">\n" +
"                                                                               <label>Transportistas</label>\n" +
"                                                                               \n" +
"                                                                                <select id=\"clientest\" name=\"transporte\" data-placeholder=\"Selecciona Transporte\" class=\"chosen-select\" tabindex=\"2\">\n" +
"                                                                                    <option value=\"\"></option>\n" +
"                                                                                    <option value=\"3405\">BLOSOM</option>\n" +
"                                                                                    <option value=\"3406\">CLIENTE RECOJE</option>\n" +
"                                                                                    <option value=\"3385\">LOGISTICA Y TRANSPORTACION GRANADOS SA DE CV</option>\n" +
"                                                                                    <option value=\"3425\">PAQUETE EXPRESS</option>\n" +
"                                                                                    <option value=\"3445\">PAQUETERIA BLOSOM</option>\n" +
"                                                                                    <option value=\"3369\">TRANSPORTE RINOCERONTE</option>\n" +
"                                                                                    <option value=\"3368\">TRANSPORTES MENESES</option>\n" +
"                                                                                    <option value=\"3366\">TRANSPORTES NECA</option>\n" +
"                                                                                    <option value=\"3367\">TRANSPORTES VF</option>\n" +
"                                                                                </select>\n" +
"                                                                            </div>\n" +
"                                                                        </div>\n" +
"                                                                        <div class=\"col-md-12 col-sm-12\">\n" +
"                                                                            <br>\n" +
"                                                                            <div  class=\"col-md-6 col-sm-6\">\n" +
"                                                                                <label>Camiones</label>\n" +
"                                                                                <select name=\"camionP\" required id=\"camion\" data-placeholder=\"Selecciona Camion\" class=\"chosen-select\" tabindex=\"2\">\n" +
"                                                                                    <option value=\" " + camion_id + "\">" + camion_placas + "" + transporte_desc + "</option>\n" +
"                                                                                </select>\n" +
"                                                                            </div>\n" +
"                                                                        </div>\n" +
"                                                                    </div>   \n" +
"                                                                </div>\n" +
"                                                                <div style=\"display: none;\" id=\"paqueteria\">\n" +
"                                                                    <div  class=\"col-md-6 col-sm-6\">\n" +
"                                                                        <br>\n" +
"                                                                        <div class=\" required col-md-12 col-sm-4\"> \n" +
"                                                                            <label>Guia de embarque</label>\n" +
"                                                                            <input name=\"guiaemb\" type=\"text\" class=\"form-control\" placeholder=\"Ingrese numero de guia\">\n" +
"                                                                                                       \n" +
"                                                                        </div>\n" +
"                                                                    </div>\n" +
"                                                                    <div class=\"col-md-12 col-sm-12\">\n" +
"                                                                            <br>\n" +
"                                                                            <div  class=\"col-md-6 col-sm-6\">\n" +
"                                                                            <label>Paqueteria</label>\n" +
"                                                                            <select name=\"paqueteria\" class=\"form-control\">\n" +
"                                                                                <option value=\"1\">DHL</option>\n" +
"                                                                                <option value=\"2\">FEDEX</option>\n" +
"                                                                                <option value=\"2\">Estafeta</option>\n" +
"                                                                            </select>\n" +
"                                                                        </div>\n" +
"                                                                    </div>\n" +
"                                                                </div>\n" +
"                                                                <div class=\"row\">\n" +
"                                                                    <div class=\"col-md-6 col-sm-6\">\n" +
"                                                                        <br>\n" +
"                                                                        <label>Ayuda:</label>\n" +
"                                                                        <div class=\"col-md-12 col-sm-6\">\n" +
"                                                                                                          \n" +
"                                                                            <li> Ingrese todos los datos, son obligatorios</li>\n" +
"                                                                            <BR></BR>\n" +
"                                                                            <BR></BR>\n" +
"                                                                            <div class=\"row\">\n" +
"                                                                                <p1>Al \"generar\", la información quedara resguardada para fines de consulta</p1>\n" +
"                                                                                <p2>de la empresa.  Porfavor, valide antes de confirmar.</p2>\n" +
"                                                                            </div>\n" +
"                                                                                                          \n" +
"                                                                        </div>\n" +
"                                                                    </div>    \n" +
"                                                                </div> \n" +
"                                                                <div class=\"col-sm-12\">\n" +
"                                                                    <button type=\"submit\"   class=\"btn btn-success waves-effect waves-light pull-right\"><span class=\"btn-label\"><i class=\"fa fa-check\"></i></span>Generar</button>\n" +
"                                                                </div>\n" +
"                                                            </div>\n" +
"                                                        </div>\n" +
"                                                </div>\n" +
"                                            </div>\n" +
"                                        </div>\n" +
"                                    </div> ";
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
