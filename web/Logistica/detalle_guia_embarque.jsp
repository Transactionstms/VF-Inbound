<%-- 
    Document   : detalle_guia_embarque
    Created on : 12/06/2023, 05:41:45 AM
    Author     : luis_
--%>

<%@page import="java.util.HashSet"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.onest.train.consultas.ConsultasQuery"%>
<%@page import="com.onest.security.menu.*" %>
<%@page import="com.onest.net.*" %>
<%@page import="com.onest.oracle.*" %>
<%@page import="com.onest.misc.*" %>
<%@page import="com.usuario.Usuario"%>
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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" sizes="16x16" href="../plugins/images/favicon.png">
        <title>Personalizar Customs</title>
        <link rel="stylesheet" href="../lib/css/style.default.css" id="theme-stylesheet">
        <!-- jQuery 3.6.0 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
                OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
                String UserId = (String) ownsession.getAttribute("login.user_id_number");
                String a = request.getParameter("a");
                String cant = "";
                String agrupador = "";
                String id_embarque = "";
                int contador = 0;       
                int bandera = 0;
                
                String test = " SELECT embarque_id, "
                            + "  to_char(embarque_fec_captura,'dd/mm/yyyy hh:mi'), "
                            + " oltr.ltransporte_nombre, "
                            + " outr.utransporte_desc, "
                            + " oc.chofer_nombre "
                            + " FROM ontms_embarque oe "
                            + " INNER JOIN ontms_chofer oc ON oc.chofer_id=oe.chofer_id "
                            + " INNER JOIN ontms_camion oca ON oca.camion_id=oe.camion_id "
                            + " INNER JOIN ontms_linea_transporte oltr ON oltr.ltransporte_id=oca.ltransporte_id "
                            + " INNER JOIN ontms_unidad_transporte outr ON outr.utransporte_id=oca.utransporte_id "
                            + " INNER JOIN ontms_docto_sal ods ON ods.docto_sal_agrupador=oe.embarque_agrupador "
                            + " WHERE oe.EMBARQUE_AGRUPADOR='" + a + "' "
                            + " AND OLTR.ESTATUS=1"
                            + " GROUP BY embarque_id, embarque_fec_captura, oltr.ltransporte_nombre,outr.utransporte_desc,oc.chofer_nombre";
                
                String subquery = " SELECT ods.docto_referencia, "
                                + "   NVL(ods.docto_piezas,0), "
                                + "   oddh.DESTINO_CIUDAD, "
                                + "   oddh.DESTINO_NOMBRE, "
                                + "   oddh.DESTINO_ESTADO, "
                                + "   ods.docto_sal_id, "
                                + "   oe.embarque_agrupador, "
                                + "   oe.embarque_id, "
                                + "   oddh.DIVISION_ID, "
                                + "   ods.CBDIV_ID, "
                                + "   SUM(NVL(odsk.cantidad_rechazada,0)), "
                                + "   NVL(ods.docto_sal_cantidad,0),  "
                                + "   ods.docto_estado_id "
                                + " FROM ontms_docto_sal ods "
                                + " INNER JOIN ontms_embarque oe "
                                + " ON ods.docto_sal_agrupador=oe.embarque_agrupador "
                                + " INNER JOIN ontms_cta_bod_div ocbd "
                                + " ON ods.cbdiv_id = ocbd.cbdiv_id "
                                + " INNER JOIN ontms_bodega ob "
                                + " ON ob.bodega_id = ocbd.bodega_id "
                                + " INNER JOIN ontms_cuenta oc "
                                + " ON oc.cuenta_id = ocbd.cuenta_id "
                                + " INNER JOIN ontms_division odv "
                                + " ON odv.division_id = ocbd.division_id "
                                + " INNER JOIN dilog_destinos_hilti oddh "
                                + " ON oddh.DESTINO_SHIP_TO = ods.destino_id "
                                +"  and oddh.division_id = ods.cbdiv_id   "
                                + " LEFT JOIN ontms_bodega ob1 "
                                + " ON ob1.bodega_id=ods.bodega_id "
                                + " LEFT JOIN ontms_docto_sku odsk "
                                + " ON odsk.docto_sal_id         =ods.docto_sal_id "
                                + " WHERE  "
                                + " oe.embarque_agrupador IN ('"+a+"')  "
                                + " AND ods.docto_estado_id     IN (3,10) "
                                + " AND oe.embarque_estado_id   <>4 "
                                + " GROUP BY ods.docto_referencia, "
                                + "   NVL(ods.docto_piezas,0), "
                                + "   oddh.DESTINO_CIUDAD, "
                                + "   oddh.DESTINO_NOMBRE, "
                                + "   oddh.DESTINO_ESTADO, "
                                + "   ods.docto_sal_id, "
                                + "   oe.embarque_agrupador, "
                                + "   oe.embarque_id, "
                                + "   oddh.DIVISION_ID, "
                                + "   ods.CBDIV_ID, "
                                + "   NVL(ods.docto_sal_cantidad,0),ods.docto_estado_id "
                                + " ORDER BY ods.docto_referencia ";
                
        if (db.doDB(test)) {//validacion.servicioCliente(a,idCuentaBroker)
            bandera = 1;        
                
            if (db.doDB(subquery)) {//validacion.detalleEvidenciaGuiaEmbarque(a, idCuentaBroker)
                bandera = 1;
        %>
        <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <section>
                        <div class="row">
                            <div class="col-lg-12 mb-4 mb-lg-0">
                                <div class="card h-100">
                                    <div class="col-md-12 card-header justify-content-between">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h2 class="card-heading">Personalizar Customs</h2>
                                            </div>
                                        </div>
                                    </div>                                    
                                    <div class="card-body">
                                        <h3 align="center">Evidencia por embarque</h3>
                                        <table align="center" width="75%" border="0">
                                            <%
                                                for (String[] row : db.getResultado()) {

                                                    out.println("<tr><td class=\"repHdrC\" width='10%'>Viaje</td><td  class='texto' width='25%'>" + row[0] + "</td><td class=\"repHdrC\" width='15%'>Fecha</td><td  class='texto' width='35%'>" + row[1] + "</td></tr>");
                                                    out.println("<tr><td class=\"repHdrC\" width='12%'>Transportista</td><td  class='texto'>" + row[2] + "</td><td class=\"repHdrC\">Tipo</td><td  class='texto'>" + row[3] + "</td></tr>");
                                                    out.println("<tr><td class=\"repHdrC\">Chofer</td><td  class='texto' colspan='3'>" + row[4] + "</td></tr>");
                                                }
                                            %>
                                        </table>
                                        <form  action="<%=request.getContextPath()%>/InsertarEvidencia"  method="post" onSubmit="return validarGuia(this);">
                                            <table align="center" width="60%">
                                                <tr><td class="repHdrC" width="3%" >Fecha</td>
                                                    <td class="repHdrC" width="13%">Folio de tienda</td>
                                                    <td class="repHdrC" width="3%">Observaciones</td>
                                                <tr>
                                                    <td class="texto" width="13%"><input type="text" name="fecha" id="fecha" size="10" readonly><input type="reset" class="btn"  value=" ... " onclick="return showCalendar('fecha', '%d/%m/%Y');"></td>
                                                    <td class="texto" ><input type="text" name="folio" id="folio" size="15"></td>
                                                    <td class="texto" width="10%"><textarea name="observaciones" cols="45" rows="1"></textarea></td>
                                                </tr>
                                            </table>
                                            <hr size='4' color='#DDDDDD'>
                                            <table align="center" width="90%">
                                                <tr>
                                                    <td class="repHdr" ></td>
                                                    <td class="repHdr" width="8%">No. de factura</td>
                                                    <td class="repHdr">Destino</td>
                                                    <td class="repHdr">Ciudad</td>
                                                    <td class="repHdr" >Estado</td>
                                                    <td class="repHdr" width="8%">Piezas</td>
                                                    <td class="repHdr" width="8%">Rechazo</td>
                                                    <td class="repHdr"  width="8%">Entregadas</td>
                                                </tr>
                                                <%
                                                    for (String[] row : db.getResultado()) {
                                                        contador++;
                                                        if (row[10].equals("0")) { 
                                                            cant = row[10];
                                                        }
                                                        out.println("<tr><td  class=\"repDatNon\">" + contador + "</td><td  class=\"repDatNon\">" + row[0] + "</td>");
                                                        out.println("<td  class=\"repDatNon\">" + row[2] + "</td><td  class=\"repDatNon\">" + row[3] + "</td>");
                                                        out.println("<td  class=\"repDatNon\">" + row[4] + "</td><td  class=\"repDatNon\">" + row[1] + "</td><td  class=\"repDatNon\">" + cant + "</td>");
                                                        out.println("<td  class=\"repDatNon\"><input type='text' name='e" + contador + "' value='' size='5'/> </td>"); 
                                                %>
                                                <input type='hidden' name='p<%=contador%>' value='<%=row[1]%>' size='5'/>
                                                <input type='hidden' name='r<%=contador%>' value='<%=row[10]%>' size='5'/>
                                                <%
                                                        agrupador = row[6];
                                                        id_embarque = row[7];
                                                    }
                                                %>
                                                <tr><td align="center"><input type="hidden" name="tipo" value="1"/></td></tr>
                                                <tr><td align="center"><input type="hidden" name="contador" value="<%=contador%>"/></td></tr>
                                                <tr><td align="center"><input type="hidden" name="agrupador" value="<%=a%>"/></td></tr>
                                                <tr><td align="center"><input type="hidden" name="pag" value="Logistica/guiaEmbarque.jsp"/></td></tr>
                                                <tr><td colspan="7" align="center"><input type="submit" class="btn"  value="Grabar"/></td></tr>
                                            </table>
                                        </form>
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
                                <p class="mb-2 mb-md-0 fw-bold">Transactions TMS &copy; <%=part3%></p>
                            </div>
                            <div class="col-md-6 text-center text-md-end text-gray-400">
                                <p class="mb-0">Version 1.1.0</p>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div> 
        <!-- JavaScript files -->
        <script src="../lib/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    </body>
    <%
          }
              } else {
                  out.println("<p class='errorHdr'>El embarque no se encuentra</p>");
              }

          } catch (NullPointerException e) {
              System.out.println("Error:" + e);
              out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
              out.println("<script>window.close();</script>");
          } catch (Exception e) {
              out.println("Excepcion revise por favor! " + e);
              e.printStackTrace();
          }
    %>
</html>

