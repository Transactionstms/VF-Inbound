/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validarNumEvento(numEventoActual) {

    let numEventoDB = document.getElementById('numEventoDB').value;

    fetch("../ConsultarNumEvento?numEventoActual=" + numEventoActual + "&numEventoDB=" + numEventoDB, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                if (data === "true") {
                    swal("", "Número de Evento Existente", "info");
                    alertclose();
                } else {
                    document.getElementById("updateEvento").value = "1";
                }
            }).catch(error => console.log(error));
}
