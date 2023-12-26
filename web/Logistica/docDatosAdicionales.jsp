<%@page import="com.usuario.Usuario"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.onest.train.consultas.ConsultasQuery"%>
<%@page import="com.onest.security.menu.*" %>
<%@page import="com.onest.net.*" %>
<%@page import="com.onest.oracle.*" %>
<%@page import="com.onest.misc.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Date date = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    String fecha = formato.format(date);
    String[] par = fecha.split("/");
    String part3 = par[2];

    //Extracción de Hora   
    Date hr = new Date();
    DateFormat hours = new SimpleDateFormat("HH:mm:ss");
    String hora = hours.format(hr);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Datos Adicionales</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">
        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">

        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css'>

    </head>
    <%
        try {
            HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            Usuario root = (Usuario) ownsession.getAttribute("login.root");
            int usr = root.getId();
            String transportista = "";
            String sbuSQL = "select LTRANSPORTE_ID, LTRANSPORTE_NOMBRE from tra_inb_linea_transporte";
            if (db.doDB(sbuSQL)) {
                for (String[] row : db.getResultado()) {
                    transportista += "<option value='" + row[0] + "' >" + row[1] + "</option>";
                }
            }

            String custodia = "";
            String sbuSQL2 = "select ID_TC, TC_DESCRIPCION from tra_inb_tipo_custodia";
            if (db.doDB(sbuSQL2)) {
                for (String[] row : db.getResultado()) {
                    custodia += "<option value='" + row[0] + "' >" + row[1] + "</option>";
                }
            }
            
              String origen = "";
            String sbuSQL23 = "select ORIGEN_ID,PUERTO ||' - '|| PATIO ||' - '|| DIRECCION from TRA_INB_ORIGEN";
            if (db.doDB(sbuSQL23)) {
                for (String[] row : db.getResultado()) {
                    origen += "<option value='" + row[0] + "' >" + row[1] + "</option>";
                }
            }

            
                String mafacturar = "";
            String sbuSQL21 = "select ID_MARCA, MARCA_NOMBRE from TRA_INB_MARCA_FACTURA";
            if (db.doDB(sbuSQL21)) {
                for (String[] row : db.getResultado()) {
                    mafacturar += "<option value='" + row[1] + "' >" + row[1] + "</option>";
                }
            }
            
            String opciones = request.getParameter("op");
            String sql = " "
                    + " SELECT DISTINCT"
                    + "   tie.id_evento,"
                    + "   gtn.shipment_id,"
                    + "   gtn.container1,"
                    + "   gtn.LOAD_TYPE_FINAL "
                    + " FROM "
                    + "   tra_inb_evento tie"
                    + "   INNER JOIN tra_inc_gtn_test gtn ON gtn.plantilla_id = tie.plantilla_id"
                    + " where    EMBARQUE_AGRUPADOR='" + opciones + "'"
                    + " ORDER BY"
                    + "   tie.id_evento";





    %>

    <body>



        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid ">
                    <section>
                        <div class="row">
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card h-100">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <h2 class="card-heading">Datos Adicionales  </h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body col-md-12">
                                        <form class="row " id="" name="" >
                                            <div class="container">
                                                <div class="row">

                                                    <div class="col-md-4 mb-4">
                                                        <label class="form-label" >Transportista</label>
                                                        <select class="form-select" id="tranporte" >
                                                            <option selected value="" >Elija una opcion</option>
                                                            <%=transportista%>
                                                        </select>
                                                    </div>

                                                    <div class="col-md-4 mb-4">
                                                        <label class="form-label" >Fecha de enrampe</label>
                                                        <div class="input-group date" id="datepicker">
                                                            <input type="text" name="f_enrampe" id="f_enrampe" class="datepicker form-control"  autocomplete="off" size="10" required="required" >                                                        </div>
                                                    </div>

                                                    <div class="col-md-4 mb-4">
                                                        <label class="form-label" >Fecha inicio de entrega</label>
                                                        <div class="input-group date" id="datepicker">
                                                            <input type="text" name="f_inicio" id="f_inicio" class="datepicker form-control"  autocomplete="off" size="10" required="required" >
                                                        </div>
                                                    </div>

                                                 <!--   <div class="col-md-3 mb-4">
                                                        <label class="form-label" >Custodia</label>
                                                        <select class="form-select" id="custodia" >
                                                            <option selected value="0">Elija una opcion</option>
                                                            <%=custodia%>
                                                        </select>
                                                    </div>-->

                                                        
                                                    
                                      
                                          
                                            <div class="col-md-3 mb-4">
                                                <label class="form-label" for="camiones">Camiones</label>
                                                <input type="text" class="form-control" id="camiones">
                                            </div>
                                            <div class="col-md-3 mb-4">
                                                <label class="form-label" for="tipoUnidad">Tipo de unidad</label>
                                                <select class="form-select" id="tipoUnidad" aria-label="Default select example">
                                                    <option value="0">Elija una opcion</option>
                                                    <option value="1">	CAM 1.5 TONS</option>
                                                    <option value="2">	CAM 3.5 TONS</option>
                                                    <option value="3">	CAM 5.0 TONS</option>
                                                    <option value="4">	RABON</option>
                                                    <option value="5">	TORTHON</option>
                                                    <option value="6">	MUDANZA</option>
                                                    <option value="7">	TRAILER</option>
                                                    <option value="8">	CAM 0.5 TONS</option> 
                                                </select>
                                            </div>

                                            <div class="col-md-6 mb-4">
                                                <label class="form-label" for="chofer">Chofer</label>
                                                <input type="text" class="form-control" id="chofer">
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <label class="form-label" for="dispositivos">Dispositivos</label>
                                                <input type="number" class="form-control" id="dispositivos">
                                            </div> 

                                         <!--   <div class="col-md-6 mb-4">
                                                <label class="form-label" for="fechaRevision">Fecha Revision</label>
                                                <div class="input-group date" id="datepicker">
                                                    <div class="input-group date" id="datepicker">
                                                        <input type="text" name="date3" id="fechaRevision" class="datepicker infecha form-control" autocomplete="off" size="10" required="required">
                                                    </div>
                                                </div>
                                            </div>-->
                                            <div class="col-md-6 mb-4">
                                                <label class="form-label" for="selloCaja">Sello de caja</label>
                                                <input type="text" class="form-control" id="selloCaja" autocomplete="false">
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <label class="form-label" for="relacionEntrega">Relacion de entrega</label>
                                                <input type="text" class="form-control" id="relacionEntrega" autocomplete="false">
                                            </div>


                                          <!--  <div class="col-md-6 mb-4">
                                                <label class="form-label" for="fechaFinEntrega">Fecha fin de carga</label>
                                                <div class="input-group date" id="datepicker">
                                                    <input type="text" name="date3" id="fechaFinEntrega" class="datepicker infecha form-control" autocomplete="off" size="10" required="required">
                                                </div>   
                                            </div>-->
                                            <div class="col-md-6 mb-4">
                                                <label class="form-label" for="packingList">Packing list</label>
                                                <div class="input-group date" id="datepicker">
                                                    <input type="text" name="date3" id="packingList" class="form-control" autocomplete="off" size="10" required="required">
                                                </div>   
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <label class="form-label" for="autor">Marca a facturar</label>
                                                    <select class="form-select" id="autor" >
                                                            <option selected value="0">Elija una opcion</option>
                                                            <%=mafacturar%>
                                                        </select>
                                            </div>

                                            <div class="col-md-6 mb-4">
                                                <label class="form-label">Observaciones</label>
                                                <textarea type="text" class="form-control" id="observaciones" autocomplete="false"></textarea>
                                            </div>
                                             
                                              <div class="col-md-3 mb-4">
                                                        <label class="form-label" >Selecciona origen</label>
                                                        <select class="form-select" id="origen" >
                                                            <option selected value="0">Elija una opcion</option>
                                                            <%=origen%>
                                                        </select>
                                                    </div>            
          
                                                        
                                                        
                                                  
                                                        
                                                        
                                                </div>
                                            </div>
                                            



                                                        
                                                        
                                                        
                                                             



<div class="row">

                                                <div class="col-md-6">


                                                    <table class="table table-striped" >
                                                        <thead>
                                                            <tr>
                                                                <th scope="col">Evento</th>
                                                                <th scope="col">Shipment ID</th>
                                                                <th scope="col">Container</th> 
                                                            </tr>
                                                        </thead>
                                                        <tbody></tbody>
                                                        <% String opc = " ", ship = "(", cont = "(";
                                                            if (db.doDB(sql)) {
                                                                for (String[] row : db.getResultado()) {
                                                                    ship += "'" + row[1] + "',";
                                                                    cont += "'" + row[2] + "',";

                                                        %>

                                                        <tr>
                                                            <th scope="row"><%=row[0]%></th>
                                                            <td><%=row[1]%></td>
                                                            <td><%=row[2]%></td> 
                                                        </tr> 

                                                        <% }
                                                            }
                                                            ship = ship.substring(0, ship.length() - 1);
                                                            cont = cont.substring(0, cont.length() - 1);
                                                            ship += ")";
                                                            cont += ")";
                                                            opc = "shipment_id in " + ship + " and container1 in" + cont;
                                                        %>
                                                        </tbody>
                                                    </table>                              


                                                </div>
 
                                                <div class="row">                                                    
                                                    <div class="col-md-12" id="iframeid" style="display: none;">
                                                        <iframe class="responsive-iframe" width="100%" height="200%" id="pdfiframe" >
                                                        </iframe>                        
                                                    </div>
                                                </div>   
                                            </div>



                                            <!--button-->
                                            <div class="text-center">
                                                <button class="btn btn-primary mb-5 col-3" type="button" id="uploadBtnid" name="uploadBtnid" role="button"
                                                        onclick="insertaEmbarque()">Grabar embarque</button>
                                            </div>

                                        </form>
                                    </div>
 
                                </div>


                                <br class="mt-5 mb-5" >  
                                <br class="mt-5 mb-5" >
                                <br class="mt-5 mb-5" >
                                <br class="mt-5 mb-5" >  
                                <br class="mt-5 mb-5" >
                                <br class="mt-5 mb-5" >
                            </div>           
                        </div>
                
                 </section>
               </div>  
            </div>  
                                                        <div style="margin-top: 150px;margin-bottom: 150px"></div> 
                               
        </div>
     

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js'></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.css">
    <script src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.js"></script>


    <script>

                                                            function insertaEmbarque1(id, nom, banCus, idc, nomc) {


                                                                let tranp = document.getElementById('iframeid');
                                                                let uploa = document.getElementById('uploadBtnid');
                                                                document.getElementById('pdfiframe').src = "<%=request.getContextPath()%>/Logistica/pdflogistica.jsp?a=<%=opciones%>&email=t&LTRANSPORTE_ID=" + id + "&nameLTransporte=" + nom + "&bc=" + banCus + "&idc=" + idc + "&nomc=" + nomc;
                                                                tranp.style.display = 'block';
                                                                uploa.style.display = 'none';
                                                            }

                                                            async function insertaEmbarque() {



                                                                let tranp = document.getElementById('tranporte').value;
                                                                let fecha = document.getElementById('f_enrampe').value;
                                                                let f_ini = document.getElementById('f_inicio').value;
                                                                
                                                        let camionesValue        = document.getElementById('camiones').value;
                                                        let tipoUnidadValue      = document.getElementById('tipoUnidad').value;
                                                        let choferValue          = document.getElementById('chofer').value;
                                                        let dispositivosValue    = document.getElementById('dispositivos').value;
                                                        let fechaRevisionValue   ='01/01/2023';// document.getElementById('fechaRevision').value;
                                                        let selloCajaValue       = document.getElementById('selloCaja').value;
                                                        let relacionEntregaValue = document.getElementById('relacionEntrega').value;
                                                        let fechaFinEntregaValue ='01/01/2023';// document.getElementById('fechaFinEntrega').value;
                                                        let packingListValue     = document.getElementById('packingList').value;
                                                        let autorValue           = document.getElementById('autor').value;
                                                        let observacionesValue   = document.getElementById('observaciones').value;

                                                        let origen   = document.getElementById('origen').value;
                                                        let reg1   ='0';// document.getElementById('reg1').value;
                                                        let reg2   ='0';// document.getElementById('reg2').value;
                                                        let reg3   ='0';// document.getElementById('reg3').value;



                                                                if (tranp === '' || tranp === null) {
                                                                    swal({title: "Selecciona el transporte", allowEscapeKey: false});
                                                                    return;
                                                                }
                                                                if (fecha === '' || fecha === null) {
                                                                    swal({title: "Selecciona la fecha de enrampe", allowEscapeKey: false});
                                                                    return;
                                                                }
                                                                if (f_ini === '' || f_ini === null) {
                                                                    swal({title: "Selecciona la fecha entrega", allowEscapeKey: false});
                                                                    return;
                                                                }




                                                                swal({title: "Guardando,Espere...", allowEscapeKey: false});
                                                                let custo ='1';// document.getElementById('custodia').value;



                                                               var selectElement1  ='N/A';// document.getElementById("custodia");
                                                               var selectedOption1 ='N/A';// selectElement1.options[selectElement1.selectedIndex];
                                                               var selectedValue1  ='1';// selectedOption1.value;
                                                               var selectedText1   ='N/A';//   selectedOption1.textContent;
   var bandera = '0';
   if (selectedValue1 == '0') {
       bandera = '0';
   } else {
       bandera = '1';
   }

                                                                try {
                                                                    const response = await fetch('<%=request.getContextPath()%>/CrearEmbarque?tran=' + tranp + '&cus=' + custo + '&f1=' + fecha + '&f2=' + f_ini + '&fol=<%=opciones%>&camionesValue=' + camionesValue +'&tipoUnidadValue=' + tipoUnidadValue +'&choferValue=' + choferValue + '&dispositivosValue=' + dispositivosValue +'&fechaRevisionValue=' + fechaRevisionValue +'&selloCajaValue=' + selloCajaValue +'&relacionEntregaValue=' + relacionEntregaValue +'&fechaFinEntregaValue=' + fechaFinEntregaValue +'&packingListValue=' + packingListValue +'&autorValue=' + autorValue +'&observacionesValue=' + observacionesValue+'&reg1='+reg1+'&reg2='+reg2+'&reg3='+reg3+'&origen='+origen);
                                                                    if (!response.ok) {
                                                                        throw new Error('Error en la solicitud');
                                                                    }

                                                                    var selectElement = document.getElementById("tranporte");
                                                                    var selectedOption = selectElement.options[selectElement.selectedIndex];
                                                                    var selectedValue = selectedOption.value;
                                                                    var selectedText = selectedOption.textContent;

                                                                    insertaEmbarque1(tranp, selectedText, bandera, selectedValue1, selectedText1);
                                                                    swal({title: " correcto", allowEscapeKey: false});//Correo enviado
                                                                    const data = await response.text();
                                                                    console.log(data);
                                                                } catch (error) {
                                                                    console.error(error);
                                                                }
                                                            }


                                                            $(document).ready(function () {
                                                                $('.datepicker').flatpickr({
                                                                    dateFormat: 'm/d/Y H:i', // Agrega el formato de hora 'H:i'
                                                                    enableTime: true, // Habilita la selección de tiempo
                                                                    time_24hr: true, // Utiliza el formato de 24 horas
                                                                    onClose: function (selectedDates, dateStr, instance) {
                                                                        instance.setDate(dateStr, true, 'm/d/Y H:i');
                                                                    }
                                                                });
                                                            });
    </script>


    <%
        } catch (NullPointerException e) {
            out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            out.println("<script>window.close();</script>");
        } catch (Exception e) {
            out.println("Excepcion revise por favor! " + e);
            e.printStackTrace();
        }
    %>
</body>
</html>
