/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transactions.documentos;

import com.dao.ServiceDAO;
import com.management.util.Loggin;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author grecendiz
 */
@WebServlet(name = "MostrarPDF_CFDI", urlPatterns = {"/MostrarPDF_CFDI"})
public class MostrarPDF_CFDI extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {
            ServiceDAO dao = new ServiceDAO();
            String agrupador = request.getParameter("agrupador");
            
            String sql = " SELECT DISTINCT "
                       + " NVL(URL_CFDI,'SIN INFORMACIÓN') "
                       + " FROM tra_inb_embarque "
                       + " WHERE EMBARQUE_AGRUPADOR = TRIM('" + agrupador + "')";
            try {
                PreparedStatement ps = dao.conectar().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                String nomArchivo = "";

                while (rs.next()) {
                    nomArchivo = rs.getString(1);
                }
                
                if (!nomArchivo.equals("")) {
                    //nomArchivo = "C:\\Users\\jasperjj7\\Documents\\3D\\PDF\\070026396562001171.PDF";
                    //nomArchivo = "C:\\Users\\jasperjj7\\Documents\\3D\\PDF\\531563_904856163.jpg";
                    //nomArchivo = "Sin POD";
                    if (nomArchivo.equals("Sin POD")) {
                        PrintWriter out = response.getWriter();
                        out.println("<center><label style='color:red;font-size:20px;font-family:sans-serif;'>No existe el documento PDF</label></center>");
                    } else {
                        try {
                            File file = new File(nomArchivo);
                            if (file.exists()) {
                                String exten = FilenameUtils.getExtension(nomArchivo);
                                FileInputStream archivo = new FileInputStream(file);
                                int longitud = archivo.available();
                                byte[] datos = new byte[longitud];
                                archivo.read(datos);
                                archivo.close();
                                if (exten.equalsIgnoreCase("pdf")) {
                                    response.setContentType("application/pdf");
                                    response.setHeader("Content-Disposition", "inline; filename=PDF_VFTMS.pdf");
                                    response.setHeader("Cache-control", "public");
                                } else if (exten.equalsIgnoreCase("jpg") || exten.equalsIgnoreCase("jpeg")) {
                                    response.setContentType("image/jpeg"); //
                                    response.setHeader("Content-Disposition", "inline; filename=imagen.jpg");
                                    response.setHeader("Cache-control", "public");
                                } else if (exten.equalsIgnoreCase("png")) {
                                    response.setContentType("image/png");
                                    response.setHeader("Content-Disposition", "inline; filename=imagen.png");
                                    response.setHeader("Cache-control", "public");
                                }
                                ServletOutputStream ouputStream = response.getOutputStream();
                                ouputStream.write(datos);
                                ouputStream.flush();
                                ouputStream.close();
                            } else {
                                PrintWriter out = response.getWriter();
                                out.println("<center><label style='color:red;font-size:20px;font-family:sans-serif;'>Consulta no valida, revise por favor</label></center>");
                            }
                        } catch (Exception e) {
                            e.printStackTrace(System.err);
                            Loggin.error(e.toString(), e);
                        }
                        Loggin.debug("Final");
                    }
                } else {
                    out.println("<center><label style='color:red;font-size:20px;font-family:sans-serif;'>No existe el documento PDF en la ruta indicada. Por favor, contacte al adminstrador.</label></center>");
                }
                dao.CerrarConexion();
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
                Loggin.error(ex.toString(), ex);
            }
        } finally {
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }
}

