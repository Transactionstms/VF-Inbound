/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function add_modalConceptos(caramelo) {

    let part = caramelo.split("*");
    //Sección General;
    let c0 = part[0];   //contador
    let c1 = part[1];   //descripción
    let c2 = part[2];   //cantidad
    let c3 = part[3];   //num identificación
    let c4 = part[4];   //precio unitario
    let c5 = part[5];   //Clave Prod/Serv SAT
    let c6 = part[6];   //Clave Unidad SAT
    let c7 = part[7];   //importe descuento
    let c8 = part[8];   //unidad medida

    let importeGral = c2 * c4;

    //Sección General;
    document.getElementById("id.contador.concepto").value = c0;
    document.getElementById("up_desc").value = c1;
    document.getElementById("up_cantidad").value = c2;
    document.getElementById("up_noIdentificacion").value = c3;
    document.getElementById("up_precioUnitario").value = parseFloat(c4).toFixed(2);
    document.getElementById("up_claveProdServ").value = c5;
    document.getElementById("up_claveUnidad").value = c6;
    document.getElementById("up_descuento").value = parseFloat(c7).toFixed(2);
    document.getElementById("up_unidadMedida").value = c8;
    //Sección Impuestos:
    document.getElementById("up_base").value = parseFloat(importeGral).toFixed(2);
}

function habilitarDatosConceptos(tipo) {

    if (tipo === '1') {

        div = document.getElementById('tabGeneral');
        div.style.display = '';

        div = document.getElementById('tabImpuestos');
        div.style.display = 'none';
        div = document.getElementById('tabOtros');
        div.style.display = 'none';
        div = document.getElementById('tabPartes');
        div.style.display = 'none';

    } else if (tipo === '2') {

        div = document.getElementById('tabImpuestos');
        div.style.display = '';

        div = document.getElementById('tabGeneral');
        div.style.display = 'none';
        div = document.getElementById('tabOtros');
        div.style.display = 'none';
        div = document.getElementById('tabPartes');
        div.style.display = 'none';

    } else if (tipo === '3') {

        div = document.getElementById('tabOtros');
        div.style.display = '';

        div = document.getElementById('tabGeneral');
        div.style.display = 'none';
        div = document.getElementById('tabImpuestos');
        div.style.display = 'none';
        div = document.getElementById('tabPartes');
        div.style.display = 'none';

    } else if (tipo === '4') {

        div = document.getElementById('tabPartes');
        div.style.display = '';

        div = document.getElementById('tabGeneral');
        div.style.display = 'none';
        div = document.getElementById('tabImpuestos');
        div.style.display = 'none';
        div = document.getElementById('tabOtros');
        div.style.display = 'none';
    }

}

function habilitarDetalle() {

    if (document.getElementById("check_comentario").checked) {

        div = document.getElementById('flotante_detalle');
        div.style.display = '';

        div = document.getElementById('flotante_concepto1');
        div.style.display = 'none';
        div = document.getElementById('flotante_concepto2');
        div.style.display = 'none';
        div = document.getElementById('flotante_concepto3');
        div.style.display = 'none';
        div = document.getElementById('flotante_concepto4');
        div.style.display = 'none';
        div = document.getElementById('flotante_concepto5');
        div.style.display = 'none';

        document.getElementById("concepto_id").value = '';
        document.getElementById("cantidad").value = '';
        document.getElementById("rel_clvsat_id").value = '';
        document.getElementById("unidad_sat_id").value = '';
        document.getElementById("precio_unitario").value = '';
    } else {

        div = document.getElementById('flotante_detalle');
        div.style.display = 'none';

        div = document.getElementById('flotante_concepto1');
        div.style.display = '';
        div = document.getElementById('flotante_concepto2');
        div.style.display = '';
        div = document.getElementById('flotante_concepto3');
        div.style.display = '';
        div = document.getElementById('flotante_concepto4');
        div.style.display = '';
        div = document.getElementById('flotante_concepto5');
        div.style.display = '';

        document.getElementById("comentarios").value = '';
    }
}

function consultarTipoImpuesto(idTipoImpuesto) {

    v_tipoImpuesto = idTipoImpuesto;
    let select = document.getElementById("up_impuesto");
    let select2 = document.getElementById("up_tipoFactor");
    let select3 = document.getElementById("up_tasa");
    document.getElementById("up_Cuota").value = 0;
    inhabilitarTasaCuota();

    let impuesto;

    if (v_tipoImpuesto === "1") {                     //TIPO IMPUESTO: Traslado
        impuesto = ["0/-- Seleccione Impuesto --", "2/IVA", "3/IEPS"];
    } else if (v_tipoImpuesto === "2") {               //TIPO IMPUESTO: RetenciÃ³n
        impuesto = ["0/-- Seleccione Impuesto --", "1/ISR", "2/IVA", "3/IEPS"];
    }

    //Limpiar: Impuesto
    for (let i = select.options.length; i >= 0; i--) {
        select.remove(i);
    }
    //Limpiar: Tipo Factor
    for (let i = select2.options.length; i >= 0; i--) {
        select2.remove(i);
    }
    //Limpiar: Tipo Tasa
    for (let i = select3.options.length; i >= 0; i--) {
        select3.remove(i);
    }

    //Cargar: Impuesto    
    for (let i = 0; i < impuesto.length; i++) {
        const myArray = impuesto[i].split("/");

        let id = myArray[0];
        let desc = myArray[1];

        let option = document.createElement("option");    //Creamos la opcion 
        option.value = id;
        option.text = desc;
        select.appendChild(option);                   //Metemos la opciÃ³n en el select
    }

}

function consultarTipoFactor(idImpuesto) {

    v_impuesto = idImpuesto;
    let select = document.getElementById("up_tipoFactor");
    let factor;

    if (v_tipoImpuesto == "1") {                       //TIPO IMPUESTO: TRASLADO

        if (v_impuesto === "2") {                              //IMPUESTO: IVA
            factor = ["0/-- Seleccione Tipo Factor --", "1/TASA", "3/EXENTO"];
        } else if (v_impuesto === "3") {                        //IMPUESTO: IEPS 
            factor = ["0/-- Seleccione Tipo Factor --", "1/TASA", "2/CUOTA", "3/EXENTO"];
        }

    } else if (v_tipoImpuesto == "2") {                //TIPO IMPUESTO: RETENCIÃN

        if (v_impuesto === "1") {                              //IMPUESTO: ISR
            factor = ["0/-- Seleccione Tipo Factor --", "1/TASA"];
        } else if (v_impuesto === "2") {                        //IMPUESTO: IVA 
            factor = ["0/-- Seleccione Tipo Factor --", "1/TASA"];
        } else if (v_impuesto === "3") {                        //IMPUESTO: IEPS 
            factor = ["0/-- Seleccione Tipo Factor --", "1/TASA", "2/CUOTA"];
        }

    }

    //Limpiar: Tipo Factor
    for (let i = select.options.length; i >= 0; i--) {
        select.remove(i);
    }

    //Cargar: Tipo Factor  
    for (let i = 0; i < factor.length; i++) {
        const myArray = factor[i].split("/");

        let id = myArray[0];
        let desc = myArray[1];

        let option = document.createElement("option");  //Creamos la opcion 
        option.value = id;
        option.text = desc;
        select.appendChild(option);                 //Metemos la opciÃ³n en el select
    }

}

function consultarTipoTasa(idTipoFactor) {

    v_tipo_factor = idTipoFactor;
    let select = document.getElementById("up_tasa");
    let tasa;

    if (v_tipoImpuesto == "1") {                       //TIPO IMPUESTO: TRASLADO

        if (v_impuesto === "2") {                              //IMPUESTO: IVA
            if (v_tipo_factor === "1") {                     //TIPO_FACTOR: TASA
                tasa = ["0/-- Seleccione una Tasa --", "13/0%", "5/16%", "9/8%"];
                habilitarTasa();
            } else if (v_tipo_factor === "3") {               //TIPO_FACTOR: EXENTO
                inhabilitarTasaCuota();
            }
        } else if (v_impuesto === "3") {                        //IMPUESTO: IEPS
            if (v_tipo_factor === "1") {                     //TIPO_FACTOR: TASA
                tasa = ["0/-- Seleccione una Tasa --", "1/26.5%", "2/30%", "3/53%", "4/50%", "5/16%", "6/30.4%", "7/25%", "8/9%", "9/8%", "10/7%", "12/6.3%", "13/0%"];
                habilitarTasa();
            } else if (v_tipo_factor === "2") {               //TIPO_FACTOR: CUOTA
                habilitarCuota();
            } else if (v_tipo_factor === "3") {               //TIPO_FACTOR: EXENTO
                inhabilitarTasaCuota();
            }
        }

    } else if (v_tipoImpuesto == "2") {                //TIPO IMPUESTO: RETENCIÃN

        if (v_impuesto === "1") {                              //IMPUESTO: ISR
            if (v_tipo_factor === "1") {                     //TIPO_FACTOR: TASA
                habilitarCuota();
            }
        } else if (v_impuesto === "2") {                       //IMPUESTO: IVA
            if (v_tipo_factor === "1") {                     //TIPO_FACTOR: TASA
                habilitarCuota();
            }
        } else if (v_impuesto === "3") {                        //IMPUESTO: IEPS
            if (v_tipo_factor === "1") {                     //TIPO_FACTOR: TASA
                tasa = ["0/-- Seleccione una Tasa --", "1/26.5%", "2/30%", "3/53%", "4/50%", "5/16%", "6/30.4%", "7/25%", "8/9%", "9/8%", "10/7%", "11/6%"];
                habilitarTasa();
            } else if (v_tipo_factor === "2") {               //TIPO_FACTOR: CUOTA
                habilitarCuota();
            }
        }

    }

    //Limpiar: Tipo Tasa
    for (let i = select.options.length; i >= 0; i--) {
        select.remove(i);
    }

    //Cargar: Tipo Tasa  
    for (var i = 0; i < tasa.length; i++) {
        const myArray = tasa[i].split("/");

        let id = myArray[0];
        let desc = myArray[1];

        var option = document.createElement("option"); //Creamos la opcion
        option.value = id;
        option.text = desc;
        select.appendChild(option); //Metemos la opciÃ³n en el select
    }

}

function habilitarTasa() {
    div = document.getElementById('tab-tasa');
    div.style.display = '';
    div = document.getElementById('tab-cuota');
    div.style.display = 'none';
}

function habilitarCuota() {
    div = document.getElementById('tab-cuota');
    div.style.display = '';
    div = document.getElementById('tab-tasa');
    div.style.display = 'none';
}

function inhabilitarTasaCuota() {
    div = document.getElementById('tab-cuota');
    div.style.display = 'none';
    div = document.getElementById('tab-tasa');
    div.style.display = 'none';
}

$("#precio_unitario").on('blur change input', function () {
    $(this).val(function (i, input) {
        input = input.replace(/\D/g, '');
        return (input / 100).toFixed(2);
    });
}).trigger('blur');

$("#up_precioUnitario").on('blur change input', function () {
    $(this).val(function (i, input) {
        input = input.replace(/\D/g, '');
        return (input / 100).toFixed(2);
    });
}).trigger('blur');

$("#up_descuento").on('blur change input', function () {
    $(this).val(function (i, input) {
        input = input.replace(/\D/g, '');
        return (input / 100).toFixed(2);
    });
}).trigger('blur');

$("#up_base").on('blur change input', function () {
    $(this).val(function (i, input) {
        input = input.replace(/\D/g, '');
        return (input / 100).toFixed(2);
    });
}).trigger('blur');

$("#up_Cuota").on('blur change input', function () {
    $(this).val(function (i, input) {
        input = input.replace(/\D/g, '');
        return (input / 100).toFixed(2);
    });
}).trigger('blur');

$("#ImpuestosTrasladados").on('blur change input', function () {
    $(this).val(function (i, input) {
        input = input.replace(/\D/g, '');
        return (input / 100).toFixed(2);
    });
}).trigger('blur');

$("#ImpuestosRetenidos").on('blur change input', function () {
    $(this).val(function (i, input) {
        input = input.replace(/\D/g, '');
        return (input / 100).toFixed(2);
    });
}).trigger('blur');

$("#upParte_precioUnitario").on('blur change input', function () {
    $(this).val(function (i, input) {
        input = input.replace(/\D/g, '');
        return (input / 100).toFixed(2);
    });
}).trigger('blur');

$("#tipo_cambio").on('blur change input', function () {
    $(this).val(function (i, input) {
        input = input.replace(/\D/g, '');
        return (input / 100).toFixed(2);
    });
}).trigger('blur');

