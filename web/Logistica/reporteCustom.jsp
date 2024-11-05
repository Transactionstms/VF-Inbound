<%-- 
    Document   : reporteCustom
    Created on : 22-oct-2024, 14:40:51
    Author     : grecendiz
--%>

<%@page import="com.pantalla.BLPantalla"%>
<%@page import="com.pantalla.Pantalla"%>
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
    SimpleDateFormat formato = new SimpleDateFormat("MM/dd/yyyy");
    String fecha = formato.format(date);
    String[] par = fecha.split("/");
    String fecha_actual = par[0]+"/"+par[1]+"/"+par[2];
%>    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Personalizar Customs</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/css/style.default.css" id="theme-stylesheet">
        <!-- Table css -->
        <link href="<%=request.getContextPath()%>/lib/validationsInbound/reporteCustoms/styleEvents.css" rel="stylesheet" type="text/css"/>
        <!-- jQuery 3.6.0 -->
        <script src="<%=request.getContextPath()%>/lib/jQuery3.6.0/js/jquery.min.js" type="text/javascript"></script>
        <!-- Filtrer Checkbox -->
        <link href="<%=request.getContextPath()%>/lib/validationsInbound/reporteCustoms/styleFiltrerCheckbox.css" rel="stylesheet" type="text/css"/>
        <!-- Descarga de Excel -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.5/xlsx.full.min.js"></script>
        <style>
            .hidden-btn {
               display: none;
            }
        </style>
       
    </head>
    <body>
      
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                String idBodega = ownsession.getAttribute("cbbodegaId").toString();
                String UserId = (String) ownsession.getAttribute("login.user_id_number");

     String sql="WITH SUM_QUANTITY AS (SELECT SHIPMENT_ID, CONTAINER1, SUM(QUANTITY) AS SUMA FROM TRA_INC_GTN_TEST GROUP BY SHIPMENT_ID, CONTAINER1) \n" +
"                     SELECT DISTINCT \n" +
"                     TIE.ID_EVENTO, \n" +
"                     NVL(BP.RESPONSABLE, ' ') AS RESPONSABLE, \n" +
"                     GTN.FINAL_DESTINATION, \n" +
"                     GTN.BRAND_DIVISION, \n" +
"                     NVL(TID.DIVISION_NOMBRE,' '), \n" +
"                     GTN.SHIPMENT_ID, \n" +
"                     GTN.CONTAINER1, \n" +
"                     GTN.BL_AWB_PRO, \n" +
"                     GTN.LOAD_TYPE_FINAL, \n" +
"                      gtn.CANTIDAD_FINAL, \n" +
"                     TIP1.NOMBRE_POD, \n" +
"                     REPLACE(NVL(TO_CHAR(GTN.EST_DEPARTURE_POL, 'MM/DD/YY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(GTN.ETA_PORT_DISCHARGE, 'MM/DD/YY'),' '), '01/01/1970', ' '), \n" +
"                     NVL(GTN.MAX_FLETE, 0) AS EST_ETA_DC, \n" +
"                     REPLACE(NVL(TO_CHAR(GTN.FECHA_CAPTURA, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     TIP2.NOMBRE_POL, \n" +
"                     NVL(TAA.AGENTE_ADUANAL_NOMBRE, ' ') AS AGENTE_ADUANAL, \n" +
"                     GTN.PLANTILLA_ID, \n" +
"                     SYSDATE AS FECHA_CAPTURAOLD, \n" +
"                     TIP1.NOMBRE_POD, \n" +
"                     TIP2.NOMBRE_POL, \n" +
"                     TIBD.NOMBRE_BD, \n" +
"                     REPLACE(NVL(TO_CHAR(GTN.ETA_PLUS2, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIE.EST_ETA_DC, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(GTN.ETA_PLUS, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     NVL(TIE.OBSERVACIONES, ' ') AS OBSERVACIONES, \n" +
"                     TIE.ESTATUS_EVENTO, \n" +
"                     NVL(TIE.REFERENCIA_AA,' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIE.FECHA_CAPTURA, 'MON/DD/YYYY'),' '), '01/01/1970', ' ') \"****************\", \n" +
"                     NVL(TIE.PRIORIDAD,' '), \n" +
"                     NVL(TIC.REFERENCIA_AA,' '), \n" +
"                     NVL(TIC.PAIS_ORIGEN,' '), \n" +
"                     NVL(TIC.SIZE_CONTAINER,' '), \n" +
"                     NVL(TIC.VALOR_USD,' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.ETA_PORT_OF_DISCHARGE, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     NVL(TIC.AGENTE_ADUANAL,' '), \n" +
"                     NVL(TIC.PEDIMENTO_A1,' '), \n" +
"                     NVL(TIC.PEDIMENTO_R1,' '), \n" +
"                     NVL(TIC.MOTIVO_RECTIFICACION_1,' '), \n" +
"                     NVL(TIC.PEDIMENTO_R1_2DO,' '), \n" +
"                     NVL(TIC.MOTIVO_RECTIFICACION_2,' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_RECEPCION_DOCUMENTOS, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     NVL(TIC.RECINTO,' '), \n" +
"                     NVL(TIC.NAVIERA_FORWARDER,' '), \n" +
"                     NVL(TIC.BUQUE,' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_REVALID_LIBE_BL, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_PREVIO_ORIGEN, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_PREVIO_DESTINO, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_RESULTADO_PREVIO, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.PROFORMA_FINAL, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     NVL(TIC.REQUIERE_PERMISO,' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_ENVIO_FICHAS_NOTAS, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FEC_RECEPCION_PERMISOS_TRAMIT, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FEC_ACT_PERMISOS, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FEC_PERM_AUT, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     NVL(TIC.CO_APLIC_PREF_ARANCELARIA,' '), \n" +
"                     NVL(TIC.APLIC_PREF_ARANCELARIA_CO,' '), \n" +
"                     NVL(TIC.REQUIERE_UVA,' '), \n" +
"                     NVL(TIC.REQUIERE_CA,' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_RECEPCION_CA, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     NVL(TIC.NÚMERO_CONSTANCIA_CA,' '), \n" +
"                     NVL(TIC.MONTO_CA,' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_DOCUMENTOS_COMPLETOS, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_PAGO_PEDIMENTO, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_SOLICITUD_TRANSPORTE, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_MODULACION, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     NVL(TIC.MODALIDAD_CAMION_TREN,' '), \n" +
"                     NVL(TIC.RESULT_MODULACION_VERDE_ROJO,' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_RECONOCIMIENTO, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_LIBERACION, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     NVL(TIC.SELLO_ORIGEN,' '), \n" +
"                     NVL(TIC.SELLO_FINAL,' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_RETENCION_AUTORIDAD, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_LIB_POR_RET_AUT, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     NVL(TEC.DESCRIPCION_ESTADO,' '), \n" +
"                     NVL(TIC.MOTIVO_ATRASO,' '), \n" +
"                     NVL(TIC.OBSERVACIONES,' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.LLEGADA_A_NOVA, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.LLEGADA_A_GLOBE_TRADE_SD, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     NVL(TIC.ARCHIVO_M,' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_ARCHIVO_M, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_SOLICIT_MANIP, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_VENCIM_MANIP, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_CONFIRM_CLAVE_PEDIM, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_RECEP_INCREMENT, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     NVL(TIC.T_E,' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_VENCIM_INBOUND, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     NVL(TIC.NO_BULTOS,' '), \n" +
"                     NVL(TIC.PESO_KG,' '), \n" +
"                     NVL(TIC.TRANSFERENCIA,' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_INICIO_ETIQUETADO, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     REPLACE(NVL(TO_CHAR(TIC.FECHA_TERMINO_ETIQUETADO, 'MON/DD/YYYY'),' '), '01/01/1970', ' '), \n" +
"                     NVL(TIC.HORA_TERMINO_ETIQUETADO,' '), \n" +
"                     NVL(TIC.PROVEEDOR,' '), \n" +
"                     NVL(TIC.PROVEEDOR_CARGA,' '), \n" +
"                     NVL(TIC.FY,' '), \n" +
"                     NVL(TIC.AGENTE_ADUANAL_ID,0), \n" +
"                     NVL(TIC.PRIORIDAD,'No'), \n" +
"                     NVL(GTN.ESTATUS,1), \n" +
"                             NVL(TIC.ESTATUS_SEMAFORO,'0'), \n" +
"                             NVL(TIP1.ADUANA_NUMERO,0), \n" +
"                             NVL(TAA.PATENTE_AGENTE_ADUANERO,'0000'), \n" +
"                             NVL(TO_CHAR(GTN.FECHA_IMPORTACION, 'DD/MM/YY'),'-') \n" +
"                             FROM TRA_INB_EVENTO TIE \n" +
"                             INNER JOIN TRA_DESTINO_RESPONSABLE BP ON BP.USER_NID = TIE.USER_NID \n" +
"                             INNER JOIN TRA_INC_GTN_TEST GTN ON GTN.PLANTILLA_ID = TIE.PLANTILLA_ID \n" +
"                             INNER JOIN TRA_INB_POD TIP1 ON TIP1.ID_POD = GTN.POD \n" +
"                             INNER JOIN TRA_INB_POL TIP2 ON TIP2.ID_POL = GTN.POL \n" +
"                             INNER JOIN TRA_INB_BRAND_DIVISION TIBD ON TIBD.ID_BD = GTN.BRAND_DIVISION \n" +
"                             INNER JOIN TRA_INB_AGENTE_ADUANAL TAA ON TAA.AGENTE_ADUANAL_ID = TIP1.AGENTE_ADUANAL_ID \n" +
"                             INNER JOIN TRA_INB_DIVISION TID ON TID.ID_DIVISION = GTN.SBU_NAME \n" +
"                             left JOIN SUM_QUANTITY SQ ON SQ.SHIPMENT_ID = GTN.SHIPMENT_ID AND SQ.CONTAINER1 = GTN.CONTAINER1 \n" +
"                             LEFT JOIN TRA_INB_CUSTOMS TIC ON GTN.SHIPMENT_ID = TIC.SHIPMENT_ID \n" +
"                             LEFT JOIN TRA_ESTADOS_CUSTOMS TEC ON GTN.ESTATUS = TEC.ID_ESTADO \n" +
"                             LEFT JOIN TRA_INB_SEMAFORO TISE ON TIC.SHIPMENT_ID = TISE.SHIPMENT_ID \n" +
"                             WHERE TIE.ESTADO = 1     AND tie.id_evento >= 240000  ";
                
                
        
           
        %>
        <div class="card-body">
         <%
             
          if (db.doDB( "" )) {
                        for (String[] row : db.getResultado()) {
                         %>
         
                         <%=row[1]%>
            
            <%                   

                        }
                    }
         %>
        </div>
        
        
 
    <h1>Reporte de Eventos</h1>
    <table id="tablaReporte">
        <thead>
            <tr>
                <th>ID Evento</th>
                <th>Responsable</th>
                <th>Destino Final</th>
                <th>Shipment ID</th>
                <th>Container</th>
                <th>Fecha ETA</th>
                <!-- Añade más columnas según sea necesario -->
            </tr>
        </thead>
        <tbody></tbody>
    </table>

    <div id="paginacion"></div>

    <script>
        let datos = [];  // Array para almacenar los datos del JSON
        const filasPorPagina = 100;  // Definir cuántos registros se mostrarán por página
        let paginaActual = 1;

        // Función para leer el archivo JSON desde el directorio local
        function cargarJSON() {
            fetch('file:///C:/fuente/sql.json')  // Ruta de tu archivo JSON
                .then(response => response.json())
                .then(data => {
                    datos = data;  // Guardamos los datos en el array
                    cargarPagina(paginaActual);  // Cargamos la primera página
                    generarPaginacion();  // Generamos los botones de paginación
                })
                .catch(error => console.error('Error al cargar el JSON:', error));
        }

        // Función para cargar los datos en la página actual
        function cargarPagina(pagina) {
            const inicio = (pagina - 1) * filasPorPagina;
            const fin = inicio + filasPorPagina;
            const datosPagina = datos.slice(inicio, fin);
            const tbody = document.getElementById("tablaReporte").getElementsByTagName("tbody")[0];
            tbody.innerHTML = "";  // Limpiamos la tabla antes de llenarla

            // Llenamos la tabla con los datos correspondientes a la página
            datosPagina.forEach(dato => {
                let fila = `<tr>
                    <td>${dato.id_evento}</td>
                    <td>${dato.responsable}</td>
                    <td>${dato.final_destination}</td>
                    <td>${dato.shipment_id}</td>
                    <td>${dato.container1}</td>
                    <td>${dato.replace_nvl_to_char_gtn_eta_port_discharge}</td>
                    <!-- Añade más columnas según sea necesario -->
                </tr>`;
                tbody.innerHTML += fila;
            });
        }

        // Función para generar la paginación
        function generarPaginacion() {
            const totalPaginas = Math.ceil(datos.length / filasPorPagina);
            const paginacionDiv = document.getElementById('paginacion');
            paginacionDiv.innerHTML = "";  // Limpiamos la paginación

            for (let i = 1; i <= totalPaginas; i++) {
                let boton = document.createElement("button");
                boton.innerText = i;
                boton.onclick = () => {
                    paginaActual = i;
                    cargarPagina(paginaActual);
                };
                paginacionDiv.appendChild(boton);
            }
        }

        // Cargamos los datos cuando la página esté lista
        cargarJSON();
    </script>
 




  </body>
    <%
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
