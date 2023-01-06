/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.hilti.dhl.plantilla;

import com.bb.web.beans.AWBInfo;
import com.bb.web.beans.ServiceArea;
import com.bb.web.beans.ShipmentEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author RicardoMartinez
 */
public class TrackingRequest {

    public String getXML(String guia) throws TransformerException {
        String salida = "";
        try {

            SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd'T'hh:mm:ss'-07:00'");
            SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
            String fecha = formato.format(new Date());
            //String file_xml = com.onest.util.Util.INICIO_RUTA+"tracking\\TrackingRequest_" + AWBNumber + "" + date.format(new Date()) + ".xml";
            String file_xml = com.onest.util.Util.INICIO_RUTA+"tracking\\TrackingRequest_" + guia + ".xml";

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("req:KnownTrackingRequest");

            rootElement.setAttribute("xmlns:req", "http://www.dhl.com");
            rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute("xsi:schemaLocation", "http://www.dhl.com TrackingRequestKnown.xsd");

            doc.appendChild(rootElement);

            Element Request = doc.createElement("Request");
            rootElement.appendChild(Request);

            Element ServiceHeader = doc.createElement("ServiceHeader");
            Request.appendChild(ServiceHeader);

            Element MessageTime = doc.createElement("MessageTime");
            MessageTime.appendChild(doc.createTextNode(fecha));
            ServiceHeader.appendChild(MessageTime);

            Element MessageReference = doc.createElement("MessageReference");
            MessageReference.appendChild(doc.createTextNode("1234567890123456789012345678"));
            ServiceHeader.appendChild(MessageReference);

            Element SiteID = doc.createElement("SiteID");
            SiteID.appendChild(doc.createTextNode("Hiltim"));
            ServiceHeader.appendChild(SiteID);

            Element Password = doc.createElement("Password");
            Password.appendChild(doc.createTextNode("JandwY6Z"));
            ServiceHeader.appendChild(Password);

            Element LanguageCode = doc.createElement("LanguageCode");
            LanguageCode.appendChild(doc.createTextNode("en"));
            rootElement.appendChild(LanguageCode);

            Element AWBNumber = doc.createElement("AWBNumber");

            String[] tmp = guia.split(",");

            for (int x = 0; x < tmp.length; x++) {
                AWBNumber.appendChild(doc.createTextNode(guia));
                rootElement.appendChild(AWBNumber);
            }

            Element LevelOfDetails = doc.createElement("LevelOfDetails");
            LevelOfDetails.appendChild(doc.createTextNode("LAST_CHECK_POINT_ONLY"));
            rootElement.appendChild(LevelOfDetails);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(file_xml));

            transformer.transform(source, result);
            salida = file_xml;

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        } catch (TransformerConfigurationException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;

    }

    public List<AWBInfo> leerXML(String requestMessagePath) {

        List<AWBInfo> listaAWBInfo = new ArrayList<AWBInfo>();

        List<ShipmentEvent> lista = new ArrayList<ShipmentEvent>();
        List<ServiceArea> tmp = new ArrayList<ServiceArea>();

        try {

            String path = "";
            //requestMessagePath = com.onest.util.Util.INICIO_RUTA+"xml\\DCTResponse_2013_08_03_11_03_43_946.xml";
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            Document doc = docBuilder.parse(new File(path + requestMessagePath));

            doc.getDocumentElement().normalize();

            //System.out.println("El elemento raíz es " + doc.getDocumentElement().getNodeName());
            NodeList listaMonto = doc.getElementsByTagName("AWBInfo");

            int montoTotal = listaMonto.getLength();

            boolean bandera = false;

            for (int i = 0; i < listaMonto.getLength(); i++) {
                bandera = true;
                //              if (i == 1) {
                AWBInfo aWBInfo = new AWBInfo();
                Node persona = listaMonto.item(i);

                if (persona.getNodeType() == Node.ELEMENT_NODE) {

                    Element elemento = (Element) persona;

                    aWBInfo.setAWBNumber(getTagValue("AWBNumber", elemento));

                    NodeList listaShipmentEvent = doc.getElementsByTagName("ShipmentEvent");

                    int ShipmentEvent = listaShipmentEvent.getLength();

                    for (int p = 0; p < ShipmentEvent; p++) {

                        Node personaShipmentEvent = listaShipmentEvent.item(p);

                        if (personaShipmentEvent.getNodeType() == Node.ELEMENT_NODE) {

                            ShipmentEvent shipmentEvent = new ShipmentEvent();

                            Element elementoShipmentEvent = (Element) personaShipmentEvent;

                            shipmentEvent.setDate(getTagValue("Date", elementoShipmentEvent));
                            shipmentEvent.setTime(getTagValue("Time", elementoShipmentEvent));
                            shipmentEvent.setServiceEventEventCode(getTagValue("EventCode", elementoShipmentEvent));
                            shipmentEvent.setServiceEventDescription(getTagValue("Description", elementoShipmentEvent));

                            NodeList listaServiceArea = doc.getElementsByTagName("ServiceArea");

                            int ServiceArea = listaServiceArea.getLength();
                            ServiceArea serviceArea = new ServiceArea();
                            for (int x = 0; x < ServiceArea; x++) {
                                Node personaServiceArea = listaServiceArea.item(p);
                                if (personaServiceArea.getNodeType() == Node.ELEMENT_NODE) {

                                    Element elementoServiceArea = (Element) personaServiceArea;
                                    serviceArea.setServiceAreaCode(getTagValue("ServiceAreaCode", elementoServiceArea));
                                    serviceArea.setDescription(getTagValue("Description", elementoServiceArea));

                                }
                                break;
                            }
                            shipmentEvent.setServiceArea(serviceArea);

                            
                            lista.add(shipmentEvent);
                        }

                    }
                    aWBInfo.setShipmentEvent(lista);
                }
                listaAWBInfo.add(aWBInfo);
//break;
                // }
            }
            String mensaje = "";
            if (bandera == false) {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaAWBInfo;
    }

    private static String getTagValue(String sTag, Element eElement) {

        String salida = "";
        if (eElement.getElementsByTagName(sTag).item(0) != null) {
            NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
            Node nValue = (Node) nlList.item(0);
            salida = nValue.getNodeValue();
        } else {
            salida = "";
        }

        return salida;

    }
}
