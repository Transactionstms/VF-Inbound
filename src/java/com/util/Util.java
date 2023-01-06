/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import com.onest.oracle.DBConfData;
import com.onest.oracle.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Omar
 */
public class Util {
    public static String getFechaProgramacion(String bodegaId,String doctoReferencia,String cbdivId,DBConfData dbData){
        DB db = new DB(dbData);
        OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
        String fechaprogramacion = "select ods.DOCTO_REFERENCIA,  to_char(ods.DOCTO_FEC_CAPTURA,'DD/MM/YYYY'),"
                            + " ods.DOCTO_FEC_FACTURA,dodp.DIAS_ENTREGA,dodp.DIAS_RETORNO, dodp.BODEGA_ID,oz.ZONA_ID ,"
                            + " dodp.ID_ORIGEN,(CASE WHEN NVL(to_char(OE.EMBARQUE_FEC_CAPTURA,'DD/MM/YYYY'),'  ')='  '"
                            + " THEN  nvl(TO_cHAR(OEP.EMBARQUE_PAQ_CAPTURA,'dd/mm/yyyy'), '  ') "
                            + "  ELSE  NVL(to_char(OE.EMBARQUE_FEC_CAPTURA,'DD/MM/YYYY'),'  ')"
                            + "  END "
                            + " ),NVL(to_char(ods.DOCTO_FEC_EVIDENCIA,'DD/MM/YYYY'), ' '), "
                            + " (  CASE     WHEN NVL(OE.EMBARQUE_ID,'  ')='  '    THEN NVL(OEP.EMBARQUE_PAQ, '  ') "
                            + "    ELSE NVL(OE.EMBARQUE_ID,'  ')   END ),"
                            + " (  CASE     WHEN NVL(olt.LTRANSPORTE_NOMBRE,'  ')='  '"
                            + "    THEN ( CASE WHEN PAQUETERIA_ID =1 THEN 'DHL' WHEN PAQUETERIA_ID =2 THEN 'Paquete Express' ELSE ' ' end) "
                            + "    ELSE NVL(olt.LTRANSPORTE_NOMBRE,'  ')  END )"
                            + " from ontms_docto_sal ods inner join DILOG_DESTINOS_HILTI ddh "
                            + " on ods.DESTINO_ID=ddh.DESTINO_SHIP_TO "
                            + " inner join DILOG_ORIG_DEST_PAQ dodp "
                            + " on ddh.DESTINO_CP=dodp.IATA_CP_DESTINO inner join ONTMS_ZONAS oz on oz.ZONA_ID=dodp.ID_ORIGEN "
                            + " inner join ontms_bodega ob "
                            + " on ob.ZONA_ID=dodp.ID_ORIGEN "
                            + " and ob.BODEGA_ID=" + bodegaId + " "
                            + " LEFT JOIN ONTMS_EMBARQUE OE ON OE.EMBARQUE_AGRUPADOR = ODS.DOCTO_SAL_AGRUPADOR "
                            + " LEFT JOIN  ONTMS_EMBARQUE_PAQUETERIA oep on oep.EMBARQUE_PAQ_AGRUPADOR= ods.docto_Sal_Agrupador "
                            + " LEFT JOIN ontms_camion oc "
                            + " ON oc.camion_id=oe.camion_id LEFT JOIN ontms_chofer och "
                            + " ON och.chofer_id=oe.chofer_id "
                            + " LEFT JOIN ontms_unidad_transporte outr "
                            + " ON outr.utransporte_id=oc.utransporte_id "
                            + " LEFT JOIN ontms_linea_transporte olt "
                            + " ON olt.ltransporte_id  =oc.ltransporte_id"
                            + " where trim(upper(ods.docto_referencia)) = trim(upper(" +doctoReferencia+ ")) AND ods.CBDIV_ID IN( " + cbdivId + ") and ddh.DIVISION_ID=ods.CBDIV_ID";

                    String fechaP = "";
                    String DiasE = "";
                    String fechaem = "";
                    String fechaent = "";
                    String guiaE = "";
                    String guiaT = "";
                    if (db.doDB(fechaprogramacion)) {
                        for (String[] row : db.getResultado()) {
                            fechaP = row[1];
                            DiasE = row[3];
                            fechaem = row[8];
                            fechaent = row[9];
                            guiaE = row[10];
                            guiaT = row[11];

                        }
                    }

                    int anio = Integer.parseInt(fechaP.substring(6));
                    int mes = Integer.parseInt(fechaP.substring(3, 5));
                    int dia = Integer.parseInt(fechaP.substring(0, 2));
                    int diasE = 0;
                    try {
                        diasE = Integer.parseInt(DiasE);
                    } catch (Exception e) {
                        diasE = 0;
                    }
                    Calendar fechaInicial = new GregorianCalendar(anio, mes - 1, dia);
                    for (int i = 0; i < diasE; i++) {
                        fechaInicial.add(Calendar.DATE, 1);
                        if (fechaInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                            fechaInicial.add(Calendar.DATE, 1);
                        }
                    }
                    DateFormat sf = new SimpleDateFormat("dd/MM/YYYY");
                    Date d = fechaInicial.getTime();
                    String convertido = sf.format(d);
                    return convertido;
    }
}
