/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function () {
/* 
    $("#input-id2").fileinput({
        language: "es",
        allowedFileExtensions: ["xls", "xlsx"],
        showUpload: false
    });
 */
    var documento;
    var input;

    /*$("#input-id").change(function () {
        input = document.getElementById('input-id');
        var file = input.files[0];
        var fr = new FileReader();
        documento;
        fr.onload = function (event) {
            documento = event.target.result.match(/,(.*)$/)[1];
        };
        fr.readAsDataURL(file);
    });*/

    /*$("#upload_file").click(async function () {
        //debugger;
        if($("#input-id").val()==''){
            alert('Debe de Seleccionar Documento');
            return;
        }
        var nombre_documento = $("#input-id").val();
        var archivo = documento;
        document.getElementById('idClouding').style.display = 'block'; 
        document.getElementById('divResultado').innerHTML = 'Procesando Información';
        
        await mostrarLoader();
        console.log("Upload File Excel Iniciado");
        
        var $el = $('#input-id');
        $el.wrap('<form>').closest('form').get(0).reset();
        $el.unwrap();

        $.ajax({
            method: "POST",
            url: document.getElementById('idAction').value,
            dataType: "html",
            data: {
                nombre_documento: nombre_documento,
                documento: archivo,
                idOpcion: 2,
                idPlantilla: document.getElementById('idPlantilla').value,
                idLenguaje: document.getElementById('idLenguaje').value,
                idDivision: document.getElementById('idDivision').value,
                idBodega: document.getElementById('idBodega').value
            }
            
        }).done(async function (data) {
            document.getElementById('divResultado').innerHTML = data;
            if (data.indexOf("ERROR") > -1) {
                $("#texto").text('Se Presentan Errores');
                $("#alert").removeClass().addClass("alert alert-danger");
            } else {
                $("#texto").text('Solicitud Exitosa');
                $("#alert").removeClass().addClass("alert alert-success");
            }
            resetInputfile();
            document.getElementById('idClouding').style.display = 'none';
        }).fail(async function (data) {
            document.getElementById('divResultado').innerHTML = data;
            document.getElementById('idClouding').style.display = 'none';
            $("#texto").text('Se describen los errores');
            $("#alert").removeClass().addClass("alert alert-danger");
            resetInputfile();
        });
            console.log("Upload File Excel Finalizado");
    });*/

    $("#created_file").click(function () {
        //debugger;
        
        $.ajax({
            method: "POST",
            url: document.getElementById('idAction').value,
            dataType: "html",
            data: {
                idOpcion: 1,
                idPlantilla: document.getElementById('idPlantilla').value,
                idLenguaje: document.getElementById('idLenguaje').value,
                idDivision: document.getElementById('idDivision').value
            }
        }).done(function (data) {
            document.getElementById('divResultado').innerHTML = data;
            if (data.indexOf("ERROR") > -1) {
                $("#texto").text('Se Presentan Errores');
                $("#alert").removeClass().addClass("alert alert-danger");
            } else {
                $("#texto").text('Solicitud Exitosa');
                $("#alert").removeClass().addClass("alert alert-success");
            }
            document.getElementById('idClouding').style.display = 'none';
            //resetInputfile();
        }).fail(function (data) {
            document.getElementById('divResultado').innerHTML = data;
            document.getElementById('idClouding').style.display = 'none';
            $("#texto").text('Se describen los errores');
            $("#alert").removeClass().addClass("alert alert-danger");
            //resetInputfile();
        });
    });

    $("#close").click(function () {
        $("#alert").removeClass().addClass("hide");
    });
    
    /*function resetInputfile() {
        console.log('hola');
        //$('#input-id').fileinput('reset');
    }*/

});
