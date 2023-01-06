/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.oracle;

import com.onest.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sistemas
 */
public class PendientesFleteBean implements Serializable {

    private String id = null;
    private String cuenta = null;
    private String fcaptura = null;
    private String chofer = null;
    private String transporte = null;
    private String unidad = null;
    private String costoFlete = null;
    private String costoIndirecto = null;
    private String tdesc = null;
    private String agrupador = null;
    private String transporteId = null;
    private Float totalFila = null;
    private List<CostoBean> costos = new ArrayList<CostoBean>(0);

    /**
     * @return the id
     */
    public String getId() {
        return id==null?"":id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the cuenta
     */
    public String getCuenta() {
        return cuenta==null?"":cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the fcaptura
     */
    public String getFcaptura() {
        return fcaptura==null?"":fcaptura;
    }

    /**
     * @param fcaptura the fcaptura to set
     */
    public void setFcaptura(String fcaptura) {
        this.fcaptura = fcaptura;
    }

    /**
     * @return the chofer
     */
    public String getChofer() {
        return chofer==null?"":chofer;
    }

    /**
     * @param chofer the chofer to set
     */
    public void setChofer(String chofer) {
        this.chofer = chofer;
    }

    /**
     * @return the transporte
     */
    public String getTransporte() {
        return transporte==null?"":transporte;
    }

    /**
     * @param transporte the transporte to set
     */
    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    /**
     * @return the unidad
     */
    public String getUnidad() {
        return unidad==null?"":unidad;
    }

    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    /**
     * @return the costoFlete
     */
    public String getCostoFlete() {
        return costoFlete==null?"0":costoFlete;
    }

    /**
     * @param costoFlete the costoFlete to set
     */
    public void setCostoFlete(String costoFlete) {
        this.costoFlete = costoFlete;
    }

    /**
     * @return the costoIndirecto
     */
    public String getCostoIndirecto() {
        return costoIndirecto==null?"0":costoIndirecto;
    }

    /**
     * @param costoIndirecto the costoIndirecto to set
     */
    public void setCostoIndirecto(String costoIndirecto) {
        this.costoIndirecto = costoIndirecto;
    }

    /**
     * @return the tdesc
     */
    public String getTdesc() {
        return tdesc;
    }

    /**
     * @param tdesc the tdesc to set
     */
    public void setTdesc(String tdesc) {
        this.tdesc = tdesc;
    }

    /**
     * @return the agrupador
     */
    public String getAgrupador() {
        return agrupador;
    }

    /**
     * @param agrupador the agrupador to set
     */
    public void setAgrupador(String agrupador) {
        this.agrupador = agrupador;
    }

    /**
     * @return the transporteId
     */
    public String getTransporteId() {
        return transporteId;
    }

    /**
     * @param transporteId the transporteId to set
     */
    public void setTransporteId(String transporteId) {
        this.transporteId = transporteId;
    }

    /*
     * @Override public boolean equals(Object obj) { if (obj != null) { if
     * (this.id != null && this.agrupador != null) { PendientesFleteBean test =
     * (PendientesFleteBean) obj; if (this.id.equalsIgnoreCase(test.getId())) {
     * if (this.agrupador.equalsIgnoreCase(test.getAgrupador())) { return true;
     * } } } } return false; }
     */
    /**
     * @return the costos
     */
    public List<CostoBean> getCostos() {
        return costos;
    }

    /**
     * @param costos the costos to set
     */
    public void setCostos(List<CostoBean> costos) {
        this.costos = costos;
    }

    public String getCostosIndirectos(String plantilla){
        Float total = new Float(0.0);
        String test = plantilla;
        if(plantilla!=null){
            if (this.getCostoIndirecto() != null) {
                getCostos().add(new CostoBean(this.getCostoIndirecto(),this.getTransporteId()));
            } 
            total+=Util.getFloatValue(this.getCostoFlete());
            if(!getCostos().isEmpty()){
                for(CostoBean next : getCostos()){
                    test = test.replace("id"+next.getKey()+"id", Util.getFFloatValue(next.getValue()));
                    total += Util.getFloatValue(next.getValue());
                }                
            } else {
                test = plantilla;
            }
            test = test.replaceAll("id[0-9]+id", "0");
            test+=Util.TD_INI+Util.getFFloatValue(String.valueOf(total))+Util.TD_FIN;
            this.setTotalFila(total);
          return test;
        }
        return "";
    }

    @Override
    public String toString() {
        return Util.TR_INI.concat(getId()).concat(Util.TD).
                concat(getCuenta()).
                concat(Util.TD).
                concat(getFcaptura()).
                concat(Util.TD).
                concat(getChofer()).
                concat(Util.TD).
                concat(getTransporte()).
                concat(Util.TD).
                concat(getUnidad()).
                concat(Util.TD).concat(Util.getFFloatValue(getCostoFlete()));
    }

    /**
     * @return the totalFila
     */
    public Float getTotalFila() {
        return totalFila;
    }

    /**
     * @param totalFila the totalFila to set
     */
    public void setTotalFila(Float totalFila) {
        this.totalFila = totalFila;
    }
}
