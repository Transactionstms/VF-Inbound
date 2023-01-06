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
public class ConsultarFacturasPorPagar extends HttpServlet {
    
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
        
        //String idFacturas = request.getParameter("idFacturas");
        String idFacturas = "100";
        
        String id_documento = "";
        String serie = "";
        String folio = "";
        String moneda_dr = "";
        String metodo_de_pago_dr = "";
        int numParcialidad = 0;
        float saldoAnterior = 0;
        float saldoPagado = 0;
        
        float totalImporte = 0;
        float saldoPendiente = 0;
        int sal = 0;
        String salida = " ";

        ServiceDAO dao = new ServiceDAO();
        ResultSet rs = dao.consulta(fac.consultarFacturaComplemento(idFacturas, cve));
        
                     salida +="<table class=\"table\" id=\"mytable\">\n"
                            + " <thead>\n"
                            + "     <tr>\n"
                            + "         <th> </th>\n"
                            + "         <th>Serie - Folio</th>\n"
                            + "         <th>Método de pago</th>\n"
                            + "         <th>Total</th>\n"
                            + "         <th>Saldo</th>\n"
                            + "         <th>Pagos</th>\n"
                            + "         <th>Importe del pago</th>\n"
                            + "         <th> </th>\n"
                            + "     </tr>\n"
                            + " </thead>\n"
                            + " <tbody>";
       
            while (rs.next()) {
                id_documento = rs.getString(1); 
                serie = rs.getString(2); 
                folio = rs.getString(3); 
                metodo_de_pago_dr = rs.getString(4);
                saldoPendiente = Float.parseFloat(rs.getString(5));
                moneda_dr =  rs.getString(6);
                
                totalImporte += saldoPendiente;
                
                if (db.doDB(fac.consultarAbonosFacturas(serie, folio, cve))) {
                    for (String[] rowRf : db.getResultado()) {
                        numParcialidad =  Integer.parseInt(rowRf[0]) + 1;
                        saldoAnterior = Float.parseFloat(rowRf[1]);
                        saldoPagado = Float.parseFloat(rowRf[2]);
                    }
                }else{
                        numParcialidad = 1;
                        saldoAnterior = saldoPendiente;
                        saldoPagado = saldoPendiente;
                }
                
                salida += " <tr>"
                        + "    <td>"
                        + "        <a class=\"text-lg text-info\" onclick=\"delete_concepto('" + sal + "')\"><i class=\"far fa-1x fa-trash-alt\"></i></a>"
                        + "        <a class=\"me-3 text-lg text-info\" href=\"#\" role=\"button\" onclick=\"hblteditRegFacturas('" + sal + "')\"><i class=\"far fa-1x fa-edit\"></i></a>"                      
                        + "        <a id=\"btnCancelar\" style=\"display:none;\" class=\"me-3 text-lg text-info\" href=\"#\" role=\"button\" onclick=\"inhblteditRegFacturas('" + sal + "')\"><i class=\"fa fa-1x fa-times\"></i></a>" 
                        + "    </td> "
                        + "    <td>" + serie + "" + folio + ""
                        + "        <input value=\"" + id_documento + "\" name=\"id_documento[" + sal + "]\"  id=\"id_documento[" + sal + "]\" type=\"hidden\" />"
                        + "        <input value=\"" + serie + "\" name=\"serieFactOld[" + sal + "]\"  id=\"serieFactOld[" + sal + "]\" type=\"hidden\" />"
                        + "        <input value=\"" + folio + "\" name=\"folioFactOld[" + sal + "]\"  id=\"folioFactOld[" + sal + "]\" type=\"hidden\" />"
                        + "    </td> "
                        + "    <td>"
                        + "        <select class=\"form-control selectpicker col-md-5\" id=\"metodo_de_pago_dr[" + sal + "]\" name=\"metodo_de_pago_dr[" + sal + "]\" onchange=\"moneda(this.value)\" value=\"" + metodo_de_pago_dr + "\" disabled=\"true\">"
                        + "           <option value=\"PPD\" selected>PPD</option>"
                        + "           <option value=\"PUE\">PUE</option>"
                        + "        </select>"
                        + "    </td> "
                        + "    <td>"
                        + "        $ " + saldoPendiente + " " + moneda_dr + ""
                        + "        <input value=\"" + saldoPendiente + "\" name=\"saldoPendiente[" + sal + "]\"  id=\"saldoPendiente[" + sal + "]\" type=\"hidden\"/>"
                        + "    </td> "
                        + "    <td>"
                        + "        $ " + saldoAnterior + " " + moneda_dr + ""
                        + "        <input value=\"" + saldoAnterior + "\" name=\"saldoAnterior[" + sal + "]\"  id=\"saldoAnterior[" + sal + "]\" type=\"hidden\"/>"
                        + "    </td> "
                        + "    <td>"
                        + "        <p><span class=\"badge badge-primary\">" + numParcialidad + "</span></p><input value=\"" + numParcialidad + "\" name=\"numParcialidad[" + sal + "]\"  id=\"numParcialidad[" + sal + "]\" type=\"hidden\"/>"
                        + "    </td> "
                        + "    <td>"
                        + "        $ <input value=\"" + saldoPagado + "\" name=\"saldoPagado[" + sal + "]\" id=\"saldoPagado[" + sal + "]\" type=\"number\" style=\"text-align: right;\" disabled=\"true\"/>"
                        + "    </td> "
                        + "    <td align=\"left\">" + moneda_dr + ""
                        + "        <input value=\"" + moneda_dr + "\" name=\"moneda_dr[" + sal + "]\" id=\"moneda_dr[" + sal + "]\" type=\"hidden\"/>"
                        + "        <input value=\"0.00\" name=\"importeSaldoInsoluto[" + sal + "]\" id=\"importeSaldoInsoluto[" + sal + "]\" type=\"hidden\"/>"
                        + "        <input value=\"1\" name=\"equivalenciaDr[" + sal + "]\" id=\"equivalenciaDr[" + sal + "]\" type=\"hidden\"/>"
                        + "        <input value=\"02\" name=\"ObjetoImpDr[" + sal + "]\" id=\"ObjetoImpDr[" + sal + "]\" type=\"hidden\"/>"
                        + "    </td> "
                        + " </tr>";
                
                sal++;
               
            }
            
                salida += "</tbody>\n"
                        + "</table>\n"
                        + "<br>\n"
                        + "<div class=\"row\">\n"
                        + "    <div class=\"col-lg-9\"></div>\n"
                        + "    <div class=\"col-lg-3\">\n"
                        + "        <a class=\"btn btn-primary text-nowrap\" role=\"button\" style=\"float: right;\" data-bs-toggle=\"modal\" data-bs-target=\"#modalclvUnidad\"><i class=\"fa fa-plus me-2\"> </i>&nbsp;Factura</a>\n"
                        + "    </div>\n"
                        + "</div> \n"
                        + "<hr>\n"
                        + "<div class=\"row\">\n"
                        + "    <div class=\"col-lg-6\"></div>\n"
                        + "    <div class=\"col-lg-3\" align=\"right\">\n"
                        + "        <h5 style=\"color:#337ab7\"><a id=\"totalusd1\" style=\"display:none;\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"\" data-original-title=\"Es la Suma de los importes del pago\">Total: $ <%=importeFactura%> USD</a></h5>\n"
                        + "    </div>\n"
                        + "    <div class=\"col-lg-3\" align=\"right\">\n"
                        + "        <h5 style=\"color:#337ab7\"><a data-toggle=\"tooltip\" data-placement=\"left\" title=\"\" data-original-title=\"Es la Suma de los importes del pago\">Total: $ " + totalImporte + " MXN</a></h5>\n"
                        + "    </div>\n"
                        + "</div> ";

                        salida += "<input type=\"hidden\" id=\"contadorFacturasPorPagar\" name=\"contadorFacturas\" value=\"" + sal +"\">";

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
            Logger.getLogger(ConsultarFacturasPorPagar.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarFacturasPorPagar.class.getName()).log(Level.SEVERE, null, ex);
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