<!DOCTYPE html>  
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16" href="../lib/css/NTMS/plugins/images/faviconTra.png">
<title>Transactions TMS</title>
<!-- Bootstrap Core CSS -->
<link href="../lib/css/NTMS/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Menu CSS -->
<link href="../lib/css/NTMS/lib/css/NTMS/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
<!-- toast CSS -->
<link href="../lib/css/NTMS/lib/css/NTMS/plugins/bower_components/toast-master/css/jquery.toast.css" rel="stylesheet">
<!-- morris CSS -->
<link href="../lib/css/NTMS/plugins/bower_components/morrisjs/morris.css" rel="stylesheet">
<!-- animation CSS -->
<link href="../lib/css/NTMS/css/animate.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="../lib/css/NTMS/css/style.css" rel="stylesheet">
<!-- color CSS -->
<link href="../lib/css/NTMS/css/colors/default.css" id="theme"  rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-19175540-9', 'auto');
  ga('send', 'pageview');

</script>
</head>
<body>
<!-- Preloader -->
<div id="wrapper">
  <!-- Navigation -->
  <nav class="navbar navbar-default navbar-static-top m-b-0">
    <div class="navbar-header"> <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse" data-target=".navbar-collapse"><i class="ti-menu"></i></a>
      <div class="top-left-part"><a class="logo" href="index.html"><b><!--This is dark logo icon--><img src="../lib/css/NTMS/plugins/images/eliteadmin-logo.png" alt="home" class="dark-logo" /><!--This is light logo icon--><img src="../lib/css/NTMS/plugins/images/logoVF-80.png" alt="home" class="light-logo" /></b><span class="hidden-xs"><!--This is dark logo text--><img src="../lib/css/NTMS/plugins/images/TEXT-VF.png" alt="home" class="dark-logo" /><!--This is light logo text--><img src="../lib/css/NTMS/plugins/images/TEXT-VF.png" alt="home" class="light-logo" /></span></a></div>
      <ul class="nav navbar-top-links navbar-left hidden-xs">
        <li><a href="javascript:void(0)" class="open-close hidden-xs waves-effect waves-light"><i class="icon-arrow-left-circle ti-menu"></i></a></li>
        <li>
          <form role="search" class="app-search hidden-xs">
            <input type="text" placeholder="Search..." class="form-control">
            <a href=""><i class="fa fa-search"></i></a>
          </form>
        </li>
      </ul>
        
      <ul class="nav navbar-top-links navbar-right pull-right">
    
        <!-- /.dropdown -->
        <li class="dropdown"> <a class="dropdown-toggle waves-effect waves-light" data-toggle="dropdown" href="#"><span class="hidden-xs">Cuenta</span> <i class="icon-options-vertical"></i></a>
          </a>
          <ul class="dropdown-menu dropdown-cart dropdown-tasks animated slideInUp">
            <li>
                <div class="cart-img"><img src="../lib/css/NTMS/plugins/images/reeflogo.png"/></div>
                <div class="cart-content"><h5>Reef</h5><small></small></div>
            </li>
            <li class="divider"></li>
            <li>
                <div class="cart-img"><img src="../lib/css/NTMS/plugins/images/nothlogo.png"/></div>
                <div class="cart-content"><h5>The North Face</h5><small></small></div>
            </li>
            <li class="divider"></li>
            <li>
                <div class="cart-img"><img src="../lib/css/NTMS/plugins/images/timberlandlogo.png"/></div>
                <div class="cart-content"><h5>Timberland</h5><small></small></div>
            </li>
                <li>
                <div class="cart-img"><img src="../lib/css/NTMS/plugins/images/vanslogo.png"/></div>
                <div class="cart-content"><h5>Vans</h5><small></small></div>
            </li>
            <li class="divider"></li>
            <li> <a class="text-center" href="product-checkout.html"> <strong>Selecciona la cuenta</strong></i> </a> </li>
          </ul>
          <!-- /.dropdown-tasks -->
        </li>
        <!-- /.dropdown -->
        <!-- .Megamenu -->
  
        <!-- /.Megamenu -->
        
        <li class="right-side-toggle"> <a class="waves-effect waves-light" href="javascript:void(0)"><i class="ti-settings"></i></a></li>
        <!-- /.dropdown -->
      </ul>
    </div>
    <!-- /.navbar-header -->
    <!-- /.navbar-top-links -->
    <!-- /.navbar-static-side -->
  </nav>
  <!-- Left navbar-header -->
  <div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse slimscrollsidebar">
      <div class="user-profile">
        <div class="dropdown user-pro-body">
          <div><img src="../lib/css/NTMS/plugins/images/users/admin.png" alt="user-img" class="img-circle"></div>
          <a href="#" class="dropdown-toggle u-dropdown" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Administrador <span class="caret"></span></a>
              <ul class="dropdown-menu animated flipInY">
                <li><a href="#"><i class="ti-user"></i> Mi Perfil</a></li>
                <li><a href="#"><i class="ti-wallet"></i> Balance</a></li>
                <li><a href="#"><i class="ti-email"></i> Inbox</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#"><i class="ti-settings"></i> Configuraciones</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="login.html"><i class="fa fa-power-off"></i>Salir</a></li>
              </ul>
        </div>
      </div>
      <ul class="nav" id="side-menu">
        <li class="sidebar-search hidden-sm hidden-md hidden-lg">
          <!-- input-group -->
          <div class="input-group custom-search-form">
            <input type="text" class="form-control" placeholder="Buscar...">
            <span class="input-group-btn">
            <button class="btn btn-default" type="button"> <i class="fa fa-search"></i> </button>
            </span> </div>
          <!-- /input-group -->
        </li>

        <li><div class="hide-menu t-earning"><div id="sparkline2dash" class="m-b-10"></div><small class="db">ESTADISTICAS DE EMBARQUES</small><h3 class="m-t-0 m-b-0">$2,478.00</h3></div>
        </li>
        <li class="nav-small-cap m-t-10">---Menu</li>
        <li> <a href="index.html" class="waves-effect active"><i class="linea-icon linea-basic fa-fw" data-icon="v"></i> <span class="hide-menu">Principal<span class="fa arrow"></span> <span class="label label-rouded label-custom pull-right"></span></span></a>
          <ul class="nav nav-second-level">
            <li> <a href="index.html">Estadisticas</a> </li>
            <li> <a href="catalogos.html">Catalogos</a> </li>
            <li> <a href="product-detail.html">Control Documental</a> </li>
            <li> <a href="product-edit.html">Documentos</a> </li>
            <li> <a href="product-orders.html">Logistica</a> </li>
            <li> <a href="product-cart.html">Reportes</a> </li>
            <li> <a href="product-checkout.html">Tracking</a> </li>
          </ul>
        </li>
<li> <a href="javascript:void(0);" class="waves-effect"><i class="linea-icon linea-basic fa-fw text-danger" data-icon="7"></i> <span class="hide-menu text-danger"> Multipurpose <span class="fa arrow"></span> <span class="label label-rouded label-danger pull-right">HOT</span></span></a>
    <ul class="nav nav-second-level">
        <li> <a href="../lib/css/NTMS/eliteadmin-hospital/index.html">Hospital Admin</a> </li>
        <li> <a href="../lib/css/NTMS/eliteadmin-crm/index.html">CRM Admin</a> </li>
        <li> <a href="../lib/css/NTMS/eliteadmin-university/index.html">University Admin</a> </li>
        <li> <a href="../lib/css/NTMS/eliteadmin-music/index.html">Music Admin</a> </li>
        <li> <a href="../lib/css/NTMS/eliteadmin-real-estate/index.html">Real Estate Admin</a> </li>
        <li role="separator" class="divider"></li>
        <li> <a href="../lib/css/NTMS/eliteadmin-inverse/index.html">Inverse</a></li>
        <li> <a href="../lib/css/NTMS/eliteadmin-colors/index.html">Colors</a></li>
        <li> <a href="../lib/css/NTMS/eliteadmin/index.html">Eliteadmin</a></li>
        <li> <a href="../lib/css/NTMS/eliteadmin-wpmenu/index.html">Wpmenu</a></li>
        
        <li> <a href="../lib/css/NTMS/eliteadmin-modern/index.html">Modern</a></li>
        <li> <a href="../lib/css/NTMS/eliteadmin-inverse-php/index.php">Basic PHP</a></li>
        <li> <a href="../lib/css/NTMS/eliteadmin-material/index3.html">Material Design</a></li>
        <li> <a href="../lib/css/NTMS/eliteadmin-dark/index.html">Dark</a></li>
        <li> <a href="../lib/css/NTMS/eliteadmin-mini-sidebar/index3.html">Mini Sidebar</a></li>
        <li> <a href="../lib/css/NTMS/eliteadmin-horizontal-navbar/index3.html">Horizontal Boxed Nav</a></li>
        <li> <a href="../lib/css/NTMS/eliteadmin-horizontal-nav-fullwidth/index.html">Horizontal Full Nav</a></li>
        <li> <a href="../lib/css/NTMS/eliteadmin-iconbar/index4.html">Iconbar</a></li>
        <li> <a href="../lib/css/NTMS/eliteadmin-rtl/index.html">Eliteadmin RTL</a></li>
        <li> <a href="../lib/css/NTMS/eliteadmin-inverse-rtl/index.html">Inverse RTL</a></li>
    </ul>
</li>
        <li><a href="inbox.html" class="waves-effect"><i data-icon=")" class="linea-icon linea-basic fa-fw"></i> <span class="hide-menu">Apps <span class="fa arrow"></span></span></a>
          <ul class="nav nav-second-level">
            <li><a href="chat.html">Chat-message</a></li>
            <li><a href="javascript:void(0)" class="waves-effect">Inbox<span class="fa arrow"></span></a>
              <ul class="nav nav-third-level">
                <li> <a href="inbox.html">Mail box</a></li>
                <li> <a href="inbox-detail.html">Inbox detail</a></li>
                <li> <a href="compose.html">Compose mail</a></li>
              </ul>
            </li>
            <li><a href="javascript:void(0)" class="waves-effect">Contacts<span class="fa arrow"></span></a>
              <ul class="nav nav-third-level">
                <li> <a href="contact.html">Contact1</a></li>
                <li> <a href="contact2.html">Contact2</a></li>
                <li> <a href="contact-detail.html">Contact Detail</a></li>
              </ul>
            </li>
          </ul>
        </li>
        <li> <a href="#" class="waves-effect"><i data-icon="/" class="linea-icon linea-basic fa-fw"></i> <span class="hide-menu">UI Elements<span class="fa arrow"></span> <span class="label label-rouded label-info pull-right">13</span> </span></a>
          <ul class="nav nav-second-level">
            <li><a href="panels-wells.html">Panels and Wells</a></li> <li><a href="panel-ui-block.html">Panels With BlockUI</a></li>
            <li><a href="buttons.html">Buttons</a></li>
            <li><a href="sweatalert.html">Sweat alert</a></li>
            <li><a href="typography.html">Typography</a></li>
            <li><a href="grid.html">Grid</a></li>
            <li><a href="tabs.html">Tabs</a></li>
<li><a href="tab-stylish.html">Stylish Tabs</a></li>
            <li><a href="modals.html">Modals</a></li>
            <li><a href="progressbars.html">Progress Bars</a></li>
            <li><a href="notification.html">Notifications</a></li>
            <li><a href="carousel.html">Carousel</a></li>
            <li><a href="list-style.html">List & Media object</a></li>
            <li><a href="user-cards.html">User Cards</a></li>
            <li><a href="timeline.html">Timeline</a></li>
            <li><a href="timeline-horizontal.html">Horizontal Timeline</a></li>
            <li><a href="nestable.html">Nesteble</a></li>
<li><a href="range-slider.html">Range Slider</a></li>
            <li><a href="bootstrap.html">Bootstrap UI</a></li>
<li><a href="tooltip-stylish.html">Stylish Tooltip</a></li>
          </ul>
        </li>
        <li> <a href="forms.html" class="waves-effect"><i data-icon="&#xe00b;" class="linea-icon linea-basic fa-fw"></i> <span class="hide-menu">Forms<span class="fa arrow"></span></span></a>
          <ul class="nav nav-second-level">
            <li><a href="form-basic.html">Basic Forms</a></li>
<li><a href="form-layout.html">Form Layout</a></li>
            <li><a href="form-advanced.html">Form Addons</a></li>
            <li><a href="form-material-elements.html">Form Material</a></li> <li><a href="form-float-input.html">Form Float Input</a></li>
            <li><a href="form-upload.html">File Upload</a></li>
            <li><a href="form-mask.html">Form Mask</a></li>
            <li><a href="form-img-cropper.html">Image Cropping</a></li>
            <li><a href="form-validation.html">Form Validation</a></li>
            <li><a href="form-dropzone.html">File Dropzone</a></li>
            <li><a href="form-pickers.html">Form-pickers</a></li>
            <li><a href="form-wizard.html">Form-wizards</a></li>
            <li><a href="form-typehead.html">Typehead</a></li>
            <li><a href="form-xeditable.html">X-editable</a></li>
            <li><a href="form-summernote.html">Summernote</a></li>
            <li><a href="form-bootstrap-wysihtml5.html">Bootstrap wysihtml5</a></li>
            <li><a href="form-tinymce-wysihtml5.html">Tinymce wysihtml5</a></li>
          </ul>
        </li>
        <li class="nav-small-cap">--- Proffessional</li>
        <li> <a href="#" class="waves-effect"><i data-icon="&#xe008;" class="linea-icon linea-basic fa-fw"></i> <span class="hide-menu">Sample Pages<span class="fa arrow"></span><span class="label label-rouded label-purple pull-right">30</span></span></a>
          <ul class="nav nav-second-level">
            <li><a href="starter-page.html">Starter Page</a></li>
            <li><a href="blank.html">Blank Page</a></li>
            <li><a href="javascript:void(0)" class="waves-effect">Email Templates<span class="fa arrow"></span></a>
              <ul class="nav nav-third-level">
                <li> <a href="../lib/css/NTMS/email-templates/basic.html">Basic</a></li>
                <li> <a href="../lib/css/NTMS/email-templates/alert.html">Alert</a></li>
                <li> <a href="../lib/css/NTMS/email-templates/billing.html">Billing</a></li>
                <li> <a href="../lib/css/NTMS/email-templates/password-reset.html">Reset Pwd</a></li>
              </ul>
            </li>
<li><a href="lightbox.html">Lightbox Popup</a></li>
<li><a href="treeview.html">Treeview</a></li>
<li><a href="search-result.html">Search Result</a></li>
            <li><a href="utility-classes.html">Utility Classes</a></li>
<li><a href="custom-scroll.html">Custom Scrolls</a></li>
<li><a href="login.html">Login Page</a></li>
            <li><a href="login2.html">Login v2</a></li>
            <li><a href="animation.html">Animations</a></li>
<li><a href="profile.html">Profile</a></li>
            <li><a href="invoice.html">Invoice</a></li>
            <li><a href="faq.html">FAQ</a></li>
            <li><a href="gallery.html">Gallery</a></li>
            <li><a href="pricing.html">Pricing</a></li>
            <li><a href="register.html">Register</a></li>
            <li><a href="register2.html">Register v2</a></li>
           <li><a href="register3.html">3 Step Registration</a></li>
            <li><a href="recoverpw.html">Recover Password</a></li>
            <li><a href="lock-screen.html">Lock Screen</a></li>
            <li><a href="400.html">Error 400</a></li>
            <li><a href="403.html">Error 403</a></li>
            <li><a href="404.html">Error 404</a></li>
            <li><a href="500.html">Error 500</a></li>
            <li><a href="503.html">Error 503</a></li>
          </ul>
        </li>
        <li> <a href="#" class="waves-effect"><i data-icon="&#xe006;" class="linea-icon linea-basic fa-fw"></i> <span class="hide-menu">Charts<span class="fa arrow"></span></span></a>
          <ul class="nav nav-second-level">
            <li> <a href="flot.html">Flot Charts</a> </li>
            <li><a href="morris-chart.html">Morris Chart</a></li>
            <li><a href="chart-js.html">Chart-js</a></li>
            <li><a href="peity-chart.html">Peity Charts</a></li>                                     <li><a href="knob-chart.html">Knob Charts</a></li>
            <li><a href="sparkline-chart.html">Sparkline charts</a></li>
            <li><a href="extra-charts.html">Extra Charts</a></li>
          </ul>
        </li>
        <li> <a href="tables.html" class="waves-effect"><i data-icon="O" class="linea-icon linea-software fa-fw"></i> <span class="hide-menu">Tables<span class="fa arrow"></span><span class="label label-rouded label-danger pull-right">7</span></span></a>
          <ul class="nav nav-second-level">
            <li><a href="basic-table.html">Basic Tables</a></li> <li><a href="table-layouts.html">Table Layouts</a></li>
            <li><a href="data-table.html">Data Table</a></li>
            <li><a href="bootstrap-tables.html">Bootstrap Tables</a></li>
            <li><a href="responsive-tables.html">Responsive Tables</a></li>
            <li><a href="editable-tables.html">Editable Tables</a></li>
            <li><a href="foo-tables.html">FooTables</a></li>
            <li><a href="jsgrid.html">JsGrid Tables</a></li>
          </ul>
        </li>
        <li> <a href="widgets.html" class="waves-effect"><i data-icon="P" class="linea-icon linea-basic fa-fw"></i> <span class="hide-menu">Widgets</span></a> </li>
        <li> <a href="#" class="waves-effect"><i data-icon="7" class="linea-icon linea-basic fa-fw"></i> <span class="hide-menu">Icons<span class="fa arrow"></span></span></a>
          <ul class="nav nav-second-level">
            <li> <a href="fontawesome.html">Font awesome</a> </li>
            <li> <a href="themifyicon.html">Themify Icons</a> </li>
            <li> <a href="simple-line.html">Simple line Icons</a> </li>
            <li><a href="linea-icon.html">Linea Icons</a></li>
            <li><a href="weather.html">Weather Icons</a></li>
          </ul>
        </li>
        <li> <a href="map-google.html" class="waves-effect"><i data-icon="Q" class="linea-icon linea-basic fa-fw"></i><span class="hide-menu" >Google Map</span></a> </li>
        <li> <a href="map-vector.html" class="waves-effect"><i data-icon="S" class="linea-icon linea-basic fa-fw"></i><span class="hide-menu" >Vector Map</span></a> </li>
        <li> <a href="calendar.html" class="waves-effect"><i data-icon="A" class="linea-icon linea-elaborate fa-fw"></i> <span class="hide-menu">Calendar</span></a></li>
        <li> <a href="javascript:void(0)" class="waves-effect"><i data-icon="F" class="linea-icon linea-software fa-fw"></i> <span class="hide-menu">Multi-Level Dropdown<span class="fa arrow"></span></span></a>
          <ul class="nav nav-second-level">
            <li> <a href="javascript:void(0)">Second Level Item</a> </li>
            <li> <a href="javascript:void(0)">Second Level Item</a> </li>
            <li> <a href="javascript:void(0)" class="waves-effect">Third Level <span class="fa arrow"></span></a>
              <ul class="nav nav-third-level">
                <li> <a href="javascript:void(0)">Third Level Item</a> </li>
                <li> <a href="javascript:void(0)">Third Level Item</a> </li>
                <li> <a href="javascript:void(0)">Third Level Item</a> </li>
                <li> <a href="javascript:void(0)">Third Level Item</a> </li>
              </ul>
            </li>
          </ul>
        </li>
        <li><a href="login.html" class="waves-effect"><i class="icon-logout fa-fw"></i> <span class="hide-menu">Log out</span></a></li>
        <li class="nav-small-cap">--- Support</li>
        <li><a href="documentation.html" class="waves-effect"><i class="fa fa-circle-o text-danger"></i> <span class="hide-menu">Documentation</span></a></li>
        <li><a href="gallery.html" class="waves-effect"><i class="fa fa-circle-o text-info"></i> <span class="hide-menu">Gallery</span></a></li>
        <li><a href="faq.html" class="waves-effect"><i class="fa fa-circle-o text-success"></i> <span class="hide-menu">Faqs</span></a></li>
      </ul>
    </div>
  </div>
  <!-- Left navbar-header end -->
  <!-- Page Content -->
  <div id="page-wrapper">
    <div class="container-fluid">
      <div class="row bg-title">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
          <h4 class="page-title">VF OUTDOORS TMS</h4>
        </div>
        <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
          <ol class="breadcrumb">
            <li><a href="#">Dashboard</a></li>
          </ol>
        </div>
        <!-- /.col-lg-12 -->
      </div>

      <!-- /.right-sidebar -->
    </div>
    <!-- /.container-fluid -->
    <footer class="footer text-center"> 2017 &copy; TransactionsTMS </footer>
  </div>
  <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<!-- jQuery -->
<script src="../lib/css/NTMS/plugins/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Menu Plugin JavaScript -->
<script src="../lib/css/NTMS/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
<!--slimscroll JavaScript -->
<script src="js/jquery.slimscroll.js"></script>
<!--Wave Effects -->
<script src="js/waves.js"></script>
<!--Counter js -->
<script src="../lib/css/NTMS/plugins/bower_components/waypoints/lib/jquery.waypoints.js"></script>
<script src="../lib/css/NTMS/plugins/bower_components/counterup/jquery.counterup.min.js"></script>
<!--Morris JavaScript -->
<script src="../lib/css/NTMS/plugins/bower_components/raphael/raphael-min.js"></script>
<script src="../lib/css/NTMS/plugins/bower_components/morrisjs/morris.js"></script>
<!-- Custom Theme JavaScript -->
<script src="js/custom.min.js"></script>
<!-- Sparkline chart JavaScript -->
<script src="../lib/css/NTMS/plugins/bower_components/jquery-sparkline/jquery.sparkline.min.js"></script>
<script src="../lib/css/NTMS/plugins/bower_components/jquery-sparkline/jquery.charts-sparkline.js"></script>
<script src="js/dashboard1.js"></script>
<!-- Sparkline chart JavaScript -->
<script src="../lib/css/NTMS/plugins/bower_components/jquery-sparkline/jquery.sparkline.min.js"></script>
<script src="../lib/css/NTMS/plugins/bower_components/jquery-sparkline/jquery.charts-sparkline.js"></script>

<!--Style Switcher -->
<script src="../lib/css/NTMS/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
