/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.inbound;

import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author luis_
 */
public class CrearSemaforoCustoms {
    
    public static String dataSemaforo(int mes, int dias, int anio, String loadType, int diaContadorActual) {
        
        String fechaInicialConfirmada = Integer.toString(mes)+"/"+Integer.toString(dias)+"/"+Integer.toString(anio);
        String res = "";
        int diasCalculadosCont = 0;

        String diasLoadType = diasTotalDespachoLoadType(loadType);                                                 /* Días Despacho Load Type */
 
        String fechaFinalConfirmada = fechaFinalEstimada(mes, dias, anio, diasLoadType);                           /* Fecha Final Estimada */  

        String diaContadorHabil = actualizarContadordiashabilesRangoFechas(mes, dias, anio, fechaFinalConfirmada, diaContadorActual); /* Actualizar el contador de los días transcurridos */

        diasCalculadosCont = diaContadorActual + Integer.parseInt(diaContadorHabil);                               /* Contador del día ya actualizado */
        
        String estatusSemaforo = colorSemaforo(loadType, diasCalculadosCont);                                      /* Estatus del Semaforo */

        res = diasCalculadosCont+"/"+estatusSemaforo+"/"+fechaInicialConfirmada+"/"+fechaFinalConfirmada+"/"+diasLoadType;
        
        return res;
    }
    
    public static String diasTotalDespachoLoadType(String LoadType) {
        /* Identificar cuantos días habiles tiene cada LoadType */ 

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
    
    public static String fechaFinalEstimada(int mes, int dias, int anio, String diasLoadType) {
        /* Obtener una fecha final estimada */
        
        String fechaFinal= "";
        int díaCalculado = 0;

        if(dias==31){
            díaCalculado = 1 + Integer.parseInt(diasLoadType);
        }else{
            díaCalculado = dias + Integer.parseInt(diasLoadType);
        }
        
        fechaFinal = Integer.toString(mes)+"/"+Integer.toString(díaCalculado)+"/"+Integer.toString(anio);
        
        return fechaFinal;
    }
    
    public static String actualizarContadordiashabilesRangoFechas(int mes, int dias, int anio, String fechaFinalEstimada, int diaContadorActual) {
        /* Actualizar el contador de días para compararlas con los indicadores de load type */
        
        String resultado = "";
        int contador = 0;
        int diaH = 0;
        int numD;
        
        Calendar c = Calendar.getInstance(Locale.US);
        c.set(anio, mes - 1, dias);
        numD = c.get(Calendar.DAY_OF_WEEK);
        
        String fechaInicial = String.valueOf(mes)+"/"+String.valueOf(dias)+"/"+String.valueOf(anio);
        
        if(!fechaInicial.equals(fechaFinalEstimada)){
            
            if (numD == Calendar.SUNDAY) {
                diaH = 0;
            } else if (numD == Calendar.MONDAY) {
                diaH = 1;
            } else if (numD == Calendar.TUESDAY) {
                diaH = 1;
            } else if (numD == Calendar.WEDNESDAY) {
                diaH = 1;
            } else if (numD == Calendar.THURSDAY) {
                diaH = 1;
            } else if (numD == Calendar.FRIDAY) {
                diaH = 1;
            } else if (numD == Calendar.SATURDAY) {
                diaH = 1;
            }
            
            contador = diaH + diaContadorActual;
            
            resultado = String.valueOf(contador);
        }
        
        return resultado;
    }
    
    public static String colorSemaforo(String LoadType, int diaContador) {
        /* Identificar el color del Semaforo, mediante el día/contador actual */
        
        String semaforo = "";

        if (LoadType.equals("FCL")) {

            if (diaContador >= 1 && diaContador <= 5) {
                semaforo = "Verde";
            } else if (diaContador >= 6 && diaContador <= 7) {
                semaforo = "Amarillo";
            } else if (diaContador == 8) {
                semaforo = "Rojo";
            }

        } else if (LoadType.equals("FCL/LCL")) {

            if (diaContador >= 1 && diaContador <= 7) {
                semaforo = "Verde";
            } else if (diaContador >= 8 && diaContador <= 9) {
                semaforo = "Amarillo";
            } else if (diaContador == 10) {
                semaforo = "Rojo";
            }

        } else if (LoadType.equals("LCL")) {

            if (diaContador >= 1 && diaContador <= 7) {
                semaforo = "Verde";
            } else if (diaContador >= 8 && diaContador <= 9) {
                semaforo = "Amarillo";
            } else if (diaContador == 10) {
                semaforo = "Rojo";
            }

        } else if (LoadType.equals("AIR")) {

            if (diaContador >= 1 && diaContador <= 3) {
                semaforo = "Verde";
            } else if (diaContador >= 4 && diaContador <= 5) {
                semaforo = "Amarillo";
            } else if (diaContador == 6) {
                semaforo = "Rojo";
            }

        } else if (LoadType.equals("LTL")) {

            if (diaContador >= 1 && diaContador <= 8) {
                semaforo = "Verde";
            } else if (diaContador >= 9 && diaContador <= 10) {
                semaforo = "Amarillo";
            } else if (diaContador == 11) {
                semaforo = "Rojo";
            }

        }
        
        return semaforo;
    }
      
}
