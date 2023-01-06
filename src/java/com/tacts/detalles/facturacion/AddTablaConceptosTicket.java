/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.detalles.facturacion;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.consultas.ConsultasQuery;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AddTablaConceptosTicket extends HttpServlet {


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
        oraDB.connect(dbData.getUser(), dbData.getPassword());
        ConsultasQuery fac = new ConsultasQuery();
        try (PrintWriter out = response.getWriter()) {
        
        //Sección: General    
        String rel_clvsat_id = request.getParameter("rel_clvsat_id"); 
        String unidad_sat_clave = request.getParameter("unidad_sat_id");  //enviar id a consulta para extraer id del regisgro
        String concepto_id = request.getParameter("concepto_id");  
        String concepto_desc = request.getParameter("concepto_desc");
        String cantidad = request.getParameter("cantidad"); 
        String precio_unitario = request.getParameter("precio_unitario"); 
        String importe_descuento = request.getParameter("importe_descuento"); 
        String importe = request.getParameter("importe"); 
        String unidad_sat_id = "";
        String salida = "";
        
        //Extraer id de la clave unidad de medida sat;
        if (db.doDB(fac.idClaveUnidadSatFact(unidad_sat_clave))) {
            for (String[] rowC : db.getResultado()) {
                unidad_sat_id = rowC[0];
            }
        }

        int contador = Integer.parseInt(request.getParameter("contadorConceptos"));
        int sal = contador;
        
        //Armado de tabla
        salida += "<tr id=\"tr" + sal + "\"> "
                + " <td>" + concepto_desc + ""
                + "     <input value=\"" + concepto_id + "\" name=\"concepto_id[" + sal + "]\"  id=\"concepto_id[" + sal + "]\" type=\"hidden\" /><input value=\"" + concepto_desc + "\" name=\"concepto_desc[" + sal + "]\"  id=\"concepto_desc[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + rel_clvsat_id + "\" name=\"rel_clvsat_id[" + sal + "]\"  id=\"rel_clvsat_id[" + sal + "]\" type=\"hidden\" />"
                + "     <input value=\"" + unidad_sat_id + "\" name=\"unidad_sat_id[" + sal + "]\"  id=\"unidad_sat_id[" + sal + "]\" type=\"hidden\" />"
                + " </td> "
                + " <td>" + cantidad + "<input value=\"" + cantidad + "\" name=\"cantidad[" + sal + "]\"  id=\"cantidad[" + sal + "]\" type=\"hidden\" /></td> "
                + " <td>$ " + precio_unitario + "<input value=\"" + precio_unitario + "\" name=\"precio_unitario[" + sal + "]\"  id=\"precio_unitario[" + sal + "]\" type=\"hidden\" /></td> "
                + " <td>$ " + importe_descuento + "<input value=\"" + importe_descuento + "\" name=\"importe_descuento[" + sal + "]\"  id=\"importe_descuento[" + sal + "]\" type=\"hidden\" /></td> "
                + " <td>$ " + importe + "<input value=\"" + precio_unitario + "\" name=\"importe[" + sal + "]\"  id=\"importe[" + sal + "]\" type=\"hidden\" /></td> "
                + "</tr> ";
                
                sal++;
                
            if (sal > 0) {
                 out.print(salida);
            } else {
                out.print("No se encontraron datos");
            }
            
            oraDB.close();
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
            Logger.getLogger(AddTablaConceptosTicket.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddTablaConceptosTicket.class.getName()).log(Level.SEVERE, null, ex);
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

