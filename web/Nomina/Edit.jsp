<%-- 
    Document   : Edit
    Created on : 4/01/2022, 04:16:17 PM
    Author     : Desarrollo Tacts
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Editar Empleado</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Google fonts - Popppins for copy-->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&amp;display=swap" rel="stylesheet">
    <!-- Prism Syntax Highlighting-->
    <link rel="stylesheet" href="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.css">
    <link rel="stylesheet" href="../lib/vendor/prismjs/themes/prism-okaidia.css">
    <!-- The Main Theme stylesheet (Contains also Bootstrap CSS)-->
    <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="../lib/css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="../lib/img/favicon.png">
  </head>
  <body>
    <!-- navbar-->
    <header class="header">
      <!--<nav class="navbar navbar-expand-lg px-4 py-2 bg-white shadow">
          <a class="sidebar-toggler text-gray-500 me-4 me-lg-5 lead" href="#"><i class="fas fa-align-left"></i></a><a class="navbar-brand fw-bold text-uppercase text-base" href="index.html"><span class="d-none d-brand-partial"></span><span class="d-none d-sm-inline">Editar Cliente</span></a>
      </nav>-->
    </header>
    <div class="d-flex align-items-stretch">
      <div class="page-holder bg-gray-100">
        <div class="container-fluid px-lg-4 px-xl-5">
            <div class="page-header">
                <h3 class="page-heading">Editar Empleado</h3>
            </div>
          <section>
          <form>
            <div class="row">
              <!-- Informacion ------------------------------------------------------------------------------------------------------->
              <div class="col-lg-12 mb-5">
                <div class="card">
                  <div class="card-header">
                    <h4 class="card-heading">Información</h4>
                  </div>
                  <div class="card-body">
                        <div class="row">
                            <div class="form-group col-md-6">
                                <div class="form-group mb-3">
                                  <input class="form-control" type="text" placeholder="Nombre" id="nuevoclientenombre">
                                </div>
                                <div class="form-group mb-3">
                                    <input class="form-control" type="text" placeholder="RFC" id="nuevoclienterfc" required>
                                </div>
                                 <div class="form-group mb-3">
                                  <input class="form-control" type="text" placeholder="Nombre Comercial" id="nuevoclientenombrecomercial">
                                </div>
                                <div class="form-group mb-3">
                                  <input class="form-control" type="text" placeholder="Código del Cliente" id="nuevoclientenombrecodigocliente">
                                </div>
                                 <div class="form-group mb-3">
                                  <input class="form-control" type="text" placeholder="Número de Cliente" id="nuevoclientenumerocliente" >
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <div class="mb-3">
                                    <input class="form-control" type="text" placeholder="Nombre de Comprador" id="nuevoclientenombrecomprador">
                                </div>
                                <div class="mb-3">
                                    <select class="form-control" id="MetodoPago" name="MetodoPago"><option value="">-- Seleccione un M&#233;todo de Pago --</option>
                                        <option value="Pago en parcialidades o diferido">PPD - Pago en parcialidades o diferido</option>
                                        <option value="Pago en una sola exhibición">PUE - Pago en una sola exhibici&#243;n</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <select class="form-control" id="FormaPago" name="FormaPago"><option value="">-- Seleccione una Forma de Pago --</option>
                                        <option value="Efectivo">01 - Efectivo</option>
                                        <option value="Cheque nominativo">02 - Cheque nominativo</option>
                                        <option value="Transferencia electrónica de fondos">03 - Transferencia electr&#243;nica de fondos</option>
                                        <option value="Tarjeta de crédito">04 - Tarjeta de cr&#233;dito</option>
                                        <option value="Monedero electrónico">05 - Monedero electr&#243;nico</option>
                                        <option value="Dinero electrónico">06 - Dinero electr&#243;nico</option>
                                        <option value="Vales de despensa">08 - Vales de despensa</option>
                                        <option value="Dación en pago">12 - Daci&#243;n en pago</option>
                                        <option value="Pago por subrogación">13 - Pago por subrogaci&#243;n</option>
                                        <option value="Pago por consignación">14 - Pago por consignaci&#243;n</option>
                                        <option value="Condonación">15 - Condonaci&#243;n</option>
                                        <option value="Compensación">17 - Compensaci&#243;n</option>
                                        <option value="Novación">23 - Novaci&#243;n</option>
                                        <option value="Confusión">24 - Confusi&#243;n</option>
                                        <option value="Remisión de deuda">25 - Remisi&#243;n de deuda</option>
                                        <option value="Prescripción o caducidad">26 - Prescripci&#243;n o caducidad</option>
                                        <option value="A satisfacción del acreedor">27 - A satisfacci&#243;n del acreedor</option>
                                        <option value="Tarjeta de débito">28 - Tarjeta de d&#233;bito</option>
                                        <option value="Tarjeta de servicios">29 - Tarjeta de servicios</option>
                                        <option value="Aplicación de anticipos">30 - Aplicaci&#243;n de anticipos</option>
                                        <option value="Intermediario pagos">31 - Intermediario pagos</option>
                                        <option value="Por definir">99 - Por definir</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <select class="form-control" id="UsoCFDI" name="UsoCFDI"><option value="">-- Seleccione un Uso de CFDI--</option>
                                        <option value="Honorarios médicos, dentales y gastos hospitalarios.">D01 - Honorarios m&#233;dicos, dentales y gastos hospitalarios.</option>
                                        <option value="Gastos médicos por incapacidad o discapacidad">D02 - Gastos m&#233;dicos por incapacidad o discapacidad</option>
                                        <option value="Gastos funerales.">D03 - Gastos funerales.</option>
                                        <option value="Donativos.">D04 - Donativos.</option>
                                        <option value="Intereses reales efectivamente pagados por créditos hipotecarios (casa habitación).">D05 - Intereses reales efectivamente pagados por cr&#233;ditos hipotecarios (casa habitaci&#243;n).</option>
                                        <option value="Aportaciones voluntarias al SAR.">D06 - Aportaciones voluntarias al SAR.</option>
                                        <option value="Primas por seguros de gastos médicos.">D07 - Primas por seguros de gastos m&#233;dicos.</option>
                                        <option value="Gastos de transportación escolar obligatoria.">D08 - Gastos de transportaci&#243;n escolar obligatoria.</option>
                                        <option value="Depósitos en cuentas para el ahorro, primas que tengan como base planes de pensiones.">D09 - Dep&#243;sitos en cuentas para el ahorro, primas que tengan como base planes de pensiones.</option>
                                        <option value="Pagos por servicios educativos (colegiaturas)">D10 - Pagos por servicios educativos (colegiaturas)</option>
                                        <option value="Adquisición de mercancias">G01 - Adquisici&#243;n de mercancias</option>
                                        <option value="Devoluciones, descuentos o bonificaciones">G02 - Devoluciones, descuentos o bonificaciones</option>
                                        <option value="Gastos en general">G03 - Gastos en general</option>
                                        <option value="Construcciones">I01 - Construcciones</option>
                                        <option value="Mobilario y equipo de oficina por inversiones">I02 - Mobilario y equipo de oficina por inversiones</option>
                                        <option value="Equipo de transporte">I03 - Equipo de transporte</option>
                                        <option value="Equipo de computo y accesorios">I04 - Equipo de computo y accesorios</option>
                                        <option value="Dados, troqueles, moldes, matrices y herramental">I05 - Dados, troqueles, moldes, matrices y herramental</option>
                                        <option value="Comunicaciones telefónicas">I06 - Comunicaciones telef&#243;nicas</option>
                                        <option value="Comunicaciones satelitales">I07 - Comunicaciones satelitales</option>
                                        <option value="Otra maquinaria y equipo">I08 - Otra maquinaria y equipo</option>
                                        <option value="Por definir">P01 - Por definir</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <div class="form-check mb-4">
                                        <input class="form-check-input" id="customCheck1" type="checkbox">
                                        <label class="custom-control-label" for="customCheck1">Enviar XML</label>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <div class="form-check mb-4">
                                        <input class="form-check-input" id="customCheck1" type="checkbox">
                                        <label class="custom-control-label" for="customCheck1">Enviar PDF</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                  </div>
                </div>
              </div>
              <!-- Domicilio--------------------------------------------------------------------------------------------------------------->
            <div class="col-lg-6 mb-5">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-heading">Domicilio</h4>
                    </div>
                    <div class="card-body">
                            <div class="row row-cols-lg-auto g-3 align-items-center">
                              <label class="custom-control-label" for="customRadioInline1">Nacionalidad:</label>
                                <div class="form-check">
                                    <input class="form-check-input" id="customRadioInline1" type="radio" name="customRadioInline1">
                                    <label class="custom-control-label" for="customRadioInline1">Nacional</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" id="customRadioInline2" type="radio" name="customRadioInline1">
                                    <label class="custom-control-label" for="customRadioInline2">Extranjero</label>
                                </div>
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" placeholder="Cale" id="nuevoclientecalle">
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" placeholder="Número Exterior" id="nuevoclientenumeroexterior">
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" placeholder="Número Interior" id="nuevoclientenumerointerior">
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" placeholder="Colonia" id="nuevoclientecolonia">
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" placeholder="Localidad" id="nuevoclientelocalidad" required>
                            </div>
                            <div class="form-group mb-3" id="NuevoClienteReferencia" style="display:block;">
                                <input class="form-control" type="text" placeholder=Referencia id="nuevoclientereferencia">
                            </div>
                            <div class="form-group mb-3" id="NuevoClienteNumRegIDTrib" style="display:none;">
                                <input class="form-control" type="text" placeholder=NumRegIDTrib id="nuevoclientenumregidtrib">
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" placeholder="Codigo Postal" id="nuevoclientecodigopostal">
                            </div>
                            <div class="form-group mb-3" id="ComboMunicipios" style="display:block;">
                                <select class="form-control" data-val="true" id="Estado" name="Estado"><option value="">-- Seleccione un Estado --</option>
                                    <option value="1">Aguascalientes</option>
                                    <option value="2">Baja California</option>
                                </select>
                            </div>
                            <div class="form-group mb-3" id="pais" style="display:none;">
                                <select class="form-control" id="Pais" name="Pais"><option value="">-- Seleccione un Pais--</option>
                                    <option value="ALB">Albania - ALB</option>
                                    <option value="DEU">Alemania - DEU</option>
                                </select>
                            </div>
                            <div class="form-group mb-3" id="municipio" style="display:block;">
                                <select class="form-control" data-val="true" disabled="true" id="IDMunicipio" name="IDMunicipio"><option value="">-- Seleccione un Municipio --</option>
                                    <option value="1">Aguascalientes</option>
                                    <option value="2">Asientos</option>
                                </select>
                            </div>
                            
                            <div class="form-group mb-3" id="NuevoClienteProEst" style="display:none;">
                                <input class="form-control" type="text" placeholder="Província o Estado" id="nuevoclienteproest">
                            </div>
                    </div>
                </div>
            </div>
              <script>
                  
              </script>
              <!-- Contacto------------------------------------------------------------>
              <div class="col-lg-6 mb-5">
                <div class="card">
                  <div class="card-header">
                    <h4 class="card-heading">Contactos</h4>
                  </div>
                  <div class="card-body">
                        
                        <div class="form-group mb-3">
                            <label class="col-md-3 form-label">Contacto 1(Principal):</label>
                            <input class="form-control" type="text" placeholder="Nombre" id="nuevoclientecalle">
                            <input class="form-control form-control-success" id="inputHorizontalSuccess" type="Correo" placeholder="Email Address">
                        </div>
                        <div class="form-group mb-3">
                            <label class="col-md-3 form-label">Contacto 2:</label>
                            <input class="form-control" type="text" placeholder="Nombre" id="nuevoclientenumeroexterior">
                            <input class="form-control form-control-success" id="inputHorizontalSuccess" type="Correo" placeholder="Email Address">
                        </div>
                        <div class="form-group mb-3">
                            <label class="col-md-3 form-label">Contacto 3:</label>
                            <input class="form-control" type="text" placeholder="Nombre" id="nuevoclientenumerointerior">
                            <input class="form-control form-control-success" id="inputHorizontalSuccess" type="Correo" placeholder="Email Address">
                        </div>
                        <div class="col-md-12 mb-3">
                            <div class="row">
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="Telefono" id="nuevoclientecolonia">
                                </div>
                                <div class="col-md-6">
                                    <input class="form-control col-md-1" type="text" placeholder="Pagina Web" id="nuevoclientelocalidad" required>
                                </div>
                            </div>
                        </div>
                        <div >Comentario para incluir en el Correo Electrónico de envío de CFDI:</div>
                        <div class="form-group mb-3">
                            <textarea class="form-control"></textarea>
                        </div>
                  </div>
                </div>
              </div>
            </div>
          </form>
              <div class="col-md-12 text-center">
                <a class="btn btn-primary text-uppercase" href="IndexEmpleados.jsp"> <i class="fa fa-angle-left"> </i>Regresar al listado</a>
                <a class="btn btn-primary text-uppercase" href=""> <i class="fa fa-save"> </i>Guardar</a>
            </div>
        </section>
        </div>
        <footer class="footer bg-white shadow align-self-end py-3 px-xl-5 w-100">
          <div class="container-fluid">
            <div class="row">
              <div class="col-md-6 text-center text-md-start fw-bold">
                <p class="mb-2 mb-md-0 fw-bold">Tacts S de RL de CV &copy; 2021</p>
              </div>
              <div class="col-md-6 text-center text-md-end text-gray-400">
                <p class="mb-0">Version 1.1.0</p>
              </div>
            </div>
          </div>
        </footer>
      </div>
    </div>
    <!-- JavaScript files-->
    <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
    <!-- Main Theme JS File-->
    <script src="../lib/js/theme.js"></script>
    <!-- Prism for syntax highlighting-->
    <script src="../lib/vendor/prismjs/prism.js"></script>
    <script src="../lib/vendor/prismjs/plugins/normalize-whitespace/prism-normalize-whitespace.min.js"></script>
    <script src="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.min.js"></script>
    <script src="../lib/vendor/prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard.min.js"></script>
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

