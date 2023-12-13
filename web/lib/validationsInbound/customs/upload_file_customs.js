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

    $("#input-id").change(function () {
        input = document.getElementById('input-id');
        var file = input.files[0];
        var fr = new FileReader();
        documento;
        fr.onload = function (event) {
            documento = event.target.result.match(/,(.*)$/)[1];
        };
        fr.readAsDataURL(file);
    });

    $("#upload_file").click(async function () {
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
            console.log("Agente Aduanal Excel");
            await consultarCustoms(idAgenteAduanal, "0",
                                        selected_referenciaAA, 
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
    });


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
            resetInputfile();
        }).fail(function (data) {
            document.getElementById('divResultado').innerHTML = data;
            document.getElementById('idClouding').style.display = 'none';
            $("#texto").text('Se describen los errores');
            $("#alert").removeClass().addClass("alert alert-danger");
            resetInputfile();
        });
    });

    $("#close").click(function () {
        $("#alert").removeClass().addClass("hide");
    });
    function resetInputfile() {
        console.log('hola');
        //$('#input-id').fileinput('reset');
    }

});
