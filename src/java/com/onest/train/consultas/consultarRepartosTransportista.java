/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;


import com.conexion.GenericJdbc;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gerardorecendiz
 */
public class consultarRepartosTransportista extends HttpServlet {

     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
       
       GenericJdbc generico = new GenericJdbc();
         generico.openConection();
         Statement statement = generico.getConnection().createStatement();
String agrupador = request.getParameter("agrupador");
       FileWriter fichero = new FileWriter("D:\\Servicios\\VFMX\\TXT\\DetallePlaneacionINB.txt");  
       //   FileWriter fichero = new FileWriter("/Users/gerardorecendiz/Desktop/DetallePlaneacion1.txt" );
         String sql = "SELECT\n" +
"    'SHIPTO',\n" +
"    bod.bodega_nombre,\n" +
"    bod.rfc,\n" +
"    bod.bodega_direccion,\n" +
"    'Colonia',\n" +
"    'Ciudad',\n" +
"    'Estado',\n" +
"    bod.destino_cp,\n" +
"    bod.bodega_direccion,\n" +
"    bod.no_int no_ext,\n" +
"    bod.clavelocal,\n" +
"    bod.clave_mun,\n" +
"    bod.bodega_pais,\n" +
"    bod.clave_colonia,\n" +
"    bod.clavelocal,\n" +
"    bod.clave_mun,\n" +
"    'Orden de compra',\n" +
"    gtn.shipment_id,\n" +
"    sci.total_actual_vol_cbms,\n" +
"    sci.total_cartons,\n" +
"    gtn.cantidad_final,\n" +
"    gtn.volume,\n" +
"    nvl(cus.pedimento_r1, nvl(cus.pedimento_r1_2do, ' ')),\n" +
"    cus.fecha_pago_pedimento,\n" +
"    gtn.style_desc,\n" +
"    cus.recinto,\n" +
"    sty.clave_sat,\n" +
"    sty.descripcion_sat,\n" +
"    sty.clave_unidad,\n" +
"    sty.unidad_medida,"
                 + "'0','0','0',\n" +
"    tie.id_evento\n" +
"FROM\n" +
"    tra_inc_gtn_test gtn\n" +
"    LEFT JOIN tra_inb_evento   tie ON gtn.plantilla_id = tie.plantilla_id\n" +
"    LEFT JOIN tra_inb_customs  cus ON cus.shipment_id = gtn.shipment_id\n" +
"    LEFT JOIN tra_inb_stylesat sty ON sty.style_desc = gtn.style_desc\n" +
"    LEFT JOIN tra_inb_sci      sci ON sci.original_shipment_id = gtn.shipment_id\n" +
"    LEFT JOIN ontms_bodega     bod ON bod.bodega_id = 2\n" +
"WHERE\n" +
"     GTN.EMBARQUE_AGRUPADOR = '"+agrupador+"'";  

         System.out.println(sql);
       ResultSet rs = statement.executeQuery(sql);
        int sal = 0;
        try (PrintWriter out = response.getWriter()) {
            
           
            
            

           
            while (rs.next()) {

              fichero.write(" " 
                    + " Shipto                    : "+rs.getString(1 ) + " \r\n"
                    + " Destino                   : "+rs.getString(2 ) + " \r\n"
                    + " RFC                       : "+rs.getString(3) +" \r\n"        
                    + " Domicilio                 : "+rs.getString(4 ) + " \r\n"
                    + " Colonia                   : "+rs.getString(5 ) + " \r\n"
                    + " Ciudad                    : "+rs.getString(6 ) + " \r\n"
                    + " Estado                    : "+rs.getString(7 ) + " \r\n"
                    + " Destino CP                : "+rs.getString(8) +" \r\n"
                    + " Calle                     : "+rs.getString(9) +" \r\n"
                    + " No Exterior               : "+rs.getString(10) +" \r\n"
                    + " No Interior               : "+rs.getString(11) +" \r\n"
                    + " Localidad                 : "+rs.getString(12) +" \r\n"
                    + " Municipio                 : "+rs.getString(13) +" \r\n"
                    + " Pais                      : "+rs.getString(14) +" \r\n"          
                    
                    + " Clave Colonia             : " + rs.getString(14) + " \r\n"    
                    + " Clave Localidad           : " + rs.getString(15) + " \r\n"    
                    + " Clave Municipio           : " + rs.getString(16) + " \r\n" 
                            
                    + " Orden de compra           : "+rs.getString(17 ) + " \r\n"
                    + " Referencia                : "+rs.getString(18 ) + " \r\n"
                   // + " Cartón                    : "+rs.getString(19) +" \r\n" 
                    + " Peso                      : "+rs.getString(20 ) + " \r\n"
                    + " Cajas                     : "+rs.getString(21) + " \r\n"
                    + " Piezas                    : "+rs.getString(22) + " \r\n"
                    + " Volumen                   : "+rs.getString(23) +" \r\n"
                    + " Pedimento                 : "+rs.getString(24) +" \r\n"
                    + " Fecha Pedimento           : "+rs.getString(25) +" \r\n"
                    + " Producto                  : "+rs.getString(26) +" \r\n"
                  //  + " STPRC                     : "+rs.getString(14) +" \r\n"
                    + " Aduana                    : "+rs.getString(27) +" \r\n"
                    + " Clave Producto Servicio   : "+rs.getString(28) +" \r\n"
                    + " Descripción               : "+rs.getString(29) +" \r\n"
                    + " Clave Unidad              : "+rs.getString(30) +" \r\n"
                    + " Unidad Medida             : "+rs.getString(31) +" \r\n"
                    + " Peso Bruto                : "+rs.getString(32) +" \r\n"
                    + " Peso Tara                 : "+rs.getString(33) +" \r\n"
                    + " Peso Neto                 : "+rs.getString(34) +" \r\n"         
                    + "\r\n \r\n");
              
            
                
                sal++;
            }
            
            fichero.close();
           
             
            if (sal > 0) {
                out.print("ok");

            } else {
                out.print("No se encontraron datos");

            }
           generico.closeConnection();
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
