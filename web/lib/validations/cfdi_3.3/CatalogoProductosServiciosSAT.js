/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function search_divisiones(valor) {
    fetch("../ConsultarClaveSAT?tipo=1&valor=" + valor, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                document.getElementById('divisiones').innerHTML = data;
            }).catch(error => console.log(error));
}

function search_grupos(valor) {
    fetch("../ConsultarClaveSAT?tipo=2&valor=" + valor, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                document.getElementById('grupos').innerHTML = data;
            }).catch(error => console.log(error));
}

function search_clases(valor) {
    fetch("../ConsultarClaveSAT?tipo=3&valor=" + valor, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                document.getElementById('clases').innerHTML = data;
            }).catch(error => console.log(error));
}

function search_claves(valor) {
    fetch("../ConsultarClaveSAT?tipo=4&valor=" + valor, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                document.getElementById('claves').innerHTML = data;
            }).catch(error => console.log(error));
}

function listarClaveSAT(id_clvsat) {
    document.getElementById('rel_clvsat_id').value = id_clvsat;
    document.getElementById("producto_ser_sat").innerHTML = "Clave SAT: " + id_clvsat;
}

function cleanFormularioClaveSAT() {
    //document.getElementById("claveSAT").value = '';
    //document.getElementById("claseSAT").value = '';
    //document.getElementById("grupoSAT").value = '';
    //document.getElementById("divisionSAT").value = '';
    document.getElementById("conceptos").value = '';
}
