/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Reglas de Negocios <Fechas Estimadas/Controladores>

let blocked = false;                     //Identificador - Campo División
let blocked_diasBrandDivision = false;   //Identificador - Campo Brand División | División (RULE #5-HISTORICO)

/*(Brand División = NORHT THE FACE y División = APPAREL/ACCESSORIES) --> No aplicar la función (diasBrandDivision) */
if(campo5 === "3003" && campo6 === "5002" || campo6 === "5001"){
    blocked_diasBrandDivision = true; 
}
console.log("Brand División = NORHT THE FACE y División = APPAREL/ACCESSORIES:" +blocked_diasBrandDivision);


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
    let diasLt2 = 0;
    
    if(sbu_name === "5003"){ //FOOTWARE
        blocked_diasBrandDivision = false;  //(2)días
    }

    if(blocked_diasBrandDivision === false){  /*Validación Historico */
        
        if (Brand === "3003") {  //NORHT THE FACE
            
            if(sbu_name === "5002" || sbu_name === "5001" || sbu_name === "5003"){ //APPAREL/ACCESSORIES/FOOTWARE
            
                if (sbu_name === "5002" || sbu_name === "5001") { //APPAREL/ACCESSORIES
                    diasLt2 = 1;  //(3) días 
                }else if(sbu_name === "5003"){ //FOOTWARE
                    diasLt2 = 0;  //(2) días 
                }

                if(!blocked){ //EVITAR ITERACIÓN DE DÍAS SI YA SE REALIZO MAS DE 1 VEZ
                    fetch("../UpBrandDivision_LoadTypeNot?eta_plus=" + eta_plus + "&lt2="+diasLt2, {
                        method: 'POST',
                    }).then(r => r.text())
                            .then(date_putAway => {
                                document.getElementById("eta_plus").value = date_putAway;
                                blocked = true;
                            }).catch(error => console.log(error));
                }
               
            }else{    //REGRESAR FECHA AL VALOR DEL HISTORICO
                document.getElementById("eta_plus").value = campo15; 
                blocked = false;
            }
        }
        
    }
}

