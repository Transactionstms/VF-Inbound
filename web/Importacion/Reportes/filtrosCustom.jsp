<%-- 
    Document   : filtrosCustom
    Created on : 24-oct-2024, 13:45:26
    Author     : grecendiz
--%>

 

<%@page import="com.onest.graficas.StoredProcedureExample"%>
<%@page import="com.onest.oracle.DB"%>
<%@page import="com.onest.oracle.DBConfData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>bandejaPlaneacion</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">
        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
        <link href="../lib/inbound/eventos/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- sweetalert -->
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>
        <!-- Connection Status Red -->
        <link href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
      
    </head>
    <%
        try {
            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            String folio = request.getParameter("folio");

            String max = "  SELECT AGENTE_ADUANAL_ID, AGENTE_ADUANAL_NOMBRE FROM  tra_inb_agente_aduanal order by 2 ";
            
        String option="";
        String optionD=" ";
            if (db.doDB(max)) {
                for (String[] row : db.getResultado()) {
                   
                      optionD += row[0]+",";
                   option+=" <option value='"+row[0]+"'>"+row[1]+"</option>";
                }
            }
 
            String default1 = optionD.substring(0, optionD.length() - 1);


    %>
    <body>

        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <section>
                        <div class="row">
                            
                            <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h2 class="card-heading">Reporte Customs   </h2>
                                            </div>
                                        </div>
                                    </div>
                            
                            <div class="col-lg-12 mb-4 mb-lg-0">
                             
                              <br><br>  
                            <div class="container mt-12">
                                <select class="selectpicker form-control" multiple aria-label="Selecciona" data-live-search="true" id="mSelect">
                                    <option value="<%=default1%>">Todos</option>   
                                                     <%=option%>
                                 </select>
                              </div>
                                    <br><br>
                    <div class="container mt-12"> 
                            <div class="mb-3">
                            <label for="exampleFormControlInput1" class="form-label">Email</label>
                            <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="correo@example.com">
                          </div>
                    </div>                
                                    <br><br><br>
                    <div class="container mt-12">            
                        <button type="button" class="btn btn-primary"   onclick="enviar(1)">Enviar por correo   </button>
                        <button type="button" class="btn btn-secondary" onclick="reporte()">Generar Reporte</button>
                        <button type="button" class="btn btn-success"   onclick="enviar(2)">Generar y Descargar</button>            
                                    
                    </div>                
                            </div>
                        </div>   
                    </section>
                </div>  

            </div>
        </div>   
        <!-- Conexión estatus red -->                    
       <!-- Bootstrap 5 CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Select CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/css/bootstrap-select.min.css">

<!-- jQuery (Bootstrap Select depende de jQuery) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Bootstrap Select JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/js/bootstrap-select.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>


<script>
    let mselect;
    
    function getSelectedOptions() {
    const select = document.getElementById('mSelect');
    const selectedValues = [];
    
    // Recorre todas las opciones y selecciona las que están seleccionadas
    for (let i = 0; i < select.options.length; i++) {
        if (select.options[i].selected) {
            selectedValues.push(select.options[i].value);
        }
    }
    
    // Unir los valores seleccionados en un string, separados por comas
    const resultString = selectedValues.join(', ');
     mselect=selectedValues;
    console.log('Valores seleccionados:', resultString);
}

      

     
    function reporte(){
        console.log('2');
        getSelectedOptions();
         console.log('res-'+mselect);
         window.open('<%=request.getContextPath()%>/Importacion/Reportes/ReporteCustoms.jsp?filterType=0&id=0&f=1&f2='+mselect, '_blank');

       // window.location.href = "<%=request.getContextPath()%>/Importacion/Reportes/ReporteCustoms.jsp?filterType=0&id=0";

        
    }
   
   
   async function enviarcorreo(nombre) {
    try {
        console.log(nombre);
        const response = await fetch('<%=request.getContextPath()%>/enviarMailRC?nom='+nombre);
        const data = await response.text();
        console.log(data);
         Swal.fire({
                    title: 'Proceso Completado',
                    text: 'El proceso ha terminado con éxito!',
                    icon: 'success',
                    confirmButtonText: 'Aceptar'
                });
    } catch (error) {
        console.error('Error:', error);
        Swal.fire({
                    title: 'Error',
                    text: 'El proceso no pudo terminar',
                     
                    confirmButtonText: 'Aceptar'
                });
    }
}
    
  async function enviar(id) {
Swal.fire({
                title: 'Por favor espera',
                text: 'El proceso está en ejecución...',
                icon: 'info',
                allowOutsideClick: false,  // Evitar que el usuario cierre el alert haciendo click afuera
                showConfirmButton: false,  // Ocultar el botón de confirmación
                willOpen: () => {
                    Swal.showLoading();   // Mostrar spinner de carga
                }
            });
   getSelectedOptions();
         console.log('res-'+mselect);
    try {
        //Iterar Encabezados tabla.
        let ExcelRepCustom = encodeURI("<%=request.getContextPath()%>/ExcelRepCustom?f2="+mselect );
        const response = await fetch(ExcelRepCustom);

        console.log(response);
        
  
        //Mostrar data/encabezados tabla
       // const dataEncabezados = await response.text();
       // document.getElementById('table-scroll').innerHTML = dataEncabezados;

         if(id==1){
              let email = document.getElementById('exampleFormControlInput1').value;
              enviarcorreo(email);
             console.log('1');
         }else if(id==2){
             console.log('2');
             descarga()
         }
        
  
    } catch (error) {
        console.error(error);
    }
}




   
  $(document).ready(function() {
    $('.selectpicker').selectpicker();
  });
</script>
   <script>
           function descarga(){
                // Realizar una solicitud al servlet para descargar el archivo
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "<%=request.getContextPath()%>/DownloadExcelEventsCustomsNom", true);
                xhr.responseType = "blob";

                xhr.onload = function() {
                    // Crear un enlace y hacer clic en él para iniciar la descarga
                    var blob = new Blob([xhr.response], { type: "application/octet-stream" });
                    var link = document.createElement("a");
                    link.href = window.URL.createObjectURL(blob);
                    link.download = "ReporteEventosCustoms2.xls";
                    document.body.appendChild(link);
                    link.click();
                    document.body.removeChild(link);
                };

                xhr.send();
                 Swal.fire({
                    title: 'Proceso Completado',
                    text: 'El proceso ha terminado con éxito!',
                    icon: 'success',
                    confirmButtonText: 'Aceptar'
                });
            }
        </script>

    </body>
    <%
        } catch (NullPointerException e) {
            // out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            //  out.println("<script>window.close();</script>");
        } catch (Exception e) {
            out.println("Excepcion revise por favor! " + e);
            e.printStackTrace();
        }
    %>
</html>
