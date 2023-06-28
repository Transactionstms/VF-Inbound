<%-- 
    Document   : detalle_guia_embarque
    Created on : 12/06/2023, 05:41:45 AM
    Author     : luis_
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
        <style>
            .input-container {
              text-align: center;
            }

            .input-container input[type="text"] {
              display: inline-block;
            }
        </style>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
                OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
                String UserId = (String) ownsession.getAttribute("login.user_id_number");
                String cveCuenta = (String) ownsession.getAttribute("cbdivcuenta");
                String embarque_id = request.getParameter("embarque_id");
                ConsultasQuery fac = new ConsultasQuery();
                int contador = 0;       
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
                                                <h2 class="card-heading">Evidencia por guía de Embarque</h2>
                                            </div>
                                        </div>
                                    </div>                                    
                                    <div class="card-body">
                                        <table align="center" width="75%" border="0">
                                            <thead>
                                                <tr>
                                                    <th></th>
                                                    <th></th>
                                                    <th></th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    if (db.doDB(fac.consultarInfoDetalleEmbarqueInbound(embarque_id, cveCuenta))) {
                                                        for (String[] row : db.getResultado()) {
                                                %>    
                                                    <tr>
                                                        <td class="repHdrC" width='10%'><strong>Embarque: </strong></td>
                                                        <td  class='texto' width='25%'><%=row[0]%></td>
                                                        <td class="repHdrC" width='15%'><strong>Fecha: </strong></td>
                                                        <td  class='texto' width='35%'><%=row[1]%></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="repHdrC" width='12%'><strong>Transportista: </strong></td>
                                                        <td  class='texto'><%=row[2]%></td>
                                                        <td class="repHdrC"><strong>Tipo: </strong></td>
                                                        <td  class='texto'><%=row[3]%></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="repHdrC"><strong>Chofer: </strong></td>
                                                        <td  class='texto' colspan='3'><%=row[4]%></td>
                                                        <td  class='texto' colspan='3'></td>
                                                        <td  class='texto' colspan='3'></td>
                                                    </tr>
                                                <%
                                                       }
                                                   }
                                                %>    
                                            </tbody>
                                        </table>
                                        <form  action="<%=request.getContextPath()%>/InsertarEvidencia" method="POST" enctype="multipart/form-data">
                                            <hr size='4' color='#DDDDDD'>
                                            <table align="center" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th class="repHdr"><strong>EVENTO</strong></th>
                                                        <th class="repHdr"><strong>SHIPMENT</strong></th>
                                                        <th class="repHdr"><strong>CONTAINER</strong></th>
                                                        <th class="repHdr"><strong>QUANTITY</strong></th>
                                                        <th class="repHdr"><strong>POD</strong></th>
                                                        <th class="repHdr"><strong>BRAND DIVISION</strong></th>
                                                        <th class="repHdr"><strong>EVIDENCIA</strong></th>
                                                        <th class="repHdr"><strong></strong></th>                                                
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <%
                                                    if (db.doDB(fac.consultarDetalleShipmentIdInbound(embarque_id, cveCuenta))) {
                                                        for (String[] row : db.getResultado()) {
                                                %>            
                                                <tr>
                                                    <td class="repDatNon"><%=row[0]%></td>
                                                    <td class="repDatNon"><%=row[1]%></td>
                                                    <td class="repDatNon"><%=row[2]%></td>
                                                    <td class="repDatNon"><%=row[3]%></td>
                                                    <td class="repDatNon"><%=row[4]%></td>
                                                    <td class="repDatNon"><%=row[5]%></td>
                                                <%
                                                    if(!row[6].equals("7")){
                                                        contador++;
                                                %>
                                                    <td class="repDatNon">
                                                        <input type="hidden" id="shipmentId<%=contador%>" name="shipmentId<%=contador%>" value="<%=row[1]%>">
                                                        <input type="file" id="file<%=contador%>" name="file<%=contador%>" accept=".pdf, .jpg">
                                                    </td>
                                                <%
                                                    }else{
                                                %>
                                                    <td class="repDatNon">
                                                        <img src="../lib/img/check.png" width="25" height="25"/>
                                                    </td>
                                                <%
                                                    }

                                                    if(!row[6].equals("7")){
                                                %>  
                                                    <td><input type="button" onclick="clearFileInput('<%=contador%>')" value="Limpiar"></td>
                                                <%
                                                    }
                                                %>    
                                                </tr>
                                                <%
                                                        }
                                                    }
                                                %>
                                                </tbody>
                                            </table>
                                            <br>
                                            <div class="input-container">
                                                <input type="submit" id="send" name="send" value="Guardar">
                                            </div> 
                                            <input type="hidden" id="embarque_id" name="embarque_id" value="<%=embarque_id%>">
                                            <input type="hidden" id="numFiles" name="numFiles" value="<%=contador%>">
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
        <script>
            function clearFileInput(contador) {
              var fileInput = document.getElementById("file"+contador);
              fileInput.value = ""; // Restablecer el valor del elemento a una cadena vacía
            }
        </script>                    
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

