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
public class RegistroServicioBean implements Serializable {

    private String tipoDePagoRfc = null;
    private String tipoServicioPorCobrar = null;
    private String idOperador = null;
    private String giroEmpresa = null;
    private String tipoMercancia = null;
    private String piezasRecolectar = null;
    private String medidadConversion = null;
    private String acuseCliente = null;
    private String acuseIvoy = null;

    /**
     * @return the tipoDePagoRfc
     */
    public String getTipoDePagoRfc() {
        return tipoDePagoRfc;
    }

    /**
     * @param tipoDePagoRfc the tipoDePagoRfc to set
     */
    public void setTipoDePagoRfc(String tipoDePagoRfc) {
        this.tipoDePagoRfc = tipoDePagoRfc;
    }

    /**
     * @return the tipoServicioPorCobrar
     */
    public String getTipoServicioPorCobrar() {
        return tipoServicioPorCobrar;
    }

    /**
     * @param tipoServicioPorCobrar the tipoServicioPorCobrar to set
     */
    public void setTipoServicioPorCobrar(String tipoServicioPorCobrar) {
        this.tipoServicioPorCobrar = tipoServicioPorCobrar;
    }

    /**
     * @return the idOperador
     */
    public String getIdOperador() {
        return idOperador;
    }

    /**
     * @param idOperador the idOperador to set
     */
    public void setIdOperador(String idOperador) {
        this.idOperador = idOperador;
    }

    /**
     * @return the giroEmpresa
     */
    public String getGiroEmpresa() {
        return giroEmpresa;
    }

    /**
     * @param giroEmpresa the giroEmpresa to set
     */
    public void setGiroEmpresa(String giroEmpresa) {
        this.giroEmpresa = giroEmpresa;
    }

    /**
     * @return the tipoMercancia
     */
    public String getTipoMercancia() {
        return tipoMercancia;
    }

    /**
     * @param tipoMercancia the tipoMercancia to set
     */
    public void setTipoMercancia(String tipoMercancia) {
        this.tipoMercancia = tipoMercancia;
    }

    /**
     * @return the piezasRecolectar
     */
    public String getPiezasRecolectar() {
        return piezasRecolectar;
    }

    /**
     * @param piezasRecolectar the piezasRecolectar to set
     */
    public void setPiezasRecolectar(String piezasRecolectar) {
        this.piezasRecolectar = piezasRecolectar;
    }

    /**
     * @return the medidadConversion
     */
    public String getMedidadConversion() {
        return medidadConversion;
    }

    /**
     * @param medidadConversion the medidadConversion to set
     */
    public void setMedidadConversion(String medidadConversion) {
        this.medidadConversion = medidadConversion;
    }

    /**
     * @return the acuseCliente
     */
    public String getAcuseCliente() {
        return acuseCliente;
    }

    /**
     * @param acuseCliente the acuseCliente to set
     */
    public void setAcuseCliente(String acuseCliente) {
        this.acuseCliente = acuseCliente;
    }

    /**
     * @return the acuseIvoy
     */
    public String getAcuseIvoy() {
        return acuseIvoy;
    }

    /**
     * @param acuseIvoy the acuseIvoy to set
     */
    public void setAcuseIvoy(String acuseIvoy) {
        this.acuseIvoy = acuseIvoy;
    }
}
