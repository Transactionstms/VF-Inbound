/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.inbound;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        String[] par = diasLoadType.split("/");
        String diasHabilesLoadType = par[0];         /*Días Hábiles*/
        String diasLimitePrioridadBaja = par[1];     /*Días Limite Semaforo (Verde)*/    
        String diasLimitePrioridadMedia = par[2];    /*Días Limite Semaforo (Amarillo)*/  
        String diasLimitePrioridadAlta = par[3];     /*Días Limite Semaforo (Rojo)*/  
        
        /* Obtener rango de fechas con días hábiles considereando el Load Type */
        String rangoFechasDiasHabiles = calcularRangoFechasDiasHabiles(month, dias, anio, diasHabilesLoadType);
        String[] par1 = rangoFechasDiasHabiles.split("@");
        String fechaInicial = par1[0]; 
        String fechaFinal = par1[1];                 /* Estatus del Semaforo */
        
        /*Identificar si el fecha a validar es <,>, = a la fecha actual */
        String typeTime = CalcularFechaAnteriorActual(fechaInicial);
        
        if(typeTime.equals("=")||typeTime.equals(">")){
            
            /* Identificar si el shipmentId tiene prioridad */
            if(Prioridad.equals("No") || Prioridad.equals("")){
                estatusSemaforo = "1";  //prioridad baja
            }else {
                estatusSemaforo = "2";  //prioridad media
            }
            
        }else if(typeTime.equals("<")){
            
            estatusSemaforo = "3"; //prioridad
            
        }
        
        res = fechaInicial+"@"+fechaFinal+"@"+diasHabilesLoadType+"@"+estatusSemaforo+"@"+diasLimitePrioridadBaja+"@"+diasLimitePrioridadMedia+"@"+diasLimitePrioridadAlta;
        
        return res;
    }
    
    public static String diasTotalDespachoLoadType(String LoadType) {
        
        String days = "";
        String daysHabilits = ""; 
        String dayLimitGreen = "";
        String dayLimitYellow = "";
        String dayLimitRed = "";

        if (LoadType.equals("FCL")) {
            daysHabilits = "8";
            dayLimitGreen = "5";
            dayLimitYellow = "7";
            dayLimitRed = "8";
        } else if (LoadType.equals("FCL/LCL")) {
            daysHabilits = "10";
            dayLimitGreen = "7";
            dayLimitYellow = "9";
            dayLimitRed = "10";
        } else if (LoadType.equals("LCL")) {
            daysHabilits = "10";
            dayLimitGreen = "7";
            dayLimitYellow = "9";
            dayLimitRed = "10";
        } else if (LoadType.equals("AIR")) {
            daysHabilits = "6";
            dayLimitGreen = "3";
            dayLimitYellow = "5";
            dayLimitRed = "6";
        } else if (LoadType.equals("LTL")) {
            daysHabilits = "11";
            dayLimitGreen = "8";
            dayLimitYellow = "10";
            dayLimitRed = "11";
        }
        
        days = daysHabilits+"/"+dayLimitGreen+"/"+dayLimitYellow+"/"+dayLimitRed;
        
        return days;
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

    public static String CalcularFechaAnteriorActual(String dateValidate) {
        
        String dateTime = "";
        
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        
        try {
            LocalDate fechaVerificar = LocalDate.parse(dateValidate);

        // Comparar las fechas
        if (fechaVerificar.isBefore(fechaActual)) {
            dateTime = "<";
        }else if (fechaVerificar.equals(fechaActual)) {
            dateTime = "=";
        }else {
            dateTime = ">";
        }
           
        } catch (DateTimeParseException e) {
            System.err.println("Error al analizar la fecha: " + e.getMessage());
            // Puedes manejar el error de formato de fecha aquí
        }
 
        return dateTime;
        
    }
    
    

 
}
