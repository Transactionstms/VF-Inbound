/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.insertar.catalogos;

import com.onest.misc.Cuenta;
import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
/**
 *
 * @author luis_
 */

@WebServlet("/uploadServlet")
@MultipartConfig
public class InsertarEvidencia extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        
        HttpSession ownsession = request.getSession();
        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
        DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        oraDB.connect(dbData.getUser(), dbData.getPassword());
        
        String UserId = (String) ownsession.getAttribute("login.user_id_number");
        String cve = (String) ownsession.getAttribute("cbdivcuenta");
        
        String a = request.getParameter("numFiles");
        int numFiles =Integer.parseInt(a); 
        String ruta = "";
        String salida = "";
        
        for (int i = 1; i <numFiles; i++) {
            
            String embarque = request.getParameter("embarque"+i);
            String shipmentId = request.getParameter("shipmentId"+i);

                // Obtiene los Part de cada archivo cargado desde la solicitud
                Part filePart1 = request.getPart("file"+i);
                
            // Verifica si el Part está vacío
            if (filePart1.getSize() != 0) {    

                // Procesa cada archivo por separado
                String fileName1 = shipmentId+"-"+filePart1.getSubmittedFileName();

                // Guardar los archivos en el servidor
                saveFile(filePart1, fileName1);
                
                // Guardar la ruta del archivo en la base de datos
                ruta = "D:/Servicios/VFINBOUND/EVIDENCIAS_EMBARQUE/"+fileName1;

                String inb_gtn = " UPDATE TRA_INC_GTN_TEST SET URL_POD = '"+ ruta +"', STATUS_EMBARQUE = 7 WHERE SHIPMENT_ID = '" + shipmentId + "' ";
                boolean oraOut1 = oraDB.execute(inb_gtn);
                
                String inb_embarque = " UPDATE TRA_INB_EMBARQUE SET EMBARQUE_ESTADO_ID = 4 WHERE EMBARQUE_ID = '" + embarque + "' "; 
                boolean oraOut2 = oraDB.execute(inb_embarque);

            }
            
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

    private void saveFile(Part filePart, String fileName) throws IOException {
        InputStream fileContent = filePart.getInputStream();
        String filePath = "D:/Servicios/VFINBOUND/EVIDENCIAS_EMBARQUE/"+fileName;
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            int bytesRead;
            byte[] buffer = new byte[4096];
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
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