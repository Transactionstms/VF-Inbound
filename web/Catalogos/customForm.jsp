<%-- 
    Document   : customForm
    Created on : 27/02/2023, 11:55:23 AM
    Author     : Desarrollo Tacts
--%>

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
        <title>Personalizar Evento</title>
        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
        <link rel="stylesheet" href="../lib/css/custom.css">
        <script src="lib/JSplantilla.js" type="text/javascript"></script>
        <!-- Table css -->
        <link href="../lib/validationsInbound/customs/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- Multiselect -->
        <link href="../lib/Multiselect/css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>
        <!-- Plantilla -->
        <script src="../plantillas/lib/JSplantilla.js" type="text/javascript"></script>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- jQuery/show modal -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <style>
            #contenedor {
              display: flex;
              flex-direction: row;
              flex-wrap: wrap;
            }

            #contenedor > div {
              width: 50%;
            }

            .columna {
              width:25%;
              float:right;
            }

            @media (max-width: 500px) {
              .columna {
                width:auto;
                float:none;
              }
            }
            
            #buscador {
              display: flex;
              flex-direction: row;
              flex-wrap: wrap;
            }
            
            #primero {
              width: 90%;
            }
            
            #segundo {
              width: 10%;
            }
        </style>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String view = request.getParameter("view");
                String UserId = (String) ownsession.getAttribute("login.user_id_number");
                String idDivision = ownsession.getAttribute("cbdivcuenta").toString();
                String idBodega = ownsession.getAttribute("cbbodegaId").toString();
                Usuario root = (Usuario) ownsession.getAttribute("login.root");
                ConsultasQuery fac = new ConsultasQuery();
                String tipoAgente = ""; // (4001)LOGIX       (4002)CUSA       (4006)VF
                String idPlantilla = "";
                String namePlantilla = "";
                
                //Obtener el agente aduanal, id plantilla y nombre plantilla del usuario: 
                String sql = "SELECT DISTINCT TIAR.AGENTE_ADUANAL_ID, TIAR.TRA_PLANTILLA_ID, TP.NOMBRE FROM TRA_INB_USUARIO_AA_RELACION TIAR INNER JOIN TRA_PLANTILLA TP ON TIAR.TRA_PLANTILLA_ID = TP.ID WHERE TIAR.USER_NID = '" + UserId + "'";
                if (db.doDB(sql)) {
                    for (String[] row : db.getResultado()) {
                        tipoAgente = row[0]; 
                        idPlantilla = row[1];
                        namePlantilla = row[2];
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
                                                <h2 class="card-heading">Personalizar Evento</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <input type="hidden" id="idAgenteAduanal" name="idAgenteAduanal" value="<%=tipoAgente%>">
                                        <div id="contenedor">
                                            <div class="row">
                                                <div class="columna">
                                                    <button type="button" class="btn btn-success" onclick="openModalPlantilla()">Subir Plantilla</button>
                                                </div>
                                            </div>       
                                            <div style="text-align: right;">
                                                <!--<a class="btn btn-default text-nowrap" role="button" href="Importacion/eventos.jsp">Regresar</a>-->
                                                <a class="btn btn-primary text-nowrap" id="uploadBtnid" name="uploadBtnid" role="button" onclick="AddCustoms()">Guardar Información</a>
                                            </div>
                                        </div>
                                        <br>
                                        <div id="table-scroll" class="table-scroll"  style="height:500px;"></div>
                                        <br>
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
        <!-- modal - Subir Plantilla --> 
        <div class="modal fade text-start" id="modalSubirPlantilla" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header border-0 bg-gray-100">
                        <h3 class="h6 modal-title" id="exampleModalLabel"><i class="fas fa-folder-open"></i>&nbsp;<%=namePlantilla%></h3>
                        <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
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
                                                <div class="col-lg-3"></div> 
                                                <div class="col-md-6 col-lg-11 mb-4">
                                                    <div class="card h-100">
                                                        <div class="card-header py-4">
                                                            <!--<h6 class="card-heading">nombre</h6>-->
                                                        </div>
                                                        <form action="<%=request.getContextPath()%>/Importacion/subirExcel.jsp?nombre=<%=namePlantilla%>.xls&idp=<%=idPlantilla%>" id="gfichero" method = "post" enctype="multipart/form-data"  >
                                                            <div class="card-body pt-3">
                                                                <div class="mb-3">
                                                                    <label for="input-id" class="form-label">Selecciona: </label>
                                                                    <input class="form-control" type="file" id="input-id" name="input-id"
                                                                           accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
                                                                </div>
                                                                <div class="row position-relative" style="top: 10px;">
                                                                    <div class="col-6 text-center">
                                                                        <button class="btn float-start btn-primary" id="created_file" type="button" >Descargar</button>
                                                                    </div>
                                                                    <div class="col-6 text-center">
                                                                        <button class="btn float-end btn-success"  type="submit"  >Subir</button><!--id="upload_file"-->
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                                <div align="center" class="col-md-8">
                                                    <div align="center" id="divResultado" name="divResultado"></div>
                                                </div>
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
                    </div>
                </div>
            </div>
        </div>                                        
        <!-- JavaScript files-->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Actions js -->
        <script src="../lib/validationsInbound/customs/customsForms.js" type="text/javascript"></script>
        <!-- Multiselect -->
        <script src="../lib/Multiselect/js/bootstrap-select.min.js" type="text/javascript"></script>
        <!-- Plantilla js -->
        <script src="<%=request.getContextPath()%>/plantillas/lib/upload_file.js" type="text/javascript"></script>
        <!-- Sweetalert -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    </body>
    <%
        } catch (NullPointerException e) {
            out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            out.println("<script>window.close();</script>");
        } catch (Exception e) {
            out.println("Excepcion revise por favor! " + e);
            e.printStackTrace();
        }
    %>
</html>
