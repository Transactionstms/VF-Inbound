<%-- 
    Document   : detalleRepartoTransportista.jsp
    Created on : 29/10/2021, 09:47:11 AM
    Author     : gerardorecendiz
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.conexion.GenericJdbc"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"   crossorigin="anonymous">
        <link rel="stylesheet" href="//cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css"   crossorigin="anonymous">
        
                <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css"   crossorigin="anonymous">
                <link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.0.1/css/buttons.dataTables.min.css"   crossorigin="anonymous">

  
    </head>
    <body  >




        <%
            // try {

            //response.setContentType("text/html;charset=UTF-8"); 
             String agrupador=request.getParameter("agrupador");
             String titulo=request.getParameter("titulo"); 
              
             String sql2 =" SELECT DISTINCT"
                     + " 'SHIPTO', "
+"     bod.bodega_nombre, "
+"     bod.rfc, "
+"     bod.bodega_direccion, "
+"     bod.colonia, "
+"     bod.ciudad, "
+"     bod.bodega_edo, "
+"     bod.destino_cp, "
+"     bod.bodega_direccion, "
+"     bod.no_int, "
+"     bod.no_ext, "
+"     bod.clavelocal, "
+"     bod.clave_mun, "
+"     bod.bodega_pais, "
+"     bod.clave_colonia, "
+"     bod.clavelocal, "
+"     bod.clave_mun, "
+"     'Orden de compra', "
+"     gtn.shipment_id, "
+"      sci.total_actual_wt_kgs, "
+"      gtn.OF_PACKAGES, "
+"      gtn.QUANTITY, "
+"      gtn.volume, "
+"     nvl(cus.PEDIMENTO_A1, nvl(cus.pedimento_r1, nvl(pedimento_r1_2do,' '))), "
+"     to_char(cus.fecha_pago_pedimento, 'mm/dd/yyyy'), "
+"     gtn.style_desc, "
+"     nvl(pod.aduana_numero, 0), "
+"     sty.clave_sat, "
+"     sty.descripcion_sat, "
+"     sty.tipo_embalage, "
+"     sty.embalage_sat, "
 +"     '0', "
+"     '0', "
+"      sci.total_actual_wt_kgs, "
+"     tie.id_evento, "
+"     gtn.doctos_aduaneros, "
+"     gtn.tipo_materia, "
+"     gtn.clave, "
+"     org.puerto "
+"     || ' - ' "
+"     || org.patio "
+"     || ' - ' "
+"     || org.direccion, "
+"     nvl(org.clave_loc, ' '), "
+"     nvl(org.clave_mun, ' '), "
+"     nvl(org.clave_col, ' '),"
                     + " gtn.container1 "
+" FROM "
+"     tra_inc_gtn_test gtn "
+"     LEFT JOIN tra_inb_evento   tie ON gtn.plantilla_id = tie.plantilla_id "
+"     LEFT JOIN tra_inb_customs  cus ON cus.shipment_id = gtn.shipment_id "
+"     LEFT JOIN tra_inb_stylesat sty ON sty.style_desc = gtn.style_desc "
+"     LEFT JOIN tra_inb_sci      sci ON sci.original_shipment_id = gtn.shipment_id  and gtn.STYLE_DESC=sci.PRODUCT_CODE "
+"     LEFT JOIN tra_inb_pod      pod ON pod.id_pod = gtn.pod "
+"     LEFT JOIN ontms_bodega     bod ON bod.bodega_id = 2 "
+"     LEFT JOIN tra_inb_embarque emb ON emb.embarque_agrupador = gtn.embarque_agrupador "
+"     LEFT JOIN tra_inb_origen   org ON org.origen_id = emb.origen_id "
+" WHERE "
+"     GTN.EMBARQUE_AGRUPADOR = '"+agrupador+"' ";
//   ";   

System.out.println("sql***********"+sql2);
        %>
 
              <div class="container">
            <div class="row">
                <div class="col">
                    <p class="h3">Guía de embarque <%=titulo%> </p>
                    <p> <button type="button" onclick="pantallaCom( )" class="btn btn-primary">Regresar</button></p>
                     <div class="table-responsive" style="overflow: auto">  
                        <div class="tableFixHead">

                               <div class="table-responsive" style="overflow: auto">  
                                                            <div class="tableFixHead">
                                                            
                                    <table id="example" class="display nowrap" cellspacing="0" width="100%">
                        <thead class="thead-dark"> 
                            <tr>
                             <th class="repHdr">Shipto</th>
                             <th class="repHdr">Destino</th>
                             <th class="repHdr"><center>RFC</center></th>
                             <th class="repHdr"><center>Domicilio</center></th>
                             <th class="repHdr"><center>Colonia</center></th>
                             <th class="repHdr"><center>Ciudad</center></th>
                             <th class="repHdr"><center>Estado</center></th>
                             <th class="repHdr"><center>Código Postal</center></th>
                             <th class="repHdr"><center>Calle </center></th> 
                             <th class="repHdr"><center>No Exterior </center></th> 
                             
                             <th class="repHdr"><center>No Interior </center></th> 
                             <th class="repHdr"><center>Localidad </center></th> 
                             <th class="repHdr"><center>Municipio </center></th> 
                             <th class="repHdr"><center>País </center></th> 
                             <th class="repHdr"><center>Clave Colonia </center></th> 
                             <th class="repHdr"><center>Clave Localidad </center></th> 
                             <th class="repHdr"><center>Clave Municipio </center></th> 
                             <th class="repHdr"><center>CONTEINER</center></th> 
                             <th class="repHdr"><center>SHIMPEMNT</center></th> 
                             <th class="repHdr"><center>Peso</center></th>
                             
        <th class="repHdr"><center>Cajas</center></th>
                             <th class="repHdr"><center>Piezas</center></th>

                             <th class="repHdr"><center>Volumen</center></th><!-- - Col 24-->
                       <!--Campos nuevos:-->
                             <th class="repHdr"><center>Pedimento</center></th><!-- - Col 25-->
                             <th class="repHdr"><center>Fecha Pedimento</center></th><!-- - Col 26-->
                             <th class="repHdr"><center>Producto</center></th><!-- - Col 27-->
                              <th class="repHdr"><center>Regimen aduanero</center></th>

                             <th class="repHdr"><center>Aduana</center></th> <!-- - Col 28-->
        
        <th class="repHdr"><center>Origen</center></th>
     <!--   <th class="repHdr"><center>Origen clave loc</center></th>
        <th class="repHdr"><center>Origen clave mun</center></th>
        <th class="repHdr"><center>Origen clave col</center></th>-->
                             <th class="repHdr"><center>Clave Producto Servicio</center></th><!-- - Col 29--> 
                             <th class="repHdr"><center>Descripción</center></th>
                             <th class="repHdr"><center>Clave Unidad</center></th>
                             <th class="repHdr"><center>Unidad Medida</center></th> 
                             
                             <th class="repHdr"><center>Peso Bruto     </center></th>
                             <th class="repHdr"><center>Peso Tara      </center></th>
                             <th class="repHdr"><center>Peso Neto      </center></th>
                             <th class="repHdr"><center>Documentos Aduaneros   </center></th>
                             <th class="repHdr"><center>Tipo Materia   </center></th>
                         </tr>
                     </thead> <tbody>
                                                                        <%
                                              GenericJdbc generico = new GenericJdbc();
         generico.openConection();
         Statement statement = generico.getConnection().createStatement();
               ResultSet rs = statement.executeQuery(sql2);
                                                                            while (rs.next()){//db.doDB(cr.viajes(new String[]{cveCuenta, fec1, fec2, t}))

                                                                        
                                                                         %>
                                                                        <tr>
                                                                            <td class=""><b><%=rs.getString(1)%></b></td> 
                                                                            
                                                                            <td class=""><b><%=rs.getString(2)%></b></td>
                                                                            <td class=""><b><%=rs.getString(3)%></b></td> <!--rfc--> 
                                                                            <td class=""><b><%=rs.getString(4)%></b></td>
                                                                            <td class=""><b><%=rs.getString(5)%></b></td>
                                                                            <td class=""><b><%=rs.getString(6)%></b></td> <!--Cajas-->
                                                                            <td class=""><b><%=rs.getString(7)%></b></td>
                                                                            <td class=""><b><%=rs.getString(8)%></b></td> <!-- código postal -->
                                                                            <td class=""><b><%=rs.getString(9)%></b></td>  
                                                                            <td class=""><b><%=rs.getString(10)%></b></td>  
                                                                            
                                                                            <td class=""><b><%=rs.getString(11)%></b></td>  
                                                                            <td class=""><b><%=rs.getString(12)%></b></td>  
                                                                            <td class=""><b><%=rs.getString(13)%></b></td>  
                                                                            <td class=""><b><%=rs.getString(14)%></b></td> 
                                                                            <td class=""><b><%=rs.getString(15)%></b></td>
                                                                            <td class=""><b><%=rs.getString(16)%></b></td>
                                                                            <td class=""><b><%=rs.getString(17)%></b></td>
                                                                            <td class=""><b><%=rs.getString(43)%></b></td> 
                                                                            
                                                                            <!--<td class=""><b><%=rs.getString(18)%></b></td>-->
                                                                            <td class=""><b><%=rs.getString(19)%></b></td>
                                                                            <td class=""><b><%=rs.getString(20)%></b></td> <!--cartón-->
                                                                            
                                                                            <td class=""><b><%=rs.getString(21)%></b></td>
                                                                            <td class=""><b><%=rs.getString(22)%></b></td>
                                                                            <td class=""><b><%=rs.getString(23)%></b></td>
                                                                            <td class=""><b><%=rs.getString(24)%></b></td> <!-- - Col 24-->
                                                                            
                                                                            <td class=""><b><%=rs.getString(25)%></b></td> <!-- - Col 25-->
                                                                            
                                                                            <td class=""><b><%=rs.getString(26)%></b></td> <!-- - Col 26-->
                                                                             <td class=""><b><%=rs.getString(38)%></b></td>   <!-- - Col 27-->
                                             
                                                                            <td class=""><b><%=rs.getString(27)%></b></td> <!-- - Col 27-->
                                                                            
                                                                                <td class=""><b><%=rs.getString(39)%></b></td>
                                                                              <!--  <td class=""><b><%=rs.getString(40)%></b></td>
                                                                                <td class=""><b><%=rs.getString(41)%></b></td>
                                                                                <td class=""><b><%=rs.getString(42)%></b></td> -->
                                                                           
                                                                                <td class=""><b><%=rs.getString(28)%></b></td> <!-- - Col 28--> 
                                                                            <td class=""><b><%=rs.getString(29)%></b></td> <!-- - Col 29-->
                                                                            <td class=""><b><%=rs.getString(30)%></b></td>
                                                                            <td class=""><b><%=rs.getString(31)%></b></td> 
                                                                            
                                                                            <td class=""><b><%=rs.getString(32)%></b></td> 
                                                                            <td class=""><b><%=rs.getString(33)%></b></td> 
                                                                            <td class=""><b><%=rs.getString(34)%></b></td>   
                                                                            <td class=""><b><%=rs.getString(36)%></b></td> 
                                                                            <td class=""><b><%=rs.getString(37)%></b></td>  
                                                                            
                                                                        </tr>

                                                                        <% 
                                                                                 
                                                                            }


                                                                        %> 
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div> 
                                                                    
                                                                    
                        </div>
                    </div> 


                </div>

            </div>
        </div>     
                                      
                     
                                             
                                      
                                                                  
               



    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="//cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"   crossorigin="anonymous"></script>
        
        <script src="https://code.jquery.com/jquery-3.5.1.js"   crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"   crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/buttons/2.0.1/js/dataTables.buttons.min.js"   crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"   crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"   crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"   crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/buttons/2.0.1/js/buttons.html5.min.js"   crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/buttons/2.0.1/js/buttons.print.min.js"   crossorigin="anonymous"></script>
       
 






<script>
   
   function pantallaCom( ) {
      history.back();

           // let idPan = document.getElementById('idpantallaCom').value; 
           // let idPan2 = document.getElementById('agrupador12').value; 
                  //  window.location.href = '<%=request.getContextPath()%>/maniobras/detalleReparto.jsp?agrupador='+idPan+'&titulo='+idPan2;
            }
   // $('#example23').DataTable( {
   //     dom: 'Bfrtip',
   //     buttons: [
   //         'copy', 'csv', 'excel',   'print'
   //     ]
   // });
   
   $(document).ready(function() {
    $('#example').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'copy', 'csv', 'excel', 'pdf', 'print'
        ]
    } );
} );
    
  </script>
  
       
    </body>
</html>





  