<%-- 
    Document   : customAct
    Created on : 21-sep-2023, 15:24:03
    Author     : grecendiz
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

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Eventos </title>
        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
        <!-- Table css -->
        <link href="../lib/validationsInbound/customs/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- Multiselect -->
        <!--<link href="../lib/Multiselect/css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- calendarios -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.css">
        <script src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.js"></script>
        <!-- Window load -->
        <link href="../lib/Loader/css/windowsLoad.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

        <style>

            /* En tu archivo CSS */
            th {
                width:350px !important;
            }

        </style>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
                OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
                String UserId = (String) ownsession.getAttribute("login.user_id_number");
                String filterType = request.getParameter("filterType");
                String id = request.getParameter("id");
                String AgentType = "";

                ConsultasQuery fac = new ConsultasQuery();

                //Obtener el agente aduanal, id plantilla y nombre plantilla del usuario: 
                if (db.doDB(fac.consultarAgenteAduanalCustoms(UserId))) {
                    for (String[] rowA : db.getResultado()) {
                        AgentType = rowA[0];
                    }
                }

                String idPlantilla = "";
                String namePlantilla = "";
                String caramelo = "";
                String colorSemaforo = "";
                String sizeSemaforo = "";
                int cont = 1;

               

        %>

        <%             String op1 = "";
            if (db.doDB(fac.consultarEstatusOperacionCustoms())) {
                for (String[] rowO : db.getResultado()) {
                    op1 += "<option value=\"" + rowO[0] + "\" >" + rowO[1] + "</option>";
                }
            }
        %>

        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
 

                    <section>
                        <div class="row">
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card ">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h2 class="card-heading">Personalizar Customs</h2>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    
         
                            
                                    
                                    
                                    <div class="card-body">
                                        <form id="uploadFileFormData1" name="uploadFileFormData1">
                                            <br>
                                            <div id="table-scroll" class="table-scroll"  style="height: 100%;">
                                                <input type="hidden" id="filterType" name="filterType" value="<%=filterType%>">
                                                <input type="hidden" id="id" name="id" value="<%=id%>">
                                                <input type="hidden" id="idAgenteAduanal" name="idAgenteAduanal" value="<%=AgentType%>">
                                            <div class="table-responsive">
                                                <table  id="tables" class="table table-bordered table-hover" style="width:300%">
                                                    <!--<table id="main-table" class="main-table" style="table-layout:fixed; width:1500%;">-->
                                                    <thead>
                                                        <tr>
                                                            <%
                                                                if (AgentType.equals("4001") || AgentType.equals("4002") || AgentType.equals("4003") || AgentType.equals("4004") || AgentType.equals("4005") || AgentType.equals("4006")) { //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF   
                                                            %>                 
                                                            <th  class="col-sm-1" class="font-titulo" style="background-color:#FFFFFF;"><p>Estatus</p></th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#333F4F;">
                                                                <font size="1">Referencia AA</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Evento <strong style="color:red">*</strong></font> 
          <button onclick="mostrarFilasSelect(true,'col_llegada_a_nova',2)" type="button" class="btn badge-info"><i class="fa fa-search"></i></button>
         <select onchange="mostrarFilasSelect(false,'col_llegada_a_nova',2)"  multiple class="custom-select" id="col_llegada_a_nova" name="col_llegada_a_nova"></select>
       

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Responsable</font> 
                                                                
           <button onclick="mostrarFilasSelect(true,'arrayList_referenciaAA',3)" type="button" class="btn badge-info"><i class="fa fa-search"></i></button>
         <select onchange="mostrarFilasSelect(false,'arrayList_referenciaAA',3)"  multiple class="custom-select" id="arrayList_referenciaAA" name="arrayList_referenciaAA"></select>

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Final Destination</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Brand-Division</font> 

                                                                </select>
                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Division</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Shipment ID</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Container</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">BL/AWB/PRO</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">LoadType</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Quantity</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">POD</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Est. Departure from POL</font> 

                                                            </th>
                                                            <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">ETA REAL Port of Discharge</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Est. Eta DC</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Inbound notification</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">POL</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">A.A.</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Fecha Mes de Venta</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Prioridad Si/No</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">País Origen</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Size Container</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Valor USD</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">ETA Port Of Discharge</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Agente Aduanal</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Pedimento A1</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Pedimento R1</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Motivo rectificación 1</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Pedimento R1 (2do)</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Motivo rectificación 2</font> 

                                                            </th>
                                                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fecha Recepción Documentos</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#e04141;">
                                                                <font size="1">Recinto</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#e04141;">
                                                                <font size="1">Naviera / Forwarder</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#e04141;">
                                                                <font size="1">Buque</font> 

                                                            </th>
                                                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fecha Revalidación/Liberación de BL</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fecha Previo Origen</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fecha Previo en destino</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fecha Resultado Previo</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Proforma Final</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Requiere permiso</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fecha envío Fichas/notas</font> 

                                                            </th>
                                                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fec. Recepción de permisos tramit.</font> 

                                                            </th>

                                                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fec. Act Permisos (Inic Vigencia)</font> 

                                                            </th>
                                                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fec. Perm. Aut. (Fin de Vigencia)</font> 

                                                            </th>
                                                            <th class="col-sm-6" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Cuenta con CO para aplicar preferencia Arancelaria</font> 

                                                            </th>
                                                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Aplico Preferencia Arancelaria</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Requiere UVA</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#a6a2a2;">
                                                                <font size="1">Requiere CA</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#a6a2a2;">
                                                                <font size="1">Fecha Recepción CA</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#a6a2a2;">
                                                                <font size="1">Número de Constancia CA</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#a6a2a2;">
                                                                <font size="1">Monto CA</font> 

                                                            </th>
                                                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fecha Documentos Completos</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fecha Pago Pedimento</font> 

                                                            </th>
                                                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fecha Solicitud de transporte</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fecha Modulacion</font>

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Modalidad</font>

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Resultado Modulacion</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fecha Reconocimiento</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fecha Liberacion</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Sello Origen</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Sello Final</font>

                                                            </th>
                                                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fecha de retencion por la autoridad</font> 

                                                            </th>
                                                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Fec. de liberacion por ret. de la aut.</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Estatus de la operación</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Motivo Atraso</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                                <font size="1">Observaciones</font> 

                                                            </th>
                                                            <%
                                                                }

                                                                if (AgentType.equals("4001") || AgentType.equals("4006")) { //LOGIX Y VF 
                                                            %>           
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Llegada a NOVA</font>

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Llegada a Globe trade SD</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Archivo M</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Fecha de Archivo M</font> 

                                                            </th>
                                                            <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Fecha Solicitud de Manipulacion</font> 

                                                            </th>
                                                            <th class="col-sm-6" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Fecha de vencimiento de Manipulacion</font> 

                                                            </th>
                                                            <th class="col-sm-6" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Fecha confirmacion Clave de Pedimento</font> 

                                                            </th>
                                                            <th class="col-sm-6" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Fecha de Recepcion de Incrementables</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">T&E</font> 

                                                            </th>
                                                            <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Fecha de Vencimiento del Inbound</font> 

                                                            </th>
                                                            <%
                                                                }

                                                                if (AgentType.equals("4002") || AgentType.equals("4006")) {  //CUSA Y VF
                                                            %>            
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">No. BULTOS</font>

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Peso (KG)</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Transferencia</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Fecha Inicio Etiquetado</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Fecha Termino Etiquetado</font> 

                                                            </th>
                                                            <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Hora de termino Etiquetado</font>

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Proveedor</font> 

                                                            </th>
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">Proveedor de Carga</font> 

                                                            </th>
                                                            <%
                                                                }
                                                            %>            
                                                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                                <font size="1">FY</font> 

                                                            </th>
                                                            <th class="col-sm-1" class="font-titulo" style="background-color:#FFFFFF;"></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="detalleCustom">
                                                        <%
                                                            
                                                            
                                                             String list_evento = " ";
                                                             String list_referenciaAA = " ";
String list_responsable = " ";
String list_finalDestination = " ";
String list_brandDivision = " ";
String list_division = " ";
String list_shipmentId = " ";
String list_containerId = " ";
String list_blAwbPro = " ";
String list_loadType = " ";
String list_quantity = " ";
String list_pod = " ";
String list_estDepartFromPol = " ";
String list_etaRealPortOfDischarge = " ";
String list_estEtaDc = " ";
String list_inboundNotification = " ";
String list_pol = " ";
String list_aa = " ";
String list_fechaMesVenta = " ";
String list_prioridad = " ";
String list_pais_origen= " ";
String list_size_container = " ";    
String list_valor_usd = " "; 
String list_eta_port_discharge = " ";         
String list_agente_aduanal = " ";              
String list_pedimento_a1 = " ";              
String list_pedimento_r1_1er = " ";          
String list_motivo_rectificacion_1er = " ";    
String list_pedimento_r1_2do = " ";         
String list_motivo_rectificacion_2do = " ";    
String list_fecha_recepcion_doc = " ";    
String list_recinto = " ";
String list_naviera = " ";
String list_buque = " ";
String list_fecha_revalidacion = " ";      
String list_fecha_previo_origen = " ";     
String list_fecha_previo_destino = " ";   
String list_fecha_resultado_previo = " ";   
String list_proforma_final = " ";        
String list_permiso = " ";               
String list_fecha_envio = " ";             
String list_fecha_recepcion_perm = " ";
String list_fecha_activacion_perm = " ";   
String list_fecha_permisos_aut = " ";     
String list_co_pref_arancelaria = " ";    
String list_aplic_pref_arancelaria = " ";  
String list_req_uva = " ";  
String list_req_ca = " ";    
String list_fecha_recepcion_ca = " "; 
String list_num_constancia_ca = " ";   
String list_monto_ca = " ";
String list_fecha_doc_completos = " ";  
String list_fecha_pago_pedimento = " ";     
String list_fecha_solicitud_transporte = " ";
String list_fecha_modulacion = " ";   
String list_modalidad = " ";          
String list_resultado_modulacion = " ";    
String list_fecha_reconocimiento = " ";     
String list_fecha_liberacion = " ";       
String list_sello_origen = " ";            
String list_sello_final = " ";      
String list_fecha_retencion_aut = " ";     
String list_fecha_liberacion_aut = " ";   
String list_estatus_operacion = " ";      
String list_motivo_atraso = " ";          
String list_observaciones = " "; 
String list_llegada_a_nova = " ";
String list_llegada_a_globe_trade_sd = " ";
String list_archivo_m = " ";
String list_fecha_archivo_m = " ";
String list_fecha_solicit_manip = " ";
String list_fecha_vencim_manip = " ";
String list_fecha_confirm_clave_pedim = " ";
String list_fecha_recep_increment = " ";
String list_t_e = " ";
String list_fecha_vencim_inbound = " ";  
String list_no_bultos = " ";
String list_peso_kg = " ";
String list_transferencia = " ";
String list_fecha_inicio_etiquetado = " ";
String list_fecha_termino_etiquetado = " ";
String list_hora_termino_etiquetado = " ";
String list_proveedor = " ";
String list_proveedor_carga = " ";     
String list_fy = " ";
                                                             
                                                             
                                                            if (db.doDB(fac.consultarEventosCustoms(AgentType, filterType, caramelo))) {
                                                                System.out.println("fac.consultarEventosCustoms(AgentType,filterType,caramelo.-" + fac.consultarEventosCustoms(AgentType, filterType, caramelo));
                                                                for (String[] row : db.getResultado()) {

list_evento += "<option value='"+row[0]+"'>"+row[0]+"</option>";
list_referenciaAA+="<option value='"+row[30]+"'>"+row[30]+"</option>";
list_responsable+= "<option value='"+row[1]+"'>"+row[1]+"</option>";
list_finalDestination+= "'" +row[2]+ "',";
list_brandDivision+= "'" +row[21]+ "',";
list_division+= "'" +row[4]+ "',";
list_shipmentId+= "'" +row[5]+ "',";
list_containerId+= "'" +row[6]+ "',";
list_blAwbPro+= "'" +row[7]+ "',";
list_loadType+= "'" +row[8]+ "',";
list_quantity+= "'" +row[9]+ "',";
list_pod+= "'" +row[19]+ "',";
list_estDepartFromPol+= "'" +row[11]+ "',";
list_etaRealPortOfDischarge+= "'" +row[12]+ "',";
list_estEtaDc+= "'" +row[22]+ "',";
list_inboundNotification+= "'" +row[14]+ "',"; 
list_pol+= "'" +row[20]+ "',";
list_aa+= "'" +row[16]+ "',";
list_fechaMesVenta+= "'" +row[28]+ "',";
list_prioridad+= "'" +row[97]+"\">"+row[97] + "',";
list_pais_origen+= "'" +row[31]+ "',";   
list_size_container+= "'" +row[32]+ "',";    
list_valor_usd+= "'" +row[33]+ "',";                 
list_eta_port_discharge+= "'" +row[34]+ "',";         
list_agente_aduanal+= "'" +row[35]+ "',";              
list_pedimento_a1+= "'" +row[36]+ "',";              
list_pedimento_r1_1er+= "'" +row[37]+ "',";          
list_motivo_rectificacion_1er+= "'" +row[38]+ "',";    
list_pedimento_r1_2do+= "'" +row[39]+ "',";         
list_motivo_rectificacion_2do+= "'" +row[40]+ "',";    
list_fecha_recepcion_doc+= "'" +row[41]+ "',";    
list_recinto+= "'" +row[42]+ "',";
list_naviera+= "'" +row[43]+ "',";
list_buque+= "'" +row[44]+ "',";
list_fecha_revalidacion+= "'" +row[45]+ "',";      
list_fecha_previo_origen+= "'" +row[46]+ "',";     
list_fecha_previo_destino+= "'" +row[47]+ "',";   
list_fecha_resultado_previo+= "'" +row[48]+ "',";   
list_proforma_final+= "'" +row[49]+ "',";        
list_permiso+= "'" +row[50]+ "',";               
list_fecha_envio+= "'" +row[51]+ "',";             
list_fecha_recepcion_perm+= "'" +row[52]+ "',";
list_fecha_activacion_perm+= "'" +row[53]+ "',";    
list_fecha_permisos_aut+= "'" +row[54]+ "',";     
list_co_pref_arancelaria+= "'" +row[55]+ "',";    
list_aplic_pref_arancelaria+= "'" +row[56]+ "',";  
list_req_uva+= "'" +row[57]+ "',";   
list_req_ca+= "'" +row[58]+ "',";    
list_fecha_recepcion_ca+= "'" +row[59]+ "',"; 
list_num_constancia_ca+= "'" +row[60]+ "',";   
list_monto_ca+= "'" +row[61]+ "',";
list_fecha_doc_completos+= "'" +row[62]+ "',";  
list_fecha_pago_pedimento+= "'" +row[63]+ "',";     
list_fecha_solicitud_transporte+= "'" +row[64]+ "',";
list_fecha_modulacion+= "'" +row[65]+ "',";   
list_modalidad+= "'" +row[66]+ "',";          
list_resultado_modulacion+= "'" +row[67]+ "',";    
list_fecha_reconocimiento+= "'" +row[68]+ "',";     
list_fecha_liberacion+= "'" +row[69]+ "',";       
list_sello_origen+= "'" +row[70]+ "',";            
list_sello_final+= "'" +row[71]+ "',";      
list_fecha_retencion_aut+= "'" +row[72]+ "',";     
list_fecha_liberacion_aut+= "'" +row[73]+ "',";   
list_estatus_operacion+= "'" +row[74]+ "',";      
list_motivo_atraso+= "'" +row[75]+ "',";          
list_observaciones+= "'" +row[76]+ "',"; 
list_llegada_a_nova+= "'" +row[77]+ "',";
list_llegada_a_globe_trade_sd+= "'" +row[78]+ "',";
list_archivo_m+= "'" +row[79]+ "',";
list_fecha_archivo_m+= "'" +row[80]+ "',";
list_fecha_solicit_manip+= "'" +row[81]+ "',";
list_fecha_vencim_manip+= "'" +row[82]+ "',";
list_fecha_confirm_clave_pedim+= "'" +row[83]+ "',";
list_fecha_recep_increment+= "'" +row[84]+ "',";
list_t_e+= "'" +row[85]+ "',";
list_fecha_vencim_inbound+= "'" +row[86]+ "',";
list_no_bultos+= "'" +row[87]+ "',";
list_peso_kg+= "'" +row[88]+ "',";
list_transferencia+= "'" +row[89]+ "',";
list_fecha_inicio_etiquetado+= "'" +row[90]+ "',";
list_fecha_termino_etiquetado+= "'" +row[91]+ "',";
list_hora_termino_etiquetado+= "'" +row[92]+ "',";
list_proveedor+= "'" +row[93]+ "',";
list_proveedor_carga+= "'" +row[94]+ "',";
list_fy+= "'" +row[95]+ "',";

                                                        %>

                                                    <input type="hidden" id="evento[<%=cont%>]" name="evento[<%=cont%>]" value="<%=row[0]%>">
                                                    <input type="hidden" id="loadTypeFinal[<%=cont%>]" name="loadTypeFinal[<%=cont%>]" value="<%=row[8]%>"> <!-- LoadTypeFinal -->
                                                    <input type="hidden" id="plantillaId[<%=cont%>]" name="plantillaId[<%=cont%>]" value="<%=row[17]%>"> <!-- plantillaId -->
                                                    <input type="hidden" id="shipmentId[<%=cont%>]" name="shipmentId[<%=cont%>]" value="<%=row[5]%>">
                                                    <input type="hidden" id="containerId[<%=cont%>]" name="containerId[<%=cont%>]" value="<%=row[6]%>">


                                                    <tr id="tr<%=cont%>">
                                                        <%
                                                            if (AgentType.equals("4001") || AgentType.equals("4002") || AgentType.equals("4003") || AgentType.equals("4004") || AgentType.equals("4005") || AgentType.equals("4006")) { //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF                                                    

                                                                if (row[99].equals("3")) {
                                                                    colorSemaforo = "../img/circle-green.png";
                                                                    sizeSemaforo = "100%";
                                                                } else if (row[99].equals("2")) {
                                                                    colorSemaforo = "../img/circle-yellow.png";
                                                                    sizeSemaforo = "80%";
                                                                } else if (row[99].equals("1")) {
                                                                    colorSemaforo = "../img/circle-red.png";
                                                                    sizeSemaforo = "60%";
                                                                } else {
                                                                    colorSemaforo = "../img/circle-green.png";
                                                                    sizeSemaforo = "100%";
                                                                }
                                                        %>   
                                                        <td class="font-numero">                    <!--Semaforo -->
                                                    <center><img src="<%=colorSemaforo%>" width="<%=sizeSemaforo%>"/></center>
                                                    </td>
                                                    <!-- Referencia Aduanal -->
                                                    <td  contenteditable="true" class="font-numero"  id="referenciaAA[<%=cont%>]"><%=row[30]%></td>
                                                       <td><%=row[0]%></td>
                            <!-- Responsable -->       <td class="font-numero"><%=row[1]%></td>
                            <!-- Final Destination --> <td class="font-numero"><%=row[2]%></td>
                            <!-- Brand-Division -->    <td class="font-numero"> <%=row[21]%> </td>
                            <!-- Division -->          <td class="font-numero"><%=row[4]%></td>
                            <!-- Shipment ID -->       <td class="font-numero"><%=row[5]%></td>
                            <!-- Container -->         <td class="font-numero"><%=row[6]%></td>
                            <!-- BL/AWB/PRO -->        <td class="font-numero"><%=row[7]%></td>
                            <!-- LoadType -->          <td class="font-numero"><%=row[8]%></td>
                            <!-- Quantity -->          <td class="font-numero"><%=row[9]%></td>
                            <!-- POD -->               <td class="font-numero"><%=row[19]%></td>
                      <!-- Est. Departure from POL --> <td class="font-numero"><%=row[11]%></td>
                    <!-- ETA REAL Port of Discharge --><td class="font-numero"><%=row[12]%></td>
                            <!-- Est. Eta DC -->       <td class="font-numero"><%=row[22]%></td>
                         <!-- Inbound notification --> <td class="font-numero"><%=row[14]%></td>
                            <!-- POL -->               <td class="font-numero"><%=row[20]%></td>
                            <!-- A.A. -->              <td class="font-numero"><%=row[16]%></td>
                            <!-- Fecha Mes de Venta --><td class="font-numero"><%=row[28]%></td>
                            <!-- Prioridad Si/No -->   <td class="font-numero"><%=row[97]%>                    
                                                        <select class="form-control" id="prioridad[<%=cont%>]" name="prioridad[<%=cont%>]" value="<%=row[97]%>"> 
                                                            <option value="<%=row[97]%>"><%=row[97]%></option> 
                                                            <option value="Si">SI</option> 
                                                            <option value="No">NO</option> 
                                                        </select> 
                                                    </td>                                                 
                                                    <td class="font-numero" id="pais_origen[<%=cont%>]" name="pais_origen[<%=cont%>]"><%=row[31]%></td>
                                                    <td class="font-numero"  id="size_container[<%=cont%>]" name="size_container[<%=cont%>]"><%=row[32]%></td>
                                                    <td class="font-numero" id="valor_usd[<%=cont%>]" name="valor_usd[<%=cont%>]"><%=row[33]%></td>
                                                    <td class="font-numero" onclick="historicoSemaforo('<%=row[5]%>')" id="eta_port_discharge[<%=cont%>]"><%=row[34]%></td>
                                                    <td class="font-numero"  id="agente_aduanal[<%=cont%>]">
                                                        <input class="form-control" name="agente_aduanal[<%=cont%>]" type="text" value="<%=row[35]%>" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero" id="pedimento_a1[<%=cont%>]"><%=row[36]%>
                                                        <input class="form-control"  name="pedimento_a1[<%=cont%>]" type="text" value="<%=row[36]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero" id="pedimento_r1_1er[<%=cont%>]"><%=row[37]%>
                                                        <input class="form-control"  name="pedimento_r1_1er[<%=cont%>]" type="text" value="<%=row[37]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero" id="motivo_rectificacion_1er[<%=cont%>]"><%=row[38]%>
                                                        <input class="form-control"  name="motivo_rectificacion_1er[<%=cont%>]" type="text" value="<%=row[38]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero" id="pedimento_r1_2do[<%=cont%>]"><%=row[39]%>
                                                        <input class="form-control"  name="pedimento_r1_2do[<%=cont%>]" type="text" value="<%=row[39]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero" id="motivo_rectificacion_2do[<%=cont%>]"><%=row[40]%>
                                                        <input class="form-control"  name="motivo_rectificacion_2do[<%=cont%>]" type="text" value="<%=row[40]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero" id="fecha_recepcion_doc[<%=cont%>]"><%=row[41]%>
                                                        <input class="form-control datepicker"  name="fecha_recepcion_doc[<%=cont%>]" type="text" value="<%=row[41]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero" id="recinto[<%=cont%>]"><%=row[42]%>
                                                        <input class="form-control"  name="recinto[<%=cont%>]" type="text" value="<%=row[42]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero" id="naviera[<%=cont%>]"><%=row[43]%>
                                                        <input class="form-control"  name="naviera[<%=cont%>]" type="text" value="<%=row[43]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero" id="buque[<%=cont%>]"><%=row[44]%>
                                                        <input class="form-control"  name="buque[<%=cont%>]" type="text" value="<%=row[44]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[45]%>
                                                        <input class="form-control datepicker" id="fecha_revalidacion[<%=cont%>]" name="fecha_revalidacion[<%=cont%>]" type="text" value="<%=row[45]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[46]%>
                                                        <input class="form-control datepicker" id="fecha_previo_origen[<%=cont%>]" name="fecha_previo_origen[<%=cont%>]" type="text" value="<%=row[46]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[47]%>
                                                        <input class="form-control datepicker" id="fecha_previo_destino[<%=cont%>]" name="fecha_previo_destino[<%=cont%>]" type="text" value="<%=row[47]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[48]%>
                                                        <input class="form-control datepicker" id="fecha_resultado_previo[<%=cont%>]" name="fecha_resultado_previo[<%=cont%>]" type="text" value="<%=row[48]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[49]%>
                                                        <input class="form-control datepicker" id="proforma_final[<%=cont%>]" name="proforma_final[<%=cont%>]" type="text" value="<%=row[49]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[50]%>
                                                        <select class="form-control" id="permiso[<%=cont%>]" name="permiso[<%=cont%>]"  value="<%=row[50]%>"> 
                                                            <option value="<%=row[50]%>"><%=row[50]%></option> 
                                                            <option value="Si">SI</option> 
                                                            <option value="No">NO</option> 
                                                        </select> 
                                                    </td>
                                                    <td class="font-numero"><%=row[51]%>
                                                        <input class="form-control datepicker" id="fecha_envio[<%=cont%>]" name="fecha_envio[<%=cont%>]" type="text" value="<%=row[51]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[52]%>
                                                        <input class="form-control datepicker" id="fecha_recepcion_perm[<%=cont%>]" name="fecha_recepcion_perm[<%=cont%>]" type="text" value="<%=row[52]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[53]%>
                                                        <input class="form-control datepicker" id="fecha_activacion_perm[<%=cont%>]" name="fecha_activacion_perm[<%=cont%>]" type="text" value="<%=row[53]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[54]%>
                                                        <input class="form-control datepicker" id="fecha_permisos_aut[<%=cont%>]" name="fecha_permisos_aut[<%=cont%>]" type="text" value="<%=row[54]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[55]%>
                                                        <select class="form-control" id="co_pref_arancelaria[<%=cont%>]" name="co_pref_arancelaria[<%=cont%>]" value="<%=row[55]%>"> 
                                                            <option value="<%=row[56]%>"><%=row[56]%></option> 
                                                            <option value="Si">SI</option> 
                                                            <option value="No">NO</option> 
                                                        </select> 
                                                    </td>
                                                    <td class="font-numero"><%=row[56]%>
                                                        <select class="form-control" id="aplic_pref_arancelaria[<%=cont%>]" name="aplic_pref_arancelaria[<%=cont%>]" value="<%=row[56]%>"> 
                                                            <option value="<%=row[57]%>"><%=row[57]%></option> 
                                                            <option value="Si">SI</option> 
                                                            <option value="No">NO</option> 
                                                        </select> 
                                                    </td>
                                                    <td class="font-numero"><%=row[57]%>
                                                        <select class="form-control" id="req_uva[<%=cont%>]" name="req_uva[<%=cont%>]" value="<%=row[57]%>"> 
                                                            <option value="<%=row[58]%>"><%=row[58]%></option> 
                                                            <option value="Si">SI</option> 
                                                            <option value="No">NO</option> 
                                                        </select> 
                                                    </td>
                                                    <td class="font-numero"><%=row[58]%>
                                                        <select class="form-control" id="req_ca[<%=cont%>]" name="req_ca[<%=cont%>]"  value="<%=row[58]%>"> 
                                                            <option value="<%=row[59]%>"><%=row[59]%></option> 
                                                            <option value="Si">SI</option> 
                                                            <option value="No">NO</option> 
                                                        </select> 
                                                    </td>
                                                    <td class="font-numero"><%=row[59]%>
                                                        <input class="form-control datepicker" id="fecha_recepcion_ca[<%=cont%>]" name="fecha_recepcion_ca[<%=cont%>]" type="text" value="<%=row[59]%>" autocomplete="off">
                                                    </td> 
                                                    <td class="font-numero"><%=row[60]%>
                                                        <input class="form-control" id="num_constancia_ca[<%=cont%>]" name="num_constancia_ca[<%=cont%>]" type="text" value="<%=row[60]%>" autocomplete="off">
                                                    </td> 
                                                    <td class="font-numero"><%=row[61]%>
                                                        <input class="form-control" id="monto_ca[<%=cont%>]" name="monto_ca[<%=cont%>]" type="text" value="<%=row[61]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[62]%>
                                                        <input class="form-control datepicker" id="fecha_doc_completos[<%=cont%>]" name="fecha_doc_completos[<%=cont%>]" type="text" value="<%=row[62]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[63]%>
                                                        <input class="form-control datepicker" id="fecha_pago_pedimento[<%=cont%>]" name="fecha_pago_pedimento[<%=cont%>]" type="text" value="<%=row[63]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[64]%>
                                                        <input class="form-control datepicker" id="fecha_solicitud_transporte[<%=cont%>]" name="fecha_solicitud_transporte[<%=cont%>]" type="text" value="<%=row[64]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[65]%>
                                                        <input class="form-control datepicker" id="fecha_modulacion[<%=cont%>]" name="fecha_modulacion[<%=cont%>]" type="text" value="<%=row[65]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[66]%>
                                                        <select class="form-control" id="modalidad[<%=cont%>]" name="modalidad[<%=cont%>]" value="<%=row[66]%>"> 
                                                            <option value="<%=row[66]%>"><%=row[66]%></option>   
                                                            <option value="Camión">Camión</option> 
                                                            <option value="Tren">Tren</option> 
                                                        </select> 
                                                    </td>
                                                    <td class="font-numero"><%=row[67]%>
                                                        <select class="form-control" id="resultado_modulacion[<%=cont%>]" name="resultado_modulacion[<%=cont%>]"  value="<%=row[67]%>"> 
                                                            <option value="<%=row[67]%>"><%=row[67]%></option> 
                                                            <option value="Verde">Verde</option> 
                                                            <option value="Rojo">Rojo</option> 
                                                        </select> 
                                                    </td>
                                                    <td class="font-numero"><%=row[68]%>
                                                        <input class="form-control datepicker" id="fecha_reconocimiento[<%=cont%>]" name="fecha_reconocimiento[<%=cont%>]" type="text"  value="<%=row[68]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[69]%>
                                                        <input class="form-control datepicker" id="fecha_liberacion[<%=cont%>]" name="fecha_liberacion[<%=cont%>]" type="text"  value="<%=row[69]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[70]%>
                                                        <input class="form-control" id="sello_origen[<%=cont%>]" name="sello_origen[<%=cont%>]" type="text"  value="<%=row[70]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[71]%>
                                                        <input class="form-control" id="sello_final[<%=cont%>]" name="sello_final[<%=cont%>]" type="text"  value="<%=row[71]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[72]%>
                                                        <input class="form-control datepicker" id="fecha_retencion_aut[<%=cont%>]" name="fecha_retencion_aut[<%=cont%>]" type="text" value="<%=row[72]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[73]%>
                                                        <input class="form-control datepicker" id="fecha_liberacion_aut[<%=cont%>]" name="fecha_liberacion_aut[<%=cont%>]" type="text" value="<%=row[73]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[74]%>
                                                        <select class="form-control" id="estatus_operacion[<%=cont%>]" name="estatus_operacion[<%=cont%>]"  value="<%=row[74]%>">
                                                            <option value="<%=row[98]%>"><%=row[74]%></option> 
                                                            <%=op1%>
                                                        </select>
                                                    </td>
                                                    <td class="font-numero"><%=row[75]%>
                                                        <input class="form-control" id="motivo_atraso[<%=cont%>]" name="motivo_atraso[<%=cont%>]" type="text"  value="<%=row[75]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[76]%>
                                                        <input class="form-control" id="observaciones[<%=cont%>]" name="observaciones[<%=cont%>]" type="text" value="<%=row[76]%>" autocomplete="off">
                                                    </td>
                                                    <%
                                                        }

                                                        if (AgentType.equals("4001") || AgentType.equals("4006")) { //LOGIX Y VF
                                                    %>
                                                    <td class="font-numero"><%=row[77]%>
                                                        <input class="form-control datepicker" id="llegada_a_nova[<%=cont%>]" name="llegada_a_nova[<%=cont%>]" type="text" value="<%=row[77]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[78]%>
                                                        <input class="form-control datepicker" id="llegada_a_globe_trade_sd[<%=cont%>]" name="llegada_a_globe_trade_sd[<%=cont%>]" type="text" value="<%=row[78]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[79]%>
                                                        <input class="form-control" id="archivo_m[<%=cont%>]" name="archivo_m[<%=cont%>]" type="text" value="<%=row[79]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[80]%>
                                                        <input class="form-control datepicker" id="fecha_archivo_m[<%=cont%>]" name="fecha_archivo_m[<%=cont%>]" type="text" value="<%=row[80]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[81]%>
                                                        <input class="form-control datepicker" id="fecha_solicit_manip[<%=cont%>]" name="fecha_solicit_manip[<%=cont%>]" type="text" value="<%=row[81]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[82]%>
                                                        <input class="form-control datepicker" id="fecha_vencim_manip[<%=cont%>]" name="fecha_vencim_manip[<%=cont%>]" type="text" value="<%=row[82]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[83]%>
                                                        <input class="form-control datepicker" id="fecha_confirm_clave_pedim[<%=cont%>]" name="fecha_confirm_clave_pedim[<%=cont%>]" type="text" value="<%=row[83]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[84]%>
                                                        <input class="form-control datepicker" id="fecha_recep_increment[<%=cont%>]" name="fecha_recep_increment[<%=cont%>]" type="text" value="<%=row[84]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[85]%>
                                                        <input class="form-control" id="t_e[<%=cont%>]" name="t_e[<%=cont%>]" type="text" value="<%=row[85]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[86]%>
                                                        <input class="form-control datepicker" id="fecha_vencim_inbound[<%=cont%>]" name="fecha_vencim_inbound[<%=cont%>]" type="text" value="<%=row[86]%>" autocomplete="off">
                                                    </td>
                                                    <%
                                                        }

                                                        if (AgentType.equals("4002") || AgentType.equals("4006")) {  //CUSA Y VF
                                                    %>
                                                    <td class="font-numero"><%=row[87]%>
                                                        <input class="form-control" id="no_bultos[<%=cont%>]" name="no_bultos[<%=cont%>]" type="text" value="<%=row[87]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[88]%>
                                                        <input class="form-control" id="peso_kg[<%=cont%>]" name="peso_kg[<%=cont%>]" type="text" value="<%=row[88]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[89]%>
                                                        <select class="form-control" id="transferencia[<%=cont%>]" name="transferencia[<%=cont%>]" value="<%=row[89]%>"> 
                                                            <option value="<%=row[89]%>"><%=row[89]%></option> 
                                                            <option value="Si">SI</option> 
                                                            <option value="No">NO</option> 
                                                        </select> 
                                                    </td>
                                                    <td class="font-numero"><%=row[90]%>
                                                        <input class="form-control datepicker" id="fecha_inicio_etiquetado[<%=cont%>]" name="fecha_inicio_etiquetado[<%=cont%>]" type="text" value="<%=row[90]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[91]%>
                                                        <input class="form-control datepicker" id="fecha_termino_etiquetado[<%=cont%>]" name="fecha_termino_etiquetado[<%=cont%>]" type="text" value="<%=row[91]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[92]%>
                                                        <input class="form-control" id="hora_termino_etiquetado[<%=cont%>]" name="hora_termino_etiquetado[<%=cont%>]" type="time" value="<%=row[92]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[93]%>
                                                        <input class="form-control" id="proveedor[<%=cont%>]" name="proveedor[<%=cont%>]" type="text" value="<%=row[93]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero"><%=row[94]%>
                                                        <input class="form-control" id="proveedor_carga[<%=cont%>]" name="proveedor_carga[<%=cont%>]" type="text" value="<%=row[94]%>" autocomplete="off">
                                                    </td>
                                                    <%
                                                        }
                                                    %>					
                                                    <td class="font-numero"><%=row[95]%>
                                                        <input class="form-control" id="fy[<%=cont%>]" name="fy[<%=cont%>]" type="text" value="<%=row[95]%>" autocomplete="off">
                                                    </td>
                                                    <td class="font-numero">
                                                    <center><a class="btn btn-primary text-uppercase" onclick="AddCustoms()"><i class="fa fa-save"></i></a></center>  
                                                    </td> 
                                                    </tr>
                                                    <%
                                                            cont++;
                                                        }
list_evento += " <option value='0'>Selecciona</option> ";
list_referenciaAA +=" <option value='0'>Selecciona</option> ";
list_responsable +=" <option value='0'>Selecciona</option> ";
list_finalDestination +="' '";
list_brandDivision +="' '";
list_division +="' '";
list_shipmentId +="' '";
list_containerId +="' '";
list_blAwbPro +="' '";
list_loadType +="' '";
list_quantity +="' '";
list_pod +="' '";
list_estDepartFromPol +="' '";
list_etaRealPortOfDischarge +="' '";
list_estEtaDc +="' '";
list_inboundNotification +="' '";
list_pol +="' '";
list_aa +="' '";
list_fechaMesVenta +="' '";
list_prioridad +="' '";
list_pais_origen +="' '";
list_size_container +="' '";
list_valor_usd +="' '";
list_eta_port_discharge +="' '";
list_agente_aduanal +="' '";
list_pedimento_a1 +="' '";
list_pedimento_r1_1er +="' '";
list_motivo_rectificacion_1er +="' '";
list_pedimento_r1_2do +="' '";
list_motivo_rectificacion_2do +="' '";
list_fecha_recepcion_doc +="' '";
list_recinto +="' '";
list_naviera +="' '";
list_buque +="' '";
list_fecha_revalidacion +="' '";
list_fecha_previo_origen +="' '";
list_fecha_previo_destino +="' '";
list_fecha_resultado_previo +="' '";
list_proforma_final +="' '";
list_permiso +="' '";
list_fecha_envio +="' '";
list_fecha_recepcion_perm +="' '";
list_fecha_activacion_perm +="' '";
list_fecha_permisos_aut +="' '";
list_co_pref_arancelaria +="' '";
list_aplic_pref_arancelaria +="' '";
list_req_uva +="' '";
list_req_ca +="' '";
list_fecha_recepcion_ca +="' '";
list_num_constancia_ca +="' '";
list_monto_ca +="' '";
list_fecha_doc_completos +="' '";
list_fecha_pago_pedimento +="' '";
list_fecha_solicitud_transporte +="' '";
list_fecha_modulacion +="' '";
list_modalidad +="' '";
list_resultado_modulacion +="' '";
list_fecha_reconocimiento +="' '";
list_fecha_liberacion +="' '";
list_sello_origen +="' '";
list_sello_final +="' '";
list_fecha_retencion_aut +="' '";
list_fecha_liberacion_aut +="' '";
list_estatus_operacion +="' '";
list_motivo_atraso +="' '";
list_observaciones +="' '";
list_llegada_a_nova +="' '";
list_llegada_a_globe_trade_sd +="' '";
list_archivo_m +="' '";
list_fecha_archivo_m +="' '";
list_fecha_solicit_manip +="' '";
list_fecha_vencim_manip +="' '";
list_fecha_confirm_clave_pedim +="' '";
list_fecha_recep_increment +="' '";
list_t_e +="' '";
list_fecha_vencim_inbound +="' '";
list_no_bultos +="' '";
list_peso_kg +="' '";
list_transferencia +="' '";
list_fecha_inicio_etiquetado +="' '";
list_fecha_termino_etiquetado +="' '";
list_hora_termino_etiquetado +="' '";
list_proveedor +="' '";
list_proveedor_carga +="' '";
list_fy +="' '";
                                                    %>

                                                    <%
                                                        }
                                                    %>      
                                                    </tbody>
                                                </table>
                                            </div>

                                            </div>
                                            <br>
                                            <!-- Botones controles -->

                                        </form>
                                    </div>
                                </div>              
                            </div>
                        </div>   
                    </section>
                </div>  

            </div>
        </div>    









       
        <hr><hr>

        <script>

            function mostrarFilasSelect(ban,idLLama,num) {
                var select = document.getElementById(idLLama);
                var selectedOptions = [];

                for (var i = 0; i < select.options.length; i++) {
                    if (select.options[i].selected) {
                        selectedOptions.push(select.options[i].value);
                    }
                }

                console.log(selectedOptions);
                if (ban == true) {
                    console.log(selectedOptions);
                    mostrarFilas(selectedOptions,num);
                }
                // mostrarFilas(['Pedro', 'Carlos'])
            }
            function mostrarFilas(nombresMostrar,num) {
                console.log('1');
                console.log(nombresMostrar);
                var table = document.getElementById("tables");
                var rows = table.getElementsByTagName("tr");

                //for (var i = 1; i < rows.length; i++) {
                  for (var i = rows.length - 1; i >= 0; i--) {
                      try {
                          var nombreFila = rows[i].getElementsByTagName("td")[num].textContent;
                          } catch (error) {
                                // Manejo de errores
                                console.error("Se produjo un error:", error.message);
                            }
                   // var cells = rows[i].getElementsByTagName("td");
                   // var nombre = cells[num].textContent; // La columna de Nombre es la primera (índice 0)

                   // if (nombresMostrar.includes(nombre)) {
                    if (nombresMostrar.includes(nombreFila)) {
                        rows[i].style.display = "table-row";
                    } else {
                         rows[i].remove();
                        //rows[i].style.display = "none";
                    }
                }
            }

            function mostrarTodasLasFilas() {
                var table = document.getElementById("tables");
                var rows = table.getElementsByTagName("tr");

                for (var i = 0; i < rows.length; i++) {
                    rows[i].style.display = "table-row";
                }
            }
        </script>

            <script type="text/javascript">



            function select() {
                var arrayConDuplicados = [<%=list_finalDestination%>];
                var arrayList_referenciaAA=[<%=list_finalDestination%>];
var arrayList_responsable=[<%=list_finalDestination%>];
var arrayList_finalDestination=[<%=list_finalDestination%>];
var arrayList_brandDivision=[<%=list_brandDivision%>];
var arrayList_division=[<%=list_division%>];
var arrayList_shipmentId=[<%=list_shipmentId%>];
var arrayList_containerId=[<%=list_containerId%>];
var arrayList_blAwbPro=[<%=list_blAwbPro%>];
var arrayList_loadType=[<%=list_loadType%>];
var arrayList_quantity=[<%=list_quantity%>];
var arrayList_pod=[<%=list_pod%>];
var arrayList_estDepartFromPol=[<%=list_estDepartFromPol%>];
var arrayList_etaRealPortOfDischarge=[<%=list_etaRealPortOfDischarge%>];
var arrayList_estEtaDc=[<%=list_estEtaDc%>];
var arrayList_inboundNotification=[<%=list_inboundNotification%>];
var arrayList_pol=[<%=list_pol%>];
var arrayList_aa=[<%=list_aa%>];
var arrayList_fechaMesVenta=[<%=list_fechaMesVenta%>];
var arrayList_prioridad=[<%=list_prioridad%>];
var arrayList_pais_origen=[<%=list_pais_origen%>];
var arrayList_size_container=[<%=list_size_container%>];
var arrayList_valor_usd=[<%=list_valor_usd%>];
var arrayList_eta_port_discharge=[<%=list_eta_port_discharge%>];
var arrayList_agente_aduanal=[<%=list_agente_aduanal%>];
var arrayList_pedimento_a1=[<%=list_pedimento_a1%>];
var arrayList_pedimento_r1_1er=[<%=list_pedimento_r1_1er%>];
var arrayList_motivo_rectificacion_1er=[<%=list_motivo_rectificacion_1er%>];
var arrayList_pedimento_r1_2do=[<%=list_pedimento_r1_2do%>];
var arrayList_motivo_rectificacion_2do=[<%=list_motivo_rectificacion_2do%>];
var arrayList_fecha_recepcion_doc=[<%=list_fecha_recepcion_doc%>];
var arrayList_recinto=[<%=list_recinto%>];
var arrayList_naviera=[<%=list_naviera%>];
var arrayList_buque=[<%=list_buque%>];
var arrayList_fecha_revalidacion=[<%=list_fecha_revalidacion%>];
var arrayList_fecha_previo_origen=[<%=list_fecha_previo_origen%>];
var arrayList_fecha_previo_destino=[<%=list_fecha_previo_destino%>];
var arrayList_fecha_resultado_previo=[<%=list_fecha_resultado_previo%>];
var arrayList_proforma_final=[<%=list_proforma_final%>];
var arrayList_permiso=[<%=list_permiso%>];
var arrayList_fecha_envio=[<%=list_fecha_envio%>];
var arrayList_fecha_recepcion_perm=[<%=list_fecha_recepcion_perm%>];
var arrayList_fecha_activacion_perm=[<%=list_fecha_activacion_perm%>];
var arrayList_fecha_permisos_aut=[<%=list_fecha_permisos_aut%>];
var arrayList_co_pref_arancelaria=[<%=list_co_pref_arancelaria%>];
var arrayList_aplic_pref_arancelaria=[<%=list_aplic_pref_arancelaria%>];
var arrayList_req_uva=[<%=list_req_uva%>];
var arrayList_req_ca=[<%=list_req_ca%>];
var arrayList_fecha_recepcion_ca=[<%=list_fecha_recepcion_ca%>];
var arrayList_num_constancia_ca=[<%=list_num_constancia_ca%>];
var arrayList_monto_ca=[<%=list_monto_ca%>];
var arrayList_fecha_doc_completos=[<%=list_fecha_doc_completos%>];
var arrayList_fecha_pago_pedimento=[<%=list_fecha_pago_pedimento%>];
var arrayList_fecha_solicitud_transporte=[<%=list_fecha_solicitud_transporte%>];
var arrayList_fecha_modulacion=[<%=list_fecha_modulacion%>];
var arrayList_modalidad=[<%=list_modalidad%>];
var arrayList_resultado_modulacion=[<%=list_resultado_modulacion%>];
var arrayList_fecha_reconocimiento=[<%=list_fecha_reconocimiento%>];
var arrayList_fecha_liberacion=[<%=list_fecha_liberacion%>];
var arrayList_sello_origen=[<%=list_sello_origen%>];
var arrayList_sello_final=[<%=list_sello_final%>];
var arrayList_fecha_retencion_aut=[<%=list_fecha_retencion_aut%>];
var arrayList_fecha_liberacion_aut=[<%=list_fecha_liberacion_aut%>];
var arrayList_estatus_operacion=[<%=list_estatus_operacion%>];
var arrayList_motivo_atraso=[<%=list_motivo_atraso%>];
var arrayList_observaciones=[<%=list_observaciones%>];
var arrayList_llegada_a_nova=[<%=list_llegada_a_nova%>];
var arrayList_llegada_a_globe_trade_sd=[<%=list_llegada_a_globe_trade_sd%>];
var arrayList_archivo_m=[<%=list_archivo_m%>];
var arrayList_fecha_archivo_m=[<%=list_fecha_archivo_m%>];
var arrayList_fecha_solicit_manip=[<%=list_fecha_solicit_manip%>];
var arrayList_fecha_vencim_manip=[<%=list_fecha_vencim_manip%>];
var arrayList_fecha_confirm_clave_pedim=[<%=list_fecha_confirm_clave_pedim%>];
var arrayList_fecha_recep_increment=[<%=list_fecha_recep_increment%>];
var arrayList_t_e=[<%=list_t_e%>];
var arrayList_fecha_vencim_inbound=[<%=list_fecha_vencim_inbound%>];
var arrayList_no_bultos=[<%=list_no_bultos%>];
var arrayList_peso_kg=[<%=list_peso_kg%>];
var arrayList_transferencia=[<%=list_transferencia%>];
var arrayList_fecha_inicio_etiquetado=[<%=list_fecha_inicio_etiquetado%>];
var arrayList_fecha_termino_etiquetado=[<%=list_fecha_termino_etiquetado%>];
var arrayList_hora_termino_etiquetado=[<%=list_hora_termino_etiquetado%>];
var arrayList_proveedor=[<%=list_proveedor%>];
var arrayList_proveedor_carga=[<%=list_proveedor_carga%>];
var arrayList_fy=[<%=list_fy%>];

                var opciones = [...new Set(arrayConDuplicados)];
                var opciones1 = [...new Set( arrayList_referenciaAA)];
var opciones2 = [...new Set( arrayList_responsable)];
var opciones3 = [...new Set( arrayList_finalDestination)];
var opciones4 = [...new Set( arrayList_brandDivision)];
var opciones5 = [...new Set( arrayList_division)];
var opciones6 = [...new Set( arrayList_shipmentId)];
var opciones7 = [...new Set( arrayList_containerId)];
var opciones8 = [...new Set( arrayList_blAwbPro)];
var opciones9 = [...new Set( arrayList_loadType)];
var opciones10 = [...new Set( arrayList_quantity)];
var opciones11 = [...new Set( arrayList_pod)];
var opciones12 = [...new Set( arrayList_estDepartFromPol)];
var opciones13 = [...new Set( arrayList_etaRealPortOfDischarge)];
var opciones14 = [...new Set( arrayList_estEtaDc)];
var opciones15 = [...new Set( arrayList_inboundNotification)];
var opciones16 = [...new Set( arrayList_pol)];
var opciones17 = [...new Set( arrayList_aa)];
var opciones18 = [...new Set( arrayList_fechaMesVenta)];
var opciones19 = [...new Set( arrayList_prioridad)];
var opciones20 = [...new Set( arrayList_pais_origen)];
var opciones21 = [...new Set( arrayList_size_container)];
var opciones22 = [...new Set( arrayList_valor_usd)];
var opciones23 = [...new Set( arrayList_eta_port_discharge)];
var opciones24 = [...new Set( arrayList_agente_aduanal)];
var opciones25 = [...new Set( arrayList_pedimento_a1)];
var opciones26 = [...new Set( arrayList_pedimento_r1_1er)];
var opciones27 = [...new Set( arrayList_motivo_rectificacion_1er)];
var opciones28 = [...new Set( arrayList_pedimento_r1_2do)];
var opciones29 = [...new Set( arrayList_motivo_rectificacion_2do)];
var opciones30 = [...new Set( arrayList_fecha_recepcion_doc)];
var opciones31 = [...new Set( arrayList_recinto)];
var opciones32 = [...new Set( arrayList_naviera)];
var opciones33 = [...new Set( arrayList_buque)];
var opciones34 = [...new Set( arrayList_fecha_revalidacion)];
var opciones35 = [...new Set( arrayList_fecha_previo_origen)];
var opciones36 = [...new Set( arrayList_fecha_previo_destino)];
var opciones37 = [...new Set( arrayList_fecha_resultado_previo)];
var opciones38 = [...new Set( arrayList_proforma_final)];
var opciones39 = [...new Set( arrayList_permiso)];
var opciones40 = [...new Set( arrayList_fecha_envio)];
var opciones41 = [...new Set( arrayList_fecha_recepcion_perm)];
var opciones42 = [...new Set( arrayList_fecha_activacion_perm)];
var opciones43 = [...new Set( arrayList_fecha_permisos_aut)];
var opciones44 = [...new Set( arrayList_co_pref_arancelaria)];
var opciones45 = [...new Set( arrayList_aplic_pref_arancelaria)];
var opciones46 = [...new Set( arrayList_req_uva)];
var opciones47 = [...new Set( arrayList_req_ca)];
var opciones48 = [...new Set( arrayList_fecha_recepcion_ca)];
var opciones49 = [...new Set( arrayList_num_constancia_ca)];
var opciones50 = [...new Set( arrayList_monto_ca)];
var opciones51 = [...new Set( arrayList_fecha_doc_completos)];
var opciones52 = [...new Set( arrayList_fecha_pago_pedimento)];
var opciones53 = [...new Set( arrayList_fecha_solicitud_transporte)];
var opciones54 = [...new Set( arrayList_fecha_modulacion)];
var opciones55 = [...new Set( arrayList_modalidad)];
var opciones56 = [...new Set( arrayList_resultado_modulacion)];
var opciones57 = [...new Set( arrayList_fecha_reconocimiento)];
var opciones58 = [...new Set( arrayList_fecha_liberacion)];
var opciones59 = [...new Set( arrayList_sello_origen)];
var opciones60 = [...new Set( arrayList_sello_final)];
var opciones61 = [...new Set( arrayList_fecha_retencion_aut)];
var opciones62 = [...new Set( arrayList_fecha_liberacion_aut)];
var opciones63 = [...new Set( arrayList_estatus_operacion)];
var opciones64 = [...new Set( arrayList_motivo_atraso)];
var opciones65 = [...new Set( arrayList_observaciones)];
var opciones66 = [...new Set( arrayList_llegada_a_nova)];
var opciones67 = [...new Set( arrayList_llegada_a_globe_trade_sd)];
var opciones68 = [...new Set( arrayList_archivo_m)];
var opciones69 = [...new Set( arrayList_fecha_archivo_m)];
var opciones70 = [...new Set( arrayList_fecha_solicit_manip)];
var opciones71 = [...new Set( arrayList_fecha_vencim_manip)];
var opciones72 = [...new Set( arrayList_fecha_confirm_clave_pedim)];
var opciones73 = [...new Set( arrayList_fecha_recep_increment)];
var opciones74 = [...new Set( arrayList_t_e)];
var opciones75 = [...new Set( arrayList_fecha_vencim_inbound)];
var opciones76 = [...new Set( arrayList_no_bultos)];
var opciones77 = [...new Set( arrayList_peso_kg)];
var opciones78 = [...new Set( arrayList_transferencia)];
var opciones79 = [...new Set( arrayList_fecha_inicio_etiquetado)];
var opciones80 = [...new Set( arrayList_fecha_termino_etiquetado)];
var opciones81 = [...new Set( arrayList_hora_termino_etiquetado)];
var opciones82 = [...new Set( arrayList_proveedor)];
var opciones83 = [...new Set( arrayList_proveedor_carga)];
var opciones84 = [...new Set( arrayList_fy)];

opciones.sort();
opciones1.sort();
opciones2.sort();
opciones3.sort();
opciones4.sort();
opciones5.sort();
opciones6.sort();
opciones7.sort();
opciones8.sort();
opciones9.sort();
opciones10.sort();
opciones11.sort();
opciones12.sort();
opciones13.sort();
opciones14.sort();
opciones15.sort();
opciones16.sort();
opciones17.sort();
opciones18.sort();
opciones19.sort();
opciones20.sort();
opciones21.sort();
opciones22.sort();
opciones23.sort();
opciones24.sort();
opciones25.sort();
opciones26.sort();
opciones27.sort();
opciones28.sort();
opciones29.sort();
opciones30.sort();
opciones31.sort();
opciones32.sort();
opciones33.sort();
opciones34.sort();
opciones35.sort();
opciones36.sort();
opciones37.sort();
opciones38.sort();
opciones39.sort();
opciones40.sort();
opciones41.sort();
opciones42.sort();
opciones43.sort();
opciones44.sort();
opciones45.sort();
opciones46.sort();
opciones47.sort();
opciones48.sort();
opciones49.sort();
opciones50.sort();
opciones51.sort();
opciones52.sort();
opciones53.sort();
opciones54.sort();
opciones55.sort();
opciones56.sort();
opciones57.sort();
opciones58.sort();
opciones59.sort();
opciones60.sort();
opciones61.sort();
opciones62.sort();
opciones63.sort();
opciones64.sort();
opciones65.sort();
opciones66.sort();
opciones67.sort();
opciones68.sort();
opciones69.sort();
opciones70.sort();
opciones71.sort();
opciones72.sort();
opciones73.sort();
opciones74.sort();
opciones75.sort();
opciones76.sort();
opciones77.sort();
opciones78.sort();
opciones79.sort();
opciones80.sort();
opciones81.sort();
opciones82.sort();
opciones83.sort();
opciones84.sort();

                console.log(opciones); // [1, 2, 3, 4, 5]

                // Obtén una referencia al elemento select en el DOM   arrayList_referenciaAA
                var selectElement = document.getElementById("col_llegada_a_nova");
                var selectElement1 = document.getElementById("arrayList_referenciaAA");
                // Recorre el array y crea opciones para cada elemento en el array
                                 
                selectElement.innerHTML="<%=list_evento%>";
                selectElement1.innerHTML="<%=list_responsable%>";
              
                

            }

            select();
            // Optional



        </script>               
        <!-- Conexión estatus red -->                    


        <!-- JavaScript files-->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Sweetalert -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
        <!-- Actions js -->
        <script src="../lib/validationsInbound/customs/customsForms.js" type="text/javascript"></script>
        <!-- fruitsSelect value -->
        <script src="../lib/validationsInbound/customs/fruitsSelect.js" type="text/javascript"></script>
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">


      
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
    </body>
    <%
        } catch (NullPointerException e) {
            System.out.println("e" + e);
            // out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            // out.println("<script>window.close();</script>");
        } catch (Exception e) {
            out.println("Excepcion revise por favor! " + e);
            e.printStackTrace();
        }
    %>
</html>





