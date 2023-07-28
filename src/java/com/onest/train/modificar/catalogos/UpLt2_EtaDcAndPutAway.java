/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.modificar.catalogos;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
public class UpLt2_EtaDcAndPutAway extends HttpServlet {

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
            
            // create SimpleDateFormat object with source string date format: Tratamiento 1
            SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd"); 

            // create SimpleDateFormat object with desired date format:       Tratamiento 2
            SimpleDateFormat sdfDestination = new SimpleDateFormat("MM/dd/yyyy");
            
            try{
            
                String actual_crd = request.getParameter("actual_crd");
                String lt2 = request.getParameter("lt2");
                String date_etaDc = "";
                String date_putAway = "";
                String fecha1_etaDc = "";
                String fecha2_putAway = "";
                String salida = "";

                //Obtener fecha Actual CRD
                String[] fec1 = actual_crd.split("/");
                int month = Integer.parseInt(fec1[0]);          
                int dias = Integer.parseInt(fec1[1]);              
                int anio = Integer.parseInt(fec1[2]);  

                //Obtener días LoadType
                int maxFlete_etaDc  = Integer.parseInt(lt2)-2; 
                int maxFlete_putAway = Integer.parseInt(lt2); 

                date_etaDc = calcularRangoFechasDiasHabiles(month, dias, anio, maxFlete_etaDc);

                date_putAway = calcularRangoFechasDiasHabiles(month, dias, anio, maxFlete_putAway);
                
                Date date1 = sdfSource.parse(date_etaDc);  
                fecha1_etaDc = sdfDestination.format(date1);
                
                Date date2 = sdfSource.parse(date_putAway);  
                fecha2_putAway = sdfDestination.format(date2);

                salida = fecha1_etaDc+"@"+fecha2_putAway;

                out.print(salida); 
                oraDB.close(); //cerrar conexión
            
            }catch(Exception e){
                System.out.println(e);
            }
            
        }
    }
    
    public static String calcularRangoFechasDiasHabiles(int month, int dias, int anio, int diasHabilesLoadType) {
        
        int diasHabilesAgregar = diasHabilesLoadType;
        
        LocalDate fechaInicial = LocalDate.of(anio, month, dias);
        LocalDate fechaFinal = aumentarDiasHabiles(fechaInicial, diasHabilesAgregar);

        String daysOn = ""+fechaFinal;
        
        return daysOn;
    }
    
    public static LocalDate aumentarDiasHabiles(LocalDate fechaInicial, int diasHabilesAgregar) {
        LocalDate fechaActual = fechaInicial;
        int diasAgregados = 0;

        while (diasAgregados < diasHabilesAgregar) {
            fechaActual = fechaActual.plusDays(1);

            if (esDiaHabil(fechaActual)) {
                diasAgregados++;
            }
        }

        return fechaActual;
    }

    public static boolean esDiaHabil(LocalDate fecha) {
        // Verificar si es fin de semana (sábado o domingo)
        if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return false;
        }

        // Aquí puedes agregar más condiciones para verificar si la fecha es un día inhábil
        return true;
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
            Logger.getLogger(UpLt2_EtaDcAndPutAway.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpLt2_EtaDcAndPutAway.class.getName()).log(Level.SEVERE, null, ex);
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
