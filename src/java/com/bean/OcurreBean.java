/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

/**
 *
 * @author sergiocastros
 */
public class OcurreBean {

    private String SUCURSAL;
    private String DESTINO_OCURRE_ID;
    private String CALLE;
    private String COLONIA;
    private String CIUDAD;
    private String ESTADO;
    private String CODIGO_POSTAL;

    public String getDESTINO_OCURRE_ID() {
        return DESTINO_OCURRE_ID;
    }

    public void setDESTINO_OCURRE_ID(String DESTINO_OCURRE_ID) {
        this.DESTINO_OCURRE_ID = DESTINO_OCURRE_ID;
    }

    public String getSUCURSAL() {
        return SUCURSAL;
    }

    public void setSUCURSAL(String SUCURSAL) {
        this.SUCURSAL = SUCURSAL;
    }

    public String getCALLE() {
        return CALLE;
    }

    public void setCALLE(String CALLE) {
        this.CALLE = CALLE;
    }

    public String getCOLONIA() {
        return COLONIA;
    }

    public void setCOLONIA(String COLONIA) {
        this.COLONIA = COLONIA;
    }

    public String getCIUDAD() {
        return CIUDAD;
    }

    public void setCIUDAD(String CIUDAD) {
        this.CIUDAD = CIUDAD;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getCODIGO_POSTAL() {
        return CODIGO_POSTAL;
    }

    public void setCODIGO_POSTAL(String CODIGO_POSTAL) {
        this.CODIGO_POSTAL = CODIGO_POSTAL;
    }

}
