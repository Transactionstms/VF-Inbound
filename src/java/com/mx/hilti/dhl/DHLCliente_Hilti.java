/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.hilti.dhl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author DjRich
 */
public class DHLCliente_Hilti {
    
    private String embarque;

    public String getEmbarque() {
        return embarque;
    }

    public void setEmbarque(String embarque) {
        this.embarque = embarque;
    }

    public String getDHLClient(String requestMessagePath, String httpURL, String responseMessagePath) {

        String file = "";
        try {
            //Preparing file inputstream from a file        
            FileInputStream fis = new FileInputStream(requestMessagePath);

            //Getting size of the stream
            int fisSize = fis.available();
            byte[] buffer = new byte[fisSize];

            //Reading file into buffer                                                                      
            fis.read(buffer);

            String clientRequestXML = new String(buffer);

            /* Preparing the URL and opening connection to the server*/
            URL servletURL = null;
            servletURL = new URL(httpURL);

            HttpURLConnection servletConnection = null;
            servletConnection = (HttpURLConnection) servletURL.openConnection();
            servletConnection.setDoOutput(true);  // to allow us to write to the URL
            servletConnection.setDoInput(true);
            servletConnection.setUseCaches(false);
            servletConnection.setRequestMethod("POST");


            servletConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String len = Integer.toString(clientRequestXML.getBytes().length);
            //System.out.println("length " + len);
            servletConnection.setRequestProperty("Content-Length", len);
            //servletConnection.setRequestProperty("Content-Language", "en-US");  

            /*Code for sending data to the server*/
            /*DataOutputStream dataOutputStream;
             dataOutputStream = new DataOutputStream(servletConnection.getOutputStream());
                                
             ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();*/
            //servletConnection.setReadTimeout(10000);
            servletConnection.connect();
            OutputStreamWriter wr = new OutputStreamWriter(servletConnection.getOutputStream());
            wr.write(clientRequestXML);
            wr.flush();
            wr.close();

            /*byte[] dataStream = clientRequestXML.getBytes();
             dataOutputStream.write(dataStream);  //Writing data to the servlet
             dataOutputStream.flush();
             dataOutputStream.close();*/

            /*Code for getting and processing response from DHL's servlet*/
            InputStream inputStream = null;
            inputStream = servletConnection.getInputStream();
            StringBuffer response = new StringBuffer();
            int printResponse;

            //Reading the response into StringBuffer
            while ((printResponse = inputStream.read()) != -1) {
                response.append((char) printResponse);
            }
            inputStream.close();
            System.out.println();


            //System.out.println(response.toString());

            //Calling filewriter to write the response to a file
            file = fileWriter(response.toString(), responseMessagePath);
            String strResponse=response.toString();
            try{
            this.embarque= strResponse.substring(strResponse.lastIndexOf("<AirwayBillNumber>"),strResponse.lastIndexOf("</AirwayBillNumber>")).replaceAll("<AirwayBillNumber>", "");
            }
            catch(Exception e){this.embarque="";}
        } catch (Throwable mfURLex) {
            System.out.println("MalformedURLException " + mfURLex.getMessage());
            mfURLex.printStackTrace();
        }

        return file;


    }

    private static String fileWriter(String strResponse, String responseMessagePath) {

        //System.out.println("entra fileWriter>>>");
        DateFormat today = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_SSS");

        //String path = responseMessagePath;
        //String responseFileName = "Dhawal.xml";
        String responseFileName = checkForRootElement(strResponse) + "_" + today.format(new java.util.Date());

        String ufn = responseMessagePath + responseFileName;
        File resFile = new File(ufn + ".xml");
        //  File resFile = new File(ufn+".pdf");
        int i = 0;
        try {
            //create file and if it already exits
            //if file exist add counter to it
            while (!resFile.createNewFile()) {
                resFile = new File(ufn + "_" + (++i) + ".xml");
                //System.out.println("resFile>>>" + resFile);
            }
            OutputStream output = new FileOutputStream(resFile);  //pintar los nodos de respuesta del WS 
            PrintStream p = null; // declare a print stream object 
            // Connect print stream to the output stream
            p = new PrintStream(output);
            p.println(strResponse);
            p.close();
            //com.management.util.Loggin.info("Response received and saved successfully at :" + responseMessagePath + "\n");
            //com.management.util.Loggin.info("The file name is :" + resFile.getName());
        } catch (Throwable e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return resFile.getName();
    }

    private static String checkForRootElement(String strResponse) {
        Element element = null;
        try {
            //System.out.println("checkForRootElement>>>");
            byte[] byteArray = strResponse.getBytes("UTF-8");
            ByteArrayInputStream baip = new ByteArrayInputStream(byteArray);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.parse(baip); //Parsing the inputstream
            element = doc.getDocumentElement(); //getting the root element           

        } catch (Exception e) {
            System.out.println("Exception in checkForRootElement " + e.getMessage());
        }
        String rootElement = element.getTagName();
        //Check if root element has res: as prefix

        if (rootElement.startsWith("res:") || rootElement.startsWith("req:") || rootElement.startsWith("err:") || rootElement.startsWith("edlres:") || rootElement.startsWith("ilres:")) {

            int index = rootElement.indexOf(":");

            rootElement = rootElement.substring(index + 1);
        }
        return rootElement; // returning the value of the root element
    } //end of checkForRootElemen
}
