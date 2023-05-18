<%-- 
    Document   : ReporteCustoms
    Created on : 12/05/2023, 03:06:29 PM
    Author     : Desarrollo Tacts
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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" sizes="16x16" href="../plugins/images/favicon.png">
        <title>Reporte Customs</title>
        <link rel="stylesheet" href="../../lib/css/style.default.css" id="theme-stylesheet">
        <link rel="stylesheet" href="../../lib/css/custom.css">
        <!-- Table css -->
        <link href="../../lib/validationsInbound/customs/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- Multiselect -->
        <link href="../../lib/Multiselect/css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>
        <!-- jQuery/show modal -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
            
            #buscador {
              display: flex;
              flex-direction: row;
              flex-wrap: wrap;
            }
            
            #primero {
              width: 90%;
            }
            
            #segundo {
              width: 10%;
            }
        </style>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String view = request.getParameter("view");
                String UserId = (String) ownsession.getAttribute("login.user_id_number");
                String idDivision = ownsession.getAttribute("cbdivcuenta").toString();
                String idBodega = ownsession.getAttribute("cbbodegaId").toString();
                Usuario root = (Usuario) ownsession.getAttribute("login.root");
                ConsultasQuery fac = new ConsultasQuery();

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

                //Parametros Generales
                String tipoAgente = ""; 
                String idPlantilla = "";
                String namePlantilla = "";
                
                //Obtener el agente aduanal, id plantilla y nombre plantilla del usuario: 
                if (db.doDB(fac.consultarAgenteAduanalCustoms(UserId))) {
                    for (String[] rowA : db.getResultado()) {
                        tipoAgente = rowA[0]; 
                        idPlantilla = rowA[1];
                        namePlantilla = rowA[2];
                    }
                }
                
                if (db.doDB(fac.consultarMultiselectCustoms(tipoAgente))) {
                    for (String[] row : db.getResultado()) {
                        
                                    list_evento.add("<option value=\""+row[0].trim()+"\">"+row[0]+"</option>");
                                    list_referenciaAA.add("<option value=\""+row[30].trim()+"\">"+row[30]+"</option>");
                                    list_responsable.add("<option value=\""+row[1].trim()+"\">"+row[1]+"</option>");
                                    list_finalDestination.add("<option value=\""+row[2].trim()+"\">"+row[2]+"</option>");
                                    list_brandDivision.add("<option value=\""+row[21].trim()+"\">"+row[21]+"</option>");
                                    list_division.add("<option value=\""+row[4].trim()+"\">"+row[4]+"</option>");
                                    list_shipmentId.add("<option value=\""+row[5].trim()+"\">"+row[5]+"</option>");
                                    list_containerId.add("<option value=\""+row[6].trim()+"\">"+row[6]+"</option>");
                                    list_blAwbPro.add("<option value=\""+row[7].trim()+"\">"+row[7]+"</option>");
                                    list_loadType.add("<option value=\""+row[22].trim()+"\">"+row[22]+"</option>");
                                    list_quantity.add("<option value=\""+row[9].trim()+"\">"+row[9]+"</option>");
                                    list_pod.add("<option value=\""+row[19].trim()+"\">"+row[19]+"</option>");
                                    list_estDepartFromPol.add("<option value=\""+row[11].trim()+"\">"+row[11]+"</option>");
                                    list_etaRealPortOfDischarge.add("<option value=\""+row[12].trim()+"\">"+row[12]+"</option>");
                                    list_estEtaDc.add("<option value=\""+row[23].trim()+"\">"+row[23]+"</option>");
                                    list_inboundNotification.add("<option value=\""+row[14].trim()+"\">"+row[15]+"</option>");
                                    list_pol.add("<option value=\""+row[20].trim()+"\">"+row[20]+"</option>");
                                    list_aa.add("<option value=\""+row[16].trim()+"\">"+row[16]+"</option>");
                                    list_fechaMesVenta.add("<option value=\""+row[28].trim()+"\">"+row[28]+"</option>");
                                    list_prioridad.add("<option value=\""+row[97].trim()+"\">"+row[97]+"</option>");
                                    list_pais_origen.add("<option value=\""+row[31].trim()+"\">"+row[31]+"</option>");   
                                    list_size_container.add("<option value=\""+row[32].trim()+"\">"+row[32]+"</option>");    
                                    list_valor_usd.add("<option value=\""+row[33].trim()+"\">"+row[33]+"</option>");                 
                                    list_eta_port_discharge.add("<option value=\""+row[34].trim()+"\">"+row[34]+"</option>");         
                                    list_agente_aduanal.add("<option value=\""+row[35].trim()+"\">"+row[35]+"</option>");              
                                    list_pedimento_a1.add("<option value=\""+row[36].trim()+"\">"+row[36]+"</option>");              
                                    list_pedimento_r1_1er.add("<option value=\""+row[37].trim()+"\">"+row[37]+"</option>");          
                                    list_motivo_rectificacion_1er.add("<option value=\""+row[38].trim()+"\">"+row[38]+"</option>");    
                                    list_pedimento_r1_2do.add("<option value=\""+row[39].trim()+"\">"+row[39]+"</option>");         
                                    list_motivo_rectificacion_2do.add("<option value=\""+row[40].trim()+"\">"+row[40]+"</option>");    
                                    list_fecha_recepcion_doc.add("<option value=\""+row[41].trim()+"\">"+row[41]+"</option>");    
                                    list_recinto.add("<option value=\""+row[42].trim()+"\">"+row[42]+"</option>");
                                    list_naviera.add("<option value=\""+row[43].trim()+"\">"+row[43]+"</option>");
                                    list_buque.add("<option value=\""+row[44].trim()+"\">"+row[44]+"</option>");
                                    list_fecha_revalidacion.add("<option value=\""+row[45].trim()+"\">"+row[45]+"</option>");      
                                    list_fecha_previo_origen.add("<option value=\""+row[46].trim()+"\">"+row[46]+"</option>");     
                                    list_fecha_previo_destino.add("<option value=\""+row[47].trim()+"\">"+row[47]+"</option>");   
                                    list_fecha_resultado_previo.add("<option value=\""+row[48].trim()+"\">"+row[48]+"</option>");   
                                    list_proforma_final.add("<option value=\""+row[49].trim()+"\">"+row[49]+"</option>");        
                                    list_permiso.add("<option value=\""+row[50].trim()+"\">"+row[50]+"</option>");               
                                    list_fecha_envio.add("<option value=\""+row[51].trim()+"\">"+row[51]+"</option>");             
                                    list_fecha_recepcion_perm.add("<option value=\""+row[52].trim()+"\">"+row[52]+"</option>");
                                    list_fecha_activacion_perm.add("<option value=\""+row[53].trim()+"\">"+row[53]+"</option>");    
                                    list_fecha_permisos_aut.add("<option value=\""+row[54].trim()+"\">"+row[54]+"</option>");     
                                    list_co_pref_arancelaria.add("<option value=\""+row[55].trim()+"\">"+row[55]+"</option>");    
                                    list_aplic_pref_arancelaria.add("<option value=\""+row[56].trim()+"\">"+row[56]+"</option>");  
                                    list_req_uva.add("<option value=\""+row[57].trim()+"\">"+row[57]+"</option>");   
                                    list_req_ca.add("<option value=\""+row[58].trim()+"\">"+row[58]+"</option>");    
                                    list_fecha_recepcion_ca.add("<option value=\""+row[59].trim()+"\">"+row[59]+"</option>"); 
                                    list_num_constancia_ca.add("<option value=\""+row[60].trim()+"\">"+row[60]+"</option>");   
                                    list_monto_ca.add("<option value=\""+row[61].trim()+"\">"+row[61]+"</option>");
                                    list_fecha_doc_completos.add("<option value=\""+row[62].trim()+"\">"+row[62]+"</option>");  
                                    list_fecha_pago_pedimento.add("<option value=\""+row[63].trim()+"\">"+row[63]+"</option>");     
                                    list_fecha_solicitud_transporte.add("<option value=\""+row[64].trim()+"\">"+row[64]+"</option>");
                                    list_fecha_modulacion.add("<option value=\""+row[65].trim()+"\">"+row[65]+"</option>");   
                                    list_modalidad.add("<option value=\""+row[66].trim()+"\">"+row[66]+"</option>");          
                                    list_resultado_modulacion.add("<option value=\""+row[67].trim()+"\">"+row[67]+"</option>");    
                                    list_fecha_reconocimiento.add("<option value=\""+row[68].trim()+"\">"+row[68]+"</option>");     
                                    list_fecha_liberacion.add("<option value=\""+row[69].trim()+"\">"+row[69]+"</option>");       
                                    list_sello_origen.add("<option value=\""+row[70].trim()+"\">"+row[70]+"</option>");            
                                    list_sello_final.add("<option value=\""+row[71].trim()+"\">"+row[71]+"</option>");      
                                    list_fecha_retencion_aut.add("<option value=\""+row[72].trim()+"\">"+row[72]+"</option>");     
                                    list_fecha_liberacion_aut.add("<option value=\""+row[73].trim()+"\">"+row[73]+"</option>");   
                                    list_estatus_operacion.add("<option value=\""+row[74].trim()+"\">"+row[74]+"</option>");      
                                    list_motivo_atraso.add("<option value=\""+row[75].trim()+"\">"+row[75]+"</option>");          
                                    list_observaciones.add("<option value=\""+row[76].trim()+"\">"+row[76]+"</option>"); 

                        if(tipoAgente.equals("4001")||tipoAgente.equals("4006")){ //Logix ó VF            
                                    list_llegada_a_nova.add("<option value=\""+row[77].trim()+"\">"+row[77]+"</option>");
                                    list_llegada_a_globe_trade_sd.add("<option value=\""+row[78].trim()+"\">"+row[78]+"</option>");
                                    list_archivo_m.add("<option value=\""+row[79].trim()+"\">"+row[79]+"</option>");
                                    list_fecha_archivo_m.add("<option value=\""+row[80].trim()+"\">"+row[80]+"</option>");
                                    list_fecha_solicit_manip.add("<option value=\""+row[81].trim()+"\">"+row[81]+"</option>");
                                    list_fecha_vencim_manip.add("<option value=\""+row[82].trim()+"\">"+row[82]+"</option>");
                                    list_fecha_confirm_clave_pedim.add("<option value=\""+row[83].trim()+"\">"+row[83]+"</option>");
                                    list_fecha_recep_increment.add("<option value=\""+row[84].trim()+"\">"+row[84]+"</option>");
                                    list_t_e.add("<option value=\""+row[85].trim()+"\">"+row[85]+"</option>");
                                    list_fecha_vencim_inbound.add("<option value=\""+row[86].trim()+"\">"+row[86]+"</option>");
                        }

                        if(tipoAgente.equals("4002")||tipoAgente.equals("4006")){  //Cusa ó VF
                                    list_no_bultos.add("<option value=\""+row[87].trim()+"\">"+row[87]+"</option>");
                                    list_peso_kg.add("<option value=\""+row[88].trim()+"\">"+row[88]+"</option>");
                                    list_transferencia.add("<option value=\""+row[89].trim()+"\">"+row[89]+"</option>");
                                    list_fecha_inicio_etiquetado.add("<option value=\""+row[90].trim()+"\">"+row[90]+"</option>");
                                    list_fecha_termino_etiquetado.add("<option value=\""+row[91].trim()+"\">"+row[91]+"</option>");
                                    list_hora_termino_etiquetado.add("<option value=\""+row[92].trim()+"\">"+row[92]+"</option>");
                                    list_proveedor.add("<option value=\""+row[93].trim()+"\">"+row[93]+"</option>");
                                    list_proveedor_carga.add("<option value=\""+row[94].trim()+"\">"+row[94]+"</option>");
                                    list_fy.add("<option value=\""+row[95].trim()+"\">"+row[95]+"</option>");
                        }
                        
                    }
                }
        %>
        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <section>
                        <div class="row">
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card h-100">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h2 class="card-heading">Reporte Customs</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div style="text-align: right;">
                                            <label><font class="redtext1">Busqueda:&nbsp; <input id="searchReporteCustoms" type="text" onkeyup="doSearch()" style="text-transform:uppercase;" data-mobile-responsive="true"/></font></label>
                                        </div>
                                        <br>
                                        <div id="table-scroll" class="table-scroll"  style="height:500px;">
                                            <table id="main-table" class="main-table" style="table-layout:fixed; width:1500%;">
                                                <thead>
                                                    <tr>
                                                        <th class="col-sm-1" class="font-titulo" style="background-color:#FFFFFF;"></th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#333F4F;">
                                                            <font size="1">Referencia AA</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('1')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_referenciaAA" name="col_referenciaAA">
                                                                <%=list_referenciaAA%>         
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Evento <strong style="color:red">*</strong></font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('2')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_evento" name="col_evento">
                                                                <%=list_evento%>
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Responsable</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('3')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_responsable" name="col_responsable">
                                                                <%=list_responsable%>      
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Final Destination</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('4')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_finalDestination" name="col_finalDestination">
                                                                <%=list_finalDestination%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Brand-Division</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('5')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_brandDivision" name="col_brandDivision">
                                                                <%=list_brandDivision%>       
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Division</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('6')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_division" name="col_division">
                                                                <%=list_division%>         
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Shipment ID</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('7')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_shipmentId" name="col_shipmentId">
                                                                <%=list_shipmentId%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Container</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('8')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_container" name="col_container">
                                                                <%=list_containerId%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">BL/AWB/PRO</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('9')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_blAwbPro" name="col_blAwbPro">
                                                                <%=list_blAwbPro%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">LoadType</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('10')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_loadType" name="col_loadType">
                                                                <%=list_loadType%>     
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Quantity</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('11')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_quantity" name="col_quantity">
                                                                <%=list_quantity%>       
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">POD</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('12')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_pod" name="col_pod">
                                                                <%=list_pod%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Est. Departure from POL</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('13')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_estDepartFromPol" name="col_estDepartFromPol">
                                                                <%=list_estDepartFromPol%>        
                                                            </select> 
                                                        </th>
                                                        <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">ETA REAL Port of Discharge</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('14')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_etaRealPortOfDischarge" name="col_etaRealPortOfDischarge">
                                                                <%=list_etaRealPortOfDischarge%>      
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Est. Eta DC</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('15')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_estEtaDc" name="col_estEtaDc">
                                                                <%=list_estEtaDc%>        
                                                            </select> 
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Inbound notification</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('16')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_inboundNotification" name="col_inboundNotification">
                                                                <%=list_inboundNotification%>        
                                                            </select> 
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">POL</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('17')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_pol" name="col_pol">
                                                                <%=list_pol%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">A.A.</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('18')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_aa" name="col_aa">
                                                                <%=list_aa%>       
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Fecha Mes de Venta</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('19')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fechaMesVenta" name="col_fechaMesVenta">
                                                                <%=list_fechaMesVenta%>         
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Prioridad Si/No</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('20')"><i class="fa fa-search"></i></a>  
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_prioridad" name="col_prioridad">
                                                                <%=list_prioridad%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">País Origen</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('21')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_pais_origen" name="col_pais_origen">
                                                                <%=list_pais_origen%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Size Container</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('22')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_size_container" name="col_size_container">
                                                                <%=list_size_container%>         
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Valor USD</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('23')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_valor_usd" name="col_valor_usd">
                                                                <%=list_valor_usd%>         
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">ETA Port Of Discharge</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('24')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_eta_port_discharge" name="col_eta_port_discharge">
                                                                <%=list_eta_port_discharge%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Agente Aduanal</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('25')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_agente_aduanal" name="col_agente_aduanal">
                                                                <%=list_agente_aduanal%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Pedimento A1</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('26')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_pedimento_a1" name="col_pedimento_a1">
                                                                <%=list_pedimento_a1%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Pedimento R1</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('27')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_pedimento_r1_1er" name="col_pedimento_r1_1er">
                                                                <%=list_pedimento_r1_1er%>       
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Motivo rectificación 1</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('28')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_motivo_rectificacion_1er" name="col_motivo_rectificacion_1er">
                                                                <%=list_motivo_rectificacion_1er%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Pedimento R1 (2do)</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('29')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_pedimento_r1_2do" name="col_pedimento_r1_2do">
                                                                <%=list_pedimento_r1_2do%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Motivo rectificación 2</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('30')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_motivo_rectificacion_2do" name="col_motivo_rectificacion_2do">
                                                                <%=list_motivo_rectificacion_2do%>       
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fecha Recepción Documentos</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('31')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_recepcion_doc" name="col_fecha_recepcion_doc">
                                                                <%=list_fecha_recepcion_doc%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#e04141;">
                                                            <font size="1">Recinto</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('32')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_recinto" name="col_recinto">
                                                                <%=list_recinto%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#e04141;">
                                                            <font size="1">Naviera / Forwarder</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('33')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_naviera" name="col_naviera">
                                                                <%=list_naviera%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#e04141;">
                                                            <font size="1">Buque</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('34')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_buque" name="col_buque">
                                                                <%=list_buque%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-6" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fecha Revalidación/Liberación de BL</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('35')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_revalidacion" name="col_fecha_revalidacion">
                                                                <%=list_fecha_revalidacion%>         
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fecha Previo Origen</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('36')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_previo_origen" name="col_fecha_previo_origen">
                                                                <%=list_fecha_previo_origen%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fecha Previo en destino</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('37')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_previo_destino" name="col_fecha_previo_destino">
                                                                <%=list_fecha_previo_destino%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fecha Resultado Previo</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('38')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_resultado_previo" name="col_fecha_resultado_previo">
                                                                <%=list_fecha_resultado_previo%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Proforma Final</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('39')"><i class="fa fa-search"></i></a>  
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_proforma_final" name="col_proforma_final">
                                                                <%=list_proforma_final%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Requiere permiso</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('40')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_permiso" name="col_permiso">
                                                                <%=list_permiso%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fecha envío Fichas/notas</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('41')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_envio" name="col_fecha_envio">
                                                                <%=list_fecha_envio%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-6" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fec. Recepción de permisos tramit.</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('42')"><i class="fa fa-search"></i></a>  
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_recepcion_perm" name="col_fecha_recepcion_perm">
                                                                <%=list_fecha_recepcion_perm%>        
                                                            </select>
                                                        </th>

                                                        <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fec. Act Permisos (Inic Vigencia)</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('43')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_activacion_perm" name="col_fecha_activacion_perm">
                                                                <%=list_fecha_activacion_perm%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fec. Perm. Aut. (Fin de Vigencia)</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('44')"><i class="fa fa-search"></i></a>  
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_permisos_aut" name="col_fecha_permisos_aut">
                                                                <%=list_fecha_permisos_aut%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-7" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Cuenta con CO para aplicar preferencia Arancelaria</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('45')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_co_pref_arancelaria" name="col_co_pref_arancelaria">
                                                                <%=list_co_pref_arancelaria%>         
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Aplico Preferencia Arancelaria</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('46')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_aplic_pref_arancelaria" name="col_aplic_pref_arancelaria">
                                                                <%=list_aplic_pref_arancelaria%>         
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Requiere UVA</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('47')"><i class="fa fa-search"></i></a>  
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_req_uva" name="col_req_uva">
                                                                <%=list_req_uva%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#a6a2a2;">
                                                            <font size="1">Requiere CA</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('48')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_req_ca" name="col_req_ca">
                                                                <%=list_req_ca%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#a6a2a2;">
                                                            <font size="1">Fecha Recepción CA</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('49')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_recepcion_ca" name="col_fecha_recepcion_ca">
                                                                <%=list_fecha_recepcion_ca%>         
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-5" class="font-titulo" style="background-color:#a6a2a2;">
                                                            <font size="1">Número de Constancia CA</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('50')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_num_constancia_ca" name="col_num_constancia_ca">
                                                                <%=list_num_constancia_ca%>       
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#a6a2a2;">
                                                            <font size="1">Monto CA</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('51')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_monto_ca" name="col_monto_ca">
                                                                <%=list_monto_ca%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fecha Documentos Completos</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('52')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_doc_completos" name="col_fecha_doc_completos">
                                                                <%=list_fecha_doc_completos%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fecha Pago Pedimento</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('53')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_pago_pedimento" name="col_fecha_pago_pedimento">
                                                                <%=list_fecha_pago_pedimento%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-5" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fecha Solicitud de transporte</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('54')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_solicitud_transporte" name="col_fecha_solicitud_transporte">
                                                                <%=list_fecha_solicitud_transporte%>         
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fecha Modulacion</font>
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('55')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_modulacion" name="col_fecha_modulacion">
                                                                <%=list_fecha_modulacion%>         
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Modalidad</font>
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('56')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_modalidad" name="col_modalidad">
                                                                <%=list_modalidad%>       
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Resultado Modulacion</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('57')"><i class="fa fa-search"></i></a>  
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_resultado_modulacion" name="col_resultado_modulacion">
                                                                <%=list_resultado_modulacion%>       
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fecha Reconocimiento</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('58')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_reconocimiento" name="col_fecha_reconocimiento">
                                                                <%=list_fecha_reconocimiento%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fecha Liberacion</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('59')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_liberacion" name="col_fecha_liberacion">
                                                                <%=list_fecha_liberacion%>         
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Sello Origen</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('60')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_sello_origen" name="col_sello_origen">
                                                                <%=list_sello_origen%>       
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Sello Final</font>
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('61')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_sello_final" name="col_sello_final">
                                                                <%=list_sello_final%>         
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-6" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fecha de retencion por la autoridad</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('62')"><i class="fa fa-search"></i></a>  
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_retencion_aut" name="col_fecha_retencion_aut">
                                                                <%=list_fecha_retencion_aut%>       
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-6" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Fec. de liberacion por ret. de la aut.</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('63')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_liberacion_aut" name="col_fecha_liberacion_aut">
                                                                <%=list_fecha_liberacion_aut%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Estatus de la operación</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('64')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_estatus_operacion" name="col_festatus_operacion">
                                                                <%=list_estatus_operacion%>        
                                                            </select> 
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Motivo Atraso</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('65')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_motivo_atraso" name="col_motivo_atraso">
                                                                <%=list_motivo_atraso%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#CC9D77;">
                                                            <font size="1">Observaciones</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('66')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_observaciones" name="col_observaciones">
                                                                <%=list_observaciones%>        
                                                            </select>
                                                        </th>
                                            <%            
                                                if(tipoAgente.equals("4001")||tipoAgente.equals("4006")){ //Logix ó VF  
                                            %>           
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Llegada a NOVA</font>
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('67')"><i class="fa fa-search"></i></a> 
                                                            
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_llegada_a_nova" name="col_llegada_a_nova">
                                                                <%=list_llegada_a_nova%>       
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Llegada a Globe trade SD</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('68')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_llegada_a_globe_trade_sd" name="col_llegada_a_globe_trade_sd">
                                                                <%=list_llegada_a_globe_trade_sd%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Archivo M</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('69')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_archivo_m" name="col_archivo_m">
                                                                <%=list_archivo_m%>      
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Fecha de Archivo M</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('70')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_archivo_m" name="col_fecha_archivo_m">
                                                                <%=list_fecha_archivo_m%>       
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Fecha Solicitud de Manipulacion</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('71')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_solicit_manip" name="col_fecha_solicit_manip">
                                                                <%=list_fecha_solicit_manip%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-6" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Fecha de vencimiento de Manipulacion</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('72')"><i class="fa fa-search"></i></a>   
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_vencim_manip" name="col_fecha_vencim_manip">
                                                                <%=list_fecha_vencim_manip%>         
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-6" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Fecha confirmacion Clave de Pedimento</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('73')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_confirm_clave_pedim" name="col_fecha_confirm_clave_pedim">
                                                                <%=list_fecha_confirm_clave_pedim%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-6" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Fecha de Recepcion de Incrementables</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('74')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_recep_increment" name="col_fecha_recep_increment">
                                                                <%=list_fecha_recep_increment%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">T&E</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('75')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_t_e" name="col_t_e">
                                                                <%=list_t_e%>         
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-6" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Fecha de Vencimiento del Inbound</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('76')"><i class="fa fa-search"></i></a>     
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_vencim_inbound" name="col_fecha_vencim_inbound">
                                                                <%=list_fecha_vencim_inbound%>         
                                                            </select>
                                                        </th>
                                            <%
                                                }

                                                if(tipoAgente.equals("4002")||tipoAgente.equals("4006")){  //Cusa ó VF
                                            %>            
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">No. BULTOS</font>
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('77')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_no_bultos" name="col_no_bultos">
                                                                <%=list_no_bultos%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Peso (KG)</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('78')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_peso_kg" name="col_peso_kg">
                                                                <%=list_peso_kg%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Transferencia</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('79')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_transferencia" name="col_transferencia">
                                                                <%=list_transferencia%>       
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Fecha Inicio Etiquetado</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('80')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_inicio_etiquetado" name="col_fecha_inicio_etiquetado">
                                                                <%=list_fecha_inicio_etiquetado%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Fecha Termino Etiquetado</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('81')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fecha_termino_etiquetado" name="col_fecha_termino_etiquetado">
                                                                <%=list_fecha_termino_etiquetado%>       
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-5" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Hora de termino Etiquetado</font>
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('82')"><i class="fa fa-search"></i></a> 
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_hora_termino_etiquetado" name="col_hora_termino_etiquetado">
                                                                <%=list_hora_termino_etiquetado%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Proveedor</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('83')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_proveedor" name="col_proveedor">
                                                                <%=list_proveedor%>        
                                                            </select>
                                                        </th>
                                                        <th class="col-sm-4" class="font-titulo" style="background-color:#1C84C6;">
                                                            <font size="1">Proveedor de Carga</font> 
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('84')"><i class="fa fa-search"></i></a>  
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
                                                            &nbsp;<a class="text-lg text-white" onclick="customForm('85')"><i class="fa fa-search"></i></a>
                                                            &nbsp;<a class="text-lg text-white" onclick="cleanMultiselects()"><i class="fa fa-trash-alt"></i></a> 
                                                            <select class="selectpicker" multiple aria-label="Seleccione"  id="col_fy" name="col_fy">
                                                                <%=list_fy%>        
                                                            </select>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody id="detalleCustom"></tbody>
                                            </table>
                                        </div>
                                        <input type="hidden" id="idAgenteAduanal" name="idAgenteAduanal" value="<%=tipoAgente%>">                    
                                        <br>
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
        <!-- JavaScript files-->
        <script src="../../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Multiselect -->
        <script src="../../lib/Multiselect/js/bootstrap-select.min.js" type="text/javascript"></script>
        <!-- actions js -->
        <script src="../../lib/validationsInbound/customs/reporteCustoms.js" type="text/javascript"></script>
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
