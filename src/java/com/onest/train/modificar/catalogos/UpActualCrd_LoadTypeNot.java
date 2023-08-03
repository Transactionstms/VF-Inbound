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
 * @author Desarrollo Tacts
 */
public class UpActualCrd_LoadTypeNot extends HttpServlet {

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
            SimpleDateFormat sdfSource1 = new SimpleDateFormat("yyyy-MM-dd"); 

            // create SimpleDateFormat object with desired date format:       Tratamiento 2
            SimpleDateFormat sdfDestination1 = new SimpleDateFormat("MM/dd/yyyy");
            
            
            // create SimpleDateFormat object with source string date format: Tratamiento 2
            SimpleDateFormat sdfSource2 = new SimpleDateFormat("MM/dd/yyyy"); 

            // create SimpleDateFormat object with desired date format:       Tratamiento 3
            SimpleDateFormat sdfDestination2 = new SimpleDateFormat("dd/MM/yyyy");
            
            //creat objcet with Lt2 Load Type Not
            Rule2_UpGtnLt2 obj = new Rule2_UpGtnLt2();
            
            try{
            
                String fecha_actual_crd = request.getParameter("actual_crd");
                String lt2 = request.getParameter("lt2");
                String actual_crd = "";
                String date_etaDc = "";
                String date_putAway = "";
                String fecha1_etaDc = "";
                String fecha2_putAway = "";
                String dateProvitional_1 = "";
                String dateProvitional_2 = "";
                String fecha_actual_crd2 = "";
                String salida = "";

                Date date0 = sdfSource2.parse(fecha_actual_crd);  
                actual_crd = sdfDestination2.format(date0);
                
                //Obtener días LoadType
                int maxFlete_etaDc  = Integer.parseInt(lt2); 
                int maxFlete_putAway = 2; 

                /****************************** Calcular 1er Fecha ******************************/
                if (db.doDB("select TO_CHAR(TO_DATE('"+actual_crd+"','DD/MM/YYYY')+"+maxFlete_etaDc+",'DD/MM/YYYY') from dual")) {
                    for (String[] row : db.getResultado()) {
                        dateProvitional_1 = row[0];
                    }
                }
                
                date_etaDc = obj.updateLt2LoadTypeNot(dateProvitional_1); //Consultar Fechas - Store Procedure 
                
                Date date1 = sdfSource1.parse(date_etaDc);  
                fecha1_etaDc = sdfDestination1.format(date1); //Formatear Fechas Finales 
                
                
                /****************************** Calcular 2da Fecha ******************************/
                Date date2 = sdfSource2.parse(fecha1_etaDc);  
                fecha_actual_crd2 = sdfDestination2.format(date2);
                
                if (db.doDB("select TO_CHAR(TO_DATE('"+fecha_actual_crd2+"','DD/MM/YYYY')+"+maxFlete_putAway+",'DD/MM/YYYY') from dual")) {
                    for (String[] row : db.getResultado()) {
                        dateProvitional_2 = row[0];
                    }
                }
                
                date_putAway = obj.updateLt2LoadTypeNot(dateProvitional_2); //Consultar Fechas - Store Procedure 
                
                Date date3 = sdfSource1.parse(date_putAway);  
                fecha2_putAway = sdfDestination1.format(date3); //Formatear Fechas Finales 

                /****************************** Cadena Final ******************************/
                salida = fecha1_etaDc+"@"+fecha2_putAway;

                out.print(salida); 
                oraDB.close(); //cerrar conexión
            
            }catch(Exception e){
                System.out.println(e);
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
            Logger.getLogger(UpActualCrd_LoadTypeNot.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpActualCrd_LoadTypeNot.class.getName()).log(Level.SEVERE, null, ex);
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