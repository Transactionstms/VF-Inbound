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

        urlCustoms += "&evento" + i + "=" + evento +
                "&shipmentId" + i + "=" + shipmentId +
                "&containerId" + i + "=" + containerId +
                "&referenciaAA" + i + "=" + referenciaAA +
                "&prioridad" + i + "=" + prioridad +
                "&loadTypeFinal" + i + "=" + loadTypeFinal +
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

        let urlData = encodeURI("../InsertarCustomsForms?idAgenteAduanal=" + idAgenteAduanal + "&numCustoms=" + cont + urlCustoms);

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

    setTimeout(await ocultarLoader, 2);
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

async function consultarCustoms(idAgenteAduanal, filterType, caramelo) {

    try {
        const response = await fetch("../ConsultarCustoms?AgentType=" + idAgenteAduanal + "&filterType=" + filterType + "&id=" + caramelo);
        if (!response.ok) {
            throw new Error('Error en la solicitud');
        }

        const data = await response.text();
        document.getElementById('AddTableDetalleCustom').innerHTML = data;

    } catch (error) {
        console.error(error);
    }

    setTimeout(await ocultarLoader, 2);

}

/*--------------------------------------------------------------------------
 FUNCIONES - REGLAS DE NEGOCIO
 --------------------------------------------------------------------------*/
function pedimento(dateEtaPortDischarge, i) {

    if (dateEtaPortDischarge !== "") {
        document.getElementById("fecha_pago_pedimento[" + i + "]").innerHTML = dateEtaPortDischarge;
    }
}

function cleanPedimento_r1_1er(pedimento, i) {
    let color = "";

    if (pedimento.replace(/\s/g, "") === "") {
        document.getElementById("motivo_rectificacion_1er[" + i + "]").innerHTML = "";
        color = "#ced4da";
    } else {
        color = "#CC9D77";
    }

    if (pedimento != "") {
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

/*--------------------------------------------------------------------------
 FUNCIONES - BUSCADOR GENERAL
 --------------------------------------------------------------------------*/
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

/*--------------------------------------------------------------------------
 FUNCIONES - BUSCADORES MULTISELECT
 --------------------------------------------------------------------------*/
async function FiltrerData(tipoFiltro) {

    await mostrarLoader();
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

    await consultarCustoms(idAgenteAduanal, tipoFiltro, selected);

}

function cleanMultiselects() {
    //$('.selectpicker').selectpicker('deselectAll');
    FiltrerData("0");
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
}

/*function changeColor(element) {
  element.style.backgroundColor = "red";
}

var td = document.getElementById("myTd");
td.addEventListener("click", function() { changeColor(this); });*/


// Obtén la referencia al td que deseas bloquear/habilitar
var miCelda = document.getElementById("miCelda");

// Función de manejo de eventos
function clicEnCelda() {
  alert("Celda clicada");
}

// Bloquear el evento
function bloquearEvento() {
  miCelda.removeEventListener("click", clicEnCelda);
}

// Habilitar el evento
function habilitarEvento() {
  miCelda.addEventListener("click", clicEnCelda);
}

// Bloquear el evento al cargar la página
bloquearEvento();

// Después de un cierto evento (por ejemplo, un botón), habilitar el evento
document.getElementById("miBotonHabilitar").addEventListener("click", function() {
  habilitarEvento();
});
