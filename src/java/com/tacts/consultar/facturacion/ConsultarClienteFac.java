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
public class ConsultarClienteFac extends HttpServlet {

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

            String clienteId = request.getParameter("clienteId");
            String salida = "";
            String email_1 = "";
            String email_2 = "";
            String email_3 = "";
            String coma = ",";
            String caramelo = "";
            
            ResultSet rs = dao.consulta(fac.consultarSusClientes(clienteId, cve));

            while (rs.next()) {
                
                if(rs.getString(7) != null){
                    email_1 = rs.getString(7) + " ";
                }else{
                    email_1 = "" + coma; 
                }
                
                if(rs.getString(8) != null){
                    email_2 = rs.getString(8) + " ";
                }else{
                    email_2 = "" + coma;
                }
                
                if(rs.getString(9) != null){
                    email_3 = rs.getString(9);
                }else{
                    email_3 = "" + coma;
                }
                
                //Se quitan correos vacíos(',')
                caramelo = email_1.replaceAll(",","") + email_2.replaceAll(",","") + email_3.replaceAll(",","");
                
                salida = rs.getString(1) + "*" + rs.getString(2) + "*" + rs.getString(3) + "*" + rs.getString(4)+ "*" + rs.getString(5) + "*" + rs.getString(6) + "*" + caramelo;
            }

            out.print(salida);  /*id || rfc || uso_cfdi || regimen_id || metodo_id || forma_id || correo1 || correo2 || correo3*/
            System.out.println("correos:" + salida);
            dao.CerrarConexion();
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
            Logger.getLogger(ConsultarClienteFac.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarClienteFac.class.getName()).log(Level.SEVERE, null, ex);
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
