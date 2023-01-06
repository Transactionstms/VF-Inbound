<%-- 
    Document   : Create3.3
    Created on : 29/12/2021, 06:02:53 PM
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
        <title>Nueva Facturación 3.3</title>
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
        <!-- jQuery/show modal -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- choise -->
        <link href="../lib/choise/styles/choices.min.css" rel="stylesheet" type="text/css"/>
        <script src="../lib/choise/scripts/choices.min.js" type="text/javascript"></script>
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
        </style>
        <!-- Connection Status Red -->
        <link href="../lib/validations/gral_3.3/header/connectionStatus.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String cve = (String) ownsession.getAttribute("cbdivcuenta");
                ConsultasQuery fac = new ConsultasQuery();
                int contImpuestoConcepto = 0;
                
                //SECCIÓN: GENERACIÓN DEL FOLIO IMPUESTO PARA RELACIONAR CONCEPTO CON IMPUESTO.
                if (db.doDB(fac.consultarFolioImpuesto3_3(cve))) {  //Consultar folio actual
                    for (String[] rowI : db.getResultado()) {
                        contImpuestoConcepto = Integer.parseInt(rowI[0])+1;
                    }
                }
        %>
        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <div class="page-header">
                        <h3 class="page-heading">Facturación 3.3</h3>
                    </div>
                    <div class="unwired alert alert-danger">¡Se ha perdido su conexión! TACTS debe de estar conectado a Internet para su correcto funcionamiento.</div>
                    <section>
                        <form>
                            <!-- Datos Generales -->
                            <div class="col-lg-12 mb-5">
                                <div class="card">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <h2 class="card-heading">Cliente</h2>
                                            </div>
                                            <div class="col-md-6">
                                                <h2 class="card-heading">Comprobante</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <div class="input-group mb-3">
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
                                                    <button class="btn btn-primary col-md-1" id="button-addon1" type="button"><i class="fa fa-edit" ></i></button>
                                                    <div id="val_cliente" style="color:#3b83bd;"></div>
                                                </div>
                                                <div class="form-group mb-3"> <!-- Btn clientes frecuentes -->
                                                    <div class="col-md-12">
                                                        <%
                                                            if (db.doDB(fac.listarClientesFrecuentes(cve))) {
                                                                for (String[] row : db.getResultado()) {
                                                                    out.println("<a class=\"btn btn-info\" onclick=\"cliente(" + row[0] + ")\">" + row[1] + "</a>");
                                                                }
                                                            }
                                                        %>
                                                    </div> 
                                                </div>
                                                <div class="form-group mb-3"> 
                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;"><font>RFC:</font></label>
                                                    <input class="form-control" type="text" id="rfc" name="rfc" onkeyup="this.value = this.value.toUpperCase()" minlength="12" maxlength="13" autocomplete="off">
                                                    <div id="val_rfc" style="color:#3b83bd;"></div>
                                                </div>
                                                <div class="form-group mb-3">
                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Uso de CFDI:</label>
                                                    <select class="form-control selectpicker col-md-5" id="usocfdi_id" name="usocfdi_id">
                                                        <option value="0" disabled selected>-- Seleccione un uso de CFDI --</option>
                                                        <%
                                                            if (db.doDB(fac.consultarUsoCfdi())) {
                                                                for (String[] row : db.getResultado()) {
                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[1] + " - " + row[2] + "</option>");
                                                                }
                                                            }
                                                        %>
                                                    </select>
                                                    <div id="val_usocfdi_id" style="color:#3b83bd;"></div>
                                                </div>
                                                <div class="form-group mb-3">
                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Correros:&nbsp;<i class="fas fa-pencil-alt"></i></label>
                                                    <input class="form-control" type="text" id="correos" name="correos" autocomplete="off">
                                                    <div id="val_correos" style="color:#3b83bd;"></div>
                                                </div>
                                                <div class="mb-3">
                                                    <div class="form-check mb-4">
                                                        <input class="form-check-input" id="check_xml" name="check_xml" type="checkbox" checked="true"> <!--value="1" onclick="habilitarXML(this.value)"-->
                                                        <label class="custom-control-label" for="customCheck1">Enviar XML</label>
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <div class="form-check mb-4">
                                                        <input class="form-check-input" id="check_pdf" name="check_pdf" type="checkbox" checked="true"> <!--value="2" onclick="habilitarPDF(this.value)"-->
                                                        <label class="custom-control-label" for="customCheck1">Enviar PDF</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <div class="mb-3">
                                                    <select class="form-control" data-val="true" id="tipo_descripcion" name="tipo_descripcion">
                                                        <option value="1">Nueva Oficina</option>
                                                    </select>
                                                    <div id="val_tipo_descripcion" style="color:#3b83bd;"></div>
                                                </div>
                                                <div class="mb-3">
                                                    <div class="row row-cols-lg">
                                                        <div class="form-check col-md-4">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Fecha Emisión:</label>
                                                        </div>
                                                        <div class="form-check col-md-8">
                                                            <div class="input-group">
                                                                <input class="form-control input-datepicker" type="text" id="fecha_emision" name="fecha_emision" value="<%=fecha%>" autocomplete="off">
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
                                                <div class="mb-3">
                                                    <div class="row row-cols-lg">
                                                        <div class="form-group col-md-4">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">&nbsp;&nbsp;Serie y Folio:</label>
                                                        </div>
                                                        <div class="form-group col-md-4">
                                                            <select id="serie" name="serie" class="form-control" onchange="listarFolioFactura(this.value)">
                                                                <%
                                                                    if (db.doDB(fac.consultarSerieFactura(cve))) {
                                                                        for (String[] row : db.getResultado()) {
                                                                            out.println("<option value=\"" + row[0] + "\">" + row[0] + "</option>");
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                        </div>
                                                        <div class="form-group col-md-4" id="comboFolioFactura"></div>
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <div class="row row-cols-lg">
                                                        <div class="form-check col-md-4">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Tipo de comprobante:</label>
                                                        </div>
                                                        <div class="form-check col-md-8">
                                                            <div class="input-group">
                                                                <select class="form-control" data-val="true" id="comprobante_id" name="comprobante_id" onchange="tipoComprobante(this.value)">
                                                                    <%
                                                                        if (db.doDB(fac.consultarTipoComprobante())) {
                                                                            for (String[] row : db.getResultado()) {
                                                                                out.println("<option value=\"" + row[0] + "\" >" + row[1] + " - " + row[2] + "</option>");
                                                                            }
                                                                        }
                                                                    %>
                                                                </select>
                                                                <div id="val_comprobante_id" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <div class="row row-cols-lg">
                                                        <div class="form-check col-md-4">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Tipo de documento:</label>
                                                        </div>
                                                        <div class="form-check col-md-8">
                                                            <div class="input-group">
                                                                <select class="form-control" data-val="true" id="documento_id" name="documento_id" onchange="tipoDocumento(this.value)">
                                                                    <%
                                                                        if (db.doDB(fac.consultarTipoDocumento())) {
                                                                            for (String[] row : db.getResultado()) {
                                                                                out.println("<option value=\"" + row[0] + "\">" + row[1] + "</option>");
                                                                            }
                                                                        }
                                                                    %>
                                                                </select>
                                                                <div id="val_documento_id" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <div class="row row-cols-lg">
                                                        <div class="form-check col-md-4">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Régimen fiscal:</label>
                                                        </div>
                                                        <div class="form-check col-md-8">
                                                            <div class="input-group">
                                                                <select class="form-control" data-val="true" id="regimen_id" name="regimen_id">
                                                                    <%
                                                                        if (db.doDB(fac.consultarRegimenFiscal())) {
                                                                            for (String[] row : db.getResultado()) {
                                                                                out.println("<option value=\"" + row[0] + "\" >" + row[1] + " - " + row[2] + "</option>");
                                                                            }
                                                                        }
                                                                    %>
                                                                </select>
                                                                <div id="val_regimen_id" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <div class="row row-cols-lg">
                                                        <div class="form-check col-md-4">
                                                        </div>
                                                        <div class="form-check col-md-8">
                                                            <div class="input-group" id="addCartaPorte" style="display:none;">
                                                                <a class="btn btn-info" data-bs-toggle="modal" data-bs-target="#modalCartaDePorte"><i class="fa fa-plus me-2"></i>&nbsp;Agregar Carta Porte</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>                
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Concepto -->
                            <div class="col-lg-12 mb-5">
                                <div class="card">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <h2 class="card-heading">Concepto</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-md-1">
                                                    <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Comentario</label>
                                                    <div class="input-group mb-3">
                                                        <div class="form-check mb-4">
                                                            <input class="form-check-input" id="check_comentario" name="check_comentario" type="checkbox" onclick="habilitarDetalle()">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-4" id="flotante_concepto1">
                                                    <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Descripción:</label>
                                                        <select class="form-control col-md-5" data-trigger name="choices-single-default" id="choices-single-default" placeholder="Ingrese Busqueda" onchange="detailsConcepto(this.value)">
                                                            <option value="0">...</option>
                                                            <%
                                                                if (db.doDB(fac.consultarDescripcion(cve))) {
                                                                    for (String[] row : db.getResultado()) {
                                                                        out.println("<option value=\"" + row[1] + "\">" + row[0] + " - " + row[2] + "</option>"); 
                                                                    }
                                                                }
                                                            %>
                                                        </select>   
                                                </div>
                                                <div class="col-md-1" id="flotante_concepto2">
                                                    <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Cantidad:</label>
                                                    <div class="input-group mb-3">
                                                        <input class="form-control" type="number" id="cantidad" name="cantidad" min="1" autocomplete="off">
                                                        <div id="val_cantidad" style="color:#3b83bd;"></div>
                                                    </div>
                                                </div>
                                                <div class="col-md-2" id="flotante_concepto3">
                                                    <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Clave SAT:</label>
                                                    <div class="input-group mb-3">
                                                        <input class="form-control" type="text" id="rel_clvsat_id" name="rel_clvsat_id" placeholder="ClaveSAT" autocomplete="off">
                                                        <button class="btn btn-primary col-md-3" id="button-addon1" type="button" data-bs-toggle="modal" data-bs-target="#modalconceptos"><i class="fa fa-search" ></i></button>
                                                        <div id="val_relclvsatId" style="color:#3b83bd;"></div>
                                                    </div>
                                                </div>
                                                <div class="col-md-2" id="flotante_concepto4">
                                                    <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Unidad SAT:</label>
                                                    <div class="input-group mb-3">
                                                        <input class="form-control" type="text" id="unidad_sat_id" name="unidad_sat_id" placeholder="UnidadSAT" autocomplete="off">
                                                        <button class="btn btn-primary col-md-3" id="button-addon1" type="button" data-bs-toggle="modal" data-bs-target="#modalclvUnidad"><i class="fa fa-search" ></i></button>
                                                        <div id="val_unidadSatId" style="color:#3b83bd;"></div>
                                                    </div>
                                                </div>
                                                <div class="col-md-2" id="flotante_concepto5">
                                                    <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Precio Unitario:</label>
                                                    <div class="input-group mb-3">
                                                        <input class="form-control" type="text" id="precio_unitario" name="precio_unitario" placeholder="$" readonly>
                                                        <div id="val_precioUnitario" style="color:#3b83bd;"></div>
                                                    </div>
                                                </div>
                                                <div class="col-md-11" id="flotante_detalle" style="display:none;">
                                                    <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Detalle:</label>
                                                    <div class="form-floating mb-3">
                                                        <input class="form-control"  type="text" id="comentarios" name="comentarios" autocomplete="off">
                                                        <label for="floatingInput">Escriba su comentario aquí(hasta 1000 caracteres)</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-9">
                                                <%  /*int cont = 0;
                                                    if (db.doDB(fac.listarConceptosFrecuentes(cve))) {
                                                        for (String[] row : db.getResultado()) {
                                                            out.println("<a class=\"btn btn-info\" id=\"desc" + cont + "\" name=\"desc" + cont + "\" onclick=\"detailsConcepto(" + row[1] + ")\">" + row[2] + "</a>");
                                                        }
                                                    }*/
                                                %>
                                            </div>
                                            <div class="col-lg-3">
                                                <a class="btn btn-primary text-nowrap" role="button" style="float: right;" onclick="AddConceptos()" ><i class="fa fa-plus me-2"> </i>&nbsp;Agregar Concepto</a>
                                            </div>
                                        </div> 
                                        <br>
                                        <table class="table" id="mytable" name="mytable">
                                            <!--<table class="table table-hover table-borderless align-middle mb-0" id="conceptosDatatable">-->
                                            <thead>
                                                <tr>
                                                    <th>Comentario</th>
                                                    <th>Descripción</th>
                                                    <th>Cantidad</th>
                                                    <th>Precio Unitario</th>
                                                    <th>Importe Descuento</th>
                                                    <th>Importe</th>
                                                    <th>Opciones</th>
                                                </tr>
                                            </thead>
                                            <tbody id="AddTableConceptos"></tbody>
                                            <!--<tbody id="AddTableUUID" name="AddTableUUID"></tbody>-->
                                        </table>
                                    </div>                  
                                </div>
                            </div>                               
                            <!-- Condiciones Comerciales y Totales -->
                            <div class="col-lg-12 mb-5">
                                <div class="card">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-8">
                                                <h2 class="card-heading">Condiciones comerciales</h2>
                                            </div>
                                            <div class="col-md-4">
                                                <h2 class="card-heading">Totales</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="form-group col-md-8 mb-3">
                                                <div class="row g-3 align-items-center mb-3">
                                                    <div class="col-4">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Método de Pago</label>
                                                        <select class="form-control selectpicker" id="metodo_id" name="metodo_id">
                                                            <option value="0" disabled selected>-- Seleccione método de pago --</option>
                                                            <%                                                                if (db.doDB(fac.consultarMetodoPago())) {
                                                                    for (String[] row : db.getResultado()) {
                                                                        out.println("<option value=\"" + row[0] + "\" >" + row[1] + " - " + row[2] + "</option>");
                                                                    }
                                                                }
                                                            %>
                                                        </select>
                                                        <div id="val_metodo_id" style="color:#3b83bd;"></div>
                                                    </div>
                                                    <div class="col-4">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Forma de Pago</label>
                                                        <select class="form-control selectpicker col-md-5" id="forma_id" name="forma_id">
                                                            <option value="0" disabled selected>-- Seleccione forma de pago --</option>
                                                            <%
                                                                if (db.doDB(fac.consultarFormaPago())) {
                                                                    for (String[] row : db.getResultado()) {
                                                                        out.println("<option value=\"" + row[0] + "\" >" + row[0] + " - " + row[1] + "</option>");
                                                                    }
                                                                }
                                                            %>
                                                        </select>
                                                        <div id="val_forma_id" style="color:#3b83bd;"></div>
                                                    </div>
                                                    <div class="col-4">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Condiciones de Pago</label>
                                                        <input class="form-control" type="text" id="condiciones_pago" name="condiciones_pago" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                        <div id="val_condiciones_pago" style="color:#3b83bd;"></div>
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="row g-3 align-items-center mb-3">
                                                    <div class="col-4">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Moneda</label>
                                                        <select class="form-control selectpicker" id="moneda_id" name="moneda_id" onchange="tipoCambio(this.value)">
                                                            <option value="2" disabled selected>MXN - Peso Mexicano</option>
                                                            <%
                                                                if (db.doDB(fac.consultarMoneda())) {
                                                                    for (String[] row : db.getResultado()) {
                                                                        out.println("<option value=\"" + row[0] + "\" >" + row[1] + "</option>");
                                                                    }
                                                                }
                                                            %>
                                                        </select>
                                                        <div id="val_moneda_id" style="color:#3b83bd;"></div>
                                                    </div>
                                                    <div class="col-4">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Tipo de Cambio</label>
                                                        <input class="form-control" type="number" id="tipo_cambio" name="tipo_cambio" autocomplete="off" disabled="true">
                                                        <div id="val_tipo_cambio" style="color:#3b83bd;"></div>
                                                    </div>
                                                    <div class="col-4">
                                                        <input class="form-check-input" id="check_addFactura" name="check_addFactura" type="checkbox">
                                                        <label class="custom-control-label" for="customCheck1" style="color:#707375;">Asignar Factura Pagada</label>
                                                    </div>
                                                </div>

                                                <br>

                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <a class="btn btn-primary text-nowrap" role="button" data-bs-toggle="modal" data-bs-target="#modalcfdirelacion"><i class="fa fa-link me-2"> </i>&nbsp;CFDI Relacionados</a>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <a class="btn btn-primary text-nowrap" href="#" role="button" data-bs-toggle="modal" data-bs-target="#modalinformacioncomercial"><i class="fa fa-plus me-2"> </i>&nbsp;Información Comercial</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group col-md-4" id="AddDetalleTotales">
                                                <!--<table class="table" id="datatable1">
                                                    <thead>
                                                        <tr><th class="" scope="col">Subtotal:</th>
                                                            <td><div id="subtotalGral"></div></td></tr>
                                                        <tr><th class="">Importe Descuentos:</th>
                                                            <td><div id="descuentoGral"></div></td></tr>
                                                        <tr><th class="">Traslados:</th>
                                                            <td><div id="trasladosGral"></div></td></tr>
                                                        <tr><th class="">Retenciones:</th>
                                                            <td><div id="retencionesGral"></div></td></tr>
                                                        <tr><th class="">Total:</th>
                                                            <td><div id="totalGral"></div></td></tr>
                                                    </thead>
                                                    <tbody>
                                                    </tbody>
                                                </table>-->
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
                            <!-- Complementos -->
                            <!--<div class="col-lg-12 mb-5">
                                <div class="card">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-8">
                                                <h2 class="card-heading">Complementos</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="form-group col-md-8 mb-3">
                                                <a class="btn-link" href="#">+ Contratar Complementos</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> -->
                            <!-- Botones controles -->
                            <div class="col-lg-12 mb-5">
                                <div class="row">
                                    <div class="col-lg-8">
                                        <a class="btn btn-primary text-nowrap" role="button" onclick="consultarExistenciaUUID(1)"><i class="fa fa-file"></i>&nbsp;Guardar sin Timbrar</a> 
                                    </div>
                                    <div class="col-lg-2">
                                        <a class="btn btn-primary text-nowrap" role="button" onclick="visorPdfFactura(1)"><i class="fa fa-file"> </i>&nbsp;Vista Previa</a>
                                    </div>
                                    <div class="col-lg-2">
                                        <a class="btn btn-primary text-nowrap" role="button" onclick="consultarExistenciaUUID(2)"><i class="fa fa-file"> </i>&nbsp;Emitir Factura</a>
                                    </div>
                                    <!--<div class="col-lg-2">
                                        <a class="btn btn-primary text-nowrap" role="button" onclick="alert()"><i class="fa fa-file"> </i>&nbsp;API</a>
                                    </div>-->
                                    <!--<div class="col-lg-2">
                                        <a class="btn btn-primary text-nowrap" role="button" onclick="showPreloader()"><i class="fa fa-file"> </i>&nbsp;Loader</a>
                                    </div>-->
                                </div>
                            </div>
                            <!-- modal - Catálogo SAT de productos --> 
                            <div class="modal fade text-start" id="modalconceptos" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header border-0 bg-gray-100">
                                            <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Catálogo SAT de Productos o Servicios</h3>
                                            <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <select class="form-control" id="conceptos" name="conceptos" onchange="search_divisiones(this.value)">
                                                    <option value="0" disabled selected>-- Seleccione una descripción --</option>
                                                    <%
                                                        if (db.doDB(fac.consultarClasificacionSAT())) {
                                                            for (String[] row : db.getResultado()) {
                                                                out.println("<option value=\"" + row[0] + "\" >" + row[1] + "</option>");
                                                            }
                                                        }
                                                    %>
                                                </select>
                                                <div id="val_conceptos" style="color:#3b83bd;"></div>
                                            </div>

                                            <div class="mb-3" id="divisiones"></div>
                                            <div class="mb-3" id="grupos"></div>
                                            <div class="mb-3" id="clases"></div>
                                            <div class="mb-3" id="claves"></div>

                                            <!--Detalle clave_sat-->
                                            <div id="producto_ser_sat"></div>
                                            <br>
                                            <div class="mb-3">
                                                <button class="btn btn-success" id="button-addon2" type="button">Buscar</button> <button type="button" class="btn btn-warning" onclick="cleanFormularioClaveSAT()">Limpiar Resultados</button>
                                            </div>
                                        </div>       
                                        <div class="modal-footer border-0 bg-gray-100">
                                            <button class="btn btn-success" type="button" data-bs-dismiss="modal">Confirmar</button>
                                            &nbsp;&nbsp;
                                            <button class="btn btn-secondary" type="button" data-bs-dismiss="modal">Cerrar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>              
                            <!-- modal - editar concepto --> 
                            <div class="modal fade text-start" id="modalEditarConceptos" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header border-0 bg-gray-100">
                                            <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Editar Concepto</h3>
                                            <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body"> 
                                            <!-- check - editar conceptos -->
                                            <div class="row">
                                                <div class="col-2">
                                                    <input class="form-check-input" type="radio" id="check_conceptos" name="check_conceptos" value="1" onclick="habilitarDatosConceptos(this.value)" checked="true">  
                                                    <label class="custom-control-label" for="customCheck1">General</label>
                                                </div>
                                                <div class="col-2">
                                                    <input class="form-check-input" type="radio" id="check_conceptos" name="check_conceptos" value="2" onclick="habilitarDatosConceptos(this.value)">  
                                                    <label class="custom-control-label" for="customCheck1">Impuestos</label>
                                                </div>
                                                <div class="col-2">
                                                    <input class="form-check-input" type="radio" id="check_conceptos" name="check_conceptos" value="3" onclick="habilitarDatosConceptos(this.value)">  
                                                    <label class="custom-control-label" for="customCheck1">Otros</label>
                                                </div>
                                                <div class="col-2">
                                                    <input class="form-check-input" type="radio" id="check_conceptos" name="check_conceptos" value="4" onclick="habilitarDatosConceptos(this.value)">  
                                                    <label class="custom-control-label" for="customCheck1">Partes</label>
                                                </div>
                                                <hr>
                                            </div>
                                            <!-- tab general -->
                                            <div id="tabGeneral" class="tab-pane">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Descripción:</label>
                                                            <textarea class="form-control" cols="20" id="up_desc" name="up_desc" rows="2"></textarea>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Cantidad:</label>
                                                            <input class="form-control"  id="up_cantidad" name="up_cantidad" type="number" min="1" autocomplete="off">
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Núm. identificación:</label>
                                                            <input class="form-control" id="up_noIdentificacion" name="up_noIdentificacion" type="text" value="" onkeypress="return soloNumeros(event)" autocomplete="off">
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Precio unitario:</label>
                                                            <input class="form-control" id="up_precioUnitario" name="up_precioUnitario" type="number" min="1" autocomplete="off">
                                                        </div>
                                                        <div class="input-group ">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Clave Prod/Serv SAT:</label>
                                                            <div class="input-group mb-3">
                                                                <input class="form-control" type="text" id="up_claveProdServ" name="up_claveProdServ" placeholder="ClaveSAT" autocomplete="off">
                                                                <button class="btn btn-primary col-md-1" id="button-addon1" type="button" data-bs-toggle="modal" data-bs-target="#modalconceptos"><i class="fa fa-search" ></i></button>
                                                            </div>
                                                        </div>
                                                        <div class="input-group ">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Clave Unidad SAT:</label>
                                                            <div class="input-group mb-3">
                                                                <input class="form-control" type="text" id="up_claveUnidad" name="up_claveUnidad" placeholder="UnidadSAT" autocomplete="off">
                                                                <button class="btn btn-primary col-md-1" id="button-addon1" type="button" data-bs-toggle="modal" data-bs-target="#modalclvUnidad"><i class="fa fa-search" ></i></button>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Descuento:</label>
                                                            <input class="form-control" data-val="true" data-val-number="The field Descuento must be a number." id="up_descuento" name="up_descuento" type="number" autocomplete="off" min="0">
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Unidad de medida:</label>
                                                            <input class="form-control" maxlength="70" id="up_unidadMedida" name="up_unidadMedida" type="number" min="0" autocomplete="off">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- tab impuestos -->
                                            <div id="tabImpuestos" class="tab-pane active" style="display:none;">
                                                <div class="ibox-content">
                                                    <div class="row">
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Tipo impuesto</label>:
                                                                <select class="form-control" id="up_tipoImpuesto" name="up_tipoImpuesto" onchange="consultarTipoImpuesto(this.value)">
                                                                    <option value="0" disabled selected>-- Seleccione tipo impuesto --</option>
                                                                    <%
                                                                        if (db.doDB(fac.tipoImpuesto())) {
                                                                            for (String[] row : db.getResultado()) {
                                                                                out.println("<option value=\"" + row[0] + "\" >" + row[1] + "</option>");
                                                                            }
                                                                        }
                                                                    %>
                                                                </select>
                                                                <div id="val_up_tipoImpuesto" style="color:#3b83bd;"></div>	
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Base</label>:
                                                                <input class="form-control" id="up_base" name="up_base" type="number" min="0" autocomplete="off">
                                                                <div id="val_up_base" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Impuesto</label>:
                                                                <select class="form-control" id="up_impuesto" name="up_impuesto" onchange="consultarTipoFactor(this.value)">
                                                                    <option value="0" disabled selected>-- Seleccione Impuesto --</option>
                                                                </select>
                                                                <div id="val_up_impuesto" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Tipo factor</label>:
                                                                <select class="form-control" id="up_tipoFactor" name="up_tipoFactor" onchange="consultarTipoTasa(this.value)">
                                                                    <option value="0" disabled selected>-- Seleccione Tipo Factor --</option>
                                                                </select>
                                                                <div id="val_up_tipoFactor" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2">
                                                            <div class="form-group" id="tab-tasa" style="display:none;">
                                                                <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Tasa</label>:
                                                                <select class="form-control" id="up_tasa" name="up_tasa">
                                                                    <option value="0" disabled selected>-- Seleccione una Tasa --</option>
                                                                </select>
                                                                <div id="val_up_tasa" style="color:#3b83bd;"></div>
                                                            </div>
                                                            <div class="form-group" id="tab-cuota" style="display:none;">
                                                                <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Cuota</label>:
                                                                <input class="form-control" id="up_Cuota" name="up_Cuota" type="number" placeholder="0.00">
                                                                <div id="val_up_Cuota" style="color:#3b83bd;"></div>
                                                            </div>    
                                                        </div>
                                                        <input type="hidden" id="up_importe" name="up_importe">  

                                                        <div class="col-md-1"></div>
                                                    </div>
                                                    <br>
                                                    <div class="row" style="text-align:right;">
                                                        <span class="input-group-btn">
                                                            <a class="btn btn-primary" onclick="AddImpuestoAdicional();"><i class="fa fa-plus-square"></i> Agregar Impuesto</a>
                                                        </span>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="dataTables_wrapper form-inline dt-bootstrap">
                                                            <table id="tParteConcepto" class="table table-striped table-bordered table-hover dataTable" role="grid">
                                                                <thead>
                                                                    <tr>
                                                                        <th>Traslado/Retención</th>
                                                                        <th>Base</th>
                                                                        <th>Impuesto</th>
                                                                        <th>Tipo factor</th>
                                                                        <th>Tasa</th>
                                                                        <th>Cuota</th>
                                                                        <th>Importe</th>
                                                                        <th></th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody id="AddTableImpuestos"></tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="form-group" style="text-align:right;">
                                                                <table>
                                                                    <tbody>
                                                                        <tr>
                                                                            <th>Impuestos trasladados</th>
                                                                            <td>&nbsp;</td>
                                                                            <td><input data-val="true" data-val-number="The field ImpuestosTrasladados must be a number." data-val-required="El campo ImpuestosTrasladados es obligatorio." disabled="disabled" id="ImpuestosTrasladados" name="ImpuestosTrasladados" style="border: 1; background-color: white;text-align:right;" type="text" value="0" autocomplete="off"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <th>Impuestos retenidos</th>
                                                                            <td>&nbsp;</td>
                                                                            <td><input data-val="true" data-val-number="The field ImpuestosRetenidos must be a number." data-val-required="El campo ImpuestosRetenidos es obligatorio." disabled="disabled" id="ImpuestosRetenidos" name="ImpuestosRetenidos" style="border: 1; background-color: white;text-align:right;" type="text" value="0" autocomplete="off"></td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- tab otros -->
                                            <div id="tabOtros" class="tab-pane" style="display:none;">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Cuenta Predial:</label>
                                                            <input class="form-control" maxlength="255" id="up_cuentaPredial" name="up_cuentaPredial" type="text" autocomplete="off">
                                                            <span class="field-validation-valid label label-danger" data-valmsg-for="EditarConcepto.CuentaPredial" data-valmsg-replace="true"></span>
                                                            <div id="val_up_cuentaPredial" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Número de Pedimento:</label>
                                                            <div class="input-group">
                                                                <input type="text" id="up_numeroPedimento" name="up_numeroPedimento" class="form-control" autocomplete="off">
                                                                <div id="val_up_numeroPedimento" style="color:#3b83bd;"></div>
                                                                <span class="input-group-btn">
                                                                    <a class="btn btn-primary" onclick="AddPedimentos()"><i class="fa fa-plus"></i> Agregar pedimento</a>
                                                                </span>
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div id="AddTablePedimentos"> </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- tab partes -->
                                            <div id="tabPartes" class="tab-pane" style="display:none;">
                                                <div class="ibox-content">
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <div class="form-group">
                                                                <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Descripción:</label>
                                                                <div class="input-group mb-3">
                                                                    <select class="form-control selectpicker col-md-5" id="upParte_desc" name="upParte_desc">
                                                                        <option value="0" disabled selected>-- Producto/servicio --</option>
                                                                        <%
                                                                            if (db.doDB(fac.consultarDescripcion(cve))) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\">" + row[1] + " - " + row[2] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                    <div id="val_upParte_desc" style="color:#3b83bd;"></div>
                                                                    <button class="btn btn-primary col-md-2" id="button-addon1" type="button"><i class="fa fa-edit" ></i></button>
                                                                </div>        
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Cantidad:</label>
                                                                <input class="form-control" data-val="true" id="upParte_Cantidad" name="upParte_Cantidad" type="number" min="1" autocomplete="off">
                                                                <div id="val_upParte_Cantidad" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Precio unitario:</label>
                                                                <input class="form-control" data-val="true" id="upParte_precioUnitario" name="upParte_precioUnitario" type="number" min="1" autocomplete="off">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Clave SAT:</label>
                                                            <div class="input-group">
                                                                <input class="form-control" id="upParte_ClaveProdServ" name="upParte_ClaveProdServ" maxlength="10" placeholder="ClaveSAT" style="display:inline-block;" type="text" value="" autocomplete="off">
                                                                <span class="input-group-btn">
                                                                    <a id="btnClaveProdServP" data-toggle="modal" class="btn btn-primary" href="#modalProdServ" onclick="$('#productoSAT').val('0'); $('#modalProdServ').attr('uso', 'parte');"><i class="fa fa-search"></i></a>
                                                                </span>
                                                                <span class="field-validation-valid label label-danger" data-valmsg-for="conceptoParte.ClaveProdServ" data-valmsg-replace="true"></span>
                                                            </div>
                                                            <div id="val_upParte_ClaveProdServ" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Unidad SAT:</label>
                                                            <div class="input-group">
                                                                <input class="form-control" id="upParte_unidad" maxlength="10" name="upParte_unidad" placeholder="UnidadSAT" style="display:inline-block;" type="text" value="" autocomplete="off">
                                                                <span class="input-group-btn">
                                                                    <a id="btnClaveUnidadSATP" data-toggle="modal" class="btn btn-primary" href="#modalServiciosSAT" onclick="$('#productoSAT').val('0'); $('#modalServiciosSAT').attr('uso', 'parte');"><i class="fa fa-search"></i></a>
                                                                </span>
                                                                <span class="field-validation-valid label label-danger" data-valmsg-for="conceptoParte.Unidad" data-valmsg-replace="true"></span>
                                                            </div>
                                                            <div id="val_upParte_unidad" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Núm. identificación:</label>
                                                                <input class="form-control" id="upParte_noIdentificacion" name="upParte_noIdentificacion"  maxlength="255" placeholder="0" type="text" value="" autocomplete="off">
                                                                <div id="val_upParte_noIdentificacion" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <br>
                                                    <div class="row" style="text-align:right;">
                                                        <span class="input-group-btn">
                                                            <a id="ModificarParte" class="btn btn-primary" onclick="AddPartes();"><i class="fa fa-plus-square"></i> Agregar Parte</a>
                                                        </span>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="dataTables_wrapper form-inline dt-bootstrap">
                                                            <table id="tParteConcepto" class="table table-striped table-bordered table-hover dataTable" role="grid">
                                                                <thead>
                                                                    <tr>
                                                                        <th>Descripción</th>
                                                                        <th>Cantidad</th>
                                                                        <th>Clave SAT</th>
                                                                        <th>Unidad SAT</th>
                                                                        <th>Precio unitario</th>
                                                                        <th>Importe</th>
                                                                        <th>Núm. identificación</th>
                                                                        <th></th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody id="AddTablePartes"></tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <input type="hidden" id="id.contador.concepto" name="id.contador.concepto">
                                        </div>
                                        <div class="modal-footer border-0 bg-gray-100">
                                            <button class="btn btn-default" type="button" data-bs-dismiss="modal" onclick="cleanEditConceptos();">Cerrar</button>
                                            <button class="btn btn-primary" type="button" data-bs-dismiss="modal">Guardar</button>
                                        </div>
                                    </div>
                                </div>
                            </div> 
                            <!-- modal - clave unidad --> 
                            <div class="modal fade text-start" id="modalclvUnidad" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header border-0 bg-gray-100">
                                            <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Catálogo SAT de Claves de Unidad</h3>
                                            <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">  
                                                <div class="col"> 
                                                    <div class="preload-wrapper">
                                                        <table class="table table-hover table-borderless align-middle mb-0" id="unidadDatatable">
                                                            <thead>
                                                                <tr>
                                                                    <th>c_ClaveUnidad - Descripción</th>
                                                                    <th> </th>
                                                                </tr>
                                                            </thead>
                                                            <tbody id="AddTableClave" name="AddTableClave">
                                                                <%
                                                                    if (db.doDB(fac.consultarUnidadMedidaSAT())) {
                                                                        for (String[] row : db.getResultado()) {
                                                                %>
                                                                <tr>
                                                                    <td><%=row[1]%> - <%=row[2]%></td>
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
                            <!-- modal - cfdi relacionados --> 
                            <div class="modal fade text-start" id="modalcfdirelacion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header border-0 bg-gray-100">
                                            <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;CFDI relacionados</h3>
                                            <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label class="form-label" style="font-weight: bold;">Tipo relación:</label>
                                                <select class="form-control" id="tiporelacion_id" name="tiporelacion_id">
                                                    <option value="0" disabled selected>-- Seleccione Tipo Relación --</option>
                                                    <%
                                                        if (db.doDB(fac.consultarTipoRelacionSAT())) {
                                                            for (String[] row : db.getResultado()) {
                                                                out.println("<option value=\"" + row[0] + "\">" + row[1] + "</option>");
                                                            }
                                                        }
                                                    %>
                                                </select>
                                                <div id="val_tiporelacion_id" style="color:#3b83bd;"></div>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <label class="form-label" style="font-weight: bold;">UUID:</label>
                                                        <div class="input-group mb-3">
                                                            <select class="form-control" id="uuid_id" name="uuid_id" onchange="relTotalCFDI(this.value)">
                                                                <option value="0" disabled selected>-- Seleccione una Factura --</option>
                                                                <%
                                                                    if (db.doDB(fac.consultarUuidSAT(cve))) {
                                                                        for (String[] row : db.getResultado()) {
                                                                            out.println("<option value=\"" + row[1] + "*" + row[4] + "\">" + row[1] + " / " + row[2] + "" + row[3] + " / $" + row[4] + "</option>");
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                            <button class="btn btn-primary col-md-2" id="button-addon1" type="button"><i class="fa fa-edit" ></i></button>
                                                            <div id="val_uuid_id" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label" style="font-weight: bold;">&nbsp;</label>
                                                        <div class="input-group mb-3">
                                                            <input class="form-control" type="text" id="total_cfdi" name="total_cfdi" placeholder="Total del CFDI" disabled="true" autocomplete="off">
                                                            <div id="val_total_cfdi" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <label class="form-label" style="font-weight: bold;">&nbsp;</label>
                                                        <div class="input-group mb-3">
                                                            <button class="btn btn-info" id="button-addon2" type="button" onclick="AddUUID()">Agregar &nbsp;<i class="fas fa-plus-square"></i></button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="dataTables_wrapper form-inline dt-bootstrap">
                                                    <table id="tParteConcepto" class="table table-striped table-bordered table-hover dataTable" role="grid">
                                                        <thead>
                                                            <tr>
                                                                <th>UUID</th>
                                                                <th>Monto</th>
                                                                <th> </th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="AddTableUUID"></tbody>
                                                    </table>
                                                </div>
                                            </div>                  
                                        </div>

                                        <div class="row" style="text-align:right;">
                                            <div id="total_rel_cfdi" name="total_rel_cfdi"></div>
                                        </div>                  
                                        <div class="modal-footer border-0 bg-gray-100">
                                            <button class="btn btn-secondary" type="button" data-bs-dismiss="modal">Cerrar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- modal - información comercial --> 
                            <div class="modal fade text-start" id="modalinformacioncomercial" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header border-0 bg-gray-100">
                                            <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Información comercial</h3>
                                            <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label class="form-label" style="font-weight: bold;">Número de orden:</label>
                                                <input class="form-control" type="text" id="num_orden" name="num_orden" placeholder="" autocomplete="off">
                                                <div id="val_num_orden" style="color:#3b83bd;"></div>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label" style="font-weight: bold;">Número de proveedor:</label>
                                                <input class="form-control" type="text" id="num_proveedor" name="num_proveedor" placeholder="" autocomplete="off">
                                                <div id="val_num_proveedor" style="color:#3b83bd;"></div>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label" style="font-weight: bold;">Observaciones:</label>
                                                <div class="form-floating mb-3">
                                                    <input class="form-control"  type="text" id="info_observaciones" name="info_observaciones" placeholder="" autocomplete="off">
                                                    <div id="val_info_observaciones" style="color:#3b83bd;"></div>
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
                            <!-- modal - carta porte -->
                            <div class="modal fade text-start" id="modalCartaDePorte" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-xl" role="document">
                                    <div class="modal-content">    
                                        <div class="modal-header border-0 bg-gray-100">
                                            <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Carta de Porte</h3>
                                            <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <!-- check - editar conceptos -->
                                            <div class="row">
                                                <div class="col-3">
                                                    <input class="form-check-input" type="radio" id="check_cartaporte" name="check_cartaporte" value="1" onclick="habilitarDatosCartaPorte(this.value)" checked="true">  
                                                    <label class="custom-control-label" for="customCheck1">Lugar de Embarque</label>
                                                </div>
                                                <div class="col-2">
                                                    <input class="form-check-input" type="radio" id="check_cartaporte" name="check_cartaporte" value="2" onclick="habilitarDatosCartaPorte(this.value)">  
                                                    <label class="custom-control-label" for="customCheck1">Destinatario</label>
                                                </div>
                                                <div class="col-3">
                                                    <input class="form-check-input" type="radio" id="check_cartaporte" name="check_cartaporte" value="3" onclick="habilitarDatosCartaPorte(this.value)">  
                                                    <label class="custom-control-label" for="customCheck1">Lugar de Entrega</label>
                                                </div>
                                                <div class="col-2">
                                                    <input class="form-check-input" type="radio" id="check_cartaporte" name="check_cartaporte" value="4" onclick="habilitarDatosCartaPorte(this.value)">  
                                                    <label class="custom-control-label" for="customCheck1">Mercancia</label>
                                                </div>
                                                <div class="col-2">
                                                    <input class="form-check-input" type="radio" id="check_cartaporte" name="check_cartaporte" value="5" onclick="habilitarDatosCartaPorte(this.value)">  
                                                    <label class="custom-control-label" for="customCheck1">Autotransporte</label>
                                                </div>
                                                <hr>
                                            </div>
                                            <!-- Lugar de Embarque -->
                                            <div id="tab-LugarDeEmbarque" class="tab-pane">
                                                <input type="hidden" id="tipoLugEmb" name="tipoLugEmb" value="">
                                                <div class="panel-body">
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="row">
                                                                <div class="col-4">
                                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">
                                                                        <input type="radio" class="iradio_square-green" name="check_lugarEmbarque" value="1" id="check_lugarEmbarque" onclick="deshabilitarLugarEmbarque(this.value)"> Seleccionar Lugar de Embarque:
                                                                    </label>
                                                                </div>
                                                                <div class="col-8">
                                                                    <select class="form-control selectpicker col-md-5" id="LugarDeEmbarque" name="LugarDeEmbarque" onchange="consultarTipoLugar(1)">
                                                                        <option value="0" disabled selected>-- Seleccione Lugar de Embarque --</option>
                                                                        <%
                                                                            if (db.doDB(fac.ubicacion1(cve))) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\">" + row[1] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                    <div id="val_LugarDeEmbarque" style="color:#3b83bd;"></div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-lg-10">
                                                                    <div class="some-class">
                                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">
                                                                            <input type="radio" class="iradio_square-green" name="check_lugarEmbarque" value="2" id="check_lugarEmbarque" onclick="deshabilitarLugarEmbarque(this.value)"> Crear Nuevo Lugar de Embarque
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-md-8">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Nombre:</label>
                                                            <input class="form-control" id="lugEmb_nombre" name="lugEmb_nombre" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_lugEmb_nombre" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">RFC:</label>
                                                            <input class="form-control" id="lugEmb_rfc" name="lugEmb_rfc" type="text" onkeyup="this.value = this.value.toUpperCase()" minlength="12" maxlength="13" autocomplete="off">
                                                            <div id="val_lugEmb_rfc" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Calle:</label>
                                                            <input class="form-control" id="lugEmb_calle"  name="lugEmb_calle" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_lugEmb_calle" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">No Interior:</label>
                                                            <input autocomplete="off" class="form-control" id="lugEmb_noInterior"  name="lugEmb_noInterior" type="number" min="0" autocomplete="off">
                                                            <div id="val_lugEmb_noInterior" style="color:#3b83bd;"></div>
                                                        </div>

                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">No Exterior:</label>
                                                            <input autocomplete="off" class="form-control" id="lugEmb_noExterior"  name="lugEmb_noExterior" type="number" min="0" autocomplete="off">
                                                            <div id="val_lugEmb_noExterior" style="color:#3b83bd;"></div>
                                                        </div>

                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Código postal:</label>
                                                            <select class="form-control" data-live-search="true" id="lugEmb_codigoPostal" name="lugEmb_codigoPostal" autocomplete="off">
                                                                <option value="0" disabled selected>-- Seleccione Código Postal --</option>
                                                                <option value="13030">13030</option>
                                                            </select>
                                                            <div id="val_lugEmb_codigoPostal" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Colonia:</label>
                                                            <select class="form-control" data-live-search="true" id="lugEmb_colonia" name="lugEmb_colonia" autocomplete="off">
                                                                <option value="0" disabled selected>-- Seleccione Colonia --</option>
                                                                <option value="1925">13030 - San Juan</option>
                                                            </select>
                                                            <div id="val_lugEmb_colonia" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Estado:</label>
                                                                <select class="form-control" data-live-search="true" id="lugEmb_estado" name="lugEmb_estado" autocomplete="off">
                                                                    <option value="0" disabled selected>-- Seleccione Estado --</option>
                                                                    <option value="DIF">DIF - Ciudad de México</option>
                                                                </select>
                                                                <div id="val_lugEmb_estado" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Municipio:</label>
                                                                <select class="form-control" data-live-search="true" id="lugEmb_municipio" name="lugEmb_municipio" autocomplete="off">
                                                                    <option value="0" disabled selected>-- Seleccione Municipio --</option>
                                                                    <option value="011">DIF - Tláhuac</option>
                                                                </select>
                                                                <div id="val_lugEmb_municipio" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                    </div>   
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Localidad:</label>
                                                                <select class="form-control" data-live-search="true" id="lugEmb_localidad" name="lugEmb_localidad" autocomplete="off">
                                                                    <option value="0" disabled selected>-- Seleccione Localidad --</option>
                                                                    <option value="13">DIF - Ciudad de México</option>
                                                                </select>
                                                                <div id="val_lugEmb_localidad" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-8">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Referencia:</label>
                                                            <input class="form-control" id="lugEmb_referencia" name="lugEmb_referencia" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_lugEmb_referencia" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-md-7"></div>
                                                        <div class="col-md-2">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Tipo Ubicación:</label>
                                                            <input class="form-control" id="lugEmb_idUbicacion" name="lugEmb_idUbicacion" maxlength="8" type="text" value="" onclick="ubicacionLugar()" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_lugEmb_idUbicacion" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Fecha Hora Salida Llegada:</label>
                                                            <input class="form-control" id="lugEmb_fechaHoraSalidaLlegada" name="lugEmb_fechaHoraSalidaLlegada" type="date" value="" autocomplete="off">
                                                            <div id="val_lugEmb_fechaHoraSalidaLlegada" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <br>
                                                </div>
                                            </div>
                                            <!-- Lugar de Destinatario -->
                                            <div id="tab-Destinatario" class="tab-pane" style="display:none;">
                                                <input type="hidden" id="tipoDest" name="tipoDest" value="">
                                                <div class="panel-body">
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="row">
                                                                <div class="col-4">
                                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">
                                                                        <input type="radio" class="iradio_square-green" name="check_lugarDestinatario" value="1" id="check_lugarDestinatario" onclick="deshabilitarLugarDestinatario(this.value)"> Seleccionar Lugar de Destinatario:
                                                                    </label>
                                                                </div>
                                                                <div class="col-8">
                                                                    <select class="form-control selectpicker col-md-5" id="LugarDestinatario" name="LugarDestinatario" onchange="consultarTipoLugar(2)">
                                                                        <option value="0" disabled selected>-- Seleccione Lugar de Destinatario --</option>
                                                                        <%
                                                                            if (db.doDB(fac.ubicacion2(cve))) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\">" + row[1] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                    <div id="val_LugarDestinatario" style="color:#3b83bd;"></div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-lg-10">
                                                                    <div class="some-class">
                                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">
                                                                            <input type="radio" class="iradio_square-green" name="check_lugarDestinatario" value="2" id="check_lugarDestinatario" onclick="deshabilitarLugarDestinatario(this.value)"> Crear Nuevo Lugar de Destinatario
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-md-8">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Nombre:</label>
                                                            <input class="form-control" id="dest_nombre" name="dest_nombre" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_dest_nombre" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">RFC: </label>
                                                            <input class="form-control" id="dest_rfc" name="dest_rfc" type="text" onkeyup="this.value = this.value.toUpperCase()" minlength="12" maxlength="13" autocomplete="off">
                                                            <div id="val_dest_rfc" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Calle:</label>
                                                            <input class="form-control" id="dest_calle" name="dest_calle" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_dest_calle" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">No Interior:</label>
                                                            <input autocomplete="off" class="form-control" id="dest_noInterior"  name="dest_noInterior" type="number" min="0" autocomplete="off">
                                                            <div id="val_dest_noInterior" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">No Exterior:</label>
                                                            <input autocomplete="off" class="form-control" id="dest_noExterior"  name="dest_noExterior" type="number" min="0" autocomplete="off">
                                                            <div id="val_dest_noExterior" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Código postal:</label>
                                                            <input autocomplete="off" class="form-control" id="dest_codigoPostal"   name="dest_codigoPostal" type="number" min="0" autocomplete="off">
                                                            <div id="val_dest_codigoPostal" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Colonia:</label>
                                                            <select class="form-control selectpicker" data-live-search="true" id="dest_colonia" name="dest_colonia"  autocomplete="off">
                                                                <option value="0" disabled selected>-- Seleccione Colonia --</option>
                                                                <option value="2834">16530 - Barrio Las Flores</option>
                                                            </select>
                                                            <div id="val_dest_colonia" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Estado:</label>
                                                                <select class="form-control selectpicker" data-live-search="true" id="dest_estado" name="dest_estado" autocomplete="off">
                                                                    <option value="0" disabled selected>-- Seleccione Estado --</option>
                                                                    <option value="DIF">DIF - Ciudad de México</option>
                                                                </select>
                                                                <div id="val_dest_estado" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Municipio:</label>
                                                                <select class="form-control selectpicker" data-live-search="true" id="dest_municipio" name="dest_municipio" autocomplete="off">
                                                                    <option value="0" disabled selected>-- Seleccione Municipio --</option>
                                                                    <option value="013">DIF - Xochimilco</option>
                                                                </select>
                                                                <div id="val_dest_municipio" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Localidad:</label>
                                                                <select class="form-control selectpicker" data-live-search="true" id="dest_localidad" name="dest_localidad" autocomplete="off">
                                                                    <option value="0" disabled selected>-- Seleccione Localidad --</option>
                                                                    <option value="16">DIF -  Ciudad de México </option>
                                                                </select>
                                                                <div id="val_est_localidad" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-8">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Referencia:</label>
                                                            <input class="form-control" id="dest_referencia" name="dest_referencia" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_dest_referencia" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-md-5"></div>
                                                        <div class="col-md-2">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Tipo Ubicación:</label>
                                                            <input class="form-control" id="dest_idUbicacion" name="dest_idUbicacion" maxlength="8" type="text" onclick="ubicacionDestino()" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_lugEmb_idUbicacion" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Fecha Hora Salida Llegada:</label>
                                                            <input class="form-control" id="dest_fechaHoraSalidaLlegada" name="dest_fechaHoraSalidaLlegada" type="date" value="" autocomplete="off">
                                                            <div id="val_dest_fechaHoraSalidaLlegada" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-2">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Distancia Recorrida:</label>
                                                            <input class="form-control" id="dest_distancia" name="dest_distancia" type="number" min="1" autocomplete="off">
                                                            <div id="val_dest_distancia" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <br>
                                                </div>
                                            </div>
                                            <!-- Lugar de Entrega -->
                                            <div id="tab-LugarDeEntrega" class="tab-pane" style="display:none;">
                                                <input type="hidden" id="tipoLugEnt" name="tipoLugEnt" value="">
                                                <div class="panel-body">
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="row">
                                                                <div class="col-4">
                                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">
                                                                        <input type="radio" class="iradio_square-green" name="check_lugarEntrega" value="1" id="check_lugarEntrega" onclick="deshabilitarLugarEntrega(this.value)">  Seleccionar Lugar de Entrega:
                                                                    </label>
                                                                </div>
                                                                <div class="col-8">
                                                                    <select class="form-control selectpicker col-md-5" id="LugarEntrega" name="LugarEntrega" onchange="consultarTipoLugar(3)">
                                                                        <option value="0" disabled selected>-- Seleccione Lugar de Entrega --</option>
                                                                        <%
                                                                            if (db.doDB(fac.ubicacion3(cve))) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\">" + row[1] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                    <div id="val_LugarEntrega" style="color:#3b83bd;"></div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-lg-10">
                                                                    <div class="some-class">
                                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">
                                                                            <input type="radio" class="iradio_square-green" name="check_lugarEntrega" value="2" id="check_lugarEntrega" onclick="deshabilitarLugarEntrega(this.value)"> Crear Nuevo Lugar de Entrega
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-md-8">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Nombre:</label>
                                                            <input class="form-control" id="lugEnt_nombre" name="lugEnt_nombre" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_lugEnt_nombre" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">RFC:</label>
                                                            <input class="form-control" id="lugEnt_rfc" name="lugEnt_rfc" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_lugEnt_rfc" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Calle:</label>
                                                            <input class="form-control" id="lugEnt_calle" name="lugEnt_calle" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_lugEnt_calle" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">No Interior:</label>
                                                            <input autocomplete="off" class="form-control" id="lugEnt_noInterior"  name="lugEnt_noInterior" placeholder="" type="number" min="0" autocomplete="off">
                                                            <div id="val_lugEnt_noInterior" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">No Exterior:</label>
                                                            <input autocomplete="off" class="form-control" id="lugEnt_noExterior"  name="lugEnt_noExterior" placeholder="" type="number" min="0" autocomplete="off">
                                                            <div id="val_lugEnt_noExterior" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Código postal:</label>
                                                            <input autocomplete="off" class="form-control" id="lugEnt_codigoPostal"   name="lugEnt_codigoPostal" placeholder="" type="number" min="0" autocomplete="off">
                                                            <div id="val_lugEnt_codigoPostal" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Colonia:</label>
                                                            <select class="form-control selectpicker" data-live-search="true" id="lugEnt_colonia" name="lugEnt_colonia" autocomplete="off">
                                                                <option value="0" disabled selected>-- Seleccione Colonia --</option>
                                                                <option value="3100">54740 - Cumbria</option>
                                                            </select>
                                                            <div id="val_lugEnt_colonia" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Estado:</label>
                                                                <select class="form-control selectpicker" data-live-search="true" id="lugEnt_estado" name="lugEnt_estado" autocomplete="off">
                                                                    <option value="0" disabled selected>-- Seleccione Estado --</option>
                                                                    <option value="MX">MX - México</option>
                                                                </select>
                                                                <div id="val_lugEnt_estado" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Municipio:</label>
                                                                <select class="form-control selectpicker" data-live-search="true" id="lugEnt_municipio" name="lugEnt_municipio" autocomplete="off">
                                                                    <option value="0" disabled selected>-- Seleccione Municipio --</option>
                                                                    <option value="121">MX - Cuautitlán Izcalli</option>
                                                                </select>
                                                                <div id="val_lugEnt_municipio" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                    </div> 
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Localidad:</label>
                                                            <select class="form-control selectpicker" data-live-search="true" id="lugEnt_localidad" name="lugEnt_localidad" autocomplete="off">
                                                                <option value="0" disabled selected>-- Seleccione Localidad --</option>
                                                                <option value="."> ----- </option>
                                                                <option value="04">MX - Cuautitlán Izcalli</option>
                                                            </select>
                                                            <div id="val_lugEnt_localidad" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Pais:</label>
                                                            <select class="form-control" id="lugEnt_pais" name="lugEnt_pais" value="" autocomplete="off">
                                                                <option value="MEX">México (Estados Unidos Mexicanos)</option>
                                                            </select>
                                                            <div id="val_lugEnt_pais" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Transporte Internacional:</label>
                                                            <select class="form-control" id="lugEnt_tipoTransporte" name="lugEnt_tipoTransporte" value="" autocomplete="off">
                                                                <option value="No">No</option>
                                                                <option value="Si">Si</option>
                                                            </select>
                                                            <div id="val_lugEnt_tipoTransporte" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Referencia:</label>
                                                            <input class="form-control" id="lugEnt_referencia" name="lugEnt_referencia" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_lugEnt_referencia" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Contacto:</label>
                                                            <input class="form-control" id="lugEnt_contacto" name="lugEnt_contacto" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_lugEnt_contacto" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-md-5"></div>
                                                        <div class="col-md-2">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Tipo Ubicación:</label>
                                                            <input class="form-control" id="lugEnt_idUbicacion" name="lugEnt_idUbicacion" type="text" maxlength="8" value="" onclick="ubicacionEntrega()" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_lugEmb_idUbicacion" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Fecha Hora Salida Llegada:</label>
                                                            <input class="form-control" id="lugEnt_fechaHoraSalidaLlegada" name="lugEnt_fechaHoraSalidaLlegada" type="date" value="" autocomplete="off">
                                                            <div id="val_lugEnt_fechaHoraSalidaLlegada" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-2">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Distancia Recorrida:</label>
                                                            <input class="form-control" id="lugEnt_distancia" name="lugEnt_distancia" type="number"  min="1" autocomplete="off">
                                                            <div id="val_lugEnt_distancia" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <br>
                                                </div>
                                            </div>
                                            <!-- Mercancías -->
                                            <div id="tab-Mercancia" class="tab-pane" style="display:none;">
                                                <div class="panel-body">
                                                    <label><strong>Datos Generales:</strong></label>
                                                    <hr style="width:20%;text-align:left;margin-left:0">
                                                    <div class="row">
                                                        <div class="col-md-7">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Unidad Peso:</label>
                                                            <select class="form-control selectpicker col-md-4" id="unidad_peso_cp" name="unidad_peso_cp" onchange="habilitarConceptoMercancia()" autocomplete="off">
                                                                <option value="0" disabled selected>-- Seleccione unidad peso --</option>
                                                                <%
                                                                    if (db.doDB(fac.tipoUnidad())) {
                                                                        for (String[] row : db.getResultado()) {
                                                                            out.println("<option value=\"" + row[0] + "\">" + row[0] + " - " + row[1] + "</option>");
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                            <div id="val_unidad_peso_cp" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Material o residuo peligroso:</label>
                                                            <select class="form-control" id="mercancia_es_peligroso_cp" name="mercancia_es_peligroso_cp" autocomplete="off">
                                                                <option value="No">No</option>
                                                                <option value="Si">Si</option>
                                                            </select>
                                                            <div id="val_mercancia_es_peligroso_cp" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <input id="numpedimento_cp" name="numpedimento_cp" type="hidden" value="012245787">
                                                        <div class="col-md-2">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Moneda:</label>
                                                            <select class="form-control selectpicker col-md-5" id="moneda_cp" name="moneda_cp" autocomplete="off">
                                                                <option value="0" disabled>-- Seleccione --</option>
                                                                <option value="EUR">EUR - Euro</option>
                                                                <option value="MXN" selected>MXN - Peso Mexicano</option>
                                                                <option value="USD">USD - Dolar americano</option>
                                                            </select>
                                                            <div id="val_moneda_cp" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <br><br>
                                                    <label><strong>Datos Unitarios:</strong></label>
                                                    <hr style="width:20%;text-align:left;margin-left:0">
                                                    <div class="row">
                                                        <div class="col-md-2" id="flotante_concepto1">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Descripción:</label>
                                                            <div class="input-group mb-4">
                                                                <select class="form-control selectpicker col-md-5" id="mercancia_descripcion" name="mercancia_descripcion" onchange="detailsConceptoCartaPorte(this.value)" disabled="true">
                                                                    <option disabled selected>-- Mercancia --</option>
                                                                    <%
                                                                        if (db.doDB(fac.consultarDescripcionCartaPorte(cve))) {
                                                                            for (String[] row : db.getResultado()) {
                                                                                out.println("<option value=\"" + row[0] + "*" + row[2] + "\">" + row[1] + " - " + row[2] + "</option>");
                                                                            }
                                                                        }
                                                                    %>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-1" id="flotante_concepto2">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Cantidad:</label>
                                                            <div class="input-group mb-3">
                                                                <input class="form-control" type="number" id="cantidad_cp" name="cantidad_cp" disabled="true" min="1" autocomplete="off">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2" id="flotante_concepto3">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Clave SAT:</label>
                                                            <div class="input-group mb-3">
                                                                <input class="form-control" type="text" id="rel_clvsat_id_cp" name="rel_clvsat_id_cp" placeholder="ClaveSAT" disabled="true" autocomplete="off">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2" id="flotante_concepto4">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Unidad SAT:</label>
                                                            <div class="input-group mb-3">
                                                                <input class="form-control" type="text" id="unidad_sat_id_cp" name="unidad_sat_id_cp" placeholder="UnidadSAT" disabled="true" autocomplete="off">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2" id="flotante_concepto7">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">No Pedimento:</label>
                                                            <div class="input-group mb-3">
                                                                <input class="form-control" type="text" id="no_pedimento_cp" name="no_pedimento_cp" onkeyup="this.value = this.value.toUpperCase()" disabled="true" autocomplete="off">
                                                            </div>
                                                        </div>        
                                                        <div class="col-md-2" id="flotante_concepto5">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Precio:</label>
                                                            <div class="input-group mb-3">
                                                                <input class="form-control" type="number" id="precio_unitario_cp" name="precio_unitario_cp" min="1" readonly>
                                                                <div id="val_precio_unitario_cp" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-1" id="flotante_concepto6">
                                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;"> </label>
                                                            <div class="input-group mb-3">
                                                                <a class="btn btn-primary text-nowrap" id="btnConceptocp" name="btnConceptocp" role="button" style="float: left;" onclick="AddConceptosCartaPorte()" disabled="true"><i class="fa fa-plus me-2"> </i>&nbsp;</a>                                                              
                                                            </div>                                                          
                                                        </div>        
                                                    </div>
                                                    <br>
                                                    <table class="table" id="mytable">
                                                        <thead>
                                                            <tr>
                                                                <th>Descripción</th>
                                                                <th>Cantidad</th>
                                                                <th>Precio Unitario</th>
                                                                <th>Peso Unitario</th>
                                                                <th>Importe</th>
                                                                <th>Opciones</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="AddTableConceptosCartaPorte"></tbody>
                                                    </table>
                                                    <br>
                                                </div>
                                            </div>  
                                            <!-- Autotransporte -->
                                            <div id="tab-Autotransporte" class="tab-pane" style="display:none;">
                                                <div class="panel-body">
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Perm SCT:</label>
                                                            <select class="form-control selectpicker col-md-5" id="perm_sct" name="perm_sct" autocomplete="off">
                                                                <option value="0" disabled selected>-- Seleccione Permiso SCT --</option>
                                                                <%
                                                                    if (db.doDB(fac.tipoPermiso())) {
                                                                        for (String[] row : db.getResultado()) {
                                                                            out.println("<option value=\"" + row[0] + "\">" + row[0] + " - " + row[1] + "</option>");
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                            <div id="val_perm_sct" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">No. Permiso SCT:</label>
                                                            <input class="form-control" id="no_permismo_sct" name="no_permismo_sct" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_no_permismo_sct" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Configuración Vehicular:</label>
                                                            <select class="form-control selectpicker col-md-5" id="config_vehicular" name="config_vehicular">
                                                                <option value="0" disabled selected>-- Seleccione Configuración Vehicular --</option>
                                                                <%
                                                                    if (db.doDB(fac.configuracionVehicular())) {
                                                                        for (String[] row : db.getResultado()) {
                                                                            out.println("<option value=\"" + row[0] + "\">" + row[0] + " - " + row[1] + "</option>");
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                            <div id="val_config_vehicular" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Placa VM:</label>
                                                            <input class="form-control" id="placa_vm"  name="placa_vm" placeholder="" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_placa_vm" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Año Modelo VM:</label>
                                                            <input type="text" maxlength="4" class="form-control" id="anio_modelovm" name="anio_modelovm" onkeypress="return soloNumeros(event)">
                                                            <div id="val_anio_modelovm" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-md-3">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Placa Remolque:</label>
                                                            <input class="form-control" id="placa_remolque" name="placa_remolque" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_placa_remolque" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Tipo Remolque:</label>
                                                            <select class="form-control selectpicker col-md-5" id="tipo_remolque" name="tipo_remolque" autocomplete="off">
                                                                <option value="0" disabled selected>-- Seleccione Tipo Remolque --</option>
                                                                <%
                                                                    if (db.doDB(fac.tipoRemolque())) {
                                                                        for (String[] row : db.getResultado()) {
                                                                            out.println("<option value=\"" + row[0] + "\">" + row[0] + " - " + row[1] + "</option>");
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                            <div id="val_tipo_remolque" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Aseguradora Responsabilidad Civil:</label>
                                                            <input class="form-control" id="aseguradora_civil" name="aseguradora_civil" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_aseguradora_civil" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Poliza Responsabilidad Civil:</label>
                                                            <input class="form-control" id="poliza_civil" name="poliza_civil" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_poliza_civil" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <div class="col-md-3">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Tipo Figura:</label>
                                                            <select class="form-control selectpicker col-md-5" id="tipo_figura" name="tipo_figura" autocomplete="off" onchange="hbltDomiciliOperador(this.value)">
                                                                <option value="0" disabled selected>-- Seleccione Tipo Figura --</option>
                                                                <%
                                                                    if (db.doDB(fac.tipoFigura())) {
                                                                        for (String[] row : db.getResultado()) {
                                                                            out.println("<option value=\"" + row[0] + "\">" + row[0] + " - " + row[1] + "</option>");
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                            <div id="val_tipo_figura" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">RFC Figura:</label>
                                                            <input class="form-control" id="rfc_figura" name="rfc_figura" type="text" onkeyup="this.value = this.value.toUpperCase()" minlength="12" maxlength="13" autocomplete="off">
                                                            <div id="val_rfc_figura" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Núm.Licencia:</label>
                                                            <input class="form-control" id="num_licencia" name="num_licencia" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_num_licencia" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Nombre Figura:</label>
                                                            <input class="form-control" id="nombre_figura" name="nombre_figura" placeholder="" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_nombre_figura" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>

                                                    <!-- Nuevos parametros - Autotransporte -->
                                                    <hr>
                                                    <div class="row" id="autChofer1" style="display:none;">
                                                        <div class="col-md-12">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Calle:</label>
                                                            <input class="form-control" id="autChofer_calle"  name="autChofer_calle" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_lugEmb_calle" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row" id="autChofer2" style="display:none;">
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">No Interior:</label>
                                                            <input class="form-control" id="autChofer_noInterior"  name="autChofer_noInterior" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_lugEmb_noInterior" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4" id="autChofer3" style="display:none;">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">No Exterior:</label>
                                                            <input class="form-control" id="autChofer_noExterior"  name="autChofer_noExterior" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_lugEmb_noExterior" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4" id="autChofer4" style="display:none;">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Código postal:</label>
                                                            <input class="form-control" id="autChofer_codigoPostal" name="autChofer_codigoPostal" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_lugEmb_codigoPostal" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row" id="autChofer5" style="display:none;">
                                                        <div class="col-md-4">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Colonia:</label>
                                                            <input class="form-control" id="autChofer_colonia" name="autChofer_colonia" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                            <div id="val_lugEmb_colonia" style="color:#3b83bd;"></div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Estado:</label>
                                                                <input class="form-control" id="autChofer_estado" name="autChofer_estado" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                                <div id="val_lugEmb_estado" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Municipio:</label>
                                                                <input class="form-control" id="autChofer_municipio" name="autChofer_municipio" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                                <div id="val_lugEmb_municipio" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                    </div>   
                                                    <div class="row"  id="autChofer6" style="display:none;">
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Localidad:</label>
                                                                <input class="form-control" id="autChofer_localidad" name="autChofer_localidad" type="text" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                                <div id="val_lugEmb_localidad" style="color:#3b83bd;"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-8">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Referencia:</label>
                                                            <input class="form-control" id="autChofer_referencia" name="autChofer_referencia" type="text" autocomplete="off" autocomplete="off">
                                                            <div id="val_lugEmb_referencia" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>        

                                                    <br>
                                                    <div class="row">
                                                        <center>
                                                            <a class="btn btn-secondary text-nowrap" role="button" onclick="visorPdfCartaPorte(1)"><i class="fa fa-file"> </i>&nbsp;Vista Previa</a>
                                                        </center>
                                                    </div>
                                                    <br>
                                                </div>
                                            </div>                    
                                            <div class="modal-footer">
                                                <button class="btn btn-default" type="button" data-bs-dismiss="modal">Cerrar</button>
                                                <button class="btn btn-secondary" type="button" onclick="AddCartaPorte()">Agregar</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
                                                <iframe class="responsive-iframe" src="<%=request.getContextPath()%>/MostrarPDF_FACT" width="100%" height="500px"></iframe>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- modal - Vista Previa Carta Porte --> 
                            <div class="modal fade text-start" id="modalVistaPreviaCartaPorte" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header border-0 bg-gray-100">
                                            <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Vista previa complemento:</h3>
                                            <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="container"> 
                                                <iframe class="responsive-iframe" name="pdf2" id="pdf2" width="100%" height="500px"></iframe>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- contadores tablas -->
                            <input type="hidden" id="numImpuestos" name="numImpuestos">
                            <input type="hidden" id="numPedimentos" name="numPedimentos">
                            <input type="hidden" id="numPartes" name="numPartes">
                            <input type="hidden" id="numUUID" name="numUUID">
                            <input type="hidden" id="numCartaPorte" name="numCartaPorte">
                            <input type="hidden" id="numConceptosCartaPorte" name="numConceptosCartaPorte">
                            <input type="hidden" id="typFactor" name="typFactor">

                            <!-- Totales Gral -->
                            <input type="hidden" id="cant_subtotal_gral" name="cant_subtotal_gral">   
                            <input type="hidden" id="cant_descuento_gral" name="cant_descuento_gral">  
                            <input type="hidden" id="cant_traslados_gral" name="cant_traslados_gral">  
                            <input type="hidden" id="cant_retenciones_gral" name="cant_retenciones_gral">  
                            <input type="hidden" id="cant_total_gral" name="cant_total_gral"> 
                            <input type="hidden" id="cant_impuesto_gral" name="cant_impuesto_gral">  
                            <input type="hidden" id="cant_factor_gral" name="cant_factor_gral">  
                            <input type="hidden" id="cant_tasa_gral" name="cant_tasa_gral">  
                            <input type="hidden" id="cant_cuota_gral" name="cant_cuota_gral">  
                            <input type="hidden" id="tipo_impueto_gral" name="tipo_impueto_gral">

                            <!-- input's hidden -->
                            <input type="hidden" id="estatusCartaPorte" name="estatusCartaPorte">
                            <input type="hidden" id="cbdiv_id" name="cbdiv_id" value="<%=cve%>">
                            <input type="hidden" id="contImpuestoConcepto" name="contImpuestoConcepto" value="<%=contImpuestoConcepto%>">
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
                            
        <!-- Conexión estatus red -->
        <script src="../lib/validations/gral_3.3/header/connectionStatus.js" type="text/javascript"></script>
        <!-- Configuración facturación -->
        <script src="../lib/validations/cfdi_3.3/ConfiguracionFacturacion.js" type="text/javascript"></script>
        <!-- Catálogo claves unidad medida SAT -->
        <script src="../lib/validations/cfdi_3.3/CatalogoClavesUnidadSAT.js" type="text/javascript"></script>
        <!-- Catálogo productos y servicios SAT -->
        <script src="../lib/validations/cfdi_3.3/CatalogoProductosServiciosSAT.js" type="text/javascript"></script>
        <!-- Configuración cliente -->
        <script src="../lib/validations/cfdi_3.3/ConfiguracionesCliente.js" type="text/javascript"></script>
        <!-- Generar concepto -->
        <script src="../lib/validations/cfdi_3.3/GenerarConcepto.js" type="text/javascript"></script>
        <!-- Añadir información adicional concepto -->
        <script src="../lib/validations/cfdi_3.3/GenerarConceptoInfoAdicional.js" type="text/javascript"></script>
        <!-- Generar impuesto -->
        <script src="../lib/validations/cfdi_3.3/GenerarImpuesto.js" type="text/javascript"></script>
        
        <!-- Generar información comercial -->
        <!--<script src="../lib/validations/cfdi_3.3/GenerarInfoComercial.js" type="text/javascript"></script>-->
        <!-- Generar parte -->
        <!--<script src="../lib/validations/cfdi_3.3/GenerarParte.js" type="text/javascript"></script>-->
        <!-- Generar pedimento -->
        <!--<script src="../lib/validations/cfdi_3.3/GenerarPedimento.js" type="text/javascript"></script>-->
        <!-- Generar relación cfdi -->
        <!--<script src="../lib/validations/cfdi_3.3/GenerarRelacionCFDI.js" type="text/javascript"></script>-->
        
        <!-- Generar visor documento fiscal -->
        <script src="../lib/validations/cfdi_3.3/VisorDocumentoFiscal.js" type="text/javascript"></script>
        <!-- Generar facturación -->
        <script src="../lib/validations/cfdi_3.3/RegistrarFacturacion.js" type="text/javascript"></script>
        <!-- Generar timbrado -->
        <script src="../lib/validations/cfdi_3.3/GenerarTimbrado.js" type="text/javascript"></script>
        <!-- Generación de documento fiscal y envío por email -->
        <script src="../lib/validations/cfdi_3.3/GeneracionEmisionEmail.js" type="text/javascript"></script>
        
        <!-- JavaScript files-->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
        <!-- Data Tables-->
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"></script>
        <!-- CMS Category JS-->
        <!--<script src="../lib/js/datatable-conceptos.js" type="text/javascript"></script>-->
        <script src="../lib/js/datatable-clave-unidad.js" type="text/javascript"></script>
        <!--<script src="../lib/js/datatable-relacion-uuid.js" type="text/javascript"></script>-->
        <!-- Main Theme JS File-->
        <script src="../lib/js/theme.js"></script>
        <!-- Prism for syntax highlighting-->
        <script src="../lib/vendor/prismjs/prism.js"></script>
        <script src="../lib/vendor/prismjs/plugins/normalize-whitespace/prism-normalize-whitespace.min.js"></script>
        <script src="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.min.js"></script>
        <script src="../lib/vendor/prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard.min.js"></script>
        <!-- sweetalert -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
        <!-- choise -->
        <script src="../lib/choise/scripts/actions.js" type="text/javascript"></script>
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