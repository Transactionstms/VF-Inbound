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
        <title>Subir CFDI</title> 
        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">

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
                int usr = root.getId();

                String op = request.getParameter("op");


        %>

        <!-- Preloader -->



        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <!-- Page Header-->


                    <section>
                        <div class="card mb-4">


                            <div class="card-header">
                                <div class="card-heading">SUBIR CFDI - <%=op%></div>
                            </div>
                            <div class="card-body text-muted">
                                <!-- Selección de Clientes -->
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="mb-3">  
                                            <div class="row">

                                                <div class="col-lg-3">
                                                </div> 

                                                <%
                                                    if (db.doDB(" select EMBARQUE_ID from tra_inb_embarque where EMBARQUE_ID='" + op + "' ")) {
                                                        for (String[] row : db.getResultado()) {
                                                %>
                                                 <form id="miform1" method="post" enctype="multipart/form-data">  
                                                <div class="col-md-6 col-lg-3 mb-4">
                                                    <div class="card h-100">
                                                        <div class="card-body pt-3">
                                                            <div class="mb-3">
                                                                <label for="input-id" class="form-label">Selecciona </label>
                                                                <input class="form-control" type="file"  name="input-id" id="input-id" required="true"
                                                                       accept=".pdf">
                                                            </div> 
                                                            <div class="row position-relative" style="top: 10px;">
                                                                <div class="col-6 text-center">
                                                                    <button  class="btn btn-info btn-lg" type="button" onclick="enviarForm();"> Aceptar</button> <!---->

                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>                          
                                            </form>
                                                <%
                                                        }
                                                    }else{
                                                %>

                                               <div class="col-md-6 col-lg-3 mb-4">
                                                    <div class="card h-100">
                                                        <div class="card-body pt-3">
                                                            <h1>No se encontro el embarque</h1>                                                           
                                                        </div>
                                                    </div>
                                                </div>  
                                                
                                                
                                                <%
                                                  } 
                                                %>
                                                
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
     



</body>

 <script>
                        
                        
                         function enviarForm() {
                         
                           
                         let formulario  = document.getElementById("miform1");
                             formulario.setAttribute('action', '<%=request.getContextPath()%>/Logistica/subircfdi.jsp?documento=<%=op%>');

                             formulario.submit();
                             console.log('asd2')
                             
                         }
                        
                    </script>  

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