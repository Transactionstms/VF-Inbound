/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function relTotalCFDI(uuid) {
    
    let objU = uuid.split("*");
    let uid_id = objU[0];
    let uuid_cantidad = objU[1];
    document.getElementById("total_cfdi").value = parseFloat(uuid_cantidad).toFixed(2);
}

function AddUUID() {
    
    let tiporelacion_id = document.getElementById("tiporelacion_id").value;
    let uuid_ = document.getElementById("uuid_id").value;

    let objUd = uuid_.split("*");
    let uuid_id = objUd[0];
    let uuid_cantidad = objUd[1];

    let total_cfdi = document.getElementById("total_cfdi").value;

    if (tiporelacion_id == null || tiporelacion_id == 0) {
        document.getElementById("val_tiporelacion_id").innerHTML = "Ingrese tipo relación!";
        return false;
    }
    if (uuid_ == null || uuid_ == 0) {
        document.getElementById("val_uuid_id").innerHTML = "Ingrese uuid!";
        return false;
    }
    if (total_cfdi == "") {
        document.getElementById("val_total_cfdi").innerHTML = "Ingrese cantidad!";
        return false;
    }

    fetch("../AddTablaCondiciones?tiporelacion_id=" + tiporelacion_id + "&uuid_id=" + uuid_id + "&total_cfdi=" + total_cfdi + "&contadorUUID=" + contadorUUID, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {

                let resultadoUuid = document.getElementById("AddTableUUID").insertRow(-1);
                resultadoUuid.innerHTML = data;
                resultadoUuid.id = "uuid" + tiporelacion_id;
                contadorUUID++;

                document.getElementById("numUUID").value = contadorUUID;
                document.getElementById('total_rel_cfdi').innerHTML = "Total: $" + parseFloat(uuid_cantidad).toFixed(2);

            }).catch(error => console.log(error));

}

function delete_relacionCFDI(id) {
    
    fetch("../EliminarConceptos?tipo=3&id=" + id, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                //actualizar contador de las tablas
            }).catch(error => console.log(error));
}

function cleanFormularioCFDIRelacion() {
    
    document.getElementById("tiporelacion_id").value = '';
    document.getElementById("uuid_id").value = '';
    document.getElementById("total_cfdi").value = '';
}

