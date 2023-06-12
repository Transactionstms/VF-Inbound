/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.insertar.catalogos;

import com.onest.misc.Cuenta;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis_
 */
public class InsertarEvidencia extends HttpServlet {

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
        HttpSession ownsession = request.getSession(false);
        try {
            DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
            OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
            try {
                Cuenta[] cta = (Cuenta[]) ownsession.getAttribute("login.user_cuenta");
                String ctaId = cta[(Integer.parseInt((String) ownsession.getAttribute("login.too.many.accounts")) > 1) ? Integer.parseInt(request.getParameter("indexCta")) : 0].getIdCta();
                String ti = request.getParameter("tipo");
                String agrupador = request.getParameter("agrupador"), a = "";
                String fecha = request.getParameter("fecha");
                String ob = request.getParameter("observaciones");
                String folio = request.getParameter("folio");
                String contador = request.getParameter("contador");
                String var = "", var1 = "", var2 = "";
                int ban = 0;
                oraDB.connect(dbData.getUser(), dbData.getPassword());
                out.println("<form action='" + request.getContextPath() + "/"+request.getParameter("pag")+"' name='proceso'>");
                    out.println("<table align='center'><tr>");
                if (ti.equals("1")) {
                    String oracleQuery = "BEGIN ? := ONTMS_PKG.ONTMS_FN_EVIDENCIA_GUIA('" + agrupador + "'," + ctaId + ",to_date('" + fecha + "','dd/MM/yyyy'),'" + ob + "','" + folio + "'); END;";
                    String[] outParam = new String[]{""};
                    String[] inParam = new String[]{};
                    String oraOut = oraDB.execStore(oracleQuery, outParam, inParam, new int[]{OracleDB.ORA_VARCHAR});
                    
                    if (oraOut.equals("0")) {
                        out.println("<td><h3>Registro insertado</h3></td>");
                    } else {
                        out.println("<td><h3>Registro no insertado</h3></td>");
                    }
                    
                } else if (ti.equals("2")) {
                    for (int i = 1; i <= Integer.parseInt(contador); i++) {
                        var = request.getParameter("chec" + i);
                        if (var == null) {
                        } else {
                            var1 = request.getParameter("id" + i);
                            var2 += "'" + var1 + "',";
                            a = var1;
                            ban = 1;
                        }
                    }
                    if (ban == 1) {
                        var2 = var2.substring(0, var2.length() - 1);
                    }
                    String choferCamion = "UPDATE ontms_docto_sal SET docto_folio_evidencia='" + a + "' WHERE docto_sal_id IN (" + var2 + ")";
                    if (oraDB.execute(choferCamion)) {
                        String oracleQuery = "BEGIN ? := ONTMS_FN_EVIDENCIA_PAQ('" + a + "'," + ctaId + ",'" + fecha + "','" + ob + "','" + folio + "'); END;";
                        String[] outParam = new String[]{""};
                        String[] inParam = new String[]{};
                        String oraOut = oraDB.execStore(oracleQuery, outParam, inParam, new int[]{OracleDB.ORA_VARCHAR});                       
                        out.println("<table align='center'><tr>");
                        if (oraOut.equals("0")) {
                            out.println("<td><h3>Registro insertado</h3></td>");
                        } else {
                            out.println("<td><h3>Registro no insertado</h3></td>");
                        }
                    } else {
                        out.println("<font color=red>Ocurrio un error</font>");
                    } 

                }
                     out.println("</tr><tr><td><a href='" + request.getContextPath() + "/forms/main.jsp' style='text-decoration:none'><input type='button' name='Captura' value='Inicio'/></a>");
                    out.println("<input type='submit' value='Evidencia'></td></tr></table></form>");
            } catch (Exception e1) {
                out.println("Error " + e1.toString());
            } finally {
                oraDB.close();
            }
        } catch (NullPointerException e) {
            out.println("<script>alert('Se termino la session'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            out.println("<script>window.close();</script>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
