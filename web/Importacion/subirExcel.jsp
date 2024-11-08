<%-- 
    Document   : subirExcel
    Created on : 27-abr-2023, 13:15:25
    Author     : grecendiz
--%>




<%@page import="com.onest.graficas.StoredProcedureExample"%>
<%@page import="com.tacts.plantillas.ExcelToOracle"%>
<%@page import="com.onest.oracle.DB"%>
<%@page import="com.onest.oracle.OracleDB"%>
<%@page import="com.onest.oracle.DBConfData"%>
<%@page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@page import="javax.servlet.http.*" %>
<%@page import="org.apache.commons.fileupload.*" %>
<%@page import="org.apache.commons.fileupload.disk.*" %>
<%@page import="org.apache.commons.fileupload.servlet.*" %>
<%@page import="org.apache.commons.io.output.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession ownsession = request.getSession(false);
    DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
    OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
    DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));

    //  response.setHeader("Access-Control-Allow-Origin", "*");
    //  response.setHeader("Content-Type", "application/json");
    //  response.setHeader("Accept", "multipart/form-data");
    //  response.setHeader("GET", "POST");
    String nombreFile = request.getParameter("nombre");
    System.out.println("aqui");

    File file;
    int maxFileSize = 50000 * 10240;
    int maxMemSize = 50000 * 10240;
    // Aqu hay que cambiar el path dependiendo a donde se quiera subir en el server

    String filePath = "D:\\Servicios\\wspod\\INBOUND\\";
     
    String respuesta = "";
    String contentType = request.getContentType();
    if ((contentType.indexOf("multipart/form-data") >= 0)) {

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemSize);
        factory.setRepository(new File("c:\\temp"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(maxFileSize);
        try {
            List fileItems = upload.parseRequest(request);
            Iterator i = fileItems.iterator();
            respuesta += "{\"status\": \"OK\", \"images\": [";

            while (i.hasNext()) {

                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    String fieldName = fi.getFieldName();
                    String fileName = nombreFile;// fi.getName();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    file = new File(filePath + fileName);
                    fi.write(file);
                    respuesta += "{\"filename\":\"" + filePath + fileName + "\"} ";
                }
            }
            
            respuesta += "], \"message\": \"OK\"}";

            ////oraDB.connect(dbData.getUser(), dbData.getPassword());
            String id = request.getParameter("id");

            //  String sql = "update tra_productos set url_img= '"+nombreFile+"' where producto_id=" + id;
            //  boolean oraOut5 = db.doDB(sql);
            // out.print(respuesta);
        } catch (Exception ex) {
            System.out.println(ex);
            //out.println("{\"status\":\"ERROR\", \"message\":\"" + ex.getMessage()+ "\"}");
        }
    } else {
        //out.println("{'status':\"ERROR\", \"message\":\"No file uploaded\"}");
    }

    String sql1 = request.getParameter("idp");

    //ExcelToOracle excelToOracle = new ExcelToOracle();
    String url1 = nombreFile;

    
    
    String protocol = request.getScheme(); // Obtiene el protocolo (HTTP o HTTPS)
String serverName = request.getServerName(); // Obtiene el nombre del servidor (localhost)
int serverPort = request.getServerPort(); // Obtiene el puerto del servidor (8080)

// Construye la URL base concatenando el protocolo, el nombre del servidor y el puerto
String baseURL = protocol + "://" + serverName + ":" + serverPort;

out.println(baseURL); // Imprime la URL base en la salida
System.out.println("baseURL"+baseURL);

    // String mensaje=excelToOracle.ExcelToOracle( sql1,url1 );
// try{}catch(Exception e){}
%>


<!doctype html>
<html lang="esp">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


        <title>TACTS</title>

        <style>

            .loader {
                width: 948px;
                height: 948px;
                border-radius: 50%;
                position: relative;
                animation: rotate 1s linear infinite
            }
            .loader::before , .loader::after {
                content: "";
                box-sizing: border-box;
                position: absolute;
                inset: 0px;
                border-radius: 50%;
                border: 50px solid #FFF;
                animation: prixClipFix 2s linear infinite ;
            }
            .loader::after{
                border-color: #FF3D00;
                animation: prixClipFix 2s linear infinite , rotate 0.5s linear infinite reverse;
                inset: 60px;
            }

            @keyframes rotate {
                0%   {transform: rotate(0deg)}
                100%   {transform: rotate(360deg)}
            }

            @keyframes prixClipFix {
                0%   {clip-path:polygon(50% 50%,0 0,0 0,0 0,0 0,0 0)}
                25%  {clip-path:polygon(50% 50%,0 0,100% 0,100% 0,100% 0,100% 0)}
                50%  {clip-path:polygon(50% 50%,0 0,100% 0,100% 100%,100% 100%,100% 100%)}
                75%  {clip-path:polygon(50% 50%,0 0,100% 0,100% 100%,0 100%,0 100%)}
                100% {clip-path:polygon(50% 50%,0 0,100% 0,100% 100%,0 100%,0 0)}
            }

        </style>
    </head>
    <body>

        <a id="btnid" class="padre" href="#">
            <div class="card border-success mb-3 hijo_dos" style="max-width: 18rem;">
                <div class="card-header ml-3"> </div>
                <div class="card-body text-success">

                    <div class="d-flex justify-content-center align-items-center">
                        <div class="text-center"  id="espere">
                            <!-- Contenido a centrar -->
                            <h1 class="card-title">  Espere....   </h1>
                            <p>Espere estamos procsando.</p>
                        </div>
                        <span class="loader" id="loader" ></span>

                        <div class="overflow-scroll" style="height: 300px;overflow: auto;"  id="respuesta">   </div> 

                    </div> 
                </div>
            </div>
        </a> 


        <style>

            
            

            .padre {
                border: 1px solid #ccc;
                padding: 0 1rem;
                margin: 1rem;
            }

            .hijo_uno { 

                /* IMPORTANTE */
                width: 200px;
                margin-left: auto;
                margin-right: auto;
            }

            .hijo_dos {

                /* IMPORTANTE */
                width: 200px;
                margin: 0 auto;
            }

        </style>
        <script>
            
            
            
            window.onload = fetchData();
         let reloadRealizado = localStorage.getItem('reloadRealizado') === 'true';


            async function fetchData() {
                console.log('aqui');
                let fecha = Date.now();
                console.log(fecha);
                try {
                      //let urlF='https://www.ta2.mx/Plantillas/SubirExcelSP.jsp?ins=<%=sql1%>&rut=<%=url1%>&folio='+fecha;
                      // let urlF='http://74.208.140.125:8080/Plantillas/SubirExcelSP.jsp?ins=<%=sql1%>&rut=<%=url1%>&folio='+fecha;
                      //  let urlF = 'http://localhost:8084/Plantillas/SubirExcelSP.jsp?ins=<%=sql1%>&rut=<%=url1%>&folio=' + fecha;
                     // let urlF='https://www.tacts.mx/Plantillas/SubirExcelSP.jsp?ins=<%=sql1%>&rut=<%=url1%>&folio='+fecha;

                      let urlF='<%=baseURL%>/Plantillas/SubirExcelSP.jsp?ins=<%=sql1%>&rut=<%=url1%>&folio='+fecha;

                    const response = await fetch(urlF); // Realiza la petición Fetch y espera la respuesta

                    if (!response.ok) {
                        throw new Error('Error en la solicitud: ' + response.status);
                    }

                    const data = await response.text(); // Espera a que los datos de respuesta se conviertan en formato JSON
                    document.getElementById('espere').innerHTML = '<h1>Correcto</h1>';
                    document.getElementById('loader').style.display = 'none';

                    // Obtén el elemento de la caja
                    const caja = document.getElementById('respuesta');

                    // Agrega contenido dinámicamente utilizando innerHTML
                    respuesta.innerHTML = data;

                    // Aplica las clases de desplazamiento después de agregar contenido
                    caja.classList.add('overflow-auto');
                          if(<%=sql1%>===20 || <%=sql1%> === 40){
                         window.location.href = '<%=request.getContextPath()%>/Importacion/logGntbatch.jsp?folio=' + fecha;
                         console.log('<%=request.getContextPath()%>/Importacion/logGntbatch.jsp?folio=' + fecha)

                       
                                         
                    }else if(<%=sql1%>===21){
                         window.location.href = '<%=request.getContextPath()%>/Importacion/logDsnBatch.jsp?folio=' + fecha;

                    }else if (<%=sql1%> === 30) {
                        window.location.href = '<%=request.getContextPath()%>/Importacion/logActBatch.jsp?folio=' + fecha;

                    }else if (<%=sql1%> === 31 || <%=sql1%> === 35) {
                        window.location.href = '<%=request.getContextPath()%>/Importacion/logRDIBatch.jsp?folio=' + fecha;

                    }else if (<%=sql1%> === 33) {
                        window.location.href = '<%=request.getContextPath()%>/Importacion/logSCIBatch.jsp?folio=' + fecha;

                    }
                    else if (<%=sql1%> === 36 || <%=sql1%> === 37) {
                        window.location.href = '<%=request.getContextPath()%>/Importacion/logibr.jsp?folio=' + fecha;

                    }
                    else if (<%=sql1%> === 41) {
                        window.location.href = '<%=request.getContextPath()%>/Importacion/logbobj.jsp?folio=' + fecha;

                    }
                    else{
                        
                    }

                    console.log(data); // Utiliza los datos recibidos  
                } catch (error) {
                 
               
                        if (!reloadRealizado) {
                            document.getElementById('espere').innerHTML = 'Error, por favor inténtelo nuevamente';
                            document.getElementById('loader').style.display = 'none';
                            console.error('Error en la solicitud:', error);
                            localStorage.setItem('reloadRealizado', 'true'); // Establecer la bandera en el localStorage
                            window.location.reload();
                        } else {
                              document.getElementById('espere').innerHTML = 'Error, por favor inténtelo nuevamente';
                            document.getElementById('loader').style.display = 'none';
                            console.error('Error en la solicitud:', error);
                            console.log('Reload ya realizado, evitando bucle infinito.');
                            localStorage.setItem('reloadRealizado', 'false');
                        }
    
                }
            }

          
        </script>

    </body>
</html>



