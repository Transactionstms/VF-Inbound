<%-- 
    Document   : Index
    Created on : 29/12/2021, 06:09:21 PM
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
        <title>Busqueda de CFDI</title>
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
        <style>
            table {
                display: block;
                overflow-x: auto;    /*scroll horizontal */
                white-space: nowrap;

                overflow-y: auto;    /*scroll vertical */
                height: 250px;
            }
        </style>

    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String cve = (String) ownsession.getAttribute("cbdivcuenta");
                //String cve = "10";
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
                        <div class="card mb-4">
                            <div class="card-header">
                                <div class="card-heading">Cliente</div>
                            </div>
                            <div class="card-body text-muted">
                                <!-- Selección de Clientes -->
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="mb-3">  
                                            <select class="form-control selectpicker col-md-5" id="cliente_id" name="cliente_id" onchange="cliente(this.value)">
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
                                </div>  

                                <div class="row">&nbsp;</div>           

                                <div class="row" style="display:none;" id="search">
                                    <div class="col-7">
                                        <div class="row" >
                                            <div class="col-2"><label>Busqueda:</label></div>
                                            <div class="col-6"><input id="searchTerm" type="text" class="form-control" onkeyup="doSearch()" /></div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <table class="table" id="dataReciboPago">
                                        <thead>
                                            <tr>
                                                <th>Serie</th>
                                                <th>Folio</th>
                                                <th>UUID</th>
                                                <th>Cliente</th>
                                                <th>Fecha de Emisión</th>
                                                <th>Total</th>
                                                <th>Estatus</th>
                                                <th>Estatus de Pago</th>
                                                <th style="min-width: 50px;">Opciones</th>
                                            </tr>
                                        </thead>
                                        <tbody id="detalle_cfdi"></tbody>
                                    </table>
                                </div>      
                            </div>
                        </div>
                        <!-- modal - Detalle Cancelar CFDI --> 
                        <div class="modal fade text-start" id="modalDetalleCancelarCfdi" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header border-0 bg-gray-100">
                                        <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fa fa-info-circle"></i>&nbsp;Detalle del CFDI: </h3>
                                        <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row" style="border: solid 1px #f5faf5;">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <label style="font-family: Arial; font-weight: bold;">UUID</label>:
                                                    <label style="font-size: 13px;" id="txtUuid"></label>
                                                </div>
                                                <div class="col-lg-6">
                                                    <label  style="font-family: Arial; font-weight: bold;">Tipo de Comprobante</label>:
                                                    <label>Factura electrónica</label>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <label style="font-family: Arial; font-weight: bold;">Serie</label>:
                                                    <label id="txtSerie"></label>
                                                </div>
                                                <div class="col-lg-6">
                                                    <label  style="font-family: Arial; font-weight: bold;">Fecha de Emisión</label>:
                                                    <label id="txtFechaEmision"></label>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <label style="font-family: Arial; font-weight: bold;">Folio</label>:
                                                    <label id="txtFolio"></label>
                                                </div>
                                                <div class="col-lg-6">
                                                    <label  style="font-family: Arial; font-weight: bold;">Fecha de Timbrado</label>:
                                                    <label id="txtFechaTimbrado"></label>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <label style="font-family: Arial; font-weight: bold;">Total</label>: $
                                                    <label id="txtTotal"></label>
                                                </div>
                                                <div class="col-lg-6">
                                                    <label style="font-family: Arial; font-weight: bold;">Email</label>:
                                                    <label id="txtEmails"></label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">&nbsp;</div>
                                        <div class="row" style="border: solid 1px #a2eaa2;">
                                            <div class="row" style="margin-left: 0px; margin-top: 10px">
                                                <div class="col-md-3" style="font-family: Arial; font-weight: bold;"><label>Estatus del CFDI:</label></div>
                                                <div class="col-md-1" style="font-family: Arial; font-weight: bold; color:#fffbfa;"><label id="estatus_sat"></label></div>
                                            </div>
                                            <div class="row">&nbsp;</div>
                                            <div class="row" style="margin-left: 0px; margin-bottom: 10px">
                                                <div id="descripcion_sat" style="font-weight: normal; font-style: italic"></div>
                                            </div>
                                        </div>
                                        <div class="row">&nbsp;</div>
                                        <div class="row" id="flotantePrincipal" style="display:none;">
                                            <div class="row">
                                                <div class="col-xs-4">
                                                    <select class="form-control selectpicker col-md-5" id="id_servicio" name="id_servicio" onchange="servicioCancelacion(this.value)">
                                                        <option value="0" disabled selected>-- Seleccione el servicio de cancelación --</option>
                                                        <option value="1">1.- Cancelar CFDI sector primario</option>
                                                        <option value="2">2.- Cancelar CFDI</option>
                                                        <option value="3">3.- Cancelar CFDI relacionado</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row">&nbsp;</div>
                                            <div class="row">
                                                <div class="col-xs-4">
                                                    <select class="form-control selectpicker col-md-5" id="id_motivo" name="id_motivo" onchange="describirCancelacion(this.value)">
                                                        <option value="0" disabled selected>-- Seleccione el motivo de cancelación --</option>
                                                        <option value="01">01.- Comprobante emitido con errores con relación</option>
                                                        <option value="02">02.- Comprobante emitido con errores sin cancelación</option>
                                                        <option value="03">03.- No se llevó a cabo la operación</option>
                                                        <option value="04">04.- Operación nominativa relacionada en la factura global</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row">&nbsp;</div>
                                            <div class="row">
                                                <div id="descripcion_cancelacion" style="font-weight: normal; font-style: italic"></div>
                                            </div>
                                            <div class="row">&nbsp;</div>
                                            <div class="row" id="flotante1" style="display:none;">
                                                <div class="col-xs-4">
                                                    <select class="form-control selectpicker col-md-5" id="uuid_sustitucion" name="uuid_sustitucion" onchange="describirCancelacion(this.value)"  data-style="btn-info">
                                                        <option value="0" disabled selected>-- Seleccione UUID a sustituir --</option>
                                                        <%
                                                            if (db.doDB("SELECT DISTINCT TF.COM_UUID, TF.SERIE, TF.FOLIO FROM TRA_FACTURACION TF LEFT JOIN TRA_CANCELACION TC  ON TF.COM_UUID = TC.COM_UUID WHERE TF.COM_NOCERTIFICADOSAT IS NOT NULL AND TF.CBDIV_ID = '" + cve + "' AND TC.COM_UUID IS NULL ORDER BY TF.FOLIO ASC")) {
                                                                for (String[] row : db.getResultado()) {
                                                                    out.println("<option value=\"" + row[0] + "\">" + row[1] + "-" + row[2] + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + row[0] + "</option>");
                                                                }
                                                            }
                                                        %>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row" id="flotante2" style="display:none;">
                                                <div class="col-xs-4" id="uuid_relacionados"></div>
                                            </div>
                                        </div>        
                                    </div>
                                    <!--loader-->
                                    <div class="row" id="loader-folding-circle" style="display:none;">
                                        <div class="card-body d-flex justify-content-center pt-5 pb-5">
                                            <div class="sk-folding-cube">
                                                <div class="sk-cube1 sk-cube"></div>
                                                <div class="sk-cube2 sk-cube"></div>
                                                <div class="sk-cube4 sk-cube"></div>
                                                <div class="sk-cube3 sk-cube"></div>
                                            </div> 
                                        </div>
                                        <div class="row">
                                            <center>Almacenando información....</center>
                                        </div>
                                    </div> 
                                    <div class="modal-footer">
                                        <button class="btn btn-default" type="button" data-bs-dismiss="modal">Cerrar</button>
                                        <button class="btn btn-danger" type="button" onclick="AddCancelacion()">Cancelar</button>
                                    </div>
                                </div>
                            </div>
                        </div>         
                        <!-- input's hidden -->
                        <input type="hidden" id="cbdiv_id" name="cbdiv_id" value="<%=cve%>">
                    </section>
                </div>
                <!--footer-->
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
        <!-- Procesos Cancelación General -->
        <script src="../lib/validations3.3/3.3/cancelacion3.3/processesCancelacionGral_3.3.js" type="text/javascript"></script>
        <!-- Procesos Cancelación -->
        <script src="../lib/validations3.3/3.3/cancelacion3.3/processesCancelacion_3.3.js" type="text/javascript"></script>
        <!-- JavaScript files-->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
        <!-- Data Tables-->
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"></script>
        <script src="../lib/js/datatable-reciboPago.js" type="text/javascript"></script>
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