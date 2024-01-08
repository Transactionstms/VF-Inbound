<%-- 
    Document   : baseLayout
    Plantilla para el Header, Body y Footer el proyecto
    Author     : Luis Alberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.onest.train.consultas.ConsultasQuery"%>
<%@page import="com.onest.security.menu.*" %>
<%@page import="com.onest.net.*" %>
<%@page import="com.onest.oracle.*" %>
<%@page import="com.onest.misc.*" %> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:getAsString name="title" ignore="true" /></title>
        <style type="text/css">  * {
            margin:0;
            padding:0;
        }  </style>
        <script type="text/javascript">  window.history.forward(1);</script>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>VF INBOUND - TACTS</title>
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
    </head>
    <%
        try {
            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            String cve = (String) ownsession.getAttribute("cbdivcuenta");
            String UserId = (String) ownsession.getAttribute("login.user_id_number");
            ConsultasQuery fac = new ConsultasQuery();
            String perfilUsuario = "";
            String nameUsuario = "";

            if (db.doDB(fac.consultarUsuarioName(UserId))) {
                for (String[] rowFT : db.getResultado()) {
                    nameUsuario = rowFT[0];
                }
            }

            if (db.doDB(fac.perfilUsuario(UserId))) {
                for (String[] row : db.getResultado()) {
                    perfilUsuario = row[0];
                }
            }
    %>
    <body>
        <header class="header">
            <nav class="navbar navbar-expand-lg px-4 py-2 bg-white shadow">
                <a class="sidebar-toggler text-gray-500 me-4 me-lg-5 lead" href="#"><i class="fas fa-align-left">&nbsp;&nbsp;&nbsp;&nbsp;VF Inbound</i></a>
                <a class="navbar-brand fw-bold text-uppercase text-base" href="#"><span class="d-none d-brand-partial"><%=nameUsuario%></span></a>
                <ul class="ms-auto d-flex align-items-center list-unstyled mb-0">
                    <li class="nav-item dropdown"> </li>
                    <li class="nav-item dropdown ms-auto"><a class="nav-link pe-0" id="userInfo" href="#" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img class="avatar p-1" src="lib/img/logo-tacts.png" alt="Tacts"></a>
                        <div class="dropdown-menu dropdown-menu-end dropdown-menu-animated" aria-labelledby="userInfo">
                            <div class="dropdown-divider"></div><a class="dropdown-item"    href="<%=request.getContextPath()%>/Logout">Salir</a>
                        </div>
                    </li>
                </ul>
            </nav>
        </header>             
        <div class="d-flex align-items-stretch">
            <div class="sidebar py-3" id="sidebar">
                <h6 class="sidebar-heading">Menu</h6>
                <ul class="list-unstyled">
                    <li class="sidebar-list-item"><a class="sidebar-link text-muted active" href="forms/main.jsp" target="data">
                            <svg class="svg-icon svg-icon-md me-3">
                            <use xlink:href="lib/icons/orion-svg-sprite.svg#real-estate-1"> </use>
                            </svg><span class="sidebar-link-title">Dashboard</span></a>
                    </li>
                    <%
                        if (db.doDB(fac.menus(perfilUsuario))) {
                            for (String[] menus : db.getResultado()) {
                    %> 
                        <li class="sidebar-list-item"><a class="sidebar-link text-muted " href="#" data-bs-target="#cmsDropdown" role="button" aria-expanded="false" data-bs-toggle="collapse"> 
                              <svg class="svg-icon svg-icon-md me-3">
                              <use xlink:href="lib/icons/orion-svg-sprite.svg#reading-1"><%=menus[2]%></use>
                              </svg><span class="sidebar-link-title"><%=menus[0]%></span></a>
                            <ul class="sidebar-menu list-unstyled collapse " id="cmsDropdown">
                    <%
                        if (db.doDB(fac.submenus(perfilUsuario,menus[3]))) {
                            for (String[] submenus : db.getResultado()) {
                    %>
                               <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="<%=request.getContextPath()%><%=submenus[4]%>" target="data"><%=submenus[3]%></a></li>
                    <%
                            }
                        }
                    %>   
                            </ul>
                        </li>
                    <%
                            }
                        }
                    %>  
                </ul>
            </div> 
            <tiles:insert attribute="body" ignore="true" /> 
        </div>
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
