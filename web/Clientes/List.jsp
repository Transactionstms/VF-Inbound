<%-- 
    Document   : List
    Created on : 29/12/2021, 04:07:51 PM
    Author     : Desarrollo Tacts
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.onest.train.consultas.ConsultasQuery"%>
<%@page import="com.onest.security.menu.*" %>
<%@page import="com.onest.net.*" %>
<%@page import="com.onest.oracle.*" %>
<%@page import="com.onest.misc.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <title>Clientes</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Google fonts - Popppins for copy-->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&amp;display=swap" rel="stylesheet">
    <!-- Prism Syntax Highlighting-->
    <link rel="stylesheet" href="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.css">
    <link rel="stylesheet" href="../lib/vendor/prismjs/themes/prism-okaidia.css">
    <!-- The Main Theme stylesheet (Contains also Bootstrap CSS)-->
    <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="../lib/css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="../lib/img/favicon.png">
    <!-- sweetalert -->
    <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
  </head>
  <body>
      <%
          try {
              HttpSession ownsession = request.getSession();
              DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
              String cve = (String) ownsession.getAttribute("cbdivcuenta");
              ConsultasQuery fac = new ConsultasQuery();
      %>
    <!-- navbar-->
    <header class="header">
      <!--<nav class="navbar navbar-expand-lg px-4 py-2 bg-white shadow">
          <a class="sidebar-toggler text-gray-500 me-4 me-lg-5 lead" href="#"><i class="fas fa-align-left"></i></a><a class="navbar-brand fw-bold text-uppercase text-base" href="index.jsp"><span class="d-none d-brand-partial"></span><span class="d-none d-sm-inline">Productos</span></a>
      </nav>-->
    </header>
    <div class="d-flex align-items-stretch">
        <div class="page-holder bg-gray-100">
            <div class="container-fluid px-lg-4 px-xl-5">
                <div class="page-header">
                    <h3 class="page-heading">Clientes</h3>
                </div>
                <section class="mb-5">
                    <div class="row">
                        <div class="col-lg-10"> </div>
                        <div class="col-lg-2">
                            <a class="btn btn-primary" href="Add.jsp"> <i class="fa fa-plus me-2"> </i>&nbsp;Agregar</a>
                        </div>
                    </div>  
                    <div class="card card-table">
                        <div class="preload-wrapper">
                            <table class="table table-hover table-borderless align-middle mb-0" id="productsDatatable">
                    <thead>
                        <tr>
                            <th width="10%" style="text-align: center;"><font size="2">ID</font></th>
                            <th width="10%" style="text-align: center;"><font size="2">RFC</font></th>
                            <th width="30%" style="text-align: center;"><font size="2">Cliente</font></th>
                            <th width="20%" style="text-align: center;"><font size="2">Contacto</font></th>
                            <th width="10%" style="text-align: center;"><font size="2">Correo</font></th>
                            <th width="10%" style="text-align: center;"><font size="2">Teléfono</font></th>
                            <th width="10%" style="text-align: center;"><font size="2">Opción</font></th>
                        </tr>
                    </thead>
                  <tbody>
                    <% 
                       if (db.doDB(fac.listClientes(cve))) {
                           for (String[] row : db.getResultado()) {
                    %>
                        <tr class="align-middle">
                          <td width="10%"><font size=2><%=row[0]%></font></td>
                          <td width="10%"><font size=2><%=row[1]%></font></td>
                          <td width="30%"><font size=2><%=row[2]%></font></td>
                          <td width="20%"><font size=2><%=row[3]%></font></td>
                          <td width="10%"><font size=2><%=row[4]%></font></td>
                          <td width="10%"><font size=2><%=row[5]%></font></td>
                          <td width="10%">
                              <a class="me-1 text-lg text-info" href="Edit.jsp?id=<%=row[0]%>"><i class="far fa-edit"></i></a>
                              <a class="text-lg text-info" onclick="confirmar(<%=row[0]%>);"><i class="far fa-trash-alt"></i></a>
                          </td>
                        </tr>
                    <%
                          }
                        }
                    %>
                  </tbody>
                </table>
                  <span class="me-2" id="categoryBulkAction">
                            <select class="form-select form-select-sm d-inline w-auto" name="categoryBulkAction">
                                <option>Bulk Actions</option>
                                <option>Delete</option>
                            </select>
                            <button class="btn btn-sm btn-outline-primary align-top">Apply</button>
                  </span>
                        </div>
                    </div>
                </section>
            </div>
            <footer class="footer bg-white shadow align-self-end py-3 px-xl-5 w-100">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-6 text-center text-md-start fw-bold">
                            <p class="mb-2 mb-md-0 fw-bold">Tacts S de RL de CV &copy; <%=part3%></p>
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
    <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
    <!-- Data Tables-->
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"></script>
    <!-- CMS Category JS-->
    <script src="../lib/js/e-commerce-products.js">    </script>
    <!-- Main Theme JS File-->
    <script src="../lib/js/theme.js"></script>
    <!-- Prism for syntax highlighting-->
    <script src="../lib/vendor/prismjs/prism.js"></script>
    <script src="../lib/vendor/prismjs/plugins/normalize-whitespace/prism-normalize-whitespace.min.js"></script>
    <script src="../lib/vendor/prismjs/plugins/toolbar/prism-toolbar.min.js"></script>
    <script src="../lib/vendor/prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard.min.js"></script>
    <script type="text/javascript">
      // Optional
      Prism.plugins.NormalizeWhitespace.setDefaults({
      'remove-trailing': true,
      'remove-indent': true,
      'left-trim': true,
      'right-trim': true,
      });
          
    </script>
    <script>
        function confirmar(cliente_id) {
            swal({
                title: 'Estas seguro?',
                text: "Se borrará de forma permanente!",
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Si, bórralo!',
                showLoaderOnConfirm: true
            },
                function (isConfirm) {
                    if (isConfirm) {
                        deleteCliente(cliente_id);
                    } else {
                        swal.close();
                    }
                });
        }
        
        function deleteCliente(cliente_id) {
            fetch("<%=request.getContextPath()%>/EliminarClientes?cliente_id=" + cliente_id, {
                method: 'POST',
            }).then(r => r.text())
                    .then(data => {
                         swal("", "Actualización exitosa", "success"); alertclose();
                         location.reload();
                    }).catch(error => console.log(error));
        }
        
        function alertclose() {
            setTimeout(function () {
                swal.close();
            }, 5000);
        }
    </script>
    <!-- sweetalert -->
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
