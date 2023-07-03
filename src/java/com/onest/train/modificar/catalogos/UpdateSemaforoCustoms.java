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
import com.tacts.evidencias.inbound.CrearSemaforoCustoms;
import com.tacts.evidencias.inbound.CreatExcel;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

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
        Email correo = new Email();
        
        /* Obtener día actual */
        Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("MM/dd/yyyy");
        String fechaActual = formato.format(date);

        /* Parametros Generales */
        String AgentType = request.getParameter("agenteAduanal");
        String numEvento = "";
        String shipmentId = "";
        String prioridad = "";
        String loadType = "";
        int diasTranscurridos = 0;
        String estatusSemaforo = "";
        String fechaActivacion = "";
        String fechaTermino = "";
        int diasCalculados = 0;
        String colorSemaforo = "";
        String semaforo = "";
        String customs = "";
        String rutaFichero = "";
        String emails = "";
        String priority = "";
        
        /* Obtener la data/registros con activación del semaforo */
        if (db.doDB(fac.consultarEstatusSemaforo(AgentType))) {
            for (String[] row : db.getResultado()) {
                
                if(!fechaActivacion.equals("")){  /* fecha eta port discharge */
                
                    numEvento = row[0];
                    shipmentId = row[1];
                    prioridad = row[2];
                    loadType = row[3];
                    diasTranscurridos = Integer.parseInt(row[4]);
                    estatusSemaforo = row[5];
                    fechaActivacion = row[6];
                    fechaTermino = row[7];
                    diasCalculados = Integer.parseInt(row[8]);

                    /* Obtener color del semaforo conforme al día transcurrido */
                    colorSemaforo = colorSemaforo(loadType, diasTranscurridos);

                    /* Castear la fecha eta_port_discharge (Activación Semaforo) */
                    String[] par = fechaActual.split("/");
                    int month = Integer.parseInt(par[0]);
                    int day = Integer.parseInt(par[1]);
                    int year = Integer.parseInt(par[2]);

                    /* Obtener el tipo de día (habil/inhabil) */
                    LocalDate fecha = LocalDate.of(year, month, day);

                    boolean esDiaHabil = esDiaHabil(fecha);

                    if (esDiaHabil) {

                        if(!fechaTermino.equals(fecha)){

                            diasTranscurridos++; //Aumentar 1 por día. 

                            if(diasTranscurridos==diasCalculados){
                                priority = "Si";
                            }else{
                                priority = prioridad;
                            }

                            semaforo = " UPDATE TRA_INB_SEMAFORO SET "
                                     + " DIAS_TRANSCURRIDOS = '" + diasTranscurridos + "', "
                                     + " ESTATUS_SEMAFORO = '" + colorSemaforo + "' "
                                     + " WHERE SHIPMENT_ID = '" + shipmentId + "' "
                                     + " AND AGENTE_ID IN (" + AgentType + ") ";  
                            boolean oraOut1 = oraDB.execute(semaforo);

                            customs = " UPDATE TRA_INB_CUSTOMS SET "
                                    + " PRIORIDAD = '" + priority + "', "
                                    + " ESTATUS_SEMAFORO = '" + colorSemaforo + "' "
                                    + " WHERE SHIPMENT_ID = '" + shipmentId + "' "
                                    + " AND AGENTE_ADUANAL_ID IN (" + AgentType + ") ";  
                            boolean oraOut2 = oraDB.execute(customs);
                        }

                    }
                    
                }
                
            }

            String consulta = "SELECT DISTINCT AGENTE_ADUANAL_ID, CORREO FROM TRA_INB_AGENTE_ADUANAL WHERE AGENTE_ADUANAL_ID IN (" + AgentType + ") AND ESTATUS = 1 AND CBDIV_ID = 20";
            if (db.doDB(consulta)) {
                for (String[] rowE : db.getResultado()) {
                    rutaFichero = CreatExcel.crearAPartirDeArrayList(rowE[0]);
                    emails = rowE[1].replaceFirst(" ", "/");  

                    correo.alertaModificarEventos(emails,rutaFichero.trim(),rowE[0]); //Quitar esta instrucción
                    //Considerar archivo 'AlertaCustoms.java' con emisión de excel
                }
            }
            
        }
        
         oraDB.close(); //cerrar conexión
            
        }
    }
    
    public static boolean esDiaHabil(LocalDate fecha) {
        DayOfWeek diaDeLaSemana = fecha.getDayOfWeek();
        
        return diaDeLaSemana != DayOfWeek.SATURDAY && diaDeLaSemana != DayOfWeek.SUNDAY;
    }
    
    public static String colorSemaforo(String LoadType, int diaContador) {
        /* Identificar el color del Semaforo, mediante el día/contador actual */
        
        String semaforo = "";

        if (LoadType.equals("FCL")) {

            if (diaContador >= 1 && diaContador <= 5) {
                semaforo = "1";
            } else if (diaContador >= 6 && diaContador <= 7) {
                semaforo = "2";
            } else if (diaContador == 8) {
                semaforo = "3";
            }

        } else if (LoadType.equals("FCL/LCL")) {

            if (diaContador >= 1 && diaContador <= 7) {
                semaforo = "1";
            } else if (diaContador >= 8 && diaContador <= 9) {
                semaforo = "2";
            } else if (diaContador == 10) {
                semaforo = "3";
            }

        } else if (LoadType.equals("LCL")) {

            if (diaContador >= 1 && diaContador <= 7) {
                semaforo = "1";
            } else if (diaContador >= 8 && diaContador <= 9) {
                semaforo = "2";
            } else if (diaContador == 10) {
                semaforo = "3";
            }

        } else if (LoadType.equals("AIR")) {

            if (diaContador >= 1 && diaContador <= 3) {
                semaforo = "1";
            } else if (diaContador >= 4 && diaContador <= 5) {
                semaforo = "2";
            } else if (diaContador == 6) {
                semaforo = "3";
            }

        } else if (LoadType.equals("LTL")) {

            if (diaContador >= 1 && diaContador <= 8) {
                semaforo = "1";
            } else if (diaContador >= 9 && diaContador <= 10) {
                semaforo = "2";
            } else if (diaContador == 11) {
                semaforo = "3";
            }

        }
        
        return semaforo;
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