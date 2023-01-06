/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bb.web.beans;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author miguel
 */
public class ResponseBean implements Serializable {
    private String codigo = null;
    private String mensaje = null;
    private String rawResponse = null;
    private List<String> errores = null;
    private String idControl = null;
    private List objects = null;

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * 
     * @return 
     */
    public List<String> getErrores(){
      return this.errores;
    }

    /**
     * 
     * @param errores 
     */
    public void setErrores(List<String> errores) {
       this.errores = errores; 
    }

    /**
     * @return the idControl
     */
    public String getIdControl() {
        return idControl;
    }

    /**
     * @param idControl the idControl to set
     */
    public void setIdControl(String idControl) {
        this.idControl = idControl;
    }

    /**
     * @return the objects
     */
    public List getObjects() {
        return objects;
    }

    /**
     * @param objects the objects to set
     */
    public void setObjects(List objects) {
        this.objects = objects;
    }

    /**
     * @return the rawResponse
     */
    public String getRawResponse() {
        return rawResponse;
    }

    /**
     * @param rawResponse the rawResponse to set
     */
    public void setRawResponse(String rawResponse) {
        this.rawResponse = rawResponse;
    }
    
}