<%-- 
    Document   : iframeDetailsCustoms
    Created on : 2/06/2023, 03:43:40 AM
    Author     : luis_
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" sizes="16x16" href="../../plugins/images/favicon.png">
        <title>Reporte Customs</title>
        <link rel="stylesheet" href="../../lib/css/style.default.css" id="theme-stylesheet">
        <!-- Table css -->
        <link href="../../lib/validationsInbound/customs/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- Multiselect -->
        <link href="../../lib/Multiselect/css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- jQuery/show modal -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!-- Window load -->
        <link href="../../lib/Loader/css/windowsLoad.css" rel="stylesheet" type="text/css"/>
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
                                
                //Parametros Generales
                String filterType = request.getParameter("filterType");       //Inicializar con 0
                String id = request.getParameter("id");     
   
                String AgentType = ""; 
                String caramelo = "";
                int cont = 0; 
            
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

                //Obtener el agente aduanal, id plantilla y nombre plantilla del usuario: 
                if (db.doDB(fac.consultarAgenteAduanalCustoms(UserId))) {
                    for (String[] rowA : db.getResultado()) {
                        AgentType = rowA[0]; 
                    }
                }
                
                if (db.doDB(fac.consultarMultiselectCustoms(AgentType))) {
                    for (String[] row : db.getResultado()) {
                        
                        if(AgentType.equals("4001")||AgentType.equals("4002")||AgentType.equals("4003")||AgentType.equals("4004")||AgentType.equals("4005")||AgentType.equals("4006")){ //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF 
                                    list_evento.add("<option value=\""+row[0]+"\">"+row[0]+"</option>");
                                    list_referenciaAA.add("<option value=\""+row[30]+"\">"+row[30]+"</option>");
                                    list_responsable.add("<option value=\""+row[1]+"\">"+row[1]+"</option>");
                                    list_finalDestination.add("<option value=\""+row[2]+"\">"+row[2]+"</option>");
                                    list_brandDivision.add("<option value=\""+row[21]+"\">"+row[21]+"</option>");
                                    list_division.add("<option value=\""+row[4]+"\">"+row[4]+"</option>");
                                    list_shipmentId.add("<option value=\""+row[5]+"\">"+row[5]+"</option>");
                                    list_containerId.add("<option value=\""+row[6]+"\">"+row[6]+"</option>");
                                    list_blAwbPro.add("<option value=\""+row[7]+"\">"+row[7]+"</option>");
                                    list_loadType.add("<option value=\""+row[8]+"\">"+row[8]+"</option>");
                                    list_quantity.add("<option value=\""+row[9]+"\">"+row[9]+"</option>");
                                    list_pod.add("<option value=\""+row[19]+"\">"+row[19]+"</option>");
                                    list_estDepartFromPol.add("<option value=\""+row[11]+"\">"+row[11]+"</option>");
                                    list_etaRealPortOfDischarge.add("<option value=\""+row[12]+"\">"+row[12]+"</option>");
                                    list_estEtaDc.add("<option value=\""+row[22]+"\">"+row[22]+"</option>");
                                    list_inboundNotification.add("<option value=\""+row[14]+"\">"+row[14]+"</option>"); 
                                    list_pol.add("<option value=\""+row[20]+"\">"+row[20]+"</option>");
                                    list_aa.add("<option value=\""+row[16]+"\">"+row[16]+"</option>");
                                    list_fechaMesVenta.add("<option value=\""+row[28]+"\">"+row[28]+"</option>");
                                    list_prioridad.add("<option value=\""+row[97]+"\">"+row[97]+"</option>");
                                    list_pais_origen.add("<option value=\""+row[31]+"\">"+row[31]+"</option>");   
                                    list_size_container.add("<option value=\""+row[32]+"\">"+row[32]+"</option>");    
                                    list_valor_usd.add("<option value=\""+row[33]+"\">"+row[33]+"</option>");                 
                                    list_eta_port_discharge.add("<option value=\""+row[34]+"\">"+row[34]+"</option>");         
                                    list_agente_aduanal.add("<option value=\""+row[35]+"\">"+row[35]+"</option>");              
                                    list_pedimento_a1.add("<option value=\""+row[36]+"\">"+row[36]+"</option>");              
                                    list_pedimento_r1_1er.add("<option value=\""+row[37]+"\">"+row[37]+"</option>");          
                                    list_motivo_rectificacion_1er.add("<option value=\""+row[38]+"\">"+row[38]+"</option>");    
                                    list_pedimento_r1_2do.add("<option value=\""+row[39]+"\">"+row[39]+"</option>");         
                                    list_motivo_rectificacion_2do.add("<option value=\""+row[40]+"\">"+row[40]+"</option>");    
                                    list_fecha_recepcion_doc.add("<option value=\""+row[41]+"\">"+row[41]+"</option>");    
                                    list_recinto.add("<option value=\""+row[42]+"\">"+row[42]+"</option>");
                                    list_naviera.add("<option value=\""+row[43]+"\">"+row[43]+"</option>");
                                    list_buque.add("<option value=\""+row[44]+"\">"+row[44]+"</option>");
                                    list_fecha_revalidacion.add("<option value=\""+row[45]+"\">"+row[45]+"</option>");      
                                    list_fecha_previo_origen.add("<option value=\""+row[46]+"\">"+row[46]+"</option>");     
                                    list_fecha_previo_destino.add("<option value=\""+row[47]+"\">"+row[47]+"</option>");   
                                    list_fecha_resultado_previo.add("<option value=\""+row[48]+"\">"+row[48]+"</option>");   
                                    list_proforma_final.add("<option value=\""+row[49]+"\">"+row[49]+"</option>");        
                                    list_permiso.add("<option value=\""+row[50]+"\">"+row[50]+"</option>");               
                                    list_fecha_envio.add("<option value=\""+row[51]+"\">"+row[51]+"</option>");             
                                    list_fecha_recepcion_perm.add("<option value=\""+row[52]+"\">"+row[52]+"</option>");
                                    list_fecha_activacion_perm.add("<option value=\""+row[53]+"\">"+row[53]+"</option>");    
                                    list_fecha_permisos_aut.add("<option value=\""+row[54]+"\">"+row[54]+"</option>");     
                                    list_co_pref_arancelaria.add("<option value=\""+row[55]+"\">"+row[55]+"</option>");    
                                    list_aplic_pref_arancelaria.add("<option value=\""+row[56]+"\">"+row[56]+"</option>");  
                                    list_req_uva.add("<option value=\""+row[57]+"\">"+row[57]+"</option>");   
                                    list_req_ca.add("<option value=\""+row[58]+"\">"+row[58]+"</option>");    
                                    list_fecha_recepcion_ca.add("<option value=\""+row[59]+"\">"+row[59]+"</option>"); 
                                    list_num_constancia_ca.add("<option value=\""+row[60]+"\">"+row[60]+"</option>");   
                                    list_monto_ca.add("<option value=\""+row[61]+"\">"+row[61]+"</option>");
                                    list_fecha_doc_completos.add("<option value=\""+row[62]+"\">"+row[62]+"</option>");  
                                    list_fecha_pago_pedimento.add("<option value=\""+row[63]+"\">"+row[63]+"</option>");     
                                    list_fecha_solicitud_transporte.add("<option value=\""+row[64]+"\">"+row[64]+"</option>");
                                    list_fecha_modulacion.add("<option value=\""+row[65]+"\">"+row[65]+"</option>");   
                                    list_modalidad.add("<option value=\""+row[66]+"\">"+row[66]+"</option>");          
                                    list_resultado_modulacion.add("<option value=\""+row[67]+"\">"+row[67]+"</option>");    
                                    list_fecha_reconocimiento.add("<option value=\""+row[68]+"\">"+row[68]+"</option>");     
                                    list_fecha_liberacion.add("<option value=\""+row[69]+"\">"+row[69]+"</option>");       
                                    list_sello_origen.add("<option value=\""+row[70]+"\">"+row[70]+"</option>");            
                                    list_sello_final.add("<option value=\""+row[71]+"\">"+row[71]+"</option>");      
                                    list_fecha_retencion_aut.add("<option value=\""+row[72]+"\">"+row[72]+"</option>");     
                                    list_fecha_liberacion_aut.add("<option value=\""+row[73]+"\">"+row[73]+"</option>");   
                                    list_estatus_operacion.add("<option value=\""+row[74]+"\">"+row[74]+"</option>");      
                                    list_motivo_atraso.add("<option value=\""+row[75]+"\">"+row[75]+"</option>");          
                                    list_observaciones.add("<option value=\""+row[76]+"\">"+row[76]+"</option>"); 
                        }
                        
                        if(AgentType.equals("4001")||AgentType.equals("4006")){ //LOGIX Y VF            
                                    list_llegada_a_nova.add("<option value=\""+row[77]+"\">"+row[77]+"</option>");
                                    list_llegada_a_globe_trade_sd.add("<option value=\""+row[78]+"\">"+row[78]+"</option>");
                                    list_archivo_m.add("<option value=\""+row[79]+"\">"+row[79]+"</option>");
                                    list_fecha_archivo_m.add("<option value=\""+row[80]+"\">"+row[80]+"</option>");
                                    list_fecha_solicit_manip.add("<option value=\""+row[81]+"\">"+row[81]+"</option>");
                                    list_fecha_vencim_manip.add("<option value=\""+row[82]+"\">"+row[82]+"</option>");
                                    list_fecha_confirm_clave_pedim.add("<option value=\""+row[83]+"\">"+row[83]+"</option>");
                                    list_fecha_recep_increment.add("<option value=\""+row[84]+"\">"+row[84]+"</option>");
                                    list_t_e.add("<option value=\""+row[85]+"\">"+row[85]+"</option>");
                                    list_fecha_vencim_inbound.add("<option value=\""+row[86]+"\">"+row[86]+"</option>");
                        }

                        if(AgentType.equals("4002")||AgentType.equals("4006")){  //CUSA Y VF
                                    list_no_bultos.add("<option value=\""+row[87]+"\">"+row[87]+"</option>");
                                    list_peso_kg.add("<option value=\""+row[88]+"\">"+row[88]+"</option>");
                                    list_transferencia.add("<option value=\""+row[89]+"\">"+row[89]+"</option>");
                                    list_fecha_inicio_etiquetado.add("<option value=\""+row[90]+"\">"+row[90]+"</option>");
                                    list_fecha_termino_etiquetado.add("<option value=\""+row[91]+"\">"+row[91]+"</option>");
                                    list_hora_termino_etiquetado.add("<option value=\""+row[92]+"\">"+row[92]+"</option>");
                                    list_proveedor.add("<option value=\""+row[93]+"\">"+row[93]+"</option>");
                                    list_proveedor_carga.add("<option value=\""+row[94]+"\">"+row[94]+"</option>");
                                    list_fy.add("<option value=\""+row[95]+"\">"+row[95]+"</option>");
                        }
                        
                    }
                }
                
                //Generar caramelo: Opciones del multiselect
                String[] arrOfStr = id.split(",");
                for (String a : arrOfStr) {
                    caramelo += "'" + a + "',";
                }
                caramelo = caramelo.replaceAll(",$", "");
        %>
        <div class="card-body">
            <div class="row">
                <center><button onclick="descargar()" style="background: transparent;border: 0;"><img src="../../img/excel.png"  width="8%" alt="Exportar"/></button></center>
            </div>
            <div style="text-align: right;">
                <label><font class="redtext1">Busqueda:&nbsp; <input id="searchReporteCustoms" type="text" onkeyup="doSearch()" style="text-transform:uppercase;" data-mobile-responsive="true"/></font></label>
            </div>
            <br>
            <div id="table-scroll" class="table-scroll"  style="height:800px;">
                <table id="main-table" class="main-table" style="table-layout:fixed; width:1500%;">
                    <thead>
                        <tr>
                <%            
                    if(AgentType.equals("4001")||AgentType.equals("4002")||AgentType.equals("4003")||AgentType.equals("4004")||AgentType.equals("4005")||AgentType.equals("4006")){ //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF  
                %>     
                            <th class="col-sm-1" class="font-titulo" style="background-color:#FFFFFF;"></th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#333F4F;">
                                <font size="1">Referencia AA</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('1')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_referenciaAA" name="col_referenciaAA">
                                    <%=list_referenciaAA%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Evento <strong style="color:red">*</strong></font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('2')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_evento" name="col_evento">
                                    <%=list_evento%>
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Responsable</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('3')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_responsable" name="col_responsable">
                                    <%=list_responsable%>      
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Final Destination</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('4')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_finalDestination" name="col_finalDestination">
                                    <%=list_finalDestination%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Brand-Division</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('5')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_brandDivision" name="col_brandDivision">
                                    <%=list_brandDivision%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Division</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('6')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_division" name="col_division">
                                    <%=list_division%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Shipment ID</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('7')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_shipmentId" name="col_shipmentId">
                                    <%=list_shipmentId%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Container</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('8')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_container" name="col_container">
                                    <%=list_containerId%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">BL/AWB/PRO</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('9')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_blAwbPro" name="col_blAwbPro">
                                    <%=list_blAwbPro%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">LoadType</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('10')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_loadType" name="col_loadType">
                                    <%=list_loadType%>     
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Quantity</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('11')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_quantity" name="col_quantity">
                                    <%=list_quantity%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">POD</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('12')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_pod" name="col_pod">
                                    <%=list_pod%>        
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Est. Departure from POL</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('13')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_estDepartFromPol" name="col_estDepartFromPol">
                                    <%=list_estDepartFromPol%>        
                                </select> 
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">ETA REAL Port of Discharge</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('14')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_etaRealPortOfDischarge" name="col_etaRealPortOfDischarge">
                                    <%=list_etaRealPortOfDischarge%>      
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Est. Eta DC</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('15')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_estEtaDc" name="col_estEtaDc">
                                    <%=list_estEtaDc%>        
                                </select> 
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Inbound notification</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('16')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_inboundNotification" name="col_inboundNotification">
                                    <%=list_inboundNotification%>        
                                </select> 
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">POL</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('17')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_pol" name="col_pol">
                                    <%=list_pol%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">A.A.</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('18')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_aa" name="col_aa">
                                    <%=list_aa%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha Mes de Venta</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('19')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fechaMesVenta" name="col_fechaMesVenta">
                                    <%=list_fechaMesVenta%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Prioridad Si/No</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('20')"><i class="fa fa-search"></i></a>  
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_prioridad" name="col_prioridad">
                                    <%=list_prioridad%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">País Origen</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('21')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_pais_origen" name="col_pais_origen">
                                    <%=list_pais_origen%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Size Container</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('22')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_size_container" name="col_size_container">
                                    <%=list_size_container%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Valor USD</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('23')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_valor_usd" name="col_valor_usd">
                                    <%=list_valor_usd%>         
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">ETA Port Of Discharge</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('24')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_eta_port_discharge" name="col_eta_port_discharge">
                                    <%=list_eta_port_discharge%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Agente Aduanal</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('25')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_agente_aduanal" name="col_agente_aduanal">
                                    <%=list_agente_aduanal%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Pedimento A1</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('26')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_pedimento_a1" name="col_pedimento_a1">
                                    <%=list_pedimento_a1%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Pedimento R1</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('27')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_pedimento_r1_1er" name="col_pedimento_r1_1er">
                                    <%=list_pedimento_r1_1er%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Motivo rectificación 1</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('28')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_motivo_rectificacion_1er" name="col_motivo_rectificacion_1er">
                                    <%=list_motivo_rectificacion_1er%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Pedimento R1 (2do)</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('29')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_pedimento_r1_2do" name="col_pedimento_r1_2do">
                                    <%=list_pedimento_r1_2do%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Motivo rectificación 2</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('30')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_motivo_rectificacion_2do" name="col_motivo_rectificacion_2do">
                                    <%=list_motivo_rectificacion_2do%>       
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Recepción Documentos</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('31')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_recepcion_doc" name="col_fecha_recepcion_doc">
                                    <%=list_fecha_recepcion_doc%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#e04141;">
                                <font size="1">Recinto</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('32')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_recinto" name="col_recinto">
                                    <%=list_recinto%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#e04141;">
                                <font size="1">Naviera / Forwarder</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('33')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_naviera" name="col_naviera">
                                    <%=list_naviera%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#e04141;">
                                <font size="1">Buque</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('34')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_buque" name="col_buque">
                                    <%=list_buque%>        
                                </select>
                            </th>
                            <th class="col-sm-6" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Revalidación/Liberación de BL</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('35')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_revalidacion" name="col_fecha_revalidacion">
                                    <%=list_fecha_revalidacion%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Previo Origen</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('36')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_previo_origen" name="col_fecha_previo_origen">
                                    <%=list_fecha_previo_origen%>        
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Previo en destino</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('37')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_previo_destino" name="col_fecha_previo_destino">
                                    <%=list_fecha_previo_destino%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Resultado Previo</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('38')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_resultado_previo" name="col_fecha_resultado_previo">
                                    <%=list_fecha_resultado_previo%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Proforma Final</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('39')"><i class="fa fa-search"></i></a>  
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_proforma_final" name="col_proforma_final">
                                    <%=list_proforma_final%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Requiere permiso</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('40')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_permiso" name="col_permiso">
                                    <%=list_permiso%>        
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha envío Fichas/notas</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('41')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_envio" name="col_fecha_envio">
                                    <%=list_fecha_envio%>        
                                </select>
                            </th>
                            <th class="col-sm-6" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fec. Recepción de permisos tramit.</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('42')"><i class="fa fa-search"></i></a>  
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_recepcion_perm" name="col_fecha_recepcion_perm">
                                    <%=list_fecha_recepcion_perm%>        
                                </select>
                            </th>

                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fec. Act Permisos (Inic Vigencia)</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('43')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_activacion_perm" name="col_fecha_activacion_perm">
                                    <%=list_fecha_activacion_perm%>        
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fec. Perm. Aut. (Fin de Vigencia)</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('44')"><i class="fa fa-search"></i></a>  
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_permisos_aut" name="col_fecha_permisos_aut">
                                    <%=list_fecha_permisos_aut%>        
                                </select>
                            </th>
                            <th class="col-sm-7" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Cuenta con CO para aplicar preferencia Arancelaria</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('45')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_co_pref_arancelaria" name="col_co_pref_arancelaria">
                                    <%=list_co_pref_arancelaria%>         
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Aplico Preferencia Arancelaria</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('46')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_aplic_pref_arancelaria" name="col_aplic_pref_arancelaria">
                                    <%=list_aplic_pref_arancelaria%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Requiere UVA</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('47')"><i class="fa fa-search"></i></a>  
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_req_uva" name="col_req_uva">
                                    <%=list_req_uva%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#a6a2a2;">
                                <font size="1">Requiere CA</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('48')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_req_ca" name="col_req_ca">
                                    <%=list_req_ca%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#a6a2a2;">
                                <font size="1">Fecha Recepción CA</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('49')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_recepcion_ca" name="col_fecha_recepcion_ca">
                                    <%=list_fecha_recepcion_ca%>         
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#a6a2a2;">
                                <font size="1">Número de Constancia CA</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('50')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_num_constancia_ca" name="col_num_constancia_ca">
                                    <%=list_num_constancia_ca%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#a6a2a2;">
                                <font size="1">Monto CA</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('51')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_monto_ca" name="col_monto_ca">
                                    <%=list_monto_ca%>        
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Documentos Completos</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('52')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_doc_completos" name="col_fecha_doc_completos">
                                    <%=list_fecha_doc_completos%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Pago Pedimento</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('53')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_pago_pedimento" name="col_fecha_pago_pedimento">
                                    <%=list_fecha_pago_pedimento%>        
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Solicitud de transporte</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('54')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_solicitud_transporte" name="col_fecha_solicitud_transporte">
                                    <%=list_fecha_solicitud_transporte%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Modulacion</font>
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('55')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_modulacion" name="col_fecha_modulacion">
                                    <%=list_fecha_modulacion%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Modalidad</font>
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('56')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_modalidad" name="col_modalidad">
                                    <%=list_modalidad%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Resultado Modulacion</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('57')"><i class="fa fa-search"></i></a>  
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_resultado_modulacion" name="col_resultado_modulacion">
                                    <%=list_resultado_modulacion%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Reconocimiento</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('58')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_reconocimiento" name="col_fecha_reconocimiento">
                                    <%=list_fecha_reconocimiento%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha Liberacion</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('59')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_liberacion" name="col_fecha_liberacion">
                                    <%=list_fecha_liberacion%>         
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Sello Origen</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('60')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_sello_origen" name="col_sello_origen">
                                    <%=list_sello_origen%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Sello Final</font>
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('61')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_sello_final" name="col_sello_final">
                                    <%=list_sello_final%>         
                                </select>
                            </th>
                            <th class="col-sm-6" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fecha de retencion por la autoridad</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('62')"><i class="fa fa-search"></i></a>  
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_retencion_aut" name="col_fecha_retencion_aut">
                                    <%=list_fecha_retencion_aut%>       
                                </select>
                            </th>
                            <th class="col-sm-6" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Fec. de liberacion por ret. de la aut.</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('63')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_liberacion_aut" name="col_fecha_liberacion_aut">
                                    <%=list_fecha_liberacion_aut%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Estatus de la operación</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('64')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_estatus_operacion" name="col_festatus_operacion">
                                    <%=list_estatus_operacion%>        
                                </select> 
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Motivo Atraso</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('65')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_motivo_atraso" name="col_motivo_atraso">
                                    <%=list_motivo_atraso%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                <font size="1">Observaciones</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('66')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_observaciones" name="col_observaciones">
                                    <%=list_observaciones%>        
                                </select>
                            </th>
                <%  
                    }

                    if(AgentType.equals("4001")||AgentType.equals("4006")){ //LOGIX Y VF  
                %>           
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Llegada a NOVA</font>
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('67')"><i class="fa fa-search"></i></a> 

                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_llegada_a_nova" name="col_llegada_a_nova">
                                    <%=list_llegada_a_nova%>       
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Llegada a Globe trade SD</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('68')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_llegada_a_globe_trade_sd" name="col_llegada_a_globe_trade_sd">
                                    <%=list_llegada_a_globe_trade_sd%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Archivo M</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('69')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_archivo_m" name="col_archivo_m">
                                    <%=list_archivo_m%>      
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha de Archivo M</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('70')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_archivo_m" name="col_fecha_archivo_m">
                                    <%=list_fecha_archivo_m%>       
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha Solicitud de Manipulacion</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('71')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_solicit_manip" name="col_fecha_solicit_manip">
                                    <%=list_fecha_solicit_manip%>        
                                </select>
                            </th>
                            <th class="col-sm-6" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha de vencimiento de Manipulacion</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('72')"><i class="fa fa-search"></i></a>   
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_vencim_manip" name="col_fecha_vencim_manip">
                                    <%=list_fecha_vencim_manip%>         
                                </select>
                            </th>
                            <th class="col-sm-6" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha confirmacion Clave de Pedimento</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('73')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_confirm_clave_pedim" name="col_fecha_confirm_clave_pedim">
                                    <%=list_fecha_confirm_clave_pedim%>        
                                </select>
                            </th>
                            <th class="col-sm-6" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha de Recepcion de Incrementables</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('74')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_recep_increment" name="col_fecha_recep_increment">
                                    <%=list_fecha_recep_increment%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">T&E</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('75')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_t_e" name="col_t_e">
                                    <%=list_t_e%>         
                                </select>
                            </th>
                            <th class="col-sm-6" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha de Vencimiento del Inbound</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('76')"><i class="fa fa-search"></i></a>     
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_vencim_inbound" name="col_fecha_vencim_inbound">
                                    <%=list_fecha_vencim_inbound%>         
                                </select>
                            </th>
                <%
                    }

                    if(AgentType.equals("4002")||AgentType.equals("4006")){  //CUSA Y VF
                %>            
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">No. BULTOS</font>
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('77')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_no_bultos" name="col_no_bultos">
                                    <%=list_no_bultos%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Peso (KG)</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('78')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_peso_kg" name="col_peso_kg">
                                    <%=list_peso_kg%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Transferencia</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('79')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_transferencia" name="col_transferencia">
                                    <%=list_transferencia%>       
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha Inicio Etiquetado</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('80')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_inicio_etiquetado" name="col_fecha_inicio_etiquetado">
                                    <%=list_fecha_inicio_etiquetado%>        
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Fecha Termino Etiquetado</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('81')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_termino_etiquetado" name="col_fecha_termino_etiquetado">
                                    <%=list_fecha_termino_etiquetado%>       
                                </select>
                            </th>
                            <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Hora de termino Etiquetado</font>
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('82')"><i class="fa fa-search"></i></a> 
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_hora_termino_etiquetado" name="col_hora_termino_etiquetado">
                                    <%=list_hora_termino_etiquetado%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Proveedor</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('83')"><i class="fa fa-search"></i></a>
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_proveedor" name="col_proveedor">
                                    <%=list_proveedor%>        
                                </select>
                            </th>
                            <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                <font size="1">Proveedor de Carga</font> 
                                &nbsp;<a class="text-lg text-white" onclick="FiltrerData('84')"><i class="fa fa-search"></i></a>  
                                &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_proveedor_carga" name="col_proveedor_carga">
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
                                <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fy" name="col_fy">
                                    <%=list_fy%>        
                                </select>
                            </th>
                        </tr>
                    </thead>
                    <tbody id="detalleCustom">
                        <%
                            if (db.doDB(fac.consultarReporteCustoms(AgentType,filterType,caramelo))) {
                                for (String[] row : db.getResultado()) {
                                   cont++;   
                        %>
                        <tr id="tr<%=cont%>">
                        <%            
                        if(AgentType.equals("4001")||AgentType.equals("4002")||AgentType.equals("4003")||AgentType.equals("4004")||AgentType.equals("4005")||AgentType.equals("4006")){ //LOGIX, CUSA, RADAR, SESMA, RECHY Y VF   
                        %>     
                            <th class="font-numero"><center><img src="../../img/circle-green.png" width="100%"/></center></th>  <!--Semaforo -->
                            <th class="font-numero"><%=row[30]%></th>  <!-- Referencia Aduanal -->
                            <th class="font-numero"><%=row[0]%></th>   <!-- Evento -->
                            <td class="font-numero"><%=row[1]%></td>   <!-- Responsable -->
                            <td class="font-numero"><%=row[2]%></td>   <!-- Final Destination -->
                            <td class="font-numero"><%=row[21]%></td>  <!-- Brand-Division -->
                            <td class="font-numero"><%=row[4]%></td>   <!-- Division -->
                            <td class="font-numero"><%=row[5]%></td>   <!-- Shipment ID -->
                            <td class="font-numero"><%=row[6]%></td>   <!-- Container -->
                            <td class="font-numero"><%=row[7]%></td>   <!-- BL/AWB/PRO -->
                            <td class="font-numero"><%=row[8]%></td>   <!-- LoadType -->
                            <td class="font-numero"><%=row[9]%></td>   <!-- Quantity -->
                            <td class="font-numero"><%=row[19]%></td>  <!-- POD -->
                            <td class="font-numero"><%=row[11]%></td>  <!-- Est. Departure from POL -->
                            <td class="font-numero"><%=row[12]%></td>  <!-- ETA REAL Port of Discharge -->
                            <td class="font-numero"><%=row[22]%></td>  <!-- Est. Eta DC -->
                            <td class="font-numero"><%=row[14]%></td>  <!-- Inbound notification -->
                            <td class="font-numero"><%=row[20]%></td>  <!-- POL -->
                            <td class="font-numero"><%=row[16]%></td>  <!-- A.A. -->
                            <td class="font-numero"><%=row[28]%></td>  <!-- Fecha Mes de Venta -->
                            <td class="font-numero"><%=row[97]%></td>  <!-- Prioridad -->
                            <td class="font-numero"><%=row[31]%></td>  <!-- País Origen -->    
                            <td class="font-numero"><%=row[32]%></td>  <!-- Size Container -->
                            <td class="font-numero"><%=row[33]%></td>  <!-- Valor USD -->
                            <td class="font-numero"><%=row[34]%></td>  <!-- ETA Port Of Discharge -->
                            <td class="font-numero"><%=row[35]%></td>  <!-- Agente Aduanal --> 
                            <td class="font-numero"><%=row[36]%></td>  <!-- Pedimento A1 -->  
                            <td class="font-numero"><%=row[37]%></td>  <!-- Pedimento R1 -->
                            <td class="font-numero"><%=row[38]%></td>  <!-- Motivo Rectificación 1 -->
                            <td class="font-numero"><%=row[39]%></td>  <!-- Pedimento R1 (2DO) -->
                            <td class="font-numero"><%=row[40]%></td>  <!-- Motivo Rectificación 2 -->
                            <td class="font-numero"><%=row[41]%></td>  <!-- Fecha Recepción Documentos -->
                            <td class="font-numero"><%=row[42]%></td>  <!-- Recinto -->
                            <td class="font-numero"><%=row[43]%></td>  <!-- Naviera/Forwarder -->
                            <td class="font-numero"><%=row[44]%></td>  <!-- Buque -->
                            <td class="font-numero"><%=row[45]%></td>  <!-- Fecha Revalidación/Liberación de BL -->
                            <td class="font-numero"><%=row[46]%></td>  <!-- Fecha Previo Origen -->
                            <td class="font-numero"><%=row[47]%></td>  <!-- Fecha Previo en destino -->
                            <td class="font-numero"><%=row[48]%></td>  <!-- Fecha Resultado Previo -->
                            <td class="font-numero"><%=row[49]%></td>  <!-- Proforma Final -->
                            <td class="font-numero"><%=row[50]%></td>  <!-- Requiere permiso -->
                            <td class="font-numero"><%=row[51]%></td>  <!-- Fecha envío Fichas/notas -->
                            <td class="font-numero"><%=row[52]%></td>  <!-- Fec. Recepción de permisos tramit. -->
                            <td class="font-numero"><%=row[53]%></td>  <!-- Fec. Act Permisos (Inic Vigencia) -->
                            <td class="font-numero"><%=row[54]%></td>  <!-- Fec. Perm. Aut. (Fin de Vigencia) -->
                            <td class="font-numero"><%=row[55]%></td>  <!-- Cuenta con CO para aplicar preferencia Arancelaria -->
                            <td class="font-numero"><%=row[56]%></td>  <!-- Aplico Preferencia Arancelaria --> 
                            <td class="font-numero"><%=row[57]%></td>  <!-- Requiere UVA -->
                            <td class="font-numero"><%=row[58]%></td>  <!-- Requiere CA -->
                            <td class="font-numero"><%=row[59]%></td>  <!-- Fecha Recepción CA -->
                            <td class="font-numero"><%=row[60]%></td>  <!-- Número de Constancia CA -->
                            <td class="font-numero"><%=row[61]%></td>  <!-- Monto CA -->
                            <td class="font-numero"><%=row[62]%></td>  <!-- Fecha Documentos Completos -->
                            <td class="font-numero"><%=row[63]%></td>  <!-- Fecha Pago Pedimento -->
                            <td class="font-numero"><%=row[64]%></td>  <!-- Fecha Solicitud de transporte -->
                            <td class="font-numero"><%=row[65]%></td>  <!-- Fecha Modulacion -->
                            <td class="font-numero"><%=row[66]%></td>  <!-- Modalidad -->
                            <td class="font-numero"><%=row[67]%></td>  <!-- Resultado Modulacion -->
                            <td class="font-numero"><%=row[68]%></td>  <!-- Fecha Reconocimiento -->
                            <td class="font-numero"><%=row[69]%></td>  <!-- Fecha Liberacion --> 
                            <td class="font-numero"><%=row[70]%></td>  <!-- Sello Origen --> 
                            <td class="font-numero"><%=row[71]%></td>  <!-- Sello Final -->
                            <td class="font-numero"><%=row[72]%></td>  <!-- Fecha de retencion por la autoridad --> 
                            <td class="font-numero"><%=row[73]%></td>  <!-- Fec. de liberacion por ret. de la aut. -->
                            <td class="font-numero"><%=row[74]%></td>  <!-- Estatus de la operación -->
                            <td class="font-numero"><%=row[75]%></td>  <!-- Motivo Atraso -->
                            <td class="font-numero"><%=row[76]%></td>  <!-- Observaciones -->
                        <%
                        }

                        if(AgentType.equals("4001")||AgentType.equals("4006")){ //LOGIX Y VF
                        %>   
                            <td class="font-numero"><%=row[77]%></td>  <!-- Llegada a NOVA -->
                            <td class="font-numero"><%=row[78]%></td>  <!-- Llegada a Globe trade SD -->  
                            <td class="font-numero"><%=row[79]%></td>  <!-- Archivo M --> 
                            <td class="font-numero"><%=row[80]%></td>  <!-- Fecha de Archivo M --> 
                            <td class="font-numero"><%=row[81]%></td>  <!-- Fecha Solicitud de Manipulacion -->
                            <td class="font-numero"><%=row[82]%></td>  <!-- Fecha de vencimiento de Manipulacion -->
                            <td class="font-numero"><%=row[83]%></td>  <!-- Fecha confirmacion Clave de Pedimento -->
                            <td class="font-numero"><%=row[84]%></td>  <!-- Fecha de Recepcion de Incrementables -->
                            <td class="font-numero"><%=row[85]%></td>  <!-- T&E --> 
                            <td class="font-numero"><%=row[86]%></td>  <!-- Fecha de Vencimiento del Inbound --> 
                        <%       
                        }

                        if(AgentType.equals("4002")||AgentType.equals("4006")){  //CUSA Y VF
                        %>    
                            <td class="font-numero"><%=row[87]%></td>  <!-- No. BULTOS -->
                            <td class="font-numero"><%=row[88]%></td>  <!-- Peso (KG) -->
                            <td class="font-numero"><%=row[89]%></td>  <!-- Transferencia --> 
                            <td class="font-numero"><%=row[90]%></td>  <!-- Fecha Inicio Etiquetado -->
                            <td class="font-numero"><%=row[91]%></td>  <!-- Fecha Termino Etiquetado --> 
                            <td class="font-numero"><%=row[92]%></td>  <!-- Hora de termino Etiquetado -->
                            <td class="font-numero"><%=row[93]%></td>  <!-- Proveedor -->
                            <td class="font-numero"><%=row[94]%></td>  <!-- Proveedor de Carga --> 
                        <%	
                        }
                        %>
                            <td class="font-numero"><%=row[95]%></td>  <!-- FY -->
                        </tr>
                        <%         
                              }
                            }
                        %>   
                    </tbody>
                </table>
            </div>
            <input type="hidden" id="idAgenteAduanal" name="idAgenteAduanal" value="<%=AgentType%>">                    
            <br>
        </div>   
        <!-- JavaScript files-->
        <script src="../../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Multiselect -->
        <script src="../../lib/Multiselect/js/bootstrap-select.min.js" type="text/javascript"></script>
        <!-- actions js -->
        <script src="../../lib/validationsInbound/customs/reporteCustoms.js" type="text/javascript"></script>
        <!-- Window load -->
        <script src="../../lib/Loader/js/windowLoadReport.js" type="text/javascript"></script>
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

