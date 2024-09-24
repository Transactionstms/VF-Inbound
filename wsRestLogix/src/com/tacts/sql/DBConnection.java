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
                shipment_id = rs.getString(6);

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
            /*if (!data_sin_procesar.equals("")) {
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

        String TokenURLP = "http://apis.grupologix.com.mx/traficotms";
        String responseLine = null;
        String responseLine2 = "";
        String msg_logError = "";

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

                        String id_traficotms = jsonObject.getString("id_traficotms").replace("-", "");
                        String evento_traficotms = jsonObject.getString("evento_traficotms").replace("-", "");
                        String shipmentId_traficotms = jsonObject.getString("shipmentId_traficotms").replace("-", "");
                        String countryOrigin_traficotms = jsonObject.getString("countryOrigin_traficotms").replace("-", "");
                        String sizeContainer_traficotms = jsonObject.getString("sizeContainer_traficotms").replace("-", "");
                        String valueDlls_traficotms = jsonObject.getString("valueDlls_traficotms").replace("-", "");
                        String etaPortDischargeTwo_traficotms = jsonObject.getString("etaPortDischargeTwo_traficotms").replace("-", "");
                        String AA_traficotms = jsonObject.getString("AA_traficotms").replace("-", "");
                        String yearPed_traficotms = jsonObject.getString("yearPed_traficotms").replace("-", "");
                        String aaPat_traficotms = jsonObject.getString("aaPat_traficotms").replace("-", "");
                        String customHouse_traficotms = jsonObject.getString("customHouse_traficotms").replace("-", "");
                        String noPed_traficotms = jsonObject.getString("noPed_traficotms").replace("-", "");
                        String noPedRect1_traficotms = jsonObject.getString("noPedRect1_traficotms").replace("-", "");
                        String noPedComment1_traficotms = jsonObject.getString("noPedComment1_traficotms").replace("-", "");
                        String noPedRect2_traficotms = jsonObject.getString("noPedRect2_traficotms").replace("-", "");
                        String noPedComment2_traficotms = jsonObject.getString("noPedComment2_traficotms").replace("-", "");
                        String DocumentRecepcionDate_traficotms = jsonObject.getString("DocumentRecepcionDate_traficotms").replace("-", "");
                        String enclosure_traficotms = jsonObject.getString("enclosure_traficotms").replace("-", "");
                        String shippingCompany_traficotms = jsonObject.getString("shippingCompany_traficotms").replace("-", "");
                        String vessel_traficotms = jsonObject.getString("vessel_traficotms").replace("-", "");
                        String revalidationDate_traficotms = jsonObject.getString("revalidationDate_traficotms").replace("-", "");
                        String d_previousOrigin_traficotms = jsonObject.getString("d_previousOrigin_traficotms").replace("-", "");
                        String d_previousDestiny_traficotms = jsonObject.getString("d_previousDestiny_traficotms").replace("-", "");
                        String d_previousResult_traficotms = jsonObject.getString("d_previousResult_traficotms").replace("-", "");
                        String finalProforma_traficotms = jsonObject.getString("finalProforma_traficotms").replace("-", "");
                        String permissionRequired_traficotms = jsonObject.getString("permissionRequired_traficotms").replace("-", "");
                        String d_sendTokens_traficotms = jsonObject.getString("d_sendTokens_traficotms").replace("-", "");
                        String d_receiptPermitsProcessed_traficotms = jsonObject.getString("d_receiptPermitsProcessed_traficotms").replace("-", "");
                        String d_PermitActivation_traficotms = jsonObject.getString("d_PermitActivation_traficotms").replace("-", "");
                        String d_AuthorizedPermits_traficotms = jsonObject.getString("d_AuthorizedPermits_traficotms").replace("-", "");
                        String AccountwithCO_traficotms = jsonObject.getString("AccountwithCO_traficotms").replace("-", "");
                        String TariffPreferenceCO_traficotms = jsonObject.getString("TariffPreferenceCO_traficotms").replace("-", "");
                        String requiresUVA_traficotms = jsonObject.getString("requiresUVA_traficotms").replace("-", "");
                        String RequiresCA_traficotms = jsonObject.getString("RequiresCA_traficotms").replace("-", "");
                        String d_receiptCA_traficotms = jsonObject.getString("RequiresCA_traficotms").replace("-", "");
                        String certificateNumberCA_traficotms = jsonObject.getString("certificateNumberCA_traficotms").replace("-", "");
                        String amountCA_traficotms = jsonObject.getString("amountCA_traficotms").replace("-", "");
                        String d_completeDocuments_traficotms = jsonObject.getString("d_completeDocuments_traficotms").replace("-", "");
                        String d_paidPed_traficotms = jsonObject.getString("d_paidPed_traficotms").replace("-", "");
                        String d_transportRequest_traficotms = jsonObject.getString("d_transportRequest_traficotms").replace("-", "");
                        String d_modulation_traficotms = jsonObject.getString("d_modulation_traficotms").replace("-", "");
                        String modality_traficotms = jsonObject.getString("modality_traficotms").replace("-", "");
                        String modulationResult_traficotms = jsonObject.getString("modulationResult_traficotms").replace("-", "");
                        String d_recognition_traficotms = jsonObject.getString("d_recognition_traficotms").replace("-", "");
                        String d_release_traficotms = jsonObject.getString("d_release_traficotms").replace("-", "");
                        String originSeal_traficotms = jsonObject.getString("originSeal_traficotms").replace("-", "");
                        String endStamp_traficotms = jsonObject.getString("endStamp_traficotms").replace("-", "");
                        String d_retentionAuthority_traficotms = jsonObject.getString("d_retentionAuthority_traficotms").replace("-", "");
                        String d_withHoldingAuthorityRelease_traficotms = jsonObject.getString("d_withHoldingAuthorityRelease_traficotms").replace("-", "");
                        String OperationStatus_traficotms = jsonObject.getString("OperationStatus_traficotms").replace("-", "");
                        String reasonDelay_traficotms = jsonObject.getString("reasonDelay_traficotms").replace("-", "");
                        String comments_traficotms = jsonObject.getString("comments_traficotms").replace("-", "");
                        String arrivalNOVA_traficotms = jsonObject.getString("arrivalNOVA_traficotms").replace("-", "");
                        String arrivalGlobalTradeSD_traficotms = jsonObject.getString("arrivalGlobalTradeSD_traficotms").replace("-", "");
                        String archiveM_traficotms = jsonObject.getString("archiveM_traficotms").replace("-", "");
                        String d_archiveM_traficotms = jsonObject.getString("d_archiveM_traficotms").replace("-", "");
                        String d_requestHandling_traficotms = jsonObject.getString("d_requestHandling_traficotms").replace("-", "");
                        String d_handlingExpiration_traficotms = jsonObject.getString("d_handlingExpiration_traficotms").replace("-", "");
                        String d_keyConfirmationPed_traficotms = jsonObject.getString("d_keyConfirmationPed_traficotms").replace("-", "");
                        String d_incrementalReception_traficotms = jsonObject.getString("d_incrementalReception_traficotms").replace("-", "");
                        String tande_traficotms = jsonObject.getString("tande_traficotms").replace("-", "");
                        String d_expirationInbound_traficotms = jsonObject.getString("d_expirationInbound_traficotms").replace("-", "");
                        String d_register_traficotms = jsonObject.getString("d_register_traficotms").replace("-", "");
                        String d_updated_traficotms = jsonObject.getString("d_updated_traficotms").replace("-", "");

                        try {

                            insertUsingStoredProcedure(evento_traficotms, shipmentId_traficotms, countryOrigin_traficotms, sizeContainer_traficotms, valueDlls_traficotms, etaPortDischargeTwo_traficotms, AA_traficotms, yearPed_traficotms, aaPat_traficotms, customHouse_traficotms, noPed_traficotms, noPedRect1_traficotms, noPedComment1_traficotms, noPedRect2_traficotms, noPedComment2_traficotms, DocumentRecepcionDate_traficotms, enclosure_traficotms, shippingCompany_traficotms, vessel_traficotms, revalidationDate_traficotms, d_previousOrigin_traficotms, d_previousDestiny_traficotms, d_previousResult_traficotms, finalProforma_traficotms, permissionRequired_traficotms, d_sendTokens_traficotms, d_receiptPermitsProcessed_traficotms, d_PermitActivation_traficotms, d_AuthorizedPermits_traficotms, AccountwithCO_traficotms, TariffPreferenceCO_traficotms, requiresUVA_traficotms, RequiresCA_traficotms, d_receiptCA_traficotms, certificateNumberCA_traficotms, amountCA_traficotms, d_completeDocuments_traficotms, d_paidPed_traficotms, d_transportRequest_traficotms, d_modulation_traficotms, modality_traficotms, modulationResult_traficotms, d_recognition_traficotms, d_release_traficotms, originSeal_traficotms, endStamp_traficotms, d_retentionAuthority_traficotms, d_withHoldingAuthorityRelease_traficotms, OperationStatus_traficotms, reasonDelay_traficotms, comments_traficotms, arrivalNOVA_traficotms, arrivalGlobalTradeSD_traficotms, archiveM_traficotms, d_archiveM_traficotms, d_requestHandling_traficotms, d_handlingExpiration_traficotms, d_keyConfirmationPed_traficotms, d_incrementalReception_traficotms, tande_traficotms, d_expirationInbound_traficotms, d_register_traficotms, d_updated_traficotms);

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
    public void insertUsingStoredProcedure(String evento_traficotms, String shipmentId_traficotms, String countryOrigin_traficotms, String sizeContainer_traficotms, String valueDlls_traficotms, String etaPortDischargeTwo_traficotms, String AA_traficotms, String yearPed_traficotms, String aaPat_traficotms, String customHouse_traficotms, String noPed_traficotms, String noPedRect1_traficotms, String noPedComment1_traficotms, String noPedRect2_traficotms, String noPedComment2_traficotms, String DocumentRecepcionDate_traficotms, String enclosure_traficotms, String shippingCompany_traficotms, String vessel_traficotms, String revalidationDate_traficotms, String d_previousOrigin_traficotms, String d_previousDestiny_traficotms, String d_previousResult_traficotms, String finalProforma_traficotms, String permissionRequired_traficotms, String d_sendTokens_traficotms, String d_receiptPermitsProcessed_traficotms, String d_PermitActivation_traficotms, String d_AuthorizedPermits_traficotms, String AccountwithCO_traficotms, String TariffPreferenceCO_traficotms, String requiresUVA_traficotms, String RequiresCA_traficotms, String d_receiptCA_traficotms, String certificateNumberCA_traficotms, String amountCA_traficotms, String d_completeDocuments_traficotms, String d_paidPed_traficotms, String d_transportRequest_traficotms, String d_modulation_traficotms, String modality_traficotms, String modulationResult_traficotms, String d_recognition_traficotms, String d_release_traficotms, String originSeal_traficotms, String endStamp_traficotms, String d_retentionAuthority_traficotms, String d_withHoldingAuthorityRelease_traficotms, String OperationStatus_traficotms, String reasonDelay_traficotms, String comments_traficotms, String arrivalNOVA_traficotms, String arrivalGlobalTradeSD_traficotms, String archiveM_traficotms, String d_archiveM_traficotms, String d_requestHandling_traficotms, String d_handlingExpiration_traficotms, String d_keyConfirmationPed_traficotms, String d_incrementalReception_traficotms, String tande_traficotms, String d_expirationInbound_traficotms, String d_register_traficotms, String d_updated_traficotms) throws SQLException {
        Connection conn = null;
        CallableStatement callableStmt = null;

        try {
            // Obtener una conexión del pool
            conn = connectionPool.getConnection();

            // Nombre del procedimiento almacenado
            String storedProcedureName = "SP_INB_WS_CONSUMO_UPLOAD_LOGIX_CUSTOMS";

            // Construir la llamada al procedimiento con parámetros
            StringBuilder procedureCall = new StringBuilder("{call ");
            procedureCall.append(storedProcedureName).append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

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
            callableStmt.setString("d_updated_traficotms", d_updated_traficotms);
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

}
