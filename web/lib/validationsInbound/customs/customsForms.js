/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    $(document).ready(function () {
        customForm("0");
    });

    function customForm(tipoFiltro) {

        let idAgente = document.getElementById("idAgenteAduanal").value;   

        fetch("../ConsultarCustom?tipoAgente="+idAgente+"&tipoFiltro="+tipoFiltro, {
            method: 'POST',
        }).then(r => r.text())
                .then(data => {
                    document.getElementById('table-scroll').innerHTML = data;
                }).catch(error => console.log(error));
    }
    
    function AddCustoms() {

          //Contadores
            let urlCustoms = ""; 

          //Parametros Indicadores
            let evento;
            let shipmentId;
            let containerId;
            let referenciaAA;
            let prioridad;

          //Parametros Generales
            let pais_origen;    
            let size_container;    
            let valor_usd;                 
            let eta_port_discharge;         
            let agente_aduanal;              
            let pedimento_a1;              
            let pedimento_r1_1er;          
            let motivo_rectificacion_1er;    
            let pedimento_r1_2do;         
            let motivo_rectificacion_2do;    
            let fecha_recepcion_doc;    
            let recinto;
            let naviera;
            let buque;
            let fecha_revalidacion;      
            let fecha_previo_origen;     
            let fecha_previo_destino;   
            let fecha_resultado_previo;   
            let proforma_final;        
            let permiso;               
            let fecha_envio;             
            let fecha_recepcion_perm;
            let fecha_activacion_perm;    
            let fecha_permisos_aut;     
            let co_pref_arancelaria;    
            let aplic_pref_arancelaria;  
            let req_uva;   
            let req_ca;    
            let fecha_recepcion_ca; 
            let num_constancia_ca;   
            let monto_ca;
            let fecha_doc_completos;  
            let fecha_pago_pedimento;     
            let fecha_solicitud_transporte;
            let fecha_modulacion;   
            let modalidad;          
            let resultado_modulacion;    
            let fecha_reconocimiento;     
            let fecha_liberacion;       
            let sello_origen;            
            let sello_final;      
            let fecha_retencion_aut;     
            let fecha_liberacion_aut;   
            let estatus_operacion;      
            let motivo_atraso;          
            let observaciones; 
            let fy;

          //Parametros Logix
            let llegada_a_nova;
            let llegada_a_globe_trade_sd;
            let archivo_m;
            let fecha_archivo_m;
            let fecha_solicit_manip;
            let fecha_vencim_manip;
            let fecha_confirm_clave_pedim;
            let fecha_recep_increment;
            let t_e;
            let fecha_vencim_inbound;  

          //Parametros Cusa
            let no_bultos;
            let peso_kg;
            let transferencia;
            let fecha_inicio_etiquetado;
            let fecha_termino_etiquetado;
            let hora_termino_etiquetado;
            let proveedor;
            let proveedor_carga;     

            let idAgenteAduanal = document.getElementById("idAgenteAduanal").value;   
            let contadorCustoms = document.getElementById("numCustoms").value; 

          for (let i=0; i < contadorCustoms; i++){

                    //Parametros Indicadores          
                        let temp1 = "evento[" + i + "]";
                        let temp2 = "shipmentId[" + i + "]";
                        let temp3 = "containerId[" + i + "]";
                        let temp4 = "referenciaAA[" + i + "]";
                        let temp5 = "prioridad[" + i + "]";

                        evento = document.getElementById(temp1).value; 
                        shipmentId = document.getElementById(temp2).value; 
                        containerId = document.getElementById(temp3).value; 
                        referenciaAA = document.getElementById(temp4).value;
                        prioridad = document.getElementById(temp5).value;


                    //Parametros Generales          
                        let temp7 = "pais_origen[" + i + "]";
                        let temp8 = "size_container[" + i + "]";
                        let temp9 = "valor_usd[" + i + "]";
                        let temp10 = "eta_port_discharge[" + i + "]";
                        let temp11 = "agente_aduanal[" + i + "]";
                        let temp12 = "pedimento_a1[" + i + "]";
                        let temp13 = "pedimento_r1_1er[" + i + "]";
                        let temp14 = "motivo_rectificacion_1er[" + i + "]";
                        let temp15 = "pedimento_r1_2do[" + i + "]";
                        let temp16 = "motivo_rectificacion_2do[" + i + "]";
                        let temp17 = "fecha_recepcion_doc[" + i + "]";
                        let temp18 = "recinto[" + i + "]";
                        let temp19 = "naviera[" + i + "]";
                        let temp20 = "buque[" + i + "]";
                        let temp21 = "fecha_revalidacion[" + i + "]";
                        let temp22 = "fecha_previo_origen[" + i + "]";
                        let temp23 = "fecha_previo_destino[" + i + "]";
                        let temp24 = "fecha_resultado_previo[" + i + "]";
                        let temp25 = "proforma_final[" + i + "]";
                        let temp26 = "permiso[" + i + "]";
                        let temp27 = "fecha_envio[" + i + "]";
                        let temp28 = "fecha_recepcion_perm[" + i + "]";
                        let temp29 = "fecha_activacion_perm[" + i + "]";
                        let temp30 = "fecha_permisos_aut[" + i + "]";
                        let temp31 = "co_pref_arancelaria[" + i + "]";
                        let temp32 = "aplic_pref_arancelaria[" + i + "]";
                        let temp33 = "req_uva[" + i + "]";
                        let temp34 = "req_ca[" + i + "]";
                        let temp35 = "fecha_recepcion_ca[" + i + "]";
                        let temp36 = "num_constancia_ca[" + i + "]";
                        let temp37 = "monto_ca[" + i + "]";
                        let temp38 = "fecha_doc_completos[" + i + "]";
                        let temp39 = "fecha_pago_pedimento[" + i + "]";
                        let temp40 = "fecha_solicitud_transporte[" + i + "]";
                        let temp41 = "fecha_modulacion[" + i + "]";
                        let temp42 = "modalidad[" + i + "]";
                        let temp43 = "resultado_modulacion[" + i + "]";
                        let temp44 = "fecha_reconocimiento[" + i + "]";
                        let temp45 = "fecha_liberacion[" + i + "]";
                        let temp46 = "sello_origen[" + i + "]";
                        let temp47 = "sello_final[" + i + "]";
                        let temp48 = "fecha_retencion_aut[" + i + "]";
                        let temp49 = "fecha_liberacion_aut[" + i + "]";
                        let temp50 = "estatus_operacion[" + i + "]";
                        let temp51 = "motivo_atraso[" + i + "]";
                        let temp52 = "observaciones[" + i + "]";
                        let temp53 = "fy[" + i + "]";

                        pais_origen = document.getElementById(temp7).value;
                        size_container = document.getElementById(temp8).value;
                        valor_usd = document.getElementById(temp9).value;               
                        eta_port_discharge = document.getElementById(temp10).value;       
                        agente_aduanal = document.getElementById(temp11).value;           
                        pedimento_a1 = document.getElementById(temp12).value;            
                        pedimento_r1_1er = document.getElementById(temp13).value;         
                        motivo_rectificacion_1er = document.getElementById(temp14).value; 
                        pedimento_r1_2do = document.getElementById(temp15).value;         
                        motivo_rectificacion_2do = document.getElementById(temp16).value; 
                        fecha_recepcion_doc = document.getElementById(temp17).value;      
                        recinto = document.getElementById(temp18).value;
                        naviera = document.getElementById(temp19).value;
                        buque = document.getElementById(temp20).value;
                        fecha_revalidacion = document.getElementById(temp21).value;       
                        fecha_previo_origen = document.getElementById(temp22).value;      
                        fecha_previo_destino = document.getElementById(temp23).value;     
                        fecha_resultado_previo = document.getElementById(temp24).value;   
                        proforma_final = document.getElementById(temp25).value;           
                        permiso = document.getElementById(temp26).value;                  
                        fecha_envio = document.getElementById(temp27).value;              
                        fecha_recepcion_perm = document.getElementById(temp28).value;   
                        fecha_activacion_perm = document.getElementById(temp29).value;    
                        fecha_permisos_aut = document.getElementById(temp30).value;       
                        co_pref_arancelaria = document.getElementById(temp31).value;      
                        aplic_pref_arancelaria = document.getElementById(temp32).value;   
                        req_uva = document.getElementById(temp33).value;    
                        req_ca = document.getElementById(temp34).value;     
                        fecha_recepcion_ca = document.getElementById(temp35).value;     
                        num_constancia_ca = document.getElementById(temp36).value;     
                        monto_ca = document.getElementById(temp37).value;    
                        fecha_doc_completos = document.getElementById(temp38).value;      
                        fecha_pago_pedimento = document.getElementById(temp39).value;     
                        fecha_solicitud_transporte = document.getElementById(temp40).value;
                        fecha_modulacion = document.getElementById(temp41).value;         
                        modalidad = document.getElementById(temp42).value;                
                        resultado_modulacion = document.getElementById(temp43).value;     
                        fecha_reconocimiento = document.getElementById(temp44).value;     
                        fecha_liberacion = document.getElementById(temp45).value;        
                        sello_origen = document.getElementById(temp46).value;             
                        sello_final = document.getElementById(temp47).value;              
                        fecha_retencion_aut = document.getElementById(temp48).value;      
                        fecha_liberacion_aut = document.getElementById(temp49).value;    
                        estatus_operacion = document.getElementById(temp50).value;        
                        motivo_atraso = document.getElementById(temp51).value;            
                        observaciones = document.getElementById(temp52).value;   
                        fy = document.getElementById(temp53).value;   

                        if(idAgenteAduanal == "4001" || idAgenteAduanal == "4006"){ //Logix ó VF

                                let temp54 = "llegada_a_nova[" + i + "]";
                                let temp55 = "llegada_a_globe_trade_sd[" + i + "]";
                                let temp56 = "archivo_m[" + i + "]";
                                let temp57 = "fecha_archivo_m[" + i + "]";
                                let temp58 = "fecha_solicit_manip[" + i + "]";
                                let temp59 = "fecha_vencim_manip[" + i + "]";
                                let temp60 = "fecha_confirm_clave_pedim[" + i + "]";
                                let temp61 = "fecha_recep_increment[" + i + "]";
                                let temp62 = "t_e[" + i + "]";
                                let temp63 = "fecha_vencim_inbound[" + i + "]"; 

                                llegada_a_nova = document.getElementById(temp54).value;  
                                llegada_a_globe_trade_sd = document.getElementById(temp55).value;  
                                archivo_m = document.getElementById(temp56).value;  
                                fecha_archivo_m = document.getElementById(temp57).value;  
                                fecha_solicit_manip = document.getElementById(temp58).value;  
                                fecha_vencim_manip = document.getElementById(temp59).value;  
                                fecha_confirm_clave_pedim = document.getElementById(temp60).value;  
                                fecha_recep_increment = document.getElementById(temp61).value;  
                                t_e = document.getElementById(temp62).value;  
                                fecha_vencim_inbound = document.getElementById(temp63).value; 

                        }
                        
                        if(idAgenteAduanal == "4002" || idAgenteAduanal == "4006"){  //Cusa ó VF

                                let temp64 = "no_bultos[" + i + "]"; 
                                let temp65 = "peso_kg[" + i + "]"; 
                                let temp66 = "transferencia[" + i + "]"; 
                                let temp67 = "fecha_inicio_etiquetado[" + i + "]"; 
                                let temp68 = "fecha_termino_etiquetado[" + i + "]"; 
                                let temp69 = "hora_termino_etiquetado[" + i + "]"; 
                                let temp70 = "proveedor[" + i + "]"; 
                                let temp71 = "proveedor_carga[" + i + "]"; 

                                no_bultos = document.getElementById(temp64).value; 
                                peso_kg = document.getElementById(temp65).value; 
                                transferencia = document.getElementById(temp66).value; 
                                fecha_inicio_etiquetado = document.getElementById(temp67).value; 
                                fecha_termino_etiquetado = document.getElementById(temp68).value; 
                                hora_termino_etiquetado = document.getElementById(temp69).value; 
                                proveedor = document.getElementById(temp70).value; 
                                proveedor_carga = document.getElementById(temp71).value; 
                        }                 	  

                                 urlCustoms +=          "&evento[" + i + "]=" + evento +
                                                        "&shipmentId[" + i + "]=" + shipmentId +
                                                        "&containerId[" + i + "]=" + containerId +
                                                        "&referenciaAA[" + i + "]=" + referenciaAA +
                                                        "&prioridad[" + i + "]=" + prioridad +

                                                        "&pais_origen[" + i + "]=" + pais_origen +
                                                        "&size_container[" + i + "]=" + size_container +
                                                        "&valor_usd[" + i + "]=" + valor_usd +              
                                                        "&eta_port_discharge[" + i + "]=" + eta_port_discharge +     
                                                        "&agente_aduanal[" + i + "]=" + agente_aduanal +         
                                                        "&pedimento_a1[" + i + "]=" + pedimento_a1 +           
                                                        "&pedimento_r1_1er[" + i + "]=" + pedimento_r1_1er +        
                                                        "&motivo_rectificacion_1er[" + i + "]=" + motivo_rectificacion_1er +
                                                        "&pedimento_r1_2do[" + i + "]=" + pedimento_r1_2do +   
                                                        "&motivo_rectificacion_2do[" + i + "]=" + motivo_rectificacion_2do + 
                                                        "&fecha_recepcion_doc[" + i + "]=" + fecha_recepcion_doc +    
                                                        "&recinto[" + i + "]=" + recinto +
                                                        "&naviera[" + i + "]=" + naviera +
                                                        "&buque[" + i + "]=" + buque +
                                                        "&fecha_revalidacion[" + i + "]=" + fecha_revalidacion +      
                                                        "&fecha_previo_origen[" + i + "]=" + fecha_previo_origen +     
                                                        "&fecha_previo_destino[" + i + "]=" + fecha_previo_destino +   
                                                        "&fecha_resultado_previo[" + i + "]=" + fecha_resultado_previo +   
                                                        "&proforma_final[" + i + "]=" + proforma_final +        
                                                        "&permiso[" + i + "]=" + permiso +               
                                                        "&fecha_envio[" + i + "]=" + fecha_envio +             
                                                        "&fecha_recepcion_perm[" + i + "]=" + fecha_recepcion_perm +
                                                        "&fecha_activacion_perm[" + i + "]=" + fecha_activacion_perm +    
                                                        "&fecha_permisos_aut[" + i + "]=" + fecha_permisos_aut +     
                                                        "&co_pref_arancelaria[" + i + "]=" + co_pref_arancelaria +    
                                                        "&aplic_pref_arancelaria[" + i + "]=" + aplic_pref_arancelaria +  
                                                        "&req_uva[" + i + "]=" + req_uva +   
                                                        "&req_ca[" + i + "]=" + req_ca +    
                                                        "&fecha_recepcion_ca[" + i + "]=" + fecha_recepcion_ca + 
                                                        "&num_constancia_ca[" + i + "]=" + num_constancia_ca +   
                                                        "&monto_ca[" + i + "]=" + monto_ca +
                                                        "&fecha_doc_completos[" + i + "]=" + fecha_doc_completos +  
                                                        "&fecha_pago_pedimento[" + i + "]=" + fecha_pago_pedimento +     
                                                        "&fecha_solicitud_transporte[" + i + "]=" + fecha_solicitud_transporte +
                                                        "&fecha_modulacion[" + i + "]=" + fecha_modulacion +   
                                                        "&modalidad[" + i + "]=" + modalidad +          
                                                        "&resultado_modulacion[" + i + "]=" + resultado_modulacion +    
                                                        "&fecha_reconocimiento[" + i + "]=" + fecha_reconocimiento +     
                                                        "&fecha_liberacion[" + i + "]=" + fecha_liberacion +       
                                                        "&sello_origen[" + i + "]=" + sello_origen +            
                                                        "&sello_final[" + i + "]=" + sello_final +      
                                                        "&fecha_retencion_aut[" + i + "]=" + fecha_retencion_aut +     
                                                        "&fecha_liberacion_aut[" + i + "]=" + fecha_liberacion_aut +   
                                                        "&estatus_operacion[" + i + "]=" + estatus_operacion +      
                                                        "&motivo_atraso[" + i + "]=" + motivo_atraso +          
                                                        "&observaciones[" + i + "]=" + observaciones + 
                                                        "&fy[" + i + "]=" + fy; 

                        if(idAgenteAduanal == "4001" || idAgenteAduanal == "4006"){ //Logix ó VF 

                                 urlCustoms +=          "&llegada_a_nova[" + i + "]=" + llegada_a_nova +
                                                        "&llegada_a_globe_trade_sd[" + i + "]=" + llegada_a_globe_trade_sd +
                                                        "&archivo_m[" + i + "]=" + archivo_m + 
                                                        "&fecha_archivo_m[" + i + "]=" + fecha_archivo_m + 
                                                        "&fecha_solicit_manip[" + i + "]=" + fecha_solicit_manip + 
                                                        "&fecha_vencim_manip[" + i + "]=" + fecha_vencim_manip + 
                                                        "&fecha_confirm_clave_pedim[" + i + "]=" + fecha_confirm_clave_pedim + 
                                                        "&fecha_recep_increment[" + i + "]=" + fecha_recep_increment + 
                                                        "&t_e[" + i + "]=" + t_e + 
                                                        "&fecha_vencim_inbound[" + i + "]=" + fecha_vencim_inbound;

                        }
                        
                        if(idAgenteAduanal == "4002" || idAgenteAduanal == "4006"){  //Cusa ó VF

                                 urlCustoms +=          "&no_bultos[" + i + "]=" + no_bultos + 
                                                        "&peso_kg[" + i + "]=" + peso_kg + 
                                                        "&transferencia[" + i + "]=" + transferencia + 
                                                        "&fecha_inicio_etiquetado[" + i + "]=" + fecha_inicio_etiquetado + 
                                                        "&fecha_termino_etiquetado[" + i + "]=" + fecha_termino_etiquetado + 
                                                        "&hora_termino_etiquetado[" + i + "]=" + hora_termino_etiquetado + 
                                                        "&proveedor[" + i + "]=" + proveedor + 
                                                        "&proveedor_carga[" + i + "]=" + proveedor_carga;
                        }

          }   

          fetch("../InsertarCustomsForms?idAgenteAduanal=" + idAgenteAduanal + "&numCustoms=" + contadorCustoms + urlCustoms, {
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
    
    function openModalPlantilla() {
        $("#modalSubirPlantilla").modal("show");
    }

    function alertclose() {
        setTimeout(function () {
            swal.close();
        }, 5000);
    }
    
    function onSemaforo(){
        swal("", "Semaforo Activado", "info");
        setTimeout(function () {
            swal.close();
        }, 1000);
    }