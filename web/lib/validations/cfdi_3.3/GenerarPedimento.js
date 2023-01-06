/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function AddPedimentos() {
    
    let up_cuentaPredial = document.getElementById("up_cuentaPredial").value;
    let up_numeroPedimento = document.getElementById("up_numeroPedimento").value;

    if (up_cuentaPredial == "") {
        document.getElementById("val_up_cuentaPredial").innerHTML = "Ingrese cuenta predial!";
        return false;
    }
    if (up_numeroPedimento == "") {
        document.getElementById("val_up_numeroPedimento").innerHTML = "Ingrese pedimento!";
        return false;
    }

    fetch("../AddTablaPedimentos?up_cuentaPredial=" + up_cuentaPredial + "&up_numeroPedimento=" + up_numeroPedimento + "&contadorPedimentos=" + contadorPedimentos, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {

                let resultadoImp = document.getElementById("AddTablePedimentos");
                resultadoImp.innerHTML = data;
                resultadoImp.id = "ped" + up_cuentaPredial;
                contadorPedimentos++;

                document.getElementById("numPedimentos").value = contadorPedimentos;

            }).catch(error => console.log(error));
}

function delete_Pedimento(id) {
    
    fetch("../EliminarConceptos?tipo=3&id=" + id, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                //actualizar contador de las tablas
            }).catch(error => console.log(error));
}


