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
        <title>Datos Complementarios</title>
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
        <!-- Table css -->
        <link href="../lib/inbound/eventos/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- Connection Status Red -->
        <link href="../lib/inbound/conexion/connectionStatus.css" rel="stylesheet" type="text/css"/>
        <link href="../lib/css/loader.css" rel="stylesheet" type="text/css"/> 
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

    </head>
    <%
        String nom = request.getParameter("nom");
        String id = request.getParameter("id");
        String ag = request.getParameter("op");
        
  String fec1 = request.getParameter("fec1");
  String fec2 = request.getParameter("fec2");
  
    %>
    <body>
        <div class="preloader" id="loaders">  
            <div class="loader2" ></div>
            <div class="loader" ></div>
        </div>
        <script src="../lib/js/loader.js"></script>

        <!-- navbar-->
        <header class="header"></header>

        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <section>
                        <div class="row">
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card h-100">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <h2 class="card-heading">DATOS COMPLEMENTARIOS</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body col-md-12">
                                        <form class="row " id="" name="" autocomplete="false">
                                            <h3 class="mb-4">Ingresar datos complementarios</h3>

                                            <div class="col-md-6 mb-4">
                                                <label class="form-label" >Transportista</label>
                                                <input type="text" class="form-control"  readonly value="<%=nom%>">
                                            </div>

                                            <div class="col-md-3 mb-4">
                                                <label class="form-label" >Fecha empaque</label>
                                                <div class="input-group date" id="datepicker">
                                                    <input type="text" value="<%=fec1%>" disabled class="infecha form-control" >
                                                </div>   
                                            </div>

                                            <div class="col-md-3 mb-4">
                                                <label class="form-label" >Fecha inicio de entrega</label>
                                                <div class="input-group date" id="datepicker">
                                                      <input type="text" value="<%=fec2%>" disabled class="infecha form-control" >
                                                </div>                                                       
                                            </div> 
                                            
                                            <div class="col-md-3 mb-4">
                                                <label class="form-label" for="camiones">Camiones</label>
                                                <input type="text" class="form-control" id="camiones">
                                            </div>
                                            <div class="col-md-3 mb-4">
                                                <label class="form-label" for="tipoUnidad">Tipo de unidad</label>
                                                <select class="form-select" id="tipoUnidad" aria-label="Default select example">
                                                    <option value="0">Elija una opcion</option>
                                                    <option value="1">	CAM 1.5 TONS</option>
                                                    <option value="2">	CAM 3.5 TONS</option>
                                                    <option value="3">	CAM 5.0 TONS</option>
                                                    <option value="4">	RABON</option>
                                                    <option value="5">	TORTHON</option>
                                                    <option value="6">	MUDANZA</option>
                                                    <option value="7">	TRAILER</option>
                                                    <option value="8">	CAM 0.5 TONS</option> 
                                                </select>
                                            </div>

                                            <div class="col-md-6 mb-4">
                                                <label class="form-label" for="chofer">Chofer</label>
                                                <input type="text" class="form-control" id="chofer">
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <label class="form-label" for="dispositivos">Dispositivos</label>
                                                <input type="number" class="form-control" id="dispositivos">
                                            </div> 

                                            <div class="col-md-6 mb-4">
                                                <label class="form-label" for="fechaRevision">Fecha Revision</label>
                                                <div class="input-group date" id="datepicker">
                                                    <div class="input-group date" id="datepicker">
                                                        <input type="text" name="date3" id="fechaRevision" class="datepicker infecha form-control" autocomplete="off" size="10" required="required">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <label class="form-label" for="selloCaja">Sello de caja</label>
                                                <input type="text" class="form-control" id="selloCaja" autocomplete="false">
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <label class="form-label" for="relacionEntrega">Relacion de entrega</label>
                                                <input type="text" class="form-control" id="relacionEntrega" autocomplete="false">
                                            </div>


                                            <div class="col-md-6 mb-4">
                                                <label class="form-label" for="fechaFinEntrega">Fecha fin de carga</label>
                                                <div class="input-group date" id="datepicker">
                                                    <input type="text" name="date3" id="fechaFinEntrega" class="datepicker infecha form-control" autocomplete="off" size="10" required="required">
                                                </div>   
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <label class="form-label" for="packingList">Packing list</label>
                                                <div class="input-group date" id="datepicker">
                                                    <input type="text" name="date3" id="packingList" class="form-control" autocomplete="off" size="10" required="required">
                                                </div>   
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <label class="form-label" for="autor">Auditor</label>
                                                <input type="text" class="form-control" id="autor" autocomplete="false">
                                            </div>

                                            <div class="col-md-6 mb-4">
                                                <label class="form-label">Observaciones</label>
                                                <textarea type="text" class="form-control" id="observaciones" autocomplete="false"></textarea>
                                            </div>






                                            <div class="text-center mt-5">
                                                <button class="btn btn-primary mb-5 col-3" type="button"id="uploadBtnid" name="uploadBtnid" role="button" onclick="save()">Grabar embarque</button>
                                            </div>

                                        </form>
                                    </div>



                                </div>

                                <!--button-->



                            </div>

                            <br>
                            <!-- Botones controles -->

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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.js"></script>

<script type="text/javascript">

                                                    async function save() {
                                                        // Obtener el valor de cada campo y asignarlo a una variable
                                                        let camionesValue      = document.getElementById('camiones').value;
                                                        let tipoUnidadValue    = document.getElementById('tipoUnidad').value;
                                                        let choferValue        = document.getElementById('chofer').value;
                                                        let dispositivosValue  = document.getElementById('dispositivos').value;
                                                        let fechaRevisionValue = document.getElementById('fechaRevision').value;
                                                        let selloCajaValue     = document.getElementById('selloCaja').value;
                                                        let relacionEntregaValue = document.getElementById('relacionEntrega').value;
                                                        let fechaFinEntregaValue = document.getElementById('fechaFinEntrega').value;
                                                        let packingListValue     = document.getElementById('packingList').value;
                                                        let autorValue           = document.getElementById('autor').value;
                                                        let observacionesValue   = document.getElementById('observaciones').value;


                                                        console.log(camionesValue);
                                                        console.log(tipoUnidadValue);
                                                        console.log(choferValue);
                                                        console.log(dispositivosValue);
                                                        console.log(fechaRevisionValue);
                                                        console.log(selloCajaValue);
                                                        console.log(relacionEntregaValue);
                                                        console.log(fechaFinEntregaValue);
                                                        console.log(packingListValue);
                                                        console.log(autorValue);
                                                        console.log(observacionesValue);
                                                        
                                                        swal("Espere...!");

                                                        try {
                                                            const data = await fetchData('<%=request.getContextPath()%>/ConsultarRepartoTransportistaEdit?camionesValue=' + camionesValue +'&tipoUnidadValue=' + tipoUnidadValue +'&choferValue=' + choferValue + '&dispositivosValue=' + dispositivosValue +'&fechaRevisionValue=' + fechaRevisionValue +'&selloCajaValue=' + selloCajaValue +'&relacionEntregaValue=' + relacionEntregaValue +'&fechaFinEntregaValue=' + fechaFinEntregaValue +'&packingListValue=' + packingListValue +'&autorValue=' + autorValue +'&observacionesValue=' + observacionesValue + '&evento=<%=ag%>');
                                                            console.log(data);
                                                            swal("Modificado");
                                                        } catch (error) {
                                                            swal("Error");
                                                            console.error(error);
                                                        }
                                                    }

                                                async function fetchData(url) {
                                                  const response = await fetch(url);
                                                  const data = await response.text();
                                                  return data;
                                                }


                                                    $(document).ready(function () {
                                                        $('.datepicker').flatpickr({
                                                            dateFormat: 'm/d/Y',
                                                            onClose: function (selectedDates, dateStr, instance) {
                                                                instance.setDate(dateStr, true, 'm/d/Y');
                                                            }
                                                        });
                                                    });

</script>
<!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
</body>
</html>
