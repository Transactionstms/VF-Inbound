<%-- 
    Document   : index
    Created on : 29/12/2021, 06:12:19 PM
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

              ConsultasQuery fac = new ConsultasQuery();
      %>
    <!-- navbar-->
    <div class="d-flex align-items-stretch">
     
      <div class="page-holder bg-gray-100">
        <div class="container-fluid px-lg-4 px-xl-5">
            
            <!-- Indicadores -->
            <section class="mb-4 mb-lg-5">
                <div class="wrapper wrapper-content animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-3">
                            <div class="widget boton_azul p-lg text-center">
                                <br>
                                <div class="m-b-sm">
                                    <a class="link" id="btnBuscarCFDI" name="btnBuscarCFDI" href="../ConfigExpress/ConfigExpress.jsp" target="data" style="color: white">
                                        <i class="fa fa-circle-bolt"></i>
                                        <h1 style="font-family: Helvetica; font-weight: normal;"><font size="6">Configuración Express</font></h1>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="widget boton_azul p-lg text-center">
                                <br>
                                <div class="m-b-sm">
                                    <a class="link" id="btnCrearCFDI" name="btnCrearCFDI" href="../Empresas/List.jsp" target="data" style="color: white">
                                        <i class="fa fa-file fa-2x"></i>
                                        <h1 style="font-family: Helvetica; font-weight: normal;"><font size="6">Empresa</font></h1>
                                    </a>
                                </div>
                                <br>
                                <br>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="widget boton_azul p-lg text-center">
                                <br>
                                <div class="m-b-sm">
                                    <a class="link" id="btnCrearPago" name="btnCrearPago" href="../ReciboPago/Search.jsp" target="data" style="color: white">
                                        <i class="fa fa-dollar-sign fa-2x"></i>
                                        <h1 style="font-family: Helvetica; font-weight: normal;"><font size="6">Lugar de Expedición</font></h1>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="widget boton_azul p-lg text-center">
                                <br>
                                <div class="m-b-sm">
                                    <a class="link" id="btnCrearPago" name="btnCrearPago" href="../ReciboPago/Search.jsp" target="data" style="color: white">
                                        <i class="fa fa-dollar-sign fa-2x"></i>
                                        <h1 style="font-family: Helvetica; font-weight: normal;"><font size="6">Series</font></h1>
                                    </a>
                                </div>
                                <br>
                                <br>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="widget boton_azul p-lg text-center">
                                <br>
                                <div class="m-b-sm">
                                    <a class="link" id="btnBuscarCFDI" name="btnBuscarCFDI" href="../Facturacion/Index.jsp" target="data" style="color: white">
                                        <i class="fa fa-search fa-2x"></i>
                                        <h1 style="font-family: Helvetica; font-weight: normal;"><font size="6">Bancos</font></h1>
                                    </a>
                                </div>
                                <br>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="widget boton_azul p-lg text-center">
                                <br>
                                <div class="m-b-sm">
                                    <a class="link" id="btnCrearCFDI" name="btnCrearCFDI" href="../Facturacion/Create.jsp" target="data" style="color: white">
                                        <i class="fa fa-file fa-2x"></i>
                                        <h1 style="font-family: Helvetica; font-weight: normal;"><font size="6">Usuarios</font></h1>
                                    </a>
                                </div>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
            </section>    
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

