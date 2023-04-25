<%-- 
    Document   : customForm
    Created on : 27/02/2023, 11:55:23 AM
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
<%@page import="com.usuario.Usuario"%>
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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" sizes="16x16" href="../plugins/images/favicon.png">
        <title>Personalizar Evento</title>
        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
        <link rel="stylesheet" href="../lib/css/custom.css">
        <script src="lib/JSplantilla.js" type="text/javascript"></script>
        <!-- Table css -->
        <link href="../lib/validationsInbound/customs/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- jQuery/show modal -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <style>
            #contenedor {
              display: flex;
              flex-direction: row;
              flex-wrap: wrap;
            }

            #contenedor > div {
              width: 50%;
            }

            .columna {
              width:25%;
              float:right;
            }

            @media (max-width: 500px) {
              .columna {
                width:auto;
                float:none;
              }
            }
        </style>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String view = request.getParameter("view");
                String idDivision = ownsession.getAttribute("cbdivcuenta").toString();
                String idBodega = ownsession.getAttribute("cbbodegaId").toString();
                Usuario root = (Usuario) ownsession.getAttribute("login.root");
                String tipoAgente = "3";  // (1)LOGIX       (2)CUSA       (3)GRAL
                String nombre = "";
                String idPlantilla = "26";
                
                if (db.doDB("select NOMBRE from TRA_PLANTILLA where id='" + view + "' ")) {
                    for (String[] row : db.getResultado()) {
                        nombre = row[0];
                    }
                }
        %>
        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <section>
                        <div class="row">
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card h-100">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h2 class="card-heading">Personalizar Evento</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <form id="uploadFileFormData" name="uploadFileFormData">
                                            <div id="contenedor">
                                                <div class="row">
                                                    <div class="columna">
                                                        <button type="button" class="btn btn-success" onclick="openModalPlantilla()">Subir Plantilla</button>
                                                    </div>
                                                    <div class="columna">
                                                        <select class="form-control" id="id" name="id" onchange="customForm('1')" required="true">
                                                            <option value="0" disabled selected>Evento</option>
                                                            <%
                                                                if (db.doDB("SELECT DISTINCT ID_EVENTO FROM TRA_INB_EVENTO WHERE ESTADO = 1 AND USER_NID IS NOT NULL ORDER BY ID_EVENTO ASC")) {
                                                                    for (String[] row : db.getResultado()) {
                                                                        out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                    }
                                                                }
                                                            %>
                                                        </select>
                                                    </div>
                                                    <div class="columna">
                                                        <select class="form-control" id="id" name="id" onchange="customForm('2')" required="true">
                                                            <option value="0" disabled selected>Shipment</option>
                                                            <%
                                                                if (db.doDB("SELECT DISTINCT SHIPMENT_ID FROM TRA_INB_EVENTO WHERE ESTADO = 1 AND USER_NID IS NOT NULL ORDER BY SHIPMENT_ID ASC")) {
                                                                    for (String[] row : db.getResultado()) {
                                                                        out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                    }
                                                                }
                                                            %>
                                                        </select>
                                                    </div>
                                                    <div class="columna">
                                                        <select class="form-control" id="id" name="id" onchange="customForm('3')" required="true">
                                                            <option value="0" disabled selected>Container</option>
                                                            <%
                                                                if (db.doDB("SELECT DISTINCT CONTAINER1 FROM TRA_INB_EVENTO WHERE ESTADO = 1 AND USER_NID IS NOT NULL ORDER BY CONTAINER1 ASC")) {
                                                                    for (String[] row : db.getResultado()) {
                                                                        out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                    }
                                                                }
                                                            %>
                                                        </select>
                                                    </div>
                                                </div>       
                                                <div style="text-align: right;">
                                                    <a class="btn btn-default text-nowrap" role="button" href="Importacion/eventos.jsp">Regresar</a>
                                                    <a class="btn btn-primary text-nowrap" id="uploadBtnid" name="uploadBtnid" role="button" onclick="save()">Guardar Información</a>
                                                </div>
                                            </div>
                                            <br>
                                            <div id="table-scroll" class="table-scroll"  style="height:500px;">
                                                <table id="main-table" class="main-table" style="table-layout:fixed; width:800%;">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col" class="font-titulo">Número de evento <strong style="color:red">*</strong></th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Semaforo</th>                                                 
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Referencia AA</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Responsable</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Final Destination (Shipment)</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Brand-Division</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Division</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Shipment ID</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Container</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">BL/AWB/PRO</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">LoadType</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Quantity</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">POD</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Est. Departure from POL</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">ETA REAL Port of Discharge</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Est. Eta DC</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Inbound notification</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">POL</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">A.A.</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha Mes de Venta</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Prioridad Si/No</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4"> </th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">País Origen</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Size Container</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Valor USD</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">ETA Port Of Discharge</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Agente Aduanal</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Pedimento A1</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Pedimento R1</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Motivo rectificación 1</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Pedimento R1 (2do)</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Motivo rectificación 2</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Recepción Documentos</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#FF4040">Recinto</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#FF4040">Naviera / Forwarder</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#FF4040">Buque</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Revalidación/Liberación de BL</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Previo Origen</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Previo en destino</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Resultado Previo</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Proforma Final</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Requiere permiso Si/No</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha envío Fichas/notas</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fec. Recepción de permisos tramit.</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fec. Act Permisos (Inic Vigencia)</th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fec. Perm. Aut. (Fin de Vigencia)</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Cuenta con CO para aplicar preferencia Arancelaria Si/No</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Aplico Preferencia Arancelaria (CO) Si/No Razon</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Requiere UVA Si/No</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#626567">Requiere CA Si/No</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#626567">Fecha Recepción CA</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#626567">Número de Constancia CA</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#626567">Monto CA</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Documentos Completos</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Pago Pedimento</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Solicitud de transporte</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Modulacion</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Modalidad Camion/Tren</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Resultado Modulacion Verde / Rojo</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Reconocimiento</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha Liberacion</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Sello Origen</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Sello Final</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fecha de retencion por la autoridad</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Fec. de liberacion por ret. de la aut.</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Estatus de la operación</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Motivo Atraso</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#00BFBF">Observaciones</th>
                                                        <%
                                                            if(tipoAgente.equals("1")){        //Logix
                                                        %>    
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Llegada a NOVA</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Llegada a Globe trade SD </th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Archivo M</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha de Archivo M</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha Solicitud de Manipulacion</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha de vencimiento de Manipulacion</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha confirmacion Clave de Pedimento</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha de Recepcion de Incrementables</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">T&E</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha de Vencimiento del Inbound</th>
                                                        <%
                                                            }else if(tipoAgente.equals("2")){  //Cusa
                                                        %>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">No. BULTOS</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Peso (KG)</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Transferencia (SI / NO)</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha Inicio Etiquetado</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Fecha Termino Etiquetado</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Hora de termino Etiquetado</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Proveedor</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Proveedor de Carga</th>
                                                        <%
                                                            }
                                                        %> 
                                                        </tr>
                                                    </thead>
                                                    <tbody id="detalleCustom"></tbody>
                                                </table>
                                            </div>
                                            <br>
                                        </form>
                                    </div>                    
                                </div>
                            </div>
                        </div>   
                    </section>
                </div>  
                <footer class="footer bg-white shadow align-self-end py-3 px-xl-5 w-100">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-6 text-center text-md-start fw-bold">
                                <p class="mb-2 mb-md-0 fw-bold">Transactions TMS &copy; <%=part3%></p>
                            </div>
                            <div class="col-md-6 text-center text-md-end text-gray-400">
                                <p class="mb-0">Version 1.1.0</p>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div> 
        <!-- modal - Subir Plantilla --> 
        <div class="modal fade text-start" id="modalSubirPlantilla" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header border-0 bg-gray-100">
                        <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;<%=nombre%></h3>
                        <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="card mb-4">
                            <div class="card-header">
                                <div class="card-heading">*Descarga el archivo, llena los datos y sube tu documento</div>
                            </div>
                            <div class="card-body text-muted">
                                <!-- Selección de Clientes -->
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="mb-3">  
                                            <div class="row">
                                                <div class="col-lg-3"></div> 
                                                <div class="col-md-6 col-lg-11 mb-4">
                                                    <div class="card h-100">
                                                        <div class="card-header py-4">
                                                            <!--<h6 class="card-heading">nombre</h6>-->
                                                        </div>
                                                        <div class="card-body pt-3">
                                                            <div class="mb-3">
                                                                <label for="input-id" class="form-label">Selecciona </label>
                                                                <input class="form-control" type="file" id="input-id" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
                                                            </div>
                                                            <div class="row position-relative" style="top: 10px;">
                                                                <div class="col-6 text-center">
                                                                    <button class="btn float-start btn-primary" id="created_file">Descargar</button>
                                                                </div>
                                                                <div class="col-6 text-center">
                                                                    <button class="btn float-end btn-success" id="upload_file">Subir</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div align="center" class="col-md-8">
                                                    <div align="center" id="divResultado" name="divResultado"></div>
                                                </div>
                                                <input type="hidden" name="idPlantilla" value="<%=idPlantilla%>" id="idPlantilla"/>
                                                <input type="hidden" name="idOpcion" value="1" id="idOpcion"/>
                                                <input type="hidden" name="idLenguaje" value="1" id="idLenguaje"/>
                                                <input type="hidden" name="idDivision" value="<%=idDivision%>" id="idDivision"/>
                                                <input type="hidden" name="idBodega" value="<%=idBodega%>" id="idBodega"/>
                                                <input type="hidden" name="idAction" value="<%=request.getContextPath()%>/plantillaExcel" id="idAction"/>
                                                <img src="../img/loadingCloud.gif" id="idClouding" width="50px" height="50px" name="idClouding" title="Clouding" style="display: none; height: 50px; width: 50px;"/>
                                            </div> 
                                        </div>
                                    </div>
                                </div>  
                            </div>
                        </div> 
                        <input type="hidden" name="idOpcion" value="1" id="idOpcion"/>
                        <input type="hidden" name="idLenguaje" value="1" id="idLenguaje"/>
                        <input type="hidden" name="idDivision" value="<%=idDivision%>" id="idDivision"/>
                        <input type="hidden" name="idBodega" value="<%=idBodega%>" id="idBodega"/>
                        <input type="hidden" name="idAction" value="<%=request.getContextPath()%>/plantillaExcel" id="idAction"/>
                        <img src="../img/loadingCloud.gif" id="idClouding" width="50px" height="50px" name="idClouding" title="Clouding" style="display: none; height: 50px; width: 50px;"/>
                    </div>
                </div>
            </div>
        </div>                
        <script>
            $(document).ready(function () {
                //customForm('0');
            });
            
            function customForm(tipoConsulta) {
                let id = "";
                
                if(id===0){
                    id = "0";
                }else{
                    id = document.getElementById("id").value;
                }
                
                fetch("<%=request.getContextPath()%>/ConsultarCustom?tipoAgente="+<%=tipoAgente%>+"&tipoConsulta=" + tipoConsulta + "&id=" + id, {
                    method: 'POST',
                }).then(r => r.text())
                        .then(data => {
                            document.getElementById('detalleCustom').innerHTML = data;
                        }).catch(error => console.log(error));
            }

            function openModalPlantilla() {
                $("#modalSubirPlantilla").modal("show");
            }
        </script>  
        <script>
            $( '#multiple-select-field' ).select2( {
                theme: "bootstrap-5",
                width: $( this ).data( 'width' ) ? $( this ).data( 'width' ) : $( this ).hasClass( 'w-100' ) ? '100%' : 'style',
                placeholder: $( this ).data( 'placeholder' ),
                closeOnSelect: false,
            } );
        </script>
        <!-- JavaScript files-->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- upload js -->
        <script src="<%=request.getContextPath()%>/plantillas/lib/upload_file.js" type="text/javascript"></script>
        <!-- actions js -->
        <script src="../lib/validationsInbound/customs/customsForms.js" type="text/javascript"></script>
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
