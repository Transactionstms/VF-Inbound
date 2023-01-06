<%-- 
    Document   : ConfigExpress
    Created on : 29/12/2021, 06:15:21 PM
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
        <title>Configuración Express</title>
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
        <header class="header">
        </header>
        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <!-- Page Header-->
                    <div class="page-header">
                        <h3 class="page-heading">Configuración Express</h3>
                    </div>
                    <section>
                        <div class="row">
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card h-100">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h2 class="card-heading">Régimen Fiscal y Certificado</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <form id="uploadFileFormData" name="uploadFileFormData">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Selecciona un Régimen Fiscal:</label>
                                                    <select class="form-control" data-val="true" id="regimen_id" name="regimen_id">
                                                        <%
                                                            if (db.doDB(fac.consultarRegimenFiscal())) {
                                                                for (String[] row : db.getResultado()) {
                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[1] + " - " + row[2] + "</option>");
                                                                }
                                                            }
                                                        %>
                                                    </select>
                                                </div>
                                            </div>
                                            <br>        
                                            <div class="row">
                                                <div class="col-md-10">
                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Firma Electrónica: (.key)</label>
                                                    <input class="form-control" type="file" id="e_firma" name="e_firma" accept=".key" placeholder="Firma Electrónica">
                                                </div>
                                                <div class="col-md-2" style="text-align: right;">
                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#ffffff;">xxxxxxx</label>
                                                    <button type="button" id="btnDesinatalarCertificado" name="btnDesinatalarCertificado" class="btn btn-primary boton_azul btn-sm" onclick="deleteFirmaElectronica()"><i class="far fa-trash-alt"></i> Eliminar Firma E.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
                                                </div>
                                            </div>        
                                            <br>
                                            <div class="row">
                                                <div class="col-md-10">
                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Certificado: (.cer)</label>
                                                    <input class="form-control" type="file" id="certificado" name="certificado" accept=".cer" placeholder="Certificado">
                                                </div>
                                                <div class="col-md-2" style="text-align: right;">
                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#ffffff;">xxxxxxx</label>
                                                    <button type="button" id="btnDesinatalarCertificado" name="btnDesinatalarCertificado" class="btn btn-primary boton_azul btn-sm" onclick="deleteCertificado()"><i class="far fa-trash-alt"></i> Eliminar Certificado</button>
                                                </div>
                                            </div>  
                                            <br>
                                            <div class="row">
                                                <div class="col-md-10">
                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#707375;">Archivo de Seguridad: (.pfx)</label>
                                                    <input class="form-control" type="file" id="pfx" name="pfx" accept=".pfx" placeholder="Certificado">
                                                </div>
                                                <div class="col-md-2" style="text-align: right;">
                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#ffffff;">xxxxxxx</label>
                                                    <button type="button" id="btnDesinatalarCertificado" name="btnDesinatalarCertificado" class="btn btn-primary boton_azul btn-sm" onclick="deleteCertificado()"><i class="far fa-trash-alt"></i> Eliminar Archivo Seg</button>
                                                </div>
                                            </div> 
                                            <br><hr>
                                            <div class="text-left">
                                                <label class="form-label" style="font-family: Arial; font-weight: bold; color:#222324;">Información del Certificado Instalado: </label>
                                                <p>
                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#222324;">Fecha Instalación: </label>
                                                    lunes, 2 de julio de 2018 a las 05:15:36 p. m.
                                                    <br>
                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#222324;">Número de Certificado: </label>
                                                    00001000000410907895
                                                    <br>
                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#222324;">Fecha de Inicio: </label>
                                                    lunes, 21 de mayo de 2018 a las 03:09:46 p. m.
                                                    <br>
                                                    <label class="form-label" style="font-family: Arial; font-weight: bold; color:#222324;">Fecha de Fin: </label>
                                                    sábado, 21 de mayo de 2022 a las 03:09:46 p. m.
                                                </p>
                                            </div>
                                            <br><br>   
                                            <!-- Botones controles -->
                                            <div class="col-lg-12" style="text-align: right;">
                                                <a class="btn btn-info text-nowrap" role="button" href="../Catalogos/index.jsp">Regresar</a>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <a class="btn btn-primary text-nowrap" id="uploadBtnid" name="uploadBtnid" role="button">Registrar Información</a>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <a class="btn btn-default text-nowrap" role="button" onclick="delete_registro()">Cancelar</a>
                                            </div>
                                        </form>
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
            const uploadinput = document.querySelector('#e_firma');
            const uploadinput2 = document.querySelector('#certificado');
            const uploadinput3 = document.querySelector('#pfx');
            
            const uploadBtn = document.querySelector('#uploadBtnid');
            uploadBtn.addEventListener('click',uploadFile);

            async function uploadFile(){
                
                const formData = new FormData();
                formData.append('uploadFileFormData',uploadinput.files[0], uploadinput2.files[0], uploadinput3.files[0]);    
                try{
                    const response = await fetch("<%=request.getContextPath()%>/InsertarCredenciales_SAT?regimen_id=" + document.getElementById("regimen_id").value,{
                        method:'POST',
                        body:formData
                    } );
                    const result = await response.json();
                    console.log(result);
                }catch(e){
                    console.log(e);
                }
                
            }
            
            function addRegistro(){
                
                let regimen_id = document.getElementById("regimen_id").value;
                let e_firma = document.getElementById("e_firma").value;
                let certificado = document.getElementById("certificado").value;
                
                if(regimen_id == null || regimen_id == 0){ swal("", "Seleccione un regimén fiscal", "info"); alertclose(); return false; }  
                if(e_firma == ""){ swal("Error", "Ingrese firma electronica", "error"); alertclose(); return false; }
                if(certificado == ""){ swal("Error", "Ingrese certificado", "error"); alertclose(); return false; }
                
                fetch("<%=request.getContextPath()%>/InsertarProductos?tipo_registro=" + tipo_registro +
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
                                 swal("", "La información se registro correctamente", "success");  alertclose();
                                 location.reload();
                            } else {
                                 swal("Error", "La información no fue registrado", "error"); alertclose();
                                 //location.reload();
                            }

                        }).catch(error => console.log(error));
                 
            }

            function deleteFirmaElectronica() {
                    swal({
              title: "¿Estás seguro?",
              text: "Si elimina la e.firma, se quitará del sistema",
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
                    swal("e.firma", "Se eliminó correctamente", "success");  alertclose();
              }else{
                  swal.close();
              } 
            });
            }
            
            function deleteCertificado() {
                    swal({
              title: "¿Estás seguro?",
              text: "Si elimina el cerficado, se quitará del sistema",
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
                   swal(".cer", "Se eliminó correctamente", "success");  alertclose();
              }else{
                  swal.close();
              } 
            });
            }
            
            function delete_registro() {
                    swal({
              title: "¿Estás seguro?",
              text: "Si cierras la ventana, tus datos no seran guardados",
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
                   window.location.href = "../Catalogos/index.jsp";
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
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
        <!-- Main Theme JS File-->
        <script src="../lib/js/theme.js"></script>
        <!-- Prism for syntax highlighting-->
        <script src="../lib/vendor/prismjs/prism.js"></script>
        <script src="../lib/vendor/prismjs/plugins/normalize-whitespace/prism-normalize-whitespace.min.js"></script>
        <script src="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.min.js"></script>
        <script src="../lib/vendor/prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard.min.js"></script>
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