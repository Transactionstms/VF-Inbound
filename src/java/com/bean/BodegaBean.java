/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import java.io.Serializable;

/**
 *
 * @author miguel
 */
public class BodegaBean implements Serializable {
    private String bodegaId = null;
    private String bodegaNombre = null;
    private String bodegaDireccion = null;
    private String bodegaCpOrigen = null;
    private String cbdivId = null;

    /**
     * @return the bodegaId
     */
    public String getBodegaId() {
        return bodegaId;
    }

    /**
     * @param bodegaId the bodegaId to set
     */
    public void setBodegaId(String bodegaId) {
        this.bodegaId = bodegaId;
    }

    /**
     * @return the bodegaNombre
     */
    public String getBodegaNombre() {
        return bodegaNombre;
    }

    /**
     * @param bodegaNombre the bodegaNombre to set
     */
    public void setBodegaNombre(String bodegaNombre) {
        this.bodegaNombre = bodegaNombre;
    }

    /**
     * @return the bodegaDireccion
     */
    public String getBodegaDireccion() {
        return bodegaDireccion;
    }

    /**
     * @param bodegaDireccion the bodegaDireccion to set
     */
    public void setBodegaDireccion(String bodegaDireccion) {
        this.bodegaDireccion = bodegaDireccion;
    }

    /**
     * @return the bodegaCpOrigen
     */
    public String getBodegaCpOrigen() {
        return bodegaCpOrigen;
    }

    /**
     * @param bodegaCpOrigen the bodegaCpOrigen to set
     */
    public void setBodegaCpOrigen(String bodegaCpOrigen) {
        this.bodegaCpOrigen = bodegaCpOrigen;
    }

    /**
     * @return the cbdivId
     */
    public String getCbdivId() {
        return cbdivId;
    }

    /**
     * @param cbdivId the cbdivId to set
     */
    public void setCbdivId(String cbdivId) {
        this.cbdivId = cbdivId;
    }
    
}
