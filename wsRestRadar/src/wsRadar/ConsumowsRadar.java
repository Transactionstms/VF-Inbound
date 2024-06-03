/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsRadar;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.tacts.dao.CustomsSql;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import json.JSONException;
import json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.tacts.sql.Conexion;
import com.tacts.sql.Configuracion;
import java.sql.CallableStatement;
import json.JSONArray;

/**
 *
 * @author grecendiz
 */
public class ConsumowsRadar {

    URL url;
    private Conexion cnBaseDeDatos;

    public ConsumowsRadar() {
        Configuracion cfg = new Configuracion();
        this.cnBaseDeDatos = new Conexion(cfg.getSid(), cfg.getUsuario(), cfg.getPassword());
    }

    public String Token() throws IOException {
        String tokenURL = "https://uat-sco.radarholding.com:51012/ws/auth";
        String idUsuario = "vfoutdoor";
        String contrasena = "Gr93Dtyub&1sie";

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("idUsuario", idUsuario);
        jsonObject.addProperty("contrasena", contrasena);
        String json = new Gson().toJson(jsonObject);
        String error = "";
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(tokenURL);
            httpPost.setHeader("accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(json));

            try (CloseableHttpResponse response = client.execute(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String responseBody = EntityUtils.toString(entity);
                        JSONObject jsonResponse = new JSONObject(responseBody);
                        JSONObject jsonData = jsonResponse.getJSONObject("data");
                        String token = jsonData.getString("token").replace("null", "");
                        //System.out.println("Token: " + token);
                        return token;
                    }
                } else {
                    System.out.println("Error: HTTP " + statusCode);
                    error = "Error: HTTP " + statusCode;
                }
            }
        } catch (JSONException e) {
            System.out.println("Error: " + e.getMessage());
            error = "Error: " + e.getMessage();
        }

        return error;
    }

    //************************ NO SE OCUPAN SON PETICIONES HTTP ************************// 
    public String ConsumoRADAR(String path, String data) throws IOException, JSONException, SQLException {

        Gson gson = new Gson();
        String TokenURLP = "https://uat-sco.radarholding.com:51012/ws/" + path;
        String barer = Token();
        String responseLine = null;
        String responseLine2 = "";
        String name = "";

        String referenciaAA = "";
        String shipmentId = "";
        String pais_origen = "";
        String size_container = "";
        String valor_usd = "";
        String agente_aduanal = "";
        String pedimento_a1 = "";
        String pedimento_r1_1er = "";
        String motivo_rectificacion_1er = "";
        String pedimento_r1_2do = "";
        String motivo_rectificacion_2do = "";
        String fecha_recepcion_doc = "";
        String recinto = "";
        String naviera = "";
        String buque = "";
        String fecha_revalidacion = "";
        String fecha_previo_origen = "";
        String fecha_previo_destino = "";
        String fecha_resultado_previo = "";
        String proforma_final = "";
        String permiso = "";
        String fecha_envio = "";
        String fecha_recepcion_perm = "";
        String fecha_activacion_perm = "";
        String fecha_permisos_aut = "";
        String co_pref_arancelaria = "";
        String aplic_pref_arancelaria = "";
        String req_uva = "";
        String req_ca = "";
        String fecha_recepcion_ca = "";
        String num_constancia_ca = "";
        String monto_ca = "";
        String fecha_doc_completos = "";
        String fecha_pago_pedimento = "";
        String fecha_solicitud_transporte = "";
        String fecha_modulacion = "";
        String modalidad = "";
        String resultado_modulacion = "";
        String fecha_reconocimiento = "";
        String fecha_liberacion = "";
        String sello_origen = "";
        String sello_final = "";
        String fecha_retencion_aut = "";
        String fecha_liberacion_aut = "";
        String estatus_operacion = "";
        String motivo_atraso = "";
        String observaciones = "";
        String containerId = "";

        CallableStatement cs7 = null;
        CustomsSql update = new CustomsSql();
        int customs = 0;

        try {
            this.url = new URL(TokenURLP);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ConsumowsRadar.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "*/*");
        con.setRequestProperty("Accept-Encoding", "gzip,deflate,br");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Authorization", "Bearer " + barer);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {

            while ((responseLine = br.readLine()) != null) {
                responseLine2 += responseLine.trim();
            }

            JSONObject obj = new JSONObject(responseLine2.trim());
            JSONArray jsonArray = obj.getJSONArray("data");

            if (jsonArray.length() == 0) {
                
                System.out.println("No se arrojo información en el servicio del proveedor, del siguiente dato: " + data);
                System.out.println("-------------------------------------------------------------------------------------------------");
                        
            } else {

                // Iterar sobre el JSONArray para obtener los valores de "description"
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    referenciaAA = jsonObject.getString("referenciaAA").replace("null", "");
                    shipmentId = jsonObject.getString("shipmentId").replace("null", "");
                    pais_origen = jsonObject.getString("paisOrigen").replace("null", "");
                    size_container = jsonObject.getString("sizeContainer").replace("null", "");
                    valor_usd = jsonObject.getString("valorUSD").replace("null", "");
                    agente_aduanal = jsonObject.getString("agenteAduanalNombre").replace("null", "");
                    pedimento_a1 = jsonObject.getString("pedimentoA1").replace("null", "");
                    pedimento_r1_1er = jsonObject.getString("pedimentoR1").replace("null", "");
                    motivo_rectificacion_1er = jsonObject.getString("motivoR1").replace("null", "");
                    pedimento_r1_2do = jsonObject.getString("pedimentoR2").replace("null", "");
                    motivo_rectificacion_2do = jsonObject.getString("motivoR2").replace("null", "");
                    fecha_recepcion_doc = jsonObject.getString("fechaRecepcionDocumentos").replace("null", "");
                    recinto = jsonObject.getString("recinto").replace("null", "");
                    naviera = jsonObject.getString("navieraForwarder").replace("null", "");
                    buque = jsonObject.getString("buque").replace("null", "");
                    fecha_revalidacion = jsonObject.getString("fechaRevalidacionLiberacionBL").replace("null", "");
                    fecha_previo_origen = jsonObject.getString("fechaPrevioOrigen").replace("null", "");
                    fecha_previo_destino = jsonObject.getString("fechaPrevioEnDestino").replace("null", "");
                    fecha_resultado_previo = jsonObject.getString("fechaResultadoPrevio").replace("null", "");
                    proforma_final = jsonObject.getString("proformaFinal").replace("null", "");
                    permiso = jsonObject.getString("requierePermiso").replace("null", "");
                    fecha_envio = jsonObject.getString("fechaEnvioFichasNotas").replace("null", "");
                    fecha_recepcion_perm = jsonObject.getString("fechaRecepcionPermisosTramitados").replace("null", "");
                    fecha_activacion_perm = jsonObject.getString("fechaActivacionPermisos").replace("null", "");
                    fecha_permisos_aut = jsonObject.getString("fechaPermisosAutorizados").replace("null", "");
                    co_pref_arancelaria = jsonObject.getString("cuentaConCOParaAplicarPreferenciaArancelaria").replace("null", "");
                    aplic_pref_arancelaria = jsonObject.getString("aplicoPreferenciaArancelariaCORazon").replace("null", "");
                    req_uva = jsonObject.getString("requiereUVA").replace("null", "");
                    req_ca = jsonObject.getString("requiereCA").replace("null", "");
                    fecha_recepcion_ca = jsonObject.getString("fechaRecepcionCA").replace("null", "");
                    num_constancia_ca = jsonObject.getString("numeroDeConstanciaCA").replace("null", "");
                    monto_ca = jsonObject.getString("montoCA").replace("null", "");
                    fecha_doc_completos = jsonObject.getString("fechaDocumentosCompletos").replace("null", "");
                    fecha_pago_pedimento = jsonObject.getString("fechaPagoPedimento").replace("null", "");
                    fecha_solicitud_transporte = jsonObject.getString("fechaSolicitudDeTransporte").replace("null", "");
                    fecha_modulacion = jsonObject.getString("fechaModulacion").replace("null", "");
                    modalidad = jsonObject.getString("modalidadCamionTren").replace("null", "");
                    resultado_modulacion = jsonObject.getString("resultadoModulacionVerdeRojo").replace("null", "");
                    fecha_reconocimiento = jsonObject.getString("fechaReconocimiento").replace("null", "");
                    fecha_liberacion = jsonObject.getString("fechaLiberacion").replace("null", "");
                    sello_origen = jsonObject.getString("selloOrigen").replace("null", "");
                    sello_final = jsonObject.getString("selloFinal").replace("null", "");
                    fecha_retencion_aut = jsonObject.getString("fechaDeRetencionPorLaAutoridad").replace("null", "");
                    fecha_liberacion_aut = jsonObject.getString("fechaDeLiberacionPorRetencionDeLaAutoridad").replace("null", "");
                    estatus_operacion = jsonObject.getString("estatusDeLaOperacion").replace("null", "");
                    motivo_atraso = jsonObject.getString("motivoAtraso").replace("null", "");
                    observaciones = jsonObject.getString("observaciones").replace("null", "");
                    containerId = jsonObject.getString("container").replace("null", "");

                    try {
                        this.cnBaseDeDatos.Iniciar();
                        cs7 = update.actualizarCustomsRadar(this.cnBaseDeDatos,
                                referenciaAA,
                                shipmentId,
                                pais_origen,
                                size_container,
                                valor_usd,
                                agente_aduanal,
                                pedimento_a1,
                                pedimento_r1_1er,
                                motivo_rectificacion_1er,
                                pedimento_r1_2do,
                                motivo_rectificacion_2do,
                                fecha_recepcion_doc,
                                recinto,
                                naviera,
                                buque,
                                fecha_revalidacion,
                                fecha_previo_origen,
                                fecha_previo_destino,
                                fecha_resultado_previo,
                                proforma_final,
                                permiso,
                                fecha_envio,
                                fecha_recepcion_perm,
                                fecha_activacion_perm,
                                fecha_permisos_aut,
                                co_pref_arancelaria,
                                aplic_pref_arancelaria,
                                req_uva,
                                req_ca,
                                fecha_recepcion_ca,
                                num_constancia_ca,
                                monto_ca,
                                fecha_doc_completos,
                                fecha_pago_pedimento,
                                fecha_solicitud_transporte,
                                fecha_modulacion,
                                modalidad,
                                resultado_modulacion,
                                fecha_reconocimiento,
                                fecha_liberacion,
                                sello_origen,
                                sello_final,
                                fecha_retencion_aut,
                                fecha_liberacion_aut,
                                estatus_operacion,
                                motivo_atraso,
                                observaciones,
                                containerId);
                        cs7.execute();
                        System.out.println("(TRA_INB_CUSTOMS) Se actualizó en sistema, el siguiente número de evento: " + shipmentId);
                        System.out.println("-------------------------------------------------------------------------------------------------");
                        customs++;

                    } catch (Exception ex) {
                        System.out.println("Error al actualizar el siguiente número de evento: " + shipmentId);
                        System.out.println("Exception:" + ex.toString());
                        return shipmentId;
                    }

                }

            }

        } catch (Error e) {
            System.out.println("error" + e);
        } catch (JsonSyntaxException ex) {
            System.out.println("ex" + ex);
            Logger.getLogger(ConsumowsRadar.class.getName()).log(Level.SEVERE, null, ex);
        }

        return name;
    }

}
