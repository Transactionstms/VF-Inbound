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

public class InsertarPresupuesto extends HttpServlet {

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
                String cuenta1 = request.getParameter("cbdivcuenta");
                String pres = request.getParameter("pres");
                String mes=request.getParameter("mes");
                String anio=request.getParameter("anio");
                String resp=request.getParameter("resp");
                String correo=request.getParameter("correo");
                
                //out.println(correo);
                oraDB.connect(dbData.getUser(), dbData.getPassword());
                String cuenta ="INSERT INTO ontms_presupuestos values ( null, " + cuenta1 + ", " +mes + ","+anio+","+pres+",'"+resp+"','"+correo+"')";
                //out.println(cuenta);
                out.println("<table align='center'><tr>");
                if (oraDB.execute(cuenta)) {
                    out.println("<td><h3>Registro insertado</h3></td>");
                } else {
                    out.println("<td><h3>Registro no insertado</h3></td>");
                }
                out.println("</tr><tr><td><a href='" + request.getContextPath() + "/forms/main.jsp' style='text-decoration:none'><input type='button' name='Captura' value='Inicio'/></a>");
                //out.println("<input type='submit' value='Insertar'></td></tr></table></form>");
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
