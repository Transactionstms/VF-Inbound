<%-- 
    Document   : seleccionPlantilla
    Created on : 24-oct-2023, 18:23:18
    Author     : grecendiz
--%>

<%@page import="java.util.Date"%>
<%@page import="com.pantalla.BLPantalla"%>
<%@page import="com.pantalla.Pantalla"%>
<%@page import="com.usuario.Usuario"%>
<%@page import="com.usuario.Usuario"%>
<%@page import="com.onest.train.consultas.Consulta"%>
<%@page contentType="text/html;charset=iso-8859-1"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ page import="com.onest.oracle.*" %>
<%@ page import="com.onest.misc.*" %>
<%@ page import="com.onest.net.*" %>
<!DOCTYPE html>  
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" sizes="16x16" href="../plugins/images/favicon.png">
        <title>TransactionsTMS</title>
        <!-- Bootstrap Core CSS
         <link rel="stylesheet" href="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.css">
    <link rel="stylesheet" href="../lib/vendor/prismjs/themes/prism-okaidia.css">
        -->


        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
        <link rel="stylesheet" href="../lib/css/custom.css">
        <script src="lib/JSplantilla.js" type="text/javascript"></script>


    </head>
    <body class="fix-sidebar">
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String idPlantilla = request.getParameter("idPlantilla");
                String idDivision = ownsession.getAttribute("cbdivcuenta").toString();
                String idBodega = ownsession.getAttribute("cbbodegaId").toString();
                Usuario root = (Usuario) ownsession.getAttribute("login.root");
             
        %>

        <!-- Preloader -->



        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <!-- Page Header-->
                    <div class="page-header">
                        <h1 >PLANTILLAS</h1>
                    </div>

                    <section>
                        <div class="card mb-4">
                            <div class="card-header">
                                <div class="card-heading">*Selecciona la plantilla</div>
                            </div>
                            <div class="card-body text-muted">
                                <!-- Selección de Clientes -->
                                <div class="row">


                                    <div class="col-lg-12">
                                        <div class="mb-3">  
                                            <div class="row">

                                                <div class="col-lg-3">
                                                </div> 

                                                <div class="col-md-6 col-lg-3 mb-4">
                                                    <div class="card h-100" style="width: 600px;">
                                                        <div class="card-header py-4">
                                                            <h6 class="card-heading">               </h6>
                                                        </div>





                                                        <div class="card-body pt-3">

<a type="button" class="btn btn-primary"  href="./plantilla.jsp?idPlantilla=25">   PLANTILLA  CUSA</a>
<a type="button" class="btn btn-info"     href="./plantilla.jsp?idPlantilla=26">   PLANTILLA  GENERAL</a>
<a type="button" class="btn btn-success"   href="./plantilla.jsp?idPlantilla=24">  PLANTILLA  LOGIX</a> 


                                                          
                                                        </div>
                                                    </div>
                                                </div>
  
 

                                            
                                           
                                          
                                            </div> 
                                        </div>
                                    </div>
                                </div>  
                            </div>
                        </div> 
                    </section>
                </div> 
            </div>
        </div> 
    </div>
 
 
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
      <script src="<%=request.getContextPath()%>/plantillas/lib/upload_file.js" type="text/javascript"></script>

 </body>



<%                } catch (NullPointerException e) {
        out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
        out.println("<script>window.close();</script>");
        out.println(e);
    } catch (Exception e) {
        out.println("Excepcion revise por favor! " + e);
        e.printStackTrace();
    }
%>
</html>