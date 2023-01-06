/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Parametros: Filtros/Buscador
let contadorFacturas;
let posicion;

//Contadores Tablas
let contadorFacturasPorPagar = 0;

//Parametros: Tabla Facturas por pagar
let id_documento;
let serieFactOld;
let folioFactOld;
let moneda_dr;
let metodo_de_pago_dr;
let numParcialidad;
let saldoAnterior;
let saldoPagado;
let saldoPendiente;
let importeSaldoInsoluto;
let equivalenciaDr;
let ObjetoImpDr;
let url = "";

function viewVisor(id) {
    document.getElementById('pdf').src = "<../MostrarPDF_FILE?id=" + id;
    $("#modalVistaPreviaCfdi").modal("show");
}

function seleccionar(id) {
    if (document.getElementById("checkFactura[" + id + "]").checked) {
        //swal("success", "seleccionado: " + id, "success");
    } else {
        //swal("Error", "seleccionado: " + id, "error");
    }
}

function AddComplementoPago(tipo_registro) {

    //Parametros - Encabezado principal:
    let cliente_id = document.getElementById("cliente_id").value;
    let montoPago = document.getElementById("montoPago").value;
    let moneda_id = document.getElementById("moneda_id").value;
    let fecha_emision = document.getElementById("fecha_emision").value;
    let hora_emision = document.getElementById("hora_emision").value;
    let totalGlobal = document.getElementById("totalGlobal").value;
    contadorFacturasPorPagar = document.getElementById("contadorFacturasPorPagar").value;

    //Parametros - Información de Pago:
    serie = document.getElementById("serie").value;
    folio = document.getElementById("folio").value;
    let ctaOrdenante = document.getElementById("ctaOrdenante").value;
    let formapago = document.getElementById("formapago").value;
    let relcfdiPag = document.getElementById("relcfdiPag").value;
    let fechaPago = document.getElementById("fechaPago").value;
    let ctaBeneficiarias = document.getElementById("ctaBeneficiarias").value;
    let numOperacion = document.getElementById("numOperacion").value;
    let regimenFiscal = document.getElementById("regimenFiscal").value;
    let horaEmision = document.getElementById("horaEmision").value;

    //Validaciones - Encabezado principal:
    if (cliente_id == "") {
        swal("Error", "Nombre de cliente inválido", "error");
        return false;
    }
    if (montoPago == "") {
        swal("Error", "Monto de pago inválido", "error");
        return false;
    }
    if (moneda_id == null || moneda_id == 0) {
        swal("Error", "Seleccione tipo moneda", "error");
        return false;
    }
    if (fecha_emision == "") {
        swal("Error", "Ingrese Fecha Emisión", "error");
        return false;
    }
    if (hora_emision == "") {
        swal("Error", "Ingrese Hora Emisión", "error");
        return false;
    }
    
    //Validaciones - Información de Pago:
    if (serie == null || serie == 0) {
        swal("Error", "Seleccione una serie", "error");
        return false;
    }
    if (folio == null || folio == 0) {
        swal("Error", "Seleccione un folio", "error");
        return false;
    }
    if (ctaOrdenante == null || ctaOrdenante == 0) {
        swal("Error", "Seleccione cuenta ordenante", "error");
        return false;
    }
    if (formapago == null || formapago == 0) {
        swal("Error", "Seleccione una forma de pago", "error");
        return false;
    }
    if (fechaPago == null || fechaPago == 0) {
        swal("Error", "Seleccione una fecha de pago", "error");
        return false;
    }
    if (horaEmision == null || horaEmision == 0) {
        swal("Error", "Seleccione una hora de pago", "error");
        return false;
    }
    if (ctaBeneficiarias == null || ctaBeneficiarias == 0) {
        swal("Error", "Seleccione cuenta beneficiaria", "error");
        return false;
    }
    if (regimenFiscal == null || regimenFiscal == 0) {
        swal("Error", "Seleccione un regimén", "error");
        return false;
    }

    //Validaciones Desglose del Pago:
    if (contadorFacturasPorPagar == 0) {
        swal("Error", "Agregue al menos un factura", "error");
        return false;
    }

    //Parametros - Desglose del pago/Facturas por pagar
    for (let p = 0; p < contadorFacturasPorPagar; p++) {

        let temp1_ = "id_documento[" + p + "]";
        let temp2_ = "serieFactOld[" + p + "]";
        let temp3_ = "folioFactOld[" + p + "]";
        let temp4_ = "moneda_dr[" + p + "]";
        let temp5_ = "metodo_de_pago_dr[" + p + "]";
        let temp6_ = "numParcialidad[" + p + "]";
        let temp7_ = "saldoAnterior[" + p + "]";
        let temp8_ = "saldoPagado[" + p + "]";
        let temp9_ = "saldoPendiente[" + p + "]";
        let temp10_ = "importeSaldoInsoluto[" + p + "]";
        let temp11_ = "equivalenciaDr[" + p + "]";
        let temp12_ = "ObjetoImpDr[" + p + "]";

        id_documento = document.getElementById(temp1_).value;
        serieFactOld = document.getElementById(temp2_).value;
        folioFactOld = document.getElementById(temp3_).value;
        moneda_dr = document.getElementById(temp4_).value;
        metodo_de_pago_dr = document.getElementById(temp5_).value;
        numParcialidad = document.getElementById(temp6_).value;
        saldoAnterior = document.getElementById(temp7_).value;
        saldoPagado = document.getElementById(temp8_).value;
        saldoPendiente = document.getElementById(temp9_).value;
        importeSaldoInsoluto = document.getElementById(temp10_).value;
        equivalenciaDr = document.getElementById(temp11_).value;
        ObjetoImpDr = document.getElementById(temp12_).value;


        url += "&id_documento[" + p + "]=" + id_documento +
                "&serieFactOld[" + p + "]=" + serieFactOld +
                "&folioFactOld[" + p + "]=" + folioFactOld +
                "&moneda_dr[" + p + "]=" + moneda_dr +
                "&metodo_de_pago_dr[" + p + "]=" + metodo_de_pago_dr +
                "&numParcialidad[" + p + "]=" + numParcialidad +
                "&saldoAnterior[" + p + "]=" + saldoAnterior +
                "&saldoPagado[" + p + "]=" + saldoPagado +
                "&saldoPendiente[" + p + "]=" + saldoPendiente +
                "&importeSaldoInsoluto[" + p + "]=" + importeSaldoInsoluto +
                "&equivalenciaDr[" + p + "]=" + equivalenciaDr +
                "&ObjetoImpDr[" + p + "]=" + ObjetoImpDr;
    }

    showPreloader();

    fetch("../InsertarComplementoPago?cliente_id=" + cliente_id + "&montoPago=" + montoPago + "&moneda_id=" + moneda_id + "&fecha_emision=" + fecha_emision + "&hora_emision=" + hora_emision + "&totalGlobal=" + totalGlobal + "&serie=" + serie + "&folio=" + folio + "&ctaOrdenante=" + ctaOrdenante + "&formapago=" + formapago + "&relcfdiPag=" + relcfdiPag + "&fechaPago=" + fechaPago + "&ctaBeneficiarias=" + ctaBeneficiarias + "&numOperacion=" + numOperacion + "&regimenFiscal=" + regimenFiscal + "&horaEmision=" + horaEmision + "&tipoEmision=" + tipo_registro + "&contadorFacturasPorPagar=" + contadorFacturasPorPagar + url, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {

                let rest = data.split("*");
                let idRegistro = rest[0];
                let timbrado = rest[1];

                if (idRegistro == "0") {
                    swal("Error al generar", "Verique la información ingresada", "error");
                    alertclose();
                    return false;
                    hidePreloader();
                } else {

                    if (timbrado == "1") {  //No timbrado

                        //Generación de PDF'S
                        visorPdfComplemento(2);       // (1 = No Marcado de ruta pdf) | (2= Marcado de ruta pdf)

                        //Almacenamiento de PDF'S
                        savegeneratepdf(tipoGeneracion);          //tipoGeneracion(complemento de pago)

                        //Envío de PDF'S
                        setTimeout(function () {
                            sendmail(tipoGeneracion, timbrado); /*tipoGeneracion(complemento de pago)|tipoTimbrado(no XML)*/
                        }, 2000);

                    } else if (timbrado == "2") {  //Si timbrado

                        Api_ComplementoPago(idRegistro, 3, timbrado); //Folio consecutivo base(api)|tipoGeneracion(complemento de pago)|tipoTimbrado(si XML)
                    }

                }

            }).catch(error => console.log(error));
}

function cliente(clienteId) {
    fetch("../ConsultarClienteFac?clienteId=" + clienteId, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                let input = data.split("*");
                correos = input[3]; //correo1 || correo2 || correo
            }).catch(error => console.log(error));
}

function consultarFacturas(idFacturas) {
    fetch("../ConsultarFacturasPorPagar?idFacturas=" + idFacturas, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                document.getElementById('AddTableFacturas').innerHTML = data;
            }).catch(error => console.log(error));
}

function visorPdfComplemento(tipo) {

    //Parametros - Encabezado principal:
    let pdf_cliente_id = document.getElementById("cliente_id").value;
    let pdf_montoPago = document.getElementById("montoPago").value;
    let pdf_moneda_id = document.getElementById("moneda_id").value;
    let pdf_fecha_emision = document.getElementById("fecha_emision").value;
    let pdf_hora_emision = document.getElementById("hora_emision").value;
    contadorFacturasPorPagar = document.getElementById("contadorFacturasPorPagar").value;

    //Parametros - Información de Pago:
    serie = document.getElementById("serie").value;
    folio = document.getElementById("folio").value;
    let pdf_ctaOrdenante = document.getElementById("ctaOrdenante").value;
    let pdf_formapago = document.getElementById("formapago").value;
    let pdf_relcfdiPag = document.getElementById("relcfdiPag").value;
    let pdf_fechaPago = document.getElementById("fechaPago").value;
    let pdf_ctaBeneficiarias = document.getElementById("ctaBeneficiarias").value;
    let pdf_numOperacion = document.getElementById("numOperacion").value;
    let pdf_regimenFiscal = document.getElementById("regimenFiscal").value;
    let pdf_horaEmision = document.getElementById("horaEmision").value;

    //Validaciones - Encabezado principal:
    if (pdf_cliente_id == "") {
        swal("Error", "Nombre de cliente inválido", "error");
        return false;
    }
    if (pdf_montoPago == "") {
        swal("Error", "Monto de pago inválido", "error");
        return false;
    }
    if (pdf_moneda_id == null || pdf_moneda_id == 0) {
        swal("Error", "Seleccione tipo moneda", "error");
        return false;
    }
    if (pdf_fecha_emision == "") {
        swal("Error", "Ingrese Fecha Emisión", "error");
        return false;
    }
    if (pdf_hora_emision == "") {
        swal("Error", "Ingrese Hora Emisión", "error");
        return false;
    }

    //Validaciones - Información de Pago:
    if (serie == null || serie == 0) {
        swal("Error", "Seleccione una serie", "error");
        return false;
    }
    if (folio == null || folio == 0) {
        swal("Error", "Seleccione un folio", "error");
        return false;
    }
    if (pdf_ctaOrdenante == null || pdf_ctaOrdenante == 0) {
        swal("Error", "Seleccione cuenta ordenante", "error");
        return false;
    }
    if (pdf_formapago == null || pdf_formapago == 0) {
        swal("Error", "Seleccione una forma de pago", "error");
        return false;
    }
    if (pdf_fechaPago == null || pdf_fechaPago == 0) {
        swal("Error", "Seleccione una fecha de pago", "error");
        return false;
    }
    if (pdf_horaEmision == null || pdf_horaEmision == 0) {
        swal("Error", "Seleccione una hora de pago", "error");
        return false;
    }
    if (pdf_ctaBeneficiarias == null || pdf_ctaBeneficiarias == 0) {
        swal("Error", "Seleccione cuenta beneficiaria", "error");
        return false;
    }
    if (pdf_regimenFiscal == null || pdf_regimenFiscal == 0) {
        swal("Error", "Seleccione un regimén", "error");
        return false;
    }

    //Validaciones Desglose del Pago:
    if (contadorFacturasPorPagar == 0) {
        swal("Error", "Agregue al menos un factura", "error");
        return false;
    }

    //Parametros - Desglose del pago/Facturas por pagar
    for (let i = 0; i < contadorFacturasPorPagar; i++) {

        let temp1 = "id_documento[" + i + "]";
        let temp2 = "serieFactOld[" + i + "]";
        let temp3 = "folioFactOld[" + i + "]";
        let temp4 = "moneda_dr[" + i + "]";
        let temp5 = "metodo_de_pago_dr[" + i + "]";
        let temp6 = "numParcialidad[" + i + "]";
        let temp7 = "saldoAnterior[" + i + "]";
        let temp8 = "saldoPagado[" + i + "]";
        let temp9 = "saldoPendiente[" + i + "]";
        let temp10 = "importeSaldoInsoluto[" + i + "]";
        let temp11 = "equivalenciaDr[" + i + "]";
        let temp12 = "ObjetoImpDr[" + i + "]";

        id_documento = document.getElementById(temp1).value;
        serieFactOld = document.getElementById(temp2).value;
        folioFactOld = document.getElementById(temp3).value;
        moneda_dr = document.getElementById(temp4).value;
        metodo_de_pago_dr = document.getElementById(temp5).value;
        numParcialidad = document.getElementById(temp6).value;
        saldoAnterior = document.getElementById(temp7).value;
        saldoPagado = document.getElementById(temp8).value;
        saldoPendiente = document.getElementById(temp9).value;
        importeSaldoInsoluto = document.getElementById(temp10).value;
        equivalenciaDr = document.getElementById(temp11).value;
        ObjetoImpDr = document.getElementById(temp12).value;


        url += "&id_documento[" + i + "]=" + id_documento +
                "&serieFactOld[" + i + "]=" + serieFactOld +
                "&folioFactOld[" + i + "]=" + folioFactOld +
                "&moneda_dr[" + i + "]=" + moneda_dr +
                "&metodo_de_pago_dr[" + i + "]=" + metodo_de_pago_dr +
                "&numParcialidad[" + i + "]=" + numParcialidad +
                "&saldoAnterior[" + i + "]=" + saldoAnterior +
                "&saldoPagado[" + i + "]=" + saldoPagado +
                "&saldoPendiente[" + i + "]=" + saldoPendiente +
                "&importeSaldoInsoluto[" + i + "]=" + importeSaldoInsoluto +
                "&equivalenciaDr[" + i + "]=" + equivalenciaDr +
                "&ObjetoImpDr[" + i + "]=" + ObjetoImpDr;
    }

    //let urlPDF=encodeURI("ReciboPagoVistaPrevia.jsp?cliente_id=" + pdf_cliente_id + "&montoPago=" + pdf_montoPago + "&moneda_id=" + pdf_moneda_id + "&fecha_emision=" + pdf_fecha_emision + "&hora_emision=" + pdf_hora_emision + "&serie=" + serie + "&folio=" + folio + "&ctaOrdenante=" + pdf_ctaOrdenante + "&formapago=" + pdf_formapago + "&relcfdiPag=" + pdf_relcfdiPag + "&fechaPago=" + pdf_fechaPago + "&ctaBeneficiarias=" + pdf_ctaBeneficiarias + "&numOperacion=" + pdf_numOperacion + "&regimenFiscal=" + pdf_regimenFiscal + "&horaEmision=" + pdf_horaEmision + "&contadorFacturasPorPagar=" + contadorFacturasPorPagar + "&tipo=" + tipo + url);           
    //console.log(urlPDF);

    fetch("ReciboPagoVistaPrevia.jsp?cliente_id=" + pdf_cliente_id + "&montoPago=" + pdf_montoPago + "&moneda_id=" + pdf_moneda_id + "&fecha_emision=" + pdf_fecha_emision + "&hora_emision=" + pdf_hora_emision + "&serie=" + serie + "&folio=" + folio + "&ctaOrdenante=" + pdf_ctaOrdenante + "&formapago=" + pdf_formapago + "&relcfdiPag=" + pdf_relcfdiPag + "&fechaPago=" + pdf_fechaPago + "&ctaBeneficiarias=" + pdf_ctaBeneficiarias + "&numOperacion=" + pdf_numOperacion + "&regimenFiscal=" + pdf_regimenFiscal + "&horaEmision=" + pdf_horaEmision + "&contadorFacturasPorPagar=" + contadorFacturasPorPagar + "&tipo=" + tipo + url, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                if (tipo == "1") {

                    $("#visor1").load(location.href + " #visor1");
                    setTimeout(function () {
                        openModalComplemento();
                    }, 1000);
                }
            }).catch(error => console.log(error));

}

function hblteditRegFacturas(posicion) {
    document.getElementById("metodo_de_pago_dr[" + posicion + "]").disabled = false;
    document.getElementById("saldoPagado[" + posicion + "]").disabled = false;
}

function openModalComplemento() {
    $("#modalVistaPreviaComplemento").modal("show");
}

function Api_ComplementoPago(id, tipoGeneracion, timbrado) {
    const Http = new XMLHttpRequest();
    
    const url = 'https://www.rtms.mx/Facturacion3.3QA/getXML.jsp?idf=' + id + '&cve=' + cbdiv_id + '&tipo=1';
    //const url = 'https://www.rtms.mx/Facturacion3.3PRO/getXML.jsp?idf=' + id + '&cve=' + cbdiv_id + '&tipo=1';
    
    Http.open("GET", url);
    Http.send();

    Http.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            let respuesta = Http.responseText;
            let palabra = "200";
            let index = respuesta.indexOf(palabra);

            if (index >= 0) {

                //Generación de PDF'S
                visorPdfComplemento(2);      // (1 = No Marcado de ruta pdf) | (2= Marcado de ruta pdf)

                //Almacenamiento de PDF'S
                savegeneratepdf(tipoGeneracion);    //tipoGeneracion(complemento de pago)

                //Envío de PDF'S    
                sendmail(tipoGeneracion, timbrado); /*tipoGeneracion(complemento de pago)|tipoTimbrado(si XML)*/

            } else {
                swal("Error", "Detalle: " + respuesta, "error");
                return false;
                hidePreloader();
            }
        }
    }
}
           
