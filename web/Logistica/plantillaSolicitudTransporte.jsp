<%-- 
    Document   : ReportePO
    Created on : 13-mar-2023, 12:22:22
    Author     : grecendiz
--%>

<%@page import="com.usuario.Usuario"%>
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
        <title>Armado de Embarque</title>
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
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
        
            String where = "";
            
            String tcarga = request.getParameter("tcarga");
            String shipment = request.getParameter("shipment");
            String container = request.getParameter("container");
            String evento = request.getParameter("evento");
             Usuario root = (Usuario) ownsession.getAttribute("login.root");
                int usr = root.getId();
        //    String tcarga1 = " ";
        //   
        //        if(tcarga.equals("1")){
        //         tcarga1 = "FCL";
        //        }else  if(tcarga.equals("2")){
        //         tcarga1 = "LCL";
        //        }else if(tcarga.equals("3")){
        //         tcarga1 = "FCL / LCL";
        //        }
        //        
        //   
        //    
        // try{    if(!tcarga.equals("0"))   {
        //     where+=" and gtn.LOAD_TYPE_FINAL in('"+tcarga1+"')";
        // } 
        // }catch(Error e){}
         try{    if(!shipment.equals("0")) { where+=" and gtn.shipment_id in ("+shipment+")";}  }catch(Error e){}
         try{    if(!container.equals("0")){ where+=" and gtn.container1 in ("+container+")";}  }catch(Error e){}
         try{    if(!evento.equals("0"))   { where+=" and tie.id_evento in ("+evento+")" ;}     }catch(Error e){}

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
                                                  <h2 class="card-heading">ARMADO DE EMBARQUE</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-12">
                                                   <button class="btn btn-primary " onclick="marcarTodosLosChecks()">Marcar Todos</button>
                                            </div>
                                        </div>
                                    </div>
                                    
                                   
    <script>
        let ban=false;
        function marcarTodosLosChecks() {
            ban=!ban;
        
            if(ban){
            var checkboxes = document.querySelectorAll('table input[type="checkbox"]');
            checkboxes.forEach(function (checkbox) {
                checkbox.checked = true;
            });
        }else{
                   var checkboxes = document.querySelectorAll('table input[type="checkbox"]');
            checkboxes.forEach(function (checkbox) {
                checkbox.checked = false;
            });
                
            }
        }
    </script>
                                    <div class="card-body">
                                        <form id="form1" name="uploadFileFormData1">
                                             <br>
                                            <div id="table-scroll" class="table-scroll"  style="height: 100%;">
                                             
                                                <table   className="main-table table border">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col" class="font-titulo">Seleccionar <strong style="color:red">*</strong></th>
                                                            <th scope="col" class="font-titulo">Evento  </th>
                                                            <th scope="col" class="font-titulo">Container  </th>
                                                            <th scope="col" class="font-titulo">BL/ AWB/ PRO  </th>
                                                            <th scope="col" class="font-titulo">Shipment ID </th> 
                                                            <th scope="col" class="font-titulo">Load Type  </th>	
                                                            <th scope="col" class="font-titulo">LUM BRIO  </th>
                                                            <th scope="col" class="font-titulo">Brand </th> 
                                                            <th scope="col" class="font-titulo">Division </th>	
                                                            <th scope="col" class="font-titulo">MX Port </th>	 
                                                            <th scope="col" class="font-titulo">ETA MX Port</th>
                                                            <th scope="col" class="font-titulo"> ETA DC </th>	
                                                            
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
        +"   gtn.shipment_id,"
        +"   gtn.container1,"
        +"   gtn.bl_awb_pro,"
        +"   gtn.load_type,"
        +"   sq.suma,"
        +"   tip1.NOMBRE_POD,"
        +"   TO_CHAR(gtn.est_departure_pol, 'MM/DD/YY') AS est_departure_pol,"
        +"   TO_CHAR(gtn.eta_port_discharge, 'MM/DD/YY') AS eta_real_port,"
        +"   NVL(gtn.max_flete, 0) AS est_eta_dc,"
        +"   'Inbound notification' AS notification_type,"
        +"   tip2.NOMBRE_POL,"
        +"   NVL(taa.agente_aduanal_nombre, ' ') AS agente_aduanal,"
        +"   gtn.plantilla_id,"
        +"   TO_CHAR(gtn.fecha_captura, 'MM/DD/YY') AS fecha_captura,"
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
        +"   NVL(tie.observaciones, ' ') AS observaciones,"
        +"   gtn.LOAD_TYPE_FINAL,"
        + "   NVL(TO_CHAR(gtn.ETA_PORT_DISCHARGE, 'MM/DD/YY'), ' ')  "
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
                                                                   + " where STATUS_EMBARQUE=0 "+where
        +" ORDER BY"
        +"   tie.id_evento";
                                                           System.out.println("sql"+sql);
int x=0;
                                                            if (db.doDB(sql)) {
                                                                for (String[] row : db.getResultado()) {
                                                                    // out.println("<option value=\"" + row[0] + "\" >" + row[1] + " - " + row[2] + "</option>");
                                                                    //row[18]
                                                                    //select to_char(to_date('01/08/2023','MM/DD/YYYY')+1, 'DAY', 'NLS_DATE_LANGUAGE=SPANISH') from dual
                                                        %>
 
                                                     <tr>
                                                         <td class="font-numero" > 
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="checkbox" value=" shipment_id='<%=row[5]%>' and container1='<%=row[6]%>'" id="flexCheckDefault<%=row[0]%>" name="valorCheck"><!-- evento=<%=row[0]%> and gtn.shipment_id='<%=row[5]%>' and gtn.container1='<%=row[6]%> and gtn.LOAD_TYPE_FINAL='<%=row[26]%>'  -->
                                                                    <label class="form-check-label" for="flexCheckDefault<%=row[0]%>">
                                                                       Agregar
                                                                    </label>
                                                                </div>
                                                            </td>
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
                                                           
                                                        </tr>

                                                        
                                                       <%
                                                           x++;}

                                                            }
                                                       %>
                                                    </tbody>
                                                </table>
                                             </div>
                                            <br>
                                            <!-- Botones controles -->
                                           <br>
                                            <!-- Botones controles -->
                                            <div class="col-lg-12" style="text-align: right;">
                                                
                                                <a class="btn btn-primary text-nowrap" id="uploadBtnid" name="uploadBtnid" role="button" onclick="nuevoEvento()">Guardar </a>
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



function nuevoEvento5() {
    let checkboxes = document.getElementsByName("valorCheck"); // Reemplaza "nombre_del_checkbox" con el nombre real de tus checkboxes
    let cont = 0; // Variable que lleva la cuenta de los checkbox pulsados
    let valores = '';

    console.log(checkboxes);

    for (var x = 0; x < checkboxes.length; x++) {
        if (checkboxes[x].checked) {
            console.log(checkboxes[x].value)
            valores += checkboxes[x].value + ' or  ';
            cont = cont + 1;
        }
    }

    valores = valores.slice(0, -4);
    console.log(valores);
}

  async function nuevoEvento() {
             swal({  title:  "Guardando. . .",    allowEscapeKey:false  });
                         let checkboxes = document.getElementsByName("valorCheck"); //Array que contiene los checkbox
                        let cont = 0; //Variable que lleva la cuenta de los checkbox pulsados
                        let valores = '';
                        for (var x = 0; x < checkboxes.length; x++) {
                            if (checkboxes[x].checked) {
                                console.log(checkboxes[x].value)
                                valores += checkboxes[x].value + ' or  ';
                                cont = cont + 1;
                            }
                        }
                        
                         valores = valores.slice(0, -4); 
                         console.log(valores);
               let fool=Date.now();
                 console.log(fool);
                         
                      
  try {
    const response = await fetch("<%=request.getContextPath()%>/ModificarTransito?status=1&op=<%=usr%>"+fool+"&shipme="+valores );
    
    if (!response.ok) {
      throw new Error('Error en la solicitud');
    }
    
    const data = await response.text();
    window.location.href =  '<%=request.getContextPath()%>/Logistica/documentosSeleccionados.jsp?op=<%=usr%>'+fool;
    return data;
  } catch (error) {
    console.log('Error:', error.message);
  }
}

           function nuevoEvento1() {
                        swal({  title:  "Guardando. . .",    allowEscapeKey:false  });
                        let checkboxes = document.getElementById("form1").valor; //Array que contiene los checkbox
                        let cont = 0; //Variable que lleva la cuenta de los checkbox pulsados
                        let valores = '';
                        for (var x = 0; x < checkboxes.length; x++) {
                            if (checkboxes[x].checked) {
                                console.log(checkboxes[x].value)
                                valores += checkboxes[x].value + ' or  ';
                                cont = cont + 1;
                            }
                        }


                         valores = valores.slice(0, -4); 
                         console.log(valores);
                         
                      window.location.href =  '<%=request.getContextPath()%>/Logistica/documentosSeleccionados.jsp?op='+valores;

                                   }     
                                   
                                   
                                   
                                   
                                   
                                   
                                   
                                   
        </script>                     
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