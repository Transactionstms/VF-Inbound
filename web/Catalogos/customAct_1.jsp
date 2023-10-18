<%-- 
    Document   : customAct_1
    Created on : 21-sep-2023, 15:24:03
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

<!DOCTYPE html>
<html lang="esp">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
   
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <!--  Datatables  -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.20/datatables.min.css"/>  
    
    <!-- searchPanes -->
    <link rel="stylesheet" href="https://cdn.datatables.net/searchpanes/1.0.1/css/searchPanes.dataTables.min.css">
    <!-- select -->
    <link href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">
    <style>
	table thead{
	background: linear-gradient(to right, #4A00E0, #8E2DE2); 
	color:white;
	}
    </style>
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
         + "   CASE"
            + "     WHEN gtn.load_type = 'LTL' THEN 'LTL'"
            + "     WHEN EXISTS ("
            + "       SELECT 1"
            + "       FROM tra_inc_gtn_test"
            + "       WHERE container1 = gtn.container1"
            + "       HAVING COUNT(DISTINCT brand_division) > 1"
            + "          and COUNT(DISTINCT gtn.sbu_name)   > 1 "//
            + "     ) THEN 'FCL / LCL'"
            + "  WHEN EXISTS (  SELECT 1  FROM   tra_inc_gtn_test   WHERE  container1 = gtn.container1  HAVING COUNT(DISTINCT sbu_name) > 1   ) THEN 'FCL / LCL'"
            + "  WHEN  gtn.pod IN ('2003','2012','2010') and gtn.MODE1='Truck'  THEN 'LTL'"
            + "  WHEN SUBSTR(gtn.container1, 1, 3) = 'TMW'   THEN 'LTL' "
            + "     WHEN gtn.load_type = 'FCL' THEN 'FCL'"
            + "     WHEN gtn.load_type = 'LCL' THEN 'LCL'"
            + "     WHEN gtn.load_type = 'AIR' THEN 'AIR' "
            + "     ELSE '-'"
        +"   END AS estado,"
        +"   NVL(TO_CHAR(gtn.eta_plus2, 'MM/DD/YY'), ' ') AS eta_dc,"
        +"   NVL(TO_CHAR(gtn.eta_plus, 'MM/DD/YY'), ' ') AS eta_dc1,"
        +"   NVL(tie.observaciones, ' ') AS observaciones"
          + " ,nvl(gtn.CANTIDAD_FINAL,sq.suma)"
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
    
    <div class="container">
        <div class="row">
            <div class="col">
            <table id="example" class="table table-bordered  display nowrap" cellspacing="0" width="100%">
                
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
        </div>
    </div>


    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
            
    <!--   Datatables-->
    <script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.20/datatables.min.js"></script>  

    <!-- searchPanes   -->
    <script src="https://cdn.datatables.net/searchpanes/1.0.1/js/dataTables.searchPanes.min.js"></script>
    <!-- select -->
    <script src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js"></script>  
    
    <script>
    $(document).ready(function(){
        $('#example').DataTable({
                searchPanes:{
                    cascadePanes:true,
                    dtOpts:{
                        dom:'tp',
                        paging:'true',
                        pagingType:'simple',
                        searching:false
                    }
                },
                dom:'Pfrtip',
                columnDefs:[{
                    searchPanes:{
                        show:false
                    },
                    targets:[5]
                }
                ]
        });

    });
    </script>
<%
        } catch (NullPointerException e) {
            out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            out.println("<script>window.close();</script>");
        } catch (Exception e) {
            out.println("Excepcion revise por favor! " + e);
            e.printStackTrace();
        }
    %>
</body>
</html>
 

   
