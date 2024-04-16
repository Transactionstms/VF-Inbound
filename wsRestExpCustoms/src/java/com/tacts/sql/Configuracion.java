/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.sql;

/**
 *
 * @author grece
 */
public class Configuracion {
    
   private String servidor;
    private String sid;
    private String usuario;
    private String password;
    private String puerto;
    
    private void leerArchivo() {
        this.servidor = "74.208.140.125";
        this.sid = "tacts125";
        this.usuario = "VANS39TEST";
        this.password = "XUKidn49N875RBH54Cq2";
        this.puerto = "1521";
    }

    public Configuracion() {
        this.leerArchivo();
    }

    public String getServidor() {
        return this.servidor;
    }

    public String getSid() {
        return this.sid;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPuerto() {
        return this.puerto;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }
}


