<%-- 
    Document   : documentosSeleccionados
    Created on : 13-mar-2023, 12:22:22
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
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>DOCUMENTOS SELECCIONADOS</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">
        <!-- Google fonts - Popppins for copy-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&amp;display=swap" rel="stylesheet">
        <!-- Prism Syntax Highlighting-->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/vendor/prismjs/plugins/toolbar/prism-toolbar.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/vendor/prismjs/themes/prism-okaidia.css">
        <!-- The Main Theme stylesheet (Contains also Bootstrap CSS)-->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/css/style.default.css" id="theme-stylesheet">
        <!-- Custom stylesheet - for your changes-->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/css/custom.css">
        <!-- Favicon-->
        <link rel="shortcut icon" href="<%=request.getContextPath()%>/lib/img/favicon.png">
        <!-- Table css -->
        <link href="<%=request.getContextPath()%>/lib/inbound/eventos/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- Connection Status Red -->
        <link href="<%=request.getContextPath()%>/lib/inbound/conexion/connectionStatus.css" rel="stylesheet" type="text/css"/>

        <link href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>

    
    </head>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
        
            String where = "";
            
            String opciones = request.getParameter("op"); 
            
           where = " ";

        %>

    <body>
    


        <!-- navbar-->
       
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
                                                  <h2 class="card-heading">DOCUMENTOS SELECCIONADOS</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <form id="form1" name="uploadFileFormData1">
                                             <br>
                                            <div id="table-scroll" class="table-scroll"  style="height: 100%;">
                                             
                                                <table  id="example" className="main-table">
                                                    <thead>
                                                        <tr>
                                                              <th scope="col" class="font-titulo">Evento <strong style="color:red">*</strong></th>
                                                              <th scope="col" class="font-titulo">Container <strong style="color:red">*</strong></th>
                                                              <th scope="col" class="font-titulo">BL/ AWB/ PRO <strong style="color:red">*</strong></th>
                                                              
                                                            <th scope="col" class="font-titulo">Shipment ID <strong style="color:red">*</strong></th> 
                                                            <th scope="col" class="font-titulo">Load Type <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">LUM BRIO <strong style="color:red">*</strong></th>
                                                            <th scope="col" class="font-titulo">Brand <strong style="color:red">*</strong></th> 
                                                            <th scope="col" class="font-titulo">Division <strong style="color:red">*</strong></th>	
                    
                                                            <th scope="col" class="font-titulo">MX Port </th>	 
                                                            <th scope="col" class="font-titulo" style="background-color:#C65911">ETA MX Port</th>
                                                            <th scope="col" class="font-titulo" style="background-color:#C65911"> ETA DC </th>	 
                                                            	
                                                          
                                                           
                                                      	
                                                      <!--  <th scope="col" class="font-titulo">POD /  <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Est. Departure from POL <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">ETA REAL PORT <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#C65911">LT2 <strong style="color:white">*</strong></th>
                                                            <th scope="col" class="font-titulo">Inbound notification <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">POL <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">A.A. <strong style="color:red">*</strong></th>-->
                                                            
                                                        </tr>
                                                    </thead>
                                                    <tbody>

                                                        
                                                      

                                                        <%
                                                            
                                                           String sql= "WITH sum_quantity AS ("
        +"   SELECT shipment_id, container1, SUM(quantity) AS suma"
        +"   FROM tra_inc_gtn_test"
        +"   GROUP BY shipment_id, container1"
        +" )"
        +" SELECT DISTINCT"
        +"   tie.id_evento,"
        +"   NVL(bp.responsable, ' ') AS responsable,"
        +"   gtn.final_destination,"
        +"   gtn.brand_division,"
        +"   nvl(tid.division_nombre,' '), "
        +"   gtn.shipment_id,"//5
        +"   gtn.container1,"
        +"   gtn.bl_awb_pro,"
        +"   gtn.load_type,"
        +"   sq.suma,"
        +"   tip1.NOMBRE_POD,"//10
        +"   TO_CHAR(gtn.est_departure_pol, 'MM/DD/YY') AS est_departure_pol,"
        +"   TO_CHAR(gtn.eta_port_discharge, 'MM/DD/YY') AS eta_real_port,"
        +"   NVL(gtn.max_flete, 0) AS est_eta_dc,"
        +"   'Inbound notification' AS notification_type,"
        +"   tip2.NOMBRE_POL,"//15
        +"   NVL(taa.agente_aduanal_nombre, ' ') AS agente_aduanal,"
        +"   gtn.plantilla_id,"
        +"   TO_CHAR(gtn.fecha_captura, 'MM/DD/YY') AS fecha_captura,"
        +"   tip1.nombre_pod,"
        +"   tip2.nombre_pol,"//20
        +"   tibd.nombre_bd,"
        +"   gtn.LOAD_TYPE_FINAL as ltf1 ,"
        +"   NVL(TO_CHAR(gtn.eta_plus2, 'MM/DD/YY'), ' ') AS eta_dc,"
        +"   NVL(TO_CHAR(gtn.eta_plus, 'MM/DD/YY'), ' ') AS eta_dc1,"
        +"   NVL(tie.observaciones, ' ') AS observaciones,"//25
        +"   gtn.LOAD_TYPE_FINAL, "
        + "  gtn.ETA_PORT_DISCHARGE "
        +" FROM "
        +"   tra_inb_evento tie"
        +"   LEFT JOIN tra_destino_responsable bp ON bp.user_nid = tie.user_nid"
        +"   INNER JOIN tra_inc_gtn_test gtn ON gtn.plantilla_id = tie.plantilla_id"
        +"   LEFT JOIN tra_inb_pod tip1 ON tip1.id_pod = gtn.pod"
        +"   LEFT JOIN tra_inb_pol tip2 ON tip2.id_pol = gtn.pol"
        +"   LEFT JOIN tra_inb_brand_division tibd ON tibd.id_bd = gtn.brand_division"
        +"   LEFT JOIN tra_inb_agente_aduanal taa ON taa.agente_aduanal_id = tip1.agente_aduanal_id"
        +"   LEFT JOIN tra_inb_division tid ON tid.id_division = gtn.sbu_name"
        +"   LEFT JOIN sum_quantity sq ON sq.shipment_id = gtn.shipment_id AND sq.container1 = gtn.container1"
                                                                   + " where  EMBARQUE_AGRUPADOR='"+opciones+"'"
        +" ORDER BY"
        +"   tie.id_evento";
                                                           System.out.println("sql"+sql);

                                                            if (db.doDB(sql)) {
                                                                for (String[] row : db.getResultado()) {
                                                                    // out.println("<option value=\"" + row[0] + "\" >" + row[1] + " - " + row[2] + "</option>");
                                                                    //row[18]
                                                                    //select to_char(to_date('01/08/2023','MM/DD/YYYY')+1, 'DAY', 'NLS_DATE_LANGUAGE=SPANISH') from dual
                                                        %>
 
                                                     <tr>
                                                          
                                                            <th class="font-numero"><%=row[0]%></th>	
                                                            <td class="font-texto"> <%=row[6]%></td>
                                                            <td class="font-texto"> <%=row[7]%></td>
                                                            
                                                            <td class="font-texto"> <%=row[5]%></td>
                                                            <td class="font-texto"> <%=row[26]%></td>
                                                            <td class="font-texto"> <%=row[9]%></td>	
                                                            
                                                            <td class="font-texto"> <%=row[21]%></td>  
                                                            <td class="font-texto"> <%=row[4]%></td> 
                                                            
                                                            <td class="font-texto"> <%=row[19]%></td> 
                                                            <td class="font-texto"> <%=row[27]%></td>	
                                                            <td class="font-texto"> <%=row[23]%></td>
                                                          
                                                            
                                                            <!--
                                                              <td class="font-texto"> <%=row[11]%></td>
                                                            <td class="font-texto"> <%=row[12]%></td>	
                                                            <td class="font-texto"> <%=row[13]%></td>
                                                            <td class="font-texto"> <%= row[23]%></td>
                                                            
                                                            <td class="font-texto"> <%= row[24]%></td>
                                                            <td class="font-texto"> <%=row[14]%></td>
                                                            <td class="font-texto"> <%=row[20]%></td>
                                                            
                                                            <td class="font-texto"> <%=row[16]%></td>-->
                                                           
                                                        </tr>

                                                        
                                                       <% }

                                                            }
                                                       %>
                                                    </tbody>
                                                </table>
                                             </div>
                                            <br>
                                            <!-- Botones controles -->
                                           <br>
                                             <br>
                                            <!-- Botones controles -->
                                            <div class="col-lg-12" style="text-align: right;">
                                             
                                         <!--       <a class="btn btn-info text-nowrap" role="button"  onclick="regresar()">Regresar</a>-->
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <a class="btn btn-primary text-nowrap" id="uploadBtnid" name="uploadBtnid" role="button" onclick="save()">Aceptar</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>              
                            </div>
                        </div>   
                    </section>
                </div>  
              
            </div>
        </div>   
        <script>

          function regresar() {
              history.back();
          }
           function save() {
               window.location.href =  "<%=request.getContextPath()%>/Logistica/Traslado/docDatosAdicionales.jsp?op=<%=opciones%>";

                    
                       }      
        </script>                     
    
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