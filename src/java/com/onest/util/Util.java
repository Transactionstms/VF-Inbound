/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.util;

import com.bean.DestinoHiltiBean;
import com.onest.misc.Cuenta;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
import jbarcodebean.JBarcodeBean;
import net.sourceforge.jbarcodebean.model.Code39;

/**
 *
 * @author Sistemas
 */
public class Util {

    public static String[] current = null;
    public static String TD = "</td><td>";
    public static String TR_INI = "<tr><td>";
    public static String TR_FIN = "<td></tr>";
    public static String TD_INI = "<td>";
    public static String TD_FIN = "</td>";
    public static String INICIO_RUTA = "C:\\";

    //public static String INICIO_RUTA = "/Users/sergiocastros/";
    //public static String INICIO_RUTA_XML = "xml/";
    public static String INICIO_RUTA_XML = "xml\\";
    //public static String INICIO_RUTA = "/home/Dilogin/apps/exec/wtomcat/tms/";

    public static String get(int index) {
        String result = "";
        try {
            result = current[index];
        } catch (Exception e) {
        }
        return result;
    }

    public static Float getFloatValue(String value) {
        Float fvalue = new Float(0.0);
        try {
            fvalue = Float.valueOf(value);
        } catch (Exception e) {
        }
        return fvalue;
    }
    private static java.text.DecimalFormat FORMATO = new DecimalFormat("#0.00");
    private static String SIGNO_PESO = "$ ";

    public static String getFFloatValue(String value) {
        Float fvalue = new Float(0.0);
        String formato = null;
        try {
            fvalue = Float.valueOf(value);
            formato = FORMATO.format(fvalue);

        } catch (Exception e) {
            formato = FORMATO.format(fvalue);
        }
        return SIGNO_PESO + formato;
    }

    public String cuentaBroker(Cuenta[] cuenta) {
        String salida = "";

        for (int i = 0; i < cuenta.length; i++) {
            Cuenta bean = cuenta[i];
            salida = bean.getIdCta();
        }

        return salida;
    }

    public String cadenaimpresion(String tmp) {
        String salida = "";

        String[] cadena = tmp.split("\\|");
        for (int i = 0; i < tmp.length(); i++) {
            System.out.println("posicion " + i + " valor   " + cadena[i]);
        }

        return salida;
    }

    public String validaNull(String var) {
        String salida = "";

        if (var == null) {
            salida = "";
        } else {
            salida = var;
        }

        return salida;
    }

    public boolean validarObject(DestinoHiltiBean bean) throws Throwable {
        boolean bandera = false;

        if (bean.getDestCustClntId() != null
                && bean.getStrtName() != null && bean.getColonyName() != null
                && bean.getZipCode() != null) {
            bandera = true;
        } else {
            bandera = false;
        }

        return bandera;
    }

    public String validacionPeso(String var) {
        String salida = "";

        String[] tmp = var.split("\\.");
        String decimal = "";

        if (tmp.length == 1) {
            salida = var;
        } else {
            if (tmp[1].length() > 3) {
                decimal = tmp[1].substring(0, tmp[1].length() - 1);
                salida = tmp[0] + "." + decimal;
            } else {
                salida = var;
            }
        }

        return salida;
    }

    public String generarCodigoBarras(String idGuia) {
        String salida = "";
        try {

            JBarcodeBean barcode = new JBarcodeBean();

            // nuestro tipo de codigo de barra
            barcode.setCodeType(new Code39());
            barcode.setCode(idGuia);
            barcode.setSize(30, 2600);

            String img = com.onest.util.Util.INICIO_RUTA + "logo\\codebar.jpg";
            //barcode.setCheckDigit(false);

            BufferedImage bufferedImage = barcode.draw(new BufferedImage(300, 100, BufferedImage.TYPE_INT_RGB));

            // guardar en disco como png
            File file = new File(img);
            ImageIO.write(bufferedImage, "jpg", file);
            salida = img;

        } catch (IOException ex) {
            ex.printStackTrace(System.err);
            com.management.util.Loggin.error(ex);
        }
        return salida;
    }

    public String valdiaXML(String file) {
        String salida = "";

        String[] tmp = file.split("_");

        if (tmp[0].length() > 31) {
            salida = "1";

        } else {
            salida = "0";
        }

        return salida;
    }
}
