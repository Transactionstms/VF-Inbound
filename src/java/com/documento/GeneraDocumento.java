/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documento;

import com.documento.pojos.DocumentoEmbarque;
import com.excel.PlantillaExcel;
import com.usuario.Usuario;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Omar
 */
@WebServlet(name = "GeneraDocumento", urlPatterns = {"/GeneraDocumento"})
public class GeneraDocumento extends HttpServlet {

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
            throws ServletException, IOException {

        String idOpcion = "1";
        String nombreArchivo = "";
        String embarques = "";

        if (request.getParameter("idOpcion") != null) {
            idOpcion = request.getParameter("idOpcion");
        }

        if (Integer.parseInt(idOpcion) == 2) {
            try {
                if (request.getParameter("txtEmbarques") == null) {
                    response.getWriter().write("<img src=\"../img/exclamation.png\" id=\"idArchivo\"  name=\"idArchivo\" title=\"Adverttencia Archivo\" /><br/><b sytyle='color:red';>No inserto Documentos</b>");
                    return;
                }
                if (request.getParameter("txtEmbarques").toString().trim().equals("")) {
                    response.getWriter().write("<img src=\"../img/exclamation.png\" id=\"idArchivo\"  name=\"idArchivo\" title=\"Adverttencia Archivo\" /><br/><b sytyle='color:red';>No inserto Documentos</b>");
                    return;
                }
                embarques = request.getParameter("txtEmbarques").trim();
                String[] embarque = null;
                if (embarques.trim().split("\n").length > 0) {
                    embarque = embarques.trim().split("\n");
                } else {
                    embarque = embarques.trim().split(" ");
                }
                DocumentoEmbarque[] documentos = new DocumentoEmbarque[embarque.length];
                SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmss");
                String path = PlantillaExcel.class.getProtectionDomain().getCodeSource().getLocation().getPath();//Se obtiene la RUTA
                path = path.substring(1, path.indexOf("WEB-INF"));
                path = path.replaceAll("%20", " ");//Se reemplaza para convertir una url a una ruta de filesystem
                Date date = new Date();
                List<InputStream> pdfs = new ArrayList<InputStream>();
                HttpSession ownsession = request.getSession(false);
                Usuario root = (Usuario) ownsession.getAttribute("login.root");
                String embarquesNoEncontrados = "";
                for (int i = 0; i < embarque.length; i++) {
                    DocumentoEmbarque documentoEmbarque = new DocumentoEmbarque();
                    documentoEmbarque = BLDocumento.getDocumento(embarque[i], root);
                    if (documentoEmbarque != null) {
                        nombreArchivo = "plantilla_" + ft.format(date) + embarque[i];
                        documentoEmbarque.setNombreArchivo(nombreArchivo);
                        String nombreArchivoSalida = path + "/tmpPlantillas/" + nombreArchivo + ".pdf";
                        ManifiestoPDF manifiesto = new ManifiestoPDF(nombreArchivoSalida, documentoEmbarque,root.getCliente());
                        pdfs.add(new FileInputStream(nombreArchivoSalida));
                        documentos[i] = documentoEmbarque;
                    } else {
                        embarquesNoEncontrados += embarque[i] + ", ";
                    }
                }
                String nombreArchivoFinal = "ArchivoFinal_" + ft.format(date);
                String pathNombreArchivoFinal = path + "/tmpPlantillas/" + nombreArchivoFinal + ".pdf";
                OutputStream output = new FileOutputStream(pathNombreArchivoFinal);
                ManifiestoPDF.concatPDFs(pdfs, output, true);
                if (!embarquesNoEncontrados.trim().equals("")) {
                    embarquesNoEncontrados = "<b><label style='color:red;'>Embarques no Encontrados: " + embarquesNoEncontrados + "</label></b><br/>";
                }
                response.getWriter().write(embarquesNoEncontrados + "<a href='../tmpPlantillas/" + nombreArchivoFinal + ".pdf'><img src=\"../img/exportPdf.png\" id=\"idArchivo\"  name=\"idArchivo\" title=\"Obtener Archivo\" /></a>");

            } catch (Exception exception) {
                response.getWriter().write("<img src=\"../img/exclamation.png\" id=\"idArchivo\"  name=\"idArchivo\" title=\"Adverttencia Archivo\" /><br/><b sytyle='color:red';>" + exception.getMessage() + "</b>");

            }
        }

    }

    public String getParameterFromMultipart(HttpServletRequest request) {

        return null;
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
        processRequest(request, response);
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
        processRequest(request, response);
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
