<%-- 
    Document   : logDsnBatch
    Created on : 02-jun-2023, 12:28:15
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

    </head>
    <%
        try {
            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            String folio = request.getParameter("folio");

            String max = " select max(consecutivo),min(consecutivo) from tra_inb_dns where FOLIO='" + folio + "' ";
            String error = " WITH numeros AS ( "
                    + " "
                    + "   SELECT LEVEL AS numero"
                    + "   FROM DUAL"
                    + "   CONNECT BY LEVEL <= (select MAX(CONSECUTIVO) from tra_inb_dns where FOLIO='" + folio + "')"
                    + " )"
                    + " SELECT numero"
                    + " FROM numeros"
                    + " WHERE numero NOT IN ("
                    + "   SELECT CONSECUTIVO"
                    + "   FROM tra_inb_dns where FOLIO='" + folio + "'"
                    + " ) and  numero not in (1,2) "
                    + " ORDER BY numero ";

            String sql2 = " select SHIPMENT_NUM, CONTAINER_NUM,PLANNED_QTY,ORDER_NUMBER, CUSTMR_NBR, PO_CUSTOMER_NAME from tra_inb_dns where FOLIO='" + folio + "' ";

            String maxt = "";
            String mint = "";
            if (db.doDB(max)) {
                for (String[] row : db.getResultado()) {
                    mint = row[1];
                    maxt = row[0];
                }
            }
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
                                                <h2 class="card-heading">Plantilla Inbound   </h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <h1 class="mb-3">Registros   <%=maxt%> </h1>
                                        <h3> No se pudieron insertar las siguientes filas porfavor revise datos </h3>
                                        <ol class="list-group list-group-flush mb-5"  style="  overflow: auto;  max-height: 300px; ">


                                            <%
                                                if (db.doDB(error)) {
                                                    for (String[] row : db.getResultado()) {

                                            %>
                                            <li class="list-group-item"><%=row[0]%></li>                                      

                                            <%
                                                    }
                                                }
                                            %>
                                        </ol>                                

                                        <h3 class="mb-3 mt-5"> Datos Guardados</h3>

                                        <form id="uploadFileFormData1" name="uploadFileFormData1"> 
                                            <br>
                                            <div id="table-scroll" class="table-scroll"  style="height: 60%;">
                                                <table id="example" class="display" style="width:100%">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col" class="font-titulo"> SHIPMENT_NUM,     </th>	
                                                            <th scope="col" class="font-titulo">CONTAINER_NUM </th>
                                                            <th scope="col" class="font-titulo">PLANNED_QTY  </th> 
                                                            <th scope="col" class="font-titulo">ORDER_NUMBER </th>
                                                            <th scope="col" class="font-titulo">CUSTMR_NBR  </th> 
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            if (db.doDB(sql2)) {
                                                                for (String[] row : db.getResultado()) {

                                                        %>
                                                        <tr>
                                                            <th class="font-numero"><%=row[0]%></th>	
                                                            <td class="font-numero"><%=row[1]%></td>
                                                            <td class="font-texto"> <%=row[2]%></td> 
                                                            <td class="font-numero"><%=row[3]%></td>
                                                            <td class="font-texto"> <%=row[4]%></td> 
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
