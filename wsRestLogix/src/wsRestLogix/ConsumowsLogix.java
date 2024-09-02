/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsRestLogix;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.send.email.Email;
import com.tacts.dao.CustomsSql;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import json.JSONArray;
import json.JSONException;
import json.JSONObject;
import com.tacts.sql.Conexion;
import com.tacts.sql.Configuracion;
import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 *
 * @author grecendiz
 */
public class ConsumowsLogix {

    URL url;
    private Conexion cnBaseDeDatos;

    public ConsumowsLogix() {
        Configuracion cfg = new Configuracion();
        this.cnBaseDeDatos = new Conexion(cfg.getSid(), cfg.getUsuario(), cfg.getPassword());
    }

    public String Token() throws IOException {

        String TokenURLP = "https://uat-sco.radarholding.com:51012/ws/auth";

        try {
            this.url = new URL(TokenURLP);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ConsumowsLogix.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");

        // Crear un objeto JSON
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("idUsuario", "vfoutdoor");
        jsonObject.addProperty("contrasena", "Gr93Dtyub&1sie");

        // Convertir el objeto JSON a una cadena de texto
        String json = new Gson().toJson(jsonObject);
        // Establecer el tamaño del contenido
        con.setFixedLengthStreamingMode(json.getBytes().length);
        // Obtener el flujo de salida de la conexión
        OutputStream outputStream = con.getOutputStream();
        // Escribir el JSON en el cuerpo de la solicitud
        outputStream.write(json.getBytes());
        outputStream.flush();
        outputStream.close();
        String name = "";
        String token = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            String responseLine2 = "";
            while ((responseLine = br.readLine()) != null) {
                System.out.println("response  : " + responseLine.trim());
                responseLine2 += responseLine.trim();
            }

            JSONObject jsn = new JSONObject(responseLine2.trim());
            name = jsn.getString("data");
            JSONObject jsnD = new JSONObject(name.trim());
            token = jsnD.getString("token");
            System.out.println("token" + token);

            System.out.println("token  :***************************************** " + token);
        } catch (Error e) {
            System.out.println("error" + e);
        } catch (JSONException ex) {
            System.out.println("ex" + ex);
            Logger.getLogger(ConsumowsLogix.class.getName()).log(Level.SEVERE, null, ex);
        }

        return token;
    }

    //************************ NO SE OCUPAN SON PETICIONES HTTP ************************// 
    public String ConsumoLOGIX(String shipmentId) throws SQLException {

        String TokenURLP = "http://apis.grupologix.com.mx/traficotms";
        String responseLine = null;
        String responseLine2 = "";
        String name = "";

        String id_traficotms = "";
        String evento_traficotms = "";
        String shipmentId_traficotms = "";
        String countryOrigin_traficotms = "";
        String sizeContainer_traficotms = "";
        String valueDlls_traficotms = "";
        String etaPortDischargeTwo_traficotms = "";
        String AA_traficotms = "";
        String yearPed_traficotms = "";
        String aaPat_traficotms = "";
        String customHouse_traficotms = "";
        String noPed_traficotms = "";
        String noPedRect1_traficotms = "";
        String noPedComment1_traficotms = "";
        String noPedRect2_traficotms = "";
        String noPedComment2_traficotms = "";
        String DocumentRecepcionDate_traficotms = "";
        String enclosure_traficotms = "";
        String shippingCompany_traficotms = "";
        String vessel_traficotms = "";
        String revalidationDate_traficotms = "";
        String d_previousOrigin_traficotms = "";
        String d_previousDestiny_traficotms = "";
        String d_previousResult_traficotms = "";
        String finalProforma_traficotms = "";
        String permissionRequired_traficotms = "";
        String d_sendTokens_traficotms = "";
        String d_receiptPermitsProcessed_traficotms = "";
        String d_PermitActivation_traficotms = "";
        String d_AuthorizedPermits_traficotms = "";
        String AccountwithCO_traficotms = "";
        String TariffPreferenceCO_traficotms = "";
        String requiresUVA_traficotms = "";
        String RequiresCA_traficotms = "";
        String d_receiptCA_traficotms = "";
        String certificateNumberCA_traficotms = "";
        String amountCA_traficotms = "";
        String d_completeDocuments_traficotms = "";
        String d_paidPed_traficotms = "";
        String d_transportRequest_traficotms = "";
        String d_modulation_traficotms = "";
        String modality_traficotms = "";
        String modulationResult_traficotms = "";
        String d_recognition_traficotms = "";
        String d_release_traficotms = "";
        String originSeal_traficotms = "";
        String endStamp_traficotms = "";
        String d_retentionAuthority_traficotms = "";
        String d_withHoldingAuthorityRelease_traficotms = "";
        String OperationStatus_traficotms = "";
        String reasonDelay_traficotms = "";
        String comments_traficotms = "";
        String arrivalNOVA_traficotms = "";
        String arrivalGlobalTradeSD_traficotms = "";
        String archiveM_traficotms = "";
        String d_archiveM_traficotms = "";
        String d_requestHandling_traficotms = "";
        String d_handlingExpiration_traficotms = "";
        String d_keyConfirmationPed_traficotms = "";
        String d_incrementalReception_traficotms = "";
        String tande_traficotms = "";
        String d_expirationInbound_traficotms = "";
        String d_register_traficotms = "";
        String d_updated_traficotms = "";
        String msg_logError = "";

        CallableStatement cs7 = null;
        CustomsSql update = new CustomsSql();
        int customs = 0;

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
            con.setRequestProperty("equalTo", shipmentId);
            con.setRequestProperty("select", "*");

            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {

                while ((responseLine = br.readLine()) != null) {
                    responseLine2 += responseLine.trim();
                }

                JSONObject obj = new JSONObject(responseLine2.trim());
                JSONArray jsonArray = obj.getJSONArray("results");

                if (jsonArray.length() == 0) {

                    System.out.println("No se arrojo información en el servicio del proveedor, del siguiente dato: " + shipmentId);
                    System.out.println("-------------------------------------------------------------------------------------------------");

                } else {

                    // Iterar sobre el JSONArray para obtener los valores de "description"
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        id_traficotms = jsonObject.getString("id_traficotms").replace("-", "");
                        evento_traficotms = jsonObject.getString("evento_traficotms").replace("-", "");
                        shipmentId_traficotms = jsonObject.getString("shipmentId_traficotms").replace("-", "");
                        countryOrigin_traficotms = jsonObject.getString("countryOrigin_traficotms").replace("-", "");
                        sizeContainer_traficotms = jsonObject.getString("sizeContainer_traficotms").replace("-", "");
                        valueDlls_traficotms = jsonObject.getString("valueDlls_traficotms").replace("-", "");
                        etaPortDischargeTwo_traficotms = jsonObject.getString("etaPortDischargeTwo_traficotms").replace("-", "");
                        AA_traficotms = jsonObject.getString("AA_traficotms").replace("-", "");
                        yearPed_traficotms = jsonObject.getString("yearPed_traficotms").replace("-", "");
                        aaPat_traficotms = jsonObject.getString("aaPat_traficotms").replace("-", "");
                        customHouse_traficotms = jsonObject.getString("customHouse_traficotms").replace("-", "");
                        noPed_traficotms = jsonObject.getString("noPed_traficotms").replace("-", "");
                        noPedRect1_traficotms = jsonObject.getString("noPedRect1_traficotms").replace("-", "");
                        noPedComment1_traficotms = jsonObject.getString("noPedComment1_traficotms").replace("-", "");
                        noPedRect2_traficotms = jsonObject.getString("noPedRect2_traficotms").replace("-", "");
                        noPedComment2_traficotms = jsonObject.getString("noPedComment2_traficotms").replace("-", "");
                        DocumentRecepcionDate_traficotms = jsonObject.getString("DocumentRecepcionDate_traficotms").replace("-", "");
                        enclosure_traficotms = jsonObject.getString("enclosure_traficotms").replace("-", "");
                        shippingCompany_traficotms = jsonObject.getString("shippingCompany_traficotms").replace("-", "");
                        vessel_traficotms = jsonObject.getString("vessel_traficotms").replace("-", "");
                        revalidationDate_traficotms = jsonObject.getString("revalidationDate_traficotms").replace("-", "");
                        d_previousOrigin_traficotms = jsonObject.getString("d_previousOrigin_traficotms").replace("-", "");
                        d_previousDestiny_traficotms = jsonObject.getString("d_previousDestiny_traficotms").replace("-", "");
                        d_previousResult_traficotms = jsonObject.getString("d_previousResult_traficotms").replace("-", "");
                        finalProforma_traficotms = jsonObject.getString("finalProforma_traficotms").replace("-", "");
                        permissionRequired_traficotms = jsonObject.getString("permissionRequired_traficotms").replace("-", "");
                        d_sendTokens_traficotms = jsonObject.getString("d_sendTokens_traficotms").replace("-", "");
                        d_receiptPermitsProcessed_traficotms = jsonObject.getString("d_receiptPermitsProcessed_traficotms").replace("-", "");
                        d_PermitActivation_traficotms = jsonObject.getString("d_PermitActivation_traficotms").replace("-", "");
                        d_AuthorizedPermits_traficotms = jsonObject.getString("d_AuthorizedPermits_traficotms").replace("-", "");
                        AccountwithCO_traficotms = jsonObject.getString("AccountwithCO_traficotms").replace("-", "");
                        TariffPreferenceCO_traficotms = jsonObject.getString("TariffPreferenceCO_traficotms").replace("-", "");
                        requiresUVA_traficotms = jsonObject.getString("requiresUVA_traficotms").replace("-", "");
                        RequiresCA_traficotms = jsonObject.getString("RequiresCA_traficotms").replace("-", "");
                        d_receiptCA_traficotms = jsonObject.getString("RequiresCA_traficotms").replace("-", "");
                        certificateNumberCA_traficotms = jsonObject.getString("certificateNumberCA_traficotms").replace("-", "");
                        amountCA_traficotms = jsonObject.getString("amountCA_traficotms").replace("-", "");
                        d_completeDocuments_traficotms = jsonObject.getString("d_completeDocuments_traficotms").replace("-", "");
                        d_paidPed_traficotms = jsonObject.getString("d_paidPed_traficotms").replace("-", "");
                        d_transportRequest_traficotms = jsonObject.getString("d_transportRequest_traficotms").replace("-", "");
                        d_modulation_traficotms = jsonObject.getString("d_modulation_traficotms").replace("-", "");
                        modality_traficotms = jsonObject.getString("modality_traficotms").replace("-", "");
                        modulationResult_traficotms = jsonObject.getString("modulationResult_traficotms").replace("-", "");
                        d_recognition_traficotms = jsonObject.getString("d_recognition_traficotms").replace("-", "");
                        d_release_traficotms = jsonObject.getString("d_release_traficotms").replace("-", "");
                        originSeal_traficotms = jsonObject.getString("originSeal_traficotms").replace("-", "");
                        endStamp_traficotms = jsonObject.getString("endStamp_traficotms").replace("-", "");
                        d_retentionAuthority_traficotms = jsonObject.getString("d_retentionAuthority_traficotms").replace("-", "");
                        d_withHoldingAuthorityRelease_traficotms = jsonObject.getString("d_withHoldingAuthorityRelease_traficotms").replace("-", "");
                        OperationStatus_traficotms = jsonObject.getString("OperationStatus_traficotms").replace("-", "");
                        reasonDelay_traficotms = jsonObject.getString("reasonDelay_traficotms").replace("-", "");
                        comments_traficotms = jsonObject.getString("comments_traficotms").replace("-", "");
                        arrivalNOVA_traficotms = jsonObject.getString("arrivalNOVA_traficotms").replace("-", "");
                        arrivalGlobalTradeSD_traficotms = jsonObject.getString("arrivalGlobalTradeSD_traficotms").replace("-", "");
                        archiveM_traficotms = jsonObject.getString("archiveM_traficotms").replace("-", "");
                        d_archiveM_traficotms = jsonObject.getString("d_archiveM_traficotms").replace("-", "");
                        d_requestHandling_traficotms = jsonObject.getString("d_requestHandling_traficotms").replace("-", "");
                        d_handlingExpiration_traficotms = jsonObject.getString("d_handlingExpiration_traficotms").replace("-", "");
                        d_keyConfirmationPed_traficotms = jsonObject.getString("d_keyConfirmationPed_traficotms").replace("-", "");
                        d_incrementalReception_traficotms = jsonObject.getString("d_incrementalReception_traficotms").replace("-", "");
                        tande_traficotms = jsonObject.getString("tande_traficotms").replace("-", "");
                        d_expirationInbound_traficotms = jsonObject.getString("d_expirationInbound_traficotms").replace("-", "");
                        d_register_traficotms = jsonObject.getString("d_register_traficotms").replace("-", "");
                        d_updated_traficotms = jsonObject.getString("d_updated_traficotms").replace("-", "");

                        try {
                            
                            this.cnBaseDeDatos.Iniciar();
                            cs7 = update.actualizarCustomsLogix(this.cnBaseDeDatos,
                                    evento_traficotms,
                                    shipmentId_traficotms,
                                    countryOrigin_traficotms,
                                    sizeContainer_traficotms,
                                    valueDlls_traficotms,
                                    etaPortDischargeTwo_traficotms,
                                    AA_traficotms,
                                    yearPed_traficotms,
                                    aaPat_traficotms,
                                    customHouse_traficotms,
                                    noPed_traficotms,
                                    noPedRect1_traficotms,
                                    noPedComment1_traficotms,
                                    noPedRect2_traficotms,
                                    noPedComment2_traficotms,
                                    DocumentRecepcionDate_traficotms,
                                    enclosure_traficotms,
                                    shippingCompany_traficotms,
                                    vessel_traficotms,
                                    revalidationDate_traficotms,
                                    d_previousOrigin_traficotms,
                                    d_previousDestiny_traficotms,
                                    d_previousResult_traficotms,
                                    finalProforma_traficotms,
                                    permissionRequired_traficotms,
                                    d_sendTokens_traficotms,
                                    d_receiptPermitsProcessed_traficotms,
                                    d_PermitActivation_traficotms,
                                    d_AuthorizedPermits_traficotms,
                                    AccountwithCO_traficotms,
                                    TariffPreferenceCO_traficotms,
                                    requiresUVA_traficotms,
                                    RequiresCA_traficotms,
                                    d_receiptCA_traficotms,
                                    certificateNumberCA_traficotms,
                                    amountCA_traficotms,
                                    d_completeDocuments_traficotms,
                                    d_paidPed_traficotms,
                                    d_transportRequest_traficotms,
                                    d_modulation_traficotms,
                                    modality_traficotms,
                                    modulationResult_traficotms,
                                    d_recognition_traficotms,
                                    d_release_traficotms,
                                    originSeal_traficotms,
                                    endStamp_traficotms,
                                    d_retentionAuthority_traficotms,
                                    d_withHoldingAuthorityRelease_traficotms,
                                    OperationStatus_traficotms,
                                    reasonDelay_traficotms,
                                    comments_traficotms,
                                    arrivalNOVA_traficotms,
                                    arrivalGlobalTradeSD_traficotms,
                                    archiveM_traficotms,
                                    d_archiveM_traficotms,
                                    d_requestHandling_traficotms,
                                    d_handlingExpiration_traficotms,
                                    d_keyConfirmationPed_traficotms,
                                    d_incrementalReception_traficotms,
                                    tande_traficotms,
                                    d_expirationInbound_traficotms,
                                    d_register_traficotms,
                                    d_updated_traficotms);
                            cs7.execute();
                            System.out.println("(TRA_INB_CUSTOMS) Se actualizó en sistema, el siguiente número de evento: " + evento_traficotms + " y número de shipment: " + shipmentId_traficotms);
                            System.out.println("----------------------------------------------------------------------------------------------------------------------------");
                            customs++;

                             this.cnBaseDeDatos.Finalizar();
                             
                        } catch (Exception ex) {
                            
                            msg_logError = "¡Error al actualizar el siguiente número de evento: " + evento_traficotms+"!";
                            
                            System.out.println(msg_logError);
                            System.out.println("----------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("Exception:" + ex.toString());
                            
                            //Emisión de email - Log de errores
                            Email correo = new Email();
                            correo.alertaLogixWebservice(msg_logError, jsonObject);
                            
                            return evento_traficotms;
                        }

                    }
                }

            } catch (Error e) {
                System.out.println("error" + e);
                return "error";
            } catch (JSONException ex) {
                System.out.println("ex" + ex);
                Logger.getLogger(ConsumowsLogix.class.getName()).log(Level.SEVERE, null, ex);
                return "error";
            }

        } catch (IOException ex) {
            System.out.println("ex" + ex);
            return "{\"error\": \"No se encontro shipment\"}";
        }

        return name;
    }

}
