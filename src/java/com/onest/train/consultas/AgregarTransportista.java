/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.onest.train.consultas;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dan_i
 */
public class AgregarTransportista extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
            String UserId = (String) ownsession.getAttribute("login.user_id_number"); //Credenciales del Usuario (user_nid)
            String cbdiv_id = (String) ownsession.getAttribute("cbdivcuenta");        // A que División pertence el cliente (cbdiv_id)

            OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
            oraDB.connect(dbData.getUser(), dbData.getPassword());

            String correoUno = request.getParameter("correoUnoJava");
            String correoDos = request.getParameter("correoDosJava");
            String correoTres = request.getParameter("correoTresJava");
            String correoCuatro = request.getParameter("correoCuatroJava");
            String telefono = request.getParameter("telefonoJava");
            String rfc = request.getParameter("rfcJava");
            String direccion = request.getParameter("direccionJava");
            String raSo = request.getParameter("raSoJava");
            String nombreTrans = request.getParameter("nombreTransJava");

            String resultado = "";
            String resultado2 = "";
            String correos = "";

            if (!correoUno.equals("")) {
                correos += correoUno;
            }

            if (!correoDos.equals("")) {
                if (!correos.equals("")) {
                    correos += "/";
                }
                correos += correoDos;
            }

            if (!correoTres.equals("")) {
                if (!correos.equals("")) {
                    correos += "/";
                }
                correos += correoTres;
            }

            if (!correoCuatro.equals("")) {
                if (!correos.equals("")) {
                    correos += "/";
                }
                correos += correoCuatro;
            }

            //System.out.println(tarea_fec_inicio + "" + tarea_fec_final);
            String insertarTransportista = " INSERT INTO tra_inb_linea_transporte "
                    + " (LTRANSPORTE_NOMBRE, "
                    + " LTRANSPORTE_DESCRIPCION, "
                    + " LTRANSPORTE_DIRECCION, "
                    + " LTRANSPORTE_RFC, "
                    + " LTRANSPORTE_TEL1) "
                    + " VALUES  "
                    + " ('" + nombreTrans + "', "
                    + " '" + raSo + "', "
                    + " '" + direccion + "', "
                    + " '" + rfc + "', "
                    + " '" + telefono + "')";
            boolean oraOut = oraDB.execute(insertarTransportista);

            String insertarCorreos = " INSERT INTO tra_correo "
                    + " (CORREO)"
                    + " VALUES "
                    + " ('" + correos + "' )";
            boolean oraOut2 = oraDB.execute(insertarCorreos);

            if (oraOut & oraOut2) {
                resultado = "true";
            } else {
                resultado = "false";

            }

            System.out.print(insertarTransportista);
            System.out.print(insertarCorreos);

            out.print(resultado);

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
        processRequest(request, response);
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
        processRequest(request, response);
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
