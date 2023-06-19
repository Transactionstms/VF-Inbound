<%-- 
    Document   : test5
    Created on : 15/06/2023, 09:45:30 AM
    Author     : luis_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet" />
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
        <style>
            #fruitsSelect {
              width: 200px; /* Adjust the width value as needed */
            }
        </style>
    </head>
    <body>
        <h1>Hello World!</h1>
        <div class="row">
            <select id="fruitsSelect" name="fruitsSelect" multiple>
                <option value="apple">Apple</option>
                <option value="banana">Banana</option>
                <option value="cherry">Cherry</option>
                <option value="grape">Grape</option>
                <option value="orange">Orange</option>
                <option value="pear">Pear</option>
            </select>
            Selected Fruits:
            <ul id="selectedFruitsList"></ul>
        </div>
        <div class="row">
            <a hrf="#" onclick="enviar()">Envíar Información</a>
        </div>
        <script>
            $(document).ready(function () {
                $('#fruitsSelect').select2({
                    closeOnSelect: false
                });

                // Event listener for fruit selection
                $('#fruitsSelect').on('select2:select', function (e) {
                    const fruitValue = e.params.data.id;
                    const fruitText = e.params.data.text;
                    addFruit(fruitValue, fruitText);
                });

                // Event listener for fruit deselection
                $('#fruitsSelect').on('select2:unselect', function (e) {
                    const fruitValue = e.params.data.id;
                    removeFruit(fruitValue);
                });
            });

            let selectedFruits = [];

            // Function to add a fruit to the selected fruits list
            function addFruit(fruitValue, fruitText) {
                selectedFruits.push(fruitValue);
                const newFruit = `<li>${fruitText}</li>`;
                $('#selectedFruitsList').append(newFruit);
            }

            // Function to remove a fruit from the selected fruits list
            function removeFruit(fruitValue) {
                selectedFruits = selectedFruits.filter((fruit) => fruit !== fruitValue);
                $('#selectedFruitsList li').each(function () {
                    if ($(this).text() === fruitValue) {
                        $(this).remove();
                        return false; // Exit the loop after removing the fruit
                    }
                });
            }

            function enviar(){
                let fruitsVal = document.getElementById('fruitsSelect').value;
                alert(fruitsVal);
            }
        </script>
    </body>
</html>
