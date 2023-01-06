/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excel.pojos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Omar
 */
public class Plantilla {

    public Plantilla(String nombre, String stored, List<Celda> celdas) {
        this.nombre = nombre;
        this.stored = stored;
        this.celdas = celdas;
        this.celdas =  new ArrayList<Celda>();
    }
    
     public Plantilla(String nombre, String stored) {
        this.nombre = nombre;
        this.stored = stored;
        
    }
    
    private String nombre;
    private String stored;
    private List<Fila> filas;
    private List<Celda> celdas;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the stored
     */
    public String getStored() {
        return stored;
    }

    /**
     * @return the celdas
     */
    public List<Celda> getCeldas() {
        return celdas;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param stored the stored to set
     */
    public void setStored(String stored) {
        this.stored = stored;
    }

    /**
     * @param celdas the celdas to set
     */
    public void setCeldas(List<Celda> celdas) {
        this.celdas = celdas;
    }
    public void setCeldas(Celda celda) {
        if(this.celdas==null){
            this.celdas =  new ArrayList<Celda>();        
        }
        this.celdas.add(celda);
    }

    /**
     * @return the filas
     */
    public List<Fila> getFilas() {
        return filas;
    }

    /**
     * @param filas the filas to set
     */
    public void setFilas(List<Fila> filas) {
        this.filas = filas;
    }
    
    
    
    
}
