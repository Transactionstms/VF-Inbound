/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.model;
/**
 *
 * @author Desarrollo Tacts
 */
public class CustomModel{
    
    private String agenteAduanal;

    public CustomModel() {
    }
   
    public CustomModel(String agenteAduanal){
        this.agenteAduanal = agenteAduanal;
    }

    public String getAgenteAduanal() {
        return agenteAduanal;
    }

    public void setAgenteAduanal(String agenteAduanal) {
        this.agenteAduanal = agenteAduanal;
    }
    
    @Override
    public String toString() {
        return agenteAduanal;
    }
    
}
