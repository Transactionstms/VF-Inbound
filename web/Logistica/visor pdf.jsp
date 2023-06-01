<%-- 
    Document   : visor pdf
    Created on : 01-jun-2023, 16:01:59
    Author     : grecendiz
--%>

<%@page import="java.io.File"%>
<%@page import="com.onest.oracle.DB"%>
<%@page import="com.onest.oracle.DBConfData"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>BandejaPlaneacion</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">

        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">

    </head>
    <%
        try {
            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));

  String rutaPDF = "D:/descargas/54281685493061389.pdf";
        
     


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
                                       
 <embed src="file:<%=rutaPDF%>" width="100%" height="800px" type="application/pdf">

                                        
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

