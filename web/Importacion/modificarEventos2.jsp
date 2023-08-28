<%-- 
    Document   : modificarEventos2
    Created on : 10-may-2023, 14:46:37
    Author     : grecendiz
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

<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Eventos </title>
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
        <!--  jquery 2.1 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <!--  choosen-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.1.0/chosen.min.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.1.0/chosen.jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                 diasEtaDc(1);
            });
        </script>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                ConsultasQuery fac = new ConsultasQuery();

                //Parametros Filtros Principales
                String evento = request.getParameter("id");
                String ship = request.getParameter("ship");
                String con = request.getParameter("con");
                
                //Variables Select´s
                String responsableOptions = "";
                String pol = "";
                String pod = "";
                String bd = "";
                String sbu = "";
                String loadT = "";
                
                //Variables Historico
                String responsable = "";
                String finaldes    = "";
                String Brand       = "";
                int sbu_name = 0;
                String Shipment = "";
                String Load1    = "";
                String quantity          = "";
                String infoPod           = "";
                String est_departure_pol = "";
                String eta_port_discharge = "";
                String max_flete          = "";
                String eta_plus2          = "";
                String eta_plus         = "";
                String infoPol         = "";
                String observaciones    = "";
                String actual_crd       = "";
                String cantidadFinal    = ""; 
                String agenteAduanal = "";
                
                //Variables Generales
                String bl       = "";
                String idBrandivision = "";
                int valorFinalQuantity = 0;
                
                //Consultas - Combos/selects
                if (db.doDB(fac.consultarResponsable_me())) {
                    for (String[] row : db.getResultado()) {
                        responsableOptions += "<option value='" + row[0] + "' >" + row[1] + "</option>";
                    }
                }

                if (db.doDB(fac.consultarPol_me())) {
                    for (String[] row : db.getResultado()) {
                        pol += "<option value='" + row[0] + "' >" + row[1] + "</option>";
                    }
                }

                if (db.doDB(fac.consultarPod_me())) {
                    for (String[] row : db.getResultado()) {
                        pod += "<option value='" + row[0] + "' >" + row[1] + "</option>";
                    }
                }

                if (db.doDB(fac.consultarBrandDivision_me())) {
                    for (String[] row : db.getResultado()) {
                        bd += "<option value='" + row[0] + "' >" + row[1] + "</option>";
                    }
                }

                if (db.doDB(fac.consultarDivision_me())) {
                    for (String[] row : db.getResultado()) {
                        sbu += "<option value='" + row[0] + "' >" + row[1] + "</option>";
                    }
                }

                if (db.doDB(fac.consultarLoadType_me())) {
                    for (String[] row : db.getResultado()) {
                        loadT += "<option value='" + row[0] + "' >" + row[0] + "</option>";
                    }
                }
        %>
 
        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <section>

                        <div class="col-lg-12 mb-5">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-heading">MODIFICAR EVENTO</h4>
                                </div>
                                <div class="card-body">
                                    <!--  <p>Lorem ipsum dolor sit amet consectetur.</p>-->

                                    <%
                                        if (db.doDB(fac.consultarEventoFormulario(evento, ship, con))) {
                                            for (String[] row : db.getResultado()) {
                                                
                                                idBrandivision = row[3]; 
                                                
                                                con = row[6];
                                                bl = row[7];
                                                responsable = row[26];
                                                finaldes = row[2];
                                                Brand = row[3];
                                                sbu_name = Integer.valueOf(row[27]);
                                                Shipment = row[5];
                                                Load1 = row[22];
                                                quantity = row[9];
                                                infoPod = row[10];
                                                est_departure_pol = row[11];
                                                eta_port_discharge = row[12];
                                                max_flete = row[13];
                                                eta_plus2 = row[23];
                                                eta_plus = row[24];
                                                infoPol= row[28];
                                                observaciones = row[25];
                                                actual_crd = row[29];
                                                cantidadFinal = row[30]; 
                                                agenteAduanal = row[31]; 
                                                
                                                //Valor Final - Campo Quantity
                                                if(sbu_name==0){ valorFinalQuantity = Integer.valueOf(row[9]); }else{ valorFinalQuantity = Integer.valueOf(row[30]); }

                                    %>

                                    <form>

                                        <div class="row">
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Número de evento</strong></label>
                                                    <input class="form-control" id="numEventoActual" name="numEventoActual" type="text" value="<%=row[0]%>" onchange="validarNumEvento(this.value);">                                                    
                                                </div>
                                            </div>  
                                              <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Container</strong></label>
                                                    <input class="form-control" type="text" id="con" name="con" value="<%=row[6]%>">
                                                </div>
                                            </div>  
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>BL/ AWB/ PRO</strong></label>
                                                    <input class="form-control" type="text" placeholder="..." value="<%=row[7]%>" name="bl" id="bl" >
                                                </div>
                                            </div>     
                                                
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Responsable</strong></label> 
                                                    <select class="form-control chosen" data-placeholder="..." name="responsable" id="responsable">                     
                                                        <option value="<%=row[26]%>"><%=row[1]%></option>
                                                        <%=responsableOptions%>
                                                    </select>
                                                </div>
                                            </div>  
                                                    
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Final Destination (Shipment)</strong></label>
                                                    <input class="form-control" type="text" placeholder="..." value="<%=row[2]%>" name="finaldes" id="finaldes">
                                                </div>
                                            </div> 
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Brand-Division</strong></label>
                                                    <select class="form-control chosen" data-placeholder="..." name="Brand" id="Brand" onchange="diasBrandDivision();">                     
                                                        <option value="<%=row[3]%>"><%=row[21]%></option> 
                                                        <%=bd%>
                                                    </select>
                                            </div>     
                                            </div> 
                                                
                                                    
                                                    
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Division</strong></label>
                                                    <select class="form-control chosen" data-placeholder="..." name="sbu_name" id="sbu_name" onchange="diasBrandDivision();">                     
                                                        <option value="<%=row[27]%>"><%=row[4]%></option>
                                                        <%=sbu%>
                                                    </select>
                                                </div>
                                            </div>  
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Shipment ID</strong></label>
                                                    <input class="form-control" type="text" placeholder="..." value="<%=row[5]%>" name="Shipment" id="Shipment">
                                                </div>
                                            </div>   
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Load Type</strong></label>
                                                    <select class="form-control chosen" name="Load1" id="Load1" onchange="diasLoadType(this.value);">                     
                                                        <option value="<%=row[22]%>"><%=row[22]%></option>
                                                        <%=loadT%>
                                                    </select>
                                                </div>
                                            </div>  

 <!--*******************************************************-->


                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Quantity</strong></label>
                                                    <input class="form-control" type="text" placeholder="..." value="<%=valorFinalQuantity%>" name="quantity" id="quantity">
                                                </div>
                                            </div>  
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>POD</strong></label> 
                                                    <select class="form-control chosen" data-placeholder="..." name="pod" id="pod">                     
                                                        <option value="<%=row[10]%>"><%=row[19]%></option>
                                                        <%=pod%>
                                                    </select>
                                                </div>
                                            </div>  
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Est. Departure from POL</strong></label>
                                                    <input class="form-control datepicker" type="text"    placeholder="MM/DD/YYYY" value="<%=row[11]%>" name="est_departure_pol" id="est_departure_pol">
                                                </div>
                                            </div>  





                                            <!--*******************************************************-->

                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>ETA REAL PORT</strong></label>
                                                    <input class="form-control datepicker" type="text" placeholder="..." value="<%=row[12]%>" name="eta_port_discharge" id="eta_port_discharge">
                                                </div>
                                            </div>  
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>LT2</strong></label>
                                                    <input class="form-control" type="text" placeholder="..." value="<%=row[13]%>" name="max_flete" id="max_flete" onchange="diasLt2(this.value);">
                                                </div>
                                            </div>  
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>ETA DC</strong></label>
                                                    <input class="form-control datepicker" type="text" placeholder="..." value="<%=row[23]%>" name="eta_plus2" id="eta_plus2" onchange="diasEtaDc(2);">
                                                </div>
                                            </div>  


 <!--*******************************************************-->

                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>INDC +2 Days Put Away</strong></label>
                                                    <input class="form-control datepicker" type="text" placeholder="..." value="<%=row[24]%>" name="eta_plus" id="eta_plus">
                                                </div>
                                            </div>  
                                            
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>POL</strong></label> 
                                                    <select class="form-control chosen" data-placeholder="..." name="pol" id="pol">                     
                                                        <option value="<%=row[28]%>"><%=row[20]%></option>
                                                        <%=pol%>
                                                    </select>

                                                </div>
                                            </div>  
                                                    
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Observaciones</strong></label>
                                                    <input class="form-control" type="text" placeholder="..." value="<%=row[25]%>" name="observaciones" id="observaciones" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                </div>
                                            </div> 
                                                
<!--*******************************************************-->

                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>ACTUAL CRD</strong></label>
                                                    <input class="form-control datepicker" type="text" placeholder="..." value="<%=row[29]%>" name="actual_crd" id="actual_crd" onchange="diasActualCrd(this.value);">
                                                </div>
                                            </div> 

                                        </div>

                                        <div class="mb-3">       
                                            <button class="btn btn-primary" type="button" onclick="getData()">Modificar</button>
                                        </div>
                                    </form>
                                    <%
                                            }
                                        }
                                    %>
                                </div>
                            </div>
                        </div>

                    </section>
                </div>  

            </div>
        </div>      
                                
        <script type="text/javascript">
            //Variables Historico
            let campo0 = '<%=evento%>';  //Evento*
            let campo1 = '<%=con%>';     //Contenedor*
            let campo2 = '<%=bl%>';       
            let campo3 = '<%=responsable%>';
            let campo4 = '<%=finaldes%>';
            let campo5 = '<%=Brand%>';
            let campo6 = '<%=sbu_name%>';
            let campo7 = '<%=Shipment%>';  //ShipmentId*
            let campo8 = '<%=Load1%>';
            let campo9 = '<%=quantity%>';
            let campo10 = '<%=infoPod%>';
            let campo11 = '<%=est_departure_pol%>';
            let campo12 = '<%=eta_port_discharge%>';
            let campo13 = '<%=max_flete%>';
            let campo14 = '<%=eta_plus2%>';
            let campo15 = '<%=eta_plus%>';
            let campo16 = '<%=infoPol%>';
            let campo17 = '<%=observaciones%>';
            let campo18 = '<%=actual_crd%>';   
        </script>
        <!-- utileria gral -->
        <script src="../lib/js/ModificacionEventos/utileriaGral.js" type="text/javascript"></script>
        <!-- consultar núm de evento -->
        <script src="../lib/js/ModificacionEventos/consultarNumEventoActual.js" type="text/javascript"></script>
        <!-- calcular días finales/estimados -->
        <script src="../lib/js/ModificacionEventos/calcularDiasFinales.js" type="text/javascript"></script>
        <!-- registrar información -->
        <script src="../lib/js/ModificacionEventos/actualizarEvento.js" type="text/javascript"></script>
        <!-- sweetAlert 1.3 --->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
        <!-- dataPiker/Calendarios -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.css">
        <script src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.js"></script>
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"  >
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