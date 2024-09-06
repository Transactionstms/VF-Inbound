/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*--------------------------------------------------------------------------
 FUNCIONES - MODALS ELEMENTOS HTML/CONTROLADORES
 --------------------------------------------------------------------------*/

function show_eta_port_discharge(data, i) {
    $("#modal_show_eta_port_discharge").modal("show");
    loadJsPicker();
    document.getElementById("eta_port_discharge").value = data;
    contModals = i;
    
}

function hide_eta_port_discharge(value) {
    
    let data = formatoFecha(value);
    
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

function show_fecha_recepcion_doc(data, i) {
    $("#modal_fecha_recepcion_doc").modal("show");
    loadJsPicker();
    document.getElementById("fecha_recepcion_doc").value = data;
    contModals = i;
}

function hide_fecha_recepcion_doc(value) {
    let data = formatoFecha(value);
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

function hide_fecha_revalidacion(value) {
    let data = formatoFecha(value);
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

function hide_fecha_previo_origen(value) {
    let data = formatoFecha(value);
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

function hide_fecha_previo_destino(value) {
    let data = formatoFecha(value);
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

function hide_fecha_resultado_previo(value) {
    let data = formatoFecha(value);
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

function hide_proforma_final(value) {
    let data = formatoFecha(value);
    contModals;
    document.getElementById("proforma_final[" + contModals + "]").innerHTML = data;
    $("#modal_proforma_final").modal("hide");
    //parametrizacionValoresEvento("proforma_final",contModals); 
}

function show_permiso(i) {
    
    $("#modal_permiso").modal("show");
    let res = document.getElementById("permiso[" + i + "]").innerHTML;
    contModals = i;

    if (res == "SI") {
        check1_permiso = true;
        check2_permiso = false;
    } else if (res == "NO") {
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
    contModals;
    
    if (data == "SI") {
        check1_permiso = true;
        check2_permiso = false;
    } else if (data == "NO") {
        check1_permiso = false;
        check2_permiso = true;   
    }
    
    document.getElementById("permiso1").checked = check1_permiso;
    document.getElementById("permiso2").checked = check2_permiso;

    document.getElementById("permiso[" + contModals + "]").innerHTML = data;
    cleanPermiso(data, contModals);
    $("#modal_permiso").modal("hide");
}

function show_fecha_envio(data, nameCelda, i) {
    
    let resultado = blocked_cell('permiso',i);
    
    if(resultado){
        toggleNotEdit(nameCelda,i);
    }else{
        $("#modal_fecha_envio").modal("show");
        toggleEdit(nameCelda,i);

        loadJsPicker();
        document.getElementById("fecha_envio").value = data;
        contModals = i;
    }
}

function hide_fecha_envio(value) {
    let data = formatoFecha(value);
    contModals;
    document.getElementById("fecha_envio[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_envio").modal("hide");
    //parametrizacionValoresEvento("fecha_envio",contModals); 
}

function show_fecha_recepcion_perm(data, nameCelda, i) {
    
    let resultado = blocked_cell('permiso',i);
    
    if(resultado){
        toggleNotEdit(nameCelda,i);
    }else{
        $("#modal_fecha_recepcion_perm").modal("show");
        toggleEdit(nameCelda,i);

        loadJsPicker();
        document.getElementById("fecha_recepcion_perm").value = data;
        contModals = i;
    }
}

function hide_fecha_recepcion_perm(value) {
    let data = formatoFecha(value);
    contModals;
    document.getElementById("fecha_recepcion_perm[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_recepcion_perm").modal("hide");
    //parametrizacionValoresEvento("fecha_recepcion_perm",contModals); 
}

function show_fecha_activacion_perm(data, nameCelda, i) {
    
    let resultado = blocked_cell('permiso',i);
    
    if(resultado){
        toggleNotEdit(nameCelda,i);
    }else{
        $("#modal_fecha_activacion_perm").modal("show");
        toggleEdit(nameCelda,i);

        loadJsPicker();
        document.getElementById("fecha_activacion_perm").value = data;
        contModals = i;
    }
    
}

function hide_fecha_activacion_perm(value) {
    let data = formatoFecha(value);
    contModals;
    document.getElementById("fecha_activacion_perm[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_activacion_perm").modal("hide");
    //parametrizacionValoresEvento("fecha_activacion_perm",contModals); 
}

function show_fecha_permisos_aut(data, nameCelda, i) {
    
    let resultado = blocked_cell('permiso',i);
    
    if(resultado){
        toggleNotEdit(nameCelda,i);
    }else{
        $("#modal_fecha_permisos_aut").modal("show");
        toggleEdit(nameCelda,i);

        loadJsPicker();
        document.getElementById("fecha_permisos_aut").value = data;
        contModals = i;
    }
}

function hide_fecha_permisos_aut(value) {
    let data = formatoFecha(value);
    contModals;
    document.getElementById("fecha_permisos_aut[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_permisos_aut").modal("hide");
    //parametrizacionValoresEvento("fecha_permisos_aut",contModals); 
}

function show_co_pref_arancelaria(i) {
    $("#modal_co_pref_arancelaria").modal("show");
    let res = document.getElementById("co_pref_arancelaria[" + i + "]").innerHTML;
    contModals = i;

    if (res == "SI") {
        check1_co_pref_arancelaria1 = true;
        check2_co_pref_arancelaria1 = false;
    } else if (res == "NO") {
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

    if (data == "SI") {
        check1_co_pref_arancelaria1 = true;
        check2_co_pref_arancelaria1 = false;
    } else if (data == "NO") {
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
    
    if (data == "NO") {
        document.getElementById("aplic_pref_arancelaria[" + contModals + "]").innerHTML = "NO";
    }else{
        document.getElementById("aplic_pref_arancelaria[" + contModals + "]").innerHTML = "";
    }
    
}

function show_aplic_pref_arancelaria(i) {
    $("#modal_aplic_pref_arancelaria").modal("show");
    let res = document.getElementById("aplic_pref_arancelaria[" + i + "]").innerHTML;
    contModals = i;

    if (res == "SI") {
        check1_aplic_pref_arancelaria1 = true;
        check2_aplic_pref_arancelaria1 = false;
    } else if (res == "NO") {
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

    if (data == "SI") {
        check1_aplic_pref_arancelaria1 = true;
        check2_aplic_pref_arancelaria1 = false;
    } else if (data == "NO") {
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

    if (res == "SI") {
        check1_req_uva = true;
        check2_req_uva = false;
    } else if (res == "NO") {
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

    if (data == "SI") {
        check1_req_uva = true;
        check2_req_uva = false;
    } else if (data == "NO") {
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

    if (res == "SI") {
        check1_req_ca = true;
        check2_req_ca = false;
    } else if (res == "NO") {
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

    contModals;
    
    if (data == "SI") {
        check1_req_ca = true;
        check2_req_ca = false;
    } else if (data == "NO") {
        check1_req_ca = false;
        check2_req_ca = true;
    }
    
    document.getElementById("req_ca1").checked = check1_req_ca;
    document.getElementById("req_ca2").checked = check2_req_ca;
    
    $("#modal_req_ca").modal("hide");
    document.getElementById("req_ca[" + contModals + "]").innerHTML = data;
    cleanRequerimientoCA(data, contModals);
}

function show_fecha_recepcion_ca(data, nameCelda, i) {
    
    let resultado = blocked_cell('req_ca',i);
    
    if(resultado){
        toggleNotEdit(nameCelda,i);
    }else{
        $("#modal_fecha_recepcion_ca").modal("show");
        toggleEdit(nameCelda,i);

        loadJsPicker();
        document.getElementById("fecha_recepcion_ca").value = data;
        contModals = i;
    }
}

function hide_fecha_recepcion_ca(value) {
    let data = formatoFecha(value);
    contModals;
    document.getElementById("fecha_recepcion_ca[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_recepcion_ca").modal("hide");
    //parametrizacionValoresEvento("fecha_recepcion_ca",contModals); 
}

function show_num_constancia_ca(nameCelda,i){
    
    let resultado = blocked_cell('req_ca',i);
    
    if(resultado){
        toggleNotEdit(nameCelda,i);
    }else{
        toggleEdit(nameCelda,i);
    }
}

function show_monto_ca(nameCelda,i){
    
    let resultado = blocked_cell('req_ca',i);
    
    if(resultado){
        toggleNotEdit(nameCelda,i);
    }else{
        toggleEdit(nameCelda,i);
    }
}

function show_fecha_doc_completos(data, i) {
    $("#modal_fecha_doc_completos").modal("show");
    loadJsPicker();
    document.getElementById("fecha_doc_completos").value = data;
    contModals = i;
}

function hide_fecha_doc_completos(value) {
    let data = formatoFecha(value);
    contModals;
    document.getElementById("fecha_doc_completos[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_doc_completos").modal("hide");
    parametrizacionValoresEvento("fecha_doc_completos",contModals); 
}

function show_fecha_pago_pedimento(i) {
    
    $("#modal_fecha_pago_pedimento").modal("show");
    let res = document.getElementById("fecha_pago_pedimento[" + i + "]").innerHTML;
    document.getElementById("fecha_pago_pedimento").innerHTML = res;

    //Conversión de fecha numerica a texto:
    var fechaConvertidaLiberacion = convertirFechaLiberacion(res);
    if (fechaConvertidaLiberacion) {

         // Aumentar un día hábil
         var f1 = addBusinessDay(fechaConvertidaLiberacion);
        
        $('.datepicker-pedimento').flatpickr({
            dateFormat: 'm/d/Y',
            minDate: f1,
            inline: true
        });

    }

    contModals = i;
}

function hide_fecha_pago_pedimento(value) {
    let data = formatoFecha(value);
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

function hide_fecha_solicitud_transporte(value) {
    let data = formatoFecha(value);
    contModals;
    document.getElementById("fecha_solicitud_transporte[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_solicitud_transporte").modal("hide");
    parametrizacionValoresEvento("fecha_solicitud_transporte",contModals); 
}

function show_fecha_modulacion(i) {
    
    $("#modal_fecha_modulacion").modal("show");
    let res = document.getElementById("fecha_modulacion[" + i + "]").innerHTML;
    document.getElementById("fecha_modulacion").innerHTML = res;

    //Conversión de fecha texto a numerica:
    var fechaConvertidaLiberacion = convertirFechaLiberacion(res);
    if (fechaConvertidaLiberacion) {
     
         // Aumentar un día hábil
         var f1 = addBusinessDay(fechaConvertidaLiberacion);
         
        $('.datepicker-modulacion').flatpickr({
            dateFormat: 'm/d/Y',
            minDate: f1,
            inline: true
        });

    }
    contModals = i;
}

function hide_fecha_modulacion(value) {
    let data = formatoFecha(value);
    contModals;
    document.getElementById("fecha_modulacion[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_modulacion").modal("hide");
    parametrizacionValoresEvento("fecha_modulacion",contModals); 
}

function show_modalidad(i) {
    $("#modal_modalidad").modal("show");
    let res = document.getElementById("modalidad[" + i + "]").innerHTML;
    contModals = i;

    if (res == "CAMION") {
        check1_modalidad = true;
        check2_modalidad = false;
    } else if (res == "TREN") {
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

    if (data == "CAMION") {
        check1_modalidad = true;
        check2_modalidad = false;
    } else if (data == "TREN") {
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

    if (res == "VERDE") {
        check1_resultado_modulacion = true;
        check2_resultado_modulacion = false;
    } else if (res == "ROJO") {
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

    if (data == "VERDE") {
        check1_resultado_modulacion = true;
        check2_resultado_modulacion = false;
    } else if (data == "ROJO") {
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

function hide_fecha_reconocimiento(value) {
    let data = formatoFecha(value);
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

function hide_fecha_liberacion(value) {
    let data = formatoFecha(value);
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

function hide_fecha_retencion_aut(value) {
    let data = formatoFecha(value);
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

function hide_fecha_liberacion_aut(value) {
    let data = formatoFecha(value);
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

function hide_llegada_a_nova(value) {
    let data = formatoFecha(value);
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

function hide_llegada_a_globe_trade_sd(value) {
    let data = formatoFecha(value);
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

function hide_fecha_archivo_m(value) {
    let data = formatoFecha(value);
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

function hide_fecha_solicit_manip(value) {
    let data = formatoFecha(value);
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

function hide_fecha_vencim_manip(value) {
    let data = formatoFecha(value);
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

function hide_fecha_confirm_clave_pedim(value) {
    let data = formatoFecha(value);
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

function hide_fecha_recep_increment(value) {
    let data = formatoFecha(value);
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

function hide_fecha_vencim_inbound(value) {
    let data = formatoFecha(value);
    contModals;
    document.getElementById("fecha_vencim_inbound[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_vencim_inbound").modal("hide");
    parametrizacionValoresEvento("fecha_vencim_inbound",contModals);
}

function show_transferencia(i) {
    $("#modal_transferencia").modal("show");
    let res = document.getElementById("transferencia[" + i + "]").innerHTML;
    contModals = i;

    if (res == "SI") {
        check1_transferencia = true;
        check2_transferencia = false;
    } else if (res == "NO") {
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

    if (data == "SI") {
        check1_transferencia = true;
        check2_transferencia = false;
    } else if (data == "NO") {
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

function hide_fecha_inicio_etiquetado(value) {
    let data = formatoFecha(value);
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

function hide_fecha_termino_etiquetado(value) {
    let data = formatoFecha(value);
    contModals;
    document.getElementById("fecha_termino_etiquetado[" + contModals + "]").innerHTML = data;
    $("#modal_fecha_termino_etiquetado").modal("hide");
    parametrizacionValoresEvento("fecha_termino_etiquetado",contModals);
}

function blocked_cell(nameCelda, i){
    let option = document.getElementById(nameCelda + "[" + i + "]").innerHTML;
    let blocked;
    
    if(option === "SI"){
        blocked = false;
    }else{
        blocked = true;
    }
    
    return blocked;
}

function toggleEdit(nameCelda, i) {
    
    document.getElementById(nameCelda + "[" + i + "]").addEventListener('click', function (event) {
        
        document.getElementById(nameCelda + "[" + i + "]").style.backgroundColor = "#FFFFFF";

        // No event.preventDefault() here
        var miCelda = document.getElementById(nameCelda + "[" + i + "]");
        miCelda.contentEditable = 'true';
    });
}

function toggleNotEdit(nameCelda, i) {

    document.getElementById(nameCelda + "[" + i + "]").addEventListener('click', function (event) {
        
        // Prevent the default behavior of the click event (e.g., navigating to a new page)
        event.preventDefault();

        document.getElementById(nameCelda + "[" + i + "]").style.backgroundColor = "#F8F7F7";
        
        var miCelda = document.getElementById(nameCelda + "[" + i + "]");
        miCelda.contentEditable = 'false';
    });
}

function handleClick(event) {
    alert('Clic detectado en la celda');
  }

function addBusinessDay(date) {
    const newDate = new Date(date);
    do {
        newDate.setDate(newDate.getDate() + 1);
    } while (newDate.getDay() === 0 || newDate.getDay() === 6 || isHoliday(newDate));
    return newDate;
}
	
function isHoliday(date) {
    // Lista de días festivos en México
    const holidays = [
        new Date(date.getFullYear(), 0, 1),       // Año Nuevo (1 de enero)
        new Date(date.getFullYear(), 1, 5),       // Día de la Constitución (5 de febrero)
        new Date(date.getFullYear(), 2, 21),      // Natalicio de Benito Juárez (21 de marzo)
        new Date(date.getFullYear(), 4, 1),       // Día del Trabajo (1 de mayo)
        new Date(date.getFullYear(), 8, 16),      // Día de la Independencia (16 de septiembre)
        new Date(date.getFullYear(), 10, 2),      // Día de los Muertos (2 de noviembre)
        new Date(date.getFullYear(), 11, 12),     // Día de la Virgen de Guadalupe (12 de diciembre)
        new Date(date.getFullYear(), 11, 25)      // Navidad (25 de diciembre)
    ];

    // Verificar si la fecha proporcionada es un día festivo
    return holidays.some(holiday => holiday.toDateString() === date.toDateString());
}
		
