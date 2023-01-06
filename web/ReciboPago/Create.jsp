<%-- 
    Document   : Create
    Created on : 7/03/2022, 06:27:06 AM
    Author     : luis_
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
        <title>Nueva Recibo de Pago</title>
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
            .btn-info {
                font-family: Raleway-SemiBold;
                font-size: 13px;
                color: #1c84c6;
                letter-spacing: 1px;
                line-height: 15px;
                border: 2px solid #1c84c6;
                border-radius: 40px;
                background: transparent;
                transition: all 0.3s ease 0s;
            }


            .badge-primary {
                background-color: #23c6c8;
                color: #FFFFFF;
            }

            .badge {
                background-color: #23c6c8;
                color: #23c6c8;
                font-family: 'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif;
                font-size: 11px;
                font-weight: 600;
                padding-bottom: 4px;
                padding-left: 6px;
                padding-right: 6px;
                text-shadow: none;
            }

            .badge {
                display: inline-block;
                min-width: 10px;
                padding: 3px 7px;
                font-size: 12px;
                font-weight: 700;
                line-height: 1;
                color: #fff;
                text-align: center;
                white-space: nowrap;
                vertical-align: middle;
                background-color: #23c6c8;
                border-radius: 10px;
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

                //String id = request.getParameter("id");
                String id = "104";
                String cliente_id = "";
                String rfc = "";
                String cliente_desc = "";
                String importeFactura = "";

                if (db.doDB(fac.consultarinfoCfdi(id, cve))) {
                    for (String[] rowZ : db.getResultado()) {
                        cliente_id = rowZ[0];
                        rfc = rowZ[1];
                        cliente_desc = rowZ[2];
                        importeFactura = rowZ[3];
                    }
                }
        %>
        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <div class="page-header">
                        <h3 class="page-heading">Facturación</h3>
                    </div>
                    <section>
                        <form>

                            <!-- Datos Recibo de Pago -->
                            <div class="col-lg-12 mb-5">
                                <div class="card">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h2 class="card-heading" style="font-weight: bold; color:#676a6c;"><%=rfc%> - <%=cliente_desc%></h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="form-group col-md-2" align="right">
                                                <div class="mb-3">
                                                    <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#337ab7;"><font size="2">Monto del pago $</font></label>
                                                </div> 
                                                <div class="mb-3" id="tcDelDR0" style="display:none;">
                                                    <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#337ab7;"><font size="2">TC del DR</font></label>
                                                </div> 
                                            </div>
                                            <div class="form-group col-md-2">
                                                <div class="mb-3">
                                                    <input autocomplete="off" class="form-control" data-val="true" data-val-required="El campo Serie es obligatorio." id="montoPago" maxlength="80" name="montoPago" type="number" value="<%=importeFactura%>">
                                                </div>  
                                                <div class="mb-3"  id="tcDelDR1" style="display:none;">
                                                    <input autocomplete="off" class="form-control" data-val="true" data-val-required="El campo Serie es obligatorio." id="Series_serie" maxlength="80" name="Series.serie" placeholder="$1.000000" type="number" value="" disabled="true">
                                                </div>  
                                            </div>
                                            <div class="form-group col-md-1">
                                                <div class="mb-3">
                                                    <select class="form-control selectpicker col-md-5" id="moneda_id" name="moneda_id" onchange="cliente(this.value)">
                                                        <option value="2" disabled selected>MXN</option>
                                                        <option value="3">USD</option>
                                                        <option value="1">EUR</option>
                                                    </select>
                                                </div> 
                                                <div class="mb-3"  id="tcDelDR2" style="display:none;">
                                                    <label class="custom-control-label" style="font-family: Arial; font-weight: bold;"><font size="2">MXN 1 = USD</font></label>
                                                </div>    
                                            </div>   

                                            <div class="form-group col-md-1">
                                                <div class="input-group mb-3" id="tcPago0" style="display:none;">
                                                    <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#337ab7;"><font size="2">TC del pago</font></label>
                                                </div>
                                            </div>
                                            <div class="form-group col-md-1">
                                                <div class="input-group mb-3" id="tcPago1" style="display:none;">
                                                    <input autocomplete="off" class="form-control" data-val="true" data-val-required="El campo Serie es obligatorio." id="Series_serie" maxlength="80" name="Series.serie" placeholder="$00.000" type="number" value="">
                                                </div>
                                            </div>
                                            <div class="form-group col-md-2">
                                                <div class="input-group mb-3" id="tcPago2" style="display:none;">
                                                    <label class="custom-control-label" style="font-family: Arial; font-weight: bold;"><font size="2">MXN 1 = USD</font></label>
                                                </div>
                                            </div> 

                                            <div class="form-group col-md-3">
                                                <div class="mb-3">
                                                    <div class="row row-cols-lg">
                                                        <div class="form-check col-md-4">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Fecha Emisión:</label>
                                                        </div>
                                                        <div class="form-check col-md-8">
                                                            <div class="input-group">
                                                                <input class="form-control input-datepicker" type="text" id="fecha_emision" name="fecha_emision" value="<%=fecha%>">
                                                                <div id="val_fecha_emision" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <div class="row row-cols-lg">
                                                        <div class="form-check col-md-4">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Hora Emisión:</label>
                                                        </div>
                                                        <div class="form-check col-md-8">
                                                            <div class="input-group">
                                                                <input type="time" class="form-control" id="hora_emision" name="hora_emision" value="<%=hora%>">
                                                                <div id="val_hora_emision" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>                
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-3" align="left" >
                                                <h5 style="color:#676a6c"><a id="totaltxt" style="display:none;" data-toggle="tooltip" data-placement="left" title="" data-original-title="Es la Suma de los importes del pago">Total: $ <%=importeFactura%> MXN</a></h5>
                                            </div>
                                        </div>
                                    </div>                          
                                </div>
                            </div>
                            <!-- Desglose del Pago / Facturas por pagar -->
                            <div class="col-lg-12 mb-5">
                                <div class="card">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <h2 class="card-heading">Desglose del Pago / Facturas por pagar</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body" id="AddTableFacturas"></div>                  
                                </div>
                            </div>                               
                            <!-- Información del pago -->
                            <div class="col-lg-12 mb-5">
                                <div class="card">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h2 class="card-heading">Información del pago</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-md-2">
                                                <div class="input-group">
                                                    <select id="serie" name="serie" class="form-control">
                                                        <option value="P">P</option>
                                                        <option value="F">F</option>
                                                    </select>
                                                    <span class="input-group-btn">
                                                        <a data-toggle="modal" class="btn btn-default" data-bs-toggle="modal" data-bs-target="#modalseries"><i class="fa fa-plus"></i></a>
                                                    </span>
                                                </div>
                                                <span class="field-validation-valid label label-danger" data-valmsg-for="IP.Series" data-valmsg-replace="true"></span>
                                                <input data-val="true" data-val-required="Se debe especificar la serie del pago" id="IP_serie" name="IP.serie" type="hidden" value="F">
                                            </div>
                                            <div class="col-md-2">
                                                <div class="form-group">
                                                    <%
                                                        if (db.doDB(fac.consultarFolioFactura("F", cve))) {
                                                            for (String[] row : db.getResultado()) {
                                                                out.println("<input type=\"number\" class=\"form-control\" id=\"folio\" name=\"folio\" value=\"" + row[0] + "\">");
                                                            }
                                                        }
                                                    %>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="input-group">
                                                    <select class="form-control" id="ctaOrdenante" name="ctaOrdenante" tabindex="0"><option value="">-- Cuenta Ordenante --</option>
                                                        <option value="BNM840515VB1">Banamex</option>
                                                    </select>
                                                    <span class="input-group-btn">
                                                        <a id="a_ctaOrdenante" data-toggle="modal" class="btn btn-default" data-bs-toggle="modal" data-bs-target="#modalcuentaOrdenante"><i class="fa fa-plus"></i></a>
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="col-md-2">
                                                <select class="form-control" data-val="true" data-val-required="Se debe especificar la forma de pago" id="formapago" name="formapago" tabindex="0"><option value="">-- Forma de pago --</option>
                                                    <%
                                                        if (db.doDB(fac.consultarFormaPago())) {
                                                            for (String[] row : db.getResultado()) {
                                                                out.println("<option value=\"" + row[0] + "\" >" + row[0] + " - " + row[1] + "</option>");
                                                            }
                                                        }
                                                    %>
                                                </select>
                                                <span class="field-validation-valid label label-danger" data-valmsg-for="IP.formapago" data-valmsg-replace="true"></span>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="input-group">
                                                    <input id="IP_SPago" name="IP.SPago" type="hidden" value="">
                                                    <input class="form-control" disabled="disabled" id="relcfdiPag" name="relcfdiPag" placeholder="CFDi Relacionados" type="text" value="">
                                                    <span class="input-group-btn">
                                                        <a data-toggle="modal" class="btn btn-default" data-bs-toggle="modal" data-bs-target="#modalPagosSustituir"><i class="fa fa-search"></i></a>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-md-2">
                                                <div class="form-group" id="data_1">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                        <input autocomplete="off" class="form-control" data-val="true" data-val-required="Se debe especificar la fecha de pago" id="fechaPago" name="fechaPago" title="Fecha de Pago" type="date" value="">
                                                        <span class="field-validation-valid label label-danger" data-valmsg-for="IP.fechaPago" data-valmsg-replace="true"></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div class="checkbox abc-checkbox abc-checkbox-success">
                                                    <input checked="" class="styled" type="checkbox" onclick="TieneHora()">
                                                    <label for="IP_TieneFechaHoraEmision"><font size="2">Especificar hora del pago</font></label>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="input-group">
                                                    <select class="form-control" id="ctaBeneficiarias" name="ctaBeneficiarias" tabindex="0"><option value="">-- Cuenta beneficiaria --</option>
                                                        <option value="BAF950102JP5">Afirme</option>
                                                    </select>
                                                    <span class="input-group-btn">
                                                        <a id="a_ctaBeneficiarias" data-toggle="modal" class="btn btn-default" data-bs-toggle="modal" data-bs-target="#modalcuentaBeneficiaria"><i class="fa fa-plus"></i></a>
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="col-md-2">
                                                <div class="form-group">
                                                    <input class="form-control" id="numOperacion" name="numOperacion" placeholder="#Operación" type="text" value="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <select class="form-control" data-val="true" data-val-required="Se debe especificar el Regimen Fiscal a utilizar" id="regimenFiscal" name="regimenFiscal" tabindex="0">
                                                    <option value="11">601 - General de Ley Personas Morales</option>
                                                </select>
                                                <span class="field-validation-valid label label-danger" data-valmsg-for="IP.RegimenFiscal" data-valmsg-replace="true"></span>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-lg-2">
                                                <div class="form-group" id="HoraEmision">
                                                    <div class="input-group clockpicker" data-placement="left" data-align="top" data-autoclose="true">
                                                        <input id="horaEmision" name="horaEmision" type="time" class="form-control" value="<%=hora%>"><!--value="">-->
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-time"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div class="checkbox abc-checkbox abc-checkbox-success">
                                                    <input checked="" value="value" class="styled" type="checkbox" onclick="EnviarDoc()">
                                                    <label for="IP_Bit_EnviarXMLyPDF"><font size="2">Enviar XML y PDF</font></label>
                                                </div>
                                            </div>
                                            <div class="col-lg-2" id="btnInfoAdcional">
                                                <a class="btn btn-primary text-nowrap" href="#" role="button" data-bs-toggle="modal" data-bs-target="#modalinformacioncomercial"><i class="fa fa-plus me-2"> </i>&nbsp;Información digital del pago</a>
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
                                    </div>
                                </div>
                            </div>

                            <!-- Botones controles -->
                            <div class="col-lg-12 mb-5">
                                <div class="row">
                                    <div class="col-lg-2">
                                        <a class="btn btn-primary" href="Search.jsp"><i class="fas fa-long-arrow-alt-left"></i>&nbsp;Regresar al listado</a>
                                    </div>
                                    <div class="col-lg-4"></div>
                                    <div class="col-lg-2">
                                        <a class="btn btn-primary text-nowrap" role="button" onclick="AddComplementoPago(1)"><i class="fa fa-file"> </i>&nbsp;Guardar sin Timbrar</a>
                                    </div>
                                    <div class="col-lg-2">
                                        <a class="btn btn-primary text-nowrap" role="button" onclick="visorPdfComplemento(1)"><i class="fa fa-search"> </i>&nbsp;Vista Previa</a>
                                    </div>
                                    <div class="col-lg-2">
                                        <a class="btn btn-primary text-nowrap" role="button" onclick="AddComplementoPago(2)"><i class="fa fa-file"> </i>&nbsp;Timbrar Pago</a>
                                    </div>
                                </div>
                            </div>            

                            <!-- modal - Facturas deudoras --> 
                            <div class="modal fade text-start" id="modalclvUnidad" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header border-0 bg-gray-100">
                                            <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Facturas deudoras</h3>
                                            <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">  
                                                <div class="col"> 
                                                    <div class="preload-wrapper">
                                                        <table class="table table-hover table-borderless align-middle mb-0" id="unidadDatatable">
                                                            <thead>
                                                                <tr>
                                                                    <th>Serie - Folio</th>
                                                                    <th>Total</th>
                                                                    <th>Saldo</th>
                                                                    <th>Pagos</th>
                                                                    <th>Moneda</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody id="AddTableClave" name="AddTableClave">
                                                                <%
                                                                    if (db.doDB(fac.consultarUnidadMedidaSAT())) {
                                                                        for (String[] row : db.getResultado()) {
                                                                %>
                                                                <tr>
                                                                    <td><%=row[1]%> - <%=row[2]%></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td>MXN</td>
                                                                    <td><a class="me-3 text-lg text-info" data-bs-dismiss="modal" onclick="listarUnidadSAT('<%=row[0]%>')"><i class="fas fa-arrow-alt-circle-right"></i></a></td>
                                                                </tr>
                                                                <%
                                                                        }
                                                                    }
                                                                %>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div> 
                                        </div>
                                        <div class="modal-footer border-0 bg-gray-100">
                                            <button class="btn btn-secondary" type="button" data-bs-dismiss="modal">Cerrar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- modal - series --> 
                            <div class="modal fade text-start" id="modalseries" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-sm">
                                    <div class="modal-content">
                                        <div class="modal-header border-0 bg-gray-100">
                                            <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Series</h3>
                                            <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="form-group ">
                                                    <select class="form-control" data-val="true" data-val-required="El campo Expedio En: es obligatorio." id="Series_expedido" name="Series.expedido"><option value="">Lugar de Expedición</option>
                                                        <option value="c73c375a-0613-4156-bcdf-f6be86418842">Nueva Oficina</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row">
                                                <div class="form-group ">
                                                    <input autocomplete="off" class="form-control" data-val="true" data-val-required="El campo Serie es obligatorio." id="Series_serie" maxlength="80" name="Series.serie" placeholder="Nombre de la Serie" type="text" value="">
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row">
                                                <div class="form-group ">
                                                    <div id="LabelMsj" style="display: none;"><span class="badge badge-info">Folio Ultimo Asignado</span></div>
                                                    <div class="input-group bootstrap-touchspin"><span class="input-group-btn"><button class="btn btn-primary bootstrap-touchspin-down" type="button">-</button></span><span class="input-group-addon bootstrap-touchspin-prefix" style="display: none;"></span><input autocomplete="off" class="form-control" data-val="true" data-val-number="The field Folio Ultimo Asignado must be a number." data-val-required="El campo Folio Ultimo Asignado es obligatorio." id="Series_folioultimo" maxlength="80" name="Series.folioultimo" placeholder="0" type="text" value="0" style="display: block;"><span class="input-group-addon bootstrap-touchspin-postfix" style="display: none;"></span><span class="input-group-btn"><button class="btn btn-primary bootstrap-touchspin-up" type="button">+</button></span></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-white" id="cerrarSerie" data-bs-dismiss="modal">Cerrar</button>
                                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="AddSerie();">Guardar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- modal - cuenta ordenante --> 
                            <div class="modal fade text-start" id="modalcuentaOrdenante" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-sm">
                                    <div class="modal-content">
                                        <div class="modal-header border-0 bg-gray-100">
                                            <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Cuenta Bancaria</h3>
                                            <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="form-group">
                                                    <div id="LabelMsj1" style="display: none;"><span class="badge badge-info">bancos</span></div>
                                                    <select class="form-control" id="idBancos" name="idBancos">
                                                        <option value="">-- Seleccione un banco --</option>
                                                        <option value="BBA830831LJ2">BBVABancomer</option>
                                                        <option value="BNM840515VB1">Banamex</option>
                                                        <option value="BSM970519DU8">Santander</option>
                                                        <option value="BMN930209927">Banorte/IXE</option>
                                                        <option value="BII931004P61">Inbursa</option>
                                                        <option value="HMI950125KG8">HSBC</option>
                                                        <option value="BAI0205236Y8">Azteca</option>
                                                        <option value="SIN9412025I4">Scotiabank Inverlat</option>
                                                        <option value="BSI061110963">Bancoppel</option>
                                                        <option value="AEB960223JP7">American Express</option>
                                                        <option value="BIN940223KE0">Invex</option>
                                                        <option value="BAF950102JP5">Afirme</option>
                                                        <option value="BBA940707IE1">Bajío</option>
                                                        <option value="BRM940216EQ6">Banregio</option>
                                                        <option value="BAF060524EV6">Banco Famsa</option>
                                                        <option value="BMI061005NY5">Banco Multiva</option>
                                                        <option value="BMI9704113PA">Monex</option>
                                                        <option value="BNY080206UR9">CIBanco</option>
                                                        <option value="BFS060308V14">Consubanco</option>
                                                        <option value="BMI9312038R3">Mifel</option>
                                                        <option value="BCI001030ECA">Compartamos</option>
                                                        <option value="FNE050520EE6">Forjadores</option>
                                                        <option value="IBI061030GD4">Intercam Ban</option>
                                                        <option value="VBI070816RU7">Volkswagen</option>
                                                        <option value="BAM0511076B3">Autofin</option>
                                                        <option value="BAN950525MD6">Bansi</option>
                                                        <option value="AGR030219425">Bankaool</option>
                                                        <option value="PBI061115SC6">BM Actinver</option>
                                                        <option value="BCN061114TQ4">ABC Capital</option>
                                                        <option value="BVM951002LX0">Ve Por Más</option>
                                                        <option value="YKI1202167J4">Dondé</option>
                                                        <option value="RBS950707FC7">Investa Bank</option>
                                                        <option value="BIN931011519">Interacciones</option>
                                                        <option value="HCM010608EG1">Inmobiliario</option>
                                                        <option value="BBA130722BR7">Bancrea</option>
                                                        <option value="BJP950104LJ5">JP Morgan</option>
                                                        <option value="BCS020221524">Credit Suisse First Boston</option>
                                                        <option value="BBM060102M82">Barclays</option>
                                                        <option value="GFI0306041K7">Banco Finter</option>
                                                        <option value="BAM9504035J2">Bamsa</option>
                                                        <option value="BTM960401DV7">Tokyo</option>
                                                        <option value="DBM000228J35">Deutsche</option>
                                                        <option value="UBM061103HX0">UBS Bank</option>
                                                        <option value="BBS110906HD3">BM Base</option>
                                                        <option value="BPS121217FS6">Pagatodo</option>
                                                        <option value="DCS150115331">Ban Sabadell</option>
                                                        <option value="GME140919LC9">ICBC</option>
                                                        <option value="SMM1503196A4">Shinhan</option>
                                                        <option value="MBM1602257N2">MBM</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row">
                                                <div class="form-group ">
                                                    <div id="LabelMsj2" style="display: none;"><span class="badge badge-info">rfc</span></div>
                                                    <input autocomplete="off" class="form-control" data-val="true" data-val-maxlength="El campo RFC del Banco debe ser un tipo de cadena o matriz con una longitud máxima de '13'." data-val-maxlength-max="13" data-val-minlength="El campo RFC del Banco debe ser un tipo de cadena o matriz con una longitud mínima de '12'." data-val-minlength-min="12" data-val-regex="El RFC del Banco no es compatible con la estructura que solicita el SAT" data-val-regex-pattern="[A-Z,Ñ,&amp;amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z,0-9]?[A-Z,0-9]?[0-9,A-Z]?" data-val-required="El campo RFC del Banco es obligatorio." id="Bancos_rfc" maxlength="80" name="Bancos.rfc" placeholder="Ingrese el RFC del banco" type="text" value="">
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row">
                                                <div class="form-group ">
                                                    <div id="LabelMsj3" style="display: none;"><span class="badge badge-info">10, 11, 16 o 18 digitos, o de 10 a 50 alfanuméricos.</span></div>
                                                    <input autocomplete="off" class="form-control" data-val="true" data-val-regex="Debe de ser 10, 11, 16 o 18 digitos, o de 10 a 50 alfanuméricos." data-val-regex-pattern="^\d{10}$|^\d{11}$|^\d{16}$|^\d{18}$|^\w{10,50}$" data-val-required="El campo Cuenta o Clabe es obligatorio." id="Bancos_cuenta" maxlength="80" name="Bancos.cuenta" pattern="^'\d{10}$|^\d{11}$|^\d{16}$|^\d{18}$" placeholder="Ingrese su cuenta ó clabe" type="text" value="">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-white" id="cerrarSerie" data-bs-dismiss="modal">Cerrar</button>
                                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="AddSerie();">Guardar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- modal - cuenta beneficiaria --> 
                            <div class="modal fade text-start" id="modalcuentaBeneficiaria" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-sm">
                                    <div class="modal-content">
                                        <div class="modal-header border-0 bg-gray-100">
                                            <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Cuenta Bancaria</h3>
                                            <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="form-group">
                                                    <div id="LabelMsj1" style="display: none;"><span class="badge badge-info">bancos</span></div>
                                                    <select class="form-control" id="idBancos" name="idBancos">
                                                        <option value="">-- Seleccione un banco --</option>
                                                        <option value="BBA830831LJ2">BBVABancomer</option>
                                                        <option value="BNM840515VB1">Banamex</option>
                                                        <option value="BSM970519DU8">Santander</option>
                                                        <option value="BMN930209927">Banorte/IXE</option>
                                                        <option value="BII931004P61">Inbursa</option>
                                                        <option value="HMI950125KG8">HSBC</option>
                                                        <option value="BAI0205236Y8">Azteca</option>
                                                        <option value="SIN9412025I4">Scotiabank Inverlat</option>
                                                        <option value="BSI061110963">Bancoppel</option>
                                                        <option value="AEB960223JP7">American Express</option>
                                                        <option value="BIN940223KE0">Invex</option>
                                                        <option value="BAF950102JP5">Afirme</option>
                                                        <option value="BBA940707IE1">Bajío</option>
                                                        <option value="BRM940216EQ6">Banregio</option>
                                                        <option value="BAF060524EV6">Banco Famsa</option>
                                                        <option value="BMI061005NY5">Banco Multiva</option>
                                                        <option value="BMI9704113PA">Monex</option>
                                                        <option value="BNY080206UR9">CIBanco</option>
                                                        <option value="BFS060308V14">Consubanco</option>
                                                        <option value="BMI9312038R3">Mifel</option>
                                                        <option value="BCI001030ECA">Compartamos</option>
                                                        <option value="FNE050520EE6">Forjadores</option>
                                                        <option value="IBI061030GD4">Intercam Ban</option>
                                                        <option value="VBI070816RU7">Volkswagen</option>
                                                        <option value="BAM0511076B3">Autofin</option>
                                                        <option value="BAN950525MD6">Bansi</option>
                                                        <option value="AGR030219425">Bankaool</option>
                                                        <option value="PBI061115SC6">BM Actinver</option>
                                                        <option value="BCN061114TQ4">ABC Capital</option>
                                                        <option value="BVM951002LX0">Ve Por Más</option>
                                                        <option value="YKI1202167J4">Dondé</option>
                                                        <option value="RBS950707FC7">Investa Bank</option>
                                                        <option value="BIN931011519">Interacciones</option>
                                                        <option value="HCM010608EG1">Inmobiliario</option>
                                                        <option value="BBA130722BR7">Bancrea</option>
                                                        <option value="BJP950104LJ5">JP Morgan</option>
                                                        <option value="BCS020221524">Credit Suisse First Boston</option>
                                                        <option value="BBM060102M82">Barclays</option>
                                                        <option value="GFI0306041K7">Banco Finter</option>
                                                        <option value="BAM9504035J2">Bamsa</option>
                                                        <option value="BTM960401DV7">Tokyo</option>
                                                        <option value="DBM000228J35">Deutsche</option>
                                                        <option value="UBM061103HX0">UBS Bank</option>
                                                        <option value="BBS110906HD3">BM Base</option>
                                                        <option value="BPS121217FS6">Pagatodo</option>
                                                        <option value="DCS150115331">Ban Sabadell</option>
                                                        <option value="GME140919LC9">ICBC</option>
                                                        <option value="SMM1503196A4">Shinhan</option>
                                                        <option value="MBM1602257N2">MBM</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row">
                                                <div class="form-group ">
                                                    <div id="LabelMsj2" style="display: none;"><span class="badge badge-info">rfc</span></div>
                                                    <input autocomplete="off" class="form-control" data-val="true" data-val-maxlength="El campo RFC del Banco debe ser un tipo de cadena o matriz con una longitud máxima de '13'." data-val-maxlength-max="13" data-val-minlength="El campo RFC del Banco debe ser un tipo de cadena o matriz con una longitud mínima de '12'." data-val-minlength-min="12" data-val-regex="El RFC del Banco no es compatible con la estructura que solicita el SAT" data-val-regex-pattern="[A-Z,Ñ,&amp;amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z,0-9]?[A-Z,0-9]?[0-9,A-Z]?" data-val-required="El campo RFC del Banco es obligatorio." id="Bancos_rfc" maxlength="80" name="Bancos.rfc" placeholder="Ingrese el RFC del banco" type="text" value="">
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row">
                                                <div class="form-group ">
                                                    <div id="LabelMsj3" style="display: none;"><span class="badge badge-info">10, 11, 16 o 18 digitos, o de 10 a 50 alfanuméricos.</span></div>
                                                    <input autocomplete="off" class="form-control" data-val="true" data-val-regex="Debe de ser 10, 11, 16 o 18 digitos, o de 10 a 50 alfanuméricos." data-val-regex-pattern="^\d{10}$|^\d{11}$|^\d{16}$|^\d{18}$|^\w{10,50}$" data-val-required="El campo Cuenta o Clabe es obligatorio." id="Bancos_cuenta" maxlength="80" name="Bancos.cuenta" pattern="^'\d{10}$|^\d{11}$|^\d{16}$|^\d{18}$" placeholder="Ingrese su cuenta ó clabe" type="text" value="">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-white" id="cerrarSerie" data-bs-dismiss="modal">Cerrar</button>
                                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="AddSerie();">Guardar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- modal - Información digital del pago --> 
                            <div class="modal fade text-start" id="modalinformacioncomercial" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header border-0 bg-gray-100">
                                            <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Información digital del pago</h3>
                                            <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Tipo de cadena de pago:</label>
                                                <select class="form-control">
                                                    <option value="01">01 - Spei</option>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Certificado que corresponde al pago:</label>
                                                <div class="form-floating mb-3">
                                                    <input class="form-control"  type="text" id="info_observaciones" name="info_observaciones" placeholder="">
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Cadena original del comprobante de pago:</label>
                                                <input class="form-control" type="text" id="num_proveedor" name="num_proveedor" placeholder="">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Sello digital del pago:</label>
                                                <div class="form-floating mb-3">
                                                    <input class="form-control"  type="text" id="info_observaciones" name="info_observaciones" placeholder="">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn btn-default" type="button" data-bs-dismiss="modal">Cerrar</button>
                                            <button class="btn btn-primary" type="button" onclick="AddInfoComercial()">Agregar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- modal - Folios de Pagos a Sustituir --> 
                            <div class="modal fade text-start" id="modalPagosSustituir" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header border-0 bg-gray-100">
                                            <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Folios de Pagos a Sustituir</h3>
                                            <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Tipo Relación:</label>
                                                <input class="form-control" id="tprelacion" value="Sustitución de los CFDIS Previos - 04" disabled="disabled" readonly="">
                                            </div>
                                            <br>
                                            <div class="form-group">
                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Seleccione un CFDI de Pagos (Solo Cancelados)</label>
                                                <select class="form-control" id="ListaCancelados">
                                                    <option value="0"> </option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn btn-default" type="button" data-bs-dismiss="modal">Cerrar</button>
                                            <button class="btn btn-primary" type="button" onclick="AddInfoComercial()">Agregar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- modal - Vista Previa --> 
                            <div class="modal fade text-start" id="modalVistaPreviaComplemento" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header border-0 bg-gray-100">
                                            <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Vista Previa Recibo de Pago:</h3>
                                            <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="container" id="visor1"> 
                                                <iframe class="responsive-iframe" src="<%=request.getContextPath()%>/MostrarPDF_COMPL" width="100%" height="500px"></iframe>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- contadores tablas -->
                            <input type="hidden" id="numConceptos" name="numConceptos">

                            <!-- input's hidden -->
                            <input type="hidden" id="cbdiv_id" name="cbdiv_id" value="<%=cve%>">
                            <input type="hidden" id="cliente_id" name="cliente_id" value="<%=cliente_id%>">
                            <input type="hidden" id="idFacturas" name="idFacturas" value="<%=id%>">
                            <input type="hidden" id="totalGlobal" name="totalGlobal" value="<%=importeFactura%>">

                        </form>    
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
        <!-- Procesos Complemento de Pago General -->
        <script src="../lib/validations3.3/3.3/recibopago3.3/processesReciboPagoGral_3.3.js" type="text/javascript"></script>
        <!-- Procesos Complemento de Pago-->
        <script src="../lib/validations3.3/3.3/recibopago3.3/processesReciboPago_3.3.js" type="text/javascript"></script>
        <!-- JavaScript files-->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
        <!-- Data Tables-->
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"></script>
        <!-- CMS Category JS-->
        <!--<script src="../lib/js/datatable-conceptos.js" type="text/javascript"></script>-->
        <script src="../lib/js/datatable-clave-unidad.js" type="text/javascript"></script>
        <script src="../lib/js/datatable-relacion-uuid.js" type="text/javascript"></script>
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