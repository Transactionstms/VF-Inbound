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
<%@page import="com.usuario.Usuario"%>
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
        <!-- jQuery/show modal -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- Connection Status Red -->
        <link href="../lib/inbound/conexion/connectionStatus.css" rel="stylesheet" type="text/css"/>
        <style>
            #contenedor {
              display: flex;
              flex-direction: row;
              flex-wrap: wrap;
            }

            #contenedor > div {
              width: 50%;
            }
        </style>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String view = request.getParameter("view");
                String idDivision = ownsession.getAttribute("cbdivcuenta").toString();
                String idBodega = ownsession.getAttribute("cbbodegaId").toString();
                Usuario root = (Usuario) ownsession.getAttribute("login.root");
                String tipoAgente = "3";  // (1)LOGIX       (2)CUSA       (3)GRAL
                String nombre = "";
                
                if (db.doDB("select NOMBRE from TRA_PLANTILLA where id='" + view + "' ")) {
                    for (String[] row : db.getResultado()) {
                        nombre = row[0];
                    }
                }
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
                                            <div id="contenedor">
                                                <div style="text-align: left;">
                                                    <button type="button" class="btn btn-success" onclick="openModalPlantilla()">Subir Plantilla</button>
                                                </div>
                                                <div style="text-align: right;">
                                                    <a class="btn btn-default text-nowrap" role="button" href="Importacion/eventos.jsp">Regresar</a>
                                                    <a class="btn btn-primary text-nowrap" id="uploadBtnid" name="uploadBtnid" role="button" onclick="save()">Guardar Información</a>
                                                </div>
                                            </div>
                                            <br>
                                            <div id="table-scroll" class="table-scroll">
                                                <table id="main-table" class="main-table" style="table-layout:fixed; width:800%;">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col" class="font-titulo">Número de evento <strong style="color:red">*</strong></th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Responsable</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Final Destination (Shipment)</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Brand-Division</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Division</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Shipment ID</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Container</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">BL/AWB/PRO</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">LoadType</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Quantity</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">POD</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Est. Departure from POL</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">ETA REAL Port of Discharge</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Est. Eta DC</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Inbound notification</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">POL</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">A.A.</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha Mes de Venta</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Prioridad Si/No</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4"> </th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">País Origen</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Size Container</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Valor USD</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">ETA Port Of Discharge</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Agente Aduanal</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Pedimento A1</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Pedimento R1</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Motivo rectificación 1</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Pedimento R1 (2do)</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Motivo rectificación 2</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Recepción Documentos</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#FF4040">Recinto</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#FF4040">Naviera / Forwarder</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#FF4040">Buque</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Revalidación/Liberación de BL</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Previo Origen</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Previo en destino</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Resultado Previo</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Proforma Final</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Requiere permiso Si/No</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha envío Fichas/notas</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fec. Recepción de permisos tramit.</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fec. Act Permisos (Inic Vigencia)</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fec. Perm. Aut. (Fin de Vigencia)</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Cuenta con CO para aplicar preferencia Arancelaria Si/No</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Aplico Preferencia Arancelaria (CO) Si/No Razon</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Requiere UVA Si/No</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#626567">Requiere CA Si/No</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#626567">Fecha Recepción CA</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#626567">Número de Constancia CA</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#626567">Monto CA</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Documentos Completos</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Pago Pedimento</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Solicitud de transporte</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Modulacion</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Modalidad Camion/Tren</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Resultado Modulacion Verde / Rojo</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Reconocimiento</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Liberacion</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Sello Origen</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Sello Final</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha de retencion por la autoridad</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fec. de liberacion por ret. de la aut.</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Estatus de la operación</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Motivo Atraso</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Observaciones</th>
                                                        <%
                                                            if(tipoAgente.equals("1")){        //Logix
                                                        %>    
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Llegada a NOVA</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Llegada a Globe trade SD </th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Archivo M</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha de Archivo M</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha Solicitud de Manipulacion</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha de vencimiento de Manipulacion</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha confirmacion Clave de Pedimento</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha de Recepcion de Incrementables</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">T&E</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha de Vencimiento del Inbond</th>
                                                        <%
                                                            }else if(tipoAgente.equals("2")){  //Cusa
                                                        %>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">No. BULTOS</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Peso (KG)</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Transferencia (SI / NO)</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha Iinicio Etiquetado</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha Termino Etiquetado</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Hora de termino Etiquetado</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Proveedor</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Proveedor de Carga</th>
                                                        <%
                                                            }
                                                        %> 
                                                        </tr>
                                                    </thead>
                                                    <tbody>         
                                                         <tr>
                                                              <th class="font-numero">
                                                                <select class="form-control" id="evento_id" name="evento_id" required="true">
                                                                    <option value="0" disabled selected> --- </option>
                                                                    <%
                                                                        if (db.doDB("SELECT DISTINCT ID_EVENTO FROM TRA_INB_EVENTO WHERE ESTADO = 1 AND USER_NID IS NOT NULL ORDER BY ID_EVENTO ASC")) {
                                                                            for (String[] row : db.getResultado()) {
                                                                                out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                            }
                                                                        }
                                                                    %>
                                                                </select>
                                                              </th>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero"></td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="pais_origen" name="pais_origen" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="size_container" name="size_container" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="valor_usd" name="valor_usd" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="eta_port_discharge" name="eta_port_discharge" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="agente_aduanal" name="agente_aduanal" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="pedimento_a1" name="pedimento_a1" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="pedimento_r1_1er" name="pedimento_r1_1er" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="motivo_rectificacion_1er" name="motivo_rectificacion_1er" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="pedimento_r1_2do" name="pedimento_r1_2do" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="motivo_rectificacion_2do" name="motivo_rectificacion_2do" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_recepcion_doc" name="fecha_recepcion_doc" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="recinto" name="recinto" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="naviera" name="naviera" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="buque" name="buque" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_revalidacion" name="fecha_revalidacion" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_previo_origen" name="fecha_previo_origen" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_previo_destino" name="fecha_previo_destino" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_resultado_previo" name="fecha_resultado_previo" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="proforma_final" name="proforma_final" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="permiso" name="permiso" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_envio" name="fecha_envio" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_recepcion_perm" name="fecha_recepcion_perm" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_activacion_perm" name="fecha_activacion_perm" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_permisos_aut" name="fecha_permisos_aut" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="co_pref_arancelaria" name="co_pref_arancelaria" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="aplic_pref_arancelaria" name="aplic_pref_arancelaria" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="req_uva" name="req_uva" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="req_ca" name="req_ca" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_recepcion_ca" name="fecha_recepcion_ca" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="num_constancia_ca" name="num_constancia_ca" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="monto_ca" name="monto_ca" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_doc_completos" name="fecha_doc_completos" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_pago_pedimento" name="fecha_pago_pedimento" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_solicitud_transporte" name="fecha_solicitud_transporte" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_modulacion" name="fecha_modulacion" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="modalidad" name="modalidad" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="resultado_modulacion" name="resultado_modulacion" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_reconocimiento" name="fecha_reconocimiento" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_liberacion" name="fecha_liberacion" type="date" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="sello_origen" name="sello_origen" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="sello_final" name="sello_final" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_retencion_aut" name="fecha_retencion_aut" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="fecha_liberacion_aut" name="fecha_liberacion_aut" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <select class="form-control" id="estatus_operacion" name="estatus_operacion" required="true">
                                                                    <option value="0" disabled selected> --- </option>
                                                                    <%
                                                                        if (db.doDB("SELECT DISTINCT ID_ESTADO, DESCRIPCION_ESTADO FROM TRA_ESTADOS_CUSTOMS WHERE ESTATUS = 1")) {
                                                                            for (String[] row : db.getResultado()) {
                                                                                out.println("<option value=\"" + row[0] + "\" >" + row[1] + "</option>");
                                                                            }
                                                                        }
                                                                    %>
                                                                </select>
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="motivo_atraso" name="motivo_atraso" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                <input class="form-control" id="observaciones" name="observaciones" type="text" autocomplete="off">
                                                              </td>
                                                            <%
                                                                if(tipoAgente.equals("1")){        //Logix
                                                            %>    
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                            <%
                                                                }else if(tipoAgente.equals("2")){  //Cusa
                                                            %>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                              <td class="font-numero">
                                                                  <input class="form-control" id="" name="" type="text" autocomplete="off">
                                                              </td>
                                                            <%
                                                                }
                                                            %>
                                                         </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <br>
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
        <!-- modal - Subir Plantilla --> 
        <div class="modal fade text-start" id="modalSubirPlantilla" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-header border-0 bg-gray-100">
                        <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;<%=nombre%></h3>
                        <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container"> 
                            <div class="mb-3">
                                <input class="form-control" type="file" id="input-id" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
                            </div>
                            <div class="row position-relative" style="top: 10px;">
                                <div class="col-6 text-center">
                                    <button class="btn float-start btn-primary" id="created_file"  >Descargar</button>
                                </div>
                                <div class="col-6 text-center">
                                    <button class="btn float-end btn-success"  id="upload_file"   >Subir</button>
                                </div>
                            </div>
                            <br>
                            <div align="center" class="col-md-11">
                                <div align="center" id="divResultado" name="divResultado"></div>
                            </div>
                        </div>
                        <input type="hidden" name="idPlantilla" value="<%=view%>" id="idPlantilla"/>
                        <input type="hidden" name="idOpcion" value="1" id="idOpcion"/>
                        <input type="hidden" name="idLenguaje" value="1" id="idLenguaje"/>
                        <input type="hidden" name="idDivision" value="<%=idDivision%>" id="idDivision"/>
                        <input type="hidden" name="idBodega" value="<%=idBodega%>" id="idBodega"/>
                        <input type="hidden" name="idAction" value="<%=request.getContextPath()%>/plantillaExcel" id="idAction"/>
                        <img src="../img/loadingCloud.gif" id="idClouding" width="50px" height="50px" name="idClouding" title="Clouding" style="display: none; height: 50px; width: 50px;"/>
                    </div>
                </div>
            </div>
        </div>                    
        <script>
            function openModalPlantilla() {
                $("#modalSubirPlantilla").modal("show");
            }
        </script>                    
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
        <script src="../lib/validationsInbound/customs/customsForms.js" type="text/javascript"></script>
        <!-- sweetalert -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
        <!-- excel -->
        <script src="<%=request.getContextPath()%>/plantillas/lib/upload_file.js" type="text/javascript"></script>
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
