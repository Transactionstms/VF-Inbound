<%-- 
    Document   : OnlineService
    Created on : 4/04/2022, 04:13:02 PM
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
        <title>Servicio de facturación en línea</title>
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
        <style>
            .wired, .unwired {
                display: none;
            }

            body.online .wired {
                display: block;
            }

            body.offline .unwired {
                display: block;
            }
            body.offline .wired {
                display: none;
            }
        </style>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String cve = (String) ownsession.getAttribute("cbdivcuenta");
                String userId = (String) ownsession.getAttribute("login.user_id_number");
                //String num_ticket = request.getParameter("t");
                String folio = "";

                ConsultasQuery fac = new ConsultasQuery();

                if (db.doDB(fac.consultarFolioFactura("F",cve))) {
                    for (String[] row : db.getResultado()) {
                        folio = row[0];
                    }
                }
        %>
        <!-- navbar-->
        <header class="header">
            <nav class="navbar navbar-light bg-red-light">
                <div class="col-sm-10">
                    <!--<img src="lib/img/logo-tacts-removebg-preview.png" width="7%"/>-->
                    <img src="lib/img/logo6-removebg-preview.png" width="5%"/>
                </div>
            </nav>
        </header>
        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <div class="unwired alert alert-danger">¡Se ha perdido su conexión! TACTS debe de estar conectado a Internet para su correcto funcionamiento.</div>
                    <section>
                        <div class="row">    
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card h-100">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h2 class="card-heading">Servicio de facturación en línea</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-sm-12" style="padding-top: 5px"> 
                                                <label style="font-family: Arial; font-weight: bold; color:#000000;" size="2">&nbsp;Importante: </label>&nbsp;"Para facturar, el periodo no debe ser mayor a los 30 días naturales a partir del mes siguiente de la fecha de su compra".
                                            </div>
                                        </div>
                                        <br><br>
                                        <!-- check - editar conceptos -->
                                        <div class="row">
                                            <div class="col-3">
                                                <input class="form-check-input" type="radio" id="check_datosGenerales" name="check_datosGenerales" value="1" onclick="habilitarDatosGenerales()" checked="true">  
                                                <label class="custom-control-label" for="customCheck1">1. Datos Generales</label>
                                            </div>
                                            <div class="col-6">
                                                <input class="form-check-input" type="radio" id="check_informacionFiscal" name="check_informacionFiscal" value="2" onclick="habilitarInformacionFiscal()">  
                                                <label class="custom-control-label" for="customCheck1">2. Información Fiscal</label>
                                            </div>
                                            <hr>
                                        </div>
                                        <br>
                                        <!-- 1. Facturación -->
                                        <div id="tab-LugarDeEmbarque" class="tab-pane">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-md-2">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Número de Ticket:&nbsp;<label style="color:#db2a2a;">*</label>&nbsp;&nbsp;<img src="lib/img/help-removebg-preview.png" data-bs-toggle="modal" data-bs-target="#modalHelp" width="3%"/></label>
                                                        <input class="form-control" id="num_ticket" name="num_ticket" type="text" autocomplete="off" onkeypress="return soloNumeros(event)">
                                                        <button class="btn btn-info" onclick="sendmail()">Buscar</button>
                                                        <br>
                                                        <pre id="ticketOK" style="font-family: Arial; font-weight: bold; color:#4d73d1;" size="1"></pre>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">RFC:&nbsp;<label style="color:#db2a2a;">*</label></label>
                                                        <input class="form-control" type="text" id="rfc" name="rfc"  value=""  autocomplete="off" oninput="validarInput(this)" onkeyup="this.value = this.value.toUpperCase()" disabled="true">
                                                        <br>
                                                        <pre id="rfcOK" style="font-family: Arial; font-weight: bold; color:#4d73d1;" size="1"></pre>
                                                    </div>
                                                    <div class="col-md-1">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">C.P.:&nbsp;<label style="color:#db2a2a;">*</label></label>
                                                        <input class="form-control" type="text" id="codigo_postal" name="codigo_postal" value="" autocomplete="off" onkeypress="return soloNumeros(event)" maxlength="5" disabled="true">
                                                        <br>
                                                        <pre id="rfcOK" style="font-family: Arial; font-weight: bold; color:#4d73d1;" size="1"></pre>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Nombre o Razón Social:&nbsp;<label style="color:#db2a2a;">*</label></label>
                                                        <input class="form-control" id="razon_social" name="razon_social" type="text" value="" autocomplete="off" onkeyup="this.value = this.value.toUpperCase()" disabled="true">
                                                        <br>
                                                        <pre id="razonSocialOK" style="font-family: Arial; font-weight: bold; color:#4d73d1;" size="1"></pre>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Correo Electrónico:&nbsp;<label style="color:#db2a2a;">*</label></label>
                                                        <input class="form-control" id="correo" name="correo" type="text" value="" autocomplete="off" disabled="true">
                                                        <br>
                                                        <pre id="emailOK" style="font-family: Arial; font-weight: bold; color:#4d73d1;" size="1"></pre>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="checkbox">
                                                            <input id="chk_Acepto" name="chk_Acepto" type="checkbox" value=""><label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">&nbsp;&nbsp;Acepto</label> los <a data-bs-toggle="modal" data-bs-target="#myModalContrato"><strong><u style="font-family: Arial; font-weight: bold; color:#707375;">Términos y Condiciones</u></strong></a> y el <a data-bs-toggle="modal" data-bs-target="#myModalAvisoPrivacidad"><strong><u style="font-family: Arial; font-weight: bold; color:#707375;">Aviso de Privacidad.</u></strong></a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="checkbox">
                                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#000000; font-size: 13px;">¡No recibí la factura electrónica!&nbsp;&nbsp;<u style="color:#2a80db;" onclick="openModalSoporte()">Contactar Soporte</u></label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12" style="text-align: right;">
                                                    <a class="btn btn-primary text-nowrap" role="button" onclick="habilitarInformacionFiscal()" disabled>Continuar</a>
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <a class="btn btn-default text-nowrap" role="button" onclick="delete_registro()">Cancelar</a>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- 2. Información Fiscal -->
                                        <div id="tab-Destinatario" class="tab-pane" style="display:none;">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <table class="table" id="mytable">
                                                        <thead>
                                                            <tr>
                                                                <th>Descripción</th>
                                                                <th>Cantidad</th>
                                                                <th>Precio Unitario</th>
                                                                <th>Importe Descuento</th>
                                                                <th>Importe</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="AddTableConceptos"></tbody>
                                                    </table>
                                                </div>
                                                <br><br>                        
                                                <div class="row">
                                                    <div class="form-group col-md-8 mb-3">
                                                        <div class="mb-3">
                                                            <div class="row row-cols-lg">
                                                                <div class="form-check col-md-3">
                                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Uso de CFDI:</label>
                                                                </div>
                                                                <div class="form-check col-md-8">
                                                                    <div class="input-group">
                                                                        <select class="form-control selectpicker col-md-5" id="usocfdi_id" name="usocfdi_id">
                                                                            <option value="21" selected>G03 - Gastos en general</option>
                                                                            <%
                                                                                if (db.doDB(fac.consultarUsoCfdi())) {
                                                                                    for (String[] row : db.getResultado()) {
                                                                                        out.println("<option value=\"" + row[0] + "\" >" + row[1] + " - " + row[2] + "</option>");
                                                                                    }
                                                                                }
                                                                            %>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>   
                                                                        
                                                        <input type="hidden" id="comprobante_id" name="comprobante_id" value="1"/>
                                                        <input type="hidden" id="documento_id" name="documento_id" value="1"/>
                                                        <input type="hidden" id="metodo_id" name="metodo_id" value="1"/>
                                                        <input type="hidden" id="moneda_id" name="moneda_id" value="2"/>
                                                        
                                                        <div class="mb-3">
                                                            <div class="row row-cols-lg">
                                                                <div class="form-check col-md-3">
                                                                    <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Régimen fiscal:</label>
                                                                </div>
                                                                <div class="form-check col-md-8">
                                                                    <div class="input-group">
                                                                        <select class="form-control" data-val="true" id="regimen_id" name="regimen_id">
                                                                            <%
                                                                                if (db.doDB(fac.consultarRegimenFiscal())) {
                                                                                    for (String[] row : db.getResultado()) {
                                                                                        out.println("<option value=\"" + row[0] + "\" >" + row[1] + " - " + row[2] + "</option>");
                                                                                    }
                                                                                }
                                                                            %>
                                                                        </select>
                                                                        <div id="val_regimen_id" style="color:#3b83bd;"></div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="mb-3">
                                                            <div class="row row-cols-lg">
                                                                <div class="form-check col-md-3">
                                                                    <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Forma de Pago:&nbsp;<label style="color:#db2a2a;">*</label></label>
                                                                </div>
                                                                <div class="form-check col-md-8">
                                                                    <div class="input-group">
                                                                        <select class="form-control" data-val="true" id="forma_id" name="forma_id">
                                                                            <option value="0" disabled selected>-- Seleccione forma de pago --</option>
                                                                            <%
                                                                                if (db.doDB(fac.consultarFormaPagoPlebes())) {
                                                                                    for (String[] row : db.getResultado()) {
                                                                                        out.println("<option value=\"" + row[0] + "\" >" + row[0] + " - " + row[1] + "</option>");
                                                                                    }
                                                                                }
                                                                            %>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>                        
                                                    </div>
                                                    <div class="form-group col-md-4">
                                                        <table class="table" id="datatable1">
                                                            <thead>
                                                                <tr><th class="">Subtotal:</th>
                                                                    <td><div id="subtotalGral"></div></td></tr>
                                                                <tr><th class="">Importe Descuento:</th>
                                                                    <td><div id="descuentoGral"></div></td></tr>
                                                                <tr><th class="">Iva:</th>
                                                                    <td><div id="trasladosGral"></div></td></tr>
                                                                <!--<tr><th class="">Retenciones:</th>
                                                                    <td><div id="retencionesGral"></div></td></tr>-->
                                                                <tr><th class="">Total:</th>
                                                                    <td><div id="totalGral"></div></td></tr>
                                                            </thead>
                                                        </table>
                                                    </div>
                                                </div>
                                                <!--loader-->
                                                <div class="row" id="loader-folding-circle" style="display:none;">
                                                    <div class="card-body d-flex justify-content-center pt-5 pb-5">
                                                        <div class="sk-folding-cube">
                                                            <div class="sk-cube1 sk-cube"></div>
                                                            <div class="sk-cube2 sk-cube"></div>
                                                            <div class="sk-cube4 sk-cube"></div>
                                                            <div class="sk-cube3 sk-cube"></div>
                                                        </div> 
                                                    </div>
                                                    <div class="row">
                                                        <center>Almacenando información....</center>
                                                    </div>
                                                </div> 
                                                <br>
                                                <div class="col-lg-12" style="text-align: right;">
                                                    <a class="btn btn-primary text-nowrap" role="button" onclick="visorPdfFactura(1)"><i class="fa fa-file"> </i>&nbsp;Vista Previa</a>
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <a class="btn btn-primary text-nowrap" role="button" onclick="AddFacturacion(2)"><i class="fa fa-file"> </i>&nbsp;Emitir Factura</a> 
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <a class="btn btn-default text-nowrap" role="button" onclick="delete_registro()">Cancelar</a>
                                                </div>
                                            </div>    
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>    
                        <!-- Parametros Generales -->
                        <input type="hidden" id="tipo_descripcion" name="tipo_descripcion" value="1">
                        <input type="hidden" id="serie" name="serie" value="F">
                        <input type="hidden" id="folio" name="folio" value="<%=folio%>">
                        <input type="hidden" id="fecha_emision" name="fecha_emision" value="<%=fecha%>">
                        <input type="hidden" id="hora_emision" name="hora_emision" value="<%=hora%>">
                        <!-- contadores tablas -->
                        <input type="hidden" id="numConceptos" name="numConceptos">
                        <!-- Totales Gral -->
                        <input type="hidden" id="cant_subtotal_gral" name="cant_subtotal_gral">   
                        <input type="hidden" id="cant_descuento_gral" name="cant_descuento_gral">  
                        <input type="hidden" id="cant_traslados_gral" name="cant_traslados_gral">  
                        <input type="hidden" id="cant_retenciones_gral" name="cant_retenciones_gral">  
                        <input type="hidden" id="cant_total_gral" name="cant_total_gral"> 
                        <input type="hidden" id="cant_impuesto_gral" name="cant_impuesto_gral">  
                        <input type="hidden" id="cant_factor_gral" name="cant_factor_gral">  
                        <input type="hidden" id="cant_tasa_gral" name="cant_tasa_gral">  
                        <input type="hidden" id="cant_cuota_gral" name="cant_cuota_gral">  
                        <input type="hidden" id="tipo_impueto_gral" name="tipo_impueto_gral">
                    </section>
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
                                CONTRATO DE SERVICIO DE USO.<br><br>Por favor lea cuidadosamente los términos y condiciones del presente contrato de uso del servicio de Emisión y Certificación de CFDI a través de la plataforma PremiumOne y del sitio comercial publicado en www.tacts.com.mx, los cuales son propiedad de <strong>Dulce Maria Bucio Rueda</strong>. en delante TACTS, (en adelante los SITIOS).<br><br>EL USUARIO, al llenar cualquier forma de registro para usar los servicios o paquetes dentro los SITIOS de TACTS le atribuye a quien lo hace la condición de USUARIO REGISTRADO (en adelante 	EL CLIENTE) y lo sujeta a la aceptación plena y sin reservas de todos y cada uno de los términos y condiciones de uso aquí detallados, no importando la versión publicada a los SITIOS por TACTS.<br><br>Si usted no está de acuerdo con todos y cada uno de los términos y condiciones de uso aquí detallados, usted no tendrá el derecho de usar los servicios o paquetes dentro de los SITIOS de TACTS.<br><br>Las páginas Web y sus funcionalidades disponibles en los SITIOS, así como todas las páginas híper vinculadas en dichas páginas Web (a menos que se indique lo contrario) son propiedad y están operados por TACTS y están disponibles para el CLIENTE bajo los siguientes términos y condiciones:<br><br>1.- OBJETO DE LOS SITIOS DE TACTS. - TACTS ofrece servicios o paquetes, todos relacionados con la emisión, certificación, validación, recepción, almacenamiento, resguardo, consulta y transmisión de comprobantes fiscales digitales (en adelante CFDI) al CLIENTE.<br><br>2.- PERSONALIDAD JURÍDICA. - El CLIENTE certifica ante TACTS que tiene toda la capacidad personal y jurídica para adherirse a estos términos y condiciones de uso que aquí se detallan y se hace responsable de la selección y uso de los servicios o paquetes a su conveniencia dentro LOS SITIOS de TACTS.<br><br>3.- MODIFICACIONES A LOS TÉRMINOS Y CONDICIONES. - TACTS se reserva el derecho a su propia discreción, de modificar estos términos y condiciones de uso de TACTS en cualquier momento con la obligación de notificarle al CLIENTE de este a través de una nota en LOS SITIOS de TACTS a través de una notificación por correo electrónico. El uso de estos servicios o paquetes por parte del CLIENTE después de dichas notificaciones constituyen su más amplia aceptación a los nuevos términos y condiciones de uso de TACTS. TACTS se compromete a que las modificaciones que se hagan nunca violaran los requerimientos legales que disponga el SAT (Servicio de Administración Tributaria), ni ninguna otra legislación aplicable o autoridad competente en la materia.<br><br>4.- CONEXIÓN A LOS SITIOS DE TACTS. - El CLIENTE está de acuerdo en cumplir con los requisitos mínimos necesarios para poderse conectar a los SITIOS de TACTS y así poder usar los servicios o paquetes. Corren por cuenta del CLIENTE los recursos necesarios para conectarse a la red de internet, incluyendo de forma enunciativa más no limitativa, la computadora, PC, Notebook, Laptop o cualquier dispositivo que el CLIENTE use para dicho fin.<br><br>5.- DE LOS SERVICIOS Y PAQUETES. - Los servicios o paquetes sólo pueden darse mediante el pago de una remuneración y de la forma en que se indica expresamente en sus correspondientes condiciones. Para ver los servicios o paquetes y cuales son así como sus condiciones particulares, favor de revisar el sitio www.tacts.com.mx en la sección de “Soluciones”.<br><br>6.- PRECIOS Y PAGOS. - El CLIENTE acepta que los servicios o paquetes son adquiridos mediante el pago establecido por TACTS, estas condiciones particulares y específicas como se describe en el sitio www.tacts.com.mx, el CLIENTE se hará responsable del pago en tiempo y forma de los servicios o paquetes seleccionados. El pago por el o los paquetes adquiridos por parte de EL CLIENTE, deberán realizarse por anticipado, ante la falta del cumplimiento de pago por parte de EL CLIENTE; TACTS podrá suspender el acceso y los servicios de EL CLIENTE sin ninguna responsabilidad para TACTS. TACTS se reserva el derecho de cambiar su lista de precios y/o de agregar nuevos servicios o paquetes.<br><br>7.- CAMBIOS EN LOS SERVICIOS O PAQUETES. - TACTS puede cambiar, suspender o descontinuar los servicios (o el acceso del CLIENTE a los mismos) en cualquier momento incluyendo la disponibilidad de cualquier funcionalidad, obligándose TACTS a notificar EL CLIENTE mediante los sitios y/o por correo electrónico.<br><br>8.- CLAVES DE ACCESO. - TACTS reserva algunos de los SERVICIOS O PAQUETES ofrecidos a través de los SITIOS a los CLIENTES registrados mediante el llenado de la forma de registro de crear cuenta y la generación de los mecanismos de acceso y control correspondientes, tales como nombre de usuario y contraseña (en adelante las CLAVES DE ACCESO). El CLIENTE se compromete a hacer un uso diligente de las CLAVES DE ACCESO, así como a no ponerla a disposición de terceros y a comunicar a TACTS a la mayor brevedad la pérdida o robo de dichas CLAVES DE ACCESO, así como cualquier riesgo de acceso a las mismas por un tercero. El CLIENTE se obliga a: (I) inmediatamente notificar a TACTS de cualquier uso no autorizado sobre sus CLAVES DE ACCESO o de cualquier otro evento que haya violado la seguridad e integridad de estas y (II) a siempre terminar su sesión de forma correcta dentro de los servicios o paquetes que soliciten CLAVES DE ACCESO. TACTS no se puede responsabilizar de cualquier daño o pérdida suscitada por parte de EL CLIENTE, si este no se obligó a los puntos (I) y (II) de esta cláusula número 8. TACTS tiene el derecho de dar de baja la clave de acceso del CLIENTE si éste no ha tenido actividad alguna en un periodo mínimo de 30 treinta días.<br><br>9.- VERACIDAD, SEGURIDAD Y CONFIDENCIALIDAD DE LA INFORMACIÓN. - Como una condición para usar los servicios o paquetes que requieran registro, el CLIENTE deberá de llenar la información requerida, de forma completa, veraz y actualizada. El no llenar la información de forma completa, veraz y actualizada en los SITIOS de TACTS por parte del CLIENTE, éste incumple inmediatamente con los términos de uso y condiciones de TACTS y TACTS se reserva el derecho de cancelar y dar por terminado de forma inmediata sin ninguna responsabilidad para TACTS. El CLIENTE será el único responsable de mantener la confidencialidad de sus CLAVES DE ACCESO y de todas las actividades que sucedan en su cuenta. La confidencialidad de la información del CLIENTE se regirá por las Políticas de Privacidad de TACTS entre el CLIENTE e TACTS.<br><br>TACTS por su parte, se compromete a garantizar la seguridad y confidencialidad de la información que EL CLIENTE proporcione a TACTS, a través de EL SITIO y que es requerida para efectos de recibir el servicio de la emisión y certificación del CFDI.<br><br>Para efectos de dar cumplimiento con lo anterior, TACTS implementará mecanismos de transmisión segura de información entre EL CLIENTE e TACTS en ambos sentidos de la comunicación, a través del protocolo seguro https, empleando certificados SSL emitidos por terceros certificados y reconocidos para tales fines. EL CLIENTE por su parte, acepta la implementación de estos mecanismos por parte de TACTS y se compromete a no realizar prácticas que atenten contra la seguridad antes, durante y después de la transmisión de la información.<br><br>Asimismo, TACTS se compromete en el mismo tenor, a salvaguardar la integridad y seguridad de los Certificados de Sellos Digitales (en delante CSD) que son propiedad EL CLIENTE y que éste último entrega de total conformidad a TACTS a través de EL SITIO para los fines de la certificación de los CFDI como parte de los requisitos establecidos en la Resolución Miscelánea Fiscal vigente, para lo cual EL CLIENTE emite mediante EL SITIO de TACTS. TACTS, implementará los mecanismos de seguridad necesarios para salvaguardar la integridad de los CSD.<br><br>10.- COMUNICACIÓN CON TACTS. - El CLIENTE dirigirá a TACTS cualquier comunicación relacionada con el uso de los servicios o paquetes de los SITIOS de TACTS.<br><br>11.- NIVELES DEL SERVICIO (SLA). - TACTS con todos sus servicios estará disponible 7(siete) días las 24(veinticuatro) horas los 365(trecientos sesenta y cinco) días, con una disponibilidad de 99.3% por ciento, donde el .07 por ciento es el periodo de tiempo máximo en que la aplicación podría no estar disponible por causas no planeadas e imputables a TACTS.<br><br>Para mejoras y mantenimiento continuo a la infraestructura y a la aplicación, TACTS podrá planear y ejecutar ventanas de mantenimiento trimestrales, de máximo 24 horas, lo anterior será notificado a EL CLIENTE a través de la página de Internet o portal web de TACTS con una anticipación de por lo menos 7 (siete) días.<br><br>Las solicitudes de soporte y quejas serán atendidas por TACTS en un plazo no mayor a 2 (dos) horas y resueltas en un plazo no mayor 8 (ocho) horas.<br><br>Los medios de contacto por los cuales EL CLIENTE solicitará dicho soporte son mediante el correo electrónico alertas@tacts.mx, chat con acceso a través de http://www.tacts.com.mx.<br><br>TACTS no será responsable de cualquier falla para suministrar los servicios o paquetes cuando éstas fallas sean por caso fortuito o fuerza mayor o el resultado de cualquier causa que esté fuera de control razonable. TACTS hará todo lo posible para remediar con sus recursos la falla. En caso de demanda por lo anterior, si alguna provisión de este acuerdo se encuentre invalidada o en riesgo, la corte le dará el efecto de la intención de la parte como se refleja en dicha provisión, pero las demás provisiones se mantendrán con toda la fuerza y efecto.<br><br>12.- CONTENIDOS.- Los SITIOS y sus materiales desplegados en cualquier formato incluyendo de forma enunciativa más no limitativa, textos, gráficas, artículos, fotografías, imágenes, logotipos, ilustraciones, enlaces o hipervínculos a LOS SITIOS o a los sitios web de terceros, clips de audio, clips de video y en general todo el material salvo se indique lo contrario, están protegidos por las leyes internacionales, así como a las Leyes Federales y la Ley de la Propiedad Industrial vigente y su intención es exclusivamente para el uso de TACTS con sus CLIENTES y sólo se pueden usar dentro del marco de este acuerdo y en conexión con el uso autorizado de los servicios de TACTS. Los contenidos que sean accesibles a través de LOS SITIOS y SERVICIOS O PAQUETES DE TACTS están protegidos por las leyes de derechos al autor.<br><br>13.- LÍMITES DE RESPONSABILIDAD DE LOS CONTENIDOS. - TACTS excluye cualquier responsabilidad por los daños y perjuicios de toda naturaleza que puedan deberse a la transmisión, difusión, almacenamiento, puesta a disposición, recepción, obtención o acceso a los contenidos.<br><br>14.- MARCAS. - TACTS y otras marcas y sus respectivos logotipos indicados en los SITIOS son marcas registradas de Sistemas de Emisión Digital S.A. de C.V. Cualquier otra marca que no sea propiedad de Sistemas de Emisión Digital S.A. de C.V. y que aparezca en los SITIOS es propiedad de su respectivo titular, el cual puede o no estar relacionado, conectado o soportado por Sistemas de Emisión Digital S.A. de C.V., TACTS no concede ninguna licencia o autorización de uso de ninguna clase sobre sus derechos de propiedad industrial e intelectual o sobre cualquier otra propiedad o derecho relacionado los SITIOS, los servicios o paquetes o los contenidos.<br><br>15.- RESTRICCIONES. - El CLIENTE se obliga a dar uso propio y que no utilizará los servicios o paquetes de TACTS para fines diferentes al propuesto por TACTS y no podrá vender, rentar, licenciar, traspasar, arrendar u otra actividad similar, la funcionalidad y/o los servicios o paquetes de los SITIOS de TACTS a terceros. De igual forma se obliga a no generar contenidos o hacer disponible los servicios o paquetes de TACTS, obligándose así a NO generar contenidos que : (I) Infrinjan la ley, sean ilegales, calumniosos, difamatorios, obscenos, abusivos, ofensivos, violentos o de odio, (II) contengan uso de alcohol o abuso de drogas, (III) violen cualquier ley o derecho de terceros, o (IV) promuevan o faciliten cualquiera de lo anterior, ya sea directamente o indirectamente por el CLIENTE.<br><br>16.- RENUNCIAS. - El CLIENTE reconoce y conviene en que TACTS no tiene ninguna relación especial con el CLIENTE y que TACTS no tiene control, ni el deber de adoptar cualquier medida con respecto a la forma en la que el CLIENTE accede a los contenidos o a los SITIOS o los servicios o paquetes; el CLIENTE es el único responsable (y asume toda la responsabilidad y riesgo) para determinar si los contenidos son o no son convenientes o aceptables para el mismo. LOS SITIOS de TACTS puede contener, o el CLIENTE puede tener acceso directo a sitios que contengan información que algunas personas podrían considerar ofensiva o inadecuada. TACTS no hace ninguna representación con respecto a los contenidos en o a través de los SITIOS o los servicios o paquetes, y TACTS se adhiere a los límites de responsabilidad de contenidos detallados en la cláusula 11 once del presente acuerdo.<br><br>17.- INDEMINIZACIÓN A TACTS POR USO INDEBIDO.- En caso de reclamo, investigación, procedimiento o de una demanda de una tercera parte hacia TACTS por el uso indebido de la cuenta del CLIENTE, incluyendo la violación de este acuerdo de términos y condiciones de uso o de violación de derechos de propiedad de los contenidos usando indebidamente la cuenta del CLIENTE u otro derecho reclamado, el CLIENTE deberá indemnizar y mantener a TACTS, sus matrices, subsidiarias, afiliados, funcionarios y empleados en paz, incluidos los gastos y honorarios de abogados.<br><br>El CLIENTE acuerda que TACTS tendrá el control único y exclusivo sobre la defensa y la solución de reclamo con el tercero en discordia.<br><br>18.- LIMITES DE RESPONSABILIDAD DE TACTS PARA CON LOS SERVICIOS O PAQUETES. - TACTS no será responsable con respecto a los SITIOS o de los servicios o paquetes con el CLIENTE por ninguna cantidad que exceda de los pagos realizados por el CLIENTE para ello, o por daños indirectos, incidentales, punitivos o consecuentes de cualquier tipo.<br><br>19.- DISPUTA DE DERECHOS DE AUTOR. - Por favor revise nuestras Aviso de Privacidad de TACTS ya que al ser CLIENTE dentro de los SITIOS de TACTS, el CLIENTE estará de acuerdo como se usa, se administra, se divulga, se guarda y se colecta la información personal y no personal en base a los lineamientos y prácticas que marcan dichas políticas. El CLIENTE acepta que TACTS pueda garantizar la integridad, el uso, manejo responsable y confidencial de la información, TACTS puede colectar, transmitir y usar dicha información en cualquiera de los centros de datos de TACTS, siempre regulados por las mismas políticas de privacidad EL CLIENTE acepta que TACTS entregara información confidencial del CLIENTE a autoridades competentes que así nos lo requieran y únicamente bajo mandato judicial de acuerdo con las leyes aplicables en los Estados Unidos Mexicanos.<br><br>20.- CONFIDENCIALIDAD. - El CLIENTE acuerda que NO podrá divulgar información confidencial de TACTS sin el consentimiento previo por escrito de TACTS. "Información Confidencial" incluye, sin limitación: (I) todo el software de TACTS, tecnología, programación, especificaciones técnicas, materiales, directrices y documentación que el CLIENTE aprenda, desarrolle u obtenga de los SITIOS de TACTS, (II) cualquier otra información designada por escrito por TACTS como "CONFIDENCIAL" o cualquier otra en el mismo sentido. "INFORMACIÓN CONFIDENCIAL" NO incluye la información que se ha hecho pública, o la información que se haya desarrollado de forma independiente sin acceder a la información confidencial de TACTS y que conste por escrito, o que haya sido obtenida legalmente por el CLIENTE de una tercera fuente, o que haya sido revelada por requerimiento de ley o judicial por una autoridad gubernamental de los Estados Unidos Mexicanos.<br><br>21.- TERMINACIÓN. - Cualquiera de las partes puede dar por terminado en cualquier momento los servicios o paquetes. Si es el CLIENTE el que da por terminado la relación con TACTS, este mismo deberá enviar un escrito solicitando la baja del servicio, firmada por representante legal en el caso de ser persona moral y en el caso de persona física por la persona contratante, TACTS no reembolsará ningún pago por SERVICIOS O PAQUETES que el CLIENTE haya erogado. Si es TACTS el que da por terminado la relación y existiera algún servicios o paquetes en la cuenta del CLIENTE por el cual el CLIENTE haya realizado un pago, se reembolsará al CLIENTE el equivalente al SERVICIO NO DEVENGADO, mismo que cuando la compra haya sido efectuada dentro de los primeros 30 días, siempre y cuando no haya infringido ningún artículo del contrato. El cálculo del reembolso sería por el equivalente del servicio no devengado de manera proporcional a lo consumo. El pago se realizará 30 días después de la notificación por parte de TACTS y en la misma forma o medio como fue pagado por el CLIENTE a TACTS. En caso de que el CLIENTE solicite el reembolso se considerara lo siguiente debe efectuarlo dentro de los primeros 30 días posteriores a su pago, mediante un escrito justificando la razón por la cual solicita su rembolso, adjuntando su factura de pago y los datos de contratación, una vez reunida dicha información enviarla al correo electrónico alertas@tacts.mx, una vez enviada será sujeta a evaluación y en caso de aplicarse su reembolso se llevará a cabo dentro de los primeros 5 días del mes siguiente; en caso de que hayan transcurrido 30 (treinta) días posteriores a la compra, no procederá dicho reembolso.<br><br>22.- AUTORIZACION DE ENTREGA DE CFDI CERTIFICADOS AL SAT. - Con la finalidad de dar legal cumplimiento a lo después por las Leyes o Códigos Federales de carácter Fiscal, así como reglas o resoluciones que emanen de ellas, haciendo alusión a lo dispuesto en el artículo 29 fracción IV del Código Fiscal de la Federación, así como a las reglas I.2.7.2.5 fracción II y la regla I.2.7.2.7. Párrafo segundo de la resolución miscelánea fiscal 2014, misma que a la letra dice: “los contribuyentes a que se refiere el párrafo anterior están obligados a proporcionar por escrito al proveedor de certificación de CFDI, su manifestación de conocimiento y autorización para que este último entregue al SAT, copia de los comprobantes que les haya certificado”, por lo anterior el CLIENTE manifiesta de conocimiento y autoriza a TACTS a la entrega de copias de los comprobantes que les haya certificado, a fin de que este ultimo los haga llegar al SAT.<br><br>23.- MISCELANEOS. - Este acuerdo de términos y servicios de uso de los SITIOS de TACTS es de carácter personal e intransferible, por lo que no se puede ceder, licenciar, transmitir total o parcialmente los derechos derivados del presente contrato. Este acuerdo se celebra entre el CLIENTE e TACTS.<br><br>24.- LEY Y JURISDICCIÓN APLICABLE. - Estos términos y condiciones de uso generales se rigen por la Ley Federal de Protección de Datos Personales en Posesión de los Particulares vigente y todas las leyes mexicanas. TACTS y el CLIENTE, renuncian expresamente a cualquier otro fuero, y solo se someten al fuero de los Juzgados y Tribunales de la Ciudad de Monterrey, Nuevo León en los Estados Unidos Mexicanos.<br>
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
                                    Sus datos personales serán tratados por <strong>Dulce Maria Bucio Rueda</strong>. (en delante TACTS), responsable de la protección y tratamiento de sus datos personales, con domicilio en Av. Musas #27 Col. Ensueños Municipio. Cuautitlán Izcalli Estado de México C.P. 54740. Nuestros teléfonos son: (52) 5868-3016.<br>
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
                <!-- modal - Help Ticket --> 
                <div class="modal fade text-start" id="modalHelp" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header border-0 bg-gray-100">
                                <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Ayuda</h3>
                                <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <img src="lib/img/ticket-logo.jpeg" alt="" width="100%"/>
                            </div>
                        </div>
                    </div>
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
                                    <iframe class="responsive-iframe" src="<%=request.getContextPath()%>/MostrarPDFTicket_FACT" width="100%" height="500px"></iframe>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- modal - Help Ticket no facturado --> 
                <div class="modal fade text-start" id="modalSoporte" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header border-0 bg-gray-100">
                                <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;Soporte - Facturación Electrónica</h3>
                                <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="mb-3">
                                    <label>Para poder brindar un mejor servicio, es necesario que nos proporcione la siguiente información:</label>
                                </div>
                                <div class="mb-3">
                                    <div class="row row-cols-lg">
                                        <div class="form-check col-md-4">
                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Núm de Ticket:</label>
                                        </div>
                                        <div class="form-check col-md-7">
                                            <div class="input-group">
                                                <input class="form-control" id="num_ticketSupport" name="num_ticketSupport" type="text" autocomplete="off" onkeypress="return soloNumeros(event)">
                                            </div>
                                        </div>
                                    </div>
                                </div> 
                                <div class="mb-3">
                                    <div class="row row-cols-lg">
                                        <div class="form-check col-md-4">
                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">RFC:</label>
                                        </div>
                                        <div class="form-check col-md-7">
                                            <div class="input-group">
                                                <input class="form-control" type="text" id="rfcSupport" name="rfcSupport"  value=""  autocomplete="off" oninput="validarInput(this)" onkeyup="this.value = this.value.toUpperCase()">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <div class="row row-cols-lg">
                                        <div class="form-check col-md-4">
                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">C.P. :</label>
                                        </div>
                                        <div class="form-check col-md-7">
                                            <div class="input-group">
                                                <input class="form-control" type="text" id="codigo_postalSupport" name="codigo_postalSupport" value="" autocomplete="off" onkeypress="return soloNumeros(event)" maxlength="5">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <div class="row row-cols-lg">
                                        <div class="form-check col-md-4">
                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Razón Social:</label>
                                        </div>
                                        <div class="form-check col-md-7">
                                            <div class="input-group">
                                                <input class="form-control" id="razon_socialSupport" name="razon_socialSupport" type="text" value="" autocomplete="off" onkeyup="this.value = this.value.toUpperCase()">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <div class="row row-cols-lg">
                                        <div class="form-check col-md-4">
                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Email:</label>
                                        </div>
                                        <div class="form-check col-md-7">
                                            <div class="input-group">
                                                <input class="form-control" id="correoSupport" name="correoSupport" type="text" value="" autocomplete="off">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <div class="row row-cols-lg">
                                        <div class="form-check col-md-4">
                                            <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Uso de CFDI:</label>
                                        </div>
                                        <div class="form-check col-md-7">
                                            <div class="input-group">
                                                <select class="form-control selectpicker col-md-5" id="usocfdi_idSupport" name="usocfdi_idSupport">
                                                    <option value="0" disabled selected>-- Seleccione uso de cfdi --</option>
                                                    <%
                                                        if (db.doDB(fac.consultarUsoCfdi())) {
                                                            for (String[] row : db.getResultado()) {
                                                                out.println("<option value=\"" + row[0] + "\" >" + row[1] + " - " + row[2] + "</option>");
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div> 
                                <div class="mb-3">
                                    <div class="row row-cols-lg">
                                        <div class="form-check col-md-4">
                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Régimen fiscal:</label>
                                        </div>
                                        <div class="form-check col-md-7">
                                            <div class="input-group">
                                                <select class="form-control" data-val="true" id="regimen_idSupport" name="regimen_idSupport">
                                                    <option value="0" disabled selected>-- Seleccione regimén fiscal --</option>
                                                    <%
                                                        if (db.doDB(fac.consultarRegimenFiscal())) {
                                                            for (String[] row : db.getResultado()) {
                                                                out.println("<option value=\"" + row[0] + "\" >" + row[1] + " - " + row[2] + "</option>");
                                                            }
                                                        }
                                                    %>
                                                </select>
                                                <div id="val_regimen_id" style="color:#3b83bd;"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <div class="row row-cols-lg">
                                        <div class="form-check col-md-4">
                                            <label class="custom-control-label" style="font-family: Arial; font-weight: bold; color:#707375;">Forma de Pago:</label>
                                        </div>
                                        <div class="form-check col-md-7">
                                            <div class="input-group">
                                                <select class="form-control" data-val="true" id="forma_idSupport" name="forma_idSupport">
                                                    <option value="0" disabled selected>-- Seleccione forma de pago --</option>
                                                    <%
                                                        if (db.doDB(fac.consultarFormaPagoPlebes())) {
                                                            for (String[] row : db.getResultado()) {
                                                                out.println("<option value=\"" + row[0] + "\" >" + row[0] + " - " + row[1] + "</option>");
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-floating mb-3">
                                    <input class="form-control" type="text" id="comentarios" name="comentarios" autocomplete="off">
                                    <label for="floatingInput">Comentarios</label>
                                </div>                
                                <div class="mb-3">                
                                    <div class="col-lg-12" style="text-align: right;">
                                        <a class="btn btn-primary text-nowrap" role="button" onclick="enviarSupport()" disabled>Envíar</a>
                                        &nbsp;
                                        <a class="btn btn-default text-nowrap" role="button" onclick="closeSupport()">Cancelar</a>
                                    </div> 
                                </div>
                                <u style="color:#2a80db;">En breve, recibirá la facturación por correo electrónico.</u>
                            </div>
                        </div>
                    </div>
                </div>   
                <!-- footer -->
                <footer class="footer bg-white shadow align-self-end py-3 px-xl-5 w-100">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-6 text-center text-md-start fw-bold">
                                <p class="mb-2 mb-md-0 fw-bold">Tacts &copy; <%=part3%></p>
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
            //Parametros: Totales
            let subtotalGral = 0;
            let importe_descuentoGral = 0;
            let trasladoGral = 0;
            let retencionesGral = 0;
            let totalGral = 0;

            //Contadores Tablas
            let contadorConceptos = 0;

            //Parametros: Tabla Conceptos
            let exist;
            let rel_clvsat_id;
            let unidad_sat_id;
            let concepto_desc;
            let concepto_id;
            let cantidad;
            let precio_unitario;
            let importe_descuento;
            let importe;
            let urlVisor = "";
            let urlFact = "";

            //Parametros: Emisión de correo
            let serie;
            let folio;
            let correo;
            let carameloTicket;
            let existenciaCliente = "";

            //Parametros: Add Ticket-Conceptos
            let ws_folio = 0;
            let ws_cliente = 0;
            let ws_subtotal = 0;
            let ws_iva = 0;
            let ws_total = 0;
            let ws_total_tarjeta = 0;
            let ws_tienda = 0;

            document.getElementById("subtotalGral").innerHTML = "$" + subtotalGral.toFixed(2);
            document.getElementById("descuentoGral").innerHTML = "$" + importe_descuentoGral.toFixed(2);
            document.getElementById("trasladosGral").innerHTML = "$" + trasladoGral.toFixed(2);
            //document.getElementById("retencionesGral").innerHTML = "$" + retencionesGral.toFixed(2);
            document.getElementById("totalGral").innerHTML = "$" + totalGral.toFixed(2);

        </script>    
        <script>
            function consultarExistenciaUUID() {

                let numTicket = document.getElementById("num_ticket").value;

                fetch("<%=request.getContextPath()%>/consultarExistenciaUUID?numTicket=" + numTicket, {
                    method: 'POST',
                }).then(r => r.text())
                        .then(data => {

                            if (data == "1") {
                                swal("", "El número de Ticket ya se encuentra facturado", "info");
                                alertclose();
                                location.reload();
                            } else {
                                validarNumTicket();
                            }

                        }).catch(error => console.log(error));
            }

            function validarNumTicket() {

                let numTicket = document.getElementById("num_ticket").value;
                validarTicket = document.getElementById('ticketOK');

                const Http = new XMLHttpRequest();
                const url = 'https://www.rtms.mx/wsFacturacionTicket/getTicket.jsp?numTicket=' + numTicket;
                Http.open("GET", url);
                Http.send();

                Http.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        const txt = Http.responseText;
                        const obj = JSON.parse(txt);
                        carameloTicket = obj.ov_folio + "*" + obj.ov_cliente + "*" + obj.ov_subtotal + "*" + obj.ov_iva + "*" + obj.ov_total_ov + "*" + obj.ov_total_tarjeta + "*" + obj.ov_tienda_id;

                        if (obj.ov_folio == undefined || obj.ov_folio == null) {
                            validarTicket.innerText = "Formato: No válido";
                            inhabilitarConceptos();
                            carameloTicket = "0*0*0*0*0*0*0";
                        } else {
                            validarTicket.innerText = "Formato: Válido";
                            document.getElementById("num_ticket").value = numTicket;
                            habilitarConceptos();
                            AddConceptos(carameloTicket);
                        }

                    }
                }
            }

            function consultarCliente(rfc) {
                fetch("<%=request.getContextPath()%>/ConsultarClienteTicket?rfc=" + rfc, {
                    method: 'POST',
                }).then(r => r.text())
                        .then(data => {
                            if (data == "no") {
                                existenciaCliente = "0";
                                document.getElementById("codigo_postal").value = '';
                                document.getElementById("razon_social").value = '';
                                document.getElementById("correo").value = '';
                                document.getElementById("chk_Acepto").checked = false;

                            } else {
                                let input = data.split("*");
                                let a0 = input[0]; //razón social
                                let a1 = input[1]; //correo
                                let a2 = input[2]; //codigo postal

                                existenciaCliente = "1";
                                document.getElementById("codigo_postal").value = a2;
                                document.getElementById("razon_social").value = a0;
                                document.getElementById("correo").value = a1;
                                document.getElementById("chk_Acepto").checked = true;
                            }
                        }).catch(error => console.log(error));
            }

            function AddConceptos(caramelo) {

                let atrib = caramelo.split("*");
                ws_folio = atrib[0]; //ov_folio
                ws_cliente = atrib[1]; //ov_cliente
                ws_subtotal = atrib[2]; //ov_subtotal
                ws_iva = atrib[3]; //ov_iva
                ws_total = atrib[4]; //ov_total_ov
                ws_total_tarjeta = atrib[5]; //ov_total_tarjeta
                ws_tienda = atrib[6]; //ov_tienda_id

                let AddrelClvSatId = "90101501";
                let AddunidadSatId = "E48";
                let AddconceptoId = "90101501";
                let AddconceptoDesc = "SERVICIO DE COMIDA";
                let AddcantConcepto = "1";
                let Addimporte_descuento = 0;

                let AddSubtotal = ws_total / 1.16;
                let AddIva = ws_total - AddSubtotal;
                let AddTotal = AddSubtotal + AddIva;

                //console.log(AddSubtotal);  //subtotal
                //console.log(AddIva);       //iva
                //console.log(AddTotal);     //total


                fetch("<%=request.getContextPath()%>/AddTablaConceptosTicket?rel_clvsat_id=" + AddrelClvSatId + "&unidad_sat_id=" + AddunidadSatId + "&concepto_id=" + AddconceptoId + "&concepto_desc=" + AddconceptoDesc + "&cantidad=" + AddcantConcepto + "&precio_unitario=" + parseFloat(AddSubtotal).toFixed(2) + "&importe_descuento=" + Addimporte_descuento + "&importe=" + parseFloat(AddTotal).toFixed(2) + "&contadorConceptos=" + contadorConceptos, {
                    method: 'POST',
                }).then(r => r.text())
                        .then(data => {

                            if (exist != AddrelClvSatId) {

                                let resultado = document.getElementById("AddTableConceptos").insertRow(-1);

                                resultado.innerHTML = data;
                                resultado.id = "tr" + concepto_id;
                                exist = AddrelClvSatId;
                                contadorConceptos++;

                                document.getElementById("numConceptos").value = contadorConceptos;

                                //Calcular Subtotal Gral
                                subtotalGral = AddSubtotal;
                                //Calcular Iva Gral
                                trasladoGral = AddIva;
                                //Calcular Retenciones Gral
                                retencionesGral += retencionesGral;
                                //Calcular Descuento Gral
                                importe_descuentoGral += importe_descuentoGral;
                                //Calcular Total Gral
                                totalGral = AddTotal;

                                document.getElementById("subtotalGral").innerHTML = "$ " + parseFloat(subtotalGral).toFixed(2);
                                document.getElementById("descuentoGral").innerHTML = "$ " + importe_descuentoGral.toFixed(2);
                                document.getElementById("trasladosGral").innerHTML = "$ " + parseFloat(trasladoGral).toFixed(2);
                                //document.getElementById("retencionesGral").innerHTML = "$ " + retencionesGral.toFixed(2);
                                document.getElementById("totalGral").innerHTML = "$ " + parseFloat(totalGral).toFixed(2);

                                document.getElementById("cant_subtotal_gral").value = parseFloat(subtotalGral).toFixed(2);
                                document.getElementById("cant_descuento_gral").value = importe_descuentoGral.toFixed(2);
                                document.getElementById("cant_traslados_gral").value = parseFloat(trasladoGral).toFixed(2);
                                document.getElementById("cant_retenciones_gral").value = retencionesGral.toFixed(2);
                                document.getElementById("cant_total_gral").value = parseFloat(totalGral).toFixed(2);
                            }
                        }).catch(error => console.log(error));

            }

            function visorPdfFactura(tipo) {
                let tipo_descripcion = document.getElementById("tipo_descripcion").value;
                let pdf_rfc = document.getElementById("rfc").value;
                let pdf_razon_social = document.getElementById("razon_social").value;
                let pdf_usocfdi_id = document.getElementById("usocfdi_id").value;
                let pdf_fecha_emision = document.getElementById("fecha_emision").value;
                let pdf_hora_emision = document.getElementById("hora_emision").value;
                serie = document.getElementById("serie").value;
                folio = document.getElementById("folio").value;
                let pdf_comprobante_id = document.getElementById("comprobante_id").value;
                let pdf_documento_id = document.getElementById("documento_id").value;
                let pdf_regimen_id = document.getElementById("regimen_id").value;
                let pdf_metodo_id = document.getElementById("metodo_id").value;
                let pdf_forma_id = document.getElementById("forma_id").value;
                let pdf_moneda_id = document.getElementById("moneda_id").value;

                //Totales:
                let pdf_subtotal_gral = document.getElementById("cant_subtotal_gral").value;
                let pdf_total_gral = document.getElementById("cant_total_gral").value;
                let pdf_descuento_gral = document.getElementById("cant_descuento_gral").value;
                let pdf_traslados_gral = document.getElementById("cant_traslados_gral").value;
                let pdf_retenciones_gral = document.getElementById("cant_retenciones_gral").value;
                let pdf_impuesto_gral = document.getElementById("cant_impuesto_gral").value;
                let pdf_factor_gral = document.getElementById("cant_factor_gral").value;
                let pdf_tasa_gral = document.getElementById("cant_tasa_gral").value;
                let pdf_cuota_gral = document.getElementById("cant_cuota_gral").value;
                let pdf_impueto_gral = document.getElementById("tipo_impueto_gral").value;

                //Información del Ticket
                let num_ticket = document.getElementById("num_ticket").value;

                //Parametros: Editar Configuración
                for (let i = 0; i < contadorConceptos; i++) {
                    let temp2 = "rel_clvsat_id[" + i + "]";
                    let temp3 = "unidad_sat_id[" + i + "]";

                    let temp9 = "concepto_desc[" + i + "]";
                    let temp10 = "concepto_id[" + i + "]";
                    let temp11 = "cantidad[" + i + "]";
                    let temp12 = "precio_unitario[" + i + "]";
                    let temp13 = "importe_descuento[" + i + "]";
                    let temp14 = "importe[" + i + "]";

                    rel_clvsat_id = document.getElementById(temp2).value;
                    unidad_sat_id = document.getElementById(temp3).value;

                    concepto_desc = document.getElementById(temp9).value;
                    concepto_id = document.getElementById(temp10).value;
                    cantidad = document.getElementById(temp11).value;
                    precio_unitario = document.getElementById(temp12).value;
                    importe_descuento = document.getElementById(temp13).value;
                    importe = document.getElementById(temp14).value;

                    urlVisor += "&rel_clvsat_id[" + i + "]=" + rel_clvsat_id +
                            "&unidad_sat_id[" + i + "]=" + unidad_sat_id +
                            "&concepto_desc[" + i + "]=" + concepto_desc +
                            "&concepto_id[" + i + "]=" + concepto_id +
                            "&cantidad[" + i + "]=" + cantidad +
                            "&precio_unitario[" + i + "]=" + precio_unitario +
                            "&importe_descuento[" + i + "]=" + importe_descuento +
                            "&importe[" + i + "]=" + importe;
                }

                if (pdf_forma_id == null || pdf_forma_id == 0) {
                    swal("", "Ingrese una forma de pago", "warning");
                    return false;
                }

                /*let urlPDF=encodeURI("Facturacion/FacturacionTicketVistaPrevia.jsp?rfc=" + pdf_rfc + "&razon_social=" + pdf_razon_social + "&usocfdi_id=" + pdf_usocfdi_id + "&tipo_descripcion=" + tipo_descripcion + "&fecha_emision=" + pdf_fecha_emision + "&hora_emision=" + pdf_hora_emision + "&serie=" + serie + "&folio=" + folio + "&comprobante_id=" + pdf_comprobante_id + "&documento_id=" + pdf_documento_id + "&regimen_id=" + pdf_regimen_id + "&metodo_id=" + pdf_metodo_id + "&forma_id=" + pdf_forma_id + "&moneda_id=" + pdf_moneda_id + "&cant_subtotal_gral=" + pdf_subtotal_gral + "&cant_descuento_gral=" + pdf_descuento_gral + "&cant_traslados_gral=" + pdf_traslados_gral + "&cant_retenciones_gral=" + pdf_retenciones_gral + "&cant_total_gral=" + pdf_total_gral + "&cant_impuesto_gral=" + pdf_impuesto_gral + "&cant_factor_gral=" + pdf_factor_gral + "&cant_tasa_gral=" + pdf_tasa_gral + "&cant_cuota_gral=" + pdf_cuota_gral + "&tipo_impueto_gral=" + pdf_impueto_gral + "&numConceptos=" + contadorConceptos + "&tipo=" + tipo + "&num_ticket=" + num_ticket + urlVisor);           
                 console.log(urlPDF);*/

                fetch("Facturacion/FacturacionTicketVistaPrevia.jsp?rfc=" + pdf_rfc + "&razon_social=" + pdf_razon_social + "&usocfdi_id=" + pdf_usocfdi_id + "&tipo_descripcion=" + tipo_descripcion + "&fecha_emision=" + pdf_fecha_emision + "&hora_emision=" + pdf_hora_emision + "&serie=" + serie + "&folio=" + folio + "&comprobante_id=" + pdf_comprobante_id + "&documento_id=" + pdf_documento_id + "&regimen_id=" + pdf_regimen_id + "&metodo_id=" + pdf_metodo_id + "&forma_id=" + pdf_forma_id + "&moneda_id=" + pdf_moneda_id + "&cant_subtotal_gral=" + pdf_subtotal_gral + "&cant_descuento_gral=" + pdf_descuento_gral + "&cant_traslados_gral=" + pdf_traslados_gral + "&cant_retenciones_gral=" + pdf_retenciones_gral + "&cant_total_gral=" + pdf_total_gral + "&cant_impuesto_gral=" + pdf_impuesto_gral + "&cant_factor_gral=" + pdf_factor_gral + "&cant_tasa_gral=" + pdf_tasa_gral + "&cant_cuota_gral=" + pdf_cuota_gral + "&tipo_impueto_gral=" + pdf_impueto_gral + "&numConceptos=" + contadorConceptos + "&tipo=" + tipo + "&num_ticket=" + num_ticket + urlVisor, {
                    method: 'POST',
                }).then(r => r.text())
                        .then(data => {
                            if (tipo == "1") {

                                $("#visor1").load(location.href + " #visor1");
                                setTimeout(function () {
                                    openModalCfdi();
                                }, 1000);
                            }
                        }).catch(error => console.log(error));

            }

            function AddFacturacion(tipo_registro) {
                let tipo_descripcion = document.getElementById("tipo_descripcion").value;
                let rfc = document.getElementById("rfc").value;
                let razon_social = document.getElementById("razon_social").value;
                correo = document.getElementById("correo").value;
                let num_ticket = document.getElementById("num_ticket").value;
                let usocfdi_id = document.getElementById("usocfdi_id").value;
                let fecha_emision = document.getElementById("fecha_emision").value;
                let hora_emision = document.getElementById("hora_emision").value;
                serie = document.getElementById("serie").value;
                folio = document.getElementById("folio").value;
                let comprobante_id = document.getElementById("comprobante_id").value;
                let documento_id = document.getElementById("documento_id").value;
                let regimen_id = document.getElementById("regimen_id").value;
                let metodo_id = document.getElementById("metodo_id").value;
                let forma_id = document.getElementById("forma_id").value;
                let moneda_id = document.getElementById("moneda_id").value;
                let codigo_postal = document.getElementById("codigo_postal").value;

                //Totales:
                let cant_subtotal_gral = document.getElementById("cant_subtotal_gral").value;
                let cant_total_gral = document.getElementById("cant_total_gral").value;
                let cant_descuento_gral = document.getElementById("cant_descuento_gral").value;
                let cant_traslados_gral = document.getElementById("cant_traslados_gral").value;
                let cant_retenciones_gral = document.getElementById("cant_retenciones_gral").value;
                let cant_impuesto_gral = document.getElementById("cant_impuesto_gral").value;
                let cant_factor_gral = document.getElementById("cant_factor_gral").value;
                let cant_tasa_gral = document.getElementById("cant_tasa_gral").value;
                let cant_cuota_gral = document.getElementById("cant_cuota_gral").value;
                let tipo_impueto_gral = document.getElementById("tipo_impueto_gral").value;

                //Parametros: Editar Configuración
                for (let i = 0; i < contadorConceptos; i++) {
                    let temp2 = "rel_clvsat_id[" + i + "]";
                    let temp3 = "unidad_sat_id[" + i + "]";

                    let temp9 = "concepto_desc[" + i + "]";
                    let temp10 = "concepto_id[" + i + "]";
                    let temp11 = "cantidad[" + i + "]";
                    let temp12 = "precio_unitario[" + i + "]";
                    let temp13 = "importe_descuento[" + i + "]";
                    let temp14 = "importe[" + i + "]";

                    rel_clvsat_id = document.getElementById(temp2).value;
                    unidad_sat_id = document.getElementById(temp3).value;

                    concepto_desc = document.getElementById(temp9).value;
                    concepto_id = document.getElementById(temp10).value;
                    cantidad = document.getElementById(temp11).value;
                    precio_unitario = document.getElementById(temp12).value;
                    importe_descuento = document.getElementById(temp13).value;
                    importe = document.getElementById(temp14).value;

                    urlFact += "&rel_clvsat_id[" + i + "]=" + rel_clvsat_id +
                            "&unidad_sat_id[" + i + "]=" + unidad_sat_id +
                            "&concepto_desc[" + i + "]=" + concepto_desc +
                            "&concepto_id[" + i + "]=" + concepto_id +
                            "&cantidad[" + i + "]=" + cantidad +
                            "&precio_unitario[" + i + "]=" + precio_unitario +
                            "&importe_descuento[" + i + "]=" + importe_descuento +
                            "&importe[" + i + "]=" + importe;
                }

                if (forma_id == null || forma_id == 0) {
                    swal("", "Ingrese una forma de pago", "warning");
                    return false;
                }

                showPreloader();

                fetch("<%=request.getContextPath()%>/InsertarFacturacionPOS?tipoEmision=" + tipo_registro + "&razon_social=" + razon_social + "&correo=" + correo + "&tipo_descripcion=" + tipo_descripcion + "&rfc=" + rfc + "&num_ticket=" + num_ticket + "&usocfdi_id=" + usocfdi_id + "&fecha_emision=" + fecha_emision + "&hora_emision=" + hora_emision + "&serie=" + serie + "&folio=" + folio + "&comprobante_id=" + comprobante_id + "&documento_id=" + documento_id + "&regimen_id=" + regimen_id + "&metodo_id=" + metodo_id + "&forma_id=" + forma_id + "&moneda_id=" + moneda_id + "&cant_subtotal_gral=" + cant_subtotal_gral + "&cant_descuento_gral=" + cant_descuento_gral + "&cant_traslados_gral=" + cant_traslados_gral + "&cant_retenciones_gral=" + cant_retenciones_gral + "&cant_total_gral=" + cant_total_gral + "&cant_impuesto_gral=" + cant_impuesto_gral + "&cant_factor_gral=" + cant_factor_gral + "&cant_tasa_gral=" + cant_tasa_gral + "&cant_cuota_gral=" + cant_cuota_gral + "&tipo_impueto_gral=" + tipo_impueto_gral + "&numConceptos=" + contadorConceptos + "&carameloTicket=" + carameloTicket + "&existenciaCliente=" + existenciaCliente + urlFact + "&codigo_postal=" + codigo_postal, {
                    method: 'POST',
                }).then(r => r.text())
                        .then(data => {

                            if (data == "0") {
                                swal("Error al generar", "Verique la información ingresada", "error");
                                alertclose();
                                return false;
                                hidePreloader();
                            } else {
                                //swal("", "La información se registro correctamente", "success");
                                //alertclose();
                                Api_Facturacion(data);
                            }

                        }).catch(error => console.log(error));
            }

            function Api_Facturacion(id) {
                const Http = new XMLHttpRequest();
                
                /*const url = 'https://www.rtms.mx/Facturacion3.3QA/getXML.jsp'*/
                /*const url = 'https://www.rtms.mx/Facturacion3.3PRO/getXML.jsp'*/
                
                const url = 'https://www.rtms.mx/Facturacion3.3QA/getXML.jsp?idf=' + id + '&cve=' + <%=cve%> + '&tipo=1';
                Http.open("GET", url);
                Http.send();

                Http.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {

                        let respuesta = Http.responseText;
                        let palabra = "200";
                        let index = respuesta.indexOf(palabra);

                        if (index >= 0) {

                            //Generación de PDF'S
                            visorPdfFactura(2);   // (1 = No Marcado de ruta pdf) | (2= Marcado de ruta pdf)

                            //Almacenamiento de PDF'S
                            savegeneratepdf(4);         //tipoGeneracion(cfdi ó cartaPorte)

                            //Envío de PDF'S    
                            sendmail(4, 2); /*tipoGeneracion(cfdi)|tipoTimbrado(si XML)*/

                        } else {
                            swal("Error", "Detalle: " + respuesta, "error");
                            hidePreloader();
                            return false;
                        }

                    }
                }
            }

            function savegeneratepdf() { //tipoGeneracion(cfdi)
                fetch("<%=request.getContextPath()%>/GenerateFiles?serie=F&folio=151&tipo=4", {
                    method: 'POST',
                }).then(r => r.text())
                        .then(data => {
                        }).catch(error => console.log(error));
            }

            function sendmail() { //tipoGeneracion(cfdi)|tipoTimbrado(si XML)
                fetch("<%=request.getContextPath()%>/AlertaFactutacion?serie=F&folio=151&tipoGeneracion=4&tipoTimbrado=2&correos=oamorales@tacts.mx", {
                    method: 'POST',
                }).then(r => r.text())
                        .then(data => {
                            hidePreloader();
                            swal("registrado", "Generación de Factura éxitoso", "success");
                            alertclose();
                            location.reload();
                        }).catch(error => console.log(error));


            }

            function rfcValido(rfc, aceptarGenerico = true) {
                const re = /^([A-ZÑ&]{3,4}) ?(?:- ?)?(\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\d|3[01])) ?(?:- ?)?([A-Z\d]{2})([A\d])$/;
                var validado = rfc.match(re);

                if (!validado)  //Coincide con el formato general del regex?
                    return false;

                //Separar el dígito verificador del resto del RFC
                const digitoVerificador = validado.pop(),
                        rfcSinDigito = validado.slice(1).join(''),
                        len = rfcSinDigito.length,
                        //Obtener el digito esperado
                        diccionario = "0123456789ABCDEFGHIJKLMN&OPQRSTUVWXYZ Ñ",
                        indice = len + 1;
                var suma,
                        digitoEsperado;

                if (len == 12)
                    suma = 0
                else
                    suma = 481; //Ajuste para persona moral

                for (var i = 0; i < len; i++)
                    suma += diccionario.indexOf(rfcSinDigito.charAt(i)) * (indice - i);
                digitoEsperado = 11 - suma % 11;
                if (digitoEsperado == 11)
                    digitoEsperado = 0;
                else if (digitoEsperado == 10)
                    digitoEsperado = "A";

                //El dígito verificador coincide con el esperado?
                // o es un RFC Genérico (ventas a público general)?
                if ((digitoVerificador != digitoEsperado)
                        && (!aceptarGenerico || rfcSinDigito + digitoVerificador != "XAXX010101000"))
                    return false;
                else if (!aceptarGenerico && rfcSinDigito + digitoVerificador == "XEXX010101000")
                    return false;
                return rfcSinDigito + digitoVerificador;
            }

            function validarInput(input) {
                var rfc = input.value.trim().toUpperCase(),
                        resultado = document.getElementById("rfcOK"),
                        valido;

                var rfcCorrecto = rfcValido(rfc);   // ⬅️ Acá se comprueba

                if (rfcCorrecto) {
                    valido = "Válido";
                    resultado.classList.add("ok");
                    consultarCliente(rfc);
                } else {
                    valido = "No válido"
                    resultado.classList.remove("ok");
                    document.getElementById("razon_social").value = '';
                    document.getElementById("correo").value = '';
                    document.getElementById("chk_Acepto").checked = false;
                }

                resultado.innerText = "Formato: " + valido;
            }

            document.getElementById('correo').addEventListener('input', function () {
                campo = event.target;
                valido = document.getElementById('emailOK');

                var reg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

                var regOficial = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;

                //Se muestra un texto a modo de ejemplo, luego va a ser un icono
                if (reg.test(campo.value) && regOficial.test(campo.value)) {
                    valido.innerText = "Formato: Válido";
                } else if (reg.test(campo.value)) {
                    valido.innerText = "Formato: Válido";
                } else {
                    valido.innerText = "Formato: No válido";
                }
            });

            function habilitarInformacionFiscal() {

                let num_ticket = document.getElementById("num_ticket").value;
                let codigo_postal = document.getElementById("codigo_postal").value;
                let rfc = document.getElementById("rfc").value;
                let correo = document.getElementById("correo").value;

                if (num_ticket == "") {
                    swal("Error", "Ingrese número de ticket", "error");
                    habilitarDatosGenerales();
                    return false;
                }
                if (rfc == "") {
                    swal("Error", "Ingrese rfc", "error");
                    habilitarDatosGenerales();
                    return false;
                }
                if (codigo_postal == ""  || codigo_postal == 0) {
                    swal("Error", "Ingrese código postal", "error");
                    habilitarDatosGenerales();
                    return false;
                }
                if (correo == "") {
                    swal("Error", "Ingrese correo", "error");
                    habilitarDatosGenerales();
                    return false;
                }
                if (!document.getElementById("chk_Acepto").checked) {
                    swal("Por favor", "Acepte los Términos y Condiciones y el Aviso de Privacidad", "info");
                    habilitarDatosGenerales();
                    return false;
                }

                document.getElementById("check_datosGenerales").checked = false;
                document.getElementById("check_informacionFiscal").checked = true;

                div = document.getElementById('tab-Destinatario');
                div.style.display = '';
                div = document.getElementById('tab-LugarDeEmbarque');
                div.style.display = 'none';

            }

            function habilitarDatosGenerales() {

                div = document.getElementById('tab-LugarDeEmbarque');
                div.style.display = '';
                div = document.getElementById('tab-Destinatario');
                div.style.display = 'none';

                document.getElementById("check_datosGenerales").checked = true;
                document.getElementById("check_informacionFiscal").checked = false;
            }

            function habilitarConceptos() {
                document.getElementById('rfc').disabled = false;
                document.getElementById('codigo_postal').disabled = false;
                document.getElementById('razon_social').disabled = false;
                document.getElementById('correo').disabled = false;

                document.getElementById('rfc').value = '';
                document.getElementById('codigo_postal').value = '';
                document.getElementById('razon_social').value = '';
                document.getElementById('correo').value = '';

            }

            function inhabilitarConceptos() {
                document.getElementById('rfc').disabled = true;
                document.getElementById('codigo_postal').disabled = true;
                document.getElementById('razon_social').disabled = true;
                document.getElementById('correo').disabled = true;

                document.getElementById('rfc').value = '';
                document.getElementById('codigo_postal').value = '';
                document.getElementById('razon_social').value = '';
                document.getElementById('correo').value = '';
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
                        function (isConfirm) {
                            if (isConfirm) {
                                location.reload();
                            } else {
                                swal.close();
                            }
                        });
            }

            function alertclose() {
                setTimeout(function () {
                    swal.close();
                }, 5000);
            }

            function soloNumeros(e) {
                var key = window.Event ? e.which : e.keyCode
                return (key >= 48 && key <= 57)
            }

            function openModalCfdi() {
                $("#modalVistaPreviaCfdi").modal("show");
            }
            
            function openModalSoporte() {
                $("#modalSoporte").modal("show");
            }
            
            function enviarSupport() {
                
                let num_ticketSupport = document.getElementById("num_ticketSupport").value;
                let rfcSupport = document.getElementById("rfcSupport").value;
                let codigo_postalSupport = document.getElementById("codigo_postalSupport").value;
                let razon_socialSupport = document.getElementById("razon_socialSupport").value;
                let correoSupport = document.getElementById("correoSupport").value;
                let usocfdi_idSupport = document.getElementById("usocfdi_idSupport").value;
                let regimen_idSupport = document.getElementById("regimen_idSupport").value;
                let forma_idSupport = document.getElementById("forma_idSupport").value;
                let comentarios = document.getElementById("comentarios").value;
                
                if (num_ticketSupport == "") {
                    swal("", "Ingrese número de ticket", "warning");
                    return false;
                }
                if (rfcSupport == "") {
                    swal("", "Ingrese rfc", "warning");
                    return false;
                }
                if (codigo_postalSupport == "") {
                    swal("", "Ingrese código postal", "warning");
                    return false;
                }
                if (razon_socialSupport == "") {
                    swal("", "Ingrese razón social", "warning");
                    return false;
                }
                if (correoSupport == "") {
                    swal("", "Ingrese email", "warning");
                    return false;
                }
                if (usocfdi_idSupport == null || usocfdi_idSupport == 0) {
                    swal("", "Ingrese uso de cfdi", "warning");
                    return false;
                }
                if (regimen_idSupport == null || regimen_idSupport == 0) {
                    swal("", "Ingrese regimén fiscal", "warning");
                    return false;
                }
                if (forma_idSupport == null || forma_idSupport == 0) {
                    swal("", "Ingrese forma de pago", "warning");
                    return false;
                }
                
                closeSupport();
                
                fetch("<%=request.getContextPath()%>/AlertSupportTicket?num_ticketSupport=" + num_ticketSupport + "&rfcSupport=" + rfcSupport + "&codigo_postalSupport=" + codigo_postalSupport + "&razon_socialSupport=" + razon_socialSupport + "&correoSupport=" + correoSupport + "&usocfdi_idSupport=" + usocfdi_idSupport + "&regimen_idSupport=" + regimen_idSupport + "&forma_idSupport=" + forma_idSupport + "&comentarios=" + comentarios, {
                    method: 'POST',
                }).then(r => r.text())
                        .then(data => {
                            hidePreloader();
                            swal("", "Solicitud de soporte envíado", "success");
                            setTimeout(function () {
                                location.reload();
                            }, 5000);
                        }).catch(error => console.log(error));
            }
            
            function closeSupport(){
               $("#modalSoporte").modal("hide");
            }
            function showPreloader() {
                div = document.getElementById('loader-folding-circle');
                div.style.display = '';
            }

            function hidePreloader() {
                div = document.getElementById('loader-folding-circle');
                div.style.display = 'none';
            }

            function soloNumeros(e) {
                var key = window.Event ? e.which : e.keyCode
                return (key >= 48 && key <= 57)
            }

        </script>  
        <script>
            function updateConnectionStatus(msg, connected) {
                if (connected) {
                    document.body.className = "online";
                } else {
                    document.body.className = "offline";
                }
            }

            window.addEventListener('load', function (e) {
                if (navigator.onLine) {
                    updateConnectionStatus('Online', true);
                } else {
                    updateConnectionStatus('Offline', false);
                }
            }, false);

            window.addEventListener('online', function (e) {
                updateConnectionStatus('Online', true);
            }, false);

            window.addEventListener('offline', function (e) {
                updateConnectionStatus('Offline', false);
            }, false);
        </script>  
        <!-- JavaScript files-->
        <script src="lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
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