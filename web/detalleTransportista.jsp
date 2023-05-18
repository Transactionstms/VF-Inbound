<%-- 
    Document   : detalleTransportista
    Created on : 17-may-2023, 16:25:24
    Author     : grecendiz
--%>


<!DOCTYPE html> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>




<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"   crossorigin="anonymous">
        <link rel="stylesheet" href="//cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css"   crossorigin="anonymous">
 
    </head>
    <body onload="ConsultarRepartoTransportista()">




        <%
            
            String transportista = request.getParameter("transporte");
            


        %>

        <!-- -->
        <div class="container">
            <div class="row">
                <div class="col">
                    <p class="h3">Planeación</p>



                    <div class="table-responsive" style="overflow: auto">  
                        <div class="tableFixHead">

                            <div id="dataTable"></div>
                        </div>
                    </div> 


                </div>

            </div>
        </div>





        <script>
            function muestraHP(agrupador) {

                window.open("<%=request.getContextPath()%>/maniobras/pdfcartaporte.jsp?a=" + agrupador, 'Hoja de Planeación', 'height=740,width=600')

            }


            function enviaForm(foliop, agrupadorA) {

                window.location.href = '<%=request.getContextPath()%>/maniobras/datosAdicionalesPlaneadoManiobra.jsp?agrupadorA=' + agrupadorA + '&cveCuenta=' + foliop;

            }


        </script>



        <div id="dialogpedidos" title="Pedidos "><br/>
            <center>
                <div id="documentos2"></div>
            </center>
        </div>     

        <form name="Estados" id="Estados" action="datosAdicionalesPlaneadoManiobra.jsp" method="post">

            <input type="hidden" value="" name="foliop" id="foliop"/>
            <input type="hidden" value="" id="tipoEmbarque" name="tipoEmbarque"/>
            <input type="hidden" value="" name="tip" id="tip"/>
            <input type="hidden" value="" name="shipto" id="shipto"/>
            <input type="hidden" value="" name="docto" id="docto" />
            <input type="hidden" value="" name="fec1" id="fec1"/>
            <input type="hidden" value="" name="fec2" id="fec2"/>
            <input type="hidden" value="" name="agrupadorA" id="agrupadorA"/>

        </form>


        <div class="modal fade   " tabindex="-1" role="dialog" id="ModalCamion" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog "  >
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Datos Adicionales</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <h3   >Guia de embarque </h3>
                        <h3  id="agrupador12"> </h3>
                        <div class="col-sm-12">
                            <div class="col-sm-12">
                                <div class="white-box">  
                                    <div class="row"> 
                                        <div class="col-md-12">
                                            <div class="col-md-4">
                                                <form action="<%=request.getContextPath()%>/forms/DescargartxtBanco.jsp" id="enviastatus" method="post">
                                                    <div class="row ">    
                                                        <div class="col-md-1">
                                                            <button align="left" type="submit" class="btn btn-info">Descargar Archivo TXT</button>  
                                                        </div>
                                                        <div class="row ">    
                                                            <div class="col-md-12">
                                                                <input type="hidden" name="nombre" value="DetallePlaneacion1.txt">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="col-md-4 mt-3">   
                                                <div class="col-md-1">
                                                    <button align="left" type="button" onclick="pantallaCom()" class="btn btn-info">Pantalla completa</button>  
                                                    <input type="hidden" id="idpantallaCom" />
                                                </div> 
                                            </div> 
                                        </div>    
                                    </div> 
                                </div>
                            </div>
                                        <div class="modal-footer">
                                             <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                        </div>
                        </div>
                    




                    </div>                           
                   

                </div>
            </div>
           






        <div class="modal fade   " tabindex="-1" role="dialog" id="ModalCamion2" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog "  >
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Guia de embarque</h5>
                        <h3  id="agrupador121"> </h3>
                        <h3  id="agrupador121"> Linea de transporte</h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body"> 



                        <div class="col-md-6"> 
                            Calle
                            <input type="text" name="nombre" class="form-control">  
                        </div> 
                        <div class="col-md-6"> 
                            Estado
                            <input type="text" name="nombre" class="form-control">  
                        </div> 

                        <div class="col-md-6"> 
                            Pais
                            <input type="text" name="nombre" class="form-control">  
                        </div> 
                        <div class="col-md-6"> 
                            C.P. ontms_linea_transporte
                            <input type="text" name="nombre" class="form-control">  
                        </div> 


                        <input type="hidden" id="idpantallaCom1" />
                        <div class="col-sm-12">
                            <div class="col-sm-12">
                                <div class="white-box"> 
                                    <form autocomplete="off">
                                        <div class="row" style="text-align: left">
                                            <div class="col-md-12"> 
                                                <label style="text-align: left" > CartaPorte:Mercancias:AutotransporteFederal</label>
                                                <input type="text" name="nombre" class="form-control">  
                                            </div>  

                                            <div class="col-md-12"> 
                                                <label  > CartaPorte:Mercancias:AutotransporteFederal:IdentificacionVehicular</label>
                                                <input type="text" name="nombre" class="form-control">  
                                            </div> 
                                            <div class="col-md-12"> 
                                                CartaPorte:Mercancias:AutotransporteFederal
                                                <input type="text" name="nombre" class="form-control">  
                                            </div> 



                                            <div class="col-md-12"> 
                                                CartaPorte:FiguraTransporte:Operadores:Operador
                                                <input type="text" name="nombre" class="form-control">  
                                            </div> 
                                            <div class="col-md-12"> 
                                                CartaPorte:FiguraTransporte:Propietario 
                                                <input type="text" name="nombre" class="form-control">  
                                            </div> 

                                            <div class="col-md-12"> 
                                                CartaPorte:FiguraTransporte:Operadores auto complete
                                                <input type="text" name="nombre" class="form-control">  
                                            </div> 

                                            <div class="col-md-6"> 
                                                Calle
                                                <input type="text" name="nombre" class="form-control">  
                                            </div> 
                                            <div class="col-md-6"> 
                                                Estado
                                                <input type="text" name="nombre" class="form-control">  
                                            </div> 

                                            <div class="col-md-6"> 
                                                Pais
                                                <input type="text" name="nombre" class="form-control">  
                                            </div> 
                                            <div class="col-md-6"> 
                                                C.P. ontms_chofer
                                                <input type="text" name="nombre" class="form-control">  
                                            </div> 

                                            <div class="col-md-6"> 
                                                Fechas ontms_embaque
                                                <input type="text" name="nombre" class="form-control">  
                                            </div> 
                                            <div class="col-md-6"> 
                                                fechas
                                                <input type="text" name="nombre" class="form-control">  
                                            </div> 

                                            <div class="col-md-6"> 
                                                Fechas ontms_embaque
                                                <input type="text" name="nombre" class="form-control">  
                                            </div> 
                                            <div class="col-md-6"> 
                                                fechas programada de llegada modificable
                                                <input type="text" name="nombre" class="form-control">  
                                            </div> 
                                            <div class="col-md-6"> 
                                                Fechas ontms_embaque
                                                <input type="text" name="nombre" class="form-control">  
                                            </div> 


                                        </div> 
                                    </form>
                                </div>
                            </div>
                        </div>
                        <br>




                    </div>                           
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">Guardar</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    </div>

                </div>
            </div>
        </div>

        <script>

            function ConsultarRepartoTransportista() {

                fetch('<%=request.getContextPath()%>/ConsultarRepartoTransportista?transporte=<%=transportista%>', {
                            method: 'POST',
                        }).then(r => r.text())
                                .then(data => {
                                    console.log(data);

                                    document.getElementById('dataTable').innerHTML = data;
                                    $(document).ready(function () {
                                        $('#myTable').DataTable();
                                    });
                                }).catch(error => console.log(error));

                    }


                    function ModificarTransportista1() {
                        let agrupador = document.getElementById('idpantallaCom').value;
                        let gde1 = document.getElementById('agrupador12').innerHTML;
                        ModificarTransportista(agrupador, gde1);

                    }
                    function ModificarTransportista(agrupador, gde1) {


                        document.getElementById('agrupador121').innerHTML = gde1;
                        document.getElementById('idpantallaCom1').value = agrupador;
                        $("#ModalCamion").modal('hide');
                        $("#ModalCamion2").modal('show');
                    }

                    function muestraRepartos(agrupador, gde1) {

                        fetch('<%=request.getContextPath()%>/consultarRepartosTransportista?agrupador=' + agrupador, {
                            method: 'POST',
                        }).then(r => r.text())
                                .then(data => {

                                    //  document.getElementById('pedidos2').innerHTML = data;

                                    document.getElementById('agrupador12').innerHTML = gde1;
                                    $("#ModalCamion").modal('show');
                                    document.getElementById('idpantallaCom').value = agrupador;
                                }).catch(error => console.log(error));

                    }

                    function pantallaCom( ) {

                        let idPan = document.getElementById('idpantallaCom').value;
                        let idPan2 = document.getElementById('agrupador12').innerHTML;
                        window.location.href = 'detalleRepartoTransportista.jsp?agrupador=' + idPan + '&titulo=' + idPan2;
                    }
                    /*function muestraPedidos(destinoShipto, agrupador) {
                     
                     fetch("<%=request.getContextPath()%>/ConsultarPedidos?agrupador=" + agrupador + "&shipto=" + destinoShipto, {
                     method: 'POST', 
                     }) .then(r => r.text())
                     .then(data => { 
                     
                     document.getElementById('documentos2').innerHTML = data;
                     $("#dialogpedidos").dialog("open");
                     
                     }).catch(error => console.log(error)); 
                     
                     }*/



        </script>         
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="//cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"   crossorigin="anonymous"></script>

        <!-- start - This is for export functionality only -->



        <script>

                            // $('#example23').DataTable( {
                            //     dom: 'Bfrtip',
                            //     buttons: [
                            //         'copy', 'csv', 'excel',   'print'
                            //     ]
                            // });

        </script>


        <%
            //  } catch (NullPointerException e) {
            //     // out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
            //     // out.println("<script>window.close();</script>");
            //  } catch (Exception e) {
            //     // out.println("Excepcion revise por favor! " + e);
            //     // e.printStackTrace();
            //  }
%>



    </body>
</html>
