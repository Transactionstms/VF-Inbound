/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    
   /**********************************************************/  
    
    $('#col_referenciaAA').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_referenciaAA').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_referenciaAA').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/
    
    $('#col_evento').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_evento').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_evento').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/
        
    $('#col_responsable').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_responsable').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_responsable').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/
         
    $('#col_finalDestination').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_finalDestination').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_finalDestination').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/   
          
    $('#col_brandDivision').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_brandDivision').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_brandDivision').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_division').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_division').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_division').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_shipmentId').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_shipmentId').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_shipmentId').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_container').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_container').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_container').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_blAwbPro').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_blAwbPro').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_blAwbPro').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_loadType').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_loadType').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_loadType').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_quantity').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_quantity').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_quantity').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_pod').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_pod').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_pod').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_estDepartFromPol').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_estDepartFromPol').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_estDepartFromPol').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_etaRealPortOfDischarge').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_etaRealPortOfDischarge').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_etaRealPortOfDischarge').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_estEtaDc').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_estEtaDc').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_estEtaDc').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_inboundNotification').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_inboundNotification').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_inboundNotification').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_pol').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_pol').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_pol').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_aa').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_aa').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_aa').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fechaMesVenta').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fechaMesVenta').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fechaMesVenta').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_prioridad').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_prioridad').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_prioridad').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_pais_origen').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_pais_origen').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_pais_origen').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_size_container').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_size_container').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_size_container').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_valor_usd').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_valor_usd').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_valor_usd').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_eta_port_discharge').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_eta_port_discharge').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_eta_port_discharge').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_agente_aduanal').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_agente_aduanal').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_agente_aduanal').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_pedimento_a1').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_pedimento_a1').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_pedimento_a1').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_pedimento_r1_1er').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_pedimento_r1_1er').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_pedimento_r1_1er').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_motivo_rectificacion_1er').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_motivo_rectificacion_1er').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_motivo_rectificacion_1er').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_pedimento_r1_2do').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_pedimento_r1_2do').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_pedimento_r1_2do').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_motivo_rectificacion_2do').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_motivo_rectificacion_2do').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_motivo_rectificacion_2do').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_recepcion_doc').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_recepcion_doc').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_recepcion_doc').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_recinto').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_recinto').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_recinto').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_naviera').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_naviera').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_naviera').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_buque').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_buque').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_buque').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_revalidacion').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_revalidacion').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_revalidacion').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_previo_origen').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_previo_origen').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_previo_origen').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_previo_destino').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_previo_destino').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_previo_destino').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_resultado_previo').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_resultado_previo').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_resultado_previo').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_proforma_final').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_proforma_final').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_proforma_final').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_permiso').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_permiso').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_permiso').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_envio').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_envio').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_envio').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_recepcion_perm').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_recepcion_perm').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_recepcion_perm').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_activacion_perm').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_activacion_perm').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_activacion_perm').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_permisos_aut').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_permisos_aut').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_permisos_aut').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_co_pref_arancelaria').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_co_pref_arancelaria').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_co_pref_arancelaria').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_aplic_pref_arancelaria').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_aplic_pref_arancelaria').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_aplic_pref_arancelaria').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_req_uva').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_req_uva').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_req_uva').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_req_ca').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_req_ca').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_req_ca').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_recepcion_ca').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_recepcion_ca').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_recepcion_ca').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_num_constancia_ca').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_num_constancia_ca').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_num_constancia_ca').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_monto_ca').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_monto_ca').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_monto_ca').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_doc_completos').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_doc_completos').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_doc_completos').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_pago_pedimento').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_pago_pedimento').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_pago_pedimento').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_solicitud_transporte').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_solicitud_transporte').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_solicitud_transporte').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_modulacion').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_modulacion').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_modulacion').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_modalidad').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_modalidad').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_modalidad').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_resultado_modulacion').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_resultado_modulacion').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_resultado_modulacion').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_reconocimiento').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_reconocimiento').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_reconocimiento').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_liberacion').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_liberacion').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_liberacion').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_sello_origen').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_sello_origen').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_sello_origen').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_sello_final').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_sello_final').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_sello_final').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_retencion_aut').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_retencion_aut').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_retencion_aut').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_liberacion_aut').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_liberacion_aut').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_liberacion_aut').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_estatus_operacion').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_estatus_operacion').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_estatus_operacion').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_motivo_atraso').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_motivo_atraso').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_motivo_atraso').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_observaciones').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_observaciones').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_observaciones').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_llegada_a_nova').select2({
        closeOnSelect: false
    });
    
     $('#arrayList_referenciaAA').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
  //  $('#col_llegada_a_nova').on('select2:select', function (e) {
  //      const fruitValue = e.params.data.id;
  //      const fruitText = e.params.data.text;
  //      addFruit(fruitValue, fruitText);
  //  });
//
  //  // Event listener for fruit deselection
  //  $('#col_llegada_a_nova').on('select2:unselect', function (e) {
  //      const fruitValue = e.params.data.id;
  //      removeFruit(fruitValue);
  //  });
    
    /**********************************************************/  
             
    $('#col_llegada_a_globe_trade_sd').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_llegada_a_globe_trade_sd').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_llegada_a_globe_trade_sd').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_archivo_m').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_archivo_m').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_archivo_m').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_archivo_m').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_archivo_m').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_archivo_m').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_solicit_manip').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_solicit_manip').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_solicit_manip').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_vencim_manip').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_vencim_manip').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_vencim_manip').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_confirm_clave_pedim').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_confirm_clave_pedim').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_confirm_clave_pedim').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_recep_increment').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_recep_increment').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_recep_increment').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_t_e').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_t_e').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_t_e').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_vencim_inbound').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_vencim_inbound').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_vencim_inbound').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_no_bultos').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_no_bultos').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_no_bultos').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_peso_kg').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_peso_kg').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_peso_kg').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_transferencia').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_transferencia').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_transferencia').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_inicio_etiquetado').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_inicio_etiquetado').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_inicio_etiquetado').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fecha_termino_etiquetado').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fecha_termino_etiquetado').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fecha_termino_etiquetado').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_hora_termino_etiquetado').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_hora_termino_etiquetado').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_hora_termino_etiquetado').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_proveedor').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_proveedor').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_proveedor').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_proveedor_carga').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_proveedor_carga').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_proveedor_carga').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
             
    $('#col_fy').select2({
        closeOnSelect: false
    });

    // Event listener for fruit selection
    $('#col_fy').on('select2:select', function (e) {
        const fruitValue = e.params.data.id;
        const fruitText = e.params.data.text;
        addFruit(fruitValue, fruitText);
    });

    // Event listener for fruit deselection
    $('#col_fy').on('select2:unselect', function (e) {
        const fruitValue = e.params.data.id;
        removeFruit(fruitValue);
    });
    
    /**********************************************************/  
 
});

