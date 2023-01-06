<%-- 
    Document   : register
    Created on : 20/12/2021, 01:44:10 PM
    Author     : Desarrollo Tacts
--%>

<%@page import="java.text.DateFormat"%>
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

    //Extracción de Hora   
    Date hr = new Date();
    DateFormat hours = new SimpleDateFormat("HH:mm:ss");
    String hora = hours.format(hr);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Recibo de Pago</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">
        <!-- Google fonts - Popppins for copy-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&amp;display=swap" rel="stylesheet">
        <!-- Prism Syntax Highlighting-->
        <link rel="stylesheet" href="lib/vendor/prismjs/plugins/toolbar/prism-toolbar.css">
        <link rel="stylesheet" href="lib/vendor/prismjs/themes/prism-okaidia.css">
        <!-- The Main Theme stylesheet (Contains also Bootstrap CSS)-->
        <link rel="stylesheet" href="lib/css/style.default.css" id="theme-stylesheet">
        <!-- Custom stylesheet - for your changes-->
        <link rel="stylesheet" href="lib/css/custom.css">
        <!-- Favicon-->
        <link rel="shortcut icon" href="lib/img/favicon.png">
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
    </head>
    <body>
        <!-- navbar-->
        <header class="header">
        </header>
        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <!-- Page Header-->
                    <div class="page-header">
                        <h3 class="page-heading">Registro de Información</h3>
                    </div>
                    <section>
                        <div class="row">
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <!-- check - editar conceptos -->
                                        <div class="row">
                                            <div class="col-3">
                                                <input class="form-check-input" type="radio" id="check_cartaporte" name="check_cartaporte" value="1" onclick="habilitarDatosContratacion(this.value)" checked="true">  
                                                <label class="custom-control-label" for="customCheck1">1. Datos Generales</label>
                                            </div>
                                            <div class="col-3">
                                                <input class="form-check-input" type="radio" id="check_cartaporte" name="check_cartaporte" value="2" onclick="habilitarDatosContratacion(this.value)">  
                                                <label class="custom-control-label" for="customCheck1">2. Resumen de compra</label>
                                            </div>
                                            <div class="col-3">
                                                <input class="form-check-input" type="radio" id="check_cartaporte" name="check_cartaporte" value="3" onclick="habilitarDatosContratacion(this.value)">  
                                                <label class="custom-control-label" for="customCheck1">3. Datos de Facturación</label>
                                            </div>
                                            <div class="col-2">
                                                <input class="form-check-input" type="radio" id="check_cartaporte" name="check_cartaporte" value="4" onclick="habilitarDatosContratacion(this.value)">  
                                                <label class="custom-control-label" for="customCheck1">4. Formas de Pago</label>
                                            </div>
                                            <hr>
                                        </div>
                                        <br>
                                        <!-- 1. Datos Generales -->
                                        <div id="tab-LugarDeEmbarque" class="tab-pane">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-md-8">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Nombre Contacto:</label>
                                                        <input class="form-control" id="nombre_contacto" name="nombre_contacto" type="text" value="" autocomplete="off">
                                                        <div id="val_lugEmb_nombre" style="color:#3b83bd;"></div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Razon Social:</label>
                                                        <input class="form-control" id="razon_social" name="razon_social" type="text" value="" autocomplete="off">
                                                        <div id="val_lugEmb_rfc" style="color:#3b83bd;"></div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">RFC:</label>
                                                        <input class="form-control" id="rfc"  name="rfc" type="text" value="" autocomplete="off">
                                                        <div id="val_lugEmb_calle" style="color:#3b83bd;"></div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-2">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Correo Electrónico:</label>
                                                        <input autocomplete="off" class="form-control" id="correo_electronico"  name="correo_electronico" placeholder="" type="text" value="" autocomplete="off">
                                                        <div id="val_lugEmb_noInterior" style="color:#3b83bd;"></div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Teléfono:</label>
                                                        <input autocomplete="off" class="form-control" id="telefono"  name="telefono" placeholder="" type="text" value="" autocomplete="off">
                                                        <div id="val_lugEmb_noExterior" style="color:#3b83bd;"></div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Usuario:</label>
                                                        <input autocomplete="off" class="form-control" id="usuario"   name="usuario" placeholder="" type="text" value="" autocomplete="off">
                                                        <div id="val_lugEmb_codigoPostal" style="color:#3b83bd;"></div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Password:</label>
                                                        <input autocomplete="off" class="form-control" id="password"   name="password" placeholder="" type="text" value="" autocomplete="off">
                                                        <div id="val_lugEmb_codigoPostal" style="color:#3b83bd;"></div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Confirmar Password:</label>
                                                        <input autocomplete="off" class="form-control" id="conf_password"   name="conf_password" placeholder="" type="text" value="" autocomplete="off">
                                                        <div id="val_lugEmb_codigoPostal" style="color:#3b83bd;"></div>
                                                    </div>
                                                </div> 
                                                <br>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="checkbox">
                                                            <input id="CHK_Acepto" type="checkbox" value=""><b>&nbsp;&nbsp;Acepto </b> los <a data-bs-toggle="modal" data-bs-target="#myModalContrato"><strong><u>Términos y Condiciones</u></strong></a> y el <a data-bs-toggle="modal" data-bs-target="#myModalAvisoPrivacidad"><strong><u>Aviso de Privacidad</u></strong></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- 2. Resumen de compra -->
                                        <div id="tab-Destinatario" class="tab-pane" style="display:none;">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Si desea cambiar de paquete, solo seleccione uno diferente:</label>
                                                        <select class="form-control selectpicker col-md-5" id="id_paqueteFolio" name="id_paqueteFolio" onchange="detailsConcepto(this.value)">
                                                            <option value="">-- Seleccione un Paquete de Folios --</option>
                                                            <option value="zlxNyLw8Si5ZKRD2VhrIZhxoU28mNOMKfoHWMeNxXW8+2DMu6qqIHw==">Paquete de 10 Folios - $120.00</option>
                                                            <option value="OtuIwDp1yUSBcA/VMbtcDKt67G4k4r+I7n7EAPosoJl0SI7cSAPCAA==">Paquete de 25 Folios - $270.00</option>
                                                            <option value="FwBazkIAxzM9l3ZXhYfZHtmU9izGPIKwyqxLxcWkytHSdFXiW7vG+w==">Paquete de 50 Folios - $406.00</option>
                                                            <option value="2sNuNU2WGDrWgs7U5DM9mm/TRGj0LRyt4pe1NGAe56mmbAYfEIUjAA==">Paquete de 100 Folios - $600.00</option>
                                                            <option value="LoTnESBgmD64r+w9tFrfQ+RJRs5jkEWa3zxt8OP3dhO684e/Oqk5rg==">Paquete de 300 Folios - $1,224.01</option>
                                                            <option value="sl8qBgjhg7PNOBqDz6MjppHFBNBSQqlAyBJ+2LUKEZI8SR7g9SpFmg==">Paquete de 500 Folios - $1,749.00</option>
                                                            <option value="5QaHZEbUaAfRy1/Eaj+IRR0JlwoTWqmNFZ8moZLy6oOaqEYEMWpRLQ==">Paquete de 1000 Folios - $2,900.00</option>
                                                            <option value="WGnPDtPtHrQ3J8f/VMdPqNeXYNeu8nrjnEoTHhTZGM54ClXn+u9/7Q==">Paquete de 3000 Folios - $3,880.00</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>    
                                        </div>
                                        <!-- 3. Datos de Facturación -->
                                        <div id="tab-LugarDeEntrega" class="tab-pane" style="display:none;">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <!--<input data-val="true" data-val-required="El campo Utilizar los datos generales como datos de facturación es obligatorio." id="UsuarioFacturacionIguales" name="UsuarioFacturacionIguales" onclick="GetDatos()" type="checkbox" value="true" /><input name="UsuarioFacturacionIguales" type="hidden" value="false" />-->
                                                            <input type="checkbox" id="chkUsoDatosGenerales" class="icheckbox_square-green">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Utilizar los datos generales como datos de facturación</label>
                                                            <br>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <input autocomplete="off" class="form-control" data-val="true" data-val-regex="Solo están permitidos caracteres de tipo: letras, numero, punto, comas, perentesis, ampersand y guiones" data-val-regex-pattern="^[a-zA-Z0-9\x20\x26\x28\x29\x2C\x2E\x2F\x5F\x2D\x22áéíóúÁÉÍÓÚñÑ]{0,}$" data-val-required="El Nombre de Contacto es requerido." id="NombreContactoFacturacion" maxlength="100" name="NombreContactoFacturacion" placeholder="Nombre Contacto" type="text" value="">
                                                            <span class="field-validation-valid label label-danger" data-valmsg-for="NombreContactoFacturacion" data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <input autocomplete="off" class="form-control" data-val="true" data-val-regex="Solo están permitidos caracteres de tipo: letras, numero, punto, comas, perentesis, ampersand y guiones" data-val-regex-pattern="^[a-zA-Z0-9\x20\x26\x28\x29\x2C\x2E\x2F\x5F\x2D\x22áéíóúÁÉÍÓÚñÑ]{0,}$" data-val-required="La Razón Social es requerida." id="RazonSocialFacturacion" maxlength="150" name="RazonSocialFacturacion" placeholder="Razon Social" type="text" value="">
                                                            <span class="field-validation-valid label label-danger" data-valmsg-for="RazonSocialFacturacion" data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <input autocomplete="off" class="form-control" data-val="true" data-val-regex="El RFC es inválido." data-val-regex-pattern="^([A-Za-zñÑ&amp;]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[A-Za-zñÑ|\d]{3})$" data-val-required="El RFC es requerido." id="RFCFacturacion" maxlength="13" name="RFCFacturacion" onkeydown="upperCaseF(this)" placeholder="RFC" type="text" value="">
                                                            <span class="field-validation-valid label label-danger" data-valmsg-for="RFCFacturacion" data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <input autocomplete="off" class="form-control" data-val="true" data-val-regex="El correo electrónico es invalido." data-val-regex-pattern="^(([a-zA-Z0-9_\-\.]+)@(([a-zA-Z0-9_\-]+)\.)+([a-zA-Z]{2,}))(([;]{1})(([a-zA-Z0-9_\-\.]+)@(([a-zA-Z0-9_\-]+)\.)+([a-zA-Z]{2,})))*$" data-val-required="El Correo Electrónico es requerido." id="CorreoElectronicofacturacion" name="CorreoElectronicofacturacion" placeholder="Correo Electrónico  Para Facturación" type="text" value="">
                                                            <span class="field-validation-valid label label-danger" data-valmsg-for="CorreoElectronicofacturacion" data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="input-group">
                                                            <input autocomplete="off" class="form-control" data-val="true" data-val-minlength="El campo Código Portal debe ser un tipo de cadena o matriz con una longitud mínima de '5'." data-val-minlength-min="5" data-val-regex="Código Postal invalido este debe tener 5 digitos" data-val-regex-pattern="^(\d{5})$" id="DomicilioFacturacion_CP" maxlength="5" minlength="5" name="DomicilioFacturacion.CP" placeholder="Código Postal" type="text" value="">
                                                            <span class="field-validation-valid label label-danger" data-valmsg-for="DomicilioFacturacion.CP" data-valmsg-replace="true"></span>

                                                            <div class="input-group-btn">
                                                                <button tabindex="-1" type="button" class="btn btn-primary" id="btnValidarCP">Validar Código Postal</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <select class="form-control" data-val="true" data-val-required="El Uso de CFDI es requerido." id="idUsoCfdi" name="idUsoCfdi"><option value="">-- Seleccione un Uso para CFDi --</option>
                                                                <option value="D01">D01 - Honorarios médicos, dentales y gastos hospitalarios.</option>
                                                                <option value="D02">D02 - Gastos médicos por incapacidad o discapacidad</option>
                                                                <option value="D03">D03 - Gastos funerales.</option>
                                                                <option value="D04">D04 - Donativos.</option>
                                                                <option value="D05">D05 - Intereses reales efectivamente pagados por créditos hipotecarios (casa habitación).</option>
                                                                <option value="D06">D06 - Aportaciones voluntarias al SAR.</option>
                                                                <option value="D07">D07 - Primas por seguros de gastos médicos.</option>
                                                                <option value="D08">D08 - Gastos de transportación escolar obligatoria.</option>
                                                                <option value="D09">D09 - Depósitos en cuentas para el ahorro, primas que tengan como base planes de pensiones.</option>
                                                                <option value="D10">D10 - Pagos por servicios educativos (colegiaturas)</option>
                                                                <option value="G01">G01 - Adquisición de mercancias</option>
                                                                <option value="G02">G02 - Devoluciones, descuentos o bonificaciones</option>
                                                                <option value="G03" selected="selected">G03 - Gastos en general</option>
                                                                <option value="I01">I01 - Construcciones</option>
                                                                <option value="I02">I02 - Mobilario y equipo de oficina por inversiones</option>
                                                                <option value="I03">I03 - Equipo de transporte</option>
                                                                <option value="I04">I04 - Equipo de computo y accesorios</option>
                                                                <option value="I05">I05 - Dados, troqueles, moldes, matrices y herramental</option>
                                                                <option value="I06">I06 - Comunicaciones telefónicas</option>
                                                                <option value="I07">I07 - Comunicaciones satelitales</option>
                                                                <option value="I08">I08 - Otra maquinaria y equipo</option>
                                                                <option value="P01">P01 - Por definir</option>
                                                            </select>
                                                            <span class="field-validation-valid label label-danger" data-valmsg-for="idUsoCfdi" data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <input autocomplete="off" class="form-control" data-val="true" data-val-regex="Solo están permitidos caracteres de tipo: letras, numero, punto, comas, perentesis, ampersand y guiones" data-val-regex-pattern="^[a-zA-Z0-9\x20\x26\x28\x29\x2C\x2E\x2F\x5F\x2D\x22áéíóúÁÉÍÓÚñÑ]{0,}$" id="DomicilioFacturacion_Calle" maxlength="100" name="DomicilioFacturacion.Calle" placeholder="Calle" type="text" value="">
                                                            <span class="field-validation-valid label label-danger" data-valmsg-for="DomicilioFacturacion.Calle" data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <input autocomplete="on" class="form-control" data-val="true" data-val-regex="Solo están permitidos caracteres de tipo: letras, numero, punto, comas, perentesis, ampersand y guiones" data-val-regex-pattern="^[a-zA-Z0-9\x20\x26\x28\x29\x2C\x2E\x2F\x5F\x2D\x22áéíóúÁÉÍÓÚñÑ]{0,}$" id="DomicilioFacturacion_Colonia" list="listaColonias" maxlength="100" name="DomicilioFacturacion.Colonia" placeholder="Colonia" type="text" value="">
                                                            <span class="field-validation-valid label label-danger" data-valmsg-for="DomicilioFacturacion.Colonia" data-valmsg-replace="true"></span>

                                                            <datalist id="listaColonias"></datalist>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <input autocomplete="off" class="form-control" data-val="true" data-val-regex="Solo están permitidos caracteres de tipo: letras, numero, punto, comas, perentesis, ampersand y guiones" data-val-regex-pattern="^[a-zA-Z0-9\x20\x26\x28\x29\x2C\x2E\x2F\x5F\x2D\x22áéíóúÁÉÍÓÚñÑ]{0,}$" id="DomicilioFacturacion_NumExterior" maxlength="30" name="DomicilioFacturacion.NumExterior" placeholder="Num. Exterior" type="text" value="">
                                                            <span class="field-validation-valid label label-danger" data-valmsg-for="DomicilioFacturacion.NumExterior" data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <input autocomplete="off" class="form-control" data-val="true" data-val-regex="Solo están permitidos caracteres de tipo: letras, numero, punto, comas, perentesis, ampersand y guiones" data-val-regex-pattern="^[a-zA-Z0-9\x20\x26\x28\x29\x2C\x2E\x2F\x5F\x2D\x22áéíóúÁÉÍÓÚñÑ]{0,}$" id="DomicilioFacturacion_NumInterior" maxlength="30" name="DomicilioFacturacion.NumInterior" placeholder="Num. Interior" type="text" value="">
                                                            <span class="field-validation-valid label label-danger" data-valmsg-for="DomicilioFacturacion.NumInterior" data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <input autocomplete="off" class="form-control" data-val="true" data-val-regex="Solo están permitidos caracteres de tipo: letras, numero, punto, comas, perentesis, ampersand y guiones" data-val-regex-pattern="^[a-zA-Z0-9\x20\x26\x28\x29\x2C\x2E\x2F\x5F\x2D\x22áéíóúÁÉÍÓÚñÑ]{0,}$" id="DomicilioFacturacion_Ciudad" maxlength="100" name="DomicilioFacturacion.Ciudad" placeholder="Ciudad" type="text" value="">
                                                            <span class="field-validation-valid label label-danger" data-valmsg-for="DomicilioFacturacion.Ciudad" data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <input autocomplete="off" class="form-control" data-val="true" data-val-regex="Solo están permitidos caracteres de tipo: letras, numero, punto, comas, perentesis, ampersand y guiones" data-val-regex-pattern="^[a-zA-Z0-9\x20\x26\x28\x29\x2C\x2E\x2F\x5F\x2D\x22áéíóúÁÉÍÓÚñÑ]{0,}$" id="DomicilioFacturacion_Localidad" maxlength="100" name="DomicilioFacturacion.Localidad" placeholder="Localidad" type="text" value="">
                                                            <span class="field-validation-valid label label-danger" data-valmsg-for="DomicilioFacturacion.Localidad" data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <input autocomplete="off" class="form-control" data-val="true" data-val-regex="Solo están permitidos caracteres de tipo: letras, numero, punto, comas, perentesis, ampersand y guiones" data-val-regex-pattern="^[a-zA-Z0-9\x20\x26\x28\x29\x2C\x2E\x2F\x5F\x2D\x22áéíóúÁÉÍÓÚñÑ]{0,}$" id="DomicilioFacturacion_Estado" maxlength="100" name="DomicilioFacturacion.Estado" placeholder="Estado" type="text" value="">
                                                            <span class="field-validation-valid label label-danger" data-valmsg-for="DomicilioFacturacion.Estado" data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <input autocomplete="off" class="form-control" data-val="true" data-val-regex="Solo están permitidos caracteres de tipo: letras, numero, punto, comas, perentesis, ampersand y guiones" data-val-regex-pattern="^[a-zA-Z0-9\x20\x26\x28\x29\x2C\x2E\x2F\x5F\x2D\x22áéíóúÁÉÍÓÚñÑ]{0,}$" id="DomicilioFacturacion_Pais" maxlength="100" name="DomicilioFacturacion.Pais" placeholder="País" type="text" value="">
                                                            <span class="field-validation-valid label label-danger" data-valmsg-for="DomicilioFacturacion.Pais" data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- 4. Formas de Pago -->
                                        <div id="tab-Mercancia" class="tab-pane" style="display:none;">
                                            <div class="panel-body">
                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Por favor seleccione la forma de pago que desee utilizar:</label>
                                                <div class="row">
                                                    <div class="panel-body">
                                                        <div class="panel-group" id="accordion">
                                                            <div class="panel panel-default">
                                                                <div class="panel-heading">
                                                                    <div class="pull-right">
                                                                        <img src="lib/img/logo-tarjetCredito-removebg-preview.png" width="3%"/>
                                                                    </div>
                                                                    <input class="form-check-input" type="radio" id="check_pago" name="check_pago" value="1" onclick="habilitarDatosPago(this.value)" checked="true">  
                                                                    <label class="custom-control-label" for="customCheck1">Tarjeta de Crédito</label>
                                                                </div>
                                                                <div id="collapseOne">
                                                                    <hr>
                                                                    <div class="panel-body">
                                                                        <div class="row">
                                                                            <div class="col-md-4">
                                                                                <strong>Paquete:</strong> <input class="form-control" disabled="disabled" id="DescripcionPaqueteTCredito" name="DescripcionPaqueteTCredito" readonly="readOnly" style="border: 0; background:none; outline:0; margin:0; padding:0;" type="text" value="">
                                                                            </div>
                                                                            <br>
                                                                            <div class="col-md-4">
                                                                                <strong>Total:</strong> <span class="text-navy"><input class="form-control" data-val="true" data-val-number="The field TotalPaqueteTCredito must be a number." data-val-required="El campo TotalPaqueteTCredito es obligatorio." disabled="disabled" id="TotalPaqueteTCredito" name="TotalPaqueteTCredito" readonly="readOnly" style="border: none; text-align: left; background:none; outline:0; margin:0; padding:0;" type="text" value="$0.00"></span>
                                                                            </div>
                                                                        </div>
                                                                        <br>
                                                                        <div class="row">
                                                                            <div class="col-xs-6 col-md-6">
                                                                                <div class="form-group">
                                                                                    <!--<input type="text" class="form-control" autocomplete="off" data-openpay-card="holder_name" id="holdernameC" placeholder="Nombre y Apellidos" value="Ivan Faris" />-->
                                                                                    <input autocomplete="off" class="form-control" data-openpay-card="holder_name" id="DatosOpenPayC_HolderName" maxlength="100" name="DatosOpenPayC.HolderName" placeholder="Nombre y Apellidos" type="text" value="">

                                                                                </div>
                                                                            </div>
                                                                            <br>
                                                                            <div class="col-xs-6 col-md-6">
                                                                                <div class="form-group">
                                                                                    <div class="input-group">
                                                                                        <!--<input type="text" class="form-control" autocomplete="off" data-openpay-card="card_number" id="cardnumberC" placeholder="Numero de Tarjeta" maxlength="16" value="4152313245708073" />-->
                                                                                        <input autocomplete="off" class="form-control" data-openpay-card="card_number" id="DatosOpenPayC_CardNumber" maxlength="16" name="DatosOpenPayC.CardNumber" placeholder="Numero de Tarjeta" type="text" value="">
                                                                                        <span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="col-xs-6 col-md-6">
                                                                                <div class="form-group">
                                                                                    <!--<input type="text" class="form-control" name="ExpiryC" id="ExpiryC" placeholder="MM / YY" maxlength="5" value="11/20" />-->
                                                                                    <input autocomplete="off" class="form-control" data-mask="99/99" id="DatosOpenPayC_Expiry" maxlength="5" name="DatosOpenPayC.Expiry" placeholder="MM/YY" type="text" value="">
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-6 col-md-6">
                                                                                <div class="form-group">
                                                                                    <!--input type="text" class="form-control" placeholder="3 dígitos" autocomplete="off" data-openpay-card="cvv2" id="cvvC" maxlength="3" value="444" />-->
                                                                                    <input autocomplete="off" class="form-control" data-openpay-card="cvv2" id="DatosOpenPayC_Cvv2" maxlength="4" name="DatosOpenPayC.Cvv2" placeholder="3 dígitos (VISA, MASTERCARD), 4 dígitos (AMEX)" type="text" value="">
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <br>
                                                                        <div class="row" style="text-align: right;">
                                                                            <div class="col-xs-12 col-md-12">
                                                                                <!--<button class="btn btn-primary" type="submit">Pagar </button>-->

                                                                                <button type="button" class="btn btn-primary boton_azul btn-sm block m-b" data-loading-text="<i class='fa fa-spinner fa-spin '></i> Procesando" value="TarjetaCredito" name="tipoMetodoPagoCredito">Pago con Tarjeta de Crédito</button>

                                                                            </div>

                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="col-xs-12 col-md-12">
                                                                                <!--Transacciones realizadas vía: <img src="/Imagenes/Iconos/openpay.png" width="76"><br>
                                                                                Tus pagos se realizan de forma segura con encriptación de 256 bits <img src="/Imagenes/Iconos/security.png" width="25">
                                                                            --></div>
                                                                        </div>
                                                                    </div>
                                                                    <hr>
                                                                    <br>
                                                                </div>
                                                            </div>
                                                            <br>
                                                            <div class="panel panel-default">
                                                                <div class="panel-heading">
                                                                    <div class="pull-right">
                                                                        <img src="lib/img/logo-tarjetDebito-removebg-preview.png" width="3%"/>
                                                                    </div>
                                                                    <input class="form-check-input" type="radio" id="check_pago" name="check_pago" value="2" onclick="habilitarDatosPago(this.value)">  
                                                                    <label class="custom-control-label" for="customCheck1">Tarjeta de Débito</label>
                                                                </div>
                                                                <div id="collapseTwo" style="display:none;">
                                                                    <hr>
                                                                    <div class="panel-body">
                                                                        <div class="row">
                                                                            <div class="col-md-4">
                                                                                <strong>Paquete:</strong> <input class="form-control" disabled="disabled" id="DescripcionPaqueteTDebito" name="DescripcionPaqueteTDebito" readonly="readOnly" style="border: 0; background:none; outline:0; margin:0; padding:0;" type="text" value="">
                                                                            </div>
                                                                            <br>
                                                                            <div class="col-md-4">
                                                                                <strong>Total:</strong> <span class="text-navy"><input class="form-control" data-val="true" data-val-number="The field TotalPaqueteTDebito must be a number." data-val-required="El campo TotalPaqueteTDebito es obligatorio." disabled="disabled" id="TotalPaqueteTDebito" name="TotalPaqueteTDebito" readonly="readOnly" style="border: none; text-align: left; background:none; outline:0; margin:0; padding:0;" type="text" value="$0.00"></span>
                                                                            </div>
                                                                        </div>
                                                                        <br>
                                                                        <div class="row">
                                                                            <div class="col-xs-6 col-md-6">
                                                                                <div class="form-group">
                                                                                    <!--<input type="text" class="form-control" autocomplete="off" data-openpay-card="holder_name" id="holdernameD" placeholder="Nombre y Apellidos" />-->
                                                                                    <input autocomplete="off" class="form-control" data-openpay-card="holder_name" id="DatosOpenPayD_HolderName" maxlength="100" name="DatosOpenPayD.HolderName" placeholder="Nombre y Apellidos" type="text" value="">
                                                                                </div>
                                                                            </div>
                                                                            <br>
                                                                            <div class="col-xs-6 col-md-6">
                                                                                <div class="form-group">
                                                                                    <div class="input-group">
                                                                                        <!--<input type="text" class="form-control" autocomplete="off" data-openpay-card="card_number" id="cardnumberD" placeholder="Numero de Tarjeta" maxlength="16" />-->
                                                                                        <input autocomplete="off" class="form-control" data-openpay-card="card_number" id="DatosOpenPayD_CardNumber" maxlength="16" name="DatosOpenPayD.CardNumber" placeholder="Numero de Tarjeta" type="text" value="">
                                                                                        <span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="col-xs-6 col-md-6">
                                                                                <div class="form-group">
                                                                                    <!--<input type="text" class="form-control" name="ExpiryD" id="ExpiryD" placeholder="MM / YY" maxlength="5" />-->
                                                                                    <input autocomplete="off" class="form-control" data-mask="99/99" id="DatosOpenPayD_Expiry" maxlength="5" name="DatosOpenPayD.Expiry" placeholder="MM/YY" type="text" value="">
                                                                                </div>
                                                                            </div>
                                                                            <br>
                                                                            <div class="col-xs-6 col-md-6">
                                                                                <div class="form-group">
                                                                                    <!--<input type="text" class="form-control" placeholder="3 dígitos" autocomplete="off" data-openpay-card="cvv2" id="cvvD" maxlength="3" />-->
                                                                                    <input autocomplete="off" class="form-control" data-openpay-card="cvv2" id="DatosOpenPayD_Cvv2" maxlength="4" name="DatosOpenPayD.Cvv2" placeholder="3 dígitos (VISA, MASTERCARD), 4 dígitos (AMEX)" type="text" value="">
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <br>
                                                                        <div class="row" style="text-align: right;">
                                                                            <div class="col-xs-12 col-md-12">
                                                                                <!--<button class="btn btn-primary" type="submit">Pagar </button>-->
                                                                                <button type="button" class="btn btn-primary boton_azul btn-sm block m-b" data-loading-text="<i class='fa fa-spinner fa-spin '></i> Procesando" value="TarjetaDebito" name="tipoMetodoPagoDebito">Pago con Tarjeta de Débito</button>
                                                                            </div>
                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="col-xs-12 col-md-12">
                                                                                <!--Transacciones realizadas vía: <img src="/Imagenes/Iconos/openpay.png" width="76"><br>
                                                                                Tus pagos se realizan de forma segura con encriptación de 256 bits <img src="/Imagenes/Iconos/security.png" width="25">
                                                                            --></div>
                                                                        </div>
                                                                    </div>
                                                                    <hr>
                                                                    <br>
                                                                </div>
                                                            </div>
                                                            <br>
                                                            <div class="panel panel-default">
                                                                <div class="panel-heading">
                                                                    <div class="pull-right">
                                                                        <img src="lib/img/log-tiendasConveniencia-removebg-preview.png" width="3%"/>
                                                                    </div>
                                                                    <input class="form-check-input" type="radio" id="check_pago" name="check_pago" value="3" onclick="habilitarDatosPago(this.value)">  
                                                                    <label class="custom-control-label" for="customCheck1">Pago en Tienda de Conveniencia</label>
                                                                </div>
                                                                <div id="collapseThree" style="display:none;">
                                                                    <hr>
                                                                    <div class="panel-body">
                                                                        <div class="row">
                                                                            <div class="col-md-4">
                                                                                <strong>Paquete:</strong> <input class="form-control" disabled="disabled" id="DescripcionPaquetePTienda" name="DescripcionPaquetePTienda" readonly="readOnly" style="border: 0; background:none; outline:0; margin:0; padding:0;" type="text" value="">
                                                                            </div>
                                                                            <br>
                                                                            <div class="col-md-4">
                                                                                <strong>Total:</strong> <span class="text-navy"><input class="form-control" data-val="true" data-val-number="The field TotalPaquetePTienda must be a number." data-val-required="El campo TotalPaquetePTienda es obligatorio." disabled="disabled" id="TotalPaquetePTienda" name="TotalPaquetePTienda" readonly="readOnly" style="border: none; text-align: left; background:none; outline:0; margin:0; padding:0;" type="text" value="$0.00"></span>
                                                                            </div>
                                                                        </div>

                                                                        <br>

                                                                        <div class="row" style="text-align: right;">
                                                                            <div class="col-md-12">
                                                                                <button type="button" class="btn btn-primary boton_azul btn-sm block m-b" data-loading-text="<i class='fa fa-spinner fa-spin '></i> Procesando" value="PagoTienda" name="tipoMetodoPagoPTienda">Pago en Tienda</button>

                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <hr>
                                                                    <br>
                                                                </div>
                                                            </div>
                                                            <br>
                                                            <div class="panel panel-default">
                                                                <div class="panel-heading">
                                                                    <div class="pull-right">
                                                                        <img src="lib/img/logo-transferencia-removebg-preview.png" width="3%"/>
                                                                    </div>
                                                                    <div class="pull-right">
                                                                        <input class="form-check-input" type="radio" id="check_pago" name="check_pago" value="4" onclick="habilitarDatosPago(this.value)">  
                                                                        <label class="custom-control-label" for="customCheck1">Transferencia Electrónica</label>
                                                                    </div>
                                                                </div>
                                                                <div id="collapseFour" style="display:none;">
                                                                    <hr>
                                                                    <div class="panel-body">
                                                                        <div class="row">
                                                                            <div class="col-md-4">
                                                                                <strong>Paquete:</strong> <input class="form-control" disabled="disabled" id="DescripcionPaqueteTElectronica" name="DescripcionPaqueteTElectronica" readonly="readOnly" style="border: 0; background:none; outline:0; margin:0; padding:0;" type="text" value="">
                                                                            </div>
                                                                            <br>
                                                                            <div class="col-md-4">
                                                                                <strong>Total:</strong> <span class="text-navy"><input class="form-control" data-val="true" data-val-number="The field TotalPaqueteTElectronica must be a number." data-val-required="El campo TotalPaqueteTElectronica es obligatorio." disabled="disabled" id="TotalPaqueteTElectronica" name="TotalPaqueteTElectronica" readonly="readOnly" style="border: none; text-align: left; background:none; outline:0; margin:0; padding:0;" type="text" value="$0.00"></span>
                                                                            </div>
                                                                        </div>

                                                                        <br>

                                                                        <div class="row" style="text-align: right;">
                                                                            <div class="col-md-12">
                                                                                <strong>Titular de la cuenta: OpenPay S.A.P.I. de C.V.</strong>
                                                                                <br><br>
                                                                                <button type="button" class="btn btn-primary boton_azul btn-sm block m-b" data-loading-text="<i class='fa fa-spinner fa-spin '></i> Procesando" value="TransferenciaElectronica" name="tipoMetodoPagoTElectronica">Pago con Transferencia Electrónica</button>

                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <hr>
                                                                    <br>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>   
                                        </div>  
                                        <br><br>   
                                        <!-- Botones controles -->
                                        <div class="col-lg-12" style="text-align: right;">
                                            <a class="btn btn-primary text-nowrap" role="button" onclick="addRegistro()">Registrar Información</a>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <a class="btn btn-default text-nowrap" role="button" onclick="delete_registro()">Cancelar</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>   
                    </section>
                </div>

                <!-- modal - Vista Previa CFDI --> 
                <div class="modal fade text-start" id="modalVistaPreviaCfdi" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header border-0 bg-gray-100">
                                <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Vista previa comprobante:</h3>
                                <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="container" id="visor1"> 
                                    <iframe class="responsive-iframe" name="pdf" id="pdf" width="100%" height="500px"></iframe>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>    
                <!-- modal - Contrato -->
                <div class="modal fade text-start" id="myModalContrato" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable" role="document">
                        <div class="modal-content">
                            <div class="modal-header border-0 bg-gray-100">
                                <h4 class="modal-title text-center"><center>Términos y Condiciones</center></h4>
                                <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                CONTRATO DE SERVICIO DE USO.<br><br>Por favor lea cuidadosamente los términos y condiciones del presente contrato de uso del servicio de Emisión y Certificación de CFDI a través de la plataforma PremiumOne y del sitio comercial publicado en www.tacts.com.mx, los cuales son propiedad de <strong>Tacts S. de R.L. de C.V</strong>. en delante TACTS, (en adelante los SITIOS).<br><br>EL USUARIO, al llenar cualquier forma de registro para usar los servicios o paquetes dentro los SITIOS de TACTS le atribuye a quien lo hace la condición de USUARIO REGISTRADO (en adelante 	EL CLIENTE) y lo sujeta a la aceptación plena y sin reservas de todos y cada uno de los términos y condiciones de uso aquí detallados, no importando la versión publicada a los SITIOS por TACTS.<br><br>Si usted no está de acuerdo con todos y cada uno de los términos y condiciones de uso aquí detallados, usted no tendrá el derecho de usar los servicios o paquetes dentro de los SITIOS de TACTS.<br><br>Las páginas Web y sus funcionalidades disponibles en los SITIOS, así como todas las páginas híper vinculadas en dichas páginas Web (a menos que se indique lo contrario) son propiedad y están operados por TACTS y están disponibles para el CLIENTE bajo los siguientes términos y condiciones:<br><br>1.- OBJETO DE LOS SITIOS DE TACTS. - TACTS ofrece servicios o paquetes, todos relacionados con la emisión, certificación, validación, recepción, almacenamiento, resguardo, consulta y transmisión de comprobantes fiscales digitales (en adelante CFDI) al CLIENTE.<br><br>2.- PERSONALIDAD JURÍDICA. - El CLIENTE certifica ante TACTS que tiene toda la capacidad personal y jurídica para adherirse a estos términos y condiciones de uso que aquí se detallan y se hace responsable de la selección y uso de los servicios o paquetes a su conveniencia dentro LOS SITIOS de TACTS.<br><br>3.- MODIFICACIONES A LOS TÉRMINOS Y CONDICIONES. - TACTS se reserva el derecho a su propia discreción, de modificar estos términos y condiciones de uso de TACTS en cualquier momento con la obligación de notificarle al CLIENTE de este a través de una nota en LOS SITIOS de TACTS a través de una notificación por correo electrónico. El uso de estos servicios o paquetes por parte del CLIENTE después de dichas notificaciones constituyen su más amplia aceptación a los nuevos términos y condiciones de uso de TACTS. TACTS se compromete a que las modificaciones que se hagan nunca violaran los requerimientos legales que disponga el SAT (Servicio de Administración Tributaria), ni ninguna otra legislación aplicable o autoridad competente en la materia.<br><br>4.- CONEXIÓN A LOS SITIOS DE TACTS. - El CLIENTE está de acuerdo en cumplir con los requisitos mínimos necesarios para poderse conectar a los SITIOS de TACTS y así poder usar los servicios o paquetes. Corren por cuenta del CLIENTE los recursos necesarios para conectarse a la red de internet, incluyendo de forma enunciativa más no limitativa, la computadora, PC, Notebook, Laptop o cualquier dispositivo que el CLIENTE use para dicho fin.<br><br>5.- DE LOS SERVICIOS Y PAQUETES. - Los servicios o paquetes sólo pueden darse mediante el pago de una remuneración y de la forma en que se indica expresamente en sus correspondientes condiciones. Para ver los servicios o paquetes y cuales son así como sus condiciones particulares, favor de revisar el sitio www.tacts.com.mx en la sección de “Soluciones”.<br><br>6.- PRECIOS Y PAGOS. - El CLIENTE acepta que los servicios o paquetes son adquiridos mediante el pago establecido por TACTS, estas condiciones particulares y específicas como se describe en el sitio www.tacts.com.mx, el CLIENTE se hará responsable del pago en tiempo y forma de los servicios o paquetes seleccionados. El pago por el o los paquetes adquiridos por parte de EL CLIENTE, deberán realizarse por anticipado, ante la falta del cumplimiento de pago por parte de EL CLIENTE; TACTS podrá suspender el acceso y los servicios de EL CLIENTE sin ninguna responsabilidad para TACTS. TACTS se reserva el derecho de cambiar su lista de precios y/o de agregar nuevos servicios o paquetes.<br><br>7.- CAMBIOS EN LOS SERVICIOS O PAQUETES. - TACTS puede cambiar, suspender o descontinuar los servicios (o el acceso del CLIENTE a los mismos) en cualquier momento incluyendo la disponibilidad de cualquier funcionalidad, obligándose TACTS a notificar EL CLIENTE mediante los sitios y/o por correo electrónico.<br><br>8.- CLAVES DE ACCESO. - TACTS reserva algunos de los SERVICIOS O PAQUETES ofrecidos a través de los SITIOS a los CLIENTES registrados mediante el llenado de la forma de registro de crear cuenta y la generación de los mecanismos de acceso y control correspondientes, tales como nombre de usuario y contraseña (en adelante las CLAVES DE ACCESO). El CLIENTE se compromete a hacer un uso diligente de las CLAVES DE ACCESO, así como a no ponerla a disposición de terceros y a comunicar a TACTS a la mayor brevedad la pérdida o robo de dichas CLAVES DE ACCESO, así como cualquier riesgo de acceso a las mismas por un tercero. El CLIENTE se obliga a: (I) inmediatamente notificar a TACTS de cualquier uso no autorizado sobre sus CLAVES DE ACCESO o de cualquier otro evento que haya violado la seguridad e integridad de estas y (II) a siempre terminar su sesión de forma correcta dentro de los servicios o paquetes que soliciten CLAVES DE ACCESO. TACTS no se puede responsabilizar de cualquier daño o pérdida suscitada por parte de EL CLIENTE, si este no se obligó a los puntos (I) y (II) de esta cláusula número 8. TACTS tiene el derecho de dar de baja la clave de acceso del CLIENTE si éste no ha tenido actividad alguna en un periodo mínimo de 30 treinta días.<br><br>9.- VERACIDAD, SEGURIDAD Y CONFIDENCIALIDAD DE LA INFORMACIÓN. - Como una condición para usar los servicios o paquetes que requieran registro, el CLIENTE deberá de llenar la información requerida, de forma completa, veraz y actualizada. El no llenar la información de forma completa, veraz y actualizada en los SITIOS de TACTS por parte del CLIENTE, éste incumple inmediatamente con los términos de uso y condiciones de TACTS y TACTS se reserva el derecho de cancelar y dar por terminado de forma inmediata sin ninguna responsabilidad para TACTS. El CLIENTE será el único responsable de mantener la confidencialidad de sus CLAVES DE ACCESO y de todas las actividades que sucedan en su cuenta. La confidencialidad de la información del CLIENTE se regirá por las Políticas de Privacidad de TACTS entre el CLIENTE e TACTS.<br><br>TACTS por su parte, se compromete a garantizar la seguridad y confidencialidad de la información que EL CLIENTE proporcione a TACTS, a través de EL SITIO y que es requerida para efectos de recibir el servicio de la emisión y certificación del CFDI.<br><br>Para efectos de dar cumplimiento con lo anterior, TACTS implementará mecanismos de transmisión segura de información entre EL CLIENTE e TACTS en ambos sentidos de la comunicación, a través del protocolo seguro https, empleando certificados SSL emitidos por terceros certificados y reconocidos para tales fines. EL CLIENTE por su parte, acepta la implementación de estos mecanismos por parte de TACTS y se compromete a no realizar prácticas que atenten contra la seguridad antes, durante y después de la transmisión de la información.<br><br>Asimismo, TACTS se compromete en el mismo tenor, a salvaguardar la integridad y seguridad de los Certificados de Sellos Digitales (en delante CSD) que son propiedad EL CLIENTE y que éste último entrega de total conformidad a TACTS a través de EL SITIO para los fines de la certificación de los CFDI como parte de los requisitos establecidos en la Resolución Miscelánea Fiscal vigente, para lo cual EL CLIENTE emite mediante EL SITIO de TACTS. TACTS, implementará los mecanismos de seguridad necesarios para salvaguardar la integridad de los CSD.<br><br>10.- COMUNICACIÓN CON TACTS. - El CLIENTE dirigirá a TACTS cualquier comunicación relacionada con el uso de los servicios o paquetes de los SITIOS de TACTS.<br><br>11.- NIVELES DEL SERVICIO (SLA). - TACTS con todos sus servicios estará disponible 7(siete) días las 24(veinticuatro) horas los 365(trecientos sesenta y cinco) días, con una disponibilidad de 99.3% por ciento, donde el .07 por ciento es el periodo de tiempo máximo en que la aplicación podría no estar disponible por causas no planeadas e imputables a TACTS.<br><br>Para mejoras y mantenimiento continuo a la infraestructura y a la aplicación, TACTS podrá planear y ejecutar ventanas de mantenimiento trimestrales, de máximo 24 horas, lo anterior será notificado a EL CLIENTE a través de la página de Internet o portal web de TACTS con una anticipación de por lo menos 7 (siete) días.<br><br>Las solicitudes de soporte y quejas serán atendidas por TACTS en un plazo no mayor a 2 (dos) horas y resueltas en un plazo no mayor 8 (ocho) horas.<br><br>Los medios de contacto por los cuales EL CLIENTE solicitará dicho soporte son mediante el correo electrónico alertas@tacts.mx, chat con acceso a través de http://www.tacts.com.mx.<br><br>TACTS no será responsable de cualquier falla para suministrar los servicios o paquetes cuando éstas fallas sean por caso fortuito o fuerza mayor o el resultado de cualquier causa que esté fuera de control razonable. TACTS hará todo lo posible para remediar con sus recursos la falla. En caso de demanda por lo anterior, si alguna provisión de este acuerdo se encuentre invalidada o en riesgo, la corte le dará el efecto de la intención de la parte como se refleja en dicha provisión, pero las demás provisiones se mantendrán con toda la fuerza y efecto.<br><br>12.- CONTENIDOS.- Los SITIOS y sus materiales desplegados en cualquier formato incluyendo de forma enunciativa más no limitativa, textos, gráficas, artículos, fotografías, imágenes, logotipos, ilustraciones, enlaces o hipervínculos a LOS SITIOS o a los sitios web de terceros, clips de audio, clips de video y en general todo el material salvo se indique lo contrario, están protegidos por las leyes internacionales, así como a las Leyes Federales y la Ley de la Propiedad Industrial vigente y su intención es exclusivamente para el uso de TACTS con sus CLIENTES y sólo se pueden usar dentro del marco de este acuerdo y en conexión con el uso autorizado de los servicios de TACTS. Los contenidos que sean accesibles a través de LOS SITIOS y SERVICIOS O PAQUETES DE TACTS están protegidos por las leyes de derechos al autor.<br><br>13.- LÍMITES DE RESPONSABILIDAD DE LOS CONTENIDOS. - TACTS excluye cualquier responsabilidad por los daños y perjuicios de toda naturaleza que puedan deberse a la transmisión, difusión, almacenamiento, puesta a disposición, recepción, obtención o acceso a los contenidos.<br><br>14.- MARCAS. - TACTS y otras marcas y sus respectivos logotipos indicados en los SITIOS son marcas registradas de Sistemas de Emisión Digital S.A. de C.V. Cualquier otra marca que no sea propiedad de Sistemas de Emisión Digital S.A. de C.V. y que aparezca en los SITIOS es propiedad de su respectivo titular, el cual puede o no estar relacionado, conectado o soportado por Sistemas de Emisión Digital S.A. de C.V., TACTS no concede ninguna licencia o autorización de uso de ninguna clase sobre sus derechos de propiedad industrial e intelectual o sobre cualquier otra propiedad o derecho relacionado los SITIOS, los servicios o paquetes o los contenidos.<br><br>15.- RESTRICCIONES. - El CLIENTE se obliga a dar uso propio y que no utilizará los servicios o paquetes de TACTS para fines diferentes al propuesto por TACTS y no podrá vender, rentar, licenciar, traspasar, arrendar u otra actividad similar, la funcionalidad y/o los servicios o paquetes de los SITIOS de TACTS a terceros. De igual forma se obliga a no generar contenidos o hacer disponible los servicios o paquetes de TACTS, obligándose así a NO generar contenidos que : (I) Infrinjan la ley, sean ilegales, calumniosos, difamatorios, obscenos, abusivos, ofensivos, violentos o de odio, (II) contengan uso de alcohol o abuso de drogas, (III) violen cualquier ley o derecho de terceros, o (IV) promuevan o faciliten cualquiera de lo anterior, ya sea directamente o indirectamente por el CLIENTE.<br><br>16.- RENUNCIAS. - El CLIENTE reconoce y conviene en que TACTS no tiene ninguna relación especial con el CLIENTE y que TACTS no tiene control, ni el deber de adoptar cualquier medida con respecto a la forma en la que el CLIENTE accede a los contenidos o a los SITIOS o los servicios o paquetes; el CLIENTE es el único responsable (y asume toda la responsabilidad y riesgo) para determinar si los contenidos son o no son convenientes o aceptables para el mismo. LOS SITIOS de TACTS puede contener, o el CLIENTE puede tener acceso directo a sitios que contengan información que algunas personas podrían considerar ofensiva o inadecuada. TACTS no hace ninguna representación con respecto a los contenidos en o a través de los SITIOS o los servicios o paquetes, y TACTS se adhiere a los límites de responsabilidad de contenidos detallados en la cláusula 11 once del presente acuerdo.<br><br>17.- INDEMINIZACIÓN A TACTS POR USO INDEBIDO.- En caso de reclamo, investigación, procedimiento o de una demanda de una tercera parte hacia TACTS por el uso indebido de la cuenta del CLIENTE, incluyendo la violación de este acuerdo de términos y condiciones de uso o de violación de derechos de propiedad de los contenidos usando indebidamente la cuenta del CLIENTE u otro derecho reclamado, el CLIENTE deberá indemnizar y mantener a TACTS, sus matrices, subsidiarias, afiliados, funcionarios y empleados en paz, incluidos los gastos y honorarios de abogados.<br><br>El CLIENTE acuerda que TACTS tendrá el control único y exclusivo sobre la defensa y la solución de reclamo con el tercero en discordia.<br><br>18.- LIMITES DE RESPONSABILIDAD DE TACTS PARA CON LOS SERVICIOS O PAQUETES. - TACTS no será responsable con respecto a los SITIOS o de los servicios o paquetes con el CLIENTE por ninguna cantidad que exceda de los pagos realizados por el CLIENTE para ello, o por daños indirectos, incidentales, punitivos o consecuentes de cualquier tipo.<br><br>19.- DISPUTA DE DERECHOS DE AUTOR. - Por favor revise nuestras Aviso de Privacidad de TACTS ya que al ser CLIENTE dentro de los SITIOS de TACTS, el CLIENTE estará de acuerdo como se usa, se administra, se divulga, se guarda y se colecta la información personal y no personal en base a los lineamientos y prácticas que marcan dichas políticas. El CLIENTE acepta que TACTS pueda garantizar la integridad, el uso, manejo responsable y confidencial de la información, TACTS puede colectar, transmitir y usar dicha información en cualquiera de los centros de datos de TACTS, siempre regulados por las mismas políticas de privacidad EL CLIENTE acepta que TACTS entregara información confidencial del CLIENTE a autoridades competentes que así nos lo requieran y únicamente bajo mandato judicial de acuerdo con las leyes aplicables en los Estados Unidos Mexicanos.<br><br>20.- CONFIDENCIALIDAD. - El CLIENTE acuerda que NO podrá divulgar información confidencial de TACTS sin el consentimiento previo por escrito de TACTS. "Información Confidencial" incluye, sin limitación: (I) todo el software de TACTS, tecnología, programación, especificaciones técnicas, materiales, directrices y documentación que el CLIENTE aprenda, desarrolle u obtenga de los SITIOS de TACTS, (II) cualquier otra información designada por escrito por TACTS como "CONFIDENCIAL" o cualquier otra en el mismo sentido. "INFORMACIÓN CONFIDENCIAL" NO incluye la información que se ha hecho pública, o la información que se haya desarrollado de forma independiente sin acceder a la información confidencial de TACTS y que conste por escrito, o que haya sido obtenida legalmente por el CLIENTE de una tercera fuente, o que haya sido revelada por requerimiento de ley o judicial por una autoridad gubernamental de los Estados Unidos Mexicanos.<br><br>21.- TERMINACIÓN. - Cualquiera de las partes puede dar por terminado en cualquier momento los servicios o paquetes. Si es el CLIENTE el que da por terminado la relación con TACTS, este mismo deberá enviar un escrito solicitando la baja del servicio, firmada por representante legal en el caso de ser persona moral y en el caso de persona física por la persona contratante, TACTS no reembolsará ningún pago por SERVICIOS O PAQUETES que el CLIENTE haya erogado. Si es TACTS el que da por terminado la relación y existiera algún servicios o paquetes en la cuenta del CLIENTE por el cual el CLIENTE haya realizado un pago, se reembolsará al CLIENTE el equivalente al SERVICIO NO DEVENGADO, mismo que cuando la compra haya sido efectuada dentro de los primeros 30 días, siempre y cuando no haya infringido ningún artículo del contrato. El cálculo del reembolso sería por el equivalente del servicio no devengado de manera proporcional a lo consumo. El pago se realizará 30 días después de la notificación por parte de TACTS y en la misma forma o medio como fue pagado por el CLIENTE a TACTS. En caso de que el CLIENTE solicite el reembolso se considerara lo siguiente debe efectuarlo dentro de los primeros 30 días posteriores a su pago, mediante un escrito justificando la razón por la cual solicita su rembolso, adjuntando su factura de pago y los datos de contratación, una vez reunida dicha información enviarla al correo electrónico alertas@tacts.mx, una vez enviada será sujeta a evaluación y en caso de aplicarse su reembolso se llevará a cabo dentro de los primeros 5 días del mes siguiente; en caso de que hayan transcurrido 30 (treinta) días posteriores a la compra, no procederá dicho reembolso.<br><br>22.- AUTORIZACION DE ENTREGA DE CFDI CERTIFICADOS AL SAT. - Con la finalidad de dar legal cumplimiento a lo después por las Leyes o Códigos Federales de carácter Fiscal, así como reglas o resoluciones que emanen de ellas, haciendo alusión a lo dispuesto en el artículo 29 fracción IV del Código Fiscal de la Federación, así como a las reglas I.2.7.2.5 fracción II y la regla I.2.7.2.7. Párrafo segundo de la resolución miscelánea fiscal 2014, misma que a la letra dice: “los contribuyentes a que se refiere el párrafo anterior están obligados a proporcionar por escrito al proveedor de certificación de CFDI, su manifestación de conocimiento y autorización para que este último entregue al SAT, copia de los comprobantes que les haya certificado”, por lo anterior el CLIENTE manifiesta de conocimiento y autoriza a TACTS a la entrega de copias de los comprobantes que les haya certificado, a fin de que este ultimo los haga llegar al SAT.<br><br>23.- MISCELANEOS. - Este acuerdo de términos y servicios de uso de los SITIOS de TACTS es de carácter personal e intransferible, por lo que no se puede ceder, licenciar, transmitir total o parcialmente los derechos derivados del presente contrato. Este acuerdo se celebra entre el CLIENTE e TACTS.<br><br>24.- LEY Y JURISDICCIÓN APLICABLE. - Estos términos y condiciones de uso generales se rigen por la Ley Federal de Protección de Datos Personales en Posesión de los Particulares vigente y todas las leyes mexicanas. TACTS y el CLIENTE, renuncian expresamente a cualquier otro fuero, y solo se someten al fuero de los Juzgados y Tribunales de la Ciudad de Monterrey, Nuevo León en los Estados Unidos Mexicanos.<br>
                            </div>
                        </div>
                    </div>
                </div> 
                <!-- modal - Aviso de Privacidad -->
                <div class="modal fade text-start" id="myModalAvisoPrivacidad" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable" role="document">
                        <div class="modal-content">
                            <div class="modal-header border-0 bg-gray-100">
                                <h4 class="modal-title text-center"><center>Aviso de Privacidad</center></h4>
                                <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <p>
                                    Última fecha de actualización: 7 de septiembre de 2015.<br>
                                    <br>
                                    Este aviso de privacidad forma parte del sitio www.tacts.com.mx.<br>
                                    <br>
                                    Sus datos personales serán tratados por <strong>Tacts S. de R.L. de C.V</strong>. (en delante TACTS), responsable de la protección y tratamiento de sus datos personales, con domicilio en Parque Desierto de los Leones #3 Int. 5 Col.Jardines del Alba Municipio.Cuautitlán Izcalli Estado de México C.P. 54750. Nuestros teléfonos son: (52) 5868-3016.<br>
                                    <br>
                                    DATOS PERSONALES QUE SOLICITAMOS<br>
                                    <br>
                                    La información solicitada al usuario en nuestro sitio es:<br>
                                    <br>
                                    Nombre de la persona que realiza el registro<br>
                                    Cuenta del Usuario<br>
                                    Representante Legal<br>
                                    Nombre y/o razón social<br>
                                    Dirección de correo electrónico<br>
                                    Nombre de la empresa<br>
                                    Teléfono (incluyendo lada)<br>
                                    Datos de facturación<br>
                                    <br>
                                    Los datos recabados nos permiten ponernos en contacto con los usuarios, solicitamos sus datos personales para que pueda contratar nuestros servicios, para generar una cuenta con contraseña para el usuario, para enviar avisos relativos al contrato del servicio o paquetes; notificar actualizaciones del servicio o paquetes, informar sobre nuevas regulaciones y dar respuesta a dudas y comentarios de nuestros usuarios.<br>
                                    <br>
                                    Es importante recalcar que la información deberá ser veraz y completa. El usuario o solicitante responderá en todo momento por la veracidad de los datos proporcionados y en ningún caso TACTS será responsable al respecto.<br>
                                    <br>
                                    En la recolección de Datos Personales seguimos todos los principios que señala el Art. 6 de la Ley Federal de Protección de Datos Personales, que son: licitud, calidad, consentimiento, información, finalidad, lealtad, proporcionalidad y responsabilidad.<br>
                                    <br>
                                    LIMITACIÓN DE USO Y DIVULGACIÓN DE INFORMACIÓN<br>
                                    <br>
                                    En nuestro programa de notificación de promociones, ofertas y servicios a través de correo electrónico, sólo TACTS tiene acceso a la información recabada. Este tipo de publicidad se realiza mediante avisos y mensajes promocionales de correo electrónico, los cuales sólo serán enviados a usted y a aquellos contactos registrados para tal propósito, esta indicación podrá usted modificarla en cualquier momento enviándonos un correo a alertas@tacts.mx<br>
                                    <br>
                                    DERECHOS ARCO (ACCESO, RECTIFICACIÓN, CANCELACIÓN Y OPOSICIÓN)<br>
                                    <br>
                                    El usuario puede ejercer sus derechos en cualquier momento enviándonos un correo a alertas@tacts.mx. Su petición deberá ir acompañada de los fundamentos por los que solicita el ejercicio de sus derechos y una identificación oficial del titular de los datos o de su apoderado. En un plazo máximo de 20(veinte) días hábiles atenderemos su solicitud y le informaremos sobre la procedencia de la misma a través del correo electrónico del que provenga la petición.<br>
                                    Asimismo, en cualquier momento podrá revocar el consentimiento al tratamiento de sus datos por el mismo medio antes señalado. La TACTS solicita al usuario que actualice sus datos cada vez que éstos sufran alguna modificación, ya que esto permitirá brindarle un servicio eficiente y personalizado.<br>
                                    <br>
                                    TRANSFERENCIAS DE INFORMACIÓN CON TERCEROS<br>
                                    <br>
                                    TACTS no realiza transferencias de información con terceros.<br>
                                    Excepto en casos especiales cuando pensemos que proporcionar esta información puede servir para identificar, localizar o realizar acciones legales contra personas que pudiesen infringir las condiciones del servicio de cualquier sitio propiedad de TACTS, o causar daños o interferencia sobre los derechos de cualquier sitio de TACTS o sus propiedades, de otros usuarios de estos sitios o de cualquier otra persona que pudiese resultar perjudicada por dichas actividades, siempre y cuando hayan sido requeridos por orden judicial para cumplir con las disposiciones procesales correspondientes.<br>
                                    <br>
                                    QUÉ SON LOS COOKIES Y CÓMO SE UTILIZAN<br>
                                    <br>
                                    Los cookies son pequeñas piezas de información que son enviadas por el sitio Web a su navegador y se almacenan en el disco duro de su equipo y se utilizan para determinar sus preferencias cuando se conecta a los servicios de nuestros sitios, así como para rastrear determinados comportamientos o actividades llevadas a cabo por usted dentro de nuestros sitios. En algunas secciones de nuestro sitio requerimos que el cliente tenga habilitados los cookies ya que algunas de las funcionalidades requieren de éstas para trabajar. La utilización de cookies no será utilizada para identificar a los usuarios, con excepción de los casos en que se investiguen posibles actividades fraudulentas.<br>
                                    El empleo de cookies no será utilizado para identificar a los usuarios, con excepción de los casos en que se investiguen posibles actividades fraudulentas. Si el usuario quisiera deshabilitar las cookies, deberá hacerlo directamente en su explorador de internet.<br>
                                    <br>
                                    DATOS PERSONALES SENSIBLES<br>
                                    <br>
                                    TACTS no solicita datos personales sensibles en el sitio www.tacts.com.mx.<br>
                                    <br>
                                    PROTECCIÓN DE LA INFORMACIÓN PERSONAL<br>
                                    <br>
                                    En el sitio web www.tacts.com.mx. utilizamos un candado SSL (Secure Socket Layer) para encriptar la información registrada y que viaje segura durante su transmisión. Le recomendamos que no revele su información personal, incluidos usuarios y contraseñas, a nadie. En todo momento, el usuario es el responsable único y final de mantener en secreto su sus datos personales.<br>
                                    <br>
                                    CONFIDENCIALIDAD DE LOS MENORES<br>
                                    <br>
                                    TACTS no solicita información de identificación personal a los menores. Los menores siempre deben solicitar permiso a sus padres o tutores antes de enviar información personal a otro usuario que se encuentre en línea o ingresarla en cualquier sitio web. Si identificamos que algún menor de edad ha ingresado información, ésta se eliminará automáticamente.<br>
                                    <br>
                                    CAMBIOS EN EL AVISO DE PRIVACIDAD<br>
                                    <br>
                                    Este aviso puede tener cambios en el futuro, de ser así se le avisará oportunamente en la sección de Aviso de Privacidad del sitio www.tacts.com.mx.<br>
                                    <br>
                                    ACEPTACIÓN DE LOS TÉRMINOS<br>
                                    <br>
                                    Esta declaración de Confidencialidad/Privacidad constituye un acuerdo legal entre el usuario y la TACTS. Si el usuario utiliza los servicios de este sitio web, significa que ha leído, entendido y consentido los términos antes expuestos. Para resolver cualquier duda en este sentido, nos podrá contactar al correo alertas@tacts.mx<br>
                                    <br>
                                    AUTORIDAD<br>
                                    <br>
                                    Si el usuario considera que han sido vulnerados sus derechos respecto de la protección de datos personales, tiene el derecho de acudir a la autoridad correspondiente para defender su ejercicio. La autoridad es el Instituto Federal de Acceso a la Información y Protección de Datos (IFAI), su sitio web es: www.ifai.mx.<br>
                                    <br>
                                    He leído detenidamente lo anterior y acepto lo establecido en el Aviso de Privacidad de TACTS.
                                </p>
                                <br>
                            </div>
                        </div>
                    </div>
                </div> 

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
            
            function addRegistro(){
                swal("", "La información se registro correctamente", "success");  alertclose();
            }
            
            function habilitarDatosContratacion(tipo) {

                if (tipo === '1') {

                    div = document.getElementById('tab-LugarDeEmbarque');
                    div.style.display = '';
                    div = document.getElementById('tab-Destinatario');
                    div.style.display = 'none';
                    div = document.getElementById('tab-LugarDeEntrega');
                    div.style.display = 'none';
                    div = document.getElementById('tab-Mercancia');
                    div.style.display = 'none';
                    div = document.getElementById('tab-Autotransporte');
                    div.style.display = 'none';

                } else if (tipo === '2') {

                    div = document.getElementById('tab-Destinatario');
                    div.style.display = '';
                    div = document.getElementById('tab-LugarDeEmbarque');
                    div.style.display = 'none';
                    div = document.getElementById('tab-LugarDeEntrega');
                    div.style.display = 'none';
                    div = document.getElementById('tab-Mercancia');
                    div.style.display = 'none';
                    div = document.getElementById('tab-Autotransporte');
                    div.style.display = 'none';

                } else if (tipo === '3') {

                    div = document.getElementById('tab-LugarDeEntrega');
                    div.style.display = '';
                    div = document.getElementById('tab-LugarDeEmbarque');
                    div.style.display = 'none';
                    div = document.getElementById('tab-Destinatario');
                    div.style.display = 'none';
                    div = document.getElementById('tab-Mercancia');
                    div.style.display = 'none';
                    div = document.getElementById('tab-Autotransporte');
                    div.style.display = 'none';

                } else if (tipo === '4') {
                    //Mercancias
                    div = document.getElementById('tab-Mercancia');
                    div.style.display = '';
                    div = document.getElementById('tab-LugarDeEmbarque');
                    div.style.display = 'none';
                    div = document.getElementById('tab-Destinatario');
                    div.style.display = 'none';
                    div = document.getElementById('tab-LugarDeEntrega');
                    div.style.display = 'none';
                    div = document.getElementById('tab-Autotransporte');
                    div.style.display = 'none';

                } else if (tipo === '5') {

                    div = document.getElementById('tab-Autotransporte');
                    div.style.display = '';
                    div = document.getElementById('tab-LugarDeEmbarque');
                    div.style.display = 'none';
                    div = document.getElementById('tab-Destinatario');
                    div.style.display = 'none';
                    div = document.getElementById('tab-LugarDeEntrega');
                    div.style.display = 'none';
                    div = document.getElementById('tab-Mercancia');
                    div.style.display = 'none';
                }
            }
            
            
            function habilitarDatosPago(tipo) {

                if (tipo === '1') {

                    div = document.getElementById('collapseOne');
                    div.style.display = '';
                    div = document.getElementById('collapseTwo');
                    div.style.display = 'none';
                    div = document.getElementById('collapseThree');
                    div.style.display = 'none';
                    div = document.getElementById('collapseFour');
                    div.style.display = 'none';

                } else if (tipo === '2') {

                    div = document.getElementById('collapseTwo');
                    div.style.display = '';
                    div = document.getElementById('collapseOne');
                    div.style.display = 'none';
                    div = document.getElementById('collapseThree');
                    div.style.display = 'none';
                    div = document.getElementById('collapseFour');
                    div.style.display = 'none';

                } else if (tipo === '3') {

                    div = document.getElementById('collapseThree');
                    div.style.display = '';
                    div = document.getElementById('collapseOne');
                    div.style.display = 'none';
                    div = document.getElementById('collapseTwo');
                    div.style.display = 'none';
                    div = document.getElementById('collapseFour');
                    div.style.display = 'none';

                } else if (tipo === '4') {
                    //Mercancias
                    div = document.getElementById('collapseFour');
                    div.style.display = '';
                    div = document.getElementById('collapseThree');
                    div.style.display = 'none';
                    div = document.getElementById('collapseTwo');
                    div.style.display = 'none';
                    div = document.getElementById('collapseThree');
                    div.style.display = 'none';

                } 
            }
            
            function delete_registro() {
                    swal({
              title: "¿Estás seguro?",
              text: "Si cierras la ventana tus datos no seran guardados",
              type: "warning",
              showCancelButton: true,
              confirmButtonColor: "#DD6B55",
              confirmButtonText: "Sí, cancelar.",
              cancelButtonText: "¡No, cancele por favor!",
              closeOnConfirm: false,
              closeOnCancel: false
            },
            function(isConfirm){
              if (isConfirm) {
                   window.location.href = "index.jsp";
              }else{
                  swal.close();
              } 
            });
            }
            
            function alertclose() {
                setTimeout(function () {
                    swal.close();
                }, 2000);
            }
        </script>                  
        <!-- JavaScript files-->
        <script src="lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
        <!-- Data Tables-->
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"></script>
        <script src="lib/js/datatable-reciboPago.js" type="text/javascript"></script>
        <script src="lib/js/datatable-Dashboard-facturasPendientes.js" type="text/javascript"></script>
        <!-- Main Theme JS File-->
        <script src="lib/js/theme.js"></script>
        <!-- Prism for syntax highlighting-->
        <script src="lib/vendor/prismjs/prism.js"></script>
        <script src="lib/vendor/prismjs/plugins/normalize-whitespace/prism-normalize-whitespace.min.js"></script>
        <script src="lib/vendor/prismjs/plugins/toolbar/prism-toolbar.min.js"></script>
        <script src="lib/vendor/prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard.min.js"></script>
        <!-- jQuery/show modal -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!-- sweetalert -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
        <script type="text/javascript">
            // Optional
            Prism.plugins.NormalizeWhitespace.setDefaults({
                'remove-trailing': true,
                'remove-indent': true,
                'left-trim': true,
                'right-trim': true,
            });

        </script>
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    </body>
</html>