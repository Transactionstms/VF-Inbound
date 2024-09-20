/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsRadar;

/**
 *
 * @author grecendiz
 */
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.send.email.Email;
import com.tacts.dao.CustomsSql;
import com.tacts.sql.Conexion;
import com.tacts.sql.Configuracion;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpStatus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import json.JSONArray;
import json.JSONException;
import json.JSONObject;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

public class ConsumowsRadar {

    CustomsSql update = new CustomsSql();
    private final Conexion cnBaseDeDatos;
    CallableStatement cs7 = null;

    public ConsumowsRadar() {
        Configuracion cfg = new Configuracion();
        this.cnBaseDeDatos = new Conexion(cfg.getSid(), cfg.getUsuario(), cfg.getPassword());
    }

    private String token; // Almacenamos el token para evitar su regeneración constante

    // Método para obtener el token de autenticación
    public String getToken() throws IOException {
        if (this.token == null || this.token.isEmpty()) {
            this.token = generateToken();
        }
        return this.token;
    }

    // Método que genera el token (método privado para encapsular la lógica)
    private String generateToken() throws IOException {
        String tokenURL = "https://uat-sco.radarholding.com:51012/ws/auth";
        String idUsuario = "vfoutdoor";
        String contrasena = "Gr93Dtyub&1sie";

        // Crear el objeto JSON para la solicitud
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("idUsuario", idUsuario);
        jsonObject.addProperty("contrasena", contrasena);
        String json = gson.toJson(jsonObject);

        // Configurar el cliente HTTP y la solicitud POST
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(tokenURL);
            httpPost.setHeader("accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(json));

            // Ejecutar la solicitud y manejar la respuesta
            try (CloseableHttpResponse response = client.execute(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());

                // Imprimir la respuesta para depuración
                System.out.println("Respuesta del servidor de autenticación: " + responseBody);

                if (statusCode == HttpStatus.SC_OK) {
                    // Procesar el JSON de la respuesta
                    JSONObject jsonResponse = new JSONObject(responseBody);
                    JSONObject jsonData = jsonResponse.getJSONObject("data");
                    return jsonData.getString("token").replace("null", "");
                } else {
                    // Si no es 200 OK, imprimir más detalles
                    System.err.println("Error al obtener el token: HTTP " + statusCode);
                    System.err.println("Cuerpo de la respuesta: " + responseBody);
                }
            }
        } catch (JSONException e) {
            System.err.println("Error al parsear el token JSON: " + e.getMessage());
        }

        // Retornar una cadena vacía en caso de error
        return "";
    }

    // Método para consumir el servicio con el token generado y procesar la base de datos
    public String ConsumoRADAR(String path) throws IOException, JSONException, SQLException {
        
        //Inicialización de Conexión
        this.cnBaseDeDatos.Iniciar();
          
        String tokenURL = "https://uat-sco.radarholding.com:51012/ws/" + path;
        String token = getToken();
        String name = "";

        // Aseguramos que los valores son válidos antes de procesar la URL
        try {
            // Validamos que los valores no contengan caracteres inválidos
            if (!isValidURLComponent(path)) {
                System.err.println("Error: La cadena del path contiene caracteres no válidos: " + path);
                return "";  // Saltamos la iteración si el path es inválido
            }

            // Realizamos la conexión HTTP usando GET
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                HttpGet httpGet = new HttpGet(tokenURL);
                httpGet.setHeader("Authorization", "Bearer " + token);
                httpGet.setHeader("Accept", "*/*");
                httpGet.setHeader("Accept-Encoding", "gzip,deflate,br");
                httpGet.setHeader("Connection", "keep-alive");

                try (CloseableHttpResponse response = client.execute(httpGet); BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"))) {
                    
                    StringBuilder responseLine2 = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        responseLine2.append(responseLine.trim());
                    }

                    // Validar si la respuesta es realmente un JSON antes de procesarla
                    String responseText = responseLine2.toString().trim();
                    if (responseText.startsWith("{")) {
                        // Procesar el JSON
                        JSONObject obj = new JSONObject(responseText);

                        if (obj.has("data")) {
                            JSONArray jsonArray = obj.getJSONArray("data");

                            if (jsonArray.length() == 0) {
                                System.out.println("Petición sin retorno de información: " + tokenURL);
                            } else {
                                System.out.println("JSON recibido: " + jsonArray);
                                
                                // Iterar sobre el JSONArray y procesar los datos
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    // Procesar cada objeto JSON como sea necesario

                                    String referenciaAA = jsonObject.getString("referenciaAA").replace("null", "");
                                    String shipmentId = jsonObject.getString("shipmentId").replace("null", "");
                                    String pais_origen = jsonObject.getString("paisOrigen").replace("null", "");
                                    String size_container = jsonObject.getString("sizeContainer").replace("null", "");
                                    String valor_usd = jsonObject.getString("valorUSD").replace("null", "");
                                    String agente_aduanal = jsonObject.getString("agenteAduanalNombre").replace("null", "");
                                    String pedimento_a1 = jsonObject.getString("pedimentoA1").replace("null", "");
                                    String pedimento_r1_1er = jsonObject.getString("pedimentoR1").replace("null", "");
                                    String motivo_rectificacion_1er = jsonObject.getString("motivoR1").replace("null", "");
                                    String pedimento_r1_2do = jsonObject.getString("pedimentoR2").replace("null", "");
                                    String motivo_rectificacion_2do = jsonObject.getString("motivoR2").replace("null", "");
                                    String fecha_recepcion_doc = jsonObject.getString("fechaRecepcionDocumentos").replace("null", "");
                                    String recinto = jsonObject.getString("recinto").replace("null", "");
                                    String naviera = jsonObject.getString("navieraForwarder").replace("null", "");
                                    String buque = jsonObject.getString("buque").replace("null", "");
                                    String fecha_revalidacion = jsonObject.getString("fechaRevalidacionLiberacionBL").replace("null", "");
                                    String fecha_previo_origen = jsonObject.getString("fechaPrevioOrigen").replace("null", "");
                                    String fecha_previo_destino = jsonObject.getString("fechaPrevioEnDestino").replace("null", "");
                                    String fecha_resultado_previo = jsonObject.getString("fechaResultadoPrevio").replace("null", "");
                                    String proforma_final = jsonObject.getString("proformaFinal").replace("null", "");
                                    String permiso = jsonObject.getString("requierePermiso").replace("null", "");
                                    String fecha_envio = jsonObject.getString("fechaEnvioFichasNotas").replace("null", "");
                                    String fecha_recepcion_perm = jsonObject.getString("fechaRecepcionPermisosTramitados").replace("null", "");
                                    String fecha_activacion_perm = jsonObject.getString("fechaActivacionPermisos").replace("null", "");
                                    String fecha_permisos_aut = jsonObject.getString("fechaPermisosAutorizados").replace("null", "");
                                    String co_pref_arancelaria = jsonObject.getString("cuentaConCOParaAplicarPreferenciaArancelaria").replace("null", "");
                                    String aplic_pref_arancelaria = jsonObject.getString("aplicoPreferenciaArancelariaCORazon").replace("null", "");
                                    String req_uva = jsonObject.getString("requiereUVA").replace("null", "");
                                    String req_ca = jsonObject.getString("requiereCA").replace("null", "");
                                    String fecha_recepcion_ca = jsonObject.getString("fechaRecepcionCA").replace("null", "");
                                    String num_constancia_ca = jsonObject.getString("numeroDeConstanciaCA").replace("null", "");
                                    String monto_ca = jsonObject.getString("montoCA").replace("null", "");
                                    String fecha_doc_completos = jsonObject.getString("fechaDocumentosCompletos").replace("null", "");
                                    String fecha_pago_pedimento = jsonObject.getString("fechaPagoPedimento").replace("null", "");
                                    String fecha_solicitud_transporte = jsonObject.getString("fechaSolicitudDeTransporte").replace("null", "");
                                    String fecha_modulacion = jsonObject.getString("fechaModulacion").replace("null", "");
                                    String modalidad = jsonObject.getString("modalidadCamionTren").replace("null", "");
                                    String resultado_modulacion = jsonObject.getString("resultadoModulacionVerdeRojo").replace("null", "");
                                    String fecha_reconocimiento = jsonObject.getString("fechaReconocimiento").replace("null", "");
                                    String fecha_liberacion = jsonObject.getString("fechaLiberacion").replace("null", "");
                                    String sello_origen = jsonObject.getString("selloOrigen").replace("null", "");
                                    String sello_final = jsonObject.getString("selloFinal").replace("null", "");
                                    String fecha_retencion_aut = jsonObject.getString("fechaDeRetencionPorLaAutoridad").replace("null", "");
                                    String fecha_liberacion_aut = jsonObject.getString("fechaDeLiberacionPorRetencionDeLaAutoridad").replace("null", "");
                                    String estatus_operacion = jsonObject.getString("estatusDeLaOperacion").replace("null", "");
                                    String motivo_atraso = jsonObject.getString("motivoAtraso").replace("null", "");
                                    String observaciones = jsonObject.getString("observaciones").replace("null", "");
                                    String containerId = jsonObject.getString("container").replace("null", "");

                                    try {

                                        CallableStatement cs7 = update.actualizarCustomsRadar(this.cnBaseDeDatos,
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

                                        System.out.println("¡(TRA_INB_CUSTOMS) Se actualizó en sistema, el siguiente número de shipment id: " + shipmentId + "!");
                                        System.out.println("-------------------------------------------------------------------------------------------------");

                                    } catch (Exception ex) {

                                        System.out.println("¡Error al actualizar el siguiente número de shipment id: " + shipmentId + "! en Base de Datos.");
                                        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
                                        System.out.println("Exception:" + ex.toString());

                                        //Emisión de correo (Proceso): 
                                        return shipmentId;
                                    }

                                }
                            }

                        } else {
                            System.out.println("El campo 'data' no está presente en la respuesta JSON.");
                        }
                    } else {
                        System.err.println("La respuesta no es un JSON válido: " + responseText);
                    }

                } catch (JSONException e) {
                    System.err.println("Error al procesar el JSON: " + e.getMessage());
                    e.printStackTrace();  // Para depuración
                } catch (IOException e) {
                    System.err.println("Error de I/O al leer la respuesta: " + e.getMessage());
                    e.printStackTrace();  // Para depuración
                }
            } catch (IOException e) {
                System.err.println("Error al realizar la conexión HTTP: " + e.getMessage());
                e.printStackTrace();  // Para depuración
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error en la URL: " + e.getMessage());
            e.printStackTrace();  // Para depuración
            // Continuar con la siguiente iteración si hay un problema con la URL
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
            e.printStackTrace();  // Para depuración
        }

        // Finalización de Conexión
        this.cnBaseDeDatos.CerrarStatement(cs7);

        return name;
    }

// Método para validar los componentes del path
    private boolean isValidURLComponent(String value) {
        try {
            URI uri = new URI("https", "example.com", "/" + value, null);
            return true;  // Si la URI es válida, retornamos true
        } catch (URISyntaxException e) {
            return false;  // Si hay una excepción, el valor no es válido
        }
    }

    /* public String sedEmail(String shipment, JsonObject objeto) throws IOException {
            //Emisión de email - Log de errores
           // Email correo = new Email();
           // boolean send = correo.alertaRadarWebservice("¡Error al actualizar el siguiente número de shipment id: " + shipment + "! en Base de Datos.", objeto);
            
            
    }*/
}
