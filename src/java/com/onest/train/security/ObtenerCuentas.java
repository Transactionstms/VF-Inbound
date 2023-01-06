/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.security;

/**
 *
 * @author tere
 */
import com.onest.oracle.DB;
// Referenced classes of package com.onest.security:
//            BASE64
public class ObtenerCuentas {
    private String[][] resultado;
     private String userName = "";
      private int isSuperUser = 0;
      private String userNID;
    public ObtenerCuentas() {
    }

    public String verifyLogin(String nouser) {
        String cadena = "";
        DB db = new DB(DB.SECURITY, DB.SECURITY);
        String query = "SELECT USR_DIV_ID, CBDIV_ID FROM BROKER_USUARIO_DIV WHERE USER_NID=" + nouser + "";
        if (db.doDB(query)) {
            for (String[] row : db.getResultado()) {
                System.out.println(row[1]);
                cadena += row[1] + ",";
            }
        }
        cadena = cadena.substring(0, cadena.length() - 1);
        return cadena;
    }
    
    public String obtieneBodegaId(String cbdivid) {
        String cadena = "";
        DB db = new DB(DB.SECURITY, DB.SECURITY);
        String query = "select distinct bodega_id from ontms_cta_bod_div where cbdiv_id in ( "+cbdivid +" ) ";
        if (db.doDB(query)) {
            for (String[] row : db.getResultado()) {
                System.out.println(row[0]);
                cadena += row[0] + ",";
            }
            cadena = cadena.substring(0, cadena.length() - 1);
        }
        System.out.println("Cadena::::"+cadena);
        return cadena;
    }    
    
   
}
