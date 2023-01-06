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
 * @author luis_
 */
public class ConsultarDataCliente extends HttpServlet {
    
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
        String idCliente = request.getParameter("idCliente");
        
          String listarClientes = " SELECT DISTINCT "        
                                + " CLIENTE_DESCRIPCION, "
                                + " NVL(RFC,' '), "                
                                + " NVL(NOMBRE_COMERCIAL,' '), "   
                                + " CODIGO_CLIENTE, "     
                                + " NUM_CLIENTE, "        
                                + " NVL(CALLE,' '), "              
                                + " NVL(NUM_EXT,0), "            
                                + " NVL(NUM_INT,0), "            
                                + " COLONIA, "            
                                + " LOCALIDAD, "          
                                + " REFERENCIA, "         
                                + " NVL(NUM_REGID_TRIB,0), "     
                                + " CODIGO_POSTAL, "      
                                + " PAIS_ID, "            
                                + " NVL(PROVINCIA_ID,0), "       
                                + " NVL(ESTADO_ID,0), "          
                                + " MUNICIPIO_ID, "       
                                + " NVL(NOMBRE_COMPRADOR,' '), "   
                                + " NVL(METODO_ID,0), "          
                                + " NVL(FORMA_ID,0), "           
                                + " NVL(USOCFDI_ID,0), "         
                                + " NVL(TELEFONO,0), "           
                                + " NVL(PAGINA_WEB,' '), "         
                                + " NVL(COMENT_EMAIL_CFDI,' '), "  
                                + " FECHA_REGISTRO, "     
                                + " NVL(NOMBRE_CONTACTO1,' '), "    
                                + " NVL(CORREO_CONTACTO1,' '), "    
                                + " NVL(NOMBRE_CONTACTO2,' '), "  
                                + " NVL(CORREO_CONTACTO2,' '), "     
                                + " NVL(NOMBRE_CONTACTO3,' '), "    
                                + " NVL(CORREO_CONTACTO3,' '), "   
                                + " EMAIL_XML, "          
                                + " EMAIL_PDF "
                                + " FROM TRA_SUSCLIENTES "
                                + " WHERE CLIENTE_ID = '" + idCliente + "' "
                                + " AND CBDIV_ID = '" + cve + "' ";

        ServiceDAO dao = new ServiceDAO();
        ResultSet rs = dao.consulta(listarClientes);
        
            String salida = " ";
            while (rs.next()) {

                String CLIENTE_DESCRIPCION = rs.getString(1);
                String RFC = rs.getString(2);
                String NOMBRE_COMERCIAL = rs.getString(3);
                String CODIGO_CLIENTE = rs.getString(4);
                String NUM_CLIENTE = rs.getString(5);
                String CALLE = rs.getString(6);
                String NUM_EXT = rs.getString(7);
                String NUM_INT = rs.getString(8);
                String COLONIA = rs.getString(9);
                String LOCALIDAD = rs.getString(10);
                String REFERENCIA = rs.getString(11);
                String NUM_REGID_TRIB = rs.getString(12);
                String CODIGO_POSTAL = rs.getString(13);
                String PAIS_ID = rs.getString(14);
                String PROVINCIA_ID = rs.getString(15);
                String ESTADO_ID = rs.getString(16);
                String MUNICIPIO_ID = rs.getString(17);
                String NOMBRE_COMPRADOR = rs.getString(18);
                String METODO_ID = rs.getString(19);
                String FORMA_ID = rs.getString(20);
                String USOCFDI_ID = rs.getString(21);
                String TELEFONO = rs.getString(22);
                String PAGINA_WEB = rs.getString(23);
                String COMENT_EMAIL_CFDI = rs.getString(24);
                String FECHA_REGISTRO = rs.getString(25);
                String NOMBRE_CONTACTO1 = rs.getString(26);
                String CORREO_CONTACTO1 = rs.getString(27);
                String NOMBRE_CONTACTO2 = rs.getString(28);
                String CORREO_CONTACTO2 = rs.getString(29);
                String NOMBRE_CONTACTO3 = rs.getString(30);
                String CORREO_CONTACTO3 = rs.getString(31);
                String EMAIL_XML = rs.getString(32);
                String EMAIL_PDF = rs.getString(33);
                
                
                salida += CLIENTE_DESCRIPCION + "*" +
                            RFC + "*" +               
                            NOMBRE_COMERCIAL + "*" +    
                            CODIGO_CLIENTE + "*" +     
                            NUM_CLIENTE + "*" +        
                            CALLE + "*" +              
                            NUM_EXT + "*" +            
                            NUM_INT + "*" +            
                            COLONIA + "*" +            
                            LOCALIDAD + "*" +          
                            REFERENCIA + "*" +         
                            NUM_REGID_TRIB + "*" +     
                            CODIGO_POSTAL + "*" +      
                            PAIS_ID + "*" +           
                            PROVINCIA_ID + "*" +       
                            ESTADO_ID + "*" +          
                            MUNICIPIO_ID + "*" +       
                            NOMBRE_COMPRADOR + "*" +   
                            METODO_ID + "*" +        
                            FORMA_ID + "*" +           
                            USOCFDI_ID + "*" +         
                            TELEFONO + "*" +           
                            PAGINA_WEB + "*" +         
                            COMENT_EMAIL_CFDI + "*" + 
                            FECHA_REGISTRO + "*" +     
                            NOMBRE_CONTACTO1 + "*" +  
                            CORREO_CONTACTO1 + "*" +   
                            NOMBRE_CONTACTO2 + "*" +   
                            CORREO_CONTACTO2 + "*" +   
                            NOMBRE_CONTACTO3 + "*" +   
                            CORREO_CONTACTO3 + "*" +   
                            EMAIL_XML + "*" +   
                            EMAIL_PDF;
            }
            dao.CerrarConexion();
            out.print(salida);
            
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
            Logger.getLogger(ConsultarDataCliente.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarDataCliente.class.getName()).log(Level.SEVERE, null, ex);
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
