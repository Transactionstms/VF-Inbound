package com.onest.oracle;

import com.onest.io.Files;
import com.onest.net.Connection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DBConfData extends DBConf
{
  private String ipv4;
  private String ipv6;
  private String serverName;
  private String dbName;
  private String serverID;
  private String puerto;
  private String sid;
  private String usr;
  private String passwd;
  private boolean isSetConf = false;
  private Lock confLock = new ReentrantLock();

  public synchronized void setConf(Connection conn) {
    try {
      this.confLock.lock();
      Files.setDir(Files.CONF_DIR);
      Files.readFromFile(Files.CONF_DIR + "oraconf");
      this.ipv4 = conn.getServerIP("v4");
      this.ipv6 = conn.getServerIP("v6");
      this.puerto = conn.getPort();
      this.sid = conn.getSID();
      this.usr = conn.getUser();
      this.passwd = conn.getPasswd();
      this.serverName = conn.getServerName();
      this.dbName = conn.getDBName();
      this.serverID = conn.getServerID();
      this.isSetConf = true;
    } finally {
      this.confLock.unlock();
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
    return this.isSetConf;
  }

    public boolean doDB(String Estatus_Carton) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<String[]> getResultado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}