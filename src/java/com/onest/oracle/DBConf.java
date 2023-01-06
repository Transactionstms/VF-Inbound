package com.onest.oracle;

import com.onest.io.Files;
import com.onest.misc.Utils;
import com.onest.util.PropertiesUtil;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DBConf {

    private String puerto;
    private String sid;
    private String usr;
    private String passwd;
    private String ipv4;
    private String ipv6;
    private String serverName;
    private String dbName;
    private String serverID;
    private boolean isSetConf = false;

    public synchronized void setConf() {
        try {
            //this.confLock.lock();
            Files.setDir(Files.CONF_DIR);
            //if (Utils.DEBUG_LEVEL > 5) {
                //System.out.println(Files.CONF_DIR + Utils.ORA_CONF);
            //}
            //this.ipv4 = "174.136.52.230";

            this.ipv4 = PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_IP);
            this.ipv6 = null;
            this.puerto = PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_PORT);
            this.sid = PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_SID);
            this.usr = PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_USER);
            this.passwd = PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_PASSWORD);
            this.serverName = null;
            this.dbName = null;
            this.serverID = null;

            //if (Utils.DEBUG_LEVEL > 5) System.out.println("DBConf.setConf() -> ipv4:" + this.ipv4 + " ipv6:" + this.ipv6 + " puerto:" + this.puerto + " sid:" + this.sid + " usr:" + this.usr + " passwd:" + this.passwd + " serverName:" + this.serverName + " dbName:" + this.dbName + " serverID:" + this.serverID);
            this.isSetConf = true;
        } finally {
            //this.confLock.unlock();
        }
    }

    public synchronized void setConf(String conFile) {
        try {
            //this.confLock.lock();
            Files.setDir(Files.CONF_DIR);
            this.ipv4 = PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_IP);
            this.ipv6 = null;
            this.puerto = PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_PORT);
            this.sid = PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_SID);
            this.usr = PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_USER);
            this.passwd = PropertiesUtil.getDbConexionProperties(PropertiesUtil.ORACLE_KEY_PASSWORD);
            this.serverName = null;
            this.dbName = null;
            this.serverID = null;
           
            this.isSetConf = true;
        } finally {
            //this.confLock.unlock();
        }
    }

    public String getIPv4() {
        return this.ipv4;
    }

    public String getIPv6() {
        return this.ipv6;
    }

    public String getPuerto() {
        return this.puerto;
    }

    public String getSid() {
        return this.sid;
    }

    public String getUser() {
        return this.usr;
    }

    public String getPassword() {
        return this.passwd;
    }

    public String getServerName() {
        return this.serverName;
    }

    public String getDBName() {
        return this.dbName;
    }

    public String getServerID() {
        return this.serverID;
    }

    public synchronized boolean isConfigured() {
        try {
            //this.confLock.lock();
            boolean bool = this.isSetConf;
            return bool;
        } finally {
            //this.confLock.unlock();
        }
    }
}