/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.facturacion;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.onest.train.consultas.ConsultasQuery;
import com.transactions.mailsender.Dao.DataBaseConnect;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis_
 */
public class GeneratePDFReciboDePago {

    // Fonts definitions (Definición de fuentes).
    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private static final Font fontTitus = FontFactory.getFont("Calibri", 12, Font.BOLD, BaseColor.BLACK);
    private static final Font DatosCliente = FontFactory.getFont("Calibri", 10, Font.BOLD, BaseColor.BLACK);
    private static final Font DatosCliente_1 = FontFactory.getFont("Calibri", 7, Font.BOLD, BaseColor.BLACK);
    private static final Font DatosCliente_2 = FontFactory.getFont("Calibri", 9, Font.NORMAL, BaseColor.BLACK);
    private static final Font DatosCliente_2_2 = FontFactory.getFont("Calibri", 9, Font.BOLD, BaseColor.BLACK);
    private static final Font DatosCliente_3 = FontFactory.getFont("Calibri", 8, Font.BOLD, BaseColor.BLACK);
    private static final Font espaciao = FontFactory.getFont("Times New Roman", 7, Font.BOLD, BaseColor.BLACK);
    private static final Font espaciox = FontFactory.getFont("Calibri", 10, Font.NORMAL, BaseColor.BLACK);
    private static final Font espacios = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
    private static final Font fontTitutu = FontFactory.getFont("arial", 5, Font.BOLD, BaseColor.BLACK);
    private static final Font fontTitututu = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
    private static final Font para = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.RED);
    private static final Font tiends = FontFactory.getFont("arial", 11, Font.NORMAL, BaseColor.BLACK);
    private static final Font liga4ever = FontFactory.getFont("Calibri", 10, Font.NORMAL, BaseColor.BLACK);
    private static final Font White = FontFactory.getFont("Calibri", 11, Font.BOLD, BaseColor.WHITE);
    private static final Font White2 = FontFactory.getFont("Calibri", 8, Font.BOLD, BaseColor.WHITE);
    private static final Font White3 = FontFactory.getFont("Calibri", 9, Font.BOLD, BaseColor.WHITE);
    private static final Font Total = FontFactory.getFont("Calibri", 8, Font.BOLD, BaseColor.WHITE);

    private static final Font espacio = FontFactory.getFont("Times New Roman", 7, Font.BOLD, BaseColor.BLACK);
    private static final Font conceptos = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
    private static final Font th = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.WHITE);
    private static final Font detalle = FontFactory.getFont("arial", 8, Font.NORMAL, BaseColor.BLACK);
    private static final Font encabezado1 = FontFactory.getFont("arial", 13, Font.BOLD, BaseColor.BLACK);
    private static final Font encabezado2 = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
    private static final Font encabezado3 = FontFactory.getFont("arial", 6, Font.NORMAL, BaseColor.BLACK);
    private static final Font encabezado4 = FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK);

    /**
     * We create a PDF document with iText using different elements to learn to
     * use this library. Creamos un documento PDF con iText usando diferentes
     * elementos para aprender a usar esta librería.
     *
     * @param pdfNewFile  <code>String</code> pdf File we are going to write.
     * Fichero pdf en el que vamos a escribir.
     */
    public void createPDFReciboDePago(File pdfNewFile, String id) throws SQLException {

        //Extracción de Fecha:
        Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formato.format(date);
        String[] par = fecha.split("/");
        String part1 = par[0];
        String part2 = par[1];
        String part3 = par[2];

        //Parametros: Lugar de Embarque
        String numcp = "";
        String lugarDeEmbarque = "";
        String lugEmb_nombre = "";
        String lugEmb_rfc = "";
        String lugEmb_calle = "";
        String lugEmb_noInterior = "";
        String lugEmb_noExterior = "";
        String lugEmb_codigoPostal = "";
        String lugEmb_estado = "";
        String lugEmb_municipio = "";
        String lugEmb_colonia = "";
        String lugEmb_referencia = "";
        String lugEmb_fechaHoraSalidaLlegada = "";
        String lugEmb_localidad = "";
        String lugEmb_idUbicacion = "";

        //Parametros: Lugar de Destino
        String check_lugarDestinatario = "";
        String lugarDestinatario = "";
        String dest_nombre = "";
        String dest_rfc = "";
        String dest_calle = "";
        String dest_noInterior = "";
        String dest_noExterior = "";
        String dest_codigoPostal = "";
        String dest_estado = "";
        String dest_municipio = "";
        String dest_colonia = "";
        String dest_referencia = "";
        String dest_fechaHoraSalidaLlegada = "";
        String dest_distancia = "";
        String dest_localidad = "";
        String dest_idUbicacion = "";

        //Parametros: Lugar de Entrega
        String check_lugarEntrega = "";
        String lugarEntrega = "";
        String lugEnt_contacto = "";
        String lugEnt_nombre = "";
        String lugEnt_rfc = "";
        String lugEnt_calle = "";
        String lugEnt_noInterior = "";
        String lugEnt_noExterior = "";
        String lugEnt_codigoPostal = "";
        String lugEnt_estado = "";
        String lugEnt_municipio = "";
        String lugEnt_colonia = "";
        String lugEnt_referencia = "";
        String lugEnt_pais = "";
        String lugEnt_tipoTransporte = "";
        String lugEnt_fechaHoraSalidaLlegada = "";
        String lugEnt_distancia = "";
        String lugEnt_localidad = "";
        String lugEnt_idUbicacion = "";

        //Parametros: Mercancía
        String unidad_peso_cp = "";
        String mercancia_es_peligroso_cp = "";
        String numpedimento_cp = "";
        String moneda_cp = "";
        String rel_clvsat_id_cp = "";
        String unidad_sat_id_cp = "";
        String unidad_sat_desc_cp = "";
        String concepto_id_cp = "";
        String concepto_desc_cp = "";
        String cantidad_cp = "";
        String precio_unitario_cp = "";
        String importe_descuento_cp = "";
        String importe_cp = "";
        String unidadMedidaValor = "";	
        
        //Parametros: Autotransporte
        String perm_sct = "";
        String no_permismo_sct = "";
        String config_vehicular = "";
        String placa_vm = "";
        String anio_modelovm = "";
        String placa_remolque = "";
        String subtipo_remolque = "";
        String aseguradora_civil = "";
        String poliza_civil = "";
        String tipo_figura = "";
        String rfc_figura = "";
        String num_licencia = "";
        String nombre_figura = "";
        
        int contadorMercancias = 0;

        
        // We create the document and set the file name.        
        // Creamos el documento e indicamos el nombre del fichero.
        try {
            Document documento = new Document(PageSize.LETTER, 25, 25, 25, 25); // (PageSize.LETTER, 20, 20, 20, 20);
            try {

                PdfWriter.getInstance(documento, new FileOutputStream(pdfNewFile));

            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("No se encontró ningún archivo de este tipo para generar el PDF"
                        + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
            }

            documento.open();
            
            DataBaseConnect dbd =new DataBaseConnect();
            Connection con = dbd.conectar();
            ConsultasQuery fac = new ConsultasQuery();
            
            
            //Parametros: Cliente
            String cve = "10";
            String cliente_descripcion0 = "";
            String rfc0 = "";
            String alias_reg = "";
            String direccion0 = "";
            String codigo_postal = "";
            
            
            //Parametros: Sus-Cliente
            String cliente_descripcion = "";
            String rfc = "";
            String alias_uso = "";
            String direccion = "";
            String rel_cartaporte_id = "";
            String com_uuid = "";
            String com_nocertificadosat = "";
            String com_nocertificadoemisor= "";
            String fecha_emision = ""; 
            String com_rfcprovcertif = "";
            String com_fechatimbrado = "";
            
            
            //Consulta: Cliente
            CallableStatement c0 = con.prepareCall(fac.consultaCliente(cve));
            ResultSet rs0 = c0.executeQuery();
            
            while(rs0.next()){
                cliente_descripcion0 = rs0.getString(1);
                rfc0 = rs0.getString(2);
                alias_reg = rs0.getString(3);
                direccion0 = rs0.getString(4);
                codigo_postal = rs0.getString(5);
            }
            
            
            //Consulta: Sus-Cliente
            CallableStatement c1 = con.prepareCall(fac.consultarSusCliente(id, cve));
            ResultSet rs1 = c1.executeQuery();
            
            while(rs1.next()){
                cliente_descripcion = rs1.getString(1);
                rfc = rs1.getString(2);
                alias_uso = rs1.getString(3);
                direccion = rs1.getString(4);
                rel_cartaporte_id = rs1.getString(5);
                com_uuid = rs1.getString(6);
                com_nocertificadosat = rs1.getString(7);
                com_nocertificadoemisor = rs1.getString(8);
                fecha_emision = rs1.getString(9);
                com_rfcprovcertif = rs1.getString(10);
                com_fechatimbrado = rs1.getString(11);
            }
            
            Image logo;
            try {
                logo = Image.getInstance("D:\\Servicios\\Facturacion\\Tools\\logo-tacts.png");
                logo.scalePercent(100);
                //logo.setAlignment(2);
                logo.setAbsolutePosition(20f, 620f);
                documento.add(logo);
            } catch (BadElementException ex) {
                Logger.getLogger(GeneratePDFCartaPorte.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GeneratePDFCartaPorte.class.getName()).log(Level.SEVERE, null, ex);
            }

            Font espacio = FontFactory.getFont("Times New Roman", 7, Font.BOLD, BaseColor.BLACK);
            Font conceptos = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
            Font th = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.WHITE);
            Font detalle = FontFactory.getFont("arial", 8, Font.NORMAL, BaseColor.BLACK);
            Font encabezado1 = FontFactory.getFont("arial", 13, Font.BOLD, BaseColor.BLACK);
            Font encabezado2 = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
            Font encabezado3 = FontFactory.getFont("arial", 6, Font.NORMAL, BaseColor.BLACK);
            Font encabezado4 = FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setHorizontalAlignment(Element.ALIGN_RIGHT);
            float[] sizeT = {0.70f, 1.70f, 1.50f};
            table.setWidths(sizeT);

            PdfPCell cell = new PdfPCell(new Paragraph(""));
            cell.setColspan(11);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(""));
            cell.setRowspan(15);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            //table.addCell(im2);

            cell = new PdfPCell(new Paragraph("LINEA DE TRANSPORTE SA DE CV", encabezado1));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph a = new Paragraph("Carta Porte Ingreso 618", encabezado1);
            a.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(a);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("TRA170411AX7", encabezado4));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph b = new Paragraph("FOLIO FISCAL (UUID)", encabezado2);
            b.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(b);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("RÉGIMEN FISCAL: 601 - General de Ley de Personas Morales", encabezado3));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph c = new Paragraph("1AF12884-62CD-4420-8D21-6D064493AB13", encabezado4);
            c.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(c);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("ISLA DEL SOCORRO, MANZANA 39/LOTE 16, Villa Esmeralda, 54910, Tultitlpan de Mariano Escobedo, Tultitlán, Estado de México, México", encabezado3));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph d = new Paragraph("NO. DE SERIE DEL CERTIFICADO DEL SAT", encabezado2);
            d.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(d);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("CLIENTE", encabezado1));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph e = new Paragraph("00001000000413073350", encabezado4);
            e.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(e);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("VF OUTDOOR MEXICO, S. DE R.L DE C.V", encabezado4));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph f = new Paragraph("NO. DE SERIE DEL CERTIFICADO DEL EMISOR", encabezado2);
            f.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("VLM970313NW5", encabezado4));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph g = new Paragraph("00001000000507313439", encabezado4);
            g.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(g);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("USO CFDI: G03 - Gastos en General", encabezado3));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph h = new Paragraph("FECHA Y Y HORA DE CERTIFICACIÓN", encabezado2);
            h.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(h);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("MARIANO ESCOBEDO, 476/PISO 9 L.14, Anzures, 11590, Ciudad de México, Miguel Hidalgo, Ciudad de México, México", encabezado3));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph i = new Paragraph("2022-02-02T20:12:31", encabezado4);
            i.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(i);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("", espacio));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph j = new Paragraph("RFC PROVEEDOR DE CERTIFICACIÓN", encabezado2);
            j.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(j);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("", espacio));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph k = new Paragraph("SVT110323827", encabezado4);
            k.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(k);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("", espacio));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph l = new Paragraph("FECHA Y HORA DE EMISIÓN DE CFDI", encabezado2);
            l.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(l);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("", encabezado3));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph m = new Paragraph("2022-02-02T20:01:43", encabezado4);
            m.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(m);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("", espacio));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph n = new Paragraph("LUGAR DE EXPEDICIÓN", encabezado2);
            n.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(n);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("", espacio));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph p = new Paragraph("54910", encabezado4);
            p.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(p);
            table.addCell(cell);

            documento.add(table);

            PdfPTable cartaporte = new PdfPTable(1);
            cartaporte.setWidthPercentage(100);
            PdfPCell titulo_1 = new PdfPCell(new Paragraph("CARTA PORTE 4.0", th));
            titulo_1.setColspan(1);
            titulo_1.setBorder(Rectangle.NO_BORDER);
            titulo_1.setBackgroundColor(BaseColor.BLACK);
            titulo_1.setHorizontalAlignment(1);
            cartaporte.addCell(titulo_1);
            documento.add(cartaporte);

            PdfPTable detalle1_1 = new PdfPTable(4);
            detalle1_1.setWidthPercentage(100);
            float[] size0 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle1_1.setWidths(size0);
            detalle1_1.addCell(new Paragraph("Transporte Internacional", conceptos));
            detalle1_1.addCell(new Paragraph("No", detalle));
            detalle1_1.addCell(new Paragraph("Total Distancia Recorrída", conceptos));
            detalle1_1.addCell(new Paragraph("0.00", detalle));
            documento.add(detalle1_1);

            PdfPTable space1 = new PdfPTable(1);
            space1.setWidthPercentage(100);
            PdfPCell white_1 = new PdfPCell(new Paragraph("", th));
            white_1.setColspan(1);
            white_1.setBorder(Rectangle.NO_BORDER);
            white_1.setBackgroundColor(BaseColor.WHITE);
            white_1.setHorizontalAlignment(1);
            space1.addCell(white_1);
            documento.add(space1);

            PdfPTable ubicacion1 = new PdfPTable(1);
            ubicacion1.setWidthPercentage(100);
            PdfPCell titulo_2 = new PdfPCell(new Paragraph("UBICACION 1", th));
            titulo_2.setColspan(1);
            titulo_2.setBorder(Rectangle.NO_BORDER);
            titulo_2.setBackgroundColor(BaseColor.BLACK);
            titulo_2.setHorizontalAlignment(1);
            ubicacion1.addCell(titulo_2);
            documento.add(ubicacion1);

            PdfPTable detalle2_1 = new PdfPTable(4);
            detalle2_1.setWidthPercentage(100);
            float[] size1 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle2_1.setWidths(size1);
            detalle2_1.addCell(new Paragraph("Tipo de Ubicación", conceptos));
            detalle2_1.addCell(new Paragraph("Origen", detalle));
            detalle2_1.addCell(new Paragraph("RFC Remitente o Destinatario", conceptos));
            detalle2_1.addCell(new Paragraph("VLM970313NW5", detalle));
            documento.add(detalle2_1);

            PdfPTable detalle2_2 = new PdfPTable(4);
            detalle2_2.setWidthPercentage(100);
            float[] size2 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle2_2.setWidths(size2);
            detalle2_2.addCell(new Paragraph("Nombre de Remitente o Destinatario", conceptos));
            detalle2_2.addCell(new Paragraph(lugEmb_nombre, detalle));
            detalle2_2.addCell(new Paragraph("Fecha Hora Salida Llegada", conceptos));
            detalle2_2.addCell(new Paragraph("2022-02-16T00:00:00", detalle));
            documento.add(detalle2_2);

            PdfPTable space2 = new PdfPTable(1);
            space2.setWidthPercentage(100);
            PdfPCell white_2 = new PdfPCell(new Paragraph("", th));
            white_2.setColspan(1);
            white_2.setBorder(Rectangle.NO_BORDER);
            white_2.setBackgroundColor(BaseColor.WHITE);
            white_2.setHorizontalAlignment(1);
            space2.addCell(white_2);
            documento.add(space2);

            PdfPTable domicilio = new PdfPTable(1);
            domicilio.setWidthPercentage(100);
            PdfPCell titulo_3 = new PdfPCell(new Paragraph("DOMICILIO", th));
            titulo_3.setColspan(1);
            titulo_3.setBorder(Rectangle.NO_BORDER);
            titulo_3.setBackgroundColor(BaseColor.BLACK);
            titulo_3.setHorizontalAlignment(1);
            domicilio.addCell(titulo_3);
            documento.add(domicilio);

            PdfPTable detalle3_1 = new PdfPTable(4);
            detalle3_1.setWidthPercentage(100);
            float[] size4 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle3_1.setWidths(size4);
            detalle3_1.addCell(new Paragraph("Calle", conceptos));
            detalle3_1.addCell(new Paragraph(lugEmb_calle, detalle));
            detalle3_1.addCell(new Paragraph("No. Interior", conceptos));
            detalle3_1.addCell(new Paragraph(lugEmb_noInterior, detalle));
            documento.add(detalle3_1);

            PdfPTable detalle3_2 = new PdfPTable(4);
            detalle3_2.setWidthPercentage(100);
            float[] size5 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle3_2.setWidths(size5);
            detalle3_2.addCell(new Paragraph("Colonia", conceptos));
            detalle3_2.addCell(new Paragraph(lugEmb_colonia, detalle));
            detalle3_2.addCell(new Paragraph("Localidad", conceptos));
            detalle3_2.addCell(new Paragraph("", detalle));
            documento.add(detalle3_2);

            PdfPTable detalle3_3 = new PdfPTable(4);
            detalle3_3.setWidthPercentage(100);
            float[] size6 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle3_3.setWidths(size6);
            detalle3_3.addCell(new Paragraph("Municipio", conceptos));
            detalle3_3.addCell(new Paragraph(lugEmb_municipio, detalle));
            detalle3_3.addCell(new Paragraph("Estado", conceptos));
            detalle3_3.addCell(new Paragraph(lugEmb_estado, detalle));
            documento.add(detalle3_3);

            PdfPTable detalle3_4 = new PdfPTable(4);
            detalle3_4.setWidthPercentage(100);
            float[] size7 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle3_4.setWidths(size7);
            detalle3_4.addCell(new Paragraph("País", conceptos));
            detalle3_4.addCell(new Paragraph("MEX - México", detalle));
            detalle3_4.addCell(new Paragraph("CP", conceptos));
            detalle3_4.addCell(new Paragraph(lugEmb_codigoPostal, detalle));
            documento.add(detalle3_4);

            PdfPTable space3 = new PdfPTable(1);
            space3.setWidthPercentage(100);
            PdfPCell white_3 = new PdfPCell(new Paragraph("", th));
            white_3.setColspan(1);
            white_3.setBorder(Rectangle.NO_BORDER);
            white_3.setBackgroundColor(BaseColor.WHITE);
            white_3.setHorizontalAlignment(1);
            space3.addCell(white_3);
            documento.add(space3);

            PdfPTable ubicacion2 = new PdfPTable(1);
            ubicacion2.setWidthPercentage(100);
            PdfPCell titulo_4 = new PdfPCell(new Paragraph("UBICACION 2", th));
            titulo_4.setColspan(1);
            titulo_4.setBorder(Rectangle.NO_BORDER);
            titulo_4.setBackgroundColor(BaseColor.BLACK);
            titulo_4.setHorizontalAlignment(1);
            ubicacion2.addCell(titulo_4);
            documento.add(ubicacion2);

            PdfPTable detalle4_1 = new PdfPTable(4);
            detalle4_1.setWidthPercentage(100);
            float[] size8 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle4_1.setWidths(size8);
            detalle4_1.addCell(new Paragraph("Tipo de Ubicación", conceptos));
            detalle4_1.addCell(new Paragraph("Destino", detalle));
            detalle4_1.addCell(new Paragraph("RFC Remitente o Destinatario", conceptos));
            detalle4_1.addCell(new Paragraph("xxxxxxxxxxxxxxxxx", detalle));
            documento.add(detalle4_1);

            PdfPTable detalle4_2 = new PdfPTable(4);
            detalle4_2.setWidthPercentage(100);
            float[] size9 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle4_2.setWidths(size9);
            detalle4_2.addCell(new Paragraph("Nombre Remitente o Destinatario", conceptos));
            detalle4_2.addCell(new Paragraph(dest_nombre, detalle));
            detalle4_2.addCell(new Paragraph("Fecha Hora Salida Llegada", conceptos));
            detalle4_2.addCell(new Paragraph("xxxxxxxxxxxxxxxxx", detalle));
            documento.add(detalle4_2);

            PdfPTable detalle4_3 = new PdfPTable(4);
            detalle4_3.setWidthPercentage(100);
            float[] size10 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle4_3.setWidths(size10);
            detalle4_3.addCell(new Paragraph("Distancia Recorrida", conceptos));
            detalle4_3.addCell(new Paragraph("0.00", detalle));
            detalle4_3.addCell(new Paragraph("", conceptos));
            detalle4_3.addCell(new Paragraph("", detalle));
            documento.add(detalle4_3);

            PdfPTable space4 = new PdfPTable(1);
            space4.setWidthPercentage(100);
            PdfPCell white_4 = new PdfPCell(new Paragraph("", th));
            white_4.setColspan(1);
            white_4.setBorder(Rectangle.NO_BORDER);
            white_4.setBackgroundColor(BaseColor.WHITE);
            white_4.setHorizontalAlignment(1);
            space4.addCell(white_4);
            documento.add(space4);

            PdfPTable domicilio2 = new PdfPTable(1);
            domicilio2.setWidthPercentage(100);
            PdfPCell titulo_5 = new PdfPCell(new Paragraph("DOMICILIO", th));
            titulo_5.setColspan(1);
            titulo_5.setBorder(Rectangle.NO_BORDER);
            titulo_5.setBackgroundColor(BaseColor.BLACK);
            titulo_5.setHorizontalAlignment(1);
            domicilio2.addCell(titulo_5);
            documento.add(domicilio2);

            PdfPTable detalle5_1 = new PdfPTable(4);
            detalle5_1.setWidthPercentage(100);
            float[] size12 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle5_1.setWidths(size12);
            detalle5_1.addCell(new Paragraph("Localidad", conceptos));
            detalle5_1.addCell(new Paragraph("", detalle));
            detalle5_1.addCell(new Paragraph("Municipio", conceptos));
            detalle5_1.addCell(new Paragraph(dest_municipio, detalle));
            documento.add(detalle5_1);

            PdfPTable detalle5_2 = new PdfPTable(4);
            detalle5_2.setWidthPercentage(100);
            float[] size13 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle5_2.setWidths(size13);
            detalle5_2.addCell(new Paragraph("Estado", conceptos));
            detalle5_2.addCell(new Paragraph(dest_estado, detalle));
            detalle5_2.addCell(new Paragraph("País", conceptos));
            detalle5_2.addCell(new Paragraph("MEX - México", detalle));
            documento.add(detalle5_2);

            PdfPTable detalle5_3 = new PdfPTable(4);
            detalle5_3.setWidthPercentage(100);
            float[] size14 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle5_3.setWidths(size14);
            detalle5_3.addCell(new Paragraph("CP", conceptos));
            detalle5_3.addCell(new Paragraph(dest_codigoPostal, detalle));
            detalle5_3.addCell(new Paragraph("", conceptos));
            detalle5_3.addCell(new Paragraph("", detalle));
            documento.add(detalle5_3);

            PdfPTable space5 = new PdfPTable(1);
            space5.setWidthPercentage(100);
            PdfPCell white_5 = new PdfPCell(new Paragraph("", th));
            white_5.setColspan(1);
            white_5.setBorder(Rectangle.NO_BORDER);
            white_5.setBackgroundColor(BaseColor.WHITE);
            white_5.setHorizontalAlignment(1);
            space5.addCell(white_5);
            documento.add(space5);

            /**
             * ************************************************
             */
            PdfPTable ubicacion3 = new PdfPTable(1);
            ubicacion3.setWidthPercentage(100);
            PdfPCell titulo_05 = new PdfPCell(new Paragraph("UBICACION 3", th));
            titulo_05.setColspan(1);
            titulo_05.setBorder(Rectangle.NO_BORDER);
            titulo_05.setBackgroundColor(BaseColor.BLACK);
            titulo_05.setHorizontalAlignment(1);
            ubicacion3.addCell(titulo_05);
            documento.add(ubicacion3);

            PdfPTable detalle05_1 = new PdfPTable(4);
            detalle05_1.setWidthPercentage(100);
            float[] size09 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle05_1.setWidths(size09);
            detalle05_1.addCell(new Paragraph("Tipo de Ubicación", conceptos));
            detalle05_1.addCell(new Paragraph("Destino", detalle));
            detalle05_1.addCell(new Paragraph("RFC Remitente o Destinatario", conceptos));
            detalle05_1.addCell(new Paragraph("xxxxxxxxxxxxxxxxx", detalle));
            documento.add(detalle05_1);

            PdfPTable detalle05_2 = new PdfPTable(4);
            detalle05_2.setWidthPercentage(100);
            float[] size010 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle05_2.setWidths(size010);
            detalle05_2.addCell(new Paragraph("Nombre Remitente o Destinatario", conceptos));
            detalle05_2.addCell(new Paragraph(lugEnt_nombre, detalle));
            detalle05_2.addCell(new Paragraph("Fecha Hora Salida Llegada", conceptos));
            detalle05_2.addCell(new Paragraph("xxxxxxxxxxxxxxxxx", detalle));
            documento.add(detalle05_2);

            PdfPTable detalle05_3 = new PdfPTable(4);
            detalle05_3.setWidthPercentage(100);
            float[] size011 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle05_3.setWidths(size011);
            detalle05_3.addCell(new Paragraph("Distancia Recorrida", conceptos));
            detalle05_3.addCell(new Paragraph("0.00", detalle));
            detalle05_3.addCell(new Paragraph("", conceptos));
            detalle05_3.addCell(new Paragraph("", detalle));
            documento.add(detalle05_3);

            PdfPTable space05 = new PdfPTable(1);
            space05.setWidthPercentage(100);
            PdfPCell white_05 = new PdfPCell(new Paragraph("", th));
            white_05.setColspan(1);
            white_05.setBorder(Rectangle.NO_BORDER);
            white_05.setBackgroundColor(BaseColor.WHITE);
            white_05.setHorizontalAlignment(1);
            space05.addCell(white_05);
            documento.add(space05);

            PdfPTable domicilio03 = new PdfPTable(1);
            domicilio03.setWidthPercentage(100);
            PdfPCell titulo_06 = new PdfPCell(new Paragraph("DOMICILIO", th));
            titulo_06.setColspan(1);
            titulo_06.setBorder(Rectangle.NO_BORDER);
            titulo_06.setBackgroundColor(BaseColor.BLACK);
            titulo_06.setHorizontalAlignment(1);
            domicilio03.addCell(titulo_06);
            documento.add(domicilio03);

            PdfPTable detalle06_1 = new PdfPTable(4);
            detalle06_1.setWidthPercentage(100);
            float[] size013 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle06_1.setWidths(size013);
            detalle06_1.addCell(new Paragraph("Localidad", conceptos));
            detalle06_1.addCell(new Paragraph("", detalle));
            detalle06_1.addCell(new Paragraph("Municipio", conceptos));
            detalle06_1.addCell(new Paragraph(lugEnt_municipio, detalle));
            documento.add(detalle06_1);

            PdfPTable detalle06_2 = new PdfPTable(4);
            detalle06_2.setWidthPercentage(100);
            float[] size014 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle06_2.setWidths(size014);
            detalle06_2.addCell(new Paragraph("Estado", conceptos));
            detalle06_2.addCell(new Paragraph(lugEnt_estado, detalle));
            detalle06_2.addCell(new Paragraph("País", conceptos));
            detalle06_2.addCell(new Paragraph("MEX - México", detalle));
            documento.add(detalle06_2);

            PdfPTable detalle06_3 = new PdfPTable(4);
            detalle06_3.setWidthPercentage(100);
            float[] size015 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle06_3.setWidths(size015);
            detalle06_3.addCell(new Paragraph("CP", conceptos));
            detalle06_3.addCell(new Paragraph(lugEnt_codigoPostal, detalle));
            detalle06_3.addCell(new Paragraph("", conceptos));
            detalle06_3.addCell(new Paragraph("", detalle));
            documento.add(detalle06_3);

            PdfPTable space06 = new PdfPTable(1);
            space06.setWidthPercentage(100);
            PdfPCell white_06 = new PdfPCell(new Paragraph("", th));
            white_06.setColspan(1);
            white_06.setBorder(Rectangle.NO_BORDER);
            white_06.setBackgroundColor(BaseColor.WHITE);
            white_06.setHorizontalAlignment(1);
            space06.addCell(white_06);
            documento.add(space06);

            PdfPTable mercancias = new PdfPTable(1);
            mercancias.setWidthPercentage(100);
            PdfPCell titulo_6 = new PdfPCell(new Paragraph("MERCANCIAS", th));
            titulo_6.setColspan(1);
            titulo_6.setBorder(Rectangle.NO_BORDER);
            titulo_6.setBackgroundColor(BaseColor.BLACK);
            titulo_6.setHorizontalAlignment(1);
            mercancias.addCell(titulo_6);
            documento.add(mercancias);

            PdfPTable detalle6_1 = new PdfPTable(4);
            detalle6_1.setWidthPercentage(100);
            float[] size15 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle6_1.setWidths(size15);
            detalle6_1.addCell(new Paragraph("Peso Bruto Total", conceptos));
            detalle6_1.addCell(new Paragraph("", detalle));
            detalle6_1.addCell(new Paragraph("Unidad Peso", conceptos));
            detalle6_1.addCell(new Paragraph("", detalle));
            documento.add(detalle6_1);

            PdfPTable detalle6_2 = new PdfPTable(4);
            detalle6_2.setWidthPercentage(100);
            float[] size16 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle6_2.setWidths(size16);
            detalle6_2.addCell(new Paragraph("Num. Total Mercancias", conceptos));
            detalle6_2.addCell(new Paragraph("", detalle));
            detalle6_2.addCell(new Paragraph("", conceptos));
            detalle6_2.addCell(new Paragraph("", detalle));
            documento.add(detalle6_2);

            PdfPTable space6 = new PdfPTable(1);
            space6.setWidthPercentage(100);
            PdfPCell white_6 = new PdfPCell(new Paragraph("", th));
            white_6.setColspan(1);
            white_6.setBorder(Rectangle.NO_BORDER);
            white_6.setBackgroundColor(BaseColor.WHITE);
            white_6.setHorizontalAlignment(1);
            space6.addCell(white_6);
            documento.add(space6);

            CallableStatement c2 = con.prepareCall(fac.consultarMercancias(id, cve));
            ResultSet rs2 = c2.executeQuery();
            while(rs2.next()){

                    contadorMercancias++;

                    rel_clvsat_id_cp = rs2.getString(1);
                    unidad_sat_id_cp = rs2.getString(2);
                    unidad_sat_desc_cp = rs2.getString(3);
                    concepto_id_cp = rs2.getString(4);
                    concepto_desc_cp = rs2.getString(5);
                    cantidad_cp = rs2.getString(6);
                    precio_unitario_cp = rs2.getString(7);
                    importe_descuento_cp = rs2.getString(8);
                    importe_cp = rs2.getString(9);
                    //unidadMedidaValor = rs.getString(10);

                    //Inicia Iteración:
                    PdfPTable mercancia1 = new PdfPTable(1);
                    mercancia1.setWidthPercentage(100);
                    PdfPCell titulo_7 = new PdfPCell(new Paragraph("MERCANCIA " + contadorMercancias, th));
                    titulo_7.setColspan(1);
                    titulo_7.setBorder(Rectangle.NO_BORDER);
                    titulo_7.setBackgroundColor(BaseColor.BLACK);
                    titulo_7.setHorizontalAlignment(1);
                    mercancia1.addCell(titulo_7);
                    documento.add(mercancia1);

                    PdfPTable detalle7_1 = new PdfPTable(4);
                    detalle7_1.setWidthPercentage(100);
                    float[] size17 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle7_1.setWidths(size17);
                    detalle7_1.addCell(new Paragraph("Bienes Transp.", conceptos));
                    detalle7_1.addCell(new Paragraph(rel_clvsat_id_cp, detalle));
                    detalle7_1.addCell(new Paragraph("Descripción", conceptos));
                    detalle7_1.addCell(new Paragraph(concepto_desc_cp, detalle));
                    documento.add(detalle7_1);

                    PdfPTable detalle7_2 = new PdfPTable(4);
                    detalle7_2.setWidthPercentage(100);
                    float[] size18 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle7_2.setWidths(size18);
                    detalle7_2.addCell(new Paragraph("Cantidad", conceptos));
                    detalle7_2.addCell(new Paragraph(cantidad_cp, detalle));
                    detalle7_2.addCell(new Paragraph("Clave Unidad", conceptos));
                    detalle7_2.addCell(new Paragraph(unidad_sat_desc_cp, detalle));
                    documento.add(detalle7_2);

                    PdfPTable detalle7_3 = new PdfPTable(4);
                    detalle7_3.setWidthPercentage(100);
                    float[] size18_1 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle7_3.setWidths(size18_1);
                    detalle7_3.addCell(new Paragraph("Peso En Kg", conceptos));
                    detalle7_3.addCell(new Paragraph("", detalle));
                    detalle7_3.addCell(new Paragraph("", conceptos));
                    detalle7_3.addCell(new Paragraph("", detalle));
                    documento.add(detalle7_3);

            }

            PdfPTable space9 = new PdfPTable(1);
            space9.setWidthPercentage(100);
            PdfPCell white_9 = new PdfPCell(new Paragraph("", th));
            white_9.setColspan(1);
            white_9.setBorder(Rectangle.NO_BORDER);
            white_9.setBackgroundColor(BaseColor.WHITE);
            white_9.setHorizontalAlignment(1);
            space9.addCell(white_9);
            documento.add(space9);

            PdfPTable autotransporte = new PdfPTable(1);
            autotransporte.setWidthPercentage(100);
            PdfPCell titulo_10 = new PdfPCell(new Paragraph("AUTOTRANSPORTE", th));
            titulo_10.setColspan(1);
            titulo_10.setBorder(Rectangle.NO_BORDER);
            titulo_10.setBackgroundColor(BaseColor.BLACK);
            titulo_10.setHorizontalAlignment(1);
            autotransporte.addCell(titulo_10);
            documento.add(autotransporte);

            PdfPTable detalle10_1 = new PdfPTable(4);
            detalle10_1.setWidthPercentage(100);
            float[] size25 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle10_1.setWidths(size25);
            detalle10_1.addCell(new Paragraph("Perm SCT", conceptos));
            detalle10_1.addCell(new Paragraph(perm_sct, detalle));
            detalle10_1.addCell(new Paragraph("No. Permiso SCT", conceptos));
            detalle10_1.addCell(new Paragraph(no_permismo_sct, detalle));
            documento.add(detalle10_1);

            PdfPTable space10 = new PdfPTable(1);
            space10.setWidthPercentage(100);
            PdfPCell white_10 = new PdfPCell(new Paragraph("", th));
            white_10.setColspan(1);
            white_10.setBorder(Rectangle.NO_BORDER);
            white_10.setBackgroundColor(BaseColor.WHITE);
            white_10.setHorizontalAlignment(1);
            space10.addCell(white_10);
            documento.add(space10);

            PdfPTable identificaionV = new PdfPTable(1);
            identificaionV.setWidthPercentage(100);
            PdfPCell titulo_11 = new PdfPCell(new Paragraph("IDENTIFICACIÓN VEHICULAR", th));
            titulo_11.setColspan(1);
            titulo_11.setBorder(Rectangle.NO_BORDER);
            titulo_11.setBackgroundColor(BaseColor.BLACK);
            titulo_11.setHorizontalAlignment(1);
            identificaionV.addCell(titulo_11);
            documento.add(identificaionV);

            PdfPTable detalle11_1 = new PdfPTable(4);
            detalle11_1.setWidthPercentage(100);
            float[] size26 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle11_1.setWidths(size26);
            detalle11_1.addCell(new Paragraph("Configuración Vehicular", conceptos));
            detalle11_1.addCell(new Paragraph(config_vehicular, detalle));
            detalle11_1.addCell(new Paragraph("Placa VM", conceptos));
            detalle11_1.addCell(new Paragraph(placa_vm, detalle));
            documento.add(detalle11_1);

            PdfPTable detalle11_2 = new PdfPTable(4);
            detalle11_2.setWidthPercentage(100);
            float[] size27 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle11_2.setWidths(size27);
            detalle11_2.addCell(new Paragraph("Año Modelo VM", conceptos));
            detalle11_2.addCell(new Paragraph(anio_modelovm, detalle));
            detalle11_2.addCell(new Paragraph("", conceptos));
            detalle11_2.addCell(new Paragraph("", detalle));
            documento.add(detalle11_2);

            PdfPTable space11 = new PdfPTable(1);
            space11.setWidthPercentage(100);
            PdfPCell white_11 = new PdfPCell(new Paragraph("", th));
            white_11.setColspan(1);
            white_11.setBorder(Rectangle.NO_BORDER);
            white_11.setBackgroundColor(BaseColor.WHITE);
            white_11.setHorizontalAlignment(1);
            space11.addCell(white_11);
            documento.add(space11);

            PdfPTable seguros = new PdfPTable(1);
            seguros.setWidthPercentage(100);
            PdfPCell titulo_12 = new PdfPCell(new Paragraph("SEGUROS", th));
            titulo_12.setColspan(1);
            titulo_12.setBorder(Rectangle.NO_BORDER);
            titulo_12.setBackgroundColor(BaseColor.BLACK);
            titulo_12.setHorizontalAlignment(1);
            seguros.addCell(titulo_12);
            documento.add(seguros);

            PdfPTable detalle12_1 = new PdfPTable(4);
            detalle12_1.setWidthPercentage(100);
            float[] size28 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle12_1.setWidths(size28);
            detalle12_1.addCell(new Paragraph("Aseguradora Responsabilidad Civil", conceptos));
            detalle12_1.addCell(new Paragraph(aseguradora_civil, detalle));
            detalle12_1.addCell(new Paragraph("Poliza Responsabilidad Civil", conceptos));
            detalle12_1.addCell(new Paragraph(poliza_civil, detalle));
            documento.add(detalle12_1);

            PdfPTable space12 = new PdfPTable(1);
            space12.setWidthPercentage(100);
            PdfPCell white_12 = new PdfPCell(new Paragraph("", th));
            white_12.setColspan(1);
            white_12.setBorder(Rectangle.NO_BORDER);
            white_12.setBackgroundColor(BaseColor.WHITE);
            white_12.setHorizontalAlignment(1);
            space12.addCell(white_12);
            documento.add(space12);

            PdfPTable figuraT = new PdfPTable(1);
            figuraT.setWidthPercentage(100);
            PdfPCell titulo_13 = new PdfPCell(new Paragraph("FIGURA TRANSPORTE 1", th));
            titulo_13.setColspan(1);
            titulo_13.setBorder(Rectangle.NO_BORDER);
            titulo_13.setBackgroundColor(BaseColor.BLACK);
            titulo_13.setHorizontalAlignment(1);
            figuraT.addCell(titulo_13);
            documento.add(figuraT);

            PdfPTable detalle13_1 = new PdfPTable(4);
            detalle13_1.setWidthPercentage(100);
            float[] size29 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle13_1.setWidths(size29);
            detalle13_1.addCell(new Paragraph("Tipo Figura", conceptos));
            detalle13_1.addCell(new Paragraph(tipo_figura, detalle));
            detalle13_1.addCell(new Paragraph("RFC Figura", conceptos));
            detalle13_1.addCell(new Paragraph(rfc_figura, detalle));
            documento.add(detalle13_1);

            PdfPTable detalle13_2 = new PdfPTable(4);
            detalle13_2.setWidthPercentage(100);
            float[] size30 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle13_2.setWidths(size30);
            detalle13_2.addCell(new Paragraph("Núm. Licencia", conceptos));
            detalle13_2.addCell(new Paragraph(num_licencia, detalle));
            detalle13_2.addCell(new Paragraph("Nombre Figura", conceptos));
            detalle13_2.addCell(new Paragraph(nombre_figura, detalle));
            documento.add(detalle13_2);

            PdfPTable space13 = new PdfPTable(1);
            space13.setWidthPercentage(100);
            PdfPCell white_13 = new PdfPCell(new Paragraph("", th));
            white_13.setColspan(1);
            white_13.setBorder(Rectangle.NO_BORDER);
            white_13.setBackgroundColor(BaseColor.WHITE);
            white_13.setHorizontalAlignment(1);
            space13.addCell(white_13);
            documento.add(space13);

            PdfPTable etiquetas = new PdfPTable(1);
            etiquetas.setWidthPercentage(100);
            PdfPCell titulo_14 = new PdfPCell(new Paragraph("ETIQUETAS PERSONALIZADAS", th));
            titulo_14.setColspan(1);
            titulo_14.setBorder(Rectangle.NO_BORDER);
            titulo_14.setBackgroundColor(BaseColor.BLACK);
            titulo_14.setHorizontalAlignment(1);
            etiquetas.addCell(titulo_14);
            documento.add(etiquetas);

            PdfPTable detalle14_1 = new PdfPTable(4);
            detalle14_1.setWidthPercentage(100);
            float[] size31 = {0.90f, 1.10f, 0.90f, 1.10f};
            detalle14_1.setWidths(size31);
            detalle14_1.addCell(new Paragraph("AAMS: VLAFRT0100 / NUMERO DE PROVEEDOR", conceptos));
            detalle14_1.addCell(new Paragraph("", detalle));
            detalle14_1.addCell(new Paragraph("FOLIO LIQUIDACION", conceptos));
            detalle14_1.addCell(new Paragraph("", detalle));
            documento.add(detalle14_1);

            PdfPTable space14 = new PdfPTable(1);
            space14.setWidthPercentage(100);
            PdfPCell white_14 = new PdfPCell(new Paragraph("", th));
            white_14.setColspan(1);
            white_14.setBorder(Rectangle.NO_BORDER);
            white_14.setBackgroundColor(BaseColor.WHITE);
            white_14.setHorizontalAlignment(1);
            space14.addCell(white_14);
            documento.add(space14);

            PdfPTable concepto = new PdfPTable(1);
            concepto.setWidthPercentage(100);
            PdfPCell titulo_15 = new PdfPCell(new Paragraph("CONCEPTOS", th));
            titulo_15.setColspan(1);
            titulo_15.setBorder(Rectangle.NO_BORDER);
            titulo_15.setBackgroundColor(BaseColor.BLACK);
            titulo_15.setHorizontalAlignment(1);
            concepto.addCell(titulo_15);
            documento.add(concepto);

            PdfPTable space15 = new PdfPTable(1);
            space15.setWidthPercentage(100);
            PdfPCell white_15 = new PdfPCell(new Paragraph("", th));
            white_15.setColspan(1);
            white_15.setBorder(Rectangle.NO_BORDER);
            white_15.setBackgroundColor(BaseColor.WHITE);
            white_15.setHorizontalAlignment(1);
            space15.addCell(white_15);
            documento.add(space15);

            PdfPTable detalle_conceptos = new PdfPTable(4);
            detalle_conceptos.setWidthPercentage(100);
            PdfPCell titulo_16 = new PdfPCell(new Paragraph("Cantidad", th));
            titulo_16.setColspan(1);
            titulo_16.setBorder(Rectangle.NO_BORDER);
            titulo_16.setBackgroundColor(BaseColor.BLACK);
            titulo_16.setHorizontalAlignment(1);
            detalle_conceptos.addCell(titulo_16);
            PdfPCell titulo_17 = new PdfPCell(new Paragraph("Unidad", th));
            titulo_17.setColspan(1);
            titulo_17.setBorder(Rectangle.NO_BORDER);
            titulo_17.setBackgroundColor(BaseColor.BLACK);
            titulo_17.setHorizontalAlignment(1);
            detalle_conceptos.addCell(titulo_17);
            PdfPCell titulo_18 = new PdfPCell(new Paragraph("Descripción", th));
            titulo_18.setColspan(1);
            titulo_18.setBorder(Rectangle.NO_BORDER);
            titulo_18.setBackgroundColor(BaseColor.BLACK);
            titulo_18.setHorizontalAlignment(1);
            detalle_conceptos.addCell(titulo_18);
            PdfPCell titulo_19 = new PdfPCell(new Paragraph("Precio Unitario", th));
            titulo_19.setColspan(1);
            titulo_19.setBorder(Rectangle.NO_BORDER);
            titulo_19.setBackgroundColor(BaseColor.BLACK);
            titulo_19.setHorizontalAlignment(1);
            detalle_conceptos.addCell(titulo_19);
            PdfPCell titulo_20 = new PdfPCell(new Paragraph("Importe", th));
            titulo_20.setColspan(1);
            titulo_20.setBorder(Rectangle.NO_BORDER);
            titulo_20.setBackgroundColor(BaseColor.BLACK);
            titulo_20.setHorizontalAlignment(1);
            detalle_conceptos.addCell(titulo_20);
            documento.add(detalle_conceptos);

            documento.close();
            System.out.println("¡Se ha generado el PDF Recibo de Pago!");

        } catch (DocumentException documentException) {
            System.out.println("El archivo no existe (Se ha producido un error al generar un documento): " + documentException);
        }
    }
}