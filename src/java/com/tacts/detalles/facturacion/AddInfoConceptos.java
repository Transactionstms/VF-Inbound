/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.detalles.facturacion;

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

/**
 *
 * @author luis_
 */
public class AddInfoConceptos extends HttpServlet {


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
        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
        DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        String cve = (String) ownsession.getAttribute("cbdivcuenta");
        String UserId = (String) ownsession.getAttribute("login.user_id_number");
        
        try (PrintWriter out = response.getWriter()) {
        
        String concepto_id = request.getParameter("concepto_id");  
        String up_desc = request.getParameter("up_desc");
        String up_cantidad = request.getParameter("up_cantidad");
        String up_noIdentificacion = request.getParameter("up_noIdentificacion");
        String up_precioUnitario = request.getParameter("up_precioUnitario");
        String up_claveProdServ = request.getParameter("up_claveProdServ");
        String up_claveUnidad= request.getParameter("up_claveUnidad");
        String up_descuento = request.getParameter("up_descuento");
        String up_unidadMedida = request.getParameter("up_unidadMedida");
        String up_tipoImpuesto = request.getParameter("up_tipoImpuesto");
        String up_base = request.getParameter("up_base");
        String up_impuesto = request.getParameter("up_impuesto");
        String up_tipoFactor = request.getParameter("up_tipoFactor");
        String up_tasaCuota = request.getParameter("up_tasaCuota");
        String up_cuentaPredial = request.getParameter("up_cuentaPredial");
        String up_numeroPedimento = request.getParameter("up_numeroPedimento");
        String upParte_desc = request.getParameter("upParte_desc");
        String upParte_Cantidad = request.getParameter("upParte_Cantidad");
        String upParte_precioUnitario = request.getParameter("upParte_precioUnitario");
        String upParte_ClaveProdServ = request.getParameter("upParte_ClaveProdServ");
        String upParte_unidad = request.getParameter("upParte_unidad");
        String upParte_NoIdentificacion = request.getParameter("upParte_NoIdentificacion");  
                 
        int contador = Integer.parseInt(request.getParameter("contadorConceptos"));
        
        String salida = "";
        int sal = contador;
        
        //Registrar la info necesaria para poder armar el pdf por medio de una consulta
        
        //Armado de tabla
        salida += "<tr id=\"tr" + sal + "\"> "
                + " <td>"
                + "     <input value=\"" + concepto_id + "\" name=\"concepto_id[" + sal + "]\"  id=\"concepto_id[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + up_desc + "\" name=\"tc_desc[" + sal + "]\"  id=\"tc_desc[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + up_cantidad + "\" name=\"tc_cantidad[" + sal + "]\"  id=\"tc_cantidad[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + up_noIdentificacion + "\" name=\"tc_noIdentificacion[" + sal + "]\"  id=\"tc_noIdentificacion[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + up_precioUnitario + "\" name=\"tc_precioUnitario[" + sal + "]\"  id=\"tc_precioUnitario[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + up_claveProdServ + "\" name=\"tc_claveProdServ[" + sal + "]\"  id=\"tc_claveProdServ[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + up_claveUnidad + "\" name=\"tc_claveUnidad[" + sal + "]\"  id=\"tc_claveUnidad[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + up_descuento + "\" name=\"tc_descuento[" + sal + "]\"  id=\"tc_descuento[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + up_unidadMedida + "\" name=\"tc_unidadMedida[" + sal + "]\"  id=\"tc_unidadMedida[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + up_tipoImpuesto + "\" name=\"tc_tipoImpuesto[" + sal + "]\"  id=\"tc_tipoImpuesto[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + up_base + "\" name=\"tc_base[" + sal + "]\"  id=\"tc_base[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + up_impuesto + "\" name=\"tc_impuesto[" + sal + "]\"  id=\"tc_impuesto[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + up_tipoFactor + "\" name=\"tc_tipoFactor[" + sal + "]\"  id=\"tc_tipoFactor[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + up_tasaCuota + "\" name=\"tc_tasaCuota[" + sal + "]\"  id=\"tc_tasaCuota[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + up_cuentaPredial + "\" name=\"tc_cuentaPredial[" + sal + "]\"  id=\"tc_cuentaPredial[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + up_numeroPedimento + "\" name=\"tc_numeroPedimento[" + sal + "]\"  id=\"tc_numeroPedimento[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + upParte_desc + "\" name=\"tcParte_desc[" + sal + "]\"  id=\"tcParte_desc[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + upParte_Cantidad + "\" name=\"tcParte_Cantidad[" + sal + "]\"  id=\"tcParte_Cantidad[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + upParte_precioUnitario + "\" name=\"tcParte_precioUnitario[" + sal + "]\"  id=\"tcParte_precioUnitario[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + upParte_ClaveProdServ + "\" name=\"tcParte_ClaveProdServ[" + sal + "]\"  id=\"tcParte_ClaveProdServ[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + upParte_unidad + "\" name=\"tcParte_unidad[" + sal + "]\"  id=\"tcParte_unidad[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + upParte_NoIdentificacion + "\" name=\"tcParte_NoIdentificacion[" + sal + "]\"  id=\"tcParte_NoIdentificacion[" + sal + "]\" type=\"hidden\" />"
                //+ "     <input value=\"" + upParte_infoAduanera + "\" name=\"tcParte_infoAduanera[" + sal + "]\"  id=\"tcParte_infoAduanera[" + sal + "]\" type=\"hidden\" />"
                + " </td>"
                + "</tr> ";

                sal++;
                
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
            Logger.getLogger(AddTablaConceptos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddTablaConceptos.class.getName()).log(Level.SEVERE, null, ex);
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