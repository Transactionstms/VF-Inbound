/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.modificar.catalogos;

import com.onest.oracle.Conexion;
import com.onest.util.PropertiesUtil;
import java.sql.CallableStatement;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;
import oracle.sql.ArrayDescriptor;
import oracle.sql.ARRAY;

/**
 *
 * @author Desarrollo Tacts
 */
public class Upltd_gtnConShipmentId {

    public static CallableStatement ltdGtnShipmentId(Conexion conexion, String shipmentId) throws Exception {
        try {
            CallableStatement sp = conexion.getConexion().prepareCall("{ call sp_inb_actualizaltd_gtnConShip(?,?) }");
            sp.setString("pshipment_details", shipmentId);
            sp.registerOutParameter("resultado", -10);
            return sp;
        } catch (Exception exception) {
            System.out.println("--" + exception);
            return null;
        }
    }

    public static CallableStatement SP_INB_SUBIRCUSTOMS_ADMIN(Conexion conexion,  String pNUMERO_DE_EVENTO, String pSHIPMENT_ID, String pCONTAINER_ID, String pREFERENCIA_AA, String pPRIORIDAD, String pPAIS_ORIGEN, String pSIZE_CONTAINER, String pVALOR_USD, String pETA_PORT_OF_DISCHARGE, String pAGENTE_ADUANAL, String pPEDIMENTO_A1, String pPEDIMENTO_R1, String pMOTIVO_RECTIFICACION_1, String pPEDIMENTO_R1_2DO, String pMOTIVO_RECTIFICACION_2, String pFECHA_RECEPCION_DOCUMENTOS, String pRECINTO, String pNAVIERA_FORWARDER, String pBUQUE, String pFECHA_REVALID_LIBE_BL, String pFECHA_PREVIO_ORIGEN, String pFECHA_PREVIO_DESTINO, String pFECHA_RESULTADO_PREVIO, String pPROFORMA_FINAL, String pREQUIERE_PERMISO, String pFECHA_ENVIO_FICHAS_NOTAS, String pFEC_RECEPCION_PERMISOS_TRAMIT, String pFEC_ACT_PERMISOS, String pFEC_PERM_AUT, String pCO_APLIC_PREF_ARANCELARIA, String pAPLIC_PREF_ARANCELARIA_CO, String pREQUIERE_UVA, String pREQUIERE_CA, String pFECHA_RECEPCION_CA, String pNÚMERO_CONSTANCIA_CA, String pMONTO_CA, String pFECHA_DOCUMENTOS_COMPLETOS, String pFECHA_PAGO_PEDIMENTO, String pFECHA_SOLICITUD_TRANSPORTE, String pFECHA_MODULACION, String pMODALIDAD_CAMION_TREN, String pRESULT_MODULACION_VERDE_ROJO, String pFECHA_RECONOCIMIENTO, String pFECHA_LIBERACION, String pSELLO_ORIGEN, String pSELLO_FINAL, String pFECHA_RETENCION_AUTORIDAD, String pFECHA_LIB_POR_RET_AUT, String pESTATUS_OPERACION, String pMOTIVO_ATRASO, String pOBSERVACIONES, String pLLEGADA_A_NOVA, String pLLEGADA_A_GLOBE_TRADE_SD, String pARCHIVO_M, String pFECHA_ARCHIVO_M, String pFECHA_SOLICIT_MANIP, String pFECHA_VENCIM_MANIP, String pFECHA_CONFIRM_CLAVE_PEDIM, String pFECHA_RECEP_INCREMENT, String pT_E, String pFECHA_VENCIM_INBOUND, String pNO_BULTOS, String pPESO_KG, String pTRANSFERENCIA, String pFECHA_INICIO_ETIQUETADO, String pFECHA_TERMINO_ETIQUETADO, String pHORA_TERMINO_ETIQUETADO, String pPROVEEDOR, String pPROVEEDOR_CARGA, String pFY) throws Exception {
        try {
            CallableStatement sp = conexion.getConexion().prepareCall("{ call SP_INB_SUBIRCUSTOMS_ADMIN(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
                sp.setString("pNUMERO_DE_EVENTO", pNUMERO_DE_EVENTO); 
                sp.setString("pSHIPMENT_ID ",pSHIPMENT_ID); 
                sp.setString("pCONTAINER_ID ",pCONTAINER_ID);
                sp.setString("pREFERENCIA_AA ",pREFERENCIA_AA);
                sp.setString("pPRIORIDAD ",pPRIORIDAD);
                sp.setString("pPAIS_ORIGEN ",pPAIS_ORIGEN);
                sp.setString("pSIZE_CONTAINER ",pSIZE_CONTAINER);
                sp.setString("pVALOR_USD ",pVALOR_USD);
                sp.setString("pETA_PORT_OF_DISCHARGE ",pETA_PORT_OF_DISCHARGE);
                sp.setString("pAGENTE_ADUANAL ",pAGENTE_ADUANAL);
                sp.setString("pPEDIMENTO_A1 ",pPEDIMENTO_A1);
                sp.setString("pPEDIMENTO_R1 ",pPEDIMENTO_R1);
                sp.setString("pMOTIVO_RECTIFICACION_1 ",pMOTIVO_RECTIFICACION_1);
                sp.setString("pPEDIMENTO_R1_2DO ",pPEDIMENTO_R1_2DO);
                sp.setString("pMOTIVO_RECTIFICACION_2 ",pMOTIVO_RECTIFICACION_2);
                sp.setString("pFECHA_RECEPCION_DOCUMENTOS ",pFECHA_RECEPCION_DOCUMENTOS);
                sp.setString("pRECINTO ",pRECINTO);
                sp.setString("pNAVIERA_FORWARDER ",pNAVIERA_FORWARDER);
                sp.setString("pBUQUE ",pBUQUE);
                sp.setString("pFECHA_REVALID_LIBE_BL ",pFECHA_REVALID_LIBE_BL);
                sp.setString("pFECHA_PREVIO_ORIGEN ",pFECHA_PREVIO_ORIGEN);
                sp.setString("pFECHA_PREVIO_DESTINO ",pFECHA_PREVIO_DESTINO);
                sp.setString("pFECHA_RESULTADO_PREVIO ",pFECHA_RESULTADO_PREVIO);
                sp.setString("pPROFORMA_FINAL ",pPROFORMA_FINAL);
                sp.setString("pREQUIERE_PERMISO ",pREQUIERE_PERMISO);
                sp.setString("pFECHA_ENVIO_FICHAS_NOTAS ",pFECHA_ENVIO_FICHAS_NOTAS);
                sp.setString("pFEC_RECEPCION_PERMISOS_TRAMIT ",pFEC_RECEPCION_PERMISOS_TRAMIT);
                sp.setString("pFEC_ACT_PERMISOS ",pFEC_ACT_PERMISOS);
                sp.setString("pFEC_PERM_AUT ",pFEC_PERM_AUT);
                sp.setString("pCO_APLIC_PREF_ARANCELARIA ",pCO_APLIC_PREF_ARANCELARIA);
                sp.setString("pAPLIC_PREF_ARANCELARIA_CO ",pAPLIC_PREF_ARANCELARIA_CO);
                sp.setString("pREQUIERE_UVA ",pREQUIERE_UVA);
                sp.setString("pREQUIERE_CA ",pREQUIERE_CA);
                sp.setString("pFECHA_RECEPCION_CA ",pFECHA_RECEPCION_CA);
                sp.setString("pNÚMERO_CONSTANCIA_CA ",pNÚMERO_CONSTANCIA_CA);
                sp.setString("pMONTO_CA ",pMONTO_CA);
                sp.setString("pFECHA_DOCUMENTOS_COMPLETOS ",pFECHA_DOCUMENTOS_COMPLETOS);
                sp.setString("pFECHA_PAGO_PEDIMENTO ",pFECHA_PAGO_PEDIMENTO);
                sp.setString("pFECHA_SOLICITUD_TRANSPORTE ",pFECHA_SOLICITUD_TRANSPORTE);
                sp.setString("pFECHA_MODULACION ",pFECHA_MODULACION);
                sp.setString("pMODALIDAD_CAMION_TREN ",pMODALIDAD_CAMION_TREN);
                sp.setString("pRESULT_MODULACION_VERDE_ROJO ",pRESULT_MODULACION_VERDE_ROJO);
                sp.setString("pFECHA_RECONOCIMIENTO ",pFECHA_RECONOCIMIENTO);
                sp.setString("pFECHA_LIBERACION ",pFECHA_LIBERACION);
                sp.setString("pSELLO_ORIGEN ",pSELLO_ORIGEN);
                sp.setString("pSELLO_FINAL ",pSELLO_FINAL);
                sp.setString("pFECHA_RETENCION_AUTORIDAD ",pFECHA_RETENCION_AUTORIDAD);
                sp.setString("pFECHA_LIB_POR_RET_AUT ",pFECHA_LIB_POR_RET_AUT);
                sp.setString("pESTATUS_OPERACION ",pESTATUS_OPERACION);
                sp.setString("pMOTIVO_ATRASO ",pMOTIVO_ATRASO);
                sp.setString("pOBSERVACIONES ",pOBSERVACIONES);
                sp.setString("pLLEGADA_A_NOVA ",pLLEGADA_A_NOVA);
                sp.setString("pLLEGADA_A_GLOBE_TRADE_SD ",pLLEGADA_A_GLOBE_TRADE_SD);
                sp.setString("pARCHIVO_M ",pARCHIVO_M);
                sp.setString("pFECHA_ARCHIVO_M ",pFECHA_ARCHIVO_M);
                sp.setString("pFECHA_SOLICIT_MANIP ",pFECHA_SOLICIT_MANIP);
                sp.setString("pFECHA_VENCIM_MANIP ",pFECHA_VENCIM_MANIP);
                sp.setString("pFECHA_CONFIRM_CLAVE_PEDIM ",pFECHA_CONFIRM_CLAVE_PEDIM);
                sp.setString("pFECHA_RECEP_INCREMENT ",pFECHA_RECEP_INCREMENT);
                sp.setString("pT_E ",pT_E);
                sp.setString("pFECHA_VENCIM_INBOUND ",pFECHA_VENCIM_INBOUND);
                sp.setString("pNO_BULTOS ",pNO_BULTOS);
                sp.setString("pPESO_KG ",pPESO_KG);
                sp.setString("pTRANSFERENCIA ",pTRANSFERENCIA);
                sp.setString("pFECHA_INICIO_ETIQUETADO ",pFECHA_INICIO_ETIQUETADO);
                sp.setString("pFECHA_TERMINO_ETIQUETADO ",pFECHA_TERMINO_ETIQUETADO);
                sp.setString("pHORA_TERMINO_ETIQUETADO ",pHORA_TERMINO_ETIQUETADO);
                sp.setString("pPROVEEDOR ",pPROVEEDOR);
                sp.setString("pPROVEEDOR_CARGA ",pPROVEEDOR_CARGA);
                sp.setString("pFY ",pFY);
                sp.setInt("pidlenguaje ",20);
                sp.setInt("pdivision ",20);
                sp.setInt("pidbodega ",20);
                sp.registerOutParameter("resultado", -10);
            return sp;
        } catch (Exception exception) {
            System.out.println("--" + exception);
            return null;
        }
    }
    
    public static CallableStatement SP_INB_SUBIRCUSTOMS_GRAL(Conexion conexion, String pNUMERO_DE_EVENTO , String pSHIPMENT_ID , String pCONTAINER_ID , String pREFERENCIA_AA , String pPRIORIDAD , String pPAIS_ORIGEN , String pSIZE_CONTAINER , String pVALOR_USD , String pETA_PORT_OF_DISCHARGE , String pAGENTE_ADUANAL , String pPEDIMENTO_A1 , String pPEDIMENTO_R1 , String pMOTIVO_RECTIFICACION_1 , String pPEDIMENTO_R1_2DO , String pMOTIVO_RECTIFICACION_2 , String pFECHA_RECEPCION_DOCUMENTOS , String pRECINTO , String pNAVIERA_FORWARDER , String pBUQUE , String pFECHA_REVALID_LIBE_BL , String pFECHA_PREVIO_ORIGEN , String pFECHA_PREVIO_DESTINO , String pFECHA_RESULTADO_PREVIO , String pPROFORMA_FINAL , String pREQUIERE_PERMISO , String pFECHA_ENVIO_FICHAS_NOTAS , String pFEC_RECEPCION_PERMISOS_TRAMIT , String pFEC_ACT_PERMISOS , String pFEC_PERM_AUT , String pCO_APLIC_PREF_ARANCELARIA , String pAPLIC_PREF_ARANCELARIA_CO , String pREQUIERE_UVA , String pREQUIERE_CA , String pFECHA_RECEPCION_CA , String pNÚMERO_CONSTANCIA_CA , String pMONTO_CA , String pFECHA_DOCUMENTOS_COMPLETOS , String pFECHA_PAGO_PEDIMENTO , String pFECHA_SOLICITUD_TRANSPORTE , String pFECHA_MODULACION , String pMODALIDAD_CAMION_TREN , String pRESULT_MODULACION_VERDE_ROJO , String pFECHA_RECONOCIMIENTO , String pFECHA_LIBERACION , String pSELLO_ORIGEN , String pSELLO_FINAL , String pFECHA_RETENCION_AUTORIDAD , String pFECHA_LIB_POR_RET_AUT , String pESTATUS_OPERACION , String pMOTIVO_ATRASO , String pOBSERVACIONES , String pFY) throws Exception {
        try {
            CallableStatement sp = conexion.getConexion().prepareCall("{ call SP_INB_SUBIRCUSTOMS_GRAL(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
            sp.setString("pNUMERO_DE_EVENTO ", pNUMERO_DE_EVENTO);
            sp.setString("pSHIPMENT_ID ", pSHIPMENT_ID);
            sp.setString("pCONTAINER_ID ", pCONTAINER_ID);
            sp.setString("pREFERENCIA_AA ", pREFERENCIA_AA);
            sp.setString("pPRIORIDAD ", pPRIORIDAD);
            sp.setString("pPAIS_ORIGEN ", pPAIS_ORIGEN);
            sp.setString("pSIZE_CONTAINER ", pSIZE_CONTAINER);
            sp.setString("pVALOR_USD ", pVALOR_USD);
            sp.setString("pETA_PORT_OF_DISCHARGE ", pETA_PORT_OF_DISCHARGE);
            sp.setString("pAGENTE_ADUANAL ", pAGENTE_ADUANAL);
            sp.setString("pPEDIMENTO_A1 ", pPEDIMENTO_A1);
            sp.setString("pPEDIMENTO_R1 ", pPEDIMENTO_R1);
            sp.setString("pMOTIVO_RECTIFICACION_1 ", pMOTIVO_RECTIFICACION_1);
            sp.setString("pPEDIMENTO_R1_2DO ", pPEDIMENTO_R1_2DO);
            sp.setString("pMOTIVO_RECTIFICACION_2 ", pMOTIVO_RECTIFICACION_2);
            sp.setString("pFECHA_RECEPCION_DOCUMENTOS ", pFECHA_RECEPCION_DOCUMENTOS); 
            sp.setString("pRECINTO ", pRECINTO);
            sp.setString("pNAVIERA_FORWARDER ", pNAVIERA_FORWARDER);
            sp.setString("pBUQUE ", pBUQUE);
            sp.setString("pFECHA_REVALID_LIBE_BL ", pFECHA_REVALID_LIBE_BL);
            sp.setString("pFECHA_PREVIO_ORIGEN ", pFECHA_PREVIO_ORIGEN);
            sp.setString("pFECHA_PREVIO_DESTINO ", pFECHA_PREVIO_DESTINO);
            sp.setString("pFECHA_RESULTADO_PREVIO ", pFECHA_RESULTADO_PREVIO);
            sp.setString("pPROFORMA_FINAL ", pPROFORMA_FINAL);
            sp.setString("pREQUIERE_PERMISO ", pREQUIERE_PERMISO);
            sp.setString("pFECHA_ENVIO_FICHAS_NOTAS ", pFECHA_ENVIO_FICHAS_NOTAS);
            sp.setString("pFEC_RECEPCION_PERMISOS_TRAMIT ", pFEC_RECEPCION_PERMISOS_TRAMIT);
            sp.setString("pFEC_ACT_PERMISOS ", pFEC_ACT_PERMISOS);
            sp.setString("pFEC_PERM_AUT ", pFEC_PERM_AUT);
            sp.setString("pCO_APLIC_PREF_ARANCELARIA ", pCO_APLIC_PREF_ARANCELARIA);
            sp.setString("pAPLIC_PREF_ARANCELARIA_CO ", pAPLIC_PREF_ARANCELARIA_CO);
            sp.setString("pREQUIERE_UVA ", pREQUIERE_UVA);
            sp.setString("pREQUIERE_CA ", pREQUIERE_CA);
            sp.setString("pFECHA_RECEPCION_CA ", pFECHA_RECEPCION_CA);
            sp.setString("pNÚMERO_CONSTANCIA_CA ", pNÚMERO_CONSTANCIA_CA); 
            sp.setString("pMONTO_CA ", pMONTO_CA);
            sp.setString("pFECHA_DOCUMENTOS_COMPLETOS ", pFECHA_DOCUMENTOS_COMPLETOS); 
            sp.setString("pFECHA_PAGO_PEDIMENTO ", pFECHA_PAGO_PEDIMENTO);
            sp.setString("pFECHA_SOLICITUD_TRANSPORTE ", pFECHA_SOLICITUD_TRANSPORTE);
            sp.setString("pFECHA_MODULACION ", pFECHA_MODULACION);
            sp.setString("pMODALIDAD_CAMION_TREN ", pMODALIDAD_CAMION_TREN);
            sp.setString("pRESULT_MODULACION_VERDE_ROJO", pRESULT_MODULACION_VERDE_ROJO); 
            sp.setString("pFECHA_RECONOCIMIENTO ", pFECHA_RECONOCIMIENTO);
            sp.setString("pFECHA_LIBERACION ", pFECHA_LIBERACION);
            sp.setString("pSELLO_ORIGEN ", pSELLO_ORIGEN);
            sp.setString("pSELLO_FINAL ", pSELLO_FINAL);
            sp.setString("pFECHA_RETENCION_AUTORIDAD ", pFECHA_RETENCION_AUTORIDAD);
            sp.setString("pFECHA_LIB_POR_RET_AUT ", pFECHA_LIB_POR_RET_AUT);
            sp.setString("pESTATUS_OPERACION ", pESTATUS_OPERACION);
            sp.setString("pMOTIVO_ATRASO ", pMOTIVO_ATRASO);
            sp.setString("pOBSERVACIONES ", pOBSERVACIONES);
            sp.setString("pFY ", pFY);
            sp.setInt("pidlenguaje ",20);
            sp.setInt("pdivision ",20);
            sp.setInt("pidbodega ",20);
            sp.registerOutParameter("resultado", -10);
            return sp;
        } catch (Exception exception) {
            System.out.println("--" + exception);
            return null;
        }
    }

    public static CallableStatement SP_INB_SUBIRCUSTOMS_CUSA(Conexion conexion, String pNUMERO_DE_EVENTO, String pSHIPMENT_ID, String pCONTAINER_ID, String pREFERENCIA_AA, String pPRIORIDAD, String pPAIS_ORIGEN, String pSIZE_CONTAINER, String pVALOR_USD, String pETA_PORT_OF_DISCHARGE, String pAGENTE_ADUANAL, String pPEDIMENTO_A1, String pPEDIMENTO_R1, String pMOTIVO_RECTIFICACION_1, String pPEDIMENTO_R1_2DO, String pMOTIVO_RECTIFICACION_2, String pFECHA_RECEPCION_DOCUMENTOS, String pRECINTO, String pNAVIERA_FORWARDER, String pBUQUE, String pFECHA_REVALID_LIBE_BL, String pFECHA_PREVIO_ORIGEN, String pFECHA_PREVIO_DESTINO, String pFECHA_RESULTADO_PREVIO, String pPROFORMA_FINAL, String pREQUIERE_PERMISO, String pFECHA_ENVIO_FICHAS_NOTAS, String pFEC_RECEPCION_PERMISOS_TRAMIT, String pFEC_ACT_PERMISOS, String pFEC_PERM_AUT, String pCO_APLIC_PREF_ARANCELARIA, String pAPLIC_PREF_ARANCELARIA_CO, String pREQUIERE_UVA, String pREQUIERE_CA, String pFECHA_RECEPCION_CA, String pNÚMERO_CONSTANCIA_CA, String pMONTO_CA, String pFECHA_DOCUMENTOS_COMPLETOS, String pFECHA_PAGO_PEDIMENTO, String pFECHA_SOLICITUD_TRANSPORTE, String pFECHA_MODULACION, String pMODALIDAD_CAMION_TREN, String pRESULT_MODULACION_VERDE_ROJO, String pFECHA_RECONOCIMIENTO, String pFECHA_LIBERACION, String pSELLO_ORIGEN, String pSELLO_FINAL, String pFECHA_RETENCION_AUTORIDAD, String pFECHA_LIB_POR_RET_AUT, String pESTATUS_OPERACION, String pMOTIVO_ATRASO, String pOBSERVACIONES, String pNO_BULTOS, String pPESO_KG, String pTRANSFERENCIA, String pFECHA_INICIO_ETIQUETADO, String pFECHA_TERMINO_ETIQUETADO, String pHORA_TERMINO_ETIQUETADO, String pPROVEEDOR, String pPROVEEDOR_CARGA, String pFY) throws Exception {
        try {
            CallableStatement sp = conexion.getConexion().prepareCall("{ call SP_INB_SUBIRCUSTOMS_CUSA(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
            sp.setString("pNUMERO_DE_EVENTO ",pNUMERO_DE_EVENTO);
            sp.setString("pSHIPMENT_ID ",pSHIPMENT_ID);
            sp.setString("pCONTAINER_ID ",pCONTAINER_ID);
            sp.setString("pREFERENCIA_AA ",pREFERENCIA_AA);
            sp.setString("pPRIORIDAD ",pPRIORIDAD);
            sp.setString("pPAIS_ORIGEN ",pPAIS_ORIGEN);
            sp.setString("pSIZE_CONTAINER ",pSIZE_CONTAINER);
            sp.setString("pVALOR_USD ",pVALOR_USD);
            sp.setString("pETA_PORT_OF_DISCHARGE ",pETA_PORT_OF_DISCHARGE);
            sp.setString("pAGENTE_ADUANAL ",pAGENTE_ADUANAL);
            sp.setString("pPEDIMENTO_A1 ",pPEDIMENTO_A1);
            sp.setString("pPEDIMENTO_R1 ",pPEDIMENTO_R1);
            sp.setString("pMOTIVO_RECTIFICACION_1 ",pMOTIVO_RECTIFICACION_1);
            sp.setString("pPEDIMENTO_R1_2DO ",pPEDIMENTO_R1_2DO);
            sp.setString("pMOTIVO_RECTIFICACION_2 ",pMOTIVO_RECTIFICACION_2);
            sp.setString("pFECHA_RECEPCION_DOCUMENTOS ",pFECHA_RECEPCION_DOCUMENTOS);
            sp.setString("pRECINTO ",pRECINTO);
            sp.setString("pNAVIERA_FORWARDER ",pNAVIERA_FORWARDER);
            sp.setString("pBUQUE ",pBUQUE);
            sp.setString("pFECHA_REVALID_LIBE_BL ",pFECHA_REVALID_LIBE_BL);
            sp.setString("pFECHA_PREVIO_ORIGEN ",pFECHA_PREVIO_ORIGEN);
            sp.setString("pFECHA_PREVIO_DESTINO ",pFECHA_PREVIO_DESTINO);
            sp.setString("pFECHA_RESULTADO_PREVIO ",pFECHA_RESULTADO_PREVIO);
            sp.setString("pPROFORMA_FINAL ",pPROFORMA_FINAL);
            sp.setString("pREQUIERE_PERMISO ",pREQUIERE_PERMISO);
            sp.setString("pFECHA_ENVIO_FICHAS_NOTAS ",pFECHA_ENVIO_FICHAS_NOTAS);
            sp.setString("pFEC_RECEPCION_PERMISOS_TRAMIT ",pFEC_RECEPCION_PERMISOS_TRAMIT);
            sp.setString("pFEC_ACT_PERMISOS ",pFEC_ACT_PERMISOS);
            sp.setString("pFEC_PERM_AUT ",pFEC_PERM_AUT);
            sp.setString("pCO_APLIC_PREF_ARANCELARIA ",pCO_APLIC_PREF_ARANCELARIA);
            sp.setString("pAPLIC_PREF_ARANCELARIA_CO ",pAPLIC_PREF_ARANCELARIA_CO);
            sp.setString("pREQUIERE_UVA ",pREQUIERE_UVA);
            sp.setString("pREQUIERE_CA ",pREQUIERE_CA);
            sp.setString("pFECHA_RECEPCION_CA ",pFECHA_RECEPCION_CA);
            sp.setString("pNÚMERO_CONSTANCIA_CA ",pNÚMERO_CONSTANCIA_CA);
            sp.setString("pMONTO_CA ",pMONTO_CA);
            sp.setString("pFECHA_DOCUMENTOS_COMPLETOS ",pFECHA_DOCUMENTOS_COMPLETOS);
            sp.setString("pFECHA_PAGO_PEDIMENTO ",pFECHA_PAGO_PEDIMENTO);
            sp.setString("pFECHA_SOLICITUD_TRANSPORTE ",pFECHA_SOLICITUD_TRANSPORTE);
            sp.setString("pFECHA_MODULACION ",pFECHA_MODULACION);
            sp.setString("pMODALIDAD_CAMION_TREN ",pMODALIDAD_CAMION_TREN);
            sp.setString("pRESULT_MODULACION_VERDE_ROJO ",pRESULT_MODULACION_VERDE_ROJO);
            sp.setString("pFECHA_RECONOCIMIENTO ",pFECHA_RECONOCIMIENTO);
            sp.setString("pFECHA_LIBERACION ",pFECHA_LIBERACION);
            sp.setString("pSELLO_ORIGEN ",pSELLO_ORIGEN);
            sp.setString("pSELLO_FINAL ",pSELLO_FINAL);
            sp.setString("pFECHA_RETENCION_AUTORIDAD ",pFECHA_RETENCION_AUTORIDAD);
            sp.setString("pFECHA_LIB_POR_RET_AUT ",pFECHA_LIB_POR_RET_AUT);
            sp.setString("pESTATUS_OPERACION ",pESTATUS_OPERACION);
            sp.setString("pMOTIVO_ATRASO ",pMOTIVO_ATRASO);
            sp.setString("pOBSERVACIONES ",pOBSERVACIONES);
            sp.setString("pNO_BULTOS ",pNO_BULTOS);
            sp.setString("pPESO_KG ",pPESO_KG);
            sp.setString("pTRANSFERENCIA ",pTRANSFERENCIA);
            sp.setString("pFECHA_INICIO_ETIQUETADO ",pFECHA_INICIO_ETIQUETADO);
            sp.setString("pFECHA_TERMINO_ETIQUETADO ",pFECHA_TERMINO_ETIQUETADO);
            sp.setString("pHORA_TERMINO_ETIQUETADO ",pHORA_TERMINO_ETIQUETADO);
            sp.setString("pPROVEEDOR ",pPROVEEDOR);
            sp.setString("pPROVEEDOR_CARGA ",pPROVEEDOR_CARGA);
            sp.setString("pFY ",pFY);
            sp.setInt("pidlenguaje ",20);
            sp.setInt("pdivision ",20);
            sp.setInt("pidbodega ",20);
            sp.registerOutParameter("resultado", -10);
            return sp;
        } catch (Exception exception) {
            System.out.println("--" + exception);
            return null;
        }
    }

    public static CallableStatement SP_INB_SUBIRCUSTOMS_LOGIX(Conexion conexion, String pNUMERO_DE_EVENTO, String pSHIPMENT_ID, String pCONTAINER_ID, String pREFERENCIA_AA, String pPRIORIDAD, String pPAIS_ORIGEN, String pSIZE_CONTAINER, String pVALOR_USD, String pETA_PORT_OF_DISCHARGE, String pAGENTE_ADUANAL, String pPEDIMENTO_A1, String pPEDIMENTO_R1, String pMOTIVO_RECTIFICACION_1, String pPEDIMENTO_R1_2DO, String pMOTIVO_RECTIFICACION_2, String pFECHA_RECEPCION_DOCUMENTOS, String pRECINTO, String pNAVIERA_FORWARDER, String pBUQUE, String pFECHA_REVALID_LIBE_BL, String pFECHA_PREVIO_ORIGEN, String pFECHA_PREVIO_DESTINO, String pFECHA_RESULTADO_PREVIO, String pPROFORMA_FINAL, String pREQUIERE_PERMISO, String pFECHA_ENVIO_FICHAS_NOTAS, String pFEC_RECEPCION_PERMISOS_TRAMIT, String pFEC_ACT_PERMISOS, String pFEC_PERM_AUT, String pCO_APLIC_PREF_ARANCELARIA, String pAPLIC_PREF_ARANCELARIA_CO, String pREQUIERE_UVA, String pREQUIERE_CA, String pFECHA_RECEPCION_CA, String pNÚMERO_CONSTANCIA_CA, String pMONTO_CA, String pFECHA_DOCUMENTOS_COMPLETOS, String pFECHA_PAGO_PEDIMENTO, String pFECHA_SOLICITUD_TRANSPORTE, String pFECHA_MODULACION, String pMODALIDAD_CAMION_TREN, String pRESULT_MODULACION_VERDE_ROJO, String pFECHA_RECONOCIMIENTO, String pFECHA_LIBERACION, String pSELLO_ORIGEN, String pSELLO_FINAL, String pFECHA_RETENCION_AUTORIDAD, String pFECHA_LIB_POR_RET_AUT, String pESTATUS_OPERACION, String pMOTIVO_ATRASO, String pOBSERVACIONES, String pLLEGADA_A_NOVA, String pLLEGADA_A_GLOBE_TRADE_SD, String pARCHIVO_M, String pFECHA_ARCHIVO_M, String pFECHA_SOLICIT_MANIP, String pFECHA_VENCIM_MANIP, String pFECHA_CONFIRM_CLAVE_PEDIM, String pFECHA_RECEP_INCREMENT, String pT_E, String pFECHA_VENCIM_INBOUND, String pFY) throws Exception {
        try {
            CallableStatement sp = conexion.getConexion().prepareCall("{ call SP_INB_SUBIRCUSTOMS_LOGIX(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
            sp.setString("pNUMERO_DE_EVENTO",pNUMERO_DE_EVENTO);
            sp.setString("pSHIPMENT_ID",pSHIPMENT_ID);
            sp.setString("pCONTAINER_ID",pCONTAINER_ID);
            sp.setString("pREFERENCIA_AA" ,pREFERENCIA_AA);
            sp.setString("pPRIORIDAD",pPRIORIDAD);
            sp.setString("pPAIS_ORIGEN" ,pPAIS_ORIGEN);
            sp.setString("pSIZE_CONTAINER", pSIZE_CONTAINER);
            sp.setString("pVALOR_USD",pVALOR_USD);
            sp.setString("pETA_PORT_OF_DISCHARGE" ,pETA_PORT_OF_DISCHARGE);
            sp.setString("pAGENTE_ADUANAL" ,pAGENTE_ADUANAL);
            sp.setString("pPEDIMENTO_A1", pPEDIMENTO_A1);
            sp.setString("pPEDIMENTO_R1" ,pPEDIMENTO_R1);
            sp.setString("pMOTIVO_RECTIFICACION_1",pMOTIVO_RECTIFICACION_1);
            sp.setString("pPEDIMENTO_R1_2DO",pPEDIMENTO_R1_2DO);
            sp.setString("pMOTIVO_RECTIFICACION_2",pMOTIVO_RECTIFICACION_2);
            sp.setString("pFECHA_RECEPCION_DOCUMENTOS", pFECHA_RECEPCION_DOCUMENTOS);
            sp.setString("pRECINTO",pRECINTO);
            sp.setString("pNAVIERA_FORWARDER", pNAVIERA_FORWARDER);
            sp.setString("pBUQUE",pBUQUE);
            sp.setString("pFECHA_REVALID_LIBE_BL",pFECHA_REVALID_LIBE_BL);
            sp.setString("pFECHA_PREVIO_ORIGEN",pFECHA_PREVIO_ORIGEN);
            sp.setString("pFECHA_PREVIO_DESTINO",pFECHA_PREVIO_DESTINO);
            sp.setString("pFECHA_RESULTADO_PREVIO" ,pFECHA_RESULTADO_PREVIO);
            sp.setString("pPROFORMA_FINAL",pPROFORMA_FINAL);
            sp.setString("pREQUIERE_PERMISO", pREQUIERE_PERMISO);
            sp.setString("pFECHA_ENVIO_FICHAS_NOTAS", pFECHA_ENVIO_FICHAS_NOTAS);
            sp.setString("pFEC_RECEPCION_PERMISOS_TRAMIT",pFEC_RECEPCION_PERMISOS_TRAMIT);
            sp.setString("pFEC_ACT_PERMISOS", pFEC_ACT_PERMISOS);
            sp.setString("pFEC_PERM_AUT", pFEC_PERM_AUT);
            sp.setString("pCO_APLIC_PREF_ARANCELARIA",pCO_APLIC_PREF_ARANCELARIA);
            sp.setString("pAPLIC_PREF_ARANCELARIA_CO",pAPLIC_PREF_ARANCELARIA_CO);
            sp.setString("pREQUIERE_UVA",pREQUIERE_UVA);
            sp.setString("pREQUIERE_CA",pREQUIERE_CA);
            sp.setString("pFECHA_RECEPCION_CA", pFECHA_RECEPCION_CA);
            sp.setString("pNÚMERO_CONSTANCIA_CA", pNÚMERO_CONSTANCIA_CA);
            sp.setString("pMONTO_CA" ,pMONTO_CA);
            sp.setString("pFECHA_DOCUMENTOS_COMPLETOS", pFECHA_DOCUMENTOS_COMPLETOS);
            sp.setString("pFECHA_PAGO_PEDIMENTO", pFECHA_PAGO_PEDIMENTO);
            sp.setString("pFECHA_SOLICITUD_TRANSPORTE",pFECHA_SOLICITUD_TRANSPORTE);
            sp.setString("pFECHA_MODULACION",pFECHA_MODULACION);
            sp.setString("pMODALIDAD_CAMION_TREN",pMODALIDAD_CAMION_TREN);
            sp.setString("pRESULT_MODULACION_VERDE_ROJO",pRESULT_MODULACION_VERDE_ROJO);
            sp.setString("pFECHA_RECONOCIMIENTO",pFECHA_RECONOCIMIENTO);
            sp.setString("pFECHA_LIBERACION", pFECHA_LIBERACION);
            sp.setString("pSELLO_ORIGEN",pSELLO_ORIGEN);
            sp.setString("pSELLO_FINAL", pSELLO_FINAL);
            sp.setString("pFECHA_RETENCION_AUTORIDAD",pFECHA_RETENCION_AUTORIDAD);
            sp.setString("pFECHA_LIB_POR_RET_AUT",pFECHA_LIB_POR_RET_AUT);
            sp.setString("pESTATUS_OPERACION" ,pESTATUS_OPERACION);
            sp.setString("pMOTIVO_ATRASO",pMOTIVO_ATRASO);
            sp.setString("pOBSERVACIONES", pOBSERVACIONES);
            sp.setString("pLLEGADA_A_NOVA",pLLEGADA_A_NOVA);
            sp.setString("pLLEGADA_A_GLOBE_TRADE_SD",pLLEGADA_A_GLOBE_TRADE_SD);
            sp.setString("pARCHIVO_M",pARCHIVO_M);
            sp.setString("pFECHA_ARCHIVO_M", pFECHA_ARCHIVO_M);
            sp.setString("pFECHA_SOLICIT_MANIP", pFECHA_SOLICIT_MANIP);
            sp.setString("pFECHA_VENCIM_MANIP" ,pFECHA_VENCIM_MANIP);
            sp.setString("pFECHA_CONFIRM_CLAVE_PEDIM", pFECHA_CONFIRM_CLAVE_PEDIM);
            sp.setString("pFECHA_RECEP_INCREMENT", pFECHA_RECEP_INCREMENT);
            sp.setString("pT_E",pT_E);
            sp.setString("pFECHA_VENCIM_INBOUND",pFECHA_VENCIM_INBOUND);
            sp.setString("pFY",pFY);
            sp.setInt("pidlenguaje ",20);
            sp.setInt("pdivision ",20);
            sp.setInt("pidbodega ",20);
            sp.registerOutParameter("resultado", -10);
            return sp;
        } catch (Exception exception) {
            System.out.println("--" + exception);
            return null;
        }
    }

}
