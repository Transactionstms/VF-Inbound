/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Desarrollo Tacts
 */
public class actualCRDModel {
    
    private String actualCr;
    
    public actualCRDModel(){
        //this.actualCr = actualCr;
    }

    public String getActualCr() {
        return actualCr;
    }

    public void setActualCr(String actualCr) {
        this.actualCr = actualCr;
    }
    
    public String getStrFecha() {
        DateFormat df = new SimpleDateFormat("DD/MM/YYYY");
        String strFecha = df.format(actualCr);
        return strFecha;
    }

    
   
}
