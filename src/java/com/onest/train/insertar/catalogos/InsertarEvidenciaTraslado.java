/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.insertar.catalogos;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.onest.train.consultas.ConsultasQuery;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author grecendiz
 */
public class InsertarEvidenciaTraslado extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        
        HttpSession ownsession = request.getSession();
        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
        DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        oraDB.connect(dbData.getUser(), dbData.getPassword());
        ConsultasQuery fac = new ConsultasQuery();
        
        String UserId = (String) ownsession.getAttribute("login.user_id_number");
        String cbdiv_id = (String) ownsession.getAttribute("cbdivcuenta");
        
        String embarque_id = request.getParameter("embarque_id");
        String a = request.getParameter("numFiles");
        int numFiles =Integer.parseInt(a); 
        int numShipmentId = 0;
        int numPod = 0;
        String typeFormat = "";
        String salida = "";
        
        for (int i = 1; i <=numFiles; i++) {
            
                //Se obtiene el shipment_id por cada input del html
                String shipmentId = request.getParameter("shipmentId"+i);

                // Obtiene los Part de cada archivo cargado desde la solicitud
                Part filePart1 = request.getPart("file"+i);
                
            // Verifica si el Part está vacío
            if (filePart1.getSize() != 0) {
                
                // Obtén el nombre de archivo original del objeto Part
                String nombreArchivoOriginal = filePart1.getSubmittedFileName();
                System.out.println("Nombre Principal del Archivo: " + nombreArchivoOriginal);
                
                // Verifica el tipo de contenido del archivo    
                String contentType = filePart1.getContentType();
                if (contentType.equals("application/pdf")) {
                    typeFormat = "pdf";
                }else{
                    typeFormat = "png";
                }
                
                // Genera un nuevo nombre para el archivo
                String nuevoNombreArchivo = shipmentId+"-"+i+"."+typeFormat;

                // Obtiene la ruta donde deseas guardar el archivo con el nuevo nombre
                String rutaDestino = "D:\\Servicios\\VFINBOUND\\EVIDENCIAS_EMBARQUE\\TRASLADO\\";

                try {
                    
                    // Crea un nuevo objeto File con la ruta de destino y el nuevo nombre
                    File archivoRenombrado = new File(rutaDestino, nuevoNombreArchivo);

                    // Guarda el archivo en el nuevo destino con el nuevo nombre
                    filePart1.write(archivoRenombrado.getAbsolutePath());
                    
                    // Guardar la ruta del archivo en la base de datos
                    String inb_gtn = " UPDATE TRA_INC_GTN_TEST SET URL_POD_T = '"+ archivoRenombrado +"', STATUS_EMBARQUE = 15 WHERE CONTAINER1 = '" + shipmentId + "' ";
                    boolean oraOut1 = oraDB.execute(inb_gtn);

                } catch (IOException e) {
                    System.out.println("No se pudo renombrar el archivo: " + e.getMessage());
                }
 
            }  
        } 
        
        /*Consultar numero de evidencias por embarque*/
        if (db.doDB(fac.consultarEvidenciaPODEmbarqueInbound(embarque_id, cbdiv_id))) {
            for (String[] rowP : db.getResultado()) {
                
                numShipmentId++; /*contar cuantos shipment_id tiene el número de embarque*/
               
                if(!rowP[1].trim().equals("")){
                    numPod++;  /*contar cuantas evidencias se han subido por shipmentId al número de embarque*/
                }
                
            }
        }
        
        /*Actualizar estatus del embarque al completarse, todas las evidencias POD*/
        if(numShipmentId == numPod){
            String inb_embarque = " UPDATE TRA_INB_EMBARQUE_TRASLADO SET EMBARQUE_ESTADO_ID = 4 WHERE EMBARQUE_T_ID = '" + embarque_id + "' "; 
            boolean oraOut2 = oraDB.execute(inb_embarque);
        }
        
          out.println("<td><br><br><font face=\"arial\"  size=5 color=\"black\">Información Actualizada</font></td>"
                    + "<td><img src='img/bien.png' width='50px' height='50px'></img>"
                    + "<script>window.opener.document.location.reload(); setTimeout(window.close(),2000);</script></td>"
                    + "<td><script>function redireccionarPagina() {\n"
                    + "  window.location ='" + request.getContextPath() + "/Logistica/guiaEmbarque.jsp';\n"
                    + "}\n"
                    + "setTimeout(\"redireccionarPagina()\", 2000);</script>"
                    + "</td>"
                    + "<td>"
                    + "</td></tr>");

          out.print(salida);
          oraDB.close(); //cerrar conexión
            
        }
    }
        
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(InsertarEvidencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(InsertarEvidencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}