package com.transactions.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import com.onest.oracle.*;
import java.sql.ResultSet;
 import java.text.ParseException;  
 import java.text.SimpleDateFormat;  
 import java.util.Date;  

public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
   
        try {
            HttpSession ownsession = request.getSession(false);
            DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
            OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
            try {
                String user = request.getParameter("user1");
                String password = request.getParameter("password1");
                
                        oraDB.connect(dbData.getUser(), dbData.getPassword());
                        String Cita = "select BROKER_PASSWD (USER_ID,USER_EMAIL) values('" + user+ "','" + password + "')";
                        boolean oraOut = oraDB.execute(Cita);
                        out.println("<form action='" + request.getContextPath() + "/login.jsp' name='proceso'>");
                        out.println("<table align='center'><tr>");
                        if (oraOut) {
                            out.println("<td><font face=\"arial\"  size=4 color=\"blue\">La sesion es exitosa</font></td>");
                        } else {
                            out.println("<td><font face=\"arial\"  size=4 color=\"red\">Ingres nuevamente tus datos/font></td>");
                        }
                         out.println("</tr><tr><td><a href='" + request.getContextPath() + "/CitasTMS/Citas.jsp' style='text-decoration:blue'><input type='button' name='Captura' value='Regresar'/></a>");
                        out.println("<input type='submit' value='Insertar'></td></tr></table></form>");
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
