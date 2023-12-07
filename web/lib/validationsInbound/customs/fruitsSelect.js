/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    
   /**********************************************************/  
    
      // Inicializa Select2 en el elemento multiselect
      $('#col_referenciaAA').select2();

      // Maneja la deselección de opciones
      $('#col_referenciaAA').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_referenciaAA').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/

      // Inicializa Select2 en el elemento multiselect
      $('#col_evento').select2();

      // Maneja la deselección de opciones
      $('#col_evento').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_evento').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/

      // Inicializa Select2 en el elemento multiselect
      $('#col_responsable').select2();

      // Maneja la deselección de opciones
      $('#col_responsable').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_responsable').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/

      // Inicializa Select2 en el elemento multiselect
      $('#col_finalDestination').select2();

      // Maneja la deselección de opciones
      $('#col_finalDestination').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_finalDestination').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/   

      // Inicializa Select2 en el elemento multiselect
      $('#col_brandDivision').select2();

      // Maneja la deselección de opciones
      $('#col_brandDivision').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_brandDivision').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_division').select2();

      // Maneja la deselección de opciones
      $('#col_division').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_division').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  
             
      // Inicializa Select2 en el elemento multiselect
      $('#col_shipmentId').select2();

      // Maneja la deselección de opciones
      $('#col_shipmentId').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_shipmentId').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_container').select2();

      // Maneja la deselección de opciones
      $('#col_container').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_container').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_blAwbPro').select2();

      // Maneja la deselección de opciones
      $('#col_blAwbPro').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_blAwbPro').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_loadType').select2();

      // Maneja la deselección de opciones
      $('#col_loadType').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_loadType').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_quantity').select2();

      // Maneja la deselección de opciones
      $('#col_quantity').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_quantity').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_pod').select2();

      // Maneja la deselección de opciones
      $('#col_pod').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_pod').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_estDepartFromPol').select2();

      // Maneja la deselección de opciones
      $('#col_estDepartFromPol').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_estDepartFromPol').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_etaRealPortOfDischarge').select2();

      // Maneja la deselección de opciones
      $('#col_etaRealPortOfDischarge').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_etaRealPortOfDischarge').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_estEtaDc').select2();

      // Maneja la deselección de opciones
      $('#col_estEtaDc').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_estEtaDc').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_inboundNotification').select2();

      // Maneja la deselección de opciones
      $('#col_inboundNotification').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_inboundNotification').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_pol').select2();

      // Maneja la deselección de opciones
      $('#col_pol').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_pol').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_aa').select2();

      // Maneja la deselección de opciones
      $('#col_aa').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_aa').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fechaMesVenta').select2();

      // Maneja la deselección de opciones
      $('#col_fechaMesVenta').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fechaMesVenta').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_prioridad').select2();

      // Maneja la deselección de opciones
      $('#col_prioridad').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_prioridad').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_pais_origen').select2();

      // Maneja la deselección de opciones
      $('#col_pais_origen').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_pais_origen').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_size_container').select2();

      // Maneja la deselección de opciones
      $('#col_size_container').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_size_container').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_valor_usd').select2();

      // Maneja la deselección de opciones
      $('#col_valor_usd').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_valor_usd').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_eta_port_discharge').select2();

      // Maneja la deselección de opciones
      $('#col_eta_port_discharge').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_eta_port_discharge').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  
    
      // Inicializa Select2 en el elemento multiselect
      $('#col_agente_aduanal').select2();

      // Maneja la deselección de opciones
      $('#col_agente_aduanal').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_agente_aduanal').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  
    
      // Inicializa Select2 en el elemento multiselect
      $('#col_pedimento_a1').select2();

      // Maneja la deselección de opciones
      $('#col_pedimento_a1').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_pedimento_a1').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_pedimento_r1_1er').select2();

      // Maneja la deselección de opciones
      $('#col_pedimento_r1_1er').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_pedimento_r1_1er').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_motivo_rectificacion_1er').select2();

      // Maneja la deselección de opciones
      $('#col_motivo_rectificacion_1er').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_motivo_rectificacion_1er').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_pedimento_r1_2do').select2();

      // Maneja la deselección de opciones
      $('#col_pedimento_r1_2do').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_pedimento_r1_2do').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_motivo_rectificacion_2do').select2();

      // Maneja la deselección de opciones
      $('#col_motivo_rectificacion_2do').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_motivo_rectificacion_2do').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_recepcion_doc').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_recepcion_doc').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_recepcion_doc').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_recinto').select2();

      // Maneja la deselección de opciones
      $('#col_recinto').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_recinto').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_naviera').select2();

      // Maneja la deselección de opciones
      $('#col_naviera').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_naviera').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_buque').select2();

      // Maneja la deselección de opciones
      $('#col_buque').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_buque').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  
    
      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_revalidacion').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_revalidacion').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_revalidacion').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_previo_origen').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_previo_origen').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_previo_origen').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  
    
      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_previo_destino').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_previo_destino').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_previo_destino').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_resultado_previo').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_resultado_previo').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_resultado_previo').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_proforma_final').select2();

      // Maneja la deselección de opciones
      $('#col_proforma_final').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_proforma_final').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_permiso').select2();

      // Maneja la deselección de opciones
      $('#col_permiso').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_permiso').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_envio').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_envio').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_envio').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_recepcion_perm').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_recepcion_perm').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_recepcion_perm').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_activacion_perm').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_activacion_perm').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_activacion_perm').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_permisos_aut').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_permisos_aut').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_permisos_aut').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_co_pref_arancelaria').select2();

      // Maneja la deselección de opciones
      $('#col_co_pref_arancelaria').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_co_pref_arancelaria').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_aplic_pref_arancelaria').select2();

      // Maneja la deselección de opciones
      $('#col_aplic_pref_arancelaria').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_aplic_pref_arancelaria').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_req_uva').select2();

      // Maneja la deselección de opciones
      $('#col_req_uva').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_req_uva').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_req_ca').select2();

      // Maneja la deselección de opciones
      $('#col_req_ca').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_req_ca').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
    
    /**********************************************************/  
             
      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_recepcion_ca').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_recepcion_ca').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_recepcion_ca').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_num_constancia_ca').select2();

      // Maneja la deselección de opciones
      $('#col_num_constancia_ca').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_num_constancia_ca').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_monto_ca').select2();

      // Maneja la deselección de opciones
      $('#col_monto_ca').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_monto_ca').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_doc_completos').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_doc_completos').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_doc_completos').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_pago_pedimento').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_pago_pedimento').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_pago_pedimento').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  
             
      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_solicitud_transporte').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_solicitud_transporte').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_solicitud_transporte').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  
             
      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_modulacion').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_modulacion').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_modulacion').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_modalidad').select2();

      // Maneja la deselección de opciones
      $('#col_modalidad').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_modalidad').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_resultado_modulacion').select2();

      // Maneja la deselección de opciones
      $('#col_resultado_modulacion').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_resultado_modulacion').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_reconocimiento').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_reconocimiento').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_reconocimiento').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_liberacion').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_liberacion').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_liberacion').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  
 
      // Inicializa Select2 en el elemento multiselect
      $('#col_sello_origen').select2();

      // Maneja la deselección de opciones
      $('#col_sello_origen').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_sello_origen').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_sello_final').select2();

      // Maneja la deselección de opciones
      $('#col_sello_final').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_sello_final').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_retencion_aut').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_retencion_aut').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_retencion_aut').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_liberacion_aut').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_liberacion_aut').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_liberacion_aut').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  
    
      // Inicializa Select2 en el elemento multiselect
      $('#col_estatus_operacion').select2();

      // Maneja la deselección de opciones
      $('#col_estatus_operacion').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_estatus_operacion').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_motivo_atraso').select2();

      // Maneja la deselección de opciones
      $('#col_motivo_atraso').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_motivo_atraso').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_observaciones').select2();

      // Maneja la deselección de opciones
      $('#col_observaciones').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_observaciones').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  
             
      // Inicializa Select2 en el elemento multiselect
      $('#col_llegada_a_nova').select2();

      // Maneja la deselección de opciones
      $('#col_llegada_a_nova').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_llegada_a_nova').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  
             
      // Inicializa Select2 en el elemento multiselect
      $('#col_llegada_a_globe_trade_sd').select2();

      // Maneja la deselección de opciones
      $('#col_llegada_a_globe_trade_sd').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_llegada_a_globe_trade_sd').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  
             
      // Inicializa Select2 en el elemento multiselect
      $('#col_archivo_m').select2();

      // Maneja la deselección de opciones
      $('#col_archivo_m').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_archivo_m').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_archivo_m').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_archivo_m').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_archivo_m').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  
    
      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_solicit_manip').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_solicit_manip').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_solicit_manip').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_vencim_manip').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_vencim_manip').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_vencim_manip').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_confirm_clave_pedim').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_confirm_clave_pedim').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_confirm_clave_pedim').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_recep_increment').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_recep_increment').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_recep_increment').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_t_e').select2();

      // Maneja la deselección de opciones
      $('#col_t_e').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_t_e').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_vencim_inbound').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_vencim_inbound').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_vencim_inbound').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_no_bultos').select2();

      // Maneja la deselección de opciones
      $('#col_no_bultos').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_no_bultos').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_peso_kg').select2();

      // Maneja la deselección de opciones
      $('#col_peso_kg').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_peso_kg').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  
    
      // Inicializa Select2 en el elemento multiselect
      $('#col_transferencia').select2();

      // Maneja la deselección de opciones
      $('#col_transferencia').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_transferencia').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_inicio_etiquetado').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_inicio_etiquetado').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_inicio_etiquetado').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fecha_termino_etiquetado').select2();

      // Maneja la deselección de opciones
      $('#col_fecha_termino_etiquetado').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fecha_termino_etiquetado').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_hora_termino_etiquetado').select2();

      // Maneja la deselección de opciones
      $('#col_hora_termino_etiquetado').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_hora_termino_etiquetado').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_proveedor').select2();

      // Maneja la deselección de opciones
      $('#col_proveedor').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_proveedor').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_proveedor_carga').select2();

      // Maneja la deselección de opciones
      $('#col_proveedor_carga').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_proveedor_carga').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  

      // Inicializa Select2 en el elemento multiselect
      $('#col_fy').select2();

      // Maneja la deselección de opciones
      $('#col_fy').on('select2:unselect', function (e) {
        var data = e.params.data;
        console.log('Deselected: ' + data.text + ' (ID: ' + data.id + ')');
      });

      // Maneja la selección de opciones
      $('#col_fy').on('select2:select', function (e) {
        var data = e.params.data;
        console.log('Selected: ' + data.text + ' (ID: ' + data.id + ')');
      });
      
    /**********************************************************/  
 
});
