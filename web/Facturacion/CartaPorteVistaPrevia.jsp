<%-- 
    Document   : CartaPorteVistaPrevia
    Created on : 8/03/2022, 05:13:48 PM
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
        <title>Vista previa carta porte</title> 
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
                
                //Parametros: Lugar de Embarque
                String numcp = request.getParameter("check_lugarEmbarque");
                String lugarDeEmbarque = request.getParameter("lugarDeEmbarque");
                String lugEmb_nombre = request.getParameter("lugEmb_nombre");
                String lugEmb_rfc = request.getParameter("lugEmb_rfc");
                String lugEmb_calle = request.getParameter("lugEmb_calle");	
                String lugEmb_noInterior = request.getParameter("lugEmb_noInterior");	
                String lugEmb_noExterior = request.getParameter("lugEmb_noExterior");	
                String lugEmb_codigoPostal = request.getParameter("lugEmb_codigoPostal");	
                String lugEmb_estado = request.getParameter("lugEmb_estado");                
                String lugEmb_municipio = request.getParameter("lugEmb_municipio");	        
                String lugEmb_colonia = request.getParameter("lugEmb_colonia");	        
                String lugEmb_referencia = request.getParameter("lugEmb_referencia");	
                String lugEmb_fechaHoraSalidaLlegada = request.getParameter("lugEmb_fechaHoraSalidaLlegada");	
                String lugEmb_localidad = request.getParameter("lugEmb_localidad");   
                String lugEmb_idUbicacion = request.getParameter("lugEmb_idUbicacion");        
                
                 
                //Parametros: Lugar de Destino
                String check_lugarDestinatario = request.getParameter("check_lugarDestinatario");
                String lugarDestinatario = request.getParameter("lugarDestinatario");
                String dest_nombre = request.getParameter("dest_nombre");	
                String dest_rfc = request.getParameter("dest_rfc");	
                String dest_calle = request.getParameter("dest_calle");	
                String dest_noInterior = request.getParameter("dest_noInterior");	
                String dest_noExterior = request.getParameter("dest_noExterior");	
                String dest_codigoPostal = request.getParameter("dest_codigoPostal");	
                String dest_estado = request.getParameter("dest_estado");	                 
                String dest_municipio = request.getParameter("dest_municipio");	        
                String dest_colonia = request.getParameter("dest_colonia");	                
                String dest_referencia = request.getParameter("dest_referencia");	 
                String dest_fechaHoraSalidaLlegada = request.getParameter("dest_fechaHoraSalidaLlegada");	
                String dest_distancia = request.getParameter("dest_distancia");
                String dest_localidad = request.getParameter("dest_localidad");   
                String dest_idUbicacion = request.getParameter("dest_idUbicacion");  
                
                 
                //Parametros: Lugar de Entrega
                String check_lugarEntrega = request.getParameter("check_lugarEntrega");
                String lugarEntrega = request.getParameter("lugarEntrega");
                String lugEnt_contacto = request.getParameter("lugEnt_contacto");	
                String lugEnt_nombre = request.getParameter("lugEnt_nombre");
                String lugEnt_rfc = request.getParameter("lugEnt_rfc");	
                String lugEnt_calle = request.getParameter("lugEnt_calle");	
                String lugEnt_noInterior = request.getParameter("lugEnt_noInterior");	
                String lugEnt_noExterior = request.getParameter("lugEnt_noExterior");	
                String lugEnt_codigoPostal = request.getParameter("lugEnt_codigoPostal");	
                String lugEnt_estado = request.getParameter("lugEnt_estado");	          
                String lugEnt_municipio = request.getParameter("lugEnt_municipio");	           
                String lugEnt_colonia = request.getParameter("lugEnt_colonia");	          
                String lugEnt_referencia = request.getParameter("lugEnt_referencia");
                String lugEnt_pais = request.getParameter("lugEnt_pais");	         // ------> revisar nomeclatura - pais          
                String lugEnt_tipoTransporte = request.getParameter("lugEnt_tipoTransporte");	
                String lugEnt_fechaHoraSalidaLlegada = request.getParameter("lugEnt_fechaHoraSalidaLlegada");	
                String lugEnt_distancia = request.getParameter("lugEnt_distancia");
                String lugEnt_localidad = request.getParameter("lugEnt_localidad");  
                String lugEnt_idUbicacion = request.getParameter("lugEnt_idUbicacion");       
                
                
                //Parametros: Mercancía
                String unidad_peso_cp = request.getParameter("unidad_peso_cp");
                String mercancia_es_peligroso_cp = request.getParameter("mercancia_es_peligroso_cp");
                String numpedimento_cp = request.getParameter("numpedimento_cp");
                String moneda_cp = request.getParameter("moneda_cp");                        
                
                                
                //Parametros: Autotransporte
                String perm_sct = request.getParameter("perm_sct");                  
                String no_permismo_sct = request.getParameter("no_permismo_sct");    
                String config_vehicular = request.getParameter("config_vehicular");  
                String placa_vm = request.getParameter("placa_vm");
                String anio_modelovm = request.getParameter("anio_modelovm");
                String placa_remolque = request.getParameter("placa_remolque");
                String subtipo_remolque = request.getParameter("tipo_remolque");   
                String aseguradora_civil = request.getParameter("aseguradora_civil");
                String poliza_civil = request.getParameter("poliza_civil");
                String tipo_figura = request.getParameter("tipo_figura");            
                String rfc_figura = request.getParameter("rfc_figura");
                String num_licencia = request.getParameter("num_licencia");
                String nombre_figura = request.getParameter("nombre_figura");
                
                //Parametros: Chofer Autotransporte
                String autChofer_calle = request.getParameter("autChofer_calle");  
                String autChofer_numero_exterior = request.getParameter("autChofer_noInterior"); 
                String autChofer_numero_interior = request.getParameter("autChofer_noExterior"); 
                String autChofer_colonia = request.getParameter("autChofer_colonia"); 
                String autChofer_localidad = request.getParameter("autChofer_localidad");       
                String autChofer_municipio = request.getParameter("autChofer_municipio");       
                String autChofer_referencia = request.getParameter("autChofer_referencia");      
                String autChofer_estado = request.getParameter("autChofer_estado");          
                String autChofer_pais = request.getParameter("autChofer_pais");            
                String autChofer_codigo_postal = request.getParameter("autChofer_codigoPostal");   
                
                //Parametros: Facturación CFDI
                String comprobante_id = request.getParameter("comprobante_id");
                String forma_id = request.getParameter("forma_id");
                String metodo_id = request.getParameter("metodo_id");
                String moneda_id = request.getParameter("moneda_id");
                
                String numFactura = serie + "" + folio;
                String fechaHoraProvional = part3 + "-" + part2 + "-" + part1 + "T" + hora;
                String fechaCertifSAT = "";
                String fechaEmision = "";
                
                String moneda_desc = "";
                String comprobante_desc = "";
                String metodo_pago_desc = "";
                String forma_pago_desc = "";
                String rfc_cliente = "TRA170411AX7";
                String rfc_tacts = "VLM970313NW5";
                String url_logo = "";
                String path = "";
                
                String folio_fiscal = "";
                String no_serie_certificado_sat = "";
                String num_certificado_digital = "";
                
                String selloDigitalCFDI = "";
                String selloDigitalSAT = "";
                String certificacionDigital = "";
                
                float subtotal = 0;
                float traslado_iva = 0; 
                float retencion = 0;
                float total = 0;
                
                //Contadores Facturación:            
                String contConcept = request.getParameter("numConceptos");
                String contCartaPorte = request.getParameter("numCartaPorte");
                int numConcept = Integer.parseInt(contConcept);
                int numCartaPorte = Integer.parseInt(contCartaPorte);
                
                float distancia1 = Float.parseFloat(dest_distancia);
                float distancia2 = Float.parseFloat(lugEnt_distancia);
                float distanciaTotal  = distancia1 + distancia2;
                int contadorMercancias = 0;
                
                if (db.doDB(fac.clienteLogo(cve))) {
                    for (String[] rowL : db.getResultado()) {
                        url_logo = rowL[0];
                    }
                } else {
                    url_logo = "D:\\Servicios\\Facturacion\\Tools\\logo-tacts.png";
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

                if (db.doDB(fac.tipoComprobanteSAT(comprobante_id))) {
                    for (String[] rowF : db.getResultado()) {
                        comprobante_desc = rowF[0] + " - " + rowF[1];
                    }
                }

                if (db.doDB(fac.formaPagoSAT(forma_id))) {
                    for (String[] rowF : db.getResultado()) {
                        forma_pago_desc = rowF[0] + " - " + rowF[1];
                    }
                }

                if (db.doDB(fac.MetodoPagoSAT(metodo_id))) {
                    for (String[] rowF : db.getResultado()) {
                        metodo_pago_desc = rowF[0] + " - " + rowF[1];
                    }
                }

                if (db.doDB(fac.monedaSAT(moneda_id))) {
                    for (String[] rowF : db.getResultado()) {
                        moneda_desc = rowF[0];
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
                       path = "D:\\Servicios\\Facturacion\\CartaPorte\\VisualizacionCartaPorte.pdf";
                    }else{
                       path = "D:\\Servicios\\Facturacion\\CartaPorte\\cvd"+cve+"\\" + numFactura +".pdf"; 
                    }
                    
                    ficheroPdf = new FileOutputStream(path);
                    PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
                try {
                    documento.open();
                    
                    Font espacio = FontFactory.getFont("Times New Roman", 7, Font.BOLD, BaseColor.BLACK);
                    Font conceptos = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
                    Font th = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.WHITE);
                    Font detalle = FontFactory.getFont("arial", 8, Font.NORMAL, BaseColor.BLACK);
                    Font encabezado1 = FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK);
                    Font encabezadofN = FontFactory.getFont("Times New Roman", 9, Font.BOLD, BaseColor.BLACK);
                    Font encabezado2 = FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
                    Font encabezado3 = FontFactory.getFont("arial", 6, Font.NORMAL, BaseColor.BLACK);
                    Font encabezado4 = FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK);
                    Font body = FontFactory.getFont("arial", 7, Font.NORMAL, BaseColor.BLACK);
                    Font encabezado11 = FontFactory.getFont("arial", 7, Font.BOLD, BaseColor.BLACK);
                    Font encabezado12 = FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK);
                    
                    Image imagen = Image.getInstance(url_logo);
                    imagen.scalePercent(100);
                    imagen.setAbsolutePosition(20f, 620f);
                    documento.add(imagen);
                    
                    PdfPTable table = new PdfPTable(3);
                    
                    table.setWidthPercentage(100);
                    table.setHorizontalAlignment(Element.ALIGN_RIGHT);         
                    float[] sizeT = {0.70f, 1.70f, 1.50f};
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
                    //table.addCell(im2);
                    
                    cell = new PdfPCell(new Phrase("LINEA DE TRANSPORTE SA DE CV",encabezado1));
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
                    
                    Paragraph a = new Paragraph("Carta Porte " + folio,encabezado1);
                    a.setAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(a);
                    table.addCell(cell);
                    
                    
                    cell = new PdfPCell(new Phrase(rfc_cliente,encabezado4));
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
                    
                    Paragraph b = new Paragraph("FOLIO FISCAL (UUID)",encabezado2);
                    b.setAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(b);
                    table.addCell(cell);
                    
                    
                    cell = new PdfPCell(new Phrase("RÉGIMEN FISCAL: 601 - General de Ley de Personas Morales",encabezado3));
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
                    
                    Paragraph c = new Paragraph(folio_fiscal,encabezado4);
                    c.setAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(c);
                    table.addCell(cell);
                    
                    
                    cell = new PdfPCell(new Phrase("ISLA DEL SOCORRO, MANZANA 39/LOTE 16, Villa Esmeralda, 54910, Tultitlpan de Mariano Escobedo, Tultitlán, Estado de México, México",encabezado3));
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
                    
                    Paragraph d = new Paragraph("NO. DE SERIE DEL CERTIFICADO DEL SAT",encabezado2);
                    d.setAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(d);
                    table.addCell(cell);
                    
                    cell = new PdfPCell(new Phrase("CLIENTE",encabezado1));
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
                    
                    Paragraph e = new Paragraph(no_serie_certificado_sat,encabezado4);
                    e.setAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(e);
                    table.addCell(cell);
                    
                    cell = new PdfPCell(new Phrase("VF OUTDOOR MEXICO, S. DE R.L DE C.V",encabezado4));
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
                    
                    Paragraph f = new Paragraph("NO. DE SERIE DEL CERTIFICADO DEL EMISOR",encabezado2);
                    f.setAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(f);
                    table.addCell(cell);
                    
                    cell = new PdfPCell(new Phrase("VLM970313NW5",encabezado4));
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
                    
                    Paragraph g = new Paragraph(num_certificado_digital,encabezado4);
                    g.setAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(g);
                    table.addCell(cell);
                    
                    cell = new PdfPCell(new Phrase("USO CFDI: G03 - Gastos en General",encabezado3));
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
                    
                    Paragraph h = new Paragraph("FECHA Y Y HORA DE CERTIFICACIÓN",encabezado2);
                    h.setAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(h);
                    table.addCell(cell);
                    
                    cell = new PdfPCell(new Phrase("MARIANO ESCOBEDO, 476/PISO 9 L.14, Anzures, 11590, Ciudad de México, Miguel Hidalgo, Ciudad de México, México",encabezado3));
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
                    
                    Paragraph i = new Paragraph(fechaCertifSAT,encabezado4);
                    i.setAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(i);
                    table.addCell(cell);
                    
                    cell = new PdfPCell(new Phrase("",espacio));
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
                    
                    Paragraph j = new Paragraph("RFC PROVEEDOR DE CERTIFICACIÓN",encabezado2);
                    j.setAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(j);
                    table.addCell(cell);
                    
                    cell = new PdfPCell(new Phrase("",espacio));
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
                    
                    Paragraph k = new Paragraph("SVT110323827",encabezado4);
                    k.setAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(k);
                    table.addCell(cell);
                    
                    cell = new PdfPCell(new Phrase("",espacio));
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
                    
                    Paragraph l = new Paragraph("FECHA Y HORA DE EMISIÓN DE CFDI",encabezado2);
                    l.setAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(l);
                    table.addCell(cell);
                    
                    cell = new PdfPCell(new Phrase("",encabezado3));
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
                    
                    Paragraph m = new Paragraph(fechaEmision,encabezado4);
                    m.setAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(m);
                    table.addCell(cell);
                    
                    cell = new PdfPCell(new Phrase("",espacio));
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
                    
                    Paragraph n = new Paragraph("LUGAR DE EXPEDICIÓN",encabezado2);
                    n.setAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(n);
                    table.addCell(cell);
                    
                    cell = new PdfPCell(new Phrase("",espacio));
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
                    
                    Paragraph p = new Paragraph("54910",encabezado4);
                    p.setAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(p);
                    table.addCell(cell);
                    documento.add(table);
                    
                    
                    PdfPTable cartaporte = new PdfPTable(1);
                    cartaporte.setWidthPercentage(100);
                    PdfPCell titulo_1 = new PdfPCell(new Paragraph("CARTA PORTE 2.0", th));
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
                    //detalle1_1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                    //detalle1_1.getDefaultCell().setBorderColorBottom(BaseColor.RED);
                    //detalle1_1.getDefaultCell().setBorderColorTop(new BaseColor(255, 0, 0));
                    detalle1_1.addCell(new Paragraph("Transporte Internacional", conceptos));
                    detalle1_1.addCell(new Paragraph(lugEnt_tipoTransporte, body)); 
                    detalle1_1.addCell(new Paragraph("Total Distancia Recorrída", conceptos));
                    detalle1_1.addCell(new Paragraph("" + distanciaTotal, body));
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
                    detalle2_1.addCell(new Paragraph("Origen", body)); 
                    detalle2_1.addCell(new Paragraph("RFC Remitente o Destinatario", conceptos));
                    detalle2_1.addCell(new Paragraph(lugEmb_rfc, body));
                    documento.add(detalle2_1);
                    
                    PdfPTable detalle2_2 = new PdfPTable(4);
                    detalle2_2.setWidthPercentage(100);
                    float[] size2 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle2_2.setWidths(size2);
                    detalle2_2.addCell(new Paragraph("Nombre de Remitente o Destinatario", conceptos));
                    detalle2_2.addCell(new Paragraph(lugEmb_nombre, body)); 
                    detalle2_2.addCell(new Paragraph("Fecha Hora Salida Llegada", conceptos));
                    detalle2_2.addCell(new Paragraph(lugEmb_fechaHoraSalidaLlegada, body));
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
                    detalle3_1.addCell(new Paragraph(lugEmb_calle, body)); 
                    detalle3_1.addCell(new Paragraph("No. Exterior", conceptos));
                    detalle3_1.addCell(new Paragraph(lugEmb_noExterior, body));
                    documento.add(detalle3_1);
                    
                    PdfPTable detalle3_2 = new PdfPTable(4);
                    detalle3_2.setWidthPercentage(100);
                    float[] size5 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle3_2.setWidths(size5);
                    detalle3_2.addCell(new Paragraph("No. Interior", conceptos));
                    detalle3_2.addCell(new Paragraph(lugEmb_noInterior, body)); 
                    detalle3_2.addCell(new Paragraph("Colonia", conceptos));
                    detalle3_2.addCell(new Paragraph(lugEmb_colonia, body));
                    documento.add(detalle3_2); 
                   
                    PdfPTable detalle3_3 = new PdfPTable(4);
                    detalle3_3.setWidthPercentage(100);
                    float[] size6 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle3_3.setWidths(size6);
                    detalle3_3.addCell(new Paragraph("Localidad", conceptos));
                    detalle3_3.addCell(new Paragraph(lugEmb_localidad, body)); 
                    detalle3_3.addCell(new Paragraph("Municipio", conceptos));
                    detalle3_3.addCell(new Paragraph(lugEmb_municipio, body));
                    documento.add(detalle3_3);
                    
                    PdfPTable detalle3_4 = new PdfPTable(4);
                    detalle3_4.setWidthPercentage(100);
                    float[] size7 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle3_4.setWidths(size7);
                    detalle3_4.addCell(new Paragraph("Estado", conceptos));
                    detalle3_4.addCell(new Paragraph(lugEmb_estado, body)); 
                    detalle3_4.addCell(new Paragraph("País", conceptos));
                    detalle3_4.addCell(new Paragraph("MEX - México", body));
                    documento.add(detalle3_4);
                    
                    PdfPTable detalle3_5 = new PdfPTable(4);
                    detalle3_5.setWidthPercentage(100);
                    float[] size8 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle3_5.setWidths(size8);
                    detalle3_5.addCell(new Paragraph("CP", conceptos));
                    detalle3_5.addCell(new Paragraph(lugEmb_codigoPostal, body)); 
                    detalle3_5.addCell(new Paragraph("", conceptos));
                    detalle3_5.addCell(new Paragraph("", body));
                    documento.add(detalle3_5);
                    
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
                    float[] size8_a = {0.90f, 1.10f, 0.90f, 1.10f}; 
                    detalle4_1.setWidths(size8_a);
                    detalle4_1.addCell(new Paragraph("Tipo de Ubicación", conceptos));
                    detalle4_1.addCell(new Paragraph("Destino", detalle)); 
                    detalle4_1.addCell(new Paragraph("RFC Remitente o Destinatario", conceptos));
                    detalle4_1.addCell(new Paragraph("" + dest_rfc, body));
                    documento.add(detalle4_1);
                    
                    PdfPTable detalle4_2 = new PdfPTable(4);
                    detalle4_2.setWidthPercentage(100);
                    float[] size9 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle4_2.setWidths(size9);
                    detalle4_2.addCell(new Paragraph("Nombre Remitente o Destinatario", conceptos));
                    detalle4_2.addCell(new Paragraph(dest_nombre, body)); 
                    detalle4_2.addCell(new Paragraph("Fecha Hora Salida Llegada", conceptos));
                    detalle4_2.addCell(new Paragraph("" + dest_fechaHoraSalidaLlegada, body));
                    documento.add(detalle4_2); 
                   
                    PdfPTable detalle4_3 = new PdfPTable(4);
                    detalle4_3.setWidthPercentage(100);
                    float[] size10 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle4_3.setWidths(size10);
                    detalle4_3.addCell(new Paragraph("Distancia Recorrida", conceptos));
                    detalle4_3.addCell(new Paragraph("" + distancia1, body)); 
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
                    detalle5_1.addCell(new Paragraph("Calle", conceptos));
                    detalle5_1.addCell(new Paragraph(dest_calle, body)); 
                    detalle5_1.addCell(new Paragraph("No. Exterior", conceptos));
                    detalle5_1.addCell(new Paragraph(dest_noExterior, body));
                    documento.add(detalle5_1);
                    
                    PdfPTable detalle5_2 = new PdfPTable(4);
                    detalle5_2.setWidthPercentage(100);
                    float[] size13 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle5_2.setWidths(size13);
                    detalle5_2.addCell(new Paragraph("No. Interior", conceptos));
                    detalle5_2.addCell(new Paragraph(dest_noInterior, body)); 
                    detalle5_2.addCell(new Paragraph("Colonia", conceptos));
                    detalle5_2.addCell(new Paragraph(dest_colonia, body));
                    documento.add(detalle5_2); 
                   
                    PdfPTable detalle5_3 = new PdfPTable(4);
                    detalle5_3.setWidthPercentage(100);
                    float[] size14 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle5_3.setWidths(size14);
                    detalle5_3.addCell(new Paragraph("Localidad", conceptos));
                    detalle5_3.addCell(new Paragraph(dest_localidad, body)); 
                    detalle5_3.addCell(new Paragraph("Municipio", conceptos));
                    detalle5_3.addCell(new Paragraph(dest_municipio, detalle));
                    documento.add(detalle5_3);
                    
                    
                    PdfPTable detalle5_4 = new PdfPTable(4);
                    detalle5_4.setWidthPercentage(100);
                    float[] size14_w = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle5_4.setWidths(size14_w);
                    detalle5_4.addCell(new Paragraph("Estado", conceptos));
                    detalle5_4.addCell(new Paragraph(dest_estado, body)); 
                    detalle5_4.addCell(new Paragraph("País", conceptos));
                    detalle5_4.addCell(new Paragraph("MEX - México", detalle));
                    documento.add(detalle5_4);
                    
                    PdfPTable detalle5_5 = new PdfPTable(4);
                    detalle5_5.setWidthPercentage(100);
                    float[] size14_d = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle5_5.setWidths(size14_d);
                    detalle5_5.addCell(new Paragraph("CP", conceptos));
                    detalle5_5.addCell(new Paragraph(dest_codigoPostal, body)); 
                    detalle5_5.addCell(new Paragraph("", conceptos));
                    detalle5_5.addCell(new Paragraph("", detalle));
                    documento.add(detalle5_5);
                    
                    PdfPTable space5 = new PdfPTable(1);
                    space5.setWidthPercentage(100);
                    PdfPCell white_5 = new PdfPCell(new Paragraph("", th));
                    white_5.setColspan(1);
                    white_5.setBorder(Rectangle.NO_BORDER);
                    white_5.setBackgroundColor(BaseColor.WHITE);
                    white_5.setHorizontalAlignment(1); 
                    space5.addCell(white_5);
                    documento.add(space5);
                    
                    /***************************************************/
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
                    detalle05_1.addCell(new Paragraph(lugEnt_rfc, body));
                    documento.add(detalle05_1);
                    
                    PdfPTable detalle05_2 = new PdfPTable(4);
                    detalle05_2.setWidthPercentage(100);
                    float[] size010 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle05_2.setWidths(size010);
                    detalle05_2.addCell(new Paragraph("Nombre Remitente o Destinatario", conceptos));
                    detalle05_2.addCell(new Paragraph(lugEnt_nombre, body)); 
                    detalle05_2.addCell(new Paragraph("Fecha Hora Salida Llegada", conceptos));
                    detalle05_2.addCell(new Paragraph(lugEnt_fechaHoraSalidaLlegada, body));
                    documento.add(detalle05_2); 
                   
                    PdfPTable detalle05_3 = new PdfPTable(4);
                    detalle05_3.setWidthPercentage(100);
                    float[] size011 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle05_3.setWidths(size011);
                    detalle05_3.addCell(new Paragraph("Distancia Recorrida", conceptos));
                    detalle05_3.addCell(new Paragraph(lugEnt_distancia, body)); 
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
                    detalle06_1.addCell(new Paragraph("Calle", conceptos));
                    detalle06_1.addCell(new Paragraph(lugEnt_calle, body)); 
                    detalle06_1.addCell(new Paragraph("No. Exterior", conceptos));
                    detalle06_1.addCell(new Paragraph(lugEnt_noExterior, body));
                    documento.add(detalle06_1);
                    
                    PdfPTable detalle06_2 = new PdfPTable(4);
                    detalle06_2.setWidthPercentage(100);
                    float[] size014= {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle06_2.setWidths(size014);
                    detalle06_2.addCell(new Paragraph("No. Interior", conceptos));
                    detalle06_2.addCell(new Paragraph(lugEnt_noInterior, body)); 
                    detalle06_2.addCell(new Paragraph("Colonia", conceptos));
                    detalle06_2.addCell(new Paragraph(lugEnt_colonia, body));
                    documento.add(detalle06_2); 
                   
                    PdfPTable detalle06_3 = new PdfPTable(4);
                    detalle06_3.setWidthPercentage(100);
                    float[] size015 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle06_3.setWidths(size015);
                    detalle06_3.addCell(new Paragraph("Localidad", conceptos));
                    detalle06_3.addCell(new Paragraph(lugEnt_localidad, body)); 
                    detalle06_3.addCell(new Paragraph("Municipio", conceptos));
                    detalle06_3.addCell(new Paragraph(lugEnt_municipio, detalle));
                    documento.add(detalle06_3);
                    
                    PdfPTable detalle06_4 = new PdfPTable(4);
                    detalle06_4.setWidthPercentage(100);
                    float[] size015_s = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle06_4.setWidths(size015_s);
                    detalle06_4.addCell(new Paragraph("Estado", conceptos));
                    detalle06_4.addCell(new Paragraph(lugEnt_estado, body)); 
                    detalle06_4.addCell(new Paragraph("País", conceptos));
                    detalle06_4.addCell(new Paragraph("MEX - México", detalle));
                    documento.add(detalle06_4);
                    
                    PdfPTable detalle06_5 = new PdfPTable(4);
                    detalle06_5.setWidthPercentage(100);
                    float[] size015_d = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle06_5.setWidths(size015_d);
                    detalle06_5.addCell(new Paragraph("CP", conceptos));
                    detalle06_5.addCell(new Paragraph(lugEnt_codigoPostal, body)); 
                    detalle06_5.addCell(new Paragraph("", conceptos));
                    detalle06_5.addCell(new Paragraph("", detalle));
                    documento.add(detalle06_5);
                    
                    PdfPTable space06 = new PdfPTable(1);
                    space06.setWidthPercentage(100);
                    PdfPCell white_06 = new PdfPCell(new Paragraph("", th));
                    white_06.setColspan(1);
                    white_06.setBorder(Rectangle.NO_BORDER);
                    white_06.setBackgroundColor(BaseColor.WHITE);
                    white_06.setHorizontalAlignment(1); 
                    space06.addCell(white_06);
                    documento.add(space06);
                    
                    float pesoBrutoTotal = 0;
                    int numTotalMercancias = 0;
                    
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
                    detalle6_1.addCell(new Paragraph("" + pesoBrutoTotal, detalle));  
                    detalle6_1.addCell(new Paragraph("Unidad Peso", conceptos));
                    detalle6_1.addCell(new Paragraph(unidad_peso_cp, body));
                    documento.add(detalle6_1);
                    
                    PdfPTable detalle6_2 = new PdfPTable(4);
                    detalle6_2.setWidthPercentage(100);
                    float[] size16 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle6_2.setWidths(size16);
                    detalle6_2.addCell(new Paragraph("Num. Total Mercancias", conceptos));
                    detalle6_2.addCell(new Paragraph("" + numTotalMercancias, body)); 
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
                    
                    if (numCartaPorte > 0) {
                      for (int z=0; z< numCartaPorte; z++) { 
                          
                          contadorMercancias ++;
                          
                          String rel_clvsat_id_cp = request.getParameter("rel_clvsat_id_cp[" + z + "]");
                          String unidad_sat_id_cp = request.getParameter("unidad_sat_id_cp[" + z + "]");
                          String unidad_sat_desc_cp = request.getParameter("unidad_sat_desc_cp[" + z + "]");
                          String concepto_id_cp = request.getParameter("concepto_id_cp[" + z + "]");
                          String concepto_desc_cp = request.getParameter("concepto_desc_cp[" + z + "]");
                          String cantidad_cp = request.getParameter("cantidad_cp[" + z + "]");
                          String precio_unitario_cp = request.getParameter("precio_unitario_cp[" + z + "]");
                          String importe_descuento_cp = request.getParameter("importe_descuento_cp[" + z + "]");
                          String importe_cp = request.getParameter("importe_cp[" + z+ "]"); 
                          String unidadMedidaValor = request.getParameter("unidadMedidaValor[" + z + "]"); 
                          
                          pesoBrutoTotal += Float.parseFloat(unidadMedidaValor);
                          numTotalMercancias ++;
                
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
                            detalle7_1.addCell(new Paragraph(rel_clvsat_id_cp, body)); 
                            detalle7_1.addCell(new Paragraph("Descripción", conceptos));
                            detalle7_1.addCell(new Paragraph(concepto_desc_cp, body));
                            documento.add(detalle7_1);

                            PdfPTable detalle7_2 = new PdfPTable(4);
                            detalle7_2.setWidthPercentage(100);
                            float[] size18 = {0.90f, 1.10f, 0.90f, 1.10f};
                            detalle7_2.setWidths(size18 );
                            detalle7_2.addCell(new Paragraph("Cantidad", conceptos));
                            detalle7_2.addCell(new Paragraph(cantidad_cp, body)); 
                            detalle7_2.addCell(new Paragraph("Clave Unidad", conceptos));
                            detalle7_2.addCell(new Paragraph(unidad_sat_desc_cp, body));
                            documento.add(detalle7_2); 

                            PdfPTable detalle7_3 = new PdfPTable(4);
                            detalle7_3.setWidthPercentage(100);
                            float[] size18_1 = {0.90f, 1.10f, 0.90f, 1.10f};
                            detalle7_3.setWidths(size18_1);
                            detalle7_3.addCell(new Paragraph("Peso En Kg", conceptos));
                            detalle7_3.addCell(new Paragraph(unidadMedidaValor, detalle)); 
                            detalle7_3.addCell(new Paragraph("No. Pedimento", conceptos));
                            detalle7_3.addCell(new Paragraph(numpedimento_cp, body));
                            documento.add(detalle7_3); 
                             
                      }
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
                    detalle10_1.addCell(new Paragraph(perm_sct, body)); 
                    detalle10_1.addCell(new Paragraph("No. Permiso SCT", conceptos));
                    detalle10_1.addCell(new Paragraph(no_permismo_sct, body));
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
                    
                    PdfPTable detalle11_1 = new PdfPTable(6);
                    detalle11_1.setWidthPercentage(100);
                    float[] size26 = {0.15f, 0.15f, 0.15f, 0.15f, 0.15f, 0.15f};
                    detalle11_1.setWidths(size26);
                    detalle11_1.addCell(new Paragraph("Configuración Vehicular", conceptos));
                    detalle11_1.addCell(new Paragraph(config_vehicular, body)); 
                    detalle11_1.addCell(new Paragraph("Placa VM", conceptos));
                    detalle11_1.addCell(new Paragraph(placa_vm, body));
                    detalle11_1.addCell(new Paragraph("Año Modelo VM", conceptos));
                    detalle11_1.addCell(new Paragraph(anio_modelovm, body));
                    documento.add(detalle11_1);
                    
                    PdfPTable detalle11_2 = new PdfPTable(4);
                    detalle11_2.setWidthPercentage(100);
                    float[] size27 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle11_2.setWidths(size27);
                    detalle11_2.addCell(new Paragraph("Placa Remolque", conceptos));
                    detalle11_2.addCell(new Paragraph(placa_remolque, body)); 
                    detalle11_2.addCell(new Paragraph("Tipo Remolque", conceptos));
                    detalle11_2.addCell(new Paragraph(subtipo_remolque, detalle));
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
                    detalle12_1.addCell(new Paragraph(aseguradora_civil, body)); 
                    detalle12_1.addCell(new Paragraph("Poliza Responsabilidad Civil", conceptos));
                    detalle12_1.addCell(new Paragraph(poliza_civil, body));
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
                    detalle13_1.addCell(new Paragraph(tipo_figura, body)); 
                    detalle13_1.addCell(new Paragraph("RFC Figura", conceptos));
                    detalle13_1.addCell(new Paragraph(rfc_figura, body));
                    documento.add(detalle13_1);
                   
                    PdfPTable detalle13_2 = new PdfPTable(4);
                    detalle13_2.setWidthPercentage(100);
                    float[] size30 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle13_2.setWidths(size30);
                    detalle13_2.addCell(new Paragraph("Núm. Licencia", conceptos));
                    detalle13_2.addCell(new Paragraph(num_licencia, body)); 
                    detalle13_2.addCell(new Paragraph("Nombre Figura", conceptos));
                    detalle13_2.addCell(new Paragraph(nombre_figura, body));
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
                    
                    
                    /************************************************/
                    
                    PdfPTable domicilioAut = new PdfPTable(1);
                    domicilioAut.setWidthPercentage(100);
                    PdfPCell titulo_3Aut = new PdfPCell(new Paragraph("DOMICILIO FIGURA TRANSPORTE", th));
                    titulo_3Aut.setColspan(1);
                    titulo_3Aut.setBorder(Rectangle.NO_BORDER);
                    titulo_3Aut.setBackgroundColor(BaseColor.BLACK);
                    titulo_3Aut.setHorizontalAlignment(1); 
                    domicilioAut.addCell(titulo_3Aut);
                    documento.add(domicilioAut);
                    
                    PdfPTable detalle3_1Aut = new PdfPTable(4);
                    detalle3_1Aut.setWidthPercentage(100);
                    float[] size4Aut = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle3_1Aut.setWidths(size4Aut);
                    detalle3_1Aut.addCell(new Paragraph("Calle", conceptos));
                    detalle3_1Aut.addCell(new Paragraph(autChofer_calle, body)); 
                    detalle3_1Aut.addCell(new Paragraph("No. Exterior", conceptos));
                    detalle3_1Aut.addCell(new Paragraph(autChofer_numero_exterior, body));
                    documento.add(detalle3_1Aut);
                    
                    PdfPTable detalle3_2Aut = new PdfPTable(4);
                    detalle3_2Aut.setWidthPercentage(100);
                    float[] size5Aut = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle3_2Aut.setWidths(size5Aut);
                    detalle3_2Aut.addCell(new Paragraph("No. Interior", conceptos));
                    detalle3_2Aut.addCell(new Paragraph(autChofer_numero_interior, body)); 
                    detalle3_2Aut.addCell(new Paragraph("Colonia", conceptos));
                    detalle3_2Aut.addCell(new Paragraph(autChofer_colonia, body));
                    documento.add(detalle3_2Aut); 
                   
                    PdfPTable detalle3_3Aut = new PdfPTable(4);
                    detalle3_3Aut.setWidthPercentage(100);
                    float[] size6Aut = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle3_3Aut.setWidths(size6Aut);
                    detalle3_3Aut.addCell(new Paragraph("Localidad", conceptos));
                    detalle3_3Aut.addCell(new Paragraph(autChofer_localidad, body)); 
                    detalle3_3Aut.addCell(new Paragraph("Municipio", conceptos));
                    detalle3_3Aut.addCell(new Paragraph(autChofer_municipio, body));
                    documento.add(detalle3_3Aut);
                    
                    PdfPTable detalle3_4Aut = new PdfPTable(4);
                    detalle3_4Aut.setWidthPercentage(100);
                    float[] size7Aut = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle3_4Aut.setWidths(size7Aut); 
                    detalle3_4Aut.addCell(new Paragraph("Estado", conceptos));
                    detalle3_4Aut.addCell(new Paragraph(autChofer_estado, body)); 
                    detalle3_4Aut.addCell(new Paragraph("CP", conceptos));
                    detalle3_4Aut.addCell(new Paragraph(autChofer_codigo_postal, body));
                    documento.add(detalle3_4Aut);
                    
                    PdfPTable detalle3_5Aut = new PdfPTable(4);
                    detalle3_5Aut.setWidthPercentage(100);
                    float[] size7Aut_3 = {0.90f, 1.10f, 0.90f, 1.10f};
                    detalle3_5Aut.setWidths(size7Aut_3); 
                    detalle3_5Aut.addCell(new Paragraph("Referencia", conceptos));
                    detalle3_5Aut.addCell(new Paragraph(autChofer_referencia, body)); 
                    detalle3_5Aut.addCell(new Paragraph("", conceptos));
                    detalle3_5Aut.addCell(new Paragraph("", body));
                    documento.add(detalle3_5Aut);
                    
                    PdfPTable space3Aut = new PdfPTable(1);
                    space3Aut.setWidthPercentage(100);
                    PdfPCell white_3Aut = new PdfPCell(new Paragraph("", th));
                    white_3Aut.setColspan(1);
                    white_3Aut.setBorder(Rectangle.NO_BORDER);
                    white_3Aut.setBackgroundColor(BaseColor.WHITE);
                    white_3Aut.setHorizontalAlignment(1); 
                    space3Aut.addCell(white_3Aut);
                    documento.add(space3Aut);
                    
                    /***********************************************/
                    
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
                    space15 .setWidthPercentage(100);
                    PdfPCell white_15 = new PdfPCell(new Paragraph("", th));
                    white_15.setColspan(1);
                    white_15.setBorder(Rectangle.NO_BORDER);
                    white_15.setBackgroundColor(BaseColor.WHITE);
                    white_15.setHorizontalAlignment(1); 
                    space15 .addCell(white_15);
                    documento.add(space15);
                    
                    PdfPTable detalle_conceptos = new PdfPTable(5);
                    detalle_conceptos.setWidthPercentage(100);
                    float[] medidaCeldasparametros0 = {0.50f, 0.50f, 2.05f, 0.50f, 0.50f};
                    detalle_conceptos.setWidths(medidaCeldasparametros0); 
                    PdfPCell titulo_16 = new PdfPCell(new Paragraph("Cantidad", th));
                    titulo_16.setColspan(1);
                    titulo_16.setBorder(Rectangle.NO_BORDER);
                    titulo_16.setBackgroundColor(BaseColor.BLACK);
                    titulo_16.setHorizontalAlignment(0); 
                    detalle_conceptos.addCell(titulo_16);
                    PdfPCell titulo_17 = new PdfPCell(new Paragraph("Unidad", th));
                    titulo_17.setColspan(1);
                    titulo_17.setBorder(Rectangle.NO_BORDER);
                    titulo_17.setBackgroundColor(BaseColor.BLACK);
                    titulo_17.setHorizontalAlignment(0); 
                    detalle_conceptos.addCell(titulo_17);
                    PdfPCell titulo_18 = new PdfPCell(new Paragraph("Descripción", th));
                    titulo_18.setColspan(1);
                    titulo_18.setBorder(Rectangle.NO_BORDER);
                    titulo_18.setBackgroundColor(BaseColor.BLACK);
                    titulo_18.setHorizontalAlignment(0); 
                    detalle_conceptos.addCell(titulo_18);
                    PdfPCell titulo_19 = new PdfPCell(new Paragraph("Precio Unitario", th));
                    titulo_19.setColspan(1);
                    titulo_19.setBorder(Rectangle.NO_BORDER);
                    titulo_19.setBackgroundColor(BaseColor.BLACK);
                    titulo_19.setHorizontalAlignment(0); 
                    detalle_conceptos.addCell(titulo_19);
                    PdfPCell titulo_20 = new PdfPCell(new Paragraph("Importe", th));
                    titulo_20.setColspan(1);
                    titulo_20.setBorder(Rectangle.NO_BORDER);
                    titulo_20.setBackgroundColor(BaseColor.BLACK);
                    titulo_20.setHorizontalAlignment(0); 
                    detalle_conceptos.addCell(titulo_20);
                    documento.add(detalle_conceptos);
                    
                    //SECCIÓN: RELACIÓN CONCEPTOS 
                    if (numConcept > 0) {
                        for (int z = 0; z < numConcept; z++) { 
                            String comentarios = request.getParameter("comentarios[" + z + "]");
                            String rel_clvsat_id = request.getParameter("rel_clvsat_id[" + z + "]");
                            String unidad_sat_id = request.getParameter("unidad_sat_id[" + z + "]");
                            String concepto_id = request.getParameter("concepto_id[" + z + "]");
                            String concepto_desc = request.getParameter("concepto_desc[" + z + "]");
                            String cantidad = request.getParameter("cantidad[" + z + "]");
                            String precio_unitario = request.getParameter("precio_unitario[" + z + "]");
                            String importe_descuento = request.getParameter("importe_descuento[" + z + "]");
                            String importe = request.getParameter("importe[" + z + "]");

                            subtotal += Float.parseFloat(precio_unitario);
                            traslado_iva = (subtotal * 0.16f); 
                            retencion = (subtotal * 0.04f);
                            total = (subtotal + traslado_iva) - retencion;
                    
                            PdfPTable parametros = new PdfPTable(5);
                            parametros.setWidthPercentage(100);
                            float[] medidaCeldasparametros = {0.50f, 0.50f, 2.05f, 0.50f, 0.50f};
                            parametros.setWidths(medidaCeldasparametros); 
                            //parametros.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                            parametros.addCell(new Paragraph(cantidad, body));            // cantidad
                            parametros.addCell(new Paragraph(unidad_sat_id, body));       // clave unidad
                            parametros.addCell(new Paragraph(concepto_desc + "\n\n"
                                                            + "Clave Prod. Serv. - 78101801 Servicios de transporte de carga por carretera (en camión) en área local \n\n"
                                                            + "Impuestos: \n"
                                                            + " \t\t\tTraslados: \n" 
                                                            + " \t\t\t\t\t\t002 IVA Base - $ " + formatter.format(subtotal) + " Tasa - 0.160000 Importe - $ " + formatter.format(traslado_iva) + " \n" 
                                                            + " \t\t\tRetenciones: \n" 
                                                            + " \t\t\t\t\t\t002 IVA Base - $ " + formatter.format(subtotal) + " Tasa - 0.040000 Importe - $ " + formatter.format(retencion) + "", body));        // concepto
                            parametros.addCell(new Paragraph("$ " + formatter.format(Float.parseFloat(precio_unitario)), body));              // precio unitario
                            parametros.addCell(new Paragraph("$ " + formatter.format(Float.parseFloat(importe)), body));                      // importe
                            documento.add(parametros);
                             
                        }
                    }
                    
                    documento.add(new Paragraph(" ", espacio));
                    
                    String importeGral = formatter.format(total);
                    String importePrincipal = importeGral.replace(".", "/"); 
                    
                    String[] sep = importePrincipal.split("/");
                    String importeEntero = sep[0];  // 000000
                    String importeDecimal = sep[1]; // .0000 
                    
                    
                    PdfPTable table61 = new PdfPTable(4);
                    table61.setWidthPercentage(100);
                    table61.setHorizontalAlignment(Element.ALIGN_RIGHT);        // (Element.ALIGN_RIGHT);
                    float[] medidaCeldas61 = {0.35f, 0.95f, 0.45f, 0.25f}; 
                    table61.setWidths(medidaCeldas61);
                    table61.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                    //table61.addCell(new Paragraph("(" + Numeros.cantidadConLetra(total_global) + "00/100 M.N.)", encabezado11));
                    table61.addCell(new Paragraph("IMPORTE CON LETRA", encabezado11));
                    table61.addCell(new Paragraph(Numeros.cantidadConLetra(importeEntero) + " PESOS, " + importeDecimal + "/100 MXN", body));
                    table61.addCell(new Paragraph("SUBTOTAL", encabezado11));
                    table61.addCell(new Paragraph("$ " + formatter.format(subtotal), body));

                    table61.addCell(new Paragraph("", encabezado11));
                    table61.addCell(new Paragraph(" ", body));
                    table61.addCell(new Paragraph("TRASLADO IVA TASA 0.160000", encabezado11)); 
                    table61.addCell(new Paragraph("$ " + formatter.format(traslado_iva), body));

                    table61.addCell(new Paragraph("TIPO DE COMPROBANTE", encabezado11));
                    table61.addCell(new Paragraph(comprobante_desc, body));
                    table61.addCell(new Paragraph("RETENCIÓN IVA", encabezado11)); 
                    table61.addCell(new Paragraph("$ " + formatter.format(retencion), body));
                    
                    table61.addCell(new Paragraph("FORMA DE PAGO", encabezado11));
                    table61.addCell(new Paragraph(forma_pago_desc, body));
                    table61.addCell(new Paragraph("TOTAL", encabezado11));
                    table61.addCell(new Paragraph("$ " + formatter.format(total), body));

                    table61.addCell(new Paragraph("MÉTODO DE PAGO", encabezado11));
                    table61.addCell(new Paragraph(metodo_pago_desc, body));
                    table61.addCell(new Paragraph("", encabezadofN));
                    table61.addCell(new Paragraph("", body));
                    
                    table61.addCell(new Paragraph("MONEDA", encabezado11));
                    table61.addCell(new Paragraph(moneda_desc, body));
                    table61.addCell(new Paragraph("", encabezadofN));
                    table61.addCell(new Paragraph("", body));

                    documento.add(table61);

                    documento.add(new Paragraph(" ", espacio));
                    documento.add(new Paragraph(" ", espacio));
                    
                    BarcodeQRCode barcodeQRCode = new BarcodeQRCode("https://verificacfdi.facturaelectronica.sat.gob.mx/default.aspx?&id=" + folio_fiscal + "&re=" + rfc_cliente + "&rr=" + rfc_tacts + "&tt=" + formatter.format(total) + "&fe=5KUPkg==", 1000, 1000, null);
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
                    cellF.setRowspan(6);
                    cellF.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellF.setBorder(Rectangle.NO_BORDER);
                    tableF.addCell(cellF);
                    
                    cellF = new PdfPCell(new Phrase("SELLO DIGITAL DEL CFDI", encabezado11));
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
                    
                    cellF = new PdfPCell(new Phrase("SELLO DIGITAL DEL SAT", encabezado11));
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
                    
                    cellF = new PdfPCell(new Phrase("CADENA ORIGINAL DEL COMPLEMENTO DE CERTIFICACIÓN DIGITAL DEL SAT", encabezado11));
                    cellF.setPadding(5);
                    cellF.setUseAscender(true);
                    cellF.setUseDescender(true);
                    cellF.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cellF.setBorder(Rectangle.NO_BORDER);
                    tableF.addCell(cellF);
                    
                    cellF = new PdfPCell(new Phrase(certificacionDigital, encabezado12));
                    cellF.setPadding(5);
                    cellF.setUseAscender(true);
                    cellF.setUseDescender(true);
                    cellF.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cellF.setBorder(Rectangle.NO_BORDER);
                    tableF.addCell(cellF);
                    
                    documento.add(tableF);
                    
                   
                    documento.close();
                    oraDB.close();
        %>
        <!--<div class="container"> 
            <iframe class="responsive-iframe" src="<%=request.getContextPath()%>/MostrarPDF_PORTE"></iframe>
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
