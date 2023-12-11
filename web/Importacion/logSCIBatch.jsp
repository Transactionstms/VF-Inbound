<%-- 
    Document   : logSCIBatch
    Created on : 11-dic-2023, 14:59:28
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

            String sql2 = "SELECT "
+ " ID_SCI,"
+ " ACTUAL_CRD_DATE,"
+ " SHIPPED_ASN_ACTUAL_DATE_UTC,"
+ " EST_DEPARTURE_FROM_POL,"
+ " DEPARTED_FROM_POL_ACTU,"
+ " ETA_POD_DATE,"
+ " VENDOR_RAW_NAME,"
+ " POL_CITY,"
+ " CARRIER,"
+ " CURRENT_VESSEL,"
+ " BL_AWB,"
+ " CONTAINER_NUMBER,"
+ " ORIGINAL_SHIPMENT_ID,"
+ " PO_PO_LINE,"
+ " SEAL_NUMBER,"
+ " PRODUCT_CODE,"
+ " STYLE_NAME,"
+ " CONTAINER_TYPE,"
+ " MODE1,"
+ " LOAD_TYPE,"
+ " SHIPMENT_FINAL_DESTINATION_LOCATION,"
+ " LAST_MILESTONE,"
+ " LAST_ADDED_COMMENT,"
+ " DEPARTMENT_NAME,"
+ " POD_CITY,"
+ " CONTRACT_NUMBER,"
+ " FORWARDER_RAW_NAME,"
+ " HBL_HAWB,"
+ " TERMINAL_POD,"
+ " PENDIENTE,"
+ " CLASS_2_AH2,"
+ " CLASS_3_AH3,"
+ " SUB_CATEGORY_AH4,"
+ " PRODUCT_GROUP_AH5,"
+ " TOTAL_ACTUAL_WT_KGS,"
+ " TOTAL_ACTUAL_VOL_CBMS,"
+ " TOTAL_SHIPPED_QTY,"
+ " TOTAL_CARTONS,"
+ " FOLIO "
                    + " FROM TRA_INB_SCI WHERE FOLIO='" + folio + "' ";


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
                                                <h2 class="card-heading">Plantilla SCI   </h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <h1 class="mb-3">Registros   </h1>
                                        <ol class="list-group list-group-flush mb-5"  style="  overflow: auto;  max-height: 300px; ">


                                            <h3 class="mb-3 mt-5"> Datos Guardados</h3>

                                            <form id="uploadFileFormData1" name="uploadFileFormData1"> 
                                                <br>
                                                <div id="table-scroll" class="table-scroll"  style="height: 60%;">
                                                    <table id="example" class="display" style="width:200%">
                                                        <thead>
                                                            <tr>
                                                                <th  >Actual_CRD_Date</th>
                                                                <th  >Shipped_ASN_Actual_Date_UTC</th>
                                                                <th  >Est_Departure_from_POL</th>
                                                                <th  >Departed_from_POL_Actu</th>
                                                                <th  >ETA_POD_Date</th>
                                                                <th  >Vendor_Raw_Name</th>
                                                                <th  >POL_City</th>
                                                                <th  >Carrier</th>
                                                                <th  >Current_Vessel</th>
                                                                <th  >BL_AWB</th>
                                                                <th  >Container_Number</th>
                                                                <th  >Original_Shipment_ID</th>
                                                                <th  >PO_PO_LINE</th>
                                                                <th  >Seal_Number</th>
                                                                <th  >Product_Code</th>
                                                                <th  >Style_Name</th>
                                                                <th  >Container_Type</th>
                                                                <th  >Mode1</th>
                                                                <th  >Load_Type</th>
                                                                <th  >Shipment_Final_Destination_Location</th>
                                                                <th  >Last_Milestone</th>
                                                                <th  >Last_Added_Comment</th>
                                                                <th  >Department_Name</th>
                                                                <th  >POD_City</th>
                                                                <th  >Contract_Number</th>
                                                                <th  >Forwarder_Raw_Name</th>
                                                                <th  >HBL_HAWB</th>
                                                                <th  >Terminal_POD</th>
                                                                <th  >pendiente</th>
                                                                <th  >Class_2_AH2</th>
                                                                <th  >Class_3_AH3</th>
                                                                <th  >Sub_Category_AH4</th>
                                                                <th  >Product_Group_AH5</th>
                                                                <th  >Total_Actual_Wt_KGs</th>
                                                                <th  >Total_Actual_Vol_CBMs</th>
                                                                <th  >Total_Shipped_Qty</th>
                                                                <th  >Total_Cartons</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <%      
                                                                System.out.println("sql"+sql2);
                                                                if (db.doDB(sql2)) {
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
