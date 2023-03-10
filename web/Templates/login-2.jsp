<%-- 
    Document   : login-2
    Created on : 20/12/2021, 01:36:54 PM
    Author     : Desarrollo Tacts
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Bubbly - Boootstrap 5 Admin template by Bootstrapious.com</title>
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
    <div class="container-fluid px-0">
      <div class="row gx-0 min-vh-100">
        <div class="col-md-9 col-lg-6 col-xl-4 px-5 d-flex align-items-center shadow">
          <div class="w-100 py-5">
            <div class="text-center"><img class="img-fluid mb-4" src="../lib/img/brand/brand-1.svg" alt="..." style="max-width: 6rem;">
              <h1 class="h4 text-uppercase mb-5">Welcome Back</h1>
            </div>
            <form method="get" action="index.jsp">
              <div class="mb-3">
                <label class="form-label">Email Address</label>
                <input class="form-control" name="loginUsername" type="email" autocomplete="off">
              </div>
              <div class="mb-4">
                <div class="row">
                  <div class="col">
                    <label class="form-label">Password</label>
                  </div>
                  <div class="col-auto"><a class="form-text small text-muted" href="#">Forgot your password?</a></div>
                </div>
                <input class="form-control" name="loginPassword" type="password">
              </div>
              <div class="form-check mb-4">
                <input class="form-check-input" id="remember" type="checkbox">
                <label class="custom-control-label" for="remember">Remember this device</label>
              </div>
              <!-- Submit-->
              <div class="d-grid mb-5">
                <button class="btn btn-primary text-uppercase">Sign in</button>
              </div>
              <!-- Link-->
              <p class="text-sm text-muted text-center">
                 Don't have an account yet? <a href="register-2.jsp">Register</a>.</p>
            </form>
          </div>
        </div>
        <div class="col-md-3 col-lg-6 col-xl-8 d-none d-md-block">
          <!-- Image-->
          <div class="bg-cover h-100 me-n3" style="background-image: url(img/photos/victor-ene-1301123-unsplash.jpg);"></div>
        </div>
      </div>
    </div>
    <!-- JavaScript files-->
    <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
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
