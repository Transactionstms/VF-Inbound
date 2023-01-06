/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Parametros: Configuración Cliente
let xml_sts;
let pdf_sts;
let addFact_sts;

//Parametros: Tabla Conceptos
let tipoConcepto=0;
let comentarios;
let rel_clvsat_id;
let unidad_sat_id;
let concepto_desc;
let concepto_id;
let cantidad;
let precio_unitario;
let importe_descuento;
let importe;
let url = "";
let contImpuestoConcepto1 = document.getElementById("contImpuestoConcepto").value;

//Parametros: Tabla Impuestos
let ti_tipoImpuesto;
let ti_base;
let ti_impuesto;
let ti_tipoFactor;
let ti_tasa;
let ti_cuota;
let ti_importe;
let url2 = "";
let contImpuestoConcepto2 = document.getElementById("contImpuestoConcepto").value;

//Parametros: Tipo de Impuestos
let v_tipoImpuesto = 0;
let v_impuesto = 0;
let v_tipo_factor = 0;

//Parametros: Tabla Partes
let tqconcepto_id;
let tqParte_desc;
let tqParte_Cantidad;
let tqParte_precioUnitario;
let tqParte_ClaveProdServ;
let tqParte_unidad;
let tqParte_NoIdentificacion;
let url4 = "";

//Parametros: Tabla Pedimentos
let tp_cuentaPredial;
let tp_numeroPedimento;
let url3 = "";

//Parametros: Tabla Relación CFDI
let tiporelacion_id;
let uuid_id;
let total_cfdi;
let url5 = "";

//Parametros: Información Comercial
let urlParametrosInformacionComercial = "";

//Totales
let subtotalP=0;
let trasladoP=0;
let retencionesP=0;
let descuentosP=0;
let subtotalGral = 0;
let importe_descuentoGral = 0;
let trasladoGral = 0;
let retencionesGral = 0;
let totalGral = 0;
let impuestoGral = 0;
let factorGral = 0;
let tasaGral = 0;
let cuotaGral = 0;
let tipoImpuestoGral = 0;

//Contadores Tablas
let contadorImpuestos = 0;
let contadorPedimentos = 0;
let contadorPartes = 0;
let contadorUUID = 0;
let contadorVisorPDF = 0;

//Parametros: Emisión de correos
let correos;
let serie;
let folio;
let tipoGeneracion;
let cbdiv_id = document.getElementById("cbdiv_id").value;

function showPreloader() {
    div = document.getElementById('loader-folding-circle');
    div.style.display = '';
}

function hidePreloader() {
    div = document.getElementById('loader-folding-circle');
    div.style.display = 'none';
}

function alertclose() {
    setTimeout(function () {
        swal.close();
    }, 2000);
}

function soloNumeros(e) {
    var key = window.Event ? e.which : e.keyCode
    return (key >= 48 && key <= 57)
}

