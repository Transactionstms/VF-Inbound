/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Contadores
let urlCustoms = "";
let contModals = 0;
let AgenteId = "";

//Parametros upload excel
let tipo_elemento = "";
let tipo_clase = "";
let tipo_escritura = "";
let contadorExcel = "";

//Parametros Operación Customs
let evento;
let shipmentId;
let containerId;
let referenciaAA;
let prioridad;
let loadTypeFinal;
let plantillaId;
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
let no_bultos;
let peso_kg;
let transferencia;
let fecha_inicio_etiquetado;
let fecha_termino_etiquetado;
let hora_termino_etiquetado;
let proveedor;
let proveedor_carga;
let fy;
let cont = 1;

//Parametros Filtros/Encabezados
let selected_referenciaAA = "";
let selected_evento = "";
let selected_responsable = "";
let selected_final_destination = "";
let selected_brand_division = "";
let selected_division = "";
let selected_shipmentId = "";
let selected_containerId = "";
let selected_blAwbPro = "";
let selected_loadTypeFinal = "";
let selected_quantity = "";
let selected_pod = "";
let selected_estDepartFromPol = "";
let selected_etaRealPortOfDischarge = "";
let selected_estEtaDc = "";
let selected_inboundNotification = "";
let selected_pol = "";
let selected_aa = "";
let selected_fechaMesVenta = "";
let selected_prioridad = "";
let selected_pais_origen = "";
let selected_size_container = "";
let selected_valor_usd = "";
let selected_eta_port_discharge = "";
let selected_agente_aduanal = "";
let selected_pedimento_a1 = "";
let selected_pedimento_r1_1er = "";
let selected_motivo_rectificacion_1er = "";
let selected_pedimento_r1_2do = "";
let selected_motivo_rectificacion_2do = "";
let selected_fecha_recepcion_doc = "";
let selected_recinto = "";
let selected_naviera = "";
let selected_buque = "";
let selected_fecha_revalidacion = "";
let selected_fecha_previo_origen = "";
let selected_fecha_previo_destino = "";
let selected_fecha_resultado_previo = "";
let selected_proforma_final = "";
let selected_permiso = "";
let selected_fecha_envio = "";
let selected_fecha_recepcion_perm = "";
let selected_fecha_activacion_perm = "";
let selected_fecha_permisos_aut = "";
let selected_co_pref_arancelaria = "";
let selected_aplic_pref_arancelaria = "";
let selected_req_uva = "";
let selected_req_ca = "";
let selected_fecha_recepcion_ca = "";
let selected_num_constancia_ca = "";
let selected_monto_ca = "";
let selected_fecha_doc_completos = "";
let selected_fecha_pago_pedimento = "";
let selected_fecha_solicitud_transporte = "";
let selected_fecha_modulacion = "";
let selected_modalidad = "";
let selected_resultado_modulacion = "";
let selected_fecha_reconocimiento = "";
let selected_fecha_liberacion = "";
let selected_sello_origen = "";
let selected_sello_final = "";
let selected_fecha_retencion_aut = "";
let selected_fecha_liberacion_aut = "";
let selected_estatus_operacion = "";
let selected_motivo_atraso = "";
let selected_observaciones = "";
let selected_llegada_a_nova = "";
let selected_llegada_a_globe_trade_sd = "";
let selected_archivo_m = "";
let selected_fecha_archivo_m = "";
let selected_fecha_solicit_manip = "";
let selected_fecha_vencim_manip = "";
let selected_fecha_confirm_clave_pedim = "";
let selected_fecha_recep_increment = "";
let selected_t_e = "";
let selected_fecha_vencim_inbound = "";
let selected_no_bultos = "";
let selected_peso_kg = "";
let selected_transferencia = "";
let selected_fecha_inicio_etiquetado = "";
let selected_fecha_termino_etiquetado = "";
let selected_hora_termino_etiquetado = "";
let selected_proveedor = "";
let selected_proveedor_carga = "";
let selected_fy = "";
let habilitarBacheoInicial = true;

// Generación de Filtros/Tabla (1er vez)
$(document).ready(async function () {
    await consultarCustomsBacheo(selected_referenciaAA, selected_evento, selected_responsable, selected_final_destination, selected_brand_division, selected_division, selected_shipmentId, selected_containerId, selected_blAwbPro, selected_loadTypeFinal, selected_quantity, selected_pod, selected_estDepartFromPol, selected_etaRealPortOfDischarge, selected_estEtaDc, selected_inboundNotification, selected_pol, selected_aa, selected_fechaMesVenta, selected_prioridad, selected_pais_origen, selected_size_container, selected_valor_usd, selected_eta_port_discharge, selected_agente_aduanal, selected_pedimento_a1, selected_pedimento_r1_1er, selected_motivo_rectificacion_1er, selected_pedimento_r1_2do, selected_motivo_rectificacion_2do, selected_fecha_recepcion_doc, selected_recinto, selected_naviera, selected_buque, selected_fecha_revalidacion, selected_fecha_previo_origen, selected_fecha_previo_destino, selected_fecha_resultado_previo, selected_proforma_final, selected_permiso, selected_fecha_envio, selected_fecha_recepcion_perm, selected_fecha_activacion_perm, selected_fecha_permisos_aut, selected_co_pref_arancelaria, selected_aplic_pref_arancelaria, selected_req_uva, selected_req_ca, selected_fecha_recepcion_ca, selected_num_constancia_ca, selected_monto_ca, selected_fecha_doc_completos, selected_fecha_pago_pedimento, selected_fecha_solicitud_transporte, selected_fecha_modulacion, selected_modalidad, selected_resultado_modulacion, selected_fecha_reconocimiento, selected_fecha_liberacion, selected_sello_origen, selected_sello_final, selected_fecha_retencion_aut, selected_fecha_liberacion_aut, selected_estatus_operacion, selected_motivo_atraso, selected_observaciones, selected_llegada_a_nova, selected_llegada_a_globe_trade_sd, selected_archivo_m, selected_fecha_archivo_m, selected_fecha_solicit_manip, selected_fecha_vencim_manip, selected_fecha_confirm_clave_pedim, selected_fecha_recep_increment, selected_t_e, selected_fecha_vencim_inbound, selected_no_bultos, selected_peso_kg, selected_transferencia, selected_fecha_inicio_etiquetado, selected_fecha_termino_etiquetado, selected_hora_termino_etiquetado, selected_proveedor, selected_proveedor_carga, selected_fy);
});

async function consultarCustomsBacheo(columna_referenciaAA, columna_evento, columna_responsable, columna_final_destination, columna_brand_division, columna_division, columna_shipmentId, columna_containerId, columna_blAwbPro, columna_loadTypeFinal, columna_quantity, columna_pod, columna_estDepartFromPol, columna_etaRealPortOfDischarge, columna_estEtaDc, columna_inboundNotification, columna_pol, columna_aa, columna_fechaMesVenta, columna_prioridad, columna_pais_origen, columna_size_container, columna_valor_usd, columna_eta_port_discharge, columna_agente_aduanal, columna_pedimento_a1, columna_pedimento_r1_1er, columna_motivo_rectificacion_1er, columna_pedimento_r1_2do, columna_motivo_rectificacion_2do, columna_fecha_recepcion_doc, columna_recinto, columna_naviera, columna_buque, columna_fecha_revalidacion, columna_fecha_previo_origen, columna_fecha_previo_destino, columna_fecha_resultado_previo, columna_proforma_final, columna_permiso, columna_fecha_envio, columna_fecha_recepcion_perm, columna_fecha_activacion_perm, columna_fecha_permisos_aut, columna_co_pref_arancelaria, columna_aplic_pref_arancelaria, columna_req_uva, columna_req_ca, columna_fecha_recepcion_ca, columna_num_constancia_ca, columna_monto_ca, columna_fecha_doc_completos, columna_fecha_pago_pedimento, columna_fecha_solicitud_transporte, columna_fecha_modulacion, columna_modalidad, columna_resultado_modulacion, columna_fecha_reconocimiento, columna_fecha_liberacion, columna_sello_origen, columna_sello_final, columna_fecha_retencion_aut, columna_fecha_liberacion_aut, columna_estatus_operacion, columna_motivo_atraso, columna_observaciones, columna_llegada_a_nova, columna_llegada_a_globe_trade_sd, columna_archivo_m, columna_fecha_archivo_m, columna_fecha_solicit_manip, columna_fecha_vencim_manip, columna_fecha_confirm_clave_pedim, columna_fecha_recep_increment, columna_t_e, columna_fecha_vencim_inbound, columna_no_bultos, columna_peso_kg, columna_transferencia, columna_fecha_inicio_etiquetado, columna_fecha_termino_etiquetado, columna_hora_termino_etiquetado, columna_proveedor, columna_proveedor_carga, columna_fy) {

    //Inicializar número total de datos a iterar por lotes de 1000 registros:  
    numBacheo = numBacheo;

    //Indicar el número de pasos por iteración:   
    const paso = 1000;

    try {
        //Iterar Encabezados tabla.
        let urlCustomsEncabezados = encodeURI("../ConsultarFiltrosCustomsBacheo?columna_referenciaAA=" + columna_referenciaAA + "&columna_evento=" + columna_evento + "&columna_responsable=" + columna_responsable + "&columna_final_destination=" + columna_final_destination + "&columna_brand_division=" + columna_brand_division + "&columna_division=" + columna_division + "&columna_shipmentId=" + columna_shipmentId + "&columna_containerId=" + columna_containerId + "&columna_blAwbPro=" + columna_blAwbPro + "&columna_loadTypeFinal=" + columna_loadTypeFinal + "&columna_quantity=" + columna_quantity + "&columna_pod=" + columna_pod + "&columna_estDepartFromPol=" + columna_estDepartFromPol + "&columna_etaRealPortOfDischarge=" + columna_etaRealPortOfDischarge + "&columna_estEtaDc=" + columna_estEtaDc + "&columna_inboundNotification=" + columna_inboundNotification + "&columna_pol=" + columna_pol + "&columna_aa=" + columna_aa + "&columna_fechaMesVenta=" + columna_fechaMesVenta + "&columna_prioridad=" + columna_prioridad + "&columna_pais_origen=" + columna_pais_origen + "&columna_size_container=" + columna_size_container + "&columna_valor_usd=" + columna_valor_usd + "&columna_eta_port_discharge=" + columna_eta_port_discharge + "&columna_agente_aduanal=" + columna_agente_aduanal + "&columna_pedimento_a1=" + columna_pedimento_a1 + "&columna_pedimento_r1_1er=" + columna_pedimento_r1_1er + "&columna_motivo_rectificacion_1er=" + columna_motivo_rectificacion_1er + "&columna_pedimento_r1_2do=" + columna_pedimento_r1_2do + "&columna_motivo_rectificacion_2do=" + columna_motivo_rectificacion_2do + "&columna_fecha_recepcion_doc=" + columna_fecha_recepcion_doc + "&columna_recinto=" + columna_recinto + "&columna_naviera=" + columna_naviera + "&columna_buque=" + columna_buque + "&columna_fecha_revalidacion=" + columna_fecha_revalidacion + "&columna_fecha_previo_origen=" + columna_fecha_previo_origen + "&columna_fecha_previo_destino=" + columna_fecha_previo_destino + "&columna_fecha_resultado_previo=" + columna_fecha_resultado_previo + "&columna_proforma_final=" + columna_proforma_final + "&columna_permiso=" + columna_permiso + "&columna_fecha_envio=" + columna_fecha_envio + "&columna_fecha_recepcion_perm=" + columna_fecha_recepcion_perm + "&columna_fecha_activacion_perm=" + columna_fecha_activacion_perm + "&columna_fecha_permisos_aut=" + columna_fecha_permisos_aut + "&columna_co_pref_arancelaria=" + columna_co_pref_arancelaria + "&columna_aplic_pref_arancelaria=" + columna_aplic_pref_arancelaria + "&columna_req_uva=" + columna_req_uva + "&columna_req_ca=" + columna_req_ca + "&columna_fecha_recepcion_ca=" + columna_fecha_recepcion_ca + "&columna_num_constancia_ca=" + columna_num_constancia_ca + "&columna_monto_ca=" + columna_monto_ca + "&columna_fecha_doc_completos=" + columna_fecha_doc_completos + "&columna_fecha_pago_pedimento=" + columna_fecha_pago_pedimento + "&columna_fecha_solicitud_transporte=" + columna_fecha_solicitud_transporte + "&columna_fecha_modulacion=" + columna_fecha_modulacion + "&columna_modalidad=" + columna_modalidad + "&columna_resultado_modulacion=" + columna_resultado_modulacion + "&columna_fecha_reconocimiento=" + columna_fecha_reconocimiento + "&columna_fecha_liberacion=" + columna_fecha_liberacion + "&columna_sello_origen=" + columna_sello_origen + "&columna_sello_final=" + columna_sello_final + "&columna_fecha_retencion_aut=" + columna_fecha_retencion_aut + "&columna_fecha_liberacion_aut=" + columna_fecha_liberacion_aut + "&columna_estatus_operacion=" + columna_estatus_operacion + "&columna_motivo_atraso=" + columna_motivo_atraso + "&columna_observaciones=" + columna_observaciones + "&columna_llegada_a_nova=" + columna_llegada_a_nova + "&columna_llegada_a_globe_trade_sd=" + columna_llegada_a_globe_trade_sd + "&columna_archivo_m=" + columna_archivo_m + "&columna_fecha_archivo_m=" + columna_fecha_archivo_m + "&columna_fecha_solicit_manip=" + columna_fecha_solicit_manip + "&columna_fecha_vencim_manip=" + columna_fecha_vencim_manip + "&columna_fecha_confirm_clave_pedim=" + columna_fecha_confirm_clave_pedim + "&columna_fecha_recep_increment=" + columna_fecha_recep_increment + "&columna_t_e=" + columna_t_e + "&columna_fecha_vencim_inbound=" + columna_fecha_vencim_inbound + "&columna_no_bultos=" + columna_no_bultos + "&columna_peso_kg=" + columna_peso_kg + "&columna_transferencia=" + columna_transferencia + "&columna_fecha_inicio_etiquetado=" + columna_fecha_inicio_etiquetado + "&columna_fecha_termino_etiquetado=" + columna_fecha_termino_etiquetado + "&columna_hora_termino_etiquetado=" + columna_hora_termino_etiquetado + "&columna_proveedor=" + columna_proveedor + "&columna_proveedor_carga=" + columna_proveedor_carga + "&columna_fy=" + columna_fy + "&contSubfiltros=" + contSubfiltros);
        const response = await fetch(urlCustomsEncabezados);

        if (!response.ok) {
            throw new Error('Error en la solicitud');
        }

        //Mostrar data/encabezados tabla
        const dataEncabezados = await response.text();
        document.getElementById('table-scroll').innerHTML = dataEncabezados;

        //Iterar datos por lotes.
        for (let i = 1; i <= numBacheo; i += paso) {

            let offset = i; //1
            let next = i + paso - 1; //1000.....

            let urlCustomsBodyBatch = encodeURI("../ConsultarCustomsBacheo?columna_referenciaAA=" + columna_referenciaAA + "&columna_evento=" + columna_evento + "&columna_responsable=" + columna_responsable + "&columna_final_destination=" + columna_final_destination + "&columna_brand_division=" + columna_brand_division + "&columna_division=" + columna_division + "&columna_shipmentId=" + columna_shipmentId + "&columna_containerId=" + columna_containerId + "&columna_blAwbPro=" + columna_blAwbPro + "&columna_loadTypeFinal=" + columna_loadTypeFinal + "&columna_quantity=" + columna_quantity + "&columna_pod=" + columna_pod + "&columna_estDepartFromPol=" + columna_estDepartFromPol + "&columna_etaRealPortOfDischarge=" + columna_etaRealPortOfDischarge + "&columna_estEtaDc=" + columna_estEtaDc + "&columna_inboundNotification=" + columna_inboundNotification + "&columna_pol=" + columna_pol + "&columna_aa=" + columna_aa + "&columna_fechaMesVenta=" + columna_fechaMesVenta + "&columna_prioridad=" + columna_prioridad + "&columna_pais_origen=" + columna_pais_origen + "&columna_size_container=" + columna_size_container + "&columna_valor_usd=" + columna_valor_usd + "&columna_eta_port_discharge=" + columna_eta_port_discharge + "&columna_agente_aduanal=" + columna_agente_aduanal + "&columna_pedimento_a1=" + columna_pedimento_a1 + "&columna_pedimento_r1_1er=" + columna_pedimento_r1_1er + "&columna_motivo_rectificacion_1er=" + columna_motivo_rectificacion_1er + "&columna_pedimento_r1_2do=" + columna_pedimento_r1_2do + "&columna_motivo_rectificacion_2do=" + columna_motivo_rectificacion_2do + "&columna_fecha_recepcion_doc=" + columna_fecha_recepcion_doc + "&columna_recinto=" + columna_recinto + "&columna_naviera=" + columna_naviera + "&columna_buque=" + columna_buque + "&columna_fecha_revalidacion=" + columna_fecha_revalidacion + "&columna_fecha_previo_origen=" + columna_fecha_previo_origen + "&columna_fecha_previo_destino=" + columna_fecha_previo_destino + "&columna_fecha_resultado_previo=" + columna_fecha_resultado_previo + "&columna_proforma_final=" + columna_proforma_final + "&columna_permiso=" + columna_permiso + "&columna_fecha_envio=" + columna_fecha_envio + "&columna_fecha_recepcion_perm=" + columna_fecha_recepcion_perm + "&columna_fecha_activacion_perm=" + columna_fecha_activacion_perm + "&columna_fecha_permisos_aut=" + columna_fecha_permisos_aut + "&columna_co_pref_arancelaria=" + columna_co_pref_arancelaria + "&columna_aplic_pref_arancelaria=" + columna_aplic_pref_arancelaria + "&columna_req_uva=" + columna_req_uva + "&columna_req_ca=" + columna_req_ca + "&columna_fecha_recepcion_ca=" + columna_fecha_recepcion_ca + "&columna_num_constancia_ca=" + columna_num_constancia_ca + "&columna_monto_ca=" + columna_monto_ca + "&columna_fecha_doc_completos=" + columna_fecha_doc_completos + "&columna_fecha_pago_pedimento=" + columna_fecha_pago_pedimento + "&columna_fecha_solicitud_transporte=" + columna_fecha_solicitud_transporte + "&columna_fecha_modulacion=" + columna_fecha_modulacion + "&columna_modalidad=" + columna_modalidad + "&columna_resultado_modulacion=" + columna_resultado_modulacion + "&columna_fecha_reconocimiento=" + columna_fecha_reconocimiento + "&columna_fecha_liberacion=" + columna_fecha_liberacion + "&columna_sello_origen=" + columna_sello_origen + "&columna_sello_final=" + columna_sello_final + "&columna_fecha_retencion_aut=" + columna_fecha_retencion_aut + "&columna_fecha_liberacion_aut=" + columna_fecha_liberacion_aut + "&columna_estatus_operacion=" + columna_estatus_operacion + "&columna_motivo_atraso=" + columna_motivo_atraso + "&columna_observaciones=" + columna_observaciones + "&columna_llegada_a_nova=" + columna_llegada_a_nova + "&columna_llegada_a_globe_trade_sd=" + columna_llegada_a_globe_trade_sd + "&columna_archivo_m=" + columna_archivo_m + "&columna_fecha_archivo_m=" + columna_fecha_archivo_m + "&columna_fecha_solicit_manip=" + columna_fecha_solicit_manip + "&columna_fecha_vencim_manip=" + columna_fecha_vencim_manip + "&columna_fecha_confirm_clave_pedim=" + columna_fecha_confirm_clave_pedim + "&columna_fecha_recep_increment=" + columna_fecha_recep_increment + "&columna_t_e=" + columna_t_e + "&columna_fecha_vencim_inbound=" + columna_fecha_vencim_inbound + "&columna_no_bultos=" + columna_no_bultos + "&columna_peso_kg=" + columna_peso_kg + "&columna_transferencia=" + columna_transferencia + "&columna_fecha_inicio_etiquetado=" + columna_fecha_inicio_etiquetado + "&columna_fecha_termino_etiquetado=" + columna_fecha_termino_etiquetado + "&columna_hora_termino_etiquetado=" + columna_hora_termino_etiquetado + "&columna_proveedor=" + columna_proveedor + "&columna_proveedor_carga=" + columna_proveedor_carga + "&columna_fy=" + columna_fy + "&contSubfiltros=" + contSubfiltros + "&offset=" + offset + "&next=" + next);
            const response = await fetch(urlCustomsBodyBatch);

            if (!response.ok) {
                throw new Error('Error en la solicitud');
            }

            const dataBodyBatch = await response.text();
            document.getElementById('data_customs').innerHTML += dataBodyBatch;

            setTimeout(await ocultarLoader, 2);
        }

        //Habilitar controles de para descarga/subida de plantilla:
        document.getElementById("input-id").disabled = false;
        document.getElementById("upload_file").disabled = false;
        document.getElementById("created_file").disabled = false;

    } catch (error) {
        console.error(error);
    }
}

async function consultarCustomsFiltros(columna_referenciaAA, columna_evento, columna_responsable, columna_final_destination, columna_brand_division, columna_division, columna_shipmentId, columna_containerId, columna_blAwbPro, columna_loadTypeFinal, columna_quantity, columna_pod, columna_estDepartFromPol, columna_etaRealPortOfDischarge, columna_estEtaDc, columna_inboundNotification, columna_pol, columna_aa, columna_fechaMesVenta, columna_prioridad, columna_pais_origen, columna_size_container, columna_valor_usd, columna_eta_port_discharge, columna_agente_aduanal, columna_pedimento_a1, columna_pedimento_r1_1er, columna_motivo_rectificacion_1er, columna_pedimento_r1_2do, columna_motivo_rectificacion_2do, columna_fecha_recepcion_doc, columna_recinto, columna_naviera, columna_buque, columna_fecha_revalidacion, columna_fecha_previo_origen, columna_fecha_previo_destino, columna_fecha_resultado_previo, columna_proforma_final, columna_permiso, columna_fecha_envio, columna_fecha_recepcion_perm, columna_fecha_activacion_perm, columna_fecha_permisos_aut, columna_co_pref_arancelaria, columna_aplic_pref_arancelaria, columna_req_uva, columna_req_ca, columna_fecha_recepcion_ca, columna_num_constancia_ca, columna_monto_ca, columna_fecha_doc_completos, columna_fecha_pago_pedimento, columna_fecha_solicitud_transporte, columna_fecha_modulacion, columna_modalidad, columna_resultado_modulacion, columna_fecha_reconocimiento, columna_fecha_liberacion, columna_sello_origen, columna_sello_final, columna_fecha_retencion_aut, columna_fecha_liberacion_aut, columna_estatus_operacion, columna_motivo_atraso, columna_observaciones, columna_llegada_a_nova, columna_llegada_a_globe_trade_sd, columna_archivo_m, columna_fecha_archivo_m, columna_fecha_solicit_manip, columna_fecha_vencim_manip, columna_fecha_confirm_clave_pedim, columna_fecha_recep_increment, columna_t_e, columna_fecha_vencim_inbound, columna_no_bultos, columna_peso_kg, columna_transferencia, columna_fecha_inicio_etiquetado, columna_fecha_termino_etiquetado, columna_hora_termino_etiquetado, columna_proveedor, columna_proveedor_carga, columna_fy) {
    try {

        //Inicializar bloqueo de controles para descarga/subida de plantilla:
        document.getElementById("input-id").disabled = true;
        document.getElementById("upload_file").disabled = true;
        document.getElementById("created_file").disabled = true;

        let urlCustomsFiltros = encodeURI("../ConsultarCustomsBacheo?columna_referenciaAA=" + columna_referenciaAA + "&columna_evento=" + columna_evento + "&columna_responsable=" + columna_responsable + "&columna_final_destination=" + columna_final_destination + "&columna_brand_division=" + columna_brand_division + "&columna_division=" + columna_division + "&columna_shipmentId=" + columna_shipmentId + "&columna_containerId=" + columna_containerId + "&columna_blAwbPro=" + columna_blAwbPro + "&columna_loadTypeFinal=" + columna_loadTypeFinal + "&columna_quantity=" + columna_quantity + "&columna_pod=" + columna_pod + "&columna_estDepartFromPol=" + columna_estDepartFromPol + "&columna_etaRealPortOfDischarge=" + columna_etaRealPortOfDischarge + "&columna_estEtaDc=" + columna_estEtaDc + "&columna_inboundNotification=" + columna_inboundNotification + "&columna_pol=" + columna_pol + "&columna_aa=" + columna_aa + "&columna_fechaMesVenta=" + columna_fechaMesVenta + "&columna_prioridad=" + columna_prioridad + "&columna_pais_origen=" + columna_pais_origen + "&columna_size_container=" + columna_size_container + "&columna_valor_usd=" + columna_valor_usd + "&columna_eta_port_discharge=" + columna_eta_port_discharge + "&columna_agente_aduanal=" + columna_agente_aduanal + "&columna_pedimento_a1=" + columna_pedimento_a1 + "&columna_pedimento_r1_1er=" + columna_pedimento_r1_1er + "&columna_motivo_rectificacion_1er=" + columna_motivo_rectificacion_1er + "&columna_pedimento_r1_2do=" + columna_pedimento_r1_2do + "&columna_motivo_rectificacion_2do=" + columna_motivo_rectificacion_2do + "&columna_fecha_recepcion_doc=" + columna_fecha_recepcion_doc + "&columna_recinto=" + columna_recinto + "&columna_naviera=" + columna_naviera + "&columna_buque=" + columna_buque + "&columna_fecha_revalidacion=" + columna_fecha_revalidacion + "&columna_fecha_previo_origen=" + columna_fecha_previo_origen + "&columna_fecha_previo_destino=" + columna_fecha_previo_destino + "&columna_fecha_resultado_previo=" + columna_fecha_resultado_previo + "&columna_proforma_final=" + columna_proforma_final + "&columna_permiso=" + columna_permiso + "&columna_fecha_envio=" + columna_fecha_envio + "&columna_fecha_recepcion_perm=" + columna_fecha_recepcion_perm + "&columna_fecha_activacion_perm=" + columna_fecha_activacion_perm + "&columna_fecha_permisos_aut=" + columna_fecha_permisos_aut + "&columna_co_pref_arancelaria=" + columna_co_pref_arancelaria + "&columna_aplic_pref_arancelaria=" + columna_aplic_pref_arancelaria + "&columna_req_uva=" + columna_req_uva + "&columna_req_ca=" + columna_req_ca + "&columna_fecha_recepcion_ca=" + columna_fecha_recepcion_ca + "&columna_num_constancia_ca=" + columna_num_constancia_ca + "&columna_monto_ca=" + columna_monto_ca + "&columna_fecha_doc_completos=" + columna_fecha_doc_completos + "&columna_fecha_pago_pedimento=" + columna_fecha_pago_pedimento + "&columna_fecha_solicitud_transporte=" + columna_fecha_solicitud_transporte + "&columna_fecha_modulacion=" + columna_fecha_modulacion + "&columna_modalidad=" + columna_modalidad + "&columna_resultado_modulacion=" + columna_resultado_modulacion + "&columna_fecha_reconocimiento=" + columna_fecha_reconocimiento + "&columna_fecha_liberacion=" + columna_fecha_liberacion + "&columna_sello_origen=" + columna_sello_origen + "&columna_sello_final=" + columna_sello_final + "&columna_fecha_retencion_aut=" + columna_fecha_retencion_aut + "&columna_fecha_liberacion_aut=" + columna_fecha_liberacion_aut + "&columna_estatus_operacion=" + columna_estatus_operacion + "&columna_motivo_atraso=" + columna_motivo_atraso + "&columna_observaciones=" + columna_observaciones + "&columna_llegada_a_nova=" + columna_llegada_a_nova + "&columna_llegada_a_globe_trade_sd=" + columna_llegada_a_globe_trade_sd + "&columna_archivo_m=" + columna_archivo_m + "&columna_fecha_archivo_m=" + columna_fecha_archivo_m + "&columna_fecha_solicit_manip=" + columna_fecha_solicit_manip + "&columna_fecha_vencim_manip=" + columna_fecha_vencim_manip + "&columna_fecha_confirm_clave_pedim=" + columna_fecha_confirm_clave_pedim + "&columna_fecha_recep_increment=" + columna_fecha_recep_increment + "&columna_t_e=" + columna_t_e + "&columna_fecha_vencim_inbound=" + columna_fecha_vencim_inbound + "&columna_no_bultos=" + columna_no_bultos + "&columna_peso_kg=" + columna_peso_kg + "&columna_transferencia=" + columna_transferencia + "&columna_fecha_inicio_etiquetado=" + columna_fecha_inicio_etiquetado + "&columna_fecha_termino_etiquetado=" + columna_fecha_termino_etiquetado + "&columna_hora_termino_etiquetado=" + columna_hora_termino_etiquetado + "&columna_proveedor=" + columna_proveedor + "&columna_proveedor_carga=" + columna_proveedor_carga + "&columna_fy=" + columna_fy + "&contSubfiltros=" + contSubfiltros + "&offset=0" + "&next=0");
        const response = await fetch(urlCustomsFiltros);

        if (!response.ok) {
            throw new Error('Error en la solicitud');
        }

        const dataBodyFiltrers = await response.text();
        document.getElementById('data_customs').innerHTML = dataBodyFiltrers;

        setTimeout(await ocultarLoader, 2);

        //Habilitar controles de para descarga/subida de plantilla:
        document.getElementById("input-id").disabled = false;
        document.getElementById("upload_file").disabled = false;
        document.getElementById("created_file").disabled = false;

    } catch (error) {
        console.error(error);
    }
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
    let fechaMayorActual = "";

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

        if (idAgenteAduanal === "4002" || idAgenteAduanal === "4006") { //CUSA Y VF

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

        if (idAgenteAduanal === "4002" || idAgenteAduanal === "4006") { //CUSA Y VF

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
            var f1 = new Date(fecha_liberacion);
            var f2 = new Date(fechaActual);

            if (f1 > f2) {
                fechaMayorActual = "";
            } else if (f1 <= f2) {
                fechaMayorActual = "-";
            } else {
                fechaMayorActual = "";
            }

            if (fechaMayorActual.replace(/\s/g, "") == "" || pedimento_a1.replace(/\s/g, "") == "" || fecha_pago_pedimento.replace(/\s/g, "") == "") {
                contadorError++;
                txtErrormSg += "Verifique: Pedimento A1 / Fecha Pago Pedimento / Fecha Liberación.";
                changeColorByPositionError(i);
                msgErrorAgenteAduanal(i, idAgenteAduanal);
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

            if (data === "0") { //Activación Semaforo
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
    let fechaMayorActual = "";

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

    if (idAgenteAduanal === "4002" || idAgenteAduanal === "4006") { //CUSA Y VF

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

    if (idAgenteAduanal === "4002" || idAgenteAduanal === "4006") { //CUSA Y VF

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
        var f1 = new Date(fecha_liberacion);
        var f2 = new Date(fechaActual);

        if (f1 > f2) {
            fechaMayorActual = "";
        } else if (f1 <= f2) {
            fechaMayorActual = "-";
        } else {
            fechaMayorActual = "";
        }

        if (fechaMayorActual.replace(/\s/g, "") == "" || pedimento_a1.replace(/\s/g, "") == "" || fecha_pago_pedimento.replace(/\s/g, "") == "") {
            contadorError++;
            txtErrormSg += "Verifique: Pedimento A1 / Fecha Pago Pedimento / Fecha Liberación.";
            changeColorByPositionError(i);
            msgErrorAgenteAduanal(i, idAgenteAduanal);
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

        if (data === "0") { //Activación Semaforo
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
    let pedimento_a1_imp = document.getElementById("pedimento_a1[" + i + "]").innerHTML;
    let fecha_pago_pedimento_imp = document.getElementById("fecha_pago_pedimento[" + i + "]").innerHTML;
    let fecha_liberacion_imp = document.getElementById("fecha_liberacion[" + i + "]").innerHTML;

    let disabledOption = "";
    let fechaMayorActual = "";

    var f1 = new Date(fecha_liberacion_imp);
    var f2 = new Date(fechaActual);

    if (f1 > f2) {
        fechaMayorActual = "";
    } else if (f1 <= f2) {
        fechaMayorActual = "-";
    } else {
        fechaMayorActual = "";
    }

    if (fechaMayorActual.replace(/\s/g, "") == "" || pedimento_a1_imp.replace(/\s/g, "") == "" || fecha_pago_pedimento_imp.replace(/\s/g, "") == "") {
        disabledOption = "true";
    } else {
        disabledOption = "";
    }

    var selector = document.getElementById("estatus_operacion[" + i + "]");
    selector.options[19].disabled = disabledOption;

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

function tabuladorVertical(event, namecelda, contador) {

    let posicion = 0;

    // Verificar si la tecla presionada es Enter
    if (event.key === 'Enter') {

        posicion = parseInt(contador) + 1;

        // Obtener el id de la celda de abajo
        const nextCellId = namecelda + "[" + posicion + "]";

        console.log("Se obtiene celda siguiente: " + nextCellId);

        // Enfocar la celda de abajo
        const nextCell = document.getElementById(nextCellId);
        if (nextCell) {
            event.preventDefault(); //eliminación del enter
            nextCell.focus();
        } else {
            event.preventDefault(); //eliminación del enter

        }
    }
}

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

function parametrizacionValoresEventoInput(name_celda, contador) {

    let eventoActual = document.getElementById("evento[" + contador + "]").value;
    let valorCeldaActual = document.getElementById(name_celda + "[" + contador + "]").value;
    let numCustoms = document.getElementById("numCustoms").value;
    let listEventos;

    for (var i = 1, max = numCustoms; i < max; i++) {

        listEventos = document.getElementById("evento[" + i + "]").value;

        if (contador !== i) {
            if (eventoActual === listEventos) {
                document.getElementById(name_celda + "[" + i + "]").value = valorCeldaActual;
            }
        }
    }

}

function validarTextoAlfanumericoObservaciones(td, namecelda, cont) {
    // Obtener el contenido actual del TD
    let contenido = td.innerText;

    if (/^[a-zA-Z0-9\s./-]+$/.test(contenido)) {
        // Si es alfanumérico, puedes realizar acciones adicionales
        console.log("Contenido del TD:", contenido);
        td.style.color = 'black';
    } else {
        // Si contiene caracteres no alfanuméricos, puedes realizar acciones adicionales
        console.log("Por favor, ingrese solo texto alfanumérico.");
        td.innerText = '';
        td.style.color = 'red';
    }

    parametrizacionValoresEvento(namecelda, cont);

}

function parametrizacionValoresEstatusOperacion(contador) {

    let eventoActual = document.getElementById("evento[" + contador + "]").value;
    let valorCeldaActual = document.getElementById("estatus_operacion[" + contador + "]").value;
    let numCustoms = document.getElementById("numCustoms").value;
    let listEventos;

    for (var i = 1, max = numCustoms; i < max; i++) {

        listEventos = document.getElementById("evento[" + i + "]").value;

        if (contador !== i) {
            if (eventoActual === listEventos) {
                document.getElementById("estatus_operacion[" + i + "]").value = valorCeldaActual;
            }
        }
    }

}

async function leerArchivo() {

    console.log("Lectura de archivo excel");

    var fileInput = document.getElementById('input-id');

    var archivo = fileInput.files[0];
    var lector = new FileReader();

    lector.onload = function (e) {
        var contenido = e.target.result;
        var workbook = XLSX.read(contenido, {
            type: 'array'
        });

        var primeraHoja = workbook.SheetNames[0];
        var hoja = workbook.Sheets[primeraHoja];

        var datos = XLSX.utils.sheet_to_json(hoja, {
            header: 1
        });

        // Itera sobre las filas del Excel
        for (var i = 2; i < datos.length; i++) {

            // Agrega celdas a la nueva fila
            for (var k = 0; k < datos[i].length; k++) {

                valor = datos[i][k];
                fila = parseInt([i]) + 1;
                //console.log("Núm Celda:" + k + " - Fila: " + [i] + " -  Valor: " + valor);

                if (valor === undefined || valor === null) {
                    valor = ""; // Puedes asignar otro valor predeterminado si lo deseas
                }

                if (k === 10 || k === 11 || k === 13) {
                    if (valor.replace(/\s/g, "") !== "") {
                        valor = valor;
                    } else {
                        valor = "00 00 0000 0000000";
                    }
                }

                if (idAgenteAduanal === "4006") { //ADMINISTRADOR
                    validar += relacion_columnas_excel_administrador(k, valor, fila);
                }

                if (idAgenteAduanal === "4001") { //LOGIX
                    validar += relacion_columnas_excel_logix(k, valor, fila);
                }

                if (idAgenteAduanal === "4002") { //CUSA
                    validar += relacion_columnas_excel_cusa(k, valor, fila);
                }

                if (idAgenteAduanal !== "4001" && idAgenteAduanal !== "4002" && idAgenteAduanal !== "4006") { //GENERICO 
                    validar += relacion_columnas_excel_generico(k, valor, fila);
                }

            }

        }

        if (validar === "") {
            //Realizar la lectura del archivo .excel
            readExcelFile();
        } else {
            //Mostrar listado de errores como notificación
            swal(validar, "warning");
            ocultarLoader();
            return false;
        }

    }

    lector.readAsArrayBuffer(archivo);
}

var hasReadFile = false; // Variable para controlar la ejecución de la función

async function readExcelFile() {
    if (hasReadFile) {
        console.log("El archivo ya ha sido leído.");
        return; // Si ya se ha leído el archivo, salir de la función
    }

    var fileInput = document.getElementById('input-id');
    var file = fileInput.files[0];
    if (file) {
        hasReadFile = true; // Marcar como ejecutado
        var reader = new FileReader();
        reader.onload = async function (e) {
            var data = new Uint8Array(e.target.result);
            var workbook = XLSX.read(data, {
                type: 'array'
            });

            // Obtenemos la primera hoja del archivo Excel
            var firstSheet = workbook.Sheets[workbook.SheetNames[0]];
            var range = XLSX.utils.decode_range(firstSheet['!ref']);

            // Iterar sobre cada fila, empezando desde la tercera fila (índice 2)
            for (var rowNum = 2; rowNum <= range.e.r; rowNum++) {
                var concatenatedRowValues = ""; // Reiniciar la variable para cada fila
                var rowValues = [];

                for (var colNum = range.s.c; colNum <= range.e.c; colNum++) {
                    var cellAddress = {c: colNum, r: rowNum};
                    var cellRef = XLSX.utils.encode_cell(cellAddress);
                    var cell = firstSheet[cellRef];
                    var cellValue = (cell ? cell.v : ""); // Asignar "NULL" si la celda está vacía

                    rowValues.push(cellValue);
                    concatenatedRowValues += cellValue + (colNum < range.e.c ? "," : ""); // Concatenar valores con coma
                }

                mostrarOcultarDiv('divAMostrarOcultar', true);
                document.getElementById("statusResultado").innerHTML = "Cargando Información......";

                // Enviar el valor concatenado a la función fetch  
                let res = async enviarValoresFila(concatenatedRowValues);

               console.log("Estatus Servlet: "+res);
    
                if (res) {

                    if (rowValues.length > 1) {    // Mostrar el segundo valor de la fila si existe
                        document.getElementById("divResultado").innerHTML += "<B style=\"color: green;\">✔ El shipment id : || " + rowValues[1] + " || se encuentra registrado en sistema. </B><br>";
                    }

                } else {
                    document.getElementById("divResultado").innerHTML += "<B style=\"color: red;\">! El shipment id : ||" + rowValues[1] + "|| no interactuó en sistema, verifique información¡</B><br>";
                }

            }

            // Mostrar alerta al finalizar la lectura del archivo
            document.getElementById("statusResultado").innerHTML = "Lectura del archivo Excel completada.";
            
        };
        reader.readAsArrayBuffer(file);
    } else {
        alert("Por favor, seleccione un archivo Excel primero.");
    }
}

async function enviarValoresFila(concatenatedRowValues) {

    let agenteAduanal = document.getElementById("idAgenteAduanal").value;
    let url = "../UpdatePlantillaCustoms?idAgenteAduanal=" + agenteAduanal + "&valores_celdas=" + concatenatedRowValues;
    let res = "";

    console.log(url);

    fetch(url, {
        method: 'POST'
    }).then(r => r.text())
            .then(data => {
                if (data) {
                    res = data;
                } else {
                    res = data;
                }

            }).catch(error => console.log(error));
    return res;
}

function clearFiltres() {
    location.reload();
}