<%-- 
     Document   : iframeDetailsCustoms
    Created on : 24/10/2023, 11:55:23 AM
    Author     : grecendiz
--%>

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
<%@page import="com.tacts.evidencias.inbound.CreatExcelReporteCustoms"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" sizes="16x16" href="../plugins/images/favicon.png">
        <title>Personalizar Customs</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/css/style.default.css" id="theme-stylesheet">
        <!-- Table css -->
        <link href="<%=request.getContextPath()%>/lib/validationsInbound/customs/styleEvents.css" rel="stylesheet" type="text/css"/>
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
        <link href="<%=request.getContextPath()%>/lib/Loader/css/windowsLoad.css" rel="stylesheet" type="text/css"/>
        <!-- Descarga de Excel -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.5/xlsx.full.min.js"></script>
        <style>
            #contenedor {
                display: flex;
                flex-direction: row;
                flex-wrap: wrap;
            }

            #contenedor > div {
                width: 50%;
            }

            .columna {
                width:25%;
                float:right;
            }

            @media (max-width: 500px) {
                .columna {
                    width:auto;
                    float:none;
                }
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
                String idDivision = ownsession.getAttribute("cbdivcuenta").toString();
                String idBodega = ownsession.getAttribute("cbbodegaId").toString();
                ConsultasQuery fac = new ConsultasQuery();
                CreatExcelReporteCustoms excel = new CreatExcelReporteCustoms();

                //Parametros Generales
                String filterType = request.getParameter("filterType");       //Inicializar con 0
                String id = request.getParameter("id");                  //Inicializar con 0
                String pathExcelCustoms = "";
                
                //String fechaInicial = request.getParameter("fechaInicial");
                //String fechaFinal = request.getParameter("fechaFinal");
                String fechaInicial = "01/10/2023";
                String fechaFinal = "24/01/2024";

                String AgentType = "";
                String idPlantilla = "";
                String namePlantilla = "";
                String caramelo = "";
                String colorSemaforo = "";
                String sizeSemaforo = "";
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
                HashSet<String> list_pais_origen = new HashSet<String>();
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

                //Obtener el agente aduanal, id plantilla y nombre plantilla del usuario: 
                if (db.doDB(fac.consultarAgenteAduanalCustoms(UserId))) {
                    for (String[] rowA : db.getResultado()) {
                        AgentType = rowA[0];
                        idPlantilla = rowA[1];
                        namePlantilla = rowA[2];
                    }
                }
                
                //Generar caramelo: Opciones del multiselect
                String[] arrOfStr = id.split(",");
                for (String a : arrOfStr) {
                    caramelo += "'" + a + "',";
                }
                caramelo = caramelo.replaceAll(",$", "");

                //Generación de Excel
                //pathExcelCustoms = excel.crearAPartirDeArrayListReporteEventosAdmin(AgentType, filterType, id, fechaInicial, fechaFinal);
                System.out.println("Path File Excel: " + pathExcelCustoms);

        %>
        <div class="card-body">
            <div id="contenedor">

                <div class="col-md-12 card-header justify-content-between">
                    <div class="row">
                        <div class="col-md-12">
                            <h2 class="card-heading">Reporte Customs</h2>
                        </div>
                    </div>
                </div> 

                <div class="row">
                    <div class="columna">
                        <a href="#" id="downloadLinkEventCustoms">Descargar Archivo</a>
                    </div>
                </div
                <div style="text-align: right;">
                    <div id="example_filter" class="dataTables_filter">
                        <label><font class="redtext1">Busqueda:&nbsp; <input id="searchTerm" type="text" onkeyup="doSearch()" style="text-transform:uppercase;" data-mobile-responsive="true"/>
                            </font>&nbsp;&nbsp;
                        </label>
                    </div> 
                </div>
            </div>
            <!-- <br><label class="txtColor">Resolución de Pantalla</label>-->
            <div id="table-scroll" class="table-scroll">
                <table id="main-table" class="main-table" style="table-layout:fixed; width:1500%;">
                    <thead>
                        <tr>
                            <%                    if (AgentType.equals("4001") || AgentType.equals("4002") || AgentType.equals("4003") || AgentType.equals("4004") || AgentType.equals("4005") || AgentType.equals("4006")) { //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF   
                            %>                 
                            <th class="col-sm-1" class="font-titulo" style="background-color:#FFFFFF;"></th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#333F4F;">
                                <font size="1">Referencia AA</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('1')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_referenciaAA" name="col_referenciaAA">
                                    <%=list_referenciaAA%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Evento <strong style="color:red">*</strong></font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('2')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_evento" name="col_evento">
                                    <%=list_evento%>
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Responsable</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('3')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_responsable" name="col_responsable">
                                    <%=list_responsable%>      
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Final Destination</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('4')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_finalDestination" name="col_finalDestination">
                                    <%=list_finalDestination%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Brand-Division</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('5')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_brandDivision" name="col_brandDivision">
                                    <%=list_brandDivision%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Division</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('6')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_division" name="col_division">
                                    <%=list_division%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Shipment ID</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('7')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_shipmentId" name="col_shipmentId">
                                    <%=list_shipmentId%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Container</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('8')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_container" name="col_container">
                                    <%=list_containerId%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">BL/AWB/PRO</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('9')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_blAwbPro" name="col_blAwbPro">
                                    <%=list_blAwbPro%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">LoadType</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('10')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_loadType" name="col_loadType">
                                    <%=list_loadType%>     
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Quantity</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('11')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_quantity" name="col_quantity">
                                    <%=list_quantity%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">POD</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('12')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_pod" name="col_pod">
                                    <%=list_pod%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Est. Departure from POL</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('13')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_estDepartFromPol" name="col_estDepartFromPol">
                                    <%=list_estDepartFromPol%>        
                                </select> 
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">ETA REAL Port of Discharge</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('14')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_etaRealPortOfDischarge" name="col_etaRealPortOfDischarge">
                                    <%=list_etaRealPortOfDischarge%>      
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Est. Eta DC</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('15')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_estEtaDc" name="col_estEtaDc">
                                    <%=list_estEtaDc%>        
                                </select> 
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Inbound notification</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('16')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_inboundNotification" name="col_inboundNotification">
                                    <%=list_inboundNotification%>        
                                </select> 
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">POL</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('17')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_pol" name="col_pol">
                                    <%=list_pol%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">A.A.</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('18')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_aa" name="col_aa">
                                    <%=list_aa%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha Mes de Venta</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('19')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fechaMesVenta" name="col_fechaMesVenta">
                                    <%=list_fechaMesVenta%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Prioridad Si/No</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('20')"><i class="fa fa-search"></i></a>  
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_prioridad" name="col_prioridad">
                                    <%=list_prioridad%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">País Origen</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('21')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_pais_origen" name="col_pais_origen">
                                    <%=list_pais_origen%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Size Container</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('22')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_size_container" name="col_size_container">
                                    <%=list_size_container%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Valor USD</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('23')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_valor_usd" name="col_valor_usd">
                                    <%=list_valor_usd%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">ETA Port Of Discharge</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('24')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_eta_port_discharge" name="col_eta_port_discharge">
                                    <%=list_eta_port_discharge%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Agente Aduanal</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('25')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_agente_aduanal" name="col_agente_aduanal">
                                    <%=list_agente_aduanal%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Pedimento A1</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('26')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_pedimento_a1" name="col_pedimento_a1">
                                    <%=list_pedimento_a1%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Pedimento R1</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('27')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_pedimento_r1_1er" name="col_pedimento_r1_1er">
                                    <%=list_pedimento_r1_1er%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Motivo rectificación 1</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('28')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_motivo_rectificacion_1er" name="col_motivo_rectificacion_1er">
                                    <%=list_motivo_rectificacion_1er%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Pedimento R1 (2do)</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('29')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_pedimento_r1_2do" name="col_pedimento_r1_2do">
                                    <%=list_pedimento_r1_2do%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Motivo rectificación 2</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('30')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_motivo_rectificacion_2do" name="col_motivo_rectificacion_2do">
                                    <%=list_motivo_rectificacion_2do%>       
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Recepción Documentos</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('31')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_recepcion_doc" name="col_fecha_recepcion_doc">
                                    <%=list_fecha_recepcion_doc%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#e04141;">
                                <font size="1">Recinto</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('32')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_recinto" name="col_recinto">
                                    <%=list_recinto%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#e04141;">
                                <font size="1">Naviera / Forwarder</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('33')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_naviera" name="col_naviera">
                                    <%=list_naviera%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#e04141;">
                                <font size="1">Buque</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('34')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_buque" name="col_buque">
                                    <%=list_buque%>        
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Revalidación/Liberación de BL</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('35')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_revalidacion" name="col_fecha_revalidacion">
                                    <%=list_fecha_revalidacion%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Previo Origen</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('36')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_previo_origen" name="col_fecha_previo_origen">
                                    <%=list_fecha_previo_origen%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Previo en destino</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('37')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_previo_destino" name="col_fecha_previo_destino">
                                    <%=list_fecha_previo_destino%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Resultado Previo</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('38')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_resultado_previo" name="col_fecha_resultado_previo">
                                    <%=list_fecha_resultado_previo%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Proforma Final</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('39')"><i class="fa fa-search"></i></a>  
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_proforma_final" name="col_proforma_final">
                                    <%=list_proforma_final%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Requiere permiso</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('40')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_permiso" name="col_permiso">
                                    <%=list_permiso%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha envío Fichas/notas</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('41')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_envio" name="col_fecha_envio">
                                    <%=list_fecha_envio%>        
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fec. Recepción de permisos tramit.</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('42')"><i class="fa fa-search"></i></a>  
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_recepcion_perm" name="col_fecha_recepcion_perm">
                                    <%=list_fecha_recepcion_perm%>        
                                </select>
                            </th>

                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fec. Act Permisos (Inic Vigencia)</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('43')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_activacion_perm" name="col_fecha_activacion_perm">
                                    <%=list_fecha_activacion_perm%>        
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fec. Perm. Aut. (Fin de Vigencia)</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('44')"><i class="fa fa-search"></i></a>  
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_permisos_aut" name="col_fecha_permisos_aut">
                                    <%=list_fecha_permisos_aut%>        
                                </select>
                            </th>
                            <th class="col-sm-6" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Cuenta con CO para aplicar preferencia Arancelaria</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('45')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_co_pref_arancelaria" name="col_co_pref_arancelaria">
                                    <%=list_co_pref_arancelaria%>         
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Aplico Preferencia Arancelaria</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('46')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_aplic_pref_arancelaria" name="col_aplic_pref_arancelaria">
                                    <%=list_aplic_pref_arancelaria%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Requiere UVA</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('47')"><i class="fa fa-search"></i></a>  
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_req_uva" name="col_req_uva">
                                    <%=list_req_uva%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#a6a2a2;">
                                <font size="1">Requiere CA</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('48')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_req_ca" name="col_req_ca">
                                    <%=list_req_ca%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#a6a2a2;">
                                <font size="1">Fecha Recepción CA</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('49')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_recepcion_ca" name="col_fecha_recepcion_ca">
                                    <%=list_fecha_recepcion_ca%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#a6a2a2;">
                                <font size="1">Número de Constancia CA</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('50')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_num_constancia_ca" name="col_num_constancia_ca">
                                    <%=list_num_constancia_ca%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#a6a2a2;">
                                <font size="1">Monto CA</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('51')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_monto_ca" name="col_monto_ca">
                                    <%=list_monto_ca%>        
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Documentos Completos</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('52')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_doc_completos" name="col_fecha_doc_completos">
                                    <%=list_fecha_doc_completos%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Pago Pedimento</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('53')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_pago_pedimento" name="col_fecha_pago_pedimento">
                                    <%=list_fecha_pago_pedimento%>        
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Solicitud de transporte</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('54')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_solicitud_transporte" name="col_fecha_solicitud_transporte">
                                    <%=list_fecha_solicitud_transporte%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Modulacion</font>
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('55')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_modulacion" name="col_fecha_modulacion">
                                    <%=list_fecha_modulacion%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Modalidad</font>
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('56')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_modalidad" name="col_modalidad">
                                    <%=list_modalidad%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Resultado Modulacion</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('57')"><i class="fa fa-search"></i></a>  
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_resultado_modulacion" name="col_resultado_modulacion">
                                    <%=list_resultado_modulacion%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Reconocimiento</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('58')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_reconocimiento" name="col_fecha_reconocimiento">
                                    <%=list_fecha_reconocimiento%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Liberacion</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('59')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_liberacion" name="col_fecha_liberacion">
                                    <%=list_fecha_liberacion%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Sello Origen</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('60')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_sello_origen" name="col_sello_origen">
                                    <%=list_sello_origen%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Sello Final</font>
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('61')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_sello_final" name="col_sello_final">
                                    <%=list_sello_final%>         
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha de retencion por la autoridad</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('62')"><i class="fa fa-search"></i></a>  
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_retencion_aut" name="col_fecha_retencion_aut">
                                    <%=list_fecha_retencion_aut%>       
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fec. de liberacion por ret. de la aut.</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('63')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_liberacion_aut" name="col_fecha_liberacion_aut">
                                    <%=list_fecha_liberacion_aut%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Estatus de la operación</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('64')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_estatus_operacion" name="col_festatus_operacion">
                                    <%=list_estatus_operacion%>        
                                </select> 
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Motivo Atraso</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('65')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_motivo_atraso" name="col_motivo_atraso">
                                    <%=list_motivo_atraso%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Observaciones</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('66')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_observaciones" name="col_observaciones">
                                    <%=list_observaciones%>        
                                </select>
                            </th>
                            <%
                                }

                                if (AgentType.equals("4001") || AgentType.equals("4006")) { //LOGIX Y VF 
                            %>           
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Llegada a NOVA</font>
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('67')"><i class="fa fa-search"></i></a> 

                                <select multiple class="custom-select" id="col_llegada_a_nova" name="col_llegada_a_nova">
                                    <%=list_llegada_a_nova%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Llegada a Globe trade SD</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('68')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_llegada_a_globe_trade_sd" name="col_llegada_a_globe_trade_sd">
                                    <%=list_llegada_a_globe_trade_sd%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Archivo M</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('69')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_archivo_m" name="col_archivo_m">
                                    <%=list_archivo_m%>      
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha de Archivo M</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('70')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_archivo_m" name="col_fecha_archivo_m">
                                    <%=list_fecha_archivo_m%>       
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha Solicitud de Manipulacion</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('71')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_solicit_manip" name="col_fecha_solicit_manip">
                                    <%=list_fecha_solicit_manip%>        
                                </select>
                            </th>
                            <th class="col-sm-6" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha de vencimiento de Manipulacion</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('72')"><i class="fa fa-search"></i></a>   
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_vencim_manip" name="col_fecha_vencim_manip">
                                    <%=list_fecha_vencim_manip%>         
                                </select>
                            </th>
                            <th class="col-sm-6" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha confirmacion Clave de Pedimento</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('73')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_confirm_clave_pedim" name="col_fecha_confirm_clave_pedim">
                                    <%=list_fecha_confirm_clave_pedim%>        
                                </select>
                            </th>
                            <th class="col-sm-6" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha de Recepcion de Incrementables</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('74')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_recep_increment" name="col_fecha_recep_increment">
                                    <%=list_fecha_recep_increment%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">T&E</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('75')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_t_e" name="col_t_e">
                                    <%=list_t_e%>         
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha de Vencimiento del Inbound</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('76')"><i class="fa fa-search"></i></a>     
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_vencim_inbound" name="col_fecha_vencim_inbound">
                                    <%=list_fecha_vencim_inbound%>         
                                </select>
                            </th>
                            <%
                                }

                                if (AgentType.equals("4002") || AgentType.equals("4006")) {  //CUSA Y VF
                            %>            
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">No. BULTOS</font>
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('77')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_no_bultos" name="col_no_bultos">
                                    <%=list_no_bultos%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Peso (KG)</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('78')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_peso_kg" name="col_peso_kg">
                                    <%=list_peso_kg%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Transferencia</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('79')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_transferencia" name="col_transferencia">
                                    <%=list_transferencia%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha Inicio Etiquetado</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('80')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_inicio_etiquetado" name="col_fecha_inicio_etiquetado">
                                    <%=list_fecha_inicio_etiquetado%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha Termino Etiquetado</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('81')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fecha_termino_etiquetado" name="col_fecha_termino_etiquetado">
                                    <%=list_fecha_termino_etiquetado%>       
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Hora de termino Etiquetado</font>
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('82')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_hora_termino_etiquetado" name="col_hora_termino_etiquetado">
                                    <%=list_hora_termino_etiquetado%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Proveedor</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('83')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_proveedor" name="col_proveedor">
                                    <%=list_proveedor%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Proveedor de Carga</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('84')"><i class="fa fa-search"></i></a>  
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_proveedor_carga" name="col_proveedor_carga">
                                    <%=list_proveedor_carga%>        
                                </select>
                            </th>
                            <%
                                }
                            %>            
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">FY</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('85')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select multiple class="custom-select" id="col_fy" name="col_fy">
                                    <%=list_fy%>        
                                </select>
                            </th>
                        </tr>
                    </thead>
                    <tbody id="detalleCustom">
                        <%
                            if (db.doDB(fac.consultarEventosCustoms(AgentType, filterType, caramelo))) {
                                for (String[] row : db.getResultado()) {

                                    if (AgentType.equals("4001") || AgentType.equals("4002") || AgentType.equals("4003") || AgentType.equals("4004") || AgentType.equals("4005") || AgentType.equals("4006")) { //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF                          
                                        list_evento.add("<option value='" + row[0] + "'>" + row[0] + "</option>");
                                        list_referenciaAA.add("<option value='" + row[30] + "'>" + row[30] + "</option>");
                                        list_responsable.add("<option value='" + row[1] + "'>" + row[1] + "</option>");
                                        list_finalDestination.add("<option value='" + row[2] + "'>" + row[2] + "</option>");
                                        list_brandDivision.add("<option value='" + row[21] + "'>" + row[21] + "</option>");
                                        list_division.add("<option value='" + row[4] + "'>" + row[4] + "</option>");
                                        list_shipmentId.add("<option value='" + row[5] + "'>" + row[5] + "</option>");
                                        list_containerId.add("<option value='" + row[6] + "'>" + row[6] + "</option>");
                                        list_blAwbPro.add("<option value='" + row[7] + "'>" + row[7] + "</option>");
                                        list_loadType.add("<option value='" + row[8] + "'>" + row[8] + "</option>");
                                        list_quantity.add("<option value='" + row[9] + "'>" + row[9] + "</option>");
                                        list_pod.add("<option value='" + row[19] + "'>" + row[19] + "</option>");
                                        list_estDepartFromPol.add("<option value='" + row[11] + "'>" + row[11] + "</option>");
                                        list_etaRealPortOfDischarge.add("<option value='" + row[12] + "'>" + row[12] + "</option>");
                                        list_estEtaDc.add("<option value='" + row[22] + "'>" + row[22] + "</option>");
                                        list_inboundNotification.add("<option value='" + row[14] + "'>" + row[14] + "</option>");
                                        list_pol.add("<option value='" + row[20] + "'>" + row[20] + "</option>");
                                        list_aa.add("<option value='" + row[16] + "'>" + row[16] + "</option>");
                                        list_fechaMesVenta.add("<option value='" + row[28] + "'>" + row[28] + "</option>");
                                        list_prioridad.add("<option value='" + row[97] + "'>" + row[97] + "</option>");
                                        list_pais_origen.add("<option value='" + row[31] + "'>" + row[31] + "</option>");
                                        list_size_container.add("<option value='" + row[32] + "'>" + row[32] + "</option>");
                                        list_valor_usd.add("<option value='" + row[33] + "'>" + row[33] + "</option>");
                                        list_eta_port_discharge.add("<option value='" + row[34] + "'>" + row[34] + "</option>");
                                        list_agente_aduanal.add("<option value='" + row[35] + "'>" + row[35] + "</option>");
                                        list_pedimento_a1.add("<option value='" + row[36] + "'>" + row[36] + "</option>");
                                        list_pedimento_r1_1er.add("<option value='" + row[37] + "'>" + row[37] + "</option>");
                                        list_motivo_rectificacion_1er.add("<option value='" + row[38] + "'>" + row[38] + "</option>");
                                        list_pedimento_r1_2do.add("<option value='" + row[39] + "'>" + row[39] + "</option>");
                                        list_motivo_rectificacion_2do.add("<option value='" + row[40] + "'>" + row[40] + "</option>");
                                        list_fecha_recepcion_doc.add("<option value='" + row[41] + "'>" + row[41] + "</option>");
                                        list_recinto.add("<option value='" + row[42] + "'>" + row[42] + "</option>");
                                        list_naviera.add("<option value='" + row[43] + "'>" + row[43] + "</option>");
                                        list_buque.add("<option value='" + row[44] + "'>" + row[44] + "</option>");
                                        list_fecha_revalidacion.add("<option value='" + row[45] + "'>" + row[45] + "</option>");
                                        list_fecha_previo_origen.add("<option value='" + row[46] + "'>" + row[46] + "</option>");
                                        list_fecha_previo_destino.add("<option value='" + row[47] + "'>" + row[47] + "</option>");
                                        list_fecha_resultado_previo.add("<option value='" + row[48] + "'>" + row[48] + "</option>");
                                        list_proforma_final.add("<option value='" + row[49] + "'>" + row[49] + "</option>");
                                        list_permiso.add("<option value='" + row[50] + "'>" + row[50] + "</option>");
                                        list_fecha_envio.add("<option value='" + row[51] + "'>" + row[51] + "</option>");
                                        list_fecha_recepcion_perm.add("<option value='" + row[52] + "'>" + row[52] + "</option>");
                                        list_fecha_activacion_perm.add("<option value='" + row[53] + "'>" + row[53] + "</option>");
                                        list_fecha_permisos_aut.add("<option value='" + row[54] + "'>" + row[54] + "</option>");
                                        list_co_pref_arancelaria.add("<option value='" + row[55] + "'>" + row[55] + "</option>");
                                        list_aplic_pref_arancelaria.add("<option value='" + row[56] + "'>" + row[56] + "</option>");
                                        list_req_uva.add("<option value='" + row[57] + "'>" + row[57] + "</option>");
                                        list_req_ca.add("<option value='" + row[58] + "'>" + row[58] + "</option>");
                                        list_fecha_recepcion_ca.add("<option value='" + row[59] + "'>" + row[59] + "</option>");
                                        list_num_constancia_ca.add("<option value='" + row[60] + "'>" + row[60] + "</option>");
                                        list_monto_ca.add("<option value='" + row[61] + "'>" + row[61] + "</option>");
                                        list_fecha_doc_completos.add("<option value='" + row[62] + "'>" + row[62] + "</option>");
                                        list_fecha_pago_pedimento.add("<option value='" + row[63] + "'>" + row[63] + "</option>");
                                        list_fecha_solicitud_transporte.add("<option value='" + row[64] + "'>" + row[64] + "</option>");
                                        list_fecha_modulacion.add("<option value='" + row[65] + "'>" + row[65] + "</option>");
                                        list_modalidad.add("<option value='" + row[66] + "'>" + row[66] + "</option>");
                                        list_resultado_modulacion.add("<option value='" + row[67] + "'>" + row[67] + "</option>");
                                        list_fecha_reconocimiento.add("<option value='" + row[68] + "'>" + row[68] + "</option>");
                                        list_fecha_liberacion.add("<option value='" + row[69] + "'>" + row[69] + "</option>");
                                        list_sello_origen.add("<option value='" + row[70] + "'>" + row[70] + "</option>");
                                        list_sello_final.add("<option value='" + row[71] + "'>" + row[71] + "</option>");
                                        list_fecha_retencion_aut.add("<option value='" + row[72] + "'>" + row[72] + "</option>");
                                        list_fecha_liberacion_aut.add("<option value='" + row[73] + "'>" + row[73] + "</option>");
                                        list_estatus_operacion.add("<option value='" + row[74] + "'>" + row[74] + "</option>");
                                        list_motivo_atraso.add("<option value='" + row[75] + "'>" + row[75] + "</option>");
                                        list_observaciones.add("<option value='" + row[76] + "'>" + row[76] + "</option>");
                                    }

                                    if (AgentType.equals("4001") || AgentType.equals("4006")) { //LOGIX Y VF            
                                        list_llegada_a_nova.add("<option value='" + row[77] + "'>" + row[77] + "</option>");
                                        list_llegada_a_globe_trade_sd.add("<option value='" + row[78] + "'>" + row[78] + "</option>");
                                        list_archivo_m.add("<option value='" + row[79] + "'>" + row[79] + "</option>");
                                        list_fecha_archivo_m.add("<option value='" + row[80] + "'>" + row[80] + "</option>");
                                        list_fecha_solicit_manip.add("<option value='" + row[81] + "'>" + row[81] + "</option>");
                                        list_fecha_vencim_manip.add("<option value='" + row[82] + "'>" + row[82] + "</option>");
                                        list_fecha_confirm_clave_pedim.add("<option value='" + row[83] + "'>" + row[83] + "</option>");
                                        list_fecha_recep_increment.add("<option value='" + row[84] + "'>" + row[84] + "</option>");
                                        list_t_e.add("<option value='" + row[85] + "'>" + row[85] + "</option>");
                                        list_fecha_vencim_inbound.add("<option value='" + row[86] + "'>" + row[86] + "</option>");
                                    }

                                    if (AgentType.equals("4002") || AgentType.equals("4006")) {  //CUSA Y VF
                                        list_no_bultos.add("<option value='" + row[87] + "'>" + row[87] + "</option>");
                                        list_peso_kg.add("<option value='" + row[88] + "'>" + row[88] + "</option>");
                                        list_transferencia.add("<option value='" + row[89] + "'>" + row[89] + "</option>");
                                        list_fecha_inicio_etiquetado.add("<option value='" + row[90] + "'>" + row[90] + "</option>");
                                        list_fecha_termino_etiquetado.add("<option value='" + row[91] + "'>" + row[91] + "</option>");
                                        list_hora_termino_etiquetado.add("<option value='" + row[92] + "'>" + row[92] + "</option>");
                                        list_proveedor.add("<option value='" + row[93] + "'>" + row[93] + "</option>");
                                        list_proveedor_carga.add("<option value='" + row[94] + "'>" + row[94] + "</option>");
                                        list_fy.add("<option value='" + row[95] + "'>" + row[95] + "</option>");
                                    }


                        %>
                        <tr id="tr<%=cont%>">
                            <%
                                if (AgentType.equals("4001") || AgentType.equals("4002") || AgentType.equals("4003") || AgentType.equals("4004") || AgentType.equals("4005") || AgentType.equals("4006")) { //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF                                                    

                                    if (row[99].equals("3")) {
                                        colorSemaforo = request.getContextPath() + "/img/circle-green.png";
                                        sizeSemaforo = "100%";
                                    } else if (row[99].equals("2")) {
                                        colorSemaforo = request.getContextPath() + "/img/circle-yellow.png";
                                        sizeSemaforo = "80%";
                                    } else if (row[99].equals("1")) {
                                        colorSemaforo = request.getContextPath() + "/img/circle-red.png";
                                        sizeSemaforo = "60%";
                                    } else {
                                        colorSemaforo = request.getContextPath() + "/img/circle-green.png";
                                        sizeSemaforo = "100%";
                                    }
                            %>   
                            <th class="font-numero">                    <!--Semaforo -->
                    <center><img src="<%=colorSemaforo%>" width="<%=sizeSemaforo%>"/></center>
                    </th>
                    <th class="font-numero"> <%=row[30]%> </th>
                    <th class="font-numero"> <%=row[0]%>  </th>
                    <td class="font-numero"> <%=row[1]%>  </td>
                    <td class="font-numero"> <%=row[2]%>  </td>
                    <td class="font-numero"> <%=row[21]%> </td>
                    <td class="font-numero"> <%=row[4]%>  </td>
                    <td class="font-numero"> <%=row[5]%>  </td>
                    <td class="font-numero"> <%=row[6]%>  </td>
                    <td class="font-numero"> <%=row[7]%>  </td>
                    <td class="font-numero"> <%=row[8]%>  </td>
                    <td class="font-numero"> <%=row[9]%>  </td>
                    <td class="font-numero"> <%=row[19]%> </td>
                    <td class="font-numero"> <%=row[11]%> </td>
                    <td class="font-numero"> <%=row[12]%> </td>
                    <td class="font-numero"> <%=row[22]%> </td>
                    <td class="font-numero"> <%=row[14]%> </td>
                    <td class="font-numero"> <%=row[20]%> </td>
                    <td class="font-numero"> <%=row[16]%> </td>
                    <td class="font-numero"> <%=row[28]%> </td>
                    <td class="font-numero"> <%=row[97]%></td>                                               
                    <td class="font-numero"> <%=row[31]%></td>
                    <td class="font-numero"> <%=row[32]%></td>
                    <td class="font-numero"> <%=row[33]%></td>
                    <td class="font-numero"> <%=row[34]%></td>
                    <td class="font-numero"> <%=row[35]%></td>
                    <td class="font-numero"> <%=row[36]%></td>
                    <td class="font-numero"> <%=row[37]%></td>
                    <td class="font-numero"> <%=row[38]%></td>
                    <td class="font-numero"> <%=row[39]%></td>
                    <td class="font-numero"> <%=row[40]%></td>
                    <td class="font-numero"> <%=row[41]%></td>
                    <td class="font-numero"> <%=row[42]%></td>
                    <td class="font-numero"> <%=row[43]%></td>
                    <td class="font-numero"> <%=row[44]%></td>
                    <td class="font-numero"> <%=row[45]%></td>
                    <td class="font-numero"> <%=row[46]%></td>
                    <td class="font-numero"> <%=row[47]%></td>
                    <td class="font-numero"> <%=row[48]%></td>
                    <td class="font-numero"> <%=row[49]%></td>
                    <td class="font-numero"> <%=row[50]%></td>
                    <td class="font-numero"> <%=row[51]%></td>
                    <td class="font-numero"> <%=row[52]%></td>
                    <td class="font-numero"> <%=row[53]%></td>
                    <td class="font-numero"> <%=row[54]%></td>
                    <td class="font-numero"> <%=row[55]%></td>
                    <td class="font-numero"> <%=row[56]%></td>
                    <td class="font-numero"> <%=row[57]%></td>
                    <td class="font-numero"> <%=row[58]%></td>
                    <td class="font-numero"> <%=row[59]%></td>
                    <td class="font-numero"> <%=row[60]%></td>
                    <td class="font-numero"> <%=row[61]%></td>
                    <td class="font-numero"> <%=row[62]%></td>
                    <td class="font-numero"> <%=row[63]%></td>
                    <td class="font-numero"> <%=row[64]%></td>
                    <td class="font-numero"> <%=row[65]%></td>
                    <td class="font-numero"> <%=row[66]%></td>
                    <td class="font-numero"> <%=row[67]%> </td>
                    <td class="font-numero"> <%=row[68]%> </td>
                    <td class="font-numero"> <%=row[69]%> </td>
                    <td class="font-numero"> <%=row[70]%> </td>
                    <td class="font-numero"> <%=row[71]%> </td>
                    <td class="font-numero"> <%=row[72]%> </td>
                    <td class="font-numero"> <%=row[73]%> </td>
                    <td class="font-numero"><%=row[74]%></td>
                    <td class="font-numero"><%=row[75]%> </td>
                    <td class="font-numero"><%=row[76]%> </td>
                    <%
                        }

                        if (AgentType.equals("4001") || AgentType.equals("4006")) { //LOGIX Y VF
                    %>
                    <td class="font-numero"> <%=row[77]%> </td>
                    <td class="font-numero"> <%=row[78]%> </td>
                    <td class="font-numero"> <%=row[79]%> </td>
                    <td class="font-numero"> <%=row[80]%> </td>
                    <td class="font-numero"> <%=row[81]%> </td>
                    <td class="font-numero"> <%=row[82]%> </td>
                    <td class="font-numero"> <%=row[83]%> </td>
                    <td class="font-numero"> <%=row[84]%> </td>
                    <td class="font-numero"> <%=row[85]%> </td>
                    <td class="font-numero"> <%=row[86]%> </td>
                    <%
                        }

                        if (AgentType.equals("4002") || AgentType.equals("4006")) {  //CUSA Y VF
                    %>
                    <td class="font-numero"> <%=row[87]%> </td>
                    <td class="font-numero"> <%=row[88]%> </td>
                    <td class="font-numero"> <%=row[89]%> </td>
                    <td class="font-numero"> <%=row[90]%> </td>
                    <td class="font-numero"> <%=row[91]%> </td>
                    <td class="font-numero"> <%=row[92]%> </td>
                    <td class="font-numero"> <%=row[93]%> </td>
                    <td class="font-numero"> <%=row[94]%> </td>
                    <%
                        }
                    %>					
                    <td class="font-numero"> <%=row[95]%> </td>

                    </tr>
                    <%
                            cont++;
                        }
                    %>
                    <script>$(document).ready(function () {
                            jsRemoveWindowLoad();
                        });</script>
                        <%
                            }
                        %>      
                    </tbody>
                </table>
            </div>
            <input type="hidden" id="idAgenteAduanal" name="idAgenteAduanal" value="<%=AgentType%>">
            <input type="hidden" id="numCustoms" name="numCustoms" value="<%=cont%>">
            <br>
        </div>                     

        <script>
            var selectElement = document.getElementById("col_evento");
            var selectElement1 = document.getElementById("col_referenciaAA");
            var selectElement2 = document.getElementById("col_responsable");
            var selectElement3 = document.getElementById("col_finalDestination");
            var selectElement4 = document.getElementById("col_brandDivision");
            var selectElement5 = document.getElementById("col_division");
            var selectElement6 = document.getElementById("col_shipmentId");
            var selectElement7 = document.getElementById("col_container");
            var selectElement8 = document.getElementById("col_blAwbPro");
            var selectElement9 = document.getElementById("col_loadType");
            var selectElement10 = document.getElementById("col_quantity");
            var selectElement11 = document.getElementById("col_pod");
            var selectElement12 = document.getElementById("col_estDepartFromPol");
            var selectElement13 = document.getElementById("col_etaRealPortOfDischarge");
            var selectElement14 = document.getElementById("col_estEtaDc");
            var selectElement15 = document.getElementById("col_inboundNotification");
            var selectElement16 = document.getElementById("col_pol");
            var selectElement17 = document.getElementById("col_aa");
            var selectElement18 = document.getElementById("col_fechaMesVenta");
            var selectElement19 = document.getElementById("col_prioridad");
            var selectElement20 = document.getElementById("col_pais_origen");
            var selectElement21 = document.getElementById("col_size_container");
            var selectElement22 = document.getElementById("col_valor_usd");
            var selectElement23 = document.getElementById("col_eta_port_discharge");
            var selectElement24 = document.getElementById("col_agente_aduanal");
            var selectElement25 = document.getElementById("col_pedimento_a1");
            var selectElement26 = document.getElementById("col_pedimento_r1_1er");
            var selectElement27 = document.getElementById("col_motivo_rectificacion_1er");
            var selectElement28 = document.getElementById("col_pedimento_r1_2do");
            var selectElement29 = document.getElementById("col_motivo_rectificacion_2do");
            var selectElement30 = document.getElementById("col_fecha_recepcion_doc");
            var selectElement31 = document.getElementById("col_recinto");
            var selectElement32 = document.getElementById("col_naviera");
            var selectElement33 = document.getElementById("col_buque");
            var selectElement34 = document.getElementById("col_fecha_revalidacion");
            var selectElement35 = document.getElementById("col_fecha_previo_origen");
            var selectElement36 = document.getElementById("col_fecha_previo_destino");
            var selectElement37 = document.getElementById("col_fecha_resultado_previo");
            var selectElement38 = document.getElementById("col_proforma_final");
            var selectElement39 = document.getElementById("col_permiso");
            var selectElement40 = document.getElementById("col_fecha_envio");
            var selectElement41 = document.getElementById("col_fecha_recepcion_perm");
            var selectElement42 = document.getElementById("col_fecha_activacion_perm");
            var selectElement43 = document.getElementById("col_fecha_permisos_aut");
            var selectElement44 = document.getElementById("col_co_pref_arancelaria");
            var selectElement45 = document.getElementById("col_aplic_pref_arancelaria");
            var selectElement46 = document.getElementById("col_req_uva");
            var selectElement47 = document.getElementById("col_req_ca");
            var selectElement48 = document.getElementById("col_fecha_recepcion_ca");
            var selectElement49 = document.getElementById("col_num_constancia_ca");
            var selectElement50 = document.getElementById("col_monto_ca");
            var selectElement51 = document.getElementById("col_fecha_doc_completos");
            var selectElement52 = document.getElementById("col_fecha_pago_pedimento");
            var selectElement53 = document.getElementById("col_fecha_solicitud_transporte");
            var selectElement54 = document.getElementById("col_fecha_modulacion");
            var selectElement55 = document.getElementById("col_modalidad");
            var selectElement56 = document.getElementById("col_resultado_modulacion");
            var selectElement57 = document.getElementById("col_fecha_reconocimiento");
            var selectElement58 = document.getElementById("col_fecha_liberacion");
            var selectElement59 = document.getElementById("col_sello_origen");
            var selectElement60 = document.getElementById("col_sello_final");
            var selectElement61 = document.getElementById("col_fecha_retencion_aut");
            var selectElement62 = document.getElementById("col_fecha_liberacion_aut");
            var selectElement63 = document.getElementById("col_estatus_operacion");
            var selectElement64 = document.getElementById("col_motivo_atraso");
            var selectElement65 = document.getElementById("col_observaciones");
            var selectElement66 = document.getElementById("col_llegada_a_nova");
            var selectElement67 = document.getElementById("col_llegada_a_globe_trade_sd");
            var selectElement68 = document.getElementById("col_archivo_m");
            var selectElement69 = document.getElementById("col_fecha_archivo_m");
            var selectElement70 = document.getElementById("col_fecha_solicit_manip");
            var selectElement71 = document.getElementById("col_fecha_vencim_manip");
            var selectElement72 = document.getElementById("col_fecha_confirm_clave_pedim");
            var selectElement73 = document.getElementById("col_fecha_recep_increment");
            var selectElement74 = document.getElementById("col_t_e");
            var selectElement75 = document.getElementById("col_fecha_vencim_inbound");
            var selectElement76 = document.getElementById("col_no_bultos");
            var selectElement77 = document.getElementById("col_peso_kg");
            var selectElement78 = document.getElementById("col_transferencia");
            var selectElement79 = document.getElementById("col_fecha_inicio_etiquetado");
            var selectElement80 = document.getElementById("col_fecha_termino_etiquetado");
            var selectElement81 = document.getElementById("col_hora_termino_etiquetado");
            var selectElement82 = document.getElementById("col_proveedor");
            var selectElement83 = document.getElementById("col_proveedor_carga");
            var selectElement84 = document.getElementById("col_fy");
            selectElement.innerHTML = "<%=list_evento%>";
            selectElement1.innerHTML = "<%=list_referenciaAA%>";
            selectElement2.innerHTML = "<%=list_responsable%>";
            selectElement3.innerHTML = "<%=list_finalDestination%>";
            selectElement4.innerHTML = "<%=list_brandDivision%>";
            selectElement5.innerHTML = "<%=list_division%>";
            selectElement6.innerHTML = "<%=list_shipmentId%>";
            selectElement7.innerHTML = "<%=list_containerId%>";
            selectElement8.innerHTML = "<%=list_blAwbPro%>";
            selectElement9.innerHTML = "<%=list_loadType%>";
            selectElement10.innerHTML = "<%=list_quantity%>";
            selectElement11.innerHTML = "<%=list_pod%>";
            selectElement12.innerHTML = "<%=list_estDepartFromPol%>";
            selectElement13.innerHTML = "<%=list_etaRealPortOfDischarge%>";
            selectElement14.innerHTML = "<%=list_estEtaDc%>";
            selectElement15.innerHTML = "<%=list_inboundNotification%>";
            selectElement16.innerHTML = "<%=list_pol%>";
            selectElement17.innerHTML = "<%=list_aa%>";
            selectElement18.innerHTML = "<%=list_fechaMesVenta%>";
            selectElement19.innerHTML = "<%=list_prioridad%>";
            selectElement20.innerHTML = "<%=list_pais_origen%>";
            selectElement21.innerHTML = "<%=list_size_container%>";
            selectElement22.innerHTML = "<%=list_valor_usd%>";
            selectElement23.innerHTML = "<%=list_eta_port_discharge%>";
            selectElement24.innerHTML = "<%=list_agente_aduanal%>";
            selectElement25.innerHTML = "<%=list_pedimento_a1%>";
            selectElement26.innerHTML = "<%=list_pedimento_r1_1er%>";
            selectElement27.innerHTML = "<%=list_motivo_rectificacion_1er%>";
            selectElement28.innerHTML = "<%=list_pedimento_r1_2do%>";
            selectElement29.innerHTML = "<%=list_motivo_rectificacion_2do%>";
            selectElement30.innerHTML = "<%=list_fecha_recepcion_doc%>";
            selectElement31.innerHTML = "<%=list_recinto%>";
            selectElement32.innerHTML = "<%=list_naviera%>";
            selectElement33.innerHTML = "<%=list_buque%>";
            selectElement34.innerHTML = "<%=list_fecha_revalidacion%>";
            selectElement35.innerHTML = "<%=list_fecha_previo_origen%>";
            selectElement36.innerHTML = "<%=list_fecha_previo_destino%>";
            selectElement37.innerHTML = "<%=list_fecha_resultado_previo%>";
            selectElement38.innerHTML = "<%=list_proforma_final%>";
            selectElement39.innerHTML = "<%=list_permiso%>";
            selectElement40.innerHTML = "<%=list_fecha_envio%>";
            selectElement41.innerHTML = "<%=list_fecha_recepcion_perm%>";
            selectElement42.innerHTML = "<%=list_fecha_activacion_perm%>";
            selectElement43.innerHTML = "<%=list_fecha_permisos_aut%>";
            selectElement44.innerHTML = "<%=list_co_pref_arancelaria%>";
            selectElement45.innerHTML = "<%=list_aplic_pref_arancelaria%>";
            selectElement46.innerHTML = "<%=list_req_uva%>";
            selectElement47.innerHTML = "<%=list_req_ca%>";
            selectElement48.innerHTML = "<%=list_fecha_recepcion_ca%>";
            selectElement49.innerHTML = "<%=list_num_constancia_ca%>";
            selectElement50.innerHTML = "<%=list_monto_ca%>";
            selectElement51.innerHTML = "<%=list_fecha_doc_completos%>";
            selectElement52.innerHTML = "<%=list_fecha_pago_pedimento%>";
            selectElement53.innerHTML = "<%=list_fecha_solicitud_transporte%>";
            selectElement54.innerHTML = "<%=list_fecha_modulacion%>";
            selectElement55.innerHTML = "<%=list_modalidad%>";
            selectElement56.innerHTML = "<%=list_resultado_modulacion%>";
            selectElement57.innerHTML = "<%=list_fecha_reconocimiento%>";
            selectElement58.innerHTML = "<%=list_fecha_liberacion%>";
            selectElement59.innerHTML = "<%=list_sello_origen%>";
            selectElement60.innerHTML = "<%=list_sello_final%>";
            selectElement61.innerHTML = "<%=list_fecha_retencion_aut%>";
            selectElement62.innerHTML = "<%=list_fecha_liberacion_aut%>";
            selectElement63.innerHTML = "<%=list_estatus_operacion%>";
            selectElement64.innerHTML = "<%=list_motivo_atraso%>";
            selectElement65.innerHTML = "<%=list_observaciones%>";
            selectElement66.innerHTML = "<%=list_llegada_a_nova%>";
            selectElement67.innerHTML = "<%=list_llegada_a_globe_trade_sd%>";
            selectElement68.innerHTML = "<%=list_archivo_m%>";
            selectElement69.innerHTML = "<%=list_fecha_archivo_m%>";
            selectElement70.innerHTML = "<%=list_fecha_solicit_manip%>";
            selectElement71.innerHTML = "<%=list_fecha_vencim_manip%>";
            selectElement72.innerHTML = "<%=list_fecha_confirm_clave_pedim%>";
            selectElement73.innerHTML = "<%=list_fecha_recep_increment%>";
            selectElement74.innerHTML = "<%=list_t_e%>";
            selectElement75.innerHTML = "<%=list_fecha_vencim_inbound%>";
            selectElement76.innerHTML = "<%=list_no_bultos%>";
            selectElement77.innerHTML = "<%=list_peso_kg%>";
            selectElement78.innerHTML = "<%=list_transferencia%>";
            selectElement79.innerHTML = "<%=list_fecha_inicio_etiquetado%>";
            selectElement80.innerHTML = "<%=list_fecha_termino_etiquetado%>";
            selectElement81.innerHTML = "<%=list_hora_termino_etiquetado%>";
            selectElement82.innerHTML = "<%=list_proveedor%>";
            selectElement83.innerHTML = "<%=list_proveedor_carga%>";
            selectElement84.innerHTML = "<%=list_fy%>";
        </script>
        <script>
            document.getElementById("downloadLinkEventCustoms").addEventListener("click", function() {
                // Realizar una solicitud al servlet para descargar el archivo
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "<%=request.getContextPath()%>/DownloadExcelEventsCustoms", true);
                xhr.responseType = "blob";

                xhr.onload = function() {
                    // Crear un enlace y hacer clic en él para iniciar la descarga
                    var blob = new Blob([xhr.response], { type: "application/octet-stream" });
                    var link = document.createElement("a");
                    link.href = window.URL.createObjectURL(blob);
                    link.download = "ReporteEventosCustoms.xls";
                    document.body.appendChild(link);
                    link.click();
                    document.body.removeChild(link);
                };

                xhr.send();
            });
        </script>
        <!-- JavaScript files-->
        <script src="<%=request.getContextPath()%>/lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Sweetalert -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
        <!-- Actions js -->
        <script src="<%=request.getContextPath()%>/lib/validationsInbound/customs/customsForms.js" type="text/javascript"></script>
        <!-- fruitsSelect value -->
        <script src="<%=request.getContextPath()%>/lib/validationsInbound/customs/fruitsSelect.js" type="text/javascript"></script>
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

    </body>
    <%
        } catch (NullPointerException e) {
            System.out.println("Error:" + e);
            out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            out.println("<script>window.close();</script>");
        } catch (Exception e) {
            out.println("Excepcion revise por favor! " + e);
            e.printStackTrace();
        }
    %>
</html>
