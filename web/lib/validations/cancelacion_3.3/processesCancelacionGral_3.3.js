/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let correos;
let cbdiv_id = document.getElementById("cbdiv_id").value;

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

function doSearch() {
    var tableReg = document.getElementById('dataReciboPago');
    var searchText = document.getElementById('searchTerm').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";

    // Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++)
    {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
        // Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++)
        {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
            // Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
            {
                found = true;
            }
        }
        if (found)
        {
            tableReg.rows[i].style.display = '';
        } else {
            // si no ha encontrado ninguna coincidencia, esconde la
            // fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
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
        location.reload();
    }, 2000);
}
