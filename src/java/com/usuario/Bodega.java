/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuario;

/**
 *
 * @author Omar
 */
public class Bodega {

    public Bodega(int bodegaId, int cuentaId, String cuentaNombre, int cbdivID, String bodegaNombre) {
        this.bodegaId = bodegaId;
        this.cuentaId = cuentaId;
        this.cuentaNombre = cuentaNombre;
        this.cbdivID = cbdivID;
        this.bodegaNombre = bodegaNombre;
    }
    
    
    private int bodegaId;
    private int cuentaId;
    private String cuentaNombre;
    private int cbdivID;
    private String bodegaNombre;

    /**
     * @return the bodegaId
     */
    public int getBodegaId() {
        return bodegaId;
    }

    /**
     * @param bodegaId the bodegaId to set
     */
    public void setBodegaId(int bodegaId) {
        this.bodegaId = bodegaId;
    }

    /**
     * @return the cuentaId
     */
    public int getCuentaId() {
        return cuentaId;
    }

    /**
     * @param cuentaId the cuentaId to set
     */
    public void setCuentaId(int cuentaId) {
        this.cuentaId = cuentaId;
    }

    /**
     * @return the cuentaNombre
     */
    public String getCuentaNombre() {
        return cuentaNombre;
    }

    /**
     * @param cuentaNombre the cuentaNombre to set
     */
    public void setCuentaNombre(String cuentaNombre) {
        this.cuentaNombre = cuentaNombre;
    }

    /**
     * @return the cbdivID
     */
    public int getCbdivID() {
        return cbdivID;
    }

    /**
     * @param cbdivID the cbdivID to set
     */
    public void setCbdivID(int cbdivID) {
        this.cbdivID = cbdivID;
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
    
    
}
