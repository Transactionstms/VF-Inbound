<%-- 
    Document   : customForm
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Personalizar Customs</title>
        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
        <!-- Table css -->
        <link href="../lib/validationsInbound/customs/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- jQuery 3.6.0 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!-- Multiselect -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
        <!-- sweetalert -->
        <link href="../lib/SweetAlert1.1.3/css/sweetalert.min.css" rel="stylesheet" type="text/css"/>
        <!-- calendarios -->
        <link href="../lib/calendarios/css/flatpickr.min.css" rel="stylesheet" type="text/css"/>
        <script src="../lib/calendarios/js/flatpickr.min.js" type="text/javascript"></script>
        <style>
            /* Estilo de ejemplo para resaltar la celda editada */
            .celda-bloqueada {
                background-color: #ECECEC; /* Fondo verde para indicar edición */
            }

            .celda-no-bloqueada {
                background-color: #ffffff; /* Fondo verde para indicar edición */
            }
        </style>
        <script>
            document.addEventListener("readystatechange", async function () {

                if (document.readyState === "loading") {
                    await mostrarLoader();
                    document.getElementById("loaderMsg").innerHTML = "loading";
                }

                if (document.readyState === "interactive") {
                    document.getElementById("loaderMsg").innerHTML = "loading";
                }

                /*if (document.readyState === "complete") {
                 document.getElementById("loaderMsg").innerHTML = "complete";
                 }*/

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
                String idPlantilla = "";
                /*Gral (26) | Logix(24) | Cusa(25)*/
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
                    caramelo += a + ",";
                }
                caramelo = caramelo.replaceAll(",$", "");

                /*Columna: Estatus Operación (listado)*/
                if (db.doDB(fac.consultarEstatusOperacionCustoms())) {
                    for (String[] rowO : db.getResultado()) {
                        listStatusOperationEvent += "<option value=\"" + rowO[0] + "\">" + rowO[1] + "</option>";
                    }
                }

                if (db.doDB(fac.consultarEventosCustoms(AgentType, filterType, caramelo))) {
                    for (String[] row : db.getResultado()) {

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

                    }
                }
                
            String selected_referenciaAA ="";
            String selected_evento ="";
            String selected_responsable ="";
            String selected_final_destination ="";
            String selected_brand_division ="";
            String selected_division ="";
            String selected_shipmentId ="";
            String selected_containerId ="";
            String selected_blAwbPro ="";
            String selected_loadTypeFinal ="";
            String selected_quantity ="";
            String selected_pod ="";
            String selected_estDepartFromPol ="";
            String selected_etaRealPortOfDischarge ="";
            String selected_estEtaDc ="";
            String selected_inboundNotification ="";
            String selected_pol ="";
            String selected_aa ="";
            String selected_fechaMesVenta ="";
            String selected_prioridad ="";
            String selected_pais_origen ="";
            String selected_size_container ="";
            String selected_valor_usd ="";
            String selected_eta_port_discharge ="";
            String selected_agente_aduanal ="";
            String selected_pedimento_a1 ="";
            String selected_pedimento_r1_1er ="";
            String selected_motivo_rectificacion_1er ="";
            String selected_pedimento_r1_2do ="";
            String selected_motivo_rectificacion_2do ="";
            String selected_fecha_recepcion_doc ="";
            String selected_recinto ="";
            String selected_naviera ="";
            String selected_buque ="";
            String selected_fecha_revalidacion ="";
            String selected_fecha_previo_origen ="";
            String selected_fecha_previo_destino ="";
            String selected_fecha_resultado_previo ="";
            String selected_proforma_final ="";
            String selected_permiso ="";
            String selected_fecha_envio ="";
            String selected_fecha_recepcion_perm ="";
            String selected_fecha_activacion_perm ="";
            String selected_fecha_permisos_aut ="";
            String selected_co_pref_arancelaria ="";
            String selected_aplic_pref_arancelaria ="";
            String selected_req_uva ="";
            String selected_req_ca ="";
            String selected_fecha_recepcion_ca ="";
            String selected_num_constancia_ca ="";
            String selected_monto_ca ="";
            String selected_fecha_doc_completos ="";
            String selected_fecha_pago_pedimento ="";
            String selected_fecha_solicitud_transporte ="";
            String selected_fecha_modulacion ="";
            String selected_modalidad ="";
            String selected_resultado_modulacion ="";
            String selected_fecha_reconocimiento ="";
            String selected_fecha_liberacion ="";
            String selected_sello_origen ="";
            String selected_sello_final ="";
            String selected_fecha_retencion_aut ="";
            String selected_fecha_liberacion_aut ="";
            String selected_estatus_operacion ="";
            String selected_motivo_atraso ="";
            String selected_observaciones ="";
            String selected_llegada_a_nova ="";
            String selected_llegada_a_globe_trade_sd ="";
            String selected_archivo_m ="";
            String selected_fecha_archivo_m ="";
            String selected_fecha_solicit_manip ="";
            String selected_fecha_vencim_manip ="";
            String selected_fecha_confirm_clave_pedim ="";
            String selected_fecha_recep_increment ="";
            String selected_t_e ="";
            String selected_fecha_vencim_inbound ="";
            String selected_no_bultos ="";
            String selected_peso_kg ="";
            String selected_transferencia ="";
            String selected_fecha_inicio_etiquetado ="";
            String selected_fecha_termino_etiquetado ="";
            String selected_hora_termino_etiquetado ="";
            String selected_proveedor ="";
            String selected_proveedor_carga ="";
            String selected_fy ="";

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
                <table id="main-table" class="main-table" style="table-layout:fixed; width:1800%;">
                    <thead>
                        <tr>    
                            <th class="col-sm-1" style="background-color:#FFFFFF;"></th>
                            <th class="col-sm-4" style="background-color:#333F4F;">Referencia AA&nbsp;<a onclick="FiltrerData('1')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('1')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_referenciaAA" name="col_referenciaAA"><%=list_referenciaAA%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Evento <strong style="color:red">*</strong>&nbsp;<a onclick="FiltrerData('2')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('2')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_evento" name="col_evento"><%=list_evento%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Responsable&nbsp;<a onclick="FiltrerData('3')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('3')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_responsable" name="col_responsable"><%=list_responsable%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Final Destination&nbsp;<a onclick="FiltrerData('4')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('4')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_finalDestination" name="col_finalDestination"><%=list_finalDestination%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Brand-Division&nbsp;<a onclick="FiltrerData('5')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('5')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_brandDivision" name="col_brandDivision"><%=list_brandDivision%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Division&nbsp;<a onclick="FiltrerData('6')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('6')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_division" name="col_division"><%=list_division%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Shipment ID&nbsp;<a onclick="FiltrerData('7')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('7')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_shipmentId" name="col_shipmentId"><%=list_shipmentId%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Container&nbsp;<a onclick="FiltrerData('8')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('8')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_container" name="col_container"><%=list_containerId%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">BL/AWB/PRO&nbsp;<a onclick="FiltrerData('9')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('9')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_blAwbPro" name="col_blAwbPro"><%=list_blAwbPro%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">LoadType&nbsp;<a onclick="FiltrerData('10')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('10')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_loadType" name="col_loadType"><%=list_loadType%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Quantity&nbsp;<a onclick="FiltrerData('11')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('11')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_quantity" name="col_quantity"><%=list_quantity%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">POD&nbsp;<a onclick="FiltrerData('12')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('12')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_pod" name="col_pod"><%=list_pod%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Est. Departure from POL&nbsp;<a onclick="FiltrerData('13')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('13')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_estDepartFromPol" name="col_estDepartFromPol"><%=list_estDepartFromPol%></select></th>
                            <th class="col-sm-5" style="background-color:#1C84C6;">ETA REAL Port of Discharge&nbsp;<a onclick="FiltrerData('14')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('14')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_etaRealPortOfDischarge" name="col_etaRealPortOfDischarge"><%=list_etaRealPortOfDischarge%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Est. Eta DC&nbsp;<a onclick="FiltrerData('15')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('15')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_estEtaDc" name="col_estEtaDc"><%=list_estEtaDc%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Inbound notification&nbsp;<a onclick="FiltrerData('16')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('16')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_inboundNotification" name="col_inboundNotification"><%=list_inboundNotification%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">POL&nbsp;<a onclick="FiltrerData('17')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('17')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_pol" name="col_pol"><%=list_pol%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">A.A.&nbsp;<a onclick="FiltrerData('18')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('18')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_aa" name="col_aa"><%=list_aa%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Fecha Mes de Venta&nbsp;<a onclick="FiltrerData('19')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('19')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fechaMesVenta" name="col_fechaMesVenta"><%=list_fechaMesVenta%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Prioridad Si/No&nbsp;<a onclick="FiltrerData('20')"><i class="fa fa-search"></i></a> &nbsp;<a onclick="cleanMultiselects('20')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_prioridad" name="col_prioridad"><%=list_prioridad%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">País Origen&nbsp;<a onclick="FiltrerData('21')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('21')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_pais_origen" name="col_pais_origen"><%=list_pais_origen%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Size Container&nbsp;<a onclick="FiltrerData('22')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('22')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_size_container" name="col_size_container"><%=list_size_container%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Valor USD&nbsp;<a onclick="FiltrerData('23')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('23')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_valor_usd" name="col_valor_usd"><%=list_valor_usd%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">ETA Port Of Discharge&nbsp;<a onclick="FiltrerData('24')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('24')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_eta_port_discharge" name="col_eta_port_discharge"><%=list_eta_port_discharge%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Agente Aduanal&nbsp;<a onclick="FiltrerData('25')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('25')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_agente_aduanal" name="col_agente_aduanal"><%=list_agente_aduanal%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Pedimento A1&nbsp;<a onclick="FiltrerData('26')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('26')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_pedimento_a1" name="col_pedimento_a1"><%=list_pedimento_a1%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Pedimento R1&nbsp;<a onclick="FiltrerData('27')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('27')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_pedimento_r1_1er" name="col_pedimento_r1_1er"><%=list_pedimento_r1_1er%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Motivo rectificación 1&nbsp;<a onclick="FiltrerData('28')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('28')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_motivo_rectificacion_1er" name="col_motivo_rectificacion_1er"><%=list_motivo_rectificacion_1er%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Pedimento R1 (2do)&nbsp;<a onclick="FiltrerData('29')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('29')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_pedimento_r1_2do" name="col_pedimento_r1_2do"><%=list_pedimento_r1_2do%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Motivo rectificación 2&nbsp;<a onclick="FiltrerData('30')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('30')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_motivo_rectificacion_2do" name="col_motivo_rectificacion_2do"><%=list_motivo_rectificacion_2do%></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fecha Recepción Documentos&nbsp;<a onclick="FiltrerData('31')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('31')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_recepcion_doc" name="col_fecha_recepcion_doc"><%=list_fecha_recepcion_doc%></select></th>
                            <th class="col-sm-4" style="background-color:#e04141;">Recinto&nbsp;<a onclick="FiltrerData('32')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('32')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_recinto" name="col_recinto"><%=list_recinto%></select></th>
                            <th class="col-sm-4" style="background-color:#e04141;">Naviera / Forwarder&nbsp;<a onclick="FiltrerData('33')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('33')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_naviera" name="col_naviera"><%=list_naviera%></select></th>
                            <th class="col-sm-4" style="background-color:#e04141;">Buque&nbsp;<a onclick="FiltrerData('34')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('34')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_buque" name="col_buque"><%=list_buque%></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fecha Revalidación/Liberación de BL&nbsp;<a onclick="FiltrerData('35')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('35')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_revalidacion" name="col_fecha_revalidacion"><%=list_fecha_revalidacion%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Fecha Previo Origen&nbsp;<a onclick="FiltrerData('36')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('36')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_previo_origen" name="col_fecha_previo_origen"><%=list_fecha_previo_origen%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Fecha Previo en destino&nbsp;<a onclick="FiltrerData('37')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('37')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_previo_destino" name="col_fecha_previo_destino"><%=list_fecha_previo_destino%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Fecha Resultado Previo&nbsp;<a onclick="FiltrerData('38')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('38')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_resultado_previo" name="col_fecha_resultado_previo"><%=list_fecha_resultado_previo%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Proforma Final&nbsp;<a onclick="FiltrerData('39')"><i class="fa fa-search"></i></a> &nbsp;<a onclick="cleanMultiselects('39')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_proforma_final" name="col_proforma_final"><%=list_proforma_final%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Requiere permiso&nbsp;<a onclick="FiltrerData('40')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('40')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_permiso" name="col_permiso"><%=list_permiso%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Fecha envío Fichas/notas&nbsp;<a onclick="FiltrerData('41')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('41')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_envio" name="col_fecha_envio"><%=list_fecha_envio%></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fec. Recepción de permisos tramit.&nbsp;<a onclick="FiltrerData('42')"><i class="fa fa-search"></i></a> &nbsp;<a onclick="cleanMultiselects('42')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_recepcion_perm" name="col_fecha_recepcion_perm"><%=list_fecha_recepcion_perm%></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fec. Act Permisos (Inic Vigencia)&nbsp;<a onclick="FiltrerData('43')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('43')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_activacion_perm" name="col_fecha_activacion_perm"><%=list_fecha_activacion_perm%></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fec. Perm. Aut. (Fin de Vigencia)&nbsp;<a onclick="FiltrerData('44')"><i class="fa fa-search"></i></a> &nbsp;<a onclick="cleanMultiselects('44')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_permisos_aut" name="col_fecha_permisos_aut"><%=list_fecha_permisos_aut%></select></th>
                            <th class="col-sm-6" style="background-color:#CC9D77;">Cuenta con CO para aplicar preferencia Arancelaria&nbsp;<a onclick="FiltrerData('45')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('45')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_co_pref_arancelaria" name="col_co_pref_arancelaria"><%=list_co_pref_arancelaria%></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Aplico Preferencia Arancelaria&nbsp;<a onclick="FiltrerData('46')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('46')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_aplic_pref_arancelaria" name="col_aplic_pref_arancelaria"><%=list_aplic_pref_arancelaria%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Requiere UVA&nbsp;<a onclick="FiltrerData('47')"><i class="fa fa-search"></i></a> &nbsp;<a onclick="cleanMultiselects('47')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_req_uva" name="col_req_uva"><%=list_req_uva%></select></th>
                            <th class="col-sm-4" style="background-color:#a6a2a2;">Requiere CA&nbsp;<a onclick="FiltrerData('48')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('48')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_req_ca" name="col_req_ca"><%=list_req_ca%></select></th>
                            <th class="col-sm-4" style="background-color:#a6a2a2;">Fecha Recepción CA&nbsp;<a onclick="FiltrerData('49')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('49')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_recepcion_ca" name="col_fecha_recepcion_ca"><%=list_fecha_recepcion_ca%></select></th>
                            <th class="col-sm-4" style="background-color:#a6a2a2;">Número de Constancia CA&nbsp;<a onclick="FiltrerData('50')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('50')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_num_constancia_ca" name="col_num_constancia_ca"><%=list_num_constancia_ca%></select></th>
                            <th class="col-sm-4" style="background-color:#a6a2a2;">Monto CA&nbsp;<a onclick="FiltrerData('51')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('51')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_monto_ca" name="col_monto_ca"><%=list_monto_ca%></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fecha Documentos Completos&nbsp;<a onclick="FiltrerData('52')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('52')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_doc_completos" name="col_fecha_doc_completos"><%=list_fecha_doc_completos%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Fecha Pago Pedimento&nbsp;<a onclick="FiltrerData('53')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('53')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_pago_pedimento" name="col_fecha_pago_pedimento"><%=list_fecha_pago_pedimento%></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fecha Solicitud de transporte&nbsp;<a onclick="FiltrerData('54')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('54')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_solicitud_transporte" name="col_fecha_solicitud_transporte"><%=list_fecha_solicitud_transporte%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Fecha Modulacion&nbsp;<a onclick="FiltrerData('55')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('55')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_modulacion" name="col_fecha_modulacion"><%=list_fecha_modulacion%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Modalidad&nbsp;<a onclick="FiltrerData('56')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('56')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_modalidad" name="col_modalidad"><%=list_modalidad%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Resultado Modulacion&nbsp;<a onclick="FiltrerData('57')"><i class="fa fa-search"></i></a> &nbsp;<a onclick="cleanMultiselects('57')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_resultado_modulacion" name="col_resultado_modulacion"><%=list_resultado_modulacion%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Fecha Reconocimiento&nbsp;<a onclick="FiltrerData('58')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('58')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_reconocimiento" name="col_fecha_reconocimiento"><%=list_fecha_reconocimiento%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Fecha Liberacion&nbsp;<a onclick="FiltrerData('59')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('59')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_liberacion" name="col_fecha_liberacion"><%=list_fecha_liberacion%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Sello Origen&nbsp;<a onclick="FiltrerData('60')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('60')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_sello_origen" name="col_sello_origen"><%=list_sello_origen%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Sello Final&nbsp;<a onclick="FiltrerData('61')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('61')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_sello_final" name="col_sello_final"><%=list_sello_final%></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fecha de retencion por la autoridad&nbsp;<a onclick="FiltrerData('62')"><i class="fa fa-search"></i></a> &nbsp;<a onclick="cleanMultiselects('62')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_retencion_aut" name="col_fecha_retencion_aut"><%=list_fecha_retencion_aut%></select></th>
                            <th class="col-sm-5" style="background-color:#CC9D77;">Fec. de liberacion por ret. de la aut.&nbsp;<a onclick="FiltrerData('63')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('63')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_liberacion_aut" name="col_fecha_liberacion_aut"><%=list_fecha_liberacion_aut%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Estatus de la operación&nbsp;<a onclick="FiltrerData('64')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('64')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_estatus_operacion" name="col_festatus_operacion"><%=list_estatus_operacion%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Motivo Atraso&nbsp;<a onclick="FiltrerData('65')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('65')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_motivo_atraso" name="col_motivo_atraso"><%=list_motivo_atraso%></select></th>
                            <th class="col-sm-4" style="background-color:#CC9D77;">Observaciones&nbsp;<a onclick="FiltrerData('66')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('66')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_observaciones" name="col_observaciones"><%=list_observaciones%></select></th>
                                    <%
                                        if (AgentType.equals("4001") || AgentType.equals("4006")) { //LOGIX Y VF 
                                    %>           
                            <th class="col-sm-4" style="background-color:#1C84C6;">Llegada a NOVA&nbsp;<a onclick="FiltrerData('67')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('67')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_llegada_a_nova" name="col_llegada_a_nova"><%=list_llegada_a_nova%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Llegada a Globe trade SD&nbsp;<a onclick="FiltrerData('68')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('68')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_llegada_a_globe_trade_sd" name="col_llegada_a_globe_trade_sd"><%=list_llegada_a_globe_trade_sd%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Archivo M&nbsp;<a onclick="FiltrerData('69')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('69')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_archivo_m" name="col_archivo_m"><%=list_archivo_m%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">Fecha de Archivo M&nbsp;<a onclick="FiltrerData('70')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('70')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_archivo_m" name="col_fecha_archivo_m"><%=list_fecha_archivo_m%></select></th>
                            <th class="col-sm-5" style="background-color:#1C84C6;">Fecha Solicitud de Manipulacion&nbsp;<a onclick="FiltrerData('71')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('71')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_solicit_manip" name="col_fecha_solicit_manip"><%=list_fecha_solicit_manip%></select></th>
                            <th class="col-sm-6" style="background-color:#1C84C6;">Fecha de vencimiento de Manipulacion&nbsp;<a onclick="FiltrerData('72')"><i class="fa fa-search"></i></a>  &nbsp;<a onclick="cleanMultiselects('72')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_vencim_manip" name="col_fecha_vencim_manip"><%=list_fecha_vencim_manip%></select></th>
                            <th class="col-sm-6" style="background-color:#1C84C6;">Fecha confirmacion Clave de Pedimento&nbsp;<a onclick="FiltrerData('73')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('73')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_confirm_clave_pedim" name="col_fecha_confirm_clave_pedim"><%=list_fecha_confirm_clave_pedim%></select></th>
                            <th class="col-sm-6" style="background-color:#1C84C6;">Fecha de Recepcion de Incrementables&nbsp;<a onclick="FiltrerData('74')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('74')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_recep_increment" name="col_fecha_recep_increment"><%=list_fecha_recep_increment%></select></th>
                            <th class="col-sm-4" style="background-color:#1C84C6;">T&E&nbsp;<a onclick="FiltrerData('75')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('75')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_t_e" name="col_t_e"><%=list_t_e%></select></th>
                            <th class="col-sm-5" style="background-color:#1C84C6;">Fecha de Vencimiento del Inbound&nbsp;<a onclick="FiltrerData('76')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('76')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_vencim_inbound" name="col_fecha_vencim_inbound"><%=list_fecha_vencim_inbound%></select></th>
                                    <%
                                        }

                                        if (AgentType.equals("4002") || AgentType.equals("4006")) {  //CUSA Y VF
                                    %>            
                            <th class="col-sm-4" style="background-color:#1C84C6;">No. BULTOS&nbsp;<a onclick="FiltrerData('77')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('77')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_no_bultos" name="col_no_bultos"><%=list_no_bultos%></select> </th> 
                            <th class="col-sm-4" style="background-color:#1C84C6;">Peso (KG)&nbsp;<a onclick="FiltrerData('78')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('78')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_peso_kg" name="col_peso_kg"><%=list_peso_kg%></select> </th> 
                            <th class="col-sm-4" style="background-color:#1C84C6;">Transferencia&nbsp;<a onclick="FiltrerData('79')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('79')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_transferencia" name="col_transferencia"><%=list_transferencia%></select> </th> 
                            <th class="col-sm-4" style="background-color:#1C84C6;">Fecha Inicio Etiquetado&nbsp;<a onclick="FiltrerData('80')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('80')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_inicio_etiquetado" name="col_fecha_inicio_etiquetado"><%=list_fecha_inicio_etiquetado%></select> </th> 
                            <th class="col-sm-4" style="background-color:#1C84C6;">Fecha Termino Etiquetado&nbsp;<a onclick="FiltrerData('81')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('81')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fecha_termino_etiquetado" name="col_fecha_termino_etiquetado"><%=list_fecha_termino_etiquetado%></select> </th> 
                            <th class="col-sm-5" style="background-color:#1C84C6;">Hora de termino Etiquetado&nbsp;<a onclick="FiltrerData('82')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('82')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_hora_termino_etiquetado" name="col_hora_termino_etiquetado"><%=list_hora_termino_etiquetado%></select> </th> 
                            <th class="col-sm-4" style="background-color:#1C84C6;">Proveedor&nbsp;<a onclick="FiltrerData('83')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('83')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_proveedor" name="col_proveedor"><%=list_proveedor%></select></th> 
                            <th class="col-sm-4" style="background-color:#1C84C6;">Proveedor de Carga&nbsp;<a onclick="FiltrerData('84')"><i class="fa fa-search"></i></a> &nbsp;<a onclick="cleanMultiselects('84')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_proveedor_carga" name="col_proveedor_carga"><%=list_proveedor_carga%></select></th>
                                    <%
                                        }
                                    %>            
                            <th class="col-sm-4" style="background-color:#1C84C6;">FY&nbsp;<a onclick="FiltrerData('85')"><i class="fa fa-search"></i></a>&nbsp;<a onclick="cleanMultiselects('85')"><i class="fa fa-trash-alt"></i></a><select multiple class="custom-select" id="col_fy" name="col_fy"><%=list_fy%></select></th>
                            <th class="col-sm-1" style="background-color:#FFFFFF;"></th>
                        </tr>
                    </thead>
                    <tbody id="AddTableDetalleCustom"></tbody>
                </table>
            </div>
        </div>

        <!-- modal - histórico eta_port_discharge -->
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

        <!----- modal_eta_port_discharge ----->
        <div class="modal fade text-start" id="modal_show_eta_port_discharge" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="eta_port_discharge" name="eta_port_discharge" type="text" autocomplete="off" onchange="hide_eta_port_discharge(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_pedimento_r1_1er ----->
        <div class="modal fade text-start" id="modal_pedimento_r1_1er" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">

                    <input class="form-control" id="pedimento_r1_1er" name="pedimento_r1_1er" type="text" autocomplete="off" onchange="hide_pedimento_r1_1er(this.value)">

                </div>
            </div>
        </div> 

        <!----- modal_pedimento_r1_2do ----->
        <div class="modal fade text-start" id="modal_pedimento_r1_2do" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">

                    <input class="form-control" id="pedimento_r1_2do" name="pedimento_r1_2do" type="text" autocomplete="off" onchange="hide_pedimento_r1_2do(this.value)">

                </div>
            </div>
        </div>    

        <!----- modal_fecha_recepcion_doc ----->
        <div class="modal fade text-start" id="modal_fecha_recepcion_doc" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">

                    <input class="form-control datepicker" value="" id="fecha_recepcion_doc" name="fecha_recepcion_doc" type="text" autocomplete="off" onchange="hide_fecha_recepcion_doc(this.value)">

                </div>
            </div>
        </div> 

        <!----- modal_fecha_revalidacion ----->
        <div class="modal fade text-start" id="modal_fecha_revalidacion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">

                    <input class="form-control datepicker" value="" id="fecha_revalidacion" name="fecha_revalidacion" type="text" autocomplete="off" onchange="hide_fecha_revalidacion(this.value)">

                </div>
            </div>
        </div> 

        <!----- modal_fecha_previo_origen ----->
        <div class="modal fade text-start" id="modal_fecha_previo_origen" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">

                    <input class="form-control datepicker" value="" id="fecha_previo_origen" name="fecha_previo_origen" type="text" autocomplete="off" onchange="hide_fecha_previo_origen(this.value)">

                </div>
            </div>
        </div> 

        <!----- modal_fecha_previo_destino ----->
        <div class="modal fade text-start" id="modal_fecha_previo_destino" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">

                    <input class="form-control datepicker" value="" id="fecha_previo_destino" name="fecha_previo_destino" type="text" autocomplete="off" onchange="hide_fecha_previo_destino(this.value)">

                </div>
            </div>
        </div> 

        <!----- modal_fecha_resultado_previo ----->
        <div class="modal fade text-start" id="modal_fecha_resultado_previo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">

                    <input class="form-control datepicker" value="" id="fecha_resultado_previo" name="fecha_resultado_previo" type="text" autocomplete="off" onchange="hide_fecha_resultado_previo(this.value)">

                </div>
            </div>
        </div> 

        <!----- modal_proforma_final ----->
        <div class="modal fade text-start" id="modal_proforma_final" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">

                    <input class="form-control datepicker" value="" id="proforma_final" name="proforma_final" type="text" autocomplete="off" onchange="hide_proforma_final(this.value)">

                </div>
            </div>
        </div> 

        <!----- modal_permiso ----->
        <div class="modal fade text-start" id="modal_permiso" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">    
                    <div class="modal-body">
                            <div class="contenedor_modal">
                                <div class="columna1_modal">
                                    <input type="checkbox" id="permiso1" name="permiso1" value="Si" onclick="hide_permiso(this.value)">
                                    <label>Si</label> 
                                </div>
                                <div class="columna2_modal">
                                    <input type="checkbox" id="permiso2" name="permiso2" value="No" onclick="hide_permiso(this.value)">
                                    <label>No</label> 
                                </div>
                            </div>   
                    </div>
                </div>
            </div>
        </div> 

        <!----- modal_fecha_envio ----->
        <div class="modal fade text-start" id="modal_fecha_envio" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_envio" name="fecha_envio" type="text" autocomplete="off" onchange="hide_fecha_envio(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_recepcion_perm ----->
        <div class="modal fade text-start" id="modal_fecha_recepcion_perm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_recepcion_perm" name="fecha_recepcion_perm" type="text" autocomplete="off" onchange="hide_fecha_recepcion_perm(this.value)"> 
                </div>
            </div>
        </div> 

        <!----- modal_fecha_activacion_perm ----->
        <div class="modal fade text-start" id="modal_fecha_activacion_perm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_activacion_perm" name="fecha_activacion_perm" type="text" autocomplete="off" onchange="hide_fecha_activacion_perm(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_permisos_aut ----->
        <div class="modal fade text-start" id="modal_fecha_permisos_aut" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_permisos_aut" name="fecha_permisos_aut" type="text" autocomplete="off" onchange="hide_fecha_permisos_aut(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_co_pref_arancelaria ----->
        <div class="modal fade text-start" id="modal_co_pref_arancelaria" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="contenedor_modal">
                            <div class="columna1_modal">
                                <input type="checkbox" id="co_pref_arancelaria1" name="co_pref_arancelaria1" value="Si" onclick="hide_co_pref_arancelaria(this.value)">
                                <label>Si</label> 
                            </div>
                            <div class="columna2_modal">
                                <input type="checkbox" id="co_pref_arancelaria2" name="co_pref_arancelaria2" value="No" onclick="hide_co_pref_arancelaria(this.value)">
                                <label>No</label> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 

        <!----- modal_aplic_pref_arancelaria ----->
        <div class="modal fade text-start" id="modal_aplic_pref_arancelaria" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="contenedor_modal">
                            <div class="columna1_modal">
                                <input type="checkbox" id="aplic_pref_arancelaria1" name="aplic_pref_arancelaria1" value="Si" onclick="hide_aplic_pref_arancelaria(this.value)">
                                <label>Si</label> 
                            </div>
                            <div class="columna2_modal">
                                <input type="checkbox" id="aplic_pref_arancelaria2" name="aplic_pref_arancelaria2" value="No" onclick="hide_aplic_pref_arancelaria(this.value)">
                                <label>No</label> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 

        <!----- modal_req_uva ----->
        <div class="modal fade text-start" id="modal_req_uva" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="contenedor_modal">
                            <div class="columna1_modal">
                                <input type="checkbox" id="req_uva1" name="req_uva1" value="Si" onclick="hide_req_uva(this.value)">
                                <label>Si</label> 
                            </div>
                            <div class="columna2_modal">
                                <input type="checkbox" id="req_uva2" name="req_uva2" value="No" onclick="hide_req_uva(this.value)">
                                <label>No</label> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 

        <!----- modal_req_ca ----->
        <div class="modal fade text-start" id="modal_req_ca" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="contenedor_modal">
                            <div class="columna1_modal">
                                <input type="checkbox" id="req_ca1" name="req_ca1" value="Si" onclick="hide_req_ca(this.value)">
                                <label>Si</label> 
                            </div>
                            <div class="columna2_modal">
                                <input type="checkbox" id="req_ca2" name="req_ca2" value="No" onclick="hide_req_ca(this.value)">
                                <label>No</label> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 

        <!----- modal_fecha_recepcion_ca ----->
        <div class="modal fade text-start" id="modal_fecha_recepcion_ca" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_recepcion_ca" name="fecha_recepcion_ca" type="text" autocomplete="off" onchange="hide_fecha_recepcion_ca(this.value)">               
                </div>
            </div>
        </div> 

        <!----- modal_fecha_doc_completos ----->
        <div class="modal fade text-start" id="modal_fecha_doc_completos" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_doc_completos" name="fecha_doc_completos" type="text" autocomplete="off" onchange="hide_fecha_doc_completos(this.value)">             
                </div>
            </div>
        </div> 

        <!----- modal_fecha_pago_pedimento ----->
        <div class="modal fade text-start" id="modal_fecha_pago_pedimento" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker-pedimento" value="" id="fecha_pago_pedimento" name="fecha_pago_pedimento" type="text" autocomplete="off" onchange="hide_fecha_pago_pedimento(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_solicitud_transporte ----->
        <div class="modal fade text-start" id="modal_fecha_solicitud_transporte" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_solicitud_transporte" name="fecha_solicitud_transporte" type="text" autocomplete="off" onchange="hide_fecha_solicitud_transporte(this.value)">
                </div>
            </div>
        </div>

        <!----- modal_fecha_modulacion ----->
        <div class="modal fade text-start" id="modal_fecha_modulacion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker-modulacion" value="" id="fecha_modulacion" name="fecha_modulacion" type="text" autocomplete="off" onchange="hide_fecha_modulacion(this.value)">
                </div>
            </div>
        </div> 

        <!-- modal_modalidad -->
        <div class="modal fade text-start" id="modal_modalidad" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="contenedor_modal">
                            <div class="columna1_modal">
                                <input type="checkbox" id="modalidad1" name="modalidad1" value="Camión" onclick="hide_modalidad(this.value)">
                                <label>Camión</label> 
                            </div>
                            <div class="columna2_modal">
                                <input type="checkbox" id="modalidad2" name="modalidad2" value="Tren" onclick="hide_modalidad(this.value)">
                                <label>Tren</label> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>  

        <!----- modal_resultado_modulacion ----->
        <div class="modal fade text-start" id="modal_resultado_modulacion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="contenedor_modal">
                            <div class="columna1_modal">
                                <input type="checkbox" id="resultado_modulacion1" name="resultado_modulacion1" value="Verde" onclick="hide_resultado_modulacion(this.value)">
                                <label>Verde</label> 
                            </div>
                            <div class="columna2_modal">
                                <input type="checkbox" id="resultado_modulacion2" name="resultado_modulacion2" value="Rojo" onclick="hide_resultado_modulacion(this.value)">
                                <label>Rojo</label> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 

        <!----- modal_fecha_reconocimiento ----->
        <div class="modal fade text-start" id="modal_fecha_reconocimiento" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_reconocimiento" name="fecha_reconocimiento" type="text" autocomplete="off" onchange="hide_fecha_reconocimiento(this.value)">
                </div>
            </div>
        </div>

        <!----- modal_fecha_liberacion ----->
        <div class="modal fade text-start" id="modal_fecha_liberacion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_liberacion" name="fecha_liberacion" type="text" autocomplete="off" onchange="hide_fecha_liberacion(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_retencion_aut ----->
        <div class="modal fade text-start" id="modal_fecha_retencion_aut" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_retencion_aut" name="fecha_retencion_aut" type="text" autocomplete="off" onchange="hide_fecha_retencion_aut(this.value)"> 
                </div>
            </div>
        </div>  

        <!----- modal_fecha_liberacion_aut ----->
        <div class="modal fade text-start" id="modal_fecha_liberacion_aut" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_liberacion_aut" name="fecha_liberacion_aut" type="text" autocomplete="off" onchange="hide_fecha_liberacion_aut(this.value)">
                </div>
            </div>
        </div>

        <!----- modal_llegada_a_nova ----->
        <div class="modal fade text-start" id="modal_llegada_a_nova" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="llegada_a_nova" name="llegada_a_nova" type="text" autocomplete="off" onchange="hide_llegada_a_nova(this.value)">
                </div>
            </div>
        </div>

        <!----- modal_llegada_a_globe_trade_sd ----->
        <div class="modal fade text-start" id="modal_llegada_a_globe_trade_sd" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="llegada_a_globe_trade_sd" name="llegada_a_globe_trade_sd" type="text" autocomplete="off" onchange="hide_llegada_a_globe_trade_sd(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_archivo_m ----->
        <div class="modal fade text-start" id="modal_fecha_archivo_m" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_archivo_m" name="fecha_archivo_m" type="text" autocomplete="off" onchange="hide_fecha_archivo_m(this.value)">

                </div>
            </div>
        </div> 

        <!----- modal_fecha_solicit_manip ----->
        <div class="modal fade text-start" id="modal_fecha_solicit_manip" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_solicit_manip" name="fecha_solicit_manip" type="text" autocomplete="off" onchange="hide_fecha_solicit_manip(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_vencim_manip ----->
        <div class="modal fade text-start" id="modal_fecha_vencim_manip" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_vencim_manip" name="fecha_vencim_manip" type="text" autocomplete="off" onchange="hide_fecha_vencim_manip(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_confirm_clave_pedim ----->
        <div class="modal fade text-start" id="modal_fecha_confirm_clave_pedim" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_confirm_clave_pedim" name="fecha_confirm_clave_pedim" type="text" autocomplete="off" onchange="hide_fecha_confirm_clave_pedim(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_recep_increment ----->
        <div class="modal fade text-start" id="modal_fecha_recep_increment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_recep_increment" name="fecha_recep_increment" type="text" autocomplete="off" onchange="hide_fecha_recep_increment(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_vencim_inbound ----->
        <div class="modal fade text-start" id="modal_fecha_vencim_inbound" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_vencim_inbound" name="fecha_vencim_inbound" type="text" autocomplete="off" onchange="hide_fecha_vencim_inbound(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_transferencia ----->
        <div class="modal fade text-start" id="modal_transferencia" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="contenedor_modal">
                            <div class="columna1_modal">
                                <input type="checkbox" id="transferencia1" name="transferencia1" value="Si" onclick="hide_transferencia(this.value)">
                                <label>Si</label> 
                            </div>
                            <div class="columna2_modal">
                                <input type="checkbox" id="transferencia2" name="transferencia2" value="No" onclick="hide_transferencia(this.value)">
                                <label>No</label> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 

        <!----- modal_fecha_inicio_etiquetado ----->
        <div class="modal fade text-start" id="modal_fecha_inicio_etiquetado" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_inicio_etiquetado" name="fecha_inicio_etiquetado" type="text" autocomplete="off" onchange="hide_fecha_inicio_etiquetado(this.value)">
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
            $(document).ready(async function () {
                 await consultarCustoms('<%=AgentType%>', '<%=filterType%>',
                                        '<%=selected_referenciaAA%>', 
                                        '<%=selected_evento%>', 
                                        '<%=selected_responsable%>',
                                        '<%=selected_final_destination%>',
                                        '<%=selected_brand_division%>',
                                        '<%=selected_division%>',
                                        '<%=selected_shipmentId%>',
                                        '<%=selected_containerId%>',
                                        '<%=selected_blAwbPro%>',
                                        '<%=selected_loadTypeFinal%>',
                                        '<%=selected_quantity%>',
                                        '<%=selected_pod%>',
                                        '<%=selected_estDepartFromPol%>',
                                        '<%=selected_etaRealPortOfDischarge%>',
                                        '<%=selected_estEtaDc%>',
                                        '<%=selected_inboundNotification%>',
                                        '<%=selected_pol%>',
                                        '<%=selected_aa%>',
                                        '<%=selected_fechaMesVenta%>',
                                        '<%=selected_prioridad%>',
                                        '<%=selected_pais_origen%>',
                                        '<%=selected_size_container%>',
                                        '<%=selected_valor_usd%>',
                                        '<%=selected_eta_port_discharge%>',
                                        '<%=selected_agente_aduanal%>',
                                        '<%=selected_pedimento_a1%>',
                                        '<%=selected_pedimento_r1_1er%>',
                                        '<%=selected_motivo_rectificacion_1er%>',
                                        '<%=selected_pedimento_r1_2do%>',
                                        '<%=selected_motivo_rectificacion_2do%>',
                                        '<%=selected_fecha_recepcion_doc%>',
                                        '<%=selected_recinto%>',
                                        '<%=selected_naviera%>',
                                        '<%=selected_buque%>',
                                        '<%=selected_fecha_revalidacion%>',
                                        '<%=selected_fecha_previo_origen%>',
                                        '<%=selected_fecha_previo_destino%>',
                                        '<%=selected_fecha_resultado_previo%>',
                                        '<%=selected_proforma_final%>',
                                        '<%=selected_permiso%>',
                                        '<%=selected_fecha_envio%>',
                                        '<%=selected_fecha_recepcion_perm%>',
                                        '<%=selected_fecha_activacion_perm%>',
                                        '<%=selected_fecha_permisos_aut%>',
                                        '<%=selected_co_pref_arancelaria%>',
                                        '<%=selected_aplic_pref_arancelaria%>',
                                        '<%=selected_req_uva%>',
                                        '<%=selected_req_ca%>',
                                        '<%=selected_fecha_recepcion_ca%>',
                                        '<%=selected_num_constancia_ca%>',
                                        '<%=selected_monto_ca%>',
                                        '<%=selected_fecha_doc_completos%>',
                                        '<%=selected_fecha_pago_pedimento%>',
                                        '<%=selected_fecha_solicitud_transporte%>',
                                        '<%=selected_fecha_modulacion%>',
                                        '<%=selected_modalidad%>',
                                        '<%=selected_resultado_modulacion%>',
                                        '<%=selected_fecha_reconocimiento%>',
                                        '<%=selected_fecha_liberacion%>',
                                        '<%=selected_sello_origen%>',
                                        '<%=selected_sello_final%>',
                                        '<%=selected_fecha_retencion_aut%>',
                                        '<%=selected_fecha_liberacion_aut%>',
                                        '<%=selected_estatus_operacion%>',
                                        '<%=selected_motivo_atraso%>',
                                        '<%=selected_observaciones%>',
                                        '<%=selected_llegada_a_nova%>',
                                        '<%=selected_llegada_a_globe_trade_sd%>',
                                        '<%=selected_archivo_m%>',
                                        '<%=selected_fecha_archivo_m%>',
                                        '<%=selected_fecha_solicit_manip%>',
                                        '<%=selected_fecha_vencim_manip%>',
                                        '<%=selected_fecha_confirm_clave_pedim%>',
                                        '<%=selected_fecha_recep_increment%>',
                                        '<%=selected_t_e%>',
                                        '<%=selected_fecha_vencim_inbound%>',
                                        '<%=selected_no_bultos%>',
                                        '<%=selected_peso_kg%>',
                                        '<%=selected_transferencia%>',
                                        '<%=selected_fecha_inicio_etiquetado%>',
                                        '<%=selected_fecha_termino_etiquetado%>',
                                        '<%=selected_hora_termino_etiquetado%>',
                                        '<%=selected_proveedor%>',
                                        '<%=selected_proveedor_carga%>',
                                        '<%=selected_fy%>');
            });

            let idAgenteAduanal = '<%=AgentType%>';

            /*lectura de columnas (selects/filtros)*/
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

            if (idAgenteAduanal === "4001" || idAgenteAduanal === "4006") { //LOGIX Y VF  

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

            }

            if (idAgenteAduanal === "4002" || idAgenteAduanal === "4006") {  //CUSA Y VF

                var selectElement76 = document.getElementById("col_no_bultos");
                var selectElement77 = document.getElementById("col_peso_kg");
                var selectElement78 = document.getElementById("col_transferencia");
                var selectElement79 = document.getElementById("col_fecha_inicio_etiquetado");
                var selectElement80 = document.getElementById("col_fecha_termino_etiquetado");
                var selectElement81 = document.getElementById("col_hora_termino_etiquetado");
                var selectElement82 = document.getElementById("col_proveedor");
                var selectElement83 = document.getElementById("col_proveedor_carga");
                var selectElement84 = document.getElementById("col_fy");

            }

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

            if (idAgenteAduanal === "4001" || idAgenteAduanal === "4006") { //LOGIX Y VF      

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

            }

            if (idAgenteAduanal === "4002" || idAgenteAduanal === "4006") {  //CUSA Y VF

                selectElement76.innerHTML = "<%=list_no_bultos%>";
                selectElement77.innerHTML = "<%=list_peso_kg%>";
                selectElement78.innerHTML = "<%=list_transferencia%>";
                selectElement79.innerHTML = "<%=list_fecha_inicio_etiquetado%>";
                selectElement80.innerHTML = "<%=list_fecha_termino_etiquetado%>";
                selectElement81.innerHTML = "<%=list_hora_termino_etiquetado%>";
                selectElement82.innerHTML = "<%=list_proveedor%>";
                selectElement83.innerHTML = "<%=list_proveedor_carga%>";
                selectElement84.innerHTML = "<%=list_fy%>";

            }
        </script>
        <!-- JavaScript files-->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Sweetalert -->
        <script src="../lib/SweetAlert1.1.3/js/sweetalert.min.js" type="text/javascript"></script>
        <!-- Actions js -->
        <script src="../lib/validationsInbound/customs/customsForms.js" type="text/javascript"></script>
        <!-- fruitsSelect value -->
        <script src="../lib/validationsInbound/customs/fruitsSelect.js" type="text/javascript"></script>
        <!-- Upload/Download Excel -->
        <script src="../lib/validationsInbound/customs/upload_file_customs.js" type="text/javascript"></script>
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
