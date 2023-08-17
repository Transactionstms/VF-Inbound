/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Reglas de Negocios <Fechas Estimadas/Controladores>

let blocked = false; //Identificador - Campo División
 
function diasLoadType(loadType) {  //RULE #1

    let actual_crd = document.getElementById('actual_crd').value;
    let max_flete = "0";

    if (loadType === "LTL" || loadType === "AIR") {

        if (loadType === "LTL") {
            max_flete = "27";
        } else if (loadType === "AIR") {
            max_flete = "20";
        }

        fetch("../UpLt2_EtaDcAndPutAway?actual_crd=" + actual_crd + "&lt2=" + max_flete, {
            method: 'POST',
        }).then(r => r.text())
                .then(data => {

                    var inputs = data.split("@");
                    var date_etaDc = inputs[0];
                    var date_putAway = inputs[1];

                    document.getElementById("eta_plus2").value = date_etaDc;
                    document.getElementById("eta_plus").value = date_putAway;
                    document.getElementById("max_flete").value = max_flete;

                }).catch(error => console.log(error));

    }

}

function diasLt2(max_flete) {  //RULE #2  

    let actual_crd = document.getElementById('actual_crd').value;

    fetch("../UpLt2_LoadTypeNot?actual_crd=" + actual_crd + "&lt2=" + max_flete, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {

                var inputs = data.split("@");
                var date_etaDc = inputs[0];
                var date_putAway = inputs[1];

                document.getElementById("eta_plus2").value = date_etaDc;
                document.getElementById("eta_plus").value = date_putAway;

            }).catch(error => console.log(error));

}

function diasEtaDc(tipoConsulta) {  //RULE #3

    let eta_plus2 = document.getElementById('eta_plus2').value;
    let sbu_name = document.getElementById('sbu_name').value;
    let id_brandDivision = "";
    let lt2 = "";

    if (tipoConsulta === "1") {
        id_brandDivision = '<%=idBrandivision%>';
    } else {
        id_brandDivision = document.getElementById('Brand').value;
    }

    if (id_brandDivision === "3003") {  //NORHT THE FACE
        if (sbu_name === "5002" || sbu_name === "5001") {
            lt2 = "3";
        } //APPAREL/ACCESSORIES
    } else {
        lt2 = "2";
    }

    fetch("../UpEtaDc_LoadTypeNot?eta_plus2=" + eta_plus2 + "&lt2=" + lt2, {
        method: 'POST',
    }).then(r => r.text())
            .then(date_putAway => {

                document.getElementById("eta_plus").value = date_putAway;

            }).catch(error => console.log(error));

}

function diasActualCrd(actual_crd) {  //RULE #4

    let max_flete = document.getElementById('max_flete').value;

    fetch("../UpActualCrd_LoadTypeNot?actual_crd=" + actual_crd + "&lt2=" + max_flete, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {

                var inputs = data.split("@");
                var date_etaDc = inputs[0];
                var date_putAway = inputs[1];

                document.getElementById("eta_plus2").value = date_etaDc;
                document.getElementById("eta_plus").value = date_putAway;

            }).catch(error => console.log(error));

}

function diasBrandDivision() {  //RULE #5

    let Brand = document.getElementById('Brand').value;
    let sbu_name = document.getElementById('sbu_name').value;
    let eta_plus = document.getElementById('eta_plus').value;

    if (Brand === "3003") {  //NORHT THE FACE
        if (sbu_name === "5002" || sbu_name === "5001") { //APPAREL/ACCESSORIES
            if(!blocked){ //Evitar iteración ++
                fetch("../UpBrandDivision_LoadTypeNot?eta_plus=" + eta_plus + "&lt2=1", {
                    method: 'POST',
                }).then(r => r.text())
                        .then(date_putAway => {
                            document.getElementById("eta_plus").value = date_putAway;
                            blocked = true;
                        }).catch(error => console.log(error));
            }
        }else{
            document.getElementById("eta_plus").value = campo15; //Asignar valor Inicial
            blocked = false;
        }
    }else{
        document.getElementById("eta_plus").value = campo15; //Asignar valor Inicial
        blocked = false;
    }

}

