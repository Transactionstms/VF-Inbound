<%-- 
    Document   : customForm
    Created on : 2/06/2023, 01:43:04 AM
    Author     : luis_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.pantalla.BLPantalla"%>
<%@page import="com.pantalla.Pantalla"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.onest.train.consultas.ConsultasQuery"%>
<%@page import="com.onest.security.menu.*" %>
<%@page import="com.onest.net.*" %>
<%@page import="com.onest.oracle.*" %>
<%@page import="com.onest.misc.*" %>
<%@page import="com.usuario.Usuario"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    Date date = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("MM/dd/yyyy");
    String fecha = formato.format(date);
    String[] par = fecha.split("/");
    String fecha_actual = par[0]+"/"+par[1]+"/"+par[2];
%>    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Personalizar Customs</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/css/style.default.css" id="theme-stylesheet">
        <!-- Table css -->
        <link href="<%=request.getContextPath()%>/lib/validationsInbound/customs/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- jQuery 3.6.0 -->
        <script src="<%=request.getContextPath()%>/lib/jQuery3.6.0/js/jquery.min.js" type="text/javascript"></script>
        <!-- sweetalert -->
        <link href="<%=request.getContextPath()%>/lib/SweetAlert1.1.3/css/sweetalert.min.css" rel="stylesheet" type="text/css"/>
        <!-- Filtrer Checkbox -->
        <link href="<%=request.getContextPath()%>/lib/validationsInbound/customs/styleFiltrerCheckbox.css" rel="stylesheet" type="text/css"/>
        <!-- calendarios -->
        <link href="<%=request.getContextPath()%>/lib/calendarios/css/flatpickr.min.css" rel="stylesheet" type="text/css"/>
        <!-- virtual-scroll -->
        <script src="https://cdn.jsdelivr.net/npm/virtual-scroll"></script>
        <style>
            .hidden-btn {
               display: none;
            }
        </style>
        <script>
            document.addEventListener("readystatechange", async function () {

                if (document.readyState === "loading") {
                    await mostrarLoader();
                    document.getElementById("loaderMsg").innerHTML = "loading";
                }

                if (document.readyState === "interactive") {
                    document.getElementById("loaderMsg").innerHTML = "loading";
                }

                if (document.readyState === "complete") {
                     document.getElementById("loaderMsg").innerHTML = "Cargando";
                }

            });

            // estado actual
            //console.log(document.readyState);

            //imprimir los cambios de estado
            document.addEventListener('readystatechange', () => console.log(document.readyState));
        </script>
    </head>
    <body>
        <div id="loader-wrapper">
            <div id="loader"></div>
            <div class="loaderMsg" id="loaderMsg"></div>
            <div id="logPull"></div>
        </div>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String idBodega = ownsession.getAttribute("cbbodegaId").toString();
                String UserId = (String) ownsession.getAttribute("login.user_id_number");

                ConsultasQuery fac = new ConsultasQuery();
               
                //Parametros Generales
                String idDivision = "20";
                String AgentType = "";
                String idPlantilla = "";
                String namePlantilla = ""; 
                int numDataBacheo = 0;
                int cont = 1;
                
                //Consultar Información de Agente Aduanal
                if (db.doDB(fac.consultarAgenteAduanalCustoms(UserId))) {
                    for (String[] rowA : db.getResultado()) {
                        AgentType = rowA[0];
                        idPlantilla = rowA[1];
                        namePlantilla = rowA[2];
                    }
                }
                
                //Consultar núm de registros para iterar bacheo de información 
                if (db.doDB(fac.consultarDataBacheoInicial(AgentType))) { 
                    for (String[] rowA : db.getResultado()) {
                         numDataBacheo ++; 
                    }
                }
                
                System.out.println("AgentType: " + AgentType);
                System.out.println("idPlantilla: " + idPlantilla);
                System.out.println("namePlantilla: " + namePlantilla);
                System.out.println("numDataBacheo:" + numDataBacheo);
        %>
        <div class="card-body">
            <form id="uploadForm" enctype="multipart/form-data">
                <div class="contenedor">
                    <div class="columna1"><input class="form-control" type="file" id="input-id" name= "input-id" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"></div>
                    <div class="columna2"><button type="button" class="btn btn-primary" title="Subir Plantilla" id="upload_file" name="upload_file" onclick="leerArchivo()"><i class="fa fa-upload"></i></button></div>
                    <div class="columna2"><button type="button" class="btn btn-primary" title="Descargar Plantilla" id="created_file" name="created_file" onclick="logExcel()"><i class="fa fa-download"></i></button></div>
                    <div class="columna4"><button type="button" class="btn btn-primary" title="Limpiar Filtros" id="clear_file" name="clear_file" onclick="clearFiltres()"><i class="fa fa-traffic-light"></i></button><!--<label class="txtColor">Resolución de Pantalla</label>--></div>
                    <div class="columna5"><a class="btn btn-primary text-uppercase" title="Guardado General" onclick="AddPullCustoms()"><i class="fa fa-save"></i></a></div>
                    <div class="columna6"></div>            
                </div> 
            </form>   
            <!--<input class="form-control" type="text" id="rfcSupport" name="rfcSupport"  value=""  autocomplete="off" oninput="validarInput(this)" onkeyup="this.value = this.value.toUpperCase()">
            <pre id="rfcOK" style="font-family: Arial; font-weight: bold; color:#4d73d1;" size="1"></pre>
            -->
            <div class="scroll-container" id="divAMostrarOcultar">
                <div align="center" id="statusResultado" name="statusResultado"></div>
                <div align="center" id="divResultado" name="divResultado"><%=namePlantilla%></div>
            </div>
            <div id="table-scroll" class="table-scroll"></div>
        </div>

        <!-- modal - histórico eta_port_discharge -->
        <div class="modal fade text-start" id="modalSemaforo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header border-0 bg-gray-100">
                        <h3 class="h6 modal-title" id="exampleModalLabel">
                            <div class="card-heading"><img src="../img/Semaforo.webp" width="3%"/>&nbsp;ETA Port Of Discharge Historic</div>
                        </h3>
                        <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <center><div id="idSemaforo"></div></center>
                        <table class="table" id="mytable" name="mytable" width="50%">
                            <thead>
                                <tr>
                                    <th><strong>FECHA ACTIVACION</strong></th>
                                    <th><strong>PRIORIDAD</strong></th>
                                    <th><strong>LOAD TYPE</strong></th>
                                    <th><strong>FECHA ESTIMADA</strong></th>
                                </tr>
                            </thead>
                            <tbody id="AddTableDetalleCustom"></tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!----- modal_eta_port_discharge ----->
        <div class="modal fade text-start" id="modal_show_eta_port_discharge" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="eta_port_discharge" name="eta_port_discharge" type="text" autocomplete="off" onchange="hide_eta_port_discharge(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_pedimento_r1_1er ----->
        <div class="modal fade text-start" id="modal_pedimento_r1_1er" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">

                    <input class="form-control" id="pedimento_r1_1er" name="pedimento_r1_1er" type="text" autocomplete="off" onchange="hide_pedimento_r1_1er(this.value)">

                </div>
            </div>
        </div> 

        <!----- modal_pedimento_r1_2do ----->
        <div class="modal fade text-start" id="modal_pedimento_r1_2do" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">

                    <input class="form-control" id="pedimento_r1_2do" name="pedimento_r1_2do" type="text" autocomplete="off" onchange="hide_pedimento_r1_2do(this.value)">

                </div>
            </div>
        </div>    

        <!----- modal_fecha_recepcion_doc ----->
        <div class="modal fade text-start" id="modal_fecha_recepcion_doc" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">

                    <input class="form-control datepicker" value="" id="fecha_recepcion_doc" name="fecha_recepcion_doc" type="text" autocomplete="off" onchange="hide_fecha_recepcion_doc(this.value)">

                </div>
            </div>
        </div> 

        <!----- modal_fecha_revalidacion ----->
        <div class="modal fade text-start" id="modal_fecha_revalidacion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">

                    <input class="form-control datepicker" value="" id="fecha_revalidacion" name="fecha_revalidacion" type="text" autocomplete="off" onchange="hide_fecha_revalidacion(this.value)">

                </div>
            </div>
        </div> 

        <!----- modal_fecha_previo_origen ----->
        <div class="modal fade text-start" id="modal_fecha_previo_origen" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">

                    <input class="form-control datepicker" value="" id="fecha_previo_origen" name="fecha_previo_origen" type="text" autocomplete="off" onchange="hide_fecha_previo_origen(this.value)">

                </div>
            </div>
        </div> 

        <!----- modal_fecha_previo_destino ----->
        <div class="modal fade text-start" id="modal_fecha_previo_destino" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">

                    <input class="form-control datepicker" value="" id="fecha_previo_destino" name="fecha_previo_destino" type="text" autocomplete="off" onchange="hide_fecha_previo_destino(this.value)">

                </div>
            </div>
        </div> 

        <!----- modal_fecha_resultado_previo ----->
        <div class="modal fade text-start" id="modal_fecha_resultado_previo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">

                    <input class="form-control datepicker" value="" id="fecha_resultado_previo" name="fecha_resultado_previo" type="text" autocomplete="off" onchange="hide_fecha_resultado_previo(this.value)">

                </div>
            </div>
        </div> 

        <!----- modal_proforma_final ----->
        <div class="modal fade text-start" id="modal_proforma_final" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">

                    <input class="form-control datepicker" value="" id="proforma_final" name="proforma_final" type="text" autocomplete="off" onchange="hide_proforma_final(this.value)">

                </div>
            </div>
        </div> 

        <!----- modal_permiso ----->
        <div class="modal fade text-start" id="modal_permiso" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">    
                    <div class="modal-body">
                            <div class="contenedor_modal">
                                <div class="columna1_modal">
                                    <input type="checkbox" id="permiso1" name="permiso1" value="Si" onclick="hide_permiso(this.value)">
                                    <label>SI</label> 
                                </div>
                                <div class="columna2_modal">
                                    <input type="checkbox" id="permiso2" name="permiso2" value="No" onclick="hide_permiso(this.value)">
                                    <label>NO</label> 
                                </div>
                            </div>   
                    </div>
                </div>
            </div>
        </div> 

        <!----- modal_fecha_envio ----->
        <div class="modal fade text-start" id="modal_fecha_envio" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_envio" name="fecha_envio" type="text" autocomplete="off" onchange="hide_fecha_envio(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_recepcion_perm ----->
        <div class="modal fade text-start" id="modal_fecha_recepcion_perm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_recepcion_perm" name="fecha_recepcion_perm" type="text" autocomplete="off" onchange="hide_fecha_recepcion_perm(this.value)"> 
                </div>
            </div>
        </div> 

        <!----- modal_fecha_activacion_perm ----->
        <div class="modal fade text-start" id="modal_fecha_activacion_perm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_activacion_perm" name="fecha_activacion_perm" type="text" autocomplete="off" onchange="hide_fecha_activacion_perm(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_permisos_aut ----->
        <div class="modal fade text-start" id="modal_fecha_permisos_aut" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_permisos_aut" name="fecha_permisos_aut" type="text" autocomplete="off" onchange="hide_fecha_permisos_aut(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_co_pref_arancelaria ----->
        <div class="modal fade text-start" id="modal_co_pref_arancelaria" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="contenedor_modal">
                            <div class="columna1_modal">
                                <input type="checkbox" id="co_pref_arancelaria1" name="co_pref_arancelaria1" value="Si" onclick="hide_co_pref_arancelaria(this.value)">
                                <label>SI</label> 
                            </div>
                            <div class="columna2_modal">
                                <input type="checkbox" id="co_pref_arancelaria2" name="co_pref_arancelaria2" value="No" onclick="hide_co_pref_arancelaria(this.value)">
                                <label>NO</label> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 

        <!----- modal_aplic_pref_arancelaria ----->
        <div class="modal fade text-start" id="modal_aplic_pref_arancelaria" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="contenedor_modal">
                            <div class="columna1_modal">
                                <input type="checkbox" id="aplic_pref_arancelaria1" name="aplic_pref_arancelaria1" value="Si" onclick="hide_aplic_pref_arancelaria(this.value)">
                                <label>SI</label> 
                            </div>
                            <div class="columna2_modal">
                                <input type="checkbox" id="aplic_pref_arancelaria2" name="aplic_pref_arancelaria2" value="No" onclick="hide_aplic_pref_arancelaria(this.value)">
                                <label>NO</label> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 

        <!----- modal_req_uva ----->
        <div class="modal fade text-start" id="modal_req_uva" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="contenedor_modal">
                            <div class="columna1_modal">
                                <input type="checkbox" id="req_uva1" name="req_uva1" value="Si" onclick="hide_req_uva(this.value)">
                                <label>SI</label> 
                            </div>
                            <div class="columna2_modal">
                                <input type="checkbox" id="req_uva2" name="req_uva2" value="No" onclick="hide_req_uva(this.value)">
                                <label>NO</label> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 

        <!----- modal_req_ca ----->
        <div class="modal fade text-start" id="modal_req_ca" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="contenedor_modal">
                            <div class="columna1_modal">
                                <input type="checkbox" id="req_ca1" name="req_ca1" value="Si" onclick="hide_req_ca(this.value)">
                                <label>SI</label> 
                            </div>
                            <div class="columna2_modal">
                                <input type="checkbox" id="req_ca2" name="req_ca2" value="No" onclick="hide_req_ca(this.value)">
                                <label>NO</label> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 

        <!----- modal_fecha_recepcion_ca ----->
        <div class="modal fade text-start" id="modal_fecha_recepcion_ca" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_recepcion_ca" name="fecha_recepcion_ca" type="text" autocomplete="off" onchange="hide_fecha_recepcion_ca(this.value)">               
                </div>
            </div>
        </div> 

        <!----- modal_fecha_doc_completos ----->
        <div class="modal fade text-start" id="modal_fecha_doc_completos" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_doc_completos" name="fecha_doc_completos" type="text" autocomplete="off" onchange="hide_fecha_doc_completos(this.value)">             
                </div>
            </div>
        </div> 

        <!----- modal_fecha_pago_pedimento ----->
        <div class="modal fade text-start" id="modal_fecha_pago_pedimento" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker-pedimento" value="" id="fecha_pago_pedimento" name="fecha_pago_pedimento" type="text" autocomplete="off" onchange="hide_fecha_pago_pedimento(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_solicitud_transporte ----->
        <div class="modal fade text-start" id="modal_fecha_solicitud_transporte" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_solicitud_transporte" name="fecha_solicitud_transporte" type="text" autocomplete="off" onchange="hide_fecha_solicitud_transporte(this.value)">
                </div>
            </div>
        </div>

        <!----- modal_fecha_modulacion ----->
        <div class="modal fade text-start" id="modal_fecha_modulacion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker-modulacion" value="" id="fecha_modulacion" name="fecha_modulacion" type="text" autocomplete="off" onchange="hide_fecha_modulacion(this.value)">
                </div>
            </div>
        </div> 

        <!-- modal_modalidad -->
        <div class="modal fade text-start" id="modal_modalidad" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="contenedor_modal">
                            <div class="columna1_modal">
                                <input type="checkbox" id="modalidad1" name="modalidad1" value="Camión" onclick="hide_modalidad(this.value)">
                                <label>CAMION</label> 
                            </div>
                            <div class="columna2_modal">
                                <input type="checkbox" id="modalidad2" name="modalidad2" value="Tren" onclick="hide_modalidad(this.value)">
                                <label>TREN</label> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>  

        <!----- modal_resultado_modulacion ----->
        <div class="modal fade text-start" id="modal_resultado_modulacion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="contenedor_modal">
                            <div class="columna1_modal">
                                <input type="checkbox" id="resultado_modulacion1" name="resultado_modulacion1" value="Verde" onclick="hide_resultado_modulacion(this.value)">
                                <label>VERDE</label> 
                            </div>
                            <div class="columna2_modal">
                                <input type="checkbox" id="resultado_modulacion2" name="resultado_modulacion2" value="Rojo" onclick="hide_resultado_modulacion(this.value)">
                                <label>ROJO</label> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 

        <!----- modal_fecha_reconocimiento ----->
        <div class="modal fade text-start" id="modal_fecha_reconocimiento" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_reconocimiento" name="fecha_reconocimiento" type="text" autocomplete="off" onchange="hide_fecha_reconocimiento(this.value)">
                </div>
            </div>
        </div>

        <!----- modal_fecha_liberacion ----->
        <div class="modal fade text-start" id="modal_fecha_liberacion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_liberacion" name="fecha_liberacion" type="text" autocomplete="off" onchange="hide_fecha_liberacion(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_retencion_aut ----->
        <div class="modal fade text-start" id="modal_fecha_retencion_aut" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_retencion_aut" name="fecha_retencion_aut" type="text" autocomplete="off" onchange="hide_fecha_retencion_aut(this.value)"> 
                </div>
            </div>
        </div>  

        <!----- modal_fecha_liberacion_aut ----->
        <div class="modal fade text-start" id="modal_fecha_liberacion_aut" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_liberacion_aut" name="fecha_liberacion_aut" type="text" autocomplete="off" onchange="hide_fecha_liberacion_aut(this.value)">
                </div>
            </div>
        </div>

        <!----- modal_llegada_a_nova ----->
        <div class="modal fade text-start" id="modal_llegada_a_nova" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="llegada_a_nova" name="llegada_a_nova" type="text" autocomplete="off" onchange="hide_llegada_a_nova(this.value)">
                </div>
            </div>
        </div>

        <!----- modal_llegada_a_globe_trade_sd ----->
        <div class="modal fade text-start" id="modal_llegada_a_globe_trade_sd" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="llegada_a_globe_trade_sd" name="llegada_a_globe_trade_sd" type="text" autocomplete="off" onchange="hide_llegada_a_globe_trade_sd(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_archivo_m ----->
        <div class="modal fade text-start" id="modal_fecha_archivo_m" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_archivo_m" name="fecha_archivo_m" type="text" autocomplete="off" onchange="hide_fecha_archivo_m(this.value)">

                </div>
            </div>
        </div> 

        <!----- modal_fecha_solicit_manip ----->
        <div class="modal fade text-start" id="modal_fecha_solicit_manip" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_solicit_manip" name="fecha_solicit_manip" type="text" autocomplete="off" onchange="hide_fecha_solicit_manip(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_vencim_manip ----->
        <div class="modal fade text-start" id="modal_fecha_vencim_manip" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_vencim_manip" name="fecha_vencim_manip" type="text" autocomplete="off" onchange="hide_fecha_vencim_manip(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_confirm_clave_pedim ----->
        <div class="modal fade text-start" id="modal_fecha_confirm_clave_pedim" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_confirm_clave_pedim" name="fecha_confirm_clave_pedim" type="text" autocomplete="off" onchange="hide_fecha_confirm_clave_pedim(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_recep_increment ----->
        <div class="modal fade text-start" id="modal_fecha_recep_increment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_recep_increment" name="fecha_recep_increment" type="text" autocomplete="off" onchange="hide_fecha_recep_increment(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_fecha_vencim_inbound ----->
        <div class="modal fade text-start" id="modal_fecha_vencim_inbound" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_vencim_inbound" name="fecha_vencim_inbound" type="text" autocomplete="off" onchange="hide_fecha_vencim_inbound(this.value)">
                </div>
            </div>
        </div> 

        <!----- modal_transferencia ----->
        <div class="modal fade text-start" id="modal_transferencia" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="contenedor_modal">
                            <div class="columna1_modal">
                                <input type="checkbox" id="transferencia1" name="transferencia1" value="Si" onclick="hide_transferencia(this.value)">
                                <label>SI</label> 
                            </div>
                            <div class="columna2_modal">
                                <input type="checkbox" id="transferencia2" name="transferencia2" value="No" onclick="hide_transferencia(this.value)">
                                <label>NO</label> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 

        <!----- modal_fecha_inicio_etiquetado ----->
        <div class="modal fade text-start" id="modal_fecha_inicio_etiquetado" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_inicio_etiquetado" name="fecha_inicio_etiquetado" type="text" autocomplete="off" onchange="hide_fecha_inicio_etiquetado(this.value)">
                </div>
            </div>
        </div> 
        
        <!----- modal_fecha_inicio_etiquetado ----->
        <div class="modal fade text-start" id="modal_fecha_termino_etiquetado" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <input class="form-control datepicker" value="" id="fecha_termino_etiquetado" name="fecha_termino_etiquetado" type="text" autocomplete="off" onchange="hide_fecha_termino_etiquetado(this.value)">
                </div>
            </div>
        </div> 
        
        <!-- Parametros - Customs -->
        <input type="hidden" id="numCustoms" name="numCustoms" value="<%=cont%>">
        <input type="hidden" id="idAgenteAduanal" name="idAgenteAduanal" value="<%=AgentType%>">

        <!-- Parametros - Generación de Excel -->
        <input type="hidden" name="idPlantilla" value="<%=idPlantilla%>" id="idPlantilla"/>
        <input type="hidden" name="idOpcion" value="1" id="idOpcion"/>
        <input type="hidden" name="idLenguaje" value="1" id="idLenguaje"/>
        <input type="hidden" name="idDivision" value="<%=idDivision%>" id="idDivision"/>                                     
        <input type="hidden" name="idBodega" value="<%=idBodega%>" id="idBodega"/>
        <input type="hidden" name="idAction" value="../plantillaExcel" id="idAction"/>
        <img src="../img/loadingCloud.gif" id="idClouding" width="50px" height="50px" name="idClouding" title="Clouding" style="display: none; height: 50px; width: 50px;"/>
        <script>
            //Parametros: Validaciones
            let numBacheo =       '<%=numDataBacheo%>';
            let idAgenteAduanal = '<%=AgentType%>'; 
            let fechaActual =     '<%=fecha_actual%>';
            let checkboxOn;
            let contSubfiltros = 0;
            let dataEnd = "";
            
           //Inicializar bloqueo de controles para descarga/subida de plantilla:
           document.getElementById("input-id").disabled=true; 
           document.getElementById("upload_file").disabled=true; 
           document.getElementById("created_file").disabled=true;
        </script>
        <!-- JavaScript files-->
        <script src="<%=request.getContextPath()%>/lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Sweetalert -->
        <script src="<%=request.getContextPath()%>/lib/SweetAlert1.1.3/js/sweetalert.min.js" type="text/javascript"></script>
        <!-- Actions js -->
        <script src="<%=request.getContextPath()%>/lib/validationsInbound/customs/customsForms.js" type="text/javascript"></script>
        <!-- Validaciones Celdas -->
        <script src="<%=request.getContextPath()%>/lib/validationsInbound/customs/validacionesCeldas.js" type="text/javascript"></script>
        <!-- Elementos Html Celdas -->
        <script src="<%=request.getContextPath()%>/lib/validationsInbound/customs/elementosCeldas.js" type="text/javascript"></script>
        <!-- lectura de excel validación de celdas -->
        <script src="<%=request.getContextPath()%>/lib/js/xlsx.full.min.js" type="text/javascript"></script>
        <!-- Upload/Download Excel -->
        <script src="<%=request.getContextPath()%>/lib/validationsInbound/customs/upload_file_customs.js" type="text/javascript"></script>
        <!-- Validar Excel - Log Errores -->
        <script src="<%=request.getContextPath()%>/lib/validationsInbound/customs/validateUploadExcel.js" type="text/javascript"></script>
        <!-- Filtrer Checkbox -->
        <script src="<%=request.getContextPath()%>/lib/validationsInbound/customs/filtrerCheckboxv2.js" type="text/javascript"></script>
        <!-- Calendarios -->
        <script src="<%=request.getContextPath()%>/lib/calendarios/js/flatpickr.min.js" type="text/javascript"></script>
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    </body>
    <%
        } catch (NullPointerException e) {
            System.out.println("Error:" + e);
            out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            out.println("<script>window.close();</script>");
        } catch (Exception e) {
            out.println("Excepcion revise por favor! " + e);
            e.printStackTrace();
        }
    %>
</html>
