<%-- 
    Document   : main
    Created on : 11-ene-2022, 14:17:48
    Author     : grece
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
    <title>Facturación Electrónica - TACTS</title>
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
            <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css"   crossorigin="anonymous">

    <style>
        .boton_azul {
            background-color: #008ec8;
            border: solid #008ec8;
        }

        .p-lg {
            padding: 30px;
        }

        .widget {
            border-radius: 5px;
            padding: 15px 20px;
            margin-bottom: 10px;
            margin-top: 10px;
        }

        .text-center {
            text-align: center;
        }

        * {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }
        
        div {
            display: block;
        }
    </style>
    <style>
        .p-sm {
            padding: 15px;
        }

        .navy-bg, .bg-success {
            background-color: #1ab394;
            color: #ffffff;
        }

        .widget-head-color-box {
            border-radius: 5px 5px 0 0;
            margin-top: 10px;
        }

        * {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }

        div {
            display: block;
        }
    </style>
  </head>
  <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String cve = (String) ownsession.getAttribute("cbdivcuenta");
                ConsultasQuery fac = new ConsultasQuery();
                    String UserId = (String) ownsession.getAttribute("login.user_id_number");
        %>
    <!-- navbar-->
    <div class="d-flex align-items-stretch">
     
      <div class="page-holder bg-gray-100">
        <div class="container-fluid px-lg-4 px-xl-5">
            
            <!-- Indicadores -->
            <section class="mb-4 mb-lg-5">
                <div class="wrapper wrapper-content animated fadeInRight">
                    <div class="row"></div>
                    <div class="row">
                        
                        <div class="col-lg-12 col-xl-6 ms-xl-auto px-lg-4 text-center text-primary">
                          
                            
                            
                           <%
                                                    if (db.doDB("    select EMBARQUE_ID,'Pendiente de evidencia' from TRA_INB_embarque where EMBARQUE_ESTADO_ID=1 and EMBARQUE_TRANSPORTISTA in (select LTRANSPORTE_ID from TRA_INB_LINEA_TRANSPORTE where USER_NID in (6356,6381,6382,6383,6384,6385,6386,5428,"+UserId+") )")) {
                                                 %>
                   <div class="table-responsive" style="overflow: auto">    
                                                            <div class="tableFixHead">
                                  
<table id="example" class="display" style="width:100%">                                 
    <thead>
                                                <tr>
                                                    <th><font size="2">Embarque</font></th>
                                                    <th><font size="2">Estatus</font></th> 
                                                </tr>
                                            </thead>
                                            <tbody id="recibos">
                                                <%
                                                         for (String[] rowFT : db.getResultado()) {   
                                                %>
                                                <tr>
                                                    <td><font size="2" style="color:black"><%=rowFT[0]%></font></td>
                                                    <td><font size="2" style="color:black"><%=rowFT[1]%></font></td> 
                                                   
                                                </tr>
                                                <%
                                                       }
                                                    
                                                %>
                                            </tbody>
                                        </table>
                                            
                         </div>
                                 </div>
                                            
                                  <%
                                                       }
                                                     
                                                %>      
                                   
                                    
                                            
                        </div>
                        
                        <div class="col-lg-12 col-xl-6 ms-xl-auto px-lg-4 text-center text-primary">
                            <img  class="img-fluid mb-4" width="350" src="../lib/img/vf.png" alt="" style="transform: rotate(10deg);">
                        </div>
                        <!--<div class="col-lg-3">
                            <div class="widget boton_azul p-lg text-center">
                                <br>
                                <div class="m-b-sm">
                                    <a class="link" id="btnBuscarCFDI" name="btnBuscarCFDI" href="../Facturacion/Index.jsp" target="data" style="color: white">
                                        <i class="fa fa-search fa-2x"></i>
                                        <h1 style="font-family: Helvetica; font-weight: normal;"><font size="6">Buscar CFDI</font></h1>
                                    </a>
                                </div>
                                <br>
                            </div>
                        </div>-->
                        <!--<div class="col-lg-3">
                            <div class="widget boton_azul p-lg text-center">
                                <br>
                                <div class="m-b-sm">
                                    <a class="link" id="btnCrearCFDI" name="btnCrearCFDI" href="../Facturacion/Create.jsp" target="data" style="color: white">
                                        <i class="fa fa-file fa-2x"></i>
                                        <h1 style="font-family: Helvetica; font-weight: normal;"><font size="6">Crear CFDI</font></h1>
                                    </a>
                                </div>
                                <br>
                            </div>
                        </div>-->
                        <!--<div class="col-lg-3">
                            <div class="widget boton_azul p-lg text-center">
                                <br>
                                <div class="m-b-sm">
                                    <a class="link" id="btnCrearPago" name="btnCrearPago" href="../ReciboPago/Search.jsp" target="data" style="color: white">
                                        <i class="fa fa-dollar-sign fa-2x"></i>
                                        <h1 style="font-family: Helvetica; font-weight: normal;"><font size="6">Crear Pago</font></h1>
                                    </a>
                                </div>
                                <br>
                            </div>
                        </div>-->
                        <!--<div class="col-lg-3">
                            <div class="widget-head-color-box navy-bg p-sm">
                                <div class="row">
                                    <div class="form-group col-md-6">
                                        <i class="fa fa-bell fa-4x"></i>
                                    </div>
                                    <div class="form-group col-md-6" align="right">
                                        <h3 style="font-family: Helvetica; font-weight: bold;"><font size="3">Folios Disponibles</font></h3>
                                        <h2 style="font-family: Helvetica; font-weight: bold;"><font size="5">26</font></h2>
                                    </div>
                                </div>
                                <div align="right">
                                    <div class="col-lg-12">&nbsp;<br></div>
                                    <a href="/Contratacion/Create" class="btn btn-sm btn-primary boton_azul"><i class="fa fa-shopping-cart fa-fw"></i> Comprar Folios </a>
                                </div>
                            </div>
                        </div>-->
                    </div>
                </div>
            </section>   
            
            <!-- Gráfica 12 meses -->
            <!--<section class="mb-4 mb-lg-5">
                <div class="row">
                    <div class="col-lg-12 mb-4 mb-lg-0">
                        <div class="card h-100">
                            <div class="card-header">
                                <h4 class="card-heading">Últimos 12 meses</h4>
                            </div>
                            <div class="card-body">
                                <div class="chart-holder w-100">
                                    <canvas id="lineChart1"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>-->
                        
            <!-- Tabla - Facturas guradadas pendientes de timbrar -->
            <!--<section class="mb-4 mb-lg-5">
                <div class="row">
                    <div class="col-lg-12 mb-4 mb-lg-0">
                        <div class="card h-100">
                            <div class="card-header">
                                <h4 class="card-heading">Facturas guardadas pendientes de timbrar</h4>
                            </div>
                            <div class="card-body">
                                    <div class="preload-wrapper">
                                        <table class="table table-hover table-borderless align-middle mb-0" id="dashFacturasDatatable">
                                            <thead>
                                                <tr>
                                                    <th><font size="2">Fecha Guardado</font></th>
                                                    <th><font size="2">Serie - Folio</font></th>
                                                    <th><font size="2">Cliente</font></th>
                                                    <th><font size="2">Total</font></th>
                                                    <th><font size="2">Moneda</font></th>
                                                    <th style="min-width: 50px;"> </th>
                                                </tr>
                                            </thead>
                                            <tbody id="recibos">
                                                <%
                                                    if (db.doDB(fac.consultarFacturasGuardadasPendientesPorTimbrar(cve))) {
                                                        for (String[] rowFT : db.getResultado()) {   
                                                %>
                                                <tr>
                                                    <td><font size="2"><%=rowFT[0]%></font></td>
                                                    <td><font size="2"><%=rowFT[1]%></font></td>
                                                    <td><font size="2"><%=rowFT[2]%></font></td>
                                                    <td><font size="2"><%=rowFT[3]%></font></td>
                                                    <td><font size="2"><%=rowFT[4]%></font></td>
                                                    <td></td>
                                                </tr>
                                                <%
                                                       }
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                        <span class="me-2" id="categoryBulkAction">
                                            <select class="form-select form-select-sm d-inline w-auto" name="categoryBulkAction">
                                                <option>Bulk Actions</option>
                                                <option>Delete</option>
                                            </select>
                                            <button class="btn btn-sm btn-outline-primary align-top">Apply</button></span>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section> -->
            
            <!-- Tabla - Recibos de pagos pendientes de timbrar -->
            <!--<section class="mb-4 mb-lg-5">
                <div class="row">
                    <div class="col-lg-12 mb-4 mb-lg-0">
                        <div class="card h-100">
                            <div class="card-header">
                                <h4 class="card-heading">Recibos de pagos pendientes por timbrar</h4>
                            </div>
                            <div class="card-body">
                                    <div class="preload-wrapper">
                                        <table class="table table-hover table-borderless align-middle mb-0" id="dashPagosDatatable">
                                            <thead>
                                                <tr>
                                                    <th><font size="2">Última Modificación</font></th>
                                                    <th><font size="2">Serie - Folio</font></th>
                                                    <th><font size="2">Cliente</font></th>
                                                    <th><font size="2">Monto del pago</font></th>
                                                    <th><font size="2">Moneda del pago</font></th>
                                                    <th style="min-width: 50px;"> </th>
                                                </tr>
                                            </thead>
                                            <tbody id="recibos">
                                                <%
                                                    if (db.doDB(fac.consultarRecibosPagosPendientesPorTimbrar(cve))) {
                                                        for (String[] rowRP : db.getResultado()) {   
                                                %>
                                                <tr>
                                                    <td><font size="2"><%=rowRP[0]%></font></td>
                                                    <td><font size="2"><%=rowRP[1]%></font></td>
                                                    <td><font size="2"><%=rowRP[2]%></font></td>
                                                    <td><font size="2"><%=rowRP[3]%></font></td>
                                                    <td><font size="2"><%=rowRP[4]%></font></td>
                                                    <td></td>
                                                </tr>
                                                <%
                                                       }
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                        <span class="me-2" id="categoryBulkAction">
                                            <select class="form-select form-select-sm d-inline w-auto" name="categoryBulkAction">
                                                <option>Bulk Actions</option>
                                                <option>Delete</option>
                                            </select>
                                            <button class="btn btn-sm btn-outline-primary align-top">Apply</button></span>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section> -->
        </div>
        <!-- footer -->  
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
    <!-- JavaScript files-->
    <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
    <!-- Data Tables-->
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"></script>
    <!-- CMS Category JS-->
    <script src="../lib/js/datatable-Dashboard-facturasPendientes.js" type="text/javascript"></script>
    <script src="../lib/js/datatable-Dashboard-pagosPendientes.js" type="text/javascript"></script>
    <!-- Init Charts on Homepage-->
    <script src="../lib/vendor/chart.js/Chart.min.js"></script>
    <script src="../lib/js/charts-defaults.js"></script>
    <script src="../lib/js/charts-home.js"></script>
    <!-- Main Theme JS File-->
    <script src="../lib/js/theme.js"></script>
    <!-- Prism for syntax highlighting-->
    <script src="../lib/vendor/prismjs/prism.js"></script>
    <script src="../lib/vendor/prismjs/plugins/normalize-whitespace/prism-normalize-whitespace.min.js"></script>
    <script src="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.min.js"></script>
    <script src="../lib/vendor/prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard.min.js"></script>
   
<script src="https://code.jquery.com/jquery-3.7.0.js"   crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"   crossorigin="anonymous"></script>
     

          
    <script type="text/javascript">
      // Optional
      Prism.plugins.NormalizeWhitespace.setDefaults({
      'remove-trailing': true,
      'remove-indent': true,
      'left-trim': true,
      'right-trim': true,
      });
        
    new DataTable('#example');

    </script>
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

