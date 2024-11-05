<%-- 
    Document   : filtrosCustom
    Created on : 24-oct-2024, 13:45:26
    Author     : grecendiz

D:\git\VF-Inbound\web/Importacion/Reportes/filtrosCustom_1.jsp
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
                                    
                                    <br><br>
                                    
                                     <div class="container mt-12">
                                <select class="selectpicker form-control" multiple aria-label="Selecciona" data-live-search="true" id="mSelect2">
                                   
                                                    
                     <option value="0">Referencia AA                                             </option> 
                     <option value="1">Evento                                                      </option> 
                     <option value="2">Responsable                                                 </option> 
                     <option value="3">Final Destination                                           </option> 
                     <option value="4">Brand-Division                                              </option> 
                     <option value="5">Division                                                    </option> 
                     <option value="6">Shipment ID                                                 </option> 
                     <option value="7">Container                                                   </option> 
                     <option value="8">BL/AWB/PRO                                                  </option> 
                     <option value="9">LoadType                                                   </option> 
                     <option value="10">Quantity                                                   </option> 
                     <option value="11">POD                                                        </option> 
                     <option value="12">Est. Departure from POL                                    </option> 
                     <option value="13">ETA REAL Port of Discharge                                 </option> 
                     <option value="14">Est. Eta DC                                                </option> 
                     <option value="15">Inbound notification                                       </option> 
                     <option value="16">POL                                                        </option> 
                     <option value="17">A.A.                                                       </option> 
                     <option value="18">Fecha Mes de Venta                                         </option> 
                     <option value="19">Prioridad Si/No                                            </option> 
                     <option value="20">País Origen                                                </option> 
                     <option value="21">Size Container                                             </option> 
                     <option value="22">Valor USD                                                  </option> 
                     <option value="23">ETA Port Of Discharge                                      </option> 
                     <option value="24">Agente Aduanal                                             </option> 
                     <option value="25">Pedimento A1                                               </option> 
                     <option value="26">Pedimento R1                                               </option> 
                     <option value="27">Motivo rectificación 1                                     </option> 
                     <option value="28">Pedimento R1 (2do)                                         </option> 
                     <option value="29">Motivo rectificación 2                                     </option> 
                     <option value="30">Fecha Recepción Documentos                                 </option> 
                     <option value="31">Recinto                                                    </option> 
                     <option value="32">Naviera / Forwarder                                        </option> 
                     <option value="33">Buque                                                      </option> 
                     <option value="34">Fecha Revalidación/Liberación de BL                        </option> 
                     <option value="35">Fecha Previo Origen                                        </option> 
                     <option value="36">Fecha Previo en destino                                    </option> 
                     <option value="37">Fecha Resultado Previo                                     </option> 
                     <option value="38">Proforma Final                                             </option> 
                     <option value="39">Requiere permiso                                           </option> 
                     <option value="40">Fecha envío Fichas/notas                                   </option> 
                     <option value="41">Fec. Recepción de permisos tramit.                         </option> 
                     <option value="42">Fec. Act Permisos (Inic Vigencia)                          </option> 
                     <option value="43">Fec. Perm. Aut. (Fin de Vigencia)                          </option> 
                     <option value="44">Cuenta con CO para aplicar preferencia Arancelaria         </option> 
                     <option value="45">Aplico Preferencia Arancelaria                             </option> 
                     <option value="46">Requiere UVA                                               </option> 
                     <option value="47">Requiere CA                                                </option> 
                     <option value="48">Fecha Recepción CA                                         </option> 
                     <option value="49">Número de Constancia CA                                    </option> 
                     <option value="50">Monto CA                                                   </option> 
                     <option value="51">Fecha Documentos Completos                                 </option> 
                     <option value="52">Fecha Pago Pedimento                                       </option> 
                     <option value="53">Fecha Solicitud de transporte                              </option> 
                     <option value="54">Fecha Modulacion                                           </option> 
                     <option value="55">Modalidad                                                  </option> 
                     <option value="56">Resultado Modulacion                                       </option> 
                     <option value="57">Fecha Reconocimiento                                       </option> 
                     <option value="58">Fecha Liberacion                                           </option> 
                     <option value="59">Sello Origen                                               </option> 
                     <option value="60">Sello Final                                                </option> 
                     <option value="61">Fecha de retencion por la autoridad                        </option> 
                     <option value="62">Fec. de liberacion por ret. de la aut.                     </option> 
                     <option value="63">Estatus de la operación                                    </option> 
                     <option value="64">Motivo Atraso                                              </option> 
                     <option value="65">Observaciones                                              </option>                                  
                     <option value="66">Llegada a NOVA                                             </option> 
                     <option value="67">Llegada a Globe trade SD                                   </option> 
                     <option value="68">Archivo M                                                  </option> 
                     <option value="69">Fecha de Archivo M                                         </option> 
                     <option value="70">Fecha Solicitud de Manipulacion                            </option> 
                     <option value="71">Fecha de vencimiento de Manipulacion                       </option> 
                     <option value="72">Fecha confirmacion Clave de Pedimento                      </option> 
                     <option value="73">Fecha de Recepcion de Incrementables                       </option> 
                     <option value="74">T&E                                                        </option> 
                     <option value="75">Fecha de Vencimiento del Inbound                           </option> 
                     <option value="76">No. BULTOS                                                 </option> 
                     <option value="77">Peso (KG)                                                  </option> 
                     <option value="78">Transferencia                                              </option> 
                     <option value="79">Fecha Inicio Etiquetado                                    </option> 
                     <option value="80">Fecha Termino Etiquetado                                   </option> 
                     <option value="81">Hora de termino Etiquetado                                 </option> 
                     <option value="82">Proveedor                                                  </option> 
                     <option value="83">Proveedor de Carga                                         </option> 
              
                                 </select>
                              </div>
                                 
                                 
                                    <br><br>
                                     <div class="container mt-4">
  <div class="overflow-auto" style="white-space: nowrap;">
      <br>  
      </div> 
                                           </div> 
                                    <br>
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
      let mselectRep;
    
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

function getSelectedOptionsRep() {
    const select2 = document.getElementById('mSelect2');
    const selectedValues2 = [];
    
    // Recorre todas las opciones y selecciona las que están seleccionadas
    for (let i = 0; i < select2.options.length; i++) {
        if (select2.options[i].selected) {
            selectedValues2.push(select2.options[i].value);
        }
    }
    
    // Unir los valores seleccionados en un string, separados por comas
    const resultString2 = selectedValues2.join(', ');
     mselectRep=selectedValues2;
    console.log('Valores seleccionados:', resultString2);
}
      

     
    function reporte(){
        console.log('2');
        getSelectedOptions();
        getSelectedOptionsRep();
         console.log('res-'+mselect);
         window.open('<%=request.getContextPath()%>/Importacion/Reportes/ReporteCustoms_2.jsp?filterType=0&id=0&f=1&f2='+mselect+'&col='+mselectRep, '_blank');

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
   getSelectedOptionsRep();
   // acepta();
         console.log('res-'+mselect);
    try {
        //Iterar Encabezados tabla.
        let ExcelRepCustom = encodeURI("<%=request.getContextPath()%>/ExcelRepCustomInt?f2=" + mselect+"&val="+mselectRep );
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
