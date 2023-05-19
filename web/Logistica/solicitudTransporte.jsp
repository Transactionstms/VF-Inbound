<%-- 
    Document   : newjsp
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
    <%
    try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
    
    %>

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
                                        <form class="col-sm-5 col-4" id="" name="" action="../Logistica/plantillaSolicitudTransporte.jsp">
                                            <div class="form-group" >

                                                <div class="mb-4" autocomplete="off">
                                                    <label for="TipoDeEmbarque" class="form-label">Tipo de embarque:</label> <b style="strong">     DIRECTO</b>

                                                </div>
                                                <div class="mb-4">
                                                    <label class="form-label">Tipo de carga</label>
                                                    <select class="form-select" aria-label="Default select example" id="tcarga" name="tcarga">
                                                        <option selected value="0">Elija una opcion</option>
                                                        <option value="1">FCL</option>
                                                        <option value="2">LCL</option>
                                                        <option value="3">FCL/LCL</option>
                                                    </select>
                                                </div>
                                                <div class="mb-4">
                                                    <label class="form-label">Shipment</label>
                                                    <input type="text" class="form-control" id="shipment" name="shipment"  >
                                                </div>
                                                <div class="mb-4">
                                                    <label class="form-label">Contenedor</label>
                                                    <input type="text" class="form-control" id="container" name="container"  >
                                                </div>
                                                <div class="mb-4"> 
                                                    <label class="form-label">Evento</label>
                                                    <input type="text" class="form-control" id="evento" name="evento"  >
                                                </div>
                                                <!--button-->
                                                <button class="btn btn-primary text-nowrap" type="button"  onclick="enviar()">Enviar</button>
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

        <script>
            
            function enviar(){
          var tcarga    = document.getElementById('tcarga').value;
          var shipment  = document.getElementById('shipment').value;
          var container = document.getElementById('container').value;
          var evento    = document.getElementById('evento').value;
          
          console.log(tcarga);
          console.log(shipment);
          console.log(container);
          console.log(evento);
          
          if(tcarga===''    || tcarga===null){    tcarga=0;   }
          if(shipment===''  || shipment===null){  shipment=0;  }else{
                let opciones =shipment; 
                let opcionesEncerradas = opciones.split(',').map(opcion => "'" + opcion + "'").join(','); 
                shipment=opcionesEncerradas
              
          }
          if(container==='' || container===null){ container=0; }
          else{
                let opciones =container; 
                let opcionesEncerradas = opciones.split(',').map(opcion => "'" + opcion + "'").join(','); 
                container=opcionesEncerradas
              
          }
          if(evento===''    || evento===null){    evento=0;    }
          
          
          console.log(tcarga);
          console.log(shipment);
          console.log(container);
          console.log(evento);
          
       window.location.href =  '<%=request.getContextPath()%>/Logistica/plantillaSolicitudTransporte.jsp?tcarga='+tcarga+'&shipment='+shipment+'&container='+container+'&evento='+evento;
 
          
            }
         //   var one = document.getElementById('one');
         //   var two = document.getElementById('two');
         //   var three = document.getElementById('three');
//
         //   var checker = setInterval(function () {
         //       if (two.value !== '' || three.value !== '') {
         //           one.disabled = true;
         //       } else {
         //           one.disabled = false;
         //       }
         //       if (one.value !== '' || three.value !== '') {
         //           two.disabled = true;
         //       } else {
         //           two.disabled = false;
         //       }
         //       if (one.value !== '' || two.value !== '') {
         //           three.disabled = true;
         //       } else {
         //           three.disabled = false;
         //       }
         //   }, 30);
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
