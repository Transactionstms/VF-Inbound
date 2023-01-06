/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function visorPdfFactura(tipo) {

    let pdf_cliente_id = document.getElementById("cliente_id").value;
    let pdf_rfc = document.getElementById("rfc").value;
    let pdf_usocfdi_id = document.getElementById("usocfdi_id").value;
    let pdf_correos = document.getElementById("correos").value;
    let pdf_tipo_descripcion = document.getElementById("tipo_descripcion").value;
    let pdf_fecha_emision = document.getElementById("fecha_emision").value;
    let pdf_hora_emision = document.getElementById("hora_emision").value;
    serie = document.getElementById("serie").value;
    folio = document.getElementById("folio").value;
    let pdf_comprobante_id = document.getElementById("comprobante_id").value;
    let pdf_documento_id = document.getElementById("documento_id").value;
    let pdf_regimen_id = document.getElementById("regimen_id").value;
    let pdf_metodo_id = document.getElementById("metodo_id").value;
    let pdf_forma_id = document.getElementById("forma_id").value;
    let pdf_condiciones_pago = document.getElementById("condiciones_pago").value;
    let pdf_moneda_id = document.getElementById("moneda_id").value;
    let pdf_tipo_cambio = document.getElementById("tipo_cambio").value;
    let pdf_num_orden = document.getElementById("num_orden").value;
    let pdf_num_proveedor = document.getElementById("num_proveedor").value;
    let pdf_info_observaciones = document.getElementById("info_observaciones").value;
    let pdf_up_desc = document.getElementById("up_desc").value;

    //Cliente y comprobantes:
    if (pdf_cliente_id == null || pdf_cliente_id == 0) {
        swal("Error", "Nombre de cliente inválido", "error");
        return false;
    }
    if (pdf_rfc == "") {
        swal("Error", "El RFC del cliente es inválido", "error");
        return false;
    }
    if (pdf_usocfdi_id == null || pdf_usocfdi_id == 0) {
        swal("Error", "Seleccione un uso de cfdi", "error");
        return false;
    }
    if (pdf_correos == "") {
        swal("Error", "Agregue al menos un correo", "error");
        return false;
    }
    if (pdf_fecha_emision == "") {
        swal("Error", "Ingrese fecha de emisión", "error");
        return false;
    }
    if (pdf_hora_emision == "") {
        swal("Error", "Ingrese hora de emisión", "error");
        return false;
    }
    if (serie == null || serie == 0) {
        swal("Error", "Agregue un tipo de serie", "error");
        return false;
    }
    if (folio == "") {
        swal("Error", "Agregue un número de folio", "error");
        return false;
    }

    //Conceptos Facturación;
    /*if (contadorConceptos == 0) {
        swal("Error", "Agregue al menos un concepto", "error");
        return false;
    }*/

    //Condiciones Generales:
    if (pdf_metodo_id == null || pdf_metodo_id == 0) {
        swal("Error", "El método de pago es inválido", "error");
        return false;
    }
    if (pdf_forma_id == null || pdf_forma_id == 0) {
        swal("Error", "Ingrese una forma de pago", "error");
        return false;
    }
    if (pdf_moneda_id == null || pdf_moneda_id == 0) {
        swal("Error", "Seleccione un cambio de moneda", "error");
        return false;
    }

    //Parametros: Editar Configuración
    /*for (let i = 0; i < contadorConceptos; i++) {
        let temp1 = "comentarios[" + i + "]";
        let temp2 = "rel_clvsat_id[" + i + "]";
        let temp3 = "unidad_sat_id[" + i + "]";

        let temp9 = "concepto_desc[" + i + "]";
        let temp10 = "concepto_id[" + i + "]";
        let temp11 = "cantidad[" + i + "]";
        let temp12 = "precio_unitario[" + i + "]";
        let temp13 = "importe_descuento[" + i + "]";
        let temp14 = "importe[" + i + "]";

        comentarios = document.getElementById(temp1).value;
        rel_clvsat_id = document.getElementById(temp2).value;
        unidad_sat_id = document.getElementById(temp3).value;

        concepto_desc = document.getElementById(temp9).value;
        concepto_id = document.getElementById(temp10).value;
        cantidad = document.getElementById(temp11).value;
        precio_unitario = document.getElementById(temp12).value;
        importe_descuento = document.getElementById(temp13).value;
        importe = document.getElementById(temp14).value;

        url += "&comentarios[" + i + "]=" + comentarios +
                "&rel_clvsat_id[" + i + "]=" + rel_clvsat_id +
                "&unidad_sat_id[" + i + "]=" + unidad_sat_id +
                "&concepto_desc[" + i + "]=" + concepto_desc +
                "&concepto_id[" + i + "]=" + concepto_id +
                "&cantidad[" + i + "]=" + cantidad +
                "&precio_unitario[" + i + "]=" + precio_unitario +
                "&importe_descuento[" + i + "]=" + importe_descuento +
                "&importe[" + i + "]=" + importe;
    }*/

    //Parametros: Impuestos
    /*for (let a = 0; a < contadorImpuestos; a++) {
        let tempImp1 = "ti_tipoImpuesto[" + a + "]";
        let tempImp2 = "ti_base[" + a + "]";
        let tempImp3 = "ti_impuesto[" + a + "]";
        let tempImp4 = "ti_tipoFactor[" + a + "]";
        let tempImp5 = "ti_up_tasa[" + a + "]";
        let tempImp6 = "ti_up_Cuota[" + a + "]";
        let tempImp7 = "ti_importe[" + a + "]";

        ti_tipoImpuesto = document.getElementById(tempImp1).value;
        ti_base = document.getElementById(tempImp2).value;
        ti_impuesto = document.getElementById(tempImp3).value;
        ti_tipoFactor = document.getElementById(tempImp4).value;
        ti_tasa = document.getElementById(tempImp5).value;
        ti_cuota = document.getElementById(tempImp6).value;
        ti_importe = document.getElementById(tempImp7).value;

        url2 += "&up_tipoImpuesto[" + a + "]=" + ti_tipoImpuesto +
                "&up_base[" + a + "]=" + ti_base +
                "&up_impuesto[" + a + "]=" + ti_impuesto +
                "&up_tipoFactor[" + a + "]=" + ti_tipoFactor +
                "&up_tasa[" + a + "]=" + ti_tasa +
                "&up_cuota[" + a + "]=" + ti_cuota +
                "&up_importe[" + a + "]=" + ti_importe;
    }*/

    //Parametros: Pedimentos
    /*for (let o = 0; o < contadorPedimentos; o++) {
        let tempPed1 = "tp_cuentaPredial[" + o + "]";
        let tempPed2 = "tp_numeroPedimento[" + o + "]";

        tp_cuentaPredial = document.getElementById(tempPed1).value;
        tp_numeroPedimento = document.getElementById(tempPed2).value;


        url3 += "&up_cuentaPredial[" + o + "]=" + tp_cuentaPredial +
                "&up_numeroPedimento[" + o + "]=" + tp_numeroPedimento;
    }*/

    //Parametros: Partes
    /*for (let u = 0; u < contadorPartes; u++) {
        let tempPart1 = "tqconcepto_id[" + u + "]";
        let tempPart2 = "tqParte_desc[" + u + "]";
        let tempPart3 = "tqParte_Cantidad[" + u + "]";
        let tempPart4 = "tqParte_precioUnitario[" + u + "]";
        let tempPart5 = "tqParte_ClaveProdServ[" + u + "]";
        let tempPart6 = "tqParte_unidad[" + u + "]";
        let tempPart7 = "tqParte_NoIdentificacion[" + u + "]";

        tqconcepto_id = document.getElementById(tempPart1).value;
        tqParte_desc = document.getElementById(tempPart2).value;
        tqParte_Cantidad = document.getElementById(tempPart3).value;
        tqParte_precioUnitario = document.getElementById(tempPart4).value;
        tqParte_ClaveProdServ = document.getElementById(tempPart5).value;
        tqParte_unidad = document.getElementById(tempPart6).value;
        tqParte_NoIdentificacion = document.getElementById(tempPart7).value;

        url4 += "&parte_concepto_id[" + u + "]=" + tqconcepto_id +
                "&upParte_desc[" + u + "]=" + tqParte_desc +
                "&upParte_Cantidad[" + u + "]=" + tqParte_Cantidad +
                "&upParte_precioUnitario[" + u + "]=" + tqParte_precioUnitario +
                "&upParte_ClaveProdServ[" + u + "]=" + tqParte_ClaveProdServ +
                "&upParte_unidad[" + u + "]=" + tqParte_unidad +
                "&upParte_NoIdentificacion[" + u + "]=" + tqParte_NoIdentificacion;
    }*/

    //Parametros: RelaciÃ³n UUID
    /*for (let e = 0; e < contadorUUID; e++) {
        let tempU1 = "tiporelacion_id[" + e + "]";
        let tempU2 = "uuid_id[" + e + "]";
        let tempU3 = "total_cfdi[" + e + "]";

        tiporelacion_id = document.getElementById(tempU1).value;
        uuid_id = document.getElementById(tempU2).value;
        total_cfdi = document.getElementById(tempU3).value;

        url5 += "&tiporelacion_id[" + e + "]=" + tiporelacion_id +
                "&uuid_id[" + e + "]=" + uuid_id +
                "&total_cfdi[" + e + "]=" + total_cfdi;
    }*/

    /*let urlPDF=encodeURI("FacturacionVistaPrevia.jsp?cliente_id=" + pdf_cliente_id + "&rfc=" + pdf_rfc + "&usocfdi_id=" + pdf_usocfdi_id + "&correos=" + pdf_correos + "&tipo_descripcion=" + pdf_tipo_descripcion + "&fecha_emision=" + pdf_fecha_emision + "&hora_emision=" + pdf_hora_emision + "&serie=" + serie + "&folio=" + folio + "&comprobante_id=" + pdf_comprobante_id + "&documento_id=" + pdf_documento_id + "&regimen_id=" + pdf_regimen_id + "&metodo_id=" + pdf_metodo_id + "&forma_id=" + pdf_forma_id + "&condiciones_pago=" + pdf_condiciones_pago + "&moneda_id=" + pdf_moneda_id + "&tipo_cambio=" + pdf_tipo_cambio + "&num_orden=" + pdf_num_orden + "&num_proveedor=" + pdf_num_proveedor + "&info_observaciones=" + pdf_info_observaciones + "&up_desc=" + pdf_up_desc + "&tipo=" + tipo);           
    console.log(urlPDF);*/

    fetch("FacturacionVistaPrevia.jsp?cliente_id=" + pdf_cliente_id + "&rfc=" + pdf_rfc + "&usocfdi_id=" + pdf_usocfdi_id + "&correos=" + pdf_correos + "&tipo_descripcion=" + pdf_tipo_descripcion + "&fecha_emision=" + pdf_fecha_emision + "&hora_emision=" + pdf_hora_emision + "&serie=" + serie + "&folio=" + folio + "&comprobante_id=" + pdf_comprobante_id + "&documento_id=" + pdf_documento_id + "&regimen_id=" + pdf_regimen_id + "&metodo_id=" + pdf_metodo_id + "&forma_id=" + pdf_forma_id + "&condiciones_pago=" + pdf_condiciones_pago + "&moneda_id=" + pdf_moneda_id + "&tipo_cambio=" + pdf_tipo_cambio + "&num_orden=" + pdf_num_orden + "&num_proveedor=" + pdf_num_proveedor + "&info_observaciones=" + pdf_info_observaciones + "&up_desc=" + pdf_up_desc + "&tipo=" + tipo, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                if (tipo == "1") {

                    $("#visor1").load(location.href + " #visor1");
                    setTimeout(function () {
                        openModalCfdi();
                    }, 1000);
                }
            }).catch(error => console.log(error));
}

function openModalCfdi() {
    $("#modalVistaPreviaCfdi").modal("show");
}   

