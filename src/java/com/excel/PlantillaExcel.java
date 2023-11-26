/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author miguel
 */
@WebServlet(name = "PlantillaExcel", urlPatterns = {"/PlantillaExcel"})
public class PlantillaExcel extends HttpServlet {

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

        InputStream stream = null;
        String nombreArchivo = null;

        String idLenguaje = "1";
        String idOpcion = "1";
        String idPlantilla = "1";
        String idDivision = "0";
        String idBodega = "0";

        Workbook wb = null;
        Sheet hoja = null;

        if (request.getParameter("idLenguaje") != null) {
            idLenguaje = request.getParameter("idLenguaje");
        }
        if (request.getParameter("idOpcion") != null) {
            idOpcion = request.getParameter("idOpcion");
        }
        if (request.getParameter("idPlantilla") != null) {
            idPlantilla = request.getParameter("idPlantilla");
        }
        if (request.getParameter("idDivision") != null) {
            idDivision = request.getParameter("idDivision");
        }
        if (request.getParameter("idBodega") != null) {
            idBodega = request.getParameter("idBodega");
        }
        if (Integer.parseInt(idOpcion) == 1) {
            try {
                wb = BLexcel.getPlantillaExcel(Integer.parseInt(idPlantilla), Integer.parseInt(idLenguaje));
                SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmss");
                Date date = new Date();
                nombreArchivo = "plantilla_" + ft.format(date);
                String path = PlantillaExcel.class.getProtectionDomain().getCodeSource().getLocation().getPath();//Se obtiene la RUTA
                path = path.substring(1, path.indexOf("WEB-INF"));
                path = path.replaceAll("%20", " ");//Se reemplaza para convertir una url a una ruta de filesystem
                String nombreArchivoSalida = path + "/tmpPlantillas/" + nombreArchivo + ".xls";
                FileOutputStream fileOut = new FileOutputStream(nombreArchivoSalida);
                wb.write(fileOut);
                fileOut.flush();
                fileOut.close();

                response.getWriter().write("<a href='../tmpPlantillas/" + nombreArchivo + ".xls'><img src=\"../img/tomaPlantilla.png\" id=\"idArchivo\"  name=\"idArchivo\" title=\"Obtener Archivo\" width=\"5%\"/></a>");
                //wb.write(response.getOutputStream());
                //response.setContentType("application/octet-stream");
                //response.setHeader("Content-Disposition", "attachment;filename=plantilla" + idPlantilla + ".xls");
                //request.setAttribute("mensaje", "La plantilla se genero satisfactoriamente.");
            } catch (Exception exception) {
                response.getWriter().write(exception.getMessage());
                //request.setAttribute("mensaje", exception.getMessage());
            }
        } else if (Integer.parseInt(idOpcion) == 2) {
            try {
                if (request.getParameter("documento") == null) {
                    response.getWriter().write("<b>[ERROR]</b>   No ha seleccionado Archivo");
                    return;
                }
                stream = new ByteArrayInputStream(Base64.decodeBase64(request.getParameter("documento")));
                nombreArchivo = request.getParameter("nombre_documento");
                   
                if (stream != null && (nombreArchivo.contains("xls") || nombreArchivo.contains("xlsx"))) {
                    if (nombreArchivo.endsWith("xlsx")) {
                        wb = new XSSFWorkbook(stream);
                    } else {
                        wb = new HSSFWorkbook(stream);
                    }
                    if (wb != null) {
                        String respuestaHtml = BLexcel.setPlantilla(Integer.parseInt(idPlantilla), Integer.parseInt(idLenguaje), Integer.parseInt(idDivision),Integer.parseInt(idBodega), wb);
                        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
                        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
                        response.getWriter().write(respuestaHtml);
                        return;
                        //System.out.println(respuestaHtml);
                    }

                } else {
                    response.getWriter().write("Se desconoce el archivo");
                }
            } catch (Exception exception) {
                response.getWriter().write(exception.getMessage());
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
