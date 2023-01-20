<%@page import="com.pantalla.BLPantalla"%>
<%@page import="com.pantalla.Pantalla"%>
<%@page import="com.usuario.Usuario"%>
<%@page import="com.usuario.Usuario"%>
<%@page import="com.onest.train.consultas.Consulta"%>
<%@page contentType="text/html;charset=iso-8859-1"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ page import="com.onest.oracle.*" %>
<%@ page import="com.onest.misc.*" %>
<%@ page import="com.onest.net.*" %>
<!DOCTYPE html>  
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" sizes="16x16" href="../plugins/images/favicon.png">
        <title>TransactionsTMS</title>
        <!-- Bootstrap Core CSS
         <link rel="stylesheet" href="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.css">
    <link rel="stylesheet" href="../lib/vendor/prismjs/themes/prism-okaidia.css">
        -->


        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
        <link rel="stylesheet" href="../lib/css/custom.css">
        <script src="lib/JSplantilla.js" type="text/javascript"></script>


    </head>
    <body class="fix-sidebar">
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String idPlantilla = request.getParameter("idPlantilla");
                String idDivision = ownsession.getAttribute("cbdivcuenta").toString();
                String idBodega = ownsession.getAttribute("cbbodegaId").toString();
                Usuario root = (Usuario) ownsession.getAttribute("login.root");
                int usr = root.getId();

                String nombre = "";
                if (db.doDB("select NOMBRE from TRA_PLANTILLA where id='" + idPlantilla + "' ")) {
                    for (String[] row : db.getResultado()) {
                        nombre = row[0];
                    }
                }
        %>

        <!-- Preloader -->



        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <!-- Page Header-->
                    <div class="page-header">
                        <h1 >PLANTILLAS</h1>
                    </div>

                    <section>
                        <div class="card mb-4">
                            <div class="card-header">
                                <div class="card-heading">*Descarga el archivo, llena los datos y sube tu documento</div>
                            </div>
                            <div class="card-body text-muted">
                                <!-- Selección de Clientes -->
                                <div class="row">


                                    <div class="col-lg-12">
                                        <div class="mb-3">  
                                            <div class="row">

                                                <div class="col-lg-3">
                                                </div> 

                                                <div class="col-md-6 col-lg-3 mb-4">
                                                    <div class="card h-100">
                                                        <div class="card-header py-4">
                                                            <h6 class="card-heading"><%=nombre%>                </h6>
                                                        </div>





                                                        <div class="card-body pt-3">

                                                            <div class="mb-3">
                                                                <label for="input-id" class="form-label">Selecciona </label>
                                                                <input class="form-control" type="file" id="input-id"
                                                                       accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
                                                            </div>


                                                            <div class="row position-relative" style="top: 10px;">
                                                                <div class="col-6 text-center">

                                                                    <button class="btn float-start btn-primary" id="created_file"  >Descargar</button>
                                                                </div>
                                                                <div class="col-6 text-center">
                                                                    <button class="btn float-end btn-success"  id="upload_file"   >Subir</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
 
                                                <div align="center" class="col-md-8">
                                                    <div align="center" id="divResultado" name="divResultado"></div>
                                                </div>
<!--<%=usr%>-->

                                                <input type="hidden" name="idPlantilla" value="<%=idPlantilla%>" id="idPlantilla"/>
                                                <input type="hidden" name="idOpcion" value="1" id="idOpcion"/>
                                                <input type="hidden" name="idLenguaje" value="1" id="idLenguaje"/>
                                                <input type="hidden" name="idDivision" value="<%=idDivision%>" id="idDivision"/>
                                                <input type="hidden" name="idBodega" value="<%=idBodega%>" id="idBodega"/>
                                                <input type="hidden" name="idAction" value="<%=request.getContextPath()%>/plantillaExcel" id="idAction"/>
                                                <img src="../img/loadingCloud.gif" id="idClouding" width="50px" height="50px" name="idClouding" title="Clouding" style="display: none; height: 50px; width: 50px;"/>
 
                                            </div> 
                                        </div>
                                    </div>
                                </div>  
                            </div>
                        </div> 
                    </section>
                </div> 
            </div>
        </div> 
    </div>
 

    <!--
    <script src="<%=request.getContextPath()%>/plantillas/lib/fileinput.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/plantillas/lib/fileinput/locales/es.js" type="text/javascript"></script>-->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
      <script src="<%=request.getContextPath()%>/plantillas/lib/upload_file.js" type="text/javascript"></script>

 </body>



<%                } catch (NullPointerException e) {
        out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
        out.println("<script>window.close();</script>");
        out.println(e);
    } catch (Exception e) {
        out.println("Excepcion revise por favor! " + e);
        e.printStackTrace();
    }
%>
</html>