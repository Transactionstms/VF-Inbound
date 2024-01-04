<%-- 
    Document   : reporteCFDI2
    Created on : Jan 2, 2024, 6:53:15 PM
    Author     : dan_i
--%>

<%@page import="com.onest.oracle.DB"%>
<%@page import="com.onest.oracle.DBConfData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Repore CFDI</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">

        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
        <link href="../lib/inbound/eventos/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- Connection Status Red -->

        <link href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>

    </head>
    <style>
        .table-scroll {
            position: relative;
            width: 100%;      /* Anchura de la tabla */
            z-index: 1;
            margin: auto;
            overflow: auto;
        }

        /* Dispositivos extra pequeños (phones, 600px and down) */
        @media only screen and (max-width: 450px) {
            .table-scroll {
                height: 200px;   /* Altura de la tabla */
            }
        }

        /* Dispositivos pequeños (portrait tablets and large phones, 600px and up) */
        @media only screen and (min-width: 600px) {
            .table-scroll {
                height: 250px;   /* Altura de la tabla */
            }
        }

        /* Dispositivos medianos (landscape tablets, 768px and up) */
        @media only screen and (min-width: 768px) {
            .table-scroll {
                height: 300px;   /* Altura de la tabla */
            }
        }

        /* Dispositivos grandes (laptops/desktops, 992px and up) */
        @media only screen and (min-width: 992px) {
            .table-scroll {
                height: 350px;   /* Altura de la tabla */
            }
        }

        /* Dispositivos extra grandes (large laptops and desktops, 1200px and up) */
        @media only screen and (min-width: 1200px) {
            .table-scroll {
                height: 400px;   /* Altura de la tabla */
            }
        }

        .table-scroll table {
            width: 100%;
            min-width: 1280px;
            margin: auto;
            border-collapse: collapse;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .table-scroll th,
        .table-scroll td {
            padding: 12px;
            border: 1px solid #e0e0e0;
            background: #fff;
            text-align: left;
            transition: background-color 0.3s;
        }

        .table-scroll th:hover,
        .table-scroll td:hover,
        .table-scroll th:focus,
        .table-scroll td:focus {
            background-color: #e3f2fd;
        }

        .table-scroll th {
            background: #2196F3;
            color: #fff;
        }

        .table-scroll td {
            border: 1px solid #e0e0e0;
            background: #f9f9f9;
        }

        .table-scroll thead th {
            position: -webkit-sticky;
            position: sticky;
            top: 0;
            background: #2196F3;     /* Color encabezado */
            color: #fff;
            padding: 12px;
        }

        .table-scroll tfoot,
        .table-scroll tfoot th,
        .table-scroll tfoot td {
            border-radius: 8px; /* Bordes redondeados */
            position: -webkit-sticky;
            position: sticky;
            bottom: 0;
            background: #333;
            color: #fff;
            padding: 12px;
            z-index: 4;
        }

        .table-scroll tfoot {
            box-shadow: 0 -2px 4px rgba(0, 0, 0, 0.1);
        }




        /* Estilo de la tabla y contenedor */
        .dataTables_wrapper {
            border-radius: 8px; /* Bordes redondeados */
            overflow: hidden;
            margin-bottom: 20px; /* Espaciado inferior */
        }

        /* Estilo del encabezado de la tabla */
        .dataTables_wrapper table thead th {
            border-radius: 4px; /* Bordes redondeados */
            background: #1565C0; /* Cambia el color azul a uno más fuerte */
        }

        /* Estilo de las filas hover en la tabla */
        .dataTables_wrapper table tbody tr:hover {
            background-color: #1976D2; /* Cambia el color azul a uno más fuerte en hover */
        }

        /* Estilo de los botones de la DataTable */
        .dataTables_wrapper .dt-buttons {
            margin-right: 10px; /* Espaciado entre los botones y la barra de búsqueda */
            margin-left:    10px; /* Espaciado entre los botones y la barra de búsqueda */


        }

        .dataTables_wrapper .dt-buttons button {
            background-color: #1976D2; /* Cambia el color azul a uno más fuerte */
            border: none;
            border-radius: 5px; /* Bordes redondeados */
            color: #fff;
            padding: 8px 16px;
            margin-right: 10px; /* Espaciado entre los botones */
            margin-left: 10px; /* Espaciado entre los botones */
            margin-bottom: 10px; /* Espaciado inferior de los botones */
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .dataTables_wrapper .dt-buttons button:hover {
            background-color: #1565C0; /* Cambia el color azul a uno más fuerte en hover */
        }

        /* Estilo de la barra de búsqueda */
        .dataTables_wrapper .dataTables_filter input {
            border: 1px solid #ccc;
            border-radius: 5px; /* Bordes redondeados */
            padding: 8px;
            margin-bottom: 10px; /* Espaciado inferior */
        }

        .dataTables_wrapper .dataTables_filter label {
            margin: 15px;
            color: #333;
        }

        /* Estilo del pie de página de la tabla */
        .dataTables_wrapper tfoot {
            border-top: 2px solid #fff;
            border-radius: 8px; /* Bordes redondeados */
        }

        /* ... (Código existente) ... */

        /* Cambiar tamaño de letra en la tabla y botones */
        .table-scroll th,
        .table-scroll td,
        .dataTables_wrapper .dt-buttons button,
        .dataTables_wrapper .dataTables_filter input,
        .dataTables_wrapper .dataTables_length select,
        .dataTables_wrapper .dataTables_info,
        .dataTables_wrapper .dataTables_paginate {
            font-size: 16px; /* Ajusta el tamaño de la letra según sea necesario */
        }

    </style>
    <%
        try {
            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            String f1 = request.getParameter("f1");
            String f2 = request.getParameter("f2");

            String sql2 = "   select DISTINCT"
                    + " te.EMBARQUE_ID,"
                    + " te.EMBARQUE_FEC_CAPTURA,"
                    + " tt.LTRANSPORTE_NOMBRE,"
                    + " TO_CHAR(te.EMBARQUE_FEC_ENRAMPE, 'MM/DD/YY'),"
                    + " TO_CHAR(te.EMBARQUE_FEC_INICIO, 'MM/DD/YY'),"
                    + " te.EMBARQUE_AGRUPADOR "
                    + " from tra_inb_embarque te"
                    + " left join tra_inb_linea_transporte tt on tt.LTRANSPORTE_ID=te.EMBARQUE_TRANSPORTISTA"
                    + " inner join tra_inc_gtn_test gtn on gtn.EMBARQUE_AGRUPADOR=te.EMBARQUE_AGRUPADOR"
                    + " WHERE  gtn.STATUS_EMBARQUE<>3 and TRUNC(te.EMBARQUE_FEC_CAPTURA) "
                    + " BETWEEN  TO_DATE('" + f1 + "', 'MM/DD/YYYY') AND TO_DATE('" + f2 + "', 'MM/DD/YYYY') ";

            //System.out.println("sql2" + sql2);

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
                                                <h2 class="card-heading">Reporte CFDI</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body"> 
                                        <br>
                                        <div id="table-scroll" class="table-scroll"  style="height: 60%;">
                                            <table id="example" class="table-scroll" style="width:100%">
                                                <thead>
                                                    <tr>
                                                        <th scope="col" class="font-titulo">Embarque  </th>	
                                                        <th scope="col" class="font-titulo">Fecha Enrampe  </th>
                                                        <th scope="col" class="font-titulo">Inicio  </th>
                                                        <th scope="col" class="font-titulo">Transportista  </th>
                                                        <th scope="col" class="font-titulo">CFDI  </th>
                                                    </tr>
                                                </thead>
                                                <tbody id="cuerpo">
                                                    <%      if (db.doDB(sql2)) {
                                                            for (String[] row : db.getResultado()) {

                                                    %>
                                                    <tr id="<%=row[5]%>">
                                                        <th class="font-numero" style="font-size: medium; font-family: inherit; font-weight: 15px"><%=row[0]%></th>	
                                                        <td class="font-numero"><%=row[3]%></td>
                                                        <td class="font-texto"> <%=row[4]%></td>
                                                        <td class="font-texto"> <%=row[2]%></td>
                                                        <td class="" style="width: auto">
                                                            <a href="<%=request.getContextPath()%>/MostrarPDF_CFDI?agrupador=<%=row[5]%>" target="_blank" class="btn-lg btn-danger">
                                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-pdf" viewBox="0 0 16 16">
                                                                <path d="M4 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm0 1h8a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1"></path>
                                                                <path d="M4.603 12.087a.81.81 0 0 1-.438-.42c-.195-.388-.13-.776.08-1.102.198-.307.526-.568.897-.787a7.68 7.68 0 0 1 1.482-.645 19.701 19.701 0 0 0 1.062-2.227 7.269 7.269 0 0 1-.43-1.295c-.086-.4-.119-.796-.046-1.136.075-.354.274-.672.65-.823.192-.077.4-.12.602-.077a.7.7 0 0 1 .477.365c.088.164.12.356.127.538.007.187-.012.395-.047.614-.084.51-.27 1.134-.52 1.794a10.954 10.954 0 0 0 .98 1.686 5.753 5.753 0 0 1 1.334.05c.364.065.734.195.96.465.12.144.193.32.2.518.007.192-.047.382-.138.563a1.04 1.04 0 0 1-.354.416.856.856 0 0 1-.51.138c-.331-.014-.654-.196-.933-.417a5.716 5.716 0 0 1-.911-.95 11.642 11.642 0 0 0-1.997.406 11.311 11.311 0 0 1-1.021 1.51c-.29.35-.608.655-.926.787a.793.793 0 0 1-.58.029zm1.379-1.901c-.166.076-.32.156-.459.238-.328.194-.541.383-.647.547-.094.145-.096.25-.04.361.01.022.02.036.026.044a.27.27 0 0 0 .035-.012c.137-.056.355-.235.635-.572a8.18 8.18 0 0 0 .45-.606zm1.64-1.33a12.647 12.647 0 0 1 1.01-.193 11.666 11.666 0 0 1-.51-.858 20.741 20.741 0 0 1-.5 1.05zm2.446.45c.15.162.296.3.435.41.24.19.407.253.498.256a.107.107 0 0 0 .07-.015.307.307 0 0 0 .094-.125.436.436 0 0 0 .059-.2.095.095 0 0 0-.026-.063c-.052-.062-.2-.152-.518-.209a3.881 3.881 0 0 0-.612-.053zM8.078 5.8a6.7 6.7 0 0 0 .2-.828c.031-.188.043-.343.038-.465a.613.613 0 0 0-.032-.198.517.517 0 0 0-.145.04c-.087.035-.158.106-.196.283-.04.192-.03.469.046.822.024.111.054.227.09.346z"></path>
                                                                </svg>
                                                            </a>
                                                        </td>

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


                fetch("<%=request.getContextPath()%>/confirmarEmbarque?evento=" + id, {
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
                    pageLength: 10, // Establece el número predeterminado de entradas por página
                    language: {
                        // Configura el idioma de la DataTable (puedes ajustar según tu preferencia)
                        "sProcessing": "Procesando...",
                        "sLengthMenu": "Mostrar _MENU_ registros",
                        "sZeroRecords": "No se encontraron resultados",
                        "sEmptyTable": "Ningún dato disponible en esta tabla",
                        "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                        "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
                        "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
                        "sInfoPostFix": "",
                        "sSearch": "Buscar:",
                        "sUrl": "",
                        "sInfoThousands": ",",
                        "sLoadingRecords": "Cargando...",
                        "oPaginate": {
                            "sFirst": "Primero",
                            "sLast": "Último",
                            "sNext": "Siguiente",
                            "sPrevious": "Anterior"
                        },
                        "oAria": {
                            "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                            "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                        }
                    }
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
