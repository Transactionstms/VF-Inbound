/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.insertar.catalogos;

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
 * @author HP
 */
public class InsertarPOD extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
            String UserId = (String) ownsession.getAttribute("login.user_id_number");
            OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
            oraDB.connect(dbData.getUser(), dbData.getPassword());

            try {

                String nombre_pod = request.getParameter("nombre_pod");
                String id_pod = request.getParameter("id_pod");
                String agente_aduanal_id = request.getParameter("agente_aduanal_id");
                boolean oraOut1 = false;
                boolean oraOut2 = false;
                boolean salida = false;

                String existencia_pol = "SELECT ID_POD FROM TRA_INB_POD_PASO WHERE NOMBRE_POD = '" + nombre_pod + "' AND STATUS = 1";
                boolean inb_pol = oraDB.execute(existencia_pol);

                if (!inb_pol) {
                    String tra_inb_pol_paso = " INSERT INTO TRA_INB_POD_PASO (ID_POD,NOMBRE_POD,STATUS, AGENTE_ADUANAL_ID) VALUES (" + id_pod + ",'" + nombre_pod + "',1, "+agente_aduanal_id+") ";
                    oraOut1 = oraDB.execute(tra_inb_pol_paso);
                }

                String existencia_pol_paso = "SELECT ID_POD FROM TRA_INB_POD WHERE NOMBRE_POD = '" + nombre_pod + "' AND STATUS = 1";
                boolean inb_pol_paso = oraDB.execute(existencia_pol_paso);

                if (!inb_pol_paso) {
                    String tra_inb_pol = " INSERT INTO TRA_INB_POD (ID_POD,NOMBRE_POD,STATUS, AGENTE_ADUANAL_ID) VALUES (" + id_pod + ",'" + nombre_pod + "',1, "+agente_aduanal_id+") ";
                    oraOut2 = oraDB.execute(tra_inb_pol);
                }

                if (oraOut1 || oraOut2) {
                    salida = true;
                } else {
                    salida = false;
                }

                out.print(salida);
                oraDB.close();

            } catch (Exception e) {
                out.println("Error " + e.toString());
            } finally {
                oraDB.close();
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
            Logger.getLogger(InsertarPOD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InsertarPOD.class.getName()).log(Level.SEVERE, null, ex);
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
