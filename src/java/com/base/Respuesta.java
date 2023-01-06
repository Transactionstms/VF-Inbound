/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base;

/**
 *
 * @author Omar
 */
public class Respuesta {

    private int proceso;
    private String mensaje;
    private String errorSql;

    public Respuesta() {
        this.proceso = 0;
        this.mensaje = "";
        this.errorSql = "";
    }

    public Respuesta(int proceso, String mensaje, String errorSql) {
        this.proceso = proceso;
        this.mensaje = mensaje;
        this.errorSql = errorSql;
    }

    /**
     * @return the proceso
     */
    public int getProceso() {
        return proceso;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @return the errorSql
     */
    public String getErrorSql() {
        return errorSql;
    }

    /**
     * @param proceso the proceso to set
     */
    public void setProceso(int proceso) {
        this.proceso = proceso;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @param errorSql the errorSql to set
     */
    public void setErrorSql(String errorSql) {
        this.errorSql = errorSql;
    }
}
