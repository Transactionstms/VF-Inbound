<%-- 
    Document   : Add
    Created on : 29/12/2021, 04:09:23 PM
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
    <title>Nuevo Cliente</title>
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
    <!-- sweetalert -->
    <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
  </head>
  <body>
      <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                
                ConsultasQuery fac = new ConsultasQuery();
        %>
    <!-- navbar-->
    <header class="header"></header>
    <div class="d-flex align-items-stretch">
      <div class="page-holder bg-gray-100">
        <div class="container-fluid px-lg-4 px-xl-5">
            <div class="page-header">
                <h3 class="page-heading">Nuevo Cliente</h3>
            </div>
          <section>
            <div class="row">
              <!-- Información -->  
              <div class="col-lg-12 mb-5">
                <div class="card">
                  <div class="card-header">
                    <h4 class="card-heading">Información</h4>
                  </div>
                  <div class="card-body">
                        <div class="row">
                            <div class="form-group col-md-6">
                                <div class="form-group mb-3">
                                    <input class="form-control" type="text" id="cliente_descripcion" name="cliente_descripcion" placeholder="Nombre" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                </div>
                                <div class="form-group mb-3">
                                    <input class="form-control" type="text" id="rfc" name="rfc" placeholder="RFC" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                </div>
                                 <div class="form-group mb-3">
                                     <input class="form-control" type="text" id="nombre_comercial" name="nombre_comercial" placeholder="Nombre Comercial" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                </div>
                                <div class="form-group mb-3">
                                  <input class="form-control" type="text" id="codigo_cliente" name="codigo_cliente" placeholder="Código del Cliente" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                </div>
                                 <div class="form-group mb-3">
                                     <input class="form-control" type="text"  id="num_cliente" name="num_cliente" placeholder="Número de Cliente" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <div class="mb-3">
                                    <select class="form-control" id="usocfdi_id" name="usocfdi_id">
                                        <option value="0" disabled selected>-- Seleccione un uso de cfdi --</option>
                                        <%
                                            if (db.doDB(fac.consultarUsoCfdi())) {
                                                for (String[] row : db.getResultado()) {
                                                    out.println("<option value=\"" + row[0] + "\" >" + row[1] + " - " + row[2] + "</option>");
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <select class="form-control" data-val="true" id="regimen_desc" name="regimen_desc">
                                        <option value="0" disabled selected>-- Seleccione régimen fiscal --</option>
                                        <%
                                            if (db.doDB(fac.consultarRegimenFiscalAlias())) {
                                                for (String[] row : db.getResultado()) {
                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + " - " + row[1] + "</option>");
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <select class="form-control" id="metodo_id" name="metodo_id">
                                        <option value="0" disabled selected>-- Seleccione método de pago --</option>
                                        <%
                                            if (db.doDB(fac.consultarMetodoPago())) {
                                                for (String[] row : db.getResultado()) {
                                                    out.println("<option value=\"" + row[0] + "\" >" + row[1] + " - " + row[2] + "</option>");
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <select class="form-control" id="forma_id" name="forma_id">
                                        <option value="0" disabled selected>-- Seleccione forma de pago --</option>
                                        <%
                                            if (db.doDB(fac.consultarFormaPago())) {
                                                for (String[] row : db.getResultado()) {
                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + " - " + row[1] + "</option>");
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <div class="form-check mb-4">
                                        <input class="form-check-input" id="check_xml" name="check_xml" type="checkbox">
                                        <label class="custom-control-label" for="customCheck1">Enviar XML</label>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <div class="form-check mb-4">
                                        <input class="form-check-input" id="check_pdf" name="check_pdf" type="checkbox">
                                        <label class="custom-control-label" for="customCheck1">Enviar PDF</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                  </div>
                </div>
              </div>
              <!-- Domicilio -->                       
              <div class="col-lg-6 mb-5">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-heading">Domicilio</h4>
                    </div>
                    <div class="card-body">
                            <div class="row">
                                <div class="col-lg-3">
                                    <div class="mb-3">  
                                        <label class="custom-control-label" for="customRadioInline1" style="font-weight: bold;">Nacionalidad:</label>
                                    </div>
                                </div>
                                <div class="col-lg-3">
                                    <div class="mb-3">  
                                        <div class="form-check">
                                            <input class="form-check-input" id="customRadioInline1" name="check_registro" type="radio" name="customRadioInline1" value="1" onclick="tiporegistro(this.value);" checked="true">
                                            <label class="custom-control-label" for="customRadioInline1">Nacional</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="mb-3">  
                                        <div class="form-check">
                                            <input class="form-check-input" id="customRadioInline1" name="check_registro" type="radio" name="customRadioInline1" value="2" onclick="tiporegistro(this.value);">
                                            <label class="custom-control-label" for="customRadioInline1">Extranjero</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" id="calle" name="calle" placeholder="Calle" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" id="num_ext" name="num_ext" placeholder="Número Exterior" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" id="num_int" name="num_int" placeholder="Número Interior" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" id="colonia" name="colonia" placeholder="Colonia" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" id="localidad"  name="localidad" placeholder="Localidad" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" id="codigo_postal" name="codigo_postal" placeholder="Código Postal" onkeypress="return soloNumeros(event)" autocomplete="off">
                            </div>
                            <div class="row" id="flotante_nacional">
                                <div class="form-group mb-3">
                                    <input class="form-control" type="text" id="referencia" name="referencia" placeholder="Referencia" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                </div>
                                <div class="form-group mb-3">
                                    <select class="form-control" data-val="true" id="estado_id" name="estado_id" onchange="search_Municipios(this.value)">
                                        <option value="0" disabled selected>-Seleccione un Estado-</option>
                                        <%
                                            if (db.doDB(fac.consultarEstados())) {
                                                for (String[] row : db.getResultado()) {
                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + " - " + row[1] + "</option>");
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-group mb-3" id="municipios"></div>
                            </div>    
                            <div class="row" id="flotante_extranjero" style="display:none;">
                                <div class="form-group mb-3">
                                    <input class="form-control" type="text" id="num_regid_trib" name="num_regid_trib" placeholder="NumRegIDTrib" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                </div>
                                <div class="form-group mb-3">
                                    <div id="flotantepais" style="margin-top:1em; display:none; border-spacing: 5px;">
                                        <label class="pedimentoTag btn btn-info btn-rounded btn-sm">País</label>
                                    </div>
                                    <select class="form-control" data-val="true" id="pais_id" name="pais_id">
                                        <option value="0" disabled selected>-Seleccione un Pais-</option>
                                        <option value="MEX">México (Estados Unidos Mexicanos)</option>
                                    </select>
                                </div>
                                <div class="form-group mb-3">
                                    <input class="form-control" type="text" id="provincia_id" name="provincia_id" placeholder="Província o Estado" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                </div>
                            </div>
                    </div>
                </div>
            </div>
              <!-- Contactos -->
              <div class="col-lg-6 mb-5">
                <div class="card">
                  <div class="card-header">
                    <h4 class="card-heading">Contactos</h4>
                  </div>
                  <div class="card-body">
                        
                        <div class="form-group mb-3">
                            <label class="col-md-4 form-label" style="font-weight: bold;">Contacto 1 (Principal):</label>
                            <input class="form-control" type="text" id="nombreContacto1" name="nombreContacto1" placeholder="Nombre" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                            <input class="form-control form-control-success" type="Correo" id="emailContacto1" name="emailContacto1" placeholder="Email Address" autocomplete="off">
                        </div>
                        <div class="form-group mb-3">
                            <label class="col-md-4 form-label" style="font-weight: bold;">Contacto 2:</label>
                            <input class="form-control" type="text" id="nombreContacto2" name="nombreContacto2" placeholder="Nombre" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                            <input class="form-control form-control-success" type="Correo" id="emailContacto2" name="emailContacto2" placeholder="Email Address" autocomplete="off">
                        </div>
                        <div class="form-group mb-3">
                            <label class="col-md-4 form-label" style="font-weight: bold;">Contacto 3:</label>
                            <input class="form-control" type="text" id="nombreContacto3" name="nombreContacto3" placeholder="Nombre" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                            <input class="form-control form-control-success" type="Correo" id="emailContacto3" name="emailContacto3" placeholder="Email Address" autocomplete="off">
                        </div>
                      
                        <div class="col-md-12 mb-3">
                            <div class="row">
                                <div class="col-md-6">
                                    <input class="form-control" type="text" id="telefono" name="telefono" placeholder="Teléfono" onkeypress="return soloNumeros(event)" autocomplete="off" >
                                </div>
                                <div class="col-md-6">
                                    <input class="form-control col-md-1" type="text" id="pagina_web" name="pagina_web" placeholder="Página Web" autocomplete="off">
                                </div>
                            </div>
                        </div>
                        <div >Comentario para incluir en el Correo Electrónico de envío de CFDI:</div>
                        <div class="form-group mb-3">
                            <textarea class="form-control" id="coment_email_cfdi" name="coment_email_cfdi" autocomplete="off"></textarea>
                        </div>
                  </div>
                </div>
              </div>
            </div>  
              <div class="row">
                  <div class="col-lg-10">
                      <a class="btn btn-primary" href="List.jsp"><i class="fas fa-long-arrow-alt-left"></i>&nbsp;Regresar al listado</a>
                  </div>
                  <div class="col-lg-2">
                      <a class="btn btn-primary" onclick="AddClientes()"> <i class="fa fa-save"> </i>&nbsp;Guardar</a>
                  </div>
              </div>  
         </section>
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
    <!-- Procesos F.E General -->
    <script src="../lib/validations/clientes_3.3/processesClienteGral_3.3.js" type="text/javascript"></script>
    <!-- Procesos Registrar Cliente -->
    <script src="../lib/validations/clientes_3.3/processesRegistrarCliente_3.3.js" type="text/javascript"></script>
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