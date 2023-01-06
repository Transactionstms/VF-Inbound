/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.menu;

/**
 *
 * @author Omar
 */
public class Lenguaje {
    private int idLenguaje;
    private String descripcion;
    private String icono;

    public Lenguaje(int idLenguaje, String descripcion, String icono) {
        this.idLenguaje = idLenguaje;
        this.descripcion = descripcion;
        this.icono = icono;
    }
    
    

    /**
     * @return the idLenguaje
     */
    public int getIdLenguaje() {
        return idLenguaje;
    }

    /**
     * @param idLenguaje the idLenguaje to set
     */
    public void setIdLenguaje(int idLenguaje) {
        this.idLenguaje = idLenguaje;
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
     * @return the icono
     */
    public String getIcono() {
        return icono;
    }

    /**
     * @param icono the icono to set
     */
    public void setIcono(String icono) {
        this.icono = icono;
    }
}
