<%-- 
    Document   : Details
    Created on : 29/12/2021, 05:57:49 PM
    Author     : Desarrollo Tacts
--%>

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
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Detalle Cliente</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Google fonts - Popppins for copy-->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&amp;display=swap" rel="stylesheet">
    <!-- VanillaJs Datepicker CSS-->
    <link rel="stylesheet" href="../lib/vendor/vanillajs-datepicker/css/datepicker-bs4.min.css">
    <!-- No UI Slider-->
    <link rel="stylesheet" href="../lib/vendor/nouislider/nouislider.css">
    <!-- Prism Syntax Highlighting-->
    <link rel="stylesheet" href="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.css">
    <link rel="stylesheet" href="../lib/vendor/prismjs/themes/prism-okaidia.css">
    <!-- The Main Theme stylesheet (Contains also Bootstrap CSS)-->
    <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="../lib/css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="../lib/img/favicon.png">
        <!-- sweetalert -->
    <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
  </head>
  <body>
      <%
        try {
            HttpSession ownsession = request.getSession();
      %>
    <!-- navbar-->
    <header class="header">
      <!--<nav class="navbar navbar-expand-lg px-4 py-2 bg-white shadow">
          <a class="sidebar-toggler text-gray-500 me-4 me-lg-5 lead" href="#"><i class="fas fa-align-left"></i></a><a class="navbar-brand fw-bold text-uppercase text-base" href="index.jsp"><span class="d-none d-brand-partial"></span><span class="d-none d-sm-inline">Detalle Cliente</span></a>
      </nav>-->
    </header>
    <div class="d-flex align-items-stretch">
      <div class="page-holder bg-gray-100">
        <div class="container-fluid px-lg-4 px-xl-5">
            <div class="page-header">
                <h3 class="page-heading">Detalle Cliente</h3>
            </div>
          <section>
            <!-- imask Input maps-->
            <div class="card mb-4">
                            <div class="card-header">
                                <h4 class="card-heading"><i class="fas fa-info-circle"></i>&nbsp;Información del Cliente</h4>
                            </div>
                            <div class="card-body">

                                <p class="mb-4">COVESTRO S.A. DE C.V.</p>

                                <div class="row">
                                    <div class="col-12 col-md-2 text-md-right">
                                        <p class="text-muted mb-4"><strong class="text-body">Código de cliente:</strong></p>
                                        <p class="text-muted mb-4"><strong class="text-body">RFC:</strong></p>
                                        <p class="text-muted mb-4"><strong class="text-body">Fecha de Registro:</strong></p>
                                        <p class="text-muted mb-4"><strong class="text-body">Teléfono:</strong></p>
                                        <p class="text-muted mb-4"><strong class="text-body">Contacto:</strong></p>
                                        <p class="text-muted mb-4"><strong class="text-body">Correo:</strong></p>
                                    </div>
                                    <div class="col-12 col-md-5 text-md-left">
                                        <p class="text-muted mb-4">11</p>
                                        <p class="text-muted mb-4">MNM150227D32</p>
                                        <p class="text-muted mb-4">2022-01-03</p>
                                        <p class="text-muted mb-4">5545655878</p>
                                        <p class="text-muted mb-4">RICARDO DANIEL ZEPEDA</p>
                                        <p class="text-muted mb-4">correo@tacts.mx</p>
                                    </div>
                                </div>
                            </div>
                        </div>
            
                    <div class="row">
                        <div class="col-lg-5">
                            <a class="btn btn-primary" href="List.jsp"><i class="fas fa-long-arrow-alt-left"></i>&nbsp;Regresar al listado</a> 
                        </div>
                        <div class="col-lg-5">
                            <a class="btn btn-primary" href=""><i class="fas fa-sticky-note"></i>&nbsp;Facturar</a> 
                        </div>
                        <div class="col-lg-2">
                            <a class="btn btn-primary" href="Edit.jsp"> <i class="fa fa-edit"> </i>&nbsp;Editar</a>
                        </div>
                    </div> 
            
          </section>
        </div>
        <footer class="footer bg-white shadow align-self-end py-3 px-xl-5 w-100">
          <div class="container-fluid">
            <div class="row">
              <div class="col-md-6 text-center text-md-start fw-bold">
                <p class="mb-2 mb-md-0 fw-bold">Tacts S de RL de CV &copy; <%=part3%></p>
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
            function DetalleClientes(){
                
                var cliente_id = document.getElementById("cliente_id").value;     
                var rfc = document.getElementById("rfc").value;             
                var usocfdi_id = document.getElementById("usocfdi_id").value;      
                var correos = document.getElementById("correos").value;         
                var tipoenvio_id = document.getElementById("tipoenvio_id").value;    
                var tipo_descripcion = document.getElementById("tipo_descripcion").value;
                var fecha_emision = document.getElementById("fecha_emision").value;   
                var hora_emision = document.getElementById("hora_emision").value;    
                var serie = document.getElementById("serie").value;           
                var folio = document.getElementById("folio").value;           
                var comprobante_id = document.getElementById("comprobante_id").value;  
                var documento_id = document.getElementById("documento_id").value;    
                var regimen_id = document.getElementById("regimen_id").value;      
                var comentarios = document.getElementById("comentarios").value;     
                var concepto_id = document.getElementById("concepto_id").value;     
                var cantidad = document.getElementById("cantidad").value;        
                var rel_clvsat_id = document.getElementById("rel_clvsat_id").value;  
                var unidad_sat_id = document.getElementById("unidad_sat_id").value;   
                var precio_unitario = document.getElementById("precio_unitario").value; 
                var metodo_id = document.getElementById("metodo_id").value;       
                var forma_id = document.getElementById("forma_id").value;        
                var condiciones_pago = document.getElementById("condiciones_pago").value;
                var moneda_id = document.getElementById("moneda_id").value;       
                var tipo_cambio = document.getElementById("tipo_cambio").value;     
                var rel_clvcfdi_id = document.getElementById("rel_clvcfdi_id").value;  
                var rel_clvcom_id = document.getElementById("rel_clvcom_id").value;
              
                fetch("<%=request.getContextPath()%>/InsertarFacturacion?cliente_id=" + cliente_id + 
                                                                        "&rfc=" + rfc + 
                                                                        "&usocfdi_id=" + usocfdi_id + 
                                                                        "&correos=" + correos + 
                                                                        "&tipoenvio_id=" + tipoenvio_id + 
                                                                        "&tipo_descripcion=" + tipo_descripcion + 
                                                                        "&fecha_emision=" + fecha_emision + 
                                                                        "&hora_emision=" + hora_emision + 
                                                                        "&serie=" + serie + 
                                                                        "&folio=" + folio + 
                                                                        "&comprobante_id=" + comprobante_id + 
                                                                        "&documento_id=" + documento_id + 
                                                                        "&regimen_id=" + regimen_id + 
                                                                        "&comentarios=" + comentarios + 
                                                                        "&concepto_id=" + concepto_id + 
                                                                        "&cantidad=" + cantidad + 
                                                                        "&rel_clvsat_id=" + rel_clvsat_id + 
                                                                        "&unidad_sat_id=" + unidad_sat_id + 
                                                                        "&precio_unitario=" + precio_unitario + 
                                                                        "&metodo_id=" + metodo_id + 
                                                                        "&forma_id=" + forma_id + 
                                                                        "&condiciones_pago=" + condiciones_pago + 
                                                                        "&moneda_id=" + moneda_id + 
                                                                        "&tipo_cambio=" + tipo_cambio + 
                                                                        "&rel_clvcfdi_id=" + rel_clvcfdi_id + 
                                                                        "&rel_clvcom_id=" + rel_clvcom_id, {
                  method: 'POST', 
                  }) .then(r => r.text())
                     .then(data => { 

                            if (data === "true") {
                                 swal("", "Registro exitoso", "success"); alertclose();
                            } else {
                                 swal("Error", "El cliente no fue registrado", "error"); alertclose();
                            }
                         //document.getElementById('container15').innerHTML = data;

                  }).catch(error => console.log(error)); 
                
            }
            
            function alertclose() {
                setTimeout(function () {
                    swal.close();
                }, 2000);
            }
    </script>
    <!-- JavaScript files-->
    <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
    <!-- Choices.js-->
    <script src="../lib/vendor/choices.js/public/assets/scripts/choices.min.js"></script>
    <!-- iMask-->
    <script src="../lib/vendor/imask/imask.min.js"></script>
    <!-- NoUI Slider-->
    <script src="../lib/vendor/nouislider/nouislider.min.js"></script>
    <!-- VanillaJs DatePicker-->
    <script src="../lib/vendor/vanillajs-datepicker/js/datepicker-full.min.js"></script>
    <!-- Forms init-->
    <script src="../lib/js/forms-advanced.js"></script>
    <!-- Main Theme JS File-->
    <script src="../lib/js/theme.js"></script>
    <!-- Prism for syntax highlighting-->
    <script src="../lib/vendor/prismjs/prism.js"></script>
    <script src="../lib/vendor/prismjs/plugins/normalize-whitespace/prism-normalize-whitespace.min.js"></script>
    <script src="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.min.js"></script>
    <script src="../lib/vendor/prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard.min.js"></script>
    <script type="text/javascript">
      // Optional
      Prism.plugins.NormalizeWhitespace.setDefaults({
      'remove-trailing': true,
      'remove-indent': true,
      'left-trim': true,
      'right-trim': true,
      });
          
    </script>
        <!-- sweetalert -->
    <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
    <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
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