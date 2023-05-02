/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    function AddCustoms() {
    
      //Parametros Indicadores
      let evento = document.getElementById("evento"); 
      let shipmentId = document.getElementById("shipmentId"); 
      let containerId = document.getElementById("containerId"); 
      let referenciaAA = document.getElementById("referenciaAA");
      let prioridad = document.getElementById("prioridad");
      let idAgenteAduanal = document.getElementById("idAgenteAduanal");
      
      //Parametros Generales
      let pais_origen = document.getElementById("pais_origen"); 
      let size_container = document.getElementById("size_container"); 
      let valor_usd = document.getElementById("valor_usd");                
      let eta_port_discharge = document.getElementById("eta_port_discharge");        
      let agente_aduanal = document.getElementById("agente_aduanal");            
      let pedimento_a1 = document.getElementById("pedimento_a1");             
      let pedimento_r1_1er = document.getElementById("pedimento_r1_1er");          
      let motivo_rectificacion_1er = document.getElementById("motivo_rectificacion_1er");  
      let pedimento_r1_2do = document.getElementById("pedimento_r1_2do");          
      let motivo_rectificacion_2do = document.getElementById("motivo_rectificacion_2do");  
      let fecha_recepcion_doc = document.getElementById("fecha_recepcion_doc");       
      let recinto = document.getElementById("recinto"); 
      let naviera = document.getElementById("naviera"); 
      let buque = document.getElementById("buque"); 
      let fecha_revalidacion = document.getElementById("fecha_revalidacion");        
      let fecha_previo_origen = document.getElementById("fecha_previo_origen");       
      let fecha_previo_destino = document.getElementById("fecha_previo_destino");      
      let fecha_resultado_previo = document.getElementById("fecha_resultado_previo");    
      let proforma_final = document.getElementById("proforma_final");            
      let permiso = document.getElementById("permiso");                   
      let fecha_envio = document.getElementById("fecha_envio");               
      let fecha_recepcion_perm = document.getElementById("fecha_recepcion_perm");    
      let fecha_activacion_perm = document.getElementById("fecha_activacion_perm");     
      let fecha_permisos_aut = document.getElementById("fecha_permisos_aut");        
      let co_pref_arancelaria = document.getElementById("co_pref_arancelaria");       
      let aplic_pref_arancelaria = document.getElementById("aplic_pref_arancelaria");    
      let req_uva = document.getElementById("req_uva");     
      let req_ca = document.getElementById("req_ca");      
      let fecha_recepcion_ca = document.getElementById("fecha_recepcion_ca");      
      let num_constancia_ca = document.getElementById("num_constancia_ca");      
      let monto_ca = document.getElementById("monto_ca");     
      let fecha_doc_completos = document.getElementById("fecha_doc_completos");       
      let fecha_pago_pedimento = document.getElementById("fecha_pago_pedimento");      
      let fecha_solicitud_transporte = document.getElementById("fecha_solicitud_transporte");
      let fecha_modulacion = document.getElementById("fecha_modulacion");          
      let modalidad = document.getElementById("modalidad");                 
      let resultado_modulacion = document.getElementById("resultado_modulacion");      
      let fecha_reconocimiento = document.getElementById("fecha_reconocimiento");      
      let fecha_liberacion = document.getElementById("fecha_liberacion");         
      let sello_origen = document.getElementById("sello_origen");              
      let sello_final = document.getElementById("sello_final");               
      let fecha_retencion_aut = document.getElementById("fecha_retencion_aut");       
      let fecha_liberacion_aut = document.getElementById("fecha_liberacion_aut");     
      let estatus_operacion = document.getElementById("estatus_operacion");         
      let motivo_atraso = document.getElementById("motivo_atraso");             
      let observaciones = document.getElementById("observaciones");
      let fy = document.getElementById("fy");

      //Parametros Logix
      let llegada_a_nova = document.getElementById("llegada_a_nova");
      let llegada_a_globe_trade_sd = document.getElementById("llegada_a_globe_trade_sd");
      let archivo_m = document.getElementById("archivo_m");
      let fecha_archivo_m = document.getElementById("fecha_archivo_m");
      let fecha_solicit_manip = document.getElementById("fecha_solicit_manip");
      let fecha_vencim_manip = document.getElementById("fecha_vencim_manip");
      let fecha_confirm_clave_pedim = document.getElementById("fecha_confirm_clave_pedim");
      let fecha_recep_increment = document.getElementById("fecha_recep_increment");
      let t_e = document.getElementById("t_e");
      let fecha_vencim_inbound = document.getElementById("fecha_vencim_inbound");
      
      //Parametros Cusa
      let no_bultos = document.getElementById("no_bultos");
      let peso_kg = document.getElementById("peso_kg");
      let transferencia = document.getElementById("transferencia");
      let fecha_inicio_etiquetado = document.getElementById("fecha_inicio_etiquetado");
      let fecha_termino_etiquetado = document.getElementById("fecha_termino_etiquetado");
      let hora_termino_etiquetado = document.getElementById("hora_termino_etiquetado");
      let proveedor = document.getElementById("proveedor");
      let proveedor_carga = document.getElementById("proveedor_carga");
      
      fetch("../InsertarCustomsForms?evento = "+ evento +      
            "&shipmentId = "+ shipmentId +  
            "&containerId = "+ containerId +  
            "&referenciaAA = "+ referenciaAA +  
            "&prioridad = "+ prioridad +  
            "&idAgenteAduanal = "+ idAgenteAduanal + 
            "&pais_origen = "+ pais_origen +   
            "&size_container = "+ size_container +   
            "&valor_usd = "+ valor_usd +               
            "&eta_port_discharge = "+ eta_port_discharge +       
            "&agente_aduanal = "+ agente_aduanal +            
            "&pedimento_a1 = "+ pedimento_a1 +             
            "&pedimento_r1_1er = "+ pedimento_r1_1er +         
            "&motivo_rectificacion_1er = "+ motivo_rectificacion_1er +
            "&pedimento_r1_2do = "+ pedimento_r1_2do +         
            "&motivo_rectificacion_2do = "+ motivo_rectificacion_2do + 
            "&fecha_recepcion_doc = "+ fecha_recepcion_doc +      
            "&recinto = "+ recinto +     
            "&naviera = "+ naviera +     
            "&buque = "+ buque +    
            "&fecha_revalidacion = "+ fecha_revalidacion +       
            "&fecha_previo_origen = "+ fecha_previo_origen +       
            "&fecha_previo_destino = "+ fecha_previo_destino +    
            "&fecha_resultado_previo = "+ fecha_resultado_previo +   
            "&proforma_final = "+ proforma_final +           
            "&permiso = "+ permiso +                   
            "&fecha_envio = "+ fecha_envio +               
            "&fecha_recepcion_perm = "+ fecha_recepcion_perm +     
            "&fecha_activacion_perm = "+ fecha_activacion_perm +    
            "&fecha_permisos_aut = "+ fecha_permisos_aut +        
            "&co_pref_arancelaria = "+ co_pref_arancelaria +       
            "&aplic_pref_arancelaria = "+ aplic_pref_arancelaria +  
            "&req_uva = "+ req_uva +   
            "&req_ca = "+ req_ca +  
            "&fecha_recepcion_ca = "+ fecha_recepcion_ca +  
            "&num_constancia_ca = "+ num_constancia_ca +  
            "&monto_ca = "+ monto_ca +  
            "&fecha_doc_completos = "+ fecha_doc_completos +       
            "&fecha_pago_pedimento = "+ fecha_pago_pedimento +      
            "&fecha_solicitud_transporte = "+ fecha_solicitud_transporte +
            "&fecha_modulacion = "+ fecha_modulacion +          
            "&modalidad = "+ modalidad +                
            "&resultado_modulacion = "+ resultado_modulacion +     
            "&fecha_reconocimiento = "+ fecha_reconocimiento +     
            "&fecha_liberacion = "+ fecha_liberacion +         
            "&sello_origen = "+ sello_origen +             
            "&sello_final = "+ sello_final +              
            "&fecha_retencion_aut = "+ fecha_retencion_aut +       
            "&fecha_liberacion_aut = "+ fecha_liberacion_aut +   
            "&estatus_operacion = "+ estatus_operacion +        
            "&motivo_atraso = "+ motivo_atraso +            
            "&observaciones = "+ observaciones + 
            "&fy = "+ fy + 
            "&llegada_a_nova = "+ llegada_a_nova + 
            "&llegada_a_globe_trade_sd = "+ llegada_a_globe_trade_sd + 
            "&archivo_m = "+ archivo_m + 
            "&fecha_archivo_m = "+ fecha_archivo_m + 
            "&fecha_solicit_manip = "+ fecha_solicit_manip + 
            "&fecha_vencim_manip = "+ fecha_vencim_manip + 
            "&fecha_confirm_clave_pedim = "+ fecha_confirm_clave_pedim + 
            "&fecha_recep_increment = "+ fecha_recep_increment + 
            "&t_e = "+ t_e + 
            "&fecha_vencim_inbound = "+ fecha_vencim_inbound + 
            "&no_bultos = "+ no_bultos + 
            "&peso_kg = "+ peso_kg + 
            "&transferencia = "+ transferencia + 
            "&fecha_inicio_etiquetado = "+ fecha_inicio_etiquetado + 
            "&fecha_termino_etiquetado = "+ fecha_termino_etiquetado + 
            "&hora_termino_etiquetado = "+ hora_termino_etiquetado + 
            "&proveedor = "+ proveedor + 
            "&proveedor_carga = "+ proveedor_carga, {
                method: 'POST',
            }).then(r => r.text())
            .then(data => {

                if (data == "1") {
                    swal("", "Registro exitoso", "success");
                    alertclose();
                    location.reload();
                } else {
                    swal("", "Información no registrada", "error");
                    alertclose();
                    return false;
                }

            }).catch(error => console.log(error));

}