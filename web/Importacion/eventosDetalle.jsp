<%-- 
    Document   : eventosDetalle
    Created on : 19/01/2023, 04:35:13 AM
    Author     : luis_
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
        <!-- Window load -->
        <link href="../lib/Loader/css/windowsLoad.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String UserId = (String) ownsession.getAttribute("login.user_id_number");
                String cve = (String) ownsession.getAttribute("cbdivcuenta");
        
                ConsultasQuery fac = new ConsultasQuery();
                String agenteAduanal = "";
                String colors = "";
                String nomeclatura = ""; 
                String soporteAA = "";
                int cont = 0; 

                if (db.doDB(fac.consultarAAEventosDetalle(UserId))) { 
                    for (String[] rowA : db.getResultado()) {
                        
                        if(rowA[0].equals("4006")){ /*Agente Aduanal Administrador*/
                            agenteAduanal = "4001,4002,4003,4004,4005,4006";
                        }else{
                            agenteAduanal = rowA[0];
                        }
                        
                    }
                }
        %>
        <!-- navbar-->
        <header class="header">
        </header>
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
                                                <h2 class="card-heading"> Eventos Nuevos </h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <form id="uploadFileFormData1" name="uploadFileFormData1">
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
                                                           <!--posiblemente se borre
                                                                                                                        <th scope="col" class="font-titulo">Quantity GTN <strong style="color:red">*</strong></th>

                                                           --> 
                                                            <!-- --> 
                                                             
                                                            <th scope="col" class="font-titulo">POD /  <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">Est. Departure from POL <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">ETA REAL PORT <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo" style="background-color:#C65911">LT2 <strong style="color:white">*</strong></th>
                                                            <th scope="col" class="font-titulo" style="background-color:#C65911">ETA DC  </th>
                                                            <th scope="col" class="font-titulo" style="background-color:#C65911"> INDC +3 Days Put Away </th>
                                                                
                                                            <th scope="col" class="font-titulo">Inbound notification <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">POL <strong style="color:red">*</strong></th>	
                                                            <th scope="col" class="font-titulo">A.A. <strong style="color:red">*</strong></th>
                                                            <th scope="col" class="font-titulo">Observaciones </th>
                                                            <th scope="col" class="font-titulo"></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            
                                                            String sql2 = " WITH sum_quantity AS ("
            + "   SELECT shipment_id, container1, SUM(quantity) AS suma"
            + "   FROM tra_inc_gtn_test"
            + "   GROUP BY shipment_id, container1"
            + " )"
            + " SELECT DISTINCT"
            + "   tie.id_evento,"
            + "   NVL(bp.responsable, ' ') AS responsable,"
            + "   gtn.final_destination,"
            + "   gtn.brand_division,"
            + "   nvl(tid.division_nombre,' '), "
            + "   gtn.shipment_id,"
            + "   gtn.container1,"
            + "   gtn.bl_awb_pro,"
            + "   gtn.load_type,"
            + "   TO_CHAR(sq.suma, 'FM999G999G999G999')  ,"
            + "   tip1.NOMBRE_POD,"
            + "   TO_CHAR(gtn.est_departure_pol, 'MM/DD/YY') AS est_departure_pol,"
            + "   TO_CHAR(gtn.eta_port_discharge, 'MM/DD/YY') AS eta_real_port,"
            + "   NVL(gtn.max_flete, 0) AS est_eta_dc,"
            + "   TO_CHAR(gtn.fecha_captura, 'MM/DD/YY')  AS notification_type,"
            + "   tip2.NOMBRE_POL,"
            + "   NVL(taa.agente_aduanal_nombre, ' ') AS agente_aduanal,"
            + "   NVL(gtn.plantilla_id,0), "
            + "   ' ',"
            + "   tip1.nombre_pod,"
            + "   tip2.nombre_pol,"
            + "   tibd.nombre_bd,"
            + "   CASE"
         
            + "  WHEN EXISTS (  SELECT 1  FROM tra_inc_gtn_test WHERE container1 = gtn.container1  HAVING COUNT(DISTINCT brand_division) > 1  ) THEN 'FCL / LCL'"
            + "  WHEN EXISTS (  SELECT 1  FROM tra_inc_gtn_test WHERE container1 = gtn.container1  HAVING COUNT(DISTINCT sbu_name) > 1   ) THEN 'FCL / LCL'"
            + "  WHEN  gtn.pod IN ('2003','2012','2010') and gtn.MODE1='Truck'  THEN 'LTL'"
            + "  WHEN SUBSTR(gtn.container1, 1, 3) = 'TMW' THEN 'LTL' "
             + "     WHEN gtn.load_type = 'FCL' THEN 'FCL'"
             + "     WHEN gtn.load_type = 'LCL' THEN 'LCL'"
             + "     WHEN gtn.load_type = 'AIR' THEN 'AIR' "
             + "     WHEN gtn.load_type = 'LTL' THEN 'LTL'"
             + "     ELSE '-'"
            + "   END AS estado,"
            + "   NVL(TO_CHAR(gtn.eta_plus2, 'MM/DD/YY'), (SELECT DISTINCT ATA_CMI FROM tra_inb_rdi WHERE shipment=gtn.shipment_id AND ATA_CMI IS NOT NULL  ORDER BY 1 FETCH FIRST 1 ROW ONLY)  ) AS eta_dc,"
            + "   NVL(TO_CHAR(gtn.eta_plus, 'MM/DD/YY'), (SELECT DISTINCT ETA_CUSTOMER FROM tra_inb_rdi WHERE shipment=gtn.shipment_id AND ATA_CMI IS NOT NULL  ORDER BY 1 FETCH FIRST 1 ROW ONLY)) AS eta_dc1,"
            + "   NVL(tie.observaciones, ' ') AS observaciones,"
                + " TO_CHAR(nvl(gtn.CANTIDAD_FINAL,sq.suma) , 'FM999G999G999G999')"
            + " FROM"
            + "   tra_inb_evento tie"
            + "   INNER JOIN tra_destino_responsable bp ON bp.user_nid = tie.user_nid"
            + "   INNER JOIN tra_inc_gtn_test gtn ON gtn.plantilla_id = tie.plantilla_id"
            + "   INNER JOIN tra_inb_pod tip1 ON tip1.id_pod = gtn.pod"
            + "   INNER JOIN tra_inb_pol tip2 ON tip2.id_pol = gtn.pol"
            + "   INNER JOIN tra_inb_brand_division tibd ON tibd.id_bd = gtn.brand_division"
            + "   INNER JOIN tra_inb_agente_aduanal taa ON taa.agente_aduanal_id = tip1.agente_aduanal_id"
            + "   INNER JOIN tra_inb_division tid ON tid.id_division = gtn.sbu_name"
            + "   INNER JOIN sum_quantity sq ON sq.shipment_id = gtn.shipment_id AND sq.container1 = gtn.container1"
            + " WHERE taa.AGENTE_ADUANAL_ID IN ("+agenteAduanal+") "
            + " AND tie.ESTATUS_EVENTO in (0) "
            + " ORDER BY tie.id_evento desc ";    
                                                            if (db.doDB(sql2)) {
                                                                for (String[] row : db.getResultado()) {
                                                                    
                                                                   //#RULE 1 
                                                                   if(row[4].equals("No/DSN")){
                                                                     colors = "color: #ff0000;";
                                                                   }else{
                                                                     colors = "color: #000000;";  
                                                                   }
                                                                   
                                                                   //#RULE 2
                                                                   char container = row[6].charAt(0); 
                                                                   nomeclatura = String.valueOf(container);
                                                                           
                                                                   if(nomeclatura.equals("T") && row[8].equals("LTL")){
                                                                       soporteAA = "RECHY";
                                                                   }else{
                                                                       soporteAA = row[16];
                                                                   }
                                                        %>
                                                        <tr id="tr<%=cont%>" style="<%=colors%>">
                                                            <td class="font-numero" style="cursor: pointer" onclick="editarEvento('<%=row[0]%>')"><%=row[0]%></td>	
                                                            <td class="font-numero"><%=row[1]%></td>
                                                            <td class="font-texto"> <%=row[2]%></td>
                                                            <td class="font-texto"> <%=row[21]%></td>
                                                            <td class="font-texto"> <%=row[4]%></td>
                                                            <td class="font-texto"> <%=row[5]%></td>	
                                                            <td class="font-texto"> <%=row[6]%></td>  <!-- CONTENEDOR -->
                                                            <td class="font-texto"> <%=row[7]%></td>
                                                            <td class="font-texto"> <%=row[22]%></td>	
                                                            <td class="font-texto"> <%=row[26]%></td>
                                                            <td class="font-texto"> <%=row[19]%></td>
                                                            <td class="font-texto"> <%=row[11]%></td>	
                                                            <td class="font-texto"> <%=row[12]%></td>	
                                                            <td class="font-texto"> <%=row[13]%></td>
                                                            <td class="font-texto"> <%=row[23]%></td>
                                                            <td class="font-texto"> <%=row[24]%></td>
                                                            <td class="font-texto"> <%=row[14]%></td>
                                                            <td class="font-texto"> <%=row[20]%></td>	
                                                            <td class="font-texto"> <%=soporteAA%></td>  <!-- AGENTE ADUANAL -->
                                                            <td class="font-texto"><input type="text" style="border: none;" id="observaciones<%=cont%>" name="observaciones<%=cont%>" value="<%=row[25]%>" autocomplete="off" onkeyup="this.value = this.value.toUpperCase()"></td>
                                                            <td><center><button type="button" class="btn btn-primary" onclick="saveObservaciones('<%=row[0]%>',<%=cont%>)">Actualizar</button></center></td>
                                                        </tr>
                                                        <%   
                                                            cont++;
                                                                }
                                                            }else{
                                                        %>
                                                            <center><label>Sin Información en Sistema.</label></center>
                                                        <%
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <br>
                                            <input type="hidden" id="agenteId" name="agenteId" value="<%=agenteAduanal%>">
                                            <input type="hidden" id="numEventos" name="numEventos" value="<%=cont%>">
                                            <!-- Botones controles -->
                                            <div class="col-lg-12" style="text-align: right;">
                                                <a class="btn btn-default text-nowrap" role="button" href="Importacion/eventos.jsp">Regresar</a>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <a class="btn btn-primary text-nowrap" id="uploadBtnid" name="uploadBtnid" role="button" onclick="save()">Guardar Información</a>
                                            </div>
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
           
           function saveObservaciones(evento,position){
               let observaciones = document.getElementById("observaciones"+position).value;
               
               fetch("<%=request.getContextPath()%>/ModificarObservaciones?evento="+evento+"&observaciones=" + observaciones, {
                    method: 'POST',
                }).then(r => r.text())
                        .then(data => {
                              if(data === "true"){
                                  swal("", "Observaciones actualizadas", "success");
                                  alertclose();
                              }else{
                                  swal("", "Información no actualizada", "error");
                                  location.reload();
                              }
                        }).catch(error => console.log(error));
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