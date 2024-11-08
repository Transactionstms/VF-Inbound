/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.inbound;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.consultas.ConsultasQuery;
import com.tacts.evidencias.facturacion.Email;
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
 * @author grecendiz
 */
public class EnviarCorreoRDI extends HttpServlet {

      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        
        HttpSession ownsession = request.getSession();
        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
        DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        oraDB.connect(dbData.getUser(), dbData.getPassword());
        
        Email correo = new Email();
        ConsultasQuery fac = new ConsultasQuery();
        
        String UserId = (String) ownsession.getAttribute("login.user_id_number");
        String cve = (String) ownsession.getAttribute("cbdivcuenta");
        
        String agenteAduanal = request.getParameter("agenteAduanal");
        String folio = request.getParameter("folio");
        String rutaFichero = "";
        String emails = "";
 
      
String idAA="";
String nomAA="";
      //  String consulta = "SELECT DISTINCT AGENTE_ADUANAL_ID, CORREO,AGENTE_ADUANAL_NOMBRE FROM TRA_INB_AGENTE_ADUANAL WHERE AGENTE_ADUANAL_ID IN (" + agenteAduanal + ") AND ESTATUS = 1 AND CBDIV_ID = 20";
        
      
      String sql1=" select DISTINCT ad.AGENTE_ADUANAL_ID, ad.CORREO,ad.AGENTE_ADUANAL_NOMBRE from tra_inb_evento  ev \n" +
" inner join TRA_INC_GTN_TEST gtn on gtn.CONTAINER1=ev.CONTAINER1 \n" +
" inner join tra_inb_pod pod on pod.ID_POD=gtn.pod\n" +
" inner join tra_inb_agente_aduanal ad on ad.AGENTE_ADUANAL_ID=pod.AGENTE_ADUANAL_ID\n" +
" where FOLIO_RDI='"+folio+"'";
      
      
        String consulta = "SELECT DISTINCT AGENTE_ADUANAL_ID, CORREO,AGENTE_ADUANAL_NOMBRE FROM TRA_INB_AGENTE_ADUANAL WHERE AGENTE_ADUANAL_ID IN (100) AND ESTATUS = 1 AND CBDIV_ID = 20";
        
      if (db.doDB(sql1)) {
            for (String[] rowE : db.getResultado()) {
                rutaFichero = CreateExcelRdi.crearAPartirDeArrayList(rowE[0],folio);
                emails =  rowE[1];  
                idAA=rowE[0];
                nomAA=rowE[2];
                //desde rdi
                  correo.alertaModificarEventos(emails,rutaFichero.trim(),idAA,nomAA);
                
            }
        }
        
       // emails+="jlmateos@tacts.mx";
         
         String estatusInicial = "UPDATE TRA_INB_EVENTO SET FEC_ACTUALIZO = SYSDATE,ESTATUS_CORREO=1 ,ESTATUS_EVENTO=1 WHERE FOLIO_RDI = '"+folio+"'";
                    boolean oraOut2 = oraDB.execute(estatusInicial);
       
      
         
         
         out.print("Enviado");
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
            Logger.getLogger(AlertaInbound.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AlertaInbound.class.getName()).log(Level.SEVERE, null, ex);
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
