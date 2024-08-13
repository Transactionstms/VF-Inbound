<%-- 
    Document   : Reporte_RDI
    Created on : 10-oct-2023, 20:33:31
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

                String sql2 = "   SELECT "
                        + "            evento,"
                        + "            contenedor,"
                        + "            bl,"
                        + "           CARRIER,"
                        + "            shipment,"
                        + "            load_type,"
                        + " CASE"
        + "   WHEN REGEXP_LIKE(LUM_BRIO, '^[0-9]+$') THEN TO_CHAR(TO_NUMBER(LUM_BRIO), 'FM999G999G999G999') "
        + "   ELSE 'Datos no numéricos' "
        + "  END AS monto_formateado , " 
                        + "            brand,"
                        + "            sbu_name,"
                        + "            pol,"
                        + "            country,"
                        + "            actual_crd,"
                        + "            departure_date,"
                        + "            mx_port,"
                        + "            ATA_CMI,"
                        + "            eta_mx_port,"
                        + "            eta_customer,"
                        + "            Estatus,"
                        + "            nvl(comentarios,' '),"
                        //+"            final_dest,"
                        //
                        //

                        + "            NVL(INBOUND_NOT,' '), " //row[20]
                        + "            NVL(AA,' '), "
                        + "            responsable,"
                        + "            NVL(PLANTA,' '), "
                        + "            NVL(LT2,' '), "
                        + "            NVL(TT,' '), "
                        + "            CUSTOMER, "
                        + "            NVL(NUM_PEDIMENTO,' '), "
                        + "            NVL(MES_FACTURACION,' ') "
                        // +"            NVL(FREE_TIME,' '), "
                        // +"            NVL(TODAY,' '), "
                        // +"            NVL(FREE_UNTIL,' '  ) "  //row[29]

                        + "            FROM"
                        + " TRA_INB_RDI WHERE tipo=1013";

                String sql3 = " SELECT  "
                        + "            evento, "
                        + "            contenedor, "
                        + "            bl, "
                        + "            carrier, "
                        + "            shipment, "
                        + "            load_type, "
                        + "            VESSEL, "
                        + "            CONTAINER_TYPE," 
                       + " CASE"
        + "   WHEN REGEXP_LIKE(LUM_BRIO, '^[0-9]+$') THEN TO_CHAR(TO_NUMBER(LUM_BRIO), 'FM999G999G999G999') "
        + "   ELSE 'Datos no numéricos' "
        + "  END AS monto_formateado , " 
                        + "            brand, "
                        + "            sbu_name, "
                        + "            pol, "
                        + "            country, "
                        + "            actual_crd, "
                        + "            departure_date, "
                        + "            mx_port, "
                        + "            eta_mx_port, "
                        + "            eta_dc, "
                        + "            indc_2_days_put_away, "
                        + "            arribo_real_dc, "
                        + "            nvl(comentarios,' '), "
                        + "            inbound_not, "
                        + "            aa, "
                        + "            responsable, "
                        + "            planta, " //row[25]
                        + "            NVL(TOTAL_LANE,' '), "
                        + "            NVL(LT2_TARGET,' '), "
                        + "            NVL(ACTUAL_LT2,' '), "
                        + "            NVL(LT2_CALIBRATION,' '), "
                        + "            NVL(WORKDAYS,' '), "
                        + "            NVL(OCEAN_TT,' ')  " 
                        + "            FROM "
                        + " TRA_INB_RDI WHERE tipo=1005";

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
                                                <h2 class="card-heading"> Reporte RDI  </h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                                            <li class="nav-item" role="presentation">
                                                <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home" type="button" role="tab" aria-controls="home" aria-selected="true">RDI OD 1013 VF</button>
                                            </li>
                                            <li class="nav-item" role="presentation">
                                                <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" type="button" role="tab" aria-controls="profile" aria-selected="false">RDI OD 1005 VF</button>
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
                                                                    <th scope="col" class="font-titulo">CARRIER</th>
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
                                                                    <th scope="col" class="font-titulo">ATA_CMI</th>
                                                                    <th scope="col" class="font-titulo">ETA_Customer</th>
                                                                    <th scope="col" class="font-titulo">Estatus</th>
                                                                    <th scope="col" class="font-titulo">Comentarios</th>
                                                                    <th scope="col" class="font-titulo">INBOUND_NOT</th>
                                                                    
                                                                    <th scope="col" class="font-titulo">AA</th>
                                                                    <th scope="col" class="font-titulo">Responsable</th> 
                                                                    <th scope="col" class="font-titulo">PLANTA</th>
                                                                    <th scope="col" class="font-titulo">LT2</th>
                                                                    <th scope="col" class="font-titulo">TT</th>
                                                                    <th scope="col" class="font-titulo">CUSTOMER</th>
                                                                    <th scope="col" class="font-titulo">NUM_PEDIMENTO</th>
                                                                    <th scope="col" class="font-titulo">MES_FACTURACION</th> 
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
                                                                        <th scope="col" class="font-titulo">BL</th>
                                                                        <th scope="col" class="font-titulo">Carrier</th>
                                                                        <th scope="col" class="font-titulo">SHIPMENT</th>
                                                                        <th scope="col" class="font-titulo">LOAD_TYPE</th>
                                                                        <th scope="col" class="font-titulo">Vessel</th>
                                                                        <th scope="col" class="font-titulo">Container_type</th> 
                                                                        <th scope="col" class="font-titulo">LUM_BRIO</th>
                                                                        <th scope="col" class="font-titulo">BRAND</th>
                                                                        <th scope="col" class="font-titulo">SBU_NAME</th>
                                                                        
                                                                        <th scope="col" class="font-titulo">POL</th>
                                                                        <th scope="col" class="font-titulo">COUNTRY</th>
                                                                        <th scope="col" class="font-titulo">ACTUAL_CRD</th>
                                                                        <th scope="col" class="font-titulo">DEPARTURE_DATE</th>
                                                                        <th scope="col" class="font-titulo">MX_PORT</th>
                                                                        <th scope="col" class="font-titulo">ETA_MX_PORT</th>
                                                                        <th scope="col" class="font-titulo">ETA_DC</th>
                                                                        <th scope="col" class="font-titulo">INDC_2_Days_PUT_AWAY</th>
                                                                        <th scope="col" class="font-titulo">Arribo_real_DC</th>
                                                                        <th scope="col" class="font-titulo">Comentarios</th>
                                                                        
                                                                        <th scope="col" class="font-titulo">Inbound_notification</th>
                                                                        <th scope="col" class="font-titulo">AA</th>
                                                                        <th scope="col" class="font-titulo">Responsabe</th>
                                                                        <th scope="col" class="font-titulo">Planta</th>
                                                                        <th scope="col" class="font-titulo">TOTAL_LANE</th>                           
                                                                        <th scope="col" class="font-titulo">LT2_TARGET</th>                            
                                                                        <th scope="col" class="font-titulo">ACTUAL_LT2</th>                             
                                                                        <th scope="col" class="font-titulo">LT2_CALIBRATION</th>                        
                                                                        <th scope="col" class="font-titulo">WORKDAYS</th>                              
                                                                        <th scope="col" class="font-titulo">OCEAN_TT</th>  
                                                                        
                                                                        
                                                                         
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <%
                                                                        if (db.doDB(sql3)) {
                                                                            for (String[] row : db.getResultado()) {

                                                                    %>
                                                                    <tr>
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


