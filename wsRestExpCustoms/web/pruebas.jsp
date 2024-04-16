<%-- 
    Document   : pruebas
    Created on : 31-may-2022, 12:15:34
    Author     : grece
--%>

<%@page import="com.tacts.rest.Medir"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
        Medir medir=new Medir(); 
        double tt=medir.distanciaCoord(22.406200, -97.895800, 19.667200, -99.199000);
        
      medir.escribir("31-May-2022 18:45:08.370 INFO [http-nio-8084-exec-380] org.apache.catalina.core.StandardContext.reload Se ha completado la recarga de este Contexto",
              "imei001");
 
        %>
    </head>
    <body>
        <h1><%=tt%></h1>
    </body>
</html>
