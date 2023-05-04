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
        <title>Datos Adicionales</title>
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
                                                <h2 class="card-heading">Datos Adicionales</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body col-md-12">
                                        <form class="row " id="" name="" >
                                            <div class="container">
                                                <div class="row">
                                                    <div class="col-md-6 mb-4">
                                                        <label class="form-label" >Transportista</label>
                                                        <select class="form-select" aria-label="Default select example">
                                                            <option selected>Elija una opcion</option>
                                                            <option  value="3405">AAMER MANEJO DE MUESTRAS QUIMICAS S</option>; <option  value="3188">AGS</option>; <option  value="3047">AIDA NUÑEZ ALLER</option>; <option  value="2905">ALEJANDRO JIMENNEZ PONCE</option>; <option  value="2906">ALEJANDRO SEPULVEDA ALVARADO</option>; <option  value="3225">ALFREDO CARREÑO OLVERA</option>; <option  value="3365">ASIGNACION PENDIENTE</option>; <option  value="3425">AUTO TRANSPORTES CABALLERO E HIJOS,</option>; <option  value="3428">AUTOTANQUES NIETO SA DE CV</option>; <option  value="3305">AUTOTRANSPORTES OCNEPAN S. DE R.L.D</option>; <option  value="3427">BULKMATIC DE MEXICO, S. DE R.L.</option>; <option  value="3105">CLAUDIA GUTIERREZ VILLASE¿OR</option>; <option  value="2845">CLIENTE RECOGE</option>; <option  value="2907">COMPA¿IA INTERAMERICANA DE DISTRIBUCION Y LOGISTICA</option>; <option  value="3285">COVESTRO</option>; <option  value="3245">ESTAFETA</option>; <option  value="3265">FLETES MÉXICO</option>; <option  value="3385">GARCIA MARTINEZ JOSE MARIO</option>; <option  value="3185">Geotrans</option>; <option  value="3366">HAULER CARGO & INTERNATIONAL SERVIC</option>; <option  value="2846">HILTI</option>; <option  value="2945">Hapag Lloyd</option>; <option  value="3045">Juan Pablo Sanchez</option>; <option  value="3046">LUIS ENRIQUE RAMOS BAUTISTA</option>; <option  value="3085">MANUEL ANEL NUÑEZ ALLER</option>; <option  value="3025">MEXICANA DE MENSAJERIA</option>; <option  value="2885">OCURRE DHL</option>; <option  value="2865">OCURRE PAQUETEXPRESS</option>; <option  value="3430">PILAR SANCHEZ</option>; <option  value="3431">RANGEL</option>; <option  value="2985">ROMA EXPRESS</option>; <option  value="3186">Samex</option>; <option  value="3189">Santa Catalina</option>; <option  value="2805">Sin Paqueteria </option>; <option  value="3026">TNL EXPRESS</option>; <option  value="3345">TORRES LOZANO ELIZABETH</option>; <option  value="3005">TRANSPORTE FISA </option>; <option  value="3165">TRANSPORTE PEREZ / DAPYR</option>; <option  value="3065">TRANSPORTES ALLER</option>; <option  value="3429">TRANSPORTES AYALA COLIN,</option>; <option  value="3426">TRANSPORTES ESPECIALIZADOS</option>; <option  value="3325">TRANSPORTES ESPECIALIZADOS CARMEX,</option>; <option  value="3407">TRANSPORTES MARTINEZ CABRERA,</option>; <option  value="2965">TRANSPORTES PINEDA</option>; <option  value="2825">TRANSPORTES ROBLES</option>; <option  value="3406">TRANSPORTES SAL AVE S.A. DE C.V.</option>; <option  value="2925">Transporte Ramsal</option>; <option  value="3205">Transporte San Andres</option>; <option  value="842">Transportes El Cantaro</option>; <option  value="18">Transportes Granados</option>; <option  value="667">Transportes Magno</option>; <option  value="11">Transportes Nieto</option>; <option  value="3145">Transportes Orcasa</option>; <option  value="721">Transportes ROGA</option>; <option  value="3187">Tur Bus</option>; <option  value="3125">XCF TRANSPORTE CARGA CONSOLIDADA</option>;

                                                        </select>
                                                    </div>

                                                    <div class="col-md-3 mb-4">
                                                        <label class="form-label" >Fecha de enrampe</label>
                                                        <div class="input-group date" id="datepicker">
                                                            <input type="datetime-local" name="date3" id="f_enrampe" class="infecha form-control"  autocomplete="off" size="10" required="required" >                                                        </div>
                                                    </div>

                                                    <div class="col-md-3 mb-4">
                                                        <label class="form-label" >Fecha inicio de entrega</label>
                                                        <div class="input-group date" id="datepicker">
                                                            <input type="datetime-local" name="date4" id="f_inicio" class="infecha form-control"  autocomplete="off" size="10" required="required" >
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>



                                    </div>

                                    <!--button-->
                                    <div class="text-center">
                                        <button class="btn btn-primary mb-5 col-3" type="button"id="uploadBtnid" name="uploadBtnid" role="button" onclick="save()">Grabar embarque</button>
                                    </div>


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

<!-- Conexión estatus red -->                    
<script src="../lib/inbound/conexion/connectionStatus.js" type="text/javascript"></script>
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
<!-- actions js -->
<script src="../lib/inbound/eventos/functionsEvents.js" type="text/javascript"></script>
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
</body>
</html>
