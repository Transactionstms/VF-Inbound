<%-- 
    Document   : customForm
    Created on : 27/02/2023, 11:55:23 AM
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
        <title>Personalizar Evento</title>
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
        <link href="../lib/validationsInbound/customs/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- Connection Status Red -->
        <link href="../lib/inbound/conexion/connectionStatus.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));

                ConsultasQuery fac = new ConsultasQuery();
                String fechas = "";
                String sql=" SELECT  DISTINCT"
                         + " TIE.ID_EVENTO,"
                         + " BP.RESPONSABLE,"
                         + " GTN.FINAL_DESTINATION,"
                         + " GTN.BRAND_DIVISION,"
                         + " 'DIVISION',"
                         + " GTN.SHIPMENT_ID,"
                         + " GTN.CONTAINER1,"
                         + " GTN.BL_AWB_PRO,"
                         + " GTN.LOAD_TYPE,"
                         + " (select sum(  tt.QUANTITY ) from TRA_INC_GTN_TEST tt where tt.PLANTILLA_ID =GTN.PLANTILLA_ID   )  as suma   ,"
                         + " GTN.POD,"
                         + " to_char(GTN.EST_DEPARTURE_POL,'MM/DD/YYYY'),"
                         + " to_char(GTN.ETA_PORT_DISCHARGE,'MM/DD/YYYY')   AS ETA_REAL_PORT ,"
                         + " (SELECT  max(RECOMMENDED_LT2)  FROM tra_inb_costofleteytd"
                         + " where"
                         + " trim(UPPER(SUBSTR(BRAND_DIVISION,0,8))) in trim(UPPER(SUBSTR(GTN.BRAND_DIVISION,0,8))) and"
                         + " trim(UPPER(SUBSTR(POD,0,6)))            in trim(UPPER(SUBSTR(GTN.POD,0,6)))  and "
                         + " trim(UPPER(SUBSTR(POL,0,6)))            in trim(UPPER(SUBSTR(GTN.POL,0,6))) "
                         + " )as EST_ETA_DC,"
                         + " 'Inbound notification',"
                         + " GTN.POL,"
                         + " 'A.A',"
                         + " GTN.PLANTILLA_ID,"
                         + " to_char(GTN.FECHA_CAPTURA,'MM/DD/YYYY')"
                         + " from TRA_INB_EVENTO    TIE"
                         + " inner JOIN TRA_DESTINO_RESPONSABLE     BP ON BP.USER_NID=TIE.USER_NID   "
                         + " inner JOIN TRA_INC_GTN_TEST           GTN ON GTN.PLANTILLA_ID=TIE.PLANTILLA_ID"
                         + " order by 1"; 

                String sql2=" SELECT DISTINCT USER_NID, RESPONSABLE FROM TRA_DESTINO_RESPONSABLE";
        %>
        <!-- navbar-->
        <header class="header">
        </header>
        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <!--<div class="unwired alert alert-danger">¡Se ha perdido su conexión! TMS debe de estar conectado a Internet para su correcto funcionamiento.</div>-->
                    <section>
                        <div class="row">
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card h-100">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h2 class="card-heading">Personalizar Evento</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <form id="uploadFileFormData" name="uploadFileFormData">
                                            <div id="table-scroll" class="table-scroll">
                                                <table id="main-table" class="main-table" style="table-layout:fixed; width:600%;">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col" class="font-titulo">Número de evento <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Valor USD</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">ETA Port Of Discharge</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Agente Aduanal</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Pedimento A1</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Pedimento R1</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Motivo rectificación 1</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Pedimento R1 (2do)</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Motivo rectificación 2</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fecha Recepción Documentos</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fecha Revalidación/Liberación de BL</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fecha Previo Origen</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fecha Previo en destino</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fecha Resultado Previo</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Proforma Final</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Requiere permiso Si/No</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fecha envío Fichas/notas</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fec. Recepción de permisos tramit.</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fec. Act Permisos (Inic Vigencia)</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fec. Perm. Aut. (Fin de Vigencia)</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Cuenta con CO para aplicar preferencia Arancelaria Si/No</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Aplico Preferencia Arancelaria (CO) Si/No Razon</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Requiere UVA Si/No</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fecha Documentos Completos</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fecha Pago Pedimento</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fecha Solicitud de transporte</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fecha Modulacion</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Modalidad Camion/Tren</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Resultado Modulacion Verde / Rojo</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fecha Reconocimiento</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fecha Liberacion</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Sello Origen</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Sello Final</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fecha de retencion por la autoridad</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Fec. de liberacion por ret. de la aut.</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Estatus de la operación</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#F8CBAD">Motivo Atraso</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>         
                                                         <tr>
                                                              <th class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </th>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                         </tr>
                                                         <tr>
                                                              <th class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </th>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                         </tr>
                                                         <tr>
                                                              <th class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </th>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                         </tr>
                                                         <tr>
                                                              <th class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </th>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                         </tr>
                                                         <tr>
                                                              <th class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </th>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                         </tr>
                                                         <tr>
                                                              <th class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </th>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="date" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="validationCustom01" type="text" value="" required="">
                                                              </td>
                                                         </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <br>
                                            <!-- Botones controles -->
                                            <div class="col-lg-12" style="text-align: right;">
                                                <a class="btn btn-default text-nowrap" role="button" href="Importacion/eventos.jsp">Regresar</a>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <a class="btn btn-primary text-nowrap" id="uploadBtnid" name="uploadBtnid" role="button" onclick="save()">Guardar Información</a>
                                            </div>
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
