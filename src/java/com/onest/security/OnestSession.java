package com.onest.security;

import java.io.Serializable;

public class OnestSession implements Serializable {

    private boolean status = false;
    private String user = "";
    private int[] group;
    private String[][] menu;
    private String sessionID;

    public boolean isSessionOk() {
        return this.status;
    }

    protected void setSessionStatus(boolean stat) {
        this.status = stat;
    }

    protected void setUser(String usr) {
        this.user = usr;
    }

    protected void setGroup(int[] grp) {
        this.group = new int[grp.length];
        this.group = grp;
    }

    protected void setSessionID(String idSession) {
        this.sessionID = idSession;
    }

    public String getUser() {
        return this.user;
    }
}