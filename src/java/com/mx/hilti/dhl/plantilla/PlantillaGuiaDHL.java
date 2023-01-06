/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.hilti.dhl.plantilla;

import com.bean.BodegaBean;
import com.bean.DestinoHiltiBean;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author RicardoMartinez
 */
public class PlantillaGuiaDHL {
    
    public String generarXML(DestinoHiltiBean bean, String piezas, String referencia, List listaPeso,String documento,String pesoTotal, BodegaBean bodegaBean){
        
        String plantilla = com.onest.util.Util.INICIO_RUTA+"fuente\\ShipmentValidateRequest_dhl.xml";
        
         try {

             String varPeso =  bean.getPeso();
             
             double varPedoD = Double.parseDouble(varPeso);
             int digitos =1;
             int cifras=(int) Math.pow(10,digitos);
             String peso = String.valueOf(Math.rint(varPedoD*cifras)/cifras);
             
             
            PlantillaGuiaDHL xml = new PlantillaGuiaDHL();
            
            int piezaInt = Integer.parseInt(piezas);

           DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("req:ShipmentValidateRequest");
            rootElement.setAttribute("xsi:schemaLocation", "http://www.dhl.com ship-val-req.xsd");
            rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute("xmlns:req", "http://www.dhl.com");

            doc.appendChild(rootElement);

            Element elementRequest = doc.createElement("Request");
            rootElement.appendChild(elementRequest);

            Element elementtRequest = doc.createElement("ServiceHeader");
            elementRequest.appendChild(elementtRequest);

            Element SiteID = doc.createElement("SiteID");
            SiteID.appendChild(doc.createTextNode("Hiltim"));
            elementtRequest.appendChild(SiteID);

            Element Password = doc.createElement("Password");
            Password.appendChild(doc.createTextNode("JandwY6Z"));
            elementtRequest.appendChild(Password);

            Element RequestedPickupTime = doc.createElement("RequestedPickupTime");
            RequestedPickupTime.appendChild(doc.createTextNode("Y"));
            rootElement.appendChild(RequestedPickupTime);

            Element NewShipper = doc.createElement("NewShipper");
            NewShipper.appendChild(doc.createTextNode("N"));
            rootElement.appendChild(NewShipper);

            Element LanguageCode = doc.createElement("LanguageCode");
            LanguageCode.appendChild(doc.createTextNode("ES"));
            rootElement.appendChild(LanguageCode);

            Element PiecesEnabled = doc.createElement("PiecesEnabled");
            PiecesEnabled.appendChild(doc.createTextNode("Y"));
            rootElement.appendChild(PiecesEnabled);

            Element Billing = doc.createElement("Billing");
            rootElement.appendChild(Billing);

            Element ShipperAccountNumber = doc.createElement("ShipperAccountNumber");
            ShipperAccountNumber.appendChild(doc.createTextNode("988101830"));//NUEVO XCAMBIO
            Billing.appendChild(ShipperAccountNumber);

            Element ShippingPaymentType = doc.createElement("ShippingPaymentType");
            ShippingPaymentType.appendChild(doc.createTextNode("S"));
            Billing.appendChild(ShippingPaymentType);

            Element BillingAccountNumber = doc.createElement("BillingAccountNumber");
            BillingAccountNumber.appendChild(doc.createTextNode("988101830"));//"988101830"
            Billing.appendChild(BillingAccountNumber);

            Element DutyPaymentType = doc.createElement("DutyPaymentType");
            DutyPaymentType.appendChild(doc.createTextNode("S"));
            Billing.appendChild(DutyPaymentType);

            Element Consignee = doc.createElement("Consignee");
            rootElement.appendChild(Consignee);

            Element CompanyName = doc.createElement("CompanyName");
            CompanyName.appendChild(doc.createTextNode(bean.getStrtName()));//"MIGUEL RUIZ CALDERON"
            Consignee.appendChild(CompanyName);

            Element AddressLine = doc.createElement("AddressLine");     
            String colonyName = "";
            if(bean.getContactoDHL()==""||bean.getContactoDHL()==null){
             colonyName =bean.getColonyName();
            }
            
            else{
             colonyName =bean.getDestino_domicilio() + " " + bean.getColonyName();
            
            }
            
            if(colonyName.length()>35){
                colonyName = colonyName.substring(0,34);
            }
            AddressLine.appendChild(doc.createTextNode(colonyName));//"Transportistas 508"
            Consignee.appendChild(AddressLine);

            String city =   bean.getDestino_ciudad() +  " " + bean.getDestino_colonia() + " " + bean.getDestino_estado();
            
            if(city.length()> 35){
                city = city.substring(0, 35);
            }
            
            
            Element City = doc.createElement("City");
            City.appendChild(doc.createTextNode( city ));//"Tlaquepaque"bean.getStrtName()
            Consignee.appendChild(City);

            Element Division = doc.createElement("Division");
            Division.appendChild(doc.createTextNode("13"));
            Consignee.appendChild(Division);

            Element PostalCode = doc.createElement("PostalCode");
            PostalCode.appendChild(doc.createTextNode(bean.getZipCode()));//"45580"
            Consignee.appendChild(PostalCode);

            Element CountryCode = doc.createElement("CountryCode");
            CountryCode.appendChild(doc.createTextNode("MX"));
            Consignee.appendChild(CountryCode);

            Element CountryName = doc.createElement("CountryName");
            CountryName.appendChild(doc.createTextNode("Mexico"));
            Consignee.appendChild(CountryName);

            Element Contact = doc.createElement("Contact"); 
            Consignee.appendChild(Contact);

            Element PersonName = doc.createElement("PersonName");
            
            String contacto = "";
            if(bean.getContactoDHL()==""||bean.getContactoDHL()==null){
                contacto = bean.getDestino_domicilio();
            }
            else{
            contacto = "Contacto: " +bean.getContactoDHL();
            }
            
            PersonName.appendChild(doc.createTextNode(contacto));//"MIGUEL RUIZ CALDERON"bean.getStrtName() Este es el nombre de quien recoge
            Contact.appendChild(PersonName);

            Element PhoneNumber = doc.createElement("PhoneNumber");
            PhoneNumber.appendChild(doc.createTextNode("00000"));
            Contact.appendChild(PhoneNumber);

            Element PhoneExtension = doc.createElement("PhoneExtension");
            PhoneExtension.appendChild(doc.createTextNode("123"));
            Contact.appendChild(PhoneExtension);

            Element FaxNumber = doc.createElement("FaxNumber");
            FaxNumber.appendChild(doc.createTextNode("999999999"));
            Contact.appendChild(FaxNumber);

            Element Reference = doc.createElement("Reference");
            rootElement.appendChild(Reference);

            Element ReferenceID = doc.createElement("ReferenceID");
            String documentosTmp = (documento.length()>35)?documento.substring(0, 35):documento;
            ReferenceID.appendChild(doc.createTextNode(documentosTmp));//"NUMERO DE REFERENCIAbean.getReferencia()
            Reference.appendChild(ReferenceID);

            Element ShipmentDetails = doc.createElement("ShipmentDetails");
            rootElement.appendChild(ShipmentDetails);
            
            Element NumberOfPieces = doc.createElement("NumberOfPieces");
            NumberOfPieces.appendChild(doc.createTextNode( String.valueOf(piezaInt)));//"1"
            ShipmentDetails.appendChild(NumberOfPieces);
            
            Element Pieces = doc.createElement("Pieces");
            ShipmentDetails.appendChild(Pieces);
            
          
            int count=0;
            int tmpDocCount =0;
            for(int x =1; x<= piezaInt; x++  ){
                 
             Element Piece = doc.createElement("Piece");
            Pieces.appendChild(Piece);
            
            Element PieceID = doc.createElement("PieceID");
            PieceID.appendChild(doc.createTextNode( String.valueOf(x) ));
            Piece.appendChild(PieceID);
            
            Element PackageType = doc.createElement("PackageType");
            PackageType.appendChild(doc.createTextNode("EE"));
            Piece.appendChild(PackageType);
            
            double pesTmp = 0.0;
            
            int cifrastmp =(int) Math.pow(10,digitos);
             String tmp = "";
            
            if(listaPeso.size() == 1){
                pesTmp = Double.parseDouble(listaPeso.get(0).toString() );
            }else{
                
                if(listaPeso != null){
                if(listaPeso.size() > 1){
                    String [] tmpDoc = documento.split(",");
                    
                    pesTmp = Double.parseDouble(listaPeso.get(tmpDocCount).toString() );
                    for(int i=0;x<tmpDoc.length; i++){
                        tmpDocCount++;
                        break;
                    }
                    
                    
                    
                    
                }        
                }else{
                    pesTmp =0.0;
                }
                
                
            }
            tmp = String.valueOf(Math.rint(pesTmp*cifrastmp)/cifrastmp);
            pesTmp = Double.parseDouble(tmp);
            BigDecimal a = new BigDecimal(String.valueOf(pesTmp/piezaInt));
            BigDecimal roundOff = a.setScale(1, BigDecimal.ROUND_DOWN);
            Element Weight = doc.createElement("Weight");
            Weight.appendChild(doc.createTextNode(roundOff.toPlainString()));
            Piece.appendChild(Weight);
            
            int cifraspesototal =(int) Math.pow(10,digitos);
             String tmptotal = "";
             double pesoTotalTmp = Double.parseDouble(pesoTotal);
             tmptotal = String.valueOf(Math.rint(pesoTotalTmp*cifraspesototal)/cifraspesototal);
            pesoTotal = tmptotal;
            
            Element DimWeight = doc.createElement("DimWeight");
            DimWeight.appendChild(doc.createTextNode(pesoTotal));
            Piece.appendChild(DimWeight);
            
            Element Width = doc.createElement("Width");
            Width.appendChild(doc.createTextNode("10"));
            Piece.appendChild(Width);
            
            Element Height = doc.createElement("Height");
            Height.appendChild(doc.createTextNode("10"));
            Piece.appendChild(Height);
            
            Element Depth = doc.createElement("Depth");
            Depth.appendChild(doc.createTextNode("10"));
            Piece.appendChild(Depth);
            count++;
            }
            
           
            
            Element Weight = doc.createElement("Weight");
            Weight.appendChild(doc.createTextNode(pesoTotal));
            ShipmentDetails.appendChild(Weight);
            
            Element WeightUnit = doc.createElement("WeightUnit");
            WeightUnit.appendChild(doc.createTextNode("K"));
            ShipmentDetails.appendChild(WeightUnit);
            //G DOMESTIC ECONOMY SELECT
            //N DOMESTIC EXPRESS            
            Element GlobalProductCode = doc.createElement("GlobalProductCode");
            GlobalProductCode.appendChild(doc.createTextNode("G"));
            ShipmentDetails.appendChild(GlobalProductCode);
            
            Element LocalProductCode = doc.createElement("LocalProductCode");
            LocalProductCode.appendChild(doc.createTextNode("G"));
            ShipmentDetails.appendChild(LocalProductCode);
            
            Element Date = doc.createElement("Date");
            Date.appendChild(doc.createTextNode(  xml.formatofecha()  ));
            ShipmentDetails.appendChild(Date);
            
            Element Contents = doc.createElement("Contents");
            Contents.appendChild(doc.createTextNode("REFACCIONES"));
            ShipmentDetails.appendChild(Contents);
            
            Element DoorTo = doc.createElement("DoorTo");
            DoorTo.appendChild(doc.createTextNode("DD"));
            ShipmentDetails.appendChild(DoorTo);
            
            Element DimensionUnit = doc.createElement("DimensionUnit");
            DimensionUnit.appendChild(doc.createTextNode("C"));
            ShipmentDetails.appendChild(DimensionUnit);
            
            Element IsDutiable = doc.createElement("IsDutiable");
            IsDutiable.appendChild(doc.createTextNode("N"));
            ShipmentDetails.appendChild(IsDutiable);
            
            Element CurrencyCode = doc.createElement("CurrencyCode");
            CurrencyCode.appendChild(doc.createTextNode("MXN"));
            ShipmentDetails.appendChild(CurrencyCode);
            
            Element Shipper = doc.createElement("Shipper");
            rootElement.appendChild(Shipper);
           
            Element ShipperID = doc.createElement("ShipperID");
            ShipperID.appendChild(doc.createTextNode("988101830"));
            Shipper.appendChild(ShipperID);
            
            CompanyName = doc.createElement("CompanyName");
            CompanyName.appendChild(doc.createTextNode("HILTI MEXICAN SA. DE CV."));
            Shipper.appendChild(CompanyName);
            
            Element RegisteredAccount = doc.createElement("RegisteredAccount");
            RegisteredAccount.appendChild(doc.createTextNode("988101830"));
            Shipper.appendChild(RegisteredAccount);

            String addresLine = bodegaBean.getBodegaDireccion();
            if(addresLine.length()> 35){
                addresLine = addresLine.substring(0, 35);
            }            
            AddressLine = doc.createElement("AddressLine");
            AddressLine.appendChild(doc.createTextNode(addresLine));//"CARRETERA ANTIGUA MEX-CUATITLAN"
            Shipper.appendChild(AddressLine);
            
            Element City_2 = doc.createElement("City");
            City_2.appendChild(doc.createTextNode(bodegaBean.getBodegaNombre()));//"LOMA BONITA-CUATITLAN"
            Shipper.appendChild(City_2);
            
             Division = doc.createElement("Division");
            Division.appendChild(doc.createTextNode("Mexico"));
            Shipper.appendChild(Division);
            
            Element DivisionCode = doc.createElement("DivisionCode");
            DivisionCode.appendChild(doc.createTextNode("MX"));
            Shipper.appendChild(DivisionCode);
            
            PostalCode = doc.createElement("PostalCode");//TODO PROBAR 
            PostalCode.appendChild(doc.createTextNode(bodegaBean.getBodegaCpOrigen()));//doc.createTextNode("54879")
            Shipper.appendChild(PostalCode);
            
            CountryCode = doc.createElement("CountryCode");
            CountryCode.appendChild(doc.createTextNode("MX"));
            Shipper.appendChild(CountryCode);
            
            CountryName = doc.createElement("CountryName");
            CountryName.appendChild(doc.createTextNode("Mexico"));
            Shipper.appendChild(CountryName);
            
            Contact = doc.createElement("Contact");
            Shipper.appendChild(Contact);
            
            PersonName = doc.createElement("PersonName");
            PersonName.appendChild(doc.createTextNode("Cazares"));
            Contact.appendChild(PersonName);
            
            PhoneNumber = doc.createElement("PhoneNumber");
            PhoneNumber.appendChild(doc.createTextNode("0180061"));
            Contact.appendChild(PhoneNumber);
            
            PhoneExtension = doc.createElement("PhoneExtension");
            PhoneExtension.appendChild(doc.createTextNode("0"));
            Contact.appendChild(PhoneExtension);
            
            FaxNumber = doc.createElement("FaxNumber");
            FaxNumber.appendChild(doc.createTextNode("0180061"));
            Contact.appendChild(FaxNumber);
            
            Element Telex = doc.createElement("Telex");
            Telex.appendChild(doc.createTextNode("0180061"));
            Contact.appendChild(Telex);
            
            Element Email = doc.createElement("Email");
            Contact.appendChild(Email);
            
            
            Element From = doc.createElement("From");
            From.appendChild(doc.createTextNode("servicio_clientes@hilti.com"));
            Email.appendChild(From);
            
            Element To = doc.createElement("To");
            To.appendChild(doc.createTextNode("servicio_clientes@hilti.com"));
            Email.appendChild(To);
            
            Element cc = doc.createElement("cc");
            cc.appendChild(doc.createTextNode("servicio_clientes@hilti.com"));
            Email.appendChild(cc);
            
            Element Subject = doc.createElement("Subject");
            Subject.appendChild(doc.createTextNode("Guia de 9228002335"));
            Email.appendChild(Subject);
            
            Element ReplyTo = doc.createElement("ReplyTo");
            ReplyTo.appendChild(doc.createTextNode("servicio_clientes@hilti.com"));
            Email.appendChild(ReplyTo);
            
            Element Body = doc.createElement("Body");
            Body.appendChild(doc.createTextNode("servicio_clientes@hilti.com"));
            Email.appendChild(Body);
            
            Element EProcShip = doc.createElement("EProcShip");
            EProcShip.appendChild(doc.createTextNode("N"));
            rootElement.appendChild(EProcShip);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(com.onest.util.Util.INICIO_RUTA+"fuente\\ShipmentValidateRequest_dhl.xml"));

            transformer.transform(source, result);

            


        } catch (TransformerException ex) {
            System.out.println(ex.getMessage());
        } catch (ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
        }
        
        return plantilla;
        
    }
    
    public String formatofecha(){
        String salida = "";
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        salida = formato.format(new Date());
        return salida ;
    }
    
}
