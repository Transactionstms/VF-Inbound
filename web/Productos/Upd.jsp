<%-- 
    Document   : Upd
    Created on : 29/12/2021, 05:37:41 PM
    Author     : Desarrollo Tacts
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.onest.train.consultas.ConsultasQuery"%>
<%@page import="com.onest.security.menu.*" %>
<%@page import="com.onest.net.*" %>
<%@page import="com.onest.oracle.*" %>
<%@page import="com.onest.misc.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Date date = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    String fecha = formato.format(date);
    String[] par = fecha.split("/");
    String part3 = par[2];
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Nuevo Producto</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">
        <!-- Google fonts - Popppins for copy-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&amp;display=swap" rel="stylesheet">
        <!-- VanillaJs Datepicker CSS-->
        <link rel="stylesheet" href="../lib/vendor/vanillajs-datepicker/css/datepicker-bs4.min.css">
        <!-- No UI Slider-->
        <link rel="stylesheet" href="../lib/vendor/nouislider/nouislider.css">
        <!-- Prism Syntax Highlighting-->
        <link rel="stylesheet" href="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.css">
        <link rel="stylesheet" href="../lib/vendor/prismjs/themes/prism-okaidia.css">
        <!-- The Main Theme stylesheet (Contains also Bootstrap CSS)-->
        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
        <!-- Custom stylesheet - for your changes-->
        <link rel="stylesheet" href="../lib/css/custom.css">
        <!-- Favicon-->
        <link rel="shortcut icon" href="../lib/img/favicon.png">
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                
                ConsultasQuery fac = new ConsultasQuery();
                
                String idProducto = request.getParameter("clvreg_id");
        %>
        <!-- navbar-->
        <header class="header"></header>
        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <div class="page-header">
                        <h3 class="page-heading">Nuevo Producto</h3>
                    </div>
                    <section>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <h4 class="card-heading">Datos</h4>
                                    </div>
                                    <!-- Datos -->
                                    <div class="card-body text-muted">
                                            <div class="row">
                                                <div class="col-lg-3">
                                                    <div class="mb-3">  
                                                        <div class="form-check">
                                                            <input class="form-check-input" id="customRadioInline1" name="check_registro" type="radio" name="customRadioInline1" value="1" onclick="tiporegistro(this.value);" checked="true">
                                                            <label class="custom-control-label" for="customRadioInline1">Producto</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-9">
                                                    <div class="mb-3">  
                                                        <div class="form-check">
                                                            <input class="form-check-input" id="customRadioInline1" name="check_registro" type="radio" name="customRadioInline1" value="2" onclick="tiporegistro(this.value);">
                                                            <label class="custom-control-label" for="customRadioInline1">Servicio</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <input class="form-control input-datepicker-multiple" type="text" id="codigo" name="codigo" placeholder="Código" autocomplete="off" onkeypress="return soloNumeros(event)">
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control"  type="text" id="descripcion" name="descripcion" autocomplete="off">
                                                <label for="floatingInput">Descripción</label>
                                            </div>
                                            <div class="mb-3">
                                                <div id="flotanteValorUnitario" style="margin-top:1em; display:none; border-spacing: 5px;">
                                                    <label class="pedimentoTag btn btn-info btn-rounded btn-sm">Valor Unitario</label>
                                                </div>
                                                <div class="input-group mb-3"><span class="input-group-text">$</span>
                                                    <input class="form-control" type="number" id="valor_unitario" name="valor_unitario" placeholder="0.00" autocomplete="off" onclick="mostarTitulo()">
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <input class="form-control input-datepicker-multiple" type="text" id="unidad_medida" name="unidad_medida" placeholder="Unidad de medida" autocomplete="off">
                                            </div>

                                            <div class="row"> <div class="col-lg-6"> <div class="mb-3"> </div> </div> </div>
                                            <div class="row"> <div class="col-lg-6"> <div class="mb-3"> </div> </div> </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Impuestos -->
                            <div class="col-lg-6">
                                <!-- Datepickers-->
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <h4 class="card-heading">Impuestos</h4>
                                    </div>
                                    <div class="card-body text-muted">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="mb-3">  
                                                        <div class="form-check">
                                                            <input class="form-check-input" id="check_iva" name="check_iva" type="checkbox" onclick="habilitarIva()">
                                                            <label class="custom-control-label" for="customCheck1" style="font-weight: bold">IVA</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="mb-3">  
                                                        <div class="form-check">
                                                            <input class="form-check-input" id="check_ivaret" name="check_ivaret" type="checkbox" onclick="habilitarIvaRet()">
                                                            <label class="custom-control-label" for="customCheck1" style="font-weight: bold">IVA Ret.</label>
                                                            <div id="msjdecimales1" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="mb-3">  
                                                        <div class="input-group">
                                                            <select class="form-control" id="iva_id" name="iva_id" disabled="true">
                                                                <option value="0" disabled selected>-Seleccione un porcentaje-</option>
                                                                <%
                                                                    if (db.doDB(fac.tasaIva())) {
                                                                        for (String[] row : db.getResultado()) {
                                                                            out.println("<option value=\"" + row[0] + "\">" + row[1] + "</option>");
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                            <span class="input-group-text">%</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="mb-3">  
                                                        <div class="input-group">
                                                            <input class="form-control" type="text" id="iva_ret" name="iva_ret" value="0.00" maxlength="5" onblur="validateDecimal(1)" autocomplete="off" disabled="true"><span class="input-group-text">%</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="mb-3">  
                                                        <div class="form-check">
                                                            <input class="form-check-input" id="check_ieps" name="check_ieps" type="checkbox" onclick="habilitarIeps()">
                                                            <label class="custom-control-label" for="customCheck1" style="font-weight: bold">IEPS</label>
                                                            <div id="msjdecimales3" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-4">
                                                            <div class="mb-3">  
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="radio" id="tipo_ieps" name="tipo_ieps" value="1" onclick="tipoIeps(this.value)" checked="true">
                                                                    
                                                                    <label class="custom-control-label" for="customRadioInline1">Tasa</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <div class="mb-3">  
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="radio" id="tipo_ieps" name="tipo_ieps" value="2" onclick="tipoIeps(this.value)">
                                                                    <label class="custom-control-label" for="customRadioInline1">Cuota</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="mb-3">  
                                                        <div class="form-check">
                                                            <input class="form-check-input" id="check_isr" type="checkbox" name="check_isr" onclick="habilitarIsr()">
                                                            <label class="custom-control-label" for="customCheck1" style="font-weight: bold">ISR</label>
                                                            <div id="msjdecimales2" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="mb-3">
                                                            <div class="input-group">
                                                                <input class="form-control" type="text" id="isr" name="isr" value="0.00" maxlength="5" onblur="validateDecimal(2)" autocomplete="off" disabled="true"><span class="input-group-text">%</span>
                                                            </div>
                                                        </div>  
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="mb-3">  
                                                        <div class="input-group" id="flotante_ieps1">
                                                            <select class="form-control" id="iepstasa_id" name="iepstasa_id" disabled="true">
                                                                <option value="0" disabled selected>-Seleccione un porcentaje-</option>
                                                                <%
                                                                    if (db.doDB(fac.tasaIeps())) {
                                                                        for (String[] row : db.getResultado()) {
                                                                            out.println("<option value=\"" + row[0] + "\">" + row[1] + "</option>");
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                            <span class="input-group-text">%</span>
                                                        </div>
                                                        <div class="input-group" id="flotante_ieps2" style="display:none;">
                                                            <input class="form-control" type="text" id="ieps_cuota" name="ieps_cuota" value="0.00" maxlength="5" onblur="validateDecimal(3)" autocomplete="off" disabled="true">
                                                            <span class="input-group-text">%</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="mb-3">  
                                                        <div class="form-check">
                                                            <input class="form-check-input" id="check_iepsret" type="checkbox" name="check_iepsret" onclick="habilitariepsRet()">
                                                            <label class="custom-control-label" for="customCheck1" style="font-weight: bold">IEPS Ret.</label>
                                                            <div id="msjdecimales4" style="color:#3b83bd;"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="mb-3"> </div>
                                                </div>
                                                <div class="col-lg-6"> 
                                                    <div class="row">
                                                        <div class="col-lg-4">
                                                            <div class="mb-3">  
                                                                <div class="form-check">
                                                                    <input class="form-check-input" id="tipo_iepsret" type="radio" name="tipo_iepsret" value="1" onclick="tipoiepsRet(this.value)" checked="true">
                                                                    <label class="custom-control-label" for="customRadioInline1">Tasa</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <div class="mb-3">  
                                                                <div class="form-check">
                                                                    <input class="form-check-input" id="tipo_iepsret" type="radio" name="tipo_iepsret" value="2" onclick="tipoiepsRet(this.value)">
                                                                    <label class="custom-control-label" for="customRadioInline1">Cuota</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="mb-3"> </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="mb-3">  
                                                        <div class="input-group" id="flotante_iepsret1">
                                                            <select class="form-control" id="epsret_id" name="epsret_id" disabled="true">
                                                                <option value="0" disabled selected>-Seleccione un porcentaje-</option>
                                                                <%
                                                                    if (db.doDB(fac.tasaIepsRet())) {
                                                                        for (String[] row : db.getResultado()) {
                                                                            out.println("<option value=\"" + row[0] + "\">" + row[1] + "</option>");
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                            <span class="input-group-text">%</span>
                                                        </div>
                                                        <div class="input-group" id="flotante_iepsret2" style="display:none;">
                                                            <input class="form-control" type="text" id="ieps_ret_cuota" name="ieps_ret_cuota" value="0.00" maxlength="5" onblur="validateDecimal(4)" autocomplete="off"><span class="input-group-text">%</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                    </div>
                                </div>
                            </div>  
                        </div>
                        
                        <!-- Datos SAT -->
                        <div class="card mb-4">
                            <div class="card-header">
                                <h4 class="card-heading">Datos SAT</h4>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="mb-3">
                                            <div class="input-group mb-3">
                                                <input class="form-control" type="text" id="rel_clvsat_id" name="rel_clvsat_id" placeholder="Clave de producto o servicio" autocomplete="off" onkeypress="return soloNumeros(event)">
                                                <button class="btn btn-info" id="button-addon2" type="button" data-bs-toggle="modal" data-bs-target="#modalconceptos">Buscar...</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="mb-3">
                                            <div class="input-group mb-3">
                                                <input class="form-control" type="text" id="unidad_sat_desc" name="unidad_sat_desc" placeholder="Clave Unidad" autocomplete="off">
                                                <button class="btn btn-info" id="button-addon2" type="button" data-bs-toggle="modal" data-bs-target="#modalclvUnidad">Buscar...</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- modal - Catálogo SAT de productos --> 
                        <div class="modal fade text-start" id="modalconceptos" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header border-0 bg-gray-100">
                                        <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Catálogo SAT de Productos o Servicios</h3>
                                        <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <select class="form-control" id="conceptos" name="conceptos" onchange="search_divisiones(this.value)">
                                                <option value="0" disabled selected>-- Seleccione una descripción --</option>
                                                <%
                                                    if (db.doDB(fac.consultarClasificacionSAT())) {
                                                        for (String[] row : db.getResultado()) {
                                                            out.println("<option value=\"" + row[0] + "\" >" + row[1] + "</option>");
                                                        }
                                                    }
                                                %>
                                            </select>
                                            <div id="val_conceptos" style="color:#3b83bd;"></div>
                                        </div>

                                            <div class="mb-3" id="divisiones"></div>
                                            <div class="mb-3" id="grupos"></div>
                                            <div class="mb-3" id="clases"></div>
                                            <div class="mb-3" id="claves"></div>

                                        <!--Detalle clave_sat-->
                                        <div id="producto_ser_sat"></div>
                                        <br>
                                        <div class="mb-3">
                                            <button class="btn btn-success" id="button-addon2" type="button">Buscar</button> <button type="button" class="btn btn-warning" onclick="cleanFormularioClaveSAT()">Limpiar Resultados</button>
                                        </div>
                                    </div>       
                                    <div class="modal-footer border-0 bg-gray-100">
                                        <button class="btn btn-success" type="button" data-bs-dismiss="modal">Confirmar</button>
                                        &nbsp;&nbsp;
                                        <button class="btn btn-secondary" type="button" data-bs-dismiss="modal">Cerrar</button>
                                    </div>
                                </div>
                            </div>
                        </div>   
                        <!-- modal - clave unidad --> 
                        <div class="modal fade text-start" id="modalclvUnidad" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header border-0 bg-gray-100">
                                        <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Catálogo SAT de Claves de Unidad</h3>
                                        <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">  
                                            <div class="col"> 
                                                <div class="preload-wrapper">
                                                    <table class="table table-hover table-borderless align-middle mb-0" id="unidadDatatable">
                                                        <thead>
                                                            <tr>
                                                                <th>c_ClaveUnidad - Descripción</th>
                                                                <th> </th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="AddTableClave" name="AddTableClave">
                                                            <%
                                                                if (db.doDB(fac.consultarUnidadMedidaSAT())) {
                                                                    for (String[] row : db.getResultado()) {
                                                            %>
                                                            <tr>
                                                                <td><%=row[1]%> - <%=row[2]%></td>
                                                                <td><a class="me-3 text-lg text-info" data-bs-dismiss="modal" onclick="listarUnidadSAT('<%=row[0]%>','<%=row[1]%>')"><i class="fas fa-arrow-alt-circle-right"></i></a></td>
                                                            </tr>
                                                            <%     
                                                                    }
                                                                }
                                                            %>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div> 
                                    </div>
                                    <div class="modal-footer border-0 bg-gray-100">
                                        <button class="btn btn-secondary" type="button" data-bs-dismiss="modal">Cerrar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <!-- parametros inicio/hidden -->
                        <input type="hidden" id="tipo_registro" name="tipo_registro">
                        <input type="hidden" id="tipo_ieps" name="tipo_ieps">
                        <input type="hidden" id="tipo_ieps_ret" name="tipo_ieps_ret">
                        <input type="hidden" id="unidad_sat_id" name="unidad_sat_id">
                        <input type="hidden" id="id_producto" name="id_producto" value="<%=idProducto%>">
                        
                        <div class="row">
                            <div class="col-lg-10">
                                <a class="btn btn-primary" href="List.jsp"><i class="fas fa-long-arrow-alt-left"></i>&nbsp;Regresar al listado</a>
                            </div>
                            <div class="col-lg-2">
                                <a class="btn btn-primary" onclick="AddProductos()"> <i class="fa fa-save"> </i>&nbsp;Guardar</a>
                            </div>
                        </div>
                    </section>
                </div>
                <!-- footer -->
                <footer class="footer bg-white shadow align-self-end py-3 px-xl-5 w-100">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-6 text-center text-md-start fw-bold">
                                <p class="mb-2 mb-md-0 fw-bold">Tacts S de RL de CV &copy; <%=part3%></p>
                            </div>
                            <div class="col-md-6 text-center text-md-end text-gray-400">
                                <p class="mb-0">Version 1.1.0</p>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>

        <script>
            window.onload=function() {
                 detailsConcepto();
            }

            function detailsConcepto(){

                    let at1 = document.getElementById("id_producto").value;

                    fetch("<%=request.getContextPath()%>/ConsultarDataProducto?idProducto=" + at1, {
                          method: 'POST', 
                          }) .then(r => r.text())
                             .then(data => {
                                 let input = data.split("*");
                                 let a0t = input[0]; //CLVREG_ID
                                 let a1t = input[1]; //TIPO_REGISTRO
                                 let a2t = input[2]; //CODIGO 
                                 let a3t = input[3]; //DESCRIPCION
                                 let a4t = input[4]; //VALOR_UNITARIO
                                 let a5t = input[5]; //UNIDAD_MEDIDA
                                 let a6t = input[6]; //REL_CLVSAT_ID
                                 let a7t = input[7]; //CLAVE_UNIDAD

                                /*if(a1t == "1"){ //Producto
                                     document.getElementById("check_registro").value = 1;
                                 }else{          //Servicio
                                     document.getElementById("check_registro").value = 2;
                                 }*/
                                 document.getElementById("codigo").value = a2t;           //CODIGO
                                 document.getElementById("descripcion").value = a3t;      //DESCRIPCION
                                 document.getElementById("valor_unitario").value = a4t;   //VALOR_UNITARIO
                                 document.getElementById("unidad_medida").value = a5t;    //UNIDAD_MEDIDA
                                 document.getElementById("rel_clvsat_id").value = a6t;    //REL_CLVSAT_ID
                                 document.getElementById("unidad_sat_desc").value = a7t;  //CLAVE_UNIDAD
                                 

                          }).catch(error => console.log(error));
                }
        </script>
        <script>
            let tipo_impuesto = 0;   //traslado ó retención
            let impuesto = 0;        //isr, iva e ieps  
            let tipo_factor = 3;     //tasa, cuota y exento
            let tipo_porcentaje = 0; //porcentaje fijo y porcentaje abierto
            
            function tiporegistro(id_reg){
                
                document.getElementById("tipo_registro").value = id_reg;
                
                document.getElementById("codigo").value = '';
                document.getElementById("descripcion").value = '';
                document.getElementById("valor_unitario").value = '';
                document.getElementById("unidad_medida").value = '';
            }
            
            function habilitarIva(){
                if(document.getElementById("check_iva").checked){
                   document.getElementById("iva_id").disabled = false;
                   tipo_impuesto = 1;
                   impuesto = 2;
                   tipo_factor = 1;  
                }else{
                   document.getElementById("iva_id").disabled = true;
                   document.getElementById("iva_id").value = '';
                   tipo_impuesto = 0;
                   impuesto = 0;
                   tipo_factor = 0;  
                }
            }
            
            function habilitarIeps(){
                if(document.getElementById("check_ieps").checked){
                   document.getElementById("iepstasa_id").disabled = false;
                   document.getElementById("ieps_cuota").disabled = false;
                   tipo_impuesto = 1;
                   impuesto = 3;
                }else{
                   document.getElementById("iepstasa_id").disabled = true;
                   document.getElementById("ieps_cuota").disabled = true;
                   document.getElementById("iepstasa_id").value = '';
                   document.getElementById("ieps_cuota").value = '';
                   tipo_impuesto = 0;
                   impuesto = 0;
                }
            }
            
            function habilitarIvaRet(){
                if(document.getElementById("check_ivaret").checked){
                   document.getElementById("iva_ret").disabled = false;
                   tipo_impuesto = 2;
                   impuesto = 2;
                }else{
                   document.getElementById("iva_ret").disabled = true;
                   document.getElementById("iva_ret").value = '';
                   tipo_impuesto = 0;
                   impuesto = 0;
                }
            }
            
            function habilitarIsr(){
                if(document.getElementById("check_isr").checked){
                   document.getElementById("isr").disabled = false;
                   tipo_impuesto = 2;
                   impuesto = 1;
                }else{
                   document.getElementById("isr").disabled = true;
                   document.getElementById("isr").value = '';
                   tipo_impuesto = 0;
                   impuesto = 0;
                }
            }
            
            function habilitariepsRet(){
                if(document.getElementById("check_iepsret").checked){
                   document.getElementById("epsret_id").disabled = false;
                   document.getElementById("ieps_ret_cuota").disabled = false;
                   tipo_impuesto = 2;
                   impuesto = 3;
                }else{
                   document.getElementById("epsret_id").disabled = true;
                   document.getElementById("ieps_ret_cuota").disabled = true;
                   document.getElementById("epsret_id").value = '';
                   document.getElementById("ieps_ret_cuota").value = '';
                   tipo_impuesto = 0;
                   impuesto = 0;
                }
            }
            
            function tipoIeps(id_ieps){
                if(id_ieps == '1'){ //tasa
                    div = document.getElementById('flotante_ieps1');
                    div.style.display = '';
                    div = document.getElementById('flotante_ieps2');
                    div.style.display = 'none';
                    document.getElementById("ieps_cuota").value = '';
                    document.getElementById("msjdecimales3").innerHTML = "";
                    tipo_factor = 1;  
                    tipo_porcentaje = 1;
                }else{              //cuota
                    div = document.getElementById('flotante_ieps1');
                    div.style.display = 'none'; 
                    div = document.getElementById('flotante_ieps2');
                    div.style.display = '';
                    document.getElementById("iepstasa_id").value = '';
                    tipo_factor = 2;  
                    tipo_porcentaje = 2;
                }
            }
            
            function tipoiepsRet(id_iepsret){
                if(id_iepsret == '1'){ //tasa
                    div = document.getElementById('flotante_iepsret1');
                    div.style.display = '';
                    div = document.getElementById('flotante_iepsret2');
                    div.style.display = 'none';
                    document.getElementById("ieps_ret_cuota").value = '';
                    document.getElementById("msjdecimales4").innerHTML = "";
                    tipo_factor = 1;  
                    tipo_porcentaje = 1;
                }else{                  //cuota
                    div = document.getElementById('flotante_iepsret1');
                    div.style.display = 'none'; 
                    div = document.getElementById('flotante_iepsret2');
                    div.style.display = '';
                    document.getElementById("epsret_id").value = '';
                    tipo_factor = 2;  
                    tipo_porcentaje = 2;
                }
            }
            
            function search_divisiones(valor){
                fetch("<%=request.getContextPath()%>/ConsultarClaveSAT?tipo=1&valor=" + valor, {
                  method: 'POST', 
                  }) .then(r => r.text())
                     .then(data => { 
                        document.getElementById('divisiones').innerHTML = data;
                  }).catch(error => console.log(error));
            }
            
            function search_grupos(valor){
                fetch("<%=request.getContextPath()%>/ConsultarClaveSAT?tipo=2&valor=" + valor, {
                  method: 'POST', 
                  }) .then(r => r.text())
                     .then(data => { 
                        document.getElementById('grupos').innerHTML = data;
                  }).catch(error => console.log(error));
            }
            
            function search_clases(valor){
                fetch("<%=request.getContextPath()%>/ConsultarClaveSAT?tipo=3&valor=" + valor, {
                  method: 'POST', 
                  }) .then(r => r.text())
                     .then(data => { 
                        document.getElementById('clases').innerHTML = data;
                  }).catch(error => console.log(error));
            }
            
            function search_claves(valor){
                fetch("<%=request.getContextPath()%>/ConsultarClaveSAT?tipo=4&valor=" + valor, {
                  method: 'POST', 
                  }) .then(r => r.text())
                     .then(data => { 
                        document.getElementById('claves').innerHTML = data;
                  }).catch(error => console.log(error));
            }
            
            function listarClaveSAT(id_clvsat){
                document.getElementById("rel_clvsat_id").value = id_clvsat;
                document.getElementById("producto_ser_sat").innerHTML = "Clave SAT: " + id_clvsat;
            }
            
            function listarUnidadSAT(idUnidadsat, descUnidaSat){
                document.getElementById('unidad_sat_desc').value = descUnidaSat;
                document.getElementById('unidad_sat_id').value = idUnidadsat;
            }
            
            function alertclose() {
                setTimeout(function () {
                    swal.close();
                }, 2000);
            }
            
            function soloNumeros(e) {
                let key = window.Event ? e.which : e.keyCode
                return (key >= 48 && key <= 57)
            }
            
            function mostarTitulo(){
               div = document.getElementById('flotanteValorUnitario');
               div.style.display = '';  
            }
            
            function validateDecimal(tipo) {
                
                let valor;
                let tipomsj = "";
                
                if(tipo == '1'){
                    valor = document.getElementById("iva_ret").value; 
                    tipomsj= "msjdecimales1";
                }else if(tipo == '2'){
                    valor = document.getElementById("isr").value;
                    tipomsj= "msjdecimales2";
                }else if(tipo == '3'){
                    valor = document.getElementById("ieps_cuota").value; 
                    tipomsj= "msjdecimales3";
                }else if(tipo == '4'){
                    valor = document.getElementById("ieps_ret_cuota").value; 
                    tipomsj= "msjdecimales4";
                }    
                
                let RE = /^\d*,?\d*$/;
                if (RE.test(valor)) {
                    document.getElementById(tipomsj).innerHTML = "Formato invalido";
                }else{
                    document.getElementById(tipomsj).innerHTML = "";
                }
            }
            
            function AddProductos() {
                let id_prod = document.getElementById("id_producto").value;
                let tipo_registro = document.getElementById("tipo_registro").value;
                let codigo = document.getElementById("codigo").value;
                let descripcion = document.getElementById("descripcion").value;
                let valor_unitario = document.getElementById("valor_unitario").value;
                let unidad_medida = document.getElementById("unidad_medida").value;
                let iva_id = document.getElementById("iva_id").value;
                let iva_ret = document.getElementById("iva_ret").value;
                let iepstasa_id = document.getElementById("iepstasa_id").value;
                let ieps_cuota = document.getElementById("ieps_cuota").value;
                let isr = document.getElementById("isr").value;
                let epsret_id = document.getElementById("epsret_id").value;
                let ieps_ret_cuota = document.getElementById("ieps_ret_cuota").value;
                let rel_clvsat_id = document.getElementById("rel_clvsat_id").value;
                let unidad_sat_id = document.getElementById("unidad_sat_id").value;

                if(codigo == ""){ swal("Error", "Agregue al menos un número de código", "error"); alertclose(); return false; }
                if(descripcion == ""){ swal("Error", "La descripción del producto es inválida", "error"); alertclose(); return false; }
                if(valor_unitario == ""){ swal("Error", "Valor unitario invalido", "error"); alertclose(); return false; }
                
                /*Tipo Impuesto: Traslado*/
                if(document.getElementById("check_iva").checked){
                  if(iva_id == null || iva_id == 0){ swal("", "Seleccione el iva", "info"); alertclose(); return false; }  
                }
                
                /*if(document.getElementById("check_ieps").checked){
                }*/
                
                /*Tipo Impuesto: Retención*/
                if(document.getElementById("check_ivaret").checked){ 
                  if(iva_ret == "" || iva_id < 0){ swal("", "Ingrese iva retención", "info"); alertclose(); return false; }
                }
                
                if(document.getElementById("check_isr").checked){
                  if(isr == ""){ swal("", "Ingrese isr", "info"); alertclose(); return false; }
                }
                
                /*if(document.getElementById("check_iepsret").checked){
                }*/
                
                if(rel_clvsat_id == ""){ swal("Error", "Ingrese clave de producto", "error"); alertclose(); return false; }
                if(unidad_sat_id == ""){ swal("Error", "Ingrese clave de unidad", "error"); alertclose(); return false; }
                
                fetch("<%=request.getContextPath()%>/ModificarProductos?id_producto="+ id_prod
                                                                    "&tipo_registro=" + tipo_registro +
                                                                    "&codigo=" + codigo +
                                                                    "&descripcion=" + descripcion +
                                                                    "&valor_unitario=" + valor_unitario +
                                                                    "&unidad_medida=" + unidad_medida +
                                                                    "&iva_id=" + iva_id +
                                                                    "&iva_ret=" + iva_ret +
                                                                    "&iepstasa_id=" + iepstasa_id +
                                                                    "&ieps_cuota=" + ieps_cuota +
                                                                    "&isr=" + isr +
                                                                    "&epsret_id=" + epsret_id +
                                                                    "&ieps_ret_cuota=" + ieps_ret_cuota +
                                                                    "&rel_clvsat_id=" + rel_clvsat_id +
                                                                    "&unidad_sat_id=" + unidad_sat_id +
                                                                    "&tipo_impuesto=" + tipo_impuesto +
                                                                    "&impuesto=" + impuesto +
                                                                    "&tipo_factor=" + tipo_factor +
                                                                    "&tipo_porcentaje=" + tipo_porcentaje, {
                        method: 'POST',
                        }).then(r => r.text())
                        .then(data => {

                            if (data === "true") {
                                 swal("", "Registro exitoso", "success"); alertclose();
                                 location.reload();
                            } else {
                                 swal("Error", "El producto no fue registrado", "error"); alertclose();
                                 //location.reload();
                            }

                        }).catch(error => console.log(error));

            }
            
            $("input[name='iva_ret']").TouchSpin({
                min: 0,
                max: 100,
                step: 0.1,
                decimals: 2,
                boostat: 5,
                maxboostedstep: 10,
                postfix: '%'
            });
            
            $("input[name='isr']").TouchSpin({
                min: 0,
                max: 100,
                step: 0.1,
                decimals: 2,
                boostat: 5,
                maxboostedstep: 10,
                postfix: '%'
            });
            
            $("input[name='ieps_cuota']").TouchSpin({
                min: 0,
                max: 100,
                step: 0.1,
                decimals: 2,
                boostat: 5,
                maxboostedstep: 10,
                postfix: '%'
            });
            
            $("input[name='ieps_ret_cuota']").TouchSpin({
                min: 0,
                max: 100,
                step: 0.1,
                decimals: 2,
                boostat: 5,
                maxboostedstep: 10,
                postfix: '%'
            });
        </script>
        <!-- JavaScript files-->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
        <!-- Data Tables-->
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"></script>
        <!-- CMS Category JS-->
        <script src="../lib/js/datatable-clave-unidad.js" type="text/javascript"></script>
        <!-- Choices.js-->
        <script src="../lib/vendor/choices.js/public/assets/scripts/choices.min.js"></script>
        <!-- iMask-->
        <script src="../lib/vendor/imask/imask.min.js"></script>
        <!-- NoUI Slider-->
        <script src="../lib/vendor/nouislider/nouislider.min.js"></script>
        <!-- VanillaJs DatePicker-->
        <script src="../lib/vendor/vanillajs-datepicker/js/datepicker-full.min.js"></script>
        <!-- Forms init-->
        <script src="../lib/js/forms-advanced.js"></script>
        <!-- Main Theme JS File-->
        <script src="../lib/js/theme.js"></script>
        <!-- Prism for syntax highlighting-->
        <script src="../lib/vendor/prismjs/prism.js"></script>
        <script src="../lib/vendor/prismjs/plugins/normalize-whitespace/prism-normalize-whitespace.min.js"></script>
        <script src="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.min.js"></script>
        <script src="../lib/vendor/prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard.min.js"></script>
        <!-- sweetalert -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <script type="text/javascript">
            // Optional
            Prism.plugins.NormalizeWhitespace.setDefaults({
                'remove-trailing': true,
                'remove-indent': true,
                'left-trim': true,
                'right-trim': true,
            });

        </script>
    </body>
    <%
        } catch (NullPointerException e) {
            out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            out.println("<script>window.close();</script>");
        } catch (Exception e) {
            out.println("Excepcion revise por favor! " + e);
            e.printStackTrace();
        }
    %>
</html>
