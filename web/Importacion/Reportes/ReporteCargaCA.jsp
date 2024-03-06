<%-- 
    Document   : ReporteCargaCA
    Created on : 6 mar 2024, 11:41:41
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

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Reporte Carga CA</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">
        <!-- Google fonts - Popppins for copy-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&amp;display=swap" rel="stylesheet">
        <!-- Prism Syntax Highlighting-->
        <!-- The Main Theme stylesheet (Contains also Bootstrap CSS)-->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/css/style.default.css" id="theme-stylesheet">
        <!-- Table css -->
        <link href="<%=request.getContextPath()%>/lib/inbound/eventos/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- Connection Status Red -->
        <link href="<%=request.getContextPath()%>/lib/inbound/conexion/connectionStatus.css" rel="stylesheet" type="text/css"/>
        <!-- table -->
        <link href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));

                String carga_ca = " SELECT DISTINCT "
                        + " REG_ID, "
                        + " NVL(EVENTO,0), "
                        + " NVL(PEDIMENTO_A1,' '), "
                        + " NVL(IMPUESTOS_EFECTIVOS,0), "
                        + " NVL(DIFERENCIA_CONTRIBUCIONES,0), "
                        + " NVL(IMPORTE_CUENTA_ADUANERA,0), "
                        + " NVL(MARCA,' '), "
                        + " NVL(TO_CHAR(FECHA_SOLICITUD,'DD/MM/YYYY'),' '), "
                        + " NVL(DIFERENCIA,0), "
                        + " NVL(CONSTANCIA,' '), "
                        + " NVL(TO_CHAR(FECHA_VENCIMIENTO,'DD/MM/YYYY'),' '), "
                        + " NVL(COMENTARIO,' '), "
                        + " NVL(TO_CHAR(CANCELACION,'DD/MM/YYYY'),' '), "
                        + " NVL(TO_CHAR(RECUPERACION,'DD/MM/YYYY'),' '), "
                        + " NVL(TO_CHAR(FECHA_REGISTRO,'DD/MM/YYYY'),' ') "
                        + " FROM TRA_CARGA_CA "
                        + " ORDER BY REG_ID ASC ";
        %>
        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <section>
                        <div class="row">
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card ">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h2 class="card-heading">Reporte Carga CA</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="tab-content" id="myTabContent">
                                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                                <form id="uploadFileFormData1" name="uploadFileFormData1">
                                                    <br>
                                                    <div id="table-scroll" class="table-scroll"  style="height: 100%;">
                                                        <table id="example" class="display" style="width:300%">
                                                            <thead>
                                                                <tr>
                                                                    <th scope="col" class="font-titulo">EVENTO</th>
                                                                    <th scope="col" class="font-titulo">PEDIMENTO A1</th>
                                                                    <th scope="col" class="font-titulo">IMPUESTOS EFECTIVOS</th>
                                                                    <th scope="col" class="font-titulo">DIFERENCIA CONTRIBUCIONES</th>
                                                                    <th scope="col" class="font-titulo">IMPORTE CUENTA ADUANERA</th>
                                                                    <th scope="col" class="font-titulo">MARCA</th>
                                                                    <th scope="col" class="font-titulo">FECHA SOLICITUD</th>
                                                                    <th scope="col" class="font-titulo">DIFERENCIA</th>
                                                                    <th scope="col" class="font-titulo">CONSTANCIA</th>
                                                                    <th scope="col" class="font-titulo">FECHA VENCIMIENTO</th>
                                                                    <th scope="col" class="font-titulo">COMENTARIO</th>
                                                                    <th scope="col" class="font-titulo">CANCELACION</th>
                                                                    <th scope="col" class="font-titulo">RECUPERACION</th> 
                                                                    <th scope="col" class="font-titulo">FECHA REGISTRO</th> 
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <%
                                                                    if (db.doDB(carga_ca)) {
                                                                        for (String[] row : db.getResultado()) {
                                                                %>
                                                                <tr  >
                                                                    <td class="font-texto"> <%=row[1]%></td>
                                                                    <td class="font-texto"> <%=row[2]%></td>
                                                                    <td class="font-texto"> <%=row[3]%></td>
                                                                    <td class="font-texto"> <%=row[4]%></td>
                                                                    <td class="font-texto"> <%=row[5]%></td>	
                                                                    <td class="font-texto"> <%=row[6]%></td>	
                                                                    <td class="font-texto"> <%=row[7]%></td>
                                                                    <td class="font-texto"> <%=row[8]%> </td>		
                                                                    <td class="font-texto"> <%=row[9]%></td>	
                                                                    <td class="font-texto"> <%=row[10]%></td>
                                                                    <td class="font-texto"> <%=row[11]%></td>	
                                                                    <td class="font-texto"> <%=row[12]%></td>	
                                                                    <td class="font-texto"> <%=row[13]%></td>
                                                                    <td class="font-texto"> <%=row[14]%></td>
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
                            </div>
                        </div>   
                    </section>
                </div> 
            </div>
        </div>    

        <!-- Conexión estatus red -->                    
        <script src="<%=request.getContextPath()%>/lib/inbound/conexion/connectionStatus.js" type="text/javascript"></script>
        <!-- JavaScript files-->
        <script src="<%=request.getContextPath()%>/lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
        <!-- Main Theme JS File-->
        <script src="<%=request.getContextPath()%>/lib/js/theme.js"></script>
        <!-- Prism for syntax highlighting-->
        <script src="<%=request.getContextPath()%>/lib/vendor/prismjs/prism.js"></script>
        <script src="<%=request.getContextPath()%>/lib/vendor/prismjs/plugins/normalize-whitespace/prism-normalize-whitespace.min.js"></script>
        <script src="<%=request.getContextPath()%>/lib/vendor/prismjs/plugins/toolbar/prism-toolbar.min.js"></script>
        <script src="<%=request.getContextPath()%>/lib/vendor/prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard.min.js"></script>
        <!-- actions js -->
        <script src="<%=request.getContextPath()%>/lib/inbound/eventos/functionsEvents.js" type="text/javascript"></script>
        <!-- sweetalert -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>


        <script src='https://code.jquery.com/jquery-3.5.1.js'></script>
        <script src='https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js'></script>
        <script src='https://cdn.datatables.net/buttons/2.3.6/js/dataTables.buttons.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js'></script>
        <script src='https://cdn.datatables.net/buttons/2.3.6/js/buttons.html5.min.js'></script>
        <script src='https://cdn.datatables.net/buttons/2.3.6/js/buttons.print.min.js'></script>


        <script type="text/javascript">
            // Optional
            Prism.plugins.NormalizeWhitespace.setDefaults({
                'remove-trailing': true,
                'remove-indent': true,
                'left-trim': true,
                'right-trim': true,
            });

            $(document).ready(function () {
                $('#example').DataTable({
                    dom: 'lBfrtip', // Incluye el elemento de paginación
                    buttons: [
                        {
                            extend: 'copy',
                            text: 'Copiar'
                        },
                        {
                            extend: 'csv',
                            text: 'CSV'
                        },
                        {
                            extend: 'excel',
                            text: 'Excel'
                        }
                    ],
                    lengthMenu: [10, 15, 25, 50, 100], // Define las opciones de "Mostrar entradas"
                    pageLength: 10 // Establece el número predeterminado de entradas por página
                });
            });

            $(document).ready(function () {
                $('#example2').DataTable({
                    dom: 'lBfrtip', // Incluye el elemento de paginación
                    buttons: [
                        {
                            extend: 'copy',
                            text: 'Copiar'
                        },
                        {
                            extend: 'csv',
                            text: 'CSV'
                        },
                        {
                            extend: 'excel',
                            text: 'Excel'
                        }
                    ],
                    lengthMenu: [10, 15, 25, 50, 100], // Define las opciones de "Mostrar entradas"
                    pageLength: 10 // Establece el número predeterminado de entradas por página
                });
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


