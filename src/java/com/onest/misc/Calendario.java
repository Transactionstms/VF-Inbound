package com.onest.misc;

import java.util.Calendar;

public class Calendario {

    private static Calendar today;
    private static final String[] months = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
    private static final String[] days = {"", "Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", ""};

    public static String getToday() {
        today = Calendar.getInstance();
        return days[today.get(7)] + " " + today.get(5) + " de " + months[today.get(2)] + ", " + today.get(1) + " " + getHour(true) + ":" + getMinute() + ":" + getSecond();
    }

    public static String getHour() {
        today = Calendar.getInstance();
        return today.get(11) + ":" + today.get(12) + ":" + today.get(13);
    }

    public static String getYear() {
        today = Calendar.getInstance();
        String year = String.valueOf(today.get(1));
        return year;
    }

    public static String getMonth() {
        today = Calendar.getInstance();
        String month = String.valueOf(today.get(2) + 1);
        return month.length() < 2 ? "0" + month : month;
    }

    public static String getMinute() {
        today = Calendar.getInstance();
        String minute = String.valueOf(today.get(12));
        return minute.length() < 2 ? "0" + minute : minute;
    }

    public static String getSecond() {
        today = Calendar.getInstance();
        String second = String.valueOf(today.get(13));
        return second.length() < 2 ? "0" + second : second;
    }

    public static String getHour(boolean day24format) {
        today = Calendar.getInstance();

        if (day24format) {
            String hour = String.valueOf(today.get(11));
            return hour.length() < 2 ? "0" + hour : hour;
        }
        String hour = String.valueOf(today.get(10));
        return hour.length() < 2 ? "0" + hour : hour;
    }

    public static String getDayOfMonth() {
        today = Calendar.getInstance();
        String day = String.valueOf(today.get(5));
        return day.length() < 2 ? "0" + day : day;
    }

    public static String getDateForID() {
        return getYear() + getMonth() + getDayOfMonth() + getHour(true) + getMinute() + getSecond();
    }
    
    public static String getHour69() {
         return getYear() + getDayOfMonth() + getMinute() + getSecond();
    }
}