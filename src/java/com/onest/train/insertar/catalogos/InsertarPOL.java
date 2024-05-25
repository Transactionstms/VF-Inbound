/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
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
 * @author User_Windows10
 */
public class InsertarPOL extends HttpServlet {

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

                String nombre_pol = request.getParameter("nombre_pol");
                String id_pol = request.getParameter("id_pol");
                boolean oraOut1 = false;
                boolean oraOut2 = false;
                boolean salida = false;

                String existencia_pol = "SELECT ID_POL FROM TRA_INB_POL_PASO WHERE NOMBRE_POL = '" + nombre_pol + "' AND STATUS = 1";
                boolean inb_pol = oraDB.execute(existencia_pol);

                if (!inb_pol) {
                    String tra_inb_pol_paso = " INSERT INTO TRA_INB_POL_PASO (ID_POL,NOMBRE_POL,STATUS) VALUES (" + id_pol + ",'" + nombre_pol + "',1) ";
                    oraOut1 = oraDB.execute(tra_inb_pol_paso);
                }

                String existencia_pol_paso = "SELECT ID_POL FROM TRA_INB_POL WHERE NOMBRE_POL = '" + nombre_pol + "' AND STATUS = 1";
                boolean inb_pol_paso = oraDB.execute(existencia_pol_paso);

                if (!inb_pol_paso) {
                    String tra_inb_pol = " INSERT INTO TRA_INB_POL (ID_POL,NOMBRE_POL,STATUS) VALUES (" + id_pol + ",'" + nombre_pol + "',1) ";
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
            Logger.getLogger(InsertarPOL.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InsertarPOL.class.getName()).log(Level.SEVERE, null, ex);
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
