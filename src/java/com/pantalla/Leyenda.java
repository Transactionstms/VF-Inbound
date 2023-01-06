/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pantalla;

/**
 *
 * @author Omar
 */
public class Leyenda {

    public Leyenda(int identificador, String texto) {
        this.identificador = identificador;
        this.texto = texto;
    }
    
    
    
  private int identificador;
  private String texto;

    /**
     * @return the identificador
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }
  
  
}
