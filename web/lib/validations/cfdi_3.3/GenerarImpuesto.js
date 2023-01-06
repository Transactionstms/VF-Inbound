/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function consultarImpuestoPrincipal(codigo_concepto, cantidad, precio_unitario){
    
    fetch("../ConsultarImpuestoPrincipalProducto?codigo_concepto=" + codigo_concepto + "&cantidad=" + cantidad + "&precio_unitario=" + precio_unitario, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                AddImpuestoPrincipal(data);
            }).catch(error => console.log(error));
}

function AddImpuestoPrincipal(carameloImpuestos) {

    let obImp = carameloImpuestos.split("*");
    let imp0 = obImp[0];  //tipoImpuestoId
    let imp1 = obImp[1];  //cantidadBase
    let imp2 = obImp[2];  //impuestoId
    let imp3 = obImp[3];  //tipoFactorId
    let imp4 = obImp[4];  //tasa-porcentaje valor
    let imp5 = obImp[5];  //cuota
    let imp6 = obImp[6];  //cantidad importe
    let imp7 = obImp[7];  //tipo concepto
    let imp8 = obImp[8];  //tipo concepto

    fetch("../AddTablaImpuestos?up_tipoImpuesto=" + imp0 + "&up_base=" + parseFloat(imp1).toFixed(2) + "&up_impuesto=" + imp2 + "&up_tipoFactor=" + imp3 + "&up_tasa=" + imp4 + "&up_Cuota=" + parseFloat(imp5).toFixed(2) + "&contadorImpuestos=" + contadorImpuestos + "&tipoConcepto=" + imp7 + "&codigo_concepto=" + imp8 + "&contImpuestoConcepto=" + contImpuestoConcepto2, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {

                let resultadoImp = document.getElementById("AddTableImpuestos").insertRow(-1);
                resultadoImp.innerHTML = data;
                resultadoImp.id = "imp" + imp0;
                
                contImpuestoConcepto2++;   //contador por concepto
                contadorImpuestos++;       //Contador de registros total

                document.getElementById("numImpuestos").value = contadorImpuestos;

                document.getElementById("ImpuestosTrasladados").value = imp6;

            }).catch(error => console.log(error));
}

function AddImpuestoAdicional() {
    
    let up_AddtipoImpuesto = document.getElementById("up_tipoImpuesto").value;
    let up_Addbase = document.getElementById("up_base").value;
    let up_Addimpuesto = document.getElementById("up_impuesto").value;
    let up_AddtipoFactor = document.getElementById("up_tipoFactor").value;
    let up_Addtasa = document.getElementById("up_tasa").value;
    let up_AddCuota = document.getElementById("up_Cuota").value;

    if (up_AddtipoImpuesto == null || up_AddtipoImpuesto == 0) {
        document.getElementById("val_up_tipoImpuesto").innerHTML = "Ingrese tipo impuesto!";
        return false;
    }
    if (up_Addbase == "") {
        document.getElementById("val_up_base").innerHTML = "Ingrese base!";
        return false;
    }
    if (up_Addimpuesto == null || up_Addimpuesto == 0) {
        document.getElementById("val_up_impuesto").innerHTML = "Ingrese impuesto!";
        return false;
    }
    if (up_AddtipoFactor == null || up_AddtipoFactor == 0) {
        document.getElementById("val_up_tipoFactor").innerHTML = "Ingrese tipo factor!";
        return false;
    }

    fetch("../AddTablaImpuestos?up_tipoImpuesto=" + up_AddtipoImpuesto + "&up_base=" + up_Addbase + "&up_impuesto=" + up_Addimpuesto + "&up_tipoFactor=" + up_AddtipoFactor + "&up_tasa=" + up_Addtasa + "&up_Cuota=" + up_AddCuota + "&contadorImpuestos=" + contadorImpuestos, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {

                let resultadoImp = document.getElementById("AddTableImpuestos").insertRow(-1);
                resultadoImp.innerHTML = data;
                resultadoImp.id = "imp" + up_AddtipoImpuesto;
                contadorImpuestos++;

                //Calcular impuesto Gral
                impuestoGral += impuestoGral;
                //Calcular factor Gral
                factorGral += factorGral * 0.16;
                //Calcular tasa Gral
                tasaGral += tasaGral;
                //Calcular cuota Gral
                cuotaGral += cuotaGral;
                //Calcular Total Gral
                tipoImpuestoGral += tipoImpuestoGral;

                document.getElementById("numImpuestos").value = contadorImpuestos;
                document.getElementById("cant_impuesto_gral").value = impuestoGral;
                document.getElementById("cant_factor_gral").value = factorGral;
                document.getElementById("cant_factor_gral").value = tasaGral;
                document.getElementById("cant_cuota_gral").value = cuotaGral;
                document.getElementById("tipo_impueto_gral").value = tipoImpuestoGral;

            }).catch(error => console.log(error));
}

