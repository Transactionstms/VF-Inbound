/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.security;

public class AlarmaUsuario {

    private int controlID;
  private   String controlIP;
 private    String controlPuerto;
 private   String cmdOFF;
   private  String cmdON;

    public AlarmaUsuario(int controlID, String controlIP, String controlPuerto, String cmdOFF, String cmdON) {
        this.controlID = controlID;
        this.controlIP = controlIP;
        this.controlPuerto = controlPuerto;
        this.cmdOFF = cmdOFF;
        this.cmdON = cmdON;
    }
           
}
