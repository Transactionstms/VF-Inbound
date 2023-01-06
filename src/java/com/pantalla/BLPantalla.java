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
public class BLPantalla {

    public static Pantalla getPantalla(int idPantalla, int idLenguaje) {
        DaoPantalla daoPantalla = new DaoPantalla();
        Pantalla pantalla = null;
        try {
            return daoPantalla.getPantalla(idPantalla, idLenguaje);
        } catch (Exception exception) { 
            exception.printStackTrace();
        }
        return pantalla;
    }
    
    public static String getEtiqueta(Pantalla pantalla,int identificador) {
        String respuesta="";
        if(pantalla!=null){
            respuesta=((((Leyenda)pantalla.getEtiquetas().get(identificador))==null)?"Indefinido":((Leyenda)pantalla.getEtiquetas().get(identificador)).getTexto());            
        }else{
            respuesta = "Pantalla Indefinida";
        }
        return respuesta;
    }

}
