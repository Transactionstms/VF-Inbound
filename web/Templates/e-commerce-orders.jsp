<%-- 
    Document   : e-commerce-orders
    Created on : 20/12/2021, 01:29:54 PM
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
                  <li class="sidebar-list-item"><a class="sidebar-link active text-muted" href="e-commerce-orders.jsp">Orders</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="e-commerce-order.jsp">Order - Detail</a></li>
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
                  <li class="breadcrumb-item active">Orders     </li>
                </ul>
              </div>
          <!-- Page Header-->
          <div class="page-header">
            <h1 class="page-heading">Orders</h1>
            <ul class="list-inline text-sm">
              <li class="list-inline-item"><a class="text-gray-600" href="#!"><i class="fas fa-upload me-2"> </i>Import</a></li>
              <li class="list-inline-item"><a class="text-gray-600" href="#!"><i class="fas fa-download me-2"> </i>Export</a></li>
            </ul>
          </div>
          <div class="card card-table mb-4">
            <div class="card-body">
              <div class="preload-wrapper">
                <div class="table-responsive">
                  <table class="table table-hover mb-0" id="ordersDatatable">
                    <thead>
                      <tr>
                        <th>Order Id</th>
                        <th>Name</th>
                        <th>Date</th>
                        <th>Total Price</th>
                        <th>Status</th>
                        <th>Review</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check0">
                            <label class="form-check-label" for="check0">#2458</label></span></td>
                        <td> <strong>Nielsen Cobb</strong><br><span class="text-muted text-sm">nielsencobb@memora.com</span></td>
                        <td>2021/01/25</td>
                        <td>$530.89</td>
                        <td><span class="badge badge-success-light">Open</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check1">
                            <label class="form-check-label" for="check1">#2459</label></span></td>
                        <td> <strong>Margret Cote</strong><br><span class="text-muted text-sm">margretcote@zilidium.com</span></td>
                        <td>2021/12/28</td>
                        <td>$510.58</td>
                        <td><span class="badge badge-danger-light">Closed</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check2">
                            <label class="form-check-label" for="check2">#2460</label></span></td>
                        <td> <strong>Rachel Vinson</strong><br><span class="text-muted text-sm">rachelvinson@chorizon.com</span></td>
                        <td>2021/07/03</td>
                        <td>$840.25</td>
                        <td><span class="badge badge-warning-light">On Hold</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check3">
                            <label class="form-check-label" for="check3">#2461</label></span></td>
                        <td> <strong>Gabrielle Aguirre</strong><br><span class="text-muted text-sm">gabrielleaguirre@comverges.com</span></td>
                        <td>2021/06/03</td>
                        <td>$480.16</td>
                        <td><span class="badge badge-info-light">In Progress</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check4">
                            <label class="form-check-label" for="check4">#2462</label></span></td>
                        <td> <strong>Spears Collier</strong><br><span class="text-muted text-sm">spearscollier@remold.com</span></td>
                        <td>2021/12/18</td>
                        <td>$490.88</td>
                        <td><span class="badge badge-success-light">Open</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check5">
                            <label class="form-check-label" for="check5">#2463</label></span></td>
                        <td> <strong>Keisha Thomas</strong><br><span class="text-muted text-sm">keishathomas@euron.com</span></td>
                        <td>2021/03/17</td>
                        <td>$590.04</td>
                        <td><span class="badge badge-danger-light">Closed</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check6">
                            <label class="form-check-label" for="check6">#2464</label></span></td>
                        <td> <strong>Elisabeth Key</strong><br><span class="text-muted text-sm">elisabethkey@netagy.com</span></td>
                        <td>2021/11/08</td>
                        <td>$760.10</td>
                        <td><span class="badge badge-warning-light">On Hold</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check7">
                            <label class="form-check-label" for="check7">#2465</label></span></td>
                        <td> <strong>Patel Mack</strong><br><span class="text-muted text-sm">patelmack@zedalis.com</span></td>
                        <td>2021/10/23</td>
                        <td>$720.16</td>
                        <td><span class="badge badge-info-light">In Progress</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check8">
                            <label class="form-check-label" for="check8">#2466</label></span></td>
                        <td> <strong>Erika Whitaker</strong><br><span class="text-muted text-sm">erikawhitaker@uniworld.com</span></td>
                        <td>2021/07/09</td>
                        <td>$790.19</td>
                        <td><span class="badge badge-success-light">Open</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check9">
                            <label class="form-check-label" for="check9">#2467</label></span></td>
                        <td> <strong>Meyers Swanson</strong><br><span class="text-muted text-sm">meyersswanson@candecor.com</span></td>
                        <td>2021/01/24</td>
                        <td>$200.98</td>
                        <td><span class="badge badge-danger-light">Closed</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check10">
                            <label class="form-check-label" for="check10">#2468</label></span></td>
                        <td> <strong>Townsend Sloan</strong><br><span class="text-muted text-sm">townsendsloan@rameon.com</span></td>
                        <td>2021/05/09</td>
                        <td>$860.05</td>
                        <td><span class="badge badge-warning-light">On Hold</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check11">
                            <label class="form-check-label" for="check11">#2469</label></span></td>
                        <td> <strong>Millicent Henry</strong><br><span class="text-muted text-sm">millicenthenry@balooba.com</span></td>
                        <td>2021/06/25</td>
                        <td>$600.50</td>
                        <td><span class="badge badge-info-light">In Progress</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check12">
                            <label class="form-check-label" for="check12">#2470</label></span></td>
                        <td> <strong>Madelyn Brock</strong><br><span class="text-muted text-sm">madelynbrock@combogene.com</span></td>
                        <td>2021/04/03</td>
                        <td>$130.71</td>
                        <td><span class="badge badge-success-light">Open</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check13">
                            <label class="form-check-label" for="check13">#2471</label></span></td>
                        <td> <strong>Jenkins Carney</strong><br><span class="text-muted text-sm">jenkinscarney@dadabase.com</span></td>
                        <td>2021/02/18</td>
                        <td>$620.25</td>
                        <td><span class="badge badge-danger-light">Closed</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check14">
                            <label class="form-check-label" for="check14">#2472</label></span></td>
                        <td> <strong>Grimes Delaney</strong><br><span class="text-muted text-sm">grimesdelaney@progenex.com</span></td>
                        <td>2021/03/15</td>
                        <td>$480.55</td>
                        <td><span class="badge badge-warning-light">On Hold</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check15">
                            <label class="form-check-label" for="check15">#2473</label></span></td>
                        <td> <strong>Valencia Rivera</strong><br><span class="text-muted text-sm">valenciarivera@xleen.com</span></td>
                        <td>2021/07/14</td>
                        <td>$840.80</td>
                        <td><span class="badge badge-info-light">In Progress</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check16">
                            <label class="form-check-label" for="check16">#2474</label></span></td>
                        <td> <strong>Helene Solomon</strong><br><span class="text-muted text-sm">helenesolomon@zomboid.com</span></td>
                        <td>2021/09/10</td>
                        <td>$260.72</td>
                        <td><span class="badge badge-success-light">Open</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check17">
                            <label class="form-check-label" for="check17">#2475</label></span></td>
                        <td> <strong>Kathleen Holman</strong><br><span class="text-muted text-sm">kathleenholman@hotcakes.com</span></td>
                        <td>2021/06/09</td>
                        <td>$590.24</td>
                        <td><span class="badge badge-danger-light">Closed</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check18">
                            <label class="form-check-label" for="check18">#2476</label></span></td>
                        <td> <strong>Merrill Garrett</strong><br><span class="text-muted text-sm">merrillgarrett@affluex.com</span></td>
                        <td>2021/07/03</td>
                        <td>$320.84</td>
                        <td><span class="badge badge-warning-light">On Hold</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                      <tr class="align-middle">
                        <td> <span class="form-check">
                            <input class="form-check-input" type="checkbox" id="check19">
                            <label class="form-check-label" for="check19">#2477</label></span></td>
                        <td> <strong>Sallie Booth</strong><br><span class="text-muted text-sm">salliebooth@ziggles.com</span></td>
                        <td>2021/08/23</td>
                        <td>$381.00</td>
                        <td><span class="badge badge-info-light">In Progress</span></td>
                        <td class="text-end" style="min-width: 125px;"><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-warning"></i></a><a href="#!"><i class="fa fa-star text-gray-500"></i></a></td>
                      </tr>
                    </tbody>
                  </table>
                </div><span class="me-2" id="categoryBulkAction">
                  <select class="form-select form-select-sm d-inline w-auto" name="categoryBulkAction">
                    <option>Bulk Actions</option>
                    <option>Delete</option>
                  </select>
                  <button class="btn btn-sm btn-outline-primary align-top">Apply</button></span>
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
    <!-- Data Tables-->
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"></script>
    <!-- CMS Category JS-->
    <script src="../lib/js/e-commerce-orders.js">    </script>
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