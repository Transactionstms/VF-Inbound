<%-- 
    Document   : iframeDataCustom
    Created on : 2/06/2023, 01:43:04 AM
    Author     : luis_
--%>

<%@page import="com.pantalla.BLPantalla"%>
<%@page import="com.pantalla.Pantalla"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.onest.train.consultas.ConsultasQuery"%>
<%@page import="com.onest.security.menu.*" %>
<%@page import="com.onest.net.*" %>
<%@page import="com.onest.oracle.*" %>
<%@page import="com.onest.misc.*" %>
<%@page import="com.usuario.Usuario"%>
<%@page contentType="text/html;charset=iso-8859-1"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <!--<link rel="icon" type="image/png" sizes="16x16" href="../plugins/images/favicon.webp">-->
        <title>Personalizar Customs</title>
        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
        <!-- Table css -->
        <link href="../lib/validationsInbound/customs/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- jquery 3.6.0 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!-- Multiselect -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- calendarios -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.css">
        <script src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.js"></script>
        <!-- Window load -->
        <!--<link href="../lib/Loader/css/windowsLoad.css" rel="stylesheet" type="text/css"/>-->
        <script>            
            document.addEventListener("readystatechange", async function () {

                if (document.readyState === "loading") {
                    await mostrarLoader();
                    document.getElementById("loaderMsg").innerHTML = "loading";
                }
                
                if (document.readyState === "interactive") {
                    document.getElementById("loaderMsg").innerHTML = "interactive";
                }
                
                if (document.readyState === "complete") {
                    await ocultarLoader();
                    document.getElementById("loaderMsg").innerHTML = "complete";
                }
                
            });
            
            // estado actual
            console.log(document.readyState);

            //imprimir los cambios de estado
            document.addEventListener('readystatechange', () => console.log(document.readyState));
        </script>
    </head>
    <body>
        <div id="loader-wrapper">
            <div id="loader"></div>
            <div class="loaderMsg" id="loaderMsg"></div>
        </div>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String idBodega = ownsession.getAttribute("cbbodegaId").toString();
                String UserId = (String) ownsession.getAttribute("login.user_id_number");

                ConsultasQuery fac = new ConsultasQuery();
                
                //Parametros Generales
                String filterType = request.getParameter("filterType");       //Inicializar con 0
                String id = request.getParameter("id");                  //Inicializar con 0
                String[] arrOfStr = id.split(",");
                String idDivision = "20";
                String AgentType = ""; 
                String idPlantilla = "";  /*Gral (26) | Logix(24) | Cusa(25)*/
                String namePlantilla = "";
                String caramelo = "";
                String colorSemaforo = "";
                String sizeSemaforo = "";
                String listStatusOperationEvent = "";
                String blockedDate = "";
                int cont = 1; 
   
                //Objetos Multiselect:
                HashSet<String> list_evento = new HashSet<String>();
                HashSet<String> list_referenciaAA = new HashSet<String>();
                HashSet<String> list_responsable = new HashSet<String>();
                HashSet<String> list_finalDestination = new HashSet<String>();
                HashSet<String> list_brandDivision = new HashSet<String>();
                HashSet<String> list_division = new HashSet<String>();
                HashSet<String> list_shipmentId = new HashSet<String>();
                HashSet<String> list_containerId = new HashSet<String>();
                HashSet<String> list_blAwbPro = new HashSet<String>();
                HashSet<String> list_loadType = new HashSet<String>();
                HashSet<String> list_quantity = new HashSet<String>();
                HashSet<String> list_pod = new HashSet<String>();
                HashSet<String> list_estDepartFromPol = new HashSet<String>();
                HashSet<String> list_etaRealPortOfDischarge = new HashSet<String>();
                HashSet<String> list_estEtaDc = new HashSet<String>();
                HashSet<String> list_inboundNotification = new HashSet<String>();
                HashSet<String> list_pol = new HashSet<String>();
                HashSet<String> list_aa = new HashSet<String>();
                HashSet<String> list_fechaMesVenta = new HashSet<String>();
                HashSet<String> list_prioridad = new HashSet<String>();
                HashSet<String> list_pais_origen= new HashSet<String>();
                HashSet<String> list_size_container = new HashSet<String>();    
                HashSet<String> list_valor_usd = new HashSet<String>();                 
                HashSet<String> list_eta_port_discharge = new HashSet<String>();         
                HashSet<String> list_agente_aduanal = new HashSet<String>();              
                HashSet<String> list_pedimento_a1 = new HashSet<String>();              
                HashSet<String> list_pedimento_r1_1er = new HashSet<String>();          
                HashSet<String> list_motivo_rectificacion_1er = new HashSet<String>();    
                HashSet<String> list_pedimento_r1_2do = new HashSet<String>();         
                HashSet<String> list_motivo_rectificacion_2do = new HashSet<String>();    
                HashSet<String> list_fecha_recepcion_doc = new HashSet<String>();    
                HashSet<String> list_recinto = new HashSet<String>();
                HashSet<String> list_naviera = new HashSet<String>();
                HashSet<String> list_buque = new HashSet<String>();
                HashSet<String> list_fecha_revalidacion = new HashSet<String>();      
                HashSet<String> list_fecha_previo_origen = new HashSet<String>();     
                HashSet<String> list_fecha_previo_destino = new HashSet<String>();   
                HashSet<String> list_fecha_resultado_previo = new HashSet<String>();   
                HashSet<String> list_proforma_final = new HashSet<String>();        
                HashSet<String> list_permiso = new HashSet<String>();               
                HashSet<String> list_fecha_envio = new HashSet<String>();             
                HashSet<String> list_fecha_recepcion_perm = new HashSet<String>();
                HashSet<String> list_fecha_activacion_perm = new HashSet<String>();   
                HashSet<String> list_fecha_permisos_aut = new HashSet<String>();     
                HashSet<String> list_co_pref_arancelaria = new HashSet<String>();    
                HashSet<String> list_aplic_pref_arancelaria = new HashSet<String>();  
                HashSet<String> list_req_uva = new HashSet<String>();  
                HashSet<String> list_req_ca = new HashSet<String>();    
                HashSet<String> list_fecha_recepcion_ca = new HashSet<String>(); 
                HashSet<String> list_num_constancia_ca = new HashSet<String>();   
                HashSet<String> list_monto_ca = new HashSet<String>();
                HashSet<String> list_fecha_doc_completos = new HashSet<String>();  
                HashSet<String> list_fecha_pago_pedimento = new HashSet<String>();     
                HashSet<String> list_fecha_solicitud_transporte = new HashSet<String>();
                HashSet<String> list_fecha_modulacion = new HashSet<String>();   
                HashSet<String> list_modalidad = new HashSet<String>();          
                HashSet<String> list_resultado_modulacion = new HashSet<String>();    
                HashSet<String> list_fecha_reconocimiento = new HashSet<String>();     
                HashSet<String> list_fecha_liberacion = new HashSet<String>();       
                HashSet<String> list_sello_origen = new HashSet<String>();            
                HashSet<String> list_sello_final = new HashSet<String>();      
                HashSet<String> list_fecha_retencion_aut = new HashSet<String>();     
                HashSet<String> list_fecha_liberacion_aut = new HashSet<String>();   
                HashSet<String> list_estatus_operacion = new HashSet<String>();      
                HashSet<String> list_motivo_atraso = new HashSet<String>();          
                HashSet<String> list_observaciones = new HashSet<String>(); 
                HashSet<String> list_llegada_a_nova = new HashSet<String>();
                HashSet<String> list_llegada_a_globe_trade_sd = new HashSet<String>();
                HashSet<String> list_archivo_m = new HashSet<String>();
                HashSet<String> list_fecha_archivo_m = new HashSet<String>();
                HashSet<String> list_fecha_solicit_manip = new HashSet<String>();
                HashSet<String> list_fecha_vencim_manip = new HashSet<String>();
                HashSet<String> list_fecha_confirm_clave_pedim = new HashSet<String>();
                HashSet<String> list_fecha_recep_increment = new HashSet<String>();
                HashSet<String> list_t_e = new HashSet<String>();
                HashSet<String> list_fecha_vencim_inbound = new HashSet<String>();  
                HashSet<String> list_no_bultos = new HashSet<String>();
                HashSet<String> list_peso_kg = new HashSet<String>();
                HashSet<String> list_transferencia = new HashSet<String>();
                HashSet<String> list_fecha_inicio_etiquetado = new HashSet<String>();
                HashSet<String> list_fecha_termino_etiquetado = new HashSet<String>();
                HashSet<String> list_hora_termino_etiquetado = new HashSet<String>();
                HashSet<String> list_proveedor = new HashSet<String>();
                HashSet<String> list_proveedor_carga = new HashSet<String>();     
                HashSet<String> list_fy = new HashSet<String>();
                
                /*HashSet<String> list_evento = new HashSet<>(list_evento1);
                list_evento.remove(null);*/ /*PENDIENTE*/
                
                //Obtener el agente aduanal, id plantilla y nombre plantilla del usuario: 
                if (db.doDB(fac.consultarAgenteAduanalCustoms(UserId))) {
                    for (String[] rowA : db.getResultado()) {
                        AgentType = rowA[0]; 
                        idPlantilla = rowA[1];
                        namePlantilla = rowA[2];
                    }
                }
                
                //Generar caramelo: Opciones del multiselect
                for (String a : arrOfStr) {
                    caramelo += "'" + a + "',";
                }
                caramelo = caramelo.replaceAll(",$", "");
                
               /*Columna: Estatus Operación (listado)*/
                if (db.doDB(fac.consultarEstatusOperacionCustoms())) {
                    for (String[] rowO : db.getResultado()) {
                        listStatusOperationEvent += "<option value=\"" + rowO[0] + "\">" + rowO[1] + "</option>"; 
                    }
                }

        %>
        <div class="card-body">
            <div class="contenedor">
                <div class="columna1"><input class="form-control" type="file" id="input-id" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"></div>
                <div class="columna2"><button type="button" class="btn btn-primary" id="upload_file" onclick="logExcel()"><i class="fa fa-upload"></i></button></div>
                <div class="columna2"><button type="button" class="btn btn-primary" id="created_file" onclick="logExcel()"><i class="fa fa-download"></i></button></div>
                <div class="columna4"><!--<label class="txtColor">Resolución de Pantalla</label>--></div>
                <div class="columna5"><label><font class="redtext1">Busqueda:&nbsp; <input id="searchTerm" type="text" onkeyup="this.value = this.value.toUpperCase()" onkeyup="doSearch()" style="text-transform:uppercase;" data-mobile-responsive="true"/>&nbsp;&nbsp;<a class="btn btn-primary text-uppercase" onclick="AddPullCustoms()"><i class="fa fa-save"></i></a></label></div>
            </div> 
            <div class="scroll-container" id="divAMostrarOcultar">
                <div align="center" id="divResultado" name="divResultado"><%=namePlantilla%></div>
            </div>
            <div id="table-scroll" class="table-scroll">
                <table id="main-table" class="main-table" style="table-layout:fixed; width:1500%;">
                    <thead>
                        <tr>
                <%            
                    if(AgentType.equals("4001")||AgentType.equals("4002")||AgentType.equals("4003")||AgentType.equals("4004")||AgentType.equals("4005")||AgentType.equals("4006")){ //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF   
                %>                 
                            <th class="col-sm-1" style="background-color:#FFFFFF;"></th>
                            <th class="col-sm-4" style="background-color:#333F4F;">Referencia AA&nbsp;<a onclick="FiltrerData('1')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_referenciaAA" name="col_referenciaAA"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Evento <strong style="color:red">*</strong>&nbsp;<a onclick="FiltrerData('2')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_evento" name="col_evento"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Responsable&nbsp;<a onclick="FiltrerData('3')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_responsable" name="col_responsable"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Final Destination&nbsp;<a onclick="FiltrerData('4')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_finalDestination" name="col_finalDestination"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Brand-Division&nbsp;<a onclick="FiltrerData('5')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_brandDivision" name="col_brandDivision"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Division&nbsp;<a onclick="FiltrerData('6')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_division" name="col_division"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Shipment ID&nbsp;<a onclick="FiltrerData('7')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_shipmentId" name="col_shipmentId"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Container&nbsp;<a onclick="FiltrerData('8')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_container" name="col_container"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">BL/AWB/PRO&nbsp;<a onclick="FiltrerData('9')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_blAwbPro" name="col_blAwbPro"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">LoadType&nbsp;<a onclick="FiltrerData('10')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_loadType" name="col_loadType"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Quantity&nbsp;<a onclick="FiltrerData('11')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_quantity" name="col_quantity"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">POD&nbsp;<a onclick="FiltrerData('12')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_pod" name="col_pod"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Est. Departure from POL&nbsp;<a onclick="FiltrerData('13')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_estDepartFromPol" name="col_estDepartFromPol"></select></th>
                            <th class="col-sm-5" style="background-color:#1C84C6;">ETA REAL Port of Discharge&nbsp;<a onclick="FiltrerData('14')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_etaRealPortOfDischarge" name="col_etaRealPortOfDischarge"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Est. Eta DC&nbsp;<a onclick="FiltrerData('15')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_estEtaDc" name="col_estEtaDc"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Inbound notification&nbsp;<a onclick="FiltrerData('16')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_inboundNotification" name="col_inboundNotification"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">POL&nbsp;<a onclick="FiltrerData('17')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_pol" name="col_pol"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">A.A.&nbsp;<a onclick="FiltrerData('18')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_aa" name="col_aa"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Fecha Mes de Venta&nbsp;<a onclick="FiltrerData('19')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fechaMesVenta" name="col_fechaMesVenta"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Prioridad Si/No&nbsp;<a onclick="FiltrerData('20')"><i class="fa fa-search"></i></a> &nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_prioridad" name="col_prioridad"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">País Origen&nbsp;<a onclick="FiltrerData('21')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_pais_origen" name="col_pais_origen"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Size Container&nbsp;<a onclick="FiltrerData('22')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_size_container" name="col_size_container"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Valor USD&nbsp;<a onclick="FiltrerData('23')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_valor_usd" name="col_valor_usd"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">ETA Port Of Discharge&nbsp;<a onclick="FiltrerData('24')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_eta_port_discharge" name="col_eta_port_discharge"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Agente Aduanal&nbsp;<a onclick="FiltrerData('25')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_agente_aduanal" name="col_agente_aduanal"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Pedimento A1&nbsp;<a onclick="FiltrerData('26')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_pedimento_a1" name="col_pedimento_a1"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Pedimento R1&nbsp;<a onclick="FiltrerData('27')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_pedimento_r1_1er" name="col_pedimento_r1_1er"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Motivo rectificación 1&nbsp;<a onclick="FiltrerData('28')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_motivo_rectificacion_1er" name="col_motivo_rectificacion_1er"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Pedimento R1 (2do)&nbsp;<a onclick="FiltrerData('29')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_pedimento_r1_2do" name="col_pedimento_r1_2do"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Motivo rectificación 2&nbsp;<a onclick="FiltrerData('30')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_motivo_rectificacion_2do" name="col_motivo_rectificacion_2do"></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fecha Recepción Documentos&nbsp;<a onclick="FiltrerData('31')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_recepcion_doc" name="col_fecha_recepcion_doc"></select></th>
                            <th class="col-sm-4" style="background-color:#e04141;">Recinto&nbsp;<a onclick="FiltrerData('32')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_recinto" name="col_recinto"></select></th>
                            <th class="col-sm-4" style="background-color:#e04141;">Naviera / Forwarder&nbsp;<a onclick="FiltrerData('33')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_naviera" name="col_naviera"></select></th>
                            <th class="col-sm-4" style="background-color:#e04141;">Buque&nbsp;<a onclick="FiltrerData('34')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_buque" name="col_buque"></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fecha Revalidación/Liberación de BL&nbsp;<a onclick="FiltrerData('35')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_revalidacion" name="col_fecha_revalidacion"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Fecha Previo Origen&nbsp;<a onclick="FiltrerData('36')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_previo_origen" name="col_fecha_previo_origen"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Fecha Previo en destino&nbsp;<a onclick="FiltrerData('37')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_previo_destino" name="col_fecha_previo_destino"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Fecha Resultado Previo&nbsp;<a onclick="FiltrerData('38')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_resultado_previo" name="col_fecha_resultado_previo"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Proforma Final&nbsp;<a onclick="FiltrerData('39')"><i class="fa fa-search"></i></a> &nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_proforma_final" name="col_proforma_final"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Requiere permiso&nbsp;<a onclick="FiltrerData('40')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_permiso" name="col_permiso"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Fecha envío Fichas/notas&nbsp;<a onclick="FiltrerData('41')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_envio" name="col_fecha_envio"></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fec. Recepción de permisos tramit.&nbsp;<a onclick="FiltrerData('42')"><i class="fa fa-search"></i></a> &nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_recepcion_perm" name="col_fecha_recepcion_perm"></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fec. Act Permisos (Inic Vigencia)&nbsp;<a onclick="FiltrerData('43')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_activacion_perm" name="col_fecha_activacion_perm"></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fec. Perm. Aut. (Fin de Vigencia)&nbsp;<a onclick="FiltrerData('44')"><i class="fa fa-search"></i></a> &nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_permisos_aut" name="col_fecha_permisos_aut"></select></th>
                            <th class="col-sm-6" style="background-color:#CC9D77;">Cuenta con CO para aplicar preferencia Arancelaria&nbsp;<a onclick="FiltrerData('45')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_co_pref_arancelaria" name="col_co_pref_arancelaria"></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Aplico Preferencia Arancelaria&nbsp;<a onclick="FiltrerData('46')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_aplic_pref_arancelaria" name="col_aplic_pref_arancelaria"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Requiere UVA&nbsp;<a onclick="FiltrerData('47')"><i class="fa fa-search"></i></a> &nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_req_uva" name="col_req_uva"></select></th>
                            <th class="col-sm-4" style="background-color:#a6a2a2;">Requiere CA&nbsp;<a onclick="FiltrerData('48')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_req_ca" name="col_req_ca"></select></th>
                            <th class="col-sm-4" style="background-color:#a6a2a2;">Fecha Recepción CA&nbsp;<a onclick="FiltrerData('49')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_recepcion_ca" name="col_fecha_recepcion_ca"></select></th>
                            <th class="col-sm-4" style="background-color:#a6a2a2;">Número de Constancia CA&nbsp;<a onclick="FiltrerData('50')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_num_constancia_ca" name="col_num_constancia_ca"></select></th>
                            <th class="col-sm-4" style="background-color:#a6a2a2;">Monto CA&nbsp;<a onclick="FiltrerData('51')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_monto_ca" name="col_monto_ca"></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fecha Documentos Completos&nbsp;<a onclick="FiltrerData('52')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_doc_completos" name="col_fecha_doc_completos"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Fecha Pago Pedimento&nbsp;<a onclick="FiltrerData('53')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_pago_pedimento" name="col_fecha_pago_pedimento"></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fecha Solicitud de transporte&nbsp;<a onclick="FiltrerData('54')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_solicitud_transporte" name="col_fecha_solicitud_transporte"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Fecha Modulacion&nbsp;<a onclick="FiltrerData('55')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_modulacion" name="col_fecha_modulacion"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Modalidad&nbsp;<a onclick="FiltrerData('56')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_modalidad" name="col_modalidad"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Resultado Modulacion&nbsp;<a onclick="FiltrerData('57')"><i class="fa fa-search"></i></a> &nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_resultado_modulacion" name="col_resultado_modulacion"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Fecha Reconocimiento&nbsp;<a onclick="FiltrerData('58')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_reconocimiento" name="col_fecha_reconocimiento"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Fecha Liberacion&nbsp;<a onclick="FiltrerData('59')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_liberacion" name="col_fecha_liberacion"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Sello Origen&nbsp;<a onclick="FiltrerData('60')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_sello_origen" name="col_sello_origen"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Sello Final&nbsp;<a onclick="FiltrerData('61')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_sello_final" name="col_sello_final"></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fecha de retencion por la autoridad&nbsp;<a onclick="FiltrerData('62')"><i class="fa fa-search"></i></a> &nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_retencion_aut" name="col_fecha_retencion_aut"></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fec. de liberacion por ret. de la aut.&nbsp;<a onclick="FiltrerData('63')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_liberacion_aut" name="col_fecha_liberacion_aut"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Estatus de la operación&nbsp;<a onclick="FiltrerData('64')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_estatus_operacion" name="col_festatus_operacion"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Motivo Atraso&nbsp;<a onclick="FiltrerData('65')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_motivo_atraso" name="col_motivo_atraso"></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Observaciones&nbsp;<a onclick="FiltrerData('66')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_observaciones" name="col_observaciones"></select></th>
                <% 
                    }

                    if(AgentType.equals("4001")||AgentType.equals("4006")){ //LOGIX Y VF 
                %>           
                            <th class="col-sm-4" style="background-color:#1C84C6;">Llegada a NOVA&nbsp;<a onclick="FiltrerData('67')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_llegada_a_nova" name="col_llegada_a_nova"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Llegada a Globe trade SD&nbsp;<a onclick="FiltrerData('68')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_llegada_a_globe_trade_sd" name="col_llegada_a_globe_trade_sd"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Archivo M&nbsp;<a onclick="FiltrerData('69')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_archivo_m" name="col_archivo_m"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Fecha de Archivo M&nbsp;<a onclick="FiltrerData('70')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_archivo_m" name="col_fecha_archivo_m"></select></th>
                            <th class="col-sm-5" style="background-color:#1C84C6;">Fecha Solicitud de Manipulacion&nbsp;<a onclick="FiltrerData('71')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_solicit_manip" name="col_fecha_solicit_manip"></select></th>
                            <th class="col-sm-6" style="background-color:#1C84C6;">Fecha de vencimiento de Manipulacion&nbsp;<a onclick="FiltrerData('72')"><i class="fa fa-search"></i></a>  &nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_vencim_manip" name="col_fecha_vencim_manip"></select></th>
                            <th class="col-sm-6" style="background-color:#1C84C6;">Fecha confirmacion Clave de Pedimento&nbsp;<a onclick="FiltrerData('73')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_confirm_clave_pedim" name="col_fecha_confirm_clave_pedim"></select></th>
                            <th class="col-sm-6" style="background-color:#1C84C6;">Fecha de Recepcion de Incrementables&nbsp;<a onclick="FiltrerData('74')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_recep_increment" name="col_fecha_recep_increment"></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">T&E&nbsp;<a onclick="FiltrerData('75')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_t_e" name="col_t_e"></select></th>
                            <th class="col-sm-5" style="background-color:#1C84C6;">Fecha de Vencimiento del Inbound&nbsp;<a onclick="FiltrerData('76')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_vencim_inbound" name="col_fecha_vencim_inbound"></select></th>
                <%
                    }

                    if(AgentType.equals("4002")||AgentType.equals("4006")){  //CUSA Y VF
                %>            
                            <th class="col-sm-4" style="background-color:#1C84C6;">No. BULTOS&nbsp;<a onclick="FiltrerData('77')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_no_bultos" name="col_no_bultos"></select> </th> 
                            <th class="col-sm-4" style="background-color:#1C84C6;">Peso (KG)&nbsp;<a onclick="FiltrerData('78')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_peso_kg" name="col_peso_kg"></select> </th> 
                            <th class="col-sm-4" style="background-color:#1C84C6;">Transferencia&nbsp;<a onclick="FiltrerData('79')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_transferencia" name="col_transferencia"></select> </th> 
                            <th class="col-sm-4" style="background-color:#1C84C6;">Fecha Inicio Etiquetado&nbsp;<a onclick="FiltrerData('80')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_inicio_etiquetado" name="col_fecha_inicio_etiquetado"></select> </th> 
                            <th class="col-sm-4" style="background-color:#1C84C6;">Fecha Termino Etiquetado&nbsp;<a onclick="FiltrerData('81')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_termino_etiquetado" name="col_fecha_termino_etiquetado"></select> </th> 
                            <th class="col-sm-5" style="background-color:#1C84C6;">Hora de termino Etiquetado&nbsp;<a onclick="FiltrerData('82')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_hora_termino_etiquetado" name="col_hora_termino_etiquetado"></select> </th> 
                            <th class="col-sm-4" style="background-color:#1C84C6;">Proveedor&nbsp;<a onclick="FiltrerData('83')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_proveedor" name="col_proveedor"></select> </th> 
                            <th class="col-sm-4" style="background-color:#1C84C6;">Proveedor de Carga&nbsp;<a onclick="FiltrerData('84')"><i class="fa fa-search"></i></a> &nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_proveedor_carga" name="col_proveedor_carga"></select> </th>
                <%
                    }
                %>            
                            <th class="col-sm-4" style="background-color:#1C84C6;">FY&nbsp;<a onclick="FiltrerData('85')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fy" name="col_fy"></select></th>
                            <th class="col-sm-1" style="background-color:#FFFFFF;"></th>
                        </tr>
                    </thead>
                    <tbody id="detalleCustom">
                       <%
                         if (db.doDB(fac.consultarEventosCustoms(AgentType,filterType,caramelo))) {
                            for (String[] row : db.getResultado()) {
                            
                                if(AgentType.equals("4001")||AgentType.equals("4002")||AgentType.equals("4003")||AgentType.equals("4004")||AgentType.equals("4005")||AgentType.equals("4006")){ //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF                          
                                    list_evento.add("<option value='"+row[0]+"'>"+row[0]+"</option>");
                                    list_referenciaAA.add("<option value='"+row[30]+"'>"+row[30]+"</option>");
                                    list_responsable.add("<option value='"+row[1]+"'>"+row[1]+"</option>");
                                    list_finalDestination.add("<option value='"+row[2]+"'>"+row[2]+"</option>");
                                    list_brandDivision.add("<option value='"+row[21]+"'>"+row[21]+"</option>");
                                    list_division.add("<option value='"+row[4]+"'>"+row[4]+"</option>");
                                    list_shipmentId.add("<option value='"+row[5]+"'>"+row[5]+"</option>");
                                    list_containerId.add("<option value='"+row[6]+"'>"+row[6]+"</option>");
                                    list_blAwbPro.add("<option value='"+row[7]+"'>"+row[7]+"</option>");
                                    list_loadType.add("<option value='"+row[8]+"'>"+row[8]+"</option>");
                                    list_quantity.add("<option value='"+row[9]+"'>"+row[9]+"</option>");
                                    list_pod.add("<option value='"+row[19]+"'>"+row[19]+"</option>");
                                    list_estDepartFromPol.add("<option value='"+row[11]+"'>"+row[11]+"</option>");
                                    list_etaRealPortOfDischarge.add("<option value='"+row[12]+"'>"+row[12]+"</option>");
                                    list_estEtaDc.add("<option value='"+row[22]+"'>"+row[22]+"</option>");
                                    list_inboundNotification.add("<option value='"+row[14]+"'>"+row[14]+"</option>"); 
                                    list_pol.add("<option value='"+row[20]+"'>"+row[20]+"</option>");
                                    list_aa.add("<option value='"+row[16]+"'>"+row[16]+"</option>");
                                    list_fechaMesVenta.add("<option value='"+row[28]+"'>"+row[28]+"</option>");
                                    list_prioridad.add("<option value='"+row[97]+"'>"+row[97]+"</option>");
                                    list_pais_origen.add("<option value='"+row[31]+"'>"+row[31]+"</option>");   
                                    list_size_container.add("<option value='"+row[32]+"'>"+row[32]+"</option>");    
                                    list_valor_usd.add("<option value='"+row[33]+"'>"+row[33]+"</option>");                 
                                    list_eta_port_discharge.add("<option value='"+row[34]+"'>"+row[34]+"</option>");         
                                    list_agente_aduanal.add("<option value='"+row[35]+"'>"+row[35]+"</option>");              
                                    list_pedimento_a1.add("<option value='"+row[36]+"'>"+row[36]+"</option>");              
                                    list_pedimento_r1_1er.add("<option value='"+row[37]+"'>"+row[37]+"</option>");          
                                    list_motivo_rectificacion_1er.add("<option value='"+row[38]+"'>"+row[38]+"</option>");    
                                    list_pedimento_r1_2do.add("<option value='"+row[39]+"'>"+row[39]+"</option>");         
                                    list_motivo_rectificacion_2do.add("<option value='"+row[40]+"'>"+row[40]+"</option>");    
                                    list_fecha_recepcion_doc.add("<option value='"+row[41]+"'>"+row[41]+"</option>");    
                                    list_recinto.add("<option value='"+row[42]+"'>"+row[42]+"</option>");
                                    list_naviera.add("<option value='"+row[43]+"'>"+row[43]+"</option>");
                                    list_buque.add("<option value='"+row[44]+"'>"+row[44]+"</option>");
                                    list_fecha_revalidacion.add("<option value='"+row[45]+"'>"+row[45]+"</option>");      
                                    list_fecha_previo_origen.add("<option value='"+row[46]+"'>"+row[46]+"</option>");     
                                    list_fecha_previo_destino.add("<option value='"+row[47]+"'>"+row[47]+"</option>");   
                                    list_fecha_resultado_previo.add("<option value='"+row[48]+"'>"+row[48]+"</option>");   
                                    list_proforma_final.add("<option value='"+row[49]+"'>"+row[49]+"</option>");        
                                    list_permiso.add("<option value='"+row[50]+"'>"+row[50]+"</option>");               
                                    list_fecha_envio.add("<option value='"+row[51]+"'>"+row[51]+"</option>");             
                                    list_fecha_recepcion_perm.add("<option value='"+row[52]+"'>"+row[52]+"</option>");
                                    list_fecha_activacion_perm.add("<option value='"+row[53]+"'>"+row[53]+"</option>");    
                                    list_fecha_permisos_aut.add("<option value='"+row[54]+"'>"+row[54]+"</option>");     
                                    list_co_pref_arancelaria.add("<option value='"+row[55]+"'>"+row[55]+"</option>");    
                                    list_aplic_pref_arancelaria.add("<option value='"+row[56]+"'>"+row[56]+"</option>");  
                                    list_req_uva.add("<option value='"+row[57]+"'>"+row[57]+"</option>");   
                                    list_req_ca.add("<option value='"+row[58]+"'>"+row[58]+"</option>");    
                                    list_fecha_recepcion_ca.add("<option value='"+row[59]+"'>"+row[59]+"</option>"); 
                                    list_num_constancia_ca.add("<option value='"+row[60]+"'>"+row[60]+"</option>");   
                                    list_monto_ca.add("<option value='"+row[61]+"'>"+row[61]+"</option>");
                                    list_fecha_doc_completos.add("<option value='"+row[62]+"'>"+row[62]+"</option>");  
                                    list_fecha_pago_pedimento.add("<option value='"+row[63]+"'>"+row[63]+"</option>");     
                                    list_fecha_solicitud_transporte.add("<option value='"+row[64]+"'>"+row[64]+"</option>");
                                    list_fecha_modulacion.add("<option value='"+row[65]+"'>"+row[65]+"</option>");   
                                    list_modalidad.add("<option value='"+row[66]+"'>"+row[66]+"</option>");          
                                    list_resultado_modulacion.add("<option value='"+row[67]+"'>"+row[67]+"</option>");    
                                    list_fecha_reconocimiento.add("<option value='"+row[68]+"'>"+row[68]+"</option>");     
                                    list_fecha_liberacion.add("<option value='"+row[69]+"'>"+row[69]+"</option>");       
                                    list_sello_origen.add("<option value='"+row[70]+"'>"+row[70]+"</option>");            
                                    list_sello_final.add("<option value='"+row[71]+"'>"+row[71]+"</option>");      
                                    list_fecha_retencion_aut.add("<option value='"+row[72]+"'>"+row[72]+"</option>");     
                                    list_fecha_liberacion_aut.add("<option value='"+row[73]+"'>"+row[73]+"</option>");   
                                    list_estatus_operacion.add("<option value='"+row[74]+"'>"+row[74]+"</option>");      
                                    list_motivo_atraso.add("<option value='"+row[75]+"'>"+row[75]+"</option>");          
                                    list_observaciones.add("<option value='"+row[76]+"'>"+row[76]+"</option>"); 
                                }

                                if(AgentType.equals("4001")||AgentType.equals("4006")){ //LOGIX Y VF            
                                    list_llegada_a_nova.add("<option value='"+row[77]+"'>"+row[77]+"</option>");
                                    list_llegada_a_globe_trade_sd.add("<option value='"+row[78]+"'>"+row[78]+"</option>");
                                    list_archivo_m.add("<option value='"+row[79]+"'>"+row[79]+"</option>");
                                    list_fecha_archivo_m.add("<option value='"+row[80]+"'>"+row[80]+"</option>");
                                    list_fecha_solicit_manip.add("<option value='"+row[81]+"'>"+row[81]+"</option>");
                                    list_fecha_vencim_manip.add("<option value='"+row[82]+"'>"+row[82]+"</option>");
                                    list_fecha_confirm_clave_pedim.add("<option value='"+row[83]+"'>"+row[83]+"</option>");
                                    list_fecha_recep_increment.add("<option value='"+row[84]+"'>"+row[84]+"</option>");
                                    list_t_e.add("<option value='"+row[85]+"'>"+row[85]+"</option>");
                                    list_fecha_vencim_inbound.add("<option value='"+row[86]+"'>"+row[86]+"</option>");
                                }

                                if(AgentType.equals("4002")||AgentType.equals("4006")){  //CUSA Y VF
                                    list_no_bultos.add("<option value='"+row[87]+"'>"+row[87]+"</option>");
                                    list_peso_kg.add("<option value='"+row[88]+"'>"+row[88]+"</option>");
                                    list_transferencia.add("<option value='"+row[89]+"'>"+row[89]+"</option>");
                                    list_fecha_inicio_etiquetado.add("<option value='"+row[90]+"'>"+row[90]+"</option>");
                                    list_fecha_termino_etiquetado.add("<option value='"+row[91]+"'>"+row[91]+"</option>");
                                    list_hora_termino_etiquetado.add("<option value='"+row[92]+"'>"+row[92]+"</option>");
                                    list_proveedor.add("<option value='"+row[93]+"'>"+row[93]+"</option>");
                                    list_proveedor_carga.add("<option value='"+row[94]+"'>"+row[94]+"</option>");
                                    list_fy.add("<option value='"+row[95]+"'>"+row[95]+"</option>");
                                }
                        %>
                        <tr id="tr<%=cont%>">
                        <%
                            if(AgentType.equals("4001")||AgentType.equals("4002")||AgentType.equals("4003")||AgentType.equals("4004")||AgentType.equals("4005")||AgentType.equals("4006")){ //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF                                                    
                             
                             if(row[99].equals("1")){
                                 colorSemaforo = "../img/circle-green.webp";
                                 sizeSemaforo = "100%";
                             }else if(row[99].equals("2")){
                                 colorSemaforo = "../img/circle-yellow.webp";
                                 sizeSemaforo = "80%";
                             }else if(row[99].equals("3")){
                                 colorSemaforo = "../img/circle-red.webp";
                                 sizeSemaforo = "60%";
                             }else{
                                 colorSemaforo = "../img/circle-gray.webp";
                                 sizeSemaforo = "100%";
                             }
                             
                            if(row[58]=="No"){ /*req_ca*/
                                blockedDate = "false";
                            }
                        %>   
                                <th id="columna"><center><img id="imgSemaforo<%=cont%>" src="<%=colorSemaforo%>" width="<%=sizeSemaforo%>"/></center></th>
                                <th id="ReferenciaAduanal"><input type="text" onkeyup="this.value = this.value.toUpperCase()" class="form-control" id="referenciaAA[<%=cont%>]" name="referenciaAA[<%=cont%>]" value="<%=row[30].trim()%>" autocomplete="off"></th>
                                <th class="font-numero first-column" id="elemento<%=cont%>"><%=row[0]%><input type="hidden" id="evento[<%=cont%>]" name="evento[<%=cont%>]" value="<%=row[0]%>"><div id="popup<%=cont%>" style="display: none;"><div id="mSgError<%=cont%>"></div></div></th>
                                <td id="Responsable[<%=cont%>]"><%=row[1]%></td>
                                <td id="FinalDestination[<%=cont%>]"><%=row[2]%></td>
                                <td id="BrandDivision[<%=cont%>]"><%=row[21]%></td>
                                <td id="Division[<%=cont%>]"><%=row[4]%></td>
                                <td><%=row[5]%><input type="hidden" id="shipmentId[<%=cont%>]" name="shipmentId[<%=cont%>]" value="<%=row[5]%>"></td>
                                <td> <%=row[6]%><input type="hidden" id="containerId[<%=cont%>]" name="containerId[<%=cont%>]" value="<%=row[6]%>"></td>
                                <td id="blAwbPro[<%=cont%>]"><%=row[7]%></td>
                                <td><%=row[8]%><input type="hidden" id="loadTypeFinal[<%=cont%>]" name="loadTypeFinal[<%=cont%>]" value="<%=row[8]%>"><input type="hidden" id="plantillaId[<%=cont%>]" name="plantillaId[<%=cont%>]" value="<%=row[17]%>"></td>
                                <td id="Quantity[<%=cont%>]"><%=row[9]%></td>
                                <td id="Pod[<%=cont%>]"><%=row[19]%></td>
                                <td id="EstDepartureFromPol[<%=cont%>]"><%=row[11]%></td>
                                <td id="EtaRealPortOfDischarge[<%=cont%>]"><%=row[12]%></td>
                                <td id="EstEtaDc[<%=cont%>]"><%=row[22]%></td>
                                <td id="InboundNotification[<%=cont%>]"><%=row[14]%></td>
                                <td id="Pol[<%=cont%>]"><%=row[20]%></td>
                                <td id="aa[<%=cont%>]"><%=row[16]%></td>
                                <td id="FechaMesVenta[<%=cont%>]"><%=row[28]%></td>
                                <td><%=row[97]%><input type="hidden" id="prioridad[<%=cont%>]" name="prioridad[<%=cont%>]" value="<%=row[97]%>"></td>
                                <td><input class="form-control" id="pais_origen[<%=cont%>]" name="pais_origen[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[31].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control" id="size_container[<%=cont%>]" name="size_container[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[32].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control" id="valor_usd[<%=cont%>]" name="valor_usd[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[33].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control datepicker" id="eta_port_discharge[<%=cont%>]" name="eta_port_discharge[<%=cont%>]" type="text" value="<%=row[34].trim()%>" onchange="pedimento(this.value,'<%=cont%>')" autocomplete="off"><a class="text-lg text-blue" onclick="historicoSemaforo('<%=row[5]%>')"><i class="fa fa-folder-open"></i></a></td>
                                <td><input class="form-control" id="agente_aduanal[<%=cont%>]" name="agente_aduanal[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[35].trim()%>" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off"></td>
                                <td><input class="form-control" id="pedimento_a1[<%=cont%>]" name="pedimento_a1[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[36].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control" id="pedimento_r1_1er[<%=cont%>]" name="pedimento_r1_1er[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[37].trim()%>" autocomplete="off" onblur="cleanPedimento_r1_1er(this.value,'<%=cont%>')"></td>
                                <td><input class="form-control" id="motivo_rectificacion_1er[<%=cont%>]" name="motivo_rectificacion_1er[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[38].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control" id="pedimento_r1_2do[<%=cont%>]" name="pedimento_r1_2do[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[39].trim()%>" autocomplete="off" onblur="cleanPedimento_r1_2do(this.value,'<%=cont%>')"></td>
                                <td><input class="form-control" id="motivo_rectificacion_2do[<%=cont%>]" name="motivo_rectificacion_2do[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[40].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control datepicker" id="fecha_recepcion_doc[<%=cont%>]" name="fecha_recepcion_doc[<%=cont%>]" type="text" value="<%=row[41].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control" id="recinto[<%=cont%>]" name="recinto[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[42].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control" id="naviera[<%=cont%>]" name="naviera[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[43].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control" id="buque[<%=cont%>]" name="buque[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[44].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control datepicker" id="fecha_revalidacion[<%=cont%>]" name="fecha_revalidacion[<%=cont%>]" type="text" value="<%=row[45].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control datepicker" id="fecha_previo_origen[<%=cont%>]" name="fecha_previo_origen[<%=cont%>]" type="text" value="<%=row[46].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control datepicker" id="fecha_previo_destino[<%=cont%>]" name="fecha_previo_destino[<%=cont%>]" type="text" value="<%=row[47].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control datepicker" id="fecha_resultado_previo[<%=cont%>]" name="fecha_resultado_previo[<%=cont%>]" type="text" value="<%=row[48].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control datepicker" id="proforma_final[<%=cont%>]" name="proforma_final[<%=cont%>]" type="text" value="<%=row[49].trim()%>" autocomplete="off"></td>
                                <td><select class="form-control" id="permiso[<%=cont%>]" name="permiso[<%=cont%>]" value="<%=row[50]%>" onchange="cleanPermiso(this.value,'<%=cont%>')"><option value="<%=row[50]%>"><%=row[50]%></option><option value="Si">Si</option><option value="No">No</option></select></td>
                                <td><input class="form-control datepicker" id="fecha_envio[<%=cont%>]" name="fecha_envio[<%=cont%>]" type="text" value="<%=row[51].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control datepicker" id="fecha_recepcion_perm[<%=cont%>]" name="fecha_recepcion_perm[<%=cont%>]" type="text" value="<%=row[52].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control datepicker" id="fecha_activacion_perm[<%=cont%>]" name="fecha_activacion_perm[<%=cont%>]" type="text" value="<%=row[53].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control datepicker" id="fecha_permisos_aut[<%=cont%>]" name="fecha_permisos_aut[<%=cont%>]" type="text" value="<%=row[54].trim()%>" autocomplete="off"></td>
                                <td><select class="form-control" id="co_pref_arancelaria[<%=cont%>]" name="co_pref_arancelaria[<%=cont%>]" value="<%=row[55]%>"><option value="<%=row[55]%>"><%=row[55]%></option><option value="Si">Si</option><option value="No">No</option></select></td>
                                <td><select class="form-control" id="aplic_pref_arancelaria[<%=cont%>]" name="aplic_pref_arancelaria[<%=cont%>]" value="<%=row[56]%>"><option value="<%=row[56]%>"><%=row[56]%></option><option value="Si">Si</option><option value="No">No</option></select></td>
                                <td><select class="form-control" id="req_uva[<%=cont%>]" name="req_uva[<%=cont%>]" value="<%=row[57]%>"><option value="<%=row[57]%>"><%=row[57]%></option><option value="Si">Si</option><option value="No">No</option></select></td>
                                <td><select class="form-control" id="req_ca[<%=cont%>]" name="req_ca[<%=cont%>]"value="<%=row[58]%>" onchange="cleanRequerimientoCA(this.value,'<%=cont%>')"><option value="<%=row[58]%>"><%=row[58]%></option><option value="Si">Si</option><option value="No">No</option></select></td>
                                <td><input class="form-control datepicker" id="fecha_recepcion_ca[<%=cont%>]" name="fecha_recepcion_ca[<%=cont%>]" type="text" value="<%=row[59].trim()%>" autocomplete="off" disabled="<%=blockedDate%>"></td>
                                <td><input class="form-control" id="num_constancia_ca[<%=cont%>]" name="num_constancia_ca[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[60].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control" id="monto_ca[<%=cont%>]" name="monto_ca[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[61].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control datepicker" id="fecha_doc_completos[<%=cont%>]" name="fecha_doc_completos[<%=cont%>]" value="<%=row[62].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control datepicker-pedimento<%=cont%>" id="fecha_pago_pedimento[<%=cont%>]" name="fecha_pago_pedimento[<%=cont%>]" type="text" value="<%=row[63].trim()%>" onchange="modulacion('<%=cont%>')"></td>
                                <td><input class="form-control datepicker" id="fecha_solicitud_transporte[<%=cont%>]" name="fecha_solicitud_transporte[<%=cont%>]" type="text" value="<%=row[64].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control datepicker-modulacion<%=cont%>" id="fecha_modulacion[<%=cont%>]" name="fecha_modulacion[<%=cont%>]" type="text" value="<%=row[65].trim()%>"></td>
                                <td><select class="form-control" id="modalidad[<%=cont%>]" name="modalidad[<%=cont%>]" value="<%=row[66]%>"><option value="<%=row[66]%>"><%=row[66]%></option><option value="Camión">Camión</option><option value="Tren">Tren</option></select></td>
                                <td><select class="form-control" id="resultado_modulacion[<%=cont%>]" name="resultado_modulacion[<%=cont%>]"value="<%=row[67]%>" onchange="cleanResultadoModulacion(this.value,'<%=cont%>','<%=AgentType%>')"><option value="<%=row[67]%>"><%=row[67]%></option><option value="Verde">Verde</option><option value="Rojo">Rojo</option></select></td>
                                <td><input class="form-control datepicker" id="fecha_reconocimiento[<%=cont%>]" name="fecha_reconocimiento[<%=cont%>]" type="text" value="<%=row[68].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control datepicker" id="fecha_liberacion[<%=cont%>]" name="fecha_liberacion[<%=cont%>]" type="text" value="<%=row[69].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control" id="sello_origen[<%=cont%>]" name="sello_origen[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()"value="<%=row[70].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control" id="sello_final[<%=cont%>]" name="sello_final[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()"value="<%=row[71].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control datepicker" id="fecha_retencion_aut[<%=cont%>]" name="fecha_retencion_aut[<%=cont%>]" type="text" value="<%=row[72].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control datepicker" id="fecha_liberacion_aut[<%=cont%>]" name="fecha_liberacion_aut[<%=cont%>]" type="text" value="<%=row[73].trim()%>" autocomplete="off"></td>
                                <td onmouseover="formComplet('<%=AgentType%>','<%=cont%>')"><select class="form-control" id="estatus_operacion[<%=cont%>]" name="estatus_operacion[<%=cont%>]"value="<%=row[74]%>"> <option value="<%=row[98]%>"><%=row[74]%></option><%=listStatusOperationEvent%></select></td>
                                <td><input class="form-control" id="motivo_atraso[<%=cont%>]" name="motivo_atraso[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()"value="<%=row[75].trim()%>" autocomplete="off"></td>
                                <td><input class="form-control" id="observaciones[<%=cont%>]" name="observaciones[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[76].trim()%>" autocomplete="off"></td>
                        <%
                            }

                            if(AgentType.equals("4001")||AgentType.equals("4006")){ //LOGIX Y VF
                        %>
                               <td><input class="form-control datepicker" id="llegada_a_nova[<%=cont%>]" name="llegada_a_nova[<%=cont%>]" type="text" value="<%=row[77].trim()%>" autocomplete="off"></td>
                               <td><input class="form-control datepicker" id="llegada_a_globe_trade_sd[<%=cont%>]" name="llegada_a_globe_trade_sd[<%=cont%>]" type="text" value="<%=row[78].trim()%>" autocomplete="off"></td>
                               <td><input class="form-control" id="archivo_m[<%=cont%>]" name="archivo_m[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[79].trim()%>" autocomplete="off"></td>
                               <td><input class="form-control datepicker" id="fecha_archivo_m[<%=cont%>]" name="fecha_archivo_m[<%=cont%>]" type="text" value="<%=row[80].trim()%>" autocomplete="off"></td>
                               <td><input class="form-control datepicker" id="fecha_solicit_manip[<%=cont%>]" name="fecha_solicit_manip[<%=cont%>]" type="text" value="<%=row[81].trim()%>" autocomplete="off"></td>
                               <td><input class="form-control datepicker" id="fecha_vencim_manip[<%=cont%>]" name="fecha_vencim_manip[<%=cont%>]" type="text" value="<%=row[82].trim()%>" autocomplete="off"></td>
                               <td><input class="form-control datepicker" id="fecha_confirm_clave_pedim[<%=cont%>]" name="fecha_confirm_clave_pedim[<%=cont%>]" type="text" value="<%=row[83].trim()%>" autocomplete="off"></td>
                               <td><input class="form-control datepicker" id="fecha_recep_increment[<%=cont%>]" name="fecha_recep_increment[<%=cont%>]" type="text" value="<%=row[84].trim()%>" autocomplete="off"></td>
                               <td><input class="form-control" id="t_e[<%=cont%>]" name="t_e[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[85].trim()%>" autocomplete="off"></td>
                               <td><input class="form-control datepicker" id="fecha_vencim_inbound[<%=cont%>]" name="fecha_vencim_inbound[<%=cont%>]" type="text" value="<%=row[86].trim()%>" autocomplete="off"></td>
                        <%
                            }

                            if(AgentType.equals("4002")||AgentType.equals("4006")){  //CUSA Y VF
                        %>
                               <td><input class="form-control" id="no_bultos[<%=cont%>]" name="no_bultos[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[87].trim()%>" autocomplete="off"></td>
                               <td><input class="form-control" id="peso_kg[<%=cont%>]" name="peso_kg[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[88].trim()%>" autocomplete="off"></td>
                               <td> <select class="form-control" id="transferencia[<%=cont%>]" name="transferencia[<%=cont%>]" value="<%=row[89]%>"><option value="<%=row[89]%>"><%=row[89]%></option><option value="Si">Si</option><option value="No">No</option> </select></td>
                               <td><input class="form-control datepicker" id="fecha_inicio_etiquetado[<%=cont%>]" name="fecha_inicio_etiquetado[<%=cont%>]" type="text" value="<%=row[90].trim()%>" autocomplete="off"></td>
                               <td><input class="form-control datepicker" id="fecha_termino_etiquetado[<%=cont%>]" name="fecha_termino_etiquetado[<%=cont%>]" type="text" value="<%=row[91].trim()%>" autocomplete="off"></td>
                               <td><input class="form-control" id="hora_termino_etiquetado[<%=cont%>]" name="hora_termino_etiquetado[<%=cont%>]" type="time" value="<%=row[92].trim()%>" autocomplete="off"></td>
                               <td><input class="form-control" id="proveedor[<%=cont%>]" name="proveedor[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[93].trim()%>" autocomplete="off"></td>
                               <td><input class="form-control" id="proveedor_carga[<%=cont%>]" name="proveedor_carga[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[94].trim()%>" autocomplete="off"></td>
                        <%   
                            }
                        %>                  
                               <td><input class="form-control" id="fy[<%=cont%>]" name="fy[<%=cont%>]" type="text" onkeyup="this.value = this.value.toUpperCase()" value="<%=row[95].trim()%>" autocomplete="off"></td>
                               <td><a class="btn btn-primary text-uppercase" onclick="AddLineCustoms('<%=cont%>')"><i class="fa fa-save"></i></a></td> 
                          </tr>
                    <%         
                        cont++;
                          }
                        }
                    %>      
                    </tbody>
                </table>
            </div>
        </div>           
        <!-- modal - Discharge Semaforo -->
        <div class="modal fade text-start" id="modalSemaforo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header border-0 bg-gray-100">
                        <h3 class="h6 modal-title" id="exampleModalLabel">
                            <div class="card-heading"><img src="../img/Semaforo.webp" width="3%"/>&nbsp;ETA Port Of Discharge Historic</div>
                        </h3>
                        <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <center><div id="idSemaforo"></div></center>
                        <table class="table" id="mytable" name="mytable" width="50%">
                            <thead>
                                <tr>
                                    <th><strong>FECHA ACTIVACION</strong></th>
                                    <th><strong>PRIORIDAD</strong></th>
                                    <th><strong>LOAD TYPE</strong></th>
                                    <th><strong>FECHA ESTIMADA</strong></th>
                                </tr>
                            </thead>
                            <tbody id="AddTableSemaforo"></tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div> 
        <!-- Parametros - Customs -->
        <input type="hidden" id="numCustoms" name="numCustoms" value="<%=cont%>">
        <input type="hidden" id="idAgenteAduanal" name="idAgenteAduanal" value="<%=AgentType%>">
        
        <!-- Parametros - Generación de Excel -->
        <input type="hidden" name="idPlantilla" value="<%=idPlantilla%>" id="idPlantilla"/>
        <input type="hidden" name="idOpcion" value="1" id="idOpcion"/>
        <input type="hidden" name="idLenguaje" value="1" id="idLenguaje"/>
        <input type="hidden" name="idDivision" value="<%=idDivision%>" id="idDivision"/>                                     
        <input type="hidden" name="idBodega" value="<%=idBodega%>" id="idBodega"/>
        <input type="hidden" name="idAction" value="<%=request.getContextPath()%>/plantillaExcel" id="idAction"/>
        <img src="../img/loadingCloud.gif" id="idClouding" width="50px" height="50px" name="idClouding" title="Clouding" style="display: none; height: 50px; width: 50px;"/>

        <script>
            let idAgenteAduanal = '<%=AgentType%>';
            
            /*lectura de columnas (selects/filtros)*/
            if(idAgenteAduanal==="4001"||idAgenteAduanal==="4002"||idAgenteAduanal==="4003"||idAgenteAduanal==="4004"||idAgenteAduanal==="4005"||idAgenteAduanal==="4006"){ //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF   
            
                var selectElement = document.getElementById("col_evento");
                var selectElement1= document.getElementById("col_referenciaAA");
                var selectElement2= document.getElementById("col_responsable");
                var selectElement3= document.getElementById("col_finalDestination");
                var selectElement4= document.getElementById("col_brandDivision");
                var selectElement5= document.getElementById("col_division");
                var selectElement6= document.getElementById("col_shipmentId");
                var selectElement7= document.getElementById("col_container");
                var selectElement8= document.getElementById("col_blAwbPro");
                var selectElement9= document.getElementById("col_loadType");
                var selectElement10= document.getElementById("col_quantity");
                var selectElement11= document.getElementById("col_pod");
                var selectElement12= document.getElementById("col_estDepartFromPol");
                var selectElement13= document.getElementById("col_etaRealPortOfDischarge");
                var selectElement14= document.getElementById("col_estEtaDc");
                var selectElement15= document.getElementById("col_inboundNotification");
                var selectElement16= document.getElementById("col_pol");
                var selectElement17= document.getElementById("col_aa");
                var selectElement18= document.getElementById("col_fechaMesVenta");
                var selectElement19= document.getElementById("col_prioridad");
                var selectElement20= document.getElementById("col_pais_origen");
                var selectElement21= document.getElementById("col_size_container");
                var selectElement22= document.getElementById("col_valor_usd");
                var selectElement23= document.getElementById("col_eta_port_discharge");
                var selectElement24= document.getElementById("col_agente_aduanal");
                var selectElement25= document.getElementById("col_pedimento_a1");
                var selectElement26= document.getElementById("col_pedimento_r1_1er");
                var selectElement27= document.getElementById("col_motivo_rectificacion_1er");
                var selectElement28= document.getElementById("col_pedimento_r1_2do");
                var selectElement29= document.getElementById("col_motivo_rectificacion_2do");
                var selectElement30= document.getElementById("col_fecha_recepcion_doc");
                var selectElement31= document.getElementById("col_recinto");
                var selectElement32= document.getElementById("col_naviera");
                var selectElement33= document.getElementById("col_buque");
                var selectElement34= document.getElementById("col_fecha_revalidacion");
                var selectElement35= document.getElementById("col_fecha_previo_origen");
                var selectElement36= document.getElementById("col_fecha_previo_destino");
                var selectElement37= document.getElementById("col_fecha_resultado_previo");
                var selectElement38= document.getElementById("col_proforma_final");
                var selectElement39= document.getElementById("col_permiso");
                var selectElement40= document.getElementById("col_fecha_envio");
                var selectElement41= document.getElementById("col_fecha_recepcion_perm");
                var selectElement42= document.getElementById("col_fecha_activacion_perm");
                var selectElement43= document.getElementById("col_fecha_permisos_aut");
                var selectElement44= document.getElementById("col_co_pref_arancelaria");
                var selectElement45= document.getElementById("col_aplic_pref_arancelaria");
                var selectElement46= document.getElementById("col_req_uva");
                var selectElement47= document.getElementById("col_req_ca");
                var selectElement48= document.getElementById("col_fecha_recepcion_ca");
                var selectElement49= document.getElementById("col_num_constancia_ca");
                var selectElement50= document.getElementById("col_monto_ca");
                var selectElement51= document.getElementById("col_fecha_doc_completos");
                var selectElement52= document.getElementById("col_fecha_pago_pedimento");
                var selectElement53= document.getElementById("col_fecha_solicitud_transporte");
                var selectElement54= document.getElementById("col_fecha_modulacion");
                var selectElement55= document.getElementById("col_modalidad");
                var selectElement56= document.getElementById("col_resultado_modulacion");
                var selectElement57= document.getElementById("col_fecha_reconocimiento");
                var selectElement58= document.getElementById("col_fecha_liberacion");
                var selectElement59= document.getElementById("col_sello_origen");
                var selectElement60= document.getElementById("col_sello_final");
                var selectElement61= document.getElementById("col_fecha_retencion_aut");
                var selectElement62= document.getElementById("col_fecha_liberacion_aut");
                var selectElement63= document.getElementById("col_estatus_operacion");
                var selectElement64= document.getElementById("col_motivo_atraso");
                var selectElement65= document.getElementById("col_observaciones");
                
           }
           
            if(idAgenteAduanal==="4001"||idAgenteAduanal==="4006"){ //LOGIX Y VF  
             
                var selectElement66= document.getElementById("col_llegada_a_nova");
                var selectElement67= document.getElementById("col_llegada_a_globe_trade_sd");
                var selectElement68= document.getElementById("col_archivo_m");
                var selectElement69= document.getElementById("col_fecha_archivo_m");
                var selectElement70= document.getElementById("col_fecha_solicit_manip");
                var selectElement71= document.getElementById("col_fecha_vencim_manip");
                var selectElement72= document.getElementById("col_fecha_confirm_clave_pedim");
                var selectElement73= document.getElementById("col_fecha_recep_increment");
                var selectElement74= document.getElementById("col_t_e");
                var selectElement75= document.getElementById("col_fecha_vencim_inbound");
            
            }
            
            if(idAgenteAduanal==="4002"||idAgenteAduanal==="4006"){  //CUSA Y VF
                
                var selectElement76= document.getElementById("col_no_bultos");
                var selectElement77= document.getElementById("col_peso_kg");
                var selectElement78= document.getElementById("col_transferencia");
                var selectElement79= document.getElementById("col_fecha_inicio_etiquetado");
                var selectElement80= document.getElementById("col_fecha_termino_etiquetado");
                var selectElement81= document.getElementById("col_hora_termino_etiquetado");
                var selectElement82= document.getElementById("col_proveedor");
                var selectElement83= document.getElementById("col_proveedor_carga");
                var selectElement84= document.getElementById("col_fy");
                
            }
            
            /*Asignar el listado de valores (selects/filtros)*/
            if(idAgenteAduanal==="4001"||idAgenteAduanal==="4002"||idAgenteAduanal==="4003"||idAgenteAduanal==="4004"||idAgenteAduanal==="4005"||idAgenteAduanal==="4006"){ //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF   
                
                selectElement.innerHTML="<%=list_evento%>";
                selectElement1.innerHTML="<%=list_referenciaAA%>";
                selectElement2.innerHTML="<%=list_responsable%>";
                selectElement3.innerHTML="<%=list_finalDestination%>";
                selectElement4.innerHTML="<%=list_brandDivision%>";
                selectElement5.innerHTML="<%=list_division%>";
                selectElement6.innerHTML="<%=list_shipmentId%>";
                selectElement7.innerHTML="<%=list_containerId%>";
                selectElement8.innerHTML="<%=list_blAwbPro%>";
                selectElement9.innerHTML="<%=list_loadType%>";
                selectElement10.innerHTML="<%=list_quantity%>";
                selectElement11.innerHTML="<%=list_pod%>";
                selectElement12.innerHTML="<%=list_estDepartFromPol%>";
                selectElement13.innerHTML="<%=list_etaRealPortOfDischarge%>";
                selectElement14.innerHTML="<%=list_estEtaDc%>";
                selectElement15.innerHTML="<%=list_inboundNotification%>";
                selectElement16.innerHTML="<%=list_pol%>";
                selectElement17.innerHTML="<%=list_aa%>";
                selectElement18.innerHTML="<%=list_fechaMesVenta%>";
                selectElement19.innerHTML="<%=list_prioridad%>";
                selectElement20.innerHTML="<%=list_pais_origen%>";
                selectElement21.innerHTML="<%=list_size_container%>";
                selectElement22.innerHTML="<%=list_valor_usd%>";
                selectElement23.innerHTML="<%=list_eta_port_discharge%>";
                selectElement24.innerHTML="<%=list_agente_aduanal%>";
                selectElement25.innerHTML="<%=list_pedimento_a1%>";
                selectElement26.innerHTML="<%=list_pedimento_r1_1er%>";
                selectElement27.innerHTML="<%=list_motivo_rectificacion_1er%>";
                selectElement28.innerHTML="<%=list_pedimento_r1_2do%>";
                selectElement29.innerHTML="<%=list_motivo_rectificacion_2do%>";
                selectElement30.innerHTML="<%=list_fecha_recepcion_doc%>";
                selectElement31.innerHTML="<%=list_recinto%>";
                selectElement32.innerHTML="<%=list_naviera%>";
                selectElement33.innerHTML="<%=list_buque%>";
                selectElement34.innerHTML="<%=list_fecha_revalidacion%>";
                selectElement35.innerHTML="<%=list_fecha_previo_origen%>";
                selectElement36.innerHTML="<%=list_fecha_previo_destino%>";
                selectElement37.innerHTML="<%=list_fecha_resultado_previo%>";
                selectElement38.innerHTML="<%=list_proforma_final%>";
                selectElement39.innerHTML="<%=list_permiso%>";
                selectElement40.innerHTML="<%=list_fecha_envio%>";
                selectElement41.innerHTML="<%=list_fecha_recepcion_perm%>";
                selectElement42.innerHTML="<%=list_fecha_activacion_perm%>";
                selectElement43.innerHTML="<%=list_fecha_permisos_aut%>";
                selectElement44.innerHTML="<%=list_co_pref_arancelaria%>";
                selectElement45.innerHTML="<%=list_aplic_pref_arancelaria%>";
                selectElement46.innerHTML="<%=list_req_uva%>";
                selectElement47.innerHTML="<%=list_req_ca%>";
                selectElement48.innerHTML="<%=list_fecha_recepcion_ca%>";
                selectElement49.innerHTML="<%=list_num_constancia_ca%>";
                selectElement50.innerHTML="<%=list_monto_ca%>";
                selectElement51.innerHTML="<%=list_fecha_doc_completos%>";
                selectElement52.innerHTML="<%=list_fecha_pago_pedimento%>";
                selectElement53.innerHTML="<%=list_fecha_solicitud_transporte%>";
                selectElement54.innerHTML="<%=list_fecha_modulacion%>";
                selectElement55.innerHTML="<%=list_modalidad%>";
                selectElement56.innerHTML="<%=list_resultado_modulacion%>";
                selectElement57.innerHTML="<%=list_fecha_reconocimiento%>";
                selectElement58.innerHTML="<%=list_fecha_liberacion%>";
                selectElement59.innerHTML="<%=list_sello_origen%>";
                selectElement60.innerHTML="<%=list_sello_final%>";
                selectElement61.innerHTML="<%=list_fecha_retencion_aut%>";
                selectElement62.innerHTML="<%=list_fecha_liberacion_aut%>";
                selectElement63.innerHTML="<%=list_estatus_operacion%>";
                selectElement64.innerHTML="<%=list_motivo_atraso%>";
                selectElement65.innerHTML="<%=list_observaciones%>";
            
            }
            
            if(idAgenteAduanal==="4001"||idAgenteAduanal==="4006"){ //LOGIX Y VF      
                
                selectElement66.innerHTML="<%=list_llegada_a_nova%>";
                selectElement67.innerHTML="<%=list_llegada_a_globe_trade_sd%>";
                selectElement68.innerHTML="<%=list_archivo_m%>";
                selectElement69.innerHTML="<%=list_fecha_archivo_m%>";
                selectElement70.innerHTML="<%=list_fecha_solicit_manip%>";
                selectElement71.innerHTML="<%=list_fecha_vencim_manip%>";
                selectElement72.innerHTML="<%=list_fecha_confirm_clave_pedim%>";
                selectElement73.innerHTML="<%=list_fecha_recep_increment%>";
                selectElement74.innerHTML="<%=list_t_e%>";
                selectElement75.innerHTML="<%=list_fecha_vencim_inbound%>";
                
            }
            
            if(idAgenteAduanal==="4002"||idAgenteAduanal==="4006"){  //CUSA Y VF
                
                selectElement76.innerHTML="<%=list_no_bultos%>";
                selectElement77.innerHTML="<%=list_peso_kg%>";
                selectElement78.innerHTML="<%=list_transferencia%>";
                selectElement79.innerHTML="<%=list_fecha_inicio_etiquetado%>";
                selectElement80.innerHTML="<%=list_fecha_termino_etiquetado%>";
                selectElement81.innerHTML="<%=list_hora_termino_etiquetado%>";
                selectElement82.innerHTML="<%=list_proveedor%>";
                selectElement83.innerHTML="<%=list_proveedor_carga%>";
                selectElement84.innerHTML="<%=list_fy%>";
                
            }  
        </script>
        <!-- JavaScript files-->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Sweetalert -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
        <!-- Actions js -->
        <script src="../lib/validationsInbound/customs/customsForms.js" type="text/javascript"></script>
        <!-- fruitsSelect value -->
        <script src="../lib/validationsInbound/customs/fruitsSelect.js" type="text/javascript"></script>
        <!-- Upload/Download Excel -->
        <script src="../plantillas/lib/upload_file.js" type="text/javascript"></script>
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    </body>
    <%
        } catch (NullPointerException e) {
            System.out.println("Error:" +e);
            out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            out.println("<script>window.close();</script>");
        } catch (Exception e) {
            out.println("Excepcion revise por favor! " + e);
            e.printStackTrace();
        }
    %>
</html>
