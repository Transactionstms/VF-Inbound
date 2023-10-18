<%-- 
    Document   : customForm_1
    Created on : 27/02/2023, 11:55:23 AM
    Author     : Desarrollo Tacts
--%>

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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" sizes="16x16" href="../plugins/images/favicon.png">
        <title>Personalizar Customs</title>
        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
        <!-- jQuery 3.6.0 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!-- Window load -->
        <link href="../lib/Loader/css/windowsLoad.css" rel="stylesheet" type="text/css"/>
        <!-- scroll iFrame -->
        <link href="../lib/validationsInbound/customs/styleEvents.css" rel="stylesheet" type="text/css"/>
        <script>
            window.onload = (event) => {
                jsShowWindowLoad('Cargando Información');
                onloadIframe();
            };
        </script>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
                OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
                String UserId = (String) ownsession.getAttribute("login.user_id_number");
                String filterType = request.getParameter("filterType");
                String id = request.getParameter("id");
                String AgentType = "";

                ConsultasQuery fac = new ConsultasQuery();
   
                //Obtener el agente aduanal, id plantilla y nombre plantilla del usuario: 
                if (db.doDB(fac.consultarAgenteAduanalCustoms(UserId))) {
                    for (String[] rowA : db.getResultado()) {
                        AgentType = rowA[0];
                    }
                }
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
                                                <h2 class="card-heading">Personalizar Customs</h2>
                                            </div>
                                        </div>
                                    </div>   

                                    <div class="card-body">
                                        <p align="center">
                                            <iframe id="frameTableCustoms" class="iFrame" title="Data Table Customs" style=" width:100%; height: 4000px"></iframe>
                                        </p>
                                        <input type="hidden" id="filterType" name="filterType" value="<%=filterType%>">
                                        <input type="hidden" id="id" name="id" value="<%=id%>">
                                        <input type="hidden" id="idAgenteAduanal" name="idAgenteAduanal" value="<%=AgentType%>">
                                    </div>

                                    
                               
                                </div>
                            </div>
                        </div>   
                    </section>
                </div>   
            </div>
        </div> 
        <script>
            function onloadIframe() {

                let filterType = document.getElementById("filterType").value;
                let id = document.getElementById("id").value;
                let agenteId = document.getElementById("idAgenteAduanal").value;

                document.getElementById('frameTableCustoms').src = "iframeDataCustom_1.jsp?filterType=" + filterType + "&id=" + id;

                /*fetch("<%=request.getContextPath()%>/UpdateSemaforoCustoms?agenteAduanal="+agenteId, {
                 method: 'POST',
                 }).then(r => r.text())
                 .then(data => {
                 if(data === "true"){
                 console.log(data);
                 }else{
                 console.log(data);
                 }
                 }).catch(error => console.log(error));*/

                $('#frameTableCustoms').on("load", function () {
                    $("#WindowLoad").remove();
                });

                //email(agenteId);    
            }

            function recibirParametroIFrame(event) {

                // Obtener el parámetro enviado desde el iframe
                var parametro = event.data;

                // Realizar las acciones necesarias con el parámetro
                let value = parametro.split("@");

                document.getElementById("filterType").value = value[0];
                document.getElementById("id").value = value[1];

                jsShowWindowLoad('Cargando Información');
                onloadIframe();

            }

            // Agregar un listener para escuchar los mensajes enviados desde el iframe
            window.addEventListener('message', recibirParametroIFrame, false);
        </script>
        <!-- Window load -->
        <script src="../lib/Loader/js/windowLoad.js" type="text/javascript"></script>
        <!-- JavaScript files -->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
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
