<%-- 
    Document   : logibr
    Created on : 22-feb-2024, 14:07:56
    Author     : grecendiz
--%>

<%@page import="com.onest.oracle.DB"%>
<%@page import="com.onest.oracle.DBConfData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>bandejaPlaneacion</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">
        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
        <link href="../lib/inbound/eventos/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- Connection Status Red -->

        <link href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
 <style>
        .table-scroll {
            position: relative;
            max-height: 60%;
            overflow-y: auto;
            width: 100%;
        }

        .table-scroll table {
            width: 100%;
            border-collapse: collapse;
        }

        .table-scroll th,
        .table-scroll td {
            padding: 8px 16px;
            text-align: left;
        }

        .table-scroll thead th {
            position: sticky;
            top: 0;
             z-index: 10;
        }

       
    </style>
    </head>
    <%
        try {
            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            String folio = request.getParameter("folio");

            String max = " select count(*) from TRA_INB_ibr where FOLIO='" + folio + "' ";
         

          
            String maxt = "";
            String mint = "";
            if (db.doDB(max)) {
                for (String[] row : db.getResultado()) {
                    mint = row[0];
                    maxt = row[0];
                }
            }
            //TO_CHAR(sq.suma, 'FM999G999G999G999')
String sql22= "  select "
        + "  IDIBR_EVENTO, CONTENEDOR, BL, SHIPMENT, LOAD_TYPE, "
        + " CASE"
        + "   WHEN REGEXP_LIKE(LUM_BRIO, '^[0-9]+$') THEN TO_CHAR(TO_NUMBER(LUM_BRIO), 'FM999G999G999G999') "
        + "   ELSE 'Datos no numéricos' "
        + "  END AS monto_formateado , " 
        + " BRAND, SBU_NAME, POL, COUNTRY, ACTUAL_CRD, DEPARTURE_DATE, MX_PORT, ETA_MX_PORT, ETA_DC, INDC_A, ARRIBO_REAL_A_DC, COMENTARIOS, REQUIERE_ETIQUETADO, A123, B456, DEPARTURE_PORT, ETA_CUSTOMER, CUSTOMER, ATA_CMI, FECHA_ACTUAL, EVENTO, ETA_CUSTOMER2, TIPO, FOLIO "
        + " from TRA_INB_ibr  where FOLIO='" + folio + "'";
    %>
    <body> 
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
                                                <h2 class="card-heading">Plantilla IBR   </h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <h1 class="mb-3">Registros   <%=maxt%> </h1>
                            

                                        <div class="card-body">
                                        <form id="uploadFileFormData1" name="uploadFileFormData1">
                                             <br>
                                            <div id="table-scroll" class="table-scroll"  style="height: 100%;">
                                                <table id="example" class="display" style="width:300%">
                                                    <thead>
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
<th scope="col" class="font-titulo">ETA_DC</th>                   
<th scope="col" class="font-titulo">INDC_2_DAYS_PUT_AWAY</th>           
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            if (db.doDB(sql22)) {
                                                                for (String[] row : db.getResultado()) {
                                                              
                                                        %>
                                                        <tr  >
                                                             <td class="font-texto"> <%=row[26]%></td>
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
                    </section>
                </div>  

            </div>
        </div>   
        <!-- Conexión estatus red -->                    
        <script src='https://code.jquery.com/jquery-3.5.1.js'></script>
        <script src='https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js'></script>
        <script src='https://cdn.datatables.net/buttons/2.3.6/js/dataTables.buttons.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js'></script>
        <script src='https://cdn.datatables.net/buttons/2.3.6/js/buttons.html5.min.js'></script>
        <script src='https://cdn.datatables.net/buttons/2.3.6/js/buttons.print.min.js'></script>




        <script type="text/javascript">

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


        </script>
    </body>
    <%
        } catch (NullPointerException e) {
            // out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            //  out.println("<script>window.close();</script>");
        } catch (Exception e) {
            out.println("Excepcion revise por favor! " + e);
            e.printStackTrace();
        }
    %>
</html>

