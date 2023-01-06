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
public class ConsultarImpuesto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
            String UserId = (String) ownsession.getAttribute("login.user_id_number");
            OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
            String cve = (String) ownsession.getAttribute("cbdivcuenta");

            ConsultasQuery fac = new ConsultasQuery();
            ServiceDAO dao = new ServiceDAO();

            String tipo = request.getParameter("tipo");
            String idTipoImpuesto = request.getParameter("idTipoImpuesto");
            String idImpuesto = request.getParameter("idImpuesto");
            String tipoFactor = request.getParameter("tipoFactor");
            String caramelo = "";
            String salida = "";
            String coma = ",";
            int sal = 0;
            
            /*Impuesto:*/    
            if(tipo.equals("1")){
               
                    ResultSet rs = dao.consulta(fac.consultarImpuesto(idTipoImpuesto));
                    while (rs.next()) {
                        //salida += "\"" + rs.getString(1) + "/" + rs.getString(2) + "\"" + coma;
                        //salida += "'1/IVA',";
                        sal++;
                    }
                      caramelo = salida.replaceFirst(".$","");
                      out.print("1/IVA");
                      System.out.println("Cadena: " + caramelo);
                      
                    dao.CerrarConexion();
            
            /*Tipo Factor:*/  
            }else if(tipo.equals("2")){
                
                    ResultSet rs = dao.consulta(fac.consultarTipoFactor(idTipoImpuesto, idImpuesto));
                    while (rs.next()) {
                        salida += "<option value=\"" + rs.getString(1) + "*" + rs.getString(2) + "\">" + rs.getString(2) + "</option>";
                        sal++;
                    }
                    if (sal > 0) {
                        out.println("<label class=\"custom-control-label\" style=\"font-family: Arial; font-weight: bold; color:#707375;\">Tipo factor</label>:");
                        out.println("<select class=\"form-control\" id=\"up_tipoFactor\" name=\"up_tipoFactor\" onchange=\"tipoTasa(this.value)\">");
                        out.println("<option disabled selected>-- Seleccione Tipo Factor --</option>");
                        out.print(salida);
                        out.println("</select>");
                    }
                    dao.CerrarConexion();
                    
            /*Tasa:*/         
            }else if(tipo.equals("3")){
                
                //Tasa:
                if(tipoFactor.equals("1")){
                    
                        ResultSet rs = dao.consulta(fac.consultarTipoTasa());
                        while (rs.next()) {
                            salida += "<option value=\"" + rs.getString(1) + "*" + rs.getString(2) + "\">" + rs.getString(2) + " %</option>";
                            sal++;
                        }
                        if (sal > 0) {
                            out.println("<label for=\"TasaoCuota\">Tasa</label>:");
                            out.println("<select class=\"form-control\" id=\"up_tasa\" name=\"up_tasa\" >");
                            out.println("<option disabled selected>-- Seleccione Tipo Factor --</option>");
                            out.print(salida);
                            
                            out.println("</select>");
                        }
                        
                            out.print("<input class=\"form-control\" id=\"up_Cuota\" name=\"up_Cuota\" type=\"hidden\" value=\"0\">");
                            
                            dao.CerrarConexion();
                //Cuota:    
                }else if(tipoFactor.equals("2")){
                            out.println("<label for=\"TasaoCuota\">Cuota</label>:");
                            out.print("<input class=\"form-control\" id=\"up_Cuota\" name=\"up_Cuota\" type=\"text\" value=\"0\">");
                            
                            out.print("<input class=\"form-control\" id=\"up_tasa\" name=\"up_tasa\" type=\"hidden\" value=\"0\">");
                //Exento:    
                }else{
                            out.print("<input class=\"form-control\" id=\"up_tasa\" name=\"up_tasa\" type=\"hidden\" value=\"0\">");
                            out.print("<input class=\"form-control\" id=\"up_Cuota\" name=\"up_Cuota\" type=\"hidden\" value=\"0\">");
                }
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
            Logger.getLogger(ConsultarClaveSAT.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarClaveSAT.class.getName()).log(Level.SEVERE, null, ex);
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