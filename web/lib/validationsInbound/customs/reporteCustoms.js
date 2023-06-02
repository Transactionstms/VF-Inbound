/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


    function FiltrerData(tipoFiltro) {
        
        jsShowWindowLoad('Cargando Información');
        
        let selectedValues = "0";
        var selected = "";

        if(tipoFiltro==="0"){
            selectedValues = "0";
        }else if(tipoFiltro==="1"){
            selectedValues = document.getElementById('col_referenciaAA');
        }else if(tipoFiltro==="2"){
            selectedValues = document.getElementById('col_evento');
        }else if(tipoFiltro==="3"){
            selectedValues = document.getElementById('col_responsable');
        }else if(tipoFiltro==="4"){
            selectedValues = document.getElementById('col_finalDestination');
        }else if(tipoFiltro==="5"){
            selectedValues = document.getElementById('col_brandDivision');
        }else if(tipoFiltro==="6"){
            selectedValues = document.getElementById('col_division');
        }else if(tipoFiltro==="7"){
            selectedValues = document.getElementById('col_shipmentId');
        }else if(tipoFiltro==="8"){
            selectedValues = document.getElementById('col_container');
        }else if(tipoFiltro==="9"){
            selectedValues = document.getElementById('col_blAwbPro');
        }else if(tipoFiltro==="10"){
            selectedValues = document.getElementById('col_loadType');
        }else if(tipoFiltro==="11"){
            selectedValues = document.getElementById('col_quantity');
        }else if(tipoFiltro==="12"){
            selectedValues = document.getElementById('col_pod');
        }else if(tipoFiltro==="13"){
            selectedValues = document.getElementById('col_estDepartFromPol');
        }else if(tipoFiltro==="14"){
            selectedValues = document.getElementById('col_etaRealPortOfDischarge');
        }else if(tipoFiltro==="15"){
            selectedValues = document.getElementById('col_estEtaDc');
        }else if(tipoFiltro==="16"){
            selectedValues = document.getElementById('col_inboundNotification');
        }else if(tipoFiltro==="17"){
            selectedValues = document.getElementById('col_pol');
        }else if(tipoFiltro==="18"){
            selectedValues = document.getElementById('col_aa');
        }else if(tipoFiltro==="19"){
            selectedValues = document.getElementById('col_fechaMesVenta');
        }else if(tipoFiltro==="20"){
            selectedValues = document.getElementById('col_prioridad');       
        }else if(tipoFiltro==="21"){
            selectedValues = document.getElementById('col_pais_origen');
        }else if(tipoFiltro==="22"){
            selectedValues = document.getElementById('col_size_container');
        }else if(tipoFiltro==="23"){
            selectedValues = document.getElementById('col_valor_usd');
        }else if(tipoFiltro==="24"){
            selectedValues = document.getElementById('col_eta_port_discharge');
        }else if(tipoFiltro==="25"){
            selectedValues = document.getElementById('col_agente_aduanal');
        }else if(tipoFiltro==="26"){
            selectedValues = document.getElementById('col_pedimento_a1');
        }else if(tipoFiltro==="27"){
            selectedValues = document.getElementById('col_pedimento_r1_1er');
        }else if(tipoFiltro==="28"){
            selectedValues = document.getElementById('col_motivo_rectificacion_1er');
        }else if(tipoFiltro==="29"){
            selectedValues = document.getElementById('col_pedimento_r1_2do');
        }else if(tipoFiltro==="30"){
            selectedValues = document.getElementById('col_motivo_rectificacion_2do');
        }else if(tipoFiltro==="31"){
            selectedValues = document.getElementById('col_fecha_recepcion_doc');
        }else if(tipoFiltro==="32"){
            selectedValues = document.getElementById('col_recinto');
        }else if(tipoFiltro==="33"){
            selectedValues = document.getElementById('col_naviera');
        }else if(tipoFiltro==="34"){
            selectedValues = document.getElementById('col_buque');
        }else if(tipoFiltro==="35"){
            selectedValues = document.getElementById('col_fecha_revalidacion');
        }else if(tipoFiltro==="36"){
            selectedValues = document.getElementById('col_fecha_previo_origen');
        }else if(tipoFiltro==="37"){
            selectedValues = document.getElementById('col_fecha_previo_destino');
        }else if(tipoFiltro==="38"){
            selectedValues = document.getElementById('col_fecha_resultado_previo');
        }else if(tipoFiltro==="39"){
            selectedValues = document.getElementById('col_proforma_final');
        }else if(tipoFiltro==="40"){
            selectedValues = document.getElementById('col_permiso');
        }else if(tipoFiltro==="41"){
            selectedValues = document.getElementById('col_fecha_envio');
        }else if(tipoFiltro==="42"){
            selectedValues = document.getElementById('col_fecha_recepcion_perm');
        }else if(tipoFiltro==="43"){
            selectedValues = document.getElementById('col_fecha_activacion_perm');
        }else if(tipoFiltro==="44"){
            selectedValues = document.getElementById('col_fecha_permisos_aut');
        }else if(tipoFiltro==="45"){
            selectedValues = document.getElementById('col_co_pref_arancelaria');
        }else if(tipoFiltro==="46"){
            selectedValues = document.getElementById('col_aplic_pref_arancelaria');
        }else if(tipoFiltro==="47"){
            selectedValues = document.getElementById('col_req_uva');
        }else if(tipoFiltro==="48"){
            selectedValues = document.getElementById('col_req_ca');
        }else if(tipoFiltro==="49"){
            selectedValues = document.getElementById('col_fecha_recepcion_ca');
        }else if(tipoFiltro==="50"){
            selectedValues = document.getElementById('col_num_constancia_ca');
        }else if(tipoFiltro==="51"){
            selectedValues = document.getElementById('col_monto_ca');
        }else if(tipoFiltro==="52"){
            selectedValues = document.getElementById('col_fecha_doc_completos');
        }else if(tipoFiltro==="53"){
            selectedValues = document.getElementById('col_fecha_pago_pedimento');
        }else if(tipoFiltro==="54"){
            selectedValues = document.getElementById('col_fecha_solicitud_transporte');
        }else if(tipoFiltro==="55"){
            selectedValues = document.getElementById('col_fecha_modulacion');
        }else if(tipoFiltro==="56"){
            selectedValues = document.getElementById('col_modalidad');
        }else if(tipoFiltro==="57"){
            selectedValues = document.getElementById('col_modalidad');
        }else if(tipoFiltro==="58"){
            selectedValues = document.getElementById('col_fecha_reconocimiento');
        }else if(tipoFiltro==="59"){
            selectedValues = document.getElementById('col_fecha_liberacion');
        }else if(tipoFiltro==="60"){
            selectedValues = document.getElementById('col_sello_origen');
        }else if(tipoFiltro==="61"){
            selectedValues = document.getElementById('col_sello_final');
        }else if(tipoFiltro==="62"){
            selectedValues = document.getElementById('col_fecha_retencion_aut');
        }else if(tipoFiltro==="63"){
            selectedValues = document.getElementById('col_fecha_liberacion_aut');
        }else if(tipoFiltro==="64"){
            selectedValues = document.getElementById('col_estatus_operacion');
        }else if(tipoFiltro==="65"){
            selectedValues = document.getElementById('col_motivo_atraso');
        }else if(tipoFiltro==="66"){
            selectedValues = document.getElementById('col_observaciones');
        }else if(tipoFiltro==="67"){
            selectedValues = document.getElementById('col_llegada_a_nova');
        }else if(tipoFiltro==="68"){
            selectedValues = document.getElementById('col_llegada_a_globe_trade_sd');
        }else if(tipoFiltro==="69"){
            selectedValues = document.getElementById('col_archivo_m');
        }else if(tipoFiltro==="70"){
            selectedValues = document.getElementById('col_fecha_archivo_m');
        }else if(tipoFiltro==="71"){
            selectedValues = document.getElementById('col_fecha_solicit_manip');
        }else if(tipoFiltro==="72"){
            selectedValues = document.getElementById('col_fecha_vencim_manip');
        }else if(tipoFiltro==="73"){
            selectedValues = document.getElementById('col_fecha_confirm_clave_pedim');
        }else if(tipoFiltro==="74"){
            selectedValues = document.getElementById('col_fecha_recep_increment');
        }else if(tipoFiltro==="75"){
            selectedValues = document.getElementById('col_t_e');
        }else if(tipoFiltro==="76"){
            selectedValues = document.getElementById('col_fecha_vencim_inbound');
        }else if(tipoFiltro==="77"){
            selectedValues = document.getElementById('col_no_bultos');
        }else if(tipoFiltro==="78"){
            selectedValues = document.getElementById('col_peso_kg');
        }else if(tipoFiltro==="79"){
            selectedValues = document.getElementById('col_transferencia');
        }else if(tipoFiltro==="80"){
            selectedValues = document.getElementById('col_fecha_inicio_etiquetado');
        }else if(tipoFiltro==="81"){
            selectedValues = document.getElementById('col_fecha_termino_etiquetado');
        }else if(tipoFiltro==="82"){
            selectedValues = document.getElementById('col_hora_termino_etiquetado');
        }else if(tipoFiltro==="83"){
            selectedValues = document.getElementById('col_proveedor');
        }else if(tipoFiltro==="84"){
            selectedValues = document.getElementById('col_proveedor_carga');
        }else if(tipoFiltro==="85"){
            selectedValues = document.getElementById('col_fy');
        }

        if(tipoFiltro==="0"){
           selected = "0"; 
        }else{
           selected = [...selectedValues.options].filter(option => option.selected).map(option => option.value);
        }       
        
        window.location.replace("../Catalogos/iframeDetailsCustoms.jsp?filterType="+tipoFiltro+"&id="+selected);
        
        $('#frameTableReporteCustoms').on("load", function() {
            $("#WindowLoad").remove();
        });
    }
            
    function doSearch() {
        var tableReg = document.getElementById('main-table');
        var searchText = document.getElementById('searchReporteCustoms').value.toLowerCase();
        var cellsOfRow = "";
        var found = false;
        var compareWith = "";

        // Recorremos todas las filas con contenido de la tabla
        for (var i = 1; i < tableReg.rows.length; i++)
        {
            cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
            found = false;
            // Recorremos todas las celdas
            for (var j = 0; j < cellsOfRow.length && !found; j++)
            {
                compareWith = cellsOfRow[j].innerHTML.toLowerCase();
                // Buscamos el texto en el contenido de la celda
                if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
                {
                    found = true;
                }
            }
            if (found)
            {
                tableReg.rows[i].style.display = '';
            } else {
                // si no ha encontrado ninguna coincidencia, esconde la
                // fila de la tabla
                tableReg.rows[i].style.display = 'none';
            }
        }
    }
    
    function descargar() {
        var tab_text = "<table border='2px'><tr bgcolor='#87AFC6'>";
        var textRange;
        var j = 0;
        var tab = document.getElementById('main-table'); // id of table
        var table_html = tab.outerHTML.replace(/ /g, '%20');

        for (j = 0; j < tab.rows.length; j++)
        {
            tab_text = tab_text + tab.rows[j].innerHTML + "</tr>";
            //tab_text=tab_text+"</tr>";
        }

        tab_text = tab_text + "</table>";
        tab_text = tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
        tab_text = tab_text.replace(/<img[^>]*>/gi, ""); // remove if u want images in your table
        tab_text = tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

        var ua = window.navigator.userAgent;
        var msie = ua.indexOf("MSIE ");

        if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
        {
            txtArea1.document.open("txt/html", "replace");
            txtArea1.document.write(tab_text);
            txtArea1.document.close();
            txtArea1.focus();
            sa = txtArea1.document.execCommand("SaveAs", true, "Reporte-Customs.xls");
        } else
            var a = document.createElement('a');
        document.body.appendChild(a);
        //other browser not tested on IE 11
        var data_type = 'data:application/vnd.ms-excel';
        a.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(tab_text);
        a.download = 'Reporte-Customs.xls';
        a.click();
    }

    function cleanMultiselects (){
        $('.selectpicker').selectpicker('deselectAll');
        FiltrerData("0");
    }
