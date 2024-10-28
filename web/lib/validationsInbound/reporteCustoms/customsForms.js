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

let cont = 1;

//Parametros Filtros/Encabezados
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
let selected_no_bultos="";
let selected_peso_kg="";
let selected_transferencia="";
let selected_fecha_inicio_etiquetado="";
let selected_fecha_termino_etiquetado="";
let selected_hora_termino_etiquetado="";
let selected_proveedor="";
let selected_proveedor_carga="";
let selected_fy="";

// Generación de Filtros/Tabla (1er vez)
    $(document).ready(async function () {
        
         await consultarCustoms(selected_referenciaAA, 
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
      
    //Función para generación de Filtros/Encabezados & tabla 
    async function consultarCustoms(columna_referenciaAA,
                                    columna_evento,
                                    columna_responsable,
                                    columna_final_destination,
                                    columna_brand_division,
                                    columna_division,
                                    columna_shipmentId,
                                    columna_containerId,
                                    columna_blAwbPro,
                                    columna_loadTypeFinal,
                                    columna_quantity,
                                    columna_pod,
                                    columna_estDepartFromPol,
                                    columna_etaRealPortOfDischarge,
                                    columna_estEtaDc,
                                    columna_inboundNotification,
                                    columna_pol,
                                    columna_aa,
                                    columna_fechaMesVenta,
                                    columna_prioridad,
                                    columna_pais_origen,
                                    columna_size_container,
                                    columna_valor_usd,
                                    columna_eta_port_discharge,
                                    columna_agente_aduanal,
                                    columna_pedimento_a1,
                                    columna_pedimento_r1_1er,
                                    columna_motivo_rectificacion_1er,
                                    columna_pedimento_r1_2do,
                                    columna_motivo_rectificacion_2do,
                                    columna_fecha_recepcion_doc,
                                    columna_recinto,
                                    columna_naviera,
                                    columna_buque,
                                    columna_fecha_revalidacion,
                                    columna_fecha_previo_origen,
                                    columna_fecha_previo_destino,
                                    columna_fecha_resultado_previo,
                                    columna_proforma_final,
                                    columna_permiso,
                                    columna_fecha_envio,
                                    columna_fecha_recepcion_perm,
                                    columna_fecha_activacion_perm,
                                    columna_fecha_permisos_aut,
                                    columna_co_pref_arancelaria,
                                    columna_aplic_pref_arancelaria,
                                    columna_req_uva,
                                    columna_req_ca,
                                    columna_fecha_recepcion_ca,
                                    columna_num_constancia_ca,
                                    columna_monto_ca,
                                    columna_fecha_doc_completos,
                                    columna_fecha_pago_pedimento,
                                    columna_fecha_solicitud_transporte,
                                    columna_fecha_modulacion,
                                    columna_modalidad,
                                    columna_resultado_modulacion,
                                    columna_fecha_reconocimiento,
                                    columna_fecha_liberacion,
                                    columna_sello_origen,
                                    columna_sello_final,
                                    columna_fecha_retencion_aut,
                                    columna_fecha_liberacion_aut,
                                    columna_estatus_operacion,
                                    columna_motivo_atraso,
                                    columna_observaciones,
                                    columna_llegada_a_nova,
                                    columna_llegada_a_globe_trade_sd,
                                    columna_archivo_m,
                                    columna_fecha_archivo_m,
                                    columna_fecha_solicit_manip,
                                    columna_fecha_vencim_manip,
                                    columna_fecha_confirm_clave_pedim,
                                    columna_fecha_recep_increment,
                                    columna_t_e,
                                    columna_fecha_vencim_inbound,
                                    columna_no_bultos,
                                    columna_peso_kg,
                                    columna_transferencia,
                                    columna_fecha_inicio_etiquetado,
                                    columna_fecha_termino_etiquetado,
                                    columna_hora_termino_etiquetado,
                                    columna_proveedor,
                                    columna_proveedor_carga,
                                    columna_fy) {
            
        try {
            var f1 = document.getElementById("f1").value;
            var f2 = document.getElementById("f2").value;
              let urlCustoms = encodeURI("../../ConsultarReporteCustoms?columna_referenciaAA="+columna_referenciaAA
                                        +"&columna_evento="+columna_evento
                                        +"&columna_responsable="+columna_responsable
                                        +"&columna_final_destination="+columna_final_destination
                                        +"&columna_brand_division="+columna_brand_division
                                        +"&columna_division="+columna_division
                                        +"&columna_shipmentId="+columna_shipmentId
                                        +"&columna_containerId="+columna_containerId
                                        +"&columna_blAwbPro="+columna_blAwbPro
                                        +"&columna_loadTypeFinal="+columna_loadTypeFinal
                                        +"&columna_quantity="+columna_quantity
                                        +"&columna_pod="+columna_pod
                                        +"&columna_estDepartFromPol="+columna_estDepartFromPol
                                        +"&columna_etaRealPortOfDischarge="+columna_etaRealPortOfDischarge
                                        +"&columna_estEtaDc="+columna_estEtaDc
                                        +"&columna_inboundNotification="+columna_inboundNotification
                                        +"&columna_pol="+columna_pol
                                        +"&columna_aa="+columna_aa
                                        +"&columna_fechaMesVenta="+columna_fechaMesVenta
                                        +"&columna_prioridad="+columna_prioridad
                                        +"&columna_pais_origen="+columna_pais_origen
                                        +"&columna_size_container="+columna_size_container
                                        +"&columna_valor_usd="+columna_valor_usd
                                        +"&columna_eta_port_discharge="+columna_eta_port_discharge
                                        +"&columna_agente_aduanal="+columna_agente_aduanal
                                        +"&columna_pedimento_a1="+columna_pedimento_a1
                                        +"&columna_pedimento_r1_1er="+columna_pedimento_r1_1er
                                        +"&columna_motivo_rectificacion_1er="+columna_motivo_rectificacion_1er
                                        +"&columna_pedimento_r1_2do="+columna_pedimento_r1_2do
                                        +"&columna_motivo_rectificacion_2do="+columna_motivo_rectificacion_2do
                                        +"&columna_fecha_recepcion_doc="+columna_fecha_recepcion_doc
                                        +"&columna_recinto="+columna_recinto
                                        +"&columna_naviera="+columna_naviera
                                        +"&columna_buque="+columna_buque
                                        +"&columna_fecha_revalidacion="+columna_fecha_revalidacion
                                        +"&columna_fecha_previo_origen="+columna_fecha_previo_origen
                                        +"&columna_fecha_previo_destino="+columna_fecha_previo_destino
                                        +"&columna_fecha_resultado_previo="+columna_fecha_resultado_previo
                                        +"&columna_proforma_final="+columna_proforma_final
                                        +"&columna_permiso="+columna_permiso
                                        +"&columna_fecha_envio="+columna_fecha_envio
                                        +"&columna_fecha_recepcion_perm="+columna_fecha_recepcion_perm
                                        +"&columna_fecha_activacion_perm="+columna_fecha_activacion_perm
                                        +"&columna_fecha_permisos_aut="+columna_fecha_permisos_aut
                                        +"&columna_co_pref_arancelaria="+columna_co_pref_arancelaria
                                        +"&columna_aplic_pref_arancelaria="+columna_aplic_pref_arancelaria
                                        +"&columna_req_uva="+columna_req_uva
                                        +"&columna_req_ca="+columna_req_ca
                                        +"&columna_fecha_recepcion_ca="+columna_fecha_recepcion_ca
                                        +"&columna_num_constancia_ca="+columna_num_constancia_ca
                                        +"&columna_monto_ca="+columna_monto_ca
                                        +"&columna_fecha_doc_completos="+columna_fecha_doc_completos
                                        +"&columna_fecha_pago_pedimento="+columna_fecha_pago_pedimento
                                        +"&columna_fecha_solicitud_transporte="+columna_fecha_solicitud_transporte
                                        +"&columna_fecha_modulacion="+columna_fecha_modulacion
                                        +"&columna_modalidad="+columna_modalidad
                                        +"&columna_resultado_modulacion="+columna_resultado_modulacion
                                        +"&columna_fecha_reconocimiento="+columna_fecha_reconocimiento
                                        +"&columna_fecha_liberacion="+columna_fecha_liberacion
                                        +"&columna_sello_origen="+columna_sello_origen
                                        +"&columna_sello_final="+columna_sello_final
                                        +"&columna_fecha_retencion_aut="+columna_fecha_retencion_aut
                                        +"&columna_fecha_liberacion_aut="+columna_fecha_liberacion_aut
                                        +"&columna_estatus_operacion="+columna_estatus_operacion
                                        +"&columna_motivo_atraso="+columna_motivo_atraso
                                        +"&columna_observaciones="+columna_observaciones
                                        +"&columna_llegada_a_nova="+columna_llegada_a_nova
                                        +"&columna_llegada_a_globe_trade_sd="+columna_llegada_a_globe_trade_sd
                                        +"&columna_archivo_m="+columna_archivo_m
                                        +"&columna_fecha_archivo_m="+columna_fecha_archivo_m
                                        +"&columna_fecha_solicit_manip="+columna_fecha_solicit_manip
                                        +"&columna_fecha_vencim_manip="+columna_fecha_vencim_manip
                                        +"&columna_fecha_confirm_clave_pedim="+columna_fecha_confirm_clave_pedim
                                        +"&columna_fecha_recep_increment="+columna_fecha_recep_increment
                                        +"&columna_t_e="+columna_t_e
                                        +"&columna_fecha_vencim_inbound="+columna_fecha_vencim_inbound
                                        +"&columna_no_bultos="+columna_no_bultos
                                        +"&columna_peso_kg="+columna_peso_kg
                                        +"&columna_transferencia="+columna_transferencia
                                        +"&columna_fecha_inicio_etiquetado="+columna_fecha_inicio_etiquetado
                                        +"&columna_fecha_termino_etiquetado="+columna_fecha_termino_etiquetado
                                        +"&columna_hora_termino_etiquetado="+columna_hora_termino_etiquetado
                                        +"&columna_proveedor="+columna_proveedor
                                        +"&columna_proveedor_carga="+columna_proveedor_carga
                                        +"&columna_fy="+columna_fy
                                        +"&f1="+f1
                                        +"&f2="+f2
                                        );

            const response = await fetch(urlCustoms);
            
            if (!response.ok) {
                throw new Error('Error en la solicitud');
            }

            const data = await response.text();
            document.getElementById('table-scroll').innerHTML = data;
            
            
                        
          } catch (error) {
            console.error(error);
        }

        setTimeout(await ocultarLoader, 2);

    }            
    
async function mostrarLoader() {
    var loaderWrapper = document.getElementById("loader-wrapper");
    loaderWrapper.style.display = "flex";
}

async function ocultarLoader() {
    var loaderWrapper = document.getElementById("loader-wrapper");
    loaderWrapper.style.display = "none";
}

function clearFiltres(){
    location.reload();
}