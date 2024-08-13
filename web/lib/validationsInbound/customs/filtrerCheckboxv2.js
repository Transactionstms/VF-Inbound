/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

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
let cadena = "";
let historic_print = "";

// Crear o inicializar lista de arrays fuera del contexto del filtro.
let listaDeArraysCheckBoxNotChecked = [];
const valoresAgrupadosCheckBoxNotChecked = {};

// Crear o inicializar lista de arrays dentro del contexto del filtro.
let listaDeArraysCheckBoxChecked = [];
const valoresAgrupadosCheckBoxChecked = {};

/*Contador para la (Información seleccionada) + (Información no seleccionada) */
let contadorlist = 0;
let contTotalCheckbox =0;
let confirm_check = "";
let confirm_not_check = "";

function filtrerCheckbox(element, tipoFiltro, list) {
    let newElement = "";
    let blocked = "";
    let data_checked = "";
    let data_not_checked = "";
    
    //Consultar filtros seleccionados
    let checked = print_historic_checked(tipoFiltro); 
        
    //Consultar filtros no seleccionados:
    let not_checked = print_historic_not_checked(tipoFiltro);  
    
    /* Si existe selección de lista checked: Igualar la información del Array */
    if(checked ==="0@0"){
        data_checked = list;
    }else{
        data_checked = checked;
    } 
    
    /* Si no existe selección de lista no checked: Igualar la información del Array, o en su caso del servlet. */
    if(not_checked ==="0@0"){                  
        data_not_checked = "NA";     
    }else{
        data_not_checked = not_checked;
    } 
        
    /*Consultar si el elemento 'contenedor' existe*/
    if (!document.getElementById("form-popup-"+tipoFiltro)) { 

        //Creación de elemento 'contenedor' 
        newElement = createNewElementFiltrer(tipoFiltro);

        if (newElement) {
            createNewStyleFiltrer(tipoFiltro); /*añadir css al contenedor*/
        } else {
            alert("Recargar Página, error en filtro.");
            return false;
        }

    }
   
    /*leer cuerpo del elemento 'contenedor'*/
    const popup = document.getElementById("form-popup-"+tipoFiltro);
    
    /*Limpiar buscador de filtros*/
    //cleanSearch(tipoFiltro);

    if (popup.style.display === 'block') {
        blocked = "none";
    } else {
        blocked = "block";
    }
    
    /*obtener coordenadas del btn, para emular el filtro sobre su posición (existente)*/
    let top = coordenadas_top(element) + 25;
    let left = coordenadas_left(element);

    /*actualizar los valores del estilo '.form-popup' emulación del filtro (existente) */
    modificar_coordenadas(top, left, tipoFiltro);

    //Ocultar/Mostrar Popup
    document.getElementById("form-popup-"+tipoFiltro).style.display = blocked;

    /*Obtener la información de la columna seleccionada*/
    checkboxOn = tipoFiltro;
    document.getElementById("multiselect" + tipoFiltro).innerHTML = "";
    document.getElementById("multiselect" + tipoFiltro).innerHTML = "<label><input type=\"checkbox\" id=\"seleccionarTodos"+tipoFiltro+"\" onchange=\"seleccionarTodos("+tipoFiltro+")\"> (Seleccionar Todo)</label>";
 
    /******************************** Tratar la información seleccionada ********************************/
    
    let array_list_checked = eliminarDuplicadosYVacios(data_checked);
        
    var parts_cheked = array_list_checked.split('@');

    //Obtener el total de checkbox checked
    for (var i = 0; i < parts_cheked.length; i++) {
        contTotalCheckbox++;  //Al terminar de iterar, el valor lo va inicializar en la 2da iteración de los no seleccionados.
    }

    //Rellenar listas cheked
    if(checked !=="0@0"){
        confirm_check = "checked";
    }

    // Iterate over the array/data_checked
    for (var i = 0; i < parts_cheked.length; i++) {
        document.getElementById("multiselect" + tipoFiltro).innerHTML += "<label><input type=\"checkbox\" id=\"checkbox_list-"+tipoFiltro+"-"+i+"\" value=\"" + parts_cheked[i] + "\" onclick=\"validateCheckboxPrimary("+tipoFiltro+")\" "+confirm_check+"> " + parts_cheked[i] + "</label>";
        contadorlist++;  //Mandar valor a la siguiente iteración, para continuar con el numero de listas de chekbox
    }
    
    /******************************** Tratar la información no seleccionada ********************************/
    
    let array_list_not_checked = eliminarDuplicadosYVacios(data_not_checked);
    
    if(array_list_not_checked !== "NA"){
        
        var parts_not_checked = array_list_not_checked.split('@');

        // Iterate over the array/data_not_checked
        for (var j = 0; j < parts_not_checked.length; j++) {
           //Rellenar listas no cheked
           document.getElementById("multiselect" + tipoFiltro).innerHTML += "<label><input type=\"checkbox\" id=\"checkbox_list-"+tipoFiltro+"-"+j+"\" value=\"" + parts_not_checked[j] + "\" onclick=\"validateCheckboxPrimary("+tipoFiltro+")\"> " + parts_not_checked[j] + "</label>";
        }
        
    }
    
    /******************************** validar si alguna de las opciones ya existe en el contenedor/div ********************************/
   
    /*if (document.getElementById("checkbox_list-"+tipoFiltro+"-"+i)) {
       seleccionarTodos(tipoFiltro);
    }*/
    
} 

function eliminarDuplicadosYVacios(cadenaConDuplicados) {
    
    // Valor retorno
    var resultado;
    
    if(cadenaConDuplicados !== "NA"){
        
        // Dividir la cadena en un array usando la coma como delimitador
        var array = cadenaConDuplicados.split('@');

        // Eliminar duplicados y valores vacíos usando un conjunto (Set)
        var arraySinDuplicadosYVacios = [...new Set(array.filter(item => item.trim() !== ''))];

        // Unir el array sin duplicados y valores vacíos en una cadena usando la coma como separador
        resultado = arraySinDuplicadosYVacios.join('@');
        
    }else{
        resultado = "NA";
    }

    return resultado;
}

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

function modificar_coordenadas(top, left, cont) {
    // Obtener el elemento
    var elemento = document.getElementById("form-popup-"+cont);

    // Modificar las propiedades top y left
    elemento.style.top = top + "px";   // Cambiar a la posición vertical deseada
    elemento.style.left = left + "px"; // Cambiar a la posición horizontal deseada
}

function filtrarOpciones(cont) {
    const busqueda = document.getElementById('buscadorFiltro-'+cont).value.toLowerCase();
    const opciones = document.querySelectorAll('#multiselect'+cont+' label');

    /*iterar lista de checkbox para su selección*/
    opciones.forEach(opcion => {
        const textoOpcion = opcion.textContent.toLowerCase();
        const estaIncluida = textoOpcion.includes(busqueda);
        opcion.style.display = estaIncluida ? 'block' : 'none';
    });
}

function seleccionarTodos(cont) {
    // Obtener el checkbox "Seleccionar Todo"
    var checkboxSeleccionarTodos = document.getElementById("seleccionarTodos"+cont);

    // Obtener todos los checkboxes dentro del div "multiselect"
    var checkboxes = document.querySelectorAll('#multiselect'+cont+' input[type="checkbox"]');

    // Establecer el estado de selección de todos los checkboxes según el estado del checkbox "Seleccionar Todo"
    checkboxes.forEach(checkbox => checkbox.checked = checkboxSeleccionarTodos.checked);
}

async function obtenerSeleccion(cont) {
    /*mostrar loader*/
    await mostrarLoader();
    
    /*iterar los valores obtenidos para su envío*/
    const checkboxes = document.querySelectorAll('#multiselect'+cont+' input[type="checkbox"]:checked');
    const seleccion = Array.from(checkboxes).map(checkbox => checkbox.value);
    
    /*iterar los valores no obtenidos para su envío (historicos por columna)*/
    const checkboxesNoMarcados = document.querySelectorAll('#multiselect'+cont+' input[type="checkbox"]:not(:checked)');
    const deseleccion = [];
    checkboxesNoMarcados.forEach(checkbox => { deseleccion.push(checkbox.value) }); //Se almacenan la lista en un array

    /******************************** (Checkbox Checked) ********************************/

    // Conversión de Array a String 
    let data_checked = seleccion.join('@');    // Convierte el arreglo en una cadena con '@' como separador
    const sendArray = String(data_checked).replaceAll('on', '');

    // Dividir la cadena en un array usando la coma como delimitador
    var array_checked = sendArray.split('@');

    // Eliminar duplicados y valores vacíos usando un conjunto (Set)
    var arrayCheckbox_send = [...new Set(array_checked.filter(item => item.trim() !== ''))];

    // Unir el array sin duplicados y valores vacíos en una cadena usando la coma como separador
    var sendData = arrayCheckbox_send.join('@');
    
    /******************************** (Checkbox No Checked) ********************************/    
    
    // Conversión de Array a String 
    let data_notchecked = deseleccion.join('@');
    const notSendArray = String(data_notchecked).replaceAll('on', '');

    // Dividir la cadena en un array usando la coma como delimitador
    var array_nochecked = notSendArray.split('@');

    // Eliminar duplicados y valores vacíos usando un conjunto (Set)
    var arrayCheckbox_notsend = [...new Set(array_nochecked.filter(item => item.trim() !== ''))];

    // Unir el array sin duplicados y valores vacíos en una cadena usando la coma como separador
    var notSendData = arrayCheckbox_notsend.join('@');
    
    /******************************** (Envío de Información ya Tratada) ********************************/  
    
    //Cerrar emulador de filtros
    document.getElementById("form-popup-"+cont).style.display = "none";

    /*
     (1). Selección de Filtros: (if)
     (2). Lectura de Histórico: (else)
     */

    if (checkboxOn === 1) {
        selected_referenciaAA = sendData;
    }
    if (checkboxOn === 2) {
        selected_evento = sendData;
    }
    if (checkboxOn === 3) {
        selected_responsable = sendData;
    }
    if (checkboxOn === 4) {
        selected_final_destination = sendData;
    }
    if (checkboxOn === 5) {
        selected_brand_division = sendData;
    }
    if (checkboxOn === 6) {
        selected_division = sendData;
    }
    if (checkboxOn === 7) {
        selected_shipmentId = sendData;
    } 
    if (checkboxOn === 8) {
        selected_containerId = sendData;
    } 
    if (checkboxOn === 9) {
        selected_blAwbPro = sendData;
    }
    if (checkboxOn === 10) {
        selected_loadTypeFinal = sendData;
    }
    if (checkboxOn === 11) {
        selected_quantity = sendData;
    }
    if (checkboxOn === 12) {
        selected_pod = sendData;
    }
    if (checkboxOn === 13) {
        selected_estDepartFromPol = sendData;
    }
    if (checkboxOn === 14) {
        selected_etaRealPortOfDischarge = sendData;
    }
    if (checkboxOn === 15) {
        selected_estEtaDc = sendData;
    }
    if (checkboxOn === 16) {
        selected_inboundNotification = sendData;
    }
    if (checkboxOn === 17) {
        selected_pol = sendData;
    }
    if (checkboxOn === 18) {
        selected_aa = sendData;
    }
    if (checkboxOn === 19) {
        selected_fechaMesVenta = sendData;
    }
    if (checkboxOn === 20) {
        selected_prioridad = sendData;
    }
    if (checkboxOn === 21) {
        selected_pais_origen = sendData;
    }
    if (checkboxOn === 22) {
        selected_size_container = sendData;
    }
    if (checkboxOn === 23) {
        selected_valor_usd = sendData;
    }
    if (checkboxOn === 24) {
        selected_eta_port_discharge = sendData;
    }
    if (checkboxOn === 25) {
        selected_agente_aduanal = sendData;
    }
    if (checkboxOn === 26) {
        selected_pedimento_a1 = sendData;
    }
    if (checkboxOn === 27) {
        selected_pedimento_r1_1er = sendData;
    }
    if (checkboxOn === 28) {
        selected_motivo_rectificacion_1er = sendData;
    }
    if (checkboxOn === 29) {
        selected_pedimento_r1_2do = sendData;
    }
    if (checkboxOn === 30) {
        selected_motivo_rectificacion_2do = sendData;
    }
    if (checkboxOn === 31) {
        selected_fecha_recepcion_doc = sendData;
    }
    if (checkboxOn === 32) {
        selected_recinto = sendData;
    }
    if (checkboxOn === 33) {
        selected_naviera = sendData;
    }
    if (checkboxOn === 34) {
        selected_buque = sendData;
    }
    if (checkboxOn === 35) {
        selected_fecha_revalidacion = sendData;
    }
    if (checkboxOn === 36) {
        selected_fecha_previo_origen = sendData;
    }
    if (checkboxOn === 37) {
        selected_fecha_previo_destino = sendData;
    }
    if (checkboxOn === 38) {
        selected_fecha_resultado_previo = sendData;
    }
    if (checkboxOn === 39) {
        selected_proforma_final = sendData;
    }
    if (checkboxOn === 40) {
        selected_permiso = sendData;
    }
    if (checkboxOn === 41) {
        selected_fecha_envio = sendData;
    }
    if (checkboxOn === 42) {
        selected_fecha_recepcion_perm = sendData;
    }
    if (checkboxOn === 43) {
        selected_fecha_activacion_perm = sendData;
    }
    if (checkboxOn === 44) {
        selected_fecha_permisos_aut = sendData;
    }
    if (checkboxOn === 45) {
        selected_co_pref_arancelaria = sendData;
    }
    if (checkboxOn === 46) {
        selected_aplic_pref_arancelaria = sendData;
    }
    if (checkboxOn === 47) {
        selected_req_uva = sendData;
    }
    if (checkboxOn === 48) {
        selected_req_ca = sendData;
    }
    if (checkboxOn === 49) {
        selected_fecha_recepcion_ca = sendData;
    }
    if (checkboxOn === 50) {
        selected_num_constancia_ca = sendData;
    }
    if (checkboxOn === 51) {
        selected_monto_ca = sendData;
    }
    if (checkboxOn === 52) {
        selected_fecha_doc_completos = sendData;
    }
    if (checkboxOn === 53) {
        selected_fecha_pago_pedimento = sendData;
    }
    if (checkboxOn === 54) {
        selected_fecha_solicitud_transporte = sendData;
    }
    if (checkboxOn === 55) {
        selected_fecha_modulacion = sendData;
    }
    if (checkboxOn === 56) {
        selected_modalidad = sendData;
    }
    if (checkboxOn === 57) {
        selected_resultado_modulacion = sendData;
    }
    if (checkboxOn === 58) {
        selected_fecha_reconocimiento = sendData;
    }
    if (checkboxOn === 59) {
        selected_fecha_liberacion = sendData;
    }
    if (checkboxOn === 60) {
        selected_sello_origen = sendData;
    }
    if (checkboxOn === 61) {
        selected_sello_final = sendData;
    }
    if (checkboxOn === 62) {
        selected_fecha_retencion_aut = sendData;
    }
    if (checkboxOn === 63) {
        selected_fecha_liberacion_aut = sendData;
    }
    if (checkboxOn === 64) {
        selected_estatus_operacion = sendData;
    }
    if (checkboxOn === 65) {
        selected_motivo_atraso = sendData;
    }
    if (checkboxOn === 66) {
        selected_observaciones = sendData;
    }

    if (idAgenteAduanal === '4001' || idAgenteAduanal === '4006') { //LOGIX Y VF  

        if (checkboxOn === 67) {
            selected_llegada_a_nova = sendData;
        }
        if (checkboxOn === 68) {
            selected_llegada_a_globe_trade_sd = sendData;
        }
        if (checkboxOn === 69) {
            selected_archivo_m = sendData;
        }
        if (checkboxOn === 70) {
            selected_fecha_archivo_m = sendData;
        }
        if (checkboxOn === 71) {
            selected_fecha_solicit_manip = sendData;
        }
        if (checkboxOn === 72) {
            selected_fecha_vencim_manip = sendData;
        }
        if (checkboxOn === 73) {
            selected_fecha_confirm_clave_pedim = sendData;
        }
        if (checkboxOn === 74) {
            selected_fecha_recep_increment = sendData;
        }
        if (checkboxOn === 75) {
            selected_t_e = sendData;
        }
        if (checkboxOn === 76) {
            selected_fecha_vencim_inbound = sendData;
        }

    }

    if (idAgenteAduanal === '4002' || idAgenteAduanal === '4006') {  //CUSA Y VF
        
        if (checkboxOn === 77) {
            selected_no_bultos = sendData;
        }
        if (checkboxOn === 78) {
            selected_peso_kg = sendData;
        }
        if (checkboxOn === 79) {
            selected_transferencia = sendData;
        }
        if (checkboxOn === 80) {
            selected_fecha_inicio_etiquetado = sendData;
        }
        if (checkboxOn === 81) {
            selected_fecha_termino_etiquetado = sendData;
        }
        if (checkboxOn === 82) {
            selected_hora_termino_etiquetado = sendData;
        }
        if (checkboxOn === 83) {
            selected_proveedor = sendData;
        }
        if (checkboxOn === 84) {
            selected_proveedor_carga = sendData;
        }

    }
    
    if (checkboxOn === 85) {
        selected_fy = sendData;
    }

    contSubfiltros++;
    console.log("N°Subfiltros: " + contSubfiltros);
    dataEnd = sendData;
    console.log("Dato Seleccionado: " + dataEnd);
    
    //Generación de Filtros (checkbox):
    await consultarCustomsFiltros(selected_referenciaAA,
                            selected_evento,
                            selected_responsable,
                            selected_final_destination,
                            selected_brand_division,
                            selected_division,
                            selected_shipmentId,
                            selected_containerId,
                            selected_blAwbPro,
                            selected_loadTypeFinal,
                            selected_quantity,
                            selected_pod,
                            selected_estDepartFromPol,
                            selected_etaRealPortOfDischarge,
                            selected_estEtaDc,
                            selected_inboundNotification,
                            selected_pol,
                            selected_aa,
                            selected_fechaMesVenta,
                            selected_prioridad,
                            selected_pais_origen,
                            selected_size_container,
                            selected_valor_usd,
                            selected_eta_port_discharge,
                            selected_agente_aduanal,
                            selected_pedimento_a1,
                            selected_pedimento_r1_1er,
                            selected_motivo_rectificacion_1er,
                            selected_pedimento_r1_2do,
                            selected_motivo_rectificacion_2do,
                            selected_fecha_recepcion_doc,
                            selected_recinto,
                            selected_naviera,
                            selected_buque,
                            selected_fecha_revalidacion,
                            selected_fecha_previo_origen,
                            selected_fecha_previo_destino,
                            selected_fecha_resultado_previo,
                            selected_proforma_final,
                            selected_permiso,
                            selected_fecha_envio,
                            selected_fecha_recepcion_perm,
                            selected_fecha_activacion_perm,
                            selected_fecha_permisos_aut,
                            selected_co_pref_arancelaria,
                            selected_aplic_pref_arancelaria,
                            selected_req_uva,
                            selected_req_ca,
                            selected_fecha_recepcion_ca,
                            selected_num_constancia_ca,
                            selected_monto_ca,
                            selected_fecha_doc_completos,
                            selected_fecha_pago_pedimento,
                            selected_fecha_solicitud_transporte,
                            selected_fecha_modulacion,
                            selected_modalidad,
                            selected_resultado_modulacion,
                            selected_fecha_reconocimiento,
                            selected_fecha_liberacion,
                            selected_sello_origen,
                            selected_sello_final,
                            selected_fecha_retencion_aut,
                            selected_fecha_liberacion_aut,
                            selected_estatus_operacion,
                            selected_motivo_atraso,
                            selected_observaciones,
                            selected_llegada_a_nova,
                            selected_llegada_a_globe_trade_sd,
                            selected_archivo_m,
                            selected_fecha_archivo_m,
                            selected_fecha_solicit_manip,
                            selected_fecha_vencim_manip,
                            selected_fecha_confirm_clave_pedim,
                            selected_fecha_recep_increment,
                            selected_t_e,
                            selected_fecha_vencim_inbound,
                            selected_no_bultos,
                            selected_peso_kg,
                            selected_transferencia,
                            selected_fecha_inicio_etiquetado,
                            selected_fecha_termino_etiquetado,
                            selected_hora_termino_etiquetado,
                            selected_proveedor,
                            selected_proveedor_carga,
                            selected_fy);
 
    //Crear array con la lista de información seleccionada: (Checkbox seleccionados)
    add_list_historic_checked(checkboxOn, sendData);   
    
    //Crear array con la lista de información no seleccionada: (Checkbox no seleccionados)
    add_list_historic_not_checked(checkboxOn, notSendData);   
}

function closeForm(cont) {
    
    /*ocultar elemento 'contenedor' */
    document.getElementById("form-popup-"+cont).style.display = "none";

    /*deseleccionar la lista de checkbox*/
    /*const checkboxes = document.querySelectorAll('#multiselect'+cont+' input[type="checkbox"]');
    checkboxes.forEach(checkbox => checkbox.checked = this.checked); */
    
    /*Limpiar buscador de filtros*/
    //cleanSearch(cont);
}

function createNewElementFiltrer(cont){
    
    // Crea un nuevo elemento div
    var nuevoElemento = document.createElement("div");
    let newElement = false; 

    // Añade contenido al nuevo elemento (opcional)
    // popup filtros
    nuevoElemento.innerHTML =  "<div id=\"form-popup-"+cont+"\"> "
                              +"    <div class=\"row\"> "  
                              +"        <input type=\"text\" id=\"buscadorFiltro-"+cont+"\" oninput=\"filtrarOpciones("+cont+")\" placeholder=\"Buscar\"> "
                              +"    </div> "  
                              +"    <div class=\"scroll-container-filtrer\"> "
                              +"        <div id=\"multiselect"+cont+"\"> "
                              //+ "         <label><input type=\"checkbox\" id=\"seleccionarTodos"+cont+"\" onchange=\"seleccionarTodos("+cont+")\"> (Seleccionar Todo)</label>" 
                              +"        </div> " 
                              +"    </div> "
                              +"    <div class=\"contenedorFiltro\"> " 
                              +"        <div class=\"columnaFiltro1\"><button onclick=\"obtenerSeleccion("+cont+")\">Aceptar</button></div> "
                              +"        <div class=\"columnaFiltro2\"><button onclick=\"closeForm("+cont+")\">Cancelar</button></div> "
                              +"    </div> "
                              +"</div>";
                      
    // Añade el nuevo elemento al cuerpo del documento (o a otro lugar deseado)
    document.body.appendChild(nuevoElemento);
    
    //validar si el elemento html, se creo en el body
    if (document.body.contains(nuevoElemento)) {
        newElement = true;
        createNewStyleSearch(cont);
    }else{
        newElement = false;
    }

    return newElement;
}

function createNewStyleFiltrer(cont){
    // Create a style element
    var styleElement = document.createElement('style');

    // Set the type attribute to 'text/css'
    styleElement.type = 'text/css';

    // Definir las reglas del estilo
    var cssRules = `
        #form-popup-`+cont+` {
           display: none;
           position: absolute; /*Se visualiza por encima de los demas elementos html*/
           top: 125px; /* Ajusta según sea necesario */
           left: 150px;
           border: 1px solid #d1cfcf;
           z-index: 9;
           background-color: #fff;
        }
    `;

    // Add the CSS rules to the style element
    if (styleElement.styleSheet) {
        // For Internet Explorer
        styleElement.styleSheet.cssText = cssRules;
    } else {
        // For other browsers
        styleElement.appendChild(document.createTextNode(cssRules));
    }

    // Append the style element to the head of the document
    document.head.appendChild(styleElement);
}

function validateCheckboxPrimary(numColumna){
    
    let contCheckboxList = 0;
            
    //Validar si la opción '(Seleccionar Todo)', esta habilitada al deseleccionar la lista de opciones.
    if(document.getElementById('seleccionarTodos'+numColumna).checked === true){
        document.getElementById('seleccionarTodos'+numColumna).checked = false;
    }else{ 
        
        //Recorrer la lista, para reactivar la opción '(Seleccionar Todo)', si todas las opciones estan activas.
        for (var i = 0, max = contTotalCheckbox; i < max; i++) {
            
            let option = document.getElementById('checkbox_list-'+numColumna+'-'+i);
            if(option.checked === true){
                contCheckboxList++; 
            }
        }
        
        //Si la lista de checkbox es completa, se reactiva la opción '(Seleccionar Todo)'.
        if(contTotalCheckbox===contCheckboxList){
            document.getElementById('seleccionarTodos'+numColumna).checked = true;
            seleccionarTodos(contCheckboxList);
        }
        
    }
    
}

function createNewStyleSearch(cont){
    // Create a style element
    var styleElement = document.createElement('style');

    // Set the type attribute to 'text/css'
    styleElement.type = 'text/css';

    // Definir las reglas del estilo
    var cssRules = `
        #buscadorFiltro-`+cont+` {
          width: 200px;
          display: block;
          margin-right: auto;
          margin-left: auto;
          margin-top: 5px;
          margin-bottom: 5px;
        }
    `;

    // Add the CSS rules to the style element
    if (styleElement.styleSheet) {
        // For Internet Explorer
        styleElement.styleSheet.cssText = cssRules;
    } else {
        // For other browsers
        styleElement.appendChild(document.createTextNode(cssRules));
    }

    // Append the style element to the head of the document
    document.head.appendChild(styleElement);
}

function cleanSearch(cont){
    document.getElementById("buscadorFiltro-"+cont).value='';
}

/*********************************** Historico CheckBox por Columnas ***********************************/

function add_list_historic_checked(numcolumna,list){ 
 
    let posicion = parseInt(numcolumna); //num columna (información ya registrada). Ejemplo: 23
    let info = list;                 //(Información ya registrada). Ejemplo: [1237,455487]

    //Castear string to array
    const casteo = info.split("@"); 

    // Agregar nuevos elementos a listaDeArraysCheckBoxNotChecked
    listaDeArraysCheckBoxChecked = listaDeArraysCheckBoxChecked.concat(casteo.filter(element => element.trim() !== "").map((element, index) => {
        console.log(listaDeArraysCheckBoxChecked);

        const objetoActual = {
            index: posicion,
            value: element
        };

        // Agrupar valores por índice en el nuevo objeto
        if (!valoresAgrupadosCheckBoxChecked[posicion]) {
            valoresAgrupadosCheckBoxChecked[posicion] = [];
        }

        valoresAgrupadosCheckBoxChecked[posicion].push(objetoActual);

        return objetoActual;
    }));

}

function print_historic_checked(numcolumna){
    
    let valoresConcatenados = "";  // Inicializamos una variable para acumular los valores

    if (valoresAgrupadosCheckBoxChecked[numcolumna] && valoresAgrupadosCheckBoxChecked[numcolumna].length > 0) {
        const indices = valoresAgrupadosCheckBoxChecked[numcolumna][0].index; // Tomar el índice del primer elemento
        
        for (var i = 0; i < valoresAgrupadosCheckBoxChecked[numcolumna].length; i++) {
            const valorPorIndice = valoresAgrupadosCheckBoxChecked[numcolumna][i].value;
            valoresConcatenados += valorPorIndice + "@";  // Concatenamos el valor
        }
        valoresConcatenados=valoresConcatenados.slice(0,-1);
    }else{
        valoresConcatenados = "0@0";
    }
    
   return valoresConcatenados;
}

function add_list_historic_not_checked(numcolumna,list){ 
 
    let posicion = parseInt(numcolumna); //num columna (información ya registrada). Ejemplo: 23
    let info = list;                 //(Información ya registrada). Ejemplo: [1237,455487]

    //Castear string to array
    const casteo = info.split("@"); 

    // Agregar nuevos elementos a listaDeArraysCheckBoxNotChecked
    listaDeArraysCheckBoxNotChecked = listaDeArraysCheckBoxNotChecked.concat(casteo.filter(element => element.trim() !== "").map((element, index) => {
        console.log(listaDeArraysCheckBoxNotChecked);

        const objetoActual = {
            index: posicion,
            value: element
        };

        // Agrupar valores por índice en el nuevo objeto
        if (!valoresAgrupadosCheckBoxNotChecked[posicion]) {
            valoresAgrupadosCheckBoxNotChecked[posicion] = [];
        }

        valoresAgrupadosCheckBoxNotChecked[posicion].push(objetoActual);

        return objetoActual;
    }));

}

function print_historic_not_checked(numcolumna){
    
    let valoresConcatenados = "";  // Inicializamos una variable para acumular los valores

    if (valoresAgrupadosCheckBoxNotChecked[numcolumna] && valoresAgrupadosCheckBoxNotChecked[numcolumna].length > 0) {
        const indices = valoresAgrupadosCheckBoxNotChecked[numcolumna][0].index; // Tomar el índice del primer elemento
        
        for (var i = 0; i < valoresAgrupadosCheckBoxNotChecked[numcolumna].length; i++) {
            const valorPorIndice = valoresAgrupadosCheckBoxNotChecked[numcolumna][i].value;
            valoresConcatenados += valorPorIndice + "@";  // Concatenamos el valor
        }
        valoresConcatenados=valoresConcatenados.slice(0,-1);
    }else{
        valoresConcatenados = "0@0";
    }
    
   return valoresConcatenados;
}

/*destructure('Fazt','Giveaway');

function destructure(args){
    
    const fazt = args[0];
    const giveaway = args[1];
    
    const [fazt,giveaway] = args;  
}*/
