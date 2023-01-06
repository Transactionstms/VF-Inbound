/**
 *
 * @author _luis
 */

package com.tacts.retail.formats;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Desktop;
import java.io.File;
import com.itextpdf.text.Document;
import java.io.FileOutputStream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.usuario.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.itextpdf.text.PageSize;
import java.io.File;
import java.io.FileOutputStream;



@WebServlet(name = "Traspaso_Merma", urlPatterns = {"/Traspaso_Merma"})
public class Traspaso_Merma extends HttpServlet {

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
        HttpSession ownsession = request.getSession(false);
        response.setContentType("text/html;charset=UTF-8");
        Usuario root = (Usuario) ownsession.getAttribute("login.root");
        DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
         
        String empresa= request.getParameter("empresa"), nombre = request.getParameter("nombre"),ap = request.getParameter("ap"),am = request.getParameter("am");
        String credito= request.getParameter("credito"),descuento = request.getParameter("descuento"), ciudad= request.getParameter("ciudad"), zona = request.getParameter("zona");
        String idd = request.getParameter("id"),FechaIn = request.getParameter("fechaIn"); 
        //String app = ap.replace("Ã", "Ñ"),amm = am.replace("Ã", "Ñ"),nombre2 = nombre.replace("Ã", "Ñ");
        Date date = new Date();
        
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = formato.format(date);
            String fechaa = request.getParameter("fecha");
            String [] par = fecha.split("/");
            String part1 = par[0];
            String part2 = par[1];
            String part3 = par[2];
        Date fechaaa = new Date(); 
            
            Document documento = new Document(PageSize.LETTER, 25, 25, 25, 25); // (PageSize.LETTER, 20, 20, 20, 20);
            
            FileOutputStream ficheroPdf;
            try {
                ficheroPdf = new FileOutputStream("D:\\Servicios\\PDF\\Traspaso_Merma.pdf");
                PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
            try {
                documento.open();

                Font fontTitus = FontFactory.getFont("Calibri", 12, Font.BOLD, BaseColor.BLACK);
                Font espacio = FontFactory.getFont("Times New Roman", 7, Font.BOLD, BaseColor.BLACK);
                Font espaciox = FontFactory.getFont("Calibri", 10, Font.NORMAL, BaseColor.BLACK);
                Font espacios = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
                Font fontTitutu = FontFactory.getFont("arial", 5, Font.BOLD, BaseColor.BLACK);
                Font fontTitututu = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);

                Paragraph parrafo1 = new Paragraph("FORM IC740", espaciox);
                parrafo1.setAlignment(2);
                documento.add(parrafo1);

                Image imagen = Image.getInstance("D:\\Servicios\\PDF\\LOGO-VANS.png");
                imagen.scalePercent(6);
                imagen.setAbsolutePosition(30f, 680f);
                //imagen.setAlignment(0);
                documento.add(imagen);

                documento.add(new Paragraph(" ", espacio));
                documento.add(new Paragraph(" ", espacio));
                Paragraph titulo1 = new Paragraph("MERMA / DEAMAGE LOG", fontTitus);
                titulo1.setAlignment(1);
                documento.add(titulo1);

                Paragraph titulo2 = new Paragraph("TRASPASO DE MERMA MOS #730", fontTitus);
                titulo2.setAlignment(1);
                documento.add(titulo2);

                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));

                if ("01".endsWith(part2)) {
                    Paragraph fe1 = new Paragraph("FECHA:   "+part1+" de Enero de "+part3+"");fe1.setAlignment(2);documento.add(fe1);
                }
                if ("02".endsWith(part2)) {
                    Paragraph fe2 = new Paragraph("FECHA:   "+part1+" de Febrero de "+part3+"");fe2.setAlignment(2);documento.add(fe2);
                }
                if ("03".endsWith(part2)) {
                    Paragraph fe3 = new Paragraph("FECHA:   "+part1+" de Marzo de "+part3+"");fe3.setAlignment(2);documento.add(fe3);
                }
                if ("04".endsWith(part2)) {
                    Paragraph fe4 = new Paragraph("FECHA:   "+part1+" de Abril de "+part3+"");fe4.setAlignment(2);documento.add(fe4);
                }
                if ("05".endsWith(part2)) {
                    Paragraph fe5 = new Paragraph("FECHA:   "+part1+" de Mayo de "+part3+"");fe5.setAlignment(2);documento.add(fe5);
                }
                if ("06".endsWith(part2)) {
                    Paragraph fe6 = new Paragraph("FECHA:   "+part1+" de Junio de "+part3+"");fe6.setAlignment(2);documento.add(fe6);                
                }
                if ("07".endsWith(part2)) {
                    Paragraph fe7 = new Paragraph("FECHA:   "+part1+" de Julio de "+part3+"");fe7.setAlignment(2);documento.add(fe7);
                }
                if ("08".endsWith(part2)) {
                    Paragraph fe8 = new Paragraph("FECHA:   "+part1+" de Agosto de "+part3+"");fe8.setAlignment(2);documento.add(fe8);
                }
                if ("09".endsWith(part2)) {
                    Paragraph fe9 = new Paragraph("FECHA:   "+part1+" de Septiembre de "+part3+"");fe9.setAlignment(2);documento.add(fe9);
                }
                if ("10".endsWith(part2)) {
                    Paragraph fe10 = new Paragraph("FECHA:   "+part1+" de Octubre de "+part3+"");fe10.setAlignment(2);documento.add(fe10);
                }
                if ("11".endsWith(part2)) {
                    Paragraph fe11 = new Paragraph("FECHA:   "+part1+" de Noviembre de "+part3+"");fe11.setAlignment(2);documento.add(fe11);
                }
                if ("12".endsWith(part2)) {
                    Paragraph fe12 = new Paragraph("FECHA:   "+part1+" de Diciembre de "+part3+"");fe12.setAlignment(2);documento.add(fe12);
                }

                PdfPTable table3 = new PdfPTable(1);
                table3.getDefaultCell().setBorderWidth(0f);
                table3.addCell(new Paragraph("TIENDA:"));
                table3.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table3.setWidthPercentage(29F);
                documento.add(table3);
                
                /*
                PdfPCell rightCell = new PdfPCell();
                rightCell.addElement(right);
                table.addCell(rightCell);
                document.add(table);
                */
                
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                //if (db.doDB(cd.doctosCarta(embarque, request.getParameter("r")))) {
                //     for (String[] row1 : db.getResultado()) {

                PdfPTable table49 = new PdfPTable(6);
                table49.setWidthPercentage(100);
                float[] medidaCeldas49 = {0.22f, 1.40f, 1.15f, 1.15f, 0.22f, 0.55f};  
                table49.setWidths(medidaCeldas49);
                //table49.setHorizontalAlignment(Element.ALIGN_CENTER);     // (Element.ALIGN_RIGHT);
                table49.addCell(new Paragraph("  LN", espacios));
                table49.addCell(new Paragraph("                      DESCRIPCION", espacios));
                table49.addCell(new Paragraph("                       UPC #", espacios));
                table49.addCell(new Paragraph("                      RAZON", espacios));
                table49.addCell(new Paragraph(" QTY", espacios));
                table49.addCell(new Paragraph("     INICIALES", espacios));
                //cell2.HorizontalAlignment = PdfPCell.ALIGN_CENTER;
                documento.add(table49);
                
                PdfPTable table60 = new PdfPTable(3);
                table60.setWidthPercentage(41);
                table60.setHorizontalAlignment(Element.ALIGN_RIGHT);        // (Element.ALIGN_RIGHT);
                float[] medidaCeldas60 = {1.15f, 0.22f, 0.55f};  
                table60.setWidths(medidaCeldas60);
                table60.addCell(new Paragraph("TOTAL DE PIEZAS MERMADAS", fontTitututu));
                table60.addCell(new Paragraph("     ", fontTitutu));
                table60.addCell(new Paragraph("     ", fontTitutu));
                documento.add(table60);

                documento.add(new Paragraph(" ", espacio));
                documento.add(new Paragraph(" ", espacio));

                PdfPTable table61 = new PdfPTable(3);
                table61.setWidthPercentage(41);
                table61.setHorizontalAlignment(Element.ALIGN_RIGHT);        // (Element.ALIGN_RIGHT);
                float[] medidaCeldas61 = {1.15f, 0.22f, 0.55f};
                table61.setWidths(medidaCeldas61);
                table61.addCell(new Paragraph("NUMERO DE CARTONES (NUMERO DE CAJAS)", fontTitutu));
                table61.addCell(new Paragraph("     ", fontTitutu));
                table61.addCell(new Paragraph("     ", fontTitutu));
                documento.add(table61);
//}
                // documento.add(new Paragraph(" ");
                // documento.add(new Paragraph(" ");
                // if (db.doDB(cd.doctosCartaSku(embarque, request.getParameter("r")))) {
                //     for (String[] row2 : db.getResultado()) {
                //         PdfPTable table = new PdfPTable(6);
                //       table.getDefaultCell().setBorderWidth(0f);
                
                documento.add(new Paragraph(" ", espacio));
                documento.add(new Paragraph(" ", espacio));
                documento.add(new Paragraph(" ", espacio));
                documento.add(new Paragraph(" ", espacio));
                documento.add(new Paragraph(" ", espacio));
                documento.add(new Paragraph(" ", espacio));
                documento.add(new Paragraph(" ", espacio));
               
                
                //Firmas Parametrizadas
                PdfPTable table52 = new PdfPTable(3);
                table52.getDefaultCell().setBorderWidth(0f);
                table52.setWidthPercentage(100);
                table52.setHorizontalAlignment(Element.ALIGN_CENTER);
                table52.addCell(new Paragraph("      ", espacios));
                table52.addCell(new Paragraph("      ", espacios));
                table52.addCell(new Paragraph("      ", espacios));
                table52.addCell(new Paragraph("      ", espacios));
                table52.addCell(new Paragraph("      ", espacios));
                table52.addCell(new Paragraph("      ", espacios));
                table52.addCell(new Paragraph("      ", espacios));
                table52.addCell(new Paragraph("      ", espacios));
                table52.addCell(new Paragraph("      ", espacios));
                table52.addCell(new Paragraph("      ", espacios));
                table52.addCell(new Paragraph("      ", espacios));
                documento.add(table52);
                
                //Firmas Parametrizadas
                PdfPTable luismateos = new PdfPTable(3);
                luismateos.getDefaultCell().setBorderWidth(0f);
                luismateos.setWidthPercentage(100);
                float[] medidaCeldasMateos = {1.35f, 1.20f, 1.10f};
                luismateos.setWidths(medidaCeldasMateos);
                luismateos.setHorizontalAlignment(Element.ALIGN_CENTER);
                luismateos.addCell(new Paragraph("                                 " + nombre + " " + ap + " " + am + "", espacios));
                luismateos.addCell(new Paragraph("                             " + nombre + " " + ap + " " + am + "", espacios));
                luismateos.addCell(new Paragraph("                        " + nombre + " " + ap + " " + am + "", espacios));
                documento.add(luismateos);
                
                
                Image Firmas = Image.getInstance("D:\\Servicios\\PDF\\Firma.png");
                Firmas.scalePercent(65);
                //Firmas.setAbsolutePosition(200f,100f);
                Firmas.setAlignment(1);
                documento.add(Firmas);
                      
                /*
                PdfPTable table51 = new PdfPTable(3);
                table51.getDefaultCell().setBorderWidth(0f);
                table51.setWidthPercentage(100);
                //float[] medidaCeldas51 = {1.70f, 1.70f, 1.70f};  
                //table51.setWidths(medidaCeldas51);
                table51.setHorizontalAlignment(Element.ALIGN_CENTER);
                table51.addCell(new Paragraph("______________________________________________", lineas));
                table51.addCell(new Paragraph("_________________________________________", lineas));
                table51.addCell(new Paragraph("____________________________________", lineas));
                documento.add(table51);

                PdfPTable table50 = new PdfPTable(3);
                table50.getDefaultCell().setBorderWidth(0f);
                table50.setWidthPercentage(100);    
                //float[] medidaCeldas50 = {1.50f, 1.50f, 1.50f};  
                //table50.setWidths(medidaCeldas50);
                table50.setHorizontalAlignment(Element.ALIGN_CENTER);
                table50.addCell(new Paragraph("NOMBRE Y FIRMA DE LA PERSONA QUE GENERA LA", espacioaa));
                table50.addCell(new Paragraph("NOMBRE Y FIRMA DEL GERENTE QUE REVISA", espacioaa));
                table50.addCell(new Paragraph("NOMBRE Y FIRMA DEL TRANSPORTISTA", espacioaa));
                documento.add(table50);
                */
                 documento.close();
                        File file = new File("D:\\Servicios\\PDF\\Traspaso_Merma.pdf");
                        response.sendRedirect(request.getContextPath() + "//MostrarPDFEmbarqueR");
                        //          Desktop.getDesktop().open(file);
                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                    }
               }
}
