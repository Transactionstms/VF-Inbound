/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PRAcroForm;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.SimpleBookmark;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author RicardoMartinez
 */
public class ConcatenarPDF {
    
    public static void main(String[] args){
        ConcatenarPDF pdf = new ConcatenarPDF();
        pdf.concatenarPDF();
    }
    
    public String concatenarPDF(){
        String salida = "";
        
        String pdf1 = com.onest.util.Util.INICIO_RUTA+"RICH\\PDF-TMS\\MARTI-MEXICO\\cartaporte.jasper";
        String pdf2 = com.onest.util.Util.INICIO_RUTA+"RICH\\PDF-TMS\\MARTI-MEXICO\\etiquetas.jasper";
        String pdfFinal = com.onest.util.Util.INICIO_RUTA+"RICH\\PDF-TMS\\MARTI-MEXICO\\finalPdf.pdf";
        
         ArrayList<String> argumnets= new ArrayList<String>(); 
         argumnets.add(pdf1);
	        argumnets.add(pdf2);
	        argumnets.add(pdfFinal);
        this.getConcatenatedPdf(argumnets);
        return salida;
    }
    
    
        public static void getConcatenatedPdf(ArrayList<String> args) {
    	
        if (args.size() < 2) {
            System.err.println("arguments: file1 [file2 ...] destfile");
        }
        else {
        	System.out.println("Pdf Merging");
            try {
                int pageOffset = 0;
               // ArrayList<Object> master = new ArrayList<Object>();
                List<HashMap<String, Object>> master = new ArrayList<HashMap<String, Object>>();
                int f = 0;
                String outFile = args.get(args.size()-1);
                Document document = null;
                PdfCopy  writer = null;
                while (f < args.size()-1) {
                    // create a reader for a certain document
                    PdfReader reader = new PdfReader(args.get(f));
                    reader.consolidateNamedDestinations();
                    // retrieve the total number of pages
                    int n = reader.getNumberOfPages();
                    n=n-1;
                   // List<Object> bookmarks = SimpleBookmark.getBookmark(reader);
                     List<HashMap<String, Object>> bookmarks = SimpleBookmark.getBookmark(reader);
                    if (bookmarks != null) {
                        if (pageOffset != 0)
                            SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset, null);
                        master.addAll(bookmarks);
                    }
                    pageOffset += n;
                    
                    if (f == 0) {
                        // step 1: creation of a document-object
                        document = new Document(reader.getPageSizeWithRotation(1));
                        // step 2: create a writer that listens to the document
                        writer = new PdfCopy(document, new FileOutputStream(outFile));
                        // step 3: open the document
                        document.open();
                    }
                    // step 4: add content
                    PdfImportedPage page;
                    for (int i = 0; i <= n; ) {
                        ++i;
                        page = writer.getImportedPage(reader, i);
                        writer.addPage(page);
                    }
                    PRAcroForm form = reader.getAcroForm();
                    if (form != null)
                        writer.copyAcroForm(reader);
                    f++;
                }
                if (!master.isEmpty()) {
                    writer.  setOutlines  (master);
                }
                // step 5: close the document
                document.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
