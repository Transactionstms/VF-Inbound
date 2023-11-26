/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

  let contCeldas = 50;
  let addCell = 66;
    /*Creación y Descarga de Excel*/
    $(document).ready(function () {     
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
        }).fail(function (data) {
            document.getElementById('divResultado').innerHTML = data;
            $("#texto").text('Se describen los errores');
            $("#alert").removeClass().addClass("alert alert-danger");
        });
    });
    
    function leerArchivo() {
        
        jsShowWindowLoad('Cargando Información');
        
        var contCustoms = document.getElementById('numCustoms').value;
        var fileInput = document.getElementById('fileInput');
        
        if (fileInput.files.length > 0) {
            var archivo = fileInput.files[0];
            var lector = new FileReader();

            lector.onload = function (e) {
                var contenido = e.target.result;
                var workbook = XLSX.read(contenido, {type: 'array'});

                var primeraHoja = workbook.SheetNames[0];
                var hoja = workbook.Sheets[primeraHoja];

                var datosJSON = XLSX.utils.sheet_to_json(hoja, {header: 1});

                // Llama a la función para agregar los datos a la tabla existente
                agregarOActualizarTablaAdmin(datosJSON,contCustoms);
            };

            lector.readAsArrayBuffer(archivo);
            //swal("", "Información Registrada", "success");
            
        } else {
            swal("", "Por favor, selecciona un archivo Excel", "info");
        }
        
         document.getElementById("fileInput").value = "";
         $("#WindowLoad").remove();
         //alertclose();
    }
        
    function agregarOActualizarTablaAdmin(datos,contadorExcel) {
        var tabla = document.getElementById('main-table');
        var tbody = tabla.getElementsByTagName('tbody')[0];

        // Itera sobre los datos del archivo Excel
        for (var i = 2; i < datos.length; i++) {
            // Busca una fila existente con el mismo identificador
            var identificador = datos[i][0];
            var filaExistente = buscarFilaPorIdentificador(tbody, identificador);

            if (filaExistente) {
                // Si se encuentra una fila existente, actualiza sus celdas con los nuevos datos
                for (var j = 0; j < datos[i].length; j++) {
                    filaExistente.cells[j].innerHTML = datos[i][j];
                }
            } else {
                // Si no se encuentra una fila existente, agrega una nueva fila
                var fila = tbody.insertRow(-1); // El índice -1 inserta la fila al final de tbody
                var estatus = fila.insertCell(0);
                var referencia = fila.insertCell(1);
                var evento = fila.insertCell(2);
                var responsable = fila.insertCell(3);
                var final_destination = fila.insertCell(4);
                var brand_division = fila.insertCell(5);
                var division = fila.insertCell(6);
                var shipment_id = fila.insertCell(7);
                var container_id = fila.insertCell(8);
                var blAwb = fila.insertCell(9);
                var loadtype = fila.insertCell(10);
                var quantity = fila.insertCell(11);
                var pod = fila.insertCell(12);
                var DepartureFromPol = fila.insertCell(13);
                var etaPortOfDischarge = fila.insertCell(14);
                var etaDc = fila.insertCell(15);
                var inboundNotification = fila.insertCell(16);
                var pol = fila.insertCell(17);
                var aa = fila.insertCell(18);
                var fecha_mes_venta = fila.insertCell(19);
                var prioridad = fila.insertCell(20);
                var pais_origen = fila.insertCell(21);
                var size_container = fila.insertCell(22);
                var valor_usd = fila.insertCell(23);
                var eta_port_discharge = fila.insertCell(24);
                var agente_aduanal = fila.insertCell(25);
                var pedimento_a1 = fila.insertCell(26);
                var pedimento_r1_1er = fila.insertCell(27);
                var motivo_rectificacion_1er = fila.insertCell(28);
                var pedimento_r1_2do = fila.insertCell(29);
                var motivo_rectificacion_2do = fila.insertCell(30);
                var fecha_recepcion_doc = fila.insertCell(31);
                var recinto = fila.insertCell(32);
                var naviera = fila.insertCell(33);
                var buque = fila.insertCell(34);
                var fecha_revalidacion = fila.insertCell(35);
                var fecha_previo_origen = fila.insertCell(36);
                var fecha_previo_destino = fila.insertCell(37);
                var fecha_resultado_previo = fila.insertCell(38);
                var proforma_final = fila.insertCell(39);
                var permiso = fila.insertCell(40);
                var fecha_envio = fila.insertCell(41);
                var fecha_recepcion_perm = fila.insertCell(42);
                var fecha_activacion_perm = fila.insertCell(43);
                var fecha_permisos_aut = fila.insertCell(44);
                var co_pref_arancelaria = fila.insertCell(45);
                var aplic_pref_arancelaria = fila.insertCell(46);
                var req_uva = fila.insertCell(47);
                var req_ca = fila.insertCell(48);
                var fecha_recepcion_ca = fila.insertCell(49);
                var num_constancia_ca = fila.insertCell(50);
                var monto_ca = fila.insertCell(51);
                var fecha_doc_completos = fila.insertCell(52);
                var fecha_pago_pedimento = fila.insertCell(53);
                var fecha_solicitud_transporte = fila.insertCell(54);
                var fecha_modulacion = fila.insertCell(55);
                var modalidad = fila.insertCell(56);
                var resultado_modulacion = fila.insertCell(57);
                var fecha_reconocimiento = fila.insertCell(58);
                var fecha_liberacion = fila.insertCell(59);
                var sello_origen = fila.insertCell(60);
                var sello_final = fila.insertCell(61);
                var fecha_retencion_aut = fila.insertCell(62);
                var fecha_liberacion_aut = fila.insertCell(63);
                var estatus_operacion = fila.insertCell(64);
                var motivo_atraso = fila.insertCell(65);
                var observaciones = fila.insertCell(66);
                var llegada_a_nova = fila.insertCell(67); 
                var llegada_a_globe_trade_sd = fila.insertCell(68);
                var archivo_m = fila.insertCell(69);
                var fecha_archivo_m = fila.insertCell(70);
                var fecha_solicit_manip = fila.insertCell(71);
                var fecha_vencim_manip = fila.insertCell(72);
                var fecha_confirm_clave_pedim = fila.insertCell(73);
                var fecha_recep_increment = fila.insertCell(74);
                var t_e = fila.insertCell(75);
                var fecha_vencim_inbound = fila.insertCell(76); 
                var no_bultos = fila.insertCell(77);  
                var peso_kg = fila.insertCell(78);
                var transferencia = fila.insertCell(79);
                var fecha_inicio_etiquetado = fila.insertCell(80);
                var fecha_termino_etiquetado = fila.insertCell(81);
                var hora_termino_etiquetado = fila.insertCell(82);
                var proveedor = fila.insertCell(83);
                var proveedor_carga = fila.insertCell(84);
                var fy = fila.insertCell(85);
                var btn = fila.insertCell(86); 
       
                /*Celdas Fijas*/
                estatus.innerHTML = "<img id=\"imgSemaforo"+contadorExcel+"\" src=\"../img/circle-gray.webp\" width=100%/>";
                responsable.innerHTML = "responsable<input type=hidden onkeyup=this.value = this.value.toUpperCase() class=\"form-control\" id=responsable autocomplete=off>";
                final_destination.innerHTML = "final_destination<input type=hidden onkeyup=this.value = this.value.toUpperCase() class=\"form-control\" id=final_destination autocomplete=off>";
                brand_division.innerHTML = "brand_division<input type=hidden onkeyup=this.value = this.value.toUpperCase() class=\"form-control\" id=brand_division autocomplete=off>";
                division.innerHTML = "division<input type=hidden onkeyup=this.value = this.value.toUpperCase() class=\"form-control\" id=division autocomplete=off>";
                blAwb.innerHTML = "blAwb<input type=hidden onkeyup=this.value = this.value.toUpperCase() class=\"form-control\" id=division autocomplete=off>";
                loadtype.innerHTML = "loadtype<input type=hidden onkeyup=this.value = this.value.toUpperCase() class=\"form-control\" id=division autocomplete=off>";
                quantity.innerHTML = "quantity<input type=hidden onkeyup=this.value = this.value.toUpperCase() class=\"form-control\" id=division autocomplete=off>";
                pod.innerHTML = "pod<input type=hidden onkeyup=this.value = this.value.toUpperCase() class=\"form-control\" id=division autocomplete=off>";
                DepartureFromPol.innerHTML = "DepartureFromPol<input type=hidden onkeyup=this.value = this.value.toUpperCase() class=\"form-control\" id=division autocomplete=off>";
                etaPortOfDischarge.innerHTML = "etaPortOfDischarge<input type=hidden onkeyup=this.value = this.value.toUpperCase() class=\"form-control\" id=division autocomplete=off>";
                etaDc.innerHTML = "etaDc<input type=hidden onkeyup=this.value = this.value.toUpperCase() class=\"form-control\" id=division autocomplete=off>";
                inboundNotification.innerHTML = "inboundNotification<input type=hidden onkeyup=this.value = this.value.toUpperCase() class=\"form-control\" id=division autocomplete=off>";
                pol.innerHTML = "pol<input type=hidden onkeyup=this.value = this.value.toUpperCase() class=\"form-control\" id=division autocomplete=off>";
                aa.innerHTML = "aa<input type=hidden onkeyup=this.value = this.value.toUpperCase() class=\"form-control\" id=division autocomplete=off>";
                fecha_mes_venta.innerHTML = "fecha_mes_venta<input type=hidden onkeyup=this.value = this.value.toUpperCase() class=\"form-control\" id=division autocomplete=off>";
                        
                // Agrega celdas a la nueva fila
                for (var k = 0; k < datos[i].length; k++) {

                    contadorExcel++;
                    var trId = tabla.getElementsByTagName('tr').innerHTML = "tr"+contadorExcel;
                    console.log("<tr>: "+trId);  
                                    
                    if(k===0){ 
                        evento.innerHTML = datos[i][k]+"<input type=hidden id=evento["+contadorExcel+"] name=evento["+contadorExcel+"] value=\""+datos[i][k]+"\">\n\
                                                        <div id=\"popup"+contadorExcel+"\" style=\"display: none;\"><div id=\"mSgError"+contadorExcel+"\"></div></div>";
                    }else if(k===1){
                        shipment_id.innerHTML = datos[i][k]+"<input type=hidden id=shipmentId["+contadorExcel+"] name=shipmentId["+contadorExcel+"] value=\""+datos[i][k]+"\">";
                    }else if(k===2){
                        container_id.innerHTML = datos[i][k]+"<input type=hidden id=containerId["+contadorExcel+"] name=containerId["+contadorExcel+"] value=\""+datos[i][k]+"\">";
                    }else if(k===3){
                        referencia.innerHTML = "<input type=text onkeyup=this.value = this.value.toUpperCase() class=\"form-control\" id=referenciaAA["+contadorExcel+"] name=referenciaAA["+contadorExcel+"] value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===4){    
                        prioridad.innerHTML = datos[i][k]+"<input type=hidden id=prioridad["+contadorExcel+"] name=prioridad["+contadorExcel+"] value=\""+datos[i][k]+"\">";
                    }else if(k===5){     
                        pais_origen.innerHTML = "<input class=\"form-control\" id=pais_origen["+contadorExcel+"] name=pais_origen["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===6){
                        size_container.innerHTML = "<input class=\"form-control\" id=size_container["+contadorExcel+"] name=size_container["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===7){
                        valor_usd.innerHTML = "<input class=\"form-control\" id=valor_usd["+contadorExcel+"] name=valor_usd["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===8){
                        eta_port_discharge.innerHTML = "<input class=\"form-control datepicker\" id=eta_port_discharge["+contadorExcel+"] name=eta_port_discharge["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" onchange=\"pedimento(this.value,"+contadorExcel+")\" autocomplete=off>";
                                                    // + "<a class=\"text-lg text-blue\" onclick=\"historicoSemaforo('V000117961')\"><i class=\"fa fa-folder-open\"></i></a>";
                    }else if(k===9){
                        agente_aduanal.innerHTML = "<input class=\"form-control\" id=agente_aduanal["+contadorExcel+"] name=agente_aduanal["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===10){
                        pedimento_a1.innerHTML = "<input class=\"form-control\" id=pedimento_a1["+contadorExcel+"] name=pedimento_a1["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===11){
                        pedimento_r1_1er.innerHTML = "<input class=\"form-control\" id=pedimento_r1_1er["+contadorExcel+"] name=pedimento_r1_1er["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off onblur=\"cleanPedimento_r1_1er(this.value,"+contadorExcel+")\">";
                    }else if(k===12){
                        motivo_rectificacion_1er.innerHTML = "<input class=\"form-control\" id=motivo_rectificacion_1er["+contadorExcel+"] name=motivo_rectificacion_1er["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===13){
                        pedimento_r1_2do.innerHTML = "<input class=\"form-control\" id=pedimento_r1_2do["+contadorExcel+"] name=pedimento_r1_2do["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off onblur=\"cleanPedimento_r1_2do(this.value,"+contadorExcel+")\">";
                    }else if(k===14){
                        motivo_rectificacion_2do.innerHTML = "<input class=\"form-control\" id=motivo_rectificacion_2do["+contadorExcel+"] name=motivo_rectificacion_2do["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===15){
                        fecha_recepcion_doc.innerHTML = "<input class=\"form-control datepicker\" id=fecha_recepcion_doc["+contadorExcel+"] name=fecha_recepcion_doc["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===16){
                        recinto.innerHTML = "<input class=\"form-control\" id=recinto["+contadorExcel+"] name=recinto["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===17){
                        naviera.innerHTML = "<input class=\"form-control\" id=naviera["+contadorExcel+"] name=naviera["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===18){
                        buque.innerHTML = "<input class=\"form-control\" id=buque["+contadorExcel+"] name=buque["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===19){
                        fecha_revalidacion.innerHTML = "<input class=\"form-control datepicker\" id=fecha_revalidacion["+contadorExcel+"] name=fecha_revalidacion["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===20){
                        fecha_previo_origen.innerHTML = "<input class=\"form-control datepicker\" id=fecha_previo_origen["+contadorExcel+"] name=fecha_previo_origen["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===21){
                        fecha_previo_destino.innerHTML = "<input class=\"form-control datepicker\" id=fecha_previo_destino["+contadorExcel+"] name=fecha_previo_destino["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===22){
                        fecha_resultado_previo.innerHTML = "<input class=\"form-control datepicker\" id=fecha_resultado_previo["+contadorExcel+"] name=fecha_resultado_previo["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===23){
                        proforma_final.innerHTML = "<input class=\"form-control datepicker\" id=proforma_final["+contadorExcel+"] name=proforma_final["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===24){
                        permiso.innerHTML = "<select class=\"form-control\" id=permiso["+contadorExcel+"] name=permiso["+contadorExcel+"] value=\""+datos[i][k]+"\" onchange=\"cleanPermiso(this.value,"+contadorExcel+")\">"
                                          + "   <option value=\""+datos[i][k]+"\">"+datos[i][k]+"</option>"
                                          + "   <option value=Si>Si</option>"
                                          + "   <option value=No>No</option>" 
                                          + "</select>"; 
                    }else if(k===25){
                        fecha_envio.innerHTML = "<input class=\"form-control datepicker\" id=fecha_envio["+contadorExcel+"] name=fecha_envio["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===26){
                        fecha_recepcion_perm.innerHTML = "<input class=\"form-control datepicker\" id=fecha_recepcion_perm["+contadorExcel+"] name=fecha_recepcion_perm["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===27){
                        fecha_activacion_perm.innerHTML = "<input class=\"form-control datepicker\" id=fecha_activacion_perm["+contadorExcel+"] name=fecha_activacion_perm["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===28){
                        fecha_permisos_aut.innerHTML = "<input class=\"form-control datepicker\" id=fecha_permisos_aut["+contadorExcel+"] name=fecha_permisos_aut["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===29){
                        co_pref_arancelaria.innerHTML = "<select class=\"form-control\" id=co_pref_arancelaria["+contadorExcel+"] name=co_pref_arancelaria["+contadorExcel+"] value=\""+datos[i][k]+"\">"  
                                                      + "   <option value=\""+datos[i][k]+"\">"+datos[i][k]+"</option>"
                                                      + "   <option value=Si>Si</option>" 
                                                      + "   <option value=No>No</option>"
                                                      + "</select>"; 
                    }else if(k===30){
                        aplic_pref_arancelaria.innerHTML = "<select class=\"form-control\" id=aplic_pref_arancelaria["+contadorExcel+"] name=aplic_pref_arancelaria["+contadorExcel+"] value=\""+datos[i][k]+"\">"
                                                         + "   <option value=\""+datos[i][k]+"\">"+datos[i][k]+"</option>"
                                                         + "   <option value=Si>Si</option>"
                                                         + "   <option value=No>No</option>" 
                                                         + "</select>"; 
                    }else if(k===31){
                        req_uva.innerHTML = "<select class=\"form-control\" id=req_uva["+contadorExcel+"] name=req_uva["+contadorExcel+"] value=\""+datos[i][k]+"\">"
                                          + "   <option value=\""+datos[i][k]+"\">"+datos[i][k]+"</option>"
                                          + "   <option value=Si>Si</option>"
                                          + "   <option value=No>No</option>" 
                                          + "</select>"; 
                    }else if(k===32){
                        req_ca.innerHTML = "<select class=\"form-control\" id=req_ca["+contadorExcel+"] name=req_ca["+contadorExcel+"] value=\""+datos[i][k]+"\" onchange=\"cleanRequerimientoCA(this.value,"+contadorExcel+")\">" 
                                         + "   <option value=\""+datos[i][k]+"\">"+datos[i][k]+"</option>"
                                         + "   <option value=Si>Si</option>"
                                         + "   <option value=No>No</option>" 
                                         + "</select>"; 
                    }else if(k===33){
                        fecha_recepcion_ca.innerHTML = "<input class=\"form-control datepicker\" id=fecha_recepcion_ca["+contadorExcel+"] name=fecha_recepcion_ca["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off disabled>";
                    }else if(k===34){
                        num_constancia_ca.innerHTML = "<input class=\"form-control\" id=num_constancia_ca["+contadorExcel+"] name=num_constancia_ca["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===35){
                        monto_ca.innerHTML = "<input class=\"form-control\" id=monto_ca["+contadorExcel+"] name=monto_ca["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===36){
                        fecha_doc_completos.innerHTML = "<input class=\"form-control datepicker\" id=fecha_doc_completos["+contadorExcel+"] name=fecha_doc_completos["+contadorExcel+"] value=\""+datos[i][k]+"\" autocomplete=off type=text>";
                    }else if(k===37){
                        fecha_pago_pedimento.innerHTML = "<input class=\"form-control datepicker\" id=fecha_pago_pedimento["+contadorExcel+"] name=fecha_pago_pedimento["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" onchange=\"modulacion("+contadorExcel+")\">";
                    }else if(k===38){
                        fecha_solicitud_transporte.innerHTML = "<input class=\"form-control datepicker\" id=fecha_solicitud_transporte["+contadorExcel+"] name=fecha_solicitud_transporte["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===39){
                        fecha_modulacion.innerHTML = "<input class=\"form-control datepicker\" id=fecha_modulacion["+contadorExcel+"] name=fecha_modulacion["+contadorExcel+"] type=text value=\""+datos[i][k]+"\">";
                    }else if(k===40){
                        modalidad.innerHTML = "<select class=\"form-control\" id=modalidad["+contadorExcel+"] name=modalidad["+contadorExcel+"] value=\""+datos[i][k]+"\">"
                                            + "   <option value=\""+datos[i][k]+"\">"+datos[i][k]+"</option>"
                                            + "   <option value=Camión>Camión</option>"
                                            + "   <option value=Tren>Tren</option> "
                                            + "</select>"; 
                    }else if(k===41){
                        resultado_modulacion.innerHTML = "<select class=\"form-control\" id=resultado_modulacion["+contadorExcel+"] name=resultado_modulacion["+contadorExcel+"] value=\""+datos[i][k]+"\" onchange=\"cleanResultadoModulacion(this.value,"+contadorExcel+","+idAgenteAduanal+")\">"
                                                       + "   <option value=\""+datos[i][k]+"\">"+datos[i][k]+"</option>"
                                                       + "   <option value=Verde>Verde</option> "
                                                       + "   <option value=Rojo>Rojo</option>"
                                                       + "</select>"; 
                    }else if(k===42){
                        fecha_reconocimiento.innerHTML = "<input class=\"form-control datepicker\" id=fecha_reconocimiento["+contadorExcel+"] name=fecha_reconocimiento["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===43){
                        fecha_liberacion.innerHTML = "<input class=\"form-control datepicker\" id=fecha_liberacion["+contadorExcel+"] name=fecha_liberacion["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===44){
                        sello_origen.innerHTML = "<input class=\"form-control\" id=sello_origen["+contadorExcel+"] name=sello_origen["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===45){
                        sello_final.innerHTML = "<input class=\"form-control\" id=sello_final["+contadorExcel+"] name=sello_final["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===46){
                        fecha_retencion_aut.innerHTML = "<input class=\"form-control datepicker\" id=fecha_retencion_aut["+contadorExcel+"] name=fecha_retencion_aut["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===47){
                        fecha_liberacion_aut.innerHTML = "<input class=\"form-control datepicker\" id=fecha_liberacion_aut["+contadorExcel+"] name=fecha_liberacion_aut["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===48){
                        estatus_operacion.innerHTML = "<select class=\"form-control\" id=estatus_operacion["+contadorExcel+"] name=estatus_operacion["+contadorExcel+"] value=\""+datos[i][k]+"\" onmouseover=\"formComplet("+idAgenteAduanal+","+contadorExcel+")\">"
                                                    + "  <option value=\""+datos[i][k]+"\">"+datos[i][k]+"</option>"
                                                    + "  <option value=1>EN ESPERA DE ESTATUS</option>"
                                                    + "	 <option value=2>EN TRANSITO - PENDIENTE REVALIDACION</option>"
                                                    + "	 <option value=3>EN TRANSITO - REVALIDADO</option>"
                                                    + "	 <option value=4>EN PROCESO DE ARRIBO</option>"
                                                    + "	 <option value=5>EN PROCESO DE RECOLECCION</option>"
                                                    + "	 <option value=6>ARRIBADO</option>"
                                                    + "	 <option value=7>ARRIBADO - PENDIENTE REVALIDACION</option>"
                                                    + "	 <option value=8>REVALIDADO</option>"
                                                    + "	 <option value=9>EN ESPERA DE PREVIO</option>"
                                                    + "	 <option value=10>RETENIDO POR LA AUTORIDAD</option>"
                                                    + "	 <option value=11>EN PREVIO</option>"
                                                    + "	 <option value=12>EN GLOSA</option>"
                                                    + "	 <option value=13>EN ESPERA DE DOCUMENTOS</option>"
                                                    + "	 <option value=14>EN PROCESO DE PAGO DE PEDIMENTO</option>"
                                                    + "	 <option value=15>PEDIMENTO PAGADO</option>"
                                                    + "	 <option value=16>EN ESPERA DE INSTRUCCIONES PARA DESPACHO</option>"
                                                    + "	 <option value=17>EN PROGRAMACION DE DESPACHO</option>"
                                                    + "	 <option value=18>EN DESPACHO</option>"
                                                    + "	 <option value=19 disabled>IMPORTADO</option>"
                                                    + "	 <option value=20>RETENIDO POR INCIDENCIA</option>"
                                                    + "	 <option value=21>EN TRANSITO</option>"
                                                    + "</select>";
                    }else if(k===49){
                        motivo_atraso.innerHTML = "<input class=\"form-control\" id=motivo_atraso["+contadorExcel+"] name=motivo_atraso["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===50){
                        observaciones.innerHTML = "<input class=\"form-control\" id=observaciones["+contadorExcel+"] name=observaciones["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===51){      
                        llegada_a_nova.innerHTML = "<input class=\"form-control datepicker\" id=llegada_a_nova["+contadorExcel+"] name=llegada_a_nova["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===52){
                        llegada_a_globe_trade_sd.innerHTML = "<input class=\"form-control datepicker\" id=llegada_a_globe_trade_sd["+contadorExcel+"] name=llegada_a_globe_trade_sd["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===53){
                        archivo_m.innerHTML = "<input class=\"form-control\" id=archivo_m["+contadorExcel+"] name=archivo_m["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===54){
                        fecha_archivo_m.innerHTML = "<input class=\"form-control datepicker\" id=fecha_archivo_m["+contadorExcel+"] name=fecha_archivo_m["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===55){
                        fecha_solicit_manip.innerHTML = "<input class=\"form-control datepicker\" id=fecha_solicit_manip["+contadorExcel+"] name=fecha_solicit_manip["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===56){
                        fecha_vencim_manip.innerHTML = "<input class=\"form-control datepicker\" id=fecha_vencim_manip["+contadorExcel+"] name=fecha_vencim_manip["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===57){
                        fecha_confirm_clave_pedim.innerHTML = "<input class=\"form-control datepicker\" id=fecha_confirm_clave_pedim["+contadorExcel+"] name=fecha_confirm_clave_pedim["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===58){
                        fecha_recep_increment.innerHTML = "<input class=\"form-control datepicker\" id=fecha_recep_increment["+contadorExcel+"] name=fecha_recep_increment["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===59){
                        t_e.innerHTML = "<input class=\"form-control\" id=t_e["+contadorExcel+"] name=t_e["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===60){  
                        fecha_vencim_inbound.innerHTML = "<input class=\"form-control datepicker\" id=fecha_vencim_inbound["+contadorExcel+"] name=fecha_vencim_inbound["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===61){
                        no_bultos.innerHTML = "<input class=\"form-control\" id=no_bultos["+contadorExcel+"] name=no_bultos["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===62){
                        peso_kg.innerHTML = "<input class=\"form-control\" id=peso_kg["+contadorExcel+"] name=peso_kg["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===63){
                        transferencia.innerHTML = "<select class=\"form-control\" id=transferencia["+contadorExcel+"] name=transferencia["+contadorExcel+"] value=\""+datos[i][k]+"\">"
                                                + " <option value=\""+datos[i][k]+"\">"+datos[i][k]+"</option>"
                                                + " <option value=Si>Si</option>" 
                                                + " <option value=No>No</option>" 
                                                + "</select>"; 
                    }else if(k===64){
                        fecha_inicio_etiquetado.innerHTML = "<input class=\"form-control datepicker\" id=fecha_inicio_etiquetado["+contadorExcel+"] name=fecha_inicio_etiquetado["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===65){
                        fecha_termino_etiquetado.innerHTML = "<input class=\"form-control datepicker\" id=fecha_termino_etiquetado["+contadorExcel+"] name=fecha_termino_etiquetado["+contadorExcel+"] type=text value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===66){
                        hora_termino_etiquetado.innerHTML = "<input class=\"form-control\" id=hora_termino_etiquetado["+contadorExcel+"] name=hora_termino_etiquetado["+contadorExcel+"] type=\"time\" value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===67){
                        proveedor.innerHTML = "<input class=\"form-control\" id=proveedor["+contadorExcel+"] name=proveedor["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===68){  
                        proveedor_carga.innerHTML = "<input class=\"form-control\" id=proveedor_carga["+contadorExcel+"] name=proveedor_carga["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                    }else if(k===69){        
                        fy.innerHTML = "<input class=\"form-control\" id=fy["+contadorExcel+"] name=fy["+contadorExcel+"] type=text onkeyup=this.value = this.value.toUpperCase() value=\""+datos[i][k]+"\" autocomplete=off>";
                        btn.innerHTML = "<a class=\"btn btn-primary text-uppercase\" id=btn["+contadorExcel+"] name=btn["+contadorExcel+"] onclick=\"AddLineCustoms("+contadorExcel+")\"><i class=\"fa fa-save\"></i></a>";   
                    }
                }
                    
                loadCss();
                loadJsPicker();
                contCeldas = 50;
                addCell = 66;
                
            }
        }
    }

    function buscarFilaPorIdentificador(tbody, identificador) {
        // Itera sobre las filas del tbody y busca una fila con el identificador dado
        for (var i = 0; i < tbody.rows.length; i++) {
            if (tbody.rows[i].cells[0].innerHTML === identificador) {
                return tbody.rows[i];
            }
        }
        return null; // Si no se encuentra ninguna fila con el identificador
    }
    
    function pedimento(dateEtaPortDischarge, i){
        
        /*Calendarios: etaPortDischarge/pagoPedimento/modulacion*/
        if(dateEtaPortDischarge !== ""){
            
            $('.datepicker-pedimento'+i).flatpickr({
                dateFormat: 'm/d/Y',
                minDate: dateEtaPortDischarge,
                maxDate: new Date().fp_incr(50)
            });
        
            $('.datepicker-modulacion'+i).flatpickr({
                dateFormat: 'm/d/Y'
            });
        }
    }            

    function modulacion(i){
        
        let fecha_pago_pedimento = document.getElementById("fecha_pago_pedimento["+i+"]").value;
        $('.datepicker-modulacion'+i).flatpickr({
            dateFormat: 'm/d/Y',
            minDate: fecha_pago_pedimento,
            maxDate: new Date().fp_incr(50)
        });
    }

    function loadCss() {
        // Create a new script element
        var script = document.createElement('script');

        // Set the source of the script to the same script you want to reload
        script.src = 'https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.css';

        // Append the script to the body
        document.body.appendChild(script);
    }
    
    function loadJsPicker(){
        $('.datepicker').flatpickr({
            dateFormat: 'm/d/Y',
            onClose: function (dateStr, instance) {
                instance.setDate(dateStr, true, 'm/d/Y');
            }
        });
    }
    
    function alertclose() {
        setTimeout(function () {
            swal.close();
            //$("#WindowLoad").remove();
        }, 1000);
    }
