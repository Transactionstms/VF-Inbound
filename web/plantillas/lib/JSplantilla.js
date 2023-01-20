function generaPlantilla() {


    var idOpcion = document.getElementById('idOpcion').value;
    var idPlantilla = document.getElementById('idPlantilla').value;
    var idLenguaje = document.getElementById('idLenguaje').value;
    document.getElementById('btnGenerar').disabled = true;
    document.getElementById('btnCargar').disabled = true;
    document.getElementById('trCargando').style.display = 'table-row';

    $(document).ready(function () {
        $.ajax({url: "demo_test.txt", success: function (result) {
                $("#div1").html(result);
            }});
    });

    //document.forms[0].action=document.forms[0].action+ '?idOpcion='+idOpcion+'&idPlantilla='+idPlantilla+'&idLenguaje='+idLenguaje;
    //document.forms[0].submit();

    //document.forms[0].submit();
}

function registraEvento() {
    $(function () {
        $("#plantillaExcel").on("submit", function (e) {
            e.preventDefault();
            var f = $(this);
            var formData = new FormData(document.getElementById("plantillaExcel"));
            formData.append("dato", "valor");
            //formData.append(f.attr("name"), $(this)[0].files[0]);
            $.ajax({
                url: "recibe.php",
                type: "post",
                dataType: "html",
                data: formData,
                cache: false,
                contentType: false,
                processData: false
            })
                    .done(function (res) {
                        $("#mensaje").html("Respuesta: " + res);
                    });
        });
    });
}
