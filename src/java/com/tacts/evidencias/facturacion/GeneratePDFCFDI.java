/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.facturacion;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.pdf.BarcodeQRCode;
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
import com.itextpdf.text.Phrase;
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
import java.text.DateFormat;


/**
 *
 * @author luis_
 */
public class GeneratePDFCFDI {

    // Fonts definitions (Definición de fuentes).
    private static final Font espacio = FontFactory.getFont("Times New Roman", 7, Font.BOLD, BaseColor.BLACK);
    private static final Font espacios = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
    private static final Font th = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.WHITE);
    private static final Font fontTitututu = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
    private static final Font para = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.RED);
    private static final Font tiends = FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.RED);
    private static final Font encabezado0 = FontFactory.getFont("arial", 13, Font.BOLD, BaseColor.RED);
    private static final Font encabezado1 = FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED);
    private static final Font encabezado4 = FontFactory.getFont("arial", 8, Font.NORMAL, BaseColor.BLACK);
    private static final Font encabezado5 = FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK);
    private static final Font encabezado6 = FontFactory.getFont("arial", 11, Font.BOLD, BaseColor.BLACK);
    private static final Font encabezado8 = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
    private static final Font encabezado9 = FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK);
    private static final Font encabezado10 = FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK);
    private static final Font encabezado11 = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
    private static final Font encabezado12 = FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK);
    /**
     * We create a PDF document with iText using different elements to learn to
     * use this library. Creamos un documento PDF con iText usando diferentes
     * elementos para aprender a usar esta librería.
     *
     * @param pdfNewFile  <code>String</code> pdf File we are going to write.
     * Fichero pdf en el que vamos a escribir.
     */
    public void createPDFCFDI(File pdfNewFile, String id) throws SQLException {

         //Extracción de Fecha:
        Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formato.format(date);
        String[] par = fecha.split("/");
        String part1 = par[0]; //día
        String part2 = par[1]; //mes
        String part3 = par[2]; //año

        //Extracción de Hora:  
        Date hr = new Date();
        DateFormat hours = new SimpleDateFormat("HH:mm:ss");
        String hora = hours.format(hr);

        String cliente_id = "1";
        String suscliente_id = "";
        String rfc = "";
        String usocfdi_id = "";
        String correos = "";
        String tipo_descripcion = "";
        //String fecha_emision = request.getParameter("fecha_emision");
        String fecha_emision = "31/01/2022";
        String hora_emision = "";
        String serie = "";
        String folio = "";
        String comprobante_id = "";
        String documento_id = "";
        String regimen_id = "";
        String metodo_id = "";
        String forma_id = "";
        String condiciones_pago = "";
        String moneda_id = "";
        String cambio = "";
        String num_orden = "";
        String num_proveedor = "";
        String info_observaciones = "";

        //Parametros nuevos (TRA_FACTURACION):
        String impuesto_global = "";
        String subtotal_global = "";
        String retenciones_global = "";
        String total_global = "";
        String importe_descuento_global = "";
        String traslados_global = "";
        String tipo_factor_global = "";
        String tasa_global = "";
        String cuota_global = "";
        String tipo_impuesto_global = "";
        String tipo_cambio = "";

        if (cambio == null) {
            tipo_cambio = "500";
        }

        //Contadores Facturación:            
        String contConcept = "";
        String contImpuestos = "";
        String contPedimentos = "";
        String contPartes = "";
        String contUUID = "";
        int numConcept = Integer.parseInt(contConcept);
        int numImpuestos = Integer.parseInt(contImpuestos);
        int numPedimentos = Integer.parseInt(contPedimentos);
        int numPartes = Integer.parseInt(contPartes);
        int numUUID = Integer.parseInt(contUUID);

        String fechaHoraProvional = part3 + "-" + part2 + "-" + part1 + "T" + hora;
        String fechaCertifSAT = "";
        String fechaEmision = "";

        String cliente_desc = "";
        String cliente_rfc = "";
        String cliente_cp = "";

        String suscliente_desc = "";
        String suscliente_rfc = "";
        String suscliente_usoCfdi = "";

        String regimen_desc = "";

        String folio_fiscal = "";
        String no_serie_certificado_sat = "";
        String num_certificado_digital = "";

        String moneda_desc = "";
        String comprobante_desc = "";

        String metodo_pago_desc = "";
        String forma_pago_desc = "";

        String selloDigitalCFDI = "";
        String selloDigitalSAT = "";
        String certificacionDigital = "";
        String verficacioncfdiQr = "";

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

            DataBaseConnect dbd = new DataBaseConnect();
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
            //String rfc = "";
            String alias_uso = "";
            String direccion = "";
            String rel_cartaporte_id = "";
            String com_uuid = "";
            String com_nocertificadosat = "";
            String com_nocertificadoemisor = "";
            //String fecha_emision = "";
            String com_rfcprovcertif = "";
            String com_fechatimbrado = "";

            //Consulta: Cliente
            CallableStatement c0 = con.prepareCall(fac.consultaCliente(cve));
            ResultSet rs0 = c0.executeQuery();

            while (rs0.next()) {
                cliente_descripcion0 = rs0.getString(1);
                rfc0 = rs0.getString(2);
                alias_reg = rs0.getString(3);
                direccion0 = rs0.getString(4);
                codigo_postal = rs0.getString(5);
            }

            //Consulta: Sus-Cliente
            CallableStatement c1 = con.prepareCall(fac.consultarSusCliente(id, cve));
            ResultSet rs1 = c1.executeQuery();

            while (rs1.next()) {
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
            
            

            PdfPTable table = new PdfPTable(3);

            table.setWidthPercentage(100);
            table.setHorizontalAlignment(Element.ALIGN_RIGHT);
            float[] sizeT = {0.80f, 1.60f, 1.40f};
            table.setWidths(sizeT);

            PdfPCell cell = new PdfPCell(new Phrase(""));
            cell.setColspan(11);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(""));
            cell.setRowspan(15);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(cliente_desc, encabezado0));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph a = new Paragraph("Factura electrónica", encabezado1);
            a.setAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(a);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", encabezado4));
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

            Paragraph b = new Paragraph(serie + "" + folio, encabezado5);
            b.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(b);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("México", encabezado10));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph c = new Paragraph("Fecha y Hora de Certificación", encabezado1);
            c.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(c);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(cliente_rfc, encabezado9));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph d = new Paragraph(fechaCertifSAT, encabezado4);
            d.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(d);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Regimen Fiscal: " + regimen_desc, encabezado8));
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setPadding(5);
            cell.setUseAscender(true);
            cell.setUseDescender(true);

            Paragraph e = new Paragraph("Fecha y Hora de Emisión", encabezado1);
            e.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(e);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", encabezado4));
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

            Paragraph f = new Paragraph(fechaEmision, encabezado4);
            f.setAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(f);
            table.addCell(cell);
            documento.add(table);

            documento.add(new Paragraph(" "));

            PdfPTable table2 = new PdfPTable(3);
            table2.setWidthPercentage(100);
            //float[] medidaDesc = {0.50f, 0.55f, 0.50f, 0.50f, 0.50f, 2.05f, 0.80f, 0.70f, 0.60f};
            //table2.setWidths(medidaDesc);
            table2.setHorizontalAlignment(Element.ALIGN_RIGHT);        // (Element.ALIGN_RIGHT);
            float[] medidaCeldas2 = {1.50f, 1.30f, 1.30f};
            table2.setWidths(medidaCeldas2);
            table2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table2.addCell(new Paragraph("Cliente", encabezado1));
            table2.addCell(new Paragraph("Folio Fiscal", encabezado1));
            table2.addCell(new Paragraph("No.Serie Certificado SAT", encabezado1));

            table2.addCell(new Paragraph(suscliente_desc, encabezado6));
            table2.addCell(new Paragraph(folio_fiscal, encabezado4));
            table2.addCell(new Paragraph(no_serie_certificado_sat, encabezado4));

            table2.addCell(new Paragraph("Uso de CFDI: " + suscliente_usoCfdi, encabezado4));
            table2.addCell(new Paragraph("Numero Certificado Digital", encabezado1));
            table2.addCell(new Paragraph("Tipo de Comprobante", encabezado1));

            table2.addCell(new Paragraph(suscliente_rfc, encabezado4));
            table2.addCell(new Paragraph(num_certificado_digital, encabezado4));
            table2.addCell(new Paragraph(comprobante_desc, encabezado4));

            table2.addCell(new Paragraph(" "));
            table2.addCell(new Paragraph("Moneda / Tipo de Cambio", encabezado1));
            table2.addCell(new Paragraph("Información Comercial", encabezado1));

            table2.addCell(new Paragraph(""));
            table2.addCell(new Paragraph(moneda_desc + "        " + moneda_id, encabezado4));
            table2.addCell(new Paragraph("No. de Cliente: " + cliente_id, encabezado4));

            table2.addCell(new Paragraph(""));
            table2.addCell(new Paragraph("Lugar de Expedición", tiends));
            table2.addCell(new Paragraph(" "));

            table2.addCell(new Paragraph(""));
            table2.addCell(new Paragraph(cliente_cp, encabezado4));
            table2.addCell(new Paragraph(" "));
            documento.add(table2);

            documento.add(new Paragraph(" "));

            PdfPTable encabezadoTT = new PdfPTable(10);
            encabezadoTT.setWidthPercentage(100);

            PdfPCell col_1 = new PdfPCell(new Paragraph("Codigo", th));
            col_1.setColspan(1);
            col_1.setBorder(Rectangle.NO_BORDER);
            col_1.setBackgroundColor(BaseColor.RED);
            encabezadoTT.addCell(col_1);

            PdfPCell col_2 = new PdfPCell(new Paragraph("ClaveSAT", th));
            col_2.setColspan(1);
            col_2.setBorder(Rectangle.NO_BORDER);
            col_2.setBackgroundColor(BaseColor.RED);
            encabezadoTT.addCell(col_2);

            PdfPCell col_3 = new PdfPCell(new Paragraph("Clave Unidad", th));
            col_3.setColspan(1);
            col_3.setBorder(Rectangle.NO_BORDER);
            col_3.setBackgroundColor(BaseColor.RED);
            encabezadoTT.addCell(col_3);

            PdfPCell col_4 = new PdfPCell(new Paragraph("Cantidad", th));
            col_4.setColspan(1);
            col_4.setBorder(Rectangle.NO_BORDER);
            col_4.setBackgroundColor(BaseColor.RED);
            col_4.setHorizontalAlignment(Element.ALIGN_CENTER);
            encabezadoTT.addCell(col_4);

            PdfPCell col_5 = new PdfPCell(new Paragraph("Unidad de Medida", th));
            col_5.setColspan(1);
            col_5.setBorder(Rectangle.NO_BORDER);
            col_5.setBackgroundColor(BaseColor.RED);
            col_5.setHorizontalAlignment(Element.ALIGN_CENTER);
            encabezadoTT.addCell(col_5);

            PdfPCell col_6 = new PdfPCell(new Paragraph("Concepto", th));
            col_6.setColspan(2);
            col_6.setBorder(Rectangle.NO_BORDER);
            col_6.setBackgroundColor(BaseColor.RED);
            col_6.setHorizontalAlignment(Element.ALIGN_CENTER);
            encabezadoTT.addCell(col_6);

            PdfPCell col_7 = new PdfPCell(new Paragraph("Precio Unitario", th));
            col_7.setColspan(1);
            col_7.setBorder(Rectangle.NO_BORDER);
            col_7.setBackgroundColor(BaseColor.RED);
            col_7.setHorizontalAlignment(Element.ALIGN_CENTER);
            encabezadoTT.addCell(col_7);

            PdfPCell col_8 = new PdfPCell(new Paragraph("Descuento", th));
            col_8.setColspan(1);
            col_8.setBorder(Rectangle.NO_BORDER);
            col_8.setBackgroundColor(BaseColor.RED);
            col_8.setHorizontalAlignment(Element.ALIGN_CENTER);
            encabezadoTT.addCell(col_8);

            PdfPCell col_9 = new PdfPCell(new Paragraph("Importe", th));
            col_9.setColspan(1);
            col_9.setBorder(Rectangle.NO_BORDER);
            col_9.setBackgroundColor(BaseColor.RED);
            col_9.setHorizontalAlignment(Element.ALIGN_CENTER);
            encabezadoTT.addCell(col_9);
            documento.add(encabezadoTT);

            //SECCIÓN: RELACIÓN CONCEPTOS 
            if (numConcept > 0) {
                for (int i = 0; i < numConcept; i++) {
                    String comentarios = "";
                    String rel_clvsat_id = "";
                    String unidad_sat_id = "";
                    String concepto_id = "";
                    String concepto_desc = "";
                    String cantidad = "";
                    String precio_unitario = "";
                    String importe_descuento = "";
                    String importe = "";

                    PdfPTable parametros = new PdfPTable(9);
                    parametros.setWidthPercentage(100);
                    //float[] medidaCeldasparametros = {0.50f, 0.55f, 0.50f, 0.50f, 0.50f, 2.05f, 0.80f, 0.70f, 0.60f};
                    //parametros.setWidths(medidaCeldasparametros);
                    parametros.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                    parametros.addCell(new Paragraph(rel_clvsat_id, espacios));  //codigo
                    parametros.addCell(new Paragraph(rel_clvsat_id, para));      //clave sat
                    parametros.addCell(new Paragraph(unidad_sat_id, para));           //clave unidad
                    parametros.addCell(new Paragraph(cantidad, para));          //cantidad
                    parametros.addCell(new Paragraph("", para));              //unidad de medida
                    parametros.addCell(new Paragraph(concepto_desc, para));  //concepto
                    parametros.addCell(new Paragraph(precio_unitario, para));     //precio unitario
                    parametros.addCell(new Paragraph(importe_descuento, para));              //descuento
                    parametros.addCell(new Paragraph(importe, para));     //importe
                    documento.add(parametros);
                }
            }

            PdfPTable table60 = new PdfPTable(1);
            table60.setWidthPercentage(100);
            table60.setHorizontalAlignment(Element.ALIGN_RIGHT);

            table60.addCell(new Paragraph("                                       Base Gravable:    $12,000             Impuesto:  002                                           Tasa         16.000000%           Importe:        $1,920.00", fontTitututu));
            documento.add(table60);

            documento.add(new Paragraph(" ", espacio));
            documento.add(new Paragraph(" ", espacio));

            PdfPTable table59 = new PdfPTable(6);
            table59.setWidthPercentage(85);
            table59.getDefaultCell().setBorderWidth(0f);
            documento.add(table59);

            PdfPTable table61 = new PdfPTable(3);
            table61.setWidthPercentage(100);
            table61.setHorizontalAlignment(Element.ALIGN_RIGHT);        // (Element.ALIGN_RIGHT);
            float[] medidaCeldas61 = {1.50f, 0.95f, 0.25f};
            table61.setWidths(medidaCeldas61);
            table61.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            //table61.addCell(new Paragraph("(" + Numeros.cantidadConLetra(total_global) + "00/100 M.N.)", encabezado11));
            table61.addCell(new Paragraph("(0.000 00/100 M.N.)", encabezado11));
            table61.addCell(new Paragraph("Subtotal", encabezado12));
            table61.addCell(new Paragraph("$12,000.00", encabezado12));

            table61.addCell(new Paragraph(forma_pago_desc, encabezado11));
            table61.addCell(new Paragraph("Traslados (IVA al 16.000000%)", encabezado12));
            table61.addCell(new Paragraph("$1,920.00", encabezado12));

            table61.addCell(new Paragraph("Método de Pago: " + metodo_pago_desc, encabezado11));
            table61.addCell(new Paragraph("Total", encabezado12));
            table61.addCell(new Paragraph("$13,920.00", encabezado12));

            table61.addCell(new Paragraph("Condiciones de Pago:" + condiciones_pago, encabezado11));
            table61.addCell(new Paragraph(" ", fontTitututu));
            table61.addCell(new Paragraph(" ", encabezado11));

            documento.add(table61);

            documento.add(new Paragraph(" ", espacio));
            documento.add(new Paragraph(" ", espacio));

            //SECCIÓN: IMPUESTOS   
            /*if (numImpuestos > 0) {

                        for (int i = 0; i < numImpuestos; i++) {
                            String up_tipoImpuesto = request.getParameter("up_tipoImpuesto[" + i + "]");
                            String up_base = request.getParameter("up_base[" + i + "]");
                            String up_impuesto = request.getParameter("up_impuesto[" + i + "]");
                            String up_tipoFactor = request.getParameter("up_tipoFactor[" + i + "]");
                            String up_tasa = request.getParameter("up_tasa[" + i + "]");
                            String up_cuota = request.getParameter("up_cuota[" + i + "]");
                            String up_importe = request.getParameter("up_importe[" + i + "]");

                            documento.add(new Paragraph(up_tipoImpuesto, espacio));
                            documento.add(new Paragraph(up_base, espacio));
                            documento.add(new Paragraph(up_impuesto, espacio));
                            documento.add(new Paragraph(up_tipoFactor, espacio));
                        }
                    }*/
            //SECCIÓN: PEDIMENTOS/OTROS 
            /*if (numPedimentos > 0) {

                        for (int i = 0; i < numPedimentos; i++) {
                            String up_cuentaPredial = request.getParameter("up_cuentaPredial[" + i + "]");
                            String up_numeroPedimento = request.getParameter("up_numeroPedimento[" + i + "]");

                            documento.add(new Paragraph(up_cuentaPredial, espacio));
                            documento.add(new Paragraph(up_numeroPedimento, espacio));

                        }
                    }*/
            //SECCIÓN: PARTES  
            /*if (numPartes > 0) {

                        for (int i = 0; i < numPartes; i++) {
                            String part_concepto_id = request.getParameter("parte_concepto_id[" + i + "]");
                            String upParte_desc = request.getParameter("upParte_desc[" + i + "]");
                            String upParte_Cantidad = request.getParameter("upParte_Cantidad[" + i + "]");
                            String upParte_precioUnitario = request.getParameter("upParte_precioUnitario[" + i + "]");
                            String upParte_ClaveProdServ = request.getParameter("upParte_ClaveProdServ[" + i + "]");
                            String upParte_unidad = request.getParameter("upParte_unidad[" + i + "]");
                            String upParte_NoIdentificacion = request.getParameter("upParte_NoIdentificacion[" + i + "]");

                            documento.add(new Paragraph(part_concepto_id, espacio));
                            documento.add(new Paragraph(upParte_desc, espacio));
                        }
                    }*/
            //SECCIÓN: RELACIÓN CFDI    
            /*if (numUUID > 0) {

                        for (int i = 0; i < numUUID; i++) {
                            String tiporelacion_id = request.getParameter("tiporelacion_id[" + i + "]");
                            String uuid_id = request.getParameter("uuid_id[" + i + "]");
                            String total_cfdi = request.getParameter("total_cfdi[" + i + "]");

                            documento.add(new Paragraph(tiporelacion_id, espacio));
                            documento.add(new Paragraph(uuid_id, espacio));
                            documento.add(new Paragraph(total_cfdi, espacio));
                        }
                    }*/
            BarcodeQRCode barcodeQRCode = new BarcodeQRCode(verficacioncfdiQr, 1000, 1000, null);
            Image codeQrImage = barcodeQRCode.getImage();
            codeQrImage.scaleAbsolute(100, 100);

            PdfPTable tableF = new PdfPTable(2);
            tableF.setWidthPercentage(100);

            float[] medidaCeldasTT2 = {0.20f, 0.80f};
            tableF.setWidths(medidaCeldasTT2);

            PdfPCell cellF = new PdfPCell(new Phrase(" "));
            cellF.setColspan(2);
            cellF.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellF.setBorder(Rectangle.NO_BORDER);
            tableF.addCell(cellF);

            cellF = new PdfPCell(codeQrImage);
            cellF.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellF.setRowspan(4);
            cellF.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellF.setBorder(Rectangle.NO_BORDER);
            tableF.addCell(cellF);

            cellF = new PdfPCell(new Phrase("Sello Digital del CFDI", encabezado1));
            cellF.setPadding(5);
            cellF.setUseAscender(true);
            cellF.setUseDescender(true);
            cellF.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellF.setBorder(Rectangle.NO_BORDER);
            tableF.addCell(cellF);

            cellF = new PdfPCell(new Phrase(selloDigitalCFDI, encabezado12));
            cellF.setPadding(5);
            cellF.setUseAscender(true);
            cellF.setUseDescender(true);
            cellF.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellF.setBorder(Rectangle.NO_BORDER);
            tableF.addCell(cellF);

            cellF = new PdfPCell(new Phrase("Sello Digital del SAT", encabezado1));
            cellF.setPadding(5);
            cellF.setUseAscender(true);
            cellF.setUseDescender(true);
            cellF.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellF.setBorder(Rectangle.NO_BORDER);
            tableF.addCell(cellF);

            cellF = new PdfPCell(new Phrase(selloDigitalSAT, encabezado12));
            cellF.setPadding(5);
            cellF.setUseAscender(true);
            cellF.setUseDescender(true);
            cellF.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellF.setBorder(Rectangle.NO_BORDER);
            tableF.addCell(cellF);

            cellF = new PdfPCell(new Phrase("Cadena Original del Complemento de Certificación Digital del SAT", encabezado1));
            cellF.setColspan(2);
            cellF.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellF.setBorder(Rectangle.NO_BORDER);
            tableF.addCell(cellF);

            cellF = new PdfPCell(new Phrase(certificacionDigital, encabezado12));
            cellF.setColspan(2);
            cellF.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellF.setBorder(Rectangle.NO_BORDER);
            tableF.addCell(cellF);

            documento.add(tableF);
            documento.close();
            System.out.println("¡Se ha generado el PDF CFDI!");

        } catch (DocumentException documentException) {
            System.out.println("El archivo no existe (Se ha producido un error al generar un documento): " + documentException);
        }
    }
}
