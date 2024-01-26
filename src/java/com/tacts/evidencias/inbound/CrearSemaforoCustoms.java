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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 *
 * @author luis_
 */
public class CrearSemaforoCustoms {
    
    public static String dataSemaforo(int month, int dias, int anio, String loadType, String Prioridad) {

        /* Obtener fecha actual */
        Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dateActual = formato.format(date);
        String[] row = dateActual.split("/");
    
        int diasHabiles = 0;
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
        
            if(typeTime.equals(">")){
                estatusSemaforo = "0"; //prioridad nula
                   
            }else if(typeTime.equals("=")){
                estatusSemaforo = "1"; //prioridad baja
            
            }else if(typeTime.equals("<")){
               
                /*Obtener el día actual*/
                int dayActual = Integer.parseInt(row[0]);
                int monthActual = Integer.parseInt(row[1]);
                int yearActual = Integer.parseInt(row[2]);

                /*Obtener los días habiles del rango de fechas*/
                LocalDate fechaEtaPortDischarge = LocalDate.of(anio, month, dias);                      
                LocalDate fechaActual = LocalDate.of(yearActual, monthActual, dayActual);   
                diasHabiles = contarDiasHabilesTranscurridos(fechaEtaPortDischarge, fechaActual);
              
                /*Asignar estatus del semaforo conforme al rango dias limite (consulta)*/
                if(diasHabiles < Integer.parseInt(diasLimitePrioridadMedia)){
                    estatusSemaforo = "1"; //prioridad baja
                }else if(diasHabiles > Integer.parseInt(diasLimitePrioridadBaja) && diasHabiles < Integer.parseInt(diasLimitePrioridadAlta)){
                    estatusSemaforo = "2"; //prioridad media
                }else if(diasHabiles > Integer.parseInt(diasLimitePrioridadMedia)){
                    estatusSemaforo = "3"; //prioridad alta
                }
                
            }
        
        res = fechaInicial+"@"+fechaFinal+"@"+diasHabilesLoadType+"@"+estatusSemaforo+"@"+diasLimitePrioridadBaja+"@"+diasLimitePrioridadMedia+"@"+diasLimitePrioridadAlta;
        
        return res;
    }
    
    public static String diasTotalDespachoLoadType(String loadtype) {
        
        String days = "";
        String daysHabilits = ""; 
        String dayLimitGreen = "";
        String dayLimitYellow = "";
        String dayLimitRed = "";
        String LoadType = loadtype.replaceAll("\\s","");

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

            //Obtener fines de semana.
            if (fechaActual.getDayOfWeek() == DayOfWeek.SATURDAY || fechaActual.getDayOfWeek() == DayOfWeek.SUNDAY) {
              diasHabilesAgregar++;
              
            }else{
                
                //Obtener dias festivos.
                if (esFestivo(fechaActual)) {
                    diasHabilesAgregar++;
                }else{
                    System.out.println("Día Normal");
                }
                
            }
            
            diasAgregados++;
            System.out.println("---------------------------- ");
            System.out.println("Fecha Iterada: " + fechaActual); 
            System.out.println("Dias a Iterar: " + diasHabilesAgregar);
            System.out.println("Dias Iterados: " + diasAgregados);
            System.out.println("---------------------------- ");
            
        }
        
        return fechaActual;
    }

   public static boolean esFestivo(LocalDate startDate) {
        
        int dayOfYear = startDate.getYear();
        int monthYear = startDate.getMonthValue();  
        int dayYear = startDate.getDayOfMonth();
        
        List<LocalDate> holidays = new ArrayList<>();

        // Agregar tus días festivos a la lista
        holidays.add(LocalDate.of(dayOfYear,1,1));   //Año Nuevo
        holidays.add(LocalDate.of(dayOfYear,2,3));   //Const. Mexicana
        holidays.add(LocalDate.of(dayOfYear,2,6));   //Const. Mexicana
        holidays.add(LocalDate.of(dayOfYear,3,16));  //Na. Benito Juarez
        holidays.add(LocalDate.of(dayOfYear,3,20));  //Na. Benito Juarez
        holidays.add(LocalDate.of(dayOfYear,4,6));   //Jueves Santo
        holidays.add(LocalDate.of(dayOfYear,4,7));   //Viernes Santo
        holidays.add(LocalDate.of(dayOfYear,5,1));   //Día del trabajador
        holidays.add(LocalDate.of(dayOfYear,9,16));  //Semana santa (1/2)
        holidays.add(LocalDate.of(dayOfYear,11,2));  //Día de los muertos
        holidays.add(LocalDate.of(dayOfYear,11,20)); //Revolución Mexicana
        holidays.add(LocalDate.of(dayOfYear,12,25)); //Navidad
        
        // Fecha que deseas verificar
        LocalDate fecha = LocalDate.of(dayOfYear, monthYear, dayYear);
       
        // Verificar si la fecha es un día festivo
         if (holidays.contains(fecha)) {
            return true;
         }
         
        return false;
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
    
    public static int contarDiasHabilesTranscurridos(LocalDate fechaInicio, LocalDate fechaFin) {
        
        int dayOfYear = fechaInicio.getYear();
        int monthYear = fechaInicio.getMonthValue();  
        int dayYear = fechaInicio.getDayOfMonth();
        int diasHabiles = 0;

        // Agregar tus días festivos a la lista
        Set<LocalDate> diasFestivos = new HashSet<>();
        
        diasFestivos.add(LocalDate.of(dayOfYear,1,1));   //Año Nuevo
        diasFestivos.add(LocalDate.of(dayOfYear,2,3));   //Const. Mexicana
        diasFestivos.add(LocalDate.of(dayOfYear,2,6));   //Const. Mexicana
        diasFestivos.add(LocalDate.of(dayOfYear,3,16));  //Na. Benito Juarez
        diasFestivos.add(LocalDate.of(dayOfYear,3,20));  //Na. Benito Juarez
        diasFestivos.add(LocalDate.of(dayOfYear,4,6));   //Jueves Santo
        diasFestivos.add(LocalDate.of(dayOfYear,4,7));   //Viernes Santo
        diasFestivos.add(LocalDate.of(dayOfYear,5,1));   //Día del trabajador
        diasFestivos.add(LocalDate.of(dayOfYear,9,16));  //Semana santa (1/2)
        diasFestivos.add(LocalDate.of(dayOfYear,11,2));  //Día de los muertos
        diasFestivos.add(LocalDate.of(dayOfYear,11,20)); //Revolución Mexicana
        diasFestivos.add(LocalDate.of(dayOfYear,12,25)); //Navidad
        // Puedes agregar días festivos a la lista
        // Ejemplo: diasFestivos.add(LocalDate.of(2023, 1, 1));

        LocalDate fechaActual = fechaInicio;

        while (!fechaActual.isAfter(fechaFin)) {
            if (esDiaHabil(fechaActual) && !diasFestivos.contains(fechaActual.getDayOfMonth())) {
                diasHabiles++;
            }
            fechaActual = fechaActual.plus(1, ChronoUnit.DAYS);
        }
        //if (esFestivo(fechaActual)) {
        return diasHabiles;
    }

    public static boolean esDiaHabil(LocalDate fecha) {
        DayOfWeek diaSemana = fecha.getDayOfWeek();
        return diaSemana != DayOfWeek.SATURDAY && diaSemana != DayOfWeek.SUNDAY;
    }

   
}
