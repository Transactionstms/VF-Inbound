<%-- 
    Document   : Reporte_DetalleEvidencia
    Created on : 22/06/2023, 11:07:10 AM
    Author     : luis_ 
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
        <title>Modificar Eventos Nuevos</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">
        <!-- Google fonts - Popppins for copy-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&amp;display=swap" rel="stylesheet">
        <!-- Prism Syntax Highlighting-->
        <link rel="stylesheet" href="../../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.css">
        <link rel="stylesheet" href="../../lib/vendor/prismjs/themes/prism-okaidia.css">
        <!-- The Main Theme stylesheet (Contains also Bootstrap CSS)-->
        <link rel="stylesheet" href="../../lib/css/style.default.css" id="theme-stylesheet">
        <!-- Custom stylesheet - for your changes-->
        <link rel="stylesheet" href="../../lib/css/custom.css">
        <!-- Favicon-->
        <link rel="shortcut icon" href="../../lib/img/favicon.png">
        <!-- Table css -->
        <link href="../../lib/inbound/eventos/styleEvents.css" rel="stylesheet" type="text/css"/>
        <style>
          .centrar {
            text-align: center;
          }
        </style>
     </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                ConsultasQuery fac = new ConsultasQuery();
                
                String fec1 = request.getParameter("fechaInicial");
                String fec2 = request.getParameter("fechaFinal");
                

String   sql = " SELECT DISTINCT "   
            + " TIE.EMBARQUE_ID, "   
            + " TO_CHAR(TIE.EMBARQUE_FEC_CAPTURA, 'DD/MM/YYYY'), " 
            + " UPPER(TILT.LTRANSPORTE_NOMBRE), "
            + " UPPER(OUT.UTRANSPORTE_DESC), " 
            + " UPPER(TIE.CHOFER_ID), "
            + " TIE.EMBARQUE_AGRUPADOR, "
            + " NVL(TIGT.URL_POD,'0'), "
            + " NVL(TIE.URL_CFDI,'0'), "
            + " TIGT.SHIPMENT_ID "
            + " FROM TRA_INB_EMBARQUE  TIE "   
            + " INNER JOIN TRA_INC_GTN_TEST TIGT ON TIE.EMBARQUE_AGRUPADOR = TIGT.EMBARQUE_AGRUPADOR "
            + " INNER JOIN TRA_INB_LINEA_TRANSPORTE TILT ON TIE.EMBARQUE_TRANSPORTISTA  = TILT.LTRANSPORTE_ID "   
            + " INNER JOIN ONTMS_UNIDAD_TRANSPORTE OUT ON TIE.UTRANSPORTE_ID = OUT.UTRANSPORTE_ID  "
            + " AND TO_DATE(trunc(TIE.EMBARQUE_FEC_CAPTURA),'dd/mm/yy') >= TO_DATE('" + fec1 + "','dd/mm/yyyy') "
            + " AND TO_DATE(trunc(TIE.EMBARQUE_FEC_CAPTURA),'dd/mm/yy') <= TO_DATE('" + fec2 + "','dd/mm/yyyy') "
            + " AND TIE.EMBARQUE_ESTADO_ID IN (1,4) "
            + " ORDER BY TIE.EMBARQUE_ID ASC ";
        %>
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
                                            <div class="col-md-12">
                                                <h2 class="card-heading">Detalle Evidenciar Embarque</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <form id="uploadFileFormData" name="uploadFileFormData">
                                            <div class="row">
                                                <div align="right">
                                                    <div id="example_filter" class="dataTables_filter">
                                                        <label>
                                                            Busqueda:
                                                            <input id="searchTerm" type="text" onkeyup="doSearch()" autocomplete="off"/>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <br>
                                            <div id="table-scroll" class="table-scroll"  style="height: 650px;">
                                                <table id="main-table" class="main-table" style="table-layout:fixed; width:80%;">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col" class="font-titulo">EMBARQUE</th>
                                                            <th scope="col" class="font-titulo">SHIPMENT ID</th>
                                                            <th scope="col" class="font-titulo">FECHA CAPTURA</th>
                                                            <th scope="col" class="font-titulo">TRANSPORTISTA</th>
                                                            <th scope="col" class="font-titulo">CAMIÓN</th>
                                                            <th scope="col" class="font-titulo">CHOFER</th>
                                                            <th scope="col" class="font-titulo">URL POD</th>
                                                            <th scope="col" class="font-titulo">URL CFDI</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <%   
                                                        if (db.doDB(sql)) {
                                                            for (String[] row : db.getResultado()) {       
                                                    %>
                                                        <tr> 
                                                            <th class="centrar"> <%=row[0]%></th>    <!-- EMBARQUE -->
                                                            <td class="centrar"> <%=row[8]%></td>    <!-- SHIPMENT ID -->
                                                            <td class="centrar"> <%=row[1]%></td>    <!-- FECHA CAPTURA -->
                                                            <td class="font-texto"> <%=row[2]%></td> <!-- TRANSPORTISTA -->
                                                            <td class="font-texto"> <%=row[3]%></td> <!-- CAMIÓN -->
                                                            <td class="font-texto"> <%=row[4]%></td> <!-- CHOFER -->
                                                            <td class="centrar">                     <!-- URL POD -->
                                                                <% if (!row[6].equals("0")) {%><b><img src="../../img/pdfflat.png" width="15%" style="cursor: pointer"  onclick="viewEvidenciaPOD('<%=row[8]%>')"/></b><% } %>
                                                            </td>
                                                            <td class="centrar">                     <!-- URL CFDI -->
                                                                <% if (!row[7].equals("0")) {%><b><img src="../../img/pdfflat.png" width="15%" style="cursor: pointer"  onclick="viewEvidenciaCFDI('<%=row[5]%>')"/></b><% } %>
                                                            </td>
                                                        </tr>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                              </div>
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
        <script>
            function viewEvidenciaPOD(shipmentId) {
                window.open("<%=request.getContextPath()%>/MostrarPDF_EvidenciarEmbarque?shipmentId="+shipmentId, "Visor Evidencia POD", "toolbar=yes,scrollbars=yes,resizable=yes,top=50,left=50,width=700,height=500");
            }
            
            function viewEvidenciaCFDI(embarqueAgrupador) {
                window.open("<%=request.getContextPath()%>/MostrarPDF_EvidenciaCFDI?embarqueAgrupador="+embarqueAgrupador, "Visor Evidencia CFDI", "toolbar=yes,scrollbars=yes,resizable=yes,top=50,left=50,width=700,height=500");
            }
            
            function doSearch() {
                var tableReg = document.getElementById('main-table');
                var searchText = document.getElementById('searchTerm').value.toLowerCase();
                var cellsOfRow = "";
                var found = false;
                var compareWith = "";

                // Recorremos todas las filas con contenido de la tabla
                for (var i = 1; i < tableReg.rows.length; i++)
                {
                    cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
                    found = false;
                    // Recorremos todas las celdas
                    for (var j = 0; j < cellsOfRow.length && !found; j++)
                    {
                        compareWith = cellsOfRow[j].innerHTML.toLowerCase();
                        // Buscamos el texto en el contenido de la celda
                        if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
                        {
                            found = true;
                        }
                    }
                    if (found)
                    {
                        tableReg.rows[i].style.display = '';
                    } else {
                        // si no ha encontrado ninguna coincidencia, esconde la
                        // fila de la tabla
                        tableReg.rows[i].style.display = 'none';
                    }
                }
            }
        </script>
        <!-- JavaScript files-->
        <script src="../../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
        <!-- Main Theme JS File-->
        <script src="../../lib/js/theme.js"></script>
        <!-- Prism for syntax highlighting-->
        <script src="../../lib/vendor/prismjs/prism.js"></script>
        <script src="../../lib/vendor/prismjs/plugins/normalize-whitespace/prism-normalize-whitespace.min.js"></script>
        <script src="../../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.min.js"></script>
        <script src="../../lib/vendor/prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard.min.js"></script>
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
