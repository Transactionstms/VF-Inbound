<%-- 
    Document   : logActBatch
    Created on : 01-ago-2023, 17:17:02
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

            String max = " select max(consecutivo),min(consecutivo),count(*) from tra_inb_eta_act where FOLIO='" + folio + "' ";
       

          
            String maxt = "";
            String mint = "";
            String count = "";
            if (db.doDB(max)) {
                for (String[] row : db.getResultado()) {
                    mint = row[1];
                    maxt = row[0];
                    count =  row[2];
                }
            }
            
                 String error = "   SELECT num"
+" FROM ("
+"   SELECT LEVEL + 2 AS num -- Iniciamos desde 3 (LEVEL + 2)"
+"   FROM DUAL"
+"   CONNECT BY LEVEL <= 100 -- Finalizamos en " + maxt + ""
+" ) full_sequence"
+" WHERE num NOT IN (SELECT CONSECUTIVO FROM tra_inb_eta_act);where FOLIO='" + folio + "' ";
            
              String consulta =  "   SELECT CONSECUTIVO,"
                      + " contenedor,"
       +" bl,"
       +" naviera,"
       +" puerto_de_carga,"
       +" fecha_zarpe,"
       +" destino_final,"
       +" eta_original,"
       +" eta_actual,"
       +" retraso_eta_dias,"
       +" ultima_actualizacion,"
       +" estatus,"
       +" fecha_arribo,"
       +" fecha_descarga,"
       +" FOLIO" 
      +"  from tra_inb_eta_act where FOLIO='" + folio + "' ";
               
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
                                        <h1 class="mb-3">Registros   <%=count%> </h1>
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
                                                <table id="example" class="display" style="width: 1200px">
                                                    <thead>
                                                        <tr>
                                                           
                                                            
                                                            <th scope="col" class="font-titulo">#</th>	
                                                            <th scope="col" class="font-titulo">CONTENEDOR</th>
                                                            <th scope="col" class="font-titulo">BL</th> 
                                                            <th scope="col" class="font-titulo">NAVIERA</th>
                                                            <th scope="col" class="font-titulo">PUERTO_DE_CARGA</th> 
                                                            <th scope="col" class="font-titulo">FECHA_ZARPE</th>	
                                                            <th scope="col" class="font-titulo">DESTINO_FINAL</th>
                                                            <th scope="col" class="font-titulo">ETA_ORIGINAL</th> 
                                                            <th scope="col" class="font-titulo">ETA_ACTUAL</th>
                                                            <th scope="col" class="font-titulo">RETRASO_ETA_DIAS</th> 
                                                            
                                                            <th scope="col" class="font-titulo">ULTIMA_ACTUALIZACION</th>	
                                                            <th scope="col" class="font-titulo">ESTATUS</th>
                                                            <th scope="col" class="font-titulo">FECHA_ARRIBO</th> 
                                                            <th scope="col" class="font-titulo">FECHA_DESCARGA</th>
                                                            <th scope="col" class="font-titulo">FECHA_ACTUAL</th> 
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            if (db.doDB(consulta)) {
                                                                for (String[] row : db.getResultado()) {

                                                        %>
                                                        <tr>
                                                            <th class="font-numero"><%=row[0]%></th>	
                                                            <td class="font-numero"><%=row[1]%></td>
                                                            <td class="font-texto"> <%=row[2]%></td> 
                                                            <td class="font-numero"><%=row[3]%></td>
                                                            <td class="font-texto"> <%=row[4]%></td> 
                                                            <th class="font-numero"><%=row[5]%></th>	
                                                            <td class="font-numero"><%=row[6]%></td>
                                                            <td class="font-texto"> <%=row[7]%></td> 
                                                            <td class="font-numero"><%=row[8]%></td>
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
