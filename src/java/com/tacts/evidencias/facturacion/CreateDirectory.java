/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.evidencias.facturacion;

import java.io.File;

/**
 *
 * @author luis_
 */
public class CreateDirectory {
    
    public static String carpetaTicketsPlebes(String cve) {
        String res = "";
        File directorio = new File("D:/Servicios/Facturacion/Tickets/cvd" + cve + "");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                res = "cvd" + cve;
            } else {
                res = "cvd" + cve;
            }
        }

        return res.toString();
    }
    
    public static String carpetaCFDI(String cve) {
        String res = "";
        File directorio = new File("D:/Servicios/Facturacion/Cfdi/cvd" + cve + "");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                res = "cvd" + cve;
            } else {
                res = "cvd" + cve;
            }
        }

        return res.toString();
    }
    
    public static String carpetaCartaPorte(String cve) {
        String res = "";
        File directorio = new File("D:/Servicios/Facturacion/CartaPorte/cvd" + cve + "");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                res = "cvd" + cve;
            } else {
                res = "cvd" + cve;
            }
        }

        return res.toString();
    }
    
    public static String carpetaComplementoPago(String cve) {
        String res = "";
        File directorio = new File("D:/Servicios/Facturacion/ReciboDePago/cvd" + cve + "");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                res = "cvd" + cve;
            } else {
                res = "cvd" + cve;
            }
        }

        return res.toString();
    }

}
