/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documento;

import java.io.FileOutputStream;
import java.io.*;
import java.util.*;
import java.sql.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oscar_valois_martinez
 */
@WebServlet(name = "PrintPDF", urlPatterns = {"/PrintPDF"})
public class PrintPDF extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        Document document = new Document();

        try {
            HttpSession ownsession = request.getSession(false);
            DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
            OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
            DB db = new DB(dbData);
            /* Create Connection objects */
            String consulta = request.getParameter("consulta");
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Format\\OSalida.pdf"));
            String ruta = "";
            DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String date = hourdateFormat.toString();
            ruta = "C:\\Format\\OSalida.pdf";
            document.open();
            Paragraph titulo = new Paragraph("Vans off the wall - Retail ");
            titulo.setIndentationLeft(150);
            document.add(titulo);

            Paragraph parrafo = new Paragraph("Formato de recepcion de cartones ");
            parrafo.setAlignment(Element.ALIGN_RIGHT);
            document.add(parrafo);

            Paragraph espacio1 = new Paragraph(" ");
            espacio1.setIndentationLeft(150);
            document.add(espacio1);
            // Este codigo genera una tabla de 3 columnas
            PdfPTable table = new PdfPTable(4);

            // addCell() agrega una celda a la tabla, el cambio de fila
            // ocurre automaticamente al llenar la fila
            table.addCell("Caja");
            table.addCell("BOL");
            table.addCell("Pedido");
            table.addCell("Cantidad");
            if (db.doDB(consulta)) {
                for (String[] row : db.getResultado()) {
                    table.addCell(row[0]);
                    table.addCell(row[1]);
                    table.addCell(row[2]);
                    table.addCell(row[3]);
                }
            }

            // Si desea crear una celda de mas de una columna
            // Cree un objecto Cell y cambie su propiedad span
            PdfPCell recibio = new PdfPCell(new Paragraph("Quien recibio:"));

            // Indicamos cuantas columnas ocupa la celda
            recibio.setColspan(4);
            table.addCell(recibio);

            PdfPCell fecha = new PdfPCell(new Paragraph("Fecha de recepcion:"));

            // Indicamos cuantas columnas ocupa la celda
            fecha.setColspan(2);
            table.addCell(date);
            // Agregamos la tabla al documento            

            PdfPCell tiporec = new PdfPCell(new Paragraph("Incidencias:"));

            // Indicamos cuantas columnas ocupa la celda
            tiporec.setColspan(2);
            table.addCell("0");
            // Agregamos la tabla al documento            

            PdfPCell cello = new PdfPCell(new Paragraph("Sello:"));

            // Indicamos cuantas columnas ocupa la celda
            cello.setColspan(4);
            table.addCell(cello);
            // Agregamos la tabla al documento            
            document.add(table);

            document.close();
            response.sendRedirect(request.getContextPath() + "//MostrarPDFRETAIL?idDocto=" + ruta + "'");
        } catch (Exception e) {
            System.err.println("Ocurrio un error al crear el archivo");
            System.exit(-1);
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
