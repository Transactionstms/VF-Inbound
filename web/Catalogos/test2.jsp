<%-- 
    Document   : test2
    Created on : 15/06/2023, 09:33:25 AM
    Author     : luis_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            #multiselect {
                width: 200px;
            }

            #multiselect option {
                padding: 5px;
            }

            button {
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <h1>Multiselect Options Controls</h1>
        <br>
        <select id="multiselect" multiple>
            <option value="1">Opción 1</option>
            <option value="2">Opción 2</option>
            <option value="3">Opción 3</option>
            <option value="4">Opción 4</option>
            <option value="5">Opción 5</option>
        </select>

        <button id="addOption">Agregar Opción</button>
        <button id="removeOption">Eliminar Opción</button>
        <script>
            // Obtener el elemento select
            const multiselect = document.getElementById("multiselect");

// Obtener los botones de agregar y eliminar opción
            const addOptionBtn = document.getElementById("addOption");
            const removeOptionBtn = document.getElementById("removeOption");

// Manejar el evento de clic en el botón "Agregar Opción"
            addOptionBtn.addEventListener("click", function () {
                const optionText = prompt("Ingrese el texto de la opción:");
                const optionValue = prompt("Ingrese el valor de la opción:");

                // Crear una nueva opción
                const newOption = new Option(optionText, optionValue);

                // Agregar la opción al multiselect
                multiselect.appendChild(newOption);
            });

// Manejar el evento de clic en el botón "Eliminar Opción"
            removeOptionBtn.addEventListener("click", function () {
                // Obtener las opciones seleccionadas
                const selectedOptions = Array.from(multiselect.selectedOptions);

                // Eliminar las opciones seleccionadas
                selectedOptions.forEach(function (option) {
                    multiselect.removeChild(option);
                });
            });
        </script> 
    </body>
</html>
