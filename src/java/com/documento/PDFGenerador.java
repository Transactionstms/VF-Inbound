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

/**
 *
 * @author oscar_valois_martinez
 */
@WebServlet(name = "PDFGenerador", urlPatterns = {"/PDFGenerador"})
public class PDFGenerador extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, DocumentException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession ownsession = request.getSession(false);
        DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        DB db = new DB(dbData);

        try (PrintWriter out = response.getWriter()) {
            /* Create Connection objects */
            String consulta = request.getParameter("consulta");

            Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using_java.pdf"));
            my_pdf_report.open();
            //we have four columns in our table
            PdfPTable my_report_table = new PdfPTable(4);
            //create a cell object
            PdfPCell table_cell;

            if (db.doDB(consulta)) {
                for (String[] row : db.getResultado()) {
                    String dept_id = row[0];
                    table_cell = new PdfPCell(new Phrase(dept_id));
                    my_report_table.addCell(table_cell);
                    String dept_name = row[1];
                    table_cell = new PdfPCell(new Phrase(dept_name));
                    my_report_table.addCell(table_cell);
                    String manager_id = row[2];
                    table_cell = new PdfPCell(new Phrase(manager_id));
                    my_report_table.addCell(table_cell);
                    String location_id = row[3];
                    table_cell = new PdfPCell(new Phrase(location_id));
                    my_report_table.addCell(table_cell);
                }
            }
            /* Attach report table to PDF */
            my_pdf_report.add(my_report_table);
            my_pdf_report.close();
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
