package com.documento;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yara Sanchez
 */
import com.documento.pojos.DocumentoEmbarque;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ManifiestoPDF {

    private int[] colorRGB = new int[3];
    private BaseColor baseColorGray;
    private BaseColor baseColorBlack;
    private BaseColor baseColorWhite;
    private float borderWidth;

    public static void concatPDFs(List<InputStream> streamOfPDFFiles,
            OutputStream outputStream, boolean paginate) {

        Document document = new Document();
        try {
            List<InputStream> pdfs = streamOfPDFFiles;
            List<PdfReader> readers = new ArrayList<PdfReader>();
            int totalPages = 0;
            Iterator<InputStream> iteratorPDFs = pdfs.iterator();

            while (iteratorPDFs.hasNext()) {
                InputStream pdf = iteratorPDFs.next();
                PdfReader pdfReader = new PdfReader(pdf);
                readers.add(pdfReader);
                totalPages += pdfReader.getNumberOfPages();
            }

            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();
            PdfContentByte cb = writer.getDirectContent();
            

            PdfImportedPage page;
            int currentPageNumber = 0;
            int pageOfCurrentReaderPDF = 0;
            Iterator<PdfReader> iteratorPDFReader = readers.iterator();

            while (iteratorPDFReader.hasNext()) {
                PdfReader pdfReader = iteratorPDFReader.next();

                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {

                    Rectangle rectangle = pdfReader.getPageSizeWithRotation(1);
                    document.setPageSize(rectangle);
                    document.newPage();

                    pageOfCurrentReaderPDF++;
                    currentPageNumber++;
                    page = writer.getImportedPage(pdfReader,
                            pageOfCurrentReaderPDF);
                    switch (rectangle.getRotation()) {
                        case 0:
                            cb.addTemplate(page, 1f, 0, 0, 1f, 0, 0);
                            break;
                        case 90:
                            cb.addTemplate(page, 0, -1f, 1f, 0, 0, pdfReader
                                    .getPageSizeWithRotation(1).getHeight());
                            break;
                        case 180:
                            cb.addTemplate(page, -1f, 0, 0, -1f, 0, 0);
                            break;
                        case 270:
                            cb.addTemplate(page, 0, 1.0F, -1.0F, 0, pdfReader
                                    .getPageSizeWithRotation(1).getWidth(), 0);
                            break;
                        default:
                            break;
                    }
                    if (paginate) {
                        cb.beginText();
                        cb.getPdfDocument().getPageSize();
                        cb.endText();
                    }
                }
                pageOfCurrentReaderPDF = 0;
            }
            outputStream.flush();
            document.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen()) {
                document.close();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    private PdfPCell getCellDecorate(String Texto, Font font, int colSpan, int rowSpan, int horizontalAlignment, int VerticalAlignment,
            BaseColor backgroundColor, float padding, float borderWidthLeft, BaseColor borderColorLeft, float borderWidthRight,
            BaseColor borderColorRight, float borderWidthTop, BaseColor borderColorTop, float borderWidthBottom, BaseColor borderColorBottom) {
        PdfPCell cell = new PdfPCell(new Paragraph(new Chunk(Texto, font)));
        cell.setColspan(colSpan);
        cell.setRowspan(rowSpan);
        cell.setHorizontalAlignment(horizontalAlignment);
        cell.setVerticalAlignment(VerticalAlignment);
        cell.setBackgroundColor(backgroundColor);
        cell.setPadding(padding);
        cell.setBorder(0);
        cell.setBorderWidthLeft(borderWidthLeft);
        cell.setBorderColorLeft(borderColorLeft);
        cell.setBorderWidthRight(borderWidthRight);
        cell.setBorderColorRight(borderColorRight);
        cell.setBorderWidthTop(borderWidthTop);
        cell.setBorderColorTop(borderColorTop);
        cell.setBorderWidthBottom(borderWidthBottom);
        cell.setBorderColorBottom(borderColorBottom);
        return cell;
    }

    public ManifiestoPDF(String ruta, DocumentoEmbarque documentoEmbarque,String cliente)
            throws Exception {
        try {
            this.colorRGB[0] = 255;
            this.colorRGB[1] = 255;
            this.colorRGB[2] = 255;
            PdfPCell cell;
            this.baseColorGray = new BaseColor(192, 192, 192);
            this.baseColorBlack = new BaseColor(0, 0, 0);
            this.baseColorWhite = new BaseColor(255, 255, 255);
            this.borderWidth = 0.1F;
            //File file = new File(rutaAbosolutaSalidaArchivo);

            Document document = new Document(PageSize.LETTER.rotate(), -50.0F, -50.0F, 2.0F, 2.0F);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(ruta));
            document.open();

            
            
            PdfPTable table = new PdfPTable(33);
            
            //Fila 1
            table.addCell(getCellDecorate(
                    " ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    33,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila 2 - 3
            table.addCell(getCellDecorate(
                    " ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    2,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo

            String path = ManifiestoPDF.class.getProtectionDomain().getCodeSource().getLocation().getPath();//Se obtiene la RUTA
            path = path.substring(1, path.indexOf("WEB-INF"));
            path = path.replaceAll("%20", " ");//Se reemplaza para convertir una url a una ruta de filesystem
            path = path + "/imgClients/logoDocumento"+cliente+".gif";
            Image imageLogo = Image.getInstance(path);
            cell = new PdfPCell(imageLogo);
            cell.setColspan(3);
            cell.setRowspan(2);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(0.0F);
            cell.setBorder(0);
            table.addCell(cell);
            /*
            table.addCell(getCellDecorate(
              " LOGO ",//Texto
              new Font(Font.FontFamily.TIMES_ROMAN, 9.0F, 1, this.baseColorBlack),
              3,//Numro de Columnas  
              2,//Numro de filas  
              1,//Alineacion Horizontal
              5,//Alineacion Vertical
              this.baseColorWhite,//Color de Fondo
              1.0F,//no lo consideres
              this.borderWidth,//borde izquierdo
              this.baseColorWhite,//Color borde izuierdo
              this.borderWidth,//borde derecho
              this.baseColorWhite,//Color borde derecho
              this.borderWidth,//borde arriba
              this.baseColorWhite,//color borde arriba
              this.borderWidth,//borde abajo
              this.baseColorWhite));//color borde abajo
             */
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    7,//Numro de Columnas  
                    2,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    " GUÍA DE EMBARQUE/CARTA PORTE ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 1, this.baseColorBlack),
                    11,//Numro de Columnas  <
                    2,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    11,//Numro de Columnas  
                    2,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //fILA 4
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    33,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
             //Codigo de Barras
            PdfContentByte cb = writer.getDirectContent();
            Barcode128 code = new Barcode128(); 
                                code.setCodeType(Barcode128.EAN13);
                                code.setCode(documentoEmbarque.getEncabezado().getGuiaEmbarque());   
                                code.setBarHeight(15f);
                                code.setX(2f);                                
                                Image codeImage = code.createImageWithBarcode(cb, BaseColor.BLACK, BaseColor.WHITE);                                
                                codeImage.setAlignment(Element.ALIGN_CENTER);
            cell = new PdfPCell(codeImage);
            cell.setColspan(33);
            cell.setRowspan(1);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(1);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(0.0F);
            cell.setBorder(0);
            table.addCell(cell);                    
                                
            //fILA 5
            table.addCell(getCellDecorate(
                    " RE - IMPRESION RE - IMPRESION RE - IMPRESION RE - IMPRESION RE - IMPRESION RE - IMPRESION RE - IMPRESION ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    33,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo

            //FILA 6
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "Guia de Embarque",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    3,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            /*
      table.addCell(getCellDecorate(
              "  ",//Texto
              new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
              1,//Numro de Columnas  
              1,//Numro de filas  
              1,//Alineacion Horizontal
              5,//Alineacion Vertical
              this.baseColorWhite,//Color de Fondo
              1.0F,//no lo consideres
              this.borderWidth,//borde izquierdo
              this.baseColorWhite,//Color borde izuierdo
              this.borderWidth,//borde derecho
              this.baseColorWhite,//Color borde derecho
              this.borderWidth,//borde arriba
              this.baseColorWhite,//color borde arriba
              this.borderWidth,//borde abajo
              this.baseColorWhite));//color borde abajo
             */
            table.addCell(getCellDecorate(
                    documentoEmbarque.getEncabezado().getGuiaEmbarque(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    3,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "Fecha: " + documentoEmbarque.getEncabezado().getFecha(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    3,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    23,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //fila7
            /*
            PdfContentByte cb = writer.getDirectContent(); 
                                Barcode128 code = new Barcode128(); 
                                code.setCodeType(Barcode128.EAN13);
                                code.setCode(documentoEmbarque.getEncabezado().getGuiaEmbarque());   
                                code.setBarHeight(15f);
                                code.setX(2f);                                
                                Image codeImage = code.createImageWithBarcode(cb, BaseColor.BLACK, Color.WHITE);                                
                                codeImage.setAlignment(Element.ALIGN_CENTER);
            */
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    33,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //fila 8
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo

            table.addCell(getCellDecorate(
                    " Datos del Cargador ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    2,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    documentoEmbarque.getEncabezado().getEmpresa(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    14,//Numro de Columnas  
                    2,//Numro de filas  
                    Element.ALIGN_LEFT,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    2,//Numro de Columnas  
                    2,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "Datos del Porteador",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    3,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    2,//Numro de Columnas  
                    2,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "Linea de Transportista: " + documentoEmbarque.getEncabezado().getLineaTransportista(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    7,//Numro de Columnas  
                    1,//Numro de filas  
                    Element.ALIGN_LEFT,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //fila 9
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    4,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    3,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    " Direccion: " + documentoEmbarque.getEncabezado().getDireccion(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    7,//Numro de Columnas  
                    1,//Numro de filas  
                    Element.ALIGN_LEFT,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //FILA 10
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    25,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    " Tipo de Unidad: " + documentoEmbarque.getEncabezado().getTipoUnidad(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    7,//Numro de Columnas  
                    1,//Numro de filas  
                    Element.ALIGN_LEFT,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila 11
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    25,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "Placas: " + documentoEmbarque.getEncabezado().getPlacas(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    7,//Numro de Columnas  
                    1,//Numro de filas  
                    Element.ALIGN_LEFT,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila 12
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    25,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "Modelo: " + documentoEmbarque.getEncabezado().getModelo(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    7,//Numro de Columnas  
                    1,//Numro de filas  
                    Element.ALIGN_LEFT,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila 13
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    25,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "Color: " + documentoEmbarque.getEncabezado().getColor(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    7,//Numro de Columnas  
                    1,//Numro de filas  
                    Element.ALIGN_LEFT,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila 14
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    33,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila 15
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    20,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "Chofer Asignado: ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    3,//Numro de Columnas  
                    1,//Numro de filas  
                    Element.ALIGN_LEFT,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    2,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    documentoEmbarque.getEncabezado().getChofer(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    3,//Numro de Columnas  
                    1,//Numro de filas  
                    Element.ALIGN_LEFT,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila 16
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    33,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila17
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    33,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila 18
            /*
      table.addCell(getCellDecorate(
              "  ",//Texto
              new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
              33,//Numro de Columnas  
              1,//Numro de filas  
              1,//Alineacion Horizontal
              5,//Alineacion Vertical
              this.baseColorWhite,//Color de Fondo
              1.0F,//no lo consideres
              this.borderWidth,//borde izquierdo
              this.baseColorWhite,//Color borde izuierdo
              this.borderWidth,//borde derecho
              this.baseColorWhite,//Color borde derecho
              this.borderWidth,//borde arriba
              this.baseColorWhite,//color borde arriba
              this.borderWidth,//borde abajo
              this.baseColorWhite));//color borde abajo
             */
            //Fila 19
            /*
      table.addCell(getCellDecorate(
              "  ",//Texto
              new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
              33,//Numro de Columnas  
              1,//Numro de filas  
              1,//Alineacion Horizontal
              5,//Alineacion Vertical
              this.baseColorWhite,//Color de Fondo
              1.0F,//no lo consideres
              this.borderWidth,//borde izquierdo
              this.baseColorWhite,//Color borde izuierdo
              this.borderWidth,//borde derecho
              this.baseColorWhite,//Color borde derecho
              this.borderWidth,//borde arriba
              this.baseColorWhite,//color borde arriba
              this.borderWidth,//borde abajo
              this.baseColorWhite));//color borde abajo
             */
            //Fila 20
            /*
      table.addCell(getCellDecorate(
              "  ",//Texto
              new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
              33,//Numro de Columnas  
              1,//Numro de filas  
              1,//Alineacion Horizontal
              5,//Alineacion Vertical
              this.baseColorWhite,//Color de Fondo
              1.0F,//no lo consideres
              this.borderWidth,//borde izquierdo
              this.baseColorWhite,//Color borde izuierdo
              this.borderWidth,//borde derecho
              this.baseColorWhite,//Color borde derecho
              this.borderWidth,//borde arriba
              this.baseColorWhite,//color borde arriba
              this.borderWidth,//borde abajo
              this.baseColorWhite));//color borde abajo
             */
            //Fila 21
            /*
      table.addCell(getCellDecorate(
              "  ",//Texto
              new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
              33,//Numro de Columnas  
              1,//Numro de filas  
              1,//Alineacion Horizontal
              5,//Alineacion Vertical
              this.baseColorWhite,//Color de Fondo
              1.0F,//no lo consideres
              this.borderWidth,//borde izquierdo
              this.baseColorWhite,//Color borde izuierdo
              this.borderWidth,//borde derecho
              this.baseColorWhite,//Color borde derecho
              this.borderWidth,//borde arriba
              this.baseColorWhite,//color borde arriba
              this.borderWidth,//borde abajo
              this.baseColorWhite));//color borde abajo
             */
            //Fila 22
            /*
      table.addCell(getCellDecorate(
              "  ",//Texto
              new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
              33,//Numro de Columnas  
              1,//Numro de filas  
              1,//Alineacion Horizontal
              5,//Alineacion Vertical
              this.baseColorWhite,//Color de Fondo
              1.0F,//no lo consideres
              this.borderWidth,//borde izquierdo
              this.baseColorWhite,//Color borde izuierdo
              this.borderWidth,//borde derecho
              this.baseColorWhite,//Color borde derecho
              this.borderWidth,//borde arriba
              this.baseColorWhite,//color borde arriba
              this.borderWidth,//borde abajo
              this.baseColorWhite));//color borde abajo
             */
            //fila 23
            /*
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
                         */
            
            table.addCell(getCellDecorate(
                    "Documento",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    2,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Destino",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    4,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Ciudad",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    2,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Estado",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    2,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Pzs",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Cjs",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Pal",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Col",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Con",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Ata",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Bul",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Vol",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Kgs",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Factura",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    2,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Recibido",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    2,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Observaciones",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    3,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Cuenta",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    5,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorGray,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //FILA 24
            HashMap hmDocumentos = new HashMap();
            HashMap hmDestino = new HashMap();
            double pzas = 0.0, cjas = 0.0, pal = 0.0, col = 0.0, con = 0.0, ats = 0.0, bul = 0.0, vol = 0.0, kgs = 0.0;
            int numeroLineas=50;
            if(documentoEmbarque.getDetalleDocumento().size()<=numeroLineas){
                numeroLineas = numeroLineas-documentoEmbarque.getDetalleDocumento().size();
            } else{
                numeroLineas = documentoEmbarque.getDetalleDocumento().size();
            }
            for (int i = 0; i < documentoEmbarque.getDetalleDocumento().size(); i++) {
                hmDocumentos.put(documentoEmbarque.getDetalleDocumento().get(i).getDocumento(), documentoEmbarque.getDetalleDocumento().get(i).getDocumento());
                hmDestino.put(documentoEmbarque.getDetalleDocumento().get(i).getDestino(), documentoEmbarque.getDetalleDocumento().get(i).getDestino());
                pzas += Double.parseDouble(documentoEmbarque.getDetalleDocumento().get(i).getPiezas());
                cjas += Double.parseDouble(documentoEmbarque.getDetalleDocumento().get(i).getCajas());
                pal += Double.parseDouble(documentoEmbarque.getDetalleDocumento().get(i).getPalets());
                col += Double.parseDouble(documentoEmbarque.getDetalleDocumento().get(i).getCol());
                con += Double.parseDouble(documentoEmbarque.getDetalleDocumento().get(i).getCon());
                ats += Double.parseDouble(documentoEmbarque.getDetalleDocumento().get(i).getAtados());
                bul += Double.parseDouble(documentoEmbarque.getDetalleDocumento().get(i).getBultos());
                vol += Double.parseDouble(documentoEmbarque.getDetalleDocumento().get(i).getVolumen());
                kgs += Double.parseDouble(documentoEmbarque.getDetalleDocumento().get(i).getKilos());
                table.addCell(getCellDecorate(
                        "  ",//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        1,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorWhite,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorWhite,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorWhite,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorWhite));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getDocumento(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        2,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getDestino(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        4,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getCiudad(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        2,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getEstado(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        2,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getPiezas(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        1,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getCajas(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        1,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getPalets(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        1,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getCol(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        1,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getCon(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        1,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getAtados(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        1,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getBultos(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        1,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getVolumen(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        1,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getKilos(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        1,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getFactura(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        2,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getRecibido(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        2,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getObservaciones(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        3,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        documentoEmbarque.getDetalleDocumento().get(i).getCuenta(),//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        5,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
                table.addCell(getCellDecorate(
                        "  ",//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        1,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorWhite,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorWhite,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorWhite,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorWhite));//color borde abajo
            }
            //Inmediatamente Despues de la fila se imprimen resultaos
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "Documentos: " + hmDocumentos.size(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    2,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "Destinos: " + hmDestino.size(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    4,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
 
            table.addCell(getCellDecorate(
                    " ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    2,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    " ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    2,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    String.valueOf(pzas),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    String.valueOf(cjas),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    String.valueOf(pal),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    String.valueOf(col),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    String.valueOf(con),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    String.valueOf(ats),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    String.valueOf(bul),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    String.valueOf(vol),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    String.valueOf(kgs),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    " ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    2,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    " ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    2,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    " ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    3,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    " ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    5,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            
            /*
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //
            */
            for (int i = 0; i <= numeroLineas; i++) {
                table.addCell(getCellDecorate(
                        "  ",//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        6,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorWhite,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorWhite,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorWhite,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorWhite));//color borde abajo
            }
            //fila26
          
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    33,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila 27
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    33,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila 28
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            
            
            table.addCell(getCellDecorate(
                    "Quien Genero el Control Vehicular",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    25,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    " ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    6,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila 29
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    4,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    " ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    19,//Numro de Columnas  
                    4,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    6,//Numro de Columnas  
                    4,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    6,//Numro de Columnas  
                    4,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    4,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Final Fila
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    4,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    documentoEmbarque.getPiePagina().getNombreFirma(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    19,//Numro de Columnas  
                    4,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    documentoEmbarque.getPiePagina().getPuesto(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    6,//Numro de Columnas  
                    4,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    6,//Numro de Columnas  
                    4,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    4,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Separacion de Fila
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    4,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "Firma",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    19,//Numro de Columnas  
                    4,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Puesto",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    6,//Numro de Columnas  
                    4,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Fecha y Salida",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    6,//Numro de Columnas  
                    4,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    4,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila 30
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "Linea de Transporte  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    6,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    documentoEmbarque.getPiePagina().getLineaTransporte(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    13,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Descripcion de la Unidad",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    6,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Mod:" + documentoEmbarque.getEncabezado().getModelo() + ", Col: " + documentoEmbarque.getEncabezado().getColor() + ", Pla: " + documentoEmbarque.getEncabezado().getPlacas() + ", Tip: " + documentoEmbarque.getEncabezado().getTipoUnidad(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    6,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila31
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "Custodia",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    6,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            if (documentoEmbarque.getPiePagina().getCustodia() == 0) {
                table.addCell(getCellDecorate(
                        "Si",//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        7,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
            } else {
                table.addCell(getCellDecorate(
                        "Si",//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        7,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorGray,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
            }
            if (documentoEmbarque.getPiePagina().getCustodia() == 0) {
                table.addCell(getCellDecorate(
                        "No",//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        6,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorGray,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
            } else {
                table.addCell(getCellDecorate(
                        "No",//Texto
                        new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                        6,//Numro de Columnas  
                        1,//Numro de filas  
                        1,//Alineacion Horizontal
                        5,//Alineacion Vertical
                        this.baseColorWhite,//Color de Fondo
                        1.0F,//no lo consideres
                        this.borderWidth,//borde izquierdo
                        this.baseColorBlack,//Color borde izuierdo
                        this.borderWidth,//borde derecho
                        this.baseColorBlack,//Color borde derecho
                        this.borderWidth,//borde arriba
                        this.baseColorBlack,//color borde arriba
                        this.borderWidth,//borde abajo
                        this.baseColorBlack));//color borde abajo
            }
            table.addCell(getCellDecorate(
                    "Observaciones: " + documentoEmbarque.getPiePagina().getObservaciones(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    12,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //fila32
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "Autorizacion de Salida " + documentoEmbarque.getPiePagina().getAutorizaSalidaNombre(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    19,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "Recibi",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    12,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo

            //Fila 33
            //Fila 1
            
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    " ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    19,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    " ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    12,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
                
            //Fila2 
           
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    " ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    19,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    documentoEmbarque.getEncabezado().getChofer(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    12,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorBlack,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Otra fila
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    19,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    " ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    12,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila 3
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    " Nombre y Firma ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    19,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "Firma de Operador",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    12,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila mas
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            table.addCell(getCellDecorate(
                    "",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    19,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    12,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorBlack,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorBlack,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorBlack));//color borde abajo
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    6,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
           
                      
//FILA 40
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    33,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
          
            //FILA 41
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    1,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
          
            table.addCell(getCellDecorate(
                    documentoEmbarque.getPiePagina().getLeyendaLinea1(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    32,//Numro de Columnas  
                    1,//Numro de filas  
                    Element.ALIGN_LEFT,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            
            //FILA 42
           
            table.addCell(getCellDecorate(
                    "  ",//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    33,//Numro de Columnas  
                    1,//Numro de filas  
                    1,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
            //Fila 43
            //
           
            table.addCell(getCellDecorate(
                    documentoEmbarque.getPiePagina().getLeyendaLinea2(),//Texto
                    new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 1, this.baseColorBlack),
                    33,//Numro de Columnas  
                    1,//Numro de filas  
                    Element.ALIGN_LEFT,//Alineacion Horizontal
                    5,//Alineacion Vertical
                    this.baseColorWhite,//Color de Fondo
                    1.0F,//no lo consideres
                    this.borderWidth,//borde izquierdo
                    this.baseColorWhite,//Color borde izuierdo
                    this.borderWidth,//borde derecho
                    this.baseColorWhite,//Color borde derecho
                    this.borderWidth,//borde arriba
                    this.baseColorWhite,//color borde arriba
                    this.borderWidth,//borde abajo
                    this.baseColorWhite));//color borde abajo
                  
            document.add(table);
            document.close();
            
        } catch (Exception ex) {
            throw ex;
        }
    }

}
