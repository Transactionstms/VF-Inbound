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
                SimpleDateFormat sdfDestination = new SimpleDateFormat("MM/dd/yyyy");
                
                /***********************************************************************/
                String numEventoActual = request.getParameter("numEventoActual");
                String ShipmentActual = request.getParameter("ShipmentActual");
                String containerActual       = request.getParameter("containerActual");
                /***********************************************************************/
                String numEventoOld = request.getParameter("numEventoOld");
                String shipmenIdOld = request.getParameter("shipmenIdOld");
                String containerOld = request.getParameter("containerOld");
                /***********************************************************************/
                
                String responsable = request.getParameter("responsable");
                String finaldes    = request.getParameter("finaldes");
                String Brand       = request.getParameter("Brand");
                String sbu_name = request.getParameter("sbu_name");
                String Load1    = request.getParameter("Load1");
                int quantity            = Integer.parseInt(request.getParameter("quantity"));
                String pod               = request.getParameter("pod");
                String est_departure_pol = request.getParameter("est_departure_pol");
                String eta_port_discharge = request.getParameter("eta_port_discharge");
                String max_flete          = request.getParameter("max_flete");
                String eta_plus2          = request.getParameter("eta_plus2");
                String eta_plus         = request.getParameter("eta_plus");
                String pol              = request.getParameter("pol");
                String observacionActual    = request.getParameter("observaciones");
                String actual_crd       = request.getParameter("actual_crd");
                //String numEventoDB = request.getParameter("numEventoDB");
                String bl       = request.getParameter("bl");

                String reg1   = request.getParameter("reg1");
                String reg2   = request.getParameter("reg2");
                String reg3   = request.getParameter("reg3");


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
                String sqlEve = "";
                String sqlGtn = "";
                String observacionOld = "";
                int valorNewQuantity = 0;
                
                boolean salida = false;
                boolean updateGtn = false;
                boolean updateObservaciones = false;
                
                int contador = 0;
                
                /*Si el núm de evento que se recibe es diferente al que esta en la d.b, se reemplaza solo ese dato, sin afectar lo demás.*/
                if(!numEventoOld.equals(numEventoActual)){ 
    /*RULE #1*/                
                    String inbEvento = "UPDATE TRA_INB_EVENTO SET ID_EVENTO = '"+numEventoActual+"' WHERE ID_EVENTO = '"+numEventoOld+"' ";
                    boolean oraout1=db.doDB(inbEvento);   /*Parametro principal(1)*/
                    
                    if(oraout1){evento = numEventoActual;}
                    
                }else{
                    evento = numEventoOld;
                }
                
                
                
                
                

                        /* Comparación de información(formulario) vs información(base de datos) */
                        if (db.doDB(fac.consultarEventoFormulario(evento, shipmenIdOld, containerOld))) {
                            System.out.println("fac.consultarEventoFormulario(evento, shipmenIdOld, containerOld)"+fac.consultarEventoFormulario(evento, shipmenIdOld, containerOld));
                            for (String[] row : db.getResultado()) {
                                System.out.println("row[6]"+row[6]);
                                if(!numEventoOld.equals(evento)){   //ok
                                   caramelo += " Número de evento: " + evento + "@";
                                   contador++;
                                }
    /*RULE #2*/                            
                                if(!row[6].equals(containerActual)){    
                                    caramelo += " Container*: " + containerActual + "@";
                                    contador++;
                                }
                                
                                System.out.println("Container Anterior:" + row[6]);
                                System.out.println("Container Actual:" + containerActual);
                                
                                if(!row[7].equals(bl)){
                                   caramelo += " BL/ AWB/ PRO: " + bl + "@";
                                   contador++;
                                }
                                
                                if(!row[26].equals(responsable)){
                                    
                                    String REs="";
                                     if (db.doDB("select DISTINCT RESPONSABLE from tra_destino_responsable where USER_NID="+responsable)) {
                                        for (String[] rowRes : db.getResultado()) {
                                                REs=rowRes[0];
                                        }}
                                    
                                    System.out.println("select DISTINCT RESPONSABLE from tra_destino_responsable where USER_NID="+responsable);
                                    
                                    
                                   caramelo += " Responsable: " + REs + "@";
                                   contador++;
                                }
                                System.out.println("1");
                                        
                                if(!row[2].equals(finaldes)){
                                   caramelo += " Final Destination (Shipment): " + finaldes + "@";
                                   contador++;
                                }
                                
                                if(!row[3].equals(Brand)){
                                     String REs="";
                                     if (db.doDB(" select  NOMBRE_BD from tra_inb_brand_division where ID_BD="+Brand)) {
                                        for (String[] rowRes : db.getResultado()) {
                                                REs=rowRes[0];
                                        }}
                                   caramelo += " Brand-Division: " + REs + "@";
                                   contador++;
                                }
                                
                                if(!row[27].equals(sbu_name)){
                                    
                                    String REs="";
                                     if (db.doDB(" select  DIVISION_NOMBRE from tra_inb_division where ID_DIVISION="+sbu_name)) {
                                        for (String[] rowRes : db.getResultado()) {
                                                REs=rowRes[0];
                                        }}
                                   caramelo += " Division: " + REs + "@";
                                   contador++;
                                }
    /*RULE #3*/                             
                                if(!row[5].equals(ShipmentActual)){
                                   caramelo += " Shipment ID: " + ShipmentActual + "@";
                                   contador++;
                                }
                                
                                if(!row[22].equals(Load1)){
                                   caramelo += " Load Type: " + Load1 + "@";
                                   contador++;
                                }
    /*RULE #4*/                      System.out.println("2");       
                                /************************************ Validación QUANTITY ********************************************/
                                if(Integer.parseInt(sbu_name)!=0){   /*(campo:SUBNAME !=0) ---> (TOMA CAMPO: CANTIDAD FINAL)*/
                                    valorNewQuantity = Integer.parseInt(row[30]); //2023
                                }else{                               /*(campo:SUBNAME ==0) ---> (TOMA CAMPO: SUMA)*/
                                    valorNewQuantity = Integer.parseInt(row[9]);
                                }
                                
                                if(valorNewQuantity!=quantity){   //#RULE 3       //2023 vs 2024
                                   caramelo += " Quantity: " + quantity + "@";
                                   contador++;
                                }
                                /***************************************************************************************************/
                                
                                if(!row[10].equals(pod)){
                                    
                                    
                                     String REs="";
                                     if (db.doDB(" select  NOMBRE_POD from tra_inb_pod where ID_POD="+pod)) {
                                        for (String[] rowRes : db.getResultado()) {
                                                REs=rowRes[0];
                                        }}
                                     
                                   caramelo += " POD: " + REs + "@";
                                   contador++;
                                }
                                System.out.println("3");
                                if(!row[11].equals("")){ 
                                    Date date1 = sdfSource.parse(row[11]);  
                                    fecha1_est_departure_pol = sdfDestination.format(date1);
                               
                                    if(!fecha1_est_departure_pol.equals(est_departure_pol)){
                                       caramelo += " Est. Departure from POL: " + est_departure_pol + "@";
                                       contador++;
                                    }
                                }
                                
                                if(!row[12].equals("")){
                                    Date date2 = sdfSource.parse(row[12]); 
                                    fecha2_eta_port_discharge = sdfDestination.format(date2);
                                
                                    if(!fecha2_eta_port_discharge.equals(eta_port_discharge)){
                                       caramelo += " ETA REAL PORT: " + eta_port_discharge + "@";
                                       contador++;
                                    }
                                }
                                
                                if(!row[13].equals(max_flete)){
                                   caramelo += " LT2: " + max_flete + "@";
                                   contador++;
                                }
                                
                                if(!row[23].equals("")){
                                    Date date3 = sdfSource.parse(row[23]); 
                                    fecha3_eta_plus2 = sdfDestination.format(date3);

                                    if(!fecha3_eta_plus2.equals(eta_plus2)){                         
                                       caramelo += " ETA DC: " + eta_plus2 + "@";
                                       contador++;
                                    }
                                }    
                                 
                                if(!row[24].equals("")){    
                                    Date date4 = sdfSource.parse(row[24]); 
                                    fecha4_eta_plus = sdfDestination.format(date4);
                                
                                    if(!fecha4_eta_plus.equals(eta_plus)){
                                       caramelo += " INDC +2 Days Put Away: " + eta_plus + "@";    
                                       contador++;
                                    }
                                } 
                            
                                if(!row[28].equals(pol)){
                                    
                                       String REs="";
                                     if (db.doDB("select  NOMBRE_POL from tra_inb_pol where ID_POL="+pol)) {
                                        for (String[] rowRes : db.getResultado()) {
                                                REs=rowRes[0];
                                        }}
                                     
                                   caramelo += " POL: " + REs + "@";
                                   contador++;
                                } 
                                
                                observacionOld = row[25]; //parametro para update (tra_inb_evento)
                                if(!observacionOld.equals(observacionActual)){
                                   caramelo += " Observaciones: " + observacionActual + "@";
                                   contador++;
                                }
                                
                                if(!row[29].equals("")){
                                    Date date5 = sdfSource.parse(row[29]); 
                                    fecha5_actual_crd = sdfDestination.format(date5);
                                
                                    if(!fecha5_actual_crd.equals(actual_crd)){
                                       caramelo += " Actual CRD: " + actual_crd + "@";
                                       contador++;
                                    }
                                }
                                
                                inboundNotification = row[14];

                            }
                        }

                        if(contador>0){  //Valores modificados en lista

                            /* Emisión de Alerta - Actualización de Campos: */
                            String consulta = "SELECT DISTINCT CORREO FROM TRA_INB_AGENTE_ADUANAL WHERE AGENTE_ADUANAL_ID = 4006 AND ESTATUS IN (1)";
                             //  String consulta = "select 'oamorales@tacts.mx/grecendiz@tacts.mx' from dual";
                            if (db.doDB(consulta)) {
                                for (String[] rowE : db.getResultado()) {
                                    emails = rowE[0].replaceFirst(" ", "@");  
                                }
                            }

                            correo.alertaModificarEventosFormulario(emails, evento, containerActual, ShipmentActual, inboundNotification, caramelo); 

                        }
                        

                        sqlGtn= " update tra_inc_gtn_test " +
                                " set  " +
                                " final_destination = '"+finaldes+"', " +
                                " brand_division='"+Brand+"', " +
                                " sbu_name='"+sbu_name+"', " +
                                " container1= '"+containerActual+"', " + /*Parametro Principal (2)*/
                                " shipment_id='"+ShipmentActual+"', " + /*Parametro Principal (3)*/
                                " load_type='"+Load1+"', " +
                                " LOAD_TYPE_FINAL='"+Load1+"', "+
                                "  clave='"+reg1+"',DOCTOS_ADUANEROS='"+reg2+"',"
                                + "TIPO_MATERIA='"+reg3+"', " ;
                    /************************************ Validación QUANTITY ********************************************/    
                                if(Integer.parseInt(sbu_name)!=0){   /*(campo:SUBNAME !=0) ---> (TOMA CAMPO: CANTIDAD FINAL)*/
                       sqlGtn +=" CANTIDAD_FINAL = '"+quantity+"', ";
                                }else{                               /*(campo:SUBNAME ==0) ---> (TOMA CAMPO: SUMA)*/
                        sqlGtn += "QUANTITY = '"+quantity+"', ";
                                }
                    /***************************************************************************************************/       
                    System.out.println("4");
                      sqlGtn += " pod='"+pod+"', " +
                                " max_flete='"+max_flete+"', ";
                 
                            if(!est_departure_pol.equals("")){ 
                       sqlGtn+= " est_departure_pol =to_date('"+est_departure_pol+"','MM/DD/YY'), ";
                            }  
                            
                            if(!eta_port_discharge.equals("")){ 
                       sqlGtn+= " eta_port_discharge=to_date('"+eta_port_discharge+"','MM/DD/YY'), ";
                            } 
                       
                            if(!eta_plus2.equals("")){ 
                       sqlGtn+= " eta_plus2=to_date('"+eta_plus2+"', 'MM/DD/YY'), ";
                            } 
                            
                            if(!eta_plus.equals("")){ 
                       sqlGtn+= " eta_plus=to_date('"+eta_plus+"','MM/DD/YY'), " ;
                            } 
                       
                            if(!actual_crd.equals("")){ 
                       sqlGtn+= " ACTUAL_CRD =to_date('"+actual_crd+"','MM/DD/YY'), ";
                            }    
                            
                       sqlGtn+= " pol='"+pol+"', " +     
                                " bl_awb_pro='"+bl+"' " +
                                " where container1='"+containerOld+"' " +
                                " and shipment_id='"+shipmenIdOld+"'";
                updateGtn =db.doDB(sqlGtn);
                
                if(!observacionOld.equals(observacionActual)){ /*ejecutar solo si la observación es diferente al historico*/
                        sqlEve =" update tra_inb_evento " +
                                " set  " +
                                " USER_NID ="+responsable+", " +
                                " observaciones='"+observacionActual+"' "+ 
                                " where ID_EVENTO='"+evento+"' ";
                 updateObservaciones=db.doDB(sqlEve);
                } 
                
                 if(updateGtn){
                     salida = true;
                 }
                System.out.println("5");
                 out.print(salida);
                 
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
