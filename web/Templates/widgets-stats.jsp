<%-- 
    Document   : widgets-stats
    Created on : 20/12/2021, 01:46:25 PM
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
    <link rel="stylesheet" href=../lib/css/custom.css">
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
              <li class="sidebar-list-item"><a class="sidebar-link text-muted active" href="#" data-bs-target="#widgetsDropdown" role="button" aria-expanded="true" data-bs-toggle="collapse"> 
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#statistic-1"> </use>
                      </svg><span class="sidebar-link-title">Widgets </span></a>
                <ul class="sidebar-menu list-unstyled collapse show" id="widgetsDropdown">
                  <li class="sidebar-list-item"><a class="sidebar-link active text-muted" href="widgets-stats.jsp">Stats</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="widgets-data.jsp">Data</a></li>
                </ul>
              </li>
              <li class="sidebar-list-item"><a class="sidebar-link text-muted " href="#" data-bs-target="#e-commerceDropdown" role="button" aria-expanded="false" data-bs-toggle="collapse"> 
                      <svg class="svg-icon svg-icon-md me-3">
                        <use xlink:href="../lib/icons/orion-svg-sprite.svg#delivery-truck-1"> </use>
                      </svg><span class="sidebar-link-title">E-commerce </span></a>
                <ul class="sidebar-menu list-unstyled collapse " id="e-commerceDropdown">
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="e-commerce-products.jsp">Products</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="e-commerce-product-new.jsp">Products - New</a></li>
                  <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="e-commerce-orders.jsp">Orders</a></li>
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
                  <li class="breadcrumb-item active">Stats Widgets     </li>
                </ul>
              </div>
              <!-- Page Header-->
              <div class="page-header">
                <h1 class="page-heading">Stats Widgets</h1>
              </div>
          <section>
            <div class="row mb-5">
              <div class="col-sm-6 col-lg-3 mb-4">
                <div class="card h-100">
                  <div class="card-body">
                    <div class="d-flex align-items-center justify-content-between">
                      <div>
                        <h4 class="fw-normal text-red">$10,500</h4>
                        <p class="subtitle text-sm text-muted mb-0">Earnings</p>
                      </div>
                      <div class="flex-shrink-0 ms-3">
                            <svg class="svg-icon text-red">
                              <use xlink:href="../lib/icons/orion-svg-sprite.svg#speed-1"> </use>
                            </svg>
                      </div>
                    </div>
                  </div>
                  <div class="card-footer py-3 bg-red-light">
                    <div class="row align-items-center text-red">
                      <div class="col-10">
                        <p class="mb-0">20% increase</p>
                      </div>
                      <div class="col-2 text-end"><i class="fas fa-caret-up"></i>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-sm-6 col-lg-3 mb-4">
                <div class="card h-100">
                  <div class="card-body">
                    <div class="d-flex align-items-center justify-content-between">
                      <div>
                        <h4 class="fw-normal text-blue">584</h4>
                        <p class="subtitle text-sm text-muted mb-0">Readers</p>
                      </div>
                      <div class="flex-shrink-0 ms-3">
                            <svg class="svg-icon text-blue">
                              <use xlink:href="../lib/icons/orion-svg-sprite.svg#news-1"> </use>
                            </svg>
                      </div>
                    </div>
                  </div>
                  <div class="card-footer py-3 bg-blue-light">
                    <div class="row align-items-center text-blue">
                      <div class="col-10">
                        <p class="mb-0">3% increase</p>
                      </div>
                      <div class="col-2 text-end"><i class="fas fa-caret-up"></i>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-sm-6 col-lg-3 mb-4">
                <div class="card h-100">
                  <div class="card-body">
                    <div class="d-flex align-items-center justify-content-between">
                      <div>
                        <h4 class="fw-normal text-primary">876</h4>
                        <p class="subtitle text-sm text-muted mb-0">Bookmarks</p>
                      </div>
                      <div class="flex-shrink-0 ms-3">
                            <svg class="svg-icon text-primary">
                              <use xlink:href="../lib/icons/orion-svg-sprite.svg#bookmark-1"> </use>
                            </svg>
                      </div>
                    </div>
                  </div>
                  <div class="card-footer py-3 bg-primary-light">
                    <div class="row align-items-center text-primary">
                      <div class="col-10">
                        <p class="mb-0">10% increase</p>
                      </div>
                      <div class="col-2 text-end"><i class="fas fa-caret-up"></i>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-sm-6 col-lg-3 mb-4">
                <div class="card h-100">
                  <div class="card-body">
                    <div class="d-flex align-items-center justify-content-between">
                      <div>
                        <h4 class="fw-normal text-green">3,500</h4>
                        <p class="subtitle text-sm text-muted mb-0">Visitors</p>
                      </div>
                      <div class="flex-shrink-0 ms-3">
                            <svg class="svg-icon text-green">
                              <use xlink:href="../lib/icons/orion-svg-sprite.svg#world-map-1"> </use>
                            </svg>
                      </div>
                    </div>
                  </div>
                  <div class="card-footer py-3 bg-green-light">
                    <div class="row align-items-center text-green">
                      <div class="col-10">
                        <p class="mb-0">5% decrease</p>
                      </div>
                      <div class="col-2 text-end"><i class="fas fa-caret-down"></i>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row mb-5">
              <div class="col-6 col-md-4 mb-4">
                <div class="card h-100">
                  <div class="card-body p-3 p-sm-4 d-flex align-items-center justify-content-between">
                    <div>
                      <div class="subtitle mb-2 text-muted">All Earnings</div>
                      <h4 class="fw-normal h2 text-primary">$19,200</h4>
                      <p class="mb-0">$2,123 <i class="fas fa-arrow-up ms-2 text-primary"></i></p>
                    </div>
                    <div class="d-none d-md-flex icon icon-xl ms-2 bg-primary-light">
                          <svg class="svg-icon text-primary">
                            <use xlink:href="../lib/icons/orion-svg-sprite.svg#pay-1"> </use>
                          </svg>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-6 col-md-4 mb-4">
                <div class="card h-100">
                  <div class="card-body p-3 p-sm-4 d-flex align-items-center justify-content-between">
                    <div>
                      <div class="subtitle mb-2 text-muted">Offers</div>
                      <h4 class="fw-normal h2 text-green">$12,500</h4>
                      <p class="mb-0">$2,123 <i class="fas fa-arrow-up ms-2 text-green"></i></p>
                    </div>
                    <div class="d-none d-md-flex icon icon-xl ms-2 bg-green-light">
                          <svg class="svg-icon text-green">
                            <use xlink:href="../lib/icons/orion-svg-sprite.svg#label-tag-1"> </use>
                          </svg>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-6 col-md-4 mb-4">
                <div class="card h-100">
                  <div class="card-body p-3 p-sm-4 d-flex align-items-center justify-content-between">
                    <div>
                      <div class="subtitle mb-2 text-muted">Bestsellers</div>
                      <h4 class="fw-normal h2 text-indigo">$10,800</h4>
                      <p class="mb-0">$2,123 <i class="fas fa-arrow-up ms-2 text-indigo"></i></p>
                    </div>
                    <div class="d-none d-md-flex icon icon-xl ms-2 bg-indigo-light">
                          <svg class="svg-icon text-indigo">
                            <use xlink:href="../lib/icons/orion-svg-sprite.svg#star-medal-1"> </use>
                          </svg>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row mb-5">
              <div class="col-6 col-md-3 mb-4">
                <div class="card text-center h-100">
                  <div class="card-body">
                    <h6 class="mb-3">Earnings</h6>
                    <h4 class="mb-3"><i class="fas fa-arrow-up text-success me-2"></i>$10,500
                    </h4>
                    <p class="card-text">20% increase</p>
                  </div>
                </div>
              </div>
              <div class="col-6 col-md-3 mb-4">
                <div class="card text-center h-100">
                  <div class="card-body">
                    <h6 class="mb-3">Readers</h6>
                    <h4 class="mb-3"><i class="fas fa-arrow-up text-success me-2"></i>584
                    </h4>
                    <p class="card-text">3% increase</p>
                  </div>
                </div>
              </div>
              <div class="col-6 col-md-3 mb-4">
                <div class="card text-center h-100">
                  <div class="card-body">
                    <h6 class="mb-3">Bookmarks</h6>
                    <h4 class="mb-3"><i class="fas fa-arrow-up text-success me-2"></i>876
                    </h4>
                    <p class="card-text">10% increase</p>
                  </div>
                </div>
              </div>
              <div class="col-6 col-md-3 mb-4">
                <div class="card text-center h-100">
                  <div class="card-body">
                    <h6 class="mb-3">Visitors</h6>
                    <h4 class="mb-3"><i class="fas fa-arrow-down text-danger me-2"> </i>3,500
                    </h4>
                    <p class="card-text">5% decrease</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row mb-5">
              <div class="col-md-4 mb-4">
                <div class="card h-100">
                  <div class="card-body text-center">
                    <div class="icon icon-xl mx-auto mb-4 bg-danger-light">
                          <svg class="svg-icon text-danger">
                            <use xlink:href="../lib/icons/orion-svg-sprite.svg#woman-1"> </use>
                          </svg>
                    </div>
                    <h4 class="mt-3"><span class="text-danger">526</span><span class="fw-normal"> subscribers</span></h4>
                    <p class="mb-4">20% change</p>
                    <button class="btn btn-danger">Manage List</button>
                  </div>
                </div>
              </div>
              <div class="col-md-4 mb-4">
                <div class="card h-100">
                  <div class="card-body text-center">
                    <div class="icon icon-xl mx-auto mb-4 bg-info-light">
                          <svg class="svg-icon text-info">
                            <use xlink:href="../lib/icons/orion-svg-sprite.svg#reading-1"> </use>
                          </svg>
                    </div>
                    <h4 class="mt-3"><span class="text-info">3,258</span><span class="fw-normal"> readers</span></h4>
                    <p class="mb-4">35% change</p>
                    <button class="btn btn-info">See details</button>
                  </div>
                </div>
              </div>
              <div class="col-md-4 mb-4">
                <div class="card h-100">
                  <div class="card-body text-center">
                    <div class="icon icon-xl mx-auto mb-4 bg-primary-light">
                          <svg class="svg-icon text-primary">
                            <use xlink:href="../lib/icons/orion-svg-sprite.svg#retro-camera-1"> </use>
                          </svg>
                    </div>
                    <h4 class="mt-3"><span class="text-primary">1,256</span><span class="fw-normal"> photos</span></h4>
                    <p class="mb-4">18% change</p>
                    <button class="btn btn-primary">Upload new</button>
                  </div>
                </div>
              </div>
            </div>
            <div class="row mb-5">
              <div class="col-md-6">
                <div class="card mb-4">
                  <div class="card-body">
                    <button class="btn float-end btn-primary">Completed</button>
                    <h4 class="h6">Publish New Theme</h4>
                    <p class="text-primary">Web Design</p>
                    <p class="text-muted">One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like ba</p>
                    <div class="mb-3">
                      <div class="d-inline-block me-3">
                        <div class="h3 mb-1">23</div>
                        <div class="text-muted text-sm">Updates</div>
                      </div>
                      <div class="d-inline-block">
                        <div class="h3 mb-1">2</div>
                        <div class="text-muted text-sm">Milestones</div>
                      </div>
                    </div>
                    <div class="mb-4"><img class="avatar avatar-sm avatar-stacked p-1" src="../lib/img/avatar-0.jpg" alt="" data-bs-toggle="tooltip" data-placement="top" title="User 0"><img class="avatar avatar-sm avatar-stacked p-1" src="../lib/img/avatar-1.jpg" alt="" data-bs-toggle="tooltip" data-placement="top" title="User 1">
                    </div>
                    <p class="sr-only">Progress</p>
                    <div class="progress">
                      <div class="progress-bar bg-primary" style="width:70%"></div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="card mb-4">
                  <div class="card-body">
                    <button class="btn float-end btn-info">Completed</button>
                    <h4 class="h6">Internal Linkbuilding</h4>
                    <p class="text-info">Marketing</p>
                    <p class="text-muted">The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of the rest of </p>
                    <div class="mb-3">
                      <div class="d-inline-block me-3">
                        <div class="h3 mb-1">23</div>
                        <div class="text-muted text-sm">Updates</div>
                      </div>
                      <div class="d-inline-block">
                        <div class="h3 mb-1">2</div>
                        <div class="text-muted text-sm">Milestones</div>
                      </div>
                    </div>
                    <div class="mb-4"><img class="avatar avatar-sm avatar-stacked p-1" src="../lib/img/avatar-1.jpg" alt="" data-bs-toggle="tooltip" data-placement="top" title="User 0"><img class="avatar avatar-sm avatar-stacked p-1" src="../lib/img/avatar-2.jpg" alt="" data-bs-toggle="tooltip" data-placement="top" title="User 1"><img class="avatar avatar-sm avatar-stacked p-1" src="../lib/img/avatar-3.jpg" alt="" data-bs-toggle="tooltip" data-placement="top" title="User 2"><img class="avatar avatar-sm avatar-stacked p-1" src="../lib/img/avatar-4.jpg" alt="" data-bs-toggle="tooltip" data-placement="top" title="User 3">
                    </div>
                    <p class="sr-only">Progress</p>
                    <div class="progress">
                      <div class="progress-bar bg-info" style="width:48%"></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row mb-5">
              <div class="col-md-6 mb-4">
                <div class="card h-100">
                  <div class="card-body">
                    <div class="row gx-2 gx-lg-4 gy-5">
                      <div class="col-sm-5">
                        <div class="h2">625</div>
                        <p class="subtitle">New Customers</p>
                        <div class="progress">
                          <div class="progress-bar bg-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:60%"></div>
                        </div>
                      </div>
                      <div class="col-sm-7">
                        <div class="row">
                          <div class="col-6 text-center">
                            <div class="h3">254</div>
                            <p class="text-muted fw-normal">Affiliates</p>
                            <hr>
                            <p class="text-muted mb-0">+125</p>
                          </div>
                          <div class="col-6 text-center">
                            <div class="h3">328</div>
                            <p class="text-muted">SEM</p>
                            <hr>
                            <p class="text-muted mb-0">+144</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-md-6 mb-4">
                <div class="card h-100">
                  <div class="card-body d-flex align-items-center">
                    <div class="row gy-5 flex-fill"> 
                      <div class="col-sm-6">
                        <div class="row">
                          <div class="col-sm-2 text-lg"><i class="fas fa-arrow-down text-danger"></i></div>
                          <div class="col-sm-10">
                            <h2>1,112</h2>
                            <h6 class="text-muted fw-normal p-b-20 p-t-10">Affiliate Sales</h6>
                            <div class="progress">
                              <div class="progress-bar bg-danger" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width:75%"></div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="col-sm-6">
                        <div class="row">
                          <div class="col-sm-2 text-lg"><i class="fas fa-arrow-up text-success"></i></div>
                          <div class="col-sm-10">
                            <h2>258</h2>
                            <h6 class="text-muted fw-normal p-b-20 p-t-10">Ads Sales</h6>
                            <div class="progress">
                              <div class="progress-bar bg-success" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%"></div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row mb-5">
              <div class="col-md-6 col-lg-3 mb-4">
                <div class="card h-100">
                  <div class="card-header py-4">
                    <h6 class="card-heading">Lines of code</h6>
                  </div>
                  <div class="card-body">
                    <div class="display-4 text-center mb-4">828</div>
                    <div class="row">
                      <div class="col-sm-6 text-center h-100">
                        <p class="text-muted text-sm">Tabs</p>
                        <div class="h4 mb-0 text-green">456</div>
                      </div>
                      <div class="col-sm-6 text-center h-100 align-items-end">
                        <p class="text-muted text-sm">Spaces</p>
                        <div class="h4 mb-0 text-indigo">125</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-lg-3 mb-4">
                <div class="card h-100">
                  <div class="card-header py-4">
                    <h6 class="card-heading">Customers                </h6>
                  </div>
                  <div class="card-body pt-3">
                    <div class="icon icon-xl bg-primary-light mx-auto mb-3">
                          <svg class="svg-icon text-primary">
                            <use xlink:href="../lib/icons/orion-svg-sprite.svg#chart-1"> </use>
                          </svg>
                    </div>
                    <div class="row position-relative" style="top: 10px;">
                      <div class="col-6 text-center">
                        <p class="text-muted text-sm">Happy</p>
                        <div class="h4 mb-0 text-pink">456</div>
                      </div>
                      <div class="col-6 text-center">
                        <p class="text-muted text-sm">Ecstatic</p>
                        <div class="h4 mb-0 text-cyan">125</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-lg-6 mb-4">
                <div class="card h-100">
                  <div class="card-header py-4">
                    <h6 class="card-heading">Plan</h6>
                  </div>
                  <div class="card-body pt-3">
                    <div class="h2 mb-3">
                       78.2% </div>
                    <div class="row align-items-center mb-3">
                      <div class="col-sm-3 text-muted text-sm">Sales</div>
                      <div class="col-sm-7">
                        <div class="progress">
                          <div class="progress-bar bg-primary" role="progressbar" aria-valuenow="78" aria-valuemin="0" aria-valuemax="100" style="width:78%"></div>
                        </div>
                      </div>
                      <div class="col-sm-2 text-muted text-sm">78%</div>
                    </div>
                    <div class="row align-items-center mb-3">
                      <div class="col-sm-3 text-muted text-sm">Ads</div>
                      <div class="col-sm-7">
                        <div class="progress">
                          <div class="progress-bar bg-success" role="progressbar" aria-valuenow="48" aria-valuemin="0" aria-valuemax="100" style="width:48%"></div>
                        </div>
                      </div>
                      <div class="col-sm-2 text-muted text-sm">48%</div>
                    </div>
                    <div class="row align-items-center mb-3">
                      <div class="col-sm-3 text-muted text-sm">Campaigns</div>
                      <div class="col-sm-7">
                        <div class="progress">
                          <div class="progress-bar bg-pink" role="progressbar" aria-valuenow="88" aria-valuemin="0" aria-valuemax="100" style="width:88%"></div>
                        </div>
                      </div>
                      <div class="col-sm-2 text-muted text-sm">88%</div>
                    </div>
                    <div class="row align-items-center">
                      <div class="col-sm-3 text-muted text-sm">Subscribers</div>
                      <div class="col-sm-7">
                        <div class="progress">
                          <div class="progress-bar bg-indigo" role="progressbar" aria-valuenow="98" aria-valuemin="0" aria-valuemax="100" style="width:98%"></div>
                        </div>
                      </div>
                      <div class="col-sm-2 text-muted text-sm">98%</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row mb-5">
              <div class="col-md-4 mb-4">
                <div class="card h-100">
                  <div class="card-header">
                    <h6 class="card-heading">Your spendings</h6>
                  </div>
                  <div class="card-body">
                    <h2 class="mb-3">$950</h2>
                    <div class="row">
                      <div class="col-auto">
                        <p class="text-muted text-sm mt-2">Planned for next month</p>
                      </div>
                      <div class="col text-end text-lg">$1253</div>
                    </div>
                    <div class="progress">
                      <div class="progress-bar bg-primary" role="progressbar" aria-valuenow="78" aria-valuemin="0" aria-valuemax="100" style="width:78%">                           </div>
                    </div>
                  </div>
                  <div class="card-footer text-end">
                    <button class="btn btn-primary">Take action                        </button>
                  </div>
                </div>
              </div>
              <div class="col-md-4 mb-4">
                <div class="card h-100">
                  <div class="card-header">
                    <h6 class="card-heading">Transactions</h6>
                  </div>
                  <div class="card-body">
                    <h2 class="mb-3">323</h2>
                    <div class="row">
                      <div class="col-auto">
                        <p class="text-muted text-sm mt-2">Planned for next month</p>
                      </div>
                      <div class="col text-end text-lg">356</div>
                    </div>
                    <div class="progress">
                      <div class="progress-bar bg-warning" role="progressbar" aria-valuenow="83" aria-valuemin="0" aria-valuemax="100" style="width:83%">                           </div>
                    </div>
                  </div>
                  <div class="card-footer text-end">
                    <button class="btn btn-warning">Take action                        </button>
                  </div>
                </div>
              </div>
              <div class="col-md-4 mb-4">
                <div class="card h-100">
                  <div class="card-header">
                    <h6 class="card-heading">VAT</h6>
                  </div>
                  <div class="card-body">
                    <h2 class="mb-3">$158</h2>
                    <div class="row">
                      <div class="col-auto">
                        <p class="text-muted text-sm mt-2">Planned for next month</p>
                      </div>
                      <div class="col text-end text-lg">$300</div>
                    </div>
                    <div class="progress">
                      <div class="progress-bar bg-danger" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width:45%">                           </div>
                    </div>
                  </div>
                  <div class="card-footer text-end">
                    <button class="btn btn-danger">Take action                        </button>
                  </div>
                </div>
              </div>
            </div>
            <div class="row mb-5">
              <div class="col-md-6 col-xl-3 mb-4">
                <div class="card h-100">
                  <div class="card-body d-flex flex-column justify-content-between">
                    <div class="row gx-1">
                      <div class="col">
                            <svg class="svg-icon text-red mb-3">
                              <use xlink:href="../lib/icons/orion-svg-sprite.svg#speed-1"> </use>
                            </svg>
                        <p class="subtitle text-gray-600">Earnings</p>
                      </div>
                      <div class="col text-end">
                        <h4 class="text-red">$10,500</h4>
                      </div>
                    </div>
                    <div class="progress">
                      <div class="progress-bar bg-red" role="progressbar" aria-valuenow="85" aria-valuemin="0" aria-valuemax="100" style="width:85%"></div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-xl-3 mb-4">
                <div class="card h-100">
                  <div class="card-body d-flex flex-column justify-content-between">
                    <div class="row gx-1">
                      <div class="col">
                            <svg class="svg-icon text-blue mb-3">
                              <use xlink:href="../lib/icons/orion-svg-sprite.svg#news-1"> </use>
                            </svg>
                        <p class="subtitle text-gray-600">Readers</p>
                      </div>
                      <div class="col text-end">
                        <h4 class="text-blue">584</h4>
                      </div>
                    </div>
                    <div class="progress">
                      <div class="progress-bar bg-blue" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width:75%"></div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-xl-3 mb-4">
                <div class="card h-100">
                  <div class="card-body d-flex flex-column justify-content-between">
                    <div class="row gx-1">
                      <div class="col">
                            <svg class="svg-icon text-primary mb-3">
                              <use xlink:href="../lib/icons/orion-svg-sprite.svg#bookmark-1"> </use>
                            </svg>
                        <p class="subtitle text-gray-600">Bookmarks</p>
                      </div>
                      <div class="col text-end">
                        <h4 class="text-primary">876</h4>
                      </div>
                    </div>
                    <div class="progress">
                      <div class="progress-bar bg-primary" role="progressbar" aria-valuenow="67" aria-valuemin="0" aria-valuemax="100" style="width:67%"></div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-xl-3 mb-4">
                <div class="card h-100">
                  <div class="card-body d-flex flex-column justify-content-between">
                    <div class="row gx-1">
                      <div class="col">
                            <svg class="svg-icon text-green mb-3">
                              <use xlink:href="../lib/icons/orion-svg-sprite.svg#world-map-1"> </use>
                            </svg>
                        <p class="subtitle text-gray-600">Visitors</p>
                      </div>
                      <div class="col text-end">
                        <h4 class="text-green">3,500</h4>
                      </div>
                    </div>
                    <div class="progress">
                      <div class="progress-bar bg-green" role="progressbar" aria-valuenow="93" aria-valuemin="0" aria-valuemax="100" style="width:93%"></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row mb-5">
              <div class="col-md-6 col-lg-3">
                <div class="card mb-4 bg-red-light">
                  <div class="card-body d-flex align-items-center justify-content-between">
                    <div>
                      <h6>Earnings</h6>
                      <h3 class="mb-0 text-red">$10,500</h3>
                    </div>
                        <svg class="svg-icon text-red ">
                          <use xlink:href="../lib/icons/orion-svg-sprite.svg#speed-1"> </use>
                        </svg>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-lg-3">
                <div class="card mb-4 bg-blue-light">
                  <div class="card-body d-flex align-items-center justify-content-between">
                    <div>
                      <h6>Readers</h6>
                      <h3 class="mb-0 text-blue">584</h3>
                    </div>
                        <svg class="svg-icon text-blue ">
                          <use xlink:href="../lib/icons/orion-svg-sprite.svg#news-1"> </use>
                        </svg>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-lg-3">
                <div class="card mb-4 bg-primary-light">
                  <div class="card-body d-flex align-items-center justify-content-between">
                    <div>
                      <h6>Bookmarks</h6>
                      <h3 class="mb-0 text-primary">876</h3>
                    </div>
                        <svg class="svg-icon text-primary ">
                          <use xlink:href="../lib/icons/orion-svg-sprite.svg#bookmark-1"> </use>
                        </svg>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-lg-3">
                <div class="card mb-4 bg-green-light">
                  <div class="card-body d-flex align-items-center justify-content-between">
                    <div>
                      <h6>Visitors</h6>
                      <h3 class="mb-0 text-green">3,500</h3>
                    </div>
                        <svg class="svg-icon text-green ">
                          <use xlink:href="../lib/icons/orion-svg-sprite.svg#world-map-1"> </use>
                        </svg>
                  </div>
                </div>
              </div>
            </div>
            <div class="row mb-5">
              <div class="col-md-6 col-lg-3">
                <div class="card mb-4">
                  <div class="card-body">
                    <div class="icon icon-lg float-end bg-red-light" style="position: relative; top: -.8rem; right: -.8rem;">
                          <svg class="svg-icon text-red svg-icon-md svg-icon-heavy">
                            <use xlink:href="../lib/icons/orion-svg-sprite.svg#speed-1"> </use>
                          </svg>
                    </div>
                    <h6>Earnings</h6>
                    <h3 class="text-red">$10,500</h3>
                    <p class="text-muted text-sm mb-0">May 23 - June 01 (2018)</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-lg-3">
                <div class="card mb-4">
                  <div class="card-body">
                    <div class="icon icon-lg float-end bg-blue-light" style="position: relative; top: -.8rem; right: -.8rem;">
                          <svg class="svg-icon text-blue svg-icon-md svg-icon-heavy">
                            <use xlink:href="../lib/icons/orion-svg-sprite.svg#news-1"> </use>
                          </svg>
                    </div>
                    <h6>Readers</h6>
                    <h3 class="text-blue">584</h3>
                    <p class="text-muted text-sm mb-0">May 23 - June 01 (2018)</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-lg-3">
                <div class="card mb-4">
                  <div class="card-body">
                    <div class="icon icon-lg float-end bg-primary-light" style="position: relative; top: -.8rem; right: -.8rem;">
                          <svg class="svg-icon text-primary svg-icon-md svg-icon-heavy">
                            <use xlink:href="../lib/icons/orion-svg-sprite.svg#bookmark-1"> </use>
                          </svg>
                    </div>
                    <h6>Bookmarks</h6>
                    <h3 class="text-primary">876</h3>
                    <p class="text-muted text-sm mb-0">May 23 - June 01 (2018)</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-lg-3">
                <div class="card mb-4">
                  <div class="card-body">
                    <div class="icon icon-lg float-end bg-green-light" style="position: relative; top: -.8rem; right: -.8rem;">
                          <svg class="svg-icon text-green svg-icon-md svg-icon-heavy">
                            <use xlink:href="../lib/icons/orion-svg-sprite.svg#world-map-1"> </use>
                          </svg>
                    </div>
                    <h6>Visitors</h6>
                    <h3 class="text-green">3,500</h3>
                    <p class="text-muted text-sm mb-0">May 23 - June 01 (2018)</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="pb-5">
              <div class="card mb-4">
                <div class="card-body">
                  <div class="row g-5">
                    <div class="col-xl-3 col-md-6">
                      <h6 class="subtitle fw-normal text-muted">Earnings</h6>
                      <h5 class="m-b-25">$10,500<span class="ms-3 float-end text-red">+20.3%</span></h5>
                      <div class="progress">
                        <div class="progress-bar bg-red" role="progressbar" aria-valuenow="85" aria-valuemin="0" aria-valuemax="100" style="width:85%"></div>
                      </div>
                    </div>
                    <div class="col-xl-3 col-md-6">
                      <h6 class="subtitle fw-normal text-muted">Readers</h6>
                      <h5 class="m-b-25">584<span class="ms-3 float-end text-blue">+3.3%</span></h5>
                      <div class="progress">
                        <div class="progress-bar bg-blue" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width:75%"></div>
                      </div>
                    </div>
                    <div class="col-xl-3 col-md-6">
                      <h6 class="subtitle fw-normal text-muted">Bookmarks</h6>
                      <h5 class="m-b-25">876<span class="ms-3 float-end text-primary">+10.5%</span></h5>
                      <div class="progress">
                        <div class="progress-bar bg-primary" role="progressbar" aria-valuenow="67" aria-valuemin="0" aria-valuemax="100" style="width:67%"></div>
                      </div>
                    </div>
                    <div class="col-xl-3 col-md-6">
                      <h6 class="subtitle fw-normal text-muted">Visitors</h6>
                      <h5 class="m-b-25">3,500<span class="ms-3 float-end text-green">-5.8%</span></h5>
                      <div class="progress">
                        <div class="progress-bar bg-green" role="progressbar" aria-valuenow="93" aria-valuemin="0" aria-valuemax="100" style="width:93%"></div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>
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