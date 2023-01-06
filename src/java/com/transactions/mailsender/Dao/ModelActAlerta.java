/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transactions.mailsender.Dao;

/**
 *
 * @author sergiocastrosauceda
 */
public class ModelActAlerta {
    
    private String DOCTOSALID;
    private int  DOCTOESTADOID;
    private String FECHA;

    public ModelActAlerta(){
        
    }
    
    public String getDOCTOSALID() {
        return DOCTOSALID;
    }

    public void setDOCTOSALID(String DOCTOSALID) {
        this.DOCTOSALID = DOCTOSALID;
    }

    public int getDOCTOESTADOID() {
        return DOCTOESTADOID;
    }

    public void setDOCTOESTADOID(int DOCTOESTADOID) {
        this.DOCTOESTADOID = DOCTOESTADOID;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }
    
    
    
}
