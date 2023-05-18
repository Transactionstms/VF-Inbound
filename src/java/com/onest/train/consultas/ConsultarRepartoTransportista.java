/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;

// import com.dao.ServiceDAO; 
 import com.conexion.GenericJdbc;
 import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class ConsultarRepartoTransportista extends HttpServlet {
 

      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String transportista=request.getParameter("transporte");
      
       GenericJdbc generico = new GenericJdbc();
         generico.openConection();
         Statement statement = generico.getConnection().createStatement();

        
         String sql =" "
       +   " SELECT distinct oe.oe_embarque_id,"
                            + " oe.oe_embarque_fec_captura, "
                            + " oe.oe_embarque_agrupador,"
                            + " '' as test,"
                            + " NVL(to_char(oe.oe_embarque_costo_real),'0'),"
                            + " SUM(NVL(oc.d_docto_piezas, 0)),"
                            + " SUM(NVL(oc.d_docto_cajas, 0)),"
                            + " SUM(NVL(oc.d_docto_pallets, 0)), "
                            + " SUM(NVL(oc.d_docto_colgados, 0)),"
                            + " SUM(NVL(oc.d_Docto_Contenedor, 0)), "
                            + " SUM(NVL(oc.d_docto_atados, 0)),"
                            + " SUM(NVL(oc.d_docto_bulks, 0)),"
                            + " SUM(NVL(oc.d_docto_importe, 0)),"
                            + " SUM(NVL(oc.d_docto_peso, 0)), "
                            + " SUM(NVL(oc.d_docto_volumen, 0)),"
                            + " NVL(oe.oc_chofer_nombre,' '),"
                            + " NVL(oe.olt_ltransporte_nombre,' '),"
                            + " NVL(oe.outr_utransporte_desc,' '),"
                            + " oe_embarque_fec_inicio,"
                            + " oe_embarque_fec_fin,"
                            + " NVL(OP.PROVEEDOR_NOMBRE,'NO APLICA')"
                            + " FROM VW_PQ_EM oe"
                            + " INNER JOIN ontms_vw_reporte_doc2 oc ON oc.d_docto_Sal_agrupador=oe.oe_embarque_agrupador"
                            + " LEFT JOIN ONTMS_PROVEEDORES OP ON OP.PROVEEDOR_EXT = OE_EMBARQUE_TCUSTODIA"
                            + " WHERE oc.d_cbdiv_id IN (20, 21, 22, 23, 24)  "
                            + " and oe.olt_ltransporte_id=" + transportista + " "
                            + " AND oc.d_docto_estado_id IN (2) or  (OE_EMBARQUE_ESTADO_ID=0 and oc.d_docto_estado_id=150)"
                            + "  "
                            + " AND OE.OLT_LTRANSPORTE_NOMBRE <> 'CLIENTE RECOGE'"
                            + " GROUP BY oe.oe_embarque_id, oe.oe_embarque_fec_captura, oe.oe_embarque_agrupador,"
                            + " oe.oe_embarque_costo_real, oe.oc_chofer_nombre, oe.olt_ltransporte_nombre, oe.outr_utransporte_desc,"
                            + " oe_embarque_fec_inicio,oe_embarque_fec_fin, OP.PROVEEDOR_NOMBRE"
                            + " ORDER BY oe.oe_embarque_fec_captura,oe.oe_embarque_id ";
          System.out.println(sql); 
       ResultSet rs = statement.executeQuery(sql);
        int sal = 0;
        try (PrintWriter out = response.getWriter()) {
            
           
            
            

            String salida = ""
                    + " <table id=\"myTable\" class=\"display nowrap\" cellspacing=\"0\" width=\"100%\">\n" +
"                                                                    <thead class=\"thead-dark\">\n" +
"                                                                        <tr  >\n" +
"                                                                            <th>Guia de embarque </th> \n" +
"                                                                            <th>Fec de Carga     </th> \n" +
"                                                                            <th>Pzs              </th>\n" +
"                                                                            <th>Cjs              </th> \n" +
"                                                                            <th>Kg               </th>\n" +
"                                                                            <th>m3               </th>\n" +
"                                                                            <th>Operador         </th>\n" +
"                                                                            <th>Transporte       </th>\n" +
"                                                                            <th>Custodia         </th>\n" +
//"                                                                            <th>Maniobra         </th>\n" +
"                                                                            <th>Unidad           </th>\n" +
"                                                                             <!--   <th>Modificar    </th>-->\n" +
"                                                                            <th>Repartos         </th>\n" +
//"                                                                            <th>Planeacion       </th>\n" +
//"                                                                            <th>Confirmar        </th> \n" +
"                                                                        </tr>\n" +
"\n" +
"                                                                    </thead> \n" +
"                                                                    <tbody>"
                    + "";
            while (rs.next()) { 
             // rs.getString(1); 
                salida += ""
                        + " <tr>\n" +
                          "     <td class=\"\"><b>"+rs.getString(1)+"</b></td> \n" +
                          "     <td class=\"\"><b>"+rs.getString(2)+"</b></td> \n" +
                          "     <td class=\"\"><b>"+rs.getString(6)+"</b></td>\n" +
                          "     <td class=\"\"><b>"+rs.getString(7)+"</b></td> <!--Cajas-->\n" +
                          "     <td class=\"\"><b>"+rs.getString(14)+"</b></td>\n" +
                          "     <td class=\"\"><b>"+rs.getString(15)+"</b></td> \n" +
                          "     <td class=\"\"><b>"+rs.getString(16)+"</b></td>\n" +
                          "     <td class=\"\"><b>"+rs.getString(17)+"</b></td>\n" +
                          "     <td><b>"+rs.getString(21)+"</b></td>\n" +
                        //  "     <td><b><%=row4[0]%></b></td> \n" +
                          "     <td class=\"\"><b>"+rs.getString(18)+"</b></td>\n" +
                       //   "     <!-- <td class=\"\"><b><button type=\"button\" onclick=\"ModificarTransportista('"+rs.getString(3)+"','"+rs.getString(1)+"')\" class=\"btn btn-primary\">Editar</button></b></td>-->\n" +
                          "     <td class=\"\"><b><img src=\"img/moredetails.png\" width=\"40%\" style=\"cursor: pointer\"  onclick=\"muestraRepartos('"+rs.getString(3)+"','"+rs.getString(1)+"')\"/></b></td>\n" +
                        //  "     <td class=\"\"><b><img src=\"img/pdfflat.png\" width=\"20%\" style=\"cursor: pointer\"  onclick=\"muestraHP('"+rs.getString(3)+"')\"/></b></td>\n" +
                          "      " +
                          " </tr>"
                        + ""; 
                sal++;
            }
            
             salida += "</tbody></table>";
           // dao.CerrarConexion();
         //  dbConnection.
           
             
            if (sal > 0) {
                out.print(salida);

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
