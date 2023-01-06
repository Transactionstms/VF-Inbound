/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.facturacion;

/**
 *
 * @author Desarrollo Tacts
 */

public class ActivityCourse {
         // Nombre del archivo original
    private String fileName;
         // La ruta de red externa del archivo
    private String filePath;
 
    public ActivityCourse () {}
    public ActivityCourse (String fileName , String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
