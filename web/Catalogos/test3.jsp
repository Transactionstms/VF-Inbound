<%-- 
    Document   : test3
    Created on : 15/06/2023, 09:36:29 AM
    Author     : luis_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .multiselect-container {
              display: flex;
              align-items: center;
            }

            #searchInput {
              margin-right: 10px;
            }

            #multiselect {
              width: 200px;
            }

            .multiselect-controls {
              margin-left: 10px;
            }

            button {
              margin-bottom: 5px;
            }
        </style>
    </head>
    <body>
        <h1>Multiselect Options Controls Chosen</h1>
        <div class="multiselect-container">
            <input type="text" id="searchInput" placeholder="Buscar opciones">
            <select id="multiselect" multiple>
                <option value="1">Opción 1</option>
                <option value="2">Opción 2</option>
                <option value="3">Opción 3</option>
                <option value="4">Opción 4</option>
                <option value="5">Opción 5</option>
            </select>
            <div class="multiselect-controls">
                <button id="addOption">Agregar</button>
                <button id="removeOption">Eliminar</button>
            </div>
        </div>
        <script>
            // Obtener el elemento select
            const multiselect = document.getElementById("multiselect");

            // Obtener el input de búsqueda
            const searchInput = document.getElementById("searchInput");

            // Obtener los botones de agregar y eliminar opción
            const addOptionBtn = document.getElementById("addOption");
            const removeOptionBtn = document.getElementById("removeOption");

            // Manejar el evento de cambio en el input de búsqueda
            searchInput.addEventListener("input", function () {
              const searchTerm = searchInput.value.toLowerCase();

              // Iterar sobre las opciones y mostrar u ocultar según el término de búsqueda
              Array.from(multiselect.options).forEach(function (option) {
                const optionText = option.text.toLowerCase();

                if (optionText.includes(searchTerm)) {
                  option.style.display = "block";
                } else {
                  option.style.display = "none";
                }
              });
            });

            // Manejar el evento de clic en el botón "Agregar"
            addOptionBtn.addEventListener("click", function () {
              const optionText = prompt("Ingrese el texto de la opción:");
              const optionValue = prompt("Ingrese el valor de la opción:");

              // Crear una nueva opción
              const newOption = new Option(optionText, optionValue);

              // Agregar la opción al multiselect
              multiselect.appendChild(newOption);
            });

            // Manejar el evento de clic en el botón "Eliminar"
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
