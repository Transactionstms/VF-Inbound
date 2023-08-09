<%-- 
    Document   : modificarEventos
    Created on : 10/03/2023, 11:28:29 AM
    Author     : Desarrollo Tacts
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
                
String sql2= "WITH sum_quantity AS ("
        +"   SELECT shipment_id, container1, SUM(quantity) AS suma"
        +"   FROM tra_inc_gtn_test"
        +"   GROUP BY shipment_id, container1"
        +" )"
        +" SELECT DISTINCT"
        +"   tie.id_evento,"
        +"   NVL(bp.responsable, 'Sin Responsable') AS responsable,"
        +"   gtn.final_destination,"
        +"   nvl(gtn.brand_division,' '), "
        +"   nvl(tid.division_nombre,' '), "
        +"   gtn.shipment_id,"
        +"   gtn.container1,"
        +"   gtn.bl_awb_pro,"
        +"   gtn.load_type,"
        +"   sq.suma,"
        +"   tip1.NOMBRE_POD,"
        +"   TO_CHAR(gtn.est_departure_pol, 'MM/DD/YY') AS est_departure_pol,"
        +"   TO_CHAR(gtn.eta_port_discharge, 'MM/DD/YY') AS eta_real_port,"
        +"   NVL(gtn.max_flete, 0) AS est_eta_dc,"
        +"  TO_CHAR(gtn.fecha_captura, 'MM/DD/YY') AS notification_type,"
        +"   tip2.NOMBRE_POL,"
        +"   NVL(taa.agente_aduanal_nombre, ' ') AS agente_aduanal,"
        +"   gtn.plantilla_id,"
        +"   ' ' AS fecha_captura,"
        +"   tip1.nombre_pod,"
        +"   tip2.nombre_pol,"
        +"   tibd.nombre_bd,"
        +"   CASE"
        +"     WHEN gtn.load_type = 'LTL' THEN 'LTL'"
        +"     WHEN EXISTS ("
        +"       SELECT 1"
        +"       FROM tra_inc_gtn_test"
        +"       WHERE container1 = gtn.container1"
        +"       HAVING COUNT(DISTINCT brand_division) > 1"
        +"     ) THEN 'FCL / LCL'"
        +"     WHEN gtn.load_type = 'FCL' THEN 'FCL'"
        +"     WHEN gtn.load_type = 'LCL' THEN 'LCL'"
        +"     ELSE '-'"
        +"   END AS estado,"
        +"   NVL(TO_CHAR(gtn.eta_plus2, 'MM/DD/YY'), ' ') AS eta_dc,"
        +"   NVL(TO_CHAR(gtn.eta_plus, 'MM/DD/YY'), ' ') AS eta_dc1,"
        +"   NVL(tie.observaciones, ' ') AS observaciones"
          + " ,nvl(gtn.CANTIDAD_FINAL,0)"
        +" FROM"
        +"   tra_inb_evento tie"
        +"   LEFT JOIN tra_destino_responsable bp ON bp.user_nid = tie.user_nid"
        +"   INNER JOIN tra_inc_gtn_test gtn ON gtn.plantilla_id = tie.plantilla_id"
        +"   LEFT JOIN tra_inb_pod tip1 ON tip1.id_pod = gtn.pod"
        +"   LEFT JOIN tra_inb_pol tip2 ON tip2.id_pol = gtn.pol"
        +"   LEFT JOIN tra_inb_brand_division tibd ON tibd.id_bd = gtn.brand_division"
        +"   LEFT JOIN tra_inb_agente_aduanal taa ON taa.agente_aduanal_id = tip1.agente_aduanal_id"
        +"   LEFT JOIN tra_inb_division tid ON tid.id_division = gtn.sbu_name"
        +"   LEFT JOIN sum_quantity sq ON sq.shipment_id = gtn.shipment_id AND sq.container1 = gtn.container1"
        +"   WHERE gtn.final_destination <> 'MARKETING MEXICO' "
        +" ORDER BY"
        +"   tie.id_evento";

        int cont =0; 
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
                                                <h2 class="card-heading"> Modificar Evento   </h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <form id="uploadFileFormData1" name="uploadFileFormData1">
                                            <input type="hidden" id="agenteId" name="agenteId" value="<%=agenteAduanal%>">
                                            <br>
                                            <div id="table-scroll" class="table-scroll"  style="height: 100%;">
                                                <table id="example" class="display" style="width:300%">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col" class="font-titulo">Número de evento <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Responsable <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Final Destination (Shipment) <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Brand-Division <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Division <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Shipment ID <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Container <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">BL/ AWB/ PRO <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Load Type <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Quantity <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">POD /  <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Est. Departure from POL <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">ETA REAL PORT <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#C65911">LT2 <strong style="color:white">*</strong></th>
                                                            <th scope="col" class="font-titulo" style="background-color:#C65911">ETA DC  </th>
                                                            <th scope="col" class="font-titulo" style="background-color:#C65911"> INDC +2 Days Put Away </th>
                                                            <th scope="col" class="font-titulo">Inbound notification <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">POL <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">A.A. <strong style="color:red">*</strong></th>
                                                            <th scope="col" class="font-titulo">Observaciones </th>
                                                            <th scope="col" class="font-titulo"></th>
                                                            <!--<th scope="col" class="font-titulo">Eliminar</th>-->
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            if (db.doDB(sql2)) {
                                                                for (String[] row : db.getResultado()) {
                                                                   cont++;
                                                                   
                                                                   if(row[4].equals("No/DSN")){
                                                                     colors = "color: #ff0000;";
                                                                   }else{
                                                                     colors = "color: #000000;";  
                                                                   }
                                                        %>
                                                        <tr style="<%=colors%>">
                                                            <th class="font-numero" style="cursor: pointer" onclick="editarEvento('<%=row[0]%>','<%=row[5]%>','<%=row[6]%>')"><%=row[0]%></th>	
                                                            <td class="font-texto"> <%=row[1]%></td>
                                                            <td class="font-texto"> <%=row[2]%></td>
                                                            <td class="font-texto"> <%=row[21]%></td>
                                                            <td class="font-texto"> <%=row[4]%></td>
                                                            <td class="font-texto"> <%=row[5]%></td>	
                                                            <td class="font-texto"> <%=row[6]%></td>	
                                                            <td class="font-texto"> <%=row[7]%></td>
                                                            <td class="font-texto"> <%=row[22]%> </td>		
                                                            <td class="font-texto"> <%=row[26]%></td>	
                                                            <td class="font-texto"> <%=row[19]%></td>
                                                            <td class="font-texto"> <%=row[11]%></td>	
                                                            <td class="font-texto"> <%=row[12]%></td>	
                                                            <td class="font-texto"> <%=row[13]%></td>
                                                            <td class="font-texto"> <%= row[23]%></td>
                                                            <td class="font-texto"> <%= row[24]%></td>
                                                            <td class="font-texto"> <%=row[14]%></td>
                                                            <td class="font-texto"> <%=row[20]%></td>	
                                                            <td class="font-texto"> <%=row[16]%></td>
                                                            <td class="font-texto" contenteditable='true'>  
                                                                <input type="text" style="border: none;" id="observaciones<%=cont%>" name="observaciones<%=cont%>" value="<%=row[25]%>" autocomplete="off">
                                                            </td>
                                                            <td><center><button type="button" class="btn btn-primary" onclick="editarEvento('<%=row[0]%>','<%=row[5]%>','<%=row[6]%>')">Modificar</button></center></td>
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
        <script>
           
           
            function editarEvento(id,ship,conteiner) {
                console.log('editar');//
                  console.log(id);
                  console.log(ship);
                  console.log(conteiner);
                window.location.href = '<%=request.getContextPath()%>/Importacion/modificarEventos2.jsp?id=' + id+"&ship="+ship+"&con="+conteiner;

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