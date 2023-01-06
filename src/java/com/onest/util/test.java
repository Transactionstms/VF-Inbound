/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.util;

import com.dao.ServiceDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author SergioCastro
 */
public class test {
    public static void main(String [] args) throws SQLException, JRException, IOException{
        ServiceDAO dao = new ServiceDAO();
        String var = "";
      String sql = " SELECT servicio_pe ,docto_piezas  FROM  "
                    + " ontms_docto_sal "
                    + " WHERE docto_sal_agrupador=5168201309171406100"
                    + " and servicio_pe is not null  ";
            String tmp = "";
            PreparedStatement ps = dao.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
             //   tmp = rs.getString(1);
                
                oracle.sql.CLOB clobValue = (oracle.sql.CLOB) rs.getClob(1);
                BufferedReader reader = new BufferedReader(new InputStreamReader(clobValue.getAsciiStream()));
                String read = null;
                StringBuffer buffer = new StringBuffer();
                while ((read = reader.readLine()) != null) {
                    buffer.append(read);
                }
                
                tmp = String.valueOf(buffer)  ;
               System.out.println(tmp);
                
            }
             String [] cadena = tmp.split("\\|");
             
            for( int x =0; x< cadena.length ; x++){
               System.out.println("Valores >>>" + cadena[x]); 
            }
            
//FECHA:|17/09/13|HORA :|13:00|IMPULSORA DE TRANSPORTES MEXICANOS SA DE CV|SUCURSAL: MEXICO|JUPITER 26|GUSTAVO A MADERO MEXICO D.F., MEXICO C.P. 07700|CRE|EAD|MEX01WW00430878|CARTA PORTE|RASTREO INTERNET: |21112631520|VALOR DECLARADO: |PESO: 0.00 KGS.  VOL: 0.00 m3|RUTA: AGP-1|MEX01|AGP01  ACUSE:CLIENTE|REMITENTE:  30168|HILTI MEXICANA SA DE CV                      |JAIME BALMES,8-102 1ER PISO  |LOS MORALES POLANCO,MIGUEL HIDALGO 11510|   TEL. 53871600|DESTINATARIO:  2082430|ABB MEXICO S.A. DE C.V.                   |CAMINO A EJIDO EL FRESNAL KM 14.5,S/N  |CENTRO,AGUA PRIETA 84200|SONORA,MEXICO TEL. 5553281492|CANT DESCRIP   QUE DICE CONTIENE                            TAR     CONCEPTO        TOTAL|2      |CAJA           |refacciones                                  |C     |       |               |                                             |      |       |               |                                             |      |       |               |                                             |      |       |               |                                             |      |       |               |                                             |      |FLETE:|        611.06|SERVICIOS:|         58.30|SEGURO:|          0.00|SUBTOTAL:|        669.36|IVA:|        107.10|IVA RET:|          0.00|TOTAL:|        776.46|OBSERVACIONES: |:GFHGFHGFH       || |DOCUMENTADOR: RICARDO AVILA CAMPOS|CLIENTE: HILTI MEXICANA SA DE CV                 |MEX01WW0430878|MEX01WW0430878

            
             
    }
}
