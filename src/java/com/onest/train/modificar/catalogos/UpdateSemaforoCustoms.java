/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.modificar.catalogos;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.consultas.ConsultasQuery;
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
import com.tacts.evidencias.inbound.CrearSemaforoCustoms;

/**
 *
 * @author luis_
 */
public class UpdateSemaforoCustoms extends HttpServlet {
    
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
        CrearSemaforoCustoms obj = new CrearSemaforoCustoms();

        //Parametros Generales
        String AgentType = request.getParameter("agenteAduanal");
        String eta_port_discharge = "";
        String semaforo = "";
        String loadTypeFinal = "";
        String shipmentId = "";
        int diasTranscurridos = 0;
        String salida = "";
        
        if(db.doDB(fac.consultarEventosCustoms(AgentType, "0", "0"))) {
            for (String[] row : db.getResultado()) {
                shipmentId = row[5];
                
                
                /*Extrar las fechas del semaforo*/
                if (db.doDB(fac.consultarFechaSemaforo(shipmentId))) {
                    for (String[] rowF : db.getResultado()) {
                        eta_port_discharge = rowF[0];                /*fecha ya registrada en sistema*/
                        diasTranscurridos = Integer.parseInt(rowF[1]);   /*dias transcurridos ya en sistema*/
                    }
                }
                
                /*fecha ya registrada en sistema vs fecha recibida del front-ed*/
                if(!eta_port_discharge.trim().equals("")){ 

                    String[] par = eta_port_discharge.split("/");
                    int month = Integer.parseInt(par[0]);
                    int day = Integer.parseInt(par[1]);
                    int year = Integer.parseInt(par[2]);    

                    /* CONSULTAR (FECHA DISCHARGE/LOAD TYPE/DÍA CONTADOR) */
                    String data = obj.dataSemaforo(month, day, year, loadTypeFinal, diasTranscurridos);

                    /* SPLIT VARIABLE DATA */
                    String[] parts = data.split("-");
                    String dia_contador = parts[0];   
                    String color_semaforo = parts[1];  
                    String fecha_inicial = parts[2];  
                    String fecha_final = parts[3];
                    String dias_total_despacho = parts[4];      

                            semaforo = " UPDATE TRA_INB_SEMAFORO SET "
                                     + " DIAS_TRANSCURRIDOS = '" + dia_contador + "', "
                                     + " DIAS_CALCULADOS = '" + dias_total_despacho + "', "
                                     + " FECHA_ACTIVACION = TO_DATE('" + eta_port_discharge + "', 'MM/DD/YYYY'), "    
                                     //+ " FECHA_TERMINO = TO_DATE('" + fecha_final + "', 'MM/DD/YYYY'), "
                                     + " ESTATUS_SEMAFORO = '" + color_semaforo + "' " 
                                     + " WHERE SHIPMENT_ID = '" + shipmentId + "' "
                                     + " AND AGENTE_ID = '" + AgentType + "' "; //idAgenteAduanal
                            boolean oraOut4 = oraDB.execute(semaforo);
                }
                
            }
        }        

         salida = "true";
     
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
            Logger.getLogger(UpdateSemaforoCustoms.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateSemaforoCustoms.class.getName()).log(Level.SEVERE, null, ex);
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