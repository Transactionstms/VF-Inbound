/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pantalla;

import java.util.HashMap;

/**
 *
 * @author Omar
 */
public class Pantalla {
    private int identificador;
    private String descripcion;
    private String url;

    public Pantalla(int identificador, String descripcion, String url) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.url = url;
    }

    public Pantalla(int identificador, String descripcion, String url, HashMap<Integer, Leyenda> etiquetas) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.url = url;
        this.etiquetas = etiquetas;
    }
    
    
    private HashMap<Integer,Leyenda> etiquetas;

    /**
     * @return the identificador
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the etiquetas
     */
    public HashMap<Integer,Leyenda> getEtiquetas() {
        return etiquetas;
    }

    /**
     * @param etiquetas the etiquetas to set
     */
    public void setEtiquetas(HashMap<Integer,Leyenda> etiquetas) {
        this.etiquetas = etiquetas;
    }
    
    
}
