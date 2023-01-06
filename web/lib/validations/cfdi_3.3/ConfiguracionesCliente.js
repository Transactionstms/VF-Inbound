/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    //Listar contador/folio actual
    listarFolioFactura('F');
    
    
});

function tipoComprobante(tipo) {

    let selectDoc = document.getElementById("documento_id");
    let documento;

    if (tipo == "1") {
        div = document.getElementById('addCartaPorte');
        div.style.display = 'none';
        documento = ["1/Factura Electrónica", "2/Recibo de Honorarios", "3/Recibo de Arrendamiento", "4/Nota de Cargo", "5/Recibo de Donativos", "6/Anticipo"];

    } else if (tipo == "2") {
        div = document.getElementById('addCartaPorte');
        div.style.display = 'none';
        $("#modalcfdirelacion").modal("show");
        documento = ["1/Factura Electrónica", "2/Recibo de Honorarios", "3/Recibo de Arrendamiento", "4/Nota de Cargo", "5/Recibo de Donativos", "6/Anticipo"];

    } else if (tipo == "3") {
        div = document.getElementById('addCartaPorte');
        div.style.display = '';
        documento = ["7/Carta de Porte"];

    }

    //Limpiar: Tipo Factor
    for (let i = selectDoc.options.length; i >= 0; i--) {
        selectDoc.remove(i);
    }

    //Cargar: Tipo Factor  
    for (let i = 0; i < documento.length; i++) {
        const myArray = documento[i].split("/");

        let id = myArray[0];
        let desc = myArray[1];

        let option = document.createElement("option");  //Creamos la opcion 
        option.value = id;
        option.text = desc;
        selectDoc.appendChild(option);            //Metemos la opción en el select
    }

}

function tipoDocumento(id) {
    
    if (id == "7") {
        div = document.getElementById('addCartaPorte');
        div.style.display = '';
    } else {
        div = document.getElementById('addCartaPorte');
        div.style.display = 'none';
    }
}

function tipoCambio(tipo) {

    if (tipo === '1') { //EUR - Euro
        document.getElementById('tipo_cambio').disabled = false;
        document.getElementById('tipo_cambio').value = "0.00";
    } else if (tipo === '2') {  //MXN
        document.getElementById('tipo_cambio').disabled = true;
        document.getElementById('tipo_cambio').value = "";
    } else if (tipo === '3') {  //USD
        document.getElementById('tipo_cambio').disabled = false;
        document.getElementById('tipo_cambio').value = "0.00";
    }
}

function cliente(clienteId) {
    
    fetch("../ConsultarClienteFac?clienteId=" + clienteId, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                let input = data.split("*");
                let a0 = input[0]; //id
                let a1 = input[1]; //rfc
                let a2 = input[2]; //uso cfdi
                let a3 = input[3]; //regimen fiscal
                let a4 = input[4]; //metodo de pago
                let a5 = input[5]; //forma de pago
                let a6 = input[6]; //correo1 || correo2 || correo 

                document.getElementById("cliente_id").value = a0; //id
                document.getElementById("rfc").value = a1; //rfc
                document.getElementById("usocfdi_id").value = a2; //uso cfdi
                document.getElementById("regimen_id").value = a3; //regimen fiscal
                document.getElementById("metodo_id").value = a4; //metodo de pago
                document.getElementById("forma_id").value = a5; //forma de pago
                document.getElementById("correos").value = a6;    //correos

            }).catch(error => console.log(error));
}

function listarFolioFactura(tipo) {
    
    fetch("../ListarFolios?serie=" + tipo, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                document.getElementById("comboFolioFactura").innerHTML = data;
                eliminarRegistrosBasura();
            }).catch(error => console.log(error));
}

function eliminarRegistrosBasura(){
    fetch("../EliminarRegistros", {
        method: 'POST',
    }).then(r => r.text())
        .then(data => {
            listarPreRegistroFactura();
        }).catch(error => console.log(error));
}

function listarPreRegistroFactura() {
    
    serie = document.getElementById("serie").value;
    folio = document.getElementById("folio").value;
    
    fetch("../InsertarPreFacturacion?serie=" + serie + "&folio=" + folio, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                
                mostrarConceptos(serie, folio);
                mostrarTotales(serie, folio);
                
                let resultado2 = document.getElementById("AddTableImpuestos").insertRow(-1);
                resultado2.innerHTML = "";
                
                document.getElementById("numImpuestos").value = "0";

            }).catch(error => console.log(error));
}

