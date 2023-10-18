/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Contadores Tablas
let contadorConceptosCartaPorte = 0;

//Parametros: Tabla Carta Porte
let rel_clvsat_id_cp;
let unidad_sat_id_cp;
let unidad_sat_desc_cp;
let concepto_desc_cp;
let concepto_id_cp;
let no_pedimento_cp;
let cantidad_cp;
let unidadMedidaValor;
let precio_unitario_cp;
let importe_descuento_cp;
let importe_cp;
let url6 = "";
let urlParametrosCartaPorte = "";
let url7 = "";
let url8 = "";

function detailsConceptoCartaPorte(atributos) {

    let atrib = atributos.split("*");
    let at1 = atrib[0]; //id
    let at2 = atrib[1]; //clave

    fetch("../ConsultarDataConceptoCartaPorte?clvreg_id=" + at1, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                let input = data.split("*");
                let a0t = input[0]; //id
                let a1t = input[1]; //clave sat
                let a2t = input[2]; //valor unitario
                let a3t = input[3]; //unidad medida clave
                let a4t = input[4]; //unidad medida id

                document.getElementById("cantidad_cp").value = 1;           //cantidad
                document.getElementById("rel_clvsat_id_cp").value = a1t;    //clave sat
                document.getElementById("precio_unitario_cp").value = parseFloat(a2t).toFixed(2);  //valor unitario
                document.getElementById("unidad_sat_id_cp").value = a3t;    //unidad medida clave
                //document.getElementById("unidad_sat_id").value = a4t;    //unidad medida id

            }).catch(error => console.log(error));
}

function AddConceptosCartaPorte() {

    let rel_clvsat_id_cp1 = document.getElementById("rel_clvsat_id_cp").value;
    let unidad_sat_id_cp1 = document.getElementById("unidad_sat_id_cp").value;

    let caramelo_cp1 = document.getElementById("mercancia_descripcion").value;
    let objcp = caramelo_cp1.split("*");
    let concepto_id_cp1 = objcp[0];
    let concepto_desc_cp1 = objcp[1];
    let cantidad_cp1 = document.getElementById("cantidad_cp").value;
    let no_pedimento_cp = document.getElementById("no_pedimento_cp").value;
    let precio_unitario_cp1 = document.getElementById("precio_unitario_cp").value;

    let importe_descuento_cp1 = 0;
    let importe_cp1 = 0;

    importe_cp1 = cantidad_cp1 * precio_unitario_cp1;

    if (caramelo_cp1 == null || caramelo_cp1 == 0) {
        swal("Error", "Ingrese concepto", "error");
        alertclose();
        return false;
    }
    if (cantidad_cp1 == "") {
        swal("Error", "Ingrese cantidad", "error");
        alertclose();
        return false;
    }
    if (rel_clvsat_id_cp1 == "") {
        swal("Error", "Ingrese clave sat", "error");
        alertclose();
        return false;
    }
    if (unidad_sat_id_cp1 == "") {
        swal("Error", "Ingrese unidad sat", "error");
        alertclose();
        return false;
    }
    if (precio_unitario_cp1 == "") {
        swal("Error", "Ingrese precio unitario", "error");
        alertclose();
        return false;
    }

    fetch("../AddTablaConceptosCartaPorte?rel_clvsat_id=" + rel_clvsat_id_cp1 + "&unidad_sat_id=" + unidad_sat_id_cp1 + "&concepto_id=" + concepto_id_cp1 + "&concepto_desc=" + concepto_desc_cp1 + "&cantidad=" + cantidad_cp1 + "&precio_unitario=" + precio_unitario_cp1 + "&importe_descuento=" + importe_descuento_cp1 + "&importe=" + importe_cp1 + "&no_pedimento=" + no_pedimento_cp + "&contadorConceptosCartaPorte=" + contadorConceptosCartaPorte, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {

                let resultadocp = document.getElementById("AddTableConceptosCartaPorte").insertRow(-1);

                resultadocp.innerHTML = data;
                resultadocp.id = "tr" + concepto_id_cp;
                contadorConceptosCartaPorte++;

                document.getElementById("numConceptosCartaPorte").value = contadorConceptosCartaPorte;
                cleanFormularioConceptoCartaPorte();

            }).catch(error => console.log(error));

}

function visorPdfCartaPorte(tipo) {

    let pdfcheck1 = "";
    let pdfcheck2 = "";
    let pdfcheck3 = "";

    let serie = document.getElementById("serie").value;
    let folio = document.getElementById("folio").value;

    //Parametros: Embarque
    let pdflugarDeEmbarque = document.getElementById("LugarDeEmbarque").value;
    let pdflugEmb_nombre = document.getElementById("lugEmb_nombre").value;
    let pdflugEmb_rfc = document.getElementById("lugEmb_rfc").value;
    let pdflugEmb_calle = document.getElementById("lugEmb_calle").value;
    let pdflugEmb_noInterior = document.getElementById("lugEmb_noInterior").value;
    let pdflugEmb_noExterior = document.getElementById("lugEmb_noExterior").value;
    let pdflugEmb_codigoPostal = document.getElementById("lugEmb_codigoPostal").value;
    let pdflugEmb_estado = document.getElementById("lugEmb_estado").value;
    let pdflugEmb_municipio = document.getElementById("lugEmb_municipio").value;
    let pdflugEmb_colonia = document.getElementById("lugEmb_colonia").value;
    let pdflugEmb_referencia = document.getElementById("lugEmb_referencia").value;
    let pdflugEmb_fechaHoraSalidaLlegada = document.getElementById("lugEmb_fechaHoraSalidaLlegada").value;
    let pdflugEmb_localidad = document.getElementById("lugEmb_localidad").value;
    let pdflugEmb_idUbicacion = document.getElementById("lugEmb_idUbicacion").value;

    //Parametros: Destino
    let pdflugarDestinatario = document.getElementById("LugarDestinatario").value;
    let pdfdest_nombre = document.getElementById("dest_nombre").value;
    let pdfdest_rfc = document.getElementById("dest_rfc").value;
    let pdfdest_calle = document.getElementById("dest_calle").value;
    let pdfdest_noInterior = document.getElementById("dest_noInterior").value;
    let pdfdest_noExterior = document.getElementById("dest_noExterior").value;
    let pdfdest_codigoPostal = document.getElementById("dest_codigoPostal").value;
    let pdfdest_estado = document.getElementById("dest_estado").value;
    let pdfdest_municipio = document.getElementById("dest_municipio").value;
    let pdfdest_colonia = document.getElementById("dest_colonia").value;
    let pdfdest_referencia = document.getElementById("dest_referencia").value;
    let pdfdest_fechaHoraSalidaLlegada = document.getElementById("dest_fechaHoraSalidaLlegada").value;
    let pdfdest_distancia = document.getElementById("dest_distancia").value;
    let pdfdest_localidad = document.getElementById("dest_localidad").value;
    let pdfdest_idUbicacion = document.getElementById("dest_idUbicacion").value;

    //Parametros: Entrega
    let pdflugarEntrega = document.getElementById("LugarEntrega").value;
    let pdflugEnt_contacto = document.getElementById("lugEnt_contacto").value;
    let pdflugEnt_nombre = document.getElementById("lugEnt_nombre").value;
    let pdflugEnt_rfc = document.getElementById("lugEnt_rfc").value;
    let pdflugEnt_calle = document.getElementById("lugEnt_calle").value;
    let pdflugEnt_noInterior = document.getElementById("lugEnt_noInterior").value;
    let pdflugEnt_noExterior = document.getElementById("lugEnt_noExterior").value;
    let pdflugEnt_codigoPostal = document.getElementById("lugEnt_codigoPostal").value;
    let pdflugEnt_estado = document.getElementById("lugEnt_estado").value;
    let pdflugEnt_municipio = document.getElementById("lugEnt_municipio").value;
    let pdflugEnt_colonia = document.getElementById("lugEnt_colonia").value;
    let pdflugEnt_referencia = document.getElementById("lugEnt_referencia").value;
    let pdflugEnt_pais = document.getElementById("lugEnt_pais").value;
    let pdflugEnt_tipoTransporte = document.getElementById("lugEnt_tipoTransporte").value;
    let pdflugEnt_fechaHoraSalidaLlegada = document.getElementById("lugEnt_fechaHoraSalidaLlegada").value;
    let pdflugEnt_distancia = document.getElementById("lugEnt_distancia").value;
    let pdflugEnt_localidad = document.getElementById("lugEnt_localidad").value;
    let pdflugEnt_idUbicacion = document.getElementById("lugEnt_idUbicacion").value;

    //Parametros: Mercancía
    let pdfunidad_peso_cp = document.getElementById("unidad_peso_cp").value;         //campo nuevo
    let pdfmercancia_es_peligroso_cp = document.getElementById("mercancia_es_peligroso_cp").value;
    let pdfnumpedimento_cp = document.getElementById("numpedimento_cp").value;
    let pdfmoneda_cp = document.getElementById("moneda_cp").value;

    //Parametros: Autotransporte
    let pdfperm_sct = document.getElementById("perm_sct").value;
    let pdfno_permismo_sct = document.getElementById("no_permismo_sct").value;
    let pdfconfig_vehicular = document.getElementById("config_vehicular").value;
    let pdfplaca_vm = document.getElementById("placa_vm").value;
    let pdfanio_modelovm = document.getElementById("anio_modelovm").value;
    let pdfplaca_remolque = document.getElementById("placa_remolque").value;  //campo nuevo
    let pdftipo_remolque = document.getElementById("tipo_remolque").value;    //campo nuevo
    let pdfaseguradora_civil = document.getElementById("aseguradora_civil").value;
    let pdfpoliza_civil = document.getElementById("poliza_civil").value;
    let pdftipo_figura = document.getElementById("tipo_figura").value;
    let pdfrfc_figura = document.getElementById("rfc_figura").value;
    let pdfnum_licencia = document.getElementById("num_licencia").value;
    let pdfnombre_figura = document.getElementById("nombre_figura").value;

    //Parametros: Chofer
    let pdfautChofer_calle = document.getElementById("autChofer_calle").value;
    let pdfautChofer_noInterior = document.getElementById("autChofer_noInterior").value;
    let pdfautChofer_noExterior = document.getElementById("autChofer_noExterior").value;
    let pdfautChofer_colonia = document.getElementById("autChofer_colonia").value;
    let pdfautChofer_localidad = document.getElementById("autChofer_localidad").value;
    let pdfautChofer_municipio = document.getElementById("autChofer_municipio").value;
    let pdfautChofer_referencia = document.getElementById("autChofer_referencia").value;
    let pdfautChofer_estado = document.getElementById("autChofer_estado").value;
    let pdfautChofer_codigoPostal = document.getElementById("autChofer_codigoPostal").value;

    //Parametros: CFDI
    let pdfmoneda_id = document.getElementById("moneda_id").value;
    let pdfcomprobante_id = document.getElementById("comprobante_id").value;
    let pdfmetodo_id = document.getElementById("metodo_id").value;
    let pdfforma_id = document.getElementById("forma_id").value;

    //Parametros: Conceptos CFDI
    for (let q = 0; q < contadorConceptos; q++) {
        let temp1 = "comentarios[" + q + "]";
        let temp2 = "rel_clvsat_id[" + q + "]";
        let temp3 = "unidad_sat_id[" + q + "]";

        let temp9 = "concepto_desc[" + q + "]";
        let temp10 = "concepto_id[" + q + "]";
        let temp11 = "cantidad[" + q + "]";
        let temp12 = "precio_unitario[" + q + "]";
        let temp13 = "importe_descuento[" + q + "]";
        let temp14 = "importe[" + q + "]";

        comentarios = document.getElementById(temp1).value;
        rel_clvsat_id = document.getElementById(temp2).value;
        unidad_sat_id = document.getElementById(temp3).value;

        concepto_desc = document.getElementById(temp9).value;
        concepto_id = document.getElementById(temp10).value;
        cantidad = document.getElementById(temp11).value;
        precio_unitario = document.getElementById(temp12).value;
        importe_descuento = document.getElementById(temp13).value;
        importe = document.getElementById(temp14).value;

        url += "&comentarios[" + q + "]=" + comentarios +
                "&rel_clvsat_id[" + q + "]=" + rel_clvsat_id +
                "&unidad_sat_id[" + q + "]=" + unidad_sat_id +
                "&concepto_desc[" + q + "]=" + concepto_desc +
                "&concepto_id[" + q + "]=" + concepto_id +
                "&cantidad[" + q + "]=" + cantidad +
                "&precio_unitario[" + q + "]=" + precio_unitario +
                "&importe_descuento[" + q + "]=" + importe_descuento +
                "&importe[" + q + "]=" + importe;
    }

    //Parametros: Carta Porte
    for (let t = 0; t < contadorConceptosCartaPorte; t++) {

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

        url8 += "&rel_clvsat_id_cp[" + t + "]=" + rel_clvsat_id_cp +
                "&unidad_sat_id_cp[" + t + "]=" + unidad_sat_id_cp +
                "&unidad_sat_desc_cp[" + t + "]=" + unidad_sat_desc_cp +
                "&concepto_desc_cp[" + t + "]=" + concepto_desc_cp +
                "&concepto_id_cp[" + t + "]=" + concepto_id_cp +
                "&cantidad_cp[" + t + "]=" + cantidad_cp +
                "&precio_unitario_cp[" + t + "]=" + precio_unitario_cp +
                "&importe_descuento_cp[" + t + "]=" + importe_descuento_cp +
                "&importe_cp[" + t + "]=" + importe_cp +
                "&unidadMedidaValor[" + t + "]=" + unidadMedidaValor;
    }

    if (pdflugEmb_calle == "") {
        swal("Error", "Ingrese domicilio origen", "error");
        return false;
    }

    if (pdfdest_calle == "") {
        swal("Error", "Ingrese domicilio destinatario", "error");
        return false;
    }

    if (pdfdest_distancia == null || pdfdest_distancia == 0) {
        swal("Error", "Ingrese distancia destino", "error");
        return false;
    }

    if (pdflugEnt_calle == "") {
        swal("Error", "Ingrese domicilio de entrega", "error");
        return false;
    }

    if (pdflugEnt_distancia == null || pdflugEnt_distancia == 0) {
        swal("Error", "Ingrese distancia entrega", "error");
        return false;
    }

    if (pdfperm_sct == null || pdfperm_sct == 0) {
        swal("Error", "Ingrese Perm SCT", "error");
        return false;
    }

    if (pdfno_permismo_sct == null) {
        swal("Error", "Ingrese información de autotransporte", "error");
        return false;
    }

    if (pdfconfig_vehicular == null || pdfperm_sct == 0) {
        swal("Error", "Ingrese Configuración Vehicular", "error");
        return false;
    }

    if (pdfplaca_vm == null) {
        swal("Error", "Ingrese Placa VM", "error");
        return false;
    }

    if (pdfanio_modelovm == null) {
        swal("Error", "Ingrese Año Modelo VM", "error");
        return false;
    }

    if (pdfaseguradora_civil == null) {
        swal("Error", "Ingrese Aseguradora Responsabilidad Civil", "error");
        return false;
    }

    if (pdfpoliza_civil == null) {
        swal("Error", "Ingrese Poliza Responsabilidad Civil", "error");
        return false;
    }

    if (pdftipo_figura == null || pdftipo_figura == 0) {
        swal("Error", "Ingrese Tipo Figura", "error");
        return false;
    }

    if (pdfrfc_figura == null) {
        swal("Error", "Ingrese RFC Figura", "error");
        return false;
    }

    if (pdfnum_licencia == null) {
        swal("Error", "Ingrese Núm.Licencia", "error");
        return false;
    }

    if (pdfnombre_figura == null) {
        swal("Error", "Ingrese Nombre Figura", "error");
        return false;
    }

    //Conceptos Facturación;
    if (contadorConceptos == 0) {
        swal("Error", "Agregue al menos un servicio - Carta Porte", "error");
        return false;
    }

    if (document.getElementById("check_lugarEmbarque").checked) {
        pdfcheck1 = "&check_lugarEmbarque=1";
    } else {
        pdfcheck1 = "&check_lugarEmbarque=0";
    }

    if (document.getElementById("check_lugarDestinatario").checked) {
        pdfcheck2 = "&check_lugarDestinatario=1";
    } else {
        pdfcheck2 = "&check_lugarDestinatario=0";
    }

    if (document.getElementById("check_lugarEntrega").checked) {
        pdfcheck3 = "&check_lugarEntrega=1";
    } else {
        pdfcheck3 = "&check_lugarEntrega=0";
    }

    //let urlPDF2=encodeURI("CartaPorteVistaPrevia.jsp?lugarDeEmbarque=" + pdflugarDeEmbarque + "&lugEmb_nombre=" + pdflugEmb_nombre + "&lugEmb_calle=" + pdflugEmb_calle + "&lugEmb_noInterior=" + pdflugEmb_noInterior + "&lugEmb_noExterior=" + pdflugEmb_noExterior + "&lugEmb_codigoPostal=" + pdflugEmb_codigoPostal + "&lugEmb_estado=" + pdflugEmb_estado + "&lugEmb_municipio=" + pdflugEmb_municipio + "&lugEmb_colonia=" + pdflugEmb_colonia + "&lugEmb_referencia=" + pdflugEmb_referencia + "&lugarDestinatario=" + pdflugarDestinatario + "&lugEmb_rfc=" + pdflugEmb_rfc + "&lugEmb_fechaHoraSalidaLlegada=" + pdflugEmb_fechaHoraSalidaLlegada + "&dest_nombre=" + pdfdest_nombre + "&dest_calle=" + pdfdest_calle + "&dest_noInterior=" + pdfdest_noInterior + "&dest_noExterior=" + pdfdest_noExterior + "&dest_codigoPostal=" + pdfdest_codigoPostal + "&dest_estado=" + pdfdest_estado + "&dest_municipio=" + pdfdest_municipio + "&dest_colonia=" + pdfdest_colonia + "&dest_referencia=" + pdfdest_referencia + "&dest_rfc=" + pdfdest_rfc + "&dest_fechaHoraSalidaLlegada=" + pdfdest_fechaHoraSalidaLlegada + "&dest_distancia=" + pdfdest_distancia + "&dest_localidad=" + pdfdest_localidad + "&dest_idUbicacion=" + pdfdest_idUbicacion + "&lugEmb_localidad=" + pdflugEmb_localidad + "&lugEmb_idUbicacion=" + pdflugEmb_idUbicacion + "&lugarEntrega=" + pdflugarEntrega + "&lugEnt_contacto=" + pdflugEnt_contacto + "&lugEnt_nombre=" + pdflugEnt_nombre + "&lugEnt_calle=" + pdflugEnt_calle + "&lugEnt_noInterior=" + pdflugEnt_noInterior + "&lugEnt_noExterior=" + pdflugEnt_noExterior + "&lugEnt_codigoPostal=" + pdflugEnt_codigoPostal + "&lugEnt_estado=" + pdflugEnt_estado + "&lugEnt_municipio=" + pdflugEnt_municipio + "&lugEnt_colonia=" + pdflugEnt_colonia + "&lugEnt_referencia=" + pdflugEnt_referencia + "&lugEnt_rfc=" + pdflugEnt_rfc + "&lugEnt_pais=" + pdflugEnt_pais + "&lugEnt_tipoTransporte=" + pdflugEnt_tipoTransporte + "&lugEnt_fechaHoraSalidaLlegada=" + pdflugEnt_fechaHoraSalidaLlegada + "&lugEnt_distancia=" + pdflugEnt_distancia + "&lugEnt_localidad=" + pdflugEnt_localidad + "&lugEnt_idUbicacion=" + pdflugEnt_idUbicacion + "&unidad_peso_cp=" + pdfunidad_peso_cp + "&mercancia_es_peligroso_cp=" + pdfmercancia_es_peligroso_cp + "&numpedimento_cp=" + pdfnumpedimento_cp + "&moneda_cp=" + pdfmoneda_cp + "&perm_sct=" + pdfperm_sct + "&no_permismo_sct=" + pdfno_permismo_sct + "&config_vehicular=" + pdfconfig_vehicular + "&placa_vm=" + pdfplaca_vm + "&anio_modelovm=" + pdfanio_modelovm + "&placa_remolque=" + pdfplaca_remolque + "&tipo_remolque=" + pdftipo_remolque + "&aseguradora_civil=" + pdfaseguradora_civil + "&poliza_civil=" + pdfpoliza_civil + "&tipo_figura=" + pdftipo_figura + "&rfc_figura=" + pdfrfc_figura + "&num_licencia=" + pdfnum_licencia + "&nombre_figura=" + pdfnombre_figura + "&autChofer_calle=" + pdfautChofer_calle + "&autChofer_noInterior=" + pdfautChofer_noInterior + "&autChofer_noExterior=" + pdfautChofer_noExterior + "&autChofer_colonia=" + pdfautChofer_colonia + "&autChofer_localidad=" + pdfautChofer_localidad + "&autChofer_municipio=" + pdfautChofer_municipio + "&autChofer_referencia=" + pdfautChofer_referencia + "&autChofer_estado=" + pdfautChofer_estado + "&autChofer_codigoPostal=" + pdfautChofer_codigoPostal + "&numConceptos=" + contadorConceptos + "&numCartaPorte=" + contadorConceptosCartaPorte + "&serie=" + serie + "&folio=" + folio + "&tipo=" + tipo + "&moneda_id=" + pdfmoneda_id + "&comprobante_id=" + pdfcomprobante_id + "&metodo_id=" + pdfmetodo_id + "&forma_id=" + pdfforma_id + url + url8);
    //console.log(urlPDF2);

    fetch("CartaPorteVistaPrevia.jsp?lugarDeEmbarque=" + pdflugarDeEmbarque + "&lugEmb_nombre=" + pdflugEmb_nombre + "&lugEmb_calle=" + pdflugEmb_calle + "&lugEmb_noInterior=" + pdflugEmb_noInterior + "&lugEmb_noExterior=" + pdflugEmb_noExterior + "&lugEmb_codigoPostal=" + pdflugEmb_codigoPostal + "&lugEmb_estado=" + pdflugEmb_estado + "&lugEmb_municipio=" + pdflugEmb_municipio + "&lugEmb_colonia=" + pdflugEmb_colonia + "&lugEmb_referencia=" + pdflugEmb_referencia + "&lugarDestinatario=" + pdflugarDestinatario + "&lugEmb_rfc=" + pdflugEmb_rfc + "&lugEmb_fechaHoraSalidaLlegada=" + pdflugEmb_fechaHoraSalidaLlegada + "&dest_nombre=" + pdfdest_nombre + "&dest_calle=" + pdfdest_calle + "&dest_noInterior=" + pdfdest_noInterior + "&dest_noExterior=" + pdfdest_noExterior + "&dest_codigoPostal=" + pdfdest_codigoPostal + "&dest_estado=" + pdfdest_estado + "&dest_municipio=" + pdfdest_municipio + "&dest_colonia=" + pdfdest_colonia + "&dest_referencia=" + pdfdest_referencia + "&dest_rfc=" + pdfdest_rfc + "&dest_fechaHoraSalidaLlegada=" + pdfdest_fechaHoraSalidaLlegada + "&dest_distancia=" + pdfdest_distancia + "&dest_localidad=" + pdfdest_localidad + "&dest_idUbicacion=" + pdfdest_idUbicacion + "&lugEmb_localidad=" + pdflugEmb_localidad + "&lugEmb_idUbicacion=" + pdflugEmb_idUbicacion + "&lugarEntrega=" + pdflugarEntrega + "&lugEnt_contacto=" + pdflugEnt_contacto + "&lugEnt_nombre=" + pdflugEnt_nombre + "&lugEnt_calle=" + pdflugEnt_calle + "&lugEnt_noInterior=" + pdflugEnt_noInterior + "&lugEnt_noExterior=" + pdflugEnt_noExterior + "&lugEnt_codigoPostal=" + pdflugEnt_codigoPostal + "&lugEnt_estado=" + pdflugEnt_estado + "&lugEnt_municipio=" + pdflugEnt_municipio + "&lugEnt_colonia=" + pdflugEnt_colonia + "&lugEnt_referencia=" + pdflugEnt_referencia + "&lugEnt_rfc=" + pdflugEnt_rfc + "&lugEnt_pais=" + pdflugEnt_pais + "&lugEnt_tipoTransporte=" + pdflugEnt_tipoTransporte + "&lugEnt_fechaHoraSalidaLlegada=" + pdflugEnt_fechaHoraSalidaLlegada + "&lugEnt_distancia=" + pdflugEnt_distancia + "&lugEnt_localidad=" + pdflugEnt_localidad + "&lugEnt_idUbicacion=" + pdflugEnt_idUbicacion + "&unidad_peso_cp=" + pdfunidad_peso_cp + "&mercancia_es_peligroso_cp=" + pdfmercancia_es_peligroso_cp + "&numpedimento_cp=" + pdfnumpedimento_cp + "&moneda_cp=" + pdfmoneda_cp + "&perm_sct=" + pdfperm_sct + "&no_permismo_sct=" + pdfno_permismo_sct + "&config_vehicular=" + pdfconfig_vehicular + "&placa_vm=" + pdfplaca_vm + "&anio_modelovm=" + pdfanio_modelovm + "&placa_remolque=" + pdfplaca_remolque + "&tipo_remolque=" + pdftipo_remolque + "&aseguradora_civil=" + pdfaseguradora_civil + "&poliza_civil=" + pdfpoliza_civil + "&tipo_figura=" + pdftipo_figura + "&rfc_figura=" + pdfrfc_figura + "&num_licencia=" + pdfnum_licencia + "&nombre_figura=" + pdfnombre_figura + "&autChofer_calle=" + pdfautChofer_calle + "&autChofer_noInterior=" + pdfautChofer_noInterior + "&autChofer_noExterior=" + pdfautChofer_noExterior + "&autChofer_colonia=" + pdfautChofer_colonia + "&autChofer_localidad=" + pdfautChofer_localidad + "&autChofer_municipio=" + pdfautChofer_municipio + "&autChofer_referencia=" + pdfautChofer_referencia + "&autChofer_estado=" + pdfautChofer_estado + "&autChofer_codigoPostal=" + pdfautChofer_codigoPostal + "&numConceptos=" + contadorConceptos + "&numCartaPorte=" + contadorConceptosCartaPorte + "&serie=" + serie + "&folio=" + folio + "&tipo=" + tipo + "&moneda_id=" + pdfmoneda_id + "&comprobante_id=" + pdfcomprobante_id + "&metodo_id=" + pdfmetodo_id + "&forma_id=" + pdfforma_id + url + url8, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                if (tipo == "1") {
                    document.getElementById('pdf2').src = "../MostrarPDF_PORTE";
                    setTimeout(function () {
                        openModalCartaPorte();
                    }, 1000);
                }
            }).catch(error => console.log(error));
}

function habilitarDatosCartaPorte(tipo) {

    if (tipo === '1') {

        div = document.getElementById('tab-LugarDeEmbarque');
        div.style.display = '';
        div = document.getElementById('tab-Destinatario');
        div.style.display = 'none';
        div = document.getElementById('tab-LugarDeEntrega');
        div.style.display = 'none';
        div = document.getElementById('tab-Mercancia');
        div.style.display = 'none';
        div = document.getElementById('tab-Autotransporte');
        div.style.display = 'none';

    } else if (tipo === '2') {

        div = document.getElementById('tab-Destinatario');
        div.style.display = '';
        div = document.getElementById('tab-LugarDeEmbarque');
        div.style.display = 'none';
        div = document.getElementById('tab-LugarDeEntrega');
        div.style.display = 'none';
        div = document.getElementById('tab-Mercancia');
        div.style.display = 'none';
        div = document.getElementById('tab-Autotransporte');
        div.style.display = 'none';

    } else if (tipo === '3') {

        div = document.getElementById('tab-LugarDeEntrega');
        div.style.display = '';
        div = document.getElementById('tab-LugarDeEmbarque');
        div.style.display = 'none';
        div = document.getElementById('tab-Destinatario');
        div.style.display = 'none';
        div = document.getElementById('tab-Mercancia');
        div.style.display = 'none';
        div = document.getElementById('tab-Autotransporte');
        div.style.display = 'none';

    } else if (tipo === '4') {
        //Mercancias
        div = document.getElementById('tab-Mercancia');
        div.style.display = '';
        div = document.getElementById('tab-LugarDeEmbarque');
        div.style.display = 'none';
        div = document.getElementById('tab-Destinatario');
        div.style.display = 'none';
        div = document.getElementById('tab-LugarDeEntrega');
        div.style.display = 'none';
        div = document.getElementById('tab-Autotransporte');
        div.style.display = 'none';

    } else if (tipo === '5') {

        div = document.getElementById('tab-Autotransporte');
        div.style.display = '';
        div = document.getElementById('tab-LugarDeEmbarque');
        div.style.display = 'none';
        div = document.getElementById('tab-Destinatario');
        div.style.display = 'none';
        div = document.getElementById('tab-LugarDeEntrega');
        div.style.display = 'none';
        div = document.getElementById('tab-Mercancia');
        div.style.display = 'none';
    }
}

function deshabilitarLugarEmbarque(tipo) {
    if (tipo === '1') {
        document.getElementById('lugEmb_nombre').disabled = true;
        document.getElementById('lugEmb_rfc').disabled = true;
        document.getElementById('lugEmb_calle').disabled = true;
        document.getElementById('lugEmb_noInterior').disabled = true;
        document.getElementById('lugEmb_noExterior').disabled = true;
        document.getElementById('lugEmb_codigoPostal').disabled = true;
        document.getElementById('lugEmb_estado').disabled = true;
        document.getElementById('lugEmb_municipio').disabled = true;
        document.getElementById('lugEmb_colonia').disabled = true;
        document.getElementById('lugEmb_referencia').disabled = true;
        document.getElementById('lugEmb_localidad').disabled = true;
        document.getElementById('lugEmb_idUbicacion').disabled = true;
        document.getElementById('LugarDeEmbarque').disabled = false;
    } else {
        document.getElementById('lugEmb_nombre').disabled = false;
        document.getElementById('lugEmb_rfc').disabled = false;
        document.getElementById('lugEmb_calle').disabled = false;
        document.getElementById('lugEmb_noInterior').disabled = false;
        document.getElementById('lugEmb_noExterior').disabled = false;
        document.getElementById('lugEmb_codigoPostal').disabled = false;
        document.getElementById('lugEmb_estado').disabled = false;
        document.getElementById('lugEmb_municipio').disabled = false;
        document.getElementById('lugEmb_colonia').disabled = false;
        document.getElementById('lugEmb_referencia').disabled = false;
        document.getElementById('lugEmb_localidad').disabled = false;
        document.getElementById('lugEmb_idUbicacion').disabled = false;
        document.getElementById('LugarDeEmbarque').disabled = true;

        document.getElementById('lugEmb_nombre').value = '';
        document.getElementById('lugEmb_rfc').value = '';
        document.getElementById('lugEmb_calle').value = '';
        document.getElementById('lugEmb_noInterior').value = '';
        document.getElementById('lugEmb_noExterior').value = '';
        document.getElementById('lugEmb_codigoPostal').value = '';
        document.getElementById('lugEmb_estado').value = '';
        document.getElementById('lugEmb_municipio').value = '';
        document.getElementById('lugEmb_colonia').value = '';
        document.getElementById('lugEmb_referencia').value = '';
        document.getElementById('lugEmb_localidad').value = '';
        document.getElementById('lugEmb_idUbicacion').value = '';
        document.getElementById("LugarDeEmbarque").value = '';
    }
    document.getElementById("tipoLugEmb").value = tipo;
}

function deshabilitarLugarDestinatario(tipo) {
    if (tipo === '1') {
        document.getElementById('dest_nombre').disabled = true;
        document.getElementById('dest_rfc').disabled = true;
        document.getElementById('dest_calle').disabled = true;
        document.getElementById('dest_noInterior').disabled = true;
        document.getElementById('dest_noExterior').disabled = true;
        document.getElementById('dest_codigoPostal').disabled = true;
        document.getElementById('dest_estado').disabled = true;
        document.getElementById('dest_municipio').disabled = true;
        document.getElementById('dest_colonia').disabled = true;
        document.getElementById('dest_referencia').disabled = true;
        document.getElementById('dest_localidad').disabled = true;
        document.getElementById('dest_idUbicacion').disabled = true;
        document.getElementById('LugarDestinatario').disabled = false;
    } else {
        document.getElementById('dest_nombre').disabled = false;
        document.getElementById('dest_rfc').disabled = false;
        document.getElementById('dest_calle').disabled = false;
        document.getElementById('dest_noInterior').disabled = false;
        document.getElementById('dest_noExterior').disabled = false;
        document.getElementById('dest_codigoPostal').disabled = false;
        document.getElementById('dest_estado').disabled = false;
        document.getElementById('dest_municipio').disabled = false;
        document.getElementById('dest_colonia').disabled = false;
        document.getElementById('dest_referencia').disabled = false;
        document.getElementById('dest_localidad').disabled = false;
        document.getElementById('dest_idUbicacion').disabled = false;
        document.getElementById('LugarDestinatario').disabled = true;

        document.getElementById('dest_nombre').value = '';
        document.getElementById('dest_rfc').value = '';
        document.getElementById('dest_calle').value = '';
        document.getElementById('dest_noInterior').value = '';
        document.getElementById('dest_noExterior').value = '';
        document.getElementById('dest_codigoPostal').value = '';
        document.getElementById('dest_estado').value = '';
        document.getElementById('dest_municipio').value = '';
        document.getElementById('dest_colonia').value = '';
        document.getElementById('dest_referencia').value = '';
        document.getElementById('dest_localidad').value = '';
        document.getElementById('dest_idUbicacion').value = '';
        document.getElementById('LugarDestinatario').value = '';
    }
    document.getElementById("tipoDest").value = tipo;
}

function deshabilitarLugarEntrega(tipo) {
    if (tipo === '1') {
        document.getElementById('lugEnt_contacto').disabled = true;
        document.getElementById('lugEnt_rfc').disabled = true;
        document.getElementById('lugEnt_nombre').disabled = true;
        document.getElementById('lugEnt_calle').disabled = true;
        document.getElementById('lugEnt_noInterior').disabled = true;
        document.getElementById('lugEnt_noExterior').disabled = true;
        document.getElementById('lugEnt_codigoPostal').disabled = true;
        document.getElementById('lugEnt_estado').disabled = true;
        document.getElementById('lugEnt_municipio').disabled = true;
        document.getElementById('lugEnt_colonia').disabled = true;
        document.getElementById('lugEnt_referencia').disabled = true;
        document.getElementById('lugEnt_pais').disabled = true;
        document.getElementById('lugEnt_tipoTransporte').disabled = true;
        document.getElementById('lugEnt_localidad').disabled = true;
        document.getElementById('lugEnt_idUbicacion').disabled = true;
        document.getElementById('LugarEntrega').disabled = false;
    } else {
        document.getElementById('lugEnt_contacto').disabled = false;
        document.getElementById('lugEnt_rfc').disabled = false;
        document.getElementById('lugEnt_nombre').disabled = false;
        document.getElementById('lugEnt_calle').disabled = false;
        document.getElementById('lugEnt_noInterior').disabled = false;
        document.getElementById('lugEnt_noExterior').disabled = false;
        document.getElementById('lugEnt_codigoPostal').disabled = false;
        document.getElementById('lugEnt_estado').disabled = false;
        document.getElementById('lugEnt_municipio').disabled = false;
        document.getElementById('lugEnt_colonia').disabled = false;
        document.getElementById('lugEnt_referencia').disabled = false;
        document.getElementById('lugEnt_pais').disabled = false;
        document.getElementById('lugEnt_tipoTransporte').disabled = false;
        document.getElementById('lugEnt_localidad').disabled = false;
        document.getElementById('lugEnt_idUbicacion').disabled = false;
        document.getElementById('LugarEntrega').disabled = true;

        document.getElementById('lugEnt_contacto').value = '';
        document.getElementById('lugEnt_rfc').value = '';
        document.getElementById('lugEnt_nombre').value = '';
        document.getElementById('lugEnt_calle').value = '';
        document.getElementById('lugEnt_noInterior').value = '';
        document.getElementById('lugEnt_noExterior').value = '';
        document.getElementById('lugEnt_codigoPostal').value = '';
        document.getElementById('lugEnt_estado').value = '';
        document.getElementById('lugEnt_municipio').value = '';
        document.getElementById('lugEnt_colonia').value = '';
        document.getElementById('lugEnt_referencia').value = '';
        document.getElementById('lugEnt_pais').value = '';
        document.getElementById('lugEnt_tipoTransporte').value = '';
        document.getElementById('lugEnt_localidad').value = '';
        document.getElementById('lugEnt_idUbicacion').value = '';
        document.getElementById('LugarEntrega').value = '';
    }
    document.getElementById("tipoLugEnt").value = tipo;
}

function consultarTipoLugar(tipo) {

    let idLugarEmbarque = document.getElementById("LugarDeEmbarque").value;
    let idLugarDestinatario = document.getElementById("LugarDestinatario").value;
    let idLugarEntrega = document.getElementById("LugarEntrega").value;

    fetch("../ConsultarLugares?idTipo=" + tipo + "&idLugarEmbarque=" + idLugarEmbarque + "&idLugarDestinatario=" + idLugarDestinatario + "&idLugarEntrega=" + idLugarEntrega, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {

                let part = data.split("*");
                //SecciÃ³n General;
                let c0 = part[0];   //clvtl_id
                let c1 = part[1];   //nombre
                let c2 = part[2];   //rfc
                let c3 = part[3];   //calle
                let c4 = part[4];   //no interior
                let c5 = part[5];   //no exterior
                let c6 = part[6];   //codigo postal
                let c7 = part[7];   //estado
                let c8 = part[8];   //municipio
                let c9 = part[9];   //colonia
                let c10 = part[10]; //referencia
                let c11 = part[11]; //localidad
                let c12 = part[12]; //pais
                let c13 = part[13]; //transporte internacional
                let c14 = part[14]; //tipo ubicación

                if (tipo == "1") {
                    document.getElementById("lugEmb_nombre").value = c1;
                    document.getElementById("lugEmb_rfc").value = c2;
                    document.getElementById("lugEmb_calle").value = c3;
                    document.getElementById("lugEmb_noInterior").value = c4;
                    document.getElementById("lugEmb_noExterior").value = c5;
                    document.getElementById("lugEmb_codigoPostal").value = c6;
                    document.getElementById("lugEmb_estado").value = c7;
                    document.getElementById("lugEmb_municipio").value = c8;
                    document.getElementById("lugEmb_colonia").value = c9;
                    document.getElementById("lugEmb_referencia").value = c10;
                    document.getElementById("lugEmb_localidad").value = c11;
                    document.getElementById("lugEmb_idUbicacion").value = c14;
                } else if (tipo == "2") {
                    document.getElementById("dest_nombre").value = c1;
                    document.getElementById("dest_rfc").value = c2;
                    document.getElementById("dest_calle").value = c3;
                    document.getElementById("dest_noInterior").value = c4;
                    document.getElementById("dest_noExterior").value = c5;
                    document.getElementById("dest_codigoPostal").value = c6;
                    document.getElementById("dest_estado").value = c7;
                    document.getElementById("dest_municipio").value = c8;
                    document.getElementById("dest_colonia").value = c9;
                    document.getElementById("dest_referencia").value = c10;
                    document.getElementById("dest_localidad").value = c11;
                    document.getElementById("dest_idUbicacion").value = c14;
                } else if (tipo == "3") {
                    document.getElementById("lugEnt_nombre").value = c1;
                    document.getElementById("lugEnt_rfc").value = c2;
                    document.getElementById("lugEnt_calle").value = c3;
                    document.getElementById("lugEnt_noInterior").value = c4;
                    document.getElementById("lugEnt_noExterior").value = c5;
                    document.getElementById("lugEnt_codigoPostal").value = c6;
                    document.getElementById("lugEnt_estado").value = c7;
                    document.getElementById("lugEnt_municipio").value = c8;
                    document.getElementById("lugEnt_colonia").value = c9;
                    document.getElementById("lugEnt_referencia").value = c10;
                    document.getElementById("lugEnt_localidad").value = c11;
                    document.getElementById("lugEnt_pais").value = c12;
                    document.getElementById("lugEnt_tipoTransporte").value = c13;
                    document.getElementById("lugEnt_idUbicacion").value = c14;
                }

            }).catch(error => console.log(error));
}

function AddCartaPorte() {
    let check1 = "";
    let check2 = "";
    let check3 = "";

    //Parametros: Embarque
    let addLugarDeEmbarque = document.getElementById("LugarDeEmbarque").value;
    let addlugEmb_nombre = document.getElementById("lugEmb_nombre").value;
    let addlugEmb_rfc = document.getElementById("lugEmb_rfc").value;
    let addlugEmb_calle = document.getElementById("lugEmb_calle").value;
    let addlugEmb_noInterior = document.getElementById("lugEmb_noInterior").value;
    let addlugEmb_noExterior = document.getElementById("lugEmb_noExterior").value;
    let addlugEmb_codigoPostal = document.getElementById("lugEmb_codigoPostal").value;
    let addlugEmb_estado = document.getElementById("lugEmb_estado").value;
    let addlugEmb_municipio = document.getElementById("lugEmb_municipio").value;
    let addlugEmb_colonia = document.getElementById("lugEmb_colonia").value;
    let addlugEmb_referencia = document.getElementById("lugEmb_referencia").value;
    let addlugEmb_fechaHoraSalidaLlegada = document.getElementById("lugEmb_fechaHoraSalidaLlegada").value;
    //let addlugEmb_distancia = document.getElementById("lugEmb_distancia").value;
    let addlugEmb_localidad = document.getElementById("lugEmb_localidad").value;
    let addlugEmb_idUbicacion = document.getElementById("lugEmb_idUbicacion").value;

    //Parametros: Destino
    let addLugarDestinatario = document.getElementById("LugarDestinatario").value;
    let adddest_nombre = document.getElementById("dest_nombre").value;
    let adddest_rfc = document.getElementById("dest_rfc").value;
    let adddest_calle = document.getElementById("dest_calle").value;
    let adddest_noInterior = document.getElementById("dest_noInterior").value;
    let adddest_noExterior = document.getElementById("dest_noExterior").value;
    let adddest_codigoPostal = document.getElementById("dest_codigoPostal").value;
    let adddest_estado = document.getElementById("dest_estado").value;
    let adddest_municipio = document.getElementById("dest_municipio").value;
    let adddest_colonia = document.getElementById("dest_colonia").value;
    let adddest_referencia = document.getElementById("dest_referencia").value;
    let adddest_fechaHoraSalidaLlegada = document.getElementById("dest_fechaHoraSalidaLlegada").value;
    let adddest_distancia = document.getElementById("dest_distancia").value;
    let adddest_localidad = document.getElementById("dest_localidad").value;
    let adddest_idUbicacion = document.getElementById("dest_idUbicacion").value;

    //Parametros: Entrega
    let addLugarEntrega = document.getElementById("LugarEntrega").value;
    let addlugEnt_contacto = document.getElementById("lugEnt_contacto").value;
    let addlugEnt_nombre = document.getElementById("lugEnt_nombre").value;
    let addlugEnt_rfc = document.getElementById("lugEnt_rfc").value;
    let addlugEnt_calle = document.getElementById("lugEnt_calle").value;
    let addlugEnt_noInterior = document.getElementById("lugEnt_noInterior").value;
    let addlugEnt_noExterior = document.getElementById("lugEnt_noExterior").value;
    let addlugEnt_codigoPostal = document.getElementById("lugEnt_codigoPostal").value;
    let addlugEnt_estado = document.getElementById("lugEnt_estado").value;
    let addlugEnt_municipio = document.getElementById("lugEnt_municipio").value;
    let addlugEnt_colonia = document.getElementById("lugEnt_colonia").value;
    let addlugEnt_referencia = document.getElementById("lugEnt_referencia").value;
    let addlugEnt_pais = document.getElementById("lugEnt_pais").value;
    let addlugEnt_tipoTransporte = document.getElementById("lugEnt_tipoTransporte").value;
    let addlugEnt_fechaHoraSalidaLlegada = document.getElementById("lugEnt_fechaHoraSalidaLlegada").value;
    let addlugEnt_distancia = document.getElementById("lugEnt_distancia").value;
    let addlugEnt_localidad = document.getElementById("lugEnt_localidad").value;
    let addlugEnt_idUbicacion = document.getElementById("lugEnt_idUbicacion").value;

    //Parametros: Mercancía
    let addunidad_peso_cp = document.getElementById("unidad_peso_cp").value;         //campo nuevo
    //let addmercancia_peso_cp = document.getElementById("mercancia_peso_cp").value;
    let addmercancia_es_peligroso_cp = document.getElementById("mercancia_es_peligroso_cp").value;
    let addnumpedimento_cp = document.getElementById("numpedimento_cp").value;
    let addmoneda_cp = document.getElementById("moneda_cp").value;

    //Parametros: Autotransporte
    let addperm_sct = document.getElementById("perm_sct").value;
    let addno_permismo_sct = document.getElementById("no_permismo_sct").value;
    let addconfig_vehicular = document.getElementById("config_vehicular").value;
    let addplaca_vm = document.getElementById("placa_vm").value;
    let addanio_modelovm = document.getElementById("anio_modelovm").value;
    let addplaca_remolque = document.getElementById("placa_remolque").value;  //campo nuevo
    let addtipo_remolque = document.getElementById("tipo_remolque").value;    //campo nuevo
    let addaseguradora_civil = document.getElementById("aseguradora_civil").value;
    let addpoliza_civil = document.getElementById("poliza_civil").value;
    let addtipo_figura = document.getElementById("tipo_figura").value;
    let addrfc_figura = document.getElementById("rfc_figura").value;
    let addnum_licencia = document.getElementById("num_licencia").value;
    let addnombre_figura = document.getElementById("nombre_figura").value;

    let autChofer_calle = document.getElementById("autChofer_calle").value;
    let autChofer_noInterior = document.getElementById("autChofer_noInterior").value;
    let autChofer_noExterior = document.getElementById("autChofer_noExterior").value;
    let autChofer_colonia = document.getElementById("autChofer_colonia").value;
    let autChofer_localidad = document.getElementById("autChofer_localidad").value;
    let autChofer_municipio = document.getElementById("autChofer_municipio").value;
    let autChofer_referencia = document.getElementById("autChofer_referencia").value;
    let autChofer_estado = document.getElementById("autChofer_estado").value;
    let autChofer_codigoPostal = document.getElementById("autChofer_codigoPostal").value;

    if (addlugEmb_calle == "") {
        swal("Error", "Ingrese domicilio origen", "error");
        return false;
    }

    if (adddest_calle == "") {
        swal("Error", "Ingrese domicilio destinatario", "error");
        return false;
    }

    if (adddest_distancia == null || adddest_distancia == 0) {
        swal("Error", "Ingrese distancia destino", "error");
        return false;
    }

    if (addlugEnt_calle == "") {
        swal("Error", "Ingrese domicilio de entrega", "error");
        return false;
    }

    if (addlugEnt_distancia == null || addlugEnt_distancia == 0) {
        swal("Error", "Ingrese distancia entrega", "error");
        return false;
    }

    if (addperm_sct == null || addperm_sct == 0) {
        swal("Error", "Ingrese Perm SCT", "error");
        return false;
    }

    if (addno_permismo_sct == null) {
        swal("Error", "Ingrese información de autotransporte", "error");
        return false;
    }

    if (addconfig_vehicular == null || addperm_sct == 0) {
        swal("Error", "Ingrese Configuración Vehicular", "error");
        return false;
    }

    if (addplaca_vm == null) {
        swal("Error", "Ingrese Placa VM", "error");
        return false;
    }

    if (addanio_modelovm == null) {
        swal("Error", "Ingrese Año Modelo VM", "error");
        return false;
    }

    if (addaseguradora_civil == null) {
        swal("Error", "Ingrese Aseguradora Responsabilidad Civil", "error");
        return false;
    }

    if (addpoliza_civil == null) {
        swal("Error", "Ingrese Poliza Responsabilidad Civil", "error");
        return false;
    }

    if (addtipo_figura == null || addtipo_figura == 0) {
        swal("Error", "Ingrese Tipo Figura", "error");
        return false;
    }

    if (addrfc_figura == null) {
        swal("Error", "Ingrese RFC Figura", "error");
        return false;
    }

    if (addnum_licencia == null) {
        swal("Error", "Ingrese Núm.Licencia", "error");
        return false;
    }

    if (addnombre_figura == null) {
        swal("Error", "Ingrese Nombre Figura", "error");
        return false;
    }

    if (contadorConceptosCartaPorte <= 0) {
        swal("Error", "Ingrese mercancía", "error");
        return false;
    }

    if (document.getElementById("check_lugarEmbarque").checked) {
        check1 = "&check_lugarEmbarque=1";
    } else {
        check1 = "&check_lugarEmbarque=0";
    }

    if (document.getElementById("check_lugarDestinatario").checked) {
        check2 = "&check_lugarDestinatario=1";
    } else {
        check2 = "&check_lugarDestinatario=0";
    }

    if (document.getElementById("check_lugarEntrega").checked) {
        check3 = "&check_lugarEntrega=1";
    } else {
        check3 = "&check_lugarEntrega=0";
    }

    $("#modalCartaDePorte").modal("hide");

    fetch("../AddTablaCartaPorte?LugarDeEmbarque=" + addLugarDeEmbarque + "&lugEmb_nombre=" + addlugEmb_nombre + "&lugEmb_rfc=" + addlugEmb_rfc + "&lugEmb_calle=" + addlugEmb_calle + "&lugEmb_noInterior=" + addlugEmb_noInterior + "&lugEmb_noExterior=" + addlugEmb_noExterior + "&lugEmb_codigoPostal=" + addlugEmb_codigoPostal + "&lugEmb_estado=" + addlugEmb_estado + "&lugEmb_municipio=" + addlugEmb_municipio + "&lugEmb_colonia=" + addlugEmb_colonia + "&lugEmb_referencia=" + addlugEmb_referencia + "&lugEmb_fechaHoraSalidaLlegada=" + addlugEmb_fechaHoraSalidaLlegada + "&lugEmb_localidad=" + addlugEmb_localidad + "&lugEmb_idUbicacion=" + addlugEmb_idUbicacion + "&LugarDestinatario=" + addLugarDestinatario + "&dest_nombre=" + adddest_nombre + "&dest_rfc=" + adddest_rfc + "&dest_calle=" + adddest_calle + "&dest_noInterior=" + adddest_noInterior + "&dest_noExterior=" + adddest_noExterior + "&dest_codigoPostal=" + adddest_codigoPostal + "&dest_estado=" + adddest_estado + "&dest_municipio=" + adddest_municipio + "&dest_colonia=" + adddest_colonia + "&dest_referencia=" + adddest_referencia + "&dest_fechaHoraSalidaLlegada=" + adddest_fechaHoraSalidaLlegada + "&dest_distancia=" + adddest_distancia + "&dest_localidad=" + adddest_localidad + "&dest_idUbicacion=" + adddest_idUbicacion + "&LugarEntrega=" + addLugarEntrega + "&lugEnt_contacto=" + addlugEnt_contacto + "&lugEnt_nombre=" + addlugEnt_nombre + "&lugEnt_rfc=" + addlugEnt_rfc + "&lugEnt_calle=" + addlugEnt_calle + "&lugEnt_noInterior=" + addlugEnt_noInterior + "&lugEnt_noExterior=" + addlugEnt_noExterior + "&lugEnt_codigoPostal=" + addlugEnt_codigoPostal + "&lugEnt_estado=" + addlugEnt_estado + "&lugEnt_municipio=" + addlugEnt_municipio + "&lugEnt_colonia=" + addlugEnt_colonia + "&lugEnt_referencia=" + addlugEnt_referencia + "&lugEnt_pais=" + addlugEnt_pais + "&lugEnt_tipoTransporte=" + addlugEnt_tipoTransporte + "&lugEnt_fechaHoraSalidaLlegada=" + addlugEnt_fechaHoraSalidaLlegada + "&lugEnt_distancia=" + addlugEnt_distancia + "&lugEnt_localidad=" + addlugEnt_localidad + "&lugEnt_idUbicacion=" + addlugEnt_idUbicacion + "&unidad_peso_cp=" + addunidad_peso_cp + "&moneda_cp=" + addmoneda_cp + "&mercancia_es_peligroso_cp=" + addmercancia_es_peligroso_cp + "&numpedimento_cp=" + addnumpedimento_cp + "&perm_sct=" + addperm_sct + "&no_permismo_sct=" + addno_permismo_sct + "&config_vehicular=" + addconfig_vehicular + "&placa_vm=" + addplaca_vm + "&anio_modelovm=" + addanio_modelovm + "&placa_remolque=" + addplaca_remolque + "&tipo_remolque=" + addtipo_remolque + "&aseguradora_civil=" + addaseguradora_civil + "&poliza_civil=" + addpoliza_civil + "&tipo_figura=" + addtipo_figura + "&rfc_figura=" + addrfc_figura + "&num_licencia=" + addnum_licencia + "&nombre_figura=" + addnombre_figura + "&autChofer_calle=" + autChofer_calle + "&autChofer_noInterior=" + autChofer_noInterior + "&autChofer_noExterior=" + autChofer_noExterior + "&autChofer_colonia=" + autChofer_colonia + "&autChofer_localidad=" + autChofer_localidad + "&autChofer_municipio=" + autChofer_municipio + "&autChofer_referencia=" + autChofer_referencia + "&autChofer_estado=" + autChofer_estado + "&autChofer_codigoPostal=" + autChofer_codigoPostal, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                urlParametrosCartaPorte = data + "" + check1 + "" + check2 + check3;

            }).catch(error => console.log(error));

}

function cleanFormularioConceptoCartaPorte() {
    document.getElementById("mercancia_descripcion").value = '';
    document.getElementById("cantidad_cp").value = '';
    document.getElementById("rel_clvsat_id_cp").value = '';
    document.getElementById("unidad_sat_id_cp").value = '';
    document.getElementById("no_pedimento_cp").value = '';
    document.getElementById("precio_unitario_cp").value = '';
}

function Api_CartaPorte(id, tipoGeneracion, timbrado) {
    const Http = new XMLHttpRequest();
    
    //const url = 'https://www.tacts.mx/ClienteSifei3.3/getXML.jsp?idf=' + id + '&cve=' + cbdiv_id + '&tipo=2';
    const url = 'https://www.tacts.mx/ClienteSifeiTEST3.3/getXML.jsp?idf=' + id + '&cve=' + cbdiv_id + '&tipo=2';
    
    Http.open("GET", url);
    Http.send();

    Http.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            let respuesta = Http.responseText;
            let palabra = "200";
            let index = respuesta.indexOf(palabra);

            if (index >= 0) {

                //Generación de PDF'S
                visorPdfFactura(2);      // (1 = No Marcado de ruta pdf) | (2= Marcado de ruta pdf)
                visorPdfCartaPorte(2);   // (1 = No Marcado de ruta pdf) | (2= Marcado de ruta pdf)

                //Almacenamiento de PDF'S
                savegeneratepdf(tipoGeneracion);    //tipoGeneracion(cfdi ó cartaPorte)

                //Envío de PDF'S    
                sendmail(tipoGeneracion, timbrado);/*tipoGeneracion(cartaPorte)|tipoTimbrado(si XML)*/
            } else {
                swal("Error", "Detalle: " + respuesta, "error");
                hidePreloader();
                return false;
            }
        }
    }
}
     
function ubicacionLugar() {
    document.getElementById('lugEmb_idUbicacion').value = "OR";
}

function ubicacionDestino() {
    document.getElementById('dest_idUbicacion').value = "DE";
}

function ubicacionEntrega() {
    document.getElementById('lugEnt_idUbicacion').value = "DE";
}
                 
function openModalCartaPorte() {
    $("#modalVistaPreviaCartaPorte").modal("show");
}

function hbltDomiciliOperador(id) {

    if (id == "01") {
        div = document.getElementById('autChofer1');
        div.style.display = '';
        div = document.getElementById('autChofer2');
        div.style.display = '';
        div = document.getElementById('autChofer3');
        div.style.display = '';
        div = document.getElementById('autChofer4');
        div.style.display = '';
        div = document.getElementById('autChofer5');
        div.style.display = '';
        div = document.getElementById('autChofer6');
        div.style.display = '';
    } else {
        div = document.getElementById('autChofer1');
        div.style.display = 'none';
        div = document.getElementById('autChofer2');
        div.style.display = 'none';
        div = document.getElementById('autChofer3');
        div.style.display = 'none';
        div = document.getElementById('autChofer4');
        div.style.display = 'none';
        div = document.getElementById('autChofer5');
        div.style.display = 'none';
        div = document.getElementById('autChofer6');
        div.style.display = 'none';
    }
}

function habilitarConceptoMercancia() {
    document.getElementById('mercancia_descripcion').disabled = false;
    document.getElementById('cantidad_cp').disabled = false;
    document.getElementById('rel_clvsat_id_cp').disabled = false;
    document.getElementById('unidad_sat_id_cp').disabled = false;
    document.getElementById('no_pedimento_cp').disabled = false;
    document.getElementById('btnConceptocp').disabled = false;
}

$("#precio_unitario_cp").on('blur change input', function () {
    $(this).val(function (i, input) {
        input = input.replace(/\D/g, '');
        return (input / 100).toFixed(2);
    });
}).trigger('blur');
