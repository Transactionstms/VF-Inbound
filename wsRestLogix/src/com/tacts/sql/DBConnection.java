/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacts.sql;

import com.send.email.Email;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import json.JSONArray;
import json.JSONException;
import json.JSONObject;

/**
 *
 * @author User_Windows10
 */

public class DBConnection {

    private ConnectionPool connectionPool;
    URL url;

    public DBConnection(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
        System.out.println("Conexión D.B inicializada!");
    }

    // Método para ejecutar una consulta (store procedure)
    public List<String> executeStoredProcedureWithQuery(String agenteId) throws IOException, JSONException, SQLException {
    //   logy();
        
        Connection conn = null;
        CallableStatement callableStmt = null;
        ResultSet rs = null;
        List<String> resultList = new ArrayList<>();
        String shipment_id = "";
        int cont = 0;

        try {
            // Obtiene la conexión del pool
            conn = connectionPool.getConnection();

            // Preparar el procedimiento almacenado con un parámetro de entrada
            callableStmt = conn.prepareCall("{call SP_INB_CUSTOMS_WSCONSUM_LOGIX(?,?)}");

            // Asignar el parámetro de entrada usando índice 1
            callableStmt.setString(1, agenteId);  // Primer parámetro de entrada (pAgenteAduanal)

            // Registrar el parámetro de salida como SYS_REFCURSOR (usando Types.REF_CURSOR)
            callableStmt.registerOutParameter(2, java.sql.Types.REF_CURSOR);

            // Ejecutar el procedimiento almacenado
            callableStmt.execute();

            // Obtener el SYS_REFCURSOR del parámetro de salida y convertirlo a ResultSet
            rs = (ResultSet) callableStmt.getObject(2);

            // Procesar el ResultSet (iterar sobre las filas)
            while (rs.next()) {
                // Obtener los valores de las dos columnas
                shipment_id = rs.getString(1);

                // Se elimina el último carácter de la cadena string (si no está vacío)
                if (!shipment_id.isEmpty()) {

                    System.out.println("Cadena de Shipments: " + shipment_id);

                    // Método para consumir el servicio con el token generado y obtener json
                    
                    //if(shipment_id.equals("V000183406")|shipment_id.equals("V000179661")|shipment_id.equals("V000180685")|shipment_id.equals("V000180108")|shipment_id.equals("V000183405")){
                        System.out.println("N° enviando: " + cont);
                    ConsumoLOGIX(shipment_id);
                    //}
                   
                    System.out.println("N° Iteración: " + cont);

                } else {
                    System.out.println("No hay valores para procesar.");
                }
            }
            
            System.out.println("shipment_id: " + shipment_id);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el ResultSet
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Cerrar el CallableStatement y devolver la conexión al pool
            if (callableStmt != null) {
                try {
                    callableStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Liberar la conexión al pool
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }

        // Retornar la lista de resultados concatenados
        return resultList;
    }

    
     public String ConsumoLOGIX3(String path) throws IOException, JSONException, SQLException {
    // Construcción de la URL con parámetros de consulta
 try {
            // URL de la API
            //String urlString = "http://apis.grupologix.com.mx:3000/shipments/"+path;
              String urlString ="https://api-tms-logix.uc.r.appspot.com/shipments/"+path;
            //"http://apis.grupologix.com.mx/traficotms?linkTo=shipmentId_traficotms&equalTo=V000179661";
            URL url = new URL(urlString);

            // Abrir la conexión HTTP
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Agregar headers a la solicitud
            con.setRequestProperty("Accept", "*/*");
            con.setRequestProperty("Accept-Encoding", "gzip,deflate,br");
            con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("Authorization", "c5LTA6WPbMwHhEabYu77nN9cn4VcMj");
            
            // Establecer un User-Agent genérico
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Obtener el código de respuesta
            int responseCode = con.getResponseCode();
            System.out.println("Código de respuesta: " + responseCode);

            // Leer la respuesta si la solicitud fue exitosa
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                // Leer cada línea de la respuesta y agregarla al StringBuilder
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Imprimir la respuesta completa
                System.out.println("Respuesta: " + response.toString());
            } else {
                System.out.println("Error en la solicitud. Código de respuesta: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

     return "";
     }
    //Método para consumir el servicio con el token generado y obtener json
    public String ConsumoLOGIX(String path) throws IOException, JSONException, SQLException {

        // Crear un formateador para convertir la fecha al formato MM/DD/YYYY
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy ");
        System.out.println("entra a consumr");
        //String TokenURLP = "http://apis.grupologix.com.mx/traficotms?linkTo=shipmentId_traficotms&equalTo="+path+"&select=*";
        String responseLine = null;
        String responseLine2 = "";
        String msg_logError = "";
        
        String etaPortDischargeTwo_traficotms  = "";
        String DocumentRecepcionDate_traficotms  = "";
        String d_previousOrigin_traficotms  = "";
        String d_previousDestiny_traficotms  = "";
        String d_previousResult_traficotms  = "";
        String finalProforma_traficotms  = "";
        String d_sendTokens_traficotms  = "";
        String d_receiptPermitsProcessed_traficotms  = "";
        String d_PermitActivation_traficotms  = "";
        String d_AuthorizedPermits_traficotms  = "";
        String d_receiptCA_traficotms  = "";
        String d_completeDocuments_traficotms  = "";
        String d_paidPed_traficotms  = "";
        String d_transportRequest_traficotms  = "";
        String d_modulation_traficotms  = "";
        String arrivalNOVA_traficotms  = "";
        String arrivalGlobalTradeSD_traficotms  = "";
        String d_archiveM_traficotms  = "";
        String d_requestHandling_traficotms  = "";
        String d_handlingExpiration_traficotms  = "";
        String d_keyConfirmationPed_traficotms  = "";
        String d_incrementalReception_traficotms  = "";
        String d_register_traficotms  = "";
        String d_recognition_traficotms = "";
        String d_release_traficotms = "";
        String d_expirationInbound_traficotms = "";

        try {
           // String baseUrl ="http://apis.grupologix.com.mx:3000/shipments/"+path; //"http://apis.grupologix.com.mx/traficotms";
             String baseUrl ="https://api-tms-logix.uc.r.appspot.com/shipments/"+path; 
             System.out.println("baseUrl"+baseUrl);
//String params = "?linkTo=shipmentId_traficotms&equalTo="+path;

       // String TokenURLP = "http://apis.grupologix.com.mx:3000/shipments/"+path;

        
URL url = new URL(baseUrl); // Concatenas la URL base con los parámetros

HttpURLConnection con = (HttpURLConnection) url.openConnection();
con.setDoOutput(true);
con.setRequestMethod("GET");
           // this.url = new URL(TokenURLP);
            //HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //con.setDoOutput(true);
            //con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "*/*");
            con.setRequestProperty("Accept-Encoding", "gzip,deflate,br");
            con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("Authorization", "c5LTA6WPbMwHhEabYu77nN9cn4VcMj");
            //con.setRequestProperty("linkTo", "shipmentId_traficotms");
            //con.setRequestProperty("equalTo", path);
            //con.setRequestProperty("select", "*");

            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
  
                
                     int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = con.getInputStream();
                
                // Si la respuesta está en GZIP, descomprimirla
                if ("gzip".equals(con.getContentEncoding())) {
                    inputStream = new GZIPInputStream(inputStream);
                }
                 BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder content = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                      responseLine2 += inputLine;//responseLine.trim();
                }
                
               // while ((responseLine = br.readLine()) != null) {
               //     responseLine2 += responseLine.trim();
               // }

                JSONObject obj = new JSONObject(responseLine2.trim());
               // JSONArray jsonArray = obj.getJSONArray("results");

                if (obj.length() == 0) {

                    System.out.println("No se arrojo información en el servicio del proveedor, del siguiente dato: " + path);
                    System.out.println("-------------------------------------------------------------------------------------------------");

                } else {
                    System.out.println("--------------------------------------------------si llega-----------------------------------------------");

                    // Iterar sobre el JSONArray para obtener los valores de "description"
                    for (int i = 0; i < 1; i++) {

                       // JSONObject jsonObject = jsonArray.getJSONObject(i);

                      String id_traficotms = obj.getString("id_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String evento_traficotms = obj.getString("evento_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String shipmentId_traficotms = obj.getString("shipmentId_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String countryOrigin_traficotms = obj.getString("countryOrigin_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String sizeContainer_traficotms = obj.getString("sizeContainer_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String valueDlls_traficotms = obj.getString("valueDlls_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String AA_traficotms = obj.getString("AA_traficotms").replace("-", "").replace(" ", "").replace("\"", "");

String yearPed_traficotms = obj.getString("yearPed_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String customHouse_traficotms = obj.getString("customHouse_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String aaPat_traficotms = obj.getString("aaPat_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String num_pedimento = obj.getString("noPed_traficotms").replace("-", "").replace(" ", "").replace("\"", "");

// Asegurar que cada variable cumpla con las limitaciones de longitud
yearPed_traficotms = ajustarLongitud(yearPed_traficotms, 2);
customHouse_traficotms = ajustarLongitud(customHouse_traficotms, 2);
aaPat_traficotms = ajustarLongitud(aaPat_traficotms, 4);
num_pedimento = ajustarLongitud(num_pedimento, 7); 

// Concatenar las variables con un espacio entre cada una  
String noPed_traficotms = yearPed_traficotms + " " + customHouse_traficotms + " " + aaPat_traficotms + " " + num_pedimento;

String noPedRect1_traficotms = obj.getString("noPedRect1_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String noPedComment1_traficotms = obj.getString("noPedComment1_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String noPedRect2_traficotms = obj.getString("noPedRect2_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String noPedComment2_traficotms = obj.getString("noPedComment2_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String enclosure_traficotms = obj.getString("enclosure_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String shippingCompany_traficotms = obj.getString("shippingCompany_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String vessel_traficotms = obj.getString("vessel_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String revalidationDate_traficotms = obj.getString("revalidationDate_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String permissionRequired_traficotms = obj.getString("permissionRequired_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String AccountwithCO_traficotms = obj.getString("AccountwithCO_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String TariffPreferenceCO_traficotms = obj.getString("TariffPreferenceCO_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String requiresUVA_traficotms = obj.getString("requiresUVA_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String RequiresCA_traficotms = obj.getString("RequiresCA_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String certificateNumberCA_traficotms = obj.getString("certificateNumberCA_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String amountCA_traficotms = obj.getString("amountCA_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String modality_traficotms = obj.getString("modality_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String modulationResult_traficotms = obj.getString("modulationResult_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String originSeal_traficotms = obj.getString("originSeal_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String endStamp_traficotms = obj.getString("endStamp_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String d_retentionAuthority_traficotms = obj.getString("d_retentionAuthority_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String d_withHoldingAuthorityRelease_traficotms = obj.getString("d_withHoldingAuthorityRelease_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String OperationStatus_traficotms = obj.getString("OperationStatus_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String reasonDelay_traficotms = obj.getString("reasonDelay_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String comments_traficotms = obj.getString("comments_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String archiveM_traficotms = obj.getString("archiveM_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
String tande_traficotms = obj.getString("tande_traficotms").replace("-", "").replace(" ", "").replace("\"", "");



try {
    String valor1 = obj.getString("etaPortDischargeTwo_traficotms").replace(" ", "").replace("\"", "");

    // Verificar si el valor es nulo o "null" (en texto)
    if (valor1 != null && !valor1.equalsIgnoreCase("null") && !valor1.equals("") && !valor1.equals("-")) {
        try {
            LocalDate date1 = LocalDate.parse(valor1); // Intentar parsear la fecha al objeto LocalDate
            etaPortDischargeTwo_traficotms = date1.format(formatter); // Formatear la fecha al nuevo formato
        } catch (DateTimeParseException e) {
            // Manejar el caso de un formato de fecha inválido
            System.err.println("Error al analizar la fecha: " + valor1);
            e.printStackTrace(); // O loguear el error
        }
    }else{

        etaPortDischargeTwo_traficotms=null;
         System.err.println("etaPortDischargeTwo_traficotms " + etaPortDischargeTwo_traficotms);

    }
} catch (JSONException e) {
    // Manejar el caso de que el campo "etaPortDischargeTwo_traficotms" no exista o haya un error al obtener el valor
    System.err.println("Error al obtener el valor de 'etaPortDischargeTwo_traficotms' del JSON");
    e.printStackTrace(); // O loguear el error
}



  try {
      
String valor2 = obj.getString("DocumentRecepcionDate_traficotms").replace(" ", "").replace("\"", "");
if (!valor2.equals("")  & !valor2.equals("-")  & !valor2.equals("null")) {
    LocalDate date2 = LocalDate.parse(valor2);                          // Parsear la fecha al objeto LocalDate
    DocumentRecepcionDate_traficotms = date2.format(formatter);   // Formatear la fecha al nuevo formato       
}

String valor3 = obj.getString("d_previousOrigin_traficotms").replace(" ", "").replace("\"", "");
if (!valor3.equals("")  & !valor3.equals("-")  & !valor3.equals("null")) {
    LocalDate date3 = LocalDate.parse(valor3);                    // Parsear la fecha al objeto LocalDate
    d_previousOrigin_traficotms = date3.format(formatter);       // Formatear la fecha al nuevo formato                             
}

String valor4 = obj.getString("d_previousDestiny_traficotms").replace(" ", "").replace("\"", "");
if (!valor4.equals("")  & !valor4.equals("-")  & !valor4.equals("null")) {
    LocalDate date4 = LocalDate.parse(valor4);                    // Parsear la fecha al objeto LocalDate
    d_previousDestiny_traficotms = date4.format(formatter);      // Formatear la fecha al nuevo formato                             
}

String valor5 = obj.getString("d_previousResult_traficotms").replace(" ", "").replace("\"", "");
if (!valor5.equals("")  &  !valor5.equals("-")  & !valor5.equals("null")) {
    LocalDate date5 = LocalDate.parse(valor5);                    // Parsear la fecha al objeto LocalDate
    d_previousResult_traficotms = date5.format(formatter);        // Formatear la fecha al nuevo formato                             
}

String valor6 = obj.getString("finalProforma_traficotms").replace(" ", "").replace("\"", "");
if (!valor6.equals("")  & !valor6.equals("-")  & !valor6.equals("null")) {
    LocalDate date6 = LocalDate.parse(valor6);                    // Parsear la fecha al objeto LocalDate
    finalProforma_traficotms = date6.format(formatter);             // Formatear la fecha al nuevo formato                             
}

String valor7 = obj.getString("d_sendTokens_traficotms").replace(" ", "").replace("\"", "");
if (!valor7.equals("")  & !valor7.equals("-")  & !valor7.equals("null")) {
    LocalDate date7 = LocalDate.parse(valor7);                    // Parsear la fecha al objeto LocalDate
    d_sendTokens_traficotms = date7.format(formatter);             // Formatear la fecha al nuevo formato                             
}

String valor8 = obj.getString("d_receiptPermitsProcessed_traficotms").replace(" ", "").replace("\"", "");
if (!valor8.equals("")  & !valor8.equals("-")  & !valor8.equals("null")) {
    LocalDate date8 = LocalDate.parse(valor8);                             // Parsear la fecha al objeto LocalDate
    d_receiptPermitsProcessed_traficotms = date8.format(formatter);   // Formatear la fecha al nuevo formato                             
}

String valor9 = obj.getString("d_PermitActivation_traficotms").replace(" ", "").replace("\"", "");
if (!valor9.equals("")  & !valor9.equals("-")  & !valor9.equals("null")) {
    LocalDate date9 = LocalDate.parse(valor9);                    // Parsear la fecha al objeto LocalDate
    d_PermitActivation_traficotms = date9.format(formatter);      // Formatear la fecha al nuevo formato                             
}

String valor10 = obj.getString("d_AuthorizedPermits_traficotms").replace(" ", "").replace("\"", "");
if (!valor10.equals("")  & !valor10.equals("-")  & !valor10.equals("null")) {
    LocalDate date10 = LocalDate.parse(valor10);                    // Parsear la fecha al objeto LocalDate
    d_AuthorizedPermits_traficotms = date10.format(formatter);   // Formatear la fecha al nuevo formato                            
}

String valor11 = obj.getString("d_receiptCA_traficotms").replace(" ", "").replace("\"", "");
if (!valor11.equals("")  & !valor11.equals("-")  & !valor11.equals("null")) {
    LocalDate date11 = LocalDate.parse(valor11);                    // Parsear la fecha al objeto LocalDate
    d_receiptCA_traficotms = date11.format(formatter);               // Formatear la fecha al nuevo formato                             
}

String valor12 = obj.getString("d_completeDocuments_traficotms").replace(" ", "").replace("\"", "");
if (!valor12.equals("")  & !valor12.equals("-")  & !valor12.equals("null")) {
    LocalDate date12 = LocalDate.parse(valor12);                       // Parsear la fecha al objeto LocalDate
    d_completeDocuments_traficotms = date12.format(formatter);   // Formatear la fecha al nuevo formato                             
}

String valor13 = obj.getString("d_paidPed_traficotms").replace(" ", "").replace("\"", "");
if (!valor13.equals("")  & !valor13.equals("-")  & !valor13.equals("null")) {
    LocalDate date13 = LocalDate.parse(valor13);                    // Parsear la fecha al objeto LocalDate
    d_paidPed_traficotms = date13.format(formatter);                  // Formatear la fecha al nuevo formato                            
}

String valor14 = obj.getString("d_transportRequest_traficotms").replace(" ", "").replace("\"", "");
if (!valor14.equals("")  & !valor14.equals("-")  & !valor14.equals("null")) {
    LocalDate date14 = LocalDate.parse(valor14);                    // Parsear la fecha al objeto LocalDate
    d_transportRequest_traficotms = date14.format(formatter);    // Formatear la fecha al nuevo formato                             
}

String valor15 = obj.getString("d_modulation_traficotms").replace(" ", "").replace("\"", "");
if (!valor15.equals("")  & !valor15.equals("-")  & !valor15.equals("null")) {
    LocalDate date15 = LocalDate.parse(valor15);                    // Parsear la fecha al objeto LocalDate
    d_modulation_traficotms = date15.format(formatter);             // Formatear la fecha al nuevo formato                             
}

String valor16 = obj.getString("arrivalNOVA_traficotms").replace(" ", "").replace("\"", "");
if (!valor16.equals("")  & !valor16.equals("-")  & !valor16.equals("null")) {
    LocalDate date16 = LocalDate.parse(valor16);                    // Parsear la fecha al objeto LocalDate
    arrivalNOVA_traficotms = date16.format(formatter);               // Formatear la fecha al nuevo formato                             
}

String valor17 = obj.getString("arrivalGlobalTradeSD_traficotms").replace(" ", "").replace("\"", "");
if (!valor17.equals("")  & !valor17.equals("-")  & !valor17.equals("null")) {
    LocalDate date17 = LocalDate.parse(valor17);                    // Parsear la fecha al objeto LocalDate
    arrivalGlobalTradeSD_traficotms = date17.format(formatter);   // Formatear la fecha al nuevo formato                             
}

String valor18 = obj.getString("d_archiveM_traficotms").replace(" ", "").replace("\"", "");
if (!valor18.equals("")  & !valor18.equals("-")  & !valor18.equals("null")) {
    LocalDate date18 = LocalDate.parse(valor18);                    // Parsear la fecha al objeto LocalDate
    d_archiveM_traficotms = date18.format(formatter);                 // Formatear la fecha al nuevo formato                             
}

try {
    String valor19 = obj.getString("d_requestHandling_traficotms").replace(" ", "").replace("\"", "");

    // Verificar si el valor es nulo o "null" (en texto)
    if (valor19 != null && !valor19.equalsIgnoreCase("null") && !valor19.equals("") && !valor19.equals("-")) {
        try {
            LocalDate date19 = LocalDate.parse(valor19); // Intentar parsear la fecha al objeto LocalDate
            d_requestHandling_traficotms = date19.format(formatter); // Formatear la fecha al nuevo formato
        } catch (DateTimeParseException e) {
            // Manejar el caso de un formato de fecha inválido
            System.err.println("Error al analizar la fecha: " + valor19);
            e.printStackTrace(); // O loguear el error
        }
    }else{

        d_requestHandling_traficotms=null;
         System.err.println("d_requestHandling_traficotms " + d_requestHandling_traficotms);

    }
} catch (JSONException e) {
    // Manejar el caso de que el campo "d_requestHandling_traficotms" no exista o haya un error al obtener el valor
    System.err.println("Error al obtener el valor de 'd_requestHandling_traficotms' del JSON");
    e.printStackTrace(); // O loguear el error
}



    try {
String valor20 = obj.getString("d_handlingExpiration_traficotms").replace(" ", "").replace("\"", "");

// Verificar si el valor es nulo o "null" (en texto)
if (valor20 != null && !valor20.equalsIgnoreCase("null") && !valor20.equals("") && !valor20.equals("-")) {
    try {
        LocalDate date20 = LocalDate.parse(valor20); // Intentar parsear la fecha al objeto LocalDate
        d_handlingExpiration_traficotms = date20.format(formatter); // Formatear la fecha al nuevo formato
    } catch (DateTimeParseException e) {
        // Manejar el caso de un formato de fecha inválido
        System.err.println("Error al analizar la fecha: " + valor20);
        e.printStackTrace(); // O loguear el error
    }
}else{

    d_handlingExpiration_traficotms=null;
     System.err.println("d_handlingExpiration_traficotms " + d_handlingExpiration_traficotms);

}
} catch (JSONException e) {
// Manejar el caso de que el campo "d_handlingExpiration_traficotms" no exista o haya un error al obtener el valor
System.err.println("Error al obtener el valor de 'd_handlingExpiration_traficotms' del JSON");
e.printStackTrace(); // O loguear el error
}




String valor21 = obj.getString("d_keyConfirmationPed_traficotms").replace(" ", "").replace("\"", "");
if (!valor21.equals("")  & !valor21.equals("-")  & !valor21.equals("null")) {
    LocalDate date21 = LocalDate.parse(valor21);                     // Parsear la fecha al objeto LocalDate
    d_keyConfirmationPed_traficotms = date21.format(formatter);   // Formatear la fecha al nuevo formato                                
}

String valor22 = obj.getString("d_incrementalReception_traficotms").replace(" ", "").replace("\"", "");
if (!valor22.equals("")  & !valor22.equals("-")  & !valor22.equals("null")) {
    LocalDate date22 = LocalDate.parse(valor22);                        // Parsear la fecha al objeto LocalDate
    d_incrementalReception_traficotms = date22.format(formatter);   // Formatear la fecha al nuevo formato                                
}

String valor23 = obj.getString("d_register_traficotms").replace(" ", "").replace("\"", "");
if (!valor23.equals("")  & !valor23.equals("-")  & !valor23.equals("null")) {
    LocalDate date23 = LocalDate.parse(valor23);                    // Parsear la fecha al objeto LocalDate
    d_register_traficotms = date23.format(formatter);                   // Formatear la fecha al nuevo formato                                
}
//**********************************************************************************************************************




String valor24 = obj.getString("d_recognition_traficotms").replace(" ", "").replace("\"", "");///***************************
if (!valor24.equals("")  & !valor24.equals("-")  & !valor24.equals("null")) {
    LocalDate date24 = LocalDate.parse(valor24);                    // Parsear la fecha al objeto LocalDate
    d_recognition_traficotms = date24.format(formatter);                   // Formatear la fecha al nuevo formato                                
}

String valor25 = obj.getString("d_release_traficotms").replace(" ", "").replace("\"", "");//******************
if (!valor25.equals("")  & !valor25.equals("-")  & !valor25.equals("null")) {
    LocalDate date25 = LocalDate.parse(valor25);                    // Parsear la fecha al objeto LocalDate
    d_release_traficotms = date25.format(formatter);                   // Formatear la fecha al nuevo formato                                
}


// if (!valor26.equals("")  & !valor26.equals("-")) {
//     LocalDate date26 = LocalDate.parse(valor26);                    // Parsear la fecha al objeto LocalDate
//     d_expirationInbound_traficotms = date26.format(formatter);                   // Formatear la fecha al nuevo formato                                
// }


    try {
String valor26 = obj.getString("d_expirationInbound_traficotms").replace(" ", "").replace("\"", "");

// Verificar si el valor es nulo o "null" (en texto)
if (valor26 != null && !valor26.equalsIgnoreCase("null") && !valor26.equals("") && !valor26.equals("-")) {
    try {
        LocalDate date26 = LocalDate.parse(valor26); // Intentar parsear la fecha al objeto LocalDate
        d_expirationInbound_traficotms = date26.format(formatter); // Formatear la fecha al nuevo formato
    } catch (DateTimeParseException e) {
        // Manejar el caso de un formato de fecha inválido
        System.err.println("Error al analizar la fecha: " + valor26);
        e.printStackTrace(); // O loguear el error
    }
}else{

    d_expirationInbound_traficotms=null;
     System.err.println("d_expirationInbound_traficotms " + d_expirationInbound_traficotms);

}
} catch (JSONException e) {
// Manejar el caso de que el campo "d_handlingExpiration_traficotms" no exista o haya un error al obtener el valor
System.err.println("Error al obtener el valor de 'd_handlingExpiration_traficotms' del JSON");
e.printStackTrace(); // O loguear el error
}


  } catch (Error e) {
            // Manejar el caso de un formato de fecha inválido
            System.err.println("Error al analizar la fecha: " + e);
            e.printStackTrace(); // O loguear el error
        }
                        
                        
                        
                        try {

                            insertUsingStoredProcedure(evento_traficotms, shipmentId_traficotms, countryOrigin_traficotms, sizeContainer_traficotms, valueDlls_traficotms, etaPortDischargeTwo_traficotms, AA_traficotms, yearPed_traficotms, aaPat_traficotms, customHouse_traficotms, noPed_traficotms, noPedRect1_traficotms, noPedComment1_traficotms, noPedRect2_traficotms, noPedComment2_traficotms, DocumentRecepcionDate_traficotms, enclosure_traficotms, shippingCompany_traficotms, vessel_traficotms, revalidationDate_traficotms, d_previousOrigin_traficotms, d_previousDestiny_traficotms, d_previousResult_traficotms, finalProforma_traficotms, permissionRequired_traficotms, d_sendTokens_traficotms, d_receiptPermitsProcessed_traficotms, d_PermitActivation_traficotms, d_AuthorizedPermits_traficotms, AccountwithCO_traficotms, TariffPreferenceCO_traficotms, requiresUVA_traficotms, RequiresCA_traficotms, d_receiptCA_traficotms, certificateNumberCA_traficotms, amountCA_traficotms, d_completeDocuments_traficotms, d_paidPed_traficotms, d_transportRequest_traficotms, d_modulation_traficotms, modality_traficotms, modulationResult_traficotms, d_recognition_traficotms, d_release_traficotms, originSeal_traficotms, endStamp_traficotms, d_retentionAuthority_traficotms, d_withHoldingAuthorityRelease_traficotms, OperationStatus_traficotms, reasonDelay_traficotms, comments_traficotms, arrivalNOVA_traficotms, arrivalGlobalTradeSD_traficotms, archiveM_traficotms, d_archiveM_traficotms, d_requestHandling_traficotms, d_handlingExpiration_traficotms, d_keyConfirmationPed_traficotms, d_incrementalReception_traficotms, tande_traficotms, d_expirationInbound_traficotms, d_register_traficotms);

                            System.out.println("(TRA_INB_CUSTOMS) Se actualizó en sistema, el siguiente número de evento: " + evento_traficotms + " y número de shipment: " + shipmentId_traficotms);
                            System.out.println("----------------------------------------------------------------------------------------------------------------------------");

                        } catch (Exception ex) {

                            msg_logError = "¡Error al actualizar el siguiente número de evento: " + evento_traficotms + "!";

                            System.out.println(msg_logError);
                            System.out.println("----------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("Exception:" + ex.toString());

                            //Emisión de email - Log de errores
                            //Email correo = new Email();
                            //correo.alertaLogixWebservice(msg_logError, jsonObject);
                            return evento_traficotms;
                        }

                    }
                }

                
                 } else {
                System.out.println("Error: Código de respuesta " + status);
            }
            
            
            } catch (Error e) {
                System.out.println("error" + e);
                return "error";
            } catch (JSONException ex) {
                System.out.println("ex" + ex);
                return "error";
            }

        } catch (IOException ex) {
            System.out.println("ex" + ex);
            return "{\"error\": \"No se encontro shipment\"}";
        }
        return null;

    }

    // Método para ejecutar un insert (store procedure)
    public void insertUsingStoredProcedure(String evento_traficotms, String shipmentId_traficotms, String countryOrigin_traficotms, String sizeContainer_traficotms, String valueDlls_traficotms, String etaPortDischargeTwo_traficotms, String AA_traficotms, String yearPed_traficotms, String aaPat_traficotms, String customHouse_traficotms, String noPed_traficotms, String noPedRect1_traficotms, String noPedComment1_traficotms, String noPedRect2_traficotms, String noPedComment2_traficotms, String DocumentRecepcionDate_traficotms, String enclosure_traficotms, String shippingCompany_traficotms, String vessel_traficotms, String revalidationDate_traficotms, String d_previousOrigin_traficotms, String d_previousDestiny_traficotms, String d_previousResult_traficotms, String finalProforma_traficotms, String permissionRequired_traficotms, String d_sendTokens_traficotms, String d_receiptPermitsProcessed_traficotms, String d_PermitActivation_traficotms, String d_AuthorizedPermits_traficotms, String AccountwithCO_traficotms, String TariffPreferenceCO_traficotms, String requiresUVA_traficotms, String RequiresCA_traficotms, String d_receiptCA_traficotms, String certificateNumberCA_traficotms, String amountCA_traficotms, String d_completeDocuments_traficotms, String d_paidPed_traficotms, String d_transportRequest_traficotms, String d_modulation_traficotms, String modality_traficotms, String modulationResult_traficotms, String d_recognition_traficotms, String d_release_traficotms, String originSeal_traficotms, String endStamp_traficotms, String d_retentionAuthority_traficotms, String d_withHoldingAuthorityRelease_traficotms, String OperationStatus_traficotms, String reasonDelay_traficotms, String comments_traficotms, String arrivalNOVA_traficotms, String arrivalGlobalTradeSD_traficotms, String archiveM_traficotms, String d_archiveM_traficotms, String d_requestHandling_traficotms, String d_handlingExpiration_traficotms, String d_keyConfirmationPed_traficotms, String d_incrementalReception_traficotms, String tande_traficotms, String d_expirationInbound_traficotms, String d_register_traficotms) throws SQLException {
        Connection conn = null;
        CallableStatement callableStmt = null;

        try {
            // Obtener una conexión del pool
            conn = connectionPool.getConnection();

            // Nombre del procedimiento almacenado
            String storedProcedureName = "SP_INB_WS_CONSUMO_UPLOAD_LOGIX_CUSTOMS";

            // Construir la llamada al procedimiento con parámetros
            StringBuilder procedureCall = new StringBuilder("{call SP_INB_WS_CONSUMO_UPLOAD_LOGIX_CUSTOMS (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            callableStmt = conn.prepareCall(procedureCall.toString());

            // Asignar los valores de los parámetros al procedimiento almacenado
            
           
            callableStmt.setString("evento_traficotms", evento_traficotms);
            callableStmt.setString("shipmentId_traficotms", shipmentId_traficotms);
            callableStmt.setString("countryOrigin_traficotms", countryOrigin_traficotms);
            callableStmt.setString("sizeContainer_traficotms", sizeContainer_traficotms);
            callableStmt.setString("valueDlls_traficotms", valueDlls_traficotms);
            callableStmt.setString("etaPortDischargeTwo_traficotms", etaPortDischargeTwo_traficotms);
            callableStmt.setString("AA_traficotms", AA_traficotms);
            callableStmt.setString("yearPed_traficotms", AA_traficotms);
            callableStmt.setString("aaPat_traficotms", aaPat_traficotms);
            callableStmt.setString("customHouse_traficotms", customHouse_traficotms);//10
            callableStmt.setString("noPed_traficotms", noPed_traficotms);
            callableStmt.setString("noPedRect1_traficotms", noPedRect1_traficotms);
            callableStmt.setString("noPedComment1_traficotms", noPedComment1_traficotms);
            callableStmt.setString("noPedRect2_traficotms", noPedRect2_traficotms);
            callableStmt.setString("noPedComment2_traficotms", noPedComment2_traficotms);
            callableStmt.setString("DocumentRecepcionDate_traficotms", DocumentRecepcionDate_traficotms);
            callableStmt.setString("enclosure_traficotms", enclosure_traficotms);
            callableStmt.setString("shippingCompany_traficotms", shippingCompany_traficotms);
            callableStmt.setString("vessel_traficotms", vessel_traficotms);
            callableStmt.setString("revalidationDate_traficotms", revalidationDate_traficotms);//20
            callableStmt.setString("d_previousOrigin_traficotms", d_previousOrigin_traficotms);
            callableStmt.setString("d_previousDestiny_traficotms", d_previousDestiny_traficotms);
            callableStmt.setString("d_previousResult_traficotms", d_previousResult_traficotms);
            callableStmt.setString("finalProforma_traficotms", finalProforma_traficotms);
            callableStmt.setString("permissionRequired_traficotms", permissionRequired_traficotms);
            callableStmt.setString("d_sendTokens_traficotms", d_sendTokens_traficotms);
            callableStmt.setString("d_receiptPermitsProcessed_traficotms", d_receiptPermitsProcessed_traficotms);
            callableStmt.setString("d_PermitActivation_traficotms", d_PermitActivation_traficotms);
            callableStmt.setString("d_AuthorizedPermits_traficotms", d_AuthorizedPermits_traficotms);
            callableStmt.setString("AccountwithCO_traficotms", AccountwithCO_traficotms);//30
            callableStmt.setString("TariffPreferenceCO_traficotms", TariffPreferenceCO_traficotms);
            callableStmt.setString("requiresUVA_traficotms", requiresUVA_traficotms);
            callableStmt.setString("RequiresCA_traficotms", RequiresCA_traficotms);
            callableStmt.setString("d_receiptCA_traficotms", d_receiptCA_traficotms);
            callableStmt.setString("certificateNumberCA_traficotms", certificateNumberCA_traficotms);
            callableStmt.setString("amountCA_traficotms", amountCA_traficotms);
            callableStmt.setString("d_completeDocuments_traficotms", d_completeDocuments_traficotms);
            callableStmt.setString("d_paidPed_traficotms", d_paidPed_traficotms);
            callableStmt.setString("d_transportRequest_traficotms", d_transportRequest_traficotms);
            callableStmt.setString("d_modulation_traficotms", d_modulation_traficotms);//40
            callableStmt.setString("modality_traficotms", modality_traficotms);
            callableStmt.setString("modulationResult_traficotms", modulationResult_traficotms);
            callableStmt.setString("d_recognition_traficotms", d_recognition_traficotms);                             //FECHA RECONOCIMENTO
            callableStmt.setString("d_release_traficotms", d_release_traficotms);                                         //FECHA LIBERACION
            callableStmt.setString("originSeal_traficotms", originSeal_traficotms);
            callableStmt.setString("endStamp_traficotms", endStamp_traficotms);
            callableStmt.setString("d_retentionAuthority_traficotms", d_retentionAuthority_traficotms);
            callableStmt.setString("d_withHoldingAuthorityRelease_traficotms", d_withHoldingAuthorityRelease_traficotms);
            callableStmt.setString("OperationStatus_traficotms", OperationStatus_traficotms);
            callableStmt.setString("reasonDelay_traficotms", reasonDelay_traficotms);//50
            callableStmt.setString("comments_traficotms", comments_traficotms);
            callableStmt.setString("arrivalNOVA_traficotms", arrivalNOVA_traficotms);
            callableStmt.setString("arrivalGlobalTradeSD_traficotms", arrivalGlobalTradeSD_traficotms);
            callableStmt.setString("archiveM_traficotms", archiveM_traficotms);
            callableStmt.setString("d_archiveM_traficotms", d_archiveM_traficotms);
            callableStmt.setString("d_requestHandling_traficotms", d_requestHandling_traficotms);
            callableStmt.setString("d_handlingExpiration_traficotms", d_handlingExpiration_traficotms);
            callableStmt.setString("d_keyConfirmationPed_traficotms", d_keyConfirmationPed_traficotms);
            callableStmt.setString("d_incrementalReception_traficotms", d_incrementalReception_traficotms);
            callableStmt.setString("tande_traficotms", tande_traficotms);//60
            
            callableStmt.setString("d_expirationInbound_traficotms", d_expirationInbound_traficotms);        //FECHA VENCIMIENTO 
            callableStmt.setString("d_register_traficotms", d_register_traficotms);
            callableStmt.registerOutParameter("resultado", java.sql.Types.VARCHAR);
            
    

            // Ejecutar el procedimiento almacenado
            callableStmt.executeUpdate();
                    String resultado = callableStmt.getString("resultado");
System.out.println("Resultado del procedimiento: " + resultado);


            System.out.println("¡(TRA_INB_CUSTOMS) Se actualizó en sistema, el siguiente número de shipment id: " + shipmentId_traficotms + "!");
            System.out.println("-------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();

            System.out.println("¡Error al actualizar el siguiente número de shipment id: " + shipmentId_traficotms + "! en Base de Datos.");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        } finally {

            // Cerrar el CallableStatement y devolver la conexión al pool
            if (callableStmt != null) {
                try {
                    callableStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Liberar la conexión al pool
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }
    }

    
        public boolean logy() throws IOException, SQLException {
         try {
            String baseUrl = "https://api-tms-logix.uc.r.appspot.com/shipments/V000032075";
            URL url = new URL(baseUrl);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            
           // con.setRequestMethod("GET");

            // Agregar headers a la solicitud
            con.setRequestProperty("Accept", "*/*");
           // con.setRequestProperty("Accept-Encoding", "gzip,deflate,br");
             con.setRequestProperty("Accept-Encoding", "gzip");
            con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("Authorization", "c5LTA6WPbMwHhEabYu77nN9cn4VcMj");
            
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

          
                 int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = con.getInputStream();
                
                // Si la respuesta está en GZIP, descomprimirla
                if ("gzip".equals(con.getContentEncoding())) {
                    inputStream = new GZIPInputStream(inputStream);
                }
                
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder content = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                // Imprime la respuesta para verificar su formato
                System.out.println("Respuesta del servidor: " + content.toString());

                // Verifica que comience con '{' para intentar convertir a JSON
                if (content.toString().trim().startsWith("{")) {
                    JSONObject jsonResponse = new JSONObject(content.toString());
                    System.out.println("JSON recibido: " + jsonResponse.toString(2));
                } else {
                    System.out.println("Error: La respuesta no es un JSON válido");
                }

            } else {
                System.out.println("Error: Código de respuesta " + status);
            }
            con.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    
            
            return false;
        
        }
        
        
    public boolean sedEmail(String objeto) throws IOException, SQLException {
        //Emisión de email - Log de errores
        Email correo = new Email();
        boolean send = correo.alertaRadarWebservice("¡Error al actualizar los siguientes números de shipment id: ", objeto);

        return send;
    }
    
    private static String ajustarLongitud(String valor, int longitud) {
        if (valor.length() > longitud) {
            return valor.substring(0, longitud);  // Limitar si excede
        } else {
            return String.format("%1$" + longitud + "s", valor).replace(' ', '0');  // Rellenar con ceros
        }
    }

}
