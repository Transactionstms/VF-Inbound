/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.security;

import com.onest.misc.Calendario;
import com.onest.misc.Cuenta;
import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.security.AlarmaBean;
import com.onest.train.security.AlarmaService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sistemas
 */
public class PermitirIngreso extends HttpServlet {

    private Connection connection;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession ownsession = request.getSession(false);
            DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
            OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
            try {
                //DB db = new DB((DBConfData) dbData);
                Cuenta[] cta = (Cuenta[]) ownsession.getAttribute("login.user_cuenta");
                String ctaId = cta[(Integer.parseInt((String) ownsession.getAttribute("login.too.many.accounts")) > 1) ? Integer.parseInt(request.getParameter("indexCta")) : 0].getIdCta();
                String userIDNumber = (String) ownsession.getAttribute("login.user_id_number");
                String fecha = request.getParameter("f_citado") + " " + request.getParameter("hora3") + ":" + request.getParameter("hora31");
                oraDB.connect(dbData.getUser(), dbData.getPassword());
                AlarmaBean beanControl = (AlarmaBean) ownsession.getAttribute("login.controlUsuario");
                String comando = request.getParameter("comando");
                
                
                if (beanControl != null) {
                    out.println("OK");
                    new AlarmaService().execute(beanControl, "0".equals(comando));
                }
                
                else{
                out.println("ERROR");
                }

                


            } catch (Exception e) {
                out.println("Error " + e.toString());
            } finally {
                oraDB.close();
            }
        } catch (NullPointerException e) {
            out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            out.println("<script>window.close();</script>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";


    }
}
