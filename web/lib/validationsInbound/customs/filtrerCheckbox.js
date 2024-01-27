/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function openFiltrer(element, array) {

    let array_list = eliminarDuplicadosYVacios(array);

    const popup = document.getElementById("form-popup");
    let blocked = "";

    if (popup.style.display === 'block') {
        blocked = "none";
    } else {
        blocked = "block";
    }

    /*obtener coordenadas del btn, para emular el filtro sobre su posición*/
    let top = coordenadas_top(element) + 25;
    let left = coordenadas_left(element);

    /*actualizar los valores del estilo '.form-popup' (emulación del filtro) */
    modificar_coordenadas(top, left);

    //Ocultar/Mostrar Popup
    document.getElementById("form-popup").style.display = blocked;

    var parts = array_list.split(',');

    // Iterate over the array
    for (var i = 0; i < parts.length; i++) {
        //Rellenar Filtros
        document.getElementById("multiselect").innerHTML += "<label><input type=\"checkbox\" value=\"" + parts[i] + "\"> " + parts[i] + "</label>";
    }

}

function eliminarDuplicadosYVacios(cadenaConDuplicados) {
    // Dividir la cadena en un array usando la coma como delimitador
    var array = cadenaConDuplicados.split(',');

    // Eliminar duplicados y valores vacíos usando un conjunto (Set)
    var arraySinDuplicadosYVacios = [...new Set(array.filter(item => item.trim() !== ''))];

    // Unir el array sin duplicados y valores vacíos en una cadena usando la coma como separador
    var resultado = arraySinDuplicadosYVacios.join(',');

    return resultado;
}

/*
 * 
 * @param {type} elem
 * @returns {Window.valorRectangulo.top|valor.top}
 * Exponer la variable en el entorno global (window)
 * 
 window.valorRectangulo = {
 x: valor.x,
 y: valor.y,
 width: valor.width,
 height: valor.height,
 top: valor.top,
 bottom: valor.bottom,
 left: valor.left,
 right: valor.right
 };
 console.log("Valores del rectángulo expuestos en window.valorRectangulo:", window.valorRectangulo);
 * 
 */

function coordenadas_top(element) {

    /*obtener las propiedades del btn para el filtro*/
    let valorT = element.getBoundingClientRect();

    /*obtener las coordenadas del elemento*/
    window.valorRectangulo = {
        top: valorT.top
    };
    return window.valorRectangulo.top;

}

function coordenadas_left(element) {

    /*obtener las propiedades del btn para el filtro*/
    let valorL = element.getBoundingClientRect();

    /*obtener las coordenadas del elemento*/
    window.valorRectangulo = {
        left: valorL.left
    };
    return window.valorRectangulo.left;

}

function modificar_coordenadas(top, left) {
    // Obtener el elemento
    var elemento = document.getElementById("form-popup");

    // Modificar las propiedades top y left
    elemento.style.top = top + "px";   // Cambiar a la posición vertical deseada
    elemento.style.left = left + "px"; // Cambiar a la posición horizontal deseada
}

function filtrarOpciones() {
    const busqueda = document.getElementById('buscadorFiltro').value.toLowerCase();
    const opciones = document.querySelectorAll('#multiselect label');

    /*iterar lista de checkbox para su selección*/
    opciones.forEach(opcion => {
        const textoOpcion = opcion.textContent.toLowerCase();
        const estaIncluida = textoOpcion.includes(busqueda);
        opcion.style.display = estaIncluida ? 'block' : 'none';
    });
}

function seleccionarTodos() {
    // Obtener el checkbox "Seleccionar Todo"
    var checkboxSeleccionarTodos = document.getElementById("seleccionarTodos");

    // Obtener todos los checkboxes dentro del div "multiselect"
    var checkboxes = document.querySelectorAll('#multiselect input[type="checkbox"]');

    // Establecer el estado de selección de todos los checkboxes según el estado del checkbox "Seleccionar Todo"
    checkboxes.forEach(checkbox => checkbox.checked = checkboxSeleccionarTodos.checked);
}

function obtenerSeleccion() {

    /*iterar los valores obtenidos para su envío*/
    const checkboxes = document.querySelectorAll('#multiselect input[type="checkbox"]:checked');
    const seleccion = Array.from(checkboxes).map(checkbox => checkbox.value);

    // Conversión de Array a String
    let data = String(seleccion);

    // Dividir la cadena en un array usando la coma como delimitador
    var array = data.split(',');

    // Eliminar duplicados y valores vacíos usando un conjunto (Set)
    var arrayCheckbox = [...new Set(array.filter(item => item.trim() !== ''))];

    // Unir el array sin duplicados y valores vacíos en una cadena usando la coma como separador
    var sendData = arrayCheckbox.join(',');


    if (sendData.replaceAll(",", "") !== null) {
        alert('Selección: ' + sendData);
    } else {
        alert('Seleccione Información');
    }

}

function closeForm() {
    document.getElementById("form-popup").style.display = "none";

    /*deseleccionar la lista de checkbox*/
    const checkboxes = document.querySelectorAll('#multiselect input[type="checkbox"]');
    checkboxes.forEach(checkbox => checkbox.checked = this.checked);
}

