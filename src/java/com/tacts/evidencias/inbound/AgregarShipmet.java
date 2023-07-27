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
 * @author grecendiz
 */
public class AgregarShipmet extends HttpServlet {

     
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

                
                String responsable = request.getParameter("responsable");
                String finaldes    = request.getParameter("finaldes");
                String Brand       = request.getParameter("Brand");

                String sbu_name = request.getParameter("sbu_name");
                String Shipment = request.getParameter("Shipment");
                String Load1    = request.getParameter("Load1");

                String quantity          = request.getParameter("quantity");
                String pod               = request.getParameter("pod");
                String est_departure_pol = request.getParameter("est_departure_pol");

                String eta_port_discharge = request.getParameter("eta_port_discharge");
                String max_flete          = request.getParameter("max_flete");
                String eta_plus2          = request.getParameter("eta_plus2");

                String eta_plus         = request.getParameter("eta_plus");
                String pol              = request.getParameter("pol");
                String observaciones    = request.getParameter("observaciones");
                
                String actual_crd       = request.getParameter("actual_crd");

                String evento    = request.getParameter("evento");
                String ship      = request.getParameter("ship");
                String con       = request.getParameter("con");

                String bl       = request.getParameter("bl");

                /*Parametros - Comparación de información (formulario/dba)*/
                String fecha1_est_departure_pol = "";
                String fecha2_eta_port_discharge = "";
                String fecha3_eta_plus2 = "";
                String fecha4_eta_plus = "";
                String fecha5_actual_crd = "";

                String caramelo = "";
                String emails = "";
                int contador = 0;

                  

                   
                        
                 String sqlEve =" update tra_inb_evento " +
                                " set  " +
                                " USER_NID ="+responsable+", " +
                                " observaciones='"+observaciones+"' " +
                                " where ID_EVENTO='"+evento+"' ";
                // boolean update=db.doDB(sqlEve);

                 String sqlGtn= "  insert into tra_inc_gtn_test (    \n" +
"SHIPMENT_DETAILS, \n" +
"BRAND_DIVISION  ,  \n" +
"SHIPMENT_ID     , \n" +
"CONTAINER1      , \n" +
"BL_AWB_PRO      , \n" +
"LOAD_TYPE       , \n" +
"CONTAINER_TYPE  , \n" +
"OF_PACKAGES     ,        \n" +
"QUANTITY        ,        \n" +
"MODE1           , \n" +
"FINAL_DESTINATION , \n" +
"PURCHASE_ORDER  , \n" +
"STYLE1          , \n" +
"STYLE_DESC      , \n" +
"POL             , \n" +
"POD             , \n" +
"ACTUAL_CRD       ,          \n" +
"RECEIVED_VENDOR  ,          \n" +
"EST_DEPARTURE_POL,          \n" +
"DEPARTED_POL     ,          \n" +
"DOCS_PROVIDED_BROKER,          \n" +
"ETA_PORT_DISCHARGE  ,          \n" +
"ETA_POD          ,          \n" +
"ARRIVED_POD      ,          \n" +
"OUTLOOK_ETA      ,          \n" +
"DELIVERED_FINAL_DEST ,\n" +
"RECEIVED_FINAL_DEST ,\n" +
"VIEW_DOCS       , \n" +
"ESTATUS         ,        \n" +
"FECHA_CAPTURA    ,          \n" +
"BODEGA_ID       ,        \n" +
"CBDIV_ID        ,        \n" +
"PLANTILLA_ID    , \n" +
"MAX_FLETE       ,        \n" +
"ETA_PLUS2        ,          \n" +
"ETA_PLUS         ,          \n" +
"SBU_NAME        , \n" +
"LOAD_TYPE_FINAL , \n" +
"FOLIO           , \n" +
"PRIORIDAD      ,  \n" +
"STATUS_EMBARQUE ,        \n" +
"EMBARQUE_AGRUPADOR, \n" +
"CONSECUTIVO     ,        \n" +
"URL_POD             , \n" +
"CARRIER         , \n" +
"VESSEL              , \n" +
"VOLUME              , \n" +
"LAST_MILESTONE      , \n" +
"LAST_ADDED_COMMENT  , \n" +
"BRAND_DIVISION_SHIP , \n" +
"CONTRACT            , \n" +
"CUSTOMER            , \n" +
"FORWARDER           , \n" +
"HBL_HAWB            , \n" +
"TERMINAL_POD        \n" +
")select \n" +
"SHIPMENT_DETAILS, \n" +
"'"+Brand+"' as BRAND_DIVISION  ,  \n" +
"'"+Shipment+"' as SHIPMENT_ID     , \n" +
"CONTAINER1      , \n" +
"'"+bl+"' as BL_AWB_PRO      , \n" +
"'"+Load1+"' as LOAD_TYPE       , \n" +
"CONTAINER_TYPE  , \n" +
"OF_PACKAGES     ,        \n" +
"'"+quantity+"' as QUANTITY        ,        \n" +
"MODE1           , \n" +
"'"+finaldes+"' as FINAL_DESTINATION , \n" +
"PURCHASE_ORDER  , \n" +
"STYLE1          , \n" +
"STYLE_DESC      , \n" +
"'"+pol+"' as POL             , \n" +
"'"+pod+"' as POD             , \n" +
" ACTUAL_CRD       ,          \n" +//to_date('"+actual_crd+"','MM/DD/YY') as
"RECEIVED_VENDOR  ,          \n" +
"to_date('"+est_departure_pol+"','MM/DD/YY') as EST_DEPARTURE_POL,          \n" +
"DEPARTED_POL     ,          \n" +
"DOCS_PROVIDED_BROKER,          \n" +
"to_date('"+eta_port_discharge+"','MM/DD/YY') as ETA_PORT_DISCHARGE  ,          \n" +
"ETA_POD          ,          \n" +
"ARRIVED_POD      ,          \n" +
"OUTLOOK_ETA      ,          \n" +
"DELIVERED_FINAL_DEST ,\n" +
"RECEIVED_FINAL_DEST ,\n" +
"VIEW_DOCS       , \n" +
"ESTATUS         ,        \n" +
"FECHA_CAPTURA    ,          \n" +
"BODEGA_ID       ,        \n" +
"CBDIV_ID        ,        \n" +
"PLANTILLA_ID    , \n" +
"'"+max_flete+"' as MAX_FLETE       ,        \n" +
"to_date('"+eta_plus2+"', 'MM/DD/YY') as ETA_PLUS2        ,          \n" +
"to_date('"+eta_plus+"','MM/DD/YY') as ETA_PLUS         ,          \n" +
"'"+sbu_name+"' as SBU_NAME        , \n" +
"'"+Load1+"' as LOAD_TYPE_FINAL , \n" +
"FOLIO           , \n" +
"PRIORIDAD      ,  \n" +
"STATUS_EMBARQUE ,        \n" +
"EMBARQUE_AGRUPADOR, \n" +
"CONSECUTIVO     ,        \n" +
"URL_POD             , \n" +
"CARRIER         , \n" +
"VESSEL              , \n" +
"VOLUME              , \n" +
"LAST_MILESTONE      , \n" +
"LAST_ADDED_COMMENT  , \n" +
"BRAND_DIVISION_SHIP , \n" +
"CONTRACT            , \n" +
"CUSTOMER            , \n" +
"FORWARDER           , \n" +
"HBL_HAWB            , \n" +
"TERMINAL_POD from tra_inc_gtn_test where ID_GTN=("
                         + "SELECT ID_GTN FROM (   SELECT ID_GTN, ROW_NUMBER() OVER (ORDER BY ID_GTN DESC) AS rn   FROM tra_inc_gtn_test where SHIPMENT_ID='"+ship+"') WHERE rn = 1 )";
              
             System.out.println("sqlGtn  "+sqlGtn);        
                 boolean insert=db.doDB(sqlGtn);
                
                /*Instrucción Store Procedure - UpdateLtdGtnConShipmentId*/
               // String storeProcedure = obj.updateGtnShipmentId(Shipment);
               // System.out.println("Respuesta Store Procedure: " + storeProcedure);
        
               if(insert){
                   out.print("correcto");
                   System.out.println("correcto");
               }else{
                   out.print("error");
                    System.out.println("error");
               }
               
               
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

