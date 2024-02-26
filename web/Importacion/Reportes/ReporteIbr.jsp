<%-- 
    Document   : ReporteIbr
    Created on : 22-feb-2024, 17:51:25
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
        <!-- The Main Theme stylesheet (Contains also Bootstrap CSS)-->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/css/style.default.css" id="theme-stylesheet">
        <!-- Custom stylesheet - for your changes-->
        <!-- Favicon-->
        <!-- Table css -->
        <link href="<%=request.getContextPath()%>/lib/inbound/eventos/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- Connection Status Red -->
        <link href="<%=request.getContextPath()%>/lib/inbound/conexion/connectionStatus.css" rel="stylesheet" type="text/css"/>

        <link href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String UserId = (String) ownsession.getAttribute("login.user_id_number");
                ConsultasQuery fac = new ConsultasQuery();
                String agenteAduanal = "";
                String coma = ",";
                String aa = "";
                String colors = "";

                if (db.doDB(fac.consultarAAEventosDetalle(UserId))) {
                    for (String[] rowA : db.getResultado()) {
                        aa = rowA[0];
                        aa = aa + coma;
                        agenteAduanal = agenteAduanal + aa;
                    }
                    agenteAduanal = "4006," + agenteAduanal.replaceAll(",$", "");
                }

                String sql2 = "  select "
                        + "        evento,"
                        + "        contenedor,"
                        + "        bl,"
                        + "        shipment,"
                        + "        load_type,"
                        + "        lum_brio,"
                        + "        brand,"
                        + "        sbu_name,"
                        + "        pol,"
                        + "        country,"
                        + "        actual_crd,"
                        + "        departure_date,"
                        + "        mx_port,"
                        + "        eta_mx_port,"
                        + "        eta_dc,"
                        + "        indc_a,"
                        + "        arribo_real_a_dc,"
                        + "        nvl(comentarios,' '),"
                        + "        requiere_etiquetado,"
                        + "        nvl(a123,' '),"
                        + "        nvl(b456,' ')"
                        + "        from TRA_INB_IBR where tipo= 1005 ";

                String sql3 = "select"
                        + "  EVENTO,"
                        + " Contenedor,"
                        + " Shipment,"
                        + " Load_Type,"
                        + " LUM_BRIO,"
                        + " Brand,"
                        + " Sbu_Name,"
                        + " nvl(Departure_Port,' '),"
                        + " Departure_Date,"
                        + " MX_Port,"
                        + " ETA_MX_Port,"
                        + " nvl(ETA_Customer,' '),"
                        + " nvl(Comentarios,' '),"
                        + " nvl(Customer,' '),"
                        + " nvl(ATA_CMI,' '),"
                        + " nvl(ETA_CUSTOMER2,' ')"
                        + "        from TRA_INB_IBR where tipo= 1013";

                int cont = 0;
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
                                                <h2 class="card-heading"> Reporte IBR  </h2>
                                            </div>
                                        </div>
                                    </div>
 





                                    <div class="card-body">



                                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                                            <li class="nav-item" role="presentation">
                                                <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home" type="button" role="tab" aria-controls="home" aria-selected="true">IBR 1005  VF</button>
                                            </li>
                                            <li class="nav-item" role="presentation">
                                                <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" type="button" role="tab" aria-controls="profile" aria-selected="false">IBR 1013  VF</button>
                                            </li>

                                        </ul>
                                        <div class="tab-content" id="myTabContent">
                                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                                <form id="uploadFileFormData1" name="uploadFileFormData1">
                                                    <br>
                                                    <div id="table-scroll" class="table-scroll"  style="height: 100%;">
                                                        <table id="example" class="display" style="width:300%">
                                                            <thead>
                                                                <tr>
                                                                <tr>
                                                                    <th scope="col" class="font-titulo">EVENTO</th>
                                                                    <th scope="col" class="font-titulo">CONTENEDOR</th>
                                                                    <th scope="col" class="font-titulo">BL</th>
                                                                    <th scope="col" class="font-titulo">SHIPMENT</th>
                                                                    <th scope="col" class="font-titulo">LOAD_TYPE</th>
                                                                    
                                                                    <th scope="col" class="font-titulo">LUM_BRIO</th>
                                                                    <th scope="col" class="font-titulo">BRAND</th>
                                                                    <th scope="col" class="font-titulo">SBU_NAME</th>
                                                                    <th scope="col" class="font-titulo">POL</th>
                                                                    <th scope="col" class="font-titulo">COUNTRY</th>
                                                                    
                                                                    <th scope="col" class="font-titulo">ACTUAL_CRD</th>
                                                                    <th scope="col" class="font-titulo">DEPARTURE_DATE</th>
                                                                    <th scope="col" class="font-titulo">MX_PORT</th> 
                                                                    <th scope="col" class="font-titulo">ETA_MX_PORT</th> 
                                                                    <th scope="col" class="font-titulo">ETA DC</th>
                                                                    
                                                                    <th scope="col" class="font-titulo">INDC + 2 Days PUT AWAY</th>
                                                                    <th scope="col" class="font-titulo">Arribo real a DC</th>
                                                                    <th scope="col" class="font-titulo">COMENTARIOS</th> 
                                                                    <th scope="col" class="font-titulo">Requiere etiquetado</th> 
                                                                    <th scope="col" class="font-titulo">123</th>
                                                                    
                                                                    <th scope="col" class="font-titulo">456</th>
                                                                </tr>         
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <%
                                                                    if (db.doDB(sql2)) {
                                                                        for (String[] row : db.getResultado()) {

                                                                %>
                                                                <tr  >
                                                                    <td class="font-texto"> <%=row[0]%></td>
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
                                                                    <td class="font-texto"> <%= row[14]%></td>
                                                                    <td class="font-texto"> <%=row[15]%></td>
                                                                    <td class="font-texto"> <%=row[16]%></td>
                                                                    <td class="font-texto"> <%=row[17]%></td>
                                                                    <td class="font-texto"> <%=row[18]%></td>
                                                                    <td class="font-texto"> <%=row[19]%></td>
                                                                    <td class="font-texto"> <%=row[20]%></td>
                                                                </tr>
                                                                <%
                                                                        }
                                                                    }
                                                                %>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <br>
                                                    <!-- Botones controles -->

                                                </form>

                                            </div>

                                            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">

                                                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                                    <form id="uploadFileFormData1" name="uploadFileFormData1">
                                                        <br>
                                                        <div id="table-scroll" class="table-scroll"  style="height: 100%;">
                                                            <table id="example2" class="display" style="width:300%">
                                                                <thead>
                                                                    <tr>
                                                                        <th scope="col" class="font-titulo">EVENTO</th>

                                                                        <th scope="col" class="font-titulo">CONTENEDOR</th> 
                                                                        <th scope="col" class="font-titulo">SHIPMENT</th>
                                                                        <th scope="col" class="font-titulo">LOAD_TYPE</th>
                                                                        <th scope="col" class="font-titulo">LUM_BRIO</th>
                                                                        <th scope="col" class="font-titulo">BRAND</th>
                                                                        
                                                                        <th scope="col" class="font-titulo">SBU_NAME</th>
                                                                        <th scope="col" class="font-titulo">Departure Port</th>
                                                                        <th scope="col" class="font-titulo">Departure Date</th>
                                                                        <th scope="col" class="font-titulo">MX_PORT</th>
                                                                        <th scope="col" class="font-titulo">ETA_MX_PORT</th>
                                                                        
                                                                        <th scope="col" class="font-titulo">ETA Customer</th>
                                                                        <th scope="col" class="font-titulo">Comentarios</th>
                                                                        <th scope="col" class="font-titulo">Customer</th> 
                                                                        <th scope="col" class="font-titulo">ATA CMI</th>
                                                                        <th scope="col" class="font-titulo">ETA CUSTOMER</th>
   
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <%
                                                                        if (db.doDB(sql3)) {
                                                                            for (String[] row : db.getResultado()) {

                                                                    %>
                                                                    <tr  >
                                                                        <td class="font-texto"> <%=row[0]%></td>
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
                                                                        <td class="font-texto"> <%=row[15]%></td> 

                                                                         

                                                                    </tr>
                                                                    <%
                                                                            }
                                                                        }
                                                                    %>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <br>
                                                        <!-- Botones controles -->

                                                    </form>

                                                </div>



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


