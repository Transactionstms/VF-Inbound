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
public class ColumnaBean implements Serializable{
    private String nombreColumna = null;
    private String tipo = null;
    private String longitud = null;
    private String nullable = null;
    private String columnId = null;
    private String nombreTabla = null;
    private String alias = null;
    private String layoutMsg = null;
    private String ignore = null;

    /**
     * @return the nombreColumna
     */
    public String getNombreColumna() {
        return nombreColumna;
    }

    /**
     * @param nombreColumna the nombreColumna to set
     */
    public void setNombreColumna(String nombreColumna) {
        this.nombreColumna = nombreColumna;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the longitud
     */
    public String getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the nullable
     */
    public String getNullable() {
        return nullable;
    }

    /**
     * @param nullable the nullable to set
     */
    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    /**
     * @return the columnId
     */
    public String getColumnId() {
        return columnId;
    }

    /**
     * @param columnId the columnId to set
     */
    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    /**
     * @return the nombreTabla
     */
    public String getNombreTabla() {
        return nombreTabla;
    }

    /**
     * @param nombreTabla the nombreTabla to set
     */
    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @return the layoutMsg
     */
    public String getLayoutMsg() {
        return layoutMsg;
    }

    /**
     * @param layoutMsg the layoutMsg to set
     */
    public void setLayoutMsg(String layoutMsg) {
        this.layoutMsg = layoutMsg;
    }

    /**
     * @return the ignore
     */
    public String getIgnore() {
        return ignore;
    }

    /**
     * @param ignore the ignore to set
     */
    public void setIgnore(String ignore) {
        this.ignore = ignore;
    }

    @Override
    public String toString() {
        String  ts = "nombreColumna-"+nombreColumna+":Tipo-"+ tipo+":longitud-"+longitud+":nullable-"+nullable
                +":columnId-"+columnId+":nombreTabla-"+nombreTabla+":alias-"+alias+":layoutMsg-"+layoutMsg+":ignore-"+ignore;
        return super.toString();
    }    
}