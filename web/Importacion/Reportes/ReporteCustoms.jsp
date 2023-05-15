<%-- 
    Document   : ReporteCustoms
    Created on : 12/05/2023, 03:06:29 PM
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
        <title>Reporte Customs</title>
        <link rel="stylesheet" href="../../lib/css/style.default.css" id="theme-stylesheet">
        <link rel="stylesheet" href="../../lib/css/custom.css">
        <!-- Table css -->
        <link href="../../lib/validationsInbound/customs/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- Multiselect -->
        <link href="../../lib/Multiselect/css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>
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
                                                <h2 class="card-heading">Reporte Customs</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <input type="hidden" id="idAgenteAduanal" name="idAgenteAduanal" value="<%=tipoAgente%>">
                                        <div id="table-scroll" class="table-scroll"  style="height:500px;">
                                            <table id="main-table" class="main-table" style="table-layout:fixed; width:1000%;">
                                                <thead align="center">
                                                    <tr>
                                                        <th class="col-sm-1" class="font-titulo" style="background-color:#8BC4C4"></th>  
                                                        <th class="col-sm-3" class="font-titulo" style="text-align:left"><font size="2">Número de evento <strong style="color:red">*</strong></font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="2">Referencia AA</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="2">Responsable</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="1">Final Destination</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="2">Brand-Division</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="2">Division</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="2">Shipment ID</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="2">Container</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="2">BL/AWB/PRO</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="2">LoadType</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="2">Quantity</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="2">POD</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="1">Est. Departure from POL</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="1">ETA REAL Port of Discharge</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="2">Est. Eta DC</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="1">Inbound notification</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="2">POL</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="2">A.A.</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="2">Fecha Mes de Venta</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4;"><font size="2">Prioridad Si/No</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">País Origen</font></th>	
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Size Container</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Valor USD</font></th>	
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">ETA Port Of Discharge</font></th>	
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Agente Aduanal</font></th>	
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Pedimento A1</font></th>	
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Pedimento R1</font></th>	
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Motivo rectificación 1</font></th>	
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Pedimento R1 (2do)</font></th>	
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Motivo rectificación 2</font></th>	
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Recepción Documentos</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#FF4040"><font size="2">Recinto</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#FF4040"><font size="2">Naviera / Forwarder</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#FF4040"><font size="2">Buque</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Revalidación/Liberación de BL</font></th>	
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Previo Origen</font></th>	
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Previo en destino</font></th>	
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Resultado Previo</font></th>	
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Proforma Final</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Requiere permiso</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha envío Fichas/notas</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fec. Recepción de permisos tramit.</font></th>	
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fec. Act Permisos (Inic Vigencia)</font></th>	
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fec. Perm. Aut. (Fin de Vigencia)</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Cuenta con CO para aplicar preferencia Arancelaria</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Aplico Preferencia Arancelaria</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Requiere UVA</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#626567"><font size="2">Requiere CA</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#626567"><font size="2">Fecha Recepción CA</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#626567"><font size="2">Número de Constancia CA</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#626567"><font size="2">Monto CA</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Documentos Completos</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Pago Pedimento</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Solicitud de transporte</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Modulacion</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Modalidad</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Resultado Modulacion</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Reconocimiento</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha Liberacion</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Sello Origen</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Sello Final</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fecha de retencion por la autoridad</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Fec. de liberacion por ret. de la aut.</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Estatus de la operación</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Motivo Atraso</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#00BFBF"><font size="2">Observaciones</font></th>
                                                    <%
                                                        if(tipoAgente.equals("4001")||tipoAgente.equals("4006")){ //Logix ó VF
                                                    %>    
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Llegada a NOVA</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Llegada a Globe trade SD</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Archivo M</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Fecha de Archivo M</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Fecha Solicitud de Manipulacion</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Fecha de vencimiento de Manipulacion</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Fecha confirmacion Clave de Pedimento</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Fecha de Recepcion de Incrementables</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">T&E</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Fecha de Vencimiento del Inbound</font></th>
                                                    <%
                                                        }

                                                        if(tipoAgente.equals("4002")||tipoAgente.equals("4006")){  //Cusa ó VF
                                                    %>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">No. BULTOS</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Peso (KG)</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Transferencia (SI / NO)</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Fecha Inicio Etiquetado</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Fecha Termino Etiquetado</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Hora de termino Etiquetado</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Proveedor</font></th>
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">Proveedor de Carga</font></th>
                                                    <%
                                                        }
                                                    %> 
                                                        <th class="col-sm-3" class="font-titulo" style="background-color:#8BC4C4"><font size="2">FY</font></th>
                                                    </tr>
                                                </thead>
                                                <tbody id="detalleCustom"></tbody>
                                            </table>
                                        </div>
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
        <!-- JavaScript files-->
        <script src="../../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Multiselect -->
        <script src="../../lib/Multiselect/js/bootstrap-select.min.js" type="text/javascript"></script>
        <!-- actions js -->
        <script src="../../lib/validationsInbound/customs/reporteCustoms.js" type="text/javascript"></script>
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
