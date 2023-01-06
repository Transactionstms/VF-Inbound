/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function detailsConcepto(codigo_concepto) {

    fetch("../ConsultarDataConcepto?codigo_concepto=" + codigo_concepto, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                let input = data.split("*");
                let a0t = input[0]; //id
                let a1t = input[1]; //clave sat
                let a2t = input[2]; //valor unitario
                let a3t = input[3]; //unidad medida clave
                let a4t = input[4]; //unidad medida id

                $('#choices-single-default').val(codigo_concepto).trigger("chosen:updated");
                document.getElementById("cantidad").value = 1;           //cantidad
                document.getElementById("rel_clvsat_id").value = a1t;    //clave sat
                document.getElementById("precio_unitario").value = parseFloat(a2t).toFixed(2);  //valor unitario
                document.getElementById("unidad_sat_id").value = a3t;    //unidad medida clave
                //document.getElementById("unidad_sat_id").value = a4t;    //unidad medida id
            }).catch(error => console.log(error));
}

function AddConceptos() {

    let importe_descuento = 0;
    let importe = 0;

    serie = document.getElementById("serie").value;
    folio = document.getElementById("folio").value;
    let clientE = document.getElementById("cliente_id").value;
    let comentarios = document.getElementById("comentarios").value;
    let rel_clvsat_id = document.getElementById("rel_clvsat_id").value;
    let unidad_sat_id = document.getElementById("unidad_sat_id").value;
    let codigo_concepto = document.getElementById("choices-single-default").value;
    let cantidad = document.getElementById("cantidad").value;
    let precio_unitario = document.getElementById("precio_unitario").value;

    if (clientE == null || clientE == 0) {
        swal("Error", "Nombre de cliente inválido", "error");
        alertclose();
        return false;
    }
    if (cantidad == "") {
        swal("Error", "Ingrese cantidad", "error");
        alertclose();
        return false;
    }
    if (rel_clvsat_id == "") {
        swal("Error", "Ingrese clave sat", "error");
        alertclose();
        return false;
    }
    if (unidad_sat_id == "") {
        swal("Error", "Ingrese unidad sat", "error");
        alertclose();
        return false;
    }
    if (precio_unitario == "") {
        swal("Error", "Ingrese precio", "error");
        alertclose();
        return false;
    }

    importe = cantidad * precio_unitario;
    
    consultarImpuestoPrincipal(codigo_concepto, cantidad, precio_unitario);
    
    fetch("../InsertarConceptos?comentarios=" + comentarios + "&rel_clvsat_id=" + rel_clvsat_id + "&unidad_sat_id=" + unidad_sat_id + "&codigo_concepto=" + codigo_concepto + "&cantidad=" + cantidad + "&precio_unitario=" + precio_unitario + "&importe_descuento=" + importe_descuento + "&importe=" + importe + "&contImpuestoConcepto=" + contImpuestoConcepto1 + "&serie=" + serie + "&folio=" + folio + "&tipoArmado=1", {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                
                contImpuestoConcepto1++;  //contador por concepto
               
                cleanFormularioConcepto();
                
                cleanFormularioClaveSAT();
                
                mostrarConceptos(serie, folio);
                
                mostrarTotales(serie, folio);
               
            }).catch(error => console.log(error));
}

function mostrarConceptos(serie, folio){
    
    fetch("../AddTablaConceptos?serie=" + serie + "&folio=" + folio, {
        method: 'POST',
    }).then(r => r.text())
        .then(data => {
            
            let resultado = document.getElementById("AddTableConceptos").innerHTML = data;
            resultado.id = "tr" + folio;
            
        }).catch(error => console.log(error));
}

function mostrarTotales(serie, folio){
    
    fetch("../DetalleTotales?serie=" + serie + "&folio=" + folio, {
        method: 'POST',
    }).then(r => r.text())
        .then(data => {
            
            let resultado2 = document.getElementById("AddDetalleTotales").innerHTML = data;
            resultado2.id = "tr" + serie;
            
        }).catch(error => console.log(error));
}

function delete_concepto(codigo_concepto, folio_concepto) {
    
    serie = document.getElementById("serie").value;
    folio = document.getElementById("folio").value;
    
    fetch("../EliminarConceptos?codigo_concepto=" + codigo_concepto + "&folio_concepto=" + folio_concepto, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                
                if (data != "1") {
                    swal("", "Concepto no eliminado, intente nuevamente", "error");
                    alertclose();
                }
                
                mostrarConceptos(serie, folio);
                mostrarTotales(serie, folio);
          
            }).catch(error => console.log(error));
}

function cleanFormularioConcepto() {
    document.getElementById("choices-single-default").value = '';
    document.getElementById("comentarios").value = '';
    document.getElementById("cantidad").value = '';
    document.getElementById("rel_clvsat_id").value = '';
    document.getElementById("unidad_sat_id").value = '';
    document.getElementById("precio_unitario").value = '';
}
