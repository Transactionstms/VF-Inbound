/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excel.pojos;

import java.util.List;

/**
 *
 * @author Omar
 */
public class Fila {
    private int id;
    private List<Celda> celdas;

    public Fila(int id, List<Celda> celdas) {
        this.id = id;
        this.celdas = celdas;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the celdas
     */
    public List<Celda> getCeldas() {
        return celdas;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param celdas the celdas to set
     */
    public void setCeldas(List<Celda> celdas) {
        this.celdas = celdas;
    }
    
    
}
