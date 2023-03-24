<%-- 
    Document   : ReporteCustom
    Created on : 15/03/2023, 09:02:51 AM
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
        <title>Reporte Custom</title>
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
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- Connection Status Red -->
        <link href="../../lib/inbound/conexion/connectionStatus.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                
                String sql =  " SELECT DISTINCT "
                            + " EVENTO, "  
                            + " PAIS_ORIGEN, "  
                            + " SIZE_CONTAINER, "  
                            + " VALOR_USD, "                
                            + " ETA_PORT_DISCHARGE, "        
                            + " AGENTE_ADUANAL, "            
                            + " PEDIMENTO_A1, "              
                            + " PEDIMENTO_R1_1ER, "          
                            + " MOTIVO_RECTIFICACION_1ER, "  
                            + " PEDIMENTO_R1_2DO, "          
                            + " MOTIVO_RECTIFICACION_2DO, "  
                            + " FECHA_RECEPCION_DOC, "    
                            + " RECINTO, "    
                            + " NAVIERA, "    
                            + " BUQUE, "   
                            + " FECHA_REVALIDACION, "        
                            + " FECHA_PREVIO_ORIGEN, "       
                            + " FECHA_PREVIO_DESTINO, "      
                            + " FECHA_RESULTADO_PREVIO, "    
                            + " PROFORMA_FINAL, "            
                            + " PERMISO, "                   
                            + " FECHA_ENVIO, "               
                            + " FECHA_RECEPCION_PERM, "      
                            + " FECHA_ACTIVACION_PERM, "     
                            + " FECHA_PERMISOS_AUT, "        
                            + " CO_PREF_ARANCELARIA, "       
                            + " APLIC_PREF_ARANCELARIA, "    
                            + " REQ_UVA, "    
                            + " REQ_CA, "  
                            + " FECHA_RECEPCION_CA, "  
                            + " NUM_CONSTANCIA_CA, "  
                            + " MONTO_CA, "  
                            + " FECHA_DOC_COMPLETOS, "       
                            + " FECHA_PAGO_PEDIMENTO, "      
                            + " FECHA_SOLICITUD_TRANSPORTE, "
                            + " FECHA_MODULACION, "          
                            + " MODALIDAD, "                 
                            + " RESULTADO_MODULACION, "      
                            + " FECHA_RECONOCIMIENTO, "      
                            + " FECHA_LIBERACION, "         
                            + " SELLO_ORIGEN, "              
                            + " SELLO_FINAL, "               
                            + " FECHA_RETENCION_AUT, "       
                            + " FECHA_LIBERACION_AUT, "      
                            + " ESTATUS_OPERACION, "         
                            + " MOTIVO_ATRASO, "             
                            + " OBSERVACIONES, "             
                            + " FECHA_REGISTRO, "            
                            + " ESTATUS_ID, "                
                            + " CBDIV_ID "
                            + " FROM TRA_CUSTOMS";
        %>
        <!-- navbar-->
        <header class="header">
        </header>
        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <!--<div class="unwired alert alert-danger">¡Se ha perdido su conexión! TMS debe de estar conectado a Internet para su correcto funcionamiento.</div>-->
                    <section>
                        <div class="row">
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card h-100">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h2 class="card-heading">Reporte Custom</h2>
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
                                              <div id="table-scroll" class="table-scroll">
                                                <table id="main-table" class="main-table" style="table-layout:fixed; width:800%;">
                                                   <thead>
                                                        <tr>
                                                            <th scope="col" class="font-titulo">Número de evento <strong style="color:red">*</strong></th>
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
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%     
                                                            if (db.doDB(sql)) {
                                                                for (String[] row : db.getResultado()) {   
                                                        %>
                                                        <tr> 
                                                            <th class="font-texto"> <%=row[0]%></th>
                                                            <td class="font-texto"> <%=row[1]%></td>
                                                            <td class="font-texto"> <%=row[2]%></td>
                                                            <td class="font-texto"> <%=row[3]%></td>
                                                            <td class="font-texto"> <%=row[4]%></td>
                                                            <td class="font-texto"> <%=row[5]%></td>	
                                                            <td class="font-texto"> <%=row[6]%></td>	
                                                            <td class="font-texto"> <%=row[7]%></td>
                                                            <td class="font-texto"> <%=row[8]%></td>		
                                                            <td class="font-texto"> <%=row[9]%></td>	
                                                            <td class="font-texto"> <%=row[10]%></td>
                                                            <td class="font-texto"> <%=row[11]%></td>	
                                                            <td class="font-texto"> <%=row[12]%></td> 
                                                            <td class="font-texto"> <%=row[13]%></td> 
                                                            <td class="font-texto"> <%=row[14]%></td>
                                                            <td class="font-texto"> <%=row[15]%></td>	
                                                            <td class="font-texto"> <%=row[16]%></td>
                                                            <td class="font-texto"> <%=row[17]%></td>
                                                            <td class="font-texto"> <%=row[18]%></td>
                                                            <td class="font-texto"> <%=row[19]%></td>
                                                            <td class="font-texto"> <%=row[20]%></td>
                                                            <td class="font-texto"> <%=row[21]%></td>
                                                            <td class="font-texto"> <%=row[22]%></td>
                                                            <td class="font-texto"> <%=row[23]%></td>
                                                            <td class="font-texto"> <%=row[24]%></td>
                                                            <td class="font-texto"> <%=row[25]%></td>
                                                            <td class="font-texto"> <%=row[26]%></td>
                                                            <td class="font-texto"> <%=row[27]%></td>
                                                            <td class="font-texto"> <%=row[28]%></td>
                                                            <td class="font-texto"> <%=row[29]%></td>
                                                            <td class="font-texto"> <%=row[30]%></td>
                                                            <td class="font-texto"> <%=row[31]%></td>
                                                            <td class="font-texto"> <%=row[32]%></td>
                                                            <td class="font-texto"> <%=row[33]%></td>
                                                            <td class="font-texto"> <%=row[34]%></td>
                                                            <td class="font-texto"> <%=row[35]%></td>
                                                            <td class="font-texto"> <%=row[36]%></td>
                                                            <td class="font-texto"> <%=row[37]%></td>
                                                            <td class="font-texto"> <%=row[38]%></td>
                                                            <td class="font-texto"> <%=row[39]%></td>
                                                            <td class="font-texto"> <%=row[40]%></td>
                                                            <td class="font-texto"> <%=row[41]%></td>
                                                            <td class="font-texto"> <%=row[42]%></td>
                                                            <td class="font-texto"> <%=row[43]%></td>
                                                            <td class="font-texto"> <%=row[44]%></td>
                                                            <td class="font-texto"> <%=row[45]%></td>
                                                            <td class="font-texto"> <%=row[46]%></td>
                                                        </tr>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </tbody>
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
        <script>
            function cambiarResponsable(id) {
                console.log(id);
            }
            
            function editarEvento(id) {
                console.log('editar');//
                console.log(id);
                window.location.href = '<%=request.getContextPath()%>/Importacion/gtnEventoEdit.jsp?id=' + id;
            }
        </script>                     
        <!-- Conexión estatus red -->                    
        <script src="../../lib/inbound/conexion/connectionStatus.js" type="text/javascript"></script>
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
        <!-- actions js -->
        <script src="../../lib/inbound/eventos/functionsEvents.js" type="text/javascript"></script>
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