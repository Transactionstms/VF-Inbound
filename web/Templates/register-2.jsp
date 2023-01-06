<%-- 
    Document   : register-2
    Created on : 20/12/2021, 01:43:36 PM
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
              <h1 class="h4 text-uppercase mb-3">Register</h1>
            </div>
            <p class="text-muted text-sm mb-4">The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of the rest of him, waved about helplessly as he looked. &quot;What's happened to me?&quot; he thought. It wasn't a dream.</p>
            <form method="get" action="index.jsp">
              <div class="mb-3">
                <label class="form-label" for="registerUsername">Username                </label>
                <input class="form-control" id="registerUsername" type="text" name="registerUsername" required>
              </div>
              <div class="mb-4">
                <label class="form-label" for="registerEmail">Email Address</label>
                <input class="form-control" id="registerEmail" type="email" name="registerEmail" required>
              </div>
              <div class="mb-4">
                <label class="form-label" for="registerPassword">Password</label>
                <input class="form-control" id="registerPassword" type="password" name="registerPassword" required>
              </div>
              <div class="form-check mb-4">
                <input class="form-check-input me-2" id="registerAgree" type="checkbox" value="">
                <label class="form-check-label" for="registerAgree">I agree with the terms and policy                          </label>
              </div>
              <!-- Submit-->
              <div class="d-grid mb-5">
                <button class="btn btn-primary text-uppercase">Register</button>
              </div>
              <!-- Link-->
              <p class="text-center"><small class="text-muted text-center">Already have an account? <a href="login-2.jsp">Log in</a>.</small></p>
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