<%-- 
    Document   : registrarPol
    Created on : 23/05/2024, 12:59:41 PM
    Author     : User_Windows10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.onest.train.consultas.ConsultasQuery"%>
<%@page import="com.onest.security.menu.*" %>
<%@page import="com.onest.net.*" %>
<%@page import="com.onest.oracle.*" %>
<%@page import="com.onest.misc.*" %>
<%@page import="com.usuario.Usuario"%>
<%
    Date date = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    String fecha = formato.format(date);
    String[] par = fecha.split("/");
    String part3 = par[2];
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" sizes="16x16" href="<%=request.getContextPath()%>/plugins/images/favicon.png">
        <title>Registrar POL</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/css/style.default.css" id="theme-stylesheet">
        <!-- jQuery 3.6.0 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!-- sweetalert -->
        <link href="<%=request.getContextPath()%>/lib/SweetAlert1.1.3/css/sweetalert.min.css" rel="stylesheet" type="text/css"/>
        <!-- formulario -->
        <link href="../lib/validationsInbound/catalogos/registrar_pol.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String id_pol = "";

                ConsultasQuery fac = new ConsultasQuery();

                if (db.doDB(fac.consultarIdPol())) {
                    for (String[] rowA : db.getResultado()) {
                        id_pol = rowA[0];
                    }
                }
        %>
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
                                                <h2 class="card-heading">Registrar POL</h2>
                                                <br>
                                                <u>(Seleccione 'Fijar' unicamente para igual al POL de la lista)</u>
                                            </div>
                                        </div>
                                    </div>                                    
                                    <div class="card-body">
                                        <div class="row" id="flotante_search">
                                            <div class="contenedor">
                                                <div class="columna1">
                                                    <input class="form-control" type="text" id="producto_search" name="producto_search" autocomplete="off" onkeyup="autocompleteSearch(this.value);"> 
                                                </div>
                                            </div>
                                            <div class="row">
                                                <center><input type="checkbox" id="checkbox1" name="checkbox1" onclick="txt();"> Fijar</center>
                                            </div>
                                        </div>
                                        <div class="row" id="flotante_text" style="display:none;">
                                            <div class="contenedor">
                                                <div class="columna1">
                                                    <input class="form-control" type="text" id="producto_txt" name="producto_txt" autocomplete="off">
                                                </div>
                                            </div>
                                            <div class="row">
                                                <center><input type="checkbox" id="checkbox2" name="checkbox2" onclick="search();"> No fijar</center>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="scroll-container" id="list"></div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <button class="btn btn-success" style="width:200px;" id="btn" name="btn" onclick="insertarPOL()">REGISTRAR</button>
                                        </div>
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
            document.getElementById("btn").disabled = true;
            let tipo_consulta = 0;
            let id_pol = '<%=id_pol%>';

            function txt() {
                div = document.getElementById('flotante_text');
                div.style.display = '';
                div = document.getElementById('flotante_search');
                div.style.display = 'none';

                let txt = document.getElementById("producto_search").value;
                document.getElementById("producto_txt").value = txt;
                document.getElementById("btn").disabled = false;
                document.getElementById("checkbox2").checked = true;
                tipo_consulta = 1;
            }

            function search() {
                div = document.getElementById('flotante_search');
                div.style.display = '';
                div = document.getElementById('flotante_text');
                div.style.display = 'none';

                let search = document.getElementById("producto_txt").value;
                document.getElementById("producto_search").value = search;
                document.getElementById("btn").disabled = true;
                document.getElementById("checkbox1").checked = false;
                tipo_consulta = 2;
                autocompleteSearch(search);
            }

            function autocompleteSearch(txt) {
                fetch("<%=request.getContextPath()%>/AutocompletePOL?txt=" + txt, {
                    method: 'POST'
                }).then(r => r.text())
                        .then(data => {
                            if (data === "0") {
                                document.getElementById("btn").disabled = false;
                                document.getElementById("list").innerHTML = "Sin coincidencias";
                            } else {
                                document.getElementById("btn").disabled = true;
                                document.getElementById("list").innerHTML = data;
                            }

                        }).catch(error => console.log(error));
            }

            function insertarPOL() {

                let search = document.getElementById("producto_search").value;
                let txt = document.getElementById("producto_txt").value;

                if (tipo_consulta === 0) {
                    pol(search);
                } else if (tipo_consulta === 1) {
                    pol(txt);
                } else if (tipo_consulta === 2) {
                    pol(search);
                }

            }

            function pol(value) {
                fetch("<%=request.getContextPath()%>/InsertarPOL?nombre_pol=" + value + "&id_pol=" + id_pol, {
                    method: 'POST'
                }).then(r => r.text())
                        .then(data => {
                            if (data === "true") {
                                swal("POL Registrado");
                            } else {
                                swal("POL no Registrado");
                            }

                            alertclose();

                        }).catch(error => console.log(error));
            }

            function position(contador) {
                id_pol = document.getElementById("pol" + contador).value;
            }

            function alertclose() {
                setTimeout(function () {
                    location.reload();
                }, 2000);
            }
        </script>   
        <!-- JavaScript files -->
        <script src="<%=request.getContextPath()%>/lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Sweetalert -->
        <script src="<%=request.getContextPath()%>/lib/SweetAlert1.1.3/js/sweetalert.min.js" type="text/javascript"></script>
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    </body>
    <%
        } catch (NullPointerException e) {
            System.out.println("Error:" + e);
            out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            out.println("<script>window.close();</script>");
        } catch (Exception e) {
            out.println("Excepcion revise por favor! " + e);
            e.printStackTrace();
        }
    %>
</html>

