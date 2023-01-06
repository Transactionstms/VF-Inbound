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
public class PiePagina {

    public PiePagina(String nombreFirma, String puesto, String fechaSalida, String lineaTransporte, String descripcionUnidad, int custodia, String observaciones, String autorizaSalidaNombre, String recibi, String leyendaLinea1, String leyendaLinea2, String leyendaLinea3) {
        this.nombreFirma = nombreFirma;
        this.puesto = puesto;
        this.fechaSalida = fechaSalida;
        this.lineaTransporte = lineaTransporte;
        this.descripcionUnidad = descripcionUnidad;
        this.custodia = custodia;
        this.observaciones = observaciones;
        this.autorizaSalidaNombre = autorizaSalidaNombre;
        this.recibi = recibi;
        this.leyendaLinea1 = leyendaLinea1;
        this.leyendaLinea2 = leyendaLinea2;
        this.leyendaLinea3 = leyendaLinea3;
    }
    
    
    
    private String nombreFirma;
    private String puesto;
    private String fechaSalida;
    private String lineaTransporte;
    private String descripcionUnidad;
    private int custodia;
    private String observaciones;
    private String autorizaSalidaNombre;
    private String recibi;
    private String leyendaLinea1;
    private String leyendaLinea2;
    private String leyendaLinea3;

    /**
     * @return the nombreFirma
     */
    public String getNombreFirma() {
        return nombreFirma;
    }

    /**
     * @return the puesto
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * @return the fechaSalida
     */
    public String getFechaSalida() {
        return fechaSalida;
    }

    /**
     * @return the lineaTransporte
     */
    public String getLineaTransporte() {
        return lineaTransporte;
    }

    /**
     * @return the descripcionUnidad
     */
    public String getDescripcionUnidad() {
        return descripcionUnidad;
    }

    /**
     * @return the custodia
     */
    public int getCustodia() {
        return custodia;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @return the autorizaSalidaNombre
     */
    public String getAutorizaSalidaNombre() {
        return autorizaSalidaNombre;
    }

    /**
     * @return the recibi
     */
    public String getRecibi() {
        return recibi;
    }

    /**
     * @return the leyendaLinea1
     */
    public String getLeyendaLinea1() {
        return leyendaLinea1;
    }

    /**
     * @return the leyendaLinea2
     */
    public String getLeyendaLinea2() {
        return leyendaLinea2;
    }

    /**
     * @return the leyendaLinea3
     */
    public String getLeyendaLinea3() {
        return leyendaLinea3;
    }

    /**
     * @param nombreFirma the nombreFirma to set
     */
    public void setNombreFirma(String nombreFirma) {
        this.nombreFirma = nombreFirma;
    }

    /**
     * @param puesto the puesto to set
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * @param fechaSalida the fechaSalida to set
     */
    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * @param lineaTransporte the lineaTransporte to set
     */
    public void setLineaTransporte(String lineaTransporte) {
        this.lineaTransporte = lineaTransporte;
    }

    /**
     * @param descripcionUnidad the descripcionUnidad to set
     */
    public void setDescripcionUnidad(String descripcionUnidad) {
        this.descripcionUnidad = descripcionUnidad;
    }

    /**
     * @param custodia the custodia to set
     */
    public void setCustodia(int custodia) {
        this.custodia = custodia;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @param autorizaSalidaNombre the autorizaSalidaNombre to set
     */
    public void setAutorizaSalidaNombre(String autorizaSalidaNombre) {
        this.autorizaSalidaNombre = autorizaSalidaNombre;
    }

    /**
     * @param recibi the recibi to set
     */
    public void setRecibi(String recibi) {
        this.recibi = recibi;
    }

    /**
     * @param leyendaLinea1 the leyendaLinea1 to set
     */
    public void setLeyendaLinea1(String leyendaLinea1) {
        this.leyendaLinea1 = leyendaLinea1;
    }

    /**
     * @param leyendaLinea2 the leyendaLinea2 to set
     */
    public void setLeyendaLinea2(String leyendaLinea2) {
        this.leyendaLinea2 = leyendaLinea2;
    }

    /**
     * @param leyendaLinea3 the leyendaLinea3 to set
     */
    public void setLeyendaLinea3(String leyendaLinea3) {
        this.leyendaLinea3 = leyendaLinea3;
    }
    
}
