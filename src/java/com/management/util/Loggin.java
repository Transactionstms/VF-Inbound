/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.util;

import org.apache.log4j.Logger;

/**
 *com.management.util.Loggin.debug
 * @author miguel
 */
public class Loggin {
    private static Logger logg = Logger.getLogger(Loggin.class);
    
    public static void info(String message){
        logg.info(message);
    }
    public static void debug(String debugInfo){
        logg.debug(debugInfo);
    }
    public static void error(String debugInfo){
        logg.error(debugInfo);
    }
    public static void error(Throwable t){
        logg.error(t);
    }    
    public static void error(String error,Throwable t){
        logg.error(error, t);
    }        
}
