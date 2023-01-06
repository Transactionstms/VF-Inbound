/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bb.web.beans;

import java.util.List;

/**
 *
 * @author RicardoMartinez
 */
public class ShipmentEvent {
    
    private String Date;
    private String Time;
    private String ServiceEventEventCode;
    private String ServiceEventDescription;
    private String Description;
    private ServiceArea serviceArea;

    //private List <ServiceArea> ServiceArea;
    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getServiceEventEventCode() {
        return ServiceEventEventCode;
    }

    public void setServiceEventEventCode(String ServiceEventEventCode) {
        this.ServiceEventEventCode = ServiceEventEventCode;
    }

    public String getServiceEventDescription() {
        return ServiceEventDescription;
    }

    public void setServiceEventDescription(String ServiceEventDescription) {
        this.ServiceEventDescription = ServiceEventDescription;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public ServiceArea getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(ServiceArea serviceArea) {
        this.serviceArea = serviceArea;
    }

  
    
}
