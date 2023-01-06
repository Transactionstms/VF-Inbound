package com.onest.net;

import java.io.Serializable;

public class Connection implements Serializable {

    private String serverID;
    private String ipv4;
    private String ipv6;
    private String sid;
    private String port;
    private String user;
    private String passwd;
    private String serverName;
    private String dbName;

    public Connection(String serverID, String ipv4, String ipv6, String serverName, String sid, String port, String user, String passwd, String dbName) {
        this.serverID = serverID;
        this.ipv4 = ipv4;
        this.ipv6 = (ipv6 == null ? "" : ipv6);
        this.serverName = serverName;
        this.sid = sid;
        this.port = port;
        this.user = user;
        this.passwd = passwd;
        this.dbName = dbName;
    }

    public String getServerIP(String whichVersion) {
        if (whichVersion.equals("v4")) {
            return this.ipv4;
        }
        return this.ipv6;
    }

    public String getServerName() {
        return this.serverName;
    }

    public String getSID() {
        return this.sid;
    }

    public String getUser() {
        return this.user;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public String getPort() {
        return this.port;
    }

    public String getDBName() {
        return this.dbName;
    }

    public String getServerID() {
        return this.serverID;
    }
}