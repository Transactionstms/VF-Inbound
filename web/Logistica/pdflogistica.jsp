<%-- 
    Document   : pdflogistica
    Created on : 26-may-2023, 13:49:38
    Author     : grecendiz
--%>
 
<%@page import="com.tacts.evidencias.facturacion.Email"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@page import="com.pantalla.BLPantalla"%>
<%@page import="com.pantalla.Pantalla"%>
<%@page import="com.usuario.Usuario"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="com.onest.train.ruta.Ruta"%>
<%@page import="com.onest.train.consultas.CapturaDocumento"%>
<%@page import=" javax.servlet.http.*,
     java.io.*,
     java.util.*,
     com.lowagie.text.*,java.util.Date,java.text.SimpleDateFormat,java.text.DecimalFormat,
     java.io.IOException, java.awt.Color,javax.swing.*, java.sql.*, java.io.PrintWriter,
     com.lowagie.text.pdf.*,
     com.lowagie.text.Document,
     com.lowagie.text.DocumentException,
     com.lowagie.text.Element,
     com.lowagie.text.PageSize,
     com.lowagie.text.pdf.PdfPageEventHelper,
     com.lowagie.text.pdf.PdfWriter,
     com.lowagie.text.pdf.ColumnText,
     com.lowagie.text.pdf.PdfPCell,
     com.lowagie.text.Paragraph,
     com.onest.oracle.*,
     com.lowagie.text.Phrase,
     com.lowagie.text.pdf.ColumnText,
     com.lowagie.text.Image,
     com.lowagie.text.Chunk,
     com.lowagie.text.Rectangle,
     com.lowagie.text.pdf.PdfGState,
     com.lowagie.text.pdf.PdfPTable,
     com.lowagie.text.pdf.PdfTemplate,
     com.lowagie.text.pdf.PdfPTable"
%><%
            try {
                
                String codigo="-";
                PdfPTable espacio; 
                PdfPCell talu;
                PdfPTable califx; 
                PdfPTable califx1;
                PdfPTable califx2;
                PdfPTable embarque0;
                PdfPTable piex;
                PdfPTable piea;
                PdfPTable bd;
                PdfPTable reimpresion;
                HttpSession ownsession = request.getSession(false);
                Usuario root = (Usuario) ownsession.getAttribute("login.root");
                response.setContentType("text/html;charset=UTF-8");
                DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
                OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
                NumberFormat dd = new DecimalFormat("#0.00");
                Font titulo = new Font(Font.TIMES_ROMAN, 10, Font.BOLD, Color.black);
                Font ctabla1 = FontFactory.getFont("Helvetica", 5, Font.BOLD);
                ctabla1.setColor(new Color(0x00, 0x00, 0x00));
                Font ctabla = FontFactory.getFont("Helvetica", 5, Font.NORMAL);
                ctabla.setColor(new Color(0x00, 0x00, 0x00));
                Font no = FontFactory.getFont("Helvetica", 6, Font.NORMAL);
                no.setColor(new Color(0x00, 0x00, 0x00));
                try {
                    CapturaDocumento cd = new CapturaDocumento();
                    Date fechaActual = new Date();
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MMMM-dd");
                    String cadenaFecha = formato.format(fechaActual);
                    String userName = (String) ownsession.getAttribute("login.user_name");
                    String ejemplo[] = cadenaFecha.split("-");
                    String mes = "";
                    String m = ejemplo[1].toUpperCase();
                    String dia1 = ejemplo[2];
                    String anio1 = ejemplo[0];
                    String e_cuentaPdf = "";
                    Calendar calendario = Calendar.getInstance();
                    int hora = calendario.get(Calendar.HOUR_OF_DAY);
                    int minutos = calendario.get(Calendar.MINUTE);
                    int segundos = calendario.get(Calendar.SECOND);
                    String hms = hora + ":" + minutos + ":" + segundos;
                    Pantalla pantalla = BLPantalla.getPantalla(31, root.getIdLenguaje());
                    String agrupador = request.getParameter("a");
                    String emt = request.getParameter("e");
                    String nombre = "";
                    if (m.equals("JANUARY") || m.equals("ENERO")) {
                        mes = "ENERO";
                    }
                    if (m.equals("FEBRUARY") || m.equals("FEBRERO")) {
                        mes = "FEBRERO";
                    }
                    if (m.equals("MARCH") || m.equals("MARZO")) {
                        mes = "MARZO";
                    }
                    if (m.equals("APRIL") || m.equals("ABRIL")) {
                        mes = "ABRIL";
                    }
                    if (m.equals("MAY") || m.equals("MAYO")) {
                        mes = "MAYO";
                    }
                    if (m.equals("JUNE") || m.equals("JUNIO")) {
                        mes = "JUNIO";
                    }
                    if (m.equals("JULY") || m.equals("JULIO")) {
                        mes = "JULIO";
                    }
                    if (m.equals("AUGUST") || m.equals("AGOSTO")) {
                        mes = "AGOSTO";
                    }
                    if (m.equals("SEPTEMBER") || m.equals("SEPTIEMBRE")) {
                        mes = "SEPTIEMBRE";
                    }
                    if (m.equals("OCTOBER") || m.equals("OCTUBRE")) {
                        mes = "OCTUBRE";
                    }
                    if (m.equals("NOVEMBER") || m.equals("NOVIEMBRE")) {
                        mes = "NOVIEMBRE";
                    }
                    if (m.equals("DECEMBER") || m.equals("DICIEMBRE")) {
                        mes = "DICIEMBRE";
                    }
                    String ant = "EN CASO  DE PRESENTARSE PROBLEMAS A LA ----ENTREGA POR FAVOR LLAME AL SERVICIO AL CLIENTE "+BLPantalla.getEtiqueta(pantalla,369)+" 00 0000-0000 ";
                    String pie_pag = "A " + hms + " HORAS DEL DIA " + dia1 + " DEL MES DE " + mes + " DE " + anio1 + " RECIBI DE: "+BLPantalla.getEtiqueta(pantalla,369)+", S.A. DE C.V. FACTURAS ORIGINALES "
                            + "ASI COMO LOS PRODUCTOS Y MERCANCIAS QUE AMPARAN A DICHAS FACTURAS Y LAS - EN EL PRESENTE "
                            + " \"GUIA DE EMBARQUES\" A EFECTO DE QUE EN CUMPLIMIENTO DE MI TRABAJO, HAGA ENTREGA A LOS DESTINATARIOS QUE SE INDICAN LOS PRODUCTOS "
                            + "Y MERCANCIAS QUE SE HA HECHO ALUSION, OBLIGANDOME A DEVOLVER DEBIDAMENTE FIRMADAS Y SELLADAS POR EL DESTINATARIO LAS FACTURAS ORIGINALES "
                            + "A: VF OUTDOOR, S.A. DE C.V ";
                    String black = "CONDICIONES PARA BLACK & DECKER, S.A DE C.V. "
                            + "\n\nEL TRANSPORTISTA EN CASO DE TENER CUALQUIER ANCIDENCIA EN LOS DOCUMENTOS DE B&D FAVOR DE COMUNICARSE A LOS SIGUIENTES TELEFONOS: "
                            + "\nBLACK & DECKER EN EL INTERIOR DE LA REPUBLICA, LADA SIN COSTO 01800 847 2315 / 16 / 17 EXT. 7032, 7033 Y 7034 "
                            + "\nBLACK & DECKER EN EL AREA METROPOLITANA 5326 7100 EXT. 7032, 7033 Y 7034 "
                            + "\nCERVANTES 00*000000*00 XXXXXX 00*000000*00 XXXXX 52*000000*00 "
                            + "\n"+BLPantalla.getEtiqueta(pantalla,369)+" 0000 0000 EXT. 000, 000 Y 000 "
                            + "\nFORANEO DIRECTO PORTEOS "
                            + "\nLAINES 00*000000*00 SILVIA CARMONA 00*000000*00 "
                            + "\nPARRA 00*000000*00";
                    class PEvent extends PdfPageEventHelper {
                        public PdfGState gstate;
                        protected BaseFont helv;
                        protected PdfTemplate tpl;
                        private PdfPTable ptu;
                        private PdfPTable p_p;
                        private PdfPTable p_a;
                        private PdfPTable e00;
                        private PdfPTable en;
                        PEvent(PdfPTable e, PdfPTable p, PdfPTable embarque00, PdfPTable piea1, PdfPTable piex1) {
                            ptu = p;
                            e00 = embarque00;
                            p_p = piex1;
                            p_a = piea1;
                            en = e;
                        }
                        public void onStartPage(PdfWriter writer, Document document) {
                            try {
                                document.add(en);
                                document.add(ptu);
                                document.add(e00);
                                document.add(ptu);
                                PdfContentByte cb = writer.getDirectContent();
                                cb.saveState();
                                cb.beginText();
                                cb.setFontAndSize(helv, 12);
                                cb.setTextMatrix(440, 50);
                                cb.setGState(gstate);
                                cb.setColorFill(Color.blue);
                                cb.beginText();
                                cb.setFontAndSize(helv, 48);
                                cb.showTextAligned(Element.ALIGN_CENTER, "V F  O U T D O O R  " + " ", document.getPageSize().getWidth() / 2, document.getPageSize().getHeight() / 2, 45);
                                cb.endText();
                                cb.restoreState();
                            } catch (Exception e) {
                                throw new ExceptionConverter(e);
                            }
                        }
                        public void onOpenDocument(PdfWriter writer, Document document) {
                            try {
                                tpl = writer.getDirectContent().createTemplate(1000, 1000);
                                tpl.setBoundingBox(new Rectangle(20, 20, 100, 100));
                                helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
                                gstate = new PdfGState();
                                gstate.setFillOpacity(0.3f);
                                gstate.setStrokeOpacity(0.3f);
                            } catch (Exception e) {
                                throw new ExceptionConverter(e);
                            }
                        }
                        public void onEndPage(PdfWriter writer, Document document) {
                            try {
                                p_a.setTotalWidth(767);
                                p_a.writeSelectedRows(0, 180, 15, 150, writer.getDirectContent());
                                p_p.setTotalWidth(767);
                                p_p.writeSelectedRows(0, 300, 15, 50, writer.getDirectContent());

                            } catch (Exception e) {
                                throw new ExceptionConverter(e);
                            }
                        }
                        public void onCloseDocument(PdfWriter writer, Document document) {
                            tpl.beginText();
                            tpl.setFontAndSize(helv, 12);
                            tpl.setTextMatrix(0, 0);
                            tpl.showText(Integer.toString(writer.getPageNumber() - 1));
                            tpl.endText();
                        }
                    }
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "inline; filename=embarque.pdf");
                    Document document = new Document(PageSize.LETTER.rotate(), -80, -80, 10, 100);
                    try {
                        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                        PdfWriter writer = PdfWriter.getInstance(document, buffer);
                        writer.setViewerPreferences(PdfWriter.PageLayoutTwoColumnLeft);
                        DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
                       
                       //String ruta = "http://localhost:"+request.getServerPort()+"/"+request.getContextPath()+"/img/VFMX.png";

                                                
                        String ruta = "C:\\Format\\VFMX.png"; 
                        System.out.println("ruta"+ruta);
                        Image onest = Image.getInstance(ruta); 
                        float[] widths = {3f, 17f};
                        float[] widths1 = {1f, 1f,1f,1f, 1f, 1f, .5f, .5f, .5f, .5f, .5f, .5f};
                               // , .5f, .5f, .5f, .7f, .7f, 1f INBOUND7
                        String obs = "";
                        PdfPTable encabezado = new PdfPTable(widths);
                        encabezado.setSpacingBefore(25f);
                        encabezado.getDefaultCell().setBorder(0);
                        encabezado.addCell(onest);
                        if ("0".equals(ownsession.getAttribute("emti")) && emt == null) {
                            PdfPCell cell3 = new PdfPCell(new Paragraph("\nGuía de Planeación / Carta Porte", titulo));
                            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell3.setBorder(Rectangle.NO_BORDER);
                            encabezado.addCell(cell3);
                        } else if(emt != null && emt.equals("2")){
                            PdfPCell cell3 = new PdfPCell(new Paragraph("\nGuía de embarque / Carta Porte", titulo));
                            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell3.setBorder(Rectangle.NO_BORDER);
                            encabezado.addCell(cell3);
                        } else {
                            PdfPCell cell3 = new PdfPCell(new Paragraph("\n Guía de Planeación / Carta Porte", titulo));
                            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell3.setBorder(Rectangle.NO_BORDER);
                            encabezado.addCell(cell3);
                        }
                        PdfPCell cell3 = new PdfPCell(new Paragraph(" ", titulo));
                        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell3.setBorder(Rectangle.NO_BORDER);
                        encabezado.addCell(cell3);
                        espacio = new PdfPTable(1);
                        espacio.getDefaultCell().setBorder(0);
                        espacio.setSpacingBefore(10f);
                        espacio.addCell("");
                        piea = new PdfPTable(5);                        
                        piea.getDefaultCell().setBorder(1);
                        piea.setHorizontalAlignment(Element.ALIGN_CENTER);
                        piex = new PdfPTable(1);
                        piex.getDefaultCell().setBorder(1);
                        piex.setHorizontalAlignment(Element.ALIGN_CENTER);
                        bd = new PdfPTable(1);
                        bd.getDefaultCell().setBorder(1);
                        bd.setHorizontalAlignment(Element.ALIGN_CENTER);
                        reimpresion = new PdfPTable(1);
                        reimpresion.getDefaultCell().setBorder(1);
                        reimpresion.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx = new PdfPTable(widths1);
                        califx.getDefaultCell().setBorder(1);
                        califx.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx1 = new PdfPTable(widths1);
                        califx1.getDefaultCell().setBorder(1);
                        califx1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2 = new PdfPTable(widths1);
                        califx2.getDefaultCell().setBorder(0);
                        califx2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        embarque0 = new PdfPTable(4);
                        float[] tamF0 = {20f, 80f,20f, 80f};
                        embarque0.setWidths(tamF0);
                        embarque0.getDefaultCell().setBorder(1);
                        embarque0.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu = new PdfPCell(new Paragraph("Quien Genero el Control Vehicular", ctabla));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu.setColspan(4);
                        piea.addCell(talu);
                        talu = new PdfPCell(new Paragraph("", ctabla));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        piea.addCell(talu);
                        talu = new PdfPCell(new Paragraph("\n\n\n" + userName.toUpperCase() + "\nfirma", ctabla));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu.setColspan(3);
                        piea.addCell(talu);
                        talu = new PdfPCell(new Paragraph("\n\n\n\nPuesto", ctabla));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        piea.addCell(talu);
                        talu = new PdfPCell(new Paragraph("\n\n\n\nFecha y Salida", ctabla));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        piea.addCell(talu);
                        talu = new PdfPCell(new Paragraph("Linea de transporte", ctabla));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        piea.addCell(talu);
                        talu = new PdfPCell(new Paragraph("", ctabla));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu.setColspan(2);
                        piea.addCell(talu);
                        talu = new PdfPCell(new Paragraph("", ctabla));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        piea.addCell(talu);
                        talu = new PdfPCell(new Paragraph("", ctabla));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        piea.addCell(talu);
                        
                        String custodia = "";
                        String nombre_custodio1 = "";
                        String nombre_custodio2 = "";
                        
                          if (db.doDB(cd.CustodiaTipo(agrupador))) {
                                  for (String[] rowcus : db.getResultado()) {

                                      if (rowcus[0] == null) {
                                          custodia = "No aplica";

                                      } else {

                                          custodia = rowcus[1];
                                          nombre_custodio1 = rowcus[2];
                                          nombre_custodio2 = rowcus[3];
                                      }
                                  }
                              }
                        
                        talu = new PdfPCell(new Paragraph("Custodia", ctabla));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        piea.addCell(talu);
                        talu = new PdfPCell(new Paragraph("" + custodia + "", ctabla));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        piea.addCell(talu);
                        
                        
                        
                        talu = new PdfPCell(new Paragraph("Nombre Custodio 1", ctabla));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        piea.addCell(talu);
                        talu = new PdfPCell(new Paragraph("" + nombre_custodio1 + "", ctabla));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        piea.addCell(talu);
                        
                        talu = new PdfPCell(new Paragraph("Nombre Custodio 2", ctabla));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        piea.addCell(talu);
                        talu = new PdfPCell(new Paragraph("" + nombre_custodio2 + "", ctabla));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        piea.addCell(talu);
                        
                        
                        
                        if (e_cuentaPdf.equals("100")) {
                            talu = new PdfPCell(new Paragraph(" " + black, ctabla));
                            talu.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                            talu.setBorder(Rectangle.NO_BORDER);
                            bd.addCell(talu);
                        }
                        if (db.doDB(cd.obserEmbarque(agrupador))) {
                            for (String[] rowc : db.getResultado()) {
                                obs = rowc[0];
                            }
                            if (obs == null) {
                                talu = new PdfPCell(new Paragraph("Observaciones: N/A", ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                talu.setColspan(2);
                                piea.addCell(talu);
                            } else {
                                talu = new PdfPCell(new Paragraph("Observaciones:" + obs, ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                talu.setColspan(2);
                                piea.addCell(talu);
                            }
                        }
                       else{
                                talu = new PdfPCell(new Paragraph("Observaciones: N/A", ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                talu.setColspan(2);
                                piea.addCell(talu);
                       }
                           if (db.doDB(cd.cabeceroPdf(agrupador))) {
                            for (String[] rowc : db.getResultado()) {
                                  System.out.println("rowc[18]"+rowc[18]);
                                nombre = rowc[15];
                                talu = new PdfPCell(new Paragraph("Autorizacion de Salida ("+BLPantalla.getEtiqueta(pantalla,369)+")", ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                                talu.setColspan(3);
                                piea.addCell(talu);
                                talu = new PdfPCell(new Paragraph("Recibi", ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                                talu.setColspan(2);
                                piea.addCell(talu);
                                talu = new PdfPCell(new Paragraph("\n\n\n\n\nNombre y Firma", ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                                talu.setColspan(3);
                                piea.addCell(talu);
                                if ("0".equals(ownsession.getAttribute("emti"))) {
                                    talu = new PdfPCell(new Paragraph("\n\n\n\n" + nombre.toUpperCase() + "\nFirma Operador", ctabla));
                                    talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    talu.setColspan(2);
                                    piea.addCell(talu);
                                }
                                else{
                                    talu = new PdfPCell(new Paragraph("\n\n\n\n\nNombre y Firma Operador", ctabla));
                                    talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    talu.setColspan(2);
                                    piea.addCell(talu);
                                  }
                                  talu = new PdfPCell(new Paragraph(" " + ant, ctabla));
                                  talu.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                                  talu.setBorder(Rectangle.NO_BORDER);
                                  piex.addCell(talu);
                                  talu = new PdfPCell(new Paragraph(" " + pie_pag, ctabla));
                                  talu.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                                  talu.setBorder(Rectangle.NO_BORDER);
                                  piex.addCell(talu);
                                  
                                talu = new PdfPCell(new Paragraph("Guia de embarque \n", ctabla1));
                                talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                talu.setBorder(Rectangle.NO_BORDER);
                                embarque0.addCell(talu);
                                  System.out.println("rowc[18]\t\t\t\t\t\tFecha"+rowc[18]);
                                talu = new PdfPCell(new Paragraph("" + rowc[0].toUpperCase() + "\t\t\t\t\t\tFecha: " + rowc[1].toUpperCase() + "\t\t\t\t\t\t Audita: " + rowc[17].toUpperCase(), ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                talu.setBorder(Rectangle.NO_BORDER);
                                embarque0.addCell(talu);
                                
                                //----------
                               
                                    //Aca va el codigo de barras
                                    codigo = rowc[0].toUpperCase();
                                    talu = new PdfPCell(new Paragraph(" ", ctabla1));
                                    talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    talu.setBorder(Rectangle.NO_BORDER);
                                    embarque0.addCell(talu);                                
                                    talu = new PdfPCell(new Paragraph(" ", ctabla1));
                                    talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    talu.setBorder(Rectangle.NO_BORDER);
                                    embarque0.addCell(talu);                                    
                                //----------
                                System.out.println("rowc[18]emit0--"+rowc[18]);
                                if (true) {
                                      
  System.out.println("rowc[18]emit"+rowc[18]);

                                //----------
                                    talu = new PdfPCell(new Paragraph("Datos del Cargador ", ctabla1));
                                    talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    talu.setBorder(Rectangle.NO_BORDER);
                                    embarque0.addCell(talu);                                
                                    //talu = new PdfPCell(new Paragraph("Empresa : "+BLPantalla.getEtiqueta(pantalla,369)+" S.A de C.V" + "\nJAIME BALMES 8-102  COLONIA LOS MORALES POLANCO DELEGACION MIGUEL HIDALGO DISTRITO FEDERAL CODIGO POSTAL 11510" + "\nR.F.C.     : HME790831MFA ", ctabla));
                                    talu = new PdfPCell(new Paragraph(root.getDireccion(), ctabla));
                                    talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    talu.setBorder(Rectangle.NO_BORDER);
                                    embarque0.addCell(talu);                                    
                                //----------                                                                                                                                                                                                                  
                                     talu = new PdfPCell(new Paragraph("Datos del Porteador \n", ctabla1));
                                    talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    talu.setBorder(Rectangle.NO_BORDER);
                                    embarque0.addCell(talu);
                                    talu = new PdfPCell(new Paragraph("Linea Transportista: " + rowc[11].toUpperCase() + "\nDireccion: " + rowc[18].toUpperCase() + "\nTipo de Unidad: " + rowc[9].toUpperCase() +"\nPlacas:" + rowc[4].toUpperCase() , ctabla));
                                    talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    talu.setBorder(Rectangle.NO_BORDER);
                                    embarque0.addCell(talu);
                                    
                                    System.out.println("rowc[18]"+rowc[18]);
                                            
                                    
                                //----------
                                    talu = new PdfPCell(new Paragraph(" ", ctabla1));
                                    talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    talu.setBorder(Rectangle.NO_BORDER);
                                    embarque0.addCell(talu);                                
                                    talu = new PdfPCell(new Paragraph(" ", ctabla1));
                                    talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    talu.setBorder(Rectangle.NO_BORDER);
                                    embarque0.addCell(talu);                                    
                                //----------                                    
                                    talu = new PdfPCell(new Paragraph("Chofer Asignado \n", ctabla1));
                                    talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    talu.setBorder(Rectangle.NO_BORDER);
                                    embarque0.addCell(talu);

                                    talu = new PdfPCell(new Paragraph("" + rowc[14].toUpperCase() + "-" + rowc[15].toUpperCase(), ctabla));
                                    talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    talu.setBorder(Rectangle.NO_BORDER);
                                    embarque0.addCell(talu);
                                }
                            }
                        }
                        talu = new PdfPCell(new Paragraph("CONTAINER", ctabla1));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                        califx.addCell(talu);
                        talu = new PdfPCell(new Paragraph("BL/AWB/PRO", ctabla1));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                        talu.setColspan(2);
                        califx.addCell(talu);
                        talu = new PdfPCell(new Paragraph("SHIPMENT", ctabla1));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                        califx.addCell(talu);
                        talu = new PdfPCell(new Paragraph("LOAD TYPE", ctabla1));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                        califx.addCell(talu);
                        talu = new PdfPCell(new Paragraph("LUMBRIO", ctabla1));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                        califx.addCell(talu);
                        talu = new PdfPCell(new Paragraph("BRAND", ctabla1));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                        califx.addCell(talu);
                        if (ownsession.getAttribute("cbdivcuenta").equals("8,9,10,11")) {
                            talu = new PdfPCell(new Paragraph("Dis", ctabla1));
                            talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                            talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                            califx.addCell(talu);
                            talu = new PdfPCell(new Paragraph("Col", ctabla1));
                            talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                            talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                            califx.addCell(talu);
                            talu = new PdfPCell(new Paragraph("Gan", ctabla1));
                            talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                            talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                            califx.addCell(talu);
                            talu = new PdfPCell(new Paragraph("Bol", ctabla1));
                            talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                            talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                            califx.addCell(talu);
                            talu = new PdfPCell(new Paragraph("P. traje", ctabla1));
                            talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                            talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                            califx.addCell(talu);
                        } else {
                            talu = new PdfPCell(new Paragraph("DIVISION", ctabla1));
                            talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                            talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                            califx.addCell(talu);
                            talu = new PdfPCell(new Paragraph("MX PORT", ctabla1));
                            talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                            talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                            califx.addCell(talu);
                            talu = new PdfPCell(new Paragraph("ETA MX PORT", ctabla1));
                            talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                            talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                            califx.addCell(talu);
                            talu = new PdfPCell(new Paragraph("ETA DC", ctabla1));
                            talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                            talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                            califx.addCell(talu);
                            
                            talu = new PdfPCell(new Paragraph("FIRMA RECIBIDO", ctabla1));
                            talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                            talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                            califx.addCell(talu);
                        }
                        talu = new PdfPCell(new Paragraph("Vol", ctabla1));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                        califx.addCell(talu);
                        talu = new PdfPCell(new Paragraph("Kgs", ctabla1));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                        califx.addCell(talu);
                        talu = new PdfPCell(new Paragraph("Fecha Captura", ctabla1));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                        califx.addCell(talu);
                        talu = new PdfPCell(new Paragraph("Recibido", ctabla1));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                        califx.addCell(talu);
                        talu = new PdfPCell(new Paragraph("Firma Recibido", ctabla1));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                        califx.addCell(talu);
                        talu = new PdfPCell(new Paragraph("Cuenta", ctabla1));
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu.setBackgroundColor(new Color(0xDD, 0xDD, 0xDD));
                        califx.addCell(talu);
                        int doctos = 0;
                        String volumen = "0", kilos =  "0", pi =  "0", ca =  "0", pa =  "0", co =  "0", con =  "0", at =  "0", bu =  "0"; 
                        String dest = "";
                        String erre="";
                        if( request.getParameter("r")==null){
                        erre="";
                        }else{
                        erre=request.getParameter("r");
                        }
                        
                        System.out.println("detallePdf");
                        if (db.doDB(cd.detallePdf(agrupador, erre))) {
                            for (String[] row : db.getResultado()) {
                                
                                 System.out.println("row[0]"+row[0]);
                                 
                                talu = new PdfPCell(new Paragraph(row[1], ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                califx1.addCell(talu);
                                
                                talu = new PdfPCell(new Paragraph(row[2], ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                talu.setColspan(2);
                                califx1.addCell(talu);
                                
                                talu = new PdfPCell(new Paragraph(row[3], ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                califx1.addCell(talu);
                                
                                talu = new PdfPCell(new Paragraph(row[4], ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                                califx1.addCell(talu);
                                
                                talu = new PdfPCell(new Paragraph(row[5], ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                                califx1.addCell(talu);
                                
                                talu = new PdfPCell(new Paragraph(row[6], ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                                califx1.addCell(talu);
                                
                                talu = new PdfPCell(new Paragraph(row[7], ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                                califx1.addCell(talu);
                                
                                talu = new PdfPCell(new Paragraph(row[8], ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                                califx1.addCell(talu);
                                
                                talu = new PdfPCell(new Paragraph(row[9], ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                                califx1.addCell(talu);
                                
                                talu = new PdfPCell(new Paragraph(row[10], ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                                califx1.addCell(talu);
                                
                                talu = new PdfPCell(new Paragraph(" ", ctabla));
                                talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                                califx1.addCell(talu);
                                
                                
                               //talu = new PdfPCell(new Paragraph(row[4], ctabla));
                               //talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                               //califx1.addCell(talu);
                               //
                               //talu = new PdfPCell(new Paragraph(row[5], ctabla));
                               //talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                               //califx1.addCell(talu);
                               //
                               //talu = new PdfPCell(new Paragraph(row[6], ctabla));
                               //talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                               //califx1.addCell(talu);
                               //
                               //talu = new PdfPCell(new Paragraph(row[7], ctabla));
                               //talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                               //califx1.addCell(talu);
                               //
                               //talu = new PdfPCell(new Paragraph(" ", ctabla));
                               //talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                               //califx1.addCell(talu);
                               //
                               //talu = new PdfPCell(new Paragraph(row[15], ctabla));
                               //talu.setHorizontalAlignment(Element.ALIGN_LEFT);
                               //califx1.addCell(talu);
                                 
                                doctos++;
                                
                               // if (!(row[4].equals(" "))) {
                               //     volumen = volumen + ( row[4]);
                               // }
                               // if (!row[5].equals(" ")) {
                               //     kilos = kilos + (Float.pa if (!(row[4].equals(" "))) {rseFloat(row[5]));
                               // }
                               // if (!row[8].equals(" ")) {
                               //     pi = pi + (Float.parseFloat(row[8]));
                               // }
                               // if (!row[9].equals(" ")) {
                               //     ca = ca + (Float.parseFloat(row[9]));
                               // }
                               // if (!row[10].equals(" ")) {
                               //     pa = pa + (Float.parseFloat(row[10]));
                               // }
                               // if (!row[11].equals(" ")) {
                               //     co = co + (Float.parseFloat(row[11]));
                               // }
                               // if (!row[12].equals(" ")) {
                               //     con = con + (Float.parseFloat(row[12]));
                               // }
                               // if (!row[13].equals(" ")) {
                               //     at = at + (Float.parseFloat(row[13]));
                               // }
                               // if (!row[14].equals(" ")) {
                               //     bu = bu + (Float.parseFloat(row[14]));
                               // }
                            }
                        }
                        if (db.doDB(cd.destinoEmbarque(agrupador, "_"))) {
                            for (String[] row5 : db.getResultado()) {
                                dest = row5[0];
                            }
                        }
                        talu = new PdfPCell(new Paragraph("Documentos " + doctos, ctabla));
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph(" " , ctabla));//Destinos+ dest
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph("", ctabla));
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        talu.setColspan(2);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph("", ctabla));
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph("", ctabla));
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph("" , ctabla));//+ pi
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph("" , ctabla));//+ ca
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph("" , ctabla));//+ pa
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph("" , ctabla));//+ co
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph("" , ctabla));//+ con
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph("" , ctabla));//+ at
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph("" , ctabla));//+ bu
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph("" , ctabla));//+ volumen
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph("" , ctabla));//+ kilos
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph("", ctabla));
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph("", ctabla));//
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph("", ctabla));
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        talu = new PdfPCell(new Paragraph("", ctabla));
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                         talu = new PdfPCell(new Paragraph("", ctabla));
                        talu.setBorder(Rectangle.NO_BORDER);
                        talu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        califx2.addCell(talu);
                        writer.setPageEvent(new PEvent(encabezado, espacio, embarque0, piea, piex));
                        document.open();
                        PdfContentByte cb = writer.getDirectContent(); 
                                Barcode128 code = new Barcode128(); 
                                code.setCodeType(Barcode.EAN13);
                                code.setCode(codigo);   
                                code.setBarHeight(15f);
                                code.setX(2f);                                
                                Image codeImage = code.createImageWithBarcode(cb, Color.black, Color.WHITE);                                
                                codeImage.setAlignment(Element.ALIGN_CENTER);
                        document.add(codeImage);      
                        document.add(califx);
                        //document.add(codeT);
                        document.add(califx1);
                        document.add(califx2);
                        if (e_cuentaPdf.equals("100")) {
                            document.add(bd);
                        }
                                                                
                        document.close();
                        DataOutput dataOutput = new DataOutputStream(response.getOutputStream());
                        byte[] bytes = buffer.toByteArray();
                        response.setContentLength(bytes.length);
                        for (int i = 0; i < bytes.length; i++) {
                            dataOutput.writeByte(bytes[i]);
                        }
                        
                        
                        
                        
                        
                        
                        
        
                           Email correot = new Email();
                           
                            String email = request.getParameter("email");
                            String emailNompdf ="";
                            String EMBARQUE_AGRUPADOR = request.getParameter("EMBARQUE_AGRUPADOR");
                            String LTRANSPORTE_ID = request.getParameter("LTRANSPORTE_ID");
                            String nameLTransporte = request.getParameter("nameLTransporte");
                            boolean correoPdf=true;
                            
                            
                             if (db.doDB("select DISTINCT EMBARQUE_ID,EMBARQUE_AGRUPADOR from TRA_INB_EMBARQUE where EMBARQUE_AGRUPADOR='"+agrupador+"'")) {
                            for (String[] row51 : db.getResultado()) {
                                emailNompdf  = row51[0];
                            }
                        }
                             
                         
                            
                         if(email.equals("t")){   
                           correoPdf=correot.alertaLiberacionV2(bytes, emailNompdf, LTRANSPORTE_ID, nameLTransporte,request.getContextPath());
                         }
                          String bc = request.getParameter("bc");
                         if(bc.equals("1")){  
                            String LTRANSPORTE_IDC = request.getParameter("idc");
                            String nameLTransporteC = request.getParameter("nomc");
                           correoPdf=correot.alertaLiberacionV2Cus(bytes, agrupador, LTRANSPORTE_IDC, nameLTransporteC,request.getContextPath());
                         
                           
                        }
                         
                         
                         
                         
                         
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                    response.getOutputStream().close();
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<script>alert('La session se termino'); top.location.href='" + request.getContextPath() + "/badreq.jsp';</script>");
                    out.println("<script>window.close();</script>");
                } finally {
                    oraDB.close();
                    
                }
            } catch (Exception e) {
                out.println("<p>Error</p>" + e);
            }
%>
