/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.inbound;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author luis_
 */
public class CrearSemaforoCustoms {
    
    public static String dataSemaforo(int month, int dias, int anio, String loadType, String Prioridad) {

        String estatusSemaforo = "";
        String res = "";

        /* Identificar cuantos días hábiles tiene cada LoadType */ 
        String diasLoadType = diasTotalDespachoLoadType(loadType);
        
        /* Obtener rango de fechas con días hábiles considereando el Load Type */
        String rangoFechasDiasHabiles = calcularRangoFechasDiasHabiles(month, dias, anio, diasLoadType);
        String[] par = rangoFechasDiasHabiles.split("@");
        String fechaInicial = par[0];
        String fechaFinal = par[1];                                 /* Estatus del Semaforo */

        /* Identificar si el shipmentId tiene prioridad */
        if(Prioridad.equals("No")){
            estatusSemaforo = "1";
        }else{
            estatusSemaforo = "3";
        }
        
        res = fechaInicial+"@"+fechaFinal+"@"+diasLoadType+"@"+estatusSemaforo;
        
        return res;
    }
    
    public static String diasTotalDespachoLoadType(String LoadType) {
        
        String díasH = ""; 

        if (LoadType.equals("FCL")) {
            díasH = "8";
        } else if (LoadType.equals("FCL/LCL")) {
            díasH = "10";
        } else if (LoadType.equals("LCL")) {
            díasH = "10";
        } else if (LoadType.equals("AIR")) {
            díasH = "6";
        } else if (LoadType.equals("LTL")) {
            díasH = "11";
        }
        
        return díasH;
    }
    
    public static String calcularRangoFechasDiasHabiles(int month, int dias, int anio, String diasHabilesLoadType) {
        
        int diasHabilesAgregar = Integer.parseInt(diasHabilesLoadType);
        
        LocalDate fechaInicial = LocalDate.of(anio, month, dias);
        LocalDate fechaFinal = aumentarDiasHabiles(fechaInicial, diasHabilesAgregar);

        String daysOn = fechaInicial+"@"+fechaFinal;
        
        return daysOn;
    }
    
    public static LocalDate aumentarDiasHabiles(LocalDate fechaInicial, int diasHabilesAgregar) {
        LocalDate fechaActual = fechaInicial;
        int diasAgregados = 0;

        while (diasAgregados < diasHabilesAgregar) {
            fechaActual = fechaActual.plusDays(1);

            if (esDiaHabil(fechaActual)) {
                diasAgregados++;
            }
        }

        return fechaActual;
    }

    public static boolean esDiaHabil(LocalDate fecha) {
        // Verificar si es fin de semana (sábado o domingo)
        if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return false;
        }

        // Aquí puedes agregar más condiciones para verificar si la fecha es un día inhábil
        return true;
    }
 
}
