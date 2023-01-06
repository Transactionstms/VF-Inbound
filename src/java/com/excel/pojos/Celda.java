/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excel.pojos;

/**
 *
 * @author Omar
 */
public class Celda {

    public Celda(String nombre, String descripcion, int idTipoDato, String tipoDato, int requerido) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idTipoDato = idTipoDato;
        this.tipoDato = tipoDato;
        this.requerido = requerido;
    }
    
    
    private String nombre;
    private String descripcion;
    private int idTipoDato;
    private String tipoDato;
    private String valor;
    private int requerido;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return the idTipoDato
     */
    public int getIdTipoDato() {
        return idTipoDato;
    }

    /**
     * @return the tipoDato
     */
    public String getTipoDato() {
        return tipoDato;
    }

    /**
     * @return the requerido
     */
    public int getRequerido() {
        return requerido;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @param idTipoDato the idTipoDato to set
     */
    public void setIdTipoDato(int idTipoDato) {
        this.idTipoDato = idTipoDato;
    }

    /**
     * @param tipoDato the tipoDato to set
     */
    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    /**
     * @param requerido the requerido to set
     */
    public void setRequerido(int requerido) {
        this.requerido = requerido;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
}
