<%-- 
    Document   : e-commerce-order
    Created on : 20/12/2021, 01:29:14 PM
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
    <!-- navbar-->
    <header class="header">
      <nav class="navbar navbar-expand-lg px-4 py-2 bg-white shadow"><a class="sidebar-toggler text-gray-500 me-4 me-lg-5 lead" href="#"><i class="fas fa-align-left"></i></a><a class="navbar-brand fw-bold text-uppercase text-base" href="index.jsp"><span class="d-none d-brand-partial">Bubbly </span><span class="d-none d-sm-inline">Dashboard</span></a>
        <ul class="ms-auto d-flex align-items-center list-unstyled mb-0">
          <li class="nav-item dropdown">
            <form class="ms-auto me-4 d-none d-lg-block" id="searchForm">
              <div class="input-group input-group-sm input-group-navbar">
                <input class="form-control" id="searchInput" type="search" placeholder="Search">
                <button class="btn" type="button"> <i class="fas fa-search"></i></button>
              </div>
            </form>
            <div class="dropdown-menu dropdown-menu-animated text-sm" id="searchDropdownMenu">
              <h6 class="dropdown-header text-uppercase fw-normal">Recent pages</h6><a class="dropdown-item py-1" href="cms-post.jsp"> <i class="far fa-file me-2"> </i>Posts</a><a class="dropdown-item py-1" href="widgets-stats.jsp"> <i class="far fa-file me-2"> </i>Widgets</a><a class="dropdown-item py-1" href="pages-profile.jsp"> <i class="far fa-file me-2"> </i>Profile</a>
              <div class="dropdown-divider"></div>
              <h6 class="dropdown-header text-uppercase fw-normal">Users</h6><a class="dropdown-item py-1" href="pages-profile.jsp"> <img class="avatar avatar-xs p-1 me-2" src="../lib/img/avatar-0.jpg" alt="Jason Doe"><span>Jason Doe</span></a><a class="dropdown-item py-1" href="pages-profile.jsp"> <img class="avatar avatar-xs p-1 me-2" src="../lib/img/avatar-1.jpg" alt="Frank Williams"><span>Frank Williams</span></a><a class="dropdown-item py-1" href="pages-profile.jsp"> <img class="avatar avatar-xs p-1 me-2" src="../lib/img/avatar-2.jpg" alt="Ashley Wood"><span>Ashley Wood</span></a>
              <div class="dropdown-divider"></div>
              <h6 class="dropdown-header text-uppercase fw-normal">Filters</h6><a class="dropdown-item py-1" href="#!"> <span class="badge me-2 badge-success-light">Posts</span><span class="text-xs">Search all posts</span></a><a class="dropdown-item py-1" href="#!"> <span class="badge me-2 badge-danger-light">Users</span><span class="text-xs">Only in users</span></a><a class="dropdown-item py-1" href="#!"> <span class="badge me-2 badge-warning-light">Campaigns</span><span class="text-xs">Only in campaigns</span></a>
            </div>
          </li>
          <li class="nav-item dropdown me-2"><a class="nav-link nav-link-icon text-gray-400 px-1" id="notifications" href="#" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <svg class="svg-icon svg-icon-md svg-icon-heavy">
                    <use xlink:href="../lib/icons/orion-svg-sprite.svg#sales-up-1"> </use>
                  </svg><span class="notification-badge bg-green"></span></a>
            <div class="dropdown-menu dropdown-menu-end dropdown-menu-animated text-sm" aria-labelledby="notifications"><a class="dropdown-item" href="#">
                <div class="d-flex align-items-center">
                  <div class="icon icon-sm bg-indigo text-white"><i class="fab fa-twitter"></i></div>
                  <div class="text ms-2">
                    <p class="mb-0">You have 2 followers</p>
                  </div>
                </div></a><a class="dropdown-item" href="#"> 
                <div class="d-flex align-items-center">
                  <div class="icon icon-sm bg-green text-white"><i class="fas fa-envelope"></i></div>
                  <div class="text ms-2">
                    <p class="mb-0">You have 6 new messages</p>
                  </div>
                </div></a><a class="dropdown-item" href="#">
                <div class="d-flex align-items-center">
                  <div class="icon icon-sm bg-blue text-white"><i class="fas fa-upload"></i></div>
                  <div class="text ms-2">
                    <p class="mb-0">Server rebooted</p>
                  </div>
                </div></a><a class="dropdown-item" href="#">
                <div class="d-flex align-items-center">
                  <div class="icon icon-sm bg-indigo text-white"><i class="fab fa-twitter"></i></div>
                  <div class="text ms-2">
                    <p class="mb-0">You have 2 followers</p>
                  </div>
                </div></a>
              <div class="dropdown-divider"></div><a class="dropdown-item text-center" href="#"><small class="fw-bold text-uppercase">View all notifications</small></a>
            </div>
          </li>
          <!-- Messages                        -->
          <li class="nav-item dropdown me-2 me-lg-3"> <a class="nav-link nav-link-icon text-gray-400 px-1" id="messages" href="#" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <svg class="svg-icon svg-icon-md svg-icon-heavy">
                    <use xlink:href="../lib/icons/orion-svg-sprite.svg#paper-plane-1"> </use>
                  </svg><span class="notification-badge notification-badge-number bg-primary">10</span></a>
            <div class="dropdown-menu dropdown-menu-end dropdown-menu-animated text-sm" aria-labelledby="messages"><a class="dropdown-item d-flex align-items-center p-3" href="#"> <img class="avatar avatar-sm p-1 me-2" src="../lib/img/avatar-0.jpg" alt="Jason Doe">
                <div class="pt-1">
                  <h6 class="fw-bold mb-0">Jason Doe</h6><span class="text-muted text-sm">Sent you a message</span>
                </div></a><a class="dropdown-item d-flex align-items-center p-3" href="#"> <img class="avatar avatar-sm p-1 me-2" src="../lib/img/avatar-1.jpg" alt="Frank Williams">
                <div class="pt-1">
                  <h6 class="fw-bold mb-0">Frank Williams</h6><span class="text-muted text-sm">Sent you a message</span>
                </div></a><a class="dropdown-item d-flex align-items-center p-3" href="#"> <img class="avatar avatar-sm p-1 me-2" src="../lib/img/avatar-2.jpg" alt="Ashley Wood">
                <div class="pt-1">
                  <h6 class="fw-bold mb-0">Ashley Wood</h6><span class="text-muted text-sm">Sent you a message</span>
                </div></a>
              <div class="dropdown-divider"></div><a class="dropdown-item text-center" href="#"> <small class="fw-bold text-uppercase">View all messages                          </small></a>
            </div>
          </li>
          <li class="nav-item dropdown ms-auto"><a class="nav-link pe-0" id="userInfo" href="#" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img class="avatar p-1" src="../lib/img/avatar-6.jpg" alt="Jason Doe"></a>
            <div class="dropdown-menu dropdown-menu-end dropdown-menu-animated" aria-labelledby="userInfo">
              <div class="dropdown-header text-gray-700">
                <h6 class="text-uppercase font-weight-bold">Mark Stephen</h6><small>Web Developer</small>
              </div>
              <div class="dropdown-divider"></div><a class="dropdown-item" href="#">Settings</a><a class="dropdown-item" href="#">Activity log       </a>
              <div class="dropdown-divider"></div><a class="dropdown-item" href="login.jsp">Logout</a>
            </div>
          </li>
        </ul>
      </nav>
    </header>
    <div class="d-flex align-items-stretch">
      <div class="sidebar py-3" id="sidebar">
        <h6 class="sidebar-heading">Main</h6>
        <ul class="list-unstyled">
              <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="index.jsp">
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#real-estate-1"> </use>
                      </svg><span class="sidebar-link-title">Dashboard</span></a></li>
              <li class="sidebar-list-item"><a class="sidebar-link text-muted " href="#" data-bs-target="#cmsDropdown" role="button" aria-expanded="false" data-bs-toggle="collapse"> 
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#reading-1"> </use>
                      </svg><span class="sidebar-link-title">CMS </span></a>
                <ul class="sidebar-menu list-unstyled collapse " id="cmsDropdown">
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="cms-post.jsp">Posts</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="cms-post-new.jsp">Add new post</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="cms-category.jsp">Categories</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="cms-media.jsp">Media library</a></li>
                </ul>
              </li>
              <li class="sidebar-list-item"><a class="sidebar-link text-muted " href="#" data-bs-target="#widgetsDropdown" role="button" aria-expanded="false" data-bs-toggle="collapse"> 
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#statistic-1"> </use>
                      </svg><span class="sidebar-link-title">Widgets </span></a>
                <ul class="sidebar-menu list-unstyled collapse " id="widgetsDropdown">
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="widgets-stats.jsp">Stats</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="widgets-data.jsp">Data</a></li>
                </ul>
              </li>
              <li class="sidebar-list-item"><a class="sidebar-link text-muted active" href="#" data-bs-target="#e-commerceDropdown" role="button" aria-expanded="true" data-bs-toggle="collapse"> 
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#delivery-truck-1"> </use>
                      </svg><span class="sidebar-link-title">E-commerce </span></a>
                <ul class="sidebar-menu list-unstyled collapse show" id="e-commerceDropdown">
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="e-commerce-products.jsp">Products</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="e-commerce-product-new.jsp">Products - New</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="e-commerce-orders.jsp">Orders</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link active text-muted" href="e-commerce-order.jsp">Order - Detail</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="e-commerce-customers.jsp">Customers</a></li>
                </ul>
              </li>
              <li class="sidebar-list-item"><a class="sidebar-link text-muted " href="#" data-bs-target="#pagesDropdown" role="button" aria-expanded="false" data-bs-toggle="collapse"> 
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#paper-stack-1"> </use>
                      </svg><span class="sidebar-link-title">Pages </span></a>
                <ul class="sidebar-menu list-unstyled collapse " id="pagesDropdown">
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="pages-profile.jsp">Profile</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="pages-pricing.jsp">Pricing table</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="pages-contacts.jsp">Contacts</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="pages-invoice.jsp">Invoice</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="pages-knowledge-base.jsp">Knowledge base</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="pages-knowledge-base-topic.jsp">Knowledge base - Topic</a></li>
                </ul>
              </li>
              <li class="sidebar-list-item"><a class="sidebar-link text-muted " href="#" data-bs-target="#userDropdown" role="button" aria-expanded="false" data-bs-toggle="collapse"> 
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#man-1"> </use>
                      </svg><span class="sidebar-link-title">User </span></a>
                <ul class="sidebar-menu list-unstyled collapse " id="userDropdown">
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="login.jsp">Login page</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="register.jsp">Register</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="login-2.jsp">Login v.2 <span class="badge bg-info ms-2 text-decoration-none">New</span></a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="register-2.jsp">Register v.2 <span class="badge bg-info ms-2 text-decoration-none">New</span></a></li>
                </ul>
              </li>
              <li class="sidebar-list-item"><a class="sidebar-link text-muted " href="#" data-bs-target="#componentsDropdown" role="button" aria-expanded="false" data-bs-toggle="collapse"> 
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#sorting-1"> </use>
                      </svg><span class="sidebar-link-title">Components </span></a>
                <ul class="sidebar-menu list-unstyled collapse " id="componentsDropdown">
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="components-cards.jsp">Cards</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="components-calendar.jsp">Calendar</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="components-gallery.jsp">Gallery</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="components-loading-buttons.jsp">Loading buttons</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="components-map.jsp">Maps</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="components-notifications.jsp">Notifications</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="components-preloader.jsp">Preloaders</a></li>
                </ul>
              </li>
              <li class="sidebar-list-item"><a class="sidebar-link text-muted " href="#" data-bs-target="#chartsDropdown" role="button" aria-expanded="false" data-bs-toggle="collapse"> 
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#pie-chart-1"> </use>
                      </svg><span class="sidebar-link-title">Charts </span></a>
                <ul class="sidebar-menu list-unstyled collapse " id="chartsDropdown">
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="charts.jsp">Charts</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="charts-gauge-sparkline.jsp">Gauge + Sparkline</a></li>
                </ul>
              </li>
              <li class="sidebar-list-item"><a class="sidebar-link text-muted " href="#" data-bs-target="#formsDropdown" role="button" aria-expanded="false" data-bs-toggle="collapse"> 
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#file-storage-1"> </use>
                      </svg><span class="sidebar-link-title">Forms </span></a>
                <ul class="sidebar-menu list-unstyled collapse " id="formsDropdown">
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="forms.jsp">Basic forms</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="forms-advanced.jsp">Advanced forms</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="forms-dropzone.jsp">Files upload</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="forms-texteditor.jsp">Text editor</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="forms-validation.jsp">Validation</a></li>
                </ul>
              </li>
              <li class="sidebar-list-item"><a class="sidebar-link text-muted " href="#" data-bs-target="#tablesDropdown" role="button" aria-expanded="false" data-bs-toggle="collapse"> 
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#grid-1"> </use>
                      </svg><span class="sidebar-link-title">Tables </span></a>
                <ul class="sidebar-menu list-unstyled collapse " id="tablesDropdown">
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="tables.jsp">Bootstrap tables</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="tables-datatable.jsp">Datatable</a></li>
                </ul>
              </li>
        </ul>
        <h6 class="sidebar-heading">Docs</h6>
        <ul class="list-unstyled">
              <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="docs/introduction.jsp">
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#angle-brackets-1"> </use>
                      </svg><span class="sidebar-link-title">Introduction</span></a></li>
              <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="docs/directory-structure.jsp">
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#table-content-1"> </use>
                      </svg><span class="sidebar-link-title">Directory structure</span></a></li>
              <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="docs/gulp.jsp">
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#keyboard-1"> </use>
                      </svg><span class="sidebar-link-title">Gulp.js</span></a></li>
              <li class="sidebar-list-item"><a class="sidebar-link text-muted " href="#" data-bs-target="#cssDropdown" role="button" aria-expanded="false" data-bs-toggle="collapse"> 
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#design-1"> </use>
                      </svg><span class="sidebar-link-title">CSS </span></a>
                <ul class="sidebar-menu list-unstyled collapse " id="cssDropdown">
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="docs/components-theme.jsp">CSS Components</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="docs/customizing-css.jsp">Customizing CSS</a></li>
                </ul>
              </li>
              <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="docs/credits.jsp">
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#star-medal-1"> </use>
                      </svg><span class="sidebar-link-title">Credits</span></a></li>
              <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="docs/changelog.jsp">
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#new-1"> </use>
                      </svg><span class="sidebar-link-title">Changelog</span></a></li>
        </ul>
      </div>
      <div class="page-holder bg-gray-100">
        <div class="container-fluid px-lg-4 px-xl-5">
              <!-- Breadcrumbs -->
              <div class="page-breadcrumb">
                <ul class="breadcrumb">
                  <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                  <li class="breadcrumb-item"><a href="e-commerce-orders.jsp">Orders</a></li>
                  <li class="breadcrumb-item active">Order #25646     </li>
                </ul>
              </div>
          <!-- Page Header-->
          <div class="page-header d-md-flex justify-content-between">
            <div>
              <h1 class="page-heading">Order #25646</h1>
              <ul class="list-inline text-sm">
                <li class="list-inline-item"><a class="text-gray-600" href="#!"><i class="fas fa-download me-2"> </i>Export</a></li>
                <li class="list-inline-item"><a class="text-gray-600" href="#!"><i class="fas fa-print me-2"> </i>Print                        </a></li>
                <li class="list-inline-item">
                  <div class="dropdown"><a class="text-gray-600" id="moreActions" href="#!" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-plus-circle me-2"> </i>More actions <i class="fas fa-angle-down"></i></a>
                    <ul class="dropdown-menu text-sm" aria-labelledby="moreActions">
                      <li><a class="dropdown-item text-muted" href="#">Action</a></li>
                      <li><a class="dropdown-item text-muted" href="#">Another action</a></li>
                      <li><a class="dropdown-item text-muted" href="#">Something else here </a></li>
                    </ul>
                  </div>
                </li>
              </ul>
            </div>
            <div class="mt-2"> 
              <p class="mb-2"> <span class="text-base badge badge-success-light me-2 mb-2"><span class="indicator"> </span>Paid</span><span class="text-base badge badge-warning-light"> <span class="indicator"> </span>Being Processed</span></p>
              <p class="text-end text-muted text-sm"><i class="far fa-calendar me-2"></i> Jun 22, 2021 &mdash; 10:25 AM</p>
            </div>
          </div>
          <div class="row mb-5">
            <div class="col-lg-8">
              <div class="card card-table mb-4">
                <div class="card-header">
                  <h5 class="card-heading"> Order details</h5>
                      <div class="card-header-more">
                        <button class="btn" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-ellipsis-v"></i></button>
                        <div class="dropdown-menu dropdown-menu-end text-sm"><a class="dropdown-item" href="#!"><i class="fas fa-expand-arrows-alt me-2"></i>Expand</a><a class="dropdown-item" href="#!"><i class="far fa-window-minimize me-2"></i>Minimize</a><a class="dropdown-item" href="#!"><i class="fas fa-redo me-2"></i> Reload</a><a class="dropdown-item" href="#!"><i class="far fa-trash-alt me-2"></i> Remove        </a></div>
                      </div>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-hover table-borderless mb-0">
                      <thead class="light">
                        <tr>
                          <th style="min-width: 300px;">Product</th>
                          <th>Items</th>
                          <th>Price</th>
                          <th class="text-end">Total</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr class="align-middle">
                          <td> <img class="card-table-img img-fluid me-3" src="../lib/img/product/product-1.jpg" alt="" width="80"><span class="card-table-text"><a class="text-reset text-decoration-none" href="#!"><strong>Round grey hanging decor</strong><br><span class="text-muted">Size: M</span></a></span></td>
                          <td>1 </td>
                          <td>$22.00</td>
                          <td class="text-end">$22.00</td>
                        </tr>
                        <tr class="align-middle">
                          <td> <img class="card-table-img img-fluid me-3" src="../lib/img/product/product-2.jpg" alt="" width="80"><span class="card-table-text"><a class="text-reset text-decoration-none" href="#!"><strong>silver black round Ipod</strong></a></span></td>
                          <td>1 </td>
                          <td>$22.00</td>
                          <td class="text-end">$22.00</td>
                        </tr>
                        <tr class="align-middle">
                          <td> <img class="card-table-img img-fluid me-3" src="../lib/img/product/product-3.jpg" alt="" width="80"><span class="card-table-text"><a class="text-reset text-decoration-none" href="#!"><strong>White USB cable</strong></a></span></td>
                          <td>1 </td>
                          <td>$22.00</td>
                          <td class="text-end">$22.00</td>
                        </tr>
                        <tr class="align-middle">
                          <td> <img class="card-table-img img-fluid me-3" src="../lib/img/product/product-4.jpg" alt="" width="80"><span class="card-table-text"><a class="text-reset text-decoration-none" href="#!"><strong>Sony PS 4 game controller</strong></a></span></td>
                          <td>1 </td>
                          <td>$22.00</td>
                          <td class="text-end">$22.00</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
              <div class="row gy-4">
                <div class="col-lg-6">
                  <div class="card">
                    <div class="card-header">
                      <h5 class="card-heading"> Invoice address</h5>
                          <div class="card-header-more">
                            <button class="btn" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-ellipsis-v"></i></button>
                            <div class="dropdown-menu dropdown-menu-end text-sm"><a class="dropdown-item" href="#!"><i class="fas fa-expand-arrows-alt me-2"></i>Expand</a><a class="dropdown-item" href="#!"><i class="far fa-window-minimize me-2"></i>Minimize</a><a class="dropdown-item" href="#!"><i class="fas fa-redo me-2"></i> Reload</a><a class="dropdown-item" href="#!"><i class="far fa-trash-alt me-2"></i> Remove        </a></div>
                          </div>
                    </div>
                    <div class="card-body">
                      <p class="card-text text-muted">John Brown<br>13/25 New Avenue<br>New Heaven<br>45Y 73J<br>England<br><strong>Great Britain</strong><span class="ms-2 align-middle mt-n1 avatar avatar-xxs bg-cover" style="background-image: url(img/flag/gb.svg)"></span></p>
                    </div>
                  </div>
                </div>
                <div class="col-lg-6">
                  <div class="card">
                    <div class="card-header">
                      <h5 class="card-heading"> Shipping address</h5>
                          <div class="card-header-more">
                            <button class="btn" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-ellipsis-v"></i></button>
                            <div class="dropdown-menu dropdown-menu-end text-sm"><a class="dropdown-item" href="#!"><i class="fas fa-expand-arrows-alt me-2"></i>Expand</a><a class="dropdown-item" href="#!"><i class="far fa-window-minimize me-2"></i>Minimize</a><a class="dropdown-item" href="#!"><i class="fas fa-redo me-2"></i> Reload</a><a class="dropdown-item" href="#!"><i class="far fa-trash-alt me-2"></i> Remove        </a></div>
                          </div>
                    </div>
                    <div class="card-body">
                      <p class="card-text text-muted">John Brown<br>13/25 New Avenue<br>New Heaven<br>45Y 73J<br>England<br><strong>Great Britain</strong><span class="ms-2 align-middle mt-n1 avatar avatar-xxs bg-cover" style="background-image: url(img/flag/gb.svg)"></span></p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4">
              <div class="card card-table">
                <div class="card-header">
                  <h5 class="card-heading">Finance</h5>
                      <div class="card-header-more">
                        <button class="btn" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-ellipsis-v"></i></button>
                        <div class="dropdown-menu dropdown-menu-end text-sm"><a class="dropdown-item" href="#!"><i class="fas fa-expand-arrows-alt me-2"></i>Expand</a><a class="dropdown-item" href="#!"><i class="far fa-window-minimize me-2"></i>Minimize</a><a class="dropdown-item" href="#!"><i class="fas fa-redo me-2"></i> Reload</a><a class="dropdown-item" href="#!"><i class="far fa-trash-alt me-2"></i> Remove        </a></div>
                      </div>
                </div>
                <div class="card-body">
                  <div class="table-responsive my-3">
                    <table class="table table-borderless card-text">
                      <tr class="text-muted"> 
                        <th class="fw-normal">Products</th>
                        <td class="text-end">$165.00</td>
                      </tr>
                      <tr class="text-muted">
                        <th class="fw-normal">Shipping Fee</th>
                        <td class="text-end">$5.00</td>
                      </tr>
                      <tr class="text-muted">
                        <th class="fw-normal">Sales Tax</th>
                        <td class="text-end">$7.00</td>
                      </tr>
                      <tr>
                        <th>Total</th>
                        <td class="text-end">$177.00</td>
                      </tr>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <footer class="footer bg-white shadow align-self-end py-3 px-xl-5 w-100">
          <div class="container-fluid">
            <div class="row">
              <div class="col-md-6 text-center text-md-start fw-bold">
                <p class="mb-2 mb-md-0 fw-bold">Your company &copy; 2021</p>
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