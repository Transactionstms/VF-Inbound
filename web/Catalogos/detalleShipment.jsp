<%-- 
    Document   : detalleShipment
    Created on : 23/05/2024, 02:03:05 PM
    Author     : User_Windows10
--%>

<%@page import="oracle.jdbc.oracore.Util"%>
<%@page import="com.onest.util.Validacion"%>
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
                String data = request.getParameter("data");
                String href = "";
                String msj = "";
                ConsultasQuery fac = new ConsultasQuery();
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
                                                <h2 class="card-heading">DETALLE SHIPMENT/EVENTO/CONTENEDOR</h2>
                                            </div>
                                        </div>
                                    </div>                                    
                                    <div class="card-body">
                                        <form action="">
                                            <table border="0" align="center" width="80%">
                                                <thead>
                                                    <tr>
                                                        <th class="repHdr">EVENTO</th>
                                                        <th class="repHdr">SHIPMENT</th>
                                                        <th class="repHdr">CONTENEDOR</th>
                                                        <th class="repHdr">BL AWB PRO</th>
                                                        <th class="repHdr">POL</th>
                                                        <th class="repHdr">POD</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        if (db.doDB(fac.consultarDetalleBLAWBPRO(data))) {
                                                           for (String[] row : db.getResultado()) {
                                                    %>              
                                                    <tr> 
                                                        <td class="repDatNon"><font color='black'><%=row[0]%></font></td>
                                                        <td class="repDatNon"><font color='black'><%=row[1]%></font></td> 
                                                        <td class="repDatNon"><font color='black'><%=row[2]%></font></td> 
                                                        <td class="repDatNon"><font color='black'><%=row[3]%></font></td> 
                                                        <td class="repDatNon"><font color='black'><%=row[4]%></font></td> 
                                                        <td class="repDatNon"><font color='black'><%=row[5]%></font></td> 
                                                    </tr>
                                                    <%      
                                                           }
                                                        }
                                                    %>
                                                </tbody>
                                            </table>
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
        <!-- JavaScript files -->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    </body>
    <%
        } catch (NullPointerException e) {
            System.out.println("Error:" +e);
            out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            out.println("<script>window.close();</script>");
        } catch (Exception e) {
            out.println("Excepcion revise por favor! " + e);
            e.printStackTrace();
        }
    %>
</html>
