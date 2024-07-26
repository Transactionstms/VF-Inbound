<%-- 
    Document   : subircfdi
    Created on : 01-jun-2023, 11:55:48
    Author     : grecendiz
--%>

 
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="java.io.File"%>
<%@page import="com.onest.oracle.DB"%>
<%@page import="com.onest.oracle.OracleDB"%>
<%@page import="com.onest.oracle.DBConfData"%>
<%@page import="com.dao.ServiceDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Merge PDF</title>
        <!-- Sweetalert -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/css/style.default.css" id="theme-stylesheet">
 </head>
    <body>
        <%
            HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
          
             String ncteid = (String) ownsession.getAttribute("cbdivcuenta");
             String usuari = (String) ownsession.getAttribute("login.user_id_number");
             int usuario=Integer.parseInt(usuari);

       
            String doc = request.getParameter("documento");
         
            String filePath = "D:\\Servicios\\inbound\\CFDI\\TRASLADO\\";
            String url = filePath + "cfdi" + doc + ".pdf"; 
            String secuencia = "";
              secuencia = "update TRA_INB_EMBARQUE_TRASLADO set URL_CFDI ='" + url + "' where EMBARQUE_T_ID = '" + doc + "'";
           System.out.println("secuencia "+secuencia);
            
            boolean oraO = db.doDB(secuencia);
        
            
            File file;
            int maxFileSize = 50000 * 10240;
            int maxMemSize = 50000 * 10240;
            ServletContext context = pageContext.getServletContext();
            boolean renombrar =false;
            boolean unir     =false; 
            String contentType = request.getContentType();
            if ((contentType.indexOf("multipart/form-data") >= 0)) {

                DiskFileItemFactory factory = new DiskFileItemFactory();
                // maximum size that will be stored in memory
                factory.setSizeThreshold(maxMemSize);
                // Location to save data that is larger than maxMemSize.
                factory.setRepository(new File("c:\\temp"));

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                // maximum file size to be uploaded.
                upload.setSizeMax(maxFileSize);
                try {
                    // Parse the request to get file items.
                    List fileItems = upload.parseRequest(request);

                    // Process the uploaded file items
                    Iterator i = fileItems.iterator();
                    
                    int x=0;
                    while (i.hasNext()) {
                        FileItem fi = (FileItem) i.next();
                        if (!fi.isFormField()) {
                            // Get the uploaded file parameters
                            String fieldName = fi.getFieldName();
                            String fileName = fi.getName();

                            fileName ="cfdi"+doc+".pdf";//nombre;  usuario
                            x++;
                            boolean isInMemory = fi.isInMemory();
                            long sizeInBytes = fi.getSize();
                            // Write the file
                            if (fileName.lastIndexOf("\\") >= 0) {
                                file = new File(filePath
                                        + fileName.substring(fileName.lastIndexOf("\\")));
                            } else {
                                file = new File(filePath
                                        + fileName.substring(fileName.lastIndexOf("\\") + 1));
                            }
                            fi.write(file);

                        }
                    } 
                     

                } catch (Exception ex) {
                    System.out.println(ex);
                 }
            }
        %>   
      
    
           <div class="d-flex align-items-stretch">
            <div class="page-holder bg-gray-100">
                <div class="container-fluid px-lg-4 px-xl-5">
                    <!-- Page Header-->


                    <section>
                        <div class="card mb-4">


                            <div class="card-header">
                                <div class="card-heading">SUBIR CFDI </div>
                            </div>
                            <div class="card-body text-muted">
                                <!-- Selección de Clientes -->
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="mb-3">  
                                            <div class="row">

                                                <div class="col-lg-3">
                                                </div> 

                                                <%
                                                    if (oraO) {
                                                       
                                                %>
                                                 <form id="miform1" method="post" enctype="multipart/form-data">  
                                                <div class="col-md-12 col-lg-3 mb-4">
                                                    <div class="card h-100">
                                                        <div class="card-body pt-3">
                                                            <h1>DOCUMENTO GUARDADO </h1> 
                                                        </div>
                                                    </div>
                                                </div>                          
                                            </form>
                                                <%
                                                       
                                                    }else{
                                                %>

                                               <div class="col-md-6 col-lg-3 mb-4">
                                                    <div class="card h-100">
                                                        <div class="card-body pt-3">
                                                            <h1>ERROR AL GUARDAR </h1>                                                           
                                                        </div>
                                                    </div>
                                                </div>  
                                                
                                                
                                                <%
                                                  } 
                                                %>
                                                
                                            </div> 
                                        </div>
                                    </div>
                                </div>  
                            </div>
                        </div> 
                    </section>
                </div> 
            </div>
        </div> 
     

    </body>
</html>
