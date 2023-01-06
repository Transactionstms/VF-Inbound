/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.io.Serializable;

/**
 *
 * @author miguel
 */
public class ListaPedidoBean implements Serializable {
    private String numPackingList = null;
    private String numPedido = null;
    private String error = null;

    /**
     * @return the numPackingList
     */
    public String getNumPackingList() {
        return numPackingList;
    }

    /**
     * @param numPackingList the numPackingList to set
     */
    public void setNumPackingList(String numPackingList) {
        this.numPackingList = numPackingList;
    }

    /**
     * @return the numPedido
     */
    public String getNumPedido() {
        return numPedido;
    }

    /**
     * @param numPedido the numPedido to set
     */
    public void setNumPedido(String numPedido) {
        this.numPedido = numPedido;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }
}
