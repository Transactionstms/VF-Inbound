<%-- 
    Document   : Search
    Created on : 29/12/2021, 06:07:41 PM
    Author     : Desarrollo Tacts
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Addendas</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Google fonts - Popppins for copy-->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&amp;display=swap" rel="stylesheet">
    <!-- VanillaJs Datepicker CSS-->
    <link rel="stylesheet" href="../lib/vendor/vanillajs-datepicker/css/datepicker-bs4.min.css">
    <!-- No UI Slider-->
    <link rel="stylesheet" href="../lib/vendor/nouislider/nouislider.css">
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
          <a class="sidebar-toggler text-gray-500 me-4 me-lg-5 lead" href="#"><i class="fas fa-align-left"></i></a><a class="navbar-brand fw-bold text-uppercase text-base" href="index.jsp"><span class="d-none d-brand-partial"></span><span class="d-none d-sm-inline">Detalle Producto</span></a>
      </nav>-->
    </header>
    <div class="d-flex align-items-stretch">
      <div class="page-holder bg-gray-100">
        <div class="container-fluid px-lg-4 px-xl-5">
            <div class="page-header">
                <h3 class="page-heading">Addendas</h3>
            </div>
          <section>
            <!-- imask Input maps-->
            <div class="card mb-4">
                <div class="card-header">
                    <h4 class="card-heading">Búsqueda de CFDI de PremiumOne</h4>
                </div>
              <div class="card-body">  
                <form class="row g-3 needs-validation" novalidate="">
                  <div class="col-md-4">
                    <label class="form-label" for="validationCustom01" style="font-weight: bold;">Empresa:</label>
                    <input class="form-control" id="validationCustom01" type="text" value="Mark" required="">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Please enter your first name.</div>
                  </div>
                  <div class="col-md-4">
                    <label class="form-label" for="validationCustom02" style="font-weight: bold;">Empleado:</label>
                    <input class="form-control" id="validationCustom02" type="text" value="Otto" required="">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Please enter your last name.</div>
                  </div>
                  <div class="col-md-4">
                    <label class="form-label" for="validationCustomUsername" style="font-weight: bold;">Tipo de Nómina:</label>
                    <div class="input-group has-validation"><span class="input-group-text" id="inputGroupPrepend">@</span>
                      <input class="form-control" id="validationCustomUsername" type="text" aria-describedby="inputGroupPrepend" required="">
                      <div class="valid-feedback">Looks good!</div>
                      <div class="invalid-feedback">Please choose a username.</div>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <label class="form-label" for="validationCustom01">First name</label>
                    <input class="form-control" id="validationCustom01" type="text" value="Mark" required="">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Please enter your first name.</div>
                  </div>
                  <div class="col-md-4">
                    <label class="form-label" for="validationCustom02">Last name</label>
                    <input class="form-control" id="validationCustom02" type="text" value="Otto" required="">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Please enter your last name.</div>
                  </div>
                  <div class="col-md-4">
                    <label class="form-label" for="validationCustomUsername">Username</label>
                    <div class="input-group has-validation"><span class="input-group-text" id="inputGroupPrepend">@</span>
                      <input class="form-control" id="validationCustomUsername" type="text" aria-describedby="inputGroupPrepend" required="">
                      <div class="valid-feedback">Looks good!</div>
                      <div class="invalid-feedback">Please choose a username.</div>
                    </div>
                  </div>
                    <div class="col-md-4">
                    <label class="form-label" for="validationCustom01">First name</label>
                    <input class="form-control" id="validationCustom01" type="text" value="Mark" required="">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Please enter your first name.</div>
                  </div>
                  <div class="col-md-4">
                    <label class="form-label" for="validationCustom02">Last name</label>
                    <input class="form-control" id="validationCustom02" type="text" value="Otto" required="">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Please enter your last name.</div>
                  </div>
                  <div class="col-md-4">
                    <label class="form-label" for="validationCustomUsername">Username</label>
                    <div class="input-group has-validation"><span class="input-group-text" id="inputGroupPrepend">@</span>
                      <input class="form-control" id="validationCustomUsername" type="text" aria-describedby="inputGroupPrepend" required="">
                      <div class="valid-feedback">Looks good!</div>
                      <div class="invalid-feedback">Please choose a username.</div>
                    </div>
                  </div>
                    <div class="col-md-4">
                    <label class="form-label" for="validationCustom01">First name</label>
                    <input class="form-control" id="validationCustom01" type="text" value="Mark" required="">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Please enter your first name.</div>
                  </div>
                  <div class="col-md-4">
                    <label class="form-label" for="validationCustom02">Last name</label>
                    <input class="form-control" id="validationCustom02" type="text" value="Otto" required="">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Please enter your last name.</div>
                  </div>
                  <div class="col-md-4">
                    <label class="form-label" for="validationCustomUsername">Username</label>
                    <div class="input-group has-validation"><span class="input-group-text" id="inputGroupPrepend">@</span>
                      <input class="form-control" id="validationCustomUsername" type="text" aria-describedby="inputGroupPrepend" required="">
                      <div class="valid-feedback">Looks good!</div>
                      <div class="invalid-feedback">Please choose a username.</div>
                    </div>
                  </div>
                    <div class="col-md-4">
                    <label class="form-label" for="validationCustom01">First name</label>
                    <input class="form-control" id="validationCustom01" type="text" value="Mark" required="">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Please enter your first name.</div>
                  </div>
                  <div class="col-md-4">
                    <label class="form-label" for="validationCustom02">Last name</label>
                    <input class="form-control" id="validationCustom02" type="text" value="Otto" required="">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Please enter your last name.</div>
                  </div>
                  <div class="col-md-4">
                    <label class="form-label" for="validationCustomUsername">Username</label>
                    <div class="input-group has-validation"><span class="input-group-text" id="inputGroupPrepend">@</span>
                      <input class="form-control" id="validationCustomUsername" type="text" aria-describedby="inputGroupPrepend" required="">
                      <div class="valid-feedback">Looks good!</div>
                      <div class="invalid-feedback">Please choose a username.</div>
                    </div>
                  </div>
                    <div class="col-md-4">
                    <label class="form-label" for="validationCustom01">First name</label>
                    <input class="form-control" id="validationCustom01" type="text" value="Mark" required="">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Please enter your first name.</div>
                  </div>
                  <div class="col-md-4">
                    <label class="form-label" for="validationCustom02">Last name</label>
                    <input class="form-control" id="validationCustom02" type="text" value="Otto" required="">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Please enter your last name.</div>
                  </div>
                  <div class="col-md-4">
                    <label class="form-label" for="validationCustomUsername">Username</label>
                    <div class="input-group has-validation"><span class="input-group-text" id="inputGroupPrepend">@</span>
                      <input class="form-control" id="validationCustomUsername" type="text" aria-describedby="inputGroupPrepend" required="">
                      <div class="valid-feedback">Looks good!</div>
                      <div class="invalid-feedback">Please choose a username.</div>
                    </div>
                  </div>
                </form>
              </div>
            </div>
            
            <div class="card card-table">
              <div class="preload-wrapper">
                <table class="table table-hover table-borderless align-middle mb-0" id="productsDatatable">
                  <thead>
                    <tr>
                      <th style="min-width: 50px;"> </th>
                      <th>Tipo de Percepción</th>
                      <th>Clave</th>
                      <th>Concepto</th>
                      <th>Importe Gravado</th>
                      <th>Importe Exento</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr class="align-middle">
                      <td> </td>
                      <td>81112200</td>
                      <td>81112200</td>
                      <td>SERVICIO DE SISTEMA TRANSACTIONS-LWS</td>
                      <td>$12,000.00</td>
                      <td>$12,000.00</td>
                    </tr>
                    <tr class="align-middle">
                      <td> </td>
                      <td>81112200</td>
                      <td>81112200</td>
                      <td>SERVICIO DE SISTEMA TRANSACTIONS-LWS</td>
                      <td>$12,000.00</td>
                      <td>$12,000.00</td>
                    </tr>
                    <tr class="align-middle">
                      <td> </td>
                      <td>81112200</td>
                      <td>81112200</td>
                      <td>SERVICIO DE SISTEMA TRANSACTIONS-LWS</td>
                      <td>$12,000.00</td>
                      <td>$12,000.00</td>
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
    <script src="../lib/js/e-commerce-products.js">    </script> <!-- Libreria Principal -->
    <!-- Choices.js-->
    <script src="../lib/vendor/choices.js/public/assets/scripts/choices.min.js"></script>
    <!-- iMask-->
    <script src="../lib/vendor/imask/imask.min.js"></script>
    <!-- NoUI Slider-->
    <script src="../lib/vendor/nouislider/nouislider.min.js"></script>
    <!-- VanillaJs DatePicker-->
    <script src="../lib/vendor/vanillajs-datepicker/js/datepicker-full.min.js"></script>
    <!-- Forms init-->
    <script src="../lib/js/forms-advanced.js"></script>
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
