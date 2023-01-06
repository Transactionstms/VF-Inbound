/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;

import com.dao.ServiceDAO;
import com.onest.oracle.OracleDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import javax.servlet.http.*;
import com.onest.oracle.*;


/**
 *
 * @author Luis Mateos
 */
public class Cosulta_Cartones_Registrados extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        HttpSession ownsession = request.getSession();
        DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
        Integer user_nid = Integer.valueOf(ownsession.getAttribute("login.user_id_number").toString());//ID de Usuario
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        OracleDB oraDB1 = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        OracleDB oraDB2 = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        OracleDB oraDB3 = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        OracleDB oraDB4 = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
   
        String Carton = request.getParameter("Carton"); 
        String destino = request.getParameter("Origen_destino"); 
        String distrito = request.getParameter("distrito"); 
        int intertienda= 3 ;
        
        oraDB.connect(dbData.getUser(), dbData.getPassword());
        oraDB1.connect(dbData.getUser(), dbData.getPassword());
        oraDB2.connect(dbData.getUser(), dbData.getPassword());
        oraDB3.connect(dbData.getUser(), dbData.getPassword());
        oraDB4.connect(dbData.getUser(), dbData.getPassword());
        
        
                                     // Validación de cartón existente/recibido en tienda por sesión!
        String Existencia_Tienda = " select tc.tra_caja, ae.edo_desc FROM tra_cajas tc INNER JOIN ontms_docto_sal ods ON tc.tra_caja_pedido = ods.docto_referencia INNER JOIN app_estado ae ON tc.estado_id = ae.edo_valor WHERE tc.tra_caja = '" + Carton + "' AND tc.estado_id in (1) AND ae.edo_tabla='TRA_CAJAS' AND tc.destino_transpaso='" + destino + "' ";
        boolean oraOut = oraDB.execute(Existencia_Tienda);

                if (oraOut) {
                    
                     out.print("1"); // Cartón ya ingresado en sistema!
                     oraDB.close();
                
                } else {             // Validación de cartón en tra_cajas
                                     
        String Existencia_Sistema = " SELECT DISTINCT tra_caja FROM tra_cajas WHERE tra_caja='" + Carton + "' ";
        boolean oraOut1 = oraDB1.execute(Existencia_Sistema);
        oraDB1.close();
        
                if (oraOut1) {
                                     // Validación de cartón por traspas de intertiendas
        String Traspaso_Intertiendas = " SELECT ESTADO_ID FROM TRA_CAJAS WHERE TRA_CAJA='"+ Carton + "' AND ESTADO_ID='" + intertienda + "' ";
        boolean oraOut2 = oraDB2.execute(Traspaso_Intertiendas);
        oraDB2.close();
        
                if (oraOut2) {       // Update para traspasos de intertiendas!
                    
        String UpIntertiendas = " UPDATE tra_cajas SET DESTINO_TRANSPASO = '" + destino + "', TRA_TIENDA_REFERENCIA = '" + distrito + "', estado_id = 1, fecha_ingreso_tienda= sysdate, user_nid='" + user_nid + "', url_poc = NULL WHERE tra_caja = '" + Carton + "' ";
        boolean oraOut3 = oraDB3.execute(UpIntertiendas);     
        oraDB3.close(); 
        
                } else {             // Update para cartones a recibir por 1era vez!
        
        String UpNormal = " UPDATE tra_cajas SET ORIGEN_TRANSPASO = 10098075,  DESTINO_TRANSPASO = '" + destino + "', TRA_TIENDA_REFERENCIA = '" + distrito + "', estado_id = 1, fecha_ingreso_tienda= sysdate, user_nid='" + user_nid + "' WHERE tra_caja = '" + Carton + "' ";
        boolean oraOut4 = oraDB4.execute(UpNormal);     
        oraDB4.close();    
        
                }  
            
        //Llenar Información en tabla
        String MostrarDatos = " select distinct               "
                + " tc.tra_caja,                              "
                + " ods.docto_programacion,                   "
                + " tc.tra_caja_cantidad,                     "
                + " tc.tra_caja_pedido,                       "
                + " ae.edo_desc                               "
                + " FROM                                      "
                + " tra_cajas tc                              "
                + " INNER JOIN                                "
                + " ontms_docto_sal ods                       "
                + " ON                                        "
                + " tc.tra_caja_pedido = ods.docto_referencia "
                + " INNER JOIN                                "
                + " app_estado ae                             "
                + " ON                                        "
                + " tc.estado_id = ae.edo_valor               "
                + " WHERE                                     "
                + " tc.tra_caja = '" + Carton + "'            "
                + " AND                                       "
                + " tc.estado_id in (1)                       "
                + " AND                                       "
                + " ae.edo_tabla='TRA_CAJAS'                  ";

        ServiceDAO dao = new ServiceDAO();
        ResultSet rs = dao.consulta(MostrarDatos);
        int sal = 0;
            String salida = "\n";
            while (rs.next()) {

                // Parametros generados mediante la consulta
                String Cartones = rs.getString(1);
                String Bill_of_Landing = rs.getString(2);
                String Cantidad = rs.getString(3);
                String Pedido = rs.getString(4);
                String Estatus = rs.getString(5);

                salida += " <tr id=\"tr\" + sal + \"\"> "
                        + " <td name=\"Cartones\" id=\"Cartones\" align=\"center\"><center>" + Cartones + "</center></td> "
                        + " <td name=\"Bill_of_Landing\" id=\"Bill_of_Landing\" align=\"center\"><center>" + Bill_of_Landing + "</center></td> "
                        + " <td name=\"Cantidad\" id=\"Cantidad\" align=\"center\"><center>" + Cantidad + "</center></td> "
                        + " <td name=\"Pedido\" id=\"Pedido\" align=\"center\"><center>" + Pedido + "</center></td> "
                        + " <td name=\"Estatus\" id=\"Estatus\" align=\"center\"><center>" + Estatus + "</center></td> "
                        + " <td><center><a class=\"btn btn-info\" onclick=\"confirmar2('" + Cartones + "')\"  id=\"El0\"><i id=\"iconE0\"  class=\"glyphicon glyphicon-comment\"></i></a></center></td> "
                        + " </tr> ";
                ;
                sal++;

            }
            dao.CerrarConexion();
            //salida+="                                </tbody>";
            if (sal > 0) {
                out.print(salida);

            } else {
                out.print("No se encontraron datos");

            }
          } else {
           out.print("2"); //Cartón no existe en tra_cajas!
        }
       } //Cierre de llave principal
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
