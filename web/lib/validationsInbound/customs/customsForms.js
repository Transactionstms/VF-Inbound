/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Contadores
let urlCustoms = "";

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

    async function consultarCustoms(idAgenteAduanal,filterType,caramelo) {
        
            try {
                const response = await fetch("../ConsultarCustoms?AgentType=" + idAgenteAduanal + "&filterType="+ filterType + "&id=" + caramelo);
                if (!response.ok) {
                    throw new Error('Error en la solicitud');
                }
                
                await loadCss();
                await loadJsPicker();
                
                const data = await response.text();
                document.getElementById('AddTableDetalleCustom').innerHTML = data;
                await ocultarLoader(); 
                    
            } catch (error) {
                console.error(error);
            }        
       
    }

    function FiltrerData(tipoFiltro) {

        let valueJsp = "";
        let selectedValues = "0";
        var selected = "";

        if (tipoFiltro === "0") {
            selectedValues = "0";
        } else if (tipoFiltro === "1") {
            selectedValues = document.getElementById('col_referenciaAA');
        } else if (tipoFiltro === "2") {
            selectedValues = document.getElementById('col_evento');
        } else if (tipoFiltro === "3") {
            selectedValues = document.getElementById('col_responsable');
        } else if (tipoFiltro === "4") {
            selectedValues = document.getElementById('col_finalDestination');
        } else if (tipoFiltro === "5") {
            selectedValues = document.getElementById('col_brandDivision');
        } else if (tipoFiltro === "6") {
            selectedValues = document.getElementById('col_division');
        } else if (tipoFiltro === "7") {
            selectedValues = document.getElementById('col_shipmentId');
        } else if (tipoFiltro === "8") {
            selectedValues = document.getElementById('col_container');
        } else if (tipoFiltro === "9") {
            selectedValues = document.getElementById('col_blAwbPro');
        } else if (tipoFiltro === "10") {
            selectedValues = document.getElementById('col_loadType');
        } else if (tipoFiltro === "11") {
            selectedValues = document.getElementById('col_quantity');
        } else if (tipoFiltro === "12") {
            selectedValues = document.getElementById('col_pod');
        } else if (tipoFiltro === "13") {
            selectedValues = document.getElementById('col_estDepartFromPol');
        } else if (tipoFiltro === "14") {
            selectedValues = document.getElementById('col_etaRealPortOfDischarge');
        } else if (tipoFiltro === "15") {
            selectedValues = document.getElementById('col_estEtaDc');
        } else if (tipoFiltro === "16") {
            selectedValues = document.getElementById('col_inboundNotification');
        } else if (tipoFiltro === "17") {
            selectedValues = document.getElementById('col_pol');
        } else if (tipoFiltro === "18") {
            selectedValues = document.getElementById('col_aa');
        } else if (tipoFiltro === "19") {
            selectedValues = document.getElementById('col_fechaMesVenta');
        } else if (tipoFiltro === "20") {
            selectedValues = document.getElementById('col_prioridad');
        } else if (tipoFiltro === "21") {
            selectedValues = document.getElementById('col_pais_origen');
        } else if (tipoFiltro === "22") {
            selectedValues = document.getElementById('col_size_container');
        } else if (tipoFiltro === "23") {
            selectedValues = document.getElementById('col_valor_usd');
        } else if (tipoFiltro === "24") {
            selectedValues = document.getElementById('col_eta_port_discharge');
        } else if (tipoFiltro === "25") {
            selectedValues = document.getElementById('col_agente_aduanal');
        } else if (tipoFiltro === "26") {
            selectedValues = document.getElementById('col_pedimento_a1');
        } else if (tipoFiltro === "27") {
            selectedValues = document.getElementById('col_pedimento_r1_1er');
        } else if (tipoFiltro === "28") {
            selectedValues = document.getElementById('col_motivo_rectificacion_1er');
        } else if (tipoFiltro === "29") {
            selectedValues = document.getElementById('col_pedimento_r1_2do');
        } else if (tipoFiltro === "30") {
            selectedValues = document.getElementById('col_motivo_rectificacion_2do');
        } else if (tipoFiltro === "31") {
            selectedValues = document.getElementById('col_fecha_recepcion_doc');
        } else if (tipoFiltro === "32") {
            selectedValues = document.getElementById('col_recinto');
        } else if (tipoFiltro === "33") {
            selectedValues = document.getElementById('col_naviera');
        } else if (tipoFiltro === "34") {
            selectedValues = document.getElementById('col_buque');
        } else if (tipoFiltro === "35") {
            selectedValues = document.getElementById('col_fecha_revalidacion');
        } else if (tipoFiltro === "36") {
            selectedValues = document.getElementById('col_fecha_previo_origen');
        } else if (tipoFiltro === "37") {
            selectedValues = document.getElementById('col_fecha_previo_destino');
        } else if (tipoFiltro === "38") {
            selectedValues = document.getElementById('col_fecha_resultado_previo');
        } else if (tipoFiltro === "39") {
            selectedValues = document.getElementById('col_proforma_final');
        } else if (tipoFiltro === "40") {
            selectedValues = document.getElementById('col_permiso');
        } else if (tipoFiltro === "41") {
            selectedValues = document.getElementById('col_fecha_envio');
        } else if (tipoFiltro === "42") {
            selectedValues = document.getElementById('col_fecha_recepcion_perm');
        } else if (tipoFiltro === "43") {
            selectedValues = document.getElementById('col_fecha_activacion_perm');
        } else if (tipoFiltro === "44") {
            selectedValues = document.getElementById('col_fecha_permisos_aut');
        } else if (tipoFiltro === "45") {
            selectedValues = document.getElementById('col_co_pref_arancelaria');
        } else if (tipoFiltro === "46") {
            selectedValues = document.getElementById('col_aplic_pref_arancelaria');
        } else if (tipoFiltro === "47") {
            selectedValues = document.getElementById('col_req_uva');
        } else if (tipoFiltro === "48") {
            selectedValues = document.getElementById('col_req_ca');
        } else if (tipoFiltro === "49") {
            selectedValues = document.getElementById('col_fecha_recepcion_ca');
        } else if (tipoFiltro === "50") {
            selectedValues = document.getElementById('col_num_constancia_ca');
        } else if (tipoFiltro === "51") {
            selectedValues = document.getElementById('col_monto_ca');
        } else if (tipoFiltro === "52") {
            selectedValues = document.getElementById('col_fecha_doc_completos');
        } else if (tipoFiltro === "53") {
            selectedValues = document.getElementById('col_fecha_pago_pedimento');
        } else if (tipoFiltro === "54") {
            selectedValues = document.getElementById('col_fecha_solicitud_transporte');
        } else if (tipoFiltro === "55") {
            selectedValues = document.getElementById('col_fecha_modulacion');
        } else if (tipoFiltro === "56") {
            selectedValues = document.getElementById('col_modalidad');
        } else if (tipoFiltro === "57") {
            selectedValues = document.getElementById('col_resultado_modulacion');
        } else if (tipoFiltro === "58") {
            selectedValues = document.getElementById('col_fecha_reconocimiento');
        } else if (tipoFiltro === "59") {
            selectedValues = document.getElementById('col_fecha_liberacion');
        } else if (tipoFiltro === "60") {
            selectedValues = document.getElementById('col_sello_origen');
        } else if (tipoFiltro === "61") {
            selectedValues = document.getElementById('col_sello_final');
        } else if (tipoFiltro === "62") {
            selectedValues = document.getElementById('col_fecha_retencion_aut');
        } else if (tipoFiltro === "63") {
            selectedValues = document.getElementById('col_fecha_liberacion_aut');
        } else if (tipoFiltro === "64") {
            selectedValues = document.getElementById('col_estatus_operacion');
        } else if (tipoFiltro === "65") {
            selectedValues = document.getElementById('col_motivo_atraso');
        } else if (tipoFiltro === "66") {
            selectedValues = document.getElementById('col_observaciones');
        } else if (tipoFiltro === "67") {
            selectedValues = document.getElementById('col_llegada_a_nova');
        } else if (tipoFiltro === "68") {
            selectedValues = document.getElementById('col_llegada_a_globe_trade_sd');
        } else if (tipoFiltro === "69") {
            selectedValues = document.getElementById('col_archivo_m');
        } else if (tipoFiltro === "70") {
            selectedValues = document.getElementById('col_fecha_archivo_m');
        } else if (tipoFiltro === "71") {
            selectedValues = document.getElementById('col_fecha_solicit_manip');
        } else if (tipoFiltro === "72") {
            selectedValues = document.getElementById('col_fecha_vencim_manip');
        } else if (tipoFiltro === "73") {
            selectedValues = document.getElementById('col_fecha_confirm_clave_pedim');
        } else if (tipoFiltro === "74") {
            selectedValues = document.getElementById('col_fecha_recep_increment');
        } else if (tipoFiltro === "75") {
            selectedValues = document.getElementById('col_t_e');
        } else if (tipoFiltro === "76") {
            selectedValues = document.getElementById('col_fecha_vencim_inbound');
        } else if (tipoFiltro === "77") {
            selectedValues = document.getElementById('col_no_bultos');
        } else if (tipoFiltro === "78") {
            selectedValues = document.getElementById('col_peso_kg');
        } else if (tipoFiltro === "79") {
            selectedValues = document.getElementById('col_transferencia');
        } else if (tipoFiltro === "80") {
            selectedValues = document.getElementById('col_fecha_inicio_etiquetado');
        } else if (tipoFiltro === "81") {
            selectedValues = document.getElementById('col_fecha_termino_etiquetado');
        } else if (tipoFiltro === "82") {
            selectedValues = document.getElementById('col_hora_termino_etiquetado');
        } else if (tipoFiltro === "83") {
            selectedValues = document.getElementById('col_proveedor');
        } else if (tipoFiltro === "84") {
            selectedValues = document.getElementById('col_proveedor_carga');
        } else if (tipoFiltro === "85") {
            selectedValues = document.getElementById('col_fy');
        }

        if (tipoFiltro === "0") {
            selected = "0";
        } else {
            selected = [...selectedValues.options].filter(option => option.selected).map(option => option.value);
        }

        valueJsp = tipoFiltro + "@" + selected;

        // Obtener una referencia al objeto window de la página principal
        var ventanaPrincipal = window.parent;

        // Enviar el parámetro al objeto window de la página principal
        ventanaPrincipal.postMessage(valueJsp, "*");

    }

    async function AddPullCustoms() {

        jsShowWindowLoad('Cargando Información');

        let idAgenteAduanal = document.getElementById("idAgenteAduanal").value;
        let contadorCustoms = document.getElementById("numCustoms").value;
        let contadorError = 0;
        let txtErrormSg = "";

        for (let i = 1; i < contadorCustoms; i++) {

            //Parametros Indicadores         

            let temp4 = "referenciaAA[" + i + "]";
            let temp1 = "evento[" + i + "]";
            let temp2 = "shipmentId[" + i + "]";
            let temp3 = "containerId[" + i + "]";
            let temp5 = "prioridad[" + i + "]";
            let temp6 = "loadTypeFinal[" + i + "]";
            let temp6_1 = "plantillaId[" + i + "]";

            referenciaAA = document.getElementById(temp4).value;
            evento = document.getElementById(temp1).value;
            shipmentId = document.getElementById(temp2).value;
            containerId = document.getElementById(temp3).value;
            prioridad = document.getElementById(temp5).value;
            loadTypeFinal = document.getElementById(temp6).value;
            plantillaId = document.getElementById(temp6_1).value;

            //Parametros Generales  
            if (idAgenteAduanal === "4001" || idAgenteAduanal === "4002" || idAgenteAduanal === "4003" || idAgenteAduanal === "4004" || idAgenteAduanal === "4005" || idAgenteAduanal === "4006") { //RADAR, SESMA, RECHY Y VF  

                let temp7 = "pais_origen[" + i + "]";
                let temp8 = "size_container[" + i + "]";
                let temp9 = "valor_usd[" + i + "]";
                let temp10 = "eta_port_discharge[" + i + "]";
                let temp11 = "agente_aduanal[" + i + "]";
                let temp12 = "pedimento_a1[" + i + "]";
                let temp13 = "pedimento_r1_1er[" + i + "]";
                let temp14 = "motivo_rectificacion_1er[" + i + "]";
                let temp15 = "pedimento_r1_2do[" + i + "]";
                let temp16 = "motivo_rectificacion_2do[" + i + "]";
                let temp17 = "fecha_recepcion_doc[" + i + "]";
                let temp18 = "recinto[" + i + "]";
                let temp19 = "naviera[" + i + "]";
                let temp20 = "buque[" + i + "]";
                let temp21 = "fecha_revalidacion[" + i + "]";
                let temp22 = "fecha_previo_origen[" + i + "]";
                let temp23 = "fecha_previo_destino[" + i + "]";
                let temp24 = "fecha_resultado_previo[" + i + "]";
                let temp25 = "proforma_final[" + i + "]";
                let temp26 = "permiso[" + i + "]";
                let temp27 = "fecha_envio[" + i + "]";
                let temp28 = "fecha_recepcion_perm[" + i + "]";
                let temp29 = "fecha_activacion_perm[" + i + "]";
                let temp30 = "fecha_permisos_aut[" + i + "]";
                let temp31 = "co_pref_arancelaria[" + i + "]";
                let temp32 = "aplic_pref_arancelaria[" + i + "]";
                let temp33 = "req_uva[" + i + "]";
                let temp34 = "req_ca[" + i + "]";
                let temp35 = "fecha_recepcion_ca[" + i + "]";
                let temp36 = "num_constancia_ca[" + i + "]";
                let temp37 = "monto_ca[" + i + "]";
                let temp38 = "fecha_doc_completos[" + i + "]";
                let temp39 = "fecha_pago_pedimento[" + i + "]";
                let temp40 = "fecha_solicitud_transporte[" + i + "]";
                let temp41 = "fecha_modulacion[" + i + "]";
                let temp42 = "modalidad[" + i + "]";
                let temp43 = "resultado_modulacion[" + i + "]";
                let temp44 = "fecha_reconocimiento[" + i + "]";
                let temp45 = "fecha_liberacion[" + i + "]";
                let temp46 = "sello_origen[" + i + "]";
                let temp47 = "sello_final[" + i + "]";
                let temp48 = "fecha_retencion_aut[" + i + "]";
                let temp49 = "fecha_liberacion_aut[" + i + "]";
                let temp50 = "estatus_operacion[" + i + "]";
                let temp51 = "motivo_atraso[" + i + "]";
                let temp52 = "observaciones[" + i + "]";
                let temp53 = "fy[" + i + "]";

                pais_origen = document.getElementById(temp7).value;
                size_container = document.getElementById(temp8).value;
                valor_usd = document.getElementById(temp9).value;
                eta_port_discharge = document.getElementById(temp10).value;
                agente_aduanal = document.getElementById(temp11).value;
                pedimento_a1 = document.getElementById(temp12).value;
                pedimento_r1_1er = document.getElementById(temp13).value;
                motivo_rectificacion_1er = document.getElementById(temp14).value;
                pedimento_r1_2do = document.getElementById(temp15).value;
                motivo_rectificacion_2do = document.getElementById(temp16).value;
                fecha_recepcion_doc = document.getElementById(temp17).value;
                recinto = document.getElementById(temp18).value;
                naviera = document.getElementById(temp19).value;
                buque = document.getElementById(temp20).value;
                fecha_revalidacion = document.getElementById(temp21).value;
                fecha_previo_origen = document.getElementById(temp22).value;
                fecha_previo_destino = document.getElementById(temp23).value;
                fecha_resultado_previo = document.getElementById(temp24).value;
                proforma_final = document.getElementById(temp25).value;
                permiso = document.getElementById(temp26).value;
                fecha_envio = document.getElementById(temp27).value;
                fecha_recepcion_perm = document.getElementById(temp28).value;
                fecha_activacion_perm = document.getElementById(temp29).value;
                fecha_permisos_aut = document.getElementById(temp30).value;
                co_pref_arancelaria = document.getElementById(temp31).value;
                aplic_pref_arancelaria = document.getElementById(temp32).value;
                req_uva = document.getElementById(temp33).value;
                req_ca = document.getElementById(temp34).value;
                fecha_recepcion_ca = document.getElementById(temp35).value;
                num_constancia_ca = document.getElementById(temp36).value;
                monto_ca = document.getElementById(temp37).value;
                fecha_doc_completos = document.getElementById(temp38).value;
                fecha_pago_pedimento = document.getElementById(temp39).value;
                fecha_solicitud_transporte = document.getElementById(temp40).value;
                fecha_modulacion = document.getElementById(temp41).value;
                modalidad = document.getElementById(temp42).value;
                resultado_modulacion = document.getElementById(temp43).value;
                fecha_reconocimiento = document.getElementById(temp44).value;
                fecha_liberacion = document.getElementById(temp45).value;
                sello_origen = document.getElementById(temp46).value;
                sello_final = document.getElementById(temp47).value;
                fecha_retencion_aut = document.getElementById(temp48).value;
                fecha_liberacion_aut = document.getElementById(temp49).value;
                estatus_operacion = document.getElementById(temp50).value;
                motivo_atraso = document.getElementById(temp51).value;
                observaciones = document.getElementById(temp52).value;
                fy = document.getElementById(temp53).value;
            }

            if (idAgenteAduanal === "4001" || idAgenteAduanal === "4006") { //LOGIX Y VF

                let temp54 = "llegada_a_nova[" + i + "]";
                let temp55 = "llegada_a_globe_trade_sd[" + i + "]";
                let temp56 = "archivo_m[" + i + "]";
                let temp57 = "fecha_archivo_m[" + i + "]";
                let temp58 = "fecha_solicit_manip[" + i + "]";
                let temp59 = "fecha_vencim_manip[" + i + "]";
                let temp60 = "fecha_confirm_clave_pedim[" + i + "]";
                let temp61 = "fecha_recep_increment[" + i + "]";
                let temp62 = "t_e[" + i + "]";
                let temp63 = "fecha_vencim_inbound[" + i + "]";

                llegada_a_nova = document.getElementById(temp54).value;
                llegada_a_globe_trade_sd = document.getElementById(temp55).value;
                archivo_m = document.getElementById(temp56).value;
                fecha_archivo_m = document.getElementById(temp57).value;
                fecha_solicit_manip = document.getElementById(temp58).value;
                fecha_vencim_manip = document.getElementById(temp59).value;
                fecha_confirm_clave_pedim = document.getElementById(temp60).value;
                fecha_recep_increment = document.getElementById(temp61).value;
                t_e = document.getElementById(temp62).value;
                fecha_vencim_inbound = document.getElementById(temp63).value;

            }

            if (idAgenteAduanal === "4002" || idAgenteAduanal === "4006") {  //CUSA Y VF

                let temp64 = "no_bultos[" + i + "]";
                let temp65 = "peso_kg[" + i + "]";
                let temp66 = "transferencia[" + i + "]";
                let temp67 = "fecha_inicio_etiquetado[" + i + "]";
                let temp68 = "fecha_termino_etiquetado[" + i + "]";
                let temp69 = "hora_termino_etiquetado[" + i + "]";
                let temp70 = "proveedor[" + i + "]";
                let temp71 = "proveedor_carga[" + i + "]";

                no_bultos = document.getElementById(temp64).value;
                peso_kg = document.getElementById(temp65).value;
                transferencia = document.getElementById(temp66).value;
                fecha_inicio_etiquetado = document.getElementById(temp67).value;
                fecha_termino_etiquetado = document.getElementById(temp68).value;
                hora_termino_etiquetado = document.getElementById(temp69).value;
                proveedor = document.getElementById(temp70).value;
                proveedor_carga = document.getElementById(temp71).value;
            }

            urlCustoms += "&evento[" + i + "]=" + evento +
                    "&shipmentId[" + i + "]=" + shipmentId +
                    "&containerId[" + i + "]=" + containerId +
                    "&referenciaAA[" + i + "]=" + referenciaAA +
                    "&prioridad[" + i + "]=" + prioridad +
                    "&loadTypeFinal[" + i + "]=" + loadTypeFinal +
                    "&plantillaId[" + i + "]=" + plantillaId;


            urlCustoms += "&evento" + i + "=" + evento +
                    "&shipmentId" + i + "=" + shipmentId +
                    "&containerId" + i + "=" + containerId +
                    "&referenciaAA" + i + "=" + referenciaAA +
                    "&prioridad" + i + "=" + prioridad +
                    "&loadTypeFinal" + i + "=" + loadTypeFinal +
                    "&plantillaId" + i + "=" + plantillaId;

            if (idAgenteAduanal === "4001" || idAgenteAduanal === "4002" || idAgenteAduanal === "4003" || idAgenteAduanal === "4004" || idAgenteAduanal === "4005" || idAgenteAduanal === "4006") { //RADAR, SESMA, RECHY Y VF 

                urlCustoms += "&pais_origen[" + i + "]=" + pais_origen +
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
            }

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
            if (pedimento_r1_1er!="") {

                if (motivo_rectificacion_1er.replace(/\s/g, "") === "") {
                    contadorError++;
                    txtErrormSg += "(" + contadorError + ")Ingrese Motivo Rectificación 1. ";
                    changeColorByPositionError(i);
                }

            }

            /* RULE #9 */
            if (pedimento_r1_2do!="") {

                if (motivo_rectificacion_2do.replace(/\s/g, "") === "") {
                    contadorError++;
                    txtErrormSg += "(" + contadorError + ")Ingrese Motivo Rectificación 2. ";
                    changeColorByPositionError(i);
                }

            }

            /* RULE #10 ESTATUS: IMPORTADO */
            if (estatus_operacion === "19") {
                if (idAgenteAduanal === "4001" || idAgenteAduanal === "4002" || idAgenteAduanal === "4006") { //RADAR|SESMA|VF
                    if (pais_origen != "" &&
                            size_container != "" &&
                            valor_usd != "" &&
                            eta_port_discharge != "" &&
                            agente_aduanal != "" &&
                            pedimento_a1 != "" &&
                            pedimento_r1_1er != "" &&
                            motivo_rectificacion_1er != "" &&
                            pedimento_r1_2do != "" &&
                            motivo_rectificacion_2do != "" &&
                            fecha_recepcion_doc != "" &&
                            recinto != "" &&
                            naviera != "" &&
                            buque != "" &&
                            fecha_revalidacion != "" &&
                            fecha_previo_origen != "" &&
                            fecha_previo_destino != "" &&
                            fecha_resultado_previo != "" &&
                            proforma_final != "" &&
                            permiso != "" &&
                            fecha_envio != "" &&
                            fecha_recepcion_perm != "" &&
                            fecha_activacion_perm != "" &&
                            fecha_permisos_aut != "" &&
                            co_pref_arancelaria != "" &&
                            aplic_pref_arancelaria != "" &&
                            req_uva != "" &&
                            req_ca != "" &&
                            fecha_recepcion_ca != "" &&
                            num_constancia_ca != "" &&
                            monto_ca != "" &&
                            fecha_doc_completos != "" &&
                            fecha_pago_pedimento != "" &&
                            fecha_solicitud_transporte != "" &&
                            fecha_modulacion != "" &&
                            modalidad != "" &&
                            resultado_modulacion != "" &&
                            fecha_reconocimiento != "" &&
                            fecha_liberacion != "" &&
                            sello_origen != "" &&
                            sello_final != "" &&
                            motivo_atraso != "" &&
                            fy != "") {
                        contadorError++;
                        txtErrormSg += "(" + contadorError + ")Todos los campos en rojo son obligatorios. ";
                        changeColorByPositionError(i);
                        msgErrorAgenteAduanal(i, idAgenteAduanal);
                    }
                } else if (idAgenteAduanal === "4001") { //LOGIX
                    if (pais_origen != "" &&
                            size_container != "" &&
                            valor_usd != "" &&
                            eta_port_discharge != "" &&
                            agente_aduanal != "" &&
                            pedimento_a1 != "" &&
                            pedimento_r1_1er != "" &&
                            motivo_rectificacion_1er != "" &&
                            pedimento_r1_2do != "" &&
                            motivo_rectificacion_2do != "" &&
                            fecha_recepcion_doc != "" &&
                            recinto != "" &&
                            naviera != "" &&
                            buque != "" &&
                            fecha_revalidacion != "" &&
                            fecha_previo_origen != "" &&
                            fecha_previo_destino != "" &&
                            fecha_resultado_previo != "" &&
                            proforma_final != "" &&
                            permiso != "" &&
                            fecha_envio != "" &&
                            fecha_recepcion_perm != "" &&
                            fecha_activacion_perm != "" &&
                            fecha_permisos_aut != "" &&
                            req_ca != "" &&
                            fecha_recepcion_ca != "" &&
                            num_constancia_ca != "" &&
                            monto_ca != "" &&
                            fecha_doc_completos != "" &&
                            fecha_pago_pedimento != "" &&
                            fecha_solicitud_transporte != "" &&
                            fecha_modulacion != "" &&
                            modalidad != "" &&
                            resultado_modulacion != "" &&
                            fecha_reconocimiento != "" &&
                            fecha_liberacion != "" &&
                            sello_origen != "" &&
                            sello_final != "" &&
                            motivo_atraso != "" &&
                            fy != "" &&
                            llegada_a_nova != "" &&
                            llegada_a_globe_trade_sd != "" &&
                            archivo_m != "" &&
                            fecha_archivo_m != "" &&
                            fecha_solicit_manip != "" &&
                            fecha_vencim_manip != "" &&
                            fecha_confirm_clave_pedim != "" &&
                            fecha_recep_increment != "" &&
                            t_e != "" &&
                            fecha_vencim_inbound != "") {
                        contadorError++;
                        txtErrormSg += "(" + contadorError + ")Todos los campos en rojo son obligatorios. ";
                        changeColorByPositionError(i);
                        msgErrorAgenteAduanal(i, idAgenteAduanal);
                    }
                } else if (idAgenteAduanal === "4002") { //CUSA
                    if (pais_origen != "" &&
                            valor_usd != "" &&
                            eta_port_discharge != "" &&
                            agente_aduanal != "" &&
                            pedimento_a1 != "" &&
                            pedimento_r1_1er != "" &&
                            motivo_rectificacion_1er != "" &&
                            pedimento_r1_2do != "" &&
                            motivo_rectificacion_2do != "" &&
                            fecha_recepcion_doc != "" &&
                            naviera != "" &&
                            fecha_revalidacion != "" &&
                            fecha_previo_destino != "" &&
                            fecha_resultado_previo != "" &&
                            proforma_final != "" &&
                            permiso != "" &&
                            fecha_envio != "" &&
                            fecha_recepcion_perm != "" &&
                            fecha_activacion_perm != "" &&
                            fecha_permisos_aut != "" &&
                            co_pref_arancelaria != "" &&
                            aplic_pref_arancelaria != "" &&
                            req_uva != "" &&
                            req_ca != "" &&
                            fecha_recepcion_ca != "" &&
                            num_constancia_ca != "" &&
                            monto_ca != "" &&
                            fecha_doc_completos != "" &&
                            fecha_pago_pedimento != "" &&
                            fecha_solicitud_transporte != "" &&
                            fecha_modulacion != "" &&
                            modalidad != "" &&
                            resultado_modulacion != "" &&
                            fecha_reconocimiento != "" &&
                            fecha_liberacion != "" &&
                            sello_final != "" &&
                            //estatus_operacion != "" &&
                            motivo_atraso != "" &&
                            fy != "" &&
                            no_bultos != "" &&
                            peso_kg != "" &&
                            transferencia != "" &&
                            fecha_inicio_etiquetado != "" &&
                            fecha_termino_etiquetado != "" &&
                            hora_termino_etiquetado != "" &&
                            proveedor != "" &&
                            proveedor_carga != "") {
                        contadorError++;
                        txtErrormSg += "(" + contadorError + ")Todos los campos en rojo son obligatorio. ";
                        changeColorByPositionError(i);
                        msgErrorAgenteAduanal(i, idAgenteAduanal);
                    }
                } else if (idAgenteAduanal === "4005") { //RECHY
                    if (pais_origen != "" &&
                            valor_usd != "" &&
                            eta_port_discharge != "" &&
                            agente_aduanal != "" &&
                            pedimento_a1 != "" &&
                            pedimento_r1_1er != "" &&
                            motivo_rectificacion_1er != "" &&
                            pedimento_r1_2do != "" &&
                            motivo_rectificacion_2do != "" &&
                            fecha_recepcion_doc != "" &&
                            fecha_previo_destino != "" &&
                            fecha_resultado_previo != "" &&
                            proforma_final != "" &&
                            permiso != "" &&
                            fecha_envio != "" &&
                            fecha_recepcion_perm != "" &&
                            fecha_activacion_perm != "" &&
                            fecha_permisos_aut != "" &&
                            co_pref_arancelaria != "" &&
                            aplic_pref_arancelaria != "" &&
                            req_uva != "" &&
                            req_ca != "" &&
                            fecha_recepcion_ca != "" &&
                            num_constancia_ca != "" &&
                            monto_ca != "" &&
                            fecha_doc_completos != "" &&
                            fecha_pago_pedimento != "" &&
                            fecha_solicitud_transporte != "" &&
                            fecha_modulacion != "" &&
                            modalidad != "" &&
                            resultado_modulacion != "" &&
                            fecha_reconocimiento != "" &&
                            fecha_liberacion != "" &&
                            motivo_atraso != "") {
                        contadorError++;
                        txtErrormSg += "(" + contadorError + ")Todos los campos en rojo son obligatorios. ";
                        changeColorByPositionError(i);
                        msgErrorAgenteAduanal(i, idAgenteAduanal);
                    }
                }
            }

            let urlData = encodeURI("../InsertarCustomsForms?idAgenteAduanal=" + idAgenteAduanal + "&numCustoms=" + cont + urlCustoms);
            
                try {
                    const response = await fetch(urlData);
                    if (!response.ok) {
                        throw new Error('Error en la solicitud');
                    }
                    const data = await response.text();
                    
                    if(contadorError >0){
                        mSgErrorLineCustoms(txtErrormSg, i);
                    }else{
                        changeColorByPositionSuccess(i);
                        document.getElementById('mSgError' + i).innerHTML = "";
                        msgErrorAgenteAduanal(i, idAgenteAduanal);
                    }

                    urlCustoms = "";
                    cont++;
                    let webp = "";

                    if (data === "0") {  //Activación Semaforo
                        webp = "../img/circle-gray.webp";
                    }else if (data === "1") { 
                        webp = "../img/circle-green.webp";
                    }else if (data === "2") { 
                        webp = "../img/circle-yellow.webp";
                    }else if (data === "3") { 
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

        $("#WindowLoad").remove();
        swal("", "Información Actualizada", "success");
        alertclose();
    }

    async function AddLineCustoms(i) {

        jsShowWindowLoad('Cargando Información');

        let idAgenteAduanal = document.getElementById("idAgenteAduanal").value;
        let contadorError = 0;
        let txtErrormSg = "";

        //Parametros Indicadores   
        let temp4 = "referenciaAA[" + i + "]";
        let temp1 = "evento[" + i + "]";
        let temp2 = "shipmentId[" + i + "]";
        let temp3 = "containerId[" + i + "]";
        let temp5 = "prioridad[" + i + "]";
        let temp6 = "loadTypeFinal[" + i + "]";
        let temp6_1 = "plantillaId[" + i + "]";

        referenciaAA = document.getElementById(temp4).value;
        evento = document.getElementById(temp1).value;
        shipmentId = document.getElementById(temp2).value;
        containerId = document.getElementById(temp3).value;
        prioridad = document.getElementById(temp5).value;
        loadTypeFinal = document.getElementById(temp6).value;
        plantillaId = document.getElementById(temp6_1).value;

        //Parametros Generales  
        if (idAgenteAduanal === "4001" || idAgenteAduanal === "4002" || idAgenteAduanal === "4003" || idAgenteAduanal === "4004" || idAgenteAduanal === "4005" || idAgenteAduanal === "4006") { //RADAR, SESMA, RECHY Y VF  

            let temp7 = "pais_origen[" + i + "]";
            let temp8 = "size_container[" + i + "]";
            let temp9 = "valor_usd[" + i + "]";
            let temp10 = "eta_port_discharge[" + i + "]";
            let temp11 = "agente_aduanal[" + i + "]";
            let temp12 = "pedimento_a1[" + i + "]";
            let temp13 = "pedimento_r1_1er[" + i + "]";
            let temp14 = "motivo_rectificacion_1er[" + i + "]";
            let temp15 = "pedimento_r1_2do[" + i + "]";
            let temp16 = "motivo_rectificacion_2do[" + i + "]";
            let temp17 = "fecha_recepcion_doc[" + i + "]";
            let temp18 = "recinto[" + i + "]";
            let temp19 = "naviera[" + i + "]";
            let temp20 = "buque[" + i + "]";
            let temp21 = "fecha_revalidacion[" + i + "]";
            let temp22 = "fecha_previo_origen[" + i + "]";
            let temp23 = "fecha_previo_destino[" + i + "]";
            let temp24 = "fecha_resultado_previo[" + i + "]";
            let temp25 = "proforma_final[" + i + "]";
            let temp26 = "permiso[" + i + "]";
            let temp27 = "fecha_envio[" + i + "]";
            let temp28 = "fecha_recepcion_perm[" + i + "]";
            let temp29 = "fecha_activacion_perm[" + i + "]";
            let temp30 = "fecha_permisos_aut[" + i + "]";
            let temp31 = "co_pref_arancelaria[" + i + "]";
            let temp32 = "aplic_pref_arancelaria[" + i + "]";
            let temp33 = "req_uva[" + i + "]";
            let temp34 = "req_ca[" + i + "]";
            let temp35 = "fecha_recepcion_ca[" + i + "]";
            let temp36 = "num_constancia_ca[" + i + "]";
            let temp37 = "monto_ca[" + i + "]";
            let temp38 = "fecha_doc_completos[" + i + "]";
            let temp39 = "fecha_pago_pedimento[" + i + "]";
            let temp40 = "fecha_solicitud_transporte[" + i + "]";
            let temp41 = "fecha_modulacion[" + i + "]";
            let temp42 = "modalidad[" + i + "]";
            let temp43 = "resultado_modulacion[" + i + "]";
            let temp44 = "fecha_reconocimiento[" + i + "]";
            let temp45 = "fecha_liberacion[" + i + "]";
            let temp46 = "sello_origen[" + i + "]";
            let temp47 = "sello_final[" + i + "]";
            let temp48 = "fecha_retencion_aut[" + i + "]";
            let temp49 = "fecha_liberacion_aut[" + i + "]";
            let temp50 = "estatus_operacion[" + i + "]";
            let temp51 = "motivo_atraso[" + i + "]";
            let temp52 = "observaciones[" + i + "]";
            let temp53 = "fy[" + i + "]";

            pais_origen = document.getElementById(temp7).value;
            size_container = document.getElementById(temp8).value;
            valor_usd = document.getElementById(temp9).value;
            eta_port_discharge = document.getElementById(temp10).value;
            agente_aduanal = document.getElementById(temp11).value;
            pedimento_a1 = document.getElementById(temp12).value;
            pedimento_r1_1er = document.getElementById(temp13).value;
            motivo_rectificacion_1er = document.getElementById(temp14).value;
            pedimento_r1_2do = document.getElementById(temp15).value;
            motivo_rectificacion_2do = document.getElementById(temp16).value;
            fecha_recepcion_doc = document.getElementById(temp17).value;
            recinto = document.getElementById(temp18).value;
            naviera = document.getElementById(temp19).value;
            buque = document.getElementById(temp20).value;
            fecha_revalidacion = document.getElementById(temp21).value;
            fecha_previo_origen = document.getElementById(temp22).value;
            fecha_previo_destino = document.getElementById(temp23).value;
            fecha_resultado_previo = document.getElementById(temp24).value;
            proforma_final = document.getElementById(temp25).value;
            permiso = document.getElementById(temp26).value;
            fecha_envio = document.getElementById(temp27).value;
            fecha_recepcion_perm = document.getElementById(temp28).value;
            fecha_activacion_perm = document.getElementById(temp29).value;
            fecha_permisos_aut = document.getElementById(temp30).value;
            co_pref_arancelaria = document.getElementById(temp31).value;
            aplic_pref_arancelaria = document.getElementById(temp32).value;
            req_uva = document.getElementById(temp33).value;
            req_ca = document.getElementById(temp34).value;
            fecha_recepcion_ca = document.getElementById(temp35).value;
            num_constancia_ca = document.getElementById(temp36).value;
            monto_ca = document.getElementById(temp37).value;
            fecha_doc_completos = document.getElementById(temp38).value;
            fecha_pago_pedimento = document.getElementById(temp39).value;
            fecha_solicitud_transporte = document.getElementById(temp40).value;
            fecha_modulacion = document.getElementById(temp41).value;
            modalidad = document.getElementById(temp42).value;
            resultado_modulacion = document.getElementById(temp43).value;
            fecha_reconocimiento = document.getElementById(temp44).value;
            fecha_liberacion = document.getElementById(temp45).value;
            sello_origen = document.getElementById(temp46).value;
            sello_final = document.getElementById(temp47).value;
            fecha_retencion_aut = document.getElementById(temp48).value;
            fecha_liberacion_aut = document.getElementById(temp49).value;
            estatus_operacion = document.getElementById(temp50).value;
            motivo_atraso = document.getElementById(temp51).value;
            observaciones = document.getElementById(temp52).value;
            fy = document.getElementById(temp53).value;
        }

        if (idAgenteAduanal === "4001" || idAgenteAduanal === "4006") { //LOGIX Y VF

            let temp54 = "llegada_a_nova[" + i + "]";
            let temp55 = "llegada_a_globe_trade_sd[" + i + "]";
            let temp56 = "archivo_m[" + i + "]";
            let temp57 = "fecha_archivo_m[" + i + "]";
            let temp58 = "fecha_solicit_manip[" + i + "]";
            let temp59 = "fecha_vencim_manip[" + i + "]";
            let temp60 = "fecha_confirm_clave_pedim[" + i + "]";
            let temp61 = "fecha_recep_increment[" + i + "]";
            let temp62 = "t_e[" + i + "]";
            let temp63 = "fecha_vencim_inbound[" + i + "]";

            llegada_a_nova = document.getElementById(temp54).value;
            llegada_a_globe_trade_sd = document.getElementById(temp55).value;
            archivo_m = document.getElementById(temp56).value;
            fecha_archivo_m = document.getElementById(temp57).value;
            fecha_solicit_manip = document.getElementById(temp58).value;
            fecha_vencim_manip = document.getElementById(temp59).value;
            fecha_confirm_clave_pedim = document.getElementById(temp60).value;
            fecha_recep_increment = document.getElementById(temp61).value;
            t_e = document.getElementById(temp62).value;
            fecha_vencim_inbound = document.getElementById(temp63).value;

        }

        if (idAgenteAduanal === "4002" || idAgenteAduanal === "4006") {  //CUSA Y VF

            let temp64 = "no_bultos[" + i + "]";
            let temp65 = "peso_kg[" + i + "]";
            let temp66 = "transferencia[" + i + "]";
            let temp67 = "fecha_inicio_etiquetado[" + i + "]";
            let temp68 = "fecha_termino_etiquetado[" + i + "]";
            let temp69 = "hora_termino_etiquetado[" + i + "]";
            let temp70 = "proveedor[" + i + "]";
            let temp71 = "proveedor_carga[" + i + "]";

            no_bultos = document.getElementById(temp64).value;
            peso_kg = document.getElementById(temp65).value;
            transferencia = document.getElementById(temp66).value;
            fecha_inicio_etiquetado = document.getElementById(temp67).value;
            fecha_termino_etiquetado = document.getElementById(temp68).value;
            hora_termino_etiquetado = document.getElementById(temp69).value;
            proveedor = document.getElementById(temp70).value;
            proveedor_carga = document.getElementById(temp71).value;
        }

        urlCustoms += "&evento[" + i + "]=" + evento +
                "&shipmentId[" + i + "]=" + shipmentId +
                "&containerId[" + i + "]=" + containerId +
                "&referenciaAA[" + i + "]=" + referenciaAA +
                "&prioridad[" + i + "]=" + prioridad +
                "&loadTypeFinal[" + i + "]=" + loadTypeFinal +
                "&plantillaId[" + i + "]=" + plantillaId;


        urlCustoms += "&evento" + i + "=" + evento +
                "&shipmentId" + i + "=" + shipmentId +
                "&containerId" + i + "=" + containerId +
                "&referenciaAA" + i + "=" + referenciaAA +
                "&prioridad" + i + "=" + prioridad +
                "&loadTypeFinal" + i + "=" + loadTypeFinal +
                "&plantillaId" + i + "=" + plantillaId;

        if (idAgenteAduanal === "4001" || idAgenteAduanal === "4002" || idAgenteAduanal === "4003" || idAgenteAduanal === "4004" || idAgenteAduanal === "4005" || idAgenteAduanal === "4006") { //RADAR, SESMA, RECHY Y VF 

            urlCustoms += "&pais_origen[" + i + "]=" + pais_origen +
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
        }

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
        if (pedimento_r1_1er != "") {

            if (motivo_rectificacion_1er.replace(/\s/g, "") === "") {
                contadorError++;
                txtErrormSg += "(" + contadorError + ")Ingrese Motivo Rectificación 1. ";
                changeColorByPositionError(i);
            }

        }

        /* RULE #9 */
        if (pedimento_r1_2do != "") {

            if (motivo_rectificacion_2do.replace(/\s/g, "") === "") {
                contadorError++;
                txtErrormSg += "(" + contadorError + ")Ingrese Motivo Rectificación 2. ";
                changeColorByPositionError(i);
            }

        }

        /* RULE #10 ESTATUS: IMPORTADO */
        if (estatus_operacion === "19") {
            if (idAgenteAduanal === "4001" || idAgenteAduanal === "4002" || idAgenteAduanal === "4006") { //RADAR|SESMA|VF
                if (pais_origen != "" &&
                        size_container != "" &&
                        valor_usd != "" &&
                        eta_port_discharge != "" &&
                        agente_aduanal != "" &&
                        pedimento_a1 != "" &&
                        pedimento_r1_1er != "" &&
                        motivo_rectificacion_1er != "" &&
                        pedimento_r1_2do != "" &&
                        motivo_rectificacion_2do != "" &&
                        fecha_recepcion_doc != "" &&
                        recinto != "" &&
                        naviera != "" &&
                        buque != "" &&
                        fecha_revalidacion != "" &&
                        fecha_previo_origen != "" &&
                        fecha_previo_destino != "" &&
                        fecha_resultado_previo != "" &&
                        proforma_final != "" &&
                        permiso != "" &&
                        fecha_envio != "" &&
                        fecha_recepcion_perm != "" &&
                        fecha_activacion_perm != "" &&
                        fecha_permisos_aut != "" &&
                        co_pref_arancelaria != "" &&
                        aplic_pref_arancelaria != "" &&
                        req_uva != "" &&
                        req_ca != "" &&
                        fecha_recepcion_ca != "" &&
                        num_constancia_ca != "" &&
                        monto_ca != "" &&
                        fecha_doc_completos != "" &&
                        fecha_pago_pedimento != "" &&
                        fecha_solicitud_transporte != "" &&
                        fecha_modulacion != "" &&
                        modalidad != "" &&
                        resultado_modulacion != "" &&
                        fecha_reconocimiento != "" &&
                        fecha_liberacion != "" &&
                        sello_origen != "" &&
                        sello_final != "" &&
                        motivo_atraso != "" &&
                        fy != "") {
                    contadorError++;
                    txtErrormSg += "(" + contadorError + ")Todos los campos en rojo son obligatorios. ";
                    changeColorByPositionError(i);
                    msgErrorAgenteAduanal(i, idAgenteAduanal);
                }
            } else if (idAgenteAduanal === "4001") { //LOGIX
                if (pais_origen != "" &&
                        size_container != "" &&
                        valor_usd != "" &&
                        eta_port_discharge != "" &&
                        agente_aduanal != "" &&
                        pedimento_a1 != "" &&
                        pedimento_r1_1er != "" &&
                        motivo_rectificacion_1er != "" &&
                        pedimento_r1_2do != "" &&
                        motivo_rectificacion_2do != "" &&
                        fecha_recepcion_doc != "" &&
                        recinto != "" &&
                        naviera != "" &&
                        buque != "" &&
                        fecha_revalidacion != "" &&
                        fecha_previo_origen != "" &&
                        fecha_previo_destino != "" &&
                        fecha_resultado_previo != "" &&
                        proforma_final != "" &&
                        permiso != "" &&
                        fecha_envio != "" &&
                        fecha_recepcion_perm != "" &&
                        fecha_activacion_perm != "" &&
                        fecha_permisos_aut != "" &&
                        req_ca != "" &&
                        fecha_recepcion_ca != "" &&
                        num_constancia_ca != "" &&
                        monto_ca != "" &&
                        fecha_doc_completos != "" &&
                        fecha_pago_pedimento != "" &&
                        fecha_solicitud_transporte != "" &&
                        fecha_modulacion != "" &&
                        modalidad != "" &&
                        resultado_modulacion != "" &&
                        fecha_reconocimiento != "" &&
                        fecha_liberacion != "" &&
                        sello_origen != "" &&
                        sello_final != "" &&
                        motivo_atraso != "" &&
                        fy != "" &&
                        llegada_a_nova != "" &&
                        llegada_a_globe_trade_sd != "" &&
                        archivo_m != "" &&
                        fecha_archivo_m != "" &&
                        fecha_solicit_manip != "" &&
                        fecha_vencim_manip != "" &&
                        fecha_confirm_clave_pedim != "" &&
                        fecha_recep_increment != "" &&
                        t_e != "" &&
                        fecha_vencim_inbound != "") {
                    contadorError++;
                    txtErrormSg += "(" + contadorError + ")Todos los campos en rojo son obligatorios. ";
                    changeColorByPositionError(i);
                    msgErrorAgenteAduanal(i, idAgenteAduanal);
                }
            } else if (idAgenteAduanal === "4002") { //CUSA
                if (pais_origen != "" &&
                        valor_usd != "" &&
                        eta_port_discharge != "" &&
                        agente_aduanal != "" &&
                        pedimento_a1 != "" &&
                        pedimento_r1_1er != "" &&
                        motivo_rectificacion_1er != "" &&
                        pedimento_r1_2do != "" &&
                        motivo_rectificacion_2do != "" &&
                        fecha_recepcion_doc != "" &&
                        naviera != "" &&
                        fecha_revalidacion != "" &&
                        fecha_previo_destino != "" &&
                        fecha_resultado_previo != "" &&
                        proforma_final != "" &&
                        permiso != "" &&
                        fecha_envio != "" &&
                        fecha_recepcion_perm != "" &&
                        fecha_activacion_perm != "" &&
                        fecha_permisos_aut != "" &&
                        co_pref_arancelaria != "" &&
                        aplic_pref_arancelaria != "" &&
                        req_uva != "" &&
                        req_ca != "" &&
                        fecha_recepcion_ca != "" &&
                        num_constancia_ca != "" &&
                        monto_ca != "" &&
                        fecha_doc_completos != "" &&
                        fecha_pago_pedimento != "" &&
                        fecha_solicitud_transporte != "" &&
                        fecha_modulacion != "" &&
                        modalidad != "" &&
                        resultado_modulacion != "" &&
                        fecha_reconocimiento != "" &&
                        fecha_liberacion != "" &&
                        sello_final != "" &&
                        //estatus_operacion != "" &&
                        motivo_atraso != "" &&
                        fy != "" &&
                        no_bultos != "" &&
                        peso_kg != "" &&
                        transferencia != "" &&
                        fecha_inicio_etiquetado != "" &&
                        fecha_termino_etiquetado != "" &&
                        hora_termino_etiquetado != "" &&
                        proveedor != "" &&
                        proveedor_carga != "") {
                    contadorError++;
                    txtErrormSg += "(" + contadorError + ")Todos los campos en rojo son obligatorio. ";
                    changeColorByPositionError(i);
                    msgErrorAgenteAduanal(i, idAgenteAduanal);
                }
            } else if (idAgenteAduanal === "4005") { //RECHY
                if (pais_origen != "" &&
                        valor_usd != "" &&
                        eta_port_discharge != "" &&
                        agente_aduanal != "" &&
                        pedimento_a1 != "" &&
                        pedimento_r1_1er != "" &&
                        motivo_rectificacion_1er != "" &&
                        pedimento_r1_2do != "" &&
                        motivo_rectificacion_2do != "" &&
                        fecha_recepcion_doc != "" &&
                        fecha_previo_destino != "" &&
                        fecha_resultado_previo != "" &&
                        proforma_final != "" &&
                        permiso != "" &&
                        fecha_envio != "" &&
                        fecha_recepcion_perm != "" &&
                        fecha_activacion_perm != "" &&
                        fecha_permisos_aut != "" &&
                        co_pref_arancelaria != "" &&
                        aplic_pref_arancelaria != "" &&
                        req_uva != "" &&
                        req_ca != "" &&
                        fecha_recepcion_ca != "" &&
                        num_constancia_ca != "" &&
                        monto_ca != "" &&
                        fecha_doc_completos != "" &&
                        fecha_pago_pedimento != "" &&
                        fecha_solicitud_transporte != "" &&
                        fecha_modulacion != "" &&
                        modalidad != "" &&
                        resultado_modulacion != "" &&
                        fecha_reconocimiento != "" &&
                        fecha_liberacion != "" &&
                        motivo_atraso != "") {
                    contadorError++;
                    txtErrormSg += "(" + contadorError + ")Todos los campos en rojo son obligatorios. ";
                    changeColorByPositionError(i);
                    msgErrorAgenteAduanal(i, idAgenteAduanal);
                }
            }
        }

        let urlData = encodeURI("../InsertarCustomsForms?idAgenteAduanal=" + idAgenteAduanal + "&numCustoms=" + i + urlCustoms);
            try {
                const response = await fetch(urlData);
                if (!response.ok) {
                    throw new Error('Error en la solicitud');
                }
                const data = await response.text();
               
                if(contadorError >0){
                    mSgErrorLineCustoms(txtErrormSg, i);
                }else{
                    changeColorByPositionSuccess(i);
                    document.getElementById('mSgError' + i).innerHTML = "";
                    msgErrorAgenteAduanal(i, idAgenteAduanal);
                }
                
                urlCustoms = "";
                cont++;
                let webp = "";

                if (data === "0") {  //Activación Semaforo
                    webp = "../img/circle-gray.webp";
                }else if (data === "1") { 
                    webp = "../img/circle-green.webp";
                }else if (data === "2") { 
                    webp = "../img/circle-yellow.webp";
                }else if (data === "3") { 
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

        $("#WindowLoad").remove();
        swal("", "Información Actualizada", "success");
        alertclose();
    }

    function openModalPlantilla() {
        $("#modalSubirPlantilla").modal("show");
    }

    function alertclose() {
        setTimeout(function () {
            swal.close();
            //$("#WindowLoad").remove();
        }, 1000);
    }

    function onSemaforo() {
        swal("", "Semaforo Activado", "info");
        setTimeout(function () {
            swal.close();
        }, 1000);
    }

    function doSearch() {
        var tableReg = document.getElementById('main-table');
        var searchText = document.getElementById('searchTerm').value.toLowerCase();
        var cellsOfRow = "";
        var found = false;
        var compareWith = "";

        // Recorremos todas las filas con contenido de la tabla
        for (var i = 1; i < tableReg.rows.length; i++)
        {
            cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
            found = false;
            // Recorremos todas las celdas
            for (var j = 0; j < cellsOfRow.length && !found; j++)
            {
                compareWith = cellsOfRow[j].innerHTML.toLowerCase();
                // Buscamos el texto en el contenido de la celda
                if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
                {
                    found = true;
                }
            }
            if (found)
            {
                tableReg.rows[i].style.display = '';
            } else {
                // si no ha encontrado ninguna coincidencia, esconde la
                // fila de la tabla
                tableReg.rows[i].style.display = 'none';
            }
        }
    }

    function cleanMultiselects() {
        //$('.selectpicker').selectpicker('deselectAll');
        FiltrerData("0");
    }

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

    function cleanPermiso(permiso, i) {
        let color = "";
        let req = false;

        if (permiso === "No") {
            document.getElementById("fecha_envio[" + i + "]").value = "";
            document.getElementById("fecha_recepcion_perm[" + i + "]").value = "";
            document.getElementById("fecha_activacion_perm[" + i + "]").value = "";
            document.getElementById("fecha_permisos_aut[" + i + "]").value = "";
            color = "#ced4da";
            req = true;
        } else {
            color = "#CC9D77";
            req = false;
        }

        document.getElementById("permiso[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_envio[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_recepcion_perm[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_activacion_perm[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_permisos_aut[" + i + "]").style.borderColor = color;
        /*Habilitar/Deshabilitar campos*/
        document.getElementById("fecha_envio[" + i + "]").disabled = req;
        document.getElementById("fecha_recepcion_perm[" + i + "]").disabled = req;
        document.getElementById("fecha_activacion_perm[" + i + "]").disabled = req;
        document.getElementById("fecha_permisos_aut[" + i + "]").disabled = req;
    }

    function cleanRequerimientoCA(reqCa, i) {
        let color = "";
        let req = false;

        if (reqCa === "No") {
            document.getElementById("fecha_recepcion_ca[" + i + "]").value = "";
            document.getElementById("num_constancia_ca[" + i + "]").value = "";
            document.getElementById("monto_ca[" + i + "]").value = "";
            color = "#ced4da";
            req = true;
        } else {
            color = "#616363";
            req = false;
        }

        document.getElementById("req_ca[" + i + "]").style.borderColor = color;
        document.getElementById("fecha_recepcion_ca[" + i + "]").style.borderColor = color;
        document.getElementById("num_constancia_ca[" + i + "]").style.borderColor = color;
        document.getElementById("monto_ca[" + i + "]").style.borderColor = color;
        /*Habilitar/Deshabilitar campos*/
        document.getElementById("fecha_recepcion_ca[" + i + "]").disabled = req;
    }

    function cleanResultadoModulacion(modulacion, i, AgentType) {
        let color = "";

        if (modulacion === "Verde") {
            document.getElementById("sello_origen[" + i + "]").value = "";
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

    function cleanPedimento_r1_1er(pedimento, i) {
        let color = "";

        if (pedimento.replace(/\s/g, "") === "") {
            document.getElementById("motivo_rectificacion_1er[" + i + "]").value = "";
            color = "#ced4da";
        } else {
            color = "#CC9D77";
        }

        document.getElementById("pedimento_r1_1er[" + i + "]").style.borderColor = color;
        document.getElementById("motivo_rectificacion_1er[" + i + "]").style.borderColor = color;
    }

    function cleanPedimento_r1_2do(pedimento, i) {
        let color = "";

        if (pedimento.replace(/\s/g, "") === "") {
            document.getElementById("motivo_rectificacion_2do[" + i + "]").value = "";
            color = "#ced4da";
        } else {
            color = "#CC9D77";
        }

        document.getElementById("pedimento_r1_2do[" + i + "]").style.borderColor = color;
        document.getElementById("motivo_rectificacion_2do[" + i + "]").style.borderColor = color;
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

    function formComplet(idAgenteAduanal, i) {
       /* RULE #10 */
        const formulario = document.getElementById('tr' + i);
        let disabledOption = "";

        pais_origen = formulario.querySelector('[name="pais_origen[' + i + ']"]').value;
        size_container = formulario.querySelector('[name="size_container[' + i + ']"]').value;
        valor_usd = formulario.querySelector('[name="valor_usd[' + i + ']"]').value;
        eta_port_discharge = formulario.querySelector('[name="eta_port_discharge[' + i + ']"]').value;
        agente_aduanal = formulario.querySelector('[name="agente_aduanal[' + i + ']"]').value;
        pedimento_a1 = formulario.querySelector('[name="pedimento_a1[' + i + ']"]').value;
        pedimento_r1_1er = formulario.querySelector('[name="pedimento_r1_1er[' + i + ']"]').value;
        motivo_rectificacion_1er = formulario.querySelector('[name="motivo_rectificacion_1er[' + i + ']"]').value;
        pedimento_r1_2do = formulario.querySelector('[name="pedimento_r1_2do[' + i + ']"]').value;
        motivo_rectificacion_2do = formulario.querySelector('[name="motivo_rectificacion_2do[' + i + ']"]').value;
        fecha_recepcion_doc = formulario.querySelector('[name="fecha_recepcion_doc[' + i + ']"]').value;
        recinto = formulario.querySelector('[name="recinto[' + i + ']"]').value;
        naviera = formulario.querySelector('[name="naviera[' + i + ']"]').value;
        buque = formulario.querySelector('[name="buque[' + i + ']"]').value;
        fecha_revalidacion = formulario.querySelector('[name="fecha_revalidacion[' + i + ']"]').value;
        fecha_previo_origen = formulario.querySelector('[name="fecha_previo_origen[' + i + ']"]').value;
        fecha_previo_destino = formulario.querySelector('[name="fecha_previo_destino[' + i + ']"]').value;
        fecha_resultado_previo = formulario.querySelector('[name="fecha_resultado_previo[' + i + ']"]').value;
        proforma_final = formulario.querySelector('[name="proforma_final[' + i + ']"]').value;
        permiso = formulario.querySelector('[name="permiso[' + i + ']"]').value;
        fecha_envio = formulario.querySelector('[name="fecha_envio[' + i + ']"]').value;
        fecha_recepcion_perm = formulario.querySelector('[name="fecha_recepcion_perm[' + i + ']"]').value;
        fecha_activacion_perm = formulario.querySelector('[name="fecha_activacion_perm[' + i + ']"]').value;
        fecha_permisos_aut = formulario.querySelector('[name="fecha_permisos_aut[' + i + ']"]').value;
        co_pref_arancelaria = formulario.querySelector('[name="co_pref_arancelaria[' + i + ']"]').value;
        aplic_pref_arancelaria = formulario.querySelector('[name="aplic_pref_arancelaria[' + i + ']"]').value;
        req_uva = formulario.querySelector('[name="req_uva[' + i + ']"]').value;
        req_ca = formulario.querySelector('[name="req_ca[' + i + ']"]').value;
        fecha_recepcion_ca = formulario.querySelector('[name="fecha_recepcion_ca[' + i + ']"]').value;
        num_constancia_ca = formulario.querySelector('[name="num_constancia_ca[' + i + ']"]').value;
        monto_ca = formulario.querySelector('[name="monto_ca[' + i + ']"]').value;
        fecha_doc_completos = formulario.querySelector('[name="fecha_doc_completos[' + i + ']"]').value;
        fecha_pago_pedimento = formulario.querySelector('[name="fecha_pago_pedimento[' + i + ']"]').value;
        fecha_solicitud_transporte = formulario.querySelector('[name="fecha_solicitud_transporte[' + i + ']"]').value;
        fecha_modulacion = formulario.querySelector('[name="fecha_modulacion[' + i + ']"]').value;
        modalidad = formulario.querySelector('[name="modalidad[' + i + ']"]').value;
        resultado_modulacion = formulario.querySelector('[name="resultado_modulacion[' + i + ']"]').value;
        fecha_reconocimiento = formulario.querySelector('[name="fecha_reconocimiento[' + i + ']"]').value;
        fecha_liberacion = formulario.querySelector('[name="fecha_liberacion[' + i + ']"]').value;
        sello_origen = formulario.querySelector('[name="sello_origen[' + i + ']"]').value;
        sello_final = formulario.querySelector('[name="sello_final[' + i + ']"]').value;
        motivo_atraso = formulario.querySelector('[name="motivo_atraso[' + i + ']"]').value;
        fy = formulario.querySelector('[name="fy[' + i + ']"]').value;

        if (idAgenteAduanal === "4001") { //LOGIX
            llegada_a_nova = formulario.querySelector('[name="llegada_a_nova[' + i + ']"]').value;
            llegada_a_globe_trade_sd = formulario.querySelector('[name="llegada_a_globe_trade_sd[' + i + ']"]').value;
            archivo_m = formulario.querySelector('[name="archivo_m[' + i + ']"]').value;
            fecha_archivo_m = formulario.querySelector('[name="fecha_archivo_m[' + i + ']"]').value;
            fecha_solicit_manip = formulario.querySelector('[name="fecha_solicit_manip[' + i + ']"]').value;
            fecha_vencim_manip = formulario.querySelector('[name="fecha_vencim_manip[' + i + ']"]').value;
            fecha_confirm_clave_pedim = formulario.querySelector('[name="fecha_confirm_clave_pedim[' + i + ']"]').value;
            fecha_recep_increment = formulario.querySelector('[name="fecha_recep_increment[' + i + ']"]').value;
            t_e = formulario.querySelector('[name="t_e[' + i + ']"]').value;
            fecha_vencim_inbound = formulario.querySelector('[name="fecha_vencim_inbound[' + i + ']"]').value;
        }

        if (idAgenteAduanal === "4002") { //CUSA
            no_bultos = formulario.querySelector('[name="no_bultos[' + i + ']"]').value;
            peso_kg = formulario.querySelector('[name="peso_kg[' + i + ']"]').value;
            transferencia = formulario.querySelector('[name="transferencia[' + i + ']"]').value;
            fecha_inicio_etiquetado = formulario.querySelector('[name="fecha_inicio_etiquetado[' + i + ']"]').value;
            fecha_termino_etiquetado = formulario.querySelector('[name="fecha_termino_etiquetado[' + i + ']"]').value;
            hora_termino_etiquetado = formulario.querySelector('[name="hora_termino_etiquetado[' + i + ']"]').value;
            proveedor = formulario.querySelector('[name="proveedor[' + i + ']"]').value;
            proveedor_carga = formulario.querySelector('[name="proveedor_carga[' + i + ']"]').value;
        }

        /* RULE #10 */   /*(Estatus Importado)*/
        if (idAgenteAduanal === "4001" || idAgenteAduanal === "4002" || idAgenteAduanal === "4006") { //RADAR|SESMA|VF
            if (pais_origen == "" || size_container == "" || valor_usd == "" || eta_port_discharge == "" || agente_aduanal == "" || pedimento_a1 == "" || pedimento_r1_1er == "" || motivo_rectificacion_1er == "" || pedimento_r1_2do == "" || motivo_rectificacion_2do == "" || fecha_recepcion_doc == "" || recinto == "" || naviera == "" || buque == "" || fecha_revalidacion == "" || fecha_previo_origen == "" || fecha_previo_destino == "" || fecha_resultado_previo == "" || proforma_final == "" || co_pref_arancelaria == "" || aplic_pref_arancelaria == "" || req_uva == "" || req_ca == "" || fecha_recepcion_ca == "" || num_constancia_ca == "" || monto_ca == "" || fecha_doc_completos == "" || fecha_pago_pedimento == "" || fecha_solicitud_transporte == "" || fecha_modulacion == "" || modalidad == "" || resultado_modulacion == "" || fecha_reconocimiento == "" || fecha_liberacion == "" || sello_origen == "" || sello_final == "" || motivo_atraso == "" || fy == "") {
                disabledOption = "false";
            }
            if (permiso === "Si") {
                if (fecha_envio == "" || fecha_recepcion_perm == "" || fecha_activacion_perm == "" || fecha_permisos_aut == "") {
                    disabledOption = "false";
                }
            }
        } else if (idAgenteAduanal === "4001") { //LOGIX
            if (pais_origen == "" || size_container == "" || valor_usd == "" || eta_port_discharge == "" || agente_aduanal == "" || pedimento_a1 == "" || pedimento_r1_1er == "" || motivo_rectificacion_1er == "" || pedimento_r1_2do == "" || motivo_rectificacion_2do == "" || fecha_recepcion_doc == "" || recinto == "" || naviera == "" || buque == "" || fecha_revalidacion == "" || fecha_previo_origen == "" || fecha_previo_destino == "" || fecha_resultado_previo == "" || proforma_final == "" || req_ca == "" || fecha_recepcion_ca == "" || num_constancia_ca == "" || monto_ca == "" || fecha_doc_completos == "" || fecha_pago_pedimento == "" || fecha_solicitud_transporte == "" || fecha_modulacion == "" || modalidad == "" || resultado_modulacion == "" || fecha_reconocimiento == "" || fecha_liberacion == "" || sello_origen == "" || sello_final == "" || motivo_atraso == "" || fy == "" || llegada_a_nova == "" || llegada_a_globe_trade_sd == "" || archivo_m == "" || fecha_archivo_m == "" || fecha_solicit_manip == "" || fecha_vencim_manip == "" || fecha_confirm_clave_pedim == "" || fecha_recep_increment == "" || t_e == "" || fecha_vencim_inbound == "") {
                disabledOption = "false";
            }
            if (permiso === "Si") {
                if (fecha_envio == "" || fecha_recepcion_perm == "" || fecha_activacion_perm == "" || fecha_permisos_aut == "") {
                    disabledOption = "false";
                }
            }
        } else if (idAgenteAduanal === "4002") { //CUSA
            if (pais_origen == "" || valor_usd == "" || eta_port_discharge == "" || agente_aduanal == "" || pedimento_a1 == "" || pedimento_r1_1er == "" || motivo_rectificacion_1er == "" || pedimento_r1_2do == "" || motivo_rectificacion_2do == "" || fecha_recepcion_doc == "" || naviera == "" || fecha_revalidacion == "" || fecha_previo_destino == "" || fecha_resultado_previo == "" || proforma_final == "" || co_pref_arancelaria == "" || aplic_pref_arancelaria == "" || req_uva == "" || req_ca == "" || fecha_recepcion_ca == "" || num_constancia_ca == "" || monto_ca == "" || fecha_doc_completos == "" || fecha_pago_pedimento == "" || fecha_solicitud_transporte == "" || fecha_modulacion == "" || modalidad == "" || resultado_modulacion == "" || fecha_reconocimiento == "" || fecha_liberacion == "" || sello_final == "" || motivo_atraso == "" || fy == "" || no_bultos == "" || peso_kg == "" || transferencia == "" || fecha_inicio_etiquetado == "" || fecha_termino_etiquetado == "" || hora_termino_etiquetado == "" || proveedor == "" || proveedor_carga == "") {
                disabledOption = "false";
            }
            if (permiso === "Si") {
                if (fecha_envio == "" || fecha_recepcion_perm == "" || fecha_activacion_perm == "" || fecha_permisos_aut == "") {
                    disabledOption = "false";
                }
            }
        } else if (idAgenteAduanal === "4005") { //RECHY
            if (pais_origen == "" || valor_usd == "" || eta_port_discharge == "" || agente_aduanal == "" || pedimento_a1 == "" || pedimento_r1_1er == "" || motivo_rectificacion_1er == "" || pedimento_r1_2do == "" || motivo_rectificacion_2do == "" || fecha_recepcion_doc == "" || fecha_previo_destino == "" || fecha_resultado_previo == "" || proforma_final == "" || co_pref_arancelaria == "" || aplic_pref_arancelaria == "" || req_uva == "" || req_ca == "" || fecha_recepcion_ca == "" || num_constancia_ca == "" || monto_ca == "" || fecha_doc_completos == "" || fecha_pago_pedimento == "" || fecha_solicitud_transporte == "" || fecha_modulacion == "" || modalidad == "" || resultado_modulacion == "" || fecha_reconocimiento == "" || fecha_liberacion == "" || motivo_atraso == "" || fy == "") {
                disabledOption = "false";
            }
            if (permiso === "Si") {
                if (fecha_envio == "" || fecha_recepcion_perm == "" || fecha_activacion_perm == "" || fecha_permisos_aut == "") {
                    disabledOption = "false";
                }
            }
        }

        var selector = document.getElementById("estatus_operacion[" + i + "]");
        selector.options[19].disabled = disabledOption;

     }

    function pedimento(dateEtaPortDischarge, i){
        
        /*Calendarios: etaPortDischarge/pagoPedimento/modulacion*/
        if(dateEtaPortDischarge !== ""){
            
            $('.datepicker-pedimento'+i).flatpickr({
                dateFormat: 'm/d/Y',
                minDate: dateEtaPortDischarge,
                maxDate: new Date().fp_incr(50)
            });
        
            $('.datepicker-modulacion'+i).flatpickr({
                dateFormat: 'm/d/Y'
            });
        }
    }            

    function modulacion(i){
        
        let fecha_pago_pedimento = document.getElementById("fecha_pago_pedimento["+i+"]").value;
        $('.datepicker-modulacion'+i).flatpickr({
            dateFormat: 'm/d/Y',
            minDate: fecha_pago_pedimento,
            maxDate: new Date().fp_incr(50)
        });
    }

    function logExcel(){
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
      
    async function loadCss() {
        // Create a new script element
        var script = document.createElement('script');

        // Set the source of the script to the same script you want to reload
        script.src = '../lib/calendarios/css/flatpickr.min.css';

        // Append the script to the body
        document.body.appendChild(script);
    }
    
    async function loadJsPicker(){
        $('.datepicker').flatpickr({
            dateFormat: 'm/d/Y',
            onClose: function (dateStr, instance) {
                instance.setDate(dateStr, true, 'm/d/Y');
            }
        });
    }
    
    //Cargar función para formato de fechas 
    $('.datepicker').flatpickr({
        dateFormat: 'm/d/Y'
    });
   
    /*$("#upload_file").click(function () {
       mostrarOcultarDiv('divAMostrarOcultar', false);
    });*/
    
    