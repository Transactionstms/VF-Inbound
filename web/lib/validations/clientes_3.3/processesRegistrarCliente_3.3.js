/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let tipoTerritorio = 0;
let tipoEnvio = 0;
let xml = 0;
let pdf = 0;

function tiporegistro(id_reg) {

    tipoTerritorio = id_reg;

    if (tipoTerritorio === '1') { //Nacional

        div = document.getElementById('flotante_nacional');
        div.style.display = '';

        div = document.getElementById('flotante_extranjero');
        div.style.display = 'none';

        document.getElementById("num_regid_trib").value = '';
        document.getElementById("pais_id").value = '';
        document.getElementById("provincia_id").value = '';

        div = document.getElementById('flotantepais');
        div.style.display = 'none';

    } else {              //Extranjero

        div = document.getElementById('flotante_nacional');
        div.style.display = 'none';

        div = document.getElementById('flotante_extranjero');
        div.style.display = '';

        document.getElementById("referencia").value = '';
        document.getElementById("estado_id").value = '';
        document.getElementById("municipio_id").value = '';

        div = document.getElementById('flotantepais');
        div.style.display = '';
    }
}

function AddClientes() {

    let cliente_descripcion = document.getElementById("cliente_descripcion").value;
    let rfc = document.getElementById("rfc").value;
    let nombre_comercial = document.getElementById("nombre_comercial").value;
    let codigo_cliente = document.getElementById("codigo_cliente").value;
    let num_cliente = document.getElementById("num_cliente").value;
    let calle = document.getElementById("calle").value;
    let num_ext = document.getElementById("num_ext").value;
    let num_int = document.getElementById("num_int").value;
    let colonia = document.getElementById("colonia").value;
    let localidad = document.getElementById("localidad").value;
    let referencia = document.getElementById("referencia").value;
    let num_regid_trib = document.getElementById("num_regid_trib").value;
    let codigo_postal = document.getElementById("codigo_postal").value;
    let pais_id = document.getElementById("pais_id").value;
    let provincia_id = document.getElementById("provincia_id").value;
    let estado_id = document.getElementById("estado_id").value;
    let municipio_id = document.getElementById("municipio_id").value;
    let regimen_desc = document.getElementById("regimen_desc").value;
    let metodo_id = document.getElementById("metodo_id").value;
    let forma_id = document.getElementById("forma_id").value;
    let usocfdi_id = document.getElementById("usocfdi_id").value;
    let nombreContacto1 = document.getElementById("nombreContacto1").value;
    let emailContacto1 = document.getElementById("emailContacto1").value;
    let nombreContacto2 = document.getElementById("nombreContacto2").value;
    let emailContacto2 = document.getElementById("emailContacto2").value;
    let nombreContacto3 = document.getElementById("nombreContacto3").value;
    let emailContacto3 = document.getElementById("emailContacto3").value;
    let telefono = document.getElementById("telefono").value;
    let pagina_web = document.getElementById("pagina_web").value;
    let coment_email_cfdi = document.getElementById("coment_email_cfdi").value;

    if (cliente_descripcion == "") {
        swal("Error", "Nombre de cliente inválido", "error");
        alertclose();
        return false;
    }
    if (rfc == "") {
        swal("Error", "El RFC del cliente es inválido", "error");
        alertclose();
        return false;
    }
    if (usocfdi_id == "" || usocfdi_id == 0) {
        swal("Error", "Seleccione un uso de cfdi", "error");
        alertclose();
        return false;
    }
    if (regimen_desc == "" || regimen_desc == 0) {
        swal("Error", "Seleccione un regimén fiscal", "error");
        alertclose();
        return false;
    }
    if (metodo_id == "" || metodo_id == 0) {
        swal("Error", "Seleccione un método de pago", "error");
        alertclose();
        return false;
    }
    if (forma_id == "" || forma_id == 0) {
        swal("Error", "Seleccione una forma de pago", "error");
        alertclose();
        return false;
    }
    
    if (calle == "") {
        swal("Error", "Agregue al menos una dirección", "error");
        alertclose();
        return false;
    }
    if (colonia == "") {
        swal("Error", "Agregue al menos una colonia", "error");
        alertclose();
        return false;
    }
    if (localidad == "") {
        swal("Error", "Agregue al menos una localidad", "error");
        alertclose();
        return false;
    }
    if (codigo_postal == "") {
        swal("Error", "Ingrese código postal", "error");
        alertclose();
        return false;
    }
    if (estado_id == "" || estado_id == 0) {
        swal("Error", "Seleccione un estado", "error");
        alertclose();
        return false;
    }
    if (municipio_id == "" || municipio_id == 0) {
        swal("Error", "Seleccione un municipio", "error");
        alertclose();
        return false;
    }

    if (tipoTerritorio == 2) {
        if (num_regid_trib == "") {
            swal("", "Ingrese número de regimen tributario", "info");
            alertclose();
            return false;
        }
        if (pais_id == null || pais_id == 0) {
            swal("", "Seleccione el país", "info");
            alertclose();
            return false;
        }
        if (provincia_id == null || provincia_id == 0) {
            swal("", "Seleccione provincia", "info");
            alertclose();
            return false;
        }
    }

    if (nombreContacto1 == "") {
        swal("Error", "Agregue al menos un nombre de contacto", "error");
        alertclose();
        return false;
    }
    if (emailContacto1 == "") {
        swal("Error", "Agregue al menos un correo de contacto", "error");
        alertclose();
        return false;
    }

    if (document.getElementById("check_xml").checked) {
        xml = 1;
    }
    if (document.getElementById("check_pdf").checked) {
        pdf = 1;
    }
    
    tipoEnvio = xml + pdf;

    if (tipoEnvio == 0) {
        swal("", "Seleccione al menos un tipo de envío", "info");
        alertclose();
        return false;
    }

    fetch("../InsertarClientes?cliente_descripcion=" + cliente_descripcion +
            "&rfc=" + rfc +
            "&nombre_comercial=" + nombre_comercial +
            "&codigo_cliente=" + codigo_cliente +
            "&num_cliente=" + num_cliente +
            "&calle=" + calle +
            "&num_ext=" + num_ext +
            "&num_int=" + num_int +
            "&colonia=" + colonia +
            "&localidad=" + localidad +
            "&referencia=" + referencia +
            "&num_regid_trib=" + num_regid_trib +
            "&codigo_postal=" + codigo_postal +
            "&pais_id=" + pais_id +
            "&provincia_id=" + provincia_id +
            "&estado_id=" + estado_id +
            "&municipio_id=" + municipio_id +
            "&regimen_desc=" + regimen_desc +
            "&metodo_id=" + metodo_id +
            "&forma_id=" + forma_id +
            "&usocfdi_id=" + usocfdi_id +
            "&nombreContacto1=" + nombreContacto1 +
            "&emailContacto1=" + emailContacto1 +
            "&nombreContacto2=" + nombreContacto2 +
            "&emailContacto2=" + emailContacto2 +
            "&nombreContacto3=" + nombreContacto3 +
            "&emailContacto3=" + emailContacto3 +
            "&telefono=" + telefono +
            "&pagina_web=" + pagina_web +
            "&coment_email_cfdi=" + coment_email_cfdi +
            "&xml=" + xml +
            "&pdf=" + pdf, {
                method: 'POST',
            }).then(r => r.text())
            .then(data => {

                if (data == "1") {
                    swal("", "Registro exitoso", "success");
                    alertclose();
                    location.reload();
                } else {
                    swal("Error", "El cliente no fue registrado", "error");
                    alertclose();
                    return false;
                }

            }).catch(error => console.log(error));

}

function search_Municipios(estado) {
    fetch("../ConsultarMunicipioCliente?estado_id=" + estado, {
        method: 'POST',
    }).then(r => r.text())
            .then(data => {
                document.getElementById('municipios').innerHTML = data;
            }).catch(error => console.log(error));
}

