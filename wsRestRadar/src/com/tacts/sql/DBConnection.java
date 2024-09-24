/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacts.sql;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.send.email.Email;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import json.JSONArray;
import json.JSONException;
import json.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author User_Windows10
 */
public class DBConnection {

    private ConnectionPool connectionPool;
    private String token;                                      // Almacenamos el token para evitar su regeneración constante
    private String data_sin_procesar;

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
            callableStmt = conn.prepareCall("{call SP_INB_CUSTOMS_WSCONSUM_RADAR(?,?)}");

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
                shipment_id += "asignaciones?idCliente=489&shipmentId=" + rs.getString(1).trim() + "&container=" + rs.getString(2).trim() + "@";
            }

            // Se elimina el último carácter de la cadena string (si no está vacío)
            if (!shipment_id.isEmpty()) {
                shipment_id = shipment_id.substring(0, shipment_id.length() - 1);

                System.out.println("Cadena de Shipments: " + shipment_id);

                // Dividir la cadena en un array usando split() si no está vacío
                String[] arrayValores = shipment_id.split("@");

                // Iterar sobre el array de valores
                for (String path : arrayValores) {
                    cont++;

                    // Método para consumir el servicio con el token generado y obtener json
                    ConsumoRADAR(path);
                    System.out.println("N° Iteración: " + cont);
                }
            } else {
                System.out.println("No hay valores para procesar.");
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
    public String ConsumoRADAR(String path) throws IOException, JSONException, SQLException {

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
                                data_sin_procesar += tokenURL + ", ";
                            } else {
                                System.out.println("JSON recibido: " + jsonArray);

                                // Iterar sobre el JSONArray y procesar los datos
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    // Procesar cada objeto JSON
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
                                    String fecha_eta_port_discharge = jsonObject.getString("fechaPrevioOrigen").replace("null", "");
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

                                    //Enviar data a método que procesa el store procedure
                                    insertUsingStoredProcedure(referenciaAA, shipmentId, pais_origen, size_container, valor_usd, agente_aduanal, pedimento_a1, pedimento_r1_1er, motivo_rectificacion_1er, pedimento_r1_2do, motivo_rectificacion_2do, fecha_recepcion_doc, recinto, naviera, buque, fecha_revalidacion, fecha_previo_origen, fecha_eta_port_discharge, fecha_previo_destino, fecha_resultado_previo, proforma_final, permiso, fecha_envio, fecha_recepcion_perm, fecha_activacion_perm, fecha_permisos_aut, co_pref_arancelaria, aplic_pref_arancelaria, req_uva, req_ca, fecha_recepcion_ca, num_constancia_ca, monto_ca, fecha_doc_completos, fecha_pago_pedimento, fecha_solicitud_transporte, fecha_modulacion, modalidad, resultado_modulacion, fecha_reconocimiento, fecha_liberacion, sello_origen, sello_final, fecha_retencion_aut, fecha_liberacion_aut, estatus_operacion, motivo_atraso, observaciones, containerId);

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

        return name;
    }

    // Método para ejecutar un insert (store procedure)
    public void insertUsingStoredProcedure(String referenciaAA, String shipmentId, String pais_origen, String size_container, String valor_usd, String agente_aduanal, String pedimento_a1, String pedimento_r1_1er, String motivo_rectificacion_1er, String pedimento_r1_2do, String motivo_rectificacion_2do, String fecha_recepcion_doc, String recinto, String naviera, String buque, String fecha_revalidacion, String fecha_previo_origen, String fecha_eta_port_discharge, String fecha_previo_destino, String fecha_resultado_previo, String proforma_final, String permiso, String fecha_envio, String fecha_recepcion_perm, String fecha_activacion_perm, String fecha_permisos_aut, String co_pref_arancelaria, String aplic_pref_arancelaria, String req_uva, String req_ca, String fecha_recepcion_ca, String num_constancia_ca, String monto_ca, String fecha_doc_completos, String fecha_pago_pedimento, String fecha_solicitud_transporte, String fecha_modulacion, String modalidad, String resultado_modulacion, String fecha_reconocimiento, String fecha_liberacion, String sello_origen, String sello_final, String fecha_retencion_aut, String fecha_liberacion_aut, String estatus_operacion, String motivo_atraso, String observaciones, String containerId) throws SQLException {
        Connection conn = null;
        CallableStatement callableStmt = null;

        try {
            // Obtener una conexión del pool
            conn = connectionPool.getConnection();

            // Nombre del procedimiento almacenado
            String storedProcedureName = "SP_INB_WS_CONSUMO_UPLOAD_RADAR_CUSTOMS";

            // Construir la llamada al procedimiento con parámetros
            StringBuilder procedureCall = new StringBuilder("{call ");
            procedureCall.append(storedProcedureName).append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            callableStmt = conn.prepareCall(procedureCall.toString());

            // Asignar los valores de los parámetros al procedimiento almacenado
            callableStmt.setString("referenciaAA", referenciaAA);
            callableStmt.setString("shipmentId", shipmentId);
            callableStmt.setString("pais_origen", pais_origen);
            callableStmt.setString("size_container", size_container);
            callableStmt.setString("valor_usd", valor_usd);
            callableStmt.setString("agente_aduanal", agente_aduanal);
            callableStmt.setString("pedimento_a1", pedimento_a1);
            callableStmt.setString("pedimento_r1_1er", pedimento_r1_1er);
            callableStmt.setString("motivo_rectificacion_1er", motivo_rectificacion_1er);
            callableStmt.setString("pedimento_r1_2do", pedimento_r1_2do);
            callableStmt.setString("motivo_rectificacion_2do", motivo_rectificacion_2do);
            callableStmt.setString("fecha_recepcion_doc", fecha_recepcion_doc);
            callableStmt.setString("recinto", recinto);
            callableStmt.setString("naviera", naviera);
            callableStmt.setString("buque", buque);
            callableStmt.setString("fecha_revalidacion", fecha_revalidacion);
            callableStmt.setString("fecha_previo_origen", fecha_previo_origen);
            callableStmt.setString("fecha_eta_port_discharge", fecha_eta_port_discharge);
            callableStmt.setString("fecha_previo_destino", fecha_previo_destino);
            callableStmt.setString("fecha_resultado_previo", fecha_resultado_previo);
            callableStmt.setString("proforma_final", proforma_final);
            callableStmt.setString("permiso", permiso);
            callableStmt.setString("fecha_envio", fecha_envio);
            callableStmt.setString("fecha_recepcion_perm", fecha_recepcion_perm);
            callableStmt.setString("fecha_activacion_perm", fecha_activacion_perm);
            callableStmt.setString("fecha_permisos_aut", fecha_permisos_aut);
            callableStmt.setString("co_pref_arancelaria", co_pref_arancelaria);
            callableStmt.setString("aplic_pref_arancelaria", aplic_pref_arancelaria);
            callableStmt.setString("req_uva", req_uva);
            callableStmt.setString("req_ca", req_ca);
            callableStmt.setString("fecha_recepcion_ca", fecha_recepcion_ca);
            callableStmt.setString("num_constancia_ca", num_constancia_ca);
            callableStmt.setString("monto_ca", monto_ca);
            callableStmt.setString("fecha_doc_completos", fecha_doc_completos);
            callableStmt.setString("fecha_pago_pedimento", fecha_pago_pedimento);
            callableStmt.setString("fecha_solicitud_transporte", fecha_solicitud_transporte);
            callableStmt.setString("fecha_modulacion", fecha_modulacion);
            callableStmt.setString("modalidad", modalidad);
            callableStmt.setString("resultado_modulacion", resultado_modulacion);
            callableStmt.setString("fecha_reconocimiento", fecha_reconocimiento);
            callableStmt.setString("fecha_liberacion", fecha_liberacion);
            callableStmt.setString("sello_origen", sello_origen);
            callableStmt.setString("sello_final", sello_final);
            callableStmt.setString("fecha_retencion_aut", fecha_retencion_aut);
            callableStmt.setString("fecha_liberacion_aut", fecha_liberacion_aut);
            callableStmt.setString("estatus_operacion", estatus_operacion);
            callableStmt.setString("motivo_atraso", motivo_atraso);
            callableStmt.setString("observaciones", observaciones);
            callableStmt.setString("containerId", containerId);
            callableStmt.registerOutParameter("resultado", java.sql.Types.REF_CURSOR);

            // Ejecutar el procedimiento almacenado
            callableStmt.executeUpdate();

            System.out.println("¡(TRA_INB_CUSTOMS) Se actualizó en sistema, el siguiente número de shipment id: " + shipmentId + "!");
            System.out.println("-------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();

            System.out.println("¡Error al actualizar el siguiente número de shipment id: " + shipmentId + "! en Base de Datos.");
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

    // Método para validar los componentes del path
    private boolean isValidURLComponent(String value) {
        try {
            URI uri = new URI("https", "example.com", "/" + value, null);
            return true;  // Si la URI es válida, retornamos true
        } catch (URISyntaxException e) {
            return false;  // Si hay una excepción, el valor no es válido
        }
    }

    public boolean sedEmail(String objeto) throws IOException, SQLException {
        //Emisión de email - Log de errores
        Email correo = new Email();
        boolean send = correo.alertaRadarWebservice("¡Error al actualizar los siguientes números de shipment id: ", objeto);

        return send;
    }

}
