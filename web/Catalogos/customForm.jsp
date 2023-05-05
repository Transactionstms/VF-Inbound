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
        <!-- Multiselect -->
        <link href="../lib/Multiselect/css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>
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
            
            #buscador {
              display: flex;
              flex-direction: row;
              flex-wrap: wrap;
            }
            
            #primero {
              width: 90%;
            }
            
            #segundo {
              width: 10%;
            }
        </style>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String view = request.getParameter("view");
                String UserId = (String) ownsession.getAttribute("login.user_id_number");
                String idDivision = ownsession.getAttribute("cbdivcuenta").toString();
                String idBodega = ownsession.getAttribute("cbbodegaId").toString();
                Usuario root = (Usuario) ownsession.getAttribute("login.root");
                ConsultasQuery fac = new ConsultasQuery();
                String tipoAgente = ""; // (4001)LOGIX       (4002)CUSA       (4006)VF
                String nombre = "";
                String idPlantilla = "26";
                
                
               //Obtener el agente aduanal 
                if (db.doDB("SELECT AGENTE_ADUANAL_ID FROM TRA_INB_USUARIO_AA_RELACION WHERE USER_NID = '" + UserId + "'")) {
                    for (String[] row : db.getResultado()) {
                        tipoAgente = row[0]; 
                    }
                }
                
                //Obtener la plantilla ligada al agente aduanal
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
                                        <input type="hidden" id="idAgenteAduanal" name="idAgenteAduanal" value="<%=tipoAgente%>">
                                        <div id="contenedor">
                                            <div class="row">
                                                <div class="columna">
                                                    <button type="button" class="btn btn-success" onclick="openModalPlantilla()">Subir Plantilla</button>
                                                </div>
                                            </div>       
                                            <div style="text-align: right;">
                                                <!--<a class="btn btn-default text-nowrap" role="button" href="Importacion/eventos.jsp">Regresar</a>-->
                                                <a class="btn btn-primary text-nowrap" id="uploadBtnid" name="uploadBtnid" role="button" onclick="AddCustoms()">Guardar Información</a>
                                            </div>
                                        </div>
                                        <br>
                                        <div id="table-scroll" class="table-scroll"  style="height:500px;">
                                            <table id="main-table" class="main-table" style="table-layout:fixed; width:1000%;">
                                                <thead>
                                                    <tr>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4">Semaforo</th>  
                                                        <th scope="col" class="font-titulo" style="text-align:left">
                                                            <center><font size="2">Número de evento <strong style="color:red">*</strong></font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="evento" name="evento" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchEventos())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo">
                                                                    <a class="text-lg text-info" onclick="customForm('1')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="2">Referencia AA</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="referencia" name="referencia" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchReferenciaAA())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo">
                                                                    <a class="text-lg text-info" onclick="customForm('2')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="2">Responsable</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="responsable" name="responsable" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchResponsable())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo"> 
                                                                    <a class="text-lg text-info" onclick="customForm('3')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="1">Final Destination</font></center>
                                                            <div id="buscador">
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="fDestination" name="fDestination" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchShipment())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo"> 
                                                                    <a class="text-lg text-info" onclick="customForm('4')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="2">Brand-Division</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="brandDivision" name="brandDivision" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchBrandDivision())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo">     
                                                                    <a class="text-lg text-info" onclick="customForm('5')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="2">Division</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="division" name="division" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchDivision())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo"> 
                                                                    <a class="text-lg text-info" onclick="customForm('6')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="2">Shipment ID</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="shipmentId" name="shipmentId" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchShipmentId())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo"> 
                                                                    <a class="text-lg text-info" onclick="customForm('7')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="2">Container</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="container" name="container" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchContainer())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo">     
                                                                    <a class="text-lg text-info" onclick="customForm('8')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="2">BL/AWB/PRO</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="blAwbPro" name="blAwbPro" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchBlAwbPro())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo"> 
                                                                    <a class="text-lg text-info" onclick="customForm('9')"><i class="fa fa-search"></i></a>
                                                                </div>    
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="2">LoadType</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="loadType" name="loadType" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchLoadType())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo"> 
                                                                    <a class="text-lg text-info" onclick="customForm('10')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="2">Quantity</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="quantity" name="quantity" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchQuantity())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo"> 
                                                                    <a class="text-lg text-info" onclick="customForm('11')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="2">POD</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="pod" name="pod" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchPod())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo"> 
                                                                    <a class="text-lg text-info" onclick="customForm('12')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="1">Est. Departure from POL</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="departurePol" name="departurePol" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchDepartureFromPOL())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo"> 
                                                                    <a class="text-lg text-info" onclick="customForm('13')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="1">ETA REAL Port of Discharge</font></center>
                                                            <div id="buscador"> 
                                                               <div id="primero"> 
                                                                    <select class="selectpicker" id="realPortDischarge" name="realPortDischarge" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchRealPortDischarge())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                               </div>
                                                               <div id="segundo"> 
                                                                    <a class="text-lg text-info" onclick="customForm('14')"><i class="fa fa-search"></i></a>
                                                               </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="2">Est. Eta DC</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="etaDc" name="etaDc" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchEtaDC())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo"> 
                                                                    <a class="text-lg text-info" onclick="customForm('15')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="1">Inbound notification</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="inbNotification" name="inbNotification" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchInboundNotificacion())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo"> 
                                                                    <a class="text-lg text-info" onclick="customForm('16')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="2">POL</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="pol" name="pol" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchPol())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo"> 
                                                                    <a class="text-lg text-info" onclick="customForm('17')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="2">A.A.</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="agenteAduanal" name="agenteAduanal" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchAA())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo"> 
                                                                    <a class="text-lg text-info" onclick="customForm('18')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="2">Fecha Mes de Venta</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero"> 
                                                                    <select class="selectpicker" id="fechaVenta" name="fechaVenta" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchFechaMesVenta())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div id="segundo"> 
                                                                    <a class="text-lg text-info" onclick="customForm('19')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="text-align:left; background-color:#8BC4C4;">
                                                            <center><font size="2">Prioridad Si/No</font></center>
                                                            <div id="buscador"> 
                                                                <div id="primero">                                                  
                                                                    <select class="selectpicker" id="prioridad" name="prioridad" multiple aria-label="Seleccione">
                                                                        <%
                                                                            if (db.doDB(fac.searchEventosPrioritarios())) {
                                                                                for (String[] row : db.getResultado()) {
                                                                                    out.println("<option value=\"" + row[0] + "\" >" + row[0] + "</option>");
                                                                                }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div> 
                                                                <div id="segundo">    
                                                                    <a class="text-lg text-info" onclick="customForm('20')"><i class="fa fa-search"></i></a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"> </th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">País Origen</font></th>	
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Size Container</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Valor USD</font></th>	
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">ETA Port Of Discharge</font></th>	
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Agente Aduanal</font></th>	
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Pedimento A1</font></th>	
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Pedimento R1</font></th>	
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Motivo rectificación 1</font></th>	
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Pedimento R1 (2do)</font></th>	
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Motivo rectificación 2</font></th>	
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Recepción Documentos</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#FF4040"><font size="2">Recinto</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#FF4040"><font size="2">Naviera / Forwarder</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#FF4040"><font size="2">Buque</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Revalidación/Liberación de BL</font></th>	
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Previo Origen</font></th>	
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Previo en destino</font></th>	
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Resultado Previo</font></th>	
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Proforma Final</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Requiere permiso</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha envío Fichas/notas</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fec. Recepción de permisos tramit.</font></th>	
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fec. Act Permisos (Inic Vigencia)</font></th>	
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fec. Perm. Aut. (Fin de Vigencia)</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Cuenta con CO para aplicar preferencia Arancelaria</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Aplico Preferencia Arancelaria</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Requiere UVA</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#626567"><font size="2">Requiere CA</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#626567"><font size="2">Fecha Recepción CA</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#626567"><font size="2">Número de Constancia CA</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#626567"><font size="2">Monto CA</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Documentos Completos</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Pago Pedimento</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Solicitud de transporte</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Modulacion</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Modalidad</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Resultado Modulacion</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Reconocimiento</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Liberacion</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Sello Origen</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Sello Final</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha de retencion por la autoridad</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fec. de liberacion por ret. de la aut.</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Estatus de la operación</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Motivo Atraso</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#00BFBF"><font size="2">Observaciones</font></th>
                                                    <%
                                                        if(tipoAgente.equals("4001")){        //Logix
                                                    %>    
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Llegada a NOVA</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Llegada a Globe trade SD</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Archivo M</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Fecha de Archivo M</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Fecha Solicitud de Manipulacion</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Fecha de vencimiento de Manipulacion</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Fecha confirmacion Clave de Pedimento</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Fecha de Recepcion de Incrementables</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">T&E</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Fecha de Vencimiento del Inbound</font></th>
                                                    <%
                                                        }else if(tipoAgente.equals("4002")){  //Cusa
                                                    %>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">No. BULTOS</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Peso (KG)</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Transferencia (SI / NO)</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Fecha Inicio Etiquetado</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Fecha Termino Etiquetado</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Hora de termino Etiquetado</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Proveedor</font></th>
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Proveedor de Carga</font></th>
                                                    <%
                                                        }
                                                    %> 
                                                        <th scope="col" class="font-titulo" style="background-color:#8BC4C4"><font size="2">FY</font></th>
                                                    </tr>
                                                </thead>
                                                <tbody id="detalleCustom"></tbody>
                                            </table>
                                        </div>
                                        <br>
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
                                                                <label for="input-id" class="form-label">Selecciona </center></label
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
        <!-- JavaScript files-->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Multiselect -->
        <script src="../lib/Multiselect/js/bootstrap-select.min.js" type="text/javascript"></script>
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
