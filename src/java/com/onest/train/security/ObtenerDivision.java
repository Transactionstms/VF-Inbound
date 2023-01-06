/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.security;

import com.onest.oracle.DB;
/**
 *
 * @author sistemas
 */
public class ObtenerDivision {
 public String division(String cuentas) {
        String cadena = "";
        DB db = new DB(DB.SECURITY, DB.SECURITY);
        String query = "SELECT DIVISION_ID FROM ONTMS_CTA_BOD_DIV WHERE CBDIV_ID in (" + cuentas + ")";
        if (db.doDB(query)) {
            for (String[] row : db.getResultado()) {
                System.out.println(row[0]);
                cadena += row[0] + ",";
            }
        }
        cadena = cadena.substring(0, cadena.length() - 1);
        return cadena;
    }
    
}
