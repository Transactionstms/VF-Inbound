/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function consultarExistenciaUUID(tipo_registro) {

    fetch("../consultarExistenciaUUIDSifei?serie=" + serie + "&folio=" + folio, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {

                if (data == "1") {
                    swal(serie + "-" + folio, "lo sentimos, ya se encuentra facturado", "info");
                    alertclose();
                } else {
                    AddFacturacion(tipo_registro);
                }

            }).catch(error => console.log(error));
}

function AddFacturacion(tipo_registro) {
   
    if (document.getElementById("check_xml").checked) {
        xml_sts = 1;
    } else {
        xml_sts = 0;
    }
    if (document.getElementById("check_pdf").checked) {
        pdf_sts = 1;
    } else {
        pdf_sts = 0;
    }
    if (document.getElementById("check_addFactura").checked) {
        addFact_sts = 1;
    } else {
        addFact_sts = 0;
    }

    //Parametros - Facturación:
    let cliente_id = document.getElementById("cliente_id").value;
    let rfc = document.getElementById("rfc").value;
    let usocfdi_id = document.getElementById("usocfdi_id").value;
    correos = document.getElementById("correos").value;
    let tipo_descripcion = document.getElementById("tipo_descripcion").value;
    let fecha_emision = document.getElementById("fecha_emision").value;
    let hora_emision = document.getElementById("hora_emision").value;
    serie = document.getElementById("serie").value;
    folio = document.getElementById("folio").value;
    let comprobante_id = document.getElementById("comprobante_id").value;
    let documento_id = document.getElementById("documento_id").value;
    let regimen_id = document.getElementById("regimen_id").value;

    //Parametros - Condiciones comerciales:
    let metodo_id = document.getElementById("metodo_id").value;
    let forma_id = document.getElementById("forma_id").value;
    let condiciones_pago = document.getElementById("condiciones_pago").value;
    let moneda_id = document.getElementById("moneda_id").value;
    let tipo_cambio = document.getElementById("tipo_cambio").value;
    let up_desc = document.getElementById("up_desc").value;

    //Totales:
    let cant_subtotal_gral = document.getElementById("cant_subtotal_gral").value;
    let cant_total_gral = document.getElementById("cant_total_gral").value;
    let cant_descuento_gral = document.getElementById("cant_descuento_gral").value;
    let cant_traslados_gral = document.getElementById("cant_traslados_gral").value;
    let cant_retenciones_gral = document.getElementById("cant_retenciones_gral").value;
    let cant_impuesto_gral = document.getElementById("cant_impuesto_gral").value;
    let cant_factor_gral = document.getElementById("cant_factor_gral").value;
    let cant_tasa_gral = document.getElementById("cant_tasa_gral").value;
    let cant_cuota_gral = document.getElementById("cant_cuota_gral").value;
    let tipo_impueto_gral = document.getElementById("tipo_impueto_gral").value;
    let estatusCartaPorte = document.getElementById("estatusCartaPorte").value;

    //Cliente y comprobantes:
    if (cliente_id == null || cliente_id == 0) {
        swal("Error", "Nombre de cliente inválido", "error");
        alertclose();
        return false;
    }
    if (rfc == "") {
        swal("Error", "El RFC del cliente es inválido", "error");
        alertclose();
        return false;
    }
    if (usocfdi_id == null || usocfdi_id == 0) {
        swal("Error", "Seleccione un uso de cfdi", "error");
        alertclose();
        return false;
    }
    if (correos == "") {
        swal("Error", "Agregue al menos un correo", "error");
        alertclose();
        return false;
    }
    if (fecha_emision == "") {
        swal("Error", "Ingrese fecha de emisión", "error");
        alertclose();
        return false;
    }
    if (hora_emision == "") {
        swal("Error", "Ingrese hora de emisión", "error");
        alertclose();
        return false;
    }
    if (serie == null || serie == 0) {
        swal("Error", "Agregue un tipo de serie", "error");
        alertclose();
        return false;
    }
    if (folio == "") {
        swal("Error", "Agregue un número de folio", "error");
        alertclose();
        return false;
    }

    //Conceptos Facturación;
    /*if (contadorConceptos == 0) {
        swal("Error", "Agregue al menos un concepto", "error");
        return false;
    }*/

    //Condiciones Generales:
    if (metodo_id == null || metodo_id == 0) {
        swal("Error", "El método de pago es inválido", "error");
        return false;
    }
    if (forma_id == null || forma_id == 0) {
        swal("Error", "Ingrese una forma de pago", "error");
        return false;
    }
    if (moneda_id == null || moneda_id == 0) {
        swal("Error", "Seleccione un cambio de moneda", "error");
        return false;
    }

    //Parametros: Conceptos CFDI
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

    //Parametros: Relación UUID
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

    //Parametros: Carta Porte
    /*for (let t = 0; t < contadorConceptosCartaPorte; t++) {

        let tempcp2 = "rel_clvsat_id_cp[" + t + "]";
        let tempcp3 = "unidad_sat_id_cp[" + t + "]";
        let tempcp1 = "unidad_sat_desc_cp[" + t + "]";
        let tempcp9 = "concepto_desc_cp[" + t + "]";
        let tempcp10 = "concepto_id_cp[" + t + "]";
        let tempcp11 = "cantidad_cp[" + t + "]";
        let tempcp12 = "precio_unitario_cp[" + t + "]";
        let tempcp13 = "importe_descuento_cp[" + t + "]";
        let tempcp14 = "importe_cp[" + t + "]";
        let tempcp15 = "unidadMedidaValor[" + t + "]";
        let tempcp16 = "no_pedimento_cp[" + t + "]";

        rel_clvsat_id_cp = document.getElementById(tempcp2).value;
        unidad_sat_id_cp = document.getElementById(tempcp3).value;
        unidad_sat_desc_cp = document.getElementById(tempcp1).value;
        concepto_desc_cp = document.getElementById(tempcp9).value;
        concepto_id_cp = document.getElementById(tempcp10).value;
        cantidad_cp = document.getElementById(tempcp11).value;
        precio_unitario_cp = document.getElementById(tempcp12).value;
        importe_descuento_cp = document.getElementById(tempcp13).value;
        importe_cp = document.getElementById(tempcp14).value;
        unidadMedidaValor = document.getElementById(tempcp15).value;
        no_pedimento_cp = document.getElementById(tempcp16).value;

        url6 += "&rel_clvsat_id_cp[" + t + "]=" + rel_clvsat_id_cp +
                "&unidad_sat_id_cp[" + t + "]=" + unidad_sat_id_cp +
                "&unidad_sat_desc_cp[" + t + "]=" + unidad_sat_desc_cp +
                "&concepto_desc_cp[" + t + "]=" + concepto_desc_cp +
                "&concepto_id_cp[" + t + "]=" + concepto_id_cp +
                "&cantidad_cp[" + t + "]=" + cantidad_cp +
                "&precio_unitario_cp[" + t + "]=" + precio_unitario_cp +
                "&importe_descuento_cp[" + t + "]=" + importe_descuento_cp +
                "&importe_cp[" + t + "]=" + importe_cp +
                "&unidadMedidaValor[" + t + "]=" + unidadMedidaValor +
                "&no_pedimento_cp[" + t + "]=" + no_pedimento_cp;
    }*/

    showPreloader();
   //fetch("../InsertarFacturacion?tipoEmision=" + tipo_registro + "&cliente_id=" + cliente_id + "&rfc=" + rfc + "&usocfdi_id=" + usocfdi_id + "&correos=" + correos + "&tipo_descripcion=" + tipo_descripcion + "&fecha_emision=" + fecha_emision + "&hora_emision=" + hora_emision + "&serie=" + serie + "&folio=" + folio + "&comprobante_id=" + comprobante_id + "&documento_id=" + documento_id + "&regimen_id=" + regimen_id + "&metodo_id=" + metodo_id + "&forma_id=" + forma_id + "&condiciones_pago=" + condiciones_pago + "&moneda_id=" + moneda_id + "&tipo_cambio=" + tipo_cambio + "&xml_sts=" + xml_sts + "&pdf_sts=" + pdf_sts + "&addFact_sts=" + addFact_sts + "&up_desc=" + up_desc + "&cant_subtotal_gral=" + cant_subtotal_gral + "&cant_descuento_gral=" + cant_descuento_gral + "&cant_traslados_gral=" + cant_traslados_gral + "&cant_retenciones_gral=" + cant_retenciones_gral + "&cant_total_gral=" + cant_total_gral + "&cant_impuesto_gral=" + cant_impuesto_gral + "&cant_factor_gral=" + cant_factor_gral + "&cant_tasa_gral=" + cant_tasa_gral + "&cant_cuota_gral=" + cant_cuota_gral + "&tipo_impueto_gral=" + tipo_impueto_gral + "&numCartaPorte=" + contadorConceptosCartaPorte + url6 + urlParametrosCartaPorte + urlParametrosInformacionComercial, {
    fetch("../InsertarFacturacion?tipoEmision=" + tipo_registro + "&cliente_id=" + cliente_id + "&rfc=" + rfc + "&usocfdi_id=" + usocfdi_id + "&correos=" + correos + "&tipo_descripcion=" + tipo_descripcion + "&fecha_emision=" + fecha_emision + "&hora_emision=" + hora_emision + "&serie=" + serie + "&folio=" + folio + "&comprobante_id=" + comprobante_id + "&documento_id=" + documento_id + "&regimen_id=" + regimen_id + "&metodo_id=" + metodo_id + "&forma_id=" + forma_id + "&condiciones_pago=" + condiciones_pago + "&moneda_id=" + moneda_id + "&tipo_cambio=" + tipo_cambio + "&xml_sts=" + xml_sts + "&pdf_sts=" + pdf_sts + "&addFact_sts=" + addFact_sts + "&up_desc=" + up_desc + "&cant_subtotal_gral=" + cant_subtotal_gral + "&cant_descuento_gral=" + cant_descuento_gral + "&cant_traslados_gral=" + cant_traslados_gral + "&cant_retenciones_gral=" + cant_retenciones_gral + "&cant_total_gral=" + cant_total_gral + "&cant_impuesto_gral=" + cant_impuesto_gral + "&cant_factor_gral=" + cant_factor_gral + "&cant_tasa_gral=" + cant_tasa_gral + "&cant_cuota_gral=" + cant_cuota_gral + "&tipo_impueto_gral=" + tipo_impueto_gral, {
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

                    if (timbrado == "1") {         //No timbrado 

                        //Generación de PDF'S
                        /*if (contadorConceptosCartaPorte > 0) {
                            visorPdfFactura(2);    // (1 = No Marcado de ruta pdf) | (2= Marcado de ruta pdf)
                            visorPdfCartaPorte(2); // (1 = No Marcado de ruta pdf) | (2= Marcado de ruta pdf)

                            tipoGeneracion = 2;    // Carta Porte
                        } else {
                            visorPdfFactura(2);    // (1 = No Marcado de ruta pdf) | (2= Marcado de ruta pdf)
                            tipoGeneracion = 1;    //CFDI 
                        }*/
                        
                            visorPdfFactura(2);    // (1 = No Marcado de ruta pdf) | (2= Marcado de ruta pdf)
                            tipoGeneracion = 1;    //CFDI 

                        //Almacenamiento de PDF'S
                        savegeneratepdf(tipoGeneracion);          //tipoGeneracion(cfdi ó cartaPorte)

                        //Envío de PDF'S
                        setTimeout(function () {
                            sendmail(tipoGeneracion, timbrado); /*tipoGeneracion(cfdi ó cartaPorte)|tipoTimbrado(no XML)*/
                        }, 2000);


                    } else if (timbrado == "2") {   //Si timbrado

                        /*if (contadorConceptosCartaPorte > 0) {
                            Api_CartaPorte(idRegistro, 2, timbrado); //Folio consecutivo base(api)|tipoGeneracion(cartaPorte)|tipoTimbrado(si XML)
                        } else {
                            Api_Facturacion(idRegistro, 1, timbrado); //Folio consecutivo base(api)|tipoGeneracion(cfdi)|tipoTimbrado(si XML)
                        }*/
                        
                        Api_Facturacion(idRegistro, 1, timbrado); //Folio consecutivo base(api)|tipoGeneracion(cfdi)|tipoTimbrado(si XML)
                    }

                }

            }).catch(error => console.log(error));
}


