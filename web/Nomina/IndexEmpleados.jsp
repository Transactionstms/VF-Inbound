<%-- 
    Document   : IndexEmpleados
    Created on : 29/12/2021, 06:06:30 PM
    Author     : Desarrollo Tacts
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Empleados</title>
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
  </head>
  <body>
    <!-- navbar-->
    <header class="header">
      <!--<nav class="navbar navbar-expand-lg px-4 py-2 bg-white shadow">
          <a class="sidebar-toggler text-gray-500 me-4 me-lg-5 lead" href="#"><i class="fas fa-align-left"></i></a><a class="navbar-brand fw-bold text-uppercase text-base" href="index.jsp"><span class="d-none d-brand-partial"></span><span class="d-none d-sm-inline">Empleados</span></a>
      </nav>-->
    </header>
    <div class="d-flex align-items-stretch">
      <div class="page-holder bg-gray-100">
        <div class="container-fluid px-lg-4 px-xl-5">
            <div class="page-header">
                <h3 class="page-heading">Empleados</h3>
            </div>
          <section class="mb-5">
              <div>
                <a class="btn btn-primary text-uppercase" href="Add.jsp"> <i class="fa fa-plus me-2"> </i>Agregar</a>
              </div>
            <div class="card mb-4">
              <div class="card-header">
                <h4 class="card-heading">Empresa</h4>
              </div>
              <div class="card-body">
                <div class="row">
                  <div class="col-lg-12">
                    <div class="mb-3">  
                      <input class="form-control" id="isbn1" type="text" placeholder="-- Seleccione una Empresa --">
                    </div>
                  </div>
                </div>
              </div>
            </div>   
            <div class="card card-table">
              <div class="preload-wrapper">
                <table class="table table-hover table-borderless align-middle mb-0" id="productsDatatable">
                  <thead>
                    <tr>
                      <th>Número de Empleado</th>
                      <th>Nombre</th>
                      <th>Apellidos</th>
                      <th>Departamento</th>
                      <th>Puesto</th>
                      <th style="min-width: 50px;"> </th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr class="align-middle">
                      <td>81112200</td>
                      <td>JESUS IGNACIO</td>
                      <td>GARCIA ROCA</td>
                      <td>SISTEMAS</td>
                      <td>DESARROLLADOR</td>
                      <td><a class="me-3 text-lg text-success" href="Edit.jsp"><i class="far fa-edit"></i></a></td>
                    </tr>
                    <tr class="align-middle">
                      <td>81112200</td>
                      <td>ANDRES</td>
                      <td>HERNANDEZ HERNANDEZ</td>
                      <td>SISTEMAS</td>
                      <td>ANALISTA</td>
                      <td><a class="me-3 text-lg text-success" href="Edit.jsp"><i class="far fa-edit"></i></a></td>
                    </tr>
                  </tbody>
                </table><span class="me-2" id="categoryBulkAction">
                  <select class="form-select form-select-sm d-inline w-auto" name="categoryBulkAction">
                    <option>Bulk Actions</option>
                    <option>Delete</option>
                  </select>
                  <button class="btn btn-sm btn-outline-primary align-top">Apply</button></span>
              </div>
            </div>
          </section>
        </div>
        <footer class="footer bg-white shadow align-self-end py-3 px-xl-5 w-100">
          <div class="container-fluid">
            <div class="row">
              <div class="col-md-6 text-center text-md-start fw-bold">
                <p class="mb-2 mb-md-0 fw-bold">Tacts S de RL de CV &copy; 2021</p>
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
    <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
  </body>
</html>
