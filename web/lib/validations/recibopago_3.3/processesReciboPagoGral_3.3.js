/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Parametros: Emisión de correos
let correos;
let serie;
let folio;
let tipoGeneracion = 3;

/*let cbdiv_id = document.getElementById("cbdiv_id").value;
let cliente = document.getElementById("cliente_id").value;
let facturas = document.getElementById("idFacturas").value;
            
window.onload = function () {
    cliente(cliente);
    consultarFacturas(facturas);
}*/

function consultarRecibo(cliente_id) {

    fetch("../ConsultarReciboPago?idCliente=" + cliente_id, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                document.getElementById('recibos').innerHTML = data;
                contadorFacturas = document.getElementById("contadorFacturas").value;
            }).catch(error => console.log(error));

}

function savegeneratepdf(tipo) {
    fetch("../GenerateFiles?serie=" + serie + "&folio=" + folio + "&tipo=" + tipo, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
            }).catch(error => console.log(error));
}

function sendmail(tipoGeneracion, tipoTimbrado) { //tipoGeneracion(complemento de pago)|tipoTimbrado(si/no XML)
    fetch("../AlertaFactutacion?serie=" + serie + "&folio=" + folio + "&tipoGeneracion=" + tipoGeneracion + "&tipoTimbrado=" + tipoTimbrado + "&correos=" + correos, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                hidePreloader();
                swal("registrado", "Generación de recibo éxitoso registrado", "success");
                alertclose();
                location.reload();

            }).catch(error => console.log(error));
}

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

