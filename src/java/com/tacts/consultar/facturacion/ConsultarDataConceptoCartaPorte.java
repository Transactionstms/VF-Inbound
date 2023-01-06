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
public class ConsultarDataConceptoCartaPorte extends HttpServlet {
    
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
        String clvreg_id = request.getParameter("clvreg_id");
        
          String listarClientes = " SELECT DISTINCT "
                                + " TPS.CLVREG_ID, "
                                + " TPS.REL_CLVSAT_ID, "
                                + " TPS.VALOR_UNITARIO, "
                                + " TUMS.CLV_UNIDAD_SAT, "
                                + " TPS.UNIDAD_SAT_ID "
                                + " FROM TRA_PRODUCTOS_SERVICIOS TPS "
                                + " INNER JOIN TRA_UNIDAD_MEDIDA_SAT TUMS ON TPS.UNIDAD_SAT_ID = TUMS.CLVU_ID "
                                + " WHERE TPS.REL_CLVSAT_ID = '" + clvreg_id + "' "
                                + " AND TPS.CBDIV_ID = '" + cve + "' ";

        ServiceDAO dao = new ServiceDAO();
        ResultSet rs = dao.consulta(listarClientes);
        
            String salida = " ";
            while (rs.next()) {

                String CLVREG_ID = rs.getString(1);
                String CODIGO = rs.getString(2);
                String VALOR_UNITARIO = rs.getString(3);
                String UNIDAD_MEDIDA_CLAVE = rs.getString(4);
                String UNIDAD_MEDIDA_ID = rs.getString(5);
                
                salida += CLVREG_ID + "*" +  CODIGO + "*" + VALOR_UNITARIO + "*" + UNIDAD_MEDIDA_CLAVE + "*" + UNIDAD_MEDIDA_ID;
            }
            dao.CerrarConexion();
            out.print(salida);
            
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
            Logger.getLogger(ConsultarDataConceptoCartaPorte.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarDataConceptoCartaPorte.class.getName()).log(Level.SEVERE, null, ex);
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
