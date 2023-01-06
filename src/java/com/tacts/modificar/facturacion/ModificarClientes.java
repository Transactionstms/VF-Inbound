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
public class ModificarClientes extends HttpServlet {
    
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

        String id_cliente = request.getParameter("id_cliente"); 
        String cliente_descripcion = request.getParameter("cliente_descripcion");
        String rfc = request.getParameter("rfc");                
        String nombre_comercial = request.getParameter("nombre_comercial");   
        String codigo_cliente = request.getParameter("codigo_cliente");     
        String num_cliente = request.getParameter("num_cliente");        
        String calle = request.getParameter("calle");              
        String num_ext = request.getParameter("num_ext");            
        String num_int = request.getParameter("num_int");            
        String colonia = request.getParameter("colonia");            
        String localidad = request.getParameter("localidad");          
        String referencia = request.getParameter("referencia");         
        String num_regid_trib = request.getParameter("num_regid_trib");     
        String codigo_postal = request.getParameter("codigo_postal");      
        String pais_id = request.getParameter("pais_id");            
        String provincia_id = request.getParameter("provincia_id");       
        String estado_id = request.getParameter("estado_id");          
        String municipio_id = request.getParameter("municipio_id");       
        String nombre_comprador = request.getParameter("nombre_comprador");   
        String metodo_id = request.getParameter("metodo_id");          
        String forma_id = request.getParameter("forma_id");           
        String usocfdi_id = request.getParameter("usocfdi_id");         
        String nombreContacto1 = request.getParameter("nombreContacto1");  
        String emailContacto1 = request.getParameter("emailContacto1");  
        String nombreContacto2 = request.getParameter("nombreContacto2");  
        String emailContacto2 = request.getParameter("emailContacto2");  
        String nombreContacto3 = request.getParameter("nombreContacto3");  
        String emailContacto3 = request.getParameter("emailContacto3");  
        String telefono = request.getParameter("telefono");           
        String pagina_web = request.getParameter("pagina_web");         
        String coment_email_cfdi = request.getParameter("coment_email_cfdi");  
        String xml = request.getParameter("xml");     
        String pdf = request.getParameter("pdf");     
        String salida = "";
        
        String insertarClie = "UPDATE TRA_SUSCLIENTES SET "
                            + " CLIENTE_DESCRIPCION = '"+ cliente_descripcion +"', "
                            + " RFC = '"+ rfc +"', "  
                            + " NOMBRE_COMERCIAL = '"+ nombre_comercial +"', "  
                            + " CODIGO_CLIENTE = '"+ codigo_cliente +"', "  
                            + " NUM_CLIENTE = '"+ num_cliente +"', " 
                            + " CALLE = '"+ calle +"', "      
                            + " NUM_EXT = '"+ num_ext +"', "  
                            + " NUM_INT = '"+ num_int +"', "  
                            + " COLONIA = '"+ colonia +"', "   
                            + " LOCALIDAD = '"+ localidad +"', " 
                            + " REFERENCIA = '"+ referencia +"', " 
                            + " NUM_REGID_TRIB = '"+ num_regid_trib +"', "  
                            + " CODIGO_POSTAL = '"+ codigo_postal +"', " 
                            + " PAIS_ID = '"+ pais_id +"', "  
                            + " PROVINCIA_ID = '"+ provincia_id +"', " 
                            + " ESTADO_ID = '"+ estado_id +"', " 
                            + " MUNICIPIO_ID = '"+ municipio_id +"', "  
                            + " NOMBRE_COMPRADOR = '"+ nombre_comprador +"', "  
                            + " METODO_ID = '"+ metodo_id +"', "
                            + " FORMA_ID = '"+ forma_id +"', "  
                            + " USOCFDI_ID = '"+ usocfdi_id +"', " 
                            + " TELEFONO = '"+ telefono +"', " 
                            + " PAGINA_WEB = '"+ pagina_web +"', "  
                            + " COMENT_EMAIL_CFDI = '"+ coment_email_cfdi +"', " 
                            + " NOMBRE_CONTACTO1 = '"+ nombreContacto1 +"', "  
                            + " CORREO_CONTACTO1 = '"+ emailContacto1 +"', "
                            + " NOMBRE_CONTACTO2 = '"+ nombreContacto2 +"', "  
                            + " CORREO_CONTACTO2 = '"+ emailContacto2 +"', "
                            + " NOMBRE_CONTACTO3 = '"+ nombreContacto3 +"', " 
                            + " CORREO_CONTACTO3 = '"+ emailContacto3 +"', "
                            + " EMAIL_XML = '"+ xml +"', "
                            + " EMAIL_PDF = '"+ pdf +"' "
                            + " WHERE CLIENTE_ID = '" + id_cliente + "' "
                            + " AND CBDIV_ID = '" + cve + "' ";
        boolean oraOut = oraDB.execute(insertarClie);
             
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
            Logger.getLogger(ModificarClientes.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ModificarClientes.class.getName()).log(Level.SEVERE, null, ex);
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