<%-- 
    Document   : agregarShipmet2
    Created on : 27-jul-2023, 14:46:37
    Author     : grecendiz
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.onest.train.consultas.ConsultasQuery"%>
<%@page import="com.onest.security.menu.*" %>
<%@page import="com.onest.net.*" %>
<%@page import="com.onest.oracle.*" %>
<%@page import="com.onest.misc.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Eventos </title>
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
        <!-- Table css -->
        <link href="../lib/inbound/eventos/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- Connection Status Red -->
        <link href="../lib/inbound/conexion/connectionStatus.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                ConsultasQuery fac = new ConsultasQuery();
                String agenteAduanal = "";

                String evento = request.getParameter("id");
                String ship = request.getParameter("ship");
                String con = request.getParameter("con");
        %>
 
        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <section>

                        <div class="col-lg-12 mb-5">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-heading">Agregar Shipment</h4>
                                </div>
                                <div class="card-body">
                                    <!--  <p>Lorem ipsum dolor sit amet consectetur.</p>-->

                                    <%
                                        String responsableOptions = "";
                                        String responsableOptionsSQL = "select DISTINCT USER_NID,RESPONSABLE from tra_destino_responsable where estatus=1";
                                        if (db.doDB(responsableOptionsSQL)) {
                                            for (String[] row : db.getResultado()) {
                                                responsableOptions += "<option value='" + row[0] + "' >" + row[1] + "</option>";
                                            }
                                        }

                                        String pol = "";
                                        String polSQL = "select ID_POL, NOMBRE_POL from tra_inb_pol";
                                        if (db.doDB(polSQL)) {
                                            for (String[] row : db.getResultado()) {
                                                pol += "<option value='" + row[0] + "' >" + row[1] + "</option>";
                                            }
                                        }

                                        String pod = "";
                                        String podSQL = "select ID_POD, NOMBRE_POD from tra_inb_pod";
                                        if (db.doDB(podSQL)) {
                                            for (String[] row : db.getResultado()) {
                                                pod += "<option value='" + row[0] + "' >" + row[1] + "</option>";
                                            }
                                        }

                                        String bd = "";
                                        String bdSQL = "select ID_BD, NOMBRE_BD from tra_inb_brand_division";
                                        if (db.doDB(bdSQL)) {
                                            for (String[] row : db.getResultado()) {
                                                bd += "<option value='" + row[0] + "' >" + row[1] + "</option>";
                                            }
                                        }

                                        String sbu = "";
                                        String sbuSQL = "select ID_DIVISION, DIVISION_NOMBRE from tra_inb_division";
                                        if (db.doDB(sbuSQL)) {
                                            for (String[] row : db.getResultado()) {
                                                sbu += "<option value='" + row[0] + "' >" + row[1] + "</option>";
                                            }
                                        }
                                        
                                        
                                        
                                         String custodia1 = "";
            String sbuSQL21 = "select CLAVE, DESCRIPCION from  tra_inb_reg_aduanero";
            if (db.doDB(sbuSQL21)) {
                for (String[] row : db.getResultado()) {
                    custodia1 += "<option value='" + row[0] + "' >" + row[0] + "- " + row[1] + "</option>";
                }
            }
              String custodia2 = "";
            String sbuSQL22 = "select CLAVE, DESCRIPCIÓN from  TRA_INB_TIPO_MATERIA";
            if (db.doDB(sbuSQL22)) {
                for (String[] row : db.getResultado()) {
                    custodia2 += "<option value='" + row[0] + "' >" + row[0] + "- " + row[1] + "</option>";
                }
            }
              String custodia3 = "";
            String sbuSQL23 = "select CLAVE, DESCRIPCION from  TRA_INB_DOCTO_ADUANEROS";
            if (db.doDB(sbuSQL23)) {
                for (String[] row : db.getResultado()) {
                    custodia3 += "<option value='" + row[0] + "' >" + row[0] + "- " + row[1] + "</option>";
                }
            }
            

                                        if (db.doDB(fac.consultarEventoFormulario(evento, ship, con))) {
                                            for (String[] row : db.getResultado()) {

                                    %>

                                    <form>

                                        <div class="row">
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong> Número de evento  </strong></label>
                                                    <input class="form-control" type="text" placeholder="..." value="<%=row[0]%>" readonly>
                                                </div>
                                            </div>  
                                              <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Container</strong></label>
                                                    <input class="form-control" type="text" placeholder="..." value="<%=row[6]%>" readonly>
                                                </div>
                                            </div>  
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>BL/ AWB/ PRO</strong></label>
                                                    <input class="form-control" type="text" placeholder="..." value="<%=row[7]%>" name="bl" id="bl" >
                                                </div>
                                            </div>     
                                                
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Responsable</strong></label> 
                                                    <select class="form-control chosen" data-placeholder="..." name="responsable" id="responsable">                     
                                                        <option value="<%=row[26]%>"><%=row[1]%></option>
                                                       <!-- <%=responsableOptions%>-->
                                                    </select>
                                                </div>
                                            </div>  
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Final Destination (Shipment)</strong></label>
                                                    <input class="form-control" type="text" placeholder="..." value="<%=row[2]%>" name="finaldes" id="finaldes">
                                                </div>
                                            </div>   
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Brand-Division</strong></label>
                                                    <select class="form-control chosen" data-placeholder="..." name="Brand" id="Brand">                     
                                                        <option value="<%=row[3]%>"><%=row[21]%></option> 
                                                        <%=bd%>
                                                    </select>
                                                </div>
                                            </div> 
                                                    
                                                    
                                                    
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Division</strong></label>
                                                    <select class="form-control chosen" data-placeholder="..." name="sbu_name" id="sbu_name">                     
                                                        <option value="<%=row[27]%>"><%=row[4]%></option>
                                                        <%=sbu%>
                                                    </select>
                                                </div>
                                            </div>  
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Shipment ID</strong></label>
                                                    <input class="form-control" type="text" placeholder="..." value="<%=row[5]%>" name="Shipment" id="Shipment">
                                                </div>
                                            </div>   
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Load Type</strong></label>
                                                    <input class="form-control" type="text" placeholder="..." value="<%=row[22]%>" name="Load1" id="Load1">
                                                </div>
                                            </div>  

 <!--*******************************************************-->


                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Quantity</strong></label>
                                                    <input class="form-control" type="number" placeholder="..." value="1" name="quantity" id="quantity">
                                                </div>
                                            </div>  
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>POD</strong></label> 
                                                    <select class="form-control chosen" data-placeholder="..." name="pod" id="pod">                     
                                                        <option value="<%=row[10]%>"><%=row[19]%></option>
                                                        <%=pod%>
                                                    </select>
                                                </div>
                                            </div>  
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Est. Departure from POL</strong></label>
                                                    <input class="form-control datepicker" type="text"    placeholder="MM/DD/YYYY" value="<%=row[11]%>" name="est_departure_pol" id="est_departure_pol">
                                                </div>
                                            </div>  





                                            <!--*******************************************************-->

                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>ETA REAL PORT</strong></label>
                                                    <input class="form-control datepicker" type="text" placeholder="..." value="<%=row[12]%>" name="eta_port_discharge" id="eta_port_discharge">
                                                </div>
                                            </div>  
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>LT2</strong></label>
                                                    <input class="form-control" type="text" placeholder="..." value="<%=row[13]%>" name="max_flete" id="max_flete">
                                                </div>
                                            </div>  
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>ETA DC</strong></label>
                                                    <input class="form-control datepicker" type="text" placeholder="..." value="<%=row[23]%>" name="eta_plus2" id="eta_plus2">
                                                </div>
                                            </div>  


 <!--*******************************************************-->

                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>INDC +2 Days Put Away</strong></label>
                                                    <input class="form-control datepicker" type="text" placeholder="..." value="<%=row[24]%>" name="eta_plus" id="eta_plus">
                                                </div>
                                            </div>  
                                            
                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>POL</strong></label> 
                                                    <select class="form-control chosen" data-placeholder="..." name="pol" id="pol">                     
                                                        <option value="<%=row[28]%>"><%=row[20]%></option>
                                                        <%=pol%>
                                                    </select>

                                                </div>
                                            </div>  



                                                    
                                                    
                                     <!--********************************-->               
                                                    
                                         <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Observaciones</strong></label>
                                                    <input class="form-control" type="text" placeholder="..."  name="observaciones" id="observaciones" onkeyup="this.value = this.value.toUpperCase()" autocomplete="off">
                                                </div>
                                            </div> 
                                                
<!--*******************************************************-->

                                            <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>ACTUAL CRD</strong></label>
                                                    <input class="form-control datepicker" type="text" placeholder="..."   name="actual_crd" id="actual_crd" onchange="diasActualCrd(this.value);">
                                                </div>
                                            </div> 
                                                
                                                
                                                
                                                
                                                    <div class="col-md-4 mb-4">
                                                <label class="form-label" for="reg1">Regimen aduanero</label>
                                                 <select class="form-select" id="reg1" >
                                                            <option value="0">Selecciona</option>
                                                            <%=custodia1%>
                                                        </select>
                                            </div>
                                                        
                                        <div class="col-md-4 mb-4">
                                                <label class="form-label" for="reg2">Documentos Aduaneros</label>
                                                <select class="form-select" id="reg2" >
                                                             <option value="0">Selecciona</option>
                                                            <%=custodia3%>
                                                        </select>
                                            </div>
                                                        
                                         <div class="col-md-4 mb-4">
                                                <label class="form-label" for="reg3">Tipo Materia </label>
                                                 <select class="form-select" id="reg3" >
                                                            <option value="0">Selecciona</option>
                                                            <%=custodia2%>
                                                        </select>
                                            </div>   
                                                        
                                                        
                                                        
                                           <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Material </strong></label>
                                                    <input class="form-control  " type="text" placeholder="..."  name="mat2" id="mat2"  >
                                                </div>
                                            </div> 
                                                        
                                         <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Carrier </strong></label>
                                                    <input class="form-control  " type="text" placeholder="..."  name="carr2" id="carr2"  >
                                                </div>
                                            </div> 
                                                        
                                         <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Tipo Contenedor </strong></label>
                                                    <input class="form-control  " type="text" placeholder="..."  name="tcon2" id="tcon2"  >
                                                </div>
                                            </div>                 
                                                        
                                           <div class="col-md-4 "> 
                                                <div class="mb-3">
                                                    <label class="form-label text-uppercase"><strong>Peso </strong></label>
                                                    <input class="form-control " type="text" placeholder="..."  name="peso2" id="peso2"  >
                                                </div>
                                            </div>                    
                                                    
                                                    
                                                    
                                                    
                                      

                                        </div>



                                        <div class="mb-3">       
                                            <button class="btn btn-primary" type="button" onclick="getData()">Agregar</button>
                                        </div>
                                    </form>
                                    <%
                                            }
                                        }
                                    %>
                                </div>
                            </div>
                        </div>

                    </section>
                </div>  

            </div>
        </div>    


        <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.1.0/chosen.min.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.1.0/chosen.jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.css">
        <script src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.js"></script>

        <script type="text/javascript">
           
async function getData() { 
        const bl                = document.getElementById('bl').value;
        const responsable       = document.getElementById('responsable').value;
        const finalDestination  = document.getElementById('finaldes').value;
        const brandDivision     = document.getElementById('Brand').value;
        const division          = document.getElementById('sbu_name').value;
        const shipmentId        = document.getElementById('Shipment').value;
        const loadType          = document.getElementById('Load1').value;
        const quantity          = document.getElementById('quantity').value;
        const pod               = document.getElementById('pod').value;
        const estDeparturePol   = document.getElementById('est_departure_pol').value;
        const etaRealPort       = document.getElementById('eta_port_discharge').value;
        const max_flete         = document.getElementById('max_flete').value;
        const eta_plus2         = document.getElementById('eta_plus2').value;
        const eta_plus          = document.getElementById('eta_plus').value;
        const pol               = document.getElementById('pol').value;
       // const observaciones     ='';// document.getElementById('observaciones').value;
           const observaciones = document.getElementById('observaciones').value;
    const actual_crd = document.getElementById('actual_crd').value;
    
     let reg1   = document.getElementById('reg1').value;
     let reg2   = document.getElementById('reg2').value;
     let reg3   = document.getElementById('reg3').value;

     let mat2   = document.getElementById('mat2').value;
     let carr2   = document.getElementById('carr2').value;
     let tcon2   = document.getElementById('tcon2').value;
     
     let peso2   = document.getElementById('peso2').value; 
        swal("Espere...!");

        try {
          const data = await fetchData('<%=request.getContextPath()%>/AgregarShipmet?responsable='+responsable+'&finaldes='+finalDestination+'&Brand='+brandDivision+'&sbu_name='+division+'&Shipment='+shipmentId+'&Load1='+loadType+'&quantity='+quantity+'&pod='+pod+'&est_departure_pol='+estDeparturePol+'&eta_port_discharge='+etaRealPort+'&max_flete='+max_flete+'&eta_plus2='+eta_plus2+'&eta_plus='+eta_plus+'&pol='+pol+'&observaciones='+observaciones+'&bl='+bl+'&evento=<%=evento%>&ship=<%=ship%>&con=<%=con%>'+ '&observaciones=' + observaciones + '&bl=' + bl + '&actual_crd=' + actual_crd + '&reg1='+reg1+'&reg2='+reg2+'&reg3='+reg3+'&mat2='+mat2+'&carr2='+carr2+'&tcon2='+tcon2+'&peso2='+peso2 );
          console.log(data);
          swal("Modificado");
        } catch (error) {
          swal("Error"); 
          console.error(error);
        }
      }

async function fetchData(url) {
  const response = await fetch(url);
  const data = await response.text();
  return data;
}

            $('.chosen').chosen();

            $(document).ready(function () {
                $('.datepicker').flatpickr({
                    dateFormat: 'm/d/Y',
                    onClose: function (selectedDates, dateStr, instance) {
                        instance.setDate(dateStr, true, 'm/d/Y');
                    }
                });
            });

        </script>
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"  >





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