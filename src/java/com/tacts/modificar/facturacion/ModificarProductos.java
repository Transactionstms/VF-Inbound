/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.modificar.facturacion;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
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
 * @author luis_
 */
public class ModificarProductos extends HttpServlet {
    
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
        
        Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha_registro = formato.format(date);
        
        String id_producto = request.getParameter("id_producto"); 
        String tipo_registro = request.getParameter("tipo_registro"); 
        String codigo = request.getParameter("codigo");       
        String descripcion = request.getParameter("descripcion");  
        String valor_unitario = request.getParameter("valor_unitario");
        String unidad_medida = request.getParameter("unidad_medida"); 
        String iva_id = request.getParameter("iva_id");        
        String ivaret = request.getParameter("iva_ret").replace('.','-');       
        String iepstasa_id = request.getParameter("iepstasa_id");   
        String iepscuota = request.getParameter("ieps_cuota").replace('.','-');      
        String isrGral = request.getParameter("isr").replace('.','-');          
        String epsret_id = request.getParameter("epsret_id");     
        String iepsretcuota = request.getParameter("ieps_ret_cuota").replace('.','-'); 
        String rel_clvsat_id = request.getParameter("rel_clvsat_id"); 
        String unidad_sat_id = request.getParameter("unidad_sat_id"); 
        String salida = "";
        String iva_ret = "";
        String ieps_cuota = "";
        String isr = "";
        String ieps_ret_cuota = ""; 
        
        /*Campo: Iva Retención*/
        String[] parts0 = ivaret.split("-");
        iva_ret = parts0[0] + "." + String.format("%-6s", parts0[1]).replace(' ','0');        //Rellenar decimales con 0
        
        /*Campo: IEPS Cuota*/
        String[] parts1 = iepscuota.split("-");
        ieps_cuota = parts1[0] + "." + String.format("%-6s", parts1[1]).replace(' ','0');     //Rellenar decimales con 0
        
        /*Campo: ISR*/
        String[] parts3 = isrGral.split("-");
        isr = parts3[0] + "." + String.format("%-6s", parts3[1]).replace(' ','0');            //Rellenar decimales con 0
       
        /*Campo: IEPS Retención Cuota*/
        String[] parts4 = iepsretcuota.split("-");
        ieps_ret_cuota = parts4[0] + "." + String.format("%-6s", parts4[1]).replace(' ','0'); //Rellenar decimales con 0
        
        
        String insertarProd = "UPDATE TRA_PRODUCTOS_SERVICIOS SET " 
                            + " TIPO_REGISTRO = '"+ tipo_registro +"', "
                            + " CODIGO = '"+ codigo +"', "
                            + " DESCRIPCION = '"+ descripcion +"', "
                            + " VALOR_UNITARIO = '"+ valor_unitario +"', "
                            + " UNIDAD_MEDIDA = '"+ unidad_medida +"', "
                            + " IVA_ID = '"+ iva_id +"', "
                            + " IVA_RET = '"+ iva_ret +"', "
                            + " IEPSTASA_ID = '"+ iepstasa_id +"', "
                            + " IEPS_CUOTA = '"+ ieps_cuota +"', "
                            + " ISR =        '"+ isr +"', "
                            + " EPSRET_ID = '"+ epsret_id +"', "
                            + " IEPS_RET_CUOTA = '"+ ieps_ret_cuota +"', "
                            + " REL_CLVSAT_ID = '"+ rel_clvsat_id +"', "
                            + " UNIDAD_SAT_ID = '"+ unidad_sat_id +"', "
                            + " FECHA_REGISTRO = '"+ fecha_registro +"' "
                            + " WHERE CLVREG_ID = '" + id_producto + "' "
                            + " AND CBDIV_ID = '" + cve + "' ";
        boolean oraOut = oraDB.execute(insertarProd);
        
            if(oraOut){
                salida = "true";
            }else{
                salida = "false";
            }
            
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
            Logger.getLogger(ModificarProductos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ModificarProductos.class.getName()).log(Level.SEVERE, null, ex);
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
