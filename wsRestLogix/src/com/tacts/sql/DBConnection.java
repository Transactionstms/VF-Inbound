/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacts.sql;

import com.send.email.Email;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
                    ConsumoLOGIX(shipment_id);
                    System.out.println("N° Iteración: " + cont);

                } else {
                    System.out.println("No hay valores para procesar.");
                }
            }
            
            //Emisión de email para registros no procesados:
            /*if (!data_sin_procesar.equals("")  & !valor1.equals("-")) {
                data_sin_procesar = data_sin_procesar.substring(0, data_sin_procesar.length() - 2);
                boolean res = sedEmail(data_sin_procesar);
                if (res) {
                    System.out.println("¡Lista de shipments sin información, envíada por correo!");
                }
            }*/
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

    //Método para consumir el servicio con el token generado y obtener json
    public String ConsumoLOGIX(String path) throws IOException, JSONException, SQLException {

        // Crear un formateador para convertir la fecha al formato MM/DD/YYYY
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy ");
        
        String TokenURLP = "http://apis.grupologix.com.mx/traficotms";
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

        try {

            this.url = new URL(TokenURLP);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "*/*");
            con.setRequestProperty("Accept-Encoding", "gzip,deflate,br");
            con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("Authorization", "c5LTA6WPbMwHhEabYu77nN9cn4VcMj");
            con.setRequestProperty("linkTo", "shipmentId_traficotms");
            con.setRequestProperty("equalTo", path);
            con.setRequestProperty("select", "*");

            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {

                while ((responseLine = br.readLine()) != null) {
                    responseLine2 += responseLine.trim();
                }

                JSONObject obj = new JSONObject(responseLine2.trim());
                JSONArray jsonArray = obj.getJSONArray("results");

                if (jsonArray.length() == 0) {

                    System.out.println("No se arrojo información en el servicio del proveedor, del siguiente dato: " + path);
                    System.out.println("-------------------------------------------------------------------------------------------------");

                } else {

                    // Iterar sobre el JSONArray para obtener los valores de "description"
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String id_traficotms = jsonObject.getString("id_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String evento_traficotms = jsonObject.getString("evento_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String shipmentId_traficotms = jsonObject.getString("shipmentId_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String countryOrigin_traficotms = jsonObject.getString("countryOrigin_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String sizeContainer_traficotms = jsonObject.getString("sizeContainer_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String valueDlls_traficotms = jsonObject.getString("valueDlls_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String AA_traficotms = jsonObject.getString("AA_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                       
                        String yearPed_traficotms = jsonObject.getString("yearPed_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String customHouse_traficotms = jsonObject.getString("customHouse_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String aaPat_traficotms = jsonObject.getString("aaPat_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String num_pedimento = jsonObject.getString("noPed_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                    
                        // Asegurar que cada variable cumpla con las limitaciones de longitud
                        yearPed_traficotms = ajustarLongitud(yearPed_traficotms, 2);
                        customHouse_traficotms = ajustarLongitud(customHouse_traficotms, 2);
                        aaPat_traficotms = ajustarLongitud(aaPat_traficotms, 4);
                        num_pedimento = ajustarLongitud(num_pedimento, 7); 

                        // Concatenar las variables con un espacio entre cada una  
                        String noPed_traficotms = yearPed_traficotms + " " + customHouse_traficotms + " " + aaPat_traficotms + " " + num_pedimento;

                        String noPedRect1_traficotms = jsonObject.getString("noPedRect1_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String noPedComment1_traficotms = jsonObject.getString("noPedComment1_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String noPedRect2_traficotms = jsonObject.getString("noPedRect2_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String noPedComment2_traficotms = jsonObject.getString("noPedComment2_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String enclosure_traficotms = jsonObject.getString("enclosure_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String shippingCompany_traficotms = jsonObject.getString("shippingCompany_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String vessel_traficotms = jsonObject.getString("vessel_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String revalidationDate_traficotms = jsonObject.getString("revalidationDate_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String permissionRequired_traficotms = jsonObject.getString("permissionRequired_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String AccountwithCO_traficotms = jsonObject.getString("AccountwithCO_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String TariffPreferenceCO_traficotms = jsonObject.getString("TariffPreferenceCO_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String requiresUVA_traficotms = jsonObject.getString("requiresUVA_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String RequiresCA_traficotms = jsonObject.getString("RequiresCA_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String certificateNumberCA_traficotms = jsonObject.getString("certificateNumberCA_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String amountCA_traficotms = jsonObject.getString("amountCA_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String modality_traficotms = jsonObject.getString("modality_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String modulationResult_traficotms = jsonObject.getString("modulationResult_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String d_recognition_traficotms = jsonObject.getString("d_recognition_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String d_release_traficotms = jsonObject.getString("d_release_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String originSeal_traficotms = jsonObject.getString("originSeal_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String endStamp_traficotms = jsonObject.getString("endStamp_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String d_retentionAuthority_traficotms = jsonObject.getString("d_retentionAuthority_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String d_withHoldingAuthorityRelease_traficotms = jsonObject.getString("d_withHoldingAuthorityRelease_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String OperationStatus_traficotms = jsonObject.getString("OperationStatus_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String reasonDelay_traficotms = jsonObject.getString("reasonDelay_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String comments_traficotms = jsonObject.getString("comments_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String archiveM_traficotms = jsonObject.getString("archiveM_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String tande_traficotms = jsonObject.getString("tande_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        String d_expirationInbound_traficotms = jsonObject.getString("d_expirationInbound_traficotms").replace("-", "").replace(" ", "").replace("\"", "");
                        
                        String valor1 = jsonObject.getString("etaPortDischargeTwo_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor1.equals("")  & !valor1.equals("-")) {
                            LocalDate date1 = LocalDate.parse(valor1);                    // Parsear la fecha al objeto LocalDate
                            etaPortDischargeTwo_traficotms = date1.format(formatter);   // Formatear la fecha al nuevo formato
                        }

                        String valor2 = jsonObject.getString("DocumentRecepcionDate_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor2.equals("")  & !valor2.equals("-")) {
                            LocalDate date2 = LocalDate.parse(valor2);                          // Parsear la fecha al objeto LocalDate
                            DocumentRecepcionDate_traficotms = date2.format(formatter);   // Formatear la fecha al nuevo formato       
                        }

                        String valor3 = jsonObject.getString("d_previousOrigin_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor3.equals("")  & !valor3.equals("-")) {
                            LocalDate date3 = LocalDate.parse(valor3);                    // Parsear la fecha al objeto LocalDate
                            d_previousOrigin_traficotms = date3.format(formatter);       // Formatear la fecha al nuevo formato                             
                        }

                        String valor4 = jsonObject.getString("d_previousDestiny_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor4.equals("")  & !valor4.equals("-")) {
                            LocalDate date4 = LocalDate.parse(valor4);                    // Parsear la fecha al objeto LocalDate
                            d_previousDestiny_traficotms = date4.format(formatter);      // Formatear la fecha al nuevo formato                             
                        }

                        String valor5 = jsonObject.getString("d_previousResult_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor5.equals("")  &  !valor5.equals("-")) {
                            LocalDate date5 = LocalDate.parse(valor5);                    // Parsear la fecha al objeto LocalDate
                            d_previousResult_traficotms = date5.format(formatter);        // Formatear la fecha al nuevo formato                             
                        }

                        String valor6 = jsonObject.getString("finalProforma_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor6.equals("")  & !valor6.equals("-")) {
                            LocalDate date6 = LocalDate.parse(valor6);                    // Parsear la fecha al objeto LocalDate
                            finalProforma_traficotms = date6.format(formatter);             // Formatear la fecha al nuevo formato                             
                        }

                        String valor7 = jsonObject.getString("d_sendTokens_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor7.equals("")  & !valor7.equals("-")) {
                            LocalDate date7 = LocalDate.parse(valor7);                    // Parsear la fecha al objeto LocalDate
                            d_sendTokens_traficotms = date7.format(formatter);             // Formatear la fecha al nuevo formato                             
                        }

                        String valor8 = jsonObject.getString("d_receiptPermitsProcessed_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor8.equals("")  & !valor8.equals("-")) {
                            LocalDate date8 = LocalDate.parse(valor8);                             // Parsear la fecha al objeto LocalDate
                            d_receiptPermitsProcessed_traficotms = date8.format(formatter);   // Formatear la fecha al nuevo formato                             
                        }

                        String valor9 = jsonObject.getString("d_PermitActivation_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor9.equals("")  & !valor9.equals("-")) {
                            LocalDate date9 = LocalDate.parse(valor9);                    // Parsear la fecha al objeto LocalDate
                            d_PermitActivation_traficotms = date9.format(formatter);      // Formatear la fecha al nuevo formato                             
                        }

                        String valor10 = jsonObject.getString("d_AuthorizedPermits_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor10.equals("")  & !valor10.equals("-")) {
                            LocalDate date10 = LocalDate.parse(valor10);                    // Parsear la fecha al objeto LocalDate
                            d_AuthorizedPermits_traficotms = date10.format(formatter);   // Formatear la fecha al nuevo formato                            
                        }

                        String valor11 = jsonObject.getString("d_receiptCA_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor11.equals("")  & !valor11.equals("-")) {
                            LocalDate date11 = LocalDate.parse(valor11);                    // Parsear la fecha al objeto LocalDate
                            d_receiptCA_traficotms = date11.format(formatter);               // Formatear la fecha al nuevo formato                             
                        }

                        String valor12 = jsonObject.getString("d_completeDocuments_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor12.equals("")  & !valor12.equals("-")) {
                            LocalDate date12 = LocalDate.parse(valor12);                       // Parsear la fecha al objeto LocalDate
                            d_completeDocuments_traficotms = date12.format(formatter);   // Formatear la fecha al nuevo formato                             
                        }

                        String valor13 = jsonObject.getString("d_paidPed_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor13.equals("")  & !valor13.equals("-")) {
                            LocalDate date13 = LocalDate.parse(valor13);                    // Parsear la fecha al objeto LocalDate
                            d_paidPed_traficotms = date13.format(formatter);                  // Formatear la fecha al nuevo formato                            
                        }

                        String valor14 = jsonObject.getString("d_transportRequest_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor14.equals("")  & !valor14.equals("-")) {
                            LocalDate date14 = LocalDate.parse(valor14);                    // Parsear la fecha al objeto LocalDate
                            d_transportRequest_traficotms = date14.format(formatter);    // Formatear la fecha al nuevo formato                             
                        }

                        String valor15 = jsonObject.getString("d_modulation_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor15.equals("")  & !valor15.equals("-")) {
                            LocalDate date15 = LocalDate.parse(valor15);                    // Parsear la fecha al objeto LocalDate
                            d_modulation_traficotms = date15.format(formatter);             // Formatear la fecha al nuevo formato                             
                        }

                        String valor16 = jsonObject.getString("arrivalNOVA_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor16.equals("")  & !valor16.equals("-")) {
                            LocalDate date16 = LocalDate.parse(valor16);                    // Parsear la fecha al objeto LocalDate
                            arrivalNOVA_traficotms = date16.format(formatter);               // Formatear la fecha al nuevo formato                             
                        }

                        String valor17 = jsonObject.getString("arrivalGlobalTradeSD_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor17.equals("")  & !valor17.equals("-")) {
                            LocalDate date17 = LocalDate.parse(valor17);                    // Parsear la fecha al objeto LocalDate
                            arrivalGlobalTradeSD_traficotms = date17.format(formatter);   // Formatear la fecha al nuevo formato                             
                        }

                        String valor18 = jsonObject.getString("d_archiveM_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor18.equals("")  & !valor18.equals("-")) {
                            LocalDate date18 = LocalDate.parse(valor18);                    // Parsear la fecha al objeto LocalDate
                            d_archiveM_traficotms = date18.format(formatter);                 // Formatear la fecha al nuevo formato                             
                        }

                        String valor19 = jsonObject.getString("d_requestHandling_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor19.equals("")  & !valor19.equals("-")) {
                            LocalDate date19 = LocalDate.parse(valor19);                    // Parsear la fecha al objeto LocalDate
                            d_requestHandling_traficotms = date19.format(formatter);      // Formatear la fecha al nuevo formato                                
                        }

                        String valor20 = jsonObject.getString("d_handlingExpiration_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor20.equals("")  & !valor20.equals("-")) {
                            LocalDate date20 = LocalDate.parse(valor20);                    // Parsear la fecha al objeto LocalDate
                            d_handlingExpiration_traficotms = date20.format(formatter);   // Formatear la fecha al nuevo formato                                
                        }

                        String valor21 = jsonObject.getString("d_keyConfirmationPed_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor21.equals("")  & !valor21.equals("-")) {
                            LocalDate date21 = LocalDate.parse(valor21);                     // Parsear la fecha al objeto LocalDate
                            d_keyConfirmationPed_traficotms = date21.format(formatter);   // Formatear la fecha al nuevo formato                                
                        }

                        String valor22 = jsonObject.getString("d_incrementalReception_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor22.equals("")  & !valor22.equals("-")) {
                            LocalDate date22 = LocalDate.parse(valor22);                        // Parsear la fecha al objeto LocalDate
                            d_incrementalReception_traficotms = date22.format(formatter);   // Formatear la fecha al nuevo formato                                
                        }

                        String valor23 = jsonObject.getString("d_register_traficotms").replace(" ", "").replace("\"", "");
                        if (!valor23.equals("")  & !valor23.equals("-")) {
                            LocalDate date23 = LocalDate.parse(valor23);                    // Parsear la fecha al objeto LocalDate
                            d_register_traficotms = date23.format(formatter);                   // Formatear la fecha al nuevo formato                                
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
            StringBuilder procedureCall = new StringBuilder("{call ");
            procedureCall.append(storedProcedureName).append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

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
            callableStmt.setString("customHouse_traficotms", customHouse_traficotms);
            callableStmt.setString("noPed_traficotms", noPed_traficotms);
            callableStmt.setString("noPedRect1_traficotms", noPedRect1_traficotms);
            callableStmt.setString("noPedComment1_traficotms", noPedComment1_traficotms);
            callableStmt.setString("noPedRect2_traficotms", noPedRect2_traficotms);
            callableStmt.setString("noPedComment2_traficotms", noPedComment2_traficotms);
            callableStmt.setString("DocumentRecepcionDate_traficotms", DocumentRecepcionDate_traficotms);
            callableStmt.setString("enclosure_traficotms", enclosure_traficotms);
            callableStmt.setString("shippingCompany_traficotms", shippingCompany_traficotms);
            callableStmt.setString("vessel_traficotms", vessel_traficotms);
            callableStmt.setString("revalidationDate_traficotms", revalidationDate_traficotms);
            callableStmt.setString("d_previousOrigin_traficotms", d_previousOrigin_traficotms);
            callableStmt.setString("d_previousDestiny_traficotms", d_previousDestiny_traficotms);
            callableStmt.setString("d_previousResult_traficotms", d_previousResult_traficotms);
            callableStmt.setString("finalProforma_traficotms", finalProforma_traficotms);
            callableStmt.setString("permissionRequired_traficotms", permissionRequired_traficotms);
            callableStmt.setString("d_sendTokens_traficotms", d_sendTokens_traficotms);
            callableStmt.setString("d_receiptPermitsProcessed_traficotms", d_receiptPermitsProcessed_traficotms);
            callableStmt.setString("d_PermitActivation_traficotms", d_PermitActivation_traficotms);
            callableStmt.setString("d_AuthorizedPermits_traficotms", d_AuthorizedPermits_traficotms);
            callableStmt.setString("AccountwithCO_traficotms", AccountwithCO_traficotms);
            callableStmt.setString("TariffPreferenceCO_traficotms", TariffPreferenceCO_traficotms);
            callableStmt.setString("requiresUVA_traficotms", requiresUVA_traficotms);
            callableStmt.setString("RequiresCA_traficotms", RequiresCA_traficotms);
            callableStmt.setString("d_receiptCA_traficotms", d_receiptCA_traficotms);
            callableStmt.setString("certificateNumberCA_traficotms", certificateNumberCA_traficotms);
            callableStmt.setString("amountCA_traficotms", amountCA_traficotms);
            callableStmt.setString("d_completeDocuments_traficotms", d_completeDocuments_traficotms);
            callableStmt.setString("d_paidPed_traficotms", d_paidPed_traficotms);
            callableStmt.setString("d_transportRequest_traficotms", d_transportRequest_traficotms);
            callableStmt.setString("d_modulation_traficotms", d_modulation_traficotms);
            callableStmt.setString("modality_traficotms", modality_traficotms);
            callableStmt.setString("modulationResult_traficotms", modulationResult_traficotms);
            callableStmt.setString("d_recognition_traficotms", d_recognition_traficotms);
            callableStmt.setString("d_release_traficotms", d_release_traficotms);
            callableStmt.setString("originSeal_traficotms", originSeal_traficotms);
            callableStmt.setString("endStamp_traficotms", endStamp_traficotms);
            callableStmt.setString("d_retentionAuthority_traficotms", d_retentionAuthority_traficotms);
            callableStmt.setString("d_withHoldingAuthorityRelease_traficotms", d_withHoldingAuthorityRelease_traficotms);
            callableStmt.setString("OperationStatus_traficotms", OperationStatus_traficotms);
            callableStmt.setString("reasonDelay_traficotms", reasonDelay_traficotms);
            callableStmt.setString("comments_traficotms", comments_traficotms);
            callableStmt.setString("arrivalNOVA_traficotms", arrivalNOVA_traficotms);
            callableStmt.setString("arrivalGlobalTradeSD_traficotms", arrivalGlobalTradeSD_traficotms);
            callableStmt.setString("archiveM_traficotms", archiveM_traficotms);
            callableStmt.setString("d_archiveM_traficotms", d_archiveM_traficotms);
            callableStmt.setString("d_requestHandling_traficotms", d_requestHandling_traficotms);
            callableStmt.setString("d_handlingExpiration_traficotms", d_handlingExpiration_traficotms);
            callableStmt.setString("d_keyConfirmationPed_traficotms", d_keyConfirmationPed_traficotms);
            callableStmt.setString("d_incrementalReception_traficotms", d_incrementalReception_traficotms);
            callableStmt.setString("tande_traficotms", tande_traficotms);
            callableStmt.setString("d_expirationInbound_traficotms", d_expirationInbound_traficotms);
            callableStmt.setString("d_register_traficotms", d_register_traficotms);
            callableStmt.registerOutParameter("resultado", java.sql.Types.REF_CURSOR);

            // Ejecutar el procedimiento almacenado
            callableStmt.executeUpdate();

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
