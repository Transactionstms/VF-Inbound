/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bb.web.beans;

import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author miguel
 */
public class ParameterExcelBean {
    private String tableName = null;
    private String idStore = null;
    private Sheet sheet = null;
    private List<ColumnaBean> columnas = null;
    private Map storeParameters = null;
    private boolean executeStore = false;
    private boolean puig = true;

    /**
     * @return the sheet
     */
    public Sheet getSheet() {
        return sheet;
    }

    /**
     * @param sheet the sheet to set
     */
    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return the columnas
     */
    public List<ColumnaBean> getColumnas() {
        return columnas;
    }

    /**
     * @param columnas the columnas to set
     */
    public void setColumnas(List<ColumnaBean> columnas) {
        this.columnas = columnas;
    }

    /**
     * @return the idStore
     */
    public String getIdStore() {
        return idStore;
    }

    /**
     * @param idStore the idStore to set
     */
    public void setIdStore(String idStore) {
        this.idStore = idStore;
    }

    /**
     * @return the executeStore
     */
    public boolean isExecuteStore() {
        return executeStore;
    }

    /**
     * @param executeStore the executeStore to set
     */
    public void setExecuteStore(boolean executeStore) {
        this.executeStore = executeStore;
    }

    /**
     * @return the storeParameters
     */
    public Map getStoreParameters() {
        return storeParameters;
    }

    /**
     * @param storeParameters the storeParameters to set
     */
    public void setStoreParameters(Map storeParameters) {
        this.storeParameters = storeParameters;
    }

    /**
     * @return the puig
     */
    public boolean isPuig() {
        return puig;
    }

    /**
     * @param puig the puig to set
     */
    public void setPuig(boolean puig) {
        this.puig = puig;
    }
}