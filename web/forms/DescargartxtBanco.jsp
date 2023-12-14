<%-- 
    Document   : DescargartxtBanco
    Created on : 13/10/2021, 03:35:10 PM
    Author     : grecendiz
--%>

<%@page import="java.io.FileInputStream"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
    try{
        
        
        
        
      String  nomFile =request.getParameter("nombre");// "bancos.alt";
        FileInputStream archivo = new FileInputStream("D:\\Servicios\\VFMX\\TXT\\"+nomFile );
       //  FileInputStream archivo = new FileInputStream("/Users/gerardorecendiz/Desktop/"+nomFile );
        int longitud = archivo.available();
        byte[] datos = new byte[longitud];
        archivo.read(datos);
        archivo.close();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename="+nomFile);
        ServletOutputStream ouputStream = response.getOutputStream();
        ouputStream.write(datos);
        ouputStream.flush();
        ouputStream.close();
      }catch(Exception e){ e.printStackTrace(); }  
    
    %>
    
    <body>
        <h1></h1>
    </body>
</html>
