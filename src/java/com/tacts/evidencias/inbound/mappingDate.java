/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacts.evidencias.inbound;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User_Windows10
 */
public class mappingDate {

    public String mappingDate(String namecolumna, int contador, String fechaInicial) {

        String fechaFinal = "";
        
        System.out.println("fechaInicial: " + fechaInicial);

        if (!fechaInicial.equals("")) {

            // Mapa para reemplazar meses en inglés por español
            Map<String, String> mapaMeses = new HashMap<>();
            mapaMeses.put("Jan", "Ene");
            mapaMeses.put("Feb", "Feb");
            mapaMeses.put("Mar", "Mar");
            mapaMeses.put("Apr", "Abr");
            mapaMeses.put("May", "May");
            mapaMeses.put("Jun", "Jun");
            mapaMeses.put("Jul", "Jul");
            mapaMeses.put("Aug", "Ago");
            mapaMeses.put("Sep", "Sep");
            mapaMeses.put("Oct", "Oct");
            mapaMeses.put("Nov", "Nov");
            mapaMeses.put("Dec", "Dic");
            mapaMeses.put("Ene", "Ene");
            mapaMeses.put("Ago", "Ago");
            mapaMeses.put("Dic", "Dic");		

            // Separar la fecha en partes
            String[] partesFecha = fechaInicial.split("/");
            String mesEnIngles = partesFecha[0];

            // Reemplazar el mes en inglés por español
            String mesEnEspanol = mapaMeses.get(mesEnIngles);

            // Reconstruir la fecha con el mes en español
            fechaFinal = mesEnEspanol + "/" + partesFecha[1] + "/" + partesFecha[2];

        } else {
            fechaFinal = "";
        }

        System.out.println("fila: " + contador + " | namecolumna: " + namecolumna + " | valor: " + fechaFinal);

        // Imprimir la fecha con el mes en español
        return fechaFinal;
    }

}
