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
         Statement statement2 = generico.getConnection().createStatement();
                   
         String sqlold = "WITH sum_quantity AS ("
        +"   SELECT shipment_id, container1, SUM(quantity) AS suma"
        +"   FROM tra_inc_gtn_test"
        +"   GROUP BY shipment_id, container1"
        +" )"
        +" SELECT DISTINCT"
        +"   tie.id_evento,"
        +"   NVL(bp.responsable, ' ') AS responsable,"
        +"   gtn.final_destination,"
        +"   tibd.nombre_bd,"
        +"   nvl(tid.division_nombre,' '), "
        +"   gtn.shipment_id,"
        +"   gtn.container1,"
        +"   gtn.bl_awb_pro,"
        +"   gtn.LOAD_TYPE_FINAL,"
        +"   sq.suma,"
        +"   tip1.NOMBRE_POD,"
        +"   TO_CHAR(gtn.est_departure_pol, 'MM/DD/YY') AS est_departure_pol,"
        +"   TO_CHAR(gtn.eta_port_discharge, 'MM/DD/YY') AS eta_real_port,"
        +"   NVL(gtn.max_flete, 0) AS est_eta_dc,"
        +"   'Inbound notification' AS notification_type,"
        +"   tip2.NOMBRE_POL,"
        +"   NVL(taa.agente_aduanal_nombre, ' ') AS agente_aduanal,"
        +"   gtn.plantilla_id,"
        +"   TO_CHAR(gtn.fecha_captura, 'MM/DD/YY') AS fecha_captura,"
        +"   tip1.nombre_pod,"
        +"   tip2.nombre_pol,"
        +"   gtn.brand_division,"
        +"   CASE"
        +"     WHEN gtn.load_type = 'LTL' THEN 'LTL'"
        +"     WHEN EXISTS ("
        +"       SELECT 1"
        +"       FROM tra_inc_gtn_test"
        +"       WHERE container1 = gtn.container1"
        +"       HAVING COUNT(DISTINCT brand_division) > 1"
        +"     ) THEN 'FCL / LCL'"
        +"     WHEN gtn.load_type = 'FCL' THEN 'FCL'"
        +"     WHEN gtn.load_type = 'LCL' THEN 'LCL'"
        +"     ELSE '-'"
        +"   END AS estado,"
        +"   NVL(TO_CHAR(gtn.eta_plus2, 'MM/DD/YY'), ' ') AS eta_dc,"
        +"   NVL(TO_CHAR(gtn.eta_plus, 'MM/DD/YY'), ' ') AS eta_dc1,"
        +"   NVL(tie.observaciones, ' ') AS observaciones,"
                 + " gtn.EMBARQUE_AGRUPADOR "
        +"    "
        +" FROM "
        +"   tra_inb_evento tie"
        +"   LEFT JOIN tra_destino_responsable bp ON bp.user_nid = tie.user_nid"
        +"   INNER JOIN tra_inc_gtn_test gtn ON gtn.plantilla_id = tie.plantilla_id"
        +"   LEFT JOIN tra_inb_pod tip1 ON tip1.id_pod = gtn.pod"
        +"   LEFT JOIN tra_inb_pol tip2 ON tip2.id_pol = gtn.pol"
        +"   LEFT JOIN tra_inb_brand_division tibd ON tibd.id_bd = gtn.brand_division"
        +"   LEFT JOIN tra_inb_agente_aduanal taa ON taa.agente_aduanal_id = tip1.agente_aduanal_id"
        +"   LEFT JOIN tra_inb_division tid ON tid.id_division = gtn.sbu_name"
        +"   LEFT JOIN sum_quantity sq ON sq.shipment_id = gtn.shipment_id AND sq.container1 = gtn.container1"
      + " where   gtn.EMBARQUE_AGRUPADOR in( select DISTINCT EMBARQUE_AGRUPADOR from TRA_INB_EMBARQUE where EMBARQUE_TRANSPORTISTA="+transportista+")"
        +" ORDER BY"
        +"   tie.id_evento";
         
         String sql = " "
                 + "select DISTINCT EMBARQUE_ID,EMBARQUE_AGRUPADOR,TO_CHAR(EMBARQUE_FEC_ENRAMPE, 'MM/DD/YY'),TO_CHAR(EMBARQUE_FEC_INICIO, 'MM/DD/YY')"
                 + " from TRA_INB_EMBARQUE where EMBARQUE_ESTADO_ID is null and EMBARQUE_TRANSPORTISTA="+transportista
                 + "";
          System.out.println(sql); 
    
       String sql2="select  LTRANSPORTE_ID, LTRANSPORTE_NOMBRE from tra_inb_linea_transporte where LTRANSPORTE_ID="+transportista;
       ResultSet rs2 = statement2.executeQuery(sql2);
        
        try (PrintWriter out = response.getWriter()) {
            String ntransporte="",idtransporte="";
            
            while (rs2.next()) { 
                ntransporte =rs2.getString(1);
                idtransporte=rs2.getString(2);
                System.out.println("---"+rs2.getString(2));
            }
            
            int sal = 0;
               ResultSet rs = statement.executeQuery(sql);

            String salida = ""
                    + " <table id=\"myTable\" class=\"display nowrap\" cellspacing=\"0\" width=\"100%\">\n" +
"                                                                    <thead class=\"thead-dark\">\n" +
"                                                                        <tr>" +
"                                                                            <th>Embarque                 </th>\n" +
"                                                                            <th>Fecha de enrampe         </th>\n" +
"                                                                            <th>Fecha inicio de entrega  </th>\n" +
                    
 "                                                                           <th>Modificar      </th>\n" + 
 "                                                                           <th>Opciones       </th>\n" + 
                    
"                                                                        </tr>" + 
"                                                                    </thead> " +
"                                                                    <tbody>"
                    + "";
            
              String salida2 = ""
                    + " <table id=\"myTable\" class=\"display nowrap\" cellspacing=\"0\" width=\"100%\">\n" +
"                                                                    <thead class=\"thead-dark\">\n" +
"                                                                        <tr>" +
"                                                                            <th>Evento        </th>\n" +
"                                                                            <th>Container #   </th>\n" +
"                                                                            <th>BL            </th>\n" +
                    
 "                                                                           <th>Shipment      </th>\n" +                   
"                                                                            <th>Load Type     </th>\n" + 
"                                                                            <th>LUM BRIO      </th>\n" +
                    
"                                                                            <th>Brand         </th>\n" +
"                                                                            <th>Sbu Name      </th>\n" + 
"                                                                            <th>MX Port       </th>\n" +
                    
"                                                                            <th>ETA MX Por    </th>\n" +
 "                                                                           <th>ETA DC        </th>\n" + 
 "                                                                           <th>Opciones       </th>\n" + 
                    
"                                                                        </tr>" + 
"                                                                    </thead> " +
"                                                                    <tbody>"
                    + "";
            while (rs.next()) {  
                 System.out.println("---**"+rs.getString(1));
               // salida2 += ""
               //         + " <tr>\n" +
               //           "     <td class=\"\"><b>"+rs.getString(1)+"</b></td> \n" +
               //           "     <td class=\"\"><b>"+rs.getString(7)+"</b></td> \n" +
               //           "     <td class=\"\"><b>"+rs.getString(8)+"</b></td>\n" +
               //         
               //           "     <td class=\"\"><b>"+rs.getString(6)+"</b></td> <!--Cajas-->\n" +
               //           "     <td class=\"\"><b>"+rs.getString(9)+"</b></td>\n" + 
               //           "     <td class=\"\"><b>"+rs.getString(10)+"</b></td> \n" +
               //         
               //           "     <td class=\"\"><b>"+rs.getString(4)+"</b></td>\n" + 
               //           "     <td class=\"\"><b>"+rs.getString(5)+"</b></td>\n" +
               //           "     <td><b>"+rs.getString(3)+"</b></td>\n" +
               //         
               //           "     <td class=\"\"><b>"+rs.getString(24)+"</b></td>\n" +
               //           "     <td class=\"\"><b>"+rs.getString(25)+"</b></td>\n" +
               //           "     <td class=\"\"><b><img src=\"img/moredetails.png\" width=\"40%\" style=\"cursor: pointer\"  onclick=\"muestraRepartos('"+rs.getString(27)+"','"+ntransporte+"','"+idtransporte+"')\"/></b></td>\n" +
               //           "      " +
               //           " </tr>"
               //         + ""; 
                
                 salida += ""
                        + " <tr>\n" +
                          "     <td class=\"\"><b>"+rs.getString(1)+"</b></td> \n" +
                          "     <td class=\"\"><b>"+rs.getString(3)+"</b></td> \n" +
                          "     <td class=\"\"><b>"+rs.getString(4)+"</b></td>\n" +
                       "     <td class=\"\"> <button type=\"button\" class=\"btn btn-primary\" onclick=\"muestraRepartos('"+rs.getString(2)+"','"+ntransporte+"','"+idtransporte+"','"+rs.getString(3)+"','"+rs.getString(4)+"')\">Modificar</button> </td>\n" +
                       "     <td class=\"\"><b><img src=\"img/moredetails.png\" width=\"40%\" style=\"cursor: pointer\"  onclick=\"muestraRepartos2('"+rs.getString(2)+"','"+ntransporte+"','"+idtransporte+"','"+rs.getString(3)+"','"+rs.getString(4)+"')\"/></b></td>\n" +
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
            statement.close();
            statement2.close();
             rs.close();
             rs2.close();
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
