/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bb.web.beans;

import java.io.Serializable;

/**
 *
 * @author miguel
 */
public class BodegaPaqueteExpress implements Serializable {
    private String orgnClntId = null;
    private String clntPswd = null;
    private String agreementKey = null;
    private String descripcion = null;
    private String cbDivId = null;

    /**
     * @return the orgnClntId
     */
    public String getOrgnClntId() {
        return orgnClntId;
    }

    /**
     * @param orgnClntId the orgnClntId to set
     */
    public void setOrgnClntId(String orgnClntId) {
        this.orgnClntId = orgnClntId;
    }

    /**
     * @return the clntPswd
     */
    public String getClntPswd() {
        return clntPswd;
    }

    /**
     * @param clntPswd the clntPswd to set
     */
    public void setClntPswd(String clntPswd) {
        this.clntPswd = clntPswd;
    }

    /**
     * @return the agreementKey
     */
    public String getAgreementKey() {
        return agreementKey;
    }

    /**
     * @param agreementKey the agreementKey to set
     */
    public void setAgreementKey(String agreementKey) {
        this.agreementKey = agreementKey;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the cbDivId
     */
    public String getCbDivId() {
        return cbDivId;
    }

    /**
     * @param cbDivId the cbDivId to set
     */
    public void setCbDivId(String cbDivId) {
        this.cbDivId = cbDivId;
    }
    
}
