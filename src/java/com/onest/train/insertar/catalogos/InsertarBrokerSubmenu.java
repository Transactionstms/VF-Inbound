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

public class InsertarBrokerSubmenu extends HttpServlet {

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
                String etiqueta = request.getParameter("etiqueta");
                String ruta = request.getParameter("ruta");
                String owner = request.getParameter("owner");
                String menu = request.getParameter("menu");
                String grupo = request.getParameter("grupo");
                String r = request.getParameter("r");
                String w = request.getParameter("w");
                String x = request.getParameter("x");
                String r1 = request.getParameter("r1");
                String w1 = request.getParameter("w1");
                String x1 = request.getParameter("x1");
                String r2 = request.getParameter("r2");
                String w2 = request.getParameter("w2");
                String x2 = request.getParameter("x2");

                oraDB.connect(dbData.getUser(), dbData.getPassword());
                String uno = r + w + x;
                String dos = r1 + w1 + x1;
                String tres = r2 + w2 + x2;
                String uno1 = uno.replaceAll("null", "-");
                String dos2 = dos.replaceAll("null", "-");
                String tres3 = tres.replaceAll("null", "-");

                String submenu = " insert into broker_submenu values (null, '" + etiqueta + "', '" + ruta + "', " + menu + ",'" + owner + "', " + grupo + ", '" + uno1 + "', '" + dos2 + "', '" + tres3 + "')";
                boolean oraOut1 = oraDB.execute(submenu);
                out.println("<form action='" + request.getContextPath() + "/forms1/alta_broker_submenu.jsp' name='proceso'>");
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
