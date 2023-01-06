/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function AddInfoComercial() {
    
    let num_orden = document.getElementById("num_orden").value;
    let num_proveedor = document.getElementById("num_proveedor").value;
    let info_observaciones = document.getElementById("info_observaciones").value;

    if (num_orden == "") {
        document.getElementById("val_num_orden").innerHTML = "Ingrese número de orden!";
        return false;
    }
    if (num_proveedor == "") {
        document.getElementById("val_num_proveedor").innerHTML = "Ingrese número de proveedor!";
        return false;
    }
    if (info_observaciones == "") {
        document.getElementById("val_info_observaciones").innerHTML = "Ingrese observaciones!";
        return false;
    }

    fetch("../AddTablaInfoComercial?num_orden=" + num_orden + "&num_proveedor=" + num_proveedor + "&info_observaciones=" + info_observaciones, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                urlParametrosInformacionComercial = data;
            }).catch(error => console.log(error));
}

function delete_infoComercial(id) {
    
    fetch("../EliminarConceptos?tipo=3&id=" + id, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                //actualizar contador de las tablas
            }).catch(error => console.log(error));
}