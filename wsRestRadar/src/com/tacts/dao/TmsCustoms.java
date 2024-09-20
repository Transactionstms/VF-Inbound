/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.dao;

import com.tacts.sql.Conexion;
import com.tacts.sql.Configuracion;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import json.JSONException;
import wsRadar.ConsumowsRadar;

/**
 *
 * @author Desarrollo Tacts
 */
public class TmsCustoms {

    private Conexion cnBaseDeDatos;

    public TmsCustoms() {
        Configuracion cfg = new Configuracion();
        this.cnBaseDeDatos = new Conexion(cfg.getSid(), cfg.getUsuario(), cfg.getPassword());
    }

    public boolean consultarCustomsRADAR(String agenteId) throws Exception {
        CallableStatement sp1 = null;
        ConsumowsRadar Consumows = new ConsumowsRadar();
        String contenidoRADAR = "";
        String shipment_id = "";
        int cont = 0;

        try {
            // Inicialización de Conexión
            this.cnBaseDeDatos.Iniciar();

            // Obtener los siguientes valores: (shipment_id/container_id)
            sp1 = CustomsSql.consultarCustomRADAR(this.cnBaseDeDatos, agenteId);
            sp1.execute();

            ResultSet rs = (ResultSet) sp1.getObject("resultado");
            while (rs.next()) {
                shipment_id += "asignaciones?idCliente=489&shipmentId="+rs.getString(1).trim()+"&container="+rs.getString(2).trim()+"@";
            }

            // Se elimina el último carácter de la cadena string (si no está vacío)
            if (!shipment_id.isEmpty()) {
                shipment_id = shipment_id.substring(0, shipment_id.length() - 1);
            }

            System.out.println("shipment_id: " + shipment_id);

            // Finalización de Conexión
            this.cnBaseDeDatos.Cerrar(rs);

        } catch (SQLException exception) {
            System.err.println("Error de SQL: " + exception.getMessage());
            exception.printStackTrace();  // Para depuración
            return false;
        } catch (NullPointerException exception) {
            System.err.println("Error: Objeto nulo encontrado. " + exception.getMessage());
            exception.printStackTrace();  // Para depuración
            return false;
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.err.println("Error: Índice fuera de límites. " + exception.getMessage());
            exception.printStackTrace();  // Para depuración
            return false;
        } catch (Exception exception) {
            System.err.println("Error general: " + exception.getMessage());
            exception.printStackTrace();  // Para depuración
            return false;
        } finally {
            // Aseguramos que la conexión se cierre aunque ocurra una excepción
            if (sp1 != null) {
                try {
                    sp1.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar CallableStatement: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        try {
            // Dividir la cadena en un array usando split() si no está vacío
            if (!shipment_id.isEmpty()) {
                String[] arrayValores = shipment_id.split("@");

                // Iterar sobre el array de valores
                for (String path : arrayValores) {
                    cont++;
                    contenidoRADAR = Consumows.ConsumoRADAR(path);  // valor2
                    System.out.println("N° Iteración: " + cont);
                }
            } else {
                System.out.println("No hay valores para procesar.");
            }
        } catch (NullPointerException exception) {
            System.err.println("Error: Objeto nulo encontrado al iterar sobre los valores. " + exception.getMessage());
            exception.printStackTrace();  // Para depuración
            return false;
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.err.println("Error: Índice fuera de límites al procesar array. " + exception.getMessage());
            exception.printStackTrace();  // Para depuración
            return false;
        } catch (IOException exception) {
            System.err.println("Error de IO al consultar Customs RADAR: " + exception.getMessage());
            exception.printStackTrace();  // Para depuración
            return false;
        } catch (JSONException exception) {
            System.err.println("Error al procesar JSON: " + exception.getMessage());
            exception.printStackTrace();  // Para depuración
            return false;
        } catch (Exception exception) {
            System.err.println("Error general en la iteración: " + exception.getMessage());
            exception.printStackTrace();  // Para depuración
            return false;
        }

        return true;
    }

}
