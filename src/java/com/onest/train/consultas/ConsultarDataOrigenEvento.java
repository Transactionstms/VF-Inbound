/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.modificar.catalogos.UpGtnShipmentId;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ConsultarDataOrigenEvento extends HttpServlet {

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

            String event = request.getParameter("event");
            String ship = request.getParameter("ship");
            String con = request.getParameter("con");
            String salida = "";
            
            //Parametros Consulta Principal
            int tipoConsulta = 0;
            
            /* Comparación de información(formulario) vs información(base de datos) */
            if (db.doDB(fac.consultarEventoFormulario(event, ship, con))) {
                for (String[] row : db.getResultado()) {
                    
                    switch (tipoConsulta) {
                        case 1:
                            salida += row[0];
                            break;
                        case 2:
                            salida += row[6];
                            break;
                        case 3:
                            salida += row[7];
                            break;
                        case 4:
                            salida += row[26];
                            break;
                        case 5:
                            salida += row[2];
                            break;
                        case 6:
                            salida += row[3];
                            break;
                        case 7:
                            salida += row[27];
                            break;
                        case 8:
                            salida += row[5];
                            break;
                        case 9:
                            salida += row[22];
                            break;
                        case 10:
                            salida += row[9];
                            break;
                        case 11:
                            salida += row[10];
                            break;
                        case 12:
                            salida += row[11];
                            break;
                        case 13:
                            salida += row[12];
                            break;
                        case 14:
                            salida += row[13];
                            break;
                        case 15:
                            salida += row[23];
                            break;
                        case 16:
                            salida += row[24];
                            break;
                        case 17:
                             salida += row[28];
                            break;
                        case 18:
                            salida += row[25];
                            break;
                        case 19:
                            salida += row[29];
                            break;
                    }
                    
                }
            }
           
            out.print(salida); 
            oraDB.close(); //cerrar conexión
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
            Logger.getLogger(ConsultarDataOrigenEvento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarDataOrigenEvento.class.getName()).log(Level.SEVERE, null, ex);
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
