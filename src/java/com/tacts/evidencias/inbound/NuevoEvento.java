/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.inbound;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
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
public class NuevoEvento extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        
        HttpSession ownsession = request.getSession();
        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));

           String UserId = (String) ownsession.getAttribute("login.user_id_number");
           
        /***********************************************************************/
                String numEventoActual = request.getParameter("numEventoActual").trim();
                String ShipmentActual = request.getParameter("ShipmentActual").trim();
                String containerActual       = request.getParameter("containerActual").trim();
                /***********************************************************************/
              
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
                
                String mat2    = request.getParameter("mat2");
                String carr2   = request.getParameter("carr2");
                String tcon2   = request.getParameter("tcon2");
                String peso2   = request.getParameter("peso2"); 
                
                
                
                
                
                
                
                
                
        int x=0;
        
         if (db.doDB("select * from TRA_INB_EVENTO where ID_EVENTO='"+numEventoActual+"'")) {
             for (String[] row : db.getResultado()) {
                 System.out.println("existente");
                x++; 
                 
               }
            }
         
         if(x==0){
                 String sql2=" Insert into TRA_INB_EVENTO("
                         + "ID_EVENTO,USER_NID,SHIPMENT_ID,CONTAINER1,BL_AWB_PRO,PLANTILLA_ID,observaciones"
                         + ")values("
                         + "'"+numEventoActual+"','"+responsable+"','"+ShipmentActual+"','"+containerActual+"','"+bl+"','"+containerActual+"','"+observacionActual+"')";
             System.out.println("sql2"+sql2);
             
                 
                 String sql1=" INSERT INTO tra_inc_gtn_test (\n" +
//"        shipment_details,\n" +
"        brand_division,\n" +
"        shipment_id,\n" +
"        container1,\n" +
"        bl_awb_pro,\n" +
"        load_type,\n" +
"        LOAD_TYPE_FINAL,"+
// + "container_type,\n" +
//"        of_packages,\n" +
"        quantity,\n" +
//"        mode1,\n" +
"        final_destination,\n" +
//"        purchase_order,\n" +
//"        style1,\n" +
//"        style_desc,\n" +
"        pol,\n" +
"        pod,\n" +
"        actual_crd,\n" +
//"        received_vendor,\n" +
"        est_departure_pol,\n" +
//"        departed_pol,\n" +
 "        eta_port_discharge,\n" +
 //"        view_docs,\n" +
"        bodega_id,\n" +
"        cbdiv_id,\n" +
"        plantilla_id,\n" +
"        FOLIO ,\n" +
"        CONSECUTIVO,\n" +
"        \n" +
//"        CARRIER,\n" +
//"VESSEL,\n" +
//"VOLUME,\n" +
//"LAST_MILESTONE,\n" +
//"LAST_ADDED_COMMENT,\n" +
//"BRAND_DIVISION_SHIP,\n" +
//"CONTRACT,\n" +
//"CUSTOMER,\n" +
//"FORWARDER,\n" +
//"HBL_HAWB,\n" +
//"TERMINAL_POD,\n" +
"SBU_NAME,\n" +
"CANTIDAD_FINAL,\n" 
//"SHIPPED_ASN,"
                         + "max_flete,"
                         + "eta_plus2,"
                         + " eta_plus,"
                         
                         + "clave,"
                         + "DOCTOS_ADUANEROS,"
                         + "TIPO_MATERIA,"
                         
                         + "STYLE_DESC,"
                         + "CARRIER,"
                         + "CONTAINER_TYPE,"
                         + "PESO " +
"    ) VALUES (\n" +
//"        pshipment_details,\n" +
"       '"+Brand+"' ,\n" +
"        '"+ShipmentActual+"',\n" +
"        '"+containerActual+"',\n" +
"        '"+bl+"',\n" +
"        '"+Load1+"',\n" +
"        '"+Load1+"',\n" +
//"        pcontainer_type,\n" +
//"        pof_packages,\n" +
"        '"+quantity+"',\n" +
//"        pmode,\n" +
"        '"+finaldes+"',\n" +
//"        ppurchase_order,\n" +
//"        pstyle,\n" +
//"        pstyle_desc,\n" +
"        '"+pol+"',\n" +
"      '"+pod+"' ,\n" +
" to_date('"+actual_crd+"','MM/DD/YY'),\n" +
//" v_fecha1,\n" +
" to_date('"+est_departure_pol+"','MM/DD/YY'),\n" +
//" v_fecha3, \n" +
" to_date('"+eta_port_discharge+"','MM/DD/YY'), \n" +
//"        pview_docs,\n" +
"        0,\n" +
"        20,\n" +
"        '"+containerActual+"'\n" +
" \n" +
"    ,'"+containerActual+"',\n" +
"    '1',\n" +
//"pCARRIER,\n" +
//"pVESSEL,\n" +
//"pVOLUME,\n" +
//"pLAST_MILESTONE,\n" +
//"pLAST_ADDED_COMMENT,\n" +
//"pBRAND_DIVISION_SHIP,\n" +
//"pCONTRACT,\n" +
//"pCUSTOMER,\n" +
//"pFORWARDER,\n" +
//"pHBL_HAWB,\n" +
//"pTERMINAL_POD,\n" +
"'"+sbu_name+"',\n" +
" '"+quantity+"', " +
//"    ,pSHIPPED_ASN\n" +
"  '"+max_flete+"' ,"
+ "to_date('"+eta_plus2+"', 'MM/DD/YY'),"
                         + "to_date('"+eta_plus+"','MM/DD/YY'),"
                         
                         + "'"+reg1+"',"
                         + "'"+reg2+"',"
                         + "'"+reg3+"',"
                            + "'"+mat2+"',"
                            + "'"+carr2+"',"
                            + "'"+tcon2+"',"
                            + "'"+peso2+"'"
                         + " )\n" +
"   ";
                 
       System.out.println("sql1"+sql1);          
              boolean   excete1=db.doDB(sql1);
              boolean   excete2=db.doDB(sql2);
              
              if(excete1 && excete2){
                out.print("Evento agregado");
              }else{
                  out.print("El evento no se pudo agregar intente de nuevo y verifique los datos");
              }
              
         }else{
             out.print("Este evento ya existe puede modificarlo en el apartado Modificar Evento, pero no puede ");
         }       
             //   String sqlUpdate="update TRA_INC_GTN_TEST set PLANTILLA_ID='"+gtnId+"' where ID_GTN in ("+gtnId+")";
                //  update=db.doDB(sqlUpdate);
                //  insert=db.doDB(sql2);
               //   System.out.println(sqlUpdate+"--"+sql2);
                 
                 
           
        
        
       
        
        /*
          
        */

       // if(update && insert){
            
            
        
      //  }
      
        
             
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