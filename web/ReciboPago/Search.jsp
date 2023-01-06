<%-- 
    Document   : Search
    Created on : 29/12/2021, 06:04:43 PM
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
        <title>Recibo de Pago</title>
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
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String cve = (String) ownsession.getAttribute("cbdivcuenta");
                ConsultasQuery fac = new ConsultasQuery();
        %>
        <!-- navbar-->
        <header class="header">
        </header>
        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <!-- Page Header-->
                    <div class="page-header">
                        <h3 class="page-heading">Nuevo Recibo de Pago</h3>
                    </div>
                    <section>
                        <div class="row">
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card h-100">
                                    <div class="card-body text-muted">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="mb-3">  
                                                    <select class="form-control selectpicker col-md-5" id="cliente_id" name="cliente_id" onchange="consultarRecibo(this.value)">
                                                        <option value="0" disabled selected>-- Seleccione un cliente --</option>
                                                        <%
                                                            if (db.doDB(fac.listarSusClientes(cve))) {
                                                                for (String[] row : db.getResultado()) {
                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[1] + " - " + row[2] + "</option>");
                                                                }
                                                            }
                                                        %>
                                                    </select>
                                                </div>
                                            </div>
                                            <!--<div class="col-lg-12" style="text-align: center;">
                                                <a class="btn btn-primary text-nowrap" role="button" onclick="consucltarRecibo()">Buscar</a>
                                            </div>-->        
                                        </div> 
                                    </div>
                                    <div class="card-body">
                                        <div class="preload-wrapper">
                                            <table class="table table-hover table-borderless align-middle mb-0" id="dashFacturasDatatable">
                                                <thead>
                                                    <tr>
                                                        <th> </th>
                                                        <th><font size="2">Cliente</font></th>
                                                        <th><font size="2">Fecha Emisión</font></th>
                                                        <th><font size="2">Serie - Folio</font></th>
                                                        <th><font size="2">Método Pago</font></th>
                                                        <th><font size="2">Total</font></th>
                                                        <th><font size="2">Abonos</font></th>
                                                        <th><font size="2">Saldo</font></th>
                                                        <th><font size="2">Moneda</font></th>
                                                    </tr>
                                                </thead>
                                                <tbody id="recibos"></tbody>
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

                        <!-- Botones controles -->
                        <div class="col-lg-12" style="text-align: right;">
                            <a class="btn btn-primary text-nowrap" role="button" href="Create.jsp">Siguiente</a>
                        </div>         
                    </section>
                </div>

                <!-- modal - Vista Previa CFDI --> 
                <div class="modal fade text-start" id="modalVistaPreviaCfdi" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header border-0 bg-gray-100">
                                <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Vista previa comprobante:</h3>
                                <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="container" id="visor1"> 
                                    <iframe class="responsive-iframe" name="pdf" id="pdf" width="100%" height="500px"></iframe>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>                                     
                <footer class="footer bg-white shadow align-self-end py-3 px-xl-5 w-100">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-6 text-center text-md-start fw-bold">
                                <p class="mb-2 mb-md-0 fw-bold">Your company &copy; 2021</p>
                            </div>
                            <div class="col-md-6 text-center text-md-end text-gray-400">
                                <p class="mb-0">Version 1.1.0</p>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>    
        <!-- Procesos Complemento de Pago Gral-->
        <script src="../lib/validations3.3/3.3/recibopago3.3/processesReciboPagoGral_3.3.js" type="text/javascript"></script>
        <!-- Procesos Complemento de Pago -->
        <script src="../lib/validations3.3/3.3/recibopago3.3/processesReciboPago_3.3.js" type="text/javascript"></script>
        <!-- JavaScript files-->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
        <!-- Data Tables-->
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"></script>
        <script src="../lib/js/datatable-reciboPago.js" type="text/javascript"></script>
        <script src="../lib/js/datatable-Dashboard-facturasPendientes.js" type="text/javascript"></script>
        <!-- Main Theme JS File-->
        <script src="../lib/js/theme.js"></script>
        <!-- Prism for syntax highlighting-->
        <script src="../lib/vendor/prismjs/prism.js"></script>
        <script src="../lib/vendor/prismjs/plugins/normalize-whitespace/prism-normalize-whitespace.min.js"></script>
        <script src="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.min.js"></script>
        <script src="../lib/vendor/prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard.min.js"></script>
        <!-- jQuery/show modal -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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