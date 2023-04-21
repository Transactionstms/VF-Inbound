<%-- 
    Document   : ReporteIBR
    Created on : 20-feb-2023, 11:00:26
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
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));

                String sql = ""
                        + "SELECT  DISTINCT"
                        + "  TIE.ID_EVENTO,"
                        + "  GTN.CONTAINER1,"
                        + "  GTN.BL_AWB_PRO,"
                        + "  GTN.SHIPMENT_ID,"
                        + "  GTN.LOAD_TYPE,"
                        + " (select sum(  tt.QUANTITY ) from TRA_INC_GTN_TEST tt where tt.PLANTILLA_ID =GTN.PLANTILLA_ID   )  LUM_BRIO ,"
                        + "  GTN.BRAND_DIVISION,"
                        + " nvl(dns.SBU_NAME,' ')  ,"
                        + "  GTN.POL,"
                        + "  to_char(GTN.ACTUAL_CRD,'MM/DD/YYYY'),"
                        + "  to_char(GTN.EST_DEPARTURE_POL,'MM/DD/YYYY'),"
                        + "123,"
                        + "456,"
                        + "GTN.POD,"
                        + "'ETA MX Port',"
                        + "  to_char(GTN.ACTUAL_CRD"
                        + "  + "
                        + "  ( "
                + "   SELECT   MAX(nvl(recommended_lt2,80))  FROM   tra_inb_costofleteytd   WHERE  TRIM(id_bd) = TRIM(gtn.brand_division)   AND TRIM(id_pod) = TRIM(gtn.pod)   AND TRIM(id_pol) = TRIM(gtn.pol) "
                        + "  )-2 "
                        + "  ,'MM/DD/YYYY') as ETA_DC,"//--+DIAS
                        + "  "
                        + "    to_char(GTN.ACTUAL_CRD"
                        + "  + "
                        + "  ( "
               + "   SELECT   MAX(nvl(recommended_lt2,80))  FROM   tra_inb_costofleteytd   WHERE  TRIM(id_bd) = TRIM(gtn.brand_division)   AND TRIM(id_pod) = TRIM(gtn.pod)   AND TRIM(id_pol) = TRIM(gtn.pol) "
                         + "  )"
                        + "  ,'MM/DD/YYYY') as ETA_DC,"//--+DIAS
                        + "  "
                        //+"  to_char(GTN.ACTUAL_CRD,'MM/DD/YYYY'),"//--+DIAS
                        + "' Arribo real  DC',"
                        + "' Comentarios',"
                        + " "
                        + "    ( "
               + "   SELECT   MAX(nvl(recommended_lt2,80))  FROM   tra_inb_costofleteytd   WHERE  TRIM(id_bd) = TRIM(gtn.brand_division)   AND TRIM(id_pod) = TRIM(gtn.pod)   AND TRIM(id_pol) = TRIM(gtn.pol) "
                        + "  )as EST_ETA_DC "
                        + " ,TIP1.NOMBRE_POD,"
                        + " TIP2.NOMBRE_POL,"
                        + " tibd.NOMBRE_BD,"
                        + " nvl((select count(distinct  BRAND_DIVISION) from tra_inc_gtn_test where CONTAINER1=GTN.CONTAINER1),0) "

                        + "   from TRA_INB_EVENTO    TIE"
                        + "  inner JOIN TRA_DESTINO_RESPONSABLE     BP ON BP.USER_NID=TIE.USER_NID  "
                        + "  inner JOIN TRA_INC_GTN_TEST           GTN ON GTN.PLANTILLA_ID=TIE.PLANTILLA_ID"
                        + "  left join tra_inb_dns                dns on dns.SHIPMENT_NUM=TIE.SHIPMENT_ID     "
                        + " left join tra_inb_POD tip1 on tip1.ID_POD=GTN.POD"
                        + " left join tra_inb_POL tip2 on tip2.ID_POL=GTN.POL"
                        + " left join tra_inb_BRAND_DIVISION tibd on tibd.ID_BD=GTN.BRAND_DIVISION"
                        + "  order by 1"
                        + ""
                        + ""
                        + "";

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
                                                <h2 class="card-heading"> Reporte IBR</h2>
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
                                                <table id="main-table" class="main-table" style="table-layout:fixed; width:200%;">
                                                    <thead>
                                                        <tr>

                                                            <th scope="col" class="font-titulo">Evento </th>
                                                            <th scope="col" class="font-titulo">Container# </th>
                                                            <th scope="col" class="font-titulo">BL </th>
                                                            <th scope="col" class="font-titulo">Shipment </th>
                                                            <th scope="col" class="font-titulo">Load Type </th>
                                                            <th scope="col" class="font-titulo">LUM BRIO </th>
                                                            <th scope="col" class="font-titulo">Brand</th>
                                                            <th scope="col" class="font-titulo">Sbu Name </th>
                                                            <th scope="col" class="font-titulo">POL </th>
                                                            <th scope="col" class="font-titulo">Actual CRD </th>
                                                            <th scope="col" class="font-titulo">Departure Date </th>
                                                            <!--  <th scope="col" class="font-titulo">123 </th>
                                                              <th scope="col" class="font-titulo">456 </th>-->
                                                            <th scope="col" class="font-titulo">MX Port </th>
                                                            <th scope="col" class="font-titulo">ETA MX Port </th>
                                                            <th scope="col" class="font-titulo">ETA DC </th>
                                                            <th scope="col" class="font-titulo">INDC +2 Days Put Away </th>
                                                            <th scope="col" class="font-titulo">Arribo real DC </th>
                                                            <th scope="col" class="font-titulo">Comentarios </th> 


                                                            <!--<th scope="col" class="font-titulo">Eliminar</th>-->
                                                        </tr>
                                                    </thead>
                                                    <tbody>

                                                        <%       if (db.doDB(sql)) {
                                                                for (String[] row : db.getResultado()) {
                                                                    
                                             int lcdN=0;
                                                try{
                                                      lcdN=Integer.parseInt(row[23]);
                                                    }catch(NumberFormatException e){
                                                      lcdN=0;
                                                    }
                                                
                                                String lcd="FCL"; 
                                                if(lcdN>1){
                                                    lcd="FCL / LCL";
                                                }

                                                        %>


                                                        <tr> 
                                                            <th class="font-texto"> <%=row[0]%></th>
                                                            <td class="font-texto"> <%=row[1]%></td>
                                                            <td class="font-texto"> <%=row[2]%></td>
                                                            <td class="font-texto"> <%=row[3]%></td>
                                                            <td class="font-texto"> <%=lcd%> 
                                                               <!-- <%=row[20]%>pod-->      
                                                                    
                                                            </td>
                                                            <td class="font-texto"> <%=row[5]%></td>	
                                                            <td class="font-texto"> <%=row[22]%>   </td>	
                                                            <td class="font-texto"> <%=row[7]%></td>
                                                            <td class="font-texto">  <%=row[21]%></td>		
                                                            <td class="font-texto"> <%=row[9]%></td>	
                                                            <td class="font-texto"> <%=row[10]%></td>

<!-- <td class="font-texto"> <%=row[11]%></td>	
<td class="font-texto"> <%=row[12]%></td>-->

                                                            <td class="font-texto"> <%=row[13]%></td> 
                                                            <td class="font-texto"> <%=row[14]%></td>
                                                            <td class="font-texto"> <%=row[15]%></td>	
                                                            <td class="font-texto"> <%=row[16]%></td>
                                                            <td class="font-texto"> <%=row[17]%></td>
                                                            <td class="font-texto"> <%=row[18]%></td>
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