/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.insertar.catalogos;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.onest.oracle.*;
import javax.servlet.http.HttpSession;

public class InsertarBrokerCuenta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession ownsession = request.getSession(false);
            DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
            OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
            try {
                String cuenta1 = request.getParameter("cuenta");
                String bodega1 = request.getParameter("bodega");
                oraDB.connect(dbData.getUser(), dbData.getPassword());
                String cuenta = "insert into broker_cuenta (cuenta_id,cuenta_nombre,bodega_id,cuenta_valida_lote,cuenta_valida_identificador,"
                        + " cuenta_valida_caducidad,secuencia_embarque,secuencia_rechazo,secuencia_evidencia,secuencia_liquidacion,secuencia_cargo,"
                        + " secuencia_interno,prefijo_embarque,prefijo_rechazo,prefijo_evidencia,prefijo_liquidacion,prefijo_cargo,prefijo_interno,"
                        + " secuencia_consolidacion,prefijo_consolidacion,secuencia_clienter,prefijo_clienter,secuencia_planeacion,prefijo_planeacion,"
                        + " prefijo_liquidaciong,secuencia_liquidaciong,prefijo_paqueteria,secuencia_paqueteria)"
                        + " values ( null, '" + cuenta1 + "', " + bodega1 + ",1,0,0,0,0,0,0,0,0,'EM','RE','EV','LI','CG','IN',0,'CO',0,'CR',0,'PL','LQ',0,'PA',0) ";
                boolean oraOut1 = oraDB.execute(cuenta);
                out.println("<form action='" + request.getContextPath() + "/forms1/alta_broker_cuenta.jsp' name='proceso'>");
                out.println("<table align='center'><tr>");
                if (oraOut1) {
                    out.println("<td><h3>Registro insertado</h3></td>");
                } else {
                    out.println("<td><h3>Registro no insertado</h3></td>");
                }
                out.println("</tr><tr><td><a href='" + request.getContextPath() + "/forms/main.jsp' style='text-decoration:none'><input type='button' name='Captura' value='Inicio'/></a>");
                out.println("<input type='submit' value='Insertar'></td></tr></table></form>");
            } catch (Exception e) {
                out.println("Error " + e.toString());
            } finally {
                oraDB.close();
            }
        } catch (NullPointerException e) {
            out.println(e.toString());
            out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            out.println("<script>window.close();</script>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";

    }
}
