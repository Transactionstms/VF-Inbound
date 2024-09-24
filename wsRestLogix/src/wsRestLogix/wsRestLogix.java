/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wsrestlogix;

import com.tacts.sql.ConnectionPool;
import com.tacts.sql.DBConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import json.JSONException;

/**
 *
 * @author User_Windows10
 */
public class WsRestLogix {

    public static void main(String[] args) throws IOException, JSONException, SQLException {
        ConnectionPool connectionPool = new ConnectionPool();
        DBConnection dbConnection = new DBConnection(connectionPool);

        String agenteId = "4001";  // Valor para el parámetro de entrada

        try {
            // Llama al método para ejecutar el stored procedure con el parámetro de entrada
            List<String> result = dbConnection.executeStoredProcedureWithQuery(agenteId);

            // Imprimir los resultados concatenados
            for (String concatenatedResult : result) {
                System.out.println("Resultado final: " + concatenatedResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Cerrar todas las conexiones del pool
                connectionPool.closeAllConnections();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
