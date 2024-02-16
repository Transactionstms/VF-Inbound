<%-- 
    Document   : cambiarEmbarque
    Created on : 16-feb-2024, 16:00:06
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
        <title>Busqueda Embarque</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">

        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>


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
                                                <h2 class="card-heading"> EMBARQUE EN ALMACEN</h2>
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
                                                <button type="button" class="btn btn-success text-nowrap" id="uploadBtnid" name="uploadBtnid" role="button" onclick="save()">En Almacen</button>

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

        <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>

        <!-- Conexión estatus red -->                    
        <script src="../lib/inbound/conexion/connectionStatus.js" type="text/javascript"></script>
        <!-- JavaScript files-->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script>
                                                    //
                                                    function save() {
                                                        swal("Espere...!");
                                                        const id = document.getElementById('emb').value;
                                                        if (id == '') {
                                                            swal("Ingresa Embarque...!");
                                                            return false;
                                                        }

                                                        fetch('../EnalmacenEmbarque?id=' + id)
                                                                .then(response => {
                                                                    if (!response.ok) {

                                                                        throw new Error('Network response was not ok');
                                                                    }

                                                                    return response.text(); // Parsea la respuesta JSON
                                                                })
                                                                .then(data => {
                                                                    // Hacer algo con los datos recibidos
                                                                    console.log(data);
                                                                    swal(data);
                                                                })
                                                                .catch(error => {
                                                                    console.error('There was a problem with the fetch operation:', error);
                                                                });
                                                    }
                                                    async function save1() {

                                                        const id = document.getElementById('emb').value;

                                                        if (id == '') {
                                                            swal("Ingresa Embarque...!");
                                                            return false;
                                                        }

                                                        swal("Espere...!");
                                                        console.log(id);
                                                        try {
                                                            const data = await fetchData('../EnalmacenEmbarque?id=' + id);

                                                            swal(data);

                                                            alertclose();
                                                        } catch (error) {
                                                        }
                                                    }
        </script>  
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
    </body>
    <%         } catch (NullPointerException e) {
            // out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            //  out.println("<script>window.close();</script>");
        } catch (Exception e) {
            out.println("Excepcion revise por favor! " + e);
            e.printStackTrace();
        }
    %>
</html>
