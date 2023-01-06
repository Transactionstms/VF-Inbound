package com.mx.hilti.dhl;

//IO Classes
import java.io.FileInputStream;                                         
import java.io.InputStream;                                                             
import java.io.ObjectOutputStream;                              
import java.io.DataOutputStream;                                        
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;           
import java.io.ObjectInputStream;                                       
import java.io.DataInputStream;                                 
import java.io.IOException;                                                             
import java.net.MalformedURLException;
import java.io.File;
// Net classes
import java.net.URL;                                                                                      
import java .net.URLConnection;                                 

//Text Classes
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//Util classes
import java.util.Date;
import java.util.zip.GZIPOutputStream;  

//Parse Packages
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 *   This class contains is a sample client class used to send request XML messages to XML Shipping service of DHL
 * 
 *   @author Dhawal Jogi (Infosys)
 **/
public class  DHLClient
{        
                public static void main(String[] args)
                {
                         if(args.length != 3)
                        {
                            System.out.println("Usage : java DHLClient Request XML MessagePath  httpURL ResponseXMLMessage Path \n");
                            System.out.println(" where \n");
                            System.out.println("Request XML MessagePath : The complete path of the request XML message to be send. E.g. C:\\RequestXML\\ShipmentValidateRequest.xml \n");
                            System.out.println("httpURL : The complete url of the server. E.g. http://IP ADDRESS:PORT NUMBER//SERVLET PATH \n");
                            System.out.println("ResponseXMLMessage : The complete directory path where the respose XML messages are to be saved. E.g. C:\\ResponseXML\\\n");
                            System.exit(9);    
                        }
                        else
                        {
                            DHLClient dhlClient = new DHLClient(args[0],args[1],args[2]);
                        }
                    
                }  //end of main method
              
               /**
                * Private method to write the response from the input stream to a file in local directory.
                * @param     strResponse  The string format of the response XML message
                **/
               private static void fileWriter(String strResponse , String responseMessagePath)
                {
                     
                         DateFormat today = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_SSS");

                         //String path = responseMessagePath;
                         //String responseFileName = "Dhawal.xml";
                         String responseFileName = checkForRootElement(strResponse)+"_"+today.format(new java.util.Date());

                         String ufn = responseMessagePath + responseFileName;
                         File resFile = new File(ufn+".xml");
                        //  File resFile = new File(ufn+".pdf");
                         int i=0;
                         try {
	                        	//create file and if it already exits
								//if file exist add counter to it
								while(!resFile.createNewFile()){
	                           	 resFile = new File(ufn + "_" + (++i) +".xml");
	                            }
                                OutputStream output = new FileOutputStream(resFile);
                                PrintStream p = null; // declare a print stream object 
                                // Connect print stream to the output stream
                                p = new PrintStream( output );
                                p.println (strResponse);
                                p.close();
                                System.out.println("Response received and saved successfully at :" + responseMessagePath +"\n");
                                System.out.println("The file name is :" + resFile.getName());
                        }catch(Exception e){
                                System.err.println(e.getMessage());
                        }
                }// end of  fileWriter method
                
                /**
                * Returns the value of the root element of the response XML message send by DHL Server
                * @param     strResponse  The string format of the response XML message
                * @return      name of the root element of type string
                **/
                private static String checkForRootElement(String strResponse)
                {
                    Element element = null;
                    try
                    {
                        byte [] byteArray = strResponse.getBytes();
                        ByteArrayInputStream baip = new ByteArrayInputStream( byteArray);
                        DocumentBuilderFactory factory       = DocumentBuilderFactory.newInstance();
                        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
                        Document doc = documentBuilder.parse(baip); //Parsing the inputstream
                        element = doc.getDocumentElement(); //getting the root element           
                                                
                    }
                    catch(Exception e)
                    {
                        System.out.println("Exception in checkForRootElement "+e.getMessage());
                    }
                        String rootElement = element.getTagName();
                        //Check if root element has res: as prefix
                        
                        if(rootElement.startsWith("res:")||rootElement.startsWith("req:")||rootElement.startsWith("err:")||rootElement.startsWith("edlres:")||rootElement.startsWith("ilres:"))
                        {
                        
                            int index = rootElement.indexOf(":");
                        
                            rootElement = rootElement.substring(index+1);
                        }
                    return rootElement; // returning the value of the root element
                } //end of checkForRootElement method

 /*
       This constructor is used to do the following important operations
        1) Read a request XML
        2) Connect to Server
        3) Send the request XML
        4) Receive response XML message
        5) Calls a private method to write the response XML message
       
        @param requestMessagePath  The path of the request XML message to be send to server
        @param httpURL The http URL to connect ot the server (e.g. http://<ip address>:<port>/application name/Servlet name)
        @param responseMessagePath The path where the response XML message is to be stored
*/
public DHLClient(String requestMessagePath, String httpURL, String responseMessagePath)
{
             try
                    {
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
                                servletConnection = (HttpURLConnection)servletURL.openConnection();
                                servletConnection.setDoOutput(true);  // to allow us to write to the URL
                                servletConnection.setDoInput(true);
                                servletConnection.setUseCaches(false); 
								servletConnection.setRequestMethod("POST");


								servletConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");	
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
                                while ((printResponse=inputStream.read())!=-1) 
                                {
                                    response.append((char) printResponse);
                                }
                                inputStream.close();
								System.out.println();
								

								//System.out.println(response.toString());
                                
                                 //Calling filewriter to write the response to a file
                                fileWriter(response.toString() , responseMessagePath);
                    }
                     catch(MalformedURLException mfURLex)
                    {
                        System.out.println("MalformedURLException "+mfURLex.getMessage());
                    }
                    catch(IOException e)
                    {
                        System.out.println("IOException "+e.getMessage());
                        //e.printStackTrace();
                    }
                   

                }
}// End of Class DHLClient
