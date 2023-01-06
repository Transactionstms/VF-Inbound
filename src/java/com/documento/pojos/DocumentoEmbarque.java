/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documento.pojos;

import java.util.List;

/**
 *
 * @author Omar
 */
public class DocumentoEmbarque {
    private String embarque;
    private String nombreArchivo;
    private String logo;
    private Encabezado encabezado;
    private PiePagina piePagina;
    private List<DetalleDocumento> detalleDocumento;
    
    

    /**
     * @return the embarque
     */
    public String getEmbarque() {
        return embarque;
    }

    /**
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param embarque the embarque to set
     */
    public void setEmbarque(String embarque) {
        this.embarque = embarque;
    }

    /**
     * @param nombreArchivo the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @return the logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * @return the encabezado
     */
    public Encabezado getEncabezado() {
        return encabezado;
    }

    /**
     * @return the piePagina
     */
    public PiePagina getPiePagina() {
        return piePagina;
    }

    /**
     * @return the detalleDocumento
     */
    public List<DetalleDocumento> getDetalleDocumento() {
        return detalleDocumento;
    }

    /**
     * @param logo the logo to set
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * @param encabezado the encabezado to set
     */
    public void setEncabezado(Encabezado encabezado) {
        this.encabezado = encabezado;
    }

    /**
     * @param piePagina the piePagina to set
     */
    public void setPiePagina(PiePagina piePagina) {
        this.piePagina = piePagina;
    }

    /**
     * @param detalleDocumento the detalleDocumento to set
     */
    public void setDetalleDocumento(List<DetalleDocumento> detalleDocumento) {
        this.detalleDocumento = detalleDocumento;
    }
}
