<%@page import="com.usuario.Usuario"%>
<%@page import="java.util.Calendar"%>
<%@ page session="false" %>
<%@ page import="com.onest.security.menu.*" %>
<%@ page import="com.onest.net.*" %>
<%@ page import="com.onest.oracle.*" %>
<%@ page import="com.onest.misc.*" %>
<!DOCTYPE html>  
<html lang="es">
   <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Facturación Electrónica - TACTS</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Google fonts - Popppins for copy-->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&amp;display=swap" rel="stylesheet">
    <!-- Prism Syntax Highlighting-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/vendor/prismjs/plugins/toolbar/prism-toolbar.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/vendor/prismjs/themes/prism-okaidia.css">
    <!-- The Main Theme stylesheet (Contains also Bootstrap CSS)-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/lib/img/favicon.png">
 


        <%
            JspWriter jspout_ = pageContext.getOut();
            String userName = "";
            String bodNom = "";
            Usuario root = null;
            HttpSession ownsession = request.getSession(false);
            ownsession.setAttribute("indexOfcuenta", (Integer.parseInt((String) ownsession.getAttribute("login.too.many.accounts")) > 1) ? Integer.parseInt(request.getParameter("indexCta")) : 0);
            Cuenta[] cta = (Cuenta[]) ownsession.getAttribute("login.user_cuenta");
            ownsession.setAttribute("slected.acct.index", (Integer.parseInt((String) ownsession.getAttribute("login.too.many.accounts")) > 1) ? Integer.parseInt(request.getParameter("indexCta")) : 0);
            bodNom = cta[((Integer.parseInt((String) ownsession.getAttribute("login.too.many.accounts")) > 1) ? Integer.parseInt(request.getParameter("indexCta")) : 0)].getNombreBod();
            root = (Usuario) ownsession.getAttribute("login.root");
            userName = root.getNombre() + " " + root.getApellidoPaterno() + " " + root.getApellidoMaterno();
            String cliente = ownsession.getAttribute("cliente.login").toString();
        %>
    </head>
    <body>

        <!-- navbar-->
    
        
     
        
        
           <!-- JavaScript files-->
    <script src="<%=request.getContextPath()%>/lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
    <!-- Init Charts on Homepage-->
    <script src="<%=request.getContextPath()%>/lib/vendor/chart.js/Chart.min.js"></script>
    <script src="<%=request.getContextPath()%>/lib/js/charts-defaults.js"></script>
    <script src="<%=request.getContextPath()%>/lib/js/charts-home.js"></script>
    <!-- Main Theme JS File-->
    <script src="<%=request.getContextPath()%>/lib/js/theme.js"></script>
    <!-- Prism for syntax highlighting-->
    <script src="<%=request.getContextPath()%>/lib/vendor/prismjs/prism.js"></script>
    <script src="<%=request.getContextPath()%>/lib/vendor/prismjs/plugins/normalize-whitespace/prism-normalize-whitespace.min.js"></script>
    <script src="<%=request.getContextPath()%>/lib/vendor/prismjs/plugins/toolbar/prism-toolbar.min.js"></script>
    <script src="<%=request.getContextPath()%>/lib/vendor/prismjs/plugins/copy-to-clipboard/prism-copy-to-clipboard.min.js"></script>
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
