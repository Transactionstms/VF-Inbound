<%-- 
    Document   : newjsp
    Created on : Apr 5, 2023, 2:37:22 PM
    Author     : dan_i
--%>
<%-- 
    Document   : modificarEventos
    Created on : 10/03/2023, 11:28:29 AM
    Author     : Desarrollo Tacts
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
        <title>Modificar Eventos</title>
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
        <link href="../lib/css/loader.css" rel="stylesheet" type="text/css"/> 
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

    </head>

    <body>
        <div class="preloader" id="loaders">  
            <div class="loader2" ></div>
            <div class="loader" ></div>
        </div>
        <script src="../lib/js/loader.js"></script>

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
                                                <h2 class="card-heading">SOLICITUD DE TRANSPORTE</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body " style="auto">
                                        <form class="col-sm-5 col-4" id="" name="" >
                                            <div class="form-group" >

                                                <div class="mb-4" autocomplete="off">
                                                    <label for="TipoDeEmbarque" class="form-label">Tipo de embarque:</label> <b style="strong">     DIRECTO</b>

                                                </div>
                                                <div class="mb-4">
                                                    <label class="form-label">Tipo de carga</label>
                                                    <select class="form-select" aria-label="Default select example">
                                                        <option selected>Elija una opcion</option>
                                                        <option value="1">FLC</option>
                                                        <option value="2">LCL</option>
                                                        <option value="3">FCL/LCL</option>
                                                    </select>
                                                </div>

                                                <div class="mb-4">
                                                    <label class="form-label">Shipment</label>
                                                    <input type="text" class="form-control" id="" autocomplete="false">
                                                </div>
                                                <div class="mb-4">
                                                    <label class="form-label">Contenedor</label>
                                                    <input type="text" class="form-control" id="" autocomplete="false">
                                                </div>
                                                <div class="mb-4">
                                                    <label class="form-label">Documento</label>
                                                    <input type="text" class="form-control" id="" autocomplete="false">
                                                </div>
                                                <!--button-->
                                                <a class="btn btn-primary text-nowrap" id="uploadBtnid" name="uploadBtnid" role="button" onclick="save()">Guardar Información</a>
                                                <a class="btn btn-default text-nowrap" role="button" href="Importacion/eventos.jsp">Regresar</a>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                            </div>

                                            <br>
                                            <!-- Botones controles -->

                                        </form>
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

        <!-- Conexión estatus red -->                    
        <script src="../lib/inbound/conexion/connectionStatus.js" type="text/javascript"></script>
        <!-- JavaScript files-->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
        <!-- Main Theme JS File-->
        <script src="../lib/js/theme.js"></script>
        <!-- Prism for syntax highlighting-->
        <script src="../lib/vendor/prismjs/prism.js"></script>
        <script src="../lib/vendor/prismjs/plugins/normalize-whitespace/prism-normalize-whitespace.min.js"></script>
        <script src="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.min.js"></script>
        <script src="../lib/vendor/prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard.min.js"></script>
        <!-- actions js -->
        <script src="../lib/inbound/eventos/functionsEvents.js" type="text/javascript"></script>
        <!-- sweetalert -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
        <script type="text/javascript">
                                                        // Optional
                                                        Prism.plugins.NormalizeWhitespace.setDefaults({
                                                            'remove-trailing': true,
                                                            'remove-indent': true,
                                                            'left-trim': true,
                                                            'right-trim': true,
                                                        });

        </script>
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
    </body>
</html>