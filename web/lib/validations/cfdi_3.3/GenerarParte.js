/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function AddPartes() {
    
    let caramelo2 = document.getElementById("upParte_desc").value;
    let obj2 = caramelo2.split("*");
    let concept_id = obj2[0];
    let concept_desc = obj2[1];
    let AddParte_Cantidad = document.getElementById("upParte_Cantidad").value;
    let AddParte_precioUnitario = document.getElementById("upParte_precioUnitario").value;
    let AddParte_ClaveProdServ = document.getElementById("upParte_ClaveProdServ").value;
    let AddParte_unidad = document.getElementById("upParte_unidad").value;
    let AddParte_NoIdentificacion = document.getElementById("upParte_noIdentificacion").value;

    if (caramelo2 == null || caramelo2 == 0) {
        document.getElementById("val_upParte_desc").innerHTML = "Ingrese concepto!";
        return false;
    }
    if (AddParte_Cantidad == "") {
        document.getElementById("val_upParte_Cantidad").innerHTML = "Ingrese cantidad!";
        return false;
    }
    if (AddParte_precioUnitario == "") {
        document.getElementById("val_upParte_precioUnitario").innerHTML = "Ingrese precio!";
        return false;
    }
    if (AddParte_ClaveProdServ == "") {
        document.getElementById("val_upParte_ClaveProdServ").innerHTML = "Ingrese clave sat!";
        return false;
    }
    if (AddParte_unidad == "") {
        document.getElementById("val_upParte_unidad").innerHTML = "Ingrese unidad sat!";
        return false;
    }
    if (AddParte_NoIdentificacion == "") {
        document.getElementById("val_upParte_noIdentificacion").innerHTML = "Ingrese núm identificación!";
        return false;
    }


    fetch("../AddTablaPartes?parte_concepto_id=" + concept_id + "&upParte_desc=" + concept_desc + "&upParte_Cantidad=" + AddParte_Cantidad + "&upParte_precioUnitario=" + AddParte_precioUnitario + "&upParte_ClaveProdServ=" + AddParte_ClaveProdServ + "&upParte_unidad=" + AddParte_unidad + "&upParte_NoIdentificacion=" + AddParte_NoIdentificacion + "&contadorPartes=" + contadorPartes, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {

                let resultadoPart = document.getElementById("AddTablePartes").insertRow(-1);
                resultadoPart.innerHTML = data;
                resultadoPart.id = "part" + concept_id;
                contadorPartes++;

                document.getElementById("numPartes").value = contadorPartes;

            }).catch(error => console.log(error));

}

function delete_parte(id) {
    
    fetch("../EliminarConceptos?tipo=3&id=" + id, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                //actualizar contador de las tablas
            }).catch(error => console.log(error));
}
