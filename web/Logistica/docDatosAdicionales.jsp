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
        <title>Datos Adicionales</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">
        <!-- Google fonts - Popppins for copy-->
 
         <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
      
    </head>
       <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
 



           String transportista = "";
           String sbuSQL = "select LTRANSPORTE_ID, LTRANSPORTE_NOMBRE from tra_inb_linea_transporte";
                if (db.doDB(sbuSQL)) {
                     for (String[] row : db.getResultado()) {
                        transportista += "<option value='" + row[0] + "' >" + row[1] + "</option>";
                  }
                }
                
            String custodia = "";
            String sbuSQL2 = "select ID_TC, TC_DESCRIPCION from tra_inb_tipo_custodia";
                if (db.doDB(sbuSQL2)) {
                     for (String[] row : db.getResultado()) {
                        custodia += "<option value='" + row[0] + "' >" + row[1] + "</option>";
                  }
                }
                     
  String opciones = request.getParameter("op"); 
            
       
       %>

    <body>
  
     
        <!-- navbar-->
        <header class="header"></header>

        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <section>
                        <div class="row">
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card h-100">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <h2 class="card-heading">Datos Adicionales</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body col-md-12">
                                        <form class="row " id="" name="" >
                                            <div class="container">
                                                <div class="row">
                                                    
                                                    <div class="col-md-6 mb-4">
                                                        <label class="form-label" >Transportista</label>
                                                        <select class="form-select" id="tranporte" >
                                                            <option selected>Elija una opcion</option>
                                                            <%=transportista%>
                                                        </select>
                                                    </div>

                                                    <div class="col-md-3 mb-4">
                                                        <label class="form-label" >Fecha de enrampe</label>
                                                        <div class="input-group date" id="datepicker">
                                                            <input type="text" name="f_enrampe" id="f_enrampe" class="datepicker form-control"  autocomplete="off" size="10" required="required" >                                                        </div>
                                                    </div>

                                                    <div class="col-md-3 mb-4">
                                                        <label class="form-label" >Fecha inicio de entrega</label>
                                                        <div class="input-group date" id="datepicker">
                                                            <input type="text" name="f_inicio" id="f_inicio" class="datepicker form-control"  autocomplete="off" size="10" required="required" >
                                                        </div>
                                                    </div>
                                                        
                                                    <div class="col-md-6 mb-4">
                                                        <label class="form-label" >Custodia</label>
                                                        <select class="form-select" id="custodia" >
                                                            <option selected>Elija una opcion</option>
                                                            <%=custodia%>
                                                        </select>
                                                    </div>
                                                        
                                                </div>
                                            </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                
                                                          
                                                                <table class="table table-striped" >
                                                               <thead>
                                                                 <tr>
                                                                   <th scope="col">Evento</th>
                                                                   <th scope="col">Shipment ID</th>
                                                                   <th scope="col">Container</th> 
                                                                 </tr>
                                                               </thead>
                                                               <tbody></tbody>
                                                                                                                          <%

                                                                                                                        String sql= " "
                                                                     +" SELECT DISTINCT"
                                                                     +"   tie.id_evento," 
                                                                     +"   gtn.shipment_id,"
                                                                     +"   gtn.container1,"

                                                                     +"   gtn.LOAD_TYPE_FINAL "
                                                                     +" FROM "
                                                                     +"   tra_inb_evento tie" 
                                                                     +"   INNER JOIN tra_inc_gtn_test gtn ON gtn.plantilla_id = tie.plantilla_id" 
                                                                                                                                + " where   "+opciones
                                                                     +" ORDER BY"
                                                                     +"   tie.id_evento";

                                                                                                                         if (db.doDB(sql)) {
                                                                                                                             for (String[] row : db.getResultado()) {
                                                                                                                                 // out.println("<option value=\"" + row[0] + "\" >" + row[1] + " - " + row[2] + "</option>");
                                                                                                                                 //row[18]
                                                                                                                                 //select to_char(to_date('01/08/2023','MM/DD/YYYY')+1, 'DAY', 'NLS_DATE_LANGUAGE=SPANISH') from dual
                                                                                                                     %>


                                                                 <tr>
                                                                   <th scope="row"><%=row[0]%></th>
                                                                   <td><%=row[1]%></td>
                                                                   <td><%=row[2]%></td> 
                                                                 </tr>




                                                                                                                       <% }

                                                                                                                         }
                                                                                                                    %>
                                                                                       </tbody>
                                                                                     </table>                              
                              
                          
                                                        </div>
                                                               </div>
                          
                          
                                    </div>

                                    <!--button-->
                                    <div class="text-center">
                                        <button class="btn btn-primary mb-5 col-3" type="button"id="uploadBtnid" name="uploadBtnid" role="button" onclick="insertaEmbarque()">Grabar embarque</button>
                                    </div>


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

         <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.css">
   <script src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.js"></script>

        
<script>
    
    function insertaEmbarque(){
        let fecha =document.getElementById('f_enrampe').value;
        console.log(fecha);
        
    }
    
    
           $(document).ready(function () {
                $('.datepicker').flatpickr({
                    dateFormat: 'm/d/Y H:i', // Agrega el formato de hora 'H:i'
                    enableTime: true, // Habilita la selección de tiempo
                    time_24hr: true, // Utiliza el formato de 24 horas
                    onClose: function (selectedDates, dateStr, instance) {
                        instance.setDate(dateStr, true, 'm/d/Y H:i');
                    }
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
