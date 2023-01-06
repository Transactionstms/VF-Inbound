/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.ruta;

import com.onest.util.PropertiesUtil;

/**
 *
 * @author Teresa Martin
 */
public class Ruta {
    public String ruta() {
        //return "/usr/local/logo/logo1.png";
        //return "c://logo//logo1.png";
          return PropertiesUtil.getDbConexionProperties(PropertiesUtil.APP_RUTA_LOGO);
    }
    public String rutaExcel() {
        
        //return "http://74.208.162.53/logos/hilti2.png";
        return "http://localhost/img/vfmx.png";
       // return "http://"+PropertiesUtil.getDbConexionProperties(PropertiesUtil.SERVER_KEY_IP)+"/logos/hilti.png";
        //return "c:/logo/Dilogin.png";
    }
     public String img() {
        //return "http://74.208.162.53/imagenesTMS/";
        return "http://localhost/img/vfmx.png";
        //return "c:/logo/logo1.png";
    }

}