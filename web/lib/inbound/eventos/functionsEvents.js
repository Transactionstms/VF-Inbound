/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    async function save(){
        
        jsShowWindowLoad('Cargando Información');

        let agenteId = document.getElementById("agenteId").value;

            try {
                const response = await fetch("../AlertaInbound?agenteAduanal="+agenteId);   
                if (!response.ok) {
                  throw new Error('Error en la solicitud');   
                }
                const data = await response.text();
                
                $("#WindowLoad").remove();
            
                swal("", "Información actualizada", "success");
                alertclose();
                location.reload();

            } catch (error) {
              console.error(error);
            }
    }
    
    function sendEmail(){

        fetch("../AlertaInbound?typeProcess=2", {
            method: 'POST',
        }).then(r => r.text())
                .then(data => {
                    
                    $("#WindowLoad").remove();
            
                    swal("", "Información actualizada", "success");
                    alertclose();
                    location.reload();
                }).catch(error => console.log(error));
    }
    
    function delete_registro() {
        swal({
            title: "¿Estás seguro?",
            text: "Si confirmas, tus datos seran guardados",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Sí, cancelar.",
            cancelButtonText: "¡No, cancele por favor!",
            closeOnConfirm: false,
            closeOnCancel: false
        },
                function (isConfirm) {
                    if (isConfirm) {
                        window.location.href = "../Importacion/eventosDetalle.jsp";
                    } else {
                        swal.close();
                    }
                });
    }

    function alertclose() {
        setTimeout(function () {
            swal.close();
        }, 2000);
    }
    
    function doSearch() {
        var tableReg = document.getElementById('main-table');
        var searchText = document.getElementById('searchTerm').value.toLowerCase();
        var cellsOfRow = "";
        var found = false;
        var compareWith = "";

        // Recorremos todas las filas con contenido de la tabla
        for (var i = 1; i < tableReg.rows.length; i++){
            cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
            found = false;
            // Recorremos todas las celdas
            for (var j = 0; j < cellsOfRow.length && !found; j++){
                compareWith = cellsOfRow[j].innerHTML.toLowerCase();
                // Buscamos el texto en el contenido de la celda
                if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1)){
                    found = true;
                }
            }
            if (found){
                tableReg.rows[i].style.display = '';
            } else {
                tableReg.rows[i].style.display = 'none'; // si no ha encontrado ninguna coincidencia, esconde la fila de la tabla
            }
        }
    }
    
    function jsShowWindowLoad(mensaje) {
        //eliminamos si existe un div ya bloqueando
        jsRemoveWindowLoad();

        //si no enviamos mensaje se pondra este por defecto
        if (mensaje === undefined) mensaje = "Procesando la información&amp;lt;br&amp;gt;Espere por favor";

        //centrar imagen gif
        height = 20;//El div del titulo, para que se vea mas arriba (H)
        var ancho = 0;
        var alto = 0;

        //obtenemos el ancho y alto de la ventana de nuestro navegador, compatible con todos los navegadores
        if (window.innerWidth == undefined) ancho = window.screen.width;
        else ancho = window.innerWidth;
        if (window.innerHeight == undefined) alto = window.screen.height;
        else alto = window.innerHeight;

        //operación necesaria para centrar el div que muestra el mensaje
        var heightdivsito = alto/2 - parseInt(height)/2;//Se utiliza en el margen superior, para centrar

       //imagen que aparece mientras nuestro div es mostrado y da apariencia de cargando
        imgCentro = "<div style='text-align:center;height:" + alto + "px;'><div  style='color:#000;margin-top:" + heightdivsito + "px; font-size:20px;font-weight:bold'>" + mensaje + "</div><img  src='../img/LoaderCustoms.gif' width='10%'></div>";

            //creamos el div que bloquea grande------------------------------------------
            div = document.createElement("div");
            div.id = "WindowLoad"
            div.style.width = ancho + "px";
            div.style.height = alto + "px";
            $("body").append(div);

            //creamos un input text para que el foco se plasme en este y el usuario no pueda escribir en nada de atras
            input = document.createElement("input");
            input.id = "focusInput";
            input.type = "text"

            //asignamos el div que bloquea
            $("#WindowLoad").append(input);

            //asignamos el foco y ocultamos el input text
            $("#focusInput").focus();
            $("#focusInput").hide();

            //centramos el div del texto
            $("#WindowLoad").html(imgCentro);
    }
    
    function jsRemoveWindowLoad() {
        // eliminamos el div que bloquea pantalla
        $("#WindowLoad").remove();
    }

