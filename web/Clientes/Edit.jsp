<%-- 
    Document   : Edit
    Created on : 29/12/2021, 05:58:10 PM
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
                String idCliente = request.getParameter("id");
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
                                    <input class="form-control" type="text" id="cliente_descripcion" name="cliente_descripcion" placeholder="Nombre" autocomplete="off">
                                </div>
                                <div class="form-group mb-3">
                                    <input class="form-control" type="text" id="rfc" name="rfc" placeholder="RFC">
                                </div>
                                 <div class="form-group mb-3">
                                     <input class="form-control" type="text" id="nombre_comercial" name="nombre_comercial" placeholder="Nombre Comercial" autocomplete="off">
                                </div>
                                <div class="form-group mb-3">
                                  <input class="form-control" type="text" id="codigo_cliente" name="codigo_cliente" placeholder="Código del Cliente" autocomplete="off">
                                </div>
                                 <div class="form-group mb-3">
                                     <input class="form-control" type="text"  id="num_cliente" name="num_cliente" placeholder="Número de Cliente" autocomplete="off">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <div class="mb-3">
                                    <input class="form-control" type="text" id="nombre_comprador" name="nombre_comprador" placeholder="Nombre de Comprador" autocomplete="off">
                                </div>
                                <div class="mb-3">
                                    <select class="form-control" id="metodo_id" name="metodo_id">
                                        <option value="0" disabled selected>-- Seleccione m&#233;todo de pago --</option>
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
                                    <select class="form-control" id="usocfdi_id" name="usocfdi_id">
                                        <option value="0" disabled selected>-- Seleccione un uso de CFDI --</option>
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
                                    <div class="form-check mb-4">
                                        <input class="form-check-input" id="check_xml" name="check_xml" type="checkbox" value="1" onclick="habilitarXML()">
                                        <label class="custom-control-label" for="customCheck1">Enviar XML</label>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <div class="form-check mb-4">
                                        <input class="form-check-input" id="check_pdf" name="check_pdf" type="checkbox" value="2" onclick="habilitarPDF()">
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
                                <input class="form-control" type="text" id="calle" name="calle" placeholder="Calle" autocomplete="off">
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" id="num_ext" name="num_ext" placeholder="Número Exterior" autocomplete="off">
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" id="num_int" name="num_int" placeholder="Número Interior" autocomplete="off">
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" id="colonia" name="colonia" placeholder="Colonia" autocomplete="off">
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" id="localidad"  name="localidad" placeholder="Localidad" autocomplete="off">
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" type="text" id="codigo_postal" name="codigo_postal" placeholder="Código Postal" autocomplete="off">
                            </div>
                            <div class="row" id="flotante_nacional">
                                <div class="form-group mb-3">
                                    <input class="form-control" type="text" id="referencia" name="referencia" placeholder="Referencia" autocomplete="off">
                                </div>
                                <div class="form-group mb-3">
                                    <select class="form-control" data-val="true" id="estado_id" name="estado_id">
                                        <option value="0" disabled selected>-Seleccione un Estado-</option>
                                        <option value="JAL">GUA - México</option>
                                    </select>
                                </div>
                                <div class="form-group mb-3">
                                    <select class="form-control" data-val="true" id="municipio_id" name="municipio_id">
                                        <option value="0" disabled selected>-Seleccione un Municipio-</option>
                                        <option value="053">GUA - Acambay de Ruíz Castañeda</option>
                                    </select>
                                </div>
                            </div>    
                            <div class="row" id="flotante_extranjero" style="display:none;">
                                <div class="form-group mb-3">
                                    <input class="form-control" type="text" id="num_regid_trib" name="num_regid_trib" placeholder="NumRegIDTrib" autocomplete="off">
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
                                    <input class="form-control" type="text" id="provincia_id" name="provincia_id" placeholder="Província o Estado" autocomplete="off">
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
                            <input class="form-control" type="text" id="nombreContacto1" name="nombreContacto1" placeholder="Nombre" autocomplete="off">
                            <input class="form-control form-control-success" type="Correo" id="emailContacto1" name="emailContacto1" placeholder="Email Address" autocomplete="off">
                        </div>
                        <div class="form-group mb-3">
                            <label class="col-md-4 form-label" style="font-weight: bold;">Contacto 2:</label>
                            <input class="form-control" type="text" id="nombreContacto2" name="nombreContacto2" placeholder="Nombre" autocomplete="off">
                            <input class="form-control form-control-success" type="Correo" id="emailContacto2" name="emailContacto2" placeholder="Email Address" autocomplete="off">
                        </div>
                        <div class="form-group mb-3">
                            <label class="col-md-4 form-label" style="font-weight: bold;">Contacto 3:</label>
                            <input class="form-control" type="text" id="nombreContacto3" name="nombreContacto3" placeholder="Nombre" autocomplete="off">
                            <input class="form-control form-control-success" type="Correo" id="emailContacto3" name="emailContacto3" placeholder="Email Address" autocomplete="off">
                        </div>
                      
                        <div class="col-md-12 mb-3">
                            <div class="row">
                                <div class="col-md-6">
                                    <input class="form-control" type="text" id="telefono" name="telefono" placeholder="Teléfono" autocomplete="off" >
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
              
              <input type="hidden" id="id_cliente" name="id_cliente" value="<%=idCliente%>">
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
    <script>
            window.onload=function() {
                 detailsCliente();
            }

            function detailsCliente(){

                    let at1 = document.getElementById("id_cliente").value;

                    fetch("<%=request.getContextPath()%>/ConsultarDataCliente?idCliente=" + at1, {
                          method: 'POST', 
                          }) .then(r => r.text())
                             .then(data => {
                                 let input = data.split("*");
                                 let a0t = input[0]; //CLIENTE_DESCRIPCION
                                 let a1t = input[1]; //RFC
                                 let a2t = input[2]; //NOMBRE_COMERCIAL 
                                 let a3t = input[3]; //CODIGO_CLIENTE
                                 let a4t = input[4]; //NUM_CLIENTE
                                 let a5t = input[5]; //CALLE
                                 let a6t = input[6]; //NUM_EXT
                                 let a7t = input[7]; //NUM_INT
                                 let a8t = input[8]; //COLONIA
                                 let a9t = input[9]; //LOCALIDAD
                                 let a10t = input[10]; //REFERENCIA
                                 let a11t = input[11]; //NUM_REGID_TRIB
                                 let a12t = input[12]; //CODIGO_POSTAL
                                 let a13t = input[13]; //PAIS_ID
                                 let a14t = input[14]; //PROVINCIA_ID
                                 let a15t = input[15]; //ESTADO_ID
                                 let a16t = input[16]; //MUNICIPIO_ID
                                 let a17t = input[17]; //NOMBRE_COMPRADOR
                                 let a18t = input[18]; //METODO_ID
                                 let a19t = input[19]; //FORMA_ID
                                 let a20t = input[20]; //USOCFDI_ID
                                 let a21t = input[21]; //TELEFONO
                                 let a22t = input[22]; //PAGINA_WEB
                                 let a23t = input[23]; //COMENT_EMAIL_CFDI
                                 let a24t = input[24]; //FECHA_REGISTRO
                                 let a25t = input[25]; //NOMBRE_CONTACTO1
                                 let a26t = input[26]; //CORREO_CONTACTO1
                                 let a27t = input[27]; //NOMBRE_CONTACTO2
                                 let a28t = input[28]; //CORREO_CONTACTO2
                                 let a29t = input[29]; //NOMBRE_CONTACTO3
                                 let a30t = input[30]; //CORREO_CONTACTO3
                                 let a31t = input[31]; //EMAIL_XML
                                 let a32t = input[32]; //EMAIL_PDF

                                document.getElementById("cliente_descripcion").value = a0t;  //CLIENTE_DESCRIPCION
                                document.getElementById("rfc").value = a1t;  //RFC
                                document.getElementById("nombre_comercial").value = a2t;  //NOMBRE_COMERCIAL
                                document.getElementById("codigo_cliente").value = a3t;  //CODIGO_CLIENTE
                                document.getElementById("num_cliente").value = a4t;  //NUM_CLIENTE
                                document.getElementById("calle").value = a5t;  //CALLE
                                document.getElementById("num_ext").value = a6t;  //NUM_EXT
                                document.getElementById("num_int").value = a7t;  //NUM_INT
                                document.getElementById("colonia").value = a8t;  //COLONIA
                                document.getElementById("localidad").value = a9t;  //LOCALIDAD
                                document.getElementById("referencia").value = a10t; //REFERENCIA
                                document.getElementById("num_regid_trib").value = a11t; //NUM_REGID_TRIB
                                document.getElementById("codigo_postal").value = a12t; //CODIGO_POSTAL
                                document.getElementById("pais_id").value = a13t; //PAIS_ID
                                document.getElementById("provincia_id").value = a14t; //PROVINCIA_ID
                                document.getElementById("estado_id").value = a15t; //ESTADO_ID
                                document.getElementById("municipio_id").value = a16t; //MUNICIPIO_ID
                                document.getElementById("nombre_comprador").value = a17t; //NOMBRE_COMPRADOR
                                document.getElementById("metodo_id").value = a18t; //METODO_ID
                                document.getElementById("forma_id").value = a19t; //FORMA_ID
                                document.getElementById("usocfdi_id").value = a20t; //USOCFDI_ID
                                document.getElementById("telefono").value = a21t; //TELEFONO
                                document.getElementById("pagina_web").value = a22t; //PAGINA_WEB
                                document.getElementById("coment_email_cfdi").value = a23t; //COMENT_EMAIL_CFDI
                                //document.getElementById("").value = a24t; //FECHA_REGISTRO
                                document.getElementById("nombreContacto1").value = a25t; //NOMBRE_CONTACTO1
                                document.getElementById("emailContacto1").value = a26t; //CORREO_CONTACTO1
                                document.getElementById("nombreContacto2").value = a27t; //NOMBRE_CONTACTO2
                                document.getElementById("emailContacto2").value = a28t; //NOMBRE_CONTACTO2
                                document.getElementById("nombreContacto3").value = a29t; //NOMBRE_CONTACTO3
                                document.getElementById("emailContacto3").value = a30t; //CORREO_CONTACTO3
                                //document.getElementById("").value = a31t; //EMAIL_XML
                                //document.getElementById("").value = a32t; //EMAIL_PDF

                          }).catch(error => console.log(error));
                }
    </script>         
    <script>
        
        let tipoRegistro = 0;
        let xml = 0;
        let pdf = 0;
        
        function tiporegistro(id_reg){
            
            tipoRegistro = id_reg;
                
            if(id_reg === '1'){ //Nacional
                
                div = document.getElementById('flotante_nacional');
                div.style.display = '';

                div = document.getElementById('flotante_extranjero');
                div.style.display = 'none';

                document.getElementById("num_regid_trib").value = '';
                document.getElementById("pais_id").value = '';
                document.getElementById("provincia_id").value = '';
                
                div = document.getElementById('flotantepais');
                div.style.display = 'none';
                
            }else{              //Extranjero
                
                div = document.getElementById('flotante_nacional');
                div.style.display = 'none'; 

                div = document.getElementById('flotante_extranjero');
                div.style.display = '';

                document.getElementById("referencia").value = '';
                document.getElementById("estado_id").value = '';
                document.getElementById("municipio_id").value = '';
                
                div = document.getElementById('flotantepais');
                div.style.display = '';
            }
        }
        
        function habilitarXML(){
            if(document.getElementById("check_xml").checked){
                xml = "1";
            }else{
                xml = "0";
            }
        }
    
        function habilitarPDF(){
            if(document.getElementById("check_pdf").checked){
                pdf = "1";
            }else{
                pdf = "0";
            }
        }
        
        function AddClientes(){
            
            let id_cliente = document.getElementById("id_cliente").value;     
            let cliente_descripcion = document.getElementById("cliente_descripcion").value; 
            let rfc = document.getElementById("rfc").value;                
            let nombre_comercial = document.getElementById("nombre_comercial").value;    
            let codigo_cliente = document.getElementById("codigo_cliente").value;      
            let num_cliente = document.getElementById("num_cliente").value;         
            let calle = document.getElementById("calle").value;              
            let num_ext = document.getElementById("num_ext").value;             
            let num_int = document.getElementById("num_int").value;             
            let colonia = document.getElementById("colonia").value;             
            let localidad = document.getElementById("localidad").value;          
            let referencia = document.getElementById("referencia").value;          
            let num_regid_trib = document.getElementById("num_regid_trib").value;      
            let codigo_postal = document.getElementById("codigo_postal").value;       
            let pais_id = document.getElementById("pais_id").value;             
            let provincia_id = document.getElementById("provincia_id").value;        
            let estado_id = document.getElementById("estado_id").value;           
            let municipio_id = document.getElementById("municipio_id").value;       
            let nombre_comprador = document.getElementById("nombre_comprador").value;    
            let metodo_id = document.getElementById("metodo_id").value;          
            let forma_id = document.getElementById("forma_id").value;           
            let usocfdi_id = document.getElementById("usocfdi_id").value;        
            let nombreContacto1 = document.getElementById("nombreContacto1").value;  
            let emailContacto1 = document.getElementById("emailContacto1").value;  
            let nombreContacto2 = document.getElementById("nombreContacto2").value;  
            let emailContacto2 = document.getElementById("emailContacto2").value;  
            let nombreContacto3 = document.getElementById("nombreContacto3").value;  
            let emailContacto3 = document.getElementById("emailContacto3").value;  
            let telefono = document.getElementById("telefono").value;            
            let pagina_web = document.getElementById("pagina_web").value;          
            let coment_email_cfdi = document.getElementById("coment_email_cfdi").value; 

            if(cliente_descripcion == ""){ swal("Error", "Nombre de cliente inválido", "error"); alertclose(); return false; }
            if(rfc == ""){ swal("Error", "El RFC del cliente es inválido", "error"); alertclose(); return false; }
            if(metodo_id == ""){ swal("Error", "Seleccione un método de pago", "error"); alertclose(); return false; }
            if(forma_id == ""){ swal("Error", "Seleccione una forma de pago", "error"); alertclose(); return false; }
            if(usocfdi_id == ""){ swal("Error", "Seleccione un uso de cfdi", "error"); alertclose(); return false; }
            if(calle == ""){ swal("Error", "Agregue al menos una dirección", "error"); alertclose(); return false; }
            if(colonia == ""){ swal("Error", "Agregue al menos una colonia", "error"); alertclose(); return false; }
            if(localidad == ""){ swal("Error", "Agregue al menos una localidad", "error"); alertclose(); return false; }
            if(codigo_postal == ""){ swal("Error", "Agregue al menos un código postal", "error"); alertclose(); return false; }
            if(estado_id == ""){ swal("Error", "Seleccione un estado", "error"); alertclose(); return false; }
            if(municipio_id == ""){ swal("Error", "Seleccione un municipio", "error"); alertclose(); return false; }
            
            if(tipoRegistro == '2'){
              if(num_regid_trib == ""){ swal("", "Ingrese número de regimen tributario", "info"); alertclose(); return false; }
              if(pais_id == null || pais_id == 0){ swal("", "Seleccione el país", "info"); alertclose(); return false; }  
              if(provincia_id == null || provincia_id == 0){ swal("", "Seleccione provincia", "info"); alertclose(); return false; }  
            }
            
            if(document.getElementById("check_xml").checked){
                if(nombreContacto1 == ""){ swal("Error", "Agregue al menos uncontacto", "error"); alertclose(); return false; }
                if(emailContacto1 == ""){ swal("Error", "Agregue al menos un correo", "error"); alertclose(); return false; }
            }
            
            if(document.getElementById("check_pdf").checked){
                if(nombreContacto1 == ""){ swal("Error", "Agregue al menos uncontacto", "error"); alertclose(); return false; }
                if(emailContacto1 == ""){ swal("Error", "Agregue al menos un correo", "error"); alertclose(); return false; }
            }
            
            fetch("<%=request.getContextPath()%>/InsertarClientes?id_cliente=" + id_cliente
                                                                 "&cliente_descripcion=" + cliente_descripcion + 
                                                                 "&rfc=" + rfc +       
                                                                 "&nombre_comercial=" + nombre_comercial +          
                                                                 "&codigo_cliente=" + codigo_cliente +      
                                                                 "&num_cliente=" + num_cliente +     
                                                                 "&calle=" + calle +      
                                                                 "&num_ext=" + num_ext +             
                                                                 "&num_int=" + num_int +             
                                                                 "&colonia=" + colonia +    
                                                                 "&localidad=" + localidad +      
                                                                 "&referencia=" + referencia +  
                                                                 "&num_regid_trib=" + num_regid_trib +
                                                                 "&codigo_postal=" + codigo_postal +       
                                                                 "&pais_id=" + pais_id +       
                                                                 "&provincia_id=" + provincia_id +        
                                                                 "&estado_id=" + estado_id +     
                                                                 "&municipio_id=" + municipio_id +     
                                                                 "&nombre_comprador=" + nombre_comprador +   
                                                                 "&metodo_id=" + metodo_id +       
                                                                 "&forma_id=" + forma_id +          
                                                                 "&usocfdi_id=" + usocfdi_id +  
                                                                 "&nombreContacto1=" + nombreContacto1 + 
                                                                 "&emailContacto1=" + emailContacto1 + 
                                                                 "&nombreContacto2=" + nombreContacto2 + 
                                                                 "&emailContacto2=" + emailContacto2 + 
                                                                 "&nombreContacto3=" + nombreContacto3 + 
                                                                 "&emailContacto3=" + emailContacto3 + 
                                                                 "&telefono=" + telefono +   
                                                                 "&pagina_web=" + pagina_web +  
                                                                 "&coment_email_cfdi=" + coment_email_cfdi + 
                                                                 "&xml=" + xml +
                                                                 "&pdf=" + pdf, {
              method: 'POST', 
              }) .then(r => r.text())
                 .then(data => { 

                    if (data === "true") {
                         swal("", "Registro exitoso", "success"); alertclose();
                         location.reload();
                    } else {
                         swal("Error", "El cliente no fue registrado", "error"); alertclose();
                         //location.reload();
                    }

              }).catch(error => console.log(error)); 
                
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
    </script>           
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
    <!-- sweetalert -->
    <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
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
