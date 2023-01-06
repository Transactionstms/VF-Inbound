/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.security;

import java.io.Serializable;

/**
 *
 * @author miguel
 */
public class AlarmaBean implements Serializable {

    private int alarmaId = 0;
    private String alarmaIp = null;
    private String alarmaPuerto = null;
    private String alarmaOn = null;
    private String alarmaOff = null;
    private String currentCmd = null;

    AlarmaBean(int controlID, String controlIP, String controlPuerto, String cmdOFF, String cmdON) {
        this.alarmaId = controlID;
        this.alarmaIp = controlIP;
        this.alarmaPuerto = controlPuerto;
        this.alarmaOn = cmdON;
        this.alarmaOff = cmdOFF;
    }

    public int getAlarmaId() {
        return alarmaId;
    }

    public void setAlarmaId(int alarmaId) {
        this.alarmaId = alarmaId;
    }

    /**
     * @return the alarmaId
     */
    

    /**
     * @return the alarmaIp
     */
    public String getAlarmaIp() {
        return alarmaIp;
    }

    /**
     * @param alarmaIp the alarmaIp to set
     */
    public void setAlarmaIp(String alarmaIp) {
        this.alarmaIp = alarmaIp;
    }

    /**
     * @return the alarmaPuerto
     */
    public String getAlarmaPuerto() {
        return alarmaPuerto;
    }

    /**
     * @param alarmaPuerto the alarmaPuerto to set
     */
    public void setAlarmaPuerto(String alarmaPuerto) {
        this.alarmaPuerto = alarmaPuerto;
    }

    /**
     * @return the alarmaOn
     */
    public String getAlarmaOn() {
        return alarmaOn;
    }

    /**
     * @param alarmaOn the alarmaOn to set
     */
    public void setAlarmaOn(String alarmaOn) {
        this.alarmaOn = alarmaOn;
    }

    /**
     * @return the alarmaOff
     */
    public String getAlarmaOff() {
        return alarmaOff;
    }

    /**
     * @param alarmaOff the alarmaOff to set
     */
    public void setAlarmaOff(String alarmaOff) {
        this.alarmaOff = alarmaOff;
    }

    /**
     * @return the currentCmd
     */
    public String getCurrentCmd() {
        return currentCmd;
    }

    /**
     * @param currentCmd the currentCmd to set
     */
    public void setCurrentCmd(String currentCmd) {
        this.currentCmd = currentCmd;
    }
    /**
     * @return the currentCmd
     */
}
