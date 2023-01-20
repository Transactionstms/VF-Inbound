<%-- 
    Document   : gtn
    Created on : 19/01/2023, 04:34:02 AM
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
        <title>Modificar GTN</title>
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
        <link href="../lib/inbound/gtn/styleGTN.css" rel="stylesheet" type="text/css"/>
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
                                                <h2 class="card-heading">Modificar GTN</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <form id="uploadFileFormData" name="uploadFileFormData">
                                            <div id="table-scroll" class="table-scroll">
                                                <table id="main-table" class="main-table" style="table-layout:fixed; width:500%;">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col" class="font-titulo">Shipment Details</th>	
                                                            <th scope="col" class="font-titulo">Brand-Division <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Shipment ID <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Container <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">BL/ AWB/ PRO <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Load Type <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Container Type <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo"># of Packages <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Quantity <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Mode <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Final Destination (Shipment) <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Purchase Order <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Style # <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Style Desc. <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">POL <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">POD <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Actual CRD <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Received from Vendor <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Est. Departure from POL <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Departed from POL <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Docs Provided to Broker <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">ETA Port of Discharge <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">ETA at POD (Updated Milestone)</th>	
                                                            <th scope="col" class="font-titulo">Arrived at POD</th>	
                                                            <th scope="col" class="font-titulo">Outlook ETA</th>	
                                                            <th scope="col" class="font-titulo">Delivered to Final Dest</th>	
                                                            <th scope="col" class="font-titulo">Received at Final Dest</th>	
                                                            <th scope="col" class="font-titulo">View Docs</th>
                                                            <th scope="col" class="font-titulo">Eliminar</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>
                                                            <td class="font-numero"><select class="" id="brand" name="brand"><option value="1">OD RETAIL</option><option value="2" selected>OD TIMBERLAND</option><option value="3">OD THE NORT FACE</option></select></td>	
                                                            <td class="font-numero"><input type="hidden" id="shipment" name="shipment" value="5011885374">5011885374</td>	
                                                            <td class="font-numero"><input class="" type="text" id="container" name="container" value="TCKU7711790"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="blAwbPro" name="blAwbPro" value="MAEU222517139"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="loadType" name="loadType" value="FCL"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="containerType" name="containerType" value="40' High Cube Dr"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="packages" name="packages" value="149"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="quantity" name="quantity" value="860"></td>	
                                                            <td class="font-numero"><select class="" id="mode" name="mode"><option value="1">Earth</option><option value="2" selected>Ocean</option><option value="3">Air</option></select></td>	
                                                            <td class="font-texto"><select class="" id="destination" name="destination"><option value="1">CENTRO DE DISTRIBUCIÓN</option><option value="2" selected>VF OUTDOOR MEXICO</option><option value="3">OTROS</option></select></td>	
                                                            <td class="font-numero"><input class="" type="text" id="order" name="order" value="4001351144"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleNum" name="styleNum" value="TB0A5R9M015"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleDesc" name="styleDesc" value="ATWELLS AVE CHELSEA BLK / JET BLACK"></td>
                                                            <td class="font-texto"><input class="" type="text" id="pol" name="pol" value="Jakarta, ID"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="pod" name="pod" value="Lazaro Cardenas, M"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="crd" name="crd"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="received" name="received"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departure" name="departure"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departed" name="departed"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="provided" name="provided"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="discharge" name="discharge"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="milestone" name="milestone"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="arrived" name="arrived"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="outlook" name="outlook"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="Delivered" name="Delivered" value="5011885374"></td>
                                                            <td class="font-texto"><input class="" type="text" id="Received" name="Received" value="5011885374"></td>		
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="delete_registro()"><i class="far fa-trash-alt"></i></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>	
                                                            <td class="font-numero"><select class="" id="brand" name="brand"><option value="1">OD RETAIL</option><option value="2" selected>OD TIMBERLAND</option><option value="3">OD THE NORT FACE</option></select></td>	
                                                            <td class="font-numero"><input type="hidden" id="shipment" name="shipment" value="5011885374">5011885374</td>	
                                                            <td class="font-numero"><input class="" type="text" id="container" name="container" value="TCKU7711790"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="blAwbPro" name="blAwbPro" value="MAEU222517139"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="loadType" name="loadType" value="FCL"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="containerType" name="containerType" value="40' High Cube Dr"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="packages" name="packages" value="149"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="quantity" name="quantity" value="860"></td>	
                                                            <td class="font-numero"><select class="" id="mode" name="mode"><option value="1">Earth</option><option value="2" selected>Ocean</option><option value="3">Air</option></select></td>	
                                                            <td class="font-texto"><select class="" id="destination" name="destination"><option value="1">CENTRO DE DISTRIBUCIÓN</option><option value="2" selected>VF OUTDOOR MEXICO</option><option value="3">OTROS</option></select></td>	
                                                            <td class="font-numero"><input class="" type="text" id="order" name="order" value="4001351144"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleNum" name="styleNum" value="TB0A5R9M015"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleDesc" name="styleDesc" value="ATWELLS AVE CHELSEA BLK / JET BLACK"></td>
                                                            <td class="font-texto"><input class="" type="text" id="pol" name="pol" value="Jakarta, ID"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="pod" name="pod" value="Lazaro Cardenas, M"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="crd" name="crd"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="received" name="received"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departure" name="departure"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departed" name="departed"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="provided" name="provided"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="discharge" name="discharge"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="milestone" name="milestone"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="arrived" name="arrived"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="outlook" name="outlook"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="Delivered" name="Delivered" value="5011885374"></td>
                                                            <td class="font-texto"><input class="" type="text" id="Received" name="Received" value="5011885374"></td>		
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="delete_registro()"><i class="far fa-trash-alt"></i></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>	
                                                            <td class="font-numero"><select class="" id="brand" name="brand"><option value="1">OD RETAIL</option><option value="2" selected>OD TIMBERLAND</option><option value="3">OD THE NORT FACE</option></select></td>	
                                                            <td class="font-numero"><input type="hidden" id="shipment" name="shipment" value="5011885374">5011885374</td>	
                                                            <td class="font-numero"><input class="" type="text" id="container" name="container" value="TCKU7711790"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="blAwbPro" name="blAwbPro" value="MAEU222517139"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="loadType" name="loadType" value="FCL"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="containerType" name="containerType" value="40' High Cube Dr"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="packages" name="packages" value="149"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="quantity" name="quantity" value="860"></td>	
                                                            <td class="font-numero"><select class="" id="mode" name="mode"><option value="1">Earth</option><option value="2" selected>Ocean</option><option value="3">Air</option></select></td>	
                                                            <td class="font-texto"><select class="" id="destination" name="destination"><option value="1">CENTRO DE DISTRIBUCIÓN</option><option value="2" selected>VF OUTDOOR MEXICO</option><option value="3">OTROS</option></select></td>	
                                                            <td class="font-numero"><input class="" type="text" id="order" name="order" value="4001351144"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleNum" name="styleNum" value="TB0A5R9M015"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleDesc" name="styleDesc" value="ATWELLS AVE CHELSEA BLK / JET BLACK"></td>
                                                            <td class="font-texto"><input class="" type="text" id="pol" name="pol" value="Jakarta, ID"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="pod" name="pod" value="Lazaro Cardenas, M"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="crd" name="crd"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="received" name="received"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departure" name="departure"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departed" name="departed"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="provided" name="provided"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="discharge" name="discharge"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="milestone" name="milestone"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="arrived" name="arrived"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="outlook" name="outlook"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="Delivered" name="Delivered" value="5011885374"></td>
                                                            <td class="font-texto"><input class="" type="text" id="Received" name="Received" value="5011885374"></td>		
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="delete_registro()"><i class="far fa-trash-alt"></i></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>	
                                                            <td class="font-numero"><select class="" id="brand" name="brand"><option value="1">OD RETAIL</option><option value="2" selected>OD TIMBERLAND</option><option value="3">OD THE NORT FACE</option></select></td>	
                                                            <td class="font-numero"><input type="hidden" id="shipment" name="shipment" value="5011885374">5011885374</td>	
                                                            <td class="font-numero"><input class="" type="text" id="container" name="container" value="TCKU7711790"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="blAwbPro" name="blAwbPro" value="MAEU222517139"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="loadType" name="loadType" value="FCL"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="containerType" name="containerType" value="40' High Cube Dr"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="packages" name="packages" value="149"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="quantity" name="quantity" value="860"></td>	
                                                            <td class="font-numero"><select class="" id="mode" name="mode"><option value="1">Earth</option><option value="2" selected>Ocean</option><option value="3">Air</option></select></td>	
                                                            <td class="font-texto"><select class="" id="destination" name="destination"><option value="1">CENTRO DE DISTRIBUCIÓN</option><option value="2" selected>VF OUTDOOR MEXICO</option><option value="3">OTROS</option></select></td>	
                                                            <td class="font-numero"><input class="" type="text" id="order" name="order" value="4001351144"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleNum" name="styleNum" value="TB0A5R9M015"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleDesc" name="styleDesc" value="ATWELLS AVE CHELSEA BLK / JET BLACK"></td>
                                                            <td class="font-texto"><input class="" type="text" id="pol" name="pol" value="Jakarta, ID"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="pod" name="pod" value="Lazaro Cardenas, M"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="crd" name="crd"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="received" name="received"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departure" name="departure"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departed" name="departed"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="provided" name="provided"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="discharge" name="discharge"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="milestone" name="milestone"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="arrived" name="arrived"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="outlook" name="outlook"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="Delivered" name="Delivered" value="5011885374"></td>
                                                            <td class="font-texto"><input class="" type="text" id="Received" name="Received" value="5011885374"></td>		
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="delete_registro()"><i class="far fa-trash-alt"></i></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>	
                                                            <td class="font-numero"><select class="" id="brand" name="brand"><option value="1">OD RETAIL</option><option value="2" selected>OD TIMBERLAND</option><option value="3">OD THE NORT FACE</option></select></td>	
                                                            <td class="font-numero"><input type="hidden" id="shipment" name="shipment" value="5011885374">5011885374</td>	
                                                            <td class="font-numero"><input class="" type="text" id="container" name="container" value="TCKU7711790"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="blAwbPro" name="blAwbPro" value="MAEU222517139"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="loadType" name="loadType" value="FCL"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="containerType" name="containerType" value="40' High Cube Dr"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="packages" name="packages" value="149"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="quantity" name="quantity" value="860"></td>	
                                                            <td class="font-numero"><select class="" id="mode" name="mode"><option value="1">Earth</option><option value="2" selected>Ocean</option><option value="3">Air</option></select></td>	
                                                            <td class="font-texto"><select class="" id="destination" name="destination"><option value="1">CENTRO DE DISTRIBUCIÓN</option><option value="2" selected>VF OUTDOOR MEXICO</option><option value="3">OTROS</option></select></td>	
                                                            <td class="font-numero"><input class="" type="text" id="order" name="order" value="4001351144"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleNum" name="styleNum" value="TB0A5R9M015"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleDesc" name="styleDesc" value="ATWELLS AVE CHELSEA BLK / JET BLACK"></td>
                                                            <td class="font-texto"><input class="" type="text" id="pol" name="pol" value="Jakarta, ID"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="pod" name="pod" value="Lazaro Cardenas, M"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="crd" name="crd"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="received" name="received"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departure" name="departure"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departed" name="departed"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="provided" name="provided"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="discharge" name="discharge"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="milestone" name="milestone"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="arrived" name="arrived"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="outlook" name="outlook"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="Delivered" name="Delivered" value="5011885374"></td>
                                                            <td class="font-texto"><input class="" type="text" id="Received" name="Received" value="5011885374"></td>		
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="delete_registro()"><i class="far fa-trash-alt"></i></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>	
                                                            <td class="font-numero"><select class="" id="brand" name="brand"><option value="1">OD RETAIL</option><option value="2" selected>OD TIMBERLAND</option><option value="3">OD THE NORT FACE</option></select></td>	
                                                            <td class="font-numero"><input type="hidden" id="shipment" name="shipment" value="5011885374">5011885374</td>	
                                                            <td class="font-numero"><input class="" type="text" id="container" name="container" value="TCKU7711790"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="blAwbPro" name="blAwbPro" value="MAEU222517139"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="loadType" name="loadType" value="FCL"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="containerType" name="containerType" value="40' High Cube Dr"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="packages" name="packages" value="149"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="quantity" name="quantity" value="860"></td>	
                                                            <td class="font-numero"><select class="" id="mode" name="mode"><option value="1">Earth</option><option value="2" selected>Ocean</option><option value="3">Air</option></select></td>	
                                                            <td class="font-texto"><select class="" id="destination" name="destination"><option value="1">CENTRO DE DISTRIBUCIÓN</option><option value="2" selected>VF OUTDOOR MEXICO</option><option value="3">OTROS</option></select></td>	
                                                            <td class="font-numero"><input class="" type="text" id="order" name="order" value="4001351144"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleNum" name="styleNum" value="TB0A5R9M015"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleDesc" name="styleDesc" value="ATWELLS AVE CHELSEA BLK / JET BLACK"></td>
                                                            <td class="font-texto"><input class="" type="text" id="pol" name="pol" value="Jakarta, ID"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="pod" name="pod" value="Lazaro Cardenas, M"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="crd" name="crd"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="received" name="received"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departure" name="departure"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departed" name="departed"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="provided" name="provided"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="discharge" name="discharge"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="milestone" name="milestone"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="arrived" name="arrived"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="outlook" name="outlook"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="Delivered" name="Delivered" value="5011885374"></td>
                                                            <td class="font-texto"><input class="" type="text" id="Received" name="Received" value="5011885374"></td>		
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="delete_registro()"><i class="far fa-trash-alt"></i></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>	
                                                            <td class="font-numero"><select class="" id="brand" name="brand"><option value="1">OD RETAIL</option><option value="2" selected>OD TIMBERLAND</option><option value="3">OD THE NORT FACE</option></select></td>	
                                                            <td class="font-numero"><input type="hidden" id="shipment" name="shipment" value="5011885374">5011885374</td>	
                                                            <td class="font-numero"><input class="" type="text" id="container" name="container" value="TCKU7711790"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="blAwbPro" name="blAwbPro" value="MAEU222517139"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="loadType" name="loadType" value="FCL"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="containerType" name="containerType" value="40' High Cube Dr"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="packages" name="packages" value="149"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="quantity" name="quantity" value="860"></td>	
                                                            <td class="font-numero"><select class="" id="mode" name="mode"><option value="1">Earth</option><option value="2" selected>Ocean</option><option value="3">Air</option></select></td>	
                                                            <td class="font-texto"><select class="" id="destination" name="destination"><option value="1">CENTRO DE DISTRIBUCIÓN</option><option value="2" selected>VF OUTDOOR MEXICO</option><option value="3">OTROS</option></select></td>	
                                                            <td class="font-numero"><input class="" type="text" id="order" name="order" value="4001351144"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleNum" name="styleNum" value="TB0A5R9M015"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleDesc" name="styleDesc" value="ATWELLS AVE CHELSEA BLK / JET BLACK"></td>
                                                            <td class="font-texto"><input class="" type="text" id="pol" name="pol" value="Jakarta, ID"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="pod" name="pod" value="Lazaro Cardenas, M"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="crd" name="crd"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="received" name="received"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departure" name="departure"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departed" name="departed"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="provided" name="provided"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="discharge" name="discharge"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="milestone" name="milestone"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="arrived" name="arrived"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="outlook" name="outlook"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="Delivered" name="Delivered" value="5011885374"></td>
                                                            <td class="font-texto"><input class="" type="text" id="Received" name="Received" value="5011885374"></td>		
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="delete_registro()"><i class="far fa-trash-alt"></i></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>	
                                                            <td class="font-numero"><select class="" id="brand" name="brand"><option value="1">OD RETAIL</option><option value="2" selected>OD TIMBERLAND</option><option value="3">OD THE NORT FACE</option></select></td>	
                                                            <td class="font-numero"><input type="hidden" id="shipment" name="shipment" value="5011885374">5011885374</td>	
                                                            <td class="font-numero"><input class="" type="text" id="container" name="container" value="TCKU7711790"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="blAwbPro" name="blAwbPro" value="MAEU222517139"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="loadType" name="loadType" value="FCL"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="containerType" name="containerType" value="40' High Cube Dr"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="packages" name="packages" value="149"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="quantity" name="quantity" value="860"></td>	
                                                            <td class="font-numero"><select class="" id="mode" name="mode"><option value="1">Earth</option><option value="2" selected>Ocean</option><option value="3">Air</option></select></td>	
                                                            <td class="font-texto"><select class="" id="destination" name="destination"><option value="1">CENTRO DE DISTRIBUCIÓN</option><option value="2" selected>VF OUTDOOR MEXICO</option><option value="3">OTROS</option></select></td>	
                                                            <td class="font-numero"><input class="" type="text" id="order" name="order" value="4001351144"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleNum" name="styleNum" value="TB0A5R9M015"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleDesc" name="styleDesc" value="ATWELLS AVE CHELSEA BLK / JET BLACK"></td>
                                                            <td class="font-texto"><input class="" type="text" id="pol" name="pol" value="Jakarta, ID"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="pod" name="pod" value="Lazaro Cardenas, M"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="crd" name="crd"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="received" name="received"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departure" name="departure"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departed" name="departed"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="provided" name="provided"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="discharge" name="discharge"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="milestone" name="milestone"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="arrived" name="arrived"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="outlook" name="outlook"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="Delivered" name="Delivered" value="5011885374"></td>
                                                            <td class="font-texto"><input class="" type="text" id="Received" name="Received" value="5011885374"></td>		
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="delete_registro()"><i class="far fa-trash-alt"></i></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>	
                                                            <td class="font-numero"><select class="" id="brand" name="brand"><option value="1">OD RETAIL</option><option value="2" selected>OD TIMBERLAND</option><option value="3">OD THE NORT FACE</option></select></td>	
                                                            <td class="font-numero"><input type="hidden" id="shipment" name="shipment" value="5011885374">5011885374</td>	
                                                            <td class="font-numero"><input class="" type="text" id="container" name="container" value="TCKU7711790"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="blAwbPro" name="blAwbPro" value="MAEU222517139"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="loadType" name="loadType" value="FCL"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="containerType" name="containerType" value="40' High Cube Dr"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="packages" name="packages" value="149"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="quantity" name="quantity" value="860"></td>	
                                                            <td class="font-numero"><select class="" id="mode" name="mode"><option value="1">Earth</option><option value="2" selected>Ocean</option><option value="3">Air</option></select></td>	
                                                            <td class="font-texto"><select class="" id="destination" name="destination"><option value="1">CENTRO DE DISTRIBUCIÓN</option><option value="2" selected>VF OUTDOOR MEXICO</option><option value="3">OTROS</option></select></td>	
                                                            <td class="font-numero"><input class="" type="text" id="order" name="order" value="4001351144"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleNum" name="styleNum" value="TB0A5R9M015"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleDesc" name="styleDesc" value="ATWELLS AVE CHELSEA BLK / JET BLACK"></td>
                                                            <td class="font-texto"><input class="" type="text" id="pol" name="pol" value="Jakarta, ID"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="pod" name="pod" value="Lazaro Cardenas, M"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="crd" name="crd"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="received" name="received"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departure" name="departure"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departed" name="departed"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="provided" name="provided"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="discharge" name="discharge"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="milestone" name="milestone"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="arrived" name="arrived"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="outlook" name="outlook"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="Delivered" name="Delivered" value="5011885374"></td>
                                                            <td class="font-texto"><input class="" type="text" id="Received" name="Received" value="5011885374"></td>		
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="delete_registro()"><i class="far fa-trash-alt"></i></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>	
                                                            <td class="font-numero"><select class="" id="brand" name="brand"><option value="1">OD RETAIL</option><option value="2" selected>OD TIMBERLAND</option><option value="3">OD THE NORT FACE</option></select></td>	
                                                            <td class="font-numero"><input type="hidden" id="shipment" name="shipment" value="5011885374">5011885374</td>	
                                                            <td class="font-numero"><input class="" type="text" id="container" name="container" value="TCKU7711790"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="blAwbPro" name="blAwbPro" value="MAEU222517139"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="loadType" name="loadType" value="FCL"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="containerType" name="containerType" value="40' High Cube Dr"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="packages" name="packages" value="149"></td>	
                                                            <td class="font-numero"><input class="" type="text" id="quantity" name="quantity" value="860"></td>	
                                                            <td class="font-numero"><select class="" id="mode" name="mode"><option value="1">Earth</option><option value="2" selected>Ocean</option><option value="3">Air</option></select></td>	
                                                            <td class="font-texto"><select class="" id="destination" name="destination"><option value="1">CENTRO DE DISTRIBUCIÓN</option><option value="2" selected>VF OUTDOOR MEXICO</option><option value="3">OTROS</option></select></td>	
                                                            <td class="font-numero"><input class="" type="text" id="order" name="order" value="4001351144"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleNum" name="styleNum" value="TB0A5R9M015"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="styleDesc" name="styleDesc" value="ATWELLS AVE CHELSEA BLK / JET BLACK"></td>
                                                            <td class="font-texto"><input class="" type="text" id="pol" name="pol" value="Jakarta, ID"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="pod" name="pod" value="Lazaro Cardenas, M"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="crd" name="crd"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="received" name="received"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departure" name="departure"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="departed" name="departed"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="provided" name="provided"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="discharge" name="discharge"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="milestone" name="milestone"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="arrived" name="arrived"></td>	
                                                            <td class="font-fecha"><input class="" type="date" id="outlook" name="outlook"></td>	
                                                            <td class="font-texto"><input class="" type="text" id="Delivered" name="Delivered" value="5011885374"></td>
                                                            <td class="font-texto"><input class="" type="text" id="Received" name="Received" value="5011885374"></td>		
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="openModalDetails()"><i class="far fa-folder-open"></i></a></td>
                                                            <td class="font-numero"><a class="text-lg text-info" onclick="delete_registro()"><i class="far fa-trash-alt"></i></a></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <br>
                                            <!-- Botones controles -->
                                            <div class="col-lg-12" style="text-align: right;">
                                                <a class="btn btn-default text-nowrap" role="button" href="../Reportes/gtn.jsp">Regresar</a>
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
                
                <!-- modal - Vista Previa CFDI --> 
                <div class="modal fade text-start" id="modalVistaPreviaDetails" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header border-0 bg-gray-100">
                                <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Vista previa detalles:</h3>
                                <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="container" id="visor1"> 
                                    Detalles
                                </div>
                            </div>
                        </div>
                    </div>
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
        <script src="../lib/inbound/gtn/functionsGtn.js" type="text/javascript"></script>
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