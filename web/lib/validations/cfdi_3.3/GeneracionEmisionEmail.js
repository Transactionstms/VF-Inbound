/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function savegeneratepdf(tipo) {
    fetch("../GenerateFiles?serie=" + serie + "&folio=" + folio + "&tipo=" + tipo, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
            }).catch(error => console.log(error));
}

function sendmail(tipoGeneracion, tipoTimbrado) { //tipoGeneracion(cfdi ó cartaPorte)|tipoTimbrado(si/no XML)
    fetch("../AlertaFactutacion?serie=" + serie + "&folio=" + folio + "&tipoGeneracion=" + tipoGeneracion + "&tipoTimbrado=" + tipoTimbrado + "&correos=" + correos, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                hidePreloader();
                swal("registrado", "Generación de Factura éxitoso", "success");
                alertclose();
                location.reload();
            }).catch(error => console.log(error));
}

