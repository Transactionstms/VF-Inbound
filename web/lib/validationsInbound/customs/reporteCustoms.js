/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    $(document).ready(function () {
        customForm();
    });
            
    function customForm() {
        let idAgente = document.getElementById("idAgenteAduanal").value;   
        fetch("../../ConsultarReporteCustom?tipoAgente="+idAgente, {
            method: 'POST',
        }).then(r => r.text())
                .then(data => {
                    document.getElementById('detalleCustom').innerHTML = data;
                }).catch(error => console.log(error));
    }

