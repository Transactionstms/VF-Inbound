/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Contadores
let urlCustoms = "";
let contModals = 0;
let AgenteId = "";

//Parametros Indicadores
let evento;
let shipmentId;
let containerId;
let referenciaAA;
let prioridad;
let loadTypeFinal;
let plantillaId;

//Parametros Generales
let pais_origen;
let size_container;
let valor_usd;
let eta_port_discharge;
let agente_aduanal;
let pedimento_a1;
let pedimento_r1_1er;
let motivo_rectificacion_1er;
let pedimento_r1_2do;
let motivo_rectificacion_2do;
let fecha_recepcion_doc;
let recinto;
let naviera;
let buque;
let fecha_revalidacion;
let fecha_previo_origen;
let fecha_previo_destino;
let fecha_resultado_previo;
let proforma_final;
let permiso;
let fecha_envio;
let fecha_recepcion_perm;
let fecha_activacion_perm;
let fecha_permisos_aut;
let co_pref_arancelaria;
let aplic_pref_arancelaria;
let req_uva;
let req_ca;
let fecha_recepcion_ca;
let num_constancia_ca;
let monto_ca;
let fecha_doc_completos;
let fecha_pago_pedimento;
let fecha_solicitud_transporte;
let fecha_modulacion;
let modalidad;
let resultado_modulacion;
let fecha_reconocimiento;
let fecha_liberacion;
let sello_origen;
let sello_final;
let fecha_retencion_aut;
let fecha_liberacion_aut;
let estatus_operacion;
let motivo_atraso;
let observaciones;
let fy;

//Parametros Logix
let llegada_a_nova;
let llegada_a_globe_trade_sd;
let archivo_m;
let fecha_archivo_m;
let fecha_solicit_manip;
let fecha_vencim_manip;
let fecha_confirm_clave_pedim;
let fecha_recep_increment;
let t_e;
let fecha_vencim_inbound;

//Parametros Cusa
let no_bultos;
let peso_kg;
let transferencia;
let fecha_inicio_etiquetado;
let fecha_termino_etiquetado;
let hora_termino_etiquetado;
let proveedor;
let proveedor_carga;
let cont = 1;

//Parametros upload excel
let tipo_elemento = "";
let tipo_clase = "";
let tipo_escritura = "";
let contadorExcel = "";

/*
  Parametros filtros
  Parametros Indicadores
 */
let selected_referenciaAA="";
let selected_evento="";
let selected_responsable="";
let selected_final_destination="";
let selected_brand_division="";
let selected_division="";
let selected_shipmentId="";
let selected_containerId="";
let selected_blAwbPro="";
let selected_loadTypeFinal="";
let selected_quantity="";
let selected_pod="";
let selected_estDepartFromPol="";
let selected_etaRealPortOfDischarge="";
let selected_estEtaDc="";
let selected_inboundNotification="";
let selected_pol="";
let selected_aa="";
let selected_fechaMesVenta="";
let selected_prioridad="";

//Parametros Generales
let selected_pais_origen="";
let selected_size_container="";
let selected_valor_usd="";
let selected_eta_port_discharge="";
let selected_agente_aduanal="";
let selected_pedimento_a1="";
let selected_pedimento_r1_1er="";
let selected_motivo_rectificacion_1er="";
let selected_pedimento_r1_2do="";
let selected_motivo_rectificacion_2do="";
let selected_fecha_recepcion_doc="";
let selected_recinto="";
let selected_naviera="";
let selected_buque="";
let selected_fecha_revalidacion="";
let selected_fecha_previo_origen="";
let selected_fecha_previo_destino="";
let selected_fecha_resultado_previo="";
let selected_proforma_final="";
let selected_permiso="";
let selected_fecha_envio="";
let selected_fecha_recepcion_perm="";
let selected_fecha_activacion_perm="";
let selected_fecha_permisos_aut="";
let selected_co_pref_arancelaria="";
let selected_aplic_pref_arancelaria="";
let selected_req_uva="";
let selected_req_ca="";
let selected_fecha_recepcion_ca="";
let selected_num_constancia_ca="";
let selected_monto_ca="";
let selected_fecha_doc_completos="";
let selected_fecha_pago_pedimento="";
let selected_fecha_solicitud_transporte="";
let selected_fecha_modulacion="";
let selected_modalidad="";
let selected_resultado_modulacion="";
let selected_fecha_reconocimiento="";
let selected_fecha_liberacion="";
let selected_sello_origen="";
let selected_sello_final="";
let selected_fecha_retencion_aut="";
let selected_fecha_liberacion_aut="";
let selected_estatus_operacion="";
let selected_motivo_atraso="";
let selected_observaciones="";

//Parametros Logix
let selected_llegada_a_nova="";
let selected_llegada_a_globe_trade_sd="";
let selected_archivo_m="";
let selected_fecha_archivo_m="";
let selected_fecha_solicit_manip="";
let selected_fecha_vencim_manip="";
let selected_fecha_confirm_clave_pedim="";
let selected_fecha_recep_increment="";
let selected_t_e="";
let selected_fecha_vencim_inbound="";

//Parametros Cusa
let selected_no_bultos="";
let selected_peso_kg="";
let selected_transferencia="";
let selected_fecha_inicio_etiquetado="";
let selected_fecha_termino_etiquetado="";
let selected_hora_termino_etiquetado="";
let selected_proveedor="";
let selected_proveedor_carga="";

let selected_fy="";

var selected1 = "0";
var selected2 = "0";
var selected3 = "0";
var selected4 = "0";
var selected5 = "0";
var selected6 = "0";
var selected7 = "0";
var selected8 = "0";
var selected9 = "0";
var selected10 = "0";
var selected11 = "0";
var selected12 = "0";
var selected13 = "0";
var selected14 = "0";
var selected15 = "0";
var selected16 = "0";
var selected17 = "0";
var selected18 = "0";
var selected19 = "0";
var selected20 = "0";
var selected21 = "0";
var selected22 = "0";
var selected23 = "0";
var selected24 = "0";
var selected25 = "0";
var selected26 = "0";
var selected27 = "0";
var selected28 = "0";
var selected29 = "0";
var selected30 = "0";
var selected31 = "0";
var selected32 = "0";
var selected33 = "0";
var selected34 = "0";
var selected35 = "0";
var selected36 = "0";
var selected37 = "0";
var selected38 = "0";
var selected39 = "0";
var selected40 = "0";
var selected41 = "0";
var selected42 = "0";
var selected43 = "0";
var selected44 = "0";
var selected45 = "0";
var selected46 = "0";
var selected47 = "0";
var selected48 = "0";
var selected49 = "0";
var selected50 = "0";
var selected51 = "0";
var selected52 = "0";
var selected53 = "0";
var selected54 = "0";
var selected55 = "0";
var selected56 = "0";
var selected57 = "0";
var selected58 = "0";
var selected59 = "0";
var selected60 = "0";
var selected61 = "0";
var selected62 = "0";
var selected63 = "0";
var selected64 = "0";
var selected65 = "0";
var selected66 = "0";
var selected67 = "0";
var selected68 = "0";
var selected69 = "0";
var selected70 = "0";
var selected71 = "0";
var selected72 = "0";
var selected73 = "0";
var selected74 = "0";
var selected75 = "0";
var selected76 = "0";
var selected77 = "0";
var selected78 = "0";
var selected79 = "0";
var selected80 = "0";
var selected81 = "0";
var selected82 = "0";
var selected83 = "0";
var selected84 = "0";
var selected85 = "0";


    $(document).ready(async function () {
        
        idAgenteAduanal;
        
         await consultarCustoms(idAgenteAduanal, "0",
                                selected_referenciaAA, 
                                selected_evento, 
                                selected_responsable,
                                selected_final_destination,
                                selected_brand_division,
                                selected_division,
                                selected_shipmentId,
                                selected_containerId,
                                selected_blAwbPro,
                                selected_loadTypeFinal,
                                selected_quantity,
                                selected_pod,
                                selected_estDepartFromPol,
                                selected_etaRealPortOfDischarge,
                                selected_estEtaDc,
                                selected_inboundNotification,
                                selected_pol,
                                selected_aa,
                                selected_fechaMesVenta,
                                selected_prioridad,
                                selected_pais_origen,
                                selected_size_container,
                                selected_valor_usd,
                                selected_eta_port_discharge,
                                selected_agente_aduanal,
                                selected_pedimento_a1,
                                selected_pedimento_r1_1er,
                                selected_motivo_rectificacion_1er,
                                selected_pedimento_r1_2do,
                                selected_motivo_rectificacion_2do,
                                selected_fecha_recepcion_doc,
                                selected_recinto,
                                selected_naviera,
                                selected_buque,
                                selected_fecha_revalidacion,
                                selected_fecha_previo_origen,
                                selected_fecha_previo_destino,
                                selected_fecha_resultado_previo,
                                selected_proforma_final,
                                selected_permiso,
                                selected_fecha_envio,
                                selected_fecha_recepcion_perm,
                                selected_fecha_activacion_perm,
                                selected_fecha_permisos_aut,
                                selected_co_pref_arancelaria,
                                selected_aplic_pref_arancelaria,
                                selected_req_uva,
                                selected_req_ca,
                                selected_fecha_recepcion_ca,
                                selected_num_constancia_ca,
                                selected_monto_ca,
                                selected_fecha_doc_completos,
                                selected_fecha_pago_pedimento,
                                selected_fecha_solicitud_transporte,
                                selected_fecha_modulacion,
                                selected_modalidad,
                                selected_resultado_modulacion,
                                selected_fecha_reconocimiento,
                                selected_fecha_liberacion,
                                selected_sello_origen,
                                selected_sello_final,
                                selected_fecha_retencion_aut,
                                selected_fecha_liberacion_aut,
                                selected_estatus_operacion,
                                selected_motivo_atraso,
                                selected_observaciones,
                                selected_llegada_a_nova,
                                selected_llegada_a_globe_trade_sd,
                                selected_archivo_m,
                                selected_fecha_archivo_m,
                                selected_fecha_solicit_manip,
                                selected_fecha_vencim_manip,
                                selected_fecha_confirm_clave_pedim,
                                selected_fecha_recep_increment,
                                selected_t_e,
                                selected_fecha_vencim_inbound,
                                selected_no_bultos,
                                selected_peso_kg,
                                selected_transferencia,
                                selected_fecha_inicio_etiquetado,
                                selected_fecha_termino_etiquetado,
                                selected_hora_termino_etiquetado,
                                selected_proveedor,
                                selected_proveedor_carga,
                                selected_fy);
    });
            
    async function consultarCustoms(idAgenteAduanal, filterType,  
                                    selected_referenciaAA,
                                    selected_evento,
                                    selected_responsable,
                                    selected_final_destination,
                                    selected_brand_division,
                                    selected_division,
                                    selected_shipmentId,
                                    selected_containerId,
                                    selected_blAwbPro,
                                    selected_loadTypeFinal,
                                    selected_quantity,
                                    selected_pod,
                                    selected_estDepartFromPol,
                                    selected_etaRealPortOfDischarge,
                                    selected_estEtaDc,
                                    selected_inboundNotification,
                                    selected_pol,
                                    selected_aa,
                                    selected_fechaMesVenta,
                                    selected_prioridad,
                                    selected_pais_origen,
                                    selected_size_container,
                                    selected_valor_usd,
                                    selected_eta_port_discharge,
                                    selected_agente_aduanal,
                                    selected_pedimento_a1,
                                    selected_pedimento_r1_1er,
                                    selected_motivo_rectificacion_1er,
                                    selected_pedimento_r1_2do,
                                    selected_motivo_rectificacion_2do,
                                    selected_fecha_recepcion_doc,
                                    selected_recinto,
                                    selected_naviera,
                                    selected_buque,
                                    selected_fecha_revalidacion,
                                    selected_fecha_previo_origen,
                                    selected_fecha_previo_destino,
                                    selected_fecha_resultado_previo,
                                    selected_proforma_final,
                                    selected_permiso,
                                    selected_fecha_envio,
                                    selected_fecha_recepcion_perm,
                                    selected_fecha_activacion_perm,
                                    selected_fecha_permisos_aut,
                                    selected_co_pref_arancelaria,
                                    selected_aplic_pref_arancelaria,
                                    selected_req_uva,
                                    selected_req_ca,
                                    selected_fecha_recepcion_ca,
                                    selected_num_constancia_ca,
                                    selected_monto_ca,
                                    selected_fecha_doc_completos,
                                    selected_fecha_pago_pedimento,
                                    selected_fecha_solicitud_transporte,
                                    selected_fecha_modulacion,
                                    selected_modalidad,
                                    selected_resultado_modulacion,
                                    selected_fecha_reconocimiento,
                                    selected_fecha_liberacion,
                                    selected_sello_origen,
                                    selected_sello_final,
                                    selected_fecha_retencion_aut,
                                    selected_fecha_liberacion_aut,
                                    selected_estatus_operacion,
                                    selected_motivo_atraso,
                                    selected_observaciones,
                                    selected_llegada_a_nova,
                                    selected_llegada_a_globe_trade_sd,
                                    selected_archivo_m,
                                    selected_fecha_archivo_m,
                                    selected_fecha_solicit_manip,
                                    selected_fecha_vencim_manip,
                                    selected_fecha_confirm_clave_pedim,
                                    selected_fecha_recep_increment,
                                    selected_t_e,
                                    selected_fecha_vencim_inbound,
                                    selected_no_bultos,
                                    selected_peso_kg,
                                    selected_transferencia,
                                    selected_fecha_inicio_etiquetado,
                                    selected_fecha_termino_etiquetado,
                                    selected_hora_termino_etiquetado,
                                    selected_proveedor,
                                    selected_proveedor_carga,
                                    selected_fy) {
        try {
            
            const response = await fetch("../ConsultarCustoms?AgentType="+idAgenteAduanal
                                        +"&filterType=" + filterType
                                        +"&selected_referenciaAA="+selected_referenciaAA
                                        +"&selected_evento="+selected_evento
                                        +"&selected_responsable="+selected_responsable
                                        +"&selected_final_destination="+selected_final_destination
                                        +"&selected_brand_division="+selected_brand_division
                                        +"&selected_division="+selected_division
                                        +"&selected_shipmentId="+selected_shipmentId
                                        +"&selected_containerId="+selected_containerId
                                        +"&selected_blAwbPro="+selected_blAwbPro
                                        +"&selected_loadTypeFinal="+selected_loadTypeFinal
                                        +"&selected_quantity="+selected_quantity
                                        +"&selected_pod="+selected_pod
                                        +"&selected_estDepartFromPol="+selected_estDepartFromPol
                                        +"&selected_etaRealPortOfDischarge="+selected_etaRealPortOfDischarge
                                        +"&selected_estEtaDc="+selected_estEtaDc
                                        +"&selected_inboundNotification="+selected_inboundNotification
                                        +"&selected_pol="+selected_pol
                                        +"&selected_aa="+selected_aa
                                        +"&selected_fechaMesVenta="+selected_fechaMesVenta
                                        +"&selected_prioridad="+selected_prioridad
                                        +"&selected_pais_origen="+selected_pais_origen
                                        +"&selected_size_container="+selected_size_container
                                        +"&selected_valor_usd="+selected_valor_usd
                                        +"&selected_eta_port_discharge="+selected_eta_port_discharge
                                        +"&selected_agente_aduanal="+selected_agente_aduanal
                                        +"&selected_pedimento_a1="+selected_pedimento_a1
                                        +"&selected_pedimento_r1_1er="+selected_pedimento_r1_1er
                                        +"&selected_motivo_rectificacion_1er="+selected_motivo_rectificacion_1er
                                        +"&selected_pedimento_r1_2do="+selected_pedimento_r1_2do
                                        +"&selected_motivo_rectificacion_2do="+selected_motivo_rectificacion_2do
                                        +"&selected_fecha_recepcion_doc="+selected_fecha_recepcion_doc
                                        +"&selected_recinto="+selected_recinto
                                        +"&selected_naviera="+selected_naviera
                                        +"&selected_buque="+selected_buque
                                        +"&selected_fecha_revalidacion="+selected_fecha_revalidacion
                                        +"&selected_fecha_previo_origen="+selected_fecha_previo_origen
                                        +"&selected_fecha_previo_destino="+selected_fecha_previo_destino
                                        +"&selected_fecha_resultado_previo="+selected_fecha_resultado_previo
                                        +"&selected_proforma_final="+selected_proforma_final
                                        +"&selected_permiso="+selected_permiso
                                        +"&selected_fecha_envio="+selected_fecha_envio
                                        +"&selected_fecha_recepcion_perm="+selected_fecha_recepcion_perm
                                        +"&selected_fecha_activacion_perm="+selected_fecha_activacion_perm
                                        +"&selected_fecha_permisos_aut="+selected_fecha_permisos_aut
                                        +"&selected_co_pref_arancelaria="+selected_co_pref_arancelaria
                                        +"&selected_aplic_pref_arancelaria="+selected_aplic_pref_arancelaria
                                        +"&selected_req_uva="+selected_req_uva
                                        +"&selected_req_ca="+selected_req_ca
                                        +"&selected_fecha_recepcion_ca="+selected_fecha_recepcion_ca
                                        +"&selected_num_constancia_ca="+selected_num_constancia_ca
                                        +"&selected_monto_ca="+selected_monto_ca
                                        +"&selected_fecha_doc_completos="+selected_fecha_doc_completos
                                        +"&selected_fecha_pago_pedimento="+selected_fecha_pago_pedimento
                                        +"&selected_fecha_solicitud_transporte="+selected_fecha_solicitud_transporte
                                        +"&selected_fecha_modulacion="+selected_fecha_modulacion
                                        +"&selected_modalidad="+selected_modalidad
                                        +"&selected_resultado_modulacion="+selected_resultado_modulacion
                                        +"&selected_fecha_reconocimiento="+selected_fecha_reconocimiento
                                        +"&selected_fecha_liberacion="+selected_fecha_liberacion
                                        +"&selected_sello_origen="+selected_sello_origen
                                        +"&selected_sello_final="+selected_sello_final
                                        +"&selected_fecha_retencion_aut="+selected_fecha_retencion_aut
                                        +"&selected_fecha_liberacion_aut="+selected_fecha_liberacion_aut
                                        +"&selected_estatus_operacion="+selected_estatus_operacion
                                        +"&selected_motivo_atraso="+selected_motivo_atraso
                                        +"&selected_observaciones="+selected_observaciones
                                        +"&selected_llegada_a_nova="+selected_llegada_a_nova
                                        +"&selected_llegada_a_globe_trade_sd="+selected_llegada_a_globe_trade_sd
                                        +"&selected_archivo_m="+selected_archivo_m
                                        +"&selected_fecha_archivo_m="+selected_fecha_archivo_m
                                        +"&selected_fecha_solicit_manip="+selected_fecha_solicit_manip
                                        +"&selected_fecha_vencim_manip="+selected_fecha_vencim_manip
                                        +"&selected_fecha_confirm_clave_pedim="+selected_fecha_confirm_clave_pedim
                                        +"&selected_fecha_recep_increment="+selected_fecha_recep_increment
                                        +"&selected_t_e="+selected_t_e
                                        +"&selected_fecha_vencim_inbound="+selected_fecha_vencim_inbound
                                        +"&selected_no_bultos="+selected_no_bultos
                                        +"&selected_peso_kg="+selected_peso_kg
                                        +"&selected_transferencia="+selected_transferencia
                                        +"&selected_fecha_inicio_etiquetado="+selected_fecha_inicio_etiquetado
                                        +"&selected_fecha_termino_etiquetado="+selected_fecha_termino_etiquetado
                                        +"&selected_hora_termino_etiquetado="+selected_hora_termino_etiquetado
                                        +"&selected_proveedor="+selected_proveedor
                                        +"&selected_proveedor_carga="+selected_proveedor_carga
                                        +"&selected_fy="+selected_fy);
            if (!response.ok) {
                throw new Error('Error en la solicitud');
            }

            const data = await response.text();
            document.getElementById('table-scroll').innerHTML = data;
            
            $('#col_referenciaAA').select2(); 
            $('#col_evento').select2();
            $('#col_responsable').select2();
            $('#col_finalDestination').select2();
            $('#col_brandDivision').select2();
            $('#col_division').select2();
            $('#col_shipmentId').select2();
            $('#col_container').select2();
            $('#col_blAwbPro').select2();
            $('#col_loadType').select2();
            $('#col_quantity').select2();
            $('#col_pod').select2();
            $('#col_estDepartFromPol').select2();
            $('#col_etaRealPortOfDischarge').select2();
            $('#col_estEtaDc').select2();
            $('#col_inboundNotification').select2();
            $('#col_pol').select2();
            $('#col_aa').select2();
            $('#col_fechaMesVenta').select2();
            $('#col_prioridad').select2();
            $('#col_pais_origen').select2();
            $('#col_size_container').select2();
            $('#col_valor_usd').select2();
            $('#col_eta_port_discharge').select2();
            $('#col_agente_aduanal').select2();
            $('#col_pedimento_a1').select2();
            $('#col_pedimento_r1_1er').select2();
            $('#col_motivo_rectificacion_1er').select2();
            $('#col_pedimento_r1_2do').select2();
            $('#col_motivo_rectificacion_2do').select2();
            $('#col_fecha_recepcion_doc').select2();
            $('#col_recinto').select2();
            $('#col_naviera').select2();
            $('#col_buque').select2();
            $('#col_fecha_revalidacion').select2();
            $('#col_fecha_previo_origen').select2();
            $('#col_fecha_previo_destino').select2();
            $('#col_fecha_resultado_previo').select2();
            $('#col_proforma_final').select2();
            $('#col_permiso').select2();
            $('#col_fecha_envio').select2();
            $('#col_fecha_recepcion_perm').select2();
            $('#col_fecha_activacion_perm').select2();
            $('#col_fecha_permisos_aut').select2();
            $('#col_co_pref_arancelaria').select2();
            $('#col_aplic_pref_arancelaria').select2();
            $('#col_req_uva').select2();
            $('#col_req_ca').select2();
            $('#col_fecha_recepcion_ca').select2();
            $('#col_num_constancia_ca').select2();
            $('#col_monto_ca').select2();
            $('#col_fecha_doc_completos').select2();
            $('#col_fecha_pago_pedimento').select2();
            $('#col_fecha_solicitud_transporte').select2();
            $('#col_fecha_modulacion').select2();
            $('#col_modalidad').select2();
            $('#col_resultado_modulacion').select2();
            $('#col_fecha_reconocimiento').select2();
            $('#col_fecha_liberacion').select2();
            $('#col_sello_origen').select2();
            $('#col_sello_final').select2();
            $('#col_fecha_retencion_aut').select2();
            $('#col_fecha_liberacion_aut').select2();
            $('#col_estatus_operacion').select2();
            $('#col_motivo_atraso').select2();
            $('#col_observaciones').select2();
            
        if (idAgenteAduanal === "4001" || idAgenteAduanal === "4006") { //LOGIX Y VF    
            
            $('#col_llegada_a_nova').select2();
            $('#col_llegada_a_globe_trade_sd').select2();
            $('#col_archivo_m').select2();
            $('#col_fecha_archivo_m').select2();
            $('#col_fecha_solicit_manip').select2();
            $('#col_fecha_vencim_manip').select2();
            $('#col_fecha_confirm_clave_pedim').select2();
            $('#col_fecha_recep_increment').select2();
            $('#col_t_e').select2();
            $('#col_fecha_vencim_inbound').select2();
            
        }    
        
        if (idAgenteAduanal === "4002" || idAgenteAduanal === "4006") {  //CUSA Y VF
            
            $('#col_no_bultos').select2();
            $('#col_peso_kg').select2();
            $('#col_transferencia').select2();
            $('#col_fecha_inicio_etiquetado').select2();
            $('#col_fecha_termino_etiquetado').select2();
            $('#col_hora_termino_etiquetado').select2();
            $('#col_proveedor').select2();
            $('#col_proveedor_carga').select2();
            
        }    
            $('#col_fy').select2();
            
        } catch (error) {
            console.error(error);
        }

        setTimeout(await ocultarLoader, 2);

    }            
/*--------------------------------------------------------------------------
 FUNCIONES - CELDAS TABLA CUSTOMS
 --------------------------------------------------------------------------*/
async function AddPullCustoms() {

    await mostrarLoader();

    let idAgenteAduanal = document.getElementById("idAgenteAduanal").value;
    let contadorCustoms = document.getElementById("numCustoms").value;
    let contadorError = 0;
    let txtErrormSg = "";
    
    for (let i = 1; i < contadorCustoms; i++) {
        
        //Parametros Indicadores   
        referenciaAA = document.getElementById("referenciaAA[" + i + "]").innerHTML;
        evento = document.getElementById("evento[" + i + "]").value;
        shipmentId = document.getElementById("shipmentId[" + i + "]").innerHTML;
        containerId = document.getElementById("containerId[" + i + "]").innerHTML;
        prioridad = document.getElementById("prioridad[" + i + "]").innerHTML;
        loadTypeFinal = document.getElementById("loadTypeFinal[" + i + "]").innerHTML;
        pais_origen = document.getElementById("pais_origen[" + i + "]").innerHTML;
        size_container = document.getElementById("size_container[" + i + "]").innerHTML;
        valor_usd = document.getElementById("valor_usd[" + i + "]").innerHTML;
        eta_port_discharge = document.getElementById("eta_port_discharge[" + i + "]").innerHTML;
        agente_aduanal = document.getElementById("agente_aduanal[" + i + "]").innerHTML;
        pedimento_a1 = document.getElementById("pedimento_a1[" + i + "]").innerHTML;
        pedimento_r1_1er = document.getElementById("pedimento_r1_1er[" + i + "]").innerHTML;
        motivo_rectificacion_1er = document.getElementById("motivo_rectificacion_1er[" + i + "]").innerHTML;
        pedimento_r1_2do = document.getElementById("pedimento_r1_2do[" + i + "]").innerHTML;
        motivo_rectificacion_2do = document.getElementById("motivo_rectificacion_2do[" + i + "]").innerHTML;
        fecha_recepcion_doc = document.getElementById("fecha_recepcion_doc[" + i + "]").innerHTML;
        recinto = document.getElementById("recinto[" + i + "]").innerHTML;
        naviera = document.getElementById("naviera[" + i + "]").innerHTML;
        buque = document.getElementById("buque[" + i + "]").innerHTML;
        fecha_revalidacion = document.getElementById("fecha_revalidacion[" + i + "]").innerHTML;
        fecha_previo_origen = document.getElementById("fecha_previo_origen[" + i + "]").innerHTML;
        fecha_previo_destino = document.getElementById("fecha_previo_destino[" + i + "]").innerHTML;
        fecha_resultado_previo = document.getElementById("fecha_resultado_previo[" + i + "]").innerHTML;
        proforma_final = document.getElementById("proforma_final[" + i + "]").innerHTML;
        permiso = document.getElementById("permiso[" + i + "]").innerHTML;
        fecha_envio = document.getElementById("fecha_envio[" + i + "]").innerHTML;
        fecha_recepcion_perm = document.getElementById("fecha_recepcion_perm[" + i + "]").innerHTML;
        fecha_activacion_perm = document.getElementById("fecha_activacion_perm[" + i + "]").innerHTML;
        fecha_permisos_aut = document.getElementById("fecha_permisos_aut[" + i + "]").innerHTML;
        co_pref_arancelaria = document.getElementById("co_pref_arancelaria[" + i + "]").innerHTML;
        aplic_pref_arancelaria = document.getElementById("aplic_pref_arancelaria[" + i + "]").innerHTML;
        req_uva = document.getElementById("req_uva[" + i + "]").innerHTML;
        req_ca = document.getElementById("req_ca[" + i + "]").innerHTML;
        fecha_recepcion_ca = document.getElementById("fecha_recepcion_ca[" + i + "]").innerHTML;
        num_constancia_ca = document.getElementById("num_constancia_ca[" + i + "]").innerHTML;
        monto_ca = document.getElementById("monto_ca[" + i + "]").innerHTML;
        fecha_doc_completos = document.getElementById("fecha_doc_completos[" + i + "]").innerHTML;
        fecha_pago_pedimento = document.getElementById("fecha_pago_pedimento[" + i + "]").innerHTML;
        fecha_solicitud_transporte = document.getElementById("fecha_solicitud_transporte[" + i + "]").innerHTML;
        fecha_modulacion = document.getElementById("fecha_modulacion[" + i + "]").innerHTML;
        modalidad = document.getElementById("modalidad[" + i + "]").innerHTML;
        resultado_modulacion = document.getElementById("resultado_modulacion[" + i + "]").innerHTML;
        fecha_reconocimiento = document.getElementById("fecha_reconocimiento[" + i + "]").innerHTML;
        fecha_liberacion = document.getElementById("fecha_liberacion[" + i + "]").innerHTML;
        sello_origen = document.getElementById("sello_origen[" + i + "]").innerHTML;
        sello_final = document.getElementById("sello_final[" + i + "]").innerHTML;
        fecha_retencion_aut = document.getElementById("fecha_retencion_aut[" + i + "]").innerHTML;
        fecha_liberacion_aut = document.getElementById("fecha_liberacion_aut[" + i + "]").innerHTML;
        estatus_operacion = document.getElementById("estatus_operacion[" + i + "]").value;
        motivo_atraso = document.getElementById("motivo_atraso[" + i + "]").innerHTML;
        observaciones = document.getElementById("observaciones[" + i + "]").innerHTML;
        fy = document.getElementById("fy[" + i + "]").innerHTML;

        if (idAgenteAduanal === "4001" || idAgenteAduanal === "4006") { //LOGIX Y VF

            llegada_a_nova = document.getElementById("llegada_a_nova[" + i + "]").innerHTML;
            llegada_a_globe_trade_sd = document.getElementById("llegada_a_globe_trade_sd[" + i + "]").innerHTML;
            archivo_m = document.getElementById("archivo_m[" + i + "]").innerHTML;
            fecha_archivo_m = document.getElementById("fecha_archivo_m[" + i + "]").innerHTML;
            fecha_solicit_manip = document.getElementById("fecha_solicit_manip[" + i + "]").innerHTML;
            fecha_vencim_manip = document.getElementById("fecha_vencim_manip[" + i + "]").innerHTML;
            fecha_confirm_clave_pedim = document.getElementById("fecha_confirm_clave_pedim[" + i + "]").innerHTML;
            fecha_recep_increment = document.getElementById("fecha_recep_increment[" + i + "]").innerHTML;
            t_e = document.getElementById("t_e[" + i + "]").innerHTML;
            fecha_vencim_inbound = document.getElementById("fecha_vencim_inbound[" + i + "]").innerHTML;

        }

        if (idAgenteAduanal === "4002" || idAgenteAduanal === "4006") {  //CUSA Y VF

            no_bultos = document.getElementById("no_bultos[" + i + "]").innerHTML;
            peso_kg = document.getElementById("peso_kg[" + i + "]").innerHTML;
            transferencia = document.getElementById("transferencia[" + i + "]").innerHTML;
            fecha_inicio_etiquetado = document.getElementById("fecha_inicio_etiquetado[" + i + "]").innerHTML;
            fecha_termino_etiquetado = document.getElementById("fecha_termino_etiquetado[" + i + "]").innerHTML;
            hora_termino_etiquetado = document.getElementById("hora_termino_etiquetado[" + i + "]").value;
            proveedor = document.getElementById("proveedor[" + i + "]").innerHTML;
            proveedor_carga = document.getElementById("proveedor_carga[" + i + "]").innerHTML;
        }


        urlCustoms += "&evento[" + i + "]=" + evento +
                "&shipmentId[" + i + "]=" + shipmentId +
                "&containerId[" + i + "]=" + containerId +
                "&referenciaAA[" + i + "]=" + referenciaAA +
                "&prioridad[" + i + "]=" + prioridad +
                "&loadTypeFinal[" + i + "]=" + loadTypeFinal +
                "&pais_origen[" + i + "]=" + pais_origen +
                "&size_container[" + i + "]=" + size_container +
                "&valor_usd[" + i + "]=" + valor_usd +
                "&eta_port_discharge[" + i + "]=" + eta_port_discharge +
                "&agente_aduanal[" + i + "]=" + agente_aduanal +
                "&pedimento_a1[" + i + "]=" + pedimento_a1 +
                "&pedimento_r1_1er[" + i + "]=" + pedimento_r1_1er +
                "&motivo_rectificacion_1er[" + i + "]=" + motivo_rectificacion_1er +
                "&pedimento_r1_2do[" + i + "]=" + pedimento_r1_2do +
                "&motivo_rectificacion_2do[" + i + "]=" + motivo_rectificacion_2do +
                "&fecha_recepcion_doc[" + i + "]=" + fecha_recepcion_doc +
                "&recinto[" + i + "]=" + recinto +
                "&naviera[" + i + "]=" + naviera +
                "&buque[" + i + "]=" + buque +
                "&fecha_revalidacion[" + i + "]=" + fecha_revalidacion +
                "&fecha_previo_origen[" + i + "]=" + fecha_previo_origen +
                "&fecha_previo_destino[" + i + "]=" + fecha_previo_destino +
                "&fecha_resultado_previo[" + i + "]=" + fecha_resultado_previo +
                "&proforma_final[" + i + "]=" + proforma_final +
                "&permiso[" + i + "]=" + permiso +
                "&fecha_envio[" + i + "]=" + fecha_envio +
                "&fecha_recepcion_perm[" + i + "]=" + fecha_recepcion_perm +
                "&fecha_activacion_perm[" + i + "]=" + fecha_activacion_perm +
                "&fecha_permisos_aut[" + i + "]=" + fecha_permisos_aut +
                "&co_pref_arancelaria[" + i + "]=" + co_pref_arancelaria +
                "&aplic_pref_arancelaria[" + i + "]=" + aplic_pref_arancelaria +
                "&req_uva[" + i + "]=" + req_uva +
                "&req_ca[" + i + "]=" + req_ca +
                "&fecha_recepcion_ca[" + i + "]=" + fecha_recepcion_ca +
                "&num_constancia_ca[" + i + "]=" + num_constancia_ca +
                "&monto_ca[" + i + "]=" + monto_ca +
                "&fecha_doc_completos[" + i + "]=" + fecha_doc_completos +
                "&fecha_pago_pedimento[" + i + "]=" + fecha_pago_pedimento +
                "&fecha_solicitud_transporte[" + i + "]=" + fecha_solicitud_transporte +
                "&fecha_modulacion[" + i + "]=" + fecha_modulacion +
                "&modalidad[" + i + "]=" + modalidad +
                "&resultado_modulacion[" + i + "]=" + resultado_modulacion +
                "&fecha_reconocimiento[" + i + "]=" + fecha_reconocimiento +
                "&fecha_liberacion[" + i + "]=" + fecha_liberacion +
                "&sello_origen[" + i + "]=" + sello_origen +
                "&sello_final[" + i + "]=" + sello_final +
                "&fecha_retencion_aut[" + i + "]=" + fecha_retencion_aut +
                "&fecha_liberacion_aut[" + i + "]=" + fecha_liberacion_aut +
                "&estatus_operacion[" + i + "]=" + estatus_operacion +
                "&motivo_atraso[" + i + "]=" + motivo_atraso +
                "&observaciones[" + i + "]=" + observaciones +
                "&fy[" + i + "]=" + fy;

        if (idAgenteAduanal === "4001" || idAgenteAduanal === "4006") { //LOGIX Y VF

            urlCustoms += "&llegada_a_nova[" + i + "]=" + llegada_a_nova +
                    "&llegada_a_globe_trade_sd[" + i + "]=" + llegada_a_globe_trade_sd +
                    "&archivo_m[" + i + "]=" + archivo_m +
                    "&fecha_archivo_m[" + i + "]=" + fecha_archivo_m +
                    "&fecha_solicit_manip[" + i + "]=" + fecha_solicit_manip +
                    "&fecha_vencim_manip[" + i + "]=" + fecha_vencim_manip +
                    "&fecha_confirm_clave_pedim[" + i + "]=" + fecha_confirm_clave_pedim +
                    "&fecha_recep_increment[" + i + "]=" + fecha_recep_increment +
                    "&t_e[" + i + "]=" + t_e +
                    "&fecha_vencim_inbound[" + i + "]=" + fecha_vencim_inbound;
        }

        if (idAgenteAduanal === "4002" || idAgenteAduanal === "4006") {  //CUSA Y VF

            urlCustoms += "&no_bultos[" + i + "]=" + no_bultos +
                    "&peso_kg[" + i + "]=" + peso_kg +
                    "&transferencia[" + i + "]=" + transferencia +
                    "&fecha_inicio_etiquetado[" + i + "]=" + fecha_inicio_etiquetado +
                    "&fecha_termino_etiquetado[" + i + "]=" + fecha_termino_etiquetado +
                    "&hora_termino_etiquetado[" + i + "]=" + hora_termino_etiquetado +
                    "&proveedor[" + i + "]=" + proveedor +
                    "&proveedor_carga[" + i + "]=" + proveedor_carga;
        }


        /* # REGLA 1: */
        if (permiso === "Si") {

            if (fecha_envio.replace(/\s/g, "") === "") {
                contadorError++;
                txtErrormSg += "(" + contadorError + ")Ingrese Fecha Envío. ";
                changeColorByPositionError(i);
            }

            if (fecha_recepcion_perm.replace(/\s/g, "") === "") {
                contadorError++;
                txtErrormSg += "(" + contadorError + ")Ingrese Fecha Recepción Permiso. ";
                changeColorByPositionError(i);
            }

            if (fecha_activacion_perm.replace(/\s/g, "") === "") {
                contadorError++;
                txtErrormSg += "(" + contadorError + ")Ingrese Fecha Activación Permiso. ";
                changeColorByPositionError(i);
            }

            if (fecha_permisos_aut.replace(/\s/g, "") === "") {
                contadorError++;
                txtErrormSg += "(" + contadorError + ")Ingrese Fecha Permisos Autorizados. ";
                changeColorByPositionError(i);
            }

        }

        /* # REGLA 2: */
        if (req_ca === "Si") {

            if (fecha_recepcion_ca.replace(/\s/g, "") === "") {
                contadorError++;
                txtErrormSg += "(" + contadorError + ")Ingrese Fecha Recepción CA. ";
                changeColorByPositionError(i);
            }

            if (num_constancia_ca.replace(/\s/g, "") === "") {
                contadorError++;
                txtErrormSg += "(" + contadorError + ")Ingrese Número de Constancia CA. ";
                changeColorByPositionError(i);
            }

            if (monto_ca.replace(/\s/g, "") === "") {
                contadorError++;
                txtErrormSg += "(" + contadorError + ")Ingrese Monto CA. ";
                changeColorByPositionError(i);
            }

        }

        /* # REGLA 3: */
        if (idAgenteAduanal != "4005") { //RECHY

            if (resultado_modulacion === "Rojo") {

                if (idAgenteAduanal === "4002") { //CUSA

                    if (sello_final.replace(/\s/g, "") === "") {
                        contadorError++;
                        txtErrormSg += "(" + contadorError + ")Ingrese Sello Final. ";
                        changeColorByPositionError(i);
                    }
                } else {

                    if (sello_origen.replace(/\s/g, "") === "") {
                        contadorError++;
                        txtErrormSg += "(" + contadorError + ")Ingrese Sello Origen. ";
                        changeColorByPositionError(i);
                    }
                }
            }
        }


        /* #REGLA 4: */
        //Establecer listado de estatus validos. (ok)

        /* RULE #5 */
        //Si se exede dias de load type ------> motivo atraso (campo obligatorio) (pendiente)

        /* RULE #6 */
        // Calendario (FISCAL YEAR) (pendiente)                            

        /* RULE #7 */
        // Limite de Bytes campo_ Observaciones (VARCHAR2(4000 BYTE)) (ok)

        /* RULE #8 */
        if (pedimento_r1_1er.replace(/\s/g, "") != "") {

            if (motivo_rectificacion_1er.replace(/\s/g, "") === "") {
                contadorError++;
                txtErrormSg += "(" + contadorError + ")Ingrese Motivo Rectificación 1. ";
                changeColorByPositionError(i);
            }

        }

        /* RULE #9 */
        if (pedimento_r1_2do.replace(/\s/g, "") != "") {

            if (motivo_rectificacion_2do.replace(/\s/g, "") === "") {
                contadorError++;
                txtErrormSg += "(" + contadorError + ")Ingrese Motivo Rectificación 2. ";
                changeColorByPositionError(i);
            }

        }

        /* RULE #10 ESTATUS: IMPORTADO */
        if (estatus_operacion === "19") {
            if (idAgenteAduanal === "4001" || idAgenteAduanal === "4002" || idAgenteAduanal === "4006") { //RADAR|SESMA|VF
                if (pais_origen.replace(/\s/g, "") != "" &&
                    size_container.replace(/\s/g, "") != "" &&
                    valor_usd.replace(/\s/g, "") != "" &&
                    eta_port_discharge.replace(/\s/g, "") != "" &&
                    agente_aduanal.replace(/\s/g, "") != "" &&
                    pedimento_a1.replace(/\s/g, "") != "" &&
                    pedimento_r1_1er.replace(/\s/g, "") != "" &&
                    motivo_rectificacion_1er.replace(/\s/g, "") != "" &&
                    pedimento_r1_2do.replace(/\s/g, "") != "" &&
                    motivo_rectificacion_2do.replace(/\s/g, "") != "" &&
                    fecha_recepcion_doc.replace(/\s/g, "") != "" &&
                    recinto.replace(/\s/g, "") != "" &&
                    naviera.replace(/\s/g, "") != "" &&
                    buque.replace(/\s/g, "") != "" &&
                    fecha_revalidacion.replace(/\s/g, "") != "" &&
                    fecha_previo_origen.replace(/\s/g, "") != "" &&
                    fecha_previo_destino.replace(/\s/g, "") != "" &&
                    fecha_resultado_previo.replace(/\s/g, "") != "" &&
                    proforma_final.replace(/\s/g, "") != "" &&
                    permiso.replace(/\s/g, "") != "" &&
                    fecha_envio.replace(/\s/g, "") != "" &&
                    fecha_recepcion_perm.replace(/\s/g, "") != "" &&
                    fecha_activacion_perm.replace(/\s/g, "") != "" &&
                    fecha_permisos_aut.replace(/\s/g, "") != "" &&
                    co_pref_arancelaria.replace(/\s/g, "") != "" &&
                    aplic_pref_arancelaria.replace(/\s/g, "") != "" &&
                    req_uva.replace(/\s/g, "") != "" &&
                    req_ca.replace(/\s/g, "") != "" &&
                    fecha_recepcion_ca.replace(/\s/g, "") != "" &&
                    num_constancia_ca.replace(/\s/g, "") != "" &&
                    monto_ca.replace(/\s/g, "") != "" &&
                    fecha_doc_completos.replace(/\s/g, "") != "" &&
                    fecha_pago_pedimento.replace(/\s/g, "") != "" &&
                    fecha_solicitud_transporte.replace(/\s/g, "") != "" &&
                    fecha_modulacion.replace(/\s/g, "") != "" &&
                    modalidad.replace(/\s/g, "") != "" &&
                    resultado_modulacion.replace(/\s/g, "") != "" &&
                    fecha_reconocimiento.replace(/\s/g, "") != "" &&
                    fecha_liberacion.replace(/\s/g, "") != "" &&
                    sello_origen.replace(/\s/g, "") != "" &&
                    sello_final.replace(/\s/g, "") != "" &&
                    motivo_atraso.replace(/\s/g, "") != "" &&
                    fy.replace(/\s/g, "") != "") {
                    changeColorByPositionSuccess(i);
                }else{
                    contadorError++;
                    txtErrormSg += "(" + contadorError + ")Todos los campos en rojo son obligatorios. ";
                    changeColorByPositionError(i);
                    msgErrorAgenteAduanal(i, idAgenteAduanal);
                }
            } else if (idAgenteAduanal === "4001") { //LOGIX
                if (pais_origen.replace(/\s/g, "") != "" &&
                    size_container.replace(/\s/g, "") != "" &&
                    valor_usd.replace(/\s/g, "") != "" &&
                    eta_port_discharge.replace(/\s/g, "") != "" &&
                    agente_aduanal.replace(/\s/g, "") != "" &&
                    pedimento_a1.replace(/\s/g, "") != "" &&
                    pedimento_r1_1er.replace(/\s/g, "") != "" &&
                    motivo_rectificacion_1er.replace(/\s/g, "") != "" &&
                    pedimento_r1_2do.replace(/\s/g, "") != "" &&
                    motivo_rectificacion_2do.replace(/\s/g, "") != "" &&
                    fecha_recepcion_doc.replace(/\s/g, "") != "" &&
                    recinto.replace(/\s/g, "") != "" &&
                    naviera.replace(/\s/g, "") != "" &&
                    buque.replace(/\s/g, "") != "" &&
                    fecha_revalidacion.replace(/\s/g, "") != "" &&
                    fecha_previo_origen.replace(/\s/g, "") != "" &&
                    fecha_previo_destino.replace(/\s/g, "") != "" &&
                    fecha_resultado_previo.replace(/\s/g, "") != "" &&
                    proforma_final.replace(/\s/g, "") != "" &&
                    permiso.replace(/\s/g, "") != "" &&
                    fecha_envio.replace(/\s/g, "") != "" &&
                    fecha_recepcion_perm.replace(/\s/g, "") != "" &&
                    fecha_activacion_perm.replace(/\s/g, "") != "" &&
                    fecha_permisos_aut.replace(/\s/g, "") != "" &&
                    req_ca.replace(/\s/g, "") != "" &&
                    fecha_recepcion_ca.replace(/\s/g, "") != "" &&
                    num_constancia_ca.replace(/\s/g, "") != "" &&
                    monto_ca.replace(/\s/g, "") != "" &&
                    fecha_doc_completos.replace(/\s/g, "") != "" &&
                    fecha_pago_pedimento.replace(/\s/g, "") != "" &&
                    fecha_solicitud_transporte.replace(/\s/g, "") != "" &&
                    fecha_modulacion.replace(/\s/g, "") != "" &&
                    modalidad.replace(/\s/g, "") != "" &&
                    resultado_modulacion.replace(/\s/g, "") != "" &&
                    fecha_reconocimiento.replace(/\s/g, "") != "" &&
                    fecha_liberacion.replace(/\s/g, "") != "" &&
                    sello_origen.replace(/\s/g, "") != "" &&
                    sello_final.replace(/\s/g, "") != "" &&
                    motivo_atraso.replace(/\s/g, "") != "" &&
                    fy.replace(/\s/g, "") != "" &&
                    llegada_a_nova.replace(/\s/g, "") != "" &&
                    llegada_a_globe_trade_sd.replace(/\s/g, "") != "" &&
                    archivo_m.replace(/\s/g, "") != "" &&
                    fecha_archivo_m.replace(/\s/g, "") != "" &&
                    fecha_solicit_manip.replace(/\s/g, "") != "" &&
                    fecha_vencim_manip.replace(/\s/g, "") != "" &&
                    fecha_confirm_clave_pedim.replace(/\s/g, "") != "" &&
                    fecha_recep_increment.replace(/\s/g, "") != "" &&
                    t_e.replace(/\s/g, "") != "" &&
                    fecha_vencim_inbound.replace(/\s/g, "") != "") {
                    changeColorByPositionSuccess(i);
                }else{
                    contadorError++;
                    txtErrormSg += "(" + contadorError + ")Todos los campos en rojo son obligatorios. ";
                    changeColorByPositionError(i);
                    msgErrorAgenteAduanal(i, idAgenteAduanal);
                }
            } else if (idAgenteAduanal === "4002") { //CUSA
                if (pais_origen.replace(/\s/g, "") != "" &&
                    valor_usd.replace(/\s/g, "") != "" &&
                    eta_port_discharge.replace(/\s/g, "") != "" &&
                    agente_aduanal.replace(/\s/g, "") != "" &&
                    pedimento_a1.replace(/\s/g, "") != "" &&
                    pedimento_r1_1er.replace(/\s/g, "") != "" &&
                    motivo_rectificacion_1er.replace(/\s/g, "") != "" &&
                    pedimento_r1_2do.replace(/\s/g, "") != "" &&
                    motivo_rectificacion_2do.replace(/\s/g, "") != "" &&
                    fecha_recepcion_doc.replace(/\s/g, "") != "" &&
                    naviera.replace(/\s/g, "") != "" &&
                    fecha_revalidacion.replace(/\s/g, "") != "" &&
                    fecha_previo_destino.replace(/\s/g, "") != "" &&
                    fecha_resultado_previo.replace(/\s/g, "") != "" &&
                    proforma_final.replace(/\s/g, "") != "" &&
                    permiso.replace(/\s/g, "") != "" &&
                    fecha_envio.replace(/\s/g, "") != "" &&
                    fecha_recepcion_perm.replace(/\s/g, "") != "" &&
                    fecha_activacion_perm.replace(/\s/g, "") != "" &&
                    fecha_permisos_aut.replace(/\s/g, "") != "" &&
                    co_pref_arancelaria.replace(/\s/g, "") != "" &&
                    aplic_pref_arancelaria.replace(/\s/g, "") != "" &&
                    req_uva.replace(/\s/g, "") != "" &&
                    req_ca.replace(/\s/g, "") != "" &&
                    fecha_recepcion_ca.replace(/\s/g, "") != "" &&
                    num_constancia_ca.replace(/\s/g, "") != "" &&
                    monto_ca.replace(/\s/g, "") != "" &&
                    fecha_doc_completos.replace(/\s/g, "") != "" &&
                    fecha_pago_pedimento.replace(/\s/g, "") != "" &&
                    fecha_solicitud_transporte.replace(/\s/g, "") != "" &&
                    fecha_modulacion.replace(/\s/g, "") != "" &&
                    modalidad.replace(/\s/g, "") != "" &&
                    resultado_modulacion.replace(/\s/g, "") != "" &&
                    fecha_reconocimiento.replace(/\s/g, "") != "" &&
                    fecha_liberacion.replace(/\s/g, "") != "" &&
                    sello_final.replace(/\s/g, "") != "" &&
                    motivo_atraso.replace(/\s/g, "") != "" &&
                    fy.replace(/\s/g, "") != "" &&
                    no_bultos.replace(/\s/g, "") != "" &&
                    peso_kg.replace(/\s/g, "") != "" &&
                    transferencia.replace(/\s/g, "") != "" &&
                    fecha_inicio_etiquetado.replace(/\s/g, "") != "" &&
                    fecha_termino_etiquetado.replace(/\s/g, "") != "" &&
                    hora_termino_etiquetado.replace(/\s/g, "") != "" &&
                    proveedor.replace(/\s/g, "") != "" &&
                    proveedor_carga.replace(/\s/g, "") != "") {
                    changeColorByPositionSuccess(i);
                }else{
                    contadorError++;
                    txtErrormSg += "(" + contadorError + ")Todos los campos en rojo son obligatorios. ";
                    changeColorByPositionError(i);
                    msgErrorAgenteAduanal(i, idAgenteAduanal);
                }
            } else if (idAgenteAduanal === "4005") { //RECHY

                if (pais_origen.replace(/\s/g, "") != "" &&
                    valor_usd.replace(/\s/g, "") != "" &&
                    eta_port_discharge.replace(/\s/g, "") != "" &&
                    agente_aduanal.replace(/\s/g, "") != "" &&
                    pedimento_a1.replace(/\s/g, "") != "" &&
                    pedimento_r1_1er.replace(/\s/g, "") != "" &&
                    motivo_rectificacion_1er.replace(/\s/g, "") != "" &&
                    pedimento_r1_2do.replace(/\s/g, "") != "" &&
                    motivo_rectificacion_2do.replace(/\s/g, "") != "" &&
                    fecha_recepcion_doc.replace(/\s/g, "") != "" &&
                    fecha_previo_destino.replace(/\s/g, "") != "" &&
                    fecha_resultado_previo.replace(/\s/g, "") != "" &&
                    proforma_final.replace(/\s/g, "") != "" &&
                    permiso.replace(/\s/g, "") != "" &&
                    fecha_envio.replace(/\s/g, "") != "" &&
                    fecha_recepcion_perm.replace(/\s/g, "") != "" &&
                    fecha_activacion_perm.replace(/\s/g, "") != "" &&
                    fecha_permisos_aut.replace(/\s/g, "") != "" &&
                    co_pref_arancelaria.replace(/\s/g, "") != "" &&
                    aplic_pref_arancelaria.replace(/\s/g, "") != "" &&
                    req_uva.replace(/\s/g, "") != "" &&
                    req_ca.replace(/\s/g, "") != "" &&
                    fecha_recepcion_ca.replace(/\s/g, "") != "" &&
                    num_constancia_ca.replace(/\s/g, "") != "" &&
                    monto_ca.replace(/\s/g, "") != "" &&
                    fecha_doc_completos.replace(/\s/g, "") != "" &&
                    fecha_pago_pedimento.replace(/\s/g, "") != "" &&
                    fecha_solicitud_transporte.replace(/\s/g, "") != "" &&
                    fecha_modulacion.replace(/\s/g, "") != "" &&
                    modalidad.replace(/\s/g, "") != "" &&
                    resultado_modulacion.replace(/\s/g, "") != "" &&
                    fecha_reconocimiento.replace(/\s/g, "") != "" &&
                    fecha_liberacion.replace(/\s/g, "") != "" &&
                    motivo_atraso.replace(/\s/g, "") != "") {
                    changeColorByPositionSuccess(i);
                }else{
                    contadorError++;
                    txtErrormSg += "(" + contadorError + ")Todos los campos en rojo son obligatorios. ";
                    changeColorByPositionError(i);
                    msgErrorAgenteAduanal(i, idAgenteAduanal);
                }
            }
        }

            let urlData = encodeURI("../InsertarCustomsForms?idAgenteAduanal=" + idAgenteAduanal + "&numCustomsInicial=" + i + "&numCustomsFinal=" + i + urlCustoms);
            try {
                const response = await fetch(urlData);
                if (!response.ok) {
                    throw new Error('Error en la solicitud');
                }
                const data = await response.text();

                if (contadorError > 0) {
                    mSgErrorLineCustoms(txtErrormSg, i);
                } else {
                    changeColorByPositionSuccess(i);
                    document.getElementById('mSgError' + i).innerHTML = "";
                    msgErrorAgenteAduanal(i, idAgenteAduanal);
                }

                urlCustoms = "";
                cont++;
                let webp = "";

                if (data === "0") {  //Activación Semaforo
                    webp = "../img/circle-gray.webp";
                } else if (data === "1") {
                    webp = "../img/circle-green.webp";
                } else if (data === "2") {
                    webp = "../img/circle-yellow.webp";
                } else if (data === "3") {
                    webp = "../img/circle-red.webp";
                }

                //color semaforo
                var imgElement = document.getElementById("imgSemaforo" + i);
                imgElement.src = webp;

            } catch (error) {
                console.error(error);
            }
    
    contadorError = 0;
    txtErrormSg = "";    
    }
    
    await ocultarLoader();
    swal("", "Información Actualizada", "success");
    alertclose();
}

async function AddLineCustoms(i) {

    await mostrarLoader();

    let idAgenteAduanal = document.getElementById("idAgenteAduanal").value;
    let contadorError = 0;
    let txtErrormSg = "";

    //Parametros Indicadores   
    referenciaAA = document.getElementById("referenciaAA[" + i + "]").innerHTML;
    evento = document.getElementById("evento[" + i + "]").value;
    shipmentId = document.getElementById("shipmentId[" + i + "]").innerHTML;
    containerId = document.getElementById("containerId[" + i + "]").innerHTML;
    prioridad = document.getElementById("prioridad[" + i + "]").innerHTML;
    loadTypeFinal = document.getElementById("loadTypeFinal[" + i + "]").innerHTML;
    pais_origen = document.getElementById("pais_origen[" + i + "]").innerHTML;
    size_container = document.getElementById("size_container[" + i + "]").innerHTML;
    valor_usd = document.getElementById("valor_usd[" + i + "]").innerHTML;
    eta_port_discharge = document.getElementById("eta_port_discharge[" + i + "]").innerHTML;
    agente_aduanal = document.getElementById("agente_aduanal[" + i + "]").innerHTML;
    pedimento_a1 = document.getElementById("pedimento_a1[" + i + "]").innerHTML;
    pedimento_r1_1er = document.getElementById("pedimento_r1_1er[" + i + "]").innerHTML;
    motivo_rectificacion_1er = document.getElementById("motivo_rectificacion_1er[" + i + "]").innerHTML;
    pedimento_r1_2do = document.getElementById("pedimento_r1_2do[" + i + "]").innerHTML;
    motivo_rectificacion_2do = document.getElementById("motivo_rectificacion_2do[" + i + "]").innerHTML;
    fecha_recepcion_doc = document.getElementById("fecha_recepcion_doc[" + i + "]").innerHTML;
    recinto = document.getElementById("recinto[" + i + "]").innerHTML;
    naviera = document.getElementById("naviera[" + i + "]").innerHTML;
    buque = document.getElementById("buque[" + i + "]").innerHTML;
    fecha_revalidacion = document.getElementById("fecha_revalidacion[" + i + "]").innerHTML;
    fecha_previo_origen = document.getElementById("fecha_previo_origen[" + i + "]").innerHTML;
    fecha_previo_destino = document.getElementById("fecha_previo_destino[" + i + "]").innerHTML;
    fecha_resultado_previo = document.getElementById("fecha_resultado_previo[" + i + "]").innerHTML;
    proforma_final = document.getElementById("proforma_final[" + i + "]").innerHTML;
    permiso = document.getElementById("permiso[" + i + "]").innerHTML;
    fecha_envio = document.getElementById("fecha_envio[" + i + "]").innerHTML;
    fecha_recepcion_perm = document.getElementById("fecha_recepcion_perm[" + i + "]").innerHTML;
    fecha_activacion_perm = document.getElementById("fecha_activacion_perm[" + i + "]").innerHTML;
    fecha_permisos_aut = document.getElementById("fecha_permisos_aut[" + i + "]").innerHTML;
    co_pref_arancelaria = document.getElementById("co_pref_arancelaria[" + i + "]").innerHTML;
    aplic_pref_arancelaria = document.getElementById("aplic_pref_arancelaria[" + i + "]").innerHTML;
    req_uva = document.getElementById("req_uva[" + i + "]").innerHTML;
    req_ca = document.getElementById("req_ca[" + i + "]").innerHTML;
    fecha_recepcion_ca = document.getElementById("fecha_recepcion_ca[" + i + "]").innerHTML;
    num_constancia_ca = document.getElementById("num_constancia_ca[" + i + "]").innerHTML;
    monto_ca = document.getElementById("monto_ca[" + i + "]").innerHTML;
    fecha_doc_completos = document.getElementById("fecha_doc_completos[" + i + "]").innerHTML;
    fecha_pago_pedimento = document.getElementById("fecha_pago_pedimento[" + i + "]").innerHTML;
    fecha_solicitud_transporte = document.getElementById("fecha_solicitud_transporte[" + i + "]").innerHTML;
    fecha_modulacion = document.getElementById("fecha_modulacion[" + i + "]").innerHTML;
    modalidad = document.getElementById("modalidad[" + i + "]").innerHTML;
    resultado_modulacion = document.getElementById("resultado_modulacion[" + i + "]").innerHTML;
    fecha_reconocimiento = document.getElementById("fecha_reconocimiento[" + i + "]").innerHTML;
    fecha_liberacion = document.getElementById("fecha_liberacion[" + i + "]").innerHTML;
    sello_origen = document.getElementById("sello_origen[" + i + "]").innerHTML;
    sello_final = document.getElementById("sello_final[" + i + "]").innerHTML;
    fecha_retencion_aut = document.getElementById("fecha_retencion_aut[" + i + "]").innerHTML;
    fecha_liberacion_aut = document.getElementById("fecha_liberacion_aut[" + i + "]").innerHTML;
    estatus_operacion = document.getElementById("estatus_operacion[" + i + "]").value;
    motivo_atraso = document.getElementById("motivo_atraso[" + i + "]").innerHTML;
    observaciones = document.getElementById("observaciones[" + i + "]").innerHTML;
    fy = document.getElementById("fy[" + i + "]").innerHTML;

    if (idAgenteAduanal === "4001" || idAgenteAduanal === "4006") { //LOGIX Y VF

        llegada_a_nova = document.getElementById("llegada_a_nova[" + i + "]").innerHTML;
        llegada_a_globe_trade_sd = document.getElementById("llegada_a_globe_trade_sd[" + i + "]").innerHTML;
        archivo_m = document.getElementById("archivo_m[" + i + "]").innerHTML;
        fecha_archivo_m = document.getElementById("fecha_archivo_m[" + i + "]").innerHTML;
        fecha_solicit_manip = document.getElementById("fecha_solicit_manip[" + i + "]").innerHTML;
        fecha_vencim_manip = document.getElementById("fecha_vencim_manip[" + i + "]").innerHTML;
        fecha_confirm_clave_pedim = document.getElementById("fecha_confirm_clave_pedim[" + i + "]").innerHTML;
        fecha_recep_increment = document.getElementById("fecha_recep_increment[" + i + "]").innerHTML;
        t_e = document.getElementById("t_e[" + i + "]").innerHTML;
        fecha_vencim_inbound = document.getElementById("fecha_vencim_inbound[" + i + "]").innerHTML;

    }

    if (idAgenteAduanal === "4002" || idAgenteAduanal === "4006") {  //CUSA Y VF

        no_bultos = document.getElementById("no_bultos[" + i + "]").innerHTML;
        peso_kg = document.getElementById("peso_kg[" + i + "]").innerHTML;
        transferencia = document.getElementById("transferencia[" + i + "]").innerHTML;
        fecha_inicio_etiquetado = document.getElementById("fecha_inicio_etiquetado[" + i + "]").innerHTML;
        fecha_termino_etiquetado = document.getElementById("fecha_termino_etiquetado[" + i + "]").innerHTML;
        hora_termino_etiquetado = document.getElementById("hora_termino_etiquetado[" + i + "]").value;
        proveedor = document.getElementById("proveedor[" + i + "]").innerHTML;
        proveedor_carga = document.getElementById("proveedor_carga[" + i + "]").innerHTML;
    }


    urlCustoms += "&evento[" + i + "]=" + evento +
            "&shipmentId[" + i + "]=" + shipmentId +
            "&containerId[" + i + "]=" + containerId +
            "&referenciaAA[" + i + "]=" + referenciaAA +
            "&prioridad[" + i + "]=" + prioridad +
            "&loadTypeFinal[" + i + "]=" + loadTypeFinal +
            "&pais_origen[" + i + "]=" + pais_origen +
            "&size_container[" + i + "]=" + size_container +
            "&valor_usd[" + i + "]=" + valor_usd +
            "&eta_port_discharge[" + i + "]=" + eta_port_discharge +
            "&agente_aduanal[" + i + "]=" + agente_aduanal +
            "&pedimento_a1[" + i + "]=" + pedimento_a1 +
            "&pedimento_r1_1er[" + i + "]=" + pedimento_r1_1er +
            "&motivo_rectificacion_1er[" + i + "]=" + motivo_rectificacion_1er +
            "&pedimento_r1_2do[" + i + "]=" + pedimento_r1_2do +
            "&motivo_rectificacion_2do[" + i + "]=" + motivo_rectificacion_2do +
            "&fecha_recepcion_doc[" + i + "]=" + fecha_recepcion_doc +
            "&recinto[" + i + "]=" + recinto +
            "&naviera[" + i + "]=" + naviera +
            "&buque[" + i + "]=" + buque +
            "&fecha_revalidacion[" + i + "]=" + fecha_revalidacion +
            "&fecha_previo_origen[" + i + "]=" + fecha_previo_origen +
            "&fecha_previo_destino[" + i + "]=" + fecha_previo_destino +
            "&fecha_resultado_previo[" + i + "]=" + fecha_resultado_previo +
            "&proforma_final[" + i + "]=" + proforma_final +
            "&permiso[" + i + "]=" + permiso +
            "&fecha_envio[" + i + "]=" + fecha_envio +
            "&fecha_recepcion_perm[" + i + "]=" + fecha_recepcion_perm +
            "&fecha_activacion_perm[" + i + "]=" + fecha_activacion_perm +
            "&fecha_permisos_aut[" + i + "]=" + fecha_permisos_aut +
            "&co_pref_arancelaria[" + i + "]=" + co_pref_arancelaria +
            "&aplic_pref_arancelaria[" + i + "]=" + aplic_pref_arancelaria +
            "&req_uva[" + i + "]=" + req_uva +
            "&req_ca[" + i + "]=" + req_ca +
            "&fecha_recepcion_ca[" + i + "]=" + fecha_recepcion_ca +
            "&num_constancia_ca[" + i + "]=" + num_constancia_ca +
            "&monto_ca[" + i + "]=" + monto_ca +
            "&fecha_doc_completos[" + i + "]=" + fecha_doc_completos +
            "&fecha_pago_pedimento[" + i + "]=" + fecha_pago_pedimento +
            "&fecha_solicitud_transporte[" + i + "]=" + fecha_solicitud_transporte +
            "&fecha_modulacion[" + i + "]=" + fecha_modulacion +
            "&modalidad[" + i + "]=" + modalidad +
            "&resultado_modulacion[" + i + "]=" + resultado_modulacion +
            "&fecha_reconocimiento[" + i + "]=" + fecha_reconocimiento +
            "&fecha_liberacion[" + i + "]=" + fecha_liberacion +
            "&sello_origen[" + i + "]=" + sello_origen +
            "&sello_final[" + i + "]=" + sello_final +
            "&fecha_retencion_aut[" + i + "]=" + fecha_retencion_aut +
            "&fecha_liberacion_aut[" + i + "]=" + fecha_liberacion_aut +
            "&estatus_operacion[" + i + "]=" + estatus_operacion +
            "&motivo_atraso[" + i + "]=" + motivo_atraso +
            "&observaciones[" + i + "]=" + observaciones +
            "&fy[" + i + "]=" + fy;

    if (idAgenteAduanal === "4001" || idAgenteAduanal === "4006") { //LOGIX Y VF

        urlCustoms += "&llegada_a_nova[" + i + "]=" + llegada_a_nova +
                "&llegada_a_globe_trade_sd[" + i + "]=" + llegada_a_globe_trade_sd +
                "&archivo_m[" + i + "]=" + archivo_m +
                "&fecha_archivo_m[" + i + "]=" + fecha_archivo_m +
                "&fecha_solicit_manip[" + i + "]=" + fecha_solicit_manip +
                "&fecha_vencim_manip[" + i + "]=" + fecha_vencim_manip +
                "&fecha_confirm_clave_pedim[" + i + "]=" + fecha_confirm_clave_pedim +
                "&fecha_recep_increment[" + i + "]=" + fecha_recep_increment +
                "&t_e[" + i + "]=" + t_e +
                "&fecha_vencim_inbound[" + i + "]=" + fecha_vencim_inbound;
    }

    if (idAgenteAduanal === "4002" || idAgenteAduanal === "4006") {  //CUSA Y VF

        urlCustoms += "&no_bultos[" + i + "]=" + no_bultos +
                "&peso_kg[" + i + "]=" + peso_kg +
                "&transferencia[" + i + "]=" + transferencia +
                "&fecha_inicio_etiquetado[" + i + "]=" + fecha_inicio_etiquetado +
                "&fecha_termino_etiquetado[" + i + "]=" + fecha_termino_etiquetado +
                "&hora_termino_etiquetado[" + i + "]=" + hora_termino_etiquetado +
                "&proveedor[" + i + "]=" + proveedor +
                "&proveedor_carga[" + i + "]=" + proveedor_carga;
    }


    /* # REGLA 1: */
    if (permiso === "Si") {

        if (fecha_envio.replace(/\s/g, "") === "") {
            contadorError++;
            txtErrormSg += "(" + contadorError + ")Ingrese Fecha Envío. ";
            changeColorByPositionError(i);
        }

        if (fecha_recepcion_perm.replace(/\s/g, "") === "") {
            contadorError++;
            txtErrormSg += "(" + contadorError + ")Ingrese Fecha Recepción Permiso. ";
            changeColorByPositionError(i);
        }

        if (fecha_activacion_perm.replace(/\s/g, "") === "") {
            contadorError++;
            txtErrormSg += "(" + contadorError + ")Ingrese Fecha Activación Permiso. ";
            changeColorByPositionError(i);
        }

        if (fecha_permisos_aut.replace(/\s/g, "") === "") {
            contadorError++;
            txtErrormSg += "(" + contadorError + ")Ingrese Fecha Permisos Autorizados. ";
            changeColorByPositionError(i);
        }

    }

    /* # REGLA 2: */
    if (req_ca === "Si") {

        if (fecha_recepcion_ca.replace(/\s/g, "") === "") {
            contadorError++;
            txtErrormSg += "(" + contadorError + ")Ingrese Fecha Recepción CA. ";
            changeColorByPositionError(i);
        }

        if (num_constancia_ca.replace(/\s/g, "") === "") {
            contadorError++;
            txtErrormSg += "(" + contadorError + ")Ingrese Número de Constancia CA. ";
            changeColorByPositionError(i);
        }

        if (monto_ca.replace(/\s/g, "") === "") {
            contadorError++;
            txtErrormSg += "(" + contadorError + ")Ingrese Monto CA. ";
            changeColorByPositionError(i);
        }

    }

    /* # REGLA 3: */
    if (idAgenteAduanal != "4005") { //RECHY

        if (resultado_modulacion === "Rojo") {

            if (idAgenteAduanal === "4002") { //CUSA

                if (sello_final.replace(/\s/g, "") === "") {
                    contadorError++;
                    txtErrormSg += "(" + contadorError + ")Ingrese Sello Final. ";
                    changeColorByPositionError(i);
                }
            } else {

                if (sello_origen.replace(/\s/g, "") === "") {
                    contadorError++;
                    txtErrormSg += "(" + contadorError + ")Ingrese Sello Origen. ";
                    changeColorByPositionError(i);
                }
            }
        }
    }


    /* #REGLA 4: */
    //Establecer listado de estatus validos. (ok)

    /* RULE #5 */
    //Si se exede dias de load type ------> motivo atraso (campo obligatorio) (pendiente)

    /* RULE #6 */
    // Calendario (FISCAL YEAR) (pendiente)                            

    /* RULE #7 */
    // Limite de Bytes campo_ Observaciones (VARCHAR2(4000 BYTE)) (ok)

    /* RULE #8 */
    if (pedimento_r1_1er.replace(/\s/g, "") != "") {

        if (motivo_rectificacion_1er.replace(/\s/g, "") === "") {
            contadorError++;
            txtErrormSg += "(" + contadorError + ")Ingrese Motivo Rectificación 1. ";
            changeColorByPositionError(i);
        }

    }

    /* RULE #9 */
    if (pedimento_r1_2do.replace(/\s/g, "") != "") {

        if (motivo_rectificacion_2do.replace(/\s/g, "") === "") {
            contadorError++;
            txtErrormSg += "(" + contadorError + ")Ingrese Motivo Rectificación 2. ";
            changeColorByPositionError(i);
        }

    }

    /* RULE #10 ESTATUS: IMPORTADO */
    if (estatus_operacion === "19") {
        if (idAgenteAduanal === "4001" || idAgenteAduanal === "4002" || idAgenteAduanal === "4006") { //RADAR|SESMA|VF
            if (pais_origen.replace(/\s/g, "") != "" &&
                size_container.replace(/\s/g, "") != "" &&
                valor_usd.replace(/\s/g, "") != "" &&
                eta_port_discharge.replace(/\s/g, "") != "" &&
                agente_aduanal.replace(/\s/g, "") != "" &&
                pedimento_a1.replace(/\s/g, "") != "" &&
                pedimento_r1_1er.replace(/\s/g, "") != "" &&
                motivo_rectificacion_1er.replace(/\s/g, "") != "" &&
                pedimento_r1_2do.replace(/\s/g, "") != "" &&
                motivo_rectificacion_2do.replace(/\s/g, "") != "" &&
                fecha_recepcion_doc.replace(/\s/g, "") != "" &&
                recinto.replace(/\s/g, "") != "" &&
                naviera.replace(/\s/g, "") != "" &&
                buque.replace(/\s/g, "") != "" &&
                fecha_revalidacion.replace(/\s/g, "") != "" &&
                fecha_previo_origen.replace(/\s/g, "") != "" &&
                fecha_previo_destino.replace(/\s/g, "") != "" &&
                fecha_resultado_previo.replace(/\s/g, "") != "" &&
                proforma_final.replace(/\s/g, "") != "" &&
                permiso.replace(/\s/g, "") != "" &&
                fecha_envio.replace(/\s/g, "") != "" &&
                fecha_recepcion_perm.replace(/\s/g, "") != "" &&
                fecha_activacion_perm.replace(/\s/g, "") != "" &&
                fecha_permisos_aut.replace(/\s/g, "") != "" &&
                co_pref_arancelaria.replace(/\s/g, "") != "" &&
                aplic_pref_arancelaria.replace(/\s/g, "") != "" &&
                req_uva.replace(/\s/g, "") != "" &&
                req_ca.replace(/\s/g, "") != "" &&
                fecha_recepcion_ca.replace(/\s/g, "") != "" &&
                num_constancia_ca.replace(/\s/g, "") != "" &&
                monto_ca.replace(/\s/g, "") != "" &&
                fecha_doc_completos.replace(/\s/g, "") != "" &&
                fecha_pago_pedimento.replace(/\s/g, "") != "" &&
                fecha_solicitud_transporte.replace(/\s/g, "") != "" &&
                fecha_modulacion.replace(/\s/g, "") != "" &&
                modalidad.replace(/\s/g, "") != "" &&
                resultado_modulacion.replace(/\s/g, "") != "" &&
                fecha_reconocimiento.replace(/\s/g, "") != "" &&
                fecha_liberacion.replace(/\s/g, "") != "" &&
                sello_origen.replace(/\s/g, "") != "" &&
                sello_final.replace(/\s/g, "") != "" &&
                motivo_atraso.replace(/\s/g, "") != "" &&
                fy.replace(/\s/g, "") != "") {
                changeColorByPositionSuccess(i);
            }else{
                contadorError++;
                txtErrormSg += "(" + contadorError + ")Todos los campos en rojo son obligatorios. ";
                changeColorByPositionError(i);
                msgErrorAgenteAduanal(i, idAgenteAduanal);
            }
        } else if (idAgenteAduanal === "4001") { //LOGIX
            if (pais_origen.replace(/\s/g, "") != "" &&
                size_container.replace(/\s/g, "") != "" &&
                valor_usd.replace(/\s/g, "") != "" &&
                eta_port_discharge.replace(/\s/g, "") != "" &&
                agente_aduanal.replace(/\s/g, "") != "" &&
                pedimento_a1.replace(/\s/g, "") != "" &&
                pedimento_r1_1er.replace(/\s/g, "") != "" &&
                motivo_rectificacion_1er.replace(/\s/g, "") != "" &&
                pedimento_r1_2do.replace(/\s/g, "") != "" &&
                motivo_rectificacion_2do.replace(/\s/g, "") != "" &&
                fecha_recepcion_doc.replace(/\s/g, "") != "" &&
                recinto.replace(/\s/g, "") != "" &&
                naviera.replace(/\s/g, "") != "" &&
                buque.replace(/\s/g, "") != "" &&
                fecha_revalidacion.replace(/\s/g, "") != "" &&
                fecha_previo_origen.replace(/\s/g, "") != "" &&
                fecha_previo_destino.replace(/\s/g, "") != "" &&
                fecha_resultado_previo.replace(/\s/g, "") != "" &&
                proforma_final.replace(/\s/g, "") != "" &&
                permiso.replace(/\s/g, "") != "" &&
                fecha_envio.replace(/\s/g, "") != "" &&
                fecha_recepcion_perm.replace(/\s/g, "") != "" &&
                fecha_activacion_perm.replace(/\s/g, "") != "" &&
                fecha_permisos_aut.replace(/\s/g, "") != "" &&
                req_ca.replace(/\s/g, "") != "" &&
                fecha_recepcion_ca.replace(/\s/g, "") != "" &&
                num_constancia_ca.replace(/\s/g, "") != "" &&
                monto_ca.replace(/\s/g, "") != "" &&
                fecha_doc_completos.replace(/\s/g, "") != "" &&
                fecha_pago_pedimento.replace(/\s/g, "") != "" &&
                fecha_solicitud_transporte.replace(/\s/g, "") != "" &&
                fecha_modulacion.replace(/\s/g, "") != "" &&
                modalidad.replace(/\s/g, "") != "" &&
                resultado_modulacion.replace(/\s/g, "") != "" &&
                fecha_reconocimiento.replace(/\s/g, "") != "" &&
                fecha_liberacion.replace(/\s/g, "") != "" &&
                sello_origen.replace(/\s/g, "") != "" &&
                sello_final.replace(/\s/g, "") != "" &&
                motivo_atraso.replace(/\s/g, "") != "" &&
                fy.replace(/\s/g, "") != "" &&
                llegada_a_nova.replace(/\s/g, "") != "" &&
                llegada_a_globe_trade_sd.replace(/\s/g, "") != "" &&
                archivo_m.replace(/\s/g, "") != "" &&
                fecha_archivo_m.replace(/\s/g, "") != "" &&
                fecha_solicit_manip.replace(/\s/g, "") != "" &&
                fecha_vencim_manip.replace(/\s/g, "") != "" &&
                fecha_confirm_clave_pedim.replace(/\s/g, "") != "" &&
                fecha_recep_increment.replace(/\s/g, "") != "" &&
                t_e.replace(/\s/g, "") != "" &&
                fecha_vencim_inbound.replace(/\s/g, "") != "") {
                changeColorByPositionSuccess(i);
            }else{
                contadorError++;
                txtErrormSg += "(" + contadorError + ")Todos los campos en rojo son obligatorios. ";
                changeColorByPositionError(i);
                msgErrorAgenteAduanal(i, idAgenteAduanal);
            }
        } else if (idAgenteAduanal === "4002") { //CUSA
            if (pais_origen.replace(/\s/g, "") != "" &&
                valor_usd.replace(/\s/g, "") != "" &&
                eta_port_discharge.replace(/\s/g, "") != "" &&
                agente_aduanal.replace(/\s/g, "") != "" &&
                pedimento_a1.replace(/\s/g, "") != "" &&
                pedimento_r1_1er.replace(/\s/g, "") != "" &&
                motivo_rectificacion_1er.replace(/\s/g, "") != "" &&
                pedimento_r1_2do.replace(/\s/g, "") != "" &&
                motivo_rectificacion_2do.replace(/\s/g, "") != "" &&
                fecha_recepcion_doc.replace(/\s/g, "") != "" &&
                naviera.replace(/\s/g, "") != "" &&
                fecha_revalidacion.replace(/\s/g, "") != "" &&
                fecha_previo_destino.replace(/\s/g, "") != "" &&
                fecha_resultado_previo.replace(/\s/g, "") != "" &&
                proforma_final.replace(/\s/g, "") != "" &&
                permiso.replace(/\s/g, "") != "" &&
                fecha_envio.replace(/\s/g, "") != "" &&
                fecha_recepcion_perm.replace(/\s/g, "") != "" &&
                fecha_activacion_perm.replace(/\s/g, "") != "" &&
                fecha_permisos_aut.replace(/\s/g, "") != "" &&
                co_pref_arancelaria.replace(/\s/g, "") != "" &&
                aplic_pref_arancelaria.replace(/\s/g, "") != "" &&
                req_uva.replace(/\s/g, "") != "" &&
                req_ca.replace(/\s/g, "") != "" &&
                fecha_recepcion_ca.replace(/\s/g, "") != "" &&
                num_constancia_ca.replace(/\s/g, "") != "" &&
                monto_ca.replace(/\s/g, "") != "" &&
                fecha_doc_completos.replace(/\s/g, "") != "" &&
                fecha_pago_pedimento.replace(/\s/g, "") != "" &&
                fecha_solicitud_transporte.replace(/\s/g, "") != "" &&
                fecha_modulacion.replace(/\s/g, "") != "" &&
                modalidad.replace(/\s/g, "") != "" &&
                resultado_modulacion.replace(/\s/g, "") != "" &&
                fecha_reconocimiento.replace(/\s/g, "") != "" &&
                fecha_liberacion.replace(/\s/g, "") != "" &&
                sello_final.replace(/\s/g, "") != "" &&
                motivo_atraso.replace(/\s/g, "") != "" &&
                fy.replace(/\s/g, "") != "" &&
                no_bultos.replace(/\s/g, "") != "" &&
                peso_kg.replace(/\s/g, "") != "" &&
                transferencia.replace(/\s/g, "") != "" &&
                fecha_inicio_etiquetado.replace(/\s/g, "") != "" &&
                fecha_termino_etiquetado.replace(/\s/g, "") != "" &&
                hora_termino_etiquetado.replace(/\s/g, "") != "" &&
                proveedor.replace(/\s/g, "") != "" &&
                proveedor_carga.replace(/\s/g, "") != "") {
                changeColorByPositionSuccess(i);
            }else{
                contadorError++;
                txtErrormSg += "(" + contadorError + ")Todos los campos en rojo son obligatorios. ";
                changeColorByPositionError(i);
                msgErrorAgenteAduanal(i, idAgenteAduanal);
            }
        } else if (idAgenteAduanal === "4005") { //RECHY
            
            if (pais_origen.replace(/\s/g, "") != "" &&
                valor_usd.replace(/\s/g, "") != "" &&
                eta_port_discharge.replace(/\s/g, "") != "" &&
                agente_aduanal.replace(/\s/g, "") != "" &&
                pedimento_a1.replace(/\s/g, "") != "" &&
                pedimento_r1_1er.replace(/\s/g, "") != "" &&
                motivo_rectificacion_1er.replace(/\s/g, "") != "" &&
                pedimento_r1_2do.replace(/\s/g, "") != "" &&
                motivo_rectificacion_2do.replace(/\s/g, "") != "" &&
                fecha_recepcion_doc.replace(/\s/g, "") != "" &&
                fecha_previo_destino.replace(/\s/g, "") != "" &&
                fecha_resultado_previo.replace(/\s/g, "") != "" &&
                proforma_final.replace(/\s/g, "") != "" &&
                permiso.replace(/\s/g, "") != "" &&
                fecha_envio.replace(/\s/g, "") != "" &&
                fecha_recepcion_perm.replace(/\s/g, "") != "" &&
                fecha_activacion_perm.replace(/\s/g, "") != "" &&
                fecha_permisos_aut.replace(/\s/g, "") != "" &&
                co_pref_arancelaria.replace(/\s/g, "") != "" &&
                aplic_pref_arancelaria.replace(/\s/g, "") != "" &&
                req_uva.replace(/\s/g, "") != "" &&
                req_ca.replace(/\s/g, "") != "" &&
                fecha_recepcion_ca.replace(/\s/g, "") != "" &&
                num_constancia_ca.replace(/\s/g, "") != "" &&
                monto_ca.replace(/\s/g, "") != "" &&
                fecha_doc_completos.replace(/\s/g, "") != "" &&
                fecha_pago_pedimento.replace(/\s/g, "") != "" &&
                fecha_solicitud_transporte.replace(/\s/g, "") != "" &&
                fecha_modulacion.replace(/\s/g, "") != "" &&
                modalidad.replace(/\s/g, "") != "" &&
                resultado_modulacion.replace(/\s/g, "") != "" &&
                fecha_reconocimiento.replace(/\s/g, "") != "" &&
                fecha_liberacion.replace(/\s/g, "") != "" &&
                motivo_atraso.replace(/\s/g, "") != "") {
                changeColorByPositionSuccess(i);
            }else{
                contadorError++;
                txtErrormSg += "(" + contadorError + ")Todos los campos en rojo son obligatorios. ";
                changeColorByPositionError(i);
                msgErrorAgenteAduanal(i, idAgenteAduanal);
            }
        }
    }

    let urlData = encodeURI("../InsertarCustomsForms?idAgenteAduanal=" + idAgenteAduanal + "&numCustomsInicial=" + i + "&numCustomsFinal=" + i + urlCustoms);
    try {
        const response = await fetch(urlData);
        if (!response.ok) {
            throw new Error('Error en la solicitud');
        }
        const data = await response.text();

        if (contadorError > 0) {
            mSgErrorLineCustoms(txtErrormSg, i);
        } else {
            changeColorByPositionSuccess(i);
            document.getElementById('mSgError' + i).innerHTML = "";
            msgErrorAgenteAduanal(i, idAgenteAduanal);
        }

        urlCustoms = "";
        cont++;
        let webp = "";

        if (data === "0") {  //Activación Semaforo
            webp = "../img/circle-gray.webp";
        } else if (data === "1") {
            webp = "../img/circle-green.webp";
        } else if (data === "2") {
            webp = "../img/circle-yellow.webp";
        } else if (data === "3") {
            webp = "../img/circle-red.webp";
        }

        //color semaforo
        var imgElement = document.getElementById("imgSemaforo" + i);
        imgElement.src = webp;

    } catch (error) {
        console.error(error);
    }
    contadorError = 0;
    txtErrormSg = "";

    await ocultarLoader();
    swal("", "Información Actualizada", "success");
    alertclose();
}

/*--------------------------------------------------------------------------
 FUNCIONES - REGLAS DE NEGOCIO
 --------------------------------------------------------------------------*/
function pedimento(dateEtaPortDischarge, i) {

    if (dateEtaPortDischarge !== "") {
        document.getElementById("fecha_pago_pedimento[" + i + "]").innerHTML = dateEtaPortDischarge;
    }
}

function cleanPedimento_r1_1er(i) {

    let pedimento_r1_1er = document.getElementById("pedimento_r1_1er[" + i + "]").innerHTML;
    let color = "";
        
    if (pedimento_r1_1er.replace(/\s/g, "") === "") {
        
        document.getElementById("motivo_rectificacion_1er[" + i + "]").innerHTML = "";
        color = "#ced4da";
    } else {
        color = "#CC9D77";
    }
    
    document.getElementById("pedimento_r1_1er[" + i + "]").style.borderColor = color;
    document.getElementById("motivo_rectificacion_1er[" + i + "]").style.borderColor = color;
}

function cleanPedimento_r1_2do(i) {
    
    let pedimento_r1_2do = document.getElementById("pedimento_r1_2do[" + i + "]").innerHTML;
    let color = "";
    
    if (pedimento_r1_2do.replace(/\s/g, "") === "") {
        
        document.getElementById("motivo_rectificacion_2do[" + i + "]").innerHTML = "";
        color = "#ced4da";
    } else {
        color = "#CC9D77";
    }

    document.getElementById("pedimento_r1_2do[" + i + "]").style.borderColor = color;
    document.getElementById("motivo_rectificacion_2do[" + i + "]").style.borderColor = color;
}

function cleanPermiso(permiso, i) {
    let color = "";

    if (permiso === "No") {
        document.getElementById("fecha_envio[" + i + "]").innerHTML = "";
        document.getElementById("fecha_recepcion_perm[" + i + "]").innerHTML = "";
        document.getElementById("fecha_activacion_perm[" + i + "]").innerHTML = "";
        document.getElementById("fecha_permisos_aut[" + i + "]").innerHTML = "";
        color = "#ced4da";
    } else {
        color = "#CC9D77";
    }

    document.getElementById("permiso[" + i + "]").style.borderColor = color;
    document.getElementById("fecha_envio[" + i + "]").style.borderColor = color;
    document.getElementById("fecha_recepcion_perm[" + i + "]").style.borderColor = color;
    document.getElementById("fecha_activacion_perm[" + i + "]").style.borderColor = color;
    document.getElementById("fecha_permisos_aut[" + i + "]").style.borderColor = color;
}

function cleanRequerimientoCA(reqCa, i) {
    let color = "";
    let req = false;

    if (reqCa === "No") {
        document.getElementById("fecha_recepcion_ca[" + i + "]").innerHTML = "";
        document.getElementById("num_constancia_ca[" + i + "]").innerHTML = "";
        document.getElementById("monto_ca[" + i + "]").innerHTML = "";
        colorcelda = "celda-no-bloqueada";
        color = "#ced4da";
        req = true;
    } else {
        colorcelda = "celda-bloqueada";
        color = "#616363";
        req = false;
    }

    document.getElementById("req_ca[" + i + "]").style.borderColor = color;
    document.getElementById("fecha_recepcion_ca[" + i + "]").style.borderColor = color;
    document.getElementById("num_constancia_ca[" + i + "]").style.borderColor = color;
    document.getElementById("monto_ca[" + i + "]").style.borderColor = color;

    /*Habilitar/Deshabilitar campos*/
    let celda1 = document.getElementById("fecha_recepcion_ca[" + i + "]").contentEditable = req;

    // Aplicar clase para indicar que la edición está deshabilitada
    //celda1.classList.add(colorcelda);

}

function modulacion(datePagoPedimento, i) {

    if (datePagoPedimento !== "") {
        document.getElementById("fecha_pago_pedimento[" + i + "]").innerHTML = datePagoPedimento;
        document.getElementById("fecha_modulacion[" + i + "]").innerHTML = datePagoPedimento;
    }
}

function cleanResultadoModulacion(modulacion, i, AgentType) {
    let color = "";

    if (modulacion === "Verde") {
        document.getElementById("sello_origen[" + i + "]").innerHTML = "";
        color = "#ced4da";
    } else {
        color = "#CC9D77";
    }

    if (AgentType != "4005") { //RECHY
        if (AgentType === "4002") { //CUSA
            document.getElementById("sello_final[" + i + "]").style.borderColor = color;
        } else {
            document.getElementById("sello_origen[" + i + "]").style.borderColor = color;
        }
    }

    document.getElementById("resultado_modulacion[" + i + "]").style.borderColor = color;
}

function formComplet(idAgenteAduanal, i) {
    /* RULE #10 */
    const formulario = document.getElementById('tr' + i);
    let disabledOption = "";

    pais_origen = document.getElementById("pais_origen[" + i + "]").innerHTML;
    size_container = document.getElementById("size_container[" + i + "]").innerHTML;
    valor_usd = document.getElementById("valor_usd[" + i + "]").innerHTML;
    eta_port_discharge = document.getElementById("eta_port_discharge[" + i + "]").innerHTML;
    agente_aduanal = document.getElementById("agente_aduanal[" + i + "]").innerHTML;
    pedimento_a1 = document.getElementById("pedimento_a1[" + i + "]").innerHTML;
    pedimento_r1_1er = document.getElementById("pedimento_r1_1er[" + i + "]").innerHTML;
    motivo_rectificacion_1er = document.getElementById("motivo_rectificacion_1er[" + i + "]").innerHTML;
    pedimento_r1_2do = document.getElementById("pedimento_r1_2do[" + i + "]").innerHTML;
    motivo_rectificacion_2do = document.getElementById("motivo_rectificacion_2do[" + i + "]").innerHTML;
    fecha_recepcion_doc = document.getElementById("fecha_recepcion_doc[" + i + "]").innerHTML;
    recinto = document.getElementById("recinto[" + i + "]").innerHTML;
    naviera = document.getElementById("naviera[" + i + "]").innerHTML;
    buque = document.getElementById("buque[" + i + "]").innerHTML;
    fecha_revalidacion = document.getElementById("fecha_revalidacion[" + i + "]").innerHTML;
    fecha_previo_origen = document.getElementById("fecha_previo_origen[" + i + "]").innerHTML;
    fecha_previo_destino = document.getElementById("fecha_previo_destino[" + i + "]").innerHTML;
    fecha_resultado_previo = document.getElementById("fecha_resultado_previo[" + i + "]").innerHTML;
    proforma_final = document.getElementById("proforma_final[" + i + "]").innerHTML;
    permiso = document.getElementById("permiso[" + i + "]").innerHTML;
    fecha_envio = document.getElementById("fecha_envio[" + i + "]").innerHTML;
    fecha_recepcion_perm = document.getElementById("fecha_recepcion_perm[" + i + "]").innerHTML;
    fecha_activacion_perm = document.getElementById("fecha_activacion_perm[" + i + "]").innerHTML;
    fecha_permisos_aut = document.getElementById("fecha_permisos_aut[" + i + "]").innerHTML;
    co_pref_arancelaria = document.getElementById("co_pref_arancelaria[" + i + "]").innerHTML;
    aplic_pref_arancelaria = document.getElementById("aplic_pref_arancelaria[" + i + "]").innerHTML;
    req_uva = document.getElementById("req_uva[" + i + "]").innerHTML;
    req_ca = document.getElementById("req_ca[" + i + "]").innerHTML;
    fecha_recepcion_ca = document.getElementById("fecha_recepcion_ca[" + i + "]").innerHTML;
    num_constancia_ca = document.getElementById("num_constancia_ca[" + i + "]").innerHTML;
    monto_ca = document.getElementById("monto_ca[" + i + "]").innerHTML;
    fecha_doc_completos = document.getElementById("fecha_doc_completos[" + i + "]").innerHTML;
    fecha_pago_pedimento = document.getElementById("fecha_pago_pedimento[" + i + "]").innerHTML;
    fecha_solicitud_transporte = document.getElementById("fecha_solicitud_transporte[" + i + "]").innerHTML;
    fecha_modulacion = document.getElementById("fecha_modulacion[" + i + "]").innerHTML;
    modalidad = document.getElementById("modalidad[" + i + "]").innerHTML;
    resultado_modulacion = document.getElementById("resultado_modulacion[" + i + "]").innerHTML;
    fecha_reconocimiento = document.getElementById("fecha_reconocimiento[" + i + "]").innerHTML;
    fecha_liberacion = document.getElementById("fecha_liberacion[" + i + "]").innerHTML;
    sello_origen = document.getElementById("sello_origen[" + i + "]").innerHTML;
    sello_final = document.getElementById("sello_final[" + i + "]").innerHTML;
    motivo_atraso = document.getElementById("motivo_atraso[" + i + "]").innerHTML;
    fy = document.getElementById("fy[" + i + "]").innerHTML;

    if (idAgenteAduanal === "4001") { //LOGIX
        llegada_a_nova = document.getElementById("llegada_a_nova[" + i + "]").innerHTML;
        llegada_a_globe_trade_sd = document.getElementById("llegada_a_globe_trade_sd[" + i + "]").innerHTML;
        archivo_m = document.getElementById("archivo_m[" + i + "]").innerHTML;
        fecha_archivo_m = document.getElementById("fecha_archivo_m[" + i + "]").innerHTML;
        fecha_solicit_manip = document.getElementById("fecha_solicit_manip[" + i + "]").innerHTML;
        fecha_vencim_manip = document.getElementById("fecha_vencim_manip[" + i + "]").innerHTML;
        fecha_confirm_clave_pedim = document.getElementById("fecha_confirm_clave_pedim[" + i + "]").innerHTML;
        fecha_recep_increment = document.getElementById("fecha_recep_increment[" + i + "]").innerHTML;
        t_e = document.getElementById("t_e[" + i + "]").innerHTML;
        fecha_vencim_inbound = document.getElementById("fecha_vencim_inbound[" + i + "]").innerHTML;
    }

    if (idAgenteAduanal === "4002") { //CUSA
        no_bultos = document.getElementById("no_bultos[" + i + "]").innerHTML;
        peso_kg = document.getElementById("peso_kg[" + i + "]").innerHTML;
        transferencia = document.getElementById("transferencia[" + i + "]").innerHTML;
        fecha_inicio_etiquetado = document.getElementById("fecha_inicio_etiquetado[" + i + "]").innerHTML;
        fecha_termino_etiquetado = document.getElementById("fecha_termino_etiquetado[" + i + "]").innerHTML;
        hora_termino_etiquetado = document.getElementById("hora_termino_etiquetado[" + i + "]").value;
        proveedor = document.getElementById("proveedor[" + i + "]").innerHTML;
        proveedor_carga = document.getElementById("proveedor_carga[" + i + "]").innerHTML;
    }

    /* RULE #10 */   /*(Estatus Importado)*/
    if (idAgenteAduanal === "4001" || idAgenteAduanal === "4002" || idAgenteAduanal === "4006") { //RADAR|SESMA|VF
        if (pais_origen.replace(/\s/g, "") == "" || size_container.replace(/\s/g, "") == "" || valor_usd.replace(/\s/g, "") == "" || eta_port_discharge.replace(/\s/g, "") == "" || agente_aduanal.replace(/\s/g, "") == "" || pedimento_a1.replace(/\s/g, "") == "" || pedimento_r1_1er.replace(/\s/g, "") == "" || motivo_rectificacion_1er.replace(/\s/g, "") == "" || pedimento_r1_2do.replace(/\s/g, "") == "" || motivo_rectificacion_2do.replace(/\s/g, "") == "" || fecha_recepcion_doc.replace(/\s/g, "") == "" || recinto.replace(/\s/g, "") == "" || naviera.replace(/\s/g, "") == "" || buque.replace(/\s/g, "") == "" || fecha_revalidacion.replace(/\s/g, "") == "" || fecha_previo_origen.replace(/\s/g, "") == "" || fecha_previo_destino.replace(/\s/g, "") == "" || fecha_resultado_previo.replace(/\s/g, "") == "" || proforma_final.replace(/\s/g, "") == "" || co_pref_arancelaria.replace(/\s/g, "") == "" || aplic_pref_arancelaria.replace(/\s/g, "") == "" || req_uva.replace(/\s/g, "") == "" || req_ca.replace(/\s/g, "") == "" || fecha_recepcion_ca.replace(/\s/g, "") == "" || num_constancia_ca.replace(/\s/g, "") == "" || monto_ca.replace(/\s/g, "") == "" || fecha_doc_completos.replace(/\s/g, "") == "" || fecha_pago_pedimento.replace(/\s/g, "") == "" || fecha_solicitud_transporte.replace(/\s/g, "") == "" || fecha_modulacion.replace(/\s/g, "") == "" || modalidad.replace(/\s/g, "") == "" || resultado_modulacion.replace(/\s/g, "") == "" || fecha_reconocimiento.replace(/\s/g, "") == "" || fecha_liberacion.replace(/\s/g, "") == "" || sello_origen.replace(/\s/g, "") == "" || sello_final.replace(/\s/g, "") == "" || motivo_atraso.replace(/\s/g, "") == "" || fy.replace(/\s/g, "") == "") {
            disabledOption = "false";
        }
        if (permiso === "Si") {
            if (fecha_envio.replace(/\s/g, "") == "" || fecha_recepcion_perm.replace(/\s/g, "") == "" || fecha_activacion_perm.replace(/\s/g, "") == "" || fecha_permisos_aut.replace(/\s/g, "") == "") {
                disabledOption = "false";
            }
        }
    } else if (idAgenteAduanal === "4001") { //LOGIX
        if (pais_origen.replace(/\s/g, "") == "" || size_container.replace(/\s/g, "") == "" || valor_usd.replace(/\s/g, "") == "" || eta_port_discharge.replace(/\s/g, "") == "" || agente_aduanal.replace(/\s/g, "") == "" || pedimento_a1.replace(/\s/g, "") == "" || pedimento_r1_1er.replace(/\s/g, "") == "" || motivo_rectificacion_1er.replace(/\s/g, "") == "" || pedimento_r1_2do.replace(/\s/g, "") == "" || motivo_rectificacion_2do.replace(/\s/g, "") == "" || fecha_recepcion_doc.replace(/\s/g, "") == "" || recinto.replace(/\s/g, "") == "" || naviera.replace(/\s/g, "") == "" || buque.replace(/\s/g, "") == "" || fecha_revalidacion.replace(/\s/g, "") == "" || fecha_previo_origen.replace(/\s/g, "") == "" || fecha_previo_destino.replace(/\s/g, "") == "" || fecha_resultado_previo.replace(/\s/g, "") == "" || proforma_final.replace(/\s/g, "") == "" || req_ca.replace(/\s/g, "") == "" || fecha_recepcion_ca.replace(/\s/g, "") == "" || num_constancia_ca.replace(/\s/g, "") == "" || monto_ca.replace(/\s/g, "") == "" || fecha_doc_completos.replace(/\s/g, "") == "" || fecha_pago_pedimento.replace(/\s/g, "") == "" || fecha_solicitud_transporte.replace(/\s/g, "") == "" || fecha_modulacion.replace(/\s/g, "") == "" || modalidad.replace(/\s/g, "") == "" || resultado_modulacion.replace(/\s/g, "") == "" || fecha_reconocimiento.replace(/\s/g, "") == "" || fecha_liberacion.replace(/\s/g, "") == "" || sello_origen.replace(/\s/g, "") == "" || sello_final.replace(/\s/g, "") == "" || motivo_atraso.replace(/\s/g, "") == "" || fy.replace(/\s/g, "") == "" || llegada_a_nova.replace(/\s/g, "") == "" || llegada_a_globe_trade_sd.replace(/\s/g, "") == "" || archivo_m.replace(/\s/g, "") == "" || fecha_archivo_m.replace(/\s/g, "") == "" || fecha_solicit_manip.replace(/\s/g, "") == "" || fecha_vencim_manip.replace(/\s/g, "") == "" || fecha_confirm_clave_pedim.replace(/\s/g, "") == "" || fecha_recep_increment.replace(/\s/g, "") == "" || t_e.replace(/\s/g, "") == "" || fecha_vencim_inbound.replace(/\s/g, "") == "") {
            disabledOption = "false";
        }
        if (permiso === "Si") {
            if (fecha_envio.replace(/\s/g, "") == "" || fecha_recepcion_perm.replace(/\s/g, "") == "" || fecha_activacion_perm.replace(/\s/g, "") == "" || fecha_permisos_aut.replace(/\s/g, "") == "") {
                disabledOption = "false";
            }
        }
    } else if (idAgenteAduanal === "4002") { //CUSA
        if (pais_origen.replace(/\s/g, "") == "" || valor_usd.replace(/\s/g, "") == "" || eta_port_discharge.replace(/\s/g, "") == "" || agente_aduanal.replace(/\s/g, "") == "" || pedimento_a1.replace(/\s/g, "") == "" || pedimento_r1_1er.replace(/\s/g, "") == "" || motivo_rectificacion_1er.replace(/\s/g, "") == "" || pedimento_r1_2do.replace(/\s/g, "") == "" || motivo_rectificacion_2do.replace(/\s/g, "") == "" || fecha_recepcion_doc.replace(/\s/g, "") == "" || naviera.replace(/\s/g, "") == "" || fecha_revalidacion.replace(/\s/g, "") == "" || fecha_previo_destino.replace(/\s/g, "") == "" || fecha_resultado_previo.replace(/\s/g, "") == "" || proforma_final.replace(/\s/g, "") == "" || co_pref_arancelaria.replace(/\s/g, "") == "" || aplic_pref_arancelaria.replace(/\s/g, "") == "" || req_uva.replace(/\s/g, "") == "" || req_ca.replace(/\s/g, "") == "" || fecha_recepcion_ca.replace(/\s/g, "") == "" || num_constancia_ca.replace(/\s/g, "") == "" || monto_ca.replace(/\s/g, "") == "" || fecha_doc_completos.replace(/\s/g, "") == "" || fecha_pago_pedimento.replace(/\s/g, "") == "" || fecha_solicitud_transporte.replace(/\s/g, "") == "" || fecha_modulacion.replace(/\s/g, "") == "" || modalidad.replace(/\s/g, "") == "" || resultado_modulacion.replace(/\s/g, "") == "" || fecha_reconocimiento.replace(/\s/g, "") == "" || fecha_liberacion.replace(/\s/g, "") == "" || sello_final.replace(/\s/g, "") == "" || motivo_atraso.replace(/\s/g, "") == "" || fy.replace(/\s/g, "") == "" || no_bultos.replace(/\s/g, "") == "" || peso_kg.replace(/\s/g, "") == "" || transferencia.replace(/\s/g, "") == "" || fecha_inicio_etiquetado.replace(/\s/g, "") == "" || fecha_termino_etiquetado.replace(/\s/g, "") == "" || hora_termino_etiquetado.replace(/\s/g, "") == "" || proveedor.replace(/\s/g, "") == "" || proveedor_carga.replace(/\s/g, "") == "") {
            disabledOption = "false";
        }
        if (permiso === "Si") {
            if (fecha_envio.replace(/\s/g, "") == "" || fecha_recepcion_perm.replace(/\s/g, "") == "" || fecha_activacion_perm.replace(/\s/g, "") == "" || fecha_permisos_aut.replace(/\s/g, "") == "") {
                disabledOption = "false";
            }
        }
    } else if (idAgenteAduanal === "4005") { //RECHY
        if (pais_origen.replace(/\s/g, "") == "" || valor_usd.replace(/\s/g, "") == "" || eta_port_discharge.replace(/\s/g, "") == "" || agente_aduanal.replace(/\s/g, "") == "" || pedimento_a1.replace(/\s/g, "") == "" || pedimento_r1_1er.replace(/\s/g, "") == "" || motivo_rectificacion_1er.replace(/\s/g, "") == "" || pedimento_r1_2do.replace(/\s/g, "") == "" || motivo_rectificacion_2do.replace(/\s/g, "") == "" || fecha_recepcion_doc.replace(/\s/g, "") == "" || fecha_previo_destino.replace(/\s/g, "") == "" || fecha_resultado_previo.replace(/\s/g, "") == "" || proforma_final.replace(/\s/g, "") == "" || co_pref_arancelaria.replace(/\s/g, "") == "" || aplic_pref_arancelaria.replace(/\s/g, "") == "" || req_uva.replace(/\s/g, "") == "" || req_ca.replace(/\s/g, "") == "" || fecha_recepcion_ca.replace(/\s/g, "") == "" || num_constancia_ca.replace(/\s/g, "") == "" || monto_ca.replace(/\s/g, "") == "" || fecha_doc_completos.replace(/\s/g, "") == "" || fecha_pago_pedimento.replace(/\s/g, "") == "" || fecha_solicitud_transporte.replace(/\s/g, "") == "" || fecha_modulacion.replace(/\s/g, "") == "" || modalidad.replace(/\s/g, "") == "" || resultado_modulacion.replace(/\s/g, "") == "" || fecha_reconocimiento.replace(/\s/g, "") == "" || fecha_liberacion.replace(/\s/g, "") == "" || motivo_atraso.replace(/\s/g, "") == "" || fy.replace(/\s/g, "") == "") {
            disabledOption = "false";
        }
        if (permiso === "Si") {
            if (fecha_envio.replace(/\s/g, "") == "" || fecha_recepcion_perm.replace(/\s/g, "") == "" || fecha_activacion_perm.replace(/\s/g, "") == "" || fecha_permisos_aut.replace(/\s/g, "") == "") {
                disabledOption = "false";
            }
        }
    }

    var selector = document.getElementById("estatus_operacion[" + i + "]");
    selector.options[19].disabled = disabledOption;

}

/*--------------------------------------------------------------------------
 FUNCIONES - BUSCADORES MULTISELECT
 --------------------------------------------------------------------------*/

async function FiltrerData(tipoFiltro) {

    await mostrarLoader();
    
        selected1 = $('#col_referenciaAA').val();
        selected2 = $('#col_evento').val(); 
        selected3 = $('#col_responsable').val();
        selected4 = $('#col_finalDestination').val();
        selected5 = $('#col_brandDivision').val();
        selected6 = $('#col_division').val();
        selected7 = $('#col_shipmentId').val();
        selected8 = $('#col_container').val();
        selected9 = $('#col_blAwbPro').val();
        selected10 = $('#col_loadType').val();
        selected11 = $('#col_quantity').val();
        selected12 = $('#col_pod').val();
        selected13 = $('#col_estDepartFromPol').val();
        selected14 = $('#col_etaRealPortOfDischarge').val();
        selected15 = $('#col_estEtaDc').val();
        selected16 = $('#col_inboundNotification').val();
        selected17 = $('#col_pol').val();
        selected18 = $('#col_aa').val();
        selected19 = $('#col_fechaMesVenta').val();
        selected20 = $('#col_prioridad').val();
        selected21 = $('#col_pais_origen').val();
        selected22 = $('#col_size_container').val();
        selected23 = $('#col_valor_usd').val();
        selected24 = $('#col_eta_port_discharge').val();
        selected25 = $('#col_agente_aduanal').val();
        selected26 = $('#col_pedimento_a1').val();
        selected27 = $('#col_pedimento_r1_1er').val();
        selected28 = $('#col_motivo_rectificacion_1er').val();
        selected29 = $('#col_pedimento_r1_2do').val();
        selected30 = $('#col_motivo_rectificacion_2do').val();
        selected31 = $('#col_fecha_recepcion_doc').val();
        selected32 = $('#col_recinto').val();
        selected33 = $('#col_naviera').val();
        selected34 = $('#col_buque').val();
        selected35 = $('#col_fecha_revalidacion').val();
        selected36 = $('#col_fecha_previo_origen').val();
        selected37 = $('#col_fecha_previo_destino').val();
        selected38 = $('#col_fecha_resultado_previo').val();
        selected39 = $('#col_proforma_final').val();
        selected40 = $('#col_permiso').val();
        selected41 = $('#col_fecha_envio').val();
        selected42 = $('#col_fecha_recepcion_perm').val();
        selected43 = $('#col_fecha_activacion_perm').val();
        selected44 = $('#col_fecha_permisos_aut').val();
        selected45 = $('#col_co_pref_arancelaria').val();
        selected46 = $('#col_aplic_pref_arancelaria').val();
        selected47 = $('#col_req_uva').val();
        selected48 = $('#col_req_ca').val();
        selected49 = $('#col_fecha_recepcion_ca').val();
        selected50 = $('#col_num_constancia_ca').val();
        selected51 = $('#col_monto_ca').val();
        selected52 = $('#col_fecha_doc_completos').val();
        selected53 = $('#col_fecha_pago_pedimento').val();
        selected54 = $('#col_fecha_solicitud_transporte').val();
        selected55 = $('#col_fecha_modulacion').val();
        selected56 = $('#col_modalidad').val();
        selected57 = $('#col_resultado_modulacion').val();
        selected58 = $('#col_fecha_reconocimiento').val();
        selected59 = $('#col_fecha_liberacion').val();
        selected60 = $('#col_sello_origen').val();
        selected61 = $('#col_sello_final').val();
        selected62 = $('#col_fecha_retencion_aut').val();
        selected63 = $('#col_fecha_liberacion_aut').val();
        selected64 = $('#col_estatus_operacion').val();
        selected65 = $('#col_motivo_atraso').val();
        selected66 = $('#col_observaciones').val();
        selected67 = $('#col_llegada_a_nova').val();
        selected68 = $('#col_llegada_a_globe_trade_sd').val();
        selected69 = $('#col_archivo_m').val();
        selected70 = $('#col_fecha_archivo_m').val();
        selected71 = $('#col_fecha_solicit_manip').val();
        selected72 = $('#col_fecha_vencim_manip').val();
        selected73 = $('#col_fecha_confirm_clave_pedim').val();
        selected74 = $('#col_fecha_recep_increment').val();
        selected75 = $('#col_t_e').val();
        selected76 = $('#col_fecha_vencim_inbound').val();
        selected77 = $('#col_no_bultos').val();
        selected78 = $('#col_peso_kg').val();
        selected79 = $('#col_transferencia').val();
        selected80 = $('#col_fecha_inicio_etiquetado').val();
        selected81 = $('#col_fecha_termino_etiquetado').val();
        selected82 = $('#col_hora_termino_etiquetado').val();
        selected83 = $('#col_proveedor').val();
        selected84 = $('#col_proveedor_carga').val();
        selected85 = $('#col_fy').val();

    await consultarCustoms(idAgenteAduanal, tipoFiltro, 
                            selected1,
                            selected2,
                            selected3,
                            selected4,
                            selected5,
                            selected6,
                            selected7,
                            selected8,
                            selected9,
                            selected10,
                            selected11,
                            selected12,
                            selected13,
                            selected14,
                            selected15,
                            selected16,
                            selected17,
                            selected18,
                            selected19,
                            selected20,
                            selected21,
                            selected22,
                            selected23,
                            selected24,
                            selected25,
                            selected26,
                            selected27,
                            selected28,
                            selected29,
                            selected30,
                            selected31,
                            selected32,
                            selected33,
                            selected34,
                            selected35,
                            selected36,
                            selected37,
                            selected38,
                            selected39,
                            selected40,
                            selected41,
                            selected42,
                            selected43,
                            selected44,
                            selected45,
                            selected46,
                            selected47,
                            selected48,
                            selected49,
                            selected50,
                            selected51,
                            selected52,
                            selected53,
                            selected54,
                            selected55,
                            selected56,
                            selected57,
                            selected58,
                            selected59,
                            selected60,
                            selected61,
                            selected62,
                            selected63,
                            selected64,
                            selected65,
                            selected66,
                            selected67,
                            selected68,
                            selected69,
                            selected70,
                            selected71,
                            selected72,
                            selected73,
                            selected74,
                            selected75,
                            selected76,
                            selected77,
                            selected78,
                            selected79,
                            selected80,
                            selected81,
                            selected82,
                            selected83,
                            selected84,
                            selected85);                        
}

function cleanMultiselects(tipoFiltro) {
    
    FiltrerData("0");
        
    if (tipoFiltro === "1") {
       $('#col_referenciaAA').val(null).trigger('change');
    } else if (tipoFiltro === "2") {
       $('#col_evento').val(null).trigger('change'); 
    } else if (tipoFiltro === "3") { 
       $('#col_responsable').val(null).trigger('change');
    } else if (tipoFiltro === "4") { 
       $('#col_finalDestination').val(null).trigger('change');
    } else if (tipoFiltro === "5") {
       $('#col_brandDivision').val(null).trigger('change');
    } else if (tipoFiltro === "6") {
       $('#col_division').val(null).trigger('change');
    } else if (tipoFiltro === "7") {
       $('#col_shipmentId').val(null).trigger('change');
    } else if (tipoFiltro === "8") {
       $('#col_container').val(null).trigger('change');
    } else if (tipoFiltro === "9") {
       $('#col_blAwbPro').val(null).trigger('change');
    } else if (tipoFiltro === "10") {
       $('#col_loadType').val(null).trigger('change');
    } else if (tipoFiltro === "11") {
       $('#col_quantity').val(null).trigger('change');
    } else if (tipoFiltro === "12") {
       $('#col_pod').val(null).trigger('change');
    } else if (tipoFiltro === "13") {
       $('#col_estDepartFromPol').val(null).trigger('change');
    } else if (tipoFiltro === "14") {
       $('#col_etaRealPortOfDischarge').val(null).trigger('change');
    } else if (tipoFiltro === "15") {
       $('#col_estEtaDc').val(null).trigger('change');
    } else if (tipoFiltro === "16") {
       $('#col_inboundNotification').val(null).trigger('change');
    } else if (tipoFiltro === "17") {
       $('#col_pol').val(null).trigger('change');
    } else if (tipoFiltro === "18") {
       $('#col_aa').val(null).trigger('change');
    } else if (tipoFiltro === "19") {
       $('#col_fechaMesVenta').val(null).trigger('change');
    } else if (tipoFiltro === "20") {
       $('#col_prioridad').val(null).trigger('change');
    } else if (tipoFiltro === "21") {
       $('#col_pais_origen').val(null).trigger('change');
    } else if (tipoFiltro === "22") {
       $('#col_size_container').val(null).trigger('change');
    } else if (tipoFiltro === "23") {
       $('#col_valor_usd').val(null).trigger('change');
    } else if (tipoFiltro === "24") {
       $('#col_eta_port_discharge').val(null).trigger('change');
    } else if (tipoFiltro === "25") {
       $('#col_agente_aduanal').val(null).trigger('change');
    } else if (tipoFiltro === "26") {
       $('#col_pedimento_a1').val(null).trigger('change');
    } else if (tipoFiltro === "27") {
       $('#col_pedimento_r1_1er').val(null).trigger('change');
    } else if (tipoFiltro === "28") {
       $('#col_motivo_rectificacion_1er').val(null).trigger('change');
    } else if (tipoFiltro === "29") {
       $('#col_pedimento_r1_2do').val(null).trigger('change');
    } else if (tipoFiltro === "30") {
       $('#col_motivo_rectificacion_2do').val(null).trigger('change');
    } else if (tipoFiltro === "31") {
       $('#col_fecha_recepcion_doc').val(null).trigger('change');
    } else if (tipoFiltro === "32") {
       $('#col_recinto').val(null).trigger('change');
    } else if (tipoFiltro === "33") {
       $('#col_naviera').val(null).trigger('change');
    } else if (tipoFiltro === "34") {
       $('#col_buque').val(null).trigger('change');
    } else if (tipoFiltro === "35") {
       $('#col_fecha_revalidacion').val(null).trigger('change');
    } else if (tipoFiltro === "36") {
       $('#col_fecha_previo_origen').val(null).trigger('change');
    } else if (tipoFiltro === "37") {
       $('#col_fecha_previo_destino').val(null).trigger('change');
    } else if (tipoFiltro === "38") {
       $('#col_fecha_resultado_previo').val(null).trigger('change');
    } else if (tipoFiltro === "39") {
       $('#col_proforma_final').val(null).trigger('change');
    } else if (tipoFiltro === "40") {
       $('#col_permiso').val(null).trigger('change');
    } else if (tipoFiltro === "41") {
       $('#col_fecha_envio').val(null).trigger('change');
    } else if (tipoFiltro === "42") {
       $('#col_fecha_recepcion_perm').val(null).trigger('change');
    } else if (tipoFiltro === "43") {
       $('#col_fecha_activacion_perm').val(null).trigger('change');
    } else if (tipoFiltro === "44") {
       $('#col_fecha_permisos_aut').val(null).trigger('change');
    } else if (tipoFiltro === "45") {
       $('#col_co_pref_arancelaria').val(null).trigger('change');
    } else if (tipoFiltro === "46") {
       $('#col_aplic_pref_arancelaria').val(null).trigger('change');
    } else if (tipoFiltro === "47") {
       $('#col_req_uva').val(null).trigger('change');
    } else if (tipoFiltro === "48") {
       $('#col_req_ca').val(null).trigger('change');
    } else if (tipoFiltro === "49") {
       $('#col_fecha_recepcion_ca').val(null).trigger('change');
    } else if (tipoFiltro === "50") { 
       $('#col_num_constancia_ca').val(null).trigger('change');
    } else if (tipoFiltro === "51") { 
       $('#col_monto_ca').val(null).trigger('change');
    } else if (tipoFiltro === "52") {
       $('#col_fecha_doc_completos').val(null).trigger('change');
    } else if (tipoFiltro === "53") {
       $('#col_fecha_pago_pedimento').val(null).trigger('change');
    } else if (tipoFiltro === "54") {
       $('#col_fecha_solicitud_transporte').val(null).trigger('change');
    } else if (tipoFiltro === "55") {
       $('#col_fecha_modulacion').val(null).trigger('change');
    } else if (tipoFiltro === "56") {
       $('#col_modalidad').val(null).trigger('change');
    } else if (tipoFiltro === "57") {
       $('#col_resultado_modulacion').val(null).trigger('change');
    } else if (tipoFiltro === "58") {
       $('#col_fecha_reconocimiento').val(null).trigger('change');
    } else if (tipoFiltro === "59") {
       $('#col_fecha_liberacion').val(null).trigger('change');
    } else if (tipoFiltro === "60") {
       $('#col_sello_origen').val(null).trigger('change');
    } else if (tipoFiltro === "61") {
       $('#col_sello_final').val(null).trigger('change');
    } else if (tipoFiltro === "62") {
       $('#col_fecha_retencion_aut').val(null).trigger('change');
    } else if (tipoFiltro === "63") {
       $('#col_fecha_liberacion_aut').val(null).trigger('change');
    } else if (tipoFiltro === "64") {
       $('#col_estatus_operacion').val(null).trigger('change');
    } else if (tipoFiltro === "65") {
       $('#col_motivo_atraso').val(null).trigger('change');
    } else if (tipoFiltro === "66") {
       $('#col_observaciones').val(null).trigger('change');
    } else if (tipoFiltro === "67") {
       $('#col_llegada_a_nova').val(null).trigger('change');
    } else if (tipoFiltro === "68") {
       $('#col_llegada_a_globe_trade_sd').val(null).trigger('change');
    } else if (tipoFiltro === "69") {
       $('#col_archivo_m').val(null).trigger('change');
    } else if (tipoFiltro === "70") {
       $('#col_fecha_archivo_m').val(null).trigger('change');
    } else if (tipoFiltro === "71") {
       $('#col_fecha_solicit_manip').val(null).trigger('change');
    } else if (tipoFiltro === "72") {
       $('#col_fecha_vencim_manip').val(null).trigger('change');
    } else if (tipoFiltro === "73") {
       $('#col_fecha_confirm_clave_pedim').val(null).trigger('change');
    } else if (tipoFiltro === "74") {
       $('#col_fecha_recep_increment').val(null).trigger('change');
    } else if (tipoFiltro === "75") {
       $('#col_t_e').val(null).trigger('change');
    } else if (tipoFiltro === "76") {
       $('#col_fecha_vencim_inbound').val(null).trigger('change');
    } else if (tipoFiltro === "77") {
       $('#col_no_bultos').val(null).trigger('change');
    } else if (tipoFiltro === "78") {
       $('#col_peso_kg').val(null).trigger('change');
    } else if (tipoFiltro === "79") {
       $('#col_transferencia').val(null).trigger('change');
    } else if (tipoFiltro === "80") {
       $('#col_fecha_inicio_etiquetado').val(null).trigger('change');
    } else if (tipoFiltro === "81") {
       $('#col_fecha_termino_etiquetado').val(null).trigger('change');
    } else if (tipoFiltro === "82") {
       $('#col_hora_termino_etiquetado').val(null).trigger('change');
    } else if (tipoFiltro === "83") {
       $('#col_proveedor').val(null).trigger('change');
    } else if (tipoFiltro === "84") {
       $('#col_proveedor_carga').val(null).trigger('change');
    } else if (tipoFiltro === "85") {
       $('#col_fy').val(null).trigger('change');
    }
}
/*--------------------------------------------------------------------------
 FUNCIONES - CONSULTA HISTORICO SEMAFORO
 --------------------------------------------------------------------------*/
function historicoSemaforo(idShipment) {
    fetch("../ConsultarHistoricoSemaforo?idShipment=" + idShipment, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                document.getElementById('AddTableSemaforo').innerHTML = data;
                document.getElementById("idSemaforo").innerHTML = " SHIPMENT ID:  " + idShipment;
                $("#modalSemaforo").modal("show");
            }).catch(error => console.log(error));
}

/*--------------------------------------------------------------------------
 FUNCIONES - LOG DE ERRORES/ALERTAS
 --------------------------------------------------------------------------*/
function msgErrorAgenteAduanal(i, AgentType) {

    let estatus = document.getElementById("estatus_operacion[" + i + "]").value;
    let color = "";

    if (estatus != 19) {
        color = "#ced4da";
    } else {
        color = "#db7f7f";
    }

    if (AgentType === "4001" || AgentType === "4002" || AgentType === "4006") { //RADAR|SESMA|VF

        document.getElementById("pais_origen[" + i + "]").style.borderColor = color;
        document.getElementById("size_container[" + i + "]").style.borderColor = color;
        document.getElementById("valor_usd[" + i + "]").style.borderColor = color;
        document.getElementById("eta_port_discharge[" + i + "]").style.borderColor = color;
        document.getElementById("agente_aduanal[" + i + "]").style.borderColor = color;
        document.getElementById("pedimento_a1[" + i + "]").style.borderColor = color;
        document.getElementById("pedimento_r1_1er[" + i + "]").style.borderColor = color;
        document.getElementById("motivo_rectificacion_1er[" + i + "]").style.borderColor = color;
        document.getElementById("pedimento_r1_2do[" + i + "]").style.borderColor = color;
        document.getElementById("motivo_rectificacion_2do[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_recepcion_doc[" + i + "]").style.borderColor = color;
        document.getElementById("recinto[" + i + "]").style.borderColor = color;
        document.getElementById("naviera[" + i + "]").style.borderColor = color;
        document.getElementById("buque[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_revalidacion[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_previo_origen[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_previo_destino[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_resultado_previo[" + i + "]").style.borderColor = color;
        document.getElementById("proforma_final[" + i + "]").style.borderColor = color;
        document.getElementById("permiso[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_envio[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_recepcion_perm[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_activacion_perm[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_permisos_aut[" + i + "]").style.borderColor = color;
        document.getElementById("co_pref_arancelaria[" + i + "]").style.borderColor = color;
        document.getElementById("aplic_pref_arancelaria[" + i + "]").style.borderColor = color;
        document.getElementById("req_uva[" + i + "]").style.borderColor = color;
        document.getElementById("req_ca[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_recepcion_ca[" + i + "]").style.borderColor = color;
        document.getElementById("num_constancia_ca[" + i + "]").style.borderColor = color;
        document.getElementById("monto_ca[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_doc_completos[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_pago_pedimento[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_solicitud_transporte[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_modulacion[" + i + "]").style.borderColor = color;
        document.getElementById("modalidad[" + i + "]").style.borderColor = color;
        document.getElementById("resultado_modulacion[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_reconocimiento[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_liberacion[" + i + "]").style.borderColor = color;
        document.getElementById("sello_origen[" + i + "]").style.borderColor = color;
        document.getElementById("sello_final[" + i + "]").style.borderColor = color;
        document.getElementById("estatus_operacion[" + i + "]").style.borderColor = color;
        document.getElementById("motivo_atraso[" + i + "]").style.borderColor = color;
        document.getElementById("fy[" + i + "]").style.borderColor = color;

    } else if (AgentType === "4001") { //LOGIX

        document.getElementById("pais_origen[" + i + "]").style.borderColor = color;
        document.getElementById("size_container[" + i + "]").style.borderColor = color;
        document.getElementById("valor_usd[" + i + "]").style.borderColor = color;
        document.getElementById("eta_port_discharge[" + i + "]").style.borderColor = color;
        document.getElementById("agente_aduanal[" + i + "]").style.borderColor = color;
        document.getElementById("pedimento_a1[" + i + "]").style.borderColor = color;
        document.getElementById("pedimento_r1_1er[" + i + "]").style.borderColor = color;
        document.getElementById("motivo_rectificacion_1er[" + i + "]").style.borderColor = color;
        document.getElementById("pedimento_r1_2do[" + i + "]").style.borderColor = color;
        document.getElementById("motivo_rectificacion_2do[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_recepcion_doc[" + i + "]").style.borderColor = color;
        document.getElementById("recinto[" + i + "]").style.borderColor = color;
        document.getElementById("naviera[" + i + "]").style.borderColor = color;
        document.getElementById("buque[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_revalidacion[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_previo_origen[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_previo_destino[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_resultado_previo[" + i + "]").style.borderColor = color;
        document.getElementById("proforma_final[" + i + "]").style.borderColor = color;
        document.getElementById("permiso[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_envio[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_recepcion_perm[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_activacion_perm[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_permisos_aut[" + i + "]").style.borderColor = color;
        document.getElementById("req_ca[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_recepcion_ca[" + i + "]").style.borderColor = color;
        document.getElementById("num_constancia_ca[" + i + "]").style.borderColor = color;
        document.getElementById("monto_ca[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_doc_completos[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_pago_pedimento[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_solicitud_transporte[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_modulacion[" + i + "]").style.borderColor = color;
        document.getElementById("modalidad[" + i + "]").style.borderColor = color;
        document.getElementById("resultado_modulacion[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_reconocimiento[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_liberacion[" + i + "]").style.borderColor = color;
        document.getElementById("sello_origen[" + i + "]").style.borderColor = color;
        document.getElementById("sello_final[" + i + "]").style.borderColor = color;
        document.getElementById("estatus_operacion[" + i + "]").style.borderColor = color;
        document.getElementById("motivo_atraso[" + i + "]").style.borderColor = color;
        document.getElementById("fy[" + i + "]").style.borderColor = color;
        document.getElementById("llegada_a_nova[" + i + "]").style.borderColor = color;
        document.getElementById("llegada_a_globe_trade_sd[" + i + "]").style.borderColor = color;
        document.getElementById("archivo_m[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_archivo_m[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_solicit_manip[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_vencim_manip[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_confirm_clave_pedim[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_recep_increment[" + i + "]").style.borderColor = color;
        document.getElementById("t_e[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_vencim_inbound[" + i + "]").style.borderColor = color;

    } else if (AgentType === "4002") { //CUSA

        document.getElementById("pais_origen[" + i + "]").style.borderColor = color;
        document.getElementById("valor_usd[" + i + "]").style.borderColor = color;
        document.getElementById("eta_port_discharge[" + i + "]").style.borderColor = color;
        document.getElementById("agente_aduanal[" + i + "]").style.borderColor = color;
        document.getElementById("pedimento_a1[" + i + "]").style.borderColor = color;
        document.getElementById("pedimento_r1_1er[" + i + "]").style.borderColor = color;
        document.getElementById("motivo_rectificacion_1er[" + i + "]").style.borderColor = color;
        document.getElementById("pedimento_r1_2do[" + i + "]").style.borderColor = color;
        document.getElementById("motivo_rectificacion_2do[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_recepcion_doc[" + i + "]").style.borderColor = color;
        document.getElementById("naviera[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_revalidacion[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_previo_destino[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_resultado_previo[" + i + "]").style.borderColor = color;
        document.getElementById("proforma_final[" + i + "]").style.borderColor = color;
        document.getElementById("permiso[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_envio[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_recepcion_perm[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_activacion_perm[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_permisos_aut[" + i + "]").style.borderColor = color;
        document.getElementById("co_pref_arancelaria[" + i + "]").style.borderColor = color;
        document.getElementById("aplic_pref_arancelaria[" + i + "]").style.borderColor = color;
        document.getElementById("req_uva[" + i + "]").style.borderColor = color;
        document.getElementById("req_ca[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_recepcion_ca[" + i + "]").style.borderColor = color;
        document.getElementById("num_constancia_ca[" + i + "]").style.borderColor = color;
        document.getElementById("monto_ca[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_doc_completos[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_pago_pedimento[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_solicitud_transporte[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_modulacion[" + i + "]").style.borderColor = color;
        document.getElementById("modalidad[" + i + "]").style.borderColor = color;
        document.getElementById("resultado_modulacion[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_reconocimiento[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_liberacion[" + i + "]").style.borderColor = color;
        document.getElementById("sello_final[" + i + "]").style.borderColor = color;
        document.getElementById("estatus_operacion[" + i + "]").style.borderColor = color;
        document.getElementById("motivo_atraso[" + i + "]").style.borderColor = color;
        document.getElementById("fy[" + i + "]").style.borderColor = color;
        document.getElementById("no_bultos[" + i + "]").style.borderColor = color;
        document.getElementById("peso_kg[" + i + "]").style.borderColor = color;
        document.getElementById("transferencia[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_inicio_etiquetado[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_termino_etiquetado[" + i + "]").style.borderColor = color;
        document.getElementById("hora_termino_etiquetado[" + i + "]").style.borderColor = color;
        document.getElementById("proveedor[" + i + "]").style.borderColor = color;
        document.getElementById("proveedor_carga[" + i + "]").style.borderColor = color;

    } else if (AgentType === "4005") { //RECHY

        document.getElementById("pais_origen[" + i + "]").style.borderColor = color;
        document.getElementById("valor_usd[" + i + "]").style.borderColor = color;
        document.getElementById("eta_port_discharge[" + i + "]").style.borderColor = color;
        document.getElementById("agente_aduanal[" + i + "]").style.borderColor = color;
        document.getElementById("pedimento_a1[" + i + "]").style.borderColor = color;
        document.getElementById("pedimento_r1_1er[" + i + "]").style.borderColor = color;
        document.getElementById("motivo_rectificacion_1er[" + i + "]").style.borderColor = color;
        document.getElementById("pedimento_r1_2do[" + i + "]").style.borderColor = color;
        document.getElementById("motivo_rectificacion_2do[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_recepcion_doc[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_previo_destino[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_resultado_previo[" + i + "]").style.borderColor = color;
        document.getElementById("proforma_final[" + i + "]").style.borderColor = color;
        document.getElementById("permiso[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_envio[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_recepcion_perm[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_activacion_perm[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_permisos_aut[" + i + "]").style.borderColor = color;
        document.getElementById("co_pref_arancelaria[" + i + "]").style.borderColor = color;
        document.getElementById("aplic_pref_arancelaria[" + i + "]").style.borderColor = color;
        document.getElementById("req_uva[" + i + "]").style.borderColor = color;
        document.getElementById("req_ca[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_recepcion_ca[" + i + "]").style.borderColor = color;
        document.getElementById("num_constancia_ca[" + i + "]").style.borderColor = color;
        document.getElementById("monto_ca[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_doc_completos[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_pago_pedimento[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_solicitud_transporte[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_modulacion[" + i + "]").style.borderColor = color;
        document.getElementById("modalidad[" + i + "]").style.borderColor = color;
        document.getElementById("resultado_modulacion[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_reconocimiento[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_liberacion[" + i + "]").style.borderColor = color;
        document.getElementById("estatus_operacion[" + i + "]").style.borderColor = color;
        document.getElementById("motivo_atraso[" + i + "]").style.borderColor = color;
        document.getElementById("fy[" + i + "]").style.borderColor = color;

    }
}

function changeColorByPositionSuccess(i) {
    const table = document.querySelector("table");
    if (table) {
        const row = table.rows[i]; // Primera fila
        const cell = row.cells[2]; // Primer <td> en la primera fila

        if (cell) {
            cell.style.backgroundColor = "#ABD1D5";
            cell.style.color = "#000000";
        }
    }
}

function changeColorByPositionError(i) {
    const table = document.querySelector("table");
    if (table) {
        const row = table.rows[i]; // Primera fila
        const cell = row.cells[2]; // Primer <td> en la primera fila

        if (cell) {
            cell.style.backgroundColor = "#E88C50";
            cell.style.color = "#000000";
        }
    }
}

function mSgErrorLineCustoms(txtErrormSg, i) {
    // Obtén una referencia al elemento y al popup
    const elemento = document.getElementById('elemento' + i);
    const popup = document.getElementById('popup' + i);

    //Formato texto 
    const txt = txtErrormSg.substring(0, txtErrormSg.length - 1);
    let msg = txt.replace(",", "\t");

    //Cargar mensaje en label
    document.getElementById('mSgError' + i).innerHTML = msg;

    // Agrega un manejador de eventos al elemento para detectar el paso del cursor
    elemento.addEventListener('mouseover', mostrarPopup);
    elemento.addEventListener('mouseout', ocultarPopup);

    // Función para mostrar el popup
    function mostrarPopup() {
        popup.style.display = 'block';
    }

    // Función para ocultar el popup
    function ocultarPopup() {
        popup.style.display = 'none';
    }
}

function alertclose() {
    setTimeout(function () {
        swal.close();
        //$("#WindowLoad").remove();
    }, 1000);
}

/*--------------------------------------------------------------------------
 FUNCIONES - EXCEL
 --------------------------------------------------------------------------*/
function logExcel() {
    mostrarOcultarDiv('divAMostrarOcultar', true);
}

function mostrarOcultarDiv(id, mostrar) {
    var elemento = document.getElementById(id);
    if (mostrar) {
        elemento.style.display = 'block';
    } else {
        elemento.style.display = 'none';
    }
}

/*--------------------------------------------------------------------------
 FUNCIONES - LOADER
 --------------------------------------------------------------------------*/
function waitForTableToLoad() {
    return new Promise(resolve => {
        document.addEventListener("readystatechange", async function () {

            if (document.readyState === "complete") {
                resolve();
                await jsRemoveWindowLoad();
            }

        });
    });
}

async function mostrarLoader() {
    var loaderWrapper = document.getElementById("loader-wrapper");
    loaderWrapper.style.display = "flex";
}

async function ocultarLoader() {
    var loaderWrapper = document.getElementById("loader-wrapper");
    loaderWrapper.style.display = "none";
}

/*--------------------------------------------------------------------------
 FUNCIONES - RECARGA DE SCRIPTS
 --------------------------------------------------------------------------*/
async function loadCss() {
    // Create a new script element
    var script = document.createElement('script');

    // Set the source of the script to the same script you want to reload
    script.src = '../lib/calendarios/css/flatpickr.min.css';

    // Append the script to the body
    document.body.appendChild(script);
}

async function loadJsPicker() {
    $('.datepicker').flatpickr({
        dateFormat: 'm/d/Y',
        inline: true
    });
}

/*--------------------------------------------------------------------------
 FUNCIONES - MODALS ELEMENTOS HTML/CONTROLADORES
 --------------------------------------------------------------------------*/

function show_eta_port_discharge(data, i) {
    $("#modal_show_eta_port_discharge").modal("show");
    loadJsPicker();
    document.getElementById("eta_port_discharge").value = data;
    contModals = i;
    var numRows = i;
    
    for(var v = 0; v < numRows; v++){
        
    }
    
}

function hide_eta_port_discharge(data) {
    $("#modal_show_eta_port_discharge").modal("hide");
    document.getElementById("eta_port_discharge[" + contModals + "]").innerHTML = data;
    pedimento(data, contModals);
    let color = "";
    
    if(data!==""){
        color = "#FF9E00"; //FF9E00
    }else{
       color = "#ced4da";
    }

    document.getElementById("eta_port_discharge[" + contModals + "]").style.borderColor = color;
    document.getElementById("fecha_pago_pedimento[" + contModals + "]").style.borderColor = color;
    document.getElementById("fecha_modulacion[" + contModals + "]").style.borderColor = color;
    parametrizacionValoresEvento("eta_port_discharge",contModals); 
}

/*function show_pedimento_r1_1er(data, i){
 $("#modal_pedimento_r1_1er").modal("show");
 document.getElementById("pedimento_r1_1er").value = data;
 contModals = i;
 }
 
 function hide_pedimento_r1_1er(data){ 
 $("#modal_pedimento_r1_1er").modal("hide");
 cleanPedimento_r1_1er(data, contModals);
 }*/

/*function show_pedimento_r1_2do(data, i){
 $("#modal_pedimento_r1_2do").modal("show");
 document.getElementById("pedimento_r1_2do").value = data;
 contModals = i;
 }
 
 function hide_pedimento_r1_2do(data){ 
 $("#modal_pedimento_r1_2do").modal("hide");
 cleanPedimento_r1_2do(data, contModals);
 }*/

function show_fecha_recepcion_doc(data, i) {
    $("#modal_fecha_recepcion_doc").modal("show");
    loadJsPicker();
    document.getElementById("fecha_recepcion_doc").value = data;
    contModals = i;
}

function hide_fecha_recepcion_doc(data) {
    contModals;
    document.getElementById("fecha_recepcion_doc[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_recepcion_doc").modal("hide");
    parametrizacionValoresEvento("fecha_recepcion_doc",contModals); 
}

function show_fecha_revalidacion(data, i) {
    $("#modal_fecha_revalidacion").modal("show");
    loadJsPicker();
    document.getElementById("fecha_revalidacion").value = data;
    contModals = i;
}

function hide_fecha_revalidacion(data) {
    contModals;
    document.getElementById("fecha_revalidacion[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_revalidacion").modal("hide");
    parametrizacionValoresEvento("fecha_revalidacion",contModals); 
}

function show_fecha_previo_origen(data, i) {
    $("#modal_fecha_previo_origen").modal("show");
    loadJsPicker();
    document.getElementById("fecha_previo_origen").value = data;
    contModals = i;
}

function hide_fecha_previo_origen(data) {
    contModals;
    document.getElementById("fecha_previo_origen[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_previo_origen").modal("hide");
    //parametrizacionValoresEvento("fecha_previo_origen",contModals);     
}

function show_fecha_previo_destino(data, i) {
    $("#modal_fecha_previo_destino").modal("show");
    loadJsPicker();
    document.getElementById("fecha_previo_destino").value = data;
    contModals = i;
}

function hide_fecha_previo_destino(data) {
    contModals;
    document.getElementById("fecha_previo_destino[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_previo_destino").modal("hide");
    //parametrizacionValoresEvento("fecha_previo_destino",contModals); 
}

function show_fecha_resultado_previo(data, i) {
    $("#modal_fecha_resultado_previo").modal("show");
    loadJsPicker();
    document.getElementById("fecha_resultado_previo").value = data;
    contModals = i;
}

function hide_fecha_resultado_previo(data) {
    contModals;
    document.getElementById("fecha_resultado_previo[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_resultado_previo").modal("hide");
    //parametrizacionValoresEvento("fecha_resultado_previo",contModals); 
}

function show_proforma_final(data, i) {
    $("#modal_proforma_final").modal("show");
    loadJsPicker();
    document.getElementById("proforma_final").value = data;
    contModals = i;
}

function hide_proforma_final(data) {
    contModals;
    document.getElementById("proforma_final[" + contModals + "]").innerHTML = data;
    $("#modal_proforma_final").modal("hide");
    //parametrizacionValoresEvento("proforma_final",contModals); 
}

function show_permiso(i) {
    
    $("#modal_permiso").modal("show");
    let res = document.getElementById("permiso[" + i + "]").innerHTML;
    contModals = i;

    if (res == "Si") {
        check1_permiso = true;
        check2_permiso = false;
    } else if (res == "No") {
        check1_permiso = false;
        check2_permiso = true;
    } else {
        check1_permiso = false;
        check2_permiso = false;
    }
    
    document.getElementById("permiso1").checked = check1_permiso;
    document.getElementById("permiso2").checked = check2_permiso;

}

function hide_permiso(data) {

    if (data == "Si") {
        check1_permiso = true;
        check2_permiso = false;
    } else if (data == "No") {
        check1_permiso = false;
        check2_permiso = true;
    } else {
        check1_permiso = false;
        check2_permiso = false;
    }
    document.getElementById("permiso1").checked = check1_permiso;
    document.getElementById("permiso2").checked = check2_permiso;

    contModals;
    document.getElementById("permiso[" + contModals + "]").innerHTML = data;
    cleanPermiso(data, contModals);
    $("#modal_permiso").modal("hide");
}

function show_fecha_envio(data, i) {
    $("#modal_fecha_envio").modal("show");
    loadJsPicker();
    document.getElementById("fecha_envio").value = data;
    contModals = i;
}

function hide_fecha_envio(data) {
    contModals;
    document.getElementById("fecha_envio[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_envio").modal("hide");
    //parametrizacionValoresEvento("fecha_envio",contModals); 
}

function show_fecha_recepcion_perm(data, i) {
    $("#modal_fecha_recepcion_perm").modal("show");
    loadJsPicker();
    document.getElementById("fecha_recepcion_perm").value = data;
    contModals = i;
}

function hide_fecha_recepcion_perm(data) {
    contModals;
    document.getElementById("fecha_recepcion_perm[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_recepcion_perm").modal("hide");
    //parametrizacionValoresEvento("fecha_recepcion_perm",contModals); 
}

function show_fecha_activacion_perm(data, i) {
    $("#modal_fecha_activacion_perm").modal("show");
    loadJsPicker();
    document.getElementById("fecha_activacion_perm").value = data;
    contModals = i;
}

function hide_fecha_activacion_perm(data) {
    contModals;
    document.getElementById("fecha_activacion_perm[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_activacion_perm").modal("hide");
    parametrizacionValoresEvento("fecha_activacion_perm",contModals); 
}

function show_fecha_permisos_aut(data, i) {
    $("#modal_fecha_permisos_aut").modal("show");
    loadJsPicker();
    document.getElementById("fecha_permisos_aut").value = data;
    contModals = i;
}

function hide_fecha_permisos_aut(data) {
    contModals;
    document.getElementById("fecha_permisos_aut[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_permisos_aut").modal("hide");
    //parametrizacionValoresEvento("fecha_permisos_aut",contModals); 
}

function show_co_pref_arancelaria(i) {
    $("#modal_co_pref_arancelaria").modal("show");
    let res = document.getElementById("co_pref_arancelaria[" + i + "]").innerHTML;
    contModals = i;

    if (res == "Si") {
        check1_co_pref_arancelaria1 = true;
        check2_co_pref_arancelaria1 = false;
    } else if (res == "No") {
        check1_co_pref_arancelaria1 = false;
        check2_co_pref_arancelaria1 = true;
    } else {
        check1_co_pref_arancelaria1 = false;
        check2_co_pref_arancelaria1 = false;
    }
    document.getElementById("co_pref_arancelaria1").checked = check1_co_pref_arancelaria1;
    document.getElementById("co_pref_arancelaria2").checked = check2_co_pref_arancelaria1;

}

function hide_co_pref_arancelaria(data) {

    if (data == "Si") {
        check1_co_pref_arancelaria1 = true;
        check2_co_pref_arancelaria1 = false;
    } else if (data == "No") {
        check1_co_pref_arancelaria1 = false;
        check2_co_pref_arancelaria1 = true;
    } else {
        check1_co_pref_arancelaria1 = false;
        check2_co_pref_arancelaria1 = false;
    }
    document.getElementById("co_pref_arancelaria1").checked = check1_co_pref_arancelaria1;
    document.getElementById("co_pref_arancelaria2").checked = check2_co_pref_arancelaria1;

    contModals;
    document.getElementById("co_pref_arancelaria[" + contModals + "]").innerHTML = data;
    $("#modal_co_pref_arancelaria").modal("hide");
    
    if (data == "No") {
        document.getElementById("aplic_pref_arancelaria[" + contModals + "]").innerHTML = "No";
    }else{
        document.getElementById("aplic_pref_arancelaria[" + contModals + "]").innerHTML = "";
    }
    
}

function show_aplic_pref_arancelaria(i) {
    $("#modal_aplic_pref_arancelaria").modal("show");
    let res = document.getElementById("aplic_pref_arancelaria[" + i + "]").innerHTML;
    contModals = i;

    if (res == "Si") {
        check1_aplic_pref_arancelaria1 = true;
        check2_aplic_pref_arancelaria1 = false;
    } else if (res == "No") {
        check1_aplic_pref_arancelaria1 = false;
        check2_aplic_pref_arancelaria1 = true;
    } else {
        check1_aplic_pref_arancelaria1 = false;
        check2_aplic_pref_arancelaria1 = false;
    }
    document.getElementById("aplic_pref_arancelaria1").checked = check1_aplic_pref_arancelaria1;
    document.getElementById("aplic_pref_arancelaria2").checked = check2_aplic_pref_arancelaria1;

}

function hide_aplic_pref_arancelaria(data) {

    if (data == "Si") {
        check1_aplic_pref_arancelaria1 = true;
        check2_aplic_pref_arancelaria1 = false;
    } else if (data == "No") {
        check1_aplic_pref_arancelaria1 = false;
        check2_aplic_pref_arancelaria1 = true;
    }
    document.getElementById("aplic_pref_arancelaria1").checked = check1_aplic_pref_arancelaria1;
    document.getElementById("aplic_pref_arancelaria2").checked = check2_aplic_pref_arancelaria1;

    contModals;
    document.getElementById("aplic_pref_arancelaria[" + contModals + "]").innerHTML = data;
    $("#modal_aplic_pref_arancelaria").modal("hide");
}

function show_req_uva(i) {
    $("#modal_req_uva").modal("show");
    let res = document.getElementById("req_uva[" + i + "]").innerHTML;
    contModals = i;

    if (res == "Si") {
        check1_req_uva = true;
        check2_req_uva = false;
    } else if (res == "No") {
        check1_req_uva = false;
        check2_req_uva = true;
    } else {
        check1_req_uva = false;
        check2_req_uva = false;
    }
    document.getElementById("req_uva1").checked = check1_req_uva;
    document.getElementById("req_uva2").checked = check2_req_uva;
}

function hide_req_uva(data) {

    if (data == "Si") {
        check1_req_uva = true;
        check2_req_uva = false;
    } else if (data == "No") {
        check1_req_uva = false;
        check2_req_uva = true;
    } else {
        check1_req_uva = false;
        check2_req_uva = false;
    }
    document.getElementById("req_uva1").checked = check1_req_uva;
    document.getElementById("req_uva2").checked = check2_req_uva;

    contModals;
    document.getElementById("req_uva[" + contModals + "]").innerHTML = data;
    $("#modal_req_uva").modal("hide");
}

function show_req_ca(i) {
    $("#modal_req_ca").modal("show");
    let res = document.getElementById("req_ca[" + i + "]").innerHTML;
    contModals = i;

    if (res == "Si") {
        check1_req_ca = true;
        check2_req_ca = false;
    } else if (res == "No") {
        check1_req_ca = false;
        check2_req_ca = true;
    } else {
        check1_req_ca = false;
        check2_req_ca = false;
    }
    document.getElementById("req_ca1").checked = check1_req_ca;
    document.getElementById("req_ca2").checked = check2_req_ca;
}

function hide_req_ca(data) {

    if (data == "Si") {
        check1_req_ca = true;
        check2_req_ca = false;
    } else if (data == "No") {
        check1_req_ca = false;
        check2_req_ca = true;
    }
    document.getElementById("req_ca1").checked = check1_req_ca;
    document.getElementById("req_ca2").checked = check2_req_ca;

    contModals;
    $("#modal_req_ca").modal("hide");
    document.getElementById("req_ca[" + contModals + "]").innerHTML = data;
    cleanRequerimientoCA(data, contModals);
}

function show_fecha_recepcion_ca(data, i) {
    $("#modal_fecha_recepcion_ca").modal("show");
    loadJsPicker();
    document.getElementById("fecha_recepcion_ca").value = data;
    contModals = i;
}

function hide_fecha_recepcion_ca(data) {
    contModals;
    document.getElementById("fecha_recepcion_ca[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_recepcion_ca").modal("hide");
    //parametrizacionValoresEvento("fecha_recepcion_ca",contModals); 
}

function show_fecha_doc_completos(data, i) {
    $("#modal_fecha_doc_completos").modal("show");
    loadJsPicker();
    document.getElementById("fecha_doc_completos").value = data;
    contModals = i;
}

function hide_fecha_doc_completos(data) {
    contModals;
    document.getElementById("fecha_doc_completos[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_doc_completos").modal("hide");
    parametrizacionValoresEvento("fecha_doc_completos",contModals); 
}

function show_fecha_pago_pedimento(i) {
    $("#modal_fecha_pago_pedimento").modal("show");
    let res = document.getElementById("fecha_pago_pedimento[" + i + "]").innerHTML;
    document.getElementById("fecha_pago_pedimento").innerHTML =res;
    
    $('.datepicker-pedimento').flatpickr({
        dateFormat: 'm/d/Y',
        minDate: res,
        maxDate: new Date().fp_incr(50),
        inline: true
    });
    contModals = i;
}

function hide_fecha_pago_pedimento(data) {
    $("#modal_fecha_pago_pedimento").modal("hide");
    contModals;
    document.getElementById("fecha_pago_pedimento[" + contModals + "]").innerHTML =data;
    modulacion(data, contModals);
    //parametrizacionValoresEvento("fecha_pago_pedimento",contModals); 
}

function show_fecha_solicitud_transporte(data, i) {
    $("#modal_fecha_solicitud_transporte").modal("show");
    loadJsPicker();
    document.getElementById("fecha_solicitud_transporte").value = data;
    contModals = i;
}

function hide_fecha_solicitud_transporte(data) {
    contModals;
    document.getElementById("fecha_solicitud_transporte[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_solicitud_transporte").modal("hide");
    parametrizacionValoresEvento("fecha_solicitud_transporte",contModals); 
}

function show_fecha_modulacion(i) {
    $("#modal_fecha_modulacion").modal("show");
    let res = document.getElementById("fecha_modulacion[" + i + "]").innerHTML;
    document.getElementById("fecha_modulacion").innerHTML =res;
    
    $('.datepicker-modulacion').flatpickr({
        dateFormat: 'm/d/Y',
        minDate: res,
        maxDate: new Date().fp_incr(50),
        inline: true
    });
    contModals = i;
}

function hide_fecha_modulacion(data) {
    contModals;
    document.getElementById("fecha_modulacion[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_modulacion").modal("hide");
    parametrizacionValoresEvento("fecha_modulacion",contModals); 
}

function show_modalidad(i) {
    $("#modal_modalidad").modal("show");
    let res = document.getElementById("modalidad[" + i + "]").innerHTML;
    contModals = i;

    if (res == "Camión") {
        check1_modalidad = true;
        check2_modalidad = false;
    } else if (res == "Tren") {
        check1_modalidad = false;
        check2_modalidad = true;
    } else {
        check1_modalidad = false;
        check2_modalidad = false;
    }
    document.getElementById("modalidad1").checked = check1_modalidad;
    document.getElementById("modalidad2").checked = check2_modalidad;
}

function hide_modalidad(data) {

    if (data == "Camión") {
        check1_modalidad = true;
        check2_modalidad = false;
    } else if (data == "Tren") {
        check1_modalidad = false;
        check2_modalidad = true;
    }
    document.getElementById("modalidad1").checked = check1_modalidad;
    document.getElementById("modalidad2").checked = check2_modalidad;

    contModals;
    document.getElementById("modalidad[" + contModals + "]").innerHTML = data;
    $("#modal_modalidad").modal("hide");
    
    parametrizacionValoresEvento("modalidad",contModals); 
    
}

function show_resultado_modulacion(i, AgenteType) {
    $("#modal_resultado_modulacion").modal("show");
    let res = document.getElementById("resultado_modulacion[" + i + "]").innerHTML;
    contModals = i;
    AgenteId = AgenteType;

    if (res == "Verde") {
        check1_resultado_modulacion = true;
        check2_resultado_modulacion = false;
    } else if (res == "Rojo") {
        check1_resultado_modulacion = false;
        check2_resultado_modulacion = true;
    } else {
        check1_resultado_modulacion = false;
        check2_resultado_modulacion = false;
    }
    document.getElementById("resultado_modulacion1").checked = check1_resultado_modulacion;
    document.getElementById("resultado_modulacion2").checked = check2_resultado_modulacion;
}

function hide_resultado_modulacion(data) {

    if (data == "Verde") {
        check1_resultado_modulacion = true;
        check2_resultado_modulacion = false;
    } else if (data == "Rojo") {
        check1_resultado_modulacion = false;
        check2_resultado_modulacion = true;
    }
    document.getElementById("resultado_modulacion1").checked = check1_resultado_modulacion;
    document.getElementById("resultado_modulacion2").checked = check2_resultado_modulacion;

    contModals;
    $("#modal_resultado_modulacion").modal("hide");
    document.getElementById("resultado_modulacion[" + contModals + "]").innerHTML = data;
    cleanResultadoModulacion(data, contModals, AgenteId);
    
    parametrizacionValoresEvento("resultado_modulacion",contModals); 
}

function show_fecha_reconocimiento(data, i) {
    $("#modal_fecha_reconocimiento").modal("show");
    loadJsPicker();
    document.getElementById("fecha_reconocimiento").value = data;
    contModals = i;
}

function hide_fecha_reconocimiento(data) {
    contModals;
    document.getElementById("fecha_reconocimiento[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_reconocimiento").modal("hide");
    parametrizacionValoresEvento("fecha_reconocimiento",contModals);
}

function show_fecha_liberacion(data, i) {
    $("#modal_fecha_liberacion").modal("show");
    loadJsPicker();
    document.getElementById("fecha_liberacion").value = data;
    contModals = i;
}

function hide_fecha_liberacion(data) {
    contModals;
    document.getElementById("fecha_liberacion[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_liberacion").modal("hide");
    parametrizacionValoresEvento("fecha_liberacion",contModals);
}

function show_fecha_retencion_aut(data, i) {
    $("#modal_fecha_retencion_aut").modal("show");
    loadJsPicker();
    document.getElementById("fecha_retencion_aut").value = data;
    contModals = i;
}

function hide_fecha_retencion_aut(data) {
    contModals;
    document.getElementById("fecha_retencion_aut[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_retencion_aut").modal("hide");
    parametrizacionValoresEvento("fecha_retencion_aut",contModals);
}

function show_fecha_liberacion_aut(data, i) {
    $("#modal_fecha_liberacion_aut").modal("show");
    loadJsPicker();
    document.getElementById("fecha_liberacion_aut").value = data;
    contModals = i;
}

function hide_fecha_liberacion_aut(data) {
    contModals;
    document.getElementById("fecha_liberacion_aut[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_liberacion_aut").modal("hide");
    parametrizacionValoresEvento("fecha_liberacion_aut",contModals);
}

function show_llegada_a_nova(data, i) {
    $("#modal_llegada_a_nova").modal("show");
    loadJsPicker();
    document.getElementById("llegada_a_nova").value = data;
    contModals = i;
}

function hide_llegada_a_nova(data) {
    contModals;
    document.getElementById("llegada_a_nova[" + contModals + "]").innerHTML = data;
    $("#modal_llegada_a_nova").modal("hide");
    parametrizacionValoresEvento("llegada_a_nova",contModals);
}

function show_llegada_a_globe_trade_sd(data, i) {
    $("#modal_llegada_a_globe_trade_sd").modal("show");
    loadJsPicker();
    document.getElementById("llegada_a_globe_trade_sd").value = data;
    contModals = i;
}

function hide_llegada_a_globe_trade_sd(data) {
    contModals;
    document.getElementById("llegada_a_globe_trade_sd[" + contModals + "]").innerHTML = data;
    $("#modal_llegada_a_globe_trade_sd").modal("hide");
    parametrizacionValoresEvento("llegada_a_globe_trade_sd",contModals);
}

function show_fecha_archivo_m(data, i) {
    $("#modal_fecha_archivo_m").modal("show");
    loadJsPicker();
    document.getElementById("fecha_archivo_m").value = data;
    contModals = i;
}

function hide_fecha_archivo_m(data) {
    contModals;
    document.getElementById("fecha_archivo_m[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_archivo_m").modal("hide");
    parametrizacionValoresEvento("fecha_archivo_m",contModals);
}

function show_fecha_solicit_manip(data, i) {
    $("#modal_fecha_solicit_manip").modal("show");
    loadJsPicker();
    document.getElementById("fecha_solicit_manip").value = data;
    contModals = i;
}

function hide_fecha_solicit_manip(data) {
    contModals;
    document.getElementById("fecha_solicit_manip[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_solicit_manip").modal("hide");
    parametrizacionValoresEvento("fecha_solicit_manip",contModals);
}

function show_fecha_vencim_manip(data, i) {
    $("#modal_fecha_vencim_manip").modal("show");
    loadJsPicker();
    document.getElementById("fecha_vencim_manip").value = data;
    contModals = i;
}

function hide_fecha_vencim_manip(data) {
    contModals;
    document.getElementById("fecha_vencim_manip[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_vencim_manip").modal("hide");
    parametrizacionValoresEvento("fecha_vencim_manip",contModals);
}

function show_fecha_confirm_clave_pedim(data, i) {
    $("#modal_fecha_confirm_clave_pedim").modal("show");
    loadJsPicker();
    document.getElementById("fecha_confirm_clave_pedim").value = data;
    contModals = i;
}

function hide_fecha_confirm_clave_pedim(data) {
    contModals;
    document.getElementById("fecha_confirm_clave_pedim[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_confirm_clave_pedim").modal("hide");
    parametrizacionValoresEvento("fecha_confirm_clave_pedim",contModals);
}

function show_fecha_recep_increment(data, i) {
    $("#modal_fecha_recep_increment").modal("show");
    loadJsPicker();
    document.getElementById("fecha_recep_increment").value = data;
    contModals = i;
}

function hide_fecha_recep_increment(data) {
    contModals;
    document.getElementById("fecha_recep_increment[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_recep_increment").modal("hide");
    parametrizacionValoresEvento("fecha_recep_increment",contModals);
}

function show_fecha_vencim_inbound(data, i) {
    $("#modal_fecha_vencim_inbound").modal("show");
    loadJsPicker();
    document.getElementById("fecha_vencim_inbound").value = data;
    contModals = i;
}

function hide_fecha_vencim_inbound(data) {
    contModals;
    document.getElementById("fecha_vencim_inbound[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_vencim_inbound").modal("hide");
    parametrizacionValoresEvento("fecha_vencim_inbound",contModals);
}

function show_transferencia(i) {
    $("#modal_transferencia").modal("show");
    let res = document.getElementById("transferencia[" + i + "]").innerHTML;
    contModals = i;

    if (res == "Si") {
        check1_transferencia = true;
        check2_transferencia = false;
    } else if (res == "No") {
        check1_transferencia = false;
        check2_transferencia = true;
    } else {
        check1_transferencia = false;
        check2_transferencia = false;
    }
    document.getElementById("transferencia1").checked = check1_transferencia;
    document.getElementById("transferencia2").checked = check2_transferencia;
}

function hide_transferencia(data) {

    if (data == "Si") {
        check1_transferencia = true;
        check2_transferencia = false;
    } else if (data == "No") {
        check1_transferencia = false;
        check2_transferencia = true;
    }
    document.getElementById("transferencia1").checked = check1_transferencia;
    document.getElementById("transferencia2").checked = check2_transferencia;

    contModals;
    document.getElementById("transferencia[" + contModals + "]").innerHTML = data;
    $("#modal_transferencia").modal("hide");
}

function show_fecha_inicio_etiquetado(data, i) {
    $("#modal_fecha_inicio_etiquetado").modal("show");
    loadJsPicker();
    document.getElementById("fecha_inicio_etiquetado").value = data;
    contModals = i;
}

function hide_fecha_inicio_etiquetado(data) {
    contModals;
    document.getElementById("fecha_inicio_etiquetado[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_inicio_etiquetado").modal("hide");
    parametrizacionValoresEvento("fecha_inicio_etiquetado",contModals);
}

function show_fecha_termino_etiquetado(data, i) {
    $("#modal_fecha_termino_etiquetado").modal("show");
    loadJsPicker();
    document.getElementById("fecha_termino_etiquetado").value = data;
    contModals = i;
}

function hide_fecha_termino_etiquetado(data) {
    contModals;
    document.getElementById("fecha_termino_etiquetado[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_termino_etiquetado").modal("hide");
    parametrizacionValoresEvento("fecha_termino_etiquetado",contModals);
}

function validarNumero(event) {
    // Obtener el contenido actual del TD
    let contenido = event.target.innerText;

    // Reemplazar comas por puntos para asegurar el formato decimal
    contenido = contenido.replace(',', '.');

    // Validar si el contenido es un número o un número decimal
    if (/^(\d*\.?\d+|\d+\.?\d*)$/.test(contenido)) {
        // Si es un número o un número decimal válido, permitir que permanezca en el TD
        event.target.style.color = 'black';
    } else {
        // Si no es un número o un número decimal válido, eliminar el contenido y mostrar un color de texto rojo
        event.target.innerText = '';
        event.target.style.color = 'red';
    }
}


function validarTextoAlfanumerico(td,namecelda,cont) {
  // Obtener el contenido actual del TD
  let contenido = td.innerText;
  
  if (/^[a-zA-Z0-9\s]+$/.test(contenido)) {
    // Si es alfanumérico, puedes realizar acciones adicionales
    console.log("Contenido del TD:", contenido);
    td.style.color = 'black';
  } else {
    // Si contiene caracteres no alfanuméricos, puedes realizar acciones adicionales
    console.log("Por favor, ingrese solo texto alfanumérico.");
    td.innerText = '';
    td.style.color = 'red';
  }
  
  parametrizacionValoresEvento(namecelda,cont); 
  
}

function validarTextoPais(td, i) {
  // Obtener el contenido actual del TD
  let contenido = td.innerText;
  
  if (/^[a-zA-Z\s]+$/.test(contenido)) {
    // Si es solo texto, puedes realizar acciones adicionales
    console.log("Contenido del TD:", contenido);
    td.style.color = 'black';
    
    parametrizacionValoresEvento("pais_origen",i);
    
  } else {
    // Si contiene números u otros caracteres, puedes realizar acciones adicionales
    console.log("Por favor, ingrese solo texto.");
    td.innerText = '';
    td.style.color = 'red';
    
    parametrizacionValoresEvento("pais_origen",i);
    
  }
  
}

function validarTextoParametrizacion(td,namecelda,cont) {
  // Obtener el contenido actual del TD
  let contenido = td.innerText;
  
  if (/^[a-zA-Z\s]+$/.test(contenido)) {
    // Si es solo texto, puedes realizar acciones adicionales
    console.log("Contenido del TD:", contenido);
    td.style.color = 'black';
  } else {
    // Si contiene números u otros caracteres, puedes realizar acciones adicionales
    console.log("Por favor, ingrese solo texto.");
    td.innerText = '';
    td.style.color = 'red';
  }
  
  parametrizacionValoresEvento(namecelda,cont);
}

function validarTexto(td) {
  // Obtener el contenido actual del TD
  let contenido = td.innerText;
  
  if (/^[a-zA-Z\s]+$/.test(contenido)) {
    // Si es solo texto, puedes realizar acciones adicionales
    console.log("Contenido del TD:", contenido);
    td.style.color = 'black';
  } else {
    // Si contiene números u otros caracteres, puedes realizar acciones adicionales
    console.log("Por favor, ingrese solo texto.");
    td.innerText = '';
    td.style.color = 'red';
  }
}

function formatoNumero(event,i) {
    
      cleanPedimento_r1_1er(i);
      cleanPedimento_r1_2do(i);
      
      // Obtener el código ASCII de la tecla presionada
      var charCode = (event.which) ? event.which : event.keyCode;

      // Validar que solo se permitan números y el carácter de retroceso
      if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode !== 8) {
        event.preventDefault();
        return false;
      }

      // Obtener el contenido actual de la celda
      var valor = event.target.innerText;

      // Filtrar caracteres no numéricos
      var numeroFiltrado = valor.replace(/\D/g, '');

      // Limitar la longitud total a 16 caracteres
      numeroFiltrado = numeroFiltrado.slice(0, 14);

      // Aplicar el formato XX XX XXXX XXXXXXXX
      if (numeroFiltrado.length > 1) {
        numeroFiltrado = numeroFiltrado.replace(/(\d{2})(\d{2})(\d{4})(\d{6})/, '$1 $2 $3 $4');
      }

      // Actualizar el contenido de la celda con el número filtrado
      event.target.innerText = numeroFiltrado.trim();

      // Posicionar el cursor al final del contenido
      var range = document.createRange();
      var selection = window.getSelection();
      range.selectNodeContents(event.target);
      range.collapse(false);
      selection.removeAllRanges();
      selection.addRange(range);

      return true;  
}

function clearFiltres(){
        
       $('#col_referenciaAA').val(null).trigger('change');

       $('#col_evento').val(null).trigger('change'); 
    
       $('#col_responsable').val(null).trigger('change');

       $('#col_finalDestination').val(null).trigger('change');
  
       $('#col_brandDivision').val(null).trigger('change');
  
       $('#col_division').val(null).trigger('change');
  
       $('#col_shipmentId').val(null).trigger('change');
    
       $('#col_container').val(null).trigger('change');
   
       $('#col_blAwbPro').val(null).trigger('change');
    
       $('#col_loadType').val(null).trigger('change');
   
       $('#col_quantity').val(null).trigger('change');
    
       $('#col_pod').val(null).trigger('change');
    
       $('#col_estDepartFromPol').val(null).trigger('change');
    
       $('#col_etaRealPortOfDischarge').val(null).trigger('change');
    
       $('#col_estEtaDc').val(null).trigger('change');
   
       $('#col_inboundNotification').val(null).trigger('change');
    
       $('#col_pol').val(null).trigger('change');
    
       $('#col_aa').val(null).trigger('change');
    
       $('#col_fechaMesVenta').val(null).trigger('change');
    
       $('#col_prioridad').val(null).trigger('change');
    
       $('#col_pais_origen').val(null).trigger('change');
   
       $('#col_size_container').val(null).trigger('change');
    
       $('#col_valor_usd').val(null).trigger('change');
   
       $('#col_eta_port_discharge').val(null).trigger('change');
   
       $('#col_agente_aduanal').val(null).trigger('change');
   
       $('#col_pedimento_a1').val(null).trigger('change');
   
       $('#col_pedimento_r1_1er').val(null).trigger('change');
    
       $('#col_motivo_rectificacion_1er').val(null).trigger('change');
    
       $('#col_pedimento_r1_2do').val(null).trigger('change');
    
       $('#col_motivo_rectificacion_2do').val(null).trigger('change');
    
       $('#col_fecha_recepcion_doc').val(null).trigger('change');
    
       $('#col_recinto').val(null).trigger('change');
    
       $('#col_naviera').val(null).trigger('change');
    
       $('#col_buque').val(null).trigger('change');
   
       $('#col_fecha_revalidacion').val(null).trigger('change');
   
       $('#col_fecha_previo_origen').val(null).trigger('change');
   
       $('#col_fecha_previo_destino').val(null).trigger('change');
   
       $('#col_fecha_resultado_previo').val(null).trigger('change');
   
       $('#col_proforma_final').val(null).trigger('change');
    
       $('#col_permiso').val(null).trigger('change');
    
       $('#col_fecha_envio').val(null).trigger('change');
   
       $('#col_fecha_recepcion_perm').val(null).trigger('change');
   
       $('#col_fecha_activacion_perm').val(null).trigger('change');
   
       $('#col_fecha_permisos_aut').val(null).trigger('change');
   
       $('#col_co_pref_arancelaria').val(null).trigger('change');
   
       $('#col_aplic_pref_arancelaria').val(null).trigger('change');
    
       $('#col_req_uva').val(null).trigger('change');
    
       $('#col_req_ca').val(null).trigger('change');
    
       $('#col_fecha_recepcion_ca').val(null).trigger('change');
    
       $('#col_num_constancia_ca').val(null).trigger('change');
    
       $('#col_monto_ca').val(null).trigger('change');
     
       $('#col_fecha_doc_completos').val(null).trigger('change');
    
       $('#col_fecha_pago_pedimento').val(null).trigger('change');
 
       $('#col_fecha_solicitud_transporte').val(null).trigger('change');
   
       $('#col_fecha_modulacion').val(null).trigger('change');
    
       $('#col_modalidad').val(null).trigger('change');
    
       $('#col_resultado_modulacion').val(null).trigger('change');
    
       $('#col_fecha_reconocimiento').val(null).trigger('change');
   
       $('#col_fecha_liberacion').val(null).trigger('change');
     
       $('#col_sello_origen').val(null).trigger('change');
    
       $('#col_sello_final').val(null).trigger('change');
    
       $('#col_fecha_retencion_aut').val(null).trigger('change');
   
       $('#col_fecha_liberacion_aut').val(null).trigger('change');
 
       $('#col_estatus_operacion').val(null).trigger('change');
  
       $('#col_motivo_atraso').val(null).trigger('change');
    
       $('#col_observaciones').val(null).trigger('change');
 
       $('#col_llegada_a_nova').val(null).trigger('change');
     
       $('#col_llegada_a_globe_trade_sd').val(null).trigger('change');
   
       $('#col_archivo_m').val(null).trigger('change');
    
       $('#col_fecha_archivo_m').val(null).trigger('change');
    
       $('#col_fecha_solicit_manip').val(null).trigger('change');
    
       $('#col_fecha_vencim_manip').val(null).trigger('change');
   
       $('#col_fecha_confirm_clave_pedim').val(null).trigger('change');
     
       $('#col_fecha_recep_increment').val(null).trigger('change');
    
       $('#col_t_e').val(null).trigger('change');
   
       $('#col_fecha_vencim_inbound').val(null).trigger('change');
   
       $('#col_no_bultos').val(null).trigger('change');
    
       $('#col_peso_kg').val(null).trigger('change');
     
       $('#col_transferencia').val(null).trigger('change');
    
       $('#col_fecha_inicio_etiquetado').val(null).trigger('change');
 
       $('#col_fecha_termino_etiquetado').val(null).trigger('change');
  
       $('#col_hora_termino_etiquetado').val(null).trigger('change');
    
       $('#col_proveedor').val(null).trigger('change');
    
       $('#col_proveedor_carga').val(null).trigger('change');
    
       $('#col_fy').val(null).trigger('change');
       
       FiltrerData("0");
       
}


/*
  -Posicionar onFocus al presionar event.key === 'Enter' por celda de forma vertical.
*/
function tabuladorVertical(event, namecelda, contador) {

    let posicion =0;

    // Verificar si la tecla presionada es Enter
    if (event.key === 'Enter') {

        posicion = parseInt(contador)+1;

        // Obtener el id de la celda de abajo
        const nextCellId = namecelda+"[" + posicion + "]";

        console.log("Se obtiene celda siguiente: "+nextCellId);

        // Enfocar la celda de abajo
        const nextCell = document.getElementById(nextCellId);
        if (nextCell) {
            event.preventDefault();  //eliminación del enter
            nextCell.focus();
        }
    }
}

/* -Recorrer lista para tomar valor y asignarlo a las demas filas de forma vertical. */
function parametrizacionValoresEvento(name_celda, contador) {

    let eventoActual = document.getElementById("evento[" + contador + "]").value;
    let valorCeldaActual = document.getElementById(name_celda + "[" + contador + "]").innerHTML;
    let numCustoms = document.getElementById("numCustoms").value;
    let listEventos;

    for (var i = 1, max = numCustoms; i < max; i++) {

        listEventos = document.getElementById("evento[" + i + "]").value;

        if (contador !== i) {
            if (eventoActual === listEventos) {
                document.getElementById(name_celda + "[" + i + "]").innerHTML = valorCeldaActual;
            }
        }
    }

}

function handlePaste(event) {
    // Evitar el comportamiento predeterminado de pegado
    event.preventDefault();

    // Obtener el texto pegado
    const clipboardData = event.clipboardData || window.clipboardData;
    const pastedData = clipboardData.getData('text/plain');

    // Realizar manipulaciones con el texto pegado según tus necesidades
    // Aquí puedes agregar lógica para analizar y controlar la información

    // Ejemplo: Imprimir el texto pegado en la consola
    console.log("Texto pegado:", pastedData);

    // Puedes realizar más manipulaciones aquí según tus requisitos
    // ...

    // Actualizar la celda con el texto manipulado (si es necesario)
    event.target.innerText = pastedData;
}
