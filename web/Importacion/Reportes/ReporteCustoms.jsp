<%-- 
    Document   : ReporteCustoms
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
<%
    Date date = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("MM/dd/yyyy");
    String fecha = formato.format(date);
    String[] par = fecha.split("/");
    String fecha_actual = par[0]+"/"+par[1]+"/"+par[2];
%>    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Personalizar Customs</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/css/style.default.css" id="theme-stylesheet">
        <!-- Table css -->
        <link href="<%=request.getContextPath()%>/lib/validationsInbound/reporteCustoms/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- jQuery 3.6.0 -->
        <script src="<%=request.getContextPath()%>/lib/jQuery3.6.0/js/jquery.min.js" type="text/javascript"></script>
        <!-- Filtrer Checkbox -->
        <link href="<%=request.getContextPath()%>/lib/validationsInbound/reporteCustoms/styleFiltrerCheckbox.css" rel="stylesheet" type="text/css"/>
        <!-- Descarga de Excel -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.5/xlsx.full.min.js"></script>
        <style>
            .hidden-btn {
               display: none;
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

                if (document.readyState === "complete") {
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
               
                //String fechaInicial = request.getParameter("fechaInicial");
                //String fechaFinal = request.getParameter("fechaFinal");
                String fechaInicial = "01/10/2023";
                String fechaFinal = "24/01/2024";
                
                //Parametros Generales
                String idDivision = "20";
                String AgentType = "";
                String idPlantilla = "";
                String namePlantilla = ""; 
                int cont = 1;
                
                //Lista de valores filtros checkbox:
                String list_evento = "";
                String list_referenciaAA = "";
                String list_responsable = "";
                String list_finalDestination = "";
                String list_brandDivision = "";
                String list_division = "";
                String list_shipmentId = "";
                String list_containerId = "";
                String list_blAwbPro = "";
                String list_loadType = "";
                String list_quantity = "";
                String list_pod = "";
                String list_estDepartFromPol = "";
                String list_etaRealPortOfDischarge = "";
                String list_estEtaDc = "";
                String list_inboundNotification = "";
                String list_pol = "";
                String list_aa = "";
                String list_fechaMesVenta = "";
                String list_prioridad = "";
                String list_pais_origen = "";
                String list_size_container = "";
                String list_valor_usd = "";
                String list_eta_port_discharge = "";
                String list_agente_aduanal = "";
                String list_pedimento_a1 = "";
                String list_pedimento_r1_1er = "";
                String list_motivo_rectificacion_1er = "";
                String list_pedimento_r1_2do = "";
                String list_motivo_rectificacion_2do = "";
                String list_fecha_recepcion_doc = "";
                String list_recinto = "";
                String list_naviera = "";
                String list_buque = "";
                String list_fecha_revalidacion = "";
                String list_fecha_previo_origen = "";
                String list_fecha_previo_destino = "";
                String list_fecha_resultado_previo = "";
                String list_proforma_final = "";
                String list_permiso = "";
                String list_fecha_envio = "";
                String list_fecha_recepcion_perm = "";
                String list_fecha_activacion_perm = "";
                String list_fecha_permisos_aut = "";
                String list_co_pref_arancelaria = "";
                String list_aplic_pref_arancelaria = "";
                String list_req_uva = "";
                String list_req_ca = "";
                String list_fecha_recepcion_ca = "";
                String list_num_constancia_ca = "";
                String list_monto_ca = "";
                String list_fecha_doc_completos = "";
                String list_fecha_pago_pedimento = "";
                String list_fecha_solicitud_transporte = "";
                String list_fecha_modulacion = "";
                String list_modalidad = "";
                String list_resultado_modulacion = "";
                String list_fecha_reconocimiento = "";
                String list_fecha_liberacion = "";
                String list_sello_origen = "";
                String list_sello_final = "";
                String list_fecha_retencion_aut = "";
                String list_fecha_liberacion_aut = "";
                String list_estatus_operacion = "";
                String list_motivo_atraso = "";
                String list_observaciones = "";
                String list_llegada_a_nova = "";
                String list_llegada_a_globe_trade_sd = "";
                String list_archivo_m = "";
                String list_fecha_archivo_m = "";
                String list_fecha_solicit_manip = "";
                String list_fecha_vencim_manip = "";
                String list_fecha_confirm_clave_pedim = "";
                String list_fecha_recep_increment = "";
                String list_t_e = "";
                String list_fecha_vencim_inbound = "";
                String list_no_bultos = "";
                String list_peso_kg = "";
                String list_transferencia = "";
                String list_fecha_inicio_etiquetado = "";
                String list_fecha_termino_etiquetado = "";
                String list_hora_termino_etiquetado = "";
                String list_proveedor = "";
                String list_proveedor_carga = "";
                String list_fy = "";
                
                
                //Consultar Información de Agente Aduanal
                if (db.doDB(fac.consultarAgenteAduanalCustoms(UserId))) {
                    for (String[] rowA : db.getResultado()) {
                        AgentType = rowA[0];
                        idPlantilla = rowA[1];
                        namePlantilla = rowA[2];
                    }
                }
                
                //Listar columnas para encabeazdos
                if (db.doDB(fac.consultarEventosCustoms(AgentType,"0","0"))) {
                        for (String[] row : db.getResultado()) {

                            list_referenciaAA += row[30] + "@";
                            list_evento += row[0] + "@";
                            list_responsable += row[1] + "@";
                            list_finalDestination += row[2] + "@";
                            list_brandDivision += row[21] + "@";
                            list_division += row[4] + "@";
                            list_shipmentId += row[5] + "@";
                            list_containerId += row[6] + "@";
                            list_blAwbPro += row[7] + "@";
                            list_loadType += row[8] + "@";
                            list_quantity += row[9] + "@";
                            list_pod += row[19] + "@";
                            list_estDepartFromPol += row[11] + "@";
                            list_etaRealPortOfDischarge += row[12] + "@";
                            list_estEtaDc += row[22] + "@";
                            list_inboundNotification += row[14] + "@";
                            list_pol += row[20] + "@";
                            list_aa += row[16] + "@";
                            list_fechaMesVenta += row[28] + "@";
                            list_prioridad += row[97] + "@";
                            list_pais_origen += row[31] + "@";
                            list_size_container += row[32] + "@";
                            list_valor_usd += row[33] + "@";
                            list_eta_port_discharge += row[34] + "@";
                            list_agente_aduanal += row[35] + "@";
                            list_pedimento_a1 += row[36] + "@";
                            list_pedimento_r1_1er += row[37] + "@";
                            list_motivo_rectificacion_1er += row[38] + "@";
                            list_pedimento_r1_2do += row[39] + "@";
                            list_motivo_rectificacion_2do += row[40] + "@";
                            list_fecha_recepcion_doc += row[41] + "@";
                            list_recinto += row[42] + "@";
                            list_naviera += row[43] + "@";
                            list_buque += row[44] + "@";
                            list_fecha_revalidacion += row[45] + "@";
                            list_fecha_previo_origen += row[46] + "@";
                            list_fecha_previo_destino += row[47] + "@";
                            list_fecha_resultado_previo += row[48] + "@";
                            list_proforma_final += row[49] + "@";
                            list_permiso += row[50] + "@";
                            list_fecha_envio += row[51] + "@";
                            list_fecha_recepcion_perm += row[52] + "@";
                            list_fecha_activacion_perm += row[53] + "@";
                            list_fecha_permisos_aut += row[54] + "@";
                            list_co_pref_arancelaria += row[55] + "@";
                            list_aplic_pref_arancelaria += row[56] + "@";
                            list_req_uva += row[57] + "@";
                            list_req_ca += row[58] + "@";
                            list_fecha_recepcion_ca += row[59] + "@";
                            list_num_constancia_ca += row[60] + "@";
                            list_monto_ca += row[61] + "@";
                            list_fecha_doc_completos += row[62] + "@";
                            list_fecha_pago_pedimento += row[63] + "@";
                            list_fecha_solicitud_transporte += row[64] + "@";
                            list_fecha_modulacion += row[65] + "@";
                            list_modalidad += row[66] + "@";
                            list_resultado_modulacion += row[67] + "@";
                            list_fecha_reconocimiento += row[68] + "@";
                            list_fecha_liberacion += row[69] + "@";
                            list_sello_origen += row[70] + "@";
                            list_sello_final += row[71] + "@";
                            list_fecha_retencion_aut += row[72] + "@";
                            list_fecha_liberacion_aut += row[73] + "@";
                            list_estatus_operacion += row[74] + "@";
                            list_motivo_atraso += row[75] + "@";
                            list_observaciones += row[76] + "@";          
                            list_llegada_a_nova += row[77] + "@";
                            list_llegada_a_globe_trade_sd += row[78] + "@";
                            list_archivo_m += row[79] + "@";
                            list_fecha_archivo_m += row[80] + "@";
                            list_fecha_solicit_manip += row[81] + "@";
                            list_fecha_vencim_manip += row[82] + "@";
                            list_fecha_confirm_clave_pedim += row[83] + "@";
                            list_fecha_recep_increment += row[84] + "@";
                            list_t_e += row[85] + "@";
                            list_fecha_vencim_inbound += row[86] + "@";
                            list_no_bultos += row[87] + "@";
                            list_peso_kg += row[88] + "@";
                            list_transferencia += row[89] + "@";
                            list_fecha_inicio_etiquetado += row[90] + "@";
                            list_fecha_termino_etiquetado += row[91] + "@";
                            list_hora_termino_etiquetado += row[92] + "@";
                            list_proveedor += row[93] + "@";
                            list_proveedor_carga += row[94] + "@";
                            list_fy += row[95] + "@";

                        }
                    }
        %>
        <div class="card-body">
            <div class="contenedor">
                <div class="columna1"></div>
                <div class="columna2"></div>
                <div class="columna2"><button type="button" class="btn btn-primary" title="Descargar Excel" id="downloadLinkEventCustoms" onclick="logExcel()"><i class="fa fa-download"></i></button></div>
                <div class="columna4"><button type="button" class="btn btn-primary" title="Limpiar Filtros" id="created_file" onclick="clearFiltres()"><i class="fa fa-traffic-light"></i></button><!--<label class="txtColor">Resolución de Pantalla</label>--></div>
                <div class="columna5"></div>
                <div class="columna6"></div>            
            </div> 
            <div class="scroll-container" id="divAMostrarOcultar">
                <div align="center" id="divResultado" name="divResultado"><%=namePlantilla%></div>
            </div>
            <div id="table-scroll" class="table-scroll"></div>
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
            //Parametros: Validaciones
                let idAgenteAduanal = '<%=AgentType%>'; 
                let fechaActual = '<%=fecha_actual%>';
                let checkboxOn;

            //Parametros: Columnas    
            //Parametros: Columnas    
                let selectElement1 = '<%=list_referenciaAA%>';
                let selectElement2 = '<%=list_evento%>';
                let selectElement3 = '<%=list_responsable%>';
                let selectElement4 = '<%=list_finalDestination%>';
                let selectElement5 = '<%=list_brandDivision%>';
                let selectElement6 = '<%=list_division%>';
                let selectElement7 = '<%=list_shipmentId%>';
                let selectElement8 = '<%=list_containerId%>';
                let selectElement9 = '<%=list_blAwbPro%>';
                let selectElement10 = '<%=list_loadType%>';
                let selectElement11 = '<%=list_quantity%>';
                let selectElement12 = '<%=list_pod%>';
                let selectElement13 = '<%=list_estDepartFromPol%>';
                let selectElement14 = '<%=list_etaRealPortOfDischarge%>';
                let selectElement15 = '<%=list_estEtaDc%>';
                let selectElement16 = '<%=list_inboundNotification%>';
                let selectElement17 = '<%=list_pol%>';
                let selectElement18 = '<%=list_aa%>';
                let selectElement19 = '<%=list_fechaMesVenta%>';
                let selectElement20 = '<%=list_prioridad%>';
                let selectElement21 = '<%=list_pais_origen%>';
                let selectElement22 = '<%=list_size_container%>';
                let selectElement23 = '<%=list_valor_usd%>';
                let selectElement24 = '<%=list_eta_port_discharge%>';
                let selectElement25 = '<%=list_agente_aduanal%>';
                let selectElement26 = '<%=list_pedimento_a1%>';
                let selectElement27 = '<%=list_pedimento_r1_1er%>';
                let selectElement28 = '<%=list_motivo_rectificacion_1er%>';
                let selectElement29 = '<%=list_pedimento_r1_2do%>';
                let selectElement30 = '<%=list_motivo_rectificacion_2do%>';
                let selectElement31 = '<%=list_fecha_recepcion_doc%>';
                let selectElement32 = '<%=list_recinto%>';
                let selectElement33 = '<%=list_naviera%>';
                let selectElement34 = '<%=list_buque%>';
                let selectElement35 = '<%=list_fecha_revalidacion%>';
                let selectElement36 = '<%=list_fecha_previo_origen%>';
                let selectElement37 = '<%=list_fecha_previo_destino%>';
                let selectElement38 = '<%=list_fecha_resultado_previo%>';
                let selectElement39 = '<%=list_proforma_final%>';
                let selectElement40 = '<%=list_permiso%>';
                let selectElement41 = '<%=list_fecha_envio%>';
                let selectElement42 = '<%=list_fecha_recepcion_perm%>';
                let selectElement43 = '<%=list_fecha_activacion_perm%>';
                let selectElement44 = '<%=list_fecha_permisos_aut%>';
                let selectElement45 = '<%=list_co_pref_arancelaria%>';
                let selectElement46 = '<%=list_aplic_pref_arancelaria%>';
                let selectElement47 = '<%=list_req_uva%>';
                let selectElement48 = '<%=list_req_ca%>';
                let selectElement49 = '<%=list_fecha_recepcion_ca%>';
                let selectElement50 = '<%=list_num_constancia_ca%>';
                let selectElement51 = '<%=list_monto_ca%>';
                let selectElement52 = '<%=list_fecha_doc_completos%>';
                let selectElement53 = '<%=list_fecha_pago_pedimento%>';
                let selectElement54 = '<%=list_fecha_solicitud_transporte%>';
                let selectElement55 = '<%=list_fecha_modulacion%>';
                let selectElement56 = '<%=list_modalidad%>';
                let selectElement57 = '<%=list_resultado_modulacion%>';
                let selectElement58 = '<%=list_fecha_reconocimiento%>';
                let selectElement59 = '<%=list_fecha_liberacion%>';
                let selectElement60 = '<%=list_sello_origen%>';
                let selectElement61 = '<%=list_sello_final%>';
                let selectElement62 = '<%=list_fecha_retencion_aut%>';
                let selectElement63 = '<%=list_fecha_liberacion_aut%>';
                let selectElement64 = '<%=list_estatus_operacion%>';
                let selectElement65 = '<%=list_motivo_atraso%>';
                let selectElement66 = '<%=list_observaciones%>';

            if (idAgenteAduanal === '4001' || idAgenteAduanal === '4006') { //LOGIX Y VF      

                let selectElement67 = '<%=list_llegada_a_nova%>';
                let selectElement68 = '<%=list_llegada_a_globe_trade_sd%>';
                let selectElement69 = '<%=list_archivo_m%>';
                let selectElement70 = '<%=list_fecha_archivo_m%>';
                let selectElement71 = '<%=list_fecha_solicit_manip%>';
                let selectElement72 = '<%=list_fecha_vencim_manip%>';
                let selectElement73 = '<%=list_fecha_confirm_clave_pedim%>';
                let selectElement74 = '<%=list_fecha_recep_increment%>';
                let selectElement75 = '<%=list_t_e%>';
                let selectElement76 = '<%=list_fecha_vencim_inbound%>';

            }

            if (idAgenteAduanal === '4002' || idAgenteAduanal === '4006') {  //CUSA Y VF

                let selectElement77 = '<%=list_no_bultos%>';
                let selectElement78 = '<%=list_peso_kg%>';
                let selectElement79 = '<%=list_transferencia%>';
                let selectElement80 = '<%=list_fecha_inicio_etiquetado%>';
                let selectElement81 = '<%=list_fecha_termino_etiquetado%>';
                let selectElement82 = '<%=list_hora_termino_etiquetado%>';
                let selectElement83 = '<%=list_proveedor%>';
                let selectElement84 = '<%=list_proveedor_carga%>';
            }   
                let selectElement85 = '<%=list_fy%>';
        </script>
        <!-- JavaScript files-->
        <script src="<%=request.getContextPath()%>/lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Actions js -->
        <script src="<%=request.getContextPath()%>/lib/validationsInbound/reporteCustoms/customsForms.js" type="text/javascript"></script>
        <!-- Filtrer Checkbox -->
        <script src="<%=request.getContextPath()%>/lib/validationsInbound/reporteCustoms/filtrerCheckbox.js" type="text/javascript"></script>
        <!-- Download Excel -->
        <script src="<%=request.getContextPath()%>/lib/validationsInbound/reporteCustoms/downloadExcel.js" type="text/javascript"></script>
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
