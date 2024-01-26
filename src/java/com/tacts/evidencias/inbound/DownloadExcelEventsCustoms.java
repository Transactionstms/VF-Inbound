/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.tacts.evidencias.inbound;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Desarrollo Tacts
 */
@WebServlet(name = "DownloadExcelEventsCustoms", urlPatterns = {"/DownloadExcelEventsCustoms"})
public class DownloadExcelEventsCustoms extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filePath = "D:\\Servicios\\VFINBOUND\\ReporteEventosCustoms.xls"; // Ruta del archivo en el servidor

        // Establecer el tipo de contenido y la longitud del archivo a descargar
        response.setContentType("application/octet-stream");
        //response.setContentLengthLong(Long.BYTES);

        // Establecer encabezados para la descarga de archivos
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", "ReporteEventosCustoms.xls");
        response.setHeader(headerKey, headerValue);

        //getServletContext().getResourceAsStream(filePath); ---> Path/Directory Internal Project
        //new FileInputStream(new File(filePath));           ---> Path/Directory External Project
        // Obtener el flujo de entrada del archivo desde el sistema de archivos
        try (InputStream inputStream = new FileInputStream(new File(filePath)); OutputStream outputStream = response.getOutputStream()) {

            // Leer el archivo desde el flujo de entrada y escribirlo en el flujo de salida de la respuesta
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
