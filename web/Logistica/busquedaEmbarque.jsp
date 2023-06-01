 

 
<%@page import="com.onest.oracle.DB"%>
<%@page import="com.onest.oracle.DBConfData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Busqueda Embarque</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">
  
        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
       
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
                                                <h2 class="card-heading">BUSQUEDA EMBARQUE</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body " style="auto">
                                        <form class="col-sm-5 col-4" id="" name="" >
                                            <div class="form-group" >

                                                
                                                <div class="mb-4">
                                                    <label class="form-label">Número de embarque</label>
                                                    <input type="text" class="form-control" id="emb" autocomplete="false">
                                                </div>
                                                <!--button-->
                                                <button type="button" class="btn btn-primary text-nowrap" id="uploadBtnid" name="uploadBtnid" role="button" onclick="save()">Entrar</button>

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
   <script>

          
           function save() {
               let emb=document.getElementById('emb').value;
               if(emb ==='' || emb ==''){
                   document.getElementById('emb').focus();
                   document.getElementById('emb').style.border = '1px solid red';
                   console.log('doc')
                   return;
               }else{
               window.location.href =  "<%=request.getContextPath()%>/Logistica/subirFactura.jsp?op="+emb.trim();
               }
                    
                       }      
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
