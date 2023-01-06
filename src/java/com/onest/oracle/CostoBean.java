/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.oracle;

import java.io.Serializable;

/**
 *
 * @author Sistemas
 */
public class CostoBean implements Serializable {
    private String value = null;
    private String key = null;
    
    public CostoBean(String v, String k){
        this.value = v;
        this.key = k;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }
}
