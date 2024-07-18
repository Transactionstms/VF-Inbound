/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.plantillas;

/**
 *
 * @author grecendiz
 */
public class formatearCadena {

    public formatearCadena() {
    }
    
     public static String formatearCadena(String cadena) {
        // Dividir la cadena en números
        String[] numeros = cadena.split("\\s+");

        // Crear una nueva cadena con números entre comillas simples y separados por comas
        StringBuilder resultado = new StringBuilder();
        for (String numero : numeros) {
            resultado.append('\'').append(numero).append('\'').append(',');
        }

        // Eliminar la coma adicional al final
        if (resultado.length() > 0) {
            resultado.deleteCharAt(resultado.length() - 1);
        }

        return resultado.toString();
    }
    
}
