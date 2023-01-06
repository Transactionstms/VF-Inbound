/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transactions.documentos;



import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author RicardoMartinez
 */
@WebServlet(name = "CargarDocs", urlPatterns = {"/CargarDocs"})
public class CargarDocs extends HttpServlet {

    private String dirUploadFiles = "C:\\ORACLE\\BLOB\\UBICACION\\";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        PrintWriter out = response.getWriter();
        try {
            File file;
            String excel = new File (".").getAbsolutePath ();
            int maxFileSize = 5000 * 1024;
            int maxMemSize = 5000 * 1024;
            String filePath = "";
            /*
             * TODO output your page here. You may use following sample code.
             */
            String origen = request.getParameter("fichero");
            String destino;
            //String destino = "C:\\ORACLE\\BLOB\\UBICACION\\";
            if (request.getParameter("ficherox") != null) {
                destino = excel;

            } else {
                destino = "C:\\SERVIDORES\\tomcat7test\\webapps\\fotografias\\";
            }

            // String destino =  "C:\\Proyectos\\TransActions-Protectio\\TransActions\\web\\fotografias\\";
            System.out.println("cargra img ..." + origen);
            filePath = destino;
            String variable = (String) request.getParameter("coincidencia_startsWith");
            String jsonid = (String) request.getParameter("file");
            String name = (String) request.getParameter("name");
            String id = (String) request.getParameter("id");
            String filename = request.getParameter("filename");

            /*
             * OutputStream outt = new FileOutputStream(destino+"img.jpg");
             * outt.write(jsonid.getBytes()); outt.close();
             */
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // maximum size that will be stored in memory.
            factory.setSizeThreshold(maxMemSize);
            // Location to save data that is larger than maxMemSize.
            factory.setRepository(new File("c:\\temp1"));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // maximum file size to be uploaded.
            upload.setSizeMax(maxFileSize);
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();
            String fileName = null;
      //      BaseDatos  bd = new BaseDatos("localhost","root","admin");
            while (i.hasNext()) {
                String nombreFile =null;
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    fileName = fi.getName();
                    // Write the file
                    
                    if (fileName.lastIndexOf("\\") >= 0) {
                        nombreFile = filePath+ fileName.substring(fileName.lastIndexOf("\\"));
                        file = new File(nombreFile);
                    } else {
                        nombreFile = filePath
                                + fileName.substring(fileName.lastIndexOf("\\") + 1);
                        file = new File(nombreFile);
                    }
                    fi.write(file);
                    //bd.guardarImagen(nombreFile, "Hola");

                }
                
                
                
            
            }
           //  bd.getImagenes();

            if (request.getParameter("ficherox") != null) {
               
             
             
                LeeExcel e = new LeeExcel(request.getParameter("account"));
                String mensaje = e.LeeArchivo(filePath+ fileName.substring(fileName.lastIndexOf("\\") + 1));
                out.write(mensaje);
                
                
            }
            //out.write("Exito");

        } finally {
            out.close();
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
        } catch (Exception ex) {
            Logger.getLogger(CargarDocs.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(CargarDocs.class.getName()).log(Level.SEVERE, null, ex);
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
