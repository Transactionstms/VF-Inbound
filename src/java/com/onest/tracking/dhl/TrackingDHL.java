/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.tracking.dhl;

import com.dhl.datatypes.AWBInfo;
import com.mx.hilti.dhl.plantilla.TrackingRequest;
import javax.xml.transform.TransformerException;

/**
 *
 * @author RicardoMartinez
 */
public class TrackingDHL {

    public AWBInfo tracking(String guia) {
        AWBInfo aWBInfo = new AWBInfo();

        TrackingRequest reques = new TrackingRequest();
        try {
            String file = reques.getXML(guia);
        } catch (TransformerException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }

        return aWBInfo;

    }
}
