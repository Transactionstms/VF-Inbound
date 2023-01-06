/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.security;

import com.onest.io.Files;
import com.onest.misc.Calendario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

public class ArrayFuncion {

    private String url = "";
    private OracleDataSource oracleDS;
    private Connection connection;
    public static final int ORA_VARCHAR = 12;
    public static final int ORA_NUMBER = 2;
    public static final int ORA_INTEGER = 4;

    public ArrayFuncion(String serverName, String portNum, String SID) {
        this.url = ("jdbc:oracle:thin:@" + serverName + ":" + portNum + ":" + SID);
    }

    public boolean connect(String user, String password) {
        try {
            this.oracleDS = new OracleDataSource();
            this.oracleDS.setUser(user);
            this.oracleDS.setPassword(password);
            this.oracleDS.setURL(this.url);
            this.connection = this.oracleDS.getConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            com.management.util.Loggin.error(e);
        }
        return false;
    }

    public String execStore(String query, String[] datos, String fe, String mo, String rma, String id, String ct, String tipo,String ca) {
        try {
            ArrayDescriptor b = ArrayDescriptor.createDescriptor("ARRAY_RECHAZO", connection);
            ARRAY array = new ARRAY(b, connection, datos);
            CallableStatement stmt = this.connection.prepareCall(query);
            stmt.registerOutParameter(1, ORA_INTEGER);
            stmt.setString(2, fe);
            stmt.setInt(3, Integer.parseInt(mo));
            stmt.setString(4, rma);
            stmt.setString(5, id);
            stmt.setInt(6, Integer.parseInt(ct));
            stmt.setArray(7, array);
            stmt.setInt(8, Integer.parseInt(tipo));
            stmt.setInt(9, Integer.parseInt(ca));
            stmt.execute();
            Object obj = stmt.getObject(1);
            return obj.toString();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            com.management.util.Loggin.error(e);
            com.management.util.Loggin.error(query);
        }
        return "-1";
    }

    public ResultSet produ(String cve) {
          ResultSet d = null;
        try {
            PreparedStatement consulta = connection.prepareStatement("select /*+ parallel(vw_reporte_ontime 16) */ producto_sku, producto_desc from ontms_producto where cbdiv_id in (44) order by producto_desc");
            d = consulta.executeQuery();
            return d;
        }
        catch (SQLException e) {
            e.printStackTrace(System.err);
            com.management.util.Loggin.error(e);
        }
        return d;
    }

    public void close() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            com.management.util.Loggin.error(e);
        }
    }
}
