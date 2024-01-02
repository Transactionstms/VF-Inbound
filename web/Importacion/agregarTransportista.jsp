<%-- 
    Document   : agregarTransportista
    Created on : Apr 5, 2023, 2:37:22 PM
    Author     : dan_i
--%>


<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.onest.train.consultas.ConsultasQuery"%>
<%@page import="com.onest.security.menu.*" %>
<%@page import="com.onest.net.*" %>
<%@page import="com.onest.oracle.*" %>
<%@page import="com.onest.misc.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Date date = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    String fecha = formato.format(date);
    String[] par = fecha.split("/");
    String part3 = par[2];

    //Extracción de Hora   
    Date hr = new Date();
    DateFormat hours = new SimpleDateFormat("HH:mm:ss");
    String hora = hours.format(hr);


%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Agregar Transportista</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">
        <!-- Google fonts - Popppins for copy-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&amp;display=swap" rel="stylesheet">
        <!-- Prism Syntax Highlighting-->
        <link rel="stylesheet" href="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.css">
        <link rel="stylesheet" href="../lib/vendor/prismjs/themes/prism-okaidia.css">
        <!-- The Main Theme stylesheet (Contains also Bootstrap CSS)-->
        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
        <!-- Custom stylesheet - for your changes-->
        <link rel="stylesheet" href="../lib/css/custom.css">
        <!-- Favicon-->
        <link rel="shortcut icon" href="../lib/img/favicon.png">
        <!-- Table css -->
        <link href="../lib/inbound/eventos/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- Connection Status Red -->
        <link href="../lib/inbound/conexion/connectionStatus.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

    </head>
    <%        try {
            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));

    %>

    <body>



        <!-- navbar-->
        <header class="header"></header>

        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <section>
                        <div class="row">
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card h-100">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h2 class="card-heading">Agregar Transportista</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body " style="auto">
                                        <div class="col-md-12">

                                            <div class="form-group" >
                                                <div class="row">

                                                    <div class="col-md-6 mb-4">
                                                        <label class="form-label">Nombre</label>
                                                        <input type="text" class="form-control" id="nombreTrans" name="nombreTrans"  >
                                                    </div>
                                                    <div class="col-md-6 mb-4">
                                                        <label class="form-label">Razon Social</label>
                                                        <input type="text" class="form-control" id="raSo" name="raSo"  >
                                                    </div>
                                                    <div class="col-md-6 mb-4"> 
                                                        <label class="form-label">Dirección</label>
                                                        <input type="text" class="form-control" id="direccion" name="direccion"  >
                                                    </div>
                                                    <div class="col-md-6 mb-4">
                                                        <label class="form-label">RFC</label>
                                                        <input type="text" class="form-control" id="rfc" name="rfc"  >
                                                    </div>
                                                    <div class="col-md-6 mb-4">
                                                        <label class="form-label">Telefono</label>
                                                        <input type="text" class="form-control" id="telefono" name="telefono"  >
                                                    </div>
                                                    <div class="col-md-6 mb-4">
                                                        <label class="form-label">Correo 1</label>
                                                        <input type="text" class="form-control" id="correoUno" name="correoUno"  >
                                                    </div>
                                                    <div class="col-md-6 mb-4"> 
                                                        <label class="form-label">Correo 2</label>
                                                        <input type="text" class="form-control" id="correoDos" name="correoDos"  >
                                                    </div>
                                                    <div class="col-md-6 mb-4">
                                                        <label class="form-label">Correo 3</label>
                                                        <input type="text" class="form-control" id="correoTres" name="correoTres"  >
                                                    </div>
                                                    <div class="col-md-6 mb-4">
                                                        <label class="form-label">Correo 4</label>
                                                        <input type="text" class="form-control" id="correoCuatro" name="correoCuatro"  >
                                                    </div>

                                                </div>
                                            </div>

                                            <button class="btn btn-primary text-nowrap" type="button"  onclick="registrarDatos()">Enviar</button>

                                            <br>
                                            <!-- Botones controles -->

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>   
                    </section>
                </div>  
                <footer class="footer bg-white shadow align-self-end py-3 px-xl-5 w-100">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-6 text-center text-md-start fw-bold">
                                <p class="mb-2 mb-md-0 fw-bold">Transactions TMS &copy; <%=part3%></p>
                            </div>
                            <div class="col-md-6 text-center text-md-end text-gray-400">
                                <p class="mb-0">Version 1.1.0</p>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>    

        <script>
            function registrarDatos() {
                let correoUno = document.getElementById("correoUno").value;
                let correoDos = document.getElementById("correoDos").value;
                let correoTres = document.getElementById("correoTres").value;
                let correoCuatro = document.getElementById("correoCuatro").value;
                let telefono = document.getElementById("telefono").value;
                let rfc = document.getElementById("rfc").value;
                let direccion = document.getElementById("direccion").value;
                let raSo = document.getElementById("raSo").value;
                let nombreTrans = document.getElementById("nombreTrans").value;

                fetch("<%=request.getContextPath()%>/AgregarTransportista?nombreTransJava=" + nombreTrans + "&raSoJava=" + raSo + "&direccionJava=" + direccion + "&rfcJava=" + rfc + "&telefonoJava=" + telefono + "&correoUnoJava=" + correoUno + "&correoDosJava=" + correoDos + "&correoTresJava=" + correoTres + "&correoCuatroJava=" + correoCuatro, {
                    method: 'POST',
                }).then(r => r.text())
                        .then(data => {
                            console.log(data);
                            console.log(r);
                            if (data === "true") {
                                alert("Registro Exitoso!");
                            } else {
                                alert("Registro no Exitoso", "error");

                            }
                            alertclose();
                            // location.reload();
                        }).catch(error => console.log(error));
            }
        </script>

        <!-- Conexión estatus red -->                    
        <script src="../lib/inbound/conexion/connectionStatus.js" type="text/javascript"></script>


        <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.1/js/bootstrap.min.js"></script>

        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
    </body>
    <%
        } catch (NullPointerException e) {
            out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            out.println("<script>window.close();</script>");
        } catch (Exception e) {
            out.println("Excepcion revise por favor! " + e);
            e.printStackTrace();
        }
    %>
</html>
