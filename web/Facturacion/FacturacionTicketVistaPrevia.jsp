<%-- 
    Document   : FacturacionTicketVistaPrevia
    Created on : 21/04/2022, 05:52:07 AM
    Author     : luis_
--%>

<%@page import="com.tacts.evidencias.facturacion.CreateDirectory"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.itextpdf.text.pdf.BarcodeQRCode"%>
<%@page import="com.itextpdf.text.Rectangle"%>
<%@page import="com.itextpdf.text.Element"%>
<%@page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@page import="com.itextpdf.text.Image"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="com.itextpdf.text.FontFactory"%>
<%@page import="com.itextpdf.text.Font"%>
<%@page import="com.itextpdf.text.BaseColor"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="com.itextpdf.text.PageSize"%>
<%@page import="com.itextpdf.text.Document"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.itextpdf.text.pdf.PdfPCell"%>

<%@page import="com.itextpdf.text.pdf.PdfContentByte"%>
<%@page import="com.itextpdf.text.pdf.ColumnText"%>
<%@page import="com.itextpdf.text.Phrase"%>
<%@page import="com.itextpdf.text.PageSize"%>
<%@page import="com.itextpdf.text.*"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="com.itextpdf.text.Chunk"%>
<%@page import="com.itextpdf.text.Element"%>
<%@page import="com.itextpdf.text.Image"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="com.itextpdf.text.BaseColor"%>
<%@page import="com.itextpdf.text.FontFactory"%>
<%@page import="com.itextpdf.text.Font"%>
<%@page import="com.itextpdf.text.Header"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@page import="com.tacts.evidencias.facturacion.Numeros"%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.onest.oracle.DBConfData"%>
<%@page import="com.onest.oracle.DB"%>
<%@page import="com.onest.oracle.OracleDB"%>
<%@page import="com.onest.train.consultas.ConsultasQuery"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vista previa comprobante</title> 
        <!--Frame-->
        <style>
            .container {
                position: relative;
                width: 100%;
                overflow: hidden;
                padding-top: 56.25%; /* 16:9 Aspect Ratio */
            }

            .responsive-iframe {
                position: absolute;
                top: 0;
                left: 0;
                bottom: 0;
                right: 0;
                width: 100%;
                height: 100%;
                border: none;
            }
        </style>
    </head>
    <body>
        <%
            try {
                HttpSession ownsession = request.getSession();
                DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
                Integer user_nid = Integer.valueOf(ownsession.getAttribute("login.user_id_number").toString());//ID de Usuario
                String cve = (String) ownsession.getAttribute("cbdivcuenta");
                OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
                ConsultasQuery fac = new ConsultasQuery();
                NumberFormat formatter = new DecimalFormat("#0.00");   

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
                
                String serie = request.getParameter("serie");
                String folio = request.getParameter("folio");
                String tipo = request.getParameter("tipo");

                String suscliente_id = request.getParameter("cliente_id");
                String rfc = request.getParameter("rfc");
                String razon_social = request.getParameter("razon_social"); 
                String usocfdi_id = request.getParameter("usocfdi_id");
                String correos = request.getParameter("correos");
                String tipo_descripcion = request.getParameter("tipo_descripcion");
                //String fecha_emision = request.getParameter("fecha_emision");
                String hora_emision = request.getParameter("hora_emision");
                String comprobante_id = request.getParameter("comprobante_id");
                String documento_id = request.getParameter("documento_id");
                String regimen_id = request.getParameter("regimen_id");
                String metodo_id = request.getParameter("metodo_id");
                String forma_id = request.getParameter("forma_id");
                String moneda_id = request.getParameter("moneda_id");
                String cambio = request.getParameter("tipo_cambio");
                String num_orden = request.getParameter("num_orden");
                String num_proveedor = request.getParameter("num_proveedor");
                String info_observaciones = request.getParameter("info_observaciones");

                //Parametros nuevos (TRA_FACTURACION):
                String impuesto_global = request.getParameter("impuesto_global");
                String subtotal_global = request.getParameter("subtotal_global");
                String retenciones_global = request.getParameter("retenciones_global");
                String total_global = request.getParameter("total_global");
                String importe_descuento_global = request.getParameter("importe_descuento_global");
                String traslados_global = request.getParameter("traslados_global");
                String tipo_factor_global = request.getParameter("tipo_factor_global");
                String tasa_global = request.getParameter("tasa_global");
                String cuota_global = request.getParameter("cuota_global");
                String tipo_impuesto_global = request.getParameter("tipo_impuesto_global");
                
                //Paarametros: Información del Ticket
                String num_ticket = request.getParameter("num_ticket");
                String importe = "";
                        
                String tipo_cambio = "";
                String numFactura = serie + "" + folio; 

                if (cambio == null) {
                    tipo_cambio = "500";
                }

                //Contadores Facturación:            
                String contConcept = request.getParameter("numConceptos");
                int numConcept = Integer.parseInt(contConcept);

                String fechaHoraProvional = part3 + "-" + part2 + "-" + part1 + "T" + hora;
                String fechaCertifSAT = "";
                String fechaEmision = "";

                String cliente_id = "";
                String cliente_desc = "";
                String cliente_rfc = "";
                String cliente_cp = "";
                String regimenIdEmisor = "";

                String suscliente_desc = razon_social;
                String suscliente_rfc = rfc;
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
                
                String path = "";
                String url_logo = "";

                float subtotal = 0;
                float traslado_iva = 0;
                float total = 0; 
                
                if (db.doDB(fac.clienteLogo(cve))) {
                    for (String[] rowL : db.getResultado()) {
                        url_logo = rowL[0];
                    }
                } else {
                    url_logo = "D:\\Servicios\\Facturacion\\Tools\\logo-tacts.png";
                }
                
                if (db.doDB(fac.clienteEmisor(cve))) {
                    for (String[] rowF : db.getResultado()) {
                        cliente_id = rowF[0];
                        cliente_desc = rowF[1];
                        cliente_rfc = rowF[2];
                        cliente_cp = rowF[3];
                        regimenIdEmisor = rowF[4];
                    }
                }

                if (db.doDB(fac.regimenFiscalSAT(regimenIdEmisor))) {
                    for (String[] rowF : db.getResultado()) {
                        regimen_desc = rowF[0] + " - " + rowF[1];
                    }
                }

                if (db.doDB(fac.fechaCertificacionSAT(serie, folio, cve))) {
                    for (String[] rowF : db.getResultado()) {
                        if (!rowF[0].equals("0")) {
                            fechaCertifSAT = rowF[0];
                        } else {
                            fechaCertifSAT = fechaHoraProvional;
                        }
                    }
                } else {
                    fechaCertifSAT = fechaHoraProvional;
                }

                if (db.doDB(fac.fechaEmision(serie, folio, cve))) {
                    for (String[] rowF : db.getResultado()) {
                        if (!rowF[0].equals("0")) {
                            fechaEmision = rowF[0].replaceAll("/", "-") + "T" + rowF[1];
                        } else {
                            fechaEmision = fechaHoraProvional;
                        }
                    }
                } else {
                    fechaEmision = fechaHoraProvional;
                }

                if (db.doDB(fac.susclienteSAT(suscliente_id, cve))) {
                    for (String[] rowF : db.getResultado()) {
                        suscliente_desc = rowF[0];
                        suscliente_rfc = rowF[1];
                        suscliente_usoCfdi = rowF[2] + " - " + rowF[3];
                    }
                }
                
                if (db.doDB(fac.certificadosDigitales(serie, folio, cve))) {
                    for (String[] rowF : db.getResultado()) {
                        if (!rowF[0].equals("0")) {
                            folio_fiscal = rowF[0];
                            no_serie_certificado_sat = rowF[1];
                            num_certificado_digital = rowF[2];
                        } else {
                            folio_fiscal = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                            no_serie_certificado_sat = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                            num_certificado_digital = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                        }
                    }
                } else {
                    folio_fiscal = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                    no_serie_certificado_sat = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                    num_certificado_digital = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                }

                if (db.doDB(fac.monedaSAT(moneda_id))) {
                    for (String[] rowF : db.getResultado()) {
                        moneda_desc = rowF[0];
                    }
                }

                if (db.doDB(fac.tipoComprobanteSAT(comprobante_id))) {
                    for (String[] rowF : db.getResultado()) {
                        comprobante_desc = rowF[0] + " - " + rowF[1];
                    }
                }

                if (db.doDB(fac.MetodoPagoSAT(metodo_id))) {
                    for (String[] rowF : db.getResultado()) {
                        metodo_pago_desc = rowF[0] + " - " + rowF[1];
                    }
                }

                if (db.doDB(fac.formaPagoSAT(forma_id))) {
                    for (String[] rowF : db.getResultado()) {
                        forma_pago_desc = rowF[0] + " - " + rowF[1];
                    }
                }

                if (db.doDB(fac.sellosDigitales(serie, folio, cve))) {
                    for (String[] rowF : db.getResultado()) {
                        if (!rowF[0].equals("0")) {
                            selloDigitalCFDI = rowF[0];
                            selloDigitalSAT = rowF[1];
                            certificacionDigital = rowF[2];
                        } else {
                            selloDigitalCFDI = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                            selloDigitalSAT = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                            certificacionDigital = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                        }
                    }
                } else {
                    selloDigitalCFDI = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                    selloDigitalSAT = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                    certificacionDigital = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                }
                
                Document documento = new Document(PageSize.LETTER, 25, 25, 25, 25); // (PageSize.LETTER, 20, 20, 20, 20);
                FileOutputStream ficheroPdf;

                try {
                    
                    if(tipo.equals("1")){
                       path = "D:\\Servicios\\Facturacion\\Tickets\\VisualizacionFacturaTickets.pdf";
                    }else{
                       path = "D:\\Servicios\\Facturacion\\Tickets\\cvd"+cve+"\\" + numFactura +".pdf";
                    }
                     
                    ficheroPdf = new FileOutputStream(path);
                    PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
                try {
                    documento.open();

                    Font espacio = FontFactory.getFont("Times New Roman", 7, Font.BOLD, BaseColor.BLACK);
                    Font espacios = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
                    Font th = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.WHITE);
                    Font fontTitututu = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
                    Font para = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.RED);
                    Font tiends = FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.RED);
                    Font encabezado0 = FontFactory.getFont("arial", 13, Font.BOLD, BaseColor.RED);
                    Font encabezado1 = FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED);
                    Font encabezado4 = FontFactory.getFont("arial", 8, Font.NORMAL, BaseColor.BLACK);
                    Font encabezado5 = FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK);
                    Font encabezado6 = FontFactory.getFont("arial", 11, Font.BOLD, BaseColor.BLACK);
                    Font encabezado8 = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
                    Font encabezado9 = FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK);
                    Font encabezado10 = FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK);
                    Font encabezado11 = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
                    Font encabezado12 = FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK);

                    Image imagen = Image.getInstance(url_logo);
                    imagen.setAbsolutePosition(5f, 670f);
                    imagen.scalePercent(25);
                    documento.add(imagen);

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
                    a.setAlignment(Element.ALIGN_RIGHT);
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
                    
                    //table2.addCell(new Paragraph("Uso de CFDI: " + suscliente_usoCfdi, encabezado4));
                    table2.addCell(new Paragraph(suscliente_rfc, encabezado4));
                    table2.addCell(new Paragraph("Numero Certificado Digital", encabezado1));
                    table2.addCell(new Paragraph("Tipo de Comprobante", encabezado1));

                    table2.addCell(new Paragraph("", encabezado4));
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

                    PdfPTable encabezadoTT = new PdfPTable(8);
                    encabezadoTT.setWidthPercentage(100);
                    
                    float[] medidaCeldasparametros02 = {0.50f, 0.55f, 0.65f, 0.50f, 1.90f, 0.80f, 0.70f, 0.60f};
                    encabezadoTT.setWidths(medidaCeldasparametros02);

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
                    col_4.setHorizontalAlignment(Element.ALIGN_LEFT);
                    encabezadoTT.addCell(col_4);

                    /*PdfPCell col_5 = new PdfPCell(new Paragraph("Unidad de Medida", th));
                    col_5.setColspan(1);
                    col_5.setBorder(Rectangle.NO_BORDER);
                    col_5.setBackgroundColor(BaseColor.RED);
                    col_5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    encabezadoTT.addCell(col_5);*/

                    PdfPCell col_6 = new PdfPCell(new Paragraph("Concepto", th));
                    col_6.setColspan(1);
                    col_6.setBorder(Rectangle.NO_BORDER);
                    col_6.setBackgroundColor(BaseColor.RED);
                    col_6.setHorizontalAlignment(Element.ALIGN_LEFT);
                    encabezadoTT.addCell(col_6);

                    PdfPCell col_7 = new PdfPCell(new Paragraph("Precio Unitario", th));
                    col_7.setColspan(1);
                    col_7.setBorder(Rectangle.NO_BORDER);
                    col_7.setBackgroundColor(BaseColor.RED);
                    col_7.setHorizontalAlignment(Element.ALIGN_LEFT);
                    encabezadoTT.addCell(col_7);

                    PdfPCell col_8 = new PdfPCell(new Paragraph("Descuento", th));
                    col_8.setColspan(1);
                    col_8.setBorder(Rectangle.NO_BORDER);
                    col_8.setBackgroundColor(BaseColor.RED);
                    col_8.setHorizontalAlignment(Element.ALIGN_LEFT);
                    encabezadoTT.addCell(col_8);

                    PdfPCell col_9 = new PdfPCell(new Paragraph("Importe", th));
                    col_9.setColspan(1);
                    col_9.setBorder(Rectangle.NO_BORDER);
                    col_9.setBackgroundColor(BaseColor.RED);
                    col_9.setHorizontalAlignment(Element.ALIGN_LEFT);
                    encabezadoTT.addCell(col_9);
                    documento.add(encabezadoTT);

                    //SECCIÓN: RELACIÓN CONCEPTOS 
                    if (numConcept > 0) {
                        for (int i = 0; i < numConcept; i++) {
                            String comentarios = request.getParameter("comentarios[" + i + "]");
                            String rel_clvsat_id = request.getParameter("rel_clvsat_id[" + i + "]");
                            String unidad_sat_id = request.getParameter("unidad_sat_id[" + i + "]");
                            String concepto_id = request.getParameter("concepto_id[" + i + "]");
                            String concepto_desc = request.getParameter("concepto_desc[" + i + "]");
                            String cantidad = request.getParameter("cantidad[" + i + "]");
                            String precio_unitario = request.getParameter("precio_unitario[" + i + "]");
                            String importe_descuento = request.getParameter("importe_descuento[" + i + "]");
                            importe = request.getParameter("importe[" + i + "]");

                            subtotal += Float.parseFloat(precio_unitario);
                            traslado_iva = (subtotal * 0.16f); 
                            total = (subtotal + traslado_iva);
                            
                            PdfPTable parametros = new PdfPTable(8);
                            parametros.setWidthPercentage(100);
                            float[] medidaCeldasparametros = {0.50f, 0.55f, 0.65f, 0.50f, 1.90f, 0.80f, 0.70f, 0.60f};
                            parametros.setWidths(medidaCeldasparametros);
                            parametros.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                            parametros.addCell(new Paragraph(rel_clvsat_id, espacios));  //codigo
                            parametros.addCell(new Paragraph(rel_clvsat_id, espacios));      //clave sat
                            parametros.addCell(new Paragraph("E48", espacios));           //clave unidad
                            parametros.addCell(new Paragraph(cantidad, espacios));          //cantidad
                            //parametros.addCell(new Paragraph("", para));              //unidad de medida
                            parametros.addCell(new Paragraph(concepto_desc, espacios));  //concepto
                            parametros.addCell(new Paragraph("$ " + formatter.format(Float.parseFloat(precio_unitario)), espacios));     //precio unitario
                            parametros.addCell(new Paragraph("$ " + formatter.format(Float.parseFloat(importe_descuento)), espacios));              //descuento
                            parametros.addCell(new Paragraph("$ " + formatter.format(Float.parseFloat(importe)), espacios));     //importe
                            documento.add(parametros); 
                        }
                    }

                    documento.add(new Paragraph(" ", espacio));
                    
                    PdfPTable table60 = new PdfPTable(1);
                    table60.setWidthPercentage(100);
                    table60.setHorizontalAlignment(Element.ALIGN_RIGHT);

                    table60.addCell(new Paragraph("                                       Base Gravable:    $" + formatter.format(Float.parseFloat(importe)) + "             Impuesto:  002                                           Tasa         16.000000%           Importe:        $ " + formatter.format(traslado_iva), fontTitututu)); 
                    documento.add(table60);

                    documento.add(new Paragraph(" ", espacio));
                    documento.add(new Paragraph(" ", espacio));

                    PdfPTable table59 = new PdfPTable(6);
                    table59.setWidthPercentage(85);
                    table59.getDefaultCell().setBorderWidth(0f);
                    documento.add(table59);
                    
                    String importeGral = formatter.format(total);
                    String importePrincipal = importeGral.replace(".", "/"); 
                    
                    String[] sep = importePrincipal.split("/");
                    String importeEntero = sep[0];  // 000000
                    String importeDecimal = sep[1]; // .0000 

                    PdfPTable table61 = new PdfPTable(3);
                    table61.setWidthPercentage(100);
                    table61.setHorizontalAlignment(Element.ALIGN_RIGHT);        // (Element.ALIGN_RIGHT);
                    float[] medidaCeldas61 = {1.50f, 0.95f, 0.25f};
                    table61.setWidths(medidaCeldas61);
                    table61.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                    //table61.addCell(new Paragraph("(" + Numeros.cantidadConLetra(total_global) + "00/100 M.N.)", encabezado11));
                    table61.addCell(new Paragraph(Numeros.cantidadConLetra(importeEntero) + " PESOS, " + importeDecimal + "/100 MXN", encabezado11));
                    table61.addCell(new Paragraph("Subtotal", encabezado12));
                    table61.addCell(new Paragraph("$ " + formatter.format(subtotal), encabezado12));

                    table61.addCell(new Paragraph(forma_pago_desc, encabezado11));
                    table61.addCell(new Paragraph("Traslados (IVA al 16.000000%)", encabezado12));
                    table61.addCell(new Paragraph("$ " + formatter.format(traslado_iva), encabezado12));

                    table61.addCell(new Paragraph("Método de Pago: " + metodo_pago_desc, encabezado11));
                    table61.addCell(new Paragraph("Total", encabezado12));
                    table61.addCell(new Paragraph("$ " + formatter.format(total), encabezado12));

                    table61.addCell(new Paragraph("Condiciones de Pago: ", encabezado11));
                    table61.addCell(new Paragraph(" ", fontTitututu));
                    table61.addCell(new Paragraph(" ", encabezado11));

                    documento.add(table61);

                    documento.add(new Paragraph(" ", espacio));
                    documento.add(new Paragraph(" ", espacio));
                   
                    BarcodeQRCode barcodeQRCode = new BarcodeQRCode("https://verificacfdi.facturaelectronica.sat.gob.mx/default.aspx?&id=" + folio_fiscal + "&re=" + suscliente_rfc + "&rr=" + cliente_rfc + "&tt=" + formatter.format(total) + "&fe=5KUPkg==", 1000, 1000, null);
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
                    oraDB.close();
        %>
        <!--<div class="container"> 
            <iframe class="responsive-iframe" src="<%=request.getContextPath()%>/MostrarPDFTicket_FACT"></iframe>
        </div>-->
        <script src="../../lib/css/NTMS/plugins/bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="../../lib/css/NTMS/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- Menu Plugin JavaScript -->
        <script src="../../lib/css/NTMS/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
        <!--slimscroll JavaScript -->
        <script src="../../lib/css/NTMS/js/jquery.slimscroll.js"></script>    
        <%
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }

            } catch (NullPointerException e) {
                out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
                out.println("<script>window.close();</script>");
            } catch (Exception e) {
                out.println("Excepcion revise por favor! " + e);
                e.printStackTrace();
            }
        %>
    </body>
</html>