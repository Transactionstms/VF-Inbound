<%-- 
    Document   : bandejaPlaneacion2
    Created on : 01-jun-2023, 13:18:48
    Author     : grecendiz
--%>

<%@page import="com.usuario.Usuario"%>
<%@page import="com.onest.oracle.DB"%>
<%@page import="com.onest.oracle.DBConfData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>bandejaPlaneacion</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">

        <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/css/style.default.css" id="theme-stylesheet">


        <link href="../lib/inbound/eventos/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- Connection Status Red -->

        <link href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>

    </head>
    <%
        try {
            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            Usuario root = (Usuario) ownsession.getAttribute("login.root");
            int usr = root.getId();
            
            String f1 = request.getParameter("f1");
            String f2 = request.getParameter("f2");

            String sql2 = "   select DISTINCT"
                    + " te.EMBARQUE_T_ID,"
                    + " te.EMBARQUE_FEC_CAPTURA,"
                    + " tt.LTRANSPORTE_NOMBRE,"
                    + " TO_CHAR(te.EMBARQUE_FEC_ENRAMPE, 'MM/DD/YY'),"
                    + " TO_CHAR(te.EMBARQUE_FEC_INICIO, 'MM/DD/YY'),"
                    + " te.EMBARQUE_AGRUPADOR "
                    + " from TRA_INB_EMBARQUE_TRASLADO te"
                    + " left join tra_inb_linea_transporte tt on tt.LTRANSPORTE_ID=te.EMBARQUE_TRANSPORTISTA"
                    + " inner join tra_inc_gtn_test gtn on gtn.EMBARQUE_AGRUPADOR=te.EMBARQUE_AGRUPADOR"
                    + " WHERE gtn.STATUS_EMBARQUE not in (3,14) "
                    + " AND tt.user_nid = '"+usr+"' "
                    + " AND TRUNC(te.EMBARQUE_FEC_CAPTURA) "
                    + " BETWEEN  TO_DATE('" + f1 + "', 'MM/DD/YYYY') AND TO_DATE('" + f2 + "', 'MM/DD/YYYY')"; 
    %>
    <body> 
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
                                                <h2 class="card-heading">Planeacion   </h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body"> 
                                            <br>
                                            <div id="table-scroll" class="table-scroll"  style="height: 60%;">
                                                <table id="example" class="display" style="width:100%">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col" class="font-titulo">Embarque         </th>	
                                                            <th scope="col" class="font-titulo">Fecha de carga   </th>
                                                            <th scope="col" class="font-titulo">Fecha de entrega </th>
                                                            <th scope="col" class="font-titulo">Transportista    </th>
                                                            <th scope="col" class="font-titulo">CFDI             </th>
                                                            <th scope="col" class="font-titulo">Planeacion       </th>
                                                            <th scope="col" class="font-titulo">Confirmacion     </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="cuerpo">
                                                        <%      if (db.doDB(sql2)) {
                                                                for (String[] row : db.getResultado()) {

                                                        %>
                                                        <tr id="<%=row[5]%>">
                                                            <th class="font-numero"><%=row[0]%></th>	
                                                            <td class="font-numero"><%=row[3]%></td>
                                                            <td class="font-texto"> <%=row[4]%></td>
                                                            <td class="font-texto"> <%=row[2]%></td>
                                                            <td class="font-texto"> <a  href="<%=request.getContextPath()%>/MostrarPDF_CFDI_T?agrupador=<%=row[5]%>" target="_blank" class="btn btn-primary">PDF CFDI</a></td>
                                                            <td class="font-texto"> <a  href="<%=request.getContextPath()%>/Logistica/Traslado/pdflogistica.jsp?a=<%=row[5]%>&email=0&bc=0" class="btn btn-primary" target="_blank" >PDF Planeacion</a></td> 
                                                            <td class="font-texto"> <button class="btn btn-primary" type="button" onclick="confirmar('<%=row[5]%>')">Confirmar</button></td> 

                                                        </tr>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <br>
                                            <!-- Botones controles -->

                                     
                                    </div>
                                </div>              
                            </div>
                        </div>   
                    </section>
                </div>  

            </div>
        </div>   
        <!-- Conexión estatus red -->                    
        <script src='https://code.jquery.com/jquery-3.5.1.js'></script>
        <script src='https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js'></script>
        <script src='https://cdn.datatables.net/buttons/2.3.6/js/dataTables.buttons.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js'></script>
        <script src='https://cdn.datatables.net/buttons/2.3.6/js/buttons.html5.min.js'></script>
        <script src='https://cdn.datatables.net/buttons/2.3.6/js/buttons.print.min.js'></script>

        <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>


        <script>

                                                                function confirmar(id) {
                                                                    swal({
                                                                        title: "Guardando. . .",
                                                                        allowEscapeKey: false
                                                                    });

 
                                                                    fetch("<%=request.getContextPath()%>/confirmarEmbarqueT?evento=" + id, {
                                                                        method: 'POST',
                                                                    }).then(r => r.text())
                                                                            .then(data => {
                                                                                console.log(data)
                                                                                swal("", data, "success"); 
                                                                                eliminaTr('cuerpo', id)
                                                                            }).catch(error => console.log(error));
                                                                }


                                                                function eliminaTr(tablaId, filaId) {
                                                                    var tabla = document.getElementById(tablaId);
                                                                    var filaEliminar = document.getElementById(filaId);

                                                                    if (tabla && filaEliminar) {
                                                                        tabla.removeChild(filaEliminar);
                                                                    }
                                                                }


        </script>
        <script type="text/javascript">




            $(document).ready(function () {
                $('#example').DataTable({
                    dom: 'lBfrtip', // Incluye el elemento de paginación
                    buttons: [
                        {
                            extend: 'copy',
                            text: 'Copiar'
                        },
                        {
                            extend: 'csv',
                            text: 'CSV'
                        },
                        {
                            extend: 'excel',
                            text: 'Excel'
                        }
                    ],
                    lengthMenu: [10, 15, 25, 50, 100], // Define las opciones de "Mostrar entradas"
                    pageLength: 10 // Establece el número predeterminado de entradas por página
                });
            });


        </script>
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
