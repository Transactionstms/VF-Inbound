/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;

import com.dao.ServiceDAO;
import com.onest.oracle.OracleDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import javax.servlet.http.*;
import com.onest.oracle.*;


/**
 *
 * @author Luis Mateos
 */
public class Consulta_BOL_CARTONES extends HttpServlet {

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
    try (PrintWriter out = response.getWriter()) {
        HttpSession ownsession = request.getSession();
        DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
        Integer user_nid = Integer.valueOf(ownsession.getAttribute("login.user_id_number").toString());//ID de Usuario
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        OracleDB oraDB1 = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        String Div = (String) ownsession.getAttribute("cbdivcuenta");
        String bo = (String) ownsession.getAttribute("bodega.id");

   
        String Carton = request.getParameter("Carton"); 

        //Llenar Información en tabla
        String MostrarDatos = " SELECT DISTINCT                                                  "
                + " ODS.DOCTO_PROGRAMACION,                                                      "
                + " UPPER(AE.EDO_DESC),                                                          "
                + " NVL(TO_CHAR(ODS.DOCTO_FEC_PROGRAMACION),'SIN INICIAR EL PROCESO EN TIENDA'), "
                + " TC.TRA_CAJA,                                                                 "
                + " UPPER(APE.EDO_DESC),                                                         "
                + " NVL(TO_CHAR(TC.FECHA_INGRESO_TIENDA),'SIN RECIBIR EN TIENDA'),               "
                + " NVL(TC.INCIDENCIAS_OBS,'SIN INCIDENCIA')                                     "
                + " FROM                                                                         "
                + " ONTMS_DOCTO_SAL ODS                                                          "
                + " INNER JOIN TRA_CAJAS TC ON ODS.DOCTO_REFERENCIA = TC.TRA_CAJA_PEDIDO         "
                + " INNER JOIN APP_ESTADO AE ON  ODS.DOCTO_ESTADO_ID = AE.EDO_VALOR              "
                + " INNER JOIN APP_ESTADO APE ON TC.ESTADO_ID = APE.EDO_VALOR                    "
                + " WHERE                                                                        "
                + " ODS.DOCTO_PROGRAMACION='" + Carton + "'                                      "
                + " OR                                                                           "
                + " TC.TRA_CAJA='" + Carton + "'                                                 "
                + " AND AE.EDO_TABLA='ONTMS_DOCTO_SAL'                                           "
                + " AND APE.EDO_TABLA='TRA_CAJAS'                                                ";

        ServiceDAO dao = new ServiceDAO();
        ResultSet rs = dao.consulta(MostrarDatos);
        int sal = 0;
            String salida = "\n";
            while (rs.next()) {

                // Parametros generados mediante la consulta
                String BOL = rs.getString(1);
                String BOL_ESTATUS = rs.getString(2);
                String BOL_FECHA = rs.getString(3);
                String CARTON = rs.getString(4);
                String CARTON_ESTATUS = rs.getString(5);
                String CARTON_FECHA = rs.getString(6);
                String INCIDENCIA = rs.getString(7);

                salida += " <tr id=\"tr\" + sal + \"\"> "
                        + " <td name=\"BOL\" id=\"BOL\" align=\"center\"><center>" + BOL + "</center></td> "
                        + " <td name=\"BOL_ESTATUS\" id=\"BOL_ESTATUS\" align=\"center\"><center>" + BOL_ESTATUS + "</center></td> "
                        + " <td name=\"BOL_FECHA\" id=\"BOL_FECHA\" align=\"center\"><center>" + BOL_FECHA + "</center></td> "
                        + " <td name=\"CARTON\" id=\"CARTON\" align=\"center\"><center>" + CARTON + "</center></td> "
                        + " <td name=\"CARTON_ESTATUS\" id=\"CARTON_ESTATUS\" align=\"center\" colspan=\"3\"><center>" + CARTON_ESTATUS + "</center></td> "
                        + " <td name=\"CARTON_FECHA\" id=\"CARTON_FECHA\" align=\"center\"><center>" + CARTON_FECHA + "</center></td> "
                        + " <td name=\"INCIDENCIA\" id=\"INCIDENCIA\" align=\"center\"><center>" + INCIDENCIA + "</center></td> "
                        + " </tr> ";
                ;
                sal++;
            }
            dao.CerrarConexion();
            //salida+="                                </tbody>";
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
