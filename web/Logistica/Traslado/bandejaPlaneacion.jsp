<%-- 
    Document   : bandejaPlaneacion
    Created on : 01-jun-2023, 12:26:21
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
        <title>BandejaPlaneacion</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">

        <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/css/style.default.css" id="theme-stylesheet">

    </head>
    <%
        try {
            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));


    %>
    <body>


        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <section>
                        <div class="row">
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card h-100">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h2 class="card-heading">Seleccione las fechas</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body" >
                                        <form class="col-sm-5 col-12" id="miform1" name="miform1"  >
                                            <div class="form-group" >


                                                <div class="mb-3">
                                                    <label class="form-label">Ingrese Rango de Fechas</label>
                                                    <div class="input-group datepicker-range">
                                                        <input class="form-control datepicker" type="text" required id="f1">
                                                        <span class="input-group-text">al</span>
                                                        <input class="form-control datepicker" type="text" required  id="f2">
                                                    </div>
                                                </div>
                                                <!--button-->
                                                <button type="button" class="btn btn-primary btn-lg ladda-button mb-1" 
                                                        id="uploadBtnid" name="uploadBtnid" onclick="enviarForm()" >Entrar</button>

                                            </div>

                                            <br> 
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
        <script src="../lib/inbound/conexion/connectionStatus.js" type="text/javascript"></script>
        <!-- JavaScript files-->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        
              <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
         <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.css">
        <script src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.js"></script>

        
        <script>

  function enviarForm() {
                            var input = document.getElementById("f1");
                            var valor = input.value.trim(); 
                            if (valor === "") { 
                              return false; // Evita el envío del formulario
                            }
                           
                           
                            var input2 = document.getElementById("f2");
                            var valor2 = input2.value.trim(); 
                            if (valor2 === "") { 
                              return false; // Evita el envío del formulario
                            }  
                       window.location.href = '<%=request.getContextPath()%>/Logistica/Traslado/bandejaPlaneacion2.jsp?f1='+valor+'&f2='+valor2;

                           
                         }
                                                    
           $(document).ready(function () {
                $('.datepicker').flatpickr({
                    dateFormat: 'm/d/Y',
                    onClose: function (selectedDates, dateStr, instance) {
                        instance.setDate(dateStr, true, 'm/d/Y');
                    }
                });
            });
        </script>  
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
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
