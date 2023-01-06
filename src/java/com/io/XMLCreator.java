package com.io;

import com.io.XML;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * com.io.XMLCreator
 * @author Sistemas
 */
public class XMLCreator extends HttpServlet {

    public static final long serialVersionUID = 24362462L;
    private String xmlStr = "";
    PrintWriter out;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ownsession = request.getSession(false);

        this.xmlStr = "";
        String tmp = "";
        String query = request.getParameter("sqlQuery");        
        String plain = request.getParameter("plain");
        String cols = request.getParameter("cols");
        String type = request.getParameter("tipo");
        boolean oraExec = false;
        com.onest.oracle.DBConf dbData;
        if ((request.getParameter("security") != null) && (request.getParameter("security").equals("1"))) {
            dbData = new com.onest.oracle.DBConf();
            dbData.setConf();
        } else {
            dbData = (com.onest.oracle.DBConfData) ownsession.getAttribute("db.data");
        }
        PrintWriter out = response.getWriter();
        response.setContentType("text/xml;");

        response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        out.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n");
        String[] querys = query.split(";");
        if(querys.length>1){
            out.append("<querys>");
            for(String next : querys){
                out.append("<query>");
                XML xmlGetter = new XML(next, dbData, request.getParameter("titulo"), "true".equalsIgnoreCase(plain),cols,type);
                out.append(xmlGetter.getXML());
                out.append("</query>");
            }
            out.append("</querys>");
        } else if(querys.length<=1){
          XML xmlGetter = new XML(query, dbData, request.getParameter("titulo"), "true".equalsIgnoreCase(plain),cols,type);
          out.append(xmlGetter.getXML());   
        }
    }

    public String getServletInfo() {
        return "Short description";
    }
}