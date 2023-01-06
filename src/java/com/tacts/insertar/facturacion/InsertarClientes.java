/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.insertar.facturacion;

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
 * @author luis_
 */
public class InsertarClientes extends HttpServlet {
    
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
        String metodo_id = request.getParameter("metodo_id");          
        String forma_id = request.getParameter("forma_id");           
        String usocfdi_id = request.getParameter("usocfdi_id");    
        String regimen_desc = request.getParameter("regimen_desc"); 
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
        String regimen_id = "";
        
        String salida = "";
        
        //Extraer descripción del regimen fiscal del cliente
        if (db.doDB(fac.consultarDescripcionRegimenFiscalSusCliente(regimen_desc))) {
            for (String[] rowRC : db.getResultado()) {
                 regimen_id = rowRC[0];
            }
        }

        String insertarClie = "INSERT INTO TRA_SUSCLIENTES"
                            + " (CLIENTE_ID,        "
                            + " CLIENTE_DESCRIPCION,"
                            + " RFC,                "
                            + " NOMBRE_COMERCIAL,   "
                            + " CODIGO_CLIENTE,     "
                            + " NUM_CLIENTE,        "
                            + " CALLE,              "
                            + " NUM_EXT,            "
                            + " NUM_INT,            "
                            + " COLONIA,            "
                            + " LOCALIDAD,          "
                            + " REFERENCIA,         "
                            + " NUM_REGID_TRIB,     "
                            + " CODIGO_POSTAL,      "
                            + " PAIS_ID,            "
                            + " PROVINCIA_ID,       "
                            + " ESTADO_ID,          "
                            + " MUNICIPIO_ID,       "
                            + " METODO_ID,          "
                            + " FORMA_ID,           "
                            + " USOCFDI_ID,         "
                            + " REGIMEN_ID,         "
                            + " TELEFONO,           "
                            + " PAGINA_WEB,         "
                            + " COMENT_EMAIL_CFDI,  "
                            + " FECHA_REGISTRO,     "
                            + " NOMBRE_CONTACTO1,   "
                            + " CORREO_CONTACTO1,   "
                            + " NOMBRE_CONTACTO2,   "
                            + " CORREO_CONTACTO2,   "
                            + " NOMBRE_CONTACTO3,   "
                            + " CORREO_CONTACTO3,   "
                            + " EMAIL_XML,          "
                            + " EMAIL_PDF,          "
                            + " REGIMENFISCALRECEPTOR,      "
                            + " DOMICILIOFISCALRECEPTOR,    "
                            + " ESTATUS_ID,                 "
                            + " CBDIV_ID)                   "
                            + " VALUES                      "
                            + " (NULL,                      "
                            + " '"+ cliente_descripcion +"',"
                            + " '"+ rfc +"',                "
                            + " '"+ nombre_comercial +"',   "
                            + " '"+ codigo_cliente +"',     "
                            + " '"+ num_cliente +"',        "
                            + " '"+ calle +"',              "
                            + " '"+ num_ext +"',            "
                            + " '"+ num_int +"',            "
                            + " '"+ colonia +"',            "
                            + " '"+ localidad +"',          "
                            + " '"+ referencia +"',         "
                            + " '"+ num_regid_trib +"',     "
                            + " '"+ codigo_postal +"',      "
                            + " '"+ pais_id +"',            "
                            + " '"+ provincia_id +"',       "
                            + " '"+ estado_id +"',          "
                            + " '"+ municipio_id +"',       "
                            + " '"+ metodo_id +"',          "
                            + " '"+ forma_id +"',           "
                            + " '"+ usocfdi_id +"',         "
                            + " '"+ regimen_id +"',         "
                            + " '"+ telefono +"',           "
                            + " '"+ pagina_web +"',         "
                            + " '"+ coment_email_cfdi +"',  "
                            + " '"+ fecha_registro +"',     "
                            + " '"+ nombreContacto1 +"',    "
                            + " '"+ emailContacto1 +"',     "
                            + " '"+ nombreContacto2 +"',    "
                            + " '"+ emailContacto2 +"',     "
                            + " '"+ nombreContacto3 +"',    "
                            + " '"+ emailContacto3 +"',     "
                            + " '"+ xml +"',                "
                            + " '"+ pdf +"',                "
                            + " '"+ regimen_desc +"',       "
                            + " '"+ codigo_postal +"',      "
                            + " 1,                          "
                            + " '" + cve + "')";
        boolean oraOut = oraDB.execute(insertarClie);
        
        System.out.println("Insert Cliente: " + insertarClie);
             
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
            Logger.getLogger(InsertarClientes.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InsertarClientes.class.getName()).log(Level.SEVERE, null, ex);
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
