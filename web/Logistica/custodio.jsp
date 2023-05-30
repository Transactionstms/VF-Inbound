<%-- 
    Document   : custodio
    Created on : 24-may-2023, 18:39:06
    Author     : grecendiz
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
        <title>Custodio</title>
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

        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
    </head>
    <%
        String custodia = request.getParameter("custodia");
        String id = request.getParameter("id");
        String op = request.getParameter("op");
        String transportista = request.getParameter("transporte");


    %>

    <body>



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
                                                <h2 class="card-heading">CUSTODIO</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body " style="auto">
                                        <form  autocomplete="off">
                                            <div class="form-group" >

                                                <div class="container">
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <div class="mb-4">
                                                                <label class="form-label" >Custodia</label>
                                                                <input type="text" class="form-control"  readonly value="<%=custodia%>" />
                                                            </div>
                                                            <div class="mb-4">
                                                                <label class="form-label">Nombre Custodio</label>
                                                                <input type="text" class="form-control" id="ncustodio" name="shipment">
                                                            </div>
                                                            <div class="mb-4">
                                                                <label class="form-label">Nombre Custodio 2</label>
                                                                <input type="text" class="form-control" id="ncustodio2" name="container">
                                                            </div>


                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="row">
                                                                <div class="mb-4"> 
                                                                    <label class="form-label">Placas Unidad</label>
                                                                    <input type="text" class="form-control" id="placa" name="evento">
                                                                </div>

                                                                <div class="mb-4"> 
                                                                    <label class="form-label">Celular Custodio</label>
                                                                    <input type="number" class="form-control" id="celcustodio" name="evento">
                                                                </div>
                                                            </div>

                                                        </div>
                                                        <!--button-->

                                                        <div class="row" >
                                                            <div class="col-md-1">
                                                                <button class="btn btn-secondary text-nowrap" type="button"  onclick="back()">Regresar</button>
                                                            </div>
                                                            <div class="col-md-1">
                                                                <button class="btn btn-primary text-nowrap" type="button"  onclick="save()">Enviar</button>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    </form>

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
                            <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
                            <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>

                            <script type="text/javascript">

                                                                    async function save() {
                                                                        // Obtener el valor de cada campo y asignarlo a una variable
                                                                        let ncustodio = document.getElementById('ncustodio').value;
                                                                        let ncustodio2 = document.getElementById('ncustodio2').value;
                                                                        let placa = document.getElementById('placa').value;
                                                                        let celcustodio = document.getElementById('celcustodio').value;



                                                                        swal("Espere...!");

                                                                        try {
                                                                            const data = await fetchData('<%=request.getContextPath()%>/ConsultarRepartoCustodiasEdit?ncustodio=' + ncustodio + '&ncustodio2=' + ncustodio2 + '&placa=' + placa + '&celcustodio=' + celcustodio + '&id=<%=id%>&EMBARQUE=<%=op%>');
                                                                            console.log(data);

                                                                            if (data === '') {
                                                                                swal("Error");
                                                                            } else {
                                                                                swal(data);
                                                                            }
                                                                        } catch (error) {
                                                                            swal("Error");
                                                                            console.error(error);
                                                                        }
                                                                    }

                                                                    async function fetchData(url) {
                                                                        const response = await fetch(url);
                                                                        const data = await response.text();
                                                                        return data;
                                                                    }

                                                                    function back() {
                                                                           window.location.href = '<%=request.getContextPath()%>/detalleCustodia.jsp?transporte=<%=transportista%>';
                                                                                    }




                            </script>



                            <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
                            </body>
                            </html>