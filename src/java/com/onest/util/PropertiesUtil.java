/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.util;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author miguel
 */
public class PropertiesUtil {
    //oracle

    public static String ORACLE_KEY_USER = "db.oracle.user";
    public static String ORACLE_KEY_PASSWORD = "db.oracle.password";
    public static String ORACLE_KEY_URL = "db.oracle.url";
    public static String ORACLE_KEY_SID = "db.oracle.sid";
    public static String ORACLE_KEY_PORT = "db.oracle.port";
    public static String ORACLE_KEY_IP = "db.oracle.ip";
    //server
    public static String SERVER_KEY_IP = "server.ip";
    //application
    public static String APP_RUTA_LOGO = "app.ruta.logo1";
    private static final Properties conexionProperties = new Properties();

    public static void load() {
        try {
            conexionProperties.load(PropertiesUtil.class.getResourceAsStream("/dbconexion.properties"));
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public static String getDbConexionProperties(String key) {
        return conexionProperties.getProperty(key);
    }
}
