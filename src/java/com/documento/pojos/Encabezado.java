/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documento.pojos;

/**
 *
 * @author Omar
 */
public class Encabezado {

    public Encabezado(String agrupador,String guiaEmbarque, String fecha, String cargador, String empresa, String rfc, String porteador, String lineaTransportista, String direccion, String tipoUnidad, String placas, String modelo, String color, String chofer) {
        this.agrupador = agrupador;
        this.guiaEmbarque = guiaEmbarque;
        this.fecha = fecha;
        this.cargador = cargador;
        this.empresa = empresa;
        this.rfc = rfc;
        this.porteador = porteador;
        this.lineaTransportista = lineaTransportista;
        this.direccion = direccion;
        this.tipoUnidad = tipoUnidad;
        this.placas = placas;
        this.modelo = modelo;
        this.color = color;
        this.chofer = chofer;
    }
    
    private String agrupador;
    private String guiaEmbarque;
    private String fecha;
    private String cargador;
    private String empresa;
    private String rfc;    
    private String porteador;
    private String lineaTransportista;
    private String direccion;
    private String tipoUnidad;
    private String placas;
    private String modelo;
    private String color;
    private String chofer;

    /**
     * @return the guiaEmbarque
     */
    public String getGuiaEmbarque() {
        return guiaEmbarque;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @return the cargador
     */
    public String getCargador() {
        return cargador;
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @return the rfc
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * @return the porteador
     */
    public String getPorteador() {
        return porteador;
    }

    /**
     * @return the lineaTransportista
     */
    public String getLineaTransportista() {
        return lineaTransportista;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @return the tipoUnidad
     */
    public String getTipoUnidad() {
        return tipoUnidad;
    }

    /**
     * @return the placas
     */
    public String getPlacas() {
        return placas;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @return the chofer
     */
    public String getChofer() {
        return chofer;
    }

    /**
     * @param guiaEmbarque the guiaEmbarque to set
     */
    public void setGuiaEmbarque(String guiaEmbarque) {
        this.guiaEmbarque = guiaEmbarque;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @param cargador the cargador to set
     */
    public void setCargador(String cargador) {
        this.cargador = cargador;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * @param rfc the rfc to set
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * @param porteador the porteador to set
     */
    public void setPorteador(String porteador) {
        this.porteador = porteador;
    }

    /**
     * @param lineaTransportista the lineaTransportista to set
     */
    public void setLineaTransportista(String lineaTransportista) {
        this.lineaTransportista = lineaTransportista;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @param tipoUnidad the tipoUnidad to set
     */
    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    /**
     * @param placas the placas to set
     */
    public void setPlacas(String placas) {
        this.placas = placas;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @param chofer the chofer to set
     */
    public void setChofer(String chofer) {
        this.chofer = chofer;
    }

    /**
     * @return the agrupador
     */
    public String getAgrupador() {
        return agrupador;
    }

    /**
     * @param agrupador the agrupador to set
     */
    public void setAgrupador(String agrupador) {
        this.agrupador = agrupador;
    }
    
}
