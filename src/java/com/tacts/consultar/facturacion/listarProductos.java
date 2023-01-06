/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.consultar.facturacion;

import com.dao.ServiceDAO;
import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
 * @author Desarrollo Tacts
 */
public class listarProductos extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        
        HttpSession ownsession = request.getSession();
        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
        DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
        String UserId = (String) ownsession.getAttribute("login.user_id_number");
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        String cve = (String) ownsession.getAttribute("cbdivcuenta");

          String listarProductos= "SELECT DISTINCT"
                                + "CLIENTE_ID,         "
                                + "CLIENTE_DESCRIPCION,"
                                + "RFC,                "
                                + "NOMBRE_COMERCIAL,   "
                                + "CODIGO_CLIENTE,     "
                                + "NUM_CLIENTE,        "
                                + "NACIONALIDAD_ID,    "
                                + "CALLE,              "
                                + "NUM_EXT,            "
                                + "NUM_INT,            "
                                + "COLONIA,            "
                                + "LOCALIDAD,          "
                                + "REFERENCIA,         "
                                + "NUM_REGID_TRIB,     "
                                + "CODIGO_POSTAL,      "
                                + "PAIS_ID,            "
                                + "PROVINCIA_ID,       "
                                + "ESTADO_ID,          "
                                + "MUNICIPIO_ID,       "
                                + "NOMBRE_COMPRADOR,   "
                                + "METODO_ID,          "
                                + "FORMA_ID,           "
                                + "USOCFDI_ID,         "
                                + "TIPOENVIO_ID,       "
                                + "REL_CONTAC_ID,      "
                                + "TELEFONO,           "
                                + "PAGINA_WEB,         "
                                + "COMENT_EMAIL_CFDI,  "
                                + "REL_DATBANC_ID,     "
                                + "FECHA_REGISTRO,     "
                                + "ESTATUS_ID,         "
                                + "CBDIV_ID,           "
                                + "REGIMEN_ID,         "
                                + "NOMBRE_CONTACTO1,   "
                                + "CORREO_CONTACTO1,   "
                                + "NOMBRE_CONTACTO2,   "
                                + "CORREO_CONTACTO2,   "
                                + "NOMBRE_CONTACTO3,   "
                                + "CORREO_CONTACTO3,   "
                                + "EMAIL_XML,          "
                                + "EMAIL_PDF "
                                + "FROM TRA_SUSCLIENTES "
                                + "AND CBDIV_ID = '" + cve + "' ";

        ServiceDAO dao = new ServiceDAO();
        ResultSet rs = dao.consulta(listarProductos);
        int sal = 0;
       
            String salida = " ";
            while (rs.next()) {
                
                String cliente_id = rs.getString(1);         
                String cliente_descripcion = rs.getString(2);     
                String rfc = rs.getString(3);                     
                String nombre_comercial = rs.getString(4);        
                String codigo_cliente = rs.getString(5);          
                String num_cliente = rs.getString(6);             
                String nacionalidad_id = rs.getString(7);         
                String calle = rs.getString(8);                 
                String num_ext = rs.getString(9);                
                String num_int = rs.getString(10);                 
                String colonia = rs.getString(11);                 
                String localidad = rs.getString(12);               
                String referencia = rs.getString(13);              
                String num_regid_trib = rs.getString(14);          
                String codigo_postal = rs.getString(15);           
                String pais_id = rs.getString(16);                 
                String provincia_id = rs.getString(17);            
                String estado_id = rs.getString(18);               
                String municipio_id = rs.getString(19);            
                String nombre_comprador = rs.getString(20);        
                String metodo_id = rs.getString(21);               
                String forma_id = rs.getString(22);                
                String usocfdi_id = rs.getString(23);              
                String tipoenvio_id = rs.getString(24);            
                String rel_contac_id = rs.getString(25);           
                String telefono = rs.getString(26);                
                String pagina_web = rs.getString(27);             
                String coment_email_cfdi = rs.getString(28);       
                String rel_datbanc_id = rs.getString(29);          
                String fecha_registro = rs.getString(30);          
                String estatus_id = rs.getString(31);              
                String cbdiv_id = rs.getString(32);                
                String regimen_id = rs.getString(33);              
                String nombre_contacto1 = rs.getString(34);       
                String correo_contacto1 = rs.getString(35);        
                String nombre_contacto2 = rs.getString(36);        
                String correo_contacto2 = rs.getString(37);        
                String nombre_contacto3 = rs.getString(38);        
                String correo_contacto3 = rs.getString(39);        
                String email_xml = rs.getString(40);               
                String email_pdf = rs.getString(41);      

                
                salida += " <tr class=\"align-middle\"> "
                        + "    <td>" + codigo_cliente + "</td> "
                        + "    <td>" + rfc + "</td> "
                        + "    <td>" + cliente_descripcion + "</td> "
                        + "    <td>" + nombre_contacto1 + "</td> "
                        + "    <td>" + correo_contacto1 + "</td> "
                        + "    <td>" + telefono + "</td> "
                        //+ "    <td><a class=\"me-3 text-lg text-success\" href=\"../Bancos/List.jsp\"><i class=\"far fa-edit\"></i></a><a class=\"text-lg text-success\" href=\"Details.jsp\"><i class=\"far fa-edit\"></i></a>  <a class=\"me-3 text-lg text-success\" href=\"Edit.jsp\"><i class=\"far fa-edit\"></i></a><a class=\"text-lg text-danger\" href=\"\"><i class=\"far fa-trash-alt\"></i></a></td> "
                        + " </tr>";
                sal++;

            }
            dao.CerrarConexion();

            if (sal > 0) {
                out.print(salida);

            } else {
                out.print("No se encontraron datos");

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
            Logger.getLogger(listarProductos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(listarProductos.class.getName()).log(Level.SEVERE, null, ex);
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