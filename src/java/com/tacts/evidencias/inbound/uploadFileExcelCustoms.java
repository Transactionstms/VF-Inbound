/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.tacts.evidencias.inbound;

import com.onest.oracle.Conexion;
import com.onest.oracle.Configuracion;
import com.onest.train.modificar.catalogos.Upltd_gtnConShipmentId;
import static java.awt.Color.green;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User_Windows10
 */
public class uploadFileExcelCustoms extends HttpServlet {

    private Conexion cnBaseDeDatos;

    public uploadFileExcelCustoms() {
        Configuracion cfg = new Configuracion();
        this.cnBaseDeDatos = new Conexion(cfg.getSid(), cfg.getUsuario(), cfg.getPassword());
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String agente_id = request.getParameter("idAgenteAduanal");
            String valores_celdas = request.getParameter("valores_celdas");
            CallableStatement sp1 = null;
            String res = "";
            String rows = "";
            
             //Inicializar conexión D.B
             this.cnBaseDeDatos.Iniciar();

            //Celdas Excel Customs
            String pNUMERO_DE_EVENTO = "";
            String pSHIPMENT_ID = "";
            String pCONTAINER_ID = "";
            String pREFERENCIA_AA = "";
            String pPRIORIDAD = "";
            String pPAIS_ORIGEN = "";
            String pSIZE_CONTAINER = "";
            String pVALOR_USD = "";
            String pETA_PORT_OF_DISCHARGE = "";
            String pAGENTE_ADUANAL = "";
            String pPEDIMENTO_A1 = "";
            String pPEDIMENTO_R1 = "";
            String pMOTIVO_RECTIFICACION_1 = "";
            String pPEDIMENTO_R1_2DO = "";
            String pMOTIVO_RECTIFICACION_2 = "";
            String pFECHA_RECEPCION_DOCUMENTOS = "";
            String pRECINTO = "";
            String pNAVIERA_FORWARDER = "";
            String pBUQUE = "";
            String pFECHA_REVALID_LIBE_BL = "";
            String pFECHA_PREVIO_ORIGEN = "";
            String pFECHA_PREVIO_DESTINO = "";
            String pFECHA_RESULTADO_PREVIO = "";
            String pPROFORMA_FINAL = "";
            String pREQUIERE_PERMISO = "";
            String pFECHA_ENVIO_FICHAS_NOTAS = "";
            String pFEC_RECEPCION_PERMISOS_TRAMIT = "";
            String pFEC_ACT_PERMISOS = "";
            String pFEC_PERM_AUT = "";
            String pCO_APLIC_PREF_ARANCELARIA = "";
            String pAPLIC_PREF_ARANCELARIA_CO = "";
            String pREQUIERE_UVA = "";
            String pREQUIERE_CA = "";
            String pFECHA_RECEPCION_CA = "";
            String pNÚMERO_CONSTANCIA_CA = "";
            String pMONTO_CA = "";
            String pFECHA_DOCUMENTOS_COMPLETOS = "";
            String pFECHA_PAGO_PEDIMENTO = "";
            String pFECHA_SOLICITUD_TRANSPORTE = "";
            String pFECHA_MODULACION = "";
            String pMODALIDAD_CAMION_TREN = "";
            String pRESULT_MODULACION_VERDE_ROJO = "";
            String pFECHA_RECONOCIMIENTO = "";
            String pFECHA_LIBERACION = "";
            String pSELLO_ORIGEN = "";
            String pSELLO_FINAL = "";
            String pFECHA_RETENCION_AUTORIDAD = "";
            String pFECHA_LIB_POR_RET_AUT = "";
            String pESTATUS_OPERACION = "";
            String pMOTIVO_ATRASO = "";
            String pOBSERVACIONES = "";
            String pLLEGADA_A_NOVA = "";
            String pLLEGADA_A_GLOBE_TRADE_SD = "";
            String pARCHIVO_M = "";
            String pFECHA_ARCHIVO_M = "";
            String pFECHA_SOLICIT_MANIP = "";
            String pFECHA_VENCIM_MANIP = "";
            String pFECHA_CONFIRM_CLAVE_PEDIM = "";
            String pFECHA_RECEP_INCREMENT = "";
            String pT_E = "";
            String pFECHA_VENCIM_INBOUND = "";
            String pNO_BULTOS = "";
            String pPESO_KG = "";
            String pTRANSFERENCIA = "";
            String pFECHA_INICIO_ETIQUETADO = "";
            String pFECHA_TERMINO_ETIQUETADO = "";
            String pHORA_TERMINO_ETIQUETADO = "";
            String pPROVEEDOR = "";
            String pPROVEEDOR_CARGA = "";
            String pFY = "";

            try {

                if (agente_id.equals("4006")) {              //Plantilla Administrador

                    String[] partes = valores_celdas.split(",");
                    pNUMERO_DE_EVENTO = partes[0];
                    pSHIPMENT_ID = partes[1];
                    pCONTAINER_ID = partes[2];
                    pREFERENCIA_AA = partes[3];
                    pPRIORIDAD = partes[4];
                    pPAIS_ORIGEN = partes[5];
                    pSIZE_CONTAINER = partes[6];
                    pVALOR_USD = partes[7];
                    pETA_PORT_OF_DISCHARGE = partes[8];
                    pAGENTE_ADUANAL = partes[9];
                    pPEDIMENTO_A1 = partes[10];
                    pPEDIMENTO_R1 = partes[11];
                    pMOTIVO_RECTIFICACION_1 = partes[12];
                    pPEDIMENTO_R1_2DO = partes[13];
                    pMOTIVO_RECTIFICACION_2 = partes[14];
                    pFECHA_RECEPCION_DOCUMENTOS = partes[15];
                    pRECINTO = partes[16];
                    pNAVIERA_FORWARDER = partes[17];
                    pBUQUE = partes[18];
                    pFECHA_REVALID_LIBE_BL = partes[19];
                    pFECHA_PREVIO_ORIGEN = partes[20];
                    pFECHA_PREVIO_DESTINO = partes[21];
                    pFECHA_RESULTADO_PREVIO = partes[22];
                    pPROFORMA_FINAL = partes[23];
                    pREQUIERE_PERMISO = partes[24];
                    pFECHA_ENVIO_FICHAS_NOTAS = partes[25];
                    pFEC_RECEPCION_PERMISOS_TRAMIT = partes[26];
                    pFEC_ACT_PERMISOS = partes[27];
                    pFEC_PERM_AUT = partes[28];
                    pCO_APLIC_PREF_ARANCELARIA = partes[29];
                    pAPLIC_PREF_ARANCELARIA_CO = partes[30];
                    pREQUIERE_UVA = partes[31];
                    pREQUIERE_CA = partes[32];
                    pFECHA_RECEPCION_CA = partes[33];
                    pNÚMERO_CONSTANCIA_CA = partes[34];
                    pMONTO_CA = partes[35];
                    pFECHA_DOCUMENTOS_COMPLETOS = partes[36];
                    pFECHA_PAGO_PEDIMENTO = partes[37];
                    pFECHA_SOLICITUD_TRANSPORTE = partes[38];
                    pFECHA_MODULACION = partes[39];
                    pMODALIDAD_CAMION_TREN = partes[40];
                    pRESULT_MODULACION_VERDE_ROJO = partes[41];
                    pFECHA_RECONOCIMIENTO = partes[42];
                    pFECHA_LIBERACION = partes[43];
                    pSELLO_ORIGEN = partes[44];
                    pSELLO_FINAL = partes[45];
                    pFECHA_RETENCION_AUTORIDAD = partes[46];
                    pFECHA_LIB_POR_RET_AUT = partes[47];
                    pESTATUS_OPERACION = partes[48];
                    pMOTIVO_ATRASO = partes[49];
                    pOBSERVACIONES = partes[50];
                    pLLEGADA_A_NOVA = partes[51];
                    pLLEGADA_A_GLOBE_TRADE_SD = partes[52];
                    pARCHIVO_M = partes[53];
                    pFECHA_ARCHIVO_M = partes[54];
                    pFECHA_SOLICIT_MANIP = partes[55];
                    pFECHA_VENCIM_MANIP = partes[56];
                    pFECHA_CONFIRM_CLAVE_PEDIM = partes[57];
                    pFECHA_RECEP_INCREMENT = partes[58];
                    pT_E = partes[59];
                    pFECHA_VENCIM_INBOUND = partes[60];
                    pNO_BULTOS = partes[61];
                    pPESO_KG = partes[62];
                    pTRANSFERENCIA = partes[63];
                    pFECHA_INICIO_ETIQUETADO = partes[64];
                    pFECHA_TERMINO_ETIQUETADO = partes[65];
                    pHORA_TERMINO_ETIQUETADO = partes[66];
                    pPROVEEDOR = partes[67];
                    pPROVEEDOR_CARGA = partes[68];
                    pFY = partes[69];

                    sp1 = Upltd_gtnConShipmentId.SP_INB_SUBIRCUSTOMS_ADMIN(this.cnBaseDeDatos,
                            pNUMERO_DE_EVENTO,
                            pSHIPMENT_ID,
                            pCONTAINER_ID,
                            pREFERENCIA_AA,
                            pPRIORIDAD,
                            pPAIS_ORIGEN,
                            pSIZE_CONTAINER,
                            pVALOR_USD,
                            pETA_PORT_OF_DISCHARGE,
                            pAGENTE_ADUANAL,
                            pPEDIMENTO_A1,
                            pPEDIMENTO_R1,
                            pMOTIVO_RECTIFICACION_1,
                            pPEDIMENTO_R1_2DO,
                            pMOTIVO_RECTIFICACION_2,
                            pFECHA_RECEPCION_DOCUMENTOS,
                            pRECINTO,
                            pNAVIERA_FORWARDER,
                            pBUQUE,
                            pFECHA_REVALID_LIBE_BL,
                            pFECHA_PREVIO_ORIGEN,
                            pFECHA_PREVIO_DESTINO,
                            pFECHA_RESULTADO_PREVIO,
                            pPROFORMA_FINAL,
                            pREQUIERE_PERMISO,
                            pFECHA_ENVIO_FICHAS_NOTAS,
                            pFEC_RECEPCION_PERMISOS_TRAMIT,
                            pFEC_ACT_PERMISOS,
                            pFEC_PERM_AUT,
                            pCO_APLIC_PREF_ARANCELARIA,
                            pAPLIC_PREF_ARANCELARIA_CO,
                            pREQUIERE_UVA,
                            pREQUIERE_CA,
                            pFECHA_RECEPCION_CA,
                            pNÚMERO_CONSTANCIA_CA,
                            pMONTO_CA,
                            pFECHA_DOCUMENTOS_COMPLETOS,
                            pFECHA_PAGO_PEDIMENTO,
                            pFECHA_SOLICITUD_TRANSPORTE,
                            pFECHA_MODULACION,
                            pMODALIDAD_CAMION_TREN,
                            pRESULT_MODULACION_VERDE_ROJO,
                            pFECHA_RECONOCIMIENTO,
                            pFECHA_LIBERACION,
                            pSELLO_ORIGEN,
                            pSELLO_FINAL,
                            pFECHA_RETENCION_AUTORIDAD,
                            pFECHA_LIB_POR_RET_AUT,
                            pESTATUS_OPERACION,
                            pMOTIVO_ATRASO,
                            pOBSERVACIONES,
                            pLLEGADA_A_NOVA,
                            pLLEGADA_A_GLOBE_TRADE_SD,
                            pARCHIVO_M,
                            pFECHA_ARCHIVO_M,
                            pFECHA_SOLICIT_MANIP,
                            pFECHA_VENCIM_MANIP,
                            pFECHA_CONFIRM_CLAVE_PEDIM,
                            pFECHA_RECEP_INCREMENT,
                            pT_E,
                            pFECHA_VENCIM_INBOUND,
                            pNO_BULTOS,
                            pPESO_KG,
                            pTRANSFERENCIA,
                            pFECHA_INICIO_ETIQUETADO,
                            pFECHA_TERMINO_ETIQUETADO,
                            pHORA_TERMINO_ETIQUETADO,
                            pPROVEEDOR,
                            pPROVEEDOR_CARGA,
                            pFY);
                } else if (agente_id.equals("4004")) {     //Plantilla Generica

                    String[] partes = valores_celdas.split(",");
                    pNUMERO_DE_EVENTO = partes[0];
                    pSHIPMENT_ID = partes[1];
                    pCONTAINER_ID = partes[2];
                    pREFERENCIA_AA = partes[3];
                    pPRIORIDAD = partes[4];
                    pPAIS_ORIGEN = partes[5];
                    pSIZE_CONTAINER = partes[6];
                    pVALOR_USD = partes[7];
                    pETA_PORT_OF_DISCHARGE = partes[8];
                    pAGENTE_ADUANAL = partes[9];
                    pPEDIMENTO_A1 = partes[10];
                    pPEDIMENTO_R1 = partes[11];
                    pMOTIVO_RECTIFICACION_1 = partes[12];
                    pPEDIMENTO_R1_2DO = partes[13];
                    pMOTIVO_RECTIFICACION_2 = partes[14];
                    pFECHA_RECEPCION_DOCUMENTOS = partes[15];
                    pRECINTO = partes[16];
                    pNAVIERA_FORWARDER = partes[17];
                    pBUQUE = partes[18];
                    pFECHA_REVALID_LIBE_BL = partes[19];
                    pFECHA_PREVIO_ORIGEN = partes[20];
                    pFECHA_PREVIO_DESTINO = partes[21];
                    pFECHA_RESULTADO_PREVIO = partes[22];
                    pPROFORMA_FINAL = partes[23];
                    pREQUIERE_PERMISO = partes[24];
                    pFECHA_ENVIO_FICHAS_NOTAS = partes[25];
                    pFEC_RECEPCION_PERMISOS_TRAMIT = partes[26];
                    pFEC_ACT_PERMISOS = partes[27];
                    pFEC_PERM_AUT = partes[28];
                    pCO_APLIC_PREF_ARANCELARIA = partes[29];
                    pAPLIC_PREF_ARANCELARIA_CO = partes[30];
                    pREQUIERE_UVA = partes[31];
                    pREQUIERE_CA = partes[32];
                    pFECHA_RECEPCION_CA = partes[33];
                    pNÚMERO_CONSTANCIA_CA = partes[34];
                    pMONTO_CA = partes[35];
                    pFECHA_DOCUMENTOS_COMPLETOS = partes[36];
                    pFECHA_PAGO_PEDIMENTO = partes[37];
                    pFECHA_SOLICITUD_TRANSPORTE = partes[38];
                    pFECHA_MODULACION = partes[39];
                    pMODALIDAD_CAMION_TREN = partes[40];
                    pRESULT_MODULACION_VERDE_ROJO = partes[41];
                    pFECHA_RECONOCIMIENTO = partes[42];
                    pFECHA_LIBERACION = partes[43];
                    pSELLO_ORIGEN = partes[44];
                    pSELLO_FINAL = partes[45];
                    pFECHA_RETENCION_AUTORIDAD = partes[46];
                    pFECHA_LIB_POR_RET_AUT = partes[47];
                    pESTATUS_OPERACION = partes[48];
                    pMOTIVO_ATRASO = partes[49];
                    pOBSERVACIONES = partes[50];
                    pFY = partes[51];

                    sp1 = Upltd_gtnConShipmentId.SP_INB_SUBIRCUSTOMS_GRAL(this.cnBaseDeDatos,
                            pNUMERO_DE_EVENTO,
                            pSHIPMENT_ID,
                            pCONTAINER_ID,
                            pREFERENCIA_AA,
                            pPRIORIDAD,
                            pPAIS_ORIGEN,
                            pSIZE_CONTAINER,
                            pVALOR_USD,
                            pETA_PORT_OF_DISCHARGE,
                            pAGENTE_ADUANAL,
                            pPEDIMENTO_A1,
                            pPEDIMENTO_R1,
                            pMOTIVO_RECTIFICACION_1,
                            pPEDIMENTO_R1_2DO,
                            pMOTIVO_RECTIFICACION_2,
                            pFECHA_RECEPCION_DOCUMENTOS,
                            pRECINTO,
                            pNAVIERA_FORWARDER,
                            pBUQUE,
                            pFECHA_REVALID_LIBE_BL,
                            pFECHA_PREVIO_ORIGEN,
                            pFECHA_PREVIO_DESTINO,
                            pFECHA_RESULTADO_PREVIO,
                            pPROFORMA_FINAL,
                            pREQUIERE_PERMISO,
                            pFECHA_ENVIO_FICHAS_NOTAS,
                            pFEC_RECEPCION_PERMISOS_TRAMIT,
                            pFEC_ACT_PERMISOS,
                            pFEC_PERM_AUT,
                            pCO_APLIC_PREF_ARANCELARIA,
                            pAPLIC_PREF_ARANCELARIA_CO,
                            pREQUIERE_UVA,
                            pREQUIERE_CA,
                            pFECHA_RECEPCION_CA,
                            pNÚMERO_CONSTANCIA_CA,
                            pMONTO_CA,
                            pFECHA_DOCUMENTOS_COMPLETOS,
                            pFECHA_PAGO_PEDIMENTO,
                            pFECHA_SOLICITUD_TRANSPORTE,
                            pFECHA_MODULACION,
                            pMODALIDAD_CAMION_TREN,
                            pRESULT_MODULACION_VERDE_ROJO,
                            pFECHA_RECONOCIMIENTO,
                            pFECHA_LIBERACION,
                            pSELLO_ORIGEN,
                            pSELLO_FINAL,
                            pFECHA_RETENCION_AUTORIDAD,
                            pFECHA_LIB_POR_RET_AUT,
                            pESTATUS_OPERACION,
                            pMOTIVO_ATRASO,
                            pOBSERVACIONES,
                            pFY);
                } else if (agente_id.equals("4002")) {     //Plantilla Cusa

                    String[] partes = valores_celdas.split(",");
                    pNUMERO_DE_EVENTO = partes[0];
                    pSHIPMENT_ID = partes[1];
                    pCONTAINER_ID = partes[2];
                    pREFERENCIA_AA = partes[3];
                    pPRIORIDAD = partes[4];
                    pPAIS_ORIGEN = partes[5];
                    pSIZE_CONTAINER = partes[6];
                    pVALOR_USD = partes[7];
                    pETA_PORT_OF_DISCHARGE = partes[8];
                    pAGENTE_ADUANAL = partes[9];
                    pPEDIMENTO_A1 = partes[10];
                    pPEDIMENTO_R1 = partes[11];
                    pMOTIVO_RECTIFICACION_1 = partes[12];
                    pPEDIMENTO_R1_2DO = partes[13];
                    pMOTIVO_RECTIFICACION_2 = partes[14];
                    pFECHA_RECEPCION_DOCUMENTOS = partes[15];
                    pRECINTO = partes[16];
                    pNAVIERA_FORWARDER = partes[17];
                    pBUQUE = partes[18];
                    pFECHA_REVALID_LIBE_BL = partes[19];
                    pFECHA_PREVIO_ORIGEN = partes[20];
                    pFECHA_PREVIO_DESTINO = partes[21];
                    pFECHA_RESULTADO_PREVIO = partes[22];
                    pPROFORMA_FINAL = partes[23];
                    pREQUIERE_PERMISO = partes[24];
                    pFECHA_ENVIO_FICHAS_NOTAS = partes[25];
                    pFEC_RECEPCION_PERMISOS_TRAMIT = partes[26];
                    pFEC_ACT_PERMISOS = partes[27];
                    pFEC_PERM_AUT = partes[28];
                    pCO_APLIC_PREF_ARANCELARIA = partes[29];
                    pAPLIC_PREF_ARANCELARIA_CO = partes[30];
                    pREQUIERE_UVA = partes[31];
                    pREQUIERE_CA = partes[32];
                    pFECHA_RECEPCION_CA = partes[33];
                    pNÚMERO_CONSTANCIA_CA = partes[34];
                    pMONTO_CA = partes[35];
                    pFECHA_DOCUMENTOS_COMPLETOS = partes[36];
                    pFECHA_PAGO_PEDIMENTO = partes[37];
                    pFECHA_SOLICITUD_TRANSPORTE = partes[38];
                    pFECHA_MODULACION = partes[39];
                    pMODALIDAD_CAMION_TREN = partes[40];
                    pRESULT_MODULACION_VERDE_ROJO = partes[41];
                    pFECHA_RECONOCIMIENTO = partes[42];
                    pFECHA_LIBERACION = partes[43];
                    pSELLO_ORIGEN = partes[44];
                    pSELLO_FINAL = partes[45];
                    pFECHA_RETENCION_AUTORIDAD = partes[46];
                    pFECHA_LIB_POR_RET_AUT = partes[47];
                    pESTATUS_OPERACION = partes[48];
                    pMOTIVO_ATRASO = partes[49];
                    pOBSERVACIONES = partes[50];
                    pNO_BULTOS = partes[51];
                    pPESO_KG = partes[52];
                    pTRANSFERENCIA = partes[53];
                    pFECHA_INICIO_ETIQUETADO = partes[54];
                    pFECHA_TERMINO_ETIQUETADO = partes[55];
                    pHORA_TERMINO_ETIQUETADO = partes[56];
                    pPROVEEDOR = partes[57];
                    pPROVEEDOR_CARGA = partes[58];
                    pFY = partes[59];

                    sp1 = Upltd_gtnConShipmentId.SP_INB_SUBIRCUSTOMS_CUSA(this.cnBaseDeDatos,
                            pNUMERO_DE_EVENTO,
                            pSHIPMENT_ID,
                            pCONTAINER_ID,
                            pREFERENCIA_AA,
                            pPRIORIDAD,
                            pPAIS_ORIGEN,
                            pSIZE_CONTAINER,
                            pVALOR_USD,
                            pETA_PORT_OF_DISCHARGE,
                            pAGENTE_ADUANAL,
                            pPEDIMENTO_A1,
                            pPEDIMENTO_R1,
                            pMOTIVO_RECTIFICACION_1,
                            pPEDIMENTO_R1_2DO,
                            pMOTIVO_RECTIFICACION_2,
                            pFECHA_RECEPCION_DOCUMENTOS,
                            pRECINTO,
                            pNAVIERA_FORWARDER,
                            pBUQUE,
                            pFECHA_REVALID_LIBE_BL,
                            pFECHA_PREVIO_ORIGEN,
                            pFECHA_PREVIO_DESTINO,
                            pFECHA_RESULTADO_PREVIO,
                            pPROFORMA_FINAL,
                            pREQUIERE_PERMISO,
                            pFECHA_ENVIO_FICHAS_NOTAS,
                            pFEC_RECEPCION_PERMISOS_TRAMIT,
                            pFEC_ACT_PERMISOS,
                            pFEC_PERM_AUT,
                            pCO_APLIC_PREF_ARANCELARIA,
                            pAPLIC_PREF_ARANCELARIA_CO,
                            pREQUIERE_UVA,
                            pREQUIERE_CA,
                            pFECHA_RECEPCION_CA,
                            pNÚMERO_CONSTANCIA_CA,
                            pMONTO_CA,
                            pFECHA_DOCUMENTOS_COMPLETOS,
                            pFECHA_PAGO_PEDIMENTO,
                            pFECHA_SOLICITUD_TRANSPORTE,
                            pFECHA_MODULACION,
                            pMODALIDAD_CAMION_TREN,
                            pRESULT_MODULACION_VERDE_ROJO,
                            pFECHA_RECONOCIMIENTO,
                            pFECHA_LIBERACION,
                            pSELLO_ORIGEN,
                            pSELLO_FINAL,
                            pFECHA_RETENCION_AUTORIDAD,
                            pFECHA_LIB_POR_RET_AUT,
                            pESTATUS_OPERACION,
                            pMOTIVO_ATRASO,
                            pOBSERVACIONES,
                            pNO_BULTOS,
                            pPESO_KG,
                            pTRANSFERENCIA,
                            pFECHA_INICIO_ETIQUETADO,
                            pFECHA_TERMINO_ETIQUETADO,
                            pHORA_TERMINO_ETIQUETADO,
                            pPROVEEDOR,
                            pPROVEEDOR_CARGA,
                            pFY);
                } else if (agente_id.equals("4001")) {     //Plantilla Logix

                    String[] partes = valores_celdas.split(",");
                    pNUMERO_DE_EVENTO = partes[0];
                    pSHIPMENT_ID = partes[1];
                    pCONTAINER_ID = partes[2];
                    pREFERENCIA_AA = partes[3];
                    pPRIORIDAD = partes[4];
                    pPAIS_ORIGEN = partes[5];
                    pSIZE_CONTAINER = partes[6];
                    pVALOR_USD = partes[7];
                    pETA_PORT_OF_DISCHARGE = partes[8];
                    pAGENTE_ADUANAL = partes[9];
                    pPEDIMENTO_A1 = partes[10];
                    pPEDIMENTO_R1 = partes[11];
                    pMOTIVO_RECTIFICACION_1 = partes[12];
                    pPEDIMENTO_R1_2DO = partes[13];
                    pMOTIVO_RECTIFICACION_2 = partes[14];
                    pFECHA_RECEPCION_DOCUMENTOS = partes[15];
                    pRECINTO = partes[16];
                    pNAVIERA_FORWARDER = partes[17];
                    pBUQUE = partes[18];
                    pFECHA_REVALID_LIBE_BL = partes[19];
                    pFECHA_PREVIO_ORIGEN = partes[20];
                    pFECHA_PREVIO_DESTINO = partes[21];
                    pFECHA_RESULTADO_PREVIO = partes[22];
                    pPROFORMA_FINAL = partes[23];
                    pREQUIERE_PERMISO = partes[24];
                    pFECHA_ENVIO_FICHAS_NOTAS = partes[25];
                    pFEC_RECEPCION_PERMISOS_TRAMIT = partes[26];
                    pFEC_ACT_PERMISOS = partes[27];
                    pFEC_PERM_AUT = partes[28];
                    pCO_APLIC_PREF_ARANCELARIA = partes[29];
                    pAPLIC_PREF_ARANCELARIA_CO = partes[30];
                    pREQUIERE_UVA = partes[31];
                    pREQUIERE_CA = partes[32];
                    pFECHA_RECEPCION_CA = partes[33];
                    pNÚMERO_CONSTANCIA_CA = partes[34];
                    pMONTO_CA = partes[35];
                    pFECHA_DOCUMENTOS_COMPLETOS = partes[36];
                    pFECHA_PAGO_PEDIMENTO = partes[37];
                    pFECHA_SOLICITUD_TRANSPORTE = partes[38];
                    pFECHA_MODULACION = partes[39];
                    pMODALIDAD_CAMION_TREN = partes[40];
                    pRESULT_MODULACION_VERDE_ROJO = partes[41];
                    pFECHA_RECONOCIMIENTO = partes[42];
                    pFECHA_LIBERACION = partes[43];
                    pSELLO_ORIGEN = partes[44];
                    pSELLO_FINAL = partes[45];
                    pFECHA_RETENCION_AUTORIDAD = partes[46];
                    pFECHA_LIB_POR_RET_AUT = partes[47];
                    pESTATUS_OPERACION = partes[48];
                    pMOTIVO_ATRASO = partes[49];
                    pOBSERVACIONES = partes[50];
                    pLLEGADA_A_NOVA = partes[51];
                    pLLEGADA_A_GLOBE_TRADE_SD = partes[52];
                    pARCHIVO_M = partes[53];
                    pFECHA_ARCHIVO_M = partes[54];
                    pFECHA_SOLICIT_MANIP = partes[55];
                    pFECHA_VENCIM_MANIP = partes[56];
                    pFECHA_CONFIRM_CLAVE_PEDIM = partes[57];
                    pFECHA_RECEP_INCREMENT = partes[58];
                    pT_E = partes[59];
                    pFECHA_VENCIM_INBOUND = partes[60];
                    pFY = partes[61];

                    sp1 = Upltd_gtnConShipmentId.SP_INB_SUBIRCUSTOMS_LOGIX(this.cnBaseDeDatos,
                            pNUMERO_DE_EVENTO,
                            pSHIPMENT_ID,
                            pCONTAINER_ID,
                            pREFERENCIA_AA,
                            pPRIORIDAD,
                            pPAIS_ORIGEN,
                            pSIZE_CONTAINER,
                            pVALOR_USD,
                            pETA_PORT_OF_DISCHARGE,
                            pAGENTE_ADUANAL,
                            pPEDIMENTO_A1,
                            pPEDIMENTO_R1,
                            pMOTIVO_RECTIFICACION_1,
                            pPEDIMENTO_R1_2DO,
                            pMOTIVO_RECTIFICACION_2,
                            pFECHA_RECEPCION_DOCUMENTOS,
                            pRECINTO,
                            pNAVIERA_FORWARDER,
                            pBUQUE,
                            pFECHA_REVALID_LIBE_BL,
                            pFECHA_PREVIO_ORIGEN,
                            pFECHA_PREVIO_DESTINO,
                            pFECHA_RESULTADO_PREVIO,
                            pPROFORMA_FINAL,
                            pREQUIERE_PERMISO,
                            pFECHA_ENVIO_FICHAS_NOTAS,
                            pFEC_RECEPCION_PERMISOS_TRAMIT,
                            pFEC_ACT_PERMISOS,
                            pFEC_PERM_AUT,
                            pCO_APLIC_PREF_ARANCELARIA,
                            pAPLIC_PREF_ARANCELARIA_CO,
                            pREQUIERE_UVA,
                            pREQUIERE_CA,
                            pFECHA_RECEPCION_CA,
                            pNÚMERO_CONSTANCIA_CA,
                            pMONTO_CA,
                            pFECHA_DOCUMENTOS_COMPLETOS,
                            pFECHA_PAGO_PEDIMENTO,
                            pFECHA_SOLICITUD_TRANSPORTE,
                            pFECHA_MODULACION,
                            pMODALIDAD_CAMION_TREN,
                            pRESULT_MODULACION_VERDE_ROJO,
                            pFECHA_RECONOCIMIENTO,
                            pFECHA_LIBERACION,
                            pSELLO_ORIGEN,
                            pSELLO_FINAL,
                            pFECHA_RETENCION_AUTORIDAD,
                            pFECHA_LIB_POR_RET_AUT,
                            pESTATUS_OPERACION,
                            pMOTIVO_ATRASO,
                            pOBSERVACIONES,
                            pLLEGADA_A_NOVA,
                            pLLEGADA_A_GLOBE_TRADE_SD,
                            pARCHIVO_M,
                            pFECHA_ARCHIVO_M,
                            pFECHA_SOLICIT_MANIP,
                            pFECHA_VENCIM_MANIP,
                            pFECHA_CONFIRM_CLAVE_PEDIM,
                            pFECHA_RECEP_INCREMENT,
                            pT_E,
                            pFECHA_VENCIM_INBOUND,
                            pFY);
                }

                //Ejecutar instancia
                sp1.execute();
                this.cnBaseDeDatos.Cerrar(sp1);

                //Finalizar conexión D.B 
                this.cnBaseDeDatos.Finalizar();
                res = "1";
                
            } catch (SQLException exception) {
                System.out.println("--" + exception);
                res = "2";
            }
            
            out.print(res);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(uploadFileExcelCustoms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(uploadFileExcelCustoms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
