<%-- 
    Document   : ReciboPagoVistaPrevia
    Created on : 11/03/2022, 05:25:52 AM
    Author     : luis_
--%>

<%@page import="com.tacts.evidencias.facturacion.CreateDirectory"%>
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
                
                String cliente_id = "1";
                String suscliente_id = request.getParameter("cliente_id");
                String rfc = request.getParameter("rfc");
                String usocfdi_id = request.getParameter("usocfdi_id");
                String correos = request.getParameter("correos");
                String tipo_descripcion = request.getParameter("tipo_descripcion");
                //String fecha_emision = request.getParameter("fecha_emision");
                String fecha_emision = "31/01/2022";
                String hora_emision = request.getParameter("hora_emision");
                String comprobante_id = request.getParameter("comprobante_id");
                String documento_id = request.getParameter("documento_id");
                String regimen_id = request.getParameter("regimenFiscal");
                String metodo_id = request.getParameter("metodo_id");
                String forma_id = request.getParameter("forma_id");
                String condiciones_pago = request.getParameter("condiciones_pago");
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
                String tipo_cambio = "";
                String numFactura = serie + "" + folio; 
                
                if (cambio == null) {
                    tipo_cambio = "500";
                }

                //Contadores Facturación:            
                String contConcept = request.getParameter("contadorFacturasPorPagar");
                int numConcept = Integer.parseInt(contConcept);

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
                String url_logo = "";
                String path = "";

                int contador=0;
                
                if (db.doDB(fac.clienteLogo(cve))) {
                    for (String[] rowL : db.getResultado()) {
                        url_logo = rowL[0];
                    }
                } else {
                    url_logo = "D:\\Servicios\\Facturacion\\Tools\\logo-tacts.png";
                }

                if (db.doDB(fac.clientesSAT(cliente_id, cve))) {
                    for (String[] rowF : db.getResultado()) {
                        cliente_desc = rowF[0];
                        cliente_rfc = rowF[1];
                        cliente_cp = rowF[2];
                    }
                }

                if (db.doDB(fac.regimenFiscalSAT(regimen_id))) {
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
                            verficacioncfdiQr = rowF[3];
                        } else {
                            selloDigitalCFDI = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                            selloDigitalSAT = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                            certificacionDigital = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                            verficacioncfdiQr = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                        }
                    }
                } else {
                    selloDigitalCFDI = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                    selloDigitalSAT = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                    certificacionDigital = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                    verficacioncfdiQr = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                }
                
                Document documento = new Document(PageSize.LETTER, 25, 25, 25, 25); // (PageSize.LETTER, 20, 20, 20, 20);
                FileOutputStream ficheroPdf;
                
                try {
                    
                    if(tipo.equals("1")){
                       path = "D:\\Servicios\\Facturacion\\ReciboDePago\\VisualizacionReciboPago.pdf";
                    }else{
                       path = "D:\\Servicios\\Facturacion\\ReciboDePago\\cvd"+cve+"\\" + numFactura +".pdf";
                    }
                    
                    ficheroPdf = new FileOutputStream(path);
                    PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
                try {
                    documento.open();

                    Font espacio = FontFactory.getFont("Times New Roman", 7, Font.NORMAL, BaseColor.BLACK);
                    Font espacios = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
                    Font th = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.WHITE);
                    Font th0 = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
                    Font th1 = FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLUE);
                    Font para = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.DARK_GRAY);
                    Font encabezado0 = FontFactory.getFont("arial", 13, Font.BOLD, BaseColor.BLUE);
                    Font encabezado1 = FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.WHITE);
                    Font encabezado4 = FontFactory.getFont("arial", 8, Font.NORMAL, BaseColor.BLACK);
                    Font encabezado5 = FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED);
                    Font encabezado8 = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
                    Font encabezado9 = FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK);
                    Font encabezado10 = FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK);
                    Font encabezado12 = FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK);
                    
                    Font tituloSellos = FontFactory.getFont("Times New Roman", 10, Font.UNDERLINE, BaseColor.BLACK);
                    Font sellos = FontFactory.getFont("bfSong", 7, Font.NORMAL, BaseColor.BLACK);

                    Image imagen = Image.getInstance(url_logo);
                    imagen.setAbsolutePosition(25f, 670f);
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

                    Paragraph a = new Paragraph("Recibo de Pago", encabezado1);
                    a.setAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.BLUE);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(a);
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

                    Paragraph b = new Paragraph(serie + " " + folio, encabezado5);
                    b.setAlignment(Element.ALIGN_CENTER);
                    //cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(b);
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
 
                    Paragraph c = new Paragraph("Fecha", encabezado1);
                    c.setAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.BLUE);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(c);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("Lugar de expedición: 54740", encabezado9));
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
                    d.setAlignment(Element.ALIGN_CENTER);
                    //cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(d);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("",encabezado8));
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

                    Paragraph f = new Paragraph("", encabezado4);
                    f.setAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(f);
                    table.addCell(cell);
                    documento.add(table);

                    
                    PdfPTable encabezadoTT0 = new PdfPTable(2);
                    encabezadoTT0.setWidthPercentage(100);
                    float[] medidaCeldasTT0 = {1.60f, 1.40f};
                    encabezadoTT0.setWidths(medidaCeldasTT0);

                    PdfPCell colt_0 = new PdfPCell(new Paragraph("Cliente", th));
                    colt_0.setColspan(1);
                    colt_0.setBorder(Rectangle.NO_BORDER);
                    colt_0.setBackgroundColor(BaseColor.BLUE);
                    colt_0.setHorizontalAlignment(Element.ALIGN_CENTER);
                    encabezadoTT0.addCell(colt_0);

                    PdfPCell colt_1 = new PdfPCell(new Paragraph("Información Fiscal", th));
                    colt_1.setColspan(1);
                    colt_1.setBorder(Rectangle.NO_BORDER);
                    colt_1.setBackgroundColor(BaseColor.BLUE);
                    colt_1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    encabezadoTT0.addCell(colt_1); 
                    
                    documento.add(encabezadoTT0);
                    
                    
                    PdfPTable encabezadoTT0_1 = new PdfPTable(2);
                    encabezadoTT0_1.setWidthPercentage(100);
                    float[] medidaCeldasTT1 = {1.60f, 1.40f};
                    encabezadoTT0_1.setWidths(medidaCeldasTT1);
                    PdfPCell colt_2 = new PdfPCell(new Paragraph(suscliente_desc, th1));
                    colt_2.setColspan(1);
                    colt_2.setBorder(Rectangle.NO_BORDER);
                    colt_2.setBackgroundColor(BaseColor.WHITE); 
                    colt_2.setHorizontalAlignment(Element.ALIGN_LEFT);
                    encabezadoTT0_1.addCell(colt_2);
                    PdfPCell colt_3 = new PdfPCell(new Paragraph("Fecha de Timbrado: " + fechaCertifSAT, th0));
                    colt_3.setColspan(1);
                    colt_3.setBorder(Rectangle.NO_BORDER);
                    colt_3.setBackgroundColor(BaseColor.WHITE);
                    colt_3.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    encabezadoTT0_1.addCell(colt_3); 
                    PdfPCell colt_4 = new PdfPCell(new Paragraph(suscliente_rfc, th0));
                    colt_4.setColspan(1);
                    colt_4.setBorder(Rectangle.NO_BORDER);
                    colt_4.setBackgroundColor(BaseColor.WHITE); 
                    colt_4.setHorizontalAlignment(Element.ALIGN_LEFT);
                    encabezadoTT0_1.addCell(colt_4);
                    PdfPCell colt_5 = new PdfPCell(new Paragraph("No. Certificado del CSD: " + folio_fiscal, th0));
                    colt_5.setColspan(1);
                    colt_5.setBorder(Rectangle.NO_BORDER);
                    colt_5.setBackgroundColor(BaseColor.WHITE);
                    colt_5.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    encabezadoTT0_1.addCell(colt_5); 
                    PdfPCell colt_6 = new PdfPCell(new Paragraph("", th0));
                    colt_6.setColspan(1);
                    colt_6.setBorder(Rectangle.NO_BORDER);
                    colt_6.setBackgroundColor(BaseColor.WHITE); 
                    colt_6.setHorizontalAlignment(Element.ALIGN_CENTER);
                    encabezadoTT0_1.addCell(colt_6);
                    PdfPCell colt_7 = new PdfPCell(new Paragraph("No. Certificado SAT: " + no_serie_certificado_sat, th0));
                    colt_7.setColspan(1);
                    colt_7.setBorder(Rectangle.NO_BORDER);
                    colt_7.setBackgroundColor(BaseColor.WHITE);
                    colt_7.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    encabezadoTT0_1.addCell(colt_7); 
                    PdfPCell colt_8 = new PdfPCell(new Paragraph("", th0));
                    colt_8.setColspan(1);
                    colt_8.setBorder(Rectangle.NO_BORDER);
                    colt_8.setBackgroundColor(BaseColor.WHITE); 
                    colt_8.setHorizontalAlignment(Element.ALIGN_CENTER);
                    encabezadoTT0_1.addCell(colt_8);
                    PdfPCell colt_9 = new PdfPCell(new Paragraph("Folio Fiscal: " + folio_fiscal, th0));
                    colt_9.setColspan(1);
                    colt_9.setBorder(Rectangle.NO_BORDER);
                    colt_9.setBackgroundColor(BaseColor.WHITE);
                    colt_9.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    encabezadoTT0_1.addCell(colt_9); 
                    PdfPCell colt_10 = new PdfPCell(new Paragraph("", th0));
                    colt_10.setColspan(1);
                    colt_10.setBorder(Rectangle.NO_BORDER);
                    colt_10.setBackgroundColor(BaseColor.WHITE); 
                    colt_10.setHorizontalAlignment(Element.ALIGN_CENTER);
                    encabezadoTT0_1.addCell(colt_10);
                    PdfPCell colt_11 = new PdfPCell(new Paragraph("Tipo de Comprobante: P - Pago", th0));
                    colt_11.setColspan(1);
                    colt_11.setBorder(Rectangle.NO_BORDER);
                    colt_11.setBackgroundColor(BaseColor.WHITE);
                    colt_11.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    encabezadoTT0_1.addCell(colt_11); 
	            documento.add(encabezadoTT0_1);

                    documento.add(new Paragraph(" "));
                    
                    PdfPTable encabezadoTT = new PdfPTable(7);
                    encabezadoTT.setWidthPercentage(100);

                    float[] medidaCeldasTT2 = {0.10f, 0.30f, 0.10f, 0.10f, 0.10f, 0.10f, 0.10f};
                    encabezadoTT.setWidths(medidaCeldasTT2);
                    
                    PdfPCell col_1 = new PdfPCell(new Paragraph("Serie y Folio", th));
                    col_1.setColspan(1);
                    col_1.setBorder(Rectangle.NO_BORDER);
                    col_1.setBackgroundColor(BaseColor.BLUE);
                    encabezadoTT.addCell(col_1);

                    PdfPCell col_2 = new PdfPCell(new Paragraph("UUID", th));
                    col_2.setColspan(1);
                    col_2.setBorder(Rectangle.NO_BORDER);
                    col_2.setBackgroundColor(BaseColor.BLUE);
                    encabezadoTT.addCell(col_2);

                    PdfPCell col_3 = new PdfPCell(new Paragraph("Moneda", th));
                    col_3.setColspan(1);
                    col_3.setBorder(Rectangle.NO_BORDER);
                    col_3.setBackgroundColor(BaseColor.BLUE);
                    encabezadoTT.addCell(col_3);

                    PdfPCell col_4 = new PdfPCell(new Paragraph("Parcialidad", th));
                    col_4.setColspan(1);
                    col_4.setBorder(Rectangle.NO_BORDER);
                    col_4.setBackgroundColor(BaseColor.BLUE);
                    col_4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    encabezadoTT.addCell(col_4);

                    PdfPCell col_5 = new PdfPCell(new Paragraph("Saldo Anterior", th));
                    col_5.setColspan(1);
                    col_5.setBorder(Rectangle.NO_BORDER);
                    col_5.setBackgroundColor(BaseColor.BLUE);
                    col_5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    encabezadoTT.addCell(col_5);

                    PdfPCell col_6 = new PdfPCell(new Paragraph("Importe Pagado", th));
                    col_6.setColspan(1);
                    col_6.setBorder(Rectangle.NO_BORDER);
                    col_6.setBackgroundColor(BaseColor.BLUE);
                    col_6.setHorizontalAlignment(Element.ALIGN_CENTER);
                    encabezadoTT.addCell(col_6);

                    PdfPCell col_7 = new PdfPCell(new Paragraph("Saldo Pendiente", th));
                    col_7.setColspan(1);
                    col_7.setBorder(Rectangle.NO_BORDER);
                    col_7.setBackgroundColor(BaseColor.BLUE);
                    col_7.setHorizontalAlignment(Element.ALIGN_CENTER);
                    encabezadoTT.addCell(col_7);
                    documento.add(encabezadoTT);

                    //SECCIÓN: RELACIÓN CONCEPTOS 
                    if (numConcept > 0) {
                        contador++;
                        
                        for (int i = 0; i < numConcept; i++) {
                            
                            String id_documento = request.getParameter("id_documento[" + i + "]");      
                            String serieFactOld = request.getParameter("serieFactOld[" + i + "]");            
                            String folioFactOld = request.getParameter("folioFactOld[" + i + "]");            
                            String moneda_dr = request.getParameter("moneda_dr[" + i + "]");       
                            String metodo_de_pago_dr = request.getParameter("metodo_de_pago_dr[" + i + "]");
                            String numParcialidad = request.getParameter("numParcialidad[" + i + "]");  
                            String saldoAnterior = request.getParameter("saldoAnterior[" + i + "]");    
                            String saldoPagado = request.getParameter("saldoPagado[" + i + "]");   
                            String saldoPendiente = request.getParameter("saldoPendiente[" + i + "]"); 
                            String importeSaldoInsoluto = request.getParameter("importeSaldoInsoluto[" + i + "]");
                            String equivalenciaDr = request.getParameter("equivalenciaDr[" + i + "]");     
                            String ObjetoImpDr = request.getParameter("ObjetoImpDr[" + i + "]"); 
                            
                            PdfPTable parametros = new PdfPTable(7);
                            parametros.setWidthPercentage(100);
                            float[] medidaCeldasTT3 = {0.10f, 0.30f, 0.10f, 0.10f, 0.10f, 0.10f, 0.10f};
                            parametros.setWidths(medidaCeldasTT3);
                            parametros.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                            parametros.addCell(new Paragraph(serieFactOld + "" + folioFactOld, espacios));   //Serie y Folio
                            parametros.addCell(new Paragraph(id_documento, para));    //UUID
                            parametros.addCell(new Paragraph(moneda_dr, para));     //Moneda
                            parametros.addCell(new Paragraph(numParcialidad, para));         //Parcialidad
                            parametros.addCell(new Paragraph("$ " + saldoAnterior, para));            //Saldo Anterior
                            parametros.addCell(new Paragraph("$ " + saldoPagado, para));       //Importe Pagado 
                            parametros.addCell(new Paragraph("$ " + saldoPendiente, para));      //Saldo Pendiente
                            documento.add(parametros);
                        }
                    }

                    documento.add(new Paragraph(" ", espacio));
                    documento.add(new Paragraph(" ", espacio));
                     
                    documento.add(new Paragraph("        Cuenta Ordenante                          Cuenta Beneficiario    ", espacio));
                    documento.add(new Paragraph("_________________________________________________________________________", espacio));
                    documento.add(new Paragraph("Cuenta: 02180002355350091                 Cuenta: 062180113410080795     ", espacio));
                    documento.add(new Paragraph("Operación:                                               RFC: BAF950102JP5   ", espacio));
                    documento.add(new Paragraph("BNM840515VB1 Banamex                                                     ", espacio));
                    
                    documento.add(new Paragraph(" ", espacio));
                    
                    BarcodeQRCode barcodeQRCode = new BarcodeQRCode(verficacioncfdiQr, 1000, 1000, null);
                    Image codeQrImage = barcodeQRCode.getImage();
                    codeQrImage.scaleAbsolute(100, 100); 
                    
                    PdfPTable tableF = new PdfPTable(3);
                    tableF.setWidthPercentage(100);
                    PdfPCell cellF = new PdfPCell(new Phrase(" "));
                    cellF.setColspan(3);
                    cellF.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellF.setBorder(Rectangle.NO_BORDER);
                    tableF.addCell(cellF);
                    cellF = new PdfPCell(codeQrImage);
                    cellF.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellF.setRowspan(3);
                    cellF.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableF.addCell(cellF);
                    tableF.addCell("              Forma de Pago");
                    cellF = new PdfPCell(new Phrase("Monto del Pago"));
                    cellF.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tableF.addCell(cellF);
                    cellF = new PdfPCell(new Phrase("03-Transferencia electrónica de fondos", encabezado12));
                    cellF.setPadding(5);
                    cellF.setUseAscender(true);
                    cellF.setUseDescender(true);
                    cellF.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tableF.addCell(cellF);
                    cellF = new PdfPCell();
                    cellF.setPadding(5);
                    cellF.setUseAscender(true);
                    cellF.setUseDescender(true);
                    Paragraph p = new Paragraph("$60,000.00 MXN", encabezado12);
                    p.setAlignment(Element.ALIGN_CENTER);
                    cellF.addElement(p);
                    tableF.addCell(cellF);
                    
                    cellF = new PdfPCell(new Phrase("Importe total en letra:"));
                    cellF.setPadding(5);
                    cellF.setUseAscender(true);
                    cellF.setUseDescender(true);
                    cellF.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tableF.addCell(cellF);
                    cellF = new PdfPCell();
                    cellF.setPadding(5);
                    cellF.setUseAscender(true);
                    cellF.setUseDescender(true);
                    Paragraph q = new Paragraph("sesenta mil Pesos 00/100 M.N",encabezado12);
                    q.setAlignment(Element.ALIGN_LEFT);
                    cellF.addElement(q);
                    tableF.addCell(cellF);
                    documento.add(tableF);
                    
                    documento.add(new Paragraph(" ", espacio));
                    documento.add(new Paragraph(" ", espacio));
                    
                    documento.add(new Paragraph("Sello Digital del CFDI                                                                                                                                                                    ", tituloSellos));
                    documento.add(new Paragraph(selloDigitalCFDI, sellos));
                    documento.add(new Paragraph(" ", espacio));
                    
                    documento.add(new Paragraph("Sello Digital del SAT                                                                                                                                                                     ", tituloSellos));
                    documento.add(new Paragraph(selloDigitalSAT, sellos));
                    documento.add(new Paragraph(" ", espacio));
                    
                    documento.add(new Paragraph("Cadena Original del Complemento de Certificación Digital del SAT                                                                                            ", tituloSellos));
                    documento.add(new Paragraph(certificacionDigital, sellos));
                    documento.add(new Paragraph(" ", espacio));
                    
                    documento.close();
                    oraDB.close();
        %>
        <!--<div class="container"> 
            <iframe class="responsive-iframe" src="<%=request.getContextPath()%>/MostrarPDF_COMPL"></iframe>
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
