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

function filtrerCheckbox(element, tipoFiltro) {
    let newElement = "";
    let blocked = "";
    let contTotalCheckbox =0;
        
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
    document.getElementById("buscadorFiltro").value="";

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
    let data = creacionFiltro(tipoFiltro);

    /*Tratar información de la celda seleccionada*/
    let array_list = eliminarDuplicadosYVacios(data);
    var parts = array_list.split('@');
    
    //Obtener el total de checkbox
    for (var i = 0; i < parts.length; i++) {
        contTotalCheckbox++;
    }
    
    // Iterate over the array/data
    for (var i = 0; i < parts.length; i++) {
        
        //validar si la opción ya existe en el contenedor/div
        if (!document.getElementById("checkbox_list-"+tipoFiltro+"-"+i)) {
                        
            //Rellenar Filtros
            document.getElementById("multiselect" + tipoFiltro).innerHTML += "<label><input type=\"checkbox\" id=\"checkbox_list-"+tipoFiltro+"-"+i+"\" value=\"" + parts[i] + "\" onclick=\"validateCheckboxPrimary("+tipoFiltro+","+contTotalCheckbox+")\"> " + parts[i] + "</label>";
        }
        
    }
}

function eliminarDuplicadosYVacios(cadenaConDuplicados) {
    // Dividir la cadena en un array usando la coma como delimitador
    var array = cadenaConDuplicados.split('@');

    // Eliminar duplicados y valores vacíos usando un conjunto (Set)
    var arraySinDuplicadosYVacios = [...new Set(array.filter(item => item.trim() !== ''))];

    // Unir el array sin duplicados y valores vacíos en una cadena usando la coma como separador
    var resultado = arraySinDuplicadosYVacios.join('@');

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
    const busqueda = document.getElementById('buscadorFiltro').value.toLowerCase();
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

    // Conversión de Array a String
    let data = String(seleccion);

    // Dividir la cadena en un array usando la coma como delimitador
    var array = data.split(',');

    // Eliminar duplicados y valores vacíos usando un conjunto (Set)
    var arrayCheckbox = [...new Set(array.filter(item => item.trim() !== ''))];

    // Unir el array sin duplicados y valores vacíos en una cadena usando la coma como separador
    var sendData = arrayCheckbox.join(',');


    if (sendData.replaceAll(",", "") !== null) {
        console.log('Selección: ' + sendData);
    } else {
        console.log('Seleccione Información');
        return false;
    }
    
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

    //Generación de Filtros (checkbox):
    await consultarCustoms(selected_referenciaAA,
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
           
}

function closeForm(cont) {
    
    /*ocultar elemento 'contenedor' */
    document.getElementById("form-popup-"+cont).style.display = "none";

    /*deseleccionar la lista de checkbox*/
    /*const checkboxes = document.querySelectorAll('#multiselect'+cont+' input[type="checkbox"]');
    checkboxes.forEach(checkbox => checkbox.checked = this.checked); */
    
    /*Limpiar buscador de filtros*/
    document.getElementById("buscadorFiltro").value="";
}

function creacionFiltro(tipoFiltro) {
    let columna;

    if (tipoFiltro === 1) {
        columna = selectElement1;
    } else if (tipoFiltro === 2) {
        columna = selectElement2;
    } else if (tipoFiltro === 3) {
        columna = selectElement3;
    } else if (tipoFiltro === 4) {
        columna = selectElement4;
    } else if (tipoFiltro === 5) {
        columna = selectElement5;
    } else if (tipoFiltro === 6) {
        columna = selectElement6;
    } else if (tipoFiltro === 7) {
        columna = selectElement7;
    } else if (tipoFiltro === 8) {
        columna = selectElement8;
    } else if (tipoFiltro === 9) {
        columna = selectElement9;
    } else if (tipoFiltro === 10) {
        columna = selectElement10;
    } else if (tipoFiltro === 11) {
        columna = selectElement11;
    } else if (tipoFiltro === 12) {
        columna = selectElement12;
    } else if (tipoFiltro === 13) {
        columna = selectElement13;
    } else if (tipoFiltro === 14) {
        columna = selectElement14;
    } else if (tipoFiltro === 15) {
        columna = selectElement15;
    } else if (tipoFiltro === 16) {
        columna = selectElement16;
    } else if (tipoFiltro === 17) {
        columna = selectElement17;
    } else if (tipoFiltro === 18) {
        columna = selectElement18;
    } else if (tipoFiltro === 19) {
        columna = selectElement19;
    } else if (tipoFiltro === 20) {
        columna = selectElement20;
    } else if (tipoFiltro === 21) {
        columna = selectElement21;
    } else if (tipoFiltro === 22) {
        columna = selectElement22;
    } else if (tipoFiltro === 23) {
        columna = selectElement23;
    } else if (tipoFiltro === 24) {
        columna = selectElement24;
    } else if (tipoFiltro === 25) {
        columna = selectElement25;
    } else if (tipoFiltro === 26) {
        columna = selectElement26;
    } else if (tipoFiltro === 27) {
        columna = selectElement27;
    } else if (tipoFiltro === 28) {
        columna = selectElement28;
    } else if (tipoFiltro === 29) {
        columna = selectElement29;
    } else if (tipoFiltro === 30) {
        columna = selectElement30;
    } else if (tipoFiltro === 31) {
        columna = selectElement31;
    } else if (tipoFiltro === 32) {
        columna = selectElement32;
    } else if (tipoFiltro === 33) {
        columna = selectElement33;
    } else if (tipoFiltro === 34) {
        columna = selectElement34;
    } else if (tipoFiltro === 35) {
        columna = selectElement35;
    } else if (tipoFiltro === 36) {
        columna = selectElement36;
    } else if (tipoFiltro === 37) {
        columna = selectElement37;
    } else if (tipoFiltro === 38) {
        columna = selectElement38;
    } else if (tipoFiltro === 39) {
        columna = selectElement39;
    } else if (tipoFiltro === 40) {
        columna = selectElement40;
    } else if (tipoFiltro === 41) {
        columna = selectElement41;
    } else if (tipoFiltro === 42) {
        columna = selectElement42;
    } else if (tipoFiltro === 43) {
        columna = selectElement43;
    } else if (tipoFiltro === 44) {
        columna = selectElement44;
    } else if (tipoFiltro === 45) {
        columna = selectElement45;
    } else if (tipoFiltro === 46) {
        columna = selectElement46;
    } else if (tipoFiltro === 47) {
        columna = selectElement47;
    } else if (tipoFiltro === 48) {
        columna = selectElement48;
    } else if (tipoFiltro === 49) {
        columna = selectElement49;
    } else if (tipoFiltro === 50) {
        columna = selectElement50;
    } else if (tipoFiltro === 51) {
        columna = selectElement51;
    } else if (tipoFiltro === 52) {
        columna = selectElement52;
    } else if (tipoFiltro === 53) {
        columna = selectElement53;
    } else if (tipoFiltro === 54) {
        columna = selectElement54;
    } else if (tipoFiltro === 55) {
        columna = selectElement55;
    } else if (tipoFiltro === 56) {
        columna = selectElement56;
    } else if (tipoFiltro === 57) {
        columna = selectElement57;
    } else if (tipoFiltro === 58) {
        columna = selectElement58;
    } else if (tipoFiltro === 59) {
        columna = selectElement59;
    } else if (tipoFiltro === 60) {
        columna = selectElement60;
    } else if (tipoFiltro === 61) {
        columna = selectElement61;
    } else if (tipoFiltro === 62) {
        columna = selectElement62;
    } else if (tipoFiltro === 63) {
        columna = selectElement63;
    } else if (tipoFiltro === 64) {
        columna = selectElement64;
    } else if (tipoFiltro === 65) {
        columna = selectElement65;
    } else if (tipoFiltro === 66) {
        columna = selectElement66;
    } else if (tipoFiltro === 67) {
        columna = selectElement67;
    } else if (tipoFiltro === 68) {
        columna = selectElement68;
    } else if (tipoFiltro === 69) {
        columna = selectElement69;
    } else if (tipoFiltro === 70) {
        columna = selectElement70;
    } else if (tipoFiltro === 71) {
        columna = selectElement71;
    } else if (tipoFiltro === 72) {
        columna = selectElement72;
    } else if (tipoFiltro === 73) {
        columna = selectElement73;
    } else if (tipoFiltro === 74) {
        columna = selectElement74;
    } else if (tipoFiltro === 75) {
        columna = selectElement75;
    } else if (tipoFiltro === 76) {
        columna = selectElement76;
    } else if (tipoFiltro === 77) {
        columna = selectElement77;
    } else if (tipoFiltro === 78) {
        columna = selectElement78;
    } else if (tipoFiltro === 79) {
        columna = selectElement79;
    } else if (tipoFiltro === 80) {
        columna = selectElement80;
    } else if (tipoFiltro === 81) {
        columna = selectElement81;
    } else if (tipoFiltro === 82) {
        columna = selectElement82;
    } else if (tipoFiltro === 83) {
        columna = selectElement83;
    } else if (tipoFiltro === 84) {
        columna = selectElement84;
    } else if (tipoFiltro === 85) {
        columna = selectElement85;
    }

    //Inicializar la columna seleccionada
    checkboxOn = tipoFiltro;

    return columna;
}

function createNewElementFiltrer(cont){
    
    // Crea un nuevo elemento div
    var nuevoElemento = document.createElement("div");
    let newElement = false; 

    // Añade contenido al nuevo elemento (opcional)
    // popup filtros
    nuevoElemento.innerHTML =  "<div id=\"form-popup-"+cont+"\"> "
                              +"    <div class=\"row\"> "  
                              +"        <input type=\"text\" id=\"buscadorFiltro\" oninput=\"filtrarOpciones("+cont+")\" placeholder=\"Buscar\"> "
                              +"    </div> "  
                              +"    <div class=\"scroll-container-filtrer\"> "
                              +"        <div id=\"multiselect"+cont+"\"> "
                              + "         <label><input type=\"checkbox\" id=\"seleccionarTodos"+cont+"\" onchange=\"seleccionarTodos("+cont+")\"> (Seleccionar Todo)</label>" 
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

function validateCheckboxPrimary(numColumna,contTotalCheckbox){
    
    let contCheckboxList = 0;
            
    //Validar si la opción '(Seleccionar Todo)', esta habilitada al deseleccionar la lista de opciones.
    if(document.getElementById('seleccionarTodos'+numColumna).checked === true){
        document.getElementById('seleccionarTodos'+numColumna).checked = false;
    }else{ 
        
        //Recorrer la lista, para reactivar la opción '(Seleccionar Todo)', si todas las opciones estan activas.
        for (var i = 0, max = contTotalCheckbox; i < max; i++) {
            if(document.getElementById('checkbox_list-'+numColumna+'-'+i).checked === true){
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