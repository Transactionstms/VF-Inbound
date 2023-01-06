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
public class ConsultarClaveSAT extends HttpServlet {

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
            String valor = request.getParameter("valor");
            String salida = " ";
            int sal = 0;
            
            /*Divisiones:*/    
            if(tipo.equals("1")){
               
                    ResultSet rs = dao.consulta(fac.consultarDivisionSAT(valor));
                    while (rs.next()) {
                        salida += "<option value=\"" + rs.getString(1) + "\">" + rs.getString(2) + "</option>";
                        sal++;
                    }
                    if (sal > 0) {
                        out.println("<select class=\"form-control\" id=\"divisionSAT\" name=\"divisionSAT\" onchange=\"search_grupos(this.value)\">");
                        out.println("<option value=\"0\" disabled selected>-- Seleccione una División --</option>");
                        out.print(salida);
                        out.println("</select>");
                    }
                    dao.CerrarConexion();
            
            /*Grupos:*/  
            }else if(tipo.equals("2")){
                
                    ResultSet rs = dao.consulta(fac.consultarGrupoSAT(valor));
                    while (rs.next()) {
                        salida += "<option value=\"" + rs.getString(1) + "\">" + rs.getString(2) + "</option>";
                        sal++;
                    }
                    if (sal > 0) {
                        out.println("<select class=\"form-control\" id=\"grupoSAT\" name=\"grupoSAT\" onchange=\"search_clases(this.value)\">");
                        out.println("<option value=\"0\" disabled selected>-- Seleccione un Grupo --</option>");
                        out.print(salida);
                        out.println("</select>");
                    }
                    dao.CerrarConexion();
            
            /*Clases:*/  
            }else if(tipo.equals("3")){
                
                    ResultSet rs = dao.consulta(fac.consultarClaseSAT(valor));
                    while (rs.next()) {
                        salida += "<option value=\"" + rs.getString(1) + "\">" + rs.getString(2) + "</option>";
                        sal++;
                    }
                    if (sal > 0) {
                        out.println("<select class=\"form-control\" id=\"claseSAT\" name=\"claseSAT\" onchange=\"search_claves(this.value)\">");
                        out.println("<option value=\"0\" disabled selected>-- Seleccione una Clase --</option>");
                        out.print(salida);
                        out.println("</select>");
                    }
                    dao.CerrarConexion();
                    
            /*Claves SAT:*/     
            }else if(tipo.equals("4")){
                
                    ResultSet rs = dao.consulta(fac.consultarClavesSAT(valor));
                    while (rs.next()) {
                        salida += "<option value=\"" + rs.getString(1) + "\">" + rs.getString(2) + "</option>";
                        sal++;
                    }
                    if (sal > 0) {
                        out.println("<select class=\"form-control\" id=\"claveSAT\" name=\"claveSAT\" onclick=\"listarClaveSAT(this.value)\">");
                        out.println("<option value=\"0\" disabled selected>-- Seleccione una Clave --</option>");
                        out.print(salida);
                        out.println("</select>");
                    }
                    dao.CerrarConexion();
                
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
