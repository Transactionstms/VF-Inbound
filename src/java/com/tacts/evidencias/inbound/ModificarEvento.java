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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.onest.train.modificar.catalogos.UpGtnShipmentId;

/**
 *
 * @author grecendiz
 */
public class ModificarEvento extends HttpServlet {

     
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

                String responsable = request.getParameter("responsable").trim();
                String finaldes    = request.getParameter("finaldes").trim();
                String Brand       = request.getParameter("Brand").trim();

                String sbu_name = request.getParameter("sbu_name").trim();
                String Shipment = request.getParameter("Shipment").trim();
                String Load1    = request.getParameter("Load1").trim();

                String quantity          = request.getParameter("quantity").trim();
                String pod               = request.getParameter("pod").trim();
                String est_departure_pol = request.getParameter("est_departure_pol").trim();

                String eta_port_discharge = request.getParameter("eta_port_discharge").trim();
                String max_flete          = request.getParameter("max_flete").trim();
                String eta_plus2          = request.getParameter("eta_plus2").trim();

                String eta_plus         = request.getParameter("eta_plus").trim();
                String pol              = request.getParameter("pol").trim();
                String observaciones    = request.getParameter("observaciones").trim();
                
                String actual_crd       = request.getParameter("actual_crd").trim();

                String ship      = request.getParameter("ship").trim();
                String con       = request.getParameter("con").trim();

                String bl       = request.getParameter("bl").trim();
                
                String numEventoActual = request.getParameter("numEventoActual").trim();
                String numEventoDB = request.getParameter("numEventoDB").trim();
                String updateEvento = request.getParameter("updateEvento").trim();

                /*Parametros - Comparación de información (formulario/dba)*/
                String fecha1_est_departure_pol = "";
                String fecha2_eta_port_discharge = "";
                String fecha3_eta_plus2 = "";
                String fecha4_eta_plus = "";
                String fecha5_actual_crd = "";
                String inboundNotification = "";

                String evento    = "";
                String caramelo = "";
                String emails = "";
                int contador = 0;
                
                if(updateEvento.equals("1")){ /*Número de Evento Acualizado*/
                    //update evento evtn
                    String inbEvento = "UPDATE TRA_INB_EVENTO SET ID_EVENTO = '"+numEventoActual+"' WHERE ID_EVENTO = '"+numEventoDB+"' ";
                    boolean oraout1=db.doDB(inbEvento);
                    
                    if(oraout1){evento = numEventoActual;}
                    
                }else{
                    evento = numEventoDB;
                }

                        /* Comparación de información(formulario) vs información(base de datos) */
                        if (db.doDB(fac.consultarEventoFormulario(evento, ship, con))) {
                            for (String[] row : db.getResultado()) {
                                
                                if(updateEvento.equals("1")){
                                   caramelo += " Número de evento: " + evento + "/";
                                   contador++;
                                }
                                
                                if(!row[6].trim().equals(con)){
                                   
                                    if (db.doDB(fac.consultarDivisionFormulario(con))) {
                                        for (String[] rowc : db.getResultado()) {
                                          caramelo += " Container*: " + rowc[0] + "/";
                                        }
                                    }
                                    
                                   contador++;
                                }
                                
                                if(!row[7].trim().equals(bl)){
                                   caramelo += " BL/ AWB/ PRO: " + bl + "/";
                                   contador++;
                                }
                                
                                if(!row[26].trim().equals(responsable)){
                                   caramelo += " Responsable: " + responsable + "/";
                                   contador++;
                                }
                                
                                if(!row[2].trim().equals(finaldes)){
                                   caramelo += " Final Destination (Shipment): " + finaldes + "/";
                                   contador++;
                                }
                                
                                if(!row[3].trim().equals(Brand)){
                                   caramelo += " Brand-Division: " + Brand + "/";
                                   contador++;
                                }
                                
                                if(!row[27].trim().equals(sbu_name)){
                                   caramelo += " Division: " + sbu_name + "/";
                                   contador++;
                                }
                                
                                if(!row[5].trim().equals(Shipment)){
                                   caramelo += " Shipment ID: " + Shipment + "/";
                                   contador++;
                                }
                                
                                if(!row[22].trim().equals(Load1)){
                                   caramelo += " Load Type: " + Load1 + "/";
                                   contador++;
                                }
                                
                                if(!row[9].trim().equals(quantity)){  //revisar
                                   caramelo += " Quantity: " + quantity + "/";
                                   contador++;
                                }
                                
                                if(!row[10].trim().equals(pod)){
                                   caramelo += " POD: " + pod + "/";
                                   contador++;
                                }
                                
                                if(!row[11].trim().equals("")){ 
                                    Date date1 = sdfSource.parse(row[11]);  
                                    fecha1_est_departure_pol = sdfDestination.format(date1);
                               
                                    if(!fecha1_est_departure_pol.equals(est_departure_pol)){
                                       caramelo += " Est. Departure from POL: " + est_departure_pol + "/";
                                       contador++;
                                    }
                                }
                                
                                if(!row[12].trim().equals("")){
                                    Date date2 = sdfSource.parse(row[12]); 
                                    fecha2_eta_port_discharge = sdfDestination.format(date2);
                                
                                    if(!fecha2_eta_port_discharge.equals(eta_port_discharge)){
                                       caramelo += " ETA REAL PORT: " + eta_port_discharge + "/";
                                       contador++;
                                    }
                                }
                                
                                if(!row[13].trim().equals(max_flete)){
                                   caramelo += " LT2: " + max_flete + "/";
                                   contador++;
                                }
                                
                                if(!row[23].trim().equals("")){
                                    Date date3 = sdfSource.parse(row[23]); 
                                    fecha3_eta_plus2 = sdfDestination.format(date3);

                                    if(!fecha3_eta_plus2.equals(eta_plus2)){                         
                                       caramelo += " ETA DC: " + eta_plus2 + "/";
                                       contador++;
                                    }
                                }    
                                 
                                if(!row[24].trim().equals("")){    
                                    Date date4 = sdfSource.parse(row[24]); 
                                    fecha4_eta_plus = sdfDestination.format(date4);
                                
                                    if(!fecha4_eta_plus.equals(eta_plus)){
                                       caramelo += " INDC +2 Days Put Away: " + eta_plus + "/";    
                                       contador++;
                                    }
                                } 
                            
                                if(!row[28].trim().equals(pol)){
                                   caramelo += " POL: " + pol + "/";
                                   contador++;
                                } 
                                
                                if(!row[25].trim().equals(observaciones)){
                                   caramelo += " Observaciones: " + observaciones + "/";
                                   contador++;
                                }
                                
                                if(!row[29].trim().equals("")){
                                    Date date5 = sdfSource.parse(row[29]); 
                                    fecha5_actual_crd = sdfDestination.format(date5);
                                
                                    if(!fecha5_actual_crd.equals(actual_crd)){
                                       caramelo += " Actual CRD: " + actual_crd + "/";
                                       contador++;
                                    }
                                }
                                
                                inboundNotification = row[14];

                            }
                        }

                        if(contador>0){

                            /* Emisión de Alerta - Actualización de Campos: */
                            String consulta = "SELECT DISTINCT CORREO FROM TRA_INB_AGENTE_ADUANAL WHERE AGENTE_ADUANAL_ID = 4005 AND ESTATUS IN (1)";
                            if (db.doDB(consulta)) {
                                for (String[] rowE : db.getResultado()) {
                                    emails = rowE[0].replaceFirst(" ", "/");  
                                }
                            }

                            correo.alertaModificarEventosFormulario(emails, evento, con, ship, inboundNotification, caramelo); 

                        }
                        

                 String sqlGtn= " update tra_inc_gtn_test " +
                                " set  " +
                                " final_destination = '"+finaldes+"', " +
                                " brand_division='"+Brand+"', " +
                                " sbu_name='"+sbu_name+"', " +
                                " shipment_id='"+Shipment+"', " +
                                " load_type='"+Load1+"', " +
                                " LOAD_TYPE_FINAL='"+Load1+"', " +
                                " quantity='"+quantity+"', " +
                                " pod='"+pod+"', " +
                                " max_flete='"+max_flete+"', ";
                 
                            if(!est_departure_pol.trim().equals("")){ 
                       sqlGtn+= " est_departure_pol =to_date('"+est_departure_pol+"','MM/DD/YY'), ";
                            }  
                            
                            if(!eta_port_discharge.trim().equals("")){ 
                       sqlGtn+= " eta_port_discharge=to_date('"+eta_port_discharge+"','MM/DD/YY'), ";
                            } 
                       
                            if(!eta_plus2.trim().equals("")){ 
                       sqlGtn+= " eta_plus2=to_date('"+eta_plus2+"', 'MM/DD/YY'), ";
                            } 
                            
                            if(!eta_plus.trim().equals("")){ 
                       sqlGtn+= " eta_plus=to_date('"+eta_plus+"','MM/DD/YY'), " ;
                            } 
                       
                            if(!actual_crd.trim().equals("")){ 
                       sqlGtn+= " ACTUAL_CRD =to_date('"+actual_crd+"','MM/DD/YY'), ";
                            }    
                            
                       sqlGtn+= " pol='"+pol+"', " +     
                                " bl_awb_pro='"+bl+"' " +
                                " where container1='"+con+"' " +
                                " and shipment_id='"+ship+"'";
                boolean insert=db.doDB(sqlGtn);
                
                 String sqlEve =" update tra_inb_evento " +
                                " set  " +
                                " USER_NID ="+responsable+", " +
                                " observaciones='"+observaciones+"' " +
                                " where ID_EVENTO='"+evento+"' ";
                 boolean update=db.doDB(sqlEve);
                
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
