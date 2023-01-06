/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.oracle;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Sistemas
 */
public class ResponseBean implements Serializable {
    private Map rows = null;
    private Map sumas = null;

    /**
     * @return the rows
     */
    public Map getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(Map rows) {
        this.rows = rows;
    }

    /**
     * @return the sumas
     */
    public Map getSumas() {
        return sumas;
    }

    /**
     * @param sumas the sumas to set
     */
    public void setSumas(Map sumas) {
        this.sumas = sumas;
    }
    
    
}
