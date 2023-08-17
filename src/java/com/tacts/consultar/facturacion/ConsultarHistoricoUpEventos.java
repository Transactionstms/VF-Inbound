/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.consultar.facturacion;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.consultas.ConsultasQuery;
import com.onest.train.modificar.catalogos.UpGtnShipmentId;
import com.tacts.evidencias.facturacion.Email;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Desarrollo Tacts
 */
public class ConsultarHistoricoUpEventos extends HttpServlet {

     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          
        HttpSession ownsession = request.getSession();
        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
        DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        oraDB.connect(dbData.getUser(), dbData.getPassword());

        Email correo = new Email();
        ConsultasQuery fac = new ConsultasQuery();
        
         try{
             
                // create object whit store procedure 
                UpGtnShipmentId obj = new UpGtnShipmentId();
             
                // create SimpleDateFormat object with source string date format: Tratamiento 1
                SimpleDateFormat sdfSource = new SimpleDateFormat("MM/dd/yy"); 

                // create SimpleDateFormat object with desired date format:       Tratamiento 2
                SimpleDateFormat sdfDestination = new SimpleDateFormat("MM/dd/yy");
                
                //Parametros Principales
                String evento = request.getParameter("evento").trim();
                String shipmentId = request.getParameter("shipmentId").trim();
                String container = request.getParameter("container").trim();
                
                //Parametros Comparación
                String responsable = "";
                String finaldes    = "";
                String Brand       = "";
                String sbu_name = "";
                String Shipment = "";
                String Load1    = "";
                String quantity          = "";
                String pod               = "";
                String est_departure_pol = "";
                String eta_port_discharge = "";
                String max_flete          = "";
                String eta_plus2          = "";
                String eta_plus         = "";
                String pol              = "";
                String observaciones    = "";
                String actual_crd       = "";
                String ship      = "";
                String con       = "";
                String bl       = "";
                
                /*Parametros - Comparación de información (formulario/dba)*/
                String fecha1_est_departure_pol = "";
                String fecha2_eta_port_discharge = "";
                String fecha3_eta_plus2 = "";
                String fecha4_eta_plus = "";
                String fecha5_actual_crd = "";

                //String evento    = "";
                String caramelo = "";
                String emails = "";
                int contador = 0;
                String salida = "";


                        /* Comparación de información(formulario) vs información(base de datos) */
                        if (db.doDB(fac.consultarEventoFormulario(evento, ship, con))) {
                            for (String[] row : db.getResultado()) {
                                
                                if(!row[6].trim().equals(con)){
                                   caramelo += row[6]; 
                                   contador++;
                                }
                                
                                if(!row[7].trim().equals(bl)){
                                   caramelo += row[7];
                                   contador++;
                                }
                                
                                if(!row[26].trim().equals(responsable)){
                                   caramelo += row[26];
                                   contador++;
                                }
                                
                                if(!row[2].trim().equals(finaldes)){
                                   caramelo += row[2];
                                   contador++;
                                }
                                
                                if(!row[3].trim().equals(Brand)){
                                   caramelo += row[3];
                                   contador++;
                                }
                                
                                if(!row[27].trim().equals(sbu_name)){
                                   caramelo += row[27];
                                   contador++;
                                }
                                
                                if(!row[5].trim().equals(Shipment)){
                                   caramelo += row[5];
                                   contador++;
                                }
                                
                                if(!row[22].trim().equals(Load1)){
                                   caramelo += row[22];
                                   contador++;
                                }
                                
                                if(!row[9].trim().equals(quantity)){  //revisar
                                   caramelo += row[9];
                                   contador++;
                                }
                                
                                if(!row[10].trim().equals(pod)){
                                   caramelo += row[10];
                                   contador++;
                                }
                                
                                if(!row[11].trim().equals("")){ 
                                    Date date1 = sdfSource.parse(row[11]);  
                                    fecha1_est_departure_pol = sdfDestination.format(date1);
                               
                                    if(!fecha1_est_departure_pol.equals(est_departure_pol)){
                                       caramelo += row[11];
                                       contador++;
                                    }
                                }
                                
                                if(!row[12].trim().equals("")){
                                    Date date2 = sdfSource.parse(row[12]); 
                                    fecha2_eta_port_discharge = sdfDestination.format(date2);
                                
                                    if(!fecha2_eta_port_discharge.equals(eta_port_discharge)){
                                       caramelo += row[12];
                                       contador++;
                                    }
                                }
                                
                                if(!row[13].trim().equals(max_flete)){
                                   caramelo += row[13];
                                   contador++;
                                }
                                
                                if(!row[23].trim().equals("")){
                                    Date date3 = sdfSource.parse(row[23]); 
                                    fecha3_eta_plus2 = sdfDestination.format(date3);

                                    if(!fecha3_eta_plus2.equals(eta_plus2)){                         
                                       caramelo += row[23];
                                       contador++;
                                    }
                                }    
                                 
                                if(!row[24].trim().equals("")){    
                                    Date date4 = sdfSource.parse(row[24]); 
                                    fecha4_eta_plus = sdfDestination.format(date4);
                                
                                    if(!fecha4_eta_plus.equals(eta_plus)){
                                       caramelo += row[24];    
                                       contador++;
                                    }
                                } 
                            
                                if(!row[28].trim().equals(pol)){
                                   caramelo += row[28];
                                   contador++;
                                } 
                                
                                if(!row[25].trim().equals(observaciones)){
                                   caramelo += row[25];
                                   contador++;
                                }
                                
                                if(!row[29].trim().equals("")){
                                    Date date5 = sdfSource.parse(row[29]); 
                                    fecha5_actual_crd = sdfDestination.format(date5);
                                
                                    if(!fecha5_actual_crd.equals(actual_crd)){
                                       caramelo += row[29];
                                       contador++;
                                    }
                                }

                            }
                        }

                out.println(salida);
                
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
