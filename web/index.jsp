<%-- 
    Document   : login
    Created on : 20/12/2021, 01:38:16 PM
    Author     : Desarrollo Tacts
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>VF INBOUND - TMS</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Google fonts - Popppins for copy-->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&amp;display=swap" rel="stylesheet">
    <!-- Prism Syntax Highlighting-->
    <link rel="stylesheet" href="lib/vendor/prismjs/plugins/toolbar/prism-toolbar.css">
    <link rel="stylesheet" href="lib/vendor/prismjs/themes/prism-okaidia.css">
    <!-- The Main Theme stylesheet (Contains also Bootstrap CSS)-->
    <link rel="stylesheet" href="lib/css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="lib/css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="lib/img/favicon.png">
  </head>
  <body>
      
        <% 
            HttpSession ownsession = request.getSession(false);
            if (session != null) {
                session.setAttribute("user", ownsession);
            }
            session.setAttribute("password", ownsession);
        %>
    <div class="page-holder align-items-center py-4 bg-gray-100 vh-100">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-lg-6 px-lg-4">
            <div class="card">
              <div class="card-header px-lg-5">
                  <div class="card-heading text-primary"><center>VF INBOUND</center></div>
              </div>
              <div class="card-body p-lg-5">
                <h3 class="mb-4">Bienvenido!</h3>
                <!--<p class="text-muted text-sm mb-5">Al portal del sistema de facturación electrónica.</p>-->
                <form id="loginForm"  action="Login" method="POST"> <!--action="index.jsp" -->
                  <div class="form-floating mb-3">
                      <input class="form-control"  name="user" id="user" type="text" placeholder="name@example.com" autocomplete="off">
                    <label for="floatingInput">Usuario</label>
                  </div>
                  <div class="form-floating mb-3">
                      <input class="form-control" name="password"  id="floatingPassword" type="password" placeholder="Password" autocomplete="off">
                    <label for="floatingPassword">Contraseña</label>
                  </div>
                  <div class="form-check mb-3">
                    <input class="form-check-input" type="checkbox" name="remember" id="remember">
                    <label class="form-check-label" for="remember">Recordarme</label>
                  </div>
                          <input type="hidden" value="" id="hidCliente" name="hidCliente"/>
                          <input type="hidden"  name="pag" value=CuentasMobil"/> 
                                             
                  <button class="btn btn-primary btn-lg" type="submit">Aceptar</button>
                </form>
              </div>
              <div class="card-footer px-lg-5 py-lg-4">
                <div class="text-sm text-muted">¿No tiene una cuenta? <a href="register.jsp">Registrar</a>.</div>
              </div>
            </div>
          </div>
          <div class="col-lg-6 col-xl-5 ms-xl-auto px-lg-4 text-center text-primary">
              <img  class="img-fluid mb-4" width="300" src="lib/img/vf.png" alt="" style="transform: rotate(10deg);">
            <h1 class="mb-4">TMS<br class="d-none d-lg-inline"></h1>
            <p class="lead text-muted">TODO EN UN SOLO LUGAR</p>
          </div>
        </div>
      </div>
    </div>
    <!-- JavaScript files-->
    <script src="lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
    <!-- Main Theme JS File-->
    <script src="lib/js/theme.js"></script>
    <!-- Prism for syntax highlighting-->
    <script src="lib/vendor/prismjs/prism.js"></script>
    <script src="lib/vendor/prismjs/plugins/normalize-whitespace/prism-normalize-whitespace.min.js"></script>
    <script src="lib/vendor/prismjs/plugins/toolbar/prism-toolbar.min.js"></script>
    <script src="lib/vendor/prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard.min.js"></script>
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