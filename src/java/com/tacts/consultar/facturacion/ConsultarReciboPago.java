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
public class ConsultarReciboPago extends HttpServlet {
    
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
        
        String idCliente = request.getParameter("idCliente");
        String clvfact_id = "";
        String cliente_descripcion = "";
        String fecha_emision = ""; 
        String serie_folio = ""; 
        String alias_metodo = ""; 
        String moneda_id = "";
        String ruta = "";
        
        float totalGloblal = 0;
        float cantidadAbono = 0;
        float saldo = 0;
        
        ServiceDAO dao = new ServiceDAO();
        ResultSet rs = dao.consulta(fac.consultarFacturasComplemento(idCliente, cve));
        int sal = 0;
       
            String salida = " ";
            while (rs.next()) {
                
                sal++;
                
                clvfact_id = rs.getString(1); 
                cliente_descripcion = rs.getString(2); 
                fecha_emision = rs.getString(3);
                serie_folio = rs.getString(4) + "" + rs.getString(5);
                alias_metodo = rs.getString(6);
                moneda_id = rs.getString(7);
                totalGloblal = Float.parseFloat(rs.getString(8));
                ruta = rs.getString(9);
                        
                    if(alias_metodo.equals("PPD")){ //Plan de pagos diferidos
                        if (db.doDB(fac.searchAbonosFacturas(rs.getString(4), rs.getString(5), cve))) { //serie, folio
                            for (String[] rowRf : db.getResultado()) { cantidadAbono += Float.parseFloat(rowRf[0]); }
                        }else{
                            cantidadAbono = 0;
                        }
                    }else{ //Pago en una sola exhibición
                            cantidadAbono = 0;
                    }
                        
                    saldo = totalGloblal - cantidadAbono;
                        
                salida += " <tr> "
                        + "    <td width=\"10%\"> " 
                        + "        <i class=\"fa fa-file fa-lg fa-fw btn btn-default btn-rounded\" onclick=\"viewVisor('" + serie_folio + "')\"></i> &nbsp;"
                        + "        <input type=\"checkbox\" autocomplete=\"off\" class=\"icheckbox_square-green\" name=\"checkFactura\" id=\"checkFactura[" + sal + "]\" value=\"" +  clvfact_id + "\" onclick=\"seleccionar(" + sal + ")\">"
                        + "    </td> "
                        + "    <td width=\"30%\"><font size=2>" + cliente_descripcion + "</font></td> "
                        + "    <td width=\"10%\"><font size=2>" + fecha_emision + "</font></td> "
                        + "    <td width=\"10%\"><font size=2>" + serie_folio + "</font></td> "
                        + "    <td width=\"5%\"><font size=2>" + alias_metodo + "</font></td> "
                        + "    <td width=\"10%\"><font size=1>$ " + totalGloblal + "</font></td> "
                        + "    <td width=\"10%\"><font size=1>$ " + cantidadAbono + "</font></td> "
                        + "    <td width=\"10%\"><font size=1>$ " + saldo + "</font></td> "
                        + "    <td width=\"5%\"><font size=2>" + moneda_id + "</font></td> "
                        + " </tr>";
               
            }
                salida += "<input type=\"hidden\" id=\"contadorFacturas\" name=\"contadorFacturas\" value=\"" + sal +"\">";
            
            dao.CerrarConexion();

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
            Logger.getLogger(ConsultarReciboPago.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarReciboPago.class.getName()).log(Level.SEVERE, null, ex);
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