<%-- 
    Document   : logbobj
    Created on : 29-ago-2024, 13:33:56
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

            String max = " select count(*) from tra_inb_bobj where FOLIO='" + folio + "' ";
         

          
            String maxt = "";
            String mint = "";
            if (db.doDB(max)) {
                for (String[] row : db.getResultado()) {
                    mint = row[0];
                    maxt = row[0];
                }
            }
            
String sql22= "SELECT  "
 + " SHIP_TO_PARTY_NAME, SOLD_TO_PARTY_NAME, SOLD_TO_NUMBER, CONTAINER_NUMBER, BL_NUMBER, REFERENCE_DOC_NUM, LINE_ITEM_REF_NUM1, SHIPMENT_NUMBER, DELIVERY_NUM_INB, PURCHASING_DOC_NUM, REF_PO_LINE, PO_CUT, OUR_REFERENCE, CUST_REF_SO_HDR, STYLE_COLOR, STYLE_COLOR_NAME, AH2_CLASS_DESC, AH3_CLASS_DESC, PO_LINE_OPEN_QTY, QTY_SUPP_CONF, PURCHASE_ORDER_QTY, INB_DELIVERY_QTY, QTY_GOODS_RECEIVED, PREPACK_INDICATOR, RECEIVED_UNITS_PK, PPK, CONTAINER_LOAD, SALES_ORDER_QTY, INB_NET_VALUE, SALES_DIST_DOC_NUM, ACTUAL_EX_FACTORY_DT, ITEM_DELIVERY_DT, SHIPPED_ASN, EST_BL_DEST_DT, DELIVERY_DT_INB, REQ_DELIVERY_DT, CANCEL_DATE, VENDOR_ACCT_NUM, GOODS_SUPPLIER_NAME, GOODS_SUPPLIER_CNTRY, PORT_LOAD_CITY, MODE1, SHIPPING_INSTR, VESSEL_NUMBER, PORT_DISCHARGE_CITY, PORT_DISCHARGE_CNTRY, BL_DEST_CITY_NAME, BL_DEST_CNTRY_CODE, FINAL_DEST_CITY_CNTY, PLANT, BRAND_NAME, SHIP_PARTY_FWD_NAME, SHIP_PARTY_CARR_NAME, DEPARTED_FROM_POL, ETA_AT_POD, ARRIVED_AT_POD, DEPARTED_FROM_POD, SEAL_NUMBER, VOYAGE, CONTAINER_TYPE_NAME, CONTAINER_CARTONS, NET_WEIGHT, CONTAINER_VOLUME, ACTUAL_GOODS_MOVE_DT, DELIVERY_COMPLETE_IND, DEL_INDICATOR_LINE, DEL_INDICATOR_HEAD, CUSTOMS_ENTRY_NBR, MATERIAL_NUM_CUST"
 + " FROM  tra_inb_bobj where FOLIO='" + folio + "' FETCH FIRST 1000 ROWS ONLY " ;
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
                                                <h2 class="card-heading">Plantilla BOBJ</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <h1 class="mb-3">Registros   <%=maxt%> </h1>
                                                                          <form id="uploadFileFormData1" name="uploadFileFormData1">


                                        <div class="card-body">
                                              <br>
                                            <div id="table-scroll" class="table-scroll"  style="height: 100%;">
                                                <table id="example" class="display" style="width:300%">
                                                    <thead>
                                                        <tr>

         
            <th class="wide-header">Ship-to-Party-Name</th>
            <th class="wide-header">Sold to Party Name</th>
            <th class="wide-header">Sold-To Number</th>
            <th class="wide-header">Container Number</th>
            <th class="wide-header">BLNumber</th>
            <th class="wide-header">Reference Document Number  </th>
            <th class="wide-header">Line Item Reference Number1</th>
            <th class="wide-header">Shipment Number</th>
            <th class="wide-header">Delivery Number  </th>
            <th class="wide-header">Purchasing Document Number</th>
            <th class="wide-header">Reference PO Line</th>
            <th class="wide-header">PO Cut</th>
            <th class="wide-header">Our Reference</th>
            <th class="wide-header">Customer Reference (SO Header Level)</th>
            <th class="wide-header">Style Color</th>
            <th class="wide-header">StyleColor Name Long</th>
            <th class="wide-header">AH2 Class 2 Description</th>
            <th class="wide-header">AH3 Class 3 Description</th>
            <th class="wide-header">PO Line Open Quantity</th>
            <th class="wide-header">Quantity as Per Supplier Confirmation</th>
            <th class="wide-header">Purchase Order Quantity (PO_QTY)</th>
            <th class="wide-header">Inbound Delivery quantity</th>
            <th class="wide-header">Quantity of Goods Received</th>
            <th class="wide-header">Prepack Indicator</th>
            <th class="wide-header">Received Units (Prepack)</th>
            <th class="wide-header">PPK</th>
            <th class="wide-header">Container Load</th>
            <th class="wide-header">Sales Order Quantity</th>
            <th class="wide-header">Inbound Net Value</th>
            <th class="wide-header">Sales and Distribution D N</th>
            <th class="wide-header">Actual Ex Factory Date</th>
            <th class="wide-header">Item delivery date  </th>
            <th class="wide-header">Shipped (ASN)</th>
            <th class="wide-header">Estimated BL Destination Date</th>
            <th class="wide-header">Delivery Date </th>
            <th class="wide-header">Requested Delivery Date</th>
            <th class="wide-header">Cancellation Date</th>
            <th class="wide-header">Vendor's account number</th>
            <th class="wide-header">Goods Supplier Name</th>
            <th class="wide-header">Goods Supplier Country Key</th>
            <th class="wide-header">Port Of Loading City Name</th>
            <th class="wide-header">Mode</th>
            <th class="wide-header">Shipping Instructions</th>
            <th class="wide-header">Vessel Number</th>
            <th class="wide-header">Port Of Discharge C N</th>
            <th class="wide-header">Port Of Discharge C C</th>
            <th class="wide-header">BL Destination C N</th>
            <th class="wide-header">BL Destination C C</th>
            <th class="wide-header">Final Destination C C C</th>
            <th class="wide-header">Plant</th>
            <th class="wide-header">Brand Name</th>
            <th class="wide-header"> Freight Forwarder Name</th>
            <th class="wide-header"> Carrier Name</th>
            <th class="wide-header">Departed from POL</th>
            <th class="wide-header">ETA at POD </th>
            <th class="wide-header">Arrived at POD</th>
            <th class="wide-header">Departed from POD</th>
            <th class="wide-header">Seal Number</th>
            <th class="wide-header">Voyage</th>
            <th class="wide-header">Container Type N</th>
            <th class="wide-header">Container Number of C</th>
            <th class="wide-header">Net Weight</th>
            <th class="wide-header">Container V</th>
            <th class="wide-header">Actual Goods Movement D</th>
            <th class="wide-header">Delivery Completed I</th>
            <th class="wide-header"> Line Item Level </th>
            <th class="wide-header"> Header Level    </th>
            <th class="wide-header">Customs Entry Nbr</th>
            <th class="wide-header">Material Number Used by C</th>
      

                                                    
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            if (db.doDB(sql22)) {
                                                                for (String[] row : db.getResultado()) {
                                                              
                                                        %>
                                                        <tr  >
            <td class="font-texto"><%= row[0] %></td>
            <td class="font-texto"><%= row[1] %></td>
            <td class="font-texto"><%= row[2] %></td>
            <td class="font-texto"><%= row[3] %></td>
            <td class="font-texto"><%= row[4] %></td>
            <td class="font-texto"><%= row[5] %></td>
            <td class="font-texto"><%= row[6] %></td>
            <td class="font-texto"><%= row[7] %></td>
            <td class="font-texto"><%= row[8] %></td>
            <td class="font-texto"><%= row[9] %></td>
            <td class="font-texto"><%= row[10] %></td>
            <td class="font-texto"><%= row[11] %></td>
            <td class="font-texto"><%= row[12] %></td>
            <td class="font-texto"><%= row[13] %></td>
            <td class="font-texto"><%= row[14] %></td>
            <td class="font-texto"><%= row[15] %></td>
            <td class="font-texto"><%= row[16] %></td>
            <td class="font-texto"><%= row[17] %></td>
            <td class="font-texto"><%= row[18] %></td>
            <td class="font-texto"><%= row[19] %></td>
            <td class="font-texto"><%= row[20] %></td>
            <td class="font-texto"><%= row[21] %></td>
            <td class="font-texto"><%= row[22] %></td>
            <td class="font-texto"><%= row[23] %></td>
            <td class="font-texto"><%= row[24] %></td>
            <td class="font-texto"><%= row[25] %></td>
            <td class="font-texto"><%= row[26] %></td>
            <td class="font-texto"><%= row[27] %></td>
            <td class="font-texto"><%= row[28] %></td>
            <td class="font-texto"><%= row[29] %></td>
            <td class="font-texto"><%= row[30] %></td>
            <td class="font-texto"><%= row[31] %></td>
            <td class="font-texto"><%= row[32] %></td>
            <td class="font-texto"><%= row[33] %></td>
            <td class="font-texto"><%= row[34] %></td>
            <td class="font-texto"><%= row[35] %></td>
            <td class="font-texto"><%= row[36] %></td>
            <td class="font-texto"><%= row[37] %></td>
            <td class="font-texto"><%= row[38] %></td>
            <td class="font-texto"><%= row[39] %></td>
            <td class="font-texto"><%= row[40] %></td>
            <td class="font-texto"><%= row[41] %></td>
            <td class="font-texto"><%= row[42] %></td>
            <td class="font-texto"><%= row[43] %></td>
            <td class="font-texto"><%= row[44] %></td>
            <td class="font-texto"><%= row[45] %></td>
            <td class="font-texto"><%= row[46] %></td>
            <td class="font-texto"><%= row[47] %></td>
            <td class="font-texto"><%= row[48] %></td>
            <td class="font-texto"><%= row[49] %></td>
            <td class="font-texto"><%= row[50] %></td>
            <td class="font-texto"><%= row[51] %></td>
            <td class="font-texto"><%= row[52] %></td>
            <td class="font-texto"><%= row[53] %></td>
            <td class="font-texto"><%= row[54] %></td>
            <td class="font-texto"><%= row[55] %></td>
            <td class="font-texto"><%= row[56] %></td>
            <td class="font-texto"><%= row[57] %></td>
            <td class="font-texto"><%= row[58] %></td>
            <td class="font-texto"><%= row[59] %></td>
            <td class="font-texto"><%= row[60] %></td>
            <td class="font-texto"><%= row[61] %></td>
            <td class="font-texto"><%= row[62] %></td>
            <td class="font-texto"><%= row[63] %></td>
            <td class="font-texto"><%= row[64] %></td>
            <td class="font-texto"><%= row[65] %></td>
            <td class="font-texto"><%= row[66] %></td>
            <td class="font-texto"><%= row[67] %></td>
            <td class="font-texto"><%= row[68] %></td> 
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
