/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

//Parametros: Excel
let txt_msg = "";
let fila = 0;
let celda;
let valor = "";
let validar = "";

function leerArchivo() {

    var fileInput = document.getElementById('input-id');

    var archivo = fileInput.files[0];
    var lector = new FileReader();

    lector.onload = function (e) {
        var contenido = e.target.result;
        var workbook = XLSX.read(contenido, {type: 'array'});

        var primeraHoja = workbook.SheetNames[0];
        var hoja = workbook.Sheets[primeraHoja];

        var datos = XLSX.utils.sheet_to_json(hoja, {header: 1});

        // Itera sobre las filas del Excel
        for (var i = 2; i < datos.length; i++) {

            // Agrega celdas a la nueva fila
            for (var k = 0; k < datos[i].length; k++) {

                valor = datos[i][k];
                fila = parseInt([i]) + 1;
                //console.log("Núm Celda:" + k + " - Fila: " + [i] + " -  Valor: " + valor);
                
                if (valor === undefined || valor === null) {
                    valor = "";  // Puedes asignar otro valor predeterminado si lo deseas
                }
                
                if(k ===10 || k ===11 || k ===13){
                    if(valor.replace(/\s/g, "") !== ""){
                        valor = valor;
                    }else{
                        valor = "00 00 0000 0000000";
                    }
                }
                
                if (idAgenteAduanal === "4006") { //ADMINISTRADOR
                    validar += relacion_columnas_excel_administrador(k, valor, fila);
                }

                if (idAgenteAduanal === "4001") { //LOGIX
                    validar += relacion_columnas_excel_logix(k, valor, fila);
                }

                if (idAgenteAduanal === "4002") {  //CUSA
                    validar += relacion_columnas_excel_cusa(k, valor, fila);
                }

                if (idAgenteAduanal !== "4001" && idAgenteAduanal !== "4002" && idAgenteAduanal !== "4006") { //GENERICO 
                    validar += relacion_columnas_excel_generico(k, valor, fila);
                }

            }
            
        }
        
        if (validar === "") {
             logExcel();
        } else {
            swal("",resultado,"warning");
            ocultarLoader();
            console.log("Excel con errores");
            return false;
        }

    };

    lector.readAsArrayBuffer(archivo);
    
}

function relacion_columnas_excel_administrador(posicion, contenido, fila) {

    let logErrores = "";
    console.log("!Excel Administrador¡");

    if (posicion === 0 || posicion === 6 || posicion === 7 || posicion === 34 || posicion === 35) {
        celda = validarNumero_excel(contenido);
    } else if (posicion === 1 || posicion === 2 || posicion === 3 || posicion === 44 || posicion === 45) {
        celda = validarTexto_excelAlfanumerico_excel(contenido);
    } else if (posicion === 5 || posicion === 9 || posicion === 12 || posicion === 14 || posicion === 16 || posicion === 17 || posicion === 18 || posicion === 49 || posicion === 50 || posicion === 53 || posicion === 57 || posicion === 58) {
        celda = validarTexto_excel(contenido);
    } else if (posicion === 10 || posicion === 11 || posicion === 13) {
        celda = formatoNumero_excel(contenido);
    } else if (posicion === 8 || posicion === 15 || posicion === 19 || posicion === 20 || posicion === 21 || posicion === 22 || posicion === 25 || posicion === 26 || posicion === 27 || posicion === 28 || posicion === 33 || posicion === 36 || posicion === 37 || posicion === 38 || posicion === 39 || posicion === 42 || posicion === 43 || posicion === 46 || posicion === 47 || posicion === 51 || posicion === 52 || posicion === 54 || posicion === 55) {
        celda = formatoFecha_excel(contenido);
    } else if (posicion === 40) {
        celda = formatoModalidad_excel(contenido);
    } else if (posicion === 41) {
        celda = formatoResultadoModulacion_excel(contenido);
    } else if (posicion === 48) {
        celda = formatoEstatusOperacion_excel(contenido);
    } else if (posicion === 4 || posicion === 24 || posicion === 29 || posicion === 30 || posicion === 31 || posicion === 32) {
        celda = formatoRequiere_excel(contenido);
    } else if (posicion === 56) {
        celda = formatoHora_excel(contenido);
    }

    if (celda === false) {
        logErrores = " Error en Fila:(" + fila + ")  Columna:(" + (posicion+1) + "),";
    }

    return logErrores;
}
 
function relacion_columnas_excel_generico(posicion, contenido, fila) {
    
    let logErrores = "";
    console.log("!Excel Generico¡");
    
    if (posicion === 0 || posicion === 7 || posicion === 34 || posicion === 35) {
        celda = validarNumero_excel(contenido);
    } else if (posicion === 1 || posicion === 2 || posicion === 3 || posicion === 6 || posicion === 12 || posicion === 14 || posicion === 44 || posicion === 45 || posicion === 51 || posicion === 5 || posicion === 9 ||  posicion === 16 || posicion === 17 || posicion === 18 || posicion === 49 || posicion === 50) {
        celda = validarTexto_excelAlfanumerico_excel(contenido);
    } else if (posicion === 10 || posicion === 11 || posicion === 13) {
        celda = formatoNumero_excel(contenido);
    } else if (posicion === 8 || posicion === 15 || posicion === 19 || posicion === 20 || posicion === 21 || posicion === 22 || posicion === 25 || posicion === 26 || posicion === 27 || posicion === 28 || posicion === 33 || posicion === 36 || posicion === 37 || posicion === 38 || posicion === 39 || posicion === 42 || posicion === 43 || posicion === 46 || posicion === 47) {
        celda = formatoFecha_excel(contenido);
    } else if (posicion === 40) {
        celda = formatoModalidad_excel(contenido);
    } else if (posicion === 41) {
        celda = formatoResultadoModulacion_excel(contenido);
    } else if (posicion === 48) {
        celda = formatoEstatusOperacion_excel(contenido);
    } else if (posicion === 4 || posicion === 24 || posicion === 29 || posicion === 30 || posicion === 31 || posicion === 32) {
        celda = formatoRequiere_excel(contenido);
    }

    if (celda === false) {
        logErrores = " Error en Fila:(" + fila + ")  Columna:(" + (posicion+1) + "),"; 
    }

    return logErrores;
}

function relacion_columnas_excel_logix(posicion, contenido, fila) {
    
    let logErrores = "";
    console.log("!Excel Logix¡");
    
    if (posicion === 0 || posicion === 6 || posicion === 7 || posicion === 35) {
        celda = validarNumero_excel(contenido);
    } else if (posicion === 1 || posicion === 2 || posicion === 3 || posicion === 9 || posicion === 34 || posicion === 44 || posicion === 45 || posicion === 53 || posicion === 59 || posicion === 61) {
        celda = validarTexto_excelAlfanumerico_excel(contenido);
    } else if (posicion === 5 || posicion === 12 || posicion === 14 || posicion === 16 || posicion === 17 || posicion === 18 || posicion === 49 || posicion === 50) {
        celda = validarTexto_excel(contenido);
    } else if (posicion === 10 || posicion === 11 || posicion === 13) {
        celda = formatoNumero_excel(contenido);
    } else if (posicion === 8 || posicion === 15 || posicion === 19 || posicion === 20 || posicion === 21 || posicion === 22 || posicion === 25 || posicion === 26 || posicion === 27 || posicion === 28 || posicion === 33 || posicion === 36 || posicion === 37 || posicion === 38 || posicion === 39 || posicion === 42 || posicion === 43 || posicion === 46 || posicion === 47 || posicion === 51 || posicion === 52 || posicion === 54 || posicion === 55 || posicion === 56 || posicion === 57 || posicion === 58 || posicion === 60) {
        celda = formatoFecha_excel(contenido);
    } else if (posicion === 40) {
        celda = formatoModalidad_excel(contenido);
    } else if (posicion === 41) {
        celda = formatoResultadoModulacion_excel(contenido);
    } else if (posicion === 48) {
        celda = formatoEstatusOperacion_excel(contenido);
    } else if (posicion === 4 || posicion === 24 || posicion === 29 || posicion === 30 || posicion === 31 || posicion === 32) {
        celda = formatoRequiere_excel(contenido);
    }

    if (celda === false) {
        logErrores = " Error en Fila:(" + fila + ")  Columna:(" + (posicion+1) + "),";
    }

    return logErrores;
}

function relacion_columnas_excel_cusa(posicion, contenido, fila) {
    
    let logErrores = "";
    console.log("!Excel Cusa¡");
    
    if (posicion === 0 || posicion === 6 || posicion === 7 || posicion === 34 || posicion === 35 || posicion === 44 || posicion === 45) {
        celda = validarNumero_excel(contenido);
    } else if (posicion === 1 || posicion === 2 || posicion === 3 || posicion === 51) {
        celda = validarTexto_excelAlfanumerico_excel(contenido);
    } else if (posicion === 5 || posicion === 9 || posicion === 12 || posicion === 14 || posicion === 16 || posicion === 17 || posicion === 18 || posicion === 49 || posicion === 50) {
        celda = validarTexto_excel(contenido);
    } else if (posicion === 10 || posicion === 11 || posicion === 13) {
        celda = formatoNumero_excel(contenido);
    } else if (posicion === 8 || posicion === 15 || posicion === 19 || posicion === 20 || posicion === 21 || posicion === 22 || posicion === 25 || posicion === 26 || posicion === 27 || posicion === 28 || posicion === 33 || posicion === 36 || posicion === 37 || posicion === 38 || posicion === 39 || posicion === 42 || posicion === 43 || posicion === 46 || posicion === 47) {
        celda = formatoFecha_excel(contenido);
    } else if (posicion === 40) {
        celda = formatoModalidad_excel(contenido);
    } else if (posicion === 41) {
        celda = formatoResultadoModulacion_excel(contenido);
    } else if (posicion === 48) {
        celda = formatoEstatusOperacion_excel(contenido);
    } else if (posicion === 4 || posicion === 24 || posicion === 29 || posicion === 30 || posicion === 31 || posicion === 32) {
        celda = formatoRequiere_excel(contenido);
    }

    if (celda === false) {
        logErrores = " Error en Fila:(" + fila + ")  Columna:(" + (posicion+1) + "),";
    }

    return logErrores;
}

//Expresiones Regulares:
function validarNumero_excel(contenido) {

    let resultado = false;

    if (/^[0-9]+([.])?([0-9]+)?$/.test(contenido) || contenido.replace(/\s/g, "") === "") {
        resultado = true;
    }

    return resultado;
}

function validarTexto_excelAlfanumerico_excel(contenido) {

   let resultado = true;
    
  /*  if (/^[\w\s.ñÑ!@#$%^&*()-]+$/g.test(contenido) || contenido.replace(/\s/g, "") === "") {
        resultado = true;
    }
*/
    return resultado;
}

function validarTexto_excel(contenido) {

    let resultado = false;

    if (/^[a-zA-ZñÑ\s.:]+$/.test(contenido) || contenido.replace(/\s/g, "") === "") {
        resultado = true;
    }

    return resultado;
}

function formatoNumero_excel(contenido) {

    let resultado = false;

    // Limitar la longitud total a 16 caracteres
    var numeroFiltrado = contenido.slice(0, 17);

    // Aplicar el formato XX XX XXXX XXXXXXXX
    if (numeroFiltrado.length > 1) {
        numeroFiltrado = numeroFiltrado.replace(/(\d{2})(\d{2})(\d{4})(\d{6})/, '$1 $2 $3 $4');
        resultado = true;
    }

    return resultado;
}

function formatoFecha_excel(info) {

    let contenido = validate_fecha(info);
    let resultado = false;

    var fechaFiltrada = contenido.slice(0, 8);

    // Aplicar el formato MM/DD/YYYY
    if (fechaFiltrada.length > 1) {
        fechaFiltrada = fechaFiltrada.replace(/^\d{2}\/\d{2}\/\d{4}$/, '$1/$2/$3');
        resultado = true;
    }

    return resultado;
}

function formatoModalidad_excel(contenido) {

    let resultado = false;
    let valor = contenido.replace(/^\s+|\s+$/g, '');
    
    if (valor.toUpperCase() === "CAMION" || valor.toUpperCase() === "TREN" || valor.replace(/\s/g, "") === "") {
        resultado = true;
    }

    return resultado;
}

function formatoResultadoModulacion_excel(contenido) {

    let resultado = false;
    let valor = contenido.replace(/^\s+|\s+$/g, '');
    
    if (valor.toUpperCase() === "VERDE" || valor.toUpperCase() === "ROJO" || valor.replace(/\s/g, "") === "") {
        resultado = true;
    }

    return resultado;
}

function formatoEstatusOperacion_excel(contenido) {

    let resultado = true;

    //Lista de Estatus:

    return resultado;
}

function formatoRequiere_excel(contenido) {

    let resultado = false;
    let valor = contenido.replace(/^\s+|\s+$/g, '');
    
    if (valor.toUpperCase() === "SI" || valor.toUpperCase() === "NO" || valor.replace(/\s/g, "") === "") {
        resultado = true;
    }

    return resultado;
}

function formatoHora_excel(contenido) {

    /*let resultado = false;
     
     var time = contenido.replace(/p. m./, 'p.m.');
     
     if (/^(0[1-9]|1[0-2]):[0-5]\d:[0-5]\d (a\.m\.|p\.m\.)$/.test(time)) {
     resultado = true;
     }*/
    resultado = true;

    return resultado;
}

function validate_fecha(example){ 

    let msg = "";

    try{

        //Convertir string a date, para su validacion.
        let partesFecha = example.split("/");
        let fecha = new Date(partesFecha[2], partesFecha[0]-1, partesFecha[1]);

        if(typeof fecha.getTime === 'function' && !isNaN(fecha) || typeof parseInt(example) === 'number'&& example.length === 5){

            if(typeof fecha.getTime === 'function' && !isNaN(fecha)){
                msg = example;
            }else {  
                var fecha2 = numeroAFecha(parseFloat(example), true);
                msg = formatoDosDigitos(fecha2.getMonth()+1) + '/' + formatoDosDigitos(fecha2.getDate()) + '/' + fecha2.getFullYear();
            }
        }else{
           msg = "00/00/0000";  
        } 

    }catch (exception) {
          msg = "00/00/0000";
    }

   return msg;
}

function numeroAFecha(numeroDeDias, esExcel = false) {
    var diasDesde1900 = esExcel ? 25567 + 1 : 25567;

    // 86400 es el número de segundos en un día, luego multiplicamos por 1000 para obtener milisegundos.
    return new Date((numeroDeDias - diasDesde1900) * 86400 * 1000);
}

function formatoDosDigitos(numero) {
    return numero < 10 ? '0' + numero : numero;
}

function ocultarLoader() {
    var loaderWrapper = document.getElementById("loader-wrapper");
    loaderWrapper.style.display = "none";
}

function logExcel() {
    mostrarOcultarDiv('divAMostrarOcultar', true);
    consultarCustoms(selected_referenciaAA, 
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

function mostrarOcultarDiv(id, mostrar) {
    var elemento = document.getElementById(id);
    if (mostrar) {
        elemento.style.display = 'block';
    } else {
        elemento.style.display = 'none';
    }
}



