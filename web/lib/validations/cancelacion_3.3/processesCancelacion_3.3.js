/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let contadorFacturas;
let posicion;
let carameloSAT;
let txt_cancelacion = "";

//Parametros: Detalle de Factura
let idFactura;
let serie;
let folio;
let rfcReceptor;
let totalGloblal;
let uuidPrincipal;
let folioFiscal;
let fechaEmision;
let fechaTimbrado;

//Parametros: Cancelación
let uuidProvicional;
let uuidObtenido;
let id_motivo;
let urlProvicional = "";

function cliente(clienteId) {
    fetch("../ConsultarFacturas?idCliente=" + clienteId, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                document.getElementById('detalle_cfdi').innerHTML = data;

                div = document.getElementById('search');
                div.style.display = '';

            }).catch(error => console.log(error));
}

function consultarEstatusSATCFDI(id_factura, fact_serie, fact_folio, rfc_emisor, rfc_receptor, total_globlal, uuid, folio_fiscal, fecha_emision, fecha_timbrado, emails) {

    idFactura = id_factura;
    serie = fact_serie;
    folio = fact_folio;
    rfcEmisor = rfc_emisor;
    rfcReceptor = rfc_receptor;
    totalGloblal = total_globlal;
    uuidPrincipal = uuid;
    folioFiscal = folio_fiscal;
    fechaEmision = fecha_emision;
    fechaTimbrado = fecha_timbrado;
    correos = emails;

    //Servicio SAT: Consultar Estatus del CFDI
    const Http = new XMLHttpRequest();
    const urlSAT = 'https://www.tacts.mx/consultar/consultarSat.jsp?re=' + rfc_emisor + '&rr=' + rfc_receptor + '&tt=' + total_globlal + '&id=' + uuid + '&fe=' + folio_fiscal;

    Http.open("GET", urlSAT);
    Http.send();

    Http.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            const txt = Http.responseText;
            const obj = JSON.parse(txt);
            console.log(txt);
            console.log(obj);
            carameloSAT = obj.CodigoEstatus + "*" + obj.EsCancelable + "*" + obj.EstatusCancelacion + "*" + obj.Estado + "*" + obj.ValidacionEFOS;
            openModalCancelacionCFDI(carameloSAT);
        }
    }
    console.log("Url SAT: " + urlSAT);
}

function openModalCancelacionCFDI(dataSAT) {

    //Detalle General: Factura
    document.getElementById("txtUuid").innerHTML = uuidPrincipal;
    document.getElementById("txtSerie").innerHTML = serie;
    document.getElementById("txtFechaEmision").innerHTML = fechaEmision;
    document.getElementById("txtFolio").innerHTML = folio;
    document.getElementById("txtFechaTimbrado").innerHTML = fechaTimbrado;
    document.getElementById("txtTotal").innerHTML = totalGloblal;
    document.getElementById("txtEmails").innerHTML = correos;

    let ob = dataSAT.split("*");
    let estado = ob[3];
    let validacionEFOS = ob[4];
    let colors = "";

    //Mostrar Formulario: Cancelación
    if (validacionEFOS == "200") {
        div = document.getElementById('flotantePrincipal');
        div.style.display = '';
        colors = "#1fa155";
    } else {
        div = document.getElementById('flotantePrincipal');
        div.style.display = 'none';
        colors = "#e64412";
    }

    //Detalle General: SAT
    document.getElementById("estatus_sat").style.backgroundColor = colors;
    document.getElementById("estatus_sat").innerHTML = "&nbsp;" + estado + "&nbsp;";
    document.getElementById("descripcion_sat").innerHTML = dataSAT;

    $("#modalDetalleCancelarCfdi").modal("show");
}

function describirCancelacion(id) {

    if (id == "01") {
        txt_cancelacion = "Este supuesto aplica cuando la factura generada contiene un error en la clave del producto, valor unitario, descuento o cualquier otro dato, por lo que se debe reexpedir.";
    } else if (id == "02") {
        txt_cancelacion = "Este supuesto aplica cuando la factura generadaa contiene algún error en la clave del producto, valor unitario, descuento o cualquier otro dato y no se requiera relacionar con otra factura generada.";
    } else if (id == "03") {
        txt_cancelacion = "Este supuesto aplica cuando se facturó una operación que no se concreta.";
    } else if (id == "04") {
        txt_cancelacion = "Este supuesto aplica cuando se incluye una venta en la factura global de operaciones con el público en general y posterior a ello, el cliente solicita su factura nominativa.";
    }

    document.getElementById("descripcion_cancelacion").innerHTML = txt_cancelacion;
}

function servicioCancelacion(tipoWs) {

    if (tipoWs == "3") {
        consultarCFDIrelacionados();
    } else {
        div = document.getElementById('flotante1');
        div.style.display = '';
        div = document.getElementById('flotante2');
        div.style.display = 'none';
    }

}

function consultarCFDIrelacionados() {

    fetch("../consultarCFDIrelacionados?idFacturaPadre=" + idFactura, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                document.getElementById('uuid_relacionados').innerHTML = data;

                div = document.getElementById('flotante1');
                div.style.display = 'none';
                div = document.getElementById('flotante2');
                div.style.display = '';

            }).catch(error => console.log(error));

}

function AddCancelacion() {

    let id_servicio = document.getElementById("id_servicio").value;
    let motivo = document.getElementById("id_motivo").value;

    if (id_servicio == "3") {
        uuidProvicional = document.getElementById("uuid_relacionado").value;
    } else {
        uuidProvicional = document.getElementById("uuid_sustitucion").value;
    }

    if (id_servicio == null || id_servicio == 0) {
        swal("Error", "Seleccione el servicio de cancelación", "error");
        return false;
    }
    if (motivo == null || motivo == 0) {
        swal("Error", "Seleccione el motivo de cancelación", "error");
        return false;
    }
    
    showPreloader();

    //Servicio SIFEI: Generar cancelación del CFDI
    const Http = new XMLHttpRequest();

    if (id_servicio == "1") {
        if (motivo == "01") {
            uuidObtenido = uuidProvicional;
        } else {
            uuidObtenido = "";
        }
        urlProvicional = "https://www.tacts.mx/cancelacion/getCancelar.jsp?ban=" + id_servicio + "&cve=" + cbdiv_id + "&rfc=" + rfcEmisor + "&uuids=" + uuidPrincipal + "&banSat=" + motivo + "&fs=" + uuidObtenido;

    } else if (id_servicio == "2") {
        if (motivo == "01") {
            uuidObtenido = uuidProvicional;
        } else {
            uuidObtenido = "";
        }
        urlProvicional = "https://www.tacts.mx/cancelacion/getCancelar.jsp?ban=" + id_servicio + "&cve=" + cbdiv_id + "&rfc=" + rfcEmisor + "&uuids=" + uuidPrincipal + "&banSat=" + motivo + "&fs=" + uuidObtenido;

    } else if (id_servicio == "3") {
        urlProvicional = "https://www.tacts.mx/cancelacion/getCancelar.jsp?ban=" + id_servicio + "&cve=" + cbdiv_id + "&rfcEmisor=" + rfcEmisor + "&rfcReceptor=" + rfcReceptor + "&uuids=" + uuidObtenido;
    }

    const urlSifei = urlProvicional;

    Http.open("GET", urlSifei);
    Http.send();

    Http.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            let respuesta = Http.responseText;
            let palabra = "200";
            let index = respuesta.indexOf(palabra);

            hidePreloader();

            if (index>0) {
                $("#modalDetalleCfdi").modal("hide");
                swal("", "Cancelación Exitosa", "success");
                alertclose();

                sendmail(5, 2);
            } else {
                swal("Error", "Detalle: " + respuesta, "error");
                return false;
            }

        }
    }
    console.log("Url SIFEI: " + urlSifei);
}