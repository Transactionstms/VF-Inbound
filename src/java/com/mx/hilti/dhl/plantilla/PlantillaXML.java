/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.hilti.dhl.plantilla;

import com.bean.BodegaBean;
import com.onest.util.Util;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author RicardoMartinez
 */
public class PlantillaXML {
    
    
    public String getXML( String zipCode, String piezas, String peso, BodegaBean bodegaBean){
        String salida = "";
        
        
        try {
            Util util = new Util();
            peso = util.validacionPeso(peso);
            
            
            Double parseKilo = 0.0;
            double pesoKilo =0.0;
            parseKilo = Double.parseDouble(peso);
            
            if(parseKilo<1){
                pesoKilo =1;
            }else{
                pesoKilo = parseKilo;
            }
            
            
            SimpleDateFormat formato = new SimpleDateFormat("dd_MM_yyyy_hh_mm_s");
            String fecha = formato.format(new Date());
            int numPiezas = Integer.parseInt(piezas);
            String file_xml = com.onest.util.Util.INICIO_RUTA+"\\fuente\\Valid__Quote_MX_MX_WithAcctProdInsurance_"+zipCode+"_"+fecha+".xml"; 
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //String  cabezera = " xmlns:p=\"http://www.dhl.com\" xmlns:p1=\"http://www.dhl.com/datatypes\" xmlns:p2=\"http://www.dhl.com/DCTRequestdatatypes\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.dhl.com DCT-req.xsd\"";
            String  cabezera = "http://www.dhl.com";
            String  cabezera2 = "http://www.dhl.com";
            
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("p:DCTRequest");
            
            
            rootElement.setAttribute( "xmlns:p", "http://www.dhl.com");
            rootElement.setAttribute( "xmlns:p1", "http://www.dhl.com/datatypes");
            rootElement.setAttribute( "xmlns:p2", "http://www.dhl.com/DCTRequestdatatypes");
            rootElement.setAttribute( "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute( "xsi:schemaLocation", "http://www.dhl.com DCT-req.xsd");
            
            doc.appendChild(rootElement);

             Element GetQuote = doc.createElement("GetQuote");
            rootElement.appendChild(GetQuote);
            
            // ELEMENTO REQUEST
            Element elementtRequest = doc.createElement("Request");
            GetQuote.appendChild(elementtRequest);

         
            Element eleemtRServiceHeader = doc.createElement("ServiceHeader");
            elementtRequest.appendChild(eleemtRServiceHeader);

            Element MessageTime = doc.createElement("MessageTime");
            MessageTime.appendChild(doc.createTextNode(this.formatoFecha()+"T11:28:56.000-08:00"));
            eleemtRServiceHeader.appendChild(MessageTime);

            Element MessageReference = doc.createElement("MessageReference");
            MessageReference.appendChild(doc.createTextNode("1234567890123456789012345678901"));
            eleemtRServiceHeader.appendChild(MessageReference);

            Element SiteID = doc.createElement("SiteID");
            SiteID.appendChild(doc.createTextNode("Hiltim"));
            eleemtRServiceHeader.appendChild(SiteID);

            Element Password = doc.createElement("Password");
            Password.appendChild(doc.createTextNode("JandwY6Z"));
            eleemtRServiceHeader.appendChild(Password);

            //ELEMENTO FROM
            Element elementtFrom = doc.createElement("From");
            GetQuote.appendChild(elementtFrom);

            Element CountryCode = doc.createElement("CountryCode");
            CountryCode.appendChild(doc.createTextNode("MX"));
            elementtFrom.appendChild(CountryCode);

            Element Postalcode = doc.createElement("Postalcode");
            Postalcode.appendChild(doc.createTextNode(bodegaBean.getBodegaCpOrigen())); //15700    11510  54879  "54879"
            elementtFrom.appendChild(Postalcode);

            //ELEMENTO BkgDetails
            Element elementtBkgDetails = doc.createElement("BkgDetails");
            GetQuote.appendChild(elementtBkgDetails);

            Element PaymentCountryCode = doc.createElement("PaymentCountryCode");
            PaymentCountryCode.appendChild(doc.createTextNode("MX"));
            elementtBkgDetails.appendChild(PaymentCountryCode);

            Element date = doc.createElement("Date");
            date.appendChild(doc.createTextNode(this.formatoFecha()));
            elementtBkgDetails.appendChild(date);

            Element ReadyTime = doc.createElement("ReadyTime");
            ReadyTime.appendChild(doc.createTextNode("PT10H21M"));
            elementtBkgDetails.appendChild(ReadyTime);

            Element ReadyTimeGMTOffset = doc.createElement("ReadyTimeGMTOffset");
            ReadyTimeGMTOffset.appendChild(doc.createTextNode("+01:00"));
            elementtBkgDetails.appendChild(ReadyTimeGMTOffset);

            Element DimensionUnit = doc.createElement("DimensionUnit");
            DimensionUnit.appendChild(doc.createTextNode("CM"));
            elementtBkgDetails.appendChild(DimensionUnit);

            Element WeightUnit = doc.createElement("WeightUnit");
            WeightUnit.appendChild(doc.createTextNode("KG"));
            elementtBkgDetails.appendChild(WeightUnit);


            Element Pieces = doc.createElement("Pieces");
            elementtBkgDetails.appendChild(Pieces);


            
            
            
            for(int x =1; x<=numPiezas ; x++){
                Element Piece = doc.createElement("Piece");
            Pieces.appendChild(Piece);


            Element PieceID = doc.createElement("PieceID");
            PieceID.appendChild(doc.createTextNode(String.valueOf(x)));
            Piece.appendChild(PieceID);
                Element Height = doc.createElement("Height");
            Height.appendChild(doc.createTextNode("10"));
            Piece.appendChild(Height);
            
            
            Element Depth = doc.createElement("Depth");
            Depth.appendChild(doc.createTextNode( "10"));
            Piece.appendChild(Depth);
            
            
            Element Width = doc.createElement("Width");
            Width.appendChild(doc.createTextNode("10"));
            Piece.appendChild(Width);
            
            BigDecimal a = new BigDecimal(String.valueOf(pesoKilo/numPiezas));
            BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_DOWN);
            
            Element Weight = doc.createElement("Weight");
            Weight.appendChild(doc.createTextNode(String.valueOf(roundOff.toPlainString())));//
            Piece.appendChild(Weight);
            }
            
            
            
            
            Element PaymentAccountNumber = doc.createElement("PaymentAccountNumber");
            PaymentAccountNumber.appendChild(doc.createTextNode("988101830"));
            elementtBkgDetails.appendChild(PaymentAccountNumber);
            
            Element IsDutiable = doc.createElement("IsDutiable");
            IsDutiable.appendChild(doc.createTextNode("N"));
            elementtBkgDetails.appendChild(IsDutiable);
            
            
            Element NetworkTypeCode = doc.createElement("NetworkTypeCode");
            NetworkTypeCode.appendChild(doc.createTextNode("AL"));
            elementtBkgDetails.appendChild(NetworkTypeCode);
            
            
            Element QtdShp = doc.createElement("QtdShp");
            elementtBkgDetails.appendChild(QtdShp);
            //G DOMESTIC ECONOMY SELECT
            //N DOMESTIC EXPRESS
            Element GlobalProductCode = doc.createElement("GlobalProductCode");
            GlobalProductCode.appendChild(doc.createTextNode("G"));//N G
            QtdShp.appendChild(GlobalProductCode);
            
            Element LocalProductCode = doc.createElement("LocalProductCode");
            LocalProductCode.appendChild(doc.createTextNode("G"));//N G
            QtdShp.appendChild(LocalProductCode);
            
            //Element InsuredValue = doc.createElement("InsuredValue");
            //InsuredValue.appendChild(doc.createTextNode("500.000"));
            //elementtBkgDetails.appendChild(InsuredValue);
            
            Element InsuredCurrency = doc.createElement("InsuredCurrency");
            InsuredCurrency.appendChild(doc.createTextNode("MXN"));
            elementtBkgDetails.appendChild(InsuredCurrency);
            
            
             //ELEMENTO <To
            Element elementTo = doc.createElement("To");
            GetQuote.appendChild(elementTo);
            
            CountryCode = doc.createElement("CountryCode");
            CountryCode.appendChild(doc.createTextNode("MX"));
            elementTo.appendChild(CountryCode);
            
            Postalcode = doc.createElement("Postalcode");
            Postalcode.appendChild(doc.createTextNode(zipCode));//44950
            elementTo.appendChild(Postalcode);
            
             //ELEMENTO <To
            Element DutiableDutiable = doc.createElement("To");
            GetQuote.appendChild(elementTo);
            
            
             //ELEMENTO <To
           // Element Dutiable = doc.createElement("Dutiable");
           // GetQuote.appendChild(Dutiable);
            
            //Element DeclaredCurrency = doc.createElement("DeclaredCurrency");
            //DeclaredCurrency.appendChild(doc.createTextNode("MXN"));
            //Dutiable.appendChild(DeclaredCurrency);
            
            //Element DeclaredValue = doc.createElement("DeclaredValue");
            //DeclaredValue.appendChild(doc.createTextNode("1002.0"));
            //Dutiable.appendChild(DeclaredValue);
            
            
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(file_xml));
            transformer.transform(source, result); 
            salida = file_xml;            
            //com.management.util.Loggin.info("File saved!");
        } catch (Throwable ex) {
            ex.printStackTrace(System.err);
            
        }
        return salida;
    }
    
    
     public String formatoFecha() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(new Date());
    }
    
    
}
