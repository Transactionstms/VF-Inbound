/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.insertar.catalogos;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.consultas.ConsultasQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
public class InsertarCustomsForms extends HttpServlet {
    
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
        
        try{
            
        Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha_registro = formato.format(date);
        ConsultasQuery fac = new ConsultasQuery();
    
          String evento = request.getParameter("evento");    
          String pais_origen = request.getParameter("pais_origen"); 
          String size_container = request.getParameter("size_container"); 
          String valor_usd = request.getParameter("valor_usd");                
          String eta_port_discharge = request.getParameter("eta_port_discharge");        
          String agente_aduanal = request.getParameter("agente_aduanal");            
          String pedimento_a1 = request.getParameter("pedimento_a1");             
          String pedimento_r1_1er = request.getParameter("pedimento_r1_1er");          
          String motivo_rectificacion_1er = request.getParameter("motivo_rectificacion_1er");  
          String pedimento_r1_2do = request.getParameter("pedimento_r1_2do");          
          String motivo_rectificacion_2do = request.getParameter("motivo_rectificacion_2do");  
          String fecha_recepcion_doc = request.getParameter("fecha_recepcion_doc");  
          String recinto = request.getParameter("recinto");
          String naviera = request.getParameter("naviera");
          String buque = request.getParameter("buque");
          String fecha_revalidacion = request.getParameter("fecha_revalidacion");        
          String fecha_previo_origen = request.getParameter("fecha_previo_origen");       
          String fecha_previo_destino = request.getParameter("fecha_previo_destino");      
          String fecha_resultado_previo = request.getParameter("fecha_resultado_previo");    
          String proforma_final = request.getParameter("proforma_final");            
          String permiso = request.getParameter("permiso");                   
          String fecha_envio = request.getParameter("fecha_envio");               
          String fecha_recepcion_perm = request.getParameter("fecha_recepcion_perm");    
          String fecha_activacion_perm = request.getParameter("fecha_activacion_perm");     
          String fecha_permisos_aut = request.getParameter("fecha_permisos_aut");        
          String co_pref_arancelaria = request.getParameter("co_pref_arancelaria");       
          String aplic_pref_arancelaria = request.getParameter("aplic_pref_arancelaria");    
          String req_uva = request.getParameter("req_uva");   
          String req_ca = request.getParameter("req_ca");   
          String fecha_recepcion_ca = request.getParameter("fecha_recepcion_ca");   
          String num_constancia_ca = request.getParameter("num_constancia_ca");   
          String monto_ca = request.getParameter("monto_ca");   
          String fecha_doc_completos = request.getParameter("fecha_doc_completos");       
          String fecha_pago_pedimento = request.getParameter("fecha_pago_pedimento");      
          String fecha_solicitud_transporte = request.getParameter("fecha_solicitud_transporte");
          String fecha_modulacion = request.getParameter("fecha_modulacion");          
          String modalidad = request.getParameter("modalidad");                 
          String resultado_modulacion = request.getParameter("resultado_modulacion");      
          String fecha_reconocimiento = request.getParameter("fecha_reconocimiento");      
          String fecha_liberacion = request.getParameter("fecha_liberacion");         
          String sello_origen = request.getParameter("sello_origen");              
          String sello_final = request.getParameter("sello_final");               
          String fecha_retencion_aut = request.getParameter("fecha_retencion_aut");       
          String fecha_liberacion_aut = request.getParameter("fecha_liberacion_aut");     
          String estatus_operacion = request.getParameter("estatus_operacion");         
          String motivo_atraso = request.getParameter("motivo_atraso");             
          String observaciones = request.getParameter("observaciones"); 
          String salida = "";
        
         String insertarClie =  " INSERT INTO TRA_CUSTOMS " 
                              + " (REG_ID, "  
                              + " EVENTO, "  
                              + " PAIS_ORIGEN, "  
                              + " SIZE_CONTAINER, "  
                              + " VALOR_USD, "                
                              + " ETA_PORT_DISCHARGE, "        
                              + " AGENTE_ADUANAL, "            
                              + " PEDIMENTO_A1, "              
                              + " PEDIMENTO_R1_1ER, "          
                              + " MOTIVO_RECTIFICACION_1ER, "  
                              + " PEDIMENTO_R1_2DO, "          
                              + " MOTIVO_RECTIFICACION_2DO, "  
                              + " FECHA_RECEPCION_DOC, "    
                              + " RECINTO, "    
                              + " NAVIERA, "    
                              + " BUQUE, "   
                              + " FECHA_REVALIDACION, "        
                              + " FECHA_PREVIO_ORIGEN, "       
                              + " FECHA_PREVIO_DESTINO, "      
                              + " FECHA_RESULTADO_PREVIO, "    
                              + " PROFORMA_FINAL, "            
                              + " PERMISO, "                   
                              + " FECHA_ENVIO, "               
                              + " FECHA_RECEPCION_PERM, "      
                              + " FECHA_ACTIVACION_PERM, "     
                              + " FECHA_PERMISOS_AUT, "        
                              + " CO_PREF_ARANCELARIA, "       
                              + " APLIC_PREF_ARANCELARIA, "    
                              + " REQ_UVA, "    
                              + " REQ_CA, "  
                              + " FECHA_RECEPCION_CA, "  
                              + " NUM_CONSTANCIA_CA, "  
                              + " MONTO_CA, "  
                              + " FECHA_DOC_COMPLETOS, "       
                              + " FECHA_PAGO_PEDIMENTO, "      
                              + " FECHA_SOLICITUD_TRANSPORTE, "
                              + " FECHA_MODULACION, "          
                              + " MODALIDAD, "                 
                              + " RESULTADO_MODULACION, "      
                              + " FECHA_RECONOCIMIENTO, "      
                              + " FECHA_LIBERACION, "         
                              + " SELLO_ORIGEN, "              
                              + " SELLO_FINAL, "               
                              + " FECHA_RETENCION_AUT, "       
                              + " FECHA_LIBERACION_AUT, "      
                              + " ESTATUS_OPERACION, "         
                              + " MOTIVO_ATRASO, "             
                              + " OBSERVACIONES, "             
                              + " FECHA_REGISTRO, "            
                              + " ESTATUS_ID, "                
                              + " CBDIV_ID, "
                              + " USER_NID) "
                              + " VALUES " 
                              + " (NULL, "  
                              + " '" + evento + "', "  
                              + " '" + pais_origen + "', "  
                              + " '" + size_container + "', "  
                              + " '" + valor_usd + "', "                
                              + " '" + eta_port_discharge + "', "        
                              + " '" + agente_aduanal + "', "            
                              + " '" + pedimento_a1 + "', "              
                              + " '" + pedimento_r1_1er + "', "          
                              + " '" + motivo_rectificacion_1er + "', "
                              + " '" + pedimento_r1_2do + "', "          
                              + " '" + motivo_rectificacion_2do + "', "  
                              + " '" + fecha_recepcion_doc + "', "   
                              + " '" + recinto + "', " 
                              + " '" + naviera + "', " 
                              + " '" + buque + "', " 
                              + " '" + fecha_revalidacion + "', "        
                              + " '" + fecha_previo_origen + "', "       
                              + " '" + fecha_previo_destino + "', "      
                              + " '" + fecha_resultado_previo + "', "    
                              + " '" + proforma_final + "', "            
                              + " '" + permiso + "', "                   
                              + " '" + fecha_envio + "', "               
                              + " '" + fecha_recepcion_perm + "', "      
                              + " '" + fecha_activacion_perm + "', "     
                              + " '" + fecha_permisos_aut + "', "        
                              + " '" + co_pref_arancelaria + "', "       
                              + " '" + aplic_pref_arancelaria + "', "    
                              + " '" + req_uva + "', "   
                              + " '" + req_ca + "', "   
                              + " '" + fecha_recepcion_ca + "', "   
                              + " '" + num_constancia_ca + "', "   
                              + " '" + monto_ca + "', "  
                              + " '" + fecha_doc_completos + "', "       
                              + " '" + fecha_pago_pedimento + "', "      
                              + " '" + fecha_solicitud_transporte + "', "
                              + " '" + fecha_modulacion + "', "          
                              + " '" + modalidad + "', "                 
                              + " '" + resultado_modulacion + "', "      
                              + " '" + fecha_reconocimiento + "', "      
                              + " '" + fecha_liberacion + "', "         
                              + " '" + sello_origen + "', "              
                              + " '" + sello_final + "', "               
                              + " '" + fecha_retencion_aut + "', "       
                              + " '" + fecha_liberacion_aut + "', "      
                              + " '" + estatus_operacion + "', "         
                              + " '" + motivo_atraso + "', "             
                              + " '" + observaciones + "', "             
                              + " SYSDATE, "            
                              + " 1, "                
                              + " '" + cve + "', "
                              + " '" + UserId + "') ";  
        boolean oraOut = oraDB.execute(insertarClie);
        
        System.out.println("Insert Registro: " + insertarClie);
             
            if(oraOut){
                salida = "1";
            }else{
                salida = "0";
            }
            
         out.print(salida);
         oraDB.close(); //cerrar conexión
         
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(InsertarCustomsForms.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InsertarCustomsForms.class.getName()).log(Level.SEVERE, null, ex);
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
