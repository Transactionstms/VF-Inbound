/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.model;

/**
 *
 * @author grece
 */
public class MensajeModel {

    private String mensaje;
    private String error;
    private String registro;
    private String aviso; 
    private String data;

    public MensajeModel() {
    }

    public MensajeModel(String mensaje, String error, String registro, String aviso, String data) {
        this.mensaje = mensaje;
        this.error = error;
        this.registro = registro;
        this.aviso = aviso;
        this.data = data;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
