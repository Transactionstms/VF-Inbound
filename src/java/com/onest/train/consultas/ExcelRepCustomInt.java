/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.train.consultas;

import com.onest.oracle.DB;
import com.onest.oracle.DBConfData;
import com.onest.oracle.OracleDB;
import com.tacts.evidencias.inbound.CreatExcelReporteCustoms;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author grecendiz
 */
public class ExcelRepCustomInt extends HttpServlet {
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
              HttpSession ownsession = request.getSession();
            DB db = new DB((DBConfData) ownsession.getAttribute("db.data"));
            DBConfData dbData = (DBConfData) ownsession.getAttribute("db.data");
            String UserId = (String) ownsession.getAttribute("login.user_id_number");
            String cve = (String) ownsession.getAttribute("cbdivcuenta");
            OracleDB oraDB = new OracleDB(dbData.getIPv4(), dbData.getPuerto(), dbData.getSid());
            oraDB.connect(dbData.getUser(), dbData.getPassword());
             String filtros2 = request.getParameter("f2");
             String valores  = request.getParameter("val");
             
             System.out.println("va"+valores);
            
            String  sql = " WITH SUM_QUANTITY AS (SELECT SHIPMENT_ID, CONTAINER1, SUM(QUANTITY) AS SUMA FROM TRA_INC_GTN_TEST GROUP BY SHIPMENT_ID, CONTAINER1) "
                + " SELECT DISTINCT "
                    /*1*/ + " TIE.ID_EVENTO, "
                    /*2*/ + " NVL(BP.RESPONSABLE, ' ') AS RESPONSABLE, "
                    /*3*/ + " GTN.FINAL_DESTINATION, "
                    /*4*/ + " GTN.BRAND_DIVISION, "
                    /*5*/ + " NVL(TID.DIVISION_NOMBRE,' '), "
                    /*6*/ + " GTN.SHIPMENT_ID, "
                    /*7*/ + " GTN.CONTAINER1, "
                    /*8*/ + " GTN.BL_AWB_PRO, "
                    /*9*/ + " GTN.LOAD_TYPE_FINAL, "
                    /*10*/ + "  gtn.CANTIDAD_FINAL, "
                    /*11*/ + " TIP1.NOMBRE_POD, "
                    /*12*/ + " REPLACE(NVL(TO_CHAR(GTN.EST_DEPARTURE_POL, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*13*/ + " REPLACE(NVL(TO_CHAR(GTN.ETA_PORT_DISCHARGE, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*14*/ + " NVL(GTN.MAX_FLETE, 0) AS EST_ETA_DC, "
                    /*15*/ + " REPLACE(NVL(TO_CHAR(GTN.FECHA_CAPTURA, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*16*/ + " TIP2.NOMBRE_POL, "
                    /*17*/ + " NVL(TAA.AGENTE_ADUANAL_NOMBRE, ' ') AS AGENTE_ADUANAL, "
                    /*18*/ + " GTN.PLANTILLA_ID, "
                    /*19*/ + " SYSDATE AS FECHA_CAPTURAOLD, "
                    /*20*/ + " TIP1.NOMBRE_POD, "
                    /*21*/ + " TIP2.NOMBRE_POL, "
                    /*22*/ + " TIBD.NOMBRE_BD, "
                    /*23*/ + " REPLACE(NVL(TO_CHAR(GTN.ETA_PLUS2, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*24*/ + " REPLACE(NVL(TO_CHAR(TIE.EST_ETA_DC, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*25*/ + " REPLACE(NVL(TO_CHAR(GTN.ETA_PLUS, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*26*/ + " NVL(TIE.OBSERVACIONES, ' ') AS OBSERVACIONES, "
                    /*27*/ + " TIE.ESTATUS_EVENTO, "
                    /*28*/ + " NVL(TIE.REFERENCIA_AA,' '), "
                    /*29*/ + " REPLACE(NVL(TO_CHAR(TIE.FECHA_CAPTURA, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*30*/ + " NVL(TIE.PRIORIDAD,' '), "
                    /*31*/ + " NVL(TIC.REFERENCIA_AA,' '), "
                    /*32*/ + " NVL(TIC.PAIS_ORIGEN,' '), "
                    /*33*/ + " NVL(TIC.SIZE_CONTAINER,' '), "
                    /*34*/ + " NVL(TIC.VALOR_USD,' '), "
                    /*35*/ + " REPLACE(NVL(TO_CHAR(TIC.ETA_PORT_OF_DISCHARGE, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*36*/ + " NVL(TIC.AGENTE_ADUANAL,' '), "
                    /*37*/ + " NVL(TIC.PEDIMENTO_A1,' '), "
                    /*38*/ + " NVL(TIC.PEDIMENTO_R1,' '), "
                    /*39*/ + " NVL(TIC.MOTIVO_RECTIFICACION_1,' '), "
                    /*40*/ + " NVL(TIC.PEDIMENTO_R1_2DO,' '), "
                    /*41*/ + " NVL(TIC.MOTIVO_RECTIFICACION_2,' '), "
                    /*42*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_RECEPCION_DOCUMENTOS, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*43*/ + " NVL(TIC.RECINTO,' '), "
                    /*44*/ + " NVL(TIC.NAVIERA_FORWARDER,' '), "
                    /*45*/ + " NVL(TIC.BUQUE,' '), "
                    /*46*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_REVALID_LIBE_BL, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*47*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_PREVIO_ORIGEN, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*48*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_PREVIO_DESTINO, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*49*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_RESULTADO_PREVIO, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*50*/ + " REPLACE(NVL(TO_CHAR(TIC.PROFORMA_FINAL, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*51*/ + " NVL(TIC.REQUIERE_PERMISO,' '), "
                    /*52*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_ENVIO_FICHAS_NOTAS, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*53*/ + " REPLACE(NVL(TO_CHAR(TIC.FEC_RECEPCION_PERMISOS_TRAMIT, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*54*/ + " REPLACE(NVL(TO_CHAR(TIC.FEC_ACT_PERMISOS, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*55*/ + " REPLACE(NVL(TO_CHAR(TIC.FEC_PERM_AUT, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*56*/ + " NVL(TIC.CO_APLIC_PREF_ARANCELARIA,' '), "
                    /*57*/ + " NVL(TIC.APLIC_PREF_ARANCELARIA_CO,' '), "
                    /*58*/ + " NVL(TIC.REQUIERE_UVA,' '), "
                    /*59*/ + " NVL(TIC.REQUIERE_CA,' '), "
                    /*60*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_RECEPCION_CA, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*61*/ + " NVL(TIC.NÚMERO_CONSTANCIA_CA,' '), "
                    /*62*/ + " NVL(TIC.MONTO_CA,' '), "
                    /*63*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_DOCUMENTOS_COMPLETOS, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*64*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_PAGO_PEDIMENTO, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*65*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_SOLICITUD_TRANSPORTE, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*66*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_MODULACION, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*67*/ + " NVL(TIC.MODALIDAD_CAMION_TREN,' '), "
                    /*68*/ + " NVL(TIC.RESULT_MODULACION_VERDE_ROJO,' '), "
                    /*69*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_RECONOCIMIENTO, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*70*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_LIBERACION, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*71*/ + " NVL(TIC.SELLO_ORIGEN,' '), "
                    /*72*/ + " NVL(TIC.SELLO_FINAL,' '), "
                    /*73*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_RETENCION_AUTORIDAD, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*74*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_LIB_POR_RET_AUT, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*75*/ + " NVL(TEC.DESCRIPCION_ESTADO,' '), "
                    /*76*/ + " NVL(TIC.MOTIVO_ATRASO,' '), "
                    /*77*/ + " NVL(TIC.OBSERVACIONES,' '), "
                    /*78*/ + " REPLACE(NVL(TO_CHAR(TIC.LLEGADA_A_NOVA, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*79*/ + " REPLACE(NVL(TO_CHAR(TIC.LLEGADA_A_GLOBE_TRADE_SD, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*80*/ + " NVL(TIC.ARCHIVO_M,' '), "
                    /*81*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_ARCHIVO_M, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*82*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_SOLICIT_MANIP, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*83*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_VENCIM_MANIP, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*84*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_CONFIRM_CLAVE_PEDIM, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*85*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_RECEP_INCREMENT, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*86*/ + " NVL(TIC.T_E,' '), "
                    /*87*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_VENCIM_INBOUND, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*88*/ + " NVL(TIC.NO_BULTOS,' '), "
                    /*89*/ + " NVL(TIC.PESO_KG,' '), "
                    /*90*/ + " NVL(TIC.TRANSFERENCIA,' '), "
                    /*91*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_INICIO_ETIQUETADO, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*92*/ + " REPLACE(NVL(TO_CHAR(TIC.FECHA_TERMINO_ETIQUETADO, 'MON/DD/YY'),' '), '01/01/1970', ' '), "
                    /*93*/ + " NVL(TIC.HORA_TERMINO_ETIQUETADO,' '), "
                    /*94*/ + " NVL(TIC.PROVEEDOR,' '), "
                    /*95*/ + " NVL(TIC.PROVEEDOR_CARGA,' '), "
                    /*96*/ + " NVL(TIC.FY,' '), "
                    /*97*/ + " NVL(TIC.AGENTE_ADUANAL_ID,0), "
                    /*98*/ + " NVL(TIC.PRIORIDAD,'No'), "
                    /*99*/ + " NVL(GTN.ESTATUS,1), "
                   /*100*/ + " NVL(TIC.ESTATUS_SEMAFORO,'0'), "
                   /*101*/ + " NVL(TIP1.ADUANA_NUMERO,0), "
                   /*102*/ + " NVL(TAA.PATENTE_AGENTE_ADUANERO,'0000'), "
                   /*103*/ + " NVL(TO_CHAR(GTN.FECHA_IMPORTACION, 'DD/MM/YY'),'-') "
                            + " FROM TRA_INB_EVENTO TIE "
                            + " INNER JOIN TRA_DESTINO_RESPONSABLE BP ON BP.USER_NID = TIE.USER_NID "
                            + " INNER JOIN TRA_INC_GTN_TEST GTN ON GTN.PLANTILLA_ID = TIE.PLANTILLA_ID "
                            + " INNER JOIN TRA_INB_POD TIP1 ON TIP1.ID_POD = GTN.POD "
                            + " INNER JOIN TRA_INB_POL TIP2 ON TIP2.ID_POL = GTN.POL "
                            + " INNER JOIN TRA_INB_BRAND_DIVISION TIBD ON TIBD.ID_BD = GTN.BRAND_DIVISION "
                            + " INNER JOIN TRA_INB_AGENTE_ADUANAL TAA ON TAA.AGENTE_ADUANAL_ID = TIP1.AGENTE_ADUANAL_ID "
                            + " INNER JOIN TRA_INB_DIVISION TID ON TID.ID_DIVISION = GTN.SBU_NAME "
                            + " INNER JOIN SUM_QUANTITY SQ ON SQ.SHIPMENT_ID = GTN.SHIPMENT_ID AND SQ.CONTAINER1 = GTN.CONTAINER1 "
                            + " LEFT JOIN TRA_INB_CUSTOMS TIC ON GTN.SHIPMENT_ID = TIC.SHIPMENT_ID "
                            + " LEFT JOIN TRA_ESTADOS_CUSTOMS TEC ON GTN.ESTATUS = TEC.ID_ESTADO "
                            + " LEFT JOIN TRA_INB_SEMAFORO TISE ON TIC.SHIPMENT_ID = TISE.SHIPMENT_ID "
                            + " WHERE TIE.ESTADO = 1 "
                       //     + " AND to_date(trunc(tie.FECHA_CAPTURA),'dd/mm/yy') >= to_date((SELECT MIN(TO_DATE(FECHA_CAPTURA, 'DD/MM/YY')) FROM TRA_INB_EVENTO WHERE ESTADO = 1),'dd/mm/yy') "
                       //      + " AND to_date(trunc(tie.FECHA_CAPTURA),'dd/mm/yy') <= to_date((SELECT MAX(TO_DATE(FECHA_CAPTURA, 'DD/MM/YY')) FROM TRA_INB_EVENTO WHERE ESTADO = 1),'dd/mm/yy') "
                       //    + " AND tid.division_nombre <> 'No/DSN' "
                     // + " AND gtn.load_type_final IS NOT NULL "
                            + " AND tie.id_evento >= 240000 AND TIP1.AGENTE_ADUANAL_ID IN (" + filtros2 + ") and GTN.FINAL_DESTINATION not in ('OD1013 MARKETING MEXICO')"
                    + " ORDER BY tie.id_evento, tibd.nombre_bd, GTN.SHIPMENT_ID ASC " ;
             String pathExcelCustoms = "";
                String AgentType = "";
            String nameAgentType = "";
             CreatExcelReporteCustoms excel = new CreatExcelReporteCustoms();
              ConsultasQuery fac = new ConsultasQuery();
             if (db.doDB(fac.consultarAgenteAduanalCustoms(UserId))) {
                for (String[] rowA : db.getResultado()) {
                    AgentType = rowA[0];
                    nameAgentType = rowA[1];
                }
            }

             
                      
         // Crear un List<Integer> vacío
        List<Integer> numeros = new ArrayList<>();

        // Dividir la cadena en un array de strings usando la coma como separador
        String[] partes =  valores.split(",");

        // Convertir cada parte en un Integer y agregarla a la lista
        for (String parte : partes) {
            numeros.add(Integer.parseInt(parte.trim())); // Convertir y agregar a la lista
        }

        // Imprimir la lista resultante
        System.out.println("Contenido del List: " + numeros);
        
//                       crearAPartirDeArrayListReporteEventosGral
             pathExcelCustoms = excel.crearAPartirDeArrayListReporteEventosGralINC(sql, AgentType, nameAgentType,"2",numeros);
            
            
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
        processRequest(request, response);
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
        processRequest(request, response);
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
