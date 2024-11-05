<%-- 
    Document   : ReporteCustoms_2
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
                     document.getElementById("loaderMsg").innerHTML = "cargando";
                }

            });

            // estado actual
            console.log(document.readyState);

            //imprimir los cambios de estado
            document.addEventListener('readystatechange', () => console.log(document.readyState));
        </script>
    </head>
    <body>
       <!-- <div id="loader-wrapper">
            <div id="loader"></div>
            <div class="loaderMsg" id="loaderMsg"></div>
        </div>-->
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
                
                
                String f = request.getParameter("f");
                String filtro = request.getParameter("f2");
                String col = request.getParameter("col");
                
                
              
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
            
                System.out.println(fac.consultarEventosCustoms(AgentType,"0","0"));
                //Listar columnas para encabeazdos
                if (db.doDB(fac.consultarEventosCustoms(AgentType,"0","0"))) {
                        for (String[] row : db.getResultado()) {
System.out.println("row[0] "+row[0]);
                            list_evento += row[0] + "@";
                            list_referenciaAA += row[30] + "@";
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
                
String  sql = " WITH SUM_QUANTITY AS (SELECT SHIPMENT_ID, CONTAINER1, SUM(QUANTITY) AS SUMA FROM TRA_INC_GTN_TEST GROUP BY SHIPMENT_ID, CONTAINER1) "
                + " SELECT DISTINCT "
                    /*1*/ + " TIE.ID_EVENTO, "
                    /*2*/ + " NVL(BP.RESPONSABLE, ' ') AS RESPONSABLE, "
                    /*3*/ + " GTN.FINAL_DESTINATION, "
                    /*4*/ + " GTN.BRAND_DIVISION, "
                    /*5*/ + " NVL(TID.DIVISION_NOMBRE,' '), "
                    /*6*/ + " GTN.SHIPMENT_ID, "
                    /*7*/ + " GTN.CONTAINER1, "
                    /*8*/ + " GTN.BL_AWB_PRO, "
                    /*9*/ + " GTN.LOAD_TYPE_FINAL, "
                    /*10*/ + "  gtn.CANTIDAD_FINAL, "
                    /*11*/ + " TIP1.NOMBRE_POD, "
                    /*12*/ + " REPLACE(NVL(TO_CHAR(GTN.EST_DEPARTURE_POL, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*13*/ + " REPLACE(NVL(TO_CHAR(GTN.ETA_PORT_DISCHARGE, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*14*/ + " NVL(GTN.MAX_FLETE, 0) AS EST_ETA_DC, "
                    /*15*/ + " REPLACE(NVL(TO_CHAR(GTN.FECHA_CAPTURA, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*16*/ + " TIP2.NOMBRE_POL, "
                    /*17*/ + " NVL(TAA.AGENTE_ADUANAL_NOMBRE, ' ') AS AGENTE_ADUANAL, "
                    /*18*/ + " GTN.PLANTILLA_ID, "
                    /*19*/ + " SYSDATE AS FECHA_CAPTURAOLD, "
                    /*20*/ + " TIP1.NOMBRE_POD, "
                    /*21*/ + " TIP2.NOMBRE_POL, "
                    /*22*/ + " TIBD.NOMBRE_BD, "
                    /*23*/ + " REPLACE(NVL(TO_CHAR(GTN.ETA_PLUS2, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*24*/ + " REPLACE(NVL(TO_CHAR(TIE.EST_ETA_DC, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*25*/ + " REPLACE(NVL(TO_CHAR(GTN.ETA_PLUS, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*26*/ + " NVL(TIE.OBSERVACIONES, ' ') AS OBSERVACIONES, "
                    /*27*/ + " TIE.ESTATUS_EVENTO, "
                    /*28*/ + " NVL(TIE.REFERENCIA_AA,' '), "
                    /*29*/ + " REPLACE(NVL(TO_CHAR(TIE.FECHA_CAPTURA, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*30*/ + " NVL(TIE.PRIORIDAD,' '), "
                    /*31*/ + " NVL(TIC.REFERENCIA_AA,' '), "
                    /*32*/ + " NVL(TIC.PAIS_ORIGEN,' '), "
                    /*33*/ + " NVL(TIC.SIZE_CONTAINER,' '), "
                    /*34*/ + " NVL(TIC.VALOR_USD,' '), "
                    /*35*/ + " REPLACE(NVL(TO_CHAR(TIC.ETA_PORT_OF_DISCHARGE, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*36*/ + " NVL(TIC.AGENTE_ADUANAL,' '), "
                    /*37*/ + " NVL(TIC.PEDIMENTO_A1,' '), "
                    /*38*/ + " NVL(TIC.PEDIMENTO_R1,' '), "
                    /*39*/ + " NVL(TIC.MOTIVO_RECTIFICACION_1,' '), "
                    /*40*/ + " NVL(TIC.PEDIMENTO_R1_2DO,' '), "
                    /*41*/ + " NVL(TIC.MOTIVO_RECTIFICACION_2,' '), "
                    /*42*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_RECEPCION_DOCUMENTOS, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*43*/ + " NVL(TIC.RECINTO,' '), "
                    /*44*/ + " NVL(TIC.NAVIERA_FORWARDER,' '), "
                    /*45*/ + " NVL(TIC.BUQUE,' '), "
                    /*46*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_REVALID_LIBE_BL, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*47*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_PREVIO_ORIGEN, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*48*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_PREVIO_DESTINO, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*49*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_RESULTADO_PREVIO, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*50*/ + " REPLACE(NVL(TO_CHAR(TIC.PROFORMA_FINAL, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*51*/ + " NVL(TIC.REQUIERE_PERMISO,' '), "
                    /*52*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_ENVIO_FICHAS_NOTAS, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*53*/ + " REPLACE(NVL(TO_CHAR(TIC.FEC_RECEPCION_PERMISOS_TRAMIT, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*54*/ + " REPLACE(NVL(TO_CHAR(TIC.FEC_ACT_PERMISOS, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*55*/ + " REPLACE(NVL(TO_CHAR(TIC.FEC_PERM_AUT, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*56*/ + " NVL(TIC.CO_APLIC_PREF_ARANCELARIA,' '), "
                    /*57*/ + " NVL(TIC.APLIC_PREF_ARANCELARIA_CO,' '), "
                    /*58*/ + " NVL(TIC.REQUIERE_UVA,' '), "
                    /*59*/ + " NVL(TIC.REQUIERE_CA,' '), "
                    /*60*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_RECEPCION_CA, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*61*/ + " NVL(TIC.NÚMERO_CONSTANCIA_CA,' '), "
                    /*62*/ + " NVL(TIC.MONTO_CA,' '), "
                    /*63*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_DOCUMENTOS_COMPLETOS, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*64*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_PAGO_PEDIMENTO, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*65*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_SOLICITUD_TRANSPORTE, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*66*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_MODULACION, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*67*/ + " NVL(TIC.MODALIDAD_CAMION_TREN,' '), "
                    /*68*/ + " NVL(TIC.RESULT_MODULACION_VERDE_ROJO,' '), "
                    /*69*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_RECONOCIMIENTO, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*70*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_LIBERACION, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*71*/ + " NVL(TIC.SELLO_ORIGEN,' '), "
                    /*72*/ + " NVL(TIC.SELLO_FINAL,' '), "
                    /*73*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_RETENCION_AUTORIDAD, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*74*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_LIB_POR_RET_AUT, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*75*/ + " NVL(TEC.DESCRIPCION_ESTADO,' '), "
                    /*76*/ + " NVL(TIC.MOTIVO_ATRASO,' '), "
                    /*77*/ + " NVL(TIC.OBSERVACIONES,' '), "
                    /*78*/ + " REPLACE(NVL(TO_CHAR(TIC.LLEGADA_A_NOVA, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*79*/ + " REPLACE(NVL(TO_CHAR(TIC.LLEGADA_A_GLOBE_TRADE_SD, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*80*/ + " NVL(TIC.ARCHIVO_M,' '), "
                    /*81*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_ARCHIVO_M, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*82*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_SOLICIT_MANIP, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*83*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_VENCIM_MANIP, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*84*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_CONFIRM_CLAVE_PEDIM, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*85*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_RECEP_INCREMENT, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*86*/ + " NVL(TIC.T_E,' '), "
                    /*87*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_VENCIM_INBOUND, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*88*/ + " NVL(TIC.NO_BULTOS,' '), "
                    /*89*/ + " NVL(TIC.PESO_KG,' '), "
                    /*90*/ + " NVL(TIC.TRANSFERENCIA,' '), "
                    /*91*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_INICIO_ETIQUETADO, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*92*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_TERMINO_ETIQUETADO, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*93*/ + " NVL(TIC.HORA_TERMINO_ETIQUETADO,' '), "
                    /*94*/ + " NVL(TIC.PROVEEDOR,' '), "
                    /*95*/ + " NVL(TIC.PROVEEDOR_CARGA,' '), "
                    /*96*/ + " NVL(TIC.FY,' '), "
                    /*97*/ + " NVL(TIC.AGENTE_ADUANAL_ID,0), "
                    /*98*/ + " NVL(TIC.PRIORIDAD,'No'), "
                    /*99*/ + " NVL(GTN.ESTATUS,1), "
                   /*100*/ + " NVL(TIC.ESTATUS_SEMAFORO,'0'), "
                   /*101*/ + " NVL(TIP1.ADUANA_NUMERO,0), "
                   /*102*/ + " NVL(TAA.PATENTE_AGENTE_ADUANERO,'0000'), "
                   /*103*/ + " NVL(TO_CHAR(GTN.FECHA_IMPORTACION, 'DD/MM/YY'),'-') "
                            + " FROM TRA_INB_EVENTO TIE "
                            + " INNER JOIN TRA_DESTINO_RESPONSABLE BP ON BP.USER_NID = TIE.USER_NID "
                            + " INNER JOIN TRA_INC_GTN_TEST GTN ON GTN.PLANTILLA_ID = TIE.PLANTILLA_ID "
                            + " INNER JOIN TRA_INB_POD TIP1 ON TIP1.ID_POD = GTN.POD "
                            + " INNER JOIN TRA_INB_POL TIP2 ON TIP2.ID_POL = GTN.POL "
                            + " INNER JOIN TRA_INB_BRAND_DIVISION TIBD ON TIBD.ID_BD = GTN.BRAND_DIVISION "
                            + " INNER JOIN TRA_INB_AGENTE_ADUANAL TAA ON TAA.AGENTE_ADUANAL_ID = TIP1.AGENTE_ADUANAL_ID "
                            + " INNER JOIN TRA_INB_DIVISION TID ON TID.ID_DIVISION = GTN.SBU_NAME "
                            + " INNER JOIN SUM_QUANTITY SQ ON SQ.SHIPMENT_ID = GTN.SHIPMENT_ID AND SQ.CONTAINER1 = GTN.CONTAINER1 "
                            + " LEFT JOIN TRA_INB_CUSTOMS TIC ON GTN.SHIPMENT_ID = TIC.SHIPMENT_ID "
                            + " LEFT JOIN TRA_ESTADOS_CUSTOMS TEC ON GTN.ESTATUS = TEC.ID_ESTADO "
                            + " LEFT JOIN TRA_INB_SEMAFORO TISE ON TIC.SHIPMENT_ID = TISE.SHIPMENT_ID "
                            + " WHERE TIE.ESTADO = 1 "
                       //     + " AND to_date(trunc(tie.FECHA_CAPTURA),'dd/mm/yy') >= to_date((SELECT MIN(TO_DATE(FECHA_CAPTURA, 'DD/MM/YY')) FROM TRA_INB_EVENTO WHERE ESTADO = 1),'dd/mm/yy') "
                       //      + " AND to_date(trunc(tie.FECHA_CAPTURA),'dd/mm/yy') <= to_date((SELECT MAX(TO_DATE(FECHA_CAPTURA, 'DD/MM/YY')) FROM TRA_INB_EVENTO WHERE ESTADO = 1),'dd/mm/yy') "
                       //    + " AND tid.division_nombre <> 'No/DSN' "
                     // + " AND gtn.load_type_final IS NOT NULL "
                            + " AND tie.id_evento >= 240000  and GTN.FINAL_DESTINATION not in ('OD1013 MARKETING MEXICO') "
        + " AND TIP1.AGENTE_ADUANAL_ID IN (" + filtro + ") ORDER BY tie.id_evento, tibd.nombre_bd, GTN.SHIPMENT_ID ASC" ;
        %>
        <div class="card-body">
            <div class="contenedor">
                <div class="columna1"></div>
                <div class="columna2"></div>
                <!--
                <div class="columna2"><button type="button" class="btn btn-primary" title="Descargar Excel" id="downloadLinkEventCustoms" onclick="logExcel()"><i class="fa fa-download"></i></button></div>
                <div class="columna4"><button type="button" class="btn btn-primary" title="Limpiar Filtros" id="created_file" onclick="clearFiltres()"><i class="fa fa-traffic-light"></i></button><!--<label class="txtColor">Resolución de Pantalla</label></div>
               -->
                <div class="columna5"></div>
                <div class="columna6"></div>            
            </div> 
            <div class="scroll-container" id="divAMostrarOcultar">
                <div align="center" id="divResultado" name="divResultado"><%=namePlantilla%></div>
            </div>
            <div id="table-scroll" class="table-scroll">
                
                <table id="tabl">
                    <thead>  
                 <tr>   
                      <th class= "col-sm-4 " style= "background-color:#333F4F; ">Referencia AA                                            </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Evento                                                   </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Responsable                                              </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Final Destination                                        </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Brand-Division                                           </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Division                                                 </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Shipment ID                                              </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Container                                                </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">BL/AWB/PRO                                               </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">LoadType                                                 </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Quantity                                                 </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">POD                                                      </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Est. Departure from POL                                  </th> 
                     <th class= "col-sm-5 " style= "background-color:#1C84C6; ">ETA REAL Port of Discharge                               </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Est. Eta DC                                              </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Inbound notification                                     </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">POL                                                      </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">A.A.                                                     </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Fecha Mes de Venta                                       </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Prioridad Si/No                                          </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">País Origen                                              </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Size Container                                           </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Valor USD                                                </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">ETA Port Of Discharge                                    </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Agente Aduanal                                           </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Pedimento A1                                             </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Pedimento R1                                             </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Motivo rectificación 1                                   </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Pedimento R1 (2do)                                       </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Motivo rectificación 2                                   </th> 
                     <th class= "col-sm-5 " style= "background-color:#CC9D77; ">Fecha Recepción Documentos                               </th> 
                     <th class= "col-sm-4 " style= "background-color:#e04141; ">Recinto                                                  </th> 
                     <th class= "col-sm-4 " style= "background-color:#e04141; ">Naviera / Forwarder                                      </th> 
                     <th class= "col-sm-4 " style= "background-color:#e04141; ">Buque                                                    </th> 
                     <th class= "col-sm-5 " style= "background-color:#CC9D77; ">Fecha Revalidación/Liberación de BL                      </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Fecha Previo Origen                                      </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Fecha Previo en destino                                  </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Fecha Resultado Previo                                   </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Proforma Final                                           </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Requiere permiso                                         </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Fecha envío Fichas/notas                                 </th> 
                     <th class= "col-sm-5 " style= "background-color:#CC9D77; ">Fec. Recepción de permisos tramit.                       </th> 
                     <th class= "col-sm-5 " style= "background-color:#CC9D77; ">Fec. Act Permisos (Inic Vigencia)                        </th> 
                     <th class= "col-sm-5 " style= "background-color:#CC9D77; ">Fec. Perm. Aut. (Fin de Vigencia)                        </th> 
                     <th class= "col-sm-6 " style= "background-color:#CC9D77; ">Cuenta con CO para aplicar preferencia Arancelaria       </th> 
                     <th class= "col-sm-5 " style= "background-color:#CC9D77; ">Aplico Preferencia Arancelaria                           </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Requiere UVA                                             </th> 
                     <th class= "col-sm-4 " style= "background-color:#a6a2a2; ">Requiere CA                                              </th> 
                     <th class= "col-sm-4 " style= "background-color:#a6a2a2; ">Fecha Recepción CA                                       </th> 
                     <th class= "col-sm-4 " style= "background-color:#a6a2a2; ">Número de Constancia CA                                  </th> 
                     <th class= "col-sm-4 " style= "background-color:#a6a2a2; ">Monto CA                                                 </th> 
                     <th class= "col-sm-5 " style= "background-color:#CC9D77; ">Fecha Documentos Completos                               </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Fecha Pago Pedimento                                     </th> 
                     <th class= "col-sm-5 " style= "background-color:#CC9D77; ">Fecha Solicitud de transporte                            </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Fecha Modulacion                                         </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Modalidad                                                </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Resultado Modulacion                                     </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Fecha Reconocimiento                                     </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Fecha Liberacion                                         </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Sello Origen                                             </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Sello Final                                              </th> 
                     <th class= "col-sm-5 " style= "background-color:#CC9D77; ">Fecha de retencion por la autoridad                      </th> 
                     <th class= "col-sm-5 " style= "background-color:#CC9D77; ">Fec. de liberacion por ret. de la aut.                   </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Estatus de la operación                                  </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Motivo Atraso                                            </th> 
                     <th class= "col-sm-4 " style= "background-color:#CC9D77; ">Observaciones                                            </th>                                  
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Llegada a NOVA                                           </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Llegada a Globe trade SD                                 </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Archivo M                                                </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Fecha de Archivo M                                       </th> 
                     <th class= "col-sm-5 " style= "background-color:#1C84C6; ">Fecha Solicitud de Manipulacion                          </th> 
                     <th class= "col-sm-6 " style= "background-color:#1C84C6; ">Fecha de vencimiento de Manipulacion                     </th> 
                     <th class= "col-sm-6 " style= "background-color:#1C84C6; ">Fecha confirmacion Clave de Pedimento                    </th> 
                     <th class= "col-sm-6 " style= "background-color:#1C84C6; ">Fecha de Recepcion de Incrementables                     </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">T&E                                                      </th> 
                     <th class= "col-sm-5 " style= "background-color:#1C84C6; ">Fecha de Vencimiento del Inbound                         </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">No. BULTOS                                               </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Peso (KG)                                                </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Transferencia                                            </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Fecha Inicio Etiquetado                                  </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Fecha Termino Etiquetado                                 </th> 
                     <th class= "col-sm-5 " style= "background-color:#1C84C6; ">Hora de termino Etiquetado                               </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Proveedor                                                </th> 
                     <th class= "col-sm-4 " style= "background-color:#1C84C6; ">Proveedor de Carga                                       </th> 
                 </tr> 
             </thead> 
             <tbody>
                 <%
                  if (db.doDB(sql)) {
                        for (String[] row : db.getResultado()) {
                            
                         %>
                 
                      <tr id="tr"  >
      
      <th>  <%=row[30]%>   </th> 
      <th>  <%=row[0]%>  </th> 
      <td>  <%=row[1]%>  </td > 
      <td>  <%=row[2]%>  </td > 
      <td>  <%=row[21]%>  </td > 
      <td>  <%=row[4]%>  </td > 
      <td>  <%=row[5]%>  </td > 
      <td>  <%=row[6]%>  </td > 
      <td>  <%=row[7]%>  </td > 
      <td>  <%=row[8]%>  </td > 
      <td>  <%=row[9]%>  </td > 
      <td>  <%=row[19]%>  </td > 
      <td>  <%=row[11]%>  </td > 
      <td>  <%=row[12]%>  </td > 
      <td>  <%=row[22]%>  </td > 
      <td>  <%=row[14]%>  </td > 
      <td>  <%=row[20]%>  </td > 
      <td>  <%=row[16]%>  </td > 
      <td>  <%=row[28]%>  </td > 
      <td>  <%=row[97]%>  </td > 
      <td>  <%=row[31]%>  </td > 
      <td>  <%=row[32]%>  </td > 
      <td>  <%=row[33]%>  </td > 
      <td>  <%=row[34]%>  </td > 
      <td>  <%=row[35]%>  </td > 
      <td>  <%=row[36]%>  </td > 
      <td>  <%=row[37]%>  </td > 
      <td>  <%=row[38]%>  </td > 
      <td>  <%=row[39]%>  </td > 
      <td>  <%=row[40]%>  </td > 
      <td>  <%=row[41]%>  </td > 
      <td>  <%=row[42]%>  </td > 
      <td>  <%=row[43]%>  </td > 
      <td>  <%=row[44]%>  </td > 
      <td>  <%=row[45]%>  </td > 
      <td>  <%=row[46]%>  </td > 
      <td>  <%=row[47]%>  </td > 
      <td>  <%=row[48]%>  </td > 
      <td>  <%=row[49]%>  </td > 
      <td>  <%=row[50]%>  </td > 
      <td>  <%=row[51]%>  </td > 
      <td>  <%=row[52]%>  </td > 
      <td>  <%=row[53]%>  </td > 
      <td>  <%=row[54]%>  </td > 
      <td>  <%=row[55]%>  </td > 
      <td>  <%=row[56]%>  </td > 
      <td>  <%=row[57]%>  </td > 
      <td>  <%=row[58]%>  </td > 
      <td>  <%=row[59]%>  </td > 
      <td>  <%=row[60]%>  </td > 
      <td>  <%=row[61]%>  </td > 
      <td>  <%=row[62]%>  </td > 
      <td>  <%=row[63]%>  </td > 
      <td>  <%=row[64]%>  </td > 
      <td>  <%=row[65]%>  </td > 
      <td>  <%=row[66]%>  </td > 
      <td>  <%=row[67]%>  </td > 
      <td>  <%=row[68]%>  </td > 
      <td>  <%=row[69]%>  </td > 
      <td>  <%=row[70]%>  </td > 
      <td>  <%=row[71]%>  </td > 
      <td>  <%=row[72]%>  </td > 
      <td>  <%=row[73]%>  </td > 
      <td>  <%=row[74]%>  </td > 
      <td>  <%=row[75]%>  </td > 
      <td>  <%=row[76]%>  </td > 
      <td>  <%=row[77]%>  </td > 
      <td>  <%=row[78]%>  </td > 
      <td>  <%=row[79]%>  </td > 
      <td>  <%=row[80]%>  </td > 
      <td>  <%=row[81]%>  </td > 
      <td>  <%=row[82]%>  </td > 
      <td>  <%=row[83]%>  </td > 
      <td>  <%=row[84]%>  </td > 
      <td>  <%=row[85]%>  </td > 
      <td>  <%=row[86]%>  </td > 
      <td>  <%=row[87]%>  </td > 
      <td>  <%=row[88]%>  </td > 
      <td>  <%=row[89]%>  </td > 
      <td>  <%=row[90]%>  </td > 
      <td>  <%=row[91]%>  </td > 
      <td>  <%=row[92]%>  </td > 
      <td>  <%=row[93]%>  </td > 
      <td>  <%=row[94]%>  </td> 
   </tr>
                 
                 <%   
                        }
                        }
                 %>
                 
             </tbody>
                </table>
                
                
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
                
                
                
                
             function ocultar(){   
                // Seleccionar todas las filas de la tabla
const filas = document.querySelectorAll("#tabl tr");

// Especificar los índices de las filas que quieres mostrar (ten en cuenta que el índice empieza en 0)
const columnasAMostrar  = [<%=col%>];

filas.forEach(fila => {
    const celdas = fila.children; // Obtener todas las celdas en la fila
    for (let i = 0; i < celdas.length; i++) {
        if (!columnasAMostrar.includes(i)) {
            celdas[i].style.display = "none"; // Ocultar columna si no está en la lista de mostrar
        } else {
            celdas[i].style.display = ""; // Mostrar columna si está en la lista de mostrar
        }
    }
});

             }
             
             ocultar();

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
